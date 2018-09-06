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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.ted.lms.exception.NoSuchCourseResultException;
import com.ted.lms.model.CourseResult;

/**
 * The persistence interface for the course result service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ted.lms.service.persistence.impl.CourseResultPersistenceImpl
 * @see CourseResultUtil
 * @generated
 */
@ProviderType
public interface CourseResultPersistence extends BasePersistence<CourseResult> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CourseResultUtil} to access the course result persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the course results where courseId = &#63;.
	*
	* @param courseId the course ID
	* @return the matching course results
	*/
	public java.util.List<CourseResult> findByCourseId(long courseId);

	/**
	* Returns a range of all the course results where courseId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CourseResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param courseId the course ID
	* @param start the lower bound of the range of course results
	* @param end the upper bound of the range of course results (not inclusive)
	* @return the range of matching course results
	*/
	public java.util.List<CourseResult> findByCourseId(long courseId,
		int start, int end);

	/**
	* Returns an ordered range of all the course results where courseId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CourseResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param courseId the course ID
	* @param start the lower bound of the range of course results
	* @param end the upper bound of the range of course results (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching course results
	*/
	public java.util.List<CourseResult> findByCourseId(long courseId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CourseResult> orderByComparator);

	/**
	* Returns an ordered range of all the course results where courseId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CourseResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param courseId the course ID
	* @param start the lower bound of the range of course results
	* @param end the upper bound of the range of course results (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching course results
	*/
	public java.util.List<CourseResult> findByCourseId(long courseId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CourseResult> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first course result in the ordered set where courseId = &#63;.
	*
	* @param courseId the course ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching course result
	* @throws NoSuchCourseResultException if a matching course result could not be found
	*/
	public CourseResult findByCourseId_First(long courseId,
		com.liferay.portal.kernel.util.OrderByComparator<CourseResult> orderByComparator)
		throws NoSuchCourseResultException;

	/**
	* Returns the first course result in the ordered set where courseId = &#63;.
	*
	* @param courseId the course ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching course result, or <code>null</code> if a matching course result could not be found
	*/
	public CourseResult fetchByCourseId_First(long courseId,
		com.liferay.portal.kernel.util.OrderByComparator<CourseResult> orderByComparator);

	/**
	* Returns the last course result in the ordered set where courseId = &#63;.
	*
	* @param courseId the course ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching course result
	* @throws NoSuchCourseResultException if a matching course result could not be found
	*/
	public CourseResult findByCourseId_Last(long courseId,
		com.liferay.portal.kernel.util.OrderByComparator<CourseResult> orderByComparator)
		throws NoSuchCourseResultException;

	/**
	* Returns the last course result in the ordered set where courseId = &#63;.
	*
	* @param courseId the course ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching course result, or <code>null</code> if a matching course result could not be found
	*/
	public CourseResult fetchByCourseId_Last(long courseId,
		com.liferay.portal.kernel.util.OrderByComparator<CourseResult> orderByComparator);

	/**
	* Returns the course results before and after the current course result in the ordered set where courseId = &#63;.
	*
	* @param crId the primary key of the current course result
	* @param courseId the course ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next course result
	* @throws NoSuchCourseResultException if a course result with the primary key could not be found
	*/
	public CourseResult[] findByCourseId_PrevAndNext(long crId, long courseId,
		com.liferay.portal.kernel.util.OrderByComparator<CourseResult> orderByComparator)
		throws NoSuchCourseResultException;

	/**
	* Removes all the course results where courseId = &#63; from the database.
	*
	* @param courseId the course ID
	*/
	public void removeByCourseId(long courseId);

	/**
	* Returns the number of course results where courseId = &#63;.
	*
	* @param courseId the course ID
	* @return the number of matching course results
	*/
	public int countByCourseId(long courseId);

	/**
	* Returns the course result where courseId = &#63; and userId = &#63; or throws a {@link NoSuchCourseResultException} if it could not be found.
	*
	* @param courseId the course ID
	* @param userId the user ID
	* @return the matching course result
	* @throws NoSuchCourseResultException if a matching course result could not be found
	*/
	public CourseResult findByCourseIdUserId(long courseId, long userId)
		throws NoSuchCourseResultException;

	/**
	* Returns the course result where courseId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param courseId the course ID
	* @param userId the user ID
	* @return the matching course result, or <code>null</code> if a matching course result could not be found
	*/
	public CourseResult fetchByCourseIdUserId(long courseId, long userId);

	/**
	* Returns the course result where courseId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param courseId the course ID
	* @param userId the user ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching course result, or <code>null</code> if a matching course result could not be found
	*/
	public CourseResult fetchByCourseIdUserId(long courseId, long userId,
		boolean retrieveFromCache);

	/**
	* Removes the course result where courseId = &#63; and userId = &#63; from the database.
	*
	* @param courseId the course ID
	* @param userId the user ID
	* @return the course result that was removed
	*/
	public CourseResult removeByCourseIdUserId(long courseId, long userId)
		throws NoSuchCourseResultException;

	/**
	* Returns the number of course results where courseId = &#63; and userId = &#63;.
	*
	* @param courseId the course ID
	* @param userId the user ID
	* @return the number of matching course results
	*/
	public int countByCourseIdUserId(long courseId, long userId);

	/**
	* Returns all the course results where courseId = &#63; and passed = &#63;.
	*
	* @param courseId the course ID
	* @param passed the passed
	* @return the matching course results
	*/
	public java.util.List<CourseResult> findByCourseIdPassed(long courseId,
		boolean passed);

	/**
	* Returns a range of all the course results where courseId = &#63; and passed = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CourseResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param courseId the course ID
	* @param passed the passed
	* @param start the lower bound of the range of course results
	* @param end the upper bound of the range of course results (not inclusive)
	* @return the range of matching course results
	*/
	public java.util.List<CourseResult> findByCourseIdPassed(long courseId,
		boolean passed, int start, int end);

	/**
	* Returns an ordered range of all the course results where courseId = &#63; and passed = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CourseResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param courseId the course ID
	* @param passed the passed
	* @param start the lower bound of the range of course results
	* @param end the upper bound of the range of course results (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching course results
	*/
	public java.util.List<CourseResult> findByCourseIdPassed(long courseId,
		boolean passed, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CourseResult> orderByComparator);

	/**
	* Returns an ordered range of all the course results where courseId = &#63; and passed = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CourseResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param courseId the course ID
	* @param passed the passed
	* @param start the lower bound of the range of course results
	* @param end the upper bound of the range of course results (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching course results
	*/
	public java.util.List<CourseResult> findByCourseIdPassed(long courseId,
		boolean passed, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CourseResult> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first course result in the ordered set where courseId = &#63; and passed = &#63;.
	*
	* @param courseId the course ID
	* @param passed the passed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching course result
	* @throws NoSuchCourseResultException if a matching course result could not be found
	*/
	public CourseResult findByCourseIdPassed_First(long courseId,
		boolean passed,
		com.liferay.portal.kernel.util.OrderByComparator<CourseResult> orderByComparator)
		throws NoSuchCourseResultException;

	/**
	* Returns the first course result in the ordered set where courseId = &#63; and passed = &#63;.
	*
	* @param courseId the course ID
	* @param passed the passed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching course result, or <code>null</code> if a matching course result could not be found
	*/
	public CourseResult fetchByCourseIdPassed_First(long courseId,
		boolean passed,
		com.liferay.portal.kernel.util.OrderByComparator<CourseResult> orderByComparator);

	/**
	* Returns the last course result in the ordered set where courseId = &#63; and passed = &#63;.
	*
	* @param courseId the course ID
	* @param passed the passed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching course result
	* @throws NoSuchCourseResultException if a matching course result could not be found
	*/
	public CourseResult findByCourseIdPassed_Last(long courseId,
		boolean passed,
		com.liferay.portal.kernel.util.OrderByComparator<CourseResult> orderByComparator)
		throws NoSuchCourseResultException;

	/**
	* Returns the last course result in the ordered set where courseId = &#63; and passed = &#63;.
	*
	* @param courseId the course ID
	* @param passed the passed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching course result, or <code>null</code> if a matching course result could not be found
	*/
	public CourseResult fetchByCourseIdPassed_Last(long courseId,
		boolean passed,
		com.liferay.portal.kernel.util.OrderByComparator<CourseResult> orderByComparator);

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
	public CourseResult[] findByCourseIdPassed_PrevAndNext(long crId,
		long courseId, boolean passed,
		com.liferay.portal.kernel.util.OrderByComparator<CourseResult> orderByComparator)
		throws NoSuchCourseResultException;

	/**
	* Removes all the course results where courseId = &#63; and passed = &#63; from the database.
	*
	* @param courseId the course ID
	* @param passed the passed
	*/
	public void removeByCourseIdPassed(long courseId, boolean passed);

	/**
	* Returns the number of course results where courseId = &#63; and passed = &#63;.
	*
	* @param courseId the course ID
	* @param passed the passed
	* @return the number of matching course results
	*/
	public int countByCourseIdPassed(long courseId, boolean passed);

	/**
	* Returns all the course results where courseId = &#63; and passed = &#63;.
	*
	* @param courseId the course ID
	* @param passed the passed
	* @return the matching course results
	*/
	public java.util.List<CourseResult> findByFinished(long courseId,
		boolean passed);

	/**
	* Returns a range of all the course results where courseId = &#63; and passed = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CourseResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param courseId the course ID
	* @param passed the passed
	* @param start the lower bound of the range of course results
	* @param end the upper bound of the range of course results (not inclusive)
	* @return the range of matching course results
	*/
	public java.util.List<CourseResult> findByFinished(long courseId,
		boolean passed, int start, int end);

	/**
	* Returns an ordered range of all the course results where courseId = &#63; and passed = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CourseResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param courseId the course ID
	* @param passed the passed
	* @param start the lower bound of the range of course results
	* @param end the upper bound of the range of course results (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching course results
	*/
	public java.util.List<CourseResult> findByFinished(long courseId,
		boolean passed, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CourseResult> orderByComparator);

	/**
	* Returns an ordered range of all the course results where courseId = &#63; and passed = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CourseResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param courseId the course ID
	* @param passed the passed
	* @param start the lower bound of the range of course results
	* @param end the upper bound of the range of course results (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching course results
	*/
	public java.util.List<CourseResult> findByFinished(long courseId,
		boolean passed, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CourseResult> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first course result in the ordered set where courseId = &#63; and passed = &#63;.
	*
	* @param courseId the course ID
	* @param passed the passed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching course result
	* @throws NoSuchCourseResultException if a matching course result could not be found
	*/
	public CourseResult findByFinished_First(long courseId, boolean passed,
		com.liferay.portal.kernel.util.OrderByComparator<CourseResult> orderByComparator)
		throws NoSuchCourseResultException;

	/**
	* Returns the first course result in the ordered set where courseId = &#63; and passed = &#63;.
	*
	* @param courseId the course ID
	* @param passed the passed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching course result, or <code>null</code> if a matching course result could not be found
	*/
	public CourseResult fetchByFinished_First(long courseId, boolean passed,
		com.liferay.portal.kernel.util.OrderByComparator<CourseResult> orderByComparator);

	/**
	* Returns the last course result in the ordered set where courseId = &#63; and passed = &#63;.
	*
	* @param courseId the course ID
	* @param passed the passed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching course result
	* @throws NoSuchCourseResultException if a matching course result could not be found
	*/
	public CourseResult findByFinished_Last(long courseId, boolean passed,
		com.liferay.portal.kernel.util.OrderByComparator<CourseResult> orderByComparator)
		throws NoSuchCourseResultException;

	/**
	* Returns the last course result in the ordered set where courseId = &#63; and passed = &#63;.
	*
	* @param courseId the course ID
	* @param passed the passed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching course result, or <code>null</code> if a matching course result could not be found
	*/
	public CourseResult fetchByFinished_Last(long courseId, boolean passed,
		com.liferay.portal.kernel.util.OrderByComparator<CourseResult> orderByComparator);

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
	public CourseResult[] findByFinished_PrevAndNext(long crId, long courseId,
		boolean passed,
		com.liferay.portal.kernel.util.OrderByComparator<CourseResult> orderByComparator)
		throws NoSuchCourseResultException;

	/**
	* Removes all the course results where courseId = &#63; and passed = &#63; from the database.
	*
	* @param courseId the course ID
	* @param passed the passed
	*/
	public void removeByFinished(long courseId, boolean passed);

	/**
	* Returns the number of course results where courseId = &#63; and passed = &#63;.
	*
	* @param courseId the course ID
	* @param passed the passed
	* @return the number of matching course results
	*/
	public int countByFinished(long courseId, boolean passed);

	/**
	* Caches the course result in the entity cache if it is enabled.
	*
	* @param courseResult the course result
	*/
	public void cacheResult(CourseResult courseResult);

	/**
	* Caches the course results in the entity cache if it is enabled.
	*
	* @param courseResults the course results
	*/
	public void cacheResult(java.util.List<CourseResult> courseResults);

	/**
	* Creates a new course result with the primary key. Does not add the course result to the database.
	*
	* @param crId the primary key for the new course result
	* @return the new course result
	*/
	public CourseResult create(long crId);

	/**
	* Removes the course result with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param crId the primary key of the course result
	* @return the course result that was removed
	* @throws NoSuchCourseResultException if a course result with the primary key could not be found
	*/
	public CourseResult remove(long crId) throws NoSuchCourseResultException;

	public CourseResult updateImpl(CourseResult courseResult);

	/**
	* Returns the course result with the primary key or throws a {@link NoSuchCourseResultException} if it could not be found.
	*
	* @param crId the primary key of the course result
	* @return the course result
	* @throws NoSuchCourseResultException if a course result with the primary key could not be found
	*/
	public CourseResult findByPrimaryKey(long crId)
		throws NoSuchCourseResultException;

	/**
	* Returns the course result with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param crId the primary key of the course result
	* @return the course result, or <code>null</code> if a course result with the primary key could not be found
	*/
	public CourseResult fetchByPrimaryKey(long crId);

	@Override
	public java.util.Map<java.io.Serializable, CourseResult> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the course results.
	*
	* @return the course results
	*/
	public java.util.List<CourseResult> findAll();

	/**
	* Returns a range of all the course results.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CourseResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of course results
	* @param end the upper bound of the range of course results (not inclusive)
	* @return the range of course results
	*/
	public java.util.List<CourseResult> findAll(int start, int end);

	/**
	* Returns an ordered range of all the course results.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CourseResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of course results
	* @param end the upper bound of the range of course results (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of course results
	*/
	public java.util.List<CourseResult> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CourseResult> orderByComparator);

	/**
	* Returns an ordered range of all the course results.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CourseResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of course results
	* @param end the upper bound of the range of course results (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of course results
	*/
	public java.util.List<CourseResult> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CourseResult> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the course results from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of course results.
	*
	* @return the number of course results
	*/
	public int countAll();
}