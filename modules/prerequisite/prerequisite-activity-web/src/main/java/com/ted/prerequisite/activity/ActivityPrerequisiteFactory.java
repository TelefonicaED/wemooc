package com.ted.prerequisite.activity;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.service.LearningActivityLocalService;
import com.ted.lms.service.LearningActivityResultLocalService;
import com.ted.prerequisite.activity.internal.constants.ActivityPrerequisitePortletKeys;
import com.ted.prerequisite.model.BasePrerequisiteFactory;
import com.ted.prerequisite.model.Prerequisite;
import com.ted.prerequisite.model.PrerequisiteFactory;
import com.ted.prerequisite.model.PrerequisiteRelation;
import com.ted.prerequisite.service.PrerequisiteRelationLocalService;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

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

/*	@Override
	public Prerequisite getPrerequisite(long classNameId, long classPK) throws PortalException {
		System.out.println("prerequisiteRelationLocalService: " + prerequisiteRelationLocalService);
		return new ActivityPrerequisite(classNameId, classPK, learningActivityResultLocalService, prerequisiteRelationLocalService, learningActivityLocalService);
	}*/
	
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
