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

package com.ted.lms.learning.activity.p2p.service.impl;

import com.ted.lms.learning.activity.p2p.model.P2PActivity;
import com.ted.lms.learning.activity.p2p.service.base.P2PActivityLocalServiceBaseImpl;

/**
 * The implementation of the p2p activity local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ted.lms.learning.activity.p2p.service.P2PActivityLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see P2PActivityLocalServiceBaseImpl
 * @see com.ted.lms.learning.activity.p2p.service.P2PActivityLocalServiceUtil
 */
public class P2PActivityLocalServiceImpl extends P2PActivityLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.ted.lms.learning.activity.p2p.service.P2PActivityLocalServiceUtil} to access the p2p activity local service.
	 */
	
	@Override
	public boolean hasP2PActivity(long actId) {
		return p2pActivityPersistence.countByActId(actId) > 0;
	}
	
	@Override
	public P2PActivity getP2PActivity(long actId, long userId) {
		return p2pActivityPersistence.fetchByActIdUserId(actId, userId);
	}
}