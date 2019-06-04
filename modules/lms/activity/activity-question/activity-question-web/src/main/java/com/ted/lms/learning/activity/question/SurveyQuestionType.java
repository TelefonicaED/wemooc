package com.ted.lms.learning.activity.question;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ted.lms.learning.activity.question.model.Question;
import com.ted.lms.learning.activity.question.service.AnswerLocalService;

import javax.portlet.ActionRequest;

public class SurveyQuestionType extends OptionsQuestionType{
	
	private static final Log log = LogFactoryUtil.getLog(SurveyQuestionType.class);

	public SurveyQuestionType(Question question, AnswerLocalService answerLocalService) {
		super(question, answerLocalService);
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
	
	@Override
	public boolean isCorrectRequest(ActionRequest actionRequest, String iteratorQuestion, int counter) {
		return true;
	}
}
