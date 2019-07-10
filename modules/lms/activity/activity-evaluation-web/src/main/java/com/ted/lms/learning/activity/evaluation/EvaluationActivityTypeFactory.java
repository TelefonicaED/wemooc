package com.ted.lms.learning.activity.evaluation;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.ted.lms.learning.activity.evaluation.web.constants.EvaluationConstants;
import com.ted.lms.learning.activity.evaluation.web.constants.EvaluationPortletKeys;
import com.ted.lms.model.BaseLearningActivityTypeFactory;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.LearningActivityType;
import com.ted.lms.model.LearningActivityTypeFactory;
import com.ted.lms.service.LearningActivityLocalService;
import com.ted.lms.service.LearningActivityResultLocalService;
import com.ted.lms.service.LearningActivityService;
import com.ted.lms.service.LearningActivityTryLocalService;
import com.ted.lms.service.ModuleService;

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
	
	private static final Log log = LogFactoryUtil.getLog(EvaluationActivityTypeFactory.class);
	private static final int DEFAULT_TRIES = 1;
	
	@Override
	public String getClassName() {
		return EvaluationActivityTypeFactory.class.getName();
	}

	@Override
	public long getType() {
		return EvaluationConstants.TYPE;
	}
	
	public EvaluationActivityType getEvaluationActivityType(LearningActivity activity) throws PortalException {
		return new EvaluationActivityType(activity, learningActivityResultLocalService, moduleService, activityService, learningActivityTryLocalService, learningActivityLocalService);
	}
	
	@Override
	public LearningActivityType getLearningActivityType(LearningActivity activity) throws PortalException {
		return new EvaluationActivityType(activity, learningActivityResultLocalService, moduleService, activityService, learningActivityTryLocalService, learningActivityLocalService);
	}
	
	@Override
	public String getPortletId() {
		return EvaluationPortletKeys.EVALUATION;
	}
	
	@Override
	public String getName(Locale locale) {
		log.debug("*********************Activity: " + resourceBundleLoader.toString());
		ResourceBundle resourceBundle = resourceBundleLoader.loadResourceBundle(locale);
		log.debug("*********************Resource bundle: " + resourceBundle.getBaseBundleName());
		log.debug("*********************LanguageUtil: " + LanguageUtil.get(resourceBundle, "learningactivity.evaluation"));
		return LanguageUtil.get(resourceBundle, "learning-activity.evaluation");
	}
	
	@Override
	public String getDescription(Locale locale) {
		ResourceBundle resourceBundle = resourceBundleLoader.loadResourceBundle(locale);
		return LanguageUtil.get(resourceBundle, "learning-activity.evaluation.help-message");
	}
	
	@Override
	public String getIconCssClass() {
		return "activity-evaluation";
	}
	
	@Override
	public boolean isScoreConfigurable() {
		return true;
	}
	
	@Override
	public String getURLSpecificContent() {
		return "/edit.jsp";
	}
	
	@Override
	public int getDefaultTries() {
		return DEFAULT_TRIES;
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
	protected void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}
	protected ModuleService moduleService;
	
	@Reference(unbind = "-")
	protected void setLearningActivityService(LearningActivityService activityService) {
		this.activityService = activityService;
	}
	private LearningActivityService activityService;
	
	@Reference(unbind = "-")
	protected void setLearningActivityTryLocalService(LearningActivityTryLocalService learningActivityTryLocalService) {
		this.learningActivityTryLocalService = learningActivityTryLocalService;
	}
	private LearningActivityTryLocalService learningActivityTryLocalService;
	
	@Reference(unbind = "-")
	protected void setLearningActivityLocalService(LearningActivityLocalService learningActivityLocalService) {
		this.learningActivityLocalService = learningActivityLocalService;
	}
	private LearningActivityLocalService learningActivityLocalService;
	
}
