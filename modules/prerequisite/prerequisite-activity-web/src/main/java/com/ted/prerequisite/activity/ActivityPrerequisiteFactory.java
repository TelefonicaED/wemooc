package com.ted.prerequisite.activity;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.ted.lms.service.LearningActivityLocalService;
import com.ted.lms.service.LearningActivityResultLocalService;
import com.ted.prerequisite.activity.internal.constants.ActivityPrerequisitePortletKeys;
import com.ted.prerequisite.model.BasePrerequisiteFactory;
import com.ted.prerequisite.model.Prerequisite;
import com.ted.prerequisite.model.PrerequisiteFactory;
import com.ted.prerequisite.model.PrerequisiteRelation;
import com.ted.prerequisite.service.PrerequisiteRelationLocalService;
import com.ted.prerequisite.service.PrerequisiteRelationLocalServiceUtil;

import java.util.List;
import java.util.Locale;
import javax.portlet.PortletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
    immediate = true,
    property = {},
    service = PrerequisiteFactory.class
)
public class ActivityPrerequisiteFactory extends BasePrerequisiteFactory {

	@Override
	public String getClassName() {
		return ActivityPrerequisiteFactory.class.getName();
	}
	
	@Override
	public ActivityPrerequisite getPrerequisite(PrerequisiteRelation prerequisiteRelation) throws PortalException {	
		return new ActivityPrerequisite(prerequisiteRelation, learningActivityResultLocalService, prerequisiteRelationLocalService, learningActivityLocalService);
	}
	
	@Override
	public void savePrerequisites(long classNameId, long classPK, PortletRequest request) throws PortalException{
		
		long actId = ParamUtil.getLong(request, "prerequisiteActId", 0);
		
		List<PrerequisiteRelation> prerequisiteRelations = PrerequisiteRelationLocalServiceUtil.getPrerequisiteRelation(getClassNameId(), classNameId, classPK);
		
		Prerequisite prerequisite = null;
		if(actId > 0 && (prerequisiteRelations == null || prerequisiteRelations.size() == 0)) {
			PrerequisiteRelation prerequisiteRelation = prerequisiteRelationLocalService.addPrerequisiteRelation(PortalUtil.getClassNameId(ActivityPrerequisiteFactory.class), classNameId, classPK, "");
			prerequisite = getPrerequisite(prerequisiteRelation);
			prerequisite.setExtraContent(request);
		}else if(actId > 0){
			prerequisite = getPrerequisite(prerequisiteRelations.get(0));
			prerequisite.setExtraContent(request);
		}else if(prerequisiteRelations != null && prerequisiteRelations.size() > 0){
			prerequisiteRelationLocalService.deletePrerequisiteRelations(PortalUtil.getClassNameId(ActivityPrerequisiteFactory.class), classNameId, classPK);
		}

		
	}
	
	@Override
	public String getIconCssClass() {
		return "learning-activity";
	}
	
	@Override
	public String getTitle(Locale locale) {
		return LanguageUtil.get(locale, "prerequisite.learning-activity");
	}
	
	@Override
	public String getDescription(Locale locale) {
		return LanguageUtil.get(locale, "prerequisite.learning-activity.description");
	}
	
	@Override
	public String getURLSpecificContent() {
		return "/edit.jsp";
	}
	
	@Override
	public String getPortletId(){
		return  ActivityPrerequisitePortletKeys.ACTIVITY;
	}
	
	@Reference(unbind = "-")
	protected void setLearningActivityResultLocalService(LearningActivityResultLocalService learningActivityResultLocalService) {
		this.learningActivityResultLocalService = learningActivityResultLocalService;
	}
	
	protected LearningActivityResultLocalService learningActivityResultLocalService;
	
	@Reference(unbind = "-")
	protected void setLearningActivityLocalService(LearningActivityLocalService learningActivityLocalService) {
		this.learningActivityLocalService = learningActivityLocalService;
	}
	
	protected LearningActivityLocalService learningActivityLocalService;
	
	@Reference(unbind = "-")
	protected void setPrerequisiteRelationLocalService(PrerequisiteRelationLocalService prerequisiteRelationLocalService) {
		this.prerequisiteRelationLocalService = prerequisiteRelationLocalService;
	}
	
	protected PrerequisiteRelationLocalService prerequisiteRelationLocalService;
}
