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

package com.ted.prerequisite.service.persistence.impl;

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

import com.ted.prerequisite.exception.NoSuchPrerequisiteRelationException;
import com.ted.prerequisite.model.PrerequisiteRelation;
import com.ted.prerequisite.model.impl.PrerequisiteRelationImpl;
import com.ted.prerequisite.model.impl.PrerequisiteRelationModelImpl;
import com.ted.prerequisite.service.persistence.PrerequisiteRelationPersistence;

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
 * The persistence implementation for the prerequisite relation service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PrerequisiteRelationPersistence
 * @see com.ted.prerequisite.service.persistence.PrerequisiteRelationUtil
 * @generated
 */
@ProviderType
public class PrerequisiteRelationPersistenceImpl extends BasePersistenceImpl<PrerequisiteRelation>
	implements PrerequisiteRelationPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link PrerequisiteRelationUtil} to access the prerequisite relation persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = PrerequisiteRelationImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PrerequisiteRelationModelImpl.ENTITY_CACHE_ENABLED,
			PrerequisiteRelationModelImpl.FINDER_CACHE_ENABLED,
			PrerequisiteRelationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PrerequisiteRelationModelImpl.ENTITY_CACHE_ENABLED,
			PrerequisiteRelationModelImpl.FINDER_CACHE_ENABLED,
			PrerequisiteRelationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PrerequisiteRelationModelImpl.ENTITY_CACHE_ENABLED,
			PrerequisiteRelationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(PrerequisiteRelationModelImpl.ENTITY_CACHE_ENABLED,
			PrerequisiteRelationModelImpl.FINDER_CACHE_ENABLED,
			PrerequisiteRelationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(PrerequisiteRelationModelImpl.ENTITY_CACHE_ENABLED,
			PrerequisiteRelationModelImpl.FINDER_CACHE_ENABLED,
			PrerequisiteRelationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			PrerequisiteRelationModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(PrerequisiteRelationModelImpl.ENTITY_CACHE_ENABLED,
			PrerequisiteRelationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the prerequisite relations where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching prerequisite relations
	 */
	@Override
	public List<PrerequisiteRelation> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the prerequisite relations where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PrerequisiteRelationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of prerequisite relations
	 * @param end the upper bound of the range of prerequisite relations (not inclusive)
	 * @return the range of matching prerequisite relations
	 */
	@Override
	public List<PrerequisiteRelation> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the prerequisite relations where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PrerequisiteRelationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of prerequisite relations
	 * @param end the upper bound of the range of prerequisite relations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching prerequisite relations
	 */
	@Override
	public List<PrerequisiteRelation> findByUuid(String uuid, int start,
		int end, OrderByComparator<PrerequisiteRelation> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the prerequisite relations where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PrerequisiteRelationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of prerequisite relations
	 * @param end the upper bound of the range of prerequisite relations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching prerequisite relations
	 */
	@Override
	public List<PrerequisiteRelation> findByUuid(String uuid, int start,
		int end, OrderByComparator<PrerequisiteRelation> orderByComparator,
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

		List<PrerequisiteRelation> list = null;

		if (retrieveFromCache) {
			list = (List<PrerequisiteRelation>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PrerequisiteRelation prerequisiteRelation : list) {
					if (!uuid.equals(prerequisiteRelation.getUuid())) {
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

			query.append(_SQL_SELECT_PREREQUISITERELATION_WHERE);

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
				query.append(PrerequisiteRelationModelImpl.ORDER_BY_JPQL);
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
					list = (List<PrerequisiteRelation>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PrerequisiteRelation>)QueryUtil.list(q,
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
	 * Returns the first prerequisite relation in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching prerequisite relation
	 * @throws NoSuchPrerequisiteRelationException if a matching prerequisite relation could not be found
	 */
	@Override
	public PrerequisiteRelation findByUuid_First(String uuid,
		OrderByComparator<PrerequisiteRelation> orderByComparator)
		throws NoSuchPrerequisiteRelationException {
		PrerequisiteRelation prerequisiteRelation = fetchByUuid_First(uuid,
				orderByComparator);

		if (prerequisiteRelation != null) {
			return prerequisiteRelation;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchPrerequisiteRelationException(msg.toString());
	}

	/**
	 * Returns the first prerequisite relation in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching prerequisite relation, or <code>null</code> if a matching prerequisite relation could not be found
	 */
	@Override
	public PrerequisiteRelation fetchByUuid_First(String uuid,
		OrderByComparator<PrerequisiteRelation> orderByComparator) {
		List<PrerequisiteRelation> list = findByUuid(uuid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last prerequisite relation in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching prerequisite relation
	 * @throws NoSuchPrerequisiteRelationException if a matching prerequisite relation could not be found
	 */
	@Override
	public PrerequisiteRelation findByUuid_Last(String uuid,
		OrderByComparator<PrerequisiteRelation> orderByComparator)
		throws NoSuchPrerequisiteRelationException {
		PrerequisiteRelation prerequisiteRelation = fetchByUuid_Last(uuid,
				orderByComparator);

		if (prerequisiteRelation != null) {
			return prerequisiteRelation;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchPrerequisiteRelationException(msg.toString());
	}

	/**
	 * Returns the last prerequisite relation in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching prerequisite relation, or <code>null</code> if a matching prerequisite relation could not be found
	 */
	@Override
	public PrerequisiteRelation fetchByUuid_Last(String uuid,
		OrderByComparator<PrerequisiteRelation> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<PrerequisiteRelation> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the prerequisite relations before and after the current prerequisite relation in the ordered set where uuid = &#63;.
	 *
	 * @param prerequisiteRelationId the primary key of the current prerequisite relation
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next prerequisite relation
	 * @throws NoSuchPrerequisiteRelationException if a prerequisite relation with the primary key could not be found
	 */
	@Override
	public PrerequisiteRelation[] findByUuid_PrevAndNext(
		long prerequisiteRelationId, String uuid,
		OrderByComparator<PrerequisiteRelation> orderByComparator)
		throws NoSuchPrerequisiteRelationException {
		uuid = Objects.toString(uuid, "");

		PrerequisiteRelation prerequisiteRelation = findByPrimaryKey(prerequisiteRelationId);

		Session session = null;

		try {
			session = openSession();

			PrerequisiteRelation[] array = new PrerequisiteRelationImpl[3];

			array[0] = getByUuid_PrevAndNext(session, prerequisiteRelation,
					uuid, orderByComparator, true);

			array[1] = prerequisiteRelation;

			array[2] = getByUuid_PrevAndNext(session, prerequisiteRelation,
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

	protected PrerequisiteRelation getByUuid_PrevAndNext(Session session,
		PrerequisiteRelation prerequisiteRelation, String uuid,
		OrderByComparator<PrerequisiteRelation> orderByComparator,
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

		query.append(_SQL_SELECT_PREREQUISITERELATION_WHERE);

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
			query.append(PrerequisiteRelationModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(prerequisiteRelation);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PrerequisiteRelation> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the prerequisite relations where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (PrerequisiteRelation prerequisiteRelation : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(prerequisiteRelation);
		}
	}

	/**
	 * Returns the number of prerequisite relations where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching prerequisite relations
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PREREQUISITERELATION_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "prerequisiteRelation.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "prerequisiteRelation.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(prerequisiteRelation.uuid IS NULL OR prerequisiteRelation.uuid = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CLASSNAMEIDCLASSPK =
		new FinderPath(PrerequisiteRelationModelImpl.ENTITY_CACHE_ENABLED,
			PrerequisiteRelationModelImpl.FINDER_CACHE_ENABLED,
			PrerequisiteRelationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByClassNameIdClassPK",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSNAMEIDCLASSPK =
		new FinderPath(PrerequisiteRelationModelImpl.ENTITY_CACHE_ENABLED,
			PrerequisiteRelationModelImpl.FINDER_CACHE_ENABLED,
			PrerequisiteRelationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByClassNameIdClassPK",
			new String[] { Long.class.getName(), Long.class.getName() },
			PrerequisiteRelationModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			PrerequisiteRelationModelImpl.CLASSPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CLASSNAMEIDCLASSPK = new FinderPath(PrerequisiteRelationModelImpl.ENTITY_CACHE_ENABLED,
			PrerequisiteRelationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByClassNameIdClassPK",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the prerequisite relations where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching prerequisite relations
	 */
	@Override
	public List<PrerequisiteRelation> findByClassNameIdClassPK(
		long classNameId, long classPK) {
		return findByClassNameIdClassPK(classNameId, classPK,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the prerequisite relations where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PrerequisiteRelationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of prerequisite relations
	 * @param end the upper bound of the range of prerequisite relations (not inclusive)
	 * @return the range of matching prerequisite relations
	 */
	@Override
	public List<PrerequisiteRelation> findByClassNameIdClassPK(
		long classNameId, long classPK, int start, int end) {
		return findByClassNameIdClassPK(classNameId, classPK, start, end, null);
	}

	/**
	 * Returns an ordered range of all the prerequisite relations where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PrerequisiteRelationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of prerequisite relations
	 * @param end the upper bound of the range of prerequisite relations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching prerequisite relations
	 */
	@Override
	public List<PrerequisiteRelation> findByClassNameIdClassPK(
		long classNameId, long classPK, int start, int end,
		OrderByComparator<PrerequisiteRelation> orderByComparator) {
		return findByClassNameIdClassPK(classNameId, classPK, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the prerequisite relations where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PrerequisiteRelationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of prerequisite relations
	 * @param end the upper bound of the range of prerequisite relations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching prerequisite relations
	 */
	@Override
	public List<PrerequisiteRelation> findByClassNameIdClassPK(
		long classNameId, long classPK, int start, int end,
		OrderByComparator<PrerequisiteRelation> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSNAMEIDCLASSPK;
			finderArgs = new Object[] { classNameId, classPK };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CLASSNAMEIDCLASSPK;
			finderArgs = new Object[] {
					classNameId, classPK,
					
					start, end, orderByComparator
				};
		}

		List<PrerequisiteRelation> list = null;

		if (retrieveFromCache) {
			list = (List<PrerequisiteRelation>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PrerequisiteRelation prerequisiteRelation : list) {
					if ((classNameId != prerequisiteRelation.getClassNameId()) ||
							(classPK != prerequisiteRelation.getClassPK())) {
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

			query.append(_SQL_SELECT_PREREQUISITERELATION_WHERE);

			query.append(_FINDER_COLUMN_CLASSNAMEIDCLASSPK_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_CLASSNAMEIDCLASSPK_CLASSPK_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PrerequisiteRelationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				if (!pagination) {
					list = (List<PrerequisiteRelation>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PrerequisiteRelation>)QueryUtil.list(q,
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
	 * Returns the first prerequisite relation in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching prerequisite relation
	 * @throws NoSuchPrerequisiteRelationException if a matching prerequisite relation could not be found
	 */
	@Override
	public PrerequisiteRelation findByClassNameIdClassPK_First(
		long classNameId, long classPK,
		OrderByComparator<PrerequisiteRelation> orderByComparator)
		throws NoSuchPrerequisiteRelationException {
		PrerequisiteRelation prerequisiteRelation = fetchByClassNameIdClassPK_First(classNameId,
				classPK, orderByComparator);

		if (prerequisiteRelation != null) {
			return prerequisiteRelation;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append("}");

		throw new NoSuchPrerequisiteRelationException(msg.toString());
	}

	/**
	 * Returns the first prerequisite relation in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching prerequisite relation, or <code>null</code> if a matching prerequisite relation could not be found
	 */
	@Override
	public PrerequisiteRelation fetchByClassNameIdClassPK_First(
		long classNameId, long classPK,
		OrderByComparator<PrerequisiteRelation> orderByComparator) {
		List<PrerequisiteRelation> list = findByClassNameIdClassPK(classNameId,
				classPK, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last prerequisite relation in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching prerequisite relation
	 * @throws NoSuchPrerequisiteRelationException if a matching prerequisite relation could not be found
	 */
	@Override
	public PrerequisiteRelation findByClassNameIdClassPK_Last(
		long classNameId, long classPK,
		OrderByComparator<PrerequisiteRelation> orderByComparator)
		throws NoSuchPrerequisiteRelationException {
		PrerequisiteRelation prerequisiteRelation = fetchByClassNameIdClassPK_Last(classNameId,
				classPK, orderByComparator);

		if (prerequisiteRelation != null) {
			return prerequisiteRelation;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append("}");

		throw new NoSuchPrerequisiteRelationException(msg.toString());
	}

	/**
	 * Returns the last prerequisite relation in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching prerequisite relation, or <code>null</code> if a matching prerequisite relation could not be found
	 */
	@Override
	public PrerequisiteRelation fetchByClassNameIdClassPK_Last(
		long classNameId, long classPK,
		OrderByComparator<PrerequisiteRelation> orderByComparator) {
		int count = countByClassNameIdClassPK(classNameId, classPK);

		if (count == 0) {
			return null;
		}

		List<PrerequisiteRelation> list = findByClassNameIdClassPK(classNameId,
				classPK, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the prerequisite relations before and after the current prerequisite relation in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param prerequisiteRelationId the primary key of the current prerequisite relation
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next prerequisite relation
	 * @throws NoSuchPrerequisiteRelationException if a prerequisite relation with the primary key could not be found
	 */
	@Override
	public PrerequisiteRelation[] findByClassNameIdClassPK_PrevAndNext(
		long prerequisiteRelationId, long classNameId, long classPK,
		OrderByComparator<PrerequisiteRelation> orderByComparator)
		throws NoSuchPrerequisiteRelationException {
		PrerequisiteRelation prerequisiteRelation = findByPrimaryKey(prerequisiteRelationId);

		Session session = null;

		try {
			session = openSession();

			PrerequisiteRelation[] array = new PrerequisiteRelationImpl[3];

			array[0] = getByClassNameIdClassPK_PrevAndNext(session,
					prerequisiteRelation, classNameId, classPK,
					orderByComparator, true);

			array[1] = prerequisiteRelation;

			array[2] = getByClassNameIdClassPK_PrevAndNext(session,
					prerequisiteRelation, classNameId, classPK,
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

	protected PrerequisiteRelation getByClassNameIdClassPK_PrevAndNext(
		Session session, PrerequisiteRelation prerequisiteRelation,
		long classNameId, long classPK,
		OrderByComparator<PrerequisiteRelation> orderByComparator,
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

		query.append(_SQL_SELECT_PREREQUISITERELATION_WHERE);

		query.append(_FINDER_COLUMN_CLASSNAMEIDCLASSPK_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_CLASSNAMEIDCLASSPK_CLASSPK_2);

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
			query.append(PrerequisiteRelationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(classNameId);

		qPos.add(classPK);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(prerequisiteRelation);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PrerequisiteRelation> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the prerequisite relations where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 */
	@Override
	public void removeByClassNameIdClassPK(long classNameId, long classPK) {
		for (PrerequisiteRelation prerequisiteRelation : findByClassNameIdClassPK(
				classNameId, classPK, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(prerequisiteRelation);
		}
	}

	/**
	 * Returns the number of prerequisite relations where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching prerequisite relations
	 */
	@Override
	public int countByClassNameIdClassPK(long classNameId, long classPK) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CLASSNAMEIDCLASSPK;

		Object[] finderArgs = new Object[] { classNameId, classPK };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PREREQUISITERELATION_WHERE);

			query.append(_FINDER_COLUMN_CLASSNAMEIDCLASSPK_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_CLASSNAMEIDCLASSPK_CLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

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

	private static final String _FINDER_COLUMN_CLASSNAMEIDCLASSPK_CLASSNAMEID_2 = "prerequisiteRelation.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_CLASSNAMEIDCLASSPK_CLASSPK_2 = "prerequisiteRelation.classPK = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_CLASSNAMEPREREQUISITEIDCLASSNAMEIDCLASSPK =
		new FinderPath(PrerequisiteRelationModelImpl.ENTITY_CACHE_ENABLED,
			PrerequisiteRelationModelImpl.FINDER_CACHE_ENABLED,
			PrerequisiteRelationImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByClassNamePrerequisiteIdClassNameIdClassPK",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			PrerequisiteRelationModelImpl.CLASSNAMEPREREQUISITEID_COLUMN_BITMASK |
			PrerequisiteRelationModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			PrerequisiteRelationModelImpl.CLASSPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CLASSNAMEPREREQUISITEIDCLASSNAMEIDCLASSPK =
		new FinderPath(PrerequisiteRelationModelImpl.ENTITY_CACHE_ENABLED,
			PrerequisiteRelationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByClassNamePrerequisiteIdClassNameIdClassPK",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});

	/**
	 * Returns the prerequisite relation where classNamePrerequisiteId = &#63; and classNameId = &#63; and classPK = &#63; or throws a {@link NoSuchPrerequisiteRelationException} if it could not be found.
	 *
	 * @param classNamePrerequisiteId the class name prerequisite ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching prerequisite relation
	 * @throws NoSuchPrerequisiteRelationException if a matching prerequisite relation could not be found
	 */
	@Override
	public PrerequisiteRelation findByClassNamePrerequisiteIdClassNameIdClassPK(
		long classNamePrerequisiteId, long classNameId, long classPK)
		throws NoSuchPrerequisiteRelationException {
		PrerequisiteRelation prerequisiteRelation = fetchByClassNamePrerequisiteIdClassNameIdClassPK(classNamePrerequisiteId,
				classNameId, classPK);

		if (prerequisiteRelation == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("classNamePrerequisiteId=");
			msg.append(classNamePrerequisiteId);

			msg.append(", classNameId=");
			msg.append(classNameId);

			msg.append(", classPK=");
			msg.append(classPK);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchPrerequisiteRelationException(msg.toString());
		}

		return prerequisiteRelation;
	}

	/**
	 * Returns the prerequisite relation where classNamePrerequisiteId = &#63; and classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param classNamePrerequisiteId the class name prerequisite ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching prerequisite relation, or <code>null</code> if a matching prerequisite relation could not be found
	 */
	@Override
	public PrerequisiteRelation fetchByClassNamePrerequisiteIdClassNameIdClassPK(
		long classNamePrerequisiteId, long classNameId, long classPK) {
		return fetchByClassNamePrerequisiteIdClassNameIdClassPK(classNamePrerequisiteId,
			classNameId, classPK, true);
	}

	/**
	 * Returns the prerequisite relation where classNamePrerequisiteId = &#63; and classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param classNamePrerequisiteId the class name prerequisite ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching prerequisite relation, or <code>null</code> if a matching prerequisite relation could not be found
	 */
	@Override
	public PrerequisiteRelation fetchByClassNamePrerequisiteIdClassNameIdClassPK(
		long classNamePrerequisiteId, long classNameId, long classPK,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] {
				classNamePrerequisiteId, classNameId, classPK
			};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_CLASSNAMEPREREQUISITEIDCLASSNAMEIDCLASSPK,
					finderArgs, this);
		}

		if (result instanceof PrerequisiteRelation) {
			PrerequisiteRelation prerequisiteRelation = (PrerequisiteRelation)result;

			if ((classNamePrerequisiteId != prerequisiteRelation.getClassNamePrerequisiteId()) ||
					(classNameId != prerequisiteRelation.getClassNameId()) ||
					(classPK != prerequisiteRelation.getClassPK())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_PREREQUISITERELATION_WHERE);

			query.append(_FINDER_COLUMN_CLASSNAMEPREREQUISITEIDCLASSNAMEIDCLASSPK_CLASSNAMEPREREQUISITEID_2);

			query.append(_FINDER_COLUMN_CLASSNAMEPREREQUISITEIDCLASSNAMEIDCLASSPK_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_CLASSNAMEPREREQUISITEIDCLASSNAMEIDCLASSPK_CLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNamePrerequisiteId);

				qPos.add(classNameId);

				qPos.add(classPK);

				List<PrerequisiteRelation> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_CLASSNAMEPREREQUISITEIDCLASSNAMEIDCLASSPK,
						finderArgs, list);
				}
				else {
					PrerequisiteRelation prerequisiteRelation = list.get(0);

					result = prerequisiteRelation;

					cacheResult(prerequisiteRelation);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_CLASSNAMEPREREQUISITEIDCLASSNAMEIDCLASSPK,
					finderArgs);

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
			return (PrerequisiteRelation)result;
		}
	}

	/**
	 * Removes the prerequisite relation where classNamePrerequisiteId = &#63; and classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNamePrerequisiteId the class name prerequisite ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the prerequisite relation that was removed
	 */
	@Override
	public PrerequisiteRelation removeByClassNamePrerequisiteIdClassNameIdClassPK(
		long classNamePrerequisiteId, long classNameId, long classPK)
		throws NoSuchPrerequisiteRelationException {
		PrerequisiteRelation prerequisiteRelation = findByClassNamePrerequisiteIdClassNameIdClassPK(classNamePrerequisiteId,
				classNameId, classPK);

		return remove(prerequisiteRelation);
	}

	/**
	 * Returns the number of prerequisite relations where classNamePrerequisiteId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNamePrerequisiteId the class name prerequisite ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching prerequisite relations
	 */
	@Override
	public int countByClassNamePrerequisiteIdClassNameIdClassPK(
		long classNamePrerequisiteId, long classNameId, long classPK) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CLASSNAMEPREREQUISITEIDCLASSNAMEIDCLASSPK;

		Object[] finderArgs = new Object[] {
				classNamePrerequisiteId, classNameId, classPK
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_PREREQUISITERELATION_WHERE);

			query.append(_FINDER_COLUMN_CLASSNAMEPREREQUISITEIDCLASSNAMEIDCLASSPK_CLASSNAMEPREREQUISITEID_2);

			query.append(_FINDER_COLUMN_CLASSNAMEPREREQUISITEIDCLASSNAMEIDCLASSPK_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_CLASSNAMEPREREQUISITEIDCLASSNAMEIDCLASSPK_CLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNamePrerequisiteId);

				qPos.add(classNameId);

				qPos.add(classPK);

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

	private static final String _FINDER_COLUMN_CLASSNAMEPREREQUISITEIDCLASSNAMEIDCLASSPK_CLASSNAMEPREREQUISITEID_2 =
		"prerequisiteRelation.classNamePrerequisiteId = ? AND ";
	private static final String _FINDER_COLUMN_CLASSNAMEPREREQUISITEIDCLASSNAMEIDCLASSPK_CLASSNAMEID_2 =
		"prerequisiteRelation.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_CLASSNAMEPREREQUISITEIDCLASSNAMEIDCLASSPK_CLASSPK_2 =
		"prerequisiteRelation.classPK = ?";

	public PrerequisiteRelationPersistenceImpl() {
		setModelClass(PrerequisiteRelation.class);

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
	 * Caches the prerequisite relation in the entity cache if it is enabled.
	 *
	 * @param prerequisiteRelation the prerequisite relation
	 */
	@Override
	public void cacheResult(PrerequisiteRelation prerequisiteRelation) {
		entityCache.putResult(PrerequisiteRelationModelImpl.ENTITY_CACHE_ENABLED,
			PrerequisiteRelationImpl.class,
			prerequisiteRelation.getPrimaryKey(), prerequisiteRelation);

		finderCache.putResult(FINDER_PATH_FETCH_BY_CLASSNAMEPREREQUISITEIDCLASSNAMEIDCLASSPK,
			new Object[] {
				prerequisiteRelation.getClassNamePrerequisiteId(),
				prerequisiteRelation.getClassNameId(),
				prerequisiteRelation.getClassPK()
			}, prerequisiteRelation);

		prerequisiteRelation.resetOriginalValues();
	}

	/**
	 * Caches the prerequisite relations in the entity cache if it is enabled.
	 *
	 * @param prerequisiteRelations the prerequisite relations
	 */
	@Override
	public void cacheResult(List<PrerequisiteRelation> prerequisiteRelations) {
		for (PrerequisiteRelation prerequisiteRelation : prerequisiteRelations) {
			if (entityCache.getResult(
						PrerequisiteRelationModelImpl.ENTITY_CACHE_ENABLED,
						PrerequisiteRelationImpl.class,
						prerequisiteRelation.getPrimaryKey()) == null) {
				cacheResult(prerequisiteRelation);
			}
			else {
				prerequisiteRelation.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all prerequisite relations.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(PrerequisiteRelationImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the prerequisite relation.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(PrerequisiteRelation prerequisiteRelation) {
		entityCache.removeResult(PrerequisiteRelationModelImpl.ENTITY_CACHE_ENABLED,
			PrerequisiteRelationImpl.class, prerequisiteRelation.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((PrerequisiteRelationModelImpl)prerequisiteRelation,
			true);
	}

	@Override
	public void clearCache(List<PrerequisiteRelation> prerequisiteRelations) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (PrerequisiteRelation prerequisiteRelation : prerequisiteRelations) {
			entityCache.removeResult(PrerequisiteRelationModelImpl.ENTITY_CACHE_ENABLED,
				PrerequisiteRelationImpl.class,
				prerequisiteRelation.getPrimaryKey());

			clearUniqueFindersCache((PrerequisiteRelationModelImpl)prerequisiteRelation,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		PrerequisiteRelationModelImpl prerequisiteRelationModelImpl) {
		Object[] args = new Object[] {
				prerequisiteRelationModelImpl.getClassNamePrerequisiteId(),
				prerequisiteRelationModelImpl.getClassNameId(),
				prerequisiteRelationModelImpl.getClassPK()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_CLASSNAMEPREREQUISITEIDCLASSNAMEIDCLASSPK,
			args, Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_CLASSNAMEPREREQUISITEIDCLASSNAMEIDCLASSPK,
			args, prerequisiteRelationModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		PrerequisiteRelationModelImpl prerequisiteRelationModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					prerequisiteRelationModelImpl.getClassNamePrerequisiteId(),
					prerequisiteRelationModelImpl.getClassNameId(),
					prerequisiteRelationModelImpl.getClassPK()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CLASSNAMEPREREQUISITEIDCLASSNAMEIDCLASSPK,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_CLASSNAMEPREREQUISITEIDCLASSNAMEIDCLASSPK,
				args);
		}

		if ((prerequisiteRelationModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_CLASSNAMEPREREQUISITEIDCLASSNAMEIDCLASSPK.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					prerequisiteRelationModelImpl.getOriginalClassNamePrerequisiteId(),
					prerequisiteRelationModelImpl.getOriginalClassNameId(),
					prerequisiteRelationModelImpl.getOriginalClassPK()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CLASSNAMEPREREQUISITEIDCLASSNAMEIDCLASSPK,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_CLASSNAMEPREREQUISITEIDCLASSNAMEIDCLASSPK,
				args);
		}
	}

	/**
	 * Creates a new prerequisite relation with the primary key. Does not add the prerequisite relation to the database.
	 *
	 * @param prerequisiteRelationId the primary key for the new prerequisite relation
	 * @return the new prerequisite relation
	 */
	@Override
	public PrerequisiteRelation create(long prerequisiteRelationId) {
		PrerequisiteRelation prerequisiteRelation = new PrerequisiteRelationImpl();

		prerequisiteRelation.setNew(true);
		prerequisiteRelation.setPrimaryKey(prerequisiteRelationId);

		String uuid = PortalUUIDUtil.generate();

		prerequisiteRelation.setUuid(uuid);

		return prerequisiteRelation;
	}

	/**
	 * Removes the prerequisite relation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param prerequisiteRelationId the primary key of the prerequisite relation
	 * @return the prerequisite relation that was removed
	 * @throws NoSuchPrerequisiteRelationException if a prerequisite relation with the primary key could not be found
	 */
	@Override
	public PrerequisiteRelation remove(long prerequisiteRelationId)
		throws NoSuchPrerequisiteRelationException {
		return remove((Serializable)prerequisiteRelationId);
	}

	/**
	 * Removes the prerequisite relation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the prerequisite relation
	 * @return the prerequisite relation that was removed
	 * @throws NoSuchPrerequisiteRelationException if a prerequisite relation with the primary key could not be found
	 */
	@Override
	public PrerequisiteRelation remove(Serializable primaryKey)
		throws NoSuchPrerequisiteRelationException {
		Session session = null;

		try {
			session = openSession();

			PrerequisiteRelation prerequisiteRelation = (PrerequisiteRelation)session.get(PrerequisiteRelationImpl.class,
					primaryKey);

			if (prerequisiteRelation == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchPrerequisiteRelationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(prerequisiteRelation);
		}
		catch (NoSuchPrerequisiteRelationException nsee) {
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
	protected PrerequisiteRelation removeImpl(
		PrerequisiteRelation prerequisiteRelation) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(prerequisiteRelation)) {
				prerequisiteRelation = (PrerequisiteRelation)session.get(PrerequisiteRelationImpl.class,
						prerequisiteRelation.getPrimaryKeyObj());
			}

			if (prerequisiteRelation != null) {
				session.delete(prerequisiteRelation);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (prerequisiteRelation != null) {
			clearCache(prerequisiteRelation);
		}

		return prerequisiteRelation;
	}

	@Override
	public PrerequisiteRelation updateImpl(
		PrerequisiteRelation prerequisiteRelation) {
		boolean isNew = prerequisiteRelation.isNew();

		if (!(prerequisiteRelation instanceof PrerequisiteRelationModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(prerequisiteRelation.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(prerequisiteRelation);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in prerequisiteRelation proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom PrerequisiteRelation implementation " +
				prerequisiteRelation.getClass());
		}

		PrerequisiteRelationModelImpl prerequisiteRelationModelImpl = (PrerequisiteRelationModelImpl)prerequisiteRelation;

		if (Validator.isNull(prerequisiteRelation.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			prerequisiteRelation.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (prerequisiteRelation.isNew()) {
				session.save(prerequisiteRelation);

				prerequisiteRelation.setNew(false);
			}
			else {
				prerequisiteRelation = (PrerequisiteRelation)session.merge(prerequisiteRelation);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!PrerequisiteRelationModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { prerequisiteRelationModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					prerequisiteRelationModelImpl.getClassNameId(),
					prerequisiteRelationModelImpl.getClassPK()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CLASSNAMEIDCLASSPK,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSNAMEIDCLASSPK,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((prerequisiteRelationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						prerequisiteRelationModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { prerequisiteRelationModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((prerequisiteRelationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSNAMEIDCLASSPK.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						prerequisiteRelationModelImpl.getOriginalClassNameId(),
						prerequisiteRelationModelImpl.getOriginalClassPK()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CLASSNAMEIDCLASSPK,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSNAMEIDCLASSPK,
					args);

				args = new Object[] {
						prerequisiteRelationModelImpl.getClassNameId(),
						prerequisiteRelationModelImpl.getClassPK()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CLASSNAMEIDCLASSPK,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSNAMEIDCLASSPK,
					args);
			}
		}

		entityCache.putResult(PrerequisiteRelationModelImpl.ENTITY_CACHE_ENABLED,
			PrerequisiteRelationImpl.class,
			prerequisiteRelation.getPrimaryKey(), prerequisiteRelation, false);

		clearUniqueFindersCache(prerequisiteRelationModelImpl, false);
		cacheUniqueFindersCache(prerequisiteRelationModelImpl);

		prerequisiteRelation.resetOriginalValues();

		return prerequisiteRelation;
	}

	/**
	 * Returns the prerequisite relation with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the prerequisite relation
	 * @return the prerequisite relation
	 * @throws NoSuchPrerequisiteRelationException if a prerequisite relation with the primary key could not be found
	 */
	@Override
	public PrerequisiteRelation findByPrimaryKey(Serializable primaryKey)
		throws NoSuchPrerequisiteRelationException {
		PrerequisiteRelation prerequisiteRelation = fetchByPrimaryKey(primaryKey);

		if (prerequisiteRelation == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchPrerequisiteRelationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return prerequisiteRelation;
	}

	/**
	 * Returns the prerequisite relation with the primary key or throws a {@link NoSuchPrerequisiteRelationException} if it could not be found.
	 *
	 * @param prerequisiteRelationId the primary key of the prerequisite relation
	 * @return the prerequisite relation
	 * @throws NoSuchPrerequisiteRelationException if a prerequisite relation with the primary key could not be found
	 */
	@Override
	public PrerequisiteRelation findByPrimaryKey(long prerequisiteRelationId)
		throws NoSuchPrerequisiteRelationException {
		return findByPrimaryKey((Serializable)prerequisiteRelationId);
	}

	/**
	 * Returns the prerequisite relation with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the prerequisite relation
	 * @return the prerequisite relation, or <code>null</code> if a prerequisite relation with the primary key could not be found
	 */
	@Override
	public PrerequisiteRelation fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(PrerequisiteRelationModelImpl.ENTITY_CACHE_ENABLED,
				PrerequisiteRelationImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		PrerequisiteRelation prerequisiteRelation = (PrerequisiteRelation)serializable;

		if (prerequisiteRelation == null) {
			Session session = null;

			try {
				session = openSession();

				prerequisiteRelation = (PrerequisiteRelation)session.get(PrerequisiteRelationImpl.class,
						primaryKey);

				if (prerequisiteRelation != null) {
					cacheResult(prerequisiteRelation);
				}
				else {
					entityCache.putResult(PrerequisiteRelationModelImpl.ENTITY_CACHE_ENABLED,
						PrerequisiteRelationImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(PrerequisiteRelationModelImpl.ENTITY_CACHE_ENABLED,
					PrerequisiteRelationImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return prerequisiteRelation;
	}

	/**
	 * Returns the prerequisite relation with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param prerequisiteRelationId the primary key of the prerequisite relation
	 * @return the prerequisite relation, or <code>null</code> if a prerequisite relation with the primary key could not be found
	 */
	@Override
	public PrerequisiteRelation fetchByPrimaryKey(long prerequisiteRelationId) {
		return fetchByPrimaryKey((Serializable)prerequisiteRelationId);
	}

	@Override
	public Map<Serializable, PrerequisiteRelation> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, PrerequisiteRelation> map = new HashMap<Serializable, PrerequisiteRelation>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			PrerequisiteRelation prerequisiteRelation = fetchByPrimaryKey(primaryKey);

			if (prerequisiteRelation != null) {
				map.put(primaryKey, prerequisiteRelation);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(PrerequisiteRelationModelImpl.ENTITY_CACHE_ENABLED,
					PrerequisiteRelationImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (PrerequisiteRelation)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_PREREQUISITERELATION_WHERE_PKS_IN);

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

			for (PrerequisiteRelation prerequisiteRelation : (List<PrerequisiteRelation>)q.list()) {
				map.put(prerequisiteRelation.getPrimaryKeyObj(),
					prerequisiteRelation);

				cacheResult(prerequisiteRelation);

				uncachedPrimaryKeys.remove(prerequisiteRelation.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(PrerequisiteRelationModelImpl.ENTITY_CACHE_ENABLED,
					PrerequisiteRelationImpl.class, primaryKey, nullModel);
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
	 * Returns all the prerequisite relations.
	 *
	 * @return the prerequisite relations
	 */
	@Override
	public List<PrerequisiteRelation> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the prerequisite relations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PrerequisiteRelationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of prerequisite relations
	 * @param end the upper bound of the range of prerequisite relations (not inclusive)
	 * @return the range of prerequisite relations
	 */
	@Override
	public List<PrerequisiteRelation> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the prerequisite relations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PrerequisiteRelationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of prerequisite relations
	 * @param end the upper bound of the range of prerequisite relations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of prerequisite relations
	 */
	@Override
	public List<PrerequisiteRelation> findAll(int start, int end,
		OrderByComparator<PrerequisiteRelation> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the prerequisite relations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PrerequisiteRelationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of prerequisite relations
	 * @param end the upper bound of the range of prerequisite relations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of prerequisite relations
	 */
	@Override
	public List<PrerequisiteRelation> findAll(int start, int end,
		OrderByComparator<PrerequisiteRelation> orderByComparator,
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

		List<PrerequisiteRelation> list = null;

		if (retrieveFromCache) {
			list = (List<PrerequisiteRelation>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_PREREQUISITERELATION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PREREQUISITERELATION;

				if (pagination) {
					sql = sql.concat(PrerequisiteRelationModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<PrerequisiteRelation>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PrerequisiteRelation>)QueryUtil.list(q,
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
	 * Removes all the prerequisite relations from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (PrerequisiteRelation prerequisiteRelation : findAll()) {
			remove(prerequisiteRelation);
		}
	}

	/**
	 * Returns the number of prerequisite relations.
	 *
	 * @return the number of prerequisite relations
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_PREREQUISITERELATION);

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
		return PrerequisiteRelationModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the prerequisite relation persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(PrerequisiteRelationImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_PREREQUISITERELATION = "SELECT prerequisiteRelation FROM PrerequisiteRelation prerequisiteRelation";
	private static final String _SQL_SELECT_PREREQUISITERELATION_WHERE_PKS_IN = "SELECT prerequisiteRelation FROM PrerequisiteRelation prerequisiteRelation WHERE prerequisiteRelationId IN (";
	private static final String _SQL_SELECT_PREREQUISITERELATION_WHERE = "SELECT prerequisiteRelation FROM PrerequisiteRelation prerequisiteRelation WHERE ";
	private static final String _SQL_COUNT_PREREQUISITERELATION = "SELECT COUNT(prerequisiteRelation) FROM PrerequisiteRelation prerequisiteRelation";
	private static final String _SQL_COUNT_PREREQUISITERELATION_WHERE = "SELECT COUNT(prerequisiteRelation) FROM PrerequisiteRelation prerequisiteRelation WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "prerequisiteRelation.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PrerequisiteRelation exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No PrerequisiteRelation exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(PrerequisiteRelationPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}