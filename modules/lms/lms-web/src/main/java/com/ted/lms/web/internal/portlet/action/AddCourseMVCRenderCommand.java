package com.ted.lms.web.internal.portlet.action;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.model.CourseType;
import com.ted.lms.service.CourseTypeLocalService;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
		immediate = true,
		property = {
			"javax.portlet.name=" + LMSPortletKeys.COURSE,
			"mvc.command.name=/courses/add_course"
		},
		service = MVCRenderCommand.class
	)
public class AddCourseMVCRenderCommand implements MVCRenderCommand {
	
	private static final Log log = LogFactoryUtil.getLog(AddCourseMVCRenderCommand.class);
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		log.debug("AddCourseMVCRenderCommand redirect: " + ParamUtil.getString(renderRequest, "redirect"));
		
		List<CourseType> courseTypes = courseTypeLocalService.getCourseTypes(themeDisplay.getCompanyId());
		renderRequest.setAttribute("courseTypes", courseTypes);

		renderRequest.setAttribute("redirect", ParamUtil.getString(renderRequest, "redirect"));
		
		return "/course_admin/add_course.jsp";
	}

	@Reference(unbind = "-")
	protected void setCourseTypeLocalService(CourseTypeLocalService courseTypeLocalService) {
		this.courseTypeLocalService = courseTypeLocalService;
	}
	
	private CourseTypeLocalService courseTypeLocalService;

}
