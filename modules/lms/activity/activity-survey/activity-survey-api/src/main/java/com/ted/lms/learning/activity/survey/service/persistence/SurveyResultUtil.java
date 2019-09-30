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

package com.ted.lms.learning.activity.survey.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.ted.lms.learning.activity.survey.model.SurveyResult;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.annotation.versioning.ProviderType;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the survey result service. This utility wraps <code>com.ted.lms.learning.activity.survey.service.persistence.impl.SurveyResultPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SurveyResultPersistence
 * @generated
 */
@ProviderType
public class SurveyResultUtil {

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
	public static void clearCache(SurveyResult surveyResult) {
		getPersistence().clearCache(surveyResult);
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
	public static Map<Serializable, SurveyResult> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<SurveyResult> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SurveyResult> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SurveyResult> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<SurveyResult> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static SurveyResult update(SurveyResult surveyResult) {
		return getPersistence().update(surveyResult);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static SurveyResult update(
		SurveyResult surveyResult, ServiceContext serviceContext) {

		return getPersistence().update(surveyResult, serviceContext);
	}

	/**
	 * Returns all the survey results where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching survey results
	 */
	public static List<SurveyResult> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the survey results where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>SurveyResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of survey results
	 * @param end the upper bound of the range of survey results (not inclusive)
	 * @return the range of matching survey results
	 */
	public static List<SurveyResult> findByUuid(
		String uuid, int start, int end) {

		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the survey results where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>SurveyResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of survey results
	 * @param end the upper bound of the range of survey results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching survey results
	 */
	public static List<SurveyResult> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<SurveyResult> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the survey results where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>SurveyResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of survey results
	 * @param end the upper bound of the range of survey results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching survey results
	 */
	public static List<SurveyResult> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<SurveyResult> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first survey result in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching survey result
	 * @throws NoSuchResultException if a matching survey result could not be found
	 */
	public static SurveyResult findByUuid_First(
			String uuid, OrderByComparator<SurveyResult> orderByComparator)
		throws com.ted.lms.learning.activity.survey.exception.
			NoSuchResultException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first survey result in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching survey result, or <code>null</code> if a matching survey result could not be found
	 */
	public static SurveyResult fetchByUuid_First(
		String uuid, OrderByComparator<SurveyResult> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last survey result in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching survey result
	 * @throws NoSuchResultException if a matching survey result could not be found
	 */
	public static SurveyResult findByUuid_Last(
			String uuid, OrderByComparator<SurveyResult> orderByComparator)
		throws com.ted.lms.learning.activity.survey.exception.
			NoSuchResultException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last survey result in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching survey result, or <code>null</code> if a matching survey result could not be found
	 */
	public static SurveyResult fetchByUuid_Last(
		String uuid, OrderByComparator<SurveyResult> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the survey results before and after the current survey result in the ordered set where uuid = &#63;.
	 *
	 * @param surveyResultId the primary key of the current survey result
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next survey result
	 * @throws NoSuchResultException if a survey result with the primary key could not be found
	 */
	public static SurveyResult[] findByUuid_PrevAndNext(
			long surveyResultId, String uuid,
			OrderByComparator<SurveyResult> orderByComparator)
		throws com.ted.lms.learning.activity.survey.exception.
			NoSuchResultException {

		return getPersistence().findByUuid_PrevAndNext(
			surveyResultId, uuid, orderByComparator);
	}

	/**
	 * Removes all the survey results where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of survey results where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching survey results
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns all the survey results where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching survey results
	 */
	public static List<SurveyResult> findByUserId(long userId) {
		return getPersistence().findByUserId(userId);
	}

	/**
	 * Returns a range of all the survey results where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>SurveyResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of survey results
	 * @param end the upper bound of the range of survey results (not inclusive)
	 * @return the range of matching survey results
	 */
	public static List<SurveyResult> findByUserId(
		long userId, int start, int end) {

		return getPersistence().findByUserId(userId, start, end);
	}

	/**
	 * Returns an ordered range of all the survey results where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>SurveyResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of survey results
	 * @param end the upper bound of the range of survey results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching survey results
	 */
	public static List<SurveyResult> findByUserId(
		long userId, int start, int end,
		OrderByComparator<SurveyResult> orderByComparator) {

		return getPersistence().findByUserId(
			userId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the survey results where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>SurveyResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of survey results
	 * @param end the upper bound of the range of survey results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching survey results
	 */
	public static List<SurveyResult> findByUserId(
		long userId, int start, int end,
		OrderByComparator<SurveyResult> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUserId(
			userId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first survey result in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching survey result
	 * @throws NoSuchResultException if a matching survey result could not be found
	 */
	public static SurveyResult findByUserId_First(
			long userId, OrderByComparator<SurveyResult> orderByComparator)
		throws com.ted.lms.learning.activity.survey.exception.
			NoSuchResultException {

		return getPersistence().findByUserId_First(userId, orderByComparator);
	}

	/**
	 * Returns the first survey result in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching survey result, or <code>null</code> if a matching survey result could not be found
	 */
	public static SurveyResult fetchByUserId_First(
		long userId, OrderByComparator<SurveyResult> orderByComparator) {

		return getPersistence().fetchByUserId_First(userId, orderByComparator);
	}

	/**
	 * Returns the last survey result in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching survey result
	 * @throws NoSuchResultException if a matching survey result could not be found
	 */
	public static SurveyResult findByUserId_Last(
			long userId, OrderByComparator<SurveyResult> orderByComparator)
		throws com.ted.lms.learning.activity.survey.exception.
			NoSuchResultException {

		return getPersistence().findByUserId_Last(userId, orderByComparator);
	}

	/**
	 * Returns the last survey result in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching survey result, or <code>null</code> if a matching survey result could not be found
	 */
	public static SurveyResult fetchByUserId_Last(
		long userId, OrderByComparator<SurveyResult> orderByComparator) {

		return getPersistence().fetchByUserId_Last(userId, orderByComparator);
	}

	/**
	 * Returns the survey results before and after the current survey result in the ordered set where userId = &#63;.
	 *
	 * @param surveyResultId the primary key of the current survey result
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next survey result
	 * @throws NoSuchResultException if a survey result with the primary key could not be found
	 */
	public static SurveyResult[] findByUserId_PrevAndNext(
			long surveyResultId, long userId,
			OrderByComparator<SurveyResult> orderByComparator)
		throws com.ted.lms.learning.activity.survey.exception.
			NoSuchResultException {

		return getPersistence().findByUserId_PrevAndNext(
			surveyResultId, userId, orderByComparator);
	}

	/**
	 * Removes all the survey results where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	public static void removeByUserId(long userId) {
		getPersistence().removeByUserId(userId);
	}

	/**
	 * Returns the number of survey results where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching survey results
	 */
	public static int countByUserId(long userId) {
		return getPersistence().countByUserId(userId);
	}

	/**
	 * Returns all the survey results where actId = &#63;.
	 *
	 * @param actId the act ID
	 * @return the matching survey results
	 */
	public static List<SurveyResult> findByActId(long actId) {
		return getPersistence().findByActId(actId);
	}

	/**
	 * Returns a range of all the survey results where actId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>SurveyResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param actId the act ID
	 * @param start the lower bound of the range of survey results
	 * @param end the upper bound of the range of survey results (not inclusive)
	 * @return the range of matching survey results
	 */
	public static List<SurveyResult> findByActId(
		long actId, int start, int end) {

		return getPersistence().findByActId(actId, start, end);
	}

	/**
	 * Returns an ordered range of all the survey results where actId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>SurveyResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param actId the act ID
	 * @param start the lower bound of the range of survey results
	 * @param end the upper bound of the range of survey results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching survey results
	 */
	public static List<SurveyResult> findByActId(
		long actId, int start, int end,
		OrderByComparator<SurveyResult> orderByComparator) {

		return getPersistence().findByActId(
			actId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the survey results where actId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>SurveyResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param actId the act ID
	 * @param start the lower bound of the range of survey results
	 * @param end the upper bound of the range of survey results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching survey results
	 */
	public static List<SurveyResult> findByActId(
		long actId, int start, int end,
		OrderByComparator<SurveyResult> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByActId(
			actId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first survey result in the ordered set where actId = &#63;.
	 *
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching survey result
	 * @throws NoSuchResultException if a matching survey result could not be found
	 */
	public static SurveyResult findByActId_First(
			long actId, OrderByComparator<SurveyResult> orderByComparator)
		throws com.ted.lms.learning.activity.survey.exception.
			NoSuchResultException {

		return getPersistence().findByActId_First(actId, orderByComparator);
	}

	/**
	 * Returns the first survey result in the ordered set where actId = &#63;.
	 *
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching survey result, or <code>null</code> if a matching survey result could not be found
	 */
	public static SurveyResult fetchByActId_First(
		long actId, OrderByComparator<SurveyResult> orderByComparator) {

		return getPersistence().fetchByActId_First(actId, orderByComparator);
	}

	/**
	 * Returns the last survey result in the ordered set where actId = &#63;.
	 *
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching survey result
	 * @throws NoSuchResultException if a matching survey result could not be found
	 */
	public static SurveyResult findByActId_Last(
			long actId, OrderByComparator<SurveyResult> orderByComparator)
		throws com.ted.lms.learning.activity.survey.exception.
			NoSuchResultException {

		return getPersistence().findByActId_Last(actId, orderByComparator);
	}

	/**
	 * Returns the last survey result in the ordered set where actId = &#63;.
	 *
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching survey result, or <code>null</code> if a matching survey result could not be found
	 */
	public static SurveyResult fetchByActId_Last(
		long actId, OrderByComparator<SurveyResult> orderByComparator) {

		return getPersistence().fetchByActId_Last(actId, orderByComparator);
	}

	/**
	 * Returns the survey results before and after the current survey result in the ordered set where actId = &#63;.
	 *
	 * @param surveyResultId the primary key of the current survey result
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next survey result
	 * @throws NoSuchResultException if a survey result with the primary key could not be found
	 */
	public static SurveyResult[] findByActId_PrevAndNext(
			long surveyResultId, long actId,
			OrderByComparator<SurveyResult> orderByComparator)
		throws com.ted.lms.learning.activity.survey.exception.
			NoSuchResultException {

		return getPersistence().findByActId_PrevAndNext(
			surveyResultId, actId, orderByComparator);
	}

	/**
	 * Removes all the survey results where actId = &#63; from the database.
	 *
	 * @param actId the act ID
	 */
	public static void removeByActId(long actId) {
		getPersistence().removeByActId(actId);
	}

	/**
	 * Returns the number of survey results where actId = &#63;.
	 *
	 * @param actId the act ID
	 * @return the number of matching survey results
	 */
	public static int countByActId(long actId) {
		return getPersistence().countByActId(actId);
	}

	/**
	 * Returns all the survey results where questionId = &#63; and actId = &#63;.
	 *
	 * @param questionId the question ID
	 * @param actId the act ID
	 * @return the matching survey results
	 */
	public static List<SurveyResult> findByQuestionIdActId(
		long questionId, long actId) {

		return getPersistence().findByQuestionIdActId(questionId, actId);
	}

	/**
	 * Returns a range of all the survey results where questionId = &#63; and actId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>SurveyResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param questionId the question ID
	 * @param actId the act ID
	 * @param start the lower bound of the range of survey results
	 * @param end the upper bound of the range of survey results (not inclusive)
	 * @return the range of matching survey results
	 */
	public static List<SurveyResult> findByQuestionIdActId(
		long questionId, long actId, int start, int end) {

		return getPersistence().findByQuestionIdActId(
			questionId, actId, start, end);
	}

	/**
	 * Returns an ordered range of all the survey results where questionId = &#63; and actId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>SurveyResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param questionId the question ID
	 * @param actId the act ID
	 * @param start the lower bound of the range of survey results
	 * @param end the upper bound of the range of survey results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching survey results
	 */
	public static List<SurveyResult> findByQuestionIdActId(
		long questionId, long actId, int start, int end,
		OrderByComparator<SurveyResult> orderByComparator) {

		return getPersistence().findByQuestionIdActId(
			questionId, actId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the survey results where questionId = &#63; and actId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>SurveyResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param questionId the question ID
	 * @param actId the act ID
	 * @param start the lower bound of the range of survey results
	 * @param end the upper bound of the range of survey results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching survey results
	 */
	public static List<SurveyResult> findByQuestionIdActId(
		long questionId, long actId, int start, int end,
		OrderByComparator<SurveyResult> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByQuestionIdActId(
			questionId, actId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first survey result in the ordered set where questionId = &#63; and actId = &#63;.
	 *
	 * @param questionId the question ID
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching survey result
	 * @throws NoSuchResultException if a matching survey result could not be found
	 */
	public static SurveyResult findByQuestionIdActId_First(
			long questionId, long actId,
			OrderByComparator<SurveyResult> orderByComparator)
		throws com.ted.lms.learning.activity.survey.exception.
			NoSuchResultException {

		return getPersistence().findByQuestionIdActId_First(
			questionId, actId, orderByComparator);
	}

	/**
	 * Returns the first survey result in the ordered set where questionId = &#63; and actId = &#63;.
	 *
	 * @param questionId the question ID
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching survey result, or <code>null</code> if a matching survey result could not be found
	 */
	public static SurveyResult fetchByQuestionIdActId_First(
		long questionId, long actId,
		OrderByComparator<SurveyResult> orderByComparator) {

		return getPersistence().fetchByQuestionIdActId_First(
			questionId, actId, orderByComparator);
	}

	/**
	 * Returns the last survey result in the ordered set where questionId = &#63; and actId = &#63;.
	 *
	 * @param questionId the question ID
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching survey result
	 * @throws NoSuchResultException if a matching survey result could not be found
	 */
	public static SurveyResult findByQuestionIdActId_Last(
			long questionId, long actId,
			OrderByComparator<SurveyResult> orderByComparator)
		throws com.ted.lms.learning.activity.survey.exception.
			NoSuchResultException {

		return getPersistence().findByQuestionIdActId_Last(
			questionId, actId, orderByComparator);
	}

	/**
	 * Returns the last survey result in the ordered set where questionId = &#63; and actId = &#63;.
	 *
	 * @param questionId the question ID
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching survey result, or <code>null</code> if a matching survey result could not be found
	 */
	public static SurveyResult fetchByQuestionIdActId_Last(
		long questionId, long actId,
		OrderByComparator<SurveyResult> orderByComparator) {

		return getPersistence().fetchByQuestionIdActId_Last(
			questionId, actId, orderByComparator);
	}

	/**
	 * Returns the survey results before and after the current survey result in the ordered set where questionId = &#63; and actId = &#63;.
	 *
	 * @param surveyResultId the primary key of the current survey result
	 * @param questionId the question ID
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next survey result
	 * @throws NoSuchResultException if a survey result with the primary key could not be found
	 */
	public static SurveyResult[] findByQuestionIdActId_PrevAndNext(
			long surveyResultId, long questionId, long actId,
			OrderByComparator<SurveyResult> orderByComparator)
		throws com.ted.lms.learning.activity.survey.exception.
			NoSuchResultException {

		return getPersistence().findByQuestionIdActId_PrevAndNext(
			surveyResultId, questionId, actId, orderByComparator);
	}

	/**
	 * Removes all the survey results where questionId = &#63; and actId = &#63; from the database.
	 *
	 * @param questionId the question ID
	 * @param actId the act ID
	 */
	public static void removeByQuestionIdActId(long questionId, long actId) {
		getPersistence().removeByQuestionIdActId(questionId, actId);
	}

	/**
	 * Returns the number of survey results where questionId = &#63; and actId = &#63;.
	 *
	 * @param questionId the question ID
	 * @param actId the act ID
	 * @return the number of matching survey results
	 */
	public static int countByQuestionIdActId(long questionId, long actId) {
		return getPersistence().countByQuestionIdActId(questionId, actId);
	}

	/**
	 * Returns all the survey results where questionId = &#63;.
	 *
	 * @param questionId the question ID
	 * @return the matching survey results
	 */
	public static List<SurveyResult> findByQuestionId(long questionId) {
		return getPersistence().findByQuestionId(questionId);
	}

	/**
	 * Returns a range of all the survey results where questionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>SurveyResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param questionId the question ID
	 * @param start the lower bound of the range of survey results
	 * @param end the upper bound of the range of survey results (not inclusive)
	 * @return the range of matching survey results
	 */
	public static List<SurveyResult> findByQuestionId(
		long questionId, int start, int end) {

		return getPersistence().findByQuestionId(questionId, start, end);
	}

	/**
	 * Returns an ordered range of all the survey results where questionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>SurveyResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param questionId the question ID
	 * @param start the lower bound of the range of survey results
	 * @param end the upper bound of the range of survey results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching survey results
	 */
	public static List<SurveyResult> findByQuestionId(
		long questionId, int start, int end,
		OrderByComparator<SurveyResult> orderByComparator) {

		return getPersistence().findByQuestionId(
			questionId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the survey results where questionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>SurveyResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param questionId the question ID
	 * @param start the lower bound of the range of survey results
	 * @param end the upper bound of the range of survey results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching survey results
	 */
	public static List<SurveyResult> findByQuestionId(
		long questionId, int start, int end,
		OrderByComparator<SurveyResult> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByQuestionId(
			questionId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first survey result in the ordered set where questionId = &#63;.
	 *
	 * @param questionId the question ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching survey result
	 * @throws NoSuchResultException if a matching survey result could not be found
	 */
	public static SurveyResult findByQuestionId_First(
			long questionId, OrderByComparator<SurveyResult> orderByComparator)
		throws com.ted.lms.learning.activity.survey.exception.
			NoSuchResultException {

		return getPersistence().findByQuestionId_First(
			questionId, orderByComparator);
	}

	/**
	 * Returns the first survey result in the ordered set where questionId = &#63;.
	 *
	 * @param questionId the question ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching survey result, or <code>null</code> if a matching survey result could not be found
	 */
	public static SurveyResult fetchByQuestionId_First(
		long questionId, OrderByComparator<SurveyResult> orderByComparator) {

		return getPersistence().fetchByQuestionId_First(
			questionId, orderByComparator);
	}

	/**
	 * Returns the last survey result in the ordered set where questionId = &#63;.
	 *
	 * @param questionId the question ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching survey result
	 * @throws NoSuchResultException if a matching survey result could not be found
	 */
	public static SurveyResult findByQuestionId_Last(
			long questionId, OrderByComparator<SurveyResult> orderByComparator)
		throws com.ted.lms.learning.activity.survey.exception.
			NoSuchResultException {

		return getPersistence().findByQuestionId_Last(
			questionId, orderByComparator);
	}

	/**
	 * Returns the last survey result in the ordered set where questionId = &#63;.
	 *
	 * @param questionId the question ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching survey result, or <code>null</code> if a matching survey result could not be found
	 */
	public static SurveyResult fetchByQuestionId_Last(
		long questionId, OrderByComparator<SurveyResult> orderByComparator) {

		return getPersistence().fetchByQuestionId_Last(
			questionId, orderByComparator);
	}

	/**
	 * Returns the survey results before and after the current survey result in the ordered set where questionId = &#63;.
	 *
	 * @param surveyResultId the primary key of the current survey result
	 * @param questionId the question ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next survey result
	 * @throws NoSuchResultException if a survey result with the primary key could not be found
	 */
	public static SurveyResult[] findByQuestionId_PrevAndNext(
			long surveyResultId, long questionId,
			OrderByComparator<SurveyResult> orderByComparator)
		throws com.ted.lms.learning.activity.survey.exception.
			NoSuchResultException {

		return getPersistence().findByQuestionId_PrevAndNext(
			surveyResultId, questionId, orderByComparator);
	}

	/**
	 * Removes all the survey results where questionId = &#63; from the database.
	 *
	 * @param questionId the question ID
	 */
	public static void removeByQuestionId(long questionId) {
		getPersistence().removeByQuestionId(questionId);
	}

	/**
	 * Returns the number of survey results where questionId = &#63;.
	 *
	 * @param questionId the question ID
	 * @return the number of matching survey results
	 */
	public static int countByQuestionId(long questionId) {
		return getPersistence().countByQuestionId(questionId);
	}

	/**
	 * Returns all the survey results where answerId = &#63; and questionId = &#63;.
	 *
	 * @param answerId the answer ID
	 * @param questionId the question ID
	 * @return the matching survey results
	 */
	public static List<SurveyResult> findByAnswerIdQuestionId(
		long answerId, long questionId) {

		return getPersistence().findByAnswerIdQuestionId(answerId, questionId);
	}

	/**
	 * Returns a range of all the survey results where answerId = &#63; and questionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>SurveyResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param answerId the answer ID
	 * @param questionId the question ID
	 * @param start the lower bound of the range of survey results
	 * @param end the upper bound of the range of survey results (not inclusive)
	 * @return the range of matching survey results
	 */
	public static List<SurveyResult> findByAnswerIdQuestionId(
		long answerId, long questionId, int start, int end) {

		return getPersistence().findByAnswerIdQuestionId(
			answerId, questionId, start, end);
	}

	/**
	 * Returns an ordered range of all the survey results where answerId = &#63; and questionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>SurveyResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param answerId the answer ID
	 * @param questionId the question ID
	 * @param start the lower bound of the range of survey results
	 * @param end the upper bound of the range of survey results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching survey results
	 */
	public static List<SurveyResult> findByAnswerIdQuestionId(
		long answerId, long questionId, int start, int end,
		OrderByComparator<SurveyResult> orderByComparator) {

		return getPersistence().findByAnswerIdQuestionId(
			answerId, questionId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the survey results where answerId = &#63; and questionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>SurveyResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param answerId the answer ID
	 * @param questionId the question ID
	 * @param start the lower bound of the range of survey results
	 * @param end the upper bound of the range of survey results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching survey results
	 */
	public static List<SurveyResult> findByAnswerIdQuestionId(
		long answerId, long questionId, int start, int end,
		OrderByComparator<SurveyResult> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByAnswerIdQuestionId(
			answerId, questionId, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first survey result in the ordered set where answerId = &#63; and questionId = &#63;.
	 *
	 * @param answerId the answer ID
	 * @param questionId the question ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching survey result
	 * @throws NoSuchResultException if a matching survey result could not be found
	 */
	public static SurveyResult findByAnswerIdQuestionId_First(
			long answerId, long questionId,
			OrderByComparator<SurveyResult> orderByComparator)
		throws com.ted.lms.learning.activity.survey.exception.
			NoSuchResultException {

		return getPersistence().findByAnswerIdQuestionId_First(
			answerId, questionId, orderByComparator);
	}

	/**
	 * Returns the first survey result in the ordered set where answerId = &#63; and questionId = &#63;.
	 *
	 * @param answerId the answer ID
	 * @param questionId the question ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching survey result, or <code>null</code> if a matching survey result could not be found
	 */
	public static SurveyResult fetchByAnswerIdQuestionId_First(
		long answerId, long questionId,
		OrderByComparator<SurveyResult> orderByComparator) {

		return getPersistence().fetchByAnswerIdQuestionId_First(
			answerId, questionId, orderByComparator);
	}

	/**
	 * Returns the last survey result in the ordered set where answerId = &#63; and questionId = &#63;.
	 *
	 * @param answerId the answer ID
	 * @param questionId the question ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching survey result
	 * @throws NoSuchResultException if a matching survey result could not be found
	 */
	public static SurveyResult findByAnswerIdQuestionId_Last(
			long answerId, long questionId,
			OrderByComparator<SurveyResult> orderByComparator)
		throws com.ted.lms.learning.activity.survey.exception.
			NoSuchResultException {

		return getPersistence().findByAnswerIdQuestionId_Last(
			answerId, questionId, orderByComparator);
	}

	/**
	 * Returns the last survey result in the ordered set where answerId = &#63; and questionId = &#63;.
	 *
	 * @param answerId the answer ID
	 * @param questionId the question ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching survey result, or <code>null</code> if a matching survey result could not be found
	 */
	public static SurveyResult fetchByAnswerIdQuestionId_Last(
		long answerId, long questionId,
		OrderByComparator<SurveyResult> orderByComparator) {

		return getPersistence().fetchByAnswerIdQuestionId_Last(
			answerId, questionId, orderByComparator);
	}

	/**
	 * Returns the survey results before and after the current survey result in the ordered set where answerId = &#63; and questionId = &#63;.
	 *
	 * @param surveyResultId the primary key of the current survey result
	 * @param answerId the answer ID
	 * @param questionId the question ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next survey result
	 * @throws NoSuchResultException if a survey result with the primary key could not be found
	 */
	public static SurveyResult[] findByAnswerIdQuestionId_PrevAndNext(
			long surveyResultId, long answerId, long questionId,
			OrderByComparator<SurveyResult> orderByComparator)
		throws com.ted.lms.learning.activity.survey.exception.
			NoSuchResultException {

		return getPersistence().findByAnswerIdQuestionId_PrevAndNext(
			surveyResultId, answerId, questionId, orderByComparator);
	}

	/**
	 * Removes all the survey results where answerId = &#63; and questionId = &#63; from the database.
	 *
	 * @param answerId the answer ID
	 * @param questionId the question ID
	 */
	public static void removeByAnswerIdQuestionId(
		long answerId, long questionId) {

		getPersistence().removeByAnswerIdQuestionId(answerId, questionId);
	}

	/**
	 * Returns the number of survey results where answerId = &#63; and questionId = &#63;.
	 *
	 * @param answerId the answer ID
	 * @param questionId the question ID
	 * @return the number of matching survey results
	 */
	public static int countByAnswerIdQuestionId(
		long answerId, long questionId) {

		return getPersistence().countByAnswerIdQuestionId(answerId, questionId);
	}

	/**
	 * Caches the survey result in the entity cache if it is enabled.
	 *
	 * @param surveyResult the survey result
	 */
	public static void cacheResult(SurveyResult surveyResult) {
		getPersistence().cacheResult(surveyResult);
	}

	/**
	 * Caches the survey results in the entity cache if it is enabled.
	 *
	 * @param surveyResults the survey results
	 */
	public static void cacheResult(List<SurveyResult> surveyResults) {
		getPersistence().cacheResult(surveyResults);
	}

	/**
	 * Creates a new survey result with the primary key. Does not add the survey result to the database.
	 *
	 * @param surveyResultId the primary key for the new survey result
	 * @return the new survey result
	 */
	public static SurveyResult create(long surveyResultId) {
		return getPersistence().create(surveyResultId);
	}

	/**
	 * Removes the survey result with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param surveyResultId the primary key of the survey result
	 * @return the survey result that was removed
	 * @throws NoSuchResultException if a survey result with the primary key could not be found
	 */
	public static SurveyResult remove(long surveyResultId)
		throws com.ted.lms.learning.activity.survey.exception.
			NoSuchResultException {

		return getPersistence().remove(surveyResultId);
	}

	public static SurveyResult updateImpl(SurveyResult surveyResult) {
		return getPersistence().updateImpl(surveyResult);
	}

	/**
	 * Returns the survey result with the primary key or throws a <code>NoSuchResultException</code> if it could not be found.
	 *
	 * @param surveyResultId the primary key of the survey result
	 * @return the survey result
	 * @throws NoSuchResultException if a survey result with the primary key could not be found
	 */
	public static SurveyResult findByPrimaryKey(long surveyResultId)
		throws com.ted.lms.learning.activity.survey.exception.
			NoSuchResultException {

		return getPersistence().findByPrimaryKey(surveyResultId);
	}

	/**
	 * Returns the survey result with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param surveyResultId the primary key of the survey result
	 * @return the survey result, or <code>null</code> if a survey result with the primary key could not be found
	 */
	public static SurveyResult fetchByPrimaryKey(long surveyResultId) {
		return getPersistence().fetchByPrimaryKey(surveyResultId);
	}

	/**
	 * Returns all the survey results.
	 *
	 * @return the survey results
	 */
	public static List<SurveyResult> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the survey results.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>SurveyResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of survey results
	 * @param end the upper bound of the range of survey results (not inclusive)
	 * @return the range of survey results
	 */
	public static List<SurveyResult> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the survey results.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>SurveyResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of survey results
	 * @param end the upper bound of the range of survey results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of survey results
	 */
	public static List<SurveyResult> findAll(
		int start, int end, OrderByComparator<SurveyResult> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the survey results.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>SurveyResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of survey results
	 * @param end the upper bound of the range of survey results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of survey results
	 */
	public static List<SurveyResult> findAll(
		int start, int end, OrderByComparator<SurveyResult> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the survey results from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of survey results.
	 *
	 * @return the number of survey results
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static SurveyResultPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<SurveyResultPersistence, SurveyResultPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(SurveyResultPersistence.class);

		ServiceTracker<SurveyResultPersistence, SurveyResultPersistence>
			serviceTracker =
				new ServiceTracker
					<SurveyResultPersistence, SurveyResultPersistence>(
						bundle.getBundleContext(),
						SurveyResultPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}