package com.ted.lms.course.eval.mandatory.avg;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.ted.lms.model.BaseCourseEvalFactory;
import com.ted.lms.model.Course;
import com.ted.lms.model.CourseEval;
import com.ted.lms.model.CourseEvalFactory;
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
	    service = CourseEvalFactory.class
	)
public class MandatoryAvgCourseEvalFactory extends BaseCourseEvalFactory{
	
	public static final long TYPE = 2;
	
	@Override
	public CourseEval getCourseEval(Course course, ServiceContext serviceContext) throws PortalException {	
		return new MandatoryAvgCourseEval(course, serviceContext, courseResultLocalService, learningActivityResultLocalService, 
				learningActivityTryLocalService, learningActivityLocalService);
	}

	@Override
	public String getIconCssClass() {
		return "course-eval-mandatory-avg";
	}

	@Override
	public String getTitle(Locale locale) {
		ResourceBundle resourceBundle = resourceBundleLoader.loadResourceBundle(locale);
		return LanguageUtil.get(resourceBundle, "course-eval.mandatory-avg.title");
	}

	@Override
	public String getClassName() {
		return MandatoryAvgCourseEvalFactory.class.getName();
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
	protected void setModuleResultLocalService(ModuleResultLocalService moduleResultLocalService) {
		this.moduleResultLocalService = moduleResultLocalService;
	}
	
	protected ModuleResultLocalService moduleResultLocalService;
	
	@Reference(unbind = "-")
	protected void setModuleLocalService(ModuleLocalService moduleLocalService) {
		this.moduleLocalService = moduleLocalService;
	}
	
	protected ModuleLocalService moduleLocalService;

}
