package com.ted.lms.web.internal.portlet.action;

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.model.CourseType;
import com.ted.lms.service.CourseTypeLocalService;
import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	configurationPid = "com.ted.lms.web.internal.configuration.CourseAdminPortletInstanceConfiguration",
	immediate = true,
	property = {
		"javax.portlet.name=" + LMSPortletKeys.COURSE_TYPE,
		"mvc.command.name=/", "mvc.command.name=/course_type/view"
	},
	service = MVCRenderCommand.class
)
public class CourseTypeViewMVCRenderCommand implements MVCRenderCommand {
	
	private static final Log log = LogFactoryUtil.getLog(CourseTypeViewMVCRenderCommand.class);

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		String redirect = ParamUtil.getString(renderRequest, "redirect");
		log.debug("CourseTypeViewMVCRenderCommand redirect: " + ParamUtil.getString(renderRequest, "redirect"));
		
		PortletURL iteratorURL = renderResponse.createRenderURL();
		iteratorURL.setParameter("mvcRenderCommandName", "/course_type/view");
		iteratorURL.setParameter("redirect", redirect);
		
		SearchContainer<CourseType> searchContainer = new SearchContainer<CourseType>(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, 
				SearchContainer.DEFAULT_DELTA, iteratorURL, null, "no-course-types");

		searchContainer.setResults(courseTypeLocalService.getCourseTypes(themeDisplay.getCompanyId(), searchContainer.getStart(), searchContainer.getEnd()));
		searchContainer.setTotal(courseTypeLocalService.countCourseTypes(themeDisplay.getCompanyId()));
		
		log.debug("total: " + searchContainer.getTotal());
		
		renderRequest.setAttribute("searchContainer", searchContainer);
		
		PortletURL addCourseTypeURL = renderResponse.createRenderURL();
		addCourseTypeURL.setParameter("mvcRenderCommandName", "/course_type/edit_course_type");
		addCourseTypeURL.setParameter("redirect", redirect);
		renderRequest.setAttribute("addCourseTypeURL", addCourseTypeURL);
		
		return "/course_type/view.jsp";
	}
	
	@Reference(unbind = "-")
	protected void setCourseTypeLocalService(CourseTypeLocalService courseTypeLocalService) {
		this.courseTypeLocalService = courseTypeLocalService;
	}
	
	private CourseTypeLocalService courseTypeLocalService;

}
