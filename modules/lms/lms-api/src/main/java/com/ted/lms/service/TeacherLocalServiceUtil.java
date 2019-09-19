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
 * Provides the local service utility for Teacher. This utility wraps
 * <code>com.ted.lms.service.impl.TeacherLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see TeacherLocalService
 * @generated
 */
@ProviderType
public class TeacherLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.ted.lms.service.impl.TeacherLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static java.util.List<com.liferay.portal.kernel.model.User>
			getTeachersFromCourse(
				long courseId, long companyId, String keywords, int status,
				java.util.LinkedHashMap<String, Object> params, int start,
				int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getTeachersFromCourse(
			courseId, companyId, keywords, status, params, start, end, obc);
	}

	public static TeacherLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<TeacherLocalService, TeacherLocalService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(TeacherLocalService.class);

		ServiceTracker<TeacherLocalService, TeacherLocalService>
			serviceTracker =
				new ServiceTracker<TeacherLocalService, TeacherLocalService>(
					bundle.getBundleContext(), TeacherLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}