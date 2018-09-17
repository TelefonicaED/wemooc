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
 * Provides a wrapper for {@link ModuleResultLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ModuleResultLocalService
 * @generated
 */
@ProviderType
public class ModuleResultLocalServiceWrapper implements ModuleResultLocalService,
	ServiceWrapper<ModuleResultLocalService> {
	public ModuleResultLocalServiceWrapper(
		ModuleResultLocalService moduleResultLocalService) {
		_moduleResultLocalService = moduleResultLocalService;
	}

	@Override
	public com.ted.lms.model.ModuleResult addModuleResult(long moduleId,
		long userId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {
		return _moduleResultLocalService.addModuleResult(moduleId, userId,
			serviceContext);
	}

	/**
	* Adds the module result to the database. Also notifies the appropriate model listeners.
	*
	* @param moduleResult the module result
	* @return the module result that was added
	*/
	@Override
	public com.ted.lms.model.ModuleResult addModuleResult(
		com.ted.lms.model.ModuleResult moduleResult) {
		return _moduleResultLocalService.addModuleResult(moduleResult);
	}

	@Override
	public int countModulesUserPassed(long groupId, long userId) {
		return _moduleResultLocalService.countModulesUserPassed(groupId, userId);
	}

	/**
	* Creates a new module result with the primary key. Does not add the module result to the database.
	*
	* @param mrId the primary key for the new module result
	* @return the new module result
	*/
	@Override
	public com.ted.lms.model.ModuleResult createModuleResult(long mrId) {
		return _moduleResultLocalService.createModuleResult(mrId);
	}

	/**
	* Deletes the module result with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param mrId the primary key of the module result
	* @return the module result that was removed
	* @throws PortalException if a module result with the primary key could not be found
	*/
	@Override
	public com.ted.lms.model.ModuleResult deleteModuleResult(long mrId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _moduleResultLocalService.deleteModuleResult(mrId);
	}

	/**
	* Deletes the module result from the database. Also notifies the appropriate model listeners.
	*
	* @param moduleResult the module result
	* @return the module result that was removed
	*/
	@Override
	public com.ted.lms.model.ModuleResult deleteModuleResult(
		com.ted.lms.model.ModuleResult moduleResult) {
		return _moduleResultLocalService.deleteModuleResult(moduleResult);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _moduleResultLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _moduleResultLocalService.dynamicQuery();
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
		return _moduleResultLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ted.lms.model.impl.ModuleResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _moduleResultLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ted.lms.model.impl.ModuleResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _moduleResultLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _moduleResultLocalService.dynamicQueryCount(dynamicQuery);
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
		return _moduleResultLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.ted.lms.model.ModuleResult fetchModuleResult(long mrId) {
		return _moduleResultLocalService.fetchModuleResult(mrId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _moduleResultLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _moduleResultLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the module result with the primary key.
	*
	* @param mrId the primary key of the module result
	* @return the module result
	* @throws PortalException if a module result with the primary key could not be found
	*/
	@Override
	public com.ted.lms.model.ModuleResult getModuleResult(long mrId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _moduleResultLocalService.getModuleResult(mrId);
	}

	@Override
	public com.ted.lms.model.ModuleResult getModuleResult(long moduleId,
		long userId) {
		return _moduleResultLocalService.getModuleResult(moduleId, userId);
	}

	/**
	* Returns a range of all the module results.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ted.lms.model.impl.ModuleResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of module results
	* @param end the upper bound of the range of module results (not inclusive)
	* @return the range of module results
	*/
	@Override
	public java.util.List<com.ted.lms.model.ModuleResult> getModuleResults(
		int start, int end) {
		return _moduleResultLocalService.getModuleResults(start, end);
	}

	@Override
	public java.util.List<com.ted.lms.model.ModuleResult> getModuleResults(
		long moduleId) {
		return _moduleResultLocalService.getModuleResults(moduleId);
	}

	/**
	* Returns the number of module results.
	*
	* @return the number of module results
	*/
	@Override
	public int getModuleResultsCount() {
		return _moduleResultLocalService.getModuleResultsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _moduleResultLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _moduleResultLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public com.ted.lms.model.ModuleResult updateModuleResult(
		com.ted.lms.model.LearningActivityResult learningActivityResult,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {
		return _moduleResultLocalService.updateModuleResult(learningActivityResult,
			serviceContext);
	}

	/**
	* Updates the module result in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param moduleResult the module result
	* @return the module result that was updated
	*/
	@Override
	public com.ted.lms.model.ModuleResult updateModuleResult(
		com.ted.lms.model.ModuleResult moduleResult) {
		return _moduleResultLocalService.updateModuleResult(moduleResult);
	}

	@Override
	public ModuleResultLocalService getWrappedService() {
		return _moduleResultLocalService;
	}

	@Override
	public void setWrappedService(
		ModuleResultLocalService moduleResultLocalService) {
		_moduleResultLocalService = moduleResultLocalService;
	}

	private ModuleResultLocalService _moduleResultLocalService;
}