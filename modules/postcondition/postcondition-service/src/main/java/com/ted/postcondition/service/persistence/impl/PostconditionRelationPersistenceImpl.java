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

package com.ted.postcondition.service.persistence.impl;

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

import com.ted.postcondition.exception.NoSuchPostconditionRelationException;
import com.ted.postcondition.model.PostconditionRelation;
import com.ted.postcondition.model.impl.PostconditionRelationImpl;
import com.ted.postcondition.model.impl.PostconditionRelationModelImpl;
import com.ted.postcondition.service.persistence.PostconditionRelationPersistence;

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
 * The persistence implementation for the postcondition relation service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PostconditionRelationPersistence
 * @see com.ted.postcondition.service.persistence.PostconditionRelationUtil
 * @generated
 */
@ProviderType
public class PostconditionRelationPersistenceImpl extends BasePersistenceImpl<PostconditionRelation>
	implements PostconditionRelationPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link PostconditionRelationUtil} to access the postcondition relation persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = PostconditionRelationImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PostconditionRelationModelImpl.ENTITY_CACHE_ENABLED,
			PostconditionRelationModelImpl.FINDER_CACHE_ENABLED,
			PostconditionRelationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PostconditionRelationModelImpl.ENTITY_CACHE_ENABLED,
			PostconditionRelationModelImpl.FINDER_CACHE_ENABLED,
			PostconditionRelationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PostconditionRelationModelImpl.ENTITY_CACHE_ENABLED,
			PostconditionRelationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(PostconditionRelationModelImpl.ENTITY_CACHE_ENABLED,
			PostconditionRelationModelImpl.FINDER_CACHE_ENABLED,
			PostconditionRelationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(PostconditionRelationModelImpl.ENTITY_CACHE_ENABLED,
			PostconditionRelationModelImpl.FINDER_CACHE_ENABLED,
			PostconditionRelationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			PostconditionRelationModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(PostconditionRelationModelImpl.ENTITY_CACHE_ENABLED,
			PostconditionRelationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the postcondition relations where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching postcondition relations
	 */
	@Override
	public List<PostconditionRelation> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the postcondition relations where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostconditionRelationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of postcondition relations
	 * @param end the upper bound of the range of postcondition relations (not inclusive)
	 * @return the range of matching postcondition relations
	 */
	@Override
	public List<PostconditionRelation> findByUuid(String uuid, int start,
		int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the postcondition relations where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostconditionRelationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of postcondition relations
	 * @param end the upper bound of the range of postcondition relations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching postcondition relations
	 */
	@Override
	public List<PostconditionRelation> findByUuid(String uuid, int start,
		int end, OrderByComparator<PostconditionRelation> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the postcondition relations where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostconditionRelationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of postcondition relations
	 * @param end the upper bound of the range of postcondition relations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching postcondition relations
	 */
	@Override
	public List<PostconditionRelation> findByUuid(String uuid, int start,
		int end, OrderByComparator<PostconditionRelation> orderByComparator,
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

		List<PostconditionRelation> list = null;

		if (retrieveFromCache) {
			list = (List<PostconditionRelation>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PostconditionRelation postconditionRelation : list) {
					if (!uuid.equals(postconditionRelation.getUuid())) {
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

			query.append(_SQL_SELECT_POSTCONDITIONRELATION_WHERE);

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
				query.append(PostconditionRelationModelImpl.ORDER_BY_JPQL);
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
					list = (List<PostconditionRelation>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PostconditionRelation>)QueryUtil.list(q,
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
	 * Returns the first postcondition relation in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching postcondition relation
	 * @throws NoSuchPostconditionRelationException if a matching postcondition relation could not be found
	 */
	@Override
	public PostconditionRelation findByUuid_First(String uuid,
		OrderByComparator<PostconditionRelation> orderByComparator)
		throws NoSuchPostconditionRelationException {
		PostconditionRelation postconditionRelation = fetchByUuid_First(uuid,
				orderByComparator);

		if (postconditionRelation != null) {
			return postconditionRelation;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchPostconditionRelationException(msg.toString());
	}

	/**
	 * Returns the first postcondition relation in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching postcondition relation, or <code>null</code> if a matching postcondition relation could not be found
	 */
	@Override
	public PostconditionRelation fetchByUuid_First(String uuid,
		OrderByComparator<PostconditionRelation> orderByComparator) {
		List<PostconditionRelation> list = findByUuid(uuid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last postcondition relation in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching postcondition relation
	 * @throws NoSuchPostconditionRelationException if a matching postcondition relation could not be found
	 */
	@Override
	public PostconditionRelation findByUuid_Last(String uuid,
		OrderByComparator<PostconditionRelation> orderByComparator)
		throws NoSuchPostconditionRelationException {
		PostconditionRelation postconditionRelation = fetchByUuid_Last(uuid,
				orderByComparator);

		if (postconditionRelation != null) {
			return postconditionRelation;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchPostconditionRelationException(msg.toString());
	}

	/**
	 * Returns the last postcondition relation in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching postcondition relation, or <code>null</code> if a matching postcondition relation could not be found
	 */
	@Override
	public PostconditionRelation fetchByUuid_Last(String uuid,
		OrderByComparator<PostconditionRelation> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<PostconditionRelation> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the postcondition relations before and after the current postcondition relation in the ordered set where uuid = &#63;.
	 *
	 * @param postconditionRelationId the primary key of the current postcondition relation
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next postcondition relation
	 * @throws NoSuchPostconditionRelationException if a postcondition relation with the primary key could not be found
	 */
	@Override
	public PostconditionRelation[] findByUuid_PrevAndNext(
		long postconditionRelationId, String uuid,
		OrderByComparator<PostconditionRelation> orderByComparator)
		throws NoSuchPostconditionRelationException {
		uuid = Objects.toString(uuid, "");

		PostconditionRelation postconditionRelation = findByPrimaryKey(postconditionRelationId);

		Session session = null;

		try {
			session = openSession();

			PostconditionRelation[] array = new PostconditionRelationImpl[3];

			array[0] = getByUuid_PrevAndNext(session, postconditionRelation,
					uuid, orderByComparator, true);

			array[1] = postconditionRelation;

			array[2] = getByUuid_PrevAndNext(session, postconditionRelation,
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

	protected PostconditionRelation getByUuid_PrevAndNext(Session session,
		PostconditionRelation postconditionRelation, String uuid,
		OrderByComparator<PostconditionRelation> orderByComparator,
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

		query.append(_SQL_SELECT_POSTCONDITIONRELATION_WHERE);

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
			query.append(PostconditionRelationModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(postconditionRelation);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PostconditionRelation> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the postcondition relations where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (PostconditionRelation postconditionRelation : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(postconditionRelation);
		}
	}

	/**
	 * Returns the number of postcondition relations where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching postcondition relations
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_POSTCONDITIONRELATION_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "postconditionRelation.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "postconditionRelation.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(postconditionRelation.uuid IS NULL OR postconditionRelation.uuid = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CLASSNAMEIDCLASSPK =
		new FinderPath(PostconditionRelationModelImpl.ENTITY_CACHE_ENABLED,
			PostconditionRelationModelImpl.FINDER_CACHE_ENABLED,
			PostconditionRelationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByClassNameIdClassPK",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSNAMEIDCLASSPK =
		new FinderPath(PostconditionRelationModelImpl.ENTITY_CACHE_ENABLED,
			PostconditionRelationModelImpl.FINDER_CACHE_ENABLED,
			PostconditionRelationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByClassNameIdClassPK",
			new String[] { Long.class.getName(), Long.class.getName() },
			PostconditionRelationModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			PostconditionRelationModelImpl.CLASSPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CLASSNAMEIDCLASSPK = new FinderPath(PostconditionRelationModelImpl.ENTITY_CACHE_ENABLED,
			PostconditionRelationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByClassNameIdClassPK",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the postcondition relations where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching postcondition relations
	 */
	@Override
	public List<PostconditionRelation> findByClassNameIdClassPK(
		long classNameId, long classPK) {
		return findByClassNameIdClassPK(classNameId, classPK,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the postcondition relations where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostconditionRelationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of postcondition relations
	 * @param end the upper bound of the range of postcondition relations (not inclusive)
	 * @return the range of matching postcondition relations
	 */
	@Override
	public List<PostconditionRelation> findByClassNameIdClassPK(
		long classNameId, long classPK, int start, int end) {
		return findByClassNameIdClassPK(classNameId, classPK, start, end, null);
	}

	/**
	 * Returns an ordered range of all the postcondition relations where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostconditionRelationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of postcondition relations
	 * @param end the upper bound of the range of postcondition relations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching postcondition relations
	 */
	@Override
	public List<PostconditionRelation> findByClassNameIdClassPK(
		long classNameId, long classPK, int start, int end,
		OrderByComparator<PostconditionRelation> orderByComparator) {
		return findByClassNameIdClassPK(classNameId, classPK, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the postcondition relations where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostconditionRelationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of postcondition relations
	 * @param end the upper bound of the range of postcondition relations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching postcondition relations
	 */
	@Override
	public List<PostconditionRelation> findByClassNameIdClassPK(
		long classNameId, long classPK, int start, int end,
		OrderByComparator<PostconditionRelation> orderByComparator,
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

		List<PostconditionRelation> list = null;

		if (retrieveFromCache) {
			list = (List<PostconditionRelation>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PostconditionRelation postconditionRelation : list) {
					if ((classNameId != postconditionRelation.getClassNameId()) ||
							(classPK != postconditionRelation.getClassPK())) {
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

			query.append(_SQL_SELECT_POSTCONDITIONRELATION_WHERE);

			query.append(_FINDER_COLUMN_CLASSNAMEIDCLASSPK_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_CLASSNAMEIDCLASSPK_CLASSPK_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PostconditionRelationModelImpl.ORDER_BY_JPQL);
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
					list = (List<PostconditionRelation>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PostconditionRelation>)QueryUtil.list(q,
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
	 * Returns the first postcondition relation in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching postcondition relation
	 * @throws NoSuchPostconditionRelationException if a matching postcondition relation could not be found
	 */
	@Override
	public PostconditionRelation findByClassNameIdClassPK_First(
		long classNameId, long classPK,
		OrderByComparator<PostconditionRelation> orderByComparator)
		throws NoSuchPostconditionRelationException {
		PostconditionRelation postconditionRelation = fetchByClassNameIdClassPK_First(classNameId,
				classPK, orderByComparator);

		if (postconditionRelation != null) {
			return postconditionRelation;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append("}");

		throw new NoSuchPostconditionRelationException(msg.toString());
	}

	/**
	 * Returns the first postcondition relation in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching postcondition relation, or <code>null</code> if a matching postcondition relation could not be found
	 */
	@Override
	public PostconditionRelation fetchByClassNameIdClassPK_First(
		long classNameId, long classPK,
		OrderByComparator<PostconditionRelation> orderByComparator) {
		List<PostconditionRelation> list = findByClassNameIdClassPK(classNameId,
				classPK, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last postcondition relation in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching postcondition relation
	 * @throws NoSuchPostconditionRelationException if a matching postcondition relation could not be found
	 */
	@Override
	public PostconditionRelation findByClassNameIdClassPK_Last(
		long classNameId, long classPK,
		OrderByComparator<PostconditionRelation> orderByComparator)
		throws NoSuchPostconditionRelationException {
		PostconditionRelation postconditionRelation = fetchByClassNameIdClassPK_Last(classNameId,
				classPK, orderByComparator);

		if (postconditionRelation != null) {
			return postconditionRelation;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append("}");

		throw new NoSuchPostconditionRelationException(msg.toString());
	}

	/**
	 * Returns the last postcondition relation in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching postcondition relation, or <code>null</code> if a matching postcondition relation could not be found
	 */
	@Override
	public PostconditionRelation fetchByClassNameIdClassPK_Last(
		long classNameId, long classPK,
		OrderByComparator<PostconditionRelation> orderByComparator) {
		int count = countByClassNameIdClassPK(classNameId, classPK);

		if (count == 0) {
			return null;
		}

		List<PostconditionRelation> list = findByClassNameIdClassPK(classNameId,
				classPK, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the postcondition relations before and after the current postcondition relation in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param postconditionRelationId the primary key of the current postcondition relation
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next postcondition relation
	 * @throws NoSuchPostconditionRelationException if a postcondition relation with the primary key could not be found
	 */
	@Override
	public PostconditionRelation[] findByClassNameIdClassPK_PrevAndNext(
		long postconditionRelationId, long classNameId, long classPK,
		OrderByComparator<PostconditionRelation> orderByComparator)
		throws NoSuchPostconditionRelationException {
		PostconditionRelation postconditionRelation = findByPrimaryKey(postconditionRelationId);

		Session session = null;

		try {
			session = openSession();

			PostconditionRelation[] array = new PostconditionRelationImpl[3];

			array[0] = getByClassNameIdClassPK_PrevAndNext(session,
					postconditionRelation, classNameId, classPK,
					orderByComparator, true);

			array[1] = postconditionRelation;

			array[2] = getByClassNameIdClassPK_PrevAndNext(session,
					postconditionRelation, classNameId, classPK,
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

	protected PostconditionRelation getByClassNameIdClassPK_PrevAndNext(
		Session session, PostconditionRelation postconditionRelation,
		long classNameId, long classPK,
		OrderByComparator<PostconditionRelation> orderByComparator,
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

		query.append(_SQL_SELECT_POSTCONDITIONRELATION_WHERE);

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
			query.append(PostconditionRelationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(classNameId);

		qPos.add(classPK);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(postconditionRelation);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PostconditionRelation> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the postcondition relations where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 */
	@Override
	public void removeByClassNameIdClassPK(long classNameId, long classPK) {
		for (PostconditionRelation postconditionRelation : findByClassNameIdClassPK(
				classNameId, classPK, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(postconditionRelation);
		}
	}

	/**
	 * Returns the number of postcondition relations where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching postcondition relations
	 */
	@Override
	public int countByClassNameIdClassPK(long classNameId, long classPK) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CLASSNAMEIDCLASSPK;

		Object[] finderArgs = new Object[] { classNameId, classPK };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_POSTCONDITIONRELATION_WHERE);

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

	private static final String _FINDER_COLUMN_CLASSNAMEIDCLASSPK_CLASSNAMEID_2 = "postconditionRelation.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_CLASSNAMEIDCLASSPK_CLASSPK_2 = "postconditionRelation.classPK = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CLASSNAMEPOSTCONDITIONIDCLASSNAMEIDCLASSPK =
		new FinderPath(PostconditionRelationModelImpl.ENTITY_CACHE_ENABLED,
			PostconditionRelationModelImpl.FINDER_CACHE_ENABLED,
			PostconditionRelationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByClassNamePostconditionIdClassNameIdClassPK",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSNAMEPOSTCONDITIONIDCLASSNAMEIDCLASSPK =
		new FinderPath(PostconditionRelationModelImpl.ENTITY_CACHE_ENABLED,
			PostconditionRelationModelImpl.FINDER_CACHE_ENABLED,
			PostconditionRelationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByClassNamePostconditionIdClassNameIdClassPK",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			PostconditionRelationModelImpl.CLASSNAMEPOSTCONDITIONID_COLUMN_BITMASK |
			PostconditionRelationModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			PostconditionRelationModelImpl.CLASSPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CLASSNAMEPOSTCONDITIONIDCLASSNAMEIDCLASSPK =
		new FinderPath(PostconditionRelationModelImpl.ENTITY_CACHE_ENABLED,
			PostconditionRelationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByClassNamePostconditionIdClassNameIdClassPK",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});

	/**
	 * Returns all the postcondition relations where classNamePostconditionId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNamePostconditionId the class name postcondition ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching postcondition relations
	 */
	@Override
	public List<PostconditionRelation> findByClassNamePostconditionIdClassNameIdClassPK(
		long classNamePostconditionId, long classNameId, long classPK) {
		return findByClassNamePostconditionIdClassNameIdClassPK(classNamePostconditionId,
			classNameId, classPK, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the postcondition relations where classNamePostconditionId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostconditionRelationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNamePostconditionId the class name postcondition ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of postcondition relations
	 * @param end the upper bound of the range of postcondition relations (not inclusive)
	 * @return the range of matching postcondition relations
	 */
	@Override
	public List<PostconditionRelation> findByClassNamePostconditionIdClassNameIdClassPK(
		long classNamePostconditionId, long classNameId, long classPK,
		int start, int end) {
		return findByClassNamePostconditionIdClassNameIdClassPK(classNamePostconditionId,
			classNameId, classPK, start, end, null);
	}

	/**
	 * Returns an ordered range of all the postcondition relations where classNamePostconditionId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostconditionRelationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNamePostconditionId the class name postcondition ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of postcondition relations
	 * @param end the upper bound of the range of postcondition relations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching postcondition relations
	 */
	@Override
	public List<PostconditionRelation> findByClassNamePostconditionIdClassNameIdClassPK(
		long classNamePostconditionId, long classNameId, long classPK,
		int start, int end,
		OrderByComparator<PostconditionRelation> orderByComparator) {
		return findByClassNamePostconditionIdClassNameIdClassPK(classNamePostconditionId,
			classNameId, classPK, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the postcondition relations where classNamePostconditionId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostconditionRelationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNamePostconditionId the class name postcondition ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of postcondition relations
	 * @param end the upper bound of the range of postcondition relations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching postcondition relations
	 */
	@Override
	public List<PostconditionRelation> findByClassNamePostconditionIdClassNameIdClassPK(
		long classNamePostconditionId, long classNameId, long classPK,
		int start, int end,
		OrderByComparator<PostconditionRelation> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSNAMEPOSTCONDITIONIDCLASSNAMEIDCLASSPK;
			finderArgs = new Object[] {
					classNamePostconditionId, classNameId, classPK
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CLASSNAMEPOSTCONDITIONIDCLASSNAMEIDCLASSPK;
			finderArgs = new Object[] {
					classNamePostconditionId, classNameId, classPK,
					
					start, end, orderByComparator
				};
		}

		List<PostconditionRelation> list = null;

		if (retrieveFromCache) {
			list = (List<PostconditionRelation>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PostconditionRelation postconditionRelation : list) {
					if ((classNamePostconditionId != postconditionRelation.getClassNamePostconditionId()) ||
							(classNameId != postconditionRelation.getClassNameId()) ||
							(classPK != postconditionRelation.getClassPK())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_POSTCONDITIONRELATION_WHERE);

			query.append(_FINDER_COLUMN_CLASSNAMEPOSTCONDITIONIDCLASSNAMEIDCLASSPK_CLASSNAMEPOSTCONDITIONID_2);

			query.append(_FINDER_COLUMN_CLASSNAMEPOSTCONDITIONIDCLASSNAMEIDCLASSPK_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_CLASSNAMEPOSTCONDITIONIDCLASSNAMEIDCLASSPK_CLASSPK_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PostconditionRelationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNamePostconditionId);

				qPos.add(classNameId);

				qPos.add(classPK);

				if (!pagination) {
					list = (List<PostconditionRelation>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PostconditionRelation>)QueryUtil.list(q,
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
	 * Returns the first postcondition relation in the ordered set where classNamePostconditionId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNamePostconditionId the class name postcondition ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching postcondition relation
	 * @throws NoSuchPostconditionRelationException if a matching postcondition relation could not be found
	 */
	@Override
	public PostconditionRelation findByClassNamePostconditionIdClassNameIdClassPK_First(
		long classNamePostconditionId, long classNameId, long classPK,
		OrderByComparator<PostconditionRelation> orderByComparator)
		throws NoSuchPostconditionRelationException {
		PostconditionRelation postconditionRelation = fetchByClassNamePostconditionIdClassNameIdClassPK_First(classNamePostconditionId,
				classNameId, classPK, orderByComparator);

		if (postconditionRelation != null) {
			return postconditionRelation;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNamePostconditionId=");
		msg.append(classNamePostconditionId);

		msg.append(", classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append("}");

		throw new NoSuchPostconditionRelationException(msg.toString());
	}

	/**
	 * Returns the first postcondition relation in the ordered set where classNamePostconditionId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNamePostconditionId the class name postcondition ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching postcondition relation, or <code>null</code> if a matching postcondition relation could not be found
	 */
	@Override
	public PostconditionRelation fetchByClassNamePostconditionIdClassNameIdClassPK_First(
		long classNamePostconditionId, long classNameId, long classPK,
		OrderByComparator<PostconditionRelation> orderByComparator) {
		List<PostconditionRelation> list = findByClassNamePostconditionIdClassNameIdClassPK(classNamePostconditionId,
				classNameId, classPK, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last postcondition relation in the ordered set where classNamePostconditionId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNamePostconditionId the class name postcondition ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching postcondition relation
	 * @throws NoSuchPostconditionRelationException if a matching postcondition relation could not be found
	 */
	@Override
	public PostconditionRelation findByClassNamePostconditionIdClassNameIdClassPK_Last(
		long classNamePostconditionId, long classNameId, long classPK,
		OrderByComparator<PostconditionRelation> orderByComparator)
		throws NoSuchPostconditionRelationException {
		PostconditionRelation postconditionRelation = fetchByClassNamePostconditionIdClassNameIdClassPK_Last(classNamePostconditionId,
				classNameId, classPK, orderByComparator);

		if (postconditionRelation != null) {
			return postconditionRelation;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNamePostconditionId=");
		msg.append(classNamePostconditionId);

		msg.append(", classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append("}");

		throw new NoSuchPostconditionRelationException(msg.toString());
	}

	/**
	 * Returns the last postcondition relation in the ordered set where classNamePostconditionId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNamePostconditionId the class name postcondition ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching postcondition relation, or <code>null</code> if a matching postcondition relation could not be found
	 */
	@Override
	public PostconditionRelation fetchByClassNamePostconditionIdClassNameIdClassPK_Last(
		long classNamePostconditionId, long classNameId, long classPK,
		OrderByComparator<PostconditionRelation> orderByComparator) {
		int count = countByClassNamePostconditionIdClassNameIdClassPK(classNamePostconditionId,
				classNameId, classPK);

		if (count == 0) {
			return null;
		}

		List<PostconditionRelation> list = findByClassNamePostconditionIdClassNameIdClassPK(classNamePostconditionId,
				classNameId, classPK, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the postcondition relations before and after the current postcondition relation in the ordered set where classNamePostconditionId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param postconditionRelationId the primary key of the current postcondition relation
	 * @param classNamePostconditionId the class name postcondition ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next postcondition relation
	 * @throws NoSuchPostconditionRelationException if a postcondition relation with the primary key could not be found
	 */
	@Override
	public PostconditionRelation[] findByClassNamePostconditionIdClassNameIdClassPK_PrevAndNext(
		long postconditionRelationId, long classNamePostconditionId,
		long classNameId, long classPK,
		OrderByComparator<PostconditionRelation> orderByComparator)
		throws NoSuchPostconditionRelationException {
		PostconditionRelation postconditionRelation = findByPrimaryKey(postconditionRelationId);

		Session session = null;

		try {
			session = openSession();

			PostconditionRelation[] array = new PostconditionRelationImpl[3];

			array[0] = getByClassNamePostconditionIdClassNameIdClassPK_PrevAndNext(session,
					postconditionRelation, classNamePostconditionId,
					classNameId, classPK, orderByComparator, true);

			array[1] = postconditionRelation;

			array[2] = getByClassNamePostconditionIdClassNameIdClassPK_PrevAndNext(session,
					postconditionRelation, classNamePostconditionId,
					classNameId, classPK, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected PostconditionRelation getByClassNamePostconditionIdClassNameIdClassPK_PrevAndNext(
		Session session, PostconditionRelation postconditionRelation,
		long classNamePostconditionId, long classNameId, long classPK,
		OrderByComparator<PostconditionRelation> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_POSTCONDITIONRELATION_WHERE);

		query.append(_FINDER_COLUMN_CLASSNAMEPOSTCONDITIONIDCLASSNAMEIDCLASSPK_CLASSNAMEPOSTCONDITIONID_2);

		query.append(_FINDER_COLUMN_CLASSNAMEPOSTCONDITIONIDCLASSNAMEIDCLASSPK_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_CLASSNAMEPOSTCONDITIONIDCLASSNAMEIDCLASSPK_CLASSPK_2);

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
			query.append(PostconditionRelationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(classNamePostconditionId);

		qPos.add(classNameId);

		qPos.add(classPK);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(postconditionRelation);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PostconditionRelation> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the postcondition relations where classNamePostconditionId = &#63; and classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNamePostconditionId the class name postcondition ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 */
	@Override
	public void removeByClassNamePostconditionIdClassNameIdClassPK(
		long classNamePostconditionId, long classNameId, long classPK) {
		for (PostconditionRelation postconditionRelation : findByClassNamePostconditionIdClassNameIdClassPK(
				classNamePostconditionId, classNameId, classPK,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(postconditionRelation);
		}
	}

	/**
	 * Returns the number of postcondition relations where classNamePostconditionId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNamePostconditionId the class name postcondition ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching postcondition relations
	 */
	@Override
	public int countByClassNamePostconditionIdClassNameIdClassPK(
		long classNamePostconditionId, long classNameId, long classPK) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CLASSNAMEPOSTCONDITIONIDCLASSNAMEIDCLASSPK;

		Object[] finderArgs = new Object[] {
				classNamePostconditionId, classNameId, classPK
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_POSTCONDITIONRELATION_WHERE);

			query.append(_FINDER_COLUMN_CLASSNAMEPOSTCONDITIONIDCLASSNAMEIDCLASSPK_CLASSNAMEPOSTCONDITIONID_2);

			query.append(_FINDER_COLUMN_CLASSNAMEPOSTCONDITIONIDCLASSNAMEIDCLASSPK_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_CLASSNAMEPOSTCONDITIONIDCLASSNAMEIDCLASSPK_CLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNamePostconditionId);

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

	private static final String _FINDER_COLUMN_CLASSNAMEPOSTCONDITIONIDCLASSNAMEIDCLASSPK_CLASSNAMEPOSTCONDITIONID_2 =
		"postconditionRelation.classNamePostconditionId = ? AND ";
	private static final String _FINDER_COLUMN_CLASSNAMEPOSTCONDITIONIDCLASSNAMEIDCLASSPK_CLASSNAMEID_2 =
		"postconditionRelation.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_CLASSNAMEPOSTCONDITIONIDCLASSNAMEIDCLASSPK_CLASSPK_2 =
		"postconditionRelation.classPK = ?";

	public PostconditionRelationPersistenceImpl() {
		setModelClass(PostconditionRelation.class);

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
	 * Caches the postcondition relation in the entity cache if it is enabled.
	 *
	 * @param postconditionRelation the postcondition relation
	 */
	@Override
	public void cacheResult(PostconditionRelation postconditionRelation) {
		entityCache.putResult(PostconditionRelationModelImpl.ENTITY_CACHE_ENABLED,
			PostconditionRelationImpl.class,
			postconditionRelation.getPrimaryKey(), postconditionRelation);

		postconditionRelation.resetOriginalValues();
	}

	/**
	 * Caches the postcondition relations in the entity cache if it is enabled.
	 *
	 * @param postconditionRelations the postcondition relations
	 */
	@Override
	public void cacheResult(List<PostconditionRelation> postconditionRelations) {
		for (PostconditionRelation postconditionRelation : postconditionRelations) {
			if (entityCache.getResult(
						PostconditionRelationModelImpl.ENTITY_CACHE_ENABLED,
						PostconditionRelationImpl.class,
						postconditionRelation.getPrimaryKey()) == null) {
				cacheResult(postconditionRelation);
			}
			else {
				postconditionRelation.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all postcondition relations.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(PostconditionRelationImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the postcondition relation.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(PostconditionRelation postconditionRelation) {
		entityCache.removeResult(PostconditionRelationModelImpl.ENTITY_CACHE_ENABLED,
			PostconditionRelationImpl.class,
			postconditionRelation.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<PostconditionRelation> postconditionRelations) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (PostconditionRelation postconditionRelation : postconditionRelations) {
			entityCache.removeResult(PostconditionRelationModelImpl.ENTITY_CACHE_ENABLED,
				PostconditionRelationImpl.class,
				postconditionRelation.getPrimaryKey());
		}
	}

	/**
	 * Creates a new postcondition relation with the primary key. Does not add the postcondition relation to the database.
	 *
	 * @param postconditionRelationId the primary key for the new postcondition relation
	 * @return the new postcondition relation
	 */
	@Override
	public PostconditionRelation create(long postconditionRelationId) {
		PostconditionRelation postconditionRelation = new PostconditionRelationImpl();

		postconditionRelation.setNew(true);
		postconditionRelation.setPrimaryKey(postconditionRelationId);

		String uuid = PortalUUIDUtil.generate();

		postconditionRelation.setUuid(uuid);

		return postconditionRelation;
	}

	/**
	 * Removes the postcondition relation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param postconditionRelationId the primary key of the postcondition relation
	 * @return the postcondition relation that was removed
	 * @throws NoSuchPostconditionRelationException if a postcondition relation with the primary key could not be found
	 */
	@Override
	public PostconditionRelation remove(long postconditionRelationId)
		throws NoSuchPostconditionRelationException {
		return remove((Serializable)postconditionRelationId);
	}

	/**
	 * Removes the postcondition relation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the postcondition relation
	 * @return the postcondition relation that was removed
	 * @throws NoSuchPostconditionRelationException if a postcondition relation with the primary key could not be found
	 */
	@Override
	public PostconditionRelation remove(Serializable primaryKey)
		throws NoSuchPostconditionRelationException {
		Session session = null;

		try {
			session = openSession();

			PostconditionRelation postconditionRelation = (PostconditionRelation)session.get(PostconditionRelationImpl.class,
					primaryKey);

			if (postconditionRelation == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchPostconditionRelationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(postconditionRelation);
		}
		catch (NoSuchPostconditionRelationException nsee) {
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
	protected PostconditionRelation removeImpl(
		PostconditionRelation postconditionRelation) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(postconditionRelation)) {
				postconditionRelation = (PostconditionRelation)session.get(PostconditionRelationImpl.class,
						postconditionRelation.getPrimaryKeyObj());
			}

			if (postconditionRelation != null) {
				session.delete(postconditionRelation);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (postconditionRelation != null) {
			clearCache(postconditionRelation);
		}

		return postconditionRelation;
	}

	@Override
	public PostconditionRelation updateImpl(
		PostconditionRelation postconditionRelation) {
		boolean isNew = postconditionRelation.isNew();

		if (!(postconditionRelation instanceof PostconditionRelationModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(postconditionRelation.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(postconditionRelation);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in postconditionRelation proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom PostconditionRelation implementation " +
				postconditionRelation.getClass());
		}

		PostconditionRelationModelImpl postconditionRelationModelImpl = (PostconditionRelationModelImpl)postconditionRelation;

		if (Validator.isNull(postconditionRelation.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			postconditionRelation.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (postconditionRelation.isNew()) {
				session.save(postconditionRelation);

				postconditionRelation.setNew(false);
			}
			else {
				postconditionRelation = (PostconditionRelation)session.merge(postconditionRelation);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!PostconditionRelationModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					postconditionRelationModelImpl.getUuid()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					postconditionRelationModelImpl.getClassNameId(),
					postconditionRelationModelImpl.getClassPK()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CLASSNAMEIDCLASSPK,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSNAMEIDCLASSPK,
				args);

			args = new Object[] {
					postconditionRelationModelImpl.getClassNamePostconditionId(),
					postconditionRelationModelImpl.getClassNameId(),
					postconditionRelationModelImpl.getClassPK()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CLASSNAMEPOSTCONDITIONIDCLASSNAMEIDCLASSPK,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSNAMEPOSTCONDITIONIDCLASSNAMEIDCLASSPK,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((postconditionRelationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						postconditionRelationModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { postconditionRelationModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((postconditionRelationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSNAMEIDCLASSPK.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						postconditionRelationModelImpl.getOriginalClassNameId(),
						postconditionRelationModelImpl.getOriginalClassPK()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CLASSNAMEIDCLASSPK,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSNAMEIDCLASSPK,
					args);

				args = new Object[] {
						postconditionRelationModelImpl.getClassNameId(),
						postconditionRelationModelImpl.getClassPK()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CLASSNAMEIDCLASSPK,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSNAMEIDCLASSPK,
					args);
			}

			if ((postconditionRelationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSNAMEPOSTCONDITIONIDCLASSNAMEIDCLASSPK.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						postconditionRelationModelImpl.getOriginalClassNamePostconditionId(),
						postconditionRelationModelImpl.getOriginalClassNameId(),
						postconditionRelationModelImpl.getOriginalClassPK()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CLASSNAMEPOSTCONDITIONIDCLASSNAMEIDCLASSPK,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSNAMEPOSTCONDITIONIDCLASSNAMEIDCLASSPK,
					args);

				args = new Object[] {
						postconditionRelationModelImpl.getClassNamePostconditionId(),
						postconditionRelationModelImpl.getClassNameId(),
						postconditionRelationModelImpl.getClassPK()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CLASSNAMEPOSTCONDITIONIDCLASSNAMEIDCLASSPK,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSNAMEPOSTCONDITIONIDCLASSNAMEIDCLASSPK,
					args);
			}
		}

		entityCache.putResult(PostconditionRelationModelImpl.ENTITY_CACHE_ENABLED,
			PostconditionRelationImpl.class,
			postconditionRelation.getPrimaryKey(), postconditionRelation, false);

		postconditionRelation.resetOriginalValues();

		return postconditionRelation;
	}

	/**
	 * Returns the postcondition relation with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the postcondition relation
	 * @return the postcondition relation
	 * @throws NoSuchPostconditionRelationException if a postcondition relation with the primary key could not be found
	 */
	@Override
	public PostconditionRelation findByPrimaryKey(Serializable primaryKey)
		throws NoSuchPostconditionRelationException {
		PostconditionRelation postconditionRelation = fetchByPrimaryKey(primaryKey);

		if (postconditionRelation == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchPostconditionRelationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return postconditionRelation;
	}

	/**
	 * Returns the postcondition relation with the primary key or throws a {@link NoSuchPostconditionRelationException} if it could not be found.
	 *
	 * @param postconditionRelationId the primary key of the postcondition relation
	 * @return the postcondition relation
	 * @throws NoSuchPostconditionRelationException if a postcondition relation with the primary key could not be found
	 */
	@Override
	public PostconditionRelation findByPrimaryKey(long postconditionRelationId)
		throws NoSuchPostconditionRelationException {
		return findByPrimaryKey((Serializable)postconditionRelationId);
	}

	/**
	 * Returns the postcondition relation with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the postcondition relation
	 * @return the postcondition relation, or <code>null</code> if a postcondition relation with the primary key could not be found
	 */
	@Override
	public PostconditionRelation fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(PostconditionRelationModelImpl.ENTITY_CACHE_ENABLED,
				PostconditionRelationImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		PostconditionRelation postconditionRelation = (PostconditionRelation)serializable;

		if (postconditionRelation == null) {
			Session session = null;

			try {
				session = openSession();

				postconditionRelation = (PostconditionRelation)session.get(PostconditionRelationImpl.class,
						primaryKey);

				if (postconditionRelation != null) {
					cacheResult(postconditionRelation);
				}
				else {
					entityCache.putResult(PostconditionRelationModelImpl.ENTITY_CACHE_ENABLED,
						PostconditionRelationImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(PostconditionRelationModelImpl.ENTITY_CACHE_ENABLED,
					PostconditionRelationImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return postconditionRelation;
	}

	/**
	 * Returns the postcondition relation with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param postconditionRelationId the primary key of the postcondition relation
	 * @return the postcondition relation, or <code>null</code> if a postcondition relation with the primary key could not be found
	 */
	@Override
	public PostconditionRelation fetchByPrimaryKey(long postconditionRelationId) {
		return fetchByPrimaryKey((Serializable)postconditionRelationId);
	}

	@Override
	public Map<Serializable, PostconditionRelation> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, PostconditionRelation> map = new HashMap<Serializable, PostconditionRelation>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			PostconditionRelation postconditionRelation = fetchByPrimaryKey(primaryKey);

			if (postconditionRelation != null) {
				map.put(primaryKey, postconditionRelation);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(PostconditionRelationModelImpl.ENTITY_CACHE_ENABLED,
					PostconditionRelationImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (PostconditionRelation)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_POSTCONDITIONRELATION_WHERE_PKS_IN);

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

			for (PostconditionRelation postconditionRelation : (List<PostconditionRelation>)q.list()) {
				map.put(postconditionRelation.getPrimaryKeyObj(),
					postconditionRelation);

				cacheResult(postconditionRelation);

				uncachedPrimaryKeys.remove(postconditionRelation.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(PostconditionRelationModelImpl.ENTITY_CACHE_ENABLED,
					PostconditionRelationImpl.class, primaryKey, nullModel);
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
	 * Returns all the postcondition relations.
	 *
	 * @return the postcondition relations
	 */
	@Override
	public List<PostconditionRelation> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the postcondition relations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostconditionRelationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of postcondition relations
	 * @param end the upper bound of the range of postcondition relations (not inclusive)
	 * @return the range of postcondition relations
	 */
	@Override
	public List<PostconditionRelation> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the postcondition relations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostconditionRelationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of postcondition relations
	 * @param end the upper bound of the range of postcondition relations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of postcondition relations
	 */
	@Override
	public List<PostconditionRelation> findAll(int start, int end,
		OrderByComparator<PostconditionRelation> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the postcondition relations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostconditionRelationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of postcondition relations
	 * @param end the upper bound of the range of postcondition relations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of postcondition relations
	 */
	@Override
	public List<PostconditionRelation> findAll(int start, int end,
		OrderByComparator<PostconditionRelation> orderByComparator,
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

		List<PostconditionRelation> list = null;

		if (retrieveFromCache) {
			list = (List<PostconditionRelation>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_POSTCONDITIONRELATION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_POSTCONDITIONRELATION;

				if (pagination) {
					sql = sql.concat(PostconditionRelationModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<PostconditionRelation>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PostconditionRelation>)QueryUtil.list(q,
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
	 * Removes all the postcondition relations from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (PostconditionRelation postconditionRelation : findAll()) {
			remove(postconditionRelation);
		}
	}

	/**
	 * Returns the number of postcondition relations.
	 *
	 * @return the number of postcondition relations
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_POSTCONDITIONRELATION);

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
		return PostconditionRelationModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the postcondition relation persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(PostconditionRelationImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_POSTCONDITIONRELATION = "SELECT postconditionRelation FROM PostconditionRelation postconditionRelation";
	private static final String _SQL_SELECT_POSTCONDITIONRELATION_WHERE_PKS_IN = "SELECT postconditionRelation FROM PostconditionRelation postconditionRelation WHERE postconditionRelationId IN (";
	private static final String _SQL_SELECT_POSTCONDITIONRELATION_WHERE = "SELECT postconditionRelation FROM PostconditionRelation postconditionRelation WHERE ";
	private static final String _SQL_COUNT_POSTCONDITIONRELATION = "SELECT COUNT(postconditionRelation) FROM PostconditionRelation postconditionRelation";
	private static final String _SQL_COUNT_POSTCONDITIONRELATION_WHERE = "SELECT COUNT(postconditionRelation) FROM PostconditionRelation postconditionRelation WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "postconditionRelation.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PostconditionRelation exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No PostconditionRelation exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(PostconditionRelationPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}