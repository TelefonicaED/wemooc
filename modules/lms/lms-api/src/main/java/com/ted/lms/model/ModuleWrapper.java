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
 * This class is a wrapper for {@link Module}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Module
 * @generated
 */
@ProviderType
public class ModuleWrapper implements Module, ModelWrapper<Module> {
	public ModuleWrapper(Module module) {
		_module = module;
	}

	@Override
	public Class<?> getModelClass() {
		return Module.class;
	}

	@Override
	public String getModelClassName() {
		return Module.class.getName();
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
		attributes.put("order", getOrder());
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

		Long order = (Long)attributes.get("order");

		if (order != null) {
			setOrder(order);
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
		return _module.addImagesFolder();
	}

	@Override
	public Object clone() {
		return new ModuleWrapper((Module)_module.clone());
	}

	@Override
	public int compareTo(Module module) {
		return _module.compareTo(module);
	}

	/**
	* Returns the allowed time of this module.
	*
	* @return the allowed time of this module
	*/
	@Override
	public long getAllowedTime() {
		return _module.getAllowedTime();
	}

	@Override
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry() {
		return _module.getAssetEntry();
	}

	@Override
	public String[] getAvailableLanguageIds() {
		return _module.getAvailableLanguageIds();
	}

	/**
	* Returns the company ID of this module.
	*
	* @return the company ID of this module
	*/
	@Override
	public long getCompanyId() {
		return _module.getCompanyId();
	}

	/**
	* Returns the create date of this module.
	*
	* @return the create date of this module
	*/
	@Override
	public Date getCreateDate() {
		return _module.getCreateDate();
	}

	@Override
	public String getDefaultLanguageId() {
		return _module.getDefaultLanguageId();
	}

	/**
	* Returns the description of this module.
	*
	* @return the description of this module
	*/
	@Override
	public String getDescription() {
		return _module.getDescription();
	}

	/**
	* Returns the localized description of this module in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized description of this module
	*/
	@Override
	public String getDescription(java.util.Locale locale) {
		return _module.getDescription(locale);
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
		return _module.getDescription(locale, useDefault);
	}

	/**
	* Returns the localized description of this module in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized description of this module
	*/
	@Override
	public String getDescription(String languageId) {
		return _module.getDescription(languageId);
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
		return _module.getDescription(languageId, useDefault);
	}

	@Override
	public String getDescriptionCurrentLanguageId() {
		return _module.getDescriptionCurrentLanguageId();
	}

	@Override
	public String getDescriptionCurrentValue() {
		return _module.getDescriptionCurrentValue();
	}

	/**
	* Returns a map of the locales and localized descriptions of this module.
	*
	* @return the locales and localized descriptions of this module
	*/
	@Override
	public Map<java.util.Locale, String> getDescriptionMap() {
		return _module.getDescriptionMap();
	}

	@Override
	public String getDescriptionMapAsXML() {
		return _module.getDescriptionMapAsXML();
	}

	/**
	* Returns the end date of this module.
	*
	* @return the end date of this module
	*/
	@Override
	public Date getEndDate() {
		return _module.getEndDate();
	}

	@Override
	public java.util.Calendar getEndDateCalendar() {
		return _module.getEndDateCalendar();
	}

	@Override
	public String getEndDateFormat(java.util.Locale locale,
		java.util.TimeZone timeZone) {
		return _module.getEndDateFormat(locale, timeZone);
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _module.getExpandoBridge();
	}

	/**
	* Returns the group ID of this module.
	*
	* @return the group ID of this module
	*/
	@Override
	public long getGroupId() {
		return _module.getGroupId();
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.repository.model.FileEntry> getImagesFileEntries()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _module.getImagesFileEntries();
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.repository.model.FileEntry> getImagesFileEntries(
		int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _module.getImagesFileEntries(start, end);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.repository.model.FileEntry> getImagesFileEntries(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _module.getImagesFileEntries(start, end, obc);
	}

	@Override
	public long getImagesFolderId() {
		return _module.getImagesFolderId();
	}

	/**
	* Returns the last publish date of this module.
	*
	* @return the last publish date of this module
	*/
	@Override
	public Date getLastPublishDate() {
		return _module.getLastPublishDate();
	}

	/**
	* Returns the modified date of this module.
	*
	* @return the modified date of this module
	*/
	@Override
	public Date getModifiedDate() {
		return _module.getModifiedDate();
	}

	/**
	* Returns the module eval ID of this module.
	*
	* @return the module eval ID of this module
	*/
	@Override
	public long getModuleEvalId() {
		return _module.getModuleEvalId();
	}

	/**
	* Returns the module extra data of this module.
	*
	* @return the module extra data of this module
	*/
	@Override
	public String getModuleExtraData() {
		return _module.getModuleExtraData();
	}

	/**
	* Returns the module ID of this module.
	*
	* @return the module ID of this module
	*/
	@Override
	public long getModuleId() {
		return _module.getModuleId();
	}

	/**
	* Returns the order of this module.
	*
	* @return the order of this module
	*/
	@Override
	public long getOrder() {
		return _module.getOrder();
	}

	@Override
	public com.ted.prerequisite.model.PrerequisiteRelation getPrerequisiteRelation(
		long classNameFactoryId) {
		return _module.getPrerequisiteRelation(classNameFactoryId);
	}

	/**
	* Returns the primary key of this module.
	*
	* @return the primary key of this module
	*/
	@Override
	public long getPrimaryKey() {
		return _module.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _module.getPrimaryKeyObj();
	}

	@Override
	public java.util.List<LearningActivity> getRequiredLearningActivities() {
		return _module.getRequiredLearningActivities();
	}

	/**
	* Returns the small image ID of this module.
	*
	* @return the small image ID of this module
	*/
	@Override
	public long getSmallImageId() {
		return _module.getSmallImageId();
	}

	@Override
	public String getSmallImageType()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _module.getSmallImageType();
	}

	/**
	* Returns the start date of this module.
	*
	* @return the start date of this module
	*/
	@Override
	public Date getStartDate() {
		return _module.getStartDate();
	}

	@Override
	public java.util.Calendar getStartDateCalendar() {
		return _module.getStartDateCalendar();
	}

	@Override
	public String getStartDateFormat(java.util.Locale locale,
		java.util.TimeZone timeZone) {
		return _module.getStartDateFormat(locale, timeZone);
	}

	/**
	* Returns the status of this module.
	*
	* @return the status of this module
	*/
	@Override
	public int getStatus() {
		return _module.getStatus();
	}

	/**
	* Returns the status by user ID of this module.
	*
	* @return the status by user ID of this module
	*/
	@Override
	public long getStatusByUserId() {
		return _module.getStatusByUserId();
	}

	/**
	* Returns the status by user name of this module.
	*
	* @return the status by user name of this module
	*/
	@Override
	public String getStatusByUserName() {
		return _module.getStatusByUserName();
	}

	/**
	* Returns the status by user uuid of this module.
	*
	* @return the status by user uuid of this module
	*/
	@Override
	public String getStatusByUserUuid() {
		return _module.getStatusByUserUuid();
	}

	/**
	* Returns the status date of this module.
	*
	* @return the status date of this module
	*/
	@Override
	public Date getStatusDate() {
		return _module.getStatusDate();
	}

	/**
	* Returns the title of this module.
	*
	* @return the title of this module
	*/
	@Override
	public String getTitle() {
		return _module.getTitle();
	}

	/**
	* Returns the localized title of this module in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized title of this module
	*/
	@Override
	public String getTitle(java.util.Locale locale) {
		return _module.getTitle(locale);
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
		return _module.getTitle(locale, useDefault);
	}

	/**
	* Returns the localized title of this module in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized title of this module
	*/
	@Override
	public String getTitle(String languageId) {
		return _module.getTitle(languageId);
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
		return _module.getTitle(languageId, useDefault);
	}

	@Override
	public String getTitleCurrentLanguageId() {
		return _module.getTitleCurrentLanguageId();
	}

	@Override
	public String getTitleCurrentValue() {
		return _module.getTitleCurrentValue();
	}

	/**
	* Returns a map of the locales and localized titles of this module.
	*
	* @return the locales and localized titles of this module
	*/
	@Override
	public Map<java.util.Locale, String> getTitleMap() {
		return _module.getTitleMap();
	}

	/**
	* Returns the trash entry created when this module was moved to the Recycle Bin. The trash entry may belong to one of the ancestors of this module.
	*
	* @return the trash entry created when this module was moved to the Recycle Bin
	*/
	@Override
	public com.liferay.trash.kernel.model.TrashEntry getTrashEntry()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _module.getTrashEntry();
	}

	/**
	* Returns the class primary key of the trash entry for this module.
	*
	* @return the class primary key of the trash entry for this module
	*/
	@Override
	public long getTrashEntryClassPK() {
		return _module.getTrashEntryClassPK();
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
		return _module.getTrashHandler();
	}

	@Override
	public String getURLEdit(
		com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay) {
		return _module.getURLEdit(themeDisplay);
	}

	@Override
	public String getURLNewActivity(
		com.liferay.portal.kernel.portlet.PortletLayoutFinder activityPortletLayoutFinder,
		javax.portlet.RenderRequest renderRequest,
		com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay) {
		return _module.getURLNewActivity(activityPortletLayoutFinder,
			renderRequest, themeDisplay);
	}

	@Override
	public String getURLView(
		com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay) {
		return _module.getURLView(themeDisplay);
	}

	/**
	* Returns the user ID of this module.
	*
	* @return the user ID of this module
	*/
	@Override
	public long getUserId() {
		return _module.getUserId();
	}

	/**
	* Returns the user name of this module.
	*
	* @return the user name of this module
	*/
	@Override
	public String getUserName() {
		return _module.getUserName();
	}

	/**
	* Returns the user uuid of this module.
	*
	* @return the user uuid of this module
	*/
	@Override
	public String getUserUuid() {
		return _module.getUserUuid();
	}

	/**
	* Returns the uuid of this module.
	*
	* @return the uuid of this module
	*/
	@Override
	public String getUuid() {
		return _module.getUuid();
	}

	@Override
	public int hashCode() {
		return _module.hashCode();
	}

	/**
	* Returns <code>true</code> if this module is approved.
	*
	* @return <code>true</code> if this module is approved; <code>false</code> otherwise
	*/
	@Override
	public boolean isApproved() {
		return _module.isApproved();
	}

	@Override
	public boolean isCachedModel() {
		return _module.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this module is denied.
	*
	* @return <code>true</code> if this module is denied; <code>false</code> otherwise
	*/
	@Override
	public boolean isDenied() {
		return _module.isDenied();
	}

	/**
	* Returns <code>true</code> if this module is a draft.
	*
	* @return <code>true</code> if this module is a draft; <code>false</code> otherwise
	*/
	@Override
	public boolean isDraft() {
		return _module.isDraft();
	}

	@Override
	public boolean isEscapedModel() {
		return _module.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this module is expired.
	*
	* @return <code>true</code> if this module is expired; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpired() {
		return _module.isExpired();
	}

	/**
	* Returns <code>true</code> if this module is inactive.
	*
	* @return <code>true</code> if this module is inactive; <code>false</code> otherwise
	*/
	@Override
	public boolean isInactive() {
		return _module.isInactive();
	}

	/**
	* Returns <code>true</code> if this module is incomplete.
	*
	* @return <code>true</code> if this module is incomplete; <code>false</code> otherwise
	*/
	@Override
	public boolean isIncomplete() {
		return _module.isIncomplete();
	}

	/**
	* Returns <code>true</code> if this module is in the Recycle Bin.
	*
	* @return <code>true</code> if this module is in the Recycle Bin; <code>false</code> otherwise
	*/
	@Override
	public boolean isInTrash() {
		return _module.isInTrash();
	}

	/**
	* Returns <code>true</code> if the parent of this module is in the Recycle Bin.
	*
	* @return <code>true</code> if the parent of this module is in the Recycle Bin; <code>false</code> otherwise
	*/
	@Override
	public boolean isInTrashContainer() {
		return _module.isInTrashContainer();
	}

	@Override
	public boolean isInTrashExplicitly() {
		return _module.isInTrashExplicitly();
	}

	@Override
	public boolean isInTrashImplicitly() {
		return _module.isInTrashImplicitly();
	}

	@Override
	public boolean isLocked(long userId,
		com.liferay.portal.kernel.security.permission.PermissionChecker permissionChecker) {
		return _module.isLocked(userId, permissionChecker);
	}

	@Override
	public boolean isNew() {
		return _module.isNew();
	}

	/**
	* Returns <code>true</code> if this module is pending.
	*
	* @return <code>true</code> if this module is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _module.isPending();
	}

	/**
	* Returns <code>true</code> if this module is scheduled.
	*
	* @return <code>true</code> if this module is scheduled; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduled() {
		return _module.isScheduled();
	}

	@Override
	public void persist() {
		_module.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {
		_module.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {
		_module.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	/**
	* Sets the allowed time of this module.
	*
	* @param allowedTime the allowed time of this module
	*/
	@Override
	public void setAllowedTime(long allowedTime) {
		_module.setAllowedTime(allowedTime);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_module.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this module.
	*
	* @param companyId the company ID of this module
	*/
	@Override
	public void setCompanyId(long companyId) {
		_module.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this module.
	*
	* @param createDate the create date of this module
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_module.setCreateDate(createDate);
	}

	/**
	* Sets the description of this module.
	*
	* @param description the description of this module
	*/
	@Override
	public void setDescription(String description) {
		_module.setDescription(description);
	}

	/**
	* Sets the localized description of this module in the language.
	*
	* @param description the localized description of this module
	* @param locale the locale of the language
	*/
	@Override
	public void setDescription(String description, java.util.Locale locale) {
		_module.setDescription(description, locale);
	}

	/**
	* Sets the localized description of this module in the language, and sets the default locale.
	*
	* @param description the localized description of this module
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescription(String description, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_module.setDescription(description, locale, defaultLocale);
	}

	@Override
	public void setDescriptionCurrentLanguageId(String languageId) {
		_module.setDescriptionCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized descriptions of this module from the map of locales and localized descriptions.
	*
	* @param descriptionMap the locales and localized descriptions of this module
	*/
	@Override
	public void setDescriptionMap(Map<java.util.Locale, String> descriptionMap) {
		_module.setDescriptionMap(descriptionMap);
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
		_module.setDescriptionMap(descriptionMap, defaultLocale);
	}

	/**
	* Sets the end date of this module.
	*
	* @param endDate the end date of this module
	*/
	@Override
	public void setEndDate(Date endDate) {
		_module.setEndDate(endDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_module.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_module.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_module.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this module.
	*
	* @param groupId the group ID of this module
	*/
	@Override
	public void setGroupId(long groupId) {
		_module.setGroupId(groupId);
	}

	@Override
	public void setImagesFolderId(long imagesFolderId) {
		_module.setImagesFolderId(imagesFolderId);
	}

	/**
	* Sets the last publish date of this module.
	*
	* @param lastPublishDate the last publish date of this module
	*/
	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		_module.setLastPublishDate(lastPublishDate);
	}

	/**
	* Sets the modified date of this module.
	*
	* @param modifiedDate the modified date of this module
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_module.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the module eval ID of this module.
	*
	* @param moduleEvalId the module eval ID of this module
	*/
	@Override
	public void setModuleEvalId(long moduleEvalId) {
		_module.setModuleEvalId(moduleEvalId);
	}

	/**
	* Sets the module extra data of this module.
	*
	* @param moduleExtraData the module extra data of this module
	*/
	@Override
	public void setModuleExtraData(String moduleExtraData) {
		_module.setModuleExtraData(moduleExtraData);
	}

	/**
	* Sets the module ID of this module.
	*
	* @param moduleId the module ID of this module
	*/
	@Override
	public void setModuleId(long moduleId) {
		_module.setModuleId(moduleId);
	}

	@Override
	public void setNew(boolean n) {
		_module.setNew(n);
	}

	/**
	* Sets the order of this module.
	*
	* @param order the order of this module
	*/
	@Override
	public void setOrder(long order) {
		_module.setOrder(order);
	}

	/**
	* Sets the primary key of this module.
	*
	* @param primaryKey the primary key of this module
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_module.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_module.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the small image ID of this module.
	*
	* @param smallImageId the small image ID of this module
	*/
	@Override
	public void setSmallImageId(long smallImageId) {
		_module.setSmallImageId(smallImageId);
	}

	@Override
	public void setSmallImageType(String smallImageType) {
		_module.setSmallImageType(smallImageType);
	}

	/**
	* Sets the start date of this module.
	*
	* @param startDate the start date of this module
	*/
	@Override
	public void setStartDate(Date startDate) {
		_module.setStartDate(startDate);
	}

	/**
	* Sets the status of this module.
	*
	* @param status the status of this module
	*/
	@Override
	public void setStatus(int status) {
		_module.setStatus(status);
	}

	/**
	* Sets the status by user ID of this module.
	*
	* @param statusByUserId the status by user ID of this module
	*/
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_module.setStatusByUserId(statusByUserId);
	}

	/**
	* Sets the status by user name of this module.
	*
	* @param statusByUserName the status by user name of this module
	*/
	@Override
	public void setStatusByUserName(String statusByUserName) {
		_module.setStatusByUserName(statusByUserName);
	}

	/**
	* Sets the status by user uuid of this module.
	*
	* @param statusByUserUuid the status by user uuid of this module
	*/
	@Override
	public void setStatusByUserUuid(String statusByUserUuid) {
		_module.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Sets the status date of this module.
	*
	* @param statusDate the status date of this module
	*/
	@Override
	public void setStatusDate(Date statusDate) {
		_module.setStatusDate(statusDate);
	}

	/**
	* Sets the title of this module.
	*
	* @param title the title of this module
	*/
	@Override
	public void setTitle(String title) {
		_module.setTitle(title);
	}

	/**
	* Sets the localized title of this module in the language.
	*
	* @param title the localized title of this module
	* @param locale the locale of the language
	*/
	@Override
	public void setTitle(String title, java.util.Locale locale) {
		_module.setTitle(title, locale);
	}

	/**
	* Sets the localized title of this module in the language, and sets the default locale.
	*
	* @param title the localized title of this module
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setTitle(String title, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_module.setTitle(title, locale, defaultLocale);
	}

	@Override
	public void setTitleCurrentLanguageId(String languageId) {
		_module.setTitleCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized titles of this module from the map of locales and localized titles.
	*
	* @param titleMap the locales and localized titles of this module
	*/
	@Override
	public void setTitleMap(Map<java.util.Locale, String> titleMap) {
		_module.setTitleMap(titleMap);
	}

	/**
	* Sets the localized titles of this module from the map of locales and localized titles, and sets the default locale.
	*
	* @param titleMap the locales and localized titles of this module
	* @param defaultLocale the default locale
	*/
	@Override
	public void setTitleMap(Map<java.util.Locale, String> titleMap,
		java.util.Locale defaultLocale) {
		_module.setTitleMap(titleMap, defaultLocale);
	}

	/**
	* Sets the user ID of this module.
	*
	* @param userId the user ID of this module
	*/
	@Override
	public void setUserId(long userId) {
		_module.setUserId(userId);
	}

	/**
	* Sets the user name of this module.
	*
	* @param userName the user name of this module
	*/
	@Override
	public void setUserName(String userName) {
		_module.setUserName(userName);
	}

	/**
	* Sets the user uuid of this module.
	*
	* @param userUuid the user uuid of this module
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_module.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this module.
	*
	* @param uuid the uuid of this module
	*/
	@Override
	public void setUuid(String uuid) {
		_module.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Module> toCacheModel() {
		return _module.toCacheModel();
	}

	@Override
	public Module toEscapedModel() {
		return new ModuleWrapper(_module.toEscapedModel());
	}

	@Override
	public String toString() {
		return _module.toString();
	}

	@Override
	public Module toUnescapedModel() {
		return new ModuleWrapper(_module.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _module.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ModuleWrapper)) {
			return false;
		}

		ModuleWrapper moduleWrapper = (ModuleWrapper)obj;

		if (Objects.equals(_module, moduleWrapper._module)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _module.getStagedModelType();
	}

	@Override
	public Module getWrappedModel() {
		return _module;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _module.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _module.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_module.resetOriginalValues();
	}

	private final Module _module;
}