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

package com.ted.prerequisite.model;

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
 * This class is a wrapper for {@link PrerequisiteRelation}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PrerequisiteRelation
 * @generated
 */
@ProviderType
public class PrerequisiteRelationWrapper implements PrerequisiteRelation,
	ModelWrapper<PrerequisiteRelation> {
	public PrerequisiteRelationWrapper(
		PrerequisiteRelation prerequisiteRelation) {
		_prerequisiteRelation = prerequisiteRelation;
	}

	@Override
	public Class<?> getModelClass() {
		return PrerequisiteRelation.class;
	}

	@Override
	public String getModelClassName() {
		return PrerequisiteRelation.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("prerequisiteRelationId", getPrerequisiteRelationId());
		attributes.put("classNamePrerequisiteId", getClassNamePrerequisiteId());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("extraData", getExtraData());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long prerequisiteRelationId = (Long)attributes.get(
				"prerequisiteRelationId");

		if (prerequisiteRelationId != null) {
			setPrerequisiteRelationId(prerequisiteRelationId);
		}

		Long classNamePrerequisiteId = (Long)attributes.get(
				"classNamePrerequisiteId");

		if (classNamePrerequisiteId != null) {
			setClassNamePrerequisiteId(classNamePrerequisiteId);
		}

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		String extraData = (String)attributes.get("extraData");

		if (extraData != null) {
			setExtraData(extraData);
		}
	}

	@Override
	public Object clone() {
		return new PrerequisiteRelationWrapper((PrerequisiteRelation)_prerequisiteRelation.clone());
	}

	@Override
	public int compareTo(PrerequisiteRelation prerequisiteRelation) {
		return _prerequisiteRelation.compareTo(prerequisiteRelation);
	}

	/**
	* Returns the fully qualified class name of this prerequisite relation.
	*
	* @return the fully qualified class name of this prerequisite relation
	*/
	@Override
	public String getClassName() {
		return _prerequisiteRelation.getClassName();
	}

	/**
	* Returns the class name ID of this prerequisite relation.
	*
	* @return the class name ID of this prerequisite relation
	*/
	@Override
	public long getClassNameId() {
		return _prerequisiteRelation.getClassNameId();
	}

	/**
	* Returns the class name prerequisite ID of this prerequisite relation.
	*
	* @return the class name prerequisite ID of this prerequisite relation
	*/
	@Override
	public long getClassNamePrerequisiteId() {
		return _prerequisiteRelation.getClassNamePrerequisiteId();
	}

	/**
	* Returns the class pk of this prerequisite relation.
	*
	* @return the class pk of this prerequisite relation
	*/
	@Override
	public long getClassPK() {
		return _prerequisiteRelation.getClassPK();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _prerequisiteRelation.getExpandoBridge();
	}

	/**
	* Returns the extra data of this prerequisite relation.
	*
	* @return the extra data of this prerequisite relation
	*/
	@Override
	public String getExtraData() {
		return _prerequisiteRelation.getExtraData();
	}

	/**
	* Returns the prerequisite relation ID of this prerequisite relation.
	*
	* @return the prerequisite relation ID of this prerequisite relation
	*/
	@Override
	public long getPrerequisiteRelationId() {
		return _prerequisiteRelation.getPrerequisiteRelationId();
	}

	/**
	* Returns the primary key of this prerequisite relation.
	*
	* @return the primary key of this prerequisite relation
	*/
	@Override
	public long getPrimaryKey() {
		return _prerequisiteRelation.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _prerequisiteRelation.getPrimaryKeyObj();
	}

	/**
	* Returns the uuid of this prerequisite relation.
	*
	* @return the uuid of this prerequisite relation
	*/
	@Override
	public String getUuid() {
		return _prerequisiteRelation.getUuid();
	}

	@Override
	public int hashCode() {
		return _prerequisiteRelation.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _prerequisiteRelation.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _prerequisiteRelation.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _prerequisiteRelation.isNew();
	}

	@Override
	public void persist() {
		_prerequisiteRelation.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_prerequisiteRelation.setCachedModel(cachedModel);
	}

	@Override
	public void setClassName(String className) {
		_prerequisiteRelation.setClassName(className);
	}

	/**
	* Sets the class name ID of this prerequisite relation.
	*
	* @param classNameId the class name ID of this prerequisite relation
	*/
	@Override
	public void setClassNameId(long classNameId) {
		_prerequisiteRelation.setClassNameId(classNameId);
	}

	/**
	* Sets the class name prerequisite ID of this prerequisite relation.
	*
	* @param classNamePrerequisiteId the class name prerequisite ID of this prerequisite relation
	*/
	@Override
	public void setClassNamePrerequisiteId(long classNamePrerequisiteId) {
		_prerequisiteRelation.setClassNamePrerequisiteId(classNamePrerequisiteId);
	}

	/**
	* Sets the class pk of this prerequisite relation.
	*
	* @param classPK the class pk of this prerequisite relation
	*/
	@Override
	public void setClassPK(long classPK) {
		_prerequisiteRelation.setClassPK(classPK);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_prerequisiteRelation.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_prerequisiteRelation.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_prerequisiteRelation.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the extra data of this prerequisite relation.
	*
	* @param extraData the extra data of this prerequisite relation
	*/
	@Override
	public void setExtraData(String extraData) {
		_prerequisiteRelation.setExtraData(extraData);
	}

	@Override
	public void setNew(boolean n) {
		_prerequisiteRelation.setNew(n);
	}

	/**
	* Sets the prerequisite relation ID of this prerequisite relation.
	*
	* @param prerequisiteRelationId the prerequisite relation ID of this prerequisite relation
	*/
	@Override
	public void setPrerequisiteRelationId(long prerequisiteRelationId) {
		_prerequisiteRelation.setPrerequisiteRelationId(prerequisiteRelationId);
	}

	/**
	* Sets the primary key of this prerequisite relation.
	*
	* @param primaryKey the primary key of this prerequisite relation
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_prerequisiteRelation.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_prerequisiteRelation.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the uuid of this prerequisite relation.
	*
	* @param uuid the uuid of this prerequisite relation
	*/
	@Override
	public void setUuid(String uuid) {
		_prerequisiteRelation.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<PrerequisiteRelation> toCacheModel() {
		return _prerequisiteRelation.toCacheModel();
	}

	@Override
	public PrerequisiteRelation toEscapedModel() {
		return new PrerequisiteRelationWrapper(_prerequisiteRelation.toEscapedModel());
	}

	@Override
	public String toString() {
		return _prerequisiteRelation.toString();
	}

	@Override
	public PrerequisiteRelation toUnescapedModel() {
		return new PrerequisiteRelationWrapper(_prerequisiteRelation.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _prerequisiteRelation.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PrerequisiteRelationWrapper)) {
			return false;
		}

		PrerequisiteRelationWrapper prerequisiteRelationWrapper = (PrerequisiteRelationWrapper)obj;

		if (Objects.equals(_prerequisiteRelation,
					prerequisiteRelationWrapper._prerequisiteRelation)) {
			return true;
		}

		return false;
	}

	@Override
	public PrerequisiteRelation getWrappedModel() {
		return _prerequisiteRelation;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _prerequisiteRelation.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _prerequisiteRelation.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_prerequisiteRelation.resetOriginalValues();
	}

	private final PrerequisiteRelation _prerequisiteRelation;
}