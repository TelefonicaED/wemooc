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

import com.liferay.portal.kernel.service.ServiceWrapper;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides a wrapper for {@link ModuleLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ModuleLocalService
 * @generated
 */
@ProviderType
public class ModuleLocalServiceWrapper
	implements ModuleLocalService, ServiceWrapper<ModuleLocalService> {

	public ModuleLocalServiceWrapper(ModuleLocalService moduleLocalService) {
		_moduleLocalService = moduleLocalService;
	}

	@Override
	public com.ted.lms.model.Module addModule(
			long userId, long groupId,
			java.util.Map<java.util.Locale, String> titleMap,
			java.util.Map<java.util.Locale, String> descriptionMap,
			boolean useStartExecutionDateCourse, int startDateMonth,
			int startDateDay, int startDateYear, int startDateHour,
			int startDateMinute, boolean useEndExecutionDateCourse,
			int endDateMonth, int endDateDay, int endDateYear, int endDateHour,
			int endDateMinute, int allowedHours, int allowedMinutes,
			com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector
				smallImageImageSelector,
			long moduleEvalId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _moduleLocalService.addModule(
			userId, groupId, titleMap, descriptionMap,
			useStartExecutionDateCourse, startDateMonth, startDateDay,
			startDateYear, startDateHour, startDateMinute,
			useEndExecutionDateCourse, endDateMonth, endDateDay, endDateYear,
			endDateHour, endDateMinute, allowedHours, allowedMinutes,
			smallImageImageSelector, moduleEvalId, serviceContext);
	}

	@Override
	public com.ted.lms.model.Module addModule(
			long userId, long groupId,
			java.util.Map<java.util.Locale, String> titleMap,
			java.util.Map<java.util.Locale, String> descriptionMap,
			java.util.Date startDate, java.util.Date endDate, long allowedTime,
			com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector
				smallImageImageSelector,
			long moduleEvalId, String moduleExtraData,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _moduleLocalService.addModule(
			userId, groupId, titleMap, descriptionMap, startDate, endDate,
			allowedTime, smallImageImageSelector, moduleEvalId, moduleExtraData,
			serviceContext);
	}

	/**
	 * Adds the module to the database. Also notifies the appropriate model listeners.
	 *
	 * @param module the module
	 * @return the module that was added
	 */
	@Override
	public com.ted.lms.model.Module addModule(com.ted.lms.model.Module module) {
		return _moduleLocalService.addModule(module);
	}

	@Override
	public com.liferay.portal.kernel.repository.model.Folder addModuleFolder(
		long userId, long repositoryId) {

		return _moduleLocalService.addModuleFolder(userId, repositoryId);
	}

	@Override
	public long addOriginalImageFileEntry(
			long userId, long groupId, long entryId,
			com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector
				imageSelector)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _moduleLocalService.addOriginalImageFileEntry(
			userId, groupId, entryId, imageSelector);
	}

	@Override
	public com.ted.lms.model.Module copyModule(
			long userId, com.ted.lms.model.Module oldModule, long groupId,
			java.util.Map<Long, Long> activitiesRelation,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws Exception {

		return _moduleLocalService.copyModule(
			userId, oldModule, groupId, activitiesRelation, serviceContext);
	}

	/**
	 * Creates a new module with the primary key. Does not add the module to the database.
	 *
	 * @param moduleId the primary key for the new module
	 * @return the new module
	 */
	@Override
	public com.ted.lms.model.Module createModule(long moduleId) {
		return _moduleLocalService.createModule(moduleId);
	}

	/**
	 * Deletes the module with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param moduleId the primary key of the module
	 * @return the module that was removed
	 * @throws PortalException if a module with the primary key could not be found
	 */
	@Override
	public com.ted.lms.model.Module deleteModule(long moduleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _moduleLocalService.deleteModule(moduleId);
	}

	/**
	 * Deletes the module from the database. Also notifies the appropriate model listeners.
	 *
	 * @param module the module
	 * @return the module that was removed
	 */
	@Override
	public com.ted.lms.model.Module deleteModule(
		com.ted.lms.model.Module module) {

		return _moduleLocalService.deleteModule(module);
	}

	@Override
	public void deleteModules(long groupId) {
		_moduleLocalService.deleteModules(groupId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _moduleLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _moduleLocalService.dynamicQuery();
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

		return _moduleLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.ted.lms.model.impl.ModuleModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _moduleLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.ted.lms.model.impl.ModuleModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _moduleLocalService.dynamicQuery(
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

		return _moduleLocalService.dynamicQueryCount(dynamicQuery);
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

		return _moduleLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.ted.lms.model.Module fetchModule(long moduleId) {
		return _moduleLocalService.fetchModule(moduleId);
	}

	/**
	 * Returns the module matching the UUID and group.
	 *
	 * @param uuid the module's UUID
	 * @param groupId the primary key of the group
	 * @return the matching module, or <code>null</code> if a matching module could not be found
	 */
	@Override
	public com.ted.lms.model.Module fetchModuleByUuidAndGroupId(
		String uuid, long groupId) {

		return _moduleLocalService.fetchModuleByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public java.util.List<com.ted.lms.model.Module> findAllInGroup(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _moduleLocalService.findAllInGroup(groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _moduleLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _moduleLocalService.getExportActionableDynamicQuery(
			portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _moduleLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the module with the primary key.
	 *
	 * @param moduleId the primary key of the module
	 * @return the module
	 * @throws PortalException if a module with the primary key could not be found
	 */
	@Override
	public com.ted.lms.model.Module getModule(long moduleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _moduleLocalService.getModule(moduleId);
	}

	/**
	 * Returns the module matching the UUID and group.
	 *
	 * @param uuid the module's UUID
	 * @param groupId the primary key of the group
	 * @return the matching module
	 * @throws PortalException if a matching module could not be found
	 */
	@Override
	public com.ted.lms.model.Module getModuleByUuidAndGroupId(
			String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _moduleLocalService.getModuleByUuidAndGroupId(uuid, groupId);
	}

	/**
	 * Returns a range of all the modules.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.ted.lms.model.impl.ModuleModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @return the range of modules
	 */
	@Override
	public java.util.List<com.ted.lms.model.Module> getModules(
		int start, int end) {

		return _moduleLocalService.getModules(start, end);
	}

	@Override
	public java.util.List<com.ted.lms.model.Module> getModules(
		long groupId, int start, int end) {

		return _moduleLocalService.getModules(groupId, start, end);
	}

	/**
	 * Returns all the modules matching the UUID and company.
	 *
	 * @param uuid the UUID of the modules
	 * @param companyId the primary key of the company
	 * @return the matching modules, or an empty list if no matches were found
	 */
	@Override
	public java.util.List<com.ted.lms.model.Module>
		getModulesByUuidAndCompanyId(String uuid, long companyId) {

		return _moduleLocalService.getModulesByUuidAndCompanyId(
			uuid, companyId);
	}

	/**
	 * Returns a range of modules matching the UUID and company.
	 *
	 * @param uuid the UUID of the modules
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching modules, or an empty list if no matches were found
	 */
	@Override
	public java.util.List<com.ted.lms.model.Module>
		getModulesByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.ted.lms.model.Module> orderByComparator) {

		return _moduleLocalService.getModulesByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of modules.
	 *
	 * @return the number of modules
	 */
	@Override
	public int getModulesCount() {
		return _moduleLocalService.getModulesCount();
	}

	@Override
	public int getModulesCount(long groupId) {
		return _moduleLocalService.getModulesCount(groupId);
	}

	@Override
	public com.ted.lms.model.Module getNextModule(
			com.ted.lms.model.Module module)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _moduleLocalService.getNextModule(module);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _moduleLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _moduleLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public String[] getPrerequisiteModules(long companyId) {
		return _moduleLocalService.getPrerequisiteModules(companyId);
	}

	@Override
	public com.ted.lms.model.Module getPreviousModule(
			com.ted.lms.model.Module module)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _moduleLocalService.getPreviousModule(module);
	}

	@Override
	public com.ted.lms.model.Module moveDownModule(long userId, long moduleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _moduleLocalService.moveDownModule(userId, moduleId);
	}

	@Override
	public com.ted.lms.model.Module moveModuleToTrash(
			long userId, long moduleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _moduleLocalService.moveModuleToTrash(userId, moduleId);
	}

	@Override
	public com.ted.lms.model.Module moveModuleToTrash(
			long userId, com.ted.lms.model.Module module)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _moduleLocalService.moveModuleToTrash(userId, module);
	}

	@Override
	public com.ted.lms.model.Module moveUpModule(long userId, long moduleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _moduleLocalService.moveUpModule(userId, moduleId);
	}

	@Override
	public void updateAsset(
			long userId, com.ted.lms.model.Module module,
			long[] assetCategoryIds, String[] assetTagNames,
			long[] assetLinkEntryIds, Double priority)
		throws com.liferay.portal.kernel.exception.PortalException {

		_moduleLocalService.updateAsset(
			userId, module, assetCategoryIds, assetTagNames, assetLinkEntryIds,
			priority);
	}

	@Override
	public com.ted.lms.model.Module updateModule(
			long userId, long moduleId,
			java.util.Map<java.util.Locale, String> titleMap,
			java.util.Map<java.util.Locale, String> descriptionMap,
			boolean useStartExecutionDateCourse, int startDateMonth,
			int startDateDay, int startDateYear, int startDateHour,
			int startDateMinute, boolean useEndExecutionDateCourse,
			int endDateMonth, int endDateDay, int endDateYear, int endDateHour,
			int endDateMinute, int allowedHours, int allowedMinutes,
			com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector
				smallImageImageSelector,
			long moduleEvalId, String moduleExtraData,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _moduleLocalService.updateModule(
			userId, moduleId, titleMap, descriptionMap,
			useStartExecutionDateCourse, startDateMonth, startDateDay,
			startDateYear, startDateHour, startDateMinute,
			useEndExecutionDateCourse, endDateMonth, endDateDay, endDateYear,
			endDateHour, endDateMinute, allowedHours, allowedMinutes,
			smallImageImageSelector, moduleEvalId, moduleExtraData,
			serviceContext);
	}

	@Override
	public com.ted.lms.model.Module updateModule(
			long userId, long moduleId,
			java.util.Map<java.util.Locale, String> titleMap,
			java.util.Map<java.util.Locale, String> descriptionMap,
			java.util.Date startDate, java.util.Date endDate, long allowedTime,
			com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector
				smallImageImageSelector,
			long moduleEvalId, String moduleExtraData,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _moduleLocalService.updateModule(
			userId, moduleId, titleMap, descriptionMap, startDate, endDate,
			allowedTime, smallImageImageSelector, moduleEvalId, moduleExtraData,
			serviceContext);
	}

	/**
	 * Updates the module in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param module the module
	 * @return the module that was updated
	 */
	@Override
	public com.ted.lms.model.Module updateModule(
		com.ted.lms.model.Module module) {

		return _moduleLocalService.updateModule(module);
	}

	@Override
	public com.ted.lms.model.Module updateOrder(
			long userId, com.ted.lms.model.Module module, long priority)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _moduleLocalService.updateOrder(userId, module, priority);
	}

	@Override
	public com.ted.lms.model.Module updateStatus(
			long userId, long moduleId, int status,
			com.liferay.portal.kernel.service.ServiceContext serviceContext,
			java.util.Map<String, java.io.Serializable> workflowContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _moduleLocalService.updateStatus(
			userId, moduleId, status, serviceContext, workflowContext);
	}

	@Override
	public ModuleLocalService getWrappedService() {
		return _moduleLocalService;
	}

	@Override
	public void setWrappedService(ModuleLocalService moduleLocalService) {
		_moduleLocalService = moduleLocalService;
	}

	private ModuleLocalService _moduleLocalService;

}