package com.ted.lms.learning.activity.survey.web.internal.portlet.action;

import com.liferay.exportimport.kernel.background.task.BackgroundTaskExecutorNames;
import com.liferay.portal.kernel.backgroundtask.BackgroundTask;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskConstants;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskManagerUtil;
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
import com.ted.lms.model.Course;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.LearningActivityTry;
import com.ted.lms.security.permission.resource.LMSPermission;
import com.ted.lms.service.CourseLocalServiceUtil;
import com.ted.lms.service.CourseResultLocalServiceUtil;
import com.ted.lms.service.LearningActivityLocalServiceUtil;
import com.ted.lms.service.LearningActivityResultLocalServiceUtil;
import com.ted.lms.service.LearningActivityTryLocalServiceUtil;

import java.text.DecimalFormat;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceURL;

import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true, 
	property = { "javax.portlet.name=" + SurveyPortletKeys.SURVEY,
			"mvc.command.name=/activities/survey/statistics" }, 
	service = MVCRenderCommand.class
)
public class StatisticsViewMVCRenderCommand implements MVCRenderCommand {
	
	private static final Log log = LogFactoryUtil.getLog(StatisticsViewMVCRenderCommand.class);

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		
		long actId = ParamUtil.getLong(renderRequest, "actId", 0);

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
	
		if (actId == 0) {
			renderRequest.setAttribute(WebKeys.PORTLET_CONFIGURATOR_VISIBILITY, Boolean.FALSE);
			return null;
		}else {
			PortletURL backURL = renderResponse.createRenderURL();
			backURL.setParameter("actId", String.valueOf(actId));
			renderRequest.setAttribute("backURL", backURL);
			
			ResourceURL statisticsReportURL = renderResponse.createResourceURL();
			statisticsReportURL.setResourceID("statisticsReports");
			statisticsReportURL.setParameter("actId", String.valueOf(actId));
			renderRequest.setAttribute("statisticsReportURL", statisticsReportURL);
			
			List<Question> questions = QuestionLocalServiceUtil.getQuestionsOrder(actId);
			renderRequest.setAttribute("questions", questions);
			Course course = CourseLocalServiceUtil.getCourseByGroupCreatedId(themeDisplay.getScopeGroupId());
			
			long participants = LearningActivityResultLocalServiceUtil.countStudentFinished(actId, themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());
			long numStudents = CourseLocalServiceUtil.countStudentsFromCourse(course.getCourseId(), themeDisplay.getCompanyId());
			double passPercent =  ((double)participants/(double)numStudents)*100;
			DecimalFormat df = new DecimalFormat("###.##");
			String percent  = df.format(passPercent);
			
			renderRequest.setAttribute("participants", participants);
			renderRequest.setAttribute("percent", percent);
			renderRequest.setAttribute("df", df);
			
			return "/statistics.jsp";
		}
	}
	
	
}
