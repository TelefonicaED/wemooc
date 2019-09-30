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

import com.ted.lms.learning.activity.p2p.exception.NoSuchP2PActivityCorrectionsException;
import com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections;
import com.ted.lms.learning.activity.p2p.model.impl.P2PActivityCorrectionsImpl;
import com.ted.lms.learning.activity.p2p.model.impl.P2PActivityCorrectionsModelImpl;
import com.ted.lms.learning.activity.p2p.service.persistence.P2PActivityCorrectionsPersistence;
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
 * The persistence implementation for the p2p activity corrections service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = P2PActivityCorrectionsPersistence.class)
@ProviderType
public class P2PActivityCorrectionsPersistenceImpl
	extends BasePersistenceImpl<P2PActivityCorrections>
	implements P2PActivityCorrectionsPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>P2PActivityCorrectionsUtil</code> to access the p2p activity corrections persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		P2PActivityCorrectionsImpl.class.getName();

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
	 * Returns all the p2p activity correctionses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching p2p activity correctionses
	 */
	@Override
	public List<P2PActivityCorrections> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the p2p activity correctionses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityCorrectionsModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of p2p activity correctionses
	 * @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	 * @return the range of matching p2p activity correctionses
	 */
	@Override
	public List<P2PActivityCorrections> findByUuid(
		String uuid, int start, int end) {

		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the p2p activity correctionses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityCorrectionsModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of p2p activity correctionses
	 * @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching p2p activity correctionses
	 */
	@Override
	public List<P2PActivityCorrections> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<P2PActivityCorrections> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the p2p activity correctionses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityCorrectionsModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of p2p activity correctionses
	 * @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching p2p activity correctionses
	 */
	@Override
	public List<P2PActivityCorrections> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<P2PActivityCorrections> orderByComparator,
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

		List<P2PActivityCorrections> list = null;

		if (useFinderCache) {
			list = (List<P2PActivityCorrections>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (P2PActivityCorrections p2pActivityCorrections : list) {
					if (!uuid.equals(p2pActivityCorrections.getUuid())) {
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

			query.append(_SQL_SELECT_P2PACTIVITYCORRECTIONS_WHERE);

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
				query.append(P2PActivityCorrectionsModelImpl.ORDER_BY_JPQL);
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
					list = (List<P2PActivityCorrections>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<P2PActivityCorrections>)QueryUtil.list(
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
	 * Returns the first p2p activity corrections in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching p2p activity corrections
	 * @throws NoSuchP2PActivityCorrectionsException if a matching p2p activity corrections could not be found
	 */
	@Override
	public P2PActivityCorrections findByUuid_First(
			String uuid,
			OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws NoSuchP2PActivityCorrectionsException {

		P2PActivityCorrections p2pActivityCorrections = fetchByUuid_First(
			uuid, orderByComparator);

		if (p2pActivityCorrections != null) {
			return p2pActivityCorrections;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchP2PActivityCorrectionsException(msg.toString());
	}

	/**
	 * Returns the first p2p activity corrections in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching p2p activity corrections, or <code>null</code> if a matching p2p activity corrections could not be found
	 */
	@Override
	public P2PActivityCorrections fetchByUuid_First(
		String uuid,
		OrderByComparator<P2PActivityCorrections> orderByComparator) {

		List<P2PActivityCorrections> list = findByUuid(
			uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last p2p activity corrections in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching p2p activity corrections
	 * @throws NoSuchP2PActivityCorrectionsException if a matching p2p activity corrections could not be found
	 */
	@Override
	public P2PActivityCorrections findByUuid_Last(
			String uuid,
			OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws NoSuchP2PActivityCorrectionsException {

		P2PActivityCorrections p2pActivityCorrections = fetchByUuid_Last(
			uuid, orderByComparator);

		if (p2pActivityCorrections != null) {
			return p2pActivityCorrections;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchP2PActivityCorrectionsException(msg.toString());
	}

	/**
	 * Returns the last p2p activity corrections in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching p2p activity corrections, or <code>null</code> if a matching p2p activity corrections could not be found
	 */
	@Override
	public P2PActivityCorrections fetchByUuid_Last(
		String uuid,
		OrderByComparator<P2PActivityCorrections> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<P2PActivityCorrections> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the p2p activity correctionses before and after the current p2p activity corrections in the ordered set where uuid = &#63;.
	 *
	 * @param p2pActivityCorrectionsId the primary key of the current p2p activity corrections
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next p2p activity corrections
	 * @throws NoSuchP2PActivityCorrectionsException if a p2p activity corrections with the primary key could not be found
	 */
	@Override
	public P2PActivityCorrections[] findByUuid_PrevAndNext(
			long p2pActivityCorrectionsId, String uuid,
			OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws NoSuchP2PActivityCorrectionsException {

		uuid = Objects.toString(uuid, "");

		P2PActivityCorrections p2pActivityCorrections = findByPrimaryKey(
			p2pActivityCorrectionsId);

		Session session = null;

		try {
			session = openSession();

			P2PActivityCorrections[] array = new P2PActivityCorrectionsImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, p2pActivityCorrections, uuid, orderByComparator, true);

			array[1] = p2pActivityCorrections;

			array[2] = getByUuid_PrevAndNext(
				session, p2pActivityCorrections, uuid, orderByComparator,
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

	protected P2PActivityCorrections getByUuid_PrevAndNext(
		Session session, P2PActivityCorrections p2pActivityCorrections,
		String uuid,
		OrderByComparator<P2PActivityCorrections> orderByComparator,
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

		query.append(_SQL_SELECT_P2PACTIVITYCORRECTIONS_WHERE);

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
			query.append(P2PActivityCorrectionsModelImpl.ORDER_BY_JPQL);
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
						p2pActivityCorrections)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<P2PActivityCorrections> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the p2p activity correctionses where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (P2PActivityCorrections p2pActivityCorrections :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(p2pActivityCorrections);
		}
	}

	/**
	 * Returns the number of p2p activity correctionses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching p2p activity correctionses
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_P2PACTIVITYCORRECTIONS_WHERE);

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
		"p2pActivityCorrections.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(p2pActivityCorrections.uuid IS NULL OR p2pActivityCorrections.uuid = '')";

	private FinderPath _finderPathFetchByUUID_G;
	private FinderPath _finderPathCountByUUID_G;

	/**
	 * Returns the p2p activity corrections where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchP2PActivityCorrectionsException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching p2p activity corrections
	 * @throws NoSuchP2PActivityCorrectionsException if a matching p2p activity corrections could not be found
	 */
	@Override
	public P2PActivityCorrections findByUUID_G(String uuid, long groupId)
		throws NoSuchP2PActivityCorrectionsException {

		P2PActivityCorrections p2pActivityCorrections = fetchByUUID_G(
			uuid, groupId);

		if (p2pActivityCorrections == null) {
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

			throw new NoSuchP2PActivityCorrectionsException(msg.toString());
		}

		return p2pActivityCorrections;
	}

	/**
	 * Returns the p2p activity corrections where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching p2p activity corrections, or <code>null</code> if a matching p2p activity corrections could not be found
	 */
	@Override
	public P2PActivityCorrections fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the p2p activity corrections where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching p2p activity corrections, or <code>null</code> if a matching p2p activity corrections could not be found
	 */
	@Override
	public P2PActivityCorrections fetchByUUID_G(
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

		if (result instanceof P2PActivityCorrections) {
			P2PActivityCorrections p2pActivityCorrections =
				(P2PActivityCorrections)result;

			if (!Objects.equals(uuid, p2pActivityCorrections.getUuid()) ||
				(groupId != p2pActivityCorrections.getGroupId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_P2PACTIVITYCORRECTIONS_WHERE);

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

				List<P2PActivityCorrections> list = q.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByUUID_G, finderArgs, list);
					}
				}
				else {
					P2PActivityCorrections p2pActivityCorrections = list.get(0);

					result = p2pActivityCorrections;

					cacheResult(p2pActivityCorrections);
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
			return (P2PActivityCorrections)result;
		}
	}

	/**
	 * Removes the p2p activity corrections where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the p2p activity corrections that was removed
	 */
	@Override
	public P2PActivityCorrections removeByUUID_G(String uuid, long groupId)
		throws NoSuchP2PActivityCorrectionsException {

		P2PActivityCorrections p2pActivityCorrections = findByUUID_G(
			uuid, groupId);

		return remove(p2pActivityCorrections);
	}

	/**
	 * Returns the number of p2p activity correctionses where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching p2p activity correctionses
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUUID_G;

		Object[] finderArgs = new Object[] {uuid, groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_P2PACTIVITYCORRECTIONS_WHERE);

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
		"p2pActivityCorrections.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_G_UUID_3 =
		"(p2pActivityCorrections.uuid IS NULL OR p2pActivityCorrections.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 =
		"p2pActivityCorrections.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the p2p activity correctionses where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching p2p activity correctionses
	 */
	@Override
	public List<P2PActivityCorrections> findByUuid_C(
		String uuid, long companyId) {

		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the p2p activity correctionses where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityCorrectionsModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of p2p activity correctionses
	 * @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	 * @return the range of matching p2p activity correctionses
	 */
	@Override
	public List<P2PActivityCorrections> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the p2p activity correctionses where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityCorrectionsModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of p2p activity correctionses
	 * @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching p2p activity correctionses
	 */
	@Override
	public List<P2PActivityCorrections> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<P2PActivityCorrections> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the p2p activity correctionses where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityCorrectionsModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of p2p activity correctionses
	 * @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching p2p activity correctionses
	 */
	@Override
	public List<P2PActivityCorrections> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<P2PActivityCorrections> orderByComparator,
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

		List<P2PActivityCorrections> list = null;

		if (useFinderCache) {
			list = (List<P2PActivityCorrections>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (P2PActivityCorrections p2pActivityCorrections : list) {
					if (!uuid.equals(p2pActivityCorrections.getUuid()) ||
						(companyId != p2pActivityCorrections.getCompanyId())) {

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

			query.append(_SQL_SELECT_P2PACTIVITYCORRECTIONS_WHERE);

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
				query.append(P2PActivityCorrectionsModelImpl.ORDER_BY_JPQL);
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
					list = (List<P2PActivityCorrections>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<P2PActivityCorrections>)QueryUtil.list(
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
	 * Returns the first p2p activity corrections in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching p2p activity corrections
	 * @throws NoSuchP2PActivityCorrectionsException if a matching p2p activity corrections could not be found
	 */
	@Override
	public P2PActivityCorrections findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws NoSuchP2PActivityCorrectionsException {

		P2PActivityCorrections p2pActivityCorrections = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (p2pActivityCorrections != null) {
			return p2pActivityCorrections;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchP2PActivityCorrectionsException(msg.toString());
	}

	/**
	 * Returns the first p2p activity corrections in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching p2p activity corrections, or <code>null</code> if a matching p2p activity corrections could not be found
	 */
	@Override
	public P2PActivityCorrections fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<P2PActivityCorrections> orderByComparator) {

		List<P2PActivityCorrections> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last p2p activity corrections in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching p2p activity corrections
	 * @throws NoSuchP2PActivityCorrectionsException if a matching p2p activity corrections could not be found
	 */
	@Override
	public P2PActivityCorrections findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws NoSuchP2PActivityCorrectionsException {

		P2PActivityCorrections p2pActivityCorrections = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (p2pActivityCorrections != null) {
			return p2pActivityCorrections;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchP2PActivityCorrectionsException(msg.toString());
	}

	/**
	 * Returns the last p2p activity corrections in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching p2p activity corrections, or <code>null</code> if a matching p2p activity corrections could not be found
	 */
	@Override
	public P2PActivityCorrections fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<P2PActivityCorrections> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<P2PActivityCorrections> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the p2p activity correctionses before and after the current p2p activity corrections in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param p2pActivityCorrectionsId the primary key of the current p2p activity corrections
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next p2p activity corrections
	 * @throws NoSuchP2PActivityCorrectionsException if a p2p activity corrections with the primary key could not be found
	 */
	@Override
	public P2PActivityCorrections[] findByUuid_C_PrevAndNext(
			long p2pActivityCorrectionsId, String uuid, long companyId,
			OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws NoSuchP2PActivityCorrectionsException {

		uuid = Objects.toString(uuid, "");

		P2PActivityCorrections p2pActivityCorrections = findByPrimaryKey(
			p2pActivityCorrectionsId);

		Session session = null;

		try {
			session = openSession();

			P2PActivityCorrections[] array = new P2PActivityCorrectionsImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, p2pActivityCorrections, uuid, companyId,
				orderByComparator, true);

			array[1] = p2pActivityCorrections;

			array[2] = getByUuid_C_PrevAndNext(
				session, p2pActivityCorrections, uuid, companyId,
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

	protected P2PActivityCorrections getByUuid_C_PrevAndNext(
		Session session, P2PActivityCorrections p2pActivityCorrections,
		String uuid, long companyId,
		OrderByComparator<P2PActivityCorrections> orderByComparator,
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

		query.append(_SQL_SELECT_P2PACTIVITYCORRECTIONS_WHERE);

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
			query.append(P2PActivityCorrectionsModelImpl.ORDER_BY_JPQL);
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
						p2pActivityCorrections)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<P2PActivityCorrections> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the p2p activity correctionses where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (P2PActivityCorrections p2pActivityCorrections :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(p2pActivityCorrections);
		}
	}

	/**
	 * Returns the number of p2p activity correctionses where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching p2p activity correctionses
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_P2PACTIVITYCORRECTIONS_WHERE);

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
		"p2pActivityCorrections.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(p2pActivityCorrections.uuid IS NULL OR p2pActivityCorrections.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"p2pActivityCorrections.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByP2PActivityId;
	private FinderPath _finderPathWithoutPaginationFindByP2PActivityId;
	private FinderPath _finderPathCountByP2PActivityId;

	/**
	 * Returns all the p2p activity correctionses where p2pActivityId = &#63;.
	 *
	 * @param p2pActivityId the p2p activity ID
	 * @return the matching p2p activity correctionses
	 */
	@Override
	public List<P2PActivityCorrections> findByP2PActivityId(
		long p2pActivityId) {

		return findByP2PActivityId(
			p2pActivityId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the p2p activity correctionses where p2pActivityId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityCorrectionsModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param p2pActivityId the p2p activity ID
	 * @param start the lower bound of the range of p2p activity correctionses
	 * @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	 * @return the range of matching p2p activity correctionses
	 */
	@Override
	public List<P2PActivityCorrections> findByP2PActivityId(
		long p2pActivityId, int start, int end) {

		return findByP2PActivityId(p2pActivityId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the p2p activity correctionses where p2pActivityId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityCorrectionsModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param p2pActivityId the p2p activity ID
	 * @param start the lower bound of the range of p2p activity correctionses
	 * @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching p2p activity correctionses
	 */
	@Override
	public List<P2PActivityCorrections> findByP2PActivityId(
		long p2pActivityId, int start, int end,
		OrderByComparator<P2PActivityCorrections> orderByComparator) {

		return findByP2PActivityId(
			p2pActivityId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the p2p activity correctionses where p2pActivityId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityCorrectionsModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param p2pActivityId the p2p activity ID
	 * @param start the lower bound of the range of p2p activity correctionses
	 * @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching p2p activity correctionses
	 */
	@Override
	public List<P2PActivityCorrections> findByP2PActivityId(
		long p2pActivityId, int start, int end,
		OrderByComparator<P2PActivityCorrections> orderByComparator,
		boolean useFinderCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByP2PActivityId;
				finderArgs = new Object[] {p2pActivityId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByP2PActivityId;
			finderArgs = new Object[] {
				p2pActivityId, start, end, orderByComparator
			};
		}

		List<P2PActivityCorrections> list = null;

		if (useFinderCache) {
			list = (List<P2PActivityCorrections>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (P2PActivityCorrections p2pActivityCorrections : list) {
					if ((p2pActivityId !=
							p2pActivityCorrections.getP2pActivityId())) {

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

			query.append(_SQL_SELECT_P2PACTIVITYCORRECTIONS_WHERE);

			query.append(_FINDER_COLUMN_P2PACTIVITYID_P2PACTIVITYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(P2PActivityCorrectionsModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(p2pActivityId);

				if (!pagination) {
					list = (List<P2PActivityCorrections>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<P2PActivityCorrections>)QueryUtil.list(
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
	 * Returns the first p2p activity corrections in the ordered set where p2pActivityId = &#63;.
	 *
	 * @param p2pActivityId the p2p activity ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching p2p activity corrections
	 * @throws NoSuchP2PActivityCorrectionsException if a matching p2p activity corrections could not be found
	 */
	@Override
	public P2PActivityCorrections findByP2PActivityId_First(
			long p2pActivityId,
			OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws NoSuchP2PActivityCorrectionsException {

		P2PActivityCorrections p2pActivityCorrections =
			fetchByP2PActivityId_First(p2pActivityId, orderByComparator);

		if (p2pActivityCorrections != null) {
			return p2pActivityCorrections;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("p2pActivityId=");
		msg.append(p2pActivityId);

		msg.append("}");

		throw new NoSuchP2PActivityCorrectionsException(msg.toString());
	}

	/**
	 * Returns the first p2p activity corrections in the ordered set where p2pActivityId = &#63;.
	 *
	 * @param p2pActivityId the p2p activity ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching p2p activity corrections, or <code>null</code> if a matching p2p activity corrections could not be found
	 */
	@Override
	public P2PActivityCorrections fetchByP2PActivityId_First(
		long p2pActivityId,
		OrderByComparator<P2PActivityCorrections> orderByComparator) {

		List<P2PActivityCorrections> list = findByP2PActivityId(
			p2pActivityId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last p2p activity corrections in the ordered set where p2pActivityId = &#63;.
	 *
	 * @param p2pActivityId the p2p activity ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching p2p activity corrections
	 * @throws NoSuchP2PActivityCorrectionsException if a matching p2p activity corrections could not be found
	 */
	@Override
	public P2PActivityCorrections findByP2PActivityId_Last(
			long p2pActivityId,
			OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws NoSuchP2PActivityCorrectionsException {

		P2PActivityCorrections p2pActivityCorrections =
			fetchByP2PActivityId_Last(p2pActivityId, orderByComparator);

		if (p2pActivityCorrections != null) {
			return p2pActivityCorrections;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("p2pActivityId=");
		msg.append(p2pActivityId);

		msg.append("}");

		throw new NoSuchP2PActivityCorrectionsException(msg.toString());
	}

	/**
	 * Returns the last p2p activity corrections in the ordered set where p2pActivityId = &#63;.
	 *
	 * @param p2pActivityId the p2p activity ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching p2p activity corrections, or <code>null</code> if a matching p2p activity corrections could not be found
	 */
	@Override
	public P2PActivityCorrections fetchByP2PActivityId_Last(
		long p2pActivityId,
		OrderByComparator<P2PActivityCorrections> orderByComparator) {

		int count = countByP2PActivityId(p2pActivityId);

		if (count == 0) {
			return null;
		}

		List<P2PActivityCorrections> list = findByP2PActivityId(
			p2pActivityId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the p2p activity correctionses before and after the current p2p activity corrections in the ordered set where p2pActivityId = &#63;.
	 *
	 * @param p2pActivityCorrectionsId the primary key of the current p2p activity corrections
	 * @param p2pActivityId the p2p activity ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next p2p activity corrections
	 * @throws NoSuchP2PActivityCorrectionsException if a p2p activity corrections with the primary key could not be found
	 */
	@Override
	public P2PActivityCorrections[] findByP2PActivityId_PrevAndNext(
			long p2pActivityCorrectionsId, long p2pActivityId,
			OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws NoSuchP2PActivityCorrectionsException {

		P2PActivityCorrections p2pActivityCorrections = findByPrimaryKey(
			p2pActivityCorrectionsId);

		Session session = null;

		try {
			session = openSession();

			P2PActivityCorrections[] array = new P2PActivityCorrectionsImpl[3];

			array[0] = getByP2PActivityId_PrevAndNext(
				session, p2pActivityCorrections, p2pActivityId,
				orderByComparator, true);

			array[1] = p2pActivityCorrections;

			array[2] = getByP2PActivityId_PrevAndNext(
				session, p2pActivityCorrections, p2pActivityId,
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

	protected P2PActivityCorrections getByP2PActivityId_PrevAndNext(
		Session session, P2PActivityCorrections p2pActivityCorrections,
		long p2pActivityId,
		OrderByComparator<P2PActivityCorrections> orderByComparator,
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

		query.append(_SQL_SELECT_P2PACTIVITYCORRECTIONS_WHERE);

		query.append(_FINDER_COLUMN_P2PACTIVITYID_P2PACTIVITYID_2);

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
			query.append(P2PActivityCorrectionsModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(p2pActivityId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						p2pActivityCorrections)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<P2PActivityCorrections> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the p2p activity correctionses where p2pActivityId = &#63; from the database.
	 *
	 * @param p2pActivityId the p2p activity ID
	 */
	@Override
	public void removeByP2PActivityId(long p2pActivityId) {
		for (P2PActivityCorrections p2pActivityCorrections :
				findByP2PActivityId(
					p2pActivityId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(p2pActivityCorrections);
		}
	}

	/**
	 * Returns the number of p2p activity correctionses where p2pActivityId = &#63;.
	 *
	 * @param p2pActivityId the p2p activity ID
	 * @return the number of matching p2p activity correctionses
	 */
	@Override
	public int countByP2PActivityId(long p2pActivityId) {
		FinderPath finderPath = _finderPathCountByP2PActivityId;

		Object[] finderArgs = new Object[] {p2pActivityId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_P2PACTIVITYCORRECTIONS_WHERE);

			query.append(_FINDER_COLUMN_P2PACTIVITYID_P2PACTIVITYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(p2pActivityId);

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

	private static final String _FINDER_COLUMN_P2PACTIVITYID_P2PACTIVITYID_2 =
		"p2pActivityCorrections.p2pActivityId = ?";

	private FinderPath _finderPathWithPaginationFindByActIdAndUserId;
	private FinderPath _finderPathWithoutPaginationFindByActIdAndUserId;
	private FinderPath _finderPathCountByActIdAndUserId;

	/**
	 * Returns all the p2p activity correctionses where actId = &#63; and userId = &#63;.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @return the matching p2p activity correctionses
	 */
	@Override
	public List<P2PActivityCorrections> findByActIdAndUserId(
		long actId, long userId) {

		return findByActIdAndUserId(
			actId, userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the p2p activity correctionses where actId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityCorrectionsModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of p2p activity correctionses
	 * @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	 * @return the range of matching p2p activity correctionses
	 */
	@Override
	public List<P2PActivityCorrections> findByActIdAndUserId(
		long actId, long userId, int start, int end) {

		return findByActIdAndUserId(actId, userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the p2p activity correctionses where actId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityCorrectionsModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of p2p activity correctionses
	 * @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching p2p activity correctionses
	 */
	@Override
	public List<P2PActivityCorrections> findByActIdAndUserId(
		long actId, long userId, int start, int end,
		OrderByComparator<P2PActivityCorrections> orderByComparator) {

		return findByActIdAndUserId(
			actId, userId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the p2p activity correctionses where actId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityCorrectionsModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of p2p activity correctionses
	 * @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching p2p activity correctionses
	 */
	@Override
	public List<P2PActivityCorrections> findByActIdAndUserId(
		long actId, long userId, int start, int end,
		OrderByComparator<P2PActivityCorrections> orderByComparator,
		boolean useFinderCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByActIdAndUserId;
				finderArgs = new Object[] {actId, userId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByActIdAndUserId;
			finderArgs = new Object[] {
				actId, userId, start, end, orderByComparator
			};
		}

		List<P2PActivityCorrections> list = null;

		if (useFinderCache) {
			list = (List<P2PActivityCorrections>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (P2PActivityCorrections p2pActivityCorrections : list) {
					if ((actId != p2pActivityCorrections.getActId()) ||
						(userId != p2pActivityCorrections.getUserId())) {

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

			query.append(_SQL_SELECT_P2PACTIVITYCORRECTIONS_WHERE);

			query.append(_FINDER_COLUMN_ACTIDANDUSERID_ACTID_2);

			query.append(_FINDER_COLUMN_ACTIDANDUSERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(P2PActivityCorrectionsModelImpl.ORDER_BY_JPQL);
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
					list = (List<P2PActivityCorrections>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<P2PActivityCorrections>)QueryUtil.list(
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
	 * Returns the first p2p activity corrections in the ordered set where actId = &#63; and userId = &#63;.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching p2p activity corrections
	 * @throws NoSuchP2PActivityCorrectionsException if a matching p2p activity corrections could not be found
	 */
	@Override
	public P2PActivityCorrections findByActIdAndUserId_First(
			long actId, long userId,
			OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws NoSuchP2PActivityCorrectionsException {

		P2PActivityCorrections p2pActivityCorrections =
			fetchByActIdAndUserId_First(actId, userId, orderByComparator);

		if (p2pActivityCorrections != null) {
			return p2pActivityCorrections;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("actId=");
		msg.append(actId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append("}");

		throw new NoSuchP2PActivityCorrectionsException(msg.toString());
	}

	/**
	 * Returns the first p2p activity corrections in the ordered set where actId = &#63; and userId = &#63;.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching p2p activity corrections, or <code>null</code> if a matching p2p activity corrections could not be found
	 */
	@Override
	public P2PActivityCorrections fetchByActIdAndUserId_First(
		long actId, long userId,
		OrderByComparator<P2PActivityCorrections> orderByComparator) {

		List<P2PActivityCorrections> list = findByActIdAndUserId(
			actId, userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last p2p activity corrections in the ordered set where actId = &#63; and userId = &#63;.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching p2p activity corrections
	 * @throws NoSuchP2PActivityCorrectionsException if a matching p2p activity corrections could not be found
	 */
	@Override
	public P2PActivityCorrections findByActIdAndUserId_Last(
			long actId, long userId,
			OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws NoSuchP2PActivityCorrectionsException {

		P2PActivityCorrections p2pActivityCorrections =
			fetchByActIdAndUserId_Last(actId, userId, orderByComparator);

		if (p2pActivityCorrections != null) {
			return p2pActivityCorrections;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("actId=");
		msg.append(actId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append("}");

		throw new NoSuchP2PActivityCorrectionsException(msg.toString());
	}

	/**
	 * Returns the last p2p activity corrections in the ordered set where actId = &#63; and userId = &#63;.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching p2p activity corrections, or <code>null</code> if a matching p2p activity corrections could not be found
	 */
	@Override
	public P2PActivityCorrections fetchByActIdAndUserId_Last(
		long actId, long userId,
		OrderByComparator<P2PActivityCorrections> orderByComparator) {

		int count = countByActIdAndUserId(actId, userId);

		if (count == 0) {
			return null;
		}

		List<P2PActivityCorrections> list = findByActIdAndUserId(
			actId, userId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the p2p activity correctionses before and after the current p2p activity corrections in the ordered set where actId = &#63; and userId = &#63;.
	 *
	 * @param p2pActivityCorrectionsId the primary key of the current p2p activity corrections
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next p2p activity corrections
	 * @throws NoSuchP2PActivityCorrectionsException if a p2p activity corrections with the primary key could not be found
	 */
	@Override
	public P2PActivityCorrections[] findByActIdAndUserId_PrevAndNext(
			long p2pActivityCorrectionsId, long actId, long userId,
			OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws NoSuchP2PActivityCorrectionsException {

		P2PActivityCorrections p2pActivityCorrections = findByPrimaryKey(
			p2pActivityCorrectionsId);

		Session session = null;

		try {
			session = openSession();

			P2PActivityCorrections[] array = new P2PActivityCorrectionsImpl[3];

			array[0] = getByActIdAndUserId_PrevAndNext(
				session, p2pActivityCorrections, actId, userId,
				orderByComparator, true);

			array[1] = p2pActivityCorrections;

			array[2] = getByActIdAndUserId_PrevAndNext(
				session, p2pActivityCorrections, actId, userId,
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

	protected P2PActivityCorrections getByActIdAndUserId_PrevAndNext(
		Session session, P2PActivityCorrections p2pActivityCorrections,
		long actId, long userId,
		OrderByComparator<P2PActivityCorrections> orderByComparator,
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

		query.append(_SQL_SELECT_P2PACTIVITYCORRECTIONS_WHERE);

		query.append(_FINDER_COLUMN_ACTIDANDUSERID_ACTID_2);

		query.append(_FINDER_COLUMN_ACTIDANDUSERID_USERID_2);

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
			query.append(P2PActivityCorrectionsModelImpl.ORDER_BY_JPQL);
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
						p2pActivityCorrections)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<P2PActivityCorrections> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the p2p activity correctionses where actId = &#63; and userId = &#63; from the database.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 */
	@Override
	public void removeByActIdAndUserId(long actId, long userId) {
		for (P2PActivityCorrections p2pActivityCorrections :
				findByActIdAndUserId(
					actId, userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(p2pActivityCorrections);
		}
	}

	/**
	 * Returns the number of p2p activity correctionses where actId = &#63; and userId = &#63;.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @return the number of matching p2p activity correctionses
	 */
	@Override
	public int countByActIdAndUserId(long actId, long userId) {
		FinderPath finderPath = _finderPathCountByActIdAndUserId;

		Object[] finderArgs = new Object[] {actId, userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_P2PACTIVITYCORRECTIONS_WHERE);

			query.append(_FINDER_COLUMN_ACTIDANDUSERID_ACTID_2);

			query.append(_FINDER_COLUMN_ACTIDANDUSERID_USERID_2);

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

	private static final String _FINDER_COLUMN_ACTIDANDUSERID_ACTID_2 =
		"p2pActivityCorrections.actId = ? AND ";

	private static final String _FINDER_COLUMN_ACTIDANDUSERID_USERID_2 =
		"p2pActivityCorrections.userId = ?";

	private FinderPath _finderPathFetchByP2PActivityIdAndUserId;
	private FinderPath _finderPathCountByP2PActivityIdAndUserId;

	/**
	 * Returns the p2p activity corrections where p2pActivityId = &#63; and userId = &#63; or throws a <code>NoSuchP2PActivityCorrectionsException</code> if it could not be found.
	 *
	 * @param p2pActivityId the p2p activity ID
	 * @param userId the user ID
	 * @return the matching p2p activity corrections
	 * @throws NoSuchP2PActivityCorrectionsException if a matching p2p activity corrections could not be found
	 */
	@Override
	public P2PActivityCorrections findByP2PActivityIdAndUserId(
			long p2pActivityId, long userId)
		throws NoSuchP2PActivityCorrectionsException {

		P2PActivityCorrections p2pActivityCorrections =
			fetchByP2PActivityIdAndUserId(p2pActivityId, userId);

		if (p2pActivityCorrections == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("p2pActivityId=");
			msg.append(p2pActivityId);

			msg.append(", userId=");
			msg.append(userId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchP2PActivityCorrectionsException(msg.toString());
		}

		return p2pActivityCorrections;
	}

	/**
	 * Returns the p2p activity corrections where p2pActivityId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param p2pActivityId the p2p activity ID
	 * @param userId the user ID
	 * @return the matching p2p activity corrections, or <code>null</code> if a matching p2p activity corrections could not be found
	 */
	@Override
	public P2PActivityCorrections fetchByP2PActivityIdAndUserId(
		long p2pActivityId, long userId) {

		return fetchByP2PActivityIdAndUserId(p2pActivityId, userId, true);
	}

	/**
	 * Returns the p2p activity corrections where p2pActivityId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param p2pActivityId the p2p activity ID
	 * @param userId the user ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching p2p activity corrections, or <code>null</code> if a matching p2p activity corrections could not be found
	 */
	@Override
	public P2PActivityCorrections fetchByP2PActivityIdAndUserId(
		long p2pActivityId, long userId, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {p2pActivityId, userId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByP2PActivityIdAndUserId, finderArgs, this);
		}

		if (result instanceof P2PActivityCorrections) {
			P2PActivityCorrections p2pActivityCorrections =
				(P2PActivityCorrections)result;

			if ((p2pActivityId != p2pActivityCorrections.getP2pActivityId()) ||
				(userId != p2pActivityCorrections.getUserId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_P2PACTIVITYCORRECTIONS_WHERE);

			query.append(_FINDER_COLUMN_P2PACTIVITYIDANDUSERID_P2PACTIVITYID_2);

			query.append(_FINDER_COLUMN_P2PACTIVITYIDANDUSERID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(p2pActivityId);

				qPos.add(userId);

				List<P2PActivityCorrections> list = q.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByP2PActivityIdAndUserId,
							finderArgs, list);
					}
				}
				else {
					P2PActivityCorrections p2pActivityCorrections = list.get(0);

					result = p2pActivityCorrections;

					cacheResult(p2pActivityCorrections);
				}
			}
			catch (Exception e) {
				if (useFinderCache) {
					finderCache.removeResult(
						_finderPathFetchByP2PActivityIdAndUserId, finderArgs);
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
			return (P2PActivityCorrections)result;
		}
	}

	/**
	 * Removes the p2p activity corrections where p2pActivityId = &#63; and userId = &#63; from the database.
	 *
	 * @param p2pActivityId the p2p activity ID
	 * @param userId the user ID
	 * @return the p2p activity corrections that was removed
	 */
	@Override
	public P2PActivityCorrections removeByP2PActivityIdAndUserId(
			long p2pActivityId, long userId)
		throws NoSuchP2PActivityCorrectionsException {

		P2PActivityCorrections p2pActivityCorrections =
			findByP2PActivityIdAndUserId(p2pActivityId, userId);

		return remove(p2pActivityCorrections);
	}

	/**
	 * Returns the number of p2p activity correctionses where p2pActivityId = &#63; and userId = &#63;.
	 *
	 * @param p2pActivityId the p2p activity ID
	 * @param userId the user ID
	 * @return the number of matching p2p activity correctionses
	 */
	@Override
	public int countByP2PActivityIdAndUserId(long p2pActivityId, long userId) {
		FinderPath finderPath = _finderPathCountByP2PActivityIdAndUserId;

		Object[] finderArgs = new Object[] {p2pActivityId, userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_P2PACTIVITYCORRECTIONS_WHERE);

			query.append(_FINDER_COLUMN_P2PACTIVITYIDANDUSERID_P2PACTIVITYID_2);

			query.append(_FINDER_COLUMN_P2PACTIVITYIDANDUSERID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(p2pActivityId);

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

	private static final String
		_FINDER_COLUMN_P2PACTIVITYIDANDUSERID_P2PACTIVITYID_2 =
			"p2pActivityCorrections.p2pActivityId = ? AND ";

	private static final String _FINDER_COLUMN_P2PACTIVITYIDANDUSERID_USERID_2 =
		"p2pActivityCorrections.userId = ?";

	private FinderPath _finderPathWithPaginationFindByUserId;
	private FinderPath _finderPathWithoutPaginationFindByUserId;
	private FinderPath _finderPathCountByUserId;

	/**
	 * Returns all the p2p activity correctionses where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching p2p activity correctionses
	 */
	@Override
	public List<P2PActivityCorrections> findByUserId(long userId) {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the p2p activity correctionses where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityCorrectionsModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of p2p activity correctionses
	 * @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	 * @return the range of matching p2p activity correctionses
	 */
	@Override
	public List<P2PActivityCorrections> findByUserId(
		long userId, int start, int end) {

		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the p2p activity correctionses where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityCorrectionsModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of p2p activity correctionses
	 * @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching p2p activity correctionses
	 */
	@Override
	public List<P2PActivityCorrections> findByUserId(
		long userId, int start, int end,
		OrderByComparator<P2PActivityCorrections> orderByComparator) {

		return findByUserId(userId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the p2p activity correctionses where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityCorrectionsModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of p2p activity correctionses
	 * @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching p2p activity correctionses
	 */
	@Override
	public List<P2PActivityCorrections> findByUserId(
		long userId, int start, int end,
		OrderByComparator<P2PActivityCorrections> orderByComparator,
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

		List<P2PActivityCorrections> list = null;

		if (useFinderCache) {
			list = (List<P2PActivityCorrections>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (P2PActivityCorrections p2pActivityCorrections : list) {
					if ((userId != p2pActivityCorrections.getUserId())) {
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

			query.append(_SQL_SELECT_P2PACTIVITYCORRECTIONS_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(P2PActivityCorrectionsModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (!pagination) {
					list = (List<P2PActivityCorrections>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<P2PActivityCorrections>)QueryUtil.list(
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
	 * Returns the first p2p activity corrections in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching p2p activity corrections
	 * @throws NoSuchP2PActivityCorrectionsException if a matching p2p activity corrections could not be found
	 */
	@Override
	public P2PActivityCorrections findByUserId_First(
			long userId,
			OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws NoSuchP2PActivityCorrectionsException {

		P2PActivityCorrections p2pActivityCorrections = fetchByUserId_First(
			userId, orderByComparator);

		if (p2pActivityCorrections != null) {
			return p2pActivityCorrections;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append("}");

		throw new NoSuchP2PActivityCorrectionsException(msg.toString());
	}

	/**
	 * Returns the first p2p activity corrections in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching p2p activity corrections, or <code>null</code> if a matching p2p activity corrections could not be found
	 */
	@Override
	public P2PActivityCorrections fetchByUserId_First(
		long userId,
		OrderByComparator<P2PActivityCorrections> orderByComparator) {

		List<P2PActivityCorrections> list = findByUserId(
			userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last p2p activity corrections in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching p2p activity corrections
	 * @throws NoSuchP2PActivityCorrectionsException if a matching p2p activity corrections could not be found
	 */
	@Override
	public P2PActivityCorrections findByUserId_Last(
			long userId,
			OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws NoSuchP2PActivityCorrectionsException {

		P2PActivityCorrections p2pActivityCorrections = fetchByUserId_Last(
			userId, orderByComparator);

		if (p2pActivityCorrections != null) {
			return p2pActivityCorrections;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append("}");

		throw new NoSuchP2PActivityCorrectionsException(msg.toString());
	}

	/**
	 * Returns the last p2p activity corrections in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching p2p activity corrections, or <code>null</code> if a matching p2p activity corrections could not be found
	 */
	@Override
	public P2PActivityCorrections fetchByUserId_Last(
		long userId,
		OrderByComparator<P2PActivityCorrections> orderByComparator) {

		int count = countByUserId(userId);

		if (count == 0) {
			return null;
		}

		List<P2PActivityCorrections> list = findByUserId(
			userId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the p2p activity correctionses before and after the current p2p activity corrections in the ordered set where userId = &#63;.
	 *
	 * @param p2pActivityCorrectionsId the primary key of the current p2p activity corrections
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next p2p activity corrections
	 * @throws NoSuchP2PActivityCorrectionsException if a p2p activity corrections with the primary key could not be found
	 */
	@Override
	public P2PActivityCorrections[] findByUserId_PrevAndNext(
			long p2pActivityCorrectionsId, long userId,
			OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws NoSuchP2PActivityCorrectionsException {

		P2PActivityCorrections p2pActivityCorrections = findByPrimaryKey(
			p2pActivityCorrectionsId);

		Session session = null;

		try {
			session = openSession();

			P2PActivityCorrections[] array = new P2PActivityCorrectionsImpl[3];

			array[0] = getByUserId_PrevAndNext(
				session, p2pActivityCorrections, userId, orderByComparator,
				true);

			array[1] = p2pActivityCorrections;

			array[2] = getByUserId_PrevAndNext(
				session, p2pActivityCorrections, userId, orderByComparator,
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

	protected P2PActivityCorrections getByUserId_PrevAndNext(
		Session session, P2PActivityCorrections p2pActivityCorrections,
		long userId,
		OrderByComparator<P2PActivityCorrections> orderByComparator,
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

		query.append(_SQL_SELECT_P2PACTIVITYCORRECTIONS_WHERE);

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
			query.append(P2PActivityCorrectionsModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						p2pActivityCorrections)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<P2PActivityCorrections> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the p2p activity correctionses where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	@Override
	public void removeByUserId(long userId) {
		for (P2PActivityCorrections p2pActivityCorrections :
				findByUserId(
					userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(p2pActivityCorrections);
		}
	}

	/**
	 * Returns the number of p2p activity correctionses where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching p2p activity correctionses
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = _finderPathCountByUserId;

		Object[] finderArgs = new Object[] {userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_P2PACTIVITYCORRECTIONS_WHERE);

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
		"p2pActivityCorrections.userId = ?";

	public P2PActivityCorrectionsPersistenceImpl() {
		setModelClass(P2PActivityCorrections.class);

		setModelImplClass(P2PActivityCorrectionsImpl.class);
		setModelPKClass(long.class);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");
		dbColumnNames.put("date", "date_");

		setDBColumnNames(dbColumnNames);
	}

	/**
	 * Caches the p2p activity corrections in the entity cache if it is enabled.
	 *
	 * @param p2pActivityCorrections the p2p activity corrections
	 */
	@Override
	public void cacheResult(P2PActivityCorrections p2pActivityCorrections) {
		entityCache.putResult(
			entityCacheEnabled, P2PActivityCorrectionsImpl.class,
			p2pActivityCorrections.getPrimaryKey(), p2pActivityCorrections);

		finderCache.putResult(
			_finderPathFetchByUUID_G,
			new Object[] {
				p2pActivityCorrections.getUuid(),
				p2pActivityCorrections.getGroupId()
			},
			p2pActivityCorrections);

		finderCache.putResult(
			_finderPathFetchByP2PActivityIdAndUserId,
			new Object[] {
				p2pActivityCorrections.getP2pActivityId(),
				p2pActivityCorrections.getUserId()
			},
			p2pActivityCorrections);

		p2pActivityCorrections.resetOriginalValues();
	}

	/**
	 * Caches the p2p activity correctionses in the entity cache if it is enabled.
	 *
	 * @param p2pActivityCorrectionses the p2p activity correctionses
	 */
	@Override
	public void cacheResult(
		List<P2PActivityCorrections> p2pActivityCorrectionses) {

		for (P2PActivityCorrections p2pActivityCorrections :
				p2pActivityCorrectionses) {

			if (entityCache.getResult(
					entityCacheEnabled, P2PActivityCorrectionsImpl.class,
					p2pActivityCorrections.getPrimaryKey()) == null) {

				cacheResult(p2pActivityCorrections);
			}
			else {
				p2pActivityCorrections.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all p2p activity correctionses.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(P2PActivityCorrectionsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the p2p activity corrections.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(P2PActivityCorrections p2pActivityCorrections) {
		entityCache.removeResult(
			entityCacheEnabled, P2PActivityCorrectionsImpl.class,
			p2pActivityCorrections.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(
			(P2PActivityCorrectionsModelImpl)p2pActivityCorrections, true);
	}

	@Override
	public void clearCache(
		List<P2PActivityCorrections> p2pActivityCorrectionses) {

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (P2PActivityCorrections p2pActivityCorrections :
				p2pActivityCorrectionses) {

			entityCache.removeResult(
				entityCacheEnabled, P2PActivityCorrectionsImpl.class,
				p2pActivityCorrections.getPrimaryKey());

			clearUniqueFindersCache(
				(P2PActivityCorrectionsModelImpl)p2pActivityCorrections, true);
		}
	}

	protected void cacheUniqueFindersCache(
		P2PActivityCorrectionsModelImpl p2pActivityCorrectionsModelImpl) {

		Object[] args = new Object[] {
			p2pActivityCorrectionsModelImpl.getUuid(),
			p2pActivityCorrectionsModelImpl.getGroupId()
		};

		finderCache.putResult(
			_finderPathCountByUUID_G, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByUUID_G, args, p2pActivityCorrectionsModelImpl,
			false);

		args = new Object[] {
			p2pActivityCorrectionsModelImpl.getP2pActivityId(),
			p2pActivityCorrectionsModelImpl.getUserId()
		};

		finderCache.putResult(
			_finderPathCountByP2PActivityIdAndUserId, args, Long.valueOf(1),
			false);
		finderCache.putResult(
			_finderPathFetchByP2PActivityIdAndUserId, args,
			p2pActivityCorrectionsModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		P2PActivityCorrectionsModelImpl p2pActivityCorrectionsModelImpl,
		boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				p2pActivityCorrectionsModelImpl.getUuid(),
				p2pActivityCorrectionsModelImpl.getGroupId()
			};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
		}

		if ((p2pActivityCorrectionsModelImpl.getColumnBitmask() &
			 _finderPathFetchByUUID_G.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				p2pActivityCorrectionsModelImpl.getOriginalUuid(),
				p2pActivityCorrectionsModelImpl.getOriginalGroupId()
			};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
				p2pActivityCorrectionsModelImpl.getP2pActivityId(),
				p2pActivityCorrectionsModelImpl.getUserId()
			};

			finderCache.removeResult(
				_finderPathCountByP2PActivityIdAndUserId, args);
			finderCache.removeResult(
				_finderPathFetchByP2PActivityIdAndUserId, args);
		}

		if ((p2pActivityCorrectionsModelImpl.getColumnBitmask() &
			 _finderPathFetchByP2PActivityIdAndUserId.getColumnBitmask()) !=
				 0) {

			Object[] args = new Object[] {
				p2pActivityCorrectionsModelImpl.getOriginalP2pActivityId(),
				p2pActivityCorrectionsModelImpl.getOriginalUserId()
			};

			finderCache.removeResult(
				_finderPathCountByP2PActivityIdAndUserId, args);
			finderCache.removeResult(
				_finderPathFetchByP2PActivityIdAndUserId, args);
		}
	}

	/**
	 * Creates a new p2p activity corrections with the primary key. Does not add the p2p activity corrections to the database.
	 *
	 * @param p2pActivityCorrectionsId the primary key for the new p2p activity corrections
	 * @return the new p2p activity corrections
	 */
	@Override
	public P2PActivityCorrections create(long p2pActivityCorrectionsId) {
		P2PActivityCorrections p2pActivityCorrections =
			new P2PActivityCorrectionsImpl();

		p2pActivityCorrections.setNew(true);
		p2pActivityCorrections.setPrimaryKey(p2pActivityCorrectionsId);

		String uuid = PortalUUIDUtil.generate();

		p2pActivityCorrections.setUuid(uuid);

		p2pActivityCorrections.setCompanyId(CompanyThreadLocal.getCompanyId());

		return p2pActivityCorrections;
	}

	/**
	 * Removes the p2p activity corrections with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param p2pActivityCorrectionsId the primary key of the p2p activity corrections
	 * @return the p2p activity corrections that was removed
	 * @throws NoSuchP2PActivityCorrectionsException if a p2p activity corrections with the primary key could not be found
	 */
	@Override
	public P2PActivityCorrections remove(long p2pActivityCorrectionsId)
		throws NoSuchP2PActivityCorrectionsException {

		return remove((Serializable)p2pActivityCorrectionsId);
	}

	/**
	 * Removes the p2p activity corrections with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the p2p activity corrections
	 * @return the p2p activity corrections that was removed
	 * @throws NoSuchP2PActivityCorrectionsException if a p2p activity corrections with the primary key could not be found
	 */
	@Override
	public P2PActivityCorrections remove(Serializable primaryKey)
		throws NoSuchP2PActivityCorrectionsException {

		Session session = null;

		try {
			session = openSession();

			P2PActivityCorrections p2pActivityCorrections =
				(P2PActivityCorrections)session.get(
					P2PActivityCorrectionsImpl.class, primaryKey);

			if (p2pActivityCorrections == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchP2PActivityCorrectionsException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(p2pActivityCorrections);
		}
		catch (NoSuchP2PActivityCorrectionsException nsee) {
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
	protected P2PActivityCorrections removeImpl(
		P2PActivityCorrections p2pActivityCorrections) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(p2pActivityCorrections)) {
				p2pActivityCorrections = (P2PActivityCorrections)session.get(
					P2PActivityCorrectionsImpl.class,
					p2pActivityCorrections.getPrimaryKeyObj());
			}

			if (p2pActivityCorrections != null) {
				session.delete(p2pActivityCorrections);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (p2pActivityCorrections != null) {
			clearCache(p2pActivityCorrections);
		}

		return p2pActivityCorrections;
	}

	@Override
	public P2PActivityCorrections updateImpl(
		P2PActivityCorrections p2pActivityCorrections) {

		boolean isNew = p2pActivityCorrections.isNew();

		if (!(p2pActivityCorrections instanceof
				P2PActivityCorrectionsModelImpl)) {

			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(p2pActivityCorrections.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					p2pActivityCorrections);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in p2pActivityCorrections proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom P2PActivityCorrections implementation " +
					p2pActivityCorrections.getClass());
		}

		P2PActivityCorrectionsModelImpl p2pActivityCorrectionsModelImpl =
			(P2PActivityCorrectionsModelImpl)p2pActivityCorrections;

		if (Validator.isNull(p2pActivityCorrections.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			p2pActivityCorrections.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (p2pActivityCorrections.getCreateDate() == null)) {
			if (serviceContext == null) {
				p2pActivityCorrections.setCreateDate(now);
			}
			else {
				p2pActivityCorrections.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!p2pActivityCorrectionsModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				p2pActivityCorrections.setModifiedDate(now);
			}
			else {
				p2pActivityCorrections.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (p2pActivityCorrections.isNew()) {
				session.save(p2pActivityCorrections);

				p2pActivityCorrections.setNew(false);
			}
			else {
				p2pActivityCorrections = (P2PActivityCorrections)session.merge(
					p2pActivityCorrections);
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
				p2pActivityCorrectionsModelImpl.getUuid()
			};

			finderCache.removeResult(_finderPathCountByUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid, args);

			args = new Object[] {
				p2pActivityCorrectionsModelImpl.getUuid(),
				p2pActivityCorrectionsModelImpl.getCompanyId()
			};

			finderCache.removeResult(_finderPathCountByUuid_C, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid_C, args);

			args = new Object[] {
				p2pActivityCorrectionsModelImpl.getP2pActivityId()
			};

			finderCache.removeResult(_finderPathCountByP2PActivityId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByP2PActivityId, args);

			args = new Object[] {
				p2pActivityCorrectionsModelImpl.getActId(),
				p2pActivityCorrectionsModelImpl.getUserId()
			};

			finderCache.removeResult(_finderPathCountByActIdAndUserId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByActIdAndUserId, args);

			args = new Object[] {p2pActivityCorrectionsModelImpl.getUserId()};

			finderCache.removeResult(_finderPathCountByUserId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUserId, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((p2pActivityCorrectionsModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					p2pActivityCorrectionsModelImpl.getOriginalUuid()
				};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);

				args = new Object[] {p2pActivityCorrectionsModelImpl.getUuid()};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);
			}

			if ((p2pActivityCorrectionsModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid_C.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					p2pActivityCorrectionsModelImpl.getOriginalUuid(),
					p2pActivityCorrectionsModelImpl.getOriginalCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);

				args = new Object[] {
					p2pActivityCorrectionsModelImpl.getUuid(),
					p2pActivityCorrectionsModelImpl.getCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);
			}

			if ((p2pActivityCorrectionsModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByP2PActivityId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					p2pActivityCorrectionsModelImpl.getOriginalP2pActivityId()
				};

				finderCache.removeResult(_finderPathCountByP2PActivityId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByP2PActivityId, args);

				args = new Object[] {
					p2pActivityCorrectionsModelImpl.getP2pActivityId()
				};

				finderCache.removeResult(_finderPathCountByP2PActivityId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByP2PActivityId, args);
			}

			if ((p2pActivityCorrectionsModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByActIdAndUserId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					p2pActivityCorrectionsModelImpl.getOriginalActId(),
					p2pActivityCorrectionsModelImpl.getOriginalUserId()
				};

				finderCache.removeResult(
					_finderPathCountByActIdAndUserId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByActIdAndUserId, args);

				args = new Object[] {
					p2pActivityCorrectionsModelImpl.getActId(),
					p2pActivityCorrectionsModelImpl.getUserId()
				};

				finderCache.removeResult(
					_finderPathCountByActIdAndUserId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByActIdAndUserId, args);
			}

			if ((p2pActivityCorrectionsModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUserId.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					p2pActivityCorrectionsModelImpl.getOriginalUserId()
				};

				finderCache.removeResult(_finderPathCountByUserId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUserId, args);

				args = new Object[] {
					p2pActivityCorrectionsModelImpl.getUserId()
				};

				finderCache.removeResult(_finderPathCountByUserId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUserId, args);
			}
		}

		entityCache.putResult(
			entityCacheEnabled, P2PActivityCorrectionsImpl.class,
			p2pActivityCorrections.getPrimaryKey(), p2pActivityCorrections,
			false);

		clearUniqueFindersCache(p2pActivityCorrectionsModelImpl, false);
		cacheUniqueFindersCache(p2pActivityCorrectionsModelImpl);

		p2pActivityCorrections.resetOriginalValues();

		return p2pActivityCorrections;
	}

	/**
	 * Returns the p2p activity corrections with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the p2p activity corrections
	 * @return the p2p activity corrections
	 * @throws NoSuchP2PActivityCorrectionsException if a p2p activity corrections with the primary key could not be found
	 */
	@Override
	public P2PActivityCorrections findByPrimaryKey(Serializable primaryKey)
		throws NoSuchP2PActivityCorrectionsException {

		P2PActivityCorrections p2pActivityCorrections = fetchByPrimaryKey(
			primaryKey);

		if (p2pActivityCorrections == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchP2PActivityCorrectionsException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return p2pActivityCorrections;
	}

	/**
	 * Returns the p2p activity corrections with the primary key or throws a <code>NoSuchP2PActivityCorrectionsException</code> if it could not be found.
	 *
	 * @param p2pActivityCorrectionsId the primary key of the p2p activity corrections
	 * @return the p2p activity corrections
	 * @throws NoSuchP2PActivityCorrectionsException if a p2p activity corrections with the primary key could not be found
	 */
	@Override
	public P2PActivityCorrections findByPrimaryKey(
			long p2pActivityCorrectionsId)
		throws NoSuchP2PActivityCorrectionsException {

		return findByPrimaryKey((Serializable)p2pActivityCorrectionsId);
	}

	/**
	 * Returns the p2p activity corrections with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param p2pActivityCorrectionsId the primary key of the p2p activity corrections
	 * @return the p2p activity corrections, or <code>null</code> if a p2p activity corrections with the primary key could not be found
	 */
	@Override
	public P2PActivityCorrections fetchByPrimaryKey(
		long p2pActivityCorrectionsId) {

		return fetchByPrimaryKey((Serializable)p2pActivityCorrectionsId);
	}

	/**
	 * Returns all the p2p activity correctionses.
	 *
	 * @return the p2p activity correctionses
	 */
	@Override
	public List<P2PActivityCorrections> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the p2p activity correctionses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityCorrectionsModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of p2p activity correctionses
	 * @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	 * @return the range of p2p activity correctionses
	 */
	@Override
	public List<P2PActivityCorrections> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the p2p activity correctionses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityCorrectionsModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of p2p activity correctionses
	 * @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of p2p activity correctionses
	 */
	@Override
	public List<P2PActivityCorrections> findAll(
		int start, int end,
		OrderByComparator<P2PActivityCorrections> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the p2p activity correctionses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>P2PActivityCorrectionsModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of p2p activity correctionses
	 * @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of p2p activity correctionses
	 */
	@Override
	public List<P2PActivityCorrections> findAll(
		int start, int end,
		OrderByComparator<P2PActivityCorrections> orderByComparator,
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

		List<P2PActivityCorrections> list = null;

		if (useFinderCache) {
			list = (List<P2PActivityCorrections>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_P2PACTIVITYCORRECTIONS);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_P2PACTIVITYCORRECTIONS;

				if (pagination) {
					sql = sql.concat(
						P2PActivityCorrectionsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<P2PActivityCorrections>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<P2PActivityCorrections>)QueryUtil.list(
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
	 * Removes all the p2p activity correctionses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (P2PActivityCorrections p2pActivityCorrections : findAll()) {
			remove(p2pActivityCorrections);
		}
	}

	/**
	 * Returns the number of p2p activity correctionses.
	 *
	 * @return the number of p2p activity correctionses
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
					_SQL_COUNT_P2PACTIVITYCORRECTIONS);

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
		return "p2pActivityCorrectionsId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_P2PACTIVITYCORRECTIONS;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return P2PActivityCorrectionsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the p2p activity corrections persistence.
	 */
	@Activate
	public void activate() {
		P2PActivityCorrectionsModelImpl.setEntityCacheEnabled(
			entityCacheEnabled);
		P2PActivityCorrectionsModelImpl.setFinderCacheEnabled(
			finderCacheEnabled);

		_finderPathWithPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			P2PActivityCorrectionsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			P2PActivityCorrectionsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			P2PActivityCorrectionsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			P2PActivityCorrectionsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()},
			P2PActivityCorrectionsModelImpl.UUID_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()});

		_finderPathFetchByUUID_G = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			P2PActivityCorrectionsImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()},
			P2PActivityCorrectionsModelImpl.UUID_COLUMN_BITMASK |
			P2PActivityCorrectionsModelImpl.GROUPID_COLUMN_BITMASK);

		_finderPathCountByUUID_G = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByUuid_C = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			P2PActivityCorrectionsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			P2PActivityCorrectionsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			P2PActivityCorrectionsModelImpl.UUID_COLUMN_BITMASK |
			P2PActivityCorrectionsModelImpl.COMPANYID_COLUMN_BITMASK);

		_finderPathCountByUuid_C = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByP2PActivityId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			P2PActivityCorrectionsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByP2PActivityId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByP2PActivityId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			P2PActivityCorrectionsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByP2PActivityId",
			new String[] {Long.class.getName()},
			P2PActivityCorrectionsModelImpl.P2PACTIVITYID_COLUMN_BITMASK);

		_finderPathCountByP2PActivityId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByP2PActivityId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByActIdAndUserId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			P2PActivityCorrectionsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByActIdAndUserId",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByActIdAndUserId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			P2PActivityCorrectionsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByActIdAndUserId",
			new String[] {Long.class.getName(), Long.class.getName()},
			P2PActivityCorrectionsModelImpl.ACTID_COLUMN_BITMASK |
			P2PActivityCorrectionsModelImpl.USERID_COLUMN_BITMASK);

		_finderPathCountByActIdAndUserId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByActIdAndUserId",
			new String[] {Long.class.getName(), Long.class.getName()});

		_finderPathFetchByP2PActivityIdAndUserId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			P2PActivityCorrectionsImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByP2PActivityIdAndUserId",
			new String[] {Long.class.getName(), Long.class.getName()},
			P2PActivityCorrectionsModelImpl.P2PACTIVITYID_COLUMN_BITMASK |
			P2PActivityCorrectionsModelImpl.USERID_COLUMN_BITMASK);

		_finderPathCountByP2PActivityIdAndUserId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByP2PActivityIdAndUserId",
			new String[] {Long.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByUserId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			P2PActivityCorrectionsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUserId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			P2PActivityCorrectionsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
			new String[] {Long.class.getName()},
			P2PActivityCorrectionsModelImpl.USERID_COLUMN_BITMASK);

		_finderPathCountByUserId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] {Long.class.getName()});
	}

	@Deactivate
	public void deactivate() {
		entityCache.removeCache(P2PActivityCorrectionsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	@Reference(
		target = PTPPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
		super.setConfiguration(configuration);

		_columnBitmaskEnabled = GetterUtil.getBoolean(
			configuration.get(
				"value.object.column.bitmask.enabled.com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections"),
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

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_P2PACTIVITYCORRECTIONS =
		"SELECT p2pActivityCorrections FROM P2PActivityCorrections p2pActivityCorrections";

	private static final String _SQL_SELECT_P2PACTIVITYCORRECTIONS_WHERE =
		"SELECT p2pActivityCorrections FROM P2PActivityCorrections p2pActivityCorrections WHERE ";

	private static final String _SQL_COUNT_P2PACTIVITYCORRECTIONS =
		"SELECT COUNT(p2pActivityCorrections) FROM P2PActivityCorrections p2pActivityCorrections";

	private static final String _SQL_COUNT_P2PACTIVITYCORRECTIONS_WHERE =
		"SELECT COUNT(p2pActivityCorrections) FROM P2PActivityCorrections p2pActivityCorrections WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"p2pActivityCorrections.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No P2PActivityCorrections exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No P2PActivityCorrections exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		P2PActivityCorrectionsPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid", "date"});

	static {
		try {
			Class.forName(PTPPersistenceConstants.class.getName());
		}
		catch (ClassNotFoundException cnfe) {
			throw new ExceptionInInitializerError(cnfe);
		}
	}

}