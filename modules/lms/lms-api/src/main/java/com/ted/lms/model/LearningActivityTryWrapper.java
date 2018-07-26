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
 * This class is a wrapper for {@link LearningActivityTry}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LearningActivityTry
 * @generated
 */
@ProviderType
public class LearningActivityTryWrapper implements LearningActivityTry,
	ModelWrapper<LearningActivityTry> {
	public LearningActivityTryWrapper(LearningActivityTry learningActivityTry) {
		_learningActivityTry = learningActivityTry;
	}

	@Override
	public Class<?> getModelClass() {
		return LearningActivityTry.class;
	}

	@Override
	public String getModelClassName() {
		return LearningActivityTry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("latId", getLatId());
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
		attributes.put("startDate", getStartDate());
		attributes.put("endDate", getEndDate());
		attributes.put("endUserDate", getEndUserDate());
		attributes.put("tryResultData", getTryResultData());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long latId = (Long)attributes.get("latId");

		if (latId != null) {
			setLatId(latId);
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

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Date endDate = (Date)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}

		Date endUserDate = (Date)attributes.get("endUserDate");

		if (endUserDate != null) {
			setEndUserDate(endUserDate);
		}

		String tryResultData = (String)attributes.get("tryResultData");

		if (tryResultData != null) {
			setTryResultData(tryResultData);
		}
	}

	@Override
	public Object clone() {
		return new LearningActivityTryWrapper((LearningActivityTry)_learningActivityTry.clone());
	}

	@Override
	public int compareTo(LearningActivityTry learningActivityTry) {
		return _learningActivityTry.compareTo(learningActivityTry);
	}

	/**
	* Returns the act ID of this learning activity try.
	*
	* @return the act ID of this learning activity try
	*/
	@Override
	public long getActId() {
		return _learningActivityTry.getActId();
	}

	/**
	* Returns the comments of this learning activity try.
	*
	* @return the comments of this learning activity try
	*/
	@Override
	public String getComments() {
		return _learningActivityTry.getComments();
	}

	/**
	* Returns the company ID of this learning activity try.
	*
	* @return the company ID of this learning activity try
	*/
	@Override
	public long getCompanyId() {
		return _learningActivityTry.getCompanyId();
	}

	/**
	* Returns the create date of this learning activity try.
	*
	* @return the create date of this learning activity try
	*/
	@Override
	public Date getCreateDate() {
		return _learningActivityTry.getCreateDate();
	}

	/**
	* Returns the end date of this learning activity try.
	*
	* @return the end date of this learning activity try
	*/
	@Override
	public Date getEndDate() {
		return _learningActivityTry.getEndDate();
	}

	/**
	* Returns the end user date of this learning activity try.
	*
	* @return the end user date of this learning activity try
	*/
	@Override
	public Date getEndUserDate() {
		return _learningActivityTry.getEndUserDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _learningActivityTry.getExpandoBridge();
	}

	/**
	* Returns the group ID of this learning activity try.
	*
	* @return the group ID of this learning activity try
	*/
	@Override
	public long getGroupId() {
		return _learningActivityTry.getGroupId();
	}

	/**
	* Returns the lat ID of this learning activity try.
	*
	* @return the lat ID of this learning activity try
	*/
	@Override
	public long getLatId() {
		return _learningActivityTry.getLatId();
	}

	/**
	* Returns the modified date of this learning activity try.
	*
	* @return the modified date of this learning activity try
	*/
	@Override
	public Date getModifiedDate() {
		return _learningActivityTry.getModifiedDate();
	}

	/**
	* Returns the primary key of this learning activity try.
	*
	* @return the primary key of this learning activity try
	*/
	@Override
	public long getPrimaryKey() {
		return _learningActivityTry.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _learningActivityTry.getPrimaryKeyObj();
	}

	/**
	* Returns the result of this learning activity try.
	*
	* @return the result of this learning activity try
	*/
	@Override
	public double getResult() {
		return _learningActivityTry.getResult();
	}

	/**
	* Returns the start date of this learning activity try.
	*
	* @return the start date of this learning activity try
	*/
	@Override
	public Date getStartDate() {
		return _learningActivityTry.getStartDate();
	}

	/**
	* Returns the try result data of this learning activity try.
	*
	* @return the try result data of this learning activity try
	*/
	@Override
	public String getTryResultData() {
		return _learningActivityTry.getTryResultData();
	}

	/**
	* Returns the user ID of this learning activity try.
	*
	* @return the user ID of this learning activity try
	*/
	@Override
	public long getUserId() {
		return _learningActivityTry.getUserId();
	}

	/**
	* Returns the user modified ID of this learning activity try.
	*
	* @return the user modified ID of this learning activity try
	*/
	@Override
	public long getUserModifiedId() {
		return _learningActivityTry.getUserModifiedId();
	}

	/**
	* Returns the user modified name of this learning activity try.
	*
	* @return the user modified name of this learning activity try
	*/
	@Override
	public String getUserModifiedName() {
		return _learningActivityTry.getUserModifiedName();
	}

	/**
	* Returns the user uuid of this learning activity try.
	*
	* @return the user uuid of this learning activity try
	*/
	@Override
	public String getUserUuid() {
		return _learningActivityTry.getUserUuid();
	}

	/**
	* Returns the uuid of this learning activity try.
	*
	* @return the uuid of this learning activity try
	*/
	@Override
	public String getUuid() {
		return _learningActivityTry.getUuid();
	}

	@Override
	public int hashCode() {
		return _learningActivityTry.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _learningActivityTry.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _learningActivityTry.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _learningActivityTry.isNew();
	}

	@Override
	public void persist() {
		_learningActivityTry.persist();
	}

	/**
	* Sets the act ID of this learning activity try.
	*
	* @param actId the act ID of this learning activity try
	*/
	@Override
	public void setActId(long actId) {
		_learningActivityTry.setActId(actId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_learningActivityTry.setCachedModel(cachedModel);
	}

	/**
	* Sets the comments of this learning activity try.
	*
	* @param comments the comments of this learning activity try
	*/
	@Override
	public void setComments(String comments) {
		_learningActivityTry.setComments(comments);
	}

	/**
	* Sets the company ID of this learning activity try.
	*
	* @param companyId the company ID of this learning activity try
	*/
	@Override
	public void setCompanyId(long companyId) {
		_learningActivityTry.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this learning activity try.
	*
	* @param createDate the create date of this learning activity try
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_learningActivityTry.setCreateDate(createDate);
	}

	/**
	* Sets the end date of this learning activity try.
	*
	* @param endDate the end date of this learning activity try
	*/
	@Override
	public void setEndDate(Date endDate) {
		_learningActivityTry.setEndDate(endDate);
	}

	/**
	* Sets the end user date of this learning activity try.
	*
	* @param endUserDate the end user date of this learning activity try
	*/
	@Override
	public void setEndUserDate(Date endUserDate) {
		_learningActivityTry.setEndUserDate(endUserDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_learningActivityTry.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_learningActivityTry.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_learningActivityTry.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this learning activity try.
	*
	* @param groupId the group ID of this learning activity try
	*/
	@Override
	public void setGroupId(long groupId) {
		_learningActivityTry.setGroupId(groupId);
	}

	/**
	* Sets the lat ID of this learning activity try.
	*
	* @param latId the lat ID of this learning activity try
	*/
	@Override
	public void setLatId(long latId) {
		_learningActivityTry.setLatId(latId);
	}

	/**
	* Sets the modified date of this learning activity try.
	*
	* @param modifiedDate the modified date of this learning activity try
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_learningActivityTry.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_learningActivityTry.setNew(n);
	}

	/**
	* Sets the primary key of this learning activity try.
	*
	* @param primaryKey the primary key of this learning activity try
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_learningActivityTry.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_learningActivityTry.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the result of this learning activity try.
	*
	* @param result the result of this learning activity try
	*/
	@Override
	public void setResult(double result) {
		_learningActivityTry.setResult(result);
	}

	/**
	* Sets the start date of this learning activity try.
	*
	* @param startDate the start date of this learning activity try
	*/
	@Override
	public void setStartDate(Date startDate) {
		_learningActivityTry.setStartDate(startDate);
	}

	/**
	* Sets the try result data of this learning activity try.
	*
	* @param tryResultData the try result data of this learning activity try
	*/
	@Override
	public void setTryResultData(String tryResultData) {
		_learningActivityTry.setTryResultData(tryResultData);
	}

	/**
	* Sets the user ID of this learning activity try.
	*
	* @param userId the user ID of this learning activity try
	*/
	@Override
	public void setUserId(long userId) {
		_learningActivityTry.setUserId(userId);
	}

	/**
	* Sets the user modified ID of this learning activity try.
	*
	* @param userModifiedId the user modified ID of this learning activity try
	*/
	@Override
	public void setUserModifiedId(long userModifiedId) {
		_learningActivityTry.setUserModifiedId(userModifiedId);
	}

	/**
	* Sets the user modified name of this learning activity try.
	*
	* @param userModifiedName the user modified name of this learning activity try
	*/
	@Override
	public void setUserModifiedName(String userModifiedName) {
		_learningActivityTry.setUserModifiedName(userModifiedName);
	}

	/**
	* Sets the user uuid of this learning activity try.
	*
	* @param userUuid the user uuid of this learning activity try
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_learningActivityTry.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this learning activity try.
	*
	* @param uuid the uuid of this learning activity try
	*/
	@Override
	public void setUuid(String uuid) {
		_learningActivityTry.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<LearningActivityTry> toCacheModel() {
		return _learningActivityTry.toCacheModel();
	}

	@Override
	public LearningActivityTry toEscapedModel() {
		return new LearningActivityTryWrapper(_learningActivityTry.toEscapedModel());
	}

	@Override
	public String toString() {
		return _learningActivityTry.toString();
	}

	@Override
	public LearningActivityTry toUnescapedModel() {
		return new LearningActivityTryWrapper(_learningActivityTry.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _learningActivityTry.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LearningActivityTryWrapper)) {
			return false;
		}

		LearningActivityTryWrapper learningActivityTryWrapper = (LearningActivityTryWrapper)obj;

		if (Objects.equals(_learningActivityTry,
					learningActivityTryWrapper._learningActivityTry)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _learningActivityTry.getStagedModelType();
	}

	@Override
	public LearningActivityTry getWrappedModel() {
		return _learningActivityTry;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _learningActivityTry.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _learningActivityTry.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_learningActivityTry.resetOriginalValues();
	}

	private final LearningActivityTry _learningActivityTry;
}