package com.ted.lms.model;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.ted.lms.registry.CourseEvalFactoryRegistryUtil;
import com.ted.lms.service.CourseResultLocalServiceUtil;

import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;

/**
 * Base para los métodos de evaluación del curso
 * @author Virginia Martín Agudo
 *
 */
public abstract class BaseCourseEval implements CourseEval {
	
	protected Course course;

	public BaseCourseEval(Course course) {
		this.course = course;
	}
	
	public void setCourse(Course course) {
		this.course = course;
	}
	
	@Override
	public CourseEvalFactory getCourseEvalFactory(){
		if (courseEvalFactory != null) {
			return courseEvalFactory;
		}

		courseEvalFactory =
			(CourseEvalFactory)
			CourseEvalFactoryRegistryUtil.
					getCourseEvalFactoryByClassName(getClassName());

		return courseEvalFactory;
	}
	
	private CourseEvalFactory courseEvalFactory;
	
	public boolean updateCourse() throws SystemException {
		List<CourseResult> courseResults = CourseResultLocalServiceUtil.getCourseResults(course.getCourseId());
		for(CourseResult courseResult: courseResults) {
			updateCourseResult(courseResult);
		}
		
		return true;
	}
	
	public boolean recalculateCourse() throws SystemException {
		List<CourseResult> courseResults = CourseResultLocalServiceUtil.getCourseResults(course.getCourseId());
		for(CourseResult courseResult: courseResults) {
			recalculateCourseResult(courseResult);
		}
		
		return true;
	}

	public void setExtraContent(ActionRequest actionRequest) throws PortalException {	
	}
	
	@Override
	public void onOpenCourse(){
		
	}
	
	@Override
	public void onCloseCourse(){
		
	}
	
	@Override
	public void onDeleteCourse() {
		
	}
	
	@Override
	public void copyCourse(Course oldCourse, Map<Long,Long> modulesRelation, Map<Long, Long> activitiesRelation) {
		
	}
}
