package com.ted.lms.web.internal.display.context;

import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemList;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.ViewTypeItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.ViewTypeItemList;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portlet.usersadmin.search.UserSearch;
import com.liferay.portlet.usersadmin.search.UserSearchTerms;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.constants.LMSRoleConstants;
import com.ted.lms.model.Course;
import com.ted.lms.service.CourseLocalServiceUtil;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

import javax.portlet.ActionRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

public class CourseMembersDisplayContext {
	public CourseMembersDisplayContext(HttpServletRequest request, RenderRequest renderRequest,
			RenderResponse renderResponse) {

		this.request = request;
		this.renderRequest = renderRequest;
		this.renderResponse = renderResponse;
	}

	public List<DropdownItem> getActionDropdownItems() throws PortalException {

		return new DropdownItemList() {
			{
				add(
					dropdownItem -> {
						dropdownItem.putData("action", "deleteSelectedUsers");
						dropdownItem.setIcon("times-circle");
						dropdownItem.setLabel(LanguageUtil.get(request, "delete"));
						dropdownItem.setQuickAction(true);
					});
			}
		};
	}

	public String getClearResultsURL() {
		PortletURL clearResultsURL = getPortletURL();

		clearResultsURL.setParameter("keywords", StringPool.BLANK);
		clearResultsURL.setParameter("mvcRenderCommandName", "/courses/view_members");
		clearResultsURL.setParameter("courseId", String.valueOf(getCourseId()));
		clearResultsURL.setParameter("roleId", String.valueOf(getRoleId()));

		return clearResultsURL.toString();
	}
	
	public long getCourseId() {
		if(courseId > 0) {
			return courseId;
		}
		
		courseId = ParamUtil.getLong(renderRequest, "courseId");
		
		return courseId;
	}
	
	public String getTab() {
		if (tab != null) {
			return tab;
		}
		
		tab = ParamUtil.getString(renderRequest, "tabs", "students");
		
		return tab;
	}

	public String getDisplayStyle() {
		if (Validator.isNotNull(displayStyle)) {
			return displayStyle;
		}

		PortalPreferences portalPreferences = PortletPreferencesFactoryUtil.getPortalPreferences(request);

		displayStyle = portalPreferences.getValue(LMSPortletKeys.COURSE, "display-style","icon");

		return displayStyle;
	}

	public List<DropdownItem> getFilterDropdownItems() {
		return new DropdownItemList() {
			{
				addGroup(
					dropdownGroupItem -> {
						dropdownGroupItem.setDropdownItems(getFilterTeamsDropdownItems());
						dropdownGroupItem.setLabel(LanguageUtil.get(request, "filter-by-teams"));
					});

				addGroup(
					dropdownGroupItem -> {
						dropdownGroupItem.setDropdownItems(getOrderByDropdownItems());
						dropdownGroupItem.setLabel(LanguageUtil.get(request, "order-by"));
					});
			}
		};
	}

	public String getKeywords() {
		if (keywords != null) {
			return keywords;
		}

		keywords = ParamUtil.getString(renderRequest, "keywords");

		return keywords;
	}

	public long getTeamId() {
		if (teamId > 0) {
			return teamId;
		}

		teamId = ParamUtil.getLong(request, "teamId", 0);

		return teamId;
	}
	
	public long getRoleId() {
		if (roleId > 0) {
			return roleId;
		}
		
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
		
		try {
			roleId = ParamUtil.getLong(renderRequest, "roleId", RoleLocalServiceUtil.getRole(themeDisplay.getCompanyId(), LMSRoleConstants.STUDENT).getRoleId());
		} catch (PortalException e) {
			e.printStackTrace();
		}

		return roleId;
	}

	public String getOrderByCol() {
		if (orderByCol != null) {
			return orderByCol;
		}

		orderByCol = ParamUtil.getString(renderRequest, "orderByCol", "first-name");

		return orderByCol;
	}

	public String getOrderByType() {
		if (orderByType != null) {
			return orderByType;
		}

		orderByType = ParamUtil.getString(renderRequest, "orderByType", "asc");

		return orderByType;
	}

	public PortletURL getPortletURL() {
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);

		PortletURL portletURL = renderResponse.createRenderURL();

		portletURL.setParameter("mvcRenderCommandName", "/courses/view_members");
		portletURL.setParameter("courseId", String.valueOf(getCourseId()));
		portletURL.setParameter("tabs", getTab());
		portletURL.setParameter("redirect", themeDisplay.getURLCurrent());
		portletURL.setParameter("roleId", String.valueOf(getRoleId()));
		
		String displayStyle = getDisplayStyle();

		if (Validator.isNotNull(displayStyle)) {
			portletURL.setParameter("displayStyle", displayStyle);
		}

		String keywords = getKeywords();

		if (Validator.isNotNull(keywords)) {
			portletURL.setParameter("keywords", keywords);
		}

