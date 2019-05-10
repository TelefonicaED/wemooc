package com.ted.lms.learning.activity.survey.web.activity;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.ted.lms.learning.activity.question.model.Question;
import com.ted.lms.learning.activity.question.service.QuestionLocalServiceUtil;
import com.ted.lms.model.BaseLearningActivityType;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.LearningActivityTry;
import java.util.List;

import javax.portlet.ActionRequest;

public class SurveyActivityType extends BaseLearningActivityType {
	
	private static final Log log = LogFactoryUtil.getLog(SurveyActivityType.class);
	
	private List<Question> questions;
	
	public SurveyActivityType(LearningActivity activity) {
		super(activity);
	}
	
	@Override
	public String getClassName() {
		return SurveyActivityType.class.getName();
	}
	
	@Override
	public void setExtraContent(ActionRequest actionRequest) throws PortalException {
		log.debug("Guardamos las preguntas");
		QuestionLocalServiceUtil.saveQuestions(actionRequest, activity.getActId());
	}
		
	@Override
	public double calculateResult(LearningActivityTry learningActivityTry) {
		return 100;
	}
	
	public List<Question> getQuestions(){
		if(questions == null) {
			questions = QuestionLocalServiceUtil.getQuestions(activity.getActId());
		}
		return questions;
	}
	
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

}
