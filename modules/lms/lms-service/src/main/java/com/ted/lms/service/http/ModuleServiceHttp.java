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

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

import com.ted.lms.service.ModuleServiceUtil;

/**
 * Provides the HTTP utility for the
 * {@link ModuleServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * {@link HttpPrincipal} parameter.
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ModuleServiceSoap
 * @see HttpPrincipal
 * @see ModuleServiceUtil
 * @generated
 */
@ProviderType
public class ModuleServiceHttp {
	public static com.ted.lms.model.Module addModule(
		HttpPrincipal httpPrincipal,
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
		try {
			MethodKey methodKey = new MethodKey(ModuleServiceUtil.class,
					"addModule", _addModuleParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					titleMap, descriptionMap, startDate, startDateMonth,
					startDateDay, startDateYear, startDateHour,
					startDateMinute, endDate, endDateMonth, endDateDay,
					endDateYear, endDateHour, endDateMinute, allowedHours,
					allowedMinutes, smallImageImageSelector, moduleEvalId,
					serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.ted.lms.model.Module)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.ted.lms.model.Module updateModule(
		HttpPrincipal httpPrincipal, long moduleId,
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
		try {
			MethodKey methodKey = new MethodKey(ModuleServiceUtil.class,
					"updateModule", _updateModuleParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					moduleId, titleMap, descriptionMap, startDate,
					startDateMonth, startDateDay, startDateYear, startDateHour,
					startDateMinute, endDate, endDateMonth, endDateDay,
					endDateYear, endDateHour, endDateMinute, allowedHours,
					allowedMinutes, smallImageImageSelector, moduleEvalId,
					serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.ted.lms.model.Module)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.ted.lms.model.Module moveModuleToTrash(
		HttpPrincipal httpPrincipal, long moduleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(ModuleServiceUtil.class,
					"moveModuleToTrash", _moveModuleToTrashParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey, moduleId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.ted.lms.model.Module)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void deleteModule(HttpPrincipal httpPrincipal, long moduleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(ModuleServiceUtil.class,
					"deleteModule", _deleteModuleParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey, moduleId);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(ModuleServiceHttp.class);
	private static final Class<?>[] _addModuleParameterTypes0 = new Class[] {
			java.util.Map.class, java.util.Map.class, boolean.class, int.class,
			int.class, int.class, int.class, int.class, boolean.class, int.class,
			int.class, int.class, int.class, int.class, int.class, int.class,
			com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector.class,
			long.class, com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _updateModuleParameterTypes1 = new Class[] {
			long.class, java.util.Map.class, java.util.Map.class, boolean.class,
			int.class, int.class, int.class, int.class, int.class, boolean.class,
			int.class, int.class, int.class, int.class, int.class, int.class,
			int.class,
			com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector.class,
			long.class, com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _moveModuleToTrashParameterTypes2 = new Class[] {
			long.class
		};
	private static final Class<?>[] _deleteModuleParameterTypes3 = new Class[] {
			long.class
		};
}