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

package com.ted.lms.learning.activity.question.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.petra.string.StringBundler;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.Validator;

import com.ted.lms.learning.activity.question.model.Answer;
import com.ted.lms.learning.activity.question.model.AnswerModel;
import com.ted.lms.learning.activity.question.model.AnswerSoap;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * The base model implementation for the Answer service. Represents a row in the &quot;qu_Answer&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link AnswerModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link AnswerImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AnswerImpl
 * @see Answer
 * @see AnswerModel
 * @generated
 */
@ProviderType
public class AnswerModelImpl extends BaseModelImpl<Answer>
	implements AnswerModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a answer model instance should use the {@link Answer} interface instead.
	 */
	public static final String TABLE_NAME = "qu_Answer";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "answerId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "questionId", Types.BIGINT },
			{ "actId", Types.BIGINT },
			{ "answer", Types.CLOB },
			{ "correct", Types.BOOLEAN },
			{ "feedbackCorrect", Types.VARCHAR },
			{ "feedbackIncorrect", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("answerId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("questionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("actId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("answer", Types.CLOB);
		TABLE_COLUMNS_MAP.put("correct", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("feedbackCorrect", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("feedbackIncorrect", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE = "create table qu_Answer (uuid_ VARCHAR(75) null,answerId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,questionId LONG,actId LONG,answer TEXT null,correct BOOLEAN,feedbackCorrect STRING null,feedbackIncorrect STRING null)";
	public static final String TABLE_SQL_DROP = "drop table qu_Answer";
	public static final String ORDER_BY_JPQL = " ORDER BY answer.answerId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY qu_Answer.answerId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.ted.lms.learning.activity.question.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.com.ted.lms.learning.activity.question.model.Answer"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.ted.lms.learning.activity.question.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.com.ted.lms.learning.activity.question.model.Answer"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.ted.lms.learning.activity.question.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.ted.lms.learning.activity.question.model.Answer"),
			true);
	public static final long COMPANYID_COLUMN_BITMASK = 1L;
	public static final long GROUPID_COLUMN_BITMASK = 2L;
	public static final long QUESTIONID_COLUMN_BITMASK = 4L;
	public static final long UUID_COLUMN_BITMASK = 8L;
	public static final long ANSWERID_COLUMN_BITMASK = 16L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static Answer toModel(AnswerSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		Answer model = new AnswerImpl();

		model.setUuid(soapModel.getUuid());
		model.setAnswerId(soapModel.getAnswerId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setQuestionId(soapModel.getQuestionId());
		model.setActId(soapModel.getActId());
		model.setAnswer(soapModel.getAnswer());
		model.setCorrect(soapModel.isCorrect());
		model.setFeedbackCorrect(soapModel.getFeedbackCorrect());
		model.setFeedbackIncorrect(soapModel.getFeedbackIncorrect());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<Answer> toModels(AnswerSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<Answer> models = new ArrayList<Answer>(soapModels.length);

		for (AnswerSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.ted.lms.learning.activity.question.service.util.ServiceProps.get(
				"lock.expiration.time.com.ted.lms.learning.activity.question.model.Answer"));

	public AnswerModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _answerId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setAnswerId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _answerId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
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

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

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
	public String getUuid() {
		if (_uuid == null) {
			return "";
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	@Override
	public long getAnswerId() {
		return _answerId;
	}

	@Override
	public void setAnswerId(long answerId) {
		_columnBitmask = -1L;

		_answerId = answerId;
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_columnBitmask |= GROUPID_COLUMN_BITMASK;

		if (!_setOriginalGroupId) {
			_setOriginalGroupId = true;

			_originalGroupId = _groupId;
		}

		_groupId = groupId;
	}

	public long getOriginalGroupId() {
		return _originalGroupId;
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@Override
	public String getUserName() {
		if (_userName == null) {
			return "";
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

	@Override
	public long getQuestionId() {
		return _questionId;
	}

	@Override
	public void setQuestionId(long questionId) {
		_columnBitmask |= QUESTIONID_COLUMN_BITMASK;

		if (!_setOriginalQuestionId) {
			_setOriginalQuestionId = true;

			_originalQuestionId = _questionId;
		}

		_questionId = questionId;
	}

	public long getOriginalQuestionId() {
		return _originalQuestionId;
	}

	@Override
	public long getActId() {
		return _actId;
	}

	@Override
	public void setActId(long actId) {
		_actId = actId;
	}

	@Override
	public String getAnswer() {
		if (_answer == null) {
			return "";
		}
		else {
			return _answer;
		}
	}

	@Override
	public void setAnswer(String answer) {
		_answer = answer;
	}

	@Override
	public boolean getCorrect() {
		return _correct;
	}

	@Override
	public boolean isCorrect() {
		return _correct;
	}

	@Override
	public void setCorrect(boolean correct) {
		_correct = correct;
	}

	@Override
	public String getFeedbackCorrect() {
		if (_feedbackCorrect == null) {
			return "";
		}
		else {
			return _feedbackCorrect;
		}
	}

	@Override
	public String getFeedbackCorrect(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getFeedbackCorrect(languageId);
	}

	@Override
	public String getFeedbackCorrect(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getFeedbackCorrect(languageId, useDefault);
	}

	@Override
	public String getFeedbackCorrect(String languageId) {
		return LocalizationUtil.getLocalization(getFeedbackCorrect(), languageId);
	}

	@Override
	public String getFeedbackCorrect(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getFeedbackCorrect(),
			languageId, useDefault);
	}

	@Override
	public String getFeedbackCorrectCurrentLanguageId() {
		return _feedbackCorrectCurrentLanguageId;
	}

	@JSON
	@Override
	public String getFeedbackCorrectCurrentValue() {
		Locale locale = getLocale(_feedbackCorrectCurrentLanguageId);

		return getFeedbackCorrect(locale);
	}

	@Override
	public Map<Locale, String> getFeedbackCorrectMap() {
		return LocalizationUtil.getLocalizationMap(getFeedbackCorrect());
	}

	@Override
	public void setFeedbackCorrect(String feedbackCorrect) {
		_feedbackCorrect = feedbackCorrect;
	}

	@Override
	public void setFeedbackCorrect(String feedbackCorrect, Locale locale) {
		setFeedbackCorrect(feedbackCorrect, locale, LocaleUtil.getSiteDefault());
	}

	@Override
	public void setFeedbackCorrect(String feedbackCorrect, Locale locale,
		Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(feedbackCorrect)) {
			setFeedbackCorrect(LocalizationUtil.updateLocalization(
					getFeedbackCorrect(), "FeedbackCorrect", feedbackCorrect,
					languageId, defaultLanguageId));
		}
		else {
			setFeedbackCorrect(LocalizationUtil.removeLocalization(
					getFeedbackCorrect(), "FeedbackCorrect", languageId));
		}
	}

	@Override
	public void setFeedbackCorrectCurrentLanguageId(String languageId) {
		_feedbackCorrectCurrentLanguageId = languageId;
	}

	@Override
	public void setFeedbackCorrectMap(Map<Locale, String> feedbackCorrectMap) {
		setFeedbackCorrectMap(feedbackCorrectMap, LocaleUtil.getSiteDefault());
	}

	@Override
	public void setFeedbackCorrectMap(Map<Locale, String> feedbackCorrectMap,
		Locale defaultLocale) {
		if (feedbackCorrectMap == null) {
			return;
		}

		setFeedbackCorrect(LocalizationUtil.updateLocalization(
				feedbackCorrectMap, getFeedbackCorrect(), "FeedbackCorrect",
				LocaleUtil.toLanguageId(defaultLocale)));
	}

	@Override
	public String getFeedbackIncorrect() {
		if (_feedbackIncorrect == null) {
			return "";
		}
		else {
			return _feedbackIncorrect;
		}
	}

	@Override
	public String getFeedbackIncorrect(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getFeedbackIncorrect(languageId);
	}

	@Override
	public String getFeedbackIncorrect(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getFeedbackIncorrect(languageId, useDefault);
	}

	@Override
	public String getFeedbackIncorrect(String languageId) {
		return LocalizationUtil.getLocalization(getFeedbackIncorrect(),
			languageId);
	}

	@Override
	public String getFeedbackIncorrect(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getFeedbackIncorrect(),
			languageId, useDefault);
	}

	@Override
	public String getFeedbackIncorrectCurrentLanguageId() {
		return _feedbackIncorrectCurrentLanguageId;
	}

	@JSON
	@Override
	public String getFeedbackIncorrectCurrentValue() {
		Locale locale = getLocale(_feedbackIncorrectCurrentLanguageId);

		return getFeedbackIncorrect(locale);
	}

	@Override
	public Map<Locale, String> getFeedbackIncorrectMap() {
		return LocalizationUtil.getLocalizationMap(getFeedbackIncorrect());
	}

	@Override
	public void setFeedbackIncorrect(String feedbackIncorrect) {
		_feedbackIncorrect = feedbackIncorrect;
	}

	@Override
	public void setFeedbackIncorrect(String feedbackIncorrect, Locale locale) {
		setFeedbackIncorrect(feedbackIncorrect, locale,
			LocaleUtil.getSiteDefault());
	}

	@Override
	public void setFeedbackIncorrect(String feedbackIncorrect, Locale locale,
		Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(feedbackIncorrect)) {
			setFeedbackIncorrect(LocalizationUtil.updateLocalization(
					getFeedbackIncorrect(), "FeedbackIncorrect",
					feedbackIncorrect, languageId, defaultLanguageId));
		}
		else {
			setFeedbackIncorrect(LocalizationUtil.removeLocalization(
					getFeedbackIncorrect(), "FeedbackIncorrect", languageId));
		}
	}

	@Override
	public void setFeedbackIncorrectCurrentLanguageId(String languageId) {
		_feedbackIncorrectCurrentLanguageId = languageId;
	}

	@Override
	public void setFeedbackIncorrectMap(
		Map<Locale, String> feedbackIncorrectMap) {
		setFeedbackIncorrectMap(feedbackIncorrectMap,
			LocaleUtil.getSiteDefault());
	}

	@Override
	public void setFeedbackIncorrectMap(
		Map<Locale, String> feedbackIncorrectMap, Locale defaultLocale) {
		if (feedbackIncorrectMap == null) {
			return;
		}

		setFeedbackIncorrect(LocalizationUtil.updateLocalization(
				feedbackIncorrectMap, getFeedbackIncorrect(),
				"FeedbackIncorrect", LocaleUtil.toLanguageId(defaultLocale)));
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(PortalUtil.getClassNameId(
				Answer.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			Answer.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public String[] getAvailableLanguageIds() {
		Set<String> availableLanguageIds = new TreeSet<String>();

		Map<Locale, String> feedbackCorrectMap = getFeedbackCorrectMap();

		for (Map.Entry<Locale, String> entry : feedbackCorrectMap.entrySet()) {
			Locale locale = entry.getKey();
			String value = entry.getValue();

			if (Validator.isNotNull(value)) {
				availableLanguageIds.add(LocaleUtil.toLanguageId(locale));
			}
		}

		Map<Locale, String> feedbackIncorrectMap = getFeedbackIncorrectMap();

		for (Map.Entry<Locale, String> entry : feedbackIncorrectMap.entrySet()) {
			Locale locale = entry.getKey();
			String value = entry.getValue();

			if (Validator.isNotNull(value)) {
				availableLanguageIds.add(LocaleUtil.toLanguageId(locale));
			}
		}

		return availableLanguageIds.toArray(new String[availableLanguageIds.size()]);
	}

	@Override
	public String getDefaultLanguageId() {
		String xml = getFeedbackCorrect();

		if (xml == null) {
			return "";
		}

		Locale defaultLocale = LocaleUtil.getSiteDefault();

		return LocalizationUtil.getDefaultLanguageId(xml, defaultLocale);
	}

	@Override
	public void prepareLocalizedFieldsForImport() throws LocaleException {
		Locale defaultLocale = LocaleUtil.fromLanguageId(getDefaultLanguageId());

		Locale[] availableLocales = LocaleUtil.fromLanguageIds(getAvailableLanguageIds());

		Locale defaultImportLocale = LocalizationUtil.getDefaultImportLocale(Answer.class.getName(),
				getPrimaryKey(), defaultLocale, availableLocales);

		prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	@SuppressWarnings("unused")
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException {
		Locale defaultLocale = LocaleUtil.getSiteDefault();

		String modelDefaultLanguageId = getDefaultLanguageId();

		String feedbackCorrect = getFeedbackCorrect(defaultLocale);

		if (Validator.isNull(feedbackCorrect)) {
			setFeedbackCorrect(getFeedbackCorrect(modelDefaultLanguageId),
				defaultLocale);
		}
		else {
			setFeedbackCorrect(getFeedbackCorrect(defaultLocale),
				defaultLocale, defaultLocale);
		}

		String feedbackIncorrect = getFeedbackIncorrect(defaultLocale);

		if (Validator.isNull(feedbackIncorrect)) {
			setFeedbackIncorrect(getFeedbackIncorrect(modelDefaultLanguageId),
				defaultLocale);
		}
		else {
			setFeedbackIncorrect(getFeedbackIncorrect(defaultLocale),
				defaultLocale, defaultLocale);
		}
	}

	@Override
	public Answer toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (Answer)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		AnswerImpl answerImpl = new AnswerImpl();

		answerImpl.setUuid(getUuid());
		answerImpl.setAnswerId(getAnswerId());
		answerImpl.setGroupId(getGroupId());
		answerImpl.setCompanyId(getCompanyId());
		answerImpl.setUserId(getUserId());
		answerImpl.setUserName(getUserName());
		answerImpl.setCreateDate(getCreateDate());
		answerImpl.setModifiedDate(getModifiedDate());
		answerImpl.setQuestionId(getQuestionId());
		answerImpl.setActId(getActId());
		answerImpl.setAnswer(getAnswer());
		answerImpl.setCorrect(isCorrect());
		answerImpl.setFeedbackCorrect(getFeedbackCorrect());
		answerImpl.setFeedbackIncorrect(getFeedbackIncorrect());

		answerImpl.resetOriginalValues();

		return answerImpl;
	}

	@Override
	public int compareTo(Answer answer) {
		int value = 0;

		if (getAnswerId() < answer.getAnswerId()) {
			value = -1;
		}
		else if (getAnswerId() > answer.getAnswerId()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Answer)) {
			return false;
		}

		Answer answer = (Answer)obj;

		long primaryKey = answer.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		AnswerModelImpl answerModelImpl = this;

		answerModelImpl._originalUuid = answerModelImpl._uuid;

		answerModelImpl._originalGroupId = answerModelImpl._groupId;

		answerModelImpl._setOriginalGroupId = false;

		answerModelImpl._originalCompanyId = answerModelImpl._companyId;

		answerModelImpl._setOriginalCompanyId = false;

		answerModelImpl._setModifiedDate = false;

		answerModelImpl._originalQuestionId = answerModelImpl._questionId;

		answerModelImpl._setOriginalQuestionId = false;

		answerModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Answer> toCacheModel() {
		AnswerCacheModel answerCacheModel = new AnswerCacheModel();

		answerCacheModel.uuid = getUuid();

		String uuid = answerCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			answerCacheModel.uuid = null;
		}

		answerCacheModel.answerId = getAnswerId();

		answerCacheModel.groupId = getGroupId();

		answerCacheModel.companyId = getCompanyId();

		answerCacheModel.userId = getUserId();

		answerCacheModel.userName = getUserName();

		String userName = answerCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			answerCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			answerCacheModel.createDate = createDate.getTime();
		}
		else {
			answerCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			answerCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			answerCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		answerCacheModel.questionId = getQuestionId();

		answerCacheModel.actId = getActId();

		answerCacheModel.answer = getAnswer();

		String answer = answerCacheModel.answer;

		if ((answer != null) && (answer.length() == 0)) {
			answerCacheModel.answer = null;
		}

		answerCacheModel.correct = isCorrect();

		answerCacheModel.feedbackCorrect = getFeedbackCorrect();

		String feedbackCorrect = answerCacheModel.feedbackCorrect;

		if ((feedbackCorrect != null) && (feedbackCorrect.length() == 0)) {
			answerCacheModel.feedbackCorrect = null;
		}

		answerCacheModel.feedbackIncorrect = getFeedbackIncorrect();

		String feedbackIncorrect = answerCacheModel.feedbackIncorrect;

		if ((feedbackIncorrect != null) && (feedbackIncorrect.length() == 0)) {
			answerCacheModel.feedbackIncorrect = null;
		}

		return answerCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", answerId=");
		sb.append(getAnswerId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", questionId=");
		sb.append(getQuestionId());
		sb.append(", actId=");
		sb.append(getActId());
		sb.append(", answer=");
		sb.append(getAnswer());
		sb.append(", correct=");
		sb.append(isCorrect());
		sb.append(", feedbackCorrect=");
		sb.append(getFeedbackCorrect());
		sb.append(", feedbackIncorrect=");
		sb.append(getFeedbackIncorrect());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(46);

		sb.append("<model><model-name>");
		sb.append("com.ted.lms.learning.activity.question.model.Answer");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>answerId</column-name><column-value><![CDATA[");
		sb.append(getAnswerId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>questionId</column-name><column-value><![CDATA[");
		sb.append(getQuestionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>actId</column-name><column-value><![CDATA[");
		sb.append(getActId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>answer</column-name><column-value><![CDATA[");
		sb.append(getAnswer());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>correct</column-name><column-value><![CDATA[");
		sb.append(isCorrect());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>feedbackCorrect</column-name><column-value><![CDATA[");
		sb.append(getFeedbackCorrect());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>feedbackIncorrect</column-name><column-value><![CDATA[");
		sb.append(getFeedbackIncorrect());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = Answer.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			Answer.class, ModelWrapper.class
		};
	private String _uuid;
	private String _originalUuid;
	private long _answerId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _questionId;
	private long _originalQuestionId;
	private boolean _setOriginalQuestionId;
	private long _actId;
	private String _answer;
	private boolean _correct;
	private String _feedbackCorrect;
	private String _feedbackCorrectCurrentLanguageId;
	private String _feedbackIncorrect;
	private String _feedbackIncorrectCurrentLanguageId;
	private long _columnBitmask;
	private Answer _escapedModel;
}