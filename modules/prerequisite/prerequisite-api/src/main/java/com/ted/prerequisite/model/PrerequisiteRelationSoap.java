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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class PrerequisiteRelationSoap implements Serializable {
	public static PrerequisiteRelationSoap toSoapModel(
		PrerequisiteRelation model) {
		PrerequisiteRelationSoap soapModel = new PrerequisiteRelationSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setPrerequisiteRelationId(model.getPrerequisiteRelationId());
		soapModel.setClassNamePrerequisiteId(model.getClassNamePrerequisiteId());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setExtraData(model.getExtraData());

		return soapModel;
	}

	public static PrerequisiteRelationSoap[] toSoapModels(
		PrerequisiteRelation[] models) {
		PrerequisiteRelationSoap[] soapModels = new PrerequisiteRelationSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static PrerequisiteRelationSoap[][] toSoapModels(
		PrerequisiteRelation[][] models) {
		PrerequisiteRelationSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new PrerequisiteRelationSoap[models.length][models[0].length];
		}
		else {
			soapModels = new PrerequisiteRelationSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static PrerequisiteRelationSoap[] toSoapModels(
		List<PrerequisiteRelation> models) {
		List<PrerequisiteRelationSoap> soapModels = new ArrayList<PrerequisiteRelationSoap>(models.size());

		for (PrerequisiteRelation model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new PrerequisiteRelationSoap[soapModels.size()]);
	}

	public PrerequisiteRelationSoap() {
	}

	public long getPrimaryKey() {
		return _prerequisiteRelationId;
	}

	public void setPrimaryKey(long pk) {
		setPrerequisiteRelationId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getPrerequisiteRelationId() {
		return _prerequisiteRelationId;
	}

	public void setPrerequisiteRelationId(long prerequisiteRelationId) {
		_prerequisiteRelationId = prerequisiteRelationId;
	}

	public long getClassNamePrerequisiteId() {
		return _classNamePrerequisiteId;
	}

	public void setClassNamePrerequisiteId(long classNamePrerequisiteId) {
		_classNamePrerequisiteId = classNamePrerequisiteId;
	}

	public long getClassNameId() {
		return _classNameId;
	}

	public void setClassNameId(long classNameId) {
		_classNameId = classNameId;
	}

	public long getClassPK() {
		return _classPK;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;
	}

	public String getExtraData() {
		return _extraData;
	}

	public void setExtraData(String extraData) {
		_extraData = extraData;
	}

	private String _uuid;
	private long _prerequisiteRelationId;
	private long _classNamePrerequisiteId;
	private long _classNameId;
	private long _classPK;
	private String _extraData;
}