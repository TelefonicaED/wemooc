package com.ted.lms.web.internal.portlet;

import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.model.CourseResult;
import com.ted.lms.service.CourseResultLocalService;
import com.ted.lms.web.internal.configuration.MyCoursesPortletInstanceConfiguration;
import com.ted.lms.web.internal.display.context.MyCoursesPortletInstanceSettingsHelper;
import com.ted.lms.web.internal.util.MyCoursesUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Virginia Martín Agudo
 */
@Component(
	configurationPid = "com.ted.lms.web.internal.configuration.MyCoursesPortletInstanceConfiguration",
	immediate = true,
	property = {
		"com.liferay.portlet.add-default-resource=true",
		"com.liferay.portlet.display-category=category.wemooc",
		"com.liferay.portlet.instanceable=true",
		"com.liferay.portlet.preferences-owned-by-group=true",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/META-INF/resources/my_courses/view.jsp",
		"javax.portlet.name=" + LMSPortletKeys.MY_COURSES,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=user",
	},
	service = Portlet.class
)
public class MyCoursesPortlet extends MVCPortlet {
	
private static final Log log = LogFactoryUtil.getLog(InscriptionPortlet.class);
	
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
		ThemeDisplay themeDisplay  =(ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		try {
			PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();
			MyCoursesPortletInstanceConfiguration myCoursesPortletInstanceConfiguration = portletDisplay.getPortletInstanceConfiguration(MyCoursesPortletInstanceConfiguration.class);
		
			int pageDelta = GetterUtil.getInteger(myCoursesPortletInstanceConfiguration.pageDelta());
			
			int countTabShow = 0;
			
			if(myCoursesPortletInstanceConfiguration.coursesInProgress()) {
				countTabShow++;
			}
			if(myCoursesPortletInstanceConfiguration.coursesCompleted() || myCoursesPortletInstanceConfiguration.coursesExpired() || myCoursesPortletInstanceConfiguration.showCourses() == MyCoursesUtil.COURSE_COMPLETED_AND_EXPIRED) {
				countTabShow++;
			}else if(myCoursesPortletInstanceConfiguration.coursesCompleted() && myCoursesPortletInstanceConfiguration.coursesExpired()){
				countTabShow += 2;
			}
			
			long groupId = myCoursesPortletInstanceConfiguration.onlyGroup() ? themeDisplay.getScopeGroupId() : 0;
			
			if(countTabShow > 1) {
				String tabsNames = "";
				String tabSelected = ParamUtil.getString(renderRequest, "tabSelected");
				LinkedHashMap<String, SearchContainer<CourseResult>> coursesShowed = new LinkedHashMap<>(countTabShow);
				
				if(myCoursesPortletInstanceConfiguration.coursesInProgress()) {
					String coursesInProgressTab = "my-courses.in-progress";
					tabsNames += coursesInProgressTab + ",";
					
					SearchContainer<CourseResult> searchContainer = getSearchContainer(renderRequest, renderResponse, pageDelta, themeDisplay, true, 
							false, false, groupId);
					searchContainer.setId("inProgress");
					searchContainer.getIteratorURL().setParameter("tabSelected", coursesInProgressTab);
					coursesShowed.put(coursesInProgressTab, searchContainer);
				}
				
				if(myCoursesPortletInstanceConfiguration.coursesCompleted() || myCoursesPortletInstanceConfiguration.coursesExpired()) {
					if(myCoursesPortletInstanceConfiguration.showCourses() == MyCoursesUtil.COURSE_COMPLETED_AND_EXPIRED) {
						String coursesCompletedAndExpired = "my-courses.completed-and-expired";
						tabsNames += coursesCompletedAndExpired + ",";
						
						SearchContainer<CourseResult> searchContainer = getSearchContainer(renderRequest, renderResponse, pageDelta, themeDisplay, false, 
								myCoursesPortletInstanceConfiguration.coursesCompleted(), myCoursesPortletInstanceConfiguration.coursesExpired(), groupId);
						searchContainer.setId("completedAndExpired");
						searchContainer.getIteratorURL().setParameter("tabSelected", coursesCompletedAndExpired);
						coursesShowed.put(coursesCompletedAndExpired, searchContainer);
						
					}else {
						if(myCoursesPortletInstanceConfiguration.coursesCompleted()) {
							String coursesCompleted = "my-courses.completed";
							tabsNames += coursesCompleted + ",";
							
							SearchContainer<CourseResult> searchContainer = getSearchContainer(renderRequest, renderResponse, pageDelta, themeDisplay, false, 
									true, false, groupId);
							searchContainer.setId("completed");
							searchContainer.getIteratorURL().setParameter("tabSelected", coursesCompleted);
							coursesShowed.put(coursesCompleted, searchContainer);
						}
						if(myCoursesPortletInstanceConfiguration.coursesExpired()) {
							String coursesExpired = "my-courses.expired";
							tabsNames += coursesExpired + ",";
							
							SearchContainer<CourseResult> searchContainer = getSearchContainer(renderRequest, renderResponse, pageDelta, themeDisplay, false, 
									false, true, groupId);
							searchContainer.setId("expired");
							searchContainer.getIteratorURL().setParameter("tabSelected", coursesExpired);
							coursesShowed.put(coursesExpired, searchContainer);
						}
					}
				}
				
				renderRequest.setAttribute("tabsNames", tabsNames);
				renderRequest.setAttribute("coursesShowed", coursesShowed);
				renderRequest.setAttribute("tabSelected", tabSelected);
				
			}else {
				renderRequest.setAttribute("searchContainer", getSearchContainer(renderRequest, renderResponse, pageDelta, themeDisplay, 
						myCoursesPortletInstanceConfiguration.coursesInProgress(), myCoursesPortletInstanceConfiguration.coursesCompleted(), 
						myCoursesPortletInstanceConfiguration.coursesExpired(), groupId));
			}

			HttpServletRequest request = PortalUtil.getHttpServletRequest(renderRequest);
			MyCoursesPortletInstanceSettingsHelper myCoursesPortletInstanceSettingsHelper = new MyCoursesPortletInstanceSettingsHelper(request, myCoursesPortletInstanceConfiguration);
			
			Map<String, Object> contextObjects = new HashMap<>();
			
			contextObjects.put("myCoursesPortletInstanceConfiguration", myCoursesPortletInstanceConfiguration);
			contextObjects.put("myCoursesPortletInstanceSettingsHelper", myCoursesPortletInstanceSettingsHelper);
			
			renderRequest.setAttribute("myCoursesPortletInstanceConfiguration", myCoursesPortletInstanceConfiguration);
			renderRequest.setAttribute("myCoursesPortletInstanceSettingsHelper", myCoursesPortletInstanceSettingsHelper);
			renderRequest.setAttribute("contextObjects", contextObjects);
			
			super.doView(renderRequest, renderResponse);
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	private SearchContainer<CourseResult> getSearchContainer(RenderRequest renderRequest, RenderResponse renderResponse, int pageDelta, ThemeDisplay themeDisplay,
			boolean inProgress, boolean completed, boolean expired, long groupId) {
		PortletURL iteratorURL = renderResponse.createRenderURL();
		
		SearchContainer<CourseResult> searchContainer = new SearchContainer<CourseResult>(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, pageDelta, 
				iteratorURL, null, null);

		searchContainer.setDelta(pageDelta);
		searchContainer.setDeltaConfigurable(false);

		int total = courseResultLocalService.getMyCoursesCount(themeDisplay.getUserId(), inProgress,
				completed, expired,
				groupId);
		List<CourseResult> results = courseResultLocalService.getMyCourses(themeDisplay.getUserId(), inProgress,
				completed, expired,
				groupId, searchContainer.getStart(), searchContainer.getEnd(),
				null);
		
		System.out.println("total: " + total);

		searchContainer.setResults(results);
		searchContainer.setTotal(total);
		
		return searchContainer;
	}

/*	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		myCoursesPortletInstanceConfiguration = ConfigurableUtil.createConfigurable(
				MyCoursesPortletInstanceConfiguration.class, properties);
	}
	
	private volatile MyCoursesPortletInstanceConfiguration myCoursesPortletInstanceConfiguration;*/
	
	@Reference(unbind = "-")
	protected void setCourseResultLocalService(CourseResultLocalService courseResultLocalService) {
		this.courseResultLocalService = courseResultLocalService;
	}
	
	private CourseResultLocalService courseResultLocalService;
}