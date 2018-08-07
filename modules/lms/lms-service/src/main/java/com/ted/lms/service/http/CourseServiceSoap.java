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
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;

import com.ted.lms.service.CourseServiceUtil;

import java.rmi.RemoteException;

import java.util.Locale;
import java.util.Map;

/**
 * Provides the SOAP utility for the
 * {@link CourseServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.ted.lms.model.CourseSoap}.
 * If the method in the service utility returns a
 * {@link com.ted.lms.model.Course}, that is translated to a
 * {@link com.ted.lms.model.CourseSoap}. Methods that SOAP cannot
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
 * @see CourseServiceHttp
 * @see com.ted.lms.model.CourseSoap
 * @see CourseServiceUtil
 * @generated
 */
@ProviderType
public class CourseServiceSoap {
	public static com.ted.lms.model.CourseSoap addCourse(
		String[] titleMapLanguageIds, String[] titleMapValues,
		String[] descriptionMapLanguageIds, String[] descriptionMapValues,
		String summary, String friendlyURL, String locale, long parentCourseId,
		long smallImageId, java.util.Date registrationStartDate,
		java.util.Date registrationEndDate, java.util.Date executionStartDate,
		java.util.Date executionEndDate, long layoutSetPrototypeId,
		int typeSite, long inscriptionType, long courseEvalId,
		long calificationType, int maxUsers, boolean welcome,
		String welcomeSubject, String welcomeMsg, boolean goodbye,
		String goodbyeSubject, String goodbyeMsg, int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			Map<Locale, String> titleMap = LocalizationUtil.getLocalizationMap(titleMapLanguageIds,
					titleMapValues);
			Map<Locale, String> descriptionMap = LocalizationUtil.getLocalizationMap(descriptionMapLanguageIds,
					descriptionMapValues);

			com.ted.lms.model.Course returnValue = CourseServiceUtil.addCourse(titleMap,
					descriptionMap, summary, friendlyURL,
					LocaleUtil.fromLanguageId(locale), parentCourseId,
					smallImageId, registrationStartDate, registrationEndDate,
					executionStartDate, executionEndDate, layoutSetPrototypeId,
					typeSite, inscriptionType, courseEvalId, calificationType,
					maxUsers, welcome, welcomeSubject, welcomeMsg, goodbye,
					goodbyeSubject, goodbyeMsg, status, serviceContext);

			return com.ted.lms.model.CourseSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.ted.lms.model.CourseSoap addCourse(String title,
		String description, String summary, String friendlyURL,
		long parentCourseId, java.util.Date registrationStartDate,
		java.util.Date registrationEndDate, java.util.Date executionStartDate,
		java.util.Date executionEndDate, long layoutSetPrototypeId,
		int typeSite, long inscriptionType, long courseEvalId,
		long calificationType, int maxUsers, boolean welcome,
		String welcomeSubject, String welcomeMsg, boolean goodbye,
		String goodbyeSubject, String goodbyeMsg, int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.ted.lms.model.Course returnValue = CourseServiceUtil.addCourse(title,
					description, summary, friendlyURL, parentCourseId,
					registrationStartDate, registrationEndDate,
					executionStartDate, executionEndDate, layoutSetPrototypeId,
					typeSite, inscriptionType, courseEvalId, calificationType,
					maxUsers, welcome, welcomeSubject, welcomeMsg, goodbye,
					goodbyeSubject, goodbyeMsg, status, serviceContext);

			return com.ted.lms.model.CourseSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(CourseServiceSoap.class);
}