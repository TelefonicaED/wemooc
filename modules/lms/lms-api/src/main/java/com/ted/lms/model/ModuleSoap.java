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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * This class is used by SOAP remote services, specifically {@link com.ted.lms.service.http.ModuleServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class ModuleSoap implements Serializable {

	public static ModuleSoap toSoapModel(Module model) {
		ModuleSoap soapModel = new ModuleSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setModuleId(model.getModuleId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setLastPublishDate(model.getLastPublishDate());
		soapModel.setTitle(model.getTitle());
		soapModel.setDescription(model.getDescription());
		soapModel.setSmallImageId(model.getSmallImageId());
		soapModel.setPriority(model.getPriority());
		soapModel.setStartDate(model.getStartDate());
		soapModel.setEndDate(model.getEndDate());
		soapModel.setAllowedTime(model.getAllowedTime());
		soapModel.setModuleEvalId(model.getModuleEvalId());
		soapModel.setModuleExtraData(model.getModuleExtraData());
		soapModel.setStatus(model.getStatus());
		soapModel.setStatusByUserId(model.getStatusByUserId());
		soapModel.setStatusByUserName(model.getStatusByUserName());
		soapModel.setStatusDate(model.getStatusDate());

		return soapModel;
	}

	public static ModuleSoap[] toSoapModels(Module[] models) {
		ModuleSoap[] soapModels = new ModuleSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ModuleSoap[][] toSoapModels(Module[][] models) {
		ModuleSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ModuleSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ModuleSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ModuleSoap[] toSoapModels(List<Module> models) {
		List<ModuleSoap> soapModels = new ArrayList<ModuleSoap>(models.size());

		for (Module model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ModuleSoap[soapModels.size()]);
	}

	public ModuleSoap() {
	}

	public long getPrimaryKey() {
		return _moduleId;
	}

	public void setPrimaryKey(long pk) {
		setModuleId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getModuleId() {
		return _moduleId;
	}

	public void setModuleId(long moduleId) {
		_moduleId = moduleId;
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

	public Date getLastPublishDate() {
		return _lastPublishDate;
	}

	public void setLastPublishDate(Date lastPublishDate) {
		_lastPublishDate = lastPublishDate;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public long getSmallImageId() {
		return _smallImageId;
	}

	public void setSmallImageId(long smallImageId) {
		_smallImageId = smallImageId;
	}

	public long getPriority() {
		return _priority;
	}

	public void setPriority(long priority) {
		_priority = priority;
	}

	public Date getStartDate() {
		return _startDate;
	}

	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	public Date getEndDate() {
		return _endDate;
	}

	public void setEndDate(Date endDate) {
		_endDate = endDate;
	}

	public long getAllowedTime() {
		return _allowedTime;
	}

	public void setAllowedTime(long allowedTime) {
		_allowedTime = allowedTime;
	}

	public long getModuleEvalId() {
		return _moduleEvalId;
	}

	public void setModuleEvalId(long moduleEvalId) {
		_moduleEvalId = moduleEvalId;
	}

	public String getModuleExtraData() {
		return _moduleExtraData;
	}

	public void setModuleExtraData(String moduleExtraData) {
		_moduleExtraData = moduleExtraData;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	public long getStatusByUserId() {
		return _statusByUserId;
	}

	public void setStatusByUserId(long statusByUserId) {
		_statusByUserId = statusByUserId;
	}

	public String getStatusByUserName() {
		return _statusByUserName;
	}

	public void setStatusByUserName(String statusByUserName) {
		_statusByUserName = statusByUserName;
	}

	public Date getStatusDate() {
		return _statusDate;
	}

	public void setStatusDate(Date statusDate) {
		_statusDate = statusDate;
	}

	private String _uuid;
	private long _moduleId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private Date _lastPublishDate;
	private String _title;
	private String _description;
	private long _smallImageId;
	private long _priority;
	private Date _startDate;
	private Date _endDate;
	private long _allowedTime;
	private long _moduleEvalId;
	private String _moduleExtraData;
	private int _status;
	private long _statusByUserId;
	private String _statusByUserName;
	private Date _statusDate;

}