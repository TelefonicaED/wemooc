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
 * This class is a wrapper for {@link Course}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Course
 * @generated
 */
@ProviderType
public class CourseWrapper implements Course, ModelWrapper<Course> {
	public CourseWrapper(Course course) {
		_course = course;
	}

	@Override
	public Class<?> getModelClass() {
		return Course.class;
	}

	@Override
	public String getModelClassName() {
		return Course.class.getName();
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
		attributes.put("parentCourseId", getParentCourseId());
		attributes.put("groupCreatedId", getGroupCreatedId());
		attributes.put("title", getTitle());
		attributes.put("description", getDescription());
		attributes.put("friendlyURL", getFriendlyURL());
		attributes.put("icon", getIcon());
		attributes.put("registrationStartDate", getRegistrationStartDate());
		attributes.put("registrationEndDate", getRegistrationEndDate());
		attributes.put("executionStartDate", getExecutionStartDate());
		attributes.put("executionEndDate", getExecutionEndDate());
		attributes.put("closed", isClosed());
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

		String friendlyURL = (String)attributes.get("friendlyURL");

		if (friendlyURL != null) {
			setFriendlyURL(friendlyURL);
		}

		Long icon = (Long)attributes.get("icon");

		if (icon != null) {
			setIcon(icon);
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

		Boolean closed = (Boolean)attributes.get("closed");

		if (closed != null) {
			setClosed(closed);
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
	public Object clone() {
		return new CourseWrapper((Course)_course.clone());
	}

	@Override
	public int compareTo(Course course) {
		return _course.compareTo(course);
	}

	@Override
	public String[] getAvailableLanguageIds() {
		return _course.getAvailableLanguageIds();
	}

	/**
	* Returns the calification type of this course.
	*
	* @return the calification type of this course
	*/
	@Override
	public long getCalificationType() {
		return _course.getCalificationType();
	}

	/**
	* Returns the closed of this course.
	*
	* @return the closed of this course
	*/
	@Override
	public boolean getClosed() {
		return _course.getClosed();
	}

	/**
	* Returns the company ID of this course.
	*
	* @return the company ID of this course
	*/
	@Override
	public long getCompanyId() {
		return _course.getCompanyId();
	}

	/**
	* Returns the course eval ID of this course.
	*
	* @return the course eval ID of this course
	*/
	@Override
	public long getCourseEvalId() {
		return _course.getCourseEvalId();
	}

	/**
	* Returns the course extra data of this course.
	*
	* @return the course extra data of this course
	*/
	@Override
	public String getCourseExtraData() {
		return _course.getCourseExtraData();
	}

	/**
	* Returns the course ID of this course.
	*
	* @return the course ID of this course
	*/
	@Override
	public long getCourseId() {
		return _course.getCourseId();
	}

	/**
	* Returns the create date of this course.
	*
	* @return the create date of this course
	*/
	@Override
	public Date getCreateDate() {
		return _course.getCreateDate();
	}

	@Override
	public String getDefaultLanguageId() {
		return _course.getDefaultLanguageId();
	}

	/**
	* Returns the description of this course.
	*
	* @return the description of this course
	*/
	@Override
	public String getDescription() {
		return _course.getDescription();
	}

	/**
	* Returns the localized description of this course in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized description of this course
	*/
	@Override
	public String getDescription(java.util.Locale locale) {
		return _course.getDescription(locale);
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
		return _course.getDescription(locale, useDefault);
	}

	/**
	* Returns the localized description of this course in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized description of this course
	*/
	@Override
	public String getDescription(String languageId) {
		return _course.getDescription(languageId);
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
		return _course.getDescription(languageId, useDefault);
	}

	@Override
	public String getDescriptionCurrentLanguageId() {
		return _course.getDescriptionCurrentLanguageId();
	}

	@Override
	public String getDescriptionCurrentValue() {
		return _course.getDescriptionCurrentValue();
	}

	/**
	* Returns a map of the locales and localized descriptions of this course.
	*
	* @return the locales and localized descriptions of this course
	*/
	@Override
	public Map<java.util.Locale, String> getDescriptionMap() {
		return _course.getDescriptionMap();
	}

	/**
	* Returns the execution end date of this course.
	*
	* @return the execution end date of this course
	*/
	@Override
	public Date getExecutionEndDate() {
		return _course.getExecutionEndDate();
	}

	/**
	* Returns the execution start date of this course.
	*
	* @return the execution start date of this course
	*/
	@Override
	public Date getExecutionStartDate() {
		return _course.getExecutionStartDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _course.getExpandoBridge();
	}

	/**
	* Returns the friendly url of this course.
	*
	* @return the friendly url of this course
	*/
	@Override
	public String getFriendlyURL() {
		return _course.getFriendlyURL();
	}

	/**
	* Returns the goodbye of this course.
	*
	* @return the goodbye of this course
	*/
	@Override
	public boolean getGoodbye() {
		return _course.getGoodbye();
	}

	/**
	* Returns the goodbye msg of this course.
	*
	* @return the goodbye msg of this course
	*/
	@Override
	public String getGoodbyeMsg() {
		return _course.getGoodbyeMsg();
	}

	/**
	* Returns the goodbye subject of this course.
	*
	* @return the goodbye subject of this course
	*/
	@Override
	public String getGoodbyeSubject() {
		return _course.getGoodbyeSubject();
	}

	/**
	* Returns the group created ID of this course.
	*
	* @return the group created ID of this course
	*/
	@Override
	public long getGroupCreatedId() {
		return _course.getGroupCreatedId();
	}

	/**
	* Returns the group ID of this course.
	*
	* @return the group ID of this course
	*/
	@Override
	public long getGroupId() {
		return _course.getGroupId();
	}

	/**
	* Returns the icon of this course.
	*
	* @return the icon of this course
	*/
	@Override
	public long getIcon() {
		return _course.getIcon();
	}

	/**
	* Returns the inscription type of this course.
	*
	* @return the inscription type of this course
	*/
	@Override
	public long getInscriptionType() {
		return _course.getInscriptionType();
	}

	/**
	* Returns the max users of this course.
	*
	* @return the max users of this course
	*/
	@Override
	public int getMaxUsers() {
		return _course.getMaxUsers();
	}

	/**
	* Returns the modified date of this course.
	*
	* @return the modified date of this course
	*/
	@Override
	public Date getModifiedDate() {
		return _course.getModifiedDate();
	}

	/**
	* Returns the parent course ID of this course.
	*
	* @return the parent course ID of this course
	*/
	@Override
	public long getParentCourseId() {
		return _course.getParentCourseId();
	}

	/**
	* Returns the primary key of this course.
	*
	* @return the primary key of this course
	*/
	@Override
	public long getPrimaryKey() {
		return _course.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _course.getPrimaryKeyObj();
	}

	/**
	* Returns the registration end date of this course.
	*
	* @return the registration end date of this course
	*/
	@Override
	public Date getRegistrationEndDate() {
		return _course.getRegistrationEndDate();
	}

	/**
	* Returns the registration start date of this course.
	*
	* @return the registration start date of this course
	*/
	@Override
	public Date getRegistrationStartDate() {
		return _course.getRegistrationStartDate();
	}

	/**
	* Returns the status of this course.
	*
	* @return the status of this course
	*/
	@Override
	public int getStatus() {
		return _course.getStatus();
	}

	/**
	* Returns the status by user ID of this course.
	*
	* @return the status by user ID of this course
	*/
	@Override
	public long getStatusByUserId() {
		return _course.getStatusByUserId();
	}

	/**
	* Returns the status by user name of this course.
	*
	* @return the status by user name of this course
	*/
	@Override
	public String getStatusByUserName() {
		return _course.getStatusByUserName();
	}

	/**
	* Returns the status by user uuid of this course.
	*
	* @return the status by user uuid of this course
	*/
	@Override
	public String getStatusByUserUuid() {
		return _course.getStatusByUserUuid();
	}

	/**
	* Returns the status date of this course.
	*
	* @return the status date of this course
	*/
	@Override
	public Date getStatusDate() {
		return _course.getStatusDate();
	}

	/**
	* Returns the title of this course.
	*
	* @return the title of this course
	*/
	@Override
	public String getTitle() {
		return _course.getTitle();
	}

	/**
	* Returns the localized title of this course in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized title of this course
	*/
	@Override
	public String getTitle(java.util.Locale locale) {
		return _course.getTitle(locale);
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
		return _course.getTitle(locale, useDefault);
	}

	/**
	* Returns the localized title of this course in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized title of this course
	*/
	@Override
	public String getTitle(String languageId) {
		return _course.getTitle(languageId);
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
		return _course.getTitle(languageId, useDefault);
	}

	@Override
	public String getTitleCurrentLanguageId() {
		return _course.getTitleCurrentLanguageId();
	}

	@Override
	public String getTitleCurrentValue() {
		return _course.getTitleCurrentValue();
	}

	/**
	* Returns a map of the locales and localized titles of this course.
	*
	* @return the locales and localized titles of this course
	*/
	@Override
	public Map<java.util.Locale, String> getTitleMap() {
		return _course.getTitleMap();
	}

	/**
	* Returns the user ID of this course.
	*
	* @return the user ID of this course
	*/
	@Override
	public long getUserId() {
		return _course.getUserId();
	}

	/**
	* Returns the user name of this course.
	*
	* @return the user name of this course
	*/
	@Override
	public String getUserName() {
		return _course.getUserName();
	}

	/**
	* Returns the user uuid of this course.
	*
	* @return the user uuid of this course
	*/
	@Override
	public String getUserUuid() {
		return _course.getUserUuid();
	}

	/**
	* Returns the uuid of this course.
	*
	* @return the uuid of this course
	*/
	@Override
	public String getUuid() {
		return _course.getUuid();
	}

	/**
	* Returns the welcome of this course.
	*
	* @return the welcome of this course
	*/
	@Override
	public boolean getWelcome() {
		return _course.getWelcome();
	}

	/**
	* Returns the welcome msg of this course.
	*
	* @return the welcome msg of this course
	*/
	@Override
	public String getWelcomeMsg() {
		return _course.getWelcomeMsg();
	}

	/**
	* Returns the welcome subject of this course.
	*
	* @return the welcome subject of this course
	*/
	@Override
	public String getWelcomeSubject() {
		return _course.getWelcomeSubject();
	}

	@Override
	public int hashCode() {
		return _course.hashCode();
	}

	/**
	* Returns <code>true</code> if this course is approved.
	*
	* @return <code>true</code> if this course is approved; <code>false</code> otherwise
	*/
	@Override
	public boolean isApproved() {
		return _course.isApproved();
	}

	@Override
	public boolean isCachedModel() {
		return _course.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this course is closed.
	*
	* @return <code>true</code> if this course is closed; <code>false</code> otherwise
	*/
	@Override
	public boolean isClosed() {
		return _course.isClosed();
	}

	/**
	* Returns <code>true</code> if this course is denied.
	*
	* @return <code>true</code> if this course is denied; <code>false</code> otherwise
	*/
	@Override
	public boolean isDenied() {
		return _course.isDenied();
	}

	/**
	* Returns <code>true</code> if this course is a draft.
	*
	* @return <code>true</code> if this course is a draft; <code>false</code> otherwise
	*/
	@Override
	public boolean isDraft() {
		return _course.isDraft();
	}

	@Override
	public boolean isEscapedModel() {
		return _course.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this course is expired.
	*
	* @return <code>true</code> if this course is expired; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpired() {
		return _course.isExpired();
	}

	/**
	* Returns <code>true</code> if this course is goodbye.
	*
	* @return <code>true</code> if this course is goodbye; <code>false</code> otherwise
	*/
	@Override
	public boolean isGoodbye() {
		return _course.isGoodbye();
	}

	/**
	* Returns <code>true</code> if this course is inactive.
	*
	* @return <code>true</code> if this course is inactive; <code>false</code> otherwise
	*/
	@Override
	public boolean isInactive() {
		return _course.isInactive();
	}

	/**
	* Returns <code>true</code> if this course is incomplete.
	*
	* @return <code>true</code> if this course is incomplete; <code>false</code> otherwise
	*/
	@Override
	public boolean isIncomplete() {
		return _course.isIncomplete();
	}

	@Override
	public boolean isNew() {
		return _course.isNew();
	}

	/**
	* Returns <code>true</code> if this course is pending.
	*
	* @return <code>true</code> if this course is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _course.isPending();
	}

	/**
	* Returns <code>true</code> if this course is scheduled.
	*
	* @return <code>true</code> if this course is scheduled; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduled() {
		return _course.isScheduled();
	}

	/**
	* Returns <code>true</code> if this course is welcome.
	*
	* @return <code>true</code> if this course is welcome; <code>false</code> otherwise
	*/
	@Override
	public boolean isWelcome() {
		return _course.isWelcome();
	}

	@Override
	public void persist() {
		_course.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {
		_course.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {
		_course.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_course.setCachedModel(cachedModel);
	}

	/**
	* Sets the calification type of this course.
	*
	* @param calificationType the calification type of this course
	*/
	@Override
	public void setCalificationType(long calificationType) {
		_course.setCalificationType(calificationType);
	}

	/**
	* Sets whether this course is closed.
	*
	* @param closed the closed of this course
	*/
	@Override
	public void setClosed(boolean closed) {
		_course.setClosed(closed);
	}

	/**
	* Sets the company ID of this course.
	*
	* @param companyId the company ID of this course
	*/
	@Override
	public void setCompanyId(long companyId) {
		_course.setCompanyId(companyId);
	}

	/**
	* Sets the course eval ID of this course.
	*
	* @param courseEvalId the course eval ID of this course
	*/
	@Override
	public void setCourseEvalId(long courseEvalId) {
		_course.setCourseEvalId(courseEvalId);
	}

	/**
	* Sets the course extra data of this course.
	*
	* @param courseExtraData the course extra data of this course
	*/
	@Override
	public void setCourseExtraData(String courseExtraData) {
		_course.setCourseExtraData(courseExtraData);
	}

	/**
	* Sets the course ID of this course.
	*
	* @param courseId the course ID of this course
	*/
	@Override
	public void setCourseId(long courseId) {
		_course.setCourseId(courseId);
	}

	/**
	* Sets the create date of this course.
	*
	* @param createDate the create date of this course
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_course.setCreateDate(createDate);
	}

	/**
	* Sets the description of this course.
	*
	* @param description the description of this course
	*/
	@Override
	public void setDescription(String description) {
		_course.setDescription(description);
	}

	/**
	* Sets the localized description of this course in the language.
	*
	* @param description the localized description of this course
	* @param locale the locale of the language
	*/
	@Override
	public void setDescription(String description, java.util.Locale locale) {
		_course.setDescription(description, locale);
	}

	/**
	* Sets the localized description of this course in the language, and sets the default locale.
	*
	* @param description the localized description of this course
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescription(String description, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_course.setDescription(description, locale, defaultLocale);
	}

	@Override
	public void setDescriptionCurrentLanguageId(String languageId) {
		_course.setDescriptionCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized descriptions of this course from the map of locales and localized descriptions.
	*
	* @param descriptionMap the locales and localized descriptions of this course
	*/
	@Override
	public void setDescriptionMap(Map<java.util.Locale, String> descriptionMap) {
		_course.setDescriptionMap(descriptionMap);
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
		_course.setDescriptionMap(descriptionMap, defaultLocale);
	}

	/**
	* Sets the execution end date of this course.
	*
	* @param executionEndDate the execution end date of this course
	*/
	@Override
	public void setExecutionEndDate(Date executionEndDate) {
		_course.setExecutionEndDate(executionEndDate);
	}

	/**
	* Sets the execution start date of this course.
	*
	* @param executionStartDate the execution start date of this course
	*/
	@Override
	public void setExecutionStartDate(Date executionStartDate) {
		_course.setExecutionStartDate(executionStartDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_course.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_course.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_course.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the friendly url of this course.
	*
	* @param friendlyURL the friendly url of this course
	*/
	@Override
	public void setFriendlyURL(String friendlyURL) {
		_course.setFriendlyURL(friendlyURL);
	}

	/**
	* Sets whether this course is goodbye.
	*
	* @param goodbye the goodbye of this course
	*/
	@Override
	public void setGoodbye(boolean goodbye) {
		_course.setGoodbye(goodbye);
	}

	/**
	* Sets the goodbye msg of this course.
	*
	* @param goodbyeMsg the goodbye msg of this course
	*/
	@Override
	public void setGoodbyeMsg(String goodbyeMsg) {
		_course.setGoodbyeMsg(goodbyeMsg);
	}

	/**
	* Sets the goodbye subject of this course.
	*
	* @param goodbyeSubject the goodbye subject of this course
	*/
	@Override
	public void setGoodbyeSubject(String goodbyeSubject) {
		_course.setGoodbyeSubject(goodbyeSubject);
	}

	/**
	* Sets the group created ID of this course.
	*
	* @param groupCreatedId the group created ID of this course
	*/
	@Override
	public void setGroupCreatedId(long groupCreatedId) {
		_course.setGroupCreatedId(groupCreatedId);
	}

	/**
	* Sets the group ID of this course.
	*
	* @param groupId the group ID of this course
	*/
	@Override
	public void setGroupId(long groupId) {
		_course.setGroupId(groupId);
	}

	/**
	* Sets the icon of this course.
	*
	* @param icon the icon of this course
	*/
	@Override
	public void setIcon(long icon) {
		_course.setIcon(icon);
	}

	/**
	* Sets the inscription type of this course.
	*
	* @param inscriptionType the inscription type of this course
	*/
	@Override
	public void setInscriptionType(long inscriptionType) {
		_course.setInscriptionType(inscriptionType);
	}

	/**
	* Sets the max users of this course.
	*
	* @param maxUsers the max users of this course
	*/
	@Override
	public void setMaxUsers(int maxUsers) {
		_course.setMaxUsers(maxUsers);
	}

	/**
	* Sets the modified date of this course.
	*
	* @param modifiedDate the modified date of this course
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_course.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_course.setNew(n);
	}

	/**
	* Sets the parent course ID of this course.
	*
	* @param parentCourseId the parent course ID of this course
	*/
	@Override
	public void setParentCourseId(long parentCourseId) {
		_course.setParentCourseId(parentCourseId);
	}

	/**
	* Sets the primary key of this course.
	*
	* @param primaryKey the primary key of this course
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_course.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_course.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the registration end date of this course.
	*
	* @param registrationEndDate the registration end date of this course
	*/
	@Override
	public void setRegistrationEndDate(Date registrationEndDate) {
		_course.setRegistrationEndDate(registrationEndDate);
	}

	/**
	* Sets the registration start date of this course.
	*
	* @param registrationStartDate the registration start date of this course
	*/
	@Override
	public void setRegistrationStartDate(Date registrationStartDate) {
		_course.setRegistrationStartDate(registrationStartDate);
	}

	/**
	* Sets the status of this course.
	*
	* @param status the status of this course
	*/
	@Override
	public void setStatus(int status) {
		_course.setStatus(status);
	}

	/**
	* Sets the status by user ID of this course.
	*
	* @param statusByUserId the status by user ID of this course
	*/
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_course.setStatusByUserId(statusByUserId);
	}

	/**
	* Sets the status by user name of this course.
	*
	* @param statusByUserName the status by user name of this course
	*/
	@Override
	public void setStatusByUserName(String statusByUserName) {
		_course.setStatusByUserName(statusByUserName);
	}

	/**
	* Sets the status by user uuid of this course.
	*
	* @param statusByUserUuid the status by user uuid of this course
	*/
	@Override
	public void setStatusByUserUuid(String statusByUserUuid) {
		_course.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Sets the status date of this course.
	*
	* @param statusDate the status date of this course
	*/
	@Override
	public void setStatusDate(Date statusDate) {
		_course.setStatusDate(statusDate);
	}

	/**
	* Sets the title of this course.
	*
	* @param title the title of this course
	*/
	@Override
	public void setTitle(String title) {
		_course.setTitle(title);
	}

	/**
	* Sets the localized title of this course in the language.
	*
	* @param title the localized title of this course
	* @param locale the locale of the language
	*/
	@Override
	public void setTitle(String title, java.util.Locale locale) {
		_course.setTitle(title, locale);
	}

	/**
	* Sets the localized title of this course in the language, and sets the default locale.
	*
	* @param title the localized title of this course
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setTitle(String title, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_course.setTitle(title, locale, defaultLocale);
	}

	@Override
	public void setTitleCurrentLanguageId(String languageId) {
		_course.setTitleCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized titles of this course from the map of locales and localized titles.
	*
	* @param titleMap the locales and localized titles of this course
	*/
	@Override
	public void setTitleMap(Map<java.util.Locale, String> titleMap) {
		_course.setTitleMap(titleMap);
	}

	/**
	* Sets the localized titles of this course from the map of locales and localized titles, and sets the default locale.
	*
	* @param titleMap the locales and localized titles of this course
	* @param defaultLocale the default locale
	*/
	@Override
	public void setTitleMap(Map<java.util.Locale, String> titleMap,
		java.util.Locale defaultLocale) {
		_course.setTitleMap(titleMap, defaultLocale);
	}

	/**
	* Sets the user ID of this course.
	*
	* @param userId the user ID of this course
	*/
	@Override
	public void setUserId(long userId) {
		_course.setUserId(userId);
	}

	/**
	* Sets the user name of this course.
	*
	* @param userName the user name of this course
	*/
	@Override
	public void setUserName(String userName) {
		_course.setUserName(userName);
	}

	/**
	* Sets the user uuid of this course.
	*
	* @param userUuid the user uuid of this course
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_course.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this course.
	*
	* @param uuid the uuid of this course
	*/
	@Override
	public void setUuid(String uuid) {
		_course.setUuid(uuid);
	}

	/**
	* Sets whether this course is welcome.
	*
	* @param welcome the welcome of this course
	*/
	@Override
	public void setWelcome(boolean welcome) {
		_course.setWelcome(welcome);
	}

	/**
	* Sets the welcome msg of this course.
	*
	* @param welcomeMsg the welcome msg of this course
	*/
	@Override
	public void setWelcomeMsg(String welcomeMsg) {
		_course.setWelcomeMsg(welcomeMsg);
	}

	/**
	* Sets the welcome subject of this course.
	*
	* @param welcomeSubject the welcome subject of this course
	*/
	@Override
	public void setWelcomeSubject(String welcomeSubject) {
		_course.setWelcomeSubject(welcomeSubject);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Course> toCacheModel() {
		return _course.toCacheModel();
	}

	@Override
	public Course toEscapedModel() {
		return new CourseWrapper(_course.toEscapedModel());
	}

	@Override
	public String toString() {
		return _course.toString();
	}

	@Override
	public Course toUnescapedModel() {
		return new CourseWrapper(_course.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _course.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CourseWrapper)) {
			return false;
		}

		CourseWrapper courseWrapper = (CourseWrapper)obj;

		if (Objects.equals(_course, courseWrapper._course)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _course.getStagedModelType();
	}

	@Override
	public Course getWrappedModel() {
		return _course;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _course.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _course.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_course.resetOriginalValues();
	}

	private final Course _course;
}