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
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class CourseResultSoap implements Serializable {

	public static CourseResultSoap toSoapModel(CourseResult model) {
		CourseResultSoap soapModel = new CourseResultSoap();

		soapModel.setCrId(model.getCrId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserModifiedId(model.getUserModifiedId());
		soapModel.setUserModifiedName(model.getUserModifiedName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setCourseId(model.getCourseId());
		soapModel.setUserId(model.getUserId());
		soapModel.setResult(model.getResult());
		soapModel.setComments(model.getComments());
		soapModel.setPassed(model.isPassed());
		soapModel.setRegistrationDate(model.getRegistrationDate());
		soapModel.setStartDate(model.getStartDate());
		soapModel.setPassedDate(model.getPassedDate());
		soapModel.setAllowStartDate(model.getAllowStartDate());
		soapModel.setAllowEndDate(model.getAllowEndDate());
		soapModel.setExtraData(model.getExtraData());

		return soapModel;
	}

	public static CourseResultSoap[] toSoapModels(CourseResult[] models) {
		CourseResultSoap[] soapModels = new CourseResultSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CourseResultSoap[][] toSoapModels(CourseResult[][] models) {
		CourseResultSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CourseResultSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CourseResultSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CourseResultSoap[] toSoapModels(List<CourseResult> models) {
		List<CourseResultSoap> soapModels = new ArrayList<CourseResultSoap>(
			models.size());

		for (CourseResult model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CourseResultSoap[soapModels.size()]);
	}

	public CourseResultSoap() {
	}

	public long getPrimaryKey() {
		return _crId;
	}

	public void setPrimaryKey(long pk) {
		setCrId(pk);
	}

	public long getCrId() {
		return _crId;
	}

	public void setCrId(long crId) {
		_crId = crId;
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

	public long getCourseId() {
		return _courseId;
	}

	public void setCourseId(long courseId) {
		_courseId = courseId;
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

	public Date getRegistrationDate() {
		return _registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		_registrationDate = registrationDate;
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

	public Date getAllowStartDate() {
		return _allowStartDate;
	}

	public void setAllowStartDate(Date allowStartDate) {
		_allowStartDate = allowStartDate;
	}

	public Date getAllowEndDate() {
		return _allowEndDate;
	}

	public void setAllowEndDate(Date allowEndDate) {
		_allowEndDate = allowEndDate;
	}

	public String getExtraData() {
		return _extraData;
	}

	public void setExtraData(String extraData) {
		_extraData = extraData;
	}

	private long _crId;
	private long _groupId;
	private long _companyId;
	private long _userModifiedId;
	private String _userModifiedName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _courseId;
	private long _userId;
	private double _result;
	private String _comments;
	private boolean _passed;
	private Date _registrationDate;
	private Date _startDate;
	private Date _passedDate;
	private Date _allowStartDate;
	private Date _allowEndDate;
	private String _extraData;

}