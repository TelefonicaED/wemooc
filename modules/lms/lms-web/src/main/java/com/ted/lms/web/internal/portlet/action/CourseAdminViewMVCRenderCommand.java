package com.ted.lms.web.internal.portlet.action;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.model.Course;
import com.ted.lms.service.CourseLocalServiceUtil;

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

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
		
		PortletURL iteratorURL = renderResponse.createRenderURL();
		
		SearchContainer<Course> searchContainer = new SearchContainer<Course>(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, SearchContainer.DEFAULT_DELTA, 
				iteratorURL, null, "no-courses");

		
		searchContainer.setResults(CourseLocalServiceUtil.getCourses(searchContainer.getStart(), searchContainer.getEnd()));
		searchContainer.setTotal(CourseLocalServiceUtil.getCoursesCount());
		
		renderRequest.setAttribute("searchContainer", searchContainer);
		
		return "/course_admin/view.jsp";
	}

}
