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
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;

import com.ted.lms.exception.NoSuchCourseTypeException;
import com.ted.lms.model.CourseType;
import com.ted.lms.model.impl.CourseTypeImpl;
import com.ted.lms.model.impl.CourseTypeModelImpl;
import com.ted.lms.service.persistence.CourseTypePersistence;
import com.ted.lms.service.persistence.impl.constants.LMSPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.osgi.annotation.versioning.ProviderType;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the course type service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = CourseTypePersistence.class)
@ProviderType
public class CourseTypePersistenceImpl
	extends BasePersistenceImpl<CourseType> implements CourseTypePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CourseTypeUtil</code> to access the course type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CourseTypeImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByCompanyId;
	private FinderPath _finderPathWithoutPaginationFindByCompanyId;
	private FinderPath _finderPathCountByCompanyId;

	/**
	 * Returns all the course types where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching course types
	 */
	@Override
	public List<CourseType> findByCompanyId(long companyId) {
		return findByCompanyId(
			companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the course types where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CourseTypeModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of course types
	 * @param end the upper bound of the range of course types (not inclusive)
	 * @return the range of matching course types
	 */
	@Override
	public List<CourseType> findByCompanyId(
		long companyId, int start, int end) {

		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the course types where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CourseTypeModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of course types
	 * @param end the upper bound of the range of course types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching course types
	 */
	@Override
	public List<CourseType> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<CourseType> orderByComparator) {

		return findByCompanyId(companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the course types where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CourseTypeModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of course types
	 * @param end the upper bound of the range of course types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching course types
	 */
	@Override
	public List<CourseType> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<CourseType> orderByComparator,
		boolean useFinderCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByCompanyId;
				finderArgs = new Object[] {companyId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByCompanyId;
			finderArgs = new Object[] {
				companyId, start, end, orderByComparator
			};
		}

		List<CourseType> list = null;

		if (useFinderCache) {
			list = (List<CourseType>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CourseType courseType : list) {
					if ((companyId != courseType.getCompanyId())) {
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
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_COURSETYPE_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CourseTypeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (!pagination) {
					list = (List<CourseType>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CourseType>)QueryUtil.list(
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
	 * Returns the first course type in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching course type
	 * @throws NoSuchCourseTypeException if a matching course type could not be found
	 */
	@Override
	public CourseType findByCompanyId_First(
			long companyId, OrderByComparator<CourseType> orderByComparator)
		throws NoSuchCourseTypeException {

		CourseType courseType = fetchByCompanyId_First(
			companyId, orderByComparator);

		if (courseType != null) {
			return courseType;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchCourseTypeException(msg.toString());
	}

	/**
	 * Returns the first course type in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching course type, or <code>null</code> if a matching course type could not be found
	 */
	@Override
	public CourseType fetchByCompanyId_First(
		long companyId, OrderByComparator<CourseType> orderByComparator) {

		List<CourseType> list = findByCompanyId(
			companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last course type in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching course type
	 * @throws NoSuchCourseTypeException if a matching course type could not be found
	 */
	@Override
	public CourseType findByCompanyId_Last(
			long companyId, OrderByComparator<CourseType> orderByComparator)
		throws NoSuchCourseTypeException {

		CourseType courseType = fetchByCompanyId_Last(
			companyId, orderByComparator);

		if (courseType != null) {
			return courseType;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchCourseTypeException(msg.toString());
	}

	/**
	 * Returns the last course type in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching course type, or <code>null</code> if a matching course type could not be found
	 */
	@Override
	public CourseType fetchByCompanyId_Last(
		long companyId, OrderByComparator<CourseType> orderByComparator) {

		int count = countByCompanyId(companyId);

		if (count == 0) {
			return null;
		}

		List<CourseType> list = findByCompanyId(
			companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public CourseType[] findByCompanyId_PrevAndNext(
			long courseTypeId, long companyId,
			OrderByComparator<CourseType> orderByComparator)
		throws NoSuchCourseTypeException {

		CourseType courseType = findByPrimaryKey(courseTypeId);

		Session session = null;

		try {
			session = openSession();

			CourseType[] array = new CourseTypeImpl[3];

			array[0] = getByCompanyId_PrevAndNext(
				session, courseType, companyId, orderByComparator, true);

			array[1] = courseType;

			array[2] = getByCompanyId_PrevAndNext(
				session, courseType, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CourseType getByCompanyId_PrevAndNext(
		Session session, CourseType courseType, long companyId,
		OrderByComparator<CourseType> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_COURSETYPE_WHERE);

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

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
			query.append(CourseTypeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(courseType)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CourseType> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the course types where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	@Override
	public void removeByCompanyId(long companyId) {
		for (CourseType courseType :
				findByCompanyId(
					companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(courseType);
		}
	}

	/**
	 * Returns the number of course types where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching course types
	 */
	@Override
	public int countByCompanyId(long companyId) {
		FinderPath finderPath = _finderPathCountByCompanyId;

		Object[] finderArgs = new Object[] {companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COURSETYPE_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

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

	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 =
		"courseType.companyId = ?";

	private FinderPath _finderPathFetchByCourseTypeId;
	private FinderPath _finderPathCountByCourseTypeId;

	/**
	 * Returns the course type where courseTypeId = &#63; or throws a <code>NoSuchCourseTypeException</code> if it could not be found.
	 *
	 * @param courseTypeId the course type ID
	 * @return the matching course type
	 * @throws NoSuchCourseTypeException if a matching course type could not be found
	 */
	@Override
	public CourseType findByCourseTypeId(long courseTypeId)
		throws NoSuchCourseTypeException {

		CourseType courseType = fetchByCourseTypeId(courseTypeId);

		if (courseType == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("courseTypeId=");
			msg.append(courseTypeId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchCourseTypeException(msg.toString());
		}

		return courseType;
	}

	/**
	 * Returns the course type where courseTypeId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param courseTypeId the course type ID
	 * @return the matching course type, or <code>null</code> if a matching course type could not be found
	 */
	@Override
	public CourseType fetchByCourseTypeId(long courseTypeId) {
		return fetchByCourseTypeId(courseTypeId, true);
	}

	/**
	 * Returns the course type where courseTypeId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param courseTypeId the course type ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching course type, or <code>null</code> if a matching course type could not be found
	 */
	@Override
	public CourseType fetchByCourseTypeId(
		long courseTypeId, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {courseTypeId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByCourseTypeId, finderArgs, this);
		}

		if (result instanceof CourseType) {
			CourseType courseType = (CourseType)result;

			if ((courseTypeId != courseType.getCourseTypeId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_COURSETYPE_WHERE);

			query.append(_FINDER_COLUMN_COURSETYPEID_COURSETYPEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(courseTypeId);

				List<CourseType> list = q.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByCourseTypeId, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {courseTypeId};
							}

							_log.warn(
								"CourseTypePersistenceImpl.fetchByCourseTypeId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					CourseType courseType = list.get(0);

					result = courseType;

					cacheResult(courseType);
				}
			}
			catch (Exception e) {
				if (useFinderCache) {
					finderCache.removeResult(
						_finderPathFetchByCourseTypeId, finderArgs);
				}

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (CourseType)result;
		}
	}

	/**
	 * Removes the course type where courseTypeId = &#63; from the database.
	 *
	 * @param courseTypeId the course type ID
	 * @return the course type that was removed
	 */
	@Override
	public CourseType removeByCourseTypeId(long courseTypeId)
		throws NoSuchCourseTypeException {

		CourseType courseType = findByCourseTypeId(courseTypeId);

		return remove(courseType);
	}

	/**
	 * Returns the number of course types where courseTypeId = &#63;.
	 *
	 * @param courseTypeId the course type ID
	 * @return the number of matching course types
	 */
	@Override
	public int countByCourseTypeId(long courseTypeId) {
		FinderPath finderPath = _finderPathCountByCourseTypeId;

		Object[] finderArgs = new Object[] {courseTypeId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COURSETYPE_WHERE);

			query.append(_FINDER_COLUMN_COURSETYPEID_COURSETYPEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(courseTypeId);

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

	private static final String _FINDER_COLUMN_COURSETYPEID_COURSETYPEID_2 =
		"courseType.courseTypeId = ?";

	public CourseTypePersistenceImpl() {
		setModelClass(CourseType.class);

		setModelImplClass(CourseTypeImpl.class);
		setModelPKClass(long.class);
	}

	/**
	 * Caches the course type in the entity cache if it is enabled.
	 *
	 * @param courseType the course type
	 */
	@Override
	public void cacheResult(CourseType courseType) {
		entityCache.putResult(
			entityCacheEnabled, CourseTypeImpl.class,
			courseType.getPrimaryKey(), courseType);

		finderCache.putResult(
			_finderPathFetchByCourseTypeId,
			new Object[] {courseType.getCourseTypeId()}, courseType);

		courseType.resetOriginalValues();
	}

	/**
	 * Caches the course types in the entity cache if it is enabled.
	 *
	 * @param courseTypes the course types
	 */
	@Override
	public void cacheResult(List<CourseType> courseTypes) {
		for (CourseType courseType : courseTypes) {
			if (entityCache.getResult(
					entityCacheEnabled, CourseTypeImpl.class,
					courseType.getPrimaryKey()) == null) {

				cacheResult(courseType);
			}
			else {
				courseType.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all course types.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CourseTypeImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the course type.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CourseType courseType) {
		entityCache.removeResult(
			entityCacheEnabled, CourseTypeImpl.class,
			courseType.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((CourseTypeModelImpl)courseType, true);
	}

	@Override
	public void clearCache(List<CourseType> courseTypes) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CourseType courseType : courseTypes) {
			entityCache.removeResult(
				entityCacheEnabled, CourseTypeImpl.class,
				courseType.getPrimaryKey());

			clearUniqueFindersCache((CourseTypeModelImpl)courseType, true);
		}
	}

	protected void cacheUniqueFindersCache(
		CourseTypeModelImpl courseTypeModelImpl) {

		Object[] args = new Object[] {courseTypeModelImpl.getCourseTypeId()};

		finderCache.putResult(
			_finderPathCountByCourseTypeId, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByCourseTypeId, args, courseTypeModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		CourseTypeModelImpl courseTypeModelImpl, boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				courseTypeModelImpl.getCourseTypeId()
			};

			finderCache.removeResult(_finderPathCountByCourseTypeId, args);
			finderCache.removeResult(_finderPathFetchByCourseTypeId, args);
		}

		if ((courseTypeModelImpl.getColumnBitmask() &
			 _finderPathFetchByCourseTypeId.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				courseTypeModelImpl.getOriginalCourseTypeId()
			};

			finderCache.removeResult(_finderPathCountByCourseTypeId, args);
			finderCache.removeResult(_finderPathFetchByCourseTypeId, args);
		}
	}

	/**
	 * Creates a new course type with the primary key. Does not add the course type to the database.
	 *
	 * @param courseTypeId the primary key for the new course type
	 * @return the new course type
	 */
	@Override
	public CourseType create(long courseTypeId) {
		CourseType courseType = new CourseTypeImpl();

		courseType.setNew(true);
		courseType.setPrimaryKey(courseTypeId);

		courseType.setCompanyId(CompanyThreadLocal.getCompanyId());

		return courseType;
	}

	/**
	 * Removes the course type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param courseTypeId the primary key of the course type
	 * @return the course type that was removed
	 * @throws NoSuchCourseTypeException if a course type with the primary key could not be found
	 */
	@Override
	public CourseType remove(long courseTypeId)
		throws NoSuchCourseTypeException {

		return remove((Serializable)courseTypeId);
	}

	/**
	 * Removes the course type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the course type
	 * @return the course type that was removed
	 * @throws NoSuchCourseTypeException if a course type with the primary key could not be found
	 */
	@Override
	public CourseType remove(Serializable primaryKey)
		throws NoSuchCourseTypeException {

		Session session = null;

		try {
			session = openSession();

			CourseType courseType = (CourseType)session.get(
				CourseTypeImpl.class, primaryKey);

			if (courseType == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCourseTypeException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(courseType);
		}
		catch (NoSuchCourseTypeException nsee) {
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
	protected CourseType removeImpl(CourseType courseType) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(courseType)) {
				courseType = (CourseType)session.get(
					CourseTypeImpl.class, courseType.getPrimaryKeyObj());
			}

			if (courseType != null) {
				session.delete(courseType);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (courseType != null) {
			clearCache(courseType);
		}

		return courseType;
	}

	@Override
	public CourseType updateImpl(CourseType courseType) {
		boolean isNew = courseType.isNew();

		if (!(courseType instanceof CourseTypeModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(courseType.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(courseType);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in courseType proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CourseType implementation " +
					courseType.getClass());
		}

		CourseTypeModelImpl courseTypeModelImpl =
			(CourseTypeModelImpl)courseType;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (courseType.getCreateDate() == null)) {
			if (serviceContext == null) {
				courseType.setCreateDate(now);
			}
			else {
				courseType.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!courseTypeModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				courseType.setModifiedDate(now);
			}
			else {
				courseType.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (courseType.isNew()) {
				session.save(courseType);

				courseType.setNew(false);
			}
			else {
				courseType = (CourseType)session.merge(courseType);
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
			Object[] args = new Object[] {courseTypeModelImpl.getCompanyId()};

			finderCache.removeResult(_finderPathCountByCompanyId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByCompanyId, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((courseTypeModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByCompanyId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					courseTypeModelImpl.getOriginalCompanyId()
				};

				finderCache.removeResult(_finderPathCountByCompanyId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCompanyId, args);

				args = new Object[] {courseTypeModelImpl.getCompanyId()};

				finderCache.removeResult(_finderPathCountByCompanyId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCompanyId, args);
			}
		}

		entityCache.putResult(
			entityCacheEnabled, CourseTypeImpl.class,
			courseType.getPrimaryKey(), courseType, false);

		clearUniqueFindersCache(courseTypeModelImpl, false);
		cacheUniqueFindersCache(courseTypeModelImpl);

		courseType.resetOriginalValues();

		return courseType;
	}

	/**
	 * Returns the course type with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the course type
	 * @return the course type
	 * @throws NoSuchCourseTypeException if a course type with the primary key could not be found
	 */
	@Override
	public CourseType findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCourseTypeException {

		CourseType courseType = fetchByPrimaryKey(primaryKey);

		if (courseType == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCourseTypeException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return courseType;
	}

	/**
	 * Returns the course type with the primary key or throws a <code>NoSuchCourseTypeException</code> if it could not be found.
	 *
	 * @param courseTypeId the primary key of the course type
	 * @return the course type
	 * @throws NoSuchCourseTypeException if a course type with the primary key could not be found
	 */
	@Override
	public CourseType findByPrimaryKey(long courseTypeId)
		throws NoSuchCourseTypeException {

		return findByPrimaryKey((Serializable)courseTypeId);
	}

	/**
	 * Returns the course type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param courseTypeId the primary key of the course type
	 * @return the course type, or <code>null</code> if a course type with the primary key could not be found
	 */
	@Override
	public CourseType fetchByPrimaryKey(long courseTypeId) {
		return fetchByPrimaryKey((Serializable)courseTypeId);
	}

	/**
	 * Returns all the course types.
	 *
	 * @return the course types
	 */
	@Override
	public List<CourseType> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the course types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CourseTypeModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of course types
	 * @param end the upper bound of the range of course types (not inclusive)
	 * @return the range of course types
	 */
	@Override
	public List<CourseType> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the course types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CourseTypeModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of course types
	 * @param end the upper bound of the range of course types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of course types
	 */
	@Override
	public List<CourseType> findAll(
		int start, int end, OrderByComparator<CourseType> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the course types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CourseTypeModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of course types
	 * @param end the upper bound of the range of course types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of course types
	 */
	@Override
	public List<CourseType> findAll(
		int start, int end, OrderByComparator<CourseType> orderByComparator,
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

		List<CourseType> list = null;

		if (useFinderCache) {
			list = (List<CourseType>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_COURSETYPE);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_COURSETYPE;

				if (pagination) {
					sql = sql.concat(CourseTypeModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CourseType>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CourseType>)QueryUtil.list(
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
	 * Removes all the course types from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CourseType courseType : findAll()) {
			remove(courseType);
		}
	}

	/**
	 * Returns the number of course types.
	 *
	 * @return the number of course types
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_COURSETYPE);

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
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "courseTypeId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_COURSETYPE;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return CourseTypeModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the course type persistence.
	 */
	@Activate
	public void activate() {
		CourseTypeModelImpl.setEntityCacheEnabled(entityCacheEnabled);
		CourseTypeModelImpl.setFinderCacheEnabled(finderCacheEnabled);

		_finderPathWithPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, CourseTypeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, CourseTypeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByCompanyId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, CourseTypeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByCompanyId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, CourseTypeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] {Long.class.getName()},
			CourseTypeModelImpl.COMPANYID_COLUMN_BITMASK);

		_finderPathCountByCompanyId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] {Long.class.getName()});

		_finderPathFetchByCourseTypeId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, CourseTypeImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByCourseTypeId",
			new String[] {Long.class.getName()},
			CourseTypeModelImpl.COURSETYPEID_COLUMN_BITMASK);

		_finderPathCountByCourseTypeId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCourseTypeId",
			new String[] {Long.class.getName()});
	}

	@Deactivate
	public void deactivate() {
		entityCache.removeCache(CourseTypeImpl.class.getName());
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
				"value.object.column.bitmask.enabled.com.ted.lms.model.CourseType"),
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

	private static final String _SQL_SELECT_COURSETYPE =
		"SELECT courseType FROM CourseType courseType";

	private static final String _SQL_SELECT_COURSETYPE_WHERE =
		"SELECT courseType FROM CourseType courseType WHERE ";

	private static final String _SQL_COUNT_COURSETYPE =
		"SELECT COUNT(courseType) FROM CourseType courseType";

	private static final String _SQL_COUNT_COURSETYPE_WHERE =
		"SELECT COUNT(courseType) FROM CourseType courseType WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "courseType.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CourseType exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No CourseType exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CourseTypePersistenceImpl.class);

	static {
		try {
			Class.forName(LMSPersistenceConstants.class.getName());
		}
		catch (ClassNotFoundException cnfe) {
			throw new ExceptionInInitializerError(cnfe);
		}
	}

}