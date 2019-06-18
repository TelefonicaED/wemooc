package com.ted.lms.web.internal.application.list;

import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.PanelApp;
import com.liferay.portal.kernel.model.Portlet;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.navigation.control.panel.internal.application.list.constants.LMSPanelCategoryKeys;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true,
	property = {
		"panel.app.order:Integer=100",
		"panel.category.key=" + LMSPanelCategoryKeys.CONTROL_PANEL_LMS
	},
	service = PanelApp.class
)
public class CourseTypePanelApp extends BasePanelApp {

	@Override
	public String getPortletId() {
		return LMSPortletKeys.COURSE_TYPE;
	}

	@Override
	@Reference(
		target = "(javax.portlet.name=" + LMSPortletKeys.COURSE_TYPE + ")",
		unbind = "-"
	)
	public void setPortlet(Portlet portlet) {
		super.setPortlet(portlet);
	}
}
