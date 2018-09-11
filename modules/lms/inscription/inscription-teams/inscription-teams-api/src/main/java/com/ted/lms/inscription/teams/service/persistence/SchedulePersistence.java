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

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.ted.lms.inscription.teams.exception.NoSuchScheduleException;
import com.ted.lms.inscription.teams.model.Schedule;

/**
 * The persistence interface for the schedule service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ted.lms.inscription.teams.service.persistence.impl.SchedulePersistenceImpl
 * @see ScheduleUtil
 * @generated
 */
@ProviderType
public interface SchedulePersistence extends BasePersistence<Schedule> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ScheduleUtil} to access the schedule persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns the schedule where teamId = &#63; or throws a {@link NoSuchScheduleException} if it could not be found.
	*
	* @param teamId the team ID
	* @return the matching schedule
	* @throws NoSuchScheduleException if a matching schedule could not be found
	*/
	public Schedule findByTeamId(long teamId) throws NoSuchScheduleException;

	/**
	* Returns the schedule where teamId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param teamId the team ID
	* @return the matching schedule, or <code>null</code> if a matching schedule could not be found
	*/
	public Schedule fetchByTeamId(long teamId);

	/**
	* Returns the schedule where teamId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param teamId the team ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching schedule, or <code>null</code> if a matching schedule could not be found
	*/
	public Schedule fetchByTeamId(long teamId, boolean retrieveFromCache);

	/**
	* Removes the schedule where teamId = &#63; from the database.
	*
	* @param teamId the team ID
	* @return the schedule that was removed
	*/
	public Schedule removeByTeamId(long teamId) throws NoSuchScheduleException;

	/**
	* Returns the number of schedules where teamId = &#63;.
	*
	* @param teamId the team ID
	* @return the number of matching schedules
	*/
	public int countByTeamId(long teamId);

	/**
	* Caches the schedule in the entity cache if it is enabled.
	*
	* @param schedule the schedule
	*/
	public void cacheResult(Schedule schedule);

	/**
	* Caches the schedules in the entity cache if it is enabled.
	*
	* @param schedules the schedules
	*/
	public void cacheResult(java.util.List<Schedule> schedules);

	/**
	* Creates a new schedule with the primary key. Does not add the schedule to the database.
	*
	* @param scheduleId the primary key for the new schedule
	* @return the new schedule
	*/
	public Schedule create(long scheduleId);

	/**
	* Removes the schedule with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param scheduleId the primary key of the schedule
	* @return the schedule that was removed
	* @throws NoSuchScheduleException if a schedule with the primary key could not be found
	*/
	public Schedule remove(long scheduleId) throws NoSuchScheduleException;

	public Schedule updateImpl(Schedule schedule);

	/**
	* Returns the schedule with the primary key or throws a {@link NoSuchScheduleException} if it could not be found.
	*
	* @param scheduleId the primary key of the schedule
	* @return the schedule
	* @throws NoSuchScheduleException if a schedule with the primary key could not be found
	*/
	public Schedule findByPrimaryKey(long scheduleId)
		throws NoSuchScheduleException;

	/**
	* Returns the schedule with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param scheduleId the primary key of the schedule
	* @return the schedule, or <code>null</code> if a schedule with the primary key could not be found
	*/
	public Schedule fetchByPrimaryKey(long scheduleId);

	@Override
	public java.util.Map<java.io.Serializable, Schedule> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the schedules.
	*
	* @return the schedules
	*/
	public java.util.List<Schedule> findAll();

	/**
	* Returns a range of all the schedules.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of schedules
	* @param end the upper bound of the range of schedules (not inclusive)
	* @return the range of schedules
	*/
	public java.util.List<Schedule> findAll(int start, int end);

	/**
	* Returns an ordered range of all the schedules.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of schedules
	* @param end the upper bound of the range of schedules (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of schedules
	*/
	public java.util.List<Schedule> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Schedule> orderByComparator);

	/**
	* Returns an ordered range of all the schedules.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of schedules
	* @param end the upper bound of the range of schedules (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of schedules
	*/
	public java.util.List<Schedule> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Schedule> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the schedules from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of schedules.
	*
	* @return the number of schedules
	*/
	public int countAll();
}