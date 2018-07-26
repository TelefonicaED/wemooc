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
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.ted.lms.constants.LMSKeys;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.model.Course;
import com.ted.lms.util.LMSPropsKeys;
import com.ted.lms.web.internal.securitiy.permission.resource.CoursePermission;
import com.ted.lms.web.internal.util.CourseUtil;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CourseAssetRenderer extends BaseJSPAssetRenderer<Course>{
	
	private final Course course;
	private final ResourceBundleLoader resourceBundleLoader;
	
	public CourseAssetRenderer(Course course, ResourceBundleLoader resourceBundleLoader) {
		this.course = course;
		this.resourceBundleLoader = resourceBundleLoader;
	}

	@Override
	public Course getAssetObject() {
		return course;
	}

	@Override
	public long getGroupId() {
		return course.getGroupId();
	}

	@Override
	public long getUserId() {
		return course.getUserId();
	}

	@Override
	public String getUserName() {
		return course.getUserName();
	}

	@Override
	public String getUuid() {
		return course.getUuid();
	}

	@Override
	public String getClassName() {
		return Course.class.getName();
	}

	@Override
	public long getClassPK() {
		return course.getCourseId();
	}

	@Override
	public String getSummary(PortletRequest portletRequest, PortletResponse portletResponse) {
		//TODO mostrar la descripci�n pero sin c�digo html
		
		int abstractLength = AssetHelper.ASSET_ENTRY_ABSTRACT_LENGTH;

	    if (portletRequest != null) {
	        abstractLength = GetterUtil.getInteger(
	            portletRequest.getAttribute(
	                WebKeys.ASSET_ENTRY_ABSTRACT_LENGTH),
	            AssetHelper.ASSET_ENTRY_ABSTRACT_LENGTH);
	    }
		
		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		ResourceBundle resourceBundle = resourceBundleLoader.loadResourceBundle(themeDisplay.getLocale());
		
		String summary =  HtmlUtil.stripHtml(StringUtil.shorten(CourseUtil.getDisplayDescription(resourceBundle, course), abstractLength));
		
		return summary;
	}

	@Override
	public String getTitle(Locale locale) {
		 ResourceBundle resourceBundle = resourceBundleLoader.loadResourceBundle(locale);

		 return CourseUtil.getDisplayTitle(resourceBundle, course);
	}
	
	@Override
	public int getStatus() {
	    return course.getStatus();
	}

	/**
	 * Para mostrar los comentarios en los cursos
	 */
	@Override
	public String getDiscussionPath() {
	    if (GetterUtil.getBoolean(PropsUtil.get(LMSPropsKeys.COURSE_ENTRY_COMMENTS_ENABLED))) {
	        return "edit_entry_discussion";
	    }
	    else {
	        return null;
	    }
	}

	public boolean hasDeletePermission(PermissionChecker permissionChecker) throws SystemException, PortalException {
	    return CoursePermission.contains(
	        permissionChecker, course, ActionKeys.DELETE);
	}

	@Override
	public boolean hasEditPermission(PermissionChecker permissionChecker) throws PortalException {
	    return CoursePermission.contains(
	        permissionChecker, course, ActionKeys.UPDATE);
	}

	@Override
	public boolean hasViewPermission(PermissionChecker permissionChecker) throws PortalException {
	    return CoursePermission.contains(
	        permissionChecker, course, ActionKeys.VIEW);
	}
	
	@Override
	public String getJspPath(HttpServletRequest request, String template) {
	    if (template.equals(TEMPLATE_ABSTRACT) ||
	        template.equals(TEMPLATE_FULL_CONTENT)) {

	        return "/course/asset/" + template + ".jsp";
	    }
	    else {
	        return null;
	    }
	}
	
	@Override
	public boolean include(HttpServletRequest request, HttpServletResponse response, String template) throws Exception {

	    request.setAttribute(LMSKeys.COURSE, course);

	    return super.include(request, response, template);
	}
	
	@Override
	public PortletURL getURLEdit(LiferayPortletRequest liferayPortletRequest, LiferayPortletResponse liferayPortletResponse) throws Exception {

	    Group group = GroupLocalServiceUtil.fetchGroup(course.getGroupId());

	    PortletURL portletURL = PortalUtil.getControlPanelPortletURL(liferayPortletRequest, group, LMSPortletKeys.COURSE, 0, 0,PortletRequest.RENDER_PHASE);

	    portletURL.setParameter("mvcRenderCommandName", "/lms/edit_course");
	    portletURL.setParameter("courseId", String.valueOf(course.getCourseId()));

	    return portletURL;
	}
	
	@Override
	public String getURLView(LiferayPortletResponse liferayPortletResponse, WindowState windowState) throws Exception {
		
		Group courseGroup= GroupLocalServiceUtil.getGroup(course.getGroupCreatedId());
		
		PortletURL portletURL = liferayPortletResponse.createLiferayPortletURL(courseGroup.getDefaultPublicPlid(), StringPool.BLANK, PortletRequest.RENDER_PHASE);
		
		return portletURL.toString();
	}
	
	@Override
	public String getURLViewInContext(LiferayPortletRequest liferayPortletRequest, LiferayPortletResponse liferayPortletResponse, String noSuchEntryRedirect) throws Exception {
		
		ThemeDisplay themeDisplay = (ThemeDisplay)liferayPortletRequest.getAttribute(WebKeys.THEME_DISPLAY);
		Group courseGroup= GroupLocalServiceUtil.getGroup(course.getGroupCreatedId());
		return courseGroup.getDisplayURL(themeDisplay);
	}
	
}
