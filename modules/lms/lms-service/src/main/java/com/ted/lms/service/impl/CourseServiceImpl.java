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
import com.liferay.portal.kernel.util.OrderByComparator;
import com.ted.lms.constants.LMSActionKeys;
import com.ted.lms.constants.LMSConstants;
import com.ted.lms.model.Course;
import com.ted.lms.service.base.CourseServiceBaseImpl;

import java.util.Date;
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
	 * @param summaryMap resumen del curso con las traducciones
	 * @param indexer si el curso se mostrará en las búsquedas
	 * @param friendlyURL url del curso, si es vacío se autogenera a partir del nombre
	 * @param layoutSetPrototypeId identificador de la plantilla de sitio web
	 * @param parentCourseId identificador del curso padre, si es cero se considera curso padre
	 * @param ImageSelector imagen selector
	 * @param serviceContext contexto de la creación del curso
	 * @throws Exception 
	 */
	@Override
	public Course addCourse(long groupId, Map<Locale,String> titleMap, Map<Locale,String> descriptionMap,  Map<Locale,String> summaryMap, boolean indexer, 
			Map<Locale, String> friendlyURLMap, long layoutSetPrototypeId, long parentCourseId, long courseTypeId, ImageSelector smallImageSelector, 
			ServiceContext serviceContext) throws Exception {
		
		portletResourcePermission.check(
				getPermissionChecker(), serviceContext.getScopeGroupId(),
				LMSActionKeys.ADD_COURSE);
		
		return courseLocalService.addCourse(getUserId(), groupId, titleMap, descriptionMap, summaryMap, indexer, friendlyURLMap, layoutSetPrototypeId,
				parentCourseId, courseTypeId, smallImageSelector, serviceContext);
		
	}
	
	/**
	 * Actualiza un curso
	 * @param courseId identificador del curso
	 * @param titleMap título del curso con las traducciones
	 * @param descriptionMap descripción del curso con las traducciones
	 * @param summaryMap resumen del curso con las traducciones
	 * @param indexer si el curso se mostrará en las búsquedas
	 * @param friendlyURL url del curso, si es vacío se autogenera a partir del nombre
	 * @param ImageSelector imagen selector
	 * @param serviceContext contexto de la creación del curso
	 * @throws PortalException 
	 * @throws PrincipalException 
	 */
	public Course updateCourse(long courseId, Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
			Map<Locale, String> summaryMap, boolean indexer, Map<Locale, String> friendlyURLMap, long layoutSetPrototypeId, 
			ImageSelector smallImageImageSelector, ServiceContext serviceContext) throws Exception {
		
		courseModelResourcePermission.check(getPermissionChecker(), courseId, ActionKeys.UPDATE);
		
		return courseLocalService.updateCourse(getUserId(), courseId, titleMap, descriptionMap, summaryMap, indexer, friendlyURLMap, layoutSetPrototypeId,
				smallImageImageSelector, serviceContext);
	}
	
	/**
	 * Actualiza el segundo paso de un curso
	 * @param courseId identificador del curso
	 * @param registrationStartMonth mes de la fecha de inicio de inscripción
	 * @param registrationStartDay día de la fecha de inicio de inscripción
	 * @param registrationStartYear año de la fecha de inicio de inscripción
	 * @param registrationStartHour hora de la fecha de inicio de inscripción
	 * @param registrationStartMinute minuto de la fecha de inicio de inscripción
	 * @param registrationEndMonth mes de la fecha de fin de inscripción
	 * @param registrationEndDay día de la fecha de fin de inscripción
	 * @param registrationEndYear año de la fecha de fin de inscripción
	 * @param registrationEndHour hora de la fecha de fin de inscripción
	 * @param registrationEndMinute minuto de la fecha de fin de inscripción
	 * @param executionStartMonth mes de la fecha de inicio de ejecución
	 * @param executionStartDay día de la fecha de inicio de ejecución
	 * @param executionStartYear año de la fecha de inicio de ejecución
	 * @param executionStartHour hora de la fecha de inicio de ejecución
	 * @param executionStartMinute minuto de la fecha de inicio de ejecución
	 * @param executionEndMonth mes de la fecha de fin de ejecución
	 * @param executionEndDay día de la fecha de fin de ejecución
	 * @param executionEndYear año de la fecha de fin de ejecución
	 * @param executionEndHour hora de la fecha de fin de ejecución
	 * @param executionEndMinute minuto de la fecha de fin de ejecución
	 * @param typeSite tipo de sitio (público, restringido y privado). Ver GroupConstants
	 * @param inscriptionType tipo de inscripción
	 * @param courseEvalId método de evaluación
	 * @param calificationType tipo de calificación
	 * @param maxUsers máximo de usuarios que se permite inscribir en el curso
	 * @param status estado del curso (ver WorkflowConstants)
	 * @param serviceContext contexto de la modificación del curso
	 * @throws Exception 
	 * @return curso modificado
	 */
	public Course updateCourse(long courseId, int registrationStartMonth, int registrationStartDay, int registrationStartYear, int registrationStartHour, 
			int registrationStartMinute, int registrationEndMonth, int registrationEndDay, int registrationEndYear, int registrationEndHour, 
			int registrationEndMinute, int executionStartMonth, int executionStartDay, int executionStartYear, int executionStartHour, 
			int executionStartMinute, int executionEndMonth, int executionEndDay, int executionEndYear, int executionEndHour, int executionEndMinute, 
			int typeSite, long inscriptionType, long courseEvalId, long calificationType, int maxUsers, int status,
			ServiceContext serviceContext) throws PortalException {
		
		courseModelResourcePermission.check(getPermissionChecker(), courseId, ActionKeys.UPDATE);
		
		return courseLocalService.updateCourse(getUserId(), courseId, registrationStartMonth, registrationStartDay, registrationStartYear, registrationStartHour, 
				registrationStartMinute, registrationEndMonth, registrationEndDay, registrationEndYear, registrationEndHour, registrationEndMinute, 
				executionStartMonth, executionStartDay, executionStartYear, executionStartHour, executionStartMinute, executionEndMonth, executionEndDay, 
				executionEndYear, executionEndHour, executionEndMinute, typeSite, inscriptionType, courseEvalId, calificationType, 
				maxUsers, status, serviceContext);
	}
	
	/**
	 * 
	 * @param courseId identificador del curso
	 * @param welcome si se habilita el mensaje de bienvenida
	 * @param welcomeSubjectMap asunto del mensaje de bienvenida
	 * @param welcomeMsgMap cuerpo del mensaje de bienvenida
	 * @param goodbye si se habilita el mensaje de despedida
	 * @param goodbyeSubjectMap asunto del mensaje de despedida
	 * @param goodbyeMsgMap cuerpo del mensaje de despedida
	 * @param serviceContext contexto de la modificación del curso
	 * @return curso modificado
	 * @throws PortalException 
	 * @throws PrincipalException 
	 */
	@Override
	public Course updateCourse(long courseId, boolean welcome, Map<Locale, String> welcomeSubjectMap,
			Map<Locale, String> welcomeMsgMap, boolean goodbye, Map<Locale, String> goodbyeSubjectMap,
			Map<Locale, String> goodbyeMsgMap, boolean deniedInscription, Map<Locale, String> deniedInscriptionSubjectMap,
			Map<Locale, String> deniedInscriptionMsgMap, int status, ServiceContext serviceContext) throws PrincipalException, PortalException {
		
		courseModelResourcePermission.check(getPermissionChecker(), courseId, ActionKeys.UPDATE);
		
		return courseLocalService.updateCourse(getUserId(), courseId, welcome, welcomeSubjectMap, welcomeMsgMap, goodbye, goodbyeSubjectMap, goodbyeMsgMap, 
				deniedInscription, deniedInscriptionSubjectMap, deniedInscriptionMsgMap, status);
		
	}
	
	/**
	 * Actualiza los contenidos relacionados y el estado
	 * @param courseId identificador del curso
	 * @param status estado del curso
	 * @param serviceContext contexto de la modificación del curso
	 * @return curso modificado
	 * @throws PrincipalException
	 * @throws PortalException
	 */
	public Course updateCourse(long courseId, int status, ServiceContext serviceContext) throws PrincipalException, PortalException {
		courseModelResourcePermission.check(getPermissionChecker(), courseId, ActionKeys.UPDATE);
		
		return courseLocalService.updateCourse(getUserId(), courseId, status, serviceContext);
	}
	
	@Override
	public void deleteCourse(long courseId) throws PortalException {
		courseModelResourcePermission.check(getPermissionChecker(), courseId, ActionKeys.DELETE);

		courseLocalService.deleteCourse(courseId);
	}
	
	@Override
	public Course moveEntryToTrash(long courseId) throws PortalException {
		courseModelResourcePermission.check(getPermissionChecker(), courseId, ActionKeys.DELETE);

		return courseLocalService.moveEntryToTrash(getUserId(), courseId);
	}

	@Override
	public void restoreEntryFromTrash(long courseId) throws PortalException {
		courseModelResourcePermission.check(getPermissionChecker(), courseId, ActionKeys.DELETE);

		courseLocalService.restoreEntryFromTrash(getUserId(), courseId);
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
	 * M�todo para buscar cursos
	 */
	public List<Course> searchCourses(long companyId, String title, String description, String language, int[] status, long parentCourseId, long groupId, 
			LinkedHashMap<String, Object> params, boolean andOperator, int start, int end,
			OrderByComparator<Course> obc){
		return courseFinder.filterByC(companyId, title, description, language, status, parentCourseId, groupId, params, andOperator, start, end, obc);
	}
	
	public int countCourses(long companyId, String title, String description, String language, int[] status, long parentCourseId, long groupId, LinkedHashMap<String, Object> params, 
			boolean andOperator){
		return courseFinder.filterCountByC(companyId, title, description, language, status, parentCourseId, groupId, params, andOperator);
	}

	public long executeCopyCourse(long courseId, long courseParentId, Map<Locale, String> titleMap, long layoutSetPrototypeId, 
			Date registrationStartDate, Date registrationEndDate, Date executionStartDate, Date executionEndDate,
			boolean copyForum, boolean copyDocuments, ServiceContext serviceContext) throws PortalException {
		
		portletResourcePermission.check(
				getPermissionChecker(), serviceContext.getScopeGroupId(),
				LMSActionKeys.ADD_COURSE);
		
		return courseLocalService.executeCopyCourse(courseId, courseParentId, titleMap, layoutSetPrototypeId, 
				registrationStartDate, registrationEndDate, executionStartDate, executionEndDate, 
				copyForum, copyDocuments, serviceContext);
	}
}