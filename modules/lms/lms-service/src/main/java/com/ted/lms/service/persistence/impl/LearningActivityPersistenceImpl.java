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
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import com.ted.lms.exception.NoSuchLearningActivityException;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.impl.LearningActivityImpl;
import com.ted.lms.model.impl.LearningActivityModelImpl;
import com.ted.lms.service.persistence.LearningActivityPersistence;
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
 * The persistence implementation for the learning activity service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = LearningActivityPersistence.class)
@ProviderType
public class LearningActivityPersistenceImpl
	extends BasePersistenceImpl<LearningActivity>
	implements LearningActivityPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>LearningActivityUtil</code> to access the learning activity persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		LearningActivityImpl.class.getName();

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
	 * Returns all the learning activities where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching learning activities
	 */
	@Override
	public List<LearningActivity> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the learning activities where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @return the range of matching learning activities
	 */
	@Override
	public List<LearningActivity> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the learning activities where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching learning activities
	 */
	@Override
	public List<LearningActivity> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<LearningActivity> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the learning activities where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching learning activities
	 */
	@Override
	public List<LearningActivity> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<LearningActivity> orderByComparator,
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

		List<LearningActivity> list = null;

		if (useFinderCache) {
			list = (List<LearningActivity>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LearningActivity learningActivity : list) {
					if (!uuid.equals(learningActivity.getUuid())) {
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

			query.append(_SQL_SELECT_LEARNINGACTIVITY_WHERE);

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
				query.append(LearningActivityModelImpl.ORDER_BY_JPQL);
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
					list = (List<LearningActivity>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LearningActivity>)QueryUtil.list(
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
	 * Returns the first learning activity in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity
	 * @throws NoSuchLearningActivityException if a matching learning activity could not be found
	 */
	@Override
	public LearningActivity findByUuid_First(
			String uuid, OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException {

		LearningActivity learningActivity = fetchByUuid_First(
			uuid, orderByComparator);

		if (learningActivity != null) {
			return learningActivity;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchLearningActivityException(msg.toString());
	}

	/**
	 * Returns the first learning activity in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity, or <code>null</code> if a matching learning activity could not be found
	 */
	@Override
	public LearningActivity fetchByUuid_First(
		String uuid, OrderByComparator<LearningActivity> orderByComparator) {

		List<LearningActivity> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last learning activity in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity
	 * @throws NoSuchLearningActivityException if a matching learning activity could not be found
	 */
	@Override
	public LearningActivity findByUuid_Last(
			String uuid, OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException {

		LearningActivity learningActivity = fetchByUuid_Last(
			uuid, orderByComparator);

		if (learningActivity != null) {
			return learningActivity;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchLearningActivityException(msg.toString());
	}

	/**
	 * Returns the last learning activity in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity, or <code>null</code> if a matching learning activity could not be found
	 */
	@Override
	public LearningActivity fetchByUuid_Last(
		String uuid, OrderByComparator<LearningActivity> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<LearningActivity> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the learning activities before and after the current learning activity in the ordered set where uuid = &#63;.
	 *
	 * @param actId the primary key of the current learning activity
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next learning activity
	 * @throws NoSuchLearningActivityException if a learning activity with the primary key could not be found
	 */
	@Override
	public LearningActivity[] findByUuid_PrevAndNext(
			long actId, String uuid,
			OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException {

		uuid = Objects.toString(uuid, "");

		LearningActivity learningActivity = findByPrimaryKey(actId);

		Session session = null;

		try {
			session = openSession();

			LearningActivity[] array = new LearningActivityImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, learningActivity, uuid, orderByComparator, true);

			array[1] = learningActivity;

			array[2] = getByUuid_PrevAndNext(
				session, learningActivity, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LearningActivity getByUuid_PrevAndNext(
		Session session, LearningActivity learningActivity, String uuid,
		OrderByComparator<LearningActivity> orderByComparator,
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

		query.append(_SQL_SELECT_LEARNINGACTIVITY_WHERE);

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
			query.append(LearningActivityModelImpl.ORDER_BY_JPQL);
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
						learningActivity)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<LearningActivity> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the learning activities where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (LearningActivity learningActivity :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(learningActivity);
		}
	}

	/**
	 * Returns the number of learning activities where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching learning activities
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LEARNINGACTIVITY_WHERE);

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
		"learningActivity.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(learningActivity.uuid IS NULL OR learningActivity.uuid = '')";

	private FinderPath _finderPathFetchByUUID_G;
	private FinderPath _finderPathCountByUUID_G;

	/**
	 * Returns the learning activity where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchLearningActivityException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching learning activity
	 * @throws NoSuchLearningActivityException if a matching learning activity could not be found
	 */
	@Override
	public LearningActivity findByUUID_G(String uuid, long groupId)
		throws NoSuchLearningActivityException {

		LearningActivity learningActivity = fetchByUUID_G(uuid, groupId);

		if (learningActivity == null) {
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

			throw new NoSuchLearningActivityException(msg.toString());
		}

		return learningActivity;
	}

	/**
	 * Returns the learning activity where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching learning activity, or <code>null</code> if a matching learning activity could not be found
	 */
	@Override
	public LearningActivity fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the learning activity where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching learning activity, or <code>null</code> if a matching learning activity could not be found
	 */
	@Override
	public LearningActivity fetchByUUID_G(
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

		if (result instanceof LearningActivity) {
			LearningActivity learningActivity = (LearningActivity)result;

			if (!Objects.equals(uuid, learningActivity.getUuid()) ||
				(groupId != learningActivity.getGroupId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_LEARNINGACTIVITY_WHERE);

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

				List<LearningActivity> list = q.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByUUID_G, finderArgs, list);
					}
				}
				else {
					LearningActivity learningActivity = list.get(0);

					result = learningActivity;

					cacheResult(learningActivity);
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
			return (LearningActivity)result;
		}
	}

	/**
	 * Removes the learning activity where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the learning activity that was removed
	 */
	@Override
	public LearningActivity removeByUUID_G(String uuid, long groupId)
		throws NoSuchLearningActivityException {

		LearningActivity learningActivity = findByUUID_G(uuid, groupId);

		return remove(learningActivity);
	}

	/**
	 * Returns the number of learning activities where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching learning activities
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUUID_G;

		Object[] finderArgs = new Object[] {uuid, groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LEARNINGACTIVITY_WHERE);

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
		"learningActivity.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_G_UUID_3 =
		"(learningActivity.uuid IS NULL OR learningActivity.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 =
		"learningActivity.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the learning activities where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching learning activities
	 */
	@Override
	public List<LearningActivity> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the learning activities where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @return the range of matching learning activities
	 */
	@Override
	public List<LearningActivity> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the learning activities where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching learning activities
	 */
	@Override
	public List<LearningActivity> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<LearningActivity> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the learning activities where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching learning activities
	 */
	@Override
	public List<LearningActivity> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<LearningActivity> orderByComparator,
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

		List<LearningActivity> list = null;

		if (useFinderCache) {
			list = (List<LearningActivity>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LearningActivity learningActivity : list) {
					if (!uuid.equals(learningActivity.getUuid()) ||
						(companyId != learningActivity.getCompanyId())) {

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

			query.append(_SQL_SELECT_LEARNINGACTIVITY_WHERE);

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
				query.append(LearningActivityModelImpl.ORDER_BY_JPQL);
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
					list = (List<LearningActivity>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LearningActivity>)QueryUtil.list(
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
	 * Returns the first learning activity in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity
	 * @throws NoSuchLearningActivityException if a matching learning activity could not be found
	 */
	@Override
	public LearningActivity findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException {

		LearningActivity learningActivity = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (learningActivity != null) {
			return learningActivity;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchLearningActivityException(msg.toString());
	}

	/**
	 * Returns the first learning activity in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity, or <code>null</code> if a matching learning activity could not be found
	 */
	@Override
	public LearningActivity fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<LearningActivity> orderByComparator) {

		List<LearningActivity> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last learning activity in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity
	 * @throws NoSuchLearningActivityException if a matching learning activity could not be found
	 */
	@Override
	public LearningActivity findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException {

		LearningActivity learningActivity = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (learningActivity != null) {
			return learningActivity;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchLearningActivityException(msg.toString());
	}

	/**
	 * Returns the last learning activity in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity, or <code>null</code> if a matching learning activity could not be found
	 */
	@Override
	public LearningActivity fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<LearningActivity> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<LearningActivity> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the learning activities before and after the current learning activity in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param actId the primary key of the current learning activity
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next learning activity
	 * @throws NoSuchLearningActivityException if a learning activity with the primary key could not be found
	 */
	@Override
	public LearningActivity[] findByUuid_C_PrevAndNext(
			long actId, String uuid, long companyId,
			OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException {

		uuid = Objects.toString(uuid, "");

		LearningActivity learningActivity = findByPrimaryKey(actId);

		Session session = null;

		try {
			session = openSession();

			LearningActivity[] array = new LearningActivityImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, learningActivity, uuid, companyId, orderByComparator,
				true);

			array[1] = learningActivity;

			array[2] = getByUuid_C_PrevAndNext(
				session, learningActivity, uuid, companyId, orderByComparator,
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

	protected LearningActivity getByUuid_C_PrevAndNext(
		Session session, LearningActivity learningActivity, String uuid,
		long companyId, OrderByComparator<LearningActivity> orderByComparator,
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

		query.append(_SQL_SELECT_LEARNINGACTIVITY_WHERE);

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
			query.append(LearningActivityModelImpl.ORDER_BY_JPQL);
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
						learningActivity)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<LearningActivity> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the learning activities where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (LearningActivity learningActivity :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(learningActivity);
		}
	}

	/**
	 * Returns the number of learning activities where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching learning activities
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LEARNINGACTIVITY_WHERE);

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
		"learningActivity.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(learningActivity.uuid IS NULL OR learningActivity.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"learningActivity.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByCompanyId;
	private FinderPath _finderPathWithoutPaginationFindByCompanyId;
	private FinderPath _finderPathCountByCompanyId;

	/**
	 * Returns all the learning activities where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching learning activities
	 */
	@Override
	public List<LearningActivity> findByCompanyId(long companyId) {
		return findByCompanyId(
			companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the learning activities where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @return the range of matching learning activities
	 */
	@Override
	public List<LearningActivity> findByCompanyId(
		long companyId, int start, int end) {

		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the learning activities where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching learning activities
	 */
	@Override
	public List<LearningActivity> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<LearningActivity> orderByComparator) {

		return findByCompanyId(companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the learning activities where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching learning activities
	 */
	@Override
	public List<LearningActivity> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<LearningActivity> orderByComparator,
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

		List<LearningActivity> list = null;

		if (useFinderCache) {
			list = (List<LearningActivity>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LearningActivity learningActivity : list) {
					if ((companyId != learningActivity.getCompanyId())) {
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

			query.append(_SQL_SELECT_LEARNINGACTIVITY_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(LearningActivityModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (!pagination) {
					list = (List<LearningActivity>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LearningActivity>)QueryUtil.list(
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
	 * Returns the first learning activity in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity
	 * @throws NoSuchLearningActivityException if a matching learning activity could not be found
	 */
	@Override
	public LearningActivity findByCompanyId_First(
			long companyId,
			OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException {

		LearningActivity learningActivity = fetchByCompanyId_First(
			companyId, orderByComparator);

		if (learningActivity != null) {
			return learningActivity;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchLearningActivityException(msg.toString());
	}

	/**
	 * Returns the first learning activity in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity, or <code>null</code> if a matching learning activity could not be found
	 */
	@Override
	public LearningActivity fetchByCompanyId_First(
		long companyId, OrderByComparator<LearningActivity> orderByComparator) {

		List<LearningActivity> list = findByCompanyId(
			companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last learning activity in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity
	 * @throws NoSuchLearningActivityException if a matching learning activity could not be found
	 */
	@Override
	public LearningActivity findByCompanyId_Last(
			long companyId,
			OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException {

		LearningActivity learningActivity = fetchByCompanyId_Last(
			companyId, orderByComparator);

		if (learningActivity != null) {
			return learningActivity;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchLearningActivityException(msg.toString());
	}

	/**
	 * Returns the last learning activity in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity, or <code>null</code> if a matching learning activity could not be found
	 */
	@Override
	public LearningActivity fetchByCompanyId_Last(
		long companyId, OrderByComparator<LearningActivity> orderByComparator) {

		int count = countByCompanyId(companyId);

		if (count == 0) {
			return null;
		}

		List<LearningActivity> list = findByCompanyId(
			companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the learning activities before and after the current learning activity in the ordered set where companyId = &#63;.
	 *
	 * @param actId the primary key of the current learning activity
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next learning activity
	 * @throws NoSuchLearningActivityException if a learning activity with the primary key could not be found
	 */
	@Override
	public LearningActivity[] findByCompanyId_PrevAndNext(
			long actId, long companyId,
			OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException {

		LearningActivity learningActivity = findByPrimaryKey(actId);

		Session session = null;

		try {
			session = openSession();

			LearningActivity[] array = new LearningActivityImpl[3];

			array[0] = getByCompanyId_PrevAndNext(
				session, learningActivity, companyId, orderByComparator, true);

			array[1] = learningActivity;

			array[2] = getByCompanyId_PrevAndNext(
				session, learningActivity, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LearningActivity getByCompanyId_PrevAndNext(
		Session session, LearningActivity learningActivity, long companyId,
		OrderByComparator<LearningActivity> orderByComparator,
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

		query.append(_SQL_SELECT_LEARNINGACTIVITY_WHERE);

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
			query.append(LearningActivityModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						learningActivity)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<LearningActivity> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the learning activities where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	@Override
	public void removeByCompanyId(long companyId) {
		for (LearningActivity learningActivity :
				findByCompanyId(
					companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(learningActivity);
		}
	}

	/**
	 * Returns the number of learning activities where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching learning activities
	 */
	@Override
	public int countByCompanyId(long companyId) {
		FinderPath finderPath = _finderPathCountByCompanyId;

		Object[] finderArgs = new Object[] {companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LEARNINGACTIVITY_WHERE);

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
		"learningActivity.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByGroupId;
	private FinderPath _finderPathWithoutPaginationFindByGroupId;
	private FinderPath _finderPathCountByGroupId;

	/**
	 * Returns all the learning activities where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching learning activities
	 */
	@Override
	public List<LearningActivity> findByGroupId(long groupId) {
		return findByGroupId(
			groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the learning activities where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @return the range of matching learning activities
	 */
	@Override
	public List<LearningActivity> findByGroupId(
		long groupId, int start, int end) {

		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the learning activities where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching learning activities
	 */
	@Override
	public List<LearningActivity> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<LearningActivity> orderByComparator) {

		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the learning activities where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching learning activities
	 */
	@Override
	public List<LearningActivity> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<LearningActivity> orderByComparator,
		boolean useFinderCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByGroupId;
				finderArgs = new Object[] {groupId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByGroupId;
			finderArgs = new Object[] {groupId, start, end, orderByComparator};
		}

		List<LearningActivity> list = null;

		if (useFinderCache) {
			list = (List<LearningActivity>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LearningActivity learningActivity : list) {
					if ((groupId != learningActivity.getGroupId())) {
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

			query.append(_SQL_SELECT_LEARNINGACTIVITY_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(LearningActivityModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<LearningActivity>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LearningActivity>)QueryUtil.list(
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
	 * Returns the first learning activity in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity
	 * @throws NoSuchLearningActivityException if a matching learning activity could not be found
	 */
	@Override
	public LearningActivity findByGroupId_First(
			long groupId, OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException {

		LearningActivity learningActivity = fetchByGroupId_First(
			groupId, orderByComparator);

		if (learningActivity != null) {
			return learningActivity;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchLearningActivityException(msg.toString());
	}

	/**
	 * Returns the first learning activity in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity, or <code>null</code> if a matching learning activity could not be found
	 */
	@Override
	public LearningActivity fetchByGroupId_First(
		long groupId, OrderByComparator<LearningActivity> orderByComparator) {

		List<LearningActivity> list = findByGroupId(
			groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last learning activity in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity
	 * @throws NoSuchLearningActivityException if a matching learning activity could not be found
	 */
	@Override
	public LearningActivity findByGroupId_Last(
			long groupId, OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException {

		LearningActivity learningActivity = fetchByGroupId_Last(
			groupId, orderByComparator);

		if (learningActivity != null) {
			return learningActivity;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchLearningActivityException(msg.toString());
	}

	/**
	 * Returns the last learning activity in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity, or <code>null</code> if a matching learning activity could not be found
	 */
	@Override
	public LearningActivity fetchByGroupId_Last(
		long groupId, OrderByComparator<LearningActivity> orderByComparator) {

		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<LearningActivity> list = findByGroupId(
			groupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the learning activities before and after the current learning activity in the ordered set where groupId = &#63;.
	 *
	 * @param actId the primary key of the current learning activity
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next learning activity
	 * @throws NoSuchLearningActivityException if a learning activity with the primary key could not be found
	 */
	@Override
	public LearningActivity[] findByGroupId_PrevAndNext(
			long actId, long groupId,
			OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException {

		LearningActivity learningActivity = findByPrimaryKey(actId);

		Session session = null;

		try {
			session = openSession();

			LearningActivity[] array = new LearningActivityImpl[3];

			array[0] = getByGroupId_PrevAndNext(
				session, learningActivity, groupId, orderByComparator, true);

			array[1] = learningActivity;

			array[2] = getByGroupId_PrevAndNext(
				session, learningActivity, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LearningActivity getByGroupId_PrevAndNext(
		Session session, LearningActivity learningActivity, long groupId,
		OrderByComparator<LearningActivity> orderByComparator,
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

		query.append(_SQL_SELECT_LEARNINGACTIVITY_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

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
			query.append(LearningActivityModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						learningActivity)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<LearningActivity> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the learning activities that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching learning activities that the user has permission to view
	 */
	@Override
	public List<LearningActivity> filterFindByGroupId(long groupId) {
		return filterFindByGroupId(
			groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the learning activities that the user has permission to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @return the range of matching learning activities that the user has permission to view
	 */
	@Override
	public List<LearningActivity> filterFindByGroupId(
		long groupId, int start, int end) {

		return filterFindByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the learning activities that the user has permissions to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching learning activities that the user has permission to view
	 */
	@Override
	public List<LearningActivity> filterFindByGroupId(
		long groupId, int start, int end,
		OrderByComparator<LearningActivity> orderByComparator) {

		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupId(groupId, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				3 + (orderByComparator.getOrderByFields().length * 2));
		}
		else {
			query = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_LEARNINGACTIVITY_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_LEARNINGACTIVITY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(
				_FILTER_SQL_SELECT_LEARNINGACTIVITY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator, true);
			}
			else {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_TABLE, orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(LearningActivityModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(LearningActivityModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), LearningActivity.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, LearningActivityImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, LearningActivityImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			return (List<LearningActivity>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the learning activities before and after the current learning activity in the ordered set of learning activities that the user has permission to view where groupId = &#63;.
	 *
	 * @param actId the primary key of the current learning activity
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next learning activity
	 * @throws NoSuchLearningActivityException if a learning activity with the primary key could not be found
	 */
	@Override
	public LearningActivity[] filterFindByGroupId_PrevAndNext(
			long actId, long groupId,
			OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException {

		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupId_PrevAndNext(actId, groupId, orderByComparator);
		}

		LearningActivity learningActivity = findByPrimaryKey(actId);

		Session session = null;

		try {
			session = openSession();

			LearningActivity[] array = new LearningActivityImpl[3];

			array[0] = filterGetByGroupId_PrevAndNext(
				session, learningActivity, groupId, orderByComparator, true);

			array[1] = learningActivity;

			array[2] = filterGetByGroupId_PrevAndNext(
				session, learningActivity, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LearningActivity filterGetByGroupId_PrevAndNext(
		Session session, LearningActivity learningActivity, long groupId,
		OrderByComparator<LearningActivity> orderByComparator,
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

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_LEARNINGACTIVITY_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_LEARNINGACTIVITY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(
				_FILTER_SQL_SELECT_LEARNINGACTIVITY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByConditionFields[i],
							true));
				}
				else {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByConditionFields[i],
							true));
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByFields[i], true));
				}
				else {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByFields[i], true));
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(LearningActivityModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(LearningActivityModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), LearningActivity.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, LearningActivityImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, LearningActivityImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						learningActivity)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<LearningActivity> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the learning activities where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (LearningActivity learningActivity :
				findByGroupId(
					groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(learningActivity);
		}
	}

	/**
	 * Returns the number of learning activities where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching learning activities
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = _finderPathCountByGroupId;

		Object[] finderArgs = new Object[] {groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LEARNINGACTIVITY_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

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

	/**
	 * Returns the number of learning activities that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching learning activities that the user has permission to view
	 */
	@Override
	public int filterCountByGroupId(long groupId) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByGroupId(groupId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_LEARNINGACTIVITY_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), LearningActivity.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(
				COUNT_COLUMN_NAME, com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 =
		"learningActivity.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByModuleId;
	private FinderPath _finderPathWithoutPaginationFindByModuleId;
	private FinderPath _finderPathCountByModuleId;

	/**
	 * Returns all the learning activities where moduleId = &#63;.
	 *
	 * @param moduleId the module ID
	 * @return the matching learning activities
	 */
	@Override
	public List<LearningActivity> findByModuleId(long moduleId) {
		return findByModuleId(
			moduleId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the learning activities where moduleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param moduleId the module ID
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @return the range of matching learning activities
	 */
	@Override
	public List<LearningActivity> findByModuleId(
		long moduleId, int start, int end) {

		return findByModuleId(moduleId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the learning activities where moduleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param moduleId the module ID
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching learning activities
	 */
	@Override
	public List<LearningActivity> findByModuleId(
		long moduleId, int start, int end,
		OrderByComparator<LearningActivity> orderByComparator) {

		return findByModuleId(moduleId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the learning activities where moduleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param moduleId the module ID
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching learning activities
	 */
	@Override
	public List<LearningActivity> findByModuleId(
		long moduleId, int start, int end,
		OrderByComparator<LearningActivity> orderByComparator,
		boolean useFinderCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByModuleId;
				finderArgs = new Object[] {moduleId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByModuleId;
			finderArgs = new Object[] {moduleId, start, end, orderByComparator};
		}

		List<LearningActivity> list = null;

		if (useFinderCache) {
			list = (List<LearningActivity>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LearningActivity learningActivity : list) {
					if ((moduleId != learningActivity.getModuleId())) {
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

			query.append(_SQL_SELECT_LEARNINGACTIVITY_WHERE);

			query.append(_FINDER_COLUMN_MODULEID_MODULEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(LearningActivityModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(moduleId);

				if (!pagination) {
					list = (List<LearningActivity>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LearningActivity>)QueryUtil.list(
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
	 * Returns the first learning activity in the ordered set where moduleId = &#63;.
	 *
	 * @param moduleId the module ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity
	 * @throws NoSuchLearningActivityException if a matching learning activity could not be found
	 */
	@Override
	public LearningActivity findByModuleId_First(
			long moduleId,
			OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException {

		LearningActivity learningActivity = fetchByModuleId_First(
			moduleId, orderByComparator);

		if (learningActivity != null) {
			return learningActivity;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("moduleId=");
		msg.append(moduleId);

		msg.append("}");

		throw new NoSuchLearningActivityException(msg.toString());
	}

	/**
	 * Returns the first learning activity in the ordered set where moduleId = &#63;.
	 *
	 * @param moduleId the module ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity, or <code>null</code> if a matching learning activity could not be found
	 */
	@Override
	public LearningActivity fetchByModuleId_First(
		long moduleId, OrderByComparator<LearningActivity> orderByComparator) {

		List<LearningActivity> list = findByModuleId(
			moduleId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last learning activity in the ordered set where moduleId = &#63;.
	 *
	 * @param moduleId the module ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity
	 * @throws NoSuchLearningActivityException if a matching learning activity could not be found
	 */
	@Override
	public LearningActivity findByModuleId_Last(
			long moduleId,
			OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException {

		LearningActivity learningActivity = fetchByModuleId_Last(
			moduleId, orderByComparator);

		if (learningActivity != null) {
			return learningActivity;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("moduleId=");
		msg.append(moduleId);

		msg.append("}");

		throw new NoSuchLearningActivityException(msg.toString());
	}

	/**
	 * Returns the last learning activity in the ordered set where moduleId = &#63;.
	 *
	 * @param moduleId the module ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity, or <code>null</code> if a matching learning activity could not be found
	 */
	@Override
	public LearningActivity fetchByModuleId_Last(
		long moduleId, OrderByComparator<LearningActivity> orderByComparator) {

		int count = countByModuleId(moduleId);

		if (count == 0) {
			return null;
		}

		List<LearningActivity> list = findByModuleId(
			moduleId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the learning activities before and after the current learning activity in the ordered set where moduleId = &#63;.
	 *
	 * @param actId the primary key of the current learning activity
	 * @param moduleId the module ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next learning activity
	 * @throws NoSuchLearningActivityException if a learning activity with the primary key could not be found
	 */
	@Override
	public LearningActivity[] findByModuleId_PrevAndNext(
			long actId, long moduleId,
			OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException {

		LearningActivity learningActivity = findByPrimaryKey(actId);

		Session session = null;

		try {
			session = openSession();

			LearningActivity[] array = new LearningActivityImpl[3];

			array[0] = getByModuleId_PrevAndNext(
				session, learningActivity, moduleId, orderByComparator, true);

			array[1] = learningActivity;

			array[2] = getByModuleId_PrevAndNext(
				session, learningActivity, moduleId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LearningActivity getByModuleId_PrevAndNext(
		Session session, LearningActivity learningActivity, long moduleId,
		OrderByComparator<LearningActivity> orderByComparator,
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

		query.append(_SQL_SELECT_LEARNINGACTIVITY_WHERE);

		query.append(_FINDER_COLUMN_MODULEID_MODULEID_2);

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
			query.append(LearningActivityModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(moduleId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						learningActivity)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<LearningActivity> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the learning activities where moduleId = &#63; from the database.
	 *
	 * @param moduleId the module ID
	 */
	@Override
	public void removeByModuleId(long moduleId) {
		for (LearningActivity learningActivity :
				findByModuleId(
					moduleId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(learningActivity);
		}
	}

	/**
	 * Returns the number of learning activities where moduleId = &#63;.
	 *
	 * @param moduleId the module ID
	 * @return the number of matching learning activities
	 */
	@Override
	public int countByModuleId(long moduleId) {
		FinderPath finderPath = _finderPathCountByModuleId;

		Object[] finderArgs = new Object[] {moduleId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LEARNINGACTIVITY_WHERE);

			query.append(_FINDER_COLUMN_MODULEID_MODULEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(moduleId);

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

	private static final String _FINDER_COLUMN_MODULEID_MODULEID_2 =
		"learningActivity.moduleId = ?";

	private FinderPath _finderPathWithPaginationFindByModuleIdRequired;
	private FinderPath _finderPathWithoutPaginationFindByModuleIdRequired;
	private FinderPath _finderPathCountByModuleIdRequired;

	/**
	 * Returns all the learning activities where moduleId = &#63; and required = &#63;.
	 *
	 * @param moduleId the module ID
	 * @param required the required
	 * @return the matching learning activities
	 */
	@Override
	public List<LearningActivity> findByModuleIdRequired(
		long moduleId, boolean required) {

		return findByModuleIdRequired(
			moduleId, required, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the learning activities where moduleId = &#63; and required = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param moduleId the module ID
	 * @param required the required
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @return the range of matching learning activities
	 */
	@Override
	public List<LearningActivity> findByModuleIdRequired(
		long moduleId, boolean required, int start, int end) {

		return findByModuleIdRequired(moduleId, required, start, end, null);
	}

	/**
	 * Returns an ordered range of all the learning activities where moduleId = &#63; and required = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param moduleId the module ID
	 * @param required the required
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching learning activities
	 */
	@Override
	public List<LearningActivity> findByModuleIdRequired(
		long moduleId, boolean required, int start, int end,
		OrderByComparator<LearningActivity> orderByComparator) {

		return findByModuleIdRequired(
			moduleId, required, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the learning activities where moduleId = &#63; and required = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param moduleId the module ID
	 * @param required the required
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching learning activities
	 */
	@Override
	public List<LearningActivity> findByModuleIdRequired(
		long moduleId, boolean required, int start, int end,
		OrderByComparator<LearningActivity> orderByComparator,
		boolean useFinderCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByModuleIdRequired;
				finderArgs = new Object[] {moduleId, required};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByModuleIdRequired;
			finderArgs = new Object[] {
				moduleId, required, start, end, orderByComparator
			};
		}

		List<LearningActivity> list = null;

		if (useFinderCache) {
			list = (List<LearningActivity>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LearningActivity learningActivity : list) {
					if ((moduleId != learningActivity.getModuleId()) ||
						(required != learningActivity.isRequired())) {

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

			query.append(_SQL_SELECT_LEARNINGACTIVITY_WHERE);

			query.append(_FINDER_COLUMN_MODULEIDREQUIRED_MODULEID_2);

			query.append(_FINDER_COLUMN_MODULEIDREQUIRED_REQUIRED_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(LearningActivityModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(moduleId);

				qPos.add(required);

				if (!pagination) {
					list = (List<LearningActivity>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LearningActivity>)QueryUtil.list(
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
	 * Returns the first learning activity in the ordered set where moduleId = &#63; and required = &#63;.
	 *
	 * @param moduleId the module ID
	 * @param required the required
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity
	 * @throws NoSuchLearningActivityException if a matching learning activity could not be found
	 */
	@Override
	public LearningActivity findByModuleIdRequired_First(
			long moduleId, boolean required,
			OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException {

		LearningActivity learningActivity = fetchByModuleIdRequired_First(
			moduleId, required, orderByComparator);

		if (learningActivity != null) {
			return learningActivity;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("moduleId=");
		msg.append(moduleId);

		msg.append(", required=");
		msg.append(required);

		msg.append("}");

		throw new NoSuchLearningActivityException(msg.toString());
	}

	/**
	 * Returns the first learning activity in the ordered set where moduleId = &#63; and required = &#63;.
	 *
	 * @param moduleId the module ID
	 * @param required the required
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity, or <code>null</code> if a matching learning activity could not be found
	 */
	@Override
	public LearningActivity fetchByModuleIdRequired_First(
		long moduleId, boolean required,
		OrderByComparator<LearningActivity> orderByComparator) {

		List<LearningActivity> list = findByModuleIdRequired(
			moduleId, required, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last learning activity in the ordered set where moduleId = &#63; and required = &#63;.
	 *
	 * @param moduleId the module ID
	 * @param required the required
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity
	 * @throws NoSuchLearningActivityException if a matching learning activity could not be found
	 */
	@Override
	public LearningActivity findByModuleIdRequired_Last(
			long moduleId, boolean required,
			OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException {

		LearningActivity learningActivity = fetchByModuleIdRequired_Last(
			moduleId, required, orderByComparator);

		if (learningActivity != null) {
			return learningActivity;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("moduleId=");
		msg.append(moduleId);

		msg.append(", required=");
		msg.append(required);

		msg.append("}");

		throw new NoSuchLearningActivityException(msg.toString());
	}

	/**
	 * Returns the last learning activity in the ordered set where moduleId = &#63; and required = &#63;.
	 *
	 * @param moduleId the module ID
	 * @param required the required
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity, or <code>null</code> if a matching learning activity could not be found
	 */
	@Override
	public LearningActivity fetchByModuleIdRequired_Last(
		long moduleId, boolean required,
		OrderByComparator<LearningActivity> orderByComparator) {

		int count = countByModuleIdRequired(moduleId, required);

		if (count == 0) {
			return null;
		}

		List<LearningActivity> list = findByModuleIdRequired(
			moduleId, required, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the learning activities before and after the current learning activity in the ordered set where moduleId = &#63; and required = &#63;.
	 *
	 * @param actId the primary key of the current learning activity
	 * @param moduleId the module ID
	 * @param required the required
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next learning activity
	 * @throws NoSuchLearningActivityException if a learning activity with the primary key could not be found
	 */
	@Override
	public LearningActivity[] findByModuleIdRequired_PrevAndNext(
			long actId, long moduleId, boolean required,
			OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException {

		LearningActivity learningActivity = findByPrimaryKey(actId);

		Session session = null;

		try {
			session = openSession();

			LearningActivity[] array = new LearningActivityImpl[3];

			array[0] = getByModuleIdRequired_PrevAndNext(
				session, learningActivity, moduleId, required,
				orderByComparator, true);

			array[1] = learningActivity;

			array[2] = getByModuleIdRequired_PrevAndNext(
				session, learningActivity, moduleId, required,
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

	protected LearningActivity getByModuleIdRequired_PrevAndNext(
		Session session, LearningActivity learningActivity, long moduleId,
		boolean required, OrderByComparator<LearningActivity> orderByComparator,
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

		query.append(_SQL_SELECT_LEARNINGACTIVITY_WHERE);

		query.append(_FINDER_COLUMN_MODULEIDREQUIRED_MODULEID_2);

		query.append(_FINDER_COLUMN_MODULEIDREQUIRED_REQUIRED_2);

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
			query.append(LearningActivityModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(moduleId);

		qPos.add(required);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						learningActivity)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<LearningActivity> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the learning activities where moduleId = &#63; and required = &#63; from the database.
	 *
	 * @param moduleId the module ID
	 * @param required the required
	 */
	@Override
	public void removeByModuleIdRequired(long moduleId, boolean required) {
		for (LearningActivity learningActivity :
				findByModuleIdRequired(
					moduleId, required, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(learningActivity);
		}
	}

	/**
	 * Returns the number of learning activities where moduleId = &#63; and required = &#63;.
	 *
	 * @param moduleId the module ID
	 * @param required the required
	 * @return the number of matching learning activities
	 */
	@Override
	public int countByModuleIdRequired(long moduleId, boolean required) {
		FinderPath finderPath = _finderPathCountByModuleIdRequired;

		Object[] finderArgs = new Object[] {moduleId, required};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LEARNINGACTIVITY_WHERE);

			query.append(_FINDER_COLUMN_MODULEIDREQUIRED_MODULEID_2);

			query.append(_FINDER_COLUMN_MODULEIDREQUIRED_REQUIRED_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(moduleId);

				qPos.add(required);

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

	private static final String _FINDER_COLUMN_MODULEIDREQUIRED_MODULEID_2 =
		"learningActivity.moduleId = ? AND ";

	private static final String _FINDER_COLUMN_MODULEIDREQUIRED_REQUIRED_2 =
		"learningActivity.required = ?";

	private FinderPath _finderPathWithPaginationFindByGroupIdRequired;
	private FinderPath _finderPathWithoutPaginationFindByGroupIdRequired;
	private FinderPath _finderPathCountByGroupIdRequired;

	/**
	 * Returns all the learning activities where groupId = &#63; and required = &#63;.
	 *
	 * @param groupId the group ID
	 * @param required the required
	 * @return the matching learning activities
	 */
	@Override
	public List<LearningActivity> findByGroupIdRequired(
		long groupId, boolean required) {

		return findByGroupIdRequired(
			groupId, required, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the learning activities where groupId = &#63; and required = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param required the required
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @return the range of matching learning activities
	 */
	@Override
	public List<LearningActivity> findByGroupIdRequired(
		long groupId, boolean required, int start, int end) {

		return findByGroupIdRequired(groupId, required, start, end, null);
	}

	/**
	 * Returns an ordered range of all the learning activities where groupId = &#63; and required = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param required the required
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching learning activities
	 */
	@Override
	public List<LearningActivity> findByGroupIdRequired(
		long groupId, boolean required, int start, int end,
		OrderByComparator<LearningActivity> orderByComparator) {

		return findByGroupIdRequired(
			groupId, required, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the learning activities where groupId = &#63; and required = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param required the required
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching learning activities
	 */
	@Override
	public List<LearningActivity> findByGroupIdRequired(
		long groupId, boolean required, int start, int end,
		OrderByComparator<LearningActivity> orderByComparator,
		boolean useFinderCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByGroupIdRequired;
				finderArgs = new Object[] {groupId, required};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByGroupIdRequired;
			finderArgs = new Object[] {
				groupId, required, start, end, orderByComparator
			};
		}

		List<LearningActivity> list = null;

		if (useFinderCache) {
			list = (List<LearningActivity>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LearningActivity learningActivity : list) {
					if ((groupId != learningActivity.getGroupId()) ||
						(required != learningActivity.isRequired())) {

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

			query.append(_SQL_SELECT_LEARNINGACTIVITY_WHERE);

			query.append(_FINDER_COLUMN_GROUPIDREQUIRED_GROUPID_2);

			query.append(_FINDER_COLUMN_GROUPIDREQUIRED_REQUIRED_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(LearningActivityModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(required);

				if (!pagination) {
					list = (List<LearningActivity>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LearningActivity>)QueryUtil.list(
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
	 * Returns the first learning activity in the ordered set where groupId = &#63; and required = &#63;.
	 *
	 * @param groupId the group ID
	 * @param required the required
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity
	 * @throws NoSuchLearningActivityException if a matching learning activity could not be found
	 */
	@Override
	public LearningActivity findByGroupIdRequired_First(
			long groupId, boolean required,
			OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException {

		LearningActivity learningActivity = fetchByGroupIdRequired_First(
			groupId, required, orderByComparator);

		if (learningActivity != null) {
			return learningActivity;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", required=");
		msg.append(required);

		msg.append("}");

		throw new NoSuchLearningActivityException(msg.toString());
	}

	/**
	 * Returns the first learning activity in the ordered set where groupId = &#63; and required = &#63;.
	 *
	 * @param groupId the group ID
	 * @param required the required
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity, or <code>null</code> if a matching learning activity could not be found
	 */
	@Override
	public LearningActivity fetchByGroupIdRequired_First(
		long groupId, boolean required,
		OrderByComparator<LearningActivity> orderByComparator) {

		List<LearningActivity> list = findByGroupIdRequired(
			groupId, required, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last learning activity in the ordered set where groupId = &#63; and required = &#63;.
	 *
	 * @param groupId the group ID
	 * @param required the required
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity
	 * @throws NoSuchLearningActivityException if a matching learning activity could not be found
	 */
	@Override
	public LearningActivity findByGroupIdRequired_Last(
			long groupId, boolean required,
			OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException {

		LearningActivity learningActivity = fetchByGroupIdRequired_Last(
			groupId, required, orderByComparator);

		if (learningActivity != null) {
			return learningActivity;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", required=");
		msg.append(required);

		msg.append("}");

		throw new NoSuchLearningActivityException(msg.toString());
	}

	/**
	 * Returns the last learning activity in the ordered set where groupId = &#63; and required = &#63;.
	 *
	 * @param groupId the group ID
	 * @param required the required
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity, or <code>null</code> if a matching learning activity could not be found
	 */
	@Override
	public LearningActivity fetchByGroupIdRequired_Last(
		long groupId, boolean required,
		OrderByComparator<LearningActivity> orderByComparator) {

		int count = countByGroupIdRequired(groupId, required);

		if (count == 0) {
			return null;
		}

		List<LearningActivity> list = findByGroupIdRequired(
			groupId, required, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the learning activities before and after the current learning activity in the ordered set where groupId = &#63; and required = &#63;.
	 *
	 * @param actId the primary key of the current learning activity
	 * @param groupId the group ID
	 * @param required the required
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next learning activity
	 * @throws NoSuchLearningActivityException if a learning activity with the primary key could not be found
	 */
	@Override
	public LearningActivity[] findByGroupIdRequired_PrevAndNext(
			long actId, long groupId, boolean required,
			OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException {

		LearningActivity learningActivity = findByPrimaryKey(actId);

		Session session = null;

		try {
			session = openSession();

			LearningActivity[] array = new LearningActivityImpl[3];

			array[0] = getByGroupIdRequired_PrevAndNext(
				session, learningActivity, groupId, required, orderByComparator,
				true);

			array[1] = learningActivity;

			array[2] = getByGroupIdRequired_PrevAndNext(
				session, learningActivity, groupId, required, orderByComparator,
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

	protected LearningActivity getByGroupIdRequired_PrevAndNext(
		Session session, LearningActivity learningActivity, long groupId,
		boolean required, OrderByComparator<LearningActivity> orderByComparator,
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

		query.append(_SQL_SELECT_LEARNINGACTIVITY_WHERE);

		query.append(_FINDER_COLUMN_GROUPIDREQUIRED_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDREQUIRED_REQUIRED_2);

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
			query.append(LearningActivityModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(required);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						learningActivity)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<LearningActivity> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the learning activities that the user has permission to view where groupId = &#63; and required = &#63;.
	 *
	 * @param groupId the group ID
	 * @param required the required
	 * @return the matching learning activities that the user has permission to view
	 */
	@Override
	public List<LearningActivity> filterFindByGroupIdRequired(
		long groupId, boolean required) {

		return filterFindByGroupIdRequired(
			groupId, required, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the learning activities that the user has permission to view where groupId = &#63; and required = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param required the required
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @return the range of matching learning activities that the user has permission to view
	 */
	@Override
	public List<LearningActivity> filterFindByGroupIdRequired(
		long groupId, boolean required, int start, int end) {

		return filterFindByGroupIdRequired(groupId, required, start, end, null);
	}

	/**
	 * Returns an ordered range of all the learning activities that the user has permissions to view where groupId = &#63; and required = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param required the required
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching learning activities that the user has permission to view
	 */
	@Override
	public List<LearningActivity> filterFindByGroupIdRequired(
		long groupId, boolean required, int start, int end,
		OrderByComparator<LearningActivity> orderByComparator) {

		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupIdRequired(
				groupId, required, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByFields().length * 2));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_LEARNINGACTIVITY_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_LEARNINGACTIVITY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPIDREQUIRED_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDREQUIRED_REQUIRED_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(
				_FILTER_SQL_SELECT_LEARNINGACTIVITY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator, true);
			}
			else {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_TABLE, orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(LearningActivityModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(LearningActivityModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), LearningActivity.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, LearningActivityImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, LearningActivityImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(required);

			return (List<LearningActivity>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the learning activities before and after the current learning activity in the ordered set of learning activities that the user has permission to view where groupId = &#63; and required = &#63;.
	 *
	 * @param actId the primary key of the current learning activity
	 * @param groupId the group ID
	 * @param required the required
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next learning activity
	 * @throws NoSuchLearningActivityException if a learning activity with the primary key could not be found
	 */
	@Override
	public LearningActivity[] filterFindByGroupIdRequired_PrevAndNext(
			long actId, long groupId, boolean required,
			OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException {

		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupIdRequired_PrevAndNext(
				actId, groupId, required, orderByComparator);
		}

		LearningActivity learningActivity = findByPrimaryKey(actId);

		Session session = null;

		try {
			session = openSession();

			LearningActivity[] array = new LearningActivityImpl[3];

			array[0] = filterGetByGroupIdRequired_PrevAndNext(
				session, learningActivity, groupId, required, orderByComparator,
				true);

			array[1] = learningActivity;

			array[2] = filterGetByGroupIdRequired_PrevAndNext(
				session, learningActivity, groupId, required, orderByComparator,
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

	protected LearningActivity filterGetByGroupIdRequired_PrevAndNext(
		Session session, LearningActivity learningActivity, long groupId,
		boolean required, OrderByComparator<LearningActivity> orderByComparator,
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

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_LEARNINGACTIVITY_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_LEARNINGACTIVITY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPIDREQUIRED_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDREQUIRED_REQUIRED_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(
				_FILTER_SQL_SELECT_LEARNINGACTIVITY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByConditionFields[i],
							true));
				}
				else {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByConditionFields[i],
							true));
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByFields[i], true));
				}
				else {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByFields[i], true));
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(LearningActivityModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(LearningActivityModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), LearningActivity.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, LearningActivityImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, LearningActivityImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(required);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						learningActivity)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<LearningActivity> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the learning activities where groupId = &#63; and required = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param required the required
	 */
	@Override
	public void removeByGroupIdRequired(long groupId, boolean required) {
		for (LearningActivity learningActivity :
				findByGroupIdRequired(
					groupId, required, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(learningActivity);
		}
	}

	/**
	 * Returns the number of learning activities where groupId = &#63; and required = &#63;.
	 *
	 * @param groupId the group ID
	 * @param required the required
	 * @return the number of matching learning activities
	 */
	@Override
	public int countByGroupIdRequired(long groupId, boolean required) {
		FinderPath finderPath = _finderPathCountByGroupIdRequired;

		Object[] finderArgs = new Object[] {groupId, required};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LEARNINGACTIVITY_WHERE);

			query.append(_FINDER_COLUMN_GROUPIDREQUIRED_GROUPID_2);

			query.append(_FINDER_COLUMN_GROUPIDREQUIRED_REQUIRED_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(required);

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

	/**
	 * Returns the number of learning activities that the user has permission to view where groupId = &#63; and required = &#63;.
	 *
	 * @param groupId the group ID
	 * @param required the required
	 * @return the number of matching learning activities that the user has permission to view
	 */
	@Override
	public int filterCountByGroupIdRequired(long groupId, boolean required) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByGroupIdRequired(groupId, required);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_LEARNINGACTIVITY_WHERE);

		query.append(_FINDER_COLUMN_GROUPIDREQUIRED_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDREQUIRED_REQUIRED_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), LearningActivity.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(
				COUNT_COLUMN_NAME, com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(required);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_GROUPIDREQUIRED_GROUPID_2 =
		"learningActivity.groupId = ? AND ";

	private static final String _FINDER_COLUMN_GROUPIDREQUIRED_REQUIRED_2 =
		"learningActivity.required = ?";

	private FinderPath _finderPathWithPaginationFindByTypeId;
	private FinderPath _finderPathWithoutPaginationFindByTypeId;
	private FinderPath _finderPathCountByTypeId;

	/**
	 * Returns all the learning activities where typeId = &#63;.
	 *
	 * @param typeId the type ID
	 * @return the matching learning activities
	 */
	@Override
	public List<LearningActivity> findByTypeId(long typeId) {
		return findByTypeId(typeId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the learning activities where typeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param typeId the type ID
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @return the range of matching learning activities
	 */
	@Override
	public List<LearningActivity> findByTypeId(
		long typeId, int start, int end) {

		return findByTypeId(typeId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the learning activities where typeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param typeId the type ID
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching learning activities
	 */
	@Override
	public List<LearningActivity> findByTypeId(
		long typeId, int start, int end,
		OrderByComparator<LearningActivity> orderByComparator) {

		return findByTypeId(typeId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the learning activities where typeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param typeId the type ID
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching learning activities
	 */
	@Override
	public List<LearningActivity> findByTypeId(
		long typeId, int start, int end,
		OrderByComparator<LearningActivity> orderByComparator,
		boolean useFinderCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByTypeId;
				finderArgs = new Object[] {typeId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByTypeId;
			finderArgs = new Object[] {typeId, start, end, orderByComparator};
		}

		List<LearningActivity> list = null;

		if (useFinderCache) {
			list = (List<LearningActivity>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LearningActivity learningActivity : list) {
					if ((typeId != learningActivity.getTypeId())) {
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

			query.append(_SQL_SELECT_LEARNINGACTIVITY_WHERE);

			query.append(_FINDER_COLUMN_TYPEID_TYPEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(LearningActivityModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(typeId);

				if (!pagination) {
					list = (List<LearningActivity>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LearningActivity>)QueryUtil.list(
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
	 * Returns the first learning activity in the ordered set where typeId = &#63;.
	 *
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity
	 * @throws NoSuchLearningActivityException if a matching learning activity could not be found
	 */
	@Override
	public LearningActivity findByTypeId_First(
			long typeId, OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException {

		LearningActivity learningActivity = fetchByTypeId_First(
			typeId, orderByComparator);

		if (learningActivity != null) {
			return learningActivity;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("typeId=");
		msg.append(typeId);

		msg.append("}");

		throw new NoSuchLearningActivityException(msg.toString());
	}

	/**
	 * Returns the first learning activity in the ordered set where typeId = &#63;.
	 *
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity, or <code>null</code> if a matching learning activity could not be found
	 */
	@Override
	public LearningActivity fetchByTypeId_First(
		long typeId, OrderByComparator<LearningActivity> orderByComparator) {

		List<LearningActivity> list = findByTypeId(
			typeId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last learning activity in the ordered set where typeId = &#63;.
	 *
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity
	 * @throws NoSuchLearningActivityException if a matching learning activity could not be found
	 */
	@Override
	public LearningActivity findByTypeId_Last(
			long typeId, OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException {

		LearningActivity learningActivity = fetchByTypeId_Last(
			typeId, orderByComparator);

		if (learningActivity != null) {
			return learningActivity;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("typeId=");
		msg.append(typeId);

		msg.append("}");

		throw new NoSuchLearningActivityException(msg.toString());
	}

	/**
	 * Returns the last learning activity in the ordered set where typeId = &#63;.
	 *
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity, or <code>null</code> if a matching learning activity could not be found
	 */
	@Override
	public LearningActivity fetchByTypeId_Last(
		long typeId, OrderByComparator<LearningActivity> orderByComparator) {

		int count = countByTypeId(typeId);

		if (count == 0) {
			return null;
		}

		List<LearningActivity> list = findByTypeId(
			typeId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the learning activities before and after the current learning activity in the ordered set where typeId = &#63;.
	 *
	 * @param actId the primary key of the current learning activity
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next learning activity
	 * @throws NoSuchLearningActivityException if a learning activity with the primary key could not be found
	 */
	@Override
	public LearningActivity[] findByTypeId_PrevAndNext(
			long actId, long typeId,
			OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException {

		LearningActivity learningActivity = findByPrimaryKey(actId);

		Session session = null;

		try {
			session = openSession();

			LearningActivity[] array = new LearningActivityImpl[3];

			array[0] = getByTypeId_PrevAndNext(
				session, learningActivity, typeId, orderByComparator, true);

			array[1] = learningActivity;

			array[2] = getByTypeId_PrevAndNext(
				session, learningActivity, typeId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LearningActivity getByTypeId_PrevAndNext(
		Session session, LearningActivity learningActivity, long typeId,
		OrderByComparator<LearningActivity> orderByComparator,
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

		query.append(_SQL_SELECT_LEARNINGACTIVITY_WHERE);

		query.append(_FINDER_COLUMN_TYPEID_TYPEID_2);

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
			query.append(LearningActivityModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(typeId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						learningActivity)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<LearningActivity> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the learning activities where typeId = &#63; from the database.
	 *
	 * @param typeId the type ID
	 */
	@Override
	public void removeByTypeId(long typeId) {
		for (LearningActivity learningActivity :
				findByTypeId(
					typeId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(learningActivity);
		}
	}

	/**
	 * Returns the number of learning activities where typeId = &#63;.
	 *
	 * @param typeId the type ID
	 * @return the number of matching learning activities
	 */
	@Override
	public int countByTypeId(long typeId) {
		FinderPath finderPath = _finderPathCountByTypeId;

		Object[] finderArgs = new Object[] {typeId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LEARNINGACTIVITY_WHERE);

			query.append(_FINDER_COLUMN_TYPEID_TYPEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(typeId);

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

	private static final String _FINDER_COLUMN_TYPEID_TYPEID_2 =
		"learningActivity.typeId = ?";

	private FinderPath
		_finderPathWithPaginationFindByModuleIdNextLearningActivities;
	private FinderPath
		_finderPathWithPaginationCountByModuleIdNextLearningActivities;

	/**
	 * Returns all the learning activities where groupId = &#63; and moduleId = &#63; and priority &gt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 * @return the matching learning activities
	 */
	@Override
	public List<LearningActivity> findByModuleIdNextLearningActivities(
		long groupId, long moduleId, long priority) {

		return findByModuleIdNextLearningActivities(
			groupId, moduleId, priority, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the learning activities where groupId = &#63; and moduleId = &#63; and priority &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @return the range of matching learning activities
	 */
	@Override
	public List<LearningActivity> findByModuleIdNextLearningActivities(
		long groupId, long moduleId, long priority, int start, int end) {

		return findByModuleIdNextLearningActivities(
			groupId, moduleId, priority, start, end, null);
	}

	/**
	 * Returns an ordered range of all the learning activities where groupId = &#63; and moduleId = &#63; and priority &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching learning activities
	 */
	@Override
	public List<LearningActivity> findByModuleIdNextLearningActivities(
		long groupId, long moduleId, long priority, int start, int end,
		OrderByComparator<LearningActivity> orderByComparator) {

		return findByModuleIdNextLearningActivities(
			groupId, moduleId, priority, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the learning activities where groupId = &#63; and moduleId = &#63; and priority &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching learning activities
	 */
	@Override
	public List<LearningActivity> findByModuleIdNextLearningActivities(
		long groupId, long moduleId, long priority, int start, int end,
		OrderByComparator<LearningActivity> orderByComparator,
		boolean useFinderCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath =
			_finderPathWithPaginationFindByModuleIdNextLearningActivities;
		finderArgs = new Object[] {
			groupId, moduleId, priority, start, end, orderByComparator
		};

		List<LearningActivity> list = null;

		if (useFinderCache) {
			list = (List<LearningActivity>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LearningActivity learningActivity : list) {
					if ((groupId != learningActivity.getGroupId()) ||
						(moduleId != learningActivity.getModuleId()) ||
						(priority >= learningActivity.getPriority())) {

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

			query.append(_SQL_SELECT_LEARNINGACTIVITY_WHERE);

			query.append(
				_FINDER_COLUMN_MODULEIDNEXTLEARNINGACTIVITIES_GROUPID_2);

			query.append(
				_FINDER_COLUMN_MODULEIDNEXTLEARNINGACTIVITIES_MODULEID_2);

			query.append(
				_FINDER_COLUMN_MODULEIDNEXTLEARNINGACTIVITIES_PRIORITY_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(LearningActivityModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(moduleId);

				qPos.add(priority);

				if (!pagination) {
					list = (List<LearningActivity>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LearningActivity>)QueryUtil.list(
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
	 * Returns the first learning activity in the ordered set where groupId = &#63; and moduleId = &#63; and priority &gt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity
	 * @throws NoSuchLearningActivityException if a matching learning activity could not be found
	 */
	@Override
	public LearningActivity findByModuleIdNextLearningActivities_First(
			long groupId, long moduleId, long priority,
			OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException {

		LearningActivity learningActivity =
			fetchByModuleIdNextLearningActivities_First(
				groupId, moduleId, priority, orderByComparator);

		if (learningActivity != null) {
			return learningActivity;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", moduleId=");
		msg.append(moduleId);

		msg.append(", priority>");
		msg.append(priority);

		msg.append("}");

		throw new NoSuchLearningActivityException(msg.toString());
	}

	/**
	 * Returns the first learning activity in the ordered set where groupId = &#63; and moduleId = &#63; and priority &gt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity, or <code>null</code> if a matching learning activity could not be found
	 */
	@Override
	public LearningActivity fetchByModuleIdNextLearningActivities_First(
		long groupId, long moduleId, long priority,
		OrderByComparator<LearningActivity> orderByComparator) {

		List<LearningActivity> list = findByModuleIdNextLearningActivities(
			groupId, moduleId, priority, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last learning activity in the ordered set where groupId = &#63; and moduleId = &#63; and priority &gt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity
	 * @throws NoSuchLearningActivityException if a matching learning activity could not be found
	 */
	@Override
	public LearningActivity findByModuleIdNextLearningActivities_Last(
			long groupId, long moduleId, long priority,
			OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException {

		LearningActivity learningActivity =
			fetchByModuleIdNextLearningActivities_Last(
				groupId, moduleId, priority, orderByComparator);

		if (learningActivity != null) {
			return learningActivity;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", moduleId=");
		msg.append(moduleId);

		msg.append(", priority>");
		msg.append(priority);

		msg.append("}");

		throw new NoSuchLearningActivityException(msg.toString());
	}

	/**
	 * Returns the last learning activity in the ordered set where groupId = &#63; and moduleId = &#63; and priority &gt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity, or <code>null</code> if a matching learning activity could not be found
	 */
	@Override
	public LearningActivity fetchByModuleIdNextLearningActivities_Last(
		long groupId, long moduleId, long priority,
		OrderByComparator<LearningActivity> orderByComparator) {

		int count = countByModuleIdNextLearningActivities(
			groupId, moduleId, priority);

		if (count == 0) {
			return null;
		}

		List<LearningActivity> list = findByModuleIdNextLearningActivities(
			groupId, moduleId, priority, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the learning activities before and after the current learning activity in the ordered set where groupId = &#63; and moduleId = &#63; and priority &gt; &#63;.
	 *
	 * @param actId the primary key of the current learning activity
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next learning activity
	 * @throws NoSuchLearningActivityException if a learning activity with the primary key could not be found
	 */
	@Override
	public LearningActivity[] findByModuleIdNextLearningActivities_PrevAndNext(
			long actId, long groupId, long moduleId, long priority,
			OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException {

		LearningActivity learningActivity = findByPrimaryKey(actId);

		Session session = null;

		try {
			session = openSession();

			LearningActivity[] array = new LearningActivityImpl[3];

			array[0] = getByModuleIdNextLearningActivities_PrevAndNext(
				session, learningActivity, groupId, moduleId, priority,
				orderByComparator, true);

			array[1] = learningActivity;

			array[2] = getByModuleIdNextLearningActivities_PrevAndNext(
				session, learningActivity, groupId, moduleId, priority,
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

	protected LearningActivity getByModuleIdNextLearningActivities_PrevAndNext(
		Session session, LearningActivity learningActivity, long groupId,
		long moduleId, long priority,
		OrderByComparator<LearningActivity> orderByComparator,
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

		query.append(_SQL_SELECT_LEARNINGACTIVITY_WHERE);

		query.append(_FINDER_COLUMN_MODULEIDNEXTLEARNINGACTIVITIES_GROUPID_2);

		query.append(_FINDER_COLUMN_MODULEIDNEXTLEARNINGACTIVITIES_MODULEID_2);

		query.append(_FINDER_COLUMN_MODULEIDNEXTLEARNINGACTIVITIES_PRIORITY_2);

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
			query.append(LearningActivityModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(moduleId);

		qPos.add(priority);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						learningActivity)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<LearningActivity> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the learning activities that the user has permission to view where groupId = &#63; and moduleId = &#63; and priority &gt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 * @return the matching learning activities that the user has permission to view
	 */
	@Override
	public List<LearningActivity> filterFindByModuleIdNextLearningActivities(
		long groupId, long moduleId, long priority) {

		return filterFindByModuleIdNextLearningActivities(
			groupId, moduleId, priority, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the learning activities that the user has permission to view where groupId = &#63; and moduleId = &#63; and priority &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @return the range of matching learning activities that the user has permission to view
	 */
	@Override
	public List<LearningActivity> filterFindByModuleIdNextLearningActivities(
		long groupId, long moduleId, long priority, int start, int end) {

		return filterFindByModuleIdNextLearningActivities(
			groupId, moduleId, priority, start, end, null);
	}

	/**
	 * Returns an ordered range of all the learning activities that the user has permissions to view where groupId = &#63; and moduleId = &#63; and priority &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching learning activities that the user has permission to view
	 */
	@Override
	public List<LearningActivity> filterFindByModuleIdNextLearningActivities(
		long groupId, long moduleId, long priority, int start, int end,
		OrderByComparator<LearningActivity> orderByComparator) {

		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByModuleIdNextLearningActivities(
				groupId, moduleId, priority, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				5 + (orderByComparator.getOrderByFields().length * 2));
		}
		else {
			query = new StringBundler(6);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_LEARNINGACTIVITY_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_LEARNINGACTIVITY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_MODULEIDNEXTLEARNINGACTIVITIES_GROUPID_2);

		query.append(_FINDER_COLUMN_MODULEIDNEXTLEARNINGACTIVITIES_MODULEID_2);

		query.append(_FINDER_COLUMN_MODULEIDNEXTLEARNINGACTIVITIES_PRIORITY_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(
				_FILTER_SQL_SELECT_LEARNINGACTIVITY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator, true);
			}
			else {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_TABLE, orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(LearningActivityModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(LearningActivityModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), LearningActivity.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, LearningActivityImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, LearningActivityImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(moduleId);

			qPos.add(priority);

			return (List<LearningActivity>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the learning activities before and after the current learning activity in the ordered set of learning activities that the user has permission to view where groupId = &#63; and moduleId = &#63; and priority &gt; &#63;.
	 *
	 * @param actId the primary key of the current learning activity
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next learning activity
	 * @throws NoSuchLearningActivityException if a learning activity with the primary key could not be found
	 */
	@Override
	public LearningActivity[]
			filterFindByModuleIdNextLearningActivities_PrevAndNext(
				long actId, long groupId, long moduleId, long priority,
				OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException {

		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByModuleIdNextLearningActivities_PrevAndNext(
				actId, groupId, moduleId, priority, orderByComparator);
		}

		LearningActivity learningActivity = findByPrimaryKey(actId);

		Session session = null;

		try {
			session = openSession();

			LearningActivity[] array = new LearningActivityImpl[3];

			array[0] = filterGetByModuleIdNextLearningActivities_PrevAndNext(
				session, learningActivity, groupId, moduleId, priority,
				orderByComparator, true);

			array[1] = learningActivity;

			array[2] = filterGetByModuleIdNextLearningActivities_PrevAndNext(
				session, learningActivity, groupId, moduleId, priority,
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

	protected LearningActivity
		filterGetByModuleIdNextLearningActivities_PrevAndNext(
			Session session, LearningActivity learningActivity, long groupId,
			long moduleId, long priority,
			OrderByComparator<LearningActivity> orderByComparator,
			boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				7 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(6);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_LEARNINGACTIVITY_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_LEARNINGACTIVITY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_MODULEIDNEXTLEARNINGACTIVITIES_GROUPID_2);

		query.append(_FINDER_COLUMN_MODULEIDNEXTLEARNINGACTIVITIES_MODULEID_2);

		query.append(_FINDER_COLUMN_MODULEIDNEXTLEARNINGACTIVITIES_PRIORITY_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(
				_FILTER_SQL_SELECT_LEARNINGACTIVITY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByConditionFields[i],
							true));
				}
				else {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByConditionFields[i],
							true));
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByFields[i], true));
				}
				else {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByFields[i], true));
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(LearningActivityModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(LearningActivityModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), LearningActivity.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, LearningActivityImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, LearningActivityImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(moduleId);

		qPos.add(priority);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						learningActivity)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<LearningActivity> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the learning activities where groupId = &#63; and moduleId = &#63; and priority &gt; &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 */
	@Override
	public void removeByModuleIdNextLearningActivities(
		long groupId, long moduleId, long priority) {

		for (LearningActivity learningActivity :
				findByModuleIdNextLearningActivities(
					groupId, moduleId, priority, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(learningActivity);
		}
	}

	/**
	 * Returns the number of learning activities where groupId = &#63; and moduleId = &#63; and priority &gt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 * @return the number of matching learning activities
	 */
	@Override
	public int countByModuleIdNextLearningActivities(
		long groupId, long moduleId, long priority) {

		FinderPath finderPath =
			_finderPathWithPaginationCountByModuleIdNextLearningActivities;

		Object[] finderArgs = new Object[] {groupId, moduleId, priority};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_LEARNINGACTIVITY_WHERE);

			query.append(
				_FINDER_COLUMN_MODULEIDNEXTLEARNINGACTIVITIES_GROUPID_2);

			query.append(
				_FINDER_COLUMN_MODULEIDNEXTLEARNINGACTIVITIES_MODULEID_2);

			query.append(
				_FINDER_COLUMN_MODULEIDNEXTLEARNINGACTIVITIES_PRIORITY_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(moduleId);

				qPos.add(priority);

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

	/**
	 * Returns the number of learning activities that the user has permission to view where groupId = &#63; and moduleId = &#63; and priority &gt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 * @return the number of matching learning activities that the user has permission to view
	 */
	@Override
	public int filterCountByModuleIdNextLearningActivities(
		long groupId, long moduleId, long priority) {

		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByModuleIdNextLearningActivities(
				groupId, moduleId, priority);
		}

		StringBundler query = new StringBundler(4);

		query.append(_FILTER_SQL_COUNT_LEARNINGACTIVITY_WHERE);

		query.append(_FINDER_COLUMN_MODULEIDNEXTLEARNINGACTIVITIES_GROUPID_2);

		query.append(_FINDER_COLUMN_MODULEIDNEXTLEARNINGACTIVITIES_MODULEID_2);

		query.append(_FINDER_COLUMN_MODULEIDNEXTLEARNINGACTIVITIES_PRIORITY_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), LearningActivity.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(
				COUNT_COLUMN_NAME, com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(moduleId);

			qPos.add(priority);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String
		_FINDER_COLUMN_MODULEIDNEXTLEARNINGACTIVITIES_GROUPID_2 =
			"learningActivity.groupId = ? AND ";

	private static final String
		_FINDER_COLUMN_MODULEIDNEXTLEARNINGACTIVITIES_MODULEID_2 =
			"learningActivity.moduleId = ? AND ";

	private static final String
		_FINDER_COLUMN_MODULEIDNEXTLEARNINGACTIVITIES_PRIORITY_2 =
			"learningActivity.priority > ?";

	private FinderPath
		_finderPathWithPaginationFindByModuleIdPreviousLearningActivities;
	private FinderPath
		_finderPathWithPaginationCountByModuleIdPreviousLearningActivities;

	/**
	 * Returns all the learning activities where groupId = &#63; and moduleId = &#63; and priority &lt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 * @return the matching learning activities
	 */
	@Override
	public List<LearningActivity> findByModuleIdPreviousLearningActivities(
		long groupId, long moduleId, long priority) {

		return findByModuleIdPreviousLearningActivities(
			groupId, moduleId, priority, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the learning activities where groupId = &#63; and moduleId = &#63; and priority &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @return the range of matching learning activities
	 */
	@Override
	public List<LearningActivity> findByModuleIdPreviousLearningActivities(
		long groupId, long moduleId, long priority, int start, int end) {

		return findByModuleIdPreviousLearningActivities(
			groupId, moduleId, priority, start, end, null);
	}

	/**
	 * Returns an ordered range of all the learning activities where groupId = &#63; and moduleId = &#63; and priority &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching learning activities
	 */
	@Override
	public List<LearningActivity> findByModuleIdPreviousLearningActivities(
		long groupId, long moduleId, long priority, int start, int end,
		OrderByComparator<LearningActivity> orderByComparator) {

		return findByModuleIdPreviousLearningActivities(
			groupId, moduleId, priority, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the learning activities where groupId = &#63; and moduleId = &#63; and priority &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching learning activities
	 */
	@Override
	public List<LearningActivity> findByModuleIdPreviousLearningActivities(
		long groupId, long moduleId, long priority, int start, int end,
		OrderByComparator<LearningActivity> orderByComparator,
		boolean useFinderCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath =
			_finderPathWithPaginationFindByModuleIdPreviousLearningActivities;
		finderArgs = new Object[] {
			groupId, moduleId, priority, start, end, orderByComparator
		};

		List<LearningActivity> list = null;

		if (useFinderCache) {
			list = (List<LearningActivity>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LearningActivity learningActivity : list) {
					if ((groupId != learningActivity.getGroupId()) ||
						(moduleId != learningActivity.getModuleId()) ||
						(priority <= learningActivity.getPriority())) {

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

			query.append(_SQL_SELECT_LEARNINGACTIVITY_WHERE);

			query.append(
				_FINDER_COLUMN_MODULEIDPREVIOUSLEARNINGACTIVITIES_GROUPID_2);

			query.append(
				_FINDER_COLUMN_MODULEIDPREVIOUSLEARNINGACTIVITIES_MODULEID_2);

			query.append(
				_FINDER_COLUMN_MODULEIDPREVIOUSLEARNINGACTIVITIES_PRIORITY_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(LearningActivityModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(moduleId);

				qPos.add(priority);

				if (!pagination) {
					list = (List<LearningActivity>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LearningActivity>)QueryUtil.list(
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
	 * Returns the first learning activity in the ordered set where groupId = &#63; and moduleId = &#63; and priority &lt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity
	 * @throws NoSuchLearningActivityException if a matching learning activity could not be found
	 */
	@Override
	public LearningActivity findByModuleIdPreviousLearningActivities_First(
			long groupId, long moduleId, long priority,
			OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException {

		LearningActivity learningActivity =
			fetchByModuleIdPreviousLearningActivities_First(
				groupId, moduleId, priority, orderByComparator);

		if (learningActivity != null) {
			return learningActivity;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", moduleId=");
		msg.append(moduleId);

		msg.append(", priority<");
		msg.append(priority);

		msg.append("}");

		throw new NoSuchLearningActivityException(msg.toString());
	}

	/**
	 * Returns the first learning activity in the ordered set where groupId = &#63; and moduleId = &#63; and priority &lt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity, or <code>null</code> if a matching learning activity could not be found
	 */
	@Override
	public LearningActivity fetchByModuleIdPreviousLearningActivities_First(
		long groupId, long moduleId, long priority,
		OrderByComparator<LearningActivity> orderByComparator) {

		List<LearningActivity> list = findByModuleIdPreviousLearningActivities(
			groupId, moduleId, priority, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last learning activity in the ordered set where groupId = &#63; and moduleId = &#63; and priority &lt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity
	 * @throws NoSuchLearningActivityException if a matching learning activity could not be found
	 */
	@Override
	public LearningActivity findByModuleIdPreviousLearningActivities_Last(
			long groupId, long moduleId, long priority,
			OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException {

		LearningActivity learningActivity =
			fetchByModuleIdPreviousLearningActivities_Last(
				groupId, moduleId, priority, orderByComparator);

		if (learningActivity != null) {
			return learningActivity;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", moduleId=");
		msg.append(moduleId);

		msg.append(", priority<");
		msg.append(priority);

		msg.append("}");

		throw new NoSuchLearningActivityException(msg.toString());
	}

	/**
	 * Returns the last learning activity in the ordered set where groupId = &#63; and moduleId = &#63; and priority &lt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity, or <code>null</code> if a matching learning activity could not be found
	 */
	@Override
	public LearningActivity fetchByModuleIdPreviousLearningActivities_Last(
		long groupId, long moduleId, long priority,
		OrderByComparator<LearningActivity> orderByComparator) {

		int count = countByModuleIdPreviousLearningActivities(
			groupId, moduleId, priority);

		if (count == 0) {
			return null;
		}

		List<LearningActivity> list = findByModuleIdPreviousLearningActivities(
			groupId, moduleId, priority, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the learning activities before and after the current learning activity in the ordered set where groupId = &#63; and moduleId = &#63; and priority &lt; &#63;.
	 *
	 * @param actId the primary key of the current learning activity
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next learning activity
	 * @throws NoSuchLearningActivityException if a learning activity with the primary key could not be found
	 */
	@Override
	public LearningActivity[]
			findByModuleIdPreviousLearningActivities_PrevAndNext(
				long actId, long groupId, long moduleId, long priority,
				OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException {

		LearningActivity learningActivity = findByPrimaryKey(actId);

		Session session = null;

		try {
			session = openSession();

			LearningActivity[] array = new LearningActivityImpl[3];

			array[0] = getByModuleIdPreviousLearningActivities_PrevAndNext(
				session, learningActivity, groupId, moduleId, priority,
				orderByComparator, true);

			array[1] = learningActivity;

			array[2] = getByModuleIdPreviousLearningActivities_PrevAndNext(
				session, learningActivity, groupId, moduleId, priority,
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

	protected LearningActivity
		getByModuleIdPreviousLearningActivities_PrevAndNext(
			Session session, LearningActivity learningActivity, long groupId,
			long moduleId, long priority,
			OrderByComparator<LearningActivity> orderByComparator,
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

		query.append(_SQL_SELECT_LEARNINGACTIVITY_WHERE);

		query.append(
			_FINDER_COLUMN_MODULEIDPREVIOUSLEARNINGACTIVITIES_GROUPID_2);

		query.append(
			_FINDER_COLUMN_MODULEIDPREVIOUSLEARNINGACTIVITIES_MODULEID_2);

		query.append(
			_FINDER_COLUMN_MODULEIDPREVIOUSLEARNINGACTIVITIES_PRIORITY_2);

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
			query.append(LearningActivityModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(moduleId);

		qPos.add(priority);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						learningActivity)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<LearningActivity> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the learning activities that the user has permission to view where groupId = &#63; and moduleId = &#63; and priority &lt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 * @return the matching learning activities that the user has permission to view
	 */
	@Override
	public List<LearningActivity>
		filterFindByModuleIdPreviousLearningActivities(
			long groupId, long moduleId, long priority) {

		return filterFindByModuleIdPreviousLearningActivities(
			groupId, moduleId, priority, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the learning activities that the user has permission to view where groupId = &#63; and moduleId = &#63; and priority &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @return the range of matching learning activities that the user has permission to view
	 */
	@Override
	public List<LearningActivity>
		filterFindByModuleIdPreviousLearningActivities(
			long groupId, long moduleId, long priority, int start, int end) {

		return filterFindByModuleIdPreviousLearningActivities(
			groupId, moduleId, priority, start, end, null);
	}

	/**
	 * Returns an ordered range of all the learning activities that the user has permissions to view where groupId = &#63; and moduleId = &#63; and priority &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching learning activities that the user has permission to view
	 */
	@Override
	public List<LearningActivity>
		filterFindByModuleIdPreviousLearningActivities(
			long groupId, long moduleId, long priority, int start, int end,
			OrderByComparator<LearningActivity> orderByComparator) {

		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByModuleIdPreviousLearningActivities(
				groupId, moduleId, priority, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				5 + (orderByComparator.getOrderByFields().length * 2));
		}
		else {
			query = new StringBundler(6);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_LEARNINGACTIVITY_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_LEARNINGACTIVITY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(
			_FINDER_COLUMN_MODULEIDPREVIOUSLEARNINGACTIVITIES_GROUPID_2);

		query.append(
			_FINDER_COLUMN_MODULEIDPREVIOUSLEARNINGACTIVITIES_MODULEID_2);

		query.append(
			_FINDER_COLUMN_MODULEIDPREVIOUSLEARNINGACTIVITIES_PRIORITY_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(
				_FILTER_SQL_SELECT_LEARNINGACTIVITY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator, true);
			}
			else {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_TABLE, orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(LearningActivityModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(LearningActivityModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), LearningActivity.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, LearningActivityImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, LearningActivityImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(moduleId);

			qPos.add(priority);

			return (List<LearningActivity>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the learning activities before and after the current learning activity in the ordered set of learning activities that the user has permission to view where groupId = &#63; and moduleId = &#63; and priority &lt; &#63;.
	 *
	 * @param actId the primary key of the current learning activity
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next learning activity
	 * @throws NoSuchLearningActivityException if a learning activity with the primary key could not be found
	 */
	@Override
	public LearningActivity[]
			filterFindByModuleIdPreviousLearningActivities_PrevAndNext(
				long actId, long groupId, long moduleId, long priority,
				OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException {

		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByModuleIdPreviousLearningActivities_PrevAndNext(
				actId, groupId, moduleId, priority, orderByComparator);
		}

		LearningActivity learningActivity = findByPrimaryKey(actId);

		Session session = null;

		try {
			session = openSession();

			LearningActivity[] array = new LearningActivityImpl[3];

			array[0] =
				filterGetByModuleIdPreviousLearningActivities_PrevAndNext(
					session, learningActivity, groupId, moduleId, priority,
					orderByComparator, true);

			array[1] = learningActivity;

			array[2] =
				filterGetByModuleIdPreviousLearningActivities_PrevAndNext(
					session, learningActivity, groupId, moduleId, priority,
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

	protected LearningActivity
		filterGetByModuleIdPreviousLearningActivities_PrevAndNext(
			Session session, LearningActivity learningActivity, long groupId,
			long moduleId, long priority,
			OrderByComparator<LearningActivity> orderByComparator,
			boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				7 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(6);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_LEARNINGACTIVITY_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_LEARNINGACTIVITY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(
			_FINDER_COLUMN_MODULEIDPREVIOUSLEARNINGACTIVITIES_GROUPID_2);

		query.append(
			_FINDER_COLUMN_MODULEIDPREVIOUSLEARNINGACTIVITIES_MODULEID_2);

		query.append(
			_FINDER_COLUMN_MODULEIDPREVIOUSLEARNINGACTIVITIES_PRIORITY_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(
				_FILTER_SQL_SELECT_LEARNINGACTIVITY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByConditionFields[i],
							true));
				}
				else {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByConditionFields[i],
							true));
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByFields[i], true));
				}
				else {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByFields[i], true));
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(LearningActivityModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(LearningActivityModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), LearningActivity.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, LearningActivityImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, LearningActivityImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(moduleId);

		qPos.add(priority);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						learningActivity)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<LearningActivity> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the learning activities where groupId = &#63; and moduleId = &#63; and priority &lt; &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 */
	@Override
	public void removeByModuleIdPreviousLearningActivities(
		long groupId, long moduleId, long priority) {

		for (LearningActivity learningActivity :
				findByModuleIdPreviousLearningActivities(
					groupId, moduleId, priority, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(learningActivity);
		}
	}

	/**
	 * Returns the number of learning activities where groupId = &#63; and moduleId = &#63; and priority &lt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 * @return the number of matching learning activities
	 */
	@Override
	public int countByModuleIdPreviousLearningActivities(
		long groupId, long moduleId, long priority) {

		FinderPath finderPath =
			_finderPathWithPaginationCountByModuleIdPreviousLearningActivities;

		Object[] finderArgs = new Object[] {groupId, moduleId, priority};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_LEARNINGACTIVITY_WHERE);

			query.append(
				_FINDER_COLUMN_MODULEIDPREVIOUSLEARNINGACTIVITIES_GROUPID_2);

			query.append(
				_FINDER_COLUMN_MODULEIDPREVIOUSLEARNINGACTIVITIES_MODULEID_2);

			query.append(
				_FINDER_COLUMN_MODULEIDPREVIOUSLEARNINGACTIVITIES_PRIORITY_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(moduleId);

				qPos.add(priority);

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

	/**
	 * Returns the number of learning activities that the user has permission to view where groupId = &#63; and moduleId = &#63; and priority &lt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param priority the priority
	 * @return the number of matching learning activities that the user has permission to view
	 */
	@Override
	public int filterCountByModuleIdPreviousLearningActivities(
		long groupId, long moduleId, long priority) {

		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByModuleIdPreviousLearningActivities(
				groupId, moduleId, priority);
		}

		StringBundler query = new StringBundler(4);

		query.append(_FILTER_SQL_COUNT_LEARNINGACTIVITY_WHERE);

		query.append(
			_FINDER_COLUMN_MODULEIDPREVIOUSLEARNINGACTIVITIES_GROUPID_2);

		query.append(
			_FINDER_COLUMN_MODULEIDPREVIOUSLEARNINGACTIVITIES_MODULEID_2);

		query.append(
			_FINDER_COLUMN_MODULEIDPREVIOUSLEARNINGACTIVITIES_PRIORITY_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), LearningActivity.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(
				COUNT_COLUMN_NAME, com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(moduleId);

			qPos.add(priority);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String
		_FINDER_COLUMN_MODULEIDPREVIOUSLEARNINGACTIVITIES_GROUPID_2 =
			"learningActivity.groupId = ? AND ";

	private static final String
		_FINDER_COLUMN_MODULEIDPREVIOUSLEARNINGACTIVITIES_MODULEID_2 =
			"learningActivity.moduleId = ? AND ";

	private static final String
		_FINDER_COLUMN_MODULEIDPREVIOUSLEARNINGACTIVITIES_PRIORITY_2 =
			"learningActivity.priority < ?";

	private FinderPath _finderPathWithPaginationFindByModuleIdNotTypeId;
	private FinderPath _finderPathWithPaginationCountByModuleIdNotTypeId;

	/**
	 * Returns all the learning activities where moduleId = &#63; and typeId &ne; &#63;.
	 *
	 * @param moduleId the module ID
	 * @param typeId the type ID
	 * @return the matching learning activities
	 */
	@Override
	public List<LearningActivity> findByModuleIdNotTypeId(
		long moduleId, long typeId) {

		return findByModuleIdNotTypeId(
			moduleId, typeId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the learning activities where moduleId = &#63; and typeId &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param moduleId the module ID
	 * @param typeId the type ID
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @return the range of matching learning activities
	 */
	@Override
	public List<LearningActivity> findByModuleIdNotTypeId(
		long moduleId, long typeId, int start, int end) {

		return findByModuleIdNotTypeId(moduleId, typeId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the learning activities where moduleId = &#63; and typeId &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param moduleId the module ID
	 * @param typeId the type ID
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching learning activities
	 */
	@Override
	public List<LearningActivity> findByModuleIdNotTypeId(
		long moduleId, long typeId, int start, int end,
		OrderByComparator<LearningActivity> orderByComparator) {

		return findByModuleIdNotTypeId(
			moduleId, typeId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the learning activities where moduleId = &#63; and typeId &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param moduleId the module ID
	 * @param typeId the type ID
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching learning activities
	 */
	@Override
	public List<LearningActivity> findByModuleIdNotTypeId(
		long moduleId, long typeId, int start, int end,
		OrderByComparator<LearningActivity> orderByComparator,
		boolean useFinderCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = _finderPathWithPaginationFindByModuleIdNotTypeId;
		finderArgs = new Object[] {
			moduleId, typeId, start, end, orderByComparator
		};

		List<LearningActivity> list = null;

		if (useFinderCache) {
			list = (List<LearningActivity>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LearningActivity learningActivity : list) {
					if ((moduleId != learningActivity.getModuleId()) ||
						(typeId == learningActivity.getTypeId())) {

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

			query.append(_SQL_SELECT_LEARNINGACTIVITY_WHERE);

			query.append(_FINDER_COLUMN_MODULEIDNOTTYPEID_MODULEID_2);

			query.append(_FINDER_COLUMN_MODULEIDNOTTYPEID_TYPEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(LearningActivityModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(moduleId);

				qPos.add(typeId);

				if (!pagination) {
					list = (List<LearningActivity>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LearningActivity>)QueryUtil.list(
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
	 * Returns the first learning activity in the ordered set where moduleId = &#63; and typeId &ne; &#63;.
	 *
	 * @param moduleId the module ID
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity
	 * @throws NoSuchLearningActivityException if a matching learning activity could not be found
	 */
	@Override
	public LearningActivity findByModuleIdNotTypeId_First(
			long moduleId, long typeId,
			OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException {

		LearningActivity learningActivity = fetchByModuleIdNotTypeId_First(
			moduleId, typeId, orderByComparator);

		if (learningActivity != null) {
			return learningActivity;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("moduleId=");
		msg.append(moduleId);

		msg.append(", typeId!=");
		msg.append(typeId);

		msg.append("}");

		throw new NoSuchLearningActivityException(msg.toString());
	}

	/**
	 * Returns the first learning activity in the ordered set where moduleId = &#63; and typeId &ne; &#63;.
	 *
	 * @param moduleId the module ID
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity, or <code>null</code> if a matching learning activity could not be found
	 */
	@Override
	public LearningActivity fetchByModuleIdNotTypeId_First(
		long moduleId, long typeId,
		OrderByComparator<LearningActivity> orderByComparator) {

		List<LearningActivity> list = findByModuleIdNotTypeId(
			moduleId, typeId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last learning activity in the ordered set where moduleId = &#63; and typeId &ne; &#63;.
	 *
	 * @param moduleId the module ID
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity
	 * @throws NoSuchLearningActivityException if a matching learning activity could not be found
	 */
	@Override
	public LearningActivity findByModuleIdNotTypeId_Last(
			long moduleId, long typeId,
			OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException {

		LearningActivity learningActivity = fetchByModuleIdNotTypeId_Last(
			moduleId, typeId, orderByComparator);

		if (learningActivity != null) {
			return learningActivity;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("moduleId=");
		msg.append(moduleId);

		msg.append(", typeId!=");
		msg.append(typeId);

		msg.append("}");

		throw new NoSuchLearningActivityException(msg.toString());
	}

	/**
	 * Returns the last learning activity in the ordered set where moduleId = &#63; and typeId &ne; &#63;.
	 *
	 * @param moduleId the module ID
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity, or <code>null</code> if a matching learning activity could not be found
	 */
	@Override
	public LearningActivity fetchByModuleIdNotTypeId_Last(
		long moduleId, long typeId,
		OrderByComparator<LearningActivity> orderByComparator) {

		int count = countByModuleIdNotTypeId(moduleId, typeId);

		if (count == 0) {
			return null;
		}

		List<LearningActivity> list = findByModuleIdNotTypeId(
			moduleId, typeId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the learning activities before and after the current learning activity in the ordered set where moduleId = &#63; and typeId &ne; &#63;.
	 *
	 * @param actId the primary key of the current learning activity
	 * @param moduleId the module ID
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next learning activity
	 * @throws NoSuchLearningActivityException if a learning activity with the primary key could not be found
	 */
	@Override
	public LearningActivity[] findByModuleIdNotTypeId_PrevAndNext(
			long actId, long moduleId, long typeId,
			OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException {

		LearningActivity learningActivity = findByPrimaryKey(actId);

		Session session = null;

		try {
			session = openSession();

			LearningActivity[] array = new LearningActivityImpl[3];

			array[0] = getByModuleIdNotTypeId_PrevAndNext(
				session, learningActivity, moduleId, typeId, orderByComparator,
				true);

			array[1] = learningActivity;

			array[2] = getByModuleIdNotTypeId_PrevAndNext(
				session, learningActivity, moduleId, typeId, orderByComparator,
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

	protected LearningActivity getByModuleIdNotTypeId_PrevAndNext(
		Session session, LearningActivity learningActivity, long moduleId,
		long typeId, OrderByComparator<LearningActivity> orderByComparator,
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

		query.append(_SQL_SELECT_LEARNINGACTIVITY_WHERE);

		query.append(_FINDER_COLUMN_MODULEIDNOTTYPEID_MODULEID_2);

		query.append(_FINDER_COLUMN_MODULEIDNOTTYPEID_TYPEID_2);

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
			query.append(LearningActivityModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(moduleId);

		qPos.add(typeId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						learningActivity)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<LearningActivity> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the learning activities where moduleId = &#63; and typeId &ne; &#63; from the database.
	 *
	 * @param moduleId the module ID
	 * @param typeId the type ID
	 */
	@Override
	public void removeByModuleIdNotTypeId(long moduleId, long typeId) {
		for (LearningActivity learningActivity :
				findByModuleIdNotTypeId(
					moduleId, typeId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(learningActivity);
		}
	}

	/**
	 * Returns the number of learning activities where moduleId = &#63; and typeId &ne; &#63;.
	 *
	 * @param moduleId the module ID
	 * @param typeId the type ID
	 * @return the number of matching learning activities
	 */
	@Override
	public int countByModuleIdNotTypeId(long moduleId, long typeId) {
		FinderPath finderPath =
			_finderPathWithPaginationCountByModuleIdNotTypeId;

		Object[] finderArgs = new Object[] {moduleId, typeId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LEARNINGACTIVITY_WHERE);

			query.append(_FINDER_COLUMN_MODULEIDNOTTYPEID_MODULEID_2);

			query.append(_FINDER_COLUMN_MODULEIDNOTTYPEID_TYPEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(moduleId);

				qPos.add(typeId);

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

	private static final String _FINDER_COLUMN_MODULEIDNOTTYPEID_MODULEID_2 =
		"learningActivity.moduleId = ? AND ";

	private static final String _FINDER_COLUMN_MODULEIDNOTTYPEID_TYPEID_2 =
		"learningActivity.typeId != ?";

	private FinderPath _finderPathWithPaginationFindByGroupIdModuleId;
	private FinderPath _finderPathWithoutPaginationFindByGroupIdModuleId;
	private FinderPath _finderPathCountByGroupIdModuleId;

	/**
	 * Returns all the learning activities where groupId = &#63; and moduleId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @return the matching learning activities
	 */
	@Override
	public List<LearningActivity> findByGroupIdModuleId(
		long groupId, long moduleId) {

		return findByGroupIdModuleId(
			groupId, moduleId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the learning activities where groupId = &#63; and moduleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @return the range of matching learning activities
	 */
	@Override
	public List<LearningActivity> findByGroupIdModuleId(
		long groupId, long moduleId, int start, int end) {

		return findByGroupIdModuleId(groupId, moduleId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the learning activities where groupId = &#63; and moduleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching learning activities
	 */
	@Override
	public List<LearningActivity> findByGroupIdModuleId(
		long groupId, long moduleId, int start, int end,
		OrderByComparator<LearningActivity> orderByComparator) {

		return findByGroupIdModuleId(
			groupId, moduleId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the learning activities where groupId = &#63; and moduleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching learning activities
	 */
	@Override
	public List<LearningActivity> findByGroupIdModuleId(
		long groupId, long moduleId, int start, int end,
		OrderByComparator<LearningActivity> orderByComparator,
		boolean useFinderCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByGroupIdModuleId;
				finderArgs = new Object[] {groupId, moduleId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByGroupIdModuleId;
			finderArgs = new Object[] {
				groupId, moduleId, start, end, orderByComparator
			};
		}

		List<LearningActivity> list = null;

		if (useFinderCache) {
			list = (List<LearningActivity>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LearningActivity learningActivity : list) {
					if ((groupId != learningActivity.getGroupId()) ||
						(moduleId != learningActivity.getModuleId())) {

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

			query.append(_SQL_SELECT_LEARNINGACTIVITY_WHERE);

			query.append(_FINDER_COLUMN_GROUPIDMODULEID_GROUPID_2);

			query.append(_FINDER_COLUMN_GROUPIDMODULEID_MODULEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(LearningActivityModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(moduleId);

				if (!pagination) {
					list = (List<LearningActivity>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LearningActivity>)QueryUtil.list(
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
	 * Returns the first learning activity in the ordered set where groupId = &#63; and moduleId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity
	 * @throws NoSuchLearningActivityException if a matching learning activity could not be found
	 */
	@Override
	public LearningActivity findByGroupIdModuleId_First(
			long groupId, long moduleId,
			OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException {

		LearningActivity learningActivity = fetchByGroupIdModuleId_First(
			groupId, moduleId, orderByComparator);

		if (learningActivity != null) {
			return learningActivity;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", moduleId=");
		msg.append(moduleId);

		msg.append("}");

		throw new NoSuchLearningActivityException(msg.toString());
	}

	/**
	 * Returns the first learning activity in the ordered set where groupId = &#63; and moduleId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching learning activity, or <code>null</code> if a matching learning activity could not be found
	 */
	@Override
	public LearningActivity fetchByGroupIdModuleId_First(
		long groupId, long moduleId,
		OrderByComparator<LearningActivity> orderByComparator) {

		List<LearningActivity> list = findByGroupIdModuleId(
			groupId, moduleId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last learning activity in the ordered set where groupId = &#63; and moduleId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity
	 * @throws NoSuchLearningActivityException if a matching learning activity could not be found
	 */
	@Override
	public LearningActivity findByGroupIdModuleId_Last(
			long groupId, long moduleId,
			OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException {

		LearningActivity learningActivity = fetchByGroupIdModuleId_Last(
			groupId, moduleId, orderByComparator);

		if (learningActivity != null) {
			return learningActivity;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", moduleId=");
		msg.append(moduleId);

		msg.append("}");

		throw new NoSuchLearningActivityException(msg.toString());
	}

	/**
	 * Returns the last learning activity in the ordered set where groupId = &#63; and moduleId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching learning activity, or <code>null</code> if a matching learning activity could not be found
	 */
	@Override
	public LearningActivity fetchByGroupIdModuleId_Last(
		long groupId, long moduleId,
		OrderByComparator<LearningActivity> orderByComparator) {

		int count = countByGroupIdModuleId(groupId, moduleId);

		if (count == 0) {
			return null;
		}

		List<LearningActivity> list = findByGroupIdModuleId(
			groupId, moduleId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the learning activities before and after the current learning activity in the ordered set where groupId = &#63; and moduleId = &#63;.
	 *
	 * @param actId the primary key of the current learning activity
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next learning activity
	 * @throws NoSuchLearningActivityException if a learning activity with the primary key could not be found
	 */
	@Override
	public LearningActivity[] findByGroupIdModuleId_PrevAndNext(
			long actId, long groupId, long moduleId,
			OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException {

		LearningActivity learningActivity = findByPrimaryKey(actId);

		Session session = null;

		try {
			session = openSession();

			LearningActivity[] array = new LearningActivityImpl[3];

			array[0] = getByGroupIdModuleId_PrevAndNext(
				session, learningActivity, groupId, moduleId, orderByComparator,
				true);

			array[1] = learningActivity;

			array[2] = getByGroupIdModuleId_PrevAndNext(
				session, learningActivity, groupId, moduleId, orderByComparator,
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

	protected LearningActivity getByGroupIdModuleId_PrevAndNext(
		Session session, LearningActivity learningActivity, long groupId,
		long moduleId, OrderByComparator<LearningActivity> orderByComparator,
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

		query.append(_SQL_SELECT_LEARNINGACTIVITY_WHERE);

		query.append(_FINDER_COLUMN_GROUPIDMODULEID_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDMODULEID_MODULEID_2);

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
			query.append(LearningActivityModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(moduleId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						learningActivity)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<LearningActivity> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the learning activities that the user has permission to view where groupId = &#63; and moduleId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @return the matching learning activities that the user has permission to view
	 */
	@Override
	public List<LearningActivity> filterFindByGroupIdModuleId(
		long groupId, long moduleId) {

		return filterFindByGroupIdModuleId(
			groupId, moduleId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the learning activities that the user has permission to view where groupId = &#63; and moduleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @return the range of matching learning activities that the user has permission to view
	 */
	@Override
	public List<LearningActivity> filterFindByGroupIdModuleId(
		long groupId, long moduleId, int start, int end) {

		return filterFindByGroupIdModuleId(groupId, moduleId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the learning activities that the user has permissions to view where groupId = &#63; and moduleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching learning activities that the user has permission to view
	 */
	@Override
	public List<LearningActivity> filterFindByGroupIdModuleId(
		long groupId, long moduleId, int start, int end,
		OrderByComparator<LearningActivity> orderByComparator) {

		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupIdModuleId(
				groupId, moduleId, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByFields().length * 2));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_LEARNINGACTIVITY_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_LEARNINGACTIVITY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPIDMODULEID_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDMODULEID_MODULEID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(
				_FILTER_SQL_SELECT_LEARNINGACTIVITY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator, true);
			}
			else {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_TABLE, orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(LearningActivityModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(LearningActivityModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), LearningActivity.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, LearningActivityImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, LearningActivityImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(moduleId);

			return (List<LearningActivity>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the learning activities before and after the current learning activity in the ordered set of learning activities that the user has permission to view where groupId = &#63; and moduleId = &#63;.
	 *
	 * @param actId the primary key of the current learning activity
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next learning activity
	 * @throws NoSuchLearningActivityException if a learning activity with the primary key could not be found
	 */
	@Override
	public LearningActivity[] filterFindByGroupIdModuleId_PrevAndNext(
			long actId, long groupId, long moduleId,
			OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException {

		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupIdModuleId_PrevAndNext(
				actId, groupId, moduleId, orderByComparator);
		}

		LearningActivity learningActivity = findByPrimaryKey(actId);

		Session session = null;

		try {
			session = openSession();

			LearningActivity[] array = new LearningActivityImpl[3];

			array[0] = filterGetByGroupIdModuleId_PrevAndNext(
				session, learningActivity, groupId, moduleId, orderByComparator,
				true);

			array[1] = learningActivity;

			array[2] = filterGetByGroupIdModuleId_PrevAndNext(
				session, learningActivity, groupId, moduleId, orderByComparator,
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

	protected LearningActivity filterGetByGroupIdModuleId_PrevAndNext(
		Session session, LearningActivity learningActivity, long groupId,
		long moduleId, OrderByComparator<LearningActivity> orderByComparator,
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

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_LEARNINGACTIVITY_WHERE);
		}
		else {
			query.append(
				_FILTER_SQL_SELECT_LEARNINGACTIVITY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPIDMODULEID_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDMODULEID_MODULEID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(
				_FILTER_SQL_SELECT_LEARNINGACTIVITY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByConditionFields[i],
							true));
				}
				else {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByConditionFields[i],
							true));
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByFields[i], true));
				}
				else {
					query.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByFields[i], true));
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(LearningActivityModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(LearningActivityModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), LearningActivity.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, LearningActivityImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, LearningActivityImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(moduleId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						learningActivity)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<LearningActivity> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the learning activities where groupId = &#63; and moduleId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 */
	@Override
	public void removeByGroupIdModuleId(long groupId, long moduleId) {
		for (LearningActivity learningActivity :
				findByGroupIdModuleId(
					groupId, moduleId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(learningActivity);
		}
	}

	/**
	 * Returns the number of learning activities where groupId = &#63; and moduleId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @return the number of matching learning activities
	 */
	@Override
	public int countByGroupIdModuleId(long groupId, long moduleId) {
		FinderPath finderPath = _finderPathCountByGroupIdModuleId;

		Object[] finderArgs = new Object[] {groupId, moduleId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LEARNINGACTIVITY_WHERE);

			query.append(_FINDER_COLUMN_GROUPIDMODULEID_GROUPID_2);

			query.append(_FINDER_COLUMN_GROUPIDMODULEID_MODULEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(moduleId);

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

	/**
	 * Returns the number of learning activities that the user has permission to view where groupId = &#63; and moduleId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param moduleId the module ID
	 * @return the number of matching learning activities that the user has permission to view
	 */
	@Override
	public int filterCountByGroupIdModuleId(long groupId, long moduleId) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByGroupIdModuleId(groupId, moduleId);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_LEARNINGACTIVITY_WHERE);

		query.append(_FINDER_COLUMN_GROUPIDMODULEID_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDMODULEID_MODULEID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			query.toString(), LearningActivity.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(
				COUNT_COLUMN_NAME, com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(moduleId);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_GROUPIDMODULEID_GROUPID_2 =
		"learningActivity.groupId = ? AND ";

	private static final String _FINDER_COLUMN_GROUPIDMODULEID_MODULEID_2 =
		"learningActivity.moduleId = ?";

	public LearningActivityPersistenceImpl() {
		setModelClass(LearningActivity.class);

		setModelImplClass(LearningActivityImpl.class);
		setModelPKClass(long.class);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");

		setDBColumnNames(dbColumnNames);
	}

	/**
	 * Caches the learning activity in the entity cache if it is enabled.
	 *
	 * @param learningActivity the learning activity
	 */
	@Override
	public void cacheResult(LearningActivity learningActivity) {
		entityCache.putResult(
			entityCacheEnabled, LearningActivityImpl.class,
			learningActivity.getPrimaryKey(), learningActivity);

		finderCache.putResult(
			_finderPathFetchByUUID_G,
			new Object[] {
				learningActivity.getUuid(), learningActivity.getGroupId()
			},
			learningActivity);

		learningActivity.resetOriginalValues();
	}

	/**
	 * Caches the learning activities in the entity cache if it is enabled.
	 *
	 * @param learningActivities the learning activities
	 */
	@Override
	public void cacheResult(List<LearningActivity> learningActivities) {
		for (LearningActivity learningActivity : learningActivities) {
			if (entityCache.getResult(
					entityCacheEnabled, LearningActivityImpl.class,
					learningActivity.getPrimaryKey()) == null) {

				cacheResult(learningActivity);
			}
			else {
				learningActivity.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all learning activities.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(LearningActivityImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the learning activity.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(LearningActivity learningActivity) {
		entityCache.removeResult(
			entityCacheEnabled, LearningActivityImpl.class,
			learningActivity.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(
			(LearningActivityModelImpl)learningActivity, true);
	}

	@Override
	public void clearCache(List<LearningActivity> learningActivities) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (LearningActivity learningActivity : learningActivities) {
			entityCache.removeResult(
				entityCacheEnabled, LearningActivityImpl.class,
				learningActivity.getPrimaryKey());

			clearUniqueFindersCache(
				(LearningActivityModelImpl)learningActivity, true);
		}
	}

	protected void cacheUniqueFindersCache(
		LearningActivityModelImpl learningActivityModelImpl) {

		Object[] args = new Object[] {
			learningActivityModelImpl.getUuid(),
			learningActivityModelImpl.getGroupId()
		};

		finderCache.putResult(
			_finderPathCountByUUID_G, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByUUID_G, args, learningActivityModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		LearningActivityModelImpl learningActivityModelImpl,
		boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				learningActivityModelImpl.getUuid(),
				learningActivityModelImpl.getGroupId()
			};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
		}

		if ((learningActivityModelImpl.getColumnBitmask() &
			 _finderPathFetchByUUID_G.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				learningActivityModelImpl.getOriginalUuid(),
				learningActivityModelImpl.getOriginalGroupId()
			};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
		}
	}

	/**
	 * Creates a new learning activity with the primary key. Does not add the learning activity to the database.
	 *
	 * @param actId the primary key for the new learning activity
	 * @return the new learning activity
	 */
	@Override
	public LearningActivity create(long actId) {
		LearningActivity learningActivity = new LearningActivityImpl();

		learningActivity.setNew(true);
		learningActivity.setPrimaryKey(actId);

		String uuid = PortalUUIDUtil.generate();

		learningActivity.setUuid(uuid);

		learningActivity.setCompanyId(CompanyThreadLocal.getCompanyId());

		return learningActivity;
	}

	/**
	 * Removes the learning activity with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param actId the primary key of the learning activity
	 * @return the learning activity that was removed
	 * @throws NoSuchLearningActivityException if a learning activity with the primary key could not be found
	 */
	@Override
	public LearningActivity remove(long actId)
		throws NoSuchLearningActivityException {

		return remove((Serializable)actId);
	}

	/**
	 * Removes the learning activity with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the learning activity
	 * @return the learning activity that was removed
	 * @throws NoSuchLearningActivityException if a learning activity with the primary key could not be found
	 */
	@Override
	public LearningActivity remove(Serializable primaryKey)
		throws NoSuchLearningActivityException {

		Session session = null;

		try {
			session = openSession();

			LearningActivity learningActivity = (LearningActivity)session.get(
				LearningActivityImpl.class, primaryKey);

			if (learningActivity == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLearningActivityException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(learningActivity);
		}
		catch (NoSuchLearningActivityException nsee) {
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
	protected LearningActivity removeImpl(LearningActivity learningActivity) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(learningActivity)) {
				learningActivity = (LearningActivity)session.get(
					LearningActivityImpl.class,
					learningActivity.getPrimaryKeyObj());
			}

			if (learningActivity != null) {
				session.delete(learningActivity);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (learningActivity != null) {
			clearCache(learningActivity);
		}

		return learningActivity;
	}

	@Override
	public LearningActivity updateImpl(LearningActivity learningActivity) {
		boolean isNew = learningActivity.isNew();

		if (!(learningActivity instanceof LearningActivityModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(learningActivity.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					learningActivity);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in learningActivity proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom LearningActivity implementation " +
					learningActivity.getClass());
		}

		LearningActivityModelImpl learningActivityModelImpl =
			(LearningActivityModelImpl)learningActivity;

		if (Validator.isNull(learningActivity.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			learningActivity.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (learningActivity.getCreateDate() == null)) {
			if (serviceContext == null) {
				learningActivity.setCreateDate(now);
			}
			else {
				learningActivity.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!learningActivityModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				learningActivity.setModifiedDate(now);
			}
			else {
				learningActivity.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (learningActivity.isNew()) {
				session.save(learningActivity);

				learningActivity.setNew(false);
			}
			else {
				learningActivity = (LearningActivity)session.merge(
					learningActivity);
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
			Object[] args = new Object[] {learningActivityModelImpl.getUuid()};

			finderCache.removeResult(_finderPathCountByUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid, args);

			args = new Object[] {
				learningActivityModelImpl.getUuid(),
				learningActivityModelImpl.getCompanyId()
			};

			finderCache.removeResult(_finderPathCountByUuid_C, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid_C, args);

			args = new Object[] {learningActivityModelImpl.getCompanyId()};

			finderCache.removeResult(_finderPathCountByCompanyId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByCompanyId, args);

			args = new Object[] {learningActivityModelImpl.getGroupId()};

			finderCache.removeResult(_finderPathCountByGroupId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByGroupId, args);

			args = new Object[] {learningActivityModelImpl.getModuleId()};

			finderCache.removeResult(_finderPathCountByModuleId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByModuleId, args);

			args = new Object[] {
				learningActivityModelImpl.getModuleId(),
				learningActivityModelImpl.isRequired()
			};

			finderCache.removeResult(_finderPathCountByModuleIdRequired, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByModuleIdRequired, args);

			args = new Object[] {
				learningActivityModelImpl.getGroupId(),
				learningActivityModelImpl.isRequired()
			};

			finderCache.removeResult(_finderPathCountByGroupIdRequired, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByGroupIdRequired, args);

			args = new Object[] {learningActivityModelImpl.getTypeId()};

			finderCache.removeResult(_finderPathCountByTypeId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByTypeId, args);

			args = new Object[] {
				learningActivityModelImpl.getGroupId(),
				learningActivityModelImpl.getModuleId()
			};

			finderCache.removeResult(_finderPathCountByGroupIdModuleId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByGroupIdModuleId, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((learningActivityModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					learningActivityModelImpl.getOriginalUuid()
				};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);

				args = new Object[] {learningActivityModelImpl.getUuid()};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);
			}

			if ((learningActivityModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid_C.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					learningActivityModelImpl.getOriginalUuid(),
					learningActivityModelImpl.getOriginalCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);

				args = new Object[] {
					learningActivityModelImpl.getUuid(),
					learningActivityModelImpl.getCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);
			}

			if ((learningActivityModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByCompanyId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					learningActivityModelImpl.getOriginalCompanyId()
				};

				finderCache.removeResult(_finderPathCountByCompanyId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCompanyId, args);

				args = new Object[] {learningActivityModelImpl.getCompanyId()};

				finderCache.removeResult(_finderPathCountByCompanyId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCompanyId, args);
			}

			if ((learningActivityModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByGroupId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					learningActivityModelImpl.getOriginalGroupId()
				};

				finderCache.removeResult(_finderPathCountByGroupId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByGroupId, args);

				args = new Object[] {learningActivityModelImpl.getGroupId()};

				finderCache.removeResult(_finderPathCountByGroupId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByGroupId, args);
			}

			if ((learningActivityModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByModuleId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					learningActivityModelImpl.getOriginalModuleId()
				};

				finderCache.removeResult(_finderPathCountByModuleId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByModuleId, args);

				args = new Object[] {learningActivityModelImpl.getModuleId()};

				finderCache.removeResult(_finderPathCountByModuleId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByModuleId, args);
			}

			if ((learningActivityModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByModuleIdRequired.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					learningActivityModelImpl.getOriginalModuleId(),
					learningActivityModelImpl.getOriginalRequired()
				};

				finderCache.removeResult(
					_finderPathCountByModuleIdRequired, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByModuleIdRequired, args);

				args = new Object[] {
					learningActivityModelImpl.getModuleId(),
					learningActivityModelImpl.isRequired()
				};

				finderCache.removeResult(
					_finderPathCountByModuleIdRequired, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByModuleIdRequired, args);
			}

			if ((learningActivityModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByGroupIdRequired.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					learningActivityModelImpl.getOriginalGroupId(),
					learningActivityModelImpl.getOriginalRequired()
				};

				finderCache.removeResult(
					_finderPathCountByGroupIdRequired, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByGroupIdRequired, args);

				args = new Object[] {
					learningActivityModelImpl.getGroupId(),
					learningActivityModelImpl.isRequired()
				};

				finderCache.removeResult(
					_finderPathCountByGroupIdRequired, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByGroupIdRequired, args);
			}

			if ((learningActivityModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByTypeId.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					learningActivityModelImpl.getOriginalTypeId()
				};

				finderCache.removeResult(_finderPathCountByTypeId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByTypeId, args);

				args = new Object[] {learningActivityModelImpl.getTypeId()};

				finderCache.removeResult(_finderPathCountByTypeId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByTypeId, args);
			}

			if ((learningActivityModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByGroupIdModuleId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					learningActivityModelImpl.getOriginalGroupId(),
					learningActivityModelImpl.getOriginalModuleId()
				};

				finderCache.removeResult(
					_finderPathCountByGroupIdModuleId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByGroupIdModuleId, args);

				args = new Object[] {
					learningActivityModelImpl.getGroupId(),
					learningActivityModelImpl.getModuleId()
				};

				finderCache.removeResult(
					_finderPathCountByGroupIdModuleId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByGroupIdModuleId, args);
			}
		}

		entityCache.putResult(
			entityCacheEnabled, LearningActivityImpl.class,
			learningActivity.getPrimaryKey(), learningActivity, false);

		clearUniqueFindersCache(learningActivityModelImpl, false);
		cacheUniqueFindersCache(learningActivityModelImpl);

		learningActivity.resetOriginalValues();

		return learningActivity;
	}

	/**
	 * Returns the learning activity with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the learning activity
	 * @return the learning activity
	 * @throws NoSuchLearningActivityException if a learning activity with the primary key could not be found
	 */
	@Override
	public LearningActivity findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLearningActivityException {

		LearningActivity learningActivity = fetchByPrimaryKey(primaryKey);

		if (learningActivity == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLearningActivityException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return learningActivity;
	}

	/**
	 * Returns the learning activity with the primary key or throws a <code>NoSuchLearningActivityException</code> if it could not be found.
	 *
	 * @param actId the primary key of the learning activity
	 * @return the learning activity
	 * @throws NoSuchLearningActivityException if a learning activity with the primary key could not be found
	 */
	@Override
	public LearningActivity findByPrimaryKey(long actId)
		throws NoSuchLearningActivityException {

		return findByPrimaryKey((Serializable)actId);
	}

	/**
	 * Returns the learning activity with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param actId the primary key of the learning activity
	 * @return the learning activity, or <code>null</code> if a learning activity with the primary key could not be found
	 */
	@Override
	public LearningActivity fetchByPrimaryKey(long actId) {
		return fetchByPrimaryKey((Serializable)actId);
	}

	/**
	 * Returns all the learning activities.
	 *
	 * @return the learning activities
	 */
	@Override
	public List<LearningActivity> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the learning activities.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @return the range of learning activities
	 */
	@Override
	public List<LearningActivity> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the learning activities.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of learning activities
	 */
	@Override
	public List<LearningActivity> findAll(
		int start, int end,
		OrderByComparator<LearningActivity> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the learning activities.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of learning activities
	 */
	@Override
	public List<LearningActivity> findAll(
		int start, int end,
		OrderByComparator<LearningActivity> orderByComparator,
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

		List<LearningActivity> list = null;

		if (useFinderCache) {
			list = (List<LearningActivity>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_LEARNINGACTIVITY);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_LEARNINGACTIVITY;

				if (pagination) {
					sql = sql.concat(LearningActivityModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<LearningActivity>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<LearningActivity>)QueryUtil.list(
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
	 * Removes all the learning activities from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (LearningActivity learningActivity : findAll()) {
			remove(learningActivity);
		}
	}

	/**
	 * Returns the number of learning activities.
	 *
	 * @return the number of learning activities
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_LEARNINGACTIVITY);

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
		return "actId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_LEARNINGACTIVITY;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return LearningActivityModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the learning activity persistence.
	 */
	@Activate
	public void activate() {
		LearningActivityModelImpl.setEntityCacheEnabled(entityCacheEnabled);
		LearningActivityModelImpl.setFinderCacheEnabled(finderCacheEnabled);

		_finderPathWithPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LearningActivityImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LearningActivityImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LearningActivityImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LearningActivityImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()},
			LearningActivityModelImpl.UUID_COLUMN_BITMASK |
			LearningActivityModelImpl.MODULEID_COLUMN_BITMASK |
			LearningActivityModelImpl.PRIORITY_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()});

		_finderPathFetchByUUID_G = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LearningActivityImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()},
			LearningActivityModelImpl.UUID_COLUMN_BITMASK |
			LearningActivityModelImpl.GROUPID_COLUMN_BITMASK);

		_finderPathCountByUUID_G = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByUuid_C = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LearningActivityImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LearningActivityImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			LearningActivityModelImpl.UUID_COLUMN_BITMASK |
			LearningActivityModelImpl.COMPANYID_COLUMN_BITMASK |
			LearningActivityModelImpl.MODULEID_COLUMN_BITMASK |
			LearningActivityModelImpl.PRIORITY_COLUMN_BITMASK);

		_finderPathCountByUuid_C = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByCompanyId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LearningActivityImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByCompanyId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LearningActivityImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] {Long.class.getName()},
			LearningActivityModelImpl.COMPANYID_COLUMN_BITMASK |
			LearningActivityModelImpl.MODULEID_COLUMN_BITMASK |
			LearningActivityModelImpl.PRIORITY_COLUMN_BITMASK);

		_finderPathCountByCompanyId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByGroupId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LearningActivityImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByGroupId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LearningActivityImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] {Long.class.getName()},
			LearningActivityModelImpl.GROUPID_COLUMN_BITMASK |
			LearningActivityModelImpl.MODULEID_COLUMN_BITMASK |
			LearningActivityModelImpl.PRIORITY_COLUMN_BITMASK);

		_finderPathCountByGroupId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByModuleId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LearningActivityImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByModuleId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByModuleId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LearningActivityImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByModuleId",
			new String[] {Long.class.getName()},
			LearningActivityModelImpl.MODULEID_COLUMN_BITMASK |
			LearningActivityModelImpl.PRIORITY_COLUMN_BITMASK);

		_finderPathCountByModuleId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByModuleId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByModuleIdRequired = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LearningActivityImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByModuleIdRequired",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByModuleIdRequired = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LearningActivityImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByModuleIdRequired",
			new String[] {Long.class.getName(), Boolean.class.getName()},
			LearningActivityModelImpl.MODULEID_COLUMN_BITMASK |
			LearningActivityModelImpl.REQUIRED_COLUMN_BITMASK |
			LearningActivityModelImpl.PRIORITY_COLUMN_BITMASK);

		_finderPathCountByModuleIdRequired = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByModuleIdRequired",
			new String[] {Long.class.getName(), Boolean.class.getName()});

		_finderPathWithPaginationFindByGroupIdRequired = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LearningActivityImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupIdRequired",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByGroupIdRequired = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LearningActivityImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupIdRequired",
			new String[] {Long.class.getName(), Boolean.class.getName()},
			LearningActivityModelImpl.GROUPID_COLUMN_BITMASK |
			LearningActivityModelImpl.REQUIRED_COLUMN_BITMASK |
			LearningActivityModelImpl.MODULEID_COLUMN_BITMASK |
			LearningActivityModelImpl.PRIORITY_COLUMN_BITMASK);

		_finderPathCountByGroupIdRequired = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupIdRequired",
			new String[] {Long.class.getName(), Boolean.class.getName()});

		_finderPathWithPaginationFindByTypeId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LearningActivityImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByTypeId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByTypeId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LearningActivityImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByTypeId",
			new String[] {Long.class.getName()},
			LearningActivityModelImpl.TYPEID_COLUMN_BITMASK |
			LearningActivityModelImpl.MODULEID_COLUMN_BITMASK |
			LearningActivityModelImpl.PRIORITY_COLUMN_BITMASK);

		_finderPathCountByTypeId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTypeId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByModuleIdNextLearningActivities =
			new FinderPath(
				entityCacheEnabled, finderCacheEnabled,
				LearningActivityImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
				"findByModuleIdNextLearningActivities",
				new String[] {
					Long.class.getName(), Long.class.getName(),
					Long.class.getName(), Integer.class.getName(),
					Integer.class.getName(), OrderByComparator.class.getName()
				});

		_finderPathWithPaginationCountByModuleIdNextLearningActivities =
			new FinderPath(
				entityCacheEnabled, finderCacheEnabled, Long.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
				"countByModuleIdNextLearningActivities",
				new String[] {
					Long.class.getName(), Long.class.getName(),
					Long.class.getName()
				});

		_finderPathWithPaginationFindByModuleIdPreviousLearningActivities =
			new FinderPath(
				entityCacheEnabled, finderCacheEnabled,
				LearningActivityImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
				"findByModuleIdPreviousLearningActivities",
				new String[] {
					Long.class.getName(), Long.class.getName(),
					Long.class.getName(), Integer.class.getName(),
					Integer.class.getName(), OrderByComparator.class.getName()
				});

		_finderPathWithPaginationCountByModuleIdPreviousLearningActivities =
			new FinderPath(
				entityCacheEnabled, finderCacheEnabled, Long.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
				"countByModuleIdPreviousLearningActivities",
				new String[] {
					Long.class.getName(), Long.class.getName(),
					Long.class.getName()
				});

		_finderPathWithPaginationFindByModuleIdNotTypeId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LearningActivityImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByModuleIdNotTypeId",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithPaginationCountByModuleIdNotTypeId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByModuleIdNotTypeId",
			new String[] {Long.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByGroupIdModuleId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LearningActivityImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupIdModuleId",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByGroupIdModuleId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LearningActivityImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupIdModuleId",
			new String[] {Long.class.getName(), Long.class.getName()},
			LearningActivityModelImpl.GROUPID_COLUMN_BITMASK |
			LearningActivityModelImpl.MODULEID_COLUMN_BITMASK |
			LearningActivityModelImpl.PRIORITY_COLUMN_BITMASK);

		_finderPathCountByGroupIdModuleId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupIdModuleId",
			new String[] {Long.class.getName(), Long.class.getName()});
	}

	@Deactivate
	public void deactivate() {
		entityCache.removeCache(LearningActivityImpl.class.getName());
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
				"value.object.column.bitmask.enabled.com.ted.lms.model.LearningActivity"),
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

	private static final String _SQL_SELECT_LEARNINGACTIVITY =
		"SELECT learningActivity FROM LearningActivity learningActivity";

	private static final String _SQL_SELECT_LEARNINGACTIVITY_WHERE =
		"SELECT learningActivity FROM LearningActivity learningActivity WHERE ";

	private static final String _SQL_COUNT_LEARNINGACTIVITY =
		"SELECT COUNT(learningActivity) FROM LearningActivity learningActivity";

	private static final String _SQL_COUNT_LEARNINGACTIVITY_WHERE =
		"SELECT COUNT(learningActivity) FROM LearningActivity learningActivity WHERE ";

	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN =
		"learningActivity.actId";

	private static final String _FILTER_SQL_SELECT_LEARNINGACTIVITY_WHERE =
		"SELECT DISTINCT {learningActivity.*} FROM LMS_LearningActivity learningActivity WHERE ";

	private static final String
		_FILTER_SQL_SELECT_LEARNINGACTIVITY_NO_INLINE_DISTINCT_WHERE_1 =
			"SELECT {LMS_LearningActivity.*} FROM (SELECT DISTINCT learningActivity.actId FROM LMS_LearningActivity learningActivity WHERE ";

	private static final String
		_FILTER_SQL_SELECT_LEARNINGACTIVITY_NO_INLINE_DISTINCT_WHERE_2 =
			") TEMP_TABLE INNER JOIN LMS_LearningActivity ON TEMP_TABLE.actId = LMS_LearningActivity.actId";

	private static final String _FILTER_SQL_COUNT_LEARNINGACTIVITY_WHERE =
		"SELECT COUNT(DISTINCT learningActivity.actId) AS COUNT_VALUE FROM LMS_LearningActivity learningActivity WHERE ";

	private static final String _FILTER_ENTITY_ALIAS = "learningActivity";

	private static final String _FILTER_ENTITY_TABLE = "LMS_LearningActivity";

	private static final String _ORDER_BY_ENTITY_ALIAS = "learningActivity.";

	private static final String _ORDER_BY_ENTITY_TABLE =
		"LMS_LearningActivity.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No LearningActivity exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No LearningActivity exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		LearningActivityPersistenceImpl.class);

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