package com.ted.lms.web.internal.portlet.action;

import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.ModelHintsUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.model.Course;
import com.ted.lms.service.CourseLocalService;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

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
		
		if(courseId != 0) {
			Course course = courseLocalService.fetchCourse(courseId);
			renderRequest.setAttribute("course", course);
		}
		
		PortletURL editCourseURL = renderResponse.createActionURL();
		editCourseURL.setParameter("javax.portlet.action", "/lms/edit_course");
		editCourseURL.setParameter("courseId", String.valueOf(courseId));
		renderRequest.setAttribute("editCourseURL", editCourseURL);
		
		String maxLengthTitle = GetterUtil.getString(ModelHintsUtil.getHints(Group.class.getName(), "name").get("max-length"),"");
		renderRequest.setAttribute("maxLengthTitle", maxLengthTitle);
		
		
		PortletURL backURL = renderResponse.createRenderURL();
		renderRequest.setAttribute("backURL", backURL);
		
		return "/course_admin/edit_course.jsp";
	}
	
	@Reference(unbind = "-")
	protected void setCourseLocalService(CourseLocalService courseLocalService) {
		this.courseLocalService = courseLocalService;
	}
	
	private CourseLocalService courseLocalService;

}
