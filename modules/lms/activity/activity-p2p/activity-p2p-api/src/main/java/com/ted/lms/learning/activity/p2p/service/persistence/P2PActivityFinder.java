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

package com.ted.lms.learning.activity.p2p.service.persistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public interface P2PActivityFinder {

	public java.util.List<com.ted.lms.learning.activity.p2p.model.P2PActivity>
		findByGroup(long actId, long p2pActId, int start, int end);

	public java.util.List<com.ted.lms.learning.activity.p2p.model.P2PActivity>
		findByTeam(
			long actId, long p2pActId,
			java.util.List<com.liferay.portal.kernel.model.Team> userTeams,
			int start, int end);

	public java.util.List<com.ted.lms.learning.activity.p2p.model.P2PActivity>
		findByUserWithoutTeamActivities(
			long actId, long p2pActId, long groupId, int start, int end);

}