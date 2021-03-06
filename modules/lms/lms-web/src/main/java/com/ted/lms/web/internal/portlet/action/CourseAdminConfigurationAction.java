package com.ted.lms.web.internal.portlet.action;

import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.ted.lms.constants.LMSPortletKeys;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true,
	property = "javax.portlet.name=" + LMSPortletKeys.COURSE,
	service = ConfigurationAction.class
)
public class CourseAdminConfigurationAction extends DefaultConfigurationAction {
	@Override
	public String getJspPath(HttpServletRequest httpServletRequest) {
		return "/course_admin/configuration.jsp";
	}


	@Override
	@Reference(
		target = "(osgi.web.symbolicname=com.ted.lms.web)",
		unbind = "-"
	)
	public void setServletContext(ServletContext servletContext) {
		super.setServletContext(servletContext);
	}

}
