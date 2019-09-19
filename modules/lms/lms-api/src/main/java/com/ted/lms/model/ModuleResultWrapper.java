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

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

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
public class ModuleResultWrapper
	extends BaseModelWrapper<ModuleResult>
	implements ModuleResult, ModelWrapper<ModuleResult> {

	public ModuleResultWrapper(ModuleResult moduleResult) {
		super(moduleResult);
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

	/**
	 * Returns the comments of this module result.
	 *
	 * @return the comments of this module result
	 */
	@Override
	public String getComments() {
		return model.getComments();
	}

	/**
	 * Returns the company ID of this module result.
	 *
	 * @return the company ID of this module result
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this module result.
	 *
	 * @return the create date of this module result
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the extra data of this module result.
	 *
	 * @return the extra data of this module result
	 */
	@Override
	public String getExtraData() {
		return model.getExtraData();
	}

	/**
	 * Returns the group ID of this module result.
	 *
	 * @return the group ID of this module result
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the modified date of this module result.
	 *
	 * @return the modified date of this module result
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the module ID of this module result.
	 *
	 * @return the module ID of this module result
	 */
	@Override
	public long getModuleId() {
		return model.getModuleId();
	}

	@Override
	public String getModuleStatus(java.util.Locale locale) {
		return model.getModuleStatus(locale);
	}

	/**
	 * Returns the mr ID of this module result.
	 *
	 * @return the mr ID of this module result
	 */
	@Override
	public long getMrId() {
		return model.getMrId();
	}

	/**
	 * Returns the passed of this module result.
	 *
	 * @return the passed of this module result
	 */
	@Override
	public boolean getPassed() {
		return model.getPassed();
	}

	/**
	 * Returns the passed date of this module result.
	 *
	 * @return the passed date of this module result
	 */
	@Override
	public Date getPassedDate() {
		return model.getPassedDate();
	}

	/**
	 * Returns the primary key of this module result.
	 *
	 * @return the primary key of this module result
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the result of this module result.
	 *
	 * @return the result of this module result
	 */
	@Override
	public double getResult() {
		return model.getResult();
	}

	/**
	 * Returns the start date of this module result.
	 *
	 * @return the start date of this module result
	 */
	@Override
	public Date getStartDate() {
		return model.getStartDate();
	}

	/**
	 * Returns the user ID of this module result.
	 *
	 * @return the user ID of this module result
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user modified ID of this module result.
	 *
	 * @return the user modified ID of this module result
	 */
	@Override
	public long getUserModifiedId() {
		return model.getUserModifiedId();
	}

	/**
	 * Returns the user modified name of this module result.
	 *
	 * @return the user modified name of this module result
	 */
	@Override
	public String getUserModifiedName() {
		return model.getUserModifiedName();
	}

	/**
	 * Returns the user uuid of this module result.
	 *
	 * @return the user uuid of this module result
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	@Override
	public boolean isFinished() {
		return model.isFinished();
	}

	/**
	 * Returns <code>true</code> if this module result is passed.
	 *
	 * @return <code>true</code> if this module result is passed; <code>false</code> otherwise
	 */
	@Override
	public boolean isPassed() {
		return model.isPassed();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the comments of this module result.
	 *
	 * @param comments the comments of this module result
	 */
	@Override
	public void setComments(String comments) {
		model.setComments(comments);
	}

	/**
	 * Sets the company ID of this module result.
	 *
	 * @param companyId the company ID of this module result
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this module result.
	 *
	 * @param createDate the create date of this module result
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the extra data of this module result.
	 *
	 * @param extraData the extra data of this module result
	 */
	@Override
	public void setExtraData(String extraData) {
		model.setExtraData(extraData);
	}

	/**
	 * Sets the group ID of this module result.
	 *
	 * @param groupId the group ID of this module result
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the modified date of this module result.
	 *
	 * @param modifiedDate the modified date of this module result
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the module ID of this module result.
	 *
	 * @param moduleId the module ID of this module result
	 */
	@Override
	public void setModuleId(long moduleId) {
		model.setModuleId(moduleId);
	}

	/**
	 * Sets the mr ID of this module result.
	 *
	 * @param mrId the mr ID of this module result
	 */
	@Override
	public void setMrId(long mrId) {
		model.setMrId(mrId);
	}

	/**
	 * Sets whether this module result is passed.
	 *
	 * @param passed the passed of this module result
	 */
	@Override
	public void setPassed(boolean passed) {
		model.setPassed(passed);
	}

	/**
	 * Sets the passed date of this module result.
	 *
	 * @param passedDate the passed date of this module result
	 */
	@Override
	public void setPassedDate(Date passedDate) {
		model.setPassedDate(passedDate);
	}

	/**
	 * Sets the primary key of this module result.
	 *
	 * @param primaryKey the primary key of this module result
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the result of this module result.
	 *
	 * @param result the result of this module result
	 */
	@Override
	public void setResult(double result) {
		model.setResult(result);
	}

	/**
	 * Sets the start date of this module result.
	 *
	 * @param startDate the start date of this module result
	 */
	@Override
	public void setStartDate(Date startDate) {
		model.setStartDate(startDate);
	}

	/**
	 * Sets the user ID of this module result.
	 *
	 * @param userId the user ID of this module result
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user modified ID of this module result.
	 *
	 * @param userModifiedId the user modified ID of this module result
	 */
	@Override
	public void setUserModifiedId(long userModifiedId) {
		model.setUserModifiedId(userModifiedId);
	}

	/**
	 * Sets the user modified name of this module result.
	 *
	 * @param userModifiedName the user modified name of this module result
	 */
	@Override
	public void setUserModifiedName(String userModifiedName) {
		model.setUserModifiedName(userModifiedName);
	}

	/**
	 * Sets the user uuid of this module result.
	 *
	 * @param userUuid the user uuid of this module result
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	@Override
	protected ModuleResultWrapper wrap(ModuleResult moduleResult) {
		return new ModuleResultWrapper(moduleResult);
	}

}