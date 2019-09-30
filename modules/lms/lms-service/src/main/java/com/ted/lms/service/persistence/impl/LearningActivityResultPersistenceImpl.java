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

import com.ted.lms.exception.NoSuchLearningActivityResultException;
import com.ted.lms.model.LearningActivityResult;
import com.ted.lms.model.impl.LearningActivityResultImpl;
import com.ted.lms.model.impl.LearningActivityResultModelImpl;
import com.ted.lms.service.persistence.LearningActivityResultPersistence;
import com.ted.lms.service.persistence.impl.constants.LMSPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

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
 * The persistence implementation for the learning activity result service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = LearningActivityResultPersistence.class)
@ProviderType
public class LearningActivityResultPersistenceImpl
	extends BasePersistenceImpl<LearningActivityResult>
	implements LearningActivityResultPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>LearningActivityResultUtil</code> to access the learning activity result persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		LearningActivityResultImpl.class.getName();

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
	 * Returns all the learning activity results where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching learning activity results
	 */
	@Override
	public List<LearningActivityResult> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the learning activity results where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of learning activity results
	 * @param end the upper bound of the range of learning activity results (not inclusive)
	 * @return the range of matching learning activity results
	 */
	@Override
	public List<LearningActivityResult> findByUuid(
		String uuid, int start, int end) {

		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the learning activity results where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of learning activity results
	 * @param end the upper bound of the range of learning activity results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching learning activity results
	 */
	@Override
	public List<LearningActivityResult> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<LearningActivityResult> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the learning activity results where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of learning activity results
	 * @param end the upper bound of the range of learning activity results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching learning activity results
	 */
	@Override
	public List<LearningActivityResult> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<LearningActivityResult> orderByComparator,
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

		List<LearningActivityResult> list = null;

		if (useFinderCache) {
			list = (List<LearningActivityResult>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LearningActivityResult learningActivityResult : list) {
					if (!uuid.equals(learningActivityResult.getUuid())) {
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

			query.append(_SQL_SELECT_LEARNINGACTIVITYRESULT_WHERE);

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
				query.append(LearningActivityResultModelImpl.ORDER_BY_JPQL);
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
					list = (List<LearningActivityResult>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LearningActivityResult>)QueryUtil.list(
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
	 * Returns the first learning activity result in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity result
	 * @throws NoSuchLearningActivityResultException if a matching learning activity result could not be found
	 */
	@Override
	public LearningActivityResult findByUuid_First(
			String uuid,
			OrderByComparator<LearningActivityResult> orderByComparator)
		throws NoSuchLearningActivityResultException {

		LearningActivityResult learningActivityResult = fetchByUuid_First(
			uuid, orderByComparator);

		if (learningActivityResult != null) {
			return learningActivityResult;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchLearningActivityResultException(msg.toString());
	}

	/**
	 * Returns the first learning activity result in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity result, or <code>null</code> if a matching learning activity result could not be found
	 */
	@Override
	public LearningActivityResult fetchByUuid_First(
		String uuid,
		OrderByComparator<LearningActivityResult> orderByComparator) {

		List<LearningActivityResult> list = findByUuid(
			uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last learning activity result in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity result
	 * @throws NoSuchLearningActivityResultException if a matching learning activity result could not be found
	 */
	@Override
	public LearningActivityResult findByUuid_Last(
			String uuid,
			OrderByComparator<LearningActivityResult> orderByComparator)
		throws NoSuchLearningActivityResultException {

		LearningActivityResult learningActivityResult = fetchByUuid_Last(
			uuid, orderByComparator);

		if (learningActivityResult != null) {
			return learningActivityResult;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchLearningActivityResultException(msg.toString());
	}

	/**
	 * Returns the last learning activity result in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity result, or <code>null</code> if a matching learning activity result could not be found
	 */
	@Override
	public LearningActivityResult fetchByUuid_Last(
		String uuid,
		OrderByComparator<LearningActivityResult> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<LearningActivityResult> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the learning activity results before and after the current learning activity result in the ordered set where uuid = &#63;.
	 *
	 * @param larId the primary key of the current learning activity result
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next learning activity result
	 * @throws NoSuchLearningActivityResultException if a learning activity result with the primary key could not be found
	 */
	@Override
	public LearningActivityResult[] findByUuid_PrevAndNext(
			long larId, String uuid,
			OrderByComparator<LearningActivityResult> orderByComparator)
		throws NoSuchLearningActivityResultException {

		uuid = Objects.toString(uuid, "");

		LearningActivityResult learningActivityResult = findByPrimaryKey(larId);

		Session session = null;

		try {
			session = openSession();

			LearningActivityResult[] array = new LearningActivityResultImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, learningActivityResult, uuid, orderByComparator, true);

			array[1] = learningActivityResult;

			array[2] = getByUuid_PrevAndNext(
				session, learningActivityResult, uuid, orderByComparator,
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

	protected LearningActivityResult getByUuid_PrevAndNext(
		Session session, LearningActivityResult learningActivityResult,
		String uuid,
		OrderByComparator<LearningActivityResult> orderByComparator,
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

		query.append(_SQL_SELECT_LEARNINGACTIVITYRESULT_WHERE);

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
			query.append(LearningActivityResultModelImpl.ORDER_BY_JPQL);
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
						learningActivityResult)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<LearningActivityResult> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the learning activity results where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (LearningActivityResult learningActivityResult :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(learningActivityResult);
		}
	}

	/**
	 * Returns the number of learning activity results where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching learning activity results
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LEARNINGACTIVITYRESULT_WHERE);

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
		"learningActivityResult.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(learningActivityResult.uuid IS NULL OR learningActivityResult.uuid = '')";

	private FinderPath _finderPathFetchByUUID_G;
	private FinderPath _finderPathCountByUUID_G;

	/**
	 * Returns the learning activity result where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchLearningActivityResultException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching learning activity result
	 * @throws NoSuchLearningActivityResultException if a matching learning activity result could not be found
	 */
	@Override
	public LearningActivityResult findByUUID_G(String uuid, long groupId)
		throws NoSuchLearningActivityResultException {

		LearningActivityResult learningActivityResult = fetchByUUID_G(
			uuid, groupId);

		if (learningActivityResult == null) {
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

			throw new NoSuchLearningActivityResultException(msg.toString());
		}

		return learningActivityResult;
	}

	/**
	 * Returns the learning activity result where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching learning activity result, or <code>null</code> if a matching learning activity result could not be found
	 */
	@Override
	public LearningActivityResult fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the learning activity result where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching learning activity result, or <code>null</code> if a matching learning activity result could not be found
	 */
	@Override
	public LearningActivityResult fetchByUUID_G(
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

		if (result instanceof LearningActivityResult) {
			LearningActivityResult learningActivityResult =
				(LearningActivityResult)result;

			if (!Objects.equals(uuid, learningActivityResult.getUuid()) ||
				(groupId != learningActivityResult.getGroupId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_LEARNINGACTIVITYRESULT_WHERE);

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

				List<LearningActivityResult> list = q.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByUUID_G, finderArgs, list);
					}
				}
				else {
					LearningActivityResult learningActivityResult = list.get(0);

					result = learningActivityResult;

					cacheResult(learningActivityResult);
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
			return (LearningActivityResult)result;
		}
	}

	/**
	 * Removes the learning activity result where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the learning activity result that was removed
	 */
	@Override
	public LearningActivityResult removeByUUID_G(String uuid, long groupId)
		throws NoSuchLearningActivityResultException {

		LearningActivityResult learningActivityResult = findByUUID_G(
			uuid, groupId);

		return remove(learningActivityResult);
	}

	/**
	 * Returns the number of learning activity results where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching learning activity results
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUUID_G;

		Object[] finderArgs = new Object[] {uuid, groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LEARNINGACTIVITYRESULT_WHERE);

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
		"learningActivityResult.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_G_UUID_3 =
		"(learningActivityResult.uuid IS NULL OR learningActivityResult.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 =
		"learningActivityResult.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the learning activity results where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching learning activity results
	 */
	@Override
	public List<LearningActivityResult> findByUuid_C(
		String uuid, long companyId) {

		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the learning activity results where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of learning activity results
	 * @param end the upper bound of the range of learning activity results (not inclusive)
	 * @return the range of matching learning activity results
	 */
	@Override
	public List<LearningActivityResult> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the learning activity results where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of learning activity results
	 * @param end the upper bound of the range of learning activity results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching learning activity results
	 */
	@Override
	public List<LearningActivityResult> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<LearningActivityResult> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the learning activity results where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of learning activity results
	 * @param end the upper bound of the range of learning activity results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching learning activity results
	 */
	@Override
	public List<LearningActivityResult> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<LearningActivityResult> orderByComparator,
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

		List<LearningActivityResult> list = null;

		if (useFinderCache) {
			list = (List<LearningActivityResult>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LearningActivityResult learningActivityResult : list) {
					if (!uuid.equals(learningActivityResult.getUuid()) ||
						(companyId != learningActivityResult.getCompanyId())) {

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

			query.append(_SQL_SELECT_LEARNINGACTIVITYRESULT_WHERE);

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
				query.append(LearningActivityResultModelImpl.ORDER_BY_JPQL);
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
					list = (List<LearningActivityResult>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LearningActivityResult>)QueryUtil.list(
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
	 * Returns the first learning activity result in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity result
	 * @throws NoSuchLearningActivityResultException if a matching learning activity result could not be found
	 */
	@Override
	public LearningActivityResult findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<LearningActivityResult> orderByComparator)
		throws NoSuchLearningActivityResultException {

		LearningActivityResult learningActivityResult = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (learningActivityResult != null) {
			return learningActivityResult;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchLearningActivityResultException(msg.toString());
	}

	/**
	 * Returns the first learning activity result in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity result, or <code>null</code> if a matching learning activity result could not be found
	 */
	@Override
	public LearningActivityResult fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<LearningActivityResult> orderByComparator) {

		List<LearningActivityResult> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last learning activity result in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity result
	 * @throws NoSuchLearningActivityResultException if a matching learning activity result could not be found
	 */
	@Override
	public LearningActivityResult findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<LearningActivityResult> orderByComparator)
		throws NoSuchLearningActivityResultException {

		LearningActivityResult learningActivityResult = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (learningActivityResult != null) {
			return learningActivityResult;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchLearningActivityResultException(msg.toString());
	}

	/**
	 * Returns the last learning activity result in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity result, or <code>null</code> if a matching learning activity result could not be found
	 */
	@Override
	public LearningActivityResult fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<LearningActivityResult> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<LearningActivityResult> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the learning activity results before and after the current learning activity result in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param larId the primary key of the current learning activity result
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next learning activity result
	 * @throws NoSuchLearningActivityResultException if a learning activity result with the primary key could not be found
	 */
	@Override
	public LearningActivityResult[] findByUuid_C_PrevAndNext(
			long larId, String uuid, long companyId,
			OrderByComparator<LearningActivityResult> orderByComparator)
		throws NoSuchLearningActivityResultException {

		uuid = Objects.toString(uuid, "");

		LearningActivityResult learningActivityResult = findByPrimaryKey(larId);

		Session session = null;

		try {
			session = openSession();

			LearningActivityResult[] array = new LearningActivityResultImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, learningActivityResult, uuid, companyId,
				orderByComparator, true);

			array[1] = learningActivityResult;

			array[2] = getByUuid_C_PrevAndNext(
				session, learningActivityResult, uuid, companyId,
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

	protected LearningActivityResult getByUuid_C_PrevAndNext(
		Session session, LearningActivityResult learningActivityResult,
		String uuid, long companyId,
		OrderByComparator<LearningActivityResult> orderByComparator,
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

		query.append(_SQL_SELECT_LEARNINGACTIVITYRESULT_WHERE);

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
			query.append(LearningActivityResultModelImpl.ORDER_BY_JPQL);
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
						learningActivityResult)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<LearningActivityResult> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the learning activity results where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (LearningActivityResult learningActivityResult :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(learningActivityResult);
		}
	}

	/**
	 * Returns the number of learning activity results where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching learning activity results
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LEARNINGACTIVITYRESULT_WHERE);

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
		"learningActivityResult.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(learningActivityResult.uuid IS NULL OR learningActivityResult.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"learningActivityResult.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByActId;
	private FinderPath _finderPathWithoutPaginationFindByActId;
	private FinderPath _finderPathCountByActId;

	/**
	 * Returns all the learning activity results where actId = &#63;.
	 *
	 * @param actId the act ID
	 * @return the matching learning activity results
	 */
	@Override
	public List<LearningActivityResult> findByActId(long actId) {
		return findByActId(actId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the learning activity results where actId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param actId the act ID
	 * @param start the lower bound of the range of learning activity results
	 * @param end the upper bound of the range of learning activity results (not inclusive)
	 * @return the range of matching learning activity results
	 */
	@Override
	public List<LearningActivityResult> findByActId(
		long actId, int start, int end) {

		return findByActId(actId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the learning activity results where actId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param actId the act ID
	 * @param start the lower bound of the range of learning activity results
	 * @param end the upper bound of the range of learning activity results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching learning activity results
	 */
	@Override
	public List<LearningActivityResult> findByActId(
		long actId, int start, int end,
		OrderByComparator<LearningActivityResult> orderByComparator) {

		return findByActId(actId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the learning activity results where actId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param actId the act ID
	 * @param start the lower bound of the range of learning activity results
	 * @param end the upper bound of the range of learning activity results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching learning activity results
	 */
	@Override
	public List<LearningActivityResult> findByActId(
		long actId, int start, int end,
		OrderByComparator<LearningActivityResult> orderByComparator,
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

		List<LearningActivityResult> list = null;

		if (useFinderCache) {
			list = (List<LearningActivityResult>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LearningActivityResult learningActivityResult : list) {
					if ((actId != learningActivityResult.getActId())) {
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

			query.append(_SQL_SELECT_LEARNINGACTIVITYRESULT_WHERE);

			query.append(_FINDER_COLUMN_ACTID_ACTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(LearningActivityResultModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(actId);

				if (!pagination) {
					list = (List<LearningActivityResult>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LearningActivityResult>)QueryUtil.list(
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
	 * Returns the first learning activity result in the ordered set where actId = &#63;.
	 *
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity result
	 * @throws NoSuchLearningActivityResultException if a matching learning activity result could not be found
	 */
	@Override
	public LearningActivityResult findByActId_First(
			long actId,
			OrderByComparator<LearningActivityResult> orderByComparator)
		throws NoSuchLearningActivityResultException {

		LearningActivityResult learningActivityResult = fetchByActId_First(
			actId, orderByComparator);

		if (learningActivityResult != null) {
			return learningActivityResult;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("actId=");
		msg.append(actId);

		msg.append("}");

		throw new NoSuchLearningActivityResultException(msg.toString());
	}

	/**
	 * Returns the first learning activity result in the ordered set where actId = &#63;.
	 *
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity result, or <code>null</code> if a matching learning activity result could not be found
	 */
	@Override
	public LearningActivityResult fetchByActId_First(
		long actId,
		OrderByComparator<LearningActivityResult> orderByComparator) {

		List<LearningActivityResult> list = findByActId(
			actId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last learning activity result in the ordered set where actId = &#63;.
	 *
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity result
	 * @throws NoSuchLearningActivityResultException if a matching learning activity result could not be found
	 */
	@Override
	public LearningActivityResult findByActId_Last(
			long actId,
			OrderByComparator<LearningActivityResult> orderByComparator)
		throws NoSuchLearningActivityResultException {

		LearningActivityResult learningActivityResult = fetchByActId_Last(
			actId, orderByComparator);

		if (learningActivityResult != null) {
			return learningActivityResult;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("actId=");
		msg.append(actId);

		msg.append("}");

		throw new NoSuchLearningActivityResultException(msg.toString());
	}

	/**
	 * Returns the last learning activity result in the ordered set where actId = &#63;.
	 *
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity result, or <code>null</code> if a matching learning activity result could not be found
	 */
	@Override
	public LearningActivityResult fetchByActId_Last(
		long actId,
		OrderByComparator<LearningActivityResult> orderByComparator) {

		int count = countByActId(actId);

		if (count == 0) {
			return null;
		}

		List<LearningActivityResult> list = findByActId(
			actId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the learning activity results before and after the current learning activity result in the ordered set where actId = &#63;.
	 *
	 * @param larId the primary key of the current learning activity result
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next learning activity result
	 * @throws NoSuchLearningActivityResultException if a learning activity result with the primary key could not be found
	 */
	@Override
	public LearningActivityResult[] findByActId_PrevAndNext(
			long larId, long actId,
			OrderByComparator<LearningActivityResult> orderByComparator)
		throws NoSuchLearningActivityResultException {

		LearningActivityResult learningActivityResult = findByPrimaryKey(larId);

		Session session = null;

		try {
			session = openSession();

			LearningActivityResult[] array = new LearningActivityResultImpl[3];

			array[0] = getByActId_PrevAndNext(
				session, learningActivityResult, actId, orderByComparator,
				true);

			array[1] = learningActivityResult;

			array[2] = getByActId_PrevAndNext(
				session, learningActivityResult, actId, orderByComparator,
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

	protected LearningActivityResult getByActId_PrevAndNext(
		Session session, LearningActivityResult learningActivityResult,
		long actId, OrderByComparator<LearningActivityResult> orderByComparator,
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

		query.append(_SQL_SELECT_LEARNINGACTIVITYRESULT_WHERE);

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
			query.append(LearningActivityResultModelImpl.ORDER_BY_JPQL);
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
						learningActivityResult)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<LearningActivityResult> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the learning activity results where actId = &#63; from the database.
	 *
	 * @param actId the act ID
	 */
	@Override
	public void removeByActId(long actId) {
		for (LearningActivityResult learningActivityResult :
				findByActId(
					actId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(learningActivityResult);
		}
	}

	/**
	 * Returns the number of learning activity results where actId = &#63;.
	 *
	 * @param actId the act ID
	 * @return the number of matching learning activity results
	 */
	@Override
	public int countByActId(long actId) {
		FinderPath finderPath = _finderPathCountByActId;

		Object[] finderArgs = new Object[] {actId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LEARNINGACTIVITYRESULT_WHERE);

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
		"learningActivityResult.actId = ?";

	private FinderPath _finderPathFetchByActIdUserId;
	private FinderPath _finderPathCountByActIdUserId;

	/**
	 * Returns the learning activity result where actId = &#63; and userId = &#63; or throws a <code>NoSuchLearningActivityResultException</code> if it could not be found.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @return the matching learning activity result
	 * @throws NoSuchLearningActivityResultException if a matching learning activity result could not be found
	 */
	@Override
	public LearningActivityResult findByActIdUserId(long actId, long userId)
		throws NoSuchLearningActivityResultException {

		LearningActivityResult learningActivityResult = fetchByActIdUserId(
			actId, userId);

		if (learningActivityResult == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("actId=");
			msg.append(actId);

			msg.append(", userId=");
			msg.append(userId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchLearningActivityResultException(msg.toString());
		}

		return learningActivityResult;
	}

	/**
	 * Returns the learning activity result where actId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @return the matching learning activity result, or <code>null</code> if a matching learning activity result could not be found
	 */
	@Override
	public LearningActivityResult fetchByActIdUserId(long actId, long userId) {
		return fetchByActIdUserId(actId, userId, true);
	}

	/**
	 * Returns the learning activity result where actId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching learning activity result, or <code>null</code> if a matching learning activity result could not be found
	 */
	@Override
	public LearningActivityResult fetchByActIdUserId(
		long actId, long userId, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {actId, userId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByActIdUserId, finderArgs, this);
		}

		if (result instanceof LearningActivityResult) {
			LearningActivityResult learningActivityResult =
				(LearningActivityResult)result;

			if ((actId != learningActivityResult.getActId()) ||
				(userId != learningActivityResult.getUserId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_LEARNINGACTIVITYRESULT_WHERE);

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

				List<LearningActivityResult> list = q.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByActIdUserId, finderArgs, list);
					}
				}
				else {
					LearningActivityResult learningActivityResult = list.get(0);

					result = learningActivityResult;

					cacheResult(learningActivityResult);
				}
			}
			catch (Exception e) {
				if (useFinderCache) {
					finderCache.removeResult(
						_finderPathFetchByActIdUserId, finderArgs);
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
			return (LearningActivityResult)result;
		}
	}

	/**
	 * Removes the learning activity result where actId = &#63; and userId = &#63; from the database.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @return the learning activity result that was removed
	 */
	@Override
	public LearningActivityResult removeByActIdUserId(long actId, long userId)
		throws NoSuchLearningActivityResultException {

		LearningActivityResult learningActivityResult = findByActIdUserId(
			actId, userId);

		return remove(learningActivityResult);
	}

	/**
	 * Returns the number of learning activity results where actId = &#63; and userId = &#63;.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @return the number of matching learning activity results
	 */
	@Override
	public int countByActIdUserId(long actId, long userId) {
		FinderPath finderPath = _finderPathCountByActIdUserId;

		Object[] finderArgs = new Object[] {actId, userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LEARNINGACTIVITYRESULT_WHERE);

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
		"learningActivityResult.actId = ? AND ";

	private static final String _FINDER_COLUMN_ACTIDUSERID_USERID_2 =
		"learningActivityResult.userId = ?";

	private FinderPath _finderPathWithPaginationFindByActIdPassedEndDateNotNull;
	private FinderPath
		_finderPathWithoutPaginationFindByActIdPassedEndDateNotNull;
	private FinderPath _finderPathCountByActIdPassedEndDateNotNull;

	/**
	 * Returns all the learning activity results where actId = &#63; and passed = &#63;.
	 *
	 * @param actId the act ID
	 * @param passed the passed
	 * @return the matching learning activity results
	 */
	@Override
	public List<LearningActivityResult> findByActIdPassedEndDateNotNull(
		long actId, boolean passed) {

		return findByActIdPassedEndDateNotNull(
			actId, passed, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the learning activity results where actId = &#63; and passed = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param actId the act ID
	 * @param passed the passed
	 * @param start the lower bound of the range of learning activity results
	 * @param end the upper bound of the range of learning activity results (not inclusive)
	 * @return the range of matching learning activity results
	 */
	@Override
	public List<LearningActivityResult> findByActIdPassedEndDateNotNull(
		long actId, boolean passed, int start, int end) {

		return findByActIdPassedEndDateNotNull(actId, passed, start, end, null);
	}

	/**
	 * Returns an ordered range of all the learning activity results where actId = &#63; and passed = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param actId the act ID
	 * @param passed the passed
	 * @param start the lower bound of the range of learning activity results
	 * @param end the upper bound of the range of learning activity results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching learning activity results
	 */
	@Override
	public List<LearningActivityResult> findByActIdPassedEndDateNotNull(
		long actId, boolean passed, int start, int end,
		OrderByComparator<LearningActivityResult> orderByComparator) {

		return findByActIdPassedEndDateNotNull(
			actId, passed, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the learning activity results where actId = &#63; and passed = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param actId the act ID
	 * @param passed the passed
	 * @param start the lower bound of the range of learning activity results
	 * @param end the upper bound of the range of learning activity results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching learning activity results
	 */
	@Override
	public List<LearningActivityResult> findByActIdPassedEndDateNotNull(
		long actId, boolean passed, int start, int end,
		OrderByComparator<LearningActivityResult> orderByComparator,
		boolean useFinderCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindByActIdPassedEndDateNotNull;
				finderArgs = new Object[] {actId, passed};
			}
		}
		else if (useFinderCache) {
			finderPath =
				_finderPathWithPaginationFindByActIdPassedEndDateNotNull;
			finderArgs = new Object[] {
				actId, passed, start, end, orderByComparator
			};
		}

		List<LearningActivityResult> list = null;

		if (useFinderCache) {
			list = (List<LearningActivityResult>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LearningActivityResult learningActivityResult : list) {
					if ((actId != learningActivityResult.getActId()) ||
						(passed != learningActivityResult.isPassed())) {

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

			query.append(_SQL_SELECT_LEARNINGACTIVITYRESULT_WHERE);

			query.append(_FINDER_COLUMN_ACTIDPASSEDENDDATENOTNULL_ACTID_2);

			query.append(_FINDER_COLUMN_ACTIDPASSEDENDDATENOTNULL_PASSED_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(LearningActivityResultModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(actId);

				qPos.add(passed);

				if (!pagination) {
					list = (List<LearningActivityResult>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LearningActivityResult>)QueryUtil.list(
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
	 * Returns the first learning activity result in the ordered set where actId = &#63; and passed = &#63;.
	 *
	 * @param actId the act ID
	 * @param passed the passed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity result
	 * @throws NoSuchLearningActivityResultException if a matching learning activity result could not be found
	 */
	@Override
	public LearningActivityResult findByActIdPassedEndDateNotNull_First(
			long actId, boolean passed,
			OrderByComparator<LearningActivityResult> orderByComparator)
		throws NoSuchLearningActivityResultException {

		LearningActivityResult learningActivityResult =
			fetchByActIdPassedEndDateNotNull_First(
				actId, passed, orderByComparator);

		if (learningActivityResult != null) {
			return learningActivityResult;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("actId=");
		msg.append(actId);

		msg.append(", passed=");
		msg.append(passed);

		msg.append("}");

		throw new NoSuchLearningActivityResultException(msg.toString());
	}

	/**
	 * Returns the first learning activity result in the ordered set where actId = &#63; and passed = &#63;.
	 *
	 * @param actId the act ID
	 * @param passed the passed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity result, or <code>null</code> if a matching learning activity result could not be found
	 */
	@Override
	public LearningActivityResult fetchByActIdPassedEndDateNotNull_First(
		long actId, boolean passed,
		OrderByComparator<LearningActivityResult> orderByComparator) {

		List<LearningActivityResult> list = findByActIdPassedEndDateNotNull(
			actId, passed, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last learning activity result in the ordered set where actId = &#63; and passed = &#63;.
	 *
	 * @param actId the act ID
	 * @param passed the passed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity result
	 * @throws NoSuchLearningActivityResultException if a matching learning activity result could not be found
	 */
	@Override
	public LearningActivityResult findByActIdPassedEndDateNotNull_Last(
			long actId, boolean passed,
			OrderByComparator<LearningActivityResult> orderByComparator)
		throws NoSuchLearningActivityResultException {

		LearningActivityResult learningActivityResult =
			fetchByActIdPassedEndDateNotNull_Last(
				actId, passed, orderByComparator);

		if (learningActivityResult != null) {
			return learningActivityResult;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("actId=");
		msg.append(actId);

		msg.append(", passed=");
		msg.append(passed);

		msg.append("}");

		throw new NoSuchLearningActivityResultException(msg.toString());
	}

	/**
	 * Returns the last learning activity result in the ordered set where actId = &#63; and passed = &#63;.
	 *
	 * @param actId the act ID
	 * @param passed the passed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity result, or <code>null</code> if a matching learning activity result could not be found
	 */
	@Override
	public LearningActivityResult fetchByActIdPassedEndDateNotNull_Last(
		long actId, boolean passed,
		OrderByComparator<LearningActivityResult> orderByComparator) {

		int count = countByActIdPassedEndDateNotNull(actId, passed);

		if (count == 0) {
			return null;
		}

		List<LearningActivityResult> list = findByActIdPassedEndDateNotNull(
			actId, passed, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the learning activity results before and after the current learning activity result in the ordered set where actId = &#63; and passed = &#63;.
	 *
	 * @param larId the primary key of the current learning activity result
	 * @param actId the act ID
	 * @param passed the passed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next learning activity result
	 * @throws NoSuchLearningActivityResultException if a learning activity result with the primary key could not be found
	 */
	@Override
	public LearningActivityResult[] findByActIdPassedEndDateNotNull_PrevAndNext(
			long larId, long actId, boolean passed,
			OrderByComparator<LearningActivityResult> orderByComparator)
		throws NoSuchLearningActivityResultException {

		LearningActivityResult learningActivityResult = findByPrimaryKey(larId);

		Session session = null;

		try {
			session = openSession();

			LearningActivityResult[] array = new LearningActivityResultImpl[3];

			array[0] = getByActIdPassedEndDateNotNull_PrevAndNext(
				session, learningActivityResult, actId, passed,
				orderByComparator, true);

			array[1] = learningActivityResult;

			array[2] = getByActIdPassedEndDateNotNull_PrevAndNext(
				session, learningActivityResult, actId, passed,
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

	protected LearningActivityResult getByActIdPassedEndDateNotNull_PrevAndNext(
		Session session, LearningActivityResult learningActivityResult,
		long actId, boolean passed,
		OrderByComparator<LearningActivityResult> orderByComparator,
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

		query.append(_SQL_SELECT_LEARNINGACTIVITYRESULT_WHERE);

		query.append(_FINDER_COLUMN_ACTIDPASSEDENDDATENOTNULL_ACTID_2);

		query.append(_FINDER_COLUMN_ACTIDPASSEDENDDATENOTNULL_PASSED_2);

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
			query.append(LearningActivityResultModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(actId);

		qPos.add(passed);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						learningActivityResult)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<LearningActivityResult> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the learning activity results where actId = &#63; and passed = &#63; from the database.
	 *
	 * @param actId the act ID
	 * @param passed the passed
	 */
	@Override
	public void removeByActIdPassedEndDateNotNull(long actId, boolean passed) {
		for (LearningActivityResult learningActivityResult :
				findByActIdPassedEndDateNotNull(
					actId, passed, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(learningActivityResult);
		}
	}

	/**
	 * Returns the number of learning activity results where actId = &#63; and passed = &#63;.
	 *
	 * @param actId the act ID
	 * @param passed the passed
	 * @return the number of matching learning activity results
	 */
	@Override
	public int countByActIdPassedEndDateNotNull(long actId, boolean passed) {
		FinderPath finderPath = _finderPathCountByActIdPassedEndDateNotNull;

		Object[] finderArgs = new Object[] {actId, passed};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LEARNINGACTIVITYRESULT_WHERE);

			query.append(_FINDER_COLUMN_ACTIDPASSEDENDDATENOTNULL_ACTID_2);

			query.append(_FINDER_COLUMN_ACTIDPASSEDENDDATENOTNULL_PASSED_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(actId);

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

	private static final String
		_FINDER_COLUMN_ACTIDPASSEDENDDATENOTNULL_ACTID_2 =
			"learningActivityResult.actId = ? AND ";

	private static final String
		_FINDER_COLUMN_ACTIDPASSEDENDDATENOTNULL_PASSED_2 =
			"learningActivityResult.passed = ? AND learningActivityResult.endDate IS NOT NULL";

	public LearningActivityResultPersistenceImpl() {
		setModelClass(LearningActivityResult.class);

		setModelImplClass(LearningActivityResultImpl.class);
		setModelPKClass(long.class);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");

		setDBColumnNames(dbColumnNames);
	}

	/**
	 * Caches the learning activity result in the entity cache if it is enabled.
	 *
	 * @param learningActivityResult the learning activity result
	 */
	@Override
	public void cacheResult(LearningActivityResult learningActivityResult) {
		entityCache.putResult(
			entityCacheEnabled, LearningActivityResultImpl.class,
			learningActivityResult.getPrimaryKey(), learningActivityResult);

		finderCache.putResult(
			_finderPathFetchByUUID_G,
			new Object[] {
				learningActivityResult.getUuid(),
				learningActivityResult.getGroupId()
			},
			learningActivityResult);

		finderCache.putResult(
			_finderPathFetchByActIdUserId,
			new Object[] {
				learningActivityResult.getActId(),
				learningActivityResult.getUserId()
			},
			learningActivityResult);

		learningActivityResult.resetOriginalValues();
	}

	/**
	 * Caches the learning activity results in the entity cache if it is enabled.
	 *
	 * @param learningActivityResults the learning activity results
	 */
	@Override
	public void cacheResult(
		List<LearningActivityResult> learningActivityResults) {

		for (LearningActivityResult learningActivityResult :
				learningActivityResults) {

			if (entityCache.getResult(
					entityCacheEnabled, LearningActivityResultImpl.class,
					learningActivityResult.getPrimaryKey()) == null) {

				cacheResult(learningActivityResult);
			}
			else {
				learningActivityResult.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all learning activity results.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(LearningActivityResultImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the learning activity result.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(LearningActivityResult learningActivityResult) {
		entityCache.removeResult(
			entityCacheEnabled, LearningActivityResultImpl.class,
			learningActivityResult.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(
			(LearningActivityResultModelImpl)learningActivityResult, true);
	}

	@Override
	public void clearCache(
		List<LearningActivityResult> learningActivityResults) {

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (LearningActivityResult learningActivityResult :
				learningActivityResults) {

			entityCache.removeResult(
				entityCacheEnabled, LearningActivityResultImpl.class,
				learningActivityResult.getPrimaryKey());

			clearUniqueFindersCache(
				(LearningActivityResultModelImpl)learningActivityResult, true);
		}
	}

	protected void cacheUniqueFindersCache(
		LearningActivityResultModelImpl learningActivityResultModelImpl) {

		Object[] args = new Object[] {
			learningActivityResultModelImpl.getUuid(),
			learningActivityResultModelImpl.getGroupId()
		};

		finderCache.putResult(
			_finderPathCountByUUID_G, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByUUID_G, args, learningActivityResultModelImpl,
			false);

		args = new Object[] {
			learningActivityResultModelImpl.getActId(),
			learningActivityResultModelImpl.getUserId()
		};

		finderCache.putResult(
			_finderPathCountByActIdUserId, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByActIdUserId, args,
			learningActivityResultModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		LearningActivityResultModelImpl learningActivityResultModelImpl,
		boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				learningActivityResultModelImpl.getUuid(),
				learningActivityResultModelImpl.getGroupId()
			};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
		}

		if ((learningActivityResultModelImpl.getColumnBitmask() &
			 _finderPathFetchByUUID_G.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				learningActivityResultModelImpl.getOriginalUuid(),
				learningActivityResultModelImpl.getOriginalGroupId()
			};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
				learningActivityResultModelImpl.getActId(),
				learningActivityResultModelImpl.getUserId()
			};

			finderCache.removeResult(_finderPathCountByActIdUserId, args);
			finderCache.removeResult(_finderPathFetchByActIdUserId, args);
		}

		if ((learningActivityResultModelImpl.getColumnBitmask() &
			 _finderPathFetchByActIdUserId.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				learningActivityResultModelImpl.getOriginalActId(),
				learningActivityResultModelImpl.getOriginalUserId()
			};

			finderCache.removeResult(_finderPathCountByActIdUserId, args);
			finderCache.removeResult(_finderPathFetchByActIdUserId, args);
		}
	}

	/**
	 * Creates a new learning activity result with the primary key. Does not add the learning activity result to the database.
	 *
	 * @param larId the primary key for the new learning activity result
	 * @return the new learning activity result
	 */
	@Override
	public LearningActivityResult create(long larId) {
		LearningActivityResult learningActivityResult =
			new LearningActivityResultImpl();

		learningActivityResult.setNew(true);
		learningActivityResult.setPrimaryKey(larId);

		String uuid = PortalUUIDUtil.generate();

		learningActivityResult.setUuid(uuid);

		learningActivityResult.setCompanyId(CompanyThreadLocal.getCompanyId());

		return learningActivityResult;
	}

	/**
	 * Removes the learning activity result with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param larId the primary key of the learning activity result
	 * @return the learning activity result that was removed
	 * @throws NoSuchLearningActivityResultException if a learning activity result with the primary key could not be found
	 */
	@Override
	public LearningActivityResult remove(long larId)
		throws NoSuchLearningActivityResultException {

		return remove((Serializable)larId);
	}

	/**
	 * Removes the learning activity result with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the learning activity result
	 * @return the learning activity result that was removed
	 * @throws NoSuchLearningActivityResultException if a learning activity result with the primary key could not be found
	 */
	@Override
	public LearningActivityResult remove(Serializable primaryKey)
		throws NoSuchLearningActivityResultException {

		Session session = null;

		try {
			session = openSession();

			LearningActivityResult learningActivityResult =
				(LearningActivityResult)session.get(
					LearningActivityResultImpl.class, primaryKey);

			if (learningActivityResult == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLearningActivityResultException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(learningActivityResult);
		}
		catch (NoSuchLearningActivityResultException nsee) {
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
	protected LearningActivityResult removeImpl(
		LearningActivityResult learningActivityResult) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(learningActivityResult)) {
				learningActivityResult = (LearningActivityResult)session.get(
					LearningActivityResultImpl.class,
					learningActivityResult.getPrimaryKeyObj());
			}

			if (learningActivityResult != null) {
				session.delete(learningActivityResult);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (learningActivityResult != null) {
			clearCache(learningActivityResult);
		}

		return learningActivityResult;
	}

	@Override
	public LearningActivityResult updateImpl(
		LearningActivityResult learningActivityResult) {

		boolean isNew = learningActivityResult.isNew();

		if (!(learningActivityResult instanceof
				LearningActivityResultModelImpl)) {

			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(learningActivityResult.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					learningActivityResult);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in learningActivityResult proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom LearningActivityResult implementation " +
					learningActivityResult.getClass());
		}

		LearningActivityResultModelImpl learningActivityResultModelImpl =
			(LearningActivityResultModelImpl)learningActivityResult;

		if (Validator.isNull(learningActivityResult.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			learningActivityResult.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (learningActivityResult.getCreateDate() == null)) {
			if (serviceContext == null) {
				learningActivityResult.setCreateDate(now);
			}
			else {
				learningActivityResult.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!learningActivityResultModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				learningActivityResult.setModifiedDate(now);
			}
			else {
				learningActivityResult.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (learningActivityResult.isNew()) {
				session.save(learningActivityResult);

				learningActivityResult.setNew(false);
			}
			else {
				learningActivityResult = (LearningActivityResult)session.merge(
					learningActivityResult);
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
				learningActivityResultModelImpl.getUuid()
			};

			finderCache.removeResult(_finderPathCountByUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid, args);

			args = new Object[] {
				learningActivityResultModelImpl.getUuid(),
				learningActivityResultModelImpl.getCompanyId()
			};

			finderCache.removeResult(_finderPathCountByUuid_C, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid_C, args);

			args = new Object[] {learningActivityResultModelImpl.getActId()};

			finderCache.removeResult(_finderPathCountByActId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByActId, args);

			args = new Object[] {
				learningActivityResultModelImpl.getActId(),
				learningActivityResultModelImpl.isPassed()
			};

			finderCache.removeResult(
				_finderPathCountByActIdPassedEndDateNotNull, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByActIdPassedEndDateNotNull,
				args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((learningActivityResultModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					learningActivityResultModelImpl.getOriginalUuid()
				};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);

				args = new Object[] {learningActivityResultModelImpl.getUuid()};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);
			}

			if ((learningActivityResultModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid_C.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					learningActivityResultModelImpl.getOriginalUuid(),
					learningActivityResultModelImpl.getOriginalCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);

				args = new Object[] {
					learningActivityResultModelImpl.getUuid(),
					learningActivityResultModelImpl.getCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);
			}

			if ((learningActivityResultModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByActId.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					learningActivityResultModelImpl.getOriginalActId()
				};

				finderCache.removeResult(_finderPathCountByActId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByActId, args);

				args = new Object[] {
					learningActivityResultModelImpl.getActId()
				};

				finderCache.removeResult(_finderPathCountByActId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByActId, args);
			}

			if ((learningActivityResultModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByActIdPassedEndDateNotNull.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					learningActivityResultModelImpl.getOriginalActId(),
					learningActivityResultModelImpl.getOriginalPassed()
				};

				finderCache.removeResult(
					_finderPathCountByActIdPassedEndDateNotNull, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByActIdPassedEndDateNotNull,
					args);

				args = new Object[] {
					learningActivityResultModelImpl.getActId(),
					learningActivityResultModelImpl.isPassed()
				};

				finderCache.removeResult(
					_finderPathCountByActIdPassedEndDateNotNull, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByActIdPassedEndDateNotNull,
					args);
			}
		}

		entityCache.putResult(
			entityCacheEnabled, LearningActivityResultImpl.class,
			learningActivityResult.getPrimaryKey(), learningActivityResult,
			false);

		clearUniqueFindersCache(learningActivityResultModelImpl, false);
		cacheUniqueFindersCache(learningActivityResultModelImpl);

		learningActivityResult.resetOriginalValues();

		return learningActivityResult;
	}

	/**
	 * Returns the learning activity result with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the learning activity result
	 * @return the learning activity result
	 * @throws NoSuchLearningActivityResultException if a learning activity result with the primary key could not be found
	 */
	@Override
	public LearningActivityResult findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLearningActivityResultException {

		LearningActivityResult learningActivityResult = fetchByPrimaryKey(
			primaryKey);

		if (learningActivityResult == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLearningActivityResultException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return learningActivityResult;
	}

	/**
	 * Returns the learning activity result with the primary key or throws a <code>NoSuchLearningActivityResultException</code> if it could not be found.
	 *
	 * @param larId the primary key of the learning activity result
	 * @return the learning activity result
	 * @throws NoSuchLearningActivityResultException if a learning activity result with the primary key could not be found
	 */
	@Override
	public LearningActivityResult findByPrimaryKey(long larId)
		throws NoSuchLearningActivityResultException {

		return findByPrimaryKey((Serializable)larId);
	}

	/**
	 * Returns the learning activity result with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param larId the primary key of the learning activity result
	 * @return the learning activity result, or <code>null</code> if a learning activity result with the primary key could not be found
	 */
	@Override
	public LearningActivityResult fetchByPrimaryKey(long larId) {
		return fetchByPrimaryKey((Serializable)larId);
	}

	/**
	 * Returns all the learning activity results.
	 *
	 * @return the learning activity results
	 */
	@Override
	public List<LearningActivityResult> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the learning activity results.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of learning activity results
	 * @param end the upper bound of the range of learning activity results (not inclusive)
	 * @return the range of learning activity results
	 */
	@Override
	public List<LearningActivityResult> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the learning activity results.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of learning activity results
	 * @param end the upper bound of the range of learning activity results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of learning activity results
	 */
	@Override
	public List<LearningActivityResult> findAll(
		int start, int end,
		OrderByComparator<LearningActivityResult> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the learning activity results.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of learning activity results
	 * @param end the upper bound of the range of learning activity results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of learning activity results
	 */
	@Override
	public List<LearningActivityResult> findAll(
		int start, int end,
		OrderByComparator<LearningActivityResult> orderByComparator,
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

		List<LearningActivityResult> list = null;

		if (useFinderCache) {
			list = (List<LearningActivityResult>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_LEARNINGACTIVITYRESULT);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_LEARNINGACTIVITYRESULT;

				if (pagination) {
					sql = sql.concat(
						LearningActivityResultModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<LearningActivityResult>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LearningActivityResult>)QueryUtil.list(
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
	 * Removes all the learning activity results from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (LearningActivityResult learningActivityResult : findAll()) {
			remove(learningActivityResult);
		}
	}

	/**
	 * Returns the number of learning activity results.
	 *
	 * @return the number of learning activity results
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(
					_SQL_COUNT_LEARNINGACTIVITYRESULT);

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
		return "larId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_LEARNINGACTIVITYRESULT;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return LearningActivityResultModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the learning activity result persistence.
	 */
	@Activate
	public void activate() {
		LearningActivityResultModelImpl.setEntityCacheEnabled(
			entityCacheEnabled);
		LearningActivityResultModelImpl.setFinderCacheEnabled(
			finderCacheEnabled);

		_finderPathWithPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			LearningActivityResultImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			LearningActivityResultImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			LearningActivityResultImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			LearningActivityResultImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()},
			LearningActivityResultModelImpl.UUID_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()});

		_finderPathFetchByUUID_G = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			LearningActivityResultImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()},
			LearningActivityResultModelImpl.UUID_COLUMN_BITMASK |
			LearningActivityResultModelImpl.GROUPID_COLUMN_BITMASK);

		_finderPathCountByUUID_G = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByUuid_C = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			LearningActivityResultImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			LearningActivityResultImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			LearningActivityResultModelImpl.UUID_COLUMN_BITMASK |
			LearningActivityResultModelImpl.COMPANYID_COLUMN_BITMASK);

		_finderPathCountByUuid_C = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByActId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			LearningActivityResultImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByActId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByActId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			LearningActivityResultImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByActId",
			new String[] {Long.class.getName()},
			LearningActivityResultModelImpl.ACTID_COLUMN_BITMASK);

		_finderPathCountByActId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByActId",
			new String[] {Long.class.getName()});

		_finderPathFetchByActIdUserId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			LearningActivityResultImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByActIdUserId",
			new String[] {Long.class.getName(), Long.class.getName()},
			LearningActivityResultModelImpl.ACTID_COLUMN_BITMASK |
			LearningActivityResultModelImpl.USERID_COLUMN_BITMASK);

		_finderPathCountByActIdUserId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByActIdUserId",
			new String[] {Long.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByActIdPassedEndDateNotNull =
			new FinderPath(
				entityCacheEnabled, finderCacheEnabled,
				LearningActivityResultImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
				"findByActIdPassedEndDateNotNull",
				new String[] {
					Long.class.getName(), Boolean.class.getName(),
					Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				});

		_finderPathWithoutPaginationFindByActIdPassedEndDateNotNull =
			new FinderPath(
				entityCacheEnabled, finderCacheEnabled,
				LearningActivityResultImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"findByActIdPassedEndDateNotNull",
				new String[] {Long.class.getName(), Boolean.class.getName()},
				LearningActivityResultModelImpl.ACTID_COLUMN_BITMASK |
				LearningActivityResultModelImpl.PASSED_COLUMN_BITMASK);

		_finderPathCountByActIdPassedEndDateNotNull = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByActIdPassedEndDateNotNull",
			new String[] {Long.class.getName(), Boolean.class.getName()});
	}

	@Deactivate
	public void deactivate() {
		entityCache.removeCache(LearningActivityResultImpl.class.getName());
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
				"value.object.column.bitmask.enabled.com.ted.lms.model.LearningActivityResult"),
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

	private static final String _SQL_SELECT_LEARNINGACTIVITYRESULT =
		"SELECT learningActivityResult FROM LearningActivityResult learningActivityResult";

	private static final String _SQL_SELECT_LEARNINGACTIVITYRESULT_WHERE =
		"SELECT learningActivityResult FROM LearningActivityResult learningActivityResult WHERE ";

	private static final String _SQL_COUNT_LEARNINGACTIVITYRESULT =
		"SELECT COUNT(learningActivityResult) FROM LearningActivityResult learningActivityResult";

	private static final String _SQL_COUNT_LEARNINGACTIVITYRESULT_WHERE =
		"SELECT COUNT(learningActivityResult) FROM LearningActivityResult learningActivityResult WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"learningActivityResult.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No LearningActivityResult exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No LearningActivityResult exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		LearningActivityResultPersistenceImpl.class);

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