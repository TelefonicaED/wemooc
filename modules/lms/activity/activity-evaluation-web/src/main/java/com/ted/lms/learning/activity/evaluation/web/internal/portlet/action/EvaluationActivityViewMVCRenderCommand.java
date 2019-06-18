package com.ted.lms.learning.activity.evaluation.web.internal.portlet.action;

import com.liferay.portal.kernel.dao.orm.CustomSQLParam;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.util.comparator.UserFirstNameComparator;
import com.liferay.portal.kernel.util.comparator.UserLastNameComparator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.ted.lms.constants.LMSActionKeys;
import com.ted.lms.learning.activity.evaluation.EvaluationActivityType;
import com.ted.lms.learning.activity.evaluation.EvaluationActivityTypeFactory;
import com.ted.lms.learning.activity.evaluation.web.constants.EvaluationConstants;
import com.ted.lms.learning.activity.evaluation.web.constants.EvaluationPortletKeys;
import com.ted.lms.learning.activity.evaluation.web.internal.util.EvaluationActivitySQL;
import com.ted.lms.model.CalificationType;
import com.ted.lms.model.CalificationTypeFactory;
import com.ted.lms.model.Course;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.LearningActivityResult;
import com.ted.lms.registry.CalificationTypeFactoryRegistryUtil;
import com.ted.lms.registry.LearningActivityTypeFactoryRegistryUtil;
import com.ted.lms.security.permission.resource.LMSPermission;
import com.ted.lms.service.CourseLocalService;
import com.ted.lms.service.LearningActivityLocalService;
import com.ted.lms.service.LearningActivityResultLocalService;
import com.ted.lms.service.LearningActivityTryLocalService;
import com.ted.lms.service.StudentLocalService;
import com.ted.lms.util.LMSPrefsPropsValues;

import java.util.LinkedHashMap;
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
	property = { "javax.portlet.name=" + EvaluationPortletKeys.EVALUATION,
			"mvc.command.name=/",
			"mvc.command.name=/activities/evaluation/view_activity" }, 
	service = MVCRenderCommand.class
)
public class EvaluationActivityViewMVCRenderCommand implements MVCRenderCommand {
	
	private static final Log log = LogFactoryUtil.getLog(EvaluationActivityViewMVCRenderCommand.class);

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		log.debug("render online");
	
		boolean isTeacher = LMSPermission.contains(themeDisplay.getPermissionChecker(), themeDisplay.getScopeGroupId(), LMSActionKeys.VIEW_RESULTS);
		
