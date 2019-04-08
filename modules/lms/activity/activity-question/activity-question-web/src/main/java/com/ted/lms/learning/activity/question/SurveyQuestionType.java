package com.ted.lms.learning.activity.question;

import com.ted.lms.learning.activity.question.model.Question;

public class SurveyQuestionType extends OptionsQuestionType{

	public SurveyQuestionType(Question question) {
		super(question);
	}

	@Override
	public long getType() {
		return SurveyQuestionTypeFactory.TYPE;
	}
	
	@Override
	public String getURLQuestion() {
		return "/question/survey/view.jsp";
	}
	
	protected boolean isQuestionCorrect(int correctAnswers, int correctAnswered, int incorrectAnswered){
		return true;
	}
}
