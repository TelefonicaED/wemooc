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
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.LearningActivityResult;
import com.ted.lms.model.Module;
import com.ted.lms.model.ModuleEval;
import com.ted.lms.model.ModuleEvalFactory;
import com.ted.lms.model.ModuleResult;
import com.ted.lms.registry.ModuleEvalFactoryRegistryUtil;
import com.ted.lms.service.base.ModuleResultLocalServiceBaseImpl;

import java.util.Date;
import java.util.List;

/**
 * The implementation of the module result local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ted.lms.service.ModuleResultLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ModuleResultLocalServiceBaseImpl
 * @see com.ted.lms.service.ModuleResultLocalServiceUtil
 */
public class ModuleResultLocalServiceImpl
	extends ModuleResultLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.ted.lms.service.ModuleResultLocalServiceUtil} to access the module result local service.
	 */
	
	public List<ModuleResult> getModuleResults(long moduleId){
		return moduleResultPersistence.findByModuleId(moduleId);
	}
	
	public int countModulesUserPassed(long groupId, long userId) {
		return moduleResultFinder.countModulesUserPassed(groupId, userId);
	}
	
	public ModuleResult getModuleResult(long moduleId, long userId) {
		return moduleResultPersistence.fetchByModuleIdUserId(moduleId, userId);
	}
	
	public ModuleResult addModuleResult(long moduleId, long userId, ServiceContext serviceContext) {
		ModuleResult moduleResult = moduleResultPersistence.create(counterLocalService.increment(ModuleResult.class.getName()));
		moduleResult.setModuleId(moduleId);
		moduleResult.setPassed(false);
		moduleResult.setUserId(userId);
		moduleResult.setStartDate(new Date());
		moduleResult.setResult(0);
		moduleResult.setCompanyId(serviceContext.getCompanyId());
		moduleResult.setCreateDate(new Date());
		moduleResult.setGroupId(serviceContext.getScopeGroupId());
		moduleResult.setModifiedDate(moduleResult.getCreateDate());
		moduleResult.setUserModifiedId(serviceContext.getUserId());
		User user = userLocalService.fetchUser(serviceContext.getUserId());
		if(user != null) {
			moduleResult.setUserModifiedName(user.getFullName());
		}
		return moduleResultPersistence.update(moduleResult);
	}
	
	public ModuleResult updateModuleResult(LearningActivityResult learningActivityResult, ServiceContext serviceContext) throws PortalException {
		ModuleResult moduleResult = null;
		
		LearningActivity learningActivity = learningActivityLocalService.getLearningActivity(learningActivityResult.getActId());
		
		Module module = modulePersistence.fetchByPrimaryKey(learningActivity.getModuleId());
		
		if(module!=null){
			
			Date now = new Date();
			
			moduleResult = moduleResultPersistence.fetchByModuleIdUserId(module.getModuleId(), learningActivityResult.getUserId());
			if(moduleResult == null) {
				moduleResult = moduleResultPersistence.create(counterLocalService.increment(ModuleResult.class.getName()));
				moduleResult.setGroupId(learningActivityResult.getGroupId());
				moduleResult.setCompanyId(learningActivityResult.getCompanyId());
				moduleResult.setCreateDate(now);
				moduleResult.setModuleId(module.getModuleId());
				moduleResult.setUserId(learningActivityResult.getUserId());
				moduleResult.setStartDate(learningActivityResult.getStartDate());
			}
			
			User user = userLocalService.getUser(serviceContext.getUserId());
			moduleResult.setUserModifiedId(user.getUserId());
			moduleResult.setUserModifiedName(user.getFullName());
			moduleResult.setModifiedDate(now);
			
			ModuleEvalFactory moduleEvalFactory = ModuleEvalFactoryRegistryUtil.getModuleEvalFactoryByType(module.getModuleEvalId());
			ModuleEval moduleEval = moduleEvalFactory.getModuleEval(module, serviceContext);
			moduleResult = moduleEval.updateModuleResult(moduleResult);
			
			//Update en la bd
			moduleResult = moduleResultPersistence.update(moduleResult);
			
			//Actualizar el resultado del curso.
			courseResultLocalService.updateCourseResult(moduleResult, serviceContext);
		}
		
		return moduleResult;
	}
	
}