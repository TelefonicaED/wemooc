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
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.social.kernel.model.SocialActivityConstants;
import com.liferay.social.kernel.service.SocialActivityLocalServiceUtil;
import com.ted.lms.constants.StudentParams;
import com.ted.lms.exception.InscriptionException;
import com.ted.lms.model.Course;
import com.ted.lms.model.CourseResult;
import com.ted.lms.service.base.StudentLocalServiceBaseImpl;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * The implementation of the student local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ted.lms.service.StudentLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see StudentLocalServiceBaseImpl
 * @see com.ted.lms.service.StudentLocalServiceUtil
 */
public class StudentLocalServiceImpl extends StudentLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.ted.lms.service.StudentLocalServiceUtil} to access the student local service.
	 */
	
	private static final Log log = LogFactoryUtil.getLog(StudentLocalServiceImpl.class);
	
	public CourseResult enrollStudent(Course course, long userId, ServiceContext serviceContext, PermissionChecker permissionChecker ) throws PortalException, InscriptionException {
		
		CourseResult courseResult = null;
		
		if(course.canEnroll(userId, serviceContext.getLocale(), permissionChecker)) {
			Group group = groupLocalService.getGroup(course.getGroupCreatedId());
			if(group.getType()==GroupConstants.TYPE_SITE_OPEN){
				Role sitemember=RoleLocalServiceUtil.getRole(course.getCompanyId(), RoleConstants.SITE_MEMBER) ;
				
			/*	if(teamId>0){
        			long[] userIds = new long[1];
        			userIds[0] = userId;	
        			if(!UserLocalServiceUtil.hasTeamUser(teamId, userId)){
        				UserLocalServiceUtil.addTeamUsers(teamId, userIds);	
        			}			
        		}*/
				
				GroupLocalServiceUtil.addUserGroups(userId, new long[]{group.getGroupId()});
				UserGroupRoleLocalServiceUtil.addUserGroupRoles(new long[] { userId }, group.getGroupId(), sitemember.getRoleId());
				SocialActivityLocalServiceUtil.addActivity(userId, group.getGroupId(), Group.class.getName(), group.getGroupId(), SocialActivityConstants.TYPE_SUBSCRIBE, "", userId);
				
				courseResult = courseResultLocalService.getByCourseIdUserId(course.getCourseId(), userId);
				if(courseResult == null) {
					courseResultLocalService.addCourseResult(course.getCourseId(), userId, serviceContext);
				}
				
				log.debug("Inscribimos usuario con id: " + userId + " en la comunidad con id: " + group.getGroupId() + " (" + group.getName() + ")");
			}else if(group.getType()==GroupConstants.TYPE_SITE_RESTRICTED && !MembershipRequestLocalServiceUtil.hasMembershipRequest(userId, group.getGroupId(), MembershipRequestConstants.STATUS_PENDING)){
				MembershipRequestLocalServiceUtil.addMembershipRequest(userId, group.getGroupId(), "Enroll petition", serviceContext);
				SocialActivityLocalServiceUtil.addActivity(userId, group.getGroupId(), Group.class.getName(), group.getGroupId(), SocialActivityConstants.TYPE_SUBSCRIBE, "", userId);
				log.debug("Lanzamos peticion de inscripcion del usuario " + userId + " en la comunidad con id: " + group.getGroupId() + " (" + group.getName() + ")");
			}
		}else {
			courseResult = courseResultPersistence.fetchByCourseIdUserId(course.getCourseId(), userId);
			//Debería tenerlo, pero lo creo si no lo tiene
			if(courseResult == null) {
				courseResult = courseResultLocalService.addCourseResult(course.getCourseId(), userId, serviceContext);
			}
		}
		return courseResult;
	}
	
	public boolean unsubscribeStudent(Course course, long userId, PermissionChecker permissionChecker) throws PortalException {
		boolean resultUnsubscribe = true;
		//Eliminamos la membresia
		log.debug("CourseLocalServiceImpl::unsubscribeStudent::courseId::" + course.getCourseId());
		log.debug("CourseLocalServiceImpl::unsubscribeStudent::userId::" + userId);
		if (course.canUnsubscribe(userId, permissionChecker)) {
			long[] groupIds = new long[] {course.getGroupCreatedId()};
			GroupLocalServiceUtil.unsetUserGroups(userId, groupIds);
			SocialActivityLocalServiceUtil.addActivity(userId, course.getGroupId(), Course.class.getName(), course.getCourseId(), SocialActivityConstants.TYPE_UNSUBSCRIBE, "", course.getUserId());
			
		}else {
			resultUnsubscribe = false;
		}
		return resultUnsubscribe;
	}
	
	/**
	 * Usar este método para contar los estudiantes de un curso
	 * @param courseId id del curso
	 * @param companyId id de company
	 * @param screenName nombre de usuario
	 * @param firstName nombre
	 * @param lastName apellido
	 * @param emailAddress direccion de correo
	 * @param status estado del usuario (WorkflowConstants)
	 * @param andOperator true si queremos que coincidan screenname, firstname, lastname y emailaddress, false en caso contrario
	 * @return
	 */
	public int countStudentsFromCourse(long courseId, long companyId, String screenName, String firstName, String lastName, String emailAddress, int status, 
			LinkedHashMap<String,Object> params, boolean andOperator){
		return courseFinder.countStudents(courseId, companyId, screenName, firstName, lastName, emailAddress, status, null, params, andOperator);
	}
	
	public int countStudentsFromCourse(long courseId, long companyId, String keywords, int status, 
			LinkedHashMap<String,Object> params){
		return courseFinder.countStudents(courseId, companyId, keywords, keywords, keywords, keywords, status, null, params, false);
	}
	
	public int countStudentsFromCourse(long courseId, long companyId) {
		return countStudentsFromCourse(courseId, companyId, null, null, null, null, WorkflowConstants.STATUS_APPROVED, null, true);
	}
	
	public List<User> getStudentsFromCourse(long courseId, long companyId, String screenName, String firstName, String lastName, String emailAddress, int status, 
			LinkedHashMap<String,Object> params, boolean andOperator, int start, int end, OrderByComparator obc){
		return courseFinder.findStudents(courseId, companyId, screenName, firstName, lastName, emailAddress, status, null, params, andOperator, start, end, obc);
			
	}
	
	public List<User> getStudentsFromCourse(long courseId, long companyId, String keywords, int status, 
			LinkedHashMap<String,Object> params, int start, int end, OrderByComparator obc){
		return courseFinder.findStudents(courseId, companyId, keywords, keywords, keywords, keywords, status, null, params, false, start, end, obc);
	}
	
	public int countStudentsFinished(long courseId, long companyId, String keywords, int status, 
			LinkedHashMap<String,Object> params, int start, int end) {
		if(params == null) {
			params = new LinkedHashMap<String,Object>();
		}
		params.put(StudentParams.PARAM_FINISHED, "");
		
		return courseFinder.countStudents(courseId, companyId, keywords, keywords, keywords, keywords, status, null, params, false);
	}
}