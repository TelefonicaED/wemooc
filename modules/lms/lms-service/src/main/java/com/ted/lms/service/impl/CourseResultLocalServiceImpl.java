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
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.MembershipRequestConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.MembershipRequestLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.social.kernel.model.SocialActivityConstants;
import com.liferay.social.kernel.service.SocialActivityLocalServiceUtil;
import com.ted.lms.constants.LMSActionKeys;
import com.ted.lms.exception.InscriptionException;
import com.ted.lms.model.Course;
import com.ted.lms.model.CourseResult;
import com.ted.lms.model.Postcondition;
import com.ted.lms.registry.PostconditionRegistryUtil;
import com.ted.lms.security.permission.resource.CoursePermission;
import com.ted.lms.service.base.CourseResultLocalServiceBaseImpl;
import com.ted.prerequisite.model.Prerequisite;
import com.ted.prerequisite.service.PrerequisiteRelationLocalServiceUtil;

import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * The implementation of the course result local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ted.lms.service.CourseResultLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CourseResultLocalServiceBaseImpl
 * @see com.ted.lms.service.CourseResultLocalServiceUtil
 */
public class CourseResultLocalServiceImpl
	extends CourseResultLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.ted.lms.service.CourseResultLocalServiceUtil} to access the course result local service.
	 */
	
	private static final Log log = LogFactoryUtil.getLog(CourseResultLocalServiceImpl.class);
	
	public List<CourseResult> getCourseResults(long courseId){
		
		return courseResultPersistence.findByCourseId(courseId);
		
	}
	
	public CourseResult getByCourseIdUserId(long courseId, long userId) {
		return courseResultPersistence.fetchByCourseIdUserId(courseId, userId);
	}
	
	public CourseResult addCourseResult(long courseId, long userId, ServiceContext serviceContext) {
		CourseResult courseResult = null;
		
		try {
			User userModified = userLocalService.getUser(serviceContext.getUserId());
			
			courseResult = courseResultPersistence.create(counterLocalService.increment(CourseResult.class.getName()));
			courseResult.setCourseId(courseId);
			courseResult.setUserId(userId);
			courseResult.setResult(0);
			courseResult.setRegistrationDate(new Date());
			courseResult.setPassed(false);
			courseResult.setGroupId(serviceContext.getScopeGroupId());
			courseResult.setCompanyId(serviceContext.getCompanyId());
			courseResult.setUserModifiedId(serviceContext.getUserId());
			courseResult.setUserModifiedName(userModified.getFullName());
			courseResult.setCreateDate(new Date());
			courseResult.setModifiedDate(courseResult.getCreateDate());
			
			courseResultPersistence.update(courseResult);
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return courseResult;
	}
	
	public List<CourseResult> getCourseResults(long courseId, boolean passed){
		return courseResultPersistence.findByCourseIdPassed(courseId, passed);
	}
	
	public List<CourseResult> getFailedCourseResults(long courseId){
		return courseResultPersistence.findByFinished(courseId, false);
	}
	
	@Override
	public CourseResult updateCourseResult(CourseResult courseResult) {
		//Si ha finalizado el curso llamamos a las postcondiciones
		if(Validator.isNotNull(courseResult.getPassedDate())) {
			List<Postcondition> listPostcondition = PostconditionRegistryUtil.getPostconditions(courseResult.getCompanyId());
			for(Postcondition postcondition: listPostcondition) {
				if(courseResult.isPassed()) {
					postcondition.passedCourseResult(courseResult);
				}else {
					postcondition.failedCourseResult(courseResult);
				}
			}
		}
		return super.updateCourseResult(courseResult);
	}

}