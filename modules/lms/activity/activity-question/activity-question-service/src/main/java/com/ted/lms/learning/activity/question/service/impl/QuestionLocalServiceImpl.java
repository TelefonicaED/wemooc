/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.ted.lms.learning.activity.question.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.spring.extender.service.ServiceReference;
import com.liferay.upload.AttachmentContentUpdater;
import com.ted.lms.copy.content.processor.DLReferencesCopyContentProcessor;
import com.ted.lms.learning.activity.question.model.Answer;
import com.ted.lms.learning.activity.question.model.Question;
import com.ted.lms.learning.activity.question.model.QuestionType;
import com.ted.lms.learning.activity.question.model.QuestionTypeFactory;
import com.ted.lms.learning.activity.question.registry.QuestionTypeFactoryRegistryUtil;
import com.ted.lms.learning.activity.question.service.base.QuestionLocalServiceBaseImpl;

import java.util.ArrayList;
import java.util.List;
import javax.portlet.ActionRequest;

/**
 * The implementation of the question local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ted.lms.learning.activity.question.service.QuestionLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see QuestionLocalServiceBaseImpl
 * @see com.ted.lms.learning.activity.question.service.QuestionLocalServiceUtil
 */
public class QuestionLocalServiceImpl extends QuestionLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.ted.lms.learning.activity.question.service.QuestionLocalServiceUtil} to access the question local service.
	 */
	
	private static final Log log = LogFactoryUtil.getLog(QuestionLocalServiceImpl.class);
	
	public List<Question> getQuestions(long actId){
		return questionPersistence.findByActId(actId);
	}
	
	public int getQuestionsCount(long actId){
		return questionPersistence.countByActId(actId);
	}
	
	public List<Question> getQuestionsOrder(long actId){
		OrderByComparator<Question> orderByComparator = OrderByComparatorFactoryUtil.create("qu_question", "weight", true);
		return questionPersistence.findByActId(actId, -1, -1, orderByComparator);
	}
	
	@Override
	public Question addQuestion(long userId, long groupId, long actId, String questionText, long questionType, boolean penalize, 
			ServiceContext serviceContext) throws PortalException {
		
		Question question = questionPersistence.create(counterLocalService.increment(Question.class.getName()));
		
		//Auditoria
		question.setUuid(serviceContext.getUuid());
		question.setGroupId(groupId);
		question.setCompanyId(serviceContext.getCompanyId());
		question.setUserId(userId);
		User user = userLocalService.getUser(userId);
		question.setUserName(user.getFullName());
		question.setCompanyId(user.getCompanyId());

		question.setText(questionText);
		
		question.setPenalize(penalize);
		question.setQuestionTypeId(questionType);
		question.setActId(actId);
		question.setWeight(question.getQuestionId());
		
		return questionPersistence.update(question);
		
	}
	
	@Override
	public Question updateQuestion(long userId, long questionId, String questionText, boolean penalize) throws PortalException {
		Question question = questionPersistence.fetchByPrimaryKey(questionId);

		User user = userLocalService.getUser(userId);
		
		question.setText(questionText);
		question.setPenalize(penalize);
		question.setUserId(userId);
		question.setUserName(user.getFullName());
		
		return questionPersistence.update(question);
	}
	
	public Question copyQuestion(long userId, long groupId, long actId, Question oldQuestion, ServiceContext serviceContext) throws Exception {
		
		serviceContext.setUuid(oldQuestion.getUuid());
		
		Question newQuestion = addQuestion(userId, groupId, actId, oldQuestion.getText(), oldQuestion.getQuestionTypeId(), 
				oldQuestion.isPenalize(), serviceContext);
		List<Answer> oldAnswers = answerLocalService.getAnswersByQuestionId(oldQuestion.getQuestionId());
		for(Answer oldAnswer: oldAnswers) {
			serviceContext.setUuid(oldAnswer.getUuid());
			answerLocalService.copyAnswer(userId, groupId, newQuestion.getQuestionId(), actId, oldAnswer, serviceContext);
		}
		copyQuestionImages(oldQuestion, newQuestion);
		
		newQuestion = questionPersistence.update(newQuestion);
		
		return newQuestion;
	}
	
	public void copyQuestionImages(Question oldQuestion, Question newQuestion) throws Exception {
		newQuestion.setText(dlReferencesCopyContentProcessor.replaceExportDLReferences(newQuestion.getText(), oldQuestion.getGroupId(), newQuestion.getGroupId(), newQuestion.getUserId()));
	}

	
	public void saveQuestions(ActionRequest actionRequest, long actId) throws PortalException {
		
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		//Pedimos las preguntas que ya existían por si alguna ha sido borrada eliminarla
		List<Question> existingQuestions = questionPersistence.findByActId(actId);
		List<Long> existingQuestionIds = new ArrayList<Long>();
		
		for(Question question: existingQuestions){
			existingQuestionIds.add(question.getQuestionId());
		}
		
		//Recorro todas las respuestas y las actualizo o las creo en funcion de si son nuevas o modificaciones y si son modificaciones guardo sus ids en un array para despues borrar las que no existan.
		String[] iteratorQuestionIds = ParamUtil.getParameterValues(actionRequest, "iteratorQuestion", null);
		List<Long> editingQuestionIds = new ArrayList<Long>();
		
		long questionId = 0;
		long questionTypeId = 0;
		String questionText = null;
		Question question = null;
		
		boolean penalize = false;
		QuestionTypeFactory questionTypeFactory  = null;
		QuestionType questionType = null;
		
		ServiceContext serviceContext = ServiceContextFactory.getInstance(Question.class.getName(), actionRequest);
		
		if(Validator.isNotNull(iteratorQuestionIds)) {
		
			for(String iteratorQuestion: iteratorQuestionIds) {
				
				questionId = ParamUtil.getLong(actionRequest, "questionId_" + iteratorQuestion, 0);
				questionTypeId = ParamUtil.getLong(actionRequest, "questionType_" + iteratorQuestion);
				penalize = ParamUtil.getBoolean(actionRequest, "penalize_" + iteratorQuestion, false);
				
				questionTypeFactory = QuestionTypeFactoryRegistryUtil.getQuestionTypeFactoryByType(questionTypeId);
				
				questionText = ParamUtil.getString(actionRequest, "question_title_" + iteratorQuestion);
				
				log.debug("***penalize:"+penalize);			
				log.debug("***questionId:"+questionId);
				log.debug("***questionType: " + questionType);
				log.debug("***questionText: " + questionText);
				
				if(questionId == 0){
					question = addQuestion(themeDisplay.getUserId(), themeDisplay.getScopeGroupId(), actId, questionText, questionTypeId, penalize, serviceContext);
				}else{
					question = updateQuestion(serviceContext.getUserId(), questionId, questionText, penalize);
				}
				
				questionType = questionTypeFactory.getQuestionType(question);
				questionType.setExtraContent(actionRequest, iteratorQuestion);
	
				questionPersistence.update(question);
				editingQuestionIds.add(question.getQuestionId());
				
				log.debug("question.getQuestionType() " + question.getQuestionTypeId());
				
				//Cada tipo de pregunta guarda sus respuestas
				questionType.saveAnswers(actionRequest, iteratorQuestion);
			}
		}
		
		//Eliminamos las preguntas que no estén	
		for(Long existingQuestionId:existingQuestionIds){
			if(editingQuestionIds != null && editingQuestionIds.size()>0){
				if(!editingQuestionIds.contains(existingQuestionId)){
					questionPersistence.remove(existingQuestionId);
				}
			}else {
				questionPersistence.remove(existingQuestionId);
			}
		}
		
		log.debug("questionType: " + questionType);
	}
	
	@ServiceReference(type = AttachmentContentUpdater.class)
	private AttachmentContentUpdater attachmentContentUpdater;
	
	@ServiceReference(type = DLReferencesCopyContentProcessor.class)
	protected DLReferencesCopyContentProcessor dlReferencesCopyContentProcessor;
	
	
	
}