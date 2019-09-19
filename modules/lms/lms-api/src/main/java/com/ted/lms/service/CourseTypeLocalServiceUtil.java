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
 * Provides the local service utility for CourseType. This utility wraps
 * <code>com.ted.lms.service.impl.CourseTypeLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see CourseTypeLocalService
 * @generated
 */
@ProviderType
public class CourseTypeLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.ted.lms.service.impl.CourseTypeLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the course type to the database. Also notifies the appropriate model listeners.
	 *
	 * @param courseType the course type
	 * @return the course type that was added
	 */
	public static com.ted.lms.model.CourseType addCourseType(
		com.ted.lms.model.CourseType courseType) {

		return getService().addCourseType(courseType);
	}

	public static com.ted.lms.model.CourseType addCourseType(
			java.util.Map<java.util.Locale, String> nameMap,
			java.util.Map<java.util.Locale, String> descriptionMap,
			com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector
				iconSelector,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addCourseType(
			nameMap, descriptionMap, iconSelector, serviceContext);
	}

	public static long addOriginalIconFileEntry(
			long userId, long groupId, long entryId,
			com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector
				imageSelector)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addOriginalIconFileEntry(
			userId, groupId, entryId, imageSelector);
	}

	public static int countCourseTypes(long companyId) {
		return getService().countCourseTypes(companyId);
	}

	/**
	 * Creates a new course type with the primary key. Does not add the course type to the database.
	 *
	 * @param courseTypeId the primary key for the new course type
	 * @return the new course type
	 */
	public static com.ted.lms.model.CourseType createCourseType(
		long courseTypeId) {

		return getService().createCourseType(courseTypeId);
	}

	/**
	 * Deletes the course type from the database. Also notifies the appropriate model listeners.
	 *
	 * @param courseType the course type
	 * @return the course type that was removed
	 */
	public static com.ted.lms.model.CourseType deleteCourseType(
		com.ted.lms.model.CourseType courseType) {

		return getService().deleteCourseType(courseType);
	}

	/**
	 * Deletes the course type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param courseTypeId the primary key of the course type
	 * @return the course type that was removed
	 * @throws PortalException if a course type with the primary key could not be found
	 */
	public static com.ted.lms.model.CourseType deleteCourseType(
			long courseTypeId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteCourseType(courseTypeId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.ted.lms.model.impl.CourseTypeModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.ted.lms.model.impl.CourseTypeModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.ted.lms.model.CourseType fetchCourseType(
		long courseTypeId) {

		return getService().fetchCourseType(courseTypeId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the course type with the primary key.
	 *
	 * @param courseTypeId the primary key of the course type
	 * @return the course type
	 * @throws PortalException if a course type with the primary key could not be found
	 */
	public static com.ted.lms.model.CourseType getCourseType(long courseTypeId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCourseType(courseTypeId);
	}

	/**
	 * Returns a range of all the course types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.ted.lms.model.impl.CourseTypeModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of course types
	 * @param end the upper bound of the range of course types (not inclusive)
	 * @return the range of course types
	 */
	public static java.util.List<com.ted.lms.model.CourseType> getCourseTypes(
		int start, int end) {

		return getService().getCourseTypes(start, end);
	}

	public static java.util.List<com.ted.lms.model.CourseType> getCourseTypes(
		long companyId) {

		return getService().getCourseTypes(companyId);
	}

	public static java.util.List<com.ted.lms.model.CourseType> getCourseTypes(
		long companyId, int start, int end) {

		return getService().getCourseTypes(companyId, start, end);
	}

	/**
	 * Returns the number of course types.
	 *
	 * @return the number of course types
	 */
	public static int getCourseTypesCount() {
		return getService().getCourseTypesCount();
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
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

	/**
	 * Updates the course type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param courseType the course type
	 * @return the course type that was updated
	 */
	public static com.ted.lms.model.CourseType updateCourseType(
		com.ted.lms.model.CourseType courseType) {

		return getService().updateCourseType(courseType);
	}

	public static com.ted.lms.model.CourseType updateCourseType(
			long courseTypeId, java.util.Map<java.util.Locale, String> nameMap,
			java.util.Map<java.util.Locale, String> descriptionMap,
			com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector
				iconSelector,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateCourseType(
			courseTypeId, nameMap, descriptionMap, iconSelector,
			serviceContext);
	}

	public static CourseTypeLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<CourseTypeLocalService, CourseTypeLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CourseTypeLocalService.class);

		ServiceTracker<CourseTypeLocalService, CourseTypeLocalService>
			serviceTracker =
				new ServiceTracker
					<CourseTypeLocalService, CourseTypeLocalService>(
						bundle.getBundleContext(), CourseTypeLocalService.class,
						null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}