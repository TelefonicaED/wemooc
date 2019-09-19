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

package com.ted.postcondition.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.ted.postcondition.model.PostconditionRelation;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The cache model class for representing PostconditionRelation in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class PostconditionRelationCacheModel
	implements CacheModel<PostconditionRelation>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PostconditionRelationCacheModel)) {
			return false;
		}

		PostconditionRelationCacheModel postconditionRelationCacheModel =
			(PostconditionRelationCacheModel)obj;

		if (postconditionRelationId ==
				postconditionRelationCacheModel.postconditionRelationId) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, postconditionRelationId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", postconditionRelationId=");
		sb.append(postconditionRelationId);
		sb.append(", classNamePostconditionId=");
		sb.append(classNamePostconditionId);
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
	public PostconditionRelation toEntityModel() {
		PostconditionRelationImpl postconditionRelationImpl =
			new PostconditionRelationImpl();

		if (uuid == null) {
			postconditionRelationImpl.setUuid("");
		}
		else {
			postconditionRelationImpl.setUuid(uuid);
		}

		postconditionRelationImpl.setPostconditionRelationId(
			postconditionRelationId);
		postconditionRelationImpl.setClassNamePostconditionId(
			classNamePostconditionId);
		postconditionRelationImpl.setClassNameId(classNameId);
		postconditionRelationImpl.setClassPK(classPK);

		if (extraData == null) {
			postconditionRelationImpl.setExtraData("");
		}
		else {
			postconditionRelationImpl.setExtraData(extraData);
		}

		postconditionRelationImpl.resetOriginalValues();

		return postconditionRelationImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		postconditionRelationId = objectInput.readLong();

		classNamePostconditionId = objectInput.readLong();

		classNameId = objectInput.readLong();

		classPK = objectInput.readLong();
		extraData = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(postconditionRelationId);

		objectOutput.writeLong(classNamePostconditionId);

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
	public long postconditionRelationId;
	public long classNamePostconditionId;
	public long classNameId;
	public long classPK;
	public String extraData;

}