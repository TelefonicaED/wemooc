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
 * This class is a wrapper for {@link CourseResult}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CourseResult
 * @generated
 */
@ProviderType
public class CourseResultWrapper
	extends BaseModelWrapper<CourseResult>
	implements CourseResult, ModelWrapper<CourseResult> {

	public CourseResultWrapper(CourseResult courseResult) {
		super(courseResult);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("crId", getCrId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userModifiedId", getUserModifiedId());
		attributes.put("userModifiedName", getUserModifiedName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("courseId", getCourseId());
		attributes.put("userId", getUserId());
		attributes.put("result", getResult());
		attributes.put("comments", getComments());
		attributes.put("passed", isPassed());
		attributes.put("registrationDate", getRegistrationDate());
		attributes.put("startDate", getStartDate());
		attributes.put("passedDate", getPassedDate());
		attributes.put("allowStartDate", getAllowStartDate());
		attributes.put("allowEndDate", getAllowEndDate());
		attributes.put("extraData", getExtraData());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long crId = (Long)attributes.get("crId");

		if (crId != null) {
			setCrId(crId);
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

		Long courseId = (Long)attributes.get("courseId");

		if (courseId != null) {
			setCourseId(courseId);
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

		Date registrationDate = (Date)attributes.get("registrationDate");

		if (registrationDate != null) {
			setRegistrationDate(registrationDate);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Date passedDate = (Date)attributes.get("passedDate");

		if (passedDate != null) {
			setPassedDate(passedDate);
		}

		Date allowStartDate = (Date)attributes.get("allowStartDate");

		if (allowStartDate != null) {
			setAllowStartDate(allowStartDate);
		}

		Date allowEndDate = (Date)attributes.get("allowEndDate");

		if (allowEndDate != null) {
			setAllowEndDate(allowEndDate);
		}

		String extraData = (String)attributes.get("extraData");

		if (extraData != null) {
			setExtraData(extraData);
		}
	}

	/**
	 * Returns the allow end date of this course result.
	 *
	 * @return the allow end date of this course result
	 */
	@Override
	public Date getAllowEndDate() {
		return model.getAllowEndDate();
	}

	/**
	 * Returns the allow start date of this course result.
	 *
	 * @return the allow start date of this course result
	 */
	@Override
	public Date getAllowStartDate() {
		return model.getAllowStartDate();
	}

	/**
	 * Returns the comments of this course result.
	 *
	 * @return the comments of this course result
	 */
	@Override
	public String getComments() {
		return model.getComments();
	}

	/**
	 * Returns the company ID of this course result.
	 *
	 * @return the company ID of this course result
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	@Override
	public Course getCourse() {
		return model.getCourse();
	}

	/**
	 * Returns the course ID of this course result.
	 *
	 * @return the course ID of this course result
	 */
	@Override
	public long getCourseId() {
		return model.getCourseId();
	}

	@Override
	public String getCourseStatus(java.util.Locale locale) {
		return model.getCourseStatus(locale);
	}

	/**
	 * Returns the create date of this course result.
	 *
	 * @return the create date of this course result
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the cr ID of this course result.
	 *
	 * @return the cr ID of this course result
	 */
	@Override
	public long getCrId() {
		return model.getCrId();
	}

	/**
	 * Returns the extra data of this course result.
	 *
	 * @return the extra data of this course result
	 */
	@Override
	public String getExtraData() {
		return model.getExtraData();
	}

	/**
	 * Returns the group ID of this course result.
	 *
	 * @return the group ID of this course result
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the modified date of this course result.
	 *
	 * @return the modified date of this course result
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the passed of this course result.
	 *
	 * @return the passed of this course result
	 */
	@Override
	public boolean getPassed() {
		return model.getPassed();
	}

	/**
	 * Returns the passed date of this course result.
	 *
	 * @return the passed date of this course result
	 */
	@Override
	public Date getPassedDate() {
		return model.getPassedDate();
	}

	/**
	 * Returns the primary key of this course result.
	 *
	 * @return the primary key of this course result
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	@Override
	public double getProgress() {
		return model.getProgress();
	}

	/**
	 * Returns the registration date of this course result.
	 *
	 * @return the registration date of this course result
	 */
	@Override
	public Date getRegistrationDate() {
		return model.getRegistrationDate();
	}

	/**
	 * Returns the result of this course result.
	 *
	 * @return the result of this course result
	 */
	@Override
	public double getResult() {
		return model.getResult();
	}

	/**
	 * Returns the start date of this course result.
	 *
	 * @return the start date of this course result
	 */
	@Override
	public Date getStartDate() {
		return model.getStartDate();
	}

	/**
	 * Returns the user ID of this course result.
	 *
	 * @return the user ID of this course result
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user modified ID of this course result.
	 *
	 * @return the user modified ID of this course result
	 */
	@Override
	public long getUserModifiedId() {
		return model.getUserModifiedId();
	}

	/**
	 * Returns the user modified name of this course result.
	 *
	 * @return the user modified name of this course result
	 */
	@Override
	public String getUserModifiedName() {
		return model.getUserModifiedName();
	}

	/**
	 * Returns the user uuid of this course result.
	 *
	 * @return the user uuid of this course result
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
	 * Returns <code>true</code> if this course result is passed.
	 *
	 * @return <code>true</code> if this course result is passed; <code>false</code> otherwise
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
	 * Sets the allow end date of this course result.
	 *
	 * @param allowEndDate the allow end date of this course result
	 */
	@Override
	public void setAllowEndDate(Date allowEndDate) {
		model.setAllowEndDate(allowEndDate);
	}

	/**
	 * Sets the allow start date of this course result.
	 *
	 * @param allowStartDate the allow start date of this course result
	 */
	@Override
	public void setAllowStartDate(Date allowStartDate) {
		model.setAllowStartDate(allowStartDate);
	}

	/**
	 * Sets the comments of this course result.
	 *
	 * @param comments the comments of this course result
	 */
	@Override
	public void setComments(String comments) {
		model.setComments(comments);
	}

	/**
	 * Sets the company ID of this course result.
	 *
	 * @param companyId the company ID of this course result
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the course ID of this course result.
	 *
	 * @param courseId the course ID of this course result
	 */
	@Override
	public void setCourseId(long courseId) {
		model.setCourseId(courseId);
	}

	/**
	 * Sets the create date of this course result.
	 *
	 * @param createDate the create date of this course result
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the cr ID of this course result.
	 *
	 * @param crId the cr ID of this course result
	 */
	@Override
	public void setCrId(long crId) {
		model.setCrId(crId);
	}

	/**
	 * Sets the extra data of this course result.
	 *
	 * @param extraData the extra data of this course result
	 */
	@Override
	public void setExtraData(String extraData) {
		model.setExtraData(extraData);
	}

	/**
	 * Sets the group ID of this course result.
	 *
	 * @param groupId the group ID of this course result
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the modified date of this course result.
	 *
	 * @param modifiedDate the modified date of this course result
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets whether this course result is passed.
	 *
	 * @param passed the passed of this course result
	 */
	@Override
	public void setPassed(boolean passed) {
		model.setPassed(passed);
	}

	/**
	 * Sets the passed date of this course result.
	 *
	 * @param passedDate the passed date of this course result
	 */
	@Override
	public void setPassedDate(Date passedDate) {
		model.setPassedDate(passedDate);
	}

	/**
	 * Sets the primary key of this course result.
	 *
	 * @param primaryKey the primary key of this course result
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the registration date of this course result.
	 *
	 * @param registrationDate the registration date of this course result
	 */
	@Override
	public void setRegistrationDate(Date registrationDate) {
		model.setRegistrationDate(registrationDate);
	}

	/**
	 * Sets the result of this course result.
	 *
	 * @param result the result of this course result
	 */
	@Override
	public void setResult(double result) {
		model.setResult(result);
	}

	/**
	 * Sets the start date of this course result.
	 *
	 * @param startDate the start date of this course result
	 */
	@Override
	public void setStartDate(Date startDate) {
		model.setStartDate(startDate);
	}

	/**
	 * Sets the user ID of this course result.
	 *
	 * @param userId the user ID of this course result
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user modified ID of this course result.
	 *
	 * @param userModifiedId the user modified ID of this course result
	 */
	@Override
	public void setUserModifiedId(long userModifiedId) {
		model.setUserModifiedId(userModifiedId);
	}

	/**
	 * Sets the user modified name of this course result.
	 *
	 * @param userModifiedName the user modified name of this course result
	 */
	@Override
	public void setUserModifiedName(String userModifiedName) {
		model.setUserModifiedName(userModifiedName);
	}

	/**
	 * Sets the user uuid of this course result.
	 *
	 * @param userUuid the user uuid of this course result
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	@Override
	protected CourseResultWrapper wrap(CourseResult courseResult) {
		return new CourseResultWrapper(courseResult);
	}

}