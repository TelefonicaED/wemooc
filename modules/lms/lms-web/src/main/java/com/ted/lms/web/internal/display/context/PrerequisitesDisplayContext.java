package com.ted.lms.web.internal.display.context;

import com.liferay.frontend.taglib.clay.servlet.taglib.util.CreationMenu;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemList;
import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.ted.lms.model.Course;
import com.ted.prerequisite.model.PrerequisiteFactory;
import com.ted.prerequisite.model.PrerequisiteRelation;
import com.ted.prerequisite.service.PrerequisiteRelationLocalServiceUtil;

import java.util.HashMap;
import java.util.List;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

public class PrerequisitesDisplayContext {
	public PrerequisitesDisplayContext(HttpServletRequest request, RenderRequest renderRequest,
			RenderResponse renderResponse, List<PrerequisiteFactory> prerequisiteFactories) {

		this.request = request;
		this.renderRequest = renderRequest;
		this.renderResponse = renderResponse;
		this.prerequisiteFactories = prerequisiteFactories;
	}

	public List<DropdownItem> getActionDropdownItems() throws PortalException {

		return new DropdownItemList() {
			{
				add(
					dropdownItem -> {
						dropdownItem.putData("action", "deleteSelectedPrerequisites");
						dropdownItem.setIcon("times-circle");
						dropdownItem.setLabel(LanguageUtil.get(request, "delete"));
						dropdownItem.setQuickAction(true);
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
	
	public String getTab() {
		if (tab != null) {
			return tab;
		}
		
		tab = ParamUtil.getString(renderRequest, "tabs", "prerequisites");
		
		return tab;
	}
	
	public CreationMenu getCreationMenu() {
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);

		CreationMenu creationMenu = new CreationMenu();
		
		prerequisiteFactories.forEach(prerequisiteFactory -> {
			HashMap<String, Object> data = new HashMap<>();
			data.put("className", prerequisiteFactory.getClassName());
			creationMenu.addDropdownItem(
					dropdownItem -> {
						dropdownItem.setLabel(prerequisiteFactory.getTitle(themeDisplay.getLocale()));
						dropdownItem.setData(data);
					});
		});
		return creationMenu;
	}
	
	public PortletURL getPortletURL() {
		PortletURL portletURL = renderResponse.createRenderURL();
		portletURL.setParameter("mvcRenderCommandName", "/courses/prerequisite_postcondition_course");
		portletURL.setParameter("courseId", String.valueOf(getCourseId()));
		portletURL.setParameter("redirect", ParamUtil.getString(renderRequest, "redirect"));
		
		return portletURL;
	}

	public int getTotalItems() {
		int total = 0;
		try {
			SearchContainer<PrerequisiteRelation> searchContainer = getSearchContainer();
			total = searchContainer.getTotal();
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return total;
	}

	public SearchContainer<PrerequisiteRelation> getSearchContainer() throws PortalException {
		if (searchContainer != null) {
			return searchContainer;
		}
		
		searchContainer = new SearchContainer<PrerequisiteRelation>(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, 
				SearchContainer.DEFAULT_DELTA, getPortletURL(), null, "no-prerequisites");
		
		searchContainer.setRowChecker(new EmptyOnClickRowChecker(renderResponse));
		
		long classNameId = PortalUtil.getClassNameId(Course.class);
		List<PrerequisiteRelation> prerequisites = PrerequisiteRelationLocalServiceUtil.getPrerequisiteRelations(classNameId, courseId, 
				searchContainer.getStart(), searchContainer.getEnd());
		int total = PrerequisiteRelationLocalServiceUtil.getPrerequisiteRelationsCount(classNameId, courseId);
		
		log.debug("prerequisites: " + prerequisites.size());
		log.debug("total: " + total);
		
		searchContainer.setResults(prerequisites);
		searchContainer.setTotal(total);
		
		return searchContainer;
	}

	public boolean isDisabledManagementBar() {
		if (getTotalItems() > 0) {
			return false;
		}

		return true;
	}

	private long courseId;
	private String tab;
	private final RenderRequest renderRequest;
	private final RenderResponse renderResponse;
	private final HttpServletRequest request;
	private SearchContainer<PrerequisiteRelation> searchContainer;
	private List<PrerequisiteFactory> prerequisiteFactories;
}
