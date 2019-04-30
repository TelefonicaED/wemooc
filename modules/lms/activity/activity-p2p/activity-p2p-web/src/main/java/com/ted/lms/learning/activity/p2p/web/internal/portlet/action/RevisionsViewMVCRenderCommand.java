package com.ted.lms.learning.activity.p2p.web.internal.portlet.action;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.ted.lms.learning.activity.p2p.web.constants.P2PWebPortletKeys;
import com.ted.lms.service.LearningActivityLocalService;
import com.ted.lms.service.LearningActivityTryLocalService;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + P2PWebPortletKeys.P2P,
		"mvc.command.name=/", "mvc.command.name=/activities/p2p/revisions"
	},
	service = MVCRenderCommand.class
)
public class RevisionsViewMVCRenderCommand implements MVCRenderCommand {
	
	private static final Log log = LogFactoryUtil.getLog(RevisionsViewMVCRenderCommand.class);

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {

		log.debug("***********RevisionsViewMVCRenderCommand**************");
		long actId = ParamUtil.getLong(renderRequest, "actId", 0);

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		if(actId == 0) {
			renderRequest.setAttribute(WebKeys.PORTLET_CONFIGURATOR_VISIBILITY, Boolean.FALSE);
		}
		
		PortletURL searchURL = renderResponse.createRenderURL();
		searchURL.setParameter("actId", String.valueOf(actId));
		searchURL.setParameter("mvcRenderCommandName", "/activities/p2p/revisions");
		renderRequest.setAttribute("searchURL", searchURL);
		
		String criteria = ParamUtil.getString(renderRequest, "criteria");
		log.debug("criteria:_ " + criteria);
		renderRequest.setAttribute("criteria", criteria);
		
		renderRequest.setAttribute("actId", actId);
		
		return "/p2p/activity/revisions.jsp";
	}
	
	@Reference(unbind = "-")
	protected void setLearningActivityLocalService(LearningActivityLocalService learningActivityLocalService) {
		this.learningActivityLocalService = learningActivityLocalService;
	}
	
	private LearningActivityLocalService learningActivityLocalService;
	
	@Reference(unbind = "-")
	protected void setLearningActivityTryLocalService(LearningActivityTryLocalService learningActivityTryLocalService) {
		this.learningActivityTryLocalService = learningActivityTryLocalService;
	}
	
	private LearningActivityTryLocalService learningActivityTryLocalService;

}
