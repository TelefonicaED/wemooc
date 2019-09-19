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

package com.ted.lms.model;

import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * <p>
 * This class is a wrapper for {@link Course}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Course
 * @generated
 */
@ProviderType
public class CourseWrapper
	extends BaseModelWrapper<Course> implements Course, ModelWrapper<Course> {

	public CourseWrapper(Course course) {
		super(course);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("courseId", getCourseId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("lastPublishDate", getLastPublishDate());
		attributes.put("parentCourseId", getParentCourseId());
		attributes.put("groupCreatedId", getGroupCreatedId());
		attributes.put("title", getTitle());
		attributes.put("description", getDescription());
		attributes.put("smallImageId", getSmallImageId());
		attributes.put("registrationStartDate", getRegistrationStartDate());
		attributes.put("registrationEndDate", getRegistrationEndDate());
		attributes.put("executionStartDate", getExecutionStartDate());
		attributes.put("executionEndDate", getExecutionEndDate());
		attributes.put("maxUsers", getMaxUsers());
		attributes.put("inscriptionType", getInscriptionType());
		attributes.put("courseEvalId", getCourseEvalId());
		attributes.put("calificationType", getCalificationType());
		attributes.put("welcome", isWelcome());
		attributes.put("welcomeSubject", getWelcomeSubject());
		attributes.put("welcomeMsg", getWelcomeMsg());
		attributes.put("goodbye", isGoodbye());
		attributes.put("goodbyeSubject", getGoodbyeSubject());
		attributes.put("goodbyeMsg", getGoodbyeMsg());
		attributes.put("deniedInscription", isDeniedInscription());
		attributes.put(
			"deniedInscriptionSubject", getDeniedInscriptionSubject());
		attributes.put("deniedInscriptionMsg", getDeniedInscriptionMsg());
		attributes.put("courseExtraData", getCourseExtraData());
		attributes.put("status", getStatus());
		attributes.put("statusByUserId", getStatusByUserId());
		attributes.put("statusByUserName", getStatusByUserName());
		attributes.put("statusDate", getStatusDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long courseId = (Long)attributes.get("courseId");

		if (courseId != null) {
			setCourseId(courseId);
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

		Long parentCourseId = (Long)attributes.get("parentCourseId");

		if (parentCourseId != null) {
			setParentCourseId(parentCourseId);
		}

		Long groupCreatedId = (Long)attributes.get("groupCreatedId");

		if (groupCreatedId != null) {
			setGroupCreatedId(groupCreatedId);
		}

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Long smallImageId = (Long)attributes.get("smallImageId");

		if (smallImageId != null) {
			setSmallImageId(smallImageId);
		}

		Date registrationStartDate = (Date)attributes.get(
			"registrationStartDate");

		if (registrationStartDate != null) {
			setRegistrationStartDate(registrationStartDate);
		}

		Date registrationEndDate = (Date)attributes.get("registrationEndDate");

		if (registrationEndDate != null) {
			setRegistrationEndDate(registrationEndDate);
		}

		Date executionStartDate = (Date)attributes.get("executionStartDate");

		if (executionStartDate != null) {
			setExecutionStartDate(executionStartDate);
		}

		Date executionEndDate = (Date)attributes.get("executionEndDate");

		if (executionEndDate != null) {
			setExecutionEndDate(executionEndDate);
		}

		Integer maxUsers = (Integer)attributes.get("maxUsers");

		if (maxUsers != null) {
			setMaxUsers(maxUsers);
		}

		Long inscriptionType = (Long)attributes.get("inscriptionType");

		if (inscriptionType != null) {
			setInscriptionType(inscriptionType);
		}

		Long courseEvalId = (Long)attributes.get("courseEvalId");

		if (courseEvalId != null) {
			setCourseEvalId(courseEvalId);
		}

		Long calificationType = (Long)attributes.get("calificationType");

		if (calificationType != null) {
			setCalificationType(calificationType);
		}

		Boolean welcome = (Boolean)attributes.get("welcome");

		if (welcome != null) {
			setWelcome(welcome);
		}

		String welcomeSubject = (String)attributes.get("welcomeSubject");

		if (welcomeSubject != null) {
			setWelcomeSubject(welcomeSubject);
		}

		String welcomeMsg = (String)attributes.get("welcomeMsg");

		if (welcomeMsg != null) {
			setWelcomeMsg(welcomeMsg);
		}

		Boolean goodbye = (Boolean)attributes.get("goodbye");

		if (goodbye != null) {
			setGoodbye(goodbye);
		}

		String goodbyeSubject = (String)attributes.get("goodbyeSubject");

		if (goodbyeSubject != null) {
			setGoodbyeSubject(goodbyeSubject);
		}

		String goodbyeMsg = (String)attributes.get("goodbyeMsg");

		if (goodbyeMsg != null) {
			setGoodbyeMsg(goodbyeMsg);
		}

		Boolean deniedInscription = (Boolean)attributes.get(
			"deniedInscription");

		if (deniedInscription != null) {
			setDeniedInscription(deniedInscription);
		}

		String deniedInscriptionSubject = (String)attributes.get(
			"deniedInscriptionSubject");

		if (deniedInscriptionSubject != null) {
			setDeniedInscriptionSubject(deniedInscriptionSubject);
		}

		String deniedInscriptionMsg = (String)attributes.get(
			"deniedInscriptionMsg");

		if (deniedInscriptionMsg != null) {
			setDeniedInscriptionMsg(deniedInscriptionMsg);
		}

		String courseExtraData = (String)attributes.get("courseExtraData");

		if (courseExtraData != null) {
			setCourseExtraData(courseExtraData);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Long statusByUserId = (Long)attributes.get("statusByUserId");

		if (statusByUserId != null) {
			setStatusByUserId(statusByUserId);
		}

		String statusByUserName = (String)attributes.get("statusByUserName");

		if (statusByUserName != null) {
			setStatusByUserName(statusByUserName);
		}

		Date statusDate = (Date)attributes.get("statusDate");

		if (statusDate != null) {
			setStatusDate(statusDate);
		}
	}

	@Override
	public void addCourseExtraDataJSON(String key, Object value) {
		model.addCourseExtraDataJSON(key, value);
	}

	@Override
	public boolean canEnroll(
			long userId, java.util.Locale locale,
			com.liferay.portal.kernel.security.permission.PermissionChecker
				permissionChecker)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.ted.lms.exception.InscriptionException {

		return model.canEnroll(userId, locale, permissionChecker);
	}

	@Override
	public boolean canUnsubscribe(
			long userId,
			com.liferay.portal.kernel.security.permission.PermissionChecker
				permissionChecker)
		throws com.liferay.portal.kernel.exception.PortalException {

		return model.canUnsubscribe(userId, permissionChecker);
	}

	@Override
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry() {
		return model.getAssetEntry();
	}

	@Override
	public String[] getAvailableLanguageIds() {
		return model.getAvailableLanguageIds();
	}

	/**
	 * Returns the calification type of this course.
	 *
	 * @return the calification type of this course
	 */
	@Override
	public long getCalificationType() {
		return model.getCalificationType();
	}

	/**
	 * Returns the company ID of this course.
	 *
	 * @return the company ID of this course
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	@Override
	public int getCountEditions() {
		return model.getCountEditions();
	}

	/**
	 * Returns the course eval ID of this course.
	 *
	 * @return the course eval ID of this course
	 */
	@Override
	public long getCourseEvalId() {
		return model.getCourseEvalId();
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getCourseEvalJSON() {
		return model.getCourseEvalJSON();
	}

	/**
	 * Returns the course extra data of this course.
	 *
	 * @return the course extra data of this course
	 */
	@Override
	public String getCourseExtraData() {
		return model.getCourseExtraData();
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getCourseExtraDataJSON() {
		return model.getCourseExtraDataJSON();
	}

	/**
	 * Returns the course ID of this course.
	 *
	 * @return the course ID of this course
	 */
	@Override
	public long getCourseId() {
		return model.getCourseId();
	}

	@Override
	public long getCourseTypeId() {
		return model.getCourseTypeId();
	}

	/**
	 * Returns the create date of this course.
	 *
	 * @return the create date of this course
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	@Override
	public String getDefaultLanguageId() {
		return model.getDefaultLanguageId();
	}

	/**
	 * Returns the denied inscription of this course.
	 *
	 * @return the denied inscription of this course
	 */
	@Override
	public boolean getDeniedInscription() {
		return model.getDeniedInscription();
	}

	/**
	 * Returns the denied inscription msg of this course.
	 *
	 * @return the denied inscription msg of this course
	 */
	@Override
	public String getDeniedInscriptionMsg() {
		return model.getDeniedInscriptionMsg();
	}

	/**
	 * Returns the localized denied inscription msg of this course in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized denied inscription msg of this course
	 */
	@Override
	public String getDeniedInscriptionMsg(java.util.Locale locale) {
		return model.getDeniedInscriptionMsg(locale);
	}

	/**
	 * Returns the localized denied inscription msg of this course in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized denied inscription msg of this course. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getDeniedInscriptionMsg(
		java.util.Locale locale, boolean useDefault) {

		return model.getDeniedInscriptionMsg(locale, useDefault);
	}

	/**
	 * Returns the localized denied inscription msg of this course in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized denied inscription msg of this course
	 */
	@Override
	public String getDeniedInscriptionMsg(String languageId) {
		return model.getDeniedInscriptionMsg(languageId);
	}

	/**
	 * Returns the localized denied inscription msg of this course in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized denied inscription msg of this course
	 */
	@Override
	public String getDeniedInscriptionMsg(
		String languageId, boolean useDefault) {

		return model.getDeniedInscriptionMsg(languageId, useDefault);
	}

	@Override
	public String getDeniedInscriptionMsgCurrentLanguageId() {
		return model.getDeniedInscriptionMsgCurrentLanguageId();
	}

	@Override
	public String getDeniedInscriptionMsgCurrentValue() {
		return model.getDeniedInscriptionMsgCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized denied inscription msgs of this course.
	 *
	 * @return the locales and localized denied inscription msgs of this course
	 */
	@Override
	public Map<java.util.Locale, String> getDeniedInscriptionMsgMap() {
		return model.getDeniedInscriptionMsgMap();
	}

	@Override
	public String getDeniedInscriptionMsgMapAsXML() {
		return model.getDeniedInscriptionMsgMapAsXML();
	}

	/**
	 * Returns the denied inscription subject of this course.
	 *
	 * @return the denied inscription subject of this course
	 */
	@Override
	public String getDeniedInscriptionSubject() {
		return model.getDeniedInscriptionSubject();
	}

	/**
	 * Returns the localized denied inscription subject of this course in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized denied inscription subject of this course
	 */
	@Override
	public String getDeniedInscriptionSubject(java.util.Locale locale) {
		return model.getDeniedInscriptionSubject(locale);
	}

	/**
	 * Returns the localized denied inscription subject of this course in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized denied inscription subject of this course. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getDeniedInscriptionSubject(
		java.util.Locale locale, boolean useDefault) {

		return model.getDeniedInscriptionSubject(locale, useDefault);
	}

	/**
	 * Returns the localized denied inscription subject of this course in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized denied inscription subject of this course
	 */
	@Override
	public String getDeniedInscriptionSubject(String languageId) {
		return model.getDeniedInscriptionSubject(languageId);
	}

	/**
	 * Returns the localized denied inscription subject of this course in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized denied inscription subject of this course
	 */
	@Override
	public String getDeniedInscriptionSubject(
		String languageId, boolean useDefault) {

		return model.getDeniedInscriptionSubject(languageId, useDefault);
	}

	@Override
	public String getDeniedInscriptionSubjectCurrentLanguageId() {
		return model.getDeniedInscriptionSubjectCurrentLanguageId();
	}

	@Override
	public String getDeniedInscriptionSubjectCurrentValue() {
		return model.getDeniedInscriptionSubjectCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized denied inscription subjects of this course.
	 *
	 * @return the locales and localized denied inscription subjects of this course
	 */
	@Override
	public Map<java.util.Locale, String> getDeniedInscriptionSubjectMap() {
		return model.getDeniedInscriptionSubjectMap();
	}

	/**
	 * Returns the description of this course.
	 *
	 * @return the description of this course
	 */
	@Override
	public String getDescription() {
		return model.getDescription();
	}

	/**
	 * Returns the localized description of this course in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized description of this course
	 */
	@Override
	public String getDescription(java.util.Locale locale) {
		return model.getDescription(locale);
	}

	/**
	 * Returns the localized description of this course in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this course. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getDescription(java.util.Locale locale, boolean useDefault) {
		return model.getDescription(locale, useDefault);
	}

	/**
	 * Returns the localized description of this course in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized description of this course
	 */
	@Override
	public String getDescription(String languageId) {
		return model.getDescription(languageId);
	}

	/**
	 * Returns the localized description of this course in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this course
	 */
	@Override
	public String getDescription(String languageId, boolean useDefault) {
		return model.getDescription(languageId, useDefault);
	}

	@Override
	public String getDescriptionCurrentLanguageId() {
		return model.getDescriptionCurrentLanguageId();
	}

	@Override
	public String getDescriptionCurrentValue() {
		return model.getDescriptionCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized descriptions of this course.
	 *
	 * @return the locales and localized descriptions of this course
	 */
	@Override
	public Map<java.util.Locale, String> getDescriptionMap() {
		return model.getDescriptionMap();
	}

	@Override
	public String getDescriptionMapAsXML() {
		return model.getDescriptionMapAsXML();
	}

	/**
	 * Returns the execution end date of this course.
	 *
	 * @return the execution end date of this course
	 */
	@Override
	public Date getExecutionEndDate() {
		return model.getExecutionEndDate();
	}

	@Override
	public java.util.Calendar getExecutionEndDateCalendar() {
		return model.getExecutionEndDateCalendar();
	}

	@Override
	public String getExecutionEndDateFormat(
		java.util.Locale locale, java.util.TimeZone timeZone) {

		return model.getExecutionEndDateFormat(locale, timeZone);
	}

	/**
	 * Returns the execution start date of this course.
	 *
	 * @return the execution start date of this course
	 */
	@Override
	public Date getExecutionStartDate() {
		return model.getExecutionStartDate();
	}

	@Override
	public java.util.Calendar getExecutionStartDateCalendar() {
		return model.getExecutionStartDateCalendar();
	}

	@Override
	public String getExecutionStartDateFormat(
		java.util.Locale locale, java.util.TimeZone timeZone) {

		return model.getExecutionStartDateFormat(locale, timeZone);
	}

	@Override
	public String getFriendlyURL() {
		return model.getFriendlyURL();
	}

	@Override
	public Map<java.util.Locale, String> getFriendlyURLMap()
		throws com.liferay.portal.kernel.exception.PortalException {

		return model.getFriendlyURLMap();
	}

	@Override
	public String getFriendlyURLsXML()
		throws com.liferay.portal.kernel.exception.PortalException {

		return model.getFriendlyURLsXML();
	}

	/**
	 * Returns the goodbye of this course.
	 *
	 * @return the goodbye of this course
	 */
	@Override
	public boolean getGoodbye() {
		return model.getGoodbye();
	}

	/**
	 * Returns the goodbye msg of this course.
	 *
	 * @return the goodbye msg of this course
	 */
	@Override
	public String getGoodbyeMsg() {
		return model.getGoodbyeMsg();
	}

	/**
	 * Returns the localized goodbye msg of this course in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized goodbye msg of this course
	 */
	@Override
	public String getGoodbyeMsg(java.util.Locale locale) {
		return model.getGoodbyeMsg(locale);
	}

	/**
	 * Returns the localized goodbye msg of this course in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized goodbye msg of this course. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getGoodbyeMsg(java.util.Locale locale, boolean useDefault) {
		return model.getGoodbyeMsg(locale, useDefault);
	}

	/**
	 * Returns the localized goodbye msg of this course in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized goodbye msg of this course
	 */
	@Override
	public String getGoodbyeMsg(String languageId) {
		return model.getGoodbyeMsg(languageId);
	}

	/**
	 * Returns the localized goodbye msg of this course in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized goodbye msg of this course
	 */
	@Override
	public String getGoodbyeMsg(String languageId, boolean useDefault) {
		return model.getGoodbyeMsg(languageId, useDefault);
	}

	@Override
	public String getGoodbyeMsgCurrentLanguageId() {
		return model.getGoodbyeMsgCurrentLanguageId();
	}

	@Override
	public String getGoodbyeMsgCurrentValue() {
		return model.getGoodbyeMsgCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized goodbye msgs of this course.
	 *
	 * @return the locales and localized goodbye msgs of this course
	 */
	@Override
	public Map<java.util.Locale, String> getGoodbyeMsgMap() {
		return model.getGoodbyeMsgMap();
	}

	@Override
	public String getGoodbyeMsgMapAsXML() {
		return model.getGoodbyeMsgMapAsXML();
	}

	/**
	 * Returns the goodbye subject of this course.
	 *
	 * @return the goodbye subject of this course
	 */
	@Override
	public String getGoodbyeSubject() {
		return model.getGoodbyeSubject();
	}

	/**
	 * Returns the localized goodbye subject of this course in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized goodbye subject of this course
	 */
	@Override
	public String getGoodbyeSubject(java.util.Locale locale) {
		return model.getGoodbyeSubject(locale);
	}

	/**
	 * Returns the localized goodbye subject of this course in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized goodbye subject of this course. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getGoodbyeSubject(
		java.util.Locale locale, boolean useDefault) {

		return model.getGoodbyeSubject(locale, useDefault);
	}

	/**
	 * Returns the localized goodbye subject of this course in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized goodbye subject of this course
	 */
	@Override
	public String getGoodbyeSubject(String languageId) {
		return model.getGoodbyeSubject(languageId);
	}

	/**
	 * Returns the localized goodbye subject of this course in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized goodbye subject of this course
	 */
	@Override
	public String getGoodbyeSubject(String languageId, boolean useDefault) {
		return model.getGoodbyeSubject(languageId, useDefault);
	}

	@Override
	public String getGoodbyeSubjectCurrentLanguageId() {
		return model.getGoodbyeSubjectCurrentLanguageId();
	}

	@Override
	public String getGoodbyeSubjectCurrentValue() {
		return model.getGoodbyeSubjectCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized goodbye subjects of this course.
	 *
	 * @return the locales and localized goodbye subjects of this course
	 */
	@Override
	public Map<java.util.Locale, String> getGoodbyeSubjectMap() {
		return model.getGoodbyeSubjectMap();
	}

	@Override
	public com.liferay.portal.kernel.model.Group getGroup() {
		return model.getGroup();
	}

	/**
	 * Returns the group created ID of this course.
	 *
	 * @return the group created ID of this course
	 */
	@Override
	public long getGroupCreatedId() {
		return model.getGroupCreatedId();
	}

	/**
	 * Returns the group ID of this course.
	 *
	 * @return the group ID of this course
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the inscription type of this course.
	 *
	 * @return the inscription type of this course
	 */
	@Override
	public long getInscriptionType() {
		return model.getInscriptionType();
	}

	/**
	 * Returns the last publish date of this course.
	 *
	 * @return the last publish date of this course
	 */
	@Override
	public Date getLastPublishDate() {
		return model.getLastPublishDate();
	}

	@Override
	public com.liferay.portal.kernel.model.LayoutSet getLayoutSet() {
		return model.getLayoutSet();
	}

	@Override
	public long getLayoutSetPrototypeId() {
		return model.getLayoutSetPrototypeId();
	}

	/**
	 * Returns the max users of this course.
	 *
	 * @return the max users of this course
	 */
	@Override
	public int getMaxUsers() {
		return model.getMaxUsers();
	}

	/**
	 * Returns the modified date of this course.
	 *
	 * @return the modified date of this course
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the parent course ID of this course.
	 *
	 * @return the parent course ID of this course
	 */
	@Override
	public long getParentCourseId() {
		return model.getParentCourseId();
	}

	/**
	 * Returns the primary key of this course.
	 *
	 * @return the primary key of this course
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the registration end date of this course.
	 *
	 * @return the registration end date of this course
	 */
	@Override
	public Date getRegistrationEndDate() {
		return model.getRegistrationEndDate();
	}

	/**
	 * Returns the registration start date of this course.
	 *
	 * @return the registration start date of this course
	 */
	@Override
	public Date getRegistrationStartDate() {
		return model.getRegistrationStartDate();
	}

	@Override
	public CourseResult getResultUser(long userId)
		throws com.ted.lms.exception.NoSuchCourseResultException {

		return model.getResultUser(userId);
	}

	/**
	 * Returns the small image ID of this course.
	 *
	 * @return the small image ID of this course
	 */
	@Override
	public long getSmallImageId() {
		return model.getSmallImageId();
	}

	@Override
	public String getSmallImageURL(
			com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException {

		return model.getSmallImageURL(themeDisplay);
	}

	/**
	 * Returns the status of this course.
	 *
	 * @return the status of this course
	 */
	@Override
	public int getStatus() {
		return model.getStatus();
	}

	/**
	 * Returns the status by user ID of this course.
	 *
	 * @return the status by user ID of this course
	 */
	@Override
	public long getStatusByUserId() {
		return model.getStatusByUserId();
	}

	/**
	 * Returns the status by user name of this course.
	 *
	 * @return the status by user name of this course
	 */
	@Override
	public String getStatusByUserName() {
		return model.getStatusByUserName();
	}

	/**
	 * Returns the status by user uuid of this course.
	 *
	 * @return the status by user uuid of this course
	 */
	@Override
	public String getStatusByUserUuid() {
		return model.getStatusByUserUuid();
	}

	/**
	 * Returns the status date of this course.
	 *
	 * @return the status date of this course
	 */
	@Override
	public Date getStatusDate() {
		return model.getStatusDate();
	}

	/**
	 * Returns the title of this course.
	 *
	 * @return the title of this course
	 */
	@Override
	public String getTitle() {
		return model.getTitle();
	}

	/**
	 * Returns the localized title of this course in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized title of this course
	 */
	@Override
	public String getTitle(java.util.Locale locale) {
		return model.getTitle(locale);
	}

	/**
	 * Returns the localized title of this course in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized title of this course. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getTitle(java.util.Locale locale, boolean useDefault) {
		return model.getTitle(locale, useDefault);
	}

	/**
	 * Returns the localized title of this course in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized title of this course
	 */
	@Override
	public String getTitle(String languageId) {
		return model.getTitle(languageId);
	}

	/**
	 * Returns the localized title of this course in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized title of this course
	 */
	@Override
	public String getTitle(String languageId, boolean useDefault) {
		return model.getTitle(languageId, useDefault);
	}

	@Override
	public String getTitleCurrentLanguageId() {
		return model.getTitleCurrentLanguageId();
	}

	@Override
	public String getTitleCurrentValue() {
		return model.getTitleCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized titles of this course.
	 *
	 * @return the locales and localized titles of this course
	 */
	@Override
	public Map<java.util.Locale, String> getTitleMap() {
		return model.getTitleMap();
	}

	/**
	 * Returns the trash entry created when this course was moved to the Recycle Bin. The trash entry may belong to one of the ancestors of this course.
	 *
	 * @return the trash entry created when this course was moved to the Recycle Bin
	 */
	@Override
	public com.liferay.trash.kernel.model.TrashEntry getTrashEntry()
		throws com.liferay.portal.kernel.exception.PortalException {

		return model.getTrashEntry();
	}

	/**
	 * Returns the class primary key of the trash entry for this course.
	 *
	 * @return the class primary key of the trash entry for this course
	 */
	@Override
	public long getTrashEntryClassPK() {
		return model.getTrashEntryClassPK();
	}

	/**
	 * Returns the trash handler for this course.
	 *
	 * @return the trash handler for this course
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public com.liferay.portal.kernel.trash.TrashHandler getTrashHandler() {
		return model.getTrashHandler();
	}

	@Override
	public int getTypeSite() {
		return model.getTypeSite();
	}

	/**
	 * Returns the user ID of this course.
	 *
	 * @return the user ID of this course
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this course.
	 *
	 * @return the user name of this course
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this course.
	 *
	 * @return the user uuid of this course
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the uuid of this course.
	 *
	 * @return the uuid of this course
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	/**
	 * Returns the welcome of this course.
	 *
	 * @return the welcome of this course
	 */
	@Override
	public boolean getWelcome() {
		return model.getWelcome();
	}

	/**
	 * Returns the welcome msg of this course.
	 *
	 * @return the welcome msg of this course
	 */
	@Override
	public String getWelcomeMsg() {
		return model.getWelcomeMsg();
	}

	/**
	 * Returns the localized welcome msg of this course in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized welcome msg of this course
	 */
	@Override
	public String getWelcomeMsg(java.util.Locale locale) {
		return model.getWelcomeMsg(locale);
	}

	/**
	 * Returns the localized welcome msg of this course in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized welcome msg of this course. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getWelcomeMsg(java.util.Locale locale, boolean useDefault) {
		return model.getWelcomeMsg(locale, useDefault);
	}

	/**
	 * Returns the localized welcome msg of this course in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized welcome msg of this course
	 */
	@Override
	public String getWelcomeMsg(String languageId) {
		return model.getWelcomeMsg(languageId);
	}

	/**
	 * Returns the localized welcome msg of this course in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized welcome msg of this course
	 */
	@Override
	public String getWelcomeMsg(String languageId, boolean useDefault) {
		return model.getWelcomeMsg(languageId, useDefault);
	}

	@Override
	public String getWelcomeMsgCurrentLanguageId() {
		return model.getWelcomeMsgCurrentLanguageId();
	}

	@Override
	public String getWelcomeMsgCurrentValue() {
		return model.getWelcomeMsgCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized welcome msgs of this course.
	 *
	 * @return the locales and localized welcome msgs of this course
	 */
	@Override
	public Map<java.util.Locale, String> getWelcomeMsgMap() {
		return model.getWelcomeMsgMap();
	}

	@Override
	public String getWelcomeMsgMapAsXML() {
		return model.getWelcomeMsgMapAsXML();
	}

	/**
	 * Returns the welcome subject of this course.
	 *
	 * @return the welcome subject of this course
	 */
	@Override
	public String getWelcomeSubject() {
		return model.getWelcomeSubject();
	}

	/**
	 * Returns the localized welcome subject of this course in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized welcome subject of this course
	 */
	@Override
	public String getWelcomeSubject(java.util.Locale locale) {
		return model.getWelcomeSubject(locale);
	}

	/**
	 * Returns the localized welcome subject of this course in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized welcome subject of this course. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getWelcomeSubject(
		java.util.Locale locale, boolean useDefault) {

		return model.getWelcomeSubject(locale, useDefault);
	}

	/**
	 * Returns the localized welcome subject of this course in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized welcome subject of this course
	 */
	@Override
	public String getWelcomeSubject(String languageId) {
		return model.getWelcomeSubject(languageId);
	}

	/**
	 * Returns the localized welcome subject of this course in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized welcome subject of this course
	 */
	@Override
	public String getWelcomeSubject(String languageId, boolean useDefault) {
		return model.getWelcomeSubject(languageId, useDefault);
	}

	@Override
	public String getWelcomeSubjectCurrentLanguageId() {
		return model.getWelcomeSubjectCurrentLanguageId();
	}

	@Override
	public String getWelcomeSubjectCurrentValue() {
		return model.getWelcomeSubjectCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized welcome subjects of this course.
	 *
	 * @return the locales and localized welcome subjects of this course
	 */
	@Override
	public Map<java.util.Locale, String> getWelcomeSubjectMap() {
		return model.getWelcomeSubjectMap();
	}

	@Override
	public boolean hasPermissionAccessCourseFinished(long userId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return model.hasPermissionAccessCourseFinished(userId);
	}

	/**
	 * Returns <code>true</code> if this course is approved.
	 *
	 * @return <code>true</code> if this course is approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isApproved() {
		return model.isApproved();
	}

	/**
	 * Returns <code>true</code> if this course is denied.
	 *
	 * @return <code>true</code> if this course is denied; <code>false</code> otherwise
	 */
	@Override
	public boolean isDenied() {
		return model.isDenied();
	}

	/**
	 * Returns <code>true</code> if this course is denied inscription.
	 *
	 * @return <code>true</code> if this course is denied inscription; <code>false</code> otherwise
	 */
	@Override
	public boolean isDeniedInscription() {
		return model.isDeniedInscription();
	}

	/**
	 * Returns <code>true</code> if this course is a draft.
	 *
	 * @return <code>true</code> if this course is a draft; <code>false</code> otherwise
	 */
	@Override
	public boolean isDraft() {
		return model.isDraft();
	}

	/**
	 * Returns <code>true</code> if this course is expired.
	 *
	 * @return <code>true</code> if this course is expired; <code>false</code> otherwise
	 */
	@Override
	public boolean isExpired() {
		return model.isExpired();
	}

	/**
	 * Returns <code>true</code> if this course is goodbye.
	 *
	 * @return <code>true</code> if this course is goodbye; <code>false</code> otherwise
	 */
	@Override
	public boolean isGoodbye() {
		return model.isGoodbye();
	}

	/**
	 * Returns <code>true</code> if this course is inactive.
	 *
	 * @return <code>true</code> if this course is inactive; <code>false</code> otherwise
	 */
	@Override
	public boolean isInactive() {
		return model.isInactive();
	}

	/**
	 * Returns <code>true</code> if this course is incomplete.
	 *
	 * @return <code>true</code> if this course is incomplete; <code>false</code> otherwise
	 */
	@Override
	public boolean isIncomplete() {
		return model.isIncomplete();
	}

	/**
	 * Returns <code>true</code> if this course is in the Recycle Bin.
	 *
	 * @return <code>true</code> if this course is in the Recycle Bin; <code>false</code> otherwise
	 */
	@Override
	public boolean isInTrash() {
		return model.isInTrash();
	}

	/**
	 * Returns <code>true</code> if the parent of this course is in the Recycle Bin.
	 *
	 * @return <code>true</code> if the parent of this course is in the Recycle Bin; <code>false</code> otherwise
	 */
	@Override
	public boolean isInTrashContainer() {
		return model.isInTrashContainer();
	}

	@Override
	public boolean isInTrashExplicitly() {
		return model.isInTrashExplicitly();
	}

	@Override
	public boolean isInTrashImplicitly() {
		return model.isInTrashImplicitly();
	}

	@Override
	public boolean isLocked(
		com.liferay.portal.kernel.model.User user,
		com.liferay.portal.kernel.security.permission.PermissionChecker
			permissionChecker) {

		return model.isLocked(user, permissionChecker);
	}

	/**
	 * Returns <code>true</code> if this course is pending.
	 *
	 * @return <code>true</code> if this course is pending; <code>false</code> otherwise
	 */
	@Override
	public boolean isPending() {
		return model.isPending();
	}

	@Override
	public boolean isRegistrationOnDate() {
		return model.isRegistrationOnDate();
	}

	@Override
	public boolean isRegistredUser(long userId) {
		return model.isRegistredUser(userId);
	}

	/**
	 * Returns <code>true</code> if this course is scheduled.
	 *
	 * @return <code>true</code> if this course is scheduled; <code>false</code> otherwise
	 */
	@Override
	public boolean isScheduled() {
		return model.isScheduled();
	}

	@Override
	public boolean isTypeSiteOpen() {
		return model.isTypeSiteOpen();
	}

	@Override
	public boolean isTypeSitePrivate() {
		return model.isTypeSitePrivate();
	}

	@Override
	public boolean isTypeSiteRestricted() {
		return model.isTypeSiteRestricted();
	}

	/**
	 * Returns <code>true</code> if this course is welcome.
	 *
	 * @return <code>true</code> if this course is welcome; <code>false</code> otherwise
	 */
	@Override
	public boolean isWelcome() {
		return model.isWelcome();
	}

	@Override
	public void persist() {
		model.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {

		model.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
			java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {

		model.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	/**
	 * Sets the calification type of this course.
	 *
	 * @param calificationType the calification type of this course
	 */
	@Override
	public void setCalificationType(long calificationType) {
		model.setCalificationType(calificationType);
	}

	/**
	 * Sets the company ID of this course.
	 *
	 * @param companyId the company ID of this course
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the course eval ID of this course.
	 *
	 * @param courseEvalId the course eval ID of this course
	 */
	@Override
	public void setCourseEvalId(long courseEvalId) {
		model.setCourseEvalId(courseEvalId);
	}

	/**
	 * Sets the course extra data of this course.
	 *
	 * @param courseExtraData the course extra data of this course
	 */
	@Override
	public void setCourseExtraData(String courseExtraData) {
		model.setCourseExtraData(courseExtraData);
	}

	/**
	 * Sets the course ID of this course.
	 *
	 * @param courseId the course ID of this course
	 */
	@Override
	public void setCourseId(long courseId) {
		model.setCourseId(courseId);
	}

	/**
	 * Sets the create date of this course.
	 *
	 * @param createDate the create date of this course
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets whether this course is denied inscription.
	 *
	 * @param deniedInscription the denied inscription of this course
	 */
	@Override
	public void setDeniedInscription(boolean deniedInscription) {
		model.setDeniedInscription(deniedInscription);
	}

	/**
	 * Sets the denied inscription msg of this course.
	 *
	 * @param deniedInscriptionMsg the denied inscription msg of this course
	 */
	@Override
	public void setDeniedInscriptionMsg(String deniedInscriptionMsg) {
		model.setDeniedInscriptionMsg(deniedInscriptionMsg);
	}

	/**
	 * Sets the localized denied inscription msg of this course in the language.
	 *
	 * @param deniedInscriptionMsg the localized denied inscription msg of this course
	 * @param locale the locale of the language
	 */
	@Override
	public void setDeniedInscriptionMsg(
		String deniedInscriptionMsg, java.util.Locale locale) {

		model.setDeniedInscriptionMsg(deniedInscriptionMsg, locale);
	}

	/**
	 * Sets the localized denied inscription msg of this course in the language, and sets the default locale.
	 *
	 * @param deniedInscriptionMsg the localized denied inscription msg of this course
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setDeniedInscriptionMsg(
		String deniedInscriptionMsg, java.util.Locale locale,
		java.util.Locale defaultLocale) {

		model.setDeniedInscriptionMsg(
			deniedInscriptionMsg, locale, defaultLocale);
	}

	@Override
	public void setDeniedInscriptionMsgCurrentLanguageId(String languageId) {
		model.setDeniedInscriptionMsgCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized denied inscription msgs of this course from the map of locales and localized denied inscription msgs.
	 *
	 * @param deniedInscriptionMsgMap the locales and localized denied inscription msgs of this course
	 */
	@Override
	public void setDeniedInscriptionMsgMap(
		Map<java.util.Locale, String> deniedInscriptionMsgMap) {

		model.setDeniedInscriptionMsgMap(deniedInscriptionMsgMap);
	}

	/**
	 * Sets the localized denied inscription msgs of this course from the map of locales and localized denied inscription msgs, and sets the default locale.
	 *
	 * @param deniedInscriptionMsgMap the locales and localized denied inscription msgs of this course
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setDeniedInscriptionMsgMap(
		Map<java.util.Locale, String> deniedInscriptionMsgMap,
		java.util.Locale defaultLocale) {

		model.setDeniedInscriptionMsgMap(
			deniedInscriptionMsgMap, defaultLocale);
	}

	/**
	 * Sets the denied inscription subject of this course.
	 *
	 * @param deniedInscriptionSubject the denied inscription subject of this course
	 */
	@Override
	public void setDeniedInscriptionSubject(String deniedInscriptionSubject) {
		model.setDeniedInscriptionSubject(deniedInscriptionSubject);
	}

	/**
	 * Sets the localized denied inscription subject of this course in the language.
	 *
	 * @param deniedInscriptionSubject the localized denied inscription subject of this course
	 * @param locale the locale of the language
	 */
	@Override
	public void setDeniedInscriptionSubject(
		String deniedInscriptionSubject, java.util.Locale locale) {

		model.setDeniedInscriptionSubject(deniedInscriptionSubject, locale);
	}

	/**
	 * Sets the localized denied inscription subject of this course in the language, and sets the default locale.
	 *
	 * @param deniedInscriptionSubject the localized denied inscription subject of this course
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setDeniedInscriptionSubject(
		String deniedInscriptionSubject, java.util.Locale locale,
		java.util.Locale defaultLocale) {

		model.setDeniedInscriptionSubject(
			deniedInscriptionSubject, locale, defaultLocale);
	}

	@Override
	public void setDeniedInscriptionSubjectCurrentLanguageId(
		String languageId) {

		model.setDeniedInscriptionSubjectCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized denied inscription subjects of this course from the map of locales and localized denied inscription subjects.
	 *
	 * @param deniedInscriptionSubjectMap the locales and localized denied inscription subjects of this course
	 */
	@Override
	public void setDeniedInscriptionSubjectMap(
		Map<java.util.Locale, String> deniedInscriptionSubjectMap) {

		model.setDeniedInscriptionSubjectMap(deniedInscriptionSubjectMap);
	}

	/**
	 * Sets the localized denied inscription subjects of this course from the map of locales and localized denied inscription subjects, and sets the default locale.
	 *
	 * @param deniedInscriptionSubjectMap the locales and localized denied inscription subjects of this course
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setDeniedInscriptionSubjectMap(
		Map<java.util.Locale, String> deniedInscriptionSubjectMap,
		java.util.Locale defaultLocale) {

		model.setDeniedInscriptionSubjectMap(
			deniedInscriptionSubjectMap, defaultLocale);
	}

	/**
	 * Sets the description of this course.
	 *
	 * @param description the description of this course
	 */
	@Override
	public void setDescription(String description) {
		model.setDescription(description);
	}

	/**
	 * Sets the localized description of this course in the language.
	 *
	 * @param description the localized description of this course
	 * @param locale the locale of the language
	 */
	@Override
	public void setDescription(String description, java.util.Locale locale) {
		model.setDescription(description, locale);
	}

	/**
	 * Sets the localized description of this course in the language, and sets the default locale.
	 *
	 * @param description the localized description of this course
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setDescription(
		String description, java.util.Locale locale,
		java.util.Locale defaultLocale) {

		model.setDescription(description, locale, defaultLocale);
	}

	@Override
	public void setDescriptionCurrentLanguageId(String languageId) {
		model.setDescriptionCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized descriptions of this course from the map of locales and localized descriptions.
	 *
	 * @param descriptionMap the locales and localized descriptions of this course
	 */
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, String> descriptionMap) {

		model.setDescriptionMap(descriptionMap);
	}

	/**
	 * Sets the localized descriptions of this course from the map of locales and localized descriptions, and sets the default locale.
	 *
	 * @param descriptionMap the locales and localized descriptions of this course
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, String> descriptionMap,
		java.util.Locale defaultLocale) {

		model.setDescriptionMap(descriptionMap, defaultLocale);
	}

	/**
	 * Sets the execution end date of this course.
	 *
	 * @param executionEndDate the execution end date of this course
	 */
	@Override
	public void setExecutionEndDate(Date executionEndDate) {
		model.setExecutionEndDate(executionEndDate);
	}

	/**
	 * Sets the execution start date of this course.
	 *
	 * @param executionStartDate the execution start date of this course
	 */
	@Override
	public void setExecutionStartDate(Date executionStartDate) {
		model.setExecutionStartDate(executionStartDate);
	}

	/**
	 * Sets whether this course is goodbye.
	 *
	 * @param goodbye the goodbye of this course
	 */
	@Override
	public void setGoodbye(boolean goodbye) {
		model.setGoodbye(goodbye);
	}

	/**
	 * Sets the goodbye msg of this course.
	 *
	 * @param goodbyeMsg the goodbye msg of this course
	 */
	@Override
	public void setGoodbyeMsg(String goodbyeMsg) {
		model.setGoodbyeMsg(goodbyeMsg);
	}

	/**
	 * Sets the localized goodbye msg of this course in the language.
	 *
	 * @param goodbyeMsg the localized goodbye msg of this course
	 * @param locale the locale of the language
	 */
	@Override
	public void setGoodbyeMsg(String goodbyeMsg, java.util.Locale locale) {
		model.setGoodbyeMsg(goodbyeMsg, locale);
	}

	/**
	 * Sets the localized goodbye msg of this course in the language, and sets the default locale.
	 *
	 * @param goodbyeMsg the localized goodbye msg of this course
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setGoodbyeMsg(
		String goodbyeMsg, java.util.Locale locale,
		java.util.Locale defaultLocale) {

		model.setGoodbyeMsg(goodbyeMsg, locale, defaultLocale);
	}

	@Override
	public void setGoodbyeMsgCurrentLanguageId(String languageId) {
		model.setGoodbyeMsgCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized goodbye msgs of this course from the map of locales and localized goodbye msgs.
	 *
	 * @param goodbyeMsgMap the locales and localized goodbye msgs of this course
	 */
	@Override
	public void setGoodbyeMsgMap(Map<java.util.Locale, String> goodbyeMsgMap) {
		model.setGoodbyeMsgMap(goodbyeMsgMap);
	}

	/**
	 * Sets the localized goodbye msgs of this course from the map of locales and localized goodbye msgs, and sets the default locale.
	 *
	 * @param goodbyeMsgMap the locales and localized goodbye msgs of this course
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setGoodbyeMsgMap(
		Map<java.util.Locale, String> goodbyeMsgMap,
		java.util.Locale defaultLocale) {

		model.setGoodbyeMsgMap(goodbyeMsgMap, defaultLocale);
	}

	/**
	 * Sets the goodbye subject of this course.
	 *
	 * @param goodbyeSubject the goodbye subject of this course
	 */
	@Override
	public void setGoodbyeSubject(String goodbyeSubject) {
		model.setGoodbyeSubject(goodbyeSubject);
	}

	/**
	 * Sets the localized goodbye subject of this course in the language.
	 *
	 * @param goodbyeSubject the localized goodbye subject of this course
	 * @param locale the locale of the language
	 */
	@Override
	public void setGoodbyeSubject(
		String goodbyeSubject, java.util.Locale locale) {

		model.setGoodbyeSubject(goodbyeSubject, locale);
	}

	/**
	 * Sets the localized goodbye subject of this course in the language, and sets the default locale.
	 *
	 * @param goodbyeSubject the localized goodbye subject of this course
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setGoodbyeSubject(
		String goodbyeSubject, java.util.Locale locale,
		java.util.Locale defaultLocale) {

		model.setGoodbyeSubject(goodbyeSubject, locale, defaultLocale);
	}

	@Override
	public void setGoodbyeSubjectCurrentLanguageId(String languageId) {
		model.setGoodbyeSubjectCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized goodbye subjects of this course from the map of locales and localized goodbye subjects.
	 *
	 * @param goodbyeSubjectMap the locales and localized goodbye subjects of this course
	 */
	@Override
	public void setGoodbyeSubjectMap(
		Map<java.util.Locale, String> goodbyeSubjectMap) {

		model.setGoodbyeSubjectMap(goodbyeSubjectMap);
	}

	/**
	 * Sets the localized goodbye subjects of this course from the map of locales and localized goodbye subjects, and sets the default locale.
	 *
	 * @param goodbyeSubjectMap the locales and localized goodbye subjects of this course
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setGoodbyeSubjectMap(
		Map<java.util.Locale, String> goodbyeSubjectMap,
		java.util.Locale defaultLocale) {

		model.setGoodbyeSubjectMap(goodbyeSubjectMap, defaultLocale);
	}

	/**
	 * Sets the group created ID of this course.
	 *
	 * @param groupCreatedId the group created ID of this course
	 */
	@Override
	public void setGroupCreatedId(long groupCreatedId) {
		model.setGroupCreatedId(groupCreatedId);
	}

	/**
	 * Sets the group ID of this course.
	 *
	 * @param groupId the group ID of this course
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the inscription type of this course.
	 *
	 * @param inscriptionType the inscription type of this course
	 */
	@Override
	public void setInscriptionType(long inscriptionType) {
		model.setInscriptionType(inscriptionType);
	}

	/**
	 * Sets the last publish date of this course.
	 *
	 * @param lastPublishDate the last publish date of this course
	 */
	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		model.setLastPublishDate(lastPublishDate);
	}

	/**
	 * Sets the max users of this course.
	 *
	 * @param maxUsers the max users of this course
	 */
	@Override
	public void setMaxUsers(int maxUsers) {
		model.setMaxUsers(maxUsers);
	}

	/**
	 * Sets the modified date of this course.
	 *
	 * @param modifiedDate the modified date of this course
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the parent course ID of this course.
	 *
	 * @param parentCourseId the parent course ID of this course
	 */
	@Override
	public void setParentCourseId(long parentCourseId) {
		model.setParentCourseId(parentCourseId);
	}

	/**
	 * Sets the primary key of this course.
	 *
	 * @param primaryKey the primary key of this course
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the registration end date of this course.
	 *
	 * @param registrationEndDate the registration end date of this course
	 */
	@Override
	public void setRegistrationEndDate(Date registrationEndDate) {
		model.setRegistrationEndDate(registrationEndDate);
	}

	/**
	 * Sets the registration start date of this course.
	 *
	 * @param registrationStartDate the registration start date of this course
	 */
	@Override
	public void setRegistrationStartDate(Date registrationStartDate) {
		model.setRegistrationStartDate(registrationStartDate);
	}

	/**
	 * Sets the small image ID of this course.
	 *
	 * @param smallImageId the small image ID of this course
	 */
	@Override
	public void setSmallImageId(long smallImageId) {
		model.setSmallImageId(smallImageId);
	}

	/**
	 * Sets the status of this course.
	 *
	 * @param status the status of this course
	 */
	@Override
	public void setStatus(int status) {
		model.setStatus(status);
	}

	/**
	 * Sets the status by user ID of this course.
	 *
	 * @param statusByUserId the status by user ID of this course
	 */
	@Override
	public void setStatusByUserId(long statusByUserId) {
		model.setStatusByUserId(statusByUserId);
	}

	/**
	 * Sets the status by user name of this course.
	 *
	 * @param statusByUserName the status by user name of this course
	 */
	@Override
	public void setStatusByUserName(String statusByUserName) {
		model.setStatusByUserName(statusByUserName);
	}

	/**
	 * Sets the status by user uuid of this course.
	 *
	 * @param statusByUserUuid the status by user uuid of this course
	 */
	@Override
	public void setStatusByUserUuid(String statusByUserUuid) {
		model.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	 * Sets the status date of this course.
	 *
	 * @param statusDate the status date of this course
	 */
	@Override
	public void setStatusDate(Date statusDate) {
		model.setStatusDate(statusDate);
	}

	/**
	 * Sets the title of this course.
	 *
	 * @param title the title of this course
	 */
	@Override
	public void setTitle(String title) {
		model.setTitle(title);
	}

	/**
	 * Sets the localized title of this course in the language.
	 *
	 * @param title the localized title of this course
	 * @param locale the locale of the language
	 */
	@Override
	public void setTitle(String title, java.util.Locale locale) {
		model.setTitle(title, locale);
	}

	/**
	 * Sets the localized title of this course in the language, and sets the default locale.
	 *
	 * @param title the localized title of this course
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setTitle(
		String title, java.util.Locale locale, java.util.Locale defaultLocale) {

		model.setTitle(title, locale, defaultLocale);
	}

	@Override
	public void setTitleCurrentLanguageId(String languageId) {
		model.setTitleCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized titles of this course from the map of locales and localized titles.
	 *
	 * @param titleMap the locales and localized titles of this course
	 */
	@Override
	public void setTitleMap(Map<java.util.Locale, String> titleMap) {
		model.setTitleMap(titleMap);
	}

	/**
	 * Sets the localized titles of this course from the map of locales and localized titles, and sets the default locale.
	 *
	 * @param titleMap the locales and localized titles of this course
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setTitleMap(
		Map<java.util.Locale, String> titleMap,
		java.util.Locale defaultLocale) {

		model.setTitleMap(titleMap, defaultLocale);
	}

	/**
	 * Sets the user ID of this course.
	 *
	 * @param userId the user ID of this course
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this course.
	 *
	 * @param userName the user name of this course
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this course.
	 *
	 * @param userUuid the user uuid of this course
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this course.
	 *
	 * @param uuid the uuid of this course
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	/**
	 * Sets whether this course is welcome.
	 *
	 * @param welcome the welcome of this course
	 */
	@Override
	public void setWelcome(boolean welcome) {
		model.setWelcome(welcome);
	}

	/**
	 * Sets the welcome msg of this course.
	 *
	 * @param welcomeMsg the welcome msg of this course
	 */
	@Override
	public void setWelcomeMsg(String welcomeMsg) {
		model.setWelcomeMsg(welcomeMsg);
	}

	/**
	 * Sets the localized welcome msg of this course in the language.
	 *
	 * @param welcomeMsg the localized welcome msg of this course
	 * @param locale the locale of the language
	 */
	@Override
	public void setWelcomeMsg(String welcomeMsg, java.util.Locale locale) {
		model.setWelcomeMsg(welcomeMsg, locale);
	}

	/**
	 * Sets the localized welcome msg of this course in the language, and sets the default locale.
	 *
	 * @param welcomeMsg the localized welcome msg of this course
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setWelcomeMsg(
		String welcomeMsg, java.util.Locale locale,
		java.util.Locale defaultLocale) {

		model.setWelcomeMsg(welcomeMsg, locale, defaultLocale);
	}

	@Override
	public void setWelcomeMsgCurrentLanguageId(String languageId) {
		model.setWelcomeMsgCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized welcome msgs of this course from the map of locales and localized welcome msgs.
	 *
	 * @param welcomeMsgMap the locales and localized welcome msgs of this course
	 */
	@Override
	public void setWelcomeMsgMap(Map<java.util.Locale, String> welcomeMsgMap) {
		model.setWelcomeMsgMap(welcomeMsgMap);
	}

	/**
	 * Sets the localized welcome msgs of this course from the map of locales and localized welcome msgs, and sets the default locale.
	 *
	 * @param welcomeMsgMap the locales and localized welcome msgs of this course
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setWelcomeMsgMap(
		Map<java.util.Locale, String> welcomeMsgMap,
		java.util.Locale defaultLocale) {

		model.setWelcomeMsgMap(welcomeMsgMap, defaultLocale);
	}

	/**
	 * Sets the welcome subject of this course.
	 *
	 * @param welcomeSubject the welcome subject of this course
	 */
	@Override
	public void setWelcomeSubject(String welcomeSubject) {
		model.setWelcomeSubject(welcomeSubject);
	}

	/**
	 * Sets the localized welcome subject of this course in the language.
	 *
	 * @param welcomeSubject the localized welcome subject of this course
	 * @param locale the locale of the language
	 */
	@Override
	public void setWelcomeSubject(
		String welcomeSubject, java.util.Locale locale) {

		model.setWelcomeSubject(welcomeSubject, locale);
	}

	/**
	 * Sets the localized welcome subject of this course in the language, and sets the default locale.
	 *
	 * @param welcomeSubject the localized welcome subject of this course
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setWelcomeSubject(
		String welcomeSubject, java.util.Locale locale,
		java.util.Locale defaultLocale) {

		model.setWelcomeSubject(welcomeSubject, locale, defaultLocale);
	}

	@Override
	public void setWelcomeSubjectCurrentLanguageId(String languageId) {
		model.setWelcomeSubjectCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized welcome subjects of this course from the map of locales and localized welcome subjects.
	 *
	 * @param welcomeSubjectMap the locales and localized welcome subjects of this course
	 */
	@Override
	public void setWelcomeSubjectMap(
		Map<java.util.Locale, String> welcomeSubjectMap) {

		model.setWelcomeSubjectMap(welcomeSubjectMap);
	}

	/**
	 * Sets the localized welcome subjects of this course from the map of locales and localized welcome subjects, and sets the default locale.
	 *
	 * @param welcomeSubjectMap the locales and localized welcome subjects of this course
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setWelcomeSubjectMap(
		Map<java.util.Locale, String> welcomeSubjectMap,
		java.util.Locale defaultLocale) {

		model.setWelcomeSubjectMap(welcomeSubjectMap, defaultLocale);
	}

	@Override
	public StagedModelType getStagedModelType() {
		return model.getStagedModelType();
	}

	@Override
	protected CourseWrapper wrap(Course course) {
		return new CourseWrapper(course);
	}

}