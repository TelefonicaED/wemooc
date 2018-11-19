package com.ted.lms.web.internal.portlet.action;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryService;
import com.liferay.asset.kernel.service.AssetTagService;
import com.liferay.asset.kernel.service.AssetVocabularyService;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.trash.TrashHelper;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.model.Course;
import com.ted.lms.service.CourseLocalService;
import com.ted.lms.service.CourseLocalServiceUtil;
import com.ted.lms.web.internal.configuration.CourseAdminPortletInstanceConfiguration;
import com.ted.lms.web.internal.display.context.CourseDisplayContext;
import com.ted.lms.web.internal.display.context.CoursesManagementToolbarDisplayContext;

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	configurationPid = "com.ted.lms.web.internal.configuration.CourseAdminPortletInstanceConfiguration",
	immediate = true,
	property = {
		"javax.portlet.name=" + LMSPortletKeys.COURSE,
		"mvc.command.name=/", "mvc.command.name=/courses/view"
	},
	service = MVCRenderCommand.class
)
public class CoursesViewMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		String keywords = ParamUtil.getString(renderRequest, "keywords", null);
		
		boolean inputFiltersShowOptions = ParamUtil.getBoolean(renderRequest, "inputFiltersShowOptions", false);
		
		PortletURL portletURL = renderResponse.createRenderURL();

		portletURL.setParameter("mvcRenderCommandName", "/courses/view");
		
		long[] groupIds = {themeDisplay.getScopeGroupId()};
		
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
		
		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();
		
		try {
			CourseAdminPortletInstanceConfiguration configuration = portletDisplay.getPortletInstanceConfiguration(CourseAdminPortletInstanceConfiguration.class);
			
			HttpServletRequest request = portal.getHttpServletRequest(renderRequest);
			
			CourseDisplayContext courseDisplayContext = new CourseDisplayContext(request, renderRequest, renderResponse, renderRequest.getPreferences(), configuration, trashHelper);
			renderRequest.setAttribute("courseDisplayContext", courseDisplayContext);
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		
		return "/course_admin/view.jsp";
	}
	
	@Reference(unbind = "-")
	protected void setCourseLocalService(CourseLocalService courseLocalService) {
		this.courseLocalService = courseLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setAssetTagService(AssetTagService assetTagService) {
		this.assetTagService = assetTagService;
	}
	
	@Reference(unbind = "-")
	protected void setAssetVocabularyService(AssetVocabularyService assetVocabularyService) {
		this.assetVocabularyService = assetVocabularyService;
	}
	
	private CourseLocalService courseLocalService;
	private AssetTagService assetTagService;
	private AssetVocabularyService assetVocabularyService;
	
	@Reference
	private TrashHelper trashHelper;
	
	@Reference
	private Portal portal;

}
