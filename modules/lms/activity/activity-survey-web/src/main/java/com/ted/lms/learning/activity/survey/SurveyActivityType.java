package com.ted.lms.learning.activity.survey;

import com.ted.lms.model.BaseLearningActivityType;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.LearningActivityTry;
import com.ted.lms.service.LearningActivityResultLocalService;

public class SurveyActivityType extends BaseLearningActivityType {
	
	public SurveyActivityType(LearningActivity activity) {
		super(activity);
	}
	
	@Override
	public String getClassName() {
		return SurveyActivityType.class.getName();
	}
	
	@Override
	public double calculateResult(LearningActivityTry learningActivityTry) {
		return 100;
	}

}
