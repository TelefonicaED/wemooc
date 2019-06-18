package com.ted.lms.web.internal.configuration.activator;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.ted.lms.configuration.CourseServiceConfiguration;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;

@Component(
	configurationPid = "com.ted.lms.configuration.CourseServiceConfiguration",
	configurationPolicy = ConfigurationPolicy.OPTIONAL, immediate = true,
	service = CourseConfigurationActivator.class
)
public class CourseConfigurationActivator {

	public CourseServiceConfiguration getCourseServiceConfiguration() {
		return courseServiceConfiguration;
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		courseServiceConfiguration = ConfigurableUtil.createConfigurable(CourseServiceConfiguration.class, properties);
	}

	private volatile CourseServiceConfiguration courseServiceConfiguration;

}
