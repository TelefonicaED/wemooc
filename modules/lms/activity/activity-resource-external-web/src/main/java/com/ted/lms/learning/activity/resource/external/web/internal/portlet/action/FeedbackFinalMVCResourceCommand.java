package com.ted.lms.learning.activity.resource.external.web.internal.portlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.ted.lms.learning.activity.question.model.Question;
import com.ted.lms.learning.activity.question.service.QuestionLocalService;
import com.ted.lms.learning.activity.resource.external.web.constants.ResourceExternalPortletKeys;
import com.ted.lms.model.LearningActivityTry;
import com.ted.lms.service.LearningActivityTryLocalService;

import java.util.List;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = {
		"javax.portlet.name=" + ResourceExternalPortletKeys.RESOURCE_EXTERNAL,
		"mvc.command.name=/activities/resource_external/feedback_final"
	},
	service = MVCResourceCommand.class
)
public class FeedbackFinalMVCResourceCommand extends BaseMVCResourceCommand {

	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
		
		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);

		long actId = ParamUtil.getLong(resourceRequest, "actId");
		
		System.out.println("actId: " + actId);
		
		List<Question> questions = questionLocalService.getQuestions(actId);
		LearningActivityTry activityTry = learningActivityTryLocalService.getLastLearningActivityTry(actId, themeDisplay.getUserId());
		
		resourceRequest.setAttribute("questions", questions);
		resourceRequest.setAttribute("tryResultData", activityTry.getTryResultData());
	
		
		include(resourceRequest, resourceResponse,"/results.jsp");
	}
	
	@Reference
	private QuestionLocalService questionLocalService;
	@Reference
	private LearningActivityTryLocalService learningActivityTryLocalService;
}
