package com.ted.lms.learning.activity.question;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
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
import javax.portlet.PortletRequest;

public class OptionsQuestionType extends BaseQuestionType{
	
	private int formatType;
	private boolean partialCorrection;

	public OptionsQuestionType(Question question) {
		super(question);
		
		JSONObject extraContent = question.getExtraContentJSON();
		
		if(extraContent != null) {
			JSONObject options = extraContent.getJSONObject(QuestionConstants.JSON_OPTIONS);
			if(options != null) {
				formatType = options.getInt(QuestionConstants.JSON_FORMAT_TYPE, OptionConstants.DEFAULT_FORMAT_TYPE);
				partialCorrection = options.getBoolean(QuestionConstants.JSON_PARTIAL_CORRECTION, false);
			}else {
				formatType = OptionConstants.DEFAULT_FORMAT_TYPE;
				partialCorrection = false;
			}
		}else {
			formatType = OptionConstants.DEFAULT_FORMAT_TYPE;
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
	public void setExtraContent(ActionRequest actionRequest) throws PortalException {
		super.setExtraContent(actionRequest);
		
		JSONObject extraContent = question.getExtraContentJSON();
		
		formatType = ParamUtil.getInteger(actionRequest, "formatType", OptionConstants.OPTION_FORMAT_TYPE_VERTICAL);
		partialCorrection = ParamUtil.getBoolean(actionRequest, "partialCorrection", QuestionConstants.DEFAULT_PARTIAL_CORRECTION);
		
		extraContent.put(QuestionConstants.JSON_FORMAT_TYPE, formatType);
		extraContent.put(QuestionConstants.JSON_PARTIAL_CORRECTION, partialCorrection);
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
	
	private long correct(List<Long> arrayAnswersId){
		long retVal = 0;
		
		List<Answer> testAnswers = AnswerLocalServiceUtil.getAnswersByQuestionId(question.getQuestionId());
		
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
	
	private static final Log log = LogFactoryUtil.getLog(OptionsQuestionType.class);
	
}
