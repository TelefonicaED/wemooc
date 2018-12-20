package com.ted.lms.learning.activity.question.web.portlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.ted.lms.learning.activity.question.constants.QuestionsWebPortletKeys;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + QuestionsWebPortletKeys.EDIT_QUESTIONS,
		"mvc.command.name=/questions/add_question"
	},
	service = MVCRenderCommand.class
)
public class AddQuestionMVCRenderCommand implements MVCRenderCommand {
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		long actId = ParamUtil.getLong(renderRequest, "actId", 0);
		
		renderRequest.setAttribute("actId", actId);
		
		return "/add_question.jsp";
	}
}
