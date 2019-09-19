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

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

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
public class SurveyResultWrapper
	extends BaseModelWrapper<SurveyResult>
	implements SurveyResult, ModelWrapper<SurveyResult> {

	public SurveyResultWrapper(SurveyResult surveyResult) {
		super(surveyResult);
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

	/**
	 * Returns the act ID of this survey result.
	 *
	 * @return the act ID of this survey result
	 */
	@Override
	public long getActId() {
		return model.getActId();
	}

	/**
	 * Returns the answer ID of this survey result.
	 *
	 * @return the answer ID of this survey result
	 */
	@Override
	public long getAnswerId() {
		return model.getAnswerId();
	}

	/**
	 * Returns the free answer of this survey result.
	 *
	 * @return the free answer of this survey result
	 */
	@Override
	public String getFreeAnswer() {
		return model.getFreeAnswer();
	}

	/**
	 * Returns the lat ID of this survey result.
	 *
	 * @return the lat ID of this survey result
	 */
	@Override
	public long getLatId() {
		return model.getLatId();
	}

	/**
	 * Returns the primary key of this survey result.
	 *
	 * @return the primary key of this survey result
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the question ID of this survey result.
	 *
	 * @return the question ID of this survey result
	 */
	@Override
	public long getQuestionId() {
		return model.getQuestionId();
	}

	/**
	 * Returns the survey result ID of this survey result.
	 *
	 * @return the survey result ID of this survey result
	 */
	@Override
	public long getSurveyResultId() {
		return model.getSurveyResultId();
	}

	/**
	 * Returns the user ID of this survey result.
	 *
	 * @return the user ID of this survey result
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this survey result.
	 *
	 * @return the user uuid of this survey result
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the uuid of this survey result.
	 *
	 * @return the uuid of this survey result
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the act ID of this survey result.
	 *
	 * @param actId the act ID of this survey result
	 */
	@Override
	public void setActId(long actId) {
		model.setActId(actId);
	}

	/**
	 * Sets the answer ID of this survey result.
	 *
	 * @param answerId the answer ID of this survey result
	 */
	@Override
	public void setAnswerId(long answerId) {
		model.setAnswerId(answerId);
	}

	/**
	 * Sets the free answer of this survey result.
	 *
	 * @param freeAnswer the free answer of this survey result
	 */
	@Override
	public void setFreeAnswer(String freeAnswer) {
		model.setFreeAnswer(freeAnswer);
	}

	/**
	 * Sets the lat ID of this survey result.
	 *
	 * @param latId the lat ID of this survey result
	 */
	@Override
	public void setLatId(long latId) {
		model.setLatId(latId);
	}

	/**
	 * Sets the primary key of this survey result.
	 *
	 * @param primaryKey the primary key of this survey result
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the question ID of this survey result.
	 *
	 * @param questionId the question ID of this survey result
	 */
	@Override
	public void setQuestionId(long questionId) {
		model.setQuestionId(questionId);
	}

	/**
	 * Sets the survey result ID of this survey result.
	 *
	 * @param surveyResultId the survey result ID of this survey result
	 */
	@Override
	public void setSurveyResultId(long surveyResultId) {
		model.setSurveyResultId(surveyResultId);
	}

	/**
	 * Sets the user ID of this survey result.
	 *
	 * @param userId the user ID of this survey result
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this survey result.
	 *
	 * @param userUuid the user uuid of this survey result
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this survey result.
	 *
	 * @param uuid the uuid of this survey result
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	@Override
	protected SurveyResultWrapper wrap(SurveyResult surveyResult) {
		return new SurveyResultWrapper(surveyResult);
	}

}