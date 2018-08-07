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
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.service.GroupService;
import com.liferay.portal.kernel.service.GroupServiceUtil;
import com.liferay.portal.kernel.service.LayoutSetPrototypeLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserGroupRoleLocalService;
import com.liferay.portal.kernel.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.kernel.service.permission.ModelPermissions;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.liveusers.LiveUsers;
import com.liferay.sites.kernel.util.SitesUtil;
import com.liferay.social.kernel.model.SocialActivityConstants;
import com.liferay.social.kernel.service.SocialActivitySettingLocalServiceUtil;
import com.ted.lms.constants.CourseConstants;
import com.ted.lms.constants.LMSPropsKeys;
import com.ted.lms.model.Course;
import com.ted.lms.service.base.CourseLocalServiceBaseImpl;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Reference;

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
	
	//Debemos rellenar con lo que sea
	@Indexable(type = IndexableType.REINDEX)
	public Course addCourse(Map<Locale,String> titleMap, Map<Locale,String> descriptionMap, String summary, String friendlyURL, Locale locale, long parentCourseId, 
			long smallImageId, Date registrationStartDate, Date registrationEndDate, Date executionStartDate, Date executionEndDate, long layoutSetPrototypeId,int typeSite, 
			long inscriptionType, long courseEvalId, long calificationType, int maxUsers, boolean welcome, String welcomeSubject, String welcomeMsg, 
			boolean goodbye, String goodbyeSubject, String goodbyeMsg, int status, ServiceContext serviceContext) {
		
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
			
			//Creamos el group asociado
			Group group = GroupServiceUtil.addGroup(
					GroupConstants.DEFAULT_PARENT_GROUP_ID, 0, titleMap,
					descriptionMap, typeSite, true, membershipRestriction,
					friendlyURL, true, false, true, serviceContext);

			LiveUsers.joinGroup(serviceContext.getCompanyId(), group.getGroupId(), serviceContext.getUserId());
				
			course = coursePersistence.create(counterLocalService.increment(Course.class.getName()));
			
			course.setGroupId(serviceContext.getScopeGroupId());
			course.setCompanyId(serviceContext.getCompanyId());
			course.setUserId(serviceContext.getUserId());
			course.setUserName(user.getFullName());
			course.setCreateDate(now);
			course.setModifiedDate(now);
			
			course.setParentCourseId(parentCourseId);
			course.setGroupCreatedId(group.getGroupId());
			course.setTitleMap(titleMap);
			course.setDescriptionMap(descriptionMap);
			course.setSmallImageId(smallImageId);
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
			
			
			coursePersistence.update(course);
			
			resourceLocalService.addResources(course.getCompanyId(), course.getGroupId(), course.getUserId(),  Course.class.getName(), course.getCourseId(), false, true, false);
			
			updateAsset(serviceContext.getUserId(), course, serviceContext.getAssetCategoryIds(), serviceContext.getAssetTagNames(), serviceContext.getAssetLinkEntryIds(), serviceContext.getAssetPriority(), summary);
			
			//Añadimos rol de editor o tutor al creador
			boolean teacherRoleToCreator = GetterUtil.getBoolean(PropsUtil.get(LMSPropsKeys.COURSE_ADD_TEACHER_ROLE_TO_CREATOR));
			boolean editorRoleToCreator = GetterUtil.getBoolean(PropsUtil.get(LMSPropsKeys.COURSE_ADD_TEACHER_ROLE_TO_CREATOR));
			
			if(teacherRoleToCreator){
				long[] teacherRoleId = {PrefsPropsUtil.getLong(serviceContext.getCompanyId(), LMSPropsKeys.LMS_PREFS_TEACHER_ROLE)};
				UserGroupRoleLocalServiceUtil.addUserGroupRoles(serviceContext.getUserId(), course.getGroupCreatedId(), teacherRoleId);
			}

			if(editorRoleToCreator){
				long[] editorRoleId = {PrefsPropsUtil.getLong(serviceContext.getCompanyId(), LMSPropsKeys.LMS_PREFS_EDITOR_ROLE)};
				UserGroupRoleLocalServiceUtil.addUserGroupRoles(serviceContext.getUserId(),	course.getGroupCreatedId(), editorRoleId);
			}	
			
			//Cargamos la plantilla de curso en el grupo creado
			SitesUtil.updateLayoutSetPrototypesLinks(group, layoutSetPrototypeId, 0, true,false);
			
			Indexer<Course> indexer = IndexerRegistryUtil.nullSafeGetIndexer(Course.class);
			indexer.reindex(course);
			
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return course;
	}
	
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
}