package com.ted.lms.module.eval.complete.activities;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.ted.lms.model.BaseModuleEvalFactory;
import com.ted.lms.model.Module;
import com.ted.lms.model.ModuleEval;
import com.ted.lms.model.ModuleEvalFactory;
import com.ted.lms.service.CourseResultLocalService;
import com.ted.lms.service.LearningActivityLocalService;
import com.ted.lms.service.LearningActivityResultLocalService;
import com.ted.lms.service.LearningActivityTryLocalService;
import com.ted.lms.service.ModuleLocalService;
import com.ted.lms.service.ModuleResultLocalService;

import java.util.Locale;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
    immediate = true,
    property = {},
    service = ModuleEvalFactory.class
)
public class CompleteActivitiesModuleEvalFactory extends BaseModuleEvalFactory{
	
	private static final Log log = LogFactoryUtil.getLog(CompleteActivitiesModuleEvalFactory.class);
	public static final long TYPE = 0;
	
	@Override
	public ModuleEval getModuleEval(Module module) throws PortalException {	
		log.debug("getModuleEval");
		return new CompleteActivitiesModuleEval(module, courseResultLocalService,
				learningActivityResultLocalService, learningActivityLocalService, 
				moduleResultLocalService);
	}

	@Override
	public String getIconCssClass() {
		return "module-eval-complete-activities";
	}

	@Override
	public String getTitle(Locale locale) {
		ResourceBundle resourceBundle = resourceBundleLoader.loadResourceBundle(locale);
		return LanguageUtil.get(resourceBundle, "module-eval.complete-activites.title");
	}

	@Override
	public String getClassName() {
		return CompleteActivitiesModuleEvalFactory.class.getName();
	}

	@Override
	public long getType() {
		return TYPE;
	}
	
	@Reference(
		unbind = "-"
	)
	public void setResourceBundleLoader(ResourceBundleLoader resourceBundleLoader) {

		this.resourceBundleLoader = resourceBundleLoader;
	}

	protected ResourceBundleLoader resourceBundleLoader;
	
	
	@Reference(unbind = "-")
	protected void setModuleResultLocalService(ModuleResultLocalService moduleResultLocalService) {
		this.moduleResultLocalService = moduleResultLocalService;
	}
	
	protected ModuleResultLocalService moduleResultLocalService;
	
	@Reference(unbind = "-")
	protected void setCourseResultLocalService(CourseResultLocalService courseResultLocalService) {
		this.courseResultLocalService = courseResultLocalService;
	}
	
	protected CourseResultLocalService courseResultLocalService;
	
	@Reference(unbind = "-")
	protected void setLearningActivityResultLocalService(LearningActivityResultLocalService learningActivityResultLocalService) {
		this.learningActivityResultLocalService = learningActivityResultLocalService;
	}
	
	protected LearningActivityResultLocalService learningActivityResultLocalService;
	
	@Reference(unbind = "-")
	protected void setLearningActivityTryLocalService(LearningActivityTryLocalService learningActivityTryLocalService) {
		this.learningActivityTryLocalService = learningActivityTryLocalService;
	}
	
	protected LearningActivityTryLocalService learningActivityTryLocalService;
	
	@Reference(unbind = "-")
	protected void setLearningActivityLocalService(LearningActivityLocalService learningActivityLocalService) {
		this.learningActivityLocalService = learningActivityLocalService;
	}
	
	protected LearningActivityLocalService learningActivityLocalService;
	
	@Reference(unbind = "-")
	protected void setModuleLocalService(ModuleLocalService moduleLocalService) {
		this.moduleLocalService = moduleLocalService;
	}
	
	protected ModuleLocalService moduleLocalService;
}
