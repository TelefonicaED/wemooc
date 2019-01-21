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

import com.liferay.petra.string.StringBundler;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;

import com.ted.lms.model.ModuleResult;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ModuleResult in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ModuleResult
 * @generated
 */
@ProviderType
public class ModuleResultCacheModel implements CacheModel<ModuleResult>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ModuleResultCacheModel)) {
			return false;
		}

		ModuleResultCacheModel moduleResultCacheModel = (ModuleResultCacheModel)obj;

		if (mrId == moduleResultCacheModel.mrId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, mrId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(31);

		sb.append("{mrId=");
		sb.append(mrId);
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
		sb.append(", moduleId=");
		sb.append(moduleId);
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
		sb.append(", passedDate=");
		sb.append(passedDate);
		sb.append(", extraData=");
		sb.append(extraData);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ModuleResult toEntityModel() {
		ModuleResultImpl moduleResultImpl = new ModuleResultImpl();

		moduleResultImpl.setMrId(mrId);
		moduleResultImpl.setGroupId(groupId);
		moduleResultImpl.setCompanyId(companyId);
		moduleResultImpl.setUserModifiedId(userModifiedId);

		if (userModifiedName == null) {
			moduleResultImpl.setUserModifiedName("");
		}
		else {
			moduleResultImpl.setUserModifiedName(userModifiedName);
		}

		if (createDate == Long.MIN_VALUE) {
			moduleResultImpl.setCreateDate(null);
		}
		else {
			moduleResultImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			moduleResultImpl.setModifiedDate(null);
		}
		else {
			moduleResultImpl.setModifiedDate(new Date(modifiedDate));
		}

		moduleResultImpl.setModuleId(moduleId);
		moduleResultImpl.setUserId(userId);
		moduleResultImpl.setResult(result);

		if (comments == null) {
			moduleResultImpl.setComments("");
		}
		else {
			moduleResultImpl.setComments(comments);
		}

		moduleResultImpl.setPassed(passed);

		if (startDate == Long.MIN_VALUE) {
			moduleResultImpl.setStartDate(null);
		}
		else {
			moduleResultImpl.setStartDate(new Date(startDate));
		}

		if (passedDate == Long.MIN_VALUE) {
			moduleResultImpl.setPassedDate(null);
		}
		else {
			moduleResultImpl.setPassedDate(new Date(passedDate));
		}

		if (extraData == null) {
			moduleResultImpl.setExtraData("");
		}
		else {
			moduleResultImpl.setExtraData(extraData);
		}

		moduleResultImpl.resetOriginalValues();

		return moduleResultImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mrId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userModifiedId = objectInput.readLong();
		userModifiedName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		moduleId = objectInput.readLong();

		userId = objectInput.readLong();

		result = objectInput.readDouble();
		comments = objectInput.readUTF();

		passed = objectInput.readBoolean();
		startDate = objectInput.readLong();
		passedDate = objectInput.readLong();
		extraData = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(mrId);

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

		objectOutput.writeLong(moduleId);

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
		objectOutput.writeLong(passedDate);

		if (extraData == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(extraData);
		}
	}

	public long mrId;
	public long groupId;
	public long companyId;
	public long userModifiedId;
	public String userModifiedName;
	public long createDate;
	public long modifiedDate;
	public long moduleId;
	public long userId;
	public double result;
	public String comments;
	public boolean passed;
	public long startDate;
	public long passedDate;
	public String extraData;
}