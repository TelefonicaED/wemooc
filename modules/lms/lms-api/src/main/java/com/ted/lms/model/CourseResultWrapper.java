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
 * This class is a wrapper for {@link CourseResult}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CourseResult
 * @generated
 */
@ProviderType
public class CourseResultWrapper implements CourseResult,
	ModelWrapper<CourseResult> {
	public CourseResultWrapper(CourseResult courseResult) {
		_courseResult = courseResult;
	}

	@Override
	public Class<?> getModelClass() {
		return CourseResult.class;
	}

	@Override
	public String getModelClassName() {
		return CourseResult.class.getName();
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

	@Override
	public Object clone() {
		return new CourseResultWrapper((CourseResult)_courseResult.clone());
	}

	@Override
	public int compareTo(CourseResult courseResult) {
		return _courseResult.compareTo(courseResult);
	}

	/**
	* Returns the allow end date of this course result.
	*
	* @return the allow end date of this course result
	*/
	@Override
	public Date getAllowEndDate() {
		return _courseResult.getAllowEndDate();
	}

	/**
	* Returns the allow start date of this course result.
	*
	* @return the allow start date of this course result
	*/
	@Override
	public Date getAllowStartDate() {
		return _courseResult.getAllowStartDate();
	}

	/**
	* Returns the comments of this course result.
	*
	* @return the comments of this course result
	*/
	@Override
	public String getComments() {
		return _courseResult.getComments();
	}

	/**
	* Returns the company ID of this course result.
	*
	* @return the company ID of this course result
	*/
	@Override
	public long getCompanyId() {
		return _courseResult.getCompanyId();
	}

	@Override
	public Course getCourse() {
		return _courseResult.getCourse();
	}

	/**
	* Returns the course ID of this course result.
	*
	* @return the course ID of this course result
	*/
	@Override
	public long getCourseId() {
		return _courseResult.getCourseId();
	}

	/**
	* Returns the create date of this course result.
	*
	* @return the create date of this course result
	*/
	@Override
	public Date getCreateDate() {
		return _courseResult.getCreateDate();
	}

	/**
	* Returns the cr ID of this course result.
	*
	* @return the cr ID of this course result
	*/
	@Override
	public long getCrId() {
		return _courseResult.getCrId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _courseResult.getExpandoBridge();
	}

	/**
	* Returns the extra data of this course result.
	*
	* @return the extra data of this course result
	*/
	@Override
	public String getExtraData() {
		return _courseResult.getExtraData();
	}

	/**
	* Returns the group ID of this course result.
	*
	* @return the group ID of this course result
	*/
	@Override
	public long getGroupId() {
		return _courseResult.getGroupId();
	}

	/**
	* Returns the modified date of this course result.
	*
	* @return the modified date of this course result
	*/
	@Override
	public Date getModifiedDate() {
		return _courseResult.getModifiedDate();
	}

	/**
	* Returns the passed of this course result.
	*
	* @return the passed of this course result
	*/
	@Override
	public boolean getPassed() {
		return _courseResult.getPassed();
	}

	/**
	* Returns the passed date of this course result.
	*
	* @return the passed date of this course result
	*/
	@Override
	public Date getPassedDate() {
		return _courseResult.getPassedDate();
	}

	/**
	* Returns the primary key of this course result.
	*
	* @return the primary key of this course result
	*/
	@Override
	public long getPrimaryKey() {
		return _courseResult.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _courseResult.getPrimaryKeyObj();
	}

	@Override
	public double getProgress() {
		return _courseResult.getProgress();
	}

	/**
	* Returns the registration date of this course result.
	*
	* @return the registration date of this course result
	*/
	@Override
	public Date getRegistrationDate() {
		return _courseResult.getRegistrationDate();
	}

	/**
	* Returns the result of this course result.
	*
	* @return the result of this course result
	*/
	@Override
	public double getResult() {
		return _courseResult.getResult();
	}

	/**
	* Returns the start date of this course result.
	*
	* @return the start date of this course result
	*/
	@Override
	public Date getStartDate() {
		return _courseResult.getStartDate();
	}

	/**
	* Returns the user ID of this course result.
	*
	* @return the user ID of this course result
	*/
	@Override
	public long getUserId() {
		return _courseResult.getUserId();
	}

	/**
	* Returns the user modified ID of this course result.
	*
	* @return the user modified ID of this course result
	*/
	@Override
	public long getUserModifiedId() {
		return _courseResult.getUserModifiedId();
	}

	/**
	* Returns the user modified name of this course result.
	*
	* @return the user modified name of this course result
	*/
	@Override
	public String getUserModifiedName() {
		return _courseResult.getUserModifiedName();
	}

	/**
	* Returns the user uuid of this course result.
	*
	* @return the user uuid of this course result
	*/
	@Override
	public String getUserUuid() {
		return _courseResult.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _courseResult.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _courseResult.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _courseResult.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _courseResult.isNew();
	}

	/**
	* Returns <code>true</code> if this course result is passed.
	*
	* @return <code>true</code> if this course result is passed; <code>false</code> otherwise
	*/
	@Override
	public boolean isPassed() {
		return _courseResult.isPassed();
	}

	@Override
	public void persist() {
		_courseResult.persist();
	}

	/**
	* Sets the allow end date of this course result.
	*
	* @param allowEndDate the allow end date of this course result
	*/
	@Override
	public void setAllowEndDate(Date allowEndDate) {
		_courseResult.setAllowEndDate(allowEndDate);
	}

	/**
	* Sets the allow start date of this course result.
	*
	* @param allowStartDate the allow start date of this course result
	*/
	@Override
	public void setAllowStartDate(Date allowStartDate) {
		_courseResult.setAllowStartDate(allowStartDate);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_courseResult.setCachedModel(cachedModel);
	}

	/**
	* Sets the comments of this course result.
	*
	* @param comments the comments of this course result
	*/
	@Override
	public void setComments(String comments) {
		_courseResult.setComments(comments);
	}

	/**
	* Sets the company ID of this course result.
	*
	* @param companyId the company ID of this course result
	*/
	@Override
	public void setCompanyId(long companyId) {
		_courseResult.setCompanyId(companyId);
	}

	/**
	* Sets the course ID of this course result.
	*
	* @param courseId the course ID of this course result
	*/
	@Override
	public void setCourseId(long courseId) {
		_courseResult.setCourseId(courseId);
	}

	/**
	* Sets the create date of this course result.
	*
	* @param createDate the create date of this course result
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_courseResult.setCreateDate(createDate);
	}

	/**
	* Sets the cr ID of this course result.
	*
	* @param crId the cr ID of this course result
	*/
	@Override
	public void setCrId(long crId) {
		_courseResult.setCrId(crId);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_courseResult.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_courseResult.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_courseResult.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the extra data of this course result.
	*
	* @param extraData the extra data of this course result
	*/
	@Override
	public void setExtraData(String extraData) {
		_courseResult.setExtraData(extraData);
	}

	/**
	* Sets the group ID of this course result.
	*
	* @param groupId the group ID of this course result
	*/
	@Override
	public void setGroupId(long groupId) {
		_courseResult.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this course result.
	*
	* @param modifiedDate the modified date of this course result
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_courseResult.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_courseResult.setNew(n);
	}

	/**
	* Sets whether this course result is passed.
	*
	* @param passed the passed of this course result
	*/
	@Override
	public void setPassed(boolean passed) {
		_courseResult.setPassed(passed);
	}

	/**
	* Sets the passed date of this course result.
	*
	* @param passedDate the passed date of this course result
	*/
	@Override
	public void setPassedDate(Date passedDate) {
		_courseResult.setPassedDate(passedDate);
	}

	/**
	* Sets the primary key of this course result.
	*
	* @param primaryKey the primary key of this course result
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_courseResult.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_courseResult.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the registration date of this course result.
	*
	* @param registrationDate the registration date of this course result
	*/
	@Override
	public void setRegistrationDate(Date registrationDate) {
		_courseResult.setRegistrationDate(registrationDate);
	}

	/**
	* Sets the result of this course result.
	*
	* @param result the result of this course result
	*/
	@Override
	public void setResult(double result) {
		_courseResult.setResult(result);
	}

	/**
	* Sets the start date of this course result.
	*
	* @param startDate the start date of this course result
	*/
	@Override
	public void setStartDate(Date startDate) {
		_courseResult.setStartDate(startDate);
	}

	/**
	* Sets the user ID of this course result.
	*
	* @param userId the user ID of this course result
	*/
	@Override
	public void setUserId(long userId) {
		_courseResult.setUserId(userId);
	}

	/**
	* Sets the user modified ID of this course result.
	*
	* @param userModifiedId the user modified ID of this course result
	*/
	@Override
	public void setUserModifiedId(long userModifiedId) {
		_courseResult.setUserModifiedId(userModifiedId);
	}

	/**
	* Sets the user modified name of this course result.
	*
	* @param userModifiedName the user modified name of this course result
	*/
	@Override
	public void setUserModifiedName(String userModifiedName) {
		_courseResult.setUserModifiedName(userModifiedName);
	}

	/**
	* Sets the user uuid of this course result.
	*
	* @param userUuid the user uuid of this course result
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_courseResult.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<CourseResult> toCacheModel() {
		return _courseResult.toCacheModel();
	}

	@Override
	public CourseResult toEscapedModel() {
		return new CourseResultWrapper(_courseResult.toEscapedModel());
	}

	@Override
	public String toString() {
		return _courseResult.toString();
	}

	@Override
	public CourseResult toUnescapedModel() {
		return new CourseResultWrapper(_courseResult.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _courseResult.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CourseResultWrapper)) {
			return false;
		}

		CourseResultWrapper courseResultWrapper = (CourseResultWrapper)obj;

		if (Objects.equals(_courseResult, courseResultWrapper._courseResult)) {
			return true;
		}

		return false;
	}

	@Override
	public CourseResult getWrappedModel() {
		return _courseResult;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _courseResult.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _courseResult.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_courseResult.resetOriginalValues();
	}

	private final CourseResult _courseResult;
}