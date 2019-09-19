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

package com.ted.prerequisite.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides a wrapper for {@link PrerequisiteRelationLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see PrerequisiteRelationLocalService
 * @generated
 */
@ProviderType
public class PrerequisiteRelationLocalServiceWrapper
	implements PrerequisiteRelationLocalService,
			   ServiceWrapper<PrerequisiteRelationLocalService> {

	public PrerequisiteRelationLocalServiceWrapper(
		PrerequisiteRelationLocalService prerequisiteRelationLocalService) {

		_prerequisiteRelationLocalService = prerequisiteRelationLocalService;
	}

	@Override
	public com.ted.prerequisite.model.PrerequisiteRelation
		addPrerequisiteRelation(
			long classNamePrerequisiteId, long classNameId, long classPK,
			String extraData) {

		return _prerequisiteRelationLocalService.addPrerequisiteRelation(
			classNamePrerequisiteId, classNameId, classPK, extraData);
	}

	/**
	 * Adds the prerequisite relation to the database. Also notifies the appropriate model listeners.
	 *
	 * @param prerequisiteRelation the prerequisite relation
	 * @return the prerequisite relation that was added
	 */
	@Override
	public com.ted.prerequisite.model.PrerequisiteRelation
		addPrerequisiteRelation(
			com.ted.prerequisite.model.PrerequisiteRelation
				prerequisiteRelation) {

		return _prerequisiteRelationLocalService.addPrerequisiteRelation(
			prerequisiteRelation);
	}

	/**
	 * Creates a new prerequisite relation with the primary key. Does not add the prerequisite relation to the database.
	 *
	 * @param prerequisiteRelationId the primary key for the new prerequisite relation
	 * @return the new prerequisite relation
	 */
	@Override
	public com.ted.prerequisite.model.PrerequisiteRelation
		createPrerequisiteRelation(long prerequisiteRelationId) {

		return _prerequisiteRelationLocalService.createPrerequisiteRelation(
			prerequisiteRelationId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _prerequisiteRelationLocalService.deletePersistedModel(
			persistedModel);
	}

	/**
	 * Deletes the prerequisite relation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param prerequisiteRelationId the primary key of the prerequisite relation
	 * @return the prerequisite relation that was removed
	 * @throws PortalException if a prerequisite relation with the primary key could not be found
	 */
	@Override
	public com.ted.prerequisite.model.PrerequisiteRelation
			deletePrerequisiteRelation(long prerequisiteRelationId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _prerequisiteRelationLocalService.deletePrerequisiteRelation(
			prerequisiteRelationId);
	}

	/**
	 * Deletes the prerequisite relation from the database. Also notifies the appropriate model listeners.
	 *
	 * @param prerequisiteRelation the prerequisite relation
	 * @return the prerequisite relation that was removed
	 */
	@Override
	public com.ted.prerequisite.model.PrerequisiteRelation
		deletePrerequisiteRelation(
			com.ted.prerequisite.model.PrerequisiteRelation
				prerequisiteRelation) {

		return _prerequisiteRelationLocalService.deletePrerequisiteRelation(
			prerequisiteRelation);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _prerequisiteRelationLocalService.dynamicQuery();
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

		return _prerequisiteRelationLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.ted.prerequisite.model.impl.PrerequisiteRelationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _prerequisiteRelationLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.ted.prerequisite.model.impl.PrerequisiteRelationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _prerequisiteRelationLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
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

		return _prerequisiteRelationLocalService.dynamicQueryCount(
			dynamicQuery);
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

		return _prerequisiteRelationLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.ted.prerequisite.model.PrerequisiteRelation
		fetchPrerequisiteRelation(long prerequisiteRelationId) {

		return _prerequisiteRelationLocalService.fetchPrerequisiteRelation(
			prerequisiteRelationId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _prerequisiteRelationLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _prerequisiteRelationLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _prerequisiteRelationLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _prerequisiteRelationLocalService.getPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Returns the prerequisite relation with the primary key.
	 *
	 * @param prerequisiteRelationId the primary key of the prerequisite relation
	 * @return the prerequisite relation
	 * @throws PortalException if a prerequisite relation with the primary key could not be found
	 */
	@Override
	public com.ted.prerequisite.model.PrerequisiteRelation
			getPrerequisiteRelation(long prerequisiteRelationId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _prerequisiteRelationLocalService.getPrerequisiteRelation(
			prerequisiteRelationId);
	}

	@Override
	public java.util.List<com.ted.prerequisite.model.PrerequisiteRelation>
		getPrerequisiteRelation(
			long classNamePrerequisiteId, long classNameId, long classPK) {

		return _prerequisiteRelationLocalService.getPrerequisiteRelation(
			classNamePrerequisiteId, classNameId, classPK);
	}

	/**
	 * Returns a range of all the prerequisite relations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.ted.prerequisite.model.impl.PrerequisiteRelationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of prerequisite relations
	 * @param end the upper bound of the range of prerequisite relations (not inclusive)
	 * @return the range of prerequisite relations
	 */
	@Override
	public java.util.List<com.ted.prerequisite.model.PrerequisiteRelation>
		getPrerequisiteRelations(int start, int end) {

		return _prerequisiteRelationLocalService.getPrerequisiteRelations(
			start, end);
	}

	@Override
	public java.util.List<com.ted.prerequisite.model.PrerequisiteRelation>
		getPrerequisiteRelations(long classNameId, long classPK) {

		return _prerequisiteRelationLocalService.getPrerequisiteRelations(
			classNameId, classPK);
	}

	@Override
	public java.util.List<com.ted.prerequisite.model.PrerequisiteRelation>
		getPrerequisiteRelations(
			long classNameId, long classPK, int start, int end) {

		return _prerequisiteRelationLocalService.getPrerequisiteRelations(
			classNameId, classPK, start, end);
	}

	/**
	 * Returns the number of prerequisite relations.
	 *
	 * @return the number of prerequisite relations
	 */
	@Override
	public int getPrerequisiteRelationsCount() {
		return _prerequisiteRelationLocalService.
			getPrerequisiteRelationsCount();
	}

	@Override
	public int getPrerequisiteRelationsCount(long classNameId, long classPK) {
		return _prerequisiteRelationLocalService.getPrerequisiteRelationsCount(
			classNameId, classPK);
	}

	@Override
	public java.util.List<com.ted.prerequisite.model.Prerequisite>
		getPrerequisites(long classNameId, long classPK) {

		return _prerequisiteRelationLocalService.getPrerequisites(
			classNameId, classPK);
	}

	@Override
	public java.util.List<com.ted.prerequisite.model.Prerequisite>
		getPrerequisites(long classNameId, long classPK, int start, int end) {

		return _prerequisiteRelationLocalService.getPrerequisites(
			classNameId, classPK, start, end);
	}

	/**
	 * Updates the prerequisite relation in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param prerequisiteRelation the prerequisite relation
	 * @return the prerequisite relation that was updated
	 */
	@Override
	public com.ted.prerequisite.model.PrerequisiteRelation
		updatePrerequisiteRelation(
			com.ted.prerequisite.model.PrerequisiteRelation
				prerequisiteRelation) {

		return _prerequisiteRelationLocalService.updatePrerequisiteRelation(
			prerequisiteRelation);
	}

	@Override
	public PrerequisiteRelationLocalService getWrappedService() {
		return _prerequisiteRelationLocalService;
	}

	@Override
	public void setWrappedService(
		PrerequisiteRelationLocalService prerequisiteRelationLocalService) {

		_prerequisiteRelationLocalService = prerequisiteRelationLocalService;
	}

	private PrerequisiteRelationLocalService _prerequisiteRelationLocalService;

}