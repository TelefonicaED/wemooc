package com.ted.lms.web.internal.portlet;

import com.ted.lms.constants.LMSPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import javax.portlet.Portlet;
import org.osgi.service.component.annotations.Component;

/**
 * @author Virginia Mart�n Agudo
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.wemooc",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.mvc-command-names-default-views=/results/edit_course_results",
		"javax.portlet.name=" + LMSPortletKeys.EDIT_COURSE_RESULTS,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=user"
	},
	service = Portlet.class
)
public class EditResultsPortlet extends MVCPortlet {
	
}