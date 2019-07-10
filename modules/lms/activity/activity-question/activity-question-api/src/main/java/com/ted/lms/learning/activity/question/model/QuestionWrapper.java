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

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
public class QuestionWrapper implements Question, ModelWrapper<Question> {
	public QuestionWrapper(Question question) {
		_question = question;
	}

	@Override
	public Class<?> getModelClass() {
		return Question.class;
	}

	@Override
	public String getModelClassName() {
		return Question.class.getName();
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

	@Override
	public Object clone() {
		return new QuestionWrapper((Question)_question.clone());
	}

	@Override
	public int compareTo(Question question) {
		return _question.compareTo(question);
	}

	/**
	* Returns the act ID of this question.
	*
	* @return the act ID of this question
	*/
	@Override
	public long getActId() {
		return _question.getActId();
	}

	/**
	* Returns the active of this question.
	*
	* @return the active of this question
	*/
	@Override
	public boolean getActive() {
		return _question.getActive();
	}

	@Override
	public java.util.List<Answer> getAnswers() {
		return _question.getAnswers();
	}

	/**
	* Returns the company ID of this question.
	*
	* @return the company ID of this question
	*/
	@Override
	public long getCompanyId() {
		return _question.getCompanyId();
	}

	/**
	* Returns the create date of this question.
	*
	* @return the create date of this question
	*/
	@Override
	public Date getCreateDate() {
		return _question.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _question.getExpandoBridge();
	}

	/**
	* Returns the extra content of this question.
	*
	* @return the extra content of this question
	*/
	@Override
	public String getExtraContent() {
		return _question.getExtraContent();
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getExtraContentJSON() {
		return _question.getExtraContentJSON();
	}

	/**
	* Returns the group ID of this question.
	*
	* @return the group ID of this question
	*/
	@Override
	public long getGroupId() {
		return _question.getGroupId();
	}

	/**
	* Returns the last publish date of this question.
	*
	* @return the last publish date of this question
	*/
	@Override
	public Date getLastPublishDate() {
		return _question.getLastPublishDate();
	}

	/**
	* Returns the modified date of this question.
	*
	* @return the modified date of this question
	*/
	@Override
	public Date getModifiedDate() {
		return _question.getModifiedDate();
	}

	/**
	* Returns the penalize of this question.
	*
	* @return the penalize of this question
	*/
	@Override
	public boolean getPenalize() {
		return _question.getPenalize();
	}

	/**
	* Returns the primary key of this question.
	*
	* @return the primary key of this question
	*/
	@Override
	public long getPrimaryKey() {
		return _question.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _question.getPrimaryKeyObj();
	}

	/**
	* Returns the question ID of this question.
	*
	* @return the question ID of this question
	*/
	@Override
	public long getQuestionId() {
		return _question.getQuestionId();
	}

	@Override
	public QuestionType getQuestionType()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _question.getQuestionType();
	}

	/**
	* Returns the question type ID of this question.
	*
	* @return the question type ID of this question
	*/
	@Override
	public long getQuestionTypeId() {
		return _question.getQuestionTypeId();
	}

	/**
	* Returns the text of this question.
	*
	* @return the text of this question
	*/
	@Override
	public String getText() {
		return _question.getText();
	}

	/**
	* Returns the user ID of this question.
	*
	* @return the user ID of this question
	*/
	@Override
	public long getUserId() {
		return _question.getUserId();
	}

	/**
	* Returns the user name of this question.
	*
	* @return the user name of this question
	*/
	@Override
	public String getUserName() {
		return _question.getUserName();
	}

	/**
	* Returns the user uuid of this question.
	*
	* @return the user uuid of this question
	*/
	@Override
	public String getUserUuid() {
		return _question.getUserUuid();
	}

	/**
	* Returns the uuid of this question.
	*
	* @return the uuid of this question
	*/
	@Override
	public String getUuid() {
		return _question.getUuid();
	}

	/**
	* Returns the weight of this question.
	*
	* @return the weight of this question
	*/
	@Override
	public long getWeight() {
		return _question.getWeight();
	}

	@Override
	public int hashCode() {
		return _question.hashCode();
	}

	/**
	* Returns <code>true</code> if this question is active.
	*
	* @return <code>true</code> if this question is active; <code>false</code> otherwise
	*/
	@Override
	public boolean isActive() {
		return _question.isActive();
	}

	@Override
	public boolean isCachedModel() {
		return _question.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _question.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _question.isNew();
	}

	/**
	* Returns <code>true</code> if this question is penalize.
	*
	* @return <code>true</code> if this question is penalize; <code>false</code> otherwise
	*/
	@Override
	public boolean isPenalize() {
		return _question.isPenalize();
	}

	@Override
	public void persist() {
		_question.persist();
	}

	/**
	* Sets the act ID of this question.
	*
	* @param actId the act ID of this question
	*/
	@Override
	public void setActId(long actId) {
		_question.setActId(actId);
	}

	/**
	* Sets whether this question is active.
	*
	* @param active the active of this question
	*/
	@Override
	public void setActive(boolean active) {
		_question.setActive(active);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_question.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this question.
	*
	* @param companyId the company ID of this question
	*/
	@Override
	public void setCompanyId(long companyId) {
		_question.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this question.
	*
	* @param createDate the create date of this question
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_question.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_question.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_question.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_question.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the extra content of this question.
	*
	* @param extraContent the extra content of this question
	*/
	@Override
	public void setExtraContent(String extraContent) {
		_question.setExtraContent(extraContent);
	}

	@Override
	public void setExtraContentJSON(
		com.liferay.portal.kernel.json.JSONObject extraContent) {
		_question.setExtraContentJSON(extraContent);
	}

	/**
	* Sets the group ID of this question.
	*
	* @param groupId the group ID of this question
	*/
	@Override
	public void setGroupId(long groupId) {
		_question.setGroupId(groupId);
	}

	/**
	* Sets the last publish date of this question.
	*
	* @param lastPublishDate the last publish date of this question
	*/
	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		_question.setLastPublishDate(lastPublishDate);
	}

	/**
	* Sets the modified date of this question.
	*
	* @param modifiedDate the modified date of this question
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_question.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_question.setNew(n);
	}

	/**
	* Sets whether this question is penalize.
	*
	* @param penalize the penalize of this question
	*/
	@Override
	public void setPenalize(boolean penalize) {
		_question.setPenalize(penalize);
	}

	/**
	* Sets the primary key of this question.
	*
	* @param primaryKey the primary key of this question
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_question.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_question.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the question ID of this question.
	*
	* @param questionId the question ID of this question
	*/
	@Override
	public void setQuestionId(long questionId) {
		_question.setQuestionId(questionId);
	}

	/**
	* Sets the question type ID of this question.
	*
	* @param questionTypeId the question type ID of this question
	*/
	@Override
	public void setQuestionTypeId(long questionTypeId) {
		_question.setQuestionTypeId(questionTypeId);
	}

	/**
	* Sets the text of this question.
	*
	* @param text the text of this question
	*/
	@Override
	public void setText(String text) {
		_question.setText(text);
	}

	/**
	* Sets the user ID of this question.
	*
	* @param userId the user ID of this question
	*/
	@Override
	public void setUserId(long userId) {
		_question.setUserId(userId);
	}

	/**
	* Sets the user name of this question.
	*
	* @param userName the user name of this question
	*/
	@Override
	public void setUserName(String userName) {
		_question.setUserName(userName);
	}

	/**
	* Sets the user uuid of this question.
	*
	* @param userUuid the user uuid of this question
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_question.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this question.
	*
	* @param uuid the uuid of this question
	*/
	@Override
	public void setUuid(String uuid) {
		_question.setUuid(uuid);
	}

	/**
	* Sets the weight of this question.
	*
	* @param weight the weight of this question
	*/
	@Override
	public void setWeight(long weight) {
		_question.setWeight(weight);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Question> toCacheModel() {
		return _question.toCacheModel();
	}

	@Override
	public Question toEscapedModel() {
		return new QuestionWrapper(_question.toEscapedModel());
	}

	@Override
	public String toString() {
		return _question.toString();
	}

	@Override
	public Question toUnescapedModel() {
		return new QuestionWrapper(_question.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _question.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof QuestionWrapper)) {
			return false;
		}

		QuestionWrapper questionWrapper = (QuestionWrapper)obj;

		if (Objects.equals(_question, questionWrapper._question)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _question.getStagedModelType();
	}

	@Override
	public Question getWrappedModel() {
		return _question;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _question.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _question.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_question.resetOriginalValues();
	}

	private final Question _question;
}