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

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

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
public class PostconditionRelationWrapper
	extends BaseModelWrapper<PostconditionRelation>
	implements PostconditionRelation, ModelWrapper<PostconditionRelation> {

	public PostconditionRelationWrapper(
		PostconditionRelation postconditionRelation) {

		super(postconditionRelation);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("postconditionRelationId", getPostconditionRelationId());
		attributes.put(
			"classNamePostconditionId", getClassNamePostconditionId());
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

	/**
	 * Returns the fully qualified class name of this postcondition relation.
	 *
	 * @return the fully qualified class name of this postcondition relation
	 */
	@Override
	public String getClassName() {
		return model.getClassName();
	}

	/**
	 * Returns the class name ID of this postcondition relation.
	 *
	 * @return the class name ID of this postcondition relation
	 */
	@Override
	public long getClassNameId() {
		return model.getClassNameId();
	}

	/**
	 * Returns the class name postcondition ID of this postcondition relation.
	 *
	 * @return the class name postcondition ID of this postcondition relation
	 */
	@Override
	public long getClassNamePostconditionId() {
		return model.getClassNamePostconditionId();
	}

	/**
	 * Returns the class pk of this postcondition relation.
	 *
	 * @return the class pk of this postcondition relation
	 */
	@Override
	public long getClassPK() {
		return model.getClassPK();
	}

	/**
	 * Returns the extra data of this postcondition relation.
	 *
	 * @return the extra data of this postcondition relation
	 */
	@Override
	public String getExtraData() {
		return model.getExtraData();
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getExtraDataJSON() {
		return model.getExtraDataJSON();
	}

	@Override
	public Postcondition getPostcondition() {
		return model.getPostcondition();
	}

	@Override
	public PostconditionFactory getPostconditionFactory() {
		return model.getPostconditionFactory();
	}

	/**
	 * Returns the postcondition relation ID of this postcondition relation.
	 *
	 * @return the postcondition relation ID of this postcondition relation
	 */
	@Override
	public long getPostconditionRelationId() {
		return model.getPostconditionRelationId();
	}

	/**
	 * Returns the primary key of this postcondition relation.
	 *
	 * @return the primary key of this postcondition relation
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the uuid of this postcondition relation.
	 *
	 * @return the uuid of this postcondition relation
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
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
	 * Sets the class name ID of this postcondition relation.
	 *
	 * @param classNameId the class name ID of this postcondition relation
	 */
	@Override
	public void setClassNameId(long classNameId) {
		model.setClassNameId(classNameId);
	}

	/**
	 * Sets the class name postcondition ID of this postcondition relation.
	 *
	 * @param classNamePostconditionId the class name postcondition ID of this postcondition relation
	 */
	@Override
	public void setClassNamePostconditionId(long classNamePostconditionId) {
		model.setClassNamePostconditionId(classNamePostconditionId);
	}

	/**
	 * Sets the class pk of this postcondition relation.
	 *
	 * @param classPK the class pk of this postcondition relation
	 */
	@Override
	public void setClassPK(long classPK) {
		model.setClassPK(classPK);
	}

	/**
	 * Sets the extra data of this postcondition relation.
	 *
	 * @param extraData the extra data of this postcondition relation
	 */
	@Override
	public void setExtraData(String extraData) {
		model.setExtraData(extraData);
	}

	/**
	 * Sets the postcondition relation ID of this postcondition relation.
	 *
	 * @param postconditionRelationId the postcondition relation ID of this postcondition relation
	 */
	@Override
	public void setPostconditionRelationId(long postconditionRelationId) {
		model.setPostconditionRelationId(postconditionRelationId);
	}

	/**
	 * Sets the primary key of this postcondition relation.
	 *
	 * @param primaryKey the primary key of this postcondition relation
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the uuid of this postcondition relation.
	 *
	 * @param uuid the uuid of this postcondition relation
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	@Override
	protected PostconditionRelationWrapper wrap(
		PostconditionRelation postconditionRelation) {

		return new PostconditionRelationWrapper(postconditionRelation);
	}

}