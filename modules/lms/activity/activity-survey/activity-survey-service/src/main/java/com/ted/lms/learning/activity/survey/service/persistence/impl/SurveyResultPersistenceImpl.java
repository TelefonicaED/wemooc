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

package com.ted.lms.learning.activity.survey.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import com.ted.lms.learning.activity.survey.exception.NoSuchResultException;
import com.ted.lms.learning.activity.survey.model.SurveyResult;
import com.ted.lms.learning.activity.survey.model.impl.SurveyResultImpl;
import com.ted.lms.learning.activity.survey.model.impl.SurveyResultModelImpl;
import com.ted.lms.learning.activity.survey.service.persistence.SurveyResultPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the survey result service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SurveyResultPersistence
 * @see com.ted.lms.learning.activity.survey.service.persistence.SurveyResultUtil
 * @generated
 */
@ProviderType
public class SurveyResultPersistenceImpl extends BasePersistenceImpl<SurveyResult>
	implements SurveyResultPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SurveyResultUtil} to access the survey result persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SurveyResultImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SurveyResultModelImpl.ENTITY_CACHE_ENABLED,
			SurveyResultModelImpl.FINDER_CACHE_ENABLED, SurveyResultImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SurveyResultModelImpl.ENTITY_CACHE_ENABLED,
			SurveyResultModelImpl.FINDER_CACHE_ENABLED, SurveyResultImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SurveyResultModelImpl.ENTITY_CACHE_ENABLED,
			SurveyResultModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(SurveyResultModelImpl.ENTITY_CACHE_ENABLED,
			SurveyResultModelImpl.FINDER_CACHE_ENABLED, SurveyResultImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(SurveyResultModelImpl.ENTITY_CACHE_ENABLED,
			SurveyResultModelImpl.FINDER_CACHE_ENABLED, SurveyResultImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			SurveyResultModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(SurveyResultModelImpl.ENTITY_CACHE_ENABLED,
			SurveyResultModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the survey results where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching survey results
	 */
	@Override
	public List<SurveyResult> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the survey results where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SurveyResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of survey results
	 * @param end the upper bound of the range of survey results (not inclusive)
	 * @return the range of matching survey results
	 */
	@Override
	public List<SurveyResult> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the survey results where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SurveyResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of survey results
	 * @param end the upper bound of the range of survey results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching survey results
	 */
	@Override
	public List<SurveyResult> findByUuid(String uuid, int start, int end,
		OrderByComparator<SurveyResult> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the survey results where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SurveyResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of survey results
	 * @param end the upper bound of the range of survey results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching survey results
	 */
	@Override
	public List<SurveyResult> findByUuid(String uuid, int start, int end,
		OrderByComparator<SurveyResult> orderByComparator,
		boolean retrieveFromCache) {
		uuid = Objects.toString(uuid, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid, start, end, orderByComparator };
		}

		List<SurveyResult> list = null;

		if (retrieveFromCache) {
			list = (List<SurveyResult>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SurveyResult surveyResult : list) {
					if (!uuid.equals(surveyResult.getUuid())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_SURVEYRESULT_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SurveyResultModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				if (!pagination) {
					list = (List<SurveyResult>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SurveyResult>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first survey result in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching survey result
	 * @throws NoSuchResultException if a matching survey result could not be found
	 */
	@Override
	public SurveyResult findByUuid_First(String uuid,
		OrderByComparator<SurveyResult> orderByComparator)
		throws NoSuchResultException {
		SurveyResult surveyResult = fetchByUuid_First(uuid, orderByComparator);

		if (surveyResult != null) {
			return surveyResult;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchResultException(msg.toString());
	}

	/**
	 * Returns the first survey result in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching survey result, or <code>null</code> if a matching survey result could not be found
	 */
	@Override
	public SurveyResult fetchByUuid_First(String uuid,
		OrderByComparator<SurveyResult> orderByComparator) {
		List<SurveyResult> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last survey result in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching survey result
	 * @throws NoSuchResultException if a matching survey result could not be found
	 */
	@Override
	public SurveyResult findByUuid_Last(String uuid,
		OrderByComparator<SurveyResult> orderByComparator)
		throws NoSuchResultException {
		SurveyResult surveyResult = fetchByUuid_Last(uuid, orderByComparator);

		if (surveyResult != null) {
			return surveyResult;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchResultException(msg.toString());
	}

	/**
	 * Returns the last survey result in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching survey result, or <code>null</code> if a matching survey result could not be found
	 */
	@Override
	public SurveyResult fetchByUuid_Last(String uuid,
		OrderByComparator<SurveyResult> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<SurveyResult> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the survey results before and after the current survey result in the ordered set where uuid = &#63;.
	 *
	 * @param surveyResultId the primary key of the current survey result
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next survey result
	 * @throws NoSuchResultException if a survey result with the primary key could not be found
	 */
	@Override
	public SurveyResult[] findByUuid_PrevAndNext(long surveyResultId,
		String uuid, OrderByComparator<SurveyResult> orderByComparator)
		throws NoSuchResultException {
		uuid = Objects.toString(uuid, "");

		SurveyResult surveyResult = findByPrimaryKey(surveyResultId);

		Session session = null;

		try {
			session = openSession();

			SurveyResult[] array = new SurveyResultImpl[3];

			array[0] = getByUuid_PrevAndNext(session, surveyResult, uuid,
					orderByComparator, true);

			array[1] = surveyResult;

			array[2] = getByUuid_PrevAndNext(session, surveyResult, uuid,
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

	protected SurveyResult getByUuid_PrevAndNext(Session session,
		SurveyResult surveyResult, String uuid,
		OrderByComparator<SurveyResult> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SURVEYRESULT_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			query.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

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
			query.append(SurveyResultModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(surveyResult);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SurveyResult> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the survey results where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (SurveyResult surveyResult : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(surveyResult);
		}
	}

	/**
	 * Returns the number of survey results where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching survey results
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SURVEYRESULT_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "surveyResult.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "surveyResult.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(surveyResult.uuid IS NULL OR surveyResult.uuid = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(SurveyResultModelImpl.ENTITY_CACHE_ENABLED,
			SurveyResultModelImpl.FINDER_CACHE_ENABLED, SurveyResultImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
		new FinderPath(SurveyResultModelImpl.ENTITY_CACHE_ENABLED,
			SurveyResultModelImpl.FINDER_CACHE_ENABLED, SurveyResultImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
			new String[] { Long.class.getName() },
			SurveyResultModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(SurveyResultModelImpl.ENTITY_CACHE_ENABLED,
			SurveyResultModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the survey results where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching survey results
	 */
	@Override
	public List<SurveyResult> findByUserId(long userId) {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the survey results where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SurveyResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of survey results
	 * @param end the upper bound of the range of survey results (not inclusive)
	 * @return the range of matching survey results
	 */
	@Override
	public List<SurveyResult> findByUserId(long userId, int start, int end) {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the survey results where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SurveyResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of survey results
	 * @param end the upper bound of the range of survey results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching survey results
	 */
	@Override
	public List<SurveyResult> findByUserId(long userId, int start, int end,
		OrderByComparator<SurveyResult> orderByComparator) {
		return findByUserId(userId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the survey results where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SurveyResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of survey results
	 * @param end the upper bound of the range of survey results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching survey results
	 */
	@Override
	public List<SurveyResult> findByUserId(long userId, int start, int end,
		OrderByComparator<SurveyResult> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID;
			finderArgs = new Object[] { userId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID;
			finderArgs = new Object[] { userId, start, end, orderByComparator };
		}

		List<SurveyResult> list = null;

		if (retrieveFromCache) {
			list = (List<SurveyResult>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SurveyResult surveyResult : list) {
					if ((userId != surveyResult.getUserId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_SURVEYRESULT_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SurveyResultModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (!pagination) {
					list = (List<SurveyResult>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SurveyResult>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first survey result in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching survey result
	 * @throws NoSuchResultException if a matching survey result could not be found
	 */
	@Override
	public SurveyResult findByUserId_First(long userId,
		OrderByComparator<SurveyResult> orderByComparator)
		throws NoSuchResultException {
		SurveyResult surveyResult = fetchByUserId_First(userId,
				orderByComparator);

		if (surveyResult != null) {
			return surveyResult;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append("}");

		throw new NoSuchResultException(msg.toString());
	}

	/**
	 * Returns the first survey result in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching survey result, or <code>null</code> if a matching survey result could not be found
	 */
	@Override
	public SurveyResult fetchByUserId_First(long userId,
		OrderByComparator<SurveyResult> orderByComparator) {
		List<SurveyResult> list = findByUserId(userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last survey result in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching survey result
	 * @throws NoSuchResultException if a matching survey result could not be found
	 */
	@Override
	public SurveyResult findByUserId_Last(long userId,
		OrderByComparator<SurveyResult> orderByComparator)
		throws NoSuchResultException {
		SurveyResult surveyResult = fetchByUserId_Last(userId, orderByComparator);

		if (surveyResult != null) {
			return surveyResult;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append("}");

		throw new NoSuchResultException(msg.toString());
	}

	/**
	 * Returns the last survey result in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching survey result, or <code>null</code> if a matching survey result could not be found
	 */
	@Override
	public SurveyResult fetchByUserId_Last(long userId,
		OrderByComparator<SurveyResult> orderByComparator) {
		int count = countByUserId(userId);

		if (count == 0) {
			return null;
		}

		List<SurveyResult> list = findByUserId(userId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the survey results before and after the current survey result in the ordered set where userId = &#63;.
	 *
	 * @param surveyResultId the primary key of the current survey result
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next survey result
	 * @throws NoSuchResultException if a survey result with the primary key could not be found
	 */
	@Override
	public SurveyResult[] findByUserId_PrevAndNext(long surveyResultId,
		long userId, OrderByComparator<SurveyResult> orderByComparator)
		throws NoSuchResultException {
		SurveyResult surveyResult = findByPrimaryKey(surveyResultId);

		Session session = null;

		try {
			session = openSession();

			SurveyResult[] array = new SurveyResultImpl[3];

			array[0] = getByUserId_PrevAndNext(session, surveyResult, userId,
					orderByComparator, true);

			array[1] = surveyResult;

			array[2] = getByUserId_PrevAndNext(session, surveyResult, userId,
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

	protected SurveyResult getByUserId_PrevAndNext(Session session,
		SurveyResult surveyResult, long userId,
		OrderByComparator<SurveyResult> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SURVEYRESULT_WHERE);

		query.append(_FINDER_COLUMN_USERID_USERID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

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
			query.append(SurveyResultModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(surveyResult);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SurveyResult> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the survey results where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	@Override
	public void removeByUserId(long userId) {
		for (SurveyResult surveyResult : findByUserId(userId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(surveyResult);
		}
	}

	/**
	 * Returns the number of survey results where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching survey results
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_USERID;

		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SURVEYRESULT_WHERE);

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

	private static final String _FINDER_COLUMN_USERID_USERID_2 = "surveyResult.userId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTID = new FinderPath(SurveyResultModelImpl.ENTITY_CACHE_ENABLED,
			SurveyResultModelImpl.FINDER_CACHE_ENABLED, SurveyResultImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByActId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTID = new FinderPath(SurveyResultModelImpl.ENTITY_CACHE_ENABLED,
			SurveyResultModelImpl.FINDER_CACHE_ENABLED, SurveyResultImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByActId",
			new String[] { Long.class.getName() },
			SurveyResultModelImpl.ACTID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ACTID = new FinderPath(SurveyResultModelImpl.ENTITY_CACHE_ENABLED,
			SurveyResultModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByActId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the survey results where actId = &#63;.
	 *
	 * @param actId the act ID
	 * @return the matching survey results
	 */
	@Override
	public List<SurveyResult> findByActId(long actId) {
		return findByActId(actId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the survey results where actId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SurveyResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param actId the act ID
	 * @param start the lower bound of the range of survey results
	 * @param end the upper bound of the range of survey results (not inclusive)
	 * @return the range of matching survey results
	 */
	@Override
	public List<SurveyResult> findByActId(long actId, int start, int end) {
		return findByActId(actId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the survey results where actId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SurveyResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param actId the act ID
	 * @param start the lower bound of the range of survey results
	 * @param end the upper bound of the range of survey results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching survey results
	 */
	@Override
	public List<SurveyResult> findByActId(long actId, int start, int end,
		OrderByComparator<SurveyResult> orderByComparator) {
		return findByActId(actId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the survey results where actId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SurveyResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param actId the act ID
	 * @param start the lower bound of the range of survey results
	 * @param end the upper bound of the range of survey results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching survey results
	 */
	@Override
	public List<SurveyResult> findByActId(long actId, int start, int end,
		OrderByComparator<SurveyResult> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTID;
			finderArgs = new Object[] { actId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTID;
			finderArgs = new Object[] { actId, start, end, orderByComparator };
		}

		List<SurveyResult> list = null;

		if (retrieveFromCache) {
			list = (List<SurveyResult>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SurveyResult surveyResult : list) {
					if ((actId != surveyResult.getActId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_SURVEYRESULT_WHERE);

			query.append(_FINDER_COLUMN_ACTID_ACTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SurveyResultModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(actId);

				if (!pagination) {
					list = (List<SurveyResult>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SurveyResult>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first survey result in the ordered set where actId = &#63;.
	 *
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching survey result
	 * @throws NoSuchResultException if a matching survey result could not be found
	 */
	@Override
	public SurveyResult findByActId_First(long actId,
		OrderByComparator<SurveyResult> orderByComparator)
		throws NoSuchResultException {
		SurveyResult surveyResult = fetchByActId_First(actId, orderByComparator);

		if (surveyResult != null) {
			return surveyResult;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("actId=");
		msg.append(actId);

		msg.append("}");

		throw new NoSuchResultException(msg.toString());
	}

	/**
	 * Returns the first survey result in the ordered set where actId = &#63;.
	 *
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching survey result, or <code>null</code> if a matching survey result could not be found
	 */
	@Override
	public SurveyResult fetchByActId_First(long actId,
		OrderByComparator<SurveyResult> orderByComparator) {
		List<SurveyResult> list = findByActId(actId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last survey result in the ordered set where actId = &#63;.
	 *
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching survey result
	 * @throws NoSuchResultException if a matching survey result could not be found
	 */
	@Override
	public SurveyResult findByActId_Last(long actId,
		OrderByComparator<SurveyResult> orderByComparator)
		throws NoSuchResultException {
		SurveyResult surveyResult = fetchByActId_Last(actId, orderByComparator);

		if (surveyResult != null) {
			return surveyResult;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("actId=");
		msg.append(actId);

		msg.append("}");

		throw new NoSuchResultException(msg.toString());
	}

	/**
	 * Returns the last survey result in the ordered set where actId = &#63;.
	 *
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching survey result, or <code>null</code> if a matching survey result could not be found
	 */
	@Override
	public SurveyResult fetchByActId_Last(long actId,
		OrderByComparator<SurveyResult> orderByComparator) {
		int count = countByActId(actId);

		if (count == 0) {
			return null;
		}

		List<SurveyResult> list = findByActId(actId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the survey results before and after the current survey result in the ordered set where actId = &#63;.
	 *
	 * @param surveyResultId the primary key of the current survey result
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next survey result
	 * @throws NoSuchResultException if a survey result with the primary key could not be found
	 */
	@Override
	public SurveyResult[] findByActId_PrevAndNext(long surveyResultId,
		long actId, OrderByComparator<SurveyResult> orderByComparator)
		throws NoSuchResultException {
		SurveyResult surveyResult = findByPrimaryKey(surveyResultId);

		Session session = null;

		try {
			session = openSession();

			SurveyResult[] array = new SurveyResultImpl[3];

			array[0] = getByActId_PrevAndNext(session, surveyResult, actId,
					orderByComparator, true);

			array[1] = surveyResult;

			array[2] = getByActId_PrevAndNext(session, surveyResult, actId,
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

	protected SurveyResult getByActId_PrevAndNext(Session session,
		SurveyResult surveyResult, long actId,
		OrderByComparator<SurveyResult> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SURVEYRESULT_WHERE);

		query.append(_FINDER_COLUMN_ACTID_ACTID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

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
			query.append(SurveyResultModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(actId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(surveyResult);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SurveyResult> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the survey results where actId = &#63; from the database.
	 *
	 * @param actId the act ID
	 */
	@Override
	public void removeByActId(long actId) {
		for (SurveyResult surveyResult : findByActId(actId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(surveyResult);
		}
	}

	/**
	 * Returns the number of survey results where actId = &#63;.
	 *
	 * @param actId the act ID
	 * @return the number of matching survey results
	 */
	@Override
	public int countByActId(long actId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ACTID;

		Object[] finderArgs = new Object[] { actId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SURVEYRESULT_WHERE);

			query.append(_FINDER_COLUMN_ACTID_ACTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(actId);

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

	private static final String _FINDER_COLUMN_ACTID_ACTID_2 = "surveyResult.actId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_QUESTIONIDACTID =
		new FinderPath(SurveyResultModelImpl.ENTITY_CACHE_ENABLED,
			SurveyResultModelImpl.FINDER_CACHE_ENABLED, SurveyResultImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByQuestionIdActId",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_QUESTIONIDACTID =
		new FinderPath(SurveyResultModelImpl.ENTITY_CACHE_ENABLED,
			SurveyResultModelImpl.FINDER_CACHE_ENABLED, SurveyResultImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByQuestionIdActId",
			new String[] { Long.class.getName(), Long.class.getName() },
			SurveyResultModelImpl.QUESTIONID_COLUMN_BITMASK |
			SurveyResultModelImpl.ACTID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_QUESTIONIDACTID = new FinderPath(SurveyResultModelImpl.ENTITY_CACHE_ENABLED,
			SurveyResultModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByQuestionIdActId",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the survey results where questionId = &#63; and actId = &#63;.
	 *
	 * @param questionId the question ID
	 * @param actId the act ID
	 * @return the matching survey results
	 */
	@Override
	public List<SurveyResult> findByQuestionIdActId(long questionId, long actId) {
		return findByQuestionIdActId(questionId, actId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the survey results where questionId = &#63; and actId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SurveyResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param questionId the question ID
	 * @param actId the act ID
	 * @param start the lower bound of the range of survey results
	 * @param end the upper bound of the range of survey results (not inclusive)
	 * @return the range of matching survey results
	 */
	@Override
	public List<SurveyResult> findByQuestionIdActId(long questionId,
		long actId, int start, int end) {
		return findByQuestionIdActId(questionId, actId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the survey results where questionId = &#63; and actId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SurveyResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param questionId the question ID
	 * @param actId the act ID
	 * @param start the lower bound of the range of survey results
	 * @param end the upper bound of the range of survey results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching survey results
	 */
	@Override
	public List<SurveyResult> findByQuestionIdActId(long questionId,
		long actId, int start, int end,
		OrderByComparator<SurveyResult> orderByComparator) {
		return findByQuestionIdActId(questionId, actId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the survey results where questionId = &#63; and actId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SurveyResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param questionId the question ID
	 * @param actId the act ID
	 * @param start the lower bound of the range of survey results
	 * @param end the upper bound of the range of survey results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching survey results
	 */
	@Override
	public List<SurveyResult> findByQuestionIdActId(long questionId,
		long actId, int start, int end,
		OrderByComparator<SurveyResult> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_QUESTIONIDACTID;
			finderArgs = new Object[] { questionId, actId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_QUESTIONIDACTID;
			finderArgs = new Object[] {
					questionId, actId,
					
					start, end, orderByComparator
				};
		}

		List<SurveyResult> list = null;

		if (retrieveFromCache) {
			list = (List<SurveyResult>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SurveyResult surveyResult : list) {
					if ((questionId != surveyResult.getQuestionId()) ||
							(actId != surveyResult.getActId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_SURVEYRESULT_WHERE);

			query.append(_FINDER_COLUMN_QUESTIONIDACTID_QUESTIONID_2);

			query.append(_FINDER_COLUMN_QUESTIONIDACTID_ACTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SurveyResultModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(questionId);

				qPos.add(actId);

				if (!pagination) {
					list = (List<SurveyResult>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SurveyResult>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first survey result in the ordered set where questionId = &#63; and actId = &#63;.
	 *
	 * @param questionId the question ID
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching survey result
	 * @throws NoSuchResultException if a matching survey result could not be found
	 */
	@Override
	public SurveyResult findByQuestionIdActId_First(long questionId,
		long actId, OrderByComparator<SurveyResult> orderByComparator)
		throws NoSuchResultException {
		SurveyResult surveyResult = fetchByQuestionIdActId_First(questionId,
				actId, orderByComparator);

		if (surveyResult != null) {
			return surveyResult;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("questionId=");
		msg.append(questionId);

		msg.append(", actId=");
		msg.append(actId);

		msg.append("}");

		throw new NoSuchResultException(msg.toString());
	}

	/**
	 * Returns the first survey result in the ordered set where questionId = &#63; and actId = &#63;.
	 *
	 * @param questionId the question ID
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching survey result, or <code>null</code> if a matching survey result could not be found
	 */
	@Override
	public SurveyResult fetchByQuestionIdActId_First(long questionId,
		long actId, OrderByComparator<SurveyResult> orderByComparator) {
		List<SurveyResult> list = findByQuestionIdActId(questionId, actId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last survey result in the ordered set where questionId = &#63; and actId = &#63;.
	 *
	 * @param questionId the question ID
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching survey result
	 * @throws NoSuchResultException if a matching survey result could not be found
	 */
	@Override
	public SurveyResult findByQuestionIdActId_Last(long questionId, long actId,
		OrderByComparator<SurveyResult> orderByComparator)
		throws NoSuchResultException {
		SurveyResult surveyResult = fetchByQuestionIdActId_Last(questionId,
				actId, orderByComparator);

		if (surveyResult != null) {
			return surveyResult;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("questionId=");
		msg.append(questionId);

		msg.append(", actId=");
		msg.append(actId);

		msg.append("}");

		throw new NoSuchResultException(msg.toString());
	}

	/**
	 * Returns the last survey result in the ordered set where questionId = &#63; and actId = &#63;.
	 *
	 * @param questionId the question ID
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching survey result, or <code>null</code> if a matching survey result could not be found
	 */
	@Override
	public SurveyResult fetchByQuestionIdActId_Last(long questionId,
		long actId, OrderByComparator<SurveyResult> orderByComparator) {
		int count = countByQuestionIdActId(questionId, actId);

		if (count == 0) {
			return null;
		}

		List<SurveyResult> list = findByQuestionIdActId(questionId, actId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the survey results before and after the current survey result in the ordered set where questionId = &#63; and actId = &#63;.
	 *
	 * @param surveyResultId the primary key of the current survey result
	 * @param questionId the question ID
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next survey result
	 * @throws NoSuchResultException if a survey result with the primary key could not be found
	 */
	@Override
	public SurveyResult[] findByQuestionIdActId_PrevAndNext(
		long surveyResultId, long questionId, long actId,
		OrderByComparator<SurveyResult> orderByComparator)
		throws NoSuchResultException {
		SurveyResult surveyResult = findByPrimaryKey(surveyResultId);

		Session session = null;

		try {
			session = openSession();

			SurveyResult[] array = new SurveyResultImpl[3];

			array[0] = getByQuestionIdActId_PrevAndNext(session, surveyResult,
					questionId, actId, orderByComparator, true);

			array[1] = surveyResult;

			array[2] = getByQuestionIdActId_PrevAndNext(session, surveyResult,
					questionId, actId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SurveyResult getByQuestionIdActId_PrevAndNext(Session session,
		SurveyResult surveyResult, long questionId, long actId,
		OrderByComparator<SurveyResult> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_SURVEYRESULT_WHERE);

		query.append(_FINDER_COLUMN_QUESTIONIDACTID_QUESTIONID_2);

		query.append(_FINDER_COLUMN_QUESTIONIDACTID_ACTID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

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
			query.append(SurveyResultModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(questionId);

		qPos.add(actId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(surveyResult);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SurveyResult> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the survey results where questionId = &#63; and actId = &#63; from the database.
	 *
	 * @param questionId the question ID
	 * @param actId the act ID
	 */
	@Override
	public void removeByQuestionIdActId(long questionId, long actId) {
		for (SurveyResult surveyResult : findByQuestionIdActId(questionId,
				actId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(surveyResult);
		}
	}

	/**
	 * Returns the number of survey results where questionId = &#63; and actId = &#63;.
	 *
	 * @param questionId the question ID
	 * @param actId the act ID
	 * @return the number of matching survey results
	 */
	@Override
	public int countByQuestionIdActId(long questionId, long actId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_QUESTIONIDACTID;

		Object[] finderArgs = new Object[] { questionId, actId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SURVEYRESULT_WHERE);

			query.append(_FINDER_COLUMN_QUESTIONIDACTID_QUESTIONID_2);

			query.append(_FINDER_COLUMN_QUESTIONIDACTID_ACTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(questionId);

				qPos.add(actId);

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

	private static final String _FINDER_COLUMN_QUESTIONIDACTID_QUESTIONID_2 = "surveyResult.questionId = ? AND ";
	private static final String _FINDER_COLUMN_QUESTIONIDACTID_ACTID_2 = "surveyResult.actId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_QUESTIONID =
		new FinderPath(SurveyResultModelImpl.ENTITY_CACHE_ENABLED,
			SurveyResultModelImpl.FINDER_CACHE_ENABLED, SurveyResultImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByQuestionId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_QUESTIONID =
		new FinderPath(SurveyResultModelImpl.ENTITY_CACHE_ENABLED,
			SurveyResultModelImpl.FINDER_CACHE_ENABLED, SurveyResultImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByQuestionId",
			new String[] { Long.class.getName() },
			SurveyResultModelImpl.QUESTIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_QUESTIONID = new FinderPath(SurveyResultModelImpl.ENTITY_CACHE_ENABLED,
			SurveyResultModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByQuestionId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the survey results where questionId = &#63;.
	 *
	 * @param questionId the question ID
	 * @return the matching survey results
	 */
	@Override
	public List<SurveyResult> findByQuestionId(long questionId) {
		return findByQuestionId(questionId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the survey results where questionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SurveyResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param questionId the question ID
	 * @param start the lower bound of the range of survey results
	 * @param end the upper bound of the range of survey results (not inclusive)
	 * @return the range of matching survey results
	 */
	@Override
	public List<SurveyResult> findByQuestionId(long questionId, int start,
		int end) {
		return findByQuestionId(questionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the survey results where questionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SurveyResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param questionId the question ID
	 * @param start the lower bound of the range of survey results
	 * @param end the upper bound of the range of survey results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching survey results
	 */
	@Override
	public List<SurveyResult> findByQuestionId(long questionId, int start,
		int end, OrderByComparator<SurveyResult> orderByComparator) {
		return findByQuestionId(questionId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the survey results where questionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SurveyResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param questionId the question ID
	 * @param start the lower bound of the range of survey results
	 * @param end the upper bound of the range of survey results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching survey results
	 */
	@Override
	public List<SurveyResult> findByQuestionId(long questionId, int start,
		int end, OrderByComparator<SurveyResult> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_QUESTIONID;
			finderArgs = new Object[] { questionId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_QUESTIONID;
			finderArgs = new Object[] { questionId, start, end, orderByComparator };
		}

		List<SurveyResult> list = null;

		if (retrieveFromCache) {
			list = (List<SurveyResult>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SurveyResult surveyResult : list) {
					if ((questionId != surveyResult.getQuestionId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_SURVEYRESULT_WHERE);

			query.append(_FINDER_COLUMN_QUESTIONID_QUESTIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SurveyResultModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(questionId);

				if (!pagination) {
					list = (List<SurveyResult>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SurveyResult>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first survey result in the ordered set where questionId = &#63;.
	 *
	 * @param questionId the question ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching survey result
	 * @throws NoSuchResultException if a matching survey result could not be found
	 */
	@Override
	public SurveyResult findByQuestionId_First(long questionId,
		OrderByComparator<SurveyResult> orderByComparator)
		throws NoSuchResultException {
		SurveyResult surveyResult = fetchByQuestionId_First(questionId,
				orderByComparator);

		if (surveyResult != null) {
			return surveyResult;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("questionId=");
		msg.append(questionId);

		msg.append("}");

		throw new NoSuchResultException(msg.toString());
	}

	/**
	 * Returns the first survey result in the ordered set where questionId = &#63;.
	 *
	 * @param questionId the question ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching survey result, or <code>null</code> if a matching survey result could not be found
	 */
	@Override
	public SurveyResult fetchByQuestionId_First(long questionId,
		OrderByComparator<SurveyResult> orderByComparator) {
		List<SurveyResult> list = findByQuestionId(questionId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last survey result in the ordered set where questionId = &#63;.
	 *
	 * @param questionId the question ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching survey result
	 * @throws NoSuchResultException if a matching survey result could not be found
	 */
	@Override
	public SurveyResult findByQuestionId_Last(long questionId,
		OrderByComparator<SurveyResult> orderByComparator)
		throws NoSuchResultException {
		SurveyResult surveyResult = fetchByQuestionId_Last(questionId,
				orderByComparator);

		if (surveyResult != null) {
			return surveyResult;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("questionId=");
		msg.append(questionId);

		msg.append("}");

		throw new NoSuchResultException(msg.toString());
	}

	/**
	 * Returns the last survey result in the ordered set where questionId = &#63;.
	 *
	 * @param questionId the question ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching survey result, or <code>null</code> if a matching survey result could not be found
	 */
	@Override
	public SurveyResult fetchByQuestionId_Last(long questionId,
		OrderByComparator<SurveyResult> orderByComparator) {
		int count = countByQuestionId(questionId);

		if (count == 0) {
			return null;
		}

		List<SurveyResult> list = findByQuestionId(questionId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the survey results before and after the current survey result in the ordered set where questionId = &#63;.
	 *
	 * @param surveyResultId the primary key of the current survey result
	 * @param questionId the question ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next survey result
	 * @throws NoSuchResultException if a survey result with the primary key could not be found
	 */
	@Override
	public SurveyResult[] findByQuestionId_PrevAndNext(long surveyResultId,
		long questionId, OrderByComparator<SurveyResult> orderByComparator)
		throws NoSuchResultException {
		SurveyResult surveyResult = findByPrimaryKey(surveyResultId);

		Session session = null;

		try {
			session = openSession();

			SurveyResult[] array = new SurveyResultImpl[3];

			array[0] = getByQuestionId_PrevAndNext(session, surveyResult,
					questionId, orderByComparator, true);

			array[1] = surveyResult;

			array[2] = getByQuestionId_PrevAndNext(session, surveyResult,
					questionId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SurveyResult getByQuestionId_PrevAndNext(Session session,
		SurveyResult surveyResult, long questionId,
		OrderByComparator<SurveyResult> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SURVEYRESULT_WHERE);

		query.append(_FINDER_COLUMN_QUESTIONID_QUESTIONID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

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
			query.append(SurveyResultModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(questionId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(surveyResult);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SurveyResult> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the survey results where questionId = &#63; from the database.
	 *
	 * @param questionId the question ID
	 */
	@Override
	public void removeByQuestionId(long questionId) {
		for (SurveyResult surveyResult : findByQuestionId(questionId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(surveyResult);
		}
	}

	/**
	 * Returns the number of survey results where questionId = &#63;.
	 *
	 * @param questionId the question ID
	 * @return the number of matching survey results
	 */
	@Override
	public int countByQuestionId(long questionId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_QUESTIONID;

		Object[] finderArgs = new Object[] { questionId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SURVEYRESULT_WHERE);

			query.append(_FINDER_COLUMN_QUESTIONID_QUESTIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(questionId);

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

	private static final String _FINDER_COLUMN_QUESTIONID_QUESTIONID_2 = "surveyResult.questionId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ANSWERIDQUESTIONID =
		new FinderPath(SurveyResultModelImpl.ENTITY_CACHE_ENABLED,
			SurveyResultModelImpl.FINDER_CACHE_ENABLED, SurveyResultImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAnswerIdQuestionId",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ANSWERIDQUESTIONID =
		new FinderPath(SurveyResultModelImpl.ENTITY_CACHE_ENABLED,
			SurveyResultModelImpl.FINDER_CACHE_ENABLED, SurveyResultImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByAnswerIdQuestionId",
			new String[] { Long.class.getName(), Long.class.getName() },
			SurveyResultModelImpl.ANSWERID_COLUMN_BITMASK |
			SurveyResultModelImpl.QUESTIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ANSWERIDQUESTIONID = new FinderPath(SurveyResultModelImpl.ENTITY_CACHE_ENABLED,
			SurveyResultModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByAnswerIdQuestionId",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the survey results where answerId = &#63; and questionId = &#63;.
	 *
	 * @param answerId the answer ID
	 * @param questionId the question ID
	 * @return the matching survey results
	 */
	@Override
	public List<SurveyResult> findByAnswerIdQuestionId(long answerId,
		long questionId) {
		return findByAnswerIdQuestionId(answerId, questionId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the survey results where answerId = &#63; and questionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SurveyResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param answerId the answer ID
	 * @param questionId the question ID
	 * @param start the lower bound of the range of survey results
	 * @param end the upper bound of the range of survey results (not inclusive)
	 * @return the range of matching survey results
	 */
	@Override
	public List<SurveyResult> findByAnswerIdQuestionId(long answerId,
		long questionId, int start, int end) {
		return findByAnswerIdQuestionId(answerId, questionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the survey results where answerId = &#63; and questionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SurveyResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param answerId the answer ID
	 * @param questionId the question ID
	 * @param start the lower bound of the range of survey results
	 * @param end the upper bound of the range of survey results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching survey results
	 */
	@Override
	public List<SurveyResult> findByAnswerIdQuestionId(long answerId,
		long questionId, int start, int end,
		OrderByComparator<SurveyResult> orderByComparator) {
		return findByAnswerIdQuestionId(answerId, questionId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the survey results where answerId = &#63; and questionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SurveyResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param answerId the answer ID
	 * @param questionId the question ID
	 * @param start the lower bound of the range of survey results
	 * @param end the upper bound of the range of survey results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching survey results
	 */
	@Override
	public List<SurveyResult> findByAnswerIdQuestionId(long answerId,
		long questionId, int start, int end,
		OrderByComparator<SurveyResult> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ANSWERIDQUESTIONID;
			finderArgs = new Object[] { answerId, questionId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ANSWERIDQUESTIONID;
			finderArgs = new Object[] {
					answerId, questionId,
					
					start, end, orderByComparator
				};
		}

		List<SurveyResult> list = null;

		if (retrieveFromCache) {
			list = (List<SurveyResult>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SurveyResult surveyResult : list) {
					if ((answerId != surveyResult.getAnswerId()) ||
							(questionId != surveyResult.getQuestionId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_SURVEYRESULT_WHERE);

			query.append(_FINDER_COLUMN_ANSWERIDQUESTIONID_ANSWERID_2);

			query.append(_FINDER_COLUMN_ANSWERIDQUESTIONID_QUESTIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SurveyResultModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(answerId);

				qPos.add(questionId);

				if (!pagination) {
					list = (List<SurveyResult>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SurveyResult>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first survey result in the ordered set where answerId = &#63; and questionId = &#63;.
	 *
	 * @param answerId the answer ID
	 * @param questionId the question ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching survey result
	 * @throws NoSuchResultException if a matching survey result could not be found
	 */
	@Override
	public SurveyResult findByAnswerIdQuestionId_First(long answerId,
		long questionId, OrderByComparator<SurveyResult> orderByComparator)
		throws NoSuchResultException {
		SurveyResult surveyResult = fetchByAnswerIdQuestionId_First(answerId,
				questionId, orderByComparator);

		if (surveyResult != null) {
			return surveyResult;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("answerId=");
		msg.append(answerId);

		msg.append(", questionId=");
		msg.append(questionId);

		msg.append("}");

		throw new NoSuchResultException(msg.toString());
	}

	/**
	 * Returns the first survey result in the ordered set where answerId = &#63; and questionId = &#63;.
	 *
	 * @param answerId the answer ID
	 * @param questionId the question ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching survey result, or <code>null</code> if a matching survey result could not be found
	 */
	@Override
	public SurveyResult fetchByAnswerIdQuestionId_First(long answerId,
		long questionId, OrderByComparator<SurveyResult> orderByComparator) {
		List<SurveyResult> list = findByAnswerIdQuestionId(answerId,
				questionId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last survey result in the ordered set where answerId = &#63; and questionId = &#63;.
	 *
	 * @param answerId the answer ID
	 * @param questionId the question ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching survey result
	 * @throws NoSuchResultException if a matching survey result could not be found
	 */
	@Override
	public SurveyResult findByAnswerIdQuestionId_Last(long answerId,
		long questionId, OrderByComparator<SurveyResult> orderByComparator)
		throws NoSuchResultException {
		SurveyResult surveyResult = fetchByAnswerIdQuestionId_Last(answerId,
				questionId, orderByComparator);

		if (surveyResult != null) {
			return surveyResult;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("answerId=");
		msg.append(answerId);

		msg.append(", questionId=");
		msg.append(questionId);

		msg.append("}");

		throw new NoSuchResultException(msg.toString());
	}

	/**
	 * Returns the last survey result in the ordered set where answerId = &#63; and questionId = &#63;.
	 *
	 * @param answerId the answer ID
	 * @param questionId the question ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching survey result, or <code>null</code> if a matching survey result could not be found
	 */
	@Override
	public SurveyResult fetchByAnswerIdQuestionId_Last(long answerId,
		long questionId, OrderByComparator<SurveyResult> orderByComparator) {
		int count = countByAnswerIdQuestionId(answerId, questionId);

		if (count == 0) {
			return null;
		}

		List<SurveyResult> list = findByAnswerIdQuestionId(answerId,
				questionId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the survey results before and after the current survey result in the ordered set where answerId = &#63; and questionId = &#63;.
	 *
	 * @param surveyResultId the primary key of the current survey result
	 * @param answerId the answer ID
	 * @param questionId the question ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next survey result
	 * @throws NoSuchResultException if a survey result with the primary key could not be found
	 */
	@Override
	public SurveyResult[] findByAnswerIdQuestionId_PrevAndNext(
		long surveyResultId, long answerId, long questionId,
		OrderByComparator<SurveyResult> orderByComparator)
		throws NoSuchResultException {
		SurveyResult surveyResult = findByPrimaryKey(surveyResultId);

		Session session = null;

		try {
			session = openSession();

			SurveyResult[] array = new SurveyResultImpl[3];

			array[0] = getByAnswerIdQuestionId_PrevAndNext(session,
					surveyResult, answerId, questionId, orderByComparator, true);

			array[1] = surveyResult;

			array[2] = getByAnswerIdQuestionId_PrevAndNext(session,
					surveyResult, answerId, questionId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SurveyResult getByAnswerIdQuestionId_PrevAndNext(
		Session session, SurveyResult surveyResult, long answerId,
		long questionId, OrderByComparator<SurveyResult> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_SURVEYRESULT_WHERE);

		query.append(_FINDER_COLUMN_ANSWERIDQUESTIONID_ANSWERID_2);

		query.append(_FINDER_COLUMN_ANSWERIDQUESTIONID_QUESTIONID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

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
			query.append(SurveyResultModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(answerId);

		qPos.add(questionId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(surveyResult);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SurveyResult> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the survey results where answerId = &#63; and questionId = &#63; from the database.
	 *
	 * @param answerId the answer ID
	 * @param questionId the question ID
	 */
	@Override
	public void removeByAnswerIdQuestionId(long answerId, long questionId) {
		for (SurveyResult surveyResult : findByAnswerIdQuestionId(answerId,
				questionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(surveyResult);
		}
	}

	/**
	 * Returns the number of survey results where answerId = &#63; and questionId = &#63;.
	 *
	 * @param answerId the answer ID
	 * @param questionId the question ID
	 * @return the number of matching survey results
	 */
	@Override
	public int countByAnswerIdQuestionId(long answerId, long questionId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ANSWERIDQUESTIONID;

		Object[] finderArgs = new Object[] { answerId, questionId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SURVEYRESULT_WHERE);

			query.append(_FINDER_COLUMN_ANSWERIDQUESTIONID_ANSWERID_2);

			query.append(_FINDER_COLUMN_ANSWERIDQUESTIONID_QUESTIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(answerId);

				qPos.add(questionId);

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

	private static final String _FINDER_COLUMN_ANSWERIDQUESTIONID_ANSWERID_2 = "surveyResult.answerId = ? AND ";
	private static final String _FINDER_COLUMN_ANSWERIDQUESTIONID_QUESTIONID_2 = "surveyResult.questionId = ?";

	public SurveyResultPersistenceImpl() {
		setModelClass(SurveyResult.class);

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
					"_dbColumnNames");

			field.setAccessible(true);

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("uuid", "uuid_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the survey result in the entity cache if it is enabled.
	 *
	 * @param surveyResult the survey result
	 */
	@Override
	public void cacheResult(SurveyResult surveyResult) {
		entityCache.putResult(SurveyResultModelImpl.ENTITY_CACHE_ENABLED,
			SurveyResultImpl.class, surveyResult.getPrimaryKey(), surveyResult);

		surveyResult.resetOriginalValues();
	}

	/**
	 * Caches the survey results in the entity cache if it is enabled.
	 *
	 * @param surveyResults the survey results
	 */
	@Override
	public void cacheResult(List<SurveyResult> surveyResults) {
		for (SurveyResult surveyResult : surveyResults) {
			if (entityCache.getResult(
						SurveyResultModelImpl.ENTITY_CACHE_ENABLED,
						SurveyResultImpl.class, surveyResult.getPrimaryKey()) == null) {
				cacheResult(surveyResult);
			}
			else {
				surveyResult.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all survey results.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SurveyResultImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the survey result.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SurveyResult surveyResult) {
		entityCache.removeResult(SurveyResultModelImpl.ENTITY_CACHE_ENABLED,
			SurveyResultImpl.class, surveyResult.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<SurveyResult> surveyResults) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SurveyResult surveyResult : surveyResults) {
			entityCache.removeResult(SurveyResultModelImpl.ENTITY_CACHE_ENABLED,
				SurveyResultImpl.class, surveyResult.getPrimaryKey());
		}
	}

	/**
	 * Creates a new survey result with the primary key. Does not add the survey result to the database.
	 *
	 * @param surveyResultId the primary key for the new survey result
	 * @return the new survey result
	 */
	@Override
	public SurveyResult create(long surveyResultId) {
		SurveyResult surveyResult = new SurveyResultImpl();

		surveyResult.setNew(true);
		surveyResult.setPrimaryKey(surveyResultId);

		String uuid = PortalUUIDUtil.generate();

		surveyResult.setUuid(uuid);

		return surveyResult;
	}

	/**
	 * Removes the survey result with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param surveyResultId the primary key of the survey result
	 * @return the survey result that was removed
	 * @throws NoSuchResultException if a survey result with the primary key could not be found
	 */
	@Override
	public SurveyResult remove(long surveyResultId)
		throws NoSuchResultException {
		return remove((Serializable)surveyResultId);
	}

	/**
	 * Removes the survey result with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the survey result
	 * @return the survey result that was removed
	 * @throws NoSuchResultException if a survey result with the primary key could not be found
	 */
	@Override
	public SurveyResult remove(Serializable primaryKey)
		throws NoSuchResultException {
		Session session = null;

		try {
			session = openSession();

			SurveyResult surveyResult = (SurveyResult)session.get(SurveyResultImpl.class,
					primaryKey);

			if (surveyResult == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchResultException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(surveyResult);
		}
		catch (NoSuchResultException nsee) {
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
	protected SurveyResult removeImpl(SurveyResult surveyResult) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(surveyResult)) {
				surveyResult = (SurveyResult)session.get(SurveyResultImpl.class,
						surveyResult.getPrimaryKeyObj());
			}

			if (surveyResult != null) {
				session.delete(surveyResult);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (surveyResult != null) {
			clearCache(surveyResult);
		}

		return surveyResult;
	}

	@Override
	public SurveyResult updateImpl(SurveyResult surveyResult) {
		boolean isNew = surveyResult.isNew();

		if (!(surveyResult instanceof SurveyResultModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(surveyResult.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(surveyResult);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in surveyResult proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom SurveyResult implementation " +
				surveyResult.getClass());
		}

		SurveyResultModelImpl surveyResultModelImpl = (SurveyResultModelImpl)surveyResult;

		if (Validator.isNull(surveyResult.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			surveyResult.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (surveyResult.isNew()) {
				session.save(surveyResult);

				surveyResult.setNew(false);
			}
			else {
				surveyResult = (SurveyResult)session.merge(surveyResult);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!SurveyResultModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { surveyResultModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] { surveyResultModelImpl.getUserId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
				args);

			args = new Object[] { surveyResultModelImpl.getActId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ACTID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTID,
				args);

			args = new Object[] {
					surveyResultModelImpl.getQuestionId(),
					surveyResultModelImpl.getActId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_QUESTIONIDACTID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_QUESTIONIDACTID,
				args);

			args = new Object[] { surveyResultModelImpl.getQuestionId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_QUESTIONID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_QUESTIONID,
				args);

			args = new Object[] {
					surveyResultModelImpl.getAnswerId(),
					surveyResultModelImpl.getQuestionId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ANSWERIDQUESTIONID,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ANSWERIDQUESTIONID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((surveyResultModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						surveyResultModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { surveyResultModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((surveyResultModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						surveyResultModelImpl.getOriginalUserId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);

				args = new Object[] { surveyResultModelImpl.getUserId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);
			}

			if ((surveyResultModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						surveyResultModelImpl.getOriginalActId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ACTID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTID,
					args);

				args = new Object[] { surveyResultModelImpl.getActId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ACTID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTID,
					args);
			}

			if ((surveyResultModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_QUESTIONIDACTID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						surveyResultModelImpl.getOriginalQuestionId(),
						surveyResultModelImpl.getOriginalActId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_QUESTIONIDACTID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_QUESTIONIDACTID,
					args);

				args = new Object[] {
						surveyResultModelImpl.getQuestionId(),
						surveyResultModelImpl.getActId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_QUESTIONIDACTID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_QUESTIONIDACTID,
					args);
			}

			if ((surveyResultModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_QUESTIONID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						surveyResultModelImpl.getOriginalQuestionId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_QUESTIONID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_QUESTIONID,
					args);

				args = new Object[] { surveyResultModelImpl.getQuestionId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_QUESTIONID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_QUESTIONID,
					args);
			}

			if ((surveyResultModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ANSWERIDQUESTIONID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						surveyResultModelImpl.getOriginalAnswerId(),
						surveyResultModelImpl.getOriginalQuestionId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ANSWERIDQUESTIONID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ANSWERIDQUESTIONID,
					args);

				args = new Object[] {
						surveyResultModelImpl.getAnswerId(),
						surveyResultModelImpl.getQuestionId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ANSWERIDQUESTIONID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ANSWERIDQUESTIONID,
					args);
			}
		}

		entityCache.putResult(SurveyResultModelImpl.ENTITY_CACHE_ENABLED,
			SurveyResultImpl.class, surveyResult.getPrimaryKey(), surveyResult,
			false);

		surveyResult.resetOriginalValues();

		return surveyResult;
	}

	/**
	 * Returns the survey result with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the survey result
	 * @return the survey result
	 * @throws NoSuchResultException if a survey result with the primary key could not be found
	 */
	@Override
	public SurveyResult findByPrimaryKey(Serializable primaryKey)
		throws NoSuchResultException {
		SurveyResult surveyResult = fetchByPrimaryKey(primaryKey);

		if (surveyResult == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchResultException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return surveyResult;
	}

	/**
	 * Returns the survey result with the primary key or throws a {@link NoSuchResultException} if it could not be found.
	 *
	 * @param surveyResultId the primary key of the survey result
	 * @return the survey result
	 * @throws NoSuchResultException if a survey result with the primary key could not be found
	 */
	@Override
	public SurveyResult findByPrimaryKey(long surveyResultId)
		throws NoSuchResultException {
		return findByPrimaryKey((Serializable)surveyResultId);
	}

	/**
	 * Returns the survey result with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the survey result
	 * @return the survey result, or <code>null</code> if a survey result with the primary key could not be found
	 */
	@Override
	public SurveyResult fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(SurveyResultModelImpl.ENTITY_CACHE_ENABLED,
				SurveyResultImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		SurveyResult surveyResult = (SurveyResult)serializable;

		if (surveyResult == null) {
			Session session = null;

			try {
				session = openSession();

				surveyResult = (SurveyResult)session.get(SurveyResultImpl.class,
						primaryKey);

				if (surveyResult != null) {
					cacheResult(surveyResult);
				}
				else {
					entityCache.putResult(SurveyResultModelImpl.ENTITY_CACHE_ENABLED,
						SurveyResultImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(SurveyResultModelImpl.ENTITY_CACHE_ENABLED,
					SurveyResultImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return surveyResult;
	}

	/**
	 * Returns the survey result with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param surveyResultId the primary key of the survey result
	 * @return the survey result, or <code>null</code> if a survey result with the primary key could not be found
	 */
	@Override
	public SurveyResult fetchByPrimaryKey(long surveyResultId) {
		return fetchByPrimaryKey((Serializable)surveyResultId);
	}

	@Override
	public Map<Serializable, SurveyResult> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, SurveyResult> map = new HashMap<Serializable, SurveyResult>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			SurveyResult surveyResult = fetchByPrimaryKey(primaryKey);

			if (surveyResult != null) {
				map.put(primaryKey, surveyResult);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(SurveyResultModelImpl.ENTITY_CACHE_ENABLED,
					SurveyResultImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (SurveyResult)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_SURVEYRESULT_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append((long)primaryKey);

			query.append(",");
		}

		query.setIndex(query.index() - 1);

		query.append(")");

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (SurveyResult surveyResult : (List<SurveyResult>)q.list()) {
				map.put(surveyResult.getPrimaryKeyObj(), surveyResult);

				cacheResult(surveyResult);

				uncachedPrimaryKeys.remove(surveyResult.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(SurveyResultModelImpl.ENTITY_CACHE_ENABLED,
					SurveyResultImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the survey results.
	 *
	 * @return the survey results
	 */
	@Override
	public List<SurveyResult> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the survey results.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SurveyResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of survey results
	 * @param end the upper bound of the range of survey results (not inclusive)
	 * @return the range of survey results
	 */
	@Override
	public List<SurveyResult> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the survey results.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SurveyResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of survey results
	 * @param end the upper bound of the range of survey results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of survey results
	 */
	@Override
	public List<SurveyResult> findAll(int start, int end,
		OrderByComparator<SurveyResult> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the survey results.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SurveyResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of survey results
	 * @param end the upper bound of the range of survey results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of survey results
	 */
	@Override
	public List<SurveyResult> findAll(int start, int end,
		OrderByComparator<SurveyResult> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<SurveyResult> list = null;

		if (retrieveFromCache) {
			list = (List<SurveyResult>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_SURVEYRESULT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SURVEYRESULT;

				if (pagination) {
					sql = sql.concat(SurveyResultModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<SurveyResult>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SurveyResult>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the survey results from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SurveyResult surveyResult : findAll()) {
			remove(surveyResult);
		}
	}

	/**
	 * Returns the number of survey results.
	 *
	 * @return the number of survey results
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SURVEYRESULT);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return SurveyResultModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the survey result persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(SurveyResultImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_SURVEYRESULT = "SELECT surveyResult FROM SurveyResult surveyResult";
	private static final String _SQL_SELECT_SURVEYRESULT_WHERE_PKS_IN = "SELECT surveyResult FROM SurveyResult surveyResult WHERE surveyResultId IN (";
	private static final String _SQL_SELECT_SURVEYRESULT_WHERE = "SELECT surveyResult FROM SurveyResult surveyResult WHERE ";
	private static final String _SQL_COUNT_SURVEYRESULT = "SELECT COUNT(surveyResult) FROM SurveyResult surveyResult";
	private static final String _SQL_COUNT_SURVEYRESULT_WHERE = "SELECT COUNT(surveyResult) FROM SurveyResult surveyResult WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "surveyResult.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SurveyResult exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No SurveyResult exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(SurveyResultPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}