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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CourseService}.
 *
 * @author Brian Wing Shun Chan
 * @see CourseService
 * @generated
 */
@ProviderType
public class CourseServiceWrapper implements CourseService,
	ServiceWrapper<CourseService> {
	public CourseServiceWrapper(CourseService courseService) {
		_courseService = courseService;
	}

	/**
	* Crea un nuevo curso
	*
	* @param titleMap título del curso con las traducciones
	* @param descriptionMap descripción del curso con las traducciones
	* @param summaryMap resumen del curso con las traducciones
	* @param indexer si el curso se mostrará en las búsquedas
	* @param friendlyURL url del curso, si es vacío se autogenera a partir del nombre
	* @param layoutSetPrototypeId identificador de la plantilla de sitio web
	* @param parentCourseId identificador del curso padre, si es cero se considera curso padre
	* @param ImageSelector imagen selector
	* @param serviceContext contexto de la creación del curso
	*/
	@Override
	public com.ted.lms.model.Course addCourse(
		java.util.Map<java.util.Locale, String> titleMap,
		java.util.Map<java.util.Locale, String> descriptionMap,
		java.util.Map<java.util.Locale, String> summaryMap, boolean indexer,
		java.util.Map<java.util.Locale, String> friendlyURLMap,
		long layoutSetPrototypeId, long parentCourseId,
		com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector smallImageSelector,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _courseService.addCourse(titleMap, descriptionMap, summaryMap,
			indexer, friendlyURLMap, layoutSetPrototypeId, parentCourseId,
			smallImageSelector, serviceContext);
	}

	@Override
	public int countCourses(long companyId, String title, String description,
		String language, int status, long parentCourseId, long groupId,
		java.util.LinkedHashMap<String, Object> params, boolean andOperator) {
		return _courseService.countCourses(companyId, title, description,
			language, status, parentCourseId, groupId, params, andOperator);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _courseService.getOSGiServiceIdentifier();
	}

	/**
	* M�todo para buscar cursos
	*/
	@Override
	public java.util.List<com.ted.lms.model.Course> searchCourses(
		long companyId, String title, String description, String language,
		int status, long parentCourseId, long groupId,
		java.util.LinkedHashMap<String, Object> params, boolean andOperator,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.ted.lms.model.Course> obc) {
		return _courseService.searchCourses(companyId, title, description,
			language, status, parentCourseId, groupId, params, andOperator,
			start, end, obc);
	}

	/**
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
	public com.ted.lms.model.Course updateCourse(long courseId,
		boolean welcome,
		java.util.Map<java.util.Locale, String> welcomeSubjectMap,
		java.util.Map<java.util.Locale, String> welcomeMsgMap, boolean goodbye,
		java.util.Map<java.util.Locale, String> goodbyeSubjectMap,
		java.util.Map<java.util.Locale, String> goodbyeMsgMap,
		boolean deniedInscription,
		java.util.Map<java.util.Locale, String> deniedInscriptionSubjectMap,
		java.util.Map<java.util.Locale, String> deniedInscriptionMsgMap,
		int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.security.auth.PrincipalException,
			com.liferay.portal.kernel.exception.PortalException {
		return _courseService.updateCourse(courseId, welcome,
			welcomeSubjectMap, welcomeMsgMap, goodbye, goodbyeSubjectMap,
			goodbyeMsgMap, deniedInscription, deniedInscriptionSubjectMap,
			deniedInscriptionMsgMap, status, serviceContext);
	}

	/**
	* Actualiza el segundo paso de un curso
	*
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
	@Override
	public com.ted.lms.model.Course updateCourse(long courseId,
		int registrationStartMonth, int registrationStartDay,
		int registrationStartYear, int registrationStartHour,
		int registrationStartMinute, int registrationEndMonth,
		int registrationEndDay, int registrationEndYear,
		int registrationEndHour, int registrationEndMinute,
		int executionStartMonth, int executionStartDay, int executionStartYear,
		int executionStartHour, int executionStartMinute,
		int executionEndMonth, int executionEndDay, int executionEndYear,
		int executionEndHour, int executionEndMinute, int typeSite,
		long inscriptionType, long courseEvalId, long calificationType,
		int maxUsers, int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _courseService.updateCourse(courseId, registrationStartMonth,
			registrationStartDay, registrationStartYear, registrationStartHour,
			registrationStartMinute, registrationEndMonth, registrationEndDay,
			registrationEndYear, registrationEndHour, registrationEndMinute,
			executionStartMonth, executionStartDay, executionStartYear,
			executionStartHour, executionStartMinute, executionEndMonth,
			executionEndDay, executionEndYear, executionEndHour,
			executionEndMinute, typeSite, inscriptionType, courseEvalId,
			calificationType, maxUsers, status, serviceContext);
	}

	/**
	* Actualiza los contenidos relacionados y el estado
	*
	* @param courseId identificador del curso
	* @param status estado del curso
	* @param serviceContext contexto de la modificación del curso
	* @return curso modificado
	* @throws PrincipalException
	* @throws PortalException
	*/
	@Override
	public com.ted.lms.model.Course updateCourse(long courseId, int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.security.auth.PrincipalException,
			com.liferay.portal.kernel.exception.PortalException {
		return _courseService.updateCourse(courseId, status, serviceContext);
	}

	/**
	* Actualiza un curso
	*
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
	@Override
	public com.ted.lms.model.Course updateCourse(long courseId,
		java.util.Map<java.util.Locale, String> titleMap,
		java.util.Map<java.util.Locale, String> descriptionMap,
		java.util.Map<java.util.Locale, String> summaryMap, boolean indexer,
		java.util.Map<java.util.Locale, String> friendlyURLMap,
		long layoutSetPrototypeId,
		com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector smallImageImageSelector,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws Exception {
		return _courseService.updateCourse(courseId, titleMap, descriptionMap,
			summaryMap, indexer, friendlyURLMap, layoutSetPrototypeId,
			smallImageImageSelector, serviceContext);
	}

	@Override
	public void updateSmallImage(long courseId, String imageString,
		String imageTitle, String imageMimeType,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.security.auth.PrincipalException,
			com.liferay.portal.kernel.exception.PortalException {
		_courseService.updateSmallImage(courseId, imageString, imageTitle,
			imageMimeType, serviceContext);
	}

	@Override
	public CourseService getWrappedService() {
		return _courseService;
	}

	@Override
	public void setWrappedService(CourseService courseService) {
		_courseService = courseService;
	}

	private CourseService _courseService;
}