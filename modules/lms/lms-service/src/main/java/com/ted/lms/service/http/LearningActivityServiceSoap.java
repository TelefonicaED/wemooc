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

package com.ted.lms.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;

import com.ted.lms.service.LearningActivityServiceUtil;

import java.rmi.RemoteException;

import java.util.Locale;
import java.util.Map;

/**
 * Provides the SOAP utility for the
 * {@link LearningActivityServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.ted.lms.model.LearningActivitySoap}.
 * If the method in the service utility returns a
 * {@link com.ted.lms.model.LearningActivity}, that is translated to a
 * {@link com.ted.lms.model.LearningActivitySoap}. Methods that SOAP cannot
 * safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LearningActivityServiceHttp
 * @see com.ted.lms.model.LearningActivitySoap
 * @see LearningActivityServiceUtil
 * @generated
 */
@ProviderType
public class LearningActivityServiceSoap {
	public static com.ted.lms.model.LearningActivitySoap updateLearningActivity(
		com.ted.lms.model.LearningActivitySoap activity)
		throws RemoteException {
		try {
			com.ted.lms.model.LearningActivity returnValue = LearningActivityServiceUtil.updateLearningActivity(com.ted.lms.model.impl.LearningActivityModelImpl.toModel(
						activity));

			return com.ted.lms.model.LearningActivitySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.ted.lms.model.LearningActivitySoap moveDownLearningActivity(
		long actId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.ted.lms.model.LearningActivity returnValue = LearningActivityServiceUtil.moveDownLearningActivity(actId,
					serviceContext);

			return com.ted.lms.model.LearningActivitySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.ted.lms.model.LearningActivitySoap moveUpLearningActivity(
		long actId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.ted.lms.model.LearningActivity returnValue = LearningActivityServiceUtil.moveUpLearningActivity(actId,
					serviceContext);

			return com.ted.lms.model.LearningActivitySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.ted.lms.model.LearningActivitySoap moveLearningActivityToTrash(
		long actId) throws RemoteException {
		try {
			com.ted.lms.model.LearningActivity returnValue = LearningActivityServiceUtil.moveLearningActivityToTrash(actId);

			return com.ted.lms.model.LearningActivitySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.ted.lms.model.LearningActivitySoap deleteLearningActivity(
		long actId) throws RemoteException {
		try {
			com.ted.lms.model.LearningActivity returnValue = LearningActivityServiceUtil.deleteLearningActivity(actId);

			return com.ted.lms.model.LearningActivitySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.ted.lms.model.LearningActivitySoap addLearningActivity(
		long moduleId, long type, String[] titleMapLanguageIds,
		String[] titleMapValues, String[] descriptionMapLanguageIds,
		String[] descriptionMapValues, boolean useStartExecutionDateCourse,
		int startDateMonth, int startDateDay, int startDateYear,
		int startDateHour, int startDateMinute,
		boolean useEndExecutionDateCourse, int endDateMonth, int endDateDay,
		int endDateYear, int endDateHour, int endDateMinute, boolean required,
		int tries, double passPuntuation,
		String[] feedbackCorrectMapLanguageIds,
		String[] feedbackCorrectMapValues,
		String[] feedbackNoCorrectMapLanguageIds,
		String[] feedbackNoCorrectMapValues, boolean commentsActivated,
		String[] selectedFileNames,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			Map<Locale, String> titleMap = LocalizationUtil.getLocalizationMap(titleMapLanguageIds,
					titleMapValues);
			Map<Locale, String> descriptionMap = LocalizationUtil.getLocalizationMap(descriptionMapLanguageIds,
					descriptionMapValues);
			Map<Locale, String> feedbackCorrectMap = LocalizationUtil.getLocalizationMap(feedbackCorrectMapLanguageIds,
					feedbackCorrectMapValues);
			Map<Locale, String> feedbackNoCorrectMap = LocalizationUtil.getLocalizationMap(feedbackNoCorrectMapLanguageIds,
					feedbackNoCorrectMapValues);

			com.ted.lms.model.LearningActivity returnValue = LearningActivityServiceUtil.addLearningActivity(moduleId,
					type, titleMap, descriptionMap,
					useStartExecutionDateCourse, startDateMonth, startDateDay,
					startDateYear, startDateHour, startDateMinute,
					useEndExecutionDateCourse, endDateMonth, endDateDay,
					endDateYear, endDateHour, endDateMinute, required, tries,
					passPuntuation, feedbackCorrectMap, feedbackNoCorrectMap,
					commentsActivated, selectedFileNames, serviceContext);

			return com.ted.lms.model.LearningActivitySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.ted.lms.model.LearningActivitySoap updateLearningActivity(
		long actId, String[] titleMapLanguageIds, String[] titleMapValues,
		String[] descriptionMapLanguageIds, String[] descriptionMapValues,
		boolean useStartExecutionDateCourse, int startDateMonth,
		int startDateDay, int startDateYear, int startDateHour,
		int startDateMinute, boolean useEndExecutionDateCourse,
		int endDateMonth, int endDateDay, int endDateYear, int endDateHour,
		int endDateMinute, boolean required, int tries, double passPuntuation,
		String[] feedbackCorrectMapLanguageIds,
		String[] feedbackCorrectMapValues,
		String[] feedbackNoCorrectMapLanguageIds,
		String[] feedbackNoCorrectMapValues, boolean commentsActivated,
		String[] selectedFileNames, long[] removeFileEntryIds,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			Map<Locale, String> titleMap = LocalizationUtil.getLocalizationMap(titleMapLanguageIds,
					titleMapValues);
			Map<Locale, String> descriptionMap = LocalizationUtil.getLocalizationMap(descriptionMapLanguageIds,
					descriptionMapValues);
			Map<Locale, String> feedbackCorrectMap = LocalizationUtil.getLocalizationMap(feedbackCorrectMapLanguageIds,
					feedbackCorrectMapValues);
			Map<Locale, String> feedbackNoCorrectMap = LocalizationUtil.getLocalizationMap(feedbackNoCorrectMapLanguageIds,
					feedbackNoCorrectMapValues);

			com.ted.lms.model.LearningActivity returnValue = LearningActivityServiceUtil.updateLearningActivity(actId,
					titleMap, descriptionMap, useStartExecutionDateCourse,
					startDateMonth, startDateDay, startDateYear, startDateHour,
					startDateMinute, useEndExecutionDateCourse, endDateMonth,
					endDateDay, endDateYear, endDateHour, endDateMinute,
					required, tries, passPuntuation, feedbackCorrectMap,
					feedbackNoCorrectMap, commentsActivated, selectedFileNames,
					removeFileEntryIds, serviceContext);

			return com.ted.lms.model.LearningActivitySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.ted.lms.model.LearningActivitySoap[] getActivities(
		long moduleId) throws RemoteException {
		try {
			java.util.List<com.ted.lms.model.LearningActivity> returnValue = LearningActivityServiceUtil.getActivities(moduleId);

			return com.ted.lms.model.LearningActivitySoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static String[] getTempFileNames(long groupId)
		throws RemoteException {
		try {
			String[] returnValue = LearningActivityServiceUtil.getTempFileNames(groupId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(LearningActivityServiceSoap.class);
}