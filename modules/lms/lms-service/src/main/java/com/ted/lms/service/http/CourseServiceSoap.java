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

package com.ted.lms.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;

import com.ted.lms.service.CourseServiceUtil;

import java.rmi.RemoteException;

import java.util.Locale;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the SOAP utility for the
 * <code>CourseServiceUtil</code> service
 * utility. The static methods of this class call the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a <code>java.util.List</code>,
 * that is translated to an array of
 * <code>com.ted.lms.model.CourseSoap</code>. If the method in the
 * service utility returns a
 * <code>com.ted.lms.model.Course</code>, that is translated to a
 * <code>com.ted.lms.model.CourseSoap</code>. Methods that SOAP
 * cannot safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CourseServiceHttp
 * @generated
 */
@ProviderType
public class CourseServiceSoap {

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
	public static com.ted.lms.model.CourseSoap addCourse(
			long groupId, String[] titleMapLanguageIds, String[] titleMapValues,
			String[] descriptionMapLanguageIds, String[] descriptionMapValues,
			String[] summaryMapLanguageIds, String[] summaryMapValues,
			boolean indexer, String friendlyURL, long layoutSetPrototypeId,
			long parentCourseId, long courseTypeId,
			com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector
				smallImageSelector,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			Map<Locale, String> titleMap = LocalizationUtil.getLocalizationMap(
				titleMapLanguageIds, titleMapValues);
			Map<Locale, String> descriptionMap =
				LocalizationUtil.getLocalizationMap(
					descriptionMapLanguageIds, descriptionMapValues);
			Map<Locale, String> summaryMap =
				LocalizationUtil.getLocalizationMap(
					summaryMapLanguageIds, summaryMapValues);

			com.ted.lms.model.Course returnValue = CourseServiceUtil.addCourse(
				groupId, titleMap, descriptionMap, summaryMap, indexer,
				friendlyURL, layoutSetPrototypeId, parentCourseId, courseTypeId,
				smallImageSelector, serviceContext);

			return com.ted.lms.model.CourseSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
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
	public static com.ted.lms.model.CourseSoap updateCourse(
			long courseId, String[] titleMapLanguageIds,
			String[] titleMapValues, String[] descriptionMapLanguageIds,
			String[] descriptionMapValues, String[] summaryMapLanguageIds,
			String[] summaryMapValues, boolean indexer, String friendlyURL,
			long layoutSetPrototypeId,
			com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector
				smallImageImageSelector,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			Map<Locale, String> titleMap = LocalizationUtil.getLocalizationMap(
				titleMapLanguageIds, titleMapValues);
			Map<Locale, String> descriptionMap =
				LocalizationUtil.getLocalizationMap(
					descriptionMapLanguageIds, descriptionMapValues);
			Map<Locale, String> summaryMap =
				LocalizationUtil.getLocalizationMap(
					summaryMapLanguageIds, summaryMapValues);

			com.ted.lms.model.Course returnValue =
				CourseServiceUtil.updateCourse(
					courseId, titleMap, descriptionMap, summaryMap, indexer,
					friendlyURL, layoutSetPrototypeId, smallImageImageSelector,
					serviceContext);

			return com.ted.lms.model.CourseSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
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
	public static com.ted.lms.model.CourseSoap updateCourse(
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
		throws RemoteException {

		try {
			com.ted.lms.model.Course returnValue =
				CourseServiceUtil.updateCourse(
					courseId, registrationStartMonth, registrationStartDay,
					registrationStartYear, registrationStartHour,
					registrationStartMinute, registrationEndMonth,
					registrationEndDay, registrationEndYear,
					registrationEndHour, registrationEndMinute,
					executionStartMonth, executionStartDay, executionStartYear,
					executionStartHour, executionStartMinute, executionEndMonth,
					executionEndDay, executionEndYear, executionEndHour,
					executionEndMinute, typeSite, inscriptionType, courseEvalId,
					calificationType, maxUsers, status, serviceContext);

			return com.ted.lms.model.CourseSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
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
	public static com.ted.lms.model.CourseSoap updateCourse(
			long courseId, boolean welcome,
			String[] welcomeSubjectMapLanguageIds,
			String[] welcomeSubjectMapValues, String[] welcomeMsgMapLanguageIds,
			String[] welcomeMsgMapValues, boolean goodbye,
			String[] goodbyeSubjectMapLanguageIds,
			String[] goodbyeSubjectMapValues, String[] goodbyeMsgMapLanguageIds,
			String[] goodbyeMsgMapValues, boolean deniedInscription,
			String[] deniedInscriptionSubjectMapLanguageIds,
			String[] deniedInscriptionSubjectMapValues,
			String[] deniedInscriptionMsgMapLanguageIds,
			String[] deniedInscriptionMsgMapValues, int status,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			Map<Locale, String> welcomeSubjectMap =
				LocalizationUtil.getLocalizationMap(
					welcomeSubjectMapLanguageIds, welcomeSubjectMapValues);
			Map<Locale, String> welcomeMsgMap =
				LocalizationUtil.getLocalizationMap(
					welcomeMsgMapLanguageIds, welcomeMsgMapValues);
			Map<Locale, String> goodbyeSubjectMap =
				LocalizationUtil.getLocalizationMap(
					goodbyeSubjectMapLanguageIds, goodbyeSubjectMapValues);
			Map<Locale, String> goodbyeMsgMap =
				LocalizationUtil.getLocalizationMap(
					goodbyeMsgMapLanguageIds, goodbyeMsgMapValues);
			Map<Locale, String> deniedInscriptionSubjectMap =
				LocalizationUtil.getLocalizationMap(
					deniedInscriptionSubjectMapLanguageIds,
					deniedInscriptionSubjectMapValues);
			Map<Locale, String> deniedInscriptionMsgMap =
				LocalizationUtil.getLocalizationMap(
					deniedInscriptionMsgMapLanguageIds,
					deniedInscriptionMsgMapValues);

			com.ted.lms.model.Course returnValue =
				CourseServiceUtil.updateCourse(
					courseId, welcome, welcomeSubjectMap, welcomeMsgMap,
					goodbye, goodbyeSubjectMap, goodbyeMsgMap,
					deniedInscription, deniedInscriptionSubjectMap,
					deniedInscriptionMsgMap, status, serviceContext);

			return com.ted.lms.model.CourseSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
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
	public static com.ted.lms.model.CourseSoap updateCourse(
			long courseId, int status,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			com.ted.lms.model.Course returnValue =
				CourseServiceUtil.updateCourse(
					courseId, status, serviceContext);

			return com.ted.lms.model.CourseSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deleteCourse(long courseId) throws RemoteException {
		try {
			CourseServiceUtil.deleteCourse(courseId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.ted.lms.model.CourseSoap moveEntryToTrash(long courseId)
		throws RemoteException {

		try {
			com.ted.lms.model.Course returnValue =
				CourseServiceUtil.moveEntryToTrash(courseId);

			return com.ted.lms.model.CourseSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void restoreEntryFromTrash(long courseId)
		throws RemoteException {

		try {
			CourseServiceUtil.restoreEntryFromTrash(courseId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void addUserCourse(
			long courseId, long[] addUserIds, long roleId)
		throws RemoteException {

		try {
			CourseServiceUtil.addUserCourse(courseId, addUserIds, roleId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void unsetUserCourse(
			long courseId, long[] removeUserIds, long roleId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			CourseServiceUtil.unsetUserCourse(
				courseId, removeUserIds, roleId, serviceContext);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void updateSmallImage(
			long courseId, String imageString, String imageTitle,
			String imageMimeType,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			CourseServiceUtil.updateSmallImage(
				courseId, imageString, imageTitle, imageMimeType,
				serviceContext);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static long executeCopyCourse(
			long courseId, long parentCourseId, String[] titleMapLanguageIds,
			String[] titleMapValues, String[] friendlyURLMapLanguageIds,
			String[] friendlyURLMapValues, long layoutSetPrototypeId,
			java.util.Date registrationStartDate,
			java.util.Date registrationEndDate,
			java.util.Date executionStartDate, java.util.Date executionEndDate,
			boolean copyForum, boolean copyDocuments,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			Map<Locale, String> titleMap = LocalizationUtil.getLocalizationMap(
				titleMapLanguageIds, titleMapValues);
			Map<Locale, String> friendlyURLMap =
				LocalizationUtil.getLocalizationMap(
					friendlyURLMapLanguageIds, friendlyURLMapValues);

			long returnValue = CourseServiceUtil.executeCopyCourse(
				courseId, parentCourseId, titleMap, friendlyURLMap,
				layoutSetPrototypeId, registrationStartDate,
				registrationEndDate, executionStartDate, executionEndDate,
				copyForum, copyDocuments, serviceContext);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(CourseServiceSoap.class);

}