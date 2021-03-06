package com.ted.lms.web.internal.portlet.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.trash.TrashHelper;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.model.Course;
import com.ted.lms.service.CourseLocalService;
import com.ted.lms.web.internal.display.context.SelectMembersDisplayContext;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	configurationPid = "com.ted.lms.configuration.CourseServiceConfiguration",
	immediate = true,
	property = {
		"javax.portlet.name=" + LMSPortletKeys.COURSE,
		"mvc.command.name=/courses/assign_members"
	},
	service = MVCRenderCommand.class
)
public class AssignMembersMVCRenderCommand implements MVCRenderCommand {
	
	private static final Log log = LogFactoryUtil.getLog(AssignMembersMVCRenderCommand.class);
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		long courseId = ParamUtil.getLong(renderRequest, "courseId", 0);
		log.debug("courseId: " + courseId);
		log.debug("AssignMembersMVCRenderCommand redirect: " + ParamUtil.getString(renderRequest, "redirect"));
		
		try {
			Course course = courseLocalService.getCourse(courseId);
			renderRequest.setAttribute("course", course);
			
			String redirect = ParamUtil.getString(renderRequest, "redirect");
			renderRequest.setAttribute("redirect", redirect);
			
			HttpServletRequest request = portal.getHttpServletRequest(renderRequest);
			
			SelectMembersDisplayContext selectMembersDisplayContext = new SelectMembersDisplayContext(request, renderRequest, renderResponse);
			
			renderRequest.setAttribute("selectMembersDisplayContext", selectMembersDisplayContext);
			renderRequest.setAttribute("courseId", courseId);
			
			return "/courses/assign_members.jsp";
		} catch (PortalException e) {
			e.printStackTrace();
			return "/courses/error.jsp";
		}

	}
	

	@Reference(unbind = "-")
	protected void setCourseLocalService(CourseLocalService courseLocalService) {
		this.courseLocalService = courseLocalService;
	}
	
	private CourseLocalService courseLocalService;
	
	@Reference
	private TrashHelper trashHelper;

	@Reference
	private Portal portal;
}
