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
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.name=" + LMSPortletKeys.CALIFICATION,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=guest,user",
		"com.liferay.portlet.use-default-template=true"
	},
	service = Portlet.class
)
public class CalificationPortlet extends MVCPortlet {
	
	

}