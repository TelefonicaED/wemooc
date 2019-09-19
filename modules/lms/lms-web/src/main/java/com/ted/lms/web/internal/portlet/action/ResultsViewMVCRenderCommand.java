package com.ted.lms.web.internal.portlet.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.exception.NoSuchCourseResultException;
import com.ted.lms.model.CalificationType;
import com.ted.lms.model.CalificationTypeFactory;
import com.ted.lms.model.Course;
import com.ted.lms.model.CourseResult;
import com.ted.lms.model.Module;
import com.ted.lms.registry.CalificationTypeFactoryRegistryUtil;
import com.ted.lms.service.CourseLocalService;
import com.ted.lms.service.CourseResultLocalService;
import com.ted.lms.service.LearningActivityLocalService;
import com.ted.lms.service.LearningActivityResultLocalService;
import com.ted.lms.service.LearningActivityService;
import com.ted.lms.service.ModuleResultLocalService;
import com.ted.lms.service.ModuleService;

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
		immediate = true,
		property = {
			"javax.portlet.name=" + LMSPortletKeys.MY_RESULTS,
			"javax.portlet.name=" + LMSPortletKeys.STUDENT_RESULTS,
			"mvc.command.name=/results/view"
		},
		service = MVCRenderCommand.class
	)
public class ResultsViewMVCRenderCommand implements MVCRenderCommand {
	
	private static final Log log = LogFactoryUtil.getLog(ResultsViewMVCRenderCommand.class);
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		long studentId = ParamUtil.getLong(renderRequest, "studentId", 0);
		long courseId = ParamUtil.getLong(renderRequest, "courseId", 0);
		
		log.debug("studentId: " + studentId);
		log.debug("courseId: " + courseId);
		
		try {
			Course course = null;
			
			if(courseId == 0) {
				course = courseLocalService.getCourseByGroupCreatedId(themeDisplay.getScopeGroupId());
				courseId = course.getCourseId();
			}else {
				course = courseLocalService.getCourse(courseId);
			}
			
			if(studentId > 0) {
				User user = userLocalService.getUser(studentId);
				renderRequest.setAttribute("student", user.getFullName());
			}else {
				studentId = themeDisplay.getUserId();
			}
			
			CalificationTypeFactory calificationTypeFactory = CalificationTypeFactoryRegistryUtil.getCalificationTypeFactoryByType(course.getCalificationType());
			CalificationType calificationType = calificationTypeFactory.getCalificationType(course);
		
			CourseResult courseResult = courseResultLocalService.fetchCourseResult(courseId, studentId);
			List<Module> modules = moduleService.getGroupModules(course.getGroupCreatedId());
			
			renderRequest.setAttribute("calificationType", calificationType);
			renderRequest.setAttribute("courseResult", courseResult);
			renderRequest.setAttribute("modules", modules);
			renderRequest.setAttribute("studentId", studentId);
			renderRequest.setAttribute("redirect", ParamUtil.getString(renderRequest, "redirect"));
			renderRequest.setAttribute("moduleResultLocalService", moduleResultLocalService);
			renderRequest.setAttribute("learningActivityService", learningActivityService);
			renderRequest.setAttribute("learningActivityResultLocalService", learningActivityResultLocalService);
			
			
			return "/results/view.jsp";
		} catch (NoSuchCourseResultException e) {
			e.printStackTrace();
			return "/inscription/error.jsp";	
		} catch (PortalException e) {
			e.printStackTrace();
			return "/error.jsp";
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

}
