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

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.ted.lms.learning.activity.question.model.Answer;
import com.ted.lms.learning.activity.question.service.base.AnswerLocalServiceBaseImpl;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * The implementation of the answer local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ted.lms.learning.activity.question.service.AnswerLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AnswerLocalServiceBaseImpl
 * @see com.ted.lms.learning.activity.question.service.AnswerLocalServiceUtil
 */
public class AnswerLocalServiceImpl extends AnswerLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.ted.lms.learning.activity.question.service.AnswerLocalServiceUtil} to access the answer local service.
	 */
	
	public List<Answer> getAnswersByQuestionId(long questionId){
		return answerPersistence.findByQuestionId(questionId);
	}
	
	public Answer addAnswer(long questionId, long actId, Map<Locale, String> answerMap, Map<Locale, String> feedbackCorrectMap, 
			Map<Locale, String> feedbackIncorrectMap, boolean correct, ServiceContext serviceContext) {
		Answer answer = answerPersistence.create(counterLocalService.increment(Answer.class.getName()));
		
		answer.setQuestionId(questionId);
		answer.setGroupId(serviceContext.getScopeGroupId());
		answer.setCompanyId(serviceContext.getCompanyId());
		answer.setUserId(serviceContext.getUserId());
		User user = userLocalService.fetchUser(serviceContext.getUserId());
		if(user != null) {
			answer.setUserName(user.getFullName());
		}
		answer.setCreateDate(new Date());
		answer.setModifiedDate(answer.getCreateDate());
		
		answer.setActId(actId);
		answer.setAnswerMap(answerMap);
		answer.setFeedbackCorrectMap(feedbackCorrectMap);
		answer.setFeedbackIncorrectMap(feedbackIncorrectMap);
		answer.setCorrect(correct);
		
		return answerPersistence.update(answer);
	}
	

	public Answer updateAnswer(long answerId, Map<Locale, String> answerMap, Map<Locale, String> feedbackCorrectMap,
			Map<Locale, String> feedbackIncorrectMap, boolean correct) {
		
		Answer answer = answerPersistence.fetchByPrimaryKey(answerId);
		answer.setAnswerMap(answerMap);
		answer.setFeedbackCorrectMap(feedbackCorrectMap);
		answer.setFeedbackIncorrectMap(feedbackIncorrectMap);
		answer.setCorrect(correct);
		answer.setModifiedDate(new Date());
		
		return answerPersistence.update(answer);
	}
}