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

package com.ted.lms.web.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Sergio González
 */
@ExtendedObjectClassDefinition(
	category = "course",
	scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE
)
@Meta.OCD(
	id = "com.ted.lms.internal.configuration.MyCoursesPortletInstanceConfiguration",
	localization = "content/Language",
	name = "my-courses-portlet-instance-configuration-name"
)
public interface MyCoursesPortletInstanceConfiguration {
	
	@Meta.AD(deflt = "false", name = "my-courses.config.view-group-courses", required = false)
	public boolean onlyGroup();
	
	@Meta.AD(deflt = "0", name = "my-courses.config.course-type", required = false)
	public boolean courseType();
	
	@Meta.AD(deflt = "true", name = "my-courses.courses.in-progress", required = false)
	public boolean coursesInProgress();
	
	@Meta.AD(deflt = "false", name = "my-courses.courses.completed", required = false)
	public boolean coursesCompleted();
	
	@Meta.AD(deflt = "false", name = "my-courses.courses.expired", required = false)
	public boolean coursesExpired();
	
	@Meta.AD(deflt = "1", name = "my-courses.config.show-courses", required = false)
	public int showCourses();

	@Meta.AD(deflt = "ddmTemplate_MY-COURSES-BASIC-FTL", name = "display-style", required = false)
	public String displayStyle();

	@Meta.AD(deflt = "0", name = "display-style-group-id", required = false)
	public long displayStyleGroupId();

	@Meta.AD(
		deflt = "${server-property://com.liferay.portal/search.container.page.default.delta}",
		name = "page-delta", required = false
	)
	public String pageDelta();

}