package com.ted.lms.learning.activity.resource.external.web.internal.portlet.action;

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
import com.ted.lms.learning.activity.resource.external.ResourceExternalActivityType;
import com.ted.lms.learning.activity.resource.external.ResourceExternalActivityTypeFactory;
import com.ted.lms.learning.activity.resource.external.web.constants.ResourceExternalPortletKeys;
import com.ted.lms.model.CalificationType;
import com.ted.lms.model.CalificationTypeFactory;
import com.ted.lms.model.Course;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.registry.CalificationTypeFactoryRegistryUtil;
import com.ted.lms.service.CourseLocalServiceUtil;
import com.ted.lms.service.LearningActivityLocalServiceUtil;
import com.ted.lms.util.LMSPrefsPropsValues;

import java.util.List;
import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowStateException;

import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true, 
	property = { "javax.portlet.name=" + ResourceExternalPortletKeys.RESOURCE_EXTERNAL,
			"mvc.command.name=/activities/resource_external/correction" }, 
	service = MVCRenderCommand.class
)
public class ResourceExternalCorrectionMVCRenderCommand implements MVCRenderCommand {
	
	private static final Log log = LogFactoryUtil.getLog(ResourceExternalCorrectionMVCRenderCommand.class);

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		
		long actId = ParamUtil.getLong(renderRequest, "actId", 0);

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		Course course = CourseLocalServiceUtil.getCourseByGroupCreatedId(themeDisplay.getScopeGroupId());
		
		ParamUtil.print(renderRequest);

		String firstName = ParamUtil.getString(renderRequest, "firstName","");
		String lastName = ParamUtil.getString(renderRequest, "lastName","");
		String screenName = ParamUtil.getString(renderRequest, "screenName","");
		String emailAddress = ParamUtil.getString(renderRequest, "emailAddress","");
		
		log.debug("firstName: " + firstName);
		
		LiferayPortletResponse liferayPortletResponse = PortalUtil.getLiferayPortletResponse(renderResponse);
		
		LiferayPortletURL searchURL = liferayPortletResponse.createRenderURL(ResourceExternalPortletKeys.RESOURCE_EXTERNAL);
		searchURL.setParameter("mvcRenderCommandName", "/activities/resource_external/correction");
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
		portletURL.setParameter("mvcRenderCommandName","/activities/resource_external/correction");
		portletURL.setParameter("actId",Long.toString(actId));
		portletURL.setParameter("firstName",firstName);
		portletURL.setParameter("lastName",lastName);
		portletURL.setParameter("screenName",screenName);
		portletURL.setParameter("emailAddress",emailAddress);

		SearchContainer<User> userSearchContainer = new SearchContainer<User>(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, 
				ParamUtil.getInteger(renderRequest, SearchContainer.DEFAULT_DELTA_PARAM,SearchContainer.DEFAULT_DELTA), portletURL, 
				null,  "no-results");

		List<User> users = CourseLocalServiceUtil.getStudentsFromCourse(course.getCourseId(), themeDisplay.getCompanyId(), screenName, firstName, lastName, emailAddress, 
				WorkflowConstants.STATUS_APPROVED, andOperator, userSearchContainer.getStart(), userSearchContainer.getEnd(), obc);

		int totalUsers = CourseLocalServiceUtil.countStudentsFromCourse(course.getCourseId(), themeDisplay.getCompanyId(), screenName, firstName, lastName, emailAddress, 
				WorkflowConstants.STATUS_APPROVED, andOperator);
		
		userSearchContainer.setResults(users);
		userSearchContainer.setTotal(totalUsers);
		
		try {
			LearningActivity activity = LearningActivityLocalServiceUtil.getLearningActivity(actId);
			ResourceExternalActivityType resourceExternalActivityType = new ResourceExternalActivityType(activity);
			
			renderRequest.setAttribute("resourceExternalActivityType", resourceExternalActivityType);
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
		renderRequest.setAttribute("resourceExternalActivityTypeFactory", new ResourceExternalActivityTypeFactory());
		renderRequest.setAttribute("courseId", course.getCourseId());
		renderRequest.setAttribute("showPopupGradesURL", showPopupGradesURL);
	
		return "/corrections.jsp";
	}
}
