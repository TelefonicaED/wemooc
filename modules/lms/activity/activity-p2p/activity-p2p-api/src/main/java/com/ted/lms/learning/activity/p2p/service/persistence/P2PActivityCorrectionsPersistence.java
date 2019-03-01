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

import com.ted.lms.learning.activity.p2p.exception.NoSuchP2PActivityCorrectionsException;
import com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections;

/**
 * The persistence interface for the p2p activity corrections service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ted.lms.learning.activity.p2p.service.persistence.impl.P2PActivityCorrectionsPersistenceImpl
 * @see P2PActivityCorrectionsUtil
 * @generated
 */
@ProviderType
public interface P2PActivityCorrectionsPersistence extends BasePersistence<P2PActivityCorrections> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link P2PActivityCorrectionsUtil} to access the p2p activity corrections persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the p2p activity correctionses where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching p2p activity correctionses
	*/
	public java.util.List<P2PActivityCorrections> findByUuid(String uuid);

	/**
	* Returns a range of all the p2p activity correctionses where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityCorrectionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of p2p activity correctionses
	* @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	* @return the range of matching p2p activity correctionses
	*/
	public java.util.List<P2PActivityCorrections> findByUuid(String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the p2p activity correctionses where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityCorrectionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of p2p activity correctionses
	* @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching p2p activity correctionses
	*/
	public java.util.List<P2PActivityCorrections> findByUuid(String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivityCorrections> orderByComparator);

	/**
	* Returns an ordered range of all the p2p activity correctionses where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityCorrectionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of p2p activity correctionses
	* @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching p2p activity correctionses
	*/
	public java.util.List<P2PActivityCorrections> findByUuid(String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivityCorrections> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first p2p activity corrections in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching p2p activity corrections
	* @throws NoSuchP2PActivityCorrectionsException if a matching p2p activity corrections could not be found
	*/
	public P2PActivityCorrections findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws NoSuchP2PActivityCorrectionsException;

	/**
	* Returns the first p2p activity corrections in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching p2p activity corrections, or <code>null</code> if a matching p2p activity corrections could not be found
	*/
	public P2PActivityCorrections fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivityCorrections> orderByComparator);

	/**
	* Returns the last p2p activity corrections in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching p2p activity corrections
	* @throws NoSuchP2PActivityCorrectionsException if a matching p2p activity corrections could not be found
	*/
	public P2PActivityCorrections findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws NoSuchP2PActivityCorrectionsException;

	/**
	* Returns the last p2p activity corrections in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching p2p activity corrections, or <code>null</code> if a matching p2p activity corrections could not be found
	*/
	public P2PActivityCorrections fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivityCorrections> orderByComparator);

	/**
	* Returns the p2p activity correctionses before and after the current p2p activity corrections in the ordered set where uuid = &#63;.
	*
	* @param p2pActivityCorrectionsId the primary key of the current p2p activity corrections
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next p2p activity corrections
	* @throws NoSuchP2PActivityCorrectionsException if a p2p activity corrections with the primary key could not be found
	*/
	public P2PActivityCorrections[] findByUuid_PrevAndNext(
		long p2pActivityCorrectionsId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws NoSuchP2PActivityCorrectionsException;

	/**
	* Removes all the p2p activity correctionses where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of p2p activity correctionses where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching p2p activity correctionses
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the p2p activity corrections where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchP2PActivityCorrectionsException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching p2p activity corrections
	* @throws NoSuchP2PActivityCorrectionsException if a matching p2p activity corrections could not be found
	*/
	public P2PActivityCorrections findByUUID_G(String uuid, long groupId)
		throws NoSuchP2PActivityCorrectionsException;

	/**
	* Returns the p2p activity corrections where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching p2p activity corrections, or <code>null</code> if a matching p2p activity corrections could not be found
	*/
	public P2PActivityCorrections fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the p2p activity corrections where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching p2p activity corrections, or <code>null</code> if a matching p2p activity corrections could not be found
	*/
	public P2PActivityCorrections fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the p2p activity corrections where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the p2p activity corrections that was removed
	*/
	public P2PActivityCorrections removeByUUID_G(String uuid, long groupId)
		throws NoSuchP2PActivityCorrectionsException;

	/**
	* Returns the number of p2p activity correctionses where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching p2p activity correctionses
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the p2p activity correctionses where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching p2p activity correctionses
	*/
	public java.util.List<P2PActivityCorrections> findByUuid_C(String uuid,
		long companyId);

	/**
	* Returns a range of all the p2p activity correctionses where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityCorrectionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of p2p activity correctionses
	* @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	* @return the range of matching p2p activity correctionses
	*/
	public java.util.List<P2PActivityCorrections> findByUuid_C(String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the p2p activity correctionses where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityCorrectionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of p2p activity correctionses
	* @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching p2p activity correctionses
	*/
	public java.util.List<P2PActivityCorrections> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivityCorrections> orderByComparator);

	/**
	* Returns an ordered range of all the p2p activity correctionses where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityCorrectionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of p2p activity correctionses
	* @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching p2p activity correctionses
	*/
	public java.util.List<P2PActivityCorrections> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivityCorrections> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first p2p activity corrections in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching p2p activity corrections
	* @throws NoSuchP2PActivityCorrectionsException if a matching p2p activity corrections could not be found
	*/
	public P2PActivityCorrections findByUuid_C_First(String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws NoSuchP2PActivityCorrectionsException;

	/**
	* Returns the first p2p activity corrections in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching p2p activity corrections, or <code>null</code> if a matching p2p activity corrections could not be found
	*/
	public P2PActivityCorrections fetchByUuid_C_First(String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivityCorrections> orderByComparator);

	/**
	* Returns the last p2p activity corrections in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching p2p activity corrections
	* @throws NoSuchP2PActivityCorrectionsException if a matching p2p activity corrections could not be found
	*/
	public P2PActivityCorrections findByUuid_C_Last(String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws NoSuchP2PActivityCorrectionsException;

	/**
	* Returns the last p2p activity corrections in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching p2p activity corrections, or <code>null</code> if a matching p2p activity corrections could not be found
	*/
	public P2PActivityCorrections fetchByUuid_C_Last(String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivityCorrections> orderByComparator);

	/**
	* Returns the p2p activity correctionses before and after the current p2p activity corrections in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param p2pActivityCorrectionsId the primary key of the current p2p activity corrections
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next p2p activity corrections
	* @throws NoSuchP2PActivityCorrectionsException if a p2p activity corrections with the primary key could not be found
	*/
	public P2PActivityCorrections[] findByUuid_C_PrevAndNext(
		long p2pActivityCorrectionsId, String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws NoSuchP2PActivityCorrectionsException;

	/**
	* Removes all the p2p activity correctionses where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of p2p activity correctionses where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching p2p activity correctionses
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns all the p2p activity correctionses where p2pActivityId = &#63;.
	*
	* @param p2pActivityId the p2p activity ID
	* @return the matching p2p activity correctionses
	*/
	public java.util.List<P2PActivityCorrections> findByP2PActivityId(
		long p2pActivityId);

	/**
	* Returns a range of all the p2p activity correctionses where p2pActivityId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityCorrectionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param p2pActivityId the p2p activity ID
	* @param start the lower bound of the range of p2p activity correctionses
	* @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	* @return the range of matching p2p activity correctionses
	*/
	public java.util.List<P2PActivityCorrections> findByP2PActivityId(
		long p2pActivityId, int start, int end);

	/**
	* Returns an ordered range of all the p2p activity correctionses where p2pActivityId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityCorrectionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param p2pActivityId the p2p activity ID
	* @param start the lower bound of the range of p2p activity correctionses
	* @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching p2p activity correctionses
	*/
	public java.util.List<P2PActivityCorrections> findByP2PActivityId(
		long p2pActivityId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivityCorrections> orderByComparator);

	/**
	* Returns an ordered range of all the p2p activity correctionses where p2pActivityId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityCorrectionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param p2pActivityId the p2p activity ID
	* @param start the lower bound of the range of p2p activity correctionses
	* @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching p2p activity correctionses
	*/
	public java.util.List<P2PActivityCorrections> findByP2PActivityId(
		long p2pActivityId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivityCorrections> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first p2p activity corrections in the ordered set where p2pActivityId = &#63;.
	*
	* @param p2pActivityId the p2p activity ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching p2p activity corrections
	* @throws NoSuchP2PActivityCorrectionsException if a matching p2p activity corrections could not be found
	*/
	public P2PActivityCorrections findByP2PActivityId_First(
		long p2pActivityId,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws NoSuchP2PActivityCorrectionsException;

	/**
	* Returns the first p2p activity corrections in the ordered set where p2pActivityId = &#63;.
	*
	* @param p2pActivityId the p2p activity ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching p2p activity corrections, or <code>null</code> if a matching p2p activity corrections could not be found
	*/
	public P2PActivityCorrections fetchByP2PActivityId_First(
		long p2pActivityId,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivityCorrections> orderByComparator);

	/**
	* Returns the last p2p activity corrections in the ordered set where p2pActivityId = &#63;.
	*
	* @param p2pActivityId the p2p activity ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching p2p activity corrections
	* @throws NoSuchP2PActivityCorrectionsException if a matching p2p activity corrections could not be found
	*/
	public P2PActivityCorrections findByP2PActivityId_Last(long p2pActivityId,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws NoSuchP2PActivityCorrectionsException;

	/**
	* Returns the last p2p activity corrections in the ordered set where p2pActivityId = &#63;.
	*
	* @param p2pActivityId the p2p activity ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching p2p activity corrections, or <code>null</code> if a matching p2p activity corrections could not be found
	*/
	public P2PActivityCorrections fetchByP2PActivityId_Last(
		long p2pActivityId,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivityCorrections> orderByComparator);

	/**
	* Returns the p2p activity correctionses before and after the current p2p activity corrections in the ordered set where p2pActivityId = &#63;.
	*
	* @param p2pActivityCorrectionsId the primary key of the current p2p activity corrections
	* @param p2pActivityId the p2p activity ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next p2p activity corrections
	* @throws NoSuchP2PActivityCorrectionsException if a p2p activity corrections with the primary key could not be found
	*/
	public P2PActivityCorrections[] findByP2PActivityId_PrevAndNext(
		long p2pActivityCorrectionsId, long p2pActivityId,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws NoSuchP2PActivityCorrectionsException;

	/**
	* Removes all the p2p activity correctionses where p2pActivityId = &#63; from the database.
	*
	* @param p2pActivityId the p2p activity ID
	*/
	public void removeByP2PActivityId(long p2pActivityId);

	/**
	* Returns the number of p2p activity correctionses where p2pActivityId = &#63;.
	*
	* @param p2pActivityId the p2p activity ID
	* @return the number of matching p2p activity correctionses
	*/
	public int countByP2PActivityId(long p2pActivityId);

	/**
	* Returns all the p2p activity correctionses where actId = &#63; and userId = &#63;.
	*
	* @param actId the act ID
	* @param userId the user ID
	* @return the matching p2p activity correctionses
	*/
	public java.util.List<P2PActivityCorrections> findByActIdAndUserId(
		long actId, long userId);

	/**
	* Returns a range of all the p2p activity correctionses where actId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityCorrectionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param actId the act ID
	* @param userId the user ID
	* @param start the lower bound of the range of p2p activity correctionses
	* @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	* @return the range of matching p2p activity correctionses
	*/
	public java.util.List<P2PActivityCorrections> findByActIdAndUserId(
		long actId, long userId, int start, int end);

	/**
	* Returns an ordered range of all the p2p activity correctionses where actId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityCorrectionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param actId the act ID
	* @param userId the user ID
	* @param start the lower bound of the range of p2p activity correctionses
	* @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching p2p activity correctionses
	*/
	public java.util.List<P2PActivityCorrections> findByActIdAndUserId(
		long actId, long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivityCorrections> orderByComparator);

	/**
	* Returns an ordered range of all the p2p activity correctionses where actId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityCorrectionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param actId the act ID
	* @param userId the user ID
	* @param start the lower bound of the range of p2p activity correctionses
	* @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching p2p activity correctionses
	*/
	public java.util.List<P2PActivityCorrections> findByActIdAndUserId(
		long actId, long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivityCorrections> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first p2p activity corrections in the ordered set where actId = &#63; and userId = &#63;.
	*
	* @param actId the act ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching p2p activity corrections
	* @throws NoSuchP2PActivityCorrectionsException if a matching p2p activity corrections could not be found
	*/
	public P2PActivityCorrections findByActIdAndUserId_First(long actId,
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws NoSuchP2PActivityCorrectionsException;

	/**
	* Returns the first p2p activity corrections in the ordered set where actId = &#63; and userId = &#63;.
	*
	* @param actId the act ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching p2p activity corrections, or <code>null</code> if a matching p2p activity corrections could not be found
	*/
	public P2PActivityCorrections fetchByActIdAndUserId_First(long actId,
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivityCorrections> orderByComparator);

	/**
	* Returns the last p2p activity corrections in the ordered set where actId = &#63; and userId = &#63;.
	*
	* @param actId the act ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching p2p activity corrections
	* @throws NoSuchP2PActivityCorrectionsException if a matching p2p activity corrections could not be found
	*/
	public P2PActivityCorrections findByActIdAndUserId_Last(long actId,
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws NoSuchP2PActivityCorrectionsException;

	/**
	* Returns the last p2p activity corrections in the ordered set where actId = &#63; and userId = &#63;.
	*
	* @param actId the act ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching p2p activity corrections, or <code>null</code> if a matching p2p activity corrections could not be found
	*/
	public P2PActivityCorrections fetchByActIdAndUserId_Last(long actId,
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivityCorrections> orderByComparator);

	/**
	* Returns the p2p activity correctionses before and after the current p2p activity corrections in the ordered set where actId = &#63; and userId = &#63;.
	*
	* @param p2pActivityCorrectionsId the primary key of the current p2p activity corrections
	* @param actId the act ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next p2p activity corrections
	* @throws NoSuchP2PActivityCorrectionsException if a p2p activity corrections with the primary key could not be found
	*/
	public P2PActivityCorrections[] findByActIdAndUserId_PrevAndNext(
		long p2pActivityCorrectionsId, long actId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws NoSuchP2PActivityCorrectionsException;

	/**
	* Removes all the p2p activity correctionses where actId = &#63; and userId = &#63; from the database.
	*
	* @param actId the act ID
	* @param userId the user ID
	*/
	public void removeByActIdAndUserId(long actId, long userId);

	/**
	* Returns the number of p2p activity correctionses where actId = &#63; and userId = &#63;.
	*
	* @param actId the act ID
	* @param userId the user ID
	* @return the number of matching p2p activity correctionses
	*/
	public int countByActIdAndUserId(long actId, long userId);

	/**
	* Returns the p2p activity corrections where p2pActivityId = &#63; and userId = &#63; or throws a {@link NoSuchP2PActivityCorrectionsException} if it could not be found.
	*
	* @param p2pActivityId the p2p activity ID
	* @param userId the user ID
	* @return the matching p2p activity corrections
	* @throws NoSuchP2PActivityCorrectionsException if a matching p2p activity corrections could not be found
	*/
	public P2PActivityCorrections findByP2PActivityIdAndUserId(
		long p2pActivityId, long userId)
		throws NoSuchP2PActivityCorrectionsException;

	/**
	* Returns the p2p activity corrections where p2pActivityId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param p2pActivityId the p2p activity ID
	* @param userId the user ID
	* @return the matching p2p activity corrections, or <code>null</code> if a matching p2p activity corrections could not be found
	*/
	public P2PActivityCorrections fetchByP2PActivityIdAndUserId(
		long p2pActivityId, long userId);

	/**
	* Returns the p2p activity corrections where p2pActivityId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param p2pActivityId the p2p activity ID
	* @param userId the user ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching p2p activity corrections, or <code>null</code> if a matching p2p activity corrections could not be found
	*/
	public P2PActivityCorrections fetchByP2PActivityIdAndUserId(
		long p2pActivityId, long userId, boolean retrieveFromCache);

	/**
	* Removes the p2p activity corrections where p2pActivityId = &#63; and userId = &#63; from the database.
	*
	* @param p2pActivityId the p2p activity ID
	* @param userId the user ID
	* @return the p2p activity corrections that was removed
	*/
	public P2PActivityCorrections removeByP2PActivityIdAndUserId(
		long p2pActivityId, long userId)
		throws NoSuchP2PActivityCorrectionsException;

	/**
	* Returns the number of p2p activity correctionses where p2pActivityId = &#63; and userId = &#63;.
	*
	* @param p2pActivityId the p2p activity ID
	* @param userId the user ID
	* @return the number of matching p2p activity correctionses
	*/
	public int countByP2PActivityIdAndUserId(long p2pActivityId, long userId);

	/**
	* Returns all the p2p activity correctionses where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching p2p activity correctionses
	*/
	public java.util.List<P2PActivityCorrections> findByUserId(long userId);

	/**
	* Returns a range of all the p2p activity correctionses where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityCorrectionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of p2p activity correctionses
	* @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	* @return the range of matching p2p activity correctionses
	*/
	public java.util.List<P2PActivityCorrections> findByUserId(long userId,
		int start, int end);

	/**
	* Returns an ordered range of all the p2p activity correctionses where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityCorrectionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of p2p activity correctionses
	* @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching p2p activity correctionses
	*/
	public java.util.List<P2PActivityCorrections> findByUserId(long userId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivityCorrections> orderByComparator);

	/**
	* Returns an ordered range of all the p2p activity correctionses where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityCorrectionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of p2p activity correctionses
	* @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching p2p activity correctionses
	*/
	public java.util.List<P2PActivityCorrections> findByUserId(long userId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivityCorrections> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first p2p activity corrections in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching p2p activity corrections
	* @throws NoSuchP2PActivityCorrectionsException if a matching p2p activity corrections could not be found
	*/
	public P2PActivityCorrections findByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws NoSuchP2PActivityCorrectionsException;

	/**
	* Returns the first p2p activity corrections in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching p2p activity corrections, or <code>null</code> if a matching p2p activity corrections could not be found
	*/
	public P2PActivityCorrections fetchByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivityCorrections> orderByComparator);

	/**
	* Returns the last p2p activity corrections in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching p2p activity corrections
	* @throws NoSuchP2PActivityCorrectionsException if a matching p2p activity corrections could not be found
	*/
	public P2PActivityCorrections findByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws NoSuchP2PActivityCorrectionsException;

	/**
	* Returns the last p2p activity corrections in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching p2p activity corrections, or <code>null</code> if a matching p2p activity corrections could not be found
	*/
	public P2PActivityCorrections fetchByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivityCorrections> orderByComparator);

	/**
	* Returns the p2p activity correctionses before and after the current p2p activity corrections in the ordered set where userId = &#63;.
	*
	* @param p2pActivityCorrectionsId the primary key of the current p2p activity corrections
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next p2p activity corrections
	* @throws NoSuchP2PActivityCorrectionsException if a p2p activity corrections with the primary key could not be found
	*/
	public P2PActivityCorrections[] findByUserId_PrevAndNext(
		long p2pActivityCorrectionsId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws NoSuchP2PActivityCorrectionsException;

	/**
	* Removes all the p2p activity correctionses where userId = &#63; from the database.
	*
	* @param userId the user ID
	*/
	public void removeByUserId(long userId);

	/**
	* Returns the number of p2p activity correctionses where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching p2p activity correctionses
	*/
	public int countByUserId(long userId);

	/**
	* Returns all the p2p activity correctionses where p2pActivityId = &#63;.
	*
	* @param p2pActivityId the p2p activity ID
	* @return the matching p2p activity correctionses
	*/
	public java.util.List<P2PActivityCorrections> findByP2PActivityIdDateNotNull(
		long p2pActivityId);

	/**
	* Returns a range of all the p2p activity correctionses where p2pActivityId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityCorrectionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param p2pActivityId the p2p activity ID
	* @param start the lower bound of the range of p2p activity correctionses
	* @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	* @return the range of matching p2p activity correctionses
	*/
	public java.util.List<P2PActivityCorrections> findByP2PActivityIdDateNotNull(
		long p2pActivityId, int start, int end);

	/**
	* Returns an ordered range of all the p2p activity correctionses where p2pActivityId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityCorrectionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param p2pActivityId the p2p activity ID
	* @param start the lower bound of the range of p2p activity correctionses
	* @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching p2p activity correctionses
	*/
	public java.util.List<P2PActivityCorrections> findByP2PActivityIdDateNotNull(
		long p2pActivityId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivityCorrections> orderByComparator);

	/**
	* Returns an ordered range of all the p2p activity correctionses where p2pActivityId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityCorrectionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param p2pActivityId the p2p activity ID
	* @param start the lower bound of the range of p2p activity correctionses
	* @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching p2p activity correctionses
	*/
	public java.util.List<P2PActivityCorrections> findByP2PActivityIdDateNotNull(
		long p2pActivityId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivityCorrections> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first p2p activity corrections in the ordered set where p2pActivityId = &#63;.
	*
	* @param p2pActivityId the p2p activity ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching p2p activity corrections
	* @throws NoSuchP2PActivityCorrectionsException if a matching p2p activity corrections could not be found
	*/
	public P2PActivityCorrections findByP2PActivityIdDateNotNull_First(
		long p2pActivityId,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws NoSuchP2PActivityCorrectionsException;

	/**
	* Returns the first p2p activity corrections in the ordered set where p2pActivityId = &#63;.
	*
	* @param p2pActivityId the p2p activity ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching p2p activity corrections, or <code>null</code> if a matching p2p activity corrections could not be found
	*/
	public P2PActivityCorrections fetchByP2PActivityIdDateNotNull_First(
		long p2pActivityId,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivityCorrections> orderByComparator);

	/**
	* Returns the last p2p activity corrections in the ordered set where p2pActivityId = &#63;.
	*
	* @param p2pActivityId the p2p activity ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching p2p activity corrections
	* @throws NoSuchP2PActivityCorrectionsException if a matching p2p activity corrections could not be found
	*/
	public P2PActivityCorrections findByP2PActivityIdDateNotNull_Last(
		long p2pActivityId,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws NoSuchP2PActivityCorrectionsException;

	/**
	* Returns the last p2p activity corrections in the ordered set where p2pActivityId = &#63;.
	*
	* @param p2pActivityId the p2p activity ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching p2p activity corrections, or <code>null</code> if a matching p2p activity corrections could not be found
	*/
	public P2PActivityCorrections fetchByP2PActivityIdDateNotNull_Last(
		long p2pActivityId,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivityCorrections> orderByComparator);

	/**
	* Returns the p2p activity correctionses before and after the current p2p activity corrections in the ordered set where p2pActivityId = &#63;.
	*
	* @param p2pActivityCorrectionsId the primary key of the current p2p activity corrections
	* @param p2pActivityId the p2p activity ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next p2p activity corrections
	* @throws NoSuchP2PActivityCorrectionsException if a p2p activity corrections with the primary key could not be found
	*/
	public P2PActivityCorrections[] findByP2PActivityIdDateNotNull_PrevAndNext(
		long p2pActivityCorrectionsId, long p2pActivityId,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws NoSuchP2PActivityCorrectionsException;

	/**
	* Removes all the p2p activity correctionses where p2pActivityId = &#63; from the database.
	*
	* @param p2pActivityId the p2p activity ID
	*/
	public void removeByP2PActivityIdDateNotNull(long p2pActivityId);

	/**
	* Returns the number of p2p activity correctionses where p2pActivityId = &#63;.
	*
	* @param p2pActivityId the p2p activity ID
	* @return the number of matching p2p activity correctionses
	*/
	public int countByP2PActivityIdDateNotNull(long p2pActivityId);

	/**
	* Returns all the p2p activity correctionses where actId = &#63; and userId = &#63;.
	*
	* @param actId the act ID
	* @param userId the user ID
	* @return the matching p2p activity correctionses
	*/
	public java.util.List<P2PActivityCorrections> findByActIdUserIdDateNotNull(
		long actId, long userId);

	/**
	* Returns a range of all the p2p activity correctionses where actId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityCorrectionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param actId the act ID
	* @param userId the user ID
	* @param start the lower bound of the range of p2p activity correctionses
	* @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	* @return the range of matching p2p activity correctionses
	*/
	public java.util.List<P2PActivityCorrections> findByActIdUserIdDateNotNull(
		long actId, long userId, int start, int end);

	/**
	* Returns an ordered range of all the p2p activity correctionses where actId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityCorrectionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param actId the act ID
	* @param userId the user ID
	* @param start the lower bound of the range of p2p activity correctionses
	* @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching p2p activity correctionses
	*/
	public java.util.List<P2PActivityCorrections> findByActIdUserIdDateNotNull(
		long actId, long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivityCorrections> orderByComparator);

	/**
	* Returns an ordered range of all the p2p activity correctionses where actId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityCorrectionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param actId the act ID
	* @param userId the user ID
	* @param start the lower bound of the range of p2p activity correctionses
	* @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching p2p activity correctionses
	*/
	public java.util.List<P2PActivityCorrections> findByActIdUserIdDateNotNull(
		long actId, long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivityCorrections> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first p2p activity corrections in the ordered set where actId = &#63; and userId = &#63;.
	*
	* @param actId the act ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching p2p activity corrections
	* @throws NoSuchP2PActivityCorrectionsException if a matching p2p activity corrections could not be found
	*/
	public P2PActivityCorrections findByActIdUserIdDateNotNull_First(
		long actId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws NoSuchP2PActivityCorrectionsException;

	/**
	* Returns the first p2p activity corrections in the ordered set where actId = &#63; and userId = &#63;.
	*
	* @param actId the act ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching p2p activity corrections, or <code>null</code> if a matching p2p activity corrections could not be found
	*/
	public P2PActivityCorrections fetchByActIdUserIdDateNotNull_First(
		long actId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivityCorrections> orderByComparator);

	/**
	* Returns the last p2p activity corrections in the ordered set where actId = &#63; and userId = &#63;.
	*
	* @param actId the act ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching p2p activity corrections
	* @throws NoSuchP2PActivityCorrectionsException if a matching p2p activity corrections could not be found
	*/
	public P2PActivityCorrections findByActIdUserIdDateNotNull_Last(
		long actId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws NoSuchP2PActivityCorrectionsException;

	/**
	* Returns the last p2p activity corrections in the ordered set where actId = &#63; and userId = &#63;.
	*
	* @param actId the act ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching p2p activity corrections, or <code>null</code> if a matching p2p activity corrections could not be found
	*/
	public P2PActivityCorrections fetchByActIdUserIdDateNotNull_Last(
		long actId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivityCorrections> orderByComparator);

	/**
	* Returns the p2p activity correctionses before and after the current p2p activity corrections in the ordered set where actId = &#63; and userId = &#63;.
	*
	* @param p2pActivityCorrectionsId the primary key of the current p2p activity corrections
	* @param actId the act ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next p2p activity corrections
	* @throws NoSuchP2PActivityCorrectionsException if a p2p activity corrections with the primary key could not be found
	*/
	public P2PActivityCorrections[] findByActIdUserIdDateNotNull_PrevAndNext(
		long p2pActivityCorrectionsId, long actId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws NoSuchP2PActivityCorrectionsException;

	/**
	* Removes all the p2p activity correctionses where actId = &#63; and userId = &#63; from the database.
	*
	* @param actId the act ID
	* @param userId the user ID
	*/
	public void removeByActIdUserIdDateNotNull(long actId, long userId);

	/**
	* Returns the number of p2p activity correctionses where actId = &#63; and userId = &#63;.
	*
	* @param actId the act ID
	* @param userId the user ID
	* @return the number of matching p2p activity correctionses
	*/
	public int countByActIdUserIdDateNotNull(long actId, long userId);

	/**
	* Returns all the p2p activity correctionses where p2pActivityId = &#63; and actId = &#63;.
	*
	* @param p2pActivityId the p2p activity ID
	* @param actId the act ID
	* @return the matching p2p activity correctionses
	*/
	public java.util.List<P2PActivityCorrections> findByP2pActivityIdActIdDateNotNull(
		long p2pActivityId, long actId);

	/**
	* Returns a range of all the p2p activity correctionses where p2pActivityId = &#63; and actId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityCorrectionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param p2pActivityId the p2p activity ID
	* @param actId the act ID
	* @param start the lower bound of the range of p2p activity correctionses
	* @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	* @return the range of matching p2p activity correctionses
	*/
	public java.util.List<P2PActivityCorrections> findByP2pActivityIdActIdDateNotNull(
		long p2pActivityId, long actId, int start, int end);

	/**
	* Returns an ordered range of all the p2p activity correctionses where p2pActivityId = &#63; and actId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityCorrectionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param p2pActivityId the p2p activity ID
	* @param actId the act ID
	* @param start the lower bound of the range of p2p activity correctionses
	* @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching p2p activity correctionses
	*/
	public java.util.List<P2PActivityCorrections> findByP2pActivityIdActIdDateNotNull(
		long p2pActivityId, long actId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivityCorrections> orderByComparator);

	/**
	* Returns an ordered range of all the p2p activity correctionses where p2pActivityId = &#63; and actId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityCorrectionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param p2pActivityId the p2p activity ID
	* @param actId the act ID
	* @param start the lower bound of the range of p2p activity correctionses
	* @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching p2p activity correctionses
	*/
	public java.util.List<P2PActivityCorrections> findByP2pActivityIdActIdDateNotNull(
		long p2pActivityId, long actId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivityCorrections> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first p2p activity corrections in the ordered set where p2pActivityId = &#63; and actId = &#63;.
	*
	* @param p2pActivityId the p2p activity ID
	* @param actId the act ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching p2p activity corrections
	* @throws NoSuchP2PActivityCorrectionsException if a matching p2p activity corrections could not be found
	*/
	public P2PActivityCorrections findByP2pActivityIdActIdDateNotNull_First(
		long p2pActivityId, long actId,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws NoSuchP2PActivityCorrectionsException;

	/**
	* Returns the first p2p activity corrections in the ordered set where p2pActivityId = &#63; and actId = &#63;.
	*
	* @param p2pActivityId the p2p activity ID
	* @param actId the act ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching p2p activity corrections, or <code>null</code> if a matching p2p activity corrections could not be found
	*/
	public P2PActivityCorrections fetchByP2pActivityIdActIdDateNotNull_First(
		long p2pActivityId, long actId,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivityCorrections> orderByComparator);

	/**
	* Returns the last p2p activity corrections in the ordered set where p2pActivityId = &#63; and actId = &#63;.
	*
	* @param p2pActivityId the p2p activity ID
	* @param actId the act ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching p2p activity corrections
	* @throws NoSuchP2PActivityCorrectionsException if a matching p2p activity corrections could not be found
	*/
	public P2PActivityCorrections findByP2pActivityIdActIdDateNotNull_Last(
		long p2pActivityId, long actId,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws NoSuchP2PActivityCorrectionsException;

	/**
	* Returns the last p2p activity corrections in the ordered set where p2pActivityId = &#63; and actId = &#63;.
	*
	* @param p2pActivityId the p2p activity ID
	* @param actId the act ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching p2p activity corrections, or <code>null</code> if a matching p2p activity corrections could not be found
	*/
	public P2PActivityCorrections fetchByP2pActivityIdActIdDateNotNull_Last(
		long p2pActivityId, long actId,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivityCorrections> orderByComparator);

	/**
	* Returns the p2p activity correctionses before and after the current p2p activity corrections in the ordered set where p2pActivityId = &#63; and actId = &#63;.
	*
	* @param p2pActivityCorrectionsId the primary key of the current p2p activity corrections
	* @param p2pActivityId the p2p activity ID
	* @param actId the act ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next p2p activity corrections
	* @throws NoSuchP2PActivityCorrectionsException if a p2p activity corrections with the primary key could not be found
	*/
	public P2PActivityCorrections[] findByP2pActivityIdActIdDateNotNull_PrevAndNext(
		long p2pActivityCorrectionsId, long p2pActivityId, long actId,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws NoSuchP2PActivityCorrectionsException;

	/**
	* Removes all the p2p activity correctionses where p2pActivityId = &#63; and actId = &#63; from the database.
	*
	* @param p2pActivityId the p2p activity ID
	* @param actId the act ID
	*/
	public void removeByP2pActivityIdActIdDateNotNull(long p2pActivityId,
		long actId);

	/**
	* Returns the number of p2p activity correctionses where p2pActivityId = &#63; and actId = &#63;.
	*
	* @param p2pActivityId the p2p activity ID
	* @param actId the act ID
	* @return the number of matching p2p activity correctionses
	*/
	public int countByP2pActivityIdActIdDateNotNull(long p2pActivityId,
		long actId);

	/**
	* Caches the p2p activity corrections in the entity cache if it is enabled.
	*
	* @param p2pActivityCorrections the p2p activity corrections
	*/
	public void cacheResult(P2PActivityCorrections p2pActivityCorrections);

	/**
	* Caches the p2p activity correctionses in the entity cache if it is enabled.
	*
	* @param p2pActivityCorrectionses the p2p activity correctionses
	*/
	public void cacheResult(
		java.util.List<P2PActivityCorrections> p2pActivityCorrectionses);

	/**
	* Creates a new p2p activity corrections with the primary key. Does not add the p2p activity corrections to the database.
	*
	* @param p2pActivityCorrectionsId the primary key for the new p2p activity corrections
	* @return the new p2p activity corrections
	*/
	public P2PActivityCorrections create(long p2pActivityCorrectionsId);

	/**
	* Removes the p2p activity corrections with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param p2pActivityCorrectionsId the primary key of the p2p activity corrections
	* @return the p2p activity corrections that was removed
	* @throws NoSuchP2PActivityCorrectionsException if a p2p activity corrections with the primary key could not be found
	*/
	public P2PActivityCorrections remove(long p2pActivityCorrectionsId)
		throws NoSuchP2PActivityCorrectionsException;

	public P2PActivityCorrections updateImpl(
		P2PActivityCorrections p2pActivityCorrections);

	/**
	* Returns the p2p activity corrections with the primary key or throws a {@link NoSuchP2PActivityCorrectionsException} if it could not be found.
	*
	* @param p2pActivityCorrectionsId the primary key of the p2p activity corrections
	* @return the p2p activity corrections
	* @throws NoSuchP2PActivityCorrectionsException if a p2p activity corrections with the primary key could not be found
	*/
	public P2PActivityCorrections findByPrimaryKey(
		long p2pActivityCorrectionsId)
		throws NoSuchP2PActivityCorrectionsException;

	/**
	* Returns the p2p activity corrections with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param p2pActivityCorrectionsId the primary key of the p2p activity corrections
	* @return the p2p activity corrections, or <code>null</code> if a p2p activity corrections with the primary key could not be found
	*/
	public P2PActivityCorrections fetchByPrimaryKey(
		long p2pActivityCorrectionsId);

	@Override
	public java.util.Map<java.io.Serializable, P2PActivityCorrections> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the p2p activity correctionses.
	*
	* @return the p2p activity correctionses
	*/
	public java.util.List<P2PActivityCorrections> findAll();

	/**
	* Returns a range of all the p2p activity correctionses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityCorrectionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of p2p activity correctionses
	* @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	* @return the range of p2p activity correctionses
	*/
	public java.util.List<P2PActivityCorrections> findAll(int start, int end);

	/**
	* Returns an ordered range of all the p2p activity correctionses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityCorrectionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of p2p activity correctionses
	* @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of p2p activity correctionses
	*/
	public java.util.List<P2PActivityCorrections> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivityCorrections> orderByComparator);

	/**
	* Returns an ordered range of all the p2p activity correctionses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityCorrectionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of p2p activity correctionses
	* @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of p2p activity correctionses
	*/
	public java.util.List<P2PActivityCorrections> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<P2PActivityCorrections> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the p2p activity correctionses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of p2p activity correctionses.
	*
	* @return the number of p2p activity correctionses
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}