package com.ted.lms.course.eval.evaluation.avg;

import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.ted.lms.constants.CourseConstants;
import com.ted.lms.course.eval.evaluation.avg.constants.EvaluationAvgPropsKeys;
import com.ted.lms.model.BaseCourseEval;
import com.ted.lms.model.Course;
import com.ted.lms.model.CourseResult;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.LearningActivityResult;
import com.ted.lms.service.CourseResultLocalService;
import com.ted.lms.service.LearningActivityLocalService;
import com.ted.lms.service.LearningActivityResultLocalService;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.portlet.ActionRequest;
import javax.portlet.PortletResponse;

public class EvaluationAvgCourseEval extends BaseCourseEval{

	private static final Log log = LogFactoryUtil.getLog(EvaluationAvgCourseEval.class);
	private static final double NULL_PASS_PUNTUATION = -1;
	
	private double passPuntuation;
	
	public EvaluationAvgCourseEval(Course course, ServiceContext serviceContext, CourseResultLocalService courseResultLocalService,
			LearningActivityResultLocalService learningActivityResultLocalService, LearningActivityLocalService learningActivityLocalService,
			ResourceBundleLoader resourceBundleLoader) {
		super(course, serviceContext);
		passPuntuation = NULL_PASS_PUNTUATION;
		this.courseResultLocalService = courseResultLocalService;
		this.learningActivityResultLocalService = learningActivityResultLocalService;
		this.learningActivityLocalService = learningActivityLocalService;
		this.resourceBundleLoader = resourceBundleLoader;
	}

	@Override
	public CourseResult updateCourseResult(CourseResult courseResult) throws SystemException {
		
		JSONObject courseExtraData = course.getCourseExtraDataJSON();
		
		if(Validator.isNull(courseExtraData)) {
			return null;
		}
		
		double passPuntuation = getPassPuntuation();
		
		Map<Long,Long> evaluations=getEvaluations();
		if(evaluations.size()==0){
			return null;
		}
		
		return updateCourseResult(courseResult, passPuntuation, evaluations);	
	}
	
	private CourseResult updateCourseResult(CourseResult courseResult, double passPuntuation, Map<Long, Long> evaluations) throws SystemException {
		double[] values=new double[evaluations.size()];
		double[] weights=new double[evaluations.size()];
		int i=0;
		
		for(Map.Entry<Long,Long> evaluation:evaluations.entrySet()){
			
			LearningActivityResult result=learningActivityResultLocalService.getLearningActivityResult(evaluation.getKey(),courseResult.getUserId());
			if(result!=null){
				values[i]=result.getResult();
			}

			weights[i++]=evaluation.getValue();
		}
		
		if(courseResult.getStartDate() == null){
			courseResult.setStartDate(new Date());
		}
		
		courseResult.setResult((long) calculateMean(values,weights));
		boolean passed = courseResult.getResult()>=passPuntuation;

		if((courseResult.getPassedDate()==null)||
				(courseResult.getPassed()!=passed)) {
			courseResult.setPassedDate(new Date());
		}
		courseResult.setPassed(passed);

		return courseResultLocalService.updateCourseResult(courseResult);
	}
	
	private double calculateMean(double[] values, double[] weights) {
		int i;
		double sumWeight=0;
		for (i = 0; i < weights.length; i++) {
			sumWeight+=weights[i];
		}
		
		double mean=0;
		for (i = 0; i < values.length; i++) {
			mean+=weights[i]*values[i];
		}
		mean/=sumWeight;
		
		//Correction factor
		double correction=0;
		for (i = 0; i < values.length; i++) {
			correction += weights[i] * (values[i] - mean);
		}
		
		return mean + (correction/sumWeight);
	}
	
	private Map<Long,Long> getEvaluations() throws SystemException{

		Map<Long,Long> evaluations = new HashMap<Long,Long>();
		JSONObject courseEval = course.getCourseEvalJSON();
		
		if(courseEval != null) {
			
			JSONArray evaluationsArray = courseEval.getJSONArray("evaluations");
			
			if(evaluationsArray  != null && evaluationsArray.length() > 0) {
				
				for (int i = 0; i < evaluationsArray.length(); i++) {
					long id = evaluationsArray.getJSONObject(i).getLong("id");
					long weight = evaluationsArray.getJSONObject(i).getLong("weight");
					if((id!=0)&&(weight!=0)){
						evaluations.put(id, weight);
					}
				}
				
				List<Long> actIdsInDatabase = 
						learningActivityLocalService.dynamicQuery(
						DynamicQueryFactoryUtil.forClass(LearningActivity.class)
						.add(PropertyFactoryUtil.forName("actId").in((Collection<Object>)(Collection<?>)evaluations.keySet()))
						.setProjection(ProjectionFactoryUtil.property("actId")));
				
				Iterator<Map.Entry<Long,Long>> evaluationsIterator = evaluations.entrySet().iterator();
				while (evaluationsIterator.hasNext()) {
					if(!actIdsInDatabase.contains(evaluationsIterator.next().getKey())){
						evaluationsIterator.remove();
				    }
				}
			}
		}
		
		return evaluations;
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
	public CourseResult recalculateCourseResult(CourseResult courseResult) throws SystemException {
		
		JSONObject courseExtraData = course.getCourseExtraDataJSON();
		
		if(Validator.isNull(courseExtraData)) {
			return null;
		}
		
		double passPuntuation = getPassPuntuation();
		
		Map<Long,Long> evaluations=getEvaluations();
		if(evaluations.size()==0){
			return null;
		}
		
		List<LearningActivityResult> lresult = learningActivityResultLocalService.getRequiredLearningActivityResults(course.getGroupCreatedId(), userId);

		if(courseResult.getStartDate() != null || (courseResult.getStartDate() != null &&  lresult.size() > 0)){
			courseResult = updateCourseResult(courseResult, passPuntuation, evaluations, userId);	
			courseResult = courseResultLocalService.updateCourseResult(courseResult);
		}
		
		return courseResult;
	}
	
	@Override
	public void setExtraContent(ActionRequest actionRequest) throws PortalException {
		
		String actionId = ParamUtil.getString(actionRequest, Constants.CMD);
		
		if((Validator.isNumber(PropsUtil.get(EvaluationAvgPropsKeys.DEFAULT_EVALUATIONS)))&&(Constants.ADD.equals(actionId))) {
			long numOfEvaluations = ParamUtil.getLong(actionRequest, "numOfEvaluations",GetterUtil.getLong(PropsUtil.get(EvaluationAvgPropsKeys.DEFAULT_EVALUATIONS), -1));
			
			ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
			ResourceBundle resourceBundle = resourceBundleLoader.loadResourceBundle(themeDisplay.getLocale());
			
			for(int currentEvaluation=1;currentEvaluation<=numOfEvaluations;currentEvaluation++) {
				
				Map<Locale,String> evaluationTitle = new HashMap<Locale, String>(1);
				evaluationTitle.put(themeDisplay.getLocale(), LanguageUtil.format(resourceBundle, "course-eval.evaluation-avg.evaluation-number", new Object[]{currentEvaluation}));
	
			/*	LearningActivityLocalServiceUtil.addLearningActivity(course.getUserId(), course.getGroupCreatedId(), WorkflowConstants.STATUS_APPROVED, 
						evaluationTitle, evaluationTitle, EvaluationLearningActivityTypeFactory.getType(), null, null, 0, 0, 0, 0, null, null, null, 0, 0, serviceContext);*/
			}	
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
		List<CourseResult> listCourseResults = courseResultLocalService.getFailedCourseResults(course.getCourseId());
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
