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

package com.ted.lms.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link CourseTypeRelation}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CourseTypeRelation
 * @generated
 */
@ProviderType
public class CourseTypeRelationWrapper implements CourseTypeRelation,
	ModelWrapper<CourseTypeRelation> {
	public CourseTypeRelationWrapper(CourseTypeRelation courseTypeRelation) {
		_courseTypeRelation = courseTypeRelation;
	}

	@Override
	public Class<?> getModelClass() {
		return CourseTypeRelation.class;
	}

	@Override
	public String getModelClassName() {
		return CourseTypeRelation.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("courseTypeId", getCourseTypeId());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long courseTypeId = (Long)attributes.get("courseTypeId");

		if (courseTypeId != null) {
			setCourseTypeId(courseTypeId);
		}

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}
	}

	@Override
	public Object clone() {
		return new CourseTypeRelationWrapper((CourseTypeRelation)_courseTypeRelation.clone());
	}

	@Override
	public int compareTo(CourseTypeRelation courseTypeRelation) {
		return _courseTypeRelation.compareTo(courseTypeRelation);
	}

	/**
	* Returns the fully qualified class name of this course type relation.
	*
	* @return the fully qualified class name of this course type relation
	*/
	@Override
	public String getClassName() {
		return _courseTypeRelation.getClassName();
	}

	/**
	* Returns the class name ID of this course type relation.
	*
	* @return the class name ID of this course type relation
	*/
	@Override
	public long getClassNameId() {
		return _courseTypeRelation.getClassNameId();
	}

	/**
	* Returns the class pk of this course type relation.
	*
	* @return the class pk of this course type relation
	*/
	@Override
	public long getClassPK() {
		return _courseTypeRelation.getClassPK();
	}

	/**
	* Returns the course type ID of this course type relation.
	*
	* @return the course type ID of this course type relation
	*/
	@Override
	public long getCourseTypeId() {
		return _courseTypeRelation.getCourseTypeId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _courseTypeRelation.getExpandoBridge();
	}

	/**
	* Returns the primary key of this course type relation.
	*
	* @return the primary key of this course type relation
	*/
	@Override
	public com.ted.lms.service.persistence.CourseTypeRelationPK getPrimaryKey() {
		return _courseTypeRelation.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _courseTypeRelation.getPrimaryKeyObj();
	}

	@Override
	public int hashCode() {
		return _courseTypeRelation.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _courseTypeRelation.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _courseTypeRelation.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _courseTypeRelation.isNew();
	}

	@Override
	public void persist() {
		_courseTypeRelation.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_courseTypeRelation.setCachedModel(cachedModel);
	}

	@Override
	public void setClassName(String className) {
		_courseTypeRelation.setClassName(className);
	}

	/**
	* Sets the class name ID of this course type relation.
	*
	* @param classNameId the class name ID of this course type relation
	*/
	@Override
	public void setClassNameId(long classNameId) {
		_courseTypeRelation.setClassNameId(classNameId);
	}

	/**
	* Sets the class pk of this course type relation.
	*
	* @param classPK the class pk of this course type relation
	*/
	@Override
	public void setClassPK(long classPK) {
		_courseTypeRelation.setClassPK(classPK);
	}

	/**
	* Sets the course type ID of this course type relation.
	*
	* @param courseTypeId the course type ID of this course type relation
	*/
	@Override
	public void setCourseTypeId(long courseTypeId) {
		_courseTypeRelation.setCourseTypeId(courseTypeId);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_courseTypeRelation.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_courseTypeRelation.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_courseTypeRelation.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_courseTypeRelation.setNew(n);
	}

	/**
	* Sets the primary key of this course type relation.
	*
	* @param primaryKey the primary key of this course type relation
	*/
	@Override
	public void setPrimaryKey(
		com.ted.lms.service.persistence.CourseTypeRelationPK primaryKey) {
		_courseTypeRelation.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_courseTypeRelation.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<CourseTypeRelation> toCacheModel() {
		return _courseTypeRelation.toCacheModel();
	}

	@Override
	public CourseTypeRelation toEscapedModel() {
		return new CourseTypeRelationWrapper(_courseTypeRelation.toEscapedModel());
	}

	@Override
	public String toString() {
		return _courseTypeRelation.toString();
	}

	@Override
	public CourseTypeRelation toUnescapedModel() {
		return new CourseTypeRelationWrapper(_courseTypeRelation.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _courseTypeRelation.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CourseTypeRelationWrapper)) {
			return false;
		}

		CourseTypeRelationWrapper courseTypeRelationWrapper = (CourseTypeRelationWrapper)obj;

		if (Objects.equals(_courseTypeRelation,
					courseTypeRelationWrapper._courseTypeRelation)) {
			return true;
		}

		return false;
	}

	@Override
	public CourseTypeRelation getWrappedModel() {
		return _courseTypeRelation;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _courseTypeRelation.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _courseTypeRelation.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_courseTypeRelation.resetOriginalValues();
	}

	private final CourseTypeRelation _courseTypeRelation;
}