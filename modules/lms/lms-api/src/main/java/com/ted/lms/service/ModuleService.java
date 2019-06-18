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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import com.ted.lms.model.Module;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Provides the remote service interface for Module. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see ModuleServiceUtil
 * @see com.ted.lms.service.base.ModuleServiceBaseImpl
 * @see com.ted.lms.service.impl.ModuleServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@OSGiBeanProperties(property =  {
	"json.web.service.context.name=lms", "json.web.service.context.path=Module"}, service = ModuleService.class)
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface ModuleService extends BaseService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ModuleServiceUtil} to access the module remote service. Add custom service methods to {@link com.ted.lms.service.impl.ModuleServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public Module addModule(long groupId, Map<Locale, String> titleMap,
		Map<Locale, String> descriptionMap, boolean startDate,
		int startDateMonth, int startDateDay, int startDateYear,
		int startDateHour, int startDateMinute, boolean endDate,
		int endDateMonth, int endDateDay, int endDateYear, int endDateHour,
		int endDateMinute, int allowedHours, int allowedMinutes,
		ImageSelector smallImageImageSelector, long moduleEvalId,
		ServiceContext serviceContext) throws PortalException;

	public void deleteModule(long moduleId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Module> getGroupModules(long groupId);

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public String getOSGiServiceIdentifier();

	public Module moveDownModule(long moduleId) throws PortalException;

	public Module moveModuleToTrash(long moduleId) throws PortalException;

	public Module moveUpModule(long moduleId) throws PortalException;

	public Module updateModule(long moduleId, Map<Locale, String> titleMap,
		Map<Locale, String> descriptionMap, boolean startDate,
		int startDateMonth, int startDateDay, int startDateYear,
		int startDateHour, int startDateMinute, boolean endDate,
		int endDateMonth, int endDateDay, int endDateYear, int endDateHour,
		int endDateMinute, int allowedHours, int allowedMinutes,
		ImageSelector smallImageImageSelector, long moduleEvalId,
		ServiceContext serviceContext) throws PortalException;

	public Module updateModule(Module module) throws PortalException;
}