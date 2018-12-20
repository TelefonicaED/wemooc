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
 * Provides a wrapper for {@link LearningActivityService}.
 *
 * @author Brian Wing Shun Chan
 * @see LearningActivityService
 * @generated
 */
@ProviderType
public class LearningActivityServiceWrapper implements LearningActivityService,
	ServiceWrapper<LearningActivityService> {
	public LearningActivityServiceWrapper(
		LearningActivityService learningActivityService) {
		_learningActivityService = learningActivityService;
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
		return _learningActivityService.addLearningActivity(moduleId, type,
			titleMap, descriptionMap, useStartExecutionDateCourse,
			startDateMonth, startDateDay, startDateYear, startDateHour,
			startDateMinute, useEndExecutionDateCourse, endDateMonth,
			endDateDay, endDateYear, endDateHour, endDateMinute, required,
			tries, passPuntuation, feedbackCorrectMap, feedbackNoCorrectMap,
			commentsActivated, serviceContext);
	}

	@Override
	public com.ted.lms.model.LearningActivity deleteLearningActivity(long actId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _learningActivityService.deleteLearningActivity(actId);
	}

	@Override
	public java.util.List<com.ted.lms.model.LearningActivity> getActivities(
		long moduleId) {
		return _learningActivityService.getActivities(moduleId);
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
	public com.ted.lms.model.LearningActivity moveDownLearningActivity(
		long actId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _learningActivityService.moveDownLearningActivity(actId,
			serviceContext);
	}

	@Override
	public com.ted.lms.model.LearningActivity moveLearningActivityToTrash(
		long actId) throws com.liferay.portal.kernel.exception.PortalException {
		return _learningActivityService.moveLearningActivityToTrash(actId);
	}

	@Override
	public com.ted.lms.model.LearningActivity moveUpLearningActivity(
		long actId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _learningActivityService.moveUpLearningActivity(actId,
			serviceContext);
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
		int endDateMinute, boolean required, int tries, double passPuntuation,
		java.util.Map<java.util.Locale, String> feedbackCorrectMap,
		java.util.Map<java.util.Locale, String> feedbackNoCorrectMap,
		boolean commentsActivated,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.security.auth.PrincipalException,
			com.liferay.portal.kernel.exception.PortalException {
		return _learningActivityService.updateLearningActivity(actId, titleMap,
			descriptionMap, useStartExecutionDateCourse, startDateMonth,
			startDateDay, startDateYear, startDateHour, startDateMinute,
			useEndExecutionDateCourse, endDateMonth, endDateDay, endDateYear,
			endDateHour, endDateMinute, required, tries, passPuntuation,
			feedbackCorrectMap, feedbackNoCorrectMap, commentsActivated,
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