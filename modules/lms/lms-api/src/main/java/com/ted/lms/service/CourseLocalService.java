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

import com.liferay.exportimport.kernel.lar.PortletDataContext;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.ted.lms.exception.InscriptionException;
import com.ted.lms.model.Course;
import com.ted.lms.model.CourseResult;

import java.io.Serializable;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Provides the local service interface for Course. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see CourseLocalServiceUtil
 * @see com.ted.lms.service.base.CourseLocalServiceBaseImpl
 * @see com.ted.lms.service.impl.CourseLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface CourseLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CourseLocalServiceUtil} to access the course local service. Add custom service methods to {@link com.ted.lms.service.impl.CourseLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the course to the database. Also notifies the appropriate model listeners.
	*
	* @param course the course
	* @return the course that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public Course addCourse(Course course);

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
	@Indexable(type = IndexableType.REINDEX)
	public Course addCourse(Map<Locale, String> titleMap,
		Map<Locale, String> descriptionMap, String summary, String friendlyURL,
		long parentCourseId, ImageSelector smallImageImageSelector,
		Date registrationStartDate, Date registrationEndDate,
		Date executionStartDate, Date executionEndDate,
		long layoutSetPrototypeId, int typeSite, long inscriptionType,
		long courseEvalId, long calificationType, int maxUsers,
		boolean welcome, String welcomeSubject, String welcomeMsg,
		boolean goodbye, String goodbyeSubject, String goodbyeMsg, int status,
		ServiceContext serviceContext);

	/**
	* Método para buscar cursos
	*/
	public int countCourses(long companyId, String freeText, String language,
		int status, long parentCourseId, long groupId,
		LinkedHashMap<String, Object> params);

	/**
	* Método para buscar cursos
	*/
	public int countCourses(long companyId, String title, String description,
		String language, int status, long parentCourseId, long groupId,
		LinkedHashMap<String, Object> params, boolean andOperator);

	/**
	* Creates a new course with the primary key. Does not add the course to the database.
	*
	* @param courseId the primary key for the new course
	* @return the new course
	*/
	@Transactional(enabled = false)
	public Course createCourse(long courseId);

	/**
	* Deletes the course from the database. Also notifies the appropriate model listeners.
	*
	* @param course the course
	* @return the course that was removed
	*/
	@Indexable(type = IndexableType.REINDEX)
	public Course deleteCourse(Course course);

	/**
	* Deletes the course with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param courseId the primary key of the course
	* @return the course that was removed
	* @throws PortalException if a course with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public Course deleteCourse(long courseId) throws PortalException;

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	public DynamicQuery dynamicQuery();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

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
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

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
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	public CourseResult enrollStudent(Course course, long userId,
		ServiceContext serviceContext, PermissionChecker permissionChecker)
		throws PortalException, InscriptionException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Course fetchCourse(long courseId);

	/**
	* Returns the course matching the UUID and group.
	*
	* @param uuid the course's UUID
	* @param groupId the primary key of the group
	* @return the matching course, or <code>null</code> if a matching course could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Course fetchCourseByUuidAndGroupId(String uuid, long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Course> getChildsRegistredUser(long parentCourseId, long userId);

	/**
	* Returns the course with the primary key.
	*
	* @param courseId the primary key of the course
	* @return the course
	* @throws PortalException if a course with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Course getCourse(long courseId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Course getCourseByGroupCreatedId(long groupCreatedId);

	/**
	* Returns the course matching the UUID and group.
	*
	* @param uuid the course's UUID
	* @param groupId the primary key of the group
	* @return the matching course
	* @throws PortalException if a matching course could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Course getCourseByUuidAndGroupId(String uuid, long groupId)
		throws PortalException;

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Course> getCourses(int start, int end);

	/**
	* Returns all the courses matching the UUID and company.
	*
	* @param uuid the UUID of the courses
	* @param companyId the primary key of the company
	* @return the matching courses, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Course> getCoursesByUuidAndCompanyId(String uuid, long companyId);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Course> getCoursesByUuidAndCompanyId(String uuid,
		long companyId, int start, int end,
		OrderByComparator<Course> orderByComparator);

	/**
	* Returns the number of courses.
	*
	* @return the number of courses
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCoursesCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public String getOSGiServiceIdentifier();

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	* Método para buscar cursos
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Course> searchCourses(long companyId, String freeText,
		String language, int status, long parentCourseId, long groupId,
		LinkedHashMap<String, Object> params, int start, int end,
		OrderByComparator<Course> obc);

	/**
	* Método para buscar cursos
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Course> searchCourses(long companyId, String title,
		String description, String language, int status, long parentCourseId,
		long groupId, LinkedHashMap<String, Object> params,
		boolean andOperator, int start, int end, OrderByComparator<Course> obc);

	public boolean unsubscribeStudent(Course course, long userId,
		PermissionChecker permissionChecker) throws PortalException;

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
	public void updateAsset(long userId, Course course,
		long[] assetCategoryIds, String[] assetTagNames,
		long[] assetLinkEntryIds, Double priority, String summary)
		throws PortalException;

	/**
	* Updates the course in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param course the course
	* @return the course that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public Course updateCourse(Course course);

	/**
	* Modifica la url de un curso
	*
	* @param groupCreatedId identificador del sitio web del curso
	* @param friendlyURL nueva url
	* @throws PortalException
	*/
	public void updateFriendlyURL(long groupCreatedId, String friendlyURL)
		throws PortalException;

	public void updateSmallImage(long courseId,
		ImageSelector smallImageSelector, ServiceContext serviceContext)
		throws PortalException;
}