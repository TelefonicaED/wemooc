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

import com.ted.lms.model.CourseType;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The cache model class for representing CourseType in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class CourseTypeCacheModel
	implements CacheModel<CourseType>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CourseTypeCacheModel)) {
			return false;
		}

		CourseTypeCacheModel courseTypeCacheModel = (CourseTypeCacheModel)obj;

		if (courseTypeId == courseTypeCacheModel.courseTypeId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, courseTypeId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{courseTypeId=");
		sb.append(courseTypeId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", iconId=");
		sb.append(iconId);
		sb.append(", lastPublishDate=");
		sb.append(lastPublishDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CourseType toEntityModel() {
		CourseTypeImpl courseTypeImpl = new CourseTypeImpl();

		courseTypeImpl.setCourseTypeId(courseTypeId);
		courseTypeImpl.setGroupId(groupId);
		courseTypeImpl.setCompanyId(companyId);
		courseTypeImpl.setUserId(userId);

		if (userName == null) {
			courseTypeImpl.setUserName("");
		}
		else {
			courseTypeImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			courseTypeImpl.setCreateDate(null);
		}
		else {
			courseTypeImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			courseTypeImpl.setModifiedDate(null);
		}
		else {
			courseTypeImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			courseTypeImpl.setName("");
		}
		else {
			courseTypeImpl.setName(name);
		}

		if (description == null) {
			courseTypeImpl.setDescription("");
		}
		else {
			courseTypeImpl.setDescription(description);
		}

		courseTypeImpl.setIconId(iconId);

		if (lastPublishDate == Long.MIN_VALUE) {
			courseTypeImpl.setLastPublishDate(null);
		}
		else {
			courseTypeImpl.setLastPublishDate(new Date(lastPublishDate));
		}

		courseTypeImpl.resetOriginalValues();

		return courseTypeImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		courseTypeId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		name = objectInput.readUTF();
		description = objectInput.readUTF();

		iconId = objectInput.readLong();
		lastPublishDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(courseTypeId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}

		objectOutput.writeLong(iconId);
		objectOutput.writeLong(lastPublishDate);
	}

	public long courseTypeId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String description;
	public long iconId;
	public long lastPublishDate;

}