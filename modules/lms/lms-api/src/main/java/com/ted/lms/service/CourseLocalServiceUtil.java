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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for Course. This utility wraps
 * {@link com.ted.lms.service.impl.CourseLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see CourseLocalService
 * @see com.ted.lms.service.base.CourseLocalServiceBaseImpl
 * @see com.ted.lms.service.impl.CourseLocalServiceImpl
 * @generated
 */
@ProviderType
public class CourseLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.ted.lms.service.impl.CourseLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.kernel.repository.model.Folder addAttachmentsFolder(
		long userId, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().addAttachmentsFolder(userId, groupId);
	}

	/**
	* Adds the course to the database. Also notifies the appropriate model listeners.
	*
	* @param course the course
	* @return the course that was added
	*/
	public static com.ted.lms.model.Course addCourse(
		com.ted.lms.model.Course course) {
		return getService().addCourse(course);
	}

	/**
	* Crea un nuevo curso
	*
	* @param titleMap título del curso con las traducciones
	* @param descriptionMap descripción del curso con las traducciones
	* @param summaryMap resumen del curso con las traducciones
	* @param indexer si el curso se muestra en las búsquedas
	* @param friendlyURLMap url del curso, si es vacío se autogenera a partir del nombre con las traducciones
	* @param layoutSetPrototypeId identificador de la plantilla de sitio web
	* @param parentCourseId identificador del curso padre, si es cero se considera curso padre
	* @param smallImageImageSelector imagen seleccionada para el curso
	* @param serviceContext contexto de la creación del curso
	* @throws Exception
	*/
	public static com.ted.lms.model.Course addCourse(long userId, long groupId,
		java.util.Map<java.util.Locale, String> titleMap,
		java.util.Map<java.util.Locale, String> descriptionMap,
		java.util.Map<java.util.Locale, String> summaryMap, boolean indexer,
		java.util.Map<java.util.Locale, String> friendlyURLMap,
		long layoutSetPrototypeId, long parentCourseId, long courseTypeId,
		com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector smallImageSelector,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws Exception {
		return getService()
				   .addCourse(userId, groupId, titleMap, descriptionMap,
			summaryMap, indexer, friendlyURLMap, layoutSetPrototypeId,
			parentCourseId, courseTypeId, smallImageSelector, serviceContext);
	}

	public static long addOriginalImageFileEntry(long userId, long groupId,
		long entryId,
		com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector imageSelector)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addOriginalImageFileEntry(userId, groupId, entryId,
			imageSelector);
	}

	/**
	* A esta función no se le debe llamar para inscribir alumnos teniendo en cuenta el método de inscripción,
	* únicamente es para inscribir como administrador a usuarios en cursos
	*
	* @param userId identifador del usuario que está inscribiendo
	* @param groupId identificador del grupo del curso
	* @param addUserIds usuarios que se quieren añadir al curso
	* @param roleId identificador del rol que queremos dar
	* @param ServiceContext contexto de la petición
	* @throws PortalException
	*/
	public static void addUserCourse(long userId, long courseId,
		long[] addUserIds, long roleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().addUserCourse(userId, courseId, addUserIds, roleId);
	}

	public static com.ted.lms.model.Course copyCourse(long userId,
		long courseId, long parentCourseId, String title,
		java.util.Map<java.util.Locale, String> friendlyURLMap,
		long layoutSetPrototypeId, java.util.Date registrationStartDate,
		java.util.Date registrationEndDate, java.util.Date executionStartDate,
		java.util.Date executionEndDate, boolean copyForum,
		boolean copyDocuments,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws Exception {
		return getService()
				   .copyCourse(userId, courseId, parentCourseId, title,
			friendlyURLMap, layoutSetPrototypeId, registrationStartDate,
			registrationEndDate, executionStartDate, executionEndDate,
			copyForum, copyDocuments, serviceContext);
	}

	/**
	* Método para buscar cursos
	*/
	public static int countCourses(long companyId, String freeText,
		String language, int[] status, long parentCourseId, long groupId,
		java.util.LinkedHashMap<String, Object> params) {
		return getService()
				   .countCourses(companyId, freeText, language, status,
			parentCourseId, groupId, params);
	}

	/**
	* Método para buscar cursos
	*/
	public static int countCourses(long companyId, String title,
		String description, String language, int[] status, long parentCourseId,
		long groupId, java.util.LinkedHashMap<String, Object> params,
		boolean andOperator) {
		return getService()
				   .countCourses(companyId, title, description, language,
			status, parentCourseId, groupId, params, andOperator);
	}

	/**
	* Creates a new course with the primary key. Does not add the course to the database.
	*
	* @param courseId the primary key for the new course
	* @return the new course
	*/
	public static com.ted.lms.model.Course createCourse(long courseId) {
		return getService().createCourse(courseId);
	}

	/**
	* Deletes the course from the database. Also notifies the appropriate model listeners.
	*
	* @param course the course
	* @return the course that was removed
	* @throws PortalException
	*/
	public static com.ted.lms.model.Course deleteCourse(
		com.ted.lms.model.Course course)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteCourse(course);
	}

	/**
	* Deletes the course with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param courseId the primary key of the course
	* @return the course that was removed
	* @throws PortalException if a course with the primary key could not be found
	*/
	public static com.ted.lms.model.Course deleteCourse(long courseId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteCourse(courseId);
	}

	public static void deleteCourses(long groupId) {
		getService().deleteCourses(groupId);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ted.lms.model.impl.CourseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ted.lms.model.impl.CourseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static long executeCopyCourse(long courseId, long parentCourseId,
		java.util.Map<java.util.Locale, String> titleMap,
		java.util.Map<java.util.Locale, String> friendlyURLMap,
		long layoutSetPrototypeId, java.util.Date registrationStartDate,
		java.util.Date registrationEndDate, java.util.Date executionStartDate,
		java.util.Date executionEndDate, boolean copyForum,
		boolean copyDocuments,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .executeCopyCourse(courseId, parentCourseId, titleMap,
			friendlyURLMap, layoutSetPrototypeId, registrationStartDate,
			registrationEndDate, executionStartDate, executionEndDate,
			copyForum, copyDocuments, serviceContext);
	}

	public static com.liferay.portal.kernel.repository.model.Folder fetchAttachmentsFolder(
		long userId, long groupId) {
		return getService().fetchAttachmentsFolder(userId, groupId);
	}

	public static com.ted.lms.model.Course fetchCourse(long courseId) {
		return getService().fetchCourse(courseId);
	}

	/**
	* Returns the course matching the UUID and group.
	*
	* @param uuid the course's UUID
	* @param groupId the primary key of the group
	* @return the matching course, or <code>null</code> if a matching course could not be found
	*/
	public static com.ted.lms.model.Course fetchCourseByUuidAndGroupId(
		String uuid, long groupId) {
		return getService().fetchCourseByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static boolean getAllowAccessToCompletedCourses(long companyId) {
		return getService().getAllowAccessToCompletedCourses(companyId);
	}

	public static java.util.List<com.ted.lms.model.Course> getChildsRegistredUser(
		long parentCourseId, long userId) {
		return getService().getChildsRegistredUser(parentCourseId, userId);
	}

	/**
	* Returns the course with the primary key.
	*
	* @param courseId the primary key of the course
	* @return the course
	* @throws PortalException if a course with the primary key could not be found
	*/
	public static com.ted.lms.model.Course getCourse(long courseId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getCourse(courseId);
	}

	public static com.ted.lms.model.Course getCourseByGroupCreatedId(
		long groupCreatedId) {
		return getService().getCourseByGroupCreatedId(groupCreatedId);
	}

	/**
	* Returns the course matching the UUID and group.
	*
	* @param uuid the course's UUID
	* @param groupId the primary key of the group
	* @return the matching course
	* @throws PortalException if a matching course could not be found
	*/
	public static com.ted.lms.model.Course getCourseByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getCourseByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the courses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ted.lms.model.impl.CourseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of courses
	* @param end the upper bound of the range of courses (not inclusive)
	* @return the range of courses
	*/
	public static java.util.List<com.ted.lms.model.Course> getCourses(
		int start, int end) {
		return getService().getCourses(start, end);
	}

	/**
	* Returns all the courses matching the UUID and company.
	*
	* @param uuid the UUID of the courses
	* @param companyId the primary key of the company
	* @return the matching courses, or an empty list if no matches were found
	*/
	public static java.util.List<com.ted.lms.model.Course> getCoursesByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService().getCoursesByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of courses matching the UUID and company.
	*
	* @param uuid the UUID of the courses
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of courses
	* @param end the upper bound of the range of courses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching courses, or an empty list if no matches were found
	*/
	public static java.util.List<com.ted.lms.model.Course> getCoursesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.ted.lms.model.Course> orderByComparator) {
		return getService()
				   .getCoursesByUuidAndCompanyId(uuid, companyId, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of courses.
	*
	* @return the number of courses
	*/
	public static int getCoursesCount() {
		return getService().getCoursesCount();
	}

	public static java.util.List<com.liferay.portal.kernel.model.Group> getDistinctCourseGroups(
		long companyId) {
		return getService().getDistinctCourseGroups(companyId);
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	public static String[] getPrerequisiteActivities(long companyId) {
		return getService().getPrerequisiteActivities(companyId);
	}

	public static String[] getPrerequisiteCourses(long companyId) {
		return getService().getPrerequisiteCourses(companyId);
	}

	public static String[] getPrerequisiteModules(long companyId) {
		return getService().getPrerequisiteModules(companyId);
	}

	public static com.liferay.portal.kernel.json.JSONObject importCourseMembers(
		long userId, long courseId, long roleId,
		com.liferay.portal.kernel.repository.model.FileEntry fileEntry)
		throws com.liferay.portal.kernel.exception.PortalException,
			java.io.IOException {
		return getService()
				   .importCourseMembers(userId, courseId, roleId, fileEntry);
	}

	public static com.liferay.portal.kernel.json.JSONObject importEditions(
		long userId, long courseId,
		com.liferay.portal.kernel.repository.model.FileEntry fileEntry)
		throws com.liferay.portal.kernel.exception.PortalException,
			java.io.IOException {
		return getService().importEditions(userId, courseId, fileEntry);
	}

	public static com.ted.lms.model.Course moveEntryToTrash(long userId,
		com.ted.lms.model.Course course)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().moveEntryToTrash(userId, course);
	}

	public static com.ted.lms.model.Course moveEntryToTrash(long userId,
		long courseId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().moveEntryToTrash(userId, courseId);
	}

	public static com.ted.lms.model.Course restoreEntryFromTrash(long userId,
		long courseId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().restoreEntryFromTrash(userId, courseId);
	}

	/**
	* Método para buscar cursos
	*/
	public static java.util.List<com.ted.lms.model.Course> searchCourses(
		long companyId, String freeText, String language, int[] status,
		long parentCourseId, long groupId,
		java.util.LinkedHashMap<String, Object> params, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.ted.lms.model.Course> obc) {
		return getService()
				   .searchCourses(companyId, freeText, language, status,
			parentCourseId, groupId, params, start, end, obc);
	}

	/**
	* Método para buscar cursos
	*/
	public static java.util.List<com.ted.lms.model.Course> searchCourses(
		long companyId, String title, String description, String language,
		int[] status, long parentCourseId, long groupId,
		java.util.LinkedHashMap<String, Object> params, boolean andOperator,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.ted.lms.model.Course> obc) {
		return getService()
				   .searchCourses(companyId, title, description, language,
			status, parentCourseId, groupId, params, andOperator, start, end,
			obc);
	}

	/**
	* A esta función se le debe llamar cuando un administrador desinscriba a un usuario
	*
	* @param courseId identificador del curso
	* @param removeUserIds usuarios a desinscribir
	* @param roleId identificador del rol del que se le va a desinscrbir
	* @param serviceContext
	* @throws PortalException
	*/
	public static void unsetUserCourse(long courseId, long[] removeUserIds,
		long roleId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.unsetUserCourse(courseId, removeUserIds, roleId, serviceContext);
	}

	/**
	* Actualiza el estado del asset correspondiente al curso
	*
	* @param userId identificador del usuario que modifica
	* @param course curso que se modifica
	* @param assetCategoryIds identificadores de categorías que añadiremos al asset del curso
	* @param assetTagNames etiquetas que añadiremos al asset del curso
	* @param assetLinkEntryIds contenidos relacionados que añadiremos al asset del curso
	* @param priority prioridad del curso en las búsquedas
	* @param summary resumen del curso
	*/
	public static void updateAsset(long userId,
		com.ted.lms.model.Course course, long[] assetCategoryIds,
		String[] assetTagNames, long[] assetLinkEntryIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.updateAsset(userId, course, assetCategoryIds, assetTagNames,
			assetLinkEntryIds);
	}

	/**
	* Updates the course in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param course the course
	* @return the course that was updated
	*/
	public static com.ted.lms.model.Course updateCourse(
		com.ted.lms.model.Course course) {
		return getService().updateCourse(course);
	}

	/**
	* Actualiza el paso de los mensajes de la modificación de curso
	*
	* @param courseId identificador del curso
	* @param welcome si se habilita el mensaje de bienvenida
	* @param welcomeSubjectMap asunto del mensaje de bienvenida con las traducciones
	* @param welcomeMsgMap cuerpo del mensaje de bienvenida con las traducciones
	* @param goodbye si se habilita el mensaje de despedida
	* @param goodbyeSubjectMap asunto del mensaje de despedida con las traducciones
	* @param goodbyeMsgMap cuerpo del mensaje de despedida con las traducciones
	* @param deniedInscription si se habilita el mensaje de denegaci�n de la inscripci�n
	* @param deniedInscriptionSubjectMap asunto del mensaje de denegaci�n de la inscripci�n con las traducciones
	* @param deniedInscriptionMsgMap cuerpod el mensaje de denegaci�n de la inscripci�n con las traducciones
	* @param status estado del curso
	* @param serviceContext contexto de la modificación del curso
	* @return curso modificado
	* @throws PortalException
	*/
	public static com.ted.lms.model.Course updateCourse(long userId,
		long courseId, boolean welcome,
		java.util.Map<java.util.Locale, String> welcomeSubjectMap,
		java.util.Map<java.util.Locale, String> welcomeMsgMap, boolean goodbye,
		java.util.Map<java.util.Locale, String> goodbyeSubjectMap,
		java.util.Map<java.util.Locale, String> goodbyeMsgMap,
		boolean deniedInscription,
		java.util.Map<java.util.Locale, String> deniedInscriptionSubjectMap,
		java.util.Map<java.util.Locale, String> deniedInscriptionMsgMap,
		int status) throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateCourse(userId, courseId, welcome, welcomeSubjectMap,
			welcomeMsgMap, goodbye, goodbyeSubjectMap, goodbyeMsgMap,
			deniedInscription, deniedInscriptionSubjectMap,
			deniedInscriptionMsgMap, status);
	}

	/**
	* Actualiza el segundo paso de un curso
	*
	* @param courseId identificador del curso
	* @param registrationStartDate fecha de inicio de inscripción
	* @param registrationEndDate fecha de fin de inscripción
	* @param executionStartDate fecha de inicio de ejecución
	* @param executionEndDate fecha de fin de ejecución
	* @param typeSite tipo de sitio (público, restringido y privado). Ver GroupConstants
	* @param inscriptionType tipo de inscripción
	* @param courseEvalId método de evaluación
	* @param calificationType tipo de calificación
	* @param maxUsers máximo de usuarios que se permite inscribir en el curso
	* @param status estado del curso (ver WorkflowConstants)
	* @param serviceContext contexto de la modificación del curso
	* @throws Exception
	* @return curso modificado
	* @throws PortalException
	*/
	public static com.ted.lms.model.Course updateCourse(long userId,
		long courseId, java.util.Date registrationStartDate,
		java.util.Date registrationEndDate, java.util.Date executionStartDate,
		java.util.Date executionEndDate, int typeSite, long inscriptionType,
		long courseEvalId, long calificationType, int maxUsers, int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateCourse(userId, courseId, registrationStartDate,
			registrationEndDate, executionStartDate, executionEndDate,
			typeSite, inscriptionType, courseEvalId, calificationType,
			maxUsers, status, serviceContext);
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
	public static com.ted.lms.model.Course updateCourse(long userId,
		long courseId, int registrationStartMonth, int registrationStartDay,
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
		return getService()
				   .updateCourse(userId, courseId, registrationStartMonth,
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
	public static com.ted.lms.model.Course updateCourse(long userId,
		long courseId, int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.security.auth.PrincipalException,
			com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateCourse(userId, courseId, status, serviceContext);
	}

	/**
	* Actualiza el paso de descripción de la modificación de curso
	*
	* @param courseId identificador del curso
	* @param titleMap título del curso con las traducciones
	* @param descriptionMap descripción del curso con las traducciones
	* @param summaryMap resumen del curso con las traducciones
	* @param indexer si el curso aparece las búsquedas
	* @param friendlyURL url para el curso
	* @param smallImageSelector selector con la imagen de la imagen del curso
	* @param serviceContext contexto de modificación del curso
	* @return curso modificado
	* @throws PortalException
	*/
	public static com.ted.lms.model.Course updateCourse(long userId,
		long courseId, java.util.Map<java.util.Locale, String> titleMap,
		java.util.Map<java.util.Locale, String> descriptionMap,
		java.util.Map<java.util.Locale, String> summaryMap, boolean indexer,
		java.util.Map<java.util.Locale, String> friendlyURLMap,
		long layoutSetPrototypeId,
		com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector smallImageSelector,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws Exception {
		return getService()
				   .updateCourse(userId, courseId, titleMap, descriptionMap,
			summaryMap, indexer, friendlyURLMap, layoutSetPrototypeId,
			smallImageSelector, serviceContext);
	}

	/**
	* Modifica la url de un curso
	*
	* @param groupCreatedId identificador del sitio web del curso
	* @param friendlyURL nueva url
	* @throws PortalException
	*/
	public static void updateFriendlyURL(long groupCreatedId, String friendlyURL)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().updateFriendlyURL(groupCreatedId, friendlyURL);
	}

	public static void updateSmallImage(long courseId,
		com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector smallImageSelector,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.updateSmallImage(courseId, smallImageSelector, serviceContext);
	}

	public static com.ted.lms.model.Course updateStatus(long userId,
		long courseId, int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateStatus(userId, courseId, status, serviceContext);
	}

	public static CourseLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CourseLocalService, CourseLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CourseLocalService.class);

		ServiceTracker<CourseLocalService, CourseLocalService> serviceTracker = new ServiceTracker<CourseLocalService, CourseLocalService>(bundle.getBundleContext(),
				CourseLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}