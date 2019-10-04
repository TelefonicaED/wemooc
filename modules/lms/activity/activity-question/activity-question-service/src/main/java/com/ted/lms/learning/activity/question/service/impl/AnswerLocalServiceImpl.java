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

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.ted.lms.copy.content.processor.DLReferencesCopyContentProcessor;
import com.ted.lms.learning.activity.question.model.Answer;
import com.ted.lms.learning.activity.question.service.base.AnswerLocalServiceBaseImpl;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * The implementation of the answer local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.ted.lms.learning.activity.question.service.AnswerLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AnswerLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=com.ted.lms.learning.activity.question.model.Answer",
	service = AopService.class
)
public class AnswerLocalServiceImpl extends AnswerLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>com.ted.lms.learning.activity.question.service.AnswerLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.ted.lms.learning.activity.question.service.AnswerLocalServiceUtil</code>.
	 */
	
	public List<Answer> getAnswersByQuestionId(long questionId){
		return answerPersistence.findByQuestionId(questionId);
	}
	
	public int getAnswersByQuestionIdCount(long questionId) {
		return answerPersistence.countByQuestionId(questionId);
	}
	
	public Answer addAnswer(long userId, long groupId, long questionId, long actId, String answerText, String feedbackCorrect, 
			String feedbackIncorrect, boolean correct, ServiceContext serviceContext) throws PortalException {
		User user = userLocalService.getUser(userId);
		
		Answer answer = answerPersistence.create(counterLocalService.increment(Answer.class.getName()));
		
		answer.setUuid(serviceContext.getUuid());
		answer.setQuestionId(questionId);
		answer.setGroupId(groupId);
		answer.setUserId(userId);
		answer.setUserName(user.getFullName());
		answer.setCompanyId(user.getCompanyId());
		
		answer.setActId(actId);
		answer.setAnswer(answerText);
		answer.setFeedbackCorrect(feedbackCorrect);
		answer.setFeedbackIncorrect(feedbackIncorrect);
		answer.setCorrect(correct);
		
		return answerPersistence.update(answer, serviceContext);
	}
	

	public Answer updateAnswer(long userId, long answerId, String answerText, String feedbackCorrect,
			String feedbackIncorrect, boolean correct) throws PortalException {
		
		User user = userLocalService.getUser(userId);
		
		Answer answer = answerPersistence.fetchByPrimaryKey(answerId);
		answer.setAnswer(answerText);
		answer.setFeedbackCorrect(feedbackCorrect);
		answer.setFeedbackIncorrect(feedbackIncorrect);
		answer.setCorrect(correct);
		answer.setUserId(userId);
		answer.setUserName(user.getFullName());
		
		return answerPersistence.update(answer);
	}
	
	public Answer copyAnswer(long userId, long groupId, long questionId, long actId, Answer oldAnswer, ServiceContext serviceContext) throws Exception {
		serviceContext.setUuid(oldAnswer.getUuid());
		Answer newAnswer = addAnswer(userId, groupId, questionId, actId, oldAnswer.getAnswer(), oldAnswer.getFeedbackCorrect(),
				oldAnswer.getFeedbackIncorrect(), oldAnswer.isCorrect(), serviceContext);
		
		copyAnswerImages(oldAnswer, newAnswer);
		
		return answerPersistence.update(newAnswer);
	}
	
	public void copyAnswerImages(Answer oldAnswer, Answer newAnswer) throws Exception {
		newAnswer.setAnswer(dlReferencesCopyContentProcessor.replaceExportDLReferences(newAnswer.getAnswer(), oldAnswer.getGroupId(), newAnswer.getGroupId(), newAnswer.getUserId()));
	}
	
	@Reference
	protected DLReferencesCopyContentProcessor dlReferencesCopyContentProcessor;
}