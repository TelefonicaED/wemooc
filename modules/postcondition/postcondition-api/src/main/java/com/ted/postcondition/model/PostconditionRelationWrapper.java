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

package com.ted.postcondition.model;

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
 * This class is a wrapper for {@link PostconditionRelation}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PostconditionRelation
 * @generated
 */
@ProviderType
public class PostconditionRelationWrapper implements PostconditionRelation,
	ModelWrapper<PostconditionRelation> {
	public PostconditionRelationWrapper(
		PostconditionRelation postconditionRelation) {
		_postconditionRelation = postconditionRelation;
	}

	@Override
	public Class<?> getModelClass() {
		return PostconditionRelation.class;
	}

	@Override
	public String getModelClassName() {
		return PostconditionRelation.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("postconditionRelationId", getPostconditionRelationId());
		attributes.put("classNamePostconditionId", getClassNamePostconditionId());
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

		Long postconditionRelationId = (Long)attributes.get(
				"postconditionRelationId");

		if (postconditionRelationId != null) {
			setPostconditionRelationId(postconditionRelationId);
		}

		Long classNamePostconditionId = (Long)attributes.get(
				"classNamePostconditionId");

		if (classNamePostconditionId != null) {
			setClassNamePostconditionId(classNamePostconditionId);
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
		return new PostconditionRelationWrapper((PostconditionRelation)_postconditionRelation.clone());
	}

	@Override
	public int compareTo(PostconditionRelation postconditionRelation) {
		return _postconditionRelation.compareTo(postconditionRelation);
	}

	/**
	* Returns the fully qualified class name of this postcondition relation.
	*
	* @return the fully qualified class name of this postcondition relation
	*/
	@Override
	public String getClassName() {
		return _postconditionRelation.getClassName();
	}

	/**
	* Returns the class name ID of this postcondition relation.
	*
	* @return the class name ID of this postcondition relation
	*/
	@Override
	public long getClassNameId() {
		return _postconditionRelation.getClassNameId();
	}

	/**
	* Returns the class name postcondition ID of this postcondition relation.
	*
	* @return the class name postcondition ID of this postcondition relation
	*/
	@Override
	public long getClassNamePostconditionId() {
		return _postconditionRelation.getClassNamePostconditionId();
	}

	/**
	* Returns the class pk of this postcondition relation.
	*
	* @return the class pk of this postcondition relation
	*/
	@Override
	public long getClassPK() {
		return _postconditionRelation.getClassPK();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _postconditionRelation.getExpandoBridge();
	}

	/**
	* Returns the extra data of this postcondition relation.
	*
	* @return the extra data of this postcondition relation
	*/
	@Override
	public String getExtraData() {
		return _postconditionRelation.getExtraData();
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getExtraDataJSON() {
		return _postconditionRelation.getExtraDataJSON();
	}

	@Override
	public Postcondition getPostcondition() {
		return _postconditionRelation.getPostcondition();
	}

	@Override
	public PostconditionFactory getPostconditionFactory() {
		return _postconditionRelation.getPostconditionFactory();
	}

	/**
	* Returns the postcondition relation ID of this postcondition relation.
	*
	* @return the postcondition relation ID of this postcondition relation
	*/
	@Override
	public long getPostconditionRelationId() {
		return _postconditionRelation.getPostconditionRelationId();
	}

	/**
	* Returns the primary key of this postcondition relation.
	*
	* @return the primary key of this postcondition relation
	*/
	@Override
	public long getPrimaryKey() {
		return _postconditionRelation.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _postconditionRelation.getPrimaryKeyObj();
	}

	/**
	* Returns the uuid of this postcondition relation.
	*
	* @return the uuid of this postcondition relation
	*/
	@Override
	public String getUuid() {
		return _postconditionRelation.getUuid();
	}

	@Override
	public int hashCode() {
		return _postconditionRelation.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _postconditionRelation.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _postconditionRelation.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _postconditionRelation.isNew();
	}

	@Override
	public void persist() {
		_postconditionRelation.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_postconditionRelation.setCachedModel(cachedModel);
	}

	@Override
	public void setClassName(String className) {
		_postconditionRelation.setClassName(className);
	}

	/**
	* Sets the class name ID of this postcondition relation.
	*
	* @param classNameId the class name ID of this postcondition relation
	*/
	@Override
	public void setClassNameId(long classNameId) {
		_postconditionRelation.setClassNameId(classNameId);
	}

	/**
	* Sets the class name postcondition ID of this postcondition relation.
	*
	* @param classNamePostconditionId the class name postcondition ID of this postcondition relation
	*/
	@Override
	public void setClassNamePostconditionId(long classNamePostconditionId) {
		_postconditionRelation.setClassNamePostconditionId(classNamePostconditionId);
	}

	/**
	* Sets the class pk of this postcondition relation.
	*
	* @param classPK the class pk of this postcondition relation
	*/
	@Override
	public void setClassPK(long classPK) {
		_postconditionRelation.setClassPK(classPK);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_postconditionRelation.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_postconditionRelation.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_postconditionRelation.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the extra data of this postcondition relation.
	*
	* @param extraData the extra data of this postcondition relation
	*/
	@Override
	public void setExtraData(String extraData) {
		_postconditionRelation.setExtraData(extraData);
	}

	@Override
	public void setNew(boolean n) {
		_postconditionRelation.setNew(n);
	}

	/**
	* Sets the postcondition relation ID of this postcondition relation.
	*
	* @param postconditionRelationId the postcondition relation ID of this postcondition relation
	*/
	@Override
	public void setPostconditionRelationId(long postconditionRelationId) {
		_postconditionRelation.setPostconditionRelationId(postconditionRelationId);
	}

	/**
	* Sets the primary key of this postcondition relation.
	*
	* @param primaryKey the primary key of this postcondition relation
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_postconditionRelation.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_postconditionRelation.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the uuid of this postcondition relation.
	*
	* @param uuid the uuid of this postcondition relation
	*/
	@Override
	public void setUuid(String uuid) {
		_postconditionRelation.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<PostconditionRelation> toCacheModel() {
		return _postconditionRelation.toCacheModel();
	}

	@Override
	public PostconditionRelation toEscapedModel() {
		return new PostconditionRelationWrapper(_postconditionRelation.toEscapedModel());
	}

	@Override
	public String toString() {
		return _postconditionRelation.toString();
	}

	@Override
	public PostconditionRelation toUnescapedModel() {
		return new PostconditionRelationWrapper(_postconditionRelation.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _postconditionRelation.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PostconditionRelationWrapper)) {
			return false;
		}

		PostconditionRelationWrapper postconditionRelationWrapper = (PostconditionRelationWrapper)obj;

		if (Objects.equals(_postconditionRelation,
					postconditionRelationWrapper._postconditionRelation)) {
			return true;
		}

		return false;
	}

	@Override
	public PostconditionRelation getWrappedModel() {
		return _postconditionRelation;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _postconditionRelation.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _postconditionRelation.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_postconditionRelation.resetOriginalValues();
	}

	private final PostconditionRelation _postconditionRelation;
}