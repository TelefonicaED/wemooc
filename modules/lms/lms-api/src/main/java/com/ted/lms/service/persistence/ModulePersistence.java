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

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.ted.lms.exception.NoSuchModuleException;
import com.ted.lms.model.Module;

/**
 * The persistence interface for the module service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ted.lms.service.persistence.impl.ModulePersistenceImpl
 * @see ModuleUtil
 * @generated
 */
@ProviderType
public interface ModulePersistence extends BasePersistence<Module> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ModuleUtil} to access the module persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the modules where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching modules
	*/
	public java.util.List<Module> findByUuid(String uuid);

	/**
	* Returns a range of all the modules where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of modules
	* @param end the upper bound of the range of modules (not inclusive)
	* @return the range of matching modules
	*/
	public java.util.List<Module> findByUuid(String uuid, int start, int end);

	/**
	* Returns an ordered range of all the modules where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of modules
	* @param end the upper bound of the range of modules (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching modules
	*/
	public java.util.List<Module> findByUuid(String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Module> orderByComparator);

	/**
	* Returns an ordered range of all the modules where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of modules
	* @param end the upper bound of the range of modules (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching modules
	*/
	public java.util.List<Module> findByUuid(String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Module> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first module in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching module
	* @throws NoSuchModuleException if a matching module could not be found
	*/
	public Module findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Module> orderByComparator)
		throws NoSuchModuleException;

	/**
	* Returns the first module in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching module, or <code>null</code> if a matching module could not be found
	*/
	public Module fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Module> orderByComparator);

	/**
	* Returns the last module in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching module
	* @throws NoSuchModuleException if a matching module could not be found
	*/
	public Module findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Module> orderByComparator)
		throws NoSuchModuleException;

	/**
	* Returns the last module in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching module, or <code>null</code> if a matching module could not be found
	*/
	public Module fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Module> orderByComparator);

	/**
	* Returns the modules before and after the current module in the ordered set where uuid = &#63;.
	*
	* @param moduleId the primary key of the current module
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next module
	* @throws NoSuchModuleException if a module with the primary key could not be found
	*/
	public Module[] findByUuid_PrevAndNext(long moduleId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Module> orderByComparator)
		throws NoSuchModuleException;

	/**
	* Removes all the modules where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of modules where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching modules
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the module where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchModuleException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching module
	* @throws NoSuchModuleException if a matching module could not be found
	*/
	public Module findByUUID_G(String uuid, long groupId)
		throws NoSuchModuleException;

	/**
	* Returns the module where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching module, or <code>null</code> if a matching module could not be found
	*/
	public Module fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the module where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching module, or <code>null</code> if a matching module could not be found
	*/
	public Module fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the module where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the module that was removed
	*/
	public Module removeByUUID_G(String uuid, long groupId)
		throws NoSuchModuleException;

	/**
	* Returns the number of modules where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching modules
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the modules where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching modules
	*/
	public java.util.List<Module> findByUuid_C(String uuid, long companyId);

	/**
	* Returns a range of all the modules where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of modules
	* @param end the upper bound of the range of modules (not inclusive)
	* @return the range of matching modules
	*/
	public java.util.List<Module> findByUuid_C(String uuid, long companyId,
		int start, int end);

	/**
	* Returns an ordered range of all the modules where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of modules
	* @param end the upper bound of the range of modules (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching modules
	*/
	public java.util.List<Module> findByUuid_C(String uuid, long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Module> orderByComparator);

	/**
	* Returns an ordered range of all the modules where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of modules
	* @param end the upper bound of the range of modules (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching modules
	*/
	public java.util.List<Module> findByUuid_C(String uuid, long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Module> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first module in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching module
	* @throws NoSuchModuleException if a matching module could not be found
	*/
	public Module findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Module> orderByComparator)
		throws NoSuchModuleException;

	/**
	* Returns the first module in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching module, or <code>null</code> if a matching module could not be found
	*/
	public Module fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Module> orderByComparator);

	/**
	* Returns the last module in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching module
	* @throws NoSuchModuleException if a matching module could not be found
	*/
	public Module findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Module> orderByComparator)
		throws NoSuchModuleException;

	/**
	* Returns the last module in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching module, or <code>null</code> if a matching module could not be found
	*/
	public Module fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Module> orderByComparator);

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
	public Module[] findByUuid_C_PrevAndNext(long moduleId, String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Module> orderByComparator)
		throws NoSuchModuleException;

	/**
	* Removes all the modules where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of modules where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching modules
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns all the modules where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching modules
	*/
	public java.util.List<Module> findByCompanyId(long companyId);

	/**
	* Returns a range of all the modules where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of modules
	* @param end the upper bound of the range of modules (not inclusive)
	* @return the range of matching modules
	*/
	public java.util.List<Module> findByCompanyId(long companyId, int start,
		int end);

	/**
	* Returns an ordered range of all the modules where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of modules
	* @param end the upper bound of the range of modules (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching modules
	*/
	public java.util.List<Module> findByCompanyId(long companyId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Module> orderByComparator);

	/**
	* Returns an ordered range of all the modules where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of modules
	* @param end the upper bound of the range of modules (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching modules
	*/
	public java.util.List<Module> findByCompanyId(long companyId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Module> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first module in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching module
	* @throws NoSuchModuleException if a matching module could not be found
	*/
	public Module findByCompanyId_First(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Module> orderByComparator)
		throws NoSuchModuleException;

	/**
	* Returns the first module in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching module, or <code>null</code> if a matching module could not be found
	*/
	public Module fetchByCompanyId_First(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Module> orderByComparator);

	/**
	* Returns the last module in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching module
	* @throws NoSuchModuleException if a matching module could not be found
	*/
	public Module findByCompanyId_Last(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Module> orderByComparator)
		throws NoSuchModuleException;

	/**
	* Returns the last module in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching module, or <code>null</code> if a matching module could not be found
	*/
	public Module fetchByCompanyId_Last(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Module> orderByComparator);

	/**
	* Returns the modules before and after the current module in the ordered set where companyId = &#63;.
	*
	* @param moduleId the primary key of the current module
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next module
	* @throws NoSuchModuleException if a module with the primary key could not be found
	*/
	public Module[] findByCompanyId_PrevAndNext(long moduleId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Module> orderByComparator)
		throws NoSuchModuleException;

	/**
	* Removes all the modules where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	*/
	public void removeByCompanyId(long companyId);

	/**
	* Returns the number of modules where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching modules
	*/
	public int countByCompanyId(long companyId);

	/**
	* Returns all the modules where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching modules
	*/
	public java.util.List<Module> findByGroupId(long groupId);

	/**
	* Returns a range of all the modules where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of modules
	* @param end the upper bound of the range of modules (not inclusive)
	* @return the range of matching modules
	*/
	public java.util.List<Module> findByGroupId(long groupId, int start, int end);

	/**
	* Returns an ordered range of all the modules where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of modules
	* @param end the upper bound of the range of modules (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching modules
	*/
	public java.util.List<Module> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Module> orderByComparator);

	/**
	* Returns an ordered range of all the modules where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of modules
	* @param end the upper bound of the range of modules (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching modules
	*/
	public java.util.List<Module> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Module> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first module in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching module
	* @throws NoSuchModuleException if a matching module could not be found
	*/
	public Module findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Module> orderByComparator)
		throws NoSuchModuleException;

	/**
	* Returns the first module in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching module, or <code>null</code> if a matching module could not be found
	*/
	public Module fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Module> orderByComparator);

	/**
	* Returns the last module in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching module
	* @throws NoSuchModuleException if a matching module could not be found
	*/
	public Module findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Module> orderByComparator)
		throws NoSuchModuleException;

	/**
	* Returns the last module in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching module, or <code>null</code> if a matching module could not be found
	*/
	public Module fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Module> orderByComparator);

	/**
	* Returns the modules before and after the current module in the ordered set where groupId = &#63;.
	*
	* @param moduleId the primary key of the current module
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next module
	* @throws NoSuchModuleException if a module with the primary key could not be found
	*/
	public Module[] findByGroupId_PrevAndNext(long moduleId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Module> orderByComparator)
		throws NoSuchModuleException;

	/**
	* Returns all the modules that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching modules that the user has permission to view
	*/
	public java.util.List<Module> filterFindByGroupId(long groupId);

	/**
	* Returns a range of all the modules that the user has permission to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of modules
	* @param end the upper bound of the range of modules (not inclusive)
	* @return the range of matching modules that the user has permission to view
	*/
	public java.util.List<Module> filterFindByGroupId(long groupId, int start,
		int end);

	/**
	* Returns an ordered range of all the modules that the user has permissions to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of modules
	* @param end the upper bound of the range of modules (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching modules that the user has permission to view
	*/
	public java.util.List<Module> filterFindByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Module> orderByComparator);

	/**
	* Returns the modules before and after the current module in the ordered set of modules that the user has permission to view where groupId = &#63;.
	*
	* @param moduleId the primary key of the current module
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next module
	* @throws NoSuchModuleException if a module with the primary key could not be found
	*/
	public Module[] filterFindByGroupId_PrevAndNext(long moduleId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Module> orderByComparator)
		throws NoSuchModuleException;

	/**
	* Removes all the modules where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of modules where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching modules
	*/
	public int countByGroupId(long groupId);

	/**
	* Returns the number of modules that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching modules that the user has permission to view
	*/
	public int filterCountByGroupId(long groupId);

	/**
	* Caches the module in the entity cache if it is enabled.
	*
	* @param module the module
	*/
	public void cacheResult(Module module);

	/**
	* Caches the modules in the entity cache if it is enabled.
	*
	* @param modules the modules
	*/
	public void cacheResult(java.util.List<Module> modules);

	/**
	* Creates a new module with the primary key. Does not add the module to the database.
	*
	* @param moduleId the primary key for the new module
	* @return the new module
	*/
	public Module create(long moduleId);

	/**
	* Removes the module with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param moduleId the primary key of the module
	* @return the module that was removed
	* @throws NoSuchModuleException if a module with the primary key could not be found
	*/
	public Module remove(long moduleId) throws NoSuchModuleException;

	public Module updateImpl(Module module);

	/**
	* Returns the module with the primary key or throws a {@link NoSuchModuleException} if it could not be found.
	*
	* @param moduleId the primary key of the module
	* @return the module
	* @throws NoSuchModuleException if a module with the primary key could not be found
	*/
	public Module findByPrimaryKey(long moduleId) throws NoSuchModuleException;

	/**
	* Returns the module with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param moduleId the primary key of the module
	* @return the module, or <code>null</code> if a module with the primary key could not be found
	*/
	public Module fetchByPrimaryKey(long moduleId);

	@Override
	public java.util.Map<java.io.Serializable, Module> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the modules.
	*
	* @return the modules
	*/
	public java.util.List<Module> findAll();

	/**
	* Returns a range of all the modules.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of modules
	* @param end the upper bound of the range of modules (not inclusive)
	* @return the range of modules
	*/
	public java.util.List<Module> findAll(int start, int end);

	/**
	* Returns an ordered range of all the modules.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of modules
	* @param end the upper bound of the range of modules (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of modules
	*/
	public java.util.List<Module> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Module> orderByComparator);

	/**
	* Returns an ordered range of all the modules.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of modules
	* @param end the upper bound of the range of modules (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of modules
	*/
	public java.util.List<Module> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Module> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the modules from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of modules.
	*
	* @return the number of modules
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}