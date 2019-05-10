package com.ted.lms.learning.activity.survey.web.activity;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.ted.lms.learning.activity.survey.web.constants.SurveyConstants;
import com.ted.lms.learning.activity.survey.web.constants.SurveyPortletKeys;
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
public class SurveyActivityTypeFactory extends BaseLearningActivityTypeFactory {
	
	@Override
	public String getClassName() {
		return SurveyActivityTypeFactory.class.getName();
	}

	@Override
	public long getType() {
		return SurveyConstants.TYPE;
	}
	
	@Override
	public LearningActivityType getLearningActivityType(LearningActivity activity) throws PortalException {
		return new SurveyActivityType(activity);
	}
	
	@Override
	public String getPortletId() {
		return SurveyPortletKeys.SURVEY;
	}
	
	@Override
	public String getName(Locale locale) {
		ResourceBundle resourceBundle = resourceBundleLoader.loadResourceBundle(locale);
		return LanguageUtil.get(resourceBundle, "learning-activity.survey");
	}
	
	@Override
	public String getDescription(Locale locale) {
		ResourceBundle resourceBundle = resourceBundleLoader.loadResourceBundle(locale);
		return LanguageUtil.get(resourceBundle, "learning-activity.survey.help-message");
	}
	
	@Override
	public String getURLSpecificContent() {
		return "/edit.jsp";
	}
	
	@Override
	public String getIconCssClass() {
		return "activity-survey";
	}
	
	@Override
	public int getDefaultTries() {
		return SurveyConstants.DEFAULT_TRIES;
	}
	
	@Override
	public boolean canDeleteTries() {
		return true;
	}
	
	@Reference(unbind = "-")
	public void setResourceBundleLoader(ResourceBundleLoader resourceBundleLoader) {

		this.resourceBundleLoader = resourceBundleLoader;
	}

	protected ResourceBundleLoader resourceBundleLoader;
}
