package com.ted.lms.web.internal.display.context;

import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemList;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portlet.usersadmin.search.UserSearch;
import com.liferay.portlet.usersadmin.search.UserSearchTerms;
import com.ted.lms.model.Course;
import com.ted.lms.model.Module;
import com.ted.lms.service.CourseLocalService;
import com.ted.lms.service.ModuleService;
import com.ted.lms.service.StudentLocalService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

public class EditResultsDisplayContext {
	
	private Course course;
	private long courseId;
	private StudentLocalService studentLocalService;
	private String keywords;
	private String orderByCol;
	private String orderByType;
	private final RenderRequest renderRequest;
	private final RenderResponse renderResponse;
	private final HttpServletRequest request;
	private List<UserSearch> userSearch;
	private ModuleService moduleService;
	
	public EditResultsDisplayContext(HttpServletRequest request, RenderRequest renderRequest,
			RenderResponse renderResponse, CourseLocalService courseLocalService, StudentLocalService studentLocalService,
			ModuleService moduleService) throws PortalException {

		this.request = request;
		this.renderRequest = renderRequest;
		this.renderResponse = renderResponse;
		this.studentLocalService = studentLocalService;
		this.moduleService = moduleService;
		
		courseId = ParamUtil.getLong(renderRequest, "courseId", 0);
		
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
		
		if(courseId == 0) {
			course = courseLocalService.getCourseByGroupCreatedId(themeDisplay.getScopeGroupId());
			courseId = course.getCourseId();
		}else {
			course = courseLocalService.getCourse(courseId);
		}
	}

	public String getClearResultsURL() throws PortalException {
		PortletURL clearResultsURL = getPortletURL();

		clearResultsURL.setParameter("keywords", StringPool.BLANK);
		clearResultsURL.setParameter("mvcRenderCommandName", "/results/edit_resutls");
		clearResultsURL.setParameter("courseId", String.valueOf(getCourseId()));

		return clearResultsURL.toString();
	}
	
	public long getCourseId() {
		return courseId;
	}

	public List<DropdownItem> getFilterDropdownItems() {
		return new DropdownItemList() {
			{
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

	public PortletURL getPortletURL() throws PortalException {
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);

		PortletURL portletURL = renderResponse.createRenderURL();

		portletURL.setParameter("mvcRenderCommandName", "/results/edit_results");
		portletURL.setParameter("courseId", String.valueOf(getCourseId()));
		portletURL.setParameter("redirect", ParamUtil.getString(renderRequest, "redirect"));

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

	public String getSearchActionURL() throws PortalException {
		PortletURL searchActionURL = getPortletURL();

		return searchActionURL.toString();
	}

	public String getSortingURL() throws PortalException {
		PortletURL sortingURL = getPortletURL();

		sortingURL.setParameter(
			"orderByType",
			Objects.equals(getOrderByType(), "asc") ? "desc" : "asc");

		return sortingURL.toString();
	}

	public int getTotalItems() {
		int total = 0;
		try {
			SearchContainer userSearchContainer = getUserSearchContainer(0);
			total = userSearchContainer.getTotal();
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return total;
	}

	public SearchContainer getUserSearchContainer(int count) throws PortalException {
		if (userSearch != null) {
			return userSearch.get(count);
		}
		
		this.userSearch = new ArrayList<UserSearch>();
		
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		List<Module> modules = moduleService.getGroupModules(getCourse().getGroupCreatedId());
		
		for(Module module: modules) {
	
			UserSearch userSearch = new UserSearch(renderRequest, getPortletURL());
	
			userSearch.setEmptyResultsMessage("no-students");
			userSearch.setId("editResults_" + module.getModuleId());
	
			UserSearchTerms searchTerms = (UserSearchTerms)userSearch.getSearchTerms();
	
			int usersCount = studentLocalService.countStudentsFromCourse(getCourseId(), themeDisplay.getCompanyId(), searchTerms.getKeywords(), WorkflowConstants.STATUS_APPROVED, null);
	
			userSearch.setTotal(usersCount);
	
			List<User> users = studentLocalService.getStudentsFromCourse(getCourseId(), themeDisplay.getCompanyId(), searchTerms.getKeywords(), WorkflowConstants.STATUS_APPROVED, null, 
					userSearch.getStart(),
					userSearch.getEnd(), userSearch.getOrderByComparator()); 
	
			userSearch.setResults(users);
			
			this.userSearch.add(userSearch);
		}
		
		return userSearch.get(count);
	}

	public boolean isDisabledManagementBar() {
		if (getTotalItems() > 0) {
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

	public Course getCourse() throws PortalException {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
}
