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

import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.service.base.LearningActivityServiceBaseImpl;

/**
 * The implementation of the learning activity remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ted.lms.service.LearningActivityService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LearningActivityServiceBaseImpl
 * @see com.ted.lms.service.LearningActivityServiceUtil
 */
public class LearningActivityServiceImpl extends LearningActivityServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.ted.lms.service.LearningActivityServiceUtil} to access the learning activity remote service.
	 */
	
	private static volatile ModelResourcePermission<LearningActivity>
		learningActivityModelResourcePermission =
	    ModelResourcePermissionFactory.getInstance(
	        LearningActivityServiceImpl.class,
	        "learningActivityModelResourcePermission", LearningActivity.class);
}