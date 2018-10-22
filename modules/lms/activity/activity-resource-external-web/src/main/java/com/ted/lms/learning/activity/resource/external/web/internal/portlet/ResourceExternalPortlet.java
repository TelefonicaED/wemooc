package com.ted.lms.learning.activity.resource.external.web.internal.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.ted.lms.learning.activity.resource.external.web.constants.ResourceExternalPortletKeys;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author JE10436
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + ResourceExternalPortletKeys.RESOURCE_EXTERNAL,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class ResourceExternalPortlet extends MVCPortlet {
}