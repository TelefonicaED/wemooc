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
 * This class is a wrapper for {@link LearningActivityResult}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LearningActivityResult
 * @generated
 */
@ProviderType
public class LearningActivityResultWrapper implements LearningActivityResult,
	ModelWrapper<LearningActivityResult> {
	public LearningActivityResultWrapper(
		LearningActivityResult learningActivityResult) {
		_learningActivityResult = learningActivityResult;
	}

	@Override
	public Class<?> getModelClass() {
		return LearningActivityResult.class;
	}

	@Override
	public String getModelClassName() {
		return LearningActivityResult.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("larId", getLarId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userModifiedId", getUserModifiedId());
		attributes.put("userModifiedName", getUserModifiedName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("actId", getActId());
		attributes.put("userId", getUserId());
		attributes.put("result", getResult());
		attributes.put("comments", getComments());
		attributes.put("passed", isPassed());
		attributes.put("startDate", getStartDate());
		attributes.put("endDate", getEndDate());
		attributes.put("extraData", getExtraData());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long larId = (Long)attributes.get("larId");

		if (larId != null) {
			setLarId(larId);
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

		Long actId = (Long)attributes.get("actId");

		if (actId != null) {
			setActId(actId);
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

		Date endDate = (Date)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}

		String extraData = (String)attributes.get("extraData");

		if (extraData != null) {
			setExtraData(extraData);
		}
	}

	@Override
	public Object clone() {
		return new LearningActivityResultWrapper((LearningActivityResult)_learningActivityResult.clone());
	}

	@Override
	public int compareTo(LearningActivityResult learningActivityResult) {
		return _learningActivityResult.compareTo(learningActivityResult);
	}

	/**
	* Returns the act ID of this learning activity result.
	*
	* @return the act ID of this learning activity result
	*/
	@Override
	public long getActId() {
		return _learningActivityResult.getActId();
	}

	/**
	* Returns the comments of this learning activity result.
	*
	* @return the comments of this learning activity result
	*/
	@Override
	public String getComments() {
		return _learningActivityResult.getComments();
	}

	/**
	* Returns the company ID of this learning activity result.
	*
	* @return the company ID of this learning activity result
	*/
	@Override
	public long getCompanyId() {
		return _learningActivityResult.getCompanyId();
	}

	/**
	* Returns the create date of this learning activity result.
	*
	* @return the create date of this learning activity result
	*/
	@Override
	public Date getCreateDate() {
		return _learningActivityResult.getCreateDate();
	}

	/**
	* Returns the end date of this learning activity result.
	*
	* @return the end date of this learning activity result
	*/
	@Override
	public Date getEndDate() {
		return _learningActivityResult.getEndDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _learningActivityResult.getExpandoBridge();
	}

	/**
	* Returns the extra data of this learning activity result.
	*
	* @return the extra data of this learning activity result
	*/
	@Override
	public String getExtraData() {
		return _learningActivityResult.getExtraData();
	}

	/**
	* Returns the group ID of this learning activity result.
	*
	* @return the group ID of this learning activity result
	*/
	@Override
	public long getGroupId() {
		return _learningActivityResult.getGroupId();
	}

	/**
	* Returns the lar ID of this learning activity result.
	*
	* @return the lar ID of this learning activity result
	*/
	@Override
	public long getLarId() {
		return _learningActivityResult.getLarId();
	}

	/**
	* Returns the modified date of this learning activity result.
	*
	* @return the modified date of this learning activity result
	*/
	@Override
	public Date getModifiedDate() {
		return _learningActivityResult.getModifiedDate();
	}

	/**
	* Returns the passed of this learning activity result.
	*
	* @return the passed of this learning activity result
	*/
	@Override
	public boolean getPassed() {
		return _learningActivityResult.getPassed();
	}

	/**
	* Returns the primary key of this learning activity result.
	*
	* @return the primary key of this learning activity result
	*/
	@Override
	public long getPrimaryKey() {
		return _learningActivityResult.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _learningActivityResult.getPrimaryKeyObj();
	}

	/**
	* Returns the result of this learning activity result.
	*
	* @return the result of this learning activity result
	*/
	@Override
	public double getResult() {
		return _learningActivityResult.getResult();
	}

	/**
	* Returns the start date of this learning activity result.
	*
	* @return the start date of this learning activity result
	*/
	@Override
	public Date getStartDate() {
		return _learningActivityResult.getStartDate();
	}

	@Override
	public String getStatusProperties() {
		return _learningActivityResult.getStatusProperties();
	}

	/**
	* Returns the user ID of this learning activity result.
	*
	* @return the user ID of this learning activity result
	*/
	@Override
	public long getUserId() {
		return _learningActivityResult.getUserId();
	}

	/**
	* Returns the user modified ID of this learning activity result.
	*
	* @return the user modified ID of this learning activity result
	*/
	@Override
	public long getUserModifiedId() {
		return _learningActivityResult.getUserModifiedId();
	}

	/**
	* Returns the user modified name of this learning activity result.
	*
	* @return the user modified name of this learning activity result
	*/
	@Override
	public String getUserModifiedName() {
		return _learningActivityResult.getUserModifiedName();
	}

	/**
	* Returns the user uuid of this learning activity result.
	*
	* @return the user uuid of this learning activity result
	*/
	@Override
	public String getUserUuid() {
		return _learningActivityResult.getUserUuid();
	}

	/**
	* Returns the uuid of this learning activity result.
	*
	* @return the uuid of this learning activity result
	*/
	@Override
	public String getUuid() {
		return _learningActivityResult.getUuid();
	}

	@Override
	public int hashCode() {
		return _learningActivityResult.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _learningActivityResult.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _learningActivityResult.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _learningActivityResult.isNew();
	}

	/**
	* Returns <code>true</code> if this learning activity result is passed.
	*
	* @return <code>true</code> if this learning activity result is passed; <code>false</code> otherwise
	*/
	@Override
	public boolean isPassed() {
		return _learningActivityResult.isPassed();
	}

	@Override
	public void persist() {
		_learningActivityResult.persist();
	}

	/**
	* Sets the act ID of this learning activity result.
	*
	* @param actId the act ID of this learning activity result
	*/
	@Override
	public void setActId(long actId) {
		_learningActivityResult.setActId(actId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_learningActivityResult.setCachedModel(cachedModel);
	}

	/**
	* Sets the comments of this learning activity result.
	*
	* @param comments the comments of this learning activity result
	*/
	@Override
	public void setComments(String comments) {
		_learningActivityResult.setComments(comments);
	}

	/**
	* Sets the company ID of this learning activity result.
	*
	* @param companyId the company ID of this learning activity result
	*/
	@Override
	public void setCompanyId(long companyId) {
		_learningActivityResult.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this learning activity result.
	*
	* @param createDate the create date of this learning activity result
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_learningActivityResult.setCreateDate(createDate);
	}

	/**
	* Sets the end date of this learning activity result.
	*
	* @param endDate the end date of this learning activity result
	*/
	@Override
	public void setEndDate(Date endDate) {
		_learningActivityResult.setEndDate(endDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_learningActivityResult.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_learningActivityResult.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_learningActivityResult.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the extra data of this learning activity result.
	*
	* @param extraData the extra data of this learning activity result
	*/
	@Override
	public void setExtraData(String extraData) {
		_learningActivityResult.setExtraData(extraData);
	}

	/**
	* Sets the group ID of this learning activity result.
	*
	* @param groupId the group ID of this learning activity result
	*/
	@Override
	public void setGroupId(long groupId) {
		_learningActivityResult.setGroupId(groupId);
	}

	/**
	* Sets the lar ID of this learning activity result.
	*
	* @param larId the lar ID of this learning activity result
	*/
	@Override
	public void setLarId(long larId) {
		_learningActivityResult.setLarId(larId);
	}

	/**
	* Sets the modified date of this learning activity result.
	*
	* @param modifiedDate the modified date of this learning activity result
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_learningActivityResult.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_learningActivityResult.setNew(n);
	}

	/**
	* Sets whether this learning activity result is passed.
	*
	* @param passed the passed of this learning activity result
	*/
	@Override
	public void setPassed(boolean passed) {
		_learningActivityResult.setPassed(passed);
	}

	/**
	* Sets the primary key of this learning activity result.
	*
	* @param primaryKey the primary key of this learning activity result
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_learningActivityResult.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_learningActivityResult.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the result of this learning activity result.
	*
	* @param result the result of this learning activity result
	*/
	@Override
	public void setResult(double result) {
		_learningActivityResult.setResult(result);
	}

	/**
	* Sets the start date of this learning activity result.
	*
	* @param startDate the start date of this learning activity result
	*/
	@Override
	public void setStartDate(Date startDate) {
		_learningActivityResult.setStartDate(startDate);
	}

	/**
	* Sets the user ID of this learning activity result.
	*
	* @param userId the user ID of this learning activity result
	*/
	@Override
	public void setUserId(long userId) {
		_learningActivityResult.setUserId(userId);
	}

	/**
	* Sets the user modified ID of this learning activity result.
	*
	* @param userModifiedId the user modified ID of this learning activity result
	*/
	@Override
	public void setUserModifiedId(long userModifiedId) {
		_learningActivityResult.setUserModifiedId(userModifiedId);
	}

	/**
	* Sets the user modified name of this learning activity result.
	*
	* @param userModifiedName the user modified name of this learning activity result
	*/
	@Override
	public void setUserModifiedName(String userModifiedName) {
		_learningActivityResult.setUserModifiedName(userModifiedName);
	}

	/**
	* Sets the user uuid of this learning activity result.
	*
	* @param userUuid the user uuid of this learning activity result
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_learningActivityResult.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this learning activity result.
	*
	* @param uuid the uuid of this learning activity result
	*/
	@Override
	public void setUuid(String uuid) {
		_learningActivityResult.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<LearningActivityResult> toCacheModel() {
		return _learningActivityResult.toCacheModel();
	}

	@Override
	public LearningActivityResult toEscapedModel() {
		return new LearningActivityResultWrapper(_learningActivityResult.toEscapedModel());
	}

	@Override
	public String toString() {
		return _learningActivityResult.toString();
	}

	@Override
	public LearningActivityResult toUnescapedModel() {
		return new LearningActivityResultWrapper(_learningActivityResult.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _learningActivityResult.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LearningActivityResultWrapper)) {
			return false;
		}

		LearningActivityResultWrapper learningActivityResultWrapper = (LearningActivityResultWrapper)obj;

		if (Objects.equals(_learningActivityResult,
					learningActivityResultWrapper._learningActivityResult)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _learningActivityResult.getStagedModelType();
	}

	@Override
	public LearningActivityResult getWrappedModel() {
		return _learningActivityResult;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _learningActivityResult.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _learningActivityResult.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_learningActivityResult.resetOriginalValues();
	}

	private final LearningActivityResult _learningActivityResult;
}