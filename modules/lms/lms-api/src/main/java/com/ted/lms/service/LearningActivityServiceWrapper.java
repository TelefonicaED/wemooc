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
 * Provides a wrapper for {@link LearningActivityService}.
 *
 * @author Brian Wing Shun Chan
 * @see LearningActivityService
 * @generated
 */
@ProviderType
public class LearningActivityServiceWrapper implements LearningActivityService,
	ServiceWrapper<LearningActivityService> {
	public LearningActivityServiceWrapper(
		LearningActivityService learningActivityService) {
		_learningActivityService = learningActivityService;
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _learningActivityService.getOSGiServiceIdentifier();
	}

	@Override
	public LearningActivityService getWrappedService() {
		return _learningActivityService;
	}

	@Override
	public void setWrappedService(
		LearningActivityService learningActivityService) {
		_learningActivityService = learningActivityService;
	}

	private LearningActivityService _learningActivityService;
}