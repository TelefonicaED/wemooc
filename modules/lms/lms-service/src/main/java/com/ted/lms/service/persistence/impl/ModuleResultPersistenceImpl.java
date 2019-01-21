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

import aQute.bnd.annotation.ProviderType;

import com.liferay.petra.string.StringBundler;

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
import com.liferay.portal.spring.extender.service.ServiceReference;

import com.ted.lms.exception.NoSuchModuleResultException;
import com.ted.lms.model.ModuleResult;
import com.ted.lms.model.impl.ModuleResultImpl;
import com.ted.lms.model.impl.ModuleResultModelImpl;
import com.ted.lms.service.persistence.ModuleResultPersistence;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the module result service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ModuleResultPersistence
 * @see com.ted.lms.service.persistence.ModuleResultUtil
 * @generated
 */
@ProviderType
public class ModuleResultPersistenceImpl extends BasePersistenceImpl<ModuleResult>
	implements ModuleResultPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ModuleResultUtil} to access the module result persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ModuleResultImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ModuleResultModelImpl.ENTITY_CACHE_ENABLED,
			ModuleResultModelImpl.FINDER_CACHE_ENABLED, ModuleResultImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ModuleResultModelImpl.ENTITY_CACHE_ENABLED,
			ModuleResultModelImpl.FINDER_CACHE_ENABLED, ModuleResultImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ModuleResultModelImpl.ENTITY_CACHE_ENABLED,
			ModuleResultModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_MODULEID = new FinderPath(ModuleResultModelImpl.ENTITY_CACHE_ENABLED,
			ModuleResultModelImpl.FINDER_CACHE_ENABLED, ModuleResultImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByModuleId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODULEID =
		new FinderPath(ModuleResultModelImpl.ENTITY_CACHE_ENABLED,
			ModuleResultModelImpl.FINDER_CACHE_ENABLED, ModuleResultImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByModuleId",
			new String[] { Long.class.getName() },
			ModuleResultModelImpl.MODULEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_MODULEID = new FinderPath(ModuleResultModelImpl.ENTITY_CACHE_ENABLED,
			ModuleResultModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByModuleId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the module results where moduleId = &#63;.
	 *
	 * @param moduleId the module ID
	 * @return the matching module results
	 */
	@Override
	public List<ModuleResult> findByModuleId(long moduleId) {
		return findByModuleId(moduleId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the module results where moduleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param moduleId the module ID
	 * @param start the lower bound of the range of module results
	 * @param end the upper bound of the range of module results (not inclusive)
	 * @return the range of matching module results
	 */
	@Override
	public List<ModuleResult> findByModuleId(long moduleId, int start, int end) {
		return findByModuleId(moduleId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the module results where moduleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param moduleId the module ID
	 * @param start the lower bound of the range of module results
	 * @param end the upper bound of the range of module results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching module results
	 */
	@Override
	public List<ModuleResult> findByModuleId(long moduleId, int start, int end,
		OrderByComparator<ModuleResult> orderByComparator) {
		return findByModuleId(moduleId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the module results where moduleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param moduleId the module ID
	 * @param start the lower bound of the range of module results
	 * @param end the upper bound of the range of module results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching module results
	 */
	@Override
	public List<ModuleResult> findByModuleId(long moduleId, int start, int end,
		OrderByComparator<ModuleResult> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODULEID;
			finderArgs = new Object[] { moduleId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_MODULEID;
			finderArgs = new Object[] { moduleId, start, end, orderByComparator };
		}

		List<ModuleResult> list = null;

		if (retrieveFromCache) {
			list = (List<ModuleResult>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ModuleResult moduleResult : list) {
					if ((moduleId != moduleResult.getModuleId())) {
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

			query.append(_SQL_SELECT_MODULERESULT_WHERE);

			query.append(_FINDER_COLUMN_MODULEID_MODULEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ModuleResultModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(moduleId);

				if (!pagination) {
					list = (List<ModuleResult>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ModuleResult>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first module result in the ordered set where moduleId = &#63;.
	 *
	 * @param moduleId the module ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching module result
	 * @throws NoSuchModuleResultException if a matching module result could not be found
	 */
	@Override
	public ModuleResult findByModuleId_First(long moduleId,
		OrderByComparator<ModuleResult> orderByComparator)
		throws NoSuchModuleResultException {
		ModuleResult moduleResult = fetchByModuleId_First(moduleId,
				orderByComparator);

		if (moduleResult != null) {
			return moduleResult;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("moduleId=");
		msg.append(moduleId);

		msg.append("}");

		throw new NoSuchModuleResultException(msg.toString());
	}

	/**
	 * Returns the first module result in the ordered set where moduleId = &#63;.
	 *
	 * @param moduleId the module ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching module result, or <code>null</code> if a matching module result could not be found
	 */
	@Override
	public ModuleResult fetchByModuleId_First(long moduleId,
		OrderByComparator<ModuleResult> orderByComparator) {
		List<ModuleResult> list = findByModuleId(moduleId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last module result in the ordered set where moduleId = &#63;.
	 *
	 * @param moduleId the module ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching module result
	 * @throws NoSuchModuleResultException if a matching module result could not be found
	 */
	@Override
	public ModuleResult findByModuleId_Last(long moduleId,
		OrderByComparator<ModuleResult> orderByComparator)
		throws NoSuchModuleResultException {
		ModuleResult moduleResult = fetchByModuleId_Last(moduleId,
				orderByComparator);

		if (moduleResult != null) {
			return moduleResult;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("moduleId=");
		msg.append(moduleId);

		msg.append("}");

		throw new NoSuchModuleResultException(msg.toString());
	}

	/**
	 * Returns the last module result in the ordered set where moduleId = &#63;.
	 *
	 * @param moduleId the module ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching module result, or <code>null</code> if a matching module result could not be found
	 */
	@Override
	public ModuleResult fetchByModuleId_Last(long moduleId,
		OrderByComparator<ModuleResult> orderByComparator) {
		int count = countByModuleId(moduleId);

		if (count == 0) {
			return null;
		}

		List<ModuleResult> list = findByModuleId(moduleId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the module results before and after the current module result in the ordered set where moduleId = &#63;.
	 *
	 * @param mrId the primary key of the current module result
	 * @param moduleId the module ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next module result
	 * @throws NoSuchModuleResultException if a module result with the primary key could not be found
	 */
	@Override
	public ModuleResult[] findByModuleId_PrevAndNext(long mrId, long moduleId,
		OrderByComparator<ModuleResult> orderByComparator)
		throws NoSuchModuleResultException {
		ModuleResult moduleResult = findByPrimaryKey(mrId);

		Session session = null;

		try {
			session = openSession();

			ModuleResult[] array = new ModuleResultImpl[3];

			array[0] = getByModuleId_PrevAndNext(session, moduleResult,
					moduleId, orderByComparator, true);

			array[1] = moduleResult;

			array[2] = getByModuleId_PrevAndNext(session, moduleResult,
					moduleId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ModuleResult getByModuleId_PrevAndNext(Session session,
		ModuleResult moduleResult, long moduleId,
		OrderByComparator<ModuleResult> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MODULERESULT_WHERE);

		query.append(_FINDER_COLUMN_MODULEID_MODULEID_2);

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
			query.append(ModuleResultModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(moduleId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(moduleResult);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ModuleResult> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the module results where moduleId = &#63; from the database.
	 *
	 * @param moduleId the module ID
	 */
	@Override
	public void removeByModuleId(long moduleId) {
		for (ModuleResult moduleResult : findByModuleId(moduleId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(moduleResult);
		}
	}

	/**
	 * Returns the number of module results where moduleId = &#63;.
	 *
	 * @param moduleId the module ID
	 * @return the number of matching module results
	 */
	@Override
	public int countByModuleId(long moduleId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_MODULEID;

		Object[] finderArgs = new Object[] { moduleId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MODULERESULT_WHERE);

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

	private static final String _FINDER_COLUMN_MODULEID_MODULEID_2 = "moduleResult.moduleId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_MODULEIDUSERID = new FinderPath(ModuleResultModelImpl.ENTITY_CACHE_ENABLED,
			ModuleResultModelImpl.FINDER_CACHE_ENABLED, ModuleResultImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByModuleIdUserId",
			new String[] { Long.class.getName(), Long.class.getName() },
			ModuleResultModelImpl.MODULEID_COLUMN_BITMASK |
			ModuleResultModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_MODULEIDUSERID = new FinderPath(ModuleResultModelImpl.ENTITY_CACHE_ENABLED,
			ModuleResultModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByModuleIdUserId",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns the module result where moduleId = &#63; and userId = &#63; or throws a {@link NoSuchModuleResultException} if it could not be found.
	 *
	 * @param moduleId the module ID
	 * @param userId the user ID
	 * @return the matching module result
	 * @throws NoSuchModuleResultException if a matching module result could not be found
	 */
	@Override
	public ModuleResult findByModuleIdUserId(long moduleId, long userId)
		throws NoSuchModuleResultException {
		ModuleResult moduleResult = fetchByModuleIdUserId(moduleId, userId);

		if (moduleResult == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("moduleId=");
			msg.append(moduleId);

			msg.append(", userId=");
			msg.append(userId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchModuleResultException(msg.toString());
		}

		return moduleResult;
	}

	/**
	 * Returns the module result where moduleId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param moduleId the module ID
	 * @param userId the user ID
	 * @return the matching module result, or <code>null</code> if a matching module result could not be found
	 */
	@Override
	public ModuleResult fetchByModuleIdUserId(long moduleId, long userId) {
		return fetchByModuleIdUserId(moduleId, userId, true);
	}

	/**
	 * Returns the module result where moduleId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param moduleId the module ID
	 * @param userId the user ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching module result, or <code>null</code> if a matching module result could not be found
	 */
	@Override
	public ModuleResult fetchByModuleIdUserId(long moduleId, long userId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { moduleId, userId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_MODULEIDUSERID,
					finderArgs, this);
		}

		if (result instanceof ModuleResult) {
			ModuleResult moduleResult = (ModuleResult)result;

			if ((moduleId != moduleResult.getModuleId()) ||
					(userId != moduleResult.getUserId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_MODULERESULT_WHERE);

			query.append(_FINDER_COLUMN_MODULEIDUSERID_MODULEID_2);

			query.append(_FINDER_COLUMN_MODULEIDUSERID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(moduleId);

				qPos.add(userId);

				List<ModuleResult> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_MODULEIDUSERID,
						finderArgs, list);
				}
				else {
					ModuleResult moduleResult = list.get(0);

					result = moduleResult;

					cacheResult(moduleResult);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_MODULEIDUSERID,
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
			return (ModuleResult)result;
		}
	}

	/**
	 * Removes the module result where moduleId = &#63; and userId = &#63; from the database.
	 *
	 * @param moduleId the module ID
	 * @param userId the user ID
	 * @return the module result that was removed
	 */
	@Override
	public ModuleResult removeByModuleIdUserId(long moduleId, long userId)
		throws NoSuchModuleResultException {
		ModuleResult moduleResult = findByModuleIdUserId(moduleId, userId);

		return remove(moduleResult);
	}

	/**
	 * Returns the number of module results where moduleId = &#63; and userId = &#63;.
	 *
	 * @param moduleId the module ID
	 * @param userId the user ID
	 * @return the number of matching module results
	 */
	@Override
	public int countByModuleIdUserId(long moduleId, long userId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_MODULEIDUSERID;

		Object[] finderArgs = new Object[] { moduleId, userId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_MODULERESULT_WHERE);

			query.append(_FINDER_COLUMN_MODULEIDUSERID_MODULEID_2);

			query.append(_FINDER_COLUMN_MODULEIDUSERID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(moduleId);

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

	private static final String _FINDER_COLUMN_MODULEIDUSERID_MODULEID_2 = "moduleResult.moduleId = ? AND ";
	private static final String _FINDER_COLUMN_MODULEIDUSERID_USERID_2 = "moduleResult.userId = ?";

	public ModuleResultPersistenceImpl() {
		setModelClass(ModuleResult.class);
	}

	/**
	 * Caches the module result in the entity cache if it is enabled.
	 *
	 * @param moduleResult the module result
	 */
	@Override
	public void cacheResult(ModuleResult moduleResult) {
		entityCache.putResult(ModuleResultModelImpl.ENTITY_CACHE_ENABLED,
			ModuleResultImpl.class, moduleResult.getPrimaryKey(), moduleResult);

		finderCache.putResult(FINDER_PATH_FETCH_BY_MODULEIDUSERID,
			new Object[] { moduleResult.getModuleId(), moduleResult.getUserId() },
			moduleResult);

		moduleResult.resetOriginalValues();
	}

	/**
	 * Caches the module results in the entity cache if it is enabled.
	 *
	 * @param moduleResults the module results
	 */
	@Override
	public void cacheResult(List<ModuleResult> moduleResults) {
		for (ModuleResult moduleResult : moduleResults) {
			if (entityCache.getResult(
						ModuleResultModelImpl.ENTITY_CACHE_ENABLED,
						ModuleResultImpl.class, moduleResult.getPrimaryKey()) == null) {
				cacheResult(moduleResult);
			}
			else {
				moduleResult.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all module results.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ModuleResultImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the module result.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ModuleResult moduleResult) {
		entityCache.removeResult(ModuleResultModelImpl.ENTITY_CACHE_ENABLED,
			ModuleResultImpl.class, moduleResult.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((ModuleResultModelImpl)moduleResult, true);
	}

	@Override
	public void clearCache(List<ModuleResult> moduleResults) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ModuleResult moduleResult : moduleResults) {
			entityCache.removeResult(ModuleResultModelImpl.ENTITY_CACHE_ENABLED,
				ModuleResultImpl.class, moduleResult.getPrimaryKey());

			clearUniqueFindersCache((ModuleResultModelImpl)moduleResult, true);
		}
	}

	protected void cacheUniqueFindersCache(
		ModuleResultModelImpl moduleResultModelImpl) {
		Object[] args = new Object[] {
				moduleResultModelImpl.getModuleId(),
				moduleResultModelImpl.getUserId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_MODULEIDUSERID, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_MODULEIDUSERID, args,
			moduleResultModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		ModuleResultModelImpl moduleResultModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					moduleResultModelImpl.getModuleId(),
					moduleResultModelImpl.getUserId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_MODULEIDUSERID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_MODULEIDUSERID, args);
		}

		if ((moduleResultModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_MODULEIDUSERID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					moduleResultModelImpl.getOriginalModuleId(),
					moduleResultModelImpl.getOriginalUserId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_MODULEIDUSERID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_MODULEIDUSERID, args);
		}
	}

	/**
	 * Creates a new module result with the primary key. Does not add the module result to the database.
	 *
	 * @param mrId the primary key for the new module result
	 * @return the new module result
	 */
	@Override
	public ModuleResult create(long mrId) {
		ModuleResult moduleResult = new ModuleResultImpl();

		moduleResult.setNew(true);
		moduleResult.setPrimaryKey(mrId);

		moduleResult.setCompanyId(companyProvider.getCompanyId());

		return moduleResult;
	}

	/**
	 * Removes the module result with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param mrId the primary key of the module result
	 * @return the module result that was removed
	 * @throws NoSuchModuleResultException if a module result with the primary key could not be found
	 */
	@Override
	public ModuleResult remove(long mrId) throws NoSuchModuleResultException {
		return remove((Serializable)mrId);
	}

	/**
	 * Removes the module result with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the module result
	 * @return the module result that was removed
	 * @throws NoSuchModuleResultException if a module result with the primary key could not be found
	 */
	@Override
	public ModuleResult remove(Serializable primaryKey)
		throws NoSuchModuleResultException {
		Session session = null;

		try {
			session = openSession();

			ModuleResult moduleResult = (ModuleResult)session.get(ModuleResultImpl.class,
					primaryKey);

			if (moduleResult == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchModuleResultException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(moduleResult);
		}
		catch (NoSuchModuleResultException nsee) {
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
	protected ModuleResult removeImpl(ModuleResult moduleResult) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(moduleResult)) {
				moduleResult = (ModuleResult)session.get(ModuleResultImpl.class,
						moduleResult.getPrimaryKeyObj());
			}

			if (moduleResult != null) {
				session.delete(moduleResult);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (moduleResult != null) {
			clearCache(moduleResult);
		}

		return moduleResult;
	}

	@Override
	public ModuleResult updateImpl(ModuleResult moduleResult) {
		boolean isNew = moduleResult.isNew();

		if (!(moduleResult instanceof ModuleResultModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(moduleResult.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(moduleResult);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in moduleResult proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom ModuleResult implementation " +
				moduleResult.getClass());
		}

		ModuleResultModelImpl moduleResultModelImpl = (ModuleResultModelImpl)moduleResult;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (moduleResult.getCreateDate() == null)) {
			if (serviceContext == null) {
				moduleResult.setCreateDate(now);
			}
			else {
				moduleResult.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!moduleResultModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				moduleResult.setModifiedDate(now);
			}
			else {
				moduleResult.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (moduleResult.isNew()) {
				session.save(moduleResult);

				moduleResult.setNew(false);
			}
			else {
				moduleResult = (ModuleResult)session.merge(moduleResult);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ModuleResultModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { moduleResultModelImpl.getModuleId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_MODULEID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODULEID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((moduleResultModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODULEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						moduleResultModelImpl.getOriginalModuleId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_MODULEID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODULEID,
					args);

				args = new Object[] { moduleResultModelImpl.getModuleId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_MODULEID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODULEID,
					args);
			}
		}

		entityCache.putResult(ModuleResultModelImpl.ENTITY_CACHE_ENABLED,
			ModuleResultImpl.class, moduleResult.getPrimaryKey(), moduleResult,
			false);

		clearUniqueFindersCache(moduleResultModelImpl, false);
		cacheUniqueFindersCache(moduleResultModelImpl);

		moduleResult.resetOriginalValues();

		return moduleResult;
	}

	/**
	 * Returns the module result with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the module result
	 * @return the module result
	 * @throws NoSuchModuleResultException if a module result with the primary key could not be found
	 */
	@Override
	public ModuleResult findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModuleResultException {
		ModuleResult moduleResult = fetchByPrimaryKey(primaryKey);

		if (moduleResult == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchModuleResultException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return moduleResult;
	}

	/**
	 * Returns the module result with the primary key or throws a {@link NoSuchModuleResultException} if it could not be found.
	 *
	 * @param mrId the primary key of the module result
	 * @return the module result
	 * @throws NoSuchModuleResultException if a module result with the primary key could not be found
	 */
	@Override
	public ModuleResult findByPrimaryKey(long mrId)
		throws NoSuchModuleResultException {
		return findByPrimaryKey((Serializable)mrId);
	}

	/**
	 * Returns the module result with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the module result
	 * @return the module result, or <code>null</code> if a module result with the primary key could not be found
	 */
	@Override
	public ModuleResult fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ModuleResultModelImpl.ENTITY_CACHE_ENABLED,
				ModuleResultImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ModuleResult moduleResult = (ModuleResult)serializable;

		if (moduleResult == null) {
			Session session = null;

			try {
				session = openSession();

				moduleResult = (ModuleResult)session.get(ModuleResultImpl.class,
						primaryKey);

				if (moduleResult != null) {
					cacheResult(moduleResult);
				}
				else {
					entityCache.putResult(ModuleResultModelImpl.ENTITY_CACHE_ENABLED,
						ModuleResultImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ModuleResultModelImpl.ENTITY_CACHE_ENABLED,
					ModuleResultImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return moduleResult;
	}

	/**
	 * Returns the module result with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param mrId the primary key of the module result
	 * @return the module result, or <code>null</code> if a module result with the primary key could not be found
	 */
	@Override
	public ModuleResult fetchByPrimaryKey(long mrId) {
		return fetchByPrimaryKey((Serializable)mrId);
	}

	@Override
	public Map<Serializable, ModuleResult> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ModuleResult> map = new HashMap<Serializable, ModuleResult>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ModuleResult moduleResult = fetchByPrimaryKey(primaryKey);

			if (moduleResult != null) {
				map.put(primaryKey, moduleResult);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ModuleResultModelImpl.ENTITY_CACHE_ENABLED,
					ModuleResultImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ModuleResult)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_MODULERESULT_WHERE_PKS_IN);

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

			for (ModuleResult moduleResult : (List<ModuleResult>)q.list()) {
				map.put(moduleResult.getPrimaryKeyObj(), moduleResult);

				cacheResult(moduleResult);

				uncachedPrimaryKeys.remove(moduleResult.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ModuleResultModelImpl.ENTITY_CACHE_ENABLED,
					ModuleResultImpl.class, primaryKey, nullModel);
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
	 * Returns all the module results.
	 *
	 * @return the module results
	 */
	@Override
	public List<ModuleResult> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the module results.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of module results
	 * @param end the upper bound of the range of module results (not inclusive)
	 * @return the range of module results
	 */
	@Override
	public List<ModuleResult> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the module results.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of module results
	 * @param end the upper bound of the range of module results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of module results
	 */
	@Override
	public List<ModuleResult> findAll(int start, int end,
		OrderByComparator<ModuleResult> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the module results.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of module results
	 * @param end the upper bound of the range of module results (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of module results
	 */
	@Override
	public List<ModuleResult> findAll(int start, int end,
		OrderByComparator<ModuleResult> orderByComparator,
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

		List<ModuleResult> list = null;

		if (retrieveFromCache) {
			list = (List<ModuleResult>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_MODULERESULT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_MODULERESULT;

				if (pagination) {
					sql = sql.concat(ModuleResultModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ModuleResult>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ModuleResult>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the module results from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ModuleResult moduleResult : findAll()) {
			remove(moduleResult);
		}
	}

	/**
	 * Returns the number of module results.
	 *
	 * @return the number of module results
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_MODULERESULT);

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
	protected Map<String, Integer> getTableColumnsMap() {
		return ModuleResultModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the module result persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ModuleResultImpl.class.getName());
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
	private static final String _SQL_SELECT_MODULERESULT = "SELECT moduleResult FROM ModuleResult moduleResult";
	private static final String _SQL_SELECT_MODULERESULT_WHERE_PKS_IN = "SELECT moduleResult FROM ModuleResult moduleResult WHERE mrId IN (";
	private static final String _SQL_SELECT_MODULERESULT_WHERE = "SELECT moduleResult FROM ModuleResult moduleResult WHERE ";
	private static final String _SQL_COUNT_MODULERESULT = "SELECT COUNT(moduleResult) FROM ModuleResult moduleResult";
	private static final String _SQL_COUNT_MODULERESULT_WHERE = "SELECT COUNT(moduleResult) FROM ModuleResult moduleResult WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "moduleResult.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ModuleResult exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ModuleResult exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ModuleResultPersistenceImpl.class);
}