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

import com.liferay.exportimport.kernel.lar.PortletDataContext;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.ted.lms.model.Module;

import java.io.Serializable;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Provides the local service interface for Module. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see ModuleLocalServiceUtil
 * @see com.ted.lms.service.base.ModuleLocalServiceBaseImpl
 * @see com.ted.lms.service.impl.ModuleLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface ModuleLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ModuleLocalServiceUtil} to access the module local service. Add custom service methods to {@link com.ted.lms.service.impl.ModuleLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public Module addModule(long userId, Map<Locale, String> titleMap,
		Map<Locale, String> descriptionMap,
		boolean useStartExecutionDateCourse, int startDateMonth,
		int startDateDay, int startDateYear, int startDateHour,
		int startDateMinute, boolean useEndExecutionDateCourse,
		int endDateMonth, int endDateDay, int endDateYear, int endDateHour,
		int endDateMinute, int allowedHours, int allowedMinutes,
		ImageSelector smallImageImageSelector, long moduleEvalId,
		ServiceContext serviceContext) throws PortalException;

	@Indexable(type = IndexableType.REINDEX)
	public Module addModule(long userId, Map<Locale, String> titleMap,
		Map<Locale, String> descriptionMap, Date startDate, Date endDate,
		long allowedTime, ImageSelector smallImageImageSelector,
		long moduleEvalId, String moduleExtraData, ServiceContext serviceContext)
		throws PortalException;

	/**
	* Adds the module to the database. Also notifies the appropriate model listeners.
	*
	* @param module the module
	* @return the module that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public Module addModule(Module module);

	public long addOriginalImageFileEntry(long userId, long groupId,
		long entryId, ImageSelector imageSelector) throws PortalException;

	/**
	* Creates a new module with the primary key. Does not add the module to the database.
	*
	* @param moduleId the primary key for the new module
	* @return the new module
	*/
	@Transactional(enabled = false)
	public Module createModule(long moduleId);

	/**
	* Deletes the module with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param moduleId the primary key of the module
	* @return the module that was removed
	* @throws PortalException if a module with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public Module deleteModule(long moduleId) throws PortalException;

	/**
	* Deletes the module from the database. Also notifies the appropriate model listeners.
	*
	* @param module the module
	* @return the module that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public Module deleteModule(Module module);

	public void deleteModules(long groupId);

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	public DynamicQuery dynamicQuery();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

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
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

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
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Module fetchModule(long moduleId);

	/**
	* Returns the module matching the UUID and group.
	*
	* @param uuid the module's UUID
	* @param groupId the primary key of the group
	* @return the matching module, or <code>null</code> if a matching module could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Module fetchModuleByUuidAndGroupId(String uuid, long groupId);

	public List<Module> findAllInGroup(long groupId) throws SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* Returns the module with the primary key.
	*
	* @param moduleId the primary key of the module
	* @return the module
	* @throws PortalException if a module with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Module getModule(long moduleId) throws PortalException;

	/**
	* Returns the module matching the UUID and group.
	*
	* @param uuid the module's UUID
	* @param groupId the primary key of the group
	* @return the matching module
	* @throws PortalException if a matching module could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Module getModuleByUuidAndGroupId(String uuid, long groupId)
		throws PortalException;

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Module> getModules(int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Module> getModules(long groupId, int start, int end);

	/**
	* Returns all the modules matching the UUID and company.
	*
	* @param uuid the UUID of the modules
	* @param companyId the primary key of the company
	* @return the matching modules, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Module> getModulesByUuidAndCompanyId(String uuid, long companyId);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Module> getModulesByUuidAndCompanyId(String uuid,
		long companyId, int start, int end,
		OrderByComparator<Module> orderByComparator);

	/**
	* Returns the number of modules.
	*
	* @return the number of modules
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getModulesCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Module getNextModule(Module module) throws PortalException;

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public String getOSGiServiceIdentifier();

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Module getPreviousModule(Module module) throws PortalException;

	public Module moveDownModule(long moduleId, ServiceContext serviceContext)
		throws PortalException;

	public Module moveModuleToTrash(long userId, long moduleId)
		throws PortalException;

	@Indexable(type = IndexableType.REINDEX)
	public Module moveModuleToTrash(long userId, Module module)
		throws PortalException;

	public Module moveUpModule(long moduleId, ServiceContext serviceContext)
		throws PortalException;

	public void updateAsset(long userId, Module module,
		long[] assetCategoryIds, String[] assetTagNames,
		long[] assetLinkEntryIds, Double priority) throws PortalException;

	public Module updateModule(long userId, long moduleId,
		Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
		boolean useStartExecutionDateCourse, int startDateMonth,
		int startDateDay, int startDateYear, int startDateHour,
		int startDateMinute, boolean useEndExecutionDateCourse,
		int endDateMonth, int endDateDay, int endDateYear, int endDateHour,
		int endDateMinute, int allowedHours, int allowedMinutes,
		ImageSelector smallImageImageSelector, long moduleEvalId,
		String moduleExtraData, ServiceContext serviceContext)
		throws PortalException;

	@Indexable(type = IndexableType.REINDEX)
	public Module updateModule(long userId, long moduleId,
		Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
		Date startDate, Date endDate, long allowedTime,
		ImageSelector smallImageImageSelector, long moduleEvalId,
		String moduleExtraData, ServiceContext serviceContext)
		throws PortalException;

	/**
	* Updates the module in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param module the module
	* @return the module that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public Module updateModule(Module module);

	public Module updateOrder(Module module, long order);

	@Indexable(type = IndexableType.REINDEX)
	public Module updateStatus(long userId, long moduleId, int status,
		ServiceContext serviceContext, Map<String, Serializable> workflowContext)
		throws PortalException;
}