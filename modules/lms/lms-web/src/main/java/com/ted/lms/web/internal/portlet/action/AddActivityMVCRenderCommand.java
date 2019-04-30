package com.ted.lms.web.internal.portlet.action;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.model.LearningActivityTypeFactory;
import com.ted.lms.registry.LearningActivityTypeFactoryRegistryUtil;
import java.util.List;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + LMSPortletKeys.COURSE_CONTENT_VIEWER,
		"mvc.command.name=/activities/add_activity"
	},
	service = MVCRenderCommand.class
)
public class AddActivityMVCRenderCommand implements MVCRenderCommand {
	
	private static final Log log = LogFactoryUtil.getLog(AddActivityMVCRenderCommand.class);
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		long moduleId = ParamUtil.getLong(renderRequest, "moduleId", 0);
		List<LearningActivityTypeFactory> listLearningActivityTypeFactory = 
				LearningActivityTypeFactoryRegistryUtil.getLearningActivityFactories(themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());
		for(LearningActivityTypeFactory lat: listLearningActivityTypeFactory) {
			log.debug("lat: " + lat.getName(themeDisplay.getLocale()));
		}
		
		PortalUtil.clearRequestParameters(renderRequest);
		renderResponse.setProperty("clear-request-parameters",Boolean.TRUE.toString());
		renderRequest.setAttribute("moduleId", moduleId);
		renderRequest.setAttribute("listLearningActivityTypeFactory", listLearningActivityTypeFactory);
		
		return "/activities/add_activity.jsp";
	}
}
