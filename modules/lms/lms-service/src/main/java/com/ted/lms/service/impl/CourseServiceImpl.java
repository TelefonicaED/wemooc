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
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermissionFactory;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.ted.lms.constants.LMSActionKeys;
import com.ted.lms.constants.LMSConstants;
import com.ted.lms.model.Course;
import com.ted.lms.service.base.CourseServiceBaseImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
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
	
	private static final Log log = LogFactoryUtil.getLog(CourseLocalServiceImpl.class);
	
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
	
	/**
	 * Crea un nuevo curso
	 * @param titleMap título del curso con las traducciones
	 * @param descriptionMap descripción del curso con las traducciones
	 * @param summary resumen del curso
	 * @param friendlyURL url del curso, si es vacío se autogenera a partir del nombre
	 * @param parentCourseId identificador del curso padre, si es cero se considera curso padre
	 * @param smallImageId identificador de la imagen del curso
	 * @param registrationStartDate fecha de inicio de inscripción
	 * @param registrationEndDate fecha de fin de inscripción
	 * @param executionStartDate fecha de inicio de ejecución
	 * @param executionEndDate fecha de fin de ejecución
	 * @param layoutSetPrototypeId identificador de la plantilla de sitio web que tendrá el curso
	 * @param typeSite tipo de sitio web (consultar constantes de GroupConstants que comienzan por TYPE_SITE)
	 * @param inscriptionType tipo de inscripción al curso
	 * @param courseEvalId tipo de evaluación del curso
	 * @param calificationType tipo de calificación del curso
	 * @param maxUsers máximo de usuarios que se pueden inscribir al curso
	 * @param welcome si se les enviará un mensaje de bienvenida a los usuarios cuando se inscriban al curso
	 * @param welcomeSubject asunto del mensaje de bienvenida
	 * @param welcomeMsg cuerpo del mensaje de bienvenida
	 * @param goodbye si se les enviará un mensaje de despedida a los usuarios cuanso se desinscriban del curso
	 * @param goodbyeSubject asunto del mensaje de despedida
	 * @param goodbyeMsg cuerpo del mensaje de despedida
	 * @param status estado del curso cuando lo creamos (consultar los estados de Workflow)
	 * @param serviceContext contexto de la creación del curso
	 */
	public Course addCourse(Map<Locale,String> titleMap, Map<Locale,String> descriptionMap, String summary, String friendlyURL, long parentCourseId, 
			ImageSelector smallImageSelector, Date registrationStartDate, Date registrationEndDate, Date executionStartDate, Date executionEndDate, long layoutSetPrototypeId,int typeSite, 
			long inscriptionType, long courseEvalId, long calificationType, int maxUsers, boolean welcome, String welcomeSubject, String welcomeMsg, 
			boolean goodbye, String goodbyeSubject, String goodbyeMsg, int status, ServiceContext serviceContext) throws PortalException {
		
		portletResourcePermission.check(
				getPermissionChecker(), serviceContext.getScopeGroupId(),
				LMSActionKeys.ADD_COURSE);
		
		return courseLocalService.addCourse(titleMap, descriptionMap, summary, friendlyURL, parentCourseId, smallImageSelector, registrationStartDate, 
				registrationEndDate, executionStartDate, executionEndDate, layoutSetPrototypeId, typeSite, inscriptionType, courseEvalId, calificationType, 
				maxUsers, welcome, welcomeSubject, welcomeMsg, goodbye, goodbyeSubject, goodbyeMsg, status, serviceContext);
		
	}
	
	/**
	 * Crea un nuevo curso
	 * @param title título del curso
	 * @param description descripción del curso
	 * @param summary resumen del curso
	 * @param friendlyURL url del curso, si es vacío se autogenera a partir del nombre
	 * @param parentCourseId identificador del curso padre, si es cero se considera curso padre
	 * @param registrationStartDate fecha de inicio de inscripción
	 * @param registrationEndDate fecha de fin de inscripción
	 * @param executionStartDate fecha de inicio de ejecución
	 * @param executionEndDate fecha de fin de ejecución
	 * @param layoutSetPrototypeId identificador de la plantilla de sitio web que tendrá el curso
	 * @param typeSite tipo de sitio web (consultar constantes de GroupConstants que comienzan por TYPE_SITE)
	 * @param inscriptionType tipo de inscripción al curso
	 * @param courseEvalId tipo de evaluación del curso
	 * @param calificationType tipo de calificación del curso
	 * @param maxUsers máximo de usuarios que se pueden inscribir al curso
	 * @param welcome si se les enviará un mensaje de bienvenida a los usuarios cuando se inscriban al curso
	 * @param welcomeSubject asunto del mensaje de bienvenida
	 * @param welcomeMsg cuerpo del mensaje de bienvenida
	 * @param goodbye si se les enviará un mensaje de despedida a los usuarios cuanso se desinscriban del curso
	 * @param goodbyeSubject asunto del mensaje de despedida
	 * @param goodbyeMsg cuerpo del mensaje de despedida
	 * @param status estado del curso cuando lo creamos (consultar los estados de Workflow)
	 * @param serviceContext contexto de la creación del curso
	 */
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
		
		return courseLocalService.addCourse(titleMap, descriptionMap, summary, friendlyURL, parentCourseId, null, registrationStartDate, 
				registrationEndDate, executionStartDate, executionEndDate, layoutSetPrototypeId, typeSite, inscriptionType, courseEvalId, calificationType, 
				maxUsers, welcome, welcomeSubject, welcomeMsg, goodbye, goodbyeSubject, goodbyeMsg, status, serviceContext);
	}
	
	public void updateSmallImage(long courseId, String imageString, String imageTitle, String imageMimeType, ServiceContext serviceContext) throws PrincipalException, PortalException {
		courseModelResourcePermission.check(getPermissionChecker(), courseId, ActionKeys.UPDATE);
		
		log.debug("CourseServiceImpl::updateSmallImage::" +  courseId + "::" + imageString);

		byte[] imageBytes = Base64.decode(imageString);
		
		ImageSelector smallImageSelector = new ImageSelector(imageBytes, imageTitle, imageMimeType, null);
		
		log.debug("CourseServiceImpl::updateSmallImage::ImageSelector" + smallImageSelector.getImageBytes());
		
		courseLocalService.updateSmallImage(courseId,smallImageSelector, serviceContext);
		
	}
	
	/**
	 * Método para buscar cursos
	 */
	public List<Course> searchCourses(long companyId, String title, String description, String language, int status, long parentCourseId, long groupId, 
			LinkedHashMap<String, Object> params, boolean andOperator, int start, int end,
			OrderByComparator<Course> obc){
		return courseFinder.filterByC(companyId, title, description, language, status, parentCourseId, groupId, params, andOperator, start, end, obc);
	}
	
	public int countCourses(long companyId, String title, String description, String language, int status, long parentCourseId, long groupId, LinkedHashMap<String, Object> params, 
			boolean andOperator){
		return courseFinder.filterCountByC(companyId, title, description, language, status, parentCourseId, groupId, params, andOperator);
	}
}