		if(isTeacher) {
			return doRenderViewTutor(renderRequest, renderResponse);
		}else {
			return doRenderViewStudent(renderRequest, renderResponse);
		}
		
	}
	
	private String doRenderViewStudent(RenderRequest renderRequest, RenderResponse renderResponse) {
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		long actId = ParamUtil.getLong(renderRequest, "actId", 0);
		
		try {
			
			Course course = courseLocalService.getCourseByGroupCreatedId(themeDisplay.getScopeGroupId());
			
			CalificationTypeFactory calificationTypeFactory = CalificationTypeFactoryRegistryUtil.getCalificationTypeFactoryByType(course.getCalificationType());
			CalificationType calificationType = calificationTypeFactory.getCalificationType(course);
			
			LearningActivity activity = learningActivityLocalService.getLearningActivity(actId);
			EvaluationActivityTypeFactory evaluationActivityTypeFactory = (EvaluationActivityTypeFactory)LearningActivityTypeFactoryRegistryUtil.getLearningActivityTypeFactoryByType(EvaluationConstants.TYPE);
			EvaluationActivityType evaluationActivityType = evaluationActivityTypeFactory.getEvaluationActivityType(activity);
			
			LearningActivityResult activityResult = learningActivityResultLocalService.getLearningActivityResult(actId, themeDisplay.getUserId());
			
			String result = null;
			String status = null;
			if(activityResult != null && evaluationActivityType.getPublishDate() != null) {
				result = calificationType.translate(themeDisplay.getLocale(), activityResult.getResult()) + calificationType.getSuffix();
				status = activityResult.getStatusProperties();
			}else {
				result = LanguageUtil.get(themeDisplay.getLocale(), "calification.pending");
				status = "learning-activity.evaluation.status.not-published";
			}
			
			renderRequest.setAttribute("result", result);
			renderRequest.setAttribute("status", status);
			renderRequest.setAttribute("evaluationActivityType", evaluationActivityType);
			renderRequest.setAttribute("resultUser", activityResult != null);
			
			
		}catch (PortalException e) {
			e.printStackTrace();
			return "/error.jsp";
		}
		
		return "/view.jsp";
	}
	
	private String doRenderViewTutor(RenderRequest renderRequest, RenderResponse renderResponse) {
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		long actId = ParamUtil.getLong(renderRequest, "actId", 0);
		
		try {
			
			Course course = courseLocalService.getCourseByGroupCreatedId(themeDisplay.getScopeGroupId());
			
			CalificationTypeFactory calificationTypeFactory = CalificationTypeFactoryRegistryUtil.getCalificationTypeFactoryByType(course.getCalificationType());
			CalificationType calificationType = calificationTypeFactory.getCalificationType(course);
			renderRequest.setAttribute("calificationType", calificationType);
			
			LearningActivity activity = learningActivityLocalService.getLearningActivity(actId);
			EvaluationActivityTypeFactory evaluationActivityTypeFactory = (EvaluationActivityTypeFactory)LearningActivityTypeFactoryRegistryUtil.getLearningActivityTypeFactoryByType(EvaluationConstants.TYPE);
			EvaluationActivityType evaluationActivityType = evaluationActivityTypeFactory.getEvaluationActivityType(activity);
		
			if(evaluationActivityType.getFiredDate() == null && evaluationActivityType.getActivities().length() > 0) {
				PortletURL calculateResultURL = renderResponse.createActionURL();
				calculateResultURL.setParameter("javax.portlet.action", "calculateResult");
				calculateResultURL.setParameter("actId", String.valueOf(actId));
				renderRequest.setAttribute("calculateResultURL", calculateResultURL);
			}
			
			if(evaluationActivityType.getFiredDate() != null && evaluationActivityType.getPublishDate() == null) {
				PortletURL publishResultURL = renderResponse.createActionURL();
				publishResultURL.setParameter("javax.portlet.action", "publishResult");
				publishResultURL.setParameter("actId", String.valueOf(actId));
				renderRequest.setAttribute("publishResultURL", publishResultURL);
			}
			
			PortletURL recalculateResultURL = renderResponse.createActionURL();
			recalculateResultURL.setParameter("javax.portlet.action", "recalculateResult");
			recalculateResultURL.setParameter("actId", String.valueOf(actId));
			renderRequest.setAttribute("recalculateResultURL", recalculateResultURL);
			
			renderRequest.setAttribute("evaluationActivityType", evaluationActivityType);
			
			try {
				PortletURL showPopupResultURL = renderResponse.createRenderURL();
				showPopupResultURL.setWindowState(LiferayWindowState.POP_UP);
				showPopupResultURL.setParameter("mvcPath", "/edit_result.jsp");
				showPopupResultURL.setParameter("actId", String.valueOf(actId));
				showPopupResultURL.setParameter("courseId", String.valueOf(course.getCourseId()));
				renderRequest.setAttribute("showPopupResultURL", showPopupResultURL);
			}catch(WindowStateException e) {
				e.printStackTrace();
			}
			
			String keywords = ParamUtil.getString(renderRequest, "keywords");
			String status = ParamUtil.getString(renderRequest, "status");
			
			PortletURL portletURL = renderResponse.createRenderURL();
			portletURL.setParameter("actId",Long.toString(actId));
			portletURL.setParameter("keywords",keywords);
			portletURL.setParameter("status",String.valueOf(status));
			portletURL.setParameter("mvcRenderCommandName", "/activities/evaluation/view_activity");
			
			PortletURL searchURL = renderResponse.createRenderURL();
			searchURL.setParameter("actId",Long.toString(actId));
			searchURL.setParameter("mvcRenderCommandName", "/activities/evaluation/view_activity");
			renderRequest.setAttribute("searchURL", searchURL);
			
			LinkedHashMap<String,Object> params = new LinkedHashMap<String,Object>();
			if(Validator.isNotNull(status)) {
				switch(status) {
					case "nocalification":
						params.put("nocalification",new CustomSQLParam(EvaluationActivitySQL.ACTIVITY_RESULT_NO_CALIFICATION_SQL,actId));
						break;
					case "passed":
						params.put("passed",new CustomSQLParam(EvaluationActivitySQL.ACTIVITY_RESULT_PASSED_SQL,actId));
						break;
					case "failed":
						params.put("failed",new CustomSQLParam(EvaluationActivitySQL.ACTIVITY_RESULT_FAIL_SQL,actId));
						break;
				}
			}
			
			SearchContainer<User> searchContainer = new SearchContainer<User>(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, 
					ParamUtil.getInteger(renderRequest, SearchContainer.DEFAULT_DELTA_PARAM,SearchContainer.DEFAULT_DELTA), portletURL, 
					null,  "no-results");
			
			OrderByComparator obc = null;
			if(LMSPrefsPropsValues.getUsersFirstLastName(themeDisplay.getCompanyId())){
				obc = new UserLastNameComparator(true);
			}else{
				obc = new UserFirstNameComparator(true);
			}
			
			List<User> users = studentLocalService.getStudentsFromCourse(course.getCourseId(), themeDisplay.getCompanyId(), keywords, WorkflowConstants.STATUS_APPROVED, 
					params, searchContainer.getStart(), searchContainer.getEnd(), obc);
			
			int total = studentLocalService.countStudentsFromCourse(course.getCourseId(), themeDisplay.getCompanyId(), keywords, WorkflowConstants.STATUS_APPROVED, params);
			
			searchContainer.setResults(users);
			searchContainer.setTotal(total);
			
			renderRequest.setAttribute("searchContainer", searchContainer);
			renderRequest.setAttribute("actId", actId);
			
			return "/califications.jsp";
		}catch (PortalException e) {
			e.printStackTrace();
			return "/error.jsp";
		}
		
	}
	
	@Reference(unbind = "-")
	protected void setLearningActivityLocalService(LearningActivityLocalService learningActivityLocalService) {
		this.learningActivityLocalService = learningActivityLocalService;
	}

	@Reference(unbind = "-")
	protected void setLearningActivityResultLocalService(LearningActivityResultLocalService learningActivityResultLocalService) {
		this.learningActivityResultLocalService = learningActivityResultLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setLearningActivityTryLocalService(LearningActivityTryLocalService learningActivityTryLocalService) {
		this.learningActivityTryLocalService = learningActivityTryLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setStudentLocalService(StudentLocalService studentLocalService) {
		this.studentLocalService = studentLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setCourseLocalService(CourseLocalService courseLocalService) {
		this.courseLocalService = courseLocalService;
	}
	
	private LearningActivityLocalService learningActivityLocalService;
	private LearningActivityResultLocalService learningActivityResultLocalService;
	private LearningActivityTryLocalService learningActivityTryLocalService;
	private StudentLocalService studentLocalService; 
	private CourseLocalService courseLocalService;
}
