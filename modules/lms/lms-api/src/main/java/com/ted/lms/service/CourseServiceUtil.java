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
 * Provides the remote service utility for Course. This utility wraps
 * {@link com.ted.lms.service.impl.CourseServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see CourseService
 * @see com.ted.lms.service.base.CourseServiceBaseImpl
 * @see com.ted.lms.service.impl.CourseServiceImpl
 * @generated
 */
@ProviderType
public class CourseServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.ted.lms.service.impl.CourseServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.ted.lms.model.Course addCourse(
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
		return getService()
				   .addCourse(titleMap, descriptionMap, summary, friendlyURL,
			locale, parentCourseId, smallImageId, registrationStartDate,
			registrationEndDate, executionStartDate, executionEndDate,
			layoutSetPrototypeId, typeSite, inscriptionType, courseEvalId,
			calificationType, maxUsers, welcome, welcomeSubject, welcomeMsg,
			goodbye, goodbyeSubject, goodbyeMsg, status, serviceContext);
	}

	public static com.ted.lms.model.Course addCourse(String title,
		String description, String summary, String friendlyURL,
		long parentCourseId, java.util.Date registrationStartDate,
		java.util.Date registrationEndDate, java.util.Date executionStartDate,
		java.util.Date executionEndDate, long layoutSetPrototypeId,
		int typeSite, long inscriptionType, long courseEvalId,
		long calificationType, int maxUsers, boolean welcome,
		String welcomeSubject, String welcomeMsg, boolean goodbye,
		String goodbyeSubject, String goodbyeMsg, int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addCourse(title, description, summary, friendlyURL,
			parentCourseId, registrationStartDate, registrationEndDate,
			executionStartDate, executionEndDate, layoutSetPrototypeId,
			typeSite, inscriptionType, courseEvalId, calificationType,
			maxUsers, welcome, welcomeSubject, welcomeMsg, goodbye,
			goodbyeSubject, goodbyeMsg, status, serviceContext);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static CourseService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CourseService, CourseService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CourseService.class);

		ServiceTracker<CourseService, CourseService> serviceTracker = new ServiceTracker<CourseService, CourseService>(bundle.getBundleContext(),
				CourseService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}