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

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

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
public class PrerequisiteRelationWrapper
	extends BaseModelWrapper<PrerequisiteRelation>
	implements PrerequisiteRelation, ModelWrapper<PrerequisiteRelation> {

	public PrerequisiteRelationWrapper(
		PrerequisiteRelation prerequisiteRelation) {

		super(prerequisiteRelation);
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

	/**
	 * Returns the fully qualified class name of this prerequisite relation.
	 *
	 * @return the fully qualified class name of this prerequisite relation
	 */
	@Override
	public String getClassName() {
		return model.getClassName();
	}

	/**
	 * Returns the class name ID of this prerequisite relation.
	 *
	 * @return the class name ID of this prerequisite relation
	 */
	@Override
	public long getClassNameId() {
		return model.getClassNameId();
	}

	/**
	 * Returns the class name prerequisite ID of this prerequisite relation.
	 *
	 * @return the class name prerequisite ID of this prerequisite relation
	 */
	@Override
	public long getClassNamePrerequisiteId() {
		return model.getClassNamePrerequisiteId();
	}

	/**
	 * Returns the class pk of this prerequisite relation.
	 *
	 * @return the class pk of this prerequisite relation
	 */
	@Override
	public long getClassPK() {
		return model.getClassPK();
	}

	/**
	 * Returns the extra data of this prerequisite relation.
	 *
	 * @return the extra data of this prerequisite relation
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
	public Prerequisite getPrerequisite() {
		return model.getPrerequisite();
	}

	@Override
	public PrerequisiteFactory getPrerequisiteFactory() {
		return model.getPrerequisiteFactory();
	}

	/**
	 * Returns the prerequisite relation ID of this prerequisite relation.
	 *
	 * @return the prerequisite relation ID of this prerequisite relation
	 */
	@Override
	public long getPrerequisiteRelationId() {
		return model.getPrerequisiteRelationId();
	}

	/**
	 * Returns the primary key of this prerequisite relation.
	 *
	 * @return the primary key of this prerequisite relation
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the uuid of this prerequisite relation.
	 *
	 * @return the uuid of this prerequisite relation
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
	 * Sets the class name ID of this prerequisite relation.
	 *
	 * @param classNameId the class name ID of this prerequisite relation
	 */
	@Override
	public void setClassNameId(long classNameId) {
		model.setClassNameId(classNameId);
	}

	/**
	 * Sets the class name prerequisite ID of this prerequisite relation.
	 *
	 * @param classNamePrerequisiteId the class name prerequisite ID of this prerequisite relation
	 */
	@Override
	public void setClassNamePrerequisiteId(long classNamePrerequisiteId) {
		model.setClassNamePrerequisiteId(classNamePrerequisiteId);
	}

	/**
	 * Sets the class pk of this prerequisite relation.
	 *
	 * @param classPK the class pk of this prerequisite relation
	 */
	@Override
	public void setClassPK(long classPK) {
		model.setClassPK(classPK);
	}

	/**
	 * Sets the extra data of this prerequisite relation.
	 *
	 * @param extraData the extra data of this prerequisite relation
	 */
	@Override
	public void setExtraData(String extraData) {
		model.setExtraData(extraData);
	}

	/**
	 * Sets the prerequisite relation ID of this prerequisite relation.
	 *
	 * @param prerequisiteRelationId the prerequisite relation ID of this prerequisite relation
	 */
	@Override
	public void setPrerequisiteRelationId(long prerequisiteRelationId) {
		model.setPrerequisiteRelationId(prerequisiteRelationId);
	}

	/**
	 * Sets the primary key of this prerequisite relation.
	 *
	 * @param primaryKey the primary key of this prerequisite relation
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the uuid of this prerequisite relation.
	 *
	 * @param uuid the uuid of this prerequisite relation
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	@Override
	protected PrerequisiteRelationWrapper wrap(
		PrerequisiteRelation prerequisiteRelation) {

		return new PrerequisiteRelationWrapper(prerequisiteRelation);
	}

}