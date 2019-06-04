package com.ted.lms.web.internal.portlet;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.ted.lms.constants.LMSPortletKeys;

import javax.portlet.Portlet;

@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.wemooc",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.name=" + LMSPortletKeys.COURSE_CONTENT_VIEWER,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=user",
		"javax.portlet.supported-public-render-parameter=actId",
		"javax.portlet.supported-public-render-parameter=moduleId"
	},
	service = Portlet.class
)
public class CourseContentViewerPortlet extends MVCPortlet {
	
}
