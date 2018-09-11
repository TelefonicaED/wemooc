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

package com.ted.lms.inscription.teams.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ScheduleLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ScheduleLocalService
 * @generated
 */
@ProviderType
public class ScheduleLocalServiceWrapper implements ScheduleLocalService,
	ServiceWrapper<ScheduleLocalService> {
	public ScheduleLocalServiceWrapper(
		ScheduleLocalService scheduleLocalService) {
		_scheduleLocalService = scheduleLocalService;
	}

	@Override
	public com.ted.lms.inscription.teams.model.Schedule addSchedule(
		long teamId, java.util.Date startDate, java.util.Date endDate,
		long courseId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {
		return _scheduleLocalService.addSchedule(teamId, startDate, endDate,
			courseId, serviceContext);
	}

	/**
	* Adds the schedule to the database. Also notifies the appropriate model listeners.
	*
	* @param schedule the schedule
	* @return the schedule that was added
	*/
	@Override
	public com.ted.lms.inscription.teams.model.Schedule addSchedule(
		com.ted.lms.inscription.teams.model.Schedule schedule) {
		return _scheduleLocalService.addSchedule(schedule);
	}

	/**
	* Creates a new schedule with the primary key. Does not add the schedule to the database.
	*
	* @param scheduleId the primary key for the new schedule
	* @return the new schedule
	*/
	@Override
	public com.ted.lms.inscription.teams.model.Schedule createSchedule(
		long scheduleId) {
		return _scheduleLocalService.createSchedule(scheduleId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _scheduleLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the schedule with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param scheduleId the primary key of the schedule
	* @return the schedule that was removed
	* @throws PortalException if a schedule with the primary key could not be found
	*/
	@Override
	public com.ted.lms.inscription.teams.model.Schedule deleteSchedule(
		long scheduleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _scheduleLocalService.deleteSchedule(scheduleId);
	}

	/**
	* Deletes the schedule from the database. Also notifies the appropriate model listeners.
	*
	* @param schedule the schedule
	* @return the schedule that was removed
	*/
	@Override
	public com.ted.lms.inscription.teams.model.Schedule deleteSchedule(
		com.ted.lms.inscription.teams.model.Schedule schedule) {
		return _scheduleLocalService.deleteSchedule(schedule);
	}

	@Override
	public void deleteScheduleUserDates(
		com.ted.lms.inscription.teams.model.Schedule schedule, long courseId,
		long userId) {
		_scheduleLocalService.deleteScheduleUserDates(schedule, courseId, userId);
	}

	@Override
	public void deleteScheduleUsersDates(
		com.ted.lms.inscription.teams.model.Schedule schedule, long courseId) {
		_scheduleLocalService.deleteScheduleUsersDates(schedule, courseId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _scheduleLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _scheduleLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ted.lms.inscription.teams.model.impl.ScheduleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _scheduleLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ted.lms.inscription.teams.model.impl.ScheduleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _scheduleLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _scheduleLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _scheduleLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.ted.lms.inscription.teams.model.Schedule fetchSchedule(
		long scheduleId) {
		return _scheduleLocalService.fetchSchedule(scheduleId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _scheduleLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _scheduleLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _scheduleLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _scheduleLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the schedule with the primary key.
	*
	* @param scheduleId the primary key of the schedule
	* @return the schedule
	* @throws PortalException if a schedule with the primary key could not be found
	*/
	@Override
	public com.ted.lms.inscription.teams.model.Schedule getSchedule(
		long scheduleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _scheduleLocalService.getSchedule(scheduleId);
	}

	@Override
	public com.ted.lms.inscription.teams.model.Schedule getScheduleByTeamId(
		long teamId) {
		return _scheduleLocalService.getScheduleByTeamId(teamId);
	}

	/**
	* Returns a range of all the schedules.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ted.lms.inscription.teams.model.impl.ScheduleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of schedules
	* @param end the upper bound of the range of schedules (not inclusive)
	* @return the range of schedules
	*/
	@Override
	public java.util.List<com.ted.lms.inscription.teams.model.Schedule> getSchedules(
		int start, int end) {
		return _scheduleLocalService.getSchedules(start, end);
	}

	/**
	* Returns the number of schedules.
	*
	* @return the number of schedules
	*/
	@Override
	public int getSchedulesCount() {
		return _scheduleLocalService.getSchedulesCount();
	}

	/**
	* Updates the schedule in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param schedule the schedule
	* @return the schedule that was updated
	*/
	@Override
	public com.ted.lms.inscription.teams.model.Schedule updateSchedule(
		com.ted.lms.inscription.teams.model.Schedule schedule) {
		return _scheduleLocalService.updateSchedule(schedule);
	}

	@Override
	public void updateScheduleUserDates(
		com.ted.lms.inscription.teams.model.Schedule schedule, long courseId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext,
		long userId) {
		_scheduleLocalService.updateScheduleUserDates(schedule, courseId,
			serviceContext, userId);
	}

	@Override
	public void updateScheduleUsersDates(
		com.ted.lms.inscription.teams.model.Schedule schedule, long courseId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {
		_scheduleLocalService.updateScheduleUsersDates(schedule, courseId,
			serviceContext);
	}

	@Override
	public ScheduleLocalService getWrappedService() {
		return _scheduleLocalService;
	}

	@Override
	public void setWrappedService(ScheduleLocalService scheduleLocalService) {
		_scheduleLocalService = scheduleLocalService;
	}

	private ScheduleLocalService _scheduleLocalService;
}