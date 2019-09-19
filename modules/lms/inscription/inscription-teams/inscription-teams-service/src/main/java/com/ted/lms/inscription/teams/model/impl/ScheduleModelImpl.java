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

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ProxyUtil;

import com.ted.lms.inscription.teams.model.Schedule;
import com.ted.lms.inscription.teams.model.ScheduleModel;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model implementation for the Schedule service. Represents a row in the &quot;iteam_Schedule&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>ScheduleModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ScheduleImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ScheduleImpl
 * @generated
 */
@ProviderType
public class ScheduleModelImpl
	extends BaseModelImpl<Schedule> implements ScheduleModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a schedule model instance should use the <code>Schedule</code> interface instead.
	 */
	public static final String TABLE_NAME = "iteam_Schedule";

	public static final Object[][] TABLE_COLUMNS = {
		{"scheduleId", Types.BIGINT}, {"teamId", Types.BIGINT},
		{"startDate", Types.TIMESTAMP}, {"endDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("scheduleId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("teamId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("startDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("endDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table iteam_Schedule (scheduleId LONG not null primary key,teamId LONG,startDate DATE null,endDate DATE null)";

	public static final String TABLE_SQL_DROP = "drop table iteam_Schedule";

	public static final String ORDER_BY_JPQL =
		" ORDER BY schedule.scheduleId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY iteam_Schedule.scheduleId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final long TEAMID_COLUMN_BITMASK = 1L;

	public static final long SCHEDULEID_COLUMN_BITMASK = 2L;

	public static void setEntityCacheEnabled(boolean entityCacheEnabled) {
		_entityCacheEnabled = entityCacheEnabled;
	}

	public static void setFinderCacheEnabled(boolean finderCacheEnabled) {
		_finderCacheEnabled = finderCacheEnabled;
	}

	public ScheduleModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _scheduleId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setScheduleId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _scheduleId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
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

		Map<String, Function<Schedule, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<Schedule, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Schedule, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((Schedule)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<Schedule, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<Schedule, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(Schedule)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<Schedule, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<Schedule, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, Schedule>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			Schedule.class.getClassLoader(), Schedule.class,
			ModelWrapper.class);

		try {
			Constructor<Schedule> constructor =
				(Constructor<Schedule>)proxyClass.getConstructor(
					InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException roe) {
					throw new InternalError(roe);
				}
			};
		}
		catch (NoSuchMethodException nsme) {
			throw new InternalError(nsme);
		}
	}

	private static final Map<String, Function<Schedule, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<Schedule, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<Schedule, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<Schedule, Object>>();
		Map<String, BiConsumer<Schedule, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<Schedule, ?>>();

		attributeGetterFunctions.put("scheduleId", Schedule::getScheduleId);
		attributeSetterBiConsumers.put(
			"scheduleId", (BiConsumer<Schedule, Long>)Schedule::setScheduleId);
		attributeGetterFunctions.put("teamId", Schedule::getTeamId);
		attributeSetterBiConsumers.put(
			"teamId", (BiConsumer<Schedule, Long>)Schedule::setTeamId);
		attributeGetterFunctions.put("startDate", Schedule::getStartDate);
		attributeSetterBiConsumers.put(
			"startDate", (BiConsumer<Schedule, Date>)Schedule::setStartDate);
		attributeGetterFunctions.put("endDate", Schedule::getEndDate);
		attributeSetterBiConsumers.put(
			"endDate", (BiConsumer<Schedule, Date>)Schedule::setEndDate);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@Override
	public long getScheduleId() {
		return _scheduleId;
	}

	@Override
	public void setScheduleId(long scheduleId) {
		_scheduleId = scheduleId;
	}

	@Override
	public long getTeamId() {
		return _teamId;
	}

	@Override
	public void setTeamId(long teamId) {
		_columnBitmask |= TEAMID_COLUMN_BITMASK;

		if (!_setOriginalTeamId) {
			_setOriginalTeamId = true;

			_originalTeamId = _teamId;
		}

		_teamId = teamId;
	}

	public long getOriginalTeamId() {
		return _originalTeamId;
	}

	@Override
	public Date getStartDate() {
		return _startDate;
	}

	@Override
	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	@Override
	public Date getEndDate() {
		return _endDate;
	}

	@Override
	public void setEndDate(Date endDate) {
		_endDate = endDate;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			0, Schedule.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Schedule toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = _escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		ScheduleImpl scheduleImpl = new ScheduleImpl();

		scheduleImpl.setScheduleId(getScheduleId());
		scheduleImpl.setTeamId(getTeamId());
		scheduleImpl.setStartDate(getStartDate());
		scheduleImpl.setEndDate(getEndDate());

		scheduleImpl.resetOriginalValues();

		return scheduleImpl;
	}

	@Override
	public int compareTo(Schedule schedule) {
		long primaryKey = schedule.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Schedule)) {
			return false;
		}

		Schedule schedule = (Schedule)obj;

		long primaryKey = schedule.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _entityCacheEnabled;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _finderCacheEnabled;
	}

	@Override
	public void resetOriginalValues() {
		ScheduleModelImpl scheduleModelImpl = this;

		scheduleModelImpl._originalTeamId = scheduleModelImpl._teamId;

		scheduleModelImpl._setOriginalTeamId = false;

		scheduleModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Schedule> toCacheModel() {
		ScheduleCacheModel scheduleCacheModel = new ScheduleCacheModel();

		scheduleCacheModel.scheduleId = getScheduleId();

		scheduleCacheModel.teamId = getTeamId();

		Date startDate = getStartDate();

		if (startDate != null) {
			scheduleCacheModel.startDate = startDate.getTime();
		}
		else {
			scheduleCacheModel.startDate = Long.MIN_VALUE;
		}

		Date endDate = getEndDate();

		if (endDate != null) {
			scheduleCacheModel.endDate = endDate.getTime();
		}
		else {
			scheduleCacheModel.endDate = Long.MIN_VALUE;
		}

		return scheduleCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<Schedule, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<Schedule, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Schedule, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((Schedule)this));
			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		Map<String, Function<Schedule, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<Schedule, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Schedule, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((Schedule)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static final Function<InvocationHandler, Schedule>
		_escapedModelProxyProviderFunction = _getProxyProviderFunction();
	private static boolean _entityCacheEnabled;
	private static boolean _finderCacheEnabled;

	private long _scheduleId;
	private long _teamId;
	private long _originalTeamId;
	private boolean _setOriginalTeamId;
	private Date _startDate;
	private Date _endDate;
	private long _columnBitmask;
	private Schedule _escapedModel;

}