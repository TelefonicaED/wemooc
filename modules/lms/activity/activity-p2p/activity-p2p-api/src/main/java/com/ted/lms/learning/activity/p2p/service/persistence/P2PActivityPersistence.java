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

package com.ted.lms.learning.activity.p2p.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.ted.lms.learning.activity.p2p.exception.NoSuchP2PActivityException;
import com.ted.lms.learning.activity.p2p.model.P2PActivity;

/**
 * The persistence interface for the p2p activity service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ted.lms.learning.activity.p2p.service.persistence.impl.P2PActivityPersistenceImpl
 * @see P2PActivityUtil
 * @generated
 */
@ProviderType
public interface P2PActivityPersistence extends BasePersistence<P2PActivity> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link P2PActivityUtil} to access the p2p activity persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the p2p activities where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching p2p activities
	*/
	public java.util.List<P2PActivity> findByUuid(String uuid);

	/**
	* Returns a range of all the p2p activities where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of p2p activities
	* @param end the upper bound of the range of p2p activities (not inclusive)
	* @return the range of matching p2p activities
	*/
	public java.util.List<P2PActivity> findByUuid(String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the p2p activities where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of p2p activities
	* @param end the upper bound of the range of p2p activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching p2p activities
	*/
	public java.util.List<P2PActivity> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivity> orderByComparator);

	/**
	* Returns an ordered range of all the p2p activities where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of p2p activities
	* @param end the upper bound of the range of p2p activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching p2p activities
	*/
	public java.util.List<P2PActivity> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivity> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first p2p activity in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching p2p activity
	* @throws NoSuchP2PActivityException if a matching p2p activity could not be found
	*/
	public P2PActivity findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivity> orderByComparator)
		throws NoSuchP2PActivityException;

	/**
	* Returns the first p2p activity in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching p2p activity, or <code>null</code> if a matching p2p activity could not be found
	*/
	public P2PActivity fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivity> orderByComparator);

	/**
	* Returns the last p2p activity in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching p2p activity
	* @throws NoSuchP2PActivityException if a matching p2p activity could not be found
	*/
	public P2PActivity findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivity> orderByComparator)
		throws NoSuchP2PActivityException;

	/**
	* Returns the last p2p activity in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching p2p activity, or <code>null</code> if a matching p2p activity could not be found
	*/
	public P2PActivity fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivity> orderByComparator);

	/**
	* Returns the p2p activities before and after the current p2p activity in the ordered set where uuid = &#63;.
	*
	* @param p2pActivityId the primary key of the current p2p activity
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next p2p activity
	* @throws NoSuchP2PActivityException if a p2p activity with the primary key could not be found
	*/
	public P2PActivity[] findByUuid_PrevAndNext(long p2pActivityId,
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivity> orderByComparator)
		throws NoSuchP2PActivityException;

	/**
	* Removes all the p2p activities where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of p2p activities where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching p2p activities
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the p2p activity where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchP2PActivityException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching p2p activity
	* @throws NoSuchP2PActivityException if a matching p2p activity could not be found
	*/
	public P2PActivity findByUUID_G(String uuid, long groupId)
		throws NoSuchP2PActivityException;

	/**
	* Returns the p2p activity where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching p2p activity, or <code>null</code> if a matching p2p activity could not be found
	*/
	public P2PActivity fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the p2p activity where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching p2p activity, or <code>null</code> if a matching p2p activity could not be found
	*/
	public P2PActivity fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the p2p activity where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the p2p activity that was removed
	*/
	public P2PActivity removeByUUID_G(String uuid, long groupId)
		throws NoSuchP2PActivityException;

	/**
	* Returns the number of p2p activities where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching p2p activities
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the p2p activities where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching p2p activities
	*/
	public java.util.List<P2PActivity> findByUuid_C(String uuid, long companyId);

	/**
	* Returns a range of all the p2p activities where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of p2p activities
	* @param end the upper bound of the range of p2p activities (not inclusive)
	* @return the range of matching p2p activities
	*/
	public java.util.List<P2PActivity> findByUuid_C(String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the p2p activities where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of p2p activities
	* @param end the upper bound of the range of p2p activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching p2p activities
	*/
	public java.util.List<P2PActivity> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivity> orderByComparator);

	/**
	* Returns an ordered range of all the p2p activities where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of p2p activities
	* @param end the upper bound of the range of p2p activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching p2p activities
	*/
	public java.util.List<P2PActivity> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivity> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first p2p activity in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching p2p activity
	* @throws NoSuchP2PActivityException if a matching p2p activity could not be found
	*/
	public P2PActivity findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivity> orderByComparator)
		throws NoSuchP2PActivityException;

	/**
	* Returns the first p2p activity in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching p2p activity, or <code>null</code> if a matching p2p activity could not be found
	*/
	public P2PActivity fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivity> orderByComparator);

	/**
	* Returns the last p2p activity in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching p2p activity
	* @throws NoSuchP2PActivityException if a matching p2p activity could not be found
	*/
	public P2PActivity findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivity> orderByComparator)
		throws NoSuchP2PActivityException;

	/**
	* Returns the last p2p activity in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching p2p activity, or <code>null</code> if a matching p2p activity could not be found
	*/
	public P2PActivity fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivity> orderByComparator);

	/**
	* Returns the p2p activities before and after the current p2p activity in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param p2pActivityId the primary key of the current p2p activity
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next p2p activity
	* @throws NoSuchP2PActivityException if a p2p activity with the primary key could not be found
	*/
	public P2PActivity[] findByUuid_C_PrevAndNext(long p2pActivityId,
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivity> orderByComparator)
		throws NoSuchP2PActivityException;

	/**
	* Removes all the p2p activities where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of p2p activities where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching p2p activities
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns the p2p activity where actId = &#63; and userId = &#63; or throws a {@link NoSuchP2PActivityException} if it could not be found.
	*
	* @param actId the act ID
	* @param userId the user ID
	* @return the matching p2p activity
	* @throws NoSuchP2PActivityException if a matching p2p activity could not be found
	*/
	public P2PActivity findByActIdUserId(long actId, long userId)
		throws NoSuchP2PActivityException;

	/**
	* Returns the p2p activity where actId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param actId the act ID
	* @param userId the user ID
	* @return the matching p2p activity, or <code>null</code> if a matching p2p activity could not be found
	*/
	public P2PActivity fetchByActIdUserId(long actId, long userId);

	/**
	* Returns the p2p activity where actId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param actId the act ID
	* @param userId the user ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching p2p activity, or <code>null</code> if a matching p2p activity could not be found
	*/
	public P2PActivity fetchByActIdUserId(long actId, long userId,
		boolean retrieveFromCache);

	/**
	* Removes the p2p activity where actId = &#63; and userId = &#63; from the database.
	*
	* @param actId the act ID
	* @param userId the user ID
	* @return the p2p activity that was removed
	*/
	public P2PActivity removeByActIdUserId(long actId, long userId)
		throws NoSuchP2PActivityException;

	/**
	* Returns the number of p2p activities where actId = &#63; and userId = &#63;.
	*
	* @param actId the act ID
	* @param userId the user ID
	* @return the number of matching p2p activities
	*/
	public int countByActIdUserId(long actId, long userId);

	/**
	* Returns all the p2p activities where actId = &#63;.
	*
	* @param actId the act ID
	* @return the matching p2p activities
	*/
	public java.util.List<P2PActivity> findByActId(long actId);

	/**
	* Returns a range of all the p2p activities where actId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param actId the act ID
	* @param start the lower bound of the range of p2p activities
	* @param end the upper bound of the range of p2p activities (not inclusive)
	* @return the range of matching p2p activities
	*/
	public java.util.List<P2PActivity> findByActId(long actId, int start,
		int end);

	/**
	* Returns an ordered range of all the p2p activities where actId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param actId the act ID
	* @param start the lower bound of the range of p2p activities
	* @param end the upper bound of the range of p2p activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching p2p activities
	*/
	public java.util.List<P2PActivity> findByActId(long actId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivity> orderByComparator);

	/**
	* Returns an ordered range of all the p2p activities where actId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param actId the act ID
	* @param start the lower bound of the range of p2p activities
	* @param end the upper bound of the range of p2p activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching p2p activities
	*/
	public java.util.List<P2PActivity> findByActId(long actId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivity> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first p2p activity in the ordered set where actId = &#63;.
	*
	* @param actId the act ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching p2p activity
	* @throws NoSuchP2PActivityException if a matching p2p activity could not be found
	*/
	public P2PActivity findByActId_First(long actId,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivity> orderByComparator)
		throws NoSuchP2PActivityException;

	/**
	* Returns the first p2p activity in the ordered set where actId = &#63;.
	*
	* @param actId the act ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching p2p activity, or <code>null</code> if a matching p2p activity could not be found
	*/
	public P2PActivity fetchByActId_First(long actId,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivity> orderByComparator);

	/**
	* Returns the last p2p activity in the ordered set where actId = &#63;.
	*
	* @param actId the act ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching p2p activity
	* @throws NoSuchP2PActivityException if a matching p2p activity could not be found
	*/
	public P2PActivity findByActId_Last(long actId,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivity> orderByComparator)
		throws NoSuchP2PActivityException;

	/**
	* Returns the last p2p activity in the ordered set where actId = &#63;.
	*
	* @param actId the act ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching p2p activity, or <code>null</code> if a matching p2p activity could not be found
	*/
	public P2PActivity fetchByActId_Last(long actId,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivity> orderByComparator);

	/**
	* Returns the p2p activities before and after the current p2p activity in the ordered set where actId = &#63;.
	*
	* @param p2pActivityId the primary key of the current p2p activity
	* @param actId the act ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next p2p activity
	* @throws NoSuchP2PActivityException if a p2p activity with the primary key could not be found
	*/
	public P2PActivity[] findByActId_PrevAndNext(long p2pActivityId,
		long actId,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivity> orderByComparator)
		throws NoSuchP2PActivityException;

	/**
	* Removes all the p2p activities where actId = &#63; from the database.
	*
	* @param actId the act ID
	*/
	public void removeByActId(long actId);

	/**
	* Returns the number of p2p activities where actId = &#63;.
	*
	* @param actId the act ID
	* @return the number of matching p2p activities
	*/
	public int countByActId(long actId);

	/**
	* Returns all the p2p activities where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching p2p activities
	*/
	public java.util.List<P2PActivity> findByUserId(long userId);

	/**
	* Returns a range of all the p2p activities where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of p2p activities
	* @param end the upper bound of the range of p2p activities (not inclusive)
	* @return the range of matching p2p activities
	*/
	public java.util.List<P2PActivity> findByUserId(long userId, int start,
		int end);

	/**
	* Returns an ordered range of all the p2p activities where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of p2p activities
	* @param end the upper bound of the range of p2p activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching p2p activities
	*/
	public java.util.List<P2PActivity> findByUserId(long userId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivity> orderByComparator);

	/**
	* Returns an ordered range of all the p2p activities where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of p2p activities
	* @param end the upper bound of the range of p2p activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching p2p activities
	*/
	public java.util.List<P2PActivity> findByUserId(long userId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivity> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first p2p activity in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching p2p activity
	* @throws NoSuchP2PActivityException if a matching p2p activity could not be found
	*/
	public P2PActivity findByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivity> orderByComparator)
		throws NoSuchP2PActivityException;

	/**
	* Returns the first p2p activity in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching p2p activity, or <code>null</code> if a matching p2p activity could not be found
	*/
	public P2PActivity fetchByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivity> orderByComparator);

	/**
	* Returns the last p2p activity in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching p2p activity
	* @throws NoSuchP2PActivityException if a matching p2p activity could not be found
	*/
	public P2PActivity findByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivity> orderByComparator)
		throws NoSuchP2PActivityException;

	/**
	* Returns the last p2p activity in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching p2p activity, or <code>null</code> if a matching p2p activity could not be found
	*/
	public P2PActivity fetchByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivity> orderByComparator);

	/**
	* Returns the p2p activities before and after the current p2p activity in the ordered set where userId = &#63;.
	*
	* @param p2pActivityId the primary key of the current p2p activity
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next p2p activity
	* @throws NoSuchP2PActivityException if a p2p activity with the primary key could not be found
	*/
	public P2PActivity[] findByUserId_PrevAndNext(long p2pActivityId,
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivity> orderByComparator)
		throws NoSuchP2PActivityException;

	/**
	* Removes all the p2p activities where userId = &#63; from the database.
	*
	* @param userId the user ID
	*/
	public void removeByUserId(long userId);

	/**
	* Returns the number of p2p activities where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching p2p activities
	*/
	public int countByUserId(long userId);

	/**
	* Returns all the p2p activities where asignationsCompleted = &#63;.
	*
	* @param asignationsCompleted the asignations completed
	* @return the matching p2p activities
	*/
	public java.util.List<P2PActivity> findByAsignationsCompleted(
		boolean asignationsCompleted);

	/**
	* Returns a range of all the p2p activities where asignationsCompleted = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param asignationsCompleted the asignations completed
	* @param start the lower bound of the range of p2p activities
	* @param end the upper bound of the range of p2p activities (not inclusive)
	* @return the range of matching p2p activities
	*/
	public java.util.List<P2PActivity> findByAsignationsCompleted(
		boolean asignationsCompleted, int start, int end);

	/**
	* Returns an ordered range of all the p2p activities where asignationsCompleted = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param asignationsCompleted the asignations completed
	* @param start the lower bound of the range of p2p activities
	* @param end the upper bound of the range of p2p activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching p2p activities
	*/
	public java.util.List<P2PActivity> findByAsignationsCompleted(
		boolean asignationsCompleted, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivity> orderByComparator);

	/**
	* Returns an ordered range of all the p2p activities where asignationsCompleted = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param asignationsCompleted the asignations completed
	* @param start the lower bound of the range of p2p activities
	* @param end the upper bound of the range of p2p activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching p2p activities
	*/
	public java.util.List<P2PActivity> findByAsignationsCompleted(
		boolean asignationsCompleted, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivity> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first p2p activity in the ordered set where asignationsCompleted = &#63;.
	*
	* @param asignationsCompleted the asignations completed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching p2p activity
	* @throws NoSuchP2PActivityException if a matching p2p activity could not be found
	*/
	public P2PActivity findByAsignationsCompleted_First(
		boolean asignationsCompleted,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivity> orderByComparator)
		throws NoSuchP2PActivityException;

	/**
	* Returns the first p2p activity in the ordered set where asignationsCompleted = &#63;.
	*
	* @param asignationsCompleted the asignations completed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching p2p activity, or <code>null</code> if a matching p2p activity could not be found
	*/
	public P2PActivity fetchByAsignationsCompleted_First(
		boolean asignationsCompleted,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivity> orderByComparator);

	/**
	* Returns the last p2p activity in the ordered set where asignationsCompleted = &#63;.
	*
	* @param asignationsCompleted the asignations completed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching p2p activity
	* @throws NoSuchP2PActivityException if a matching p2p activity could not be found
	*/
	public P2PActivity findByAsignationsCompleted_Last(
		boolean asignationsCompleted,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivity> orderByComparator)
		throws NoSuchP2PActivityException;

	/**
	* Returns the last p2p activity in the ordered set where asignationsCompleted = &#63;.
	*
	* @param asignationsCompleted the asignations completed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching p2p activity, or <code>null</code> if a matching p2p activity could not be found
	*/
	public P2PActivity fetchByAsignationsCompleted_Last(
		boolean asignationsCompleted,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivity> orderByComparator);

	/**
	* Returns the p2p activities before and after the current p2p activity in the ordered set where asignationsCompleted = &#63;.
	*
	* @param p2pActivityId the primary key of the current p2p activity
	* @param asignationsCompleted the asignations completed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next p2p activity
	* @throws NoSuchP2PActivityException if a p2p activity with the primary key could not be found
	*/
	public P2PActivity[] findByAsignationsCompleted_PrevAndNext(
		long p2pActivityId, boolean asignationsCompleted,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivity> orderByComparator)
		throws NoSuchP2PActivityException;

	/**
	* Removes all the p2p activities where asignationsCompleted = &#63; from the database.
	*
	* @param asignationsCompleted the asignations completed
	*/
	public void removeByAsignationsCompleted(boolean asignationsCompleted);

	/**
	* Returns the number of p2p activities where asignationsCompleted = &#63;.
	*
	* @param asignationsCompleted the asignations completed
	* @return the number of matching p2p activities
	*/
	public int countByAsignationsCompleted(boolean asignationsCompleted);

	/**
	* Caches the p2p activity in the entity cache if it is enabled.
	*
	* @param p2pActivity the p2p activity
	*/
	public void cacheResult(P2PActivity p2pActivity);

	/**
	* Caches the p2p activities in the entity cache if it is enabled.
	*
	* @param p2pActivities the p2p activities
	*/
	public void cacheResult(java.util.List<P2PActivity> p2pActivities);

	/**
	* Creates a new p2p activity with the primary key. Does not add the p2p activity to the database.
	*
	* @param p2pActivityId the primary key for the new p2p activity
	* @return the new p2p activity
	*/
	public P2PActivity create(long p2pActivityId);

	/**
	* Removes the p2p activity with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param p2pActivityId the primary key of the p2p activity
	* @return the p2p activity that was removed
	* @throws NoSuchP2PActivityException if a p2p activity with the primary key could not be found
	*/
	public P2PActivity remove(long p2pActivityId)
		throws NoSuchP2PActivityException;

	public P2PActivity updateImpl(P2PActivity p2pActivity);

	/**
	* Returns the p2p activity with the primary key or throws a {@link NoSuchP2PActivityException} if it could not be found.
	*
	* @param p2pActivityId the primary key of the p2p activity
	* @return the p2p activity
	* @throws NoSuchP2PActivityException if a p2p activity with the primary key could not be found
	*/
	public P2PActivity findByPrimaryKey(long p2pActivityId)
		throws NoSuchP2PActivityException;

	/**
	* Returns the p2p activity with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param p2pActivityId the primary key of the p2p activity
	* @return the p2p activity, or <code>null</code> if a p2p activity with the primary key could not be found
	*/
	public P2PActivity fetchByPrimaryKey(long p2pActivityId);

	@Override
	public java.util.Map<java.io.Serializable, P2PActivity> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the p2p activities.
	*
	* @return the p2p activities
	*/
	public java.util.List<P2PActivity> findAll();

	/**
	* Returns a range of all the p2p activities.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of p2p activities
	* @param end the upper bound of the range of p2p activities (not inclusive)
	* @return the range of p2p activities
	*/
	public java.util.List<P2PActivity> findAll(int start, int end);

	/**
	* Returns an ordered range of all the p2p activities.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of p2p activities
	* @param end the upper bound of the range of p2p activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of p2p activities
	*/
	public java.util.List<P2PActivity> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivity> orderByComparator);

	/**
	* Returns an ordered range of all the p2p activities.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of p2p activities
	* @param end the upper bound of the range of p2p activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of p2p activities
	*/
	public java.util.List<P2PActivity> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivity> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the p2p activities from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of p2p activities.
	*
	* @return the number of p2p activities
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}