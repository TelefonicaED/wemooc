package com.ted.lms.learning.activity.test;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.ted.lms.learning.activity.question.service.QuestionLocalService;
import com.ted.lms.learning.activity.test.web.constants.TestConstants;
import com.ted.lms.learning.activity.test.web.constants.TestPortletKeys;
import com.ted.lms.model.BaseLearningActivityTypeFactory;
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
public class TestActivityTypeFactory extends BaseLearningActivityTypeFactory {
	
	@Override
	public String getClassName() {
		return TestActivityTypeFactory.class.getName();
	}

	@Override
	public long getType() {
		return TestConstants.TYPE;
	}
	
	@Override
	public LearningActivityType getLearningActivityType(LearningActivity activity) throws PortalException {
		return new TestActivityType(activity, learningActivityResultLocalService, questionLocalService);
	}
	
	@Override
	public String getPortletId() {
		return TestPortletKeys.TEST;
	}
	
	@Override
	public String getName(Locale locale) {
		ResourceBundle resourceBundle = resourceBundleLoader.loadResourceBundle(locale);
		return LanguageUtil.get(resourceBundle, "learningactivity.test");
	}
	
	@Override
	public String getDescription(Locale locale) {
		ResourceBundle resourceBundle = resourceBundleLoader.loadResourceBundle(locale);
		return LanguageUtil.get(resourceBundle, "learningactivity.test.helpmessage");
	}
	
	@Override
	public String getURLSpecificContent() {
		return "/test/activity/edit.jsp";
	}
	
	@Override
	public String getIconCssClass() {
		return "activity-test";
	}
	
	@Override
	public double getDefaultScore() {
		return TestConstants.DEFAULT_SCORE;
	}
	
	@Override
	public boolean isScoreConfigurable() {
		return true;
	}
	
	@Override
	public boolean isTriesConfigurable() {
		return true;
	}
	
	@Override
	public boolean isFeedbackCorrectConfigurable() {
		return true;
	}
	
	@Override
	public boolean isFeedbackNoCorrectConfigurable() {
		return true;
	}
	
	@Override
	public boolean canDeleteTries() {
		return true;
	}
	
	@Reference(unbind = "-")
	protected void setLearningActivityResultLocalService(LearningActivityResultLocalService learningActivityResultLocalService) {
		this.learningActivityResultLocalService = learningActivityResultLocalService;
	}
	
	protected LearningActivityResultLocalService learningActivityResultLocalService;
	
	@Reference(unbind = "-")
	protected void setQuestionLocalService(QuestionLocalService questionLocalService) {
		this.questionLocalService = questionLocalService;
	}
	
	protected QuestionLocalService questionLocalService;
	
	@Reference(unbind = "-")
	public void setResourceBundleLoader(ResourceBundleLoader resourceBundleLoader) {

		this.resourceBundleLoader = resourceBundleLoader;
	}

	protected ResourceBundleLoader resourceBundleLoader;
}