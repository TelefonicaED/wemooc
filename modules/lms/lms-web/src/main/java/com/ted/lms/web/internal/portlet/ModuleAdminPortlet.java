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
		"javax.portlet.init-param.template-path=/html/portlet/moduleadmin",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + LMSPortletKeys.MODULE,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=user"
	},
	service = Portlet.class
)
public class ModuleAdminPortlet extends MVCPortlet {
}