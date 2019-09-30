package com.ted.lms.web.internal.portlet.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;
import com.ted.lms.constants.LMSActionKeys;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.model.CalificationType;
import com.ted.lms.model.CalificationTypeFactory;
import com.ted.lms.model.Course;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.LearningActivityTypeFactory;
import com.ted.lms.registry.CalificationTypeFactoryRegistryUtil;
import com.ted.lms.registry.LearningActivityTypeFactoryRegistryUtil;
import com.ted.lms.security.permission.resource.LearningActivityPermission;
import com.ted.lms.service.CourseLocalService;
import com.ted.lms.service.LearningActivityLocalService;
import com.ted.lms.service.LearningActivityResultLocalService;
import com.ted.lms.service.ModuleLocalService;
import com.ted.lms.service.StudentLocalService;
import com.ted.lms.web.internal.display.context.ActivityResultsDisplayContext;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + LMSPortletKeys.MODULES_ACTIVITIES,
		"mvc.command.name=/activities/view_results"
	},
	service = MVCRenderCommand.class
)
public class ViewActivityResultsMVCRenderCommand implements MVCRenderCommand {
	
	private static final Log log = LogFactoryUtil.getLog(ViewActivityResultsMVCRenderCommand.class);

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		long actId = ParamUtil.getLong(renderRequest, "actId", 0);
		
		HttpServletRequest request = portal.getHttpServletRequest(renderRequest);
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
		
		try {
			ActivityResultsDisplayContext activityResultsDisplayContext = new ActivityResultsDisplayContext(request, 
					renderRequest, renderResponse, courseLocalService, studentLocalService);
			
			LearningActivity activity = learningActivityLocalService.getLearningActivity(actId);
			LearningActivityTypeFactory learningActivityTypeFactory = LearningActivityTypeFactoryRegistryUtil.getLearningActivityTypeFactoryByType(activity.getTypeId());
			
			Course course = activityResultsDisplayContext.getCourse();
			CalificationTypeFactory calificationTypeFactory = CalificationTypeFactoryRegistryUtil.getCalificationTypeFactoryByType(course.getCalificationType());
			CalificationType calificationType = calificationTypeFactory.getCalificationType(course);
			boolean deleteTries = learningActivityTypeFactory.canDeleteTries() && LearningActivityPermission.contains(themeDisplay.getPermissionChecker(), actId, LMSActionKeys.DELETE_TRIES);
			
			deleteTries = deleteTries && !learningActivityLocalService.hasDeleteTriesInProgress(actId, activity.getGroupId());
			
			renderRequest.setAttribute("activityResultsDisplayContext", activityResultsDisplayContext);
			renderRequest.setAttribute("learningActivityResultLocalService", learningActivityResultLocalService);
			renderRequest.setAttribute("calificationTypeFactory", calificationTypeFactory);
			renderRequest.setAttribute("calificationType", calificationType);
			renderRequest.setAttribute("actId", actId);
			renderRequest.setAttribute("deleteTries", deleteTries);
		
		} catch (PortalException e) {
			e.printStackTrace();
		}
		
		return "/activities/view_results.jsp";
	}
	
	@Reference
	private LearningActivityLocalService learningActivityLocalService;
	@Reference
	private CourseLocalService courseLocalService;
	@Reference
	private ModuleLocalService moduleLocalService;
	@Reference
	private LearningActivityResultLocalService learningActivityResultLocalService;
	@Reference
	private StudentLocalService studentLocalService;
	
	@Reference
	private Portal portal;
	
}
