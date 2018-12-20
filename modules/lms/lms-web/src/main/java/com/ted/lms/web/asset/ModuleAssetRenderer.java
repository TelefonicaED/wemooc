package com.ted.lms.web.asset;

import com.liferay.asset.kernel.model.BaseJSPAssetRenderer;
import com.liferay.asset.util.AssetHelper;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.ted.lms.constants.LMSKeys;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.model.Module;
import com.ted.lms.security.permission.resource.ModulePermission;
import com.ted.lms.util.LMSPrefsPropsValues;
import com.ted.lms.web.internal.util.ModuleUtil;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Esta clase declara la entidad módulo como asset
 * @author Virginia Martín Agudo
 *
 */
public class ModuleAssetRenderer extends BaseJSPAssetRenderer<Module>{
	
	private final Module module;
	private final ResourceBundleLoader resourceBundleLoader;
	
	public ModuleAssetRenderer(Module module, ResourceBundleLoader resourceBundleLoader) {
		this.module = module; 
		this.resourceBundleLoader = resourceBundleLoader;
	}

	@Override
	public Module getAssetObject() {
		return module;
	}

	@Override
	public long getGroupId() {
		return module.getGroupId();
	}

	@Override
	public long getUserId() {
		return module.getUserId();
	}

	@Override
	public String getUserName() {
		return module.getUserName();
	}

	@Override
	public String getUuid() {
		return module.getUuid();
	}

	@Override
	public String getClassName() {
		return Module.class.getName();
	}

	@Override
	public long getClassPK() {
		return module.getModuleId();
	}

	@Override
	public String getSummary(PortletRequest portletRequest, PortletResponse portletResponse) {
		
		int abstractLength = AssetHelper.ASSET_ENTRY_ABSTRACT_LENGTH;

	    if (portletRequest != null) {
	        abstractLength = GetterUtil.getInteger(
	            portletRequest.getAttribute(
	                WebKeys.ASSET_ENTRY_ABSTRACT_LENGTH),
	            AssetHelper.ASSET_ENTRY_ABSTRACT_LENGTH);
	    }
		
		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		ResourceBundle resourceBundle = resourceBundleLoader.loadResourceBundle(themeDisplay.getLocale());
		
		String summary =  HtmlUtil.stripHtml(StringUtil.shorten(ModuleUtil.getDisplayDescription(resourceBundle, module), abstractLength));
		
		return summary;
	}

	@Override
	public String getTitle(Locale locale) {
		 ResourceBundle resourceBundle = resourceBundleLoader.loadResourceBundle(locale);

		 return ModuleUtil.getDisplayTitle(resourceBundle, module);
	}
	
	@Override
	public int getStatus() {
	    return module.getStatus();
	}

	/**
	 * Para mostrar los comentarios en los cursos
	 */
	@Override
	public String getDiscussionPath() {
	    if (LMSPrefsPropsValues.getModuleCommentsEnabled(module.getCompanyId())) {
	        return "edit_entry_discussion";
	    }
	    else {
	        return null;
	    }
	}

	public boolean hasDeletePermission(PermissionChecker permissionChecker) throws SystemException, PortalException {
	    return ModulePermission.contains(permissionChecker, module, ActionKeys.DELETE);
	}

	@Override
	public boolean hasEditPermission(PermissionChecker permissionChecker) throws PortalException {
	    return ModulePermission.contains(permissionChecker, module, ActionKeys.UPDATE);
	}

	@Override
	public boolean hasViewPermission(PermissionChecker permissionChecker) throws PortalException {
	    return ModulePermission.contains(permissionChecker, module, ActionKeys.VIEW);
	}
	
	@Override
	public String getJspPath(HttpServletRequest request, String template) {
	    if (template.equals(TEMPLATE_ABSTRACT) || template.equals(TEMPLATE_FULL_CONTENT)) {
	        return "/modules/asset/" + template + ".jsp";
	    }
	    else {
	        return null;
	    }
	}
	
	@Override
	public boolean include(HttpServletRequest request, HttpServletResponse response, String template) throws Exception {

	    request.setAttribute(LMSKeys.MODULE, module);

	    return super.include(request, response, template);
	}
	
	@Override
	public PortletURL getURLEdit(LiferayPortletRequest liferayPortletRequest, LiferayPortletResponse liferayPortletResponse) throws Exception {

	    Group group = GroupLocalServiceUtil.fetchGroup(module.getGroupId());

	    PortletURL portletURL = PortalUtil.getControlPanelPortletURL(liferayPortletRequest, group, LMSPortletKeys.MODULES_ADMIN, 0, 0,PortletRequest.RENDER_PHASE);

	    portletURL.setParameter("mvcRenderCommandName", "/modules/edit_module");
	    portletURL.setParameter("moduleId", String.valueOf(module.getModuleId()));

	    return portletURL;
	}
	
	@Override
	public String getURLView(LiferayPortletResponse liferayPortletResponse, WindowState windowState) throws Exception {
		
		Group moduleGroup= GroupLocalServiceUtil.getGroup(module.getGroupId());
		
		PortletURL portletURL = liferayPortletResponse.createLiferayPortletURL(moduleGroup.getDefaultPublicPlid(), StringPool.BLANK, PortletRequest.RENDER_PHASE);
		
		return portletURL.toString();
	}
	
	@Override
	public String getURLViewInContext(LiferayPortletRequest liferayPortletRequest, LiferayPortletResponse liferayPortletResponse, 
										String noSuchEntryRedirect) throws Exception {
		return module.getURLView(liferayPortletRequest);
	}
	
}
