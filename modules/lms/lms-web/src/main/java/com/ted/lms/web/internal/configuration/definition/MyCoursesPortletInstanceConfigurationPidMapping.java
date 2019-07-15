package com.ted.lms.web.internal.configuration.definition;

import com.liferay.portal.kernel.settings.definition.ConfigurationPidMapping;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.web.internal.configuration.MyCoursesPortletInstanceConfiguration;

import org.osgi.service.component.annotations.Component;

@Component(service = ConfigurationPidMapping.class)
public class MyCoursesPortletInstanceConfigurationPidMapping
	implements ConfigurationPidMapping {

	@Override
	public Class<?> getConfigurationBeanClass() {
		return MyCoursesPortletInstanceConfiguration.class;
	}

	@Override
	public String getConfigurationPid() {
		return LMSPortletKeys.MY_COURSES;
	}

}