package com.ted.lms.web.internal.portlet;

import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.web.internal.configuration.CourseAdminPortletInstanceConfiguration;

import java.util.Map;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

/**
 * @author Virginia Martín Agudo
 */
@Component(
	configurationPid = "com.ted.lms.web.internal.configuration.CourseAdminPortletInstanceConfiguration",
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.wemooc",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.mvc-command-names-default-views=/courses/view",
		"javax.portlet.name=" + LMSPortletKeys.COURSE,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=user"
	},
	service = Portlet.class
)
public class CourseAdminPortlet extends MVCPortlet {
}