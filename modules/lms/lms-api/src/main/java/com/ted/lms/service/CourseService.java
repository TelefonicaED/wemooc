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
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.ted.lms.model.Course;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
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

	/**
	* Crea un nuevo curso
	*
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
	public Course addCourse(Map<Locale, String> titleMap,
		Map<Locale, String> descriptionMap, String summary, String friendlyURL,
		long parentCourseId, ImageSelector smallImageSelector,
		Date registrationStartDate, Date registrationEndDate,
		Date executionStartDate, Date executionEndDate,
		long layoutSetPrototypeId, int typeSite, long inscriptionType,
		long courseEvalId, long calificationType, int maxUsers,
		boolean welcome, String welcomeSubject, String welcomeMsg,
		boolean goodbye, String goodbyeSubject, String goodbyeMsg, int status,
		ServiceContext serviceContext) throws PortalException;

	/**
	* Crea un nuevo curso
	*
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
	public Course addCourse(String title, String description, String summary,
		String friendlyURL, long parentCourseId, Date registrationStartDate,
		Date registrationEndDate, Date executionStartDate,
		Date executionEndDate, long layoutSetPrototypeId, int typeSite,
		long inscriptionType, long courseEvalId, long calificationType,
		int maxUsers, boolean welcome, String welcomeSubject,
		String welcomeMsg, boolean goodbye, String goodbyeSubject,
		String goodbyeMsg, int status, ServiceContext serviceContext)
		throws PortalException;

	public int countCourses(long companyId, String title, String description,
		String language, int status, long parentCourseId, long groupId,
		LinkedHashMap<String, Object> params, boolean andOperator);

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public String getOSGiServiceIdentifier();

	/**
	* Método para buscar cursos
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Course> searchCourses(long companyId, String title,
		String description, String language, int status, long parentCourseId,
		long groupId, LinkedHashMap<String, Object> params,
		boolean andOperator, int start, int end, OrderByComparator<Course> obc);

	public void updateSmallImage(long courseId, String imageString,
		String imageTitle, String imageMimeType, ServiceContext serviceContext)
		throws PrincipalException, PortalException;
}