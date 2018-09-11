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

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link Schedule}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Schedule
 * @generated
 */
@ProviderType
public class ScheduleWrapper implements Schedule, ModelWrapper<Schedule> {
	public ScheduleWrapper(Schedule schedule) {
		_schedule = schedule;
	}

	@Override
	public Class<?> getModelClass() {
		return Schedule.class;
	}

	@Override
	public String getModelClassName() {
		return Schedule.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("scheduleId", getScheduleId());
		attributes.put("teamId", getTeamId());
		attributes.put("startDate", getStartDate());
		attributes.put("endDate", getEndDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long scheduleId = (Long)attributes.get("scheduleId");

		if (scheduleId != null) {
			setScheduleId(scheduleId);
		}

		Long teamId = (Long)attributes.get("teamId");

		if (teamId != null) {
			setTeamId(teamId);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Date endDate = (Date)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}
	}

	@Override
	public Object clone() {
		return new ScheduleWrapper((Schedule)_schedule.clone());
	}

	@Override
	public int compareTo(Schedule schedule) {
		return _schedule.compareTo(schedule);
	}

	/**
	* Returns the end date of this schedule.
	*
	* @return the end date of this schedule
	*/
	@Override
	public Date getEndDate() {
		return _schedule.getEndDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _schedule.getExpandoBridge();
	}

	/**
	* Returns the primary key of this schedule.
	*
	* @return the primary key of this schedule
	*/
	@Override
	public long getPrimaryKey() {
		return _schedule.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _schedule.getPrimaryKeyObj();
	}

	/**
	* Returns the schedule ID of this schedule.
	*
	* @return the schedule ID of this schedule
	*/
	@Override
	public long getScheduleId() {
		return _schedule.getScheduleId();
	}

	/**
	* Returns the start date of this schedule.
	*
	* @return the start date of this schedule
	*/
	@Override
	public Date getStartDate() {
		return _schedule.getStartDate();
	}

	/**
	* Returns the team ID of this schedule.
	*
	* @return the team ID of this schedule
	*/
	@Override
	public long getTeamId() {
		return _schedule.getTeamId();
	}

	@Override
	public int hashCode() {
		return _schedule.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _schedule.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _schedule.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _schedule.isNew();
	}

	@Override
	public void persist() {
		_schedule.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_schedule.setCachedModel(cachedModel);
	}

	/**
	* Sets the end date of this schedule.
	*
	* @param endDate the end date of this schedule
	*/
	@Override
	public void setEndDate(Date endDate) {
		_schedule.setEndDate(endDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_schedule.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_schedule.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_schedule.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_schedule.setNew(n);
	}

	/**
	* Sets the primary key of this schedule.
	*
	* @param primaryKey the primary key of this schedule
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_schedule.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_schedule.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the schedule ID of this schedule.
	*
	* @param scheduleId the schedule ID of this schedule
	*/
	@Override
	public void setScheduleId(long scheduleId) {
		_schedule.setScheduleId(scheduleId);
	}

	/**
	* Sets the start date of this schedule.
	*
	* @param startDate the start date of this schedule
	*/
	@Override
	public void setStartDate(Date startDate) {
		_schedule.setStartDate(startDate);
	}

	/**
	* Sets the team ID of this schedule.
	*
	* @param teamId the team ID of this schedule
	*/
	@Override
	public void setTeamId(long teamId) {
		_schedule.setTeamId(teamId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Schedule> toCacheModel() {
		return _schedule.toCacheModel();
	}

	@Override
	public Schedule toEscapedModel() {
		return new ScheduleWrapper(_schedule.toEscapedModel());
	}

	@Override
	public String toString() {
		return _schedule.toString();
	}

	@Override
	public Schedule toUnescapedModel() {
		return new ScheduleWrapper(_schedule.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _schedule.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ScheduleWrapper)) {
			return false;
		}

		ScheduleWrapper scheduleWrapper = (ScheduleWrapper)obj;

		if (Objects.equals(_schedule, scheduleWrapper._schedule)) {
			return true;
		}

		return false;
	}

	@Override
	public Schedule getWrappedModel() {
		return _schedule;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _schedule.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _schedule.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_schedule.resetOriginalValues();
	}

	private final Schedule _schedule;
}