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

package com.ted.postcondition.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides a wrapper for {@link PostconditionRelationLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see PostconditionRelationLocalService
 * @generated
 */
@ProviderType
public class PostconditionRelationLocalServiceWrapper
	implements PostconditionRelationLocalService,
			   ServiceWrapper<PostconditionRelationLocalService> {

	public PostconditionRelationLocalServiceWrapper(
		PostconditionRelationLocalService postconditionRelationLocalService) {

		_postconditionRelationLocalService = postconditionRelationLocalService;
	}

	@Override
	public com.ted.postcondition.model.PostconditionRelation
		addPostconditionRelation(
			long classNamePostconditionId, long classNameId, long classPK,
			String extraData) {

		return _postconditionRelationLocalService.addPostconditionRelation(
			classNamePostconditionId, classNameId, classPK, extraData);
	}

	/**
	 * Adds the postcondition relation to the database. Also notifies the appropriate model listeners.
	 *
	 * @param postconditionRelation the postcondition relation
	 * @return the postcondition relation that was added
	 */
	@Override
	public com.ted.postcondition.model.PostconditionRelation
		addPostconditionRelation(
			com.ted.postcondition.model.PostconditionRelation
				postconditionRelation) {

		return _postconditionRelationLocalService.addPostconditionRelation(
			postconditionRelation);
	}

	/**
	 * Creates a new postcondition relation with the primary key. Does not add the postcondition relation to the database.
	 *
	 * @param postconditionRelationId the primary key for the new postcondition relation
	 * @return the new postcondition relation
	 */
	@Override
	public com.ted.postcondition.model.PostconditionRelation
		createPostconditionRelation(long postconditionRelationId) {

		return _postconditionRelationLocalService.createPostconditionRelation(
			postconditionRelationId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _postconditionRelationLocalService.deletePersistedModel(
			persistedModel);
	}

	/**
	 * Deletes the postcondition relation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param postconditionRelationId the primary key of the postcondition relation
	 * @return the postcondition relation that was removed
	 * @throws PortalException if a postcondition relation with the primary key could not be found
	 */
	@Override
	public com.ted.postcondition.model.PostconditionRelation
			deletePostconditionRelation(long postconditionRelationId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _postconditionRelationLocalService.deletePostconditionRelation(
			postconditionRelationId);
	}

	/**
	 * Deletes the postcondition relation from the database. Also notifies the appropriate model listeners.
	 *
	 * @param postconditionRelation the postcondition relation
	 * @return the postcondition relation that was removed
	 */
	@Override
	public com.ted.postcondition.model.PostconditionRelation
		deletePostconditionRelation(
			com.ted.postcondition.model.PostconditionRelation
				postconditionRelation) {

		return _postconditionRelationLocalService.deletePostconditionRelation(
			postconditionRelation);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _postconditionRelationLocalService.dynamicQuery();
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

		return _postconditionRelationLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.ted.postcondition.model.impl.PostconditionRelationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _postconditionRelationLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.ted.postcondition.model.impl.PostconditionRelationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _postconditionRelationLocalService.dynamicQuery(
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

		return _postconditionRelationLocalService.dynamicQueryCount(
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

		return _postconditionRelationLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.ted.postcondition.model.PostconditionRelation
		fetchPostconditionRelation(long postconditionRelationId) {

		return _postconditionRelationLocalService.fetchPostconditionRelation(
			postconditionRelationId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _postconditionRelationLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _postconditionRelationLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _postconditionRelationLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _postconditionRelationLocalService.getPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Returns the postcondition relation with the primary key.
	 *
	 * @param postconditionRelationId the primary key of the postcondition relation
	 * @return the postcondition relation
	 * @throws PortalException if a postcondition relation with the primary key could not be found
	 */
	@Override
	public com.ted.postcondition.model.PostconditionRelation
			getPostconditionRelation(long postconditionRelationId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _postconditionRelationLocalService.getPostconditionRelation(
			postconditionRelationId);
	}

	@Override
	public java.util.List<com.ted.postcondition.model.PostconditionRelation>
		getPostconditionRelation(
			long classNamePostconditionId, long classNameId, long classPK) {

		return _postconditionRelationLocalService.getPostconditionRelation(
			classNamePostconditionId, classNameId, classPK);
	}

	/**
	 * Returns a range of all the postcondition relations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.ted.postcondition.model.impl.PostconditionRelationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of postcondition relations
	 * @param end the upper bound of the range of postcondition relations (not inclusive)
	 * @return the range of postcondition relations
	 */
	@Override
	public java.util.List<com.ted.postcondition.model.PostconditionRelation>
		getPostconditionRelations(int start, int end) {

		return _postconditionRelationLocalService.getPostconditionRelations(
			start, end);
	}

	@Override
	public java.util.List<com.ted.postcondition.model.PostconditionRelation>
		getPostconditionRelations(long classNameId, long classPK) {

		return _postconditionRelationLocalService.getPostconditionRelations(
			classNameId, classPK);
	}

	@Override
	public java.util.List<com.ted.postcondition.model.PostconditionRelation>
		getPostconditionRelations(
			long classNameId, long classPK, int start, int end) {

		return _postconditionRelationLocalService.getPostconditionRelations(
			classNameId, classPK, start, end);
	}

	/**
	 * Returns the number of postcondition relations.
	 *
	 * @return the number of postcondition relations
	 */
	@Override
	public int getPostconditionRelationsCount() {
		return _postconditionRelationLocalService.
			getPostconditionRelationsCount();
	}

	@Override
	public int getPostconditionRelationsCount(long classNameId, long classPK) {
		return _postconditionRelationLocalService.
			getPostconditionRelationsCount(classNameId, classPK);
	}

	@Override
	public java.util.List<com.ted.postcondition.model.Postcondition>
		getPostconditions(long classNameId, long classPK) {

		return _postconditionRelationLocalService.getPostconditions(
			classNameId, classPK);
	}

	@Override
	public java.util.List<com.ted.postcondition.model.Postcondition>
		getPostconditions(long classNameId, long classPK, int start, int end) {

		return _postconditionRelationLocalService.getPostconditions(
			classNameId, classPK, start, end);
	}

	/**
	 * Updates the postcondition relation in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param postconditionRelation the postcondition relation
	 * @return the postcondition relation that was updated
	 */
	@Override
	public com.ted.postcondition.model.PostconditionRelation
		updatePostconditionRelation(
			com.ted.postcondition.model.PostconditionRelation
				postconditionRelation) {

		return _postconditionRelationLocalService.updatePostconditionRelation(
			postconditionRelation);
	}

	@Override
	public PostconditionRelationLocalService getWrappedService() {
		return _postconditionRelationLocalService;
	}

	@Override
	public void setWrappedService(
		PostconditionRelationLocalService postconditionRelationLocalService) {

		_postconditionRelationLocalService = postconditionRelationLocalService;
	}

	private PostconditionRelationLocalService
		_postconditionRelationLocalService;

}