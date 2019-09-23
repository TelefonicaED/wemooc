package com.ted.lms.web.internal.configuration.definition;

import com.liferay.portal.kernel.settings.definition.ConfigurationBeanDeclaration;
import com.ted.lms.web.internal.configuration.LMSWebConfiguration;

import org.osgi.service.component.annotations.Component;


@Component
public class LMSWebConfigurationBeanDeclaration implements ConfigurationBeanDeclaration {
	
	@Override
	public Class<?> getConfigurationBeanClass() {
		return LMSWebConfiguration.class;
	}

}
