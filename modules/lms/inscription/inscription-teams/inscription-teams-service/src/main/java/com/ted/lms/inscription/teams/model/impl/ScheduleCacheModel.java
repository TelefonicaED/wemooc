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

package com.ted.lms.inscription.teams.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.ted.lms.inscription.teams.model.Schedule;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The cache model class for representing Schedule in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class ScheduleCacheModel
	implements CacheModel<Schedule>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ScheduleCacheModel)) {
			return false;
		}

		ScheduleCacheModel scheduleCacheModel = (ScheduleCacheModel)obj;

		if (scheduleId == scheduleCacheModel.scheduleId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, scheduleId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{scheduleId=");
		sb.append(scheduleId);
		sb.append(", teamId=");
		sb.append(teamId);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", endDate=");
		sb.append(endDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Schedule toEntityModel() {
		ScheduleImpl scheduleImpl = new ScheduleImpl();

		scheduleImpl.setScheduleId(scheduleId);
		scheduleImpl.setTeamId(teamId);

		if (startDate == Long.MIN_VALUE) {
			scheduleImpl.setStartDate(null);
		}
		else {
			scheduleImpl.setStartDate(new Date(startDate));
		}

		if (endDate == Long.MIN_VALUE) {
			scheduleImpl.setEndDate(null);
		}
		else {
			scheduleImpl.setEndDate(new Date(endDate));
		}

		scheduleImpl.resetOriginalValues();

		return scheduleImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		scheduleId = objectInput.readLong();

		teamId = objectInput.readLong();
		startDate = objectInput.readLong();
		endDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(scheduleId);

		objectOutput.writeLong(teamId);
		objectOutput.writeLong(startDate);
		objectOutput.writeLong(endDate);
	}

	public long scheduleId;
	public long teamId;
	public long startDate;
	public long endDate;

}