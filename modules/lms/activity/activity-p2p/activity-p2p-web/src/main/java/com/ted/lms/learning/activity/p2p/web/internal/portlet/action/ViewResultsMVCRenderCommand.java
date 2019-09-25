package com.ted.lms.learning.activity.p2p.web.internal.portlet.action;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.ted.lms.learning.activity.p2p.web.constants.P2PWebPortletKeys;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + P2PWebPortletKeys.P2P,
		"mvc.command.name=/activities/p2p/view_result"
	},
	service = MVCRenderCommand.class
)
public class ViewResultsMVCRenderCommand implements MVCRenderCommand {
	
	private static final Log log = LogFactoryUtil.getLog(ViewResultsMVCRenderCommand.class);

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		
		return "/p2p/activity/detalleAct.jsp";
	}

}
