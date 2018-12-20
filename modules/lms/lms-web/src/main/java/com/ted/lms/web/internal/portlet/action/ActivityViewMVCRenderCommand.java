package com.ted.lms.web.internal.portlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.service.LearningActivityLocalService;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + LMSPortletKeys.COURSE_CONTENT_VIEWER,
		"mvc.command.name=/", "mvc.command.name=/activities/view_activity"
	},
	service = MVCRenderCommand.class
)
public class ActivityViewMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		long actId = ParamUtil.getLong(renderRequest, "actId", 0);
		System.out.println("render view learningActivityId: " + actId);
		
		LearningActivity activity = null;
		
		if(actId > 0) {
			activity = learningActivityLocalService.fetchLearningActivity(actId);
		}
		
		renderRequest.setAttribute("activity", activity);
		
		return "/activities/view_activity.jsp";
	}
	
	@Reference(unbind = "-")
	protected void setLearningActivityLocalService(LearningActivityLocalService learningActivityLocalService) {
		this.learningActivityLocalService = learningActivityLocalService;
	}
	
	private LearningActivityLocalService learningActivityLocalService;

}
