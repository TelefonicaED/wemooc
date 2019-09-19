package com.ted.lms.learning.activity.evaluation;

import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.xml.Element;
import com.ted.lms.constants.LearningActivityConstants;
import com.ted.lms.learning.activity.evaluation.web.constants.EvaluationConstants;
import com.ted.lms.model.BaseLearningActivityType;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.LearningActivityResult;
import com.ted.lms.model.LearningActivityTry;
import com.ted.lms.model.Module;
import com.ted.lms.service.LearningActivityLocalService;
import com.ted.lms.service.LearningActivityResultLocalService;
import com.ted.lms.service.LearningActivityService;
import com.ted.lms.service.LearningActivityTryLocalService;
import com.ted.lms.service.ModuleService;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.portlet.ActionRequest;

public class EvaluationActivityType extends BaseLearningActivityType {
	
	private final ModuleService moduleService;
	private final LearningActivityService activityService;
	private final LearningActivityTryLocalService learningActivityTryLocalService;
	private final LearningActivityLocalService activityLocalService;
	private static final Log log = LogFactoryUtil.getLog(EvaluationActivityType.class);
	
	private Date firedDate;
	private Date publishDate;
	private JSONObject activities;

	public EvaluationActivityType(LearningActivity activity, LearningActivityResultLocalService learningActivityResultLocalService,
			ModuleService moduleService, LearningActivityService activityService, LearningActivityTryLocalService learningActivityTryLocalService,
			LearningActivityLocalService activityLocalService) {
		super(activity, learningActivityResultLocalService);
		
		this.moduleService = moduleService;
		this.activityService  = activityService;
		this.learningActivityTryLocalService = learningActivityTryLocalService;
		this.activityLocalService = activityLocalService;
		
		JSONObject extraContent = activity.getExtraContentJSON();
		
		if(extraContent != null) {
			JSONObject evaluation = extraContent.getJSONObject(EvaluationConstants.JSON_EVALUATION);
			if(evaluation != null) {
				firedDate = null;
				if(Validator.isNotNull(evaluation.get(EvaluationConstants.JSON_FIRED_DATE))) {
					firedDate = new Date();
					firedDate.setTime(evaluation.getLong(EvaluationConstants.JSON_FIRED_DATE));
				}
				publishDate = null;
				if(Validator.isNotNull(evaluation.get(EvaluationConstants.JSON_PUBLISH_DATE))) {
					publishDate = new Date();
					publishDate.setTime(evaluation.getLong(EvaluationConstants.JSON_PUBLISH_DATE));
				}
				activities = evaluation.getJSONObject(LearningActivityConstants.JSON_ACTIVITIES);
			}else {
				initializateActivity();
			}
		}else {
			initializateActivity();
		}
	} 
	
	@Override
	public double calculateResult(LearningActivityTry learningActivityTry) {
		JSONObject activities = getActivities();
		
		double[] values = new double[activities.length()];
		double[] weights = new double[activities.length()];
		
		AtomicInteger cnt = new AtomicInteger(0);
		
		activities.keys().forEachRemaining(keyStr ->{
			LearningActivityResult learningActivityResult = learningActivityResultLocalService.getLearningActivityResult(Long.parseLong(keyStr), learningActivityTry.getUserId());
			values[cnt.get()] = learningActivityResult != null ? learningActivityResult.getResult() : 0;
			weights[cnt.get()]=activities.getDouble(keyStr);	
			cnt.incrementAndGet();
		});

		double sumWeight=0;
		double mean=0;
		
		for (int i = 0; i < values.length; i++) {
			sumWeight+=weights[i];
			mean+=weights[i]*values[i];
		}
		mean/=sumWeight;
		
		//Correction factor
		double correction=0;
		for (int i = 0; i < values.length; i++) {
			correction += weights[i] * (values[i] - mean);
		}
		
		return mean + (correction/sumWeight);	
	}
	
	public JSONObject getActivities(){
		return activities;
	}
	
	public Date getFiredDate() {
		return firedDate;
	}
	
	public void setFiredDate(Date firedDate) {
		this.firedDate = firedDate;
		
		JSONObject extraContent = activity.getExtraContentJSON();
		
		JSONObject evaluationContent = extraContent.getJSONObject(EvaluationConstants.JSON_EVALUATION);
		
		if(Validator.isNull(evaluationContent)) {
			evaluationContent = JSONFactoryUtil.createJSONObject();
			extraContent.put(EvaluationConstants.JSON_EVALUATION, evaluationContent);
		}
		
		evaluationContent.put(EvaluationConstants.JSON_FIRED_DATE, firedDate);
		
		activity.setExtraContent(extraContent.toJSONString());
	} 
	
