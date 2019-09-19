package com.ted.lms.web.internal.configuration;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

@ExtendedObjectClassDefinition(
	category = "lms",
	scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE
)
@Meta.OCD(
	id = "com.ted.lms.web.internal.configuration.ModulesActivitiesPortletInstanceConfiguration",
	localization = "content/Language",
	name = "modules-activites-instance-configuration-name"
)
public interface ModulesActivitiesPortletInstanceConfiguration {
	@Meta.AD(
		name = "show-actions", required = false, deflt = "true"
	)
	public boolean showActions();
	
}
