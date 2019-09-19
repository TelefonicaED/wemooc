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
 * This class is a wrapper for {@link Module}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Module
 * @generated
 */
@ProviderType
public class ModuleWrapper
	extends BaseModelWrapper<Module> implements Module, ModelWrapper<Module> {

	public ModuleWrapper(Module module) {
		super(module);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("moduleId", getModuleId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("lastPublishDate", getLastPublishDate());
		attributes.put("title", getTitle());
		attributes.put("description", getDescription());
		attributes.put("smallImageId", getSmallImageId());
		attributes.put("priority", getPriority());
		attributes.put("startDate", getStartDate());
		attributes.put("endDate", getEndDate());
		attributes.put("allowedTime", getAllowedTime());
		attributes.put("moduleEvalId", getModuleEvalId());
		attributes.put("moduleExtraData", getModuleExtraData());
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

		Long moduleId = (Long)attributes.get("moduleId");

		if (moduleId != null) {
			setModuleId(moduleId);
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

		Long priority = (Long)attributes.get("priority");

		if (priority != null) {
			setPriority(priority);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Date endDate = (Date)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}

		Long allowedTime = (Long)attributes.get("allowedTime");

		if (allowedTime != null) {
			setAllowedTime(allowedTime);
		}

		Long moduleEvalId = (Long)attributes.get("moduleEvalId");

		if (moduleEvalId != null) {
			setModuleEvalId(moduleEvalId);
		}

		String moduleExtraData = (String)attributes.get("moduleExtraData");

		if (moduleExtraData != null) {
			setModuleExtraData(moduleExtraData);
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
	public com.liferay.portal.kernel.repository.model.Folder addImagesFolder()
		throws com.liferay.portal.kernel.exception.PortalException {

		return model.addImagesFolder();
	}

	/**
	 * Returns the allowed time of this module.
	 *
	 * @return the allowed time of this module
	 */
	@Override
	public long getAllowedTime() {
		return model.getAllowedTime();
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
	 * Returns the company ID of this module.
	 *
	 * @return the company ID of this module
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this module.
	 *
	 * @return the create date of this module
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
	 * Returns the description of this module.
	 *
	 * @return the description of this module
	 */
	@Override
	public String getDescription() {
		return model.getDescription();
	}

	/**
	 * Returns the localized description of this module in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized description of this module
	 */
	@Override
	public String getDescription(java.util.Locale locale) {
		return model.getDescription(locale);
	}

	/**
	 * Returns the localized description of this module in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this module. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getDescription(java.util.Locale locale, boolean useDefault) {
		return model.getDescription(locale, useDefault);
	}

	/**
	 * Returns the localized description of this module in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized description of this module
	 */
	@Override
	public String getDescription(String languageId) {
		return model.getDescription(languageId);
	}

	/**
	 * Returns the localized description of this module in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this module
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
	 * Returns a map of the locales and localized descriptions of this module.
	 *
	 * @return the locales and localized descriptions of this module
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
	 * Returns the end date of this module.
	 *
	 * @return the end date of this module
	 */
	@Override
	public Date getEndDate() {
		return model.getEndDate();
	}

	@Override
	public java.util.Calendar getEndDateCalendar() {
		return model.getEndDateCalendar();
	}

	@Override
	public String getEndDateFormat(
		java.util.Locale locale, java.util.TimeZone timeZone) {

		return model.getEndDateFormat(locale, timeZone);
	}

	/**
	 * Returns the group ID of this module.
	 *
	 * @return the group ID of this module
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.repository.model.FileEntry>
			getImagesFileEntries()
		throws com.liferay.portal.kernel.exception.PortalException {

		return model.getImagesFileEntries();
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.repository.model.FileEntry>
			getImagesFileEntries(int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		return model.getImagesFileEntries(start, end);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.repository.model.FileEntry>
			getImagesFileEntries(
				int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException {

		return model.getImagesFileEntries(start, end, obc);
	}

	@Override
	public long getImagesFolderId() {
		return model.getImagesFolderId();
	}

	/**
	 * Returns the last publish date of this module.
	 *
	 * @return the last publish date of this module
	 */
	@Override
	public Date getLastPublishDate() {
		return model.getLastPublishDate();
	}

	/**
	 * Returns the modified date of this module.
	 *
	 * @return the modified date of this module
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the module eval ID of this module.
	 *
	 * @return the module eval ID of this module
	 */
	@Override
	public long getModuleEvalId() {
		return model.getModuleEvalId();
	}

	/**
	 * Returns the module extra data of this module.
	 *
	 * @return the module extra data of this module
	 */
	@Override
	public String getModuleExtraData() {
		return model.getModuleExtraData();
	}

	/**
	 * Returns the module ID of this module.
	 *
	 * @return the module ID of this module
	 */
	@Override
	public long getModuleId() {
		return model.getModuleId();
	}

	@Override
	public com.ted.prerequisite.model.PrerequisiteRelation
		getPrerequisiteRelation(long classNameFactoryId) {

		return model.getPrerequisiteRelation(classNameFactoryId);
	}

	/**
	 * Returns the primary key of this module.
	 *
	 * @return the primary key of this module
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the priority of this module.
	 *
	 * @return the priority of this module
	 */
	@Override
	public long getPriority() {
		return model.getPriority();
	}

	@Override
	public java.util.List<LearningActivity> getRequiredLearningActivities() {
		return model.getRequiredLearningActivities();
	}

	/**
	 * Returns the small image ID of this module.
	 *
	 * @return the small image ID of this module
	 */
	@Override
	public long getSmallImageId() {
		return model.getSmallImageId();
	}

	@Override
	public String getSmallImageType()
		throws com.liferay.portal.kernel.exception.PortalException {

		return model.getSmallImageType();
	}

	/**
	 * Returns the start date of this module.
	 *
	 * @return the start date of this module
	 */
	@Override
	public Date getStartDate() {
		return model.getStartDate();
	}

	@Override
	public java.util.Calendar getStartDateCalendar() {
		return model.getStartDateCalendar();
	}

	@Override
	public String getStartDateFormat(
		java.util.Locale locale, java.util.TimeZone timeZone) {

		return model.getStartDateFormat(locale, timeZone);
	}

	/**
	 * Returns the status of this module.
	 *
	 * @return the status of this module
	 */
	@Override
	public int getStatus() {
		return model.getStatus();
	}

	/**
	 * Returns the status by user ID of this module.
	 *
	 * @return the status by user ID of this module
	 */
	@Override
	public long getStatusByUserId() {
		return model.getStatusByUserId();
	}

	/**
	 * Returns the status by user name of this module.
	 *
	 * @return the status by user name of this module
	 */
	@Override
	public String getStatusByUserName() {
		return model.getStatusByUserName();
	}

	/**
	 * Returns the status by user uuid of this module.
	 *
	 * @return the status by user uuid of this module
	 */
	@Override
	public String getStatusByUserUuid() {
		return model.getStatusByUserUuid();
	}

	/**
	 * Returns the status date of this module.
	 *
	 * @return the status date of this module
	 */
	@Override
	public Date getStatusDate() {
		return model.getStatusDate();
	}

	/**
	 * Returns the title of this module.
	 *
	 * @return the title of this module
	 */
	@Override
	public String getTitle() {
		return model.getTitle();
	}

	/**
	 * Returns the localized title of this module in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized title of this module
	 */
	@Override
	public String getTitle(java.util.Locale locale) {
		return model.getTitle(locale);
	}

	/**
	 * Returns the localized title of this module in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized title of this module. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getTitle(java.util.Locale locale, boolean useDefault) {
		return model.getTitle(locale, useDefault);
	}

	/**
	 * Returns the localized title of this module in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized title of this module
	 */
	@Override
	public String getTitle(String languageId) {
		return model.getTitle(languageId);
	}

	/**
	 * Returns the localized title of this module in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized title of this module
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
	 * Returns a map of the locales and localized titles of this module.
	 *
	 * @return the locales and localized titles of this module
	 */
	@Override
	public Map<java.util.Locale, String> getTitleMap() {
		return model.getTitleMap();
	}

	/**
	 * Returns the trash entry created when this module was moved to the Recycle Bin. The trash entry may belong to one of the ancestors of this module.
	 *
	 * @return the trash entry created when this module was moved to the Recycle Bin
	 */
	@Override
	public com.liferay.trash.kernel.model.TrashEntry getTrashEntry()
		throws com.liferay.portal.kernel.exception.PortalException {

		return model.getTrashEntry();
	}

	/**
	 * Returns the class primary key of the trash entry for this module.
	 *
	 * @return the class primary key of the trash entry for this module
	 */
	@Override
	public long getTrashEntryClassPK() {
		return model.getTrashEntryClassPK();
	}

	/**
	 * Returns the trash handler for this module.
	 *
	 * @return the trash handler for this module
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public com.liferay.portal.kernel.trash.TrashHandler getTrashHandler() {
		return model.getTrashHandler();
	}

	@Override
	public String getURLEdit(
		com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay) {

		return model.getURLEdit(themeDisplay);
	}

	@Override
	public String getURLNewActivity(
		com.liferay.portal.kernel.portlet.PortletLayoutFinder
			activityPortletLayoutFinder,
		javax.portlet.RenderRequest renderRequest,
		com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay) {

		return model.getURLNewActivity(
			activityPortletLayoutFinder, renderRequest, themeDisplay);
	}

	@Override
	public String getURLView(
		com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay) {

		return model.getURLView(themeDisplay);
	}

	/**
	 * Returns the user ID of this module.
	 *
	 * @return the user ID of this module
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this module.
	 *
	 * @return the user name of this module
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this module.
	 *
	 * @return the user uuid of this module
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the uuid of this module.
	 *
	 * @return the uuid of this module
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	/**
	 * Returns <code>true</code> if this module is approved.
	 *
	 * @return <code>true</code> if this module is approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isApproved() {
		return model.isApproved();
	}

	/**
	 * Returns <code>true</code> if this module is denied.
	 *
	 * @return <code>true</code> if this module is denied; <code>false</code> otherwise
	 */
	@Override
	public boolean isDenied() {
		return model.isDenied();
	}

	/**
	 * Returns <code>true</code> if this module is a draft.
	 *
	 * @return <code>true</code> if this module is a draft; <code>false</code> otherwise
	 */
	@Override
	public boolean isDraft() {
		return model.isDraft();
	}

	/**
	 * Returns <code>true</code> if this module is expired.
	 *
	 * @return <code>true</code> if this module is expired; <code>false</code> otherwise
	 */
	@Override
	public boolean isExpired() {
		return model.isExpired();
	}

	/**
	 * Returns <code>true</code> if this module is inactive.
	 *
	 * @return <code>true</code> if this module is inactive; <code>false</code> otherwise
	 */
	@Override
	public boolean isInactive() {
		return model.isInactive();
	}

	/**
	 * Returns <code>true</code> if this module is incomplete.
	 *
	 * @return <code>true</code> if this module is incomplete; <code>false</code> otherwise
	 */
	@Override
	public boolean isIncomplete() {
		return model.isIncomplete();
	}

	/**
	 * Returns <code>true</code> if this module is in the Recycle Bin.
	 *
	 * @return <code>true</code> if this module is in the Recycle Bin; <code>false</code> otherwise
	 */
	@Override
	public boolean isInTrash() {
		return model.isInTrash();
	}

	/**
	 * Returns <code>true</code> if the parent of this module is in the Recycle Bin.
	 *
	 * @return <code>true</code> if the parent of this module is in the Recycle Bin; <code>false</code> otherwise
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
		long userId,
		com.liferay.portal.kernel.security.permission.PermissionChecker
			permissionChecker) {

		return model.isLocked(userId, permissionChecker);
	}

	/**
	 * Returns <code>true</code> if this module is pending.
	 *
	 * @return <code>true</code> if this module is pending; <code>false</code> otherwise
	 */
	@Override
	public boolean isPending() {
		return model.isPending();
	}

	/**
	 * Returns <code>true</code> if this module is scheduled.
	 *
	 * @return <code>true</code> if this module is scheduled; <code>false</code> otherwise
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
	 * Sets the allowed time of this module.
	 *
	 * @param allowedTime the allowed time of this module
	 */
	@Override
	public void setAllowedTime(long allowedTime) {
		model.setAllowedTime(allowedTime);
	}

	/**
	 * Sets the company ID of this module.
	 *
	 * @param companyId the company ID of this module
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this module.
	 *
	 * @param createDate the create date of this module
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the description of this module.
	 *
	 * @param description the description of this module
	 */
	@Override
	public void setDescription(String description) {
		model.setDescription(description);
	}

	/**
	 * Sets the localized description of this module in the language.
	 *
	 * @param description the localized description of this module
	 * @param locale the locale of the language
	 */
	@Override
	public void setDescription(String description, java.util.Locale locale) {
		model.setDescription(description, locale);
	}

	/**
	 * Sets the localized description of this module in the language, and sets the default locale.
	 *
	 * @param description the localized description of this module
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
	 * Sets the localized descriptions of this module from the map of locales and localized descriptions.
	 *
	 * @param descriptionMap the locales and localized descriptions of this module
	 */
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, String> descriptionMap) {

		model.setDescriptionMap(descriptionMap);
	}

	/**
	 * Sets the localized descriptions of this module from the map of locales and localized descriptions, and sets the default locale.
	 *
	 * @param descriptionMap the locales and localized descriptions of this module
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, String> descriptionMap,
		java.util.Locale defaultLocale) {

		model.setDescriptionMap(descriptionMap, defaultLocale);
	}

	/**
	 * Sets the end date of this module.
	 *
	 * @param endDate the end date of this module
	 */
	@Override
	public void setEndDate(Date endDate) {
		model.setEndDate(endDate);
	}

	/**
	 * Sets the group ID of this module.
	 *
	 * @param groupId the group ID of this module
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	@Override
	public void setImagesFolderId(long imagesFolderId) {
		model.setImagesFolderId(imagesFolderId);
	}

	/**
	 * Sets the last publish date of this module.
	 *
	 * @param lastPublishDate the last publish date of this module
	 */
	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		model.setLastPublishDate(lastPublishDate);
	}

	/**
	 * Sets the modified date of this module.
	 *
	 * @param modifiedDate the modified date of this module
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the module eval ID of this module.
	 *
	 * @param moduleEvalId the module eval ID of this module
	 */
	@Override
	public void setModuleEvalId(long moduleEvalId) {
		model.setModuleEvalId(moduleEvalId);
	}

	/**
	 * Sets the module extra data of this module.
	 *
	 * @param moduleExtraData the module extra data of this module
	 */
	@Override
	public void setModuleExtraData(String moduleExtraData) {
		model.setModuleExtraData(moduleExtraData);
	}

	/**
	 * Sets the module ID of this module.
	 *
	 * @param moduleId the module ID of this module
	 */
	@Override
	public void setModuleId(long moduleId) {
		model.setModuleId(moduleId);
	}

	/**
	 * Sets the primary key of this module.
	 *
	 * @param primaryKey the primary key of this module
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the priority of this module.
	 *
	 * @param priority the priority of this module
	 */
	@Override
	public void setPriority(long priority) {
		model.setPriority(priority);
	}

	/**
	 * Sets the small image ID of this module.
	 *
	 * @param smallImageId the small image ID of this module
	 */
	@Override
	public void setSmallImageId(long smallImageId) {
		model.setSmallImageId(smallImageId);
	}

	@Override
	public void setSmallImageType(String smallImageType) {
		model.setSmallImageType(smallImageType);
	}

	/**
	 * Sets the start date of this module.
	 *
	 * @param startDate the start date of this module
	 */
	@Override
	public void setStartDate(Date startDate) {
		model.setStartDate(startDate);
	}

	/**
	 * Sets the status of this module.
	 *
	 * @param status the status of this module
	 */
	@Override
	public void setStatus(int status) {
		model.setStatus(status);
	}

	/**
	 * Sets the status by user ID of this module.
	 *
	 * @param statusByUserId the status by user ID of this module
	 */
	@Override
	public void setStatusByUserId(long statusByUserId) {
		model.setStatusByUserId(statusByUserId);
	}

	/**
	 * Sets the status by user name of this module.
	 *
	 * @param statusByUserName the status by user name of this module
	 */
	@Override
	public void setStatusByUserName(String statusByUserName) {
		model.setStatusByUserName(statusByUserName);
	}

	/**
	 * Sets the status by user uuid of this module.
	 *
	 * @param statusByUserUuid the status by user uuid of this module
	 */
	@Override
	public void setStatusByUserUuid(String statusByUserUuid) {
		model.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	 * Sets the status date of this module.
	 *
	 * @param statusDate the status date of this module
	 */
	@Override
	public void setStatusDate(Date statusDate) {
		model.setStatusDate(statusDate);
	}

	/**
	 * Sets the title of this module.
	 *
	 * @param title the title of this module
	 */
	@Override
	public void setTitle(String title) {
		model.setTitle(title);
	}

	/**
	 * Sets the localized title of this module in the language.
	 *
	 * @param title the localized title of this module
	 * @param locale the locale of the language
	 */
	@Override
	public void setTitle(String title, java.util.Locale locale) {
		model.setTitle(title, locale);
	}

	/**
	 * Sets the localized title of this module in the language, and sets the default locale.
	 *
	 * @param title the localized title of this module
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
	 * Sets the localized titles of this module from the map of locales and localized titles.
	 *
	 * @param titleMap the locales and localized titles of this module
	 */
	@Override
	public void setTitleMap(Map<java.util.Locale, String> titleMap) {
		model.setTitleMap(titleMap);
	}

	/**
	 * Sets the localized titles of this module from the map of locales and localized titles, and sets the default locale.
	 *
	 * @param titleMap the locales and localized titles of this module
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setTitleMap(
		Map<java.util.Locale, String> titleMap,
		java.util.Locale defaultLocale) {

		model.setTitleMap(titleMap, defaultLocale);
	}

	/**
	 * Sets the user ID of this module.
	 *
	 * @param userId the user ID of this module
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this module.
	 *
	 * @param userName the user name of this module
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this module.
	 *
	 * @param userUuid the user uuid of this module
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this module.
	 *
	 * @param uuid the uuid of this module
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
	protected ModuleWrapper wrap(Module module) {
		return new ModuleWrapper(module);
	}

}