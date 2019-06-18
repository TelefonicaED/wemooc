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

import com.ted.lms.exception.NoSuchCourseTypeException;
import com.ted.lms.model.CourseType;

/**
 * The persistence interface for the course type service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ted.lms.service.persistence.impl.CourseTypePersistenceImpl
 * @see CourseTypeUtil
 * @generated
 */
@ProviderType
public interface CourseTypePersistence extends BasePersistence<CourseType> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CourseTypeUtil} to access the course type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the course types where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching course types
	*/
	public java.util.List<CourseType> findByCompanyId(long companyId);

	/**
	* Returns a range of all the course types where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CourseTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of course types
	* @param end the upper bound of the range of course types (not inclusive)
	* @return the range of matching course types
	*/
	public java.util.List<CourseType> findByCompanyId(long companyId,
		int start, int end);

	/**
	* Returns an ordered range of all the course types where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CourseTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of course types
	* @param end the upper bound of the range of course types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching course types
	*/
	public java.util.List<CourseType> findByCompanyId(long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CourseType> orderByComparator);

	/**
	* Returns an ordered range of all the course types where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CourseTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of course types
	* @param end the upper bound of the range of course types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching course types
	*/
	public java.util.List<CourseType> findByCompanyId(long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CourseType> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first course type in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching course type
	* @throws NoSuchCourseTypeException if a matching course type could not be found
	*/
	public CourseType findByCompanyId_First(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CourseType> orderByComparator)
		throws NoSuchCourseTypeException;

	/**
	* Returns the first course type in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching course type, or <code>null</code> if a matching course type could not be found
	*/
	public CourseType fetchByCompanyId_First(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CourseType> orderByComparator);

	/**
	* Returns the last course type in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching course type
	* @throws NoSuchCourseTypeException if a matching course type could not be found
	*/
	public CourseType findByCompanyId_Last(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CourseType> orderByComparator)
		throws NoSuchCourseTypeException;

	/**
	* Returns the last course type in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching course type, or <code>null</code> if a matching course type could not be found
	*/
	public CourseType fetchByCompanyId_Last(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CourseType> orderByComparator);

	/**
	* Returns the course types before and after the current course type in the ordered set where companyId = &#63;.
	*
	* @param courseTypeId the primary key of the current course type
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next course type
	* @throws NoSuchCourseTypeException if a course type with the primary key could not be found
	*/
	public CourseType[] findByCompanyId_PrevAndNext(long courseTypeId,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CourseType> orderByComparator)
		throws NoSuchCourseTypeException;

	/**
	* Removes all the course types where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	*/
	public void removeByCompanyId(long companyId);

	/**
	* Returns the number of course types where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching course types
	*/
	public int countByCompanyId(long companyId);

	/**
	* Returns the course type where courseTypeId = &#63; or throws a {@link NoSuchCourseTypeException} if it could not be found.
	*
	* @param courseTypeId the course type ID
	* @return the matching course type
	* @throws NoSuchCourseTypeException if a matching course type could not be found
	*/
	public CourseType findByCourseTypeId(long courseTypeId)
		throws NoSuchCourseTypeException;

	/**
	* Returns the course type where courseTypeId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param courseTypeId the course type ID
	* @return the matching course type, or <code>null</code> if a matching course type could not be found
	*/
	public CourseType fetchByCourseTypeId(long courseTypeId);

	/**
	* Returns the course type where courseTypeId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param courseTypeId the course type ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching course type, or <code>null</code> if a matching course type could not be found
	*/
	public CourseType fetchByCourseTypeId(long courseTypeId,
		boolean retrieveFromCache);

	/**
	* Removes the course type where courseTypeId = &#63; from the database.
	*
	* @param courseTypeId the course type ID
	* @return the course type that was removed
	*/
	public CourseType removeByCourseTypeId(long courseTypeId)
		throws NoSuchCourseTypeException;

	/**
	* Returns the number of course types where courseTypeId = &#63;.
	*
	* @param courseTypeId the course type ID
	* @return the number of matching course types
	*/
	public int countByCourseTypeId(long courseTypeId);

	/**
	* Caches the course type in the entity cache if it is enabled.
	*
	* @param courseType the course type
	*/
	public void cacheResult(CourseType courseType);

	/**
	* Caches the course types in the entity cache if it is enabled.
	*
	* @param courseTypes the course types
	*/
	public void cacheResult(java.util.List<CourseType> courseTypes);

	/**
	* Creates a new course type with the primary key. Does not add the course type to the database.
	*
	* @param courseTypeId the primary key for the new course type
	* @return the new course type
	*/
	public CourseType create(long courseTypeId);

	/**
	* Removes the course type with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param courseTypeId the primary key of the course type
	* @return the course type that was removed
	* @throws NoSuchCourseTypeException if a course type with the primary key could not be found
	*/
	public CourseType remove(long courseTypeId)
		throws NoSuchCourseTypeException;

	public CourseType updateImpl(CourseType courseType);

	/**
	* Returns the course type with the primary key or throws a {@link NoSuchCourseTypeException} if it could not be found.
	*
	* @param courseTypeId the primary key of the course type
	* @return the course type
	* @throws NoSuchCourseTypeException if a course type with the primary key could not be found
	*/
	public CourseType findByPrimaryKey(long courseTypeId)
		throws NoSuchCourseTypeException;

	/**
	* Returns the course type with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param courseTypeId the primary key of the course type
	* @return the course type, or <code>null</code> if a course type with the primary key could not be found
	*/
	public CourseType fetchByPrimaryKey(long courseTypeId);

	@Override
	public java.util.Map<java.io.Serializable, CourseType> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the course types.
	*
	* @return the course types
	*/
	public java.util.List<CourseType> findAll();

	/**
	* Returns a range of all the course types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CourseTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of course types
	* @param end the upper bound of the range of course types (not inclusive)
	* @return the range of course types
	*/
	public java.util.List<CourseType> findAll(int start, int end);

	/**
	* Returns an ordered range of all the course types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CourseTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of course types
	* @param end the upper bound of the range of course types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of course types
	*/
	public java.util.List<CourseType> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CourseType> orderByComparator);

	/**
	* Returns an ordered range of all the course types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CourseTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of course types
	* @param end the upper bound of the range of course types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of course types
	*/
	public java.util.List<CourseType> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CourseType> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the course types from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of course types.
	*
	* @return the number of course types
	*/
	public int countAll();
}