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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for Module. This utility wraps
 * {@link com.ted.lms.service.impl.ModuleServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see ModuleService
 * @see com.ted.lms.service.base.ModuleServiceBaseImpl
 * @see com.ted.lms.service.impl.ModuleServiceImpl
 * @generated
 */
@ProviderType
public class ModuleServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.ted.lms.service.impl.ModuleServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static ModuleService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ModuleService, ModuleService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ModuleService.class);

		ServiceTracker<ModuleService, ModuleService> serviceTracker = new ServiceTracker<ModuleService, ModuleService>(bundle.getBundleContext(),
				ModuleService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}