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
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermissionFactory;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector;
import com.ted.lms.constants.LMSActionKeys;
import com.ted.lms.constants.LMSConstants;
import com.ted.lms.model.Module;
import com.ted.lms.service.base.ModuleServiceBaseImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * The implementation of the module remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ted.lms.service.ModuleService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ModuleServiceBaseImpl
 * @see com.ted.lms.service.ModuleServiceUtil
 */
public class ModuleServiceImpl extends ModuleServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.ted.lms.service.ModuleServiceUtil} to access the module remote service.
	 */
	
	private static volatile ModelResourcePermission<Module>
		moduleModelResourcePermission =
	    ModelResourcePermissionFactory.getInstance(
	        ModuleServiceImpl.class,
	        "moduleModelResourcePermission", Module.class);
	
	private static volatile PortletResourcePermission portletResourcePermission =
        PortletResourcePermissionFactory.getInstance(
            ModuleServiceImpl.class, "portletResourcePermission",
            LMSConstants.RESOURCE_NAME);
	
	@Override
	public Module addModule(Map<Locale,String> titleMap, Map<Locale,String> descriptionMap, boolean startDate, int startDateMonth, 
			int startDateDay, int startDateYear, int startDateHour, int startDateMinute, boolean endDate, int endDateMonth, int endDateDay,
			int endDateYear, int endDateHour, int endDateMinute, int allowedHours, int allowedMinutes, ImageSelector smallImageImageSelector, 
			long moduleEvalId, ServiceContext serviceContext) throws PortalException {

		portletResourcePermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			LMSActionKeys.ADD_MODULE);

		return moduleLocalService.addModule(
			getUserId(), titleMap, descriptionMap, startDate, startDateMonth, startDateDay, startDateYear,
			startDateHour, startDateMinute, endDate, endDateMonth, endDateDay, endDateYear, endDateHour, endDateMinute,
			allowedHours, allowedMinutes, smallImageImageSelector, moduleEvalId, serviceContext);
	}
	
	@Override
	public Module updateModule(long moduleId, Map<Locale,String> titleMap, Map<Locale,String> descriptionMap, boolean startDate, int startDateMonth, 
			int startDateDay, int startDateYear, int startDateHour, int startDateMinute, boolean endDate, int endDateMonth, int endDateDay,
			int endDateYear, int endDateHour, int endDateMinute, int allowedHours, int allowedMinutes, ImageSelector smallImageImageSelector, 
			long moduleEvalId, ServiceContext serviceContext)  throws PortalException {

		moduleModelResourcePermission.check(getPermissionChecker(), moduleId, ActionKeys.UPDATE);

		return moduleLocalService.updateModule(getUserId(), moduleId, titleMap, descriptionMap, startDate, startDateMonth, startDateDay, startDateYear,
			startDateHour, startDateMinute, endDate, endDateMonth, endDateDay, endDateYear, endDateHour, endDateMinute,
			allowedHours, allowedMinutes, smallImageImageSelector, moduleEvalId, null, serviceContext);
	}
	
	@Override
	public Module updateModule(Module module) throws PortalException {

		moduleModelResourcePermission.check(getPermissionChecker(), module.getModuleId(), ActionKeys.UPDATE);

		return moduleLocalService.updateModule(module);
	}
	
	@Override
	public Module moveDownModule(long moduleId , ServiceContext serviceContext) throws PortalException{
		moduleModelResourcePermission.check(getPermissionChecker(), moduleId, ActionKeys.UPDATE);
		
		return moduleLocalService.moveDownModule(moduleId, serviceContext);
	}
	
	@Override
	public Module moveUpModule(long moduleId , ServiceContext serviceContext) throws PortalException{
		moduleModelResourcePermission.check(getPermissionChecker(), moduleId, ActionKeys.UPDATE);
		
		return moduleLocalService.moveUpModule(moduleId, serviceContext);
	}
	
	@Override
	public Module moveModuleToTrash(long moduleId) throws PortalException {
		moduleModelResourcePermission.check(
			getPermissionChecker(), moduleId, ActionKeys.DELETE);

		return moduleLocalService.moveModuleToTrash(getUserId(), moduleId);
	}
	
	@Override
	public void deleteModule(long moduleId) throws PortalException {
		moduleModelResourcePermission.check(getPermissionChecker(), moduleId, ActionKeys.DELETE);

		moduleLocalService.deleteModule(moduleId);
	}
	
	@Override
	public List<Module> getGroupModules(long groupId){
		List<Module> modules = new ArrayList<Module>();
		
		List<Module> listModules = moduleLocalService.findAllInGroup(groupId);
		for(Module module: listModules) {
			try {
				if(moduleModelResourcePermission.contains(getPermissionChecker(), module, ActionKeys.VIEW)) {
					modules.add(module);
				}
			} catch (PortalException e) {
				e.printStackTrace();
			}
		}
		
		return modules;
	}

}