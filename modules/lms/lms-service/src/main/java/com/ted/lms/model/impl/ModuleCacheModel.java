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

import com.ted.lms.model.Module;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Module in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Module
 * @generated
 */
@ProviderType
public class ModuleCacheModel implements CacheModel<Module>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ModuleCacheModel)) {
			return false;
		}

		ModuleCacheModel moduleCacheModel = (ModuleCacheModel)obj;

		if (moduleId == moduleCacheModel.moduleId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, moduleId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(43);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", moduleId=");
		sb.append(moduleId);
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
		sb.append(", title=");
		sb.append(title);
		sb.append(", description=");
		sb.append(description);
		sb.append(", smallImageId=");
		sb.append(smallImageId);
		sb.append(", order=");
		sb.append(order);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", endDate=");
		sb.append(endDate);
		sb.append(", allowedTime=");
		sb.append(allowedTime);
		sb.append(", moduleEvalId=");
		sb.append(moduleEvalId);
		sb.append(", moduleExtraData=");
		sb.append(moduleExtraData);
		sb.append(", status=");
		sb.append(status);
		sb.append(", statusByUserId=");
		sb.append(statusByUserId);
		sb.append(", statusByUserName=");
		sb.append(statusByUserName);
		sb.append(", statusDate=");
		sb.append(statusDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Module toEntityModel() {
		ModuleImpl moduleImpl = new ModuleImpl();

		if (uuid == null) {
			moduleImpl.setUuid("");
		}
		else {
			moduleImpl.setUuid(uuid);
		}

		moduleImpl.setModuleId(moduleId);
		moduleImpl.setGroupId(groupId);
		moduleImpl.setCompanyId(companyId);
		moduleImpl.setUserId(userId);

		if (userName == null) {
			moduleImpl.setUserName("");
		}
		else {
			moduleImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			moduleImpl.setCreateDate(null);
		}
		else {
			moduleImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			moduleImpl.setModifiedDate(null);
		}
		else {
			moduleImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (title == null) {
			moduleImpl.setTitle("");
		}
		else {
			moduleImpl.setTitle(title);
		}

		if (description == null) {
			moduleImpl.setDescription("");
		}
		else {
			moduleImpl.setDescription(description);
		}

		moduleImpl.setSmallImageId(smallImageId);
		moduleImpl.setOrder(order);

		if (startDate == Long.MIN_VALUE) {
			moduleImpl.setStartDate(null);
		}
		else {
			moduleImpl.setStartDate(new Date(startDate));
		}

		if (endDate == Long.MIN_VALUE) {
			moduleImpl.setEndDate(null);
		}
		else {
			moduleImpl.setEndDate(new Date(endDate));
		}

		moduleImpl.setAllowedTime(allowedTime);
		moduleImpl.setModuleEvalId(moduleEvalId);

		if (moduleExtraData == null) {
			moduleImpl.setModuleExtraData("");
		}
		else {
			moduleImpl.setModuleExtraData(moduleExtraData);
		}

		moduleImpl.setStatus(status);
		moduleImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			moduleImpl.setStatusByUserName("");
		}
		else {
			moduleImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			moduleImpl.setStatusDate(null);
		}
		else {
			moduleImpl.setStatusDate(new Date(statusDate));
		}

		moduleImpl.resetOriginalValues();

		return moduleImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		moduleId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		title = objectInput.readUTF();
		description = objectInput.readUTF();

		smallImageId = objectInput.readLong();

		order = objectInput.readLong();
		startDate = objectInput.readLong();
		endDate = objectInput.readLong();

		allowedTime = objectInput.readLong();

		moduleEvalId = objectInput.readLong();
		moduleExtraData = objectInput.readUTF();

		status = objectInput.readInt();

		statusByUserId = objectInput.readLong();
		statusByUserName = objectInput.readUTF();
		statusDate = objectInput.readLong();
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

		objectOutput.writeLong(moduleId);

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

		if (title == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(title);
		}

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}

		objectOutput.writeLong(smallImageId);

		objectOutput.writeLong(order);
		objectOutput.writeLong(startDate);
		objectOutput.writeLong(endDate);

		objectOutput.writeLong(allowedTime);

		objectOutput.writeLong(moduleEvalId);

		if (moduleExtraData == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(moduleExtraData);
		}

		objectOutput.writeInt(status);

		objectOutput.writeLong(statusByUserId);

		if (statusByUserName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(statusByUserName);
		}

		objectOutput.writeLong(statusDate);
	}

	public String uuid;
	public long moduleId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String title;
	public String description;
	public long smallImageId;
	public long order;
	public long startDate;
	public long endDate;
	public long allowedTime;
	public long moduleEvalId;
	public String moduleExtraData;
	public int status;
	public long statusByUserId;
	public String statusByUserName;
	public long statusDate;
}