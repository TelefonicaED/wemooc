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
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.MembershipRequestLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.social.kernel.model.SocialActivityConstants;
import com.liferay.social.kernel.service.SocialActivityLocalServiceUtil;
import com.ted.lms.constants.LMSActionKeys;
import com.ted.lms.constants.LMSRoleConstants;
import com.ted.lms.constants.StudentParams;
import com.ted.lms.exception.InscriptionException;
import com.ted.lms.model.Course;
import com.ted.lms.model.CourseResult;
import com.ted.lms.security.permission.resource.CoursePermission;
import com.ted.lms.service.CourseResultLocalServiceUtil;
import com.ted.lms.service.base.StudentLocalServiceBaseImpl;
import com.ted.prerequisite.model.Prerequisite;
import com.ted.prerequisite.service.PrerequisiteRelationLocalServiceUtil;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

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
	
	/**
	 * Este método realiza las comprobaciones necesarias en base al método de inscripción seleccionado en el curso e inscribe al usuario,
	 * se debe llamar cuando es el mismo estudiante el que se está inscribiendo en el curso
	 */
	public CourseResult enrollStudent(Course course, long studentId, ServiceContext serviceContext, PermissionChecker permissionChecker ) throws PortalException, InscriptionException {
		
		CourseResult courseResult = null;
		
		if(course.canEnroll(studentId, serviceContext.getLocale(), permissionChecker)) {
			Group group = groupLocalService.getGroup(course.getGroupCreatedId());
			if(group.getType()==GroupConstants.TYPE_SITE_OPEN){
				Role student = RoleLocalServiceUtil.getRole(course.getCompanyId(), LMSRoleConstants.STUDENT) ;
				
				courseLocalService.addUserCourse(studentId, course.getCourseId(), new long[] { studentId }, student.getRoleId());
				
				SocialActivityLocalServiceUtil.addActivity(studentId, group.getGroupId(), Group.class.getName(), group.getGroupId(), SocialActivityConstants.TYPE_SUBSCRIBE, "", studentId);
				
				log.debug("Inscribimos usuario con id: " + studentId + " en la comunidad con id: " + group.getGroupId() + " (" + group.getName() + ")");
			}else if(group.getType()==GroupConstants.TYPE_SITE_RESTRICTED && !MembershipRequestLocalServiceUtil.hasMembershipRequest(studentId, group.getGroupId(), MembershipRequestConstants.STATUS_PENDING)){
				MembershipRequestLocalServiceUtil.addMembershipRequest(studentId, group.getGroupId(), "Enroll petition", serviceContext);
				SocialActivityLocalServiceUtil.addActivity(studentId, group.getGroupId(), Group.class.getName(), group.getGroupId(), SocialActivityConstants.TYPE_SUBSCRIBE, "", studentId);
				log.debug("Lanzamos peticion de inscripcion del usuario " + studentId + " en la comunidad con id: " + group.getGroupId() + " (" + group.getName() + ")");
			}
		}else {
			courseResult = courseResultPersistence.fetchByCourseIdUserId(course.getCourseId(), studentId);
			//Debería tenerlo, pero lo creo si no lo tiene
			if(courseResult == null) {
				courseResult = courseResultLocalService.addCourseResult(studentId, course.getCourseId(), studentId);
			}
		}
		return courseResult;
	}
	
	/**
	 * Este método se usa para desinscribir a un usuario de un curso teniendo en cuenta el método de inscripción
	 */
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
	
	public boolean canUnsubscribe(Course course, long userId, PermissionChecker permissionChecker) throws PortalException {
		Date now = new Date();
			
		if (GroupLocalServiceUtil.hasUserGroup(userId, course.getGroupCreatedId()) && course.getRegistrationStartDate().before(now) && 
				course.getRegistrationEndDate().after(now) && CoursePermission.contains(permissionChecker, course, LMSActionKeys.REGISTER)) {
			CourseResult courseResult = CourseResultLocalServiceUtil.getCourseResult(course.getCourseId(), userId); 
			Group group = GroupLocalServiceUtil.getGroup(course.getGroupCreatedId());

			return courseResult == null || courseResult.getPassedDate() == null || (group.getType() == GroupConstants.TYPE_SITE_OPEN);
		}else {
			return false;
		}
	}
	
	public boolean canEnroll(Course course, long userId, Locale locale, PermissionChecker permissionChecker) throws PortalException, InscriptionException {
		//1.Comprobamos que no esté ya inscrito
		if(!GroupLocalServiceUtil.hasUserGroup(userId, course.getGroupCreatedId())) {
			
			Date now = new Date();
	
			if(CoursePermission.contains(permissionChecker, course, LMSActionKeys.REGISTER)){
				//2. Fecha actual dentro del periodo de inscripcion
				if((course.getRegistrationStartDate().before(now) && course.getRegistrationEndDate().after(now))){
	
					//3.Comprobamos que se cumplan todos los prerequisitos
					List<Prerequisite> listPrerequiste = PrerequisiteRelationLocalServiceUtil.getPrerequisites(PortalUtil.getClassNameId(Course.class.getName()), course.getCourseId());
					boolean isPassed = true;
					int i = 0;
					while(isPassed && listPrerequiste.size() > i) {
						isPassed = listPrerequiste.get(i).isPassed(userId);
						i++;
					}
					if(isPassed) {
						// 4. El mÃ¡ximo de inscripciones del curso no ha sido superado
						if(course.getMaxUsers()<=0 || UserLocalServiceUtil.getGroupUsersCount(course.getGroupCreatedId()) < course.getMaxUsers()){
							//5. Comprobamos el tipo de inscripción
							Group group = GroupLocalServiceUtil.getGroup(course.getGroupCreatedId());
							if(group.getType()==GroupConstants.TYPE_SITE_OPEN){
								return true;
							}else{
								if(group.getType()==GroupConstants.TYPE_SITE_RESTRICTED){
									if(!MembershipRequestLocalServiceUtil.hasMembershipRequest(userId, group.getGroupId(), MembershipRequestConstants.STATUS_PENDING)){
										return true;
									}
								}else{
									if(group.getType()==GroupConstants.TYPE_SITE_PRIVATE){
										//Debería lanzar una excepción indicando que es privado y que no se puede
										throw new InscriptionException("site-private", LanguageUtil.get(locale, "inscription.error.site-private"));
									}
								}
							}
						}else {
							throw new InscriptionException("max-users", LanguageUtil.get(locale, "inscription.error.max-users"));
						}
					}else {
						//Debería lanzar una excepción indicando que no se cumplen los prerequisitos
						throw new InscriptionException("prerequisites", LanguageUtil.get(locale, "inscription.error.prerequisites"));
					}
				}else {
					//Debería lanzar una excepción indicando que está en periodo de ejecución
					throw new InscriptionException("registration-dates", LanguageUtil.get(locale, "inscription.error.registration-dates"));
				}
			}else {
				throw new InscriptionException("permission-register", LanguageUtil.get(locale, "inscription.error.permission-register"));
			}
			return false;
		}else {
			return false;
		}
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