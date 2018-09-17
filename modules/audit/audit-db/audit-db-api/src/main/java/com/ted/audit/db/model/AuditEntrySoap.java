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

package com.ted.audit.db.model;

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
public class AuditEntrySoap implements Serializable {
	public static AuditEntrySoap toSoapModel(AuditEntry model) {
		AuditEntrySoap soapModel = new AuditEntrySoap();

		soapModel.setAuditId(model.getAuditId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setAssociationClassPK(model.getAssociationClassPK());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setStartDate(model.getStartDate());
		soapModel.setEndDate(model.getEndDate());
		soapModel.setActionId(model.getActionId());
		soapModel.setExtradata(model.getExtradata());

		return soapModel;
	}

	public static AuditEntrySoap[] toSoapModels(AuditEntry[] models) {
		AuditEntrySoap[] soapModels = new AuditEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AuditEntrySoap[][] toSoapModels(AuditEntry[][] models) {
		AuditEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AuditEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new AuditEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AuditEntrySoap[] toSoapModels(List<AuditEntry> models) {
		List<AuditEntrySoap> soapModels = new ArrayList<AuditEntrySoap>(models.size());

		for (AuditEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AuditEntrySoap[soapModels.size()]);
	}

	public AuditEntrySoap() {
	}

	public long getPrimaryKey() {
		return _auditId;
	}

	public void setPrimaryKey(long pk) {
		setAuditId(pk);
	}

	public long getAuditId() {
		return _auditId;
	}

	public void setAuditId(long auditId) {
		_auditId = auditId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
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

	public long getAssociationClassPK() {
		return _associationClassPK;
	}

	public void setAssociationClassPK(long associationClassPK) {
		_associationClassPK = associationClassPK;
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

	public int getActionId() {
		return _actionId;
	}

	public void setActionId(int actionId) {
		_actionId = actionId;
	}

	public String getExtradata() {
		return _extradata;
	}

	public void setExtradata(String extradata) {
		_extradata = extradata;
	}

	private long _auditId;
	private long _companyId;
	private long _groupId;
	private long _classNameId;
	private long _classPK;
	private long _associationClassPK;
	private long _userId;
	private String _userName;
	private Date _startDate;
	private Date _endDate;
	private int _actionId;
	private String _extradata;
}