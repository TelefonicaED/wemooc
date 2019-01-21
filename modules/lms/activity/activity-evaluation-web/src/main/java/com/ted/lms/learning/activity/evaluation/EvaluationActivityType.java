package com.ted.lms.learning.activity.evaluation;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Validator;
import com.ted.lms.learning.activity.evaluation.web.constants.EvaluationConstants;
import com.ted.lms.model.BaseLearningActivityType;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.LearningActivityResult;
import com.ted.lms.model.LearningActivityTry;
import com.ted.lms.service.LearningActivityResultLocalService;

import javax.portlet.ActionRequest;

public class EvaluationActivityType extends BaseLearningActivityType {

	public EvaluationActivityType(LearningActivity activity, LearningActivityResultLocalService learningActivityResultLocalService) {
		super(activity, learningActivityResultLocalService);
	} 
	
	@Override
	public double calculateResult(LearningActivityTry learningActivityTry) {
		JSONArray activities = getLearningActivities();
		
		double[] values = new double[activities.length()];
		double[] weights = new double[activities.length()];
		
		LearningActivityResult learningActivityResult = null;
		
		for(int i = 0; i < activities.length(); i++){
			learningActivityResult = learningActivityResultLocalService.getLearningActivityResult(activities.getJSONObject(i).getLong(EvaluationConstants.JSON_ACT_ID), learningActivityTry.getUserId());
			values[i] = learningActivityResult != null ? learningActivityResult.getResult() : 0;
			weights[i]=activities.getJSONObject(i).getInt(EvaluationConstants.JSON_WEIGHT);	
		}

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
	
	public JSONArray getLearningActivities(){
		
		JSONObject extraContent = activity.getExtraContentJSON();
		
		JSONObject evaluation = extraContent.getJSONObject(EvaluationConstants.JSON_EVALUATION);
		
		JSONArray activitiesArray = null;
		
		if(Validator.isNotNull(evaluation)) {
			activitiesArray = evaluation.getJSONArray(EvaluationConstants.JSON_ACTIVITIES);
		}
		
		
		return activitiesArray;
	}
	
	@Override
	public void setExtraContent(ActionRequest actionRequest) throws PortalException {
		
	}
	
	@Override
	public String getClassName() {
		return EvaluationActivityType.class.getName();
	}
			
}
