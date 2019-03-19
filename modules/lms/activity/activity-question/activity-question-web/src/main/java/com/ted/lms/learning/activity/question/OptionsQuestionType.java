package com.ted.lms.learning.activity.question;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.ted.lms.learning.activity.question.constants.OptionConstants;
import com.ted.lms.learning.activity.question.constants.QuestionConstants;
import com.ted.lms.learning.activity.question.model.Answer;
import com.ted.lms.learning.activity.question.model.BaseQuestionType;
import com.ted.lms.learning.activity.question.model.Question;
import com.ted.lms.learning.activity.question.service.AnswerLocalServiceUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.portlet.ActionRequest;

public class OptionsQuestionType extends BaseQuestionType{
	
	private int formatType;

	public OptionsQuestionType(Question question) {
		super(question);
		
		JSONObject extraContent = question.getExtraContentJSON();
		
		if(extraContent != null) {
			JSONObject options = extraContent.getJSONObject(QuestionConstants.JSON_OPTIONS);
			if(options != null) {
				formatType = options.getInt(QuestionConstants.JSON_FORMAT_TYPE, OptionConstants.DEFAULT_FORMAT_TYPE);
			}else {
				formatType = OptionConstants.DEFAULT_FORMAT_TYPE;
			}
		}else {
			formatType = OptionConstants.DEFAULT_FORMAT_TYPE;
		}
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
	public String getURLQuestion() {
		return "/question/options/view.jsp";
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
		System.out.println("correct: " + ParamUtil.getInteger(actionRequest, iteratorQuestion + "_correct_" + counter));
		
		if(ParamUtil.getInteger(actionRequest, iteratorQuestion + "_correct") == counter){
			correct = true;
		}	
		return correct;
	}
	
	@Override
	public long getType() {
		return OptionsQuestionTypeFactory.TYPE;
	}
	
	public int getFormatType() {
		return formatType;
	}
	
	public List<Answer> getAnswersSelected(Document document,long questionId){
		List<Answer> answerSelected = new ArrayList<Answer>();
		if(document != null){
			Iterator<Element> nodeItr = document.getRootElement().elementIterator();
			while(nodeItr.hasNext()) {
				Element element = nodeItr.next();
				if("question".equals(element.getName()) && questionId == Long.valueOf(element.attributeValue("id"))){
					Iterator<Element> elementItr = element.elementIterator();
					while(elementItr.hasNext()) {
						Element elementElement = elementItr.next();
						if("answer".equals(elementElement.getName())) {
							try {
								answerSelected.add(AnswerLocalServiceUtil.getAnswer(Long.valueOf(elementElement.attributeValue("id"))));
							} catch (NumberFormatException e) {
								e.printStackTrace();
							} catch (PortalException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}	
		}
		return answerSelected;
	}
	
	public boolean isCorrect(Answer testAnswer){
		return (testAnswer!=null)?testAnswer.isCorrect():false;
	}
	
}
