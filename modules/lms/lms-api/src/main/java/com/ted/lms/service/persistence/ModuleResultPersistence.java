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

import com.ted.lms.exception.NoSuchModuleResultException;
import com.ted.lms.model.ModuleResult;

/**
 * The persistence interface for the module result service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ted.lms.service.persistence.impl.ModuleResultPersistenceImpl
 * @see ModuleResultUtil
 * @generated
 */
@ProviderType
public interface ModuleResultPersistence extends BasePersistence<ModuleResult> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ModuleResultUtil} to access the module result persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the module results where moduleId = &#63;.
	*
	* @param moduleId the module ID
	* @return the matching module results
	*/
	public java.util.List<ModuleResult> findByModuleId(long moduleId);

	/**
	* Returns a range of all the module results where moduleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param moduleId the module ID
	* @param start the lower bound of the range of module results
	* @param end the upper bound of the range of module results (not inclusive)
	* @return the range of matching module results
	*/
	public java.util.List<ModuleResult> findByModuleId(long moduleId,
		int start, int end);

	/**
	* Returns an ordered range of all the module results where moduleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param moduleId the module ID
	* @param start the lower bound of the range of module results
	* @param end the upper bound of the range of module results (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching module results
	*/
	public java.util.List<ModuleResult> findByModuleId(long moduleId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ModuleResult> orderByComparator);

	/**
	* Returns an ordered range of all the module results where moduleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param moduleId the module ID
	* @param start the lower bound of the range of module results
	* @param end the upper bound of the range of module results (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching module results
	*/
	public java.util.List<ModuleResult> findByModuleId(long moduleId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ModuleResult> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first module result in the ordered set where moduleId = &#63;.
	*
	* @param moduleId the module ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching module result
	* @throws NoSuchModuleResultException if a matching module result could not be found
	*/
	public ModuleResult findByModuleId_First(long moduleId,
		com.liferay.portal.kernel.util.OrderByComparator<ModuleResult> orderByComparator)
		throws NoSuchModuleResultException;

	/**
	* Returns the first module result in the ordered set where moduleId = &#63;.
	*
	* @param moduleId the module ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching module result, or <code>null</code> if a matching module result could not be found
	*/
	public ModuleResult fetchByModuleId_First(long moduleId,
		com.liferay.portal.kernel.util.OrderByComparator<ModuleResult> orderByComparator);

	/**
	* Returns the last module result in the ordered set where moduleId = &#63;.
	*
	* @param moduleId the module ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching module result
	* @throws NoSuchModuleResultException if a matching module result could not be found
	*/
	public ModuleResult findByModuleId_Last(long moduleId,
		com.liferay.portal.kernel.util.OrderByComparator<ModuleResult> orderByComparator)
		throws NoSuchModuleResultException;

	/**
	* Returns the last module result in the ordered set where moduleId = &#63;.
	*
	* @param moduleId the module ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching module result, or <code>null</code> if a matching module result could not be found
	*/
	public ModuleResult fetchByModuleId_Last(long moduleId,
		com.liferay.portal.kernel.util.OrderByComparator<ModuleResult> orderByComparator);

	/**
	* Returns the module results before and after the current module result in the ordered set where moduleId = &#63;.
	*
	* @param mrId the primary key of the current module result
	* @param moduleId the module ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next module result
	* @throws NoSuchModuleResultException if a module result with the primary key could not be found
	*/
	public ModuleResult[] findByModuleId_PrevAndNext(long mrId, long moduleId,
		com.liferay.portal.kernel.util.OrderByComparator<ModuleResult> orderByComparator)
		throws NoSuchModuleResultException;

	/**
	* Removes all the module results where moduleId = &#63; from the database.
	*
	* @param moduleId the module ID
	*/
	public void removeByModuleId(long moduleId);

	/**
	* Returns the number of module results where moduleId = &#63;.
	*
	* @param moduleId the module ID
	* @return the number of matching module results
	*/
	public int countByModuleId(long moduleId);

	/**
	* Returns the module result where moduleId = &#63; and userId = &#63; or throws a {@link NoSuchModuleResultException} if it could not be found.
	*
	* @param moduleId the module ID
	* @param userId the user ID
	* @return the matching module result
	* @throws NoSuchModuleResultException if a matching module result could not be found
	*/
	public ModuleResult findByModuleIdUserId(long moduleId, long userId)
		throws NoSuchModuleResultException;

	/**
	* Returns the module result where moduleId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param moduleId the module ID
	* @param userId the user ID
	* @return the matching module result, or <code>null</code> if a matching module result could not be found
	*/
	public ModuleResult fetchByModuleIdUserId(long moduleId, long userId);

	/**
	* Returns the module result where moduleId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param moduleId the module ID
	* @param userId the user ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching module result, or <code>null</code> if a matching module result could not be found
	*/
	public ModuleResult fetchByModuleIdUserId(long moduleId, long userId,
		boolean retrieveFromCache);

	/**
	* Removes the module result where moduleId = &#63; and userId = &#63; from the database.
	*
	* @param moduleId the module ID
	* @param userId the user ID
	* @return the module result that was removed
	*/
	public ModuleResult removeByModuleIdUserId(long moduleId, long userId)
		throws NoSuchModuleResultException;

	/**
	* Returns the number of module results where moduleId = &#63; and userId = &#63;.
	*
	* @param moduleId the module ID
	* @param userId the user ID
	* @return the number of matching module results
	*/
	public int countByModuleIdUserId(long moduleId, long userId);

	/**
	* Caches the module result in the entity cache if it is enabled.
	*
	* @param moduleResult the module result
	*/
	public void cacheResult(ModuleResult moduleResult);

	/**
	* Caches the module results in the entity cache if it is enabled.
	*
	* @param moduleResults the module results
	*/
	public void cacheResult(java.util.List<ModuleResult> moduleResults);

	/**
	* Creates a new module result with the primary key. Does not add the module result to the database.
	*
	* @param mrId the primary key for the new module result
	* @return the new module result
	*/
	public ModuleResult create(long mrId);

	/**
	* Removes the module result with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param mrId the primary key of the module result
	* @return the module result that was removed
	* @throws NoSuchModuleResultException if a module result with the primary key could not be found
	*/
	public ModuleResult remove(long mrId) throws NoSuchModuleResultException;

	public ModuleResult updateImpl(ModuleResult moduleResult);

	/**
	* Returns the module result with the primary key or throws a {@link NoSuchModuleResultException} if it could not be found.
	*
	* @param mrId the primary key of the module result
	* @return the module result
	* @throws NoSuchModuleResultException if a module result with the primary key could not be found
	*/
	public ModuleResult findByPrimaryKey(long mrId)
		throws NoSuchModuleResultException;

	/**
	* Returns the module result with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param mrId the primary key of the module result
	* @return the module result, or <code>null</code> if a module result with the primary key could not be found
	*/
	public ModuleResult fetchByPrimaryKey(long mrId);

	@Override
	public java.util.Map<java.io.Serializable, ModuleResult> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the module results.
	*
	* @return the module results
	*/
	public java.util.List<ModuleResult> findAll();

	/**
	* Returns a range of all the module results.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of module results
	* @param end the upper bound of the range of module results (not inclusive)
	* @return the range of module results
	*/
	public java.util.List<ModuleResult> findAll(int start, int end);

	/**
	* Returns an ordered range of all the module results.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of module results
	* @param end the upper bound of the range of module results (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of module results
	*/
	public java.util.List<ModuleResult> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ModuleResult> orderByComparator);

	/**
	* Returns an ordered range of all the module results.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of module results
	* @param end the upper bound of the range of module results (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of module results
	*/
	public java.util.List<ModuleResult> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ModuleResult> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the module results from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of module results.
	*
	* @return the number of module results
	*/
	public int countAll();
}