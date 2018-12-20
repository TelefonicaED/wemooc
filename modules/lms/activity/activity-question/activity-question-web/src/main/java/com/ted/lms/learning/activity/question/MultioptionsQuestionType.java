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
}
