package com.ted.lms.web.internal.portlet.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.model.CalificationType;
import com.ted.lms.model.CalificationTypeFactory;
import com.ted.lms.model.Course;
import com.ted.lms.registry.CalificationTypeFactoryRegistryUtil;
import com.ted.lms.service.CourseLocalService;
import com.ted.lms.service.CourseResultLocalService;
import com.ted.lms.service.LearningActivityResultLocalService;
import com.ted.lms.service.LearningActivityService;
import com.ted.lms.service.ModuleResultLocalService;
import com.ted.lms.service.ModuleService;
import com.ted.lms.service.StudentLocalService;
import com.ted.lms.web.internal.display.context.StudentResultsDisplayContext;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
		immediate = true,
		property = {
			"javax.portlet.name=" + LMSPortletKeys.STUDENT_RESULTS,
			"mvc.command.name=/results/students"
		},
		service = MVCRenderCommand.class
	)
public class StudentResultsViewMVCRenderCommand implements MVCRenderCommand {
	
	private static final Log log = LogFactoryUtil.getLog(StudentResultsViewMVCRenderCommand.class);
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		
		HttpServletRequest request = portal.getHttpServletRequest(renderRequest);
		
		try {
			StudentResultsDisplayContext studentResultsDisplayContext = new StudentResultsDisplayContext(request, renderRequest, renderResponse, 
					courseLocalService, studentLocalService);
			
			Course course = studentResultsDisplayContext.getCourse();
			CalificationTypeFactory calificationTypeFactory = CalificationTypeFactoryRegistryUtil.getCalificationTypeFactoryByType(course.getCalificationType());
			CalificationType calificationType = calificationTypeFactory.getCalificationType(course);
			
			String redirect = ParamUtil.getString(renderRequest, "redirect");
			
			PortletURL portletURL = renderResponse.createRenderURL();

			portletURL.setParameter("mvcRenderCommandName", "/results/students");
			portletURL.setParameter("redirect", redirect);
			
			renderRequest.setAttribute("calificationType", calificationType);
			renderRequest.setAttribute("studentResultsDisplayContext", studentResultsDisplayContext);
			renderRequest.setAttribute("redirect", ParamUtil.getString(renderRequest, "redirect"));
			renderRequest.setAttribute("courseResultLocalService", courseResultLocalService);
			renderRequest.setAttribute("portletURL", portletURL);
			
			return "/results/students.jsp";
		} catch (PortalException e) {
			e.printStackTrace();
			return "/course/no_such_course.jsp";
		}
	}

	@Reference
	private CourseResultLocalService courseResultLocalService;
	
	@Reference
	private CourseLocalService courseLocalService;
	
	@Reference
	private ModuleService moduleService;
	
	@Reference
	private UserLocalService userLocalService;
	
	@Reference
	private ModuleResultLocalService moduleResultLocalService;
	
	@Reference
	private LearningActivityService learningActivityService;
	
	@Reference
	private LearningActivityResultLocalService learningActivityResultLocalService;
	
	@Reference
	private StudentLocalService studentLocalService;
	
	@Reference
	private Portal portal;

}
