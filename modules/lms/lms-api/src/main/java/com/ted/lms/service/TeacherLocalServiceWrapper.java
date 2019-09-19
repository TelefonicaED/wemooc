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
 * Provides a wrapper for {@link TeacherLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see TeacherLocalService
 * @generated
 */
@ProviderType
public class TeacherLocalServiceWrapper
	implements TeacherLocalService, ServiceWrapper<TeacherLocalService> {

	public TeacherLocalServiceWrapper(TeacherLocalService teacherLocalService) {
		_teacherLocalService = teacherLocalService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _teacherLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.User>
			getTeachersFromCourse(
				long courseId, long companyId, String keywords, int status,
				java.util.LinkedHashMap<String, Object> params, int start,
				int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teacherLocalService.getTeachersFromCourse(
			courseId, companyId, keywords, status, params, start, end, obc);
	}

	@Override
	public TeacherLocalService getWrappedService() {
		return _teacherLocalService;
	}

	@Override
	public void setWrappedService(TeacherLocalService teacherLocalService) {
		_teacherLocalService = teacherLocalService;
	}

	private TeacherLocalService _teacherLocalService;

}