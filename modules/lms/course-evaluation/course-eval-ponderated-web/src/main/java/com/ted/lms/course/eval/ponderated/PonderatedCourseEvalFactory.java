package com.ted.lms.course.eval.ponderated;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.ted.lms.model.BaseCourseEvalFactory;
import com.ted.lms.model.Course;
import com.ted.lms.model.CourseEval;
import com.ted.lms.model.CourseEvalFactory;
import java.util.Locale;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	    immediate = true,
	    property = {},
	    service = CourseEvalFactory.class
	)
public class PonderatedCourseEvalFactory extends BaseCourseEvalFactory{
	
	public static final long TYPE = 3;
	
	@Override
	public CourseEval getCourseEval(Course course, ServiceContext serviceContext) throws PortalException {	
		return new PonderatedCourseEval(course, serviceContext, courseResultLocalService,
				learningActivityResultLocalService, learningActivityTryLocalService, learningActivityLocalService);
	}

	@Override
	public String getIconCssClass() {
		return "course-eval-ponderated";
	}

	@Override
	public String getTitle(Locale locale) {
		ResourceBundle resourceBundle = resourceBundleLoader.loadResourceBundle(locale);
		return LanguageUtil.get(resourceBundle, "course-eval.ponderated.title");
	}

	@Override
	public String getClassName() {
		return PonderatedCourseEvalFactory.class.getName();
	}

	@Override
	public long getType() {
		return TYPE;
	}
	
	@Reference(
		target = "(bundle.symbolic.name=com.ted.lms.course.eval.ponderated.web)", unbind = "-"
	)
	public void setResourceBundleLoader(ResourceBundleLoader resourceBundleLoader) {

		this.resourceBundleLoader = resourceBundleLoader;
	}

	protected ResourceBundleLoader resourceBundleLoader;

}
