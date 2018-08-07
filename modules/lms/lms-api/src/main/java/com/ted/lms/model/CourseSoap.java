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
 * This class is used by SOAP remote services, specifically {@link com.ted.lms.service.http.CourseServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ted.lms.service.http.CourseServiceSoap
 * @generated
 */
@ProviderType
public class CourseSoap implements Serializable {
	public static CourseSoap toSoapModel(Course model) {
		CourseSoap soapModel = new CourseSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setCourseId(model.getCourseId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setParentCourseId(model.getParentCourseId());
		soapModel.setGroupCreatedId(model.getGroupCreatedId());
		soapModel.setTitle(model.getTitle());
		soapModel.setDescription(model.getDescription());
		soapModel.setSmallImageId(model.getSmallImageId());
		soapModel.setRegistrationStartDate(model.getRegistrationStartDate());
		soapModel.setRegistrationEndDate(model.getRegistrationEndDate());
		soapModel.setExecutionStartDate(model.getExecutionStartDate());
		soapModel.setExecutionEndDate(model.getExecutionEndDate());
		soapModel.setMaxUsers(model.getMaxUsers());
		soapModel.setInscriptionType(model.getInscriptionType());
		soapModel.setCourseEvalId(model.getCourseEvalId());
		soapModel.setCalificationType(model.getCalificationType());
		soapModel.setWelcome(model.isWelcome());
		soapModel.setWelcomeSubject(model.getWelcomeSubject());
		soapModel.setWelcomeMsg(model.getWelcomeMsg());
		soapModel.setGoodbye(model.isGoodbye());
		soapModel.setGoodbyeSubject(model.getGoodbyeSubject());
		soapModel.setGoodbyeMsg(model.getGoodbyeMsg());
		soapModel.setCourseExtraData(model.getCourseExtraData());
		soapModel.setStatus(model.getStatus());
		soapModel.setStatusByUserId(model.getStatusByUserId());
		soapModel.setStatusByUserName(model.getStatusByUserName());
		soapModel.setStatusDate(model.getStatusDate());

		return soapModel;
	}

	public static CourseSoap[] toSoapModels(Course[] models) {
		CourseSoap[] soapModels = new CourseSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CourseSoap[][] toSoapModels(Course[][] models) {
		CourseSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CourseSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CourseSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CourseSoap[] toSoapModels(List<Course> models) {
		List<CourseSoap> soapModels = new ArrayList<CourseSoap>(models.size());

		for (Course model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CourseSoap[soapModels.size()]);
	}

	public CourseSoap() {
	}

	public long getPrimaryKey() {
		return _courseId;
	}

	public void setPrimaryKey(long pk) {
		setCourseId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getCourseId() {
		return _courseId;
	}

	public void setCourseId(long courseId) {
		_courseId = courseId;
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

	public long getParentCourseId() {
		return _parentCourseId;
	}

	public void setParentCourseId(long parentCourseId) {
		_parentCourseId = parentCourseId;
	}

	public long getGroupCreatedId() {
		return _groupCreatedId;
	}

	public void setGroupCreatedId(long groupCreatedId) {
		_groupCreatedId = groupCreatedId;
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

	public Date getRegistrationStartDate() {
		return _registrationStartDate;
	}

	public void setRegistrationStartDate(Date registrationStartDate) {
		_registrationStartDate = registrationStartDate;
	}

	public Date getRegistrationEndDate() {
		return _registrationEndDate;
	}

	public void setRegistrationEndDate(Date registrationEndDate) {
		_registrationEndDate = registrationEndDate;
	}

	public Date getExecutionStartDate() {
		return _executionStartDate;
	}

	public void setExecutionStartDate(Date executionStartDate) {
		_executionStartDate = executionStartDate;
	}

	public Date getExecutionEndDate() {
		return _executionEndDate;
	}

	public void setExecutionEndDate(Date executionEndDate) {
		_executionEndDate = executionEndDate;
	}

	public int getMaxUsers() {
		return _maxUsers;
	}

	public void setMaxUsers(int maxUsers) {
		_maxUsers = maxUsers;
	}

	public long getInscriptionType() {
		return _inscriptionType;
	}

	public void setInscriptionType(long inscriptionType) {
		_inscriptionType = inscriptionType;
	}

	public long getCourseEvalId() {
		return _courseEvalId;
	}

	public void setCourseEvalId(long courseEvalId) {
		_courseEvalId = courseEvalId;
	}

	public long getCalificationType() {
		return _calificationType;
	}

	public void setCalificationType(long calificationType) {
		_calificationType = calificationType;
	}

	public boolean getWelcome() {
		return _welcome;
	}

	public boolean isWelcome() {
		return _welcome;
	}

	public void setWelcome(boolean welcome) {
		_welcome = welcome;
	}

	public String getWelcomeSubject() {
		return _welcomeSubject;
	}

	public void setWelcomeSubject(String welcomeSubject) {
		_welcomeSubject = welcomeSubject;
	}

	public String getWelcomeMsg() {
		return _welcomeMsg;
	}

	public void setWelcomeMsg(String welcomeMsg) {
		_welcomeMsg = welcomeMsg;
	}

	public boolean getGoodbye() {
		return _goodbye;
	}

	public boolean isGoodbye() {
		return _goodbye;
	}

	public void setGoodbye(boolean goodbye) {
		_goodbye = goodbye;
	}

	public String getGoodbyeSubject() {
		return _goodbyeSubject;
	}

	public void setGoodbyeSubject(String goodbyeSubject) {
		_goodbyeSubject = goodbyeSubject;
	}

	public String getGoodbyeMsg() {
		return _goodbyeMsg;
	}

	public void setGoodbyeMsg(String goodbyeMsg) {
		_goodbyeMsg = goodbyeMsg;
	}

	public String getCourseExtraData() {
		return _courseExtraData;
	}

	public void setCourseExtraData(String courseExtraData) {
		_courseExtraData = courseExtraData;
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
	private long _courseId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _parentCourseId;
	private long _groupCreatedId;
	private String _title;
	private String _description;
	private long _smallImageId;
	private Date _registrationStartDate;
	private Date _registrationEndDate;
	private Date _executionStartDate;
	private Date _executionEndDate;
	private int _maxUsers;
	private long _inscriptionType;
	private long _courseEvalId;
	private long _calificationType;
	private boolean _welcome;
	private String _welcomeSubject;
	private String _welcomeMsg;
	private boolean _goodbye;
	private String _goodbyeSubject;
	private String _goodbyeMsg;
	private String _courseExtraData;
	private int _status;
	private long _statusByUserId;
	private String _statusByUserName;
	private Date _statusDate;
}