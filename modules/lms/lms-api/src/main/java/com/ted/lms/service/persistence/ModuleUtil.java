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

package com.ted.lms.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.ted.lms.model.Module;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.annotation.versioning.ProviderType;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the module service. This utility wraps <code>com.ted.lms.service.persistence.impl.ModulePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ModulePersistence
 * @generated
 */
@ProviderType
public class ModuleUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(Module module) {
		getPersistence().clearCache(module);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, Module> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Module> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Module> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Module> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Module> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Module update(Module module) {
		return getPersistence().update(module);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Module update(Module module, ServiceContext serviceContext) {
		return getPersistence().update(module, serviceContext);
	}

	/**
	 * Returns all the modules where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching modules
	 */
	public static List<Module> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the modules where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ModuleModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @return the range of matching modules
	 */
	public static List<Module> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the modules where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ModuleModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching modules
	 */
	public static List<Module> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Module> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the modules where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ModuleModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching modules
	 */
	public static List<Module> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Module> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first module in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching module
	 * @throws NoSuchModuleException if a matching module could not be found
	 */
	public static Module findByUuid_First(
			String uuid, OrderByComparator<Module> orderByComparator)
		throws com.ted.lms.exception.NoSuchModuleException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first module in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching module, or <code>null</code> if a matching module could not be found
	 */
	public static Module fetchByUuid_First(
		String uuid, OrderByComparator<Module> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last module in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching module
	 * @throws NoSuchModuleException if a matching module could not be found
	 */
	public static Module findByUuid_Last(
			String uuid, OrderByComparator<Module> orderByComparator)
		throws com.ted.lms.exception.NoSuchModuleException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last module in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching module, or <code>null</code> if a matching module could not be found
	 */
	public static Module fetchByUuid_Last(
		String uuid, OrderByComparator<Module> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the modules before and after the current module in the ordered set where uuid = &#63;.
	 *
	 * @param moduleId the primary key of the current module
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next module
	 * @throws NoSuchModuleException if a module with the primary key could not be found
	 */
	public static Module[] findByUuid_PrevAndNext(
			long moduleId, String uuid,
			OrderByComparator<Module> orderByComparator)
		throws com.ted.lms.exception.NoSuchModuleException {

		return getPersistence().findByUuid_PrevAndNext(
			moduleId, uuid, orderByComparator);
	}

	/**
	 * Removes all the modules where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of modules where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching modules
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the module where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchModuleException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching module
	 * @throws NoSuchModuleException if a matching module could not be found
	 */
	public static Module findByUUID_G(String uuid, long groupId)
		throws com.ted.lms.exception.NoSuchModuleException {

		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the module where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching module, or <code>null</code> if a matching module could not be found
	 */
	public static Module fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the module where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching module, or <code>null</code> if a matching module could not be found
	 */
	public static Module fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		return getPersistence().fetchByUUID_G(uuid, groupId, useFinderCache);
	}

	/**
	 * Removes the module where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the module that was removed
	 */
	public static Module removeByUUID_G(String uuid, long groupId)
		throws com.ted.lms.exception.NoSuchModuleException {

		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the number of modules where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching modules
	 */
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	 * Returns all the modules where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching modules
	 */
	public static List<Module> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the modules where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ModuleModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @return the range of matching modules
	 */
	public static List<Module> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the modules where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ModuleModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching modules
	 */
	public static List<Module> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Module> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the modules where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ModuleModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching modules
	 */
	public static List<Module> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Module> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first module in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching module
	 * @throws NoSuchModuleException if a matching module could not be found
	 */
	public static Module findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<Module> orderByComparator)
		throws com.ted.lms.exception.NoSuchModuleException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first module in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching module, or <code>null</code> if a matching module could not be found
	 */
	public static Module fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<Module> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last module in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching module
	 * @throws NoSuchModuleException if a matching module could not be found
	 */
	public static Module findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<Module> orderByComparator)
		throws com.ted.lms.exception.NoSuchModuleException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last module in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching module, or <code>null</code> if a matching module could not be found
	 */
	public static Module fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<Module> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the modules before and after the current module in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param moduleId the primary key of the current module
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next module
	 * @throws NoSuchModuleException if a module with the primary key could not be found
	 */
	public static Module[] findByUuid_C_PrevAndNext(
			long moduleId, String uuid, long companyId,
			OrderByComparator<Module> orderByComparator)
		throws com.ted.lms.exception.NoSuchModuleException {

		return getPersistence().findByUuid_C_PrevAndNext(
			moduleId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the modules where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of modules where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching modules
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns all the modules where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching modules
	 */
	public static List<Module> findByCompanyId(long companyId) {
		return getPersistence().findByCompanyId(companyId);
	}

	/**
	 * Returns a range of all the modules where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ModuleModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @return the range of matching modules
	 */
	public static List<Module> findByCompanyId(
		long companyId, int start, int end) {

		return getPersistence().findByCompanyId(companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the modules where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ModuleModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching modules
	 */
	public static List<Module> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<Module> orderByComparator) {

		return getPersistence().findByCompanyId(
			companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the modules where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ModuleModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching modules
	 */
	public static List<Module> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<Module> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByCompanyId(
			companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first module in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching module
	 * @throws NoSuchModuleException if a matching module could not be found
	 */
	public static Module findByCompanyId_First(
			long companyId, OrderByComparator<Module> orderByComparator)
		throws com.ted.lms.exception.NoSuchModuleException {

		return getPersistence().findByCompanyId_First(
			companyId, orderByComparator);
	}

	/**
	 * Returns the first module in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching module, or <code>null</code> if a matching module could not be found
	 */
	public static Module fetchByCompanyId_First(
		long companyId, OrderByComparator<Module> orderByComparator) {

		return getPersistence().fetchByCompanyId_First(
			companyId, orderByComparator);
	}

	/**
	 * Returns the last module in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching module
	 * @throws NoSuchModuleException if a matching module could not be found
	 */
	public static Module findByCompanyId_Last(
			long companyId, OrderByComparator<Module> orderByComparator)
		throws com.ted.lms.exception.NoSuchModuleException {

		return getPersistence().findByCompanyId_Last(
			companyId, orderByComparator);
	}

	/**
	 * Returns the last module in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching module, or <code>null</code> if a matching module could not be found
	 */
	public static Module fetchByCompanyId_Last(
		long companyId, OrderByComparator<Module> orderByComparator) {

		return getPersistence().fetchByCompanyId_Last(
			companyId, orderByComparator);
	}

	/**
	 * Returns the modules before and after the current module in the ordered set where companyId = &#63;.
	 *
	 * @param moduleId the primary key of the current module
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next module
	 * @throws NoSuchModuleException if a module with the primary key could not be found
	 */
	public static Module[] findByCompanyId_PrevAndNext(
			long moduleId, long companyId,
			OrderByComparator<Module> orderByComparator)
		throws com.ted.lms.exception.NoSuchModuleException {

		return getPersistence().findByCompanyId_PrevAndNext(
			moduleId, companyId, orderByComparator);
	}

	/**
	 * Removes all the modules where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	public static void removeByCompanyId(long companyId) {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	 * Returns the number of modules where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching modules
	 */
	public static int countByCompanyId(long companyId) {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	 * Returns all the modules where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching modules
	 */
	public static List<Module> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	 * Returns a range of all the modules where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ModuleModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @return the range of matching modules
	 */
	public static List<Module> findByGroupId(long groupId, int start, int end) {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	 * Returns an ordered range of all the modules where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ModuleModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching modules
	 */
	public static List<Module> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<Module> orderByComparator) {

		return getPersistence().findByGroupId(
			groupId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the modules where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ModuleModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching modules
	 */
	public static List<Module> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<Module> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByGroupId(
			groupId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first module in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching module
	 * @throws NoSuchModuleException if a matching module could not be found
	 */
	public static Module findByGroupId_First(
			long groupId, OrderByComparator<Module> orderByComparator)
		throws com.ted.lms.exception.NoSuchModuleException {

		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	 * Returns the first module in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching module, or <code>null</code> if a matching module could not be found
	 */
	public static Module fetchByGroupId_First(
		long groupId, OrderByComparator<Module> orderByComparator) {

		return getPersistence().fetchByGroupId_First(
			groupId, orderByComparator);
	}

	/**
	 * Returns the last module in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching module
	 * @throws NoSuchModuleException if a matching module could not be found
	 */
	public static Module findByGroupId_Last(
			long groupId, OrderByComparator<Module> orderByComparator)
		throws com.ted.lms.exception.NoSuchModuleException {

		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the last module in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching module, or <code>null</code> if a matching module could not be found
	 */
	public static Module fetchByGroupId_Last(
		long groupId, OrderByComparator<Module> orderByComparator) {

		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the modules before and after the current module in the ordered set where groupId = &#63;.
	 *
	 * @param moduleId the primary key of the current module
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next module
	 * @throws NoSuchModuleException if a module with the primary key could not be found
	 */
	public static Module[] findByGroupId_PrevAndNext(
			long moduleId, long groupId,
			OrderByComparator<Module> orderByComparator)
		throws com.ted.lms.exception.NoSuchModuleException {

		return getPersistence().findByGroupId_PrevAndNext(
			moduleId, groupId, orderByComparator);
	}

	/**
	 * Returns all the modules that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching modules that the user has permission to view
	 */
	public static List<Module> filterFindByGroupId(long groupId) {
		return getPersistence().filterFindByGroupId(groupId);
	}

	/**
	 * Returns a range of all the modules that the user has permission to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ModuleModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @return the range of matching modules that the user has permission to view
	 */
	public static List<Module> filterFindByGroupId(
		long groupId, int start, int end) {

		return getPersistence().filterFindByGroupId(groupId, start, end);
	}

	/**
	 * Returns an ordered range of all the modules that the user has permissions to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ModuleModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching modules that the user has permission to view
	 */
	public static List<Module> filterFindByGroupId(
		long groupId, int start, int end,
		OrderByComparator<Module> orderByComparator) {

		return getPersistence().filterFindByGroupId(
			groupId, start, end, orderByComparator);
	}

	/**
	 * Returns the modules before and after the current module in the ordered set of modules that the user has permission to view where groupId = &#63;.
	 *
	 * @param moduleId the primary key of the current module
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next module
	 * @throws NoSuchModuleException if a module with the primary key could not be found
	 */
	public static Module[] filterFindByGroupId_PrevAndNext(
			long moduleId, long groupId,
			OrderByComparator<Module> orderByComparator)
		throws com.ted.lms.exception.NoSuchModuleException {

		return getPersistence().filterFindByGroupId_PrevAndNext(
			moduleId, groupId, orderByComparator);
	}

	/**
	 * Removes all the modules where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	 * Returns the number of modules where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching modules
	 */
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	 * Returns the number of modules that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching modules that the user has permission to view
	 */
	public static int filterCountByGroupId(long groupId) {
		return getPersistence().filterCountByGroupId(groupId);
	}

	/**
	 * Returns all the modules where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @return the matching modules
	 */
	public static List<Module> findByGroupIdStatus(long groupId, int status) {
		return getPersistence().findByGroupIdStatus(groupId, status);
	}

	/**
	 * Returns a range of all the modules where groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ModuleModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @return the range of matching modules
	 */
	public static List<Module> findByGroupIdStatus(
		long groupId, int status, int start, int end) {

		return getPersistence().findByGroupIdStatus(
			groupId, status, start, end);
	}

	/**
	 * Returns an ordered range of all the modules where groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ModuleModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching modules
	 */
	public static List<Module> findByGroupIdStatus(
		long groupId, int status, int start, int end,
		OrderByComparator<Module> orderByComparator) {

		return getPersistence().findByGroupIdStatus(
			groupId, status, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the modules where groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ModuleModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching modules
	 */
	public static List<Module> findByGroupIdStatus(
		long groupId, int status, int start, int end,
		OrderByComparator<Module> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByGroupIdStatus(
			groupId, status, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first module in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching module
	 * @throws NoSuchModuleException if a matching module could not be found
	 */
	public static Module findByGroupIdStatus_First(
			long groupId, int status,
			OrderByComparator<Module> orderByComparator)
		throws com.ted.lms.exception.NoSuchModuleException {

		return getPersistence().findByGroupIdStatus_First(
			groupId, status, orderByComparator);
	}

	/**
	 * Returns the first module in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching module, or <code>null</code> if a matching module could not be found
	 */
	public static Module fetchByGroupIdStatus_First(
		long groupId, int status, OrderByComparator<Module> orderByComparator) {

		return getPersistence().fetchByGroupIdStatus_First(
			groupId, status, orderByComparator);
	}

	/**
	 * Returns the last module in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching module
	 * @throws NoSuchModuleException if a matching module could not be found
	 */
	public static Module findByGroupIdStatus_Last(
			long groupId, int status,
			OrderByComparator<Module> orderByComparator)
		throws com.ted.lms.exception.NoSuchModuleException {

		return getPersistence().findByGroupIdStatus_Last(
			groupId, status, orderByComparator);
	}

	/**
	 * Returns the last module in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching module, or <code>null</code> if a matching module could not be found
	 */
	public static Module fetchByGroupIdStatus_Last(
		long groupId, int status, OrderByComparator<Module> orderByComparator) {

		return getPersistence().fetchByGroupIdStatus_Last(
			groupId, status, orderByComparator);
	}

	/**
	 * Returns the modules before and after the current module in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param moduleId the primary key of the current module
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next module
	 * @throws NoSuchModuleException if a module with the primary key could not be found
	 */
	public static Module[] findByGroupIdStatus_PrevAndNext(
			long moduleId, long groupId, int status,
			OrderByComparator<Module> orderByComparator)
		throws com.ted.lms.exception.NoSuchModuleException {

		return getPersistence().findByGroupIdStatus_PrevAndNext(
			moduleId, groupId, status, orderByComparator);
	}

	/**
	 * Returns all the modules that the user has permission to view where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @return the matching modules that the user has permission to view
	 */
	public static List<Module> filterFindByGroupIdStatus(
		long groupId, int status) {

		return getPersistence().filterFindByGroupIdStatus(groupId, status);
	}

	/**
	 * Returns a range of all the modules that the user has permission to view where groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ModuleModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @return the range of matching modules that the user has permission to view
	 */
	public static List<Module> filterFindByGroupIdStatus(
		long groupId, int status, int start, int end) {

		return getPersistence().filterFindByGroupIdStatus(
			groupId, status, start, end);
	}

	/**
	 * Returns an ordered range of all the modules that the user has permissions to view where groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ModuleModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching modules that the user has permission to view
	 */
	public static List<Module> filterFindByGroupIdStatus(
		long groupId, int status, int start, int end,
		OrderByComparator<Module> orderByComparator) {

		return getPersistence().filterFindByGroupIdStatus(
			groupId, status, start, end, orderByComparator);
	}

	/**
	 * Returns the modules before and after the current module in the ordered set of modules that the user has permission to view where groupId = &#63; and status = &#63;.
	 *
	 * @param moduleId the primary key of the current module
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next module
	 * @throws NoSuchModuleException if a module with the primary key could not be found
	 */
	public static Module[] filterFindByGroupIdStatus_PrevAndNext(
			long moduleId, long groupId, int status,
			OrderByComparator<Module> orderByComparator)
		throws com.ted.lms.exception.NoSuchModuleException {

		return getPersistence().filterFindByGroupIdStatus_PrevAndNext(
			moduleId, groupId, status, orderByComparator);
	}

	/**
	 * Removes all the modules where groupId = &#63; and status = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 */
	public static void removeByGroupIdStatus(long groupId, int status) {
		getPersistence().removeByGroupIdStatus(groupId, status);
	}

	/**
	 * Returns the number of modules where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @return the number of matching modules
	 */
	public static int countByGroupIdStatus(long groupId, int status) {
		return getPersistence().countByGroupIdStatus(groupId, status);
	}

	/**
	 * Returns the number of modules that the user has permission to view where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @return the number of matching modules that the user has permission to view
	 */
	public static int filterCountByGroupIdStatus(long groupId, int status) {
		return getPersistence().filterCountByGroupIdStatus(groupId, status);
	}

	/**
	 * Returns all the modules where groupId = &#63; and priority &gt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param priority the priority
	 * @return the matching modules
	 */
	public static List<Module> findByGroupIdNextModules(
		long groupId, long priority) {

		return getPersistence().findByGroupIdNextModules(groupId, priority);
	}

	/**
	 * Returns a range of all the modules where groupId = &#63; and priority &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ModuleModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param priority the priority
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @return the range of matching modules
	 */
	public static List<Module> findByGroupIdNextModules(
		long groupId, long priority, int start, int end) {

		return getPersistence().findByGroupIdNextModules(
			groupId, priority, start, end);
	}

	/**
	 * Returns an ordered range of all the modules where groupId = &#63; and priority &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ModuleModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param priority the priority
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching modules
	 */
	public static List<Module> findByGroupIdNextModules(
		long groupId, long priority, int start, int end,
		OrderByComparator<Module> orderByComparator) {

		return getPersistence().findByGroupIdNextModules(
			groupId, priority, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the modules where groupId = &#63; and priority &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ModuleModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param priority the priority
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching modules
	 */
	public static List<Module> findByGroupIdNextModules(
		long groupId, long priority, int start, int end,
		OrderByComparator<Module> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByGroupIdNextModules(
			groupId, priority, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first module in the ordered set where groupId = &#63; and priority &gt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param priority the priority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching module
	 * @throws NoSuchModuleException if a matching module could not be found
	 */
	public static Module findByGroupIdNextModules_First(
			long groupId, long priority,
			OrderByComparator<Module> orderByComparator)
		throws com.ted.lms.exception.NoSuchModuleException {

		return getPersistence().findByGroupIdNextModules_First(
			groupId, priority, orderByComparator);
	}

	/**
	 * Returns the first module in the ordered set where groupId = &#63; and priority &gt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param priority the priority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching module, or <code>null</code> if a matching module could not be found
	 */
	public static Module fetchByGroupIdNextModules_First(
		long groupId, long priority,
		OrderByComparator<Module> orderByComparator) {

		return getPersistence().fetchByGroupIdNextModules_First(
			groupId, priority, orderByComparator);
	}

	/**
	 * Returns the last module in the ordered set where groupId = &#63; and priority &gt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param priority the priority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching module
	 * @throws NoSuchModuleException if a matching module could not be found
	 */
	public static Module findByGroupIdNextModules_Last(
			long groupId, long priority,
			OrderByComparator<Module> orderByComparator)
		throws com.ted.lms.exception.NoSuchModuleException {

		return getPersistence().findByGroupIdNextModules_Last(
			groupId, priority, orderByComparator);
	}

	/**
	 * Returns the last module in the ordered set where groupId = &#63; and priority &gt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param priority the priority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching module, or <code>null</code> if a matching module could not be found
	 */
	public static Module fetchByGroupIdNextModules_Last(
		long groupId, long priority,
		OrderByComparator<Module> orderByComparator) {

		return getPersistence().fetchByGroupIdNextModules_Last(
			groupId, priority, orderByComparator);
	}

	/**
	 * Returns the modules before and after the current module in the ordered set where groupId = &#63; and priority &gt; &#63;.
	 *
	 * @param moduleId the primary key of the current module
	 * @param groupId the group ID
	 * @param priority the priority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next module
	 * @throws NoSuchModuleException if a module with the primary key could not be found
	 */
	public static Module[] findByGroupIdNextModules_PrevAndNext(
			long moduleId, long groupId, long priority,
			OrderByComparator<Module> orderByComparator)
		throws com.ted.lms.exception.NoSuchModuleException {

		return getPersistence().findByGroupIdNextModules_PrevAndNext(
			moduleId, groupId, priority, orderByComparator);
	}

	/**
	 * Returns all the modules that the user has permission to view where groupId = &#63; and priority &gt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param priority the priority
	 * @return the matching modules that the user has permission to view
	 */
	public static List<Module> filterFindByGroupIdNextModules(
		long groupId, long priority) {

		return getPersistence().filterFindByGroupIdNextModules(
			groupId, priority);
	}

	/**
	 * Returns a range of all the modules that the user has permission to view where groupId = &#63; and priority &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ModuleModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param priority the priority
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @return the range of matching modules that the user has permission to view
	 */
	public static List<Module> filterFindByGroupIdNextModules(
		long groupId, long priority, int start, int end) {

		return getPersistence().filterFindByGroupIdNextModules(
			groupId, priority, start, end);
	}

	/**
	 * Returns an ordered range of all the modules that the user has permissions to view where groupId = &#63; and priority &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ModuleModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param priority the priority
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching modules that the user has permission to view
	 */
	public static List<Module> filterFindByGroupIdNextModules(
		long groupId, long priority, int start, int end,
		OrderByComparator<Module> orderByComparator) {

		return getPersistence().filterFindByGroupIdNextModules(
			groupId, priority, start, end, orderByComparator);
	}

	/**
	 * Returns the modules before and after the current module in the ordered set of modules that the user has permission to view where groupId = &#63; and priority &gt; &#63;.
	 *
	 * @param moduleId the primary key of the current module
	 * @param groupId the group ID
	 * @param priority the priority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next module
	 * @throws NoSuchModuleException if a module with the primary key could not be found
	 */
	public static Module[] filterFindByGroupIdNextModules_PrevAndNext(
			long moduleId, long groupId, long priority,
			OrderByComparator<Module> orderByComparator)
		throws com.ted.lms.exception.NoSuchModuleException {

		return getPersistence().filterFindByGroupIdNextModules_PrevAndNext(
			moduleId, groupId, priority, orderByComparator);
	}

	/**
	 * Removes all the modules where groupId = &#63; and priority &gt; &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param priority the priority
	 */
	public static void removeByGroupIdNextModules(long groupId, long priority) {
		getPersistence().removeByGroupIdNextModules(groupId, priority);
	}

	/**
	 * Returns the number of modules where groupId = &#63; and priority &gt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param priority the priority
	 * @return the number of matching modules
	 */
	public static int countByGroupIdNextModules(long groupId, long priority) {
		return getPersistence().countByGroupIdNextModules(groupId, priority);
	}

	/**
	 * Returns the number of modules that the user has permission to view where groupId = &#63; and priority &gt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param priority the priority
	 * @return the number of matching modules that the user has permission to view
	 */
	public static int filterCountByGroupIdNextModules(
		long groupId, long priority) {

		return getPersistence().filterCountByGroupIdNextModules(
			groupId, priority);
	}

	/**
	 * Returns all the modules where groupId = &#63; and priority &lt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param priority the priority
	 * @return the matching modules
	 */
	public static List<Module> findByGroupIdPreviousModules(
		long groupId, long priority) {

		return getPersistence().findByGroupIdPreviousModules(groupId, priority);
	}

	/**
	 * Returns a range of all the modules where groupId = &#63; and priority &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ModuleModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param priority the priority
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @return the range of matching modules
	 */
	public static List<Module> findByGroupIdPreviousModules(
		long groupId, long priority, int start, int end) {

		return getPersistence().findByGroupIdPreviousModules(
			groupId, priority, start, end);
	}

	/**
	 * Returns an ordered range of all the modules where groupId = &#63; and priority &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ModuleModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param priority the priority
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching modules
	 */
	public static List<Module> findByGroupIdPreviousModules(
		long groupId, long priority, int start, int end,
		OrderByComparator<Module> orderByComparator) {

		return getPersistence().findByGroupIdPreviousModules(
			groupId, priority, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the modules where groupId = &#63; and priority &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ModuleModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param priority the priority
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching modules
	 */
	public static List<Module> findByGroupIdPreviousModules(
		long groupId, long priority, int start, int end,
		OrderByComparator<Module> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByGroupIdPreviousModules(
			groupId, priority, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first module in the ordered set where groupId = &#63; and priority &lt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param priority the priority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching module
	 * @throws NoSuchModuleException if a matching module could not be found
	 */
	public static Module findByGroupIdPreviousModules_First(
			long groupId, long priority,
			OrderByComparator<Module> orderByComparator)
		throws com.ted.lms.exception.NoSuchModuleException {

		return getPersistence().findByGroupIdPreviousModules_First(
			groupId, priority, orderByComparator);
	}

	/**
	 * Returns the first module in the ordered set where groupId = &#63; and priority &lt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param priority the priority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching module, or <code>null</code> if a matching module could not be found
	 */
	public static Module fetchByGroupIdPreviousModules_First(
		long groupId, long priority,
		OrderByComparator<Module> orderByComparator) {

		return getPersistence().fetchByGroupIdPreviousModules_First(
			groupId, priority, orderByComparator);
	}

	/**
	 * Returns the last module in the ordered set where groupId = &#63; and priority &lt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param priority the priority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching module
	 * @throws NoSuchModuleException if a matching module could not be found
	 */
	public static Module findByGroupIdPreviousModules_Last(
			long groupId, long priority,
			OrderByComparator<Module> orderByComparator)
		throws com.ted.lms.exception.NoSuchModuleException {

		return getPersistence().findByGroupIdPreviousModules_Last(
			groupId, priority, orderByComparator);
	}

	/**
	 * Returns the last module in the ordered set where groupId = &#63; and priority &lt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param priority the priority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching module, or <code>null</code> if a matching module could not be found
	 */
	public static Module fetchByGroupIdPreviousModules_Last(
		long groupId, long priority,
		OrderByComparator<Module> orderByComparator) {

		return getPersistence().fetchByGroupIdPreviousModules_Last(
			groupId, priority, orderByComparator);
	}

	/**
	 * Returns the modules before and after the current module in the ordered set where groupId = &#63; and priority &lt; &#63;.
	 *
	 * @param moduleId the primary key of the current module
	 * @param groupId the group ID
	 * @param priority the priority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next module
	 * @throws NoSuchModuleException if a module with the primary key could not be found
	 */
	public static Module[] findByGroupIdPreviousModules_PrevAndNext(
			long moduleId, long groupId, long priority,
			OrderByComparator<Module> orderByComparator)
		throws com.ted.lms.exception.NoSuchModuleException {

		return getPersistence().findByGroupIdPreviousModules_PrevAndNext(
			moduleId, groupId, priority, orderByComparator);
	}

	/**
	 * Returns all the modules that the user has permission to view where groupId = &#63; and priority &lt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param priority the priority
	 * @return the matching modules that the user has permission to view
	 */
	public static List<Module> filterFindByGroupIdPreviousModules(
		long groupId, long priority) {

		return getPersistence().filterFindByGroupIdPreviousModules(
			groupId, priority);
	}

	/**
	 * Returns a range of all the modules that the user has permission to view where groupId = &#63; and priority &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ModuleModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param priority the priority
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @return the range of matching modules that the user has permission to view
	 */
	public static List<Module> filterFindByGroupIdPreviousModules(
		long groupId, long priority, int start, int end) {

		return getPersistence().filterFindByGroupIdPreviousModules(
			groupId, priority, start, end);
	}

	/**
	 * Returns an ordered range of all the modules that the user has permissions to view where groupId = &#63; and priority &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ModuleModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param priority the priority
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching modules that the user has permission to view
	 */
	public static List<Module> filterFindByGroupIdPreviousModules(
		long groupId, long priority, int start, int end,
		OrderByComparator<Module> orderByComparator) {

		return getPersistence().filterFindByGroupIdPreviousModules(
			groupId, priority, start, end, orderByComparator);
	}

	/**
	 * Returns the modules before and after the current module in the ordered set of modules that the user has permission to view where groupId = &#63; and priority &lt; &#63;.
	 *
	 * @param moduleId the primary key of the current module
	 * @param groupId the group ID
	 * @param priority the priority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next module
	 * @throws NoSuchModuleException if a module with the primary key could not be found
	 */
	public static Module[] filterFindByGroupIdPreviousModules_PrevAndNext(
			long moduleId, long groupId, long priority,
			OrderByComparator<Module> orderByComparator)
		throws com.ted.lms.exception.NoSuchModuleException {

		return getPersistence().filterFindByGroupIdPreviousModules_PrevAndNext(
			moduleId, groupId, priority, orderByComparator);
	}

	/**
	 * Removes all the modules where groupId = &#63; and priority &lt; &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param priority the priority
	 */
	public static void removeByGroupIdPreviousModules(
		long groupId, long priority) {

		getPersistence().removeByGroupIdPreviousModules(groupId, priority);
	}

	/**
	 * Returns the number of modules where groupId = &#63; and priority &lt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param priority the priority
	 * @return the number of matching modules
	 */
	public static int countByGroupIdPreviousModules(
		long groupId, long priority) {

		return getPersistence().countByGroupIdPreviousModules(
			groupId, priority);
	}

	/**
	 * Returns the number of modules that the user has permission to view where groupId = &#63; and priority &lt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param priority the priority
	 * @return the number of matching modules that the user has permission to view
	 */
	public static int filterCountByGroupIdPreviousModules(
		long groupId, long priority) {

		return getPersistence().filterCountByGroupIdPreviousModules(
			groupId, priority);
	}

	/**
	 * Caches the module in the entity cache if it is enabled.
	 *
	 * @param module the module
	 */
	public static void cacheResult(Module module) {
		getPersistence().cacheResult(module);
	}

	/**
	 * Caches the modules in the entity cache if it is enabled.
	 *
	 * @param modules the modules
	 */
	public static void cacheResult(List<Module> modules) {
		getPersistence().cacheResult(modules);
	}

	/**
	 * Creates a new module with the primary key. Does not add the module to the database.
	 *
	 * @param moduleId the primary key for the new module
	 * @return the new module
	 */
	public static Module create(long moduleId) {
		return getPersistence().create(moduleId);
	}

	/**
	 * Removes the module with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param moduleId the primary key of the module
	 * @return the module that was removed
	 * @throws NoSuchModuleException if a module with the primary key could not be found
	 */
	public static Module remove(long moduleId)
		throws com.ted.lms.exception.NoSuchModuleException {

		return getPersistence().remove(moduleId);
	}

	public static Module updateImpl(Module module) {
		return getPersistence().updateImpl(module);
	}

	/**
	 * Returns the module with the primary key or throws a <code>NoSuchModuleException</code> if it could not be found.
	 *
	 * @param moduleId the primary key of the module
	 * @return the module
	 * @throws NoSuchModuleException if a module with the primary key could not be found
	 */
	public static Module findByPrimaryKey(long moduleId)
		throws com.ted.lms.exception.NoSuchModuleException {

		return getPersistence().findByPrimaryKey(moduleId);
	}

	/**
	 * Returns the module with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param moduleId the primary key of the module
	 * @return the module, or <code>null</code> if a module with the primary key could not be found
	 */
	public static Module fetchByPrimaryKey(long moduleId) {
		return getPersistence().fetchByPrimaryKey(moduleId);
	}

	/**
	 * Returns all the modules.
	 *
	 * @return the modules
	 */
	public static List<Module> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the modules.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ModuleModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @return the range of modules
	 */
	public static List<Module> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the modules.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ModuleModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of modules
	 */
	public static List<Module> findAll(
		int start, int end, OrderByComparator<Module> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the modules.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ModuleModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of modules
	 */
	public static List<Module> findAll(
		int start, int end, OrderByComparator<Module> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the modules from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of modules.
	 *
	 * @return the number of modules
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static ModulePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ModulePersistence, ModulePersistence>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ModulePersistence.class);

		ServiceTracker<ModulePersistence, ModulePersistence> serviceTracker =
			new ServiceTracker<ModulePersistence, ModulePersistence>(
				bundle.getBundleContext(), ModulePersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}