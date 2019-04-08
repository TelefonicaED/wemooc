package com.ted.lms.learning.activity.test.web.internal.portlet.action;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.ted.lms.constants.LMSActionKeys;
import com.ted.lms.learning.activity.question.model.Question;
import com.ted.lms.learning.activity.question.model.QuestionType;
import com.ted.lms.learning.activity.question.model.QuestionTypeFactory;
import com.ted.lms.learning.activity.question.registry.QuestionTypeFactoryRegistryUtil;
import com.ted.lms.learning.activity.question.service.QuestionLocalServiceUtil;
import com.ted.lms.learning.activity.test.web.activity.TestActivityType;
import com.ted.lms.learning.activity.test.web.activity.TestActivityTypeFactory;
import com.ted.lms.learning.activity.test.web.constants.TestPortletKeys;
import com.ted.lms.model.CalificationType;
import com.ted.lms.model.CalificationTypeFactory;
import com.ted.lms.model.Course;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.LearningActivityResult;
import com.ted.lms.model.LearningActivityTry;
import com.ted.lms.registry.CalificationTypeFactoryRegistryUtil;
import com.ted.lms.security.permission.resource.LMSPermission;
import com.ted.lms.security.permission.resource.LearningActivityPermission;
import com.ted.lms.service.CourseLocalServiceUtil;
import com.ted.lms.service.LearningActivityLocalServiceUtil;
import com.ted.lms.service.LearningActivityResultLocalServiceUtil;
import com.ted.lms.service.LearningActivityTryLocalServiceUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true, 
	property = { "javax.portlet.name=" + TestPortletKeys.TEST,
			"mvc.command.name=/",
			"mvc.command.name=/activities/test/view_test" }, 
	service = MVCRenderCommand.class
)
public class TestActivityViewTestMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		boolean hasPermissionAccessCourseFinished = ParamUtil.getBoolean(renderRequest, "hasPermissionAccessCourseFinished");
		TestActivityType testActivityType = (TestActivityType)renderRequest.getAttribute("testActivityType");
		LearningActivityTry learningTry = (LearningActivityTry)renderRequest.getAttribute("larntry");
		LearningActivity activity = (LearningActivity)renderRequest.getAttribute("activity");
		LearningActivityResult result = (LearningActivityResult)renderRequest.getAttribute("result");
		boolean improve = ParamUtil.getBoolean(renderRequest, "improve");
		long activityTimestamp = ParamUtil.getLong(renderRequest, "activityTimestamp");
		long timestamp = ParamUtil.getLong(renderRequest, "timestamp");
		boolean canUserDoNewTry = ParamUtil.getBoolean(renderRequest, "canUserDoNewTry");
		
		List<Question> questions = QuestionLocalServiceUtil.getQuestionsOrder(activity.getActId());
		
		long random = 0;
		if(!hasPermissionAccessCourseFinished){
			random = testActivityType.getRandom();
		}
		
		long currentQuestionId = 0;
		if (learningTry != null && Validator.isXml(learningTry.getTryResultData())) {
			try {
				String tryResultData = learningTry.getTryResultData();
				Document docQuestions = SAXReaderUtil.read(tryResultData);
				List<Element> xmlQuestions = docQuestions.getRootElement().elements("question");
				
				Map<Long, Question> questionMap = new HashMap<Long, Question>();
				for (Question question : questions) {
					questionMap.put(question.getQuestionId(), question);
				}
				questions = new ArrayList<Question>();
				for (Element xmlQuestion : xmlQuestions) {
					String questionIdString = xmlQuestion.attributeValue("id");
					if (Validator.isNotNull(questionIdString) && Validator.isNumber(questionIdString)) {
						Long questionId = Long.valueOf(questionIdString);
						Question testQuestion = questionMap.get(questionId);
						if (testQuestion != null) {
							questions.add(testQuestion);
							String currentString = xmlQuestion.attributeValue("current");
							if ("true".equals(currentString)) {
								currentQuestionId = questionId;
							}
						}
					}
				}
				random = questions.size();
			}catch (DocumentException e) {
				e.printStackTrace();
				questions = null;
			}
		} else {
			if (random != 0){
				questions = new ArrayList<Question>(questions);
				Collections.shuffle(questions);	
				if (random > questions.size()){
					random=questions.size();
				}
			}else{
				random=questions.size();
			}
		}
		
		long limitChunk = testActivityType.getQuestionsPerPage() == 0 ? random : testActivityType.getQuestionsPerPage();
		if (limitChunk == 0) {
			limitChunk = 1;
		}
		
		long totalPages = (random / limitChunk) + (random % limitChunk != 0 ? 1 : 0);
		long width_frame = 10000 / totalPages;
		
		String tryResultData = "";
		if (!hasPermissionAccessCourseFinished && Validator.isXml(learningTry.getTryResultData())) {
			tryResultData = learningTry.getTryResultData();
		}
		renderRequest.setAttribute("tryResultData", tryResultData);
		
		renderRequest.setAttribute("isPassed", result != null && result.isPassed());
		renderRequest.setAttribute("hasPermissionAccessCourseFinished", hasPermissionAccessCourseFinished);
		
		PortletURL correctURL = renderResponse.createActionURL();
		correctURL.setParameter("javax.portlet.action", "correct");
		correctURL.setParameter("actId", String.valueOf(activity.getActId()));
		correctURL.setParameter("improve", String.valueOf(improve));
		if(!hasPermissionAccessCourseFinished) {
			correctURL.setParameter("latId", String.valueOf(learningTry.getLatId()));
		}
		renderRequest.setAttribute("correctURL", correctURL);
		
		PortletURL correctAccessFinishedURL = renderResponse.createActionURL();
		correctAccessFinishedURL.setParameter("javax.portlet.action", "correctAccessFinished");
		correctAccessFinishedURL.setParameter("actId", String.valueOf(activity.getActId()));
		correctAccessFinishedURL.setParameter("improve", String.valueOf(improve));
		renderRequest.setAttribute("correctAccessFinishedURL", correctAccessFinishedURL);
		
		renderRequest.setAttribute("activityTimestamp", activityTimestamp);
		renderRequest.setAttribute("timestamp", timestamp);
		renderRequest.setAttribute("questions", questions);
		renderRequest.setAttribute("currentQuestionId", currentQuestionId);
		renderRequest.setAttribute("totalPages", totalPages);
		renderRequest.setAttribute("width_frame", width_frame);
		renderRequest.setAttribute("random", random);
		renderRequest.setAttribute("limitChunk", limitChunk);
		renderRequest.setAttribute("testActivityType", testActivityType);
		renderRequest.setAttribute("canUserDoNewTry", canUserDoNewTry);
		
		return "/test.jsp";
	}
}
