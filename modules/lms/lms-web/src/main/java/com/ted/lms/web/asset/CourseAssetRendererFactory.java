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
import com.ted.lms.model.Course;
import com.ted.lms.security.permission.resource.CoursePermission;
import com.ted.lms.security.permission.resource.LMSPermission;
import com.ted.lms.service.CourseLocalService;

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
    property = {"javax.portlet.name=" + LMSPortletKeys.COURSE},
    service = AssetRendererFactory.class
)
public class CourseAssetRendererFactory extends BaseAssetRendererFactory<Course>{
	
	private static final Log log = LogFactoryUtil.getLog(CourseAssetRendererFactory.class);
	
	public static final String TYPE = "course";

	public CourseAssetRendererFactory() {
		
		log.debug("CourseAssetRendererFactory::constructor");
	    setClassName(Course.class.getName());
	    setCategorizable(true);
	    setLinkable(true);
	    setPortletId(LMSPortletKeys.COURSE);
	    setSearchable(true);
	    setSelectable(true);
	}
	
	@Override
	public AssetRenderer<Course> getAssetRenderer(long classPK, int type) throws PortalException {
		log.debug("CourseAssetRendererFactory::getAssetRenderer::"+classPK+"::"+type);
		Course course = courseLocalService.getCourse(classPK);

	    CourseAssetRenderer courseAssetRenderer = new CourseAssetRenderer(course, resourceBundleLoader);

	    courseAssetRenderer.setAssetRendererType(type);
	    courseAssetRenderer.setServletContext(servletContext);

	    return courseAssetRenderer;
	}
	
	@Override
	public String getIconCssClass() {
		log.debug("CourseAssetRendererFactory::getIconCssClass");
	    return "course";
	}
	
	@Override
	public PortletURL getURLAdd(LiferayPortletRequest liferayPortletRequest, LiferayPortletResponse liferayPortletResponse, long classTypeId) {
		log.debug("CourseAssetRendererFactory::getURLAdd::"+classTypeId);
	    PortletURL portletURL = _portal.getControlPanelPortletURL(liferayPortletRequest, getGroup(liferayPortletRequest),
	    															LMSPortletKeys.COURSE, 0, 0, PortletRequest.RENDER_PHASE);
	    portletURL.setParameter("mvcRenderCommandName", "/lms/edit_course");

	    return portletURL;
	}

	@Override
	public PortletURL getURLView(LiferayPortletResponse liferayPortletResponse,WindowState windowState) {
		log.debug("CourseAssetRendererFactory::getURLView");
	    LiferayPortletURL liferayPortletURL = liferayPortletResponse.createLiferayPortletURL(LMSPortletKeys.COURSE, PortletRequest.RENDER_PHASE);

	    try {
	        liferayPortletURL.setWindowState(windowState);
	    }
	    catch (WindowStateException wse) {
	    	wse.printStackTrace();
	    }

	    return liferayPortletURL;
	}
	
	@Override
	public boolean hasAddPermission(PermissionChecker permissionChecker, long groupId, long classTypeId)
	    throws Exception {
		log.debug("CourseAssetRendererFactory::hasAddPermission::"+groupId+"::"+classTypeId);

	    return LMSPermission.contains(permissionChecker, groupId, LMSActionKeys.ADD_COURSE);
	}

	@Override
	public boolean hasPermission(
	        PermissionChecker permissionChecker, long classPK, String actionId)
	    throws Exception {
		log.debug("CourseAssetRendererFactory::hasPermission::"+classPK+"::"+actionId);
	    return CoursePermission.contains(permissionChecker, classPK, actionId);
	}
	
	@Reference(unbind = "-")
	protected void setCourseLocalService(CourseLocalService courseLocalService) {

	    this.courseLocalService = courseLocalService;
	}

	private CourseLocalService courseLocalService;
	
	@Reference(
		target = "(bundle.symbolic.name=com.ted.lms.web)", unbind = "-"
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
		log.debug("CourseAssetRendererFactory::getType");
	    return TYPE;
	}
	
	@Override
	public String getClassName() {
		log.debug("CourseAssetRendererFactory::getClassName");
	    return Course.class.getName();
	}
	
	@Reference
	private Portal _portal;
}
