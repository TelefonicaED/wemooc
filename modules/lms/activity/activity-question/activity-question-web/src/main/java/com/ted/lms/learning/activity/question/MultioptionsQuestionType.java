package com.ted.lms.learning.activity.question;

import com.ted.lms.learning.activity.question.model.Question;

public class MultioptionsQuestionType extends OptionsQuestionType{

	public MultioptionsQuestionType(Question question) {
		super(question);
	}

	@Override
	public long getType() {
		return MultioptionsQuestionTypeFactory.TYPE;
	}
	
	@Override
	public String getURLQuestion() {
		return "/question/multioptions/view.jsp";
	}
	
	public boolean isQuestionCorrect(int correctAnswers, int correctAnswered, int incorrectAnswered){
		return correctAnswers==correctAnswered && incorrectAnswered==0;
	}
}
