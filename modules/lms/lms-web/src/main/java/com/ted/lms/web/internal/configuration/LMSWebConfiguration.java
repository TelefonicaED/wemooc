package com.ted.lms.web.internal.configuration;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;
import aQute.bnd.annotation.metatype.Meta.OCD;

@ExtendedObjectClassDefinition(
	category = "lms",
	scope = ExtendedObjectClassDefinition.Scope.COMPANY
)
@OCD(
	id = "com.ted.lms.configuration.LMSWebConfiguration",
	localization = "content/Language",
	name = "lms-web-configuration-name"
)
public @interface LMSWebConfiguration {
	
	@Meta.AD(
		deflt = "${server-property://com.liferay.portal/editor.wysiwyg.lms-web.docroot.html.portlet.courses.edit_course.jsp}",
		name = "html-course-editor", required = false
	)
	public String getHTMLCourseEditor();
	
	@Meta.AD(
		deflt = "${server-property://com.liferay.portal/editor.wysiwyg.lms-web.docroot.html.portlet.modules.edit_module.jsp}",
		name = "html-module-editor", required = false
	)
	public String getHTMLModuleEditor();
	
	@Meta.AD(
		deflt = "${server-property://com.liferay.portal/editor.wysiwyg.lms-web.docroot.html.portlet.activities.edit_activity.jsp}",
		name = "html-activity-editor", required = false
	)
	public String getHTMLActivityEditor();
	
}
