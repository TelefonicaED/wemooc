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

package com.ted.lms.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ModuleService}.
 *
 * @author Brian Wing Shun Chan
 * @see ModuleService
 * @generated
 */
@ProviderType
public class ModuleServiceWrapper implements ModuleService,
	ServiceWrapper<ModuleService> {
	public ModuleServiceWrapper(ModuleService moduleService) {
		_moduleService = moduleService;
	}

	@Override
	public com.ted.lms.model.Module addModule(long groupId,
		java.util.Map<java.util.Locale, String> titleMap,
		java.util.Map<java.util.Locale, String> descriptionMap,
		boolean startDate, int startDateMonth, int startDateDay,
		int startDateYear, int startDateHour, int startDateMinute,
		boolean endDate, int endDateMonth, int endDateDay, int endDateYear,
		int endDateHour, int endDateMinute, int allowedHours,
		int allowedMinutes,
		com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector smallImageImageSelector,
		long moduleEvalId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _moduleService.addModule(groupId, titleMap, descriptionMap,
			startDate, startDateMonth, startDateDay, startDateYear,
			startDateHour, startDateMinute, endDate, endDateMonth, endDateDay,
			endDateYear, endDateHour, endDateMinute, allowedHours,
			allowedMinutes, smallImageImageSelector, moduleEvalId,
			serviceContext);
	}

	@Override
	public void deleteModule(long moduleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_moduleService.deleteModule(moduleId);
	}

	@Override
	public java.util.List<com.ted.lms.model.Module> getGroupModules(
		long groupId) {
		return _moduleService.getGroupModules(groupId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _moduleService.getOSGiServiceIdentifier();
	}

	@Override
	public com.ted.lms.model.Module moveDownModule(long moduleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _moduleService.moveDownModule(moduleId);
	}

	@Override
	public com.ted.lms.model.Module moveModuleToTrash(long moduleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _moduleService.moveModuleToTrash(moduleId);
	}

	@Override
	public com.ted.lms.model.Module moveUpModule(long moduleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _moduleService.moveUpModule(moduleId);
	}

	@Override
	public com.ted.lms.model.Module updateModule(long moduleId,
		java.util.Map<java.util.Locale, String> titleMap,
		java.util.Map<java.util.Locale, String> descriptionMap,
		boolean startDate, int startDateMonth, int startDateDay,
		int startDateYear, int startDateHour, int startDateMinute,
		boolean endDate, int endDateMonth, int endDateDay, int endDateYear,
		int endDateHour, int endDateMinute, int allowedHours,
		int allowedMinutes,
		com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector smallImageImageSelector,
		long moduleEvalId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _moduleService.updateModule(moduleId, titleMap, descriptionMap,
			startDate, startDateMonth, startDateDay, startDateYear,
			startDateHour, startDateMinute, endDate, endDateMonth, endDateDay,
			endDateYear, endDateHour, endDateMinute, allowedHours,
			allowedMinutes, smallImageImageSelector, moduleEvalId,
			serviceContext);
	}

	@Override
	public com.ted.lms.model.Module updateModule(
		com.ted.lms.model.Module module)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _moduleService.updateModule(module);
	}

	@Override
	public ModuleService getWrappedService() {
		return _moduleService;
	}

	@Override
	public void setWrappedService(ModuleService moduleService) {
		_moduleService = moduleService;
	}

	private ModuleService _moduleService;
}