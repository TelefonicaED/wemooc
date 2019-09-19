package com.ted.lms.web.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

@ExtendedObjectClassDefinition(
	category = "lms",
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