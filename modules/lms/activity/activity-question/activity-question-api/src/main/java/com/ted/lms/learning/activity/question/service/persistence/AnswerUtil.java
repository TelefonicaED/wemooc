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

package com.ted.lms.learning.activity.question.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.ted.lms.learning.activity.question.model.Answer;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.annotation.versioning.ProviderType;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the answer service. This utility wraps <code>com.ted.lms.learning.activity.question.service.persistence.impl.AnswerPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AnswerPersistence
 * @generated
 */
@ProviderType
public class AnswerUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(Answer answer) {
		getPersistence().clearCache(answer);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, Answer> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Answer> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Answer> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Answer> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Answer> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Answer update(Answer answer) {
		return getPersistence().update(answer);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Answer update(Answer answer, ServiceContext serviceContext) {
		return getPersistence().update(answer, serviceContext);
	}

	/**
	 * Returns all the answers where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching answers
	 */
	public static List<Answer> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the answers where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AnswerModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of answers
	 * @param end the upper bound of the range of answers (not inclusive)
	 * @return the range of matching answers
	 */
	public static List<Answer> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the answers where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AnswerModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of answers
	 * @param end the upper bound of the range of answers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching answers
	 */
	public static List<Answer> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Answer> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the answers where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AnswerModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of answers
	 * @param end the upper bound of the range of answers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching answers
	 */
	public static List<Answer> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Answer> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Returns the first answer in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching answer
	 * @throws NoSuchAnswerException if a matching answer could not be found
	 */
	public static Answer findByUuid_First(
			String uuid, OrderByComparator<Answer> orderByComparator)
		throws com.ted.lms.learning.activity.question.exception.
			NoSuchAnswerException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first answer in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching answer, or <code>null</code> if a matching answer could not be found
	 */
	public static Answer fetchByUuid_First(
		String uuid, OrderByComparator<Answer> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last answer in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching answer
	 * @throws NoSuchAnswerException if a matching answer could not be found
	 */
	public static Answer findByUuid_Last(
			String uuid, OrderByComparator<Answer> orderByComparator)
		throws com.ted.lms.learning.activity.question.exception.
			NoSuchAnswerException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last answer in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching answer, or <code>null</code> if a matching answer could not be found
	 */
	public static Answer fetchByUuid_Last(
		String uuid, OrderByComparator<Answer> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the answers before and after the current answer in the ordered set where uuid = &#63;.
	 *
	 * @param answerId the primary key of the current answer
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next answer
	 * @throws NoSuchAnswerException if a answer with the primary key could not be found
	 */
	public static Answer[] findByUuid_PrevAndNext(
			long answerId, String uuid,
			OrderByComparator<Answer> orderByComparator)
		throws com.ted.lms.learning.activity.question.exception.
			NoSuchAnswerException {

		return getPersistence().findByUuid_PrevAndNext(
			answerId, uuid, orderByComparator);
	}

	/**
	 * Removes all the answers where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of answers where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching answers
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the answer where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchAnswerException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching answer
	 * @throws NoSuchAnswerException if a matching answer could not be found
	 */
	public static Answer findByUUID_G(String uuid, long groupId)
		throws com.ted.lms.learning.activity.question.exception.
			NoSuchAnswerException {

		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the answer where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching answer, or <code>null</code> if a matching answer could not be found
	 */
	public static Answer fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the answer where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching answer, or <code>null</code> if a matching answer could not be found
	 */
	public static Answer fetchByUUID_G(
		String uuid, long groupId, boolean retrieveFromCache) {

		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	 * Removes the answer where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the answer that was removed
	 */
	public static Answer removeByUUID_G(String uuid, long groupId)
		throws com.ted.lms.learning.activity.question.exception.
			NoSuchAnswerException {

		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the number of answers where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching answers
	 */
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	 * Returns all the answers where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching answers
	 */
	public static List<Answer> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the answers where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AnswerModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of answers
	 * @param end the upper bound of the range of answers (not inclusive)
	 * @return the range of matching answers
	 */
	public static List<Answer> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the answers where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AnswerModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of answers
	 * @param end the upper bound of the range of answers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching answers
	 */
	public static List<Answer> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Answer> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the answers where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AnswerModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of answers
	 * @param end the upper bound of the range of answers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching answers
	 */
	public static List<Answer> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Answer> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Returns the first answer in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching answer
	 * @throws NoSuchAnswerException if a matching answer could not be found
	 */
	public static Answer findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<Answer> orderByComparator)
		throws com.ted.lms.learning.activity.question.exception.
			NoSuchAnswerException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first answer in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching answer, or <code>null</code> if a matching answer could not be found
	 */
	public static Answer fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<Answer> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last answer in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching answer
	 * @throws NoSuchAnswerException if a matching answer could not be found
	 */
	public static Answer findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<Answer> orderByComparator)
		throws com.ted.lms.learning.activity.question.exception.
			NoSuchAnswerException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last answer in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching answer, or <code>null</code> if a matching answer could not be found
	 */
	public static Answer fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<Answer> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the answers before and after the current answer in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param answerId the primary key of the current answer
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next answer
	 * @throws NoSuchAnswerException if a answer with the primary key could not be found
	 */
	public static Answer[] findByUuid_C_PrevAndNext(
			long answerId, String uuid, long companyId,
			OrderByComparator<Answer> orderByComparator)
		throws com.ted.lms.learning.activity.question.exception.
			NoSuchAnswerException {

		return getPersistence().findByUuid_C_PrevAndNext(
			answerId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the answers where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of answers where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching answers
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns all the answers where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching answers
	 */
	public static List<Answer> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	 * Returns a range of all the answers where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AnswerModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of answers
	 * @param end the upper bound of the range of answers (not inclusive)
	 * @return the range of matching answers
	 */
	public static List<Answer> findByGroupId(long groupId, int start, int end) {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	 * Returns an ordered range of all the answers where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AnswerModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of answers
	 * @param end the upper bound of the range of answers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching answers
	 */
	public static List<Answer> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<Answer> orderByComparator) {

		return getPersistence().findByGroupId(
			groupId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the answers where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AnswerModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of answers
	 * @param end the upper bound of the range of answers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching answers
	 */
	public static List<Answer> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<Answer> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findByGroupId(
			groupId, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Returns the first answer in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching answer
	 * @throws NoSuchAnswerException if a matching answer could not be found
	 */
	public static Answer findByGroupId_First(
			long groupId, OrderByComparator<Answer> orderByComparator)
		throws com.ted.lms.learning.activity.question.exception.
			NoSuchAnswerException {

		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	 * Returns the first answer in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching answer, or <code>null</code> if a matching answer could not be found
	 */
	public static Answer fetchByGroupId_First(
		long groupId, OrderByComparator<Answer> orderByComparator) {

		return getPersistence().fetchByGroupId_First(
			groupId, orderByComparator);
	}

	/**
	 * Returns the last answer in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching answer
	 * @throws NoSuchAnswerException if a matching answer could not be found
	 */
	public static Answer findByGroupId_Last(
			long groupId, OrderByComparator<Answer> orderByComparator)
		throws com.ted.lms.learning.activity.question.exception.
			NoSuchAnswerException {

		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the last answer in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching answer, or <code>null</code> if a matching answer could not be found
	 */
	public static Answer fetchByGroupId_Last(
		long groupId, OrderByComparator<Answer> orderByComparator) {

		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the answers before and after the current answer in the ordered set where groupId = &#63;.
	 *
	 * @param answerId the primary key of the current answer
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next answer
	 * @throws NoSuchAnswerException if a answer with the primary key could not be found
	 */
	public static Answer[] findByGroupId_PrevAndNext(
			long answerId, long groupId,
			OrderByComparator<Answer> orderByComparator)
		throws com.ted.lms.learning.activity.question.exception.
			NoSuchAnswerException {

		return getPersistence().findByGroupId_PrevAndNext(
			answerId, groupId, orderByComparator);
	}

	/**
	 * Removes all the answers where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	 * Returns the number of answers where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching answers
	 */
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	 * Returns all the answers where questionId = &#63;.
	 *
	 * @param questionId the question ID
	 * @return the matching answers
	 */
	public static List<Answer> findByQuestionId(long questionId) {
		return getPersistence().findByQuestionId(questionId);
	}

	/**
	 * Returns a range of all the answers where questionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AnswerModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param questionId the question ID
	 * @param start the lower bound of the range of answers
	 * @param end the upper bound of the range of answers (not inclusive)
	 * @return the range of matching answers
	 */
	public static List<Answer> findByQuestionId(
		long questionId, int start, int end) {

		return getPersistence().findByQuestionId(questionId, start, end);
	}

	/**
	 * Returns an ordered range of all the answers where questionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AnswerModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param questionId the question ID
	 * @param start the lower bound of the range of answers
	 * @param end the upper bound of the range of answers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching answers
	 */
	public static List<Answer> findByQuestionId(
		long questionId, int start, int end,
		OrderByComparator<Answer> orderByComparator) {

		return getPersistence().findByQuestionId(
			questionId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the answers where questionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AnswerModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param questionId the question ID
	 * @param start the lower bound of the range of answers
	 * @param end the upper bound of the range of answers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching answers
	 */
	public static List<Answer> findByQuestionId(
		long questionId, int start, int end,
		OrderByComparator<Answer> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findByQuestionId(
			questionId, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Returns the first answer in the ordered set where questionId = &#63;.
	 *
	 * @param questionId the question ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching answer
	 * @throws NoSuchAnswerException if a matching answer could not be found
	 */
	public static Answer findByQuestionId_First(
			long questionId, OrderByComparator<Answer> orderByComparator)
		throws com.ted.lms.learning.activity.question.exception.
			NoSuchAnswerException {

		return getPersistence().findByQuestionId_First(
			questionId, orderByComparator);
	}

	/**
	 * Returns the first answer in the ordered set where questionId = &#63;.
	 *
	 * @param questionId the question ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching answer, or <code>null</code> if a matching answer could not be found
	 */
	public static Answer fetchByQuestionId_First(
		long questionId, OrderByComparator<Answer> orderByComparator) {

		return getPersistence().fetchByQuestionId_First(
			questionId, orderByComparator);
	}

	/**
	 * Returns the last answer in the ordered set where questionId = &#63;.
	 *
	 * @param questionId the question ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching answer
	 * @throws NoSuchAnswerException if a matching answer could not be found
	 */
	public static Answer findByQuestionId_Last(
			long questionId, OrderByComparator<Answer> orderByComparator)
		throws com.ted.lms.learning.activity.question.exception.
			NoSuchAnswerException {

		return getPersistence().findByQuestionId_Last(
			questionId, orderByComparator);
	}

	/**
	 * Returns the last answer in the ordered set where questionId = &#63;.
	 *
	 * @param questionId the question ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching answer, or <code>null</code> if a matching answer could not be found
	 */
	public static Answer fetchByQuestionId_Last(
		long questionId, OrderByComparator<Answer> orderByComparator) {

		return getPersistence().fetchByQuestionId_Last(
			questionId, orderByComparator);
	}

	/**
	 * Returns the answers before and after the current answer in the ordered set where questionId = &#63;.
	 *
	 * @param answerId the primary key of the current answer
	 * @param questionId the question ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next answer
	 * @throws NoSuchAnswerException if a answer with the primary key could not be found
	 */
	public static Answer[] findByQuestionId_PrevAndNext(
			long answerId, long questionId,
			OrderByComparator<Answer> orderByComparator)
		throws com.ted.lms.learning.activity.question.exception.
			NoSuchAnswerException {

		return getPersistence().findByQuestionId_PrevAndNext(
			answerId, questionId, orderByComparator);
	}

	/**
	 * Removes all the answers where questionId = &#63; from the database.
	 *
	 * @param questionId the question ID
	 */
	public static void removeByQuestionId(long questionId) {
		getPersistence().removeByQuestionId(questionId);
	}

	/**
	 * Returns the number of answers where questionId = &#63;.
	 *
	 * @param questionId the question ID
	 * @return the number of matching answers
	 */
	public static int countByQuestionId(long questionId) {
		return getPersistence().countByQuestionId(questionId);
	}

	/**
	 * Caches the answer in the entity cache if it is enabled.
	 *
	 * @param answer the answer
	 */
	public static void cacheResult(Answer answer) {
		getPersistence().cacheResult(answer);
	}

	/**
	 * Caches the answers in the entity cache if it is enabled.
	 *
	 * @param answers the answers
	 */
	public static void cacheResult(List<Answer> answers) {
		getPersistence().cacheResult(answers);
	}

	/**
	 * Creates a new answer with the primary key. Does not add the answer to the database.
	 *
	 * @param answerId the primary key for the new answer
	 * @return the new answer
	 */
	public static Answer create(long answerId) {
		return getPersistence().create(answerId);
	}

	/**
	 * Removes the answer with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param answerId the primary key of the answer
	 * @return the answer that was removed
	 * @throws NoSuchAnswerException if a answer with the primary key could not be found
	 */
	public static Answer remove(long answerId)
		throws com.ted.lms.learning.activity.question.exception.
			NoSuchAnswerException {

		return getPersistence().remove(answerId);
	}

	public static Answer updateImpl(Answer answer) {
		return getPersistence().updateImpl(answer);
	}

	/**
	 * Returns the answer with the primary key or throws a <code>NoSuchAnswerException</code> if it could not be found.
	 *
	 * @param answerId the primary key of the answer
	 * @return the answer
	 * @throws NoSuchAnswerException if a answer with the primary key could not be found
	 */
	public static Answer findByPrimaryKey(long answerId)
		throws com.ted.lms.learning.activity.question.exception.
			NoSuchAnswerException {

		return getPersistence().findByPrimaryKey(answerId);
	}

	/**
	 * Returns the answer with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param answerId the primary key of the answer
	 * @return the answer, or <code>null</code> if a answer with the primary key could not be found
	 */
	public static Answer fetchByPrimaryKey(long answerId) {
		return getPersistence().fetchByPrimaryKey(answerId);
	}

	/**
	 * Returns all the answers.
	 *
	 * @return the answers
	 */
	public static List<Answer> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the answers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AnswerModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of answers
	 * @param end the upper bound of the range of answers (not inclusive)
	 * @return the range of answers
	 */
	public static List<Answer> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the answers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AnswerModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of answers
	 * @param end the upper bound of the range of answers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of answers
	 */
	public static List<Answer> findAll(
		int start, int end, OrderByComparator<Answer> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the answers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AnswerModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of answers
	 * @param end the upper bound of the range of answers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of answers
	 */
	public static List<Answer> findAll(
		int start, int end, OrderByComparator<Answer> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Removes all the answers from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of answers.
	 *
	 * @return the number of answers
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static AnswerPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<AnswerPersistence, AnswerPersistence>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(AnswerPersistence.class);

		ServiceTracker<AnswerPersistence, AnswerPersistence> serviceTracker =
			new ServiceTracker<AnswerPersistence, AnswerPersistence>(
				bundle.getBundleContext(), AnswerPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}