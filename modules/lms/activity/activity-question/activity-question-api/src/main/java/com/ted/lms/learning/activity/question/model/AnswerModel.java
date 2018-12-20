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
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.GroupedModel;
import com.liferay.portal.kernel.model.LocalizedModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedAuditedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

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
	LocalizedModel, ShardedModel, StagedAuditedModel {
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
	public String getAnswer();

	/**
	 * Returns the localized answer of this answer in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized answer of this answer
	 */
	@AutoEscape
	public String getAnswer(Locale locale);

	/**
	 * Returns the localized answer of this answer in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized answer of this answer. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getAnswer(Locale locale, boolean useDefault);

	/**
	 * Returns the localized answer of this answer in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized answer of this answer
	 */
	@AutoEscape
	public String getAnswer(String languageId);

	/**
	 * Returns the localized answer of this answer in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized answer of this answer
	 */
	@AutoEscape
	public String getAnswer(String languageId, boolean useDefault);

	@AutoEscape
	public String getAnswerCurrentLanguageId();

	@AutoEscape
	public String getAnswerCurrentValue();

	/**
	 * Returns a map of the locales and localized answers of this answer.
	 *
	 * @return the locales and localized answers of this answer
	 */
	public Map<Locale, String> getAnswerMap();

	/**
	 * Sets the answer of this answer.
	 *
	 * @param answer the answer of this answer
	 */
	public void setAnswer(String answer);

	/**
	 * Sets the localized answer of this answer in the language.
	 *
	 * @param answer the localized answer of this answer
	 * @param locale the locale of the language
	 */
	public void setAnswer(String answer, Locale locale);

	/**
	 * Sets the localized answer of this answer in the language, and sets the default locale.
	 *
	 * @param answer the localized answer of this answer
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setAnswer(String answer, Locale locale, Locale defaultLocale);

	public void setAnswerCurrentLanguageId(String languageId);

	/**
	 * Sets the localized answers of this answer from the map of locales and localized answers.
	 *
	 * @param answerMap the locales and localized answers of this answer
	 */
	public void setAnswerMap(Map<Locale, String> answerMap);

	/**
	 * Sets the localized answers of this answer from the map of locales and localized answers, and sets the default locale.
	 *
	 * @param answerMap the locales and localized answers of this answer
	 * @param defaultLocale the default locale
	 */
	public void setAnswerMap(Map<Locale, String> answerMap, Locale defaultLocale);

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
	public String getFeedbackCorrect();

	/**
	 * Returns the localized feedback correct of this answer in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized feedback correct of this answer
	 */
	@AutoEscape
	public String getFeedbackCorrect(Locale locale);

	/**
	 * Returns the localized feedback correct of this answer in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized feedback correct of this answer. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getFeedbackCorrect(Locale locale, boolean useDefault);

	/**
	 * Returns the localized feedback correct of this answer in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized feedback correct of this answer
	 */
	@AutoEscape
	public String getFeedbackCorrect(String languageId);

	/**
	 * Returns the localized feedback correct of this answer in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized feedback correct of this answer
	 */
	@AutoEscape
	public String getFeedbackCorrect(String languageId, boolean useDefault);

	@AutoEscape
	public String getFeedbackCorrectCurrentLanguageId();

	@AutoEscape
	public String getFeedbackCorrectCurrentValue();

	/**
	 * Returns a map of the locales and localized feedback corrects of this answer.
	 *
	 * @return the locales and localized feedback corrects of this answer
	 */
	public Map<Locale, String> getFeedbackCorrectMap();

	/**
	 * Sets the feedback correct of this answer.
	 *
	 * @param feedbackCorrect the feedback correct of this answer
	 */
	public void setFeedbackCorrect(String feedbackCorrect);

	/**
	 * Sets the localized feedback correct of this answer in the language.
	 *
	 * @param feedbackCorrect the localized feedback correct of this answer
	 * @param locale the locale of the language
	 */
	public void setFeedbackCorrect(String feedbackCorrect, Locale locale);

	/**
	 * Sets the localized feedback correct of this answer in the language, and sets the default locale.
	 *
	 * @param feedbackCorrect the localized feedback correct of this answer
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setFeedbackCorrect(String feedbackCorrect, Locale locale,
		Locale defaultLocale);

	public void setFeedbackCorrectCurrentLanguageId(String languageId);

	/**
	 * Sets the localized feedback corrects of this answer from the map of locales and localized feedback corrects.
	 *
	 * @param feedbackCorrectMap the locales and localized feedback corrects of this answer
	 */
	public void setFeedbackCorrectMap(Map<Locale, String> feedbackCorrectMap);

	/**
	 * Sets the localized feedback corrects of this answer from the map of locales and localized feedback corrects, and sets the default locale.
	 *
	 * @param feedbackCorrectMap the locales and localized feedback corrects of this answer
	 * @param defaultLocale the default locale
	 */
	public void setFeedbackCorrectMap(Map<Locale, String> feedbackCorrectMap,
		Locale defaultLocale);

	/**
	 * Returns the feedback incorrect of this answer.
	 *
	 * @return the feedback incorrect of this answer
	 */
	public String getFeedbackIncorrect();

	/**
	 * Returns the localized feedback incorrect of this answer in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized feedback incorrect of this answer
	 */
	@AutoEscape
	public String getFeedbackIncorrect(Locale locale);

	/**
	 * Returns the localized feedback incorrect of this answer in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized feedback incorrect of this answer. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getFeedbackIncorrect(Locale locale, boolean useDefault);

	/**
	 * Returns the localized feedback incorrect of this answer in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized feedback incorrect of this answer
	 */
	@AutoEscape
	public String getFeedbackIncorrect(String languageId);

	/**
	 * Returns the localized feedback incorrect of this answer in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized feedback incorrect of this answer
	 */
	@AutoEscape
	public String getFeedbackIncorrect(String languageId, boolean useDefault);

	@AutoEscape
	public String getFeedbackIncorrectCurrentLanguageId();

	@AutoEscape
	public String getFeedbackIncorrectCurrentValue();

	/**
	 * Returns a map of the locales and localized feedback incorrects of this answer.
	 *
	 * @return the locales and localized feedback incorrects of this answer
	 */
	public Map<Locale, String> getFeedbackIncorrectMap();

	/**
	 * Sets the feedback incorrect of this answer.
	 *
	 * @param feedbackIncorrect the feedback incorrect of this answer
	 */
	public void setFeedbackIncorrect(String feedbackIncorrect);

	/**
	 * Sets the localized feedback incorrect of this answer in the language.
	 *
	 * @param feedbackIncorrect the localized feedback incorrect of this answer
	 * @param locale the locale of the language
	 */
	public void setFeedbackIncorrect(String feedbackIncorrect, Locale locale);

	/**
	 * Sets the localized feedback incorrect of this answer in the language, and sets the default locale.
	 *
	 * @param feedbackIncorrect the localized feedback incorrect of this answer
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setFeedbackIncorrect(String feedbackIncorrect, Locale locale,
		Locale defaultLocale);

	public void setFeedbackIncorrectCurrentLanguageId(String languageId);

	/**
	 * Sets the localized feedback incorrects of this answer from the map of locales and localized feedback incorrects.
	 *
	 * @param feedbackIncorrectMap the locales and localized feedback incorrects of this answer
	 */
	public void setFeedbackIncorrectMap(
		Map<Locale, String> feedbackIncorrectMap);

	/**
	 * Sets the localized feedback incorrects of this answer from the map of locales and localized feedback incorrects, and sets the default locale.
	 *
	 * @param feedbackIncorrectMap the locales and localized feedback incorrects of this answer
	 * @param defaultLocale the default locale
	 */
	public void setFeedbackIncorrectMap(
		Map<Locale, String> feedbackIncorrectMap, Locale defaultLocale);

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
	public String[] getAvailableLanguageIds();

	@Override
	public String getDefaultLanguageId();

	@Override
	public void prepareLocalizedFieldsForImport() throws LocaleException;

	@Override
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException;

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