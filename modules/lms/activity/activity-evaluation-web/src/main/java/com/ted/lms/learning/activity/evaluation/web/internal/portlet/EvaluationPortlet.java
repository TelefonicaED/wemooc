package com.ted.lms.learning.activity.evaluation.web.internal.portlet;

import com.ted.lms.constants.LMSActionKeys;
import com.ted.lms.learning.activity.evaluation.EvaluationActivityType;
import com.ted.lms.learning.activity.evaluation.EvaluationActivityTypeFactory;
import com.ted.lms.learning.activity.evaluation.web.constants.EvaluationConstants;
import com.ted.lms.learning.activity.evaluation.web.constants.EvaluationPortletKeys;
import com.ted.lms.model.CalificationType;
import com.ted.lms.model.CalificationTypeFactory;
import com.ted.lms.model.Course;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.LearningActivityResult;
import com.ted.lms.model.LearningActivityTry;
import com.ted.lms.registry.CalificationTypeFactoryRegistryUtil;
import com.ted.lms.registry.LearningActivityTypeFactoryRegistryUtil;
import com.ted.lms.security.permission.resource.LMSPermission;
import com.ted.lms.service.CourseLocalService;
import com.ted.lms.service.LearningActivityLocalService;
import com.ted.lms.service.LearningActivityResultLocalService;
import com.ted.lms.service.LearningActivityService;
import com.ted.lms.service.LearningActivityTryLocalService;

import java.util.Date;
import java.util.List;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author JE10436
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=hidden",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.init-param.template-path=/META-INF/resources/",
		"javax.portlet.init-param.mvc-command-names-default-views=/activities/evaluation/view_activity",
		"javax.portlet.name=" + EvaluationPortletKeys.EVALUATION,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
		"javax.portlet.supported-public-render-parameter=actId"
	},
	service = Portlet.class
)
public class EvaluationPortlet extends MVCPortlet {
	
	private static final Log log = LogFactoryUtil.getLog(EvaluationPortlet.class);
	
