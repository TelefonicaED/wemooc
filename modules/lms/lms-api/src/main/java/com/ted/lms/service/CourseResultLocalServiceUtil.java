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

package com.ted.lms.service;

import org.osgi.annotation.versioning.ProviderType;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for CourseResult. This utility wraps
 * <code>com.ted.lms.service.impl.CourseResultLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see CourseResultLocalService
 * @generated
 */
@ProviderType
public class CourseResultLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.ted.lms.service.impl.CourseResultLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the course result to the database. Also notifies the appropriate model listeners.
	 *
	 * @param courseResult the course result
	 * @return the course result that was added
	 */
	public static com.ted.lms.model.CourseResult addCourseResult(
		com.ted.lms.model.CourseResult courseResult) {

		return getService().addCourseResult(courseResult);
	}

	/**
	 * Crear un courseresult para el alumno cuando se inscribe
	 *
	 * @param userId identificador del usuario que está inscribiendo
	 * @param courseId identificador del curso
	 * @param studentId usuario que se inscribe en el curso
	 * @param serviceContext
	 * @return
	 */
	public static com.ted.lms.model.CourseResult addCourseResult(
		long userId, long courseId, long studentId) {

		return getService().addCourseResult(userId, courseId, studentId);
	}

	/**
	 * Creates a new course result with the primary key. Does not add the course result to the database.
	 *
	 * @param crId the primary key for the new course result
	 * @return the new course result
	 */
	public static com.ted.lms.model.CourseResult createCourseResult(long crId) {
		return getService().createCourseResult(crId);
	}

	/**
	 * Deletes the course result from the database. Also notifies the appropriate model listeners.
	 *
	 * @param courseResult the course result
	 * @return the course result that was removed
	 */
	public static com.ted.lms.model.CourseResult deleteCourseResult(
		com.ted.lms.model.CourseResult courseResult) {

		return getService().deleteCourseResult(courseResult);
	}

	/**
	 * Deletes the course result with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param crId the primary key of the course result
	 * @return the course result that was removed
	 * @throws PortalException if a course result with the primary key could not be found
	 */
	public static com.ted.lms.model.CourseResult deleteCourseResult(long crId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteCourseResult(crId);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			deletePersistedModel(
				com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery
		dynamicQuery() {

		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.ted.lms.model.impl.CourseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.ted.lms.model.impl.CourseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.ted.lms.model.CourseResult fetchCourseResult(long crId) {
		return getService().fetchCourseResult(crId);
	}

	public static com.ted.lms.model.CourseResult fetchCourseResult(
		long courseId, long userId) {

		return getService().fetchCourseResult(courseId, userId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the course result with the primary key.
	 *
	 * @param crId the primary key of the course result
	 * @return the course result
	 * @throws PortalException if a course result with the primary key could not be found
	 */
	public static com.ted.lms.model.CourseResult getCourseResult(long crId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCourseResult(crId);
	}

	public static com.ted.lms.model.CourseResult getCourseResult(
			long courseId, long userId)
		throws com.ted.lms.exception.NoSuchCourseResultException {

		return getService().getCourseResult(courseId, userId);
	}

	/**
	 * Returns a range of all the course results.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.ted.lms.model.impl.CourseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of course results
	 * @param end the upper bound of the range of course results (not inclusive)
	 * @return the range of course results
	 */
	public static java.util.List<com.ted.lms.model.CourseResult>
		getCourseResults(int start, int end) {

		return getService().getCourseResults(start, end);
	}

	public static java.util.List<com.ted.lms.model.CourseResult>
		getCourseResults(long courseId) {

		return getService().getCourseResults(courseId);
	}

	public static java.util.List<com.ted.lms.model.CourseResult>
		getCourseResults(long courseId, boolean passed) {

		return getService().getCourseResults(courseId, passed);
	}

	/**
	 * Returns the number of course results.
	 *
	 * @return the number of course results
	 */
	public static int getCourseResultsCount() {
		return getService().getCourseResultsCount();
	}

	public static java.util.List<com.ted.lms.model.CourseResult>
		getCourseResultsNotFinished(long courseId) {

		return getService().getCourseResultsNotFinished(courseId);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	public static java.util.List<com.ted.lms.model.CourseResult> getMyCourses(
		long userId, boolean inProgress, boolean completed, boolean expired,
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc) {

		return getService().getMyCourses(
			userId, inProgress, completed, expired, groupId, start, end, obc);
	}

	public static int getMyCoursesCount(
		long userId, boolean inProgress, boolean completed, boolean expired,
		long groupId) {

		return getService().getMyCoursesCount(
			userId, inProgress, completed, expired, groupId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	public static boolean hasUserTries(long courseId, long userId) {
		return getService().hasUserTries(courseId, userId);
	}

	public static com.ted.lms.model.CourseResult recalculate(
			long userId, com.ted.lms.model.CourseResult courseResult)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().recalculate(userId, courseResult);
	}

	/**
	 * Updates the course result in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param courseResult the course result
	 * @return the course result that was updated
	 */
	public static com.ted.lms.model.CourseResult updateCourseResult(
		com.ted.lms.model.CourseResult courseResult) {

		return getService().updateCourseResult(courseResult);
	}

	public static com.ted.lms.model.CourseResult updateCourseResult(
			com.ted.lms.model.ModuleResult moduleResult)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateCourseResult(moduleResult);
	}

	public static CourseResultLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<CourseResultLocalService, CourseResultLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CourseResultLocalService.class);

		ServiceTracker<CourseResultLocalService, CourseResultLocalService>
			serviceTracker =
				new ServiceTracker
					<CourseResultLocalService, CourseResultLocalService>(
						bundle.getBundleContext(),
						CourseResultLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}