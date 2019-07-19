package com.ted.lms.configuration;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.ProviderType;
import aQute.bnd.annotation.metatype.Meta;

@ExtendedObjectClassDefinition(
	category = "course",
	scope = ExtendedObjectClassDefinition.Scope.COMPANY
)
@Meta.OCD(
	id = "com.ted.lms.configuration.CourseServiceConfiguration",
	localization = "content/Language",
	name = "course-service-configuration-name"
)
@ProviderType
public interface CourseServiceConfiguration {
	@Meta.AD(
		description = "specifcy-activities-that-are-allowed-in-courses",
		name = "activities", required = false
	)
	public String[] activities();
	
	@Meta.AD(
		description = "specifcy-templates-that-are-allowed-in-courses",
		name = "course-templates", required = false
	)
	public String[] courseTemplates();
	
	@Meta.AD(
		description = "specifcy-course-evaluations-that-are-allowed-in-courses",
		name = "course-evaluations", required = false
	)
	public String[] courseEvaluations();
	
	@Meta.AD(
		description = "specifcy-califications-that-are-allowed-in-courses",
		name = "califications", required = false
	)
	public String[] califications();
	
	@Meta.AD(
		description = "specifcy-inscriptions-that-are-allowed-in-courses",
		name = "inscriptions", required = false
	)
	public String[] inscriptions();
	
	@Meta.AD(
		description = "specifcy-show-activity-that-are-allowed-in-courses",
		name = "show-hide-activity", required = false
	)
	public boolean showHideActivity();
	
	@Meta.AD(
		description = "specifcy-allow-access-to-completed-courses-that-are-allowed-in-courses",
		name = "allow-access-to-completed-courses", required = false
	)
	public boolean allowAccessToCompletedCourses();
	
	@Meta.AD(
		description = "specifcy-prerequisite-activities",
		name = "prerequisite-activities", required = false
	)
	public String[] prerequisitesActivity();
	
	@Meta.AD(
		description = "specifcy-prerequisite-modules",
		name = "prerequisite-modules", required = false
	)
	public String[] prerequisitesModules();
	
	@Meta.AD(
		description = "specifcy-prerequisite-courses",
		name = "prerequisite-courses", required = false
	)
	public String[] prerequisitesCourses();
	
	@Meta.AD(
		description = "specifcy-postcondition-courses",
		name = "postcondition-courses", required = false
	)
	public String[] postconditionCourses();
	
	@Meta.AD(
		name = "edition-without-restrictions", required = false
	)
	public boolean editionWithoutRestrictions();
	
	@Meta.AD(
		description = "specifcy-role-courses",
		name = "role-courses", required = false
	)
	public String[] roleCourses();
	
	@Meta.AD(
		deflt = "com/ted/lms/web/template/dependencies/my-courses-portlet-display-templates.xml",
		name = "display-templates-my-courses-config", required = false
	)
	public String displayTemplatesMyCoursesConfig();
}
