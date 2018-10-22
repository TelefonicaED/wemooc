package com.ted.lms.web.internal.portlet.action;

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.trash.TrashHelper;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.model.Course;
import com.ted.lms.service.CourseLocalServiceUtil;
import com.ted.lms.web.internal.display.context.CoursesManagementToolbarDisplayContext;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
		immediate = true,
		property = {
			"javax.portlet.name=" + LMSPortletKeys.COURSE,
			"mvc.command.name=/", "mvc.command.name=/courses/view"
		},
		service = MVCRenderCommand.class
	)
public class CourseAdminViewMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		
		String keywords = ParamUtil.getString(renderRequest, "keywords", null);
		
		boolean inputFiltersShowOptions = ParamUtil.getBoolean(renderRequest, "inputFiltersShowOptions", false);
		
		PortletURL portletURL = renderResponse.createRenderURL();

		portletURL.setParameter("mvcRenderCommandName", "/courses/view");
		
		LiferayPortletResponse liferayPortletResponse = PortalUtil.getLiferayPortletResponse(renderResponse);
		
		CoursesManagementToolbarDisplayContext coursesManagementToolbarDisplayContext = new CoursesManagementToolbarDisplayContext(
				PortalUtil.getLiferayPortletRequest(renderRequest), liferayPortletResponse, PortalUtil.getHttpServletRequest(renderRequest), 
				portletURL, trashHelper);
		
		PortletURL iteratorURL = renderResponse.createRenderURL();
		
		SearchContainer<Course> searchContainer = new SearchContainer<Course>(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, SearchContainer.DEFAULT_DELTA, 
				iteratorURL, null, "no-courses");

		
		searchContainer.setResults(CourseLocalServiceUtil.getCourses(searchContainer.getStart(), searchContainer.getEnd()));
		searchContainer.setTotal(CourseLocalServiceUtil.getCoursesCount());
		
		renderRequest.setAttribute("searchContainer", searchContainer);
		renderRequest.setAttribute("inputFiltersShowOptions", inputFiltersShowOptions);
		
		renderRequest.setAttribute("coursesManagementToolbarDisplayContext", coursesManagementToolbarDisplayContext);
		
		return "/course_admin/view.jsp";
	}
	
	@Reference
	private TrashHelper trashHelper;

}
