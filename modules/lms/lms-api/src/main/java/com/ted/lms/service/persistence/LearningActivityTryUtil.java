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

import com.ted.lms.model.LearningActivityTry;

import java.io.Serializable;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.annotation.versioning.ProviderType;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the learning activity try service. This utility wraps <code>com.ted.lms.service.persistence.impl.LearningActivityTryPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LearningActivityTryPersistence
 * @generated
 */
@ProviderType
public class LearningActivityTryUtil {

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
	public static void clearCache(LearningActivityTry learningActivityTry) {
		getPersistence().clearCache(learningActivityTry);
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
	public static Map<Serializable, LearningActivityTry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<LearningActivityTry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<LearningActivityTry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<LearningActivityTry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<LearningActivityTry> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static LearningActivityTry update(
		LearningActivityTry learningActivityTry) {

		return getPersistence().update(learningActivityTry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static LearningActivityTry update(
		LearningActivityTry learningActivityTry,
		ServiceContext serviceContext) {

		return getPersistence().update(learningActivityTry, serviceContext);
	}

	/**
	 * Returns all the learning activity tries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching learning activity tries
	 */
	public static List<LearningActivityTry> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the learning activity tries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityTryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of learning activity tries
	 * @param end the upper bound of the range of learning activity tries (not inclusive)
	 * @return the range of matching learning activity tries
	 */
	public static List<LearningActivityTry> findByUuid(
		String uuid, int start, int end) {

		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the learning activity tries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityTryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of learning activity tries
	 * @param end the upper bound of the range of learning activity tries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching learning activity tries
	 */
	public static List<LearningActivityTry> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<LearningActivityTry> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the learning activity tries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityTryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of learning activity tries
	 * @param end the upper bound of the range of learning activity tries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching learning activity tries
	 */
	public static List<LearningActivityTry> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<LearningActivityTry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first learning activity try in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity try
	 * @throws NoSuchLearningActivityTryException if a matching learning activity try could not be found
	 */
	public static LearningActivityTry findByUuid_First(
			String uuid,
			OrderByComparator<LearningActivityTry> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityTryException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first learning activity try in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity try, or <code>null</code> if a matching learning activity try could not be found
	 */
	public static LearningActivityTry fetchByUuid_First(
		String uuid, OrderByComparator<LearningActivityTry> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last learning activity try in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity try
	 * @throws NoSuchLearningActivityTryException if a matching learning activity try could not be found
	 */
	public static LearningActivityTry findByUuid_Last(
			String uuid,
			OrderByComparator<LearningActivityTry> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityTryException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last learning activity try in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity try, or <code>null</code> if a matching learning activity try could not be found
	 */
	public static LearningActivityTry fetchByUuid_Last(
		String uuid, OrderByComparator<LearningActivityTry> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the learning activity tries before and after the current learning activity try in the ordered set where uuid = &#63;.
	 *
	 * @param latId the primary key of the current learning activity try
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next learning activity try
	 * @throws NoSuchLearningActivityTryException if a learning activity try with the primary key could not be found
	 */
	public static LearningActivityTry[] findByUuid_PrevAndNext(
			long latId, String uuid,
			OrderByComparator<LearningActivityTry> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityTryException {

		return getPersistence().findByUuid_PrevAndNext(
			latId, uuid, orderByComparator);
	}

	/**
	 * Removes all the learning activity tries where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of learning activity tries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching learning activity tries
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the learning activity try where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchLearningActivityTryException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching learning activity try
	 * @throws NoSuchLearningActivityTryException if a matching learning activity try could not be found
	 */
	public static LearningActivityTry findByUUID_G(String uuid, long groupId)
		throws com.ted.lms.exception.NoSuchLearningActivityTryException {

		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the learning activity try where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching learning activity try, or <code>null</code> if a matching learning activity try could not be found
	 */
	public static LearningActivityTry fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the learning activity try where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching learning activity try, or <code>null</code> if a matching learning activity try could not be found
	 */
	public static LearningActivityTry fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		return getPersistence().fetchByUUID_G(uuid, groupId, useFinderCache);
	}

	/**
	 * Removes the learning activity try where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the learning activity try that was removed
	 */
	public static LearningActivityTry removeByUUID_G(String uuid, long groupId)
		throws com.ted.lms.exception.NoSuchLearningActivityTryException {

		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the number of learning activity tries where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching learning activity tries
	 */
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	 * Returns all the learning activity tries where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching learning activity tries
	 */
	public static List<LearningActivityTry> findByUuid_C(
		String uuid, long companyId) {

		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the learning activity tries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityTryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of learning activity tries
	 * @param end the upper bound of the range of learning activity tries (not inclusive)
	 * @return the range of matching learning activity tries
	 */
	public static List<LearningActivityTry> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the learning activity tries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityTryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of learning activity tries
	 * @param end the upper bound of the range of learning activity tries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching learning activity tries
	 */
	public static List<LearningActivityTry> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<LearningActivityTry> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the learning activity tries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityTryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of learning activity tries
	 * @param end the upper bound of the range of learning activity tries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching learning activity tries
	 */
	public static List<LearningActivityTry> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<LearningActivityTry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first learning activity try in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity try
	 * @throws NoSuchLearningActivityTryException if a matching learning activity try could not be found
	 */
	public static LearningActivityTry findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<LearningActivityTry> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityTryException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first learning activity try in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity try, or <code>null</code> if a matching learning activity try could not be found
	 */
	public static LearningActivityTry fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<LearningActivityTry> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last learning activity try in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity try
	 * @throws NoSuchLearningActivityTryException if a matching learning activity try could not be found
	 */
	public static LearningActivityTry findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<LearningActivityTry> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityTryException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last learning activity try in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity try, or <code>null</code> if a matching learning activity try could not be found
	 */
	public static LearningActivityTry fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<LearningActivityTry> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the learning activity tries before and after the current learning activity try in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param latId the primary key of the current learning activity try
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next learning activity try
	 * @throws NoSuchLearningActivityTryException if a learning activity try with the primary key could not be found
	 */
	public static LearningActivityTry[] findByUuid_C_PrevAndNext(
			long latId, String uuid, long companyId,
			OrderByComparator<LearningActivityTry> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityTryException {

		return getPersistence().findByUuid_C_PrevAndNext(
			latId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the learning activity tries where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of learning activity tries where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching learning activity tries
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns all the learning activity tries where actId = &#63;.
	 *
	 * @param actId the act ID
	 * @return the matching learning activity tries
	 */
	public static List<LearningActivityTry> findByActId(long actId) {
		return getPersistence().findByActId(actId);
	}

	/**
	 * Returns a range of all the learning activity tries where actId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityTryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param actId the act ID
	 * @param start the lower bound of the range of learning activity tries
	 * @param end the upper bound of the range of learning activity tries (not inclusive)
	 * @return the range of matching learning activity tries
	 */
	public static List<LearningActivityTry> findByActId(
		long actId, int start, int end) {

		return getPersistence().findByActId(actId, start, end);
	}

	/**
	 * Returns an ordered range of all the learning activity tries where actId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityTryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param actId the act ID
	 * @param start the lower bound of the range of learning activity tries
	 * @param end the upper bound of the range of learning activity tries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching learning activity tries
	 */
	public static List<LearningActivityTry> findByActId(
		long actId, int start, int end,
		OrderByComparator<LearningActivityTry> orderByComparator) {

		return getPersistence().findByActId(
			actId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the learning activity tries where actId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityTryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param actId the act ID
	 * @param start the lower bound of the range of learning activity tries
	 * @param end the upper bound of the range of learning activity tries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching learning activity tries
	 */
	public static List<LearningActivityTry> findByActId(
		long actId, int start, int end,
		OrderByComparator<LearningActivityTry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByActId(
			actId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first learning activity try in the ordered set where actId = &#63;.
	 *
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity try
	 * @throws NoSuchLearningActivityTryException if a matching learning activity try could not be found
	 */
	public static LearningActivityTry findByActId_First(
			long actId,
			OrderByComparator<LearningActivityTry> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityTryException {

		return getPersistence().findByActId_First(actId, orderByComparator);
	}

	/**
	 * Returns the first learning activity try in the ordered set where actId = &#63;.
	 *
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity try, or <code>null</code> if a matching learning activity try could not be found
	 */
	public static LearningActivityTry fetchByActId_First(
		long actId, OrderByComparator<LearningActivityTry> orderByComparator) {

		return getPersistence().fetchByActId_First(actId, orderByComparator);
	}

	/**
	 * Returns the last learning activity try in the ordered set where actId = &#63;.
	 *
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity try
	 * @throws NoSuchLearningActivityTryException if a matching learning activity try could not be found
	 */
	public static LearningActivityTry findByActId_Last(
			long actId,
			OrderByComparator<LearningActivityTry> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityTryException {

		return getPersistence().findByActId_Last(actId, orderByComparator);
	}

	/**
	 * Returns the last learning activity try in the ordered set where actId = &#63;.
	 *
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity try, or <code>null</code> if a matching learning activity try could not be found
	 */
	public static LearningActivityTry fetchByActId_Last(
		long actId, OrderByComparator<LearningActivityTry> orderByComparator) {

		return getPersistence().fetchByActId_Last(actId, orderByComparator);
	}

	/**
	 * Returns the learning activity tries before and after the current learning activity try in the ordered set where actId = &#63;.
	 *
	 * @param latId the primary key of the current learning activity try
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next learning activity try
	 * @throws NoSuchLearningActivityTryException if a learning activity try with the primary key could not be found
	 */
	public static LearningActivityTry[] findByActId_PrevAndNext(
			long latId, long actId,
			OrderByComparator<LearningActivityTry> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityTryException {

		return getPersistence().findByActId_PrevAndNext(
			latId, actId, orderByComparator);
	}

	/**
	 * Removes all the learning activity tries where actId = &#63; from the database.
	 *
	 * @param actId the act ID
	 */
	public static void removeByActId(long actId) {
		getPersistence().removeByActId(actId);
	}

	/**
	 * Returns the number of learning activity tries where actId = &#63;.
	 *
	 * @param actId the act ID
	 * @return the number of matching learning activity tries
	 */
	public static int countByActId(long actId) {
		return getPersistence().countByActId(actId);
	}

	/**
	 * Returns all the learning activity tries where actId = &#63; and userId = &#63;.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @return the matching learning activity tries
	 */
	public static List<LearningActivityTry> findByActIdUserId(
		long actId, long userId) {

		return getPersistence().findByActIdUserId(actId, userId);
	}

	/**
	 * Returns a range of all the learning activity tries where actId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityTryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of learning activity tries
	 * @param end the upper bound of the range of learning activity tries (not inclusive)
	 * @return the range of matching learning activity tries
	 */
	public static List<LearningActivityTry> findByActIdUserId(
		long actId, long userId, int start, int end) {

		return getPersistence().findByActIdUserId(actId, userId, start, end);
	}

	/**
	 * Returns an ordered range of all the learning activity tries where actId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityTryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of learning activity tries
	 * @param end the upper bound of the range of learning activity tries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching learning activity tries
	 */
	public static List<LearningActivityTry> findByActIdUserId(
		long actId, long userId, int start, int end,
		OrderByComparator<LearningActivityTry> orderByComparator) {

		return getPersistence().findByActIdUserId(
			actId, userId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the learning activity tries where actId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityTryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of learning activity tries
	 * @param end the upper bound of the range of learning activity tries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching learning activity tries
	 */
	public static List<LearningActivityTry> findByActIdUserId(
		long actId, long userId, int start, int end,
		OrderByComparator<LearningActivityTry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByActIdUserId(
			actId, userId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first learning activity try in the ordered set where actId = &#63; and userId = &#63;.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity try
	 * @throws NoSuchLearningActivityTryException if a matching learning activity try could not be found
	 */
	public static LearningActivityTry findByActIdUserId_First(
			long actId, long userId,
			OrderByComparator<LearningActivityTry> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityTryException {

		return getPersistence().findByActIdUserId_First(
			actId, userId, orderByComparator);
	}

	/**
	 * Returns the first learning activity try in the ordered set where actId = &#63; and userId = &#63;.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity try, or <code>null</code> if a matching learning activity try could not be found
	 */
	public static LearningActivityTry fetchByActIdUserId_First(
		long actId, long userId,
		OrderByComparator<LearningActivityTry> orderByComparator) {

		return getPersistence().fetchByActIdUserId_First(
			actId, userId, orderByComparator);
	}

	/**
	 * Returns the last learning activity try in the ordered set where actId = &#63; and userId = &#63;.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity try
	 * @throws NoSuchLearningActivityTryException if a matching learning activity try could not be found
	 */
	public static LearningActivityTry findByActIdUserId_Last(
			long actId, long userId,
			OrderByComparator<LearningActivityTry> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityTryException {

		return getPersistence().findByActIdUserId_Last(
			actId, userId, orderByComparator);
	}

	/**
	 * Returns the last learning activity try in the ordered set where actId = &#63; and userId = &#63;.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity try, or <code>null</code> if a matching learning activity try could not be found
	 */
	public static LearningActivityTry fetchByActIdUserId_Last(
		long actId, long userId,
		OrderByComparator<LearningActivityTry> orderByComparator) {

		return getPersistence().fetchByActIdUserId_Last(
			actId, userId, orderByComparator);
	}

	/**
	 * Returns the learning activity tries before and after the current learning activity try in the ordered set where actId = &#63; and userId = &#63;.
	 *
	 * @param latId the primary key of the current learning activity try
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next learning activity try
	 * @throws NoSuchLearningActivityTryException if a learning activity try with the primary key could not be found
	 */
	public static LearningActivityTry[] findByActIdUserId_PrevAndNext(
			long latId, long actId, long userId,
			OrderByComparator<LearningActivityTry> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityTryException {

		return getPersistence().findByActIdUserId_PrevAndNext(
			latId, actId, userId, orderByComparator);
	}

	/**
	 * Removes all the learning activity tries where actId = &#63; and userId = &#63; from the database.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 */
	public static void removeByActIdUserId(long actId, long userId) {
		getPersistence().removeByActIdUserId(actId, userId);
	}

	/**
	 * Returns the number of learning activity tries where actId = &#63; and userId = &#63;.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @return the number of matching learning activity tries
	 */
	public static int countByActIdUserId(long actId, long userId) {
		return getPersistence().countByActIdUserId(actId, userId);
	}

	/**
	 * Returns all the learning activity tries where actId = &#63; and userId = &#63; and endDate = &#63;.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param endDate the end date
	 * @return the matching learning activity tries
	 */
	public static List<LearningActivityTry> findByActIdUserIdEndDate(
		long actId, long userId, Date endDate) {

		return getPersistence().findByActIdUserIdEndDate(
			actId, userId, endDate);
	}

	/**
	 * Returns a range of all the learning activity tries where actId = &#63; and userId = &#63; and endDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityTryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param endDate the end date
	 * @param start the lower bound of the range of learning activity tries
	 * @param end the upper bound of the range of learning activity tries (not inclusive)
	 * @return the range of matching learning activity tries
	 */
	public static List<LearningActivityTry> findByActIdUserIdEndDate(
		long actId, long userId, Date endDate, int start, int end) {

		return getPersistence().findByActIdUserIdEndDate(
			actId, userId, endDate, start, end);
	}

	/**
	 * Returns an ordered range of all the learning activity tries where actId = &#63; and userId = &#63; and endDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityTryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param endDate the end date
	 * @param start the lower bound of the range of learning activity tries
	 * @param end the upper bound of the range of learning activity tries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching learning activity tries
	 */
	public static List<LearningActivityTry> findByActIdUserIdEndDate(
		long actId, long userId, Date endDate, int start, int end,
		OrderByComparator<LearningActivityTry> orderByComparator) {

		return getPersistence().findByActIdUserIdEndDate(
			actId, userId, endDate, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the learning activity tries where actId = &#63; and userId = &#63; and endDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityTryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param endDate the end date
	 * @param start the lower bound of the range of learning activity tries
	 * @param end the upper bound of the range of learning activity tries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching learning activity tries
	 */
	public static List<LearningActivityTry> findByActIdUserIdEndDate(
		long actId, long userId, Date endDate, int start, int end,
		OrderByComparator<LearningActivityTry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByActIdUserIdEndDate(
			actId, userId, endDate, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first learning activity try in the ordered set where actId = &#63; and userId = &#63; and endDate = &#63;.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param endDate the end date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity try
	 * @throws NoSuchLearningActivityTryException if a matching learning activity try could not be found
	 */
	public static LearningActivityTry findByActIdUserIdEndDate_First(
			long actId, long userId, Date endDate,
			OrderByComparator<LearningActivityTry> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityTryException {

		return getPersistence().findByActIdUserIdEndDate_First(
			actId, userId, endDate, orderByComparator);
	}

	/**
	 * Returns the first learning activity try in the ordered set where actId = &#63; and userId = &#63; and endDate = &#63;.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param endDate the end date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity try, or <code>null</code> if a matching learning activity try could not be found
	 */
	public static LearningActivityTry fetchByActIdUserIdEndDate_First(
		long actId, long userId, Date endDate,
		OrderByComparator<LearningActivityTry> orderByComparator) {

		return getPersistence().fetchByActIdUserIdEndDate_First(
			actId, userId, endDate, orderByComparator);
	}

	/**
	 * Returns the last learning activity try in the ordered set where actId = &#63; and userId = &#63; and endDate = &#63;.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param endDate the end date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity try
	 * @throws NoSuchLearningActivityTryException if a matching learning activity try could not be found
	 */
	public static LearningActivityTry findByActIdUserIdEndDate_Last(
			long actId, long userId, Date endDate,
			OrderByComparator<LearningActivityTry> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityTryException {

		return getPersistence().findByActIdUserIdEndDate_Last(
			actId, userId, endDate, orderByComparator);
	}

	/**
	 * Returns the last learning activity try in the ordered set where actId = &#63; and userId = &#63; and endDate = &#63;.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param endDate the end date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity try, or <code>null</code> if a matching learning activity try could not be found
	 */
	public static LearningActivityTry fetchByActIdUserIdEndDate_Last(
		long actId, long userId, Date endDate,
		OrderByComparator<LearningActivityTry> orderByComparator) {

		return getPersistence().fetchByActIdUserIdEndDate_Last(
			actId, userId, endDate, orderByComparator);
	}

	/**
	 * Returns the learning activity tries before and after the current learning activity try in the ordered set where actId = &#63; and userId = &#63; and endDate = &#63;.
	 *
	 * @param latId the primary key of the current learning activity try
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param endDate the end date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next learning activity try
	 * @throws NoSuchLearningActivityTryException if a learning activity try with the primary key could not be found
	 */
	public static LearningActivityTry[] findByActIdUserIdEndDate_PrevAndNext(
			long latId, long actId, long userId, Date endDate,
			OrderByComparator<LearningActivityTry> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityTryException {

		return getPersistence().findByActIdUserIdEndDate_PrevAndNext(
			latId, actId, userId, endDate, orderByComparator);
	}

	/**
	 * Removes all the learning activity tries where actId = &#63; and userId = &#63; and endDate = &#63; from the database.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param endDate the end date
	 */
	public static void removeByActIdUserIdEndDate(
		long actId, long userId, Date endDate) {

		getPersistence().removeByActIdUserIdEndDate(actId, userId, endDate);
	}

	/**
	 * Returns the number of learning activity tries where actId = &#63; and userId = &#63; and endDate = &#63;.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param endDate the end date
	 * @return the number of matching learning activity tries
	 */
	public static int countByActIdUserIdEndDate(
		long actId, long userId, Date endDate) {

		return getPersistence().countByActIdUserIdEndDate(
			actId, userId, endDate);
	}

	/**
	 * Caches the learning activity try in the entity cache if it is enabled.
	 *
	 * @param learningActivityTry the learning activity try
	 */
	public static void cacheResult(LearningActivityTry learningActivityTry) {
		getPersistence().cacheResult(learningActivityTry);
	}

	/**
	 * Caches the learning activity tries in the entity cache if it is enabled.
	 *
	 * @param learningActivityTries the learning activity tries
	 */
	public static void cacheResult(
		List<LearningActivityTry> learningActivityTries) {

		getPersistence().cacheResult(learningActivityTries);
	}

	/**
	 * Creates a new learning activity try with the primary key. Does not add the learning activity try to the database.
	 *
	 * @param latId the primary key for the new learning activity try
	 * @return the new learning activity try
	 */
	public static LearningActivityTry create(long latId) {
		return getPersistence().create(latId);
	}

	/**
	 * Removes the learning activity try with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param latId the primary key of the learning activity try
	 * @return the learning activity try that was removed
	 * @throws NoSuchLearningActivityTryException if a learning activity try with the primary key could not be found
	 */
	public static LearningActivityTry remove(long latId)
		throws com.ted.lms.exception.NoSuchLearningActivityTryException {

		return getPersistence().remove(latId);
	}

	public static LearningActivityTry updateImpl(
		LearningActivityTry learningActivityTry) {

		return getPersistence().updateImpl(learningActivityTry);
	}

	/**
	 * Returns the learning activity try with the primary key or throws a <code>NoSuchLearningActivityTryException</code> if it could not be found.
	 *
	 * @param latId the primary key of the learning activity try
	 * @return the learning activity try
	 * @throws NoSuchLearningActivityTryException if a learning activity try with the primary key could not be found
	 */
	public static LearningActivityTry findByPrimaryKey(long latId)
		throws com.ted.lms.exception.NoSuchLearningActivityTryException {

		return getPersistence().findByPrimaryKey(latId);
	}

	/**
	 * Returns the learning activity try with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param latId the primary key of the learning activity try
	 * @return the learning activity try, or <code>null</code> if a learning activity try with the primary key could not be found
	 */
	public static LearningActivityTry fetchByPrimaryKey(long latId) {
		return getPersistence().fetchByPrimaryKey(latId);
	}

	/**
	 * Returns all the learning activity tries.
	 *
	 * @return the learning activity tries
	 */
	public static List<LearningActivityTry> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the learning activity tries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityTryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of learning activity tries
	 * @param end the upper bound of the range of learning activity tries (not inclusive)
	 * @return the range of learning activity tries
	 */
	public static List<LearningActivityTry> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the learning activity tries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityTryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of learning activity tries
	 * @param end the upper bound of the range of learning activity tries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of learning activity tries
	 */
	public static List<LearningActivityTry> findAll(
		int start, int end,
		OrderByComparator<LearningActivityTry> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the learning activity tries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityTryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of learning activity tries
	 * @param end the upper bound of the range of learning activity tries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of learning activity tries
	 */
	public static List<LearningActivityTry> findAll(
		int start, int end,
		OrderByComparator<LearningActivityTry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the learning activity tries from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of learning activity tries.
	 *
	 * @return the number of learning activity tries
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static LearningActivityTryPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<LearningActivityTryPersistence, LearningActivityTryPersistence>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			LearningActivityTryPersistence.class);

		ServiceTracker
			<LearningActivityTryPersistence, LearningActivityTryPersistence>
				serviceTracker =
					new ServiceTracker
						<LearningActivityTryPersistence,
						 LearningActivityTryPersistence>(
							 bundle.getBundleContext(),
							 LearningActivityTryPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}