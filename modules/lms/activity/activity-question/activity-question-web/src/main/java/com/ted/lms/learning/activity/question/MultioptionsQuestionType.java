package com.ted.lms.learning.activity.question;

import com.ted.lms.learning.activity.question.model.Question;
import com.ted.lms.learning.activity.question.service.AnswerLocalService;

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
