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

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.permission.InlineSQLHelperUtil;
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

import com.ted.lms.exception.NoSuchModuleException;
import com.ted.lms.model.Module;
import com.ted.lms.model.impl.ModuleImpl;
import com.ted.lms.model.impl.ModuleModelImpl;
import com.ted.lms.service.persistence.ModulePersistence;

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
 * The persistence implementation for the module service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ModulePersistence
 * @see com.ted.lms.service.persistence.ModuleUtil
 * @generated
 */
@ProviderType
public class ModulePersistenceImpl extends BasePersistenceImpl<Module>
	implements ModulePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ModuleUtil} to access the module persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ModuleImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ModuleModelImpl.ENTITY_CACHE_ENABLED,
			ModuleModelImpl.FINDER_CACHE_ENABLED, ModuleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ModuleModelImpl.ENTITY_CACHE_ENABLED,
			ModuleModelImpl.FINDER_CACHE_ENABLED, ModuleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ModuleModelImpl.ENTITY_CACHE_ENABLED,
			ModuleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(ModuleModelImpl.ENTITY_CACHE_ENABLED,
			ModuleModelImpl.FINDER_CACHE_ENABLED, ModuleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(ModuleModelImpl.ENTITY_CACHE_ENABLED,
			ModuleModelImpl.FINDER_CACHE_ENABLED, ModuleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			ModuleModelImpl.UUID_COLUMN_BITMASK |
			ModuleModelImpl.ORDER_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(ModuleModelImpl.ENTITY_CACHE_ENABLED,
			ModuleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the modules where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching modules
	 */
	@Override
	public List<Module> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the modules where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @return the range of matching modules
	 */
	@Override
	public List<Module> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the modules where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching modules
	 */
	@Override
	public List<Module> findByUuid(String uuid, int start, int end,
		OrderByComparator<Module> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the modules where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching modules
	 */
	@Override
	public List<Module> findByUuid(String uuid, int start, int end,
		OrderByComparator<Module> orderByComparator, boolean retrieveFromCache) {
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

		List<Module> list = null;

		if (retrieveFromCache) {
			list = (List<Module>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Module module : list) {
					if (!Objects.equals(uuid, module.getUuid())) {
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

			query.append(_SQL_SELECT_MODULE_WHERE);

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
				query.append(ModuleModelImpl.ORDER_BY_JPQL);
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
					list = (List<Module>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Module>)QueryUtil.list(q, getDialect(), start,
							end);
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
	 * Returns the first module in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching module
	 * @throws NoSuchModuleException if a matching module could not be found
	 */
	@Override
	public Module findByUuid_First(String uuid,
		OrderByComparator<Module> orderByComparator)
		throws NoSuchModuleException {
		Module module = fetchByUuid_First(uuid, orderByComparator);

		if (module != null) {
			return module;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchModuleException(msg.toString());
	}

	/**
	 * Returns the first module in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching module, or <code>null</code> if a matching module could not be found
	 */
	@Override
	public Module fetchByUuid_First(String uuid,
		OrderByComparator<Module> orderByComparator) {
		List<Module> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last module in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching module
	 * @throws NoSuchModuleException if a matching module could not be found
	 */
	@Override
	public Module findByUuid_Last(String uuid,
		OrderByComparator<Module> orderByComparator)
		throws NoSuchModuleException {
		Module module = fetchByUuid_Last(uuid, orderByComparator);

		if (module != null) {
			return module;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchModuleException(msg.toString());
	}

	/**
	 * Returns the last module in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching module, or <code>null</code> if a matching module could not be found
	 */
	@Override
	public Module fetchByUuid_Last(String uuid,
		OrderByComparator<Module> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<Module> list = findByUuid(uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the modules before and after the current module in the ordered set where uuid = &#63;.
	 *
	 * @param moduleId the primary key of the current module
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next module
	 * @throws NoSuchModuleException if a module with the primary key could not be found
	 */
	@Override
	public Module[] findByUuid_PrevAndNext(long moduleId, String uuid,
		OrderByComparator<Module> orderByComparator)
		throws NoSuchModuleException {
		Module module = findByPrimaryKey(moduleId);

		Session session = null;

		try {
			session = openSession();

			Module[] array = new ModuleImpl[3];

			array[0] = getByUuid_PrevAndNext(session, module, uuid,
					orderByComparator, true);

			array[1] = module;

			array[2] = getByUuid_PrevAndNext(session, module, uuid,
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

	protected Module getByUuid_PrevAndNext(Session session, Module module,
		String uuid, OrderByComparator<Module> orderByComparator,
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

		query.append(_SQL_SELECT_MODULE_WHERE);

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
			query.append(ModuleModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(module);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Module> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the modules where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (Module module : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(module);
		}
	}

	/**
	 * Returns the number of modules where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching modules
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MODULE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "module.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "module.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(module.uuid IS NULL OR module.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(ModuleModelImpl.ENTITY_CACHE_ENABLED,
			ModuleModelImpl.FINDER_CACHE_ENABLED, ModuleImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			ModuleModelImpl.UUID_COLUMN_BITMASK |
			ModuleModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(ModuleModelImpl.ENTITY_CACHE_ENABLED,
			ModuleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the module where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchModuleException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching module
	 * @throws NoSuchModuleException if a matching module could not be found
	 */
	@Override
	public Module findByUUID_G(String uuid, long groupId)
		throws NoSuchModuleException {
		Module module = fetchByUUID_G(uuid, groupId);

		if (module == null) {
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

			throw new NoSuchModuleException(msg.toString());
		}

		return module;
	}

	/**
	 * Returns the module where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching module, or <code>null</code> if a matching module could not be found
	 */
	@Override
	public Module fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the module where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching module, or <code>null</code> if a matching module could not be found
	 */
	@Override
	public Module fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof Module) {
			Module module = (Module)result;

			if (!Objects.equals(uuid, module.getUuid()) ||
					(groupId != module.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_MODULE_WHERE);

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

				List<Module> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					Module module = list.get(0);

					result = module;

					cacheResult(module);
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
			return (Module)result;
		}
	}

	/**
	 * Removes the module where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the module that was removed
	 */
	@Override
	public Module removeByUUID_G(String uuid, long groupId)
		throws NoSuchModuleException {
		Module module = findByUUID_G(uuid, groupId);

		return remove(module);
	}

	/**
	 * Returns the number of modules where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching modules
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_MODULE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "module.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "module.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(module.uuid IS NULL OR module.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "module.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(ModuleModelImpl.ENTITY_CACHE_ENABLED,
			ModuleModelImpl.FINDER_CACHE_ENABLED, ModuleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(ModuleModelImpl.ENTITY_CACHE_ENABLED,
			ModuleModelImpl.FINDER_CACHE_ENABLED, ModuleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			ModuleModelImpl.UUID_COLUMN_BITMASK |
			ModuleModelImpl.COMPANYID_COLUMN_BITMASK |
			ModuleModelImpl.ORDER_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(ModuleModelImpl.ENTITY_CACHE_ENABLED,
			ModuleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the modules where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching modules
	 */
	@Override
	public List<Module> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the modules where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @return the range of matching modules
	 */
	@Override
	public List<Module> findByUuid_C(String uuid, long companyId, int start,
		int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the modules where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching modules
	 */
	@Override
	public List<Module> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<Module> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the modules where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching modules
	 */
	@Override
	public List<Module> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<Module> orderByComparator,
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

		List<Module> list = null;

		if (retrieveFromCache) {
			list = (List<Module>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Module module : list) {
					if (!Objects.equals(uuid, module.getUuid()) ||
							(companyId != module.getCompanyId())) {
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

			query.append(_SQL_SELECT_MODULE_WHERE);

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
				query.append(ModuleModelImpl.ORDER_BY_JPQL);
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
					list = (List<Module>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Module>)QueryUtil.list(q, getDialect(), start,
							end);
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
	 * Returns the first module in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching module
	 * @throws NoSuchModuleException if a matching module could not be found
	 */
	@Override
	public Module findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Module> orderByComparator)
		throws NoSuchModuleException {
		Module module = fetchByUuid_C_First(uuid, companyId, orderByComparator);

		if (module != null) {
			return module;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchModuleException(msg.toString());
	}

	/**
	 * Returns the first module in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching module, or <code>null</code> if a matching module could not be found
	 */
	@Override
	public Module fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Module> orderByComparator) {
		List<Module> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last module in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching module
	 * @throws NoSuchModuleException if a matching module could not be found
	 */
	@Override
	public Module findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Module> orderByComparator)
		throws NoSuchModuleException {
		Module module = fetchByUuid_C_Last(uuid, companyId, orderByComparator);

		if (module != null) {
			return module;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchModuleException(msg.toString());
	}

	/**
	 * Returns the last module in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching module, or <code>null</code> if a matching module could not be found
	 */
	@Override
	public Module fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Module> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<Module> list = findByUuid_C(uuid, companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the modules before and after the current module in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param moduleId the primary key of the current module
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next module
	 * @throws NoSuchModuleException if a module with the primary key could not be found
	 */
	@Override
	public Module[] findByUuid_C_PrevAndNext(long moduleId, String uuid,
		long companyId, OrderByComparator<Module> orderByComparator)
		throws NoSuchModuleException {
		Module module = findByPrimaryKey(moduleId);

		Session session = null;

		try {
			session = openSession();

			Module[] array = new ModuleImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, module, uuid,
					companyId, orderByComparator, true);

			array[1] = module;

			array[2] = getByUuid_C_PrevAndNext(session, module, uuid,
					companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Module getByUuid_C_PrevAndNext(Session session, Module module,
		String uuid, long companyId,
		OrderByComparator<Module> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_MODULE_WHERE);

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
			query.append(ModuleModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(module);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Module> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the modules where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (Module module : findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(module);
		}
	}

	/**
	 * Returns the number of modules where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching modules
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_MODULE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "module.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "module.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(module.uuid IS NULL OR module.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "module.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(ModuleModelImpl.ENTITY_CACHE_ENABLED,
			ModuleModelImpl.FINDER_CACHE_ENABLED, ModuleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(ModuleModelImpl.ENTITY_CACHE_ENABLED,
			ModuleModelImpl.FINDER_CACHE_ENABLED, ModuleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] { Long.class.getName() },
			ModuleModelImpl.COMPANYID_COLUMN_BITMASK |
			ModuleModelImpl.ORDER_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(ModuleModelImpl.ENTITY_CACHE_ENABLED,
			ModuleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the modules where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching modules
	 */
	@Override
	public List<Module> findByCompanyId(long companyId) {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the modules where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @return the range of matching modules
	 */
	@Override
	public List<Module> findByCompanyId(long companyId, int start, int end) {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the modules where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching modules
	 */
	@Override
	public List<Module> findByCompanyId(long companyId, int start, int end,
		OrderByComparator<Module> orderByComparator) {
		return findByCompanyId(companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the modules where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching modules
	 */
	@Override
	public List<Module> findByCompanyId(long companyId, int start, int end,
		OrderByComparator<Module> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID;
			finderArgs = new Object[] { companyId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID;
			finderArgs = new Object[] { companyId, start, end, orderByComparator };
		}

		List<Module> list = null;

		if (retrieveFromCache) {
			list = (List<Module>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Module module : list) {
					if ((companyId != module.getCompanyId())) {
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

			query.append(_SQL_SELECT_MODULE_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ModuleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (!pagination) {
					list = (List<Module>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Module>)QueryUtil.list(q, getDialect(), start,
							end);
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
	 * Returns the first module in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching module
	 * @throws NoSuchModuleException if a matching module could not be found
	 */
	@Override
	public Module findByCompanyId_First(long companyId,
		OrderByComparator<Module> orderByComparator)
		throws NoSuchModuleException {
		Module module = fetchByCompanyId_First(companyId, orderByComparator);

		if (module != null) {
			return module;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchModuleException(msg.toString());
	}

	/**
	 * Returns the first module in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching module, or <code>null</code> if a matching module could not be found
	 */
	@Override
	public Module fetchByCompanyId_First(long companyId,
		OrderByComparator<Module> orderByComparator) {
		List<Module> list = findByCompanyId(companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last module in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching module
	 * @throws NoSuchModuleException if a matching module could not be found
	 */
	@Override
	public Module findByCompanyId_Last(long companyId,
		OrderByComparator<Module> orderByComparator)
		throws NoSuchModuleException {
		Module module = fetchByCompanyId_Last(companyId, orderByComparator);

		if (module != null) {
			return module;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchModuleException(msg.toString());
	}

	/**
	 * Returns the last module in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching module, or <code>null</code> if a matching module could not be found
	 */
	@Override
	public Module fetchByCompanyId_Last(long companyId,
		OrderByComparator<Module> orderByComparator) {
		int count = countByCompanyId(companyId);

		if (count == 0) {
			return null;
		}

		List<Module> list = findByCompanyId(companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the modules before and after the current module in the ordered set where companyId = &#63;.
	 *
	 * @param moduleId the primary key of the current module
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next module
	 * @throws NoSuchModuleException if a module with the primary key could not be found
	 */
	@Override
	public Module[] findByCompanyId_PrevAndNext(long moduleId, long companyId,
		OrderByComparator<Module> orderByComparator)
		throws NoSuchModuleException {
		Module module = findByPrimaryKey(moduleId);

		Session session = null;

		try {
			session = openSession();

			Module[] array = new ModuleImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session, module, companyId,
					orderByComparator, true);

			array[1] = module;

			array[2] = getByCompanyId_PrevAndNext(session, module, companyId,
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

	protected Module getByCompanyId_PrevAndNext(Session session, Module module,
		long companyId, OrderByComparator<Module> orderByComparator,
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

		query.append(_SQL_SELECT_MODULE_WHERE);

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

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
			query.append(ModuleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(module);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Module> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the modules where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	@Override
	public void removeByCompanyId(long companyId) {
		for (Module module : findByCompanyId(companyId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(module);
		}
	}

	/**
	 * Returns the number of modules where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching modules
	 */
	@Override
	public int countByCompanyId(long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYID;

		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MODULE_WHERE);

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

	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "module.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(ModuleModelImpl.ENTITY_CACHE_ENABLED,
			ModuleModelImpl.FINDER_CACHE_ENABLED, ModuleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(ModuleModelImpl.ENTITY_CACHE_ENABLED,
			ModuleModelImpl.FINDER_CACHE_ENABLED, ModuleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			ModuleModelImpl.GROUPID_COLUMN_BITMASK |
			ModuleModelImpl.ORDER_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(ModuleModelImpl.ENTITY_CACHE_ENABLED,
			ModuleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the modules where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching modules
	 */
	@Override
	public List<Module> findByGroupId(long groupId) {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the modules where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @return the range of matching modules
	 */
	@Override
	public List<Module> findByGroupId(long groupId, int start, int end) {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the modules where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching modules
	 */
	@Override
	public List<Module> findByGroupId(long groupId, int start, int end,
		OrderByComparator<Module> orderByComparator) {
		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the modules where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching modules
	 */
	@Override
	public List<Module> findByGroupId(long groupId, int start, int end,
		OrderByComparator<Module> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId, start, end, orderByComparator };
		}

		List<Module> list = null;

		if (retrieveFromCache) {
			list = (List<Module>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Module module : list) {
					if ((groupId != module.getGroupId())) {
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

			query.append(_SQL_SELECT_MODULE_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ModuleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<Module>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Module>)QueryUtil.list(q, getDialect(), start,
							end);
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
	 * Returns the first module in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching module
	 * @throws NoSuchModuleException if a matching module could not be found
	 */
	@Override
	public Module findByGroupId_First(long groupId,
		OrderByComparator<Module> orderByComparator)
		throws NoSuchModuleException {
		Module module = fetchByGroupId_First(groupId, orderByComparator);

		if (module != null) {
			return module;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchModuleException(msg.toString());
	}

	/**
	 * Returns the first module in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching module, or <code>null</code> if a matching module could not be found
	 */
	@Override
	public Module fetchByGroupId_First(long groupId,
		OrderByComparator<Module> orderByComparator) {
		List<Module> list = findByGroupId(groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last module in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching module
	 * @throws NoSuchModuleException if a matching module could not be found
	 */
	@Override
	public Module findByGroupId_Last(long groupId,
		OrderByComparator<Module> orderByComparator)
		throws NoSuchModuleException {
		Module module = fetchByGroupId_Last(groupId, orderByComparator);

		if (module != null) {
			return module;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append("}");

		throw new NoSuchModuleException(msg.toString());
	}

	/**
	 * Returns the last module in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching module, or <code>null</code> if a matching module could not be found
	 */
	@Override
	public Module fetchByGroupId_Last(long groupId,
		OrderByComparator<Module> orderByComparator) {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<Module> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the modules before and after the current module in the ordered set where groupId = &#63;.
	 *
	 * @param moduleId the primary key of the current module
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next module
	 * @throws NoSuchModuleException if a module with the primary key could not be found
	 */
	@Override
	public Module[] findByGroupId_PrevAndNext(long moduleId, long groupId,
		OrderByComparator<Module> orderByComparator)
		throws NoSuchModuleException {
		Module module = findByPrimaryKey(moduleId);

		Session session = null;

		try {
			session = openSession();

			Module[] array = new ModuleImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, module, groupId,
					orderByComparator, true);

			array[1] = module;

			array[2] = getByGroupId_PrevAndNext(session, module, groupId,
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

	protected Module getByGroupId_PrevAndNext(Session session, Module module,
		long groupId, OrderByComparator<Module> orderByComparator,
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

		query.append(_SQL_SELECT_MODULE_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

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
			query.append(ModuleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(module);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Module> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the modules that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching modules that the user has permission to view
	 */
	@Override
	public List<Module> filterFindByGroupId(long groupId) {
		return filterFindByGroupId(groupId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the modules that the user has permission to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @return the range of matching modules that the user has permission to view
	 */
	@Override
	public List<Module> filterFindByGroupId(long groupId, int start, int end) {
		return filterFindByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the modules that the user has permissions to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching modules that the user has permission to view
	 */
	@Override
	public List<Module> filterFindByGroupId(long groupId, int start, int end,
		OrderByComparator<Module> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupId(groupId, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(3 +
					(orderByComparator.getOrderByFields().length * 2));
		}
		else {
			query = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MODULE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_MODULE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MODULE_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator, true);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(ModuleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ModuleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Module.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN,
				groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, ModuleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, ModuleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			return (List<Module>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the modules before and after the current module in the ordered set of modules that the user has permission to view where groupId = &#63;.
	 *
	 * @param moduleId the primary key of the current module
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next module
	 * @throws NoSuchModuleException if a module with the primary key could not be found
	 */
	@Override
	public Module[] filterFindByGroupId_PrevAndNext(long moduleId,
		long groupId, OrderByComparator<Module> orderByComparator)
		throws NoSuchModuleException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupId_PrevAndNext(moduleId, groupId,
				orderByComparator);
		}

		Module module = findByPrimaryKey(moduleId);

		Session session = null;

		try {
			session = openSession();

			Module[] array = new ModuleImpl[3];

			array[0] = filterGetByGroupId_PrevAndNext(session, module, groupId,
					orderByComparator, true);

			array[1] = module;

			array[2] = filterGetByGroupId_PrevAndNext(session, module, groupId,
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

	protected Module filterGetByGroupId_PrevAndNext(Session session,
		Module module, long groupId,
		OrderByComparator<Module> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MODULE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_MODULE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MODULE_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(ModuleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ModuleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Module.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN,
				groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, ModuleImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, ModuleImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(module);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Module> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the modules where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (Module module : findByGroupId(groupId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(module);
		}
	}

	/**
	 * Returns the number of modules where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching modules
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MODULE_WHERE);

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
	 * Returns the number of modules that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching modules that the user has permission to view
	 */
	@Override
	public int filterCountByGroupId(long groupId) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByGroupId(groupId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_MODULE_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Module.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN,
				groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "module.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPIDSTATUS =
		new FinderPath(ModuleModelImpl.ENTITY_CACHE_ENABLED,
			ModuleModelImpl.FINDER_CACHE_ENABLED, ModuleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupIdStatus",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDSTATUS =
		new FinderPath(ModuleModelImpl.ENTITY_CACHE_ENABLED,
			ModuleModelImpl.FINDER_CACHE_ENABLED, ModuleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupIdStatus",
			new String[] { Long.class.getName(), Integer.class.getName() },
			ModuleModelImpl.GROUPID_COLUMN_BITMASK |
			ModuleModelImpl.STATUS_COLUMN_BITMASK |
			ModuleModelImpl.ORDER_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPIDSTATUS = new FinderPath(ModuleModelImpl.ENTITY_CACHE_ENABLED,
			ModuleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupIdStatus",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the modules where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @return the matching modules
	 */
	@Override
	public List<Module> findByGroupIdStatus(long groupId, int status) {
		return findByGroupIdStatus(groupId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the modules where groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @return the range of matching modules
	 */
	@Override
	public List<Module> findByGroupIdStatus(long groupId, int status,
		int start, int end) {
		return findByGroupIdStatus(groupId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the modules where groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching modules
	 */
	@Override
	public List<Module> findByGroupIdStatus(long groupId, int status,
		int start, int end, OrderByComparator<Module> orderByComparator) {
		return findByGroupIdStatus(groupId, status, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the modules where groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching modules
	 */
	@Override
	public List<Module> findByGroupIdStatus(long groupId, int status,
		int start, int end, OrderByComparator<Module> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDSTATUS;
			finderArgs = new Object[] { groupId, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPIDSTATUS;
			finderArgs = new Object[] {
					groupId, status,
					
					start, end, orderByComparator
				};
		}

		List<Module> list = null;

		if (retrieveFromCache) {
			list = (List<Module>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Module module : list) {
					if ((groupId != module.getGroupId()) ||
							(status != module.getStatus())) {
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

			query.append(_SQL_SELECT_MODULE_WHERE);

			query.append(_FINDER_COLUMN_GROUPIDSTATUS_GROUPID_2);

			query.append(_FINDER_COLUMN_GROUPIDSTATUS_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ModuleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(status);

				if (!pagination) {
					list = (List<Module>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Module>)QueryUtil.list(q, getDialect(), start,
							end);
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
	 * Returns the first module in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching module
	 * @throws NoSuchModuleException if a matching module could not be found
	 */
	@Override
	public Module findByGroupIdStatus_First(long groupId, int status,
		OrderByComparator<Module> orderByComparator)
		throws NoSuchModuleException {
		Module module = fetchByGroupIdStatus_First(groupId, status,
				orderByComparator);

		if (module != null) {
			return module;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchModuleException(msg.toString());
	}

	/**
	 * Returns the first module in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching module, or <code>null</code> if a matching module could not be found
	 */
	@Override
	public Module fetchByGroupIdStatus_First(long groupId, int status,
		OrderByComparator<Module> orderByComparator) {
		List<Module> list = findByGroupIdStatus(groupId, status, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last module in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching module
	 * @throws NoSuchModuleException if a matching module could not be found
	 */
	@Override
	public Module findByGroupIdStatus_Last(long groupId, int status,
		OrderByComparator<Module> orderByComparator)
		throws NoSuchModuleException {
		Module module = fetchByGroupIdStatus_Last(groupId, status,
				orderByComparator);

		if (module != null) {
			return module;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchModuleException(msg.toString());
	}

	/**
	 * Returns the last module in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching module, or <code>null</code> if a matching module could not be found
	 */
	@Override
	public Module fetchByGroupIdStatus_Last(long groupId, int status,
		OrderByComparator<Module> orderByComparator) {
		int count = countByGroupIdStatus(groupId, status);

		if (count == 0) {
			return null;
		}

		List<Module> list = findByGroupIdStatus(groupId, status, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the modules before and after the current module in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param moduleId the primary key of the current module
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next module
	 * @throws NoSuchModuleException if a module with the primary key could not be found
	 */
	@Override
	public Module[] findByGroupIdStatus_PrevAndNext(long moduleId,
		long groupId, int status, OrderByComparator<Module> orderByComparator)
		throws NoSuchModuleException {
		Module module = findByPrimaryKey(moduleId);

		Session session = null;

		try {
			session = openSession();

			Module[] array = new ModuleImpl[3];

			array[0] = getByGroupIdStatus_PrevAndNext(session, module, groupId,
					status, orderByComparator, true);

			array[1] = module;

			array[2] = getByGroupIdStatus_PrevAndNext(session, module, groupId,
					status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Module getByGroupIdStatus_PrevAndNext(Session session,
		Module module, long groupId, int status,
		OrderByComparator<Module> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_MODULE_WHERE);

		query.append(_FINDER_COLUMN_GROUPIDSTATUS_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDSTATUS_STATUS_2);

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
			query.append(ModuleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(module);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Module> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the modules that the user has permission to view where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @return the matching modules that the user has permission to view
	 */
	@Override
	public List<Module> filterFindByGroupIdStatus(long groupId, int status) {
		return filterFindByGroupIdStatus(groupId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the modules that the user has permission to view where groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @return the range of matching modules that the user has permission to view
	 */
	@Override
	public List<Module> filterFindByGroupIdStatus(long groupId, int status,
		int start, int end) {
		return filterFindByGroupIdStatus(groupId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the modules that the user has permissions to view where groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching modules that the user has permission to view
	 */
	@Override
	public List<Module> filterFindByGroupIdStatus(long groupId, int status,
		int start, int end, OrderByComparator<Module> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupIdStatus(groupId, status, start, end,
				orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByFields().length * 2));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MODULE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_MODULE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPIDSTATUS_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDSTATUS_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MODULE_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator, true);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(ModuleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ModuleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Module.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN,
				groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, ModuleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, ModuleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(status);

			return (List<Module>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the modules before and after the current module in the ordered set of modules that the user has permission to view where groupId = &#63; and status = &#63;.
	 *
	 * @param moduleId the primary key of the current module
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next module
	 * @throws NoSuchModuleException if a module with the primary key could not be found
	 */
	@Override
	public Module[] filterFindByGroupIdStatus_PrevAndNext(long moduleId,
		long groupId, int status, OrderByComparator<Module> orderByComparator)
		throws NoSuchModuleException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupIdStatus_PrevAndNext(moduleId, groupId, status,
				orderByComparator);
		}

		Module module = findByPrimaryKey(moduleId);

		Session session = null;

		try {
			session = openSession();

			Module[] array = new ModuleImpl[3];

			array[0] = filterGetByGroupIdStatus_PrevAndNext(session, module,
					groupId, status, orderByComparator, true);

			array[1] = module;

			array[2] = filterGetByGroupIdStatus_PrevAndNext(session, module,
					groupId, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Module filterGetByGroupIdStatus_PrevAndNext(Session session,
		Module module, long groupId, int status,
		OrderByComparator<Module> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MODULE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_MODULE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPIDSTATUS_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDSTATUS_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MODULE_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(ModuleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ModuleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Module.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN,
				groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, ModuleImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, ModuleImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(module);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Module> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the modules where groupId = &#63; and status = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 */
	@Override
	public void removeByGroupIdStatus(long groupId, int status) {
		for (Module module : findByGroupIdStatus(groupId, status,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(module);
		}
	}

	/**
	 * Returns the number of modules where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @return the number of matching modules
	 */
	@Override
	public int countByGroupIdStatus(long groupId, int status) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPIDSTATUS;

		Object[] finderArgs = new Object[] { groupId, status };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_MODULE_WHERE);

			query.append(_FINDER_COLUMN_GROUPIDSTATUS_GROUPID_2);

			query.append(_FINDER_COLUMN_GROUPIDSTATUS_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(status);

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
	 * Returns the number of modules that the user has permission to view where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @return the number of matching modules that the user has permission to view
	 */
	@Override
	public int filterCountByGroupIdStatus(long groupId, int status) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByGroupIdStatus(groupId, status);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_MODULE_WHERE);

		query.append(_FINDER_COLUMN_GROUPIDSTATUS_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDSTATUS_STATUS_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Module.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN,
				groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(status);

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

	private static final String _FINDER_COLUMN_GROUPIDSTATUS_GROUPID_2 = "module.groupId = ? AND ";
	private static final String _FINDER_COLUMN_GROUPIDSTATUS_STATUS_2 = "module.status = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPIDNEXTMODULES =
		new FinderPath(ModuleModelImpl.ENTITY_CACHE_ENABLED,
			ModuleModelImpl.FINDER_CACHE_ENABLED, ModuleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupIdNextModules",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_GROUPIDNEXTMODULES =
		new FinderPath(ModuleModelImpl.ENTITY_CACHE_ENABLED,
			ModuleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"countByGroupIdNextModules",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the modules where groupId = &#63; and order &gt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param order the order
	 * @return the matching modules
	 */
	@Override
	public List<Module> findByGroupIdNextModules(long groupId, long order) {
		return findByGroupIdNextModules(groupId, order, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the modules where groupId = &#63; and order &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param order the order
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @return the range of matching modules
	 */
	@Override
	public List<Module> findByGroupIdNextModules(long groupId, long order,
		int start, int end) {
		return findByGroupIdNextModules(groupId, order, start, end, null);
	}

	/**
	 * Returns an ordered range of all the modules where groupId = &#63; and order &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param order the order
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching modules
	 */
	@Override
	public List<Module> findByGroupIdNextModules(long groupId, long order,
		int start, int end, OrderByComparator<Module> orderByComparator) {
		return findByGroupIdNextModules(groupId, order, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the modules where groupId = &#63; and order &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param order the order
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching modules
	 */
	@Override
	public List<Module> findByGroupIdNextModules(long groupId, long order,
		int start, int end, OrderByComparator<Module> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPIDNEXTMODULES;
		finderArgs = new Object[] { groupId, order, start, end, orderByComparator };

		List<Module> list = null;

		if (retrieveFromCache) {
			list = (List<Module>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Module module : list) {
					if ((groupId != module.getGroupId()) ||
							(order >= module.getOrder())) {
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

			query.append(_SQL_SELECT_MODULE_WHERE);

			query.append(_FINDER_COLUMN_GROUPIDNEXTMODULES_GROUPID_2);

			query.append(_FINDER_COLUMN_GROUPIDNEXTMODULES_ORDER_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ModuleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(order);

				if (!pagination) {
					list = (List<Module>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Module>)QueryUtil.list(q, getDialect(), start,
							end);
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
	 * Returns the first module in the ordered set where groupId = &#63; and order &gt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param order the order
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching module
	 * @throws NoSuchModuleException if a matching module could not be found
	 */
	@Override
	public Module findByGroupIdNextModules_First(long groupId, long order,
		OrderByComparator<Module> orderByComparator)
		throws NoSuchModuleException {
		Module module = fetchByGroupIdNextModules_First(groupId, order,
				orderByComparator);

		if (module != null) {
			return module;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", order=");
		msg.append(order);

		msg.append("}");

		throw new NoSuchModuleException(msg.toString());
	}

	/**
	 * Returns the first module in the ordered set where groupId = &#63; and order &gt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param order the order
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching module, or <code>null</code> if a matching module could not be found
	 */
	@Override
	public Module fetchByGroupIdNextModules_First(long groupId, long order,
		OrderByComparator<Module> orderByComparator) {
		List<Module> list = findByGroupIdNextModules(groupId, order, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last module in the ordered set where groupId = &#63; and order &gt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param order the order
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching module
	 * @throws NoSuchModuleException if a matching module could not be found
	 */
	@Override
	public Module findByGroupIdNextModules_Last(long groupId, long order,
		OrderByComparator<Module> orderByComparator)
		throws NoSuchModuleException {
		Module module = fetchByGroupIdNextModules_Last(groupId, order,
				orderByComparator);

		if (module != null) {
			return module;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", order=");
		msg.append(order);

		msg.append("}");

		throw new NoSuchModuleException(msg.toString());
	}

	/**
	 * Returns the last module in the ordered set where groupId = &#63; and order &gt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param order the order
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching module, or <code>null</code> if a matching module could not be found
	 */
	@Override
	public Module fetchByGroupIdNextModules_Last(long groupId, long order,
		OrderByComparator<Module> orderByComparator) {
		int count = countByGroupIdNextModules(groupId, order);

		if (count == 0) {
			return null;
		}

		List<Module> list = findByGroupIdNextModules(groupId, order, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the modules before and after the current module in the ordered set where groupId = &#63; and order &gt; &#63;.
	 *
	 * @param moduleId the primary key of the current module
	 * @param groupId the group ID
	 * @param order the order
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next module
	 * @throws NoSuchModuleException if a module with the primary key could not be found
	 */
	@Override
	public Module[] findByGroupIdNextModules_PrevAndNext(long moduleId,
		long groupId, long order, OrderByComparator<Module> orderByComparator)
		throws NoSuchModuleException {
		Module module = findByPrimaryKey(moduleId);

		Session session = null;

		try {
			session = openSession();

			Module[] array = new ModuleImpl[3];

			array[0] = getByGroupIdNextModules_PrevAndNext(session, module,
					groupId, order, orderByComparator, true);

			array[1] = module;

			array[2] = getByGroupIdNextModules_PrevAndNext(session, module,
					groupId, order, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Module getByGroupIdNextModules_PrevAndNext(Session session,
		Module module, long groupId, long order,
		OrderByComparator<Module> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_MODULE_WHERE);

		query.append(_FINDER_COLUMN_GROUPIDNEXTMODULES_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDNEXTMODULES_ORDER_2);

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
			query.append(ModuleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(order);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(module);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Module> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the modules that the user has permission to view where groupId = &#63; and order &gt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param order the order
	 * @return the matching modules that the user has permission to view
	 */
	@Override
	public List<Module> filterFindByGroupIdNextModules(long groupId, long order) {
		return filterFindByGroupIdNextModules(groupId, order,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the modules that the user has permission to view where groupId = &#63; and order &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param order the order
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @return the range of matching modules that the user has permission to view
	 */
	@Override
	public List<Module> filterFindByGroupIdNextModules(long groupId,
		long order, int start, int end) {
		return filterFindByGroupIdNextModules(groupId, order, start, end, null);
	}

	/**
	 * Returns an ordered range of all the modules that the user has permissions to view where groupId = &#63; and order &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param order the order
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching modules that the user has permission to view
	 */
	@Override
	public List<Module> filterFindByGroupIdNextModules(long groupId,
		long order, int start, int end,
		OrderByComparator<Module> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupIdNextModules(groupId, order, start, end,
				orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByFields().length * 2));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MODULE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_MODULE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPIDNEXTMODULES_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDNEXTMODULES_ORDER_2_SQL);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MODULE_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator, true);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(ModuleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ModuleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Module.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN,
				groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, ModuleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, ModuleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(order);

			return (List<Module>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the modules before and after the current module in the ordered set of modules that the user has permission to view where groupId = &#63; and order &gt; &#63;.
	 *
	 * @param moduleId the primary key of the current module
	 * @param groupId the group ID
	 * @param order the order
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next module
	 * @throws NoSuchModuleException if a module with the primary key could not be found
	 */
	@Override
	public Module[] filterFindByGroupIdNextModules_PrevAndNext(long moduleId,
		long groupId, long order, OrderByComparator<Module> orderByComparator)
		throws NoSuchModuleException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupIdNextModules_PrevAndNext(moduleId, groupId,
				order, orderByComparator);
		}

		Module module = findByPrimaryKey(moduleId);

		Session session = null;

		try {
			session = openSession();

			Module[] array = new ModuleImpl[3];

			array[0] = filterGetByGroupIdNextModules_PrevAndNext(session,
					module, groupId, order, orderByComparator, true);

			array[1] = module;

			array[2] = filterGetByGroupIdNextModules_PrevAndNext(session,
					module, groupId, order, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Module filterGetByGroupIdNextModules_PrevAndNext(
		Session session, Module module, long groupId, long order,
		OrderByComparator<Module> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MODULE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_MODULE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPIDNEXTMODULES_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDNEXTMODULES_ORDER_2_SQL);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MODULE_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(ModuleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ModuleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Module.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN,
				groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, ModuleImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, ModuleImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(order);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(module);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Module> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the modules where groupId = &#63; and order &gt; &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param order the order
	 */
	@Override
	public void removeByGroupIdNextModules(long groupId, long order) {
		for (Module module : findByGroupIdNextModules(groupId, order,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(module);
		}
	}

	/**
	 * Returns the number of modules where groupId = &#63; and order &gt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param order the order
	 * @return the number of matching modules
	 */
	@Override
	public int countByGroupIdNextModules(long groupId, long order) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_GROUPIDNEXTMODULES;

		Object[] finderArgs = new Object[] { groupId, order };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_MODULE_WHERE);

			query.append(_FINDER_COLUMN_GROUPIDNEXTMODULES_GROUPID_2);

			query.append(_FINDER_COLUMN_GROUPIDNEXTMODULES_ORDER_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(order);

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
	 * Returns the number of modules that the user has permission to view where groupId = &#63; and order &gt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param order the order
	 * @return the number of matching modules that the user has permission to view
	 */
	@Override
	public int filterCountByGroupIdNextModules(long groupId, long order) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByGroupIdNextModules(groupId, order);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_MODULE_WHERE);

		query.append(_FINDER_COLUMN_GROUPIDNEXTMODULES_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDNEXTMODULES_ORDER_2_SQL);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Module.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN,
				groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(order);

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

	private static final String _FINDER_COLUMN_GROUPIDNEXTMODULES_GROUPID_2 = "module.groupId = ? AND ";
	private static final String _FINDER_COLUMN_GROUPIDNEXTMODULES_ORDER_2 = "module.order > ?";
	private static final String _FINDER_COLUMN_GROUPIDNEXTMODULES_ORDER_2_SQL = "module.order_ > ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPIDPREVIOUSMODULES =
		new FinderPath(ModuleModelImpl.ENTITY_CACHE_ENABLED,
			ModuleModelImpl.FINDER_CACHE_ENABLED, ModuleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByGroupIdPreviousModules",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_GROUPIDPREVIOUSMODULES =
		new FinderPath(ModuleModelImpl.ENTITY_CACHE_ENABLED,
			ModuleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"countByGroupIdPreviousModules",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the modules where groupId = &#63; and order &lt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param order the order
	 * @return the matching modules
	 */
	@Override
	public List<Module> findByGroupIdPreviousModules(long groupId, long order) {
		return findByGroupIdPreviousModules(groupId, order, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the modules where groupId = &#63; and order &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param order the order
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @return the range of matching modules
	 */
	@Override
	public List<Module> findByGroupIdPreviousModules(long groupId, long order,
		int start, int end) {
		return findByGroupIdPreviousModules(groupId, order, start, end, null);
	}

	/**
	 * Returns an ordered range of all the modules where groupId = &#63; and order &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param order the order
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching modules
	 */
	@Override
	public List<Module> findByGroupIdPreviousModules(long groupId, long order,
		int start, int end, OrderByComparator<Module> orderByComparator) {
		return findByGroupIdPreviousModules(groupId, order, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the modules where groupId = &#63; and order &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param order the order
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching modules
	 */
	@Override
	public List<Module> findByGroupIdPreviousModules(long groupId, long order,
		int start, int end, OrderByComparator<Module> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPIDPREVIOUSMODULES;
		finderArgs = new Object[] { groupId, order, start, end, orderByComparator };

		List<Module> list = null;

		if (retrieveFromCache) {
			list = (List<Module>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Module module : list) {
					if ((groupId != module.getGroupId()) ||
							(order <= module.getOrder())) {
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

			query.append(_SQL_SELECT_MODULE_WHERE);

			query.append(_FINDER_COLUMN_GROUPIDPREVIOUSMODULES_GROUPID_2);

			query.append(_FINDER_COLUMN_GROUPIDPREVIOUSMODULES_ORDER_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ModuleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(order);

				if (!pagination) {
					list = (List<Module>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Module>)QueryUtil.list(q, getDialect(), start,
							end);
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
	 * Returns the first module in the ordered set where groupId = &#63; and order &lt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param order the order
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching module
	 * @throws NoSuchModuleException if a matching module could not be found
	 */
	@Override
	public Module findByGroupIdPreviousModules_First(long groupId, long order,
		OrderByComparator<Module> orderByComparator)
		throws NoSuchModuleException {
		Module module = fetchByGroupIdPreviousModules_First(groupId, order,
				orderByComparator);

		if (module != null) {
			return module;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", order=");
		msg.append(order);

		msg.append("}");

		throw new NoSuchModuleException(msg.toString());
	}

	/**
	 * Returns the first module in the ordered set where groupId = &#63; and order &lt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param order the order
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching module, or <code>null</code> if a matching module could not be found
	 */
	@Override
	public Module fetchByGroupIdPreviousModules_First(long groupId, long order,
		OrderByComparator<Module> orderByComparator) {
		List<Module> list = findByGroupIdPreviousModules(groupId, order, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last module in the ordered set where groupId = &#63; and order &lt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param order the order
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching module
	 * @throws NoSuchModuleException if a matching module could not be found
	 */
	@Override
	public Module findByGroupIdPreviousModules_Last(long groupId, long order,
		OrderByComparator<Module> orderByComparator)
		throws NoSuchModuleException {
		Module module = fetchByGroupIdPreviousModules_Last(groupId, order,
				orderByComparator);

		if (module != null) {
			return module;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", order=");
		msg.append(order);

		msg.append("}");

		throw new NoSuchModuleException(msg.toString());
	}

	/**
	 * Returns the last module in the ordered set where groupId = &#63; and order &lt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param order the order
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching module, or <code>null</code> if a matching module could not be found
	 */
	@Override
	public Module fetchByGroupIdPreviousModules_Last(long groupId, long order,
		OrderByComparator<Module> orderByComparator) {
		int count = countByGroupIdPreviousModules(groupId, order);

		if (count == 0) {
			return null;
		}

		List<Module> list = findByGroupIdPreviousModules(groupId, order,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the modules before and after the current module in the ordered set where groupId = &#63; and order &lt; &#63;.
	 *
	 * @param moduleId the primary key of the current module
	 * @param groupId the group ID
	 * @param order the order
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next module
	 * @throws NoSuchModuleException if a module with the primary key could not be found
	 */
	@Override
	public Module[] findByGroupIdPreviousModules_PrevAndNext(long moduleId,
		long groupId, long order, OrderByComparator<Module> orderByComparator)
		throws NoSuchModuleException {
		Module module = findByPrimaryKey(moduleId);

		Session session = null;

		try {
			session = openSession();

			Module[] array = new ModuleImpl[3];

			array[0] = getByGroupIdPreviousModules_PrevAndNext(session, module,
					groupId, order, orderByComparator, true);

			array[1] = module;

			array[2] = getByGroupIdPreviousModules_PrevAndNext(session, module,
					groupId, order, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Module getByGroupIdPreviousModules_PrevAndNext(Session session,
		Module module, long groupId, long order,
		OrderByComparator<Module> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_MODULE_WHERE);

		query.append(_FINDER_COLUMN_GROUPIDPREVIOUSMODULES_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDPREVIOUSMODULES_ORDER_2);

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
			query.append(ModuleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(order);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(module);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Module> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the modules that the user has permission to view where groupId = &#63; and order &lt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param order the order
	 * @return the matching modules that the user has permission to view
	 */
	@Override
	public List<Module> filterFindByGroupIdPreviousModules(long groupId,
		long order) {
		return filterFindByGroupIdPreviousModules(groupId, order,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the modules that the user has permission to view where groupId = &#63; and order &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param order the order
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @return the range of matching modules that the user has permission to view
	 */
	@Override
	public List<Module> filterFindByGroupIdPreviousModules(long groupId,
		long order, int start, int end) {
		return filterFindByGroupIdPreviousModules(groupId, order, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the modules that the user has permissions to view where groupId = &#63; and order &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param order the order
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching modules that the user has permission to view
	 */
	@Override
	public List<Module> filterFindByGroupIdPreviousModules(long groupId,
		long order, int start, int end,
		OrderByComparator<Module> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupIdPreviousModules(groupId, order, start, end,
				orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByFields().length * 2));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MODULE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_MODULE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPIDPREVIOUSMODULES_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDPREVIOUSMODULES_ORDER_2_SQL);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MODULE_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator, true);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(ModuleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ModuleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Module.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN,
				groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, ModuleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, ModuleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(order);

			return (List<Module>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the modules before and after the current module in the ordered set of modules that the user has permission to view where groupId = &#63; and order &lt; &#63;.
	 *
	 * @param moduleId the primary key of the current module
	 * @param groupId the group ID
	 * @param order the order
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next module
	 * @throws NoSuchModuleException if a module with the primary key could not be found
	 */
	@Override
	public Module[] filterFindByGroupIdPreviousModules_PrevAndNext(
		long moduleId, long groupId, long order,
		OrderByComparator<Module> orderByComparator)
		throws NoSuchModuleException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupIdPreviousModules_PrevAndNext(moduleId, groupId,
				order, orderByComparator);
		}

		Module module = findByPrimaryKey(moduleId);

		Session session = null;

		try {
			session = openSession();

			Module[] array = new ModuleImpl[3];

			array[0] = filterGetByGroupIdPreviousModules_PrevAndNext(session,
					module, groupId, order, orderByComparator, true);

			array[1] = module;

			array[2] = filterGetByGroupIdPreviousModules_PrevAndNext(session,
					module, groupId, order, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Module filterGetByGroupIdPreviousModules_PrevAndNext(
		Session session, Module module, long groupId, long order,
		OrderByComparator<Module> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MODULE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_MODULE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPIDPREVIOUSMODULES_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDPREVIOUSMODULES_ORDER_2_SQL);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MODULE_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(ModuleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(ModuleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Module.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN,
				groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, ModuleImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, ModuleImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(order);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(module);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Module> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the modules where groupId = &#63; and order &lt; &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param order the order
	 */
	@Override
	public void removeByGroupIdPreviousModules(long groupId, long order) {
		for (Module module : findByGroupIdPreviousModules(groupId, order,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(module);
		}
	}

	/**
	 * Returns the number of modules where groupId = &#63; and order &lt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param order the order
	 * @return the number of matching modules
	 */
	@Override
	public int countByGroupIdPreviousModules(long groupId, long order) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_GROUPIDPREVIOUSMODULES;

		Object[] finderArgs = new Object[] { groupId, order };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_MODULE_WHERE);

			query.append(_FINDER_COLUMN_GROUPIDPREVIOUSMODULES_GROUPID_2);

			query.append(_FINDER_COLUMN_GROUPIDPREVIOUSMODULES_ORDER_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(order);

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
	 * Returns the number of modules that the user has permission to view where groupId = &#63; and order &lt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param order the order
	 * @return the number of matching modules that the user has permission to view
	 */
	@Override
	public int filterCountByGroupIdPreviousModules(long groupId, long order) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByGroupIdPreviousModules(groupId, order);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_MODULE_WHERE);

		query.append(_FINDER_COLUMN_GROUPIDPREVIOUSMODULES_GROUPID_2);

		query.append(_FINDER_COLUMN_GROUPIDPREVIOUSMODULES_ORDER_2_SQL);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Module.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN,
				groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(order);

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

	private static final String _FINDER_COLUMN_GROUPIDPREVIOUSMODULES_GROUPID_2 = "module.groupId = ? AND ";
	private static final String _FINDER_COLUMN_GROUPIDPREVIOUSMODULES_ORDER_2 = "module.order < ?";
	private static final String _FINDER_COLUMN_GROUPIDPREVIOUSMODULES_ORDER_2_SQL =
		"module.order_ < ?";

	public ModulePersistenceImpl() {
		setModelClass(Module.class);

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
					"_dbColumnNames");

			field.setAccessible(true);

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("uuid", "uuid_");
			dbColumnNames.put("order", "order_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the module in the entity cache if it is enabled.
	 *
	 * @param module the module
	 */
	@Override
	public void cacheResult(Module module) {
		entityCache.putResult(ModuleModelImpl.ENTITY_CACHE_ENABLED,
			ModuleImpl.class, module.getPrimaryKey(), module);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { module.getUuid(), module.getGroupId() }, module);

		module.resetOriginalValues();
	}

	/**
	 * Caches the modules in the entity cache if it is enabled.
	 *
	 * @param modules the modules
	 */
	@Override
	public void cacheResult(List<Module> modules) {
		for (Module module : modules) {
			if (entityCache.getResult(ModuleModelImpl.ENTITY_CACHE_ENABLED,
						ModuleImpl.class, module.getPrimaryKey()) == null) {
				cacheResult(module);
			}
			else {
				module.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all modules.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ModuleImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the module.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Module module) {
		entityCache.removeResult(ModuleModelImpl.ENTITY_CACHE_ENABLED,
			ModuleImpl.class, module.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((ModuleModelImpl)module, true);
	}

	@Override
	public void clearCache(List<Module> modules) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Module module : modules) {
			entityCache.removeResult(ModuleModelImpl.ENTITY_CACHE_ENABLED,
				ModuleImpl.class, module.getPrimaryKey());

			clearUniqueFindersCache((ModuleModelImpl)module, true);
		}
	}

	protected void cacheUniqueFindersCache(ModuleModelImpl moduleModelImpl) {
		Object[] args = new Object[] {
				moduleModelImpl.getUuid(), moduleModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			moduleModelImpl, false);
	}

	protected void clearUniqueFindersCache(ModuleModelImpl moduleModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					moduleModelImpl.getUuid(), moduleModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((moduleModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					moduleModelImpl.getOriginalUuid(),
					moduleModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new module with the primary key. Does not add the module to the database.
	 *
	 * @param moduleId the primary key for the new module
	 * @return the new module
	 */
	@Override
	public Module create(long moduleId) {
		Module module = new ModuleImpl();

		module.setNew(true);
		module.setPrimaryKey(moduleId);

		String uuid = PortalUUIDUtil.generate();

		module.setUuid(uuid);

		module.setCompanyId(companyProvider.getCompanyId());

		return module;
	}

	/**
	 * Removes the module with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param moduleId the primary key of the module
	 * @return the module that was removed
	 * @throws NoSuchModuleException if a module with the primary key could not be found
	 */
	@Override
	public Module remove(long moduleId) throws NoSuchModuleException {
		return remove((Serializable)moduleId);
	}

	/**
	 * Removes the module with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the module
	 * @return the module that was removed
	 * @throws NoSuchModuleException if a module with the primary key could not be found
	 */
	@Override
	public Module remove(Serializable primaryKey) throws NoSuchModuleException {
		Session session = null;

		try {
			session = openSession();

			Module module = (Module)session.get(ModuleImpl.class, primaryKey);

			if (module == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchModuleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(module);
		}
		catch (NoSuchModuleException nsee) {
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
	protected Module removeImpl(Module module) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(module)) {
				module = (Module)session.get(ModuleImpl.class,
						module.getPrimaryKeyObj());
			}

			if (module != null) {
				session.delete(module);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (module != null) {
			clearCache(module);
		}

		return module;
	}

	@Override
	public Module updateImpl(Module module) {
		boolean isNew = module.isNew();

		if (!(module instanceof ModuleModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(module.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(module);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in module proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Module implementation " +
				module.getClass());
		}

		ModuleModelImpl moduleModelImpl = (ModuleModelImpl)module;

		if (Validator.isNull(module.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			module.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (module.getCreateDate() == null)) {
			if (serviceContext == null) {
				module.setCreateDate(now);
			}
			else {
				module.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!moduleModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				module.setModifiedDate(now);
			}
			else {
				module.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (module.isNew()) {
				session.save(module);

				module.setNew(false);
			}
			else {
				module = (Module)session.merge(module);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ModuleModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { moduleModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					moduleModelImpl.getUuid(), moduleModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] { moduleModelImpl.getCompanyId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
				args);

			args = new Object[] { moduleModelImpl.getGroupId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
				args);

			args = new Object[] {
					moduleModelImpl.getGroupId(), moduleModelImpl.getStatus()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPIDSTATUS, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDSTATUS,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((moduleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { moduleModelImpl.getOriginalUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { moduleModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((moduleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						moduleModelImpl.getOriginalUuid(),
						moduleModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						moduleModelImpl.getUuid(),
						moduleModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((moduleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						moduleModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);

				args = new Object[] { moduleModelImpl.getCompanyId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);
			}

			if ((moduleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						moduleModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { moduleModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}

			if ((moduleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDSTATUS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						moduleModelImpl.getOriginalGroupId(),
						moduleModelImpl.getOriginalStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPIDSTATUS,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDSTATUS,
					args);

				args = new Object[] {
						moduleModelImpl.getGroupId(),
						moduleModelImpl.getStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPIDSTATUS,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPIDSTATUS,
					args);
			}
		}

		entityCache.putResult(ModuleModelImpl.ENTITY_CACHE_ENABLED,
			ModuleImpl.class, module.getPrimaryKey(), module, false);

		clearUniqueFindersCache(moduleModelImpl, false);
		cacheUniqueFindersCache(moduleModelImpl);

		module.resetOriginalValues();

		return module;
	}

	/**
	 * Returns the module with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the module
	 * @return the module
	 * @throws NoSuchModuleException if a module with the primary key could not be found
	 */
	@Override
	public Module findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModuleException {
		Module module = fetchByPrimaryKey(primaryKey);

		if (module == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchModuleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return module;
	}

	/**
	 * Returns the module with the primary key or throws a {@link NoSuchModuleException} if it could not be found.
	 *
	 * @param moduleId the primary key of the module
	 * @return the module
	 * @throws NoSuchModuleException if a module with the primary key could not be found
	 */
	@Override
	public Module findByPrimaryKey(long moduleId) throws NoSuchModuleException {
		return findByPrimaryKey((Serializable)moduleId);
	}

	/**
	 * Returns the module with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the module
	 * @return the module, or <code>null</code> if a module with the primary key could not be found
	 */
	@Override
	public Module fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ModuleModelImpl.ENTITY_CACHE_ENABLED,
				ModuleImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Module module = (Module)serializable;

		if (module == null) {
			Session session = null;

			try {
				session = openSession();

				module = (Module)session.get(ModuleImpl.class, primaryKey);

				if (module != null) {
					cacheResult(module);
				}
				else {
					entityCache.putResult(ModuleModelImpl.ENTITY_CACHE_ENABLED,
						ModuleImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ModuleModelImpl.ENTITY_CACHE_ENABLED,
					ModuleImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return module;
	}

	/**
	 * Returns the module with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param moduleId the primary key of the module
	 * @return the module, or <code>null</code> if a module with the primary key could not be found
	 */
	@Override
	public Module fetchByPrimaryKey(long moduleId) {
		return fetchByPrimaryKey((Serializable)moduleId);
	}

	@Override
	public Map<Serializable, Module> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Module> map = new HashMap<Serializable, Module>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Module module = fetchByPrimaryKey(primaryKey);

			if (module != null) {
				map.put(primaryKey, module);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ModuleModelImpl.ENTITY_CACHE_ENABLED,
					ModuleImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Module)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_MODULE_WHERE_PKS_IN);

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

			for (Module module : (List<Module>)q.list()) {
				map.put(module.getPrimaryKeyObj(), module);

				cacheResult(module);

				uncachedPrimaryKeys.remove(module.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ModuleModelImpl.ENTITY_CACHE_ENABLED,
					ModuleImpl.class, primaryKey, nullModel);
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
	 * Returns all the modules.
	 *
	 * @return the modules
	 */
	@Override
	public List<Module> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the modules.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @return the range of modules
	 */
	@Override
	public List<Module> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the modules.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of modules
	 */
	@Override
	public List<Module> findAll(int start, int end,
		OrderByComparator<Module> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the modules.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of modules
	 */
	@Override
	public List<Module> findAll(int start, int end,
		OrderByComparator<Module> orderByComparator, boolean retrieveFromCache) {
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

		List<Module> list = null;

		if (retrieveFromCache) {
			list = (List<Module>)finderCache.getResult(finderPath, finderArgs,
					this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_MODULE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_MODULE;

				if (pagination) {
					sql = sql.concat(ModuleModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Module>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Module>)QueryUtil.list(q, getDialect(), start,
							end);
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
	 * Removes all the modules from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Module module : findAll()) {
			remove(module);
		}
	}

	/**
	 * Returns the number of modules.
	 *
	 * @return the number of modules
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_MODULE);

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
		return ModuleModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the module persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ModuleImpl.class.getName());
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
	private static final String _SQL_SELECT_MODULE = "SELECT module FROM Module module";
	private static final String _SQL_SELECT_MODULE_WHERE_PKS_IN = "SELECT module FROM Module module WHERE moduleId IN (";
	private static final String _SQL_SELECT_MODULE_WHERE = "SELECT module FROM Module module WHERE ";
	private static final String _SQL_COUNT_MODULE = "SELECT COUNT(module) FROM Module module";
	private static final String _SQL_COUNT_MODULE_WHERE = "SELECT COUNT(module) FROM Module module WHERE ";
	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN = "module.moduleId";
	private static final String _FILTER_SQL_SELECT_MODULE_WHERE = "SELECT DISTINCT {module.*} FROM LMS_Module module WHERE ";
	private static final String _FILTER_SQL_SELECT_MODULE_NO_INLINE_DISTINCT_WHERE_1 =
		"SELECT {LMS_Module.*} FROM (SELECT DISTINCT module.moduleId FROM LMS_Module module WHERE ";
	private static final String _FILTER_SQL_SELECT_MODULE_NO_INLINE_DISTINCT_WHERE_2 =
		") TEMP_TABLE INNER JOIN LMS_Module ON TEMP_TABLE.moduleId = LMS_Module.moduleId";
	private static final String _FILTER_SQL_COUNT_MODULE_WHERE = "SELECT COUNT(DISTINCT module.moduleId) AS COUNT_VALUE FROM LMS_Module module WHERE ";
	private static final String _FILTER_ENTITY_ALIAS = "module";
	private static final String _FILTER_ENTITY_TABLE = "LMS_Module";
	private static final String _ORDER_BY_ENTITY_ALIAS = "module.";
	private static final String _ORDER_BY_ENTITY_TABLE = "LMS_Module.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Module exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Module exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ModulePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid", "order"
			});
}