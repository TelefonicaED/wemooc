package com.ted.lms.question.drag.and.drop.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.ted.lms.question.drag.and.drop.constants.DragAndDropWebPortletKeys;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * Portlet para poder insertar el jsp de preguntas de arrastrar
 * @author Virginia Martín Agudo
 *
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.name=" + DragAndDropWebPortletKeys.DRAG_AND_DROP_PORTLET,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=user",
		"com.liferay.portlet.add-default-resource=true"
	},
	service = Portlet.class
)
public class DragAndDropPortlet extends MVCPortlet {

}
