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

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

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
public class ScheduleWrapper
	extends BaseModelWrapper<Schedule>
	implements Schedule, ModelWrapper<Schedule> {

	public ScheduleWrapper(Schedule schedule) {
		super(schedule);
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

	/**
	 * Returns the end date of this schedule.
	 *
	 * @return the end date of this schedule
	 */
	@Override
	public Date getEndDate() {
		return model.getEndDate();
	}

	/**
	 * Returns the primary key of this schedule.
	 *
	 * @return the primary key of this schedule
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the schedule ID of this schedule.
	 *
	 * @return the schedule ID of this schedule
	 */
	@Override
	public long getScheduleId() {
		return model.getScheduleId();
	}

	/**
	 * Returns the start date of this schedule.
	 *
	 * @return the start date of this schedule
	 */
	@Override
	public Date getStartDate() {
		return model.getStartDate();
	}

	/**
	 * Returns the team ID of this schedule.
	 *
	 * @return the team ID of this schedule
	 */
	@Override
	public long getTeamId() {
		return model.getTeamId();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the end date of this schedule.
	 *
	 * @param endDate the end date of this schedule
	 */
	@Override
	public void setEndDate(Date endDate) {
		model.setEndDate(endDate);
	}

	/**
	 * Sets the primary key of this schedule.
	 *
	 * @param primaryKey the primary key of this schedule
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the schedule ID of this schedule.
	 *
	 * @param scheduleId the schedule ID of this schedule
	 */
	@Override
	public void setScheduleId(long scheduleId) {
		model.setScheduleId(scheduleId);
	}

	/**
	 * Sets the start date of this schedule.
	 *
	 * @param startDate the start date of this schedule
	 */
	@Override
	public void setStartDate(Date startDate) {
		model.setStartDate(startDate);
	}

	/**
	 * Sets the team ID of this schedule.
	 *
	 * @param teamId the team ID of this schedule
	 */
	@Override
	public void setTeamId(long teamId) {
		model.setTeamId(teamId);
	}

	@Override
	protected ScheduleWrapper wrap(Schedule schedule) {
		return new ScheduleWrapper(schedule);
	}

}