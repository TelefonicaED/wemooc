package com.ted.lms.learning.activity.test.web.internal.portlet.action;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.util.comparator.UserFirstNameComparator;
import com.liferay.portal.kernel.util.comparator.UserLastNameComparator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.ted.lms.learning.activity.test.web.activity.TestActivityType;
import com.ted.lms.learning.activity.test.web.activity.TestActivityTypeFactory;
import com.ted.lms.learning.activity.test.web.constants.TestConstants;
import com.ted.lms.learning.activity.test.web.constants.TestPortletKeys;
import com.ted.lms.model.CalificationType;
import com.ted.lms.model.CalificationTypeFactory;
import com.ted.lms.model.Course;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.registry.CalificationTypeFactoryRegistryUtil;
import com.ted.lms.registry.LearningActivityTypeFactoryRegistryUtil;
import com.ted.lms.service.CourseLocalService;
import com.ted.lms.service.LearningActivityLocalService;
import com.ted.lms.service.StudentLocalService;
import com.ted.lms.util.LMSPrefsPropsValues;

import java.util.ArrayList;
import java.util.List;
import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowStateException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true, 
	property = { "javax.portlet.name=" + TestPortletKeys.TEST,
			"mvc.command.name=/activities/test/correction" }, 
	service = MVCRenderCommand.class
)
public class TestActivityCorrectionMVCRenderCommand implements MVCRenderCommand {
	
	private static final Log log = LogFactoryUtil.getLog(TestActivityCorrectionMVCRenderCommand.class);

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		
		long actId = ParamUtil.getLong(renderRequest, "actId", 0);

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		Course course = courseLocalService.getCourseByGroupCreatedId(themeDisplay.getScopeGroupId());
		
		ParamUtil.print(renderRequest);

		String firstName = ParamUtil.getString(renderRequest, "firstName","");
		String lastName = ParamUtil.getString(renderRequest, "lastName","");
		String screenName = ParamUtil.getString(renderRequest, "screenName","");
		String emailAddress = ParamUtil.getString(renderRequest, "emailAddress","");
		
		log.debug("firstName: " + firstName);
		
		LiferayPortletResponse liferayPortletResponse = PortalUtil.getLiferayPortletResponse(renderResponse);
		
		LiferayPortletURL searchURL = liferayPortletResponse.createRenderURL(TestPortletKeys.TEST);
		searchURL.setParameter("mvcRenderCommandName", "/activities/test/correction");
		searchURL.setParameter("actId",Long.toString(actId));
		
		renderRequest.setAttribute("searchURL", searchURL.toString());
		
		OrderByComparator obc = null;
		if(LMSPrefsPropsValues.getUsersFirstLastName(themeDisplay.getCompanyId())){
			obc = new UserLastNameComparator(true);
		}else{
			obc = new UserFirstNameComparator(true);
		}

		boolean andOperator = true;

		PortletURL portletURL = renderResponse.createRenderURL();
		portletURL.setParameter("mvcRenderCommandName","/activities/test/correction");
		portletURL.setParameter("actId",Long.toString(actId));
		portletURL.setParameter("firstName",firstName);
		portletURL.setParameter("lastName",lastName);
		portletURL.setParameter("screenName",screenName);
		portletURL.setParameter("emailAddress",emailAddress);

		SearchContainer<User> userSearchContainer = new SearchContainer<User>(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, 
				ParamUtil.getInteger(renderRequest, SearchContainer.DEFAULT_DELTA_PARAM,SearchContainer.DEFAULT_DELTA), portletURL, 
				null,  "no-results");

		List<User> users = null;
		try {
			users = studentLocalService.getStudentsFromCourse(course.getCourseId(), themeDisplay.getCompanyId(), screenName, firstName, lastName, emailAddress, 
					WorkflowConstants.STATUS_APPROVED, null, andOperator, userSearchContainer.getStart(), userSearchContainer.getEnd(), obc);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			users = new ArrayList<User>();
		}

		int totalUsers = studentLocalService.countStudentsFromCourse(course.getCourseId(), themeDisplay.getCompanyId(), screenName, firstName, lastName, emailAddress, 
				WorkflowConstants.STATUS_APPROVED, null, andOperator);
		
		userSearchContainer.setResults(users);
		userSearchContainer.setTotal(totalUsers);
		
		try {
			LearningActivity activity = learningActivityLocalService.getLearningActivity(actId);
			TestActivityTypeFactory testActivityTypeFactory = (TestActivityTypeFactory)LearningActivityTypeFactoryRegistryUtil.getLearningActivityTypeFactoryByType(TestConstants.TYPE);
			TestActivityType testActivityType = testActivityTypeFactory.getTestActivityType(activity);
			
			renderRequest.setAttribute("testActivityType", testActivityType);
		} catch (PortalException e) {
			e.printStackTrace();
		}
		
		CalificationTypeFactory calificationTypeFactory = CalificationTypeFactoryRegistryUtil.getCalificationTypeFactoryByType(course.getCalificationType());
		CalificationType calificationType = calificationTypeFactory.getCalificationType(course);
		
		LiferayPortletURL showPopupGradesURL = renderResponse.createRenderURL();
		try {
			showPopupGradesURL.setWindowState(LiferayWindowState.POP_UP);
	    }
	    catch (WindowStateException wse) {
	    	wse.printStackTrace();
	    }
		showPopupGradesURL.setParameter(WebKeys.PORTLET_CONFIGURATOR_VISIBILITY, StringPool.TRUE);
		showPopupGradesURL.setParameter("mvcPath", "/edit_result.jsp");
		showPopupGradesURL.setParameter("actId", String.valueOf(actId));
		showPopupGradesURL.setParameter("courseId", String.valueOf(course.getCourseId()));
		showPopupGradesURL.setParameter("firstName",firstName);
		showPopupGradesURL.setParameter("lastName",lastName);
		showPopupGradesURL.setParameter("screenName",screenName);
		showPopupGradesURL.setParameter("emailAddress",emailAddress);
		
		PortletURL backURL = renderResponse.createRenderURL();
		backURL.setParameter("actId", String.valueOf(actId));
		
		renderRequest.setAttribute("searchContainer", userSearchContainer);
		renderRequest.setAttribute("calificationType", calificationType);
		renderRequest.setAttribute("actId", actId);
		renderRequest.setAttribute("backURL", backURL);
		renderRequest.setAttribute("testActivityTypeFactory", (TestActivityTypeFactory)LearningActivityTypeFactoryRegistryUtil.getLearningActivityTypeFactoryByType(TestConstants.TYPE));
		renderRequest.setAttribute("courseId", course.getCourseId());
		renderRequest.setAttribute("showPopupGradesURL", showPopupGradesURL);
	
		return "/corrections.jsp";
	}
	
	@Reference
	private LearningActivityLocalService learningActivityLocalService;
	@Reference
	private CourseLocalService courseLocalService;
	@Reference
	private StudentLocalService studentLocalService;
}
