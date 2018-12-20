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
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ted.lms.learning.activity.question.model.Question;
import com.ted.lms.learning.activity.question.model.QuestionType;
import com.ted.lms.learning.activity.question.model.QuestionTypeFactory;
import com.ted.lms.learning.activity.question.registry.QuestionTypeFactoryRegistryUtil;
import com.ted.lms.learning.activity.question.service.base.QuestionLocalServiceBaseImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
	
	@Override
	public Question addQuestion(long actId, Map<Locale, String> questionTextMap, long questionType, boolean penalize, ServiceContext serviceContext) {
		Question question = questionPersistence.create(counterLocalService.increment(Question.class.getName()));
		
		//Auditoria
		question.setGroupId(serviceContext.getScopeGroupId());
		question.setCompanyId(serviceContext.getCompanyId());
		question.setUserId(serviceContext.getUserId());
		User user = userLocalService.fetchUser(serviceContext.getUserId());
		if(user != null) {
			question.setUserName(user.getFullName());
		}
		question.setCreateDate(new Date());
		question.setModifiedDate(question.getCreateDate());
		
		question.setTextMap(questionTextMap);
		question.setPenalize(penalize);
		question.setQuestionType(questionType);
		question.setActId(actId);
		question.setWeight(question.getQuestionId());
		
		return questionPersistence.update(question);
		
	}
	
	@Override
	public Question updateQuestion(long questionId, Map<Locale, String> questionTextMap, boolean penalize) {
		Question question = questionPersistence.fetchByPrimaryKey(questionId);
		
		question.setModifiedDate(new Date());
		question.setTextMap(questionTextMap);
		question.setPenalize(penalize);
		question.setModifiedDate(new Date());
		
		return questionPersistence.update(question);
	}
	
	public void saveQuestions(ActionRequest actionRequest, long actId) throws PortalException {
		
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
		Map<Locale, String> questionMap = null;
		Question question = null;
		
		boolean penalize = false;
		QuestionTypeFactory questionTypeFactory  = null;
		QuestionType questionType = null;
		
		ServiceContext serviceContext = ServiceContextFactory.getInstance(Question.class.getName(), actionRequest);
		
		for(String iteratorQuestion: iteratorQuestionIds) {
			
			questionId = ParamUtil.getLong(actionRequest, "questionId_" + iteratorQuestion, 0);
			questionTypeId = ParamUtil.getLong(actionRequest, "questionType_" + iteratorQuestion);
			penalize = ParamUtil.getBoolean(actionRequest, "penalize_" + iteratorQuestion, false);
			
			questionTypeFactory = QuestionTypeFactoryRegistryUtil.getQuestionTypeFactoryByType(questionTypeId);
			
			questionMap = LocalizationUtil.getLocalizationMap(actionRequest, "questionTitleMapAsXML" + iteratorQuestion);
			
			log.debug("***penalize:"+penalize);			
			log.debug("***questionId:"+questionId);
			log.debug("***questionType: " + questionType);
			log.debug("***questionText: " + questionMap.toString());
			
			if(questionId == 0){
				question = questionLocalService.addQuestion(actId, questionMap, questionTypeId, penalize, serviceContext);
			}else{
				question = questionLocalService.updateQuestion(questionId, questionMap, penalize);
			}
			
			questionType = questionTypeFactory.getQuestionType(question);
			questionType.setExtraContent(actionRequest);

			questionLocalService.updateQuestion(question);
			editingQuestionIds.add(question.getQuestionId());
			
			log.debug("question.getQuestionType() " + question.getQuestionType());
			
			//Cada tipo de pregunta guarda sus respuestas
			questionType.saveAnswers(actionRequest, iteratorQuestion);
			
		}
		
		//Eliminamos las preguntas que no estén	
		for(Long existingQuestionId:existingQuestionIds){
			if(editingQuestionIds != null && editingQuestionIds.size()>0){
				if(!editingQuestionIds.contains(existingQuestionId)){
					answerLocalService.deleteAnswer(existingQuestionId);
				}
			}else {
				answerLocalService.deleteAnswer(existingQuestionId);
			}
		}
		
		log.debug("questionType: " + questionType);
	}
	
}