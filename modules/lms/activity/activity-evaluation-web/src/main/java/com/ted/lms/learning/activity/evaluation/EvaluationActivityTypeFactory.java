package com.ted.lms.learning.activity.evaluation;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.ted.lms.learning.activity.evaluation.web.constants.EvaluationConstants;
import com.ted.lms.learning.activity.evaluation.web.constants.EvaluationPortletKeys;
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
public class EvaluationActivityTypeFactory extends BaseLearningActivityTypeFactory{
	
	@Override
	public String getClassName() {
		return EvaluationActivityTypeFactory.class.getName();
	}

	@Override
	public long getType() {
		return EvaluationConstants.TYPE;
	}
	
	@Override
	public LearningActivityType getLearningActivityType(LearningActivity activity) throws PortalException {
		return new EvaluationActivityType(activity, learningActivityResultLocalService);
	}
	
	@Override
	public String getPortletId() {
		return EvaluationPortletKeys.EVALUATION;
	}
	
	@Override
	public String getName(Locale locale) {
		System.out.println("*********************Activity: " + resourceBundleLoader.toString());
		ResourceBundle resourceBundle = resourceBundleLoader.loadResourceBundle(locale);
		System.out.println("*********************Resource bundle: " + resourceBundle.getBaseBundleName());
		System.out.println("*********************LanguageUtil: " + LanguageUtil.get(resourceBundle, "learningactivity.evaluation"));
		return LanguageUtil.get(resourceBundle, "learning-activity.evaluation");
	}
	
	@Override
	public String getDescription(Locale locale) {
		ResourceBundle resourceBundle = resourceBundleLoader.loadResourceBundle(locale);
		return LanguageUtil.get(resourceBundle, "learning-activity.evaluation.help-message");
	}
	
	@Override
	public String getURLSpecificContent() {
		return "/evaluation/activity/edit.jsp";
	}
	
	@Override
	public String getIconCssClass() {
		return "activity-evaluation";
	}
	
	@Override
	public boolean isScoreConfigurable() {
		return true;
	}
	
	@Reference(unbind = "-")
	protected void setLearningActivityResultLocalService(LearningActivityResultLocalService learningActivityResultLocalService) {
		this.learningActivityResultLocalService = learningActivityResultLocalService;
	}
	
	protected LearningActivityResultLocalService learningActivityResultLocalService;
	
	@Reference(unbind = "-")
	public void setResourceBundleLoader(ResourceBundleLoader resourceBundleLoader) {

		this.resourceBundleLoader = resourceBundleLoader;
	}

	protected ResourceBundleLoader resourceBundleLoader;
	
}
