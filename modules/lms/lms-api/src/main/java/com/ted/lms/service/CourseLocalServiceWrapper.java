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
 * Provides a wrapper for {@link CourseLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see CourseLocalService
 * @generated
 */
@ProviderType
public class CourseLocalServiceWrapper implements CourseLocalService,
	ServiceWrapper<CourseLocalService> {
	public CourseLocalServiceWrapper(CourseLocalService courseLocalService) {
		_courseLocalService = courseLocalService;
	}

	@Override
	public com.liferay.portal.kernel.repository.model.Folder addAttachmentsFolder(
		long userId, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _courseLocalService.addAttachmentsFolder(userId, groupId);
	}

	/**
	* Adds the course to the database. Also notifies the appropriate model listeners.
	*
	* @param course the course
	* @return the course that was added
	*/
	@Override
	public com.ted.lms.model.Course addCourse(com.ted.lms.model.Course course) {
		return _courseLocalService.addCourse(course);
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
	*/
	@Override
	public com.ted.lms.model.Course addCourse(
		java.util.Map<java.util.Locale, String> titleMap,
		java.util.Map<java.util.Locale, String> descriptionMap,
		java.util.Map<java.util.Locale, String> summaryMap, boolean indexer,
		java.util.Map<java.util.Locale, String> friendlyURLMap,
		long layoutSetPrototypeId, long parentCourseId,
		com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector smallImageSelector,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {
		return _courseLocalService.addCourse(titleMap, descriptionMap,
			summaryMap, indexer, friendlyURLMap, layoutSetPrototypeId,
			parentCourseId, smallImageSelector, serviceContext);
	}

	@Override
	public long addOriginalImageFileEntry(long userId, long groupId,
		long entryId,
		com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector imageSelector)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _courseLocalService.addOriginalImageFileEntry(userId, groupId,
			entryId, imageSelector);
	}

	/**
	* Método para buscar cursos
	*/
	@Override
	public int countCourses(long companyId, String freeText, String language,
		int status, long parentCourseId, long groupId,
		java.util.LinkedHashMap<String, Object> params) {
		return _courseLocalService.countCourses(companyId, freeText, language,
			status, parentCourseId, groupId, params);
	}

	/**
	* Método para buscar cursos
	*/
	@Override
	public int countCourses(long companyId, String title, String description,
		String language, int status, long parentCourseId, long groupId,
		java.util.LinkedHashMap<String, Object> params, boolean andOperator) {
		return _courseLocalService.countCourses(companyId, title, description,
			language, status, parentCourseId, groupId, params, andOperator);
	}

	/**
	* Creates a new course with the primary key. Does not add the course to the database.
	*
	* @param courseId the primary key for the new course
	* @return the new course
	*/
	@Override
	public com.ted.lms.model.Course createCourse(long courseId) {
		return _courseLocalService.createCourse(courseId);
	}

	/**
	* Deletes the course from the database. Also notifies the appropriate model listeners.
	*
	* @param course the course
	* @return the course that was removed
	*/
	@Override
	public com.ted.lms.model.Course deleteCourse(
		com.ted.lms.model.Course course) {
		return _courseLocalService.deleteCourse(course);
	}

	/**
	* Deletes the course with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param courseId the primary key of the course
	* @return the course that was removed
	* @throws PortalException if a course with the primary key could not be found
	*/
	@Override
	public com.ted.lms.model.Course deleteCourse(long courseId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _courseLocalService.deleteCourse(courseId);
	}

	@Override
	public void deleteCourses(long groupId) {
		_courseLocalService.deleteCourses(groupId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _courseLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _courseLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _courseLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _courseLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _courseLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _courseLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _courseLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.ted.lms.model.CourseResult enrollStudent(
		com.ted.lms.model.Course course, long userId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext,
		com.liferay.portal.kernel.security.permission.PermissionChecker permissionChecker)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.ted.lms.exception.InscriptionException {
		return _courseLocalService.enrollStudent(course, userId,
			serviceContext, permissionChecker);
	}

	@Override
	public com.liferay.portal.kernel.repository.model.Folder fetchAttachmentsFolder(
		long userId, long groupId) {
		return _courseLocalService.fetchAttachmentsFolder(userId, groupId);
	}

	@Override
	public com.ted.lms.model.Course fetchCourse(long courseId) {
		return _courseLocalService.fetchCourse(courseId);
	}

	/**
	* Returns the course matching the UUID and group.
	*
	* @param uuid the course's UUID
	* @param groupId the primary key of the group
	* @return the matching course, or <code>null</code> if a matching course could not be found
	*/
	@Override
	public com.ted.lms.model.Course fetchCourseByUuidAndGroupId(String uuid,
		long groupId) {
		return _courseLocalService.fetchCourseByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _courseLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List<com.ted.lms.model.Course> getChildsRegistredUser(
		long parentCourseId, long userId) {
		return _courseLocalService.getChildsRegistredUser(parentCourseId, userId);
	}

	/**
	* Returns the course with the primary key.
	*
	* @param courseId the primary key of the course
	* @return the course
	* @throws PortalException if a course with the primary key could not be found
	*/
	@Override
	public com.ted.lms.model.Course getCourse(long courseId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _courseLocalService.getCourse(courseId);
	}

	@Override
	public com.ted.lms.model.Course getCourseByGroupCreatedId(
		long groupCreatedId) {
		return _courseLocalService.getCourseByGroupCreatedId(groupCreatedId);
	}

	/**
	* Returns the course matching the UUID and group.
	*
	* @param uuid the course's UUID
	* @param groupId the primary key of the group
	* @return the matching course
	* @throws PortalException if a matching course could not be found
	*/
	@Override
	public com.ted.lms.model.Course getCourseByUuidAndGroupId(String uuid,
		long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _courseLocalService.getCourseByUuidAndGroupId(uuid, groupId);
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
	@Override
	public java.util.List<com.ted.lms.model.Course> getCourses(int start,
		int end) {
		return _courseLocalService.getCourses(start, end);
	}

	/**
	* Returns all the courses matching the UUID and company.
	*
	* @param uuid the UUID of the courses
	* @param companyId the primary key of the company
	* @return the matching courses, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.ted.lms.model.Course> getCoursesByUuidAndCompanyId(
		String uuid, long companyId) {
		return _courseLocalService.getCoursesByUuidAndCompanyId(uuid, companyId);
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
	@Override
	public java.util.List<com.ted.lms.model.Course> getCoursesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.ted.lms.model.Course> orderByComparator) {
		return _courseLocalService.getCoursesByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of courses.
	*
	* @return the number of courses
	*/
	@Override
	public int getCoursesCount() {
		return _courseLocalService.getCoursesCount();
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.Group> getDistinctCourseGroups(
		long companyId) {
		return _courseLocalService.getDistinctCourseGroups(companyId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _courseLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _courseLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _courseLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _courseLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Método para buscar cursos
	*/
	@Override
	public java.util.List<com.ted.lms.model.Course> searchCourses(
		long companyId, String freeText, String language, int status,
		long parentCourseId, long groupId,
		java.util.LinkedHashMap<String, Object> params, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.ted.lms.model.Course> obc) {
		return _courseLocalService.searchCourses(companyId, freeText, language,
			status, parentCourseId, groupId, params, start, end, obc);
	}

	/**
	* Método para buscar cursos
	*/
	@Override
	public java.util.List<com.ted.lms.model.Course> searchCourses(
		long companyId, String title, String description, String language,
		int status, long parentCourseId, long groupId,
		java.util.LinkedHashMap<String, Object> params, boolean andOperator,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.ted.lms.model.Course> obc) {
		return _courseLocalService.searchCourses(companyId, title, description,
			language, status, parentCourseId, groupId, params, andOperator,
			start, end, obc);
	}

	@Override
	public boolean unsubscribeStudent(com.ted.lms.model.Course course,
		long userId,
		com.liferay.portal.kernel.security.permission.PermissionChecker permissionChecker)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _courseLocalService.unsubscribeStudent(course, userId,
			permissionChecker);
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
	@Override
	public void updateAsset(long userId, com.ted.lms.model.Course course,
		long[] assetCategoryIds, String[] assetTagNames,
		long[] assetLinkEntryIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		_courseLocalService.updateAsset(userId, course, assetCategoryIds,
			assetTagNames, assetLinkEntryIds);
	}

	/**
	* Updates the course in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param course the course
	* @return the course that was updated
	*/
	@Override
	public com.ted.lms.model.Course updateCourse(
		com.ted.lms.model.Course course) {
		return _courseLocalService.updateCourse(course);
	}

	/**
	* Actualiza el paso de los mensajes de la modificación de curso
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
	* @throws NoSuchCourseException
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
		throws com.ted.lms.exception.NoSuchCourseException {
		return _courseLocalService.updateCourse(courseId, welcome,
			welcomeSubjectMap, welcomeMsgMap, goodbye, goodbyeSubjectMap,
			goodbyeMsgMap, deniedInscription, deniedInscriptionSubjectMap,
			deniedInscriptionMsgMap, status, serviceContext);
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
	@Override
	public com.ted.lms.model.Course updateCourse(long courseId,
		java.util.Date registrationStartDate,
		java.util.Date registrationEndDate, java.util.Date executionStartDate,
		java.util.Date executionEndDate, int typeSite, long inscriptionType,
		long courseEvalId, long calificationType, int maxUsers, int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _courseLocalService.updateCourse(courseId,
			registrationStartDate, registrationEndDate, executionStartDate,
			executionEndDate, typeSite, inscriptionType, courseEvalId,
			calificationType, maxUsers, status, serviceContext);
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
		return _courseLocalService.updateCourse(courseId,
			registrationStartMonth, registrationStartDay,
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
	@Override
	public com.ted.lms.model.Course updateCourse(long courseId, int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.security.auth.PrincipalException,
			com.liferay.portal.kernel.exception.PortalException {
		return _courseLocalService.updateCourse(courseId, status, serviceContext);
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
	@Override
	public com.ted.lms.model.Course updateCourse(long courseId,
		java.util.Map<java.util.Locale, String> titleMap,
		java.util.Map<java.util.Locale, String> descriptionMap,
		java.util.Map<java.util.Locale, String> summaryMap, boolean indexer,
		java.util.Map<java.util.Locale, String> friendlyURLMap,
		long layoutSetPrototypeId,
		com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector smallImageSelector,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws Exception {
		return _courseLocalService.updateCourse(courseId, titleMap,
			descriptionMap, summaryMap, indexer, friendlyURLMap,
			layoutSetPrototypeId, smallImageSelector, serviceContext);
	}

	/**
	* Modifica la url de un curso
	*
	* @param groupCreatedId identificador del sitio web del curso
	* @param friendlyURL nueva url
	* @throws PortalException
	*/
	@Override
	public void updateFriendlyURL(long groupCreatedId, String friendlyURL)
		throws com.liferay.portal.kernel.exception.PortalException {
		_courseLocalService.updateFriendlyURL(groupCreatedId, friendlyURL);
	}

	@Override
	public void updateSmallImage(long courseId,
		com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector smallImageSelector,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		_courseLocalService.updateSmallImage(courseId, smallImageSelector,
			serviceContext);
	}

	@Override
	public CourseLocalService getWrappedService() {
		return _courseLocalService;
	}

	@Override
	public void setWrappedService(CourseLocalService courseLocalService) {
		_courseLocalService = courseLocalService;
	}

	private CourseLocalService _courseLocalService;
}