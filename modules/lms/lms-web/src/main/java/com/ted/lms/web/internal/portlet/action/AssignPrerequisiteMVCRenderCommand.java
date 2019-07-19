package com.ted.lms.web.internal.portlet.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.trash.TrashHelper;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.model.Course;
import com.ted.lms.service.CourseLocalService;
import com.ted.lms.web.internal.display.context.SelectMembersDisplayContext;
import com.ted.prerequisite.model.PrerequisiteFactory;
import com.ted.prerequisite.model.PrerequisiteRelation;
import com.ted.prerequisite.registry.PrerequisiteFactoryRegistryUtil;
import com.ted.prerequisite.service.PrerequisiteRelationLocalServiceUtil;

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
		"mvc.command.name=/courses/assign_prerequisite"
	},
	service = MVCRenderCommand.class
)
public class AssignPrerequisiteMVCRenderCommand implements MVCRenderCommand {
	
	private static final Log log = LogFactoryUtil.getLog(AssignPrerequisiteMVCRenderCommand.class);
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		long courseId = ParamUtil.getLong(renderRequest, "courseId", 0);
		log.debug("courseId: " + courseId);
		
		
		try {
			if(SessionMessages.contains(renderRequest, "prerequisiteAdded")) {
				ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
				
				long prerequisiteRelationId = ParamUtil.getLong(renderRequest, "prerequisiteRelationId");
				
				PrerequisiteRelation prerequisiteRelation = PrerequisiteRelationLocalServiceUtil.getPrerequisiteRelation(prerequisiteRelationId);
				renderRequest.setAttribute("name", prerequisiteRelation.getPrerequisite().getTitle(themeDisplay.getLocale()));
				renderRequest.setAttribute("type", prerequisiteRelation.getPrerequisite().getPrerequisiteFactory().getTitle(themeDisplay.getLocale()));
				renderRequest.setAttribute("prerequisiteRelationId", prerequisiteRelationId);
			}else {
				
				Course course = courseLocalService.getCourse(courseId);
				renderRequest.setAttribute("course", course);
				
				String redirect = ParamUtil.getString(renderRequest, "redirect");
				renderRequest.setAttribute("redirect", redirect);
				
				String className = ParamUtil.getString(renderRequest, "className");
				PrerequisiteFactory prerequisiteFactory = PrerequisiteFactoryRegistryUtil.getPrerequisiteFactoryByClassName(className);
				
				renderRequest.setAttribute("prerequisiteFactory", prerequisiteFactory);
				renderRequest.setAttribute("courseId", courseId);
				renderRequest.setAttribute("classNameId", PortalUtil.getClassNameId(Course.class));
			}
			return "/courses/assign_prerequisite.jsp";
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
	private Portal portal;
}
