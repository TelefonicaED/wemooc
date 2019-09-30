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

import com.ted.lms.learning.activity.p2p.model.P2PActivity;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.annotation.versioning.ProviderType;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the p2p activity service. This utility wraps <code>com.ted.lms.learning.activity.p2p.service.persistence.impl.P2PActivityPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see P2PActivityPersistence
 * @generated
 */
@ProviderType
public class P2PActivityUtil {

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
	public static void clearCache(P2PActivity p2pActivity) {
		getPersistence().clearCache(p2pActivity);
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
	public static Map<Serializable, P2PActivity> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<P2PActivity> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<P2PActivity> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<P2PActivity> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<P2PActivity> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static P2PActivity update(P2PActivity p2pActivity) {
		return getPersistence().update(p2pActivity);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static P2PActivity update(
		P2PActivity p2pActivity, ServiceContext serviceContext) {

		return getPersistence().update(p2pActivity, serviceContext);
	}

	/**
	 * Returns all the p2p activities where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching p2p activities
	 */
	public static List<P2PActivity> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the p2p activities where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of p2p activities
	 * @param end the upper bound of the range of p2p activities (not inclusive)
	 * @return the range of matching p2p activities
	 */
	public static List<P2PActivity> findByUuid(
		String uuid, int start, int end) {

		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the p2p activities where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of p2p activities
	 * @param end the upper bound of the range of p2p activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching p2p activities
	 */
	public static List<P2PActivity> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<P2PActivity> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the p2p activities where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of p2p activities
	 * @param end the upper bound of the range of p2p activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching p2p activities
	 */
	public static List<P2PActivity> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<P2PActivity> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first p2p activity in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching p2p activity
	 * @throws NoSuchP2PActivityException if a matching p2p activity could not be found
	 */
	public static P2PActivity findByUuid_First(
			String uuid, OrderByComparator<P2PActivity> orderByComparator)
		throws com.ted.lms.learning.activity.p2p.exception.
			NoSuchP2PActivityException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first p2p activity in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching p2p activity, or <code>null</code> if a matching p2p activity could not be found
	 */
	public static P2PActivity fetchByUuid_First(
		String uuid, OrderByComparator<P2PActivity> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last p2p activity in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching p2p activity
	 * @throws NoSuchP2PActivityException if a matching p2p activity could not be found
	 */
	public static P2PActivity findByUuid_Last(
			String uuid, OrderByComparator<P2PActivity> orderByComparator)
		throws com.ted.lms.learning.activity.p2p.exception.
			NoSuchP2PActivityException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last p2p activity in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching p2p activity, or <code>null</code> if a matching p2p activity could not be found
	 */
	public static P2PActivity fetchByUuid_Last(
		String uuid, OrderByComparator<P2PActivity> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the p2p activities before and after the current p2p activity in the ordered set where uuid = &#63;.
	 *
	 * @param p2pActivityId the primary key of the current p2p activity
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next p2p activity
	 * @throws NoSuchP2PActivityException if a p2p activity with the primary key could not be found
	 */
	public static P2PActivity[] findByUuid_PrevAndNext(
			long p2pActivityId, String uuid,
			OrderByComparator<P2PActivity> orderByComparator)
		throws com.ted.lms.learning.activity.p2p.exception.
			NoSuchP2PActivityException {

		return getPersistence().findByUuid_PrevAndNext(
			p2pActivityId, uuid, orderByComparator);
	}

	/**
	 * Removes all the p2p activities where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of p2p activities where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching p2p activities
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the p2p activity where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchP2PActivityException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching p2p activity
	 * @throws NoSuchP2PActivityException if a matching p2p activity could not be found
	 */
	public static P2PActivity findByUUID_G(String uuid, long groupId)
		throws com.ted.lms.learning.activity.p2p.exception.
			NoSuchP2PActivityException {

		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the p2p activity where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching p2p activity, or <code>null</code> if a matching p2p activity could not be found
	 */
	public static P2PActivity fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the p2p activity where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching p2p activity, or <code>null</code> if a matching p2p activity could not be found
	 */
	public static P2PActivity fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		return getPersistence().fetchByUUID_G(uuid, groupId, useFinderCache);
	}

	/**
	 * Removes the p2p activity where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the p2p activity that was removed
	 */
	public static P2PActivity removeByUUID_G(String uuid, long groupId)
		throws com.ted.lms.learning.activity.p2p.exception.
			NoSuchP2PActivityException {

		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the number of p2p activities where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching p2p activities
	 */
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	 * Returns all the p2p activities where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching p2p activities
	 */
	public static List<P2PActivity> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the p2p activities where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of p2p activities
	 * @param end the upper bound of the range of p2p activities (not inclusive)
	 * @return the range of matching p2p activities
	 */
	public static List<P2PActivity> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the p2p activities where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of p2p activities
	 * @param end the upper bound of the range of p2p activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching p2p activities
	 */
	public static List<P2PActivity> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<P2PActivity> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the p2p activities where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of p2p activities
	 * @param end the upper bound of the range of p2p activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching p2p activities
	 */
	public static List<P2PActivity> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<P2PActivity> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first p2p activity in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching p2p activity
	 * @throws NoSuchP2PActivityException if a matching p2p activity could not be found
	 */
	public static P2PActivity findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<P2PActivity> orderByComparator)
		throws com.ted.lms.learning.activity.p2p.exception.
			NoSuchP2PActivityException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first p2p activity in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching p2p activity, or <code>null</code> if a matching p2p activity could not be found
	 */
	public static P2PActivity fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<P2PActivity> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last p2p activity in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching p2p activity
	 * @throws NoSuchP2PActivityException if a matching p2p activity could not be found
	 */
	public static P2PActivity findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<P2PActivity> orderByComparator)
		throws com.ted.lms.learning.activity.p2p.exception.
			NoSuchP2PActivityException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last p2p activity in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching p2p activity, or <code>null</code> if a matching p2p activity could not be found
	 */
	public static P2PActivity fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<P2PActivity> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

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
	public static P2PActivity[] findByUuid_C_PrevAndNext(
			long p2pActivityId, String uuid, long companyId,
			OrderByComparator<P2PActivity> orderByComparator)
		throws com.ted.lms.learning.activity.p2p.exception.
			NoSuchP2PActivityException {

		return getPersistence().findByUuid_C_PrevAndNext(
			p2pActivityId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the p2p activities where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of p2p activities where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching p2p activities
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the p2p activity where actId = &#63; and userId = &#63; or throws a <code>NoSuchP2PActivityException</code> if it could not be found.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @return the matching p2p activity
	 * @throws NoSuchP2PActivityException if a matching p2p activity could not be found
	 */
	public static P2PActivity findByActIdUserId(long actId, long userId)
		throws com.ted.lms.learning.activity.p2p.exception.
			NoSuchP2PActivityException {

		return getPersistence().findByActIdUserId(actId, userId);
	}

	/**
	 * Returns the p2p activity where actId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @return the matching p2p activity, or <code>null</code> if a matching p2p activity could not be found
	 */
	public static P2PActivity fetchByActIdUserId(long actId, long userId) {
		return getPersistence().fetchByActIdUserId(actId, userId);
	}

	/**
	 * Returns the p2p activity where actId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching p2p activity, or <code>null</code> if a matching p2p activity could not be found
	 */
	public static P2PActivity fetchByActIdUserId(
		long actId, long userId, boolean useFinderCache) {

		return getPersistence().fetchByActIdUserId(
			actId, userId, useFinderCache);
	}

	/**
	 * Removes the p2p activity where actId = &#63; and userId = &#63; from the database.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @return the p2p activity that was removed
	 */
	public static P2PActivity removeByActIdUserId(long actId, long userId)
		throws com.ted.lms.learning.activity.p2p.exception.
			NoSuchP2PActivityException {

		return getPersistence().removeByActIdUserId(actId, userId);
	}

	/**
	 * Returns the number of p2p activities where actId = &#63; and userId = &#63;.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @return the number of matching p2p activities
	 */
	public static int countByActIdUserId(long actId, long userId) {
		return getPersistence().countByActIdUserId(actId, userId);
	}

	/**
	 * Returns all the p2p activities where actId = &#63;.
	 *
	 * @param actId the act ID
	 * @return the matching p2p activities
	 */
	public static List<P2PActivity> findByActId(long actId) {
		return getPersistence().findByActId(actId);
	}

	/**
	 * Returns a range of all the p2p activities where actId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param actId the act ID
	 * @param start the lower bound of the range of p2p activities
	 * @param end the upper bound of the range of p2p activities (not inclusive)
	 * @return the range of matching p2p activities
	 */
	public static List<P2PActivity> findByActId(
		long actId, int start, int end) {

		return getPersistence().findByActId(actId, start, end);
	}

	/**
	 * Returns an ordered range of all the p2p activities where actId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param actId the act ID
	 * @param start the lower bound of the range of p2p activities
	 * @param end the upper bound of the range of p2p activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching p2p activities
	 */
	public static List<P2PActivity> findByActId(
		long actId, int start, int end,
		OrderByComparator<P2PActivity> orderByComparator) {

		return getPersistence().findByActId(
			actId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the p2p activities where actId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param actId the act ID
	 * @param start the lower bound of the range of p2p activities
	 * @param end the upper bound of the range of p2p activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching p2p activities
	 */
	public static List<P2PActivity> findByActId(
		long actId, int start, int end,
		OrderByComparator<P2PActivity> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByActId(
			actId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first p2p activity in the ordered set where actId = &#63;.
	 *
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching p2p activity
	 * @throws NoSuchP2PActivityException if a matching p2p activity could not be found
	 */
	public static P2PActivity findByActId_First(
			long actId, OrderByComparator<P2PActivity> orderByComparator)
		throws com.ted.lms.learning.activity.p2p.exception.
			NoSuchP2PActivityException {

		return getPersistence().findByActId_First(actId, orderByComparator);
	}

	/**
	 * Returns the first p2p activity in the ordered set where actId = &#63;.
	 *
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching p2p activity, or <code>null</code> if a matching p2p activity could not be found
	 */
	public static P2PActivity fetchByActId_First(
		long actId, OrderByComparator<P2PActivity> orderByComparator) {

		return getPersistence().fetchByActId_First(actId, orderByComparator);
	}

	/**
	 * Returns the last p2p activity in the ordered set where actId = &#63;.
	 *
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching p2p activity
	 * @throws NoSuchP2PActivityException if a matching p2p activity could not be found
	 */
	public static P2PActivity findByActId_Last(
			long actId, OrderByComparator<P2PActivity> orderByComparator)
		throws com.ted.lms.learning.activity.p2p.exception.
			NoSuchP2PActivityException {

		return getPersistence().findByActId_Last(actId, orderByComparator);
	}

	/**
	 * Returns the last p2p activity in the ordered set where actId = &#63;.
	 *
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching p2p activity, or <code>null</code> if a matching p2p activity could not be found
	 */
	public static P2PActivity fetchByActId_Last(
		long actId, OrderByComparator<P2PActivity> orderByComparator) {

		return getPersistence().fetchByActId_Last(actId, orderByComparator);
	}

	/**
	 * Returns the p2p activities before and after the current p2p activity in the ordered set where actId = &#63;.
	 *
	 * @param p2pActivityId the primary key of the current p2p activity
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next p2p activity
	 * @throws NoSuchP2PActivityException if a p2p activity with the primary key could not be found
	 */
	public static P2PActivity[] findByActId_PrevAndNext(
			long p2pActivityId, long actId,
			OrderByComparator<P2PActivity> orderByComparator)
		throws com.ted.lms.learning.activity.p2p.exception.
			NoSuchP2PActivityException {

		return getPersistence().findByActId_PrevAndNext(
			p2pActivityId, actId, orderByComparator);
	}

	/**
	 * Removes all the p2p activities where actId = &#63; from the database.
	 *
	 * @param actId the act ID
	 */
	public static void removeByActId(long actId) {
		getPersistence().removeByActId(actId);
	}

	/**
	 * Returns the number of p2p activities where actId = &#63;.
	 *
	 * @param actId the act ID
	 * @return the number of matching p2p activities
	 */
	public static int countByActId(long actId) {
		return getPersistence().countByActId(actId);
	}

	/**
	 * Returns all the p2p activities where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching p2p activities
	 */
	public static List<P2PActivity> findByUserId(long userId) {
		return getPersistence().findByUserId(userId);
	}

	/**
	 * Returns a range of all the p2p activities where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of p2p activities
	 * @param end the upper bound of the range of p2p activities (not inclusive)
	 * @return the range of matching p2p activities
	 */
	public static List<P2PActivity> findByUserId(
		long userId, int start, int end) {

		return getPersistence().findByUserId(userId, start, end);
	}

	/**
	 * Returns an ordered range of all the p2p activities where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of p2p activities
	 * @param end the upper bound of the range of p2p activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching p2p activities
	 */
	public static List<P2PActivity> findByUserId(
		long userId, int start, int end,
		OrderByComparator<P2PActivity> orderByComparator) {

		return getPersistence().findByUserId(
			userId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the p2p activities where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of p2p activities
	 * @param end the upper bound of the range of p2p activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching p2p activities
	 */
	public static List<P2PActivity> findByUserId(
		long userId, int start, int end,
		OrderByComparator<P2PActivity> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUserId(
			userId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first p2p activity in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching p2p activity
	 * @throws NoSuchP2PActivityException if a matching p2p activity could not be found
	 */
	public static P2PActivity findByUserId_First(
			long userId, OrderByComparator<P2PActivity> orderByComparator)
		throws com.ted.lms.learning.activity.p2p.exception.
			NoSuchP2PActivityException {

		return getPersistence().findByUserId_First(userId, orderByComparator);
	}

	/**
	 * Returns the first p2p activity in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching p2p activity, or <code>null</code> if a matching p2p activity could not be found
	 */
	public static P2PActivity fetchByUserId_First(
		long userId, OrderByComparator<P2PActivity> orderByComparator) {

		return getPersistence().fetchByUserId_First(userId, orderByComparator);
	}

	/**
	 * Returns the last p2p activity in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching p2p activity
	 * @throws NoSuchP2PActivityException if a matching p2p activity could not be found
	 */
	public static P2PActivity findByUserId_Last(
			long userId, OrderByComparator<P2PActivity> orderByComparator)
		throws com.ted.lms.learning.activity.p2p.exception.
			NoSuchP2PActivityException {

		return getPersistence().findByUserId_Last(userId, orderByComparator);
	}

	/**
	 * Returns the last p2p activity in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching p2p activity, or <code>null</code> if a matching p2p activity could not be found
	 */
	public static P2PActivity fetchByUserId_Last(
		long userId, OrderByComparator<P2PActivity> orderByComparator) {

		return getPersistence().fetchByUserId_Last(userId, orderByComparator);
	}

	/**
	 * Returns the p2p activities before and after the current p2p activity in the ordered set where userId = &#63;.
	 *
	 * @param p2pActivityId the primary key of the current p2p activity
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next p2p activity
	 * @throws NoSuchP2PActivityException if a p2p activity with the primary key could not be found
	 */
	public static P2PActivity[] findByUserId_PrevAndNext(
			long p2pActivityId, long userId,
			OrderByComparator<P2PActivity> orderByComparator)
		throws com.ted.lms.learning.activity.p2p.exception.
			NoSuchP2PActivityException {

		return getPersistence().findByUserId_PrevAndNext(
			p2pActivityId, userId, orderByComparator);
	}

	/**
	 * Removes all the p2p activities where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	public static void removeByUserId(long userId) {
		getPersistence().removeByUserId(userId);
	}

	/**
	 * Returns the number of p2p activities where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching p2p activities
	 */
	public static int countByUserId(long userId) {
		return getPersistence().countByUserId(userId);
	}

	/**
	 * Returns all the p2p activities where asignationsCompleted = &#63;.
	 *
	 * @param asignationsCompleted the asignations completed
	 * @return the matching p2p activities
	 */
	public static List<P2PActivity> findByAsignationsCompleted(
		boolean asignationsCompleted) {

		return getPersistence().findByAsignationsCompleted(
			asignationsCompleted);
	}

	/**
	 * Returns a range of all the p2p activities where asignationsCompleted = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param asignationsCompleted the asignations completed
	 * @param start the lower bound of the range of p2p activities
	 * @param end the upper bound of the range of p2p activities (not inclusive)
	 * @return the range of matching p2p activities
	 */
	public static List<P2PActivity> findByAsignationsCompleted(
		boolean asignationsCompleted, int start, int end) {

		return getPersistence().findByAsignationsCompleted(
			asignationsCompleted, start, end);
	}

	/**
	 * Returns an ordered range of all the p2p activities where asignationsCompleted = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param asignationsCompleted the asignations completed
	 * @param start the lower bound of the range of p2p activities
	 * @param end the upper bound of the range of p2p activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching p2p activities
	 */
	public static List<P2PActivity> findByAsignationsCompleted(
		boolean asignationsCompleted, int start, int end,
		OrderByComparator<P2PActivity> orderByComparator) {

		return getPersistence().findByAsignationsCompleted(
			asignationsCompleted, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the p2p activities where asignationsCompleted = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param asignationsCompleted the asignations completed
	 * @param start the lower bound of the range of p2p activities
	 * @param end the upper bound of the range of p2p activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching p2p activities
	 */
	public static List<P2PActivity> findByAsignationsCompleted(
		boolean asignationsCompleted, int start, int end,
		OrderByComparator<P2PActivity> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByAsignationsCompleted(
			asignationsCompleted, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first p2p activity in the ordered set where asignationsCompleted = &#63;.
	 *
	 * @param asignationsCompleted the asignations completed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching p2p activity
	 * @throws NoSuchP2PActivityException if a matching p2p activity could not be found
	 */
	public static P2PActivity findByAsignationsCompleted_First(
			boolean asignationsCompleted,
			OrderByComparator<P2PActivity> orderByComparator)
		throws com.ted.lms.learning.activity.p2p.exception.
			NoSuchP2PActivityException {

		return getPersistence().findByAsignationsCompleted_First(
			asignationsCompleted, orderByComparator);
	}

	/**
	 * Returns the first p2p activity in the ordered set where asignationsCompleted = &#63;.
	 *
	 * @param asignationsCompleted the asignations completed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching p2p activity, or <code>null</code> if a matching p2p activity could not be found
	 */
	public static P2PActivity fetchByAsignationsCompleted_First(
		boolean asignationsCompleted,
		OrderByComparator<P2PActivity> orderByComparator) {

		return getPersistence().fetchByAsignationsCompleted_First(
			asignationsCompleted, orderByComparator);
	}

	/**
	 * Returns the last p2p activity in the ordered set where asignationsCompleted = &#63;.
	 *
	 * @param asignationsCompleted the asignations completed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching p2p activity
	 * @throws NoSuchP2PActivityException if a matching p2p activity could not be found
	 */
	public static P2PActivity findByAsignationsCompleted_Last(
			boolean asignationsCompleted,
			OrderByComparator<P2PActivity> orderByComparator)
		throws com.ted.lms.learning.activity.p2p.exception.
			NoSuchP2PActivityException {

		return getPersistence().findByAsignationsCompleted_Last(
			asignationsCompleted, orderByComparator);
	}

	/**
	 * Returns the last p2p activity in the ordered set where asignationsCompleted = &#63;.
	 *
	 * @param asignationsCompleted the asignations completed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching p2p activity, or <code>null</code> if a matching p2p activity could not be found
	 */
	public static P2PActivity fetchByAsignationsCompleted_Last(
		boolean asignationsCompleted,
		OrderByComparator<P2PActivity> orderByComparator) {

		return getPersistence().fetchByAsignationsCompleted_Last(
			asignationsCompleted, orderByComparator);
	}

	/**
	 * Returns the p2p activities before and after the current p2p activity in the ordered set where asignationsCompleted = &#63;.
	 *
	 * @param p2pActivityId the primary key of the current p2p activity
	 * @param asignationsCompleted the asignations completed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next p2p activity
	 * @throws NoSuchP2PActivityException if a p2p activity with the primary key could not be found
	 */
	public static P2PActivity[] findByAsignationsCompleted_PrevAndNext(
			long p2pActivityId, boolean asignationsCompleted,
			OrderByComparator<P2PActivity> orderByComparator)
		throws com.ted.lms.learning.activity.p2p.exception.
			NoSuchP2PActivityException {

		return getPersistence().findByAsignationsCompleted_PrevAndNext(
			p2pActivityId, asignationsCompleted, orderByComparator);
	}

	/**
	 * Removes all the p2p activities where asignationsCompleted = &#63; from the database.
	 *
	 * @param asignationsCompleted the asignations completed
	 */
	public static void removeByAsignationsCompleted(
		boolean asignationsCompleted) {

		getPersistence().removeByAsignationsCompleted(asignationsCompleted);
	}

	/**
	 * Returns the number of p2p activities where asignationsCompleted = &#63;.
	 *
	 * @param asignationsCompleted the asignations completed
	 * @return the number of matching p2p activities
	 */
	public static int countByAsignationsCompleted(
		boolean asignationsCompleted) {

		return getPersistence().countByAsignationsCompleted(
			asignationsCompleted);
	}

	/**
	 * Caches the p2p activity in the entity cache if it is enabled.
	 *
	 * @param p2pActivity the p2p activity
	 */
	public static void cacheResult(P2PActivity p2pActivity) {
		getPersistence().cacheResult(p2pActivity);
	}

	/**
	 * Caches the p2p activities in the entity cache if it is enabled.
	 *
	 * @param p2pActivities the p2p activities
	 */
	public static void cacheResult(List<P2PActivity> p2pActivities) {
		getPersistence().cacheResult(p2pActivities);
	}

	/**
	 * Creates a new p2p activity with the primary key. Does not add the p2p activity to the database.
	 *
	 * @param p2pActivityId the primary key for the new p2p activity
	 * @return the new p2p activity
	 */
	public static P2PActivity create(long p2pActivityId) {
		return getPersistence().create(p2pActivityId);
	}

	/**
	 * Removes the p2p activity with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param p2pActivityId the primary key of the p2p activity
	 * @return the p2p activity that was removed
	 * @throws NoSuchP2PActivityException if a p2p activity with the primary key could not be found
	 */
	public static P2PActivity remove(long p2pActivityId)
		throws com.ted.lms.learning.activity.p2p.exception.
			NoSuchP2PActivityException {

		return getPersistence().remove(p2pActivityId);
	}

	public static P2PActivity updateImpl(P2PActivity p2pActivity) {
		return getPersistence().updateImpl(p2pActivity);
	}

	/**
	 * Returns the p2p activity with the primary key or throws a <code>NoSuchP2PActivityException</code> if it could not be found.
	 *
	 * @param p2pActivityId the primary key of the p2p activity
	 * @return the p2p activity
	 * @throws NoSuchP2PActivityException if a p2p activity with the primary key could not be found
	 */
	public static P2PActivity findByPrimaryKey(long p2pActivityId)
		throws com.ted.lms.learning.activity.p2p.exception.
			NoSuchP2PActivityException {

		return getPersistence().findByPrimaryKey(p2pActivityId);
	}

	/**
	 * Returns the p2p activity with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param p2pActivityId the primary key of the p2p activity
	 * @return the p2p activity, or <code>null</code> if a p2p activity with the primary key could not be found
	 */
	public static P2PActivity fetchByPrimaryKey(long p2pActivityId) {
		return getPersistence().fetchByPrimaryKey(p2pActivityId);
	}

	/**
	 * Returns all the p2p activities.
	 *
	 * @return the p2p activities
	 */
	public static List<P2PActivity> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the p2p activities.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of p2p activities
	 * @param end the upper bound of the range of p2p activities (not inclusive)
	 * @return the range of p2p activities
	 */
	public static List<P2PActivity> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the p2p activities.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of p2p activities
	 * @param end the upper bound of the range of p2p activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of p2p activities
	 */
	public static List<P2PActivity> findAll(
		int start, int end, OrderByComparator<P2PActivity> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the p2p activities.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of p2p activities
	 * @param end the upper bound of the range of p2p activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of p2p activities
	 */
	public static List<P2PActivity> findAll(
		int start, int end, OrderByComparator<P2PActivity> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the p2p activities from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of p2p activities.
	 *
	 * @return the number of p2p activities
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static P2PActivityPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<P2PActivityPersistence, P2PActivityPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(P2PActivityPersistence.class);

		ServiceTracker<P2PActivityPersistence, P2PActivityPersistence>
			serviceTracker =
				new ServiceTracker
					<P2PActivityPersistence, P2PActivityPersistence>(
						bundle.getBundleContext(), P2PActivityPersistence.class,
						null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}