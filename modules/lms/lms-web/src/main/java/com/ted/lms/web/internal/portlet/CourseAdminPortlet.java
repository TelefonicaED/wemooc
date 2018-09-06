package com.ted.lms.web.internal.portlet;

import com.ted.lms.constants.LMSPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author Virginia Martín Agudo
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=wemooc",
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