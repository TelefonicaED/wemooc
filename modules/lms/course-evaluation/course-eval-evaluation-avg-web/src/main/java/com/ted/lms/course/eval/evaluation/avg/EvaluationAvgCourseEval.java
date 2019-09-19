package com.ted.lms.course.eval.evaluation.avg;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.liferay.portal.kernel.util.Validator;
import com.ted.lms.constants.CourseConstants;
import com.ted.lms.learning.activity.evaluation.web.constants.EvaluationConstants;
import com.ted.lms.model.BaseCourseEval;
import com.ted.lms.model.Course;
import com.ted.lms.model.CourseResult;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.LearningActivityResult;
import com.ted.lms.service.CourseResultLocalService;
import com.ted.lms.service.LearningActivityLocalService;
import com.ted.lms.service.LearningActivityResultLocalService;
import java.util.Date;
import java.util.List;

public class EvaluationAvgCourseEval extends BaseCourseEval{

	private static final Log log = LogFactoryUtil.getLog(EvaluationAvgCourseEval.class);
	private static final double NULL_PASS_PUNTUATION = -1;
	
	private double passPuntuation;
	
	public EvaluationAvgCourseEval(Course course, CourseResultLocalService courseResultLocalService,
			LearningActivityResultLocalService learningActivityResultLocalService, LearningActivityLocalService learningActivityLocalService,
			ResourceBundleLoader resourceBundleLoader) {
		super(course);
		passPuntuation = NULL_PASS_PUNTUATION;
		this.courseResultLocalService = courseResultLocalService;
		this.learningActivityResultLocalService = learningActivityResultLocalService;
		this.learningActivityLocalService = learningActivityLocalService;
		this.resourceBundleLoader = resourceBundleLoader;
	}

	@Override
	public CourseResult updateCourseResult(CourseResult courseResult) throws SystemException, PortalException  {
		
		JSONObject courseExtraData = course.getCourseExtraDataJSON();
		
		if(Validator.isNull(courseExtraData)) {
			return null;
		}
		
		double passPuntuation = getPassPuntuation();
		
		List<LearningActivity> evaluations = learningActivityLocalService.getLearningActivitiesByTypeId(EvaluationConstants.TYPE);
		if(evaluations.size()==0){
			return null;
		}
		
		return updateCourseResult(courseResult, passPuntuation, evaluations);	
	}
	
	private CourseResult updateCourseResult(CourseResult courseResult, double passPuntuation, List<LearningActivity> evaluations) throws SystemException {
		double values = 0;
		
		for(LearningActivity evaluation:evaluations){
			
			LearningActivityResult result=learningActivityResultLocalService.getLearningActivityResult(evaluation.getActId(),courseResult.getUserId());
			if(result!=null){
				values += result.getResult();
			}
		}
		
		if(courseResult.getStartDate() == null){
			courseResult.setStartDate(new Date());
		}
		
		double result = values/evaluations.size();
		
		courseResult.setResult(result);
		boolean passed = courseResult.getResult()>=passPuntuation;

		if((courseResult.getPassedDate()==null)||
				(courseResult.getPassed()!=passed)) {
			courseResult.setPassedDate(new Date());
		}
		courseResult.setPassed(passed);

		return courseResult;
	}

	@Override
	public double getPassPuntuation() {
		
		double passPuntuation = 0;
		
		if(this.passPuntuation == NULL_PASS_PUNTUATION) {
			
			JSONObject courseEval = course.getCourseEvalJSON();
			
			if(courseEval != null) {
				passPuntuation = courseEval.getDouble(CourseConstants.JSON_COURSE_EVAL_PASS_PUNTUATION, 0);
			}
			
			this.passPuntuation = passPuntuation;
		}else {
			passPuntuation = this.passPuntuation;
		}
		
		log.debug("passPuntuation: " + passPuntuation);
		return passPuntuation;
	}

	@Override
	public String getClassName() {
		return EvaluationAvgCourseEval.class.getName();
	}

	@Override
	public CourseResult recalculateCourseResult(CourseResult courseResult) throws SystemException, PortalException  {
		
		JSONObject courseExtraData = course.getCourseExtraDataJSON();
		
		if(Validator.isNull(courseExtraData)) {
			return null;
		}
		
		double passPuntuation = getPassPuntuation();
		
		List<LearningActivity> evaluations = learningActivityLocalService.getLearningActivitiesByTypeId(EvaluationConstants.TYPE);
		if(evaluations.size()==0){
			return null;
		}
		
		List<LearningActivityResult> lresult = learningActivityResultLocalService.getRequiredLearningActivityResults(course.getGroupCreatedId(), courseResult.getUserId());

		if(courseResult.getStartDate() != null || (courseResult.getStartDate() != null &&  lresult.size() > 0)){
			courseResult = updateCourseResult(courseResult, passPuntuation, evaluations);	
			courseResult = courseResultLocalService.updateCourseResult(courseResult);
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
	private LearningActivityLocalService learningActivityLocalService;
	protected ResourceBundleLoader resourceBundleLoader;
	
	/**
	 * Este método devuelve el json correspondiente al método de evaluación del curso
	 * @return JSONObject
	 * @throws PortalException
	 * @throws SystemException
	 */
	public JSONObject getEvaluationModel() throws PortalException, SystemException {
		
		JSONObject courseEvalData = course.getCourseEvalJSON();
		
		if(courseEvalData == null) {
			courseEvalData = JSONFactoryUtil.createJSONObject();
		}
		
		return courseEvalData;
	}
	
	/**
	 * Añade el json de entrada al del método de evaluación del curso
	 * @param model
	 * @throws PortalException
	 * @throws SystemException
	 */
	public void setEvaluationModel(JSONObject model) throws PortalException, SystemException {
		
		JSONObject courseExtraData = course.getCourseExtraDataJSON();
		
		JSONObject courseEvalData = course.getCourseEvalJSON();
		
		if(courseEvalData == null) {
			courseEvalData = JSONFactoryUtil.createJSONObject();
		}
		
		courseEvalData.put("passPuntuation", model.getLong("passPuntuation"));
		
		
		JSONArray evaluations = model.getJSONArray("evaluations");
		if(evaluations!=null){		
			courseEvalData.put("evaluations", evaluations);
		}
		
		courseExtraData.put(CourseConstants.JSON_COURSE_EVAL, courseEvalData);
		
		course.setCourseExtraData(courseExtraData.toJSONString());
		
	}
}
