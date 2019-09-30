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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.ted.lms.learning.activity.survey.exception.NoSuchResultException;
import com.ted.lms.learning.activity.survey.model.SurveyResult;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the survey result service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SurveyResultUtil
 * @generated
 */
@ProviderType
public interface SurveyResultPersistence extends BasePersistence<SurveyResult> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SurveyResultUtil} to access the survey result persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the survey results where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching survey results
	 */
	public java.util.List<SurveyResult> findByUuid(String uuid);

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
	public java.util.List<SurveyResult> findByUuid(
		String uuid, int start, int end);

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
	public java.util.List<SurveyResult> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SurveyResult>
			orderByComparator);

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
	public java.util.List<SurveyResult> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SurveyResult>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first survey result in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching survey result
	 * @throws NoSuchResultException if a matching survey result could not be found
	 */
	public SurveyResult findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<SurveyResult>
				orderByComparator)
		throws NoSuchResultException;

	/**
	 * Returns the first survey result in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching survey result, or <code>null</code> if a matching survey result could not be found
	 */
	public SurveyResult fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<SurveyResult>
			orderByComparator);

	/**
	 * Returns the last survey result in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching survey result
	 * @throws NoSuchResultException if a matching survey result could not be found
	 */
	public SurveyResult findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<SurveyResult>
				orderByComparator)
		throws NoSuchResultException;

	/**
	 * Returns the last survey result in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching survey result, or <code>null</code> if a matching survey result could not be found
	 */
	public SurveyResult fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<SurveyResult>
			orderByComparator);

	/**
	 * Returns the survey results before and after the current survey result in the ordered set where uuid = &#63;.
	 *
	 * @param surveyResultId the primary key of the current survey result
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next survey result
	 * @throws NoSuchResultException if a survey result with the primary key could not be found
	 */
	public SurveyResult[] findByUuid_PrevAndNext(
			long surveyResultId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<SurveyResult>
				orderByComparator)
		throws NoSuchResultException;

	/**
	 * Removes all the survey results where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of survey results where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching survey results
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns all the survey results where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching survey results
	 */
	public java.util.List<SurveyResult> findByUserId(long userId);

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
	public java.util.List<SurveyResult> findByUserId(
		long userId, int start, int end);

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
	public java.util.List<SurveyResult> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SurveyResult>
			orderByComparator);

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
	public java.util.List<SurveyResult> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SurveyResult>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first survey result in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching survey result
	 * @throws NoSuchResultException if a matching survey result could not be found
	 */
	public SurveyResult findByUserId_First(
			long userId,
			com.liferay.portal.kernel.util.OrderByComparator<SurveyResult>
				orderByComparator)
		throws NoSuchResultException;

	/**
	 * Returns the first survey result in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching survey result, or <code>null</code> if a matching survey result could not be found
	 */
	public SurveyResult fetchByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<SurveyResult>
			orderByComparator);

	/**
	 * Returns the last survey result in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching survey result
	 * @throws NoSuchResultException if a matching survey result could not be found
	 */
	public SurveyResult findByUserId_Last(
			long userId,
			com.liferay.portal.kernel.util.OrderByComparator<SurveyResult>
				orderByComparator)
		throws NoSuchResultException;

	/**
	 * Returns the last survey result in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching survey result, or <code>null</code> if a matching survey result could not be found
	 */
	public SurveyResult fetchByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<SurveyResult>
			orderByComparator);

	/**
	 * Returns the survey results before and after the current survey result in the ordered set where userId = &#63;.
	 *
	 * @param surveyResultId the primary key of the current survey result
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next survey result
	 * @throws NoSuchResultException if a survey result with the primary key could not be found
	 */
	public SurveyResult[] findByUserId_PrevAndNext(
			long surveyResultId, long userId,
			com.liferay.portal.kernel.util.OrderByComparator<SurveyResult>
				orderByComparator)
		throws NoSuchResultException;

	/**
	 * Removes all the survey results where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	public void removeByUserId(long userId);

	/**
	 * Returns the number of survey results where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching survey results
	 */
	public int countByUserId(long userId);

	/**
	 * Returns all the survey results where actId = &#63;.
	 *
	 * @param actId the act ID
	 * @return the matching survey results
	 */
	public java.util.List<SurveyResult> findByActId(long actId);

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
	public java.util.List<SurveyResult> findByActId(
		long actId, int start, int end);

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
	public java.util.List<SurveyResult> findByActId(
		long actId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SurveyResult>
			orderByComparator);

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
	public java.util.List<SurveyResult> findByActId(
		long actId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SurveyResult>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first survey result in the ordered set where actId = &#63;.
	 *
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching survey result
	 * @throws NoSuchResultException if a matching survey result could not be found
	 */
	public SurveyResult findByActId_First(
			long actId,
			com.liferay.portal.kernel.util.OrderByComparator<SurveyResult>
				orderByComparator)
		throws NoSuchResultException;

	/**
	 * Returns the first survey result in the ordered set where actId = &#63;.
	 *
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching survey result, or <code>null</code> if a matching survey result could not be found
	 */
	public SurveyResult fetchByActId_First(
		long actId,
		com.liferay.portal.kernel.util.OrderByComparator<SurveyResult>
			orderByComparator);

	/**
	 * Returns the last survey result in the ordered set where actId = &#63;.
	 *
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching survey result
	 * @throws NoSuchResultException if a matching survey result could not be found
	 */
	public SurveyResult findByActId_Last(
			long actId,
			com.liferay.portal.kernel.util.OrderByComparator<SurveyResult>
				orderByComparator)
		throws NoSuchResultException;

	/**
	 * Returns the last survey result in the ordered set where actId = &#63;.
	 *
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching survey result, or <code>null</code> if a matching survey result could not be found
	 */
	public SurveyResult fetchByActId_Last(
		long actId,
		com.liferay.portal.kernel.util.OrderByComparator<SurveyResult>
			orderByComparator);

	/**
	 * Returns the survey results before and after the current survey result in the ordered set where actId = &#63;.
	 *
	 * @param surveyResultId the primary key of the current survey result
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next survey result
	 * @throws NoSuchResultException if a survey result with the primary key could not be found
	 */
	public SurveyResult[] findByActId_PrevAndNext(
			long surveyResultId, long actId,
			com.liferay.portal.kernel.util.OrderByComparator<SurveyResult>
				orderByComparator)
		throws NoSuchResultException;

	/**
	 * Removes all the survey results where actId = &#63; from the database.
	 *
	 * @param actId the act ID
	 */
	public void removeByActId(long actId);

	/**
	 * Returns the number of survey results where actId = &#63;.
	 *
	 * @param actId the act ID
	 * @return the number of matching survey results
	 */
	public int countByActId(long actId);

	/**
	 * Returns all the survey results where questionId = &#63; and actId = &#63;.
	 *
	 * @param questionId the question ID
	 * @param actId the act ID
	 * @return the matching survey results
	 */
	public java.util.List<SurveyResult> findByQuestionIdActId(
		long questionId, long actId);

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
	public java.util.List<SurveyResult> findByQuestionIdActId(
		long questionId, long actId, int start, int end);

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
	public java.util.List<SurveyResult> findByQuestionIdActId(
		long questionId, long actId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SurveyResult>
			orderByComparator);

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
	public java.util.List<SurveyResult> findByQuestionIdActId(
		long questionId, long actId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SurveyResult>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first survey result in the ordered set where questionId = &#63; and actId = &#63;.
	 *
	 * @param questionId the question ID
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching survey result
	 * @throws NoSuchResultException if a matching survey result could not be found
	 */
	public SurveyResult findByQuestionIdActId_First(
			long questionId, long actId,
			com.liferay.portal.kernel.util.OrderByComparator<SurveyResult>
				orderByComparator)
		throws NoSuchResultException;

	/**
	 * Returns the first survey result in the ordered set where questionId = &#63; and actId = &#63;.
	 *
	 * @param questionId the question ID
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching survey result, or <code>null</code> if a matching survey result could not be found
	 */
	public SurveyResult fetchByQuestionIdActId_First(
		long questionId, long actId,
		com.liferay.portal.kernel.util.OrderByComparator<SurveyResult>
			orderByComparator);

	/**
	 * Returns the last survey result in the ordered set where questionId = &#63; and actId = &#63;.
	 *
	 * @param questionId the question ID
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching survey result
	 * @throws NoSuchResultException if a matching survey result could not be found
	 */
	public SurveyResult findByQuestionIdActId_Last(
			long questionId, long actId,
			com.liferay.portal.kernel.util.OrderByComparator<SurveyResult>
				orderByComparator)
		throws NoSuchResultException;

	/**
	 * Returns the last survey result in the ordered set where questionId = &#63; and actId = &#63;.
	 *
	 * @param questionId the question ID
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching survey result, or <code>null</code> if a matching survey result could not be found
	 */
	public SurveyResult fetchByQuestionIdActId_Last(
		long questionId, long actId,
		com.liferay.portal.kernel.util.OrderByComparator<SurveyResult>
			orderByComparator);

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
	public SurveyResult[] findByQuestionIdActId_PrevAndNext(
			long surveyResultId, long questionId, long actId,
			com.liferay.portal.kernel.util.OrderByComparator<SurveyResult>
				orderByComparator)
		throws NoSuchResultException;

	/**
	 * Removes all the survey results where questionId = &#63; and actId = &#63; from the database.
	 *
	 * @param questionId the question ID
	 * @param actId the act ID
	 */
	public void removeByQuestionIdActId(long questionId, long actId);

	/**
	 * Returns the number of survey results where questionId = &#63; and actId = &#63;.
	 *
	 * @param questionId the question ID
	 * @param actId the act ID
	 * @return the number of matching survey results
	 */
	public int countByQuestionIdActId(long questionId, long actId);

	/**
	 * Returns all the survey results where questionId = &#63;.
	 *
	 * @param questionId the question ID
	 * @return the matching survey results
	 */
	public java.util.List<SurveyResult> findByQuestionId(long questionId);

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
	public java.util.List<SurveyResult> findByQuestionId(
		long questionId, int start, int end);

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
	public java.util.List<SurveyResult> findByQuestionId(
		long questionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SurveyResult>
			orderByComparator);

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
	public java.util.List<SurveyResult> findByQuestionId(
		long questionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SurveyResult>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first survey result in the ordered set where questionId = &#63;.
	 *
	 * @param questionId the question ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching survey result
	 * @throws NoSuchResultException if a matching survey result could not be found
	 */
	public SurveyResult findByQuestionId_First(
			long questionId,
			com.liferay.portal.kernel.util.OrderByComparator<SurveyResult>
				orderByComparator)
		throws NoSuchResultException;

	/**
	 * Returns the first survey result in the ordered set where questionId = &#63;.
	 *
	 * @param questionId the question ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching survey result, or <code>null</code> if a matching survey result could not be found
	 */
	public SurveyResult fetchByQuestionId_First(
		long questionId,
		com.liferay.portal.kernel.util.OrderByComparator<SurveyResult>
			orderByComparator);

	/**
	 * Returns the last survey result in the ordered set where questionId = &#63;.
	 *
	 * @param questionId the question ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching survey result
	 * @throws NoSuchResultException if a matching survey result could not be found
	 */
	public SurveyResult findByQuestionId_Last(
			long questionId,
			com.liferay.portal.kernel.util.OrderByComparator<SurveyResult>
				orderByComparator)
		throws NoSuchResultException;

	/**
	 * Returns the last survey result in the ordered set where questionId = &#63;.
	 *
	 * @param questionId the question ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching survey result, or <code>null</code> if a matching survey result could not be found
	 */
	public SurveyResult fetchByQuestionId_Last(
		long questionId,
		com.liferay.portal.kernel.util.OrderByComparator<SurveyResult>
			orderByComparator);

	/**
	 * Returns the survey results before and after the current survey result in the ordered set where questionId = &#63;.
	 *
	 * @param surveyResultId the primary key of the current survey result
	 * @param questionId the question ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next survey result
	 * @throws NoSuchResultException if a survey result with the primary key could not be found
	 */
	public SurveyResult[] findByQuestionId_PrevAndNext(
			long surveyResultId, long questionId,
			com.liferay.portal.kernel.util.OrderByComparator<SurveyResult>
				orderByComparator)
		throws NoSuchResultException;

	/**
	 * Removes all the survey results where questionId = &#63; from the database.
	 *
	 * @param questionId the question ID
	 */
	public void removeByQuestionId(long questionId);

	/**
	 * Returns the number of survey results where questionId = &#63;.
	 *
	 * @param questionId the question ID
	 * @return the number of matching survey results
	 */
	public int countByQuestionId(long questionId);

	/**
	 * Returns all the survey results where answerId = &#63; and questionId = &#63;.
	 *
	 * @param answerId the answer ID
	 * @param questionId the question ID
	 * @return the matching survey results
	 */
	public java.util.List<SurveyResult> findByAnswerIdQuestionId(
		long answerId, long questionId);

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
	public java.util.List<SurveyResult> findByAnswerIdQuestionId(
		long answerId, long questionId, int start, int end);

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
	public java.util.List<SurveyResult> findByAnswerIdQuestionId(
		long answerId, long questionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SurveyResult>
			orderByComparator);

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
	public java.util.List<SurveyResult> findByAnswerIdQuestionId(
		long answerId, long questionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SurveyResult>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first survey result in the ordered set where answerId = &#63; and questionId = &#63;.
	 *
	 * @param answerId the answer ID
	 * @param questionId the question ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching survey result
	 * @throws NoSuchResultException if a matching survey result could not be found
	 */
	public SurveyResult findByAnswerIdQuestionId_First(
			long answerId, long questionId,
			com.liferay.portal.kernel.util.OrderByComparator<SurveyResult>
				orderByComparator)
		throws NoSuchResultException;

	/**
	 * Returns the first survey result in the ordered set where answerId = &#63; and questionId = &#63;.
	 *
	 * @param answerId the answer ID
	 * @param questionId the question ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching survey result, or <code>null</code> if a matching survey result could not be found
	 */
	public SurveyResult fetchByAnswerIdQuestionId_First(
		long answerId, long questionId,
		com.liferay.portal.kernel.util.OrderByComparator<SurveyResult>
			orderByComparator);

	/**
	 * Returns the last survey result in the ordered set where answerId = &#63; and questionId = &#63;.
	 *
	 * @param answerId the answer ID
	 * @param questionId the question ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching survey result
	 * @throws NoSuchResultException if a matching survey result could not be found
	 */
	public SurveyResult findByAnswerIdQuestionId_Last(
			long answerId, long questionId,
			com.liferay.portal.kernel.util.OrderByComparator<SurveyResult>
				orderByComparator)
		throws NoSuchResultException;

	/**
	 * Returns the last survey result in the ordered set where answerId = &#63; and questionId = &#63;.
	 *
	 * @param answerId the answer ID
	 * @param questionId the question ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching survey result, or <code>null</code> if a matching survey result could not be found
	 */
	public SurveyResult fetchByAnswerIdQuestionId_Last(
		long answerId, long questionId,
		com.liferay.portal.kernel.util.OrderByComparator<SurveyResult>
			orderByComparator);

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
	public SurveyResult[] findByAnswerIdQuestionId_PrevAndNext(
			long surveyResultId, long answerId, long questionId,
			com.liferay.portal.kernel.util.OrderByComparator<SurveyResult>
				orderByComparator)
		throws NoSuchResultException;

	/**
	 * Removes all the survey results where answerId = &#63; and questionId = &#63; from the database.
	 *
	 * @param answerId the answer ID
	 * @param questionId the question ID
	 */
	public void removeByAnswerIdQuestionId(long answerId, long questionId);

	/**
	 * Returns the number of survey results where answerId = &#63; and questionId = &#63;.
	 *
	 * @param answerId the answer ID
	 * @param questionId the question ID
	 * @return the number of matching survey results
	 */
	public int countByAnswerIdQuestionId(long answerId, long questionId);

	/**
	 * Caches the survey result in the entity cache if it is enabled.
	 *
	 * @param surveyResult the survey result
	 */
	public void cacheResult(SurveyResult surveyResult);

	/**
	 * Caches the survey results in the entity cache if it is enabled.
	 *
	 * @param surveyResults the survey results
	 */
	public void cacheResult(java.util.List<SurveyResult> surveyResults);

	/**
	 * Creates a new survey result with the primary key. Does not add the survey result to the database.
	 *
	 * @param surveyResultId the primary key for the new survey result
	 * @return the new survey result
	 */
	public SurveyResult create(long surveyResultId);

	/**
	 * Removes the survey result with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param surveyResultId the primary key of the survey result
	 * @return the survey result that was removed
	 * @throws NoSuchResultException if a survey result with the primary key could not be found
	 */
	public SurveyResult remove(long surveyResultId)
		throws NoSuchResultException;

	public SurveyResult updateImpl(SurveyResult surveyResult);

	/**
	 * Returns the survey result with the primary key or throws a <code>NoSuchResultException</code> if it could not be found.
	 *
	 * @param surveyResultId the primary key of the survey result
	 * @return the survey result
	 * @throws NoSuchResultException if a survey result with the primary key could not be found
	 */
	public SurveyResult findByPrimaryKey(long surveyResultId)
		throws NoSuchResultException;

	/**
	 * Returns the survey result with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param surveyResultId the primary key of the survey result
	 * @return the survey result, or <code>null</code> if a survey result with the primary key could not be found
	 */
	public SurveyResult fetchByPrimaryKey(long surveyResultId);

	/**
	 * Returns all the survey results.
	 *
	 * @return the survey results
	 */
	public java.util.List<SurveyResult> findAll();

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
	public java.util.List<SurveyResult> findAll(int start, int end);

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
	public java.util.List<SurveyResult> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SurveyResult>
			orderByComparator);

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
	public java.util.List<SurveyResult> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SurveyResult>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the survey results from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of survey results.
	 *
	 * @return the number of survey results
	 */
	public int countAll();

}