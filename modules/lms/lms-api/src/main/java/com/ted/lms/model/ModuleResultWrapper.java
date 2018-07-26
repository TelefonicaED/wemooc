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
 * This class is a wrapper for {@link ModuleResult}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ModuleResult
 * @generated
 */
@ProviderType
public class ModuleResultWrapper implements ModuleResult,
	ModelWrapper<ModuleResult> {
	public ModuleResultWrapper(ModuleResult moduleResult) {
		_moduleResult = moduleResult;
	}

	@Override
	public Class<?> getModelClass() {
		return ModuleResult.class;
	}

	@Override
	public String getModelClassName() {
		return ModuleResult.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mrId", getMrId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userModifiedId", getUserModifiedId());
		attributes.put("userModifiedName", getUserModifiedName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("moduleId", getModuleId());
		attributes.put("userId", getUserId());
		attributes.put("result", getResult());
		attributes.put("comments", getComments());
		attributes.put("passed", isPassed());
		attributes.put("startDate", getStartDate());
		attributes.put("passedDate", getPassedDate());
		attributes.put("extraData", getExtraData());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mrId = (Long)attributes.get("mrId");

		if (mrId != null) {
			setMrId(mrId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userModifiedId = (Long)attributes.get("userModifiedId");

		if (userModifiedId != null) {
			setUserModifiedId(userModifiedId);
		}

		String userModifiedName = (String)attributes.get("userModifiedName");

		if (userModifiedName != null) {
			setUserModifiedName(userModifiedName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long moduleId = (Long)attributes.get("moduleId");

		if (moduleId != null) {
			setModuleId(moduleId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Double result = (Double)attributes.get("result");

		if (result != null) {
			setResult(result);
		}

		String comments = (String)attributes.get("comments");

		if (comments != null) {
			setComments(comments);
		}

		Boolean passed = (Boolean)attributes.get("passed");

		if (passed != null) {
			setPassed(passed);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Date passedDate = (Date)attributes.get("passedDate");

		if (passedDate != null) {
			setPassedDate(passedDate);
		}

		String extraData = (String)attributes.get("extraData");

		if (extraData != null) {
			setExtraData(extraData);
		}
	}

	@Override
	public Object clone() {
		return new ModuleResultWrapper((ModuleResult)_moduleResult.clone());
	}

	@Override
	public int compareTo(ModuleResult moduleResult) {
		return _moduleResult.compareTo(moduleResult);
	}

	/**
	* Returns the comments of this module result.
	*
	* @return the comments of this module result
	*/
	@Override
	public String getComments() {
		return _moduleResult.getComments();
	}

	/**
	* Returns the company ID of this module result.
	*
	* @return the company ID of this module result
	*/
	@Override
	public long getCompanyId() {
		return _moduleResult.getCompanyId();
	}

	/**
	* Returns the create date of this module result.
	*
	* @return the create date of this module result
	*/
	@Override
	public Date getCreateDate() {
		return _moduleResult.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _moduleResult.getExpandoBridge();
	}

	/**
	* Returns the extra data of this module result.
	*
	* @return the extra data of this module result
	*/
	@Override
	public String getExtraData() {
		return _moduleResult.getExtraData();
	}

	/**
	* Returns the group ID of this module result.
	*
	* @return the group ID of this module result
	*/
	@Override
	public long getGroupId() {
		return _moduleResult.getGroupId();
	}

	/**
	* Returns the modified date of this module result.
	*
	* @return the modified date of this module result
	*/
	@Override
	public Date getModifiedDate() {
		return _moduleResult.getModifiedDate();
	}

	/**
	* Returns the module ID of this module result.
	*
	* @return the module ID of this module result
	*/
	@Override
	public long getModuleId() {
		return _moduleResult.getModuleId();
	}

	/**
	* Returns the mr ID of this module result.
	*
	* @return the mr ID of this module result
	*/
	@Override
	public long getMrId() {
		return _moduleResult.getMrId();
	}

	/**
	* Returns the passed of this module result.
	*
	* @return the passed of this module result
	*/
	@Override
	public boolean getPassed() {
		return _moduleResult.getPassed();
	}

	/**
	* Returns the passed date of this module result.
	*
	* @return the passed date of this module result
	*/
	@Override
	public Date getPassedDate() {
		return _moduleResult.getPassedDate();
	}

	/**
	* Returns the primary key of this module result.
	*
	* @return the primary key of this module result
	*/
	@Override
	public long getPrimaryKey() {
		return _moduleResult.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _moduleResult.getPrimaryKeyObj();
	}

	/**
	* Returns the result of this module result.
	*
	* @return the result of this module result
	*/
	@Override
	public double getResult() {
		return _moduleResult.getResult();
	}

	/**
	* Returns the start date of this module result.
	*
	* @return the start date of this module result
	*/
	@Override
	public Date getStartDate() {
		return _moduleResult.getStartDate();
	}

	/**
	* Returns the user ID of this module result.
	*
	* @return the user ID of this module result
	*/
	@Override
	public long getUserId() {
		return _moduleResult.getUserId();
	}

	/**
	* Returns the user modified ID of this module result.
	*
	* @return the user modified ID of this module result
	*/
	@Override
	public long getUserModifiedId() {
		return _moduleResult.getUserModifiedId();
	}

	/**
	* Returns the user modified name of this module result.
	*
	* @return the user modified name of this module result
	*/
	@Override
	public String getUserModifiedName() {
		return _moduleResult.getUserModifiedName();
	}

	/**
	* Returns the user uuid of this module result.
	*
	* @return the user uuid of this module result
	*/
	@Override
	public String getUserUuid() {
		return _moduleResult.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _moduleResult.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _moduleResult.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _moduleResult.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _moduleResult.isNew();
	}

	/**
	* Returns <code>true</code> if this module result is passed.
	*
	* @return <code>true</code> if this module result is passed; <code>false</code> otherwise
	*/
	@Override
	public boolean isPassed() {
		return _moduleResult.isPassed();
	}

	@Override
	public void persist() {
		_moduleResult.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_moduleResult.setCachedModel(cachedModel);
	}

	/**
	* Sets the comments of this module result.
	*
	* @param comments the comments of this module result
	*/
	@Override
	public void setComments(String comments) {
		_moduleResult.setComments(comments);
	}

	/**
	* Sets the company ID of this module result.
	*
	* @param companyId the company ID of this module result
	*/
	@Override
	public void setCompanyId(long companyId) {
		_moduleResult.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this module result.
	*
	* @param createDate the create date of this module result
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_moduleResult.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_moduleResult.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_moduleResult.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_moduleResult.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the extra data of this module result.
	*
	* @param extraData the extra data of this module result
	*/
	@Override
	public void setExtraData(String extraData) {
		_moduleResult.setExtraData(extraData);
	}

	/**
	* Sets the group ID of this module result.
	*
	* @param groupId the group ID of this module result
	*/
	@Override
	public void setGroupId(long groupId) {
		_moduleResult.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this module result.
	*
	* @param modifiedDate the modified date of this module result
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_moduleResult.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the module ID of this module result.
	*
	* @param moduleId the module ID of this module result
	*/
	@Override
	public void setModuleId(long moduleId) {
		_moduleResult.setModuleId(moduleId);
	}

	/**
	* Sets the mr ID of this module result.
	*
	* @param mrId the mr ID of this module result
	*/
	@Override
	public void setMrId(long mrId) {
		_moduleResult.setMrId(mrId);
	}

	@Override
	public void setNew(boolean n) {
		_moduleResult.setNew(n);
	}

	/**
	* Sets whether this module result is passed.
	*
	* @param passed the passed of this module result
	*/
	@Override
	public void setPassed(boolean passed) {
		_moduleResult.setPassed(passed);
	}

	/**
	* Sets the passed date of this module result.
	*
	* @param passedDate the passed date of this module result
	*/
	@Override
	public void setPassedDate(Date passedDate) {
		_moduleResult.setPassedDate(passedDate);
	}

	/**
	* Sets the primary key of this module result.
	*
	* @param primaryKey the primary key of this module result
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_moduleResult.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_moduleResult.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the result of this module result.
	*
	* @param result the result of this module result
	*/
	@Override
	public void setResult(double result) {
		_moduleResult.setResult(result);
	}

	/**
	* Sets the start date of this module result.
	*
	* @param startDate the start date of this module result
	*/
	@Override
	public void setStartDate(Date startDate) {
		_moduleResult.setStartDate(startDate);
	}

	/**
	* Sets the user ID of this module result.
	*
	* @param userId the user ID of this module result
	*/
	@Override
	public void setUserId(long userId) {
		_moduleResult.setUserId(userId);
	}

	/**
	* Sets the user modified ID of this module result.
	*
	* @param userModifiedId the user modified ID of this module result
	*/
	@Override
	public void setUserModifiedId(long userModifiedId) {
		_moduleResult.setUserModifiedId(userModifiedId);
	}

	/**
	* Sets the user modified name of this module result.
	*
	* @param userModifiedName the user modified name of this module result
	*/
	@Override
	public void setUserModifiedName(String userModifiedName) {
		_moduleResult.setUserModifiedName(userModifiedName);
	}

	/**
	* Sets the user uuid of this module result.
	*
	* @param userUuid the user uuid of this module result
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_moduleResult.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ModuleResult> toCacheModel() {
		return _moduleResult.toCacheModel();
	}

	@Override
	public ModuleResult toEscapedModel() {
		return new ModuleResultWrapper(_moduleResult.toEscapedModel());
	}

	@Override
	public String toString() {
		return _moduleResult.toString();
	}

	@Override
	public ModuleResult toUnescapedModel() {
		return new ModuleResultWrapper(_moduleResult.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _moduleResult.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ModuleResultWrapper)) {
			return false;
		}

		ModuleResultWrapper moduleResultWrapper = (ModuleResultWrapper)obj;

		if (Objects.equals(_moduleResult, moduleResultWrapper._moduleResult)) {
			return true;
		}

		return false;
	}

	@Override
	public ModuleResult getWrappedModel() {
		return _moduleResult;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _moduleResult.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _moduleResult.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_moduleResult.resetOriginalValues();
	}

	private final ModuleResult _moduleResult;
}