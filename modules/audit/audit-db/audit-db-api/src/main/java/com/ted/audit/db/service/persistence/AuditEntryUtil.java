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

package com.ted.audit.db.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.ted.audit.db.model.AuditEntry;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the audit entry service. This utility wraps {@link com.ted.audit.db.service.persistence.impl.AuditEntryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AuditEntryPersistence
 * @see com.ted.audit.db.service.persistence.impl.AuditEntryPersistenceImpl
 * @generated
 */
@ProviderType
public class AuditEntryUtil {
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
	public static void clearCache(AuditEntry auditEntry) {
		getPersistence().clearCache(auditEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<AuditEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AuditEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AuditEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<AuditEntry> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static AuditEntry update(AuditEntry auditEntry) {
		return getPersistence().update(auditEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static AuditEntry update(AuditEntry auditEntry,
		ServiceContext serviceContext) {
		return getPersistence().update(auditEntry, serviceContext);
	}

	/**
	* Returns all the audit entries where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching audit entries
	*/
	public static List<AuditEntry> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	* Returns a range of all the audit entries where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of audit entries
	* @param end the upper bound of the range of audit entries (not inclusive)
	* @return the range of matching audit entries
	*/
	public static List<AuditEntry> findByGroupId(long groupId, int start,
		int end) {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the audit entries where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of audit entries
	* @param end the upper bound of the range of audit entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching audit entries
	*/
	public static List<AuditEntry> findByGroupId(long groupId, int start,
		int end, OrderByComparator<AuditEntry> orderByComparator) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the audit entries where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of audit entries
	* @param end the upper bound of the range of audit entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching audit entries
	*/
	public static List<AuditEntry> findByGroupId(long groupId, int start,
		int end, OrderByComparator<AuditEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first audit entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit entry
	* @throws NoSuchAuditEntryException if a matching audit entry could not be found
	*/
	public static AuditEntry findByGroupId_First(long groupId,
		OrderByComparator<AuditEntry> orderByComparator)
		throws com.ted.audit.db.exception.NoSuchAuditEntryException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first audit entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit entry, or <code>null</code> if a matching audit entry could not be found
	*/
	public static AuditEntry fetchByGroupId_First(long groupId,
		OrderByComparator<AuditEntry> orderByComparator) {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last audit entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit entry
	* @throws NoSuchAuditEntryException if a matching audit entry could not be found
	*/
	public static AuditEntry findByGroupId_Last(long groupId,
		OrderByComparator<AuditEntry> orderByComparator)
		throws com.ted.audit.db.exception.NoSuchAuditEntryException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last audit entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit entry, or <code>null</code> if a matching audit entry could not be found
	*/
	public static AuditEntry fetchByGroupId_Last(long groupId,
		OrderByComparator<AuditEntry> orderByComparator) {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the audit entries before and after the current audit entry in the ordered set where groupId = &#63;.
	*
	* @param auditId the primary key of the current audit entry
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next audit entry
	* @throws NoSuchAuditEntryException if a audit entry with the primary key could not be found
	*/
	public static AuditEntry[] findByGroupId_PrevAndNext(long auditId,
		long groupId, OrderByComparator<AuditEntry> orderByComparator)
		throws com.ted.audit.db.exception.NoSuchAuditEntryException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(auditId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the audit entries where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of audit entries where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching audit entries
	*/
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Returns all the audit entries where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching audit entries
	*/
	public static List<AuditEntry> findByCompanyId(long companyId) {
		return getPersistence().findByCompanyId(companyId);
	}

	/**
	* Returns a range of all the audit entries where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of audit entries
	* @param end the upper bound of the range of audit entries (not inclusive)
	* @return the range of matching audit entries
	*/
	public static List<AuditEntry> findByCompanyId(long companyId, int start,
		int end) {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

	/**
	* Returns an ordered range of all the audit entries where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of audit entries
	* @param end the upper bound of the range of audit entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching audit entries
	*/
	public static List<AuditEntry> findByCompanyId(long companyId, int start,
		int end, OrderByComparator<AuditEntry> orderByComparator) {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the audit entries where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of audit entries
	* @param end the upper bound of the range of audit entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching audit entries
	*/
	public static List<AuditEntry> findByCompanyId(long companyId, int start,
		int end, OrderByComparator<AuditEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first audit entry in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit entry
	* @throws NoSuchAuditEntryException if a matching audit entry could not be found
	*/
	public static AuditEntry findByCompanyId_First(long companyId,
		OrderByComparator<AuditEntry> orderByComparator)
		throws com.ted.audit.db.exception.NoSuchAuditEntryException {
		return getPersistence()
				   .findByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the first audit entry in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit entry, or <code>null</code> if a matching audit entry could not be found
	*/
	public static AuditEntry fetchByCompanyId_First(long companyId,
		OrderByComparator<AuditEntry> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the last audit entry in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit entry
	* @throws NoSuchAuditEntryException if a matching audit entry could not be found
	*/
	public static AuditEntry findByCompanyId_Last(long companyId,
		OrderByComparator<AuditEntry> orderByComparator)
		throws com.ted.audit.db.exception.NoSuchAuditEntryException {
		return getPersistence()
				   .findByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the last audit entry in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit entry, or <code>null</code> if a matching audit entry could not be found
	*/
	public static AuditEntry fetchByCompanyId_Last(long companyId,
		OrderByComparator<AuditEntry> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the audit entries before and after the current audit entry in the ordered set where companyId = &#63;.
	*
	* @param auditId the primary key of the current audit entry
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next audit entry
	* @throws NoSuchAuditEntryException if a audit entry with the primary key could not be found
	*/
	public static AuditEntry[] findByCompanyId_PrevAndNext(long auditId,
		long companyId, OrderByComparator<AuditEntry> orderByComparator)
		throws com.ted.audit.db.exception.NoSuchAuditEntryException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(auditId, companyId,
			orderByComparator);
	}

	/**
	* Removes all the audit entries where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	*/
	public static void removeByCompanyId(long companyId) {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	* Returns the number of audit entries where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching audit entries
	*/
	public static int countByCompanyId(long companyId) {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	* Returns all the audit entries where companyId = &#63; and actionId = &#63;.
	*
	* @param companyId the company ID
	* @param actionId the action ID
	* @return the matching audit entries
	*/
	public static List<AuditEntry> findByCompanyIdActionId(long companyId,
		int actionId) {
		return getPersistence().findByCompanyIdActionId(companyId, actionId);
	}

	/**
	* Returns a range of all the audit entries where companyId = &#63; and actionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param actionId the action ID
	* @param start the lower bound of the range of audit entries
	* @param end the upper bound of the range of audit entries (not inclusive)
	* @return the range of matching audit entries
	*/
	public static List<AuditEntry> findByCompanyIdActionId(long companyId,
		int actionId, int start, int end) {
		return getPersistence()
				   .findByCompanyIdActionId(companyId, actionId, start, end);
	}

	/**
	* Returns an ordered range of all the audit entries where companyId = &#63; and actionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param actionId the action ID
	* @param start the lower bound of the range of audit entries
	* @param end the upper bound of the range of audit entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching audit entries
	*/
	public static List<AuditEntry> findByCompanyIdActionId(long companyId,
		int actionId, int start, int end,
		OrderByComparator<AuditEntry> orderByComparator) {
		return getPersistence()
				   .findByCompanyIdActionId(companyId, actionId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the audit entries where companyId = &#63; and actionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param actionId the action ID
	* @param start the lower bound of the range of audit entries
	* @param end the upper bound of the range of audit entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching audit entries
	*/
	public static List<AuditEntry> findByCompanyIdActionId(long companyId,
		int actionId, int start, int end,
		OrderByComparator<AuditEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCompanyIdActionId(companyId, actionId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first audit entry in the ordered set where companyId = &#63; and actionId = &#63;.
	*
	* @param companyId the company ID
	* @param actionId the action ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit entry
	* @throws NoSuchAuditEntryException if a matching audit entry could not be found
	*/
	public static AuditEntry findByCompanyIdActionId_First(long companyId,
		int actionId, OrderByComparator<AuditEntry> orderByComparator)
		throws com.ted.audit.db.exception.NoSuchAuditEntryException {
		return getPersistence()
				   .findByCompanyIdActionId_First(companyId, actionId,
			orderByComparator);
	}

	/**
	* Returns the first audit entry in the ordered set where companyId = &#63; and actionId = &#63;.
	*
	* @param companyId the company ID
	* @param actionId the action ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit entry, or <code>null</code> if a matching audit entry could not be found
	*/
	public static AuditEntry fetchByCompanyIdActionId_First(long companyId,
		int actionId, OrderByComparator<AuditEntry> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyIdActionId_First(companyId, actionId,
			orderByComparator);
	}

	/**
	* Returns the last audit entry in the ordered set where companyId = &#63; and actionId = &#63;.
	*
	* @param companyId the company ID
	* @param actionId the action ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit entry
	* @throws NoSuchAuditEntryException if a matching audit entry could not be found
	*/
	public static AuditEntry findByCompanyIdActionId_Last(long companyId,
		int actionId, OrderByComparator<AuditEntry> orderByComparator)
		throws com.ted.audit.db.exception.NoSuchAuditEntryException {
		return getPersistence()
				   .findByCompanyIdActionId_Last(companyId, actionId,
			orderByComparator);
	}

	/**
	* Returns the last audit entry in the ordered set where companyId = &#63; and actionId = &#63;.
	*
	* @param companyId the company ID
	* @param actionId the action ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit entry, or <code>null</code> if a matching audit entry could not be found
	*/
	public static AuditEntry fetchByCompanyIdActionId_Last(long companyId,
		int actionId, OrderByComparator<AuditEntry> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyIdActionId_Last(companyId, actionId,
			orderByComparator);
	}

	/**
	* Returns the audit entries before and after the current audit entry in the ordered set where companyId = &#63; and actionId = &#63;.
	*
	* @param auditId the primary key of the current audit entry
	* @param companyId the company ID
	* @param actionId the action ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next audit entry
	* @throws NoSuchAuditEntryException if a audit entry with the primary key could not be found
	*/
	public static AuditEntry[] findByCompanyIdActionId_PrevAndNext(
		long auditId, long companyId, int actionId,
		OrderByComparator<AuditEntry> orderByComparator)
		throws com.ted.audit.db.exception.NoSuchAuditEntryException {
		return getPersistence()
				   .findByCompanyIdActionId_PrevAndNext(auditId, companyId,
			actionId, orderByComparator);
	}

	/**
	* Removes all the audit entries where companyId = &#63; and actionId = &#63; from the database.
	*
	* @param companyId the company ID
	* @param actionId the action ID
	*/
	public static void removeByCompanyIdActionId(long companyId, int actionId) {
		getPersistence().removeByCompanyIdActionId(companyId, actionId);
	}

	/**
	* Returns the number of audit entries where companyId = &#63; and actionId = &#63;.
	*
	* @param companyId the company ID
	* @param actionId the action ID
	* @return the number of matching audit entries
	*/
	public static int countByCompanyIdActionId(long companyId, int actionId) {
		return getPersistence().countByCompanyIdActionId(companyId, actionId);
	}

	/**
	* Returns all the audit entries where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching audit entries
	*/
	public static List<AuditEntry> findByUserId(long userId) {
		return getPersistence().findByUserId(userId);
	}

	/**
	* Returns a range of all the audit entries where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of audit entries
	* @param end the upper bound of the range of audit entries (not inclusive)
	* @return the range of matching audit entries
	*/
	public static List<AuditEntry> findByUserId(long userId, int start, int end) {
		return getPersistence().findByUserId(userId, start, end);
	}

	/**
	* Returns an ordered range of all the audit entries where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of audit entries
	* @param end the upper bound of the range of audit entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching audit entries
	*/
	public static List<AuditEntry> findByUserId(long userId, int start,
		int end, OrderByComparator<AuditEntry> orderByComparator) {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the audit entries where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of audit entries
	* @param end the upper bound of the range of audit entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching audit entries
	*/
	public static List<AuditEntry> findByUserId(long userId, int start,
		int end, OrderByComparator<AuditEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first audit entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit entry
	* @throws NoSuchAuditEntryException if a matching audit entry could not be found
	*/
	public static AuditEntry findByUserId_First(long userId,
		OrderByComparator<AuditEntry> orderByComparator)
		throws com.ted.audit.db.exception.NoSuchAuditEntryException {
		return getPersistence().findByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the first audit entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit entry, or <code>null</code> if a matching audit entry could not be found
	*/
	public static AuditEntry fetchByUserId_First(long userId,
		OrderByComparator<AuditEntry> orderByComparator) {
		return getPersistence().fetchByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the last audit entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit entry
	* @throws NoSuchAuditEntryException if a matching audit entry could not be found
	*/
	public static AuditEntry findByUserId_Last(long userId,
		OrderByComparator<AuditEntry> orderByComparator)
		throws com.ted.audit.db.exception.NoSuchAuditEntryException {
		return getPersistence().findByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the last audit entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit entry, or <code>null</code> if a matching audit entry could not be found
	*/
	public static AuditEntry fetchByUserId_Last(long userId,
		OrderByComparator<AuditEntry> orderByComparator) {
		return getPersistence().fetchByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the audit entries before and after the current audit entry in the ordered set where userId = &#63;.
	*
	* @param auditId the primary key of the current audit entry
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next audit entry
	* @throws NoSuchAuditEntryException if a audit entry with the primary key could not be found
	*/
	public static AuditEntry[] findByUserId_PrevAndNext(long auditId,
		long userId, OrderByComparator<AuditEntry> orderByComparator)
		throws com.ted.audit.db.exception.NoSuchAuditEntryException {
		return getPersistence()
				   .findByUserId_PrevAndNext(auditId, userId, orderByComparator);
	}

	/**
	* Removes all the audit entries where userId = &#63; from the database.
	*
	* @param userId the user ID
	*/
	public static void removeByUserId(long userId) {
		getPersistence().removeByUserId(userId);
	}

	/**
	* Returns the number of audit entries where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching audit entries
	*/
	public static int countByUserId(long userId) {
		return getPersistence().countByUserId(userId);
	}

	/**
	* Returns all the audit entries where classNameId = &#63;.
	*
	* @param classNameId the class name ID
	* @return the matching audit entries
	*/
	public static List<AuditEntry> findByClassNameId(long classNameId) {
		return getPersistence().findByClassNameId(classNameId);
	}

	/**
	* Returns a range of all the audit entries where classNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param start the lower bound of the range of audit entries
	* @param end the upper bound of the range of audit entries (not inclusive)
	* @return the range of matching audit entries
	*/
	public static List<AuditEntry> findByClassNameId(long classNameId,
		int start, int end) {
		return getPersistence().findByClassNameId(classNameId, start, end);
	}

	/**
	* Returns an ordered range of all the audit entries where classNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param start the lower bound of the range of audit entries
	* @param end the upper bound of the range of audit entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching audit entries
	*/
	public static List<AuditEntry> findByClassNameId(long classNameId,
		int start, int end, OrderByComparator<AuditEntry> orderByComparator) {
		return getPersistence()
				   .findByClassNameId(classNameId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the audit entries where classNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param start the lower bound of the range of audit entries
	* @param end the upper bound of the range of audit entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching audit entries
	*/
	public static List<AuditEntry> findByClassNameId(long classNameId,
		int start, int end, OrderByComparator<AuditEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByClassNameId(classNameId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first audit entry in the ordered set where classNameId = &#63;.
	*
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit entry
	* @throws NoSuchAuditEntryException if a matching audit entry could not be found
	*/
	public static AuditEntry findByClassNameId_First(long classNameId,
		OrderByComparator<AuditEntry> orderByComparator)
		throws com.ted.audit.db.exception.NoSuchAuditEntryException {
		return getPersistence()
				   .findByClassNameId_First(classNameId, orderByComparator);
	}

	/**
	* Returns the first audit entry in the ordered set where classNameId = &#63;.
	*
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit entry, or <code>null</code> if a matching audit entry could not be found
	*/
	public static AuditEntry fetchByClassNameId_First(long classNameId,
		OrderByComparator<AuditEntry> orderByComparator) {
		return getPersistence()
				   .fetchByClassNameId_First(classNameId, orderByComparator);
	}

	/**
	* Returns the last audit entry in the ordered set where classNameId = &#63;.
	*
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit entry
	* @throws NoSuchAuditEntryException if a matching audit entry could not be found
	*/
	public static AuditEntry findByClassNameId_Last(long classNameId,
		OrderByComparator<AuditEntry> orderByComparator)
		throws com.ted.audit.db.exception.NoSuchAuditEntryException {
		return getPersistence()
				   .findByClassNameId_Last(classNameId, orderByComparator);
	}

	/**
	* Returns the last audit entry in the ordered set where classNameId = &#63;.
	*
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit entry, or <code>null</code> if a matching audit entry could not be found
	*/
	public static AuditEntry fetchByClassNameId_Last(long classNameId,
		OrderByComparator<AuditEntry> orderByComparator) {
		return getPersistence()
				   .fetchByClassNameId_Last(classNameId, orderByComparator);
	}

	/**
	* Returns the audit entries before and after the current audit entry in the ordered set where classNameId = &#63;.
	*
	* @param auditId the primary key of the current audit entry
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next audit entry
	* @throws NoSuchAuditEntryException if a audit entry with the primary key could not be found
	*/
	public static AuditEntry[] findByClassNameId_PrevAndNext(long auditId,
		long classNameId, OrderByComparator<AuditEntry> orderByComparator)
		throws com.ted.audit.db.exception.NoSuchAuditEntryException {
		return getPersistence()
				   .findByClassNameId_PrevAndNext(auditId, classNameId,
			orderByComparator);
	}

	/**
	* Removes all the audit entries where classNameId = &#63; from the database.
	*
	* @param classNameId the class name ID
	*/
	public static void removeByClassNameId(long classNameId) {
		getPersistence().removeByClassNameId(classNameId);
	}

	/**
	* Returns the number of audit entries where classNameId = &#63;.
	*
	* @param classNameId the class name ID
	* @return the number of matching audit entries
	*/
	public static int countByClassNameId(long classNameId) {
		return getPersistence().countByClassNameId(classNameId);
	}

	/**
	* Caches the audit entry in the entity cache if it is enabled.
	*
	* @param auditEntry the audit entry
	*/
	public static void cacheResult(AuditEntry auditEntry) {
		getPersistence().cacheResult(auditEntry);
	}

	/**
	* Caches the audit entries in the entity cache if it is enabled.
	*
	* @param auditEntries the audit entries
	*/
	public static void cacheResult(List<AuditEntry> auditEntries) {
		getPersistence().cacheResult(auditEntries);
	}

	/**
	* Creates a new audit entry with the primary key. Does not add the audit entry to the database.
	*
	* @param auditId the primary key for the new audit entry
	* @return the new audit entry
	*/
	public static AuditEntry create(long auditId) {
		return getPersistence().create(auditId);
	}

	/**
	* Removes the audit entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param auditId the primary key of the audit entry
	* @return the audit entry that was removed
	* @throws NoSuchAuditEntryException if a audit entry with the primary key could not be found
	*/
	public static AuditEntry remove(long auditId)
		throws com.ted.audit.db.exception.NoSuchAuditEntryException {
		return getPersistence().remove(auditId);
	}

	public static AuditEntry updateImpl(AuditEntry auditEntry) {
		return getPersistence().updateImpl(auditEntry);
	}

	/**
	* Returns the audit entry with the primary key or throws a {@link NoSuchAuditEntryException} if it could not be found.
	*
	* @param auditId the primary key of the audit entry
	* @return the audit entry
	* @throws NoSuchAuditEntryException if a audit entry with the primary key could not be found
	*/
	public static AuditEntry findByPrimaryKey(long auditId)
		throws com.ted.audit.db.exception.NoSuchAuditEntryException {
		return getPersistence().findByPrimaryKey(auditId);
	}

	/**
	* Returns the audit entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param auditId the primary key of the audit entry
	* @return the audit entry, or <code>null</code> if a audit entry with the primary key could not be found
	*/
	public static AuditEntry fetchByPrimaryKey(long auditId) {
		return getPersistence().fetchByPrimaryKey(auditId);
	}

	public static java.util.Map<java.io.Serializable, AuditEntry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the audit entries.
	*
	* @return the audit entries
	*/
	public static List<AuditEntry> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the audit entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of audit entries
	* @param end the upper bound of the range of audit entries (not inclusive)
	* @return the range of audit entries
	*/
	public static List<AuditEntry> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the audit entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of audit entries
	* @param end the upper bound of the range of audit entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of audit entries
	*/
	public static List<AuditEntry> findAll(int start, int end,
		OrderByComparator<AuditEntry> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the audit entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of audit entries
	* @param end the upper bound of the range of audit entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of audit entries
	*/
	public static List<AuditEntry> findAll(int start, int end,
		OrderByComparator<AuditEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the audit entries from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of audit entries.
	*
	* @return the number of audit entries
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static AuditEntryPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<AuditEntryPersistence, AuditEntryPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(AuditEntryPersistence.class);

		ServiceTracker<AuditEntryPersistence, AuditEntryPersistence> serviceTracker =
			new ServiceTracker<AuditEntryPersistence, AuditEntryPersistence>(bundle.getBundleContext(),
				AuditEntryPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}