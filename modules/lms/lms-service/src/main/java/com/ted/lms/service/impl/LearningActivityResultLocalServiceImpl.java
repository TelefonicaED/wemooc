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
import com.ted.lms.service.ModuleResultLocalService;
import com.ted.lms.service.base.LearningActivityResultLocalServiceBaseImpl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.util.Validator;
import com.ted.audit.api.AuditFactory;
import com.ted.lms.constants.LMSAuditConstants;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.LearningActivityResult;
import com.ted.lms.model.LearningActivityTry;
import com.ted.lms.model.ModuleResult;
import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * The implementation of the learning activity result local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.ted.lms.service.LearningActivityResultLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LearningActivityResultLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=com.ted.lms.model.LearningActivityResult",
	service = AopService.class
)
public class LearningActivityResultLocalServiceImpl
	extends LearningActivityResultLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>com.ted.lms.service.LearningActivityResultLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.ted.lms.service.LearningActivityResultLocalServiceUtil</code>.
	 */
	
	@Override
	public List<LearningActivityResult> getRequiredLearningActivityResults(long groupId, long userId){
		return learningActivityResultFinder.findRequiredLearningActivityResults(groupId, userId);
	}
	
	@Override
	public int countRequiredLearningActivityResults(long groupId, long userId){
		return learningActivityResultFinder.countRequiredLearningActivityResults(groupId, userId);
	}
	
	@Override
	public LearningActivityResult getLearningActivityResult(long actId, long userId) {
		log.debug("actId: " + actId);
		log.debug("userId: " + userId);
		return learningActivityResultPersistence.fetchByActIdUserId(actId, userId);
	}
	
	@Override
	public int getRequiredLearningActivityResultsByModuleCount(long moduleId, long userId){
		return learningActivityResultFinder.countRequiredLearningActivityResultsByModule(moduleId, userId);
	}
	
	@Override
	public int getLearningActivityCountByActId(long actId) {
		return learningActivityResultPersistence.countByActId(actId);
	}
	
	@Override
	public boolean hasUserPassed(long actId, long userId) {
		LearningActivityResult learningActivityResult = learningActivityResultPersistence.fetchByActIdUserId(actId, userId);
		return learningActivityResult != null && learningActivityResult.isPassed();
	}
	
	public LearningActivityResult updateLearningActivityResult(LearningActivityTry learningActivityTry, ServiceContext serviceContext) throws PortalException {

		if(serviceContext == null) {
			serviceContext = ServiceContextThreadLocal.getServiceContext();
		}
		
		LearningActivityResult learningActivityResult = learningActivityResultPersistence.fetchByActIdUserId(learningActivityTry.getActId(), learningActivityTry.getUserId());
		
		boolean recalculateActivity = false;
		
		Date now = new Date();
		
		log.debug("****LAR "+learningActivityResult);
		if(learningActivityResult == null){	
			learningActivityResult = learningActivityResultPersistence.create(counterLocalService.increment(LearningActivityResult.class.getName()));
			
			learningActivityResult.setGroupId(learningActivityTry.getGroupId());
			
			learningActivityResult.setCompanyId(learningActivityTry.getCompanyId());
			learningActivityResult.setCreateDate(now);
			
			learningActivityResult.setActId(learningActivityTry.getActId());
			learningActivityResult.setUserId(learningActivityTry.getUserId());
			learningActivityResult.setPassed(false);
			learningActivityResult.setStartDate(learningActivityTry.getStartDate());
			
			recalculateActivity = true;
		}
		
		log.debug("****END DATE "+learningActivityTry.getEndDate());
		
		LearningActivity learningActivity = learningActivityPersistence.fetchByPrimaryKey(learningActivityTry.getActId());
		
		if(learningActivityTry.getEndDate()!=null){
			
			long numTries = learningActivityTryPersistence.countByActIdUserId(learningActivityTry.getActId(), learningActivityTry.getUserId());
			
			log.debug("****Cuantos try llevo "+numTries);
			
			if(learningActivity.getTries()>0 && numTries >= learningActivity.getTries()){
				learningActivityResult.setEndDate(learningActivityTry.getEndDate());
				recalculateActivity= true;
				log.debug("****Recalculamos 1");
			}

			if(learningActivityTry.getResult() > learningActivityResult.getResult()){			
				learningActivityResult.setResult(learningActivityTry.getResult());
				recalculateActivity= true;
				log.debug("****Recalculamos 2");
			}

			if(!learningActivityResult.getPassed() && learningActivityTry.getResult() >= learningActivity.getPassPuntuation()){
				learningActivityResult.setEndDate(learningActivityTry.getEndDate());
				learningActivityResult.setPassed(true);	
				recalculateActivity= true;	
				log.debug("****Recalculamos 3");
			}	
			if(Validator.isNotNull(learningActivityTry.getComments())&&!learningActivityTry.getComments().equals(learningActivityResult.getComments())){
				learningActivityResult.setComments(learningActivityTry.getComments());
				recalculateActivity= true;
				log.debug("****Recalculamos 4");
			}
		}
		
		log.debug("****Recalculate "+recalculateActivity);
		if(recalculateActivity){
			User userModified = userLocalService.getUser(serviceContext.getUserId());
			learningActivityResult.setUserModifiedId(userModified.getUserId());
			learningActivityResult.setUserModifiedName(userModified.getFullName());
			learningActivityResult.setModifiedDate(now);
			
			learningActivityResultPersistence.update(learningActivityResult);
			
			moduleResultLocalService.updateModuleResult(learningActivityResult);
			
			//auditing
			AuditFactory.audit(serviceContext.getCompanyId(), serviceContext.getScopeGroupId(), LMSAuditConstants.LEARNING_ACTIVITY_RESULT_UPDATE, 
					LearningActivityResult.class.getName(), learningActivityResult.getPrimaryKey(), userModified.getUserId(), 
					userModified.getFullName(), null);
				
		}
		return learningActivityResult;

	}
	
	public LearningActivityResult deleteLearningActivityResult(long userId, LearningActivityResult learningActivityResult, boolean recalculate) throws PortalException {
		
		User user = userLocalService.getUser(userId);
		
		AuditFactory.audit(learningActivityResult.getCompanyId(), learningActivityResult.getGroupId(), LMSAuditConstants.LEARNING_ACTIVITY_RESULT_REMOVE, 
				LearningActivityResult.class.getName(), learningActivityResult.getActId(), userId, user.getFullName(), null);
		
		learningActivityResult = super.deleteLearningActivityResult(learningActivityResult);
		
		if(recalculate) {
			LearningActivity activity = learningActivityPersistence.fetchByPrimaryKey(learningActivityResult.getActId());
			if(activity.isRequired()) {
				ModuleResult moduleResult = moduleResultLocalService.getModuleResult(activity.getModuleId(), learningActivityResult.getUserId());
				moduleResultLocalService.recalculate(userId, moduleResult);
			}
		}
		
		return learningActivityResult;
	}
	
	public long countStudentFinished(long actId, long companyId, long courseGroupId) {
		return learningActivityResultFinder.countStudentsFinished(actId, courseGroupId, companyId);
	}
	
	private static Log log = LogFactoryUtil.getLog(LearningActivityResultLocalServiceImpl.class);
	
	@Reference
	protected ModuleResultLocalService moduleResultLocalService;
}