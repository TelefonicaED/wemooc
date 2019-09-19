package com.ted.lms.learning.activity.question.model;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.xml.Element;
import com.ted.lms.learning.activity.question.exception.MinNumAnswerException;
import com.ted.lms.learning.activity.question.exception.MinNumCorrectAnswerException;
import com.ted.lms.learning.activity.question.registry.QuestionTypeFactoryRegistryUtil;
import com.ted.lms.learning.activity.question.service.AnswerLocalService;

import java.util.ArrayList;
import java.util.List;
import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;


public abstract class BaseQuestionType implements QuestionType{
	
	private static final Log log = LogFactoryUtil.getLog(BaseQuestionType.class);
	
	protected static final long CORRECT = 100;
	protected static final long INCORRECT = 0;
	protected final AnswerLocalService answerLocalService;
	
	protected Question question;
	private QuestionTypeFactory questionTypeFactory;
	
	public BaseQuestionType(Question question, AnswerLocalService answerLocalService) {
		this.question = question;
		this.answerLocalService = answerLocalService;
	}
	
	@Override
	public void setExtraContent(ActionRequest actionRequest, String iteratorQuestion) throws PortalException {
	}
	
	@Override
	public void saveAnswers(ActionRequest actionRequest, String iteratorQuestion) throws PortalException {
		//Obtengo un array con los ids de las respuestas que ya contenia la pregunta
		List<Answer> existingAnswers = answerLocalService.getAnswersByQuestionId(question.getQuestionId());
		List<Long> existingAnswersIds = new ArrayList<Long>();
		
		log.debug("*************************************************************************************************************");
		log.debug("*************************************************************************************************************");
		log.debug("iteratorQuestion: " + iteratorQuestion);
		
		for(Answer answer:existingAnswers){
			existingAnswersIds.add(answer.getAnswerId());
		}
		
		//Recorro todas las respuestas y las actualizo o las creo en funcion de si son nuevas o modificaciones y si son modificaciones guardo sus ids en un array para despues borrar las que no existan.
		String[] iteratorAnswersIds = ParamUtil.getParameterValues(actionRequest, iteratorQuestion + "_iteratorAnswer", null);
		
		List<Long> editingAnswersIds = new ArrayList<Long>();
		if(iteratorAnswersIds != null){
			
			log.debug("numAnswers: " + iteratorAnswersIds.length);
			
			int counter = 1;
			int trueCounter = 0;
			String answerText = null;
			String feedbackCorrect = null;
			String feedbackIncorrect = null;
			boolean correct = false;
			long answerId = 0;
			ServiceContext serviceContext = null;
			
			ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
			
			for(String iteratorAnswer:iteratorAnswersIds){
				
				log.debug("iteratorAnswer: " + iteratorAnswer);
				
				answerId = ParamUtil.getLong(actionRequest, iteratorQuestion + "_answerId_" + iteratorAnswer, 0);
				answerText = ParamUtil.getString(actionRequest, iteratorQuestion + "_answer_title_" + iteratorAnswer);
				
				log.debug("answerText: " + answerText);
				log.debug("answerId: " + answerId);
				
				if(Validator.isNotNull(answerText)){
					correct = isCorrectRequest(actionRequest, iteratorQuestion, counter);
					log.debug("correct: " + correct);
					counter++;
					if(correct)trueCounter++;
					
					feedbackCorrect = ParamUtil.getString(actionRequest, iteratorQuestion + "_feedbackCorrect_" + iteratorAnswer);
					feedbackIncorrect = ParamUtil.getString(actionRequest, iteratorQuestion + "_feedbackIncorrect_" + iteratorAnswer);

					if(answerId == 0){
						//creo respuesta
						serviceContext = ServiceContextFactory.getInstance(Answer.class.getName(), actionRequest);
						answerLocalService.addAnswer(themeDisplay.getUserId(), themeDisplay.getScopeGroupId(), question.getQuestionId(), 
								question.getActId(), answerText, feedbackCorrect, feedbackIncorrect, correct, serviceContext);
					}else {
						editingAnswersIds.add(answerId);//almaceno en array para posterior borrado de las que no esten
						//actualizo respuesta
						answerLocalService.updateAnswer(themeDisplay.getUserId(), answerId, answerText, feedbackCorrect, feedbackIncorrect, correct);
					}
				}
			}
			log.debug("minNumAnswers: " + getQuestionTypeFactory().getMinNumAnswers());
			log.debug("counter: " + counter);
			log.debug("trueCounter: " + trueCounter);
			log.debug("minNumCorrectAnswers: " + getQuestionTypeFactory().getMinNumCorrectAnswers());
			if(getQuestionTypeFactory().getMinNumAnswers() > counter) {
				throw new MinNumAnswerException();
			}
			if(getQuestionTypeFactory().getMinNumCorrectAnswers() > trueCounter){
				throw new MinNumCorrectAnswerException();
			}
		}
		
		log.debug("respuestas modificadas: ");
		editingAnswersIds.forEach(answerId -> log.debug("answerId: " + answerId));
		log.debug("respuestas existentes: ");
		existingAnswersIds.forEach(answerId -> log.debug("answerId: " + answerId));

		//Recorro los ids de respuestas que ya contenia y compruebo si siguen estando, si no, elimino dichas respuestas.
		for(Long existingAnswerId:existingAnswersIds){
			if(editingAnswersIds != null && editingAnswersIds.size()>0){
				if(!editingAnswersIds.contains(existingAnswerId)){
					answerLocalService.deleteAnswer(existingAnswerId);
				}
			}else {
				answerLocalService.deleteAnswer(existingAnswerId);
			}
		}
		
		
		log.debug("*************************************************************************************************************");
		log.debug("*************************************************************************************************************");
	}
	
	@Override
	public QuestionTypeFactory getQuestionTypeFactory(){
		log.debug("questionTypeFactory: " + questionTypeFactory);
		if (questionTypeFactory != null) {
			log.debug("questionTypeFactory: " + questionTypeFactory.getType());
			return questionTypeFactory;
		}

		log.debug("type: " + getType());
		
		questionTypeFactory = (QuestionTypeFactory) QuestionTypeFactoryRegistryUtil.getQuestionTypeFactoryByType(getType());

		log.debug("questionTypeFactory: " + questionTypeFactory.getType());
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
