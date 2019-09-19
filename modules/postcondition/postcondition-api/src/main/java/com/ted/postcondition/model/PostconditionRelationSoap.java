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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class PostconditionRelationSoap implements Serializable {

	public static PostconditionRelationSoap toSoapModel(
		PostconditionRelation model) {

		PostconditionRelationSoap soapModel = new PostconditionRelationSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setPostconditionRelationId(
			model.getPostconditionRelationId());
		soapModel.setClassNamePostconditionId(
			model.getClassNamePostconditionId());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setExtraData(model.getExtraData());

		return soapModel;
	}

	public static PostconditionRelationSoap[] toSoapModels(
		PostconditionRelation[] models) {

		PostconditionRelationSoap[] soapModels =
			new PostconditionRelationSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static PostconditionRelationSoap[][] toSoapModels(
		PostconditionRelation[][] models) {

		PostconditionRelationSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new PostconditionRelationSoap[models.length][models[0].length];
		}
		else {
			soapModels = new PostconditionRelationSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static PostconditionRelationSoap[] toSoapModels(
		List<PostconditionRelation> models) {

		List<PostconditionRelationSoap> soapModels =
			new ArrayList<PostconditionRelationSoap>(models.size());

		for (PostconditionRelation model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new PostconditionRelationSoap[soapModels.size()]);
	}

	public PostconditionRelationSoap() {
	}

	public long getPrimaryKey() {
		return _postconditionRelationId;
	}

	public void setPrimaryKey(long pk) {
		setPostconditionRelationId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getPostconditionRelationId() {
		return _postconditionRelationId;
	}

	public void setPostconditionRelationId(long postconditionRelationId) {
		_postconditionRelationId = postconditionRelationId;
	}

	public long getClassNamePostconditionId() {
		return _classNamePostconditionId;
	}

	public void setClassNamePostconditionId(long classNamePostconditionId) {
		_classNamePostconditionId = classNamePostconditionId;
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
	private long _postconditionRelationId;
	private long _classNamePostconditionId;
	private long _classNameId;
	private long _classPK;
	private String _extraData;

}