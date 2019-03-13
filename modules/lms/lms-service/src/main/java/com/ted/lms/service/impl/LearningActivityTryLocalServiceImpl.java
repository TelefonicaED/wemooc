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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;
import com.ted.audit.api.AuditFactory;
import com.ted.lms.constants.LMSAuditConstants;
import com.ted.lms.exception.NoSuchLearningActivityException;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.LearningActivityTry;
import com.ted.lms.service.base.LearningActivityTryLocalServiceBaseImpl;

import java.util.Date;
import java.util.List;

/**
 * The implementation of the learning activity try local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ted.lms.service.LearningActivityTryLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LearningActivityTryLocalServiceBaseImpl
 * @see com.ted.lms.service.LearningActivityTryLocalServiceUtil
 */
public class LearningActivityTryLocalServiceImpl
	extends LearningActivityTryLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.ted.lms.service.LearningActivityTryLocalServiceUtil} to access the learning activity try local service.
	 */
	
	public int getLearningActivityTriesCount(long actId, long userId) {
		return learningActivityTryPersistence.countByActIdUserId(actId, userId);
	}
	
	public List<LearningActivityTry> getLearningActivityTries(long actId, long userId){
		return learningActivityTryPersistence.findByActIdUserId(actId, userId);
	}
	
	public LearningActivityTry getLastLearningActivityTry(long actId, long userId) {
		LearningActivityTry learningActivityTry = null;
		
		List<LearningActivityTry> learningActivityTries =  learningActivityTryPersistence.findByActIdUserId(actId, userId);
		if(learningActivityTries != null && learningActivityTries.size() > 0) {
			learningActivityTry = learningActivityTries.get(learningActivityTries.size()-1);
		}
		return learningActivityTry;
	}
	
	public LearningActivityTry addLearningActivityTry(long actId, long userId, ServiceContext serviceContext) throws PortalException {
		LearningActivity learningActivity = learningActivityPersistence.fetchByPrimaryKey(actId);
		
		if(Validator.isNull(learningActivity)) {
			throw new NoSuchLearningActivityException();
		}
		
		LearningActivityTry learningActivityTry = learningActivityTryPersistence.create(counterLocalService.increment(LearningActivityTry.class.getName()));
		
		learningActivityTry.setGroupId(learningActivity.getGroupId());
		
		//Campos de auditoria
		User user = userLocalService.getUser(serviceContext.getUserId());
		Date now = new Date();
		learningActivityTry.setCompanyId(serviceContext.getCompanyId());
		learningActivityTry.setUserModifiedId(serviceContext.getUserId());
		learningActivityTry.setUserModifiedName(user.getFullName());
		learningActivityTry.setCreateDate(now);
		learningActivityTry.setModifiedDate(now);
		
		learningActivityTry.setActId(actId);
		learningActivityTry.setUserId(userId);
		learningActivityTry.setResult(0);
		
		learningActivityTry.setStartDate(now);
		
		learningActivityTry = learningActivityTryPersistence.update(learningActivityTry);
		
		learningActivityResultLocalService.updateLearningActivityResult(learningActivityTry, serviceContext);

		//auditing
		AuditFactory.audit(serviceContext.getCompanyId(), serviceContext.getScopeGroupId(), LMSAuditConstants.LEARNING_ACTIVITY_TRY_ADD, 
				LearningActivityTry.class.getName(), learningActivityTry.getPrimaryKey(), user.getUserId(), 
				user.getFullName(), null);
		
		return learningActivityTry;
	}
	
	public LearningActivityTry updateLearningActivityTry(LearningActivityTry learningActivityTry, double result, ServiceContext serviceContext) throws PortalException {
		
		learningActivityTry.setResult(result);
		
		//Actualizamos los campos de auditoria
		User user = userLocalService.getUser(serviceContext.getUserId());
		Date now = new Date();
		learningActivityTry.setUserModifiedId(serviceContext.getUserId());
		learningActivityTry.setUserModifiedName(user.getFullName());
		learningActivityTry.setModifiedDate(now);
		
		learningActivityTry = learningActivityTryPersistence.update(learningActivityTry);
		
		learningActivityResultLocalService.updateLearningActivityResult(learningActivityTry, serviceContext);
		
		//auditing
		AuditFactory.audit(serviceContext.getCompanyId(), serviceContext.getScopeGroupId(), LMSAuditConstants.LEARNING_ACTIVITY_TRY_UPDATE, 
				LearningActivityTry.class.getName(), learningActivityTry.getPrimaryKey(), user.getUserId(), 
				user.getFullName(), null);
		
		return learningActivityTry;
	}
	
	public LearningActivityTry finishLearningActivityTry(LearningActivityTry learningActivityTry, double result, ServiceContext serviceContext) throws PortalException {
		
		Date now = new Date();
		learningActivityTry.setResult(result);
		learningActivityTry.setEndDate(now);
		
		//Actualizamos los campos de auditoria
		User user = userLocalService.getUser(serviceContext.getUserId());
		learningActivityTry.setUserModifiedId(serviceContext.getUserId());
		learningActivityTry.setUserModifiedName(user.getFullName());
		learningActivityTry.setModifiedDate(now);
		
		learningActivityTry = learningActivityTryPersistence.update(learningActivityTry);
		
		learningActivityResultLocalService.updateLearningActivityResult(learningActivityTry, serviceContext);
		
		//auditing
		AuditFactory.audit(serviceContext.getCompanyId(), serviceContext.getScopeGroupId(), LMSAuditConstants.LEARNING_ACTIVITY_TRY_FINISH, 
				LearningActivityTry.class.getName(), learningActivityTry.getPrimaryKey(), user.getUserId(), 
				user.getFullName(), null);
		
		return learningActivityTry;
	}
}