		long teamId = getTeamId();

		if (teamId > 0) {
			portletURL.setParameter("teamId", String.valueOf(teamId));
		}

		String orderByCol = getOrderByCol();

		if (Validator.isNotNull(orderByCol)) {
			portletURL.setParameter("orderByCol", orderByCol);
		}

		String orderByType = getOrderByType();

		if (Validator.isNotNull(orderByType)) {
			portletURL.setParameter("orderByType", orderByType);
		}

		return portletURL;
	}

	public String getSearchActionURL() {
		PortletURL searchActionURL = getPortletURL();

		return searchActionURL.toString();
	}

	public String getSortingURL() {
		PortletURL sortingURL = getPortletURL();

		sortingURL.setParameter(
			"orderByType",
			Objects.equals(getOrderByType(), "asc") ? "desc" : "asc");

		return sortingURL.toString();
	}

	public int getTotalItems() {
		int total = 0;
		try {
			SearchContainer userSearchContainer = getUserSearchContainer();
			total = userSearchContainer.getTotal();
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return total;
	}

	public SearchContainer getUserSearchContainer() throws PortalException {
		if (userSearch != null) {
			return userSearch;
		}
		
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		UserSearch userSearch = new UserSearch(renderRequest, getPortletURL());

		userSearch.setEmptyResultsMessage("no-user-was-found-that-is-a-direct-member-of-this-site");
		userSearch.setRowChecker(new EmptyOnClickRowChecker(renderResponse));

		UserSearchTerms searchTerms = (UserSearchTerms)userSearch.getSearchTerms();

		LinkedHashMap<String, Object> userParams = new LinkedHashMap<>();
		
		Course course = CourseLocalServiceUtil.getCourse(getCourseId());

		userParams.put("usersGroups", Long.valueOf(course.getGroupCreatedId()));
		userParams.put("userGroupRole",new Long[] {Long.valueOf(course.getGroupCreatedId()), Long.valueOf(getRoleId())});

		int usersCount = UserLocalServiceUtil.searchCount(
			themeDisplay.getCompanyId(), searchTerms.getKeywords(),
			searchTerms.getStatus(), userParams);

		userSearch.setTotal(usersCount);

		List<User> users = UserLocalServiceUtil.search(
			themeDisplay.getCompanyId(), searchTerms.getKeywords(),
			searchTerms.getStatus(), userParams, userSearch.getStart(),
			userSearch.getEnd(), userSearch.getOrderByComparator());

		userSearch.setResults(users);
		
		return userSearch;
	}

	public List<ViewTypeItem> getViewTypeItems() {
		PortletURL portletURL = renderResponse.createActionURL();

		portletURL.setParameter(ActionRequest.ACTION_NAME, "changeDisplayStyle");
		portletURL.setParameter("redirect", PortalUtil.getCurrentURL(request));

		return new ViewTypeItemList(portletURL, getDisplayStyle()) {
			{
				addCardViewTypeItem();
				addListViewTypeItem();
				addTableViewTypeItem();
			}
		};
	}

	public boolean isDisabledManagementBar() {
		if (getTotalItems() > 0) {
			return false;
		}

		if (getTeamId() > 0) {
			return false;
		}

		return true;
	}

	public boolean isShowSearch() {
		if (getTotalItems() > 0) {
			return true;
		}

		if (Validator.isNotNull(getKeywords())) {
			return true;
		}

		return false;
	}

	private List<DropdownItem> getFilterTeamsDropdownItems() {

		return new DropdownItemList() {
			{
				add(
					dropdownItem -> {
						dropdownItem.setActive(getTeamId() > 0);
						dropdownItem.setHref(getPortletURL(), "team", "all", "teamId","0");
						dropdownItem.setLabel(LanguageUtil.get(request, "all"));
					});
			}
		};
	}

	private List<DropdownItem> getOrderByDropdownItems() {
		return new DropdownItemList() {
			{
				add(
					dropdownItem -> {
						dropdownItem.setActive(
							Objects.equals(getOrderByCol(), "first-name"));
						dropdownItem.setHref(
							getPortletURL(), "orderByCol", "first-name");
						dropdownItem.setLabel(
							LanguageUtil.get(request, "first-name"));
					});

				add(
					dropdownItem -> {
						dropdownItem.setActive(
							Objects.equals(getOrderByCol(), "screen-name"));
						dropdownItem.setHref(
							getPortletURL(), "orderByCol", "screen-name");
						dropdownItem.setLabel(
							LanguageUtil.get(request, "screen-name"));
					});
			}
		};
	}

	private String displayStyle;
	private long courseId;
	private String tab;
	private String keywords;
	private long teamId;
	private long roleId;
	private String orderByCol;
	private String orderByType;
	private final RenderRequest renderRequest;
	private final RenderResponse renderResponse;
	private final HttpServletRequest request;
	private UserSearch userSearch;
}
