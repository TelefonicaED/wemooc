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
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

import com.ted.lms.service.CourseServiceUtil;

/**
 * Provides the HTTP utility for the
 * {@link CourseServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * {@link HttpPrincipal} parameter.
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CourseServiceSoap
 * @see HttpPrincipal
 * @see CourseServiceUtil
 * @generated
 */
@ProviderType
public class CourseServiceHttp {
	public static com.ted.lms.model.Course addCourse(
		HttpPrincipal httpPrincipal,
		java.util.Map<java.util.Locale, String> titleMap,
		java.util.Map<java.util.Locale, String> descriptionMap, String summary,
		String friendlyURL, java.util.Locale locale, long parentCourseId,
		long smallImageId, java.util.Date registrationStartDate,
		java.util.Date registrationEndDate, java.util.Date executionStartDate,
		java.util.Date executionEndDate, long layoutSetPrototypeId,
		int typeSite, long inscriptionType, long courseEvalId,
		long calificationType, int maxUsers, boolean welcome,
		String welcomeSubject, String welcomeMsg, boolean goodbye,
		String goodbyeSubject, String goodbyeMsg, int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CourseServiceUtil.class,
					"addCourse", _addCourseParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					titleMap, descriptionMap, summary, friendlyURL, locale,
					parentCourseId, smallImageId, registrationStartDate,
					registrationEndDate, executionStartDate, executionEndDate,
					layoutSetPrototypeId, typeSite, inscriptionType,
					courseEvalId, calificationType, maxUsers, welcome,
					welcomeSubject, welcomeMsg, goodbye, goodbyeSubject,
					goodbyeMsg, status, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.ted.lms.model.Course)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.ted.lms.model.Course addCourse(
		HttpPrincipal httpPrincipal, String title, String description,
		String summary, String friendlyURL, long parentCourseId,
		java.util.Date registrationStartDate,
		java.util.Date registrationEndDate, java.util.Date executionStartDate,
		java.util.Date executionEndDate, long layoutSetPrototypeId,
		int typeSite, long inscriptionType, long courseEvalId,
		long calificationType, int maxUsers, boolean welcome,
		String welcomeSubject, String welcomeMsg, boolean goodbye,
		String goodbyeSubject, String goodbyeMsg, int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CourseServiceUtil.class,
					"addCourse", _addCourseParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey, title,
					description, summary, friendlyURL, parentCourseId,
					registrationStartDate, registrationEndDate,
					executionStartDate, executionEndDate, layoutSetPrototypeId,
					typeSite, inscriptionType, courseEvalId, calificationType,
					maxUsers, welcome, welcomeSubject, welcomeMsg, goodbye,
					goodbyeSubject, goodbyeMsg, status, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.ted.lms.model.Course)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(CourseServiceHttp.class);
	private static final Class<?>[] _addCourseParameterTypes0 = new Class[] {
			java.util.Map.class, java.util.Map.class, String.class, String.class,
			java.util.Locale.class, long.class, long.class, java.util.Date.class,
			java.util.Date.class, java.util.Date.class, java.util.Date.class,
			long.class, int.class, long.class, long.class, long.class, int.class,
			boolean.class, String.class, String.class, boolean.class,
			String.class, String.class, int.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _addCourseParameterTypes1 = new Class[] {
			String.class, String.class, String.class, String.class, long.class,
			java.util.Date.class, java.util.Date.class, java.util.Date.class,
			java.util.Date.class, long.class, int.class, long.class, long.class,
			long.class, int.class, boolean.class, String.class, String.class,
			boolean.class, String.class, String.class, int.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
}