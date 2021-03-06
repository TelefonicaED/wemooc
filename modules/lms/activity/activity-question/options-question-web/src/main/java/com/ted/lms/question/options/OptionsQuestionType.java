package com.ted.lms.question.options;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
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
import com.ted.lms.question.options.constants.OptionsConstants;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;

public class OptionsQuestionType extends BaseQuestionType{
	
	private int formatType;
	private boolean partialCorrection;

	public OptionsQuestionType(Question question, AnswerLocalService answerLocalService) {
		super(question, answerLocalService);
		
		JSONObject extraContent = question.getExtraContentJSON();
		
		log.debug("extraContent: " + extraContent);
		
		if(extraContent != null) {
			JSONObject options = extraContent.getJSONObject(OptionsConstants.JSON_OPTIONS);
			if(options != null) {
				formatType = options.getInt(OptionsConstants.JSON_FORMAT_TYPE, OptionsConstants.DEFAULT_FORMAT_TYPE);
				partialCorrection = options.getBoolean(OptionsConstants.JSON_PARTIAL_CORRECTION, false);
			}else {
				formatType = OptionsConstants.DEFAULT_FORMAT_TYPE;
				partialCorrection = false;
			}
		}else {
			formatType = OptionsConstants.DEFAULT_FORMAT_TYPE;
			partialCorrection = false;
		}
	}

	@Override
	public String getClassName() {
		return OptionsQuestionType.class.getName();
	}

	@Override
	public long correct(Element element) throws PortalException {
		Iterator<Element> iteratorAnswers = element.elementIterator("answer");
		List<Long> arrayAnswersId = new ArrayList<Long>();
		
		Element elementAnswer = null;
		
		while(iteratorAnswers.hasNext()) {
			elementAnswer = iteratorAnswers.next();
			arrayAnswersId.add(Long.parseLong(elementAnswer.attributeValue("id")));
		}
		
		
		return correct(arrayAnswersId);
	}
	
	@Override
	public String getURLQuestion() {
		return "/question/options/view.jsp";
	}

	@Override
	public void setExtraContent(ActionRequest actionRequest, String iteratorQuestion) throws PortalException {
		super.setExtraContent(actionRequest, iteratorQuestion);
		
		JSONObject extraContent = question.getExtraContentJSON();
		
		
		log.debug("extraContent: " + extraContent);
		
		JSONObject optionsContent = extraContent.getJSONObject(OptionsConstants.JSON_OPTIONS);
		
		if(Validator.isNull(optionsContent)) {
			optionsContent = JSONFactoryUtil.createJSONObject();
			extraContent.put(OptionsConstants.JSON_OPTIONS, optionsContent);
		}
		
		formatType = ParamUtil.getInteger(actionRequest, iteratorQuestion + "_formatType", OptionsConstants.OPTION_FORMAT_TYPE_VERTICAL);
		partialCorrection = ParamUtil.getBoolean(actionRequest, iteratorQuestion + "_partialCorrection", OptionsConstants.DEFAULT_PARTIAL_CORRECTION);
		
		optionsContent.put(OptionsConstants.JSON_FORMAT_TYPE, formatType);
		optionsContent.put(OptionsConstants.JSON_PARTIAL_CORRECTION, partialCorrection);
		
		log.debug("extraContent modified: " + extraContent);
		
		question.setExtraContent(extraContent.toJSONString());
		question.setExtraContentJSON(extraContent);
	}

	@Override
	public boolean isCorrectRequest(ActionRequest actionRequest, String iteratorQuestion, int counter) {
		boolean correct = false;
		log.debug("iteratorQuestion: " + iteratorQuestion);
		log.debug("counter: " + counter);
		log.debug("correct: " + ParamUtil.getInteger(actionRequest, iteratorQuestion + "_correct_" + counter));
		
		if(ParamUtil.getInteger(actionRequest, iteratorQuestion + "_correct") == counter){
			correct = true;
		}	
		return correct;
	}
	
	@Override
	public long getType() {
		return OptionsQuestionTypeFactory.TYPE;
	}
	
	@Override
	public Element getResults(PortletRequest portletRequest){
		long[] answersId= ParamUtil.getLongValues(portletRequest, "question_"+question.getQuestionId());

		List<Long> arrayAnswersId = new ArrayList<Long>();
		for(long answerId:answersId) arrayAnswersId.add(answerId);

		Element questionXML=SAXReaderUtil.createElement("question");
		questionXML.addAttribute("id", Long.toString(question.getQuestionId()));

		long currentQuestionId = ParamUtil.getLong(portletRequest, "currentQuestionId");
		if (currentQuestionId == question.getQuestionId()) {
			questionXML.addAttribute("current", "true");
		}

		for(long answer:arrayAnswersId){
			if(answer >0){
				Element answerXML=SAXReaderUtil.createElement("answer");
				answerXML.addAttribute("id", Long.toString(answer));
				questionXML.add(answerXML);
			}
		}
		return questionXML;
	}
	
	public long isCorrect(PortletRequest portletRequest){
		long[] answersId= ParamUtil.getLongValues(portletRequest, "question_"+question.getQuestionId());
		List<Long> arrayAnswersId = new ArrayList<Long>();
		for(long answerId:answersId) arrayAnswersId.add(answerId);

		return correct(arrayAnswersId);
		
	}
	
	public int getFormatType() {
		return formatType;
	}
	
	public boolean getPartialCorrection() {
		return partialCorrection;
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
								answerSelected.add(answerLocalService.getAnswer(Long.valueOf(elementElement.attributeValue("id"))));
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
	
	private long correct(List<Long> arrayAnswersId){
		long retVal = 0;
		
		List<Answer> testAnswers = answerLocalService.getAnswersByQuestionId(question.getQuestionId());
		
		int correctAnswers=0, correctAnswered=0, incorrectAnswered=0;
		for(Answer answer:testAnswers){
			log.debug("comprobamos la respuesta: " + answer.getAnswerId());
			if(isCorrect(answer)){
				log.debug("es la correcta");
				correctAnswers++;
				if(arrayAnswersId.contains(answer.getAnswerId())){ 
					correctAnswered++;
				}
			}else if(arrayAnswersId.contains(answer.getAnswerId())){ 
				incorrectAnswered++;
			}
		}
		boolean partialCorrection = getPartialCorrection();
			
		if(partialCorrection){
			retVal = correctAnswered*100/correctAnswers;
		}else{
			log.debug("correctAnswers: " + correctAnswers);
			log.debug("correctAnswered: " + correctAnswered);
			log.debug("incorrectAnswered: " + incorrectAnswered);
			if(isQuestionCorrect(correctAnswers, correctAnswered, incorrectAnswered)){
				retVal = CORRECT;
			}
			else{
				retVal = question.getPenalize() ? -CORRECT : INCORRECT;
			}
		}
		
		return retVal;
	}
	
	protected boolean isQuestionCorrect(int correctAnswers, int correctAnswered, int incorrectAnswered){
		return correctAnswered>0 && incorrectAnswered==0;
	}
	
	public boolean getXMLSingle() {
		return true;
	}
	
	@Override
	public String getXMLType() {
		return "multichoice";
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
		Element single = SAXReaderUtil.createElement("single");
		single.addText(String.valueOf(getXMLSingle()));
		questionXML.add(single);
		return questionXML;
	}
	
	private static final Log log = LogFactoryUtil.getLog(OptionsQuestionType.class);
	
}
