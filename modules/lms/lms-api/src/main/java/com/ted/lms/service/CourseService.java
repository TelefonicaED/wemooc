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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector;
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

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the remote service interface for Course. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see CourseServiceUtil
 * @generated
 */
@AccessControlled
@JSONWebService
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface CourseService extends BaseService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CourseServiceUtil} to access the course remote service. Add custom service methods to <code>com.ted.lms.service.impl.CourseServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
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
	public Course addCourse(
			long groupId, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap, Map<Locale, String> summaryMap,
			boolean indexer, String friendlyURL, long layoutSetPrototypeId,
			long parentCourseId, long courseTypeId,
			ImageSelector smallImageSelector, ServiceContext serviceContext)
		throws Exception;

	public void addUserCourse(long courseId, long[] addUserIds, long roleId)
		throws PortalException;

	public int countCourses(
		long companyId, String title, String description, String language,
		int[] status, long parentCourseId, long groupId,
		LinkedHashMap<String, Object> params, boolean andOperator);

	public void deleteCourse(long courseId) throws PortalException;

	public long executeCopyCourse(
			long courseId, long parentCourseId, Map<Locale, String> titleMap,
			Map<Locale, String> friendlyURLMap, long layoutSetPrototypeId,
			Date registrationStartDate, Date registrationEndDate,
			Date executionStartDate, Date executionEndDate, boolean copyForum,
			boolean copyDocuments, ServiceContext serviceContext)
		throws PortalException;

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	public Course moveEntryToTrash(long courseId) throws PortalException;

	public void restoreEntryFromTrash(long courseId) throws PortalException;

	/**
	 * M�todo para buscar cursos
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Course> searchCourses(
		long companyId, String title, String description, String language,
		int[] status, long parentCourseId, long groupId,
		LinkedHashMap<String, Object> params, boolean andOperator, int start,
		int end, OrderByComparator<Course> obc);

	public void unsetUserCourse(
			long courseId, long[] removeUserIds, long roleId,
			ServiceContext serviceContext)
		throws PortalException;

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
	public Course updateCourse(
			long courseId, boolean welcome,
			Map<Locale, String> welcomeSubjectMap,
			Map<Locale, String> welcomeMsgMap, boolean goodbye,
			Map<Locale, String> goodbyeSubjectMap,
			Map<Locale, String> goodbyeMsgMap, boolean deniedInscription,
			Map<Locale, String> deniedInscriptionSubjectMap,
			Map<Locale, String> deniedInscriptionMsgMap, int status,
			ServiceContext serviceContext)
		throws PortalException, PrincipalException;

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
	public Course updateCourse(
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
			ServiceContext serviceContext)
		throws PortalException;

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
	public Course updateCourse(
			long courseId, int status, ServiceContext serviceContext)
		throws PortalException, PrincipalException;

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
	public Course updateCourse(
			long courseId, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap, Map<Locale, String> summaryMap,
			boolean indexer, String friendlyURL, long layoutSetPrototypeId,
			ImageSelector smallImageImageSelector,
			ServiceContext serviceContext)
		throws Exception;

	public void updateSmallImage(
			long courseId, String imageString, String imageTitle,
			String imageMimeType, ServiceContext serviceContext)
		throws PortalException, PrincipalException;

}