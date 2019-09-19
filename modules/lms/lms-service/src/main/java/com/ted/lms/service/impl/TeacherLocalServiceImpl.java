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

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.ted.lms.service.base.TeacherLocalServiceBaseImpl;

import java.util.LinkedHashMap;
import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * The implementation of the teacher local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.ted.lms.service.TeacherLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TeacherLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=com.ted.lms.model.Teacher",
	service = AopService.class
)
public class TeacherLocalServiceImpl extends TeacherLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>com.ted.lms.service.TeacherLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.ted.lms.service.TeacherLocalServiceUtil</code>.
	 */
	
	public List<User> getTeachersFromCourse(long courseId, long companyId, String keywords, int status, 
			LinkedHashMap<String,Object> params, int start, int end, OrderByComparator obc) throws PortalException{
		return courseFinder.findTeachers(courseId, companyId, keywords, keywords, keywords, keywords, status, null, params, false, start, end, obc);
	}
}