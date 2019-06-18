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
 * Provides a wrapper for {@link CourseTypeRelationLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see CourseTypeRelationLocalService
 * @generated
 */
@ProviderType
public class CourseTypeRelationLocalServiceWrapper
	implements CourseTypeRelationLocalService,
		ServiceWrapper<CourseTypeRelationLocalService> {
	public CourseTypeRelationLocalServiceWrapper(
		CourseTypeRelationLocalService courseTypeRelationLocalService) {
		_courseTypeRelationLocalService = courseTypeRelationLocalService;
	}

	/**
	* Adds the course type relation to the database. Also notifies the appropriate model listeners.
	*
	* @param courseTypeRelation the course type relation
	* @return the course type relation that was added
	*/
	@Override
	public com.ted.lms.model.CourseTypeRelation addCourseTypeRelation(
		com.ted.lms.model.CourseTypeRelation courseTypeRelation) {
		return _courseTypeRelationLocalService.addCourseTypeRelation(courseTypeRelation);
	}

	@Override
	public void addCourseTypeRelation(long courseTypeId, long classNameId,
		long[] classPKs) {
		_courseTypeRelationLocalService.addCourseTypeRelation(courseTypeId,
			classNameId, classPKs);
	}

	/**
	* Creates a new course type relation with the primary key. Does not add the course type relation to the database.
	*
	* @param courseTypeRelationPK the primary key for the new course type relation
	* @return the new course type relation
	*/
	@Override
	public com.ted.lms.model.CourseTypeRelation createCourseTypeRelation(
		com.ted.lms.service.persistence.CourseTypeRelationPK courseTypeRelationPK) {
		return _courseTypeRelationLocalService.createCourseTypeRelation(courseTypeRelationPK);
	}

	/**
	* Deletes the course type relation from the database. Also notifies the appropriate model listeners.
	*
	* @param courseTypeRelation the course type relation
	* @return the course type relation that was removed
	*/
	@Override
	public com.ted.lms.model.CourseTypeRelation deleteCourseTypeRelation(
		com.ted.lms.model.CourseTypeRelation courseTypeRelation) {
		return _courseTypeRelationLocalService.deleteCourseTypeRelation(courseTypeRelation);
	}

	/**
	* Deletes the course type relation with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param courseTypeRelationPK the primary key of the course type relation
	* @return the course type relation that was removed
	* @throws PortalException if a course type relation with the primary key could not be found
	*/
	@Override
	public com.ted.lms.model.CourseTypeRelation deleteCourseTypeRelation(
		com.ted.lms.service.persistence.CourseTypeRelationPK courseTypeRelationPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _courseTypeRelationLocalService.deleteCourseTypeRelation(courseTypeRelationPK);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _courseTypeRelationLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _courseTypeRelationLocalService.dynamicQuery();
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
		return _courseTypeRelationLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _courseTypeRelationLocalService.dynamicQuery(dynamicQuery,
			start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _courseTypeRelationLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
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
		return _courseTypeRelationLocalService.dynamicQueryCount(dynamicQuery);
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
		return _courseTypeRelationLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.ted.lms.model.CourseTypeRelation fetchCourseTypeRelation(
		com.ted.lms.service.persistence.CourseTypeRelationPK courseTypeRelationPK) {
		return _courseTypeRelationLocalService.fetchCourseTypeRelation(courseTypeRelationPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _courseTypeRelationLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List<Long> getCalificationTypeIds(long courseTypeId) {
		return _courseTypeRelationLocalService.getCalificationTypeIds(courseTypeId);
	}

	@Override
	public java.util.List<com.ted.lms.model.CalificationTypeFactory> getCalificationTypes(
		long courseTypeId) {
		return _courseTypeRelationLocalService.getCalificationTypes(courseTypeId);
	}

	@Override
	public java.util.List<Long> getCourseEvalIds(long courseTypeId) {
		return _courseTypeRelationLocalService.getCourseEvalIds(courseTypeId);
	}

	@Override
	public java.util.List<com.ted.lms.model.CourseEvalFactory> getCourseEvals(
		long courseTypeId) {
		return _courseTypeRelationLocalService.getCourseEvals(courseTypeId);
	}

	/**
	* Returns the course type relation with the primary key.
	*
	* @param courseTypeRelationPK the primary key of the course type relation
	* @return the course type relation
	* @throws PortalException if a course type relation with the primary key could not be found
	*/
	@Override
	public com.ted.lms.model.CourseTypeRelation getCourseTypeRelation(
		com.ted.lms.service.persistence.CourseTypeRelationPK courseTypeRelationPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _courseTypeRelationLocalService.getCourseTypeRelation(courseTypeRelationPK);
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
	@Override
	public java.util.List<com.ted.lms.model.CourseTypeRelation> getCourseTypeRelations(
		int start, int end) {
		return _courseTypeRelationLocalService.getCourseTypeRelations(start, end);
	}

	/**
	* Returns the number of course type relations.
	*
	* @return the number of course type relations
	*/
	@Override
	public int getCourseTypeRelationsCount() {
		return _courseTypeRelationLocalService.getCourseTypeRelationsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _courseTypeRelationLocalService.getIndexableActionableDynamicQuery();
	}

	@Override
	public java.util.List<Long> getInscriptionTypeIds(long courseTypeId) {
		return _courseTypeRelationLocalService.getInscriptionTypeIds(courseTypeId);
	}

	@Override
	public java.util.List<com.ted.lms.model.InscriptionTypeFactory> getInscriptionTypes(
		long courseTypeId) {
		return _courseTypeRelationLocalService.getInscriptionTypes(courseTypeId);
	}

	@Override
	public java.util.List<Long> getLearningActivityTypeIds(long courseTypeId) {
		return _courseTypeRelationLocalService.getLearningActivityTypeIds(courseTypeId);
	}

	@Override
	public java.util.List<com.ted.lms.model.LearningActivityTypeFactory> getLearningActivityTypes(
		long courseTypeId) {
		return _courseTypeRelationLocalService.getLearningActivityTypes(courseTypeId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _courseTypeRelationLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _courseTypeRelationLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public java.util.List<Long> getTemplateIds(long courseTypeId) {
		return _courseTypeRelationLocalService.getTemplateIds(courseTypeId);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.LayoutSetPrototype> getTemplates(
		long courseTypeId) {
		return _courseTypeRelationLocalService.getTemplates(courseTypeId);
	}

	/**
	* Updates the course type relation in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param courseTypeRelation the course type relation
	* @return the course type relation that was updated
	*/
	@Override
	public com.ted.lms.model.CourseTypeRelation updateCourseTypeRelation(
		com.ted.lms.model.CourseTypeRelation courseTypeRelation) {
		return _courseTypeRelationLocalService.updateCourseTypeRelation(courseTypeRelation);
	}

	@Override
	public CourseTypeRelationLocalService getWrappedService() {
		return _courseTypeRelationLocalService;
	}

	@Override
	public void setWrappedService(
		CourseTypeRelationLocalService courseTypeRelationLocalService) {
		_courseTypeRelationLocalService = courseTypeRelationLocalService;
	}

	private CourseTypeRelationLocalService _courseTypeRelationLocalService;
}