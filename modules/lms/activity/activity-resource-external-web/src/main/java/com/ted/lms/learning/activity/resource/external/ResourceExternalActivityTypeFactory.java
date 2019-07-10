package com.ted.lms.learning.activity.resource.external;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.ted.lms.learning.activity.QuestionsLearningActivityTypeFactory;
import com.ted.lms.learning.activity.question.service.AnswerLocalService;
import com.ted.lms.learning.activity.question.service.QuestionLocalService;
import com.ted.lms.learning.activity.resource.external.web.constants.ResourceExternalConstants;
import com.ted.lms.learning.activity.resource.external.web.constants.ResourceExternalPortletKeys;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.LearningActivityType;
import com.ted.lms.model.LearningActivityTypeFactory;
import com.ted.lms.service.LearningActivityResultLocalService;

import java.util.Locale;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
    immediate = true,
    property = {},
    service = LearningActivityTypeFactory.class
)
public class ResourceExternalActivityTypeFactory extends QuestionsLearningActivityTypeFactory {
	
	@Override
	public String getClassName() {
		return ResourceExternalActivityTypeFactory.class.getName();
	}

	@Override
	public long getType() {
		return ResourceExternalConstants.TYPE;
	}
	
	public ResourceExternalActivityType getResourceExternalActivityType(LearningActivity activity) {
		return new ResourceExternalActivityType(activity, learningActivityResultLocalService, questionLocalService, answerLocalService);
	}
	
	@Override
	public LearningActivityType getLearningActivityType(LearningActivity activity) throws PortalException {
		return new ResourceExternalActivityType(activity, learningActivityResultLocalService, questionLocalService, answerLocalService);
	}
	
	@Override
	public String getPortletId() {
		return ResourceExternalPortletKeys.RESOURCE_EXTERNAL;
	}
	
	@Override
	public String getName(Locale locale) {
		ResourceBundle resourceBundle = resourceBundleLoader.loadResourceBundle(locale);
		return LanguageUtil.get(resourceBundle, "learning-activity.external");
	}
	
	@Override
	public String getDescription(Locale locale) {
		ResourceBundle resourceBundle = resourceBundleLoader.loadResourceBundle(locale);
		return LanguageUtil.get(resourceBundle, "learning-activity.external.help-message");
	}
	
	@Override
	public String getURLSpecificContent() {
		return "/edit.jsp";
	}
	
	@Override
	public String getIconCssClass() {
		return "activity-resource-external";
	}
	
	@Override
	public boolean isManualCalificationAllowed() {
		return false;
	}
	
	@Override
	public boolean canDeleteTries() {
		return true;
	}
	
	@Override
	public String getSpecificResultsPage() {
		return "/view_results.jsp";
	}
	
	@Override
	public boolean hasDocuments() {
		return true;
	}
	
	@Reference(unbind = "-")
	public void setResourceBundleLoader(ResourceBundleLoader resourceBundleLoader) {

		this.resourceBundleLoader = resourceBundleLoader;
	}

	protected ResourceBundleLoader resourceBundleLoader;
	
	@Reference(unbind = "-")
	protected void setLearningActivityResultLocalService(LearningActivityResultLocalService learningActivityResultLocalService) {
		this.learningActivityResultLocalService = learningActivityResultLocalService;
	}
	protected LearningActivityResultLocalService learningActivityResultLocalService;
	
	@Reference(unbind = "-")
	protected void QuestionLocalService(QuestionLocalService questionLocalService) {
		this.questionLocalService = questionLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setAnswerLocalService(AnswerLocalService answerLocalService) {
		this.answerLocalService = answerLocalService;
	}
}
