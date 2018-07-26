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
public class LearningActivitySoap implements Serializable {
	public static LearningActivitySoap toSoapModel(LearningActivity model) {
		LearningActivitySoap soapModel = new LearningActivitySoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setActId(model.getActId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setModuleId(model.getModuleId());
		soapModel.setTitle(model.getTitle());
		soapModel.setDescription(model.getDescription());
		soapModel.setTypeId(model.getTypeId());
		soapModel.setStartDate(model.getStartDate());
		soapModel.setEndDate(model.getEndDate());
		soapModel.setTries(model.getTries());
		soapModel.setPassPuntuation(model.getPassPuntuation());
		soapModel.setPriority(model.getPriority());
		soapModel.setExtraContent(model.getExtraContent());
		soapModel.setFeedbackCorrect(model.getFeedbackCorrect());
		soapModel.setFeedbackNoCorrect(model.getFeedbackNoCorrect());
		soapModel.setRequired(model.isRequired());
		soapModel.setCommentsActivated(model.isCommentsActivated());
		soapModel.setStatus(model.getStatus());
		soapModel.setStatusByUserId(model.getStatusByUserId());
		soapModel.setStatusByUserName(model.getStatusByUserName());
		soapModel.setStatusDate(model.getStatusDate());

		return soapModel;
	}

	public static LearningActivitySoap[] toSoapModels(LearningActivity[] models) {
		LearningActivitySoap[] soapModels = new LearningActivitySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LearningActivitySoap[][] toSoapModels(
		LearningActivity[][] models) {
		LearningActivitySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new LearningActivitySoap[models.length][models[0].length];
		}
		else {
			soapModels = new LearningActivitySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static LearningActivitySoap[] toSoapModels(
		List<LearningActivity> models) {
		List<LearningActivitySoap> soapModels = new ArrayList<LearningActivitySoap>(models.size());

		for (LearningActivity model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new LearningActivitySoap[soapModels.size()]);
	}

	public LearningActivitySoap() {
	}

	public long getPrimaryKey() {
		return _actId;
	}

	public void setPrimaryKey(long pk) {
		setActId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getActId() {
		return _actId;
	}

	public void setActId(long actId) {
		_actId = actId;
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

	public long getModuleId() {
		return _moduleId;
	}

	public void setModuleId(long moduleId) {
		_moduleId = moduleId;
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

	public int getTypeId() {
		return _typeId;
	}

	public void setTypeId(int typeId) {
		_typeId = typeId;
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

	public int getTries() {
		return _tries;
	}

	public void setTries(int tries) {
		_tries = tries;
	}

	public int getPassPuntuation() {
		return _passPuntuation;
	}

	public void setPassPuntuation(int passPuntuation) {
		_passPuntuation = passPuntuation;
	}

	public long getPriority() {
		return _priority;
	}

	public void setPriority(long priority) {
		_priority = priority;
	}

	public String getExtraContent() {
		return _extraContent;
	}

	public void setExtraContent(String extraContent) {
		_extraContent = extraContent;
	}

	public String getFeedbackCorrect() {
		return _feedbackCorrect;
	}

	public void setFeedbackCorrect(String feedbackCorrect) {
		_feedbackCorrect = feedbackCorrect;
	}

	public String getFeedbackNoCorrect() {
		return _feedbackNoCorrect;
	}

	public void setFeedbackNoCorrect(String feedbackNoCorrect) {
		_feedbackNoCorrect = feedbackNoCorrect;
	}

	public boolean getRequired() {
		return _required;
	}

	public boolean isRequired() {
		return _required;
	}

	public void setRequired(boolean required) {
		_required = required;
	}

	public boolean getCommentsActivated() {
		return _commentsActivated;
	}

	public boolean isCommentsActivated() {
		return _commentsActivated;
	}

	public void setCommentsActivated(boolean commentsActivated) {
		_commentsActivated = commentsActivated;
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
	private long _actId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _moduleId;
	private String _title;
	private String _description;
	private int _typeId;
	private Date _startDate;
	private Date _endDate;
	private int _tries;
	private int _passPuntuation;
	private long _priority;
	private String _extraContent;
	private String _feedbackCorrect;
	private String _feedbackNoCorrect;
	private boolean _required;
	private boolean _commentsActivated;
	private int _status;
	private long _statusByUserId;
	private String _statusByUserName;
	private Date _statusDate;
}