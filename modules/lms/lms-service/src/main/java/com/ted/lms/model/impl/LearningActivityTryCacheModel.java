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

import com.ted.lms.model.LearningActivityTry;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The cache model class for representing LearningActivityTry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class LearningActivityTryCacheModel
	implements CacheModel<LearningActivityTry>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LearningActivityTryCacheModel)) {
			return false;
		}

		LearningActivityTryCacheModel learningActivityTryCacheModel =
			(LearningActivityTryCacheModel)obj;

		if (latId == learningActivityTryCacheModel.latId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, latId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(33);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", latId=");
		sb.append(latId);
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
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", endDate=");
		sb.append(endDate);
		sb.append(", endUserDate=");
		sb.append(endUserDate);
		sb.append(", tryResultData=");
		sb.append(tryResultData);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public LearningActivityTry toEntityModel() {
		LearningActivityTryImpl learningActivityTryImpl =
			new LearningActivityTryImpl();

		if (uuid == null) {
			learningActivityTryImpl.setUuid("");
		}
		else {
			learningActivityTryImpl.setUuid(uuid);
		}

		learningActivityTryImpl.setLatId(latId);
		learningActivityTryImpl.setGroupId(groupId);
		learningActivityTryImpl.setCompanyId(companyId);
		learningActivityTryImpl.setUserModifiedId(userModifiedId);

		if (userModifiedName == null) {
			learningActivityTryImpl.setUserModifiedName("");
		}
		else {
			learningActivityTryImpl.setUserModifiedName(userModifiedName);
		}

		if (createDate == Long.MIN_VALUE) {
			learningActivityTryImpl.setCreateDate(null);
		}
		else {
			learningActivityTryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			learningActivityTryImpl.setModifiedDate(null);
		}
		else {
			learningActivityTryImpl.setModifiedDate(new Date(modifiedDate));
		}

		learningActivityTryImpl.setActId(actId);
		learningActivityTryImpl.setUserId(userId);
		learningActivityTryImpl.setResult(result);

		if (comments == null) {
			learningActivityTryImpl.setComments("");
		}
		else {
			learningActivityTryImpl.setComments(comments);
		}

		if (startDate == Long.MIN_VALUE) {
			learningActivityTryImpl.setStartDate(null);
		}
		else {
			learningActivityTryImpl.setStartDate(new Date(startDate));
		}

		if (endDate == Long.MIN_VALUE) {
			learningActivityTryImpl.setEndDate(null);
		}
		else {
			learningActivityTryImpl.setEndDate(new Date(endDate));
		}

		if (endUserDate == Long.MIN_VALUE) {
			learningActivityTryImpl.setEndUserDate(null);
		}
		else {
			learningActivityTryImpl.setEndUserDate(new Date(endUserDate));
		}

		if (tryResultData == null) {
			learningActivityTryImpl.setTryResultData("");
		}
		else {
			learningActivityTryImpl.setTryResultData(tryResultData);
		}

		learningActivityTryImpl.resetOriginalValues();

		return learningActivityTryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		latId = objectInput.readLong();

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
		startDate = objectInput.readLong();
		endDate = objectInput.readLong();
		endUserDate = objectInput.readLong();
		tryResultData = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(latId);

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

		objectOutput.writeLong(startDate);
		objectOutput.writeLong(endDate);
		objectOutput.writeLong(endUserDate);

		if (tryResultData == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(tryResultData);
		}
	}

	public String uuid;
	public long latId;
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
	public long startDate;
	public long endDate;
	public long endUserDate;
	public String tryResultData;

}