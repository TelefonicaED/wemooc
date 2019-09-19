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

package com.ted.lms.inscription.teams.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.ted.lms.inscription.teams.model.Schedule;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.annotation.versioning.ProviderType;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the schedule service. This utility wraps <code>com.ted.lms.inscription.teams.service.persistence.impl.SchedulePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SchedulePersistence
 * @generated
 */
@ProviderType
public class ScheduleUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(Schedule schedule) {
		getPersistence().clearCache(schedule);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, Schedule> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Schedule> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Schedule> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Schedule> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Schedule> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Schedule update(Schedule schedule) {
		return getPersistence().update(schedule);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Schedule update(
		Schedule schedule, ServiceContext serviceContext) {

		return getPersistence().update(schedule, serviceContext);
	}

	/**
	 * Returns the schedule where teamId = &#63; or throws a <code>NoSuchScheduleException</code> if it could not be found.
	 *
	 * @param teamId the team ID
	 * @return the matching schedule
	 * @throws NoSuchScheduleException if a matching schedule could not be found
	 */
	public static Schedule findByTeamId(long teamId)
		throws com.ted.lms.inscription.teams.exception.NoSuchScheduleException {

		return getPersistence().findByTeamId(teamId);
	}

	/**
	 * Returns the schedule where teamId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param teamId the team ID
	 * @return the matching schedule, or <code>null</code> if a matching schedule could not be found
	 */
	public static Schedule fetchByTeamId(long teamId) {
		return getPersistence().fetchByTeamId(teamId);
	}

	/**
	 * Returns the schedule where teamId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param teamId the team ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching schedule, or <code>null</code> if a matching schedule could not be found
	 */
	public static Schedule fetchByTeamId(
		long teamId, boolean retrieveFromCache) {

		return getPersistence().fetchByTeamId(teamId, retrieveFromCache);
	}

	/**
	 * Removes the schedule where teamId = &#63; from the database.
	 *
	 * @param teamId the team ID
	 * @return the schedule that was removed
	 */
	public static Schedule removeByTeamId(long teamId)
		throws com.ted.lms.inscription.teams.exception.NoSuchScheduleException {

		return getPersistence().removeByTeamId(teamId);
	}

	/**
	 * Returns the number of schedules where teamId = &#63;.
	 *
	 * @param teamId the team ID
	 * @return the number of matching schedules
	 */
	public static int countByTeamId(long teamId) {
		return getPersistence().countByTeamId(teamId);
	}

	/**
	 * Caches the schedule in the entity cache if it is enabled.
	 *
	 * @param schedule the schedule
	 */
	public static void cacheResult(Schedule schedule) {
		getPersistence().cacheResult(schedule);
	}

	/**
	 * Caches the schedules in the entity cache if it is enabled.
	 *
	 * @param schedules the schedules
	 */
	public static void cacheResult(List<Schedule> schedules) {
		getPersistence().cacheResult(schedules);
	}

	/**
	 * Creates a new schedule with the primary key. Does not add the schedule to the database.
	 *
	 * @param scheduleId the primary key for the new schedule
	 * @return the new schedule
	 */
	public static Schedule create(long scheduleId) {
		return getPersistence().create(scheduleId);
	}

	/**
	 * Removes the schedule with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param scheduleId the primary key of the schedule
	 * @return the schedule that was removed
	 * @throws NoSuchScheduleException if a schedule with the primary key could not be found
	 */
	public static Schedule remove(long scheduleId)
		throws com.ted.lms.inscription.teams.exception.NoSuchScheduleException {

		return getPersistence().remove(scheduleId);
	}

	public static Schedule updateImpl(Schedule schedule) {
		return getPersistence().updateImpl(schedule);
	}

	/**
	 * Returns the schedule with the primary key or throws a <code>NoSuchScheduleException</code> if it could not be found.
	 *
	 * @param scheduleId the primary key of the schedule
	 * @return the schedule
	 * @throws NoSuchScheduleException if a schedule with the primary key could not be found
	 */
	public static Schedule findByPrimaryKey(long scheduleId)
		throws com.ted.lms.inscription.teams.exception.NoSuchScheduleException {

		return getPersistence().findByPrimaryKey(scheduleId);
	}

	/**
	 * Returns the schedule with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param scheduleId the primary key of the schedule
	 * @return the schedule, or <code>null</code> if a schedule with the primary key could not be found
	 */
	public static Schedule fetchByPrimaryKey(long scheduleId) {
		return getPersistence().fetchByPrimaryKey(scheduleId);
	}

	/**
	 * Returns all the schedules.
	 *
	 * @return the schedules
	 */
	public static List<Schedule> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the schedules.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ScheduleModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of schedules
	 * @param end the upper bound of the range of schedules (not inclusive)
	 * @return the range of schedules
	 */
	public static List<Schedule> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the schedules.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ScheduleModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of schedules
	 * @param end the upper bound of the range of schedules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of schedules
	 */
	public static List<Schedule> findAll(
		int start, int end, OrderByComparator<Schedule> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the schedules.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ScheduleModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of schedules
	 * @param end the upper bound of the range of schedules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of schedules
	 */
	public static List<Schedule> findAll(
		int start, int end, OrderByComparator<Schedule> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Removes all the schedules from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of schedules.
	 *
	 * @return the number of schedules
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static SchedulePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<SchedulePersistence, SchedulePersistence>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(SchedulePersistence.class);

		ServiceTracker<SchedulePersistence, SchedulePersistence>
			serviceTracker =
				new ServiceTracker<SchedulePersistence, SchedulePersistence>(
					bundle.getBundleContext(), SchedulePersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}