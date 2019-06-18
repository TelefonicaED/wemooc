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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.TempFileEntryUtil;
import com.ted.lms.constants.LMSActionKeys;
import com.ted.lms.constants.LearningActivityConstants;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.Module;
import com.ted.lms.service.base.LearningActivityServiceBaseImpl;

/**
 * The implementation of the learning activity remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ted.lms.service.LearningActivityService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LearningActivityServiceBaseImpl
 * @see com.ted.lms.service.LearningActivityServiceUtil
 */
public class LearningActivityServiceImpl extends LearningActivityServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.ted.lms.service.LearningActivityServiceUtil} to access the learning activity remote service.
	 */
	
	private static volatile ModelResourcePermission<LearningActivity>
		learningActivityModelResourcePermission =
	    ModelResourcePermissionFactory.getInstance(
	        LearningActivityServiceImpl.class,
	        "learningActivityModelResourcePermission", LearningActivity.class);
	
	private static volatile ModelResourcePermission<Module> moduleModelResourcePermission = 
			ModelResourcePermissionFactory.getInstance(ModuleServiceImpl.class, "moduleModelResourcePermission", Module.class);
	
	@Override
	public LearningActivity updateLearningActivity(LearningActivity activity) throws PortalException {

		learningActivityModelResourcePermission.check(getPermissionChecker(), activity.getActId(), ActionKeys.UPDATE);

		return learningActivityLocalService.updateLearningActivity(activity);
	}
	
	@Override
	public LearningActivity moveDownLearningActivity(long actId) throws PortalException{
		learningActivityModelResourcePermission.check(getPermissionChecker(), actId, ActionKeys.UPDATE);
		
		return learningActivityLocalService.moveDownLearningActivity(getUserId(), actId);
	}
	
	@Override
	public LearningActivity moveUpLearningActivity(long actId) throws PortalException{
		learningActivityModelResourcePermission.check(getPermissionChecker(), actId, ActionKeys.UPDATE);
		
		return learningActivityLocalService.moveUpLearningActivity(getUserId(), actId);
	}
	
	@Override
	public LearningActivity moveLearningActivityToTrash(long actId) throws PortalException {
		learningActivityModelResourcePermission.check(getPermissionChecker(), actId, ActionKeys.DELETE);

		return learningActivityLocalService.moveLearningActivityToTrash(getUserId(), actId);
	}
	
	@Override
	public LearningActivity deleteLearningActivity(long actId) throws PortalException {
		learningActivityModelResourcePermission.check(getPermissionChecker(), actId, ActionKeys.DELETE);

		return learningActivityLocalService.deleteLearningActivity(actId);
	}
	
	@Override
	public LearningActivity addLearningActivity(long groupId, long moduleId, long type, Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
			boolean useStartExecutionDateCourse, int startDateMonth, int startDateDay, int startDateYear,
			int startDateHour, int startDateMinute, boolean useEndExecutionDateCourse, int endDateMonth, int endDateDay,
			int endDateYear, int endDateHour, int endDateMinute, boolean required, int tries, double passPuntuation, Map<Locale, String> feedbackCorrectMap, 
			Map<Locale, String> feedbackNoCorrectMap, boolean commentsActivated, String[] selectedFileNames, ServiceContext serviceContext) throws PortalException {
		moduleModelResourcePermission.check(getPermissionChecker(), moduleId, LMSActionKeys.ADD_ACT);
		
		return learningActivityLocalService.addLearningActivity(getUserId(), groupId, moduleId, type, titleMap, descriptionMap, useStartExecutionDateCourse, startDateMonth, 
				startDateDay, startDateYear, startDateHour, startDateMinute, useEndExecutionDateCourse, endDateMonth, endDateDay, endDateYear, 
				endDateHour, endDateMinute, required, tries, passPuntuation, feedbackCorrectMap, feedbackNoCorrectMap, commentsActivated, 
				selectedFileNames, serviceContext);
	}
	
	@Override
	public LearningActivity updateLearningActivity(long actId, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap, boolean useStartExecutionDateCourse, int startDateMonth,
			int startDateDay, int startDateYear, int startDateHour, int startDateMinute,
			boolean useEndExecutionDateCourse, int endDateMonth, int endDateDay, int endDateYear, int endDateHour,
			int endDateMinute, boolean required, int tries, double passPuntuation, Map<Locale, String> feedbackCorrectMap,
			Map<Locale, String> feedbackNoCorrectMap, boolean commentsActivated, String[] selectedFileNames, 
			long[] removeFileEntryIds, ServiceContext serviceContext) throws PrincipalException, PortalException {
		
		learningActivityModelResourcePermission.check(getPermissionChecker(), actId, ActionKeys.UPDATE);
		
		return learningActivityLocalService.updateLearningActivity(getUserId(), actId, titleMap, descriptionMap, useStartExecutionDateCourse, startDateMonth, 
				startDateDay, startDateYear, startDateHour, startDateMinute, useEndExecutionDateCourse, endDateMonth, endDateDay, endDateYear, 
				endDateHour, endDateMinute, required, tries, passPuntuation, feedbackCorrectMap, feedbackNoCorrectMap, commentsActivated, 
				selectedFileNames, removeFileEntryIds, serviceContext);
	}
	
	@Override
	public List<LearningActivity> getActivities(long moduleId){
		List<LearningActivity> activities = new ArrayList<LearningActivity>();
		
		List<LearningActivity> listActivities = learningActivityLocalService.getLearningActivities(moduleId);
		
		for(LearningActivity activity: listActivities) {
			try {
				if(learningActivityModelResourcePermission.contains(getPermissionChecker(), activity, ActionKeys.VIEW)) {
					activities.add(activity);
				}
			} catch (PortalException e) {
				e.printStackTrace();
			}
		}
		
		return activities;
	}
	
	@Override
	public List<LearningActivity> getActivitiesExcluded(long moduleId, long actId){
		List<LearningActivity> activities = new ArrayList<LearningActivity>();
		
		List<LearningActivity> listActivities = learningActivityLocalService.getLearningActivities(moduleId);
		
		for(LearningActivity activity: listActivities) {
			try {
				if(activity.getActId() != actId && learningActivityModelResourcePermission.contains(getPermissionChecker(), activity, ActionKeys.VIEW)) {
					activities.add(activity);
				}
			} catch (PortalException e) {
				e.printStackTrace();
			}
		}
		
		return activities;
	}
	
	@Override
	public List<LearningActivity> getActivitiesNotTypeId(long moduleId, long typeId){
		List<LearningActivity> activities = new ArrayList<LearningActivity>();
		
		List<LearningActivity> listActivities = learningActivityLocalService.getLearningActivitiesNotTypeId(moduleId, typeId);
		
		for(LearningActivity activity: listActivities) {
			try {
				if(learningActivityModelResourcePermission.contains(getPermissionChecker(), activity, ActionKeys.VIEW)) {
					activities.add(activity);
				}
			} catch (PortalException e) {
				e.printStackTrace();
			}
		}
		
		return activities;
	}
	
	@Override
	public String[] getTempFileNames(long groupId) throws PortalException {

		return TempFileEntryUtil.getTempFileNames(groupId, getUserId(),LearningActivityConstants.TEMP_FOLDER_NAME);
	}
}