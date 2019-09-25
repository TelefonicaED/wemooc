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
 * This class is a wrapper for {@link LearningActivityResult}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LearningActivityResult
 * @generated
 */
@ProviderType
public class LearningActivityResultWrapper
	extends BaseModelWrapper<LearningActivityResult>
	implements LearningActivityResult, ModelWrapper<LearningActivityResult> {

	public LearningActivityResultWrapper(
		LearningActivityResult learningActivityResult) {

		super(learningActivityResult);
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

	/**
	 * Returns the act ID of this learning activity result.
	 *
	 * @return the act ID of this learning activity result
	 */
	@Override
	public long getActId() {
		return model.getActId();
	}

	@Override
	public String getActivityStatus(java.util.Locale locale) {
		return model.getActivityStatus(locale);
	}

	/**
	 * Returns the comments of this learning activity result.
	 *
	 * @return the comments of this learning activity result
	 */
	@Override
	public String getComments() {
		return model.getComments();
	}

	/**
	 * Returns the company ID of this learning activity result.
	 *
	 * @return the company ID of this learning activity result
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this learning activity result.
	 *
	 * @return the create date of this learning activity result
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the end date of this learning activity result.
	 *
	 * @return the end date of this learning activity result
	 */
	@Override
	public Date getEndDate() {
		return model.getEndDate();
	}

	/**
	 * Returns the extra data of this learning activity result.
	 *
	 * @return the extra data of this learning activity result
	 */
	@Override
	public String getExtraData() {
		return model.getExtraData();
	}

	/**
	 * Returns the group ID of this learning activity result.
	 *
	 * @return the group ID of this learning activity result
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the lar ID of this learning activity result.
	 *
	 * @return the lar ID of this learning activity result
	 */
	@Override
	public long getLarId() {
		return model.getLarId();
	}

	/**
	 * Returns the modified date of this learning activity result.
	 *
	 * @return the modified date of this learning activity result
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the passed of this learning activity result.
	 *
	 * @return the passed of this learning activity result
	 */
	@Override
	public boolean getPassed() {
		return model.getPassed();
	}

	/**
	 * Returns the primary key of this learning activity result.
	 *
	 * @return the primary key of this learning activity result
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the result of this learning activity result.
	 *
	 * @return the result of this learning activity result
	 */
	@Override
	public double getResult() {
		return model.getResult();
	}

	/**
	 * Returns the start date of this learning activity result.
	 *
	 * @return the start date of this learning activity result
	 */
	@Override
	public Date getStartDate() {
		return model.getStartDate();
	}

	@Override
	public String getStatusProperties() {
		return model.getStatusProperties();
	}

	/**
	 * Returns the user ID of this learning activity result.
	 *
	 * @return the user ID of this learning activity result
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user modified ID of this learning activity result.
	 *
	 * @return the user modified ID of this learning activity result
	 */
	@Override
	public long getUserModifiedId() {
		return model.getUserModifiedId();
	}

	/**
	 * Returns the user modified name of this learning activity result.
	 *
	 * @return the user modified name of this learning activity result
	 */
	@Override
	public String getUserModifiedName() {
		return model.getUserModifiedName();
	}

	/**
	 * Returns the user uuid of this learning activity result.
	 *
	 * @return the user uuid of this learning activity result
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the uuid of this learning activity result.
	 *
	 * @return the uuid of this learning activity result
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	@Override
	public boolean isFailed() {
		return model.isFailed();
	}

	@Override
	public boolean isFinished() {
		return model.isFinished();
	}

	/**
	 * Returns <code>true</code> if this learning activity result is passed.
	 *
	 * @return <code>true</code> if this learning activity result is passed; <code>false</code> otherwise
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
	 * Sets the act ID of this learning activity result.
	 *
	 * @param actId the act ID of this learning activity result
	 */
	@Override
	public void setActId(long actId) {
		model.setActId(actId);
	}

	/**
	 * Sets the comments of this learning activity result.
	 *
	 * @param comments the comments of this learning activity result
	 */
	@Override
	public void setComments(String comments) {
		model.setComments(comments);
	}

	/**
	 * Sets the company ID of this learning activity result.
	 *
	 * @param companyId the company ID of this learning activity result
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this learning activity result.
	 *
	 * @param createDate the create date of this learning activity result
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the end date of this learning activity result.
	 *
	 * @param endDate the end date of this learning activity result
	 */
	@Override
	public void setEndDate(Date endDate) {
		model.setEndDate(endDate);
	}

	/**
	 * Sets the extra data of this learning activity result.
	 *
	 * @param extraData the extra data of this learning activity result
	 */
	@Override
	public void setExtraData(String extraData) {
		model.setExtraData(extraData);
	}

	/**
	 * Sets the group ID of this learning activity result.
	 *
	 * @param groupId the group ID of this learning activity result
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the lar ID of this learning activity result.
	 *
	 * @param larId the lar ID of this learning activity result
	 */
	@Override
	public void setLarId(long larId) {
		model.setLarId(larId);
	}

	/**
	 * Sets the modified date of this learning activity result.
	 *
	 * @param modifiedDate the modified date of this learning activity result
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets whether this learning activity result is passed.
	 *
	 * @param passed the passed of this learning activity result
	 */
	@Override
	public void setPassed(boolean passed) {
		model.setPassed(passed);
	}

	/**
	 * Sets the primary key of this learning activity result.
	 *
	 * @param primaryKey the primary key of this learning activity result
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the result of this learning activity result.
	 *
	 * @param result the result of this learning activity result
	 */
	@Override
	public void setResult(double result) {
		model.setResult(result);
	}

	/**
	 * Sets the start date of this learning activity result.
	 *
	 * @param startDate the start date of this learning activity result
	 */
	@Override
	public void setStartDate(Date startDate) {
		model.setStartDate(startDate);
	}

	/**
	 * Sets the user ID of this learning activity result.
	 *
	 * @param userId the user ID of this learning activity result
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user modified ID of this learning activity result.
	 *
	 * @param userModifiedId the user modified ID of this learning activity result
	 */
	@Override
	public void setUserModifiedId(long userModifiedId) {
		model.setUserModifiedId(userModifiedId);
	}

	/**
	 * Sets the user modified name of this learning activity result.
	 *
	 * @param userModifiedName the user modified name of this learning activity result
	 */
	@Override
	public void setUserModifiedName(String userModifiedName) {
		model.setUserModifiedName(userModifiedName);
	}

	/**
	 * Sets the user uuid of this learning activity result.
	 *
	 * @param userUuid the user uuid of this learning activity result
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this learning activity result.
	 *
	 * @param uuid the uuid of this learning activity result
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
	protected LearningActivityResultWrapper wrap(
		LearningActivityResult learningActivityResult) {

		return new LearningActivityResultWrapper(learningActivityResult);
	}

}