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
 * This class is a wrapper for {@link LearningActivity}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LearningActivity
 * @generated
 */
@ProviderType
public class LearningActivityWrapper implements LearningActivity,
	ModelWrapper<LearningActivity> {
	public LearningActivityWrapper(LearningActivity learningActivity) {
		_learningActivity = learningActivity;
	}

	@Override
	public Class<?> getModelClass() {
		return LearningActivity.class;
	}

	@Override
	public String getModelClassName() {
		return LearningActivity.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("actId", getActId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("lastPublishDate", getLastPublishDate());
		attributes.put("moduleId", getModuleId());
		attributes.put("title", getTitle());
		attributes.put("description", getDescription());
		attributes.put("typeId", getTypeId());
		attributes.put("startDate", getStartDate());
		attributes.put("endDate", getEndDate());
		attributes.put("tries", getTries());
		attributes.put("passPuntuation", getPassPuntuation());
		attributes.put("priority", getPriority());
		attributes.put("extraContent", getExtraContent());
		attributes.put("feedbackCorrect", getFeedbackCorrect());
		attributes.put("feedbackNoCorrect", getFeedbackNoCorrect());
		attributes.put("required", isRequired());
		attributes.put("commentsActivated", isCommentsActivated());
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

		Long actId = (Long)attributes.get("actId");

		if (actId != null) {
			setActId(actId);
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

		Long moduleId = (Long)attributes.get("moduleId");

		if (moduleId != null) {
			setModuleId(moduleId);
		}

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Long typeId = (Long)attributes.get("typeId");

		if (typeId != null) {
			setTypeId(typeId);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Date endDate = (Date)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}

		Integer tries = (Integer)attributes.get("tries");

		if (tries != null) {
			setTries(tries);
		}

		Integer passPuntuation = (Integer)attributes.get("passPuntuation");

		if (passPuntuation != null) {
			setPassPuntuation(passPuntuation);
		}

		Long priority = (Long)attributes.get("priority");

		if (priority != null) {
			setPriority(priority);
		}

		String extraContent = (String)attributes.get("extraContent");

		if (extraContent != null) {
			setExtraContent(extraContent);
		}

		String feedbackCorrect = (String)attributes.get("feedbackCorrect");

		if (feedbackCorrect != null) {
			setFeedbackCorrect(feedbackCorrect);
		}

		String feedbackNoCorrect = (String)attributes.get("feedbackNoCorrect");

		if (feedbackNoCorrect != null) {
			setFeedbackNoCorrect(feedbackNoCorrect);
		}

		Boolean required = (Boolean)attributes.get("required");

		if (required != null) {
			setRequired(required);
		}

		Boolean commentsActivated = (Boolean)attributes.get("commentsActivated");

		if (commentsActivated != null) {
			setCommentsActivated(commentsActivated);
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
	public void addExtraContentJSON(String key, Object value) {
		_learningActivity.addExtraContentJSON(key, value);
	}

	@Override
	public Object clone() {
		return new LearningActivityWrapper((LearningActivity)_learningActivity.clone());
	}

	@Override
	public int compareTo(LearningActivity learningActivity) {
		return _learningActivity.compareTo(learningActivity);
	}

	/**
	* Returns the act ID of this learning activity.
	*
	* @return the act ID of this learning activity
	*/
	@Override
	public long getActId() {
		return _learningActivity.getActId();
	}

	@Override
	public String[] getAvailableLanguageIds() {
		return _learningActivity.getAvailableLanguageIds();
	}

	/**
	* Returns the comments activated of this learning activity.
	*
	* @return the comments activated of this learning activity
	*/
	@Override
	public boolean getCommentsActivated() {
		return _learningActivity.getCommentsActivated();
	}

	/**
	* Returns the company ID of this learning activity.
	*
	* @return the company ID of this learning activity
	*/
	@Override
	public long getCompanyId() {
		return _learningActivity.getCompanyId();
	}

	/**
	* Returns the create date of this learning activity.
	*
	* @return the create date of this learning activity
	*/
	@Override
	public Date getCreateDate() {
		return _learningActivity.getCreateDate();
	}

	@Override
	public String getDefaultLanguageId() {
		return _learningActivity.getDefaultLanguageId();
	}

	/**
	* Returns the description of this learning activity.
	*
	* @return the description of this learning activity
	*/
	@Override
	public String getDescription() {
		return _learningActivity.getDescription();
	}

	/**
	* Returns the localized description of this learning activity in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized description of this learning activity
	*/
	@Override
	public String getDescription(java.util.Locale locale) {
		return _learningActivity.getDescription(locale);
	}

	/**
	* Returns the localized description of this learning activity in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this learning activity. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public String getDescription(java.util.Locale locale, boolean useDefault) {
		return _learningActivity.getDescription(locale, useDefault);
	}

	/**
	* Returns the localized description of this learning activity in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized description of this learning activity
	*/
	@Override
	public String getDescription(String languageId) {
		return _learningActivity.getDescription(languageId);
	}

	/**
	* Returns the localized description of this learning activity in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this learning activity
	*/
	@Override
	public String getDescription(String languageId, boolean useDefault) {
		return _learningActivity.getDescription(languageId, useDefault);
	}

	@Override
	public String getDescriptionCurrentLanguageId() {
		return _learningActivity.getDescriptionCurrentLanguageId();
	}

	@Override
	public String getDescriptionCurrentValue() {
		return _learningActivity.getDescriptionCurrentValue();
	}

	/**
	* Returns a map of the locales and localized descriptions of this learning activity.
	*
	* @return the locales and localized descriptions of this learning activity
	*/
	@Override
	public Map<java.util.Locale, String> getDescriptionMap() {
		return _learningActivity.getDescriptionMap();
	}

	/**
	* Returns the end date of this learning activity.
	*
	* @return the end date of this learning activity
	*/
	@Override
	public Date getEndDate() {
		return _learningActivity.getEndDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _learningActivity.getExpandoBridge();
	}

	/**
	* Returns the extra content of this learning activity.
	*
	* @return the extra content of this learning activity
	*/
	@Override
	public String getExtraContent() {
		return _learningActivity.getExtraContent();
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getExtraContentJSON() {
		return _learningActivity.getExtraContentJSON();
	}

	/**
	* Returns the feedback correct of this learning activity.
	*
	* @return the feedback correct of this learning activity
	*/
	@Override
	public String getFeedbackCorrect() {
		return _learningActivity.getFeedbackCorrect();
	}

	/**
	* Returns the feedback no correct of this learning activity.
	*
	* @return the feedback no correct of this learning activity
	*/
	@Override
	public String getFeedbackNoCorrect() {
		return _learningActivity.getFeedbackNoCorrect();
	}

	/**
	* Returns the group ID of this learning activity.
	*
	* @return the group ID of this learning activity
	*/
	@Override
	public long getGroupId() {
		return _learningActivity.getGroupId();
	}

	/**
	* Returns the last publish date of this learning activity.
	*
	* @return the last publish date of this learning activity
	*/
	@Override
	public Date getLastPublishDate() {
		return _learningActivity.getLastPublishDate();
	}

	/**
	* Returns the modified date of this learning activity.
	*
	* @return the modified date of this learning activity
	*/
	@Override
	public Date getModifiedDate() {
		return _learningActivity.getModifiedDate();
	}

	/**
	* Returns the module ID of this learning activity.
	*
	* @return the module ID of this learning activity
	*/
	@Override
	public long getModuleId() {
		return _learningActivity.getModuleId();
	}

	/**
	* Returns the pass puntuation of this learning activity.
	*
	* @return the pass puntuation of this learning activity
	*/
	@Override
	public int getPassPuntuation() {
		return _learningActivity.getPassPuntuation();
	}

	/**
	* Returns the primary key of this learning activity.
	*
	* @return the primary key of this learning activity
	*/
	@Override
	public long getPrimaryKey() {
		return _learningActivity.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _learningActivity.getPrimaryKeyObj();
	}

	/**
	* Returns the priority of this learning activity.
	*
	* @return the priority of this learning activity
	*/
	@Override
	public long getPriority() {
		return _learningActivity.getPriority();
	}

	/**
	* Returns the required of this learning activity.
	*
	* @return the required of this learning activity
	*/
	@Override
	public boolean getRequired() {
		return _learningActivity.getRequired();
	}

	/**
	* Returns the start date of this learning activity.
	*
	* @return the start date of this learning activity
	*/
	@Override
	public Date getStartDate() {
		return _learningActivity.getStartDate();
	}

	/**
	* Returns the status of this learning activity.
	*
	* @return the status of this learning activity
	*/
	@Override
	public int getStatus() {
		return _learningActivity.getStatus();
	}

	/**
	* Returns the status by user ID of this learning activity.
	*
	* @return the status by user ID of this learning activity
	*/
	@Override
	public long getStatusByUserId() {
		return _learningActivity.getStatusByUserId();
	}

	/**
	* Returns the status by user name of this learning activity.
	*
	* @return the status by user name of this learning activity
	*/
	@Override
	public String getStatusByUserName() {
		return _learningActivity.getStatusByUserName();
	}

	/**
	* Returns the status by user uuid of this learning activity.
	*
	* @return the status by user uuid of this learning activity
	*/
	@Override
	public String getStatusByUserUuid() {
		return _learningActivity.getStatusByUserUuid();
	}

	/**
	* Returns the status date of this learning activity.
	*
	* @return the status date of this learning activity
	*/
	@Override
	public Date getStatusDate() {
		return _learningActivity.getStatusDate();
	}

	/**
	* Returns the title of this learning activity.
	*
	* @return the title of this learning activity
	*/
	@Override
	public String getTitle() {
		return _learningActivity.getTitle();
	}

	/**
	* Returns the localized title of this learning activity in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized title of this learning activity
	*/
	@Override
	public String getTitle(java.util.Locale locale) {
		return _learningActivity.getTitle(locale);
	}

	/**
	* Returns the localized title of this learning activity in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized title of this learning activity. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public String getTitle(java.util.Locale locale, boolean useDefault) {
		return _learningActivity.getTitle(locale, useDefault);
	}

	/**
	* Returns the localized title of this learning activity in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized title of this learning activity
	*/
	@Override
	public String getTitle(String languageId) {
		return _learningActivity.getTitle(languageId);
	}

	/**
	* Returns the localized title of this learning activity in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized title of this learning activity
	*/
	@Override
	public String getTitle(String languageId, boolean useDefault) {
		return _learningActivity.getTitle(languageId, useDefault);
	}

	@Override
	public String getTitleCurrentLanguageId() {
		return _learningActivity.getTitleCurrentLanguageId();
	}

	@Override
	public String getTitleCurrentValue() {
		return _learningActivity.getTitleCurrentValue();
	}

	/**
	* Returns a map of the locales and localized titles of this learning activity.
	*
	* @return the locales and localized titles of this learning activity
	*/
	@Override
	public Map<java.util.Locale, String> getTitleMap() {
		return _learningActivity.getTitleMap();
	}

	/**
	* Returns the tries of this learning activity.
	*
	* @return the tries of this learning activity
	*/
	@Override
	public int getTries() {
		return _learningActivity.getTries();
	}

	/**
	* Returns the type ID of this learning activity.
	*
	* @return the type ID of this learning activity
	*/
	@Override
	public long getTypeId() {
		return _learningActivity.getTypeId();
	}

	/**
	* Returns the user ID of this learning activity.
	*
	* @return the user ID of this learning activity
	*/
	@Override
	public long getUserId() {
		return _learningActivity.getUserId();
	}

	/**
	* Returns the user name of this learning activity.
	*
	* @return the user name of this learning activity
	*/
	@Override
	public String getUserName() {
		return _learningActivity.getUserName();
	}

	/**
	* Returns the user uuid of this learning activity.
	*
	* @return the user uuid of this learning activity
	*/
	@Override
	public String getUserUuid() {
		return _learningActivity.getUserUuid();
	}

	/**
	* Returns the uuid of this learning activity.
	*
	* @return the uuid of this learning activity
	*/
	@Override
	public String getUuid() {
		return _learningActivity.getUuid();
	}

	@Override
	public int hashCode() {
		return _learningActivity.hashCode();
	}

	/**
	* Returns <code>true</code> if this learning activity is approved.
	*
	* @return <code>true</code> if this learning activity is approved; <code>false</code> otherwise
	*/
	@Override
	public boolean isApproved() {
		return _learningActivity.isApproved();
	}

	@Override
	public boolean isCachedModel() {
		return _learningActivity.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this learning activity is comments activated.
	*
	* @return <code>true</code> if this learning activity is comments activated; <code>false</code> otherwise
	*/
	@Override
	public boolean isCommentsActivated() {
		return _learningActivity.isCommentsActivated();
	}

	/**
	* Returns <code>true</code> if this learning activity is denied.
	*
	* @return <code>true</code> if this learning activity is denied; <code>false</code> otherwise
	*/
	@Override
	public boolean isDenied() {
		return _learningActivity.isDenied();
	}

	/**
	* Returns <code>true</code> if this learning activity is a draft.
	*
	* @return <code>true</code> if this learning activity is a draft; <code>false</code> otherwise
	*/
	@Override
	public boolean isDraft() {
		return _learningActivity.isDraft();
	}

	@Override
	public boolean isEscapedModel() {
		return _learningActivity.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this learning activity is expired.
	*
	* @return <code>true</code> if this learning activity is expired; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpired() {
		return _learningActivity.isExpired();
	}

	/**
	* Returns <code>true</code> if this learning activity is inactive.
	*
	* @return <code>true</code> if this learning activity is inactive; <code>false</code> otherwise
	*/
	@Override
	public boolean isInactive() {
		return _learningActivity.isInactive();
	}

	/**
	* Returns <code>true</code> if this learning activity is incomplete.
	*
	* @return <code>true</code> if this learning activity is incomplete; <code>false</code> otherwise
	*/
	@Override
	public boolean isIncomplete() {
		return _learningActivity.isIncomplete();
	}

	@Override
	public boolean isNew() {
		return _learningActivity.isNew();
	}

	/**
	* Returns <code>true</code> if this learning activity is pending.
	*
	* @return <code>true</code> if this learning activity is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _learningActivity.isPending();
	}

	/**
	* Returns <code>true</code> if this learning activity is required.
	*
	* @return <code>true</code> if this learning activity is required; <code>false</code> otherwise
	*/
	@Override
	public boolean isRequired() {
		return _learningActivity.isRequired();
	}

	/**
	* Returns <code>true</code> if this learning activity is scheduled.
	*
	* @return <code>true</code> if this learning activity is scheduled; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduled() {
		return _learningActivity.isScheduled();
	}

	@Override
	public void persist() {
		_learningActivity.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {
		_learningActivity.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {
		_learningActivity.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	/**
	* Sets the act ID of this learning activity.
	*
	* @param actId the act ID of this learning activity
	*/
	@Override
	public void setActId(long actId) {
		_learningActivity.setActId(actId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_learningActivity.setCachedModel(cachedModel);
	}

	/**
	* Sets whether this learning activity is comments activated.
	*
	* @param commentsActivated the comments activated of this learning activity
	*/
	@Override
	public void setCommentsActivated(boolean commentsActivated) {
		_learningActivity.setCommentsActivated(commentsActivated);
	}

	/**
	* Sets the company ID of this learning activity.
	*
	* @param companyId the company ID of this learning activity
	*/
	@Override
	public void setCompanyId(long companyId) {
		_learningActivity.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this learning activity.
	*
	* @param createDate the create date of this learning activity
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_learningActivity.setCreateDate(createDate);
	}

	/**
	* Sets the description of this learning activity.
	*
	* @param description the description of this learning activity
	*/
	@Override
	public void setDescription(String description) {
		_learningActivity.setDescription(description);
	}

	/**
	* Sets the localized description of this learning activity in the language.
	*
	* @param description the localized description of this learning activity
	* @param locale the locale of the language
	*/
	@Override
	public void setDescription(String description, java.util.Locale locale) {
		_learningActivity.setDescription(description, locale);
	}

	/**
	* Sets the localized description of this learning activity in the language, and sets the default locale.
	*
	* @param description the localized description of this learning activity
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescription(String description, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_learningActivity.setDescription(description, locale, defaultLocale);
	}

	@Override
	public void setDescriptionCurrentLanguageId(String languageId) {
		_learningActivity.setDescriptionCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized descriptions of this learning activity from the map of locales and localized descriptions.
	*
	* @param descriptionMap the locales and localized descriptions of this learning activity
	*/
	@Override
	public void setDescriptionMap(Map<java.util.Locale, String> descriptionMap) {
		_learningActivity.setDescriptionMap(descriptionMap);
	}

	/**
	* Sets the localized descriptions of this learning activity from the map of locales and localized descriptions, and sets the default locale.
	*
	* @param descriptionMap the locales and localized descriptions of this learning activity
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, String> descriptionMap,
		java.util.Locale defaultLocale) {
		_learningActivity.setDescriptionMap(descriptionMap, defaultLocale);
	}

	/**
	* Sets the end date of this learning activity.
	*
	* @param endDate the end date of this learning activity
	*/
	@Override
	public void setEndDate(Date endDate) {
		_learningActivity.setEndDate(endDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_learningActivity.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_learningActivity.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_learningActivity.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the extra content of this learning activity.
	*
	* @param extraContent the extra content of this learning activity
	*/
	@Override
	public void setExtraContent(String extraContent) {
		_learningActivity.setExtraContent(extraContent);
	}

	/**
	* Sets the feedback correct of this learning activity.
	*
	* @param feedbackCorrect the feedback correct of this learning activity
	*/
	@Override
	public void setFeedbackCorrect(String feedbackCorrect) {
		_learningActivity.setFeedbackCorrect(feedbackCorrect);
	}

	/**
	* Sets the feedback no correct of this learning activity.
	*
	* @param feedbackNoCorrect the feedback no correct of this learning activity
	*/
	@Override
	public void setFeedbackNoCorrect(String feedbackNoCorrect) {
		_learningActivity.setFeedbackNoCorrect(feedbackNoCorrect);
	}

	/**
	* Sets the group ID of this learning activity.
	*
	* @param groupId the group ID of this learning activity
	*/
	@Override
	public void setGroupId(long groupId) {
		_learningActivity.setGroupId(groupId);
	}

	/**
	* Sets the last publish date of this learning activity.
	*
	* @param lastPublishDate the last publish date of this learning activity
	*/
	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		_learningActivity.setLastPublishDate(lastPublishDate);
	}

	/**
	* Sets the modified date of this learning activity.
	*
	* @param modifiedDate the modified date of this learning activity
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_learningActivity.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the module ID of this learning activity.
	*
	* @param moduleId the module ID of this learning activity
	*/
	@Override
	public void setModuleId(long moduleId) {
		_learningActivity.setModuleId(moduleId);
	}

	@Override
	public void setNew(boolean n) {
		_learningActivity.setNew(n);
	}

	/**
	* Sets the pass puntuation of this learning activity.
	*
	* @param passPuntuation the pass puntuation of this learning activity
	*/
	@Override
	public void setPassPuntuation(int passPuntuation) {
		_learningActivity.setPassPuntuation(passPuntuation);
	}

	/**
	* Sets the primary key of this learning activity.
	*
	* @param primaryKey the primary key of this learning activity
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_learningActivity.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_learningActivity.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the priority of this learning activity.
	*
	* @param priority the priority of this learning activity
	*/
	@Override
	public void setPriority(long priority) {
		_learningActivity.setPriority(priority);
	}

	/**
	* Sets whether this learning activity is required.
	*
	* @param required the required of this learning activity
	*/
	@Override
	public void setRequired(boolean required) {
		_learningActivity.setRequired(required);
	}

	/**
	* Sets the start date of this learning activity.
	*
	* @param startDate the start date of this learning activity
	*/
	@Override
	public void setStartDate(Date startDate) {
		_learningActivity.setStartDate(startDate);
	}

	/**
	* Sets the status of this learning activity.
	*
	* @param status the status of this learning activity
	*/
	@Override
	public void setStatus(int status) {
		_learningActivity.setStatus(status);
	}

	/**
	* Sets the status by user ID of this learning activity.
	*
	* @param statusByUserId the status by user ID of this learning activity
	*/
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_learningActivity.setStatusByUserId(statusByUserId);
	}

	/**
	* Sets the status by user name of this learning activity.
	*
	* @param statusByUserName the status by user name of this learning activity
	*/
	@Override
	public void setStatusByUserName(String statusByUserName) {
		_learningActivity.setStatusByUserName(statusByUserName);
	}

	/**
	* Sets the status by user uuid of this learning activity.
	*
	* @param statusByUserUuid the status by user uuid of this learning activity
	*/
	@Override
	public void setStatusByUserUuid(String statusByUserUuid) {
		_learningActivity.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Sets the status date of this learning activity.
	*
	* @param statusDate the status date of this learning activity
	*/
	@Override
	public void setStatusDate(Date statusDate) {
		_learningActivity.setStatusDate(statusDate);
	}

	/**
	* Sets the title of this learning activity.
	*
	* @param title the title of this learning activity
	*/
	@Override
	public void setTitle(String title) {
		_learningActivity.setTitle(title);
	}

	/**
	* Sets the localized title of this learning activity in the language.
	*
	* @param title the localized title of this learning activity
	* @param locale the locale of the language
	*/
	@Override
	public void setTitle(String title, java.util.Locale locale) {
		_learningActivity.setTitle(title, locale);
	}

	/**
	* Sets the localized title of this learning activity in the language, and sets the default locale.
	*
	* @param title the localized title of this learning activity
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setTitle(String title, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_learningActivity.setTitle(title, locale, defaultLocale);
	}

	@Override
	public void setTitleCurrentLanguageId(String languageId) {
		_learningActivity.setTitleCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized titles of this learning activity from the map of locales and localized titles.
	*
	* @param titleMap the locales and localized titles of this learning activity
	*/
	@Override
	public void setTitleMap(Map<java.util.Locale, String> titleMap) {
		_learningActivity.setTitleMap(titleMap);
	}

	/**
	* Sets the localized titles of this learning activity from the map of locales and localized titles, and sets the default locale.
	*
	* @param titleMap the locales and localized titles of this learning activity
	* @param defaultLocale the default locale
	*/
	@Override
	public void setTitleMap(Map<java.util.Locale, String> titleMap,
		java.util.Locale defaultLocale) {
		_learningActivity.setTitleMap(titleMap, defaultLocale);
	}

	/**
	* Sets the tries of this learning activity.
	*
	* @param tries the tries of this learning activity
	*/
	@Override
	public void setTries(int tries) {
		_learningActivity.setTries(tries);
	}

	/**
	* Sets the type ID of this learning activity.
	*
	* @param typeId the type ID of this learning activity
	*/
	@Override
	public void setTypeId(long typeId) {
		_learningActivity.setTypeId(typeId);
	}

	/**
	* Sets the user ID of this learning activity.
	*
	* @param userId the user ID of this learning activity
	*/
	@Override
	public void setUserId(long userId) {
		_learningActivity.setUserId(userId);
	}

	/**
	* Sets the user name of this learning activity.
	*
	* @param userName the user name of this learning activity
	*/
	@Override
	public void setUserName(String userName) {
		_learningActivity.setUserName(userName);
	}

	/**
	* Sets the user uuid of this learning activity.
	*
	* @param userUuid the user uuid of this learning activity
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_learningActivity.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this learning activity.
	*
	* @param uuid the uuid of this learning activity
	*/
	@Override
	public void setUuid(String uuid) {
		_learningActivity.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<LearningActivity> toCacheModel() {
		return _learningActivity.toCacheModel();
	}

	@Override
	public LearningActivity toEscapedModel() {
		return new LearningActivityWrapper(_learningActivity.toEscapedModel());
	}

	@Override
	public String toString() {
		return _learningActivity.toString();
	}

	@Override
	public LearningActivity toUnescapedModel() {
		return new LearningActivityWrapper(_learningActivity.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _learningActivity.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LearningActivityWrapper)) {
			return false;
		}

		LearningActivityWrapper learningActivityWrapper = (LearningActivityWrapper)obj;

		if (Objects.equals(_learningActivity,
					learningActivityWrapper._learningActivity)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _learningActivity.getStagedModelType();
	}

	@Override
	public LearningActivity getWrappedModel() {
		return _learningActivity;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _learningActivity.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _learningActivity.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_learningActivity.resetOriginalValues();
	}

	private final LearningActivity _learningActivity;
}