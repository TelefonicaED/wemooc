package com.ted.lms.internal.configuration;

import com.liferay.portal.configuration.persistence.listener.ConfigurationModelListener;
import com.liferay.portal.servlet.filters.cache.CacheUtil;

import java.util.Dictionary;

import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true,
	property = "model.class.name=com.ted.lms.configuration.CourseServiceConfiguration",
	service = ConfigurationModelListener.class
)
public class CourseServiceConfigurationListener implements ConfigurationModelListener {
	@Override
	public void onAfterSave(String pid, Dictionary<String, Object> properties) {
		CacheUtil.clearCache();
	}
}
