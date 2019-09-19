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

package com.ted.lms.learning.activity.p2p.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.ted.lms.learning.activity.p2p.model.P2PActivity;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The cache model class for representing P2PActivity in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class P2PActivityCacheModel
	implements CacheModel<P2PActivity>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof P2PActivityCacheModel)) {
			return false;
		}

		P2PActivityCacheModel p2pActivityCacheModel =
			(P2PActivityCacheModel)obj;

		if (p2pActivityId == p2pActivityCacheModel.p2pActivityId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, p2pActivityId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(31);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", p2pActivityId=");
		sb.append(p2pActivityId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userCreateId=");
		sb.append(userCreateId);
		sb.append(", userCreateName=");
		sb.append(userCreateName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", actId=");
		sb.append(actId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", fileEntryId=");
		sb.append(fileEntryId);
		sb.append(", countCorrections=");
		sb.append(countCorrections);
		sb.append(", description=");
		sb.append(description);
		sb.append(", date=");
		sb.append(date);
		sb.append(", asignationsCompleted=");
		sb.append(asignationsCompleted);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public P2PActivity toEntityModel() {
		P2PActivityImpl p2pActivityImpl = new P2PActivityImpl();

		if (uuid == null) {
			p2pActivityImpl.setUuid("");
		}
		else {
			p2pActivityImpl.setUuid(uuid);
		}

		p2pActivityImpl.setP2pActivityId(p2pActivityId);
		p2pActivityImpl.setGroupId(groupId);
		p2pActivityImpl.setCompanyId(companyId);
		p2pActivityImpl.setUserCreateId(userCreateId);

		if (userCreateName == null) {
			p2pActivityImpl.setUserCreateName("");
		}
		else {
			p2pActivityImpl.setUserCreateName(userCreateName);
		}

		if (createDate == Long.MIN_VALUE) {
			p2pActivityImpl.setCreateDate(null);
		}
		else {
			p2pActivityImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			p2pActivityImpl.setModifiedDate(null);
		}
		else {
			p2pActivityImpl.setModifiedDate(new Date(modifiedDate));
		}

		p2pActivityImpl.setActId(actId);
		p2pActivityImpl.setUserId(userId);
		p2pActivityImpl.setFileEntryId(fileEntryId);
		p2pActivityImpl.setCountCorrections(countCorrections);

		if (description == null) {
			p2pActivityImpl.setDescription("");
		}
		else {
			p2pActivityImpl.setDescription(description);
		}

		if (date == Long.MIN_VALUE) {
			p2pActivityImpl.setDate(null);
		}
		else {
			p2pActivityImpl.setDate(new Date(date));
		}

		p2pActivityImpl.setAsignationsCompleted(asignationsCompleted);

		p2pActivityImpl.resetOriginalValues();

		return p2pActivityImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		p2pActivityId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userCreateId = objectInput.readLong();
		userCreateName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		actId = objectInput.readLong();

		userId = objectInput.readLong();

		fileEntryId = objectInput.readLong();

		countCorrections = objectInput.readLong();
		description = objectInput.readUTF();
		date = objectInput.readLong();

		asignationsCompleted = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(p2pActivityId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userCreateId);

		if (userCreateName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userCreateName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(actId);

		objectOutput.writeLong(userId);

		objectOutput.writeLong(fileEntryId);

		objectOutput.writeLong(countCorrections);

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}

		objectOutput.writeLong(date);

		objectOutput.writeBoolean(asignationsCompleted);
	}

	public String uuid;
	public long p2pActivityId;
	public long groupId;
	public long companyId;
	public long userCreateId;
	public String userCreateName;
	public long createDate;
	public long modifiedDate;
	public long actId;
	public long userId;
	public long fileEntryId;
	public long countCorrections;
	public String description;
	public long date;
	public boolean asignationsCompleted;

}