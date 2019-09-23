package com.ted.lms.question.options.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.ted.lms.question.options.constants.OptionsWebPortletKeys;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author JE10436
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.name=" + OptionsWebPortletKeys.OPTIONS_PORTLET,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=user",
		"com.liferay.portlet.add-default-resource=true"
	},
	service = Portlet.class
)
public class OptionsPortlet extends MVCPortlet {
}