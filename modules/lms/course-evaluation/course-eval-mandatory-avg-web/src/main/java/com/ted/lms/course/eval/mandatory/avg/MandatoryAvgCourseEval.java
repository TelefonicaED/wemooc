package com.ted.lms.course.eval.mandatory.avg;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.ted.lms.model.BaseCourseEval;
import com.ted.lms.model.Course;
import com.ted.lms.model.CourseResult;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.LearningActivityResult;
import com.ted.lms.service.CourseResultLocalService;
import com.ted.lms.service.LearningActivityLocalService;
import com.ted.lms.service.LearningActivityResultLocalService;
import com.ted.lms.service.LearningActivityTryLocalService;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MandatoryAvgCourseEval extends BaseCourseEval{

	private static final Log log = LogFactoryUtil.getLog(MandatoryAvgCourseEval.class);
	
	public MandatoryAvgCourseEval(Course course, CourseResultLocalService courseResultLocalService,
			LearningActivityResultLocalService learningActivityResultLocalService, LearningActivityTryLocalService learningActivityTryLocalService,
			LearningActivityLocalService learningActivityLocalService) {
		super(course);
		this.courseResultLocalService = courseResultLocalService;
		this.learningActivityResultLocalService = learningActivityResultLocalService;
		this.learningActivityTryLocalService = learningActivityTryLocalService;
		this.learningActivityLocalService = learningActivityLocalService;
	}

	@Override
	public CourseResult updateCourseResult(CourseResult courseResult) throws SystemException {

		if(courseResult.getStartDate() == null){
			courseResult.setStartDate(new Date());
		}
		
		List<LearningActivityResult> lresult = learningActivityResultLocalService.getRequiredLearningActivityResults(course.getGroupCreatedId(), courseResult.getUserId());
		
		return updateCourseResult(courseResult, lresult);		
	}

	@Override
	public double getPassPuntuation() {
		throw new RuntimeException();
	}

	@Override
	public String getClassName() {
		return MandatoryAvgCourseEval.class.getName();
	}

	@Override
	public CourseResult recalculateCourseResult(CourseResult courseResult) throws SystemException {
		// Se obtiene el courseresult del usuario en dicho course.

		List<LearningActivityResult> lresult = learningActivityResultLocalService.getRequiredLearningActivityResults(course.getGroupCreatedId(), courseResult.getUserId());

		if(courseResult.getStartDate() != null || (courseResult.getStartDate() != null &&  lresult.size() > 0)){
			courseResult = updateCourseResult(courseResult, lresult);
			courseResult  = courseResultLocalService.updateCourseResult(courseResult);
		}
		return courseResult;	
	}
	
	private CourseResult updateCourseResult(CourseResult courseResult, List<LearningActivityResult> lresult) {
		
		boolean passed=true;
		long result=0;	
		
		List<LearningActivity> learningActivities=learningActivityLocalService.getRequiredLearningActivitiesOfGroup(course.getGroupCreatedId());
		
		//Guardo los resultados de las actividades del usuario en el curso en un hashmap para no tener que acceder a bbdd por cada uno de ellos
		HashMap<Long, LearningActivityResult> results = new HashMap<Long, LearningActivityResult>();
		for(LearningActivityResult ar:lresult){
			results.put(ar.getActId(), ar);
		}
		
		boolean isFailed=false;
		LearningActivityResult learningActivityResult = null;
		
		for(LearningActivity activity:learningActivities){
			
			if(results.containsKey(activity.getActId())){
				learningActivityResult = results.get(activity.getActId());
			}else{
				learningActivityResult = null;
			}
			
			if(learningActivityResult != null){					
				if(learningActivityResult.getEndDate()!=null){
					if(!learningActivityResult.isPassed()){
						passed = false;
						if(activity.getTries() > 0){
							long  userTries = learningActivityTryLocalService.getLearningActivityTriesCount(activity.getActId(), courseResult.getUserId());
							if(userTries >= activity.getTries()){
								isFailed=true;
							}
						}
					}
				}else{
					passed=false;					
				}
				result+=learningActivityResult.getResult();
			}else{
				passed=false;
			}
		}

		if(learningActivities.size()>0){
			result=result/learningActivities.size();
		}
		
		// Si el usuario se ha marcado como isFailed es porque lo tiene suspenso. Se le asigna un passed a false y se marca la fecha de finalización del curso (passedDate).
        courseResult.setPassed(passed && !isFailed);
        // Se almacena el result del resultado del usuario en el curso.
        courseResult.setResult(result);
        if((passed || isFailed) && courseResult.getPassedDate() == null) {
               courseResult.setPassedDate(new Date());
        }
       return courseResult;
	}
	
	@Override
	public void onOpenCourse() {
		List<CourseResult> listCourseResults = courseResultLocalService.getCourseResults(course.getCourseId(), false);
		for(CourseResult courseResult: listCourseResults){
			courseResult.setPassedDate(null);
			courseResultLocalService.updateCourseResult(courseResult);
		}
	}
	
	@Override
	public void onCloseCourse() {
		List<CourseResult> listCourseResults = courseResultLocalService.getCourseResultsNotFinished(course.getCourseId());
		for(CourseResult courseResult: listCourseResults) {
			courseResult.setPassedDate(course.getModifiedDate());
			courseResultLocalService.updateCourseResult(courseResult);
		}
	}

	private CourseResultLocalService courseResultLocalService;
	private LearningActivityResultLocalService learningActivityResultLocalService;
	private LearningActivityTryLocalService learningActivityTryLocalService;
	private LearningActivityLocalService learningActivityLocalService;

}