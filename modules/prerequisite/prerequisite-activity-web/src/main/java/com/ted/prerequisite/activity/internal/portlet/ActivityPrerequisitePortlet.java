package com.ted.prerequisite.activity.internal.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.ted.prerequisite.activity.internal.constants.ActivityPrerequisitePortletKeys;

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
		"javax.portlet.init-param.view-template=/edit.jsp",
		"javax.portlet.name=" + ActivityPrerequisitePortletKeys.ACTIVITY,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=user"
	},
	service = Portlet.class
)
public class ActivityPrerequisitePortlet extends MVCPortlet {
}