package com.ted.lms.learning.activity.test.web.internal.portlet.action;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ted.lms.learning.activity.test.web.activity.TestActivityType;
import com.ted.lms.learning.activity.test.web.constants.TestPortletKeys;
import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true, 
	property = { "javax.portlet.name=" + TestPortletKeys.TEST,
			"mvc.command.name=/",
			"mvc.command.name=/activities/test/password" }, 
	service = MVCRenderCommand.class
)
public class TestActivityPasswordMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		
		TestActivityType testActivityType = (TestActivityType)renderRequest.getAttribute("testActivityType");
		
		String navigateParam = ParamUtil.getString(renderRequest, "navigate");
		String passwordParam = ParamUtil.getString(renderRequest, "password",StringPool.BLANK).trim();
		String password = testActivityType.getPassword();
		
		boolean improve = ParamUtil.getBoolean(renderRequest, "improve");

		if(!passwordParam.equals(password)) {
			SessionErrors.add(renderRequest, "password-incorrect");
		}
		PortletURL passwordURL = renderResponse.createRenderURL();
		if(improve) {
			passwordURL.setParameter("improve", "true");		
		}
		renderRequest.setAttribute("passwordURL", passwordURL);
		return "/password.jsp";
	}
}