	public void calculateResult(ActionRequest actionRequest,ActionResponse actionResponse) {
		long actId=ParamUtil.getLong(actionRequest, "actId");
		
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		try {
			LearningActivity activity = learningActivityLocalService.getLearningActivity(actId);
			EvaluationActivityTypeFactory evaluationActivityTypeFactory = (EvaluationActivityTypeFactory)LearningActivityTypeFactoryRegistryUtil.getLearningActivityTypeFactoryByType(EvaluationConstants.TYPE);
			EvaluationActivityType evaluationActivityType = evaluationActivityTypeFactory.getEvaluationActivityType(activity);
			
			evaluationActivityType.setFiredDate(new Date());
			
			Course course = courseLocalService.getCourseByGroupCreatedId(themeDisplay.getScopeGroupId());
			
			List<User> users = courseLocalService.getStudentsFromCourse(course.getCourseId(), themeDisplay.getCompanyId(), null, 
					WorkflowConstants.STATUS_APPROVED, null, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
			
			log.debug("users: " + users.size());
			
			ServiceContext serviceContext = ServiceContextFactory.getInstance(actionRequest);
			
			for(User user: users) {
				log.debug("user: " + user.getUserId());
				evaluationActivityType.evaluateUser(user.getUserId(), serviceContext);
			}
		
			learningActivityLocalService.updateLearningActivity(activity, serviceContext);
			
			SessionMessages.add(actionRequest, "calculate-result");
		} catch (PortalException e) {
			e.printStackTrace();
			SessionErrors.add(actionRequest, "calculate-result");
		}
	}
	
	public void recalculateResult(ActionRequest actionRequest,ActionResponse actionResponse) {
		long actId=ParamUtil.getLong(actionRequest, "actId");
		long studentId = ParamUtil.getLong(actionRequest,"studentId");	
		
		try {
			
			LearningActivity activity = learningActivityLocalService.getLearningActivity(actId);
			EvaluationActivityTypeFactory evaluationActivityTypeFactory = (EvaluationActivityTypeFactory)LearningActivityTypeFactoryRegistryUtil.getLearningActivityTypeFactoryByType(EvaluationConstants.TYPE);
			EvaluationActivityType evaluationActivityType = evaluationActivityTypeFactory.getEvaluationActivityType(activity);
			
			ServiceContext serviceContext = ServiceContextFactory.getInstance(actionRequest);
			
			evaluationActivityType.evaluateUser(studentId, serviceContext);
		} catch (Exception e) {
			e.printStackTrace();
			SessionErrors.add(actionRequest, "recalculate-result");
		}
	}
	
	public void publishResult(ActionRequest actionRequest,ActionResponse actionResponse) {
		long actId=ParamUtil.getLong(actionRequest, "actId");
		
		try {
			LearningActivity activity = learningActivityLocalService.getLearningActivity(actId);
			EvaluationActivityTypeFactory evaluationActivityTypeFactory = (EvaluationActivityTypeFactory)LearningActivityTypeFactoryRegistryUtil.getLearningActivityTypeFactoryByType(EvaluationConstants.TYPE);
			EvaluationActivityType evaluationActivityType = evaluationActivityTypeFactory.getEvaluationActivityType(activity);
			
			evaluationActivityType.setPublishDate(new Date());
		
			ServiceContext serviceContext = ServiceContextFactory.getInstance(LearningActivity.class.getName(), actionRequest);
			learningActivityLocalService.updateLearningActivity(activity, serviceContext);
			
			SessionMessages.add(actionRequest, "publish-result");
		} catch (PortalException e) {
			e.printStackTrace();
			SessionErrors.add(actionRequest, "publish-result");
		}
	}
	
	public void updateResult(ActionRequest actionRequest,ActionResponse actionResponse) {
		long actId=ParamUtil.getLong(actionRequest, "actId");
		long studentId = ParamUtil.getLong(actionRequest,"studentId");	
		String comments = ParamUtil.getString(actionRequest,"comments");
		
		log.debug("actId: " + actId);
		log.debug("studentId: " + studentId);
		log.debug("comments: " + comments);
		
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		//Obtenemos el método de calificación para obtener el valor
		Course course = courseLocalService.getCourseByGroupCreatedId(themeDisplay.getScopeGroupId());
		CalificationTypeFactory calificationTypeFactory = CalificationTypeFactoryRegistryUtil.getCalificationTypeFactoryByType(course.getCalificationType());
		CalificationType calificationType = calificationTypeFactory.getCalificationType(course);
		
		try {
		
			double result = calificationType.getResultBase100(actionRequest);
			
			log.debug("result base 100: " + result);
			
			LearningActivityTry  learningActivityTry =  learningActivityTryLocalService.getLastLearningActivityTry(actId, studentId);
			ServiceContext serviceContext = ServiceContextFactory.getInstance(LearningActivityTry.class.getName(), actionRequest);
			if(learningActivityTry==null){
				learningActivityTry = learningActivityTryLocalService.addLearningActivityTry(actId, studentId, serviceContext);
				log.debug("creamos learningActivityTryId: " + learningActivityTry.getLatId());
			}else {
				log.debug("learningActivityTryId: " + learningActivityTry.getLatId());
			}
			
			learningActivityTry.setComments(comments);
			learningActivityTryLocalService.finishLearningActivityTry(learningActivityTry, result, serviceContext);
			
			LearningActivityResult learningActivityResult = learningActivityResultLocalService.getLearningActivityResult(actId, studentId);
			String status="status.not-attempted";
			if(learningActivityResult != null){
				status="status.incomplete";
				
				if(learningActivityResult.getEndDate()!=null){
					status="status.failed"	;
				}
				if(learningActivityResult.isPassed()){
					status="status.passed"	;
				}
			}	
			
			actionResponse.setRenderParameter("result", calificationType.translate(themeDisplay.getLocale(), learningActivityResult.getResult()));
			actionResponse.setRenderParameter("status", status);
			SessionMessages.add(actionRequest, "resultEdited");
		}catch(PortalException e) {
			SessionErrors.add(actionRequest, "grades.bad-updating");
		}

		actionResponse.setRenderParameter("mvcPath", "/edit_result.jsp");
	}
	
	@Reference(unbind = "-")
	protected void setCourseLocalService(CourseLocalService courseLocalService) {
		this.courseLocalService = courseLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setLearningActivityTryLocalService(LearningActivityTryLocalService learningActivityTryLocalService) {
		this.learningActivityTryLocalService = learningActivityTryLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setLearningActivityResultLocalService(LearningActivityResultLocalService learningActivityResultLocalService) {
		this.learningActivityResultLocalService = learningActivityResultLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setLearningActivityLocalService(LearningActivityLocalService learningActivityLocalService) {
		this.learningActivityLocalService = learningActivityLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setUserLocalService(UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}
	
	private CourseLocalService courseLocalService;
	private LearningActivityTryLocalService learningActivityTryLocalService;
	private LearningActivityResultLocalService learningActivityResultLocalService;
	private LearningActivityLocalService learningActivityLocalService;
	private UserLocalService userLocalService;
}