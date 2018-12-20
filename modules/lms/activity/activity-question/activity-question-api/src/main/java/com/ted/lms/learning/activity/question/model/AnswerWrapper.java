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
 * This class is a wrapper for {@link Answer}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Answer
 * @generated
 */
@ProviderType
public class AnswerWrapper implements Answer, ModelWrapper<Answer> {
	public AnswerWrapper(Answer answer) {
		_answer = answer;
	}

	@Override
	public Class<?> getModelClass() {
		return Answer.class;
	}

	@Override
	public String getModelClassName() {
		return Answer.class.getName();
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

	@Override
	public Object clone() {
		return new AnswerWrapper((Answer)_answer.clone());
	}

	@Override
	public int compareTo(Answer answer) {
		return _answer.compareTo(answer);
	}

	/**
	* Returns the act ID of this answer.
	*
	* @return the act ID of this answer
	*/
	@Override
	public long getActId() {
		return _answer.getActId();
	}

	/**
	* Returns the answer of this answer.
	*
	* @return the answer of this answer
	*/
	@Override
	public String getAnswer() {
		return _answer.getAnswer();
	}

	/**
	* Returns the localized answer of this answer in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized answer of this answer
	*/
	@Override
	public String getAnswer(java.util.Locale locale) {
		return _answer.getAnswer(locale);
	}

	/**
	* Returns the localized answer of this answer in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized answer of this answer. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public String getAnswer(java.util.Locale locale, boolean useDefault) {
		return _answer.getAnswer(locale, useDefault);
	}

	/**
	* Returns the localized answer of this answer in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized answer of this answer
	*/
	@Override
	public String getAnswer(String languageId) {
		return _answer.getAnswer(languageId);
	}

	/**
	* Returns the localized answer of this answer in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized answer of this answer
	*/
	@Override
	public String getAnswer(String languageId, boolean useDefault) {
		return _answer.getAnswer(languageId, useDefault);
	}

	@Override
	public String getAnswerCurrentLanguageId() {
		return _answer.getAnswerCurrentLanguageId();
	}

	@Override
	public String getAnswerCurrentValue() {
		return _answer.getAnswerCurrentValue();
	}

	/**
	* Returns the answer ID of this answer.
	*
	* @return the answer ID of this answer
	*/
	@Override
	public long getAnswerId() {
		return _answer.getAnswerId();
	}

	/**
	* Returns a map of the locales and localized answers of this answer.
	*
	* @return the locales and localized answers of this answer
	*/
	@Override
	public Map<java.util.Locale, String> getAnswerMap() {
		return _answer.getAnswerMap();
	}

	@Override
	public String getAnswerMapAsXML() {
		return _answer.getAnswerMapAsXML();
	}

	@Override
	public String[] getAvailableLanguageIds() {
		return _answer.getAvailableLanguageIds();
	}

	/**
	* Returns the company ID of this answer.
	*
	* @return the company ID of this answer
	*/
	@Override
	public long getCompanyId() {
		return _answer.getCompanyId();
	}

	/**
	* Returns the correct of this answer.
	*
	* @return the correct of this answer
	*/
	@Override
	public boolean getCorrect() {
		return _answer.getCorrect();
	}

	/**
	* Returns the create date of this answer.
	*
	* @return the create date of this answer
	*/
	@Override
	public Date getCreateDate() {
		return _answer.getCreateDate();
	}

	@Override
	public String getDefaultLanguageId() {
		return _answer.getDefaultLanguageId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _answer.getExpandoBridge();
	}

	/**
	* Returns the feedback correct of this answer.
	*
	* @return the feedback correct of this answer
	*/
	@Override
	public String getFeedbackCorrect() {
		return _answer.getFeedbackCorrect();
	}

	/**
	* Returns the localized feedback correct of this answer in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized feedback correct of this answer
	*/
	@Override
	public String getFeedbackCorrect(java.util.Locale locale) {
		return _answer.getFeedbackCorrect(locale);
	}

	/**
	* Returns the localized feedback correct of this answer in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized feedback correct of this answer. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public String getFeedbackCorrect(java.util.Locale locale, boolean useDefault) {
		return _answer.getFeedbackCorrect(locale, useDefault);
	}

	/**
	* Returns the localized feedback correct of this answer in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized feedback correct of this answer
	*/
	@Override
	public String getFeedbackCorrect(String languageId) {
		return _answer.getFeedbackCorrect(languageId);
	}

	/**
	* Returns the localized feedback correct of this answer in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized feedback correct of this answer
	*/
	@Override
	public String getFeedbackCorrect(String languageId, boolean useDefault) {
		return _answer.getFeedbackCorrect(languageId, useDefault);
	}

	@Override
	public String getFeedbackCorrectCurrentLanguageId() {
		return _answer.getFeedbackCorrectCurrentLanguageId();
	}

	@Override
	public String getFeedbackCorrectCurrentValue() {
		return _answer.getFeedbackCorrectCurrentValue();
	}

	/**
	* Returns a map of the locales and localized feedback corrects of this answer.
	*
	* @return the locales and localized feedback corrects of this answer
	*/
	@Override
	public Map<java.util.Locale, String> getFeedbackCorrectMap() {
		return _answer.getFeedbackCorrectMap();
	}

	@Override
	public String getFeedbackCorrectMapAsXML() {
		return _answer.getFeedbackCorrectMapAsXML();
	}

	/**
	* Returns the feedback incorrect of this answer.
	*
	* @return the feedback incorrect of this answer
	*/
	@Override
	public String getFeedbackIncorrect() {
		return _answer.getFeedbackIncorrect();
	}

	/**
	* Returns the localized feedback incorrect of this answer in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized feedback incorrect of this answer
	*/
	@Override
	public String getFeedbackIncorrect(java.util.Locale locale) {
		return _answer.getFeedbackIncorrect(locale);
	}

	/**
	* Returns the localized feedback incorrect of this answer in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized feedback incorrect of this answer. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public String getFeedbackIncorrect(java.util.Locale locale,
		boolean useDefault) {
		return _answer.getFeedbackIncorrect(locale, useDefault);
	}

	/**
	* Returns the localized feedback incorrect of this answer in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized feedback incorrect of this answer
	*/
	@Override
	public String getFeedbackIncorrect(String languageId) {
		return _answer.getFeedbackIncorrect(languageId);
	}

	/**
	* Returns the localized feedback incorrect of this answer in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized feedback incorrect of this answer
	*/
	@Override
	public String getFeedbackIncorrect(String languageId, boolean useDefault) {
		return _answer.getFeedbackIncorrect(languageId, useDefault);
	}

	@Override
	public String getFeedbackIncorrectCurrentLanguageId() {
		return _answer.getFeedbackIncorrectCurrentLanguageId();
	}

	@Override
	public String getFeedbackIncorrectCurrentValue() {
		return _answer.getFeedbackIncorrectCurrentValue();
	}

	/**
	* Returns a map of the locales and localized feedback incorrects of this answer.
	*
	* @return the locales and localized feedback incorrects of this answer
	*/
	@Override
	public Map<java.util.Locale, String> getFeedbackIncorrectMap() {
		return _answer.getFeedbackIncorrectMap();
	}

	@Override
	public String getFeedbackIncorrectMapAsXML() {
		return _answer.getFeedbackIncorrectMapAsXML();
	}

	/**
	* Returns the group ID of this answer.
	*
	* @return the group ID of this answer
	*/
	@Override
	public long getGroupId() {
		return _answer.getGroupId();
	}

	/**
	* Returns the modified date of this answer.
	*
	* @return the modified date of this answer
	*/
	@Override
	public Date getModifiedDate() {
		return _answer.getModifiedDate();
	}

	/**
	* Returns the primary key of this answer.
	*
	* @return the primary key of this answer
	*/
	@Override
	public long getPrimaryKey() {
		return _answer.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _answer.getPrimaryKeyObj();
	}

	/**
	* Returns the question ID of this answer.
	*
	* @return the question ID of this answer
	*/
	@Override
	public long getQuestionId() {
		return _answer.getQuestionId();
	}

	/**
	* Returns the user ID of this answer.
	*
	* @return the user ID of this answer
	*/
	@Override
	public long getUserId() {
		return _answer.getUserId();
	}

	/**
	* Returns the user name of this answer.
	*
	* @return the user name of this answer
	*/
	@Override
	public String getUserName() {
		return _answer.getUserName();
	}

	/**
	* Returns the user uuid of this answer.
	*
	* @return the user uuid of this answer
	*/
	@Override
	public String getUserUuid() {
		return _answer.getUserUuid();
	}

	/**
	* Returns the uuid of this answer.
	*
	* @return the uuid of this answer
	*/
	@Override
	public String getUuid() {
		return _answer.getUuid();
	}

	@Override
	public int hashCode() {
		return _answer.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _answer.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this answer is correct.
	*
	* @return <code>true</code> if this answer is correct; <code>false</code> otherwise
	*/
	@Override
	public boolean isCorrect() {
		return _answer.isCorrect();
	}

	@Override
	public boolean isEscapedModel() {
		return _answer.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _answer.isNew();
	}

	@Override
	public void persist() {
		_answer.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {
		_answer.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {
		_answer.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	/**
	* Sets the act ID of this answer.
	*
	* @param actId the act ID of this answer
	*/
	@Override
	public void setActId(long actId) {
		_answer.setActId(actId);
	}

	/**
	* Sets the answer of this answer.
	*
	* @param answer the answer of this answer
	*/
	@Override
	public void setAnswer(String answer) {
		_answer.setAnswer(answer);
	}

	/**
	* Sets the localized answer of this answer in the language.
	*
	* @param answer the localized answer of this answer
	* @param locale the locale of the language
	*/
	@Override
	public void setAnswer(String answer, java.util.Locale locale) {
		_answer.setAnswer(answer, locale);
	}

	/**
	* Sets the localized answer of this answer in the language, and sets the default locale.
	*
	* @param answer the localized answer of this answer
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setAnswer(String answer, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_answer.setAnswer(answer, locale, defaultLocale);
	}

	@Override
	public void setAnswerCurrentLanguageId(String languageId) {
		_answer.setAnswerCurrentLanguageId(languageId);
	}

	/**
	* Sets the answer ID of this answer.
	*
	* @param answerId the answer ID of this answer
	*/
	@Override
	public void setAnswerId(long answerId) {
		_answer.setAnswerId(answerId);
	}

	/**
	* Sets the localized answers of this answer from the map of locales and localized answers.
	*
	* @param answerMap the locales and localized answers of this answer
	*/
	@Override
	public void setAnswerMap(Map<java.util.Locale, String> answerMap) {
		_answer.setAnswerMap(answerMap);
	}

	/**
	* Sets the localized answers of this answer from the map of locales and localized answers, and sets the default locale.
	*
	* @param answerMap the locales and localized answers of this answer
	* @param defaultLocale the default locale
	*/
	@Override
	public void setAnswerMap(Map<java.util.Locale, String> answerMap,
		java.util.Locale defaultLocale) {
		_answer.setAnswerMap(answerMap, defaultLocale);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_answer.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this answer.
	*
	* @param companyId the company ID of this answer
	*/
	@Override
	public void setCompanyId(long companyId) {
		_answer.setCompanyId(companyId);
	}

	/**
	* Sets whether this answer is correct.
	*
	* @param correct the correct of this answer
	*/
	@Override
	public void setCorrect(boolean correct) {
		_answer.setCorrect(correct);
	}

	/**
	* Sets the create date of this answer.
	*
	* @param createDate the create date of this answer
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_answer.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_answer.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_answer.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_answer.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the feedback correct of this answer.
	*
	* @param feedbackCorrect the feedback correct of this answer
	*/
	@Override
	public void setFeedbackCorrect(String feedbackCorrect) {
		_answer.setFeedbackCorrect(feedbackCorrect);
	}

	/**
	* Sets the localized feedback correct of this answer in the language.
	*
	* @param feedbackCorrect the localized feedback correct of this answer
	* @param locale the locale of the language
	*/
	@Override
	public void setFeedbackCorrect(String feedbackCorrect,
		java.util.Locale locale) {
		_answer.setFeedbackCorrect(feedbackCorrect, locale);
	}

	/**
	* Sets the localized feedback correct of this answer in the language, and sets the default locale.
	*
	* @param feedbackCorrect the localized feedback correct of this answer
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setFeedbackCorrect(String feedbackCorrect,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_answer.setFeedbackCorrect(feedbackCorrect, locale, defaultLocale);
	}

	@Override
	public void setFeedbackCorrectCurrentLanguageId(String languageId) {
		_answer.setFeedbackCorrectCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized feedback corrects of this answer from the map of locales and localized feedback corrects.
	*
	* @param feedbackCorrectMap the locales and localized feedback corrects of this answer
	*/
	@Override
	public void setFeedbackCorrectMap(
		Map<java.util.Locale, String> feedbackCorrectMap) {
		_answer.setFeedbackCorrectMap(feedbackCorrectMap);
	}

	/**
	* Sets the localized feedback corrects of this answer from the map of locales and localized feedback corrects, and sets the default locale.
	*
	* @param feedbackCorrectMap the locales and localized feedback corrects of this answer
	* @param defaultLocale the default locale
	*/
	@Override
	public void setFeedbackCorrectMap(
		Map<java.util.Locale, String> feedbackCorrectMap,
		java.util.Locale defaultLocale) {
		_answer.setFeedbackCorrectMap(feedbackCorrectMap, defaultLocale);
	}

	/**
	* Sets the feedback incorrect of this answer.
	*
	* @param feedbackIncorrect the feedback incorrect of this answer
	*/
	@Override
	public void setFeedbackIncorrect(String feedbackIncorrect) {
		_answer.setFeedbackIncorrect(feedbackIncorrect);
	}

	/**
	* Sets the localized feedback incorrect of this answer in the language.
	*
	* @param feedbackIncorrect the localized feedback incorrect of this answer
	* @param locale the locale of the language
	*/
	@Override
	public void setFeedbackIncorrect(String feedbackIncorrect,
		java.util.Locale locale) {
		_answer.setFeedbackIncorrect(feedbackIncorrect, locale);
	}

	/**
	* Sets the localized feedback incorrect of this answer in the language, and sets the default locale.
	*
	* @param feedbackIncorrect the localized feedback incorrect of this answer
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setFeedbackIncorrect(String feedbackIncorrect,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_answer.setFeedbackIncorrect(feedbackIncorrect, locale, defaultLocale);
	}

	@Override
	public void setFeedbackIncorrectCurrentLanguageId(String languageId) {
		_answer.setFeedbackIncorrectCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized feedback incorrects of this answer from the map of locales and localized feedback incorrects.
	*
	* @param feedbackIncorrectMap the locales and localized feedback incorrects of this answer
	*/
	@Override
	public void setFeedbackIncorrectMap(
		Map<java.util.Locale, String> feedbackIncorrectMap) {
		_answer.setFeedbackIncorrectMap(feedbackIncorrectMap);
	}

	/**
	* Sets the localized feedback incorrects of this answer from the map of locales and localized feedback incorrects, and sets the default locale.
	*
	* @param feedbackIncorrectMap the locales and localized feedback incorrects of this answer
	* @param defaultLocale the default locale
	*/
	@Override
	public void setFeedbackIncorrectMap(
		Map<java.util.Locale, String> feedbackIncorrectMap,
		java.util.Locale defaultLocale) {
		_answer.setFeedbackIncorrectMap(feedbackIncorrectMap, defaultLocale);
	}

	/**
	* Sets the group ID of this answer.
	*
	* @param groupId the group ID of this answer
	*/
	@Override
	public void setGroupId(long groupId) {
		_answer.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this answer.
	*
	* @param modifiedDate the modified date of this answer
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_answer.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_answer.setNew(n);
	}

	/**
	* Sets the primary key of this answer.
	*
	* @param primaryKey the primary key of this answer
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_answer.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_answer.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the question ID of this answer.
	*
	* @param questionId the question ID of this answer
	*/
	@Override
	public void setQuestionId(long questionId) {
		_answer.setQuestionId(questionId);
	}

	/**
	* Sets the user ID of this answer.
	*
	* @param userId the user ID of this answer
	*/
	@Override
	public void setUserId(long userId) {
		_answer.setUserId(userId);
	}

	/**
	* Sets the user name of this answer.
	*
	* @param userName the user name of this answer
	*/
	@Override
	public void setUserName(String userName) {
		_answer.setUserName(userName);
	}

	/**
	* Sets the user uuid of this answer.
	*
	* @param userUuid the user uuid of this answer
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_answer.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this answer.
	*
	* @param uuid the uuid of this answer
	*/
	@Override
	public void setUuid(String uuid) {
		_answer.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Answer> toCacheModel() {
		return _answer.toCacheModel();
	}

	@Override
	public Answer toEscapedModel() {
		return new AnswerWrapper(_answer.toEscapedModel());
	}

	@Override
	public String toString() {
		return _answer.toString();
	}

	@Override
	public Answer toUnescapedModel() {
		return new AnswerWrapper(_answer.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _answer.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AnswerWrapper)) {
			return false;
		}

		AnswerWrapper answerWrapper = (AnswerWrapper)obj;

		if (Objects.equals(_answer, answerWrapper._answer)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _answer.getStagedModelType();
	}

	@Override
	public Answer getWrappedModel() {
		return _answer;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _answer.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _answer.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_answer.resetOriginalValues();
	}

	private final Answer _answer;
}