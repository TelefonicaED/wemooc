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

import com.ted.lms.exception.NoSuchCourseResultException;
import com.ted.lms.model.CourseResult;
import com.ted.lms.model.impl.CourseResultImpl;
import com.ted.lms.model.impl.CourseResultModelImpl;
import com.ted.lms.service.persistence.CourseResultPersistence;
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
 * The persistence implementation for the course result service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = CourseResultPersistence.class)
@ProviderType
public class CourseResultPersistenceImpl
	extends BasePersistenceImpl<CourseResult>
	implements CourseResultPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CourseResultUtil</code> to access the course result persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CourseResultImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByCourseId;
	private FinderPath _finderPathWithoutPaginationFindByCourseId;
	private FinderPath _finderPathCountByCourseId;

	/**
	 * Returns all the course results where courseId = &#63;.
	 *
	 * @param courseId the course ID
	 * @return the matching course results
	 */
	@Override
	public List<CourseResult> findByCourseId(long courseId) {
		return findByCourseId(
			courseId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the course results where courseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CourseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param courseId the course ID
	 * @param start the lower bound of the range of course results
	 * @param end the upper bound of the range of course results (not inclusive)
	 * @return the range of matching course results
	 */
	@Override
	public List<CourseResult> findByCourseId(
		long courseId, int start, int end) {

		return findByCourseId(courseId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the course results where courseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CourseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param courseId the course ID
	 * @param start the lower bound of the range of course results
	 * @param end the upper bound of the range of course results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching course results
	 */
	@Override
	public List<CourseResult> findByCourseId(
		long courseId, int start, int end,
		OrderByComparator<CourseResult> orderByComparator) {

		return findByCourseId(courseId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the course results where courseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CourseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param courseId the course ID
	 * @param start the lower bound of the range of course results
	 * @param end the upper bound of the range of course results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching course results
	 */
	@Override
	public List<CourseResult> findByCourseId(
		long courseId, int start, int end,
		OrderByComparator<CourseResult> orderByComparator,
		boolean useFinderCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByCourseId;
				finderArgs = new Object[] {courseId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByCourseId;
			finderArgs = new Object[] {courseId, start, end, orderByComparator};
		}

		List<CourseResult> list = null;

		if (useFinderCache) {
			list = (List<CourseResult>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CourseResult courseResult : list) {
					if ((courseId != courseResult.getCourseId())) {
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

			query.append(_SQL_SELECT_COURSERESULT_WHERE);

			query.append(_FINDER_COLUMN_COURSEID_COURSEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CourseResultModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(courseId);

				if (!pagination) {
					list = (List<CourseResult>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CourseResult>)QueryUtil.list(
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
	 * Returns the first course result in the ordered set where courseId = &#63;.
	 *
	 * @param courseId the course ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching course result
	 * @throws NoSuchCourseResultException if a matching course result could not be found
	 */
	@Override
	public CourseResult findByCourseId_First(
			long courseId, OrderByComparator<CourseResult> orderByComparator)
		throws NoSuchCourseResultException {

		CourseResult courseResult = fetchByCourseId_First(
			courseId, orderByComparator);

		if (courseResult != null) {
			return courseResult;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("courseId=");
		msg.append(courseId);

		msg.append("}");

		throw new NoSuchCourseResultException(msg.toString());
	}

	/**
	 * Returns the first course result in the ordered set where courseId = &#63;.
	 *
	 * @param courseId the course ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching course result, or <code>null</code> if a matching course result could not be found
	 */
	@Override
	public CourseResult fetchByCourseId_First(
		long courseId, OrderByComparator<CourseResult> orderByComparator) {

		List<CourseResult> list = findByCourseId(
			courseId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last course result in the ordered set where courseId = &#63;.
	 *
	 * @param courseId the course ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching course result
	 * @throws NoSuchCourseResultException if a matching course result could not be found
	 */
	@Override
	public CourseResult findByCourseId_Last(
			long courseId, OrderByComparator<CourseResult> orderByComparator)
		throws NoSuchCourseResultException {

		CourseResult courseResult = fetchByCourseId_Last(
			courseId, orderByComparator);

		if (courseResult != null) {
			return courseResult;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("courseId=");
		msg.append(courseId);

		msg.append("}");

		throw new NoSuchCourseResultException(msg.toString());
	}

	/**
	 * Returns the last course result in the ordered set where courseId = &#63;.
	 *
	 * @param courseId the course ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching course result, or <code>null</code> if a matching course result could not be found
	 */
	@Override
	public CourseResult fetchByCourseId_Last(
		long courseId, OrderByComparator<CourseResult> orderByComparator) {

		int count = countByCourseId(courseId);

		if (count == 0) {
			return null;
		}

		List<CourseResult> list = findByCourseId(
			courseId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the course results before and after the current course result in the ordered set where courseId = &#63;.
	 *
	 * @param crId the primary key of the current course result
	 * @param courseId the course ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next course result
	 * @throws NoSuchCourseResultException if a course result with the primary key could not be found
	 */
	@Override
	public CourseResult[] findByCourseId_PrevAndNext(
			long crId, long courseId,
			OrderByComparator<CourseResult> orderByComparator)
		throws NoSuchCourseResultException {

		CourseResult courseResult = findByPrimaryKey(crId);

		Session session = null;

		try {
			session = openSession();

			CourseResult[] array = new CourseResultImpl[3];

			array[0] = getByCourseId_PrevAndNext(
				session, courseResult, courseId, orderByComparator, true);

			array[1] = courseResult;

			array[2] = getByCourseId_PrevAndNext(
				session, courseResult, courseId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CourseResult getByCourseId_PrevAndNext(
		Session session, CourseResult courseResult, long courseId,
		OrderByComparator<CourseResult> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_COURSERESULT_WHERE);

		query.append(_FINDER_COLUMN_COURSEID_COURSEID_2);

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
			query.append(CourseResultModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(courseId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(courseResult)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CourseResult> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the course results where courseId = &#63; from the database.
	 *
	 * @param courseId the course ID
	 */
	@Override
	public void removeByCourseId(long courseId) {
		for (CourseResult courseResult :
				findByCourseId(
					courseId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(courseResult);
		}
	}

	/**
	 * Returns the number of course results where courseId = &#63;.
	 *
	 * @param courseId the course ID
	 * @return the number of matching course results
	 */
	@Override
	public int countByCourseId(long courseId) {
		FinderPath finderPath = _finderPathCountByCourseId;

		Object[] finderArgs = new Object[] {courseId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COURSERESULT_WHERE);

			query.append(_FINDER_COLUMN_COURSEID_COURSEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(courseId);

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

	private static final String _FINDER_COLUMN_COURSEID_COURSEID_2 =
		"courseResult.courseId = ?";

	private FinderPath _finderPathWithPaginationFindByUserId;
	private FinderPath _finderPathWithoutPaginationFindByUserId;
	private FinderPath _finderPathCountByUserId;

	/**
	 * Returns all the course results where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching course results
	 */
	@Override
	public List<CourseResult> findByUserId(long userId) {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the course results where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CourseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of course results
	 * @param end the upper bound of the range of course results (not inclusive)
	 * @return the range of matching course results
	 */
	@Override
	public List<CourseResult> findByUserId(long userId, int start, int end) {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the course results where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CourseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of course results
	 * @param end the upper bound of the range of course results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching course results
	 */
	@Override
	public List<CourseResult> findByUserId(
		long userId, int start, int end,
		OrderByComparator<CourseResult> orderByComparator) {

		return findByUserId(userId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the course results where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CourseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of course results
	 * @param end the upper bound of the range of course results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching course results
	 */
	@Override
	public List<CourseResult> findByUserId(
		long userId, int start, int end,
		OrderByComparator<CourseResult> orderByComparator,
		boolean useFinderCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUserId;
				finderArgs = new Object[] {userId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUserId;
			finderArgs = new Object[] {userId, start, end, orderByComparator};
		}

		List<CourseResult> list = null;

		if (useFinderCache) {
			list = (List<CourseResult>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CourseResult courseResult : list) {
					if ((userId != courseResult.getUserId())) {
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

			query.append(_SQL_SELECT_COURSERESULT_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CourseResultModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (!pagination) {
					list = (List<CourseResult>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CourseResult>)QueryUtil.list(
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
	 * Returns the first course result in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching course result
	 * @throws NoSuchCourseResultException if a matching course result could not be found
	 */
	@Override
	public CourseResult findByUserId_First(
			long userId, OrderByComparator<CourseResult> orderByComparator)
		throws NoSuchCourseResultException {

		CourseResult courseResult = fetchByUserId_First(
			userId, orderByComparator);

		if (courseResult != null) {
			return courseResult;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append("}");

		throw new NoSuchCourseResultException(msg.toString());
	}

	/**
	 * Returns the first course result in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching course result, or <code>null</code> if a matching course result could not be found
	 */
	@Override
	public CourseResult fetchByUserId_First(
		long userId, OrderByComparator<CourseResult> orderByComparator) {

		List<CourseResult> list = findByUserId(userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last course result in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching course result
	 * @throws NoSuchCourseResultException if a matching course result could not be found
	 */
	@Override
	public CourseResult findByUserId_Last(
			long userId, OrderByComparator<CourseResult> orderByComparator)
		throws NoSuchCourseResultException {

		CourseResult courseResult = fetchByUserId_Last(
			userId, orderByComparator);

		if (courseResult != null) {
			return courseResult;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append("}");

		throw new NoSuchCourseResultException(msg.toString());
	}

	/**
	 * Returns the last course result in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching course result, or <code>null</code> if a matching course result could not be found
	 */
	@Override
	public CourseResult fetchByUserId_Last(
		long userId, OrderByComparator<CourseResult> orderByComparator) {

		int count = countByUserId(userId);

		if (count == 0) {
			return null;
		}

		List<CourseResult> list = findByUserId(
			userId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the course results before and after the current course result in the ordered set where userId = &#63;.
	 *
	 * @param crId the primary key of the current course result
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next course result
	 * @throws NoSuchCourseResultException if a course result with the primary key could not be found
	 */
	@Override
	public CourseResult[] findByUserId_PrevAndNext(
			long crId, long userId,
			OrderByComparator<CourseResult> orderByComparator)
		throws NoSuchCourseResultException {

		CourseResult courseResult = findByPrimaryKey(crId);

		Session session = null;

		try {
			session = openSession();

			CourseResult[] array = new CourseResultImpl[3];

			array[0] = getByUserId_PrevAndNext(
				session, courseResult, userId, orderByComparator, true);

			array[1] = courseResult;

			array[2] = getByUserId_PrevAndNext(
				session, courseResult, userId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CourseResult getByUserId_PrevAndNext(
		Session session, CourseResult courseResult, long userId,
		OrderByComparator<CourseResult> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_COURSERESULT_WHERE);

		query.append(_FINDER_COLUMN_USERID_USERID_2);

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
			query.append(CourseResultModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(courseResult)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CourseResult> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the course results where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	@Override
	public void removeByUserId(long userId) {
		for (CourseResult courseResult :
				findByUserId(
					userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(courseResult);
		}
	}

	/**
	 * Returns the number of course results where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching course results
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = _finderPathCountByUserId;

		Object[] finderArgs = new Object[] {userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COURSERESULT_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

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

	private static final String _FINDER_COLUMN_USERID_USERID_2 =
		"courseResult.userId = ?";

	private FinderPath _finderPathFetchByCourseIdUserId;
	private FinderPath _finderPathCountByCourseIdUserId;

	/**
	 * Returns the course result where courseId = &#63; and userId = &#63; or throws a <code>NoSuchCourseResultException</code> if it could not be found.
	 *
	 * @param courseId the course ID
	 * @param userId the user ID
	 * @return the matching course result
	 * @throws NoSuchCourseResultException if a matching course result could not be found
	 */
	@Override
	public CourseResult findByCourseIdUserId(long courseId, long userId)
		throws NoSuchCourseResultException {

		CourseResult courseResult = fetchByCourseIdUserId(courseId, userId);

		if (courseResult == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("courseId=");
			msg.append(courseId);

			msg.append(", userId=");
			msg.append(userId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchCourseResultException(msg.toString());
		}

		return courseResult;
	}

	/**
	 * Returns the course result where courseId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param courseId the course ID
	 * @param userId the user ID
	 * @return the matching course result, or <code>null</code> if a matching course result could not be found
	 */
	@Override
	public CourseResult fetchByCourseIdUserId(long courseId, long userId) {
		return fetchByCourseIdUserId(courseId, userId, true);
	}

	/**
	 * Returns the course result where courseId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param courseId the course ID
	 * @param userId the user ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching course result, or <code>null</code> if a matching course result could not be found
	 */
	@Override
	public CourseResult fetchByCourseIdUserId(
		long courseId, long userId, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {courseId, userId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByCourseIdUserId, finderArgs, this);
		}

		if (result instanceof CourseResult) {
			CourseResult courseResult = (CourseResult)result;

			if ((courseId != courseResult.getCourseId()) ||
				(userId != courseResult.getUserId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_COURSERESULT_WHERE);

			query.append(_FINDER_COLUMN_COURSEIDUSERID_COURSEID_2);

			query.append(_FINDER_COLUMN_COURSEIDUSERID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(courseId);

				qPos.add(userId);

				List<CourseResult> list = q.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByCourseIdUserId, finderArgs, list);
					}
				}
				else {
					CourseResult courseResult = list.get(0);

					result = courseResult;

					cacheResult(courseResult);
				}
			}
			catch (Exception e) {
				if (useFinderCache) {
					finderCache.removeResult(
						_finderPathFetchByCourseIdUserId, finderArgs);
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
			return (CourseResult)result;
		}
	}

	/**
	 * Removes the course result where courseId = &#63; and userId = &#63; from the database.
	 *
	 * @param courseId the course ID
	 * @param userId the user ID
	 * @return the course result that was removed
	 */
	@Override
	public CourseResult removeByCourseIdUserId(long courseId, long userId)
		throws NoSuchCourseResultException {

		CourseResult courseResult = findByCourseIdUserId(courseId, userId);

		return remove(courseResult);
	}

	/**
	 * Returns the number of course results where courseId = &#63; and userId = &#63;.
	 *
	 * @param courseId the course ID
	 * @param userId the user ID
	 * @return the number of matching course results
	 */
	@Override
	public int countByCourseIdUserId(long courseId, long userId) {
		FinderPath finderPath = _finderPathCountByCourseIdUserId;

		Object[] finderArgs = new Object[] {courseId, userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_COURSERESULT_WHERE);

			query.append(_FINDER_COLUMN_COURSEIDUSERID_COURSEID_2);

			query.append(_FINDER_COLUMN_COURSEIDUSERID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(courseId);

				qPos.add(userId);

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

	private static final String _FINDER_COLUMN_COURSEIDUSERID_COURSEID_2 =
		"courseResult.courseId = ? AND ";

	private static final String _FINDER_COLUMN_COURSEIDUSERID_USERID_2 =
		"courseResult.userId = ?";

	private FinderPath _finderPathWithPaginationFindByCourseIdPassed;
	private FinderPath _finderPathWithoutPaginationFindByCourseIdPassed;
	private FinderPath _finderPathCountByCourseIdPassed;

	/**
	 * Returns all the course results where courseId = &#63; and passed = &#63;.
	 *
	 * @param courseId the course ID
	 * @param passed the passed
	 * @return the matching course results
	 */
	@Override
	public List<CourseResult> findByCourseIdPassed(
		long courseId, boolean passed) {

		return findByCourseIdPassed(
			courseId, passed, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the course results where courseId = &#63; and passed = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CourseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param courseId the course ID
	 * @param passed the passed
	 * @param start the lower bound of the range of course results
	 * @param end the upper bound of the range of course results (not inclusive)
	 * @return the range of matching course results
	 */
	@Override
	public List<CourseResult> findByCourseIdPassed(
		long courseId, boolean passed, int start, int end) {

		return findByCourseIdPassed(courseId, passed, start, end, null);
	}

	/**
	 * Returns an ordered range of all the course results where courseId = &#63; and passed = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CourseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param courseId the course ID
	 * @param passed the passed
	 * @param start the lower bound of the range of course results
	 * @param end the upper bound of the range of course results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching course results
	 */
	@Override
	public List<CourseResult> findByCourseIdPassed(
		long courseId, boolean passed, int start, int end,
		OrderByComparator<CourseResult> orderByComparator) {

		return findByCourseIdPassed(
			courseId, passed, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the course results where courseId = &#63; and passed = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CourseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param courseId the course ID
	 * @param passed the passed
	 * @param start the lower bound of the range of course results
	 * @param end the upper bound of the range of course results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching course results
	 */
	@Override
	public List<CourseResult> findByCourseIdPassed(
		long courseId, boolean passed, int start, int end,
		OrderByComparator<CourseResult> orderByComparator,
		boolean useFinderCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByCourseIdPassed;
				finderArgs = new Object[] {courseId, passed};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByCourseIdPassed;
			finderArgs = new Object[] {
				courseId, passed, start, end, orderByComparator
			};
		}

		List<CourseResult> list = null;

		if (useFinderCache) {
			list = (List<CourseResult>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CourseResult courseResult : list) {
					if ((courseId != courseResult.getCourseId()) ||
						(passed != courseResult.isPassed())) {

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

			query.append(_SQL_SELECT_COURSERESULT_WHERE);

			query.append(_FINDER_COLUMN_COURSEIDPASSED_COURSEID_2);

			query.append(_FINDER_COLUMN_COURSEIDPASSED_PASSED_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CourseResultModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(courseId);

				qPos.add(passed);

				if (!pagination) {
					list = (List<CourseResult>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CourseResult>)QueryUtil.list(
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
	 * Returns the first course result in the ordered set where courseId = &#63; and passed = &#63;.
	 *
	 * @param courseId the course ID
	 * @param passed the passed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching course result
	 * @throws NoSuchCourseResultException if a matching course result could not be found
	 */
	@Override
	public CourseResult findByCourseIdPassed_First(
			long courseId, boolean passed,
			OrderByComparator<CourseResult> orderByComparator)
		throws NoSuchCourseResultException {

		CourseResult courseResult = fetchByCourseIdPassed_First(
			courseId, passed, orderByComparator);

		if (courseResult != null) {
			return courseResult;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("courseId=");
		msg.append(courseId);

		msg.append(", passed=");
		msg.append(passed);

		msg.append("}");

		throw new NoSuchCourseResultException(msg.toString());
	}

	/**
	 * Returns the first course result in the ordered set where courseId = &#63; and passed = &#63;.
	 *
	 * @param courseId the course ID
	 * @param passed the passed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching course result, or <code>null</code> if a matching course result could not be found
	 */
	@Override
	public CourseResult fetchByCourseIdPassed_First(
		long courseId, boolean passed,
		OrderByComparator<CourseResult> orderByComparator) {

		List<CourseResult> list = findByCourseIdPassed(
			courseId, passed, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last course result in the ordered set where courseId = &#63; and passed = &#63;.
	 *
	 * @param courseId the course ID
	 * @param passed the passed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching course result
	 * @throws NoSuchCourseResultException if a matching course result could not be found
	 */
	@Override
	public CourseResult findByCourseIdPassed_Last(
			long courseId, boolean passed,
			OrderByComparator<CourseResult> orderByComparator)
		throws NoSuchCourseResultException {

		CourseResult courseResult = fetchByCourseIdPassed_Last(
			courseId, passed, orderByComparator);

		if (courseResult != null) {
			return courseResult;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("courseId=");
		msg.append(courseId);

		msg.append(", passed=");
		msg.append(passed);

		msg.append("}");

		throw new NoSuchCourseResultException(msg.toString());
	}

	/**
	 * Returns the last course result in the ordered set where courseId = &#63; and passed = &#63;.
	 *
	 * @param courseId the course ID
	 * @param passed the passed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching course result, or <code>null</code> if a matching course result could not be found
	 */
	@Override
	public CourseResult fetchByCourseIdPassed_Last(
		long courseId, boolean passed,
		OrderByComparator<CourseResult> orderByComparator) {

		int count = countByCourseIdPassed(courseId, passed);

		if (count == 0) {
			return null;
		}

		List<CourseResult> list = findByCourseIdPassed(
			courseId, passed, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

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
	@Override
	public CourseResult[] findByCourseIdPassed_PrevAndNext(
			long crId, long courseId, boolean passed,
			OrderByComparator<CourseResult> orderByComparator)
		throws NoSuchCourseResultException {

		CourseResult courseResult = findByPrimaryKey(crId);

		Session session = null;

		try {
			session = openSession();

			CourseResult[] array = new CourseResultImpl[3];

			array[0] = getByCourseIdPassed_PrevAndNext(
				session, courseResult, courseId, passed, orderByComparator,
				true);

			array[1] = courseResult;

			array[2] = getByCourseIdPassed_PrevAndNext(
				session, courseResult, courseId, passed, orderByComparator,
				false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CourseResult getByCourseIdPassed_PrevAndNext(
		Session session, CourseResult courseResult, long courseId,
		boolean passed, OrderByComparator<CourseResult> orderByComparator,
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

		query.append(_SQL_SELECT_COURSERESULT_WHERE);

		query.append(_FINDER_COLUMN_COURSEIDPASSED_COURSEID_2);

		query.append(_FINDER_COLUMN_COURSEIDPASSED_PASSED_2);

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
			query.append(CourseResultModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(courseId);

		qPos.add(passed);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(courseResult)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CourseResult> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the course results where courseId = &#63; and passed = &#63; from the database.
	 *
	 * @param courseId the course ID
	 * @param passed the passed
	 */
	@Override
	public void removeByCourseIdPassed(long courseId, boolean passed) {
		for (CourseResult courseResult :
				findByCourseIdPassed(
					courseId, passed, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(courseResult);
		}
	}

	/**
	 * Returns the number of course results where courseId = &#63; and passed = &#63;.
	 *
	 * @param courseId the course ID
	 * @param passed the passed
	 * @return the number of matching course results
	 */
	@Override
	public int countByCourseIdPassed(long courseId, boolean passed) {
		FinderPath finderPath = _finderPathCountByCourseIdPassed;

		Object[] finderArgs = new Object[] {courseId, passed};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_COURSERESULT_WHERE);

			query.append(_FINDER_COLUMN_COURSEIDPASSED_COURSEID_2);

			query.append(_FINDER_COLUMN_COURSEIDPASSED_PASSED_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(courseId);

				qPos.add(passed);

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

	private static final String _FINDER_COLUMN_COURSEIDPASSED_COURSEID_2 =
		"courseResult.courseId = ? AND ";

	private static final String _FINDER_COLUMN_COURSEIDPASSED_PASSED_2 =
		"courseResult.passed = ?";

	private FinderPath _finderPathWithPaginationFindByFinished;
	private FinderPath _finderPathWithoutPaginationFindByFinished;
	private FinderPath _finderPathCountByFinished;

	/**
	 * Returns all the course results where courseId = &#63; and passed = &#63;.
	 *
	 * @param courseId the course ID
	 * @param passed the passed
	 * @return the matching course results
	 */
	@Override
	public List<CourseResult> findByFinished(long courseId, boolean passed) {
		return findByFinished(
			courseId, passed, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the course results where courseId = &#63; and passed = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CourseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param courseId the course ID
	 * @param passed the passed
	 * @param start the lower bound of the range of course results
	 * @param end the upper bound of the range of course results (not inclusive)
	 * @return the range of matching course results
	 */
	@Override
	public List<CourseResult> findByFinished(
		long courseId, boolean passed, int start, int end) {

		return findByFinished(courseId, passed, start, end, null);
	}

	/**
	 * Returns an ordered range of all the course results where courseId = &#63; and passed = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CourseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param courseId the course ID
	 * @param passed the passed
	 * @param start the lower bound of the range of course results
	 * @param end the upper bound of the range of course results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching course results
	 */
	@Override
	public List<CourseResult> findByFinished(
		long courseId, boolean passed, int start, int end,
		OrderByComparator<CourseResult> orderByComparator) {

		return findByFinished(
			courseId, passed, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the course results where courseId = &#63; and passed = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CourseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param courseId the course ID
	 * @param passed the passed
	 * @param start the lower bound of the range of course results
	 * @param end the upper bound of the range of course results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching course results
	 */
	@Override
	public List<CourseResult> findByFinished(
		long courseId, boolean passed, int start, int end,
		OrderByComparator<CourseResult> orderByComparator,
		boolean useFinderCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByFinished;
				finderArgs = new Object[] {courseId, passed};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByFinished;
			finderArgs = new Object[] {
				courseId, passed, start, end, orderByComparator
			};
		}

		List<CourseResult> list = null;

		if (useFinderCache) {
			list = (List<CourseResult>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CourseResult courseResult : list) {
					if ((courseId != courseResult.getCourseId()) ||
						(passed != courseResult.isPassed())) {

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

			query.append(_SQL_SELECT_COURSERESULT_WHERE);

			query.append(_FINDER_COLUMN_FINISHED_COURSEID_2);

			query.append(_FINDER_COLUMN_FINISHED_PASSED_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CourseResultModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(courseId);

				qPos.add(passed);

				if (!pagination) {
					list = (List<CourseResult>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CourseResult>)QueryUtil.list(
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
	 * Returns the first course result in the ordered set where courseId = &#63; and passed = &#63;.
	 *
	 * @param courseId the course ID
	 * @param passed the passed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching course result
	 * @throws NoSuchCourseResultException if a matching course result could not be found
	 */
	@Override
	public CourseResult findByFinished_First(
			long courseId, boolean passed,
			OrderByComparator<CourseResult> orderByComparator)
		throws NoSuchCourseResultException {

		CourseResult courseResult = fetchByFinished_First(
			courseId, passed, orderByComparator);

		if (courseResult != null) {
			return courseResult;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("courseId=");
		msg.append(courseId);

		msg.append(", passed=");
		msg.append(passed);

		msg.append("}");

		throw new NoSuchCourseResultException(msg.toString());
	}

	/**
	 * Returns the first course result in the ordered set where courseId = &#63; and passed = &#63;.
	 *
	 * @param courseId the course ID
	 * @param passed the passed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching course result, or <code>null</code> if a matching course result could not be found
	 */
	@Override
	public CourseResult fetchByFinished_First(
		long courseId, boolean passed,
		OrderByComparator<CourseResult> orderByComparator) {

		List<CourseResult> list = findByFinished(
			courseId, passed, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last course result in the ordered set where courseId = &#63; and passed = &#63;.
	 *
	 * @param courseId the course ID
	 * @param passed the passed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching course result
	 * @throws NoSuchCourseResultException if a matching course result could not be found
	 */
	@Override
	public CourseResult findByFinished_Last(
			long courseId, boolean passed,
			OrderByComparator<CourseResult> orderByComparator)
		throws NoSuchCourseResultException {

		CourseResult courseResult = fetchByFinished_Last(
			courseId, passed, orderByComparator);

		if (courseResult != null) {
			return courseResult;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("courseId=");
		msg.append(courseId);

		msg.append(", passed=");
		msg.append(passed);

		msg.append("}");

		throw new NoSuchCourseResultException(msg.toString());
	}

	/**
	 * Returns the last course result in the ordered set where courseId = &#63; and passed = &#63;.
	 *
	 * @param courseId the course ID
	 * @param passed the passed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching course result, or <code>null</code> if a matching course result could not be found
	 */
	@Override
	public CourseResult fetchByFinished_Last(
		long courseId, boolean passed,
		OrderByComparator<CourseResult> orderByComparator) {

		int count = countByFinished(courseId, passed);

		if (count == 0) {
			return null;
		}

		List<CourseResult> list = findByFinished(
			courseId, passed, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

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
	@Override
	public CourseResult[] findByFinished_PrevAndNext(
			long crId, long courseId, boolean passed,
			OrderByComparator<CourseResult> orderByComparator)
		throws NoSuchCourseResultException {

		CourseResult courseResult = findByPrimaryKey(crId);

		Session session = null;

		try {
			session = openSession();

			CourseResult[] array = new CourseResultImpl[3];

			array[0] = getByFinished_PrevAndNext(
				session, courseResult, courseId, passed, orderByComparator,
				true);

			array[1] = courseResult;

			array[2] = getByFinished_PrevAndNext(
				session, courseResult, courseId, passed, orderByComparator,
				false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CourseResult getByFinished_PrevAndNext(
		Session session, CourseResult courseResult, long courseId,
		boolean passed, OrderByComparator<CourseResult> orderByComparator,
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

		query.append(_SQL_SELECT_COURSERESULT_WHERE);

		query.append(_FINDER_COLUMN_FINISHED_COURSEID_2);

		query.append(_FINDER_COLUMN_FINISHED_PASSED_2);

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
			query.append(CourseResultModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(courseId);

		qPos.add(passed);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(courseResult)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<CourseResult> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the course results where courseId = &#63; and passed = &#63; from the database.
	 *
	 * @param courseId the course ID
	 * @param passed the passed
	 */
	@Override
	public void removeByFinished(long courseId, boolean passed) {
		for (CourseResult courseResult :
				findByFinished(
					courseId, passed, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(courseResult);
		}
	}

	/**
	 * Returns the number of course results where courseId = &#63; and passed = &#63;.
	 *
	 * @param courseId the course ID
	 * @param passed the passed
	 * @return the number of matching course results
	 */
	@Override
	public int countByFinished(long courseId, boolean passed) {
		FinderPath finderPath = _finderPathCountByFinished;

		Object[] finderArgs = new Object[] {courseId, passed};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_COURSERESULT_WHERE);

			query.append(_FINDER_COLUMN_FINISHED_COURSEID_2);

			query.append(_FINDER_COLUMN_FINISHED_PASSED_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(courseId);

				qPos.add(passed);

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

	private static final String _FINDER_COLUMN_FINISHED_COURSEID_2 =
		"courseResult.courseId = ? AND ";

	private static final String _FINDER_COLUMN_FINISHED_PASSED_2 =
		"courseResult.passed = ? AND courseResult.courseResult.passedDate IS NOT NULL";

	public CourseResultPersistenceImpl() {
		setModelClass(CourseResult.class);

		setModelImplClass(CourseResultImpl.class);
		setModelPKClass(long.class);
	}

	/**
	 * Caches the course result in the entity cache if it is enabled.
	 *
	 * @param courseResult the course result
	 */
	@Override
	public void cacheResult(CourseResult courseResult) {
		entityCache.putResult(
			entityCacheEnabled, CourseResultImpl.class,
			courseResult.getPrimaryKey(), courseResult);

		finderCache.putResult(
			_finderPathFetchByCourseIdUserId,
			new Object[] {courseResult.getCourseId(), courseResult.getUserId()},
			courseResult);

		courseResult.resetOriginalValues();
	}

	/**
	 * Caches the course results in the entity cache if it is enabled.
	 *
	 * @param courseResults the course results
	 */
	@Override
	public void cacheResult(List<CourseResult> courseResults) {
		for (CourseResult courseResult : courseResults) {
			if (entityCache.getResult(
					entityCacheEnabled, CourseResultImpl.class,
					courseResult.getPrimaryKey()) == null) {

				cacheResult(courseResult);
			}
			else {
				courseResult.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all course results.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CourseResultImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the course result.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CourseResult courseResult) {
		entityCache.removeResult(
			entityCacheEnabled, CourseResultImpl.class,
			courseResult.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((CourseResultModelImpl)courseResult, true);
	}

	@Override
	public void clearCache(List<CourseResult> courseResults) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CourseResult courseResult : courseResults) {
			entityCache.removeResult(
				entityCacheEnabled, CourseResultImpl.class,
				courseResult.getPrimaryKey());

			clearUniqueFindersCache((CourseResultModelImpl)courseResult, true);
		}
	}

	protected void cacheUniqueFindersCache(
		CourseResultModelImpl courseResultModelImpl) {

		Object[] args = new Object[] {
			courseResultModelImpl.getCourseId(),
			courseResultModelImpl.getUserId()
		};

		finderCache.putResult(
			_finderPathCountByCourseIdUserId, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByCourseIdUserId, args, courseResultModelImpl,
			false);
	}

	protected void clearUniqueFindersCache(
		CourseResultModelImpl courseResultModelImpl, boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				courseResultModelImpl.getCourseId(),
				courseResultModelImpl.getUserId()
			};

			finderCache.removeResult(_finderPathCountByCourseIdUserId, args);
			finderCache.removeResult(_finderPathFetchByCourseIdUserId, args);
		}

		if ((courseResultModelImpl.getColumnBitmask() &
			 _finderPathFetchByCourseIdUserId.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				courseResultModelImpl.getOriginalCourseId(),
				courseResultModelImpl.getOriginalUserId()
			};

			finderCache.removeResult(_finderPathCountByCourseIdUserId, args);
			finderCache.removeResult(_finderPathFetchByCourseIdUserId, args);
		}
	}

	/**
	 * Creates a new course result with the primary key. Does not add the course result to the database.
	 *
	 * @param crId the primary key for the new course result
	 * @return the new course result
	 */
	@Override
	public CourseResult create(long crId) {
		CourseResult courseResult = new CourseResultImpl();

		courseResult.setNew(true);
		courseResult.setPrimaryKey(crId);

		courseResult.setCompanyId(CompanyThreadLocal.getCompanyId());

		return courseResult;
	}

	/**
	 * Removes the course result with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param crId the primary key of the course result
	 * @return the course result that was removed
	 * @throws NoSuchCourseResultException if a course result with the primary key could not be found
	 */
	@Override
	public CourseResult remove(long crId) throws NoSuchCourseResultException {
		return remove((Serializable)crId);
	}

	/**
	 * Removes the course result with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the course result
	 * @return the course result that was removed
	 * @throws NoSuchCourseResultException if a course result with the primary key could not be found
	 */
	@Override
	public CourseResult remove(Serializable primaryKey)
		throws NoSuchCourseResultException {

		Session session = null;

		try {
			session = openSession();

			CourseResult courseResult = (CourseResult)session.get(
				CourseResultImpl.class, primaryKey);

			if (courseResult == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCourseResultException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(courseResult);
		}
		catch (NoSuchCourseResultException nsee) {
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
	protected CourseResult removeImpl(CourseResult courseResult) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(courseResult)) {
				courseResult = (CourseResult)session.get(
					CourseResultImpl.class, courseResult.getPrimaryKeyObj());
			}

			if (courseResult != null) {
				session.delete(courseResult);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (courseResult != null) {
			clearCache(courseResult);
		}

		return courseResult;
	}

	@Override
	public CourseResult updateImpl(CourseResult courseResult) {
		boolean isNew = courseResult.isNew();

		if (!(courseResult instanceof CourseResultModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(courseResult.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					courseResult);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in courseResult proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CourseResult implementation " +
					courseResult.getClass());
		}

		CourseResultModelImpl courseResultModelImpl =
			(CourseResultModelImpl)courseResult;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (courseResult.getCreateDate() == null)) {
			if (serviceContext == null) {
				courseResult.setCreateDate(now);
			}
			else {
				courseResult.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!courseResultModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				courseResult.setModifiedDate(now);
			}
			else {
				courseResult.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (courseResult.isNew()) {
				session.save(courseResult);

				courseResult.setNew(false);
			}
			else {
				courseResult = (CourseResult)session.merge(courseResult);
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
			Object[] args = new Object[] {courseResultModelImpl.getCourseId()};

			finderCache.removeResult(_finderPathCountByCourseId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByCourseId, args);

			args = new Object[] {courseResultModelImpl.getUserId()};

			finderCache.removeResult(_finderPathCountByUserId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUserId, args);

			args = new Object[] {
				courseResultModelImpl.getCourseId(),
				courseResultModelImpl.isPassed()
			};

			finderCache.removeResult(_finderPathCountByCourseIdPassed, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByCourseIdPassed, args);

			args = new Object[] {
				courseResultModelImpl.getCourseId(),
				courseResultModelImpl.isPassed()
			};

			finderCache.removeResult(_finderPathCountByFinished, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByFinished, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((courseResultModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByCourseId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					courseResultModelImpl.getOriginalCourseId()
				};

				finderCache.removeResult(_finderPathCountByCourseId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCourseId, args);

				args = new Object[] {courseResultModelImpl.getCourseId()};

				finderCache.removeResult(_finderPathCountByCourseId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCourseId, args);
			}

			if ((courseResultModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUserId.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					courseResultModelImpl.getOriginalUserId()
				};

				finderCache.removeResult(_finderPathCountByUserId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUserId, args);

				args = new Object[] {courseResultModelImpl.getUserId()};

				finderCache.removeResult(_finderPathCountByUserId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUserId, args);
			}

			if ((courseResultModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByCourseIdPassed.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					courseResultModelImpl.getOriginalCourseId(),
					courseResultModelImpl.getOriginalPassed()
				};

				finderCache.removeResult(
					_finderPathCountByCourseIdPassed, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCourseIdPassed, args);

				args = new Object[] {
					courseResultModelImpl.getCourseId(),
					courseResultModelImpl.isPassed()
				};

				finderCache.removeResult(
					_finderPathCountByCourseIdPassed, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCourseIdPassed, args);
			}

			if ((courseResultModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByFinished.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					courseResultModelImpl.getOriginalCourseId(),
					courseResultModelImpl.getOriginalPassed()
				};

				finderCache.removeResult(_finderPathCountByFinished, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByFinished, args);

				args = new Object[] {
					courseResultModelImpl.getCourseId(),
					courseResultModelImpl.isPassed()
				};

				finderCache.removeResult(_finderPathCountByFinished, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByFinished, args);
			}
		}

		entityCache.putResult(
			entityCacheEnabled, CourseResultImpl.class,
			courseResult.getPrimaryKey(), courseResult, false);

		clearUniqueFindersCache(courseResultModelImpl, false);
		cacheUniqueFindersCache(courseResultModelImpl);

		courseResult.resetOriginalValues();

		return courseResult;
	}

	/**
	 * Returns the course result with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the course result
	 * @return the course result
	 * @throws NoSuchCourseResultException if a course result with the primary key could not be found
	 */
	@Override
	public CourseResult findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCourseResultException {

		CourseResult courseResult = fetchByPrimaryKey(primaryKey);

		if (courseResult == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCourseResultException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return courseResult;
	}

	/**
	 * Returns the course result with the primary key or throws a <code>NoSuchCourseResultException</code> if it could not be found.
	 *
	 * @param crId the primary key of the course result
	 * @return the course result
	 * @throws NoSuchCourseResultException if a course result with the primary key could not be found
	 */
	@Override
	public CourseResult findByPrimaryKey(long crId)
		throws NoSuchCourseResultException {

		return findByPrimaryKey((Serializable)crId);
	}

	/**
	 * Returns the course result with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param crId the primary key of the course result
	 * @return the course result, or <code>null</code> if a course result with the primary key could not be found
	 */
	@Override
	public CourseResult fetchByPrimaryKey(long crId) {
		return fetchByPrimaryKey((Serializable)crId);
	}

	/**
	 * Returns all the course results.
	 *
	 * @return the course results
	 */
	@Override
	public List<CourseResult> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the course results.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CourseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of course results
	 * @param end the upper bound of the range of course results (not inclusive)
	 * @return the range of course results
	 */
	@Override
	public List<CourseResult> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the course results.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CourseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of course results
	 * @param end the upper bound of the range of course results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of course results
	 */
	@Override
	public List<CourseResult> findAll(
		int start, int end, OrderByComparator<CourseResult> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the course results.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CourseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of course results
	 * @param end the upper bound of the range of course results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of course results
	 */
	@Override
	public List<CourseResult> findAll(
		int start, int end, OrderByComparator<CourseResult> orderByComparator,
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

		List<CourseResult> list = null;

		if (useFinderCache) {
			list = (List<CourseResult>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_COURSERESULT);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_COURSERESULT;

				if (pagination) {
					sql = sql.concat(CourseResultModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CourseResult>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CourseResult>)QueryUtil.list(
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
	 * Removes all the course results from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CourseResult courseResult : findAll()) {
			remove(courseResult);
		}
	}

	/**
	 * Returns the number of course results.
	 *
	 * @return the number of course results
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_COURSERESULT);

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
		return "crId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_COURSERESULT;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return CourseResultModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the course result persistence.
	 */
	@Activate
	public void activate() {
		CourseResultModelImpl.setEntityCacheEnabled(entityCacheEnabled);
		CourseResultModelImpl.setFinderCacheEnabled(finderCacheEnabled);

		_finderPathWithPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, CourseResultImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, CourseResultImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByCourseId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, CourseResultImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCourseId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByCourseId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, CourseResultImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCourseId",
			new String[] {Long.class.getName()},
			CourseResultModelImpl.COURSEID_COLUMN_BITMASK);

		_finderPathCountByCourseId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCourseId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByUserId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, CourseResultImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUserId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, CourseResultImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
			new String[] {Long.class.getName()},
			CourseResultModelImpl.USERID_COLUMN_BITMASK);

		_finderPathCountByUserId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] {Long.class.getName()});

		_finderPathFetchByCourseIdUserId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, CourseResultImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByCourseIdUserId",
			new String[] {Long.class.getName(), Long.class.getName()},
			CourseResultModelImpl.COURSEID_COLUMN_BITMASK |
			CourseResultModelImpl.USERID_COLUMN_BITMASK);

		_finderPathCountByCourseIdUserId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCourseIdUserId",
			new String[] {Long.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByCourseIdPassed = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, CourseResultImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCourseIdPassed",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByCourseIdPassed = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, CourseResultImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCourseIdPassed",
			new String[] {Long.class.getName(), Boolean.class.getName()},
			CourseResultModelImpl.COURSEID_COLUMN_BITMASK |
			CourseResultModelImpl.PASSED_COLUMN_BITMASK);

		_finderPathCountByCourseIdPassed = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCourseIdPassed",
			new String[] {Long.class.getName(), Boolean.class.getName()});

		_finderPathWithPaginationFindByFinished = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, CourseResultImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByFinished",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByFinished = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, CourseResultImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByFinished",
			new String[] {Long.class.getName(), Boolean.class.getName()},
			CourseResultModelImpl.COURSEID_COLUMN_BITMASK |
			CourseResultModelImpl.PASSED_COLUMN_BITMASK);

		_finderPathCountByFinished = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByFinished",
			new String[] {Long.class.getName(), Boolean.class.getName()});
	}

	@Deactivate
	public void deactivate() {
		entityCache.removeCache(CourseResultImpl.class.getName());
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
				"value.object.column.bitmask.enabled.com.ted.lms.model.CourseResult"),
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

	private static final String _SQL_SELECT_COURSERESULT =
		"SELECT courseResult FROM CourseResult courseResult";

	private static final String _SQL_SELECT_COURSERESULT_WHERE =
		"SELECT courseResult FROM CourseResult courseResult WHERE ";

	private static final String _SQL_COUNT_COURSERESULT =
		"SELECT COUNT(courseResult) FROM CourseResult courseResult";

	private static final String _SQL_COUNT_COURSERESULT_WHERE =
		"SELECT COUNT(courseResult) FROM CourseResult courseResult WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "courseResult.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CourseResult exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No CourseResult exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CourseResultPersistenceImpl.class);

	static {
		try {
			Class.forName(LMSPersistenceConstants.class.getName());
		}
		catch (ClassNotFoundException cnfe) {
			throw new ExceptionInInitializerError(cnfe);
		}
	}

}