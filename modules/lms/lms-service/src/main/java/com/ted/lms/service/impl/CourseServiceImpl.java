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

package com.ted.lms.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermissionFactory;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.ted.lms.constants.LMSActionKeys;
import com.ted.lms.constants.LMSConstants;
import com.ted.lms.model.Course;
import com.ted.lms.service.base.CourseServiceBaseImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * The implementation of the course remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ted.lms.service.CourseService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CourseServiceBaseImpl
 * @see com.ted.lms.service.CourseServiceUtil
 */
public class CourseServiceImpl extends CourseServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.ted.lms.service.CourseServiceUtil} to access the course remote service.
	 */
	
	private static volatile ModelResourcePermission<Course>
    	courseModelResourcePermission =
        ModelResourcePermissionFactory.getInstance(
            CourseServiceImpl.class,
            "courseModelResourcePermission", Course.class);
	private static volatile PortletResourcePermission
	    portletResourcePermission =
	        PortletResourcePermissionFactory.getInstance(
	            CourseServiceImpl.class, "portletResourcePermission",
	            LMSConstants.RESOURCE_NAME);
	
	public Course addCourse(Map<Locale,String> titleMap, Map<Locale,String> descriptionMap, String summary, String friendlyURL, Locale locale, long parentCourseId, 
			long smallImageId, Date registrationStartDate, Date registrationEndDate, Date executionStartDate, Date executionEndDate, long layoutSetPrototypeId,int typeSite, 
			long inscriptionType, long courseEvalId, long calificationType, int maxUsers, boolean welcome, String welcomeSubject, String welcomeMsg, 
			boolean goodbye, String goodbyeSubject, String goodbyeMsg, int status, ServiceContext serviceContext) throws PortalException {
		
		portletResourcePermission.check(
				getPermissionChecker(), serviceContext.getScopeGroupId(),
				LMSActionKeys.ADD_COURSE);
		
		return courseLocalService.addCourse(titleMap, descriptionMap, summary, friendlyURL, locale, parentCourseId, smallImageId, registrationStartDate, 
				registrationEndDate, executionStartDate, executionEndDate, layoutSetPrototypeId, typeSite, inscriptionType, courseEvalId, calificationType, 
				maxUsers, welcome, welcomeSubject, welcomeMsg, goodbye, goodbyeSubject, goodbyeMsg, status, serviceContext);
		
	}
	
	public Course addCourse(String title, String description, String summary, String friendlyURL, long parentCourseId, 
			Date registrationStartDate, Date registrationEndDate, Date executionStartDate, Date executionEndDate, long layoutSetPrototypeId,int typeSite, 
			long inscriptionType, long courseEvalId, long calificationType, int maxUsers, boolean welcome, String welcomeSubject, String welcomeMsg, 
			boolean goodbye, String goodbyeSubject, String goodbyeMsg, int status, ServiceContext serviceContext) throws PortalException {
		
		portletResourcePermission.check(
				getPermissionChecker(), serviceContext.getScopeGroupId(),
				LMSActionKeys.ADD_COURSE);
		Map<Locale, String> titleMap = new HashMap<Locale,String>();
		titleMap.put(LocaleUtil.getDefault(), title);
		
		Map<Locale, String> descriptionMap = new HashMap<Locale,String>();
		descriptionMap.put(LocaleUtil.getDefault(), description);
		
		return courseLocalService.addCourse(titleMap, descriptionMap, summary, friendlyURL, LocaleUtil.getDefault(), parentCourseId, 0, registrationStartDate, 
				registrationEndDate, executionStartDate, executionEndDate, layoutSetPrototypeId, typeSite, inscriptionType, courseEvalId, calificationType, 
				maxUsers, welcome, welcomeSubject, welcomeMsg, goodbye, goodbyeSubject, goodbyeMsg, status, serviceContext);
		
	}
}