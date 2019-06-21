package com.ted.lms.web.internal.portlet.action;

import com.liferay.frontend.taglib.clay.servlet.taglib.util.NavigationItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.NavigationItemList;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;
import com.ted.lms.configuration.CourseServiceConfiguration;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.constants.LMSRoleConstants;
import com.ted.lms.model.Course;
import com.ted.lms.service.CourseLocalService;
import com.ted.lms.web.internal.display.context.CourseMembersDisplayContext;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

@Component(
	configurationPid = "com.ted.lms.configuration.CourseServiceConfiguration",
	immediate = true,
	property = {
		"javax.portlet.name=" + LMSPortletKeys.COURSE,
		"mvc.command.name=/courses/view_members"
	},
	service = MVCRenderCommand.class
)
public class ViewMembersMVCRenderCommand implements MVCRenderCommand {
	
	private static final Log log = LogFactoryUtil.getLog(ViewMembersMVCRenderCommand.class);
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		long courseId = ParamUtil.getLong(renderRequest, "courseId", 0);
		log.debug("courseId: " + courseId);
		
		try {
			Course course = courseLocalService.getCourse(courseId);
			renderRequest.setAttribute("course", course);
			
			String redirect = ParamUtil.getString(renderRequest, "redirect");
			renderRequest.setAttribute("redirect", redirect);
			
			HttpServletRequest request = portal.getHttpServletRequest(renderRequest);
			
			ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			
			CourseMembersDisplayContext courseMembersDisplayContext = new CourseMembersDisplayContext(request, renderRequest, renderResponse);
			
			String tabs = ParamUtil.getString(renderRequest, "tabs", "students");
			
			String[] roleCourses = configuration.roleCourses();
			
			Role studentRole = RoleLocalServiceUtil.getRole(themeDisplay.getCompanyId(), LMSRoleConstants.STUDENT);
			Role teacherRole = RoleLocalServiceUtil.getRole(themeDisplay.getCompanyId(), LMSRoleConstants.TEACHER_ROLE);
			
			List<NavigationItem> navigationItem =  new NavigationItemList() {
				{
					add(
						navigationItem -> {
							navigationItem.setActive(Objects.equals(tabs, "students"));
							navigationItem.setHref(courseMembersDisplayContext.getPortletURL(), "tabs", "students", "roleId", studentRole.getRoleId());
							navigationItem.setLabel(studentRole.getTitle(themeDisplay.getLocale()));
						});
					if(teacherRole != null) {
						add(
							navigationItem -> {
								navigationItem.setActive(Objects.equals(tabs, "teacher"));
								navigationItem.setHref(courseMembersDisplayContext.getPortletURL(), "tabs", "teacher", "roleId", teacherRole.getRoleId());
								navigationItem.setLabel(teacherRole.getTitle(themeDisplay.getLocale()));
							});
					}
					for(String roleCourse: roleCourses) {
						Role role = RoleLocalServiceUtil.fetchRole(themeDisplay.getCompanyId(), roleCourse);
						if(role != null) {
							add(
								navigationItem -> {
									navigationItem.setActive(Objects.equals(tabs, role.getName()));
									navigationItem.setHref(courseMembersDisplayContext.getPortletURL(), "tabs", role.getName(), "roleId", role.getRoleId());
									navigationItem.setLabel(role.getTitle(themeDisplay.getLocale()));
								});
						}
					}
				}
			};
			
			renderRequest.setAttribute("courseId", courseId);
			renderRequest.setAttribute("courseMembersDisplayContext", courseMembersDisplayContext);
			renderRequest.setAttribute("navigationItem", navigationItem);
			
			return "/courses/view_members.jsp";
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
	
	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
	    configuration = ConfigurableUtil.createConfigurable(CourseServiceConfiguration.class, properties);
	}

	private volatile CourseServiceConfiguration configuration;

	@Reference
	private Portal portal;
}
