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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for CourseTypeRelation. This utility wraps
 * {@link com.ted.lms.service.impl.CourseTypeRelationLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see CourseTypeRelationLocalService
 * @see com.ted.lms.service.base.CourseTypeRelationLocalServiceBaseImpl
 * @see com.ted.lms.service.impl.CourseTypeRelationLocalServiceImpl
 * @generated
 */
@ProviderType
public class CourseTypeRelationLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.ted.lms.service.impl.CourseTypeRelationLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the course type relation to the database. Also notifies the appropriate model listeners.
	*
	* @param courseTypeRelation the course type relation
	* @return the course type relation that was added
	*/
	public static com.ted.lms.model.CourseTypeRelation addCourseTypeRelation(
		com.ted.lms.model.CourseTypeRelation courseTypeRelation) {
		return getService().addCourseTypeRelation(courseTypeRelation);
	}

	public static void addCourseTypeRelation(long courseTypeId,
		long classNameId, long[] classPKs) {
		getService().addCourseTypeRelation(courseTypeId, classNameId, classPKs);
	}

	/**
	* Creates a new course type relation with the primary key. Does not add the course type relation to the database.
	*
	* @param courseTypeRelationPK the primary key for the new course type relation
	* @return the new course type relation
	*/
	public static com.ted.lms.model.CourseTypeRelation createCourseTypeRelation(
		com.ted.lms.service.persistence.CourseTypeRelationPK courseTypeRelationPK) {
		return getService().createCourseTypeRelation(courseTypeRelationPK);
	}

	/**
	* Deletes the course type relation from the database. Also notifies the appropriate model listeners.
	*
	* @param courseTypeRelation the course type relation
	* @return the course type relation that was removed
	*/
	public static com.ted.lms.model.CourseTypeRelation deleteCourseTypeRelation(
		com.ted.lms.model.CourseTypeRelation courseTypeRelation) {
		return getService().deleteCourseTypeRelation(courseTypeRelation);
	}

	/**
	* Deletes the course type relation with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param courseTypeRelationPK the primary key of the course type relation
	* @return the course type relation that was removed
	* @throws PortalException if a course type relation with the primary key could not be found
	*/
	public static com.ted.lms.model.CourseTypeRelation deleteCourseTypeRelation(
		com.ted.lms.service.persistence.CourseTypeRelationPK courseTypeRelationPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteCourseTypeRelation(courseTypeRelationPK);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ted.lms.model.impl.CourseTypeRelationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ted.lms.model.impl.CourseTypeRelationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
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

	public static com.ted.lms.model.CourseTypeRelation fetchCourseTypeRelation(
		com.ted.lms.service.persistence.CourseTypeRelationPK courseTypeRelationPK) {
		return getService().fetchCourseTypeRelation(courseTypeRelationPK);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static java.util.List<Long> getCalificationTypeIds(long courseTypeId) {
		return getService().getCalificationTypeIds(courseTypeId);
	}

	public static java.util.List<com.ted.lms.model.CalificationTypeFactory> getCalificationTypes(
		long courseTypeId) {
		return getService().getCalificationTypes(courseTypeId);
	}

	public static java.util.List<Long> getCourseEvalIds(long courseTypeId) {
		return getService().getCourseEvalIds(courseTypeId);
	}

	public static java.util.List<com.ted.lms.model.CourseEvalFactory> getCourseEvals(
		long courseTypeId) {
		return getService().getCourseEvals(courseTypeId);
	}

	/**
	* Returns the course type relation with the primary key.
	*
	* @param courseTypeRelationPK the primary key of the course type relation
	* @return the course type relation
	* @throws PortalException if a course type relation with the primary key could not be found
	*/
	public static com.ted.lms.model.CourseTypeRelation getCourseTypeRelation(
		com.ted.lms.service.persistence.CourseTypeRelationPK courseTypeRelationPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getCourseTypeRelation(courseTypeRelationPK);
	}

	/**
	* Returns a range of all the course type relations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ted.lms.model.impl.CourseTypeRelationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of course type relations
	* @param end the upper bound of the range of course type relations (not inclusive)
	* @return the range of course type relations
	*/
	public static java.util.List<com.ted.lms.model.CourseTypeRelation> getCourseTypeRelations(
		int start, int end) {
		return getService().getCourseTypeRelations(start, end);
	}

	/**
	* Returns the number of course type relations.
	*
	* @return the number of course type relations
	*/
	public static int getCourseTypeRelationsCount() {
		return getService().getCourseTypeRelationsCount();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	public static java.util.List<Long> getInscriptionTypeIds(long courseTypeId) {
		return getService().getInscriptionTypeIds(courseTypeId);
	}

	public static java.util.List<com.ted.lms.model.InscriptionTypeFactory> getInscriptionTypes(
		long courseTypeId) {
		return getService().getInscriptionTypes(courseTypeId);
	}

	public static java.util.List<Long> getLearningActivityTypeIds(
		long courseTypeId) {
		return getService().getLearningActivityTypeIds(courseTypeId);
	}

	public static java.util.List<com.ted.lms.model.LearningActivityTypeFactory> getLearningActivityTypes(
		long courseTypeId) {
		return getService().getLearningActivityTypes(courseTypeId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	public static java.util.List<Long> getTemplateIds(long courseTypeId) {
		return getService().getTemplateIds(courseTypeId);
	}

	public static java.util.List<com.liferay.portal.kernel.model.LayoutSetPrototype> getTemplates(
		long courseTypeId) {
		return getService().getTemplates(courseTypeId);
	}

	/**
	* Updates the course type relation in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param courseTypeRelation the course type relation
	* @return the course type relation that was updated
	*/
	public static com.ted.lms.model.CourseTypeRelation updateCourseTypeRelation(
		com.ted.lms.model.CourseTypeRelation courseTypeRelation) {
		return getService().updateCourseTypeRelation(courseTypeRelation);
	}

	public static CourseTypeRelationLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CourseTypeRelationLocalService, CourseTypeRelationLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CourseTypeRelationLocalService.class);

		ServiceTracker<CourseTypeRelationLocalService, CourseTypeRelationLocalService> serviceTracker =
			new ServiceTracker<CourseTypeRelationLocalService, CourseTypeRelationLocalService>(bundle.getBundleContext(),
				CourseTypeRelationLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}