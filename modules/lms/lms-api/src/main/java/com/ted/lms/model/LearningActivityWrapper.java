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
 * This class is a wrapper for {@link LearningActivity}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LearningActivity
 * @generated
 */
@ProviderType
public class LearningActivityWrapper
	extends BaseModelWrapper<LearningActivity>
	implements LearningActivity, ModelWrapper<LearningActivity> {

	public LearningActivityWrapper(LearningActivity learningActivity) {
		super(learningActivity);
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

		Double passPuntuation = (Double)attributes.get("passPuntuation");

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

		Boolean commentsActivated = (Boolean)attributes.get(
			"commentsActivated");

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
		model.addExtraContentJSON(key, value);
	}

	/**
	 * Comprueba si se puede accceder a una actividad aunque esté bloqueada
	 *
	 * @param viewActivityFinish Si la actividad deja acceder coon el modo observador
	 * @param user Usuario que accede a la actividad
	 * @param permissionChecker permisos del usuario
	 * @return true si puede acceder
	 * @throws PortalException
	 */
	@Override
	public boolean canAccess(
			boolean viewActivityFinish,
			com.liferay.portal.kernel.model.User user,
			com.liferay.portal.kernel.security.permission.PermissionChecker
				permissionChecker,
			Course course)
		throws com.liferay.portal.kernel.exception.PortalException {

		return model.canAccess(
			viewActivityFinish, user, permissionChecker, course);
	}

	/**
	 * Returns the act ID of this learning activity.
	 *
	 * @return the act ID of this learning activity
	 */
	@Override
	public long getActId() {
		return model.getActId();
	}

	@Override
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry() {
		return model.getAssetEntry();
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.repository.model.FileEntry>
			getAttachmentsFileEntries()
		throws com.liferay.portal.kernel.exception.PortalException {

		return model.getAttachmentsFileEntries();
	}

	@Override
	public long getAttachmentsFolderId()
		throws com.liferay.portal.kernel.exception.PortalException {

		return model.getAttachmentsFolderId();
	}

	@Override
	public String[] getAvailableLanguageIds() {
		return model.getAvailableLanguageIds();
	}

	/**
	 * Returns the comments activated of this learning activity.
	 *
	 * @return the comments activated of this learning activity
	 */
	@Override
	public boolean getCommentsActivated() {
		return model.getCommentsActivated();
	}

	/**
	 * Returns the company ID of this learning activity.
	 *
	 * @return the company ID of this learning activity
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this learning activity.
	 *
	 * @return the create date of this learning activity
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
	 * Returns the description of this learning activity.
	 *
	 * @return the description of this learning activity
	 */
	@Override
	public String getDescription() {
		return model.getDescription();
	}

	/**
	 * Returns the localized description of this learning activity in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized description of this learning activity
	 */
	@Override
	public String getDescription(java.util.Locale locale) {
		return model.getDescription(locale);
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
		return model.getDescription(locale, useDefault);
	}

	/**
	 * Returns the localized description of this learning activity in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized description of this learning activity
	 */
	@Override
	public String getDescription(String languageId) {
		return model.getDescription(languageId);
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
	 * Returns a map of the locales and localized descriptions of this learning activity.
	 *
	 * @return the locales and localized descriptions of this learning activity
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
	 * Returns the end date of this learning activity.
	 *
	 * @return the end date of this learning activity
	 */
	@Override
	public Date getEndDate() {
		return model.getEndDate();
	}

	@Override
	public java.util.Calendar getEndDateCalendar() {
		return model.getEndDateCalendar();
	}

	/**
	 * Returns the extra content of this learning activity.
	 *
	 * @return the extra content of this learning activity
	 */
	@Override
	public String getExtraContent() {
		return model.getExtraContent();
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getExtraContentJSON() {
		return model.getExtraContentJSON();
	}

	/**
	 * Returns the feedback correct of this learning activity.
	 *
	 * @return the feedback correct of this learning activity
	 */
	@Override
	public String getFeedbackCorrect() {
		return model.getFeedbackCorrect();
	}

	/**
	 * Returns the localized feedback correct of this learning activity in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized feedback correct of this learning activity
	 */
	@Override
	public String getFeedbackCorrect(java.util.Locale locale) {
		return model.getFeedbackCorrect(locale);
	}

	/**
	 * Returns the localized feedback correct of this learning activity in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized feedback correct of this learning activity. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getFeedbackCorrect(
		java.util.Locale locale, boolean useDefault) {

		return model.getFeedbackCorrect(locale, useDefault);
	}

	/**
	 * Returns the localized feedback correct of this learning activity in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized feedback correct of this learning activity
	 */
	@Override
	public String getFeedbackCorrect(String languageId) {
		return model.getFeedbackCorrect(languageId);
	}

	/**
	 * Returns the localized feedback correct of this learning activity in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized feedback correct of this learning activity
	 */
	@Override
	public String getFeedbackCorrect(String languageId, boolean useDefault) {
		return model.getFeedbackCorrect(languageId, useDefault);
	}

	@Override
	public String getFeedbackCorrectCurrentLanguageId() {
		return model.getFeedbackCorrectCurrentLanguageId();
	}

	@Override
	public String getFeedbackCorrectCurrentValue() {
		return model.getFeedbackCorrectCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized feedback corrects of this learning activity.
	 *
	 * @return the locales and localized feedback corrects of this learning activity
	 */
	@Override
	public Map<java.util.Locale, String> getFeedbackCorrectMap() {
		return model.getFeedbackCorrectMap();
	}

	/**
	 * Returns the feedback no correct of this learning activity.
	 *
	 * @return the feedback no correct of this learning activity
	 */
	@Override
	public String getFeedbackNoCorrect() {
		return model.getFeedbackNoCorrect();
	}

	/**
	 * Returns the localized feedback no correct of this learning activity in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized feedback no correct of this learning activity
	 */
	@Override
	public String getFeedbackNoCorrect(java.util.Locale locale) {
		return model.getFeedbackNoCorrect(locale);
	}

	/**
	 * Returns the localized feedback no correct of this learning activity in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized feedback no correct of this learning activity. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getFeedbackNoCorrect(
		java.util.Locale locale, boolean useDefault) {

		return model.getFeedbackNoCorrect(locale, useDefault);
	}

	/**
	 * Returns the localized feedback no correct of this learning activity in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized feedback no correct of this learning activity
	 */
	@Override
	public String getFeedbackNoCorrect(String languageId) {
		return model.getFeedbackNoCorrect(languageId);
	}

	/**
	 * Returns the localized feedback no correct of this learning activity in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized feedback no correct of this learning activity
	 */
	@Override
	public String getFeedbackNoCorrect(String languageId, boolean useDefault) {
		return model.getFeedbackNoCorrect(languageId, useDefault);
	}

	@Override
	public String getFeedbackNoCorrectCurrentLanguageId() {
		return model.getFeedbackNoCorrectCurrentLanguageId();
	}

	@Override
	public String getFeedbackNoCorrectCurrentValue() {
		return model.getFeedbackNoCorrectCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized feedback no corrects of this learning activity.
	 *
	 * @return the locales and localized feedback no corrects of this learning activity
	 */
	@Override
	public Map<java.util.Locale, String> getFeedbackNoCorrectMap() {
		return model.getFeedbackNoCorrectMap();
	}

	/**
	 * Returns the group ID of this learning activity.
	 *
	 * @return the group ID of this learning activity
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the last publish date of this learning activity.
	 *
	 * @return the last publish date of this learning activity
	 */
	@Override
	public Date getLastPublishDate() {
		return model.getLastPublishDate();
	}

	@Override
	public LearningActivityTypeFactory getLearningActivityTypeFactory() {
		return model.getLearningActivityTypeFactory();
	}

	/**
	 * Returns the modified date of this learning activity.
	 *
	 * @return the modified date of this learning activity
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the module ID of this learning activity.
	 *
	 * @return the module ID of this learning activity
	 */
	@Override
	public long getModuleId() {
		return model.getModuleId();
	}

	/**
	 * Returns the pass puntuation of this learning activity.
	 *
	 * @return the pass puntuation of this learning activity
	 */
	@Override
	public double getPassPuntuation() {
		return model.getPassPuntuation();
	}

	@Override
	public com.ted.prerequisite.model.PrerequisiteRelation
		getPrerequisiteRelation(long classNameFactoryId) {

		return model.getPrerequisiteRelation(classNameFactoryId);
	}

	/**
	 * Returns the primary key of this learning activity.
	 *
	 * @return the primary key of this learning activity
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the priority of this learning activity.
	 *
	 * @return the priority of this learning activity
	 */
	@Override
	public long getPriority() {
		return model.getPriority();
	}

	/**
	 * Returns the required of this learning activity.
	 *
	 * @return the required of this learning activity
	 */
	@Override
	public boolean getRequired() {
		return model.getRequired();
	}

	/**
	 * Returns the start date of this learning activity.
	 *
	 * @return the start date of this learning activity
	 */
	@Override
	public Date getStartDate() {
		return model.getStartDate();
	}

	@Override
	public java.util.Calendar getStartDateCalendar() {
		return model.getStartDateCalendar();
	}

	/**
	 * Returns the status of this learning activity.
	 *
	 * @return the status of this learning activity
	 */
	@Override
	public int getStatus() {
		return model.getStatus();
	}

	/**
	 * Returns the status by user ID of this learning activity.
	 *
	 * @return the status by user ID of this learning activity
	 */
	@Override
	public long getStatusByUserId() {
		return model.getStatusByUserId();
	}

	/**
	 * Returns the status by user name of this learning activity.
	 *
	 * @return the status by user name of this learning activity
	 */
	@Override
	public String getStatusByUserName() {
		return model.getStatusByUserName();
	}

	/**
	 * Returns the status by user uuid of this learning activity.
	 *
	 * @return the status by user uuid of this learning activity
	 */
	@Override
	public String getStatusByUserUuid() {
		return model.getStatusByUserUuid();
	}

	/**
	 * Returns the status date of this learning activity.
	 *
	 * @return the status date of this learning activity
	 */
	@Override
	public Date getStatusDate() {
		return model.getStatusDate();
	}

	@Override
	public long getTeamId() {
		return model.getTeamId();
	}

	/**
	 * Returns the title of this learning activity.
	 *
	 * @return the title of this learning activity
	 */
	@Override
	public String getTitle() {
		return model.getTitle();
	}

	/**
	 * Returns the localized title of this learning activity in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized title of this learning activity
	 */
	@Override
	public String getTitle(java.util.Locale locale) {
		return model.getTitle(locale);
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
		return model.getTitle(locale, useDefault);
	}

	/**
	 * Returns the localized title of this learning activity in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized title of this learning activity
	 */
	@Override
	public String getTitle(String languageId) {
		return model.getTitle(languageId);
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
	 * Returns a map of the locales and localized titles of this learning activity.
	 *
	 * @return the locales and localized titles of this learning activity
	 */
	@Override
	public Map<java.util.Locale, String> getTitleMap() {
		return model.getTitleMap();
	}

	/**
	 * Returns the trash entry created when this learning activity was moved to the Recycle Bin. The trash entry may belong to one of the ancestors of this learning activity.
	 *
	 * @return the trash entry created when this learning activity was moved to the Recycle Bin
	 */
	@Override
	public com.liferay.trash.kernel.model.TrashEntry getTrashEntry()
		throws com.liferay.portal.kernel.exception.PortalException {

		return model.getTrashEntry();
	}

	/**
	 * Returns the class primary key of the trash entry for this learning activity.
	 *
	 * @return the class primary key of the trash entry for this learning activity
	 */
	@Override
	public long getTrashEntryClassPK() {
		return model.getTrashEntryClassPK();
	}

	/**
	 * Returns the trash handler for this learning activity.
	 *
	 * @return the trash handler for this learning activity
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public com.liferay.portal.kernel.trash.TrashHandler getTrashHandler() {
		return model.getTrashHandler();
	}

	/**
	 * Returns the tries of this learning activity.
	 *
	 * @return the tries of this learning activity
	 */
	@Override
	public int getTries() {
		return model.getTries();
	}

	/**
	 * Returns the type ID of this learning activity.
	 *
	 * @return the type ID of this learning activity
	 */
	@Override
	public long getTypeId() {
		return model.getTypeId();
	}

	@Override
	public String getURLEdit(
		com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay) {

		return model.getURLEdit(themeDisplay);
	}

	@Override
	public String getURLView(
		com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay) {

		return model.getURLView(themeDisplay);
	}

	/**
	 * Returns the user ID of this learning activity.
	 *
	 * @return the user ID of this learning activity
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this learning activity.
	 *
	 * @return the user name of this learning activity
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this learning activity.
	 *
	 * @return the user uuid of this learning activity
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the uuid of this learning activity.
	 *
	 * @return the uuid of this learning activity
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	/**
	 * Returns <code>true</code> if this learning activity is approved.
	 *
	 * @return <code>true</code> if this learning activity is approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isApproved() {
		return model.isApproved();
	}

	/**
	 * Returns <code>true</code> if this learning activity is comments activated.
	 *
	 * @return <code>true</code> if this learning activity is comments activated; <code>false</code> otherwise
	 */
	@Override
	public boolean isCommentsActivated() {
		return model.isCommentsActivated();
	}

	/**
	 * Returns <code>true</code> if this learning activity is denied.
	 *
	 * @return <code>true</code> if this learning activity is denied; <code>false</code> otherwise
	 */
	@Override
	public boolean isDenied() {
		return model.isDenied();
	}

	/**
	 * Returns <code>true</code> if this learning activity is a draft.
	 *
	 * @return <code>true</code> if this learning activity is a draft; <code>false</code> otherwise
	 */
	@Override
	public boolean isDraft() {
		return model.isDraft();
	}

	/**
	 * Returns <code>true</code> if this learning activity is expired.
	 *
	 * @return <code>true</code> if this learning activity is expired; <code>false</code> otherwise
	 */
	@Override
	public boolean isExpired() {
		return model.isExpired();
	}

	/**
	 * Returns <code>true</code> if this learning activity is inactive.
	 *
	 * @return <code>true</code> if this learning activity is inactive; <code>false</code> otherwise
	 */
	@Override
	public boolean isInactive() {
		return model.isInactive();
	}

	/**
	 * Returns <code>true</code> if this learning activity is incomplete.
	 *
	 * @return <code>true</code> if this learning activity is incomplete; <code>false</code> otherwise
	 */
	@Override
	public boolean isIncomplete() {
		return model.isIncomplete();
	}

	/**
	 * Returns <code>true</code> if this learning activity is in the Recycle Bin.
	 *
	 * @return <code>true</code> if this learning activity is in the Recycle Bin; <code>false</code> otherwise
	 */
	@Override
	public boolean isInTrash() {
		return model.isInTrash();
	}

	/**
	 * Returns <code>true</code> if the parent of this learning activity is in the Recycle Bin.
	 *
	 * @return <code>true</code> if the parent of this learning activity is in the Recycle Bin; <code>false</code> otherwise
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

	/**
	 * Comprueba que la actividad esté bloqueda para el usuario para realizarla
	 */
	@Override
	public boolean isLocked(
			com.liferay.portal.kernel.model.User user,
			com.liferay.portal.kernel.security.permission.PermissionChecker
				permissionChecker)
		throws com.liferay.portal.kernel.exception.PortalException {

		return model.isLocked(user, permissionChecker);
	}

	/**
	 * Returns <code>true</code> if this learning activity is pending.
	 *
	 * @return <code>true</code> if this learning activity is pending; <code>false</code> otherwise
	 */
	@Override
	public boolean isPending() {
		return model.isPending();
	}

	/**
	 * Returns <code>true</code> if this learning activity is required.
	 *
	 * @return <code>true</code> if this learning activity is required; <code>false</code> otherwise
	 */
	@Override
	public boolean isRequired() {
		return model.isRequired();
	}

	/**
	 * Returns <code>true</code> if this learning activity is scheduled.
	 *
	 * @return <code>true</code> if this learning activity is scheduled; <code>false</code> otherwise
	 */
	@Override
	public boolean isScheduled() {
		return model.isScheduled();
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
	 * Sets the act ID of this learning activity.
	 *
	 * @param actId the act ID of this learning activity
	 */
	@Override
	public void setActId(long actId) {
		model.setActId(actId);
	}

	/**
	 * Sets whether this learning activity is comments activated.
	 *
	 * @param commentsActivated the comments activated of this learning activity
	 */
	@Override
	public void setCommentsActivated(boolean commentsActivated) {
		model.setCommentsActivated(commentsActivated);
	}

	/**
	 * Sets the company ID of this learning activity.
	 *
	 * @param companyId the company ID of this learning activity
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this learning activity.
	 *
	 * @param createDate the create date of this learning activity
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the description of this learning activity.
	 *
	 * @param description the description of this learning activity
	 */
	@Override
	public void setDescription(String description) {
		model.setDescription(description);
	}

	/**
	 * Sets the localized description of this learning activity in the language.
	 *
	 * @param description the localized description of this learning activity
	 * @param locale the locale of the language
	 */
	@Override
	public void setDescription(String description, java.util.Locale locale) {
		model.setDescription(description, locale);
	}

	/**
	 * Sets the localized description of this learning activity in the language, and sets the default locale.
	 *
	 * @param description the localized description of this learning activity
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
	 * Sets the localized descriptions of this learning activity from the map of locales and localized descriptions.
	 *
	 * @param descriptionMap the locales and localized descriptions of this learning activity
	 */
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, String> descriptionMap) {

		model.setDescriptionMap(descriptionMap);
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

		model.setDescriptionMap(descriptionMap, defaultLocale);
	}

	/**
	 * Sets the end date of this learning activity.
	 *
	 * @param endDate the end date of this learning activity
	 */
	@Override
	public void setEndDate(Date endDate) {
		model.setEndDate(endDate);
	}

	/**
	 * Sets the extra content of this learning activity.
	 *
	 * @param extraContent the extra content of this learning activity
	 */
	@Override
	public void setExtraContent(String extraContent) {
		model.setExtraContent(extraContent);
	}

	@Override
	public void setExtraContentJSON(
		com.liferay.portal.kernel.json.JSONObject extraContent) {

		model.setExtraContentJSON(extraContent);
	}

	/**
	 * Sets the feedback correct of this learning activity.
	 *
	 * @param feedbackCorrect the feedback correct of this learning activity
	 */
	@Override
	public void setFeedbackCorrect(String feedbackCorrect) {
		model.setFeedbackCorrect(feedbackCorrect);
	}

	/**
	 * Sets the localized feedback correct of this learning activity in the language.
	 *
	 * @param feedbackCorrect the localized feedback correct of this learning activity
	 * @param locale the locale of the language
	 */
	@Override
	public void setFeedbackCorrect(
		String feedbackCorrect, java.util.Locale locale) {

		model.setFeedbackCorrect(feedbackCorrect, locale);
	}

	/**
	 * Sets the localized feedback correct of this learning activity in the language, and sets the default locale.
	 *
	 * @param feedbackCorrect the localized feedback correct of this learning activity
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setFeedbackCorrect(
		String feedbackCorrect, java.util.Locale locale,
		java.util.Locale defaultLocale) {

		model.setFeedbackCorrect(feedbackCorrect, locale, defaultLocale);
	}

	@Override
	public void setFeedbackCorrectCurrentLanguageId(String languageId) {
		model.setFeedbackCorrectCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized feedback corrects of this learning activity from the map of locales and localized feedback corrects.
	 *
	 * @param feedbackCorrectMap the locales and localized feedback corrects of this learning activity
	 */
	@Override
	public void setFeedbackCorrectMap(
		Map<java.util.Locale, String> feedbackCorrectMap) {

		model.setFeedbackCorrectMap(feedbackCorrectMap);
	}

	/**
	 * Sets the localized feedback corrects of this learning activity from the map of locales and localized feedback corrects, and sets the default locale.
	 *
	 * @param feedbackCorrectMap the locales and localized feedback corrects of this learning activity
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setFeedbackCorrectMap(
		Map<java.util.Locale, String> feedbackCorrectMap,
		java.util.Locale defaultLocale) {

		model.setFeedbackCorrectMap(feedbackCorrectMap, defaultLocale);
	}

	/**
	 * Sets the feedback no correct of this learning activity.
	 *
	 * @param feedbackNoCorrect the feedback no correct of this learning activity
	 */
	@Override
	public void setFeedbackNoCorrect(String feedbackNoCorrect) {
		model.setFeedbackNoCorrect(feedbackNoCorrect);
	}

	/**
	 * Sets the localized feedback no correct of this learning activity in the language.
	 *
	 * @param feedbackNoCorrect the localized feedback no correct of this learning activity
	 * @param locale the locale of the language
	 */
	@Override
	public void setFeedbackNoCorrect(
		String feedbackNoCorrect, java.util.Locale locale) {

		model.setFeedbackNoCorrect(feedbackNoCorrect, locale);
	}

	/**
	 * Sets the localized feedback no correct of this learning activity in the language, and sets the default locale.
	 *
	 * @param feedbackNoCorrect the localized feedback no correct of this learning activity
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setFeedbackNoCorrect(
		String feedbackNoCorrect, java.util.Locale locale,
		java.util.Locale defaultLocale) {

		model.setFeedbackNoCorrect(feedbackNoCorrect, locale, defaultLocale);
	}

	@Override
	public void setFeedbackNoCorrectCurrentLanguageId(String languageId) {
		model.setFeedbackNoCorrectCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized feedback no corrects of this learning activity from the map of locales and localized feedback no corrects.
	 *
	 * @param feedbackNoCorrectMap the locales and localized feedback no corrects of this learning activity
	 */
	@Override
	public void setFeedbackNoCorrectMap(
		Map<java.util.Locale, String> feedbackNoCorrectMap) {

		model.setFeedbackNoCorrectMap(feedbackNoCorrectMap);
	}

	/**
	 * Sets the localized feedback no corrects of this learning activity from the map of locales and localized feedback no corrects, and sets the default locale.
	 *
	 * @param feedbackNoCorrectMap the locales and localized feedback no corrects of this learning activity
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setFeedbackNoCorrectMap(
		Map<java.util.Locale, String> feedbackNoCorrectMap,
		java.util.Locale defaultLocale) {

		model.setFeedbackNoCorrectMap(feedbackNoCorrectMap, defaultLocale);
	}

	/**
	 * Sets the group ID of this learning activity.
	 *
	 * @param groupId the group ID of this learning activity
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the last publish date of this learning activity.
	 *
	 * @param lastPublishDate the last publish date of this learning activity
	 */
	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		model.setLastPublishDate(lastPublishDate);
	}

	/**
	 * Sets the modified date of this learning activity.
	 *
	 * @param modifiedDate the modified date of this learning activity
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the module ID of this learning activity.
	 *
	 * @param moduleId the module ID of this learning activity
	 */
	@Override
	public void setModuleId(long moduleId) {
		model.setModuleId(moduleId);
	}

	/**
	 * Sets the pass puntuation of this learning activity.
	 *
	 * @param passPuntuation the pass puntuation of this learning activity
	 */
	@Override
	public void setPassPuntuation(double passPuntuation) {
		model.setPassPuntuation(passPuntuation);
	}

	/**
	 * Sets the primary key of this learning activity.
	 *
	 * @param primaryKey the primary key of this learning activity
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the priority of this learning activity.
	 *
	 * @param priority the priority of this learning activity
	 */
	@Override
	public void setPriority(long priority) {
		model.setPriority(priority);
	}

	/**
	 * Sets whether this learning activity is required.
	 *
	 * @param required the required of this learning activity
	 */
	@Override
	public void setRequired(boolean required) {
		model.setRequired(required);
	}

	/**
	 * Sets the start date of this learning activity.
	 *
	 * @param startDate the start date of this learning activity
	 */
	@Override
	public void setStartDate(Date startDate) {
		model.setStartDate(startDate);
	}

	/**
	 * Sets the status of this learning activity.
	 *
	 * @param status the status of this learning activity
	 */
	@Override
	public void setStatus(int status) {
		model.setStatus(status);
	}

	/**
	 * Sets the status by user ID of this learning activity.
	 *
	 * @param statusByUserId the status by user ID of this learning activity
	 */
	@Override
	public void setStatusByUserId(long statusByUserId) {
		model.setStatusByUserId(statusByUserId);
	}

	/**
	 * Sets the status by user name of this learning activity.
	 *
	 * @param statusByUserName the status by user name of this learning activity
	 */
	@Override
	public void setStatusByUserName(String statusByUserName) {
		model.setStatusByUserName(statusByUserName);
	}

	/**
	 * Sets the status by user uuid of this learning activity.
	 *
	 * @param statusByUserUuid the status by user uuid of this learning activity
	 */
	@Override
	public void setStatusByUserUuid(String statusByUserUuid) {
		model.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	 * Sets the status date of this learning activity.
	 *
	 * @param statusDate the status date of this learning activity
	 */
	@Override
	public void setStatusDate(Date statusDate) {
		model.setStatusDate(statusDate);
	}

	/**
	 * Sets the title of this learning activity.
	 *
	 * @param title the title of this learning activity
	 */
	@Override
	public void setTitle(String title) {
		model.setTitle(title);
	}

	/**
	 * Sets the localized title of this learning activity in the language.
	 *
	 * @param title the localized title of this learning activity
	 * @param locale the locale of the language
	 */
	@Override
	public void setTitle(String title, java.util.Locale locale) {
		model.setTitle(title, locale);
	}

	/**
	 * Sets the localized title of this learning activity in the language, and sets the default locale.
	 *
	 * @param title the localized title of this learning activity
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
	 * Sets the localized titles of this learning activity from the map of locales and localized titles.
	 *
	 * @param titleMap the locales and localized titles of this learning activity
	 */
	@Override
	public void setTitleMap(Map<java.util.Locale, String> titleMap) {
		model.setTitleMap(titleMap);
	}

	/**
	 * Sets the localized titles of this learning activity from the map of locales and localized titles, and sets the default locale.
	 *
	 * @param titleMap the locales and localized titles of this learning activity
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setTitleMap(
		Map<java.util.Locale, String> titleMap,
		java.util.Locale defaultLocale) {

		model.setTitleMap(titleMap, defaultLocale);
	}

	/**
	 * Sets the tries of this learning activity.
	 *
	 * @param tries the tries of this learning activity
	 */
	@Override
	public void setTries(int tries) {
		model.setTries(tries);
	}

	/**
	 * Sets the type ID of this learning activity.
	 *
	 * @param typeId the type ID of this learning activity
	 */
	@Override
	public void setTypeId(long typeId) {
		model.setTypeId(typeId);
	}

	/**
	 * Sets the user ID of this learning activity.
	 *
	 * @param userId the user ID of this learning activity
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this learning activity.
	 *
	 * @param userName the user name of this learning activity
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this learning activity.
	 *
	 * @param userUuid the user uuid of this learning activity
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this learning activity.
	 *
	 * @param uuid the uuid of this learning activity
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	@Override
	public StagedModelType getStagedModelType() {
		return model.getStagedModelType();
	}

	@Override
	protected LearningActivityWrapper wrap(LearningActivity learningActivity) {
		return new LearningActivityWrapper(learningActivity);
	}

}