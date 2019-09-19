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

package com.ted.lms.learning.activity.p2p.service.persistence.impl;

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
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import com.ted.lms.learning.activity.p2p.exception.NoSuchP2PActivityException;
import com.ted.lms.learning.activity.p2p.model.P2PActivity;
import com.ted.lms.learning.activity.p2p.model.impl.P2PActivityImpl;
import com.ted.lms.learning.activity.p2p.model.impl.P2PActivityModelImpl;
import com.ted.lms.learning.activity.p2p.service.persistence.P2PActivityPersistence;
import com.ted.lms.learning.activity.p2p.service.persistence.impl.constants.PTPPersistenceConstants;

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
 * The persistence implementation for the p2p activity service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = P2PActivityPersistence.class)
@ProviderType
public class P2PActivityPersistenceImpl
	extends BasePersistenceImpl<P2PActivity> implements P2PActivityPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>P2PActivityUtil</code> to access the p2p activity persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		P2PActivityImpl.class.getName();

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
	 * Returns all the p2p activities where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching p2p activities
	 */
	@Override
	public List<P2PActivity> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the p2p activities where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of p2p activities
	 * @param end the upper bound of the range of p2p activities (not inclusive)
	 * @return the range of matching p2p activities
	 */
	@Override
	public List<P2PActivity> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the p2p activities where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of p2p activities
	 * @param end the upper bound of the range of p2p activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching p2p activities
	 */
	@Override
	public List<P2PActivity> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<P2PActivity> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the p2p activities where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of p2p activities
	 * @param end the upper bound of the range of p2p activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching p2p activities
	 */
	@Override
	public List<P2PActivity> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<P2PActivity> orderByComparator,
		boolean retrieveFromCache) {

		uuid = Objects.toString(uuid, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByUuid;
			finderArgs = new Object[] {uuid};
		}
		else {
			finderPath = _finderPathWithPaginationFindByUuid;
			finderArgs = new Object[] {uuid, start, end, orderByComparator};
		}

		List<P2PActivity> list = null;

		if (retrieveFromCache) {
			list = (List<P2PActivity>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (P2PActivity p2pActivity : list) {
					if (!uuid.equals(p2pActivity.getUuid())) {
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

			query.append(_SQL_SELECT_P2PACTIVITY_WHERE);

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
				query.append(P2PActivityModelImpl.ORDER_BY_JPQL);
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
					list = (List<P2PActivity>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<P2PActivity>)QueryUtil.list(
						q, getDialect(), start, end);
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
	 * Returns the first p2p activity in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching p2p activity
	 * @throws NoSuchP2PActivityException if a matching p2p activity could not be found
	 */
	@Override
	public P2PActivity findByUuid_First(
			String uuid, OrderByComparator<P2PActivity> orderByComparator)
		throws NoSuchP2PActivityException {

		P2PActivity p2pActivity = fetchByUuid_First(uuid, orderByComparator);

		if (p2pActivity != null) {
			return p2pActivity;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchP2PActivityException(msg.toString());
	}

	/**
	 * Returns the first p2p activity in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching p2p activity, or <code>null</code> if a matching p2p activity could not be found
	 */
	@Override
	public P2PActivity fetchByUuid_First(
		String uuid, OrderByComparator<P2PActivity> orderByComparator) {

		List<P2PActivity> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last p2p activity in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching p2p activity
	 * @throws NoSuchP2PActivityException if a matching p2p activity could not be found
	 */
	@Override
	public P2PActivity findByUuid_Last(
			String uuid, OrderByComparator<P2PActivity> orderByComparator)
		throws NoSuchP2PActivityException {

		P2PActivity p2pActivity = fetchByUuid_Last(uuid, orderByComparator);

		if (p2pActivity != null) {
			return p2pActivity;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchP2PActivityException(msg.toString());
	}

	/**
	 * Returns the last p2p activity in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching p2p activity, or <code>null</code> if a matching p2p activity could not be found
	 */
	@Override
	public P2PActivity fetchByUuid_Last(
		String uuid, OrderByComparator<P2PActivity> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<P2PActivity> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the p2p activities before and after the current p2p activity in the ordered set where uuid = &#63;.
	 *
	 * @param p2pActivityId the primary key of the current p2p activity
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next p2p activity
	 * @throws NoSuchP2PActivityException if a p2p activity with the primary key could not be found
	 */
	@Override
	public P2PActivity[] findByUuid_PrevAndNext(
			long p2pActivityId, String uuid,
			OrderByComparator<P2PActivity> orderByComparator)
		throws NoSuchP2PActivityException {

		uuid = Objects.toString(uuid, "");

		P2PActivity p2pActivity = findByPrimaryKey(p2pActivityId);

		Session session = null;

		try {
			session = openSession();

			P2PActivity[] array = new P2PActivityImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, p2pActivity, uuid, orderByComparator, true);

			array[1] = p2pActivity;

			array[2] = getByUuid_PrevAndNext(
				session, p2pActivity, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected P2PActivity getByUuid_PrevAndNext(
		Session session, P2PActivity p2pActivity, String uuid,
		OrderByComparator<P2PActivity> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_P2PACTIVITY_WHERE);

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
			query.append(P2PActivityModelImpl.ORDER_BY_JPQL);
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
					orderByComparator.getOrderByConditionValues(p2pActivity)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<P2PActivity> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the p2p activities where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (P2PActivity p2pActivity :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(p2pActivity);
		}
	}

	/**
	 * Returns the number of p2p activities where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching p2p activities
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_P2PACTIVITY_WHERE);

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
		"p2pActivity.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(p2pActivity.uuid IS NULL OR p2pActivity.uuid = '')";

	private FinderPath _finderPathFetchByUUID_G;
	private FinderPath _finderPathCountByUUID_G;

	/**
	 * Returns the p2p activity where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchP2PActivityException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching p2p activity
	 * @throws NoSuchP2PActivityException if a matching p2p activity could not be found
	 */
	@Override
	public P2PActivity findByUUID_G(String uuid, long groupId)
		throws NoSuchP2PActivityException {

		P2PActivity p2pActivity = fetchByUUID_G(uuid, groupId);

		if (p2pActivity == null) {
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

			throw new NoSuchP2PActivityException(msg.toString());
		}

		return p2pActivity;
	}

	/**
	 * Returns the p2p activity where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching p2p activity, or <code>null</code> if a matching p2p activity could not be found
	 */
	@Override
	public P2PActivity fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the p2p activity where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching p2p activity, or <code>null</code> if a matching p2p activity could not be found
	 */
	@Override
	public P2PActivity fetchByUUID_G(
		String uuid, long groupId, boolean retrieveFromCache) {

		uuid = Objects.toString(uuid, "");

		Object[] finderArgs = new Object[] {uuid, groupId};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(
				_finderPathFetchByUUID_G, finderArgs, this);
		}

		if (result instanceof P2PActivity) {
			P2PActivity p2pActivity = (P2PActivity)result;

			if (!Objects.equals(uuid, p2pActivity.getUuid()) ||
				(groupId != p2pActivity.getGroupId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_P2PACTIVITY_WHERE);

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

				List<P2PActivity> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(
						_finderPathFetchByUUID_G, finderArgs, list);
				}
				else {
					P2PActivity p2pActivity = list.get(0);

					result = p2pActivity;

					cacheResult(p2pActivity);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(_finderPathFetchByUUID_G, finderArgs);

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
			return (P2PActivity)result;
		}
	}

	/**
	 * Removes the p2p activity where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the p2p activity that was removed
	 */
	@Override
	public P2PActivity removeByUUID_G(String uuid, long groupId)
		throws NoSuchP2PActivityException {

		P2PActivity p2pActivity = findByUUID_G(uuid, groupId);

		return remove(p2pActivity);
	}

	/**
	 * Returns the number of p2p activities where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching p2p activities
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUUID_G;

		Object[] finderArgs = new Object[] {uuid, groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_P2PACTIVITY_WHERE);

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
		"p2pActivity.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_G_UUID_3 =
		"(p2pActivity.uuid IS NULL OR p2pActivity.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 =
		"p2pActivity.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the p2p activities where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching p2p activities
	 */
	@Override
	public List<P2PActivity> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the p2p activities where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of p2p activities
	 * @param end the upper bound of the range of p2p activities (not inclusive)
	 * @return the range of matching p2p activities
	 */
	@Override
	public List<P2PActivity> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the p2p activities where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of p2p activities
	 * @param end the upper bound of the range of p2p activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching p2p activities
	 */
	@Override
	public List<P2PActivity> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<P2PActivity> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the p2p activities where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of p2p activities
	 * @param end the upper bound of the range of p2p activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching p2p activities
	 */
	@Override
	public List<P2PActivity> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<P2PActivity> orderByComparator,
		boolean retrieveFromCache) {

		uuid = Objects.toString(uuid, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByUuid_C;
			finderArgs = new Object[] {uuid, companyId};
		}
		else {
			finderPath = _finderPathWithPaginationFindByUuid_C;
			finderArgs = new Object[] {
				uuid, companyId, start, end, orderByComparator
			};
		}

		List<P2PActivity> list = null;

		if (retrieveFromCache) {
			list = (List<P2PActivity>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (P2PActivity p2pActivity : list) {
					if (!uuid.equals(p2pActivity.getUuid()) ||
						(companyId != p2pActivity.getCompanyId())) {

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

			query.append(_SQL_SELECT_P2PACTIVITY_WHERE);

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
				query.append(P2PActivityModelImpl.ORDER_BY_JPQL);
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
					list = (List<P2PActivity>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<P2PActivity>)QueryUtil.list(
						q, getDialect(), start, end);
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
	 * Returns the first p2p activity in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching p2p activity
	 * @throws NoSuchP2PActivityException if a matching p2p activity could not be found
	 */
	@Override
	public P2PActivity findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<P2PActivity> orderByComparator)
		throws NoSuchP2PActivityException {

		P2PActivity p2pActivity = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (p2pActivity != null) {
			return p2pActivity;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchP2PActivityException(msg.toString());
	}

	/**
	 * Returns the first p2p activity in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching p2p activity, or <code>null</code> if a matching p2p activity could not be found
	 */
	@Override
	public P2PActivity fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<P2PActivity> orderByComparator) {

		List<P2PActivity> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last p2p activity in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching p2p activity
	 * @throws NoSuchP2PActivityException if a matching p2p activity could not be found
	 */
	@Override
	public P2PActivity findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<P2PActivity> orderByComparator)
		throws NoSuchP2PActivityException {

		P2PActivity p2pActivity = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (p2pActivity != null) {
			return p2pActivity;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchP2PActivityException(msg.toString());
	}

	/**
	 * Returns the last p2p activity in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching p2p activity, or <code>null</code> if a matching p2p activity could not be found
	 */
	@Override
	public P2PActivity fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<P2PActivity> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<P2PActivity> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the p2p activities before and after the current p2p activity in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param p2pActivityId the primary key of the current p2p activity
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next p2p activity
	 * @throws NoSuchP2PActivityException if a p2p activity with the primary key could not be found
	 */
	@Override
	public P2PActivity[] findByUuid_C_PrevAndNext(
			long p2pActivityId, String uuid, long companyId,
			OrderByComparator<P2PActivity> orderByComparator)
		throws NoSuchP2PActivityException {

		uuid = Objects.toString(uuid, "");

		P2PActivity p2pActivity = findByPrimaryKey(p2pActivityId);

		Session session = null;

		try {
			session = openSession();

			P2PActivity[] array = new P2PActivityImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, p2pActivity, uuid, companyId, orderByComparator, true);

			array[1] = p2pActivity;

			array[2] = getByUuid_C_PrevAndNext(
				session, p2pActivity, uuid, companyId, orderByComparator,
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

	protected P2PActivity getByUuid_C_PrevAndNext(
		Session session, P2PActivity p2pActivity, String uuid, long companyId,
		OrderByComparator<P2PActivity> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_P2PACTIVITY_WHERE);

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
			query.append(P2PActivityModelImpl.ORDER_BY_JPQL);
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
					orderByComparator.getOrderByConditionValues(p2pActivity)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<P2PActivity> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the p2p activities where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (P2PActivity p2pActivity :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(p2pActivity);
		}
	}

	/**
	 * Returns the number of p2p activities where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching p2p activities
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_P2PACTIVITY_WHERE);

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
		"p2pActivity.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(p2pActivity.uuid IS NULL OR p2pActivity.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"p2pActivity.companyId = ?";

	private FinderPath _finderPathFetchByActIdUserId;
	private FinderPath _finderPathCountByActIdUserId;

	/**
	 * Returns the p2p activity where actId = &#63; and userId = &#63; or throws a <code>NoSuchP2PActivityException</code> if it could not be found.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @return the matching p2p activity
	 * @throws NoSuchP2PActivityException if a matching p2p activity could not be found
	 */
	@Override
	public P2PActivity findByActIdUserId(long actId, long userId)
		throws NoSuchP2PActivityException {

		P2PActivity p2pActivity = fetchByActIdUserId(actId, userId);

		if (p2pActivity == null) {
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

			throw new NoSuchP2PActivityException(msg.toString());
		}

		return p2pActivity;
	}

	/**
	 * Returns the p2p activity where actId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @return the matching p2p activity, or <code>null</code> if a matching p2p activity could not be found
	 */
	@Override
	public P2PActivity fetchByActIdUserId(long actId, long userId) {
		return fetchByActIdUserId(actId, userId, true);
	}

	/**
	 * Returns the p2p activity where actId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching p2p activity, or <code>null</code> if a matching p2p activity could not be found
	 */
	@Override
	public P2PActivity fetchByActIdUserId(
		long actId, long userId, boolean retrieveFromCache) {

		Object[] finderArgs = new Object[] {actId, userId};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(
				_finderPathFetchByActIdUserId, finderArgs, this);
		}

		if (result instanceof P2PActivity) {
			P2PActivity p2pActivity = (P2PActivity)result;

			if ((actId != p2pActivity.getActId()) ||
				(userId != p2pActivity.getUserId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_P2PACTIVITY_WHERE);

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

				List<P2PActivity> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(
						_finderPathFetchByActIdUserId, finderArgs, list);
				}
				else {
					P2PActivity p2pActivity = list.get(0);

					result = p2pActivity;

					cacheResult(p2pActivity);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(
					_finderPathFetchByActIdUserId, finderArgs);

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
			return (P2PActivity)result;
		}
	}

	/**
	 * Removes the p2p activity where actId = &#63; and userId = &#63; from the database.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @return the p2p activity that was removed
	 */
	@Override
	public P2PActivity removeByActIdUserId(long actId, long userId)
		throws NoSuchP2PActivityException {

		P2PActivity p2pActivity = findByActIdUserId(actId, userId);

		return remove(p2pActivity);
	}

	/**
	 * Returns the number of p2p activities where actId = &#63; and userId = &#63;.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @return the number of matching p2p activities
	 */
	@Override
	public int countByActIdUserId(long actId, long userId) {
		FinderPath finderPath = _finderPathCountByActIdUserId;

		Object[] finderArgs = new Object[] {actId, userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_P2PACTIVITY_WHERE);

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
		"p2pActivity.actId = ? AND ";

	private static final String _FINDER_COLUMN_ACTIDUSERID_USERID_2 =
		"p2pActivity.userId = ?";

	private FinderPath _finderPathWithPaginationFindByActId;
	private FinderPath _finderPathWithoutPaginationFindByActId;
	private FinderPath _finderPathCountByActId;

	/**
	 * Returns all the p2p activities where actId = &#63;.
	 *
	 * @param actId the act ID
	 * @return the matching p2p activities
	 */
	@Override
	public List<P2PActivity> findByActId(long actId) {
		return findByActId(actId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the p2p activities where actId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param actId the act ID
	 * @param start the lower bound of the range of p2p activities
	 * @param end the upper bound of the range of p2p activities (not inclusive)
	 * @return the range of matching p2p activities
	 */
	@Override
	public List<P2PActivity> findByActId(long actId, int start, int end) {
		return findByActId(actId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the p2p activities where actId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param actId the act ID
	 * @param start the lower bound of the range of p2p activities
	 * @param end the upper bound of the range of p2p activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching p2p activities
	 */
	@Override
	public List<P2PActivity> findByActId(
		long actId, int start, int end,
		OrderByComparator<P2PActivity> orderByComparator) {

		return findByActId(actId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the p2p activities where actId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param actId the act ID
	 * @param start the lower bound of the range of p2p activities
	 * @param end the upper bound of the range of p2p activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching p2p activities
	 */
	@Override
	public List<P2PActivity> findByActId(
		long actId, int start, int end,
		OrderByComparator<P2PActivity> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByActId;
			finderArgs = new Object[] {actId};
		}
		else {
			finderPath = _finderPathWithPaginationFindByActId;
			finderArgs = new Object[] {actId, start, end, orderByComparator};
		}

		List<P2PActivity> list = null;

		if (retrieveFromCache) {
			list = (List<P2PActivity>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (P2PActivity p2pActivity : list) {
					if ((actId != p2pActivity.getActId())) {
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

			query.append(_SQL_SELECT_P2PACTIVITY_WHERE);

			query.append(_FINDER_COLUMN_ACTID_ACTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(P2PActivityModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(actId);

				if (!pagination) {
					list = (List<P2PActivity>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<P2PActivity>)QueryUtil.list(
						q, getDialect(), start, end);
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
	 * Returns the first p2p activity in the ordered set where actId = &#63;.
	 *
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching p2p activity
	 * @throws NoSuchP2PActivityException if a matching p2p activity could not be found
	 */
	@Override
	public P2PActivity findByActId_First(
			long actId, OrderByComparator<P2PActivity> orderByComparator)
		throws NoSuchP2PActivityException {

		P2PActivity p2pActivity = fetchByActId_First(actId, orderByComparator);

		if (p2pActivity != null) {
			return p2pActivity;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("actId=");
		msg.append(actId);

		msg.append("}");

		throw new NoSuchP2PActivityException(msg.toString());
	}

	/**
	 * Returns the first p2p activity in the ordered set where actId = &#63;.
	 *
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching p2p activity, or <code>null</code> if a matching p2p activity could not be found
	 */
	@Override
	public P2PActivity fetchByActId_First(
		long actId, OrderByComparator<P2PActivity> orderByComparator) {

		List<P2PActivity> list = findByActId(actId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last p2p activity in the ordered set where actId = &#63;.
	 *
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching p2p activity
	 * @throws NoSuchP2PActivityException if a matching p2p activity could not be found
	 */
	@Override
	public P2PActivity findByActId_Last(
			long actId, OrderByComparator<P2PActivity> orderByComparator)
		throws NoSuchP2PActivityException {

		P2PActivity p2pActivity = fetchByActId_Last(actId, orderByComparator);

		if (p2pActivity != null) {
			return p2pActivity;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("actId=");
		msg.append(actId);

		msg.append("}");

		throw new NoSuchP2PActivityException(msg.toString());
	}

	/**
	 * Returns the last p2p activity in the ordered set where actId = &#63;.
	 *
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching p2p activity, or <code>null</code> if a matching p2p activity could not be found
	 */
	@Override
	public P2PActivity fetchByActId_Last(
		long actId, OrderByComparator<P2PActivity> orderByComparator) {

		int count = countByActId(actId);

		if (count == 0) {
			return null;
		}

		List<P2PActivity> list = findByActId(
			actId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the p2p activities before and after the current p2p activity in the ordered set where actId = &#63;.
	 *
	 * @param p2pActivityId the primary key of the current p2p activity
	 * @param actId the act ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next p2p activity
	 * @throws NoSuchP2PActivityException if a p2p activity with the primary key could not be found
	 */
	@Override
	public P2PActivity[] findByActId_PrevAndNext(
			long p2pActivityId, long actId,
			OrderByComparator<P2PActivity> orderByComparator)
		throws NoSuchP2PActivityException {

		P2PActivity p2pActivity = findByPrimaryKey(p2pActivityId);

		Session session = null;

		try {
			session = openSession();

			P2PActivity[] array = new P2PActivityImpl[3];

			array[0] = getByActId_PrevAndNext(
				session, p2pActivity, actId, orderByComparator, true);

			array[1] = p2pActivity;

			array[2] = getByActId_PrevAndNext(
				session, p2pActivity, actId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected P2PActivity getByActId_PrevAndNext(
		Session session, P2PActivity p2pActivity, long actId,
		OrderByComparator<P2PActivity> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_P2PACTIVITY_WHERE);

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
			query.append(P2PActivityModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(actId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(p2pActivity)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<P2PActivity> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the p2p activities where actId = &#63; from the database.
	 *
	 * @param actId the act ID
	 */
	@Override
	public void removeByActId(long actId) {
		for (P2PActivity p2pActivity :
				findByActId(
					actId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(p2pActivity);
		}
	}

	/**
	 * Returns the number of p2p activities where actId = &#63;.
	 *
	 * @param actId the act ID
	 * @return the number of matching p2p activities
	 */
	@Override
	public int countByActId(long actId) {
		FinderPath finderPath = _finderPathCountByActId;

		Object[] finderArgs = new Object[] {actId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_P2PACTIVITY_WHERE);

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
		"p2pActivity.actId = ?";

	private FinderPath _finderPathWithPaginationFindByUserId;
	private FinderPath _finderPathWithoutPaginationFindByUserId;
	private FinderPath _finderPathCountByUserId;

	/**
	 * Returns all the p2p activities where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching p2p activities
	 */
	@Override
	public List<P2PActivity> findByUserId(long userId) {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the p2p activities where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of p2p activities
	 * @param end the upper bound of the range of p2p activities (not inclusive)
	 * @return the range of matching p2p activities
	 */
	@Override
	public List<P2PActivity> findByUserId(long userId, int start, int end) {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the p2p activities where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of p2p activities
	 * @param end the upper bound of the range of p2p activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching p2p activities
	 */
	@Override
	public List<P2PActivity> findByUserId(
		long userId, int start, int end,
		OrderByComparator<P2PActivity> orderByComparator) {

		return findByUserId(userId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the p2p activities where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of p2p activities
	 * @param end the upper bound of the range of p2p activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching p2p activities
	 */
	@Override
	public List<P2PActivity> findByUserId(
		long userId, int start, int end,
		OrderByComparator<P2PActivity> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByUserId;
			finderArgs = new Object[] {userId};
		}
		else {
			finderPath = _finderPathWithPaginationFindByUserId;
			finderArgs = new Object[] {userId, start, end, orderByComparator};
		}

		List<P2PActivity> list = null;

		if (retrieveFromCache) {
			list = (List<P2PActivity>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (P2PActivity p2pActivity : list) {
					if ((userId != p2pActivity.getUserId())) {
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

			query.append(_SQL_SELECT_P2PACTIVITY_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(P2PActivityModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (!pagination) {
					list = (List<P2PActivity>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<P2PActivity>)QueryUtil.list(
						q, getDialect(), start, end);
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
	 * Returns the first p2p activity in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching p2p activity
	 * @throws NoSuchP2PActivityException if a matching p2p activity could not be found
	 */
	@Override
	public P2PActivity findByUserId_First(
			long userId, OrderByComparator<P2PActivity> orderByComparator)
		throws NoSuchP2PActivityException {

		P2PActivity p2pActivity = fetchByUserId_First(
			userId, orderByComparator);

		if (p2pActivity != null) {
			return p2pActivity;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append("}");

		throw new NoSuchP2PActivityException(msg.toString());
	}

	/**
	 * Returns the first p2p activity in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching p2p activity, or <code>null</code> if a matching p2p activity could not be found
	 */
	@Override
	public P2PActivity fetchByUserId_First(
		long userId, OrderByComparator<P2PActivity> orderByComparator) {

		List<P2PActivity> list = findByUserId(userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last p2p activity in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching p2p activity
	 * @throws NoSuchP2PActivityException if a matching p2p activity could not be found
	 */
	@Override
	public P2PActivity findByUserId_Last(
			long userId, OrderByComparator<P2PActivity> orderByComparator)
		throws NoSuchP2PActivityException {

		P2PActivity p2pActivity = fetchByUserId_Last(userId, orderByComparator);

		if (p2pActivity != null) {
			return p2pActivity;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append("}");

		throw new NoSuchP2PActivityException(msg.toString());
	}

	/**
	 * Returns the last p2p activity in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching p2p activity, or <code>null</code> if a matching p2p activity could not be found
	 */
	@Override
	public P2PActivity fetchByUserId_Last(
		long userId, OrderByComparator<P2PActivity> orderByComparator) {

		int count = countByUserId(userId);

		if (count == 0) {
			return null;
		}

		List<P2PActivity> list = findByUserId(
			userId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the p2p activities before and after the current p2p activity in the ordered set where userId = &#63;.
	 *
	 * @param p2pActivityId the primary key of the current p2p activity
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next p2p activity
	 * @throws NoSuchP2PActivityException if a p2p activity with the primary key could not be found
	 */
	@Override
	public P2PActivity[] findByUserId_PrevAndNext(
			long p2pActivityId, long userId,
			OrderByComparator<P2PActivity> orderByComparator)
		throws NoSuchP2PActivityException {

		P2PActivity p2pActivity = findByPrimaryKey(p2pActivityId);

		Session session = null;

		try {
			session = openSession();

			P2PActivity[] array = new P2PActivityImpl[3];

			array[0] = getByUserId_PrevAndNext(
				session, p2pActivity, userId, orderByComparator, true);

			array[1] = p2pActivity;

			array[2] = getByUserId_PrevAndNext(
				session, p2pActivity, userId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected P2PActivity getByUserId_PrevAndNext(
		Session session, P2PActivity p2pActivity, long userId,
		OrderByComparator<P2PActivity> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_P2PACTIVITY_WHERE);

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
			query.append(P2PActivityModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(p2pActivity)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<P2PActivity> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the p2p activities where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	@Override
	public void removeByUserId(long userId) {
		for (P2PActivity p2pActivity :
				findByUserId(
					userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(p2pActivity);
		}
	}

	/**
	 * Returns the number of p2p activities where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching p2p activities
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = _finderPathCountByUserId;

		Object[] finderArgs = new Object[] {userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_P2PACTIVITY_WHERE);

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
		"p2pActivity.userId = ?";

	private FinderPath _finderPathWithPaginationFindByAsignationsCompleted;
	private FinderPath _finderPathWithoutPaginationFindByAsignationsCompleted;
	private FinderPath _finderPathCountByAsignationsCompleted;

	/**
	 * Returns all the p2p activities where asignationsCompleted = &#63;.
	 *
	 * @param asignationsCompleted the asignations completed
	 * @return the matching p2p activities
	 */
	@Override
	public List<P2PActivity> findByAsignationsCompleted(
		boolean asignationsCompleted) {

		return findByAsignationsCompleted(
			asignationsCompleted, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the p2p activities where asignationsCompleted = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param asignationsCompleted the asignations completed
	 * @param start the lower bound of the range of p2p activities
	 * @param end the upper bound of the range of p2p activities (not inclusive)
	 * @return the range of matching p2p activities
	 */
	@Override
	public List<P2PActivity> findByAsignationsCompleted(
		boolean asignationsCompleted, int start, int end) {

		return findByAsignationsCompleted(
			asignationsCompleted, start, end, null);
	}

	/**
	 * Returns an ordered range of all the p2p activities where asignationsCompleted = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param asignationsCompleted the asignations completed
	 * @param start the lower bound of the range of p2p activities
	 * @param end the upper bound of the range of p2p activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching p2p activities
	 */
	@Override
	public List<P2PActivity> findByAsignationsCompleted(
		boolean asignationsCompleted, int start, int end,
		OrderByComparator<P2PActivity> orderByComparator) {

		return findByAsignationsCompleted(
			asignationsCompleted, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the p2p activities where asignationsCompleted = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param asignationsCompleted the asignations completed
	 * @param start the lower bound of the range of p2p activities
	 * @param end the upper bound of the range of p2p activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching p2p activities
	 */
	@Override
	public List<P2PActivity> findByAsignationsCompleted(
		boolean asignationsCompleted, int start, int end,
		OrderByComparator<P2PActivity> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByAsignationsCompleted;
			finderArgs = new Object[] {asignationsCompleted};
		}
		else {
			finderPath = _finderPathWithPaginationFindByAsignationsCompleted;
			finderArgs = new Object[] {
				asignationsCompleted, start, end, orderByComparator
			};
		}

		List<P2PActivity> list = null;

		if (retrieveFromCache) {
			list = (List<P2PActivity>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (P2PActivity p2pActivity : list) {
					if ((asignationsCompleted !=
							p2pActivity.isAsignationsCompleted())) {

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

			query.append(_SQL_SELECT_P2PACTIVITY_WHERE);

			query.append(
				_FINDER_COLUMN_ASIGNATIONSCOMPLETED_ASIGNATIONSCOMPLETED_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(P2PActivityModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(asignationsCompleted);

				if (!pagination) {
					list = (List<P2PActivity>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<P2PActivity>)QueryUtil.list(
						q, getDialect(), start, end);
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
	 * Returns the first p2p activity in the ordered set where asignationsCompleted = &#63;.
	 *
	 * @param asignationsCompleted the asignations completed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching p2p activity
	 * @throws NoSuchP2PActivityException if a matching p2p activity could not be found
	 */
	@Override
	public P2PActivity findByAsignationsCompleted_First(
			boolean asignationsCompleted,
			OrderByComparator<P2PActivity> orderByComparator)
		throws NoSuchP2PActivityException {

		P2PActivity p2pActivity = fetchByAsignationsCompleted_First(
			asignationsCompleted, orderByComparator);

		if (p2pActivity != null) {
			return p2pActivity;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("asignationsCompleted=");
		msg.append(asignationsCompleted);

		msg.append("}");

		throw new NoSuchP2PActivityException(msg.toString());
	}

	/**
	 * Returns the first p2p activity in the ordered set where asignationsCompleted = &#63;.
	 *
	 * @param asignationsCompleted the asignations completed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching p2p activity, or <code>null</code> if a matching p2p activity could not be found
	 */
	@Override
	public P2PActivity fetchByAsignationsCompleted_First(
		boolean asignationsCompleted,
		OrderByComparator<P2PActivity> orderByComparator) {

		List<P2PActivity> list = findByAsignationsCompleted(
			asignationsCompleted, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last p2p activity in the ordered set where asignationsCompleted = &#63;.
	 *
	 * @param asignationsCompleted the asignations completed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching p2p activity
	 * @throws NoSuchP2PActivityException if a matching p2p activity could not be found
	 */
	@Override
	public P2PActivity findByAsignationsCompleted_Last(
			boolean asignationsCompleted,
			OrderByComparator<P2PActivity> orderByComparator)
		throws NoSuchP2PActivityException {

		P2PActivity p2pActivity = fetchByAsignationsCompleted_Last(
			asignationsCompleted, orderByComparator);

		if (p2pActivity != null) {
			return p2pActivity;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("asignationsCompleted=");
		msg.append(asignationsCompleted);

		msg.append("}");

		throw new NoSuchP2PActivityException(msg.toString());
	}

	/**
	 * Returns the last p2p activity in the ordered set where asignationsCompleted = &#63;.
	 *
	 * @param asignationsCompleted the asignations completed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching p2p activity, or <code>null</code> if a matching p2p activity could not be found
	 */
	@Override
	public P2PActivity fetchByAsignationsCompleted_Last(
		boolean asignationsCompleted,
		OrderByComparator<P2PActivity> orderByComparator) {

		int count = countByAsignationsCompleted(asignationsCompleted);

		if (count == 0) {
			return null;
		}

		List<P2PActivity> list = findByAsignationsCompleted(
			asignationsCompleted, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the p2p activities before and after the current p2p activity in the ordered set where asignationsCompleted = &#63;.
	 *
	 * @param p2pActivityId the primary key of the current p2p activity
	 * @param asignationsCompleted the asignations completed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next p2p activity
	 * @throws NoSuchP2PActivityException if a p2p activity with the primary key could not be found
	 */
	@Override
	public P2PActivity[] findByAsignationsCompleted_PrevAndNext(
			long p2pActivityId, boolean asignationsCompleted,
			OrderByComparator<P2PActivity> orderByComparator)
		throws NoSuchP2PActivityException {

		P2PActivity p2pActivity = findByPrimaryKey(p2pActivityId);

		Session session = null;

		try {
			session = openSession();

			P2PActivity[] array = new P2PActivityImpl[3];

			array[0] = getByAsignationsCompleted_PrevAndNext(
				session, p2pActivity, asignationsCompleted, orderByComparator,
				true);

			array[1] = p2pActivity;

			array[2] = getByAsignationsCompleted_PrevAndNext(
				session, p2pActivity, asignationsCompleted, orderByComparator,
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

	protected P2PActivity getByAsignationsCompleted_PrevAndNext(
		Session session, P2PActivity p2pActivity, boolean asignationsCompleted,
		OrderByComparator<P2PActivity> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_P2PACTIVITY_WHERE);

		query.append(
			_FINDER_COLUMN_ASIGNATIONSCOMPLETED_ASIGNATIONSCOMPLETED_2);

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
			query.append(P2PActivityModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(asignationsCompleted);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(p2pActivity)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<P2PActivity> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the p2p activities where asignationsCompleted = &#63; from the database.
	 *
	 * @param asignationsCompleted the asignations completed
	 */
	@Override
	public void removeByAsignationsCompleted(boolean asignationsCompleted) {
		for (P2PActivity p2pActivity :
				findByAsignationsCompleted(
					asignationsCompleted, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(p2pActivity);
		}
	}

	/**
	 * Returns the number of p2p activities where asignationsCompleted = &#63;.
	 *
	 * @param asignationsCompleted the asignations completed
	 * @return the number of matching p2p activities
	 */
	@Override
	public int countByAsignationsCompleted(boolean asignationsCompleted) {
		FinderPath finderPath = _finderPathCountByAsignationsCompleted;

		Object[] finderArgs = new Object[] {asignationsCompleted};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_P2PACTIVITY_WHERE);

			query.append(
				_FINDER_COLUMN_ASIGNATIONSCOMPLETED_ASIGNATIONSCOMPLETED_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(asignationsCompleted);

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
		_FINDER_COLUMN_ASIGNATIONSCOMPLETED_ASIGNATIONSCOMPLETED_2 =
			"p2pActivity.asignationsCompleted = ?";

	public P2PActivityPersistenceImpl() {
		setModelClass(P2PActivity.class);

		setModelImplClass(P2PActivityImpl.class);
		setModelPKClass(long.class);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");
		dbColumnNames.put("date", "date_");

		setDBColumnNames(dbColumnNames);
	}

	/**
	 * Caches the p2p activity in the entity cache if it is enabled.
	 *
	 * @param p2pActivity the p2p activity
	 */
	@Override
	public void cacheResult(P2PActivity p2pActivity) {
		entityCache.putResult(
			entityCacheEnabled, P2PActivityImpl.class,
			p2pActivity.getPrimaryKey(), p2pActivity);

		finderCache.putResult(
			_finderPathFetchByUUID_G,
			new Object[] {p2pActivity.getUuid(), p2pActivity.getGroupId()},
			p2pActivity);

		finderCache.putResult(
			_finderPathFetchByActIdUserId,
			new Object[] {p2pActivity.getActId(), p2pActivity.getUserId()},
			p2pActivity);

		p2pActivity.resetOriginalValues();
	}

	/**
	 * Caches the p2p activities in the entity cache if it is enabled.
	 *
	 * @param p2pActivities the p2p activities
	 */
	@Override
	public void cacheResult(List<P2PActivity> p2pActivities) {
		for (P2PActivity p2pActivity : p2pActivities) {
			if (entityCache.getResult(
					entityCacheEnabled, P2PActivityImpl.class,
					p2pActivity.getPrimaryKey()) == null) {

				cacheResult(p2pActivity);
			}
			else {
				p2pActivity.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all p2p activities.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(P2PActivityImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the p2p activity.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(P2PActivity p2pActivity) {
		entityCache.removeResult(
			entityCacheEnabled, P2PActivityImpl.class,
			p2pActivity.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((P2PActivityModelImpl)p2pActivity, true);
	}

	@Override
	public void clearCache(List<P2PActivity> p2pActivities) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (P2PActivity p2pActivity : p2pActivities) {
			entityCache.removeResult(
				entityCacheEnabled, P2PActivityImpl.class,
				p2pActivity.getPrimaryKey());

			clearUniqueFindersCache((P2PActivityModelImpl)p2pActivity, true);
		}
	}

	protected void cacheUniqueFindersCache(
		P2PActivityModelImpl p2pActivityModelImpl) {

		Object[] args = new Object[] {
			p2pActivityModelImpl.getUuid(), p2pActivityModelImpl.getGroupId()
		};

		finderCache.putResult(
			_finderPathCountByUUID_G, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByUUID_G, args, p2pActivityModelImpl, false);

		args = new Object[] {
			p2pActivityModelImpl.getActId(), p2pActivityModelImpl.getUserId()
		};

		finderCache.putResult(
			_finderPathCountByActIdUserId, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByActIdUserId, args, p2pActivityModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		P2PActivityModelImpl p2pActivityModelImpl, boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				p2pActivityModelImpl.getUuid(),
				p2pActivityModelImpl.getGroupId()
			};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
		}

		if ((p2pActivityModelImpl.getColumnBitmask() &
			 _finderPathFetchByUUID_G.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				p2pActivityModelImpl.getOriginalUuid(),
				p2pActivityModelImpl.getOriginalGroupId()
			};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
				p2pActivityModelImpl.getActId(),
				p2pActivityModelImpl.getUserId()
			};

			finderCache.removeResult(_finderPathCountByActIdUserId, args);
			finderCache.removeResult(_finderPathFetchByActIdUserId, args);
		}

		if ((p2pActivityModelImpl.getColumnBitmask() &
			 _finderPathFetchByActIdUserId.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				p2pActivityModelImpl.getOriginalActId(),
				p2pActivityModelImpl.getOriginalUserId()
			};

			finderCache.removeResult(_finderPathCountByActIdUserId, args);
			finderCache.removeResult(_finderPathFetchByActIdUserId, args);
		}
	}

	/**
	 * Creates a new p2p activity with the primary key. Does not add the p2p activity to the database.
	 *
	 * @param p2pActivityId the primary key for the new p2p activity
	 * @return the new p2p activity
	 */
	@Override
	public P2PActivity create(long p2pActivityId) {
		P2PActivity p2pActivity = new P2PActivityImpl();

		p2pActivity.setNew(true);
		p2pActivity.setPrimaryKey(p2pActivityId);

		String uuid = PortalUUIDUtil.generate();

		p2pActivity.setUuid(uuid);

		p2pActivity.setCompanyId(companyProvider.getCompanyId());

		return p2pActivity;
	}

	/**
	 * Removes the p2p activity with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param p2pActivityId the primary key of the p2p activity
	 * @return the p2p activity that was removed
	 * @throws NoSuchP2PActivityException if a p2p activity with the primary key could not be found
	 */
	@Override
	public P2PActivity remove(long p2pActivityId)
		throws NoSuchP2PActivityException {

		return remove((Serializable)p2pActivityId);
	}

	/**
	 * Removes the p2p activity with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the p2p activity
	 * @return the p2p activity that was removed
	 * @throws NoSuchP2PActivityException if a p2p activity with the primary key could not be found
	 */
	@Override
	public P2PActivity remove(Serializable primaryKey)
		throws NoSuchP2PActivityException {

		Session session = null;

		try {
			session = openSession();

			P2PActivity p2pActivity = (P2PActivity)session.get(
				P2PActivityImpl.class, primaryKey);

			if (p2pActivity == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchP2PActivityException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(p2pActivity);
		}
		catch (NoSuchP2PActivityException nsee) {
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
	protected P2PActivity removeImpl(P2PActivity p2pActivity) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(p2pActivity)) {
				p2pActivity = (P2PActivity)session.get(
					P2PActivityImpl.class, p2pActivity.getPrimaryKeyObj());
			}

			if (p2pActivity != null) {
				session.delete(p2pActivity);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (p2pActivity != null) {
			clearCache(p2pActivity);
		}

		return p2pActivity;
	}

	@Override
	public P2PActivity updateImpl(P2PActivity p2pActivity) {
		boolean isNew = p2pActivity.isNew();

		if (!(p2pActivity instanceof P2PActivityModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(p2pActivity.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(p2pActivity);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in p2pActivity proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom P2PActivity implementation " +
					p2pActivity.getClass());
		}

		P2PActivityModelImpl p2pActivityModelImpl =
			(P2PActivityModelImpl)p2pActivity;

		if (Validator.isNull(p2pActivity.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			p2pActivity.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (p2pActivity.getCreateDate() == null)) {
			if (serviceContext == null) {
				p2pActivity.setCreateDate(now);
			}
			else {
				p2pActivity.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!p2pActivityModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				p2pActivity.setModifiedDate(now);
			}
			else {
				p2pActivity.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (p2pActivity.isNew()) {
				session.save(p2pActivity);

				p2pActivity.setNew(false);
			}
			else {
				p2pActivity = (P2PActivity)session.merge(p2pActivity);
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
			Object[] args = new Object[] {p2pActivityModelImpl.getUuid()};

			finderCache.removeResult(_finderPathCountByUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid, args);

			args = new Object[] {
				p2pActivityModelImpl.getUuid(),
				p2pActivityModelImpl.getCompanyId()
			};

			finderCache.removeResult(_finderPathCountByUuid_C, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid_C, args);

			args = new Object[] {p2pActivityModelImpl.getActId()};

			finderCache.removeResult(_finderPathCountByActId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByActId, args);

			args = new Object[] {p2pActivityModelImpl.getUserId()};

			finderCache.removeResult(_finderPathCountByUserId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUserId, args);

			args = new Object[] {p2pActivityModelImpl.isAsignationsCompleted()};

			finderCache.removeResult(
				_finderPathCountByAsignationsCompleted, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByAsignationsCompleted, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((p2pActivityModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					p2pActivityModelImpl.getOriginalUuid()
				};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);

				args = new Object[] {p2pActivityModelImpl.getUuid()};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);
			}

			if ((p2pActivityModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid_C.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					p2pActivityModelImpl.getOriginalUuid(),
					p2pActivityModelImpl.getOriginalCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);

				args = new Object[] {
					p2pActivityModelImpl.getUuid(),
					p2pActivityModelImpl.getCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);
			}

			if ((p2pActivityModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByActId.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					p2pActivityModelImpl.getOriginalActId()
				};

				finderCache.removeResult(_finderPathCountByActId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByActId, args);

				args = new Object[] {p2pActivityModelImpl.getActId()};

				finderCache.removeResult(_finderPathCountByActId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByActId, args);
			}

			if ((p2pActivityModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUserId.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					p2pActivityModelImpl.getOriginalUserId()
				};

				finderCache.removeResult(_finderPathCountByUserId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUserId, args);

				args = new Object[] {p2pActivityModelImpl.getUserId()};

				finderCache.removeResult(_finderPathCountByUserId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUserId, args);
			}

			if ((p2pActivityModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByAsignationsCompleted.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					p2pActivityModelImpl.getOriginalAsignationsCompleted()
				};

				finderCache.removeResult(
					_finderPathCountByAsignationsCompleted, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByAsignationsCompleted,
					args);

				args = new Object[] {
					p2pActivityModelImpl.isAsignationsCompleted()
				};

				finderCache.removeResult(
					_finderPathCountByAsignationsCompleted, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByAsignationsCompleted,
					args);
			}
		}

		entityCache.putResult(
			entityCacheEnabled, P2PActivityImpl.class,
			p2pActivity.getPrimaryKey(), p2pActivity, false);

		clearUniqueFindersCache(p2pActivityModelImpl, false);
		cacheUniqueFindersCache(p2pActivityModelImpl);

		p2pActivity.resetOriginalValues();

		return p2pActivity;
	}

	/**
	 * Returns the p2p activity with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the p2p activity
	 * @return the p2p activity
	 * @throws NoSuchP2PActivityException if a p2p activity with the primary key could not be found
	 */
	@Override
	public P2PActivity findByPrimaryKey(Serializable primaryKey)
		throws NoSuchP2PActivityException {

		P2PActivity p2pActivity = fetchByPrimaryKey(primaryKey);

		if (p2pActivity == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchP2PActivityException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return p2pActivity;
	}

	/**
	 * Returns the p2p activity with the primary key or throws a <code>NoSuchP2PActivityException</code> if it could not be found.
	 *
	 * @param p2pActivityId the primary key of the p2p activity
	 * @return the p2p activity
	 * @throws NoSuchP2PActivityException if a p2p activity with the primary key could not be found
	 */
	@Override
	public P2PActivity findByPrimaryKey(long p2pActivityId)
		throws NoSuchP2PActivityException {

		return findByPrimaryKey((Serializable)p2pActivityId);
	}

	/**
	 * Returns the p2p activity with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param p2pActivityId the primary key of the p2p activity
	 * @return the p2p activity, or <code>null</code> if a p2p activity with the primary key could not be found
	 */
	@Override
	public P2PActivity fetchByPrimaryKey(long p2pActivityId) {
		return fetchByPrimaryKey((Serializable)p2pActivityId);
	}

	/**
	 * Returns all the p2p activities.
	 *
	 * @return the p2p activities
	 */
	@Override
	public List<P2PActivity> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the p2p activities.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of p2p activities
	 * @param end the upper bound of the range of p2p activities (not inclusive)
	 * @return the range of p2p activities
	 */
	@Override
	public List<P2PActivity> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the p2p activities.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of p2p activities
	 * @param end the upper bound of the range of p2p activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of p2p activities
	 */
	@Override
	public List<P2PActivity> findAll(
		int start, int end, OrderByComparator<P2PActivity> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the p2p activities.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of p2p activities
	 * @param end the upper bound of the range of p2p activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of p2p activities
	 */
	@Override
	public List<P2PActivity> findAll(
		int start, int end, OrderByComparator<P2PActivity> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindAll;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<P2PActivity> list = null;

		if (retrieveFromCache) {
			list = (List<P2PActivity>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_P2PACTIVITY);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_P2PACTIVITY;

				if (pagination) {
					sql = sql.concat(P2PActivityModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<P2PActivity>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<P2PActivity>)QueryUtil.list(
						q, getDialect(), start, end);
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
	 * Removes all the p2p activities from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (P2PActivity p2pActivity : findAll()) {
			remove(p2pActivity);
		}
	}

	/**
	 * Returns the number of p2p activities.
	 *
	 * @return the number of p2p activities
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_P2PACTIVITY);

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
		return "p2pActivityId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_P2PACTIVITY;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return P2PActivityModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the p2p activity persistence.
	 */
	@Activate
	public void activate() {
		P2PActivityModelImpl.setEntityCacheEnabled(entityCacheEnabled);
		P2PActivityModelImpl.setFinderCacheEnabled(finderCacheEnabled);

		_finderPathWithPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, P2PActivityImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, P2PActivityImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, P2PActivityImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, P2PActivityImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()},
			P2PActivityModelImpl.UUID_COLUMN_BITMASK |
			P2PActivityModelImpl.COUNTCORRECTIONS_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()});

		_finderPathFetchByUUID_G = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, P2PActivityImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()},
			P2PActivityModelImpl.UUID_COLUMN_BITMASK |
			P2PActivityModelImpl.GROUPID_COLUMN_BITMASK);

		_finderPathCountByUUID_G = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByUuid_C = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, P2PActivityImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, P2PActivityImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			P2PActivityModelImpl.UUID_COLUMN_BITMASK |
			P2PActivityModelImpl.COMPANYID_COLUMN_BITMASK |
			P2PActivityModelImpl.COUNTCORRECTIONS_COLUMN_BITMASK);

		_finderPathCountByUuid_C = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathFetchByActIdUserId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, P2PActivityImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByActIdUserId",
			new String[] {Long.class.getName(), Long.class.getName()},
			P2PActivityModelImpl.ACTID_COLUMN_BITMASK |
			P2PActivityModelImpl.USERID_COLUMN_BITMASK);

		_finderPathCountByActIdUserId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByActIdUserId",
			new String[] {Long.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByActId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, P2PActivityImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByActId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByActId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, P2PActivityImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByActId",
			new String[] {Long.class.getName()},
			P2PActivityModelImpl.ACTID_COLUMN_BITMASK |
			P2PActivityModelImpl.COUNTCORRECTIONS_COLUMN_BITMASK);

		_finderPathCountByActId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByActId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByUserId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, P2PActivityImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUserId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, P2PActivityImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
			new String[] {Long.class.getName()},
			P2PActivityModelImpl.USERID_COLUMN_BITMASK |
			P2PActivityModelImpl.COUNTCORRECTIONS_COLUMN_BITMASK);

		_finderPathCountByUserId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByAsignationsCompleted = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, P2PActivityImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByAsignationsCompleted",
			new String[] {
				Boolean.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByAsignationsCompleted = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, P2PActivityImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByAsignationsCompleted",
			new String[] {Boolean.class.getName()},
			P2PActivityModelImpl.ASIGNATIONSCOMPLETED_COLUMN_BITMASK |
			P2PActivityModelImpl.COUNTCORRECTIONS_COLUMN_BITMASK);

		_finderPathCountByAsignationsCompleted = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByAsignationsCompleted",
			new String[] {Boolean.class.getName()});
	}

	@Deactivate
	public void deactivate() {
		entityCache.removeCache(P2PActivityImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	@Reference(
		target = PTPPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
		super.setConfiguration(configuration);

		_columnBitmaskEnabled = GetterUtil.getBoolean(
			configuration.get(
				"value.object.column.bitmask.enabled.com.ted.lms.learning.activity.p2p.model.P2PActivity"),
			true);
	}

	@Override
	@Reference(
		target = PTPPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = PTPPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	private boolean _columnBitmaskEnabled;

	@Reference(service = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_P2PACTIVITY =
		"SELECT p2pActivity FROM P2PActivity p2pActivity";

	private static final String _SQL_SELECT_P2PACTIVITY_WHERE =
		"SELECT p2pActivity FROM P2PActivity p2pActivity WHERE ";

	private static final String _SQL_COUNT_P2PACTIVITY =
		"SELECT COUNT(p2pActivity) FROM P2PActivity p2pActivity";

	private static final String _SQL_COUNT_P2PACTIVITY_WHERE =
		"SELECT COUNT(p2pActivity) FROM P2PActivity p2pActivity WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "p2pActivity.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No P2PActivity exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No P2PActivity exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		P2PActivityPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid", "date"});

}