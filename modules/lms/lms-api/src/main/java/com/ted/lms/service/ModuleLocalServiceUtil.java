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
 * Provides the local service utility for Module. This utility wraps
 * {@link com.ted.lms.service.impl.ModuleLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ModuleLocalService
 * @see com.ted.lms.service.base.ModuleLocalServiceBaseImpl
 * @see com.ted.lms.service.impl.ModuleLocalServiceImpl
 * @generated
 */
@ProviderType
public class ModuleLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.ted.lms.service.impl.ModuleLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.ted.lms.model.Module addModule(long userId,
		java.util.Map<java.util.Locale, String> titleMap,
		java.util.Map<java.util.Locale, String> descriptionMap,
		boolean useStartExecutionDateCourse, int startDateMonth,
		int startDateDay, int startDateYear, int startDateHour,
		int startDateMinute, boolean useEndExecutionDateCourse,
		int endDateMonth, int endDateDay, int endDateYear, int endDateHour,
		int endDateMinute, int allowedHours, int allowedMinutes,
		com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector smallImageImageSelector,
		long moduleEvalId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addModule(userId, titleMap, descriptionMap,
			useStartExecutionDateCourse, startDateMonth, startDateDay,
			startDateYear, startDateHour, startDateMinute,
			useEndExecutionDateCourse, endDateMonth, endDateDay, endDateYear,
			endDateHour, endDateMinute, allowedHours, allowedMinutes,
			smallImageImageSelector, moduleEvalId, serviceContext);
	}

	public static com.ted.lms.model.Module addModule(long userId,
		java.util.Map<java.util.Locale, String> titleMap,
		java.util.Map<java.util.Locale, String> descriptionMap,
		java.util.Date startDate, java.util.Date endDate, long allowedTime,
		com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector smallImageImageSelector,
		long moduleEvalId, String moduleExtraData,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addModule(userId, titleMap, descriptionMap, startDate,
			endDate, allowedTime, smallImageImageSelector, moduleEvalId,
			moduleExtraData, serviceContext);
	}

	/**
	* Adds the module to the database. Also notifies the appropriate model listeners.
	*
	* @param module the module
	* @return the module that was added
	*/
	public static com.ted.lms.model.Module addModule(
		com.ted.lms.model.Module module) {
		return getService().addModule(module);
	}

	public static long addOriginalImageFileEntry(long userId, long groupId,
		long entryId,
		com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector imageSelector)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addOriginalImageFileEntry(userId, groupId, entryId,
			imageSelector);
	}

	/**
	* Creates a new module with the primary key. Does not add the module to the database.
	*
	* @param moduleId the primary key for the new module
	* @return the new module
	*/
	public static com.ted.lms.model.Module createModule(long moduleId) {
		return getService().createModule(moduleId);
	}

	/**
	* Deletes the module with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param moduleId the primary key of the module
	* @return the module that was removed
	* @throws PortalException if a module with the primary key could not be found
	*/
	public static com.ted.lms.model.Module deleteModule(long moduleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteModule(moduleId);
	}

	/**
	* Deletes the module from the database. Also notifies the appropriate model listeners.
	*
	* @param module the module
	* @return the module that was removed
	*/
	public static com.ted.lms.model.Module deleteModule(
		com.ted.lms.model.Module module) {
		return getService().deleteModule(module);
	}

	public static void deleteModules(long groupId) {
		getService().deleteModules(groupId);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ted.lms.model.impl.ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ted.lms.model.impl.ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.ted.lms.model.Module fetchModule(long moduleId) {
		return getService().fetchModule(moduleId);
	}

	/**
	* Returns the module matching the UUID and group.
	*
	* @param uuid the module's UUID
	* @param groupId the primary key of the group
	* @return the matching module, or <code>null</code> if a matching module could not be found
	*/
	public static com.ted.lms.model.Module fetchModuleByUuidAndGroupId(
		String uuid, long groupId) {
		return getService().fetchModuleByUuidAndGroupId(uuid, groupId);
	}

	public static java.util.List<com.ted.lms.model.Module> findAllInGroup(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().findAllInGroup(groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the module with the primary key.
	*
	* @param moduleId the primary key of the module
	* @return the module
	* @throws PortalException if a module with the primary key could not be found
	*/
	public static com.ted.lms.model.Module getModule(long moduleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getModule(moduleId);
	}

	/**
	* Returns the module matching the UUID and group.
	*
	* @param uuid the module's UUID
	* @param groupId the primary key of the group
	* @return the matching module
	* @throws PortalException if a matching module could not be found
	*/
	public static com.ted.lms.model.Module getModuleByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getModuleByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the modules.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ted.lms.model.impl.ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of modules
	* @param end the upper bound of the range of modules (not inclusive)
	* @return the range of modules
	*/
	public static java.util.List<com.ted.lms.model.Module> getModules(
		int start, int end) {
		return getService().getModules(start, end);
	}

	public static java.util.List<com.ted.lms.model.Module> getModules(
		long groupId, int start, int end) {
		return getService().getModules(groupId, start, end);
	}

	/**
	* Returns all the modules matching the UUID and company.
	*
	* @param uuid the UUID of the modules
	* @param companyId the primary key of the company
	* @return the matching modules, or an empty list if no matches were found
	*/
	public static java.util.List<com.ted.lms.model.Module> getModulesByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService().getModulesByUuidAndCompanyId(uuid, companyId);
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
	public static java.util.List<com.ted.lms.model.Module> getModulesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.ted.lms.model.Module> orderByComparator) {
		return getService()
				   .getModulesByUuidAndCompanyId(uuid, companyId, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of modules.
	*
	* @return the number of modules
	*/
	public static int getModulesCount() {
		return getService().getModulesCount();
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

	public static com.ted.lms.model.Module moveModuleToTrash(long userId,
		long moduleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().moveModuleToTrash(userId, moduleId);
	}

	public static com.ted.lms.model.Module moveModuleToTrash(long userId,
		com.ted.lms.model.Module module)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().moveModuleToTrash(userId, module);
	}

	public static com.ted.lms.model.Module updateModule(long userId,
		long moduleId, java.util.Map<java.util.Locale, String> titleMap,
		java.util.Map<java.util.Locale, String> descriptionMap,
		boolean useStartExecutionDateCourse, int startDateMonth,
		int startDateDay, int startDateYear, int startDateHour,
		int startDateMinute, boolean useEndExecutionDateCourse,
		int endDateMonth, int endDateDay, int endDateYear, int endDateHour,
		int endDateMinute, int allowedHours, int allowedMinutes,
		com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector smallImageImageSelector,
		long moduleEvalId, String moduleExtraData,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateModule(userId, moduleId, titleMap, descriptionMap,
			useStartExecutionDateCourse, startDateMonth, startDateDay,
			startDateYear, startDateHour, startDateMinute,
			useEndExecutionDateCourse, endDateMonth, endDateDay, endDateYear,
			endDateHour, endDateMinute, allowedHours, allowedMinutes,
			smallImageImageSelector, moduleEvalId, moduleExtraData,
			serviceContext);
	}

	public static com.ted.lms.model.Module updateModule(long userId,
		long moduleId, java.util.Map<java.util.Locale, String> titleMap,
		java.util.Map<java.util.Locale, String> descriptionMap,
		java.util.Date startDate, java.util.Date endDate, long allowedTime,
		com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector smallImageImageSelector,
		long moduleEvalId, String moduleExtraData,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateModule(userId, moduleId, titleMap, descriptionMap,
			startDate, endDate, allowedTime, smallImageImageSelector,
			moduleEvalId, moduleExtraData, serviceContext);
	}

	/**
	* Updates the module in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param module the module
	* @return the module that was updated
	*/
	public static com.ted.lms.model.Module updateModule(
		com.ted.lms.model.Module module) {
		return getService().updateModule(module);
	}

	public static com.ted.lms.model.Module updateOrder(
		com.ted.lms.model.Module module, long order) {
		return getService().updateOrder(module, order);
	}

	public static com.ted.lms.model.Module updateStatus(long userId,
		long moduleId, int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext,
		java.util.Map<String, java.io.Serializable> workflowContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateStatus(userId, moduleId, status, serviceContext,
			workflowContext);
	}

	public static ModuleLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ModuleLocalService, ModuleLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ModuleLocalService.class);

		ServiceTracker<ModuleLocalService, ModuleLocalService> serviceTracker = new ServiceTracker<ModuleLocalService, ModuleLocalService>(bundle.getBundleContext(),
				ModuleLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}