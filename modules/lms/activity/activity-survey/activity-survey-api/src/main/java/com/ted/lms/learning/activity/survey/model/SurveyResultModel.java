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

package com.ted.lms.learning.activity.survey.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

/**
 * The base model interface for the SurveyResult service. Represents a row in the &quot;Survey_SurveyResult&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.ted.lms.learning.activity.survey.model.impl.SurveyResultModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.ted.lms.learning.activity.survey.model.impl.SurveyResultImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SurveyResult
 * @see com.ted.lms.learning.activity.survey.model.impl.SurveyResultImpl
 * @see com.ted.lms.learning.activity.survey.model.impl.SurveyResultModelImpl
 * @generated
 */
@ProviderType
public interface SurveyResultModel extends BaseModel<SurveyResult> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a survey result model instance should use the {@link SurveyResult} interface instead.
	 */

	/**
	 * Returns the primary key of this survey result.
	 *
	 * @return the primary key of this survey result
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this survey result.
	 *
	 * @param primaryKey the primary key of this survey result
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this survey result.
	 *
	 * @return the uuid of this survey result
	 */
	@AutoEscape
	public String getUuid();

	/**
	 * Sets the uuid of this survey result.
	 *
	 * @param uuid the uuid of this survey result
	 */
	public void setUuid(String uuid);

	/**
	 * Returns the survey result ID of this survey result.
	 *
	 * @return the survey result ID of this survey result
	 */
	public long getSurveyResultId();

	/**
	 * Sets the survey result ID of this survey result.
	 *
	 * @param surveyResultId the survey result ID of this survey result
	 */
	public void setSurveyResultId(long surveyResultId);

	/**
	 * Returns the act ID of this survey result.
	 *
	 * @return the act ID of this survey result
	 */
	public long getActId();

	/**
	 * Sets the act ID of this survey result.
	 *
	 * @param actId the act ID of this survey result
	 */
	public void setActId(long actId);

	/**
	 * Returns the lat ID of this survey result.
	 *
	 * @return the lat ID of this survey result
	 */
	public long getLatId();

	/**
	 * Sets the lat ID of this survey result.
	 *
	 * @param latId the lat ID of this survey result
	 */
	public void setLatId(long latId);

	/**
	 * Returns the question ID of this survey result.
	 *
	 * @return the question ID of this survey result
	 */
	public long getQuestionId();

	/**
	 * Sets the question ID of this survey result.
	 *
	 * @param questionId the question ID of this survey result
	 */
	public void setQuestionId(long questionId);

	/**
	 * Returns the answer ID of this survey result.
	 *
	 * @return the answer ID of this survey result
	 */
	public long getAnswerId();

	/**
	 * Sets the answer ID of this survey result.
	 *
	 * @param answerId the answer ID of this survey result
	 */
	public void setAnswerId(long answerId);

	/**
	 * Returns the user ID of this survey result.
	 *
	 * @return the user ID of this survey result
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this survey result.
	 *
	 * @param userId the user ID of this survey result
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this survey result.
	 *
	 * @return the user uuid of this survey result
	 */
	public String getUserUuid();

	/**
	 * Sets the user uuid of this survey result.
	 *
	 * @param userUuid the user uuid of this survey result
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the free answer of this survey result.
	 *
	 * @return the free answer of this survey result
	 */
	@AutoEscape
	public String getFreeAnswer();

	/**
	 * Sets the free answer of this survey result.
	 *
	 * @param freeAnswer the free answer of this survey result
	 */
	public void setFreeAnswer(String freeAnswer);

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
	public int compareTo(SurveyResult surveyResult);

	@Override
	public int hashCode();

	@Override
	public CacheModel<SurveyResult> toCacheModel();

	@Override
	public SurveyResult toEscapedModel();

	@Override
	public SurveyResult toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}