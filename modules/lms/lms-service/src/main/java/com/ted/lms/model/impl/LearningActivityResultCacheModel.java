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

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import com.ted.lms.model.LearningActivityResult;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing LearningActivityResult in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see LearningActivityResult
 * @generated
 */
@ProviderType
public class LearningActivityResultCacheModel implements CacheModel<LearningActivityResult>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LearningActivityResultCacheModel)) {
			return false;
		}

		LearningActivityResultCacheModel learningActivityResultCacheModel = (LearningActivityResultCacheModel)obj;

		if (larId == learningActivityResultCacheModel.larId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, larId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(33);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", larId=");
		sb.append(larId);
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
		sb.append(", actId=");
		sb.append(actId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", result=");
		sb.append(result);
		sb.append(", comments=");
		sb.append(comments);
		sb.append(", passed=");
		sb.append(passed);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", endDate=");
		sb.append(endDate);
		sb.append(", extraData=");
		sb.append(extraData);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public LearningActivityResult toEntityModel() {
		LearningActivityResultImpl learningActivityResultImpl = new LearningActivityResultImpl();

		if (uuid == null) {
			learningActivityResultImpl.setUuid("");
		}
		else {
			learningActivityResultImpl.setUuid(uuid);
		}

		learningActivityResultImpl.setLarId(larId);
		learningActivityResultImpl.setGroupId(groupId);
		learningActivityResultImpl.setCompanyId(companyId);
		learningActivityResultImpl.setUserModifiedId(userModifiedId);

		if (userModifiedName == null) {
			learningActivityResultImpl.setUserModifiedName("");
		}
		else {
			learningActivityResultImpl.setUserModifiedName(userModifiedName);
		}

		if (createDate == Long.MIN_VALUE) {
			learningActivityResultImpl.setCreateDate(null);
		}
		else {
			learningActivityResultImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			learningActivityResultImpl.setModifiedDate(null);
		}
		else {
			learningActivityResultImpl.setModifiedDate(new Date(modifiedDate));
		}

		learningActivityResultImpl.setActId(actId);
		learningActivityResultImpl.setUserId(userId);
		learningActivityResultImpl.setResult(result);

		if (comments == null) {
			learningActivityResultImpl.setComments("");
		}
		else {
			learningActivityResultImpl.setComments(comments);
		}

		learningActivityResultImpl.setPassed(passed);

		if (startDate == Long.MIN_VALUE) {
			learningActivityResultImpl.setStartDate(null);
		}
		else {
			learningActivityResultImpl.setStartDate(new Date(startDate));
		}

		if (endDate == Long.MIN_VALUE) {
			learningActivityResultImpl.setEndDate(null);
		}
		else {
			learningActivityResultImpl.setEndDate(new Date(endDate));
		}

		if (extraData == null) {
			learningActivityResultImpl.setExtraData("");
		}
		else {
			learningActivityResultImpl.setExtraData(extraData);
		}

		learningActivityResultImpl.resetOriginalValues();

		return learningActivityResultImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		larId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userModifiedId = objectInput.readLong();
		userModifiedName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		actId = objectInput.readLong();

		userId = objectInput.readLong();

		result = objectInput.readDouble();
		comments = objectInput.readUTF();

		passed = objectInput.readBoolean();
		startDate = objectInput.readLong();
		endDate = objectInput.readLong();
		extraData = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(larId);

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

		objectOutput.writeLong(actId);

		objectOutput.writeLong(userId);

		objectOutput.writeDouble(result);

		if (comments == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(comments);
		}

		objectOutput.writeBoolean(passed);
		objectOutput.writeLong(startDate);
		objectOutput.writeLong(endDate);

		if (extraData == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(extraData);
		}
	}

	public String uuid;
	public long larId;
	public long groupId;
	public long companyId;
	public long userModifiedId;
	public String userModifiedName;
	public long createDate;
	public long modifiedDate;
	public long actId;
	public long userId;
	public double result;
	public String comments;
	public boolean passed;
	public long startDate;
	public long endDate;
	public String extraData;
}