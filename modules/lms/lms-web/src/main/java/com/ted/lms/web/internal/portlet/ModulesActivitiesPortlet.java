package com.ted.lms.web.internal.portlet;

import com.ted.lms.constants.LMSActionKeys;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.model.Course;
import com.ted.lms.model.Module;
import com.ted.lms.security.permission.resource.LMSPermission;
import com.ted.lms.service.CourseLocalService;
import com.ted.lms.service.LearningActivityService;
import com.ted.lms.service.ModuleService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.impl.VirtualLayout;
import com.liferay.portal.kernel.portlet.PortletLayoutFinder;
import com.liferay.portal.kernel.portlet.PortletLayoutFinder.Result;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.sites.kernel.util.SitesUtil;
import com.liferay.trash.TrashHelper;

import java.io.IOException;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowState;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Virginia Mart�n Agudo
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.wemooc",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/modules_activities/view.jsp",
		"javax.portlet.name=" + LMSPortletKeys.MODULES_ACTIVITIES,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=user",
		"javax.portlet.display-name=ModulesActivities",
		"javax.portlet.supported-public-render-parameter=actId",
		"javax.portlet.supported-public-render-parameter=moduleId"
	},
	service = Portlet.class
)
public class ModulesActivitiesPortlet extends MVCPortlet {
	
	private static final Log log = LogFactoryUtil.getLog(ModulesActivitiesPortlet.class);
	
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		List<Module> listModules = moduleService.getGroupModules(themeDisplay.getScopeGroupId());
		
		//Comprobamos el permiso de acceder a bloqueados
		boolean accessLock = LMSPermission.contains(themeDisplay.getPermissionChecker(), themeDisplay.getScopeGroupId(), LMSActionKeys.ACCESSLOCK);
		log.debug("accessLock: " + accessLock);
		
		//Comprobamos si el curso est� bloqueado
		Course course = courseLocalService.getCourseByGroupCreatedId(themeDisplay.getScopeGroupId());
		boolean courseIsLocked = course.isLocked(themeDisplay.getUser(), themeDisplay.getPermissionChecker());
		log.debug("courseIsLocked: " + courseIsLocked);
		
		long moduleId = ParamUtil.getLong(renderRequest, "moduleId");
		log.debug("moduleId: " + moduleId);
		
		renderRequest.setAttribute("listModules", listModules);
		renderRequest.setAttribute("trashHelper", trashHelper);
		renderRequest.setAttribute("accessLock", accessLock);
		renderRequest.setAttribute("courseIsLocked", courseIsLocked);
		renderRequest.setAttribute("newModuleURL", getURLNewModule(renderRequest, themeDisplay));
		renderRequest.setAttribute("activityPortletLayoutFinder", activityPortletLayoutFinder);
		renderRequest.setAttribute("moduleId", moduleId);
		renderRequest.setAttribute("learningActivityService", learningActivityService);
		
		super.render(renderRequest, renderResponse);
	}
	
	private String getURLNewModule(RenderRequest renderRequest, ThemeDisplay themeDisplay) {

		String urlNewModule = "";
		
		try {
			Result result = portletLayoutFinder.find(themeDisplay, themeDisplay.getScopeGroupId());
			
			Layout layout = setTargetLayout(renderRequest, themeDisplay.getScopeGroupId(), result.getPlid());
			
			PortletURL portletURL = PortletURLFactoryUtil.create(renderRequest, result.getPortletId(), layout, PortletRequest.RENDER_PHASE);
			portletURL.setParameter("mvcRenderCommandName", "/modules/edit_module");
			portletURL.setParameter("moduleId", "0");
			portletURL.setPortletMode(PortletMode.VIEW);
			portletURL.setWindowState(WindowState.NORMAL);
			
			urlNewModule = portletURL.toString();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return urlNewModule;
	}
	
	protected static Layout setTargetLayout(RenderRequest request, long groupId, long plid)throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);

		PermissionChecker permissionChecker = themeDisplay.getPermissionChecker();

		Group group = GroupLocalServiceUtil.getGroup(groupId);
		Layout layout = LayoutLocalServiceUtil.getLayout(plid);

		if ((groupId == layout.getGroupId()) || (group.getParentGroupId() == layout.getGroupId()) ||
			(layout.isPrivateLayout() && !SitesUtil.isUserGroupLayoutSetViewable(permissionChecker, layout.getGroup()))) {

			return layout;
		}

		layout = new VirtualLayout(layout, group);

		return layout;
	}
	
	@Reference(
		target = "(model.class.name=com.ted.lms.model.Module)",
		unbind = "-"
	)
	protected void setPortletLayoutFinder(PortletLayoutFinder portletPageFinder) {
		this.portletLayoutFinder = portletPageFinder;
	}
	
	private PortletLayoutFinder portletLayoutFinder;
	
	@Reference(
		target = "(model.class.name=com.ted.lms.model.LearningActivity)",
		unbind = "-"
	)
	protected void setActivityPortletLayoutFinder(PortletLayoutFinder portletPageFinder) {
		this.activityPortletLayoutFinder = portletPageFinder;
	}
	
	private PortletLayoutFinder activityPortletLayoutFinder;
	
	@Reference(unbind = "-")
	protected void setLearningActivityService(LearningActivityService learningActivityService) {
		this.learningActivityService = learningActivityService;
	}
	
	private LearningActivityService learningActivityService;
	
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