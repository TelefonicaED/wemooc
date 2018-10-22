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

import com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections;
import com.ted.lms.learning.activity.p2p.service.base.P2PActivityCorrectionsLocalServiceBaseImpl;

import java.util.List;

/**
 * The implementation of the p2p activity corrections local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ted.lms.learning.activity.p2p.service.P2PActivityCorrectionsLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see P2PActivityCorrectionsLocalServiceBaseImpl
 * @see com.ted.lms.learning.activity.p2p.service.P2PActivityCorrectionsLocalServiceUtil
 */
public class P2PActivityCorrectionsLocalServiceImpl
	extends P2PActivityCorrectionsLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.ted.lms.learning.activity.p2p.service.P2PActivityCorrectionsLocalServiceUtil} to access the p2p activity corrections local service.
	 */
	
	@Override
	public List<P2PActivityCorrections> getCorrectionsDoneByUser(long actId, long userId) {
		return p2pActivityCorrectionsPersistence.findByActIdUserIdDateNotNull(actId, userId);
	}
	
	@Override
	public List<P2PActivityCorrections> getCorrectionsDoneByP2PActivityId(long p2pActivityId){
		return p2pActivityCorrectionsPersistence.findByP2PActivityIdDateNotNull(p2pActivityId);
	}
}