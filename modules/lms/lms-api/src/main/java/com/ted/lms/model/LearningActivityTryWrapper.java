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
 * This class is a wrapper for {@link LearningActivityTry}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LearningActivityTry
 * @generated
 */
@ProviderType
public class LearningActivityTryWrapper
	extends BaseModelWrapper<LearningActivityTry>
	implements LearningActivityTry, ModelWrapper<LearningActivityTry> {

	public LearningActivityTryWrapper(LearningActivityTry learningActivityTry) {
		super(learningActivityTry);
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

	/**
	 * Returns the act ID of this learning activity try.
	 *
	 * @return the act ID of this learning activity try
	 */
	@Override
	public long getActId() {
		return model.getActId();
	}

	/**
	 * Returns the comments of this learning activity try.
	 *
	 * @return the comments of this learning activity try
	 */
	@Override
	public String getComments() {
		return model.getComments();
	}

	/**
	 * Returns the company ID of this learning activity try.
	 *
	 * @return the company ID of this learning activity try
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this learning activity try.
	 *
	 * @return the create date of this learning activity try
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the end date of this learning activity try.
	 *
	 * @return the end date of this learning activity try
	 */
	@Override
	public Date getEndDate() {
		return model.getEndDate();
	}

	/**
	 * Returns the end user date of this learning activity try.
	 *
	 * @return the end user date of this learning activity try
	 */
	@Override
	public Date getEndUserDate() {
		return model.getEndUserDate();
	}

	/**
	 * Returns the group ID of this learning activity try.
	 *
	 * @return the group ID of this learning activity try
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the lat ID of this learning activity try.
	 *
	 * @return the lat ID of this learning activity try
	 */
	@Override
	public long getLatId() {
		return model.getLatId();
	}

	/**
	 * Returns the modified date of this learning activity try.
	 *
	 * @return the modified date of this learning activity try
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the primary key of this learning activity try.
	 *
	 * @return the primary key of this learning activity try
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the result of this learning activity try.
	 *
	 * @return the result of this learning activity try
	 */
	@Override
	public double getResult() {
		return model.getResult();
	}

	/**
	 * Returns the start date of this learning activity try.
	 *
	 * @return the start date of this learning activity try
	 */
	@Override
	public Date getStartDate() {
		return model.getStartDate();
	}

	/**
	 * Returns the try result data of this learning activity try.
	 *
	 * @return the try result data of this learning activity try
	 */
	@Override
	public String getTryResultData() {
		return model.getTryResultData();
	}

	/**
	 * Returns the user ID of this learning activity try.
	 *
	 * @return the user ID of this learning activity try
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user modified ID of this learning activity try.
	 *
	 * @return the user modified ID of this learning activity try
	 */
	@Override
	public long getUserModifiedId() {
		return model.getUserModifiedId();
	}

	/**
	 * Returns the user modified name of this learning activity try.
	 *
	 * @return the user modified name of this learning activity try
	 */
	@Override
	public String getUserModifiedName() {
		return model.getUserModifiedName();
	}

	/**
	 * Returns the user uuid of this learning activity try.
	 *
	 * @return the user uuid of this learning activity try
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the uuid of this learning activity try.
	 *
	 * @return the uuid of this learning activity try
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the act ID of this learning activity try.
	 *
	 * @param actId the act ID of this learning activity try
	 */
	@Override
	public void setActId(long actId) {
		model.setActId(actId);
	}

	/**
	 * Sets the comments of this learning activity try.
	 *
	 * @param comments the comments of this learning activity try
	 */
	@Override
	public void setComments(String comments) {
		model.setComments(comments);
	}

	/**
	 * Sets the company ID of this learning activity try.
	 *
	 * @param companyId the company ID of this learning activity try
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this learning activity try.
	 *
	 * @param createDate the create date of this learning activity try
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the end date of this learning activity try.
	 *
	 * @param endDate the end date of this learning activity try
	 */
	@Override
	public void setEndDate(Date endDate) {
		model.setEndDate(endDate);
	}

	/**
	 * Sets the end user date of this learning activity try.
	 *
	 * @param endUserDate the end user date of this learning activity try
	 */
	@Override
	public void setEndUserDate(Date endUserDate) {
		model.setEndUserDate(endUserDate);
	}

	/**
	 * Sets the group ID of this learning activity try.
	 *
	 * @param groupId the group ID of this learning activity try
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the lat ID of this learning activity try.
	 *
	 * @param latId the lat ID of this learning activity try
	 */
	@Override
	public void setLatId(long latId) {
		model.setLatId(latId);
	}

	/**
	 * Sets the modified date of this learning activity try.
	 *
	 * @param modifiedDate the modified date of this learning activity try
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the primary key of this learning activity try.
	 *
	 * @param primaryKey the primary key of this learning activity try
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the result of this learning activity try.
	 *
	 * @param result the result of this learning activity try
	 */
	@Override
	public void setResult(double result) {
		model.setResult(result);
	}

	/**
	 * Sets the start date of this learning activity try.
	 *
	 * @param startDate the start date of this learning activity try
	 */
	@Override
	public void setStartDate(Date startDate) {
		model.setStartDate(startDate);
	}

	/**
	 * Sets the try result data of this learning activity try.
	 *
	 * @param tryResultData the try result data of this learning activity try
	 */
	@Override
	public void setTryResultData(String tryResultData) {
		model.setTryResultData(tryResultData);
	}

	/**
	 * Sets the user ID of this learning activity try.
	 *
	 * @param userId the user ID of this learning activity try
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user modified ID of this learning activity try.
	 *
	 * @param userModifiedId the user modified ID of this learning activity try
	 */
	@Override
	public void setUserModifiedId(long userModifiedId) {
		model.setUserModifiedId(userModifiedId);
	}

	/**
	 * Sets the user modified name of this learning activity try.
	 *
	 * @param userModifiedName the user modified name of this learning activity try
	 */
	@Override
	public void setUserModifiedName(String userModifiedName) {
		model.setUserModifiedName(userModifiedName);
	}

	/**
	 * Sets the user uuid of this learning activity try.
	 *
	 * @param userUuid the user uuid of this learning activity try
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this learning activity try.
	 *
	 * @param uuid the uuid of this learning activity try
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
	protected LearningActivityTryWrapper wrap(
		LearningActivityTry learningActivityTry) {

		return new LearningActivityTryWrapper(learningActivityTry);
	}

}