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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.ted.lms.exception.NoSuchLearningActivityTryException;
import com.ted.lms.model.LearningActivityTry;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the learning activity try service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LearningActivityTryUtil
 * @generated
 */
@ProviderType
public interface LearningActivityTryPersistence
	extends BasePersistence<LearningActivityTry> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LearningActivityTryUtil} to access the learning activity try persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the learning activity tries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching learning activity tries
	 */
	public java.util.List<LearningActivityTry> findByUuid(String uuid);

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
	public java.util.List<LearningActivityTry> findByUuid(
		String uuid, int start, int end);

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
	public java.util.List<LearningActivityTry> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivityTry>
			orderByComparator);

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
	public java.util.List<LearningActivityTry> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivityTry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first learning activity try in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity try
	 * @throws NoSuchLearningActivityTryException if a matching learning activity try could not be found
	 */
	public LearningActivityTry findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator
				<LearningActivityTry> orderByComparator)
		throws NoSuchLearningActivityTryException;

	/**
	 * Returns the first learning activity try in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity try, or <code>null</code> if a matching learning activity try could not be found
	 */
	public LearningActivityTry fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivityTry>
			orderByComparator);

	/**
	 * Returns the last learning activity try in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity try
	 * @throws NoSuchLearningActivityTryException if a matching learning activity try could not be found
	 */
	public LearningActivityTry findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator
				<LearningActivityTry> orderByComparator)
		throws NoSuchLearningActivityTryException;

	/**
	 * Returns the last learning activity try in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity try, or <code>null</code> if a matching learning activity try could not be found
	 */
	public LearningActivityTry fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivityTry>
			orderByComparator);

	/**
	 * Returns the learning activity tries before and after the current learning activity try in the ordered set where uuid = &#63;.
	 *
	 * @param latId the primary key of the current learning activity try
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next learning activity try
	 * @throws NoSuchLearningActivityTryException if a learning activity try with the primary key could not be found
	 */
	public LearningActivityTry[] findByUuid_PrevAndNext(
			long latId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator
				<LearningActivityTry> orderByComparator)
		throws NoSuchLearningActivityTryException;

	/**
	 * Removes all the learning activity tries where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of learning activity tries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching learning activity tries
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the learning activity try where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchLearningActivityTryException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching learning activity try
	 * @throws NoSuchLearningActivityTryException if a matching learning activity try could not be found
	 */
	public LearningActivityTry findByUUID_G(String uuid, long groupId)
		throws NoSuchLearningActivityTryException;

	/**
	 * Returns the learning activity try where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching learning activity try, or <code>null</code> if a matching learning activity try could not be found
	 */
	public LearningActivityTry fetchByUUID_G(String uuid, long groupId);

	/**
	 * Returns the learning activity try where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching learning activity try, or <code>null</code> if a matching learning activity try could not be found
	 */
	public LearningActivityTry fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache);

	/**
	 * Removes the learning activity try where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the learning activity try that was removed
	 */
	public LearningActivityTry removeByUUID_G(String uuid, long groupId)
		throws NoSuchLearningActivityTryException;

	/**
	 * Returns the number of learning activity tries where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching learning activity tries
	 */
	public int countByUUID_G(String uuid, long groupId);

	/**
	 * Returns all the learning activity tries where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching learning activity tries
	 */
	public java.util.List<LearningActivityTry> findByUuid_C(
		String uuid, long companyId);

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
	public java.util.List<LearningActivityTry> findByUuid_C(
		String uuid, long companyId, int start, int end);

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
	public java.util.List<LearningActivityTry> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivityTry>
			orderByComparator);

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
	public java.util.List<LearningActivityTry> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivityTry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first learning activity try in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity try
	 * @throws NoSuchLearningActivityTryException if a matching learning activity try could not be found
	 */
	public LearningActivityTry findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator
				<LearningActivityTry> orderByComparator)
		throws NoSuchLearningActivityTryException;

	/**
	 * Returns the first learning activity try in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity try, or <code>null</code> if a matching learning activity try could not be found
	 */
	public LearningActivityTry fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivityTry>
			orderByComparator);

	/**
	 * Returns the last learning activity try in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity try
	 * @throws NoSuchLearningActivityTryException if a matching learning activity try could not be found
	 */
	public LearningActivityTry findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator
				<LearningActivityTry> orderByComparator)
		throws NoSuchLearningActivityTryException;

	/**
	 * Returns the last learning activity try in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity try, or <code>null</code> if a matching learning activity try could not be found
	 */
	public LearningActivityTry fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivityTry>
			orderByComparator);

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
	public LearningActivityTry[] findByUuid_C_PrevAndNext(
			long latId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator
				<LearningActivityTry> orderByComparator)
		throws NoSuchLearningActivityTryException;

	/**
	 * Removes all the learning activity tries where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of learning activity tries where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching learning activity tries
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns all the learning activity tries where actId = &#63;.
	 *
	 * @param actId the act ID
	 * @return the matching learning activity tries
	 */
	public java.util.List<LearningActivityTry> findByActId(long actId);

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
	public java.util.List<LearningActivityTry> findByActId(
		long actId, int start, int end);

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
	public java.util.List<LearningActivityTry> findByActId(
		long actId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivityTry>
			orderByComparator);

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
	public java.util.List<LearningActivityTry> findByActId(
		long actId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivityTry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first learning activity try in the ordered set where actId = &#63;.
	 *
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity try
	 * @throws NoSuchLearningActivityTryException if a matching learning activity try could not be found
	 */
	public LearningActivityTry findByActId_First(
			long actId,
			com.liferay.portal.kernel.util.OrderByComparator
				<LearningActivityTry> orderByComparator)
		throws NoSuchLearningActivityTryException;

	/**
	 * Returns the first learning activity try in the ordered set where actId = &#63;.
	 *
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity try, or <code>null</code> if a matching learning activity try could not be found
	 */
	public LearningActivityTry fetchByActId_First(
		long actId,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivityTry>
			orderByComparator);

	/**
	 * Returns the last learning activity try in the ordered set where actId = &#63;.
	 *
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity try
	 * @throws NoSuchLearningActivityTryException if a matching learning activity try could not be found
	 */
	public LearningActivityTry findByActId_Last(
			long actId,
			com.liferay.portal.kernel.util.OrderByComparator
				<LearningActivityTry> orderByComparator)
		throws NoSuchLearningActivityTryException;

	/**
	 * Returns the last learning activity try in the ordered set where actId = &#63;.
	 *
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity try, or <code>null</code> if a matching learning activity try could not be found
	 */
	public LearningActivityTry fetchByActId_Last(
		long actId,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivityTry>
			orderByComparator);

	/**
	 * Returns the learning activity tries before and after the current learning activity try in the ordered set where actId = &#63;.
	 *
	 * @param latId the primary key of the current learning activity try
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next learning activity try
	 * @throws NoSuchLearningActivityTryException if a learning activity try with the primary key could not be found
	 */
	public LearningActivityTry[] findByActId_PrevAndNext(
			long latId, long actId,
			com.liferay.portal.kernel.util.OrderByComparator
				<LearningActivityTry> orderByComparator)
		throws NoSuchLearningActivityTryException;

	/**
	 * Removes all the learning activity tries where actId = &#63; from the database.
	 *
	 * @param actId the act ID
	 */
	public void removeByActId(long actId);

	/**
	 * Returns the number of learning activity tries where actId = &#63;.
	 *
	 * @param actId the act ID
	 * @return the number of matching learning activity tries
	 */
	public int countByActId(long actId);

	/**
	 * Returns all the learning activity tries where actId = &#63; and userId = &#63;.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @return the matching learning activity tries
	 */
	public java.util.List<LearningActivityTry> findByActIdUserId(
		long actId, long userId);

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
	public java.util.List<LearningActivityTry> findByActIdUserId(
		long actId, long userId, int start, int end);

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
	public java.util.List<LearningActivityTry> findByActIdUserId(
		long actId, long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivityTry>
			orderByComparator);

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
	public java.util.List<LearningActivityTry> findByActIdUserId(
		long actId, long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivityTry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first learning activity try in the ordered set where actId = &#63; and userId = &#63;.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity try
	 * @throws NoSuchLearningActivityTryException if a matching learning activity try could not be found
	 */
	public LearningActivityTry findByActIdUserId_First(
			long actId, long userId,
			com.liferay.portal.kernel.util.OrderByComparator
				<LearningActivityTry> orderByComparator)
		throws NoSuchLearningActivityTryException;

	/**
	 * Returns the first learning activity try in the ordered set where actId = &#63; and userId = &#63;.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity try, or <code>null</code> if a matching learning activity try could not be found
	 */
	public LearningActivityTry fetchByActIdUserId_First(
		long actId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivityTry>
			orderByComparator);

	/**
	 * Returns the last learning activity try in the ordered set where actId = &#63; and userId = &#63;.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity try
	 * @throws NoSuchLearningActivityTryException if a matching learning activity try could not be found
	 */
	public LearningActivityTry findByActIdUserId_Last(
			long actId, long userId,
			com.liferay.portal.kernel.util.OrderByComparator
				<LearningActivityTry> orderByComparator)
		throws NoSuchLearningActivityTryException;

	/**
	 * Returns the last learning activity try in the ordered set where actId = &#63; and userId = &#63;.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity try, or <code>null</code> if a matching learning activity try could not be found
	 */
	public LearningActivityTry fetchByActIdUserId_Last(
		long actId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivityTry>
			orderByComparator);

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
	public LearningActivityTry[] findByActIdUserId_PrevAndNext(
			long latId, long actId, long userId,
			com.liferay.portal.kernel.util.OrderByComparator
				<LearningActivityTry> orderByComparator)
		throws NoSuchLearningActivityTryException;

	/**
	 * Removes all the learning activity tries where actId = &#63; and userId = &#63; from the database.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 */
	public void removeByActIdUserId(long actId, long userId);

	/**
	 * Returns the number of learning activity tries where actId = &#63; and userId = &#63;.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @return the number of matching learning activity tries
	 */
	public int countByActIdUserId(long actId, long userId);

	/**
	 * Returns all the learning activity tries where actId = &#63; and userId = &#63; and endDate = &#63;.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param endDate the end date
	 * @return the matching learning activity tries
	 */
	public java.util.List<LearningActivityTry> findByActIdUserIdEndDate(
		long actId, long userId, Date endDate);

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
	public java.util.List<LearningActivityTry> findByActIdUserIdEndDate(
		long actId, long userId, Date endDate, int start, int end);

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
	public java.util.List<LearningActivityTry> findByActIdUserIdEndDate(
		long actId, long userId, Date endDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivityTry>
			orderByComparator);

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
	public java.util.List<LearningActivityTry> findByActIdUserIdEndDate(
		long actId, long userId, Date endDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivityTry>
			orderByComparator,
		boolean useFinderCache);

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
	public LearningActivityTry findByActIdUserIdEndDate_First(
			long actId, long userId, Date endDate,
			com.liferay.portal.kernel.util.OrderByComparator
				<LearningActivityTry> orderByComparator)
		throws NoSuchLearningActivityTryException;

	/**
	 * Returns the first learning activity try in the ordered set where actId = &#63; and userId = &#63; and endDate = &#63;.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param endDate the end date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity try, or <code>null</code> if a matching learning activity try could not be found
	 */
	public LearningActivityTry fetchByActIdUserIdEndDate_First(
		long actId, long userId, Date endDate,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivityTry>
			orderByComparator);

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
	public LearningActivityTry findByActIdUserIdEndDate_Last(
			long actId, long userId, Date endDate,
			com.liferay.portal.kernel.util.OrderByComparator
				<LearningActivityTry> orderByComparator)
		throws NoSuchLearningActivityTryException;

	/**
	 * Returns the last learning activity try in the ordered set where actId = &#63; and userId = &#63; and endDate = &#63;.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param endDate the end date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity try, or <code>null</code> if a matching learning activity try could not be found
	 */
	public LearningActivityTry fetchByActIdUserIdEndDate_Last(
		long actId, long userId, Date endDate,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivityTry>
			orderByComparator);

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
	public LearningActivityTry[] findByActIdUserIdEndDate_PrevAndNext(
			long latId, long actId, long userId, Date endDate,
			com.liferay.portal.kernel.util.OrderByComparator
				<LearningActivityTry> orderByComparator)
		throws NoSuchLearningActivityTryException;

	/**
	 * Removes all the learning activity tries where actId = &#63; and userId = &#63; and endDate = &#63; from the database.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param endDate the end date
	 */
	public void removeByActIdUserIdEndDate(
		long actId, long userId, Date endDate);

	/**
	 * Returns the number of learning activity tries where actId = &#63; and userId = &#63; and endDate = &#63;.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param endDate the end date
	 * @return the number of matching learning activity tries
	 */
	public int countByActIdUserIdEndDate(long actId, long userId, Date endDate);

	/**
	 * Caches the learning activity try in the entity cache if it is enabled.
	 *
	 * @param learningActivityTry the learning activity try
	 */
	public void cacheResult(LearningActivityTry learningActivityTry);

	/**
	 * Caches the learning activity tries in the entity cache if it is enabled.
	 *
	 * @param learningActivityTries the learning activity tries
	 */
	public void cacheResult(
		java.util.List<LearningActivityTry> learningActivityTries);

	/**
	 * Creates a new learning activity try with the primary key. Does not add the learning activity try to the database.
	 *
	 * @param latId the primary key for the new learning activity try
	 * @return the new learning activity try
	 */
	public LearningActivityTry create(long latId);

	/**
	 * Removes the learning activity try with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param latId the primary key of the learning activity try
	 * @return the learning activity try that was removed
	 * @throws NoSuchLearningActivityTryException if a learning activity try with the primary key could not be found
	 */
	public LearningActivityTry remove(long latId)
		throws NoSuchLearningActivityTryException;

	public LearningActivityTry updateImpl(
		LearningActivityTry learningActivityTry);

	/**
	 * Returns the learning activity try with the primary key or throws a <code>NoSuchLearningActivityTryException</code> if it could not be found.
	 *
	 * @param latId the primary key of the learning activity try
	 * @return the learning activity try
	 * @throws NoSuchLearningActivityTryException if a learning activity try with the primary key could not be found
	 */
	public LearningActivityTry findByPrimaryKey(long latId)
		throws NoSuchLearningActivityTryException;

	/**
	 * Returns the learning activity try with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param latId the primary key of the learning activity try
	 * @return the learning activity try, or <code>null</code> if a learning activity try with the primary key could not be found
	 */
	public LearningActivityTry fetchByPrimaryKey(long latId);

	/**
	 * Returns all the learning activity tries.
	 *
	 * @return the learning activity tries
	 */
	public java.util.List<LearningActivityTry> findAll();

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
	public java.util.List<LearningActivityTry> findAll(int start, int end);

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
	public java.util.List<LearningActivityTry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivityTry>
			orderByComparator);

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
	public java.util.List<LearningActivityTry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivityTry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the learning activity tries from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of learning activity tries.
	 *
	 * @return the number of learning activity tries
	 */
	public int countAll();

}