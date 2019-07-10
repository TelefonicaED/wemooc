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
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.ted.lms.model.Course;

import java.io.IOException;
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
	public Folder addAttachmentsFolder(long userId, long groupId)
		throws PortalException;

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
	* @param summaryMap resumen del curso con las traducciones
	* @param indexer si el curso se muestra en las búsquedas
	* @param friendlyURLMap url del curso, si es vacío se autogenera a partir del nombre con las traducciones
	* @param layoutSetPrototypeId identificador de la plantilla de sitio web
	* @param parentCourseId identificador del curso padre, si es cero se considera curso padre
	* @param smallImageImageSelector imagen seleccionada para el curso
	* @param serviceContext contexto de la creación del curso
	* @throws Exception
	*/
	@Indexable(type = IndexableType.REINDEX)
	public Course addCourse(long userId, long groupId,
		Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
		Map<Locale, String> summaryMap, boolean indexer,
		Map<Locale, String> friendlyURLMap, long layoutSetPrototypeId,
		long parentCourseId, long courseTypeId,
		ImageSelector smallImageSelector, ServiceContext serviceContext)
		throws Exception;

	public long addOriginalImageFileEntry(long userId, long groupId,
		long entryId, ImageSelector imageSelector) throws PortalException;

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
	public void addUserCourse(long userId, long courseId, long[] addUserIds,
		long roleId) throws PortalException;

	public Course copyCourse(long userId, long courseId, long parentCourseId,
		String title, Map<Locale, String> friendlyURLMap,
		long layoutSetPrototypeId, Date registrationStartDate,
		Date registrationEndDate, Date executionStartDate,
		Date executionEndDate, boolean copyForum, boolean copyDocuments,
		ServiceContext serviceContext) throws Exception;

	/**
	* Método para buscar cursos
	*/
	public int countCourses(long companyId, String freeText, String language,
		int[] status, long parentCourseId, long groupId,
		LinkedHashMap<String, Object> params);

	/**
	* Método para buscar cursos
	*/
	public int countCourses(long companyId, String title, String description,
		String language, int[] status, long parentCourseId, long groupId,
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
	* @throws PortalException
	*/
	@Indexable(type = IndexableType.DELETE)
	public Course deleteCourse(Course course) throws PortalException;

	/**
	* Deletes the course with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param courseId the primary key of the course
	* @return the course that was removed
	* @throws PortalException if a course with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public Course deleteCourse(long courseId) throws PortalException;

	@Indexable(type = IndexableType.DELETE)
	public void deleteCourses(long groupId);

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DynamicQuery dynamicQuery();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	public long executeCopyCourse(long courseId, long parentCourseId,
		Map<Locale, String> titleMap, Map<Locale, String> friendlyURLMap,
		long layoutSetPrototypeId, Date registrationStartDate,
		Date registrationEndDate, Date executionStartDate,
		Date executionEndDate, boolean copyForum, boolean copyDocuments,
		ServiceContext serviceContext) throws PortalException;

	public long exportCourseContent(long courseId, long sourcePlid,
		String fileName, Map<String, String[]> parameterMap,
		ServiceContext serviceContext) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Folder fetchAttachmentsFolder(long userId, long groupId);

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
	public boolean getAllowAccessToCompletedCourses(long companyId);

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
	public List<Group> getDistinctCourseGroups(long companyId);

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

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public String[] getPrerequisiteActivities(long companyId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public String[] getPrerequisiteCourses(long companyId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public String[] getPrerequisiteModules(long companyId);

	public JSONObject importCourseMembers(long userId, long courseId,
		long roleId, FileEntry fileEntry) throws PortalException, IOException;

	public JSONObject importEditions(long userId, long courseId,
		FileEntry fileEntry) throws PortalException, IOException;

	@Indexable(type = IndexableType.REINDEX)
	public Course moveEntryToTrash(long userId, Course course)
		throws PortalException;

	public Course moveEntryToTrash(long userId, long courseId)
		throws PortalException;

	@Indexable(type = IndexableType.REINDEX)
	public Course restoreEntryFromTrash(long userId, long courseId)
		throws PortalException;

	/**
	* Método para buscar cursos
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Course> searchCourses(long companyId, String freeText,
		String language, int[] status, long parentCourseId, long groupId,
		LinkedHashMap<String, Object> params, int start, int end,
		OrderByComparator<Course> obc);

	/**
	* Método para buscar cursos
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Course> searchCourses(long companyId, String title,
		String description, String language, int[] status, long parentCourseId,
		long groupId, LinkedHashMap<String, Object> params,
		boolean andOperator, int start, int end, OrderByComparator<Course> obc);

	/**
	* A esta función se le debe llamar cuando un administrador desinscriba a un usuario
	*
	* @param courseId identificador del curso
	* @param removeUserIds usuarios a desinscribir
	* @param roleId identificador del rol del que se le va a desinscrbir
	* @param serviceContext
	* @throws PortalException
	*/
	public void unsetUserCourse(long courseId, long[] removeUserIds,
		long roleId, ServiceContext serviceContext) throws PortalException;

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
		long[] assetLinkEntryIds) throws PortalException;

	/**
	* Updates the course in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param course the course
	* @return the course that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public Course updateCourse(Course course);

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
	@Indexable(type = IndexableType.REINDEX)
	public Course updateCourse(long userId, long courseId, boolean welcome,
		Map<Locale, String> welcomeSubjectMap,
		Map<Locale, String> welcomeMsgMap, boolean goodbye,
		Map<Locale, String> goodbyeSubjectMap,
		Map<Locale, String> goodbyeMsgMap, boolean deniedInscription,
		Map<Locale, String> deniedInscriptionSubjectMap,
		Map<Locale, String> deniedInscriptionMsgMap, int status)
		throws PortalException;

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
	@Indexable(type = IndexableType.REINDEX)
	public Course updateCourse(long userId, long courseId,
		Date registrationStartDate, Date registrationEndDate,
		Date executionStartDate, Date executionEndDate, int typeSite,
		long inscriptionType, long courseEvalId, long calificationType,
		int maxUsers, int status, ServiceContext serviceContext)
		throws PortalException;

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
	public Course updateCourse(long userId, long courseId,
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
		int maxUsers, int status, ServiceContext serviceContext)
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
	@Indexable(type = IndexableType.REINDEX)
	public Course updateCourse(long userId, long courseId, int status,
		ServiceContext serviceContext)
		throws PrincipalException, PortalException;

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
	@Indexable(type = IndexableType.REINDEX)
	public Course updateCourse(long userId, long courseId,
		Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
		Map<Locale, String> summaryMap, boolean indexer,
		Map<Locale, String> friendlyURLMap, long layoutSetPrototypeId,
		ImageSelector smallImageSelector, ServiceContext serviceContext)
		throws Exception;

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

	@Indexable(type = IndexableType.REINDEX)
	public Course updateStatus(long userId, long courseId, int status,
		ServiceContext serviceContext) throws PortalException;
}