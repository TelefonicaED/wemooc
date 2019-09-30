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

import com.liferay.portal.aop.AopService;
import com.ted.lms.service.LearningActivityResultLocalService;
import com.ted.lms.service.base.LearningActivityLocalServiceBaseImpl;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetLinkConstants;
import com.liferay.expando.kernel.util.ExpandoBridgeUtil;
import com.liferay.portal.kernel.backgroundtask.BackgroundTask;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskConstants;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskManagerUtil;
import com.liferay.portal.kernel.comment.CommentManager;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProviderUtil;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepository;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.social.SocialActivityManagerUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.TempFileEntryUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.social.kernel.model.SocialActivityConstants;
import com.liferay.trash.exception.TrashEntryException;
import com.liferay.trash.service.TrashEntryLocalService;
import com.ted.audit.api.AuditFactory;
import com.ted.lms.configuration.CourseServiceConfiguration;
import com.ted.lms.constants.CourseConstants;
import com.ted.lms.constants.LMSActivityKeys;
import com.ted.lms.constants.LMSAuditConstants;
import com.ted.lms.constants.LMSConstants;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.constants.LMSRoleConstants;
import com.ted.lms.constants.LearningActivityConstants;
import com.ted.lms.copy.content.processor.DLReferencesCopyContentProcessor;
import com.ted.lms.copy.permission.ResourceCopy;
import com.ted.lms.exception.LearningActivityEndDateException;
import com.ted.lms.exception.LearningActivityStartDateException;
import com.ted.lms.exception.NoSuchNextLearningActivityException;
import com.ted.lms.exception.NoSuchPreviousLearningActivityException;
import com.ted.lms.internal.background.task.DeleteTriesBackgroundTaskExecutor;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.LearningActivityResult;
import com.ted.lms.model.LearningActivityType;
import com.ted.lms.model.LearningActivityTypeFactory;
import com.ted.lms.model.Module;
import com.ted.lms.registry.LearningActivityTypeFactoryRegistryUtil;
import com.ted.lms.util.LMSPrefsPropsValues;
import com.ted.prerequisite.model.PrerequisiteFactory;
import com.ted.prerequisite.registry.PrerequisiteFactoryRegistryUtil;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * The implementation of the learning activity local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.ted.lms.service.LearningActivityLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LearningActivityLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=com.ted.lms.model.LearningActivity",
	service = AopService.class
)
public class LearningActivityLocalServiceImpl
	extends LearningActivityLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>com.ted.lms.service.LearningActivityLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.ted.lms.service.LearningActivityLocalServiceUtil</code>.
	 */
	
	private static final Log log = LogFactoryUtil.getLog(LearningActivityLocalServiceImpl.class);
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.ted.lms.service.LearningActivityLocalServiceUtil} to access the learning activity local service.
	 */
	
	public List<LearningActivity> getLearningActivitiesWithoutModule(long groupId){
		return learningActivityPersistence.findByGroupIdModuleId(groupId, CourseConstants.DEFAULT_MODULE_EMPTY);
	}
	
	public List<LearningActivity> getLearningActivities(long moduleId){
		return learningActivityPersistence.findByModuleId(moduleId);
	}
	
	public List<LearningActivity> getRequiredLearningActivitiesOfGroup(long groupId){
		return learningActivityPersistence.findByGroupIdRequired(groupId, true);
	}
	
	public List<LearningActivity> getRequiredLearningActivitiesOfModule(long moduleId){
		return learningActivityPersistence.findByModuleIdRequired(moduleId, true);
	}
	
	public int getLearningActivitiesOfModuleCount(long moduleId) {
		return learningActivityPersistence.countByModuleId(moduleId);
	}
	
	public List<LearningActivity> getLearningActivitiesNotTypeId(long moduleId, long typeId){
		return learningActivityPersistence.findByModuleIdNotTypeId(moduleId, typeId);
	}
	
	public void deleteLearningActivities(long moduleId) {
		List<LearningActivity> listLearningActivities = learningActivityPersistence.findByModuleId(moduleId);
		for(LearningActivity learningActivity: listLearningActivities) {
			learningActivityPersistence.remove(learningActivity);
		}
	}
	
	@Override
	public LearningActivity addLearningActivity(long userId, long groupId, long moduleId, long type, Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
			boolean useStartExecutionDateCourse, int startDateMonth, int startDateDay, int startDateYear,
			int startDateHour, int startDateMinute, boolean useEndExecutionDateCourse, int endDateMonth, int endDateDay,
			int endDateYear, int endDateHour, int endDateMinute, boolean required, int tries, double passPuntuation,
			Map<Locale, String> feedbackCorrectMap, Map<Locale, String> feedbackNoCorrectMap, boolean commentsActivated, 
			String[] selectedFileNames, ServiceContext serviceContext) throws PortalException {
		
		User user = userLocalService.getUser(userId);
		
		Date startDate = null;
		
		if(!useStartExecutionDateCourse) {	
			startDate = PortalUtil.getDate(
					startDateMonth, startDateDay, startDateYear, startDateHour,
					startDateMinute, user.getTimeZone(),
					LearningActivityStartDateException.class);
		}
		
		Date endDate = null;
		
		if(!useEndExecutionDateCourse) {
			endDate = PortalUtil.getDate(
					endDateMonth, endDateDay, endDateYear, endDateHour,
					endDateMinute, user.getTimeZone(),
					LearningActivityEndDateException.class);
		}
		
		return addLearningActivity(userId, groupId, moduleId, type, titleMap, descriptionMap, startDate, endDate, required, tries, 
				passPuntuation, feedbackCorrectMap, feedbackNoCorrectMap, commentsActivated, selectedFileNames, serviceContext);
		
	}
	
	@Override
	public LearningActivity addLearningActivity(long userId, long groupId, long moduleId, long type, Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
			Date startDate, Date endDate, boolean required, int tries, double passPuntuation, Map<Locale, String> feedbackCorrectMap, 
			Map<Locale, String> feedbackNoCorrectMap, boolean commentsActivated, String[] selectedFileNames, ServiceContext serviceContext) throws PortalException {
		
		return addLearningActivity(userId, groupId, moduleId, titleMap, descriptionMap, type, startDate, endDate, 
				tries, passPuntuation, 0, null, feedbackCorrectMap, feedbackNoCorrectMap, required, commentsActivated, selectedFileNames, serviceContext);
		
	}
	
	@Override
	@Indexable(type = IndexableType.REINDEX)
	public LearningActivity addLearningActivity(long userId, long groupId, long moduleId, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap, long typeId, Date startDate, Date endDate, int tries, double passPuntuation, long priority,
			String extraContent, Map<Locale, String> feedbackCorrectMap, Map<Locale, String> feedbackNoCorrectMap, boolean required, boolean commentsActivated,
			String[] selectedFileNames, ServiceContext serviceContext) throws PortalException {
		
		User user = userLocalService.getUser(userId);
		
		long actId = counterLocalService.increment(LearningActivity.class.getName());
		
		LearningActivity activity = learningActivityPersistence.create(actId);
		activity.setUuid(serviceContext.getUuid());
		activity.setGroupId(groupId);
		activity.setCompanyId(user.getCompanyId());
		activity.setUserId(userId);
		activity.setUserName(user.getFullName());
		activity.setStatusByUserName(user.getFullName());
		
		activity.setModuleId(moduleId);
		//TODO Mirar si sanitizamos
		activity.setTitleMap(titleMap);
		activity.setDescriptionMap(descriptionMap);
		
		activity.setTypeId(typeId);
		activity.setStartDate(startDate);
		activity.setEndDate(endDate);
		activity.setTries(tries);
		activity.setPassPuntuation(passPuntuation);
		if(priority == 0) priority = actId;
		activity.setPriority(priority);
		activity.setExtraContent(extraContent);
		activity.setFeedbackCorrectMap(feedbackCorrectMap);
		activity.setFeedbackNoCorrectMap(feedbackNoCorrectMap);
		activity.setRequired(required);
		activity.setCommentsActivated(commentsActivated);
		activity.setStatus(WorkflowConstants.STATUS_APPROVED);
		activity.setStatusByUserId(userId);
		activity.setStatusDate(activity.getCreateDate());
		
		activity = learningActivityPersistence.update(activity);
	
		resourceLocalService.addResources(activity.getCompanyId(), groupId, userId,  LearningActivity.class.getName(), 
				activity.getActId(), false, true, false);
		
		Role studentRole = roleLocalService.fetchRole(user.getCompanyId(), LMSRoleConstants.STUDENT);
		
		String[] actIds = {ActionKeys.VIEW};
		resourcePermissionLocalService.setResourcePermissions(studentRole.getCompanyId(), LearningActivity.class.getName(), 
				ResourceConstants.SCOPE_INDIVIDUAL,	Long.toString(actId),studentRole.getRoleId(), actIds);
		
		updateAsset(userId, activity, serviceContext.getAssetCategoryIds(), serviceContext.getAssetTagNames(), 
				serviceContext.getAssetLinkEntryIds(), serviceContext.getAssetPriority());
		
		addLearningActivityAttachments(userId, activity, selectedFileNames);
		
		AuditFactory.audit(activity.getCompanyId(), activity.getGroupId(), LMSAuditConstants.LEARNING_ACTIVITY_ADD, LearningActivity.class.getName(), activity.getActId(), 
				userId, user.getFullName(), null);
		
		return activity;
		
	}
	
	@Override
	public LearningActivity updateLearningActivity(long userId, long actId, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap, boolean useStartExecutionDateCourse, int startDateMonth,
			int startDateDay, int startDateYear, int startDateHour, int startDateMinute,
			boolean useEndExecutionDateCourse, int endDateMonth, int endDateDay, int endDateYear, int endDateHour,
			int endDateMinute, boolean required, int tries, double passPuntuation, Map<Locale, String> feedbackCorrectMap,
			Map<Locale, String> feedbackNoCorrectMap, boolean commentsActivated, String[] selectedFileNames, long[] removeFileEntryIds, 
			ServiceContext serviceContext) throws PortalException {
		
		User user = userLocalService.getUser(userId);
		
		Date startDate = null;
		
		if(!useStartExecutionDateCourse) {	
			startDate = PortalUtil.getDate(
					startDateMonth, startDateDay, startDateYear, startDateHour,
					startDateMinute, user.getTimeZone(),
					LearningActivityStartDateException.class);
		}
		
		Date endDate = null;
		
		if(!useEndExecutionDateCourse) {
			endDate = PortalUtil.getDate(
					endDateMonth, endDateDay, endDateYear, endDateHour,
					endDateMinute, user.getTimeZone(),
					LearningActivityEndDateException.class);
		}
		
		return updateLearningActivity(userId, actId, titleMap, descriptionMap, startDate, endDate, tries, passPuntuation, 
				feedbackCorrectMap, feedbackNoCorrectMap, required, commentsActivated, selectedFileNames, removeFileEntryIds, 
				serviceContext);
	}
	
	@Override
	public LearningActivity updateLearningActivity(long userId, long actId, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap, Date startDate, Date endDate, int tries, double passPuntuation,
			Map<Locale, String> feedbackCorrectMap, Map<Locale, String> feedbackNoCorrectMap, boolean required, boolean commentsActivated,
			String[] selectedFileNames, long[] removeFileEntryIds, ServiceContext serviceContext) throws PortalException {
		
		LearningActivity activity = learningActivityPersistence.findByPrimaryKey(actId);
		
		return updateLearningActivity(userId, actId, activity.getModuleId(), titleMap, descriptionMap, activity.getTypeId(), 
				startDate, endDate, tries, passPuntuation, activity.getPriority(), activity.getExtraContent(), feedbackCorrectMap, feedbackNoCorrectMap, 
				required, commentsActivated, selectedFileNames, removeFileEntryIds, serviceContext);
		
	}
	
	@Override
	@Indexable(type = IndexableType.REINDEX)
	public LearningActivity updateLearningActivity(long userId, long actId, long moduleId, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap, long typeId, Date startDate, Date endDate, int tries, double passPuntuation, long priority,
			String extraContent, Map<Locale, String> feedbackCorrectMap, Map<Locale, String> feedbackNoCorrectMap, boolean required, boolean commentsActivated,
			String[] selectedFileNames, long[] removeFileEntryIds, ServiceContext serviceContext) throws PortalException {
		
		User user = userLocalService.getUser(userId);
		
		LearningActivity activity = learningActivityPersistence.fetchByPrimaryKey(actId);
		
		activity.setUserId(userId);
		activity.setUserName(user.getFullName());
		
		activity.setModuleId(moduleId);
		activity.setTitleMap(titleMap);
		activity.setDescriptionMap(descriptionMap);
		activity.setTypeId(typeId);
		activity.setStartDate(startDate);
		activity.setEndDate(endDate);
		activity.setTries(tries);
		activity.setPassPuntuation(passPuntuation);
		activity.setPriority(priority);
		activity.setExtraContent(extraContent);
		activity.setFeedbackCorrectMap(feedbackCorrectMap);
		activity.setFeedbackNoCorrectMap(feedbackNoCorrectMap);
		activity.setRequired(required);
		activity.setCommentsActivated(commentsActivated);
		
		activity = learningActivityPersistence.update(activity);
		
		updateAsset(userId, activity, serviceContext.getAssetCategoryIds(), serviceContext.getAssetTagNames(), 
				serviceContext.getAssetLinkEntryIds(), serviceContext.getAssetPriority());
		
		addLearningActivityAttachments(userId, activity, selectedFileNames);

		removeLearningActivityAttachments(removeFileEntryIds);
		
		AuditFactory.audit(activity.getCompanyId(), activity.getGroupId(), LMSAuditConstants.LEARNING_ACTIVITY_UPDATE, LearningActivity.class.getName(), activity.getActId(), 
				userId, user.getFullName(), null);
		
		return activity;
		
	}
	
	@Override
	public LearningActivity updateLearningActivity(long userId, LearningActivity activity) {

		User user = userLocalService.fetchUser(userId);
		
		activity.setUserId(userId);
		activity.setUserName(user.getFullName());
		
		AuditFactory.audit(activity.getCompanyId(), activity.getGroupId(), LMSAuditConstants.LEARNING_ACTIVITY_UPDATE, LearningActivity.class.getName(), activity.getActId(), 
				userId, user.getFullName(), null);
		
		return learningActivityPersistence.update(activity);
	}
	
	public void updateAsset(
			long userId, LearningActivity activity, long[] assetCategoryIds,
			String[] assetTagNames, long[] assetLinkEntryIds, Double priority)
		throws PortalException {

		Date publishDate = null;
		if (activity.isApproved()) {
			publishDate = activity.getCreateDate();
		}

		AssetEntry assetEntry = assetEntryLocalService.updateEntry(
			userId, activity.getGroupId(), activity.getCreateDate(),
			activity.getModifiedDate(), LearningActivity.class.getName(),
			activity.getActId(), activity.getUuid(), 0, assetCategoryIds,
			assetTagNames, true, activity.isApproved(), null, null, publishDate,
			null, ContentTypes.TEXT_HTML, activity.getTitle(), activity.getDescription(), null, null,
			null, 0, 0, priority);

		assetLinkLocalService.updateLinks(
			userId, assetEntry.getEntryId(), assetLinkEntryIds,
			AssetLinkConstants.TYPE_RELATED);
	}
	
	public List<LearningActivity> getLearningActivitiesByTypeId(long typeId){
		return learningActivityPersistence.findByTypeId(typeId);
	}
	
	@Override
	public LearningActivity moveDownLearningActivity(long userId, long actId) throws PortalException{
		
		LearningActivity activity = learningActivityPersistence.fetchByPrimaryKey(actId);
		LearningActivity nextActivity = getNextLearningActivity(activity);
		
		if(nextActivity!=null) {
			User user = userLocalService.getUser(userId);
			
			//Se actualiza el orden
			long priority = activity.getPriority();
			
			activity.setPriority(nextActivity.getPriority());
			activity.setUserId(userId);
			activity.setUserName(user.getFullName());
			
			nextActivity.setPriority(priority);
			nextActivity.setUserId(userId);
			nextActivity.setUserName(user.getFullName());
			
			learningActivityPersistence.update(activity);			
			learningActivityPersistence.update(nextActivity);
			
			AuditFactory.audit(activity.getCompanyId(), activity.getGroupId(), LMSAuditConstants.LEARNING_ACTIVITY_UPDATE, LearningActivity.class.getName(), activity.getActId(), 
					userId, user.getFullName(), null);
			AuditFactory.audit(nextActivity.getCompanyId(), nextActivity.getGroupId(), LMSAuditConstants.LEARNING_ACTIVITY_UPDATE, LearningActivity.class.getName(), nextActivity.getActId(), 
					userId, user.getFullName(), null);
		}
		
		return activity;
	}
	
	@Override
	public LearningActivity moveUpLearningActivity(long userId, long actId) throws PortalException{
		
		LearningActivity activity = learningActivityPersistence.fetchByPrimaryKey(actId);
		
		LearningActivity previousActivity = getPreviousLearningActivity(activity);
		
		if(previousActivity!=null) {
			User user = userLocalService.getUser(userId);
			
			long priority=activity.getPriority();
			
			activity.setPriority(previousActivity.getPriority());
			activity.setUserId(userId);
			activity.setUserName(user.getFullName());
			
			previousActivity.setPriority(priority);
			previousActivity.setUserId(userId);
			previousActivity.setUserName(user.getFullName());
			
			learningActivityPersistence.update(activity);			
			learningActivityPersistence.update(previousActivity);
			
			AuditFactory.audit(activity.getCompanyId(), activity.getGroupId(), LMSAuditConstants.LEARNING_ACTIVITY_UPDATE, LearningActivity.class.getName(), activity.getActId(), 
					userId, user.getFullName(), null);
			AuditFactory.audit(previousActivity.getCompanyId(), previousActivity.getGroupId(), LMSAuditConstants.LEARNING_ACTIVITY_UPDATE, LearningActivity.class.getName(), previousActivity.getActId(), 
					userId, user.getFullName(), null);
		}
		
		return activity;
	}
	
	
	@Override
	public void changeVisibility(long userId, long actId) throws PortalException{
		
		User user = userLocalService.getUser(userId);
		
		Role studentRole = roleLocalService.fetchRole(user.getCompanyId(), LMSRoleConstants.STUDENT);
		
		boolean visible = resourcePermissionLocalService.hasResourcePermission(studentRole.getCompanyId(), LearningActivity.class.getName(), 
				ResourceConstants.SCOPE_INDIVIDUAL,	Long.toString(actId),studentRole.getRoleId(), ActionKeys.VIEW);
		
		if(visible) {
			resourcePermissionLocalService.removeResourcePermission(studentRole.getCompanyId(), LearningActivity.class.getName(), 
					ResourceConstants.SCOPE_INDIVIDUAL,	Long.toString(actId),studentRole.getRoleId(), ActionKeys.VIEW);	
		}else {
			String[] actIds = {ActionKeys.VIEW};
			resourcePermissionLocalService.setResourcePermissions(studentRole.getCompanyId(), LearningActivity.class.getName(), 
					ResourceConstants.SCOPE_INDIVIDUAL,	Long.toString(actId),studentRole.getRoleId(), actIds);
		}
		
	}

	private LearningActivity getNextLearningActivity(LearningActivity activity) throws PortalException {
		List<LearningActivity> listNextLearningActivities = learningActivityPersistence.findByModuleIdNextLearningActivities(activity.getGroupId(), activity.getModuleId(), 
				activity.getPriority());
		LearningActivity nextLearningActivity = null;
		if(listNextLearningActivities != null && listNextLearningActivities.size() > 0) {
			nextLearningActivity = listNextLearningActivities.get(0);
		}else {
			throw new NoSuchNextLearningActivityException();
		}
		return nextLearningActivity;
	}
	

	private LearningActivity getPreviousLearningActivity(LearningActivity activity) throws PortalException {
		List<LearningActivity> listPreviousLearningActivities = learningActivityPersistence.findByModuleIdPreviousLearningActivities(activity.getGroupId(), activity.getModuleId(), 
				activity.getPriority());
		LearningActivity previousLearningActivity = null;
		if(listPreviousLearningActivities != null && listPreviousLearningActivities.size() > 0) {
			previousLearningActivity = listPreviousLearningActivities.get(0);
		}else {
			throw new NoSuchPreviousLearningActivityException();
		}
		return previousLearningActivity;
	}
	
	@Override
	public LearningActivity moveLearningActivityToTrash(long userId, long actId) throws PortalException {

		LearningActivity activity = learningActivityPersistence.findByPrimaryKey(actId);

		return learningActivityLocalService.moveLearningActivityToTrash(userId, activity);
	}
	
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public LearningActivity moveLearningActivityToTrash(long userId, LearningActivity activity) throws PortalException {

		// Actividad

		if (activity.isInTrash()) {
			throw new TrashEntryException();
		}

		int oldStatus = activity.getStatus();

		if (oldStatus == WorkflowConstants.STATUS_PENDING) {
			activity.setStatus(WorkflowConstants.STATUS_DRAFT);

			learningActivityPersistence.update(activity);
		}

		activity = updateStatus(userId, activity.getActId(), WorkflowConstants.STATUS_IN_TRASH, new ServiceContext(), new HashMap<>());

		// Social
		JSONObject extraDataJSONObject = JSONFactoryUtil.createJSONObject();

		extraDataJSONObject.put("title", activity.getTitleCurrentValue());

		SocialActivityManagerUtil.addActivity(userId, activity, SocialActivityConstants.TYPE_MOVE_TO_TRASH, extraDataJSONObject.toString(), 0);

		// Workflow
		if (oldStatus == WorkflowConstants.STATUS_PENDING) {
			workflowInstanceLinkLocalService.deleteWorkflowInstanceLink(activity.getCompanyId(), activity.getGroupId(), Module.class.getName(), activity.getModuleId());
		}
		
		User user = userLocalService.getUser(userId);
		
		AuditFactory.audit(activity.getCompanyId(), activity.getGroupId(), LMSAuditConstants.LEARNING_ACTIVITY_TO_TRASH, LearningActivity.class.getName(), activity.getActId(), 
				userId, user.getFullName(), null);

		return activity;
	}
	
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public LearningActivity updateStatus(long userId, long actId, int status, ServiceContext serviceContext, Map<String, Serializable> workflowContext) throws PortalException {

		User user = userLocalService.getUser(userId);
		Date now = new Date();

		LearningActivity activity = learningActivityPersistence.findByPrimaryKey(actId);

		int oldStatus = activity.getStatus();

		activity.setStatus(status);
		activity.setStatusByUserId(user.getUserId());
		activity.setStatusByUserName(user.getFullName());
		activity.setStatusDate(serviceContext.getModifiedDate(now));

		learningActivityPersistence.update(activity);

		JSONObject extraDataJSONObject = JSONFactoryUtil.createJSONObject();

		extraDataJSONObject.put("title", activity.getTitleCurrentValue());

		if (status == WorkflowConstants.STATUS_APPROVED) {
			// Social
			if (oldStatus != WorkflowConstants.STATUS_IN_TRASH) {
				if (serviceContext.isCommandUpdate()) {
					SocialActivityManagerUtil.addActivity(user.getUserId(), activity, LMSActivityKeys.UPDATE_LEARNING_ACTIVITY, 
							extraDataJSONObject.toString(), 0);
				} else {
					SocialActivityManagerUtil.addUniqueActivity(user.getUserId(), activity, LMSActivityKeys.ADD_LEARNING_ACTIVITY, 
							extraDataJSONObject.toString(), 0);
				}
			}

			// Trash
			if (oldStatus == WorkflowConstants.STATUS_IN_TRASH) {
				if (LMSPrefsPropsValues.getLearningActivityCommentsEnabled(activity.getCompanyId())) {
					commentManager.restoreDiscussionFromTrash(LearningActivity.class.getName(), actId);
				}
				trashEntryLocalService.deleteEntry(LearningActivity.class.getName(), actId);
			}
		} else {
			// Social

			if (oldStatus != WorkflowConstants.STATUS_IN_TRASH) {
				if (serviceContext.isCommandUpdate()) {
					SocialActivityManagerUtil.addActivity(user.getUserId(), activity, LMSActivityKeys.UPDATE_LEARNING_ACTIVITY, 
							extraDataJSONObject.toString(), 0);
				} else {
					SocialActivityManagerUtil.addUniqueActivity(user.getUserId(), activity, LMSActivityKeys.ADD_LEARNING_ACTIVITY, 
							extraDataJSONObject.toString(), 0);
				}
			}

			// Trash
			if (status == WorkflowConstants.STATUS_IN_TRASH) {
				if (LMSPrefsPropsValues.getLearningActivityCommentsEnabled(activity.getCompanyId())) {
					commentManager.moveDiscussionToTrash(LearningActivity.class.getName(), actId);
				}

				trashEntryLocalService.addTrashEntry( userId, activity.getGroupId(), Module.class.getName(),
						activity.getActId(), activity.getUuid(), null, oldStatus, null, null);
			} else if (oldStatus == WorkflowConstants.STATUS_IN_TRASH) {
				if (LMSPrefsPropsValues.getLearningActivityCommentsEnabled(activity.getCompanyId())) {
					commentManager.restoreDiscussionFromTrash(LearningActivity.class.getName(), actId);
				}

				trashEntryLocalService.deleteEntry(LearningActivity.class.getName(), actId);
			}
		}
		
		AuditFactory.audit(activity.getCompanyId(), activity.getGroupId(), LMSAuditConstants.LEARNING_ACTIVITY_UPDATE_STATUS, LearningActivity.class.getName(), activity.getActId(), 
				userId, user.getFullName(), null);

		return activity;
	}
	
	public LearningActivity copyActivity(long userId, LearningActivity oldActivity, Module newModule, ServiceContext serviceContext) throws Exception {
		
		AssetEntry oldAssetEntry = oldActivity.getAssetEntry();
		
		serviceContext.setAssetCategoryIds(oldAssetEntry.getCategoryIds());
		serviceContext.setAssetEntryVisible(oldAssetEntry.isVisible());
		serviceContext.setAssetPriority(oldAssetEntry.getPriority());
		serviceContext.setAssetTagNames(oldAssetEntry.getTagNames());
		
		//Primer paso, creamos el curso
		LearningActivity newActivity = addLearningActivity(userId, newModule.getGroupId(), newModule.getModuleId(), oldActivity.getTypeId(), oldActivity.getTitleMap(), 
				oldActivity.getDescriptionMap(), oldActivity.getStartDate(), oldActivity.getEndDate(), oldActivity.isRequired(), oldActivity.getTries(), 
				oldActivity.getPassPuntuation(), oldActivity.getFeedbackCorrectMap(), oldActivity.getFeedbackNoCorrectMap(), 
				oldActivity.getCommentsActivated(), null, serviceContext);
		
		//Ahora copiamos los ficheros asociados y las imágenes
		
		copyActivityImages(oldActivity, newActivity);
		copyAttachmentsFileEntries(oldActivity, newActivity);
		
		LearningActivityTypeFactory activityTypeFactory = LearningActivityTypeFactoryRegistryUtil.getLearningActivityTypeFactoryByType(newActivity.getTypeId());
		LearningActivityType activityType = activityTypeFactory.getLearningActivityType(newActivity);
		log.debug("extraContent antes: " + newActivity.getExtraContent());
		activityType.copyActivity(oldActivity, serviceContext);
		log.debug("extraContent antes: " + newActivity.getExtraContent());
		
		newActivity = learningActivityPersistence.update(newActivity);
		
		resourceCopy.copyModelResourcePermissions(newActivity.getCompanyId(), LearningActivity.class.getName(), oldActivity.getActId(), newActivity.getActId());
		
		//Copiamos los expandos
		ExpandoBridgeUtil.copyExpandoBridgeAttributes(oldActivity.getExpandoBridge(), newActivity.getExpandoBridge());
		
		//Duplicamos los prerequisitos
		String[] classNamePrerequisites = getPrerequisiteActivities(newActivity.getCompanyId());
		PrerequisiteFactory prerequisiteFactory = null;
		
		long activityClassNameId = PortalUtil.getClassNameId(LearningActivity.class);
		
		for(String classNamePrerequisite: classNamePrerequisites){
			prerequisiteFactory = PrerequisiteFactoryRegistryUtil.getPrerequisiteFactoryByClassName(classNamePrerequisite);
			if(prerequisiteFactory != null) {
				prerequisiteFactory.copyPrerequisite(activityClassNameId, oldActivity.getActId(), newActivity.getActId());
			}
		}
		
		return newActivity;
		
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
	
	protected void copyActivityImages(LearningActivity oldActivity, LearningActivity newActivity) throws Exception {
		newActivity.setDescription(dlReferencesCopyContentProcessor.replaceExportDLReferences(newActivity.getDescription(), oldActivity.getGroupId(), 
				newActivity.getGroupId(), newActivity.getUserId()));
	}
	
	protected void copyAttachmentsFileEntries(LearningActivity oldActivity, LearningActivity newActivity) throws Exception {
		List<FileEntry> oldAttachmentsFileEntries = oldActivity.getAttachmentsFileEntries();
		
		for(FileEntry fileEntry: oldAttachmentsFileEntries) {
			addAttachment(newActivity.getUserId(), newActivity, fileEntry.getFileName(), fileEntry.getContentStream(), fileEntry.getMimeType());
		}
	}
	
	@Override
	public FileEntry addAttachment(
			long userId, LearningActivity activity, String fileName,
			InputStream inputStream, String mimeType)
		throws PortalException {

		log.debug("attachmentsFolderId: " + activity.getAttachmentsFolderId());

		return portletFileRepository.addPortletFileEntry(
				activity.getGroupId(), userId, LearningActivity.class.getName(),
				activity.getActId(), LMSConstants.SERVICE_NAME,
				activity.getAttachmentsFolderId(), inputStream, fileName, mimeType,
			false);
	}
	
	protected void addLearningActivityAttachment(
			long userId, long groupId, LearningActivity activity,
			String selectedFileName)
		throws PortalException {
		
		log.debug("groupId: " + groupId);
		log.debug("userId: " + userId);
		log.debug("folderName: " + LearningActivityConstants.TEMP_FOLDER_NAME);
		log.debug("selectedFileName: " + selectedFileName);
	
		FileEntry tempFileEntry = TempFileEntryUtil.getTempFileEntry(
			groupId, userId, LearningActivityConstants.TEMP_FOLDER_NAME,
			selectedFileName);
		log.debug("tempFileEntry: " + tempFileEntry.getFileEntryId());

		InputStream inputStream = tempFileEntry.getContentStream();
		String mimeType = tempFileEntry.getMimeType();

		addAttachment(userId, activity, selectedFileName, inputStream, mimeType);

		if (tempFileEntry != null) {
			TempFileEntryUtil.deleteTempFileEntry(tempFileEntry.getFileEntryId());
		}
	}
	
	protected void addLearningActivityAttachments(
			long userId, LearningActivity activity, String[] selectedFileNames)
		throws PortalException {

		if (ArrayUtil.isEmpty(selectedFileNames)) {
			return;
		}

		for (String selectedFileName : selectedFileNames) {
			log.debug("añadimos el archivo: " + selectedFileName);
			addLearningActivityAttachment(
				userId, activity.getGroupId(), activity,
				selectedFileName);
		}
	}
	
	protected void removeLearningActivityAttachments(long[] removeFileEntryIds) throws PortalException {

		if (ArrayUtil.isEmpty(removeFileEntryIds)) {
			return;
		}

		for (long removeFileEntryId : removeFileEntryIds) {
			portletFileRepository.deletePortletFileEntry(removeFileEntryId);
		}
	}
	
	@Override
	public LearningActivity deleteLearningActivity(LearningActivity activity) {
		try {
			activity = super.deleteLearningActivity(activity);
			LearningActivityTypeFactory activityTypeFactory = LearningActivityTypeFactoryRegistryUtil.getLearningActivityTypeFactoryByType(activity.getTypeId());
			LearningActivityType activityType = activityTypeFactory.getLearningActivityType(activity);
			
			long userId = PrincipalThreadLocal.getUserId();
			User user = userLocalService.getUser(userId);
			
			AuditFactory.audit(activity.getCompanyId(), activity.getGroupId(), LMSAuditConstants.LEARNING_ACTIVITY_REMOVE, LearningActivity.class.getName(), activity.getActId(), 
					userId, user.getFullName(), null);
			
			activityType.onDelete();
		}catch (PortalException e) {
			e.printStackTrace();
			activity = null;
		}
		
		return activity;
	}
	
	public boolean deleteTries(long userId, long actId, long studentId, boolean deleteOnlyFailed) {
		
		LearningActivity activity = learningActivityPersistence.fetchByPrimaryKey(actId);
		
		LearningActivityTypeFactory learningActivityTypeFactory = LearningActivityTypeFactoryRegistryUtil.getLearningActivityTypeFactoryByType(activity.getTypeId());
		if(learningActivityTypeFactory.canDeleteTries()) {
			List<LearningActivityResult> learningActivityResults = null;
			if(studentId > 0) {
				learningActivityResults = new ArrayList<LearningActivityResult>();
				LearningActivityResult learningActivityResult = learningActivityResultPersistence.fetchByActIdUserId(actId, studentId);
				if(learningActivityResult != null) {
					learningActivityResults.add(learningActivityResult);
				}
			}else if(deleteOnlyFailed) {
				learningActivityResults = learningActivityResultPersistence.findByActIdPassedEndDateNotNull(actId, false);
			}else {
				learningActivityResults = learningActivityResultPersistence.findByActId(actId);
			}
			
			for(LearningActivityResult learningActivityResult: learningActivityResults) {
				learningActivityResultLocalService.deleteLearningActivityResult(learningActivityResult);
			}
			
			return true;
		}else {
			return false;
		}
	}
	
	public void executeDeleteTries(long userId, long groupId, long actId, long studentId, boolean deleteOnlyFailed, ServiceContext serviceContext) throws PortalException {
		Map<String, Serializable> taskContextMap = new HashMap<>();
		
		taskContextMap.put("userId", userId);
		taskContextMap.put("actId", actId);
		taskContextMap.put("studentId", studentId);
		taskContextMap.put("deleteOnlyFailed", deleteOnlyFailed);
		
		BackgroundTaskManagerUtil.addBackgroundTask(userId, groupId,
				LMSPortletKeys.MODULES_ACTIVITIES, DeleteTriesBackgroundTaskExecutor.class.getName(),
				taskContextMap, serviceContext);
	}
	
	public boolean hasDeleteTriesInProgress(long actId, long groupId) {
		List<BackgroundTask> backgroundTasks = BackgroundTaskManagerUtil.getBackgroundTasks(groupId, DeleteTriesBackgroundTaskExecutor.class.getName(), BackgroundTaskConstants.STATUS_IN_PROGRESS);
		
		List<BackgroundTask> filterBackgroundTasks = backgroundTasks.stream().filter(background -> 
			MapUtil.getLong(background.getTaskContextMap(), "actId", 0) == actId).collect(Collectors.toList()
		);
		return filterBackgroundTasks != null && filterBackgroundTasks.size() > 0;
	}
	
	
	@Reference
	protected CommentManager commentManager;
	
	@Reference
	protected TrashEntryLocalService trashEntryLocalService;
	
	@Reference
	protected PortletFileRepository portletFileRepository;

	@Reference
	protected DLReferencesCopyContentProcessor dlReferencesCopyContentProcessor;
	
	@Reference
	protected ResourceCopy resourceCopy;
	
	@Reference
	protected LearningActivityResultLocalService learningActivityResultLocalService;
}