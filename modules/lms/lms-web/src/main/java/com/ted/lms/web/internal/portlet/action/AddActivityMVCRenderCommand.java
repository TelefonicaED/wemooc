package com.ted.lms.web.internal.portlet.action;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.model.Course;
import com.ted.lms.model.LearningActivityTypeFactory;
import com.ted.lms.registry.LearningActivityTypeFactoryRegistryUtil;
import com.ted.lms.service.CourseLocalService;
import com.ted.lms.service.CourseTypeLocalService;
import com.ted.lms.service.CourseTypeRelationLocalService;

import java.util.List;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

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
		
		Course course = courseLocalService.getCourseByGroupCreatedId(themeDisplay.getScopeGroupId());
		
		List<LearningActivityTypeFactory> listLearningActivityTypeFactory = null;
		
		if(course.getCourseTypeId() > 0) {
			listLearningActivityTypeFactory = courseTypeRelationLocalService.getLearningActivityTypes(course.getCourseTypeId());
		}else {
			listLearningActivityTypeFactory = 
					LearningActivityTypeFactoryRegistryUtil.getLearningActivityFactories(themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());
		}
		
		PortalUtil.clearRequestParameters(renderRequest);
		renderResponse.setProperty("clear-request-parameters",Boolean.TRUE.toString());
		renderRequest.setAttribute("moduleId", moduleId);
		renderRequest.setAttribute("listLearningActivityTypeFactory", listLearningActivityTypeFactory);
		
		return "/activities/add_activity.jsp";
	}
	
	@Reference
	private CourseLocalService courseLocalService;
	@Reference
	private CourseTypeRelationLocalService courseTypeRelationLocalService;
}
