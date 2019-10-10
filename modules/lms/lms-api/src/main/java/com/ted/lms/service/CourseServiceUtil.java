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

import org.osgi.annotation.versioning.ProviderType;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for Course. This utility wraps
 * <code>com.ted.lms.service.impl.CourseServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see CourseService
 * @generated
 */
@ProviderType
public class CourseServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.ted.lms.service.impl.CourseServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

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
	 * @throws Exception
	 */
	public static com.ted.lms.model.Course addCourse(
			long groupId, java.util.Map<java.util.Locale, String> titleMap,
			java.util.Map<java.util.Locale, String> descriptionMap,
			java.util.Map<java.util.Locale, String> summaryMap, boolean indexer,
			String friendlyURL, long layoutSetPrototypeId, long parentCourseId,
			long courseTypeId,
			com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector
				smallImageSelector,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws Exception {

		return getService().addCourse(
			groupId, titleMap, descriptionMap, summaryMap, indexer, friendlyURL,
			layoutSetPrototypeId, parentCourseId, courseTypeId,
			smallImageSelector, serviceContext);
	}

	public static void addUserCourse(
			long courseId, long[] addUserIds, long roleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().addUserCourse(courseId, addUserIds, roleId);
	}

	public static int countCourses(
		long companyId, String title, String description, String language,
		int[] status, long parentCourseId, long groupId,
		java.util.LinkedHashMap<String, Object> params, boolean andOperator) {

		return getService().countCourses(
			companyId, title, description, language, status, parentCourseId,
			groupId, params, andOperator);
	}

	public static void deleteCourse(long courseId)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().deleteCourse(courseId);
	}

	public static long executeCopyCourse(
			long courseId, long parentCourseId,
			java.util.Map<java.util.Locale, String> titleMap,
			java.util.Map<java.util.Locale, String> friendlyURLMap,
			long layoutSetPrototypeId, java.util.Date registrationStartDate,
			java.util.Date registrationEndDate,
			java.util.Date executionStartDate, java.util.Date executionEndDate,
			boolean copyForum, boolean copyDocuments,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().executeCopyCourse(
			courseId, parentCourseId, titleMap, friendlyURLMap,
			layoutSetPrototypeId, registrationStartDate, registrationEndDate,
			executionStartDate, executionEndDate, copyForum, copyDocuments,
			serviceContext);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.ted.lms.model.Course moveEntryToTrash(long courseId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().moveEntryToTrash(courseId);
	}

	public static void restoreEntryFromTrash(long courseId)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().restoreEntryFromTrash(courseId);
	}

	/**
	 * M�todo para buscar cursos
	 */
	public static java.util.List<com.ted.lms.model.Course> searchCourses(
		long companyId, String title, String description, String language,
		int[] status, long parentCourseId, long groupId,
		java.util.LinkedHashMap<String, Object> params, boolean andOperator,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<com.ted.lms.model.Course> obc) {

		return getService().searchCourses(
			companyId, title, description, language, status, parentCourseId,
			groupId, params, andOperator, start, end, obc);
	}

	public static void unsetUserCourse(
			long courseId, long[] removeUserIds, long roleId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().unsetUserCourse(
			courseId, removeUserIds, roleId, serviceContext);
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
	public static com.ted.lms.model.Course updateCourse(
			long courseId, boolean welcome,
			java.util.Map<java.util.Locale, String> welcomeSubjectMap,
			java.util.Map<java.util.Locale, String> welcomeMsgMap,
			boolean goodbye,
			java.util.Map<java.util.Locale, String> goodbyeSubjectMap,
			java.util.Map<java.util.Locale, String> goodbyeMsgMap,
			boolean deniedInscription,
			java.util.Map<java.util.Locale, String> deniedInscriptionSubjectMap,
			java.util.Map<java.util.Locale, String> deniedInscriptionMsgMap,
			int status,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.security.auth.PrincipalException {

		return getService().updateCourse(
			courseId, welcome, welcomeSubjectMap, welcomeMsgMap, goodbye,
			goodbyeSubjectMap, goodbyeMsgMap, deniedInscription,
			deniedInscriptionSubjectMap, deniedInscriptionMsgMap, status,
			serviceContext);
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
	public static com.ted.lms.model.Course updateCourse(
			long courseId, int registrationStartMonth, int registrationStartDay,
			int registrationStartYear, int registrationStartHour,
			int registrationStartMinute, int registrationEndMonth,
			int registrationEndDay, int registrationEndYear,
			int registrationEndHour, int registrationEndMinute,
			int executionStartMonth, int executionStartDay,
			int executionStartYear, int executionStartHour,
			int executionStartMinute, int executionEndMonth,
			int executionEndDay, int executionEndYear, int executionEndHour,
			int executionEndMinute, int typeSite, long inscriptionType,
			long courseEvalId, long calificationType, int maxUsers, int status,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateCourse(
			courseId, registrationStartMonth, registrationStartDay,
			registrationStartYear, registrationStartHour,
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
	public static com.ted.lms.model.Course updateCourse(
			long courseId, int status,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.security.auth.PrincipalException {

		return getService().updateCourse(courseId, status, serviceContext);
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
	public static com.ted.lms.model.Course updateCourse(
			long courseId, java.util.Map<java.util.Locale, String> titleMap,
			java.util.Map<java.util.Locale, String> descriptionMap,
			java.util.Map<java.util.Locale, String> summaryMap, boolean indexer,
			String friendlyURL, long layoutSetPrototypeId,
			com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector
				smallImageImageSelector,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws Exception {

		return getService().updateCourse(
			courseId, titleMap, descriptionMap, summaryMap, indexer,
			friendlyURL, layoutSetPrototypeId, smallImageImageSelector,
			serviceContext);
	}

	public static void updateSmallImage(
			long courseId, String imageString, String imageTitle,
			String imageMimeType,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.security.auth.PrincipalException {

		getService().updateSmallImage(
			courseId, imageString, imageTitle, imageMimeType, serviceContext);
	}

	public static CourseService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CourseService, CourseService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CourseService.class);

		ServiceTracker<CourseService, CourseService> serviceTracker =
			new ServiceTracker<CourseService, CourseService>(
				bundle.getBundleContext(), CourseService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}