	public Date getPublishDate() {
		return publishDate;
	}
	
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
		
		JSONObject extraContent = activity.getExtraContentJSON();
		
		JSONObject evaluationContent = extraContent.getJSONObject(EvaluationConstants.JSON_EVALUATION);
		
		if(Validator.isNull(evaluationContent)) {
			evaluationContent = JSONFactoryUtil.createJSONObject();
			extraContent.put(EvaluationConstants.JSON_EVALUATION, evaluationContent);
		}
		
		evaluationContent.put(EvaluationConstants.JSON_PUBLISH_DATE, publishDate);
		
		activity.setExtraContent(extraContent.toJSONString());
	}
	
	@Override
	public void setExtraContent(ActionRequest actionRequest) throws PortalException {
		
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		JSONObject extraContent = activity.getExtraContentJSON();
		
		JSONObject evaluationContent = extraContent.getJSONObject(EvaluationConstants.JSON_EVALUATION);
		
		if(Validator.isNull(evaluationContent)) {
			evaluationContent = JSONFactoryUtil.createJSONObject();
			extraContent.put(EvaluationConstants.JSON_EVALUATION, evaluationContent);
		}

		List<Module> modules = moduleService.getGroupModules(themeDisplay.getScopeGroupId());

		List<LearningActivity> listActivities = null;
		activities = JSONFactoryUtil.createJSONObject();
		evaluationContent.put(LearningActivityConstants.JSON_ACTIVITIES, activities);

		double weight = 0;
		
		for(Module module: modules) {
			listActivities = activityService.getActivitiesNotTypeId(module.getModuleId(), EvaluationConstants.TYPE);
			for(LearningActivity activity: listActivities) {
				weight = ParamUtil.getDouble(actionRequest, "weight_" + activity.getActId(), 0);
				if(weight > 0) {
					activities.put(String.valueOf(activity.getActId()), weight);
				}
			}
		}
		
		activity.setExtraContent(extraContent.toJSONString());
	}
	
	@Override
	public String getClassName() {
		return EvaluationActivityType.class.getName();
	}
	
	private void initializateActivity() {
		firedDate = null;
		publishDate = null;
		activities = JSONFactoryUtil.createJSONObject();
	}
	
	public void evaluateUser(long userId, ServiceContext serviceContext) throws PortalException {
		
		log.debug("num activities: " + activities.length());
		double[] values = new double[activities.length()];
		double[] weights = new double[activities.length()];
		
		AtomicInteger cnt = new AtomicInteger(0);
		
		if(activities.length() > 0) {
			
			log.debug("learningActivityResultLocalService: " + learningActivityResultLocalService);
			
			activities.keys().forEachRemaining(keyStr ->{
				LearningActivityResult learningActivityResult = learningActivityResultLocalService.getLearningActivityResult(Long.parseLong(keyStr), userId);
				values[cnt.get()] = learningActivityResult != null ? learningActivityResult.getResult() : 0;
				weights[cnt.get()]=activities.getDouble(keyStr);	
				cnt.incrementAndGet();
			});
	
			LearningActivityTry  learningActivityTry =  learningActivityTryLocalService.getLastLearningActivityTry(activity.getActId(), userId);
			if(learningActivityTry==null){
				learningActivityTry =  learningActivityTryLocalService.addLearningActivityTry(activity.getActId(), userId, serviceContext);
			}
			
			learningActivityTryLocalService.finishLearningActivityTry(learningActivityTry, calculateResult(learningActivityTry), serviceContext);	
		}else {
			log.debug("No hay actividades configuradas para la actividad: " + activity.getActId());
		}
	}
	
	@Override
	public void doExportStagedModel(PortletDataContext portletDataContext, Element activityElement) throws PortalException{
		
		activities = getActivities();
		
		if(activities.length() > 0) {
			activities.keys().forEachRemaining(keyStr ->{
				try {
					LearningActivity activityRelation = activityLocalService.getLearningActivity(Long.parseLong(keyStr));
					StagedModelDataHandlerUtil.exportReferenceStagedModel(
							portletDataContext, activity, activityRelation,
							PortletDataContext.REFERENCE_TYPE_DEPENDENCY);
				} catch (NumberFormatException | PortalException e) {
					e.printStackTrace();
				}
			});
		}
	}
			
}
