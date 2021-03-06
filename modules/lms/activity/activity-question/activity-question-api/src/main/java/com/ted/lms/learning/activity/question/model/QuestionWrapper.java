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
 * This class is a wrapper for {@link Question}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Question
 * @generated
 */
@ProviderType
public class QuestionWrapper
	extends BaseModelWrapper<Question>
	implements Question, ModelWrapper<Question> {

	public QuestionWrapper(Question question) {
		super(question);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("questionId", getQuestionId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("lastPublishDate", getLastPublishDate());
		attributes.put("actId", getActId());
		attributes.put("text", getText());
		attributes.put("questionTypeId", getQuestionTypeId());
		attributes.put("active", isActive());
		attributes.put("weight", getWeight());
		attributes.put("penalize", isPenalize());
		attributes.put("extraContent", getExtraContent());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long questionId = (Long)attributes.get("questionId");

		if (questionId != null) {
			setQuestionId(questionId);
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

		Long actId = (Long)attributes.get("actId");

		if (actId != null) {
			setActId(actId);
		}

		String text = (String)attributes.get("text");

		if (text != null) {
			setText(text);
		}

		Long questionTypeId = (Long)attributes.get("questionTypeId");

		if (questionTypeId != null) {
			setQuestionTypeId(questionTypeId);
		}

		Boolean active = (Boolean)attributes.get("active");

		if (active != null) {
			setActive(active);
		}

		Long weight = (Long)attributes.get("weight");

		if (weight != null) {
			setWeight(weight);
		}

		Boolean penalize = (Boolean)attributes.get("penalize");

		if (penalize != null) {
			setPenalize(penalize);
		}

		String extraContent = (String)attributes.get("extraContent");

		if (extraContent != null) {
			setExtraContent(extraContent);
		}
	}

	/**
	 * Returns the act ID of this question.
	 *
	 * @return the act ID of this question
	 */
	@Override
	public long getActId() {
		return model.getActId();
	}

	/**
	 * Returns the active of this question.
	 *
	 * @return the active of this question
	 */
	@Override
	public boolean getActive() {
		return model.getActive();
	}

	@Override
	public java.util.List<Answer> getAnswers() {
		return model.getAnswers();
	}

	/**
	 * Returns the company ID of this question.
	 *
	 * @return the company ID of this question
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this question.
	 *
	 * @return the create date of this question
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the extra content of this question.
	 *
	 * @return the extra content of this question
	 */
	@Override
	public String getExtraContent() {
		return model.getExtraContent();
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getExtraContentJSON() {
		return model.getExtraContentJSON();
	}

	/**
	 * Returns the group ID of this question.
	 *
	 * @return the group ID of this question
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the last publish date of this question.
	 *
	 * @return the last publish date of this question
	 */
	@Override
	public Date getLastPublishDate() {
		return model.getLastPublishDate();
	}

	/**
	 * Returns the modified date of this question.
	 *
	 * @return the modified date of this question
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the penalize of this question.
	 *
	 * @return the penalize of this question
	 */
	@Override
	public boolean getPenalize() {
		return model.getPenalize();
	}

	/**
	 * Returns the primary key of this question.
	 *
	 * @return the primary key of this question
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the question ID of this question.
	 *
	 * @return the question ID of this question
	 */
	@Override
	public long getQuestionId() {
		return model.getQuestionId();
	}

	@Override
	public QuestionType getQuestionType()
		throws com.liferay.portal.kernel.exception.PortalException {

		return model.getQuestionType();
	}

	/**
	 * Returns the question type ID of this question.
	 *
	 * @return the question type ID of this question
	 */
	@Override
	public long getQuestionTypeId() {
		return model.getQuestionTypeId();
	}

	/**
	 * Returns the text of this question.
	 *
	 * @return the text of this question
	 */
	@Override
	public String getText() {
		return model.getText();
	}

	/**
	 * Returns the user ID of this question.
	 *
	 * @return the user ID of this question
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this question.
	 *
	 * @return the user name of this question
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this question.
	 *
	 * @return the user uuid of this question
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the uuid of this question.
	 *
	 * @return the uuid of this question
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	/**
	 * Returns the weight of this question.
	 *
	 * @return the weight of this question
	 */
	@Override
	public long getWeight() {
		return model.getWeight();
	}

	/**
	 * Returns <code>true</code> if this question is active.
	 *
	 * @return <code>true</code> if this question is active; <code>false</code> otherwise
	 */
	@Override
	public boolean isActive() {
		return model.isActive();
	}

	/**
	 * Returns <code>true</code> if this question is penalize.
	 *
	 * @return <code>true</code> if this question is penalize; <code>false</code> otherwise
	 */
	@Override
	public boolean isPenalize() {
		return model.isPenalize();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the act ID of this question.
	 *
	 * @param actId the act ID of this question
	 */
	@Override
	public void setActId(long actId) {
		model.setActId(actId);
	}

	/**
	 * Sets whether this question is active.
	 *
	 * @param active the active of this question
	 */
	@Override
	public void setActive(boolean active) {
		model.setActive(active);
	}

	/**
	 * Sets the company ID of this question.
	 *
	 * @param companyId the company ID of this question
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this question.
	 *
	 * @param createDate the create date of this question
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the extra content of this question.
	 *
	 * @param extraContent the extra content of this question
	 */
	@Override
	public void setExtraContent(String extraContent) {
		model.setExtraContent(extraContent);
	}

	@Override
	public void setExtraContentJSON(
		com.liferay.portal.kernel.json.JSONObject extraContent) {

		model.setExtraContentJSON(extraContent);
	}

	/**
	 * Sets the group ID of this question.
	 *
	 * @param groupId the group ID of this question
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the last publish date of this question.
	 *
	 * @param lastPublishDate the last publish date of this question
	 */
	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		model.setLastPublishDate(lastPublishDate);
	}

	/**
	 * Sets the modified date of this question.
	 *
	 * @param modifiedDate the modified date of this question
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets whether this question is penalize.
	 *
	 * @param penalize the penalize of this question
	 */
	@Override
	public void setPenalize(boolean penalize) {
		model.setPenalize(penalize);
	}

	/**
	 * Sets the primary key of this question.
	 *
	 * @param primaryKey the primary key of this question
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the question ID of this question.
	 *
	 * @param questionId the question ID of this question
	 */
	@Override
	public void setQuestionId(long questionId) {
		model.setQuestionId(questionId);
	}

	/**
	 * Sets the question type ID of this question.
	 *
	 * @param questionTypeId the question type ID of this question
	 */
	@Override
	public void setQuestionTypeId(long questionTypeId) {
		model.setQuestionTypeId(questionTypeId);
	}

	/**
	 * Sets the text of this question.
	 *
	 * @param text the text of this question
	 */
	@Override
	public void setText(String text) {
		model.setText(text);
	}

	/**
	 * Sets the user ID of this question.
	 *
	 * @param userId the user ID of this question
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this question.
	 *
	 * @param userName the user name of this question
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this question.
	 *
	 * @param userUuid the user uuid of this question
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this question.
	 *
	 * @param uuid the uuid of this question
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	/**
	 * Sets the weight of this question.
	 *
	 * @param weight the weight of this question
	 */
	@Override
	public void setWeight(long weight) {
		model.setWeight(weight);
	}

	@Override
	public StagedModelType getStagedModelType() {
		return model.getStagedModelType();
	}

	@Override
	protected QuestionWrapper wrap(Question question) {
		return new QuestionWrapper(question);
	}

}