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
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import com.ted.lms.learning.activity.p2p.exception.NoSuchP2PActivityCorrectionsException;
import com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections;
import com.ted.lms.learning.activity.p2p.model.impl.P2PActivityCorrectionsImpl;
import com.ted.lms.learning.activity.p2p.model.impl.P2PActivityCorrectionsModelImpl;
import com.ted.lms.learning.activity.p2p.service.persistence.P2PActivityCorrectionsPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the p2p activity corrections service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see P2PActivityCorrectionsPersistence
 * @see com.ted.lms.learning.activity.p2p.service.persistence.P2PActivityCorrectionsUtil
 * @generated
 */
@ProviderType
public class P2PActivityCorrectionsPersistenceImpl extends BasePersistenceImpl<P2PActivityCorrections>
	implements P2PActivityCorrectionsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link P2PActivityCorrectionsUtil} to access the p2p activity corrections persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = P2PActivityCorrectionsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(P2PActivityCorrectionsModelImpl.ENTITY_CACHE_ENABLED,
			P2PActivityCorrectionsModelImpl.FINDER_CACHE_ENABLED,
			P2PActivityCorrectionsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(P2PActivityCorrectionsModelImpl.ENTITY_CACHE_ENABLED,
			P2PActivityCorrectionsModelImpl.FINDER_CACHE_ENABLED,
			P2PActivityCorrectionsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(P2PActivityCorrectionsModelImpl.ENTITY_CACHE_ENABLED,
			P2PActivityCorrectionsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(P2PActivityCorrectionsModelImpl.ENTITY_CACHE_ENABLED,
			P2PActivityCorrectionsModelImpl.FINDER_CACHE_ENABLED,
			P2PActivityCorrectionsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(P2PActivityCorrectionsModelImpl.ENTITY_CACHE_ENABLED,
			P2PActivityCorrectionsModelImpl.FINDER_CACHE_ENABLED,
			P2PActivityCorrectionsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			P2PActivityCorrectionsModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(P2PActivityCorrectionsModelImpl.ENTITY_CACHE_ENABLED,
			P2PActivityCorrectionsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityCorrectionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of p2p activity correctionses
	 * @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	 * @return the range of matching p2p activity correctionses
	 */
	@Override
	public List<P2PActivityCorrections> findByUuid(String uuid, int start,
		int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the p2p activity correctionses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityCorrectionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of p2p activity correctionses
	 * @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching p2p activity correctionses
	 */
	@Override
	public List<P2PActivityCorrections> findByUuid(String uuid, int start,
		int end, OrderByComparator<P2PActivityCorrections> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the p2p activity correctionses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityCorrectionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of p2p activity correctionses
	 * @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching p2p activity correctionses
	 */
	@Override
	public List<P2PActivityCorrections> findByUuid(String uuid, int start,
		int end, OrderByComparator<P2PActivityCorrections> orderByComparator,
		boolean retrieveFromCache) {
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

		List<P2PActivityCorrections> list = null;

		if (retrieveFromCache) {
			list = (List<P2PActivityCorrections>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (P2PActivityCorrections p2pActivityCorrections : list) {
					if (!Objects.equals(uuid, p2pActivityCorrections.getUuid())) {
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

			query.append(_SQL_SELECT_P2PACTIVITYCORRECTIONS_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals("")) {
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
					list = (List<P2PActivityCorrections>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<P2PActivityCorrections>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Returns the first p2p activity corrections in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching p2p activity corrections
	 * @throws NoSuchP2PActivityCorrectionsException if a matching p2p activity corrections could not be found
	 */
	@Override
	public P2PActivityCorrections findByUuid_First(String uuid,
		OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws NoSuchP2PActivityCorrectionsException {
		P2PActivityCorrections p2pActivityCorrections = fetchByUuid_First(uuid,
				orderByComparator);

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
	public P2PActivityCorrections fetchByUuid_First(String uuid,
		OrderByComparator<P2PActivityCorrections> orderByComparator) {
		List<P2PActivityCorrections> list = findByUuid(uuid, 0, 1,
				orderByComparator);

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
	public P2PActivityCorrections findByUuid_Last(String uuid,
		OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws NoSuchP2PActivityCorrectionsException {
		P2PActivityCorrections p2pActivityCorrections = fetchByUuid_Last(uuid,
				orderByComparator);

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
	public P2PActivityCorrections fetchByUuid_Last(String uuid,
		OrderByComparator<P2PActivityCorrections> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<P2PActivityCorrections> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

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
		P2PActivityCorrections p2pActivityCorrections = findByPrimaryKey(p2pActivityCorrectionsId);

		Session session = null;

		try {
			session = openSession();

			P2PActivityCorrections[] array = new P2PActivityCorrectionsImpl[3];

			array[0] = getByUuid_PrevAndNext(session, p2pActivityCorrections,
					uuid, orderByComparator, true);

			array[1] = p2pActivityCorrections;

			array[2] = getByUuid_PrevAndNext(session, p2pActivityCorrections,
					uuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected P2PActivityCorrections getByUuid_PrevAndNext(Session session,
		P2PActivityCorrections p2pActivityCorrections, String uuid,
		OrderByComparator<P2PActivityCorrections> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_P2PACTIVITYCORRECTIONS_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1);
		}
		else if (uuid.equals("")) {
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
			Object[] values = orderByComparator.getOrderByConditionValues(p2pActivityCorrections);

			for (Object value : values) {
				qPos.add(value);
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
		for (P2PActivityCorrections p2pActivityCorrections : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
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
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_P2PACTIVITYCORRECTIONS_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals("")) {
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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "p2pActivityCorrections.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "p2pActivityCorrections.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(p2pActivityCorrections.uuid IS NULL OR p2pActivityCorrections.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(P2PActivityCorrectionsModelImpl.ENTITY_CACHE_ENABLED,
			P2PActivityCorrectionsModelImpl.FINDER_CACHE_ENABLED,
			P2PActivityCorrectionsImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			P2PActivityCorrectionsModelImpl.UUID_COLUMN_BITMASK |
			P2PActivityCorrectionsModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(P2PActivityCorrectionsModelImpl.ENTITY_CACHE_ENABLED,
			P2PActivityCorrectionsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the p2p activity corrections where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchP2PActivityCorrectionsException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching p2p activity corrections
	 * @throws NoSuchP2PActivityCorrectionsException if a matching p2p activity corrections could not be found
	 */
	@Override
	public P2PActivityCorrections findByUUID_G(String uuid, long groupId)
		throws NoSuchP2PActivityCorrectionsException {
		P2PActivityCorrections p2pActivityCorrections = fetchByUUID_G(uuid,
				groupId);

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
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching p2p activity corrections, or <code>null</code> if a matching p2p activity corrections could not be found
	 */
	@Override
	public P2PActivityCorrections fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof P2PActivityCorrections) {
			P2PActivityCorrections p2pActivityCorrections = (P2PActivityCorrections)result;

			if (!Objects.equals(uuid, p2pActivityCorrections.getUuid()) ||
					(groupId != p2pActivityCorrections.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_P2PACTIVITYCORRECTIONS_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else if (uuid.equals("")) {
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
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					P2PActivityCorrections p2pActivityCorrections = list.get(0);

					result = p2pActivityCorrections;

					cacheResult(p2pActivityCorrections);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, finderArgs);

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
		P2PActivityCorrections p2pActivityCorrections = findByUUID_G(uuid,
				groupId);

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
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_P2PACTIVITYCORRECTIONS_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else if (uuid.equals("")) {
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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "p2pActivityCorrections.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "p2pActivityCorrections.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(p2pActivityCorrections.uuid IS NULL OR p2pActivityCorrections.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "p2pActivityCorrections.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(P2PActivityCorrectionsModelImpl.ENTITY_CACHE_ENABLED,
			P2PActivityCorrectionsModelImpl.FINDER_CACHE_ENABLED,
			P2PActivityCorrectionsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(P2PActivityCorrectionsModelImpl.ENTITY_CACHE_ENABLED,
			P2PActivityCorrectionsModelImpl.FINDER_CACHE_ENABLED,
			P2PActivityCorrectionsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			P2PActivityCorrectionsModelImpl.UUID_COLUMN_BITMASK |
			P2PActivityCorrectionsModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(P2PActivityCorrectionsModelImpl.ENTITY_CACHE_ENABLED,
			P2PActivityCorrectionsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the p2p activity correctionses where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching p2p activity correctionses
	 */
	@Override
	public List<P2PActivityCorrections> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the p2p activity correctionses where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityCorrectionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of p2p activity correctionses
	 * @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	 * @return the range of matching p2p activity correctionses
	 */
	@Override
	public List<P2PActivityCorrections> findByUuid_C(String uuid,
		long companyId, int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the p2p activity correctionses where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityCorrectionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public List<P2PActivityCorrections> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<P2PActivityCorrections> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the p2p activity correctionses where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityCorrectionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of p2p activity correctionses
	 * @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching p2p activity correctionses
	 */
	@Override
	public List<P2PActivityCorrections> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<P2PActivityCorrections> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] { uuid, companyId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] {
					uuid, companyId,
					
					start, end, orderByComparator
				};
		}

		List<P2PActivityCorrections> list = null;

		if (retrieveFromCache) {
			list = (List<P2PActivityCorrections>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (P2PActivityCorrections p2pActivityCorrections : list) {
					if (!Objects.equals(uuid, p2pActivityCorrections.getUuid()) ||
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
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_P2PACTIVITYCORRECTIONS_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else if (uuid.equals("")) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
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
					list = (List<P2PActivityCorrections>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<P2PActivityCorrections>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Returns the first p2p activity corrections in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching p2p activity corrections
	 * @throws NoSuchP2PActivityCorrectionsException if a matching p2p activity corrections could not be found
	 */
	@Override
	public P2PActivityCorrections findByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws NoSuchP2PActivityCorrectionsException {
		P2PActivityCorrections p2pActivityCorrections = fetchByUuid_C_First(uuid,
				companyId, orderByComparator);

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
	public P2PActivityCorrections fetchByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<P2PActivityCorrections> orderByComparator) {
		List<P2PActivityCorrections> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

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
	public P2PActivityCorrections findByUuid_C_Last(String uuid,
		long companyId,
		OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws NoSuchP2PActivityCorrectionsException {
		P2PActivityCorrections p2pActivityCorrections = fetchByUuid_C_Last(uuid,
				companyId, orderByComparator);

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
	public P2PActivityCorrections fetchByUuid_C_Last(String uuid,
		long companyId,
		OrderByComparator<P2PActivityCorrections> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<P2PActivityCorrections> list = findByUuid_C(uuid, companyId,
				count - 1, count, orderByComparator);

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
		P2PActivityCorrections p2pActivityCorrections = findByPrimaryKey(p2pActivityCorrectionsId);

		Session session = null;

		try {
			session = openSession();

			P2PActivityCorrections[] array = new P2PActivityCorrectionsImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, p2pActivityCorrections,
					uuid, companyId, orderByComparator, true);

			array[1] = p2pActivityCorrections;

			array[2] = getByUuid_C_PrevAndNext(session, p2pActivityCorrections,
					uuid, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected P2PActivityCorrections getByUuid_C_PrevAndNext(Session session,
		P2PActivityCorrections p2pActivityCorrections, String uuid,
		long companyId,
		OrderByComparator<P2PActivityCorrections> orderByComparator,
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

		query.append(_SQL_SELECT_P2PACTIVITYCORRECTIONS_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_1);
		}
		else if (uuid.equals("")) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_C_UUID_2);
		}

		query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

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
			Object[] values = orderByComparator.getOrderByConditionValues(p2pActivityCorrections);

			for (Object value : values) {
				qPos.add(value);
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
		for (P2PActivityCorrections p2pActivityCorrections : findByUuid_C(
				uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
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
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_P2PACTIVITYCORRECTIONS_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else if (uuid.equals("")) {
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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "p2pActivityCorrections.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "p2pActivityCorrections.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(p2pActivityCorrections.uuid IS NULL OR p2pActivityCorrections.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "p2pActivityCorrections.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_P2PACTIVITYID =
		new FinderPath(P2PActivityCorrectionsModelImpl.ENTITY_CACHE_ENABLED,
			P2PActivityCorrectionsModelImpl.FINDER_CACHE_ENABLED,
			P2PActivityCorrectionsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByP2PActivityId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P2PACTIVITYID =
		new FinderPath(P2PActivityCorrectionsModelImpl.ENTITY_CACHE_ENABLED,
			P2PActivityCorrectionsModelImpl.FINDER_CACHE_ENABLED,
			P2PActivityCorrectionsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByP2PActivityId",
			new String[] { Long.class.getName() },
			P2PActivityCorrectionsModelImpl.P2PACTIVITYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_P2PACTIVITYID = new FinderPath(P2PActivityCorrectionsModelImpl.ENTITY_CACHE_ENABLED,
			P2PActivityCorrectionsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByP2PActivityId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the p2p activity correctionses where p2pActivityId = &#63;.
	 *
	 * @param p2pActivityId the p2p activity ID
	 * @return the matching p2p activity correctionses
	 */
	@Override
	public List<P2PActivityCorrections> findByP2PActivityId(long p2pActivityId) {
		return findByP2PActivityId(p2pActivityId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the p2p activity correctionses where p2pActivityId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityCorrectionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityCorrectionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return findByP2PActivityId(p2pActivityId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the p2p activity correctionses where p2pActivityId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityCorrectionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param p2pActivityId the p2p activity ID
	 * @param start the lower bound of the range of p2p activity correctionses
	 * @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching p2p activity correctionses
	 */
	@Override
	public List<P2PActivityCorrections> findByP2PActivityId(
		long p2pActivityId, int start, int end,
		OrderByComparator<P2PActivityCorrections> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P2PACTIVITYID;
			finderArgs = new Object[] { p2pActivityId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_P2PACTIVITYID;
			finderArgs = new Object[] {
					p2pActivityId,
					
					start, end, orderByComparator
				};
		}

		List<P2PActivityCorrections> list = null;

		if (retrieveFromCache) {
			list = (List<P2PActivityCorrections>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (P2PActivityCorrections p2pActivityCorrections : list) {
					if ((p2pActivityId != p2pActivityCorrections.getP2pActivityId())) {
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

			query.append(_SQL_SELECT_P2PACTIVITYCORRECTIONS_WHERE);

			query.append(_FINDER_COLUMN_P2PACTIVITYID_P2PACTIVITYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
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
					list = (List<P2PActivityCorrections>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<P2PActivityCorrections>)QueryUtil.list(q,
							getDialect(), start, end);
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
		P2PActivityCorrections p2pActivityCorrections = fetchByP2PActivityId_First(p2pActivityId,
				orderByComparator);

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
		List<P2PActivityCorrections> list = findByP2PActivityId(p2pActivityId,
				0, 1, orderByComparator);

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
	public P2PActivityCorrections findByP2PActivityId_Last(long p2pActivityId,
		OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws NoSuchP2PActivityCorrectionsException {
		P2PActivityCorrections p2pActivityCorrections = fetchByP2PActivityId_Last(p2pActivityId,
				orderByComparator);

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

		List<P2PActivityCorrections> list = findByP2PActivityId(p2pActivityId,
				count - 1, count, orderByComparator);

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
		P2PActivityCorrections p2pActivityCorrections = findByPrimaryKey(p2pActivityCorrectionsId);

		Session session = null;

		try {
			session = openSession();

			P2PActivityCorrections[] array = new P2PActivityCorrectionsImpl[3];

			array[0] = getByP2PActivityId_PrevAndNext(session,
					p2pActivityCorrections, p2pActivityId, orderByComparator,
					true);

			array[1] = p2pActivityCorrections;

			array[2] = getByP2PActivityId_PrevAndNext(session,
					p2pActivityCorrections, p2pActivityId, orderByComparator,
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

	protected P2PActivityCorrections getByP2PActivityId_PrevAndNext(
		Session session, P2PActivityCorrections p2pActivityCorrections,
		long p2pActivityId,
		OrderByComparator<P2PActivityCorrections> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_P2PACTIVITYCORRECTIONS_WHERE);

		query.append(_FINDER_COLUMN_P2PACTIVITYID_P2PACTIVITYID_2);

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
			query.append(P2PActivityCorrectionsModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(p2pActivityId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(p2pActivityCorrections);

			for (Object value : values) {
				qPos.add(value);
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
		for (P2PActivityCorrections p2pActivityCorrections : findByP2PActivityId(
				p2pActivityId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
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
		FinderPath finderPath = FINDER_PATH_COUNT_BY_P2PACTIVITYID;

		Object[] finderArgs = new Object[] { p2pActivityId };

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

	private static final String _FINDER_COLUMN_P2PACTIVITYID_P2PACTIVITYID_2 = "p2pActivityCorrections.p2pActivityId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIDANDUSERID =
		new FinderPath(P2PActivityCorrectionsModelImpl.ENTITY_CACHE_ENABLED,
			P2PActivityCorrectionsModelImpl.FINDER_CACHE_ENABLED,
			P2PActivityCorrectionsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByActIdAndUserId",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIDANDUSERID =
		new FinderPath(P2PActivityCorrectionsModelImpl.ENTITY_CACHE_ENABLED,
			P2PActivityCorrectionsModelImpl.FINDER_CACHE_ENABLED,
			P2PActivityCorrectionsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByActIdAndUserId",
			new String[] { Long.class.getName(), Long.class.getName() },
			P2PActivityCorrectionsModelImpl.ACTID_COLUMN_BITMASK |
			P2PActivityCorrectionsModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ACTIDANDUSERID = new FinderPath(P2PActivityCorrectionsModelImpl.ENTITY_CACHE_ENABLED,
			P2PActivityCorrectionsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByActIdAndUserId",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the p2p activity correctionses where actId = &#63; and userId = &#63;.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @return the matching p2p activity correctionses
	 */
	@Override
	public List<P2PActivityCorrections> findByActIdAndUserId(long actId,
		long userId) {
		return findByActIdAndUserId(actId, userId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the p2p activity correctionses where actId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityCorrectionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of p2p activity correctionses
	 * @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	 * @return the range of matching p2p activity correctionses
	 */
	@Override
	public List<P2PActivityCorrections> findByActIdAndUserId(long actId,
		long userId, int start, int end) {
		return findByActIdAndUserId(actId, userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the p2p activity correctionses where actId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityCorrectionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public List<P2PActivityCorrections> findByActIdAndUserId(long actId,
		long userId, int start, int end,
		OrderByComparator<P2PActivityCorrections> orderByComparator) {
		return findByActIdAndUserId(actId, userId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the p2p activity correctionses where actId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityCorrectionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of p2p activity correctionses
	 * @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching p2p activity correctionses
	 */
	@Override
	public List<P2PActivityCorrections> findByActIdAndUserId(long actId,
		long userId, int start, int end,
		OrderByComparator<P2PActivityCorrections> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIDANDUSERID;
			finderArgs = new Object[] { actId, userId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIDANDUSERID;
			finderArgs = new Object[] {
					actId, userId,
					
					start, end, orderByComparator
				};
		}

		List<P2PActivityCorrections> list = null;

		if (retrieveFromCache) {
			list = (List<P2PActivityCorrections>)finderCache.getResult(finderPath,
					finderArgs, this);

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
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_P2PACTIVITYCORRECTIONS_WHERE);

			query.append(_FINDER_COLUMN_ACTIDANDUSERID_ACTID_2);

			query.append(_FINDER_COLUMN_ACTIDANDUSERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
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
					list = (List<P2PActivityCorrections>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<P2PActivityCorrections>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Returns the first p2p activity corrections in the ordered set where actId = &#63; and userId = &#63;.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching p2p activity corrections
	 * @throws NoSuchP2PActivityCorrectionsException if a matching p2p activity corrections could not be found
	 */
	@Override
	public P2PActivityCorrections findByActIdAndUserId_First(long actId,
		long userId, OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws NoSuchP2PActivityCorrectionsException {
		P2PActivityCorrections p2pActivityCorrections = fetchByActIdAndUserId_First(actId,
				userId, orderByComparator);

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
	public P2PActivityCorrections fetchByActIdAndUserId_First(long actId,
		long userId, OrderByComparator<P2PActivityCorrections> orderByComparator) {
		List<P2PActivityCorrections> list = findByActIdAndUserId(actId, userId,
				0, 1, orderByComparator);

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
	public P2PActivityCorrections findByActIdAndUserId_Last(long actId,
		long userId, OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws NoSuchP2PActivityCorrectionsException {
		P2PActivityCorrections p2pActivityCorrections = fetchByActIdAndUserId_Last(actId,
				userId, orderByComparator);

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
	public P2PActivityCorrections fetchByActIdAndUserId_Last(long actId,
		long userId, OrderByComparator<P2PActivityCorrections> orderByComparator) {
		int count = countByActIdAndUserId(actId, userId);

		if (count == 0) {
			return null;
		}

		List<P2PActivityCorrections> list = findByActIdAndUserId(actId, userId,
				count - 1, count, orderByComparator);

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
		P2PActivityCorrections p2pActivityCorrections = findByPrimaryKey(p2pActivityCorrectionsId);

		Session session = null;

		try {
			session = openSession();

			P2PActivityCorrections[] array = new P2PActivityCorrectionsImpl[3];

			array[0] = getByActIdAndUserId_PrevAndNext(session,
					p2pActivityCorrections, actId, userId, orderByComparator,
					true);

			array[1] = p2pActivityCorrections;

			array[2] = getByActIdAndUserId_PrevAndNext(session,
					p2pActivityCorrections, actId, userId, orderByComparator,
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

	protected P2PActivityCorrections getByActIdAndUserId_PrevAndNext(
		Session session, P2PActivityCorrections p2pActivityCorrections,
		long actId, long userId,
		OrderByComparator<P2PActivityCorrections> orderByComparator,
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

		query.append(_SQL_SELECT_P2PACTIVITYCORRECTIONS_WHERE);

		query.append(_FINDER_COLUMN_ACTIDANDUSERID_ACTID_2);

		query.append(_FINDER_COLUMN_ACTIDANDUSERID_USERID_2);

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
			Object[] values = orderByComparator.getOrderByConditionValues(p2pActivityCorrections);

			for (Object value : values) {
				qPos.add(value);
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
		for (P2PActivityCorrections p2pActivityCorrections : findByActIdAndUserId(
				actId, userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
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
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ACTIDANDUSERID;

		Object[] finderArgs = new Object[] { actId, userId };

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

	private static final String _FINDER_COLUMN_ACTIDANDUSERID_ACTID_2 = "p2pActivityCorrections.actId = ? AND ";
	private static final String _FINDER_COLUMN_ACTIDANDUSERID_USERID_2 = "p2pActivityCorrections.userId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_P2PACTIVITYIDANDUSERID =
		new FinderPath(P2PActivityCorrectionsModelImpl.ENTITY_CACHE_ENABLED,
			P2PActivityCorrectionsModelImpl.FINDER_CACHE_ENABLED,
			P2PActivityCorrectionsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByP2PActivityIdAndUserId",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P2PACTIVITYIDANDUSERID =
		new FinderPath(P2PActivityCorrectionsModelImpl.ENTITY_CACHE_ENABLED,
			P2PActivityCorrectionsModelImpl.FINDER_CACHE_ENABLED,
			P2PActivityCorrectionsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByP2PActivityIdAndUserId",
			new String[] { Long.class.getName(), Long.class.getName() },
			P2PActivityCorrectionsModelImpl.P2PACTIVITYID_COLUMN_BITMASK |
			P2PActivityCorrectionsModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_P2PACTIVITYIDANDUSERID = new FinderPath(P2PActivityCorrectionsModelImpl.ENTITY_CACHE_ENABLED,
			P2PActivityCorrectionsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByP2PActivityIdAndUserId",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the p2p activity correctionses where p2pActivityId = &#63; and userId = &#63;.
	 *
	 * @param p2pActivityId the p2p activity ID
	 * @param userId the user ID
	 * @return the matching p2p activity correctionses
	 */
	@Override
	public List<P2PActivityCorrections> findByP2PActivityIdAndUserId(
		long p2pActivityId, long userId) {
		return findByP2PActivityIdAndUserId(p2pActivityId, userId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the p2p activity correctionses where p2pActivityId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityCorrectionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param p2pActivityId the p2p activity ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of p2p activity correctionses
	 * @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	 * @return the range of matching p2p activity correctionses
	 */
	@Override
	public List<P2PActivityCorrections> findByP2PActivityIdAndUserId(
		long p2pActivityId, long userId, int start, int end) {
		return findByP2PActivityIdAndUserId(p2pActivityId, userId, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the p2p activity correctionses where p2pActivityId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityCorrectionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param p2pActivityId the p2p activity ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of p2p activity correctionses
	 * @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching p2p activity correctionses
	 */
	@Override
	public List<P2PActivityCorrections> findByP2PActivityIdAndUserId(
		long p2pActivityId, long userId, int start, int end,
		OrderByComparator<P2PActivityCorrections> orderByComparator) {
		return findByP2PActivityIdAndUserId(p2pActivityId, userId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the p2p activity correctionses where p2pActivityId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityCorrectionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param p2pActivityId the p2p activity ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of p2p activity correctionses
	 * @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching p2p activity correctionses
	 */
	@Override
	public List<P2PActivityCorrections> findByP2PActivityIdAndUserId(
		long p2pActivityId, long userId, int start, int end,
		OrderByComparator<P2PActivityCorrections> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P2PACTIVITYIDANDUSERID;
			finderArgs = new Object[] { p2pActivityId, userId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_P2PACTIVITYIDANDUSERID;
			finderArgs = new Object[] {
					p2pActivityId, userId,
					
					start, end, orderByComparator
				};
		}

		List<P2PActivityCorrections> list = null;

		if (retrieveFromCache) {
			list = (List<P2PActivityCorrections>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (P2PActivityCorrections p2pActivityCorrections : list) {
					if ((p2pActivityId != p2pActivityCorrections.getP2pActivityId()) ||
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
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_P2PACTIVITYCORRECTIONS_WHERE);

			query.append(_FINDER_COLUMN_P2PACTIVITYIDANDUSERID_P2PACTIVITYID_2);

			query.append(_FINDER_COLUMN_P2PACTIVITYIDANDUSERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(P2PActivityCorrectionsModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(p2pActivityId);

				qPos.add(userId);

				if (!pagination) {
					list = (List<P2PActivityCorrections>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<P2PActivityCorrections>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Returns the first p2p activity corrections in the ordered set where p2pActivityId = &#63; and userId = &#63;.
	 *
	 * @param p2pActivityId the p2p activity ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching p2p activity corrections
	 * @throws NoSuchP2PActivityCorrectionsException if a matching p2p activity corrections could not be found
	 */
	@Override
	public P2PActivityCorrections findByP2PActivityIdAndUserId_First(
		long p2pActivityId, long userId,
		OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws NoSuchP2PActivityCorrectionsException {
		P2PActivityCorrections p2pActivityCorrections = fetchByP2PActivityIdAndUserId_First(p2pActivityId,
				userId, orderByComparator);

		if (p2pActivityCorrections != null) {
			return p2pActivityCorrections;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("p2pActivityId=");
		msg.append(p2pActivityId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append("}");

		throw new NoSuchP2PActivityCorrectionsException(msg.toString());
	}

	/**
	 * Returns the first p2p activity corrections in the ordered set where p2pActivityId = &#63; and userId = &#63;.
	 *
	 * @param p2pActivityId the p2p activity ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching p2p activity corrections, or <code>null</code> if a matching p2p activity corrections could not be found
	 */
	@Override
	public P2PActivityCorrections fetchByP2PActivityIdAndUserId_First(
		long p2pActivityId, long userId,
		OrderByComparator<P2PActivityCorrections> orderByComparator) {
		List<P2PActivityCorrections> list = findByP2PActivityIdAndUserId(p2pActivityId,
				userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last p2p activity corrections in the ordered set where p2pActivityId = &#63; and userId = &#63;.
	 *
	 * @param p2pActivityId the p2p activity ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching p2p activity corrections
	 * @throws NoSuchP2PActivityCorrectionsException if a matching p2p activity corrections could not be found
	 */
	@Override
	public P2PActivityCorrections findByP2PActivityIdAndUserId_Last(
		long p2pActivityId, long userId,
		OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws NoSuchP2PActivityCorrectionsException {
		P2PActivityCorrections p2pActivityCorrections = fetchByP2PActivityIdAndUserId_Last(p2pActivityId,
				userId, orderByComparator);

		if (p2pActivityCorrections != null) {
			return p2pActivityCorrections;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("p2pActivityId=");
		msg.append(p2pActivityId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append("}");

		throw new NoSuchP2PActivityCorrectionsException(msg.toString());
	}

	/**
	 * Returns the last p2p activity corrections in the ordered set where p2pActivityId = &#63; and userId = &#63;.
	 *
	 * @param p2pActivityId the p2p activity ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching p2p activity corrections, or <code>null</code> if a matching p2p activity corrections could not be found
	 */
	@Override
	public P2PActivityCorrections fetchByP2PActivityIdAndUserId_Last(
		long p2pActivityId, long userId,
		OrderByComparator<P2PActivityCorrections> orderByComparator) {
		int count = countByP2PActivityIdAndUserId(p2pActivityId, userId);

		if (count == 0) {
			return null;
		}

		List<P2PActivityCorrections> list = findByP2PActivityIdAndUserId(p2pActivityId,
				userId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the p2p activity correctionses before and after the current p2p activity corrections in the ordered set where p2pActivityId = &#63; and userId = &#63;.
	 *
	 * @param p2pActivityCorrectionsId the primary key of the current p2p activity corrections
	 * @param p2pActivityId the p2p activity ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next p2p activity corrections
	 * @throws NoSuchP2PActivityCorrectionsException if a p2p activity corrections with the primary key could not be found
	 */
	@Override
	public P2PActivityCorrections[] findByP2PActivityIdAndUserId_PrevAndNext(
		long p2pActivityCorrectionsId, long p2pActivityId, long userId,
		OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws NoSuchP2PActivityCorrectionsException {
		P2PActivityCorrections p2pActivityCorrections = findByPrimaryKey(p2pActivityCorrectionsId);

		Session session = null;

		try {
			session = openSession();

			P2PActivityCorrections[] array = new P2PActivityCorrectionsImpl[3];

			array[0] = getByP2PActivityIdAndUserId_PrevAndNext(session,
					p2pActivityCorrections, p2pActivityId, userId,
					orderByComparator, true);

			array[1] = p2pActivityCorrections;

			array[2] = getByP2PActivityIdAndUserId_PrevAndNext(session,
					p2pActivityCorrections, p2pActivityId, userId,
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

	protected P2PActivityCorrections getByP2PActivityIdAndUserId_PrevAndNext(
		Session session, P2PActivityCorrections p2pActivityCorrections,
		long p2pActivityId, long userId,
		OrderByComparator<P2PActivityCorrections> orderByComparator,
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

		query.append(_SQL_SELECT_P2PACTIVITYCORRECTIONS_WHERE);

		query.append(_FINDER_COLUMN_P2PACTIVITYIDANDUSERID_P2PACTIVITYID_2);

		query.append(_FINDER_COLUMN_P2PACTIVITYIDANDUSERID_USERID_2);

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
			query.append(P2PActivityCorrectionsModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(p2pActivityId);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(p2pActivityCorrections);

			for (Object value : values) {
				qPos.add(value);
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
	 * Removes all the p2p activity correctionses where p2pActivityId = &#63; and userId = &#63; from the database.
	 *
	 * @param p2pActivityId the p2p activity ID
	 * @param userId the user ID
	 */
	@Override
	public void removeByP2PActivityIdAndUserId(long p2pActivityId, long userId) {
		for (P2PActivityCorrections p2pActivityCorrections : findByP2PActivityIdAndUserId(
				p2pActivityId, userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(p2pActivityCorrections);
		}
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
		FinderPath finderPath = FINDER_PATH_COUNT_BY_P2PACTIVITYIDANDUSERID;

		Object[] finderArgs = new Object[] { p2pActivityId, userId };

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

	private static final String _FINDER_COLUMN_P2PACTIVITYIDANDUSERID_P2PACTIVITYID_2 =
		"p2pActivityCorrections.p2pActivityId = ? AND ";
	private static final String _FINDER_COLUMN_P2PACTIVITYIDANDUSERID_USERID_2 = "p2pActivityCorrections.userId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(P2PActivityCorrectionsModelImpl.ENTITY_CACHE_ENABLED,
			P2PActivityCorrectionsModelImpl.FINDER_CACHE_ENABLED,
			P2PActivityCorrectionsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
		new FinderPath(P2PActivityCorrectionsModelImpl.ENTITY_CACHE_ENABLED,
			P2PActivityCorrectionsModelImpl.FINDER_CACHE_ENABLED,
			P2PActivityCorrectionsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
			new String[] { Long.class.getName() },
			P2PActivityCorrectionsModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(P2PActivityCorrectionsModelImpl.ENTITY_CACHE_ENABLED,
			P2PActivityCorrectionsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] { Long.class.getName() });

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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityCorrectionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of p2p activity correctionses
	 * @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	 * @return the range of matching p2p activity correctionses
	 */
	@Override
	public List<P2PActivityCorrections> findByUserId(long userId, int start,
		int end) {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the p2p activity correctionses where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityCorrectionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of p2p activity correctionses
	 * @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching p2p activity correctionses
	 */
	@Override
	public List<P2PActivityCorrections> findByUserId(long userId, int start,
		int end, OrderByComparator<P2PActivityCorrections> orderByComparator) {
		return findByUserId(userId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the p2p activity correctionses where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityCorrectionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of p2p activity correctionses
	 * @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching p2p activity correctionses
	 */
	@Override
	public List<P2PActivityCorrections> findByUserId(long userId, int start,
		int end, OrderByComparator<P2PActivityCorrections> orderByComparator,
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

		List<P2PActivityCorrections> list = null;

		if (retrieveFromCache) {
			list = (List<P2PActivityCorrections>)finderCache.getResult(finderPath,
					finderArgs, this);

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
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_P2PACTIVITYCORRECTIONS_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
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
					list = (List<P2PActivityCorrections>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<P2PActivityCorrections>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Returns the first p2p activity corrections in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching p2p activity corrections
	 * @throws NoSuchP2PActivityCorrectionsException if a matching p2p activity corrections could not be found
	 */
	@Override
	public P2PActivityCorrections findByUserId_First(long userId,
		OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws NoSuchP2PActivityCorrectionsException {
		P2PActivityCorrections p2pActivityCorrections = fetchByUserId_First(userId,
				orderByComparator);

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
	public P2PActivityCorrections fetchByUserId_First(long userId,
		OrderByComparator<P2PActivityCorrections> orderByComparator) {
		List<P2PActivityCorrections> list = findByUserId(userId, 0, 1,
				orderByComparator);

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
	public P2PActivityCorrections findByUserId_Last(long userId,
		OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws NoSuchP2PActivityCorrectionsException {
		P2PActivityCorrections p2pActivityCorrections = fetchByUserId_Last(userId,
				orderByComparator);

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
	public P2PActivityCorrections fetchByUserId_Last(long userId,
		OrderByComparator<P2PActivityCorrections> orderByComparator) {
		int count = countByUserId(userId);

		if (count == 0) {
			return null;
		}

		List<P2PActivityCorrections> list = findByUserId(userId, count - 1,
				count, orderByComparator);

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
		P2PActivityCorrections p2pActivityCorrections = findByPrimaryKey(p2pActivityCorrectionsId);

		Session session = null;

		try {
			session = openSession();

			P2PActivityCorrections[] array = new P2PActivityCorrectionsImpl[3];

			array[0] = getByUserId_PrevAndNext(session, p2pActivityCorrections,
					userId, orderByComparator, true);

			array[1] = p2pActivityCorrections;

			array[2] = getByUserId_PrevAndNext(session, p2pActivityCorrections,
					userId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected P2PActivityCorrections getByUserId_PrevAndNext(Session session,
		P2PActivityCorrections p2pActivityCorrections, long userId,
		OrderByComparator<P2PActivityCorrections> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_P2PACTIVITYCORRECTIONS_WHERE);

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
			query.append(P2PActivityCorrectionsModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(p2pActivityCorrections);

			for (Object value : values) {
				qPos.add(value);
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
		for (P2PActivityCorrections p2pActivityCorrections : findByUserId(
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
		FinderPath finderPath = FINDER_PATH_COUNT_BY_USERID;

		Object[] finderArgs = new Object[] { userId };

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

	private static final String _FINDER_COLUMN_USERID_USERID_2 = "p2pActivityCorrections.userId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_P2PACTIVITYIDDATENOTNULL =
		new FinderPath(P2PActivityCorrectionsModelImpl.ENTITY_CACHE_ENABLED,
			P2PActivityCorrectionsModelImpl.FINDER_CACHE_ENABLED,
			P2PActivityCorrectionsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByP2PActivityIdDateNotNull",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P2PACTIVITYIDDATENOTNULL =
		new FinderPath(P2PActivityCorrectionsModelImpl.ENTITY_CACHE_ENABLED,
			P2PActivityCorrectionsModelImpl.FINDER_CACHE_ENABLED,
			P2PActivityCorrectionsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByP2PActivityIdDateNotNull",
			new String[] { Long.class.getName() },
			P2PActivityCorrectionsModelImpl.P2PACTIVITYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_P2PACTIVITYIDDATENOTNULL =
		new FinderPath(P2PActivityCorrectionsModelImpl.ENTITY_CACHE_ENABLED,
			P2PActivityCorrectionsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByP2PActivityIdDateNotNull",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the p2p activity correctionses where p2pActivityId = &#63;.
	 *
	 * @param p2pActivityId the p2p activity ID
	 * @return the matching p2p activity correctionses
	 */
	@Override
	public List<P2PActivityCorrections> findByP2PActivityIdDateNotNull(
		long p2pActivityId) {
		return findByP2PActivityIdDateNotNull(p2pActivityId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the p2p activity correctionses where p2pActivityId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityCorrectionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param p2pActivityId the p2p activity ID
	 * @param start the lower bound of the range of p2p activity correctionses
	 * @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	 * @return the range of matching p2p activity correctionses
	 */
	@Override
	public List<P2PActivityCorrections> findByP2PActivityIdDateNotNull(
		long p2pActivityId, int start, int end) {
		return findByP2PActivityIdDateNotNull(p2pActivityId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the p2p activity correctionses where p2pActivityId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityCorrectionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param p2pActivityId the p2p activity ID
	 * @param start the lower bound of the range of p2p activity correctionses
	 * @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching p2p activity correctionses
	 */
	@Override
	public List<P2PActivityCorrections> findByP2PActivityIdDateNotNull(
		long p2pActivityId, int start, int end,
		OrderByComparator<P2PActivityCorrections> orderByComparator) {
		return findByP2PActivityIdDateNotNull(p2pActivityId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the p2p activity correctionses where p2pActivityId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityCorrectionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param p2pActivityId the p2p activity ID
	 * @param start the lower bound of the range of p2p activity correctionses
	 * @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching p2p activity correctionses
	 */
	@Override
	public List<P2PActivityCorrections> findByP2PActivityIdDateNotNull(
		long p2pActivityId, int start, int end,
		OrderByComparator<P2PActivityCorrections> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P2PACTIVITYIDDATENOTNULL;
			finderArgs = new Object[] { p2pActivityId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_P2PACTIVITYIDDATENOTNULL;
			finderArgs = new Object[] {
					p2pActivityId,
					
					start, end, orderByComparator
				};
		}

		List<P2PActivityCorrections> list = null;

		if (retrieveFromCache) {
			list = (List<P2PActivityCorrections>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (P2PActivityCorrections p2pActivityCorrections : list) {
					if ((p2pActivityId != p2pActivityCorrections.getP2pActivityId())) {
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

			query.append(_SQL_SELECT_P2PACTIVITYCORRECTIONS_WHERE);

			query.append(_FINDER_COLUMN_P2PACTIVITYIDDATENOTNULL_P2PACTIVITYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
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
					list = (List<P2PActivityCorrections>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<P2PActivityCorrections>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Returns the first p2p activity corrections in the ordered set where p2pActivityId = &#63;.
	 *
	 * @param p2pActivityId the p2p activity ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching p2p activity corrections
	 * @throws NoSuchP2PActivityCorrectionsException if a matching p2p activity corrections could not be found
	 */
	@Override
	public P2PActivityCorrections findByP2PActivityIdDateNotNull_First(
		long p2pActivityId,
		OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws NoSuchP2PActivityCorrectionsException {
		P2PActivityCorrections p2pActivityCorrections = fetchByP2PActivityIdDateNotNull_First(p2pActivityId,
				orderByComparator);

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
	public P2PActivityCorrections fetchByP2PActivityIdDateNotNull_First(
		long p2pActivityId,
		OrderByComparator<P2PActivityCorrections> orderByComparator) {
		List<P2PActivityCorrections> list = findByP2PActivityIdDateNotNull(p2pActivityId,
				0, 1, orderByComparator);

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
	public P2PActivityCorrections findByP2PActivityIdDateNotNull_Last(
		long p2pActivityId,
		OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws NoSuchP2PActivityCorrectionsException {
		P2PActivityCorrections p2pActivityCorrections = fetchByP2PActivityIdDateNotNull_Last(p2pActivityId,
				orderByComparator);

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
	public P2PActivityCorrections fetchByP2PActivityIdDateNotNull_Last(
		long p2pActivityId,
		OrderByComparator<P2PActivityCorrections> orderByComparator) {
		int count = countByP2PActivityIdDateNotNull(p2pActivityId);

		if (count == 0) {
			return null;
		}

		List<P2PActivityCorrections> list = findByP2PActivityIdDateNotNull(p2pActivityId,
				count - 1, count, orderByComparator);

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
	public P2PActivityCorrections[] findByP2PActivityIdDateNotNull_PrevAndNext(
		long p2pActivityCorrectionsId, long p2pActivityId,
		OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws NoSuchP2PActivityCorrectionsException {
		P2PActivityCorrections p2pActivityCorrections = findByPrimaryKey(p2pActivityCorrectionsId);

		Session session = null;

		try {
			session = openSession();

			P2PActivityCorrections[] array = new P2PActivityCorrectionsImpl[3];

			array[0] = getByP2PActivityIdDateNotNull_PrevAndNext(session,
					p2pActivityCorrections, p2pActivityId, orderByComparator,
					true);

			array[1] = p2pActivityCorrections;

			array[2] = getByP2PActivityIdDateNotNull_PrevAndNext(session,
					p2pActivityCorrections, p2pActivityId, orderByComparator,
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

	protected P2PActivityCorrections getByP2PActivityIdDateNotNull_PrevAndNext(
		Session session, P2PActivityCorrections p2pActivityCorrections,
		long p2pActivityId,
		OrderByComparator<P2PActivityCorrections> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_P2PACTIVITYCORRECTIONS_WHERE);

		query.append(_FINDER_COLUMN_P2PACTIVITYIDDATENOTNULL_P2PACTIVITYID_2);

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
			query.append(P2PActivityCorrectionsModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(p2pActivityId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(p2pActivityCorrections);

			for (Object value : values) {
				qPos.add(value);
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
	public void removeByP2PActivityIdDateNotNull(long p2pActivityId) {
		for (P2PActivityCorrections p2pActivityCorrections : findByP2PActivityIdDateNotNull(
				p2pActivityId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
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
	public int countByP2PActivityIdDateNotNull(long p2pActivityId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_P2PACTIVITYIDDATENOTNULL;

		Object[] finderArgs = new Object[] { p2pActivityId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_P2PACTIVITYCORRECTIONS_WHERE);

			query.append(_FINDER_COLUMN_P2PACTIVITYIDDATENOTNULL_P2PACTIVITYID_2);

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

	private static final String _FINDER_COLUMN_P2PACTIVITYIDDATENOTNULL_P2PACTIVITYID_2 =
		"p2pActivityCorrections.p2pActivityId = ? AND p2pActivityCorrections.date IS NOT null";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIDUSERIDDATENOTNULL =
		new FinderPath(P2PActivityCorrectionsModelImpl.ENTITY_CACHE_ENABLED,
			P2PActivityCorrectionsModelImpl.FINDER_CACHE_ENABLED,
			P2PActivityCorrectionsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByActIdUserIdDateNotNull",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIDUSERIDDATENOTNULL =
		new FinderPath(P2PActivityCorrectionsModelImpl.ENTITY_CACHE_ENABLED,
			P2PActivityCorrectionsModelImpl.FINDER_CACHE_ENABLED,
			P2PActivityCorrectionsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByActIdUserIdDateNotNull",
			new String[] { Long.class.getName(), Long.class.getName() },
			P2PActivityCorrectionsModelImpl.ACTID_COLUMN_BITMASK |
			P2PActivityCorrectionsModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ACTIDUSERIDDATENOTNULL = new FinderPath(P2PActivityCorrectionsModelImpl.ENTITY_CACHE_ENABLED,
			P2PActivityCorrectionsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByActIdUserIdDateNotNull",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the p2p activity correctionses where actId = &#63; and userId = &#63;.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @return the matching p2p activity correctionses
	 */
	@Override
	public List<P2PActivityCorrections> findByActIdUserIdDateNotNull(
		long actId, long userId) {
		return findByActIdUserIdDateNotNull(actId, userId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the p2p activity correctionses where actId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityCorrectionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of p2p activity correctionses
	 * @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	 * @return the range of matching p2p activity correctionses
	 */
	@Override
	public List<P2PActivityCorrections> findByActIdUserIdDateNotNull(
		long actId, long userId, int start, int end) {
		return findByActIdUserIdDateNotNull(actId, userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the p2p activity correctionses where actId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityCorrectionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public List<P2PActivityCorrections> findByActIdUserIdDateNotNull(
		long actId, long userId, int start, int end,
		OrderByComparator<P2PActivityCorrections> orderByComparator) {
		return findByActIdUserIdDateNotNull(actId, userId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the p2p activity correctionses where actId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityCorrectionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of p2p activity correctionses
	 * @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching p2p activity correctionses
	 */
	@Override
	public List<P2PActivityCorrections> findByActIdUserIdDateNotNull(
		long actId, long userId, int start, int end,
		OrderByComparator<P2PActivityCorrections> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIDUSERIDDATENOTNULL;
			finderArgs = new Object[] { actId, userId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIDUSERIDDATENOTNULL;
			finderArgs = new Object[] {
					actId, userId,
					
					start, end, orderByComparator
				};
		}

		List<P2PActivityCorrections> list = null;

		if (retrieveFromCache) {
			list = (List<P2PActivityCorrections>)finderCache.getResult(finderPath,
					finderArgs, this);

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
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_P2PACTIVITYCORRECTIONS_WHERE);

			query.append(_FINDER_COLUMN_ACTIDUSERIDDATENOTNULL_ACTID_2);

			query.append(_FINDER_COLUMN_ACTIDUSERIDDATENOTNULL_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
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
					list = (List<P2PActivityCorrections>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<P2PActivityCorrections>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Returns the first p2p activity corrections in the ordered set where actId = &#63; and userId = &#63;.
	 *
	 * @param actId the act ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching p2p activity corrections
	 * @throws NoSuchP2PActivityCorrectionsException if a matching p2p activity corrections could not be found
	 */
	@Override
	public P2PActivityCorrections findByActIdUserIdDateNotNull_First(
		long actId, long userId,
		OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws NoSuchP2PActivityCorrectionsException {
		P2PActivityCorrections p2pActivityCorrections = fetchByActIdUserIdDateNotNull_First(actId,
				userId, orderByComparator);

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
	public P2PActivityCorrections fetchByActIdUserIdDateNotNull_First(
		long actId, long userId,
		OrderByComparator<P2PActivityCorrections> orderByComparator) {
		List<P2PActivityCorrections> list = findByActIdUserIdDateNotNull(actId,
				userId, 0, 1, orderByComparator);

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
	public P2PActivityCorrections findByActIdUserIdDateNotNull_Last(
		long actId, long userId,
		OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws NoSuchP2PActivityCorrectionsException {
		P2PActivityCorrections p2pActivityCorrections = fetchByActIdUserIdDateNotNull_Last(actId,
				userId, orderByComparator);

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
	public P2PActivityCorrections fetchByActIdUserIdDateNotNull_Last(
		long actId, long userId,
		OrderByComparator<P2PActivityCorrections> orderByComparator) {
		int count = countByActIdUserIdDateNotNull(actId, userId);

		if (count == 0) {
			return null;
		}

		List<P2PActivityCorrections> list = findByActIdUserIdDateNotNull(actId,
				userId, count - 1, count, orderByComparator);

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
	public P2PActivityCorrections[] findByActIdUserIdDateNotNull_PrevAndNext(
		long p2pActivityCorrectionsId, long actId, long userId,
		OrderByComparator<P2PActivityCorrections> orderByComparator)
		throws NoSuchP2PActivityCorrectionsException {
		P2PActivityCorrections p2pActivityCorrections = findByPrimaryKey(p2pActivityCorrectionsId);

		Session session = null;

		try {
			session = openSession();

			P2PActivityCorrections[] array = new P2PActivityCorrectionsImpl[3];

			array[0] = getByActIdUserIdDateNotNull_PrevAndNext(session,
					p2pActivityCorrections, actId, userId, orderByComparator,
					true);

			array[1] = p2pActivityCorrections;

			array[2] = getByActIdUserIdDateNotNull_PrevAndNext(session,
					p2pActivityCorrections, actId, userId, orderByComparator,
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

	protected P2PActivityCorrections getByActIdUserIdDateNotNull_PrevAndNext(
		Session session, P2PActivityCorrections p2pActivityCorrections,
		long actId, long userId,
		OrderByComparator<P2PActivityCorrections> orderByComparator,
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

		query.append(_SQL_SELECT_P2PACTIVITYCORRECTIONS_WHERE);

		query.append(_FINDER_COLUMN_ACTIDUSERIDDATENOTNULL_ACTID_2);

		query.append(_FINDER_COLUMN_ACTIDUSERIDDATENOTNULL_USERID_2);

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
			Object[] values = orderByComparator.getOrderByConditionValues(p2pActivityCorrections);

			for (Object value : values) {
				qPos.add(value);
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
	public void removeByActIdUserIdDateNotNull(long actId, long userId) {
		for (P2PActivityCorrections p2pActivityCorrections : findByActIdUserIdDateNotNull(
				actId, userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
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
	public int countByActIdUserIdDateNotNull(long actId, long userId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ACTIDUSERIDDATENOTNULL;

		Object[] finderArgs = new Object[] { actId, userId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_P2PACTIVITYCORRECTIONS_WHERE);

			query.append(_FINDER_COLUMN_ACTIDUSERIDDATENOTNULL_ACTID_2);

			query.append(_FINDER_COLUMN_ACTIDUSERIDDATENOTNULL_USERID_2);

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

	private static final String _FINDER_COLUMN_ACTIDUSERIDDATENOTNULL_ACTID_2 = "p2pActivityCorrections.actId = ? AND ";
	private static final String _FINDER_COLUMN_ACTIDUSERIDDATENOTNULL_USERID_2 = "p2pActivityCorrections.userId = ? AND p2pActivityCorrections.date IS NOT null";

	public P2PActivityCorrectionsPersistenceImpl() {
		setModelClass(P2PActivityCorrections.class);

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
					"_dbColumnNames");

			field.setAccessible(true);

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("uuid", "uuid_");
			dbColumnNames.put("date", "date_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the p2p activity corrections in the entity cache if it is enabled.
	 *
	 * @param p2pActivityCorrections the p2p activity corrections
	 */
	@Override
	public void cacheResult(P2PActivityCorrections p2pActivityCorrections) {
		entityCache.putResult(P2PActivityCorrectionsModelImpl.ENTITY_CACHE_ENABLED,
			P2PActivityCorrectionsImpl.class,
			p2pActivityCorrections.getPrimaryKey(), p2pActivityCorrections);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				p2pActivityCorrections.getUuid(),
				p2pActivityCorrections.getGroupId()
			}, p2pActivityCorrections);

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
		for (P2PActivityCorrections p2pActivityCorrections : p2pActivityCorrectionses) {
			if (entityCache.getResult(
						P2PActivityCorrectionsModelImpl.ENTITY_CACHE_ENABLED,
						P2PActivityCorrectionsImpl.class,
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
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
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
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(P2PActivityCorrections p2pActivityCorrections) {
		entityCache.removeResult(P2PActivityCorrectionsModelImpl.ENTITY_CACHE_ENABLED,
			P2PActivityCorrectionsImpl.class,
			p2pActivityCorrections.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((P2PActivityCorrectionsModelImpl)p2pActivityCorrections,
			true);
	}

	@Override
	public void clearCache(
		List<P2PActivityCorrections> p2pActivityCorrectionses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (P2PActivityCorrections p2pActivityCorrections : p2pActivityCorrectionses) {
			entityCache.removeResult(P2PActivityCorrectionsModelImpl.ENTITY_CACHE_ENABLED,
				P2PActivityCorrectionsImpl.class,
				p2pActivityCorrections.getPrimaryKey());

			clearUniqueFindersCache((P2PActivityCorrectionsModelImpl)p2pActivityCorrections,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		P2PActivityCorrectionsModelImpl p2pActivityCorrectionsModelImpl) {
		Object[] args = new Object[] {
				p2pActivityCorrectionsModelImpl.getUuid(),
				p2pActivityCorrectionsModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
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

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((p2pActivityCorrectionsModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					p2pActivityCorrectionsModelImpl.getOriginalUuid(),
					p2pActivityCorrectionsModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
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
		P2PActivityCorrections p2pActivityCorrections = new P2PActivityCorrectionsImpl();

		p2pActivityCorrections.setNew(true);
		p2pActivityCorrections.setPrimaryKey(p2pActivityCorrectionsId);

		String uuid = PortalUUIDUtil.generate();

		p2pActivityCorrections.setUuid(uuid);

		p2pActivityCorrections.setCompanyId(companyProvider.getCompanyId());

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

			P2PActivityCorrections p2pActivityCorrections = (P2PActivityCorrections)session.get(P2PActivityCorrectionsImpl.class,
					primaryKey);

			if (p2pActivityCorrections == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchP2PActivityCorrectionsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
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
				p2pActivityCorrections = (P2PActivityCorrections)session.get(P2PActivityCorrectionsImpl.class,
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

		if (!(p2pActivityCorrections instanceof P2PActivityCorrectionsModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(p2pActivityCorrections.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(p2pActivityCorrections);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in p2pActivityCorrections proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom P2PActivityCorrections implementation " +
				p2pActivityCorrections.getClass());
		}

		P2PActivityCorrectionsModelImpl p2pActivityCorrectionsModelImpl = (P2PActivityCorrectionsModelImpl)p2pActivityCorrections;

		if (Validator.isNull(p2pActivityCorrections.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			p2pActivityCorrections.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (p2pActivityCorrections.getCreateDate() == null)) {
			if (serviceContext == null) {
				p2pActivityCorrections.setCreateDate(now);
			}
			else {
				p2pActivityCorrections.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!p2pActivityCorrectionsModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				p2pActivityCorrections.setModifiedDate(now);
			}
			else {
				p2pActivityCorrections.setModifiedDate(serviceContext.getModifiedDate(
						now));
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
				p2pActivityCorrections = (P2PActivityCorrections)session.merge(p2pActivityCorrections);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!P2PActivityCorrectionsModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					p2pActivityCorrectionsModelImpl.getUuid()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					p2pActivityCorrectionsModelImpl.getUuid(),
					p2pActivityCorrectionsModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] {
					p2pActivityCorrectionsModelImpl.getP2pActivityId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_P2PACTIVITYID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P2PACTIVITYID,
				args);

			args = new Object[] {
					p2pActivityCorrectionsModelImpl.getActId(),
					p2pActivityCorrectionsModelImpl.getUserId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ACTIDANDUSERID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIDANDUSERID,
				args);

			args = new Object[] {
					p2pActivityCorrectionsModelImpl.getP2pActivityId(),
					p2pActivityCorrectionsModelImpl.getUserId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_P2PACTIVITYIDANDUSERID,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P2PACTIVITYIDANDUSERID,
				args);

			args = new Object[] { p2pActivityCorrectionsModelImpl.getUserId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
				args);

			args = new Object[] {
					p2pActivityCorrectionsModelImpl.getP2pActivityId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_P2PACTIVITYIDDATENOTNULL,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P2PACTIVITYIDDATENOTNULL,
				args);

			args = new Object[] {
					p2pActivityCorrectionsModelImpl.getActId(),
					p2pActivityCorrectionsModelImpl.getUserId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ACTIDUSERIDDATENOTNULL,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIDUSERIDDATENOTNULL,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((p2pActivityCorrectionsModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						p2pActivityCorrectionsModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { p2pActivityCorrectionsModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((p2pActivityCorrectionsModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						p2pActivityCorrectionsModelImpl.getOriginalUuid(),
						p2pActivityCorrectionsModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						p2pActivityCorrectionsModelImpl.getUuid(),
						p2pActivityCorrectionsModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((p2pActivityCorrectionsModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P2PACTIVITYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						p2pActivityCorrectionsModelImpl.getOriginalP2pActivityId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_P2PACTIVITYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P2PACTIVITYID,
					args);

				args = new Object[] {
						p2pActivityCorrectionsModelImpl.getP2pActivityId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_P2PACTIVITYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P2PACTIVITYID,
					args);
			}

			if ((p2pActivityCorrectionsModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIDANDUSERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						p2pActivityCorrectionsModelImpl.getOriginalActId(),
						p2pActivityCorrectionsModelImpl.getOriginalUserId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ACTIDANDUSERID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIDANDUSERID,
					args);

				args = new Object[] {
						p2pActivityCorrectionsModelImpl.getActId(),
						p2pActivityCorrectionsModelImpl.getUserId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ACTIDANDUSERID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIDANDUSERID,
					args);
			}

			if ((p2pActivityCorrectionsModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P2PACTIVITYIDANDUSERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						p2pActivityCorrectionsModelImpl.getOriginalP2pActivityId(),
						p2pActivityCorrectionsModelImpl.getOriginalUserId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_P2PACTIVITYIDANDUSERID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P2PACTIVITYIDANDUSERID,
					args);

				args = new Object[] {
						p2pActivityCorrectionsModelImpl.getP2pActivityId(),
						p2pActivityCorrectionsModelImpl.getUserId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_P2PACTIVITYIDANDUSERID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P2PACTIVITYIDANDUSERID,
					args);
			}

			if ((p2pActivityCorrectionsModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						p2pActivityCorrectionsModelImpl.getOriginalUserId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);

				args = new Object[] { p2pActivityCorrectionsModelImpl.getUserId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);
			}

			if ((p2pActivityCorrectionsModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P2PACTIVITYIDDATENOTNULL.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						p2pActivityCorrectionsModelImpl.getOriginalP2pActivityId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_P2PACTIVITYIDDATENOTNULL,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P2PACTIVITYIDDATENOTNULL,
					args);

				args = new Object[] {
						p2pActivityCorrectionsModelImpl.getP2pActivityId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_P2PACTIVITYIDDATENOTNULL,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P2PACTIVITYIDDATENOTNULL,
					args);
			}

			if ((p2pActivityCorrectionsModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIDUSERIDDATENOTNULL.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						p2pActivityCorrectionsModelImpl.getOriginalActId(),
						p2pActivityCorrectionsModelImpl.getOriginalUserId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ACTIDUSERIDDATENOTNULL,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIDUSERIDDATENOTNULL,
					args);

				args = new Object[] {
						p2pActivityCorrectionsModelImpl.getActId(),
						p2pActivityCorrectionsModelImpl.getUserId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ACTIDUSERIDDATENOTNULL,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIDUSERIDDATENOTNULL,
					args);
			}
		}

		entityCache.putResult(P2PActivityCorrectionsModelImpl.ENTITY_CACHE_ENABLED,
			P2PActivityCorrectionsImpl.class,
			p2pActivityCorrections.getPrimaryKey(), p2pActivityCorrections,
			false);

		clearUniqueFindersCache(p2pActivityCorrectionsModelImpl, false);
		cacheUniqueFindersCache(p2pActivityCorrectionsModelImpl);

		p2pActivityCorrections.resetOriginalValues();

		return p2pActivityCorrections;
	}

	/**
	 * Returns the p2p activity corrections with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the p2p activity corrections
	 * @return the p2p activity corrections
	 * @throws NoSuchP2PActivityCorrectionsException if a p2p activity corrections with the primary key could not be found
	 */
	@Override
	public P2PActivityCorrections findByPrimaryKey(Serializable primaryKey)
		throws NoSuchP2PActivityCorrectionsException {
		P2PActivityCorrections p2pActivityCorrections = fetchByPrimaryKey(primaryKey);

		if (p2pActivityCorrections == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchP2PActivityCorrectionsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return p2pActivityCorrections;
	}

	/**
	 * Returns the p2p activity corrections with the primary key or throws a {@link NoSuchP2PActivityCorrectionsException} if it could not be found.
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
	 * @param primaryKey the primary key of the p2p activity corrections
	 * @return the p2p activity corrections, or <code>null</code> if a p2p activity corrections with the primary key could not be found
	 */
	@Override
	public P2PActivityCorrections fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(P2PActivityCorrectionsModelImpl.ENTITY_CACHE_ENABLED,
				P2PActivityCorrectionsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		P2PActivityCorrections p2pActivityCorrections = (P2PActivityCorrections)serializable;

		if (p2pActivityCorrections == null) {
			Session session = null;

			try {
				session = openSession();

				p2pActivityCorrections = (P2PActivityCorrections)session.get(P2PActivityCorrectionsImpl.class,
						primaryKey);

				if (p2pActivityCorrections != null) {
					cacheResult(p2pActivityCorrections);
				}
				else {
					entityCache.putResult(P2PActivityCorrectionsModelImpl.ENTITY_CACHE_ENABLED,
						P2PActivityCorrectionsImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(P2PActivityCorrectionsModelImpl.ENTITY_CACHE_ENABLED,
					P2PActivityCorrectionsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return p2pActivityCorrections;
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

	@Override
	public Map<Serializable, P2PActivityCorrections> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, P2PActivityCorrections> map = new HashMap<Serializable, P2PActivityCorrections>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			P2PActivityCorrections p2pActivityCorrections = fetchByPrimaryKey(primaryKey);

			if (p2pActivityCorrections != null) {
				map.put(primaryKey, p2pActivityCorrections);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(P2PActivityCorrectionsModelImpl.ENTITY_CACHE_ENABLED,
					P2PActivityCorrectionsImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (P2PActivityCorrections)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_P2PACTIVITYCORRECTIONS_WHERE_PKS_IN);

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

			for (P2PActivityCorrections p2pActivityCorrections : (List<P2PActivityCorrections>)q.list()) {
				map.put(p2pActivityCorrections.getPrimaryKeyObj(),
					p2pActivityCorrections);

				cacheResult(p2pActivityCorrections);

				uncachedPrimaryKeys.remove(p2pActivityCorrections.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(P2PActivityCorrectionsModelImpl.ENTITY_CACHE_ENABLED,
					P2PActivityCorrectionsImpl.class, primaryKey, nullModel);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityCorrectionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityCorrectionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of p2p activity correctionses
	 * @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of p2p activity correctionses
	 */
	@Override
	public List<P2PActivityCorrections> findAll(int start, int end,
		OrderByComparator<P2PActivityCorrections> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the p2p activity correctionses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link P2PActivityCorrectionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of p2p activity correctionses
	 * @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of p2p activity correctionses
	 */
	@Override
	public List<P2PActivityCorrections> findAll(int start, int end,
		OrderByComparator<P2PActivityCorrections> orderByComparator,
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

		List<P2PActivityCorrections> list = null;

		if (retrieveFromCache) {
			list = (List<P2PActivityCorrections>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_P2PACTIVITYCORRECTIONS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_P2PACTIVITYCORRECTIONS;

				if (pagination) {
					sql = sql.concat(P2PActivityCorrectionsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<P2PActivityCorrections>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<P2PActivityCorrections>)QueryUtil.list(q,
							getDialect(), start, end);
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
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_P2PACTIVITYCORRECTIONS);

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
		return P2PActivityCorrectionsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the p2p activity corrections persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(P2PActivityCorrectionsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_P2PACTIVITYCORRECTIONS = "SELECT p2pActivityCorrections FROM P2PActivityCorrections p2pActivityCorrections";
	private static final String _SQL_SELECT_P2PACTIVITYCORRECTIONS_WHERE_PKS_IN = "SELECT p2pActivityCorrections FROM P2PActivityCorrections p2pActivityCorrections WHERE p2pActivityCorrectionsId IN (";
	private static final String _SQL_SELECT_P2PACTIVITYCORRECTIONS_WHERE = "SELECT p2pActivityCorrections FROM P2PActivityCorrections p2pActivityCorrections WHERE ";
	private static final String _SQL_COUNT_P2PACTIVITYCORRECTIONS = "SELECT COUNT(p2pActivityCorrections) FROM P2PActivityCorrections p2pActivityCorrections";
	private static final String _SQL_COUNT_P2PACTIVITYCORRECTIONS_WHERE = "SELECT COUNT(p2pActivityCorrections) FROM P2PActivityCorrections p2pActivityCorrections WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "p2pActivityCorrections.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No P2PActivityCorrections exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No P2PActivityCorrections exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(P2PActivityCorrectionsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid", "date"
			});
}