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

import com.ted.lms.model.CourseTypeRelation;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the course type relation service. This utility wraps {@link com.ted.lms.service.persistence.impl.CourseTypeRelationPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CourseTypeRelationPersistence
 * @see com.ted.lms.service.persistence.impl.CourseTypeRelationPersistenceImpl
 * @generated
 */
@ProviderType
public class CourseTypeRelationUtil {
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
	public static void clearCache(CourseTypeRelation courseTypeRelation) {
		getPersistence().clearCache(courseTypeRelation);
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
	public static List<CourseTypeRelation> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CourseTypeRelation> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CourseTypeRelation> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CourseTypeRelation> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CourseTypeRelation update(
		CourseTypeRelation courseTypeRelation) {
		return getPersistence().update(courseTypeRelation);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CourseTypeRelation update(
		CourseTypeRelation courseTypeRelation, ServiceContext serviceContext) {
		return getPersistence().update(courseTypeRelation, serviceContext);
	}

	/**
	* Returns all the course type relations where courseTypeId = &#63; and classNameId = &#63;.
	*
	* @param courseTypeId the course type ID
	* @param classNameId the class name ID
	* @return the matching course type relations
	*/
	public static List<CourseTypeRelation> findByCourseTypeIdClassNameId(
		long courseTypeId, long classNameId) {
		return getPersistence()
				   .findByCourseTypeIdClassNameId(courseTypeId, classNameId);
	}

	/**
	* Returns a range of all the course type relations where courseTypeId = &#63; and classNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CourseTypeRelationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param courseTypeId the course type ID
	* @param classNameId the class name ID
	* @param start the lower bound of the range of course type relations
	* @param end the upper bound of the range of course type relations (not inclusive)
	* @return the range of matching course type relations
	*/
	public static List<CourseTypeRelation> findByCourseTypeIdClassNameId(
		long courseTypeId, long classNameId, int start, int end) {
		return getPersistence()
				   .findByCourseTypeIdClassNameId(courseTypeId, classNameId,
			start, end);
	}

	/**
	* Returns an ordered range of all the course type relations where courseTypeId = &#63; and classNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CourseTypeRelationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param courseTypeId the course type ID
	* @param classNameId the class name ID
	* @param start the lower bound of the range of course type relations
	* @param end the upper bound of the range of course type relations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching course type relations
	*/
	public static List<CourseTypeRelation> findByCourseTypeIdClassNameId(
		long courseTypeId, long classNameId, int start, int end,
		OrderByComparator<CourseTypeRelation> orderByComparator) {
		return getPersistence()
				   .findByCourseTypeIdClassNameId(courseTypeId, classNameId,
			start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the course type relations where courseTypeId = &#63; and classNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CourseTypeRelationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param courseTypeId the course type ID
	* @param classNameId the class name ID
	* @param start the lower bound of the range of course type relations
	* @param end the upper bound of the range of course type relations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching course type relations
	*/
	public static List<CourseTypeRelation> findByCourseTypeIdClassNameId(
		long courseTypeId, long classNameId, int start, int end,
		OrderByComparator<CourseTypeRelation> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCourseTypeIdClassNameId(courseTypeId, classNameId,
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first course type relation in the ordered set where courseTypeId = &#63; and classNameId = &#63;.
	*
	* @param courseTypeId the course type ID
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching course type relation
	* @throws NoSuchCourseTypeRelationException if a matching course type relation could not be found
	*/
	public static CourseTypeRelation findByCourseTypeIdClassNameId_First(
		long courseTypeId, long classNameId,
		OrderByComparator<CourseTypeRelation> orderByComparator)
		throws com.ted.lms.exception.NoSuchCourseTypeRelationException {
		return getPersistence()
				   .findByCourseTypeIdClassNameId_First(courseTypeId,
			classNameId, orderByComparator);
	}

	/**
	* Returns the first course type relation in the ordered set where courseTypeId = &#63; and classNameId = &#63;.
	*
	* @param courseTypeId the course type ID
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching course type relation, or <code>null</code> if a matching course type relation could not be found
	*/
	public static CourseTypeRelation fetchByCourseTypeIdClassNameId_First(
		long courseTypeId, long classNameId,
		OrderByComparator<CourseTypeRelation> orderByComparator) {
		return getPersistence()
				   .fetchByCourseTypeIdClassNameId_First(courseTypeId,
			classNameId, orderByComparator);
	}

	/**
	* Returns the last course type relation in the ordered set where courseTypeId = &#63; and classNameId = &#63;.
	*
	* @param courseTypeId the course type ID
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching course type relation
	* @throws NoSuchCourseTypeRelationException if a matching course type relation could not be found
	*/
	public static CourseTypeRelation findByCourseTypeIdClassNameId_Last(
		long courseTypeId, long classNameId,
		OrderByComparator<CourseTypeRelation> orderByComparator)
		throws com.ted.lms.exception.NoSuchCourseTypeRelationException {
		return getPersistence()
				   .findByCourseTypeIdClassNameId_Last(courseTypeId,
			classNameId, orderByComparator);
	}

	/**
	* Returns the last course type relation in the ordered set where courseTypeId = &#63; and classNameId = &#63;.
	*
	* @param courseTypeId the course type ID
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching course type relation, or <code>null</code> if a matching course type relation could not be found
	*/
	public static CourseTypeRelation fetchByCourseTypeIdClassNameId_Last(
		long courseTypeId, long classNameId,
		OrderByComparator<CourseTypeRelation> orderByComparator) {
		return getPersistence()
				   .fetchByCourseTypeIdClassNameId_Last(courseTypeId,
			classNameId, orderByComparator);
	}

	/**
	* Returns the course type relations before and after the current course type relation in the ordered set where courseTypeId = &#63; and classNameId = &#63;.
	*
	* @param courseTypeRelationPK the primary key of the current course type relation
	* @param courseTypeId the course type ID
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next course type relation
	* @throws NoSuchCourseTypeRelationException if a course type relation with the primary key could not be found
	*/
	public static CourseTypeRelation[] findByCourseTypeIdClassNameId_PrevAndNext(
		CourseTypeRelationPK courseTypeRelationPK, long courseTypeId,
		long classNameId,
		OrderByComparator<CourseTypeRelation> orderByComparator)
		throws com.ted.lms.exception.NoSuchCourseTypeRelationException {
		return getPersistence()
				   .findByCourseTypeIdClassNameId_PrevAndNext(courseTypeRelationPK,
			courseTypeId, classNameId, orderByComparator);
	}

	/**
	* Removes all the course type relations where courseTypeId = &#63; and classNameId = &#63; from the database.
	*
	* @param courseTypeId the course type ID
	* @param classNameId the class name ID
	*/
	public static void removeByCourseTypeIdClassNameId(long courseTypeId,
		long classNameId) {
		getPersistence()
			.removeByCourseTypeIdClassNameId(courseTypeId, classNameId);
	}

	/**
	* Returns the number of course type relations where courseTypeId = &#63; and classNameId = &#63;.
	*
	* @param courseTypeId the course type ID
	* @param classNameId the class name ID
	* @return the number of matching course type relations
	*/
	public static int countByCourseTypeIdClassNameId(long courseTypeId,
		long classNameId) {
		return getPersistence()
				   .countByCourseTypeIdClassNameId(courseTypeId, classNameId);
	}

	/**
	* Caches the course type relation in the entity cache if it is enabled.
	*
	* @param courseTypeRelation the course type relation
	*/
	public static void cacheResult(CourseTypeRelation courseTypeRelation) {
		getPersistence().cacheResult(courseTypeRelation);
	}

	/**
	* Caches the course type relations in the entity cache if it is enabled.
	*
	* @param courseTypeRelations the course type relations
	*/
	public static void cacheResult(List<CourseTypeRelation> courseTypeRelations) {
		getPersistence().cacheResult(courseTypeRelations);
	}

	/**
	* Creates a new course type relation with the primary key. Does not add the course type relation to the database.
	*
	* @param courseTypeRelationPK the primary key for the new course type relation
	* @return the new course type relation
	*/
	public static CourseTypeRelation create(
		CourseTypeRelationPK courseTypeRelationPK) {
		return getPersistence().create(courseTypeRelationPK);
	}

	/**
	* Removes the course type relation with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param courseTypeRelationPK the primary key of the course type relation
	* @return the course type relation that was removed
	* @throws NoSuchCourseTypeRelationException if a course type relation with the primary key could not be found
	*/
	public static CourseTypeRelation remove(
		CourseTypeRelationPK courseTypeRelationPK)
		throws com.ted.lms.exception.NoSuchCourseTypeRelationException {
		return getPersistence().remove(courseTypeRelationPK);
	}

	public static CourseTypeRelation updateImpl(
		CourseTypeRelation courseTypeRelation) {
		return getPersistence().updateImpl(courseTypeRelation);
	}

	/**
	* Returns the course type relation with the primary key or throws a {@link NoSuchCourseTypeRelationException} if it could not be found.
	*
	* @param courseTypeRelationPK the primary key of the course type relation
	* @return the course type relation
	* @throws NoSuchCourseTypeRelationException if a course type relation with the primary key could not be found
	*/
	public static CourseTypeRelation findByPrimaryKey(
		CourseTypeRelationPK courseTypeRelationPK)
		throws com.ted.lms.exception.NoSuchCourseTypeRelationException {
		return getPersistence().findByPrimaryKey(courseTypeRelationPK);
	}

	/**
	* Returns the course type relation with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param courseTypeRelationPK the primary key of the course type relation
	* @return the course type relation, or <code>null</code> if a course type relation with the primary key could not be found
	*/
	public static CourseTypeRelation fetchByPrimaryKey(
		CourseTypeRelationPK courseTypeRelationPK) {
		return getPersistence().fetchByPrimaryKey(courseTypeRelationPK);
	}

	public static java.util.Map<java.io.Serializable, CourseTypeRelation> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the course type relations.
	*
	* @return the course type relations
	*/
	public static List<CourseTypeRelation> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the course type relations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CourseTypeRelationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of course type relations
	* @param end the upper bound of the range of course type relations (not inclusive)
	* @return the range of course type relations
	*/
	public static List<CourseTypeRelation> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the course type relations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CourseTypeRelationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of course type relations
	* @param end the upper bound of the range of course type relations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of course type relations
	*/
	public static List<CourseTypeRelation> findAll(int start, int end,
		OrderByComparator<CourseTypeRelation> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the course type relations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CourseTypeRelationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of course type relations
	* @param end the upper bound of the range of course type relations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of course type relations
	*/
	public static List<CourseTypeRelation> findAll(int start, int end,
		OrderByComparator<CourseTypeRelation> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the course type relations from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of course type relations.
	*
	* @return the number of course type relations
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getCompoundPKColumnNames() {
		return getPersistence().getCompoundPKColumnNames();
	}

	public static CourseTypeRelationPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CourseTypeRelationPersistence, CourseTypeRelationPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CourseTypeRelationPersistence.class);

		ServiceTracker<CourseTypeRelationPersistence, CourseTypeRelationPersistence> serviceTracker =
			new ServiceTracker<CourseTypeRelationPersistence, CourseTypeRelationPersistence>(bundle.getBundleContext(),
				CourseTypeRelationPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}