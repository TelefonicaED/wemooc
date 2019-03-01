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

package com.ted.lms.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for LearningActivityTry. This utility wraps
 * {@link com.ted.lms.service.impl.LearningActivityTryLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see LearningActivityTryLocalService
 * @see com.ted.lms.service.base.LearningActivityTryLocalServiceBaseImpl
 * @see com.ted.lms.service.impl.LearningActivityTryLocalServiceImpl
 * @generated
 */
@ProviderType
public class LearningActivityTryLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.ted.lms.service.impl.LearningActivityTryLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the learning activity try to the database. Also notifies the appropriate model listeners.
	*
	* @param learningActivityTry the learning activity try
	* @return the learning activity try that was added
	*/
	public static com.ted.lms.model.LearningActivityTry addLearningActivityTry(
		com.ted.lms.model.LearningActivityTry learningActivityTry) {
		return getService().addLearningActivityTry(learningActivityTry);
	}

	public static com.ted.lms.model.LearningActivityTry addLearningActivityTry(
		long actId, long userId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().addLearningActivityTry(actId, userId, serviceContext);
	}

	/**
	* Creates a new learning activity try with the primary key. Does not add the learning activity try to the database.
	*
	* @param latId the primary key for the new learning activity try
	* @return the new learning activity try
	*/
	public static com.ted.lms.model.LearningActivityTry createLearningActivityTry(
		long latId) {
		return getService().createLearningActivityTry(latId);
	}

	/**
	* Deletes the learning activity try from the database. Also notifies the appropriate model listeners.
	*
	* @param learningActivityTry the learning activity try
	* @return the learning activity try that was removed
	*/
	public static com.ted.lms.model.LearningActivityTry deleteLearningActivityTry(
		com.ted.lms.model.LearningActivityTry learningActivityTry) {
		return getService().deleteLearningActivityTry(learningActivityTry);
	}

	/**
	* Deletes the learning activity try with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param latId the primary key of the learning activity try
	* @return the learning activity try that was removed
	* @throws PortalException if a learning activity try with the primary key could not be found
	*/
	public static com.ted.lms.model.LearningActivityTry deleteLearningActivityTry(
		long latId) throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteLearningActivityTry(latId);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ted.lms.model.impl.LearningActivityTryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ted.lms.model.impl.LearningActivityTryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.ted.lms.model.LearningActivityTry fetchLearningActivityTry(
		long latId) {
		return getService().fetchLearningActivityTry(latId);
	}

	/**
	* Returns the learning activity try matching the UUID and group.
	*
	* @param uuid the learning activity try's UUID
	* @param groupId the primary key of the group
	* @return the matching learning activity try, or <code>null</code> if a matching learning activity try could not be found
	*/
	public static com.ted.lms.model.LearningActivityTry fetchLearningActivityTryByUuidAndGroupId(
		String uuid, long groupId) {
		return getService()
				   .fetchLearningActivityTryByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	public static com.ted.lms.model.LearningActivityTry getLastLearningActivityTry(
		long actId, long userId) {
		return getService().getLastLearningActivityTry(actId, userId);
	}

	/**
	* Returns a range of all the learning activity tries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ted.lms.model.impl.LearningActivityTryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of learning activity tries
	* @param end the upper bound of the range of learning activity tries (not inclusive)
	* @return the range of learning activity tries
	*/
	public static java.util.List<com.ted.lms.model.LearningActivityTry> getLearningActivityTries(
		int start, int end) {
		return getService().getLearningActivityTries(start, end);
	}

	public static java.util.List<com.ted.lms.model.LearningActivityTry> getLearningActivityTries(
		long actId, long userId) {
		return getService().getLearningActivityTries(actId, userId);
	}

	/**
	* Returns all the learning activity tries matching the UUID and company.
	*
	* @param uuid the UUID of the learning activity tries
	* @param companyId the primary key of the company
	* @return the matching learning activity tries, or an empty list if no matches were found
	*/
	public static java.util.List<com.ted.lms.model.LearningActivityTry> getLearningActivityTriesByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService()
				   .getLearningActivityTriesByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of learning activity tries matching the UUID and company.
	*
	* @param uuid the UUID of the learning activity tries
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of learning activity tries
	* @param end the upper bound of the range of learning activity tries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching learning activity tries, or an empty list if no matches were found
	*/
	public static java.util.List<com.ted.lms.model.LearningActivityTry> getLearningActivityTriesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.ted.lms.model.LearningActivityTry> orderByComparator) {
		return getService()
				   .getLearningActivityTriesByUuidAndCompanyId(uuid, companyId,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of learning activity tries.
	*
	* @return the number of learning activity tries
	*/
	public static int getLearningActivityTriesCount() {
		return getService().getLearningActivityTriesCount();
	}

	public static int getLearningActivityTriesCount(long actId, long userId) {
		return getService().getLearningActivityTriesCount(actId, userId);
	}

	/**
	* Returns the learning activity try with the primary key.
	*
	* @param latId the primary key of the learning activity try
	* @return the learning activity try
	* @throws PortalException if a learning activity try with the primary key could not be found
	*/
	public static com.ted.lms.model.LearningActivityTry getLearningActivityTry(
		long latId) throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLearningActivityTry(latId);
	}

	/**
	* Returns the learning activity try matching the UUID and group.
	*
	* @param uuid the learning activity try's UUID
	* @param groupId the primary key of the group
	* @return the matching learning activity try
	* @throws PortalException if a matching learning activity try could not be found
	*/
	public static com.ted.lms.model.LearningActivityTry getLearningActivityTryByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLearningActivityTryByUuidAndGroupId(uuid, groupId);
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
	* Updates the learning activity try in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param learningActivityTry the learning activity try
	* @return the learning activity try that was updated
	*/
	public static com.ted.lms.model.LearningActivityTry updateLearningActivityTry(
		com.ted.lms.model.LearningActivityTry learningActivityTry) {
		return getService().updateLearningActivityTry(learningActivityTry);
	}

	public static LearningActivityTryLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LearningActivityTryLocalService, LearningActivityTryLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(LearningActivityTryLocalService.class);

		ServiceTracker<LearningActivityTryLocalService, LearningActivityTryLocalService> serviceTracker =
			new ServiceTracker<LearningActivityTryLocalService, LearningActivityTryLocalService>(bundle.getBundleContext(),
				LearningActivityTryLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}