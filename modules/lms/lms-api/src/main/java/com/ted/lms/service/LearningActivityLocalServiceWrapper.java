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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link LearningActivityLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see LearningActivityLocalService
 * @generated
 */
@ProviderType
public class LearningActivityLocalServiceWrapper
	implements LearningActivityLocalService,
		ServiceWrapper<LearningActivityLocalService> {
	public LearningActivityLocalServiceWrapper(
		LearningActivityLocalService learningActivityLocalService) {
		_learningActivityLocalService = learningActivityLocalService;
	}

	/**
	* Adds the learning activity to the database. Also notifies the appropriate model listeners.
	*
	* @param learningActivity the learning activity
	* @return the learning activity that was added
	*/
	@Override
	public com.ted.lms.model.LearningActivity addLearningActivity(
		com.ted.lms.model.LearningActivity learningActivity) {
		return _learningActivityLocalService.addLearningActivity(learningActivity);
	}

	@Override
	public com.ted.lms.model.LearningActivity addLearningActivity(
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
		boolean commentsActivated,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _learningActivityLocalService.addLearningActivity(moduleId,
			type, titleMap, descriptionMap, useStartExecutionDateCourse,
			startDateMonth, startDateDay, startDateYear, startDateHour,
			startDateMinute, useEndExecutionDateCourse, endDateMonth,
			endDateDay, endDateYear, endDateHour, endDateMinute, required,
			tries, passPuntuation, feedbackCorrectMap, feedbackNoCorrectMap,
			commentsActivated, serviceContext);
	}

	@Override
	public com.ted.lms.model.LearningActivity addLearningActivity(
		long moduleId, long type,
		java.util.Map<java.util.Locale, String> titleMap,
		java.util.Map<java.util.Locale, String> descriptionMap,
		java.util.Date startDate, java.util.Date endDate, boolean required,
		int tries, double passPuntuation,
		java.util.Map<java.util.Locale, String> feedbackCorrectMap,
		java.util.Map<java.util.Locale, String> feedbackNoCorrectMap,
		boolean commentsActivated,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _learningActivityLocalService.addLearningActivity(moduleId,
			type, titleMap, descriptionMap, startDate, endDate, required,
			tries, passPuntuation, feedbackCorrectMap, feedbackNoCorrectMap,
			commentsActivated, serviceContext);
	}

	@Override
	public com.ted.lms.model.LearningActivity addLearningActivity(
		long moduleId, java.util.Map<java.util.Locale, String> titleMap,
		java.util.Map<java.util.Locale, String> descriptionMap, long typeId,
		java.util.Date startDate, java.util.Date endDate, int tries,
		double passPuntuation, long priority, String extraContent,
		java.util.Map<java.util.Locale, String> feedbackCorrectMap,
		java.util.Map<java.util.Locale, String> feedbackNoCorrectMap,
		boolean required, boolean commentsActivated,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _learningActivityLocalService.addLearningActivity(moduleId,
			titleMap, descriptionMap, typeId, startDate, endDate, tries,
			passPuntuation, priority, extraContent, feedbackCorrectMap,
			feedbackNoCorrectMap, required, commentsActivated, serviceContext);
	}

	/**
	* Creates a new learning activity with the primary key. Does not add the learning activity to the database.
	*
	* @param actId the primary key for the new learning activity
	* @return the new learning activity
	*/
	@Override
	public com.ted.lms.model.LearningActivity createLearningActivity(long actId) {
		return _learningActivityLocalService.createLearningActivity(actId);
	}

	@Override
	public void deleteLearningActivities(long moduleId) {
		_learningActivityLocalService.deleteLearningActivities(moduleId);
	}

	/**
	* Deletes the learning activity from the database. Also notifies the appropriate model listeners.
	*
	* @param learningActivity the learning activity
	* @return the learning activity that was removed
	*/
	@Override
	public com.ted.lms.model.LearningActivity deleteLearningActivity(
		com.ted.lms.model.LearningActivity learningActivity) {
		return _learningActivityLocalService.deleteLearningActivity(learningActivity);
	}

	/**
	* Deletes the learning activity with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param actId the primary key of the learning activity
	* @return the learning activity that was removed
	* @throws PortalException if a learning activity with the primary key could not be found
	*/
	@Override
	public com.ted.lms.model.LearningActivity deleteLearningActivity(long actId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _learningActivityLocalService.deleteLearningActivity(actId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _learningActivityLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _learningActivityLocalService.dynamicQuery();
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
		return _learningActivityLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _learningActivityLocalService.dynamicQuery(dynamicQuery, start,
			end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _learningActivityLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
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
		return _learningActivityLocalService.dynamicQueryCount(dynamicQuery);
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
		return _learningActivityLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.ted.lms.model.LearningActivity fetchLearningActivity(long actId) {
		return _learningActivityLocalService.fetchLearningActivity(actId);
	}

	/**
	* Returns the learning activity matching the UUID and group.
	*
	* @param uuid the learning activity's UUID
	* @param groupId the primary key of the group
	* @return the matching learning activity, or <code>null</code> if a matching learning activity could not be found
	*/
	@Override
	public com.ted.lms.model.LearningActivity fetchLearningActivityByUuidAndGroupId(
		String uuid, long groupId) {
		return _learningActivityLocalService.fetchLearningActivityByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _learningActivityLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _learningActivityLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _learningActivityLocalService.getIndexableActionableDynamicQuery();
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
	@Override
	public java.util.List<com.ted.lms.model.LearningActivity> getLearningActivities(
		int start, int end) {
		return _learningActivityLocalService.getLearningActivities(start, end);
	}

	@Override
	public java.util.List<com.ted.lms.model.LearningActivity> getLearningActivities(
		long moduleId) {
		return _learningActivityLocalService.getLearningActivities(moduleId);
	}

	@Override
	public java.util.List<com.ted.lms.model.LearningActivity> getLearningActivitiesByTypeId(
		long typeId) {
		return _learningActivityLocalService.getLearningActivitiesByTypeId(typeId);
	}

	/**
	* Returns all the learning activities matching the UUID and company.
	*
	* @param uuid the UUID of the learning activities
	* @param companyId the primary key of the company
	* @return the matching learning activities, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.ted.lms.model.LearningActivity> getLearningActivitiesByUuidAndCompanyId(
		String uuid, long companyId) {
		return _learningActivityLocalService.getLearningActivitiesByUuidAndCompanyId(uuid,
			companyId);
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
	@Override
	public java.util.List<com.ted.lms.model.LearningActivity> getLearningActivitiesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.ted.lms.model.LearningActivity> orderByComparator) {
		return _learningActivityLocalService.getLearningActivitiesByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of learning activities.
	*
	* @return the number of learning activities
	*/
	@Override
	public int getLearningActivitiesCount() {
		return _learningActivityLocalService.getLearningActivitiesCount();
	}

	@Override
	public int getLearningActivitiesOfModuleCount(long moduleId) {
		return _learningActivityLocalService.getLearningActivitiesOfModuleCount(moduleId);
	}

	/**
	* Returns the learning activity with the primary key.
	*
	* @param actId the primary key of the learning activity
	* @return the learning activity
	* @throws PortalException if a learning activity with the primary key could not be found
	*/
	@Override
	public com.ted.lms.model.LearningActivity getLearningActivity(long actId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _learningActivityLocalService.getLearningActivity(actId);
	}

	/**
	* Returns the learning activity matching the UUID and group.
	*
	* @param uuid the learning activity's UUID
	* @param groupId the primary key of the group
	* @return the matching learning activity
	* @throws PortalException if a matching learning activity could not be found
	*/
	@Override
	public com.ted.lms.model.LearningActivity getLearningActivityByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _learningActivityLocalService.getLearningActivityByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public com.ted.lms.model.LearningActivity getNextLearningActivity(
		com.ted.lms.model.LearningActivity activity)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _learningActivityLocalService.getNextLearningActivity(activity);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _learningActivityLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _learningActivityLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public com.ted.lms.model.LearningActivity getPreviousLearningActivity(
		com.ted.lms.model.LearningActivity activity)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _learningActivityLocalService.getPreviousLearningActivity(activity);
	}

	@Override
	public java.util.List<com.ted.lms.model.LearningActivity> getRequiredLearningActivitiesOfGroup(
		long groupId) {
		return _learningActivityLocalService.getRequiredLearningActivitiesOfGroup(groupId);
	}

	@Override
	public java.util.List<com.ted.lms.model.LearningActivity> getRequiredLearningActivitiesOfModule(
		long moduleId) {
		return _learningActivityLocalService.getRequiredLearningActivitiesOfModule(moduleId);
	}

	@Override
	public com.ted.lms.model.LearningActivity moveDownLearningActivity(
		long actId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _learningActivityLocalService.moveDownLearningActivity(actId,
			serviceContext);
	}

	@Override
	public com.ted.lms.model.LearningActivity moveLearningActivityToTrash(
		long userId, com.ted.lms.model.LearningActivity activity)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _learningActivityLocalService.moveLearningActivityToTrash(userId,
			activity);
	}

	@Override
	public com.ted.lms.model.LearningActivity moveLearningActivityToTrash(
		long userId, long actId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _learningActivityLocalService.moveLearningActivityToTrash(userId,
			actId);
	}

	@Override
	public com.ted.lms.model.LearningActivity moveUpLearningActivity(
		long actId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _learningActivityLocalService.moveUpLearningActivity(actId,
			serviceContext);
	}

	@Override
	public void updateAsset(long userId,
		com.ted.lms.model.LearningActivity activity, long[] assetCategoryIds,
		String[] assetTagNames, long[] assetLinkEntryIds, Double priority)
		throws com.liferay.portal.kernel.exception.PortalException {
		_learningActivityLocalService.updateAsset(userId, activity,
			assetCategoryIds, assetTagNames, assetLinkEntryIds, priority);
	}

	/**
	* Updates the learning activity in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param learningActivity the learning activity
	* @return the learning activity that was updated
	*/
	@Override
	public com.ted.lms.model.LearningActivity updateLearningActivity(
		com.ted.lms.model.LearningActivity learningActivity) {
		return _learningActivityLocalService.updateLearningActivity(learningActivity);
	}

	@Override
	public com.ted.lms.model.LearningActivity updateLearningActivity(
		long actId, long moduleId,
		java.util.Map<java.util.Locale, String> titleMap,
		java.util.Map<java.util.Locale, String> descriptionMap, long typeId,
		java.util.Date startDate, java.util.Date endDate, int tries,
		double passPuntuation, long priority, String extraContent,
		java.util.Map<java.util.Locale, String> feedbackCorrectMap,
		java.util.Map<java.util.Locale, String> feedbackNoCorrectMap,
		boolean required, boolean commentsActivated,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _learningActivityLocalService.updateLearningActivity(actId,
			moduleId, titleMap, descriptionMap, typeId, startDate, endDate,
			tries, passPuntuation, priority, extraContent, feedbackCorrectMap,
			feedbackNoCorrectMap, required, commentsActivated, serviceContext);
	}

	@Override
	public com.ted.lms.model.LearningActivity updateLearningActivity(
		long actId, java.util.Map<java.util.Locale, String> titleMap,
		java.util.Map<java.util.Locale, String> descriptionMap,
		boolean useStartExecutionDateCourse, int startDateMonth,
		int startDateDay, int startDateYear, int startDateHour,
		int startDateMinute, boolean useEndExecutionDateCourse,
		int endDateMonth, int endDateDay, int endDateYear, int endDateHour,
		int endDateMinute, boolean required, int tries, double passPuntuation,
		java.util.Map<java.util.Locale, String> feedbackCorrectMap,
		java.util.Map<java.util.Locale, String> feedbackNoCorrectMap,
		boolean commentsActivated,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _learningActivityLocalService.updateLearningActivity(actId,
			titleMap, descriptionMap, useStartExecutionDateCourse,
			startDateMonth, startDateDay, startDateYear, startDateHour,
			startDateMinute, useEndExecutionDateCourse, endDateMonth,
			endDateDay, endDateYear, endDateHour, endDateMinute, required,
			tries, passPuntuation, feedbackCorrectMap, feedbackNoCorrectMap,
			commentsActivated, serviceContext);
	}

	@Override
	public com.ted.lms.model.LearningActivity updateLearningActivity(
		long actId, java.util.Map<java.util.Locale, String> titleMap,
		java.util.Map<java.util.Locale, String> descriptionMap,
		java.util.Date startDate, java.util.Date endDate, int tries,
		double passPuntuation,
		java.util.Map<java.util.Locale, String> feedbackCorrectMap,
		java.util.Map<java.util.Locale, String> feedbackNoCorrectMap,
		boolean required, boolean commentsActivated,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _learningActivityLocalService.updateLearningActivity(actId,
			titleMap, descriptionMap, startDate, endDate, tries,
			passPuntuation, feedbackCorrectMap, feedbackNoCorrectMap, required,
			commentsActivated, serviceContext);
	}

	@Override
	public com.ted.lms.model.LearningActivity updateStatus(long userId,
		long actId, int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext,
		java.util.Map<String, java.io.Serializable> workflowContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _learningActivityLocalService.updateStatus(userId, actId,
			status, serviceContext, workflowContext);
	}

	@Override
	public LearningActivityLocalService getWrappedService() {
		return _learningActivityLocalService;
	}

	@Override
	public void setWrappedService(
		LearningActivityLocalService learningActivityLocalService) {
		_learningActivityLocalService = learningActivityLocalService;
	}

	private LearningActivityLocalService _learningActivityLocalService;
}