package com.ted.lms.web.internal.configuration.definition;

import com.liferay.portal.kernel.settings.definition.ConfigurationPidMapping;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.web.internal.configuration.CourseAdminPortletInstanceConfiguration;

public class CourseAdminPortletInstanceConfigurationPidMapping implements ConfigurationPidMapping{
	@Override
	public Class<?> getConfigurationBeanClass() {
		return CourseAdminPortletInstanceConfiguration.class;
	}

	@Override
	public String getConfigurationPid() {
		return LMSPortletKeys.COURSE;
	}
}
