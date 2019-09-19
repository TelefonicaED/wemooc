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

import com.ted.lms.model.LearningActivity;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.annotation.versioning.ProviderType;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the learning activity service. This utility wraps <code>com.ted.lms.service.persistence.impl.LearningActivityPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LearningActivityPersistence
 * @generated
 */
@ProviderType
public class LearningActivityUtil {

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
	public static void clearCache(LearningActivity learningActivity) {
		getPersistence().clearCache(learningActivity);
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
	public static Map<Serializable, LearningActivity> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<LearningActivity> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<LearningActivity> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<LearningActivity> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<LearningActivity> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static LearningActivity update(LearningActivity learningActivity) {
		return getPersistence().update(learningActivity);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static LearningActivity update(
		LearningActivity learningActivity, ServiceContext serviceContext) {

		return getPersistence().update(learningActivity, serviceContext);
	}

	/**
	 * Returns all the learning activities where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching learning activities
	 */
	public static List<LearningActivity> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the learning activities where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @return the range of matching learning activities
	 */
	public static List<LearningActivity> findByUuid(
		String uuid, int start, int end) {

		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the learning activities where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching learning activities
	 */
	public static List<LearningActivity> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<LearningActivity> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the learning activities where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching learning activities
	 */
	public static List<LearningActivity> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<LearningActivity> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first learning activity in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity
	 * @throws NoSuchLearningActivityException if a matching learning activity could not be found
	 */
	public static LearningActivity findByUuid_First(
			String uuid, OrderByComparator<LearningActivity> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first learning activity in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity, or <code>null</code> if a matching learning activity could not be found
	 */
	public static LearningActivity fetchByUuid_First(
		String uuid, OrderByComparator<LearningActivity> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last learning activity in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity
	 * @throws NoSuchLearningActivityException if a matching learning activity could not be found
	 */
	public static LearningActivity findByUuid_Last(
			String uuid, OrderByComparator<LearningActivity> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last learning activity in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity, or <code>null</code> if a matching learning activity could not be found
	 */
	public static LearningActivity fetchByUuid_Last(
		String uuid, OrderByComparator<LearningActivity> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the learning activities before and after the current learning activity in the ordered set where uuid = &#63;.
	 *
	 * @param actId the primary key of the current learning activity
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next learning activity
	 * @throws NoSuchLearningActivityException if a learning activity with the primary key could not be found
	 */
	public static LearningActivity[] findByUuid_PrevAndNext(
			long actId, String uuid,
			OrderByComparator<LearningActivity> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityException {

		return getPersistence().findByUuid_PrevAndNext(
			actId, uuid, orderByComparator);
	}

	/**
	 * Removes all the learning activities where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of learning activities where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching learning activities
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the learning activity where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchLearningActivityException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching learning activity
	 * @throws NoSuchLearningActivityException if a matching learning activity could not be found
	 */
	public static LearningActivity findByUUID_G(String uuid, long groupId)
		throws com.ted.lms.exception.NoSuchLearningActivityException {

		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the learning activity where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching learning activity, or <code>null</code> if a matching learning activity could not be found
	 */
	public static LearningActivity fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the learning activity where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching learning activity, or <code>null</code> if a matching learning activity could not be found
	 */
	public static LearningActivity fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		return getPersistence().fetchByUUID_G(uuid, groupId, useFinderCache);
	}

	/**
	 * Removes the learning activity where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the learning activity that was removed
	 */
	public static LearningActivity removeByUUID_G(String uuid, long groupId)
		throws com.ted.lms.exception.NoSuchLearningActivityException {

		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the number of learning activities where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching learning activities
	 */
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	 * Returns all the learning activities where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching learning activities
	 */
	public static List<LearningActivity> findByUuid_C(
		String uuid, long companyId) {

		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the learning activities where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @return the range of matching learning activities
	 */
	public static List<LearningActivity> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the learning activities where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching learning activities
	 */
	public static List<LearningActivity> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<LearningActivity> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the learning activities where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching learning activities
	 */
	public static List<LearningActivity> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<LearningActivity> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first learning activity in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity
	 * @throws NoSuchLearningActivityException if a matching learning activity could not be found
	 */
	public static LearningActivity findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<LearningActivity> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first learning activity in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity, or <code>null</code> if a matching learning activity could not be found
	 */
	public static LearningActivity fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<LearningActivity> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last learning activity in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity
	 * @throws NoSuchLearningActivityException if a matching learning activity could not be found
	 */
	public static LearningActivity findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<LearningActivity> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last learning activity in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity, or <code>null</code> if a matching learning activity could not be found
	 */
	public static LearningActivity fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<LearningActivity> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the learning activities before and after the current learning activity in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param actId the primary key of the current learning activity
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next learning activity
	 * @throws NoSuchLearningActivityException if a learning activity with the primary key could not be found
	 */
	public static LearningActivity[] findByUuid_C_PrevAndNext(
			long actId, String uuid, long companyId,
			OrderByComparator<LearningActivity> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityException {

		return getPersistence().findByUuid_C_PrevAndNext(
			actId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the learning activities where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of learning activities where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching learning activities
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns all the learning activities where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching learning activities
	 */
	public static List<LearningActivity> findByCompanyId(long companyId) {
		return getPersistence().findByCompanyId(companyId);
	}

	/**
	 * Returns a range of all the learning activities where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @return the range of matching learning activities
	 */
	public static List<LearningActivity> findByCompanyId(
		long companyId, int start, int end) {

		return getPersistence().findByCompanyId(companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the learning activities where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching learning activities
	 */
	public static List<LearningActivity> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<LearningActivity> orderByComparator) {

		return getPersistence().findByCompanyId(
			companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the learning activities where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching learning activities
	 */
	public static List<LearningActivity> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<LearningActivity> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByCompanyId(
			companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first learning activity in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity
	 * @throws NoSuchLearningActivityException if a matching learning activity could not be found
	 */
	public static LearningActivity findByCompanyId_First(
			long companyId,
			OrderByComparator<LearningActivity> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityException {

		return getPersistence().findByCompanyId_First(
			companyId, orderByComparator);
	}

	/**
	 * Returns the first learning activity in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity, or <code>null</code> if a matching learning activity could not be found
	 */
	public static LearningActivity fetchByCompanyId_First(
		long companyId, OrderByComparator<LearningActivity> orderByComparator) {

		return getPersistence().fetchByCompanyId_First(
			companyId, orderByComparator);
	}

	/**
	 * Returns the last learning activity in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity
	 * @throws NoSuchLearningActivityException if a matching learning activity could not be found
	 */
	public static LearningActivity findByCompanyId_Last(
			long companyId,
			OrderByComparator<LearningActivity> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityException {

		return getPersistence().findByCompanyId_Last(
			companyId, orderByComparator);
	}

	/**
	 * Returns the last learning activity in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity, or <code>null</code> if a matching learning activity could not be found
	 */
	public static LearningActivity fetchByCompanyId_Last(
		long companyId, OrderByComparator<LearningActivity> orderByComparator) {

		return getPersistence().fetchByCompanyId_Last(
			companyId, orderByComparator);
	}

	/**
	 * Returns the learning activities before and after the current learning activity in the ordered set where companyId = &#63;.
	 *
	 * @param actId the primary key of the current learning activity
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next learning activity
	 * @throws NoSuchLearningActivityException if a learning activity with the primary key could not be found
	 */
	public static LearningActivity[] findByCompanyId_PrevAndNext(
			long actId, long companyId,
			OrderByComparator<LearningActivity> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityException {

		return getPersistence().findByCompanyId_PrevAndNext(
			actId, companyId, orderByComparator);
	}

	/**
	 * Removes all the learning activities where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	public static void removeByCompanyId(long companyId) {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	 * Returns the number of learning activities where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching learning activities
	 */
	public static int countByCompanyId(long companyId) {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	 * Returns all the learning activities where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching learning activities
	 */
	public static List<LearningActivity> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	 * Returns a range of all the learning activities where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @return the range of matching learning activities
	 */
	public static List<LearningActivity> findByGroupId(
		long groupId, int start, int end) {

		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	 * Returns an ordered range of all the learning activities where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching learning activities
	 */
	public static List<LearningActivity> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<LearningActivity> orderByComparator) {

		return getPersistence().findByGroupId(
			groupId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the learning activities where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching learning activities
	 */
	public static List<LearningActivity> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<LearningActivity> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByGroupId(
			groupId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first learning activity in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity
	 * @throws NoSuchLearningActivityException if a matching learning activity could not be found
	 */
	public static LearningActivity findByGroupId_First(
			long groupId, OrderByComparator<LearningActivity> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityException {

		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	 * Returns the first learning activity in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity, or <code>null</code> if a matching learning activity could not be found
	 */
	public static LearningActivity fetchByGroupId_First(
		long groupId, OrderByComparator<LearningActivity> orderByComparator) {

		return getPersistence().fetchByGroupId_First(
			groupId, orderByComparator);
	}

	/**
	 * Returns the last learning activity in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity
	 * @throws NoSuchLearningActivityException if a matching learning activity could not be found
	 */
	public static LearningActivity findByGroupId_Last(
			long groupId, OrderByComparator<LearningActivity> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityException {

		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the last learning activity in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity, or <code>null</code> if a matching learning activity could not be found
	 */
	public static LearningActivity fetchByGroupId_Last(
		long groupId, OrderByComparator<LearningActivity> orderByComparator) {

		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the learning activities before and after the current learning activity in the ordered set where groupId = &#63;.
	 *
	 * @param actId the primary key of the current learning activity
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next learning activity
	 * @throws NoSuchLearningActivityException if a learning activity with the primary key could not be found
	 */
	public static LearningActivity[] findByGroupId_PrevAndNext(
			long actId, long groupId,
			OrderByComparator<LearningActivity> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityException {

		return getPersistence().findByGroupId_PrevAndNext(
			actId, groupId, orderByComparator);
	}

	/**
	 * Returns all the learning activities that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching learning activities that the user has permission to view
	 */
	public static List<LearningActivity> filterFindByGroupId(long groupId) {
		return getPersistence().filterFindByGroupId(groupId);
	}

	/**
	 * Returns a range of all the learning activities that the user has permission to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @return the range of matching learning activities that the user has permission to view
	 */
	public static List<LearningActivity> filterFindByGroupId(
		long groupId, int start, int end) {

		return getPersistence().filterFindByGroupId(groupId, start, end);
	}

	/**
	 * Returns an ordered range of all the learning activities that the user has permissions to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching learning activities that the user has permission to view
	 */
	public static List<LearningActivity> filterFindByGroupId(
		long groupId, int start, int end,
		OrderByComparator<LearningActivity> orderByComparator) {

		return getPersistence().filterFindByGroupId(
			groupId, start, end, orderByComparator);
	}

	/**
	 * Returns the learning activities before and after the current learning activity in the ordered set of learning activities that the user has permission to view where groupId = &#63;.
	 *
	 * @param actId the primary key of the current learning activity
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next learning activity
	 * @throws NoSuchLearningActivityException if a learning activity with the primary key could not be found
	 */
	public static LearningActivity[] filterFindByGroupId_PrevAndNext(
			long actId, long groupId,
			OrderByComparator<LearningActivity> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityException {

		return getPersistence().filterFindByGroupId_PrevAndNext(
			actId, groupId, orderByComparator);
	}

	/**
	 * Removes all the learning activities where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	 * Returns the number of learning activities where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching learning activities
	 */
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	 * Returns the number of learning activities that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching learning activities that the user has permission to view
	 */
	public static int filterCountByGroupId(long groupId) {
		return getPersistence().filterCountByGroupId(groupId);
	}

	/**
	 * Returns all the learning activities where moduleId = &#63;.
	 *
	 * @param moduleId the module ID
	 * @return the matching learning activities
	 */
	public static List<LearningActivity> findByModuleId(long moduleId) {
		return getPersistence().findByModuleId(moduleId);
	}

	/**
	 * Returns a range of all the learning activities where moduleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param moduleId the module ID
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @return the range of matching learning activities
	 */
	public static List<LearningActivity> findByModuleId(
		long moduleId, int start, int end) {

		return getPersistence().findByModuleId(moduleId, start, end);
	}

	/**
	 * Returns an ordered range of all the learning activities where moduleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param moduleId the module ID
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching learning activities
	 */
	public static List<LearningActivity> findByModuleId(
		long moduleId, int start, int end,
		OrderByComparator<LearningActivity> orderByComparator) {

		return getPersistence().findByModuleId(
			moduleId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the learning activities where moduleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param moduleId the module ID
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching learning activities
	 */
	public static List<LearningActivity> findByModuleId(
		long moduleId, int start, int end,
		OrderByComparator<LearningActivity> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByModuleId(
			moduleId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first learning activity in the ordered set where moduleId = &#63;.
	 *
	 * @param moduleId the module ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity
	 * @throws NoSuchLearningActivityException if a matching learning activity could not be found
	 */
	public static LearningActivity findByModuleId_First(
			long moduleId,
			OrderByComparator<LearningActivity> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityException {

		return getPersistence().findByModuleId_First(
			moduleId, orderByComparator);
	}

	/**
	 * Returns the first learning activity in the ordered set where moduleId = &#63;.
	 *
	 * @param moduleId the module ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity, or <code>null</code> if a matching learning activity could not be found
	 */
	public static LearningActivity fetchByModuleId_First(
		long moduleId, OrderByComparator<LearningActivity> orderByComparator) {

		return getPersistence().fetchByModuleId_First(
			moduleId, orderByComparator);
	}

	/**
	 * Returns the last learning activity in the ordered set where moduleId = &#63;.
	 *
	 * @param moduleId the module ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity
	 * @throws NoSuchLearningActivityException if a matching learning activity could not be found
	 */
	public static LearningActivity findByModuleId_Last(
			long moduleId,
			OrderByComparator<LearningActivity> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityException {

		return getPersistence().findByModuleId_Last(
			moduleId, orderByComparator);
	}

	/**
	 * Returns the last learning activity in the ordered set where moduleId = &#63;.
	 *
	 * @param moduleId the module ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity, or <code>null</code> if a matching learning activity could not be found
	 */
	public static LearningActivity fetchByModuleId_Last(
		long moduleId, OrderByComparator<LearningActivity> orderByComparator) {

		return getPersistence().fetchByModuleId_Last(
			moduleId, orderByComparator);
	}

	/**
	 * Returns the learning activities before and after the current learning activity in the ordered set where moduleId = &#63;.
	 *
	 * @param actId the primary key of the current learning activity
	 * @param moduleId the module ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next learning activity
	 * @throws NoSuchLearningActivityException if a learning activity with the primary key could not be found
	 */
	public static LearningActivity[] findByModuleId_PrevAndNext(
			long actId, long moduleId,
			OrderByComparator<LearningActivity> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityException {

		return getPersistence().findByModuleId_PrevAndNext(
			actId, moduleId, orderByComparator);
	}

	/**
	 * Removes all the learning activities where moduleId = &#63; from the database.
	 *
	 * @param moduleId the module ID
	 */
	public static void removeByModuleId(long moduleId) {
		getPersistence().removeByModuleId(moduleId);
	}

	/**
	 * Returns the number of learning activities where moduleId = &#63;.
	 *
	 * @param moduleId the module ID
	 * @return the number of matching learning activities
	 */
	public static int countByModuleId(long moduleId) {
		return getPersistence().countByModuleId(moduleId);
	}

	/**
	 * Returns all the learning activities where moduleId = &#63; and required = &#63;.
	 *
	 * @param moduleId the module ID
	 * @param required the required
	 * @return the matching learning activities
	 */
	public static List<LearningActivity> findByModuleIdRequired(
		long moduleId, boolean required) {

		return getPersistence().findByModuleIdRequired(moduleId, required);
	}

	/**
	 * Returns a range of all the learning activities where moduleId = &#63; and required = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param moduleId the module ID
	 * @param required the required
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @return the range of matching learning activities
	 */
	public static List<LearningActivity> findByModuleIdRequired(
		long moduleId, boolean required, int start, int end) {

		return getPersistence().findByModuleIdRequired(
			moduleId, required, start, end);
	}

	/**
	 * Returns an ordered range of all the learning activities where moduleId = &#63; and required = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param moduleId the module ID
	 * @param required the required
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching learning activities
	 */
	public static List<LearningActivity> findByModuleIdRequired(
		long moduleId, boolean required, int start, int end,
		OrderByComparator<LearningActivity> orderByComparator) {

		return getPersistence().findByModuleIdRequired(
			moduleId, required, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the learning activities where moduleId = &#63; and required = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param moduleId the module ID
	 * @param required the required
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching learning activities
	 */
	public static List<LearningActivity> findByModuleIdRequired(
		long moduleId, boolean required, int start, int end,
		OrderByComparator<LearningActivity> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByModuleIdRequired(
			moduleId, required, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first learning activity in the ordered set where moduleId = &#63; and required = &#63;.
	 *
	 * @param moduleId the module ID
	 * @param required the required
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity
	 * @throws NoSuchLearningActivityException if a matching learning activity could not be found
	 */
	public static LearningActivity findByModuleIdRequired_First(
			long moduleId, boolean required,
			OrderByComparator<LearningActivity> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityException {

		return getPersistence().findByModuleIdRequired_First(
			moduleId, required, orderByComparator);
	}

	/**
	 * Returns the first learning activity in the ordered set where moduleId = &#63; and required = &#63;.
	 *
	 * @param moduleId the module ID
	 * @param required the required
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity, or <code>null</code> if a matching learning activity could not be found
	 */
	public static LearningActivity fetchByModuleIdRequired_First(
		long moduleId, boolean required,
		OrderByComparator<LearningActivity> orderByComparator) {

		return getPersistence().fetchByModuleIdRequired_First(
			moduleId, required, orderByComparator);
	}

	/**
	 * Returns the last learning activity in the ordered set where moduleId = &#63; and required = &#63;.
	 *
	 * @param moduleId the module ID
	 * @param required the required
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity
	 * @throws NoSuchLearningActivityException if a matching learning activity could not be found
	 */
	public static LearningActivity findByModuleIdRequired_Last(
			long moduleId, boolean required,
			OrderByComparator<LearningActivity> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityException {

		return getPersistence().findByModuleIdRequired_Last(
			moduleId, required, orderByComparator);
	}

	/**
	 * Returns the last learning activity in the ordered set where moduleId = &#63; and required = &#63;.
	 *
	 * @param moduleId the module ID
	 * @param required the required
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity, or <code>null</code> if a matching learning activity could not be found
	 */
	public static LearningActivity fetchByModuleIdRequired_Last(
		long moduleId, boolean required,
		OrderByComparator<LearningActivity> orderByComparator) {

		return getPersistence().fetchByModuleIdRequired_Last(
			moduleId, required, orderByComparator);
	}

	/**
	 * Returns the learning activities before and after the current learning activity in the ordered set where moduleId = &#63; and required = &#63;.
	 *
	 * @param actId the primary key of the current learning activity
	 * @param moduleId the module ID
	 * @param required the required
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next learning activity
	 * @throws NoSuchLearningActivityException if a learning activity with the primary key could not be found
	 */
	public static LearningActivity[] findByModuleIdRequired_PrevAndNext(
			long actId, long moduleId, boolean required,
			OrderByComparator<LearningActivity> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityException {

		return getPersistence().findByModuleIdRequired_PrevAndNext(
			actId, moduleId, required, orderByComparator);
	}

	/**
	 * Removes all the learning activities where moduleId = &#63; and required = &#63; from the database.
	 *
	 * @param moduleId the module ID
	 * @param required the required
	 */
	public static void removeByModuleIdRequired(
		long moduleId, boolean required) {

		getPersistence().removeByModuleIdRequired(moduleId, required);
	}

	/**
	 * Returns the number of learning activities where moduleId = &#63; and required = &#63;.
	 *
	 * @param moduleId the module ID
	 * @param required the required
	 * @return the number of matching learning activities
	 */
	public static int countByModuleIdRequired(long moduleId, boolean required) {
		return getPersistence().countByModuleIdRequired(moduleId, required);
	}

	/**
	 * Returns all the learning activities where groupId = &#63; and required = &#63;.
	 *
	 * @param groupId the group ID
	 * @param required the required
	 * @return the matching learning activities
	 */
	public static List<LearningActivity> findByGroupIdRequired(
		long groupId, boolean required) {

		return getPersistence().findByGroupIdRequired(groupId, required);
	}

	/**
	 * Returns a range of all the learning activities where groupId = &#63; and required = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param required the required
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @return the range of matching learning activities
	 */
	public static List<LearningActivity> findByGroupIdRequired(
		long groupId, boolean required, int start, int end) {

		return getPersistence().findByGroupIdRequired(
			groupId, required, start, end);
	}

	/**
	 * Returns an ordered range of all the learning activities where groupId = &#63; and required = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param required the required
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching learning activities
	 */
	public static List<LearningActivity> findByGroupIdRequired(
		long groupId, boolean required, int start, int end,
		OrderByComparator<LearningActivity> orderByComparator) {

		return getPersistence().findByGroupIdRequired(
			groupId, required, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the learning activities where groupId = &#63; and required = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param required the required
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching learning activities
	 */
	public static List<LearningActivity> findByGroupIdRequired(
		long groupId, boolean required, int start, int end,
		OrderByComparator<LearningActivity> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByGroupIdRequired(
			groupId, required, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first learning activity in the ordered set where groupId = &#63; and required = &#63;.
	 *
	 * @param groupId the group ID
	 * @param required the required
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity
	 * @throws NoSuchLearningActivityException if a matching learning activity could not be found
	 */
	public static LearningActivity findByGroupIdRequired_First(
			long groupId, boolean required,
			OrderByComparator<LearningActivity> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityException {

		return getPersistence().findByGroupIdRequired_First(
			groupId, required, orderByComparator);
	}

	/**
	 * Returns the first learning activity in the ordered set where groupId = &#63; and required = &#63;.
	 *
	 * @param groupId the group ID
	 * @param required the required
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity, or <code>null</code> if a matching learning activity could not be found
	 */
	public static LearningActivity fetchByGroupIdRequired_First(
		long groupId, boolean required,
		OrderByComparator<LearningActivity> orderByComparator) {

		return getPersistence().fetchByGroupIdRequired_First(
			groupId, required, orderByComparator);
	}

	/**
	 * Returns the last learning activity in the ordered set where groupId = &#63; and required = &#63;.
	 *
	 * @param groupId the group ID
	 * @param required the required
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity
	 * @throws NoSuchLearningActivityException if a matching learning activity could not be found
	 */
	public static LearningActivity findByGroupIdRequired_Last(
			long groupId, boolean required,
			OrderByComparator<LearningActivity> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityException {

		return getPersistence().findByGroupIdRequired_Last(
			groupId, required, orderByComparator);
	}

	/**
	 * Returns the last learning activity in the ordered set where groupId = &#63; and required = &#63;.
	 *
	 * @param groupId the group ID
	 * @param required the required
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity, or <code>null</code> if a matching learning activity could not be found
	 */
	public static LearningActivity fetchByGroupIdRequired_Last(
		long groupId, boolean required,
		OrderByComparator<LearningActivity> orderByComparator) {

		return getPersistence().fetchByGroupIdRequired_Last(
			groupId, required, orderByComparator);
	}

	/**
	 * Returns the learning activities before and after the current learning activity in the ordered set where groupId = &#63; and required = &#63;.
	 *
	 * @param actId the primary key of the current learning activity
	 * @param groupId the group ID
	 * @param required the required
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next learning activity
	 * @throws NoSuchLearningActivityException if a learning activity with the primary key could not be found
	 */
	public static LearningActivity[] findByGroupIdRequired_PrevAndNext(
			long actId, long groupId, boolean required,
			OrderByComparator<LearningActivity> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityException {

		return getPersistence().findByGroupIdRequired_PrevAndNext(
			actId, groupId, required, orderByComparator);
	}

	/**
	 * Returns all the learning activities that the user has permission to view where groupId = &#63; and required = &#63;.
	 *
	 * @param groupId the group ID
	 * @param required the required
	 * @return the matching learning activities that the user has permission to view
	 */
	public static List<LearningActivity> filterFindByGroupIdRequired(
		long groupId, boolean required) {

		return getPersistence().filterFindByGroupIdRequired(groupId, required);
	}

	/**
	 * Returns a range of all the learning activities that the user has permission to view where groupId = &#63; and required = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param required the required
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @return the range of matching learning activities that the user has permission to view
	 */
	public static List<LearningActivity> filterFindByGroupIdRequired(
		long groupId, boolean required, int start, int end) {

		return getPersistence().filterFindByGroupIdRequired(
			groupId, required, start, end);
	}

	/**
	 * Returns an ordered range of all the learning activities that the user has permissions to view where groupId = &#63; and required = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param required the required
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching learning activities that the user has permission to view
	 */
	public static List<LearningActivity> filterFindByGroupIdRequired(
		long groupId, boolean required, int start, int end,
		OrderByComparator<LearningActivity> orderByComparator) {

		return getPersistence().filterFindByGroupIdRequired(
			groupId, required, start, end, orderByComparator);
	}

	/**
	 * Returns the learning activities before and after the current learning activity in the ordered set of learning activities that the user has permission to view where groupId = &#63; and required = &#63;.
	 *
	 * @param actId the primary key of the current learning activity
	 * @param groupId the group ID
	 * @param required the required
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next learning activity
	 * @throws NoSuchLearningActivityException if a learning activity with the primary key could not be found
	 */
	public static LearningActivity[] filterFindByGroupIdRequired_PrevAndNext(
			long actId, long groupId, boolean required,
			OrderByComparator<LearningActivity> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityException {

		return getPersistence().filterFindByGroupIdRequired_PrevAndNext(
			actId, groupId, required, orderByComparator);
	}

	/**
	 * Removes all the learning activities where groupId = &#63; and required = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param required the required
	 */
	public static void removeByGroupIdRequired(long groupId, boolean required) {
		getPersistence().removeByGroupIdRequired(groupId, required);
	}

	/**
	 * Returns the number of learning activities where groupId = &#63; and required = &#63;.
	 *
	 * @param groupId the group ID
	 * @param required the required
	 * @return the number of matching learning activities
	 */
	public static int countByGroupIdRequired(long groupId, boolean required) {
		return getPersistence().countByGroupIdRequired(groupId, required);
	}

	/**
	 * Returns the number of learning activities that the user has permission to view where groupId = &#63; and required = &#63;.
	 *
	 * @param groupId the group ID
	 * @param required the required
	 * @return the number of matching learning activities that the user has permission to view
	 */
	public static int filterCountByGroupIdRequired(
		long groupId, boolean required) {

		return getPersistence().filterCountByGroupIdRequired(groupId, required);
	}

	/**
	 * Returns all the learning activities where typeId = &#63;.
	 *
	 * @param typeId the type ID
	 * @return the matching learning activities
	 */
	public static List<LearningActivity> findByTypeId(long typeId) {
		return getPersistence().findByTypeId(typeId);
	}

	/**
	 * Returns a range of all the learning activities where typeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param typeId the type ID
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @return the range of matching learning activities
	 */
	public static List<LearningActivity> findByTypeId(
		long typeId, int start, int end) {

		return getPersistence().findByTypeId(typeId, start, end);
	}

	/**
	 * Returns an ordered range of all the learning activities where typeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param typeId the type ID
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching learning activities
	 */
	public static List<LearningActivity> findByTypeId(
		long typeId, int start, int end,
		OrderByComparator<LearningActivity> orderByComparator) {

		return getPersistence().findByTypeId(
			typeId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the learning activities where typeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param typeId the type ID
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching learning activities
	 */
	public static List<LearningActivity> findByTypeId(
		long typeId, int start, int end,
		OrderByComparator<LearningActivity> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByTypeId(
			typeId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first learning activity in the ordered set where typeId = &#63;.
	 *
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity
	 * @throws NoSuchLearningActivityException if a matching learning activity could not be found
	 */
	public static LearningActivity findByTypeId_First(
			long typeId, OrderByComparator<LearningActivity> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityException {

		return getPersistence().findByTypeId_First(typeId, orderByComparator);
	}

	/**
	 * Returns the first learning activity in the ordered set where typeId = &#63;.
	 *
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity, or <code>null</code> if a matching learning activity could not be found
	 */
	public static LearningActivity fetchByTypeId_First(
		long typeId, OrderByComparator<LearningActivity> orderByComparator) {

		return getPersistence().fetchByTypeId_First(typeId, orderByComparator);
	}

	/**
	 * Returns the last learning activity in the ordered set where typeId = &#63;.
	 *
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity
	 * @throws NoSuchLearningActivityException if a matching learning activity could not be found
	 */
	public static LearningActivity findByTypeId_Last(
			long typeId, OrderByComparator<LearningActivity> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityException {

		return getPersistence().findByTypeId_Last(typeId, orderByComparator);
	}

	/**
	 * Returns the last learning activity in the ordered set where typeId = &#63;.
	 *
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity, or <code>null</code> if a matching learning activity could not be found
	 */
	public static LearningActivity fetchByTypeId_Last(
		long typeId, OrderByComparator<LearningActivity> orderByComparator) {

		return getPersistence().fetchByTypeId_Last(typeId, orderByComparator);
	}

	/**
	 * Returns the learning activities before and after the current learning activity in the ordered set where typeId = &#63;.
	 *
	 * @param actId the primary key of the current learning activity
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next learning activity
	 * @throws NoSuchLearningActivityException if a learning activity with the primary key could not be found
	 */
	public static LearningActivity[] findByTypeId_PrevAndNext(
			long actId, long typeId,
			OrderByComparator<LearningActivity> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityException {

		return getPersistence().findByTypeId_PrevAndNext(
			actId, typeId, orderByComparator);
	}

	/**
	 * Removes all the learning activities where typeId = &#63; from the database.
	 *
	 * @param typeId the type ID
	 */
	public static void removeByTypeId(long typeId) {
		getPersistence().removeByTypeId(typeId);
	}

	/**
	 * Returns the number of learning activities where typeId = &#63;.
	 *
	 * @param typeId the type ID
	 * @return the number of matching learning activities
	 */
	public static int countByTypeId(long typeId) {
		return getPersistence().countByTypeId(typeId);
	}

	/**
	 * Returns all the learning activities where groupId = &#63; and moduleId = &#63; and priority &gt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 * @return the matching learning activities
	 */
	public static List<LearningActivity> findByModuleIdNextLearningActivities(
		long groupId, long moduleId, long priority) {

		return getPersistence().findByModuleIdNextLearningActivities(
			groupId, moduleId, priority);
	}

	/**
	 * Returns a range of all the learning activities where groupId = &#63; and moduleId = &#63; and priority &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @return the range of matching learning activities
	 */
	public static List<LearningActivity> findByModuleIdNextLearningActivities(
		long groupId, long moduleId, long priority, int start, int end) {

		return getPersistence().findByModuleIdNextLearningActivities(
			groupId, moduleId, priority, start, end);
	}

	/**
	 * Returns an ordered range of all the learning activities where groupId = &#63; and moduleId = &#63; and priority &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching learning activities
	 */
	public static List<LearningActivity> findByModuleIdNextLearningActivities(
		long groupId, long moduleId, long priority, int start, int end,
		OrderByComparator<LearningActivity> orderByComparator) {

		return getPersistence().findByModuleIdNextLearningActivities(
			groupId, moduleId, priority, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the learning activities where groupId = &#63; and moduleId = &#63; and priority &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching learning activities
	 */
	public static List<LearningActivity> findByModuleIdNextLearningActivities(
		long groupId, long moduleId, long priority, int start, int end,
		OrderByComparator<LearningActivity> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByModuleIdNextLearningActivities(
			groupId, moduleId, priority, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first learning activity in the ordered set where groupId = &#63; and moduleId = &#63; and priority &gt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity
	 * @throws NoSuchLearningActivityException if a matching learning activity could not be found
	 */
	public static LearningActivity findByModuleIdNextLearningActivities_First(
			long groupId, long moduleId, long priority,
			OrderByComparator<LearningActivity> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityException {

		return getPersistence().findByModuleIdNextLearningActivities_First(
			groupId, moduleId, priority, orderByComparator);
	}

	/**
	 * Returns the first learning activity in the ordered set where groupId = &#63; and moduleId = &#63; and priority &gt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity, or <code>null</code> if a matching learning activity could not be found
	 */
	public static LearningActivity fetchByModuleIdNextLearningActivities_First(
		long groupId, long moduleId, long priority,
		OrderByComparator<LearningActivity> orderByComparator) {

		return getPersistence().fetchByModuleIdNextLearningActivities_First(
			groupId, moduleId, priority, orderByComparator);
	}

	/**
	 * Returns the last learning activity in the ordered set where groupId = &#63; and moduleId = &#63; and priority &gt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity
	 * @throws NoSuchLearningActivityException if a matching learning activity could not be found
	 */
	public static LearningActivity findByModuleIdNextLearningActivities_Last(
			long groupId, long moduleId, long priority,
			OrderByComparator<LearningActivity> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityException {

		return getPersistence().findByModuleIdNextLearningActivities_Last(
			groupId, moduleId, priority, orderByComparator);
	}

	/**
	 * Returns the last learning activity in the ordered set where groupId = &#63; and moduleId = &#63; and priority &gt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity, or <code>null</code> if a matching learning activity could not be found
	 */
	public static LearningActivity fetchByModuleIdNextLearningActivities_Last(
		long groupId, long moduleId, long priority,
		OrderByComparator<LearningActivity> orderByComparator) {

		return getPersistence().fetchByModuleIdNextLearningActivities_Last(
			groupId, moduleId, priority, orderByComparator);
	}

	/**
	 * Returns the learning activities before and after the current learning activity in the ordered set where groupId = &#63; and moduleId = &#63; and priority &gt; &#63;.
	 *
	 * @param actId the primary key of the current learning activity
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next learning activity
	 * @throws NoSuchLearningActivityException if a learning activity with the primary key could not be found
	 */
	public static LearningActivity[]
			findByModuleIdNextLearningActivities_PrevAndNext(
				long actId, long groupId, long moduleId, long priority,
				OrderByComparator<LearningActivity> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityException {

		return getPersistence().
			findByModuleIdNextLearningActivities_PrevAndNext(
				actId, groupId, moduleId, priority, orderByComparator);
	}

	/**
	 * Returns all the learning activities that the user has permission to view where groupId = &#63; and moduleId = &#63; and priority &gt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 * @return the matching learning activities that the user has permission to view
	 */
	public static List<LearningActivity>
		filterFindByModuleIdNextLearningActivities(
			long groupId, long moduleId, long priority) {

		return getPersistence().filterFindByModuleIdNextLearningActivities(
			groupId, moduleId, priority);
	}

	/**
	 * Returns a range of all the learning activities that the user has permission to view where groupId = &#63; and moduleId = &#63; and priority &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @return the range of matching learning activities that the user has permission to view
	 */
	public static List<LearningActivity>
		filterFindByModuleIdNextLearningActivities(
			long groupId, long moduleId, long priority, int start, int end) {

		return getPersistence().filterFindByModuleIdNextLearningActivities(
			groupId, moduleId, priority, start, end);
	}

	/**
	 * Returns an ordered range of all the learning activities that the user has permissions to view where groupId = &#63; and moduleId = &#63; and priority &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching learning activities that the user has permission to view
	 */
	public static List<LearningActivity>
		filterFindByModuleIdNextLearningActivities(
			long groupId, long moduleId, long priority, int start, int end,
			OrderByComparator<LearningActivity> orderByComparator) {

		return getPersistence().filterFindByModuleIdNextLearningActivities(
			groupId, moduleId, priority, start, end, orderByComparator);
	}

	/**
	 * Returns the learning activities before and after the current learning activity in the ordered set of learning activities that the user has permission to view where groupId = &#63; and moduleId = &#63; and priority &gt; &#63;.
	 *
	 * @param actId the primary key of the current learning activity
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next learning activity
	 * @throws NoSuchLearningActivityException if a learning activity with the primary key could not be found
	 */
	public static LearningActivity[]
			filterFindByModuleIdNextLearningActivities_PrevAndNext(
				long actId, long groupId, long moduleId, long priority,
				OrderByComparator<LearningActivity> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityException {

		return getPersistence().
			filterFindByModuleIdNextLearningActivities_PrevAndNext(
				actId, groupId, moduleId, priority, orderByComparator);
	}

	/**
	 * Removes all the learning activities where groupId = &#63; and moduleId = &#63; and priority &gt; &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 */
	public static void removeByModuleIdNextLearningActivities(
		long groupId, long moduleId, long priority) {

		getPersistence().removeByModuleIdNextLearningActivities(
			groupId, moduleId, priority);
	}

	/**
	 * Returns the number of learning activities where groupId = &#63; and moduleId = &#63; and priority &gt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 * @return the number of matching learning activities
	 */
	public static int countByModuleIdNextLearningActivities(
		long groupId, long moduleId, long priority) {

		return getPersistence().countByModuleIdNextLearningActivities(
			groupId, moduleId, priority);
	}

	/**
	 * Returns the number of learning activities that the user has permission to view where groupId = &#63; and moduleId = &#63; and priority &gt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 * @return the number of matching learning activities that the user has permission to view
	 */
	public static int filterCountByModuleIdNextLearningActivities(
		long groupId, long moduleId, long priority) {

		return getPersistence().filterCountByModuleIdNextLearningActivities(
			groupId, moduleId, priority);
	}

	/**
	 * Returns all the learning activities where groupId = &#63; and moduleId = &#63; and priority &lt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 * @return the matching learning activities
	 */
	public static List<LearningActivity>
		findByModuleIdPreviousLearningActivities(
			long groupId, long moduleId, long priority) {

		return getPersistence().findByModuleIdPreviousLearningActivities(
			groupId, moduleId, priority);
	}

	/**
	 * Returns a range of all the learning activities where groupId = &#63; and moduleId = &#63; and priority &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @return the range of matching learning activities
	 */
	public static List<LearningActivity>
		findByModuleIdPreviousLearningActivities(
			long groupId, long moduleId, long priority, int start, int end) {

		return getPersistence().findByModuleIdPreviousLearningActivities(
			groupId, moduleId, priority, start, end);
	}

	/**
	 * Returns an ordered range of all the learning activities where groupId = &#63; and moduleId = &#63; and priority &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching learning activities
	 */
	public static List<LearningActivity>
		findByModuleIdPreviousLearningActivities(
			long groupId, long moduleId, long priority, int start, int end,
			OrderByComparator<LearningActivity> orderByComparator) {

		return getPersistence().findByModuleIdPreviousLearningActivities(
			groupId, moduleId, priority, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the learning activities where groupId = &#63; and moduleId = &#63; and priority &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching learning activities
	 */
	public static List<LearningActivity>
		findByModuleIdPreviousLearningActivities(
			long groupId, long moduleId, long priority, int start, int end,
			OrderByComparator<LearningActivity> orderByComparator,
			boolean useFinderCache) {

		return getPersistence().findByModuleIdPreviousLearningActivities(
			groupId, moduleId, priority, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first learning activity in the ordered set where groupId = &#63; and moduleId = &#63; and priority &lt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity
	 * @throws NoSuchLearningActivityException if a matching learning activity could not be found
	 */
	public static LearningActivity
			findByModuleIdPreviousLearningActivities_First(
				long groupId, long moduleId, long priority,
				OrderByComparator<LearningActivity> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityException {

		return getPersistence().findByModuleIdPreviousLearningActivities_First(
			groupId, moduleId, priority, orderByComparator);
	}

	/**
	 * Returns the first learning activity in the ordered set where groupId = &#63; and moduleId = &#63; and priority &lt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity, or <code>null</code> if a matching learning activity could not be found
	 */
	public static LearningActivity
		fetchByModuleIdPreviousLearningActivities_First(
			long groupId, long moduleId, long priority,
			OrderByComparator<LearningActivity> orderByComparator) {

		return getPersistence().fetchByModuleIdPreviousLearningActivities_First(
			groupId, moduleId, priority, orderByComparator);
	}

	/**
	 * Returns the last learning activity in the ordered set where groupId = &#63; and moduleId = &#63; and priority &lt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity
	 * @throws NoSuchLearningActivityException if a matching learning activity could not be found
	 */
	public static LearningActivity
			findByModuleIdPreviousLearningActivities_Last(
				long groupId, long moduleId, long priority,
				OrderByComparator<LearningActivity> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityException {

		return getPersistence().findByModuleIdPreviousLearningActivities_Last(
			groupId, moduleId, priority, orderByComparator);
	}

	/**
	 * Returns the last learning activity in the ordered set where groupId = &#63; and moduleId = &#63; and priority &lt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity, or <code>null</code> if a matching learning activity could not be found
	 */
	public static LearningActivity
		fetchByModuleIdPreviousLearningActivities_Last(
			long groupId, long moduleId, long priority,
			OrderByComparator<LearningActivity> orderByComparator) {

		return getPersistence().fetchByModuleIdPreviousLearningActivities_Last(
			groupId, moduleId, priority, orderByComparator);
	}

	/**
	 * Returns the learning activities before and after the current learning activity in the ordered set where groupId = &#63; and moduleId = &#63; and priority &lt; &#63;.
	 *
	 * @param actId the primary key of the current learning activity
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next learning activity
	 * @throws NoSuchLearningActivityException if a learning activity with the primary key could not be found
	 */
	public static LearningActivity[]
			findByModuleIdPreviousLearningActivities_PrevAndNext(
				long actId, long groupId, long moduleId, long priority,
				OrderByComparator<LearningActivity> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityException {

		return getPersistence().
			findByModuleIdPreviousLearningActivities_PrevAndNext(
				actId, groupId, moduleId, priority, orderByComparator);
	}

	/**
	 * Returns all the learning activities that the user has permission to view where groupId = &#63; and moduleId = &#63; and priority &lt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 * @return the matching learning activities that the user has permission to view
	 */
	public static List<LearningActivity>
		filterFindByModuleIdPreviousLearningActivities(
			long groupId, long moduleId, long priority) {

		return getPersistence().filterFindByModuleIdPreviousLearningActivities(
			groupId, moduleId, priority);
	}

	/**
	 * Returns a range of all the learning activities that the user has permission to view where groupId = &#63; and moduleId = &#63; and priority &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @return the range of matching learning activities that the user has permission to view
	 */
	public static List<LearningActivity>
		filterFindByModuleIdPreviousLearningActivities(
			long groupId, long moduleId, long priority, int start, int end) {

		return getPersistence().filterFindByModuleIdPreviousLearningActivities(
			groupId, moduleId, priority, start, end);
	}

	/**
	 * Returns an ordered range of all the learning activities that the user has permissions to view where groupId = &#63; and moduleId = &#63; and priority &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching learning activities that the user has permission to view
	 */
	public static List<LearningActivity>
		filterFindByModuleIdPreviousLearningActivities(
			long groupId, long moduleId, long priority, int start, int end,
			OrderByComparator<LearningActivity> orderByComparator) {

		return getPersistence().filterFindByModuleIdPreviousLearningActivities(
			groupId, moduleId, priority, start, end, orderByComparator);
	}

	/**
	 * Returns the learning activities before and after the current learning activity in the ordered set of learning activities that the user has permission to view where groupId = &#63; and moduleId = &#63; and priority &lt; &#63;.
	 *
	 * @param actId the primary key of the current learning activity
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next learning activity
	 * @throws NoSuchLearningActivityException if a learning activity with the primary key could not be found
	 */
	public static LearningActivity[]
			filterFindByModuleIdPreviousLearningActivities_PrevAndNext(
				long actId, long groupId, long moduleId, long priority,
				OrderByComparator<LearningActivity> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityException {

		return getPersistence().
			filterFindByModuleIdPreviousLearningActivities_PrevAndNext(
				actId, groupId, moduleId, priority, orderByComparator);
	}

	/**
	 * Removes all the learning activities where groupId = &#63; and moduleId = &#63; and priority &lt; &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 */
	public static void removeByModuleIdPreviousLearningActivities(
		long groupId, long moduleId, long priority) {

		getPersistence().removeByModuleIdPreviousLearningActivities(
			groupId, moduleId, priority);
	}

	/**
	 * Returns the number of learning activities where groupId = &#63; and moduleId = &#63; and priority &lt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 * @return the number of matching learning activities
	 */
	public static int countByModuleIdPreviousLearningActivities(
		long groupId, long moduleId, long priority) {

		return getPersistence().countByModuleIdPreviousLearningActivities(
			groupId, moduleId, priority);
	}

	/**
	 * Returns the number of learning activities that the user has permission to view where groupId = &#63; and moduleId = &#63; and priority &lt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 * @return the number of matching learning activities that the user has permission to view
	 */
	public static int filterCountByModuleIdPreviousLearningActivities(
		long groupId, long moduleId, long priority) {

		return getPersistence().filterCountByModuleIdPreviousLearningActivities(
			groupId, moduleId, priority);
	}

	/**
	 * Returns all the learning activities where moduleId = &#63; and typeId &ne; &#63;.
	 *
	 * @param moduleId the module ID
	 * @param typeId the type ID
	 * @return the matching learning activities
	 */
	public static List<LearningActivity> findByModuleIdNotTypeId(
		long moduleId, long typeId) {

		return getPersistence().findByModuleIdNotTypeId(moduleId, typeId);
	}

	/**
	 * Returns a range of all the learning activities where moduleId = &#63; and typeId &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param moduleId the module ID
	 * @param typeId the type ID
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @return the range of matching learning activities
	 */
	public static List<LearningActivity> findByModuleIdNotTypeId(
		long moduleId, long typeId, int start, int end) {

		return getPersistence().findByModuleIdNotTypeId(
			moduleId, typeId, start, end);
	}

	/**
	 * Returns an ordered range of all the learning activities where moduleId = &#63; and typeId &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param moduleId the module ID
	 * @param typeId the type ID
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching learning activities
	 */
	public static List<LearningActivity> findByModuleIdNotTypeId(
		long moduleId, long typeId, int start, int end,
		OrderByComparator<LearningActivity> orderByComparator) {

		return getPersistence().findByModuleIdNotTypeId(
			moduleId, typeId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the learning activities where moduleId = &#63; and typeId &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param moduleId the module ID
	 * @param typeId the type ID
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching learning activities
	 */
	public static List<LearningActivity> findByModuleIdNotTypeId(
		long moduleId, long typeId, int start, int end,
		OrderByComparator<LearningActivity> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByModuleIdNotTypeId(
			moduleId, typeId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first learning activity in the ordered set where moduleId = &#63; and typeId &ne; &#63;.
	 *
	 * @param moduleId the module ID
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity
	 * @throws NoSuchLearningActivityException if a matching learning activity could not be found
	 */
	public static LearningActivity findByModuleIdNotTypeId_First(
			long moduleId, long typeId,
			OrderByComparator<LearningActivity> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityException {

		return getPersistence().findByModuleIdNotTypeId_First(
			moduleId, typeId, orderByComparator);
	}

	/**
	 * Returns the first learning activity in the ordered set where moduleId = &#63; and typeId &ne; &#63;.
	 *
	 * @param moduleId the module ID
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity, or <code>null</code> if a matching learning activity could not be found
	 */
	public static LearningActivity fetchByModuleIdNotTypeId_First(
		long moduleId, long typeId,
		OrderByComparator<LearningActivity> orderByComparator) {

		return getPersistence().fetchByModuleIdNotTypeId_First(
			moduleId, typeId, orderByComparator);
	}

	/**
	 * Returns the last learning activity in the ordered set where moduleId = &#63; and typeId &ne; &#63;.
	 *
	 * @param moduleId the module ID
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity
	 * @throws NoSuchLearningActivityException if a matching learning activity could not be found
	 */
	public static LearningActivity findByModuleIdNotTypeId_Last(
			long moduleId, long typeId,
			OrderByComparator<LearningActivity> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityException {

		return getPersistence().findByModuleIdNotTypeId_Last(
			moduleId, typeId, orderByComparator);
	}

	/**
	 * Returns the last learning activity in the ordered set where moduleId = &#63; and typeId &ne; &#63;.
	 *
	 * @param moduleId the module ID
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity, or <code>null</code> if a matching learning activity could not be found
	 */
	public static LearningActivity fetchByModuleIdNotTypeId_Last(
		long moduleId, long typeId,
		OrderByComparator<LearningActivity> orderByComparator) {

		return getPersistence().fetchByModuleIdNotTypeId_Last(
			moduleId, typeId, orderByComparator);
	}

	/**
	 * Returns the learning activities before and after the current learning activity in the ordered set where moduleId = &#63; and typeId &ne; &#63;.
	 *
	 * @param actId the primary key of the current learning activity
	 * @param moduleId the module ID
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next learning activity
	 * @throws NoSuchLearningActivityException if a learning activity with the primary key could not be found
	 */
	public static LearningActivity[] findByModuleIdNotTypeId_PrevAndNext(
			long actId, long moduleId, long typeId,
			OrderByComparator<LearningActivity> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityException {

		return getPersistence().findByModuleIdNotTypeId_PrevAndNext(
			actId, moduleId, typeId, orderByComparator);
	}

	/**
	 * Removes all the learning activities where moduleId = &#63; and typeId &ne; &#63; from the database.
	 *
	 * @param moduleId the module ID
	 * @param typeId the type ID
	 */
	public static void removeByModuleIdNotTypeId(long moduleId, long typeId) {
		getPersistence().removeByModuleIdNotTypeId(moduleId, typeId);
	}

	/**
	 * Returns the number of learning activities where moduleId = &#63; and typeId &ne; &#63;.
	 *
	 * @param moduleId the module ID
	 * @param typeId the type ID
	 * @return the number of matching learning activities
	 */
	public static int countByModuleIdNotTypeId(long moduleId, long typeId) {
		return getPersistence().countByModuleIdNotTypeId(moduleId, typeId);
	}

	/**
	 * Returns all the learning activities where groupId = &#63; and moduleId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @return the matching learning activities
	 */
	public static List<LearningActivity> findByGroupIdModuleId(
		long groupId, long moduleId) {

		return getPersistence().findByGroupIdModuleId(groupId, moduleId);
	}

	/**
	 * Returns a range of all the learning activities where groupId = &#63; and moduleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @return the range of matching learning activities
	 */
	public static List<LearningActivity> findByGroupIdModuleId(
		long groupId, long moduleId, int start, int end) {

		return getPersistence().findByGroupIdModuleId(
			groupId, moduleId, start, end);
	}

	/**
	 * Returns an ordered range of all the learning activities where groupId = &#63; and moduleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching learning activities
	 */
	public static List<LearningActivity> findByGroupIdModuleId(
		long groupId, long moduleId, int start, int end,
		OrderByComparator<LearningActivity> orderByComparator) {

		return getPersistence().findByGroupIdModuleId(
			groupId, moduleId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the learning activities where groupId = &#63; and moduleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching learning activities
	 */
	public static List<LearningActivity> findByGroupIdModuleId(
		long groupId, long moduleId, int start, int end,
		OrderByComparator<LearningActivity> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByGroupIdModuleId(
			groupId, moduleId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first learning activity in the ordered set where groupId = &#63; and moduleId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity
	 * @throws NoSuchLearningActivityException if a matching learning activity could not be found
	 */
	public static LearningActivity findByGroupIdModuleId_First(
			long groupId, long moduleId,
			OrderByComparator<LearningActivity> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityException {

		return getPersistence().findByGroupIdModuleId_First(
			groupId, moduleId, orderByComparator);
	}

	/**
	 * Returns the first learning activity in the ordered set where groupId = &#63; and moduleId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity, or <code>null</code> if a matching learning activity could not be found
	 */
	public static LearningActivity fetchByGroupIdModuleId_First(
		long groupId, long moduleId,
		OrderByComparator<LearningActivity> orderByComparator) {

		return getPersistence().fetchByGroupIdModuleId_First(
			groupId, moduleId, orderByComparator);
	}

	/**
	 * Returns the last learning activity in the ordered set where groupId = &#63; and moduleId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity
	 * @throws NoSuchLearningActivityException if a matching learning activity could not be found
	 */
	public static LearningActivity findByGroupIdModuleId_Last(
			long groupId, long moduleId,
			OrderByComparator<LearningActivity> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityException {

		return getPersistence().findByGroupIdModuleId_Last(
			groupId, moduleId, orderByComparator);
	}

	/**
	 * Returns the last learning activity in the ordered set where groupId = &#63; and moduleId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity, or <code>null</code> if a matching learning activity could not be found
	 */
	public static LearningActivity fetchByGroupIdModuleId_Last(
		long groupId, long moduleId,
		OrderByComparator<LearningActivity> orderByComparator) {

		return getPersistence().fetchByGroupIdModuleId_Last(
			groupId, moduleId, orderByComparator);
	}

	/**
	 * Returns the learning activities before and after the current learning activity in the ordered set where groupId = &#63; and moduleId = &#63;.
	 *
	 * @param actId the primary key of the current learning activity
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next learning activity
	 * @throws NoSuchLearningActivityException if a learning activity with the primary key could not be found
	 */
	public static LearningActivity[] findByGroupIdModuleId_PrevAndNext(
			long actId, long groupId, long moduleId,
			OrderByComparator<LearningActivity> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityException {

		return getPersistence().findByGroupIdModuleId_PrevAndNext(
			actId, groupId, moduleId, orderByComparator);
	}

	/**
	 * Returns all the learning activities that the user has permission to view where groupId = &#63; and moduleId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @return the matching learning activities that the user has permission to view
	 */
	public static List<LearningActivity> filterFindByGroupIdModuleId(
		long groupId, long moduleId) {

		return getPersistence().filterFindByGroupIdModuleId(groupId, moduleId);
	}

	/**
	 * Returns a range of all the learning activities that the user has permission to view where groupId = &#63; and moduleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @return the range of matching learning activities that the user has permission to view
	 */
	public static List<LearningActivity> filterFindByGroupIdModuleId(
		long groupId, long moduleId, int start, int end) {

		return getPersistence().filterFindByGroupIdModuleId(
			groupId, moduleId, start, end);
	}

	/**
	 * Returns an ordered range of all the learning activities that the user has permissions to view where groupId = &#63; and moduleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching learning activities that the user has permission to view
	 */
	public static List<LearningActivity> filterFindByGroupIdModuleId(
		long groupId, long moduleId, int start, int end,
		OrderByComparator<LearningActivity> orderByComparator) {

		return getPersistence().filterFindByGroupIdModuleId(
			groupId, moduleId, start, end, orderByComparator);
	}

	/**
	 * Returns the learning activities before and after the current learning activity in the ordered set of learning activities that the user has permission to view where groupId = &#63; and moduleId = &#63;.
	 *
	 * @param actId the primary key of the current learning activity
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next learning activity
	 * @throws NoSuchLearningActivityException if a learning activity with the primary key could not be found
	 */
	public static LearningActivity[] filterFindByGroupIdModuleId_PrevAndNext(
			long actId, long groupId, long moduleId,
			OrderByComparator<LearningActivity> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityException {

		return getPersistence().filterFindByGroupIdModuleId_PrevAndNext(
			actId, groupId, moduleId, orderByComparator);
	}

	/**
	 * Removes all the learning activities where groupId = &#63; and moduleId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 */
	public static void removeByGroupIdModuleId(long groupId, long moduleId) {
		getPersistence().removeByGroupIdModuleId(groupId, moduleId);
	}

	/**
	 * Returns the number of learning activities where groupId = &#63; and moduleId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @return the number of matching learning activities
	 */
	public static int countByGroupIdModuleId(long groupId, long moduleId) {
		return getPersistence().countByGroupIdModuleId(groupId, moduleId);
	}

	/**
	 * Returns the number of learning activities that the user has permission to view where groupId = &#63; and moduleId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @return the number of matching learning activities that the user has permission to view
	 */
	public static int filterCountByGroupIdModuleId(
		long groupId, long moduleId) {

		return getPersistence().filterCountByGroupIdModuleId(groupId, moduleId);
	}

	/**
	 * Caches the learning activity in the entity cache if it is enabled.
	 *
	 * @param learningActivity the learning activity
	 */
	public static void cacheResult(LearningActivity learningActivity) {
		getPersistence().cacheResult(learningActivity);
	}

	/**
	 * Caches the learning activities in the entity cache if it is enabled.
	 *
	 * @param learningActivities the learning activities
	 */
	public static void cacheResult(List<LearningActivity> learningActivities) {
		getPersistence().cacheResult(learningActivities);
	}

	/**
	 * Creates a new learning activity with the primary key. Does not add the learning activity to the database.
	 *
	 * @param actId the primary key for the new learning activity
	 * @return the new learning activity
	 */
	public static LearningActivity create(long actId) {
		return getPersistence().create(actId);
	}

	/**
	 * Removes the learning activity with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param actId the primary key of the learning activity
	 * @return the learning activity that was removed
	 * @throws NoSuchLearningActivityException if a learning activity with the primary key could not be found
	 */
	public static LearningActivity remove(long actId)
		throws com.ted.lms.exception.NoSuchLearningActivityException {

		return getPersistence().remove(actId);
	}

	public static LearningActivity updateImpl(
		LearningActivity learningActivity) {

		return getPersistence().updateImpl(learningActivity);
	}

	/**
	 * Returns the learning activity with the primary key or throws a <code>NoSuchLearningActivityException</code> if it could not be found.
	 *
	 * @param actId the primary key of the learning activity
	 * @return the learning activity
	 * @throws NoSuchLearningActivityException if a learning activity with the primary key could not be found
	 */
	public static LearningActivity findByPrimaryKey(long actId)
		throws com.ted.lms.exception.NoSuchLearningActivityException {

		return getPersistence().findByPrimaryKey(actId);
	}

	/**
	 * Returns the learning activity with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param actId the primary key of the learning activity
	 * @return the learning activity, or <code>null</code> if a learning activity with the primary key could not be found
	 */
	public static LearningActivity fetchByPrimaryKey(long actId) {
		return getPersistence().fetchByPrimaryKey(actId);
	}

	/**
	 * Returns all the learning activities.
	 *
	 * @return the learning activities
	 */
	public static List<LearningActivity> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the learning activities.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @return the range of learning activities
	 */
	public static List<LearningActivity> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the learning activities.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of learning activities
	 */
	public static List<LearningActivity> findAll(
		int start, int end,
		OrderByComparator<LearningActivity> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the learning activities.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of learning activities
	 */
	public static List<LearningActivity> findAll(
		int start, int end,
		OrderByComparator<LearningActivity> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the learning activities from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of learning activities.
	 *
	 * @return the number of learning activities
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static LearningActivityPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<LearningActivityPersistence, LearningActivityPersistence>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			LearningActivityPersistence.class);

		ServiceTracker<LearningActivityPersistence, LearningActivityPersistence>
			serviceTracker =
				new ServiceTracker
					<LearningActivityPersistence, LearningActivityPersistence>(
						bundle.getBundleContext(),
						LearningActivityPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}