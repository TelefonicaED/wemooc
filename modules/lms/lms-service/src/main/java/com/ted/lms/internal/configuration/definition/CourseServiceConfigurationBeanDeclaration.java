package com.ted.lms.internal.configuration.definition;

import com.liferay.portal.kernel.settings.definition.ConfigurationBeanDeclaration;
import com.ted.lms.configuration.CourseServiceConfiguration;

import org.osgi.service.component.annotations.Component;


@Component
public class CourseServiceConfigurationBeanDeclaration implements ConfigurationBeanDeclaration {
	@Override
	public Class<?> getConfigurationBeanClass() {
		return CourseServiceConfiguration.class;
	}

}
