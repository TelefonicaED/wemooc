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

import com.ted.lms.exception.NoSuchCourseTypeRelationException;
import com.ted.lms.model.CourseTypeRelation;

import java.util.Set;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the course type relation service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CourseTypeRelationUtil
 * @generated
 */
@ProviderType
public interface CourseTypeRelationPersistence
	extends BasePersistence<CourseTypeRelation> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CourseTypeRelationUtil} to access the course type relation persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the course type relations where courseTypeId = &#63; and classNameId = &#63;.
	 *
	 * @param courseTypeId the course type ID
	 * @param classNameId the class name ID
	 * @return the matching course type relations
	 */
	public java.util.List<CourseTypeRelation> findByCourseTypeIdClassNameId(
		long courseTypeId, long classNameId);

	/**
	 * Returns a range of all the course type relations where courseTypeId = &#63; and classNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CourseTypeRelationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param courseTypeId the course type ID
	 * @param classNameId the class name ID
	 * @param start the lower bound of the range of course type relations
	 * @param end the upper bound of the range of course type relations (not inclusive)
	 * @return the range of matching course type relations
	 */
	public java.util.List<CourseTypeRelation> findByCourseTypeIdClassNameId(
		long courseTypeId, long classNameId, int start, int end);

	/**
	 * Returns an ordered range of all the course type relations where courseTypeId = &#63; and classNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CourseTypeRelationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param courseTypeId the course type ID
	 * @param classNameId the class name ID
	 * @param start the lower bound of the range of course type relations
	 * @param end the upper bound of the range of course type relations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching course type relations
	 */
	public java.util.List<CourseTypeRelation> findByCourseTypeIdClassNameId(
		long courseTypeId, long classNameId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CourseTypeRelation>
			orderByComparator);

	/**
	 * Returns an ordered range of all the course type relations where courseTypeId = &#63; and classNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CourseTypeRelationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param courseTypeId the course type ID
	 * @param classNameId the class name ID
	 * @param start the lower bound of the range of course type relations
	 * @param end the upper bound of the range of course type relations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching course type relations
	 */
	public java.util.List<CourseTypeRelation> findByCourseTypeIdClassNameId(
		long courseTypeId, long classNameId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CourseTypeRelation>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first course type relation in the ordered set where courseTypeId = &#63; and classNameId = &#63;.
	 *
	 * @param courseTypeId the course type ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching course type relation
	 * @throws NoSuchCourseTypeRelationException if a matching course type relation could not be found
	 */
	public CourseTypeRelation findByCourseTypeIdClassNameId_First(
			long courseTypeId, long classNameId,
			com.liferay.portal.kernel.util.OrderByComparator<CourseTypeRelation>
				orderByComparator)
		throws NoSuchCourseTypeRelationException;

	/**
	 * Returns the first course type relation in the ordered set where courseTypeId = &#63; and classNameId = &#63;.
	 *
	 * @param courseTypeId the course type ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching course type relation, or <code>null</code> if a matching course type relation could not be found
	 */
	public CourseTypeRelation fetchByCourseTypeIdClassNameId_First(
		long courseTypeId, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator<CourseTypeRelation>
			orderByComparator);

	/**
	 * Returns the last course type relation in the ordered set where courseTypeId = &#63; and classNameId = &#63;.
	 *
	 * @param courseTypeId the course type ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching course type relation
	 * @throws NoSuchCourseTypeRelationException if a matching course type relation could not be found
	 */
	public CourseTypeRelation findByCourseTypeIdClassNameId_Last(
			long courseTypeId, long classNameId,
			com.liferay.portal.kernel.util.OrderByComparator<CourseTypeRelation>
				orderByComparator)
		throws NoSuchCourseTypeRelationException;

	/**
	 * Returns the last course type relation in the ordered set where courseTypeId = &#63; and classNameId = &#63;.
	 *
	 * @param courseTypeId the course type ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching course type relation, or <code>null</code> if a matching course type relation could not be found
	 */
	public CourseTypeRelation fetchByCourseTypeIdClassNameId_Last(
		long courseTypeId, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator<CourseTypeRelation>
			orderByComparator);

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
	public CourseTypeRelation[] findByCourseTypeIdClassNameId_PrevAndNext(
			CourseTypeRelationPK courseTypeRelationPK, long courseTypeId,
			long classNameId,
			com.liferay.portal.kernel.util.OrderByComparator<CourseTypeRelation>
				orderByComparator)
		throws NoSuchCourseTypeRelationException;

	/**
	 * Removes all the course type relations where courseTypeId = &#63; and classNameId = &#63; from the database.
	 *
	 * @param courseTypeId the course type ID
	 * @param classNameId the class name ID
	 */
	public void removeByCourseTypeIdClassNameId(
		long courseTypeId, long classNameId);

	/**
	 * Returns the number of course type relations where courseTypeId = &#63; and classNameId = &#63;.
	 *
	 * @param courseTypeId the course type ID
	 * @param classNameId the class name ID
	 * @return the number of matching course type relations
	 */
	public int countByCourseTypeIdClassNameId(
		long courseTypeId, long classNameId);

	/**
	 * Caches the course type relation in the entity cache if it is enabled.
	 *
	 * @param courseTypeRelation the course type relation
	 */
	public void cacheResult(CourseTypeRelation courseTypeRelation);

	/**
	 * Caches the course type relations in the entity cache if it is enabled.
	 *
	 * @param courseTypeRelations the course type relations
	 */
	public void cacheResult(
		java.util.List<CourseTypeRelation> courseTypeRelations);

	/**
	 * Creates a new course type relation with the primary key. Does not add the course type relation to the database.
	 *
	 * @param courseTypeRelationPK the primary key for the new course type relation
	 * @return the new course type relation
	 */
	public CourseTypeRelation create(CourseTypeRelationPK courseTypeRelationPK);

	/**
	 * Removes the course type relation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param courseTypeRelationPK the primary key of the course type relation
	 * @return the course type relation that was removed
	 * @throws NoSuchCourseTypeRelationException if a course type relation with the primary key could not be found
	 */
	public CourseTypeRelation remove(CourseTypeRelationPK courseTypeRelationPK)
		throws NoSuchCourseTypeRelationException;

	public CourseTypeRelation updateImpl(CourseTypeRelation courseTypeRelation);

	/**
	 * Returns the course type relation with the primary key or throws a <code>NoSuchCourseTypeRelationException</code> if it could not be found.
	 *
	 * @param courseTypeRelationPK the primary key of the course type relation
	 * @return the course type relation
	 * @throws NoSuchCourseTypeRelationException if a course type relation with the primary key could not be found
	 */
	public CourseTypeRelation findByPrimaryKey(
			CourseTypeRelationPK courseTypeRelationPK)
		throws NoSuchCourseTypeRelationException;

	/**
	 * Returns the course type relation with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param courseTypeRelationPK the primary key of the course type relation
	 * @return the course type relation, or <code>null</code> if a course type relation with the primary key could not be found
	 */
	public CourseTypeRelation fetchByPrimaryKey(
		CourseTypeRelationPK courseTypeRelationPK);

	/**
	 * Returns all the course type relations.
	 *
	 * @return the course type relations
	 */
	public java.util.List<CourseTypeRelation> findAll();

	/**
	 * Returns a range of all the course type relations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CourseTypeRelationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of course type relations
	 * @param end the upper bound of the range of course type relations (not inclusive)
	 * @return the range of course type relations
	 */
	public java.util.List<CourseTypeRelation> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the course type relations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CourseTypeRelationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of course type relations
	 * @param end the upper bound of the range of course type relations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of course type relations
	 */
	public java.util.List<CourseTypeRelation> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CourseTypeRelation>
			orderByComparator);

	/**
	 * Returns an ordered range of all the course type relations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CourseTypeRelationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of course type relations
	 * @param end the upper bound of the range of course type relations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of course type relations
	 */
	public java.util.List<CourseTypeRelation> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CourseTypeRelation>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the course type relations from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of course type relations.
	 *
	 * @return the number of course type relations
	 */
	public int countAll();

	public Set<String> getCompoundPKColumnNames();

}