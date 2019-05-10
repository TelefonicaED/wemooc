package com.ted.lms.learning.activity.survey.web.internal.portlet.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.ted.lms.constants.LMSActionKeys;
import com.ted.lms.learning.activity.question.model.Question;
import com.ted.lms.learning.activity.question.service.QuestionLocalServiceUtil;
import com.ted.lms.learning.activity.survey.web.constants.SurveyPortletKeys;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.LearningActivityTry;
import com.ted.lms.security.permission.resource.LMSPermission;
import com.ted.lms.service.LearningActivityLocalServiceUtil;
import com.ted.lms.service.LearningActivityResultLocalServiceUtil;
import com.ted.lms.service.LearningActivityTryLocalServiceUtil;

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true, 
	property = { "javax.portlet.name=" + SurveyPortletKeys.SURVEY,
			"mvc.command.name=/",
			"mvc.command.name=/activities/survey/view_activity" }, 
	service = MVCRenderCommand.class
)
public class SurveyActivityViewMVCRenderCommand implements MVCRenderCommand {
	
	private static final Log log = LogFactoryUtil.getLog(SurveyActivityViewMVCRenderCommand.class);

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		
		long actId = ParamUtil.getLong(renderRequest, "actId", 0);

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
	
		if (actId == 0) {
			renderRequest.setAttribute(WebKeys.PORTLET_CONFIGURATOR_VISIBILITY, Boolean.FALSE);
			return null;
		}else {
			
			LearningActivity activity = LearningActivityLocalServiceUtil.fetchLearningActivity(actId);

			boolean isTeacher = LMSPermission.contains(themeDisplay.getPermissionChecker(), themeDisplay.getScopeGroupId(), LMSActionKeys.VIEW_RESULTS);
			if(isTeacher) {
				PortletURL statisticsURL = renderResponse.createRenderURL();
				statisticsURL.setParameter("mvcRenderCommandName", "/activities/survey/statistics");
				statisticsURL.setParameter("actId", String.valueOf(actId));
				renderRequest.setAttribute("statisticsURL", statisticsURL);
			}
			
			boolean userPassed = LearningActivityResultLocalServiceUtil.hasUserPassed(actId,themeDisplay.getUserId());
			
			if(!userPassed) {
				List<Question> questions = QuestionLocalServiceUtil.getQuestionsOrder(actId);
				//Creamos el try
				try {
					ServiceContext serviceContext = ServiceContextFactory.getInstance(LearningActivityTry.class.getName(), renderRequest);
					LearningActivityTryLocalServiceUtil.addLearningActivityTry(actId, themeDisplay.getUserId(), serviceContext);
				} catch (PortalException e) {
					e.printStackTrace();
				}
				
				PortletURL surveyURL = renderResponse.createActionURL();
				surveyURL.setParameter("javax.portlet.action", "/activities/survey/save_survey");
				surveyURL.setParameter("actId", String.valueOf(actId));
				
				renderRequest.setAttribute("questions", questions);
				renderRequest.setAttribute("surveyURL", surveyURL);
			}
			
			renderRequest.setAttribute("activity", activity);
			renderRequest.setAttribute("actId", actId);
			renderRequest.setAttribute("isTeacher", isTeacher);
			renderRequest.setAttribute("userPassed", userPassed);
			
			return "/view.jsp";
		}
	}
	
	
}
