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

package com.ted.lms.learning.activity.question.model;

import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * <p>
 * This class is a wrapper for {@link Answer}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Answer
 * @generated
 */
@ProviderType
public class AnswerWrapper
	extends BaseModelWrapper<Answer> implements Answer, ModelWrapper<Answer> {

	public AnswerWrapper(Answer answer) {
		super(answer);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("answerId", getAnswerId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("lastPublishDate", getLastPublishDate());
		attributes.put("questionId", getQuestionId());
		attributes.put("actId", getActId());
		attributes.put("answer", getAnswer());
		attributes.put("correct", isCorrect());
		attributes.put("feedbackCorrect", getFeedbackCorrect());
		attributes.put("feedbackIncorrect", getFeedbackIncorrect());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long answerId = (Long)attributes.get("answerId");

		if (answerId != null) {
			setAnswerId(answerId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Date lastPublishDate = (Date)attributes.get("lastPublishDate");

		if (lastPublishDate != null) {
			setLastPublishDate(lastPublishDate);
		}

		Long questionId = (Long)attributes.get("questionId");

		if (questionId != null) {
			setQuestionId(questionId);
		}

		Long actId = (Long)attributes.get("actId");

		if (actId != null) {
			setActId(actId);
		}

		String answer = (String)attributes.get("answer");

		if (answer != null) {
			setAnswer(answer);
		}

		Boolean correct = (Boolean)attributes.get("correct");

		if (correct != null) {
			setCorrect(correct);
		}

		String feedbackCorrect = (String)attributes.get("feedbackCorrect");

		if (feedbackCorrect != null) {
			setFeedbackCorrect(feedbackCorrect);
		}

		String feedbackIncorrect = (String)attributes.get("feedbackIncorrect");

		if (feedbackIncorrect != null) {
			setFeedbackIncorrect(feedbackIncorrect);
		}
	}

	/**
	 * Returns the act ID of this answer.
	 *
	 * @return the act ID of this answer
	 */
	@Override
	public long getActId() {
		return model.getActId();
	}

	/**
	 * Returns the answer of this answer.
	 *
	 * @return the answer of this answer
	 */
	@Override
	public String getAnswer() {
		return model.getAnswer();
	}

	/**
	 * Returns the answer ID of this answer.
	 *
	 * @return the answer ID of this answer
	 */
	@Override
	public long getAnswerId() {
		return model.getAnswerId();
	}

	/**
	 * Returns the company ID of this answer.
	 *
	 * @return the company ID of this answer
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the correct of this answer.
	 *
	 * @return the correct of this answer
	 */
	@Override
	public boolean getCorrect() {
		return model.getCorrect();
	}

	/**
	 * Returns the create date of this answer.
	 *
	 * @return the create date of this answer
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the feedback correct of this answer.
	 *
	 * @return the feedback correct of this answer
	 */
	@Override
	public String getFeedbackCorrect() {
		return model.getFeedbackCorrect();
	}

	/**
	 * Returns the feedback incorrect of this answer.
	 *
	 * @return the feedback incorrect of this answer
	 */
	@Override
	public String getFeedbackIncorrect() {
		return model.getFeedbackIncorrect();
	}

	/**
	 * Returns the group ID of this answer.
	 *
	 * @return the group ID of this answer
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the last publish date of this answer.
	 *
	 * @return the last publish date of this answer
	 */
	@Override
	public Date getLastPublishDate() {
		return model.getLastPublishDate();
	}

	/**
	 * Returns the modified date of this answer.
	 *
	 * @return the modified date of this answer
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the primary key of this answer.
	 *
	 * @return the primary key of this answer
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the question ID of this answer.
	 *
	 * @return the question ID of this answer
	 */
	@Override
	public long getQuestionId() {
		return model.getQuestionId();
	}

	/**
	 * Returns the user ID of this answer.
	 *
	 * @return the user ID of this answer
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this answer.
	 *
	 * @return the user name of this answer
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this answer.
	 *
	 * @return the user uuid of this answer
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the uuid of this answer.
	 *
	 * @return the uuid of this answer
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	/**
	 * Returns <code>true</code> if this answer is correct.
	 *
	 * @return <code>true</code> if this answer is correct; <code>false</code> otherwise
	 */
	@Override
	public boolean isCorrect() {
		return model.isCorrect();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the act ID of this answer.
	 *
	 * @param actId the act ID of this answer
	 */
	@Override
	public void setActId(long actId) {
		model.setActId(actId);
	}

	/**
	 * Sets the answer of this answer.
	 *
	 * @param answer the answer of this answer
	 */
	@Override
	public void setAnswer(String answer) {
		model.setAnswer(answer);
	}

	/**
	 * Sets the answer ID of this answer.
	 *
	 * @param answerId the answer ID of this answer
	 */
	@Override
	public void setAnswerId(long answerId) {
		model.setAnswerId(answerId);
	}

	/**
	 * Sets the company ID of this answer.
	 *
	 * @param companyId the company ID of this answer
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets whether this answer is correct.
	 *
	 * @param correct the correct of this answer
	 */
	@Override
	public void setCorrect(boolean correct) {
		model.setCorrect(correct);
	}

	/**
	 * Sets the create date of this answer.
	 *
	 * @param createDate the create date of this answer
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the feedback correct of this answer.
	 *
	 * @param feedbackCorrect the feedback correct of this answer
	 */
	@Override
	public void setFeedbackCorrect(String feedbackCorrect) {
		model.setFeedbackCorrect(feedbackCorrect);
	}

	/**
	 * Sets the feedback incorrect of this answer.
	 *
	 * @param feedbackIncorrect the feedback incorrect of this answer
	 */
	@Override
	public void setFeedbackIncorrect(String feedbackIncorrect) {
		model.setFeedbackIncorrect(feedbackIncorrect);
	}

	/**
	 * Sets the group ID of this answer.
	 *
	 * @param groupId the group ID of this answer
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the last publish date of this answer.
	 *
	 * @param lastPublishDate the last publish date of this answer
	 */
	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		model.setLastPublishDate(lastPublishDate);
	}

	/**
	 * Sets the modified date of this answer.
	 *
	 * @param modifiedDate the modified date of this answer
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the primary key of this answer.
	 *
	 * @param primaryKey the primary key of this answer
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the question ID of this answer.
	 *
	 * @param questionId the question ID of this answer
	 */
	@Override
	public void setQuestionId(long questionId) {
		model.setQuestionId(questionId);
	}

	/**
	 * Sets the user ID of this answer.
	 *
	 * @param userId the user ID of this answer
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this answer.
	 *
	 * @param userName the user name of this answer
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this answer.
	 *
	 * @param userUuid the user uuid of this answer
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this answer.
	 *
	 * @param uuid the uuid of this answer
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	@Override
	public StagedModelType getStagedModelType() {
		return model.getStagedModelType();
	}

	@Override
	protected AnswerWrapper wrap(Answer answer) {
		return new AnswerWrapper(answer);
	}

}