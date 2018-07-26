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

import com.ted.lms.exception.NoSuchLearningActivityResultException;
import com.ted.lms.model.LearningActivityResult;

/**
 * The persistence interface for the learning activity result service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ted.lms.service.persistence.impl.LearningActivityResultPersistenceImpl
 * @see LearningActivityResultUtil
 * @generated
 */
@ProviderType
public interface LearningActivityResultPersistence extends BasePersistence<LearningActivityResult> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LearningActivityResultUtil} to access the learning activity result persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the learning activity results where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching learning activity results
	*/
	public java.util.List<LearningActivityResult> findByUuid(String uuid);

	/**
	* Returns a range of all the learning activity results where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of learning activity results
	* @param end the upper bound of the range of learning activity results (not inclusive)
	* @return the range of matching learning activity results
	*/
	public java.util.List<LearningActivityResult> findByUuid(String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the learning activity results where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of learning activity results
	* @param end the upper bound of the range of learning activity results (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching learning activity results
	*/
	public java.util.List<LearningActivityResult> findByUuid(String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivityResult> orderByComparator);

	/**
	* Returns an ordered range of all the learning activity results where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of learning activity results
	* @param end the upper bound of the range of learning activity results (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching learning activity results
	*/
	public java.util.List<LearningActivityResult> findByUuid(String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivityResult> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first learning activity result in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching learning activity result
	* @throws NoSuchLearningActivityResultException if a matching learning activity result could not be found
	*/
	public LearningActivityResult findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivityResult> orderByComparator)
		throws NoSuchLearningActivityResultException;

	/**
	* Returns the first learning activity result in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching learning activity result, or <code>null</code> if a matching learning activity result could not be found
	*/
	public LearningActivityResult fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivityResult> orderByComparator);

	/**
	* Returns the last learning activity result in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching learning activity result
	* @throws NoSuchLearningActivityResultException if a matching learning activity result could not be found
	*/
	public LearningActivityResult findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivityResult> orderByComparator)
		throws NoSuchLearningActivityResultException;

	/**
	* Returns the last learning activity result in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching learning activity result, or <code>null</code> if a matching learning activity result could not be found
	*/
	public LearningActivityResult fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivityResult> orderByComparator);

	/**
	* Returns the learning activity results before and after the current learning activity result in the ordered set where uuid = &#63;.
	*
	* @param larId the primary key of the current learning activity result
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next learning activity result
	* @throws NoSuchLearningActivityResultException if a learning activity result with the primary key could not be found
	*/
	public LearningActivityResult[] findByUuid_PrevAndNext(long larId,
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivityResult> orderByComparator)
		throws NoSuchLearningActivityResultException;

	/**
	* Removes all the learning activity results where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of learning activity results where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching learning activity results
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the learning activity result where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchLearningActivityResultException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching learning activity result
	* @throws NoSuchLearningActivityResultException if a matching learning activity result could not be found
	*/
	public LearningActivityResult findByUUID_G(String uuid, long groupId)
		throws NoSuchLearningActivityResultException;

	/**
	* Returns the learning activity result where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching learning activity result, or <code>null</code> if a matching learning activity result could not be found
	*/
	public LearningActivityResult fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the learning activity result where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching learning activity result, or <code>null</code> if a matching learning activity result could not be found
	*/
	public LearningActivityResult fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the learning activity result where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the learning activity result that was removed
	*/
	public LearningActivityResult removeByUUID_G(String uuid, long groupId)
		throws NoSuchLearningActivityResultException;

	/**
	* Returns the number of learning activity results where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching learning activity results
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the learning activity results where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching learning activity results
	*/
	public java.util.List<LearningActivityResult> findByUuid_C(String uuid,
		long companyId);

	/**
	* Returns a range of all the learning activity results where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of learning activity results
	* @param end the upper bound of the range of learning activity results (not inclusive)
	* @return the range of matching learning activity results
	*/
	public java.util.List<LearningActivityResult> findByUuid_C(String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the learning activity results where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of learning activity results
	* @param end the upper bound of the range of learning activity results (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching learning activity results
	*/
	public java.util.List<LearningActivityResult> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivityResult> orderByComparator);

	/**
	* Returns an ordered range of all the learning activity results where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of learning activity results
	* @param end the upper bound of the range of learning activity results (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching learning activity results
	*/
	public java.util.List<LearningActivityResult> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivityResult> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first learning activity result in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching learning activity result
	* @throws NoSuchLearningActivityResultException if a matching learning activity result could not be found
	*/
	public LearningActivityResult findByUuid_C_First(String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivityResult> orderByComparator)
		throws NoSuchLearningActivityResultException;

	/**
	* Returns the first learning activity result in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching learning activity result, or <code>null</code> if a matching learning activity result could not be found
	*/
	public LearningActivityResult fetchByUuid_C_First(String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivityResult> orderByComparator);

	/**
	* Returns the last learning activity result in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching learning activity result
	* @throws NoSuchLearningActivityResultException if a matching learning activity result could not be found
	*/
	public LearningActivityResult findByUuid_C_Last(String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivityResult> orderByComparator)
		throws NoSuchLearningActivityResultException;

	/**
	* Returns the last learning activity result in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching learning activity result, or <code>null</code> if a matching learning activity result could not be found
	*/
	public LearningActivityResult fetchByUuid_C_Last(String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivityResult> orderByComparator);

	/**
	* Returns the learning activity results before and after the current learning activity result in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param larId the primary key of the current learning activity result
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next learning activity result
	* @throws NoSuchLearningActivityResultException if a learning activity result with the primary key could not be found
	*/
	public LearningActivityResult[] findByUuid_C_PrevAndNext(long larId,
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivityResult> orderByComparator)
		throws NoSuchLearningActivityResultException;

	/**
	* Removes all the learning activity results where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of learning activity results where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching learning activity results
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns all the learning activity results where actId = &#63;.
	*
	* @param actId the act ID
	* @return the matching learning activity results
	*/
	public java.util.List<LearningActivityResult> findByActId(long actId);

	/**
	* Returns a range of all the learning activity results where actId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param actId the act ID
	* @param start the lower bound of the range of learning activity results
	* @param end the upper bound of the range of learning activity results (not inclusive)
	* @return the range of matching learning activity results
	*/
	public java.util.List<LearningActivityResult> findByActId(long actId,
		int start, int end);

	/**
	* Returns an ordered range of all the learning activity results where actId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param actId the act ID
	* @param start the lower bound of the range of learning activity results
	* @param end the upper bound of the range of learning activity results (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching learning activity results
	*/
	public java.util.List<LearningActivityResult> findByActId(long actId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivityResult> orderByComparator);

	/**
	* Returns an ordered range of all the learning activity results where actId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param actId the act ID
	* @param start the lower bound of the range of learning activity results
	* @param end the upper bound of the range of learning activity results (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching learning activity results
	*/
	public java.util.List<LearningActivityResult> findByActId(long actId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivityResult> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first learning activity result in the ordered set where actId = &#63;.
	*
	* @param actId the act ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching learning activity result
	* @throws NoSuchLearningActivityResultException if a matching learning activity result could not be found
	*/
	public LearningActivityResult findByActId_First(long actId,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivityResult> orderByComparator)
		throws NoSuchLearningActivityResultException;

	/**
	* Returns the first learning activity result in the ordered set where actId = &#63;.
	*
	* @param actId the act ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching learning activity result, or <code>null</code> if a matching learning activity result could not be found
	*/
	public LearningActivityResult fetchByActId_First(long actId,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivityResult> orderByComparator);

	/**
	* Returns the last learning activity result in the ordered set where actId = &#63;.
	*
	* @param actId the act ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching learning activity result
	* @throws NoSuchLearningActivityResultException if a matching learning activity result could not be found
	*/
	public LearningActivityResult findByActId_Last(long actId,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivityResult> orderByComparator)
		throws NoSuchLearningActivityResultException;

	/**
	* Returns the last learning activity result in the ordered set where actId = &#63;.
	*
	* @param actId the act ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching learning activity result, or <code>null</code> if a matching learning activity result could not be found
	*/
	public LearningActivityResult fetchByActId_Last(long actId,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivityResult> orderByComparator);

	/**
	* Returns the learning activity results before and after the current learning activity result in the ordered set where actId = &#63;.
	*
	* @param larId the primary key of the current learning activity result
	* @param actId the act ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next learning activity result
	* @throws NoSuchLearningActivityResultException if a learning activity result with the primary key could not be found
	*/
	public LearningActivityResult[] findByActId_PrevAndNext(long larId,
		long actId,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivityResult> orderByComparator)
		throws NoSuchLearningActivityResultException;

	/**
	* Removes all the learning activity results where actId = &#63; from the database.
	*
	* @param actId the act ID
	*/
	public void removeByActId(long actId);

	/**
	* Returns the number of learning activity results where actId = &#63;.
	*
	* @param actId the act ID
	* @return the number of matching learning activity results
	*/
	public int countByActId(long actId);

	/**
	* Returns the learning activity result where actId = &#63; and userId = &#63; or throws a {@link NoSuchLearningActivityResultException} if it could not be found.
	*
	* @param actId the act ID
	* @param userId the user ID
	* @return the matching learning activity result
	* @throws NoSuchLearningActivityResultException if a matching learning activity result could not be found
	*/
	public LearningActivityResult findByActIdUserId(long actId, long userId)
		throws NoSuchLearningActivityResultException;

	/**
	* Returns the learning activity result where actId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param actId the act ID
	* @param userId the user ID
	* @return the matching learning activity result, or <code>null</code> if a matching learning activity result could not be found
	*/
	public LearningActivityResult fetchByActIdUserId(long actId, long userId);

	/**
	* Returns the learning activity result where actId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param actId the act ID
	* @param userId the user ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching learning activity result, or <code>null</code> if a matching learning activity result could not be found
	*/
	public LearningActivityResult fetchByActIdUserId(long actId, long userId,
		boolean retrieveFromCache);

	/**
	* Removes the learning activity result where actId = &#63; and userId = &#63; from the database.
	*
	* @param actId the act ID
	* @param userId the user ID
	* @return the learning activity result that was removed
	*/
	public LearningActivityResult removeByActIdUserId(long actId, long userId)
		throws NoSuchLearningActivityResultException;

	/**
	* Returns the number of learning activity results where actId = &#63; and userId = &#63;.
	*
	* @param actId the act ID
	* @param userId the user ID
	* @return the number of matching learning activity results
	*/
	public int countByActIdUserId(long actId, long userId);

	/**
	* Caches the learning activity result in the entity cache if it is enabled.
	*
	* @param learningActivityResult the learning activity result
	*/
	public void cacheResult(LearningActivityResult learningActivityResult);

	/**
	* Caches the learning activity results in the entity cache if it is enabled.
	*
	* @param learningActivityResults the learning activity results
	*/
	public void cacheResult(
		java.util.List<LearningActivityResult> learningActivityResults);

	/**
	* Creates a new learning activity result with the primary key. Does not add the learning activity result to the database.
	*
	* @param larId the primary key for the new learning activity result
	* @return the new learning activity result
	*/
	public LearningActivityResult create(long larId);

	/**
	* Removes the learning activity result with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param larId the primary key of the learning activity result
	* @return the learning activity result that was removed
	* @throws NoSuchLearningActivityResultException if a learning activity result with the primary key could not be found
	*/
	public LearningActivityResult remove(long larId)
		throws NoSuchLearningActivityResultException;

	public LearningActivityResult updateImpl(
		LearningActivityResult learningActivityResult);

	/**
	* Returns the learning activity result with the primary key or throws a {@link NoSuchLearningActivityResultException} if it could not be found.
	*
	* @param larId the primary key of the learning activity result
	* @return the learning activity result
	* @throws NoSuchLearningActivityResultException if a learning activity result with the primary key could not be found
	*/
	public LearningActivityResult findByPrimaryKey(long larId)
		throws NoSuchLearningActivityResultException;

	/**
	* Returns the learning activity result with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param larId the primary key of the learning activity result
	* @return the learning activity result, or <code>null</code> if a learning activity result with the primary key could not be found
	*/
	public LearningActivityResult fetchByPrimaryKey(long larId);

	@Override
	public java.util.Map<java.io.Serializable, LearningActivityResult> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the learning activity results.
	*
	* @return the learning activity results
	*/
	public java.util.List<LearningActivityResult> findAll();

	/**
	* Returns a range of all the learning activity results.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of learning activity results
	* @param end the upper bound of the range of learning activity results (not inclusive)
	* @return the range of learning activity results
	*/
	public java.util.List<LearningActivityResult> findAll(int start, int end);

	/**
	* Returns an ordered range of all the learning activity results.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of learning activity results
	* @param end the upper bound of the range of learning activity results (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of learning activity results
	*/
	public java.util.List<LearningActivityResult> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivityResult> orderByComparator);

	/**
	* Returns an ordered range of all the learning activity results.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of learning activity results
	* @param end the upper bound of the range of learning activity results (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of learning activity results
	*/
	public java.util.List<LearningActivityResult> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivityResult> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the learning activity results from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of learning activity results.
	*
	* @return the number of learning activity results
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}