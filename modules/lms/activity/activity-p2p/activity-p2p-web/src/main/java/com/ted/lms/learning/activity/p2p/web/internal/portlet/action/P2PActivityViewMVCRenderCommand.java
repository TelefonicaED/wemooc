package com.ted.lms.learning.activity.p2p.web.internal.portlet.action;

import com.liferay.frontend.taglib.clay.servlet.taglib.util.JSPNavigationItemList;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.NavigationItem;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.ted.lms.learning.activity.p2p.web.constants.P2PWebPortletKeys;
import com.ted.lms.service.LearningActivityLocalService;
import com.ted.lms.service.LearningActivityTryLocalService;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.RenderURL;

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
		
		
		ParamUtil.print(renderRequest);
		long actId = ParamUtil.getLong(renderRequest, "actId", 0);

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		if(actId == 0) {
			renderRequest.setAttribute(WebKeys.PORTLET_CONFIGURATOR_VISIBILITY, Boolean.FALSE);
		}
		
		PortletURL addP2PActivityURL = renderResponse.createActionURL();
		addP2PActivityURL.setParameter("javax.portlet.action", "addP2PActivity");
		addP2PActivityURL.setParameter("actId", String.valueOf(actId));
		renderRequest.setAttribute("addP2PActivityURL", addP2PActivityURL);
		
		LiferayPortletResponse liferayPortletResponse = PortalUtil.getLiferayPortletResponse(renderResponse);
		
		renderRequest.setAttribute("actId", actId);
		
		return "/p2p/activity/view.jsp";
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
