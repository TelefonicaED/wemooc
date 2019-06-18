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

import com.ted.lms.model.CourseType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the course type service. This utility wraps {@link com.ted.lms.service.persistence.impl.CourseTypePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CourseTypePersistence
 * @see com.ted.lms.service.persistence.impl.CourseTypePersistenceImpl
 * @generated
 */
@ProviderType
public class CourseTypeUtil {
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
	public static void clearCache(CourseType courseType) {
		getPersistence().clearCache(courseType);
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
	public static List<CourseType> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CourseType> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CourseType> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CourseType> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CourseType update(CourseType courseType) {
		return getPersistence().update(courseType);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CourseType update(CourseType courseType,
		ServiceContext serviceContext) {
		return getPersistence().update(courseType, serviceContext);
	}

	/**
	* Returns all the course types where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching course types
	*/
	public static List<CourseType> findByCompanyId(long companyId) {
		return getPersistence().findByCompanyId(companyId);
	}

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
	public static List<CourseType> findByCompanyId(long companyId, int start,
		int end) {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

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
	public static List<CourseType> findByCompanyId(long companyId, int start,
		int end, OrderByComparator<CourseType> orderByComparator) {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator);
	}

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
	public static List<CourseType> findByCompanyId(long companyId, int start,
		int end, OrderByComparator<CourseType> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first course type in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching course type
	* @throws NoSuchCourseTypeException if a matching course type could not be found
	*/
	public static CourseType findByCompanyId_First(long companyId,
		OrderByComparator<CourseType> orderByComparator)
		throws com.ted.lms.exception.NoSuchCourseTypeException {
		return getPersistence()
				   .findByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the first course type in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching course type, or <code>null</code> if a matching course type could not be found
	*/
	public static CourseType fetchByCompanyId_First(long companyId,
		OrderByComparator<CourseType> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the last course type in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching course type
	* @throws NoSuchCourseTypeException if a matching course type could not be found
	*/
	public static CourseType findByCompanyId_Last(long companyId,
		OrderByComparator<CourseType> orderByComparator)
		throws com.ted.lms.exception.NoSuchCourseTypeException {
		return getPersistence()
				   .findByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the last course type in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching course type, or <code>null</code> if a matching course type could not be found
	*/
	public static CourseType fetchByCompanyId_Last(long companyId,
		OrderByComparator<CourseType> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the course types before and after the current course type in the ordered set where companyId = &#63;.
	*
	* @param courseTypeId the primary key of the current course type
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next course type
	* @throws NoSuchCourseTypeException if a course type with the primary key could not be found
	*/
	public static CourseType[] findByCompanyId_PrevAndNext(long courseTypeId,
		long companyId, OrderByComparator<CourseType> orderByComparator)
		throws com.ted.lms.exception.NoSuchCourseTypeException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(courseTypeId, companyId,
			orderByComparator);
	}

	/**
	* Removes all the course types where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	*/
	public static void removeByCompanyId(long companyId) {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	* Returns the number of course types where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching course types
	*/
	public static int countByCompanyId(long companyId) {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	* Returns the course type where courseTypeId = &#63; or throws a {@link NoSuchCourseTypeException} if it could not be found.
	*
	* @param courseTypeId the course type ID
	* @return the matching course type
	* @throws NoSuchCourseTypeException if a matching course type could not be found
	*/
	public static CourseType findByCourseTypeId(long courseTypeId)
		throws com.ted.lms.exception.NoSuchCourseTypeException {
		return getPersistence().findByCourseTypeId(courseTypeId);
	}

	/**
	* Returns the course type where courseTypeId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param courseTypeId the course type ID
	* @return the matching course type, or <code>null</code> if a matching course type could not be found
	*/
	public static CourseType fetchByCourseTypeId(long courseTypeId) {
		return getPersistence().fetchByCourseTypeId(courseTypeId);
	}

	/**
	* Returns the course type where courseTypeId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param courseTypeId the course type ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching course type, or <code>null</code> if a matching course type could not be found
	*/
	public static CourseType fetchByCourseTypeId(long courseTypeId,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByCourseTypeId(courseTypeId, retrieveFromCache);
	}

	/**
	* Removes the course type where courseTypeId = &#63; from the database.
	*
	* @param courseTypeId the course type ID
	* @return the course type that was removed
	*/
	public static CourseType removeByCourseTypeId(long courseTypeId)
		throws com.ted.lms.exception.NoSuchCourseTypeException {
		return getPersistence().removeByCourseTypeId(courseTypeId);
	}

	/**
	* Returns the number of course types where courseTypeId = &#63;.
	*
	* @param courseTypeId the course type ID
	* @return the number of matching course types
	*/
	public static int countByCourseTypeId(long courseTypeId) {
		return getPersistence().countByCourseTypeId(courseTypeId);
	}

	/**
	* Caches the course type in the entity cache if it is enabled.
	*
	* @param courseType the course type
	*/
	public static void cacheResult(CourseType courseType) {
		getPersistence().cacheResult(courseType);
	}

	/**
	* Caches the course types in the entity cache if it is enabled.
	*
	* @param courseTypes the course types
	*/
	public static void cacheResult(List<CourseType> courseTypes) {
		getPersistence().cacheResult(courseTypes);
	}

	/**
	* Creates a new course type with the primary key. Does not add the course type to the database.
	*
	* @param courseTypeId the primary key for the new course type
	* @return the new course type
	*/
	public static CourseType create(long courseTypeId) {
		return getPersistence().create(courseTypeId);
	}

	/**
	* Removes the course type with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param courseTypeId the primary key of the course type
	* @return the course type that was removed
	* @throws NoSuchCourseTypeException if a course type with the primary key could not be found
	*/
	public static CourseType remove(long courseTypeId)
		throws com.ted.lms.exception.NoSuchCourseTypeException {
		return getPersistence().remove(courseTypeId);
	}

	public static CourseType updateImpl(CourseType courseType) {
		return getPersistence().updateImpl(courseType);
	}

	/**
	* Returns the course type with the primary key or throws a {@link NoSuchCourseTypeException} if it could not be found.
	*
	* @param courseTypeId the primary key of the course type
	* @return the course type
	* @throws NoSuchCourseTypeException if a course type with the primary key could not be found
	*/
	public static CourseType findByPrimaryKey(long courseTypeId)
		throws com.ted.lms.exception.NoSuchCourseTypeException {
		return getPersistence().findByPrimaryKey(courseTypeId);
	}

	/**
	* Returns the course type with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param courseTypeId the primary key of the course type
	* @return the course type, or <code>null</code> if a course type with the primary key could not be found
	*/
	public static CourseType fetchByPrimaryKey(long courseTypeId) {
		return getPersistence().fetchByPrimaryKey(courseTypeId);
	}

	public static java.util.Map<java.io.Serializable, CourseType> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the course types.
	*
	* @return the course types
	*/
	public static List<CourseType> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<CourseType> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<CourseType> findAll(int start, int end,
		OrderByComparator<CourseType> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<CourseType> findAll(int start, int end,
		OrderByComparator<CourseType> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the course types from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of course types.
	*
	* @return the number of course types
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static CourseTypePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CourseTypePersistence, CourseTypePersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CourseTypePersistence.class);

		ServiceTracker<CourseTypePersistence, CourseTypePersistence> serviceTracker =
			new ServiceTracker<CourseTypePersistence, CourseTypePersistence>(bundle.getBundleContext(),
				CourseTypePersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}