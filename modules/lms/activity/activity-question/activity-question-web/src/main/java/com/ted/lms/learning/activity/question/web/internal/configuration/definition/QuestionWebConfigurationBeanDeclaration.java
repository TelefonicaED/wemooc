package com.ted.lms.learning.activity.question.web.internal.configuration.definition;

import com.liferay.portal.kernel.settings.definition.ConfigurationBeanDeclaration;
import com.ted.lms.learning.activity.question.web.internal.configuration.QuestionWebConfiguration;

import org.osgi.service.component.annotations.Component;


@Component
public class QuestionWebConfigurationBeanDeclaration implements ConfigurationBeanDeclaration {
	
	@Override
	public Class<?> getConfigurationBeanClass() {
		return QuestionWebConfiguration.class;
	}

}
