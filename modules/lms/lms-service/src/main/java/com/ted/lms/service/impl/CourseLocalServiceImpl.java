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
import com.liferay.document.library.kernel.exception.DuplicateFileEntryException;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.expando.kernel.util.ExpandoBridgeUtil;
import com.liferay.exportimport.kernel.configuration.ExportImportConfigurationConstants;
import com.liferay.exportimport.kernel.configuration.ExportImportConfigurationSettingsMapFactoryUtil;
import com.liferay.exportimport.kernel.model.ExportImportConfiguration;
import com.liferay.exportimport.kernel.service.ExportImportConfigurationLocalService;
import com.liferay.exportimport.kernel.service.ExportImportLocalService;
import com.liferay.friendly.url.model.FriendlyURLEntry;
import com.liferay.friendly.url.service.FriendlyURLEntryLocalService;
import com.liferay.message.boards.constants.MBCategoryConstants;
import com.liferay.message.boards.kernel.model.MBCategory;
import com.liferay.message.boards.kernel.service.MBCategoryLocalService;
import com.liferay.message.boards.model.MBMailingList;
import com.liferay.message.boards.service.MBMailingListLocalService;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.backgroundtask.BackgroundTask;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskManager;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskManagerUtil;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.ModelHintsConstants;
import com.liferay.portal.kernel.model.ModelHintsUtil;
import com.liferay.portal.kernel.model.Repository;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.module.configuration.ConfigurationProviderUtil;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.kernel.repository.RepositoryProvider;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector;
import com.liferay.portal.kernel.servlet.taglib.ui.ImageSelectorProcessor;
import com.liferay.portal.kernel.social.SocialActivityManagerUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.FriendlyURLNormalizerUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.liveusers.LiveUsers;
import com.liferay.portal.spring.extender.service.ServiceReference;
import com.liferay.sites.kernel.util.SitesUtil;
import com.liferay.social.kernel.model.SocialActivityConstants;
import com.liferay.trash.exception.RestoreEntryException;
import com.liferay.trash.exception.TrashEntryException;
import com.liferay.trash.model.TrashEntry;
import com.liferay.trash.service.TrashEntryLocalService;
import com.liferay.upload.AttachmentContentUpdater;
import com.opencsv.CSVReader;
import com.ted.lms.configuration.CourseServiceConfiguration;
import com.ted.lms.constants.CourseConstants;
import com.ted.lms.constants.LMSActivityKeys;
import com.ted.lms.constants.LMSConstants;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.constants.LMSRoleConstants;
import com.ted.lms.copy.content.processor.DLReferencesCopyContentProcessor;
import com.ted.lms.copy.permission.ResourceCopy;
import com.ted.lms.exception.ExecutionEndDateException;
import com.ted.lms.exception.ExecutionStartDateException;
import com.ted.lms.exception.RegistrationEndDateException;
import com.ted.lms.exception.RegistrationStartDateException;
import com.ted.lms.internal.background.task.CopyCourseBackgroundTaskExecutor;
import com.ted.lms.model.Course;
import com.ted.lms.model.CourseEval;
import com.ted.lms.model.CourseEvalFactory;
import com.ted.lms.model.CourseResult;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.LearningActivityType;
import com.ted.lms.model.Module;
import com.ted.lms.registry.CourseEvalFactoryRegistryUtil;
import com.ted.lms.service.base.CourseLocalServiceBaseImpl;
import com.ted.lms.service.util.SmallImageHelper;
import com.ted.lms.settings.CoursesGroupServiceSettings;
import com.ted.prerequisite.model.Prerequisite;
import com.ted.prerequisite.service.PrerequisiteRelationLocalServiceUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.text.DateFormat;
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
	 * @throws Exception 
	 */
	@Override
	@Indexable(type = IndexableType.REINDEX)
	public Course addCourse(long userId, long groupId, Map<Locale, String> titleMap, Map<Locale, String> descriptionMap, Map<Locale, String> summaryMap, boolean indexer, 
			Map<Locale, String> friendlyURLMap, long layoutSetPrototypeId, long parentCourseId, long courseTypeId, ImageSelector smallImageSelector, 
			ServiceContext serviceContext) throws Exception {

		User user = userLocalService.getUser(userId);
		
		long courseId = counterLocalService.increment(Course.class.getName());
		Course course = coursePersistence.create(courseId);
		
		course.setGroupId(groupId);
		course.setCompanyId(user.getCompanyId());
		course.setUserId(userId);
		course.setUserName(user.getFullName());
		
		course.setParentCourseId(parentCourseId);
		course.setTitleMap(titleMap);
		course.setDescriptionMap(descriptionMap);
		course.setCourseEvalId(-1);
		course.setCalificationType(-1);
		course.setInscriptionType(-1);
		
		course.setStatus(WorkflowConstants.STATUS_DRAFT);
		course.setStatusByUserId(userId);
		course.setStatusByUserName(user.getFullName());
		course.setStatusDate(new Date());
		if(smallImageSelector != null) {
			course.setSmallImageId(addSmallImageCourse(userId, groupId, course.getCourseId(), smallImageSelector));
		}
		
		course.setUuid(serviceContext.getUuid());
		
		//Guardamos la url si no es vacía para cuando creemos el sitio web
		// Friendly URLs

		Map<String, String> urlGroupMap = getURLGroupMap(groupId, course.getCourseId(), friendlyURLMap);
		
		//Creamos el group asociado, le cambiamos al nombre al grupo porque no deja crear dos con el mismo nombre por el groupKey
		
		String urlGroup = urlGroupMap.get(LocaleUtil.toLanguageId(LocaleUtil.getDefault()));
		if(Validator.isNull(urlGroup)) {
			urlGroup = getFriendlyURL(user.getCompanyId(), urlGroup, course.getTitleMap());
			urlGroupMap.put(LocaleUtil.toLanguageId(LocaleUtil.getDefault()), urlGroup.substring(1));
		}
		
		friendlyURLEntryLocalService.addFriendlyURLEntry(groupId, classNameLocalService.getClassNameId(Course.class),
				course.getCourseId(), urlGroupMap, serviceContext);
	
		int membershipRestriction = GroupConstants.DEFAULT_MEMBERSHIP_RESTRICTION;

		if (course.getParentCourseId() != GroupConstants.DEFAULT_PARENT_GROUP_ID) {
			membershipRestriction =GroupConstants.MEMBERSHIP_RESTRICTION_TO_PARENT_SITE_MEMBERS;
		}
		
		
		long parentGroupId = GroupConstants.DEFAULT_PARENT_GROUP_ID;
		if(course.getParentCourseId() != CourseConstants.DEFAULT_PARENT_COURSE_ID) {
			Course parentCourse = coursePersistence.fetchByPrimaryKey(course.getParentCourseId());
			if(parentCourse != null) {
				parentGroupId = parentCourse.getGroupCreatedId();
			}
		}
		
		Group group = GroupLocalServiceUtil.addGroup(userId, parentGroupId, Course.class.getName(), courseId, 0, titleMap, course.getDescriptionMap(), 
				GroupConstants.TYPE_SITE_OPEN, true, membershipRestriction, urlGroup, 
				true, false, true, serviceContext);
		
		course.setGroupCreatedId(group.getGroupId());
		
		LiveUsers.joinGroup(user.getCompanyId(), group.getGroupId(), userId);

		SitesUtil.updateLayoutSetPrototypesLinks(group, layoutSetPrototypeId, 0, true,false);
	
		course = coursePersistence.update(course);
		
		resourceLocalService.addResources(course.getCompanyId(), course.getGroupId(), course.getUserId(),  Course.class.getName(), course.getCourseId(), false, true, false);
		
		AssetEntry assetEntry = assetEntryLocalService.updateEntry(
				userId, groupId, course.getCreateDate(),
				course.getModifiedDate(), Course.class.getName(),
				course.getCourseId(), course.getUuid(), courseTypeId, serviceContext.getAssetCategoryIds(),
				serviceContext.getAssetTagNames(), true, indexer, null, null, null,
				null, ContentTypes.TEXT_HTML, course.getTitle(), course.getDescription(), null, null,
				null, 0, 0, serviceContext.getAssetPriority());
		
		assetEntry.setSummaryMap(summaryMap);
		assetEntryLocalService.updateAssetEntry(assetEntry);
			
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
	public Course updateCourse(long userId, long courseId, int registrationStartMonth, int registrationStartDay, int registrationStartYear, int registrationStartHour, int registrationStartMinute, 
			int registrationEndMonth, int registrationEndDay, int registrationEndYear, int registrationEndHour, int registrationEndMinute, 
			int executionStartMonth, int executionStartDay, int executionStartYear, int executionStartHour, int executionStartMinute, 
			int executionEndMonth, int executionEndDay, int executionEndYear, int executionEndHour, int executionEndMinute, 
			int typeSite, long inscriptionType, long courseEvalId, long calificationType, int maxUsers, int status,
			ServiceContext serviceContext) throws PortalException {
		
		User user = userLocalService.getUser(userId);

		Date registrationStartDate = PortalUtil.getDate(registrationStartMonth, registrationStartDay, registrationStartYear, registrationStartHour,
				registrationStartMinute, user.getTimeZone(), RegistrationStartDateException.class);
		Date registrationEndDate = PortalUtil.getDate(registrationEndMonth, registrationEndDay, registrationEndYear, registrationEndHour,
				registrationEndMinute, user.getTimeZone(), RegistrationEndDateException.class);
		Date executionStartDate = PortalUtil.getDate(executionStartMonth, executionStartDay, executionStartYear, executionStartHour,
				executionStartMinute, user.getTimeZone(), ExecutionStartDateException.class);
		Date executionEndDate = PortalUtil.getDate(executionEndMonth, executionEndDay, executionEndYear, executionEndHour,
				executionEndMinute, user.getTimeZone(), ExecutionEndDateException.class);
		
		return updateCourse(userId, courseId, registrationStartDate, registrationEndDate, executionStartDate, executionEndDate, 
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
	public Course updateCourse(long userId, long courseId, Date registrationStartDate, Date registrationEndDate, Date executionStartDate, Date executionEndDate, 
			int typeSite, long inscriptionType, long courseEvalId, long calificationType, int maxUsers, int status,
			ServiceContext serviceContext) throws PortalException {
		
		User user = userLocalService.getUser(userId);
		
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
		course.setUserId(userId);
		course.setUserName(user.getFullName());
		if(status != course.getStatus()) {
			course.setStatusByUserId(userId);
			course.setStatusByUserName(user.getFullName());
			course.setStatusByUserUuid(user.getUserUuid());
			course.setStatusDate(new Date());
		}
		course.setStatus(status);
		course = coursePersistence.update(course);
		
		//Actualizamos el tipo del sitio
		Group group = groupLocalService.getGroup(course.getGroupCreatedId());
		group.setType(typeSite);
		groupLocalService.updateGroup(group);
		
		updateAsset(userId, course, serviceContext.getAssetCategoryIds(), serviceContext.getAssetTagNames(),
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
	 * @throws PortalException 
	 */
	@Override
	@Indexable(type = IndexableType.REINDEX)
	public Course updateCourse(long userId, long courseId, boolean welcome, Map<Locale, String> welcomeSubjectMap,
			Map<Locale, String> welcomeMsgMap, boolean goodbye, Map<Locale, String> goodbyeSubjectMap,
			Map<Locale, String> goodbyeMsgMap, boolean deniedInscription, Map<Locale, String> deniedInscriptionSubjectMap,
			Map<Locale, String> deniedInscriptionMsgMap, int status) throws PortalException {
		
		Course course = coursePersistence.findByPrimaryKey(courseId);
		User user = userLocalService.getUser(userId);	
		
		course.setUserId(userId);
		course.setUserName(user.getFullName());
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
			course.setStatusByUserId(userId);
			course.setStatusByUserName(user.getFullName());
			course.setStatusByUserUuid(user.getUserUuid());
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
	public Course updateCourse(long userId, long courseId, int status, ServiceContext serviceContext) throws PrincipalException, PortalException {		
		
		Course course = updateStatus(userId, courseId, status, serviceContext);
		
		assetLinkLocalService.updateLinks(userId, course.getAssetEntry().getEntryId(), serviceContext.getAssetLinkEntryIds(),AssetLinkConstants.TYPE_RELATED);
		
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
	public Course updateCourse(long userId, long courseId, Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
			Map<Locale, String> summaryMap, boolean indexer, Map<Locale, String> friendlyURLMap, long layoutSetPrototypeId, 
			ImageSelector smallImageSelector, ServiceContext serviceContext) throws Exception {
		
		User user = userLocalService.getUser(userId);
		
		Course course = coursePersistence.findByPrimaryKey(courseId);
		
		Date now = new Date();
		
		course.setTitleMap(titleMap);
		course.setDescriptionMap(descriptionMap);
		course.setUserId(userId);
		course.setUserName(user.getFullName());
		
		course.setModifiedDate(now);
		if(smallImageSelector != null) {
			course.setSmallImageId(addSmallImageCourse(userId, course.getGroupId(), course.getCourseId(), smallImageSelector));
		}
		
		coursePersistence.update(course);
		
		AssetEntry assetEntry = assetEntryLocalService.getEntry(Course.class.getName(), courseId);
		assetEntry.setSummaryMap(summaryMap);
		assetEntry.setVisible(indexer);
		assetEntry.setModifiedDate(now);
		assetEntryLocalService.updateAssetEntry(assetEntry);
		
		// Friendly URLs

		List<FriendlyURLEntry> friendlyURLEntries =
			friendlyURLEntryLocalService.getFriendlyURLEntries(course.getGroupId(),classNameLocalService.getClassNameId(Course.class),
					course.getCourseId());

		for (FriendlyURLEntry friendlyURLEntry : friendlyURLEntries) {
			friendlyURLEntryLocalService.deleteFriendlyURLEntry(friendlyURLEntry);
		}
		
		Map<String, String> urlGroupMap = getURLGroupMap(course.getGroupId(), course.getCourseId(), friendlyURLMap);

		String urlGroup = urlGroupMap.get(LocaleUtil.toLanguageId(serviceContext.getLocale()));

		friendlyURLEntryLocalService.addFriendlyURLEntry(course.getGroupId(), classNameLocalService.getClassNameId(Course.class),
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
	
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Course updateStatus(long userId, long courseId, int status, ServiceContext serviceContext) throws PortalException {

		//Curso

		User user = userLocalService.getUser(userId);
		Date now = new Date();

		Course course = coursePersistence.findByPrimaryKey(courseId);

		int oldStatus = course.getStatus();

		course.setStatus(status);
		course.setStatusByUserId(user.getUserId());
		course.setStatusByUserName(user.getFullName());
		course.setStatusDate(serviceContext.getModifiedDate(now));

		coursePersistence.update(course);

		AssetEntry assetEntry = assetEntryLocalService.fetchEntry(Course.class.getName(), courseId);

		if ((assetEntry == null) || (assetEntry.getPublishDate() == null)) {
			serviceContext.setCommand(Constants.ADD);
		}

		JSONObject extraDataJSONObject = JSONFactoryUtil.createJSONObject();

		extraDataJSONObject.put("title", course.getTitle());

		if (status == WorkflowConstants.STATUS_APPROVED || status == WorkflowConstants.STATUS_DRAFT) {

			// Social

			if ((oldStatus != WorkflowConstants.STATUS_IN_TRASH) &&
				(oldStatus != WorkflowConstants.STATUS_SCHEDULED)) {

				if (serviceContext.isCommandUpdate()) {
					SocialActivityManagerUtil.addActivity(
						user.getUserId(), course, LMSActivityKeys.UPDATE_COURSE,
						extraDataJSONObject.toString(), 0);
				} else {
					SocialActivityManagerUtil.addUniqueActivity(
						user.getUserId(), course, LMSActivityKeys.ADD_COURSE,
						extraDataJSONObject.toString(), 0);
				}
			}

			// Trash

			if (oldStatus == WorkflowConstants.STATUS_IN_TRASH) {

				trashEntryLocalService.deleteEntry(
					Course.class.getName(), courseId);
				
			}
			
			if(oldStatus == WorkflowConstants.STATUS_INACTIVE) {
				CourseEvalFactory courseEvalFactory = CourseEvalFactoryRegistryUtil.getCourseEvalFactoryByType(course.getCourseEvalId());
				CourseEval courseEval = courseEvalFactory.getCourseEval(course);
				courseEval.onOpenCourse();
			}
			
			if(oldStatus == WorkflowConstants.STATUS_IN_TRASH || oldStatus == WorkflowConstants.STATUS_INACTIVE) {
				Group group = groupLocalService.getGroup(course.getGroupCreatedId());
				group.setActive(true);
				groupLocalService.updateGroup(group);
			}
		} else {

			// Social
			if ((status == WorkflowConstants.STATUS_SCHEDULED) && (oldStatus != WorkflowConstants.STATUS_IN_TRASH)) {

				if (serviceContext.isCommandUpdate()) {
					SocialActivityManagerUtil.addActivity(user.getUserId(), course, LMSActivityKeys.UPDATE_COURSE,extraDataJSONObject.toString(), 0);
				}
				else {
					SocialActivityManagerUtil.addUniqueActivity(user.getUserId(), course, LMSActivityKeys.ADD_COURSE,extraDataJSONObject.toString(), 0);
				}
			}

			// Trash
			if (status == WorkflowConstants.STATUS_IN_TRASH) {
				trashEntryLocalService.addTrashEntry(
					userId, course.getGroupId(), Course.class.getName(),
					course.getCourseId(), course.getUuid(), null, oldStatus, null,
					null);
				
			} else if (oldStatus == WorkflowConstants.STATUS_IN_TRASH) {

				trashEntryLocalService.deleteEntry(Course.class.getName(), courseId);
			}
			
			if(status == WorkflowConstants.STATUS_INACTIVE) {
				CourseEvalFactory courseEvalFactory = CourseEvalFactoryRegistryUtil.getCourseEvalFactoryByType(course.getCourseEvalId());
				CourseEval courseEval = courseEvalFactory.getCourseEval(course);
				courseEval.onCloseCourse();
			}
		
			if(status == WorkflowConstants.STATUS_IN_TRASH || status == WorkflowConstants.STATUS_INACTIVE) {
				Group group = groupLocalService.getGroup(course.getGroupCreatedId());
				group.setActive(false);
				groupLocalService.updateGroup(group);
			}
		}

		return course;
	}
	
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Course moveEntryToTrash(long userId, Course course) throws PortalException {

		if (course.isInTrash()) {
			throw new TrashEntryException();
		}

		int oldStatus = course.getStatus();

		if (oldStatus == WorkflowConstants.STATUS_PENDING) {
			course.setStatus(WorkflowConstants.STATUS_DRAFT);

			coursePersistence.update(course);
		}

		course = updateStatus(
			userId, course.getCourseId(), WorkflowConstants.STATUS_IN_TRASH,
			new ServiceContext());

		// Social

		JSONObject extraDataJSONObject = JSONFactoryUtil.createJSONObject();

		extraDataJSONObject.put("title", course.getTitle());

		SocialActivityManagerUtil.addActivity(
			userId, course, SocialActivityConstants.TYPE_MOVE_TO_TRASH,
			extraDataJSONObject.toString(), 0);

		// Workflow

		if (oldStatus == WorkflowConstants.STATUS_PENDING) {
			workflowInstanceLinkLocalService.deleteWorkflowInstanceLink(
				course.getCompanyId(), course.getGroupId(),
				Course.class.getName(), course.getCourseId());
		}

		return course;
	}
	
	/**
	 * A esta función no se le debe llamar para inscribir alumnos teniendo en cuenta el método de inscripción,
	 * únicamente es para inscribir como administrador a usuarios en cursos
	 * @param userId identifador del usuario que está inscribiendo
	 * @param groupId identificador del grupo del curso
	 * @param addUserIds usuarios que se quieren añadir al curso
	 * @param roleId identificador del rol que queremos dar
	 * @param ServiceContext contexto de la petición
	 * @throws PortalException
	 */
	public void addUserCourse(long userId, long courseId, long[] addUserIds, long roleId) throws PortalException {
		Course course = coursePersistence.findByPrimaryKey(courseId);
		
		userLocalService.addGroupUsers(course.getGroupCreatedId(), addUserIds);
		userGroupRoleLocalService.addUserGroupRoles(addUserIds, course.getGroupCreatedId(), roleId);
		
		LiveUsers.joinGroup(course.getCompanyId(), course.getGroupCreatedId(), addUserIds);
		
		if(roleId == roleLocalService.getRole(course.getCompanyId(), LMSRoleConstants.STUDENT).getRoleId()) {
			for(long addUserId: addUserIds) {
				if(courseResultLocalService.fetchCourseResult(courseId, addUserId) == null) {
					courseResultLocalService.addCourseResult(userId, course.getCourseId(), addUserId);
				}
			}
		}
	}
	
	/**
	 * A esta función se le debe llamar cuando un administrador desinscriba a un usuario
	 * @param courseId identificador del curso
	 * @param removeUserIds usuarios a desinscribir
	 * @param roleId identificador del rol del que se le va a desinscrbir
	 * @param serviceContext
	 * @throws PortalException
	 */
	public void unsetUserCourse(long courseId, long[] removeUserIds, long roleId, ServiceContext serviceContext) throws PortalException {
		Course course = coursePersistence.findByPrimaryKey(courseId);
		
		userLocalService.unsetGroupUsers(course.getGroupCreatedId(), removeUserIds, serviceContext);
		userGroupRoleLocalService.deleteUserGroupRoles(removeUserIds, course.getGroupCreatedId(), roleId);

		LiveUsers.leaveGroup(course.getCompanyId(), course.getGroupCreatedId(), removeUserIds);
	}

	
	@Override
	public Course moveEntryToTrash(long userId, long courseId)
		throws PortalException {

		Course course = coursePersistence.findByPrimaryKey(courseId);

		return courseLocalService.moveEntryToTrash(userId, course);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Course restoreEntryFromTrash(long userId, long courseId)
		throws PortalException {

		Course course = coursePersistence.findByPrimaryKey(courseId);

		if (!course.isInTrash()) {
			throw new RestoreEntryException(
				RestoreEntryException.INVALID_STATUS);
		}

		TrashEntry trashEntry = trashEntryLocalService.getEntry(
			Course.class.getName(), courseId);

		course = updateStatus(
			userId, courseId, trashEntry.getStatus(), new ServiceContext());

		// Social

		JSONObject extraDataJSONObject = JSONFactoryUtil.createJSONObject();

		extraDataJSONObject.put("title", course.getTitle());

		SocialActivityManagerUtil.addActivity(
			userId, course, SocialActivityConstants.TYPE_RESTORE_FROM_TRASH,
			extraDataJSONObject.toString(), 0);

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
	public Course deleteCourse(long courseId) throws PortalException {
		Course course = coursePersistence.findByPrimaryKey(courseId);
		
		return deleteCourse(course);
	}
	
	@Override
	@Indexable(type = IndexableType.DELETE)
	public Course deleteCourse(Course course) throws PortalException {
		
		//Ratings
		ratingsStatsLocalService.deleteStats(Course.class.getName(), course.getCourseId());
		
		//Curso
		coursePersistence.remove(course);
		
		//Imágenes
		if(course.getSmallImageId() > 0) {
			PortletFileRepositoryUtil.deletePortletFileEntry(course.getSmallImageId());
		}
		
		//Resources
		resourceLocalService.deleteResource(course.getCompanyId(), Course.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL, course.getCourseId());
		
		//Asset
		assetEntryLocalService.deleteEntry(Course.class.getName(), course.getCourseId());
		
		//Expandos
		expandoRowLocalService.deleteRows(course.getCourseId());
		
		// Friendly URL
		friendlyURLEntryLocalService.deleteFriendlyURLEntry(course.getGroupId(), Course.class, course.getCourseId());
		
		// Trash
		trashEntryLocalService.deleteEntry(Course.class.getName(), course.getCourseId());
		
		// Workflow
		workflowInstanceLinkLocalService.deleteWorkflowInstanceLinks(course.getCompanyId(), course.getGroupId(),
					Course.class.getName(), course.getCourseId());
		
		//Group
		groupLocalService.deleteGroup(course.getGroupCreatedId());
		
		//CourseEval
		try {
			CourseEvalFactory courseEvalFactory = CourseEvalFactoryRegistryUtil.getCourseEvalFactoryByType(course.getCourseEvalId());
			CourseEval courseEval = courseEvalFactory.getCourseEval(course);
			courseEval.onDeleteCourse();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//CourseResult
		List<CourseResult> courseResults = courseResultLocalService.getCourseResults(course.getCourseId());
		courseResults.forEach(courseResult -> courseResultLocalService.deleteCourseResult(courseResult));
		
		//Módulos
		moduleLocalService.deleteModules(course.getGroupCreatedId());
		
		//Actividades sin módulo
		List<LearningActivity> activities = learningActivityLocalService.getLearningActivitiesWithoutModule(course.getGroupCreatedId());
		activities.forEach(activity -> learningActivityLocalService.deleteLearningActivity(activity));
		
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
	public List<Course> searchCourses(long companyId, String freeText, String language, int[] status, long parentCourseId, long groupId, LinkedHashMap<String, Object> params, int start, int end,
			OrderByComparator<Course> obc){
		return courseFinder.findByKeywords(companyId, freeText, language, status, parentCourseId, groupId, params, start, end, obc);
	}
	
	/**
	 * Método para buscar cursos
	 */
	public int countCourses(long companyId, String freeText, String language, int[] status, long parentCourseId, long groupId, LinkedHashMap<String, Object> params){
		return courseFinder.countByKeywords(companyId, freeText, language, status, parentCourseId, groupId, params, false);
	}
	
	/**
	 * Método para buscar cursos
	 */
	public List<Course> searchCourses(long companyId, String title, String description, String language, int[] status, long parentCourseId, long groupId, 
			LinkedHashMap<String, Object> params, boolean andOperator, int start, int end,
			OrderByComparator<Course> obc){
		return courseFinder.findByC(companyId, title, description, language, status, parentCourseId, groupId, params, andOperator, start, end, obc);
	}
	
	/**
	 * Método para buscar cursos
	 */
	public int countCourses(long companyId, String title, String description, String language, int[] status, long parentCourseId, long groupId, LinkedHashMap<String, Object> params, 
			boolean andOperator){
		return courseFinder.countByC(companyId, title, description, language, status, parentCourseId, groupId, params, andOperator);
	}
	
	public List<Course> getChildsRegistredUser(long parentCourseId, long userId){
		return courseFinder.findChildRegistredUser(parentCourseId, userId);
	}
	
	@Indexable(type = IndexableType.DELETE)
	public void deleteCourses(long groupId) {
		List<Course> listCourses = coursePersistence.findByGroupId(groupId);
		for(Course course: listCourses) {
			coursePersistence.remove(course);
		}
	}

	@Override
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

	public boolean getAllowAccessToCompletedCourses(long companyId) {
		boolean allowAccessToCompletedCourses = false;

		try {
			CourseServiceConfiguration courseServiceConfiguration = configurationProvider.getCompanyConfiguration(CourseServiceConfiguration.class, companyId);

			allowAccessToCompletedCourses = courseServiceConfiguration.allowAccessToCompletedCourses();
		}
		catch (ConfigurationException ce) {
			log.error("Unable to get course service configuration", ce);
		}

		return allowAccessToCompletedCourses;
	}
	
	public String[] getPrerequisiteActivities(long companyId) {
		String[] prerequisiteActivities = null;
		try {
			CourseServiceConfiguration courseServiceConfiguration = ConfigurationProviderUtil.getCompanyConfiguration(CourseServiceConfiguration.class, companyId);
			prerequisiteActivities = courseServiceConfiguration.prerequisitesActivity();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		return prerequisiteActivities;
	}
	
	public String[] getPrerequisiteModules(long companyId) {
		String[] prerequisiteModules = null;
		try {
			CourseServiceConfiguration courseServiceConfiguration = configurationProvider.getCompanyConfiguration(CourseServiceConfiguration.class, companyId);
			prerequisiteModules = courseServiceConfiguration.prerequisitesModules();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		return prerequisiteModules;
	}
	
	public String[] getPrerequisiteCourses(long companyId) {
		String[] prerequisiteCourses = null;
		try {
			CourseServiceConfiguration courseServiceConfiguration = configurationProvider.getCompanyConfiguration(CourseServiceConfiguration.class, companyId);
			prerequisiteCourses = courseServiceConfiguration.prerequisitesCourses();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		return prerequisiteCourses;
	}
	
	public String[] getPostconditionCourses(long companyId) {
		String[] postconditionCourses = null;
		try {
			CourseServiceConfiguration courseServiceConfiguration = configurationProvider.getCompanyConfiguration(CourseServiceConfiguration.class, companyId);
			postconditionCourses = courseServiceConfiguration.postconditionCourses();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		return postconditionCourses;
	}
	
	public JSONObject importEditions(long userId, long courseId, FileEntry fileEntry) throws PortalException, IOException {
		User userModified = userLocalService.getUser(userId);
		JSONObject result = JSONFactoryUtil.createJSONObject();
		
		try (InputStream inputStream = dlFileEntryLocalService.getFileAsStream(
				fileEntry.getFileEntryId(), fileEntry.getVersion(), false);
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				CSVReader reader = new CSVReader(inputStreamReader, CharPool.SEMICOLON)) {
			
			Course parentCourse = coursePersistence.findByPrimaryKey(courseId);
			
			DateFormat dateFormatDateTime = DateFormatFactoryUtil.getDateTime(userModified.getLocale(), userModified.getTimeZone());
			
			String[] currLine = reader.readNext();
			String title = null;
			String friendlyURL = null;
			Date registrationStartDate = null;
			Date registrationEndDate = null;
			Date executionStartDate = null;
			Date executionEndDate = null;
			int line = 1;
			Map<Locale, String> friendlyURLMap = null;
			String[] languageIds = {LocaleUtil.toLanguageId(LocaleUtil.getSiteDefault())};
			String[] valuesTitle = new String[1];
			String[] valuesFriendlyURL = new String[1];
			long layoutSetPrototypeId = parentCourse.getLayoutSetPrototypeId();
			ServiceContext serviceContext = null;
			
			while ((currLine = reader.readNext()) != null) {
				line++;
				try {
					title = currLine[0];
					friendlyURL = currLine[1];
					String registrationStartDateCSV = currLine[2];
					String registrationEndDateCSV = currLine[3];
					String executionStartDateCSV = currLine[4];
					String executionEndDateCSV = currLine[5];
					
					if(Validator.isNull(title)) {
						result.put(String.valueOf(line), LanguageUtil.get(userModified.getLocale(),"course-admin.editions.import.error.empty-edition-name"));
					}else if(Validator.isNull(registrationStartDateCSV) || Validator.isNull(registrationEndDateCSV)) {
						result.put(String.valueOf(line), LanguageUtil.get(userModified.getLocale(),"course-admin.editions.import.error.empty-inscription-dates"));
					}else if(Validator.isNull(executionStartDateCSV) || Validator.isNull(executionEndDateCSV)) {
						result.put(String.valueOf(line), LanguageUtil.get(userModified.getLocale(),"course-admin.editions.import.error.empty-execution-dates"));
					}else if(Validator.isNotNull(friendlyURL) && groupLocalService.fetchFriendlyURLGroup(userModified.getCompanyId(), friendlyURL) != null) {
						result.put(String.valueOf(line), LanguageUtil.get(userModified.getLocale(),"course-admin.editions.import.error.edition-friendly-url-already-exists"));
					}else {
						registrationStartDate = GetterUtil.getDate(registrationStartDateCSV, dateFormatDateTime);
						registrationEndDate = GetterUtil.getDate(registrationEndDateCSV, dateFormatDateTime);
						executionStartDate = GetterUtil.getDate(executionStartDateCSV, dateFormatDateTime);
						executionEndDate = GetterUtil.getDate(executionEndDateCSV, dateFormatDateTime);
						
						valuesTitle[0] = title;
						title = LocalizationUtil.updateLocalization(LocalizationUtil.getLocalizationMap(languageIds, valuesTitle), "", "Title", LocaleUtil.toLanguageId(LocaleUtil.getSiteDefault()));
						
						serviceContext = new ServiceContext();
						serviceContext.setCompanyId(parentCourse.getCompanyId());
						serviceContext.setScopeGroupId(parentCourse.getGroupId());
						
						if(Validator.isNotNull(friendlyURL)) {
							valuesFriendlyURL[0] = friendlyURL;
							friendlyURLMap = LocalizationUtil.getLocalizationMap(languageIds, valuesFriendlyURL);
						}else {
							friendlyURLMap = null;
						}
						
						try {
							copyCourse(userId, courseId, courseId, title, friendlyURLMap, layoutSetPrototypeId, registrationStartDate, registrationEndDate, 
									executionStartDate, executionEndDate, false, false, serviceContext);
							
							result.put(String.valueOf(line), LanguageUtil.get(userModified.getLocale(),"correct"));
						} catch (Exception e) {
							e.printStackTrace();
							result.put(String.valueOf(line), e.getLocalizedMessage());
						}
					}
				}catch (ArrayIndexOutOfBoundsException e) {
					result.put(String.valueOf(line), LanguageUtil.get(userModified.getLocale(), "course-admin.editions.import.error.empty-line"));
				}catch (Exception e) {
					e.printStackTrace();
					result.put(String.valueOf(line), e.getLocalizedMessage());
				}
				
			}
		}
		
		return result;
	}
	
	public JSONObject importCourseMembers(long userId, long courseId, long roleId,  FileEntry fileEntry) throws PortalException, IOException {
		
		Role roleStudent = roleLocalService.getRole(fileEntry.getCompanyId(), LMSRoleConstants.STUDENT);
		boolean isStudent = roleStudent.getRoleId() == roleId;
		
		User userModified = userLocalService.getUser(userId);
		
		Company company = companyLocalService.getCompany(userModified.getCompanyId());
		
		String authType = company.getAuthType();
		JSONObject result = JSONFactoryUtil.createJSONObject();
		
		try (InputStream inputStream = dlFileEntryLocalService.getFileAsStream(
				fileEntry.getFileEntryId(), fileEntry.getVersion(), false);
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				CSVReader reader = new CSVReader(inputStreamReader, CharPool.SEMICOLON)) {
			
			DateFormat dateFormatDateTime = DateFormatFactoryUtil.getDateTime(userModified.getLocale(), userModified.getTimeZone());
			
			String[] currLine = reader.readNext();
			String userAuth = null;
			User user = null;
			Date allowStartDate = null;
			Date allowEndDate = null;
			int line = 1;
			
			while ((currLine = reader.readNext()) != null) {
				line++;
				userAuth = currLine[0];
				
				if (Validator.isNotNull(userAuth)){
					
					try {
						if (CompanyConstants.AUTH_TYPE_SN.equalsIgnoreCase(authType)) {
							user = userLocalService.getUserByScreenName(company.getCompanyId(), userAuth.trim());
						}else if(CompanyConstants.AUTH_TYPE_EA.equalsIgnoreCase(authType)){
							user = userLocalService.getUserByEmailAddress(company.getCompanyId(), userAuth.trim());
						}else{
							user = userLocalService.getUser(Long.parseLong(userAuth.trim()));
						}
						
						if(user != null){
							if(log.isDebugEnabled())log.debug("User Name:: " + user.getFullName() );
							
							addUserCourse(userId, courseId, new long[] {user.getUserId()}, roleId);
							
							if(isStudent) {
								String allowStartDateStr = currLine[2].trim();
								String allowEndDateStr = currLine[3].trim();
								
								if(Validator.isNotNull(allowStartDateStr)){
									allowStartDate = GetterUtil.getDate(allowStartDateStr, dateFormatDateTime);
								}else{
									allowStartDate=null;
								}
								if(Validator.isNotNull(allowEndDateStr)){
									allowEndDate = GetterUtil.getDate(allowEndDateStr, dateFormatDateTime);
								}else{
									allowEndDate=null;
								}
								
								if(Validator.isNotNull(allowStartDate) || Validator.isNotNull(allowEndDate) ){												
									
									CourseResult courseResult = courseResultLocalService.getCourseResult(courseId, user.getUserId());
									courseResult.setAllowStartDate(allowStartDate);
									courseResult.setAllowEndDate(allowEndDate);
									courseResultLocalService.updateCourseResult(courseResult);												
								}
							}
							result.put(String.valueOf(line), LanguageUtil.format(userModified.getLocale(),
									 "correct", userAuth));
						}else{
							result.put(String.valueOf(line), LanguageUtil.format(userModified.getLocale(),
											 "course-admin.assign-members.import.error.user-id-not-found", userAuth));
						}
					} catch (NumberFormatException e) {
						result.put(String.valueOf(line), LanguageUtil.format(userModified.getLocale(),
								 "course-admin.assign-members.import.error.user-id-bad-format", userAuth));
					} catch (PortalException e) {
						result.put(String.valueOf(line), LanguageUtil.format(userModified.getLocale(),
								 "course-admin.assign-members.import.error.user-id-not-found", userAuth));
					} catch (Exception e){
						e.printStackTrace();
						result.put(String.valueOf(line), LanguageUtil.format(userModified.getLocale(),
								 "course-admin.assign-members.import.error", userAuth));
					}
				}
			}
		}
		
		return result;
	}
	
	public Course copyCourse(long userId, long courseId, long parentCourseId, String title, Map<Locale, String> friendlyURLMap, 
			long layoutSetPrototypeId, Date registrationStartDate, Date registrationEndDate, Date executionStartDate, Date executionEndDate,
			boolean copyForum, boolean copyDocuments, ServiceContext serviceContext) throws Exception {
		
		log.debug("copyCourse: " + courseId);
		
		Course oldCourse = coursePersistence.fetchByPrimaryKey(courseId);
		AssetEntry oldAssetEntry = oldCourse.getAssetEntry();
		
		Map<Locale, String> titleMap = LocalizationUtil.getLocalizationMap(title);
		
		ImageSelector smallImageSelector = null;
		
		if(oldCourse.getSmallImageId() > 0) {
		
			FileEntry fileEntry = PortletFileRepositoryUtil.getPortletFileEntry(oldCourse.getSmallImageId());
			
			InputStream contentStream = fileEntry.getContentStream();
			byte[] imageBytes = FileUtil.getBytes(contentStream);
	
			smallImageSelector = new ImageSelector(imageBytes, fileEntry.getFileName(), fileEntry.getMimeType(), StringPool.BLANK);
		}
		
		if(friendlyURLMap == null) {
			friendlyURLMap = new HashMap<>();
		}
		
		serviceContext.setUserId(userId);
		serviceContext.setAssetCategoryIds(oldAssetEntry.getCategoryIds());
		serviceContext.setAssetEntryVisible(oldAssetEntry.isVisible());
		serviceContext.setAssetPriority(oldAssetEntry.getPriority());
		serviceContext.setAssetTagNames(oldAssetEntry.getTagNames());
		
		log.debug("primer paso comienzo ");
		
		//Primer paso, creamos el curso
		Course newCourse = addCourse(userId, serviceContext.getScopeGroupId(), titleMap, oldCourse.getDescriptionMap(), oldAssetEntry.getSummaryMap(), oldAssetEntry.isVisible(), 
				friendlyURLMap, layoutSetPrototypeId, parentCourseId, oldCourse.getCourseTypeId(), smallImageSelector, serviceContext);
		
		log.debug("primer paso fin: " + newCourse.getCourseId());
		
		//Segundo paso, copiamos la configuración
		newCourse = updateCourse(userId, newCourse.getCourseId(), registrationStartDate, registrationEndDate, executionStartDate, executionEndDate, 
				oldCourse.getTypeSite(), oldCourse.getInscriptionType(), oldCourse.getCourseEvalId(), oldCourse.getCalificationType(), 
				oldCourse.getMaxUsers(), WorkflowConstants.STATUS_DRAFT, serviceContext);
		
		log.debug("segundo paso fin ");
		
		//Copiamos los expandos
		ExpandoBridgeUtil.copyExpandoBridgeAttributes(oldCourse.getExpandoBridge(), newCourse.getExpandoBridge());
		
		//Actualizamos los mensajes
		newCourse = updateCourse(userId, newCourse.getCourseId(), oldCourse.isWelcome(), oldCourse.getWelcomeSubjectMap(), oldCourse.getWelcomeMsgMap(), 
				oldCourse.isGoodbye(), oldCourse.getGoodbyeSubjectMap(), oldCourse.getGoodbyeMsgMap(), oldCourse.isDeniedInscription(), 
				oldCourse.getDeniedInscriptionSubjectMap(), oldCourse.getDeniedInscriptionMsgMap(), WorkflowConstants.STATUS_DRAFT);
		
		log.debug("tercer paso fin ");

		long[] assetLinkIds = assetLinkLocalService.getLinks(newCourse.getAssetEntry().getEntryId(), AssetLinkConstants.TYPE_RELATED).stream().mapToLong(assetLink -> assetLink.getLinkId()).toArray();
		
		//Actualizamos los contenidos relacionados
		assetLinkLocalService.updateLinks(userId, newCourse.getAssetEntry().getEntryId(), assetLinkIds,AssetLinkConstants.TYPE_RELATED);
		
		resourceCopy.copyModelResourcePermissions(newCourse.getCompanyId(), Course.class.getName(), oldCourse.getCourseId(), newCourse.getCourseId());
		
		copyCourseImages(oldCourse, newCourse);
		
		newCourse = coursePersistence.update(newCourse);
		
		Map<Long, Long> modulesRelation = new HashMap<>();
		Map<Long, Long> activitiesRelation = new HashMap<>();
		
		//Ahora duplicamos los módulos
		List<Module> oldModules = moduleLocalService.getModules(oldCourse.getGroupCreatedId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		Module newModule = null;
		for(Module oldModule: oldModules) {
			log.debug("copiamos módulo: " + oldModule.getModuleId());
			serviceContext.setUuid(oldModule.getUuid());
			newModule = moduleLocalService.copyModule(userId, oldModule, newCourse.getGroupCreatedId(), activitiesRelation, serviceContext);
			modulesRelation.put(oldModule.getModuleId(), newModule.getModuleId());
		}
		
		//Ahora actualizamos el método de evaluación, las poscondiciones y precondiciones
		CourseEvalFactory courseEvalFactory = CourseEvalFactoryRegistryUtil.getCourseEvalFactoryByType(newCourse.getCourseEvalId());
		CourseEval courseEval = courseEvalFactory.getCourseEval(newCourse);
		courseEval.copyCourse(oldCourse, modulesRelation, activitiesRelation);
		coursePersistence.update(newCourse);
		
		long moduleClassNameId = PortalUtil.getClassNameId(Module.class);
		long activityClassNameId = PortalUtil.getClassNameId(LearningActivity.class);
		List<Module> newModules = moduleLocalService.getModules(newCourse.getGroupCreatedId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		
		List<Prerequisite> prerequisites = null;
		List<LearningActivity> activities = null;
		
		LearningActivityType learningActivityType = null;
		
		for(Module module: newModules) {
			prerequisites = PrerequisiteRelationLocalServiceUtil.getPrerequisites(moduleClassNameId, module.getModuleId());
			for(Prerequisite prerequisite: prerequisites) {
				prerequisite.updatePrerequisiteCopied(moduleClassNameId, modulesRelation);
				prerequisite.updatePrerequisiteCopied(activityClassNameId, activitiesRelation);
			}
			activities = learningActivityLocalService.getLearningActivities(module.getModuleId());
			for(LearningActivity activity: activities) {
				prerequisites = PrerequisiteRelationLocalServiceUtil.getPrerequisites(activityClassNameId, activity.getActId());
				for(Prerequisite prerequisite: prerequisites) {
					prerequisite.updatePrerequisiteCopied(moduleClassNameId, modulesRelation);
					prerequisite.updatePrerequisiteCopied(activityClassNameId, activitiesRelation);
				}
				//Ahora el extraData
				learningActivityType = activity.getLearningActivityTypeFactory().getLearningActivityType(activity);
				log.debug("extraContent antes: " + activity.getExtraContent());
				learningActivityType.updateActivityCopied(activitiesRelation);
				learningActivityLocalService.updateLearningActivity(activity);
				log.debug("extraContent antes: " + activity.getExtraContent());
			}
		}
		

		//Ahora copiamos el foro y los documentos si corresponde
		if(copyForum) {
			List<MBCategory> categories = mbCategoryLocalService.getCategories(oldCourse.getGroupCreatedId(), MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			
			if(categories != null && categories.size() > 0) {
				serviceContext.setScopeGroupId(newCourse.getGroupCreatedId());
				for(MBCategory category: categories) {
					copyCategory(userId, category, MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID, oldCourse.getGroupCreatedId(), newCourse.getGroupCreatedId(), serviceContext);
				}
			}
		}
		
		if(copyDocuments) {
			com.liferay.portal.kernel.repository.Repository oldRepository = repositoryProvider.getRepository(oldCourse.getGroupCreatedId());
			copyFolder(userId, null, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, oldCourse.getGroupCreatedId(), newCourse.getGroupCreatedId(), oldRepository, serviceContext);
		}
		
		return newCourse;
		
	}
	
	public long executeCopyCourse(long courseId, long parentCourseId, Map<Locale, String> titleMap, Map<Locale, String> friendlyURLMap, long layoutSetPrototypeId, 
			Date registrationStartDate, Date registrationEndDate, Date executionStartDate, Date executionEndDate,
			boolean copyForum, boolean copyDocuments, ServiceContext serviceContext) throws PortalException {
		
		long backgroundTaskId = 0;
		
		Map<String, Serializable> taskContextMap = new HashMap<>();
		
		String title = LocalizationUtil.updateLocalization(titleMap, "", "Title", LocaleUtil.toLanguageId(LocaleUtil.getSiteDefault()));
		String friendlyURL = null;
		if(friendlyURLMap != null) {
			friendlyURL = LocalizationUtil.updateLocalization(friendlyURLMap, "", "friendlyURL", LocaleUtil.toLanguageId(LocaleUtil.getSiteDefault()));
		}
		
		taskContextMap.put("userId", serviceContext.getUserId());
		taskContextMap.put("serviceContext", serviceContext);
		taskContextMap.put("courseId", courseId);
		taskContextMap.put("parentCourseId", parentCourseId);
		taskContextMap.put("title", title);
		if(Validator.isNotNull(friendlyURL)) {
			taskContextMap.put("friendlyURL", friendlyURL);
		}
		taskContextMap.put("layoutSetPrototypeId", layoutSetPrototypeId);
		taskContextMap.put("registrationStartDate", registrationStartDate);
		taskContextMap.put("registrationEndDate", registrationEndDate);
		taskContextMap.put("executionStartDate", executionStartDate);
		taskContextMap.put("executionEndDate", executionEndDate);
		taskContextMap.put("copyForum", copyForum);
		taskContextMap.put("copyDocuments", copyDocuments);
		
		BackgroundTask backgroundTask = BackgroundTaskManagerUtil.addBackgroundTask(serviceContext.getUserId(), serviceContext.getScopeGroupId(),
					LMSPortletKeys.COURSE, CopyCourseBackgroundTaskExecutor.class.getName(),
					taskContextMap, serviceContext);
		backgroundTaskId = backgroundTask.getBackgroundTaskId();
		
		return backgroundTaskId;
	}
	
	//Copiamos las imagenes de la descripción y de los mensajes
	protected void copyCourseImages(Course oldCourse, Course newCourse) throws Exception {
		newCourse.setDescription(dlReferencesCopyContentProcessor.replaceExportDLReferences(newCourse.getDescription(), oldCourse.getGroupCreatedId(), 
				newCourse.getGroupCreatedId(), newCourse.getUserId()));
		newCourse.setWelcomeMsg(dlReferencesCopyContentProcessor.replaceExportDLReferences(newCourse.getWelcomeMsg(), oldCourse.getGroupCreatedId(), 
				newCourse.getGroupCreatedId(), newCourse.getUserId()));
		newCourse.setGoodbyeMsg(dlReferencesCopyContentProcessor.replaceExportDLReferences(newCourse.getGoodbyeMsg(), oldCourse.getGroupCreatedId(), 
				newCourse.getGroupCreatedId(), newCourse.getUserId()));
		newCourse.setDeniedInscriptionMsg(dlReferencesCopyContentProcessor.replaceExportDLReferences(newCourse.getDeniedInscriptionMsg(), 
				oldCourse.getGroupCreatedId(), newCourse.getGroupCreatedId(), newCourse.getUserId()));
	}
	
	protected void copyFolder(long userId, DLFolder oldFolder, long parentFolderId, long oldGroupId, long newGroupId, com.liferay.portal.kernel.repository.Repository oldRepository, ServiceContext serviceContext) throws PortalException {
		long newFolderId = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;
		long oldFolderId = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;
		
		if(oldFolder != null) {
			
			DLFolder newFolder = dlFolderLocalService.fetchFolder(newGroupId, parentFolderId, oldFolder.getName());
					
			if(newFolder == null) {
				newFolder = dlFolderLocalService.addFolder(userId, newGroupId, newGroupId, oldFolder.getMountPoint(), parentFolderId, oldFolder.getName(), 
						oldFolder.getDescription(), oldFolder.isHidden(), serviceContext);
				
				resourceCopy.copyModelResourcePermissions(newFolder.getCompanyId(), DLFolder.class.getName(), oldFolder.getFolderId(), newFolder.getFolderId());
			
			}
			newFolderId = newFolder.getFolderId();
			oldFolderId = oldFolder.getFolderId();
		}
		
		//Ahora copiamos los ficheros
		List<DLFileEntry> oldFileEntries = dlFileEntryLocalService.getFileEntries(oldGroupId, oldFolderId);
		AssetEntry oldAssetEntry = null;
		FileEntry newFileEntry = null;
		long[] assetLinkIds = null;
		AssetEntry newAssetEntry = null;
		for(DLFileEntry oldFileEntry: oldFileEntries) {
			
			oldAssetEntry = assetEntryLocalService.getEntry(DLFileEntry.class.getName(), oldFileEntry.getFileEntryId());
			
			serviceContext.setAssetCategoryIds(oldAssetEntry.getCategoryIds());
			serviceContext.setAssetEntryVisible(oldAssetEntry.isVisible());
			serviceContext.setAssetPriority(oldAssetEntry.getPriority());
			serviceContext.setAssetTagNames(oldAssetEntry.getTagNames());
			
			try {
			
				newFileEntry = dlAppLocalService.addFileEntry(userId, newGroupId, newFolderId, oldFileEntry.getFileName(), oldFileEntry.getMimeType(), oldFileEntry.getTitle(), 
						oldFileEntry.getDescription(), "", oldFileEntry.getContentStream(), oldFileEntry.getSize(), serviceContext);
				
				resourceCopy.copyModelResourcePermissions(oldAssetEntry.getCompanyId(), DLFileEntry.class.getName(), oldFileEntry.getFileEntryId(), newFileEntry.getFileEntryId());
				
				assetLinkIds = assetLinkLocalService.getLinks(oldAssetEntry.getEntryId(), AssetLinkConstants.TYPE_RELATED).stream().mapToLong(assetLink -> assetLink.getLinkId()).toArray();
				
				newAssetEntry = assetEntryLocalService.getEntry(DLFileEntry.class.getName(), newFileEntry.getFileEntryId());
				
				//Actualizamos los contenidos relacionados
				assetLinkLocalService.updateLinks(userId, newAssetEntry.getEntryId(), assetLinkIds,AssetLinkConstants.TYPE_RELATED);
			} catch (DuplicateFileEntryException e) {
				
			}
		}
		
		//Ahora copiamos las carpetas
		List<Folder> oldSubfolders = oldRepository.getFolders(oldFolderId, false, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
		DLFolder oldSubDLFolder = null;
		for(Folder oldSubfolder: oldSubfolders) {
			oldSubDLFolder = dlFolderLocalService.getDLFolder(oldSubfolder.getFolderId());
			copyFolder(userId, oldSubDLFolder, newFolderId, oldGroupId, newGroupId, oldRepository, serviceContext);
		}
	}
	
	protected void copyCategory(long userId, MBCategory oldCategory, long parentCategoryId, long oldGroupId, long newGroupId, ServiceContext serviceContext) throws Exception {
		//Copiamos la categoría
		MBMailingList mailingList = mbMailingListLocalService.getCategoryMailingList(oldGroupId, oldCategory.getCategoryId());
		
		String description = oldCategory.getDescription();
		description = dlReferencesCopyContentProcessor.replaceExportDLReferences(description, oldGroupId, newGroupId, userId);
		
		MBCategory newCategory = mbCategoryLocalService.addCategory(userId, parentCategoryId, oldCategory.getName(), description, 
				oldCategory.getDisplayStyle(), mailingList.getEmailAddress(), mailingList.getInProtocol(), mailingList.getInServerName(), 
				mailingList.getInServerPort(), mailingList.getInUseSSL(), mailingList.getInUserName(), mailingList.getInPassword(), 
				mailingList.getInReadInterval(), mailingList.getOutEmailAddress(), mailingList.getOutCustom(), mailingList.getOutServerName(), 
				mailingList.getOutServerPort(), mailingList.getOutUseSSL(), mailingList.getOutUserName(), mailingList.getOutPassword(), 
				mailingList.getAllowAnonymous(), mailingList.isActive(), serviceContext);
		
		resourceCopy.copyModelResourcePermissions(oldCategory.getCompanyId(), MBCategory.class.getName(), oldCategory.getCategoryId(), newCategory.getCategoryId());
		
		List<MBCategory> subcategories = mbCategoryLocalService.getCategories(oldGroupId, oldCategory.getCategoryId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		for(MBCategory oldSubcategory: subcategories) {
			copyCategory(userId, oldSubcategory, newCategory.getCategoryId(), oldGroupId, newGroupId, serviceContext);
		}
	}
	
	public long exportCourseContent(
			long courseId, long sourcePlid, String fileName, Map<String, String[]> parameterMap, ServiceContext serviceContext) throws PortalException {
		
		Course course = coursePersistence.findByPrimaryKey(courseId);
		
		User user = userLocalService.getUser(
				GetterUtil.getLong(PrincipalThreadLocal.getName()));
		
		Map<String, Serializable> exportLayoutSettingsMap =
			ExportImportConfigurationSettingsMapFactoryUtil.
				buildExportPortletSettingsMap(user, sourcePlid, course.getGroupCreatedId(), 
						LMSPortletKeys.MODULES_ADMIN, parameterMap, fileName);

		
		ExportImportConfiguration exportImportConfiguration =
			exportImportConfigurationLocalService.
				addDraftExportImportConfiguration(
					user.getUserId(),
					ExportImportConfigurationConstants.TYPE_EXPORT_PORTLET,
					exportLayoutSettingsMap);

		return exportImportLocalService.exportPortletInfoAsFileInBackground(user.getUserId(), 
			exportImportConfiguration);
	}
	
	@Override
	public Folder addAttachmentsFolder(long userId, long groupId) throws PortalException {

		return doAddFolder(userId, groupId, LMSConstants.SERVICE_NAME);
	}
	
	@ServiceReference(type = FriendlyURLEntryLocalService.class)
	protected FriendlyURLEntryLocalService friendlyURLEntryLocalService;
	
	@ServiceReference(type = ConfigurationProvider.class)
	protected ConfigurationProvider configurationProvider;
	
	@ServiceReference(type = BackgroundTaskManager.class)
	protected BackgroundTaskManager backgroundTaskmanager;
	
	@ServiceReference(type = AttachmentContentUpdater.class)
	private AttachmentContentUpdater attachmentContentUpdater;
	
	private static final String SMALL_IMAGE_FOLDER_NAME = "Small Image";
	private static final int UNIQUE_FILE_NAME_TRIES = 50;
	
	@ServiceReference(type = ResourceCopy.class)
	protected ResourceCopy resourceCopy;
	
	@ServiceReference(type = DLReferencesCopyContentProcessor.class)
	protected DLReferencesCopyContentProcessor dlReferencesCopyContentProcessor;
	
	@ServiceReference(type = MBCategoryLocalService.class)
	protected MBCategoryLocalService mbCategoryLocalService;
	
	@ServiceReference(type = MBMailingListLocalService.class)
	protected MBMailingListLocalService mbMailingListLocalService;
	
	@ServiceReference(type = ExportImportConfigurationLocalService.class)
	protected ExportImportConfigurationLocalService exportImportConfigurationLocalService;
	
	@ServiceReference(type = ExportImportLocalService.class)
	protected ExportImportLocalService exportImportLocalService;
	
	@BeanReference(type = RepositoryProvider.class)
	protected RepositoryProvider repositoryProvider;
	
	@ServiceReference(type = TrashEntryLocalService.class)
	protected TrashEntryLocalService trashEntryLocalService;
	

}