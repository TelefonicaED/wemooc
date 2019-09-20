package com.ted.lms.web.internal.portlet.action;

import com.liferay.asset.kernel.service.AssetTagService;
import com.liferay.asset.kernel.service.AssetVocabularyService;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProviderUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.trash.TrashHelper;
import com.ted.lms.configuration.CourseServiceConfiguration;
import com.ted.lms.constants.CourseConstants;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.model.Course;
import com.ted.lms.service.CourseLocalService;
import com.ted.lms.web.internal.configuration.CourseAdminPortletInstanceConfiguration;
import com.ted.lms.web.internal.display.context.CourseDisplayContext;
import com.ted.lms.web.internal.display.context.CoursesManagementToolbarDisplayContext;
import com.ted.postcondition.model.PostconditionFactory;
import com.ted.postcondition.registry.PostconditionFactoryRegistryUtil;
import com.ted.prerequisite.model.PrerequisiteFactory;
import com.ted.prerequisite.registry.PrerequisiteFactoryRegistryUtil;

import java.util.ArrayList;
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
	
	private static final Log log = LogFactoryUtil.getLog(CoursesViewMVCRenderCommand.class);

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		log.debug("CoursesViewMVCRenderCommand redirect: " + ParamUtil.getString(renderRequest, "redirect"));
		
		//Si recibo un courseId es porque estoy en ediciones
		long parentCourseId = ParamUtil.getLong(renderRequest, "parentCourseId", CourseConstants.DEFAULT_PARENT_COURSE_ID);
		
		String criteria = ParamUtil.getString(renderRequest, "criteria");
		log.debug("render criteria:_ " + criteria);
		
		String keywords = ParamUtil.getString(renderRequest, "keywords", null);
		int status = ParamUtil.getInteger(renderRequest, "status");
		
		log.debug("status: " + status);
		
		int[] statusArray = null;
		if(status == WorkflowConstants.STATUS_APPROVED) {
			statusArray = new int[]{WorkflowConstants.STATUS_APPROVED, WorkflowConstants.STATUS_DRAFT};
		}else if(status != WorkflowConstants.STATUS_ANY){
			statusArray = new int[] {status};
		}
		
		log.debug("keywords: " + keywords);
		
		boolean inputFiltersShowOptions = ParamUtil.getBoolean(renderRequest, "inputFiltersShowOptions", false);
		
		String redirect = ParamUtil.getString(renderRequest, "redirect");
		
		PortletURL portletURL = renderResponse.createRenderURL();

		portletURL.setParameter("mvcRenderCommandName", "/courses/view");
		portletURL.setParameter("parentCourseId", String.valueOf(parentCourseId));
		portletURL.setParameter("redirect", redirect);
		
		long groupId = ParamUtil.getLong(renderRequest, "groupId", themeDisplay.getScopeGroupId());
		
		LiferayPortletResponse liferayPortletResponse = PortalUtil.getLiferayPortletResponse(renderResponse);
		
		CoursesManagementToolbarDisplayContext coursesManagementToolbarDisplayContext = new CoursesManagementToolbarDisplayContext(
				PortalUtil.getLiferayPortletRequest(renderRequest), liferayPortletResponse, PortalUtil.getHttpServletRequest(renderRequest), 
				portletURL, trashHelper);
		
		PortletURL iteratorURL = renderResponse.createRenderURL();
		
		SearchContainer<Course> searchContainer = new SearchContainer<Course>(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, SearchContainer.DEFAULT_DELTA, 
				iteratorURL, null, "no-courses");
		
		List<Course> courses = courseLocalService.searchCourses(themeDisplay.getCompanyId(), keywords, themeDisplay.getLanguageId(), statusArray, 
				parentCourseId, groupId, null, searchContainer.getStart(), searchContainer.getEnd(), 
				searchContainer.getOrderByComparator());
		int countCourses = courseLocalService.countCourses(themeDisplay.getCompanyId(), keywords, themeDisplay.getLanguageId(), statusArray, 
				parentCourseId, groupId, null);
		searchContainer.setResults(courses);
		searchContainer.setTotal(countCourses);
		
		log.debug("total: " + searchContainer.getTotal());
		
		if(parentCourseId != CourseConstants.DEFAULT_PARENT_COURSE_ID) {
			Course course = courseLocalService.fetchCourse(parentCourseId);
			renderRequest.setAttribute("course", course);
			
			PortletURL importEditionsURL = renderResponse.createRenderURL();
			importEditionsURL.setWindowState(LiferayWindowState.POP_UP);
			importEditionsURL.setParameter("parentCourseId", String.valueOf(parentCourseId));
			importEditionsURL.setParameter("mvcRenderCommandName", "/courses/import_editions");
			renderRequest.setAttribute("importEditionsURL", importEditionsURL);
		}
		
		renderRequest.setAttribute("searchContainer", searchContainer);
		renderRequest.setAttribute("inputFiltersShowOptions", inputFiltersShowOptions);
		renderRequest.setAttribute("portletURL", portletURL);
		renderRequest.setAttribute("redirect", redirect);
		
		renderRequest.setAttribute("coursesManagementToolbarDisplayContext", coursesManagementToolbarDisplayContext);
		
		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();
		
		try {
			CourseAdminPortletInstanceConfiguration configuration = portletDisplay.getPortletInstanceConfiguration(CourseAdminPortletInstanceConfiguration.class);
			
			HttpServletRequest request = portal.getHttpServletRequest(renderRequest);
			
			CourseServiceConfiguration courseServiceConfiguration = ConfigurationProviderUtil.getCompanyConfiguration(
							CourseServiceConfiguration.class, CompanyThreadLocal.getCompanyId());
			
			CourseDisplayContext courseDisplayContext = new CourseDisplayContext(request, renderRequest, renderResponse, 
					renderRequest.getPreferences(), configuration, trashHelper, courseServiceConfiguration);
			renderRequest.setAttribute("courseDisplayContext", courseDisplayContext);
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		
		//Mandamos los prerequisitos y las postcondiciones si las hay para mostrarlas en el menú de acciones
		String[] classNamePrerequisites = courseLocalService.getPrerequisiteCourses(themeDisplay.getCompanyId());
		
		PrerequisiteFactory prerequisiteFactory = null;
		
		List<PrerequisiteFactory> prerequisiteFactories = new ArrayList<PrerequisiteFactory>();
		
		for(String classNamePrerequisite: classNamePrerequisites){
			prerequisiteFactory = PrerequisiteFactoryRegistryUtil.getPrerequisiteFactoryByClassName(classNamePrerequisite);
			if(prerequisiteFactory != null) {
				prerequisiteFactories.add(prerequisiteFactory);
			}
		}
		
		String[] classNamePostconditions = courseLocalService.getPostconditionCourses(themeDisplay.getCompanyId());
		
		PostconditionFactory postconditionFactory = null;
		
		List<PostconditionFactory> postconditionFactories = new ArrayList<PostconditionFactory>();
		
		for(String classNamePostcondition: classNamePostconditions){
			postconditionFactory = PostconditionFactoryRegistryUtil.getPostconditionFactoryByClassName(classNamePostcondition);
			if(postconditionFactory != null) {
				postconditionFactories.add(postconditionFactory);
			}
		}
		
		renderRequest.setAttribute("showPrerequisitePostcondition", prerequisiteFactories.size() > 0 || postconditionFactories.size() > 0);
		
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
