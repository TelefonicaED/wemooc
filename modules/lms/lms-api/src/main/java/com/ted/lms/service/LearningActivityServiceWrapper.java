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

import com.liferay.portal.kernel.service.ServiceWrapper;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides a wrapper for {@link LearningActivityService}.
 *
 * @author Brian Wing Shun Chan
 * @see LearningActivityService
 * @generated
 */
@ProviderType
public class LearningActivityServiceWrapper
	implements LearningActivityService,
			   ServiceWrapper<LearningActivityService> {

	public LearningActivityServiceWrapper(
		LearningActivityService learningActivityService) {

		_learningActivityService = learningActivityService;
	}

	@Override
	public com.ted.lms.model.LearningActivity addLearningActivity(
			long groupId, long moduleId, long type,
			java.util.Map<java.util.Locale, String> titleMap,
			java.util.Map<java.util.Locale, String> descriptionMap,
			boolean useStartExecutionDateCourse, int startDateMonth,
			int startDateDay, int startDateYear, int startDateHour,
			int startDateMinute, boolean useEndExecutionDateCourse,
			int endDateMonth, int endDateDay, int endDateYear, int endDateHour,
			int endDateMinute, boolean required, int tries,
			double passPuntuation,
			java.util.Map<java.util.Locale, String> feedbackCorrectMap,
			java.util.Map<java.util.Locale, String> feedbackNoCorrectMap,
			boolean commentsActivated, String[] selectedFileNames,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _learningActivityService.addLearningActivity(
			groupId, moduleId, type, titleMap, descriptionMap,
			useStartExecutionDateCourse, startDateMonth, startDateDay,
			startDateYear, startDateHour, startDateMinute,
			useEndExecutionDateCourse, endDateMonth, endDateDay, endDateYear,
			endDateHour, endDateMinute, required, tries, passPuntuation,
			feedbackCorrectMap, feedbackNoCorrectMap, commentsActivated,
			selectedFileNames, serviceContext);
	}

	@Override
	public void changeVisibility(long actId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_learningActivityService.changeVisibility(actId);
	}

	@Override
	public com.ted.lms.model.LearningActivity deleteLearningActivity(long actId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _learningActivityService.deleteLearningActivity(actId);
	}

	@Override
	public void executeDeleteTries(
			long groupId, long actId, long studentId, boolean onlyTriesFailed,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.security.auth.PrincipalException {

		_learningActivityService.executeDeleteTries(
			groupId, actId, studentId, onlyTriesFailed, serviceContext);
	}

	@Override
	public java.util.List<com.ted.lms.model.LearningActivity> getActivities(
		long moduleId) {

		return _learningActivityService.getActivities(moduleId);
	}

	@Override
	public java.util.List<com.ted.lms.model.LearningActivity>
		getActivitiesExcluded(long moduleId, long actId) {

		return _learningActivityService.getActivitiesExcluded(moduleId, actId);
	}

	@Override
	public java.util.List<com.ted.lms.model.LearningActivity>
		getActivitiesNotTypeId(long moduleId, long typeId) {

		return _learningActivityService.getActivitiesNotTypeId(
			moduleId, typeId);
	}

	@Override
	public com.ted.lms.model.LearningActivity getFirstLearningActivity(
			long groupId, long moduleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _learningActivityService.getFirstLearningActivity(
			groupId, moduleId);
	}

	@Override
	public com.ted.lms.model.LearningActivity getLearningActivity(long actId)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.security.auth.PrincipalException {

		return _learningActivityService.getLearningActivity(actId);
	}

	@Override
	public com.ted.lms.model.LearningActivity getNextLearningActivity(
			com.ted.lms.model.LearningActivity activity)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _learningActivityService.getNextLearningActivity(activity);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _learningActivityService.getOSGiServiceIdentifier();
	}

	@Override
	public com.ted.lms.model.LearningActivity getPreviousLearningActivity(
			com.ted.lms.model.LearningActivity activity)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _learningActivityService.getPreviousLearningActivity(activity);
	}

	@Override
	public String[] getTempFileNames(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _learningActivityService.getTempFileNames(groupId);
	}

	@Override
	public com.ted.lms.model.LearningActivity moveDownLearningActivity(
			long actId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _learningActivityService.moveDownLearningActivity(actId);
	}

	@Override
	public com.ted.lms.model.LearningActivity moveLearningActivityToTrash(
			long actId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _learningActivityService.moveLearningActivityToTrash(actId);
	}

	@Override
	public com.ted.lms.model.LearningActivity moveUpLearningActivity(long actId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _learningActivityService.moveUpLearningActivity(actId);
	}

	@Override
	public com.ted.lms.model.LearningActivity updateLearningActivity(
			com.ted.lms.model.LearningActivity activity)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _learningActivityService.updateLearningActivity(activity);
	}

	@Override
	public com.ted.lms.model.LearningActivity updateLearningActivity(
			long actId, java.util.Map<java.util.Locale, String> titleMap,
			java.util.Map<java.util.Locale, String> descriptionMap,
			boolean useStartExecutionDateCourse, int startDateMonth,
			int startDateDay, int startDateYear, int startDateHour,
			int startDateMinute, boolean useEndExecutionDateCourse,
			int endDateMonth, int endDateDay, int endDateYear, int endDateHour,
			int endDateMinute, boolean required, int tries,
			double passPuntuation,
			java.util.Map<java.util.Locale, String> feedbackCorrectMap,
			java.util.Map<java.util.Locale, String> feedbackNoCorrectMap,
			boolean commentsActivated, String[] selectedFileNames,
			long[] removeFileEntryIds,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.security.auth.PrincipalException {

		return _learningActivityService.updateLearningActivity(
			actId, titleMap, descriptionMap, useStartExecutionDateCourse,
			startDateMonth, startDateDay, startDateYear, startDateHour,
			startDateMinute, useEndExecutionDateCourse, endDateMonth,
			endDateDay, endDateYear, endDateHour, endDateMinute, required,
			tries, passPuntuation, feedbackCorrectMap, feedbackNoCorrectMap,
			commentsActivated, selectedFileNames, removeFileEntryIds,
			serviceContext);
	}

	@Override
	public LearningActivityService getWrappedService() {
		return _learningActivityService;
	}

	@Override
	public void setWrappedService(
		LearningActivityService learningActivityService) {

		_learningActivityService = learningActivityService;
	}

	private LearningActivityService _learningActivityService;

}