package com.ted.lms.question.freetext;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.ted.lms.learning.activity.question.model.Answer;
import com.ted.lms.learning.activity.question.model.BaseQuestionType;
import com.ted.lms.learning.activity.question.model.Question;
import com.ted.lms.learning.activity.question.service.AnswerLocalService;

import java.text.Collator;
import java.util.Iterator;
import java.util.List;

import javax.portlet.PortletRequest;

public class FreetextQuestionType extends BaseQuestionType{
	
	private static final Log log = LogFactoryUtil.getLog(FreetextQuestionType.class);

	public FreetextQuestionType(Question question, AnswerLocalService answerLocalService) {
		super(question, answerLocalService);
	}
	
	@Override
	public String getClassName() {
		return FreetextQuestionType.class.getName();
	}

	@Override
	public long getType() {
		return FreetextQuestionTypeFactory.TYPE;
	}
	
	@Override
	public String getURLQuestion() {
		return "/question/freetext/view.jsp";
	}
	
	@Override
	public long correct(Element element) throws PortalException {
		String answer= element.element("answer").getText();
		List<Answer> answers = answerLocalService.getAnswersByQuestionId(question.getQuestionId());

		long result = INCORRECT;
		
		if(Validator.isNotNull(answers) && answers.size() > 0){
			Answer solution = answers.get(0);
			if (isCorrect(solution, answer)){
				result = CORRECT;
			}else{
				result = INCORRECT;
			}
		}
		return result;
	}
	
	@Override
	public Element getResults(PortletRequest portletRequest){
		String answer= ParamUtil.getString(portletRequest, "question_"+question.getQuestionId(), "");

		Element questionXML=SAXReaderUtil.createElement("question");
		questionXML.addAttribute("id", Long.toString(question.getQuestionId()));

		long currentQuestionId = ParamUtil.getLong(portletRequest, "currentQuestionId");
		if (currentQuestionId == question.getQuestionId()) {
			questionXML.addAttribute("current", "true");
		}

		Element answerXML=SAXReaderUtil.createElement("answer");
		answerXML.addText(answer);
		questionXML.add(answerXML);

		return questionXML;
	}
	
	public boolean isCorrect(Answer solution, String answer){
		Collator c = Collator.getInstance();
		c.setStrength(Collator.PRIMARY);
		return c.compare(solution.getAnswer(), answer) == 0;
	}
	
	public long isCorrect(PortletRequest portletRequest){
		String answer= ParamUtil.getString(portletRequest, "question_"+question.getQuestionId(), "");
		List<Answer> testAnswers = answerLocalService.getAnswersByQuestionId(question.getQuestionId());

		if(testAnswers!=null && testAnswers.size()>0){
			Answer solution = testAnswers.get(0);
			if (isCorrect(solution, answer)){
				return CORRECT;
			}else{
				return INCORRECT;
			}
		}
		return INCORRECT;
	}
	
	public String getAnswersSelected(Document document){
		String answerGiven = "";
		if(document != null){
			Iterator<Element> nodeItr = document.getRootElement().elementIterator();
			while(nodeItr.hasNext()) {
				Element element = nodeItr.next();
				if("question".equals(element.getName()) && question.getQuestionId() == Long.valueOf(element.attributeValue("id"))){
					Iterator<Element> elementItr = element.elementIterator();
					while(elementItr.hasNext()) {
						Element elementElement = elementItr.next();
						if("answer".equals(elementElement.getName())) {
							try {
								answerGiven = elementElement.getText();
							} catch (NumberFormatException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}	
		}
		return answerGiven;
	}
}
