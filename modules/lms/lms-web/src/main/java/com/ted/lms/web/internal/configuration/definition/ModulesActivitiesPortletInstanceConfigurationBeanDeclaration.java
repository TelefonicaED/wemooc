package com.ted.lms.web.internal.configuration.definition;

import com.liferay.portal.kernel.settings.definition.ConfigurationBeanDeclaration;
import com.ted.lms.web.internal.configuration.ModulesActivitiesPortletInstanceConfiguration;

import org.osgi.service.component.annotations.Component;

@Component(service=ConfigurationBeanDeclaration.class)
public class ModulesActivitiesPortletInstanceConfigurationBeanDeclaration 
	implements ConfigurationBeanDeclaration {
	
	@Override
	public Class<?> getConfigurationBeanClass() {
		return ModulesActivitiesPortletInstanceConfiguration.class;
	}
}
