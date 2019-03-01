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

package com.ted.lms.learning.activity.p2p.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link P2PActivityLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see P2PActivityLocalService
 * @generated
 */
@ProviderType
public class P2PActivityLocalServiceWrapper implements P2PActivityLocalService,
	ServiceWrapper<P2PActivityLocalService> {
	public P2PActivityLocalServiceWrapper(
		P2PActivityLocalService p2pActivityLocalService) {
		_p2pActivityLocalService = p2pActivityLocalService;
	}

	@Override
	public com.ted.lms.learning.activity.p2p.model.P2PActivity addP2PActivity(
		long userId, long actId, String description,
		com.liferay.portal.kernel.service.ServiceContext serviceContext,
		com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _p2pActivityLocalService.addP2PActivity(userId, actId,
			description, serviceContext, themeDisplay);
	}

	@Override
	public com.ted.lms.learning.activity.p2p.model.P2PActivity addP2PActivity(
		long userId, long actId, String description, String fileName,
		java.io.File file, String mimeType,
		com.liferay.portal.kernel.service.ServiceContext serviceContext,
		com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException,
			java.io.IOException {
		return _p2pActivityLocalService.addP2PActivity(userId, actId,
			description, fileName, file, mimeType, serviceContext, themeDisplay);
	}

	/**
	* Adds the p2p activity to the database. Also notifies the appropriate model listeners.
	*
	* @param p2pActivity the p2p activity
	* @return the p2p activity that was added
	*/
	@Override
	public com.ted.lms.learning.activity.p2p.model.P2PActivity addP2PActivity(
		com.ted.lms.learning.activity.p2p.model.P2PActivity p2pActivity) {
		return _p2pActivityLocalService.addP2PActivity(p2pActivity);
	}

	@Override
	public com.liferay.document.library.kernel.model.DLFileEntry addP2PFileEntry(
		String fileName, java.io.File file, String mimeType, long folderId,
		long groupId, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			java.io.IOException {
		return _p2pActivityLocalService.addP2PFileEntry(fileName, file,
			mimeType, folderId, groupId, companyId);
	}

	@Override
	public com.liferay.portal.kernel.repository.model.Folder addP2PFolder(
		long userId, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _p2pActivityLocalService.addP2PFolder(userId, groupId);
	}

	@Override
	public void asignCorrectionP2PActivity(
		com.ted.lms.learning.activity.p2p.model.P2PActivity p2pActivity,
		int numValidations, String assignationType) {
		_p2pActivityLocalService.asignCorrectionP2PActivity(p2pActivity,
			numValidations, assignationType);
	}

	/**
	* Creates a new p2p activity with the primary key. Does not add the p2p activity to the database.
	*
	* @param p2pActivityId the primary key for the new p2p activity
	* @return the new p2p activity
	*/
	@Override
	public com.ted.lms.learning.activity.p2p.model.P2PActivity createP2PActivity(
		long p2pActivityId) {
		return _p2pActivityLocalService.createP2PActivity(p2pActivityId);
	}

	/**
	* Deletes the p2p activity with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param p2pActivityId the primary key of the p2p activity
	* @return the p2p activity that was removed
	* @throws PortalException if a p2p activity with the primary key could not be found
	*/
	@Override
	public com.ted.lms.learning.activity.p2p.model.P2PActivity deleteP2PActivity(
		long p2pActivityId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _p2pActivityLocalService.deleteP2PActivity(p2pActivityId);
	}

	/**
	* Deletes the p2p activity from the database. Also notifies the appropriate model listeners.
	*
	* @param p2pActivity the p2p activity
	* @return the p2p activity that was removed
	*/
	@Override
	public com.ted.lms.learning.activity.p2p.model.P2PActivity deleteP2PActivity(
		com.ted.lms.learning.activity.p2p.model.P2PActivity p2pActivity) {
		return _p2pActivityLocalService.deleteP2PActivity(p2pActivity);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _p2pActivityLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _p2pActivityLocalService.dynamicQuery();
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
		return _p2pActivityLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ted.lms.learning.activity.p2p.model.impl.P2PActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _p2pActivityLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ted.lms.learning.activity.p2p.model.impl.P2PActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _p2pActivityLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
		return _p2pActivityLocalService.dynamicQueryCount(dynamicQuery);
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
		return _p2pActivityLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.ted.lms.learning.activity.p2p.model.P2PActivity fetchP2PActivity(
		long p2pActivityId) {
		return _p2pActivityLocalService.fetchP2PActivity(p2pActivityId);
	}

	/**
	* Returns the p2p activity matching the UUID and group.
	*
	* @param uuid the p2p activity's UUID
	* @param groupId the primary key of the group
	* @return the matching p2p activity, or <code>null</code> if a matching p2p activity could not be found
	*/
	@Override
	public com.ted.lms.learning.activity.p2p.model.P2PActivity fetchP2PActivityByUuidAndGroupId(
		String uuid, long groupId) {
		return _p2pActivityLocalService.fetchP2PActivityByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _p2pActivityLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _p2pActivityLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _p2pActivityLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _p2pActivityLocalService.getOSGiServiceIdentifier();
	}

	/**
	* Returns a range of all the p2p activities.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ted.lms.learning.activity.p2p.model.impl.P2PActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of p2p activities
	* @param end the upper bound of the range of p2p activities (not inclusive)
	* @return the range of p2p activities
	*/
	@Override
	public java.util.List<com.ted.lms.learning.activity.p2p.model.P2PActivity> getP2PActivities(
		int start, int end) {
		return _p2pActivityLocalService.getP2PActivities(start, end);
	}

	@Override
	public java.util.List<com.ted.lms.learning.activity.p2p.model.P2PActivity> getP2PActivitiesByAssignationsCompleted(
		boolean assignationsCompleted) {
		return _p2pActivityLocalService.getP2PActivitiesByAssignationsCompleted(assignationsCompleted);
	}

	/**
	* Returns all the p2p activities matching the UUID and company.
	*
	* @param uuid the UUID of the p2p activities
	* @param companyId the primary key of the company
	* @return the matching p2p activities, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.ted.lms.learning.activity.p2p.model.P2PActivity> getP2PActivitiesByUuidAndCompanyId(
		String uuid, long companyId) {
		return _p2pActivityLocalService.getP2PActivitiesByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of p2p activities matching the UUID and company.
	*
	* @param uuid the UUID of the p2p activities
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of p2p activities
	* @param end the upper bound of the range of p2p activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching p2p activities, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.ted.lms.learning.activity.p2p.model.P2PActivity> getP2PActivitiesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.ted.lms.learning.activity.p2p.model.P2PActivity> orderByComparator) {
		return _p2pActivityLocalService.getP2PActivitiesByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of p2p activities.
	*
	* @return the number of p2p activities
	*/
	@Override
	public int getP2PActivitiesCount() {
		return _p2pActivityLocalService.getP2PActivitiesCount();
	}

	@Override
	public java.util.List<com.ted.lms.learning.activity.p2p.model.P2PActivity> getP2PActivitiesToCorrect(
		long actId, long p2pActivityId, int numValidaciones,
		String assignationType)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _p2pActivityLocalService.getP2PActivitiesToCorrect(actId,
			p2pActivityId, numValidaciones, assignationType);
	}

	/**
	* Returns the p2p activity with the primary key.
	*
	* @param p2pActivityId the primary key of the p2p activity
	* @return the p2p activity
	* @throws PortalException if a p2p activity with the primary key could not be found
	*/
	@Override
	public com.ted.lms.learning.activity.p2p.model.P2PActivity getP2PActivity(
		long p2pActivityId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _p2pActivityLocalService.getP2PActivity(p2pActivityId);
	}

	@Override
	public com.ted.lms.learning.activity.p2p.model.P2PActivity getP2PActivity(
		long actId, long userId) {
		return _p2pActivityLocalService.getP2PActivity(actId, userId);
	}

	/**
	* Returns the p2p activity matching the UUID and group.
	*
	* @param uuid the p2p activity's UUID
	* @param groupId the primary key of the group
	* @return the matching p2p activity
	* @throws PortalException if a matching p2p activity could not be found
	*/
	@Override
	public com.ted.lms.learning.activity.p2p.model.P2PActivity getP2PActivityByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _p2pActivityLocalService.getP2PActivityByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _p2pActivityLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public boolean hasP2PActivity(long actId) {
		return _p2pActivityLocalService.hasP2PActivity(actId);
	}

	@Override
	public boolean hasP2PActivity(long actId, long userId) {
		return _p2pActivityLocalService.hasP2PActivity(actId, userId);
	}

	/**
	* Updates the p2p activity in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param p2pActivity the p2p activity
	* @return the p2p activity that was updated
	*/
	@Override
	public com.ted.lms.learning.activity.p2p.model.P2PActivity updateP2PActivity(
		com.ted.lms.learning.activity.p2p.model.P2PActivity p2pActivity) {
		return _p2pActivityLocalService.updateP2PActivity(p2pActivity);
	}

	/**
	* Se actualizan los tries y los results de los usuarios implicados
	*
	* @param p2pActivityId
	* @param userId
	* @throws PortalException
	* @throws UnsupportedEncodingException
	* @throws Exception
	*/
	@Override
	public void updateResultP2PActivity(
		com.ted.lms.learning.activity.p2p.model.P2PActivity p2pActivityCorrected,
		com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections p2pActivityCorrection,
		long userId, int numValidations, boolean result, boolean anonimous,
		boolean emailAnonimous,
		com.liferay.portal.kernel.json.JSONArray evaluationCriteria,
		com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			java.io.UnsupportedEncodingException {
		_p2pActivityLocalService.updateResultP2PActivity(p2pActivityCorrected,
			p2pActivityCorrection, userId, numValidations, result, anonimous,
			emailAnonimous, evaluationCriteria, themeDisplay, serviceContext);
	}

	@Override
	public P2PActivityLocalService getWrappedService() {
		return _p2pActivityLocalService;
	}

	@Override
	public void setWrappedService(
		P2PActivityLocalService p2pActivityLocalService) {
		_p2pActivityLocalService = p2pActivityLocalService;
	}

	private P2PActivityLocalService _p2pActivityLocalService;
}