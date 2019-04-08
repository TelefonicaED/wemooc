package com.ted.lms.learning.activity.question.model;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Element;
import com.ted.lms.learning.activity.question.constants.QuestionConstants;
import com.ted.lms.learning.activity.question.exception.MinNumAnswerException;
import com.ted.lms.learning.activity.question.exception.MinNumCorrectAnswerException;
import com.ted.lms.learning.activity.question.registry.QuestionTypeFactoryRegistryUtil;
import com.ted.lms.learning.activity.question.service.AnswerLocalService;
import com.ted.lms.learning.activity.question.service.AnswerLocalServiceUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;

public abstract class BaseQuestionType implements QuestionType{
	protected static final long CORRECT = 100;
	protected static final long INCORRECT = 0;
	
	protected Question question;
	private QuestionTypeFactory questionTypeFactory;
	
	public BaseQuestionType(Question question) {
		this.question = question;
	}
	
	@Override
	public void setExtraContent(ActionRequest actionRequest) throws PortalException {
	}
	
	@Override
	public void saveAnswers(ActionRequest actionRequest, String iteratorQuestion) throws PortalException {
		//Obtengo un array con los ids de las respuestas que ya contenia la pregunta
		List<Answer> existingAnswers = AnswerLocalServiceUtil.getAnswersByQuestionId(question.getQuestionId());
		List<Long> existingAnswersIds = new ArrayList<Long>();
		
		for(Answer answer:existingAnswers){
			existingAnswersIds.add(answer.getAnswerId());
		}
		
		//Recorro todas las respuestas y las actualizo o las creo en funcion de si son nuevas o modificaciones y si son modificaciones guardo sus ids en un array para despues borrar las que no existan.
		String[] newAnswersIds = ParamUtil.getParameterValues(actionRequest, iteratorQuestion + "_answerId", null);
		
		List<Long> editingAnswersIds = new ArrayList<Long>();
		if(newAnswersIds != null){
			int counter = 1;
			int trueCounter = 0;
			String answerText = null;
			Map<Locale, String> feedbackCorrectMap = null;
			Map<Locale, String> feedbackIncorrectMap = null;
			boolean correct = false;
			long answerId = 0;
			ServiceContext serviceContext = null;
			
			for(String iteratorAnswer:newAnswersIds){
				answerId = ParamUtil.getLong(actionRequest, iteratorQuestion + "_answerId_" + iteratorAnswer, 0);
				answerText = ParamUtil.getString(actionRequest, iteratorQuestion + "_answer_" + iteratorAnswer);
				
				if(Validator.isNotNull(answerText)){
					correct = isCorrectRequest(actionRequest, iteratorQuestion, counter);
					System.out.println("correct: " + correct);
					counter++;
					if(correct)trueCounter++;
					
					feedbackCorrectMap = LocalizationUtil.getLocalizationMap(actionRequest, iteratorQuestion + "_feedbackCorrectMapAsXML_" + iteratorAnswer);
					feedbackIncorrectMap = LocalizationUtil.getLocalizationMap(actionRequest, iteratorQuestion + "_feedbackInCorrectMapAsXML_" + iteratorAnswer);

					if(answerId == 0){
						//creo respuesta
						serviceContext = ServiceContextFactory.getInstance(Answer.class.getName(), actionRequest);
						AnswerLocalServiceUtil.addAnswer(question.getQuestionId(), question.getActId(), answerText, feedbackCorrectMap, 
								feedbackIncorrectMap, correct, serviceContext);
					}else {
						editingAnswersIds.add(answerId);//almaceno en array para posterior borrado de las que no esten
						//actualizo respuesta
						AnswerLocalServiceUtil.updateAnswer(answerId, answerText, feedbackCorrectMap, feedbackIncorrectMap, correct);
					}
				}
			}
			System.out.println("minNumAnswers: " + getQuestionTypeFactory().getMinNumAnswers());
			System.out.println("counter: " + counter);
			System.out.println("trueCounter: " + trueCounter);
			System.out.println("minNumCorrectAnswers: " + getQuestionTypeFactory().getMinNumCorrectAnswers());
			if(getQuestionTypeFactory().getMinNumAnswers() > counter) {
				throw new MinNumAnswerException();
			}
			if(getQuestionTypeFactory().getMinNumCorrectAnswers() > trueCounter){
				throw new MinNumCorrectAnswerException();
			}
		}

		//Recorro los ids de respuestas que ya contenia y compruebo si siguen estando, si no, elimino dichas respuestas.
		for(Long existingAnswerId:existingAnswersIds){
			if(editingAnswersIds != null && editingAnswersIds.size()>0){
				if(!editingAnswersIds.contains(existingAnswerId)){
					AnswerLocalServiceUtil.deleteAnswer(existingAnswerId);
				}
			}else {
				AnswerLocalServiceUtil.deleteAnswer(existingAnswerId);
			}
		}
	}
	
	@Override
	public QuestionTypeFactory getQuestionTypeFactory(){
		if (questionTypeFactory != null) {
			return questionTypeFactory;
		}

		questionTypeFactory = (QuestionTypeFactory) QuestionTypeFactoryRegistryUtil.getQuestionTypeFactoryByType(getType());

		return questionTypeFactory;
	}
	
	@Override
	public Element getResults(PortletRequest portletRequest){
		return null;
	}
	
	@Override
	public boolean isCorrectRequest(ActionRequest actionRequest, String iteratorQuestion, int counter) {
		return true;
	}
}
