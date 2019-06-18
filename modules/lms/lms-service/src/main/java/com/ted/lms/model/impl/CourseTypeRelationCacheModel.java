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

import com.ted.lms.model.CourseTypeRelation;
import com.ted.lms.service.persistence.CourseTypeRelationPK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing CourseTypeRelation in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see CourseTypeRelation
 * @generated
 */
@ProviderType
public class CourseTypeRelationCacheModel implements CacheModel<CourseTypeRelation>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CourseTypeRelationCacheModel)) {
			return false;
		}

		CourseTypeRelationCacheModel courseTypeRelationCacheModel = (CourseTypeRelationCacheModel)obj;

		if (courseTypeRelationPK.equals(
					courseTypeRelationCacheModel.courseTypeRelationPK)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, courseTypeRelationPK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{courseTypeId=");
		sb.append(courseTypeId);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CourseTypeRelation toEntityModel() {
		CourseTypeRelationImpl courseTypeRelationImpl = new CourseTypeRelationImpl();

		courseTypeRelationImpl.setCourseTypeId(courseTypeId);
		courseTypeRelationImpl.setClassNameId(classNameId);
		courseTypeRelationImpl.setClassPK(classPK);

		courseTypeRelationImpl.resetOriginalValues();

		return courseTypeRelationImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		courseTypeId = objectInput.readLong();

		classNameId = objectInput.readLong();

		classPK = objectInput.readLong();

		courseTypeRelationPK = new CourseTypeRelationPK(courseTypeId,
				classNameId, classPK);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(courseTypeId);

		objectOutput.writeLong(classNameId);

		objectOutput.writeLong(classPK);
	}

	public long courseTypeId;
	public long classNameId;
	public long classPK;
	public transient CourseTypeRelationPK courseTypeRelationPK;
}