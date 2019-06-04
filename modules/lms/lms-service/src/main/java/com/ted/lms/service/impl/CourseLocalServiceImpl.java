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

package com.ted.lms.service.impl;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetLinkConstants;
import com.liferay.blogs.kernel.exception.EntrySmallImageScaleException;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.friendly.url.model.FriendlyURLEntry;
import com.liferay.friendly.url.service.FriendlyURLEntryLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Conjunction;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.Disjunction;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.MembershipRequestConstants;
import com.liferay.portal.kernel.model.ModelHintsConstants;
import com.liferay.portal.kernel.model.ModelHintsUtil;
import com.liferay.portal.kernel.model.Repository;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.GroupServiceUtil;
import com.liferay.portal.kernel.service.MembershipRequestLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector;
import com.liferay.portal.kernel.servlet.taglib.ui.ImageSelectorProcessor;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.FriendlyURLNormalizerUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.comparator.WorkflowComparatorFactory;
import com.liferay.portal.liveusers.LiveUsers;
import com.liferay.portal.spring.extender.service.ServiceReference;
import com.liferay.sites.kernel.util.SitesUtil;
import com.liferay.social.kernel.model.SocialActivityConstants;
import com.liferay.social.kernel.service.SocialActivityLocalServiceUtil;
import com.ted.lms.configuration.CourseServiceConfiguration;
import com.ted.lms.constants.CourseConstants;
import com.ted.lms.constants.LMSConstants;
import com.ted.lms.exception.ExecutionEndDateException;
import com.ted.lms.exception.ExecutionStartDateException;
import com.ted.lms.exception.InscriptionException;
import com.ted.lms.exception.NoSuchCourseException;
import com.ted.lms.exception.RegistrationEndDateException;
import com.ted.lms.exception.RegistrationStartDateException;
import com.ted.lms.model.Course;
import com.ted.lms.model.CourseResult;
import com.ted.lms.service.base.CourseLocalServiceBaseImpl;
import com.ted.lms.service.util.SmallImageHelper;
import com.ted.lms.settings.CoursesGroupServiceSettings;
import com.ted.lms.util.LMSPrefsPropsValues;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

/**
 * The implementation of the course local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ted.lms.service.CourseLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CourseLocalServiceBaseImpl
 * @see com.ted.lms.service.CourseLocalServiceUtil
 */
