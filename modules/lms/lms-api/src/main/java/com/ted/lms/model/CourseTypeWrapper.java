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

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link CourseType}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CourseType
 * @generated
 */
@ProviderType
public class CourseTypeWrapper implements CourseType, ModelWrapper<CourseType> {
	public CourseTypeWrapper(CourseType courseType) {
		_courseType = courseType;
	}

	@Override
	public Class<?> getModelClass() {
		return CourseType.class;
	}

	@Override
	public String getModelClassName() {
		return CourseType.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("courseTypeId", getCourseTypeId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("iconId", getIconId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long courseTypeId = (Long)attributes.get("courseTypeId");

		if (courseTypeId != null) {
			setCourseTypeId(courseTypeId);
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

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Long iconId = (Long)attributes.get("iconId");

		if (iconId != null) {
			setIconId(iconId);
		}
	}

	@Override
	public Object clone() {
		return new CourseTypeWrapper((CourseType)_courseType.clone());
	}

	@Override
	public int compareTo(CourseType courseType) {
		return _courseType.compareTo(courseType);
	}

	@Override
	public String[] getAvailableLanguageIds() {
		return _courseType.getAvailableLanguageIds();
	}

	/**
	* Returns the company ID of this course type.
	*
	* @return the company ID of this course type
	*/
	@Override
	public long getCompanyId() {
		return _courseType.getCompanyId();
	}

	/**
	* Returns the course type ID of this course type.
	*
	* @return the course type ID of this course type
	*/
	@Override
	public long getCourseTypeId() {
		return _courseType.getCourseTypeId();
	}

	/**
	* Returns the create date of this course type.
	*
	* @return the create date of this course type
	*/
	@Override
	public Date getCreateDate() {
		return _courseType.getCreateDate();
	}

	@Override
	public String getDefaultLanguageId() {
		return _courseType.getDefaultLanguageId();
	}

	/**
	* Returns the description of this course type.
	*
	* @return the description of this course type
	*/
	@Override
	public String getDescription() {
		return _courseType.getDescription();
	}

	/**
	* Returns the localized description of this course type in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized description of this course type
	*/
	@Override
	public String getDescription(java.util.Locale locale) {
		return _courseType.getDescription(locale);
	}

	/**
	* Returns the localized description of this course type in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this course type. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public String getDescription(java.util.Locale locale, boolean useDefault) {
		return _courseType.getDescription(locale, useDefault);
	}

	/**
	* Returns the localized description of this course type in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized description of this course type
	*/
	@Override
	public String getDescription(String languageId) {
		return _courseType.getDescription(languageId);
	}

	/**
	* Returns the localized description of this course type in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this course type
	*/
	@Override
	public String getDescription(String languageId, boolean useDefault) {
		return _courseType.getDescription(languageId, useDefault);
	}

	@Override
	public String getDescriptionCurrentLanguageId() {
		return _courseType.getDescriptionCurrentLanguageId();
	}

	@Override
	public String getDescriptionCurrentValue() {
		return _courseType.getDescriptionCurrentValue();
	}

	/**
	* Returns a map of the locales and localized descriptions of this course type.
	*
	* @return the locales and localized descriptions of this course type
	*/
	@Override
	public Map<java.util.Locale, String> getDescriptionMap() {
		return _courseType.getDescriptionMap();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _courseType.getExpandoBridge();
	}

	/**
	* Returns the group ID of this course type.
	*
	* @return the group ID of this course type
	*/
	@Override
	public long getGroupId() {
		return _courseType.getGroupId();
	}

	/**
	* Returns the icon ID of this course type.
	*
	* @return the icon ID of this course type
	*/
	@Override
	public long getIconId() {
		return _courseType.getIconId();
	}

	@Override
	public String getIconURL(
		com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _courseType.getIconURL(themeDisplay);
	}

	/**
	* Returns the modified date of this course type.
	*
	* @return the modified date of this course type
	*/
	@Override
	public Date getModifiedDate() {
		return _courseType.getModifiedDate();
	}

	/**
	* Returns the name of this course type.
	*
	* @return the name of this course type
	*/
	@Override
	public String getName() {
		return _courseType.getName();
	}

	/**
	* Returns the localized name of this course type in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized name of this course type
	*/
	@Override
	public String getName(java.util.Locale locale) {
		return _courseType.getName(locale);
	}

	/**
	* Returns the localized name of this course type in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized name of this course type. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public String getName(java.util.Locale locale, boolean useDefault) {
		return _courseType.getName(locale, useDefault);
	}

	/**
	* Returns the localized name of this course type in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized name of this course type
	*/
	@Override
	public String getName(String languageId) {
		return _courseType.getName(languageId);
	}

	/**
	* Returns the localized name of this course type in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized name of this course type
	*/
	@Override
	public String getName(String languageId, boolean useDefault) {
		return _courseType.getName(languageId, useDefault);
	}

	@Override
	public String getNameCurrentLanguageId() {
		return _courseType.getNameCurrentLanguageId();
	}

	@Override
	public String getNameCurrentValue() {
		return _courseType.getNameCurrentValue();
	}

	/**
	* Returns a map of the locales and localized names of this course type.
	*
	* @return the locales and localized names of this course type
	*/
	@Override
	public Map<java.util.Locale, String> getNameMap() {
		return _courseType.getNameMap();
	}

	/**
	* Returns the primary key of this course type.
	*
	* @return the primary key of this course type
	*/
	@Override
	public long getPrimaryKey() {
		return _courseType.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _courseType.getPrimaryKeyObj();
	}

	/**
	* Returns the user ID of this course type.
	*
	* @return the user ID of this course type
	*/
	@Override
	public long getUserId() {
		return _courseType.getUserId();
	}

	/**
	* Returns the user name of this course type.
	*
	* @return the user name of this course type
	*/
	@Override
	public String getUserName() {
		return _courseType.getUserName();
	}

	/**
	* Returns the user uuid of this course type.
	*
	* @return the user uuid of this course type
	*/
	@Override
	public String getUserUuid() {
		return _courseType.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _courseType.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _courseType.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _courseType.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _courseType.isNew();
	}

	@Override
	public void persist() {
		_courseType.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {
		_courseType.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {
		_courseType.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_courseType.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this course type.
	*
	* @param companyId the company ID of this course type
	*/
	@Override
	public void setCompanyId(long companyId) {
		_courseType.setCompanyId(companyId);
	}

	/**
	* Sets the course type ID of this course type.
	*
	* @param courseTypeId the course type ID of this course type
	*/
	@Override
	public void setCourseTypeId(long courseTypeId) {
		_courseType.setCourseTypeId(courseTypeId);
	}

	/**
	* Sets the create date of this course type.
	*
	* @param createDate the create date of this course type
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_courseType.setCreateDate(createDate);
	}

	/**
	* Sets the description of this course type.
	*
	* @param description the description of this course type
	*/
	@Override
	public void setDescription(String description) {
		_courseType.setDescription(description);
	}

	/**
	* Sets the localized description of this course type in the language.
	*
	* @param description the localized description of this course type
	* @param locale the locale of the language
	*/
	@Override
	public void setDescription(String description, java.util.Locale locale) {
		_courseType.setDescription(description, locale);
	}

	/**
	* Sets the localized description of this course type in the language, and sets the default locale.
	*
	* @param description the localized description of this course type
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescription(String description, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_courseType.setDescription(description, locale, defaultLocale);
	}

	@Override
	public void setDescriptionCurrentLanguageId(String languageId) {
		_courseType.setDescriptionCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized descriptions of this course type from the map of locales and localized descriptions.
	*
	* @param descriptionMap the locales and localized descriptions of this course type
	*/
	@Override
	public void setDescriptionMap(Map<java.util.Locale, String> descriptionMap) {
		_courseType.setDescriptionMap(descriptionMap);
	}

	/**
	* Sets the localized descriptions of this course type from the map of locales and localized descriptions, and sets the default locale.
	*
	* @param descriptionMap the locales and localized descriptions of this course type
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, String> descriptionMap,
		java.util.Locale defaultLocale) {
		_courseType.setDescriptionMap(descriptionMap, defaultLocale);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_courseType.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_courseType.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_courseType.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this course type.
	*
	* @param groupId the group ID of this course type
	*/
	@Override
	public void setGroupId(long groupId) {
		_courseType.setGroupId(groupId);
	}

	/**
	* Sets the icon ID of this course type.
	*
	* @param iconId the icon ID of this course type
	*/
	@Override
	public void setIconId(long iconId) {
		_courseType.setIconId(iconId);
	}

	/**
	* Sets the modified date of this course type.
	*
	* @param modifiedDate the modified date of this course type
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_courseType.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this course type.
	*
	* @param name the name of this course type
	*/
	@Override
	public void setName(String name) {
		_courseType.setName(name);
	}

	/**
	* Sets the localized name of this course type in the language.
	*
	* @param name the localized name of this course type
	* @param locale the locale of the language
	*/
	@Override
	public void setName(String name, java.util.Locale locale) {
		_courseType.setName(name, locale);
	}

	/**
	* Sets the localized name of this course type in the language, and sets the default locale.
	*
	* @param name the localized name of this course type
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setName(String name, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_courseType.setName(name, locale, defaultLocale);
	}

	@Override
	public void setNameCurrentLanguageId(String languageId) {
		_courseType.setNameCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized names of this course type from the map of locales and localized names.
	*
	* @param nameMap the locales and localized names of this course type
	*/
	@Override
	public void setNameMap(Map<java.util.Locale, String> nameMap) {
		_courseType.setNameMap(nameMap);
	}

	/**
	* Sets the localized names of this course type from the map of locales and localized names, and sets the default locale.
	*
	* @param nameMap the locales and localized names of this course type
	* @param defaultLocale the default locale
	*/
	@Override
	public void setNameMap(Map<java.util.Locale, String> nameMap,
		java.util.Locale defaultLocale) {
		_courseType.setNameMap(nameMap, defaultLocale);
	}

	@Override
	public void setNew(boolean n) {
		_courseType.setNew(n);
	}

	/**
	* Sets the primary key of this course type.
	*
	* @param primaryKey the primary key of this course type
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_courseType.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_courseType.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the user ID of this course type.
	*
	* @param userId the user ID of this course type
	*/
	@Override
	public void setUserId(long userId) {
		_courseType.setUserId(userId);
	}

	/**
	* Sets the user name of this course type.
	*
	* @param userName the user name of this course type
	*/
	@Override
	public void setUserName(String userName) {
		_courseType.setUserName(userName);
	}

	/**
	* Sets the user uuid of this course type.
	*
	* @param userUuid the user uuid of this course type
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_courseType.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<CourseType> toCacheModel() {
		return _courseType.toCacheModel();
	}

	@Override
	public CourseType toEscapedModel() {
		return new CourseTypeWrapper(_courseType.toEscapedModel());
	}

	@Override
	public String toString() {
		return _courseType.toString();
	}

	@Override
	public CourseType toUnescapedModel() {
		return new CourseTypeWrapper(_courseType.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _courseType.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CourseTypeWrapper)) {
			return false;
		}

		CourseTypeWrapper courseTypeWrapper = (CourseTypeWrapper)obj;

		if (Objects.equals(_courseType, courseTypeWrapper._courseType)) {
			return true;
		}

		return false;
	}

	@Override
	public CourseType getWrappedModel() {
		return _courseType;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _courseType.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _courseType.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_courseType.resetOriginalValues();
	}

	private final CourseType _courseType;
}