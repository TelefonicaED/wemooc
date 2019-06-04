package com.ted.prerequisite.activity;

import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.xml.Element;
import com.ted.lms.model.LearningActivityResult;
import com.ted.lms.model.ModuleResult;
import com.ted.lms.service.LearningActivityResultLocalService;
import com.ted.lms.service.ModuleResultLocalService;
import com.ted.prerequisite.activity.internal.constants.ActivityPrerequisiteConstants;
import com.ted.prerequisite.model.BasePrerequisite;
import com.ted.prerequisite.model.PrerequisiteRelation;
import com.ted.prerequisite.service.PrerequisiteRelationLocalService;

import javax.portlet.PortletRequest;

public class ActivityPrerequisite extends BasePrerequisite{

	public ActivityPrerequisite(PrerequisiteRelation prerequisiteRelation, LearningActivityResultLocalService learningActivityResultLocalService, 
								PrerequisiteRelationLocalService prerequisiteRelationLocalService) {
		super(prerequisiteRelation, prerequisiteRelationLocalService);
		this.learningActivityResultLocalService = learningActivityResultLocalService;
	}
	
	public ActivityPrerequisite(long classNameId, long classPK, LearningActivityResultLocalService learningActivityResultLocalService, 
				PrerequisiteRelationLocalService prerequisiteRelationLocalService) {
		super(classNameId, classPK, prerequisiteRelationLocalService);
		this.learningActivityResultLocalService = learningActivityResultLocalService;
		if(prerequisiteRelation == null) {
			prerequisiteRelation = prerequisiteRelationLocalService.getPrerequisiteRelation(PortalUtil.getClassNameId(ActivityPrerequisiteFactory.class), classNameId, classPK);
		}
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
			prerequisiteRelation = prerequisiteRelationLocalService.addPrerequisiteRelation(PortalUtil.getClassNameId(ActivityPrerequisiteFactory.class), classNameId, classPK);
		}
		
		if(actId > 0) {
			JSONObject extraData = prerequisiteRelation.getExtraDataJSON();
			extraData.put(ActivityPrerequisiteConstants.JSON_ACT_ID, actId);
			prerequisiteRelation.setExtraData(extraData.toString());
			prerequisiteRelationLocalService.updatePrerequisiteRelation(prerequisiteRelation);
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
	
	private LearningActivityResultLocalService learningActivityResultLocalService;

}
