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
	* @param summary resumen del curso
	* @param friendlyURL url del curso, si es vacío se autogenera a partir del nombre
	* @param parentCourseId identificador del curso padre, si es cero se considera curso padre
	* @param smallImageImageSelector imagen seleccionada para el curso
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
	public static com.ted.lms.model.Course addCourse(
		java.util.Map<java.util.Locale, String> titleMap,
		java.util.Map<java.util.Locale, String> descriptionMap, String summary,
		String friendlyURL, long parentCourseId,
		com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector smallImageSelector,
		java.util.Date registrationStartDate,
		java.util.Date registrationEndDate, java.util.Date executionStartDate,
		java.util.Date executionEndDate, long layoutSetPrototypeId,
		int typeSite, long inscriptionType, long courseEvalId,
		long calificationType, int maxUsers, boolean welcome,
		String welcomeSubject, String welcomeMsg, boolean goodbye,
		String goodbyeSubject, String goodbyeMsg, int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {
		return getService()
				   .addCourse(titleMap, descriptionMap, summary, friendlyURL,
			parentCourseId, smallImageSelector, registrationStartDate,
			registrationEndDate, executionStartDate, executionEndDate,
			layoutSetPrototypeId, typeSite, inscriptionType, courseEvalId,
			calificationType, maxUsers, welcome, welcomeSubject, welcomeMsg,
			goodbye, goodbyeSubject, goodbyeMsg, status, serviceContext);
	}

	/**
	* Método para buscar cursos
	*/
	public static int countCourses(long companyId, String freeText,
		String language, int status, long parentCourseId, long groupId,
		java.util.LinkedHashMap<String, Object> params) {
		return getService()
				   .countCourses(companyId, freeText, language, status,
			parentCourseId, groupId, params);
	}

	/**
	* Método para buscar cursos
	*/
	public static int countCourses(long companyId, String title,
		String description, String language, int status, long parentCourseId,
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
	*/
	public static com.ted.lms.model.Course deleteCourse(
		com.ted.lms.model.Course course) {
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

	public static com.ted.lms.model.CourseResult enrollStudent(
		com.ted.lms.model.Course course, long userId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext,
		com.liferay.portal.kernel.security.permission.PermissionChecker permissionChecker)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.ted.lms.exception.InscriptionException {
		return getService()
				   .enrollStudent(course, userId, serviceContext,
			permissionChecker);
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

	/**
	* Método para buscar cursos
	*/
	public static java.util.List<com.ted.lms.model.Course> searchCourses(
		long companyId, String freeText, String language, int status,
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
		int status, long parentCourseId, long groupId,
		java.util.LinkedHashMap<String, Object> params, boolean andOperator,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.ted.lms.model.Course> obc) {
		return getService()
				   .searchCourses(companyId, title, description, language,
			status, parentCourseId, groupId, params, andOperator, start, end,
			obc);
	}

	public static boolean unsubscribeStudent(com.ted.lms.model.Course course,
		long userId,
		com.liferay.portal.kernel.security.permission.PermissionChecker permissionChecker)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().unsubscribeStudent(course, userId, permissionChecker);
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
		String[] assetTagNames, long[] assetLinkEntryIds, Double priority,
		String summary)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.updateAsset(userId, course, assetCategoryIds, assetTagNames,
			assetLinkEntryIds, priority, summary);
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