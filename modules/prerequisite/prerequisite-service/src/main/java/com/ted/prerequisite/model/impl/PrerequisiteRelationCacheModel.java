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

package com.ted.prerequisite.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.petra.string.StringBundler;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;

import com.ted.prerequisite.model.PrerequisiteRelation;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing PrerequisiteRelation in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see PrerequisiteRelation
 * @generated
 */
@ProviderType
public class PrerequisiteRelationCacheModel implements CacheModel<PrerequisiteRelation>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PrerequisiteRelationCacheModel)) {
			return false;
		}

		PrerequisiteRelationCacheModel prerequisiteRelationCacheModel = (PrerequisiteRelationCacheModel)obj;

		if (prerequisiteRelationId == prerequisiteRelationCacheModel.prerequisiteRelationId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, prerequisiteRelationId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", prerequisiteRelationId=");
		sb.append(prerequisiteRelationId);
		sb.append(", classNamePrerequisiteId=");
		sb.append(classNamePrerequisiteId);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", extraData=");
		sb.append(extraData);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public PrerequisiteRelation toEntityModel() {
		PrerequisiteRelationImpl prerequisiteRelationImpl = new PrerequisiteRelationImpl();

		if (uuid == null) {
			prerequisiteRelationImpl.setUuid("");
		}
		else {
			prerequisiteRelationImpl.setUuid(uuid);
		}

		prerequisiteRelationImpl.setPrerequisiteRelationId(prerequisiteRelationId);
		prerequisiteRelationImpl.setClassNamePrerequisiteId(classNamePrerequisiteId);
		prerequisiteRelationImpl.setClassNameId(classNameId);
		prerequisiteRelationImpl.setClassPK(classPK);

		if (extraData == null) {
			prerequisiteRelationImpl.setExtraData("");
		}
		else {
			prerequisiteRelationImpl.setExtraData(extraData);
		}

		prerequisiteRelationImpl.resetOriginalValues();

		return prerequisiteRelationImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		prerequisiteRelationId = objectInput.readLong();

		classNamePrerequisiteId = objectInput.readLong();

		classNameId = objectInput.readLong();

		classPK = objectInput.readLong();
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

		objectOutput.writeLong(prerequisiteRelationId);

		objectOutput.writeLong(classNamePrerequisiteId);

		objectOutput.writeLong(classNameId);

		objectOutput.writeLong(classPK);

		if (extraData == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(extraData);
		}
	}

	public String uuid;
	public long prerequisiteRelationId;
	public long classNamePrerequisiteId;
	public long classNameId;
	public long classPK;
	public String extraData;
}