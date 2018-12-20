package com.ted.lms.web.internal.portlet;

import com.ted.lms.constants.LMSActionKeys;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.model.Course;
import com.ted.lms.model.Module;
import com.ted.lms.security.permission.resource.LMSPermission;
import com.ted.lms.service.CourseLocalService;
import com.ted.lms.service.ModuleService;
import com.liferay.portal.kernel.portlet.PortletLayoutFinder;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.trash.TrashHelper;

import java.io.IOException;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Virginia Mart�n Agudo
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=wemooc",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/modules_admin/view.jsp",
		"javax.portlet.name=" + LMSPortletKeys.MODULES_ADMIN,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=user"
	},
	service = Portlet.class
)
public class ModuleAdminPortlet extends MVCPortlet {
	
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException, IOException {
		
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		List<Module> listModules = moduleService.getGroupModules(themeDisplay.getScopeGroupId());
		
		//Comprobamos el permiso de acceder a bloqueados
		boolean accessLock = LMSPermission.contains(themeDisplay.getPermissionChecker(), themeDisplay.getScopeGroupId(), LMSActionKeys.ACCESSLOCK);
		System.out.println("accessLock: " + accessLock);
		
		//Comprobamos si el curso est� bloqueado
		Course course = courseLocalService.getCourseByGroupCreatedId(themeDisplay.getScopeGroupId());
		boolean courseIsLocked = course.isLocked(themeDisplay.getUser(), themeDisplay.getPermissionChecker());
		System.out.println("courseIsLocked: " + courseIsLocked);
		
		renderRequest.setAttribute("listModules", listModules);
		renderRequest.setAttribute("trashHelper", trashHelper);
		renderRequest.setAttribute("accessLock", accessLock);
		renderRequest.setAttribute("courseIsLocked", courseIsLocked);
		renderRequest.setAttribute("activityPortletLayoutFinder", activityPortletLayoutFinder);
		
		super.render(renderRequest, renderResponse);
	}
	
	@Reference(
		target = "(model.class.name=com.ted.lms.model.LearningActivity)",
		unbind = "-"
	)
	protected void setActivityPortletLayoutFinder(PortletLayoutFinder portletPageFinder) {
		this.activityPortletLayoutFinder = portletPageFinder;
	}
	
	private PortletLayoutFinder activityPortletLayoutFinder;
	
	@Reference(unbind = "-")
	protected void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}
	
	private ModuleService moduleService;
	
	@Reference(unbind = "-")
	protected void setCourseLocalService(CourseLocalService courseLocalService) {
		this.courseLocalService = courseLocalService;
	}
	
	private CourseLocalService courseLocalService;
	
	@Reference
	private TrashHelper trashHelper;
}