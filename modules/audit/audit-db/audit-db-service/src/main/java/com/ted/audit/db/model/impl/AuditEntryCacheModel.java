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

package com.ted.audit.db.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import com.ted.audit.db.model.AuditEntry;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing AuditEntry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AuditEntry
 * @generated
 */
@ProviderType
public class AuditEntryCacheModel implements CacheModel<AuditEntry>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AuditEntryCacheModel)) {
			return false;
		}

		AuditEntryCacheModel auditEntryCacheModel = (AuditEntryCacheModel)obj;

		if (auditId == auditEntryCacheModel.auditId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, auditId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{auditId=");
		sb.append(auditId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", associationClassPK=");
		sb.append(associationClassPK);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", endDate=");
		sb.append(endDate);
		sb.append(", actionId=");
		sb.append(actionId);
		sb.append(", extradata=");
		sb.append(extradata);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public AuditEntry toEntityModel() {
		AuditEntryImpl auditEntryImpl = new AuditEntryImpl();

		auditEntryImpl.setAuditId(auditId);
		auditEntryImpl.setCompanyId(companyId);
		auditEntryImpl.setGroupId(groupId);
		auditEntryImpl.setClassNameId(classNameId);
		auditEntryImpl.setClassPK(classPK);
		auditEntryImpl.setAssociationClassPK(associationClassPK);
		auditEntryImpl.setUserId(userId);

		if (userName == null) {
			auditEntryImpl.setUserName("");
		}
		else {
			auditEntryImpl.setUserName(userName);
		}

		if (startDate == Long.MIN_VALUE) {
			auditEntryImpl.setStartDate(null);
		}
		else {
			auditEntryImpl.setStartDate(new Date(startDate));
		}

		if (endDate == Long.MIN_VALUE) {
			auditEntryImpl.setEndDate(null);
		}
		else {
			auditEntryImpl.setEndDate(new Date(endDate));
		}

		auditEntryImpl.setActionId(actionId);

		if (extradata == null) {
			auditEntryImpl.setExtradata("");
		}
		else {
			auditEntryImpl.setExtradata(extradata);
		}

		auditEntryImpl.resetOriginalValues();

		return auditEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		auditId = objectInput.readLong();

		companyId = objectInput.readLong();

		groupId = objectInput.readLong();

		classNameId = objectInput.readLong();

		classPK = objectInput.readLong();

		associationClassPK = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		startDate = objectInput.readLong();
		endDate = objectInput.readLong();

		actionId = objectInput.readInt();
		extradata = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(auditId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(classNameId);

		objectOutput.writeLong(classPK);

		objectOutput.writeLong(associationClassPK);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(startDate);
		objectOutput.writeLong(endDate);

		objectOutput.writeInt(actionId);

		if (extradata == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(extradata);
		}
	}

	public long auditId;
	public long companyId;
	public long groupId;
	public long classNameId;
	public long classPK;
	public long associationClassPK;
	public long userId;
	public String userName;
	public long startDate;
	public long endDate;
	public int actionId;
	public String extradata;
}