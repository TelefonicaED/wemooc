package com.ted.lms.question.multioptions.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.ted.lms.question.multioptions.constants.MultioptionsWebPortletKeys;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author JE10436
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=MultioptionsQuestionWeb",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.name=" + MultioptionsWebPortletKeys.MULTIOPTIONS,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=user",
		"com.liferay.portlet.add-default-resource=true",
		"com.liferay.portlet.use-default-template=true"
	},
	service = Portlet.class
)
public class MultioptionsPortlet extends MVCPortlet {
}