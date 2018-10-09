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

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for AuditEntry. This utility wraps
 * {@link com.ted.audit.db.service.impl.AuditEntryLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see AuditEntryLocalService
 * @see com.ted.audit.db.service.base.AuditEntryLocalServiceBaseImpl
 * @see com.ted.audit.db.service.impl.AuditEntryLocalServiceImpl
 * @generated
 */
@ProviderType
public class AuditEntryLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.ted.audit.db.service.impl.AuditEntryLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the audit entry to the database. Also notifies the appropriate model listeners.
	*
	* @param auditEntry the audit entry
	* @return the audit entry that was added
	*/
	public static com.ted.audit.db.model.AuditEntry addAuditEntry(
		com.ted.audit.db.model.AuditEntry auditEntry) {
		return getService().addAuditEntry(auditEntry);
	}

	public static void addAuditEntry(long companyId, long groupId,
		int actionId, String className, long classPK, long associationClassPK,
		long userId, String userName, String extraData) {
		getService()
			.addAuditEntry(companyId, groupId, actionId, className, classPK,
			associationClassPK, userId, userName, extraData);
	}

	public static void addAuditEntry(long companyId, long groupId,
		int actionId, String className, long classPK, long userId,
		String userName, String extraData) {
		getService()
			.addAuditEntry(companyId, groupId, actionId, className, classPK,
			userId, userName, extraData);
	}

	/**
	* Creates a new audit entry with the primary key. Does not add the audit entry to the database.
	*
	* @param auditId the primary key for the new audit entry
	* @return the new audit entry
	*/
	public static com.ted.audit.db.model.AuditEntry createAuditEntry(
		long auditId) {
		return getService().createAuditEntry(auditId);
	}

	/**
	* Deletes the audit entry from the database. Also notifies the appropriate model listeners.
	*
	* @param auditEntry the audit entry
	* @return the audit entry that was removed
	*/
	public static com.ted.audit.db.model.AuditEntry deleteAuditEntry(
		com.ted.audit.db.model.AuditEntry auditEntry) {
		return getService().deleteAuditEntry(auditEntry);
	}

	/**
	* Deletes the audit entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param auditId the primary key of the audit entry
	* @return the audit entry that was removed
	* @throws PortalException if a audit entry with the primary key could not be found
	*/
	public static com.ted.audit.db.model.AuditEntry deleteAuditEntry(
		long auditId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteAuditEntry(auditId);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ted.audit.db.model.impl.AuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ted.audit.db.model.impl.AuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.ted.audit.db.model.AuditEntry fetchAuditEntry(
		long auditId) {
		return getService().fetchAuditEntry(auditId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	/**
	* Returns a range of all the audit entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ted.audit.db.model.impl.AuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of audit entries
	* @param end the upper bound of the range of audit entries (not inclusive)
	* @return the range of audit entries
	*/
	public static java.util.List<com.ted.audit.db.model.AuditEntry> getAuditEntries(
		int start, int end) {
		return getService().getAuditEntries(start, end);
	}

	/**
	* Returns the number of audit entries.
	*
	* @return the number of audit entries
	*/
	public static int getAuditEntriesCount() {
		return getService().getAuditEntriesCount();
	}

	/**
	* Returns the audit entry with the primary key.
	*
	* @param auditId the primary key of the audit entry
	* @return the audit entry
	* @throws PortalException if a audit entry with the primary key could not be found
	*/
	public static com.ted.audit.db.model.AuditEntry getAuditEntry(long auditId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getAuditEntry(auditId);
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the audit entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param auditEntry the audit entry
	* @return the audit entry that was updated
	*/
	public static com.ted.audit.db.model.AuditEntry updateAuditEntry(
		com.ted.audit.db.model.AuditEntry auditEntry) {
		return getService().updateAuditEntry(auditEntry);
	}

	public static AuditEntryLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<AuditEntryLocalService, AuditEntryLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(AuditEntryLocalService.class);

		ServiceTracker<AuditEntryLocalService, AuditEntryLocalService> serviceTracker =
			new ServiceTracker<AuditEntryLocalService, AuditEntryLocalService>(bundle.getBundleContext(),
				AuditEntryLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}