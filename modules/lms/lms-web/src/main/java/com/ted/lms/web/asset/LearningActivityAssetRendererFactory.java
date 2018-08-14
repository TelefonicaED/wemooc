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
import com.ted.lms.model.LearningActivity;
import com.ted.lms.registry.LearningActivityTypeFactoryRegistryUtil;
import com.ted.lms.service.CourseLocalService;
import com.ted.lms.service.LearningActivityLocalService;
import com.ted.lms.web.internal.security.permission.resource.CoursePermission;
import com.ted.lms.web.internal.security.permission.resource.LMSPermission;
import com.ted.lms.web.internal.security.permission.resource.LearningActivityPermission;

import java.util.Locale;
import java.util.Map;

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
    property = {"javax.portlet.name=" + LMSPortletKeys.LEARNING_ACTIVITY},
    service = AssetRendererFactory.class
)
public class LearningActivityAssetRendererFactory extends BaseAssetRendererFactory<LearningActivity>{
	
	private static final Log log = LogFactoryUtil.getLog(LearningActivityAssetRendererFactory.class);
	
	public static final String TYPE = "learningactivity";

	public LearningActivityAssetRendererFactory() {
		
		log.debug("LearningActivityAssetRendererFactory::constructor");
	    setClassName(LearningActivity.class.getName());
	    setCategorizable(true);
	    setLinkable(true);
	    setPortletId(LMSPortletKeys.LEARNING_ACTIVITY);
	    setSearchable(true);
	    setSelectable(true);
	}
	
	@Override
	public AssetRenderer<LearningActivity> getAssetRenderer(long classPK, int type) throws PortalException {
		log.debug("LearningActivityAssetRendererFactory::getAssetRenderer::"+classPK+"::"+type);
		LearningActivity activity = learningActivityLocalService.getLearningActivity(classPK);

	    LearningActivityAssetRenderer learningActivityAssetRenderer = new LearningActivityAssetRenderer(activity, resourceBundleLoader);

	    learningActivityAssetRenderer.setAssetRendererType(type);
	    learningActivityAssetRenderer.setServletContext(servletContext);

	    return learningActivityAssetRenderer;
	}
	
	@Override
	public String getIconCssClass() {
		log.debug("LearningActivityAssetRendererFactory::getIconCssClass");
	    return "activity";
	}

	@Override
	public boolean hasPermission(
	        PermissionChecker permissionChecker, long classPK, String actionId)
	    throws Exception {
		log.debug("LearningActivityAssetRendererFactory::hasPermission::"+classPK+"::"+actionId);
	    return LearningActivityPermission.contains(permissionChecker, classPK, actionId);
	}
	
	@Reference(unbind = "-")
	protected void setLearningActivityLocalService(LearningActivityLocalService learningActivityLocalService) {

	    this.learningActivityLocalService = learningActivityLocalService;
	}

	private LearningActivityLocalService learningActivityLocalService;
	
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
		log.debug("LearningActivityAssetRendererFactory::getType");
	    return TYPE;
	}
	
	@Override
	public String getClassName() {
		log.debug("LearningActivityAssetRendererFactory::getClassName");
	    return LearningActivity.class.getName();
	}
	
	@Reference
	private Portal _portal;
}
