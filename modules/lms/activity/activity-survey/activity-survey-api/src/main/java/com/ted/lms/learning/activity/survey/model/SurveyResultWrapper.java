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

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link SurveyResult}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SurveyResult
 * @generated
 */
@ProviderType
public class SurveyResultWrapper implements SurveyResult,
	ModelWrapper<SurveyResult> {
	public SurveyResultWrapper(SurveyResult surveyResult) {
		_surveyResult = surveyResult;
	}

	@Override
	public Class<?> getModelClass() {
		return SurveyResult.class;
	}

	@Override
	public String getModelClassName() {
		return SurveyResult.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("surveyResultId", getSurveyResultId());
		attributes.put("actId", getActId());
		attributes.put("latId", getLatId());
		attributes.put("questionId", getQuestionId());
		attributes.put("answerId", getAnswerId());
		attributes.put("userId", getUserId());
		attributes.put("freeAnswer", getFreeAnswer());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long surveyResultId = (Long)attributes.get("surveyResultId");

		if (surveyResultId != null) {
			setSurveyResultId(surveyResultId);
		}

		Long actId = (Long)attributes.get("actId");

		if (actId != null) {
			setActId(actId);
		}

		Long latId = (Long)attributes.get("latId");

		if (latId != null) {
			setLatId(latId);
		}

		Long questionId = (Long)attributes.get("questionId");

		if (questionId != null) {
			setQuestionId(questionId);
		}

		Long answerId = (Long)attributes.get("answerId");

		if (answerId != null) {
			setAnswerId(answerId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String freeAnswer = (String)attributes.get("freeAnswer");

		if (freeAnswer != null) {
			setFreeAnswer(freeAnswer);
		}
	}

	@Override
	public Object clone() {
		return new SurveyResultWrapper((SurveyResult)_surveyResult.clone());
	}

	@Override
	public int compareTo(SurveyResult surveyResult) {
		return _surveyResult.compareTo(surveyResult);
	}

	/**
	* Returns the act ID of this survey result.
	*
	* @return the act ID of this survey result
	*/
	@Override
	public long getActId() {
		return _surveyResult.getActId();
	}

	/**
	* Returns the answer ID of this survey result.
	*
	* @return the answer ID of this survey result
	*/
	@Override
	public long getAnswerId() {
		return _surveyResult.getAnswerId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _surveyResult.getExpandoBridge();
	}

	/**
	* Returns the free answer of this survey result.
	*
	* @return the free answer of this survey result
	*/
	@Override
	public String getFreeAnswer() {
		return _surveyResult.getFreeAnswer();
	}

	/**
	* Returns the lat ID of this survey result.
	*
	* @return the lat ID of this survey result
	*/
	@Override
	public long getLatId() {
		return _surveyResult.getLatId();
	}

	/**
	* Returns the primary key of this survey result.
	*
	* @return the primary key of this survey result
	*/
	@Override
	public long getPrimaryKey() {
		return _surveyResult.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _surveyResult.getPrimaryKeyObj();
	}

	/**
	* Returns the question ID of this survey result.
	*
	* @return the question ID of this survey result
	*/
	@Override
	public long getQuestionId() {
		return _surveyResult.getQuestionId();
	}

	/**
	* Returns the survey result ID of this survey result.
	*
	* @return the survey result ID of this survey result
	*/
	@Override
	public long getSurveyResultId() {
		return _surveyResult.getSurveyResultId();
	}

	/**
	* Returns the user ID of this survey result.
	*
	* @return the user ID of this survey result
	*/
	@Override
	public long getUserId() {
		return _surveyResult.getUserId();
	}

	/**
	* Returns the user uuid of this survey result.
	*
	* @return the user uuid of this survey result
	*/
	@Override
	public String getUserUuid() {
		return _surveyResult.getUserUuid();
	}

	/**
	* Returns the uuid of this survey result.
	*
	* @return the uuid of this survey result
	*/
	@Override
	public String getUuid() {
		return _surveyResult.getUuid();
	}

	@Override
	public int hashCode() {
		return _surveyResult.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _surveyResult.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _surveyResult.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _surveyResult.isNew();
	}

	@Override
	public void persist() {
		_surveyResult.persist();
	}

	/**
	* Sets the act ID of this survey result.
	*
	* @param actId the act ID of this survey result
	*/
	@Override
	public void setActId(long actId) {
		_surveyResult.setActId(actId);
	}

	/**
	* Sets the answer ID of this survey result.
	*
	* @param answerId the answer ID of this survey result
	*/
	@Override
	public void setAnswerId(long answerId) {
		_surveyResult.setAnswerId(answerId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_surveyResult.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_surveyResult.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_surveyResult.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_surveyResult.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the free answer of this survey result.
	*
	* @param freeAnswer the free answer of this survey result
	*/
	@Override
	public void setFreeAnswer(String freeAnswer) {
		_surveyResult.setFreeAnswer(freeAnswer);
	}

	/**
	* Sets the lat ID of this survey result.
	*
	* @param latId the lat ID of this survey result
	*/
	@Override
	public void setLatId(long latId) {
		_surveyResult.setLatId(latId);
	}

	@Override
	public void setNew(boolean n) {
		_surveyResult.setNew(n);
	}

	/**
	* Sets the primary key of this survey result.
	*
	* @param primaryKey the primary key of this survey result
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_surveyResult.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_surveyResult.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the question ID of this survey result.
	*
	* @param questionId the question ID of this survey result
	*/
	@Override
	public void setQuestionId(long questionId) {
		_surveyResult.setQuestionId(questionId);
	}

	/**
	* Sets the survey result ID of this survey result.
	*
	* @param surveyResultId the survey result ID of this survey result
	*/
	@Override
	public void setSurveyResultId(long surveyResultId) {
		_surveyResult.setSurveyResultId(surveyResultId);
	}

	/**
	* Sets the user ID of this survey result.
	*
	* @param userId the user ID of this survey result
	*/
	@Override
	public void setUserId(long userId) {
		_surveyResult.setUserId(userId);
	}

	/**
	* Sets the user uuid of this survey result.
	*
	* @param userUuid the user uuid of this survey result
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_surveyResult.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this survey result.
	*
	* @param uuid the uuid of this survey result
	*/
	@Override
	public void setUuid(String uuid) {
		_surveyResult.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<SurveyResult> toCacheModel() {
		return _surveyResult.toCacheModel();
	}

	@Override
	public SurveyResult toEscapedModel() {
		return new SurveyResultWrapper(_surveyResult.toEscapedModel());
	}

	@Override
	public String toString() {
		return _surveyResult.toString();
	}

	@Override
	public SurveyResult toUnescapedModel() {
		return new SurveyResultWrapper(_surveyResult.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _surveyResult.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SurveyResultWrapper)) {
			return false;
		}

		SurveyResultWrapper surveyResultWrapper = (SurveyResultWrapper)obj;

		if (Objects.equals(_surveyResult, surveyResultWrapper._surveyResult)) {
			return true;
		}

		return false;
	}

	@Override
	public SurveyResult getWrappedModel() {
		return _surveyResult;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _surveyResult.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _surveyResult.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_surveyResult.resetOriginalValues();
	}

	private final SurveyResult _surveyResult;
}