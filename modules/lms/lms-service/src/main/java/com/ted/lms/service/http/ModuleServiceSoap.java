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

package com.ted.lms.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;

import com.ted.lms.service.ModuleServiceUtil;

import java.rmi.RemoteException;

import java.util.Locale;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the SOAP utility for the
 * <code>ModuleServiceUtil</code> service
 * utility. The static methods of this class call the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a <code>java.util.List</code>,
 * that is translated to an array of
 * <code>com.ted.lms.model.ModuleSoap</code>. If the method in the
 * service utility returns a
 * <code>com.ted.lms.model.Module</code>, that is translated to a
 * <code>com.ted.lms.model.ModuleSoap</code>. Methods that SOAP
 * cannot safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ModuleServiceHttp
 * @generated
 */
@ProviderType
public class ModuleServiceSoap {

	public static com.ted.lms.model.ModuleSoap addModule(
			long groupId, String[] titleMapLanguageIds, String[] titleMapValues,
			String[] descriptionMapLanguageIds, String[] descriptionMapValues,
			boolean startDate, int startDateMonth, int startDateDay,
			int startDateYear, int startDateHour, int startDateMinute,
			boolean endDate, int endDateMonth, int endDateDay, int endDateYear,
			int endDateHour, int endDateMinute, int allowedHours,
			int allowedMinutes,
			com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector
				smallImageImageSelector,
			long moduleEvalId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			Map<Locale, String> titleMap = LocalizationUtil.getLocalizationMap(
				titleMapLanguageIds, titleMapValues);
			Map<Locale, String> descriptionMap =
				LocalizationUtil.getLocalizationMap(
					descriptionMapLanguageIds, descriptionMapValues);

			com.ted.lms.model.Module returnValue = ModuleServiceUtil.addModule(
				groupId, titleMap, descriptionMap, startDate, startDateMonth,
				startDateDay, startDateYear, startDateHour, startDateMinute,
				endDate, endDateMonth, endDateDay, endDateYear, endDateHour,
				endDateMinute, allowedHours, allowedMinutes,
				smallImageImageSelector, moduleEvalId, serviceContext);

			return com.ted.lms.model.ModuleSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.ted.lms.model.ModuleSoap updateModule(
			long moduleId, String[] titleMapLanguageIds,
			String[] titleMapValues, String[] descriptionMapLanguageIds,
			String[] descriptionMapValues, boolean startDate,
			int startDateMonth, int startDateDay, int startDateYear,
			int startDateHour, int startDateMinute, boolean endDate,
			int endDateMonth, int endDateDay, int endDateYear, int endDateHour,
			int endDateMinute, int allowedHours, int allowedMinutes,
			com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector
				smallImageImageSelector,
			long moduleEvalId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			Map<Locale, String> titleMap = LocalizationUtil.getLocalizationMap(
				titleMapLanguageIds, titleMapValues);
			Map<Locale, String> descriptionMap =
				LocalizationUtil.getLocalizationMap(
					descriptionMapLanguageIds, descriptionMapValues);

			com.ted.lms.model.Module returnValue =
				ModuleServiceUtil.updateModule(
					moduleId, titleMap, descriptionMap, startDate,
					startDateMonth, startDateDay, startDateYear, startDateHour,
					startDateMinute, endDate, endDateMonth, endDateDay,
					endDateYear, endDateHour, endDateMinute, allowedHours,
					allowedMinutes, smallImageImageSelector, moduleEvalId,
					serviceContext);

			return com.ted.lms.model.ModuleSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.ted.lms.model.ModuleSoap updateModule(
			com.ted.lms.model.ModuleSoap module)
		throws RemoteException {

		try {
			com.ted.lms.model.Module returnValue =
				ModuleServiceUtil.updateModule(
					com.ted.lms.model.impl.ModuleModelImpl.toModel(module));

			return com.ted.lms.model.ModuleSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.ted.lms.model.ModuleSoap moveDownModule(long moduleId)
		throws RemoteException {

		try {
			com.ted.lms.model.Module returnValue =
				ModuleServiceUtil.moveDownModule(moduleId);

			return com.ted.lms.model.ModuleSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.ted.lms.model.ModuleSoap moveUpModule(long moduleId)
		throws RemoteException {

		try {
			com.ted.lms.model.Module returnValue =
				ModuleServiceUtil.moveUpModule(moduleId);

			return com.ted.lms.model.ModuleSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.ted.lms.model.ModuleSoap moveModuleToTrash(long moduleId)
		throws RemoteException {

		try {
			com.ted.lms.model.Module returnValue =
				ModuleServiceUtil.moveModuleToTrash(moduleId);

			return com.ted.lms.model.ModuleSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deleteModule(long moduleId) throws RemoteException {
		try {
			ModuleServiceUtil.deleteModule(moduleId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.ted.lms.model.ModuleSoap[] getGroupModules(long groupId)
		throws RemoteException {

		try {
			java.util.List<com.ted.lms.model.Module> returnValue =
				ModuleServiceUtil.getGroupModules(groupId);

			return com.ted.lms.model.ModuleSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(ModuleServiceSoap.class);

}