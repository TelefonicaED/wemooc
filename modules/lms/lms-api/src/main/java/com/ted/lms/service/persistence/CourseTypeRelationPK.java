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

package com.ted.lms.service.persistence;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;

import java.io.Serializable;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class CourseTypeRelationPK
	implements Comparable<CourseTypeRelationPK>, Serializable {

	public long courseTypeId;
	public long classNameId;
	public long classPK;

	public CourseTypeRelationPK() {
	}

	public CourseTypeRelationPK(
		long courseTypeId, long classNameId, long classPK) {

		this.courseTypeId = courseTypeId;
		this.classNameId = classNameId;
		this.classPK = classPK;
	}

	public long getCourseTypeId() {
		return courseTypeId;
	}

	public void setCourseTypeId(long courseTypeId) {
		this.courseTypeId = courseTypeId;
	}

	public long getClassNameId() {
		return classNameId;
	}

	public void setClassNameId(long classNameId) {
		this.classNameId = classNameId;
	}

	public long getClassPK() {
		return classPK;
	}

	public void setClassPK(long classPK) {
		this.classPK = classPK;
	}

	@Override
	public int compareTo(CourseTypeRelationPK pk) {
		if (pk == null) {
			return -1;
		}

		int value = 0;

		if (courseTypeId < pk.courseTypeId) {
			value = -1;
		}
		else if (courseTypeId > pk.courseTypeId) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (classNameId < pk.classNameId) {
			value = -1;
		}
		else if (classNameId > pk.classNameId) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (classPK < pk.classPK) {
			value = -1;
		}
		else if (classPK > pk.classPK) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CourseTypeRelationPK)) {
			return false;
		}

		CourseTypeRelationPK pk = (CourseTypeRelationPK)obj;

		if ((courseTypeId == pk.courseTypeId) &&
			(classNameId == pk.classNameId) && (classPK == pk.classPK)) {

			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		int hashCode = 0;

		hashCode = HashUtil.hash(hashCode, courseTypeId);
		hashCode = HashUtil.hash(hashCode, classNameId);
		hashCode = HashUtil.hash(hashCode, classPK);

		return hashCode;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(8);

		sb.append("{");

		sb.append("courseTypeId=");

		sb.append(courseTypeId);
		sb.append(", classNameId=");

		sb.append(classNameId);
		sb.append(", classPK=");

		sb.append(classPK);

		sb.append("}");

		return sb.toString();
	}

}