package com.ted.lms.web.internal.portlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ted.lms.constants.LMSPortletKeys;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

@Component(
		immediate = true,
		property = {
			"javax.portlet.name=" + LMSPortletKeys.COURSE,
			"mvc.command.name=/lms/edit_course"
		},
		service = MVCRenderCommand.class
	)
public class EditCourseMVCRenderCommand implements MVCRenderCommand {
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		
		long courseId = ParamUtil.getLong(renderRequest, "courseId", 0);
		
		if(courseId == 0) {
			
		}else {
			
		}
		
		return "/course_admin/edit_course_description.jsp";
	}

}
