package com.ted.lms.learning.activity.question;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.xml.Element;
import com.ted.lms.learning.activity.question.constants.OptionConstants;
import com.ted.lms.learning.activity.question.constants.QuestionConstants;
import com.ted.lms.learning.activity.question.model.BaseQuestionType;
import com.ted.lms.learning.activity.question.model.Question;
import com.ted.lms.learning.activity.question.service.AnswerLocalService;

import javax.portlet.ActionRequest;

public class OptionsQuestionType extends BaseQuestionType{

	public OptionsQuestionType(Question question, AnswerLocalService answerLocalService) {
		super(question, answerLocalService);
	}

	@Override
	public String getClassName() {
		return OptionsQuestionType.class.getName();
	}

	@Override
	public long correct(Element element) throws PortalException {
		return 0;
	}

	@Override
	public void setExtraContent(ActionRequest actionRequest) throws PortalException {
		super.setExtraContent(actionRequest);
		
		JSONObject extraContent = question.getExtraContentJSON();
		
		int formatType = ParamUtil.getInteger(actionRequest, "formatType", OptionConstants.OPTION_FORMAT_TYPE_VERTICAL);
		
		extraContent.put(QuestionConstants.JSON_FORMAT_TYPE, formatType);
	}

	@Override
	public boolean isCorrectRequest(ActionRequest actionRequest, String iteratorQuestion, int counter) {
		boolean correct = false;
		System.out.println("iteratorQuestion: " + iteratorQuestion);
		System.out.println("counter: " + counter);
		System.out.println("correct: " + ParamUtil.getInteger(actionRequest, iteratorQuestion + "_correct_new_" + counter));
		
		if(ParamUtil.getInteger(actionRequest, iteratorQuestion + "_correct_new") == counter){
			correct = true;
		}	
		return correct;
	}
	
	@Override
	public long getType() {
		return OptionsQuestionTypeFactory.TYPE;
	}
	
}
