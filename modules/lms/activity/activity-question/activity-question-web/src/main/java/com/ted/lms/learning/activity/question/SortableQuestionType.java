package com.ted.lms.learning.activity.question;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.ted.lms.learning.activity.question.model.Answer;
import com.ted.lms.learning.activity.question.model.BaseQuestionType;
import com.ted.lms.learning.activity.question.model.Question;
import com.ted.lms.learning.activity.question.service.AnswerLocalServiceUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.portlet.PortletRequest;

public class SortableQuestionType extends BaseQuestionType{
	
	private static final Log log = LogFactoryUtil.getLog(SortableQuestionType.class);

	public SortableQuestionType(Question question) {
		super(question);
	}
	
	@Override
	public String getClassName() {
		return SortableQuestionType.class.getName();
	}

	@Override
	public long getType() {
		return SortableQuestionTypeFactory.TYPE;
	}
	
	@Override
	public String getURLQuestion() {
		return "/question/sortable/view.jsp";
	}
	
	@Override
	public long correct(Element element) throws PortalException {
		log.debug("element: " + element.asXML());
		
		Iterator<Element> iteratorAnswers = element.elementIterator("answer");
		List<Long> answersId = new ArrayList<Long>();
		
		Element elementAnswer = null;
		
		while(iteratorAnswers.hasNext()) {
			elementAnswer = iteratorAnswers.next();
			log.debug("elementAnswer: " + elementAnswer.asXML());
			answersId.add(Long.parseLong(elementAnswer.attributeValue("id")));
		}
		
		List<Answer> answers = AnswerLocalServiceUtil.getAnswersByQuestionId(question.getQuestionId());
		
		if(!isCorrect(answersId, answers)){
			return INCORRECT;
		}else{
			return CORRECT;
		}
	}
	
	@Override
	public Element getResults(PortletRequest portletRequest){

		List<Long> answersId = new ArrayList<Long>();
		long[] answers = ParamUtil.getLongValues(portletRequest, "question_"+question.getQuestionId()+"_ans");
		for(long id:answers){
			answersId.add(id);
		}

		Element questionXML=SAXReaderUtil.createElement("question");
		questionXML.addAttribute("id", Long.toString(question.getQuestionId()));
		
		long currentQuestionId = ParamUtil.getLong(portletRequest, "currentQuestionId");
		if (currentQuestionId == question.getQuestionId()) {
			questionXML.addAttribute("current", "true");
		}

		for(long answer:answersId){
			Element answerXML=SAXReaderUtil.createElement("answer");
			answerXML.addAttribute("id", Long.toString(answer));
			questionXML.add(answerXML);
		}
		return questionXML;
	}
	
	
	public boolean isCorrect(List<Long> answersId, List<Answer> answers){
		boolean correct = true;
		for(int i=0;i<answers.size();i++){ 
			if(answersId.get(i) == -1 || answersId.get(i) != answers.get(i).getAnswerId())	
				correct = false;
		}
		return correct;
	}
	
	@Override
	public long isCorrect(PortletRequest portletRequest) throws PortalException {
		List<Long> answersId = new ArrayList<Long>();
		long[] answers = ParamUtil.getLongValues(portletRequest, "question_"+question.getQuestionId()+"_ans");
		for(long id:answers){
			answersId.add(id);
		}

		List<Answer> testAnswers = AnswerLocalServiceUtil.getAnswersByQuestionId(question.getQuestionId());
		
		if(!isCorrect(answersId, testAnswers)){
			return INCORRECT;
		}else{
			return CORRECT;
		}
	}
	
	public List<Answer> getAnswerSelected(Document document,long questionId){
		List<Answer> answerSelected = new ArrayList<Answer>();
		if (document != null) {
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

}
