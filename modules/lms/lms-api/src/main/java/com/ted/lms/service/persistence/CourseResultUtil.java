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

import com.ted.lms.model.CourseResult;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.annotation.versioning.ProviderType;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the course result service. This utility wraps <code>com.ted.lms.service.persistence.impl.CourseResultPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CourseResultPersistence
 * @generated
 */
@ProviderType
public class CourseResultUtil {

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
	public static void clearCache(CourseResult courseResult) {
		getPersistence().clearCache(courseResult);
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
	public static Map<Serializable, CourseResult> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<CourseResult> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CourseResult> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CourseResult> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CourseResult> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CourseResult update(CourseResult courseResult) {
		return getPersistence().update(courseResult);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CourseResult update(
		CourseResult courseResult, ServiceContext serviceContext) {

		return getPersistence().update(courseResult, serviceContext);
	}

	/**
	 * Returns all the course results where courseId = &#63;.
	 *
	 * @param courseId the course ID
	 * @return the matching course results
	 */
	public static List<CourseResult> findByCourseId(long courseId) {
		return getPersistence().findByCourseId(courseId);
	}

	/**
	 * Returns a range of all the course results where courseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CourseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param courseId the course ID
	 * @param start the lower bound of the range of course results
	 * @param end the upper bound of the range of course results (not inclusive)
	 * @return the range of matching course results
	 */
	public static List<CourseResult> findByCourseId(
		long courseId, int start, int end) {

		return getPersistence().findByCourseId(courseId, start, end);
	}

	/**
	 * Returns an ordered range of all the course results where courseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CourseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param courseId the course ID
	 * @param start the lower bound of the range of course results
	 * @param end the upper bound of the range of course results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching course results
	 */
	public static List<CourseResult> findByCourseId(
		long courseId, int start, int end,
		OrderByComparator<CourseResult> orderByComparator) {

		return getPersistence().findByCourseId(
			courseId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the course results where courseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CourseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param courseId the course ID
	 * @param start the lower bound of the range of course results
	 * @param end the upper bound of the range of course results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching course results
	 */
	public static List<CourseResult> findByCourseId(
		long courseId, int start, int end,
		OrderByComparator<CourseResult> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByCourseId(
			courseId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first course result in the ordered set where courseId = &#63;.
	 *
	 * @param courseId the course ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching course result
	 * @throws NoSuchCourseResultException if a matching course result could not be found
	 */
	public static CourseResult findByCourseId_First(
			long courseId, OrderByComparator<CourseResult> orderByComparator)
		throws com.ted.lms.exception.NoSuchCourseResultException {

		return getPersistence().findByCourseId_First(
			courseId, orderByComparator);
	}

	/**
	 * Returns the first course result in the ordered set where courseId = &#63;.
	 *
	 * @param courseId the course ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching course result, or <code>null</code> if a matching course result could not be found
	 */
	public static CourseResult fetchByCourseId_First(
		long courseId, OrderByComparator<CourseResult> orderByComparator) {

		return getPersistence().fetchByCourseId_First(
			courseId, orderByComparator);
	}

	/**
	 * Returns the last course result in the ordered set where courseId = &#63;.
	 *
	 * @param courseId the course ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching course result
	 * @throws NoSuchCourseResultException if a matching course result could not be found
	 */
	public static CourseResult findByCourseId_Last(
			long courseId, OrderByComparator<CourseResult> orderByComparator)
		throws com.ted.lms.exception.NoSuchCourseResultException {

		return getPersistence().findByCourseId_Last(
			courseId, orderByComparator);
	}

	/**
	 * Returns the last course result in the ordered set where courseId = &#63;.
	 *
	 * @param courseId the course ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching course result, or <code>null</code> if a matching course result could not be found
	 */
	public static CourseResult fetchByCourseId_Last(
		long courseId, OrderByComparator<CourseResult> orderByComparator) {

		return getPersistence().fetchByCourseId_Last(
			courseId, orderByComparator);
	}

	/**
	 * Returns the course results before and after the current course result in the ordered set where courseId = &#63;.
	 *
	 * @param crId the primary key of the current course result
	 * @param courseId the course ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next course result
	 * @throws NoSuchCourseResultException if a course result with the primary key could not be found
	 */
	public static CourseResult[] findByCourseId_PrevAndNext(
			long crId, long courseId,
			OrderByComparator<CourseResult> orderByComparator)
		throws com.ted.lms.exception.NoSuchCourseResultException {

		return getPersistence().findByCourseId_PrevAndNext(
			crId, courseId, orderByComparator);
	}

	/**
	 * Removes all the course results where courseId = &#63; from the database.
	 *
	 * @param courseId the course ID
	 */
	public static void removeByCourseId(long courseId) {
		getPersistence().removeByCourseId(courseId);
	}

	/**
	 * Returns the number of course results where courseId = &#63;.
	 *
	 * @param courseId the course ID
	 * @return the number of matching course results
	 */
	public static int countByCourseId(long courseId) {
		return getPersistence().countByCourseId(courseId);
	}

	/**
	 * Returns all the course results where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching course results
	 */
	public static List<CourseResult> findByUserId(long userId) {
		return getPersistence().findByUserId(userId);
	}

	/**
	 * Returns a range of all the course results where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CourseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of course results
	 * @param end the upper bound of the range of course results (not inclusive)
	 * @return the range of matching course results
	 */
	public static List<CourseResult> findByUserId(
		long userId, int start, int end) {

		return getPersistence().findByUserId(userId, start, end);
	}

	/**
	 * Returns an ordered range of all the course results where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CourseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of course results
	 * @param end the upper bound of the range of course results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching course results
	 */
	public static List<CourseResult> findByUserId(
		long userId, int start, int end,
		OrderByComparator<CourseResult> orderByComparator) {

		return getPersistence().findByUserId(
			userId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the course results where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CourseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of course results
	 * @param end the upper bound of the range of course results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching course results
	 */
	public static List<CourseResult> findByUserId(
		long userId, int start, int end,
		OrderByComparator<CourseResult> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUserId(
			userId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first course result in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching course result
	 * @throws NoSuchCourseResultException if a matching course result could not be found
	 */
	public static CourseResult findByUserId_First(
			long userId, OrderByComparator<CourseResult> orderByComparator)
		throws com.ted.lms.exception.NoSuchCourseResultException {

		return getPersistence().findByUserId_First(userId, orderByComparator);
	}

	/**
	 * Returns the first course result in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching course result, or <code>null</code> if a matching course result could not be found
	 */
	public static CourseResult fetchByUserId_First(
		long userId, OrderByComparator<CourseResult> orderByComparator) {

		return getPersistence().fetchByUserId_First(userId, orderByComparator);
	}

	/**
	 * Returns the last course result in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching course result
	 * @throws NoSuchCourseResultException if a matching course result could not be found
	 */
	public static CourseResult findByUserId_Last(
			long userId, OrderByComparator<CourseResult> orderByComparator)
		throws com.ted.lms.exception.NoSuchCourseResultException {

		return getPersistence().findByUserId_Last(userId, orderByComparator);
	}

	/**
	 * Returns the last course result in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching course result, or <code>null</code> if a matching course result could not be found
	 */
	public static CourseResult fetchByUserId_Last(
		long userId, OrderByComparator<CourseResult> orderByComparator) {

		return getPersistence().fetchByUserId_Last(userId, orderByComparator);
	}

	/**
	 * Returns the course results before and after the current course result in the ordered set where userId = &#63;.
	 *
	 * @param crId the primary key of the current course result
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next course result
	 * @throws NoSuchCourseResultException if a course result with the primary key could not be found
	 */
	public static CourseResult[] findByUserId_PrevAndNext(
			long crId, long userId,
			OrderByComparator<CourseResult> orderByComparator)
		throws com.ted.lms.exception.NoSuchCourseResultException {

		return getPersistence().findByUserId_PrevAndNext(
			crId, userId, orderByComparator);
	}

	/**
	 * Removes all the course results where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	public static void removeByUserId(long userId) {
		getPersistence().removeByUserId(userId);
	}

	/**
	 * Returns the number of course results where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching course results
	 */
	public static int countByUserId(long userId) {
		return getPersistence().countByUserId(userId);
	}

	/**
	 * Returns the course result where courseId = &#63; and userId = &#63; or throws a <code>NoSuchCourseResultException</code> if it could not be found.
	 *
	 * @param courseId the course ID
	 * @param userId the user ID
	 * @return the matching course result
	 * @throws NoSuchCourseResultException if a matching course result could not be found
	 */
	public static CourseResult findByCourseIdUserId(long courseId, long userId)
		throws com.ted.lms.exception.NoSuchCourseResultException {

		return getPersistence().findByCourseIdUserId(courseId, userId);
	}

	/**
	 * Returns the course result where courseId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param courseId the course ID
	 * @param userId the user ID
	 * @return the matching course result, or <code>null</code> if a matching course result could not be found
	 */
	public static CourseResult fetchByCourseIdUserId(
		long courseId, long userId) {

		return getPersistence().fetchByCourseIdUserId(courseId, userId);
	}

	/**
	 * Returns the course result where courseId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param courseId the course ID
	 * @param userId the user ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching course result, or <code>null</code> if a matching course result could not be found
	 */
	public static CourseResult fetchByCourseIdUserId(
		long courseId, long userId, boolean useFinderCache) {

		return getPersistence().fetchByCourseIdUserId(
			courseId, userId, useFinderCache);
	}

	/**
	 * Removes the course result where courseId = &#63; and userId = &#63; from the database.
	 *
	 * @param courseId the course ID
	 * @param userId the user ID
	 * @return the course result that was removed
	 */
	public static CourseResult removeByCourseIdUserId(
			long courseId, long userId)
		throws com.ted.lms.exception.NoSuchCourseResultException {

		return getPersistence().removeByCourseIdUserId(courseId, userId);
	}

	/**
	 * Returns the number of course results where courseId = &#63; and userId = &#63;.
	 *
	 * @param courseId the course ID
	 * @param userId the user ID
	 * @return the number of matching course results
	 */
	public static int countByCourseIdUserId(long courseId, long userId) {
		return getPersistence().countByCourseIdUserId(courseId, userId);
	}

	/**
	 * Returns all the course results where courseId = &#63; and passed = &#63;.
	 *
	 * @param courseId the course ID
	 * @param passed the passed
	 * @return the matching course results
	 */
	public static List<CourseResult> findByCourseIdPassed(
		long courseId, boolean passed) {

		return getPersistence().findByCourseIdPassed(courseId, passed);
	}

	/**
	 * Returns a range of all the course results where courseId = &#63; and passed = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CourseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param courseId the course ID
	 * @param passed the passed
	 * @param start the lower bound of the range of course results
	 * @param end the upper bound of the range of course results (not inclusive)
	 * @return the range of matching course results
	 */
	public static List<CourseResult> findByCourseIdPassed(
		long courseId, boolean passed, int start, int end) {

		return getPersistence().findByCourseIdPassed(
			courseId, passed, start, end);
	}

	/**
	 * Returns an ordered range of all the course results where courseId = &#63; and passed = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CourseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param courseId the course ID
	 * @param passed the passed
	 * @param start the lower bound of the range of course results
	 * @param end the upper bound of the range of course results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching course results
	 */
	public static List<CourseResult> findByCourseIdPassed(
		long courseId, boolean passed, int start, int end,
		OrderByComparator<CourseResult> orderByComparator) {

		return getPersistence().findByCourseIdPassed(
			courseId, passed, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the course results where courseId = &#63; and passed = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CourseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param courseId the course ID
	 * @param passed the passed
	 * @param start the lower bound of the range of course results
	 * @param end the upper bound of the range of course results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching course results
	 */
	public static List<CourseResult> findByCourseIdPassed(
		long courseId, boolean passed, int start, int end,
		OrderByComparator<CourseResult> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByCourseIdPassed(
			courseId, passed, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first course result in the ordered set where courseId = &#63; and passed = &#63;.
	 *
	 * @param courseId the course ID
	 * @param passed the passed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching course result
	 * @throws NoSuchCourseResultException if a matching course result could not be found
	 */
	public static CourseResult findByCourseIdPassed_First(
			long courseId, boolean passed,
			OrderByComparator<CourseResult> orderByComparator)
		throws com.ted.lms.exception.NoSuchCourseResultException {

		return getPersistence().findByCourseIdPassed_First(
			courseId, passed, orderByComparator);
	}

	/**
	 * Returns the first course result in the ordered set where courseId = &#63; and passed = &#63;.
	 *
	 * @param courseId the course ID
	 * @param passed the passed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching course result, or <code>null</code> if a matching course result could not be found
	 */
	public static CourseResult fetchByCourseIdPassed_First(
		long courseId, boolean passed,
		OrderByComparator<CourseResult> orderByComparator) {

		return getPersistence().fetchByCourseIdPassed_First(
			courseId, passed, orderByComparator);
	}

	/**
	 * Returns the last course result in the ordered set where courseId = &#63; and passed = &#63;.
	 *
	 * @param courseId the course ID
	 * @param passed the passed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching course result
	 * @throws NoSuchCourseResultException if a matching course result could not be found
	 */
	public static CourseResult findByCourseIdPassed_Last(
			long courseId, boolean passed,
			OrderByComparator<CourseResult> orderByComparator)
		throws com.ted.lms.exception.NoSuchCourseResultException {

		return getPersistence().findByCourseIdPassed_Last(
			courseId, passed, orderByComparator);
	}

	/**
	 * Returns the last course result in the ordered set where courseId = &#63; and passed = &#63;.
	 *
	 * @param courseId the course ID
	 * @param passed the passed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching course result, or <code>null</code> if a matching course result could not be found
	 */
	public static CourseResult fetchByCourseIdPassed_Last(
		long courseId, boolean passed,
		OrderByComparator<CourseResult> orderByComparator) {

		return getPersistence().fetchByCourseIdPassed_Last(
			courseId, passed, orderByComparator);
	}

	/**
	 * Returns the course results before and after the current course result in the ordered set where courseId = &#63; and passed = &#63;.
	 *
	 * @param crId the primary key of the current course result
	 * @param courseId the course ID
	 * @param passed the passed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next course result
	 * @throws NoSuchCourseResultException if a course result with the primary key could not be found
	 */
	public static CourseResult[] findByCourseIdPassed_PrevAndNext(
			long crId, long courseId, boolean passed,
			OrderByComparator<CourseResult> orderByComparator)
		throws com.ted.lms.exception.NoSuchCourseResultException {

		return getPersistence().findByCourseIdPassed_PrevAndNext(
			crId, courseId, passed, orderByComparator);
	}

	/**
	 * Removes all the course results where courseId = &#63; and passed = &#63; from the database.
	 *
	 * @param courseId the course ID
	 * @param passed the passed
	 */
	public static void removeByCourseIdPassed(long courseId, boolean passed) {
		getPersistence().removeByCourseIdPassed(courseId, passed);
	}

	/**
	 * Returns the number of course results where courseId = &#63; and passed = &#63;.
	 *
	 * @param courseId the course ID
	 * @param passed the passed
	 * @return the number of matching course results
	 */
	public static int countByCourseIdPassed(long courseId, boolean passed) {
		return getPersistence().countByCourseIdPassed(courseId, passed);
	}

	/**
	 * Returns all the course results where courseId = &#63; and passed = &#63;.
	 *
	 * @param courseId the course ID
	 * @param passed the passed
	 * @return the matching course results
	 */
	public static List<CourseResult> findByFinished(
		long courseId, boolean passed) {

		return getPersistence().findByFinished(courseId, passed);
	}

	/**
	 * Returns a range of all the course results where courseId = &#63; and passed = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CourseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param courseId the course ID
	 * @param passed the passed
	 * @param start the lower bound of the range of course results
	 * @param end the upper bound of the range of course results (not inclusive)
	 * @return the range of matching course results
	 */
	public static List<CourseResult> findByFinished(
		long courseId, boolean passed, int start, int end) {

		return getPersistence().findByFinished(courseId, passed, start, end);
	}

	/**
	 * Returns an ordered range of all the course results where courseId = &#63; and passed = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CourseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param courseId the course ID
	 * @param passed the passed
	 * @param start the lower bound of the range of course results
	 * @param end the upper bound of the range of course results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching course results
	 */
	public static List<CourseResult> findByFinished(
		long courseId, boolean passed, int start, int end,
		OrderByComparator<CourseResult> orderByComparator) {

		return getPersistence().findByFinished(
			courseId, passed, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the course results where courseId = &#63; and passed = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CourseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param courseId the course ID
	 * @param passed the passed
	 * @param start the lower bound of the range of course results
	 * @param end the upper bound of the range of course results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching course results
	 */
	public static List<CourseResult> findByFinished(
		long courseId, boolean passed, int start, int end,
		OrderByComparator<CourseResult> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByFinished(
			courseId, passed, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first course result in the ordered set where courseId = &#63; and passed = &#63;.
	 *
	 * @param courseId the course ID
	 * @param passed the passed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching course result
	 * @throws NoSuchCourseResultException if a matching course result could not be found
	 */
	public static CourseResult findByFinished_First(
			long courseId, boolean passed,
			OrderByComparator<CourseResult> orderByComparator)
		throws com.ted.lms.exception.NoSuchCourseResultException {

		return getPersistence().findByFinished_First(
			courseId, passed, orderByComparator);
	}

	/**
	 * Returns the first course result in the ordered set where courseId = &#63; and passed = &#63;.
	 *
	 * @param courseId the course ID
	 * @param passed the passed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching course result, or <code>null</code> if a matching course result could not be found
	 */
	public static CourseResult fetchByFinished_First(
		long courseId, boolean passed,
		OrderByComparator<CourseResult> orderByComparator) {

		return getPersistence().fetchByFinished_First(
			courseId, passed, orderByComparator);
	}

	/**
	 * Returns the last course result in the ordered set where courseId = &#63; and passed = &#63;.
	 *
	 * @param courseId the course ID
	 * @param passed the passed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching course result
	 * @throws NoSuchCourseResultException if a matching course result could not be found
	 */
	public static CourseResult findByFinished_Last(
			long courseId, boolean passed,
			OrderByComparator<CourseResult> orderByComparator)
		throws com.ted.lms.exception.NoSuchCourseResultException {

		return getPersistence().findByFinished_Last(
			courseId, passed, orderByComparator);
	}

	/**
	 * Returns the last course result in the ordered set where courseId = &#63; and passed = &#63;.
	 *
	 * @param courseId the course ID
	 * @param passed the passed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching course result, or <code>null</code> if a matching course result could not be found
	 */
	public static CourseResult fetchByFinished_Last(
		long courseId, boolean passed,
		OrderByComparator<CourseResult> orderByComparator) {

		return getPersistence().fetchByFinished_Last(
			courseId, passed, orderByComparator);
	}

	/**
	 * Returns the course results before and after the current course result in the ordered set where courseId = &#63; and passed = &#63;.
	 *
	 * @param crId the primary key of the current course result
	 * @param courseId the course ID
	 * @param passed the passed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next course result
	 * @throws NoSuchCourseResultException if a course result with the primary key could not be found
	 */
	public static CourseResult[] findByFinished_PrevAndNext(
			long crId, long courseId, boolean passed,
			OrderByComparator<CourseResult> orderByComparator)
		throws com.ted.lms.exception.NoSuchCourseResultException {

		return getPersistence().findByFinished_PrevAndNext(
			crId, courseId, passed, orderByComparator);
	}

	/**
	 * Removes all the course results where courseId = &#63; and passed = &#63; from the database.
	 *
	 * @param courseId the course ID
	 * @param passed the passed
	 */
	public static void removeByFinished(long courseId, boolean passed) {
		getPersistence().removeByFinished(courseId, passed);
	}

	/**
	 * Returns the number of course results where courseId = &#63; and passed = &#63;.
	 *
	 * @param courseId the course ID
	 * @param passed the passed
	 * @return the number of matching course results
	 */
	public static int countByFinished(long courseId, boolean passed) {
		return getPersistence().countByFinished(courseId, passed);
	}

	/**
	 * Caches the course result in the entity cache if it is enabled.
	 *
	 * @param courseResult the course result
	 */
	public static void cacheResult(CourseResult courseResult) {
		getPersistence().cacheResult(courseResult);
	}

	/**
	 * Caches the course results in the entity cache if it is enabled.
	 *
	 * @param courseResults the course results
	 */
	public static void cacheResult(List<CourseResult> courseResults) {
		getPersistence().cacheResult(courseResults);
	}

	/**
	 * Creates a new course result with the primary key. Does not add the course result to the database.
	 *
	 * @param crId the primary key for the new course result
	 * @return the new course result
	 */
	public static CourseResult create(long crId) {
		return getPersistence().create(crId);
	}

	/**
	 * Removes the course result with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param crId the primary key of the course result
	 * @return the course result that was removed
	 * @throws NoSuchCourseResultException if a course result with the primary key could not be found
	 */
	public static CourseResult remove(long crId)
		throws com.ted.lms.exception.NoSuchCourseResultException {

		return getPersistence().remove(crId);
	}

	public static CourseResult updateImpl(CourseResult courseResult) {
		return getPersistence().updateImpl(courseResult);
	}

	/**
	 * Returns the course result with the primary key or throws a <code>NoSuchCourseResultException</code> if it could not be found.
	 *
	 * @param crId the primary key of the course result
	 * @return the course result
	 * @throws NoSuchCourseResultException if a course result with the primary key could not be found
	 */
	public static CourseResult findByPrimaryKey(long crId)
		throws com.ted.lms.exception.NoSuchCourseResultException {

		return getPersistence().findByPrimaryKey(crId);
	}

	/**
	 * Returns the course result with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param crId the primary key of the course result
	 * @return the course result, or <code>null</code> if a course result with the primary key could not be found
	 */
	public static CourseResult fetchByPrimaryKey(long crId) {
		return getPersistence().fetchByPrimaryKey(crId);
	}

	/**
	 * Returns all the course results.
	 *
	 * @return the course results
	 */
	public static List<CourseResult> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the course results.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CourseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of course results
	 * @param end the upper bound of the range of course results (not inclusive)
	 * @return the range of course results
	 */
	public static List<CourseResult> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the course results.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CourseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of course results
	 * @param end the upper bound of the range of course results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of course results
	 */
	public static List<CourseResult> findAll(
		int start, int end, OrderByComparator<CourseResult> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the course results.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CourseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of course results
	 * @param end the upper bound of the range of course results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of course results
	 */
	public static List<CourseResult> findAll(
		int start, int end, OrderByComparator<CourseResult> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the course results from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of course results.
	 *
	 * @return the number of course results
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static CourseResultPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<CourseResultPersistence, CourseResultPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CourseResultPersistence.class);

		ServiceTracker<CourseResultPersistence, CourseResultPersistence>
			serviceTracker =
				new ServiceTracker
					<CourseResultPersistence, CourseResultPersistence>(
						bundle.getBundleContext(),
						CourseResultPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}