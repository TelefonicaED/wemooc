package com.ted.lms.question.multioptions;

import com.ted.lms.learning.activity.question.model.Question;
import com.ted.lms.learning.activity.question.service.AnswerLocalService;
import com.ted.lms.question.options.OptionsQuestionType;

public class MultioptionsQuestionType extends OptionsQuestionType{

	public MultioptionsQuestionType(Question question, AnswerLocalService answerLocalService) {
		super(question, answerLocalService);
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
