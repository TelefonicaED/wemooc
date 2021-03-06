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

import org.osgi.annotation.versioning.ProviderType;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for LearningActivity. This utility wraps
 * <code>com.ted.lms.service.impl.LearningActivityServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see LearningActivityService
 * @generated
 */
@ProviderType
public class LearningActivityServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.ted.lms.service.impl.LearningActivityServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.ted.lms.model.LearningActivity addLearningActivity(
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

		return getService().addLearningActivity(
			groupId, moduleId, type, titleMap, descriptionMap,
			useStartExecutionDateCourse, startDateMonth, startDateDay,
			startDateYear, startDateHour, startDateMinute,
			useEndExecutionDateCourse, endDateMonth, endDateDay, endDateYear,
			endDateHour, endDateMinute, required, tries, passPuntuation,
			feedbackCorrectMap, feedbackNoCorrectMap, commentsActivated,
			selectedFileNames, serviceContext);
	}

	public static void changeVisibility(long actId)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().changeVisibility(actId);
	}

	public static com.ted.lms.model.LearningActivity deleteLearningActivity(
			long actId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteLearningActivity(actId);
	}

	public static void executeDeleteTries(
			long groupId, long actId, long studentId, boolean onlyTriesFailed,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.security.auth.PrincipalException {

		getService().executeDeleteTries(
			groupId, actId, studentId, onlyTriesFailed, serviceContext);
	}

	public static java.util.List<com.ted.lms.model.LearningActivity>
		getActivities(long moduleId) {

		return getService().getActivities(moduleId);
	}

	public static java.util.List<com.ted.lms.model.LearningActivity>
		getActivitiesExcluded(long moduleId, long actId) {

		return getService().getActivitiesExcluded(moduleId, actId);
	}

	public static java.util.List<com.ted.lms.model.LearningActivity>
		getActivitiesNotTypeId(long moduleId, long typeId) {

		return getService().getActivitiesNotTypeId(moduleId, typeId);
	}

	public static com.ted.lms.model.LearningActivity getFirstLearningActivity(
			long groupId, long moduleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getFirstLearningActivity(groupId, moduleId);
	}

	public static com.ted.lms.model.LearningActivity getLearningActivity(
			long actId)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.security.auth.PrincipalException {

		return getService().getLearningActivity(actId);
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

	public static com.ted.lms.model.LearningActivity
			getPreviousLearningActivity(
				com.ted.lms.model.LearningActivity activity)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPreviousLearningActivity(activity);
	}

	public static String[] getTempFileNames(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getTempFileNames(groupId);
	}

	public static com.ted.lms.model.LearningActivity moveDownLearningActivity(
			long actId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().moveDownLearningActivity(actId);
	}

	public static com.ted.lms.model.LearningActivity
			moveLearningActivityToTrash(long actId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().moveLearningActivityToTrash(actId);
	}

	public static com.ted.lms.model.LearningActivity moveUpLearningActivity(
			long actId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().moveUpLearningActivity(actId);
	}

	public static com.ted.lms.model.LearningActivity updateLearningActivity(
			com.ted.lms.model.LearningActivity activity)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateLearningActivity(activity);
	}

	public static com.ted.lms.model.LearningActivity updateLearningActivity(
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

		return getService().updateLearningActivity(
			actId, titleMap, descriptionMap, useStartExecutionDateCourse,
			startDateMonth, startDateDay, startDateYear, startDateHour,
			startDateMinute, useEndExecutionDateCourse, endDateMonth,
			endDateDay, endDateYear, endDateHour, endDateMinute, required,
			tries, passPuntuation, feedbackCorrectMap, feedbackNoCorrectMap,
			commentsActivated, selectedFileNames, removeFileEntryIds,
			serviceContext);
	}

	public static LearningActivityService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<LearningActivityService, LearningActivityService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(LearningActivityService.class);

		ServiceTracker<LearningActivityService, LearningActivityService>
			serviceTracker =
				new ServiceTracker
					<LearningActivityService, LearningActivityService>(
						bundle.getBundleContext(),
						LearningActivityService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}