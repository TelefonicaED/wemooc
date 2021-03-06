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

import com.liferay.portal.kernel.service.ServiceWrapper;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides a wrapper for {@link P2PActivityCorrectionsLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see P2PActivityCorrectionsLocalService
 * @generated
 */
@ProviderType
public class P2PActivityCorrectionsLocalServiceWrapper
	implements P2PActivityCorrectionsLocalService,
			   ServiceWrapper<P2PActivityCorrectionsLocalService> {

	public P2PActivityCorrectionsLocalServiceWrapper(
		P2PActivityCorrectionsLocalService p2pActivityCorrectionsLocalService) {

		_p2pActivityCorrectionsLocalService =
			p2pActivityCorrectionsLocalService;
	}

	@Override
	public com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections
		addP2PActivityCorrections(
			long actId, long userId, long p2pActivityId, long groupId,
			long companyId, long userCreatedId) {

		return _p2pActivityCorrectionsLocalService.addP2PActivityCorrections(
			actId, userId, p2pActivityId, groupId, companyId, userCreatedId);
	}

	/**
	 * Adds the p2p activity corrections to the database. Also notifies the appropriate model listeners.
	 *
	 * @param p2pActivityCorrections the p2p activity corrections
	 * @return the p2p activity corrections that was added
	 */
	@Override
	public com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections
		addP2PActivityCorrections(
			com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections
				p2pActivityCorrections) {

		return _p2pActivityCorrectionsLocalService.addP2PActivityCorrections(
			p2pActivityCorrections);
	}

	@Override
	public boolean areAllCorrectionsDoneByUserInP2PActivity(
		long actId, long userId, int numValidations) {

		return _p2pActivityCorrectionsLocalService.
			areAllCorrectionsDoneByUserInP2PActivity(
				actId, userId, numValidations);
	}

	@Override
	public void asignCorrectionsToP2PActivities(
			long actId, long p2pActivityId, int numValidaciones,
			java.util.List<com.ted.lms.learning.activity.p2p.model.P2PActivity>
				activityList,
			long userId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_p2pActivityCorrectionsLocalService.asignCorrectionsToP2PActivities(
			actId, p2pActivityId, numValidaciones, activityList, userId);
	}

	/**
	 * Creates a new p2p activity corrections with the primary key. Does not add the p2p activity corrections to the database.
	 *
	 * @param p2pActivityCorrectionsId the primary key for the new p2p activity corrections
	 * @return the new p2p activity corrections
	 */
	@Override
	public com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections
		createP2PActivityCorrections(long p2pActivityCorrectionsId) {

		return _p2pActivityCorrectionsLocalService.createP2PActivityCorrections(
			p2pActivityCorrectionsId);
	}

	/**
	 * Deletes the p2p activity corrections with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param p2pActivityCorrectionsId the primary key of the p2p activity corrections
	 * @return the p2p activity corrections that was removed
	 * @throws PortalException if a p2p activity corrections with the primary key could not be found
	 */
	@Override
	public com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections
			deleteP2PActivityCorrections(long p2pActivityCorrectionsId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _p2pActivityCorrectionsLocalService.deleteP2PActivityCorrections(
			p2pActivityCorrectionsId);
	}

	/**
	 * Deletes the p2p activity corrections from the database. Also notifies the appropriate model listeners.
	 *
	 * @param p2pActivityCorrections the p2p activity corrections
	 * @return the p2p activity corrections that was removed
	 */
	@Override
	public com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections
		deleteP2PActivityCorrections(
			com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections
				p2pActivityCorrections) {

		return _p2pActivityCorrectionsLocalService.deleteP2PActivityCorrections(
			p2pActivityCorrections);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _p2pActivityCorrectionsLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _p2pActivityCorrectionsLocalService.dynamicQuery();
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

		return _p2pActivityCorrectionsLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.ted.lms.learning.activity.p2p.model.impl.P2PActivityCorrectionsModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _p2pActivityCorrectionsLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.ted.lms.learning.activity.p2p.model.impl.P2PActivityCorrectionsModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _p2pActivityCorrectionsLocalService.dynamicQuery(
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

		return _p2pActivityCorrectionsLocalService.dynamicQueryCount(
			dynamicQuery);
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

		return _p2pActivityCorrectionsLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections
		fetchP2PActivityCorrections(long p2pActivityCorrectionsId) {

		return _p2pActivityCorrectionsLocalService.fetchP2PActivityCorrections(
			p2pActivityCorrectionsId);
	}

	/**
	 * Returns the p2p activity corrections matching the UUID and group.
	 *
	 * @param uuid the p2p activity corrections's UUID
	 * @param groupId the primary key of the group
	 * @return the matching p2p activity corrections, or <code>null</code> if a matching p2p activity corrections could not be found
	 */
	@Override
	public com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections
		fetchP2PActivityCorrectionsByUuidAndGroupId(String uuid, long groupId) {

		return _p2pActivityCorrectionsLocalService.
			fetchP2PActivityCorrectionsByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _p2pActivityCorrectionsLocalService.getActionableDynamicQuery();
	}

	/**
	 * A partir del id de una actividad, obtenemos la media de resultados que ha obtenido en ellas.
	 * En las correcciones que se han realizado y tiene fecha de realizaci�n.
	 *
	 * @param p2pActivityId
	 * @return la media de results
	 */
	@Override
	public long getAVGCorrectionsResults(long p2pActivityId) {
		return _p2pActivityCorrectionsLocalService.getAVGCorrectionsResults(
			p2pActivityId);
	}

	@Override
	public java.util.List
		<com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections>
			getCorrections(long actId, long userId) {

		return _p2pActivityCorrectionsLocalService.getCorrections(
			actId, userId);
	}

	@Override
	public com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections
		getCorrectionsByP2PActivityIdUserId(long p2pActivityId, long userId) {

		return _p2pActivityCorrectionsLocalService.
			getCorrectionsByP2PActivityIdUserId(p2pActivityId, userId);
	}

	@Override
	public int getCorrectionsCount(long actId, long userId) {
		return _p2pActivityCorrectionsLocalService.getCorrectionsCount(
			actId, userId);
	}

	@Override
	public java.util.List
		<com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections>
			getCorrectionsDoneByP2PActivityId(long p2pActivityId) {

		return _p2pActivityCorrectionsLocalService.
			getCorrectionsDoneByP2PActivityId(p2pActivityId);
	}

	@Override
	public java.util.List
		<com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections>
			getCorrectionsDoneByUser(long actId, long userId) {

		return _p2pActivityCorrectionsLocalService.getCorrectionsDoneByUser(
			actId, userId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _p2pActivityCorrectionsLocalService.
			getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _p2pActivityCorrectionsLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _p2pActivityCorrectionsLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * Returns the p2p activity corrections with the primary key.
	 *
	 * @param p2pActivityCorrectionsId the primary key of the p2p activity corrections
	 * @return the p2p activity corrections
	 * @throws PortalException if a p2p activity corrections with the primary key could not be found
	 */
	@Override
	public com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections
			getP2PActivityCorrections(long p2pActivityCorrectionsId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _p2pActivityCorrectionsLocalService.getP2PActivityCorrections(
			p2pActivityCorrectionsId);
	}

	@Override
	public java.util.List
		<com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections>
			getP2PActivityCorrectionsByP2PActivityId(long p2pActivityId) {

		return _p2pActivityCorrectionsLocalService.
			getP2PActivityCorrectionsByP2PActivityId(p2pActivityId);
	}

	/**
	 * Returns the p2p activity corrections matching the UUID and group.
	 *
	 * @param uuid the p2p activity corrections's UUID
	 * @param groupId the primary key of the group
	 * @return the matching p2p activity corrections
	 * @throws PortalException if a matching p2p activity corrections could not be found
	 */
	@Override
	public com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections
			getP2PActivityCorrectionsByUuidAndGroupId(String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _p2pActivityCorrectionsLocalService.
			getP2PActivityCorrectionsByUuidAndGroupId(uuid, groupId);
	}

	/**
	 * Returns a range of all the p2p activity correctionses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.ted.lms.learning.activity.p2p.model.impl.P2PActivityCorrectionsModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of p2p activity correctionses
	 * @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	 * @return the range of p2p activity correctionses
	 */
	@Override
	public java.util.List
		<com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections>
			getP2PActivityCorrectionses(int start, int end) {

		return _p2pActivityCorrectionsLocalService.getP2PActivityCorrectionses(
			start, end);
	}

	/**
	 * Returns all the p2p activity correctionses matching the UUID and company.
	 *
	 * @param uuid the UUID of the p2p activity correctionses
	 * @param companyId the primary key of the company
	 * @return the matching p2p activity correctionses, or an empty list if no matches were found
	 */
	@Override
	public java.util.List
		<com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections>
			getP2PActivityCorrectionsesByUuidAndCompanyId(
				String uuid, long companyId) {

		return _p2pActivityCorrectionsLocalService.
			getP2PActivityCorrectionsesByUuidAndCompanyId(uuid, companyId);
	}

	/**
	 * Returns a range of p2p activity correctionses matching the UUID and company.
	 *
	 * @param uuid the UUID of the p2p activity correctionses
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of p2p activity correctionses
	 * @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching p2p activity correctionses, or an empty list if no matches were found
	 */
	@Override
	public java.util.List
		<com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections>
			getP2PActivityCorrectionsesByUuidAndCompanyId(
				String uuid, long companyId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.ted.lms.learning.activity.p2p.model.
						P2PActivityCorrections> orderByComparator) {

		return _p2pActivityCorrectionsLocalService.
			getP2PActivityCorrectionsesByUuidAndCompanyId(
				uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of p2p activity correctionses.
	 *
	 * @return the number of p2p activity correctionses
	 */
	@Override
	public int getP2PActivityCorrectionsesCount() {
		return _p2pActivityCorrectionsLocalService.
			getP2PActivityCorrectionsesCount();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _p2pActivityCorrectionsLocalService.getPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Para saber si al usuario le han realizado todas las correcciones que se indica en el extracontent.
	 */
	@Override
	public boolean hasAllCorrectionsDoneAboutUserInP2PActivity(
		long actId, long p2pActivityId, int numValidations) {

		return _p2pActivityCorrectionsLocalService.
			hasAllCorrectionsDoneAboutUserInP2PActivity(
				actId, p2pActivityId, numValidations);
	}

	/**
	 * Updates the p2p activity corrections in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param p2pActivityCorrections the p2p activity corrections
	 * @return the p2p activity corrections that was updated
	 */
	@Override
	public com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections
		updateP2PActivityCorrections(
			com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections
				p2pActivityCorrections) {

		return _p2pActivityCorrectionsLocalService.updateP2PActivityCorrections(
			p2pActivityCorrections);
	}

	@Override
	public com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections
			updateP2PActivityCorrections(
				com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections
					p2pActivityCorrections,
				String description, String fileName, java.io.File file,
				String mimeType, long result,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			   java.io.IOException {

		return _p2pActivityCorrectionsLocalService.updateP2PActivityCorrections(
			p2pActivityCorrections, description, fileName, file, mimeType,
			result, serviceContext);
	}

	@Override
	public P2PActivityCorrectionsLocalService getWrappedService() {
		return _p2pActivityCorrectionsLocalService;
	}

	@Override
	public void setWrappedService(
		P2PActivityCorrectionsLocalService p2pActivityCorrectionsLocalService) {

		_p2pActivityCorrectionsLocalService =
			p2pActivityCorrectionsLocalService;
	}

	private P2PActivityCorrectionsLocalService
		_p2pActivityCorrectionsLocalService;

}