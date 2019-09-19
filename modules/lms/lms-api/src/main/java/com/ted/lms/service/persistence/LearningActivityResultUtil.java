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

import com.ted.lms.model.LearningActivityResult;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.annotation.versioning.ProviderType;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the learning activity result service. This utility wraps <code>com.ted.lms.service.persistence.impl.LearningActivityResultPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LearningActivityResultPersistence
 * @generated
 */
@ProviderType
public class LearningActivityResultUtil {

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
		LearningActivityResult learningActivityResult) {

		getPersistence().clearCache(learningActivityResult);
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
	public static Map<Serializable, LearningActivityResult> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<LearningActivityResult> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<LearningActivityResult> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<LearningActivityResult> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<LearningActivityResult> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static LearningActivityResult update(
		LearningActivityResult learningActivityResult) {

		return getPersistence().update(learningActivityResult);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static LearningActivityResult update(
		LearningActivityResult learningActivityResult,
		ServiceContext serviceContext) {

		return getPersistence().update(learningActivityResult, serviceContext);
	}

	/**
	 * Returns all the learning activity results where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching learning activity results
	 */
	public static List<LearningActivityResult> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the learning activity results where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of learning activity results
	 * @param end the upper bound of the range of learning activity results (not inclusive)
	 * @return the range of matching learning activity results
	 */
	public static List<LearningActivityResult> findByUuid(
		String uuid, int start, int end) {

		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the learning activity results where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of learning activity results
	 * @param end the upper bound of the range of learning activity results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching learning activity results
	 */
	public static List<LearningActivityResult> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<LearningActivityResult> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the learning activity results where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of learning activity results
	 * @param end the upper bound of the range of learning activity results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching learning activity results
	 */
	public static List<LearningActivityResult> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<LearningActivityResult> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first learning activity result in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity result
	 * @throws NoSuchLearningActivityResultException if a matching learning activity result could not be found
	 */
	public static LearningActivityResult findByUuid_First(
			String uuid,
			OrderByComparator<LearningActivityResult> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityResultException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first learning activity result in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity result, or <code>null</code> if a matching learning activity result could not be found
	 */
	public static LearningActivityResult fetchByUuid_First(
		String uuid,
		OrderByComparator<LearningActivityResult> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last learning activity result in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity result
	 * @throws NoSuchLearningActivityResultException if a matching learning activity result could not be found
	 */
	public static LearningActivityResult findByUuid_Last(
			String uuid,
			OrderByComparator<LearningActivityResult> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityResultException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last learning activity result in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity result, or <code>null</code> if a matching learning activity result could not be found
	 */
	public static LearningActivityResult fetchByUuid_Last(
		String uuid,
		OrderByComparator<LearningActivityResult> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the learning activity results before and after the current learning activity result in the ordered set where uuid = &#63;.
	 *
	 * @param larId the primary key of the current learning activity result
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next learning activity result
	 * @throws NoSuchLearningActivityResultException if a learning activity result with the primary key could not be found
	 */
	public static LearningActivityResult[] findByUuid_PrevAndNext(
			long larId, String uuid,
			OrderByComparator<LearningActivityResult> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityResultException {

		return getPersistence().findByUuid_PrevAndNext(
			larId, uuid, orderByComparator);
	}

	/**
	 * Removes all the learning activity results where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of learning activity results where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching learning activity results
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the learning activity result where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchLearningActivityResultException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching learning activity result
	 * @throws NoSuchLearningActivityResultException if a matching learning activity result could not be found
	 */
	public static LearningActivityResult findByUUID_G(String uuid, long groupId)
		throws com.ted.lms.exception.NoSuchLearningActivityResultException {

		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the learning activity result where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching learning activity result, or <code>null</code> if a matching learning activity result could not be found
	 */
	public static LearningActivityResult fetchByUUID_G(
		String uuid, long groupId) {

		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the learning activity result where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching learning activity result, or <code>null</code> if a matching learning activity result could not be found
	 */
	public static LearningActivityResult fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		return getPersistence().fetchByUUID_G(uuid, groupId, useFinderCache);
	}

	/**
	 * Removes the learning activity result where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the learning activity result that was removed
	 */
	public static LearningActivityResult removeByUUID_G(
			String uuid, long groupId)
		throws com.ted.lms.exception.NoSuchLearningActivityResultException {

		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the number of learning activity results where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching learning activity results
	 */
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	 * Returns all the learning activity results where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching learning activity results
	 */
	public static List<LearningActivityResult> findByUuid_C(
		String uuid, long companyId) {

		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the learning activity results where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of learning activity results
	 * @param end the upper bound of the range of learning activity results (not inclusive)
	 * @return the range of matching learning activity results
	 */
	public static List<LearningActivityResult> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the learning activity results where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of learning activity results
	 * @param end the upper bound of the range of learning activity results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching learning activity results
	 */
	public static List<LearningActivityResult> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<LearningActivityResult> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the learning activity results where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of learning activity results
	 * @param end the upper bound of the range of learning activity results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching learning activity results
	 */
	public static List<LearningActivityResult> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<LearningActivityResult> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first learning activity result in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity result
	 * @throws NoSuchLearningActivityResultException if a matching learning activity result could not be found
	 */
	public static LearningActivityResult findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<LearningActivityResult> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityResultException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first learning activity result in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity result, or <code>null</code> if a matching learning activity result could not be found
	 */
	public static LearningActivityResult fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<LearningActivityResult> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last learning activity result in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity result
	 * @throws NoSuchLearningActivityResultException if a matching learning activity result could not be found
	 */
	public static LearningActivityResult findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<LearningActivityResult> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityResultException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last learning activity result in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity result, or <code>null</code> if a matching learning activity result could not be found
	 */
	public static LearningActivityResult fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<LearningActivityResult> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

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
	public static LearningActivityResult[] findByUuid_C_PrevAndNext(
			long larId, String uuid, long companyId,
			OrderByComparator<LearningActivityResult> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityResultException {

		return getPersistence().findByUuid_C_PrevAndNext(
			larId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the learning activity results where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of learning activity results where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching learning activity results
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns all the learning activity results where actId = &#63;.
	 *
	 * @param actId the act ID
	 * @return the matching learning activity results
	 */
	public static List<LearningActivityResult> findByActId(long actId) {
		return getPersistence().findByActId(actId);
	}

	/**
	 * Returns a range of all the learning activity results where actId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param actId the act ID
	 * @param start the lower bound of the range of learning activity results
	 * @param end the upper bound of the range of learning activity results (not inclusive)
	 * @return the range of matching learning activity results
	 */
	public static List<LearningActivityResult> findByActId(
		long actId, int start, int end) {

		return getPersistence().findByActId(actId, start, end);
	}

	/**
	 * Returns an ordered range of all the learning activity results where actId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param actId the act ID
	 * @param start the lower bound of the range of learning activity results
	 * @param end the upper bound of the range of learning activity results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching learning activity results
	 */
	public static List<LearningActivityResult> findByActId(
		long actId, int start, int end,
		OrderByComparator<LearningActivityResult> orderByComparator) {

		return getPersistence().findByActId(
			actId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the learning activity results where actId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param actId the act ID
	 * @param start the lower bound of the range of learning activity results
	 * @param end the upper bound of the range of learning activity results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching learning activity results
	 */
	public static List<LearningActivityResult> findByActId(
		long actId, int start, int end,
		OrderByComparator<LearningActivityResult> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByActId(
			actId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first learning activity result in the ordered set where actId = &#63;.
	 *
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity result
	 * @throws NoSuchLearningActivityResultException if a matching learning activity result could not be found
	 */
	public static LearningActivityResult findByActId_First(
			long actId,
			OrderByComparator<LearningActivityResult> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityResultException {

		return getPersistence().findByActId_First(actId, orderByComparator);
	}

	/**
	 * Returns the first learning activity result in the ordered set where actId = &#63;.
	 *
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity result, or <code>null</code> if a matching learning activity result could not be found
	 */
	public static LearningActivityResult fetchByActId_First(
		long actId,
		OrderByComparator<LearningActivityResult> orderByComparator) {

		return getPersistence().fetchByActId_First(actId, orderByComparator);
	}

	/**
	 * Returns the last learning activity result in the ordered set where actId = &#63;.
	 *
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity result
	 * @throws NoSuchLearningActivityResultException if a matching learning activity result could not be found
	 */
	public static LearningActivityResult findByActId_Last(
			long actId,
			OrderByComparator<LearningActivityResult> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityResultException {

		return getPersistence().findByActId_Last(actId, orderByComparator);
	}

	/**
	 * Returns the last learning activity result in the ordered set where actId = &#63;.
	 *
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity result, or <code>null</code> if a matching learning activity result could not be found
	 */
	public static LearningActivityResult fetchByActId_Last(
		long actId,
		OrderByComparator<LearningActivityResult> orderByComparator) {

		return getPersistence().fetchByActId_Last(actId, orderByComparator);
	}

	/**
	 * Returns the learning activity results before and after the current learning activity result in the ordered set where actId = &#63;.
	 *
	 * @param larId the primary key of the current learning activity result
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next learning activity result
	 * @throws NoSuchLearningActivityResultException if a learning activity result with the primary key could not be found
	 */
	public static LearningActivityResult[] findByActId_PrevAndNext(
			long larId, long actId,
			OrderByComparator<LearningActivityResult> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityResultException {

		return getPersistence().findByActId_PrevAndNext(
			larId, actId, orderByComparator);
	}

	/**
	 * Removes all the learning activity results where actId = &#63; from the database.
	 *
	 * @param actId the act ID
	 */
	public static void removeByActId(long actId) {
		getPersistence().removeByActId(actId);
	}

	/**
	 * Returns the number of learning activity results where actId = &#63;.
	 *
	 * @param actId the act ID
	 * @return the number of matching learning activity results
	 */
	public static int countByActId(long actId) {
		return getPersistence().countByActId(actId);
	}

	/**
	 * Returns the learning activity result where actId = &#63; and userId = &#63; or throws a <code>NoSuchLearningActivityResultException</code> if it could not be found.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @return the matching learning activity result
	 * @throws NoSuchLearningActivityResultException if a matching learning activity result could not be found
	 */
	public static LearningActivityResult findByActIdUserId(
			long actId, long userId)
		throws com.ted.lms.exception.NoSuchLearningActivityResultException {

		return getPersistence().findByActIdUserId(actId, userId);
	}

	/**
	 * Returns the learning activity result where actId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @return the matching learning activity result, or <code>null</code> if a matching learning activity result could not be found
	 */
	public static LearningActivityResult fetchByActIdUserId(
		long actId, long userId) {

		return getPersistence().fetchByActIdUserId(actId, userId);
	}

	/**
	 * Returns the learning activity result where actId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching learning activity result, or <code>null</code> if a matching learning activity result could not be found
	 */
	public static LearningActivityResult fetchByActIdUserId(
		long actId, long userId, boolean useFinderCache) {

		return getPersistence().fetchByActIdUserId(
			actId, userId, useFinderCache);
	}

	/**
	 * Removes the learning activity result where actId = &#63; and userId = &#63; from the database.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @return the learning activity result that was removed
	 */
	public static LearningActivityResult removeByActIdUserId(
			long actId, long userId)
		throws com.ted.lms.exception.NoSuchLearningActivityResultException {

		return getPersistence().removeByActIdUserId(actId, userId);
	}

	/**
	 * Returns the number of learning activity results where actId = &#63; and userId = &#63;.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @return the number of matching learning activity results
	 */
	public static int countByActIdUserId(long actId, long userId) {
		return getPersistence().countByActIdUserId(actId, userId);
	}

	/**
	 * Caches the learning activity result in the entity cache if it is enabled.
	 *
	 * @param learningActivityResult the learning activity result
	 */
	public static void cacheResult(
		LearningActivityResult learningActivityResult) {

		getPersistence().cacheResult(learningActivityResult);
	}

	/**
	 * Caches the learning activity results in the entity cache if it is enabled.
	 *
	 * @param learningActivityResults the learning activity results
	 */
	public static void cacheResult(
		List<LearningActivityResult> learningActivityResults) {

		getPersistence().cacheResult(learningActivityResults);
	}

	/**
	 * Creates a new learning activity result with the primary key. Does not add the learning activity result to the database.
	 *
	 * @param larId the primary key for the new learning activity result
	 * @return the new learning activity result
	 */
	public static LearningActivityResult create(long larId) {
		return getPersistence().create(larId);
	}

	/**
	 * Removes the learning activity result with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param larId the primary key of the learning activity result
	 * @return the learning activity result that was removed
	 * @throws NoSuchLearningActivityResultException if a learning activity result with the primary key could not be found
	 */
	public static LearningActivityResult remove(long larId)
		throws com.ted.lms.exception.NoSuchLearningActivityResultException {

		return getPersistence().remove(larId);
	}

	public static LearningActivityResult updateImpl(
		LearningActivityResult learningActivityResult) {

		return getPersistence().updateImpl(learningActivityResult);
	}

	/**
	 * Returns the learning activity result with the primary key or throws a <code>NoSuchLearningActivityResultException</code> if it could not be found.
	 *
	 * @param larId the primary key of the learning activity result
	 * @return the learning activity result
	 * @throws NoSuchLearningActivityResultException if a learning activity result with the primary key could not be found
	 */
	public static LearningActivityResult findByPrimaryKey(long larId)
		throws com.ted.lms.exception.NoSuchLearningActivityResultException {

		return getPersistence().findByPrimaryKey(larId);
	}

	/**
	 * Returns the learning activity result with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param larId the primary key of the learning activity result
	 * @return the learning activity result, or <code>null</code> if a learning activity result with the primary key could not be found
	 */
	public static LearningActivityResult fetchByPrimaryKey(long larId) {
		return getPersistence().fetchByPrimaryKey(larId);
	}

	/**
	 * Returns all the learning activity results.
	 *
	 * @return the learning activity results
	 */
	public static List<LearningActivityResult> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the learning activity results.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of learning activity results
	 * @param end the upper bound of the range of learning activity results (not inclusive)
	 * @return the range of learning activity results
	 */
	public static List<LearningActivityResult> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the learning activity results.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of learning activity results
	 * @param end the upper bound of the range of learning activity results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of learning activity results
	 */
	public static List<LearningActivityResult> findAll(
		int start, int end,
		OrderByComparator<LearningActivityResult> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the learning activity results.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of learning activity results
	 * @param end the upper bound of the range of learning activity results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of learning activity results
	 */
	public static List<LearningActivityResult> findAll(
		int start, int end,
		OrderByComparator<LearningActivityResult> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the learning activity results from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of learning activity results.
	 *
	 * @return the number of learning activity results
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static LearningActivityResultPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<LearningActivityResultPersistence, LearningActivityResultPersistence>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			LearningActivityResultPersistence.class);

		ServiceTracker
			<LearningActivityResultPersistence,
			 LearningActivityResultPersistence> serviceTracker =
				new ServiceTracker
					<LearningActivityResultPersistence,
					 LearningActivityResultPersistence>(
						 bundle.getBundleContext(),
						 LearningActivityResultPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}