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
 * Provides the local service utility for ModuleResult. This utility wraps
 * <code>com.ted.lms.service.impl.ModuleResultLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ModuleResultLocalService
 * @generated
 */
@ProviderType
public class ModuleResultLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.ted.lms.service.impl.ModuleResultLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.ted.lms.model.ModuleResult addModuleResult(
		long moduleId, long userId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		return getService().addModuleResult(moduleId, userId, serviceContext);
	}

	/**
	 * Adds the module result to the database. Also notifies the appropriate model listeners.
	 *
	 * @param moduleResult the module result
	 * @return the module result that was added
	 */
	public static com.ted.lms.model.ModuleResult addModuleResult(
		com.ted.lms.model.ModuleResult moduleResult) {

		return getService().addModuleResult(moduleResult);
	}

	public static int countModulesUserPassed(long groupId, long userId) {
		return getService().countModulesUserPassed(groupId, userId);
	}

	/**
	 * Creates a new module result with the primary key. Does not add the module result to the database.
	 *
	 * @param mrId the primary key for the new module result
	 * @return the new module result
	 */
	public static com.ted.lms.model.ModuleResult createModuleResult(long mrId) {
		return getService().createModuleResult(mrId);
	}

	/**
	 * Deletes the module result with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param mrId the primary key of the module result
	 * @return the module result that was removed
	 * @throws PortalException if a module result with the primary key could not be found
	 */
	public static com.ted.lms.model.ModuleResult deleteModuleResult(long mrId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteModuleResult(mrId);
	}

	/**
	 * Deletes the module result from the database. Also notifies the appropriate model listeners.
	 *
	 * @param moduleResult the module result
	 * @return the module result that was removed
	 */
	public static com.ted.lms.model.ModuleResult deleteModuleResult(
		com.ted.lms.model.ModuleResult moduleResult) {

		return getService().deleteModuleResult(moduleResult);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.ted.lms.model.impl.ModuleResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.ted.lms.model.impl.ModuleResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.ted.lms.model.ModuleResult fetchModuleResult(long mrId) {
		return getService().fetchModuleResult(mrId);
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
	 * Returns the module result with the primary key.
	 *
	 * @param mrId the primary key of the module result
	 * @return the module result
	 * @throws PortalException if a module result with the primary key could not be found
	 */
	public static com.ted.lms.model.ModuleResult getModuleResult(long mrId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getModuleResult(mrId);
	}

	public static com.ted.lms.model.ModuleResult getModuleResult(
		long moduleId, long userId) {

		return getService().getModuleResult(moduleId, userId);
	}

	/**
	 * Returns a range of all the module results.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.ted.lms.model.impl.ModuleResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of module results
	 * @param end the upper bound of the range of module results (not inclusive)
	 * @return the range of module results
	 */
	public static java.util.List<com.ted.lms.model.ModuleResult>
		getModuleResults(int start, int end) {

		return getService().getModuleResults(start, end);
	}

	public static java.util.List<com.ted.lms.model.ModuleResult>
		getModuleResults(long moduleId) {

		return getService().getModuleResults(moduleId);
	}

	/**
	 * Returns the number of module results.
	 *
	 * @return the number of module results
	 */
	public static int getModuleResultsCount() {
		return getService().getModuleResultsCount();
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

	public static com.ted.lms.model.ModuleResult recalculate(
			long userId, com.ted.lms.model.ModuleResult moduleResult)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().recalculate(userId, moduleResult);
	}

	public static com.ted.lms.model.ModuleResult updateModuleResult(
			com.ted.lms.model.LearningActivityResult learningActivityResult)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateModuleResult(learningActivityResult);
	}

	/**
	 * Updates the module result in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param moduleResult the module result
	 * @return the module result that was updated
	 */
	public static com.ted.lms.model.ModuleResult updateModuleResult(
		com.ted.lms.model.ModuleResult moduleResult) {

		return getService().updateModuleResult(moduleResult);
	}

	public static ModuleResultLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<ModuleResultLocalService, ModuleResultLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ModuleResultLocalService.class);

		ServiceTracker<ModuleResultLocalService, ModuleResultLocalService>
			serviceTracker =
				new ServiceTracker
					<ModuleResultLocalService, ModuleResultLocalService>(
						bundle.getBundleContext(),
						ModuleResultLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}