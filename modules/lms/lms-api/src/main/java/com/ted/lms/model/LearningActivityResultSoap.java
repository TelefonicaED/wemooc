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
public class LearningActivityResultSoap implements Serializable {
	public static LearningActivityResultSoap toSoapModel(
		LearningActivityResult model) {
		LearningActivityResultSoap soapModel = new LearningActivityResultSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setLarId(model.getLarId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserModifiedId(model.getUserModifiedId());
		soapModel.setUserModifiedName(model.getUserModifiedName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setActId(model.getActId());
		soapModel.setUserId(model.getUserId());
		soapModel.setResult(model.getResult());
		soapModel.setComments(model.getComments());
		soapModel.setPassed(model.isPassed());
		soapModel.setStartDate(model.getStartDate());
		soapModel.setEndDate(model.getEndDate());
		soapModel.setExtraData(model.getExtraData());

		return soapModel;
	}

	public static LearningActivityResultSoap[] toSoapModels(
		LearningActivityResult[] models) {
		LearningActivityResultSoap[] soapModels = new LearningActivityResultSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LearningActivityResultSoap[][] toSoapModels(
		LearningActivityResult[][] models) {
		LearningActivityResultSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new LearningActivityResultSoap[models.length][models[0].length];
		}
		else {
			soapModels = new LearningActivityResultSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static LearningActivityResultSoap[] toSoapModels(
		List<LearningActivityResult> models) {
		List<LearningActivityResultSoap> soapModels = new ArrayList<LearningActivityResultSoap>(models.size());

		for (LearningActivityResult model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new LearningActivityResultSoap[soapModels.size()]);
	}

	public LearningActivityResultSoap() {
	}

	public long getPrimaryKey() {
		return _larId;
	}

	public void setPrimaryKey(long pk) {
		setLarId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getLarId() {
		return _larId;
	}

	public void setLarId(long larId) {
		_larId = larId;
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

	public long getActId() {
		return _actId;
	}

	public void setActId(long actId) {
		_actId = actId;
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

	public Date getEndDate() {
		return _endDate;
	}

	public void setEndDate(Date endDate) {
		_endDate = endDate;
	}

	public String getExtraData() {
		return _extraData;
	}

	public void setExtraData(String extraData) {
		_extraData = extraData;
	}

	private String _uuid;
	private long _larId;
	private long _groupId;
	private long _companyId;
	private long _userModifiedId;
	private String _userModifiedName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _actId;
	private long _userId;
	private double _result;
	private String _comments;
	private boolean _passed;
	private Date _startDate;
	private Date _endDate;
	private String _extraData;
}