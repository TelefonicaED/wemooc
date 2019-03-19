package com.ted.lms.learning.activity.question.web.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.ted.lms.learning.activity.question.constants.QuestionsWebPortletKeys;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author JE10436
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.wemooc",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=" + QuestionsWebPortletKeys.EDIT_QUESTIONS_JSP,
		"javax.portlet.name=" + QuestionsWebPortletKeys.EDIT_QUESTIONS,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=user",
		"com.liferay.portlet.add-default-resource=true"
		
	},
	service = Portlet.class
)
public class EditQuestionsWebPortlet extends MVCPortlet {
}