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
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.comment.CommentManager;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepository;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.social.SocialActivityManagerUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.TempFileEntryUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.spring.extender.service.ServiceReference;
import com.liferay.social.kernel.model.SocialActivityConstants;
import com.liferay.trash.exception.TrashEntryException;
import com.liferay.trash.service.TrashEntryLocalService;
import com.ted.lms.constants.LMSActivityKeys;
import com.ted.lms.constants.LMSConstants;
import com.ted.lms.constants.LearningActivityConstants;
import com.ted.lms.exception.LearningActivityEndDateException;
import com.ted.lms.exception.LearningActivityStartDateException;
import com.ted.lms.exception.NoSuchNextLearningActivityException;
import com.ted.lms.exception.NoSuchPreviousLearningActivityException;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.Module;
import com.ted.lms.service.base.LearningActivityLocalServiceBaseImpl;
import com.ted.lms.util.LMSPrefsPropsValues;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * The implementation of the learning activity local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ted.lms.service.LearningActivityLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LearningActivityLocalServiceBaseImpl
 * @see com.ted.lms.service.LearningActivityLocalServiceUtil
 */
public class LearningActivityLocalServiceImpl
	extends LearningActivityLocalServiceBaseImpl {
	
	private static final Log log = LogFactoryUtil.getLog(LearningActivityLocalServiceImpl.class);
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.ted.lms.service.LearningActivityLocalServiceUtil} to access the learning activity local service.
	 */
	
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
	public LearningActivity addLearningActivity(long moduleId, long type, Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
			boolean useStartExecutionDateCourse, int startDateMonth, int startDateDay, int startDateYear,
			int startDateHour, int startDateMinute, boolean useEndExecutionDateCourse, int endDateMonth, int endDateDay,
			int endDateYear, int endDateHour, int endDateMinute, boolean required, int tries, double passPuntuation,
			Map<Locale, String> feedbackCorrectMap, Map<Locale, String> feedbackNoCorrectMap, boolean commentsActivated, 
			String[] selectedFileNames, ServiceContext serviceContext) throws PortalException {
		
		User user = userLocalService.getUser(serviceContext.getUserId());
		
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
		
		return learningActivityLocalService.addLearningActivity(moduleId, type, titleMap, descriptionMap, startDate, endDate, required, tries, 
				passPuntuation, feedbackCorrectMap, feedbackNoCorrectMap, commentsActivated, selectedFileNames, serviceContext);
		
	}
	
	@Override
	public LearningActivity addLearningActivity(long moduleId, long type, Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
			Date startDate, Date endDate, boolean required, int tries, double passPuntuation, Map<Locale, String> feedbackCorrectMap, 
			Map<Locale, String> feedbackNoCorrectMap, boolean commentsActivated, String[] selectedFileNames, ServiceContext serviceContext) throws PortalException {
		
		return learningActivityLocalService.addLearningActivity(moduleId, titleMap, descriptionMap, type, startDate, endDate, 
				tries, passPuntuation, 0, null, feedbackCorrectMap, feedbackNoCorrectMap, required, commentsActivated, selectedFileNames, serviceContext);
		
	}
	
	@Override
	@Indexable(type = IndexableType.REINDEX)
	public LearningActivity addLearningActivity(long moduleId, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap, long typeId, Date startDate, Date endDate, int tries, double passPuntuation, long priority,
			String extraContent, Map<Locale, String> feedbackCorrectMap, Map<Locale, String> feedbackNoCorrectMap, boolean required, boolean commentsActivated,
			String[] selectedFileNames, ServiceContext serviceContext) throws PortalException {
		
		long actId = counterLocalService.increment(LearningActivity.class.getName());
		
		LearningActivity activity = learningActivityPersistence.create(actId);
		activity.setGroupId(serviceContext.getScopeGroupId());
		activity.setCompanyId(serviceContext.getCompanyId());
		activity.setUserId(serviceContext.getUserId());
		User user = userLocalService.fetchUser(serviceContext.getUserId());
		if(user != null) {
			activity.setUserName(user.getFullName());
			activity.setStatusByUserName(user.getFullName());
		}
		activity.setCreateDate(new Date());
		activity.setModifiedDate(activity.getCreateDate());
		activity.setModuleId(moduleId);
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
		activity.setStatusByUserId(serviceContext.getUserId());
		activity.setStatusDate(activity.getCreateDate());
		
		activity = learningActivityPersistence.update(activity);
	
		resourceLocalService.addResources(activity.getCompanyId(), activity.getGroupId(), activity.getUserId(),  LearningActivity.class.getName(), 
				activity.getActId(), false, true, false);
		
		updateAsset(serviceContext.getUserId(), activity, serviceContext.getAssetCategoryIds(), serviceContext.getAssetTagNames(), 
				serviceContext.getAssetLinkEntryIds(), serviceContext.getAssetPriority());
		
		addLearningActivityAttachments(serviceContext.getUserId(), activity, selectedFileNames);
		
		return activity;
		
	}
	
	@Override
	public LearningActivity updateLearningActivity(long actId, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap, boolean useStartExecutionDateCourse, int startDateMonth,
			int startDateDay, int startDateYear, int startDateHour, int startDateMinute,
			boolean useEndExecutionDateCourse, int endDateMonth, int endDateDay, int endDateYear, int endDateHour,
			int endDateMinute, boolean required, int tries, double passPuntuation, Map<Locale, String> feedbackCorrectMap,
			Map<Locale, String> feedbackNoCorrectMap, boolean commentsActivated, String[] selectedFileNames, long[] removeFileEntryIds, 
			ServiceContext serviceContext) throws PortalException {
		
		User user = userLocalService.getUser(serviceContext.getUserId());
		
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
		
		return updateLearningActivity(actId, titleMap, descriptionMap, startDate, endDate, tries, passPuntuation, 
				feedbackCorrectMap, feedbackNoCorrectMap, required, commentsActivated, selectedFileNames, removeFileEntryIds, 
				serviceContext);
	}
	
	@Override
	public LearningActivity updateLearningActivity(long actId, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap, Date startDate, Date endDate, int tries, double passPuntuation,
			Map<Locale, String> feedbackCorrectMap, Map<Locale, String> feedbackNoCorrectMap, boolean required, boolean commentsActivated,
			String[] selectedFileNames, long[] removeFileEntryIds, ServiceContext serviceContext) throws PortalException {
		
		LearningActivity activity = learningActivityPersistence.findByPrimaryKey(actId);
		
		return updateLearningActivity(actId, activity.getModuleId(), titleMap, descriptionMap, activity.getTypeId(), 
				startDate, endDate, tries, passPuntuation, activity.getPriority(), activity.getExtraContent(), feedbackCorrectMap, feedbackNoCorrectMap, 
				required, commentsActivated, selectedFileNames, removeFileEntryIds, serviceContext);
		
	}
	
	@Override
	@Indexable(type = IndexableType.REINDEX)
	public LearningActivity updateLearningActivity(long actId, long moduleId, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap, long typeId, Date startDate, Date endDate, int tries, double passPuntuation, long priority,
			String extraContent, Map<Locale, String> feedbackCorrectMap, Map<Locale, String> feedbackNoCorrectMap, boolean required, boolean commentsActivated,
			String[] selectedFileNames, long[] removeFileEntryIds, ServiceContext serviceContext) throws PortalException {
		
		LearningActivity activity = learningActivityPersistence.fetchByPrimaryKey(actId);
		
		activity.setModifiedDate(new Date());
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
		
		updateAsset(serviceContext.getUserId(), activity, serviceContext.getAssetCategoryIds(), serviceContext.getAssetTagNames(), 
				serviceContext.getAssetLinkEntryIds(), serviceContext.getAssetPriority());
		
		addLearningActivityAttachments(serviceContext.getUserId(), activity, selectedFileNames);

		removeLearningActivityAttachments(removeFileEntryIds);
		
		return activity;
		
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
	public LearningActivity moveDownLearningActivity(long actId , ServiceContext serviceContext) throws PortalException{
		
		LearningActivity activity = learningActivityPersistence.fetchByPrimaryKey(actId);
		
		LearningActivity nextActivity = getNextLearningActivity(activity);
		
		if(nextActivity!=null) {
			Date now = new Date();
			//Se actualiza el orden
			long priority = activity.getPriority();
			activity.setPriority(nextActivity.getPriority());
			nextActivity.setPriority(priority);
			
			activity.setModifiedDate(serviceContext.getModifiedDate(now));
			nextActivity.setModifiedDate(serviceContext.getModifiedDate(now));
			
			learningActivityPersistence.update(activity);			
			learningActivityPersistence.update(nextActivity);
		}
		
		return activity;
	}
	
	@Override
	public LearningActivity moveUpLearningActivity(long actId , ServiceContext serviceContext) throws PortalException{
		
		LearningActivity activity = learningActivityPersistence.fetchByPrimaryKey(actId);
		
		LearningActivity previousActivity = getPreviousLearningActivity(activity);
		
		if(previousActivity!=null) {
			Date now = new Date();
			long priority=activity.getPriority();
			activity.setPriority(previousActivity.getPriority());
			previousActivity.setPriority(priority);
			
			activity.setModifiedDate(serviceContext.getModifiedDate(now));
			previousActivity.setModifiedDate(serviceContext.getModifiedDate(now));
			
			learningActivityPersistence.update(activity);			
			learningActivityPersistence.update(previousActivity);
		}
		
		return activity;
	}
	
	@Override
	public LearningActivity getNextLearningActivity(LearningActivity activity) throws PortalException {
		List<LearningActivity> listNextLearningActivities = learningActivityPersistence.findByModuleIdNextLearningActivities(activity.getModuleId(), 
				activity.getPriority());
		LearningActivity nextLearningActivity = null;
		if(listNextLearningActivities != null && listNextLearningActivities.size() > 0) {
			nextLearningActivity = listNextLearningActivities.get(0);
		}else {
			throw new NoSuchNextLearningActivityException();
		}
		return nextLearningActivity;
	}
	
	@Override
	public LearningActivity getPreviousLearningActivity(LearningActivity activity) throws PortalException {
		List<LearningActivity> listPreviousLearningActivities = learningActivityPersistence.findByModuleIdPreviousLearningActivities(activity.getModuleId(), 
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

		return activity;
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
	
	@ServiceReference(type = CommentManager.class)
	protected CommentManager commentManager;
	
	@ServiceReference(type = TrashEntryLocalService.class)
	protected TrashEntryLocalService trashEntryLocalService;
	
	@BeanReference(type = PortletFileRepository.class)
	protected PortletFileRepository portletFileRepository;
	
}