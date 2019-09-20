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

import org.osgi.annotation.versioning.ProviderType;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for PrerequisiteRelation. This utility wraps
 * <code>com.ted.prerequisite.service.impl.PrerequisiteRelationLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see PrerequisiteRelationLocalService
 * @generated
 */
@ProviderType
public class PrerequisiteRelationLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.ted.prerequisite.service.impl.PrerequisiteRelationLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.ted.prerequisite.model.PrerequisiteRelation
		addPrerequisiteRelation(
			long classNamePrerequisiteId, long classNameId, long classPK,
			String extraData) {

		return getService().addPrerequisiteRelation(
			classNamePrerequisiteId, classNameId, classPK, extraData);
	}

	/**
	 * Adds the prerequisite relation to the database. Also notifies the appropriate model listeners.
	 *
	 * @param prerequisiteRelation the prerequisite relation
	 * @return the prerequisite relation that was added
	 */
	public static com.ted.prerequisite.model.PrerequisiteRelation
		addPrerequisiteRelation(
			com.ted.prerequisite.model.PrerequisiteRelation
				prerequisiteRelation) {

		return getService().addPrerequisiteRelation(prerequisiteRelation);
	}

	/**
	 * Creates a new prerequisite relation with the primary key. Does not add the prerequisite relation to the database.
	 *
	 * @param prerequisiteRelationId the primary key for the new prerequisite relation
	 * @return the new prerequisite relation
	 */
	public static com.ted.prerequisite.model.PrerequisiteRelation
		createPrerequisiteRelation(long prerequisiteRelationId) {

		return getService().createPrerequisiteRelation(prerequisiteRelationId);
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

	/**
	 * Deletes the prerequisite relation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param prerequisiteRelationId the primary key of the prerequisite relation
	 * @return the prerequisite relation that was removed
	 * @throws PortalException if a prerequisite relation with the primary key could not be found
	 */
	public static com.ted.prerequisite.model.PrerequisiteRelation
			deletePrerequisiteRelation(long prerequisiteRelationId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deletePrerequisiteRelation(prerequisiteRelationId);
	}

	/**
	 * Deletes the prerequisite relation from the database. Also notifies the appropriate model listeners.
	 *
	 * @param prerequisiteRelation the prerequisite relation
	 * @return the prerequisite relation that was removed
	 */
	public static com.ted.prerequisite.model.PrerequisiteRelation
		deletePrerequisiteRelation(
			com.ted.prerequisite.model.PrerequisiteRelation
				prerequisiteRelation) {

		return getService().deletePrerequisiteRelation(prerequisiteRelation);
	}

	public static void deletePrerequisiteRelations(
		long classNamePrerequisiteId, long classNameId, long classPK) {

		getService().deletePrerequisiteRelations(
			classNamePrerequisiteId, classNameId, classPK);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.ted.prerequisite.model.impl.PrerequisiteRelationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.ted.prerequisite.model.impl.PrerequisiteRelationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.ted.prerequisite.model.PrerequisiteRelation
		fetchPrerequisiteRelation(long prerequisiteRelationId) {

		return getService().fetchPrerequisiteRelation(prerequisiteRelationId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
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
	 * Returns the prerequisite relation with the primary key.
	 *
	 * @param prerequisiteRelationId the primary key of the prerequisite relation
	 * @return the prerequisite relation
	 * @throws PortalException if a prerequisite relation with the primary key could not be found
	 */
	public static com.ted.prerequisite.model.PrerequisiteRelation
			getPrerequisiteRelation(long prerequisiteRelationId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPrerequisiteRelation(prerequisiteRelationId);
	}

	public static java.util.List
		<com.ted.prerequisite.model.PrerequisiteRelation>
			getPrerequisiteRelation(
				long classNamePrerequisiteId, long classNameId, long classPK) {

		return getService().getPrerequisiteRelation(
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
	public static java.util.List
		<com.ted.prerequisite.model.PrerequisiteRelation>
			getPrerequisiteRelations(int start, int end) {

		return getService().getPrerequisiteRelations(start, end);
	}

	public static java.util.List
		<com.ted.prerequisite.model.PrerequisiteRelation>
			getPrerequisiteRelations(long classNameId, long classPK) {

		return getService().getPrerequisiteRelations(classNameId, classPK);
	}

	public static java.util.List
		<com.ted.prerequisite.model.PrerequisiteRelation>
			getPrerequisiteRelations(
				long classNameId, long classPK, int start, int end) {

		return getService().getPrerequisiteRelations(
			classNameId, classPK, start, end);
	}

	/**
	 * Returns the number of prerequisite relations.
	 *
	 * @return the number of prerequisite relations
	 */
	public static int getPrerequisiteRelationsCount() {
		return getService().getPrerequisiteRelationsCount();
	}

	public static int getPrerequisiteRelationsCount(
		long classNameId, long classPK) {

		return getService().getPrerequisiteRelationsCount(classNameId, classPK);
	}

	public static java.util.List<com.ted.prerequisite.model.Prerequisite>
		getPrerequisites(long classNameId, long classPK) {

		return getService().getPrerequisites(classNameId, classPK);
	}

	public static java.util.List<com.ted.prerequisite.model.Prerequisite>
		getPrerequisites(long classNameId, long classPK, int start, int end) {

		return getService().getPrerequisites(classNameId, classPK, start, end);
	}

	/**
	 * Updates the prerequisite relation in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param prerequisiteRelation the prerequisite relation
	 * @return the prerequisite relation that was updated
	 */
	public static com.ted.prerequisite.model.PrerequisiteRelation
		updatePrerequisiteRelation(
			com.ted.prerequisite.model.PrerequisiteRelation
				prerequisiteRelation) {

		return getService().updatePrerequisiteRelation(prerequisiteRelation);
	}

	public static PrerequisiteRelationLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<PrerequisiteRelationLocalService, PrerequisiteRelationLocalService>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			PrerequisiteRelationLocalService.class);

		ServiceTracker
			<PrerequisiteRelationLocalService, PrerequisiteRelationLocalService>
				serviceTracker =
					new ServiceTracker
						<PrerequisiteRelationLocalService,
						 PrerequisiteRelationLocalService>(
							 bundle.getBundleContext(),
							 PrerequisiteRelationLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}