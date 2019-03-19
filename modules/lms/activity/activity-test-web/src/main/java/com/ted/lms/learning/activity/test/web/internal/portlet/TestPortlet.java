package com.ted.lms.learning.activity.test.web.internal.portlet;

import com.ted.lms.learning.activity.test.web.constants.TestPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

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
		"javax.portlet.init-param.mvc-command-names-default-views=/activities/test/view_activity",
		"javax.portlet.name=" + TestPortletKeys.TEST,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=user",
		"javax.portlet.supported-public-render-parameter=actId",
		"com.liferay.portlet.add-default-resource=true"
	},
	service = Portlet.class
)
public class TestPortlet extends MVCPortlet {
	
}