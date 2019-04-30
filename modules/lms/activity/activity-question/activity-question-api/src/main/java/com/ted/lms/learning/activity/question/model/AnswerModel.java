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

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.GroupedModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedAuditedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the Answer service. Represents a row in the &quot;qu_Answer&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.ted.lms.learning.activity.question.model.impl.AnswerModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.ted.lms.learning.activity.question.model.impl.AnswerImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Answer
 * @see com.ted.lms.learning.activity.question.model.impl.AnswerImpl
 * @see com.ted.lms.learning.activity.question.model.impl.AnswerModelImpl
 * @generated
 */
@ProviderType
public interface AnswerModel extends BaseModel<Answer>, GroupedModel,
	ShardedModel, StagedAuditedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a answer model instance should use the {@link Answer} interface instead.
	 */

	/**
	 * Returns the primary key of this answer.
	 *
	 * @return the primary key of this answer
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this answer.
	 *
	 * @param primaryKey the primary key of this answer
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this answer.
	 *
	 * @return the uuid of this answer
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this answer.
	 *
	 * @param uuid the uuid of this answer
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the answer ID of this answer.
	 *
	 * @return the answer ID of this answer
	 */
	public long getAnswerId();

	/**
	 * Sets the answer ID of this answer.
	 *
	 * @param answerId the answer ID of this answer
	 */
	public void setAnswerId(long answerId);

	/**
	 * Returns the group ID of this answer.
	 *
	 * @return the group ID of this answer
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this answer.
	 *
	 * @param groupId the group ID of this answer
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this answer.
	 *
	 * @return the company ID of this answer
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this answer.
	 *
	 * @param companyId the company ID of this answer
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this answer.
	 *
	 * @return the user ID of this answer
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this answer.
	 *
	 * @param userId the user ID of this answer
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this answer.
	 *
	 * @return the user uuid of this answer
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this answer.
	 *
	 * @param userUuid the user uuid of this answer
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this answer.
	 *
	 * @return the user name of this answer
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this answer.
	 *
	 * @param userName the user name of this answer
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this answer.
	 *
	 * @return the create date of this answer
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this answer.
	 *
	 * @param createDate the create date of this answer
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this answer.
	 *
	 * @return the modified date of this answer
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this answer.
	 *
	 * @param modifiedDate the modified date of this answer
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the question ID of this answer.
	 *
	 * @return the question ID of this answer
	 */
	public long getQuestionId();

	/**
	 * Sets the question ID of this answer.
	 *
	 * @param questionId the question ID of this answer
	 */
	public void setQuestionId(long questionId);

	/**
	 * Returns the act ID of this answer.
	 *
	 * @return the act ID of this answer
	 */
	public long getActId();

	/**
	 * Sets the act ID of this answer.
	 *
	 * @param actId the act ID of this answer
	 */
	public void setActId(long actId);

	/**
	 * Returns the answer of this answer.
	 *
	 * @return the answer of this answer
	 */
	@AutoEscape
	public String getAnswer();

	/**
	 * Sets the answer of this answer.
	 *
	 * @param answer the answer of this answer
	 */
	public void setAnswer(String answer);

	/**
	 * Returns the correct of this answer.
	 *
	 * @return the correct of this answer
	 */
	public boolean getCorrect();

	/**
	 * Returns <code>true</code> if this answer is correct.
	 *
	 * @return <code>true</code> if this answer is correct; <code>false</code> otherwise
	 */
	public boolean isCorrect();

	/**
	 * Sets whether this answer is correct.
	 *
	 * @param correct the correct of this answer
	 */
	public void setCorrect(boolean correct);

	/**
	 * Returns the feedback correct of this answer.
	 *
	 * @return the feedback correct of this answer
	 */
	@AutoEscape
	public String getFeedbackCorrect();

	/**
	 * Sets the feedback correct of this answer.
	 *
	 * @param feedbackCorrect the feedback correct of this answer
	 */
	public void setFeedbackCorrect(String feedbackCorrect);

	/**
	 * Returns the feedback incorrect of this answer.
	 *
	 * @return the feedback incorrect of this answer
	 */
	@AutoEscape
	public String getFeedbackIncorrect();

	/**
	 * Sets the feedback incorrect of this answer.
	 *
	 * @param feedbackIncorrect the feedback incorrect of this answer
	 */
	public void setFeedbackIncorrect(String feedbackIncorrect);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(Answer answer);

	@Override
	public int hashCode();

	@Override
	public CacheModel<Answer> toCacheModel();

	@Override
	public Answer toEscapedModel();

	@Override
	public Answer toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}