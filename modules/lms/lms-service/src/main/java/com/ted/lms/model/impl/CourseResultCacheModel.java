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

package com.ted.lms.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.ted.lms.model.CourseResult;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The cache model class for representing CourseResult in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class CourseResultCacheModel
	implements CacheModel<CourseResult>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CourseResultCacheModel)) {
			return false;
		}

		CourseResultCacheModel courseResultCacheModel =
			(CourseResultCacheModel)obj;

		if (crId == courseResultCacheModel.crId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, crId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(37);

		sb.append("{crId=");
		sb.append(crId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userModifiedId=");
		sb.append(userModifiedId);
		sb.append(", userModifiedName=");
		sb.append(userModifiedName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", courseId=");
		sb.append(courseId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", result=");
		sb.append(result);
		sb.append(", comments=");
		sb.append(comments);
		sb.append(", passed=");
		sb.append(passed);
		sb.append(", registrationDate=");
		sb.append(registrationDate);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", passedDate=");
		sb.append(passedDate);
		sb.append(", allowStartDate=");
		sb.append(allowStartDate);
		sb.append(", allowEndDate=");
		sb.append(allowEndDate);
		sb.append(", extraData=");
		sb.append(extraData);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CourseResult toEntityModel() {
		CourseResultImpl courseResultImpl = new CourseResultImpl();

		courseResultImpl.setCrId(crId);
		courseResultImpl.setGroupId(groupId);
		courseResultImpl.setCompanyId(companyId);
		courseResultImpl.setUserModifiedId(userModifiedId);

		if (userModifiedName == null) {
			courseResultImpl.setUserModifiedName("");
		}
		else {
			courseResultImpl.setUserModifiedName(userModifiedName);
		}

		if (createDate == Long.MIN_VALUE) {
			courseResultImpl.setCreateDate(null);
		}
		else {
			courseResultImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			courseResultImpl.setModifiedDate(null);
		}
		else {
			courseResultImpl.setModifiedDate(new Date(modifiedDate));
		}

		courseResultImpl.setCourseId(courseId);
		courseResultImpl.setUserId(userId);
		courseResultImpl.setResult(result);

		if (comments == null) {
			courseResultImpl.setComments("");
		}
		else {
			courseResultImpl.setComments(comments);
		}

		courseResultImpl.setPassed(passed);

		if (registrationDate == Long.MIN_VALUE) {
			courseResultImpl.setRegistrationDate(null);
		}
		else {
			courseResultImpl.setRegistrationDate(new Date(registrationDate));
		}

		if (startDate == Long.MIN_VALUE) {
			courseResultImpl.setStartDate(null);
		}
		else {
			courseResultImpl.setStartDate(new Date(startDate));
		}

		if (passedDate == Long.MIN_VALUE) {
			courseResultImpl.setPassedDate(null);
		}
		else {
			courseResultImpl.setPassedDate(new Date(passedDate));
		}

		if (allowStartDate == Long.MIN_VALUE) {
			courseResultImpl.setAllowStartDate(null);
		}
		else {
			courseResultImpl.setAllowStartDate(new Date(allowStartDate));
		}

		if (allowEndDate == Long.MIN_VALUE) {
			courseResultImpl.setAllowEndDate(null);
		}
		else {
			courseResultImpl.setAllowEndDate(new Date(allowEndDate));
		}

		if (extraData == null) {
			courseResultImpl.setExtraData("");
		}
		else {
			courseResultImpl.setExtraData(extraData);
		}

		courseResultImpl.resetOriginalValues();

		return courseResultImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		crId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userModifiedId = objectInput.readLong();
		userModifiedName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		courseId = objectInput.readLong();

		userId = objectInput.readLong();

		result = objectInput.readDouble();
		comments = objectInput.readUTF();

		passed = objectInput.readBoolean();
		registrationDate = objectInput.readLong();
		startDate = objectInput.readLong();
		passedDate = objectInput.readLong();
		allowStartDate = objectInput.readLong();
		allowEndDate = objectInput.readLong();
		extraData = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(crId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userModifiedId);

		if (userModifiedName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userModifiedName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(courseId);

		objectOutput.writeLong(userId);

		objectOutput.writeDouble(result);

		if (comments == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(comments);
		}

		objectOutput.writeBoolean(passed);
		objectOutput.writeLong(registrationDate);
		objectOutput.writeLong(startDate);
		objectOutput.writeLong(passedDate);
		objectOutput.writeLong(allowStartDate);
		objectOutput.writeLong(allowEndDate);

		if (extraData == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(extraData);
		}
	}

	public long crId;
	public long groupId;
	public long companyId;
	public long userModifiedId;
	public String userModifiedName;
	public long createDate;
	public long modifiedDate;
	public long courseId;
	public long userId;
	public double result;
	public String comments;
	public boolean passed;
	public long registrationDate;
	public long startDate;
	public long passedDate;
	public long allowStartDate;
	public long allowEndDate;
	public String extraData;

}