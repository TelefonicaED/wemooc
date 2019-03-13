package com.ted.lms.learning.activity.resource.internal.web.internal.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.ted.lms.learning.activity.resource.internal.web.constants.ResourceInternalPortletKeys;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author JE10436
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.mvc-command-names-default-views=/activities/resource_internal/view_activity",
		"javax.portlet.name=" + ResourceInternalPortletKeys.RESOURCE_INTERNAL,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=user",
		"javax.portlet.supported-public-render-parameter=actId",
		"com.liferay.portlet.add-default-resource=true"
	},
	service = Portlet.class
)
public class ResourceInternalPortlet extends MVCPortlet {
}