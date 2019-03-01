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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.ted.audit.db.exception.NoSuchitEntryException;
import com.ted.audit.db.model.AuditEntry;

/**
 * The persistence interface for the audit entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ted.audit.db.service.persistence.impl.AuditEntryPersistenceImpl
 * @see AuditEntryUtil
 * @generated
 */
@ProviderType
public interface AuditEntryPersistence extends BasePersistence<AuditEntry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AuditEntryUtil} to access the audit entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the audit entries where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching audit entries
	*/
	public java.util.List<AuditEntry> findByGroupId(long groupId);

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
	public java.util.List<AuditEntry> findByGroupId(long groupId, int start,
		int end);

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
	public java.util.List<AuditEntry> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator);

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
	public java.util.List<AuditEntry> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first audit entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit entry
	* @throws NoSuchitEntryException if a matching audit entry could not be found
	*/
	public AuditEntry findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator)
		throws NoSuchitEntryException;

	/**
	* Returns the first audit entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit entry, or <code>null</code> if a matching audit entry could not be found
	*/
	public AuditEntry fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator);

	/**
	* Returns the last audit entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit entry
	* @throws NoSuchitEntryException if a matching audit entry could not be found
	*/
	public AuditEntry findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator)
		throws NoSuchitEntryException;

	/**
	* Returns the last audit entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit entry, or <code>null</code> if a matching audit entry could not be found
	*/
	public AuditEntry fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator);

	/**
	* Returns the audit entries before and after the current audit entry in the ordered set where groupId = &#63;.
	*
	* @param auditId the primary key of the current audit entry
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next audit entry
	* @throws NoSuchitEntryException if a audit entry with the primary key could not be found
	*/
	public AuditEntry[] findByGroupId_PrevAndNext(long auditId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator)
		throws NoSuchitEntryException;

	/**
	* Removes all the audit entries where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of audit entries where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching audit entries
	*/
	public int countByGroupId(long groupId);

	/**
	* Returns all the audit entries where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching audit entries
	*/
	public java.util.List<AuditEntry> findByCompanyId(long companyId);

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
	public java.util.List<AuditEntry> findByCompanyId(long companyId,
		int start, int end);

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
	public java.util.List<AuditEntry> findByCompanyId(long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator);

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
	public java.util.List<AuditEntry> findByCompanyId(long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first audit entry in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit entry
	* @throws NoSuchitEntryException if a matching audit entry could not be found
	*/
	public AuditEntry findByCompanyId_First(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator)
		throws NoSuchitEntryException;

	/**
	* Returns the first audit entry in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit entry, or <code>null</code> if a matching audit entry could not be found
	*/
	public AuditEntry fetchByCompanyId_First(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator);

	/**
	* Returns the last audit entry in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit entry
	* @throws NoSuchitEntryException if a matching audit entry could not be found
	*/
	public AuditEntry findByCompanyId_Last(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator)
		throws NoSuchitEntryException;

	/**
	* Returns the last audit entry in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit entry, or <code>null</code> if a matching audit entry could not be found
	*/
	public AuditEntry fetchByCompanyId_Last(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator);

	/**
	* Returns the audit entries before and after the current audit entry in the ordered set where companyId = &#63;.
	*
	* @param auditId the primary key of the current audit entry
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next audit entry
	* @throws NoSuchitEntryException if a audit entry with the primary key could not be found
	*/
	public AuditEntry[] findByCompanyId_PrevAndNext(long auditId,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator)
		throws NoSuchitEntryException;

	/**
	* Removes all the audit entries where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	*/
	public void removeByCompanyId(long companyId);

	/**
	* Returns the number of audit entries where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching audit entries
	*/
	public int countByCompanyId(long companyId);

	/**
	* Returns all the audit entries where companyId = &#63; and actionId = &#63;.
	*
	* @param companyId the company ID
	* @param actionId the action ID
	* @return the matching audit entries
	*/
	public java.util.List<AuditEntry> findByCompanyIdActionId(long companyId,
		int actionId);

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
	public java.util.List<AuditEntry> findByCompanyIdActionId(long companyId,
		int actionId, int start, int end);

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
	public java.util.List<AuditEntry> findByCompanyIdActionId(long companyId,
		int actionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator);

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
	public java.util.List<AuditEntry> findByCompanyIdActionId(long companyId,
		int actionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first audit entry in the ordered set where companyId = &#63; and actionId = &#63;.
	*
	* @param companyId the company ID
	* @param actionId the action ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit entry
	* @throws NoSuchitEntryException if a matching audit entry could not be found
	*/
	public AuditEntry findByCompanyIdActionId_First(long companyId,
		int actionId,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator)
		throws NoSuchitEntryException;

	/**
	* Returns the first audit entry in the ordered set where companyId = &#63; and actionId = &#63;.
	*
	* @param companyId the company ID
	* @param actionId the action ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit entry, or <code>null</code> if a matching audit entry could not be found
	*/
	public AuditEntry fetchByCompanyIdActionId_First(long companyId,
		int actionId,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator);

	/**
	* Returns the last audit entry in the ordered set where companyId = &#63; and actionId = &#63;.
	*
	* @param companyId the company ID
	* @param actionId the action ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit entry
	* @throws NoSuchitEntryException if a matching audit entry could not be found
	*/
	public AuditEntry findByCompanyIdActionId_Last(long companyId,
		int actionId,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator)
		throws NoSuchitEntryException;

	/**
	* Returns the last audit entry in the ordered set where companyId = &#63; and actionId = &#63;.
	*
	* @param companyId the company ID
	* @param actionId the action ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit entry, or <code>null</code> if a matching audit entry could not be found
	*/
	public AuditEntry fetchByCompanyIdActionId_Last(long companyId,
		int actionId,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator);

	/**
	* Returns the audit entries before and after the current audit entry in the ordered set where companyId = &#63; and actionId = &#63;.
	*
	* @param auditId the primary key of the current audit entry
	* @param companyId the company ID
	* @param actionId the action ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next audit entry
	* @throws NoSuchitEntryException if a audit entry with the primary key could not be found
	*/
	public AuditEntry[] findByCompanyIdActionId_PrevAndNext(long auditId,
		long companyId, int actionId,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator)
		throws NoSuchitEntryException;

	/**
	* Removes all the audit entries where companyId = &#63; and actionId = &#63; from the database.
	*
	* @param companyId the company ID
	* @param actionId the action ID
	*/
	public void removeByCompanyIdActionId(long companyId, int actionId);

	/**
	* Returns the number of audit entries where companyId = &#63; and actionId = &#63;.
	*
	* @param companyId the company ID
	* @param actionId the action ID
	* @return the number of matching audit entries
	*/
	public int countByCompanyIdActionId(long companyId, int actionId);

	/**
	* Returns all the audit entries where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching audit entries
	*/
	public java.util.List<AuditEntry> findByUserId(long userId);

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
	public java.util.List<AuditEntry> findByUserId(long userId, int start,
		int end);

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
	public java.util.List<AuditEntry> findByUserId(long userId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator);

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
	public java.util.List<AuditEntry> findByUserId(long userId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first audit entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit entry
	* @throws NoSuchitEntryException if a matching audit entry could not be found
	*/
	public AuditEntry findByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator)
		throws NoSuchitEntryException;

	/**
	* Returns the first audit entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit entry, or <code>null</code> if a matching audit entry could not be found
	*/
	public AuditEntry fetchByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator);

	/**
	* Returns the last audit entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit entry
	* @throws NoSuchitEntryException if a matching audit entry could not be found
	*/
	public AuditEntry findByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator)
		throws NoSuchitEntryException;

	/**
	* Returns the last audit entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit entry, or <code>null</code> if a matching audit entry could not be found
	*/
	public AuditEntry fetchByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator);

	/**
	* Returns the audit entries before and after the current audit entry in the ordered set where userId = &#63;.
	*
	* @param auditId the primary key of the current audit entry
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next audit entry
	* @throws NoSuchitEntryException if a audit entry with the primary key could not be found
	*/
	public AuditEntry[] findByUserId_PrevAndNext(long auditId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator)
		throws NoSuchitEntryException;

	/**
	* Removes all the audit entries where userId = &#63; from the database.
	*
	* @param userId the user ID
	*/
	public void removeByUserId(long userId);

	/**
	* Returns the number of audit entries where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching audit entries
	*/
	public int countByUserId(long userId);

	/**
	* Returns all the audit entries where classNameId = &#63;.
	*
	* @param classNameId the class name ID
	* @return the matching audit entries
	*/
	public java.util.List<AuditEntry> findByClassNameId(long classNameId);

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
	public java.util.List<AuditEntry> findByClassNameId(long classNameId,
		int start, int end);

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
	public java.util.List<AuditEntry> findByClassNameId(long classNameId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator);

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
	public java.util.List<AuditEntry> findByClassNameId(long classNameId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first audit entry in the ordered set where classNameId = &#63;.
	*
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit entry
	* @throws NoSuchitEntryException if a matching audit entry could not be found
	*/
	public AuditEntry findByClassNameId_First(long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator)
		throws NoSuchitEntryException;

	/**
	* Returns the first audit entry in the ordered set where classNameId = &#63;.
	*
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit entry, or <code>null</code> if a matching audit entry could not be found
	*/
	public AuditEntry fetchByClassNameId_First(long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator);

	/**
	* Returns the last audit entry in the ordered set where classNameId = &#63;.
	*
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit entry
	* @throws NoSuchitEntryException if a matching audit entry could not be found
	*/
	public AuditEntry findByClassNameId_Last(long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator)
		throws NoSuchitEntryException;

	/**
	* Returns the last audit entry in the ordered set where classNameId = &#63;.
	*
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit entry, or <code>null</code> if a matching audit entry could not be found
	*/
	public AuditEntry fetchByClassNameId_Last(long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator);

	/**
	* Returns the audit entries before and after the current audit entry in the ordered set where classNameId = &#63;.
	*
	* @param auditId the primary key of the current audit entry
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next audit entry
	* @throws NoSuchitEntryException if a audit entry with the primary key could not be found
	*/
	public AuditEntry[] findByClassNameId_PrevAndNext(long auditId,
		long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator)
		throws NoSuchitEntryException;

	/**
	* Removes all the audit entries where classNameId = &#63; from the database.
	*
	* @param classNameId the class name ID
	*/
	public void removeByClassNameId(long classNameId);

	/**
	* Returns the number of audit entries where classNameId = &#63;.
	*
	* @param classNameId the class name ID
	* @return the number of matching audit entries
	*/
	public int countByClassNameId(long classNameId);

	/**
	* Caches the audit entry in the entity cache if it is enabled.
	*
	* @param auditEntry the audit entry
	*/
	public void cacheResult(AuditEntry auditEntry);

	/**
	* Caches the audit entries in the entity cache if it is enabled.
	*
	* @param auditEntries the audit entries
	*/
	public void cacheResult(java.util.List<AuditEntry> auditEntries);

	/**
	* Creates a new audit entry with the primary key. Does not add the audit entry to the database.
	*
	* @param auditId the primary key for the new audit entry
	* @return the new audit entry
	*/
	public AuditEntry create(long auditId);

	/**
	* Removes the audit entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param auditId the primary key of the audit entry
	* @return the audit entry that was removed
	* @throws NoSuchitEntryException if a audit entry with the primary key could not be found
	*/
	public AuditEntry remove(long auditId) throws NoSuchitEntryException;

	public AuditEntry updateImpl(AuditEntry auditEntry);

	/**
	* Returns the audit entry with the primary key or throws a {@link NoSuchitEntryException} if it could not be found.
	*
	* @param auditId the primary key of the audit entry
	* @return the audit entry
	* @throws NoSuchitEntryException if a audit entry with the primary key could not be found
	*/
	public AuditEntry findByPrimaryKey(long auditId)
		throws NoSuchitEntryException;

	/**
	* Returns the audit entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param auditId the primary key of the audit entry
	* @return the audit entry, or <code>null</code> if a audit entry with the primary key could not be found
	*/
	public AuditEntry fetchByPrimaryKey(long auditId);

	@Override
	public java.util.Map<java.io.Serializable, AuditEntry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the audit entries.
	*
	* @return the audit entries
	*/
	public java.util.List<AuditEntry> findAll();

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
	public java.util.List<AuditEntry> findAll(int start, int end);

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
	public java.util.List<AuditEntry> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator);

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
	public java.util.List<AuditEntry> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the audit entries from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of audit entries.
	*
	* @return the number of audit entries
	*/
	public int countAll();
}