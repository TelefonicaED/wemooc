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
	public long[] courseTemplates();
	
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
}