package com.ted.lms.course.eval.ponderated;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.ted.lms.constants.CourseConstants;
import com.ted.lms.course.eval.ponderated.constants.PonderatedConstants;
import com.ted.lms.model.BaseCourseEval;
import com.ted.lms.model.Course;
import com.ted.lms.model.CourseResult;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.LearningActivityResult;
import com.ted.lms.service.CourseResultLocalService;
import com.ted.lms.service.LearningActivityLocalService;
import com.ted.lms.service.LearningActivityResultLocalService;
import com.ted.lms.service.LearningActivityTryLocalService;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PonderatedCourseEval extends BaseCourseEval{

	private static final Log log = LogFactoryUtil.getLog(PonderatedCourseEval.class);
	
	public PonderatedCourseEval(Course course, CourseResultLocalService courseResultLocalService,
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
		
		return updateCourseResult(courseResult, courseResult.getUserId(), lresult);
	}

	@Override
	public double getPassPuntuation() {
		double score=0;
		
		JSONObject courseEvalData = course.getCourseEvalJSON();
		
		if(courseEvalData != null) {
			score = courseEvalData.getDouble(CourseConstants.JSON_COURSE_EVAL_PASS_PUNTUATION, 0);
		}
		
		return score;
	}

	@Override
	public String getClassName() {
		return PonderatedCourseEval.class.getName();
	}

	@Override
	public CourseResult recalculateCourseResult(CourseResult courseResult) throws SystemException {
		// Se obtiene el courseresult del usuario en dicho course.

		List<LearningActivityResult> lresult = learningActivityResultLocalService.getRequiredLearningActivityResults(course.getGroupCreatedId(), courseResult.getUserId());

		if(courseResult.getStartDate() != null || (courseResult.getStartDate() != null &&  lresult.size() > 0)){
			courseResult = updateCourseResult(courseResult, courseResult.getUserId(), lresult);
			courseResult = courseResultLocalService.updateCourseResult(courseResult);
		}
		return courseResult;	
	}
	
	public CourseResult updateCourseResult(CourseResult courseResult, long userId, List<LearningActivityResult> lresult) {
		
		try {
			java.util.Map<Long,Long> weights=getActivitiesWeight();
			double score = getPassPuntuation();

			boolean passed=true;
			double result=0;
			long weight=0;
			List<LearningActivity> learningActivities=learningActivityLocalService.getRequiredLearningActivitiesOfGroup(course.getGroupCreatedId());
			
			//Guardo los resultados de las actividades del usuario en el curso en un hashmap para no tener que acceder a bbdd por cada uno de ellos
			HashMap<Long, LearningActivityResult> results = new HashMap<Long, LearningActivityResult>();
			for(LearningActivityResult ar:lresult){
				results.put(ar.getActId(), ar);
			}
			
			boolean isFailed=false;
			LearningActivityResult learningActivityResult = null;
			boolean hasTries = false;
			for(LearningActivity act:learningActivities){

				if(!weights.containsKey(act.getActId())){//Solo se tienen en cuenta las actividades obligatorias que tienen peso definido
					continue;
				}
				
				if(results.containsKey(act.getActId())){
					learningActivityResult = results.get(act.getActId());
				}else{
					learningActivityResult = null;
				}
				
				if(learningActivityResult != null){					
					if(learningActivityResult.getEndDate()!=null){						
						if(!learningActivityResult.isPassed()){
							passed=false;
							if(act.getTries() > 0){
								long  userTries = learningActivityTryLocalService.getLearningActivityTriesCount(act.getActId(), userId);
								if(userTries >= act.getTries()){
									isFailed=true;
								}else{
									hasTries = true;
								}
							}else{
								hasTries = true;
							}
						}
						result=result+(learningActivityResult.getResult()*weights.get(act.getActId()));

					}else{
						passed=false;
						hasTries = true;
					}
				}else{
					passed=false;
					hasTries = true;
				}
				weight+=weights.get(act.getActId());
			}

			
			
			if(result>0&&weight>0){
				result=result/weight;
			}

			if(result<score){
				passed=false;
			}
						

			if(!hasTries && !passed){
				isFailed = true;
			}
			
			// Si el usuario se ha marcado como isFailed es porque lo tiene suspenso. Se le asigna un passed a false y se marca la fecha de finalización del curso (passedDate).
            courseResult.setPassed(passed && !isFailed);
            // Se almacena el result del resultado del usuario en el curso.
            courseResult.setResult(result);
            if((passed || isFailed) && courseResult.getPassedDate() == null) {
                   courseResult.setPassedDate(new Date());
            }
            return courseResultLocalService.updateCourseResult(courseResult);	
			
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
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
	
	public Map<Long, Long> getActivitiesWeight() throws PortalException, SystemException {
		Map<Long, Long> result =new HashMap<Long, Long>();
		
		JSONObject courseEvalData = course.getCourseEvalJSON();
		
		if(courseEvalData != null) {
			JSONArray reqElements = courseEvalData.getJSONArray(PonderatedConstants.JSON_WEIGHTS);
			for(int i=0; i < reqElements.length(); i++){
				long actId= reqElements.getJSONObject(i).getLong(PonderatedConstants.JSON_ACT_ID);
				long ponderation=reqElements.getJSONObject(i).getLong(PonderatedConstants.JSON_WEIGHT_PONDERATION);
				
				LearningActivity larn = learningActivityLocalService.fetchLearningActivity(actId);
				if(larn!=null&& larn.getGroupId()==course.getGroupCreatedId() && larn.getRequired()){
					result.put(actId,ponderation);
				}
			}
		}
		return result;
	}
	
	public List<Long> getRequiredActivities() throws PortalException, SystemException{
		List<Long> result = new ArrayList<Long>();
		
		JSONObject courseEvalData = course.getCourseEvalJSON();
		
		if(courseEvalData != null) {
			JSONArray reqElements = courseEvalData.getJSONArray(PonderatedConstants.JSON_REQUIRED);
			LearningActivity larn = null;
			long actId = 0;
			for(int i=0; i < reqElements.length(); i++){
				actId = reqElements.getJSONObject(i).getLong(PonderatedConstants.JSON_ACT_ID);
				larn = learningActivityLocalService.fetchLearningActivity(actId);
				if(larn!=null && larn.getGroupId()==course.getGroupCreatedId() && larn.isRequired()){
					result.add(actId);
				}
			}
		}
		return result;
	}

	private CourseResultLocalService courseResultLocalService;
	private LearningActivityResultLocalService learningActivityResultLocalService;
	private LearningActivityTryLocalService learningActivityTryLocalService;
	private LearningActivityLocalService learningActivityLocalService;

}
