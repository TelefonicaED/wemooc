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
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class ModuleResultSoap implements Serializable {
	public static ModuleResultSoap toSoapModel(ModuleResult model) {
		ModuleResultSoap soapModel = new ModuleResultSoap();

		soapModel.setMrId(model.getMrId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserModifiedId(model.getUserModifiedId());
		soapModel.setUserModifiedName(model.getUserModifiedName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setModuleId(model.getModuleId());
		soapModel.setUserId(model.getUserId());
		soapModel.setResult(model.getResult());
		soapModel.setComments(model.getComments());
		soapModel.setPassed(model.isPassed());
		soapModel.setStartDate(model.getStartDate());
		soapModel.setPassedDate(model.getPassedDate());
		soapModel.setExtraData(model.getExtraData());

		return soapModel;
	}

	public static ModuleResultSoap[] toSoapModels(ModuleResult[] models) {
		ModuleResultSoap[] soapModels = new ModuleResultSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ModuleResultSoap[][] toSoapModels(ModuleResult[][] models) {
		ModuleResultSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ModuleResultSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ModuleResultSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ModuleResultSoap[] toSoapModels(List<ModuleResult> models) {
		List<ModuleResultSoap> soapModels = new ArrayList<ModuleResultSoap>(models.size());

		for (ModuleResult model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ModuleResultSoap[soapModels.size()]);
	}

	public ModuleResultSoap() {
	}

	public long getPrimaryKey() {
		return _mrId;
	}

	public void setPrimaryKey(long pk) {
		setMrId(pk);
	}

	public long getMrId() {
		return _mrId;
	}

	public void setMrId(long mrId) {
		_mrId = mrId;
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

	public long getUserModifiedId() {
		return _userModifiedId;
	}

	public void setUserModifiedId(long userModifiedId) {
		_userModifiedId = userModifiedId;
	}

	public String getUserModifiedName() {
		return _userModifiedName;
	}

	public void setUserModifiedName(String userModifiedName) {
		_userModifiedName = userModifiedName;
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

	public long getModuleId() {
		return _moduleId;
	}

	public void setModuleId(long moduleId) {
		_moduleId = moduleId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public double getResult() {
		return _result;
	}

	public void setResult(double result) {
		_result = result;
	}

	public String getComments() {
		return _comments;
	}

	public void setComments(String comments) {
		_comments = comments;
	}

	public boolean getPassed() {
		return _passed;
	}

	public boolean isPassed() {
		return _passed;
	}

	public void setPassed(boolean passed) {
		_passed = passed;
	}

	public Date getStartDate() {
		return _startDate;
	}

	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	public Date getPassedDate() {
		return _passedDate;
	}

	public void setPassedDate(Date passedDate) {
		_passedDate = passedDate;
	}

	public String getExtraData() {
		return _extraData;
	}

	public void setExtraData(String extraData) {
		_extraData = extraData;
	}

	private long _mrId;
	private long _groupId;
	private long _companyId;
	private long _userModifiedId;
	private String _userModifiedName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _moduleId;
	private long _userId;
	private double _result;
	private String _comments;
	private boolean _passed;
	private Date _startDate;
	private Date _passedDate;
	private String _extraData;
}