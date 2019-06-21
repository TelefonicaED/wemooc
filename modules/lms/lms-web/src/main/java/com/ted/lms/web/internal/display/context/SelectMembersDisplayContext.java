package com.ted.lms.web.internal.display.context;

import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemList;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.ViewTypeItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.ViewTypeItemList;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portlet.sitesadmin.search.UserSiteMembershipChecker;
import com.liferay.portlet.usersadmin.search.UserSearch;
import com.liferay.portlet.usersadmin.search.UserSearchTerms;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.model.Course;
import com.ted.lms.service.CourseLocalServiceUtil;
import com.ted.lms.web.internal.search.UserCourseMemberChecker;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

import javax.portlet.ActionRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

public class SelectMembersDisplayContext {
	public SelectMembersDisplayContext(HttpServletRequest request, RenderRequest renderRequest, RenderResponse renderResponse) {

		this.request = request;
		this.renderRequest = renderRequest;
		this.renderResponse = renderResponse;
	}

	public String getClearResultsURL() {
		PortletURL clearResultsURL = getPortletURL();

		clearResultsURL.setParameter("keywords", StringPool.BLANK);
		clearResultsURL.setParameter("courseId", ParamUtil.getString(renderRequest, "courseId"));
		clearResultsURL.setParameter("mvcRenderCommandName", "/courses/assign_members");

		return clearResultsURL.toString();
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
	
	public long getCourseId() {
		if(courseId > 0) {
			return courseId;
		}
		
		courseId = ParamUtil.getLong(renderRequest, "courseId");
		
		return courseId;
	}

	public String getKeywords() {
		if (keywords != null) {
			return keywords;
		}

		keywords = ParamUtil.getString(renderRequest, "keywords");

		return keywords;
	}
	
	public long getRoleId() {
		if(roleId > 0) {
			return roleId;
		}
		
		roleId = ParamUtil.getLong(renderRequest, "roleId");
		
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
		PortletURL portletURL = renderResponse.createRenderURL();

		portletURL.setParameter("mvcRenderCommandName", "/courses/assign_members");
		portletURL.setParameter("courseId", String.valueOf(getCourseId()));
		portletURL.setParameter("roleId", String.valueOf(getRoleId()));

		String displayStyle = getDisplayStyle();

		if (Validator.isNotNull(displayStyle)) {
			portletURL.setParameter("displayStyle", displayStyle);
		}

		String keywords = getKeywords();

		if (Validator.isNotNull(keywords)) {
			portletURL.setParameter("keywords", keywords);
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

		sortingURL.setParameter("orderByType",Objects.equals(getOrderByType(), "asc") ? "desc" : "asc");

		return sortingURL.toString();
	}

	public int getTotalItems() {
		int total = 0;
		try {
			SearchContainer<User> userSearchContainer = getUserSearchContainer();
			total = userSearchContainer.getTotal();
		} catch (PortalException e) {
			e.printStackTrace();
		}

		return total;
	}

	public SearchContainer<User> getUserSearchContainer() throws PortalException {
		if (userSearch != null) {
			return userSearch;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);

		UserSearch userSearch = new UserSearch(renderRequest, getPortletURL());
		
		Course course = CourseLocalServiceUtil.getCourse(getCourseId());

		Group group = GroupLocalServiceUtil.fetchGroup(course.getGroupCreatedId());

		userSearch.setRowChecker(new UserCourseMemberChecker(renderResponse, group, getRoleId()));

		UserSearchTerms searchTerms =(UserSearchTerms)userSearch.getSearchTerms();

		LinkedHashMap<String, Object> userParams = new LinkedHashMap<>();

		if (group.isLimitedToParentSiteMembers()) {
			userParams.put("inherit", Boolean.TRUE);
			userParams.put("usersGroups", Long.valueOf(group.getParentGroupId()));
		}

		int usersCount = UserLocalServiceUtil.searchCount(themeDisplay.getCompanyId(), searchTerms.getKeywords(),searchTerms.getStatus(), userParams);

		userSearch.setTotal(usersCount);

		List<User> users = UserLocalServiceUtil.search(themeDisplay.getCompanyId(), searchTerms.getKeywords(),
			searchTerms.getStatus(), userParams, userSearch.getStart(), userSearch.getEnd(), userSearch.getOrderByComparator());

		userSearch.setResults(users);

		this.userSearch = userSearch;

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
		if (getTotalItems() <= 0) {
			return true;
		}

		return false;
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
						dropdownItem.setActive(true);
						dropdownItem.setHref(getPortletURL());
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
	private String keywords;
	private String orderByCol;
	private String orderByType;
	private long roleId;
	private final RenderRequest renderRequest;
	private final RenderResponse renderResponse;
	private final HttpServletRequest request;
	private UserSearch userSearch;
}
