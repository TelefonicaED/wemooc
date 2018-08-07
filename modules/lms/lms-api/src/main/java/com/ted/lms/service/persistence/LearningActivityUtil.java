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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.ted.lms.model.LearningActivity;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the learning activity service. This utility wraps {@link com.ted.lms.service.persistence.impl.LearningActivityPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LearningActivityPersistence
 * @see com.ted.lms.service.persistence.impl.LearningActivityPersistenceImpl
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
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
	public static LearningActivity update(LearningActivity learningActivity,
		ServiceContext serviceContext) {
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @return the range of matching learning activities
	*/
	public static List<LearningActivity> findByUuid(String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the learning activities where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching learning activities
	*/
	public static List<LearningActivity> findByUuid(String uuid, int start,
		int end, OrderByComparator<LearningActivity> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the learning activities where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching learning activities
	*/
	public static List<LearningActivity> findByUuid(String uuid, int start,
		int end, OrderByComparator<LearningActivity> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first learning activity in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching learning activity
	* @throws NoSuchLearningActivityException if a matching learning activity could not be found
	*/
	public static LearningActivity findByUuid_First(String uuid,
		OrderByComparator<LearningActivity> orderByComparator)
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
	public static LearningActivity fetchByUuid_First(String uuid,
		OrderByComparator<LearningActivity> orderByComparator) {
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
	public static LearningActivity findByUuid_Last(String uuid,
		OrderByComparator<LearningActivity> orderByComparator)
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
	public static LearningActivity fetchByUuid_Last(String uuid,
		OrderByComparator<LearningActivity> orderByComparator) {
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
	public static LearningActivity[] findByUuid_PrevAndNext(long actId,
		String uuid, OrderByComparator<LearningActivity> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityException {
		return getPersistence()
				   .findByUuid_PrevAndNext(actId, uuid, orderByComparator);
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
	* Returns the learning activity where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchLearningActivityException} if it could not be found.
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
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching learning activity, or <code>null</code> if a matching learning activity could not be found
	*/
	public static LearningActivity fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
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
	public static List<LearningActivity> findByUuid_C(String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the learning activities where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @return the range of matching learning activities
	*/
	public static List<LearningActivity> findByUuid_C(String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the learning activities where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching learning activities
	*/
	public static List<LearningActivity> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<LearningActivity> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the learning activities where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching learning activities
	*/
	public static List<LearningActivity> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<LearningActivity> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
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
	public static LearningActivity findByUuid_C_First(String uuid,
		long companyId, OrderByComparator<LearningActivity> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first learning activity in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching learning activity, or <code>null</code> if a matching learning activity could not be found
	*/
	public static LearningActivity fetchByUuid_C_First(String uuid,
		long companyId, OrderByComparator<LearningActivity> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
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
	public static LearningActivity findByUuid_C_Last(String uuid,
		long companyId, OrderByComparator<LearningActivity> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last learning activity in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching learning activity, or <code>null</code> if a matching learning activity could not be found
	*/
	public static LearningActivity fetchByUuid_C_Last(String uuid,
		long companyId, OrderByComparator<LearningActivity> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
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
	public static LearningActivity[] findByUuid_C_PrevAndNext(long actId,
		String uuid, long companyId,
		OrderByComparator<LearningActivity> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(actId, uuid, companyId,
			orderByComparator);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @return the range of matching learning activities
	*/
	public static List<LearningActivity> findByCompanyId(long companyId,
		int start, int end) {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

	/**
	* Returns an ordered range of all the learning activities where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching learning activities
	*/
	public static List<LearningActivity> findByCompanyId(long companyId,
		int start, int end,
		OrderByComparator<LearningActivity> orderByComparator) {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the learning activities where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching learning activities
	*/
	public static List<LearningActivity> findByCompanyId(long companyId,
		int start, int end,
		OrderByComparator<LearningActivity> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first learning activity in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching learning activity
	* @throws NoSuchLearningActivityException if a matching learning activity could not be found
	*/
	public static LearningActivity findByCompanyId_First(long companyId,
		OrderByComparator<LearningActivity> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityException {
		return getPersistence()
				   .findByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the first learning activity in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching learning activity, or <code>null</code> if a matching learning activity could not be found
	*/
	public static LearningActivity fetchByCompanyId_First(long companyId,
		OrderByComparator<LearningActivity> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the last learning activity in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching learning activity
	* @throws NoSuchLearningActivityException if a matching learning activity could not be found
	*/
	public static LearningActivity findByCompanyId_Last(long companyId,
		OrderByComparator<LearningActivity> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityException {
		return getPersistence()
				   .findByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the last learning activity in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching learning activity, or <code>null</code> if a matching learning activity could not be found
	*/
	public static LearningActivity fetchByCompanyId_Last(long companyId,
		OrderByComparator<LearningActivity> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyId_Last(companyId, orderByComparator);
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
	public static LearningActivity[] findByCompanyId_PrevAndNext(long actId,
		long companyId, OrderByComparator<LearningActivity> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(actId, companyId,
			orderByComparator);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @return the range of matching learning activities
	*/
	public static List<LearningActivity> findByGroupId(long groupId, int start,
		int end) {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the learning activities where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching learning activities
	*/
	public static List<LearningActivity> findByGroupId(long groupId, int start,
		int end, OrderByComparator<LearningActivity> orderByComparator) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the learning activities where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching learning activities
	*/
	public static List<LearningActivity> findByGroupId(long groupId, int start,
		int end, OrderByComparator<LearningActivity> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first learning activity in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching learning activity
	* @throws NoSuchLearningActivityException if a matching learning activity could not be found
	*/
	public static LearningActivity findByGroupId_First(long groupId,
		OrderByComparator<LearningActivity> orderByComparator)
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
	public static LearningActivity fetchByGroupId_First(long groupId,
		OrderByComparator<LearningActivity> orderByComparator) {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last learning activity in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching learning activity
	* @throws NoSuchLearningActivityException if a matching learning activity could not be found
	*/
	public static LearningActivity findByGroupId_Last(long groupId,
		OrderByComparator<LearningActivity> orderByComparator)
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
	public static LearningActivity fetchByGroupId_Last(long groupId,
		OrderByComparator<LearningActivity> orderByComparator) {
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
	public static LearningActivity[] findByGroupId_PrevAndNext(long actId,
		long groupId, OrderByComparator<LearningActivity> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(actId, groupId, orderByComparator);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @return the range of matching learning activities that the user has permission to view
	*/
	public static List<LearningActivity> filterFindByGroupId(long groupId,
		int start, int end) {
		return getPersistence().filterFindByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the learning activities that the user has permissions to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching learning activities that the user has permission to view
	*/
	public static List<LearningActivity> filterFindByGroupId(long groupId,
		int start, int end,
		OrderByComparator<LearningActivity> orderByComparator) {
		return getPersistence()
				   .filterFindByGroupId(groupId, start, end, orderByComparator);
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
		return getPersistence()
				   .filterFindByGroupId_PrevAndNext(actId, groupId,
			orderByComparator);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param moduleId the module ID
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @return the range of matching learning activities
	*/
	public static List<LearningActivity> findByModuleId(long moduleId,
		int start, int end) {
		return getPersistence().findByModuleId(moduleId, start, end);
	}

	/**
	* Returns an ordered range of all the learning activities where moduleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param moduleId the module ID
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching learning activities
	*/
	public static List<LearningActivity> findByModuleId(long moduleId,
		int start, int end,
		OrderByComparator<LearningActivity> orderByComparator) {
		return getPersistence()
				   .findByModuleId(moduleId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the learning activities where moduleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param moduleId the module ID
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching learning activities
	*/
	public static List<LearningActivity> findByModuleId(long moduleId,
		int start, int end,
		OrderByComparator<LearningActivity> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByModuleId(moduleId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first learning activity in the ordered set where moduleId = &#63;.
	*
	* @param moduleId the module ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching learning activity
	* @throws NoSuchLearningActivityException if a matching learning activity could not be found
	*/
	public static LearningActivity findByModuleId_First(long moduleId,
		OrderByComparator<LearningActivity> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityException {
		return getPersistence().findByModuleId_First(moduleId, orderByComparator);
	}

	/**
	* Returns the first learning activity in the ordered set where moduleId = &#63;.
	*
	* @param moduleId the module ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching learning activity, or <code>null</code> if a matching learning activity could not be found
	*/
	public static LearningActivity fetchByModuleId_First(long moduleId,
		OrderByComparator<LearningActivity> orderByComparator) {
		return getPersistence()
				   .fetchByModuleId_First(moduleId, orderByComparator);
	}

	/**
	* Returns the last learning activity in the ordered set where moduleId = &#63;.
	*
	* @param moduleId the module ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching learning activity
	* @throws NoSuchLearningActivityException if a matching learning activity could not be found
	*/
	public static LearningActivity findByModuleId_Last(long moduleId,
		OrderByComparator<LearningActivity> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityException {
		return getPersistence().findByModuleId_Last(moduleId, orderByComparator);
	}

	/**
	* Returns the last learning activity in the ordered set where moduleId = &#63;.
	*
	* @param moduleId the module ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching learning activity, or <code>null</code> if a matching learning activity could not be found
	*/
	public static LearningActivity fetchByModuleId_Last(long moduleId,
		OrderByComparator<LearningActivity> orderByComparator) {
		return getPersistence().fetchByModuleId_Last(moduleId, orderByComparator);
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
	public static LearningActivity[] findByModuleId_PrevAndNext(long actId,
		long moduleId, OrderByComparator<LearningActivity> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityException {
		return getPersistence()
				   .findByModuleId_PrevAndNext(actId, moduleId,
			orderByComparator);
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
	public static List<LearningActivity> findByModuleIdRequired(long moduleId,
		boolean required) {
		return getPersistence().findByModuleIdRequired(moduleId, required);
	}

	/**
	* Returns a range of all the learning activities where moduleId = &#63; and required = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param moduleId the module ID
	* @param required the required
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @return the range of matching learning activities
	*/
	public static List<LearningActivity> findByModuleIdRequired(long moduleId,
		boolean required, int start, int end) {
		return getPersistence()
				   .findByModuleIdRequired(moduleId, required, start, end);
	}

	/**
	* Returns an ordered range of all the learning activities where moduleId = &#63; and required = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param moduleId the module ID
	* @param required the required
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching learning activities
	*/
	public static List<LearningActivity> findByModuleIdRequired(long moduleId,
		boolean required, int start, int end,
		OrderByComparator<LearningActivity> orderByComparator) {
		return getPersistence()
				   .findByModuleIdRequired(moduleId, required, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the learning activities where moduleId = &#63; and required = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param moduleId the module ID
	* @param required the required
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching learning activities
	*/
	public static List<LearningActivity> findByModuleIdRequired(long moduleId,
		boolean required, int start, int end,
		OrderByComparator<LearningActivity> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByModuleIdRequired(moduleId, required, start, end,
			orderByComparator, retrieveFromCache);
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
	public static LearningActivity findByModuleIdRequired_First(long moduleId,
		boolean required, OrderByComparator<LearningActivity> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityException {
		return getPersistence()
				   .findByModuleIdRequired_First(moduleId, required,
			orderByComparator);
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
		return getPersistence()
				   .fetchByModuleIdRequired_First(moduleId, required,
			orderByComparator);
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
	public static LearningActivity findByModuleIdRequired_Last(long moduleId,
		boolean required, OrderByComparator<LearningActivity> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityException {
		return getPersistence()
				   .findByModuleIdRequired_Last(moduleId, required,
			orderByComparator);
	}

	/**
	* Returns the last learning activity in the ordered set where moduleId = &#63; and required = &#63;.
	*
	* @param moduleId the module ID
	* @param required the required
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching learning activity, or <code>null</code> if a matching learning activity could not be found
	*/
	public static LearningActivity fetchByModuleIdRequired_Last(long moduleId,
		boolean required, OrderByComparator<LearningActivity> orderByComparator) {
		return getPersistence()
				   .fetchByModuleIdRequired_Last(moduleId, required,
			orderByComparator);
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
		return getPersistence()
				   .findByModuleIdRequired_PrevAndNext(actId, moduleId,
			required, orderByComparator);
	}

	/**
	* Removes all the learning activities where moduleId = &#63; and required = &#63; from the database.
	*
	* @param moduleId the module ID
	* @param required the required
	*/
	public static void removeByModuleIdRequired(long moduleId, boolean required) {
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
	public static List<LearningActivity> findByGroupIdRequired(long groupId,
		boolean required) {
		return getPersistence().findByGroupIdRequired(groupId, required);
	}

	/**
	* Returns a range of all the learning activities where groupId = &#63; and required = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param required the required
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @return the range of matching learning activities
	*/
	public static List<LearningActivity> findByGroupIdRequired(long groupId,
		boolean required, int start, int end) {
		return getPersistence()
				   .findByGroupIdRequired(groupId, required, start, end);
	}

	/**
	* Returns an ordered range of all the learning activities where groupId = &#63; and required = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param required the required
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching learning activities
	*/
	public static List<LearningActivity> findByGroupIdRequired(long groupId,
		boolean required, int start, int end,
		OrderByComparator<LearningActivity> orderByComparator) {
		return getPersistence()
				   .findByGroupIdRequired(groupId, required, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the learning activities where groupId = &#63; and required = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param required the required
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching learning activities
	*/
	public static List<LearningActivity> findByGroupIdRequired(long groupId,
		boolean required, int start, int end,
		OrderByComparator<LearningActivity> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGroupIdRequired(groupId, required, start, end,
			orderByComparator, retrieveFromCache);
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
	public static LearningActivity findByGroupIdRequired_First(long groupId,
		boolean required, OrderByComparator<LearningActivity> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityException {
		return getPersistence()
				   .findByGroupIdRequired_First(groupId, required,
			orderByComparator);
	}

	/**
	* Returns the first learning activity in the ordered set where groupId = &#63; and required = &#63;.
	*
	* @param groupId the group ID
	* @param required the required
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching learning activity, or <code>null</code> if a matching learning activity could not be found
	*/
	public static LearningActivity fetchByGroupIdRequired_First(long groupId,
		boolean required, OrderByComparator<LearningActivity> orderByComparator) {
		return getPersistence()
				   .fetchByGroupIdRequired_First(groupId, required,
			orderByComparator);
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
	public static LearningActivity findByGroupIdRequired_Last(long groupId,
		boolean required, OrderByComparator<LearningActivity> orderByComparator)
		throws com.ted.lms.exception.NoSuchLearningActivityException {
		return getPersistence()
				   .findByGroupIdRequired_Last(groupId, required,
			orderByComparator);
	}

	/**
	* Returns the last learning activity in the ordered set where groupId = &#63; and required = &#63;.
	*
	* @param groupId the group ID
	* @param required the required
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching learning activity, or <code>null</code> if a matching learning activity could not be found
	*/
	public static LearningActivity fetchByGroupIdRequired_Last(long groupId,
		boolean required, OrderByComparator<LearningActivity> orderByComparator) {
		return getPersistence()
				   .fetchByGroupIdRequired_Last(groupId, required,
			orderByComparator);
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
		return getPersistence()
				   .findByGroupIdRequired_PrevAndNext(actId, groupId, required,
			orderByComparator);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return getPersistence()
				   .filterFindByGroupIdRequired(groupId, required, start, end);
	}

	/**
	* Returns an ordered range of all the learning activities that the user has permissions to view where groupId = &#63; and required = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return getPersistence()
				   .filterFindByGroupIdRequired(groupId, required, start, end,
			orderByComparator);
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
		return getPersistence()
				   .filterFindByGroupIdRequired_PrevAndNext(actId, groupId,
			required, orderByComparator);
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
	public static int filterCountByGroupIdRequired(long groupId,
		boolean required) {
		return getPersistence().filterCountByGroupIdRequired(groupId, required);
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

	public static LearningActivity updateImpl(LearningActivity learningActivity) {
		return getPersistence().updateImpl(learningActivity);
	}

	/**
	* Returns the learning activity with the primary key or throws a {@link NoSuchLearningActivityException} if it could not be found.
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

	public static java.util.Map<java.io.Serializable, LearningActivity> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of learning activities
	*/
	public static List<LearningActivity> findAll(int start, int end,
		OrderByComparator<LearningActivity> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the learning activities.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of learning activities
	*/
	public static List<LearningActivity> findAll(int start, int end,
		OrderByComparator<LearningActivity> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
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

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static LearningActivityPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LearningActivityPersistence, LearningActivityPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(LearningActivityPersistence.class);

		ServiceTracker<LearningActivityPersistence, LearningActivityPersistence> serviceTracker =
			new ServiceTracker<LearningActivityPersistence, LearningActivityPersistence>(bundle.getBundleContext(),
				LearningActivityPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}