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

package com.ted.lms.service.persistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public interface CourseResultFinder {

	public java.util.List<com.ted.lms.model.CourseResult> filterByU_G(
		long userId, boolean inProgress, boolean completed, boolean expired,
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc,
		boolean inlineSQLHelper);

	public int doCountByU_G(
		long userId, boolean inProgress, boolean completed, boolean expired,
		long groupId, boolean inlineSQLHelper);

	public boolean hasUserTries(long courseId, long userId);

	public java.util.List<com.ted.lms.model.CourseResult>
		getCourseResultNotFinished(long courseId);

}