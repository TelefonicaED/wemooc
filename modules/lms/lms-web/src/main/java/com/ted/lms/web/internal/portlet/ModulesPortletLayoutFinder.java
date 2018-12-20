package com.ted.lms.web.internal.portlet;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portlet.BasePortletLayoutFinder;
import com.liferay.portal.kernel.portlet.PortletLayoutFinder;
import com.ted.lms.constants.LMSPortletKeys;

@Component(
	immediate = true,
	property = "model.class.name=com.ted.lms.model.Module",
	service = PortletLayoutFinder.class
)
public class ModulesPortletLayoutFinder extends BasePortletLayoutFinder {
	
	@Override
	protected String[] getPortletIds() {
		return PORTLET_IDS;
	}

	private static final String[] PORTLET_IDS = {LMSPortletKeys.COURSE_CONTENT_VIEWER};
}
