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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ted.lms.service.http.CourseTypeServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ted.lms.service.http.CourseTypeServiceSoap
 * @generated
 */
@ProviderType
public class CourseTypeSoap implements Serializable {
	public static CourseTypeSoap toSoapModel(CourseType model) {
		CourseTypeSoap soapModel = new CourseTypeSoap();

		soapModel.setCourseTypeId(model.getCourseTypeId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setName(model.getName());
		soapModel.setDescription(model.getDescription());
		soapModel.setIconId(model.getIconId());

		return soapModel;
	}

	public static CourseTypeSoap[] toSoapModels(CourseType[] models) {
		CourseTypeSoap[] soapModels = new CourseTypeSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CourseTypeSoap[][] toSoapModels(CourseType[][] models) {
		CourseTypeSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CourseTypeSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CourseTypeSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CourseTypeSoap[] toSoapModels(List<CourseType> models) {
		List<CourseTypeSoap> soapModels = new ArrayList<CourseTypeSoap>(models.size());

		for (CourseType model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CourseTypeSoap[soapModels.size()]);
	}

	public CourseTypeSoap() {
	}

	public long getPrimaryKey() {
		return _courseTypeId;
	}

	public void setPrimaryKey(long pk) {
		setCourseTypeId(pk);
	}

	public long getCourseTypeId() {
		return _courseTypeId;
	}

	public void setCourseTypeId(long courseTypeId) {
		_courseTypeId = courseTypeId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public long getIconId() {
		return _iconId;
	}

	public void setIconId(long iconId) {
		_iconId = iconId;
	}

	private long _courseTypeId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private String _description;
	private long _iconId;
}