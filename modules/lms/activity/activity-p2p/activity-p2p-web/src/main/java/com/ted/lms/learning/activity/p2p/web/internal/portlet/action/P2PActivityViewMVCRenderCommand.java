package com.ted.lms.learning.activity.p2p.web.internal.portlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.ted.lms.learning.activity.p2p.web.constants.P2PWebPortletKeys;
import com.ted.lms.service.LearningActivityLocalService;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + P2PWebPortletKeys.P2P,
		"mvc.command.name=/", "mvc.command.name=/activities/p2p/view_activity"
	},
	service = MVCRenderCommand.class
)
public class P2PActivityViewMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		long actId = ParamUtil.getLong(renderRequest, "actId", 0);
		System.out.println("render view p2p: " + actId);
		
		if(actId == 0) {
			renderRequest.setAttribute(WebKeys.PORTLET_CONFIGURATOR_VISIBILITY, Boolean.FALSE);
		}
		
		renderRequest.setAttribute("actId", actId);
		
		return "/p2p/activity/view.jsp";
	}
	
	@Reference(unbind = "-")
	protected void setLearningActivityLocalService(LearningActivityLocalService learningActivityLocalService) {
		this.learningActivityLocalService = learningActivityLocalService;
	}
	
	private LearningActivityLocalService learningActivityLocalService;

}
