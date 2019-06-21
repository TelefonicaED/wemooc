package com.ted.lms.web.internal.portlet;

import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.service.CourseLocalService;
import com.ted.lms.service.CourseService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.util.ParamUtil;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Virginia Martín Agudo
 */
@Component(
	configurationPid = "com.ted.lms.web.internal.configuration.CourseAdminPortletInstanceConfiguration",
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.wemooc",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.mvc-command-names-default-views=/courses/view",
		"javax.portlet.name=" + LMSPortletKeys.COURSE,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=user"
	},
	service = Portlet.class
)
public class CourseAdminPortlet extends MVCPortlet {
	
	public void addGroupUsers(ActionRequest actionRequest, ActionResponse actionResponse) throws PortalException{

		long courseId = ParamUtil.getLong(actionRequest, "courseId");
		long roleId = ParamUtil.getLong(actionRequest, "roleId");

		long[] addUserIds = ParamUtil.getLongValues(actionRequest, "rowIds");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(actionRequest);

		courseService.addUserCourse(courseId, addUserIds, roleId, serviceContext);

		actionResponse.setRenderParameter("mvcRenderCommandName", "/courses/view_members");
		actionResponse.setRenderParameter("courseId", String.valueOf(courseId));
		actionResponse.setRenderParameter("roleId", String.valueOf(roleId));
	}
	
	public void deleteCourseUsers(ActionRequest actionRequest, ActionResponse actionResponse) throws PortalException{

		long courseId = ParamUtil.getLong(actionRequest, "courseId");
		long roleId = ParamUtil.getLong(actionRequest, "roleId");
		
		long[] removeUserIds = null;

		long removeUserId = ParamUtil.getLong(actionRequest, "removeUserId");

		if (removeUserId > 0) {
			removeUserIds = new long[] {removeUserId};
		}
		else {
			removeUserIds = ParamUtil.getLongValues(actionRequest, "rowIds");
		}
		
		ServiceContext serviceContext = ServiceContextFactory.getInstance(actionRequest);

		courseService.unsetUserCourse(courseId, removeUserIds, roleId, serviceContext);
		
		actionResponse.setRenderParameter("mvcRenderCommandName", "/courses/view_members");
		actionResponse.setRenderParameter("courseId", String.valueOf(courseId));
		actionResponse.setRenderParameter("roleId", String.valueOf(roleId));
	}
	
	public void changeDisplayStyle(ActionRequest actionRequest, ActionResponse actionResponse) {

			hideDefaultSuccessMessage(actionRequest);

			String displayStyle = ParamUtil.getString(actionRequest, "displayStyle");

			PortalPreferences portalPreferences = PortletPreferencesFactoryUtil.getPortalPreferences(actionRequest);

			portalPreferences.setValue(LMSPortletKeys.COURSE, "display-style",displayStyle);
		}
	
	@Reference(unbind = "-")
	protected void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}
	
	@Reference(unbind = "-")
	protected void setCourseLocalService(CourseLocalService courseLocalService) {
		this.courseLocalService = courseLocalService;
	}
	
	private CourseService courseService;
	private CourseLocalService courseLocalService;
}