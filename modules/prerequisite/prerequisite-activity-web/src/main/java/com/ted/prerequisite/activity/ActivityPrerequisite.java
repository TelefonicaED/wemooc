package com.ted.prerequisite.activity;

import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.xml.Element;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.LearningActivityResult;
import com.ted.lms.service.LearningActivityLocalService;
import com.ted.lms.service.LearningActivityResultLocalService;
import com.ted.prerequisite.activity.internal.constants.ActivityPrerequisiteConstants;
import com.ted.prerequisite.model.BasePrerequisite;
import com.ted.prerequisite.model.PrerequisiteFactory;
import com.ted.prerequisite.model.PrerequisiteRelation;
import com.ted.prerequisite.registry.PrerequisiteFactoryRegistryUtil;
import com.ted.prerequisite.service.PrerequisiteRelationLocalService;

import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletRequest;

public class ActivityPrerequisite extends BasePrerequisite{

	public ActivityPrerequisite(PrerequisiteRelation prerequisiteRelation, LearningActivityResultLocalService learningActivityResultLocalService, 
								PrerequisiteRelationLocalService prerequisiteRelationLocalService, LearningActivityLocalService learningActivityLocalService) {
		super(prerequisiteRelation, prerequisiteRelationLocalService);
		this.learningActivityResultLocalService = learningActivityResultLocalService;
		this.learningActivityLocalService = learningActivityLocalService;
	}
	
	@Override
	public String getTitle(Locale locale) {
		JSONObject extraData = prerequisiteRelation.getExtraDataJSON();
		long actId = extraData.getLong(ActivityPrerequisiteConstants.JSON_ACT_ID);
		
		String title  = "";
		
		try {
			LearningActivity activity = learningActivityLocalService.getLearningActivity(actId);
			title = activity.getTitle(locale);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return title;
	}

	@Override
	public String getClassName() {
		return ActivityPrerequisite.class.getName();
	}

	@Override
	public boolean isPassed(long userId) {
		JSONObject extraData = prerequisiteRelation.getExtraDataJSON();
		long actId = extraData.getLong(ActivityPrerequisiteConstants.JSON_ACT_ID);
		
		LearningActivityResult activityResult = learningActivityResultLocalService.getLearningActivityResult(actId, userId);
		
		return activityResult != null && activityResult.isPassed();
	}

	@Override
	public void setExtraContent(PortletRequest request) throws PortalException{
		long actId = ParamUtil.getLong(request, "prerequisiteActId", 0);
		
		if(actId > 0 && prerequisiteRelation == null) {
			prerequisiteRelation = prerequisiteRelationLocalService.addPrerequisiteRelation(PortalUtil.getClassNameId(ActivityPrerequisiteFactory.class), classNameId, classPK, "");
		}
		
		if(actId > 0) {
			JSONObject extraData = prerequisiteRelation.getExtraDataJSON();
			extraData.put(ActivityPrerequisiteConstants.JSON_ACT_ID, actId);
			prerequisiteRelation.setExtraData(extraData.toString());
			prerequisiteRelation = prerequisiteRelationLocalService.updatePrerequisiteRelation(prerequisiteRelation);
		}else if(actId == 0 && prerequisiteRelation != null){
			prerequisiteRelationLocalService.deletePrerequisiteRelation(prerequisiteRelation);
			prerequisiteRelation = null;
		}
	}

	//Debemos cambiar la referencia por la nueva
	@Override
	public String doImportStagedModel(PortletDataContext portletDataContext, Element element) {
		return "";
	}
	
	@Override
	public PrerequisiteFactory getPrerequisiteFactory(){
		if (prerequisiteFactory != null) {
			return prerequisiteFactory;
		}
		
		prerequisiteFactory = PrerequisiteFactoryRegistryUtil.getPrerequisiteFactoryByClassName(ActivityPrerequisiteFactory.class.getName());

		return prerequisiteFactory;
	}
	
	@Override
	public void updatePrerequisiteCopied(long classNameId, Object ... params) {
		if(classNameId == PortalUtil.getClassNameId(LearningActivity.class) && params != null && params.length > 0 && params[0] instanceof Map<?,?>) {
			Map<Long,Long> activitiesRelation = (Map<Long,Long>)params[0];
			
			JSONObject extraData = prerequisiteRelation.getExtraDataJSON();
			extraData.put(ActivityPrerequisiteConstants.JSON_ACT_ID, activitiesRelation.get(extraData.getLong(ActivityPrerequisiteConstants.JSON_ACT_ID)));
			
			prerequisiteRelation.setExtraData(extraData.toJSONString());
			
			prerequisiteRelation = prerequisiteRelationLocalService.updatePrerequisiteRelation(prerequisiteRelation);
		}
	}
	
	private LearningActivityResultLocalService learningActivityResultLocalService;
	private LearningActivityLocalService learningActivityLocalService;
}
