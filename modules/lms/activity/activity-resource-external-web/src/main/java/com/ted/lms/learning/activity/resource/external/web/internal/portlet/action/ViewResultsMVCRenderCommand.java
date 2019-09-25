package com.ted.lms.learning.activity.resource.external.web.internal.portlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.ted.lms.learning.activity.resource.external.web.constants.ResourceExternalPortletKeys;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true, 
	property = { "javax.portlet.name=" + ResourceExternalPortletKeys.RESOURCE_EXTERNAL,
			"mvc.command.name=/activity/resource_external/view_result" }, 
	service = MVCRenderCommand.class
)
public class ViewResultsMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
	
		return "/view_results.jsp";
	}
}