public class CourseLocalServiceImpl extends CourseLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.ted.lms.service.CourseLocalServiceUtil} to access the course local service.
	 */
	
	private static final Log log = LogFactoryUtil.getLog(CourseLocalServiceImpl.class);
	
	/**
	 * Crea un nuevo curso
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
	@Indexable(type = IndexableType.REINDEX)
	public Course addCourse(Map<Locale, String> titleMap, Map<Locale, String> descriptionMap, Map<Locale, String> summaryMap, boolean indexer, 
			Map<Locale, String> friendlyURLMap, long layoutSetPrototypeId, long parentCourseId, ImageSelector smallImageSelector, 
			ServiceContext serviceContext) {
		Course course = null;
		try {
			User user = userLocalService.getUser(serviceContext.getUserId());
			Date now = new Date();
			
			long courseId = counterLocalService.increment(Course.class.getName());
			course = coursePersistence.create(courseId);
			
			course.setGroupId(serviceContext.getScopeGroupId());
			course.setCompanyId(serviceContext.getCompanyId());
			course.setUserId(serviceContext.getUserId());
			course.setUserName(user.getFullName());
			course.setCreateDate(now);
			course.setModifiedDate(now);
			
			course.setParentCourseId(parentCourseId);
			course.setTitleMap(titleMap);
			course.setDescriptionMap(descriptionMap);
			course.setCourseEvalId(-1);
			course.setCalificationType(-1);
			course.setInscriptionType(-1);
			
			course.setStatus(WorkflowConstants.STATUS_DRAFT);
			course.setStatusByUserId(serviceContext.getUserId());
			course.setStatusByUserName(user.getFullName());
			course.setStatusDate(now);
			if(smallImageSelector != null) {
				course.setSmallImageId(addSmallImageCourse(serviceContext.getUserId(), serviceContext.getScopeGroupId(), course.getCourseId(), smallImageSelector));
			}
			
			//Guardamos la url si no es vacía para cuando creemos el sitio web
			// Friendly URLs

			Map<String, String> urlGroupMap = getURLGroupMap(serviceContext.getScopeGroupId(), course.getCourseId(), friendlyURLMap);
			
			//Creamos el group asociado, le cambiamos al nombre al grupo porque no deja crear dos con el mismo nombre por el groupKey
			Map<Locale,String> titleMapGroup = new HashMap<Locale,String>();
			
			course.getTitleMap().forEach((k,v)->titleMapGroup.put(k, v + " (" + courseId + ")"));
			
			String urlGroup = urlGroupMap.get(LocaleUtil.toLanguageId(LocaleUtil.getDefault()));
			if(Validator.isNull(urlGroup)) {
				urlGroup = getFriendlyURL(serviceContext.getCompanyId(), urlGroup, course.getTitleMap());
				urlGroupMap.put(LocaleUtil.toLanguageId(LocaleUtil.getDefault()), urlGroup.substring(1));
			}
			
			friendlyURLEntryLocalService.addFriendlyURLEntry(serviceContext.getScopeGroupId(), classNameLocalService.getClassNameId(Course.class),
					course.getCourseId(), urlGroupMap, serviceContext);
		
			int membershipRestriction = GroupConstants.DEFAULT_MEMBERSHIP_RESTRICTION;
	
			if (course.getParentCourseId() != GroupConstants.DEFAULT_PARENT_GROUP_ID) {
				membershipRestriction =GroupConstants.MEMBERSHIP_RESTRICTION_TO_PARENT_SITE_MEMBERS;
			}
			
			
			long parentGroupId = GroupConstants.DEFAULT_PARENT_GROUP_ID;
			if(course.getParentCourseId() != CourseConstants.DEFAULT_PARENT_COURSE_ID) {
				Course courseParent = coursePersistence.fetchByPrimaryKey(course.getParentCourseId());
				if(courseParent != null) {
					parentGroupId = courseParent.getGroupCreatedId();
				}
			}
			
			Group group = GroupServiceUtil.addGroup(parentGroupId, 0, titleMapGroup, course.getDescriptionMap(), 
					GroupConstants.TYPE_SITE_OPEN, true, membershipRestriction, urlGroup, 
					true, false, true, serviceContext);
			
			course.setGroupCreatedId(group.getGroupId());
			
			LiveUsers.joinGroup(serviceContext.getCompanyId(), group.getGroupId(), serviceContext.getUserId());
			
			//Añadimos rol de editor o tutor al creador
			boolean teacherRoleToCreator = LMSPrefsPropsValues.getCourseAddTeacherRoleToCreator(course.getCourseId());
			boolean editorRoleToCreator = LMSPrefsPropsValues.getCourseAddEditorRoleToCreator(course.getCourseId());
			
			if(teacherRoleToCreator){
				long teacherRole = LMSPrefsPropsValues.getLMSPrefsTeacherRole(course.getCourseId());
				if(teacherRole > 0) {
					long[] teacherRoleId = {teacherRole};
					UserGroupRoleLocalServiceUtil.addUserGroupRoles(serviceContext.getUserId(), course.getGroupCreatedId(), teacherRoleId);
				}
			}

			if(editorRoleToCreator){
				long editorRole = LMSPrefsPropsValues.getLMSPrefsEditorRole(course.getCompanyId());
				if(editorRole > 0) {
					long[] editorRoleId = {editorRole};
					UserGroupRoleLocalServiceUtil.addUserGroupRoles(serviceContext.getUserId(),	course.getGroupCreatedId(), editorRoleId);
				}
			}	
			SitesUtil.updateLayoutSetPrototypesLinks(group, layoutSetPrototypeId, 0, true,false);
		
			course = coursePersistence.update(course);
			
			resourceLocalService.addResources(course.getCompanyId(), course.getGroupId(), course.getUserId(),  Course.class.getName(), course.getCourseId(), false, true, false);
			
			AssetEntry assetEntry = assetEntryLocalService.updateEntry(
					serviceContext.getUserId(), course.getGroupId(), course.getCreateDate(),
					course.getModifiedDate(), Course.class.getName(),
					course.getCourseId(), course.getUuid(), 0, serviceContext.getAssetCategoryIds(),
					serviceContext.getAssetTagNames(), true, course.isApproved(), null, null, null,
					null, ContentTypes.TEXT_HTML, course.getTitle(), course.getDescription(), null, null,
					null, 0, 0, serviceContext.getAssetPriority());
			
			assetEntry.setSummaryMap(summaryMap);
			assetEntryLocalService.updateAssetEntry(assetEntry);
			

		} catch (PortalException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return course;
	}
	
	/**
	 * Actualiza el segundo paso de un curso
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
	public Course updateCourse(long courseId, int registrationStartMonth, int registrationStartDay, int registrationStartYear, int registrationStartHour, int registrationStartMinute, 
			int registrationEndMonth, int registrationEndDay, int registrationEndYear, int registrationEndHour, int registrationEndMinute, 
			int executionStartMonth, int executionStartDay, int executionStartYear, int executionStartHour, int executionStartMinute, 
			int executionEndMonth, int executionEndDay, int executionEndYear, int executionEndHour, int executionEndMinute, 
			int typeSite, long inscriptionType, long courseEvalId, long calificationType, int maxUsers, int status,
			ServiceContext serviceContext) throws PortalException {
		
		User user = userLocalService.getUser(serviceContext.getUserId());

		Date registrationStartDate = PortalUtil.getDate(registrationStartMonth, registrationStartDay, registrationStartYear, registrationStartHour,
				registrationStartMinute, user.getTimeZone(), RegistrationStartDateException.class);
		Date registrationEndDate = PortalUtil.getDate(registrationEndMonth, registrationEndDay, registrationEndYear, registrationEndHour,
				registrationEndMinute, user.getTimeZone(), RegistrationEndDateException.class);
		Date executionStartDate = PortalUtil.getDate(executionStartMonth, executionStartDay, executionStartYear, executionStartHour,
				executionStartMinute, user.getTimeZone(), ExecutionStartDateException.class);
		Date executionEndDate = PortalUtil.getDate(executionEndMonth, executionEndDay, executionEndYear, executionEndHour,
				executionEndMinute, user.getTimeZone(), ExecutionEndDateException.class);
		
		return updateCourse(courseId, registrationStartDate, registrationEndDate, executionStartDate, executionEndDate, 
				typeSite, inscriptionType, courseEvalId, calificationType, maxUsers, status, serviceContext);
	}
	
	/**
	 * Actualiza el segundo paso de un curso
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
	@Indexable(type = IndexableType.REINDEX)
	public Course updateCourse(long courseId, Date registrationStartDate, Date registrationEndDate, Date executionStartDate, Date executionEndDate, 
			int typeSite, long inscriptionType, long courseEvalId, long calificationType, int maxUsers, int status,
			ServiceContext serviceContext) throws PortalException {
		
		Course course = coursePersistence.findByPrimaryKey(courseId);
		
		course.setRegistrationStartDate(registrationStartDate);
		course.setRegistrationEndDate(registrationEndDate);
		course.setExecutionStartDate(executionStartDate);
		course.setExecutionEndDate(executionEndDate);
		course.setMaxUsers(maxUsers);
		course.setInscriptionType(inscriptionType);
		course.setCourseEvalId(courseEvalId);
		course.setCalificationType(calificationType);
		course.setExpandoBridgeAttributes(serviceContext);
		if(status != course.getStatus()) {
			course.setStatusByUserId(serviceContext.getUserId());
			User user = userLocalService.getUser(serviceContext.getUserId());
			course.setStatusByUserName(user.getFullName());
			course.setStatusByUserUuid(user.getUserUuid());
			course.setStatusDate(new Date());
		}
		course.setStatus(status);
		course.setModifiedDate(new Date());
		course = coursePersistence.update(course);
		
		//Actualizamos el tipo del sitio
		Group group = groupLocalService.getGroup(course.getGroupCreatedId());
		group.setType(typeSite);
		groupLocalService.updateGroup(group);
		
		updateAsset(serviceContext.getUserId(), course, serviceContext.getAssetCategoryIds(), serviceContext.getAssetTagNames(),
				serviceContext.getAssetLinkEntryIds());

		return course;
	}
	
	/**
	 * Actualiza el paso de los mensajes de la modificación de curso
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
	 * @throws NoSuchCourseException 
	 */
	@Override
	@Indexable(type = IndexableType.REINDEX)
	public Course updateCourse(long courseId, boolean welcome, Map<Locale, String> welcomeSubjectMap,
			Map<Locale, String> welcomeMsgMap, boolean goodbye, Map<Locale, String> goodbyeSubjectMap,
			Map<Locale, String> goodbyeMsgMap, boolean deniedInscription, Map<Locale, String> deniedInscriptionSubjectMap,
			Map<Locale, String> deniedInscriptionMsgMap, int status, ServiceContext serviceContext) throws NoSuchCourseException {
		
		Course course = coursePersistence.findByPrimaryKey(courseId);
		
		course.setModifiedDate(new Date());
		course.setWelcome(welcome);
		if(welcome) {
			course.setWelcomeSubjectMap(welcomeSubjectMap);
			course.setWelcomeMsgMap(welcomeMsgMap);
		}else {
			course.setWelcomeSubject(null);
			course.setWelcomeMsg(null);
		}
		course.setGoodbye(goodbye);
		if(goodbye) {
			course.setGoodbyeSubjectMap(goodbyeSubjectMap);
			course.setGoodbyeMsgMap(goodbyeMsgMap);
		}else {
			course.setGoodbyeSubject(null);
			course.setGoodbyeMsg(null);
		}
		course.setDeniedInscription(deniedInscription);
		if(deniedInscription) {
			course.setDeniedInscriptionSubjectMap(deniedInscriptionSubjectMap);
			course.setDeniedInscriptionMsgMap(deniedInscriptionMsgMap);
		}else {
			course.setDeniedInscriptionSubject(null);
			course.setDeniedInscriptionMsg(null);
		}
		if(status != course.getStatus()) {
			course.setStatusByUserId(serviceContext.getUserId());
			try {
				User user = userLocalService.getUser(serviceContext.getUserId());			
				course.setStatusByUserName(user.getFullName());
				course.setStatusByUserUuid(user.getUserUuid());
			} catch (PortalException e) {
				e.printStackTrace();
			}
			course.setStatusDate(new Date());
		}
		course.setStatus(status);
		
		course = coursePersistence.update(course);
		
		return course;
	}
	
	/**
	 * Actualiza los contenidos relacionados y el estado
	 * @param courseId identificador del curso
	 * @param status estado del curso
	 * @param serviceContext contexto de la modificación del curso
	 * @return curso modificado
	 * @throws PrincipalException
	 * @throws PortalException
	 */
	@Indexable(type = IndexableType.REINDEX)
	public Course updateCourse(long courseId, int status, ServiceContext serviceContext) throws PrincipalException, PortalException {

		Course course = coursePersistence.findByPrimaryKey(courseId);
		
		if(status != course.getStatus()) {
			course.setStatusByUserId(serviceContext.getUserId());
			try {
				User user = userLocalService.getUser(serviceContext.getUserId());			
				course.setStatusByUserName(user.getFullName());
				course.setStatusByUserUuid(user.getUserUuid());
			} catch (PortalException e) {
				e.printStackTrace();
			}
			course.setStatusDate(new Date());
		}
		course.setStatus(status);
		
		course = coursePersistence.update(course);
		
		assetLinkLocalService.updateLinks(serviceContext.getUserId(), course.getAssetEntry().getEntryId(), serviceContext.getAssetLinkEntryIds(),AssetLinkConstants.TYPE_RELATED);
		
		return course;
	}
	
	/**
	 * Actualiza el paso de descripción de la modificación de curso
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
	public Course updateCourse(long courseId, Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
			Map<Locale, String> summaryMap, boolean indexer, Map<Locale, String> friendlyURLMap, long layoutSetPrototypeId, 
			ImageSelector smallImageSelector, ServiceContext serviceContext) throws Exception {
		
		Course course = coursePersistence.findByPrimaryKey(courseId);
		
		Date now = new Date();
		
		course.setTitleMap(titleMap);
		course.setDescriptionMap(descriptionMap);
		course.setModifiedDate(now);
		if(smallImageSelector != null) {
			course.setSmallImageId(addSmallImageCourse(serviceContext.getUserId(), serviceContext.getScopeGroupId(), course.getCourseId(), smallImageSelector));
		}
		
		coursePersistence.update(course);
		
		AssetEntry assetEntry = assetEntryLocalService.getEntry(Course.class.getName(), courseId);
		assetEntry.setSummaryMap(summaryMap);
		assetEntry.setVisible(indexer);
		assetEntry.setModifiedDate(now);
		assetEntryLocalService.updateAssetEntry(assetEntry);
		
		// Friendly URLs

		List<FriendlyURLEntry> friendlyURLEntries =
			friendlyURLEntryLocalService.getFriendlyURLEntries(serviceContext.getScopeGroupId(),classNameLocalService.getClassNameId(Course.class),
					course.getCourseId());

		for (FriendlyURLEntry friendlyURLEntry : friendlyURLEntries) {
			friendlyURLEntryLocalService.deleteFriendlyURLEntry(friendlyURLEntry);
		}
		
		Map<String, String> urlGroupMap = getURLGroupMap(serviceContext.getScopeGroupId(), course.getCourseId(), friendlyURLMap);

		String urlGroup = urlGroupMap.get(LocaleUtil.toLanguageId(serviceContext.getLocale()));

		friendlyURLEntryLocalService.addFriendlyURLEntry(serviceContext.getScopeGroupId(), classNameLocalService.getClassNameId(Course.class),
			course.getCourseId(), urlGroupMap, serviceContext);
		
		//Para la url miramos si ya tiene group, si no lo tiene actualizo la friendlyurl, si no la del curso
		if(course.getGroupCreatedId() > 0 ) {
			Group group = course.getGroup();
			urlGroup = StringPool.FORWARD_SLASH + urlGroup;
			if(!group.getFriendlyURL().equals(urlGroup)) {
				groupLocalService.updateFriendlyURL(course.getGroupCreatedId(), urlGroup);
			}	
		}
		
		//Comprobamos si la plantilla se ha modificado
		if(layoutSetPrototypeId != course.getLayoutSetPrototypeId()) {
			Group group = course.getGroup();
			if(group != null) {
				SitesUtil.updateLayoutSetPrototypesLinks(group, layoutSetPrototypeId, 0, true,false);
			}
		}
		
		return course;
	}
	
	private String getFriendlyURL(long companyId, String friendlyURL, Map<Locale, String> titleMap) {
		//Se asegura que la longitud de friendlyURL no supere el maximo
		int maxLength  = GetterUtil.getInteger(
							ModelHintsUtil.getHints(Group.class.getName(), "friendlyURL").get("max-length"),
							GetterUtil.getInteger(ModelHintsConstants.TEXT_MAX_LENGTH));
		
		String title = null;
		if(titleMap.containsKey(Locale.getDefault())){
			title = titleMap.get(Locale.getDefault());
		}else{
			//Cogemos el primero
			Entry<Locale, String> entry = titleMap.entrySet().iterator().next();
			title = entry.getValue();
		}
		
		if(Validator.isNull(friendlyURL)) {
			friendlyURL = StringPool.SLASH + FriendlyURLNormalizerUtil.normalize(title);
			if(friendlyURL.length()>maxLength) {
				friendlyURL = friendlyURL.substring(0, maxLength);
			}
			int  i = 0;
			boolean friendlyNotExit = false;
			while(!friendlyNotExit) {
				Group exist = groupLocalService.fetchFriendlyURLGroup(companyId, friendlyURL);
				if (Validator.isNotNull(exist)){
					String iString = String.valueOf(i);
					if(friendlyURL.length()+iString.length()>maxLength) {
						if(iString.length()>maxLength) {
							throw new SystemException();
						}
						friendlyURL =friendlyURL.substring(0, maxLength-iString.length())+iString;
					}
					else {
						friendlyURL =friendlyURL+iString;
					}
				}else{
					friendlyNotExit = true;
				}
				i++;
			}				
		}
		
		friendlyURL = StringPool.SLASH+friendlyURL.replaceAll("[^a-zA-Z0-9_-]+", "");
		return friendlyURL;
	}
	
	/**
	 * Actualiza el estado del asset correspondiente al curso
	 * @param userId identificador del usuario que modifica
	 * @param course curso que se modifica
	 * @param assetCategoryIds identificadores de categorías que añadiremos al asset del curso
	 * @param assetTagNames etiquetas que añadiremos al asset del curso
	 * @param assetLinkEntryIds contenidos relacionados que añadiremos al asset del curso
	 * @param priority prioridad del curso en las búsquedas
	 * @param summary resumen del curso
	 */
	public void updateAsset(long userId, Course course, long[] assetCategoryIds,String[] assetTagNames, long[] assetLinkEntryIds) throws PortalException {

		assetEntryLocalService.updateEntry(userId, course.getGroupId(), Course.class.getName(), course.getCourseId(), assetCategoryIds,
			assetTagNames);

	}
	
	@Override
	@Indexable(type = IndexableType.DELETE)
	public Course deleteCourse(Course course) {
		
		try {
			resourceLocalService.deleteResource(course.getCompanyId(), Course.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL, course.getCourseId());
			
			AssetEntry assetEntry = assetEntryLocalService.fetchEntry(Course.class.getName(), course.getCourseId());
			
			assetLinkLocalService.deleteLinks(assetEntry.getEntryId());
			assetEntryLocalService.deleteEntry(assetEntry);

			Indexer<Course> indexer = IndexerRegistryUtil.nullSafeGetIndexer(Course.class);
			indexer.delete(course);
			
			coursePersistence.remove(course);
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			course = null;
		}
		
		return course;
	}	
	
	/**
	 * Modifica la url de un curso
	 * @param groupCreatedId identificador del sitio web del curso
	 * @param friendlyURL nueva url
	 * @throws PortalException
	 */
	public void updateFriendlyURL(long groupCreatedId, String friendlyURL) throws PortalException {
		groupLocalService.updateFriendlyURL(groupCreatedId, friendlyURL);
	}
	
	private Map<String, String> getURLGroupMap(long groupId, long resourcePrimKey, Map<Locale, String> friendlyURLMap) {

		Map<String, String> urlTitleMap = new HashMap<>();

		for (Map.Entry<Locale, String> entry : friendlyURLMap.entrySet()) {
			String title = entry.getValue();

			if (Validator.isNull(title)) {
				continue;
			}

			String urlTitle = friendlyURLEntryLocalService.getUniqueUrlTitle(groupId,classNameLocalService.getClassNameId(Course.class),resourcePrimKey, title);
			urlTitleMap.put(LocaleUtil.toLanguageId(entry.getKey()), urlTitle);
		}

		return urlTitleMap;
	}
	
	@Override
	public long addOriginalImageFileEntry(long userId, long groupId, long entryId, ImageSelector imageSelector) throws PortalException {

		byte[] imageBytes = imageSelector.getImageBytes();

		if (imageBytes == null) {
			return 0;
		}

		Folder folder = SmallImageHelper.addSmallImageFolder(userId, groupId, SMALL_IMAGE_FOLDER_NAME);

		FileEntry originalFileEntry = PortletFileRepositoryUtil.addPortletFileEntry(
				groupId, userId, null, 0, LMSConstants.SERVICE_NAME, folder.getFolderId(), imageBytes,
				SmallImageHelper.getUniqueFileName(groupId, imageSelector.getImageTitle(), folder.getFolderId(), UNIQUE_FILE_NAME_TRIES),
				imageSelector.getImageMimeType(), true);

		return originalFileEntry.getFileEntryId();
	}
	
	public void updateSmallImage(long courseId, ImageSelector smallImageSelector, ServiceContext serviceContext) throws PortalException {
		Course course = coursePersistence.findByPrimaryKey(courseId);
		
		course.setSmallImageId(addSmallImageCourse(serviceContext.getUserId(), serviceContext.getScopeGroupId(), courseId, smallImageSelector));
		
		coursePersistence.update(course);
	}
	
	protected long addSmallImageCourse(long userId, long groupId, long courseId, ImageSelector imageSelector) throws PortalException {

		byte[] imageBytes = imageSelector.getImageBytes();

		if (imageBytes == null) {
			return 0;
		}

		try {
			CoursesGroupServiceSettings coursesGroupServiceSettings =
				CoursesGroupServiceSettings.getInstance(groupId);

			ImageSelectorProcessor imageSelectorProcessor =
				new ImageSelectorProcessor(imageSelector.getImageBytes());

			imageBytes = imageSelectorProcessor.scaleImage(
				coursesGroupServiceSettings.getSmallImageWidth());

			if (imageBytes == null) {
				throw new EntrySmallImageScaleException();
			}

			Folder folder = addSmallImageFolder(userId, groupId);

			return addProcessedImageFileEntry(
				userId, groupId, courseId, folder.getFolderId(),
				imageSelector.getImageTitle(), imageSelector.getImageMimeType(),
				imageBytes);
		}
		catch (IOException ioe) {
			throw new EntrySmallImageScaleException(ioe);
		}
	}
	
	protected Folder addSmallImageFolder(long userId, long groupId) throws PortalException {

		return doAddFolder(userId, groupId, SMALL_IMAGE_FOLDER_NAME);
	}
	
	protected Folder doAddFolder(long userId, long groupId, String folderName)
		throws PortalException {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);

		Repository repository = PortletFileRepositoryUtil.addPortletRepository(
			groupId, LMSConstants.SERVICE_NAME, serviceContext);

		return PortletFileRepositoryUtil.addPortletFolder(
			userId, repository.getRepositoryId(),
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, folderName,
			serviceContext);
	}
	
	protected long addProcessedImageFileEntry(
			long userId, long groupId, long entryId, long folderId,
			String title, String mimeType, byte[] bytes)
		throws PortalException {

		if (Validator.isNull(title)) {
			title = StringUtil.randomString() + "_processedImage_" + entryId;
		}

		FileEntry processedImageFileEntry =
			PortletFileRepositoryUtil.addPortletFileEntry(
				groupId, userId, Course.class.getName(), entryId,
				LMSConstants.SERVICE_NAME, folderId, bytes,
				DLUtil.getUniqueFileName(groupId, folderId, title), mimeType, true);

		return processedImageFileEntry.getFileEntryId();
	}
	
	public Course getCourseByGroupCreatedId(long groupCreatedId) {
		return coursePersistence.fetchByGroupCreatedId(groupCreatedId);
	}
	
	/**
	 * Método para buscar cursos
	 */
	public List<Course> searchCourses(long companyId, String freeText, String language, int status, long parentCourseId, long groupId, LinkedHashMap<String, Object> params, int start, int end,
			OrderByComparator<Course> obc){
		return courseFinder.findByKeywords(companyId, freeText, language, status, parentCourseId, groupId, params, start, end, obc);
	}
	
	/**
	 * Método para buscar cursos
	 */
	public int countCourses(long companyId, String freeText, String language, int status, long parentCourseId, long groupId, LinkedHashMap<String, Object> params){
		return courseFinder.countByKeywords(companyId, freeText, language, status, parentCourseId, groupId, params, false);
	}
	
	/**
	 * Método para buscar cursos
	 */
	public List<Course> searchCourses(long companyId, String title, String description, String language, int status, long parentCourseId, long groupId, 
			LinkedHashMap<String, Object> params, boolean andOperator, int start, int end,
			OrderByComparator<Course> obc){
		return courseFinder.findByC(companyId, title, description, language, status, parentCourseId, groupId, params, andOperator, start, end, obc);
	}
	
	/**
	 * Método para buscar cursos
	 */
	public int countCourses(long companyId, String title, String description, String language, int status, long parentCourseId, long groupId, LinkedHashMap<String, Object> params, 
			boolean andOperator){
		return courseFinder.countByC(companyId, title, description, language, status, parentCourseId, groupId, params, andOperator);
	}
	
	public List<Course> getChildsRegistredUser(long parentCourseId, long userId){
		return courseFinder.findChildRegistredUser(parentCourseId, userId);
	}
	
	public CourseResult enrollStudent(Course course, long userId, ServiceContext serviceContext, PermissionChecker permissionChecker ) throws PortalException, InscriptionException {
		
		CourseResult courseResult = null;
		
		if(course.canEnroll(userId, serviceContext.getLocale(), permissionChecker)) {
			Group group = groupLocalService.getGroup(course.getGroupCreatedId());
			if(group.getType()==GroupConstants.TYPE_SITE_OPEN){
				Role sitemember=RoleLocalServiceUtil.getRole(course.getCompanyId(), RoleConstants.SITE_MEMBER) ;
				
			/*	if(teamId>0){
        			long[] userIds = new long[1];
        			userIds[0] = userId;	
        			if(!UserLocalServiceUtil.hasTeamUser(teamId, userId)){
        				UserLocalServiceUtil.addTeamUsers(teamId, userIds);	
        			}			
        		}*/
				
				GroupLocalServiceUtil.addUserGroups(userId, new long[]{group.getGroupId()});
				UserGroupRoleLocalServiceUtil.addUserGroupRoles(new long[] { userId }, group.getGroupId(), sitemember.getRoleId());
				SocialActivityLocalServiceUtil.addActivity(userId, group.getGroupId(), Group.class.getName(), group.getGroupId(), SocialActivityConstants.TYPE_SUBSCRIBE, "", userId);
				
				courseResult = courseResultLocalService.getByCourseIdUserId(course.getCourseId(), userId);
				if(courseResult == null) {
					courseResultLocalService.addCourseResult(course.getCourseId(), userId, serviceContext);
				}
				
				log.debug("Inscribimos usuario con id: " + userId + " en la comunidad con id: " + group.getGroupId() + " (" + group.getName() + ")");
			}else if(group.getType()==GroupConstants.TYPE_SITE_RESTRICTED && !MembershipRequestLocalServiceUtil.hasMembershipRequest(userId, group.getGroupId(), MembershipRequestConstants.STATUS_PENDING)){
				MembershipRequestLocalServiceUtil.addMembershipRequest(userId, group.getGroupId(), "Enroll petition", serviceContext);
				SocialActivityLocalServiceUtil.addActivity(userId, group.getGroupId(), Group.class.getName(), group.getGroupId(), SocialActivityConstants.TYPE_SUBSCRIBE, "", userId);
				log.debug("Lanzamos peticion de inscripcion del usuario " + userId + " en la comunidad con id: " + group.getGroupId() + " (" + group.getName() + ")");
			}
		}else {
			courseResult = courseResultPersistence.fetchByCourseIdUserId(course.getCourseId(), userId);
			//Debería tenerlo, pero lo creo si no lo tiene
			if(courseResult == null) {
				courseResult = courseResultLocalService.addCourseResult(course.getCourseId(), userId, serviceContext);
			}
		}
		return courseResult;
	}
	
	public boolean unsubscribeStudent(Course course, long userId, PermissionChecker permissionChecker) throws PortalException {
		boolean resultUnsubscribe = true;
		//Eliminamos la membresia
		log.debug("CourseLocalServiceImpl::unsubscribeStudent::courseId::" + course.getCourseId());
		log.debug("CourseLocalServiceImpl::unsubscribeStudent::userId::" + userId);
		if (course.canUnsubscribe(userId, permissionChecker)) {
			long[] groupIds = new long[] {course.getGroupCreatedId()};
			GroupLocalServiceUtil.unsetUserGroups(userId, groupIds);
			SocialActivityLocalServiceUtil.addActivity(userId, course.getGroupId(), Course.class.getName(), course.getCourseId(), SocialActivityConstants.TYPE_UNSUBSCRIBE, "", course.getUserId());
			
		}else {
			resultUnsubscribe = false;
		}
		return resultUnsubscribe;
	}
	
	@Indexable(type = IndexableType.DELETE)
	public void deleteCourses(long groupId) {
		List<Course> listCourses = coursePersistence.findByGroupId(groupId);
		for(Course course: listCourses) {
			coursePersistence.remove(course);
		}
	}
	
	@Override
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		final PortletDataContext portletDataContext) {
		final ExportActionableDynamicQuery exportActionableDynamicQuery = new ExportActionableDynamicQuery() {
				@Override
				public long performCount() throws PortalException {
					ManifestSummary manifestSummary = portletDataContext.getManifestSummary();

					StagedModelType stagedModelType = getStagedModelType();

					long modelAdditionCount = super.performCount();

					manifestSummary.addModelAdditionCount(stagedModelType,
						modelAdditionCount);

					long modelDeletionCount = ExportImportHelperUtil.getModelDeletionCount(portletDataContext,
							stagedModelType);

					manifestSummary.addModelDeletionCount(stagedModelType,
						modelDeletionCount);

					return modelAdditionCount;
				}
			};

		initActionableDynamicQuery(exportActionableDynamicQuery);

		exportActionableDynamicQuery.setAddCriteriaMethod(new ActionableDynamicQuery.AddCriteriaMethod() {
				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
					Criterion modifiedDateCriterion = portletDataContext.getDateRangeCriteria(
							"modifiedDate");

					if (modifiedDateCriterion != null) {
						Conjunction conjunction = RestrictionsFactoryUtil.conjunction();

						conjunction.add(modifiedDateCriterion);

						Disjunction disjunction = RestrictionsFactoryUtil.disjunction();

						disjunction.add(RestrictionsFactoryUtil.gtProperty(
								"modifiedDate", "lastPublishDate"));

						Property lastPublishDateProperty = PropertyFactoryUtil.forName(
								"lastPublishDate");

						disjunction.add(lastPublishDateProperty.isNull());

						conjunction.add(disjunction);

						modifiedDateCriterion = conjunction;
					}

					Criterion statusDateCriterion = portletDataContext.getDateRangeCriteria(
							"statusDate");

					if ((modifiedDateCriterion != null) &&
							(statusDateCriterion != null)) {
						Disjunction disjunction = RestrictionsFactoryUtil.disjunction();

						disjunction.add(modifiedDateCriterion);
						disjunction.add(statusDateCriterion);

						dynamicQuery.add(disjunction);
					}

					Property workflowStatusProperty = PropertyFactoryUtil.forName(
							"status");

					if (portletDataContext.isInitialPublication()) {
						dynamicQuery.add(workflowStatusProperty.ne(
								WorkflowConstants.STATUS_IN_TRASH));
					}
					else {
						/*StagedModelDataHandler<?> stagedModelDataHandler = StagedModelDataHandlerRegistryUtil.getStagedModelDataHandler(Course.class.getName());

						dynamicQuery.add(workflowStatusProperty.in(
								stagedModelDataHandler.getExportableStatuses()));*/
					}
				}
			});

		exportActionableDynamicQuery.setCompanyId(portletDataContext.getCompanyId());

		exportActionableDynamicQuery.setGroupId(portletDataContext.getScopeGroupId());

		exportActionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<Course>() {
				@Override
				public void performAction(Course course)
					throws PortalException {
					StagedModelDataHandlerUtil.exportStagedModel(portletDataContext,
						course);
				}
			});
		exportActionableDynamicQuery.setStagedModelType(new StagedModelType(
				PortalUtil.getClassNameId(Course.class.getName())));

		return exportActionableDynamicQuery;
	}
	
	public List<Group> getDistinctCourseGroups(long companyId){
		return courseFinder.getDistinctCourseGroups(companyId);
	}
	
	@Override
	public Folder fetchAttachmentsFolder(long userId, long groupId) {
		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);

		Repository repository =
			PortletFileRepositoryUtil.fetchPortletRepository(
				groupId, LMSConstants.SERVICE_NAME);

		try {
			return PortletFileRepositoryUtil.getPortletFolder(
				repository.getRepositoryId(),
				DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
				LMSConstants.SERVICE_NAME);
		}
		catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug(e, e);
			}
		}

		return null;
	}
	
	@ServiceReference(type = ConfigurationProvider.class)
	protected ConfigurationProvider configurationProvider;

	public boolean getAllowAccessToCompletedCourses() {
		boolean allowAccessToCompletedCourses = false;

		try {
			CourseServiceConfiguration courseServiceConfiguration = configurationProvider.getSystemConfiguration(CourseServiceConfiguration.class);

			allowAccessToCompletedCourses = courseServiceConfiguration.allowAccessToCompletedCourses();
		}
		catch (ConfigurationException ce) {
			log.error("Unable to get course service configuration", ce);
		}

		return allowAccessToCompletedCourses;
	}
	
	public String[] getPrerequisiteActivities() {
		String[] prerequisiteActivities = null;
		try {
			CourseServiceConfiguration courseServiceConfiguration = configurationProvider.getSystemConfiguration(CourseServiceConfiguration.class);
			prerequisiteActivities = courseServiceConfiguration.prerequisitesActivity();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		return prerequisiteActivities;
	}
	
	/**
	 * Usar este método para contar los estudiantes de un curso
	 * @param courseId id del curso
	 * @param companyId id de company
	 * @param screenName nombre de usuario
	 * @param firstName nombre
	 * @param lastName apellido
	 * @param emailAddress direccion de correo
	 * @param status estado del usuario (WorkflowConstants)
	 * @param andOperator true si queremos que coincidan screenname, firstname, lastname y emailaddress, false en caso contrario
	 * @return
	 */
	public int countStudentsFromCourse(long courseId, long companyId, String screenName, String firstName, String lastName, String emailAddress, int status, 
			LinkedHashMap<String,Object> params, boolean andOperator){
		return courseFinder.countStudents(courseId, companyId, screenName, firstName, lastName, emailAddress, status, null, params, andOperator);
	}
	
	public int countStudentsFromCourse(long courseId, long companyId, String keywords, int status, 
			LinkedHashMap<String,Object> params){
		return courseFinder.countStudents(courseId, companyId, keywords, keywords, keywords, keywords, status, null, params, false);
	}
	
	public int countStudentsFromCourse(long courseId, long companyId) {
		return countStudentsFromCourse(courseId, companyId, null, null, null, null, WorkflowConstants.STATUS_APPROVED, null, true);
	}
	
	public List<User> getStudentsFromCourse(long courseId, long companyId, String screenName, String firstName, String lastName, String emailAddress, int status, 
			LinkedHashMap<String,Object> params, boolean andOperator, int start, int end, OrderByComparator obc){
		return courseFinder.findStudents(courseId, companyId, screenName, firstName, lastName, emailAddress, status, null, params, andOperator, start, end, obc);
			
	}
	
	public List<User> getStudentsFromCourse(long courseId, long companyId, String keywords, int status, 
			LinkedHashMap<String,Object> params, int start, int end, OrderByComparator obc){
		return courseFinder.findStudents(courseId, companyId, keywords, keywords, keywords, keywords, status, null, params, false, start, end, obc);
	}
	
	@Override
	public Folder addAttachmentsFolder(long userId, long groupId) throws PortalException {

		return doAddFolder(userId, groupId, LMSConstants.SERVICE_NAME);
	}
	
	@ServiceReference(type = FriendlyURLEntryLocalService.class)
	protected FriendlyURLEntryLocalService friendlyURLEntryLocalService;
	
	private static final String SMALL_IMAGE_FOLDER_NAME = "Small Image";
	private static final int UNIQUE_FILE_NAME_TRIES = 50;

}