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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.annotation.versioning.ProviderType;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the p2p activity corrections service. This utility wraps <code>com.ted.lms.learning.activity.p2p.service.persistence.impl.P2PActivityCorrectionsPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see P2PActivityCorrectionsPersistence
 * @generated
 */
@ProviderType
public class P2PActivityCorrectionsUtil {

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
	public static void clearCache(
		P2PActivityCorrections p2pActivityCorrections) {

		getPersistence().clearCache(p2pActivityCorrections);
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
	public static Map<Serializable, P2PActivityCorrections> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<P2PActivityCorrections> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<P2PActivityCorrections> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<P2PActivityCorrections> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<P2PActivityCorrections> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static P2PActivityCorrections update(
		P2PActivityCorrections p2pActivityCorrections) {

		return getPersistence().update(p2pActivityCorrections);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static P2PActivityCorrections update(
		P2PActivityCorrections p2pActivityCorrections,
		ServiceContext serviceContext) {

		return getPersistence().update(p2pActivityCorrections, serviceContext);
	}

	/**
	 * Returns all the p2p activity correctionses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching p2p activity correctionses
	 */
	public static List<P2PActivityCorrections> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the p2p activity correctionses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityCorrectionsModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of p2p activity correctionses
	 * @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	 * @return the range of matching p2p activity correctionses
	 */
	public static List<P2PActivityCorrections> findByUuid(
		String uuid, int start, int end) {

		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the p2p activity correctionses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityCorrectionsModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of p2p activity correctionses
	 * @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching p2p activity correctionses
	 */
	public static List<P2PActivityCorrections> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<P2PActivityCorrections> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the p2p activity correctionses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityCorrectionsModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of p2p activity correctionses
	 * @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching p2p activity correctionses
	 */
	public static List<P2PActivityCorrections> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<P2PActivityCorrections> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first p2p activity corrections in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching p2p activity corrections
	 * @throws NoSuchP2PActivityCorrectionsException if a matching p2p activity corrections could not be found
	 */
	public static P2PActivityCorrections findByUuid_First(
			String uuid,
			OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws com.ted.lms.learning.activity.p2p.exception.
			NoSuchP2PActivityCorrectionsException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first p2p activity corrections in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching p2p activity corrections, or <code>null</code> if a matching p2p activity corrections could not be found
	 */
	public static P2PActivityCorrections fetchByUuid_First(
		String uuid,
		OrderByComparator<P2PActivityCorrections> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last p2p activity corrections in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching p2p activity corrections
	 * @throws NoSuchP2PActivityCorrectionsException if a matching p2p activity corrections could not be found
	 */
	public static P2PActivityCorrections findByUuid_Last(
			String uuid,
			OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws com.ted.lms.learning.activity.p2p.exception.
			NoSuchP2PActivityCorrectionsException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last p2p activity corrections in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching p2p activity corrections, or <code>null</code> if a matching p2p activity corrections could not be found
	 */
	public static P2PActivityCorrections fetchByUuid_Last(
		String uuid,
		OrderByComparator<P2PActivityCorrections> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the p2p activity correctionses before and after the current p2p activity corrections in the ordered set where uuid = &#63;.
	 *
	 * @param p2pActivityCorrectionsId the primary key of the current p2p activity corrections
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next p2p activity corrections
	 * @throws NoSuchP2PActivityCorrectionsException if a p2p activity corrections with the primary key could not be found
	 */
	public static P2PActivityCorrections[] findByUuid_PrevAndNext(
			long p2pActivityCorrectionsId, String uuid,
			OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws com.ted.lms.learning.activity.p2p.exception.
			NoSuchP2PActivityCorrectionsException {

		return getPersistence().findByUuid_PrevAndNext(
			p2pActivityCorrectionsId, uuid, orderByComparator);
	}

	/**
	 * Removes all the p2p activity correctionses where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of p2p activity correctionses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching p2p activity correctionses
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the p2p activity corrections where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchP2PActivityCorrectionsException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching p2p activity corrections
	 * @throws NoSuchP2PActivityCorrectionsException if a matching p2p activity corrections could not be found
	 */
	public static P2PActivityCorrections findByUUID_G(String uuid, long groupId)
		throws com.ted.lms.learning.activity.p2p.exception.
			NoSuchP2PActivityCorrectionsException {

		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the p2p activity corrections where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching p2p activity corrections, or <code>null</code> if a matching p2p activity corrections could not be found
	 */
	public static P2PActivityCorrections fetchByUUID_G(
		String uuid, long groupId) {

		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the p2p activity corrections where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching p2p activity corrections, or <code>null</code> if a matching p2p activity corrections could not be found
	 */
	public static P2PActivityCorrections fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		return getPersistence().fetchByUUID_G(uuid, groupId, useFinderCache);
	}

	/**
	 * Removes the p2p activity corrections where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the p2p activity corrections that was removed
	 */
	public static P2PActivityCorrections removeByUUID_G(
			String uuid, long groupId)
		throws com.ted.lms.learning.activity.p2p.exception.
			NoSuchP2PActivityCorrectionsException {

		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the number of p2p activity correctionses where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching p2p activity correctionses
	 */
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	 * Returns all the p2p activity correctionses where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching p2p activity correctionses
	 */
	public static List<P2PActivityCorrections> findByUuid_C(
		String uuid, long companyId) {

		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the p2p activity correctionses where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityCorrectionsModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of p2p activity correctionses
	 * @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	 * @return the range of matching p2p activity correctionses
	 */
	public static List<P2PActivityCorrections> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the p2p activity correctionses where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityCorrectionsModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of p2p activity correctionses
	 * @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching p2p activity correctionses
	 */
	public static List<P2PActivityCorrections> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<P2PActivityCorrections> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the p2p activity correctionses where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityCorrectionsModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of p2p activity correctionses
	 * @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching p2p activity correctionses
	 */
	public static List<P2PActivityCorrections> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<P2PActivityCorrections> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first p2p activity corrections in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching p2p activity corrections
	 * @throws NoSuchP2PActivityCorrectionsException if a matching p2p activity corrections could not be found
	 */
	public static P2PActivityCorrections findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws com.ted.lms.learning.activity.p2p.exception.
			NoSuchP2PActivityCorrectionsException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first p2p activity corrections in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching p2p activity corrections, or <code>null</code> if a matching p2p activity corrections could not be found
	 */
	public static P2PActivityCorrections fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<P2PActivityCorrections> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last p2p activity corrections in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching p2p activity corrections
	 * @throws NoSuchP2PActivityCorrectionsException if a matching p2p activity corrections could not be found
	 */
	public static P2PActivityCorrections findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws com.ted.lms.learning.activity.p2p.exception.
			NoSuchP2PActivityCorrectionsException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last p2p activity corrections in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching p2p activity corrections, or <code>null</code> if a matching p2p activity corrections could not be found
	 */
	public static P2PActivityCorrections fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<P2PActivityCorrections> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

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
	public static P2PActivityCorrections[] findByUuid_C_PrevAndNext(
			long p2pActivityCorrectionsId, String uuid, long companyId,
			OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws com.ted.lms.learning.activity.p2p.exception.
			NoSuchP2PActivityCorrectionsException {

		return getPersistence().findByUuid_C_PrevAndNext(
			p2pActivityCorrectionsId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the p2p activity correctionses where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of p2p activity correctionses where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching p2p activity correctionses
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns all the p2p activity correctionses where p2pActivityId = &#63;.
	 *
	 * @param p2pActivityId the p2p activity ID
	 * @return the matching p2p activity correctionses
	 */
	public static List<P2PActivityCorrections> findByP2PActivityId(
		long p2pActivityId) {

		return getPersistence().findByP2PActivityId(p2pActivityId);
	}

	/**
	 * Returns a range of all the p2p activity correctionses where p2pActivityId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityCorrectionsModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param p2pActivityId the p2p activity ID
	 * @param start the lower bound of the range of p2p activity correctionses
	 * @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	 * @return the range of matching p2p activity correctionses
	 */
	public static List<P2PActivityCorrections> findByP2PActivityId(
		long p2pActivityId, int start, int end) {

		return getPersistence().findByP2PActivityId(p2pActivityId, start, end);
	}

	/**
	 * Returns an ordered range of all the p2p activity correctionses where p2pActivityId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityCorrectionsModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param p2pActivityId the p2p activity ID
	 * @param start the lower bound of the range of p2p activity correctionses
	 * @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching p2p activity correctionses
	 */
	public static List<P2PActivityCorrections> findByP2PActivityId(
		long p2pActivityId, int start, int end,
		OrderByComparator<P2PActivityCorrections> orderByComparator) {

		return getPersistence().findByP2PActivityId(
			p2pActivityId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the p2p activity correctionses where p2pActivityId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityCorrectionsModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param p2pActivityId the p2p activity ID
	 * @param start the lower bound of the range of p2p activity correctionses
	 * @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching p2p activity correctionses
	 */
	public static List<P2PActivityCorrections> findByP2PActivityId(
		long p2pActivityId, int start, int end,
		OrderByComparator<P2PActivityCorrections> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByP2PActivityId(
			p2pActivityId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first p2p activity corrections in the ordered set where p2pActivityId = &#63;.
	 *
	 * @param p2pActivityId the p2p activity ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching p2p activity corrections
	 * @throws NoSuchP2PActivityCorrectionsException if a matching p2p activity corrections could not be found
	 */
	public static P2PActivityCorrections findByP2PActivityId_First(
			long p2pActivityId,
			OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws com.ted.lms.learning.activity.p2p.exception.
			NoSuchP2PActivityCorrectionsException {

		return getPersistence().findByP2PActivityId_First(
			p2pActivityId, orderByComparator);
	}

	/**
	 * Returns the first p2p activity corrections in the ordered set where p2pActivityId = &#63;.
	 *
	 * @param p2pActivityId the p2p activity ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching p2p activity corrections, or <code>null</code> if a matching p2p activity corrections could not be found
	 */
	public static P2PActivityCorrections fetchByP2PActivityId_First(
		long p2pActivityId,
		OrderByComparator<P2PActivityCorrections> orderByComparator) {

		return getPersistence().fetchByP2PActivityId_First(
			p2pActivityId, orderByComparator);
	}

	/**
	 * Returns the last p2p activity corrections in the ordered set where p2pActivityId = &#63;.
	 *
	 * @param p2pActivityId the p2p activity ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching p2p activity corrections
	 * @throws NoSuchP2PActivityCorrectionsException if a matching p2p activity corrections could not be found
	 */
	public static P2PActivityCorrections findByP2PActivityId_Last(
			long p2pActivityId,
			OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws com.ted.lms.learning.activity.p2p.exception.
			NoSuchP2PActivityCorrectionsException {

		return getPersistence().findByP2PActivityId_Last(
			p2pActivityId, orderByComparator);
	}

	/**
	 * Returns the last p2p activity corrections in the ordered set where p2pActivityId = &#63;.
	 *
	 * @param p2pActivityId the p2p activity ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching p2p activity corrections, or <code>null</code> if a matching p2p activity corrections could not be found
	 */
	public static P2PActivityCorrections fetchByP2PActivityId_Last(
		long p2pActivityId,
		OrderByComparator<P2PActivityCorrections> orderByComparator) {

		return getPersistence().fetchByP2PActivityId_Last(
			p2pActivityId, orderByComparator);
	}

	/**
	 * Returns the p2p activity correctionses before and after the current p2p activity corrections in the ordered set where p2pActivityId = &#63;.
	 *
	 * @param p2pActivityCorrectionsId the primary key of the current p2p activity corrections
	 * @param p2pActivityId the p2p activity ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next p2p activity corrections
	 * @throws NoSuchP2PActivityCorrectionsException if a p2p activity corrections with the primary key could not be found
	 */
	public static P2PActivityCorrections[] findByP2PActivityId_PrevAndNext(
			long p2pActivityCorrectionsId, long p2pActivityId,
			OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws com.ted.lms.learning.activity.p2p.exception.
			NoSuchP2PActivityCorrectionsException {

		return getPersistence().findByP2PActivityId_PrevAndNext(
			p2pActivityCorrectionsId, p2pActivityId, orderByComparator);
	}

	/**
	 * Removes all the p2p activity correctionses where p2pActivityId = &#63; from the database.
	 *
	 * @param p2pActivityId the p2p activity ID
	 */
	public static void removeByP2PActivityId(long p2pActivityId) {
		getPersistence().removeByP2PActivityId(p2pActivityId);
	}

	/**
	 * Returns the number of p2p activity correctionses where p2pActivityId = &#63;.
	 *
	 * @param p2pActivityId the p2p activity ID
	 * @return the number of matching p2p activity correctionses
	 */
	public static int countByP2PActivityId(long p2pActivityId) {
		return getPersistence().countByP2PActivityId(p2pActivityId);
	}

	/**
	 * Returns all the p2p activity correctionses where actId = &#63; and userId = &#63;.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @return the matching p2p activity correctionses
	 */
	public static List<P2PActivityCorrections> findByActIdAndUserId(
		long actId, long userId) {

		return getPersistence().findByActIdAndUserId(actId, userId);
	}

	/**
	 * Returns a range of all the p2p activity correctionses where actId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityCorrectionsModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of p2p activity correctionses
	 * @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	 * @return the range of matching p2p activity correctionses
	 */
	public static List<P2PActivityCorrections> findByActIdAndUserId(
		long actId, long userId, int start, int end) {

		return getPersistence().findByActIdAndUserId(actId, userId, start, end);
	}

	/**
	 * Returns an ordered range of all the p2p activity correctionses where actId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityCorrectionsModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of p2p activity correctionses
	 * @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching p2p activity correctionses
	 */
	public static List<P2PActivityCorrections> findByActIdAndUserId(
		long actId, long userId, int start, int end,
		OrderByComparator<P2PActivityCorrections> orderByComparator) {

		return getPersistence().findByActIdAndUserId(
			actId, userId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the p2p activity correctionses where actId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityCorrectionsModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of p2p activity correctionses
	 * @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching p2p activity correctionses
	 */
	public static List<P2PActivityCorrections> findByActIdAndUserId(
		long actId, long userId, int start, int end,
		OrderByComparator<P2PActivityCorrections> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByActIdAndUserId(
			actId, userId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first p2p activity corrections in the ordered set where actId = &#63; and userId = &#63;.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching p2p activity corrections
	 * @throws NoSuchP2PActivityCorrectionsException if a matching p2p activity corrections could not be found
	 */
	public static P2PActivityCorrections findByActIdAndUserId_First(
			long actId, long userId,
			OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws com.ted.lms.learning.activity.p2p.exception.
			NoSuchP2PActivityCorrectionsException {

		return getPersistence().findByActIdAndUserId_First(
			actId, userId, orderByComparator);
	}

	/**
	 * Returns the first p2p activity corrections in the ordered set where actId = &#63; and userId = &#63;.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching p2p activity corrections, or <code>null</code> if a matching p2p activity corrections could not be found
	 */
	public static P2PActivityCorrections fetchByActIdAndUserId_First(
		long actId, long userId,
		OrderByComparator<P2PActivityCorrections> orderByComparator) {

		return getPersistence().fetchByActIdAndUserId_First(
			actId, userId, orderByComparator);
	}

	/**
	 * Returns the last p2p activity corrections in the ordered set where actId = &#63; and userId = &#63;.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching p2p activity corrections
	 * @throws NoSuchP2PActivityCorrectionsException if a matching p2p activity corrections could not be found
	 */
	public static P2PActivityCorrections findByActIdAndUserId_Last(
			long actId, long userId,
			OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws com.ted.lms.learning.activity.p2p.exception.
			NoSuchP2PActivityCorrectionsException {

		return getPersistence().findByActIdAndUserId_Last(
			actId, userId, orderByComparator);
	}

	/**
	 * Returns the last p2p activity corrections in the ordered set where actId = &#63; and userId = &#63;.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching p2p activity corrections, or <code>null</code> if a matching p2p activity corrections could not be found
	 */
	public static P2PActivityCorrections fetchByActIdAndUserId_Last(
		long actId, long userId,
		OrderByComparator<P2PActivityCorrections> orderByComparator) {

		return getPersistence().fetchByActIdAndUserId_Last(
			actId, userId, orderByComparator);
	}

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
	public static P2PActivityCorrections[] findByActIdAndUserId_PrevAndNext(
			long p2pActivityCorrectionsId, long actId, long userId,
			OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws com.ted.lms.learning.activity.p2p.exception.
			NoSuchP2PActivityCorrectionsException {

		return getPersistence().findByActIdAndUserId_PrevAndNext(
			p2pActivityCorrectionsId, actId, userId, orderByComparator);
	}

	/**
	 * Removes all the p2p activity correctionses where actId = &#63; and userId = &#63; from the database.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 */
	public static void removeByActIdAndUserId(long actId, long userId) {
		getPersistence().removeByActIdAndUserId(actId, userId);
	}

	/**
	 * Returns the number of p2p activity correctionses where actId = &#63; and userId = &#63;.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @return the number of matching p2p activity correctionses
	 */
	public static int countByActIdAndUserId(long actId, long userId) {
		return getPersistence().countByActIdAndUserId(actId, userId);
	}

	/**
	 * Returns the p2p activity corrections where p2pActivityId = &#63; and userId = &#63; or throws a <code>NoSuchP2PActivityCorrectionsException</code> if it could not be found.
	 *
	 * @param p2pActivityId the p2p activity ID
	 * @param userId the user ID
	 * @return the matching p2p activity corrections
	 * @throws NoSuchP2PActivityCorrectionsException if a matching p2p activity corrections could not be found
	 */
	public static P2PActivityCorrections findByP2PActivityIdAndUserId(
			long p2pActivityId, long userId)
		throws com.ted.lms.learning.activity.p2p.exception.
			NoSuchP2PActivityCorrectionsException {

		return getPersistence().findByP2PActivityIdAndUserId(
			p2pActivityId, userId);
	}

	/**
	 * Returns the p2p activity corrections where p2pActivityId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param p2pActivityId the p2p activity ID
	 * @param userId the user ID
	 * @return the matching p2p activity corrections, or <code>null</code> if a matching p2p activity corrections could not be found
	 */
	public static P2PActivityCorrections fetchByP2PActivityIdAndUserId(
		long p2pActivityId, long userId) {

		return getPersistence().fetchByP2PActivityIdAndUserId(
			p2pActivityId, userId);
	}

	/**
	 * Returns the p2p activity corrections where p2pActivityId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param p2pActivityId the p2p activity ID
	 * @param userId the user ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching p2p activity corrections, or <code>null</code> if a matching p2p activity corrections could not be found
	 */
	public static P2PActivityCorrections fetchByP2PActivityIdAndUserId(
		long p2pActivityId, long userId, boolean useFinderCache) {

		return getPersistence().fetchByP2PActivityIdAndUserId(
			p2pActivityId, userId, useFinderCache);
	}

	/**
	 * Removes the p2p activity corrections where p2pActivityId = &#63; and userId = &#63; from the database.
	 *
	 * @param p2pActivityId the p2p activity ID
	 * @param userId the user ID
	 * @return the p2p activity corrections that was removed
	 */
	public static P2PActivityCorrections removeByP2PActivityIdAndUserId(
			long p2pActivityId, long userId)
		throws com.ted.lms.learning.activity.p2p.exception.
			NoSuchP2PActivityCorrectionsException {

		return getPersistence().removeByP2PActivityIdAndUserId(
			p2pActivityId, userId);
	}

	/**
	 * Returns the number of p2p activity correctionses where p2pActivityId = &#63; and userId = &#63;.
	 *
	 * @param p2pActivityId the p2p activity ID
	 * @param userId the user ID
	 * @return the number of matching p2p activity correctionses
	 */
	public static int countByP2PActivityIdAndUserId(
		long p2pActivityId, long userId) {

		return getPersistence().countByP2PActivityIdAndUserId(
			p2pActivityId, userId);
	}

	/**
	 * Returns all the p2p activity correctionses where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching p2p activity correctionses
	 */
	public static List<P2PActivityCorrections> findByUserId(long userId) {
		return getPersistence().findByUserId(userId);
	}

	/**
	 * Returns a range of all the p2p activity correctionses where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityCorrectionsModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of p2p activity correctionses
	 * @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	 * @return the range of matching p2p activity correctionses
	 */
	public static List<P2PActivityCorrections> findByUserId(
		long userId, int start, int end) {

		return getPersistence().findByUserId(userId, start, end);
	}

	/**
	 * Returns an ordered range of all the p2p activity correctionses where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityCorrectionsModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of p2p activity correctionses
	 * @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching p2p activity correctionses
	 */
	public static List<P2PActivityCorrections> findByUserId(
		long userId, int start, int end,
		OrderByComparator<P2PActivityCorrections> orderByComparator) {

		return getPersistence().findByUserId(
			userId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the p2p activity correctionses where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityCorrectionsModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of p2p activity correctionses
	 * @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching p2p activity correctionses
	 */
	public static List<P2PActivityCorrections> findByUserId(
		long userId, int start, int end,
		OrderByComparator<P2PActivityCorrections> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUserId(
			userId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first p2p activity corrections in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching p2p activity corrections
	 * @throws NoSuchP2PActivityCorrectionsException if a matching p2p activity corrections could not be found
	 */
	public static P2PActivityCorrections findByUserId_First(
			long userId,
			OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws com.ted.lms.learning.activity.p2p.exception.
			NoSuchP2PActivityCorrectionsException {

		return getPersistence().findByUserId_First(userId, orderByComparator);
	}

	/**
	 * Returns the first p2p activity corrections in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching p2p activity corrections, or <code>null</code> if a matching p2p activity corrections could not be found
	 */
	public static P2PActivityCorrections fetchByUserId_First(
		long userId,
		OrderByComparator<P2PActivityCorrections> orderByComparator) {

		return getPersistence().fetchByUserId_First(userId, orderByComparator);
	}

	/**
	 * Returns the last p2p activity corrections in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching p2p activity corrections
	 * @throws NoSuchP2PActivityCorrectionsException if a matching p2p activity corrections could not be found
	 */
	public static P2PActivityCorrections findByUserId_Last(
			long userId,
			OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws com.ted.lms.learning.activity.p2p.exception.
			NoSuchP2PActivityCorrectionsException {

		return getPersistence().findByUserId_Last(userId, orderByComparator);
	}

	/**
	 * Returns the last p2p activity corrections in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching p2p activity corrections, or <code>null</code> if a matching p2p activity corrections could not be found
	 */
	public static P2PActivityCorrections fetchByUserId_Last(
		long userId,
		OrderByComparator<P2PActivityCorrections> orderByComparator) {

		return getPersistence().fetchByUserId_Last(userId, orderByComparator);
	}

	/**
	 * Returns the p2p activity correctionses before and after the current p2p activity corrections in the ordered set where userId = &#63;.
	 *
	 * @param p2pActivityCorrectionsId the primary key of the current p2p activity corrections
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next p2p activity corrections
	 * @throws NoSuchP2PActivityCorrectionsException if a p2p activity corrections with the primary key could not be found
	 */
	public static P2PActivityCorrections[] findByUserId_PrevAndNext(
			long p2pActivityCorrectionsId, long userId,
			OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws com.ted.lms.learning.activity.p2p.exception.
			NoSuchP2PActivityCorrectionsException {

		return getPersistence().findByUserId_PrevAndNext(
			p2pActivityCorrectionsId, userId, orderByComparator);
	}

	/**
	 * Removes all the p2p activity correctionses where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	public static void removeByUserId(long userId) {
		getPersistence().removeByUserId(userId);
	}

	/**
	 * Returns the number of p2p activity correctionses where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching p2p activity correctionses
	 */
	public static int countByUserId(long userId) {
		return getPersistence().countByUserId(userId);
	}

	/**
	 * Caches the p2p activity corrections in the entity cache if it is enabled.
	 *
	 * @param p2pActivityCorrections the p2p activity corrections
	 */
	public static void cacheResult(
		P2PActivityCorrections p2pActivityCorrections) {

		getPersistence().cacheResult(p2pActivityCorrections);
	}

	/**
	 * Caches the p2p activity correctionses in the entity cache if it is enabled.
	 *
	 * @param p2pActivityCorrectionses the p2p activity correctionses
	 */
	public static void cacheResult(
		List<P2PActivityCorrections> p2pActivityCorrectionses) {

		getPersistence().cacheResult(p2pActivityCorrectionses);
	}

	/**
	 * Creates a new p2p activity corrections with the primary key. Does not add the p2p activity corrections to the database.
	 *
	 * @param p2pActivityCorrectionsId the primary key for the new p2p activity corrections
	 * @return the new p2p activity corrections
	 */
	public static P2PActivityCorrections create(long p2pActivityCorrectionsId) {
		return getPersistence().create(p2pActivityCorrectionsId);
	}

	/**
	 * Removes the p2p activity corrections with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param p2pActivityCorrectionsId the primary key of the p2p activity corrections
	 * @return the p2p activity corrections that was removed
	 * @throws NoSuchP2PActivityCorrectionsException if a p2p activity corrections with the primary key could not be found
	 */
	public static P2PActivityCorrections remove(long p2pActivityCorrectionsId)
		throws com.ted.lms.learning.activity.p2p.exception.
			NoSuchP2PActivityCorrectionsException {

		return getPersistence().remove(p2pActivityCorrectionsId);
	}

	public static P2PActivityCorrections updateImpl(
		P2PActivityCorrections p2pActivityCorrections) {

		return getPersistence().updateImpl(p2pActivityCorrections);
	}

	/**
	 * Returns the p2p activity corrections with the primary key or throws a <code>NoSuchP2PActivityCorrectionsException</code> if it could not be found.
	 *
	 * @param p2pActivityCorrectionsId the primary key of the p2p activity corrections
	 * @return the p2p activity corrections
	 * @throws NoSuchP2PActivityCorrectionsException if a p2p activity corrections with the primary key could not be found
	 */
	public static P2PActivityCorrections findByPrimaryKey(
			long p2pActivityCorrectionsId)
		throws com.ted.lms.learning.activity.p2p.exception.
			NoSuchP2PActivityCorrectionsException {

		return getPersistence().findByPrimaryKey(p2pActivityCorrectionsId);
	}

	/**
	 * Returns the p2p activity corrections with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param p2pActivityCorrectionsId the primary key of the p2p activity corrections
	 * @return the p2p activity corrections, or <code>null</code> if a p2p activity corrections with the primary key could not be found
	 */
	public static P2PActivityCorrections fetchByPrimaryKey(
		long p2pActivityCorrectionsId) {

		return getPersistence().fetchByPrimaryKey(p2pActivityCorrectionsId);
	}

	/**
	 * Returns all the p2p activity correctionses.
	 *
	 * @return the p2p activity correctionses
	 */
	public static List<P2PActivityCorrections> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the p2p activity correctionses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityCorrectionsModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of p2p activity correctionses
	 * @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	 * @return the range of p2p activity correctionses
	 */
	public static List<P2PActivityCorrections> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the p2p activity correctionses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityCorrectionsModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of p2p activity correctionses
	 * @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of p2p activity correctionses
	 */
	public static List<P2PActivityCorrections> findAll(
		int start, int end,
		OrderByComparator<P2PActivityCorrections> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the p2p activity correctionses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityCorrectionsModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of p2p activity correctionses
	 * @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of p2p activity correctionses
	 */
	public static List<P2PActivityCorrections> findAll(
		int start, int end,
		OrderByComparator<P2PActivityCorrections> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the p2p activity correctionses from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of p2p activity correctionses.
	 *
	 * @return the number of p2p activity correctionses
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static P2PActivityCorrectionsPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<P2PActivityCorrectionsPersistence, P2PActivityCorrectionsPersistence>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			P2PActivityCorrectionsPersistence.class);

		ServiceTracker
			<P2PActivityCorrectionsPersistence,
			 P2PActivityCorrectionsPersistence> serviceTracker =
				new ServiceTracker
					<P2PActivityCorrectionsPersistence,
					 P2PActivityCorrectionsPersistence>(
						 bundle.getBundleContext(),
						 P2PActivityCorrectionsPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}