package com.ted.lms.web.asset;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.ted.lms.constants.LMSActionKeys;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.model.Module;
import com.ted.lms.security.permission.resource.ModulePermission;
import com.ted.lms.security.permission.resource.LMSPermission;
import com.ted.lms.service.ModuleLocalService;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;
import javax.portlet.WindowStateException;
import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * Esta clase registra la entidad curso para que pueda ser usado como un asset
 * @author Virginia Martín Agudo
 *
 */
@Component(
    immediate = true,
    property = {"javax.portlet.name=" + LMSPortletKeys.MODULE},
    service = AssetRendererFactory.class
)
public class ModuleAssetRendererFactory extends BaseAssetRendererFactory<Module>{
	
	private static final Log log = LogFactoryUtil.getLog(ModuleAssetRendererFactory.class);
	
	public static final String TYPE = "module";

	public ModuleAssetRendererFactory() {
		
		log.debug("ModuleAssetRendererFactory::constructor");
	    setClassName(Module.class.getName());
	    setCategorizable(true);
	    setLinkable(true);
	    setPortletId(LMSPortletKeys.MODULE);
	    setSearchable(true);
	    setSelectable(true);
	}
	
	@Override
	public AssetRenderer<Module> getAssetRenderer(long classPK, int type) throws PortalException {
		log.debug("ModuleAssetRendererFactory::getAssetRenderer::"+classPK+"::"+type);
		Module module = moduleLocalService.getModule(classPK);

	    ModuleAssetRenderer moduleAssetRenderer = new ModuleAssetRenderer(module, resourceBundleLoader);

	    moduleAssetRenderer.setAssetRendererType(type);
	    moduleAssetRenderer.setServletContext(servletContext);

	    return moduleAssetRenderer;
	}
	
	@Override
	public String getIconCssClass() {
		log.debug("ModuleAssetRendererFactory::getIconCssClass");
	    return "module";
	}
	
	@Override
	public PortletURL getURLAdd(LiferayPortletRequest liferayPortletRequest, LiferayPortletResponse liferayPortletResponse, long classTypeId) {
		log.debug("ModuleAssetRendererFactory::getURLAdd::"+classTypeId);
	    PortletURL portletURL = _portal.getControlPanelPortletURL(liferayPortletRequest, getGroup(liferayPortletRequest),
	    															LMSPortletKeys.MODULE, 0, 0, PortletRequest.RENDER_PHASE);
	    portletURL.setParameter("mvcRenderCommandName", "/modules/edit_module");

	    return portletURL;
	}

	@Override
	public PortletURL getURLView(LiferayPortletResponse liferayPortletResponse,WindowState windowState) {
		log.debug("ModuleAssetRendererFactory::getURLView");
	    LiferayPortletURL liferayPortletURL = liferayPortletResponse.createLiferayPortletURL(LMSPortletKeys.MODULE, PortletRequest.RENDER_PHASE);

	    try {
	        liferayPortletURL.setWindowState(windowState);
	    }
	    catch (WindowStateException wse) {
	    	wse.printStackTrace();
	    }

	    return liferayPortletURL;
	}
	
	@Override
	public boolean hasAddPermission(PermissionChecker permissionChecker, long groupId, long classTypeId) throws Exception {
		log.debug("ModuleAssetRendererFactory::hasAddPermission::"+groupId+"::"+classTypeId);

	    return LMSPermission.contains(permissionChecker, groupId, LMSActionKeys.ADD_MODULE);
	}

	@Override
	public boolean hasPermission(PermissionChecker permissionChecker, long classPK, String actionId) throws Exception {
		log.debug("ModuleAssetRendererFactory::hasPermission::"+classPK+"::"+actionId);
	    return ModulePermission.contains(permissionChecker, classPK, actionId);
	}
	
	@Reference(unbind = "-")
	protected void setModuleLocalService(ModuleLocalService moduleLocalService) {

	    this.moduleLocalService = moduleLocalService;
	}

	private ModuleLocalService moduleLocalService;
	
	@Reference(
		unbind = "-"
	)
	public void setResourceBundleLoader(ResourceBundleLoader resourceBundleLoader) {

		this.resourceBundleLoader = resourceBundleLoader;
	}

	private ResourceBundleLoader resourceBundleLoader;
	
	@Reference(
	    target = "(osgi.web.symbolicname=com.ted.lms.web)", unbind = "-"
	)
	public void setServletContext(ServletContext servletContext) {
	    this.servletContext = servletContext;
	}

	private ServletContext servletContext;
	
	@Override
	public String getType() {
		log.debug("ModuleAssetRendererFactory::getType");
	    return TYPE;
	}
	
	@Override
	public String getClassName() {
		log.debug("ModuleAssetRendererFactory::getClassName");
	    return Module.class.getName();
	}
	
	@Reference
	private Portal _portal;
}
