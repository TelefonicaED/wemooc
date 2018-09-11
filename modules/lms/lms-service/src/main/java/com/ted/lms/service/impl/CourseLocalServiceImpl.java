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
import com.liferay.petra.string.StringPool;
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
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
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
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.liveusers.LiveUsers;
import com.liferay.sites.kernel.util.SitesUtil;
import com.liferay.social.kernel.model.SocialActivityConstants;
import com.liferay.social.kernel.service.SocialActivityLocalServiceUtil;
import com.ted.lms.constants.CourseConstants;
import com.ted.lms.constants.LMSConstants;
import com.ted.lms.constants.LMSPropsKeys;
import com.ted.lms.exception.InscriptionException;
import com.ted.lms.model.Course;
import com.ted.lms.model.CourseResult;
import com.ted.lms.service.base.CourseLocalServiceBaseImpl;
import com.ted.lms.settings.CoursesGroupServiceSettings;

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
	public Course addCourse(Map<Locale,String> titleMap, Map<Locale,String> descriptionMap, String summary, String friendlyURL, long parentCourseId, 
			ImageSelector smallImageSelector, Date registrationStartDate, Date registrationEndDate, Date executionStartDate, Date executionEndDate, 
			long layoutSetPrototypeId,int typeSite, long inscriptionType, long courseEvalId, long calificationType, int maxUsers, boolean welcome, 
			String welcomeSubject, String welcomeMsg, boolean goodbye, String goodbyeSubject, String goodbyeMsg, int status, 
			ServiceContext serviceContext) {
		
		Course course = null;
		try {
			User user = userLocalService.getUser(serviceContext.getUserId());
			Date now = new Date();
			
			long parentGroupId = GroupConstants.DEFAULT_PARENT_GROUP_ID;
			if(parentCourseId != CourseConstants.DEFAULT_PARENT_COURSE_ID) {
				Course courseParent = coursePersistence.fetchByPrimaryKey(parentCourseId);
				if(courseParent != null) {
					parentGroupId = courseParent.getGroupCreatedId();
				}
			}
			
			int membershipRestriction = GroupConstants.DEFAULT_MEMBERSHIP_RESTRICTION;

			if (parentGroupId != GroupConstants.DEFAULT_PARENT_GROUP_ID) {

				membershipRestriction =
					GroupConstants.MEMBERSHIP_RESTRICTION_TO_PARENT_SITE_MEMBERS;
			}
			
				
			course = coursePersistence.create(counterLocalService.increment(Course.class.getName()));
			
			course.setGroupId(serviceContext.getScopeGroupId());
			course.setCompanyId(serviceContext.getCompanyId());
			course.setUserId(serviceContext.getUserId());
			course.setUserName(user.getFullName());
			course.setCreateDate(now);
			course.setModifiedDate(now);
			
			course.setParentCourseId(parentCourseId);
			course.setTitleMap(titleMap);
			course.setDescriptionMap(descriptionMap);
			course.setRegistrationStartDate(registrationStartDate);
			course.setRegistrationEndDate(registrationEndDate);
			course.setExecutionStartDate(executionStartDate);
			course.setExecutionEndDate(executionEndDate);
			course.setMaxUsers(maxUsers);
			course.setInscriptionType(inscriptionType);
			course.setCourseEvalId(courseEvalId);
			course.setCalificationType(calificationType);
			course.setWelcome(welcome);
			course.setWelcomeSubject(welcomeSubject);
			course.setWelcomeMsg(welcomeMsg);
			course.setGoodbye(goodbye);
			course.setGoodbyeSubject(goodbyeSubject);
			course.setGoodbyeMsg(goodbyeMsg);
			course.setStatus(status);
			course.setStatusByUserId(serviceContext.getUserId());
			course.setStatusByUserName(user.getFullName());
			course.setStatusDate(now);
			if(smallImageSelector != null) {
				course.setSmallImageId(addSmallImageCourse(serviceContext.getUserId(), serviceContext.getScopeGroupId(), course.getCourseId(), smallImageSelector));
			}
			
			coursePersistence.update(course);
			
			//Creamos el group asociado, le cambiamos al nombre al grupo porque no deja crear dos con el mismo nombre por el groupKey
			Map<Locale,String> titleMapGroup = new HashMap<Locale,String>();
			final long courseId = course.getCourseId();
			
			titleMap.forEach((k,v)->titleMapGroup.put(k, v + " (" + courseId + ")"));
			
			Group group = GroupServiceUtil.addGroup(
					GroupConstants.DEFAULT_PARENT_GROUP_ID, 0, titleMapGroup,
					descriptionMap, typeSite, true, membershipRestriction,
					getFriendlyURL(serviceContext.getCompanyId(), friendlyURL, titleMap), true, false, true, serviceContext);
			
			course.setGroupCreatedId(group.getGroupId());
			coursePersistence.update(course);

			LiveUsers.joinGroup(serviceContext.getCompanyId(), group.getGroupId(), serviceContext.getUserId());
			
			//Añadimos la imagen del curso
			
			resourceLocalService.addResources(course.getCompanyId(), course.getGroupId(), course.getUserId(),  Course.class.getName(), course.getCourseId(), false, true, false);
			
			updateAsset(serviceContext.getUserId(), course, serviceContext.getAssetCategoryIds(), serviceContext.getAssetTagNames(), serviceContext.getAssetLinkEntryIds(), serviceContext.getAssetPriority(), summary);
			
			//Añadimos rol de editor o tutor al creador
			boolean teacherRoleToCreator = GetterUtil.getBoolean(PropsUtil.get(LMSPropsKeys.COURSE_ADD_TEACHER_ROLE_TO_CREATOR));
			boolean editorRoleToCreator = GetterUtil.getBoolean(PropsUtil.get(LMSPropsKeys.COURSE_ADD_TEACHER_ROLE_TO_CREATOR));
			
			if(teacherRoleToCreator){
				long teacherRole = PrefsPropsUtil.getLong(serviceContext.getCompanyId(), LMSPropsKeys.LMS_PREFS_TEACHER_ROLE,0);
				if(teacherRole > 0) {
					long[] teacherRoleId = {PrefsPropsUtil.getLong(serviceContext.getCompanyId(), LMSPropsKeys.LMS_PREFS_TEACHER_ROLE)};
					UserGroupRoleLocalServiceUtil.addUserGroupRoles(serviceContext.getUserId(), course.getGroupCreatedId(), teacherRoleId);
				}
			}

			if(editorRoleToCreator){
				long editorRole = PrefsPropsUtil.getLong(serviceContext.getCompanyId(), LMSPropsKeys.LMS_PREFS_EDITOR_ROLE);
				if(editorRole > 0) {
					long[] editorRoleId = {PrefsPropsUtil.getLong(serviceContext.getCompanyId(), LMSPropsKeys.LMS_PREFS_EDITOR_ROLE)};
					UserGroupRoleLocalServiceUtil.addUserGroupRoles(serviceContext.getUserId(),	course.getGroupCreatedId(), editorRoleId);
				}
			}	
			
			//Cargamos la plantilla de curso en el grupo creado
			SitesUtil.updateLayoutSetPrototypesLinks(group, layoutSetPrototypeId, 0, true,false);
			
			Indexer<Course> indexer = IndexerRegistryUtil.nullSafeGetIndexer(Course.class);
			indexer.reindex(course);
			
		} catch (PortalException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
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
	public void updateAsset(
			long userId, Course course, long[] assetCategoryIds,
			String[] assetTagNames, long[] assetLinkEntryIds, Double priority,
			String summary)
		throws PortalException {

		Date publishDate = null;
		if (course.isApproved()) {
			publishDate = course.getCreateDate();
		}

		AssetEntry assetEntry = assetEntryLocalService.updateEntry(
			userId, course.getGroupId(), course.getCreateDate(),
			course.getModifiedDate(), Course.class.getName(),
			course.getCourseId(), course.getUuid(), 0, assetCategoryIds,
			assetTagNames, true, course.isApproved(), null, null, publishDate,
			null, ContentTypes.TEXT_HTML, course.getTitle(), course.getDescription(), summary, null,
			null, 0, 0, priority);

		assetLinkLocalService.updateLinks(
			userId, assetEntry.getEntryId(), assetLinkEntryIds,
			AssetLinkConstants.TYPE_RELATED);
	}
	
	@Override
	@Indexable(type = IndexableType.REINDEX)
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
	
	private static final String SMALL_IMAGE_FOLDER_NAME = "Small Image";

}