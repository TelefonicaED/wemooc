package com.ted.lms.web.internal.servlet.taglib.ui;

import com.liferay.portal.kernel.servlet.taglib.ui.FormNavigatorEntry;

import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = "form.navigator.entry.order:Integer=90",
	service = FormNavigatorEntry.class
)
public class ModuleContentFormNavigatorEntry extends BaseModuleFormNavigatorEntry{

	@Override
	public String getKey() {
		return "content";
	}
	
	@Override
	@Reference(
		target = "(osgi.web.symbolicname=com.ted.lms.web)", unbind = "-"
	)
	public void setServletContext(ServletContext servletContext) {
		super.setServletContext(servletContext);
	}

	@Override
	protected String getJspPath() {
		return "/module/content.jsp";
	}
}
