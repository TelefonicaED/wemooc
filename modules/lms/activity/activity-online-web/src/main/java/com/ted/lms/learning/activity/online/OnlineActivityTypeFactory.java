package com.ted.lms.learning.activity.online;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.ted.lms.learning.activity.online.web.constants.OnlineConstants;
import com.ted.lms.learning.activity.online.web.constants.OnlinePortletKeys;
import com.ted.lms.model.BaseLearningActivityTypeFactory;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.LearningActivityType;
import com.ted.lms.model.LearningActivityTypeFactory;
import com.ted.lms.service.LearningActivityResultLocalService;
import com.ted.lms.service.LearningActivityTryLocalService;

import java.util.Locale;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
    immediate = true,
    property = {},
    service = LearningActivityTypeFactory.class
)
public class OnlineActivityTypeFactory extends BaseLearningActivityTypeFactory {
	
	public static final double DEFAULT_SCORE = 50;
	
	@Override
	public String getClassName() {
		return OnlineActivityTypeFactory.class.getName();
	}

	@Override
	public long getType() {
		return OnlineConstants.TYPE;
	}
	
	@Override
	public LearningActivityType getLearningActivityType(LearningActivity activity) throws PortalException {
		return new OnlineActivityType(activity, learningActivityResultLocalService);
	}
	
	public OnlineActivityType getOnlineActivityType(LearningActivity activity) throws PortalException {
		return new OnlineActivityType(activity, learningActivityResultLocalService);
	}
	
	@Override
	public String getPortletId() {
		return OnlinePortletKeys.ONLINE;
	}
	
	@Override
	public String getName(Locale locale) {
		ResourceBundle resourceBundle = resourceBundleLoader.loadResourceBundle(locale);
		return LanguageUtil.get(resourceBundle, "learning-activity.online");
	}
	
	@Override
	public String getDescription(Locale locale) {
		ResourceBundle resourceBundle = resourceBundleLoader.loadResourceBundle(locale);
		return LanguageUtil.get(resourceBundle, "learning-activity.online.help-message");
	}
	
	@Override
	public String getURLSpecificContent() {
		return "/edit.jsp";
	}
	
	@Override
	public String getIconCssClass() {
		return "activity-online";
	}
	
	@Override
	public boolean isScoreConfigurable() {
		return true;
	}
	
	@Override
	public double getDefaultScore() {
		return DEFAULT_SCORE;
	}
	
	@Override
	public int getDefaultTries() {
		return OnlineConstants.DEFAULT_TRIES;
	}	
	
	@Override
	public boolean canDeleteTries() {
		return true;
	}
	
	@Override
	public boolean isAutoCorrect() {
		return false;
	}
	
	@Override
	public String getSpecificResultsPage() {
		return "/result.jsp";
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
	private LearningActivityResultLocalService learningActivityResultLocalService;
}
