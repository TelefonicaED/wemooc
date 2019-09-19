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

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

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
public class CourseTypeRelationWrapper
	extends BaseModelWrapper<CourseTypeRelation>
	implements CourseTypeRelation, ModelWrapper<CourseTypeRelation> {

	public CourseTypeRelationWrapper(CourseTypeRelation courseTypeRelation) {
		super(courseTypeRelation);
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

	/**
	 * Returns the fully qualified class name of this course type relation.
	 *
	 * @return the fully qualified class name of this course type relation
	 */
	@Override
	public String getClassName() {
		return model.getClassName();
	}

	/**
	 * Returns the class name ID of this course type relation.
	 *
	 * @return the class name ID of this course type relation
	 */
	@Override
	public long getClassNameId() {
		return model.getClassNameId();
	}

	/**
	 * Returns the class pk of this course type relation.
	 *
	 * @return the class pk of this course type relation
	 */
	@Override
	public long getClassPK() {
		return model.getClassPK();
	}

	/**
	 * Returns the course type ID of this course type relation.
	 *
	 * @return the course type ID of this course type relation
	 */
	@Override
	public long getCourseTypeId() {
		return model.getCourseTypeId();
	}

	/**
	 * Returns the primary key of this course type relation.
	 *
	 * @return the primary key of this course type relation
	 */
	@Override
	public com.ted.lms.service.persistence.CourseTypeRelationPK
		getPrimaryKey() {

		return model.getPrimaryKey();
	}

	@Override
	public void persist() {
		model.persist();
	}

	@Override
	public void setClassName(String className) {
		model.setClassName(className);
	}

	/**
	 * Sets the class name ID of this course type relation.
	 *
	 * @param classNameId the class name ID of this course type relation
	 */
	@Override
	public void setClassNameId(long classNameId) {
		model.setClassNameId(classNameId);
	}

	/**
	 * Sets the class pk of this course type relation.
	 *
	 * @param classPK the class pk of this course type relation
	 */
	@Override
	public void setClassPK(long classPK) {
		model.setClassPK(classPK);
	}

	/**
	 * Sets the course type ID of this course type relation.
	 *
	 * @param courseTypeId the course type ID of this course type relation
	 */
	@Override
	public void setCourseTypeId(long courseTypeId) {
		model.setCourseTypeId(courseTypeId);
	}

	/**
	 * Sets the primary key of this course type relation.
	 *
	 * @param primaryKey the primary key of this course type relation
	 */
	@Override
	public void setPrimaryKey(
		com.ted.lms.service.persistence.CourseTypeRelationPK primaryKey) {

		model.setPrimaryKey(primaryKey);
	}

	@Override
	protected CourseTypeRelationWrapper wrap(
		CourseTypeRelation courseTypeRelation) {

		return new CourseTypeRelationWrapper(courseTypeRelation);
	}

}