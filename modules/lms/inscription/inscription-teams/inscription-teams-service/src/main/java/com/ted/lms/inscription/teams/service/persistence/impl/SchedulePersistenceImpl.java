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

package com.ted.lms.inscription.teams.service.persistence.impl;

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
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import com.ted.lms.inscription.teams.exception.NoSuchScheduleException;
import com.ted.lms.inscription.teams.model.Schedule;
import com.ted.lms.inscription.teams.model.impl.ScheduleImpl;
import com.ted.lms.inscription.teams.model.impl.ScheduleModelImpl;
import com.ted.lms.inscription.teams.service.persistence.SchedulePersistence;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the schedule service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SchedulePersistence
 * @see com.ted.lms.inscription.teams.service.persistence.ScheduleUtil
 * @generated
 */
@ProviderType
public class SchedulePersistenceImpl extends BasePersistenceImpl<Schedule>
	implements SchedulePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ScheduleUtil} to access the schedule persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ScheduleImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ScheduleModelImpl.ENTITY_CACHE_ENABLED,
			ScheduleModelImpl.FINDER_CACHE_ENABLED, ScheduleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ScheduleModelImpl.ENTITY_CACHE_ENABLED,
			ScheduleModelImpl.FINDER_CACHE_ENABLED, ScheduleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ScheduleModelImpl.ENTITY_CACHE_ENABLED,
			ScheduleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_TEAMID = new FinderPath(ScheduleModelImpl.ENTITY_CACHE_ENABLED,
			ScheduleModelImpl.FINDER_CACHE_ENABLED, ScheduleImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByTeamId",
			new String[] { Long.class.getName() },
			ScheduleModelImpl.TEAMID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TEAMID = new FinderPath(ScheduleModelImpl.ENTITY_CACHE_ENABLED,
			ScheduleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTeamId",
			new String[] { Long.class.getName() });

	/**
	 * Returns the schedule where teamId = &#63; or throws a {@link NoSuchScheduleException} if it could not be found.
	 *
	 * @param teamId the team ID
	 * @return the matching schedule
	 * @throws NoSuchScheduleException if a matching schedule could not be found
	 */
	@Override
	public Schedule findByTeamId(long teamId) throws NoSuchScheduleException {
		Schedule schedule = fetchByTeamId(teamId);

		if (schedule == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("teamId=");
			msg.append(teamId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchScheduleException(msg.toString());
		}

		return schedule;
	}

	/**
	 * Returns the schedule where teamId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param teamId the team ID
	 * @return the matching schedule, or <code>null</code> if a matching schedule could not be found
	 */
	@Override
	public Schedule fetchByTeamId(long teamId) {
		return fetchByTeamId(teamId, true);
	}

	/**
	 * Returns the schedule where teamId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param teamId the team ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching schedule, or <code>null</code> if a matching schedule could not be found
	 */
	@Override
	public Schedule fetchByTeamId(long teamId, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { teamId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_TEAMID,
					finderArgs, this);
		}

		if (result instanceof Schedule) {
			Schedule schedule = (Schedule)result;

			if ((teamId != schedule.getTeamId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_SCHEDULE_WHERE);

			query.append(_FINDER_COLUMN_TEAMID_TEAMID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(teamId);

				List<Schedule> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_TEAMID,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"SchedulePersistenceImpl.fetchByTeamId(long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					Schedule schedule = list.get(0);

					result = schedule;

					cacheResult(schedule);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_TEAMID, finderArgs);

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
			return (Schedule)result;
		}
	}

	/**
	 * Removes the schedule where teamId = &#63; from the database.
	 *
	 * @param teamId the team ID
	 * @return the schedule that was removed
	 */
	@Override
	public Schedule removeByTeamId(long teamId) throws NoSuchScheduleException {
		Schedule schedule = findByTeamId(teamId);

		return remove(schedule);
	}

	/**
	 * Returns the number of schedules where teamId = &#63;.
	 *
	 * @param teamId the team ID
	 * @return the number of matching schedules
	 */
	@Override
	public int countByTeamId(long teamId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_TEAMID;

		Object[] finderArgs = new Object[] { teamId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SCHEDULE_WHERE);

			query.append(_FINDER_COLUMN_TEAMID_TEAMID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(teamId);

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

	private static final String _FINDER_COLUMN_TEAMID_TEAMID_2 = "schedule.teamId = ?";

	public SchedulePersistenceImpl() {
		setModelClass(Schedule.class);
	}

	/**
	 * Caches the schedule in the entity cache if it is enabled.
	 *
	 * @param schedule the schedule
	 */
	@Override
	public void cacheResult(Schedule schedule) {
		entityCache.putResult(ScheduleModelImpl.ENTITY_CACHE_ENABLED,
			ScheduleImpl.class, schedule.getPrimaryKey(), schedule);

		finderCache.putResult(FINDER_PATH_FETCH_BY_TEAMID,
			new Object[] { schedule.getTeamId() }, schedule);

		schedule.resetOriginalValues();
	}

	/**
	 * Caches the schedules in the entity cache if it is enabled.
	 *
	 * @param schedules the schedules
	 */
	@Override
	public void cacheResult(List<Schedule> schedules) {
		for (Schedule schedule : schedules) {
			if (entityCache.getResult(ScheduleModelImpl.ENTITY_CACHE_ENABLED,
						ScheduleImpl.class, schedule.getPrimaryKey()) == null) {
				cacheResult(schedule);
			}
			else {
				schedule.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all schedules.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ScheduleImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the schedule.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Schedule schedule) {
		entityCache.removeResult(ScheduleModelImpl.ENTITY_CACHE_ENABLED,
			ScheduleImpl.class, schedule.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((ScheduleModelImpl)schedule, true);
	}

	@Override
	public void clearCache(List<Schedule> schedules) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Schedule schedule : schedules) {
			entityCache.removeResult(ScheduleModelImpl.ENTITY_CACHE_ENABLED,
				ScheduleImpl.class, schedule.getPrimaryKey());

			clearUniqueFindersCache((ScheduleModelImpl)schedule, true);
		}
	}

	protected void cacheUniqueFindersCache(ScheduleModelImpl scheduleModelImpl) {
		Object[] args = new Object[] { scheduleModelImpl.getTeamId() };

		finderCache.putResult(FINDER_PATH_COUNT_BY_TEAMID, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_TEAMID, args,
			scheduleModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		ScheduleModelImpl scheduleModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] { scheduleModelImpl.getTeamId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_TEAMID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_TEAMID, args);
		}

		if ((scheduleModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_TEAMID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] { scheduleModelImpl.getOriginalTeamId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_TEAMID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_TEAMID, args);
		}
	}

	/**
	 * Creates a new schedule with the primary key. Does not add the schedule to the database.
	 *
	 * @param scheduleId the primary key for the new schedule
	 * @return the new schedule
	 */
	@Override
	public Schedule create(long scheduleId) {
		Schedule schedule = new ScheduleImpl();

		schedule.setNew(true);
		schedule.setPrimaryKey(scheduleId);

		return schedule;
	}

	/**
	 * Removes the schedule with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param scheduleId the primary key of the schedule
	 * @return the schedule that was removed
	 * @throws NoSuchScheduleException if a schedule with the primary key could not be found
	 */
	@Override
	public Schedule remove(long scheduleId) throws NoSuchScheduleException {
		return remove((Serializable)scheduleId);
	}

	/**
	 * Removes the schedule with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the schedule
	 * @return the schedule that was removed
	 * @throws NoSuchScheduleException if a schedule with the primary key could not be found
	 */
	@Override
	public Schedule remove(Serializable primaryKey)
		throws NoSuchScheduleException {
		Session session = null;

		try {
			session = openSession();

			Schedule schedule = (Schedule)session.get(ScheduleImpl.class,
					primaryKey);

			if (schedule == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchScheduleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(schedule);
		}
		catch (NoSuchScheduleException nsee) {
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
	protected Schedule removeImpl(Schedule schedule) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(schedule)) {
				schedule = (Schedule)session.get(ScheduleImpl.class,
						schedule.getPrimaryKeyObj());
			}

			if (schedule != null) {
				session.delete(schedule);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (schedule != null) {
			clearCache(schedule);
		}

		return schedule;
	}

	@Override
	public Schedule updateImpl(Schedule schedule) {
		boolean isNew = schedule.isNew();

		if (!(schedule instanceof ScheduleModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(schedule.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(schedule);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in schedule proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Schedule implementation " +
				schedule.getClass());
		}

		ScheduleModelImpl scheduleModelImpl = (ScheduleModelImpl)schedule;

		Session session = null;

		try {
			session = openSession();

			if (schedule.isNew()) {
				session.save(schedule);

				schedule.setNew(false);
			}
			else {
				schedule = (Schedule)session.merge(schedule);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ScheduleModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(ScheduleModelImpl.ENTITY_CACHE_ENABLED,
			ScheduleImpl.class, schedule.getPrimaryKey(), schedule, false);

		clearUniqueFindersCache(scheduleModelImpl, false);
		cacheUniqueFindersCache(scheduleModelImpl);

		schedule.resetOriginalValues();

		return schedule;
	}

	/**
	 * Returns the schedule with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the schedule
	 * @return the schedule
	 * @throws NoSuchScheduleException if a schedule with the primary key could not be found
	 */
	@Override
	public Schedule findByPrimaryKey(Serializable primaryKey)
		throws NoSuchScheduleException {
		Schedule schedule = fetchByPrimaryKey(primaryKey);

		if (schedule == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchScheduleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return schedule;
	}

	/**
	 * Returns the schedule with the primary key or throws a {@link NoSuchScheduleException} if it could not be found.
	 *
	 * @param scheduleId the primary key of the schedule
	 * @return the schedule
	 * @throws NoSuchScheduleException if a schedule with the primary key could not be found
	 */
	@Override
	public Schedule findByPrimaryKey(long scheduleId)
		throws NoSuchScheduleException {
		return findByPrimaryKey((Serializable)scheduleId);
	}

	/**
	 * Returns the schedule with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the schedule
	 * @return the schedule, or <code>null</code> if a schedule with the primary key could not be found
	 */
	@Override
	public Schedule fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ScheduleModelImpl.ENTITY_CACHE_ENABLED,
				ScheduleImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Schedule schedule = (Schedule)serializable;

		if (schedule == null) {
			Session session = null;

			try {
				session = openSession();

				schedule = (Schedule)session.get(ScheduleImpl.class, primaryKey);

				if (schedule != null) {
					cacheResult(schedule);
				}
				else {
					entityCache.putResult(ScheduleModelImpl.ENTITY_CACHE_ENABLED,
						ScheduleImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ScheduleModelImpl.ENTITY_CACHE_ENABLED,
					ScheduleImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return schedule;
	}

	/**
	 * Returns the schedule with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param scheduleId the primary key of the schedule
	 * @return the schedule, or <code>null</code> if a schedule with the primary key could not be found
	 */
	@Override
	public Schedule fetchByPrimaryKey(long scheduleId) {
		return fetchByPrimaryKey((Serializable)scheduleId);
	}

	@Override
	public Map<Serializable, Schedule> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Schedule> map = new HashMap<Serializable, Schedule>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Schedule schedule = fetchByPrimaryKey(primaryKey);

			if (schedule != null) {
				map.put(primaryKey, schedule);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ScheduleModelImpl.ENTITY_CACHE_ENABLED,
					ScheduleImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Schedule)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_SCHEDULE_WHERE_PKS_IN);

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

			for (Schedule schedule : (List<Schedule>)q.list()) {
				map.put(schedule.getPrimaryKeyObj(), schedule);

				cacheResult(schedule);

				uncachedPrimaryKeys.remove(schedule.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ScheduleModelImpl.ENTITY_CACHE_ENABLED,
					ScheduleImpl.class, primaryKey, nullModel);
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
	 * Returns all the schedules.
	 *
	 * @return the schedules
	 */
	@Override
	public List<Schedule> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the schedules.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of schedules
	 * @param end the upper bound of the range of schedules (not inclusive)
	 * @return the range of schedules
	 */
	@Override
	public List<Schedule> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the schedules.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of schedules
	 * @param end the upper bound of the range of schedules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of schedules
	 */
	@Override
	public List<Schedule> findAll(int start, int end,
		OrderByComparator<Schedule> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the schedules.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of schedules
	 * @param end the upper bound of the range of schedules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of schedules
	 */
	@Override
	public List<Schedule> findAll(int start, int end,
		OrderByComparator<Schedule> orderByComparator, boolean retrieveFromCache) {
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

		List<Schedule> list = null;

		if (retrieveFromCache) {
			list = (List<Schedule>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_SCHEDULE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SCHEDULE;

				if (pagination) {
					sql = sql.concat(ScheduleModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Schedule>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Schedule>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the schedules from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Schedule schedule : findAll()) {
			remove(schedule);
		}
	}

	/**
	 * Returns the number of schedules.
	 *
	 * @return the number of schedules
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SCHEDULE);

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
		return ScheduleModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the schedule persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ScheduleImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_SCHEDULE = "SELECT schedule FROM Schedule schedule";
	private static final String _SQL_SELECT_SCHEDULE_WHERE_PKS_IN = "SELECT schedule FROM Schedule schedule WHERE scheduleId IN (";
	private static final String _SQL_SELECT_SCHEDULE_WHERE = "SELECT schedule FROM Schedule schedule WHERE ";
	private static final String _SQL_COUNT_SCHEDULE = "SELECT COUNT(schedule) FROM Schedule schedule";
	private static final String _SQL_COUNT_SCHEDULE_WHERE = "SELECT COUNT(schedule) FROM Schedule schedule WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "schedule.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Schedule exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Schedule exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(SchedulePersistenceImpl.class);
}