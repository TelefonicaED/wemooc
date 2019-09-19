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

package com.ted.lms.service.persistence.impl;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;

import com.ted.lms.exception.NoSuchCourseTypeRelationException;
import com.ted.lms.model.CourseTypeRelation;
import com.ted.lms.model.impl.CourseTypeRelationImpl;
import com.ted.lms.model.impl.CourseTypeRelationModelImpl;
import com.ted.lms.service.persistence.CourseTypeRelationPK;
import com.ted.lms.service.persistence.CourseTypeRelationPersistence;
import com.ted.lms.service.persistence.impl.constants.LMSPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.annotation.versioning.ProviderType;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the course type relation service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = CourseTypeRelationPersistence.class)
@ProviderType
public class CourseTypeRelationPersistenceImpl
	extends BasePersistenceImpl<CourseTypeRelation>
	implements CourseTypeRelationPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CourseTypeRelationUtil</code> to access the course type relation persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CourseTypeRelationImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByCourseTypeIdClassNameId;
	private FinderPath
		_finderPathWithoutPaginationFindByCourseTypeIdClassNameId;
	private FinderPath _finderPathCountByCourseTypeIdClassNameId;

	/**
	 * Returns all the course type relations where courseTypeId = &#63; and classNameId = &#63;.
	 *
	 * @param courseTypeId the course type ID
	 * @param classNameId the class name ID
	 * @return the matching course type relations
	 */
	@Override
	public List<CourseTypeRelation> findByCourseTypeIdClassNameId(
		long courseTypeId, long classNameId) {

		return findByCourseTypeIdClassNameId(
			courseTypeId, classNameId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

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
	@Override
	public List<CourseTypeRelation> findByCourseTypeIdClassNameId(
		long courseTypeId, long classNameId, int start, int end) {

		return findByCourseTypeIdClassNameId(
			courseTypeId, classNameId, start, end, null);
	}

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
	@Override
	public List<CourseTypeRelation> findByCourseTypeIdClassNameId(
		long courseTypeId, long classNameId, int start, int end,
		OrderByComparator<CourseTypeRelation> orderByComparator) {

		return findByCourseTypeIdClassNameId(
			courseTypeId, classNameId, start, end, orderByComparator, true);
	}

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
	@Override
	public List<CourseTypeRelation> findByCourseTypeIdClassNameId(
		long courseTypeId, long classNameId, int start, int end,
		OrderByComparator<CourseTypeRelation> orderByComparator,
		boolean useFinderCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindByCourseTypeIdClassNameId;
				finderArgs = new Object[] {courseTypeId, classNameId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByCourseTypeIdClassNameId;
			finderArgs = new Object[] {
				courseTypeId, classNameId, start, end, orderByComparator
			};
		}

		List<CourseTypeRelation> list = null;

		if (useFinderCache) {
			list = (List<CourseTypeRelation>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CourseTypeRelation courseTypeRelation : list) {
					if ((courseTypeId !=
							courseTypeRelation.getCourseTypeId()) ||
						(classNameId != courseTypeRelation.getClassNameId())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					4 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_COURSETYPERELATION_WHERE);

			query.append(_FINDER_COLUMN_COURSETYPEIDCLASSNAMEID_COURSETYPEID_2);

			query.append(_FINDER_COLUMN_COURSETYPEIDCLASSNAMEID_CLASSNAMEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CourseTypeRelationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(courseTypeId);

				qPos.add(classNameId);

				if (!pagination) {
					list = (List<CourseTypeRelation>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CourseTypeRelation>)QueryUtil.list(
						q, getDialect(), start, end);
				}

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception e) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public CourseTypeRelation findByCourseTypeIdClassNameId_First(
			long courseTypeId, long classNameId,
			OrderByComparator<CourseTypeRelation> orderByComparator)
		throws NoSuchCourseTypeRelationException {

		CourseTypeRelation courseTypeRelation =
			fetchByCourseTypeIdClassNameId_First(
				courseTypeId, classNameId, orderByComparator);

		if (courseTypeRelation != null) {
			return courseTypeRelation;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("courseTypeId=");
		msg.append(courseTypeId);

		msg.append(", classNameId=");
		msg.append(classNameId);

		msg.append("}");

		throw new NoSuchCourseTypeRelationException(msg.toString());
	}

	/**
	 * Returns the first course type relation in the ordered set where courseTypeId = &#63; and classNameId = &#63;.
	 *
	 * @param courseTypeId the course type ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching course type relation, or <code>null</code> if a matching course type relation could not be found
	 */
	@Override
	public CourseTypeRelation fetchByCourseTypeIdClassNameId_First(
		long courseTypeId, long classNameId,
		OrderByComparator<CourseTypeRelation> orderByComparator) {

		List<CourseTypeRelation> list = findByCourseTypeIdClassNameId(
			courseTypeId, classNameId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public CourseTypeRelation findByCourseTypeIdClassNameId_Last(
			long courseTypeId, long classNameId,
			OrderByComparator<CourseTypeRelation> orderByComparator)
		throws NoSuchCourseTypeRelationException {

		CourseTypeRelation courseTypeRelation =
			fetchByCourseTypeIdClassNameId_Last(
				courseTypeId, classNameId, orderByComparator);

		if (courseTypeRelation != null) {
			return courseTypeRelation;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("courseTypeId=");
		msg.append(courseTypeId);

		msg.append(", classNameId=");
		msg.append(classNameId);

		msg.append("}");

		throw new NoSuchCourseTypeRelationException(msg.toString());
	}

	/**
	 * Returns the last course type relation in the ordered set where courseTypeId = &#63; and classNameId = &#63;.
	 *
	 * @param courseTypeId the course type ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching course type relation, or <code>null</code> if a matching course type relation could not be found
	 */
	@Override
	public CourseTypeRelation fetchByCourseTypeIdClassNameId_Last(
		long courseTypeId, long classNameId,
		OrderByComparator<CourseTypeRelation> orderByComparator) {

		int count = countByCourseTypeIdClassNameId(courseTypeId, classNameId);

		if (count == 0) {
			return null;
		}

		List<CourseTypeRelation> list = findByCourseTypeIdClassNameId(
			courseTypeId, classNameId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public CourseTypeRelation[] findByCourseTypeIdClassNameId_PrevAndNext(
			CourseTypeRelationPK courseTypeRelationPK, long courseTypeId,
			long classNameId,
			OrderByComparator<CourseTypeRelation> orderByComparator)
		throws NoSuchCourseTypeRelationException {

		CourseTypeRelation courseTypeRelation = findByPrimaryKey(
			courseTypeRelationPK);

		Session session = null;

		try {
			session = openSession();

			CourseTypeRelation[] array = new CourseTypeRelationImpl[3];

			array[0] = getByCourseTypeIdClassNameId_PrevAndNext(
				session, courseTypeRelation, courseTypeId, classNameId,
				orderByComparator, true);

			array[1] = courseTypeRelation;

			array[2] = getByCourseTypeIdClassNameId_PrevAndNext(
				session, courseTypeRelation, courseTypeId, classNameId,
				orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CourseTypeRelation getByCourseTypeIdClassNameId_PrevAndNext(
		Session session, CourseTypeRelation courseTypeRelation,
		long courseTypeId, long classNameId,
		OrderByComparator<CourseTypeRelation> orderByComparator,
		boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_COURSETYPERELATION_WHERE);

		query.append(_FINDER_COLUMN_COURSETYPEIDCLASSNAMEID_COURSETYPEID_2);

		query.append(_FINDER_COLUMN_COURSETYPEIDCLASSNAMEID_CLASSNAMEID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(CourseTypeRelationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(courseTypeId);

		qPos.add(classNameId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						courseTypeRelation)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CourseTypeRelation> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the course type relations where courseTypeId = &#63; and classNameId = &#63; from the database.
	 *
	 * @param courseTypeId the course type ID
	 * @param classNameId the class name ID
	 */
	@Override
	public void removeByCourseTypeIdClassNameId(
		long courseTypeId, long classNameId) {

		for (CourseTypeRelation courseTypeRelation :
				findByCourseTypeIdClassNameId(
					courseTypeId, classNameId, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(courseTypeRelation);
		}
	}

	/**
	 * Returns the number of course type relations where courseTypeId = &#63; and classNameId = &#63;.
	 *
	 * @param courseTypeId the course type ID
	 * @param classNameId the class name ID
	 * @return the number of matching course type relations
	 */
	@Override
	public int countByCourseTypeIdClassNameId(
		long courseTypeId, long classNameId) {

		FinderPath finderPath = _finderPathCountByCourseTypeIdClassNameId;

		Object[] finderArgs = new Object[] {courseTypeId, classNameId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_COURSETYPERELATION_WHERE);

			query.append(_FINDER_COLUMN_COURSETYPEIDCLASSNAMEID_COURSETYPEID_2);

			query.append(_FINDER_COLUMN_COURSETYPEIDCLASSNAMEID_CLASSNAMEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(courseTypeId);

				qPos.add(classNameId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String
		_FINDER_COLUMN_COURSETYPEIDCLASSNAMEID_COURSETYPEID_2 =
			"courseTypeRelation.id.courseTypeId = ? AND ";

	private static final String
		_FINDER_COLUMN_COURSETYPEIDCLASSNAMEID_CLASSNAMEID_2 =
			"courseTypeRelation.id.classNameId = ?";

	public CourseTypeRelationPersistenceImpl() {
		setModelClass(CourseTypeRelation.class);

		setModelImplClass(CourseTypeRelationImpl.class);
		setModelPKClass(CourseTypeRelationPK.class);
	}

	/**
	 * Caches the course type relation in the entity cache if it is enabled.
	 *
	 * @param courseTypeRelation the course type relation
	 */
	@Override
	public void cacheResult(CourseTypeRelation courseTypeRelation) {
		entityCache.putResult(
			entityCacheEnabled, CourseTypeRelationImpl.class,
			courseTypeRelation.getPrimaryKey(), courseTypeRelation);

		courseTypeRelation.resetOriginalValues();
	}

	/**
	 * Caches the course type relations in the entity cache if it is enabled.
	 *
	 * @param courseTypeRelations the course type relations
	 */
	@Override
	public void cacheResult(List<CourseTypeRelation> courseTypeRelations) {
		for (CourseTypeRelation courseTypeRelation : courseTypeRelations) {
			if (entityCache.getResult(
					entityCacheEnabled, CourseTypeRelationImpl.class,
					courseTypeRelation.getPrimaryKey()) == null) {

				cacheResult(courseTypeRelation);
			}
			else {
				courseTypeRelation.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all course type relations.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CourseTypeRelationImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the course type relation.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CourseTypeRelation courseTypeRelation) {
		entityCache.removeResult(
			entityCacheEnabled, CourseTypeRelationImpl.class,
			courseTypeRelation.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<CourseTypeRelation> courseTypeRelations) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CourseTypeRelation courseTypeRelation : courseTypeRelations) {
			entityCache.removeResult(
				entityCacheEnabled, CourseTypeRelationImpl.class,
				courseTypeRelation.getPrimaryKey());
		}
	}

	/**
	 * Creates a new course type relation with the primary key. Does not add the course type relation to the database.
	 *
	 * @param courseTypeRelationPK the primary key for the new course type relation
	 * @return the new course type relation
	 */
	@Override
	public CourseTypeRelation create(
		CourseTypeRelationPK courseTypeRelationPK) {

		CourseTypeRelation courseTypeRelation = new CourseTypeRelationImpl();

		courseTypeRelation.setNew(true);
		courseTypeRelation.setPrimaryKey(courseTypeRelationPK);

		return courseTypeRelation;
	}

	/**
	 * Removes the course type relation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param courseTypeRelationPK the primary key of the course type relation
	 * @return the course type relation that was removed
	 * @throws NoSuchCourseTypeRelationException if a course type relation with the primary key could not be found
	 */
	@Override
	public CourseTypeRelation remove(CourseTypeRelationPK courseTypeRelationPK)
		throws NoSuchCourseTypeRelationException {

		return remove((Serializable)courseTypeRelationPK);
	}

	/**
	 * Removes the course type relation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the course type relation
	 * @return the course type relation that was removed
	 * @throws NoSuchCourseTypeRelationException if a course type relation with the primary key could not be found
	 */
	@Override
	public CourseTypeRelation remove(Serializable primaryKey)
		throws NoSuchCourseTypeRelationException {

		Session session = null;

		try {
			session = openSession();

			CourseTypeRelation courseTypeRelation =
				(CourseTypeRelation)session.get(
					CourseTypeRelationImpl.class, primaryKey);

			if (courseTypeRelation == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCourseTypeRelationException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(courseTypeRelation);
		}
		catch (NoSuchCourseTypeRelationException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected CourseTypeRelation removeImpl(
		CourseTypeRelation courseTypeRelation) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(courseTypeRelation)) {
				courseTypeRelation = (CourseTypeRelation)session.get(
					CourseTypeRelationImpl.class,
					courseTypeRelation.getPrimaryKeyObj());
			}

			if (courseTypeRelation != null) {
				session.delete(courseTypeRelation);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (courseTypeRelation != null) {
			clearCache(courseTypeRelation);
		}

		return courseTypeRelation;
	}

	@Override
	public CourseTypeRelation updateImpl(
		CourseTypeRelation courseTypeRelation) {

		boolean isNew = courseTypeRelation.isNew();

		if (!(courseTypeRelation instanceof CourseTypeRelationModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(courseTypeRelation.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					courseTypeRelation);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in courseTypeRelation proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CourseTypeRelation implementation " +
					courseTypeRelation.getClass());
		}

		CourseTypeRelationModelImpl courseTypeRelationModelImpl =
			(CourseTypeRelationModelImpl)courseTypeRelation;

		Session session = null;

		try {
			session = openSession();

			if (courseTypeRelation.isNew()) {
				session.save(courseTypeRelation);

				courseTypeRelation.setNew(false);
			}
			else {
				courseTypeRelation = (CourseTypeRelation)session.merge(
					courseTypeRelation);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!_columnBitmaskEnabled) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {
				courseTypeRelationModelImpl.getCourseTypeId(),
				courseTypeRelationModelImpl.getClassNameId()
			};

			finderCache.removeResult(
				_finderPathCountByCourseTypeIdClassNameId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByCourseTypeIdClassNameId,
				args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((courseTypeRelationModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByCourseTypeIdClassNameId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					courseTypeRelationModelImpl.getOriginalCourseTypeId(),
					courseTypeRelationModelImpl.getOriginalClassNameId()
				};

				finderCache.removeResult(
					_finderPathCountByCourseTypeIdClassNameId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCourseTypeIdClassNameId,
					args);

				args = new Object[] {
					courseTypeRelationModelImpl.getCourseTypeId(),
					courseTypeRelationModelImpl.getClassNameId()
				};

				finderCache.removeResult(
					_finderPathCountByCourseTypeIdClassNameId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCourseTypeIdClassNameId,
					args);
			}
		}

		entityCache.putResult(
			entityCacheEnabled, CourseTypeRelationImpl.class,
			courseTypeRelation.getPrimaryKey(), courseTypeRelation, false);

		courseTypeRelation.resetOriginalValues();

		return courseTypeRelation;
	}

	/**
	 * Returns the course type relation with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the course type relation
	 * @return the course type relation
	 * @throws NoSuchCourseTypeRelationException if a course type relation with the primary key could not be found
	 */
	@Override
	public CourseTypeRelation findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCourseTypeRelationException {

		CourseTypeRelation courseTypeRelation = fetchByPrimaryKey(primaryKey);

		if (courseTypeRelation == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCourseTypeRelationException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return courseTypeRelation;
	}

	/**
	 * Returns the course type relation with the primary key or throws a <code>NoSuchCourseTypeRelationException</code> if it could not be found.
	 *
	 * @param courseTypeRelationPK the primary key of the course type relation
	 * @return the course type relation
	 * @throws NoSuchCourseTypeRelationException if a course type relation with the primary key could not be found
	 */
	@Override
	public CourseTypeRelation findByPrimaryKey(
			CourseTypeRelationPK courseTypeRelationPK)
		throws NoSuchCourseTypeRelationException {

		return findByPrimaryKey((Serializable)courseTypeRelationPK);
	}

	/**
	 * Returns the course type relation with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param courseTypeRelationPK the primary key of the course type relation
	 * @return the course type relation, or <code>null</code> if a course type relation with the primary key could not be found
	 */
	@Override
	public CourseTypeRelation fetchByPrimaryKey(
		CourseTypeRelationPK courseTypeRelationPK) {

		return fetchByPrimaryKey((Serializable)courseTypeRelationPK);
	}

	/**
	 * Returns all the course type relations.
	 *
	 * @return the course type relations
	 */
	@Override
	public List<CourseTypeRelation> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<CourseTypeRelation> findAll(int start, int end) {
		return findAll(start, end, null);
	}

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
	@Override
	public List<CourseTypeRelation> findAll(
		int start, int end,
		OrderByComparator<CourseTypeRelation> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

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
	@Override
	public List<CourseTypeRelation> findAll(
		int start, int end,
		OrderByComparator<CourseTypeRelation> orderByComparator,
		boolean useFinderCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<CourseTypeRelation> list = null;

		if (useFinderCache) {
			list = (List<CourseTypeRelation>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_COURSETYPERELATION);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_COURSETYPERELATION;

				if (pagination) {
					sql = sql.concat(CourseTypeRelationModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CourseTypeRelation>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CourseTypeRelation>)QueryUtil.list(
						q, getDialect(), start, end);
				}

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception e) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the course type relations from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CourseTypeRelation courseTypeRelation : findAll()) {
			remove(courseTypeRelation);
		}
	}

	/**
	 * Returns the number of course type relations.
	 *
	 * @return the number of course type relations
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_COURSETYPERELATION);

				count = (Long)q.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				finderCache.removeResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getCompoundPKColumnNames() {
		return _compoundPKColumnNames;
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "courseTypeRelationPK";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_COURSETYPERELATION;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return CourseTypeRelationModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the course type relation persistence.
	 */
	@Activate
	public void activate() {
		CourseTypeRelationModelImpl.setEntityCacheEnabled(entityCacheEnabled);
		CourseTypeRelationModelImpl.setFinderCacheEnabled(finderCacheEnabled);

		_finderPathWithPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			CourseTypeRelationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			CourseTypeRelationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByCourseTypeIdClassNameId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			CourseTypeRelationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCourseTypeIdClassNameId",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByCourseTypeIdClassNameId =
			new FinderPath(
				entityCacheEnabled, finderCacheEnabled,
				CourseTypeRelationImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"findByCourseTypeIdClassNameId",
				new String[] {Long.class.getName(), Long.class.getName()},
				CourseTypeRelationModelImpl.COURSETYPEID_COLUMN_BITMASK |
				CourseTypeRelationModelImpl.CLASSNAMEID_COLUMN_BITMASK);

		_finderPathCountByCourseTypeIdClassNameId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCourseTypeIdClassNameId",
			new String[] {Long.class.getName(), Long.class.getName()});
	}

	@Deactivate
	public void deactivate() {
		entityCache.removeCache(CourseTypeRelationImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	@Reference(
		target = LMSPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
		super.setConfiguration(configuration);

		_columnBitmaskEnabled = GetterUtil.getBoolean(
			configuration.get(
				"value.object.column.bitmask.enabled.com.ted.lms.model.CourseTypeRelation"),
			true);
	}

	@Override
	@Reference(
		target = LMSPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = LMSPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	private boolean _columnBitmaskEnabled;

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_COURSETYPERELATION =
		"SELECT courseTypeRelation FROM CourseTypeRelation courseTypeRelation";

	private static final String _SQL_SELECT_COURSETYPERELATION_WHERE =
		"SELECT courseTypeRelation FROM CourseTypeRelation courseTypeRelation WHERE ";

	private static final String _SQL_COUNT_COURSETYPERELATION =
		"SELECT COUNT(courseTypeRelation) FROM CourseTypeRelation courseTypeRelation";

	private static final String _SQL_COUNT_COURSETYPERELATION_WHERE =
		"SELECT COUNT(courseTypeRelation) FROM CourseTypeRelation courseTypeRelation WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "courseTypeRelation.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CourseTypeRelation exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No CourseTypeRelation exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CourseTypeRelationPersistenceImpl.class);

	private static final Set<String> _compoundPKColumnNames = SetUtil.fromArray(
		new String[] {"courseTypeId", "classNameId", "classPK"});

	static {
		try {
			Class.forName(LMSPersistenceConstants.class.getName());
		}
		catch (ClassNotFoundException cnfe) {
			throw new ExceptionInInitializerError(cnfe);
		}
	}

}