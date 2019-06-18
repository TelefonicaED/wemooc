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
 * Provides a wrapper for {@link CourseTypeLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see CourseTypeLocalService
 * @generated
 */
@ProviderType
public class CourseTypeLocalServiceWrapper implements CourseTypeLocalService,
	ServiceWrapper<CourseTypeLocalService> {
	public CourseTypeLocalServiceWrapper(
		CourseTypeLocalService courseTypeLocalService) {
		_courseTypeLocalService = courseTypeLocalService;
	}

	/**
	* Adds the course type to the database. Also notifies the appropriate model listeners.
	*
	* @param courseType the course type
	* @return the course type that was added
	*/
	@Override
	public com.ted.lms.model.CourseType addCourseType(
		com.ted.lms.model.CourseType courseType) {
		return _courseTypeLocalService.addCourseType(courseType);
	}

	@Override
	public com.ted.lms.model.CourseType addCourseType(
		java.util.Map<java.util.Locale, String> nameMap,
		java.util.Map<java.util.Locale, String> descriptionMap,
		com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector iconSelector,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _courseTypeLocalService.addCourseType(nameMap, descriptionMap,
			iconSelector, serviceContext);
	}

	@Override
	public long addOriginalIconFileEntry(long userId, long groupId,
		long entryId,
		com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector imageSelector)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _courseTypeLocalService.addOriginalIconFileEntry(userId,
			groupId, entryId, imageSelector);
	}

	@Override
	public int countCourseTypes(long companyId) {
		return _courseTypeLocalService.countCourseTypes(companyId);
	}

	/**
	* Creates a new course type with the primary key. Does not add the course type to the database.
	*
	* @param courseTypeId the primary key for the new course type
	* @return the new course type
	*/
	@Override
	public com.ted.lms.model.CourseType createCourseType(long courseTypeId) {
		return _courseTypeLocalService.createCourseType(courseTypeId);
	}

	/**
	* Deletes the course type from the database. Also notifies the appropriate model listeners.
	*
	* @param courseType the course type
	* @return the course type that was removed
	*/
	@Override
	public com.ted.lms.model.CourseType deleteCourseType(
		com.ted.lms.model.CourseType courseType) {
		return _courseTypeLocalService.deleteCourseType(courseType);
	}

	/**
	* Deletes the course type with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param courseTypeId the primary key of the course type
	* @return the course type that was removed
	* @throws PortalException if a course type with the primary key could not be found
	*/
	@Override
	public com.ted.lms.model.CourseType deleteCourseType(long courseTypeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _courseTypeLocalService.deleteCourseType(courseTypeId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _courseTypeLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _courseTypeLocalService.dynamicQuery();
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
		return _courseTypeLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ted.lms.model.impl.CourseTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _courseTypeLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ted.lms.model.impl.CourseTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _courseTypeLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _courseTypeLocalService.dynamicQueryCount(dynamicQuery);
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
		return _courseTypeLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.ted.lms.model.CourseType fetchCourseType(long courseTypeId) {
		return _courseTypeLocalService.fetchCourseType(courseTypeId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _courseTypeLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the course type with the primary key.
	*
	* @param courseTypeId the primary key of the course type
	* @return the course type
	* @throws PortalException if a course type with the primary key could not be found
	*/
	@Override
	public com.ted.lms.model.CourseType getCourseType(long courseTypeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _courseTypeLocalService.getCourseType(courseTypeId);
	}

	/**
	* Returns a range of all the course types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ted.lms.model.impl.CourseTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of course types
	* @param end the upper bound of the range of course types (not inclusive)
	* @return the range of course types
	*/
	@Override
	public java.util.List<com.ted.lms.model.CourseType> getCourseTypes(
		int start, int end) {
		return _courseTypeLocalService.getCourseTypes(start, end);
	}

	@Override
	public java.util.List<com.ted.lms.model.CourseType> getCourseTypes(
		long companyId) {
		return _courseTypeLocalService.getCourseTypes(companyId);
	}

	@Override
	public java.util.List<com.ted.lms.model.CourseType> getCourseTypes(
		long companyId, int start, int end) {
		return _courseTypeLocalService.getCourseTypes(companyId, start, end);
	}

	/**
	* Returns the number of course types.
	*
	* @return the number of course types
	*/
	@Override
	public int getCourseTypesCount() {
		return _courseTypeLocalService.getCourseTypesCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _courseTypeLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _courseTypeLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _courseTypeLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the course type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param courseType the course type
	* @return the course type that was updated
	*/
	@Override
	public com.ted.lms.model.CourseType updateCourseType(
		com.ted.lms.model.CourseType courseType) {
		return _courseTypeLocalService.updateCourseType(courseType);
	}

	@Override
	public com.ted.lms.model.CourseType updateCourseType(long courseTypeId,
		java.util.Map<java.util.Locale, String> nameMap,
		java.util.Map<java.util.Locale, String> descriptionMap,
		com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector iconSelector,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _courseTypeLocalService.updateCourseType(courseTypeId, nameMap,
			descriptionMap, iconSelector, serviceContext);
	}

	@Override
	public CourseTypeLocalService getWrappedService() {
		return _courseTypeLocalService;
	}

	@Override
	public void setWrappedService(CourseTypeLocalService courseTypeLocalService) {
		_courseTypeLocalService = courseTypeLocalService;
	}

	private CourseTypeLocalService _courseTypeLocalService;
}