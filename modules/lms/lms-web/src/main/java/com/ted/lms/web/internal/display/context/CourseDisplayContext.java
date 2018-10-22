package com.ted.lms.web.internal.display.context;

import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.trash.TrashHelper;
import com.ted.lms.model.Course;

import javax.portlet.PortletPreferences;

import javax.servlet.http.HttpServletRequest;

public class CourseDisplayContext {
	
	public CourseDisplayContext(HttpServletRequest request, LiferayPortletRequest liferayPortletRequest,LiferayPortletResponse liferayPortletResponse,
		PortletPreferences portletPreferences, TrashHelper trashHelper) {

		this.liferayPortletRequest = liferayPortletRequest;
		this.liferayPortletResponse = liferayPortletResponse;
		this.portletPreferences = portletPreferences;
		this.trashHelper = trashHelper;
		this.request = request;

		//this.courseWebConfiguration = (CourseWebConfiguration)this.request.getAttribute(CourseWebConfiguration.class.getName());
		this.portalPreferences = PortletPreferencesFactoryUtil.getPortalPreferences(this.request);
	}
	
	public String getFriendlyURLBase() {
		StringBundler sb = new StringBundler(4);

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
		sb.append(themeDisplay.getPortalURL());

		Group group = themeDisplay.getScopeGroup();

		sb.append(group.getPathFriendlyURL(false, themeDisplay));
		System.out.println(group.getFriendlyURL());

		//sb.append(CourseConstants.CANONICAL_URL_SEPARATOR);

		return sb.toString();
	}
	
	private String[] _addMenuFavItems;
	private Course course;
	private String displayStyle;
	private String[] displayViews;
	private String keywords;
	private final LiferayPortletRequest liferayPortletRequest;
	private final LiferayPortletResponse liferayPortletResponse;
	private String navigation;
	private String orderByCol;
	private String orderByType;
	private final PortalPreferences portalPreferences;
	private final PortletPreferences portletPreferences;
	private Integer status;
	private final TrashHelper trashHelper;
	private final HttpServletRequest request;
	//private final CourseWebConfiguration courseWebConfiguration;
}
