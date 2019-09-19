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

package com.ted.audit.db.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides a wrapper for {@link AuditEntryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see AuditEntryLocalService
 * @generated
 */
@ProviderType
public class AuditEntryLocalServiceWrapper
	implements AuditEntryLocalService, ServiceWrapper<AuditEntryLocalService> {

	public AuditEntryLocalServiceWrapper(
		AuditEntryLocalService auditEntryLocalService) {

		_auditEntryLocalService = auditEntryLocalService;
	}

	/**
	 * Adds the audit entry to the database. Also notifies the appropriate model listeners.
	 *
	 * @param auditEntry the audit entry
	 * @return the audit entry that was added
	 */
	@Override
	public com.ted.audit.db.model.AuditEntry addAuditEntry(
		com.ted.audit.db.model.AuditEntry auditEntry) {

		return _auditEntryLocalService.addAuditEntry(auditEntry);
	}

	@Override
	public void addAuditEntry(
		long companyId, long groupId, int actionId, String className,
		long classPK, long associationClassPK, long userId, String userName,
		String extraData) {

		_auditEntryLocalService.addAuditEntry(
			companyId, groupId, actionId, className, classPK,
			associationClassPK, userId, userName, extraData);
	}

	@Override
	public void addAuditEntry(
		long companyId, long groupId, int actionId, String className,
		long classPK, long userId, String userName, String extraData) {

		_auditEntryLocalService.addAuditEntry(
			companyId, groupId, actionId, className, classPK, userId, userName,
			extraData);
	}

	/**
	 * Creates a new audit entry with the primary key. Does not add the audit entry to the database.
	 *
	 * @param auditId the primary key for the new audit entry
	 * @return the new audit entry
	 */
	@Override
	public com.ted.audit.db.model.AuditEntry createAuditEntry(long auditId) {
		return _auditEntryLocalService.createAuditEntry(auditId);
	}

	/**
	 * Deletes the audit entry from the database. Also notifies the appropriate model listeners.
	 *
	 * @param auditEntry the audit entry
	 * @return the audit entry that was removed
	 */
	@Override
	public com.ted.audit.db.model.AuditEntry deleteAuditEntry(
		com.ted.audit.db.model.AuditEntry auditEntry) {

		return _auditEntryLocalService.deleteAuditEntry(auditEntry);
	}

	/**
	 * Deletes the audit entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param auditId the primary key of the audit entry
	 * @return the audit entry that was removed
	 * @throws PortalException if a audit entry with the primary key could not be found
	 */
	@Override
	public com.ted.audit.db.model.AuditEntry deleteAuditEntry(long auditId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _auditEntryLocalService.deleteAuditEntry(auditId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _auditEntryLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _auditEntryLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _auditEntryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.ted.audit.db.model.impl.AuditEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _auditEntryLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.ted.audit.db.model.impl.AuditEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _auditEntryLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _auditEntryLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _auditEntryLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.ted.audit.db.model.AuditEntry fetchAuditEntry(long auditId) {
		return _auditEntryLocalService.fetchAuditEntry(auditId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _auditEntryLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns a range of all the audit entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.ted.audit.db.model.impl.AuditEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of audit entries
	 * @param end the upper bound of the range of audit entries (not inclusive)
	 * @return the range of audit entries
	 */
	@Override
	public java.util.List<com.ted.audit.db.model.AuditEntry> getAuditEntries(
		int start, int end) {

		return _auditEntryLocalService.getAuditEntries(start, end);
	}

	/**
	 * Returns the number of audit entries.
	 *
	 * @return the number of audit entries
	 */
	@Override
	public int getAuditEntriesCount() {
		return _auditEntryLocalService.getAuditEntriesCount();
	}

	/**
	 * Returns the audit entry with the primary key.
	 *
	 * @param auditId the primary key of the audit entry
	 * @return the audit entry
	 * @throws PortalException if a audit entry with the primary key could not be found
	 */
	@Override
	public com.ted.audit.db.model.AuditEntry getAuditEntry(long auditId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _auditEntryLocalService.getAuditEntry(auditId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _auditEntryLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _auditEntryLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _auditEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the audit entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param auditEntry the audit entry
	 * @return the audit entry that was updated
	 */
	@Override
	public com.ted.audit.db.model.AuditEntry updateAuditEntry(
		com.ted.audit.db.model.AuditEntry auditEntry) {

		return _auditEntryLocalService.updateAuditEntry(auditEntry);
	}

	@Override
	public AuditEntryLocalService getWrappedService() {
		return _auditEntryLocalService;
	}

	@Override
	public void setWrappedService(
		AuditEntryLocalService auditEntryLocalService) {

		_auditEntryLocalService = auditEntryLocalService;
	}

	private AuditEntryLocalService _auditEntryLocalService;

}