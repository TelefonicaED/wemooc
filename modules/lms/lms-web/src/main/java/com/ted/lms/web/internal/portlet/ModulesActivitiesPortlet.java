package com.ted.lms.web.internal.portlet;

import com.ted.lms.constants.LMSActionKeys;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.model.Course;
import com.ted.lms.model.Module;
import com.ted.lms.security.permission.resource.LMSPermission;
import com.ted.lms.service.CourseLocalService;
import com.ted.lms.service.LearningActivityService;
import com.ted.lms.service.ModuleService;
import com.ted.lms.web.internal.configuration.ModulesActivitiesPortletInstanceConfiguration;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.model.impl.VirtualLayout;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.portlet.PortletIdCodec;
import com.liferay.portal.kernel.portlet.PortletLayoutFinder;
import com.liferay.portal.kernel.portlet.PortletLayoutFinder.Result;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.ResourcePermissionService;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portlet.portletconfiguration.action.ActionUtil;
import com.liferay.sites.kernel.util.SitesUtil;
import com.liferay.trash.TrashHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.PortletPreferences;
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
	configurationPid = "com.ted.lms.web.internal.configuration.ModulesActivitiesPortletInstanceConfiguration",
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
	service = javax.portlet.Portlet.class
)
public class ModulesActivitiesPortlet extends MVCPortlet {
	
	private static final Log log = LogFactoryUtil.getLog(ModulesActivitiesPortlet.class);
	
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		PortalUtil.clearRequestParameters(renderRequest);

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
		
		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();
		boolean showActions = true;

		try {
			ModulesActivitiesPortletInstanceConfiguration configuration = portletDisplay.getPortletInstanceConfiguration(ModulesActivitiesPortletInstanceConfiguration.class);
			showActions = configuration.showActions();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		
		renderRequest.setAttribute("listModules", listModules);
		renderRequest.setAttribute("trashHelper", trashHelper);
		renderRequest.setAttribute("accessLock", accessLock);
		renderRequest.setAttribute("courseIsLocked", courseIsLocked);
		renderRequest.setAttribute("showActions", showActions);
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
	
	public void updateRolePermissions(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception{

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		String portletResource = ParamUtil.getString(actionRequest, "portletResource");
		String modelResource = ParamUtil.getString(actionRequest, "modelResource");
		long[] roleIds = StringUtil.split(ParamUtil.getString(actionRequest, "rolesSearchContainerPrimaryKeys"),0L);

		String selResource = PortletIdCodec.decodePortletName(portletResource);

		if (Validator.isNotNull(modelResource)) {
			selResource = modelResource;
		}

		long resourceGroupId = ParamUtil.getLong(actionRequest, "resourceGroupId", themeDisplay.getScopeGroupId());
		String resourcePrimKey = ParamUtil.getString(actionRequest, "resourcePrimKey");

		Map<Long, String[]> roleIdsToActionIds = new HashMap<>();

		for (long roleId : roleIds) {
			String[] actionIds = getActionIds(actionRequest, roleId, false);

			roleIdsToActionIds.put(roleId, actionIds);
		}

		resourcePermissionService.setIndividualResourcePermissions(resourceGroupId, themeDisplay.getCompanyId(), selResource,
			resourcePrimKey, roleIdsToActionIds);

		if (Validator.isNull(modelResource)) {

			Portlet portlet = ActionUtil.getPortlet(actionRequest);

			PortletPreferences portletPreferences = ActionUtil.getLayoutPortletSetup(actionRequest, portlet);

			portletPreferences.store();
		}
	}
	
	protected String[] getActionIds(ActionRequest actionRequest, long roleId, boolean includePreselected) {

		List<String> actionIds = getActionIdsList(actionRequest, roleId, includePreselected);

		return actionIds.toArray(new String[0]);
	}
	
	protected List<String> getActionIdsList(ActionRequest actionRequest, long roleId, boolean includePreselected) {

		List<String> actionIds = new ArrayList<>();

		Enumeration<String> enu = actionRequest.getParameterNames();

		while (enu.hasMoreElements()) {
			String name = enu.nextElement();

			if (name.startsWith(roleId + ActionUtil.ACTION)) {
				int pos = name.indexOf(ActionUtil.ACTION);

				String actionId = name.substring(pos + ActionUtil.ACTION.length());

				actionIds.add(actionId);
			} else if (includePreselected &&name.startsWith(roleId + ActionUtil.PRESELECTED)) {

				int pos = name.indexOf(ActionUtil.PRESELECTED);

				String actionId = name.substring(pos + ActionUtil.PRESELECTED.length());

				actionIds.add(actionId);
			}
		}

		return actionIds;
	}
	
	protected Layout setTargetLayout(RenderRequest request, long groupId, long plid)throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);

		PermissionChecker permissionChecker = themeDisplay.getPermissionChecker();

		Group group = groupLocalService.getGroup(groupId);
		Layout layout = layoutLocalService.getLayout(plid);

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
	
	@Reference(unbind = "-")
	protected void setGroupLocalService(GroupLocalService groupLocalService) {
		this.groupLocalService = groupLocalService;
	}
	
	private GroupLocalService groupLocalService;
	
	@Reference(unbind = "-")
	protected void setLayoutLocalService(LayoutLocalService layoutLocalService) {
		this.layoutLocalService = layoutLocalService;
	}
	
	private LayoutLocalService layoutLocalService;
	
	@Reference(unbind = "-")
	protected void setResourcePermissionService(
		ResourcePermissionService resourcePermissionService) {

		this.resourcePermissionService = resourcePermissionService;
	}
	
	private ResourcePermissionService resourcePermissionService;
	
	@Reference
	private TrashHelper trashHelper;
}