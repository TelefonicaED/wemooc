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

package com.ted.lms.inscription.teams.model;

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
public class ScheduleSoap implements Serializable {
	public static ScheduleSoap toSoapModel(Schedule model) {
		ScheduleSoap soapModel = new ScheduleSoap();

		soapModel.setScheduleId(model.getScheduleId());
		soapModel.setTeamId(model.getTeamId());
		soapModel.setStartDate(model.getStartDate());
		soapModel.setEndDate(model.getEndDate());

		return soapModel;
	}

	public static ScheduleSoap[] toSoapModels(Schedule[] models) {
		ScheduleSoap[] soapModels = new ScheduleSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ScheduleSoap[][] toSoapModels(Schedule[][] models) {
		ScheduleSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ScheduleSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ScheduleSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ScheduleSoap[] toSoapModels(List<Schedule> models) {
		List<ScheduleSoap> soapModels = new ArrayList<ScheduleSoap>(models.size());

		for (Schedule model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ScheduleSoap[soapModels.size()]);
	}

	public ScheduleSoap() {
	}

	public long getPrimaryKey() {
		return _scheduleId;
	}

	public void setPrimaryKey(long pk) {
		setScheduleId(pk);
	}

	public long getScheduleId() {
		return _scheduleId;
	}

	public void setScheduleId(long scheduleId) {
		_scheduleId = scheduleId;
	}

	public long getTeamId() {
		return _teamId;
	}

	public void setTeamId(long teamId) {
		_teamId = teamId;
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

	private long _scheduleId;
	private long _teamId;
	private Date _startDate;
	private Date _endDate;
}