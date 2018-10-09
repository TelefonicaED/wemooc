package com.ted.prerequisite.module.internal.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.ted.prerequisite.module.internal.constants.ModulePrerequisitePortletKeys;
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
		"javax.portlet.init-param.view-template=/module/prerequisite/edit.jsp",
		"javax.portlet.name=" + ModulePrerequisitePortletKeys.MODULE,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=user"
	},
	service = Portlet.class
)
public class ModulePrerequisitePortlet extends MVCPortlet {
}