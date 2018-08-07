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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Transactional;

import com.ted.lms.model.Course;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * Provides the remote service interface for Course. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see CourseServiceUtil
 * @see com.ted.lms.service.base.CourseServiceBaseImpl
 * @see com.ted.lms.service.impl.CourseServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@OSGiBeanProperties(property =  {
	"json.web.service.context.name=lms", "json.web.service.context.path=Course"}, service = CourseService.class)
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface CourseService extends BaseService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CourseServiceUtil} to access the course remote service. Add custom service methods to {@link com.ted.lms.service.impl.CourseServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public Course addCourse(Map<Locale, String> titleMap,
		Map<Locale, String> descriptionMap, String summary, String friendlyURL,
		Locale locale, long parentCourseId, long smallImageId,
		Date registrationStartDate, Date registrationEndDate,
		Date executionStartDate, Date executionEndDate,
		long layoutSetPrototypeId, int typeSite, long inscriptionType,
		long courseEvalId, long calificationType, int maxUsers,
		boolean welcome, String welcomeSubject, String welcomeMsg,
		boolean goodbye, String goodbyeSubject, String goodbyeMsg, int status,
		ServiceContext serviceContext) throws PortalException;

	public Course addCourse(String title, String description, String summary,
		String friendlyURL, long parentCourseId, Date registrationStartDate,
		Date registrationEndDate, Date executionStartDate,
		Date executionEndDate, long layoutSetPrototypeId, int typeSite,
		long inscriptionType, long courseEvalId, long calificationType,
		int maxUsers, boolean welcome, String welcomeSubject,
		String welcomeMsg, boolean goodbye, String goodbyeSubject,
		String goodbyeMsg, int status, ServiceContext serviceContext)
		throws PortalException;

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public String getOSGiServiceIdentifier();
}