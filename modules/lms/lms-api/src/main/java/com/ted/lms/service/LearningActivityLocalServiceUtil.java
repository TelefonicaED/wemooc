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
 * Provides the local service utility for LearningActivity. This utility wraps
 * {@link com.ted.lms.service.impl.LearningActivityLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see LearningActivityLocalService
 * @see com.ted.lms.service.base.LearningActivityLocalServiceBaseImpl
 * @see com.ted.lms.service.impl.LearningActivityLocalServiceImpl
 * @generated
 */
@ProviderType
public class LearningActivityLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.ted.lms.service.impl.LearningActivityLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.kernel.repository.model.FileEntry addAttachment(
		long userId, com.ted.lms.model.LearningActivity activity,
		String fileName, java.io.InputStream inputStream, String mimeType)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addAttachment(userId, activity, fileName, inputStream,
			mimeType);
	}

	/**
	* Adds the learning activity to the database. Also notifies the appropriate model listeners.
	*
	* @param learningActivity the learning activity
	* @return the learning activity that was added
	*/
	public static com.ted.lms.model.LearningActivity addLearningActivity(
		com.ted.lms.model.LearningActivity learningActivity) {
		return getService().addLearningActivity(learningActivity);
	}

	public static com.ted.lms.model.LearningActivity addLearningActivity(
		long moduleId, long type,
		java.util.Map<java.util.Locale, String> titleMap,
		java.util.Map<java.util.Locale, String> descriptionMap,
		boolean useStartExecutionDateCourse, int startDateMonth,
		int startDateDay, int startDateYear, int startDateHour,
		int startDateMinute, boolean useEndExecutionDateCourse,
		int endDateMonth, int endDateDay, int endDateYear, int endDateHour,
		int endDateMinute, boolean required, int tries, double passPuntuation,
		java.util.Map<java.util.Locale, String> feedbackCorrectMap,
		java.util.Map<java.util.Locale, String> feedbackNoCorrectMap,
		boolean commentsActivated, String[] selectedFileNames,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addLearningActivity(moduleId, type, titleMap,
			descriptionMap, useStartExecutionDateCourse, startDateMonth,
			startDateDay, startDateYear, startDateHour, startDateMinute,
			useEndExecutionDateCourse, endDateMonth, endDateDay, endDateYear,
			endDateHour, endDateMinute, required, tries, passPuntuation,
			feedbackCorrectMap, feedbackNoCorrectMap, commentsActivated,
			selectedFileNames, serviceContext);
	}

	public static com.ted.lms.model.LearningActivity addLearningActivity(
		long moduleId, long type,
		java.util.Map<java.util.Locale, String> titleMap,
		java.util.Map<java.util.Locale, String> descriptionMap,
		java.util.Date startDate, java.util.Date endDate, boolean required,
		int tries, double passPuntuation,
		java.util.Map<java.util.Locale, String> feedbackCorrectMap,
		java.util.Map<java.util.Locale, String> feedbackNoCorrectMap,
		boolean commentsActivated, String[] selectedFileNames,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addLearningActivity(moduleId, type, titleMap,
			descriptionMap, startDate, endDate, required, tries,
			passPuntuation, feedbackCorrectMap, feedbackNoCorrectMap,
			commentsActivated, selectedFileNames, serviceContext);
	}

	public static com.ted.lms.model.LearningActivity addLearningActivity(
		long moduleId, java.util.Map<java.util.Locale, String> titleMap,
		java.util.Map<java.util.Locale, String> descriptionMap, long typeId,
		java.util.Date startDate, java.util.Date endDate, int tries,
		double passPuntuation, long priority, String extraContent,
		java.util.Map<java.util.Locale, String> feedbackCorrectMap,
		java.util.Map<java.util.Locale, String> feedbackNoCorrectMap,
		boolean required, boolean commentsActivated,
		String[] selectedFileNames,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addLearningActivity(moduleId, titleMap, descriptionMap,
			typeId, startDate, endDate, tries, passPuntuation, priority,
			extraContent, feedbackCorrectMap, feedbackNoCorrectMap, required,
			commentsActivated, selectedFileNames, serviceContext);
	}

	/**
	* Creates a new learning activity with the primary key. Does not add the learning activity to the database.
	*
	* @param actId the primary key for the new learning activity
	* @return the new learning activity
	*/
	public static com.ted.lms.model.LearningActivity createLearningActivity(
		long actId) {
		return getService().createLearningActivity(actId);
	}

	public static void deleteLearningActivities(long moduleId) {
		getService().deleteLearningActivities(moduleId);
	}

	/**
	* Deletes the learning activity from the database. Also notifies the appropriate model listeners.
	*
	* @param learningActivity the learning activity
	* @return the learning activity that was removed
	*/
	public static com.ted.lms.model.LearningActivity deleteLearningActivity(
		com.ted.lms.model.LearningActivity learningActivity) {
		return getService().deleteLearningActivity(learningActivity);
	}

	/**
	* Deletes the learning activity with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param actId the primary key of the learning activity
	* @return the learning activity that was removed
	* @throws PortalException if a learning activity with the primary key could not be found
	*/
	public static com.ted.lms.model.LearningActivity deleteLearningActivity(
		long actId) throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteLearningActivity(actId);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ted.lms.model.impl.LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ted.lms.model.impl.LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.ted.lms.model.LearningActivity fetchLearningActivity(
		long actId) {
		return getService().fetchLearningActivity(actId);
	}

	/**
	* Returns the learning activity matching the UUID and group.
	*
	* @param uuid the learning activity's UUID
	* @param groupId the primary key of the group
	* @return the matching learning activity, or <code>null</code> if a matching learning activity could not be found
	*/
	public static com.ted.lms.model.LearningActivity fetchLearningActivityByUuidAndGroupId(
		String uuid, long groupId) {
		return getService().fetchLearningActivityByUuidAndGroupId(uuid, groupId);
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

	/**
	* Returns a range of all the learning activities.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ted.lms.model.impl.LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @return the range of learning activities
	*/
	public static java.util.List<com.ted.lms.model.LearningActivity> getLearningActivities(
		int start, int end) {
		return getService().getLearningActivities(start, end);
	}

	public static java.util.List<com.ted.lms.model.LearningActivity> getLearningActivities(
		long moduleId) {
		return getService().getLearningActivities(moduleId);
	}

	public static java.util.List<com.ted.lms.model.LearningActivity> getLearningActivitiesByTypeId(
		long typeId) {
		return getService().getLearningActivitiesByTypeId(typeId);
	}

	/**
	* Returns all the learning activities matching the UUID and company.
	*
	* @param uuid the UUID of the learning activities
	* @param companyId the primary key of the company
	* @return the matching learning activities, or an empty list if no matches were found
	*/
	public static java.util.List<com.ted.lms.model.LearningActivity> getLearningActivitiesByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService()
				   .getLearningActivitiesByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of learning activities matching the UUID and company.
	*
	* @param uuid the UUID of the learning activities
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching learning activities, or an empty list if no matches were found
	*/
	public static java.util.List<com.ted.lms.model.LearningActivity> getLearningActivitiesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.ted.lms.model.LearningActivity> orderByComparator) {
		return getService()
				   .getLearningActivitiesByUuidAndCompanyId(uuid, companyId,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of learning activities.
	*
	* @return the number of learning activities
	*/
	public static int getLearningActivitiesCount() {
		return getService().getLearningActivitiesCount();
	}

	public static int getLearningActivitiesOfModuleCount(long moduleId) {
		return getService().getLearningActivitiesOfModuleCount(moduleId);
	}

	/**
	* Returns the learning activity with the primary key.
	*
	* @param actId the primary key of the learning activity
	* @return the learning activity
	* @throws PortalException if a learning activity with the primary key could not be found
	*/
	public static com.ted.lms.model.LearningActivity getLearningActivity(
		long actId) throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLearningActivity(actId);
	}

	/**
	* Returns the learning activity matching the UUID and group.
	*
	* @param uuid the learning activity's UUID
	* @param groupId the primary key of the group
	* @return the matching learning activity
	* @throws PortalException if a matching learning activity could not be found
	*/
	public static com.ted.lms.model.LearningActivity getLearningActivityByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLearningActivityByUuidAndGroupId(uuid, groupId);
	}

	public static com.ted.lms.model.LearningActivity getNextLearningActivity(
		com.ted.lms.model.LearningActivity activity)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getNextLearningActivity(activity);
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

	public static com.ted.lms.model.LearningActivity getPreviousLearningActivity(
		com.ted.lms.model.LearningActivity activity)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPreviousLearningActivity(activity);
	}

	public static java.util.List<com.ted.lms.model.LearningActivity> getRequiredLearningActivitiesOfGroup(
		long groupId) {
		return getService().getRequiredLearningActivitiesOfGroup(groupId);
	}

	public static java.util.List<com.ted.lms.model.LearningActivity> getRequiredLearningActivitiesOfModule(
		long moduleId) {
		return getService().getRequiredLearningActivitiesOfModule(moduleId);
	}

	public static com.ted.lms.model.LearningActivity moveDownLearningActivity(
		long actId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().moveDownLearningActivity(actId, serviceContext);
	}

	public static com.ted.lms.model.LearningActivity moveLearningActivityToTrash(
		long userId, com.ted.lms.model.LearningActivity activity)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().moveLearningActivityToTrash(userId, activity);
	}

	public static com.ted.lms.model.LearningActivity moveLearningActivityToTrash(
		long userId, long actId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().moveLearningActivityToTrash(userId, actId);
	}

	public static com.ted.lms.model.LearningActivity moveUpLearningActivity(
		long actId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().moveUpLearningActivity(actId, serviceContext);
	}

	public static void updateAsset(long userId,
		com.ted.lms.model.LearningActivity activity, long[] assetCategoryIds,
		String[] assetTagNames, long[] assetLinkEntryIds, Double priority)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.updateAsset(userId, activity, assetCategoryIds, assetTagNames,
			assetLinkEntryIds, priority);
	}

	/**
	* Updates the learning activity in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param learningActivity the learning activity
	* @return the learning activity that was updated
	*/
	public static com.ted.lms.model.LearningActivity updateLearningActivity(
		com.ted.lms.model.LearningActivity learningActivity) {
		return getService().updateLearningActivity(learningActivity);
	}

	public static com.ted.lms.model.LearningActivity updateLearningActivity(
		long actId, long moduleId,
		java.util.Map<java.util.Locale, String> titleMap,
		java.util.Map<java.util.Locale, String> descriptionMap, long typeId,
		java.util.Date startDate, java.util.Date endDate, int tries,
		double passPuntuation, long priority, String extraContent,
		java.util.Map<java.util.Locale, String> feedbackCorrectMap,
		java.util.Map<java.util.Locale, String> feedbackNoCorrectMap,
		boolean required, boolean commentsActivated,
		String[] selectedFileNames, long[] removeFileEntryIds,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateLearningActivity(actId, moduleId, titleMap,
			descriptionMap, typeId, startDate, endDate, tries, passPuntuation,
			priority, extraContent, feedbackCorrectMap, feedbackNoCorrectMap,
			required, commentsActivated, selectedFileNames, removeFileEntryIds,
			serviceContext);
	}

	public static com.ted.lms.model.LearningActivity updateLearningActivity(
		long actId, java.util.Map<java.util.Locale, String> titleMap,
		java.util.Map<java.util.Locale, String> descriptionMap,
		boolean useStartExecutionDateCourse, int startDateMonth,
		int startDateDay, int startDateYear, int startDateHour,
		int startDateMinute, boolean useEndExecutionDateCourse,
		int endDateMonth, int endDateDay, int endDateYear, int endDateHour,
		int endDateMinute, boolean required, int tries, double passPuntuation,
		java.util.Map<java.util.Locale, String> feedbackCorrectMap,
		java.util.Map<java.util.Locale, String> feedbackNoCorrectMap,
		boolean commentsActivated, String[] selectedFileNames,
		long[] removeFileEntryIds,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateLearningActivity(actId, titleMap, descriptionMap,
			useStartExecutionDateCourse, startDateMonth, startDateDay,
			startDateYear, startDateHour, startDateMinute,
			useEndExecutionDateCourse, endDateMonth, endDateDay, endDateYear,
			endDateHour, endDateMinute, required, tries, passPuntuation,
			feedbackCorrectMap, feedbackNoCorrectMap, commentsActivated,
			selectedFileNames, removeFileEntryIds, serviceContext);
	}

	public static com.ted.lms.model.LearningActivity updateLearningActivity(
		long actId, java.util.Map<java.util.Locale, String> titleMap,
		java.util.Map<java.util.Locale, String> descriptionMap,
		java.util.Date startDate, java.util.Date endDate, int tries,
		double passPuntuation,
		java.util.Map<java.util.Locale, String> feedbackCorrectMap,
		java.util.Map<java.util.Locale, String> feedbackNoCorrectMap,
		boolean required, boolean commentsActivated,
		String[] selectedFileNames, long[] removeFileEntryIds,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateLearningActivity(actId, titleMap, descriptionMap,
			startDate, endDate, tries, passPuntuation, feedbackCorrectMap,
			feedbackNoCorrectMap, required, commentsActivated,
			selectedFileNames, removeFileEntryIds, serviceContext);
	}

	public static com.ted.lms.model.LearningActivity updateStatus(long userId,
		long actId, int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext,
		java.util.Map<String, java.io.Serializable> workflowContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateStatus(userId, actId, status, serviceContext,
			workflowContext);
	}

	public static LearningActivityLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LearningActivityLocalService, LearningActivityLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(LearningActivityLocalService.class);

		ServiceTracker<LearningActivityLocalService, LearningActivityLocalService> serviceTracker =
			new ServiceTracker<LearningActivityLocalService, LearningActivityLocalService>(bundle.getBundleContext(),
				LearningActivityLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}