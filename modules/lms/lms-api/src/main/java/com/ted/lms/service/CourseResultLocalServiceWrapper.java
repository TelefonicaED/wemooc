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

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CourseResultLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see CourseResultLocalService
 * @generated
 */
@ProviderType
public class CourseResultLocalServiceWrapper implements CourseResultLocalService,
	ServiceWrapper<CourseResultLocalService> {
	public CourseResultLocalServiceWrapper(
		CourseResultLocalService courseResultLocalService) {
		_courseResultLocalService = courseResultLocalService;
	}

	/**
	* Adds the course result to the database. Also notifies the appropriate model listeners.
	*
	* @param courseResult the course result
	* @return the course result that was added
	*/
	@Override
	public com.ted.lms.model.CourseResult addCourseResult(
		com.ted.lms.model.CourseResult courseResult) {
		return _courseResultLocalService.addCourseResult(courseResult);
	}

	@Override
	public com.ted.lms.model.CourseResult addCourseResult(long courseId,
		long userId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {
		return _courseResultLocalService.addCourseResult(courseId, userId,
			serviceContext);
	}

	/**
	* Creates a new course result with the primary key. Does not add the course result to the database.
	*
	* @param crId the primary key for the new course result
	* @return the new course result
	*/
	@Override
	public com.ted.lms.model.CourseResult createCourseResult(long crId) {
		return _courseResultLocalService.createCourseResult(crId);
	}

	/**
	* Deletes the course result from the database. Also notifies the appropriate model listeners.
	*
	* @param courseResult the course result
	* @return the course result that was removed
	*/
	@Override
	public com.ted.lms.model.CourseResult deleteCourseResult(
		com.ted.lms.model.CourseResult courseResult) {
		return _courseResultLocalService.deleteCourseResult(courseResult);
	}

	/**
	* Deletes the course result with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param crId the primary key of the course result
	* @return the course result that was removed
	* @throws PortalException if a course result with the primary key could not be found
	*/
	@Override
	public com.ted.lms.model.CourseResult deleteCourseResult(long crId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _courseResultLocalService.deleteCourseResult(crId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _courseResultLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _courseResultLocalService.dynamicQuery();
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
		return _courseResultLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ted.lms.model.impl.CourseResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _courseResultLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ted.lms.model.impl.CourseResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _courseResultLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _courseResultLocalService.dynamicQueryCount(dynamicQuery);
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
		return _courseResultLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.ted.lms.model.CourseResult enrollStudent(
		com.ted.lms.model.Course course, long userId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {
		return _courseResultLocalService.enrollStudent(course, userId,
			serviceContext);
	}

	@Override
	public com.ted.lms.model.CourseResult fetchCourseResult(long crId) {
		return _courseResultLocalService.fetchCourseResult(crId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _courseResultLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.ted.lms.model.CourseResult getByCourseIdUserId(long courseId,
		long userId) {
		return _courseResultLocalService.getByCourseIdUserId(courseId, userId);
	}

	/**
	* Returns the course result with the primary key.
	*
	* @param crId the primary key of the course result
	* @return the course result
	* @throws PortalException if a course result with the primary key could not be found
	*/
	@Override
	public com.ted.lms.model.CourseResult getCourseResult(long crId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _courseResultLocalService.getCourseResult(crId);
	}

	/**
	* Returns a range of all the course results.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ted.lms.model.impl.CourseResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of course results
	* @param end the upper bound of the range of course results (not inclusive)
	* @return the range of course results
	*/
	@Override
	public java.util.List<com.ted.lms.model.CourseResult> getCourseResults(
		int start, int end) {
		return _courseResultLocalService.getCourseResults(start, end);
	}

	@Override
	public java.util.List<com.ted.lms.model.CourseResult> getCourseResults(
		long courseId) {
		return _courseResultLocalService.getCourseResults(courseId);
	}

	@Override
	public java.util.List<com.ted.lms.model.CourseResult> getCourseResults(
		long courseId, boolean passed) {
		return _courseResultLocalService.getCourseResults(courseId, passed);
	}

	/**
	* Returns the number of course results.
	*
	* @return the number of course results
	*/
	@Override
	public int getCourseResultsCount() {
		return _courseResultLocalService.getCourseResultsCount();
	}

	@Override
	public java.util.List<com.ted.lms.model.CourseResult> getFailedCourseResults(
		long courseId) {
		return _courseResultLocalService.getFailedCourseResults(courseId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _courseResultLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _courseResultLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _courseResultLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public boolean unsubscribeStudent(com.ted.lms.model.Course course,
		long userId) {
		return _courseResultLocalService.unsubscribeStudent(course, userId);
	}

	/**
	* Updates the course result in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param courseResult the course result
	* @return the course result that was updated
	*/
	@Override
	public com.ted.lms.model.CourseResult updateCourseResult(
		com.ted.lms.model.CourseResult courseResult) {
		return _courseResultLocalService.updateCourseResult(courseResult);
	}

	@Override
	public CourseResultLocalService getWrappedService() {
		return _courseResultLocalService;
	}

	@Override
	public void setWrappedService(
		CourseResultLocalService courseResultLocalService) {
		_courseResultLocalService = courseResultLocalService;
	}

	private CourseResultLocalService _courseResultLocalService;
}