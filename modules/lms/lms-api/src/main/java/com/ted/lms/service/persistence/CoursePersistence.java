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

import com.ted.lms.exception.NoSuchCourseException;
import com.ted.lms.model.Course;

/**
 * The persistence interface for the course service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ted.lms.service.persistence.impl.CoursePersistenceImpl
 * @see CourseUtil
 * @generated
 */
@ProviderType
public interface CoursePersistence extends BasePersistence<Course> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CourseUtil} to access the course persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the courses where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching courses
	*/
	public java.util.List<Course> findByUuid(String uuid);

	/**
	* Returns a range of all the courses where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CourseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of courses
	* @param end the upper bound of the range of courses (not inclusive)
	* @return the range of matching courses
	*/
	public java.util.List<Course> findByUuid(String uuid, int start, int end);

	/**
	* Returns an ordered range of all the courses where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CourseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of courses
	* @param end the upper bound of the range of courses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching courses
	*/
	public java.util.List<Course> findByUuid(String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Course> orderByComparator);

	/**
	* Returns an ordered range of all the courses where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CourseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of courses
	* @param end the upper bound of the range of courses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching courses
	*/
	public java.util.List<Course> findByUuid(String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Course> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first course in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching course
	* @throws NoSuchCourseException if a matching course could not be found
	*/
	public Course findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Course> orderByComparator)
		throws NoSuchCourseException;

	/**
	* Returns the first course in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching course, or <code>null</code> if a matching course could not be found
	*/
	public Course fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Course> orderByComparator);

	/**
	* Returns the last course in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching course
	* @throws NoSuchCourseException if a matching course could not be found
	*/
	public Course findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Course> orderByComparator)
		throws NoSuchCourseException;

	/**
	* Returns the last course in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching course, or <code>null</code> if a matching course could not be found
	*/
	public Course fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Course> orderByComparator);

	/**
	* Returns the courses before and after the current course in the ordered set where uuid = &#63;.
	*
	* @param courseId the primary key of the current course
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next course
	* @throws NoSuchCourseException if a course with the primary key could not be found
	*/
	public Course[] findByUuid_PrevAndNext(long courseId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Course> orderByComparator)
		throws NoSuchCourseException;

	/**
	* Removes all the courses where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of courses where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching courses
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the course where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchCourseException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching course
	* @throws NoSuchCourseException if a matching course could not be found
	*/
	public Course findByUUID_G(String uuid, long groupId)
		throws NoSuchCourseException;

	/**
	* Returns the course where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching course, or <code>null</code> if a matching course could not be found
	*/
	public Course fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the course where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching course, or <code>null</code> if a matching course could not be found
	*/
	public Course fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the course where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the course that was removed
	*/
	public Course removeByUUID_G(String uuid, long groupId)
		throws NoSuchCourseException;

	/**
	* Returns the number of courses where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching courses
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the courses where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching courses
	*/
	public java.util.List<Course> findByUuid_C(String uuid, long companyId);

	/**
	* Returns a range of all the courses where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CourseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of courses
	* @param end the upper bound of the range of courses (not inclusive)
	* @return the range of matching courses
	*/
	public java.util.List<Course> findByUuid_C(String uuid, long companyId,
		int start, int end);

	/**
	* Returns an ordered range of all the courses where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CourseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of courses
	* @param end the upper bound of the range of courses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching courses
	*/
	public java.util.List<Course> findByUuid_C(String uuid, long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Course> orderByComparator);

	/**
	* Returns an ordered range of all the courses where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CourseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of courses
	* @param end the upper bound of the range of courses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching courses
	*/
	public java.util.List<Course> findByUuid_C(String uuid, long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Course> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first course in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching course
	* @throws NoSuchCourseException if a matching course could not be found
	*/
	public Course findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Course> orderByComparator)
		throws NoSuchCourseException;

	/**
	* Returns the first course in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching course, or <code>null</code> if a matching course could not be found
	*/
	public Course fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Course> orderByComparator);

	/**
	* Returns the last course in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching course
	* @throws NoSuchCourseException if a matching course could not be found
	*/
	public Course findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Course> orderByComparator)
		throws NoSuchCourseException;

	/**
	* Returns the last course in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching course, or <code>null</code> if a matching course could not be found
	*/
	public Course fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Course> orderByComparator);

	/**
	* Returns the courses before and after the current course in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param courseId the primary key of the current course
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next course
	* @throws NoSuchCourseException if a course with the primary key could not be found
	*/
	public Course[] findByUuid_C_PrevAndNext(long courseId, String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Course> orderByComparator)
		throws NoSuchCourseException;

	/**
	* Removes all the courses where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of courses where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching courses
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns all the courses where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching courses
	*/
	public java.util.List<Course> findByCompanyId(long companyId);

	/**
	* Returns a range of all the courses where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CourseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of courses
	* @param end the upper bound of the range of courses (not inclusive)
	* @return the range of matching courses
	*/
	public java.util.List<Course> findByCompanyId(long companyId, int start,
		int end);

	/**
	* Returns an ordered range of all the courses where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CourseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of courses
	* @param end the upper bound of the range of courses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching courses
	*/
	public java.util.List<Course> findByCompanyId(long companyId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Course> orderByComparator);

	/**
	* Returns an ordered range of all the courses where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CourseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of courses
	* @param end the upper bound of the range of courses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching courses
	*/
	public java.util.List<Course> findByCompanyId(long companyId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Course> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first course in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching course
	* @throws NoSuchCourseException if a matching course could not be found
	*/
	public Course findByCompanyId_First(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Course> orderByComparator)
		throws NoSuchCourseException;

	/**
	* Returns the first course in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching course, or <code>null</code> if a matching course could not be found
	*/
	public Course fetchByCompanyId_First(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Course> orderByComparator);

	/**
	* Returns the last course in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching course
	* @throws NoSuchCourseException if a matching course could not be found
	*/
	public Course findByCompanyId_Last(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Course> orderByComparator)
		throws NoSuchCourseException;

	/**
	* Returns the last course in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching course, or <code>null</code> if a matching course could not be found
	*/
	public Course fetchByCompanyId_Last(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Course> orderByComparator);

	/**
	* Returns the courses before and after the current course in the ordered set where companyId = &#63;.
	*
	* @param courseId the primary key of the current course
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next course
	* @throws NoSuchCourseException if a course with the primary key could not be found
	*/
	public Course[] findByCompanyId_PrevAndNext(long courseId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Course> orderByComparator)
		throws NoSuchCourseException;

	/**
	* Removes all the courses where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	*/
	public void removeByCompanyId(long companyId);

	/**
	* Returns the number of courses where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching courses
	*/
	public int countByCompanyId(long companyId);

	/**
	* Returns all the courses where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching courses
	*/
	public java.util.List<Course> findByGroupId(long groupId);

	/**
	* Returns a range of all the courses where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CourseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of courses
	* @param end the upper bound of the range of courses (not inclusive)
	* @return the range of matching courses
	*/
	public java.util.List<Course> findByGroupId(long groupId, int start, int end);

	/**
	* Returns an ordered range of all the courses where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CourseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of courses
	* @param end the upper bound of the range of courses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching courses
	*/
	public java.util.List<Course> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Course> orderByComparator);

	/**
	* Returns an ordered range of all the courses where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CourseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of courses
	* @param end the upper bound of the range of courses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching courses
	*/
	public java.util.List<Course> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Course> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first course in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching course
	* @throws NoSuchCourseException if a matching course could not be found
	*/
	public Course findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Course> orderByComparator)
		throws NoSuchCourseException;

	/**
	* Returns the first course in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching course, or <code>null</code> if a matching course could not be found
	*/
	public Course fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Course> orderByComparator);

	/**
	* Returns the last course in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching course
	* @throws NoSuchCourseException if a matching course could not be found
	*/
	public Course findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Course> orderByComparator)
		throws NoSuchCourseException;

	/**
	* Returns the last course in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching course, or <code>null</code> if a matching course could not be found
	*/
	public Course fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Course> orderByComparator);

	/**
	* Returns the courses before and after the current course in the ordered set where groupId = &#63;.
	*
	* @param courseId the primary key of the current course
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next course
	* @throws NoSuchCourseException if a course with the primary key could not be found
	*/
	public Course[] findByGroupId_PrevAndNext(long courseId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Course> orderByComparator)
		throws NoSuchCourseException;

	/**
	* Returns all the courses that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching courses that the user has permission to view
	*/
	public java.util.List<Course> filterFindByGroupId(long groupId);

	/**
	* Returns a range of all the courses that the user has permission to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CourseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of courses
	* @param end the upper bound of the range of courses (not inclusive)
	* @return the range of matching courses that the user has permission to view
	*/
	public java.util.List<Course> filterFindByGroupId(long groupId, int start,
		int end);

	/**
	* Returns an ordered range of all the courses that the user has permissions to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CourseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of courses
	* @param end the upper bound of the range of courses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching courses that the user has permission to view
	*/
	public java.util.List<Course> filterFindByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Course> orderByComparator);

	/**
	* Returns the courses before and after the current course in the ordered set of courses that the user has permission to view where groupId = &#63;.
	*
	* @param courseId the primary key of the current course
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next course
	* @throws NoSuchCourseException if a course with the primary key could not be found
	*/
	public Course[] filterFindByGroupId_PrevAndNext(long courseId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Course> orderByComparator)
		throws NoSuchCourseException;

	/**
	* Removes all the courses where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of courses where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching courses
	*/
	public int countByGroupId(long groupId);

	/**
	* Returns the number of courses that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching courses that the user has permission to view
	*/
	public int filterCountByGroupId(long groupId);

	/**
	* Returns the course where groupCreatedId = &#63; or throws a {@link NoSuchCourseException} if it could not be found.
	*
	* @param groupCreatedId the group created ID
	* @return the matching course
	* @throws NoSuchCourseException if a matching course could not be found
	*/
	public Course findByGroupCreatedId(long groupCreatedId)
		throws NoSuchCourseException;

	/**
	* Returns the course where groupCreatedId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupCreatedId the group created ID
	* @return the matching course, or <code>null</code> if a matching course could not be found
	*/
	public Course fetchByGroupCreatedId(long groupCreatedId);

	/**
	* Returns the course where groupCreatedId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupCreatedId the group created ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching course, or <code>null</code> if a matching course could not be found
	*/
	public Course fetchByGroupCreatedId(long groupCreatedId,
		boolean retrieveFromCache);

	/**
	* Removes the course where groupCreatedId = &#63; from the database.
	*
	* @param groupCreatedId the group created ID
	* @return the course that was removed
	*/
	public Course removeByGroupCreatedId(long groupCreatedId)
		throws NoSuchCourseException;

	/**
	* Returns the number of courses where groupCreatedId = &#63;.
	*
	* @param groupCreatedId the group created ID
	* @return the number of matching courses
	*/
	public int countByGroupCreatedId(long groupCreatedId);

	/**
	* Returns all the courses where parentCourseId = &#63; and status &ne; &#63;.
	*
	* @param parentCourseId the parent course ID
	* @param status the status
	* @return the matching courses
	*/
	public java.util.List<Course> findByparentCourseId(long parentCourseId,
		int status);

	/**
	* Returns a range of all the courses where parentCourseId = &#63; and status &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CourseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentCourseId the parent course ID
	* @param status the status
	* @param start the lower bound of the range of courses
	* @param end the upper bound of the range of courses (not inclusive)
	* @return the range of matching courses
	*/
	public java.util.List<Course> findByparentCourseId(long parentCourseId,
		int status, int start, int end);

	/**
	* Returns an ordered range of all the courses where parentCourseId = &#63; and status &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CourseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentCourseId the parent course ID
	* @param status the status
	* @param start the lower bound of the range of courses
	* @param end the upper bound of the range of courses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching courses
	*/
	public java.util.List<Course> findByparentCourseId(long parentCourseId,
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Course> orderByComparator);

	/**
	* Returns an ordered range of all the courses where parentCourseId = &#63; and status &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CourseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentCourseId the parent course ID
	* @param status the status
	* @param start the lower bound of the range of courses
	* @param end the upper bound of the range of courses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching courses
	*/
	public java.util.List<Course> findByparentCourseId(long parentCourseId,
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Course> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first course in the ordered set where parentCourseId = &#63; and status &ne; &#63;.
	*
	* @param parentCourseId the parent course ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching course
	* @throws NoSuchCourseException if a matching course could not be found
	*/
	public Course findByparentCourseId_First(long parentCourseId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<Course> orderByComparator)
		throws NoSuchCourseException;

	/**
	* Returns the first course in the ordered set where parentCourseId = &#63; and status &ne; &#63;.
	*
	* @param parentCourseId the parent course ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching course, or <code>null</code> if a matching course could not be found
	*/
	public Course fetchByparentCourseId_First(long parentCourseId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<Course> orderByComparator);

	/**
	* Returns the last course in the ordered set where parentCourseId = &#63; and status &ne; &#63;.
	*
	* @param parentCourseId the parent course ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching course
	* @throws NoSuchCourseException if a matching course could not be found
	*/
	public Course findByparentCourseId_Last(long parentCourseId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<Course> orderByComparator)
		throws NoSuchCourseException;

	/**
	* Returns the last course in the ordered set where parentCourseId = &#63; and status &ne; &#63;.
	*
	* @param parentCourseId the parent course ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching course, or <code>null</code> if a matching course could not be found
	*/
	public Course fetchByparentCourseId_Last(long parentCourseId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<Course> orderByComparator);

	/**
	* Returns the courses before and after the current course in the ordered set where parentCourseId = &#63; and status &ne; &#63;.
	*
	* @param courseId the primary key of the current course
	* @param parentCourseId the parent course ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next course
	* @throws NoSuchCourseException if a course with the primary key could not be found
	*/
	public Course[] findByparentCourseId_PrevAndNext(long courseId,
		long parentCourseId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<Course> orderByComparator)
		throws NoSuchCourseException;

	/**
	* Removes all the courses where parentCourseId = &#63; and status &ne; &#63; from the database.
	*
	* @param parentCourseId the parent course ID
	* @param status the status
	*/
	public void removeByparentCourseId(long parentCourseId, int status);

	/**
	* Returns the number of courses where parentCourseId = &#63; and status &ne; &#63;.
	*
	* @param parentCourseId the parent course ID
	* @param status the status
	* @return the number of matching courses
	*/
	public int countByparentCourseId(long parentCourseId, int status);

	/**
	* Caches the course in the entity cache if it is enabled.
	*
	* @param course the course
	*/
	public void cacheResult(Course course);

	/**
	* Caches the courses in the entity cache if it is enabled.
	*
	* @param courses the courses
	*/
	public void cacheResult(java.util.List<Course> courses);

	/**
	* Creates a new course with the primary key. Does not add the course to the database.
	*
	* @param courseId the primary key for the new course
	* @return the new course
	*/
	public Course create(long courseId);

	/**
	* Removes the course with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param courseId the primary key of the course
	* @return the course that was removed
	* @throws NoSuchCourseException if a course with the primary key could not be found
	*/
	public Course remove(long courseId) throws NoSuchCourseException;

	public Course updateImpl(Course course);

	/**
	* Returns the course with the primary key or throws a {@link NoSuchCourseException} if it could not be found.
	*
	* @param courseId the primary key of the course
	* @return the course
	* @throws NoSuchCourseException if a course with the primary key could not be found
	*/
	public Course findByPrimaryKey(long courseId) throws NoSuchCourseException;

	/**
	* Returns the course with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param courseId the primary key of the course
	* @return the course, or <code>null</code> if a course with the primary key could not be found
	*/
	public Course fetchByPrimaryKey(long courseId);

	@Override
	public java.util.Map<java.io.Serializable, Course> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the courses.
	*
	* @return the courses
	*/
	public java.util.List<Course> findAll();

	/**
	* Returns a range of all the courses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CourseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of courses
	* @param end the upper bound of the range of courses (not inclusive)
	* @return the range of courses
	*/
	public java.util.List<Course> findAll(int start, int end);

	/**
	* Returns an ordered range of all the courses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CourseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of courses
	* @param end the upper bound of the range of courses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of courses
	*/
	public java.util.List<Course> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Course> orderByComparator);

	/**
	* Returns an ordered range of all the courses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CourseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of courses
	* @param end the upper bound of the range of courses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of courses
	*/
	public java.util.List<Course> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Course> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the courses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of courses.
	*
	* @return the number of courses
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}