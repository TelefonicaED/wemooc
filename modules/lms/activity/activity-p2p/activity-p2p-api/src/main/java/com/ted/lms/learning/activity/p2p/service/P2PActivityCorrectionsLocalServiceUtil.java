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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for P2PActivityCorrections. This utility wraps
 * {@link com.ted.lms.learning.activity.p2p.service.impl.P2PActivityCorrectionsLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see P2PActivityCorrectionsLocalService
 * @see com.ted.lms.learning.activity.p2p.service.base.P2PActivityCorrectionsLocalServiceBaseImpl
 * @see com.ted.lms.learning.activity.p2p.service.impl.P2PActivityCorrectionsLocalServiceImpl
 * @generated
 */
@ProviderType
public class P2PActivityCorrectionsLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.ted.lms.learning.activity.p2p.service.impl.P2PActivityCorrectionsLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections addP2PActivityCorrections(
		long actId, long userId, long p2pActivityId, long groupId,
		long companyId, long userCreatedId) {
		return getService()
				   .addP2PActivityCorrections(actId, userId, p2pActivityId,
			groupId, companyId, userCreatedId);
	}

	/**
	* Adds the p2p activity corrections to the database. Also notifies the appropriate model listeners.
	*
	* @param p2pActivityCorrections the p2p activity corrections
	* @return the p2p activity corrections that was added
	*/
	public static com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections addP2PActivityCorrections(
		com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections p2pActivityCorrections) {
		return getService().addP2PActivityCorrections(p2pActivityCorrections);
	}

	public static boolean areAllCorrectionsDoneByUserInP2PActivity(long actId,
		long userId, int numValidations) {
		return getService()
				   .areAllCorrectionsDoneByUserInP2PActivity(actId, userId,
			numValidations);
	}

	public static void asignCorrectionsToP2PActivities(long actId,
		long p2pActivityId, int numValidaciones,
		java.util.List<com.ted.lms.learning.activity.p2p.model.P2PActivity> activityList,
		long userId) throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.asignCorrectionsToP2PActivities(actId, p2pActivityId,
			numValidaciones, activityList, userId);
	}

	/**
	* Creates a new p2p activity corrections with the primary key. Does not add the p2p activity corrections to the database.
	*
	* @param p2pActivityCorrectionsId the primary key for the new p2p activity corrections
	* @return the new p2p activity corrections
	*/
	public static com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections createP2PActivityCorrections(
		long p2pActivityCorrectionsId) {
		return getService()
				   .createP2PActivityCorrections(p2pActivityCorrectionsId);
	}

	/**
	* Deletes the p2p activity corrections with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param p2pActivityCorrectionsId the primary key of the p2p activity corrections
	* @return the p2p activity corrections that was removed
	* @throws PortalException if a p2p activity corrections with the primary key could not be found
	*/
	public static com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections deleteP2PActivityCorrections(
		long p2pActivityCorrectionsId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .deleteP2PActivityCorrections(p2pActivityCorrectionsId);
	}

	/**
	* Deletes the p2p activity corrections from the database. Also notifies the appropriate model listeners.
	*
	* @param p2pActivityCorrections the p2p activity corrections
	* @return the p2p activity corrections that was removed
	*/
	public static com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections deleteP2PActivityCorrections(
		com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections p2pActivityCorrections) {
		return getService().deleteP2PActivityCorrections(p2pActivityCorrections);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ted.lms.learning.activity.p2p.model.impl.P2PActivityCorrectionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ted.lms.learning.activity.p2p.model.impl.P2PActivityCorrectionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections fetchP2PActivityCorrections(
		long p2pActivityCorrectionsId) {
		return getService().fetchP2PActivityCorrections(p2pActivityCorrectionsId);
	}

	/**
	* Returns the p2p activity corrections matching the UUID and group.
	*
	* @param uuid the p2p activity corrections's UUID
	* @param groupId the primary key of the group
	* @return the matching p2p activity corrections, or <code>null</code> if a matching p2p activity corrections could not be found
	*/
	public static com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections fetchP2PActivityCorrectionsByUuidAndGroupId(
		String uuid, long groupId) {
		return getService()
				   .fetchP2PActivityCorrectionsByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	/**
	* A partir del id de una actividad, obtenemos la media de resultados que ha obtenido en ellas.
	* En las correcciones que se han realizado y tiene fecha de realizaci�n.
	*
	* @param p2pActivityId
	* @return la media de results
	*/
	public static long getAVGCorrectionsResults(long p2pActivityId) {
		return getService().getAVGCorrectionsResults(p2pActivityId);
	}

	public static java.util.List<com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections> getCorrections(
		long actId, long userId) {
		return getService().getCorrections(actId, userId);
	}

	public static com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections getCorrectionsByP2PActivityIdUserId(
		long p2pActivityId, long userId) {
		return getService()
				   .getCorrectionsByP2PActivityIdUserId(p2pActivityId, userId);
	}

	public static int getCorrectionsCount(long actId, long userId) {
		return getService().getCorrectionsCount(actId, userId);
	}

	public static java.util.List<com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections> getCorrectionsDoneByP2PActivityId(
		long p2pActivityId) {
		return getService().getCorrectionsDoneByP2PActivityId(p2pActivityId);
	}

	public static java.util.List<com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections> getCorrectionsDoneByUser(
		long actId, long userId) {
		return getService().getCorrectionsDoneByUser(actId, userId);
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
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

	/**
	* Returns the p2p activity corrections with the primary key.
	*
	* @param p2pActivityCorrectionsId the primary key of the p2p activity corrections
	* @return the p2p activity corrections
	* @throws PortalException if a p2p activity corrections with the primary key could not be found
	*/
	public static com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections getP2PActivityCorrections(
		long p2pActivityCorrectionsId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getP2PActivityCorrections(p2pActivityCorrectionsId);
	}

	public static java.util.List<com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections> getP2PActivityCorrectionsByP2PActivityId(
		long p2pActivityId) {
		return getService()
				   .getP2PActivityCorrectionsByP2PActivityId(p2pActivityId);
	}

	/**
	* Returns the p2p activity corrections matching the UUID and group.
	*
	* @param uuid the p2p activity corrections's UUID
	* @param groupId the primary key of the group
	* @return the matching p2p activity corrections
	* @throws PortalException if a matching p2p activity corrections could not be found
	*/
	public static com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections getP2PActivityCorrectionsByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getP2PActivityCorrectionsByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the p2p activity correctionses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ted.lms.learning.activity.p2p.model.impl.P2PActivityCorrectionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of p2p activity correctionses
	* @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	* @return the range of p2p activity correctionses
	*/
	public static java.util.List<com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections> getP2PActivityCorrectionses(
		int start, int end) {
		return getService().getP2PActivityCorrectionses(start, end);
	}

	/**
	* Returns all the p2p activity correctionses matching the UUID and company.
	*
	* @param uuid the UUID of the p2p activity correctionses
	* @param companyId the primary key of the company
	* @return the matching p2p activity correctionses, or an empty list if no matches were found
	*/
	public static java.util.List<com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections> getP2PActivityCorrectionsesByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService()
				   .getP2PActivityCorrectionsesByUuidAndCompanyId(uuid,
			companyId);
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
	public static java.util.List<com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections> getP2PActivityCorrectionsesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections> orderByComparator) {
		return getService()
				   .getP2PActivityCorrectionsesByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of p2p activity correctionses.
	*
	* @return the number of p2p activity correctionses
	*/
	public static int getP2PActivityCorrectionsesCount() {
		return getService().getP2PActivityCorrectionsesCount();
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Para saber si al usuario le han realizado todas las correcciones que se indica en el extracontent.
	*/
	public static boolean hasAllCorrectionsDoneAboutUserInP2PActivity(
		long actId, long p2pActivityId, int numValidations) {
		return getService()
				   .hasAllCorrectionsDoneAboutUserInP2PActivity(actId,
			p2pActivityId, numValidations);
	}

	/**
	* Updates the p2p activity corrections in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param p2pActivityCorrections the p2p activity corrections
	* @return the p2p activity corrections that was updated
	*/
	public static com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections updateP2PActivityCorrections(
		com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections p2pActivityCorrections) {
		return getService().updateP2PActivityCorrections(p2pActivityCorrections);
	}

	public static com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections updateP2PActivityCorrections(
		com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections p2pActivityCorrections,
		String description, String fileName, java.io.File file,
		String mimeType, long result,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			java.io.IOException {
		return getService()
				   .updateP2PActivityCorrections(p2pActivityCorrections,
			description, fileName, file, mimeType, result, serviceContext);
	}

	public static P2PActivityCorrectionsLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<P2PActivityCorrectionsLocalService, P2PActivityCorrectionsLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(P2PActivityCorrectionsLocalService.class);

		ServiceTracker<P2PActivityCorrectionsLocalService, P2PActivityCorrectionsLocalService> serviceTracker =
			new ServiceTracker<P2PActivityCorrectionsLocalService, P2PActivityCorrectionsLocalService>(bundle.getBundleContext(),
				P2PActivityCorrectionsLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}