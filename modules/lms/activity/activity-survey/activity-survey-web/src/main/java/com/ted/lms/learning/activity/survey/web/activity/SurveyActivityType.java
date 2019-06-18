package com.ted.lms.learning.activity.survey.web.activity;

import com.liferay.portal.kernel.exception.PortalException;
import com.ted.lms.learning.activity.QuestionsLearningActivityType;
import com.ted.lms.learning.activity.question.service.AnswerLocalService;
import com.ted.lms.learning.activity.question.service.QuestionLocalService;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.LearningActivityTry;
import com.ted.lms.service.LearningActivityResultLocalService;

import javax.portlet.ActionRequest;

public class SurveyActivityType extends QuestionsLearningActivityType {
	
	public SurveyActivityType(LearningActivity activity, LearningActivityResultLocalService learningActivityResultLocalService,
			QuestionLocalService questionLocalService, AnswerLocalService answerLocalService) {
		super(activity, learningActivityResultLocalService, questionLocalService, answerLocalService);
	}
	
	@Override
	public String getClassName() {
		return SurveyActivityType.class.getName();
	}
	
	@Override
	public void setExtraContent(ActionRequest actionRequest) throws PortalException {
		super.setExtraContent(actionRequest);
	}
		
	@Override
	public double calculateResult(LearningActivityTry learningActivityTry) {
		return 100;
	}

}
