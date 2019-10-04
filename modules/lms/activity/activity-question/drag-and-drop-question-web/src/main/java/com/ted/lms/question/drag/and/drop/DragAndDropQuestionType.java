package com.ted.lms.question.drag.and.drop;

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
import com.ted.lms.learning.activity.question.service.AnswerLocalService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.portlet.PortletRequest;

public class DragAndDropQuestionType extends BaseQuestionType{
	
	private static final Log log = LogFactoryUtil.getLog(DragAndDropQuestionType.class);

	public DragAndDropQuestionType(Question question, AnswerLocalService answerLocalService) {
		super(question, answerLocalService);
	}
	
	@Override
	public String getClassName() {
		return DragAndDropQuestionType.class.getName();
	}

	@Override
	public long getType() {
		return DragAndDropQuestionTypeFactory.TYPE;
	}
	
	@Override
	public String getURLQuestion() {
		return "/question/draganddrop/view.jsp";
	}
	
	@Override
	public long correct(Element element) throws PortalException {
		List<Answer> answers = new ArrayList<Answer>();
		answers.addAll(answerLocalService.getAnswersByQuestionId(question.getQuestionId()));

		//me quedo solo con un array con la solucion
		for(ListIterator<Answer> itr = answers.listIterator(); itr.hasNext();){
			Answer tanswer = itr.next();
			if(!tanswer.isCorrect()) itr.remove();
		}

		List<Long> answersId = new ArrayList<Long>();
		for(int i=0;i<answers.size();i++){
			log.debug("TEST ANSWER ID "+answers.get(i).getAnswerId());
			Iterator<Element> itElements = element.elements("answer").iterator();
			boolean idExist = false;
			while(!idExist && itElements.hasNext()){
				Element questionElement = itElements.next();
				idExist = questionElement.attributeValue("id").equals(String.valueOf(answers.get(i).getAnswerId()));
				log.debug("ID ELEMENT "+questionElement.attributeValue("id"));
				log.debug("ID ANSWER "+answers.get(i).getAnswerId());
				log.debug("ID EXIST "+idExist);
				if(idExist){
					answersId.add(answers.get(i).getAnswerId());
				}
			}
		}

		if(!isCorrect(answersId, answers)){
			return INCORRECT;
		}else{
			return CORRECT;
		}
	}
	
	public boolean isCorrect(List<Long> answersId, List<Answer> answers){
		boolean result = Boolean.TRUE;
		if (answers.size() == answersId.size()) {
			// El numero de respuestas ha de coincidir
			for(Answer answer : answers) {
				if (!answersId.contains(answer.getAnswerId())) {
					result = Boolean.FALSE;
				}
			}
		} else {
			return Boolean.FALSE;
		}
		return result;
	}
	
	@Override
	public Element getResults(PortletRequest portletRequest){
		List<Answer> testAnswers = new ArrayList<Answer>();
		testAnswers.addAll(answerLocalService.getAnswersByQuestionId(question.getQuestionId()));

		//me quedo solo con un array con la solucion
		for(ListIterator<Answer> itr = testAnswers.listIterator(); itr.hasNext();){
			Answer tanswer = itr.next();
			if(!tanswer.isCorrect()) itr.remove();
		}

		List<Long> answersId = new ArrayList<Long>();
		for(int i=0;i<testAnswers.size();i++){
			answersId.add(ParamUtil.getLong(portletRequest, "question_"+question.getQuestionId()+"_"+i+"hidden"));
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
	
	public long isCorrect(PortletRequest portletRequest){
		List<Answer> answers = new ArrayList<Answer>();
		answers.addAll(answerLocalService.getAnswersByQuestionId(question.getQuestionId()));

		//me quedo solo con un array con la solucion
		for(ListIterator<Answer> itr = answers.listIterator(); itr.hasNext();){
			Answer tanswer = itr.next();
			if(!tanswer.isCorrect()) itr.remove();
		}

		List<Long> answersId = new ArrayList<Long>();
		for(int i=0;i<answers.size();i++){
			answersId.add(ParamUtil.getLong(portletRequest, "question_"+question.getQuestionId()+"_"+i+"hidden"));
		}

		if(!isCorrect(answersId, answers)){
			return INCORRECT;
		}else{
			return CORRECT;
		}
	}
	
	public List<Answer> getAnswersSelected(Document document){
		List<Answer> answerSelected = new ArrayList<Answer>();
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
								long id = Long.valueOf(elementElement.attributeValue("id"));
								if(id != -1)
									answerSelected.add(answerLocalService.getAnswer(id));
								else answerSelected.add(null);
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
	
	@Override
	public String getXMLType() {
		return "draganddrop";
	}

	@Override
	public Element exportXML() {
		Element questionXML = super.exportXML();

		List<Answer> answers = answerLocalService.getAnswersByQuestionId(question.getQuestionId());
		for(Answer answer:answers){
			Element answerE = SAXReaderUtil.createElement("answer");
			answerE.addAttribute("fraction", (answer.isCorrect())?"100":"0");
			
			Element text = SAXReaderUtil.createElement("text");
			text.addText(answer.getAnswer());
			answerE.add(text);
			
			Element feedback = SAXReaderUtil.createElement("feedback");
			Element feedText = SAXReaderUtil.createElement("text");
			feedText.addText(answer.getFeedbackCorrect());
			feedback.add(feedText);
			answerE.add(feedback);
			questionXML.add(answerE);
		}

		return questionXML;
	}
}
