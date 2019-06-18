package com.ted.lms.learning.activity.survey.web.internal.portlet.action;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.ted.lms.learning.activity.question.model.Question;
import com.ted.lms.learning.activity.question.service.QuestionLocalService;
import com.ted.lms.learning.activity.survey.web.constants.SurveyPortletKeys;
import com.ted.lms.model.Course;
import com.ted.lms.service.CourseLocalService;
import com.ted.lms.service.LearningActivityResultLocalService;
import com.ted.lms.service.StudentLocalService;

import java.text.DecimalFormat;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceURL;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

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
			
			List<Question> questions = questionLocalService.getQuestionsOrder(actId);
			renderRequest.setAttribute("questions", questions);
			Course course = courseLocalService.getCourseByGroupCreatedId(themeDisplay.getScopeGroupId());
			
			long participants = learningActivityResultLocalService.countStudentFinished(actId, themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());
			long numStudents = studentLocalService.countStudentsFromCourse(course.getCourseId(), themeDisplay.getCompanyId());
			double passPercent =  ((double)participants/(double)numStudents)*100;
			DecimalFormat df = new DecimalFormat("###.##");
			String percent  = df.format(passPercent);
			
			renderRequest.setAttribute("participants", participants);
			renderRequest.setAttribute("percent", percent);
			renderRequest.setAttribute("df", df);
			
			return "/statistics.jsp";
		}
	}
	
	@Reference
	private StudentLocalService studentLocalService;
	@Reference
	private CourseLocalService courseLocalService;
	@Reference
	private LearningActivityResultLocalService learningActivityResultLocalService;
	@Reference
	private QuestionLocalService questionLocalService;
}
