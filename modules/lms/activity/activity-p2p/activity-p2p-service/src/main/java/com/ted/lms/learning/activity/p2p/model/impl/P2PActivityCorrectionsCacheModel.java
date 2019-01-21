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

import aQute.bnd.annotation.ProviderType;

import com.liferay.petra.string.StringBundler;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;

import com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing P2PActivityCorrections in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see P2PActivityCorrections
 * @generated
 */
@ProviderType
public class P2PActivityCorrectionsCacheModel implements CacheModel<P2PActivityCorrections>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof P2PActivityCorrectionsCacheModel)) {
			return false;
		}

		P2PActivityCorrectionsCacheModel p2pActivityCorrectionsCacheModel = (P2PActivityCorrectionsCacheModel)obj;

		if (p2pActivityCorrectionsId == p2pActivityCorrectionsCacheModel.p2pActivityCorrectionsId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, p2pActivityCorrectionsId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(31);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", p2pActivityCorrectionsId=");
		sb.append(p2pActivityCorrectionsId);
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
		sb.append(", p2pActivityId=");
		sb.append(p2pActivityId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", actId=");
		sb.append(actId);
		sb.append(", description=");
		sb.append(description);
		sb.append(", date=");
		sb.append(date);
		sb.append(", fileEntryId=");
		sb.append(fileEntryId);
		sb.append(", result=");
		sb.append(result);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public P2PActivityCorrections toEntityModel() {
		P2PActivityCorrectionsImpl p2pActivityCorrectionsImpl = new P2PActivityCorrectionsImpl();

		if (uuid == null) {
			p2pActivityCorrectionsImpl.setUuid("");
		}
		else {
			p2pActivityCorrectionsImpl.setUuid(uuid);
		}

		p2pActivityCorrectionsImpl.setP2pActivityCorrectionsId(p2pActivityCorrectionsId);
		p2pActivityCorrectionsImpl.setGroupId(groupId);
		p2pActivityCorrectionsImpl.setCompanyId(companyId);
		p2pActivityCorrectionsImpl.setUserCreateId(userCreateId);

		if (userCreateName == null) {
			p2pActivityCorrectionsImpl.setUserCreateName("");
		}
		else {
			p2pActivityCorrectionsImpl.setUserCreateName(userCreateName);
		}

		if (createDate == Long.MIN_VALUE) {
			p2pActivityCorrectionsImpl.setCreateDate(null);
		}
		else {
			p2pActivityCorrectionsImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			p2pActivityCorrectionsImpl.setModifiedDate(null);
		}
		else {
			p2pActivityCorrectionsImpl.setModifiedDate(new Date(modifiedDate));
		}

		p2pActivityCorrectionsImpl.setP2pActivityId(p2pActivityId);
		p2pActivityCorrectionsImpl.setUserId(userId);
		p2pActivityCorrectionsImpl.setActId(actId);

		if (description == null) {
			p2pActivityCorrectionsImpl.setDescription("");
		}
		else {
			p2pActivityCorrectionsImpl.setDescription(description);
		}

		if (date == Long.MIN_VALUE) {
			p2pActivityCorrectionsImpl.setDate(null);
		}
		else {
			p2pActivityCorrectionsImpl.setDate(new Date(date));
		}

		p2pActivityCorrectionsImpl.setFileEntryId(fileEntryId);
		p2pActivityCorrectionsImpl.setResult(result);

		p2pActivityCorrectionsImpl.resetOriginalValues();

		return p2pActivityCorrectionsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		p2pActivityCorrectionsId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userCreateId = objectInput.readLong();
		userCreateName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		p2pActivityId = objectInput.readLong();

		userId = objectInput.readLong();

		actId = objectInput.readLong();
		description = objectInput.readUTF();
		date = objectInput.readLong();

		fileEntryId = objectInput.readLong();

		result = objectInput.readLong();
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

		objectOutput.writeLong(p2pActivityCorrectionsId);

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

		objectOutput.writeLong(p2pActivityId);

		objectOutput.writeLong(userId);

		objectOutput.writeLong(actId);

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}

		objectOutput.writeLong(date);

		objectOutput.writeLong(fileEntryId);

		objectOutput.writeLong(result);
	}

	public String uuid;
	public long p2pActivityCorrectionsId;
	public long groupId;
	public long companyId;
	public long userCreateId;
	public String userCreateName;
	public long createDate;
	public long modifiedDate;
	public long p2pActivityId;
	public long userId;
	public long actId;
	public String description;
	public long date;
	public long fileEntryId;
	public long result;
}