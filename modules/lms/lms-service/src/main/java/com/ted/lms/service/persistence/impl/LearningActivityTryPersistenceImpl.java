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
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import com.ted.lms.exception.NoSuchLearningActivityTryException;
import com.ted.lms.model.LearningActivityTry;
import com.ted.lms.model.impl.LearningActivityTryImpl;
import com.ted.lms.model.impl.LearningActivityTryModelImpl;
import com.ted.lms.service.persistence.LearningActivityTryPersistence;
import com.ted.lms.service.persistence.impl.constants.LMSPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.sql.Timestamp;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.annotation.versioning.ProviderType;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the learning activity try service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = LearningActivityTryPersistence.class)
@ProviderType
public class LearningActivityTryPersistenceImpl
	extends BasePersistenceImpl<LearningActivityTry>
	implements LearningActivityTryPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>LearningActivityTryUtil</code> to access the learning activity try persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		LearningActivityTryImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByUuid;
	private FinderPath _finderPathWithoutPaginationFindByUuid;
	private FinderPath _finderPathCountByUuid;

	/**
	 * Returns all the learning activity tries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching learning activity tries
	 */
	@Override
	public List<LearningActivityTry> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the learning activity tries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityTryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of learning activity tries
	 * @param end the upper bound of the range of learning activity tries (not inclusive)
	 * @return the range of matching learning activity tries
	 */
	@Override
	public List<LearningActivityTry> findByUuid(
		String uuid, int start, int end) {

		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the learning activity tries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityTryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of learning activity tries
	 * @param end the upper bound of the range of learning activity tries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching learning activity tries
	 */
	@Override
	public List<LearningActivityTry> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<LearningActivityTry> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the learning activity tries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityTryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of learning activity tries
	 * @param end the upper bound of the range of learning activity tries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching learning activity tries
	 */
	@Override
	public List<LearningActivityTry> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<LearningActivityTry> orderByComparator,
		boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUuid;
				finderArgs = new Object[] {uuid};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUuid;
			finderArgs = new Object[] {uuid, start, end, orderByComparator};
		}

		List<LearningActivityTry> list = null;

		if (useFinderCache) {
			list = (List<LearningActivityTry>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LearningActivityTry learningActivityTry : list) {
					if (!uuid.equals(learningActivityTry.getUuid())) {
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

			query.append(_SQL_SELECT_LEARNINGACTIVITYTRY_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(LearningActivityTryModelImpl.ORDER_BY_JPQL);
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
					list = (List<LearningActivityTry>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LearningActivityTry>)QueryUtil.list(
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
	 * Returns the first learning activity try in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity try
	 * @throws NoSuchLearningActivityTryException if a matching learning activity try could not be found
	 */
	@Override
	public LearningActivityTry findByUuid_First(
			String uuid,
			OrderByComparator<LearningActivityTry> orderByComparator)
		throws NoSuchLearningActivityTryException {

		LearningActivityTry learningActivityTry = fetchByUuid_First(
			uuid, orderByComparator);

		if (learningActivityTry != null) {
			return learningActivityTry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchLearningActivityTryException(msg.toString());
	}

	/**
	 * Returns the first learning activity try in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity try, or <code>null</code> if a matching learning activity try could not be found
	 */
	@Override
	public LearningActivityTry fetchByUuid_First(
		String uuid, OrderByComparator<LearningActivityTry> orderByComparator) {

		List<LearningActivityTry> list = findByUuid(
			uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last learning activity try in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity try
	 * @throws NoSuchLearningActivityTryException if a matching learning activity try could not be found
	 */
	@Override
	public LearningActivityTry findByUuid_Last(
			String uuid,
			OrderByComparator<LearningActivityTry> orderByComparator)
		throws NoSuchLearningActivityTryException {

		LearningActivityTry learningActivityTry = fetchByUuid_Last(
			uuid, orderByComparator);

		if (learningActivityTry != null) {
			return learningActivityTry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchLearningActivityTryException(msg.toString());
	}

	/**
	 * Returns the last learning activity try in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity try, or <code>null</code> if a matching learning activity try could not be found
	 */
	@Override
	public LearningActivityTry fetchByUuid_Last(
		String uuid, OrderByComparator<LearningActivityTry> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<LearningActivityTry> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the learning activity tries before and after the current learning activity try in the ordered set where uuid = &#63;.
	 *
	 * @param latId the primary key of the current learning activity try
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next learning activity try
	 * @throws NoSuchLearningActivityTryException if a learning activity try with the primary key could not be found
	 */
	@Override
	public LearningActivityTry[] findByUuid_PrevAndNext(
			long latId, String uuid,
			OrderByComparator<LearningActivityTry> orderByComparator)
		throws NoSuchLearningActivityTryException {

		uuid = Objects.toString(uuid, "");

		LearningActivityTry learningActivityTry = findByPrimaryKey(latId);

		Session session = null;

		try {
			session = openSession();

			LearningActivityTry[] array = new LearningActivityTryImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, learningActivityTry, uuid, orderByComparator, true);

			array[1] = learningActivityTry;

			array[2] = getByUuid_PrevAndNext(
				session, learningActivityTry, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LearningActivityTry getByUuid_PrevAndNext(
		Session session, LearningActivityTry learningActivityTry, String uuid,
		OrderByComparator<LearningActivityTry> orderByComparator,
		boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LEARNINGACTIVITYTRY_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			query.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2);
		}

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
			query.append(LearningActivityTryModelImpl.ORDER_BY_JPQL);
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
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						learningActivityTry)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<LearningActivityTry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the learning activity tries where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (LearningActivityTry learningActivityTry :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(learningActivityTry);
		}
	}

	/**
	 * Returns the number of learning activity tries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching learning activity tries
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LEARNINGACTIVITYTRY_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_2 =
		"learningActivityTry.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(learningActivityTry.uuid IS NULL OR learningActivityTry.uuid = '')";

	private FinderPath _finderPathFetchByUUID_G;
	private FinderPath _finderPathCountByUUID_G;

	/**
	 * Returns the learning activity try where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchLearningActivityTryException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching learning activity try
	 * @throws NoSuchLearningActivityTryException if a matching learning activity try could not be found
	 */
	@Override
	public LearningActivityTry findByUUID_G(String uuid, long groupId)
		throws NoSuchLearningActivityTryException {

		LearningActivityTry learningActivityTry = fetchByUUID_G(uuid, groupId);

		if (learningActivityTry == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchLearningActivityTryException(msg.toString());
		}

		return learningActivityTry;
	}

	/**
	 * Returns the learning activity try where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching learning activity try, or <code>null</code> if a matching learning activity try could not be found
	 */
	@Override
	public LearningActivityTry fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the learning activity try where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching learning activity try, or <code>null</code> if a matching learning activity try could not be found
	 */
	@Override
	public LearningActivityTry fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {uuid, groupId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByUUID_G, finderArgs, this);
		}

		if (result instanceof LearningActivityTry) {
			LearningActivityTry learningActivityTry =
				(LearningActivityTry)result;

			if (!Objects.equals(uuid, learningActivityTry.getUuid()) ||
				(groupId != learningActivityTry.getGroupId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_LEARNINGACTIVITYTRY_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				List<LearningActivityTry> list = q.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByUUID_G, finderArgs, list);
					}
				}
				else {
					LearningActivityTry learningActivityTry = list.get(0);

					result = learningActivityTry;

					cacheResult(learningActivityTry);
				}
			}
			catch (Exception e) {
				if (useFinderCache) {
					finderCache.removeResult(
						_finderPathFetchByUUID_G, finderArgs);
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
			return (LearningActivityTry)result;
		}
	}

	/**
	 * Removes the learning activity try where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the learning activity try that was removed
	 */
	@Override
	public LearningActivityTry removeByUUID_G(String uuid, long groupId)
		throws NoSuchLearningActivityTryException {

		LearningActivityTry learningActivityTry = findByUUID_G(uuid, groupId);

		return remove(learningActivityTry);
	}

	/**
	 * Returns the number of learning activity tries where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching learning activity tries
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUUID_G;

		Object[] finderArgs = new Object[] {uuid, groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LEARNINGACTIVITYTRY_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_2 =
		"learningActivityTry.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_G_UUID_3 =
		"(learningActivityTry.uuid IS NULL OR learningActivityTry.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 =
		"learningActivityTry.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the learning activity tries where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching learning activity tries
	 */
	@Override
	public List<LearningActivityTry> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the learning activity tries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityTryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of learning activity tries
	 * @param end the upper bound of the range of learning activity tries (not inclusive)
	 * @return the range of matching learning activity tries
	 */
	@Override
	public List<LearningActivityTry> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the learning activity tries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityTryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of learning activity tries
	 * @param end the upper bound of the range of learning activity tries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching learning activity tries
	 */
	@Override
	public List<LearningActivityTry> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<LearningActivityTry> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the learning activity tries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityTryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of learning activity tries
	 * @param end the upper bound of the range of learning activity tries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching learning activity tries
	 */
	@Override
	public List<LearningActivityTry> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<LearningActivityTry> orderByComparator,
		boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUuid_C;
				finderArgs = new Object[] {uuid, companyId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUuid_C;
			finderArgs = new Object[] {
				uuid, companyId, start, end, orderByComparator
			};
		}

		List<LearningActivityTry> list = null;

		if (useFinderCache) {
			list = (List<LearningActivityTry>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LearningActivityTry learningActivityTry : list) {
					if (!uuid.equals(learningActivityTry.getUuid()) ||
						(companyId != learningActivityTry.getCompanyId())) {

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

			query.append(_SQL_SELECT_LEARNINGACTIVITYTRY_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(LearningActivityTryModelImpl.ORDER_BY_JPQL);
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

				qPos.add(companyId);

				if (!pagination) {
					list = (List<LearningActivityTry>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LearningActivityTry>)QueryUtil.list(
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
	 * Returns the first learning activity try in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity try
	 * @throws NoSuchLearningActivityTryException if a matching learning activity try could not be found
	 */
	@Override
	public LearningActivityTry findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<LearningActivityTry> orderByComparator)
		throws NoSuchLearningActivityTryException {

		LearningActivityTry learningActivityTry = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (learningActivityTry != null) {
			return learningActivityTry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchLearningActivityTryException(msg.toString());
	}

	/**
	 * Returns the first learning activity try in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity try, or <code>null</code> if a matching learning activity try could not be found
	 */
	@Override
	public LearningActivityTry fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<LearningActivityTry> orderByComparator) {

		List<LearningActivityTry> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last learning activity try in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity try
	 * @throws NoSuchLearningActivityTryException if a matching learning activity try could not be found
	 */
	@Override
	public LearningActivityTry findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<LearningActivityTry> orderByComparator)
		throws NoSuchLearningActivityTryException {

		LearningActivityTry learningActivityTry = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (learningActivityTry != null) {
			return learningActivityTry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchLearningActivityTryException(msg.toString());
	}

	/**
	 * Returns the last learning activity try in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity try, or <code>null</code> if a matching learning activity try could not be found
	 */
	@Override
	public LearningActivityTry fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<LearningActivityTry> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<LearningActivityTry> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the learning activity tries before and after the current learning activity try in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param latId the primary key of the current learning activity try
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next learning activity try
	 * @throws NoSuchLearningActivityTryException if a learning activity try with the primary key could not be found
	 */
	@Override
	public LearningActivityTry[] findByUuid_C_PrevAndNext(
			long latId, String uuid, long companyId,
			OrderByComparator<LearningActivityTry> orderByComparator)
		throws NoSuchLearningActivityTryException {

		uuid = Objects.toString(uuid, "");

		LearningActivityTry learningActivityTry = findByPrimaryKey(latId);

		Session session = null;

		try {
			session = openSession();

			LearningActivityTry[] array = new LearningActivityTryImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, learningActivityTry, uuid, companyId,
				orderByComparator, true);

			array[1] = learningActivityTry;

			array[2] = getByUuid_C_PrevAndNext(
				session, learningActivityTry, uuid, companyId,
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

	protected LearningActivityTry getByUuid_C_PrevAndNext(
		Session session, LearningActivityTry learningActivityTry, String uuid,
		long companyId,
		OrderByComparator<LearningActivityTry> orderByComparator,
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

		query.append(_SQL_SELECT_LEARNINGACTIVITYTRY_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_C_UUID_2);
		}

		query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

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
			query.append(LearningActivityTryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		qPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						learningActivityTry)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<LearningActivityTry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the learning activity tries where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (LearningActivityTry learningActivityTry :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(learningActivityTry);
		}
	}

	/**
	 * Returns the number of learning activity tries where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching learning activity tries
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LEARNINGACTIVITYTRY_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_2 =
		"learningActivityTry.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(learningActivityTry.uuid IS NULL OR learningActivityTry.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"learningActivityTry.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByActId;
	private FinderPath _finderPathWithoutPaginationFindByActId;
	private FinderPath _finderPathCountByActId;

	/**
	 * Returns all the learning activity tries where actId = &#63;.
	 *
	 * @param actId the act ID
	 * @return the matching learning activity tries
	 */
	@Override
	public List<LearningActivityTry> findByActId(long actId) {
		return findByActId(actId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the learning activity tries where actId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityTryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param actId the act ID
	 * @param start the lower bound of the range of learning activity tries
	 * @param end the upper bound of the range of learning activity tries (not inclusive)
	 * @return the range of matching learning activity tries
	 */
	@Override
	public List<LearningActivityTry> findByActId(
		long actId, int start, int end) {

		return findByActId(actId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the learning activity tries where actId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityTryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param actId the act ID
	 * @param start the lower bound of the range of learning activity tries
	 * @param end the upper bound of the range of learning activity tries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching learning activity tries
	 */
	@Override
	public List<LearningActivityTry> findByActId(
		long actId, int start, int end,
		OrderByComparator<LearningActivityTry> orderByComparator) {

		return findByActId(actId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the learning activity tries where actId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityTryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param actId the act ID
	 * @param start the lower bound of the range of learning activity tries
	 * @param end the upper bound of the range of learning activity tries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching learning activity tries
	 */
	@Override
	public List<LearningActivityTry> findByActId(
		long actId, int start, int end,
		OrderByComparator<LearningActivityTry> orderByComparator,
		boolean useFinderCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByActId;
				finderArgs = new Object[] {actId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByActId;
			finderArgs = new Object[] {actId, start, end, orderByComparator};
		}

		List<LearningActivityTry> list = null;

		if (useFinderCache) {
			list = (List<LearningActivityTry>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LearningActivityTry learningActivityTry : list) {
					if ((actId != learningActivityTry.getActId())) {
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

			query.append(_SQL_SELECT_LEARNINGACTIVITYTRY_WHERE);

			query.append(_FINDER_COLUMN_ACTID_ACTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(LearningActivityTryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(actId);

				if (!pagination) {
					list = (List<LearningActivityTry>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LearningActivityTry>)QueryUtil.list(
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
	 * Returns the first learning activity try in the ordered set where actId = &#63;.
	 *
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity try
	 * @throws NoSuchLearningActivityTryException if a matching learning activity try could not be found
	 */
	@Override
	public LearningActivityTry findByActId_First(
			long actId,
			OrderByComparator<LearningActivityTry> orderByComparator)
		throws NoSuchLearningActivityTryException {

		LearningActivityTry learningActivityTry = fetchByActId_First(
			actId, orderByComparator);

		if (learningActivityTry != null) {
			return learningActivityTry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("actId=");
		msg.append(actId);

		msg.append("}");

		throw new NoSuchLearningActivityTryException(msg.toString());
	}

	/**
	 * Returns the first learning activity try in the ordered set where actId = &#63;.
	 *
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity try, or <code>null</code> if a matching learning activity try could not be found
	 */
	@Override
	public LearningActivityTry fetchByActId_First(
		long actId, OrderByComparator<LearningActivityTry> orderByComparator) {

		List<LearningActivityTry> list = findByActId(
			actId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last learning activity try in the ordered set where actId = &#63;.
	 *
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity try
	 * @throws NoSuchLearningActivityTryException if a matching learning activity try could not be found
	 */
	@Override
	public LearningActivityTry findByActId_Last(
			long actId,
			OrderByComparator<LearningActivityTry> orderByComparator)
		throws NoSuchLearningActivityTryException {

		LearningActivityTry learningActivityTry = fetchByActId_Last(
			actId, orderByComparator);

		if (learningActivityTry != null) {
			return learningActivityTry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("actId=");
		msg.append(actId);

		msg.append("}");

		throw new NoSuchLearningActivityTryException(msg.toString());
	}

	/**
	 * Returns the last learning activity try in the ordered set where actId = &#63;.
	 *
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity try, or <code>null</code> if a matching learning activity try could not be found
	 */
	@Override
	public LearningActivityTry fetchByActId_Last(
		long actId, OrderByComparator<LearningActivityTry> orderByComparator) {

		int count = countByActId(actId);

		if (count == 0) {
			return null;
		}

		List<LearningActivityTry> list = findByActId(
			actId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the learning activity tries before and after the current learning activity try in the ordered set where actId = &#63;.
	 *
	 * @param latId the primary key of the current learning activity try
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next learning activity try
	 * @throws NoSuchLearningActivityTryException if a learning activity try with the primary key could not be found
	 */
	@Override
	public LearningActivityTry[] findByActId_PrevAndNext(
			long latId, long actId,
			OrderByComparator<LearningActivityTry> orderByComparator)
		throws NoSuchLearningActivityTryException {

		LearningActivityTry learningActivityTry = findByPrimaryKey(latId);

		Session session = null;

		try {
			session = openSession();

			LearningActivityTry[] array = new LearningActivityTryImpl[3];

			array[0] = getByActId_PrevAndNext(
				session, learningActivityTry, actId, orderByComparator, true);

			array[1] = learningActivityTry;

			array[2] = getByActId_PrevAndNext(
				session, learningActivityTry, actId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LearningActivityTry getByActId_PrevAndNext(
		Session session, LearningActivityTry learningActivityTry, long actId,
		OrderByComparator<LearningActivityTry> orderByComparator,
		boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LEARNINGACTIVITYTRY_WHERE);

		query.append(_FINDER_COLUMN_ACTID_ACTID_2);

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
			query.append(LearningActivityTryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(actId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						learningActivityTry)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<LearningActivityTry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the learning activity tries where actId = &#63; from the database.
	 *
	 * @param actId the act ID
	 */
	@Override
	public void removeByActId(long actId) {
		for (LearningActivityTry learningActivityTry :
				findByActId(
					actId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(learningActivityTry);
		}
	}

	/**
	 * Returns the number of learning activity tries where actId = &#63;.
	 *
	 * @param actId the act ID
	 * @return the number of matching learning activity tries
	 */
	@Override
	public int countByActId(long actId) {
		FinderPath finderPath = _finderPathCountByActId;

		Object[] finderArgs = new Object[] {actId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LEARNINGACTIVITYTRY_WHERE);

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

	private static final String _FINDER_COLUMN_ACTID_ACTID_2 =
		"learningActivityTry.actId = ?";

	private FinderPath _finderPathWithPaginationFindByActIdUserId;
	private FinderPath _finderPathWithoutPaginationFindByActIdUserId;
	private FinderPath _finderPathCountByActIdUserId;

	/**
	 * Returns all the learning activity tries where actId = &#63; and userId = &#63;.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @return the matching learning activity tries
	 */
	@Override
	public List<LearningActivityTry> findByActIdUserId(
		long actId, long userId) {

		return findByActIdUserId(
			actId, userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the learning activity tries where actId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityTryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of learning activity tries
	 * @param end the upper bound of the range of learning activity tries (not inclusive)
	 * @return the range of matching learning activity tries
	 */
	@Override
	public List<LearningActivityTry> findByActIdUserId(
		long actId, long userId, int start, int end) {

		return findByActIdUserId(actId, userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the learning activity tries where actId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityTryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of learning activity tries
	 * @param end the upper bound of the range of learning activity tries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching learning activity tries
	 */
	@Override
	public List<LearningActivityTry> findByActIdUserId(
		long actId, long userId, int start, int end,
		OrderByComparator<LearningActivityTry> orderByComparator) {

		return findByActIdUserId(
			actId, userId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the learning activity tries where actId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityTryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of learning activity tries
	 * @param end the upper bound of the range of learning activity tries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching learning activity tries
	 */
	@Override
	public List<LearningActivityTry> findByActIdUserId(
		long actId, long userId, int start, int end,
		OrderByComparator<LearningActivityTry> orderByComparator,
		boolean useFinderCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByActIdUserId;
				finderArgs = new Object[] {actId, userId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByActIdUserId;
			finderArgs = new Object[] {
				actId, userId, start, end, orderByComparator
			};
		}

		List<LearningActivityTry> list = null;

		if (useFinderCache) {
			list = (List<LearningActivityTry>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LearningActivityTry learningActivityTry : list) {
					if ((actId != learningActivityTry.getActId()) ||
						(userId != learningActivityTry.getUserId())) {

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

			query.append(_SQL_SELECT_LEARNINGACTIVITYTRY_WHERE);

			query.append(_FINDER_COLUMN_ACTIDUSERID_ACTID_2);

			query.append(_FINDER_COLUMN_ACTIDUSERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(LearningActivityTryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(actId);

				qPos.add(userId);

				if (!pagination) {
					list = (List<LearningActivityTry>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LearningActivityTry>)QueryUtil.list(
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
	 * Returns the first learning activity try in the ordered set where actId = &#63; and userId = &#63;.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity try
	 * @throws NoSuchLearningActivityTryException if a matching learning activity try could not be found
	 */
	@Override
	public LearningActivityTry findByActIdUserId_First(
			long actId, long userId,
			OrderByComparator<LearningActivityTry> orderByComparator)
		throws NoSuchLearningActivityTryException {

		LearningActivityTry learningActivityTry = fetchByActIdUserId_First(
			actId, userId, orderByComparator);

		if (learningActivityTry != null) {
			return learningActivityTry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("actId=");
		msg.append(actId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append("}");

		throw new NoSuchLearningActivityTryException(msg.toString());
	}

	/**
	 * Returns the first learning activity try in the ordered set where actId = &#63; and userId = &#63;.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity try, or <code>null</code> if a matching learning activity try could not be found
	 */
	@Override
	public LearningActivityTry fetchByActIdUserId_First(
		long actId, long userId,
		OrderByComparator<LearningActivityTry> orderByComparator) {

		List<LearningActivityTry> list = findByActIdUserId(
			actId, userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last learning activity try in the ordered set where actId = &#63; and userId = &#63;.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity try
	 * @throws NoSuchLearningActivityTryException if a matching learning activity try could not be found
	 */
	@Override
	public LearningActivityTry findByActIdUserId_Last(
			long actId, long userId,
			OrderByComparator<LearningActivityTry> orderByComparator)
		throws NoSuchLearningActivityTryException {

		LearningActivityTry learningActivityTry = fetchByActIdUserId_Last(
			actId, userId, orderByComparator);

		if (learningActivityTry != null) {
			return learningActivityTry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("actId=");
		msg.append(actId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append("}");

		throw new NoSuchLearningActivityTryException(msg.toString());
	}

	/**
	 * Returns the last learning activity try in the ordered set where actId = &#63; and userId = &#63;.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity try, or <code>null</code> if a matching learning activity try could not be found
	 */
	@Override
	public LearningActivityTry fetchByActIdUserId_Last(
		long actId, long userId,
		OrderByComparator<LearningActivityTry> orderByComparator) {

		int count = countByActIdUserId(actId, userId);

		if (count == 0) {
			return null;
		}

		List<LearningActivityTry> list = findByActIdUserId(
			actId, userId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the learning activity tries before and after the current learning activity try in the ordered set where actId = &#63; and userId = &#63;.
	 *
	 * @param latId the primary key of the current learning activity try
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next learning activity try
	 * @throws NoSuchLearningActivityTryException if a learning activity try with the primary key could not be found
	 */
	@Override
	public LearningActivityTry[] findByActIdUserId_PrevAndNext(
			long latId, long actId, long userId,
			OrderByComparator<LearningActivityTry> orderByComparator)
		throws NoSuchLearningActivityTryException {

		LearningActivityTry learningActivityTry = findByPrimaryKey(latId);

		Session session = null;

		try {
			session = openSession();

			LearningActivityTry[] array = new LearningActivityTryImpl[3];

			array[0] = getByActIdUserId_PrevAndNext(
				session, learningActivityTry, actId, userId, orderByComparator,
				true);

			array[1] = learningActivityTry;

			array[2] = getByActIdUserId_PrevAndNext(
				session, learningActivityTry, actId, userId, orderByComparator,
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

	protected LearningActivityTry getByActIdUserId_PrevAndNext(
		Session session, LearningActivityTry learningActivityTry, long actId,
		long userId, OrderByComparator<LearningActivityTry> orderByComparator,
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

		query.append(_SQL_SELECT_LEARNINGACTIVITYTRY_WHERE);

		query.append(_FINDER_COLUMN_ACTIDUSERID_ACTID_2);

		query.append(_FINDER_COLUMN_ACTIDUSERID_USERID_2);

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
			query.append(LearningActivityTryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(actId);

		qPos.add(userId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						learningActivityTry)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<LearningActivityTry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the learning activity tries where actId = &#63; and userId = &#63; from the database.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 */
	@Override
	public void removeByActIdUserId(long actId, long userId) {
		for (LearningActivityTry learningActivityTry :
				findByActIdUserId(
					actId, userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(learningActivityTry);
		}
	}

	/**
	 * Returns the number of learning activity tries where actId = &#63; and userId = &#63;.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @return the number of matching learning activity tries
	 */
	@Override
	public int countByActIdUserId(long actId, long userId) {
		FinderPath finderPath = _finderPathCountByActIdUserId;

		Object[] finderArgs = new Object[] {actId, userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LEARNINGACTIVITYTRY_WHERE);

			query.append(_FINDER_COLUMN_ACTIDUSERID_ACTID_2);

			query.append(_FINDER_COLUMN_ACTIDUSERID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(actId);

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

	private static final String _FINDER_COLUMN_ACTIDUSERID_ACTID_2 =
		"learningActivityTry.actId = ? AND ";

	private static final String _FINDER_COLUMN_ACTIDUSERID_USERID_2 =
		"learningActivityTry.userId = ?";

	private FinderPath _finderPathWithPaginationFindByActIdUserIdEndDate;
	private FinderPath _finderPathWithoutPaginationFindByActIdUserIdEndDate;
	private FinderPath _finderPathCountByActIdUserIdEndDate;

	/**
	 * Returns all the learning activity tries where actId = &#63; and userId = &#63; and endDate = &#63;.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param endDate the end date
	 * @return the matching learning activity tries
	 */
	@Override
	public List<LearningActivityTry> findByActIdUserIdEndDate(
		long actId, long userId, Date endDate) {

		return findByActIdUserIdEndDate(
			actId, userId, endDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the learning activity tries where actId = &#63; and userId = &#63; and endDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityTryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param endDate the end date
	 * @param start the lower bound of the range of learning activity tries
	 * @param end the upper bound of the range of learning activity tries (not inclusive)
	 * @return the range of matching learning activity tries
	 */
	@Override
	public List<LearningActivityTry> findByActIdUserIdEndDate(
		long actId, long userId, Date endDate, int start, int end) {

		return findByActIdUserIdEndDate(
			actId, userId, endDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the learning activity tries where actId = &#63; and userId = &#63; and endDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityTryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param endDate the end date
	 * @param start the lower bound of the range of learning activity tries
	 * @param end the upper bound of the range of learning activity tries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching learning activity tries
	 */
	@Override
	public List<LearningActivityTry> findByActIdUserIdEndDate(
		long actId, long userId, Date endDate, int start, int end,
		OrderByComparator<LearningActivityTry> orderByComparator) {

		return findByActIdUserIdEndDate(
			actId, userId, endDate, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the learning activity tries where actId = &#63; and userId = &#63; and endDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityTryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param endDate the end date
	 * @param start the lower bound of the range of learning activity tries
	 * @param end the upper bound of the range of learning activity tries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching learning activity tries
	 */
	@Override
	public List<LearningActivityTry> findByActIdUserIdEndDate(
		long actId, long userId, Date endDate, int start, int end,
		OrderByComparator<LearningActivityTry> orderByComparator,
		boolean useFinderCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindByActIdUserIdEndDate;
				finderArgs = new Object[] {actId, userId, _getTime(endDate)};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByActIdUserIdEndDate;
			finderArgs = new Object[] {
				actId, userId, _getTime(endDate), start, end, orderByComparator
			};
		}

		List<LearningActivityTry> list = null;

		if (useFinderCache) {
			list = (List<LearningActivityTry>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LearningActivityTry learningActivityTry : list) {
					if ((actId != learningActivityTry.getActId()) ||
						(userId != learningActivityTry.getUserId()) ||
						!Objects.equals(
							endDate, learningActivityTry.getEndDate())) {

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
					5 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_LEARNINGACTIVITYTRY_WHERE);

			query.append(_FINDER_COLUMN_ACTIDUSERIDENDDATE_ACTID_2);

			query.append(_FINDER_COLUMN_ACTIDUSERIDENDDATE_USERID_2);

			boolean bindEndDate = false;

			if (endDate == null) {
				query.append(_FINDER_COLUMN_ACTIDUSERIDENDDATE_ENDDATE_1);
			}
			else {
				bindEndDate = true;

				query.append(_FINDER_COLUMN_ACTIDUSERIDENDDATE_ENDDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(LearningActivityTryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(actId);

				qPos.add(userId);

				if (bindEndDate) {
					qPos.add(new Timestamp(endDate.getTime()));
				}

				if (!pagination) {
					list = (List<LearningActivityTry>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LearningActivityTry>)QueryUtil.list(
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
	 * Returns the first learning activity try in the ordered set where actId = &#63; and userId = &#63; and endDate = &#63;.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param endDate the end date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity try
	 * @throws NoSuchLearningActivityTryException if a matching learning activity try could not be found
	 */
	@Override
	public LearningActivityTry findByActIdUserIdEndDate_First(
			long actId, long userId, Date endDate,
			OrderByComparator<LearningActivityTry> orderByComparator)
		throws NoSuchLearningActivityTryException {

		LearningActivityTry learningActivityTry =
			fetchByActIdUserIdEndDate_First(
				actId, userId, endDate, orderByComparator);

		if (learningActivityTry != null) {
			return learningActivityTry;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("actId=");
		msg.append(actId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(", endDate=");
		msg.append(endDate);

		msg.append("}");

		throw new NoSuchLearningActivityTryException(msg.toString());
	}

	/**
	 * Returns the first learning activity try in the ordered set where actId = &#63; and userId = &#63; and endDate = &#63;.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param endDate the end date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity try, or <code>null</code> if a matching learning activity try could not be found
	 */
	@Override
	public LearningActivityTry fetchByActIdUserIdEndDate_First(
		long actId, long userId, Date endDate,
		OrderByComparator<LearningActivityTry> orderByComparator) {

		List<LearningActivityTry> list = findByActIdUserIdEndDate(
			actId, userId, endDate, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last learning activity try in the ordered set where actId = &#63; and userId = &#63; and endDate = &#63;.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param endDate the end date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity try
	 * @throws NoSuchLearningActivityTryException if a matching learning activity try could not be found
	 */
	@Override
	public LearningActivityTry findByActIdUserIdEndDate_Last(
			long actId, long userId, Date endDate,
			OrderByComparator<LearningActivityTry> orderByComparator)
		throws NoSuchLearningActivityTryException {

		LearningActivityTry learningActivityTry =
			fetchByActIdUserIdEndDate_Last(
				actId, userId, endDate, orderByComparator);

		if (learningActivityTry != null) {
			return learningActivityTry;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("actId=");
		msg.append(actId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(", endDate=");
		msg.append(endDate);

		msg.append("}");

		throw new NoSuchLearningActivityTryException(msg.toString());
	}

	/**
	 * Returns the last learning activity try in the ordered set where actId = &#63; and userId = &#63; and endDate = &#63;.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param endDate the end date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity try, or <code>null</code> if a matching learning activity try could not be found
	 */
	@Override
	public LearningActivityTry fetchByActIdUserIdEndDate_Last(
		long actId, long userId, Date endDate,
		OrderByComparator<LearningActivityTry> orderByComparator) {

		int count = countByActIdUserIdEndDate(actId, userId, endDate);

		if (count == 0) {
			return null;
		}

		List<LearningActivityTry> list = findByActIdUserIdEndDate(
			actId, userId, endDate, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the learning activity tries before and after the current learning activity try in the ordered set where actId = &#63; and userId = &#63; and endDate = &#63;.
	 *
	 * @param latId the primary key of the current learning activity try
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param endDate the end date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next learning activity try
	 * @throws NoSuchLearningActivityTryException if a learning activity try with the primary key could not be found
	 */
	@Override
	public LearningActivityTry[] findByActIdUserIdEndDate_PrevAndNext(
			long latId, long actId, long userId, Date endDate,
			OrderByComparator<LearningActivityTry> orderByComparator)
		throws NoSuchLearningActivityTryException {

		LearningActivityTry learningActivityTry = findByPrimaryKey(latId);

		Session session = null;

		try {
			session = openSession();

			LearningActivityTry[] array = new LearningActivityTryImpl[3];

			array[0] = getByActIdUserIdEndDate_PrevAndNext(
				session, learningActivityTry, actId, userId, endDate,
				orderByComparator, true);

			array[1] = learningActivityTry;

			array[2] = getByActIdUserIdEndDate_PrevAndNext(
				session, learningActivityTry, actId, userId, endDate,
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

	protected LearningActivityTry getByActIdUserIdEndDate_PrevAndNext(
		Session session, LearningActivityTry learningActivityTry, long actId,
		long userId, Date endDate,
		OrderByComparator<LearningActivityTry> orderByComparator,
		boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				6 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_LEARNINGACTIVITYTRY_WHERE);

		query.append(_FINDER_COLUMN_ACTIDUSERIDENDDATE_ACTID_2);

		query.append(_FINDER_COLUMN_ACTIDUSERIDENDDATE_USERID_2);

		boolean bindEndDate = false;

		if (endDate == null) {
			query.append(_FINDER_COLUMN_ACTIDUSERIDENDDATE_ENDDATE_1);
		}
		else {
			bindEndDate = true;

			query.append(_FINDER_COLUMN_ACTIDUSERIDENDDATE_ENDDATE_2);
		}

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
			query.append(LearningActivityTryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(actId);

		qPos.add(userId);

		if (bindEndDate) {
			qPos.add(new Timestamp(endDate.getTime()));
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						learningActivityTry)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<LearningActivityTry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the learning activity tries where actId = &#63; and userId = &#63; and endDate = &#63; from the database.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param endDate the end date
	 */
	@Override
	public void removeByActIdUserIdEndDate(
		long actId, long userId, Date endDate) {

		for (LearningActivityTry learningActivityTry :
				findByActIdUserIdEndDate(
					actId, userId, endDate, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(learningActivityTry);
		}
	}

	/**
	 * Returns the number of learning activity tries where actId = &#63; and userId = &#63; and endDate = &#63;.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param endDate the end date
	 * @return the number of matching learning activity tries
	 */
	@Override
	public int countByActIdUserIdEndDate(
		long actId, long userId, Date endDate) {

		FinderPath finderPath = _finderPathCountByActIdUserIdEndDate;

		Object[] finderArgs = new Object[] {actId, userId, _getTime(endDate)};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_LEARNINGACTIVITYTRY_WHERE);

			query.append(_FINDER_COLUMN_ACTIDUSERIDENDDATE_ACTID_2);

			query.append(_FINDER_COLUMN_ACTIDUSERIDENDDATE_USERID_2);

			boolean bindEndDate = false;

			if (endDate == null) {
				query.append(_FINDER_COLUMN_ACTIDUSERIDENDDATE_ENDDATE_1);
			}
			else {
				bindEndDate = true;

				query.append(_FINDER_COLUMN_ACTIDUSERIDENDDATE_ENDDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(actId);

				qPos.add(userId);

				if (bindEndDate) {
					qPos.add(new Timestamp(endDate.getTime()));
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

	private static final String _FINDER_COLUMN_ACTIDUSERIDENDDATE_ACTID_2 =
		"learningActivityTry.actId = ? AND ";

	private static final String _FINDER_COLUMN_ACTIDUSERIDENDDATE_USERID_2 =
		"learningActivityTry.userId = ? AND ";

	private static final String _FINDER_COLUMN_ACTIDUSERIDENDDATE_ENDDATE_1 =
		"learningActivityTry.endDate IS NULL";

	private static final String _FINDER_COLUMN_ACTIDUSERIDENDDATE_ENDDATE_2 =
		"learningActivityTry.endDate = ?";

	public LearningActivityTryPersistenceImpl() {
		setModelClass(LearningActivityTry.class);

		setModelImplClass(LearningActivityTryImpl.class);
		setModelPKClass(long.class);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");

		setDBColumnNames(dbColumnNames);
	}

	/**
	 * Caches the learning activity try in the entity cache if it is enabled.
	 *
	 * @param learningActivityTry the learning activity try
	 */
	@Override
	public void cacheResult(LearningActivityTry learningActivityTry) {
		entityCache.putResult(
			entityCacheEnabled, LearningActivityTryImpl.class,
			learningActivityTry.getPrimaryKey(), learningActivityTry);

		finderCache.putResult(
			_finderPathFetchByUUID_G,
			new Object[] {
				learningActivityTry.getUuid(), learningActivityTry.getGroupId()
			},
			learningActivityTry);

		learningActivityTry.resetOriginalValues();
	}

	/**
	 * Caches the learning activity tries in the entity cache if it is enabled.
	 *
	 * @param learningActivityTries the learning activity tries
	 */
	@Override
	public void cacheResult(List<LearningActivityTry> learningActivityTries) {
		for (LearningActivityTry learningActivityTry : learningActivityTries) {
			if (entityCache.getResult(
					entityCacheEnabled, LearningActivityTryImpl.class,
					learningActivityTry.getPrimaryKey()) == null) {

				cacheResult(learningActivityTry);
			}
			else {
				learningActivityTry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all learning activity tries.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(LearningActivityTryImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the learning activity try.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(LearningActivityTry learningActivityTry) {
		entityCache.removeResult(
			entityCacheEnabled, LearningActivityTryImpl.class,
			learningActivityTry.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(
			(LearningActivityTryModelImpl)learningActivityTry, true);
	}

	@Override
	public void clearCache(List<LearningActivityTry> learningActivityTries) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (LearningActivityTry learningActivityTry : learningActivityTries) {
			entityCache.removeResult(
				entityCacheEnabled, LearningActivityTryImpl.class,
				learningActivityTry.getPrimaryKey());

			clearUniqueFindersCache(
				(LearningActivityTryModelImpl)learningActivityTry, true);
		}
	}

	protected void cacheUniqueFindersCache(
		LearningActivityTryModelImpl learningActivityTryModelImpl) {

		Object[] args = new Object[] {
			learningActivityTryModelImpl.getUuid(),
			learningActivityTryModelImpl.getGroupId()
		};

		finderCache.putResult(
			_finderPathCountByUUID_G, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByUUID_G, args, learningActivityTryModelImpl,
			false);
	}

	protected void clearUniqueFindersCache(
		LearningActivityTryModelImpl learningActivityTryModelImpl,
		boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				learningActivityTryModelImpl.getUuid(),
				learningActivityTryModelImpl.getGroupId()
			};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
		}

		if ((learningActivityTryModelImpl.getColumnBitmask() &
			 _finderPathFetchByUUID_G.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				learningActivityTryModelImpl.getOriginalUuid(),
				learningActivityTryModelImpl.getOriginalGroupId()
			};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
		}
	}

	/**
	 * Creates a new learning activity try with the primary key. Does not add the learning activity try to the database.
	 *
	 * @param latId the primary key for the new learning activity try
	 * @return the new learning activity try
	 */
	@Override
	public LearningActivityTry create(long latId) {
		LearningActivityTry learningActivityTry = new LearningActivityTryImpl();

		learningActivityTry.setNew(true);
		learningActivityTry.setPrimaryKey(latId);

		String uuid = PortalUUIDUtil.generate();

		learningActivityTry.setUuid(uuid);

		learningActivityTry.setCompanyId(CompanyThreadLocal.getCompanyId());

		return learningActivityTry;
	}

	/**
	 * Removes the learning activity try with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param latId the primary key of the learning activity try
	 * @return the learning activity try that was removed
	 * @throws NoSuchLearningActivityTryException if a learning activity try with the primary key could not be found
	 */
	@Override
	public LearningActivityTry remove(long latId)
		throws NoSuchLearningActivityTryException {

		return remove((Serializable)latId);
	}

	/**
	 * Removes the learning activity try with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the learning activity try
	 * @return the learning activity try that was removed
	 * @throws NoSuchLearningActivityTryException if a learning activity try with the primary key could not be found
	 */
	@Override
	public LearningActivityTry remove(Serializable primaryKey)
		throws NoSuchLearningActivityTryException {

		Session session = null;

		try {
			session = openSession();

			LearningActivityTry learningActivityTry =
				(LearningActivityTry)session.get(
					LearningActivityTryImpl.class, primaryKey);

			if (learningActivityTry == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLearningActivityTryException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(learningActivityTry);
		}
		catch (NoSuchLearningActivityTryException nsee) {
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
	protected LearningActivityTry removeImpl(
		LearningActivityTry learningActivityTry) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(learningActivityTry)) {
				learningActivityTry = (LearningActivityTry)session.get(
					LearningActivityTryImpl.class,
					learningActivityTry.getPrimaryKeyObj());
			}

			if (learningActivityTry != null) {
				session.delete(learningActivityTry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (learningActivityTry != null) {
			clearCache(learningActivityTry);
		}

		return learningActivityTry;
	}

	@Override
	public LearningActivityTry updateImpl(
		LearningActivityTry learningActivityTry) {

		boolean isNew = learningActivityTry.isNew();

		if (!(learningActivityTry instanceof LearningActivityTryModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(learningActivityTry.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					learningActivityTry);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in learningActivityTry proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom LearningActivityTry implementation " +
					learningActivityTry.getClass());
		}

		LearningActivityTryModelImpl learningActivityTryModelImpl =
			(LearningActivityTryModelImpl)learningActivityTry;

		if (Validator.isNull(learningActivityTry.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			learningActivityTry.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (learningActivityTry.getCreateDate() == null)) {
			if (serviceContext == null) {
				learningActivityTry.setCreateDate(now);
			}
			else {
				learningActivityTry.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!learningActivityTryModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				learningActivityTry.setModifiedDate(now);
			}
			else {
				learningActivityTry.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (learningActivityTry.isNew()) {
				session.save(learningActivityTry);

				learningActivityTry.setNew(false);
			}
			else {
				learningActivityTry = (LearningActivityTry)session.merge(
					learningActivityTry);
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
				learningActivityTryModelImpl.getUuid()
			};

			finderCache.removeResult(_finderPathCountByUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid, args);

			args = new Object[] {
				learningActivityTryModelImpl.getUuid(),
				learningActivityTryModelImpl.getCompanyId()
			};

			finderCache.removeResult(_finderPathCountByUuid_C, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid_C, args);

			args = new Object[] {learningActivityTryModelImpl.getActId()};

			finderCache.removeResult(_finderPathCountByActId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByActId, args);

			args = new Object[] {
				learningActivityTryModelImpl.getActId(),
				learningActivityTryModelImpl.getUserId()
			};

			finderCache.removeResult(_finderPathCountByActIdUserId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByActIdUserId, args);

			args = new Object[] {
				learningActivityTryModelImpl.getActId(),
				learningActivityTryModelImpl.getUserId(),
				learningActivityTryModelImpl.getEndDate()
			};

			finderCache.removeResult(
				_finderPathCountByActIdUserIdEndDate, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByActIdUserIdEndDate, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((learningActivityTryModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					learningActivityTryModelImpl.getOriginalUuid()
				};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);

				args = new Object[] {learningActivityTryModelImpl.getUuid()};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);
			}

			if ((learningActivityTryModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid_C.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					learningActivityTryModelImpl.getOriginalUuid(),
					learningActivityTryModelImpl.getOriginalCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);

				args = new Object[] {
					learningActivityTryModelImpl.getUuid(),
					learningActivityTryModelImpl.getCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);
			}

			if ((learningActivityTryModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByActId.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					learningActivityTryModelImpl.getOriginalActId()
				};

				finderCache.removeResult(_finderPathCountByActId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByActId, args);

				args = new Object[] {learningActivityTryModelImpl.getActId()};

				finderCache.removeResult(_finderPathCountByActId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByActId, args);
			}

			if ((learningActivityTryModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByActIdUserId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					learningActivityTryModelImpl.getOriginalActId(),
					learningActivityTryModelImpl.getOriginalUserId()
				};

				finderCache.removeResult(_finderPathCountByActIdUserId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByActIdUserId, args);

				args = new Object[] {
					learningActivityTryModelImpl.getActId(),
					learningActivityTryModelImpl.getUserId()
				};

				finderCache.removeResult(_finderPathCountByActIdUserId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByActIdUserId, args);
			}

			if ((learningActivityTryModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByActIdUserIdEndDate.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					learningActivityTryModelImpl.getOriginalActId(),
					learningActivityTryModelImpl.getOriginalUserId(),
					learningActivityTryModelImpl.getOriginalEndDate()
				};

				finderCache.removeResult(
					_finderPathCountByActIdUserIdEndDate, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByActIdUserIdEndDate, args);

				args = new Object[] {
					learningActivityTryModelImpl.getActId(),
					learningActivityTryModelImpl.getUserId(),
					learningActivityTryModelImpl.getEndDate()
				};

				finderCache.removeResult(
					_finderPathCountByActIdUserIdEndDate, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByActIdUserIdEndDate, args);
			}
		}

		entityCache.putResult(
			entityCacheEnabled, LearningActivityTryImpl.class,
			learningActivityTry.getPrimaryKey(), learningActivityTry, false);

		clearUniqueFindersCache(learningActivityTryModelImpl, false);
		cacheUniqueFindersCache(learningActivityTryModelImpl);

		learningActivityTry.resetOriginalValues();

		return learningActivityTry;
	}

	/**
	 * Returns the learning activity try with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the learning activity try
	 * @return the learning activity try
	 * @throws NoSuchLearningActivityTryException if a learning activity try with the primary key could not be found
	 */
	@Override
	public LearningActivityTry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLearningActivityTryException {

		LearningActivityTry learningActivityTry = fetchByPrimaryKey(primaryKey);

		if (learningActivityTry == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLearningActivityTryException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return learningActivityTry;
	}

	/**
	 * Returns the learning activity try with the primary key or throws a <code>NoSuchLearningActivityTryException</code> if it could not be found.
	 *
	 * @param latId the primary key of the learning activity try
	 * @return the learning activity try
	 * @throws NoSuchLearningActivityTryException if a learning activity try with the primary key could not be found
	 */
	@Override
	public LearningActivityTry findByPrimaryKey(long latId)
		throws NoSuchLearningActivityTryException {

		return findByPrimaryKey((Serializable)latId);
	}

	/**
	 * Returns the learning activity try with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param latId the primary key of the learning activity try
	 * @return the learning activity try, or <code>null</code> if a learning activity try with the primary key could not be found
	 */
	@Override
	public LearningActivityTry fetchByPrimaryKey(long latId) {
		return fetchByPrimaryKey((Serializable)latId);
	}

	/**
	 * Returns all the learning activity tries.
	 *
	 * @return the learning activity tries
	 */
	@Override
	public List<LearningActivityTry> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the learning activity tries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityTryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of learning activity tries
	 * @param end the upper bound of the range of learning activity tries (not inclusive)
	 * @return the range of learning activity tries
	 */
	@Override
	public List<LearningActivityTry> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the learning activity tries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityTryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of learning activity tries
	 * @param end the upper bound of the range of learning activity tries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of learning activity tries
	 */
	@Override
	public List<LearningActivityTry> findAll(
		int start, int end,
		OrderByComparator<LearningActivityTry> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the learning activity tries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityTryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of learning activity tries
	 * @param end the upper bound of the range of learning activity tries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of learning activity tries
	 */
	@Override
	public List<LearningActivityTry> findAll(
		int start, int end,
		OrderByComparator<LearningActivityTry> orderByComparator,
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

		List<LearningActivityTry> list = null;

		if (useFinderCache) {
			list = (List<LearningActivityTry>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_LEARNINGACTIVITYTRY);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_LEARNINGACTIVITYTRY;

				if (pagination) {
					sql = sql.concat(
						LearningActivityTryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<LearningActivityTry>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LearningActivityTry>)QueryUtil.list(
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
	 * Removes all the learning activity tries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (LearningActivityTry learningActivityTry : findAll()) {
			remove(learningActivityTry);
		}
	}

	/**
	 * Returns the number of learning activity tries.
	 *
	 * @return the number of learning activity tries
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_LEARNINGACTIVITYTRY);

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
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "latId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_LEARNINGACTIVITYTRY;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return LearningActivityTryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the learning activity try persistence.
	 */
	@Activate
	public void activate() {
		LearningActivityTryModelImpl.setEntityCacheEnabled(entityCacheEnabled);
		LearningActivityTryModelImpl.setFinderCacheEnabled(finderCacheEnabled);

		_finderPathWithPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			LearningActivityTryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			LearningActivityTryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			LearningActivityTryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			LearningActivityTryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()},
			LearningActivityTryModelImpl.UUID_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()});

		_finderPathFetchByUUID_G = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			LearningActivityTryImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()},
			LearningActivityTryModelImpl.UUID_COLUMN_BITMASK |
			LearningActivityTryModelImpl.GROUPID_COLUMN_BITMASK);

		_finderPathCountByUUID_G = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByUuid_C = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			LearningActivityTryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			LearningActivityTryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			LearningActivityTryModelImpl.UUID_COLUMN_BITMASK |
			LearningActivityTryModelImpl.COMPANYID_COLUMN_BITMASK);

		_finderPathCountByUuid_C = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByActId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			LearningActivityTryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByActId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByActId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			LearningActivityTryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByActId",
			new String[] {Long.class.getName()},
			LearningActivityTryModelImpl.ACTID_COLUMN_BITMASK);

		_finderPathCountByActId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByActId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByActIdUserId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			LearningActivityTryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByActIdUserId",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByActIdUserId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			LearningActivityTryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByActIdUserId",
			new String[] {Long.class.getName(), Long.class.getName()},
			LearningActivityTryModelImpl.ACTID_COLUMN_BITMASK |
			LearningActivityTryModelImpl.USERID_COLUMN_BITMASK);

		_finderPathCountByActIdUserId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByActIdUserId",
			new String[] {Long.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByActIdUserIdEndDate = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			LearningActivityTryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByActIdUserIdEndDate",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Date.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByActIdUserIdEndDate = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			LearningActivityTryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByActIdUserIdEndDate",
			new String[] {
				Long.class.getName(), Long.class.getName(), Date.class.getName()
			},
			LearningActivityTryModelImpl.ACTID_COLUMN_BITMASK |
			LearningActivityTryModelImpl.USERID_COLUMN_BITMASK |
			LearningActivityTryModelImpl.ENDDATE_COLUMN_BITMASK);

		_finderPathCountByActIdUserIdEndDate = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByActIdUserIdEndDate",
			new String[] {
				Long.class.getName(), Long.class.getName(), Date.class.getName()
			});
	}

	@Deactivate
	public void deactivate() {
		entityCache.removeCache(LearningActivityTryImpl.class.getName());
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
				"value.object.column.bitmask.enabled.com.ted.lms.model.LearningActivityTry"),
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

	private Long _getTime(Date date) {
		if (date == null) {
			return null;
		}

		return date.getTime();
	}

	private static final String _SQL_SELECT_LEARNINGACTIVITYTRY =
		"SELECT learningActivityTry FROM LearningActivityTry learningActivityTry";

	private static final String _SQL_SELECT_LEARNINGACTIVITYTRY_WHERE =
		"SELECT learningActivityTry FROM LearningActivityTry learningActivityTry WHERE ";

	private static final String _SQL_COUNT_LEARNINGACTIVITYTRY =
		"SELECT COUNT(learningActivityTry) FROM LearningActivityTry learningActivityTry";

	private static final String _SQL_COUNT_LEARNINGACTIVITYTRY_WHERE =
		"SELECT COUNT(learningActivityTry) FROM LearningActivityTry learningActivityTry WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "learningActivityTry.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No LearningActivityTry exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No LearningActivityTry exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		LearningActivityTryPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});

	static {
		try {
			Class.forName(LMSPersistenceConstants.class.getName());
		}
		catch (ClassNotFoundException cnfe) {
			throw new ExceptionInInitializerError(cnfe);
		}
	}

}