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
public class LearningActivityTrySoap implements Serializable {
	public static LearningActivityTrySoap toSoapModel(LearningActivityTry model) {
		LearningActivityTrySoap soapModel = new LearningActivityTrySoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setLatId(model.getLatId());
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
		soapModel.setStartDate(model.getStartDate());
		soapModel.setEndDate(model.getEndDate());
		soapModel.setEndUserDate(model.getEndUserDate());
		soapModel.setTryResultData(model.getTryResultData());

		return soapModel;
	}

	public static LearningActivityTrySoap[] toSoapModels(
		LearningActivityTry[] models) {
		LearningActivityTrySoap[] soapModels = new LearningActivityTrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LearningActivityTrySoap[][] toSoapModels(
		LearningActivityTry[][] models) {
		LearningActivityTrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new LearningActivityTrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new LearningActivityTrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static LearningActivityTrySoap[] toSoapModels(
		List<LearningActivityTry> models) {
		List<LearningActivityTrySoap> soapModels = new ArrayList<LearningActivityTrySoap>(models.size());

		for (LearningActivityTry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new LearningActivityTrySoap[soapModels.size()]);
	}

	public LearningActivityTrySoap() {
	}

	public long getPrimaryKey() {
		return _latId;
	}

	public void setPrimaryKey(long pk) {
		setLatId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getLatId() {
		return _latId;
	}

	public void setLatId(long latId) {
		_latId = latId;
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

	public Date getEndUserDate() {
		return _endUserDate;
	}

	public void setEndUserDate(Date endUserDate) {
		_endUserDate = endUserDate;
	}

	public String getTryResultData() {
		return _tryResultData;
	}

	public void setTryResultData(String tryResultData) {
		_tryResultData = tryResultData;
	}

	private String _uuid;
	private long _latId;
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
	private Date _startDate;
	private Date _endDate;
	private Date _endUserDate;
	private String _tryResultData;
}