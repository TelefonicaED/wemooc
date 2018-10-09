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
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.service.base.LearningActivityLocalServiceBaseImpl;

import java.util.Date;
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
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.ted.lms.service.LearningActivityLocalServiceUtil} to access the learning activity local service.
	 */
	
	public List<LearningActivity> getRequiredLearningActivitiesOfGroup(long groupId){
		return learningActivityPersistence.findByGroupIdRequired(groupId, true);
	}
	
	public List<LearningActivity> getRequiredLearningActivitiesOfModule(long moduleId){
		return learningActivityPersistence.findByModuleIdRequired(moduleId, true);
	}
	
	public int getLearningActivitiesOfModuleCount(long moduleId) {
		return learningActivityPersistence.countByModuleId(moduleId);
	}
	
	public void deleteLearningActivities(long moduleId) {
		List<LearningActivity> listLearningActivities = learningActivityPersistence.findByModuleId(moduleId);
		for(LearningActivity learningActivity: listLearningActivities) {
			learningActivityPersistence.remove(learningActivity);
		}
	}
	
	@Indexable(type = IndexableType.REINDEX)
	public LearningActivity addLearningActivity(long groupId, long userId, long moduleId, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap, long typeId, Date startDate, Date endDate, int tries, int passPuntuation, long priority,
			String extraContent, String feedbackCorrect, String feedbackNoCorrect, boolean required, boolean commentsActivated,
			ServiceContext serviceContext) {
		LearningActivity activity = null;
		try {
			activity = learningActivityPersistence.create(counterLocalService.increment(LearningActivity.class.getName()));
			activity.setGroupId(groupId);
			activity.setCompanyId(serviceContext.getCompanyId());
			activity.setUserId(userId);
			User user = userLocalService.fetchUser(userId);
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
			activity.setPriority(priority);
			activity.setExtraContent(extraContent);
			activity.setFeedbackCorrect(feedbackCorrect);
			activity.setFeedbackNoCorrect(feedbackNoCorrect);
			activity.setRequired(required);
			activity.setCommentsActivated(commentsActivated);
			activity.setStatus(WorkflowConstants.STATUS_APPROVED);
			activity.setStatusByUserId(userId);
			activity.setStatusDate(activity.getCreateDate());
			
			activity = learningActivityPersistence.update(activity);
		
			resourceLocalService.addResources(activity.getCompanyId(), activity.getGroupId(), activity.getUserId(),  LearningActivity.class.getName(), 
					activity.getActId(), false, true, false);
			
			updateAsset(serviceContext.getUserId(), activity, serviceContext.getAssetCategoryIds(), serviceContext.getAssetTagNames(), 
					serviceContext.getAssetLinkEntryIds(), serviceContext.getAssetPriority());
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return activity;
		
	}
	
	@Indexable(type = IndexableType.REINDEX)
	public LearningActivity updateLearningActivity(long actId, long moduleId, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap, long typeId, Date startDate, Date endDate, int tries, int passPuntuation, long priority,
			String extraContent, String feedbackCorrect, String feedbackNoCorrect, boolean required, boolean commentsActivated,
			ServiceContext serviceContext) {
		LearningActivity activity = null;
		try {
			activity = learningActivityPersistence.fetchByPrimaryKey(actId);
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
			activity.setFeedbackCorrect(feedbackCorrect);
			activity.setFeedbackNoCorrect(feedbackNoCorrect);
			activity.setRequired(required);
			activity.setCommentsActivated(commentsActivated);
			
			activity = learningActivityPersistence.update(activity);
			
			updateAsset(serviceContext.getUserId(), activity, serviceContext.getAssetCategoryIds(), serviceContext.getAssetTagNames(), 
					serviceContext.getAssetLinkEntryIds(), serviceContext.getAssetPriority());
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
	
}