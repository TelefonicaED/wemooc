package com.ted.lms.calification.zero.to.n.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.ted.lms.calification.zero.to.n.constants.ZeroToNPortletKeys;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * Portlet para poder insertar el jsp de edición del método de calificación de 0 a n
 * @author Virginia Martín Agudo
 *
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/zero_to_n/calification/edit.jsp",
		"javax.portlet.name=" + ZeroToNPortletKeys.ZERO_TO_N,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=user"
	},
	service = Portlet.class
)
public class ZeroToNPortlet extends MVCPortlet {

}
