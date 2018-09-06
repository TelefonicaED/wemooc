package com.ted.lms.course.eval.evaluation.avg;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.liferay.portal.kernel.util.Validator;
import com.ted.lms.course.eval.evaluation.avg.constants.EvaluationAvgPropsKeys;
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
public class EvaluationAvgCourseEvalFactory extends BaseCourseEvalFactory{
	
	public static final long TYPE = 1;
	
	@Override
	public CourseEval getCourseEval(Course course, ServiceContext serviceContext) throws PortalException {	
		return new EvaluationAvgCourseEval(course, serviceContext, courseResultLocalService,
				learningActivityResultLocalService, learningActivityLocalService,
				resourceBundleLoader);
	}

	@Override
	public String getIconCssClass() {
		return "course-eval-evaluation-avg";
	}

	@Override
	public String getTitle(Locale locale) {
		ResourceBundle resourceBundle = resourceBundleLoader.loadResourceBundle(locale);
		return LanguageUtil.get(resourceBundle, "course-eval.evaluation-avg.title");
	}

	@Override
	public String getClassName() {
		return EvaluationAvgCourseEvalFactory.class.getName();
	}

	@Override
	public long getType() {
		return TYPE;
	}
	
	@Override
	public String getURLSpecificContent(){
		return (Validator.isNumber(PropsUtil.get(EvaluationAvgPropsKeys.DEFAULT_EVALUATIONS)))?"/evaluation-avg/course-eval/edit.jsp":null;
	}
	
	@Override
	public boolean getNeedPassPuntuation() {
		return true;
	}
	
	@Reference(
		target = "(bundle.symbolic.name=com.ted.lms.course.eval.evaluation.avg.web)", unbind = "-"
	)
	public void setResourceBundleLoader(ResourceBundleLoader resourceBundleLoader) {

		this.resourceBundleLoader = resourceBundleLoader;
	}

	protected ResourceBundleLoader resourceBundleLoader;

}
