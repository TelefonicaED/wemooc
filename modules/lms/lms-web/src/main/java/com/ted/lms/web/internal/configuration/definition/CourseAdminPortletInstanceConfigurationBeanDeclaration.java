package com.ted.lms.web.internal.configuration.definition;

import com.liferay.portal.kernel.settings.definition.ConfigurationBeanDeclaration;
import com.ted.lms.web.internal.configuration.CourseAdminPortletInstanceConfiguration;

import org.osgi.service.component.annotations.Component;

@Component
public class CourseAdminPortletInstanceConfigurationBeanDeclaration implements ConfigurationBeanDeclaration {
	@Override
	public Class<?> getConfigurationBeanClass() {
		return CourseAdminPortletInstanceConfiguration.class;
	}
}
