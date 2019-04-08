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
			"mvc.command.name=/activities/test/results" }, 
	service = MVCRenderCommand.class
)
public class TestActivityResultsMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		boolean hasPermissionAccessCourseFinished = ParamUtil.getBoolean(renderRequest, "hasPermissionAccessCourseFinished");
		LearningActivityTry learningActivityTry = (LearningActivityTry)renderRequest.getAttribute("larntry");
		TestActivityType testActivityType = (TestActivityType)renderRequest.getAttribute("testActivityType");
		Course course = (Course)renderRequest.getAttribute("course");
		boolean isLocked = ParamUtil.getBoolean(renderRequest, "isLocked");
		
		List<Question> questions = testActivityType.getQuestions();
		boolean hasFreeQuestions = testActivityType.hasFreeQuestions();
		
		long tries = 0;
		long userTries = 0;
		double score = 0;
		double scoreTry = 0;
		String tryResultData = null;
		LearningActivity activity = testActivityType.getLearningActivity();
		
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		if(!hasPermissionAccessCourseFinished){
			tries = testActivityType.getLearningActivity().getTries();
			userTries = LearningActivityTryLocalServiceUtil.getLearningActivityTriesCount(activity.getActId(),themeDisplay.getUserId());
		
			LearningActivityResult result = LearningActivityResultLocalServiceUtil.getLearningActivityResult(activity.getActId(),themeDisplay.getUserId());
			score = result.getResult();
			scoreTry = learningActivityTry.getResult();
			tryResultData = learningActivityTry.getTryResultData();
		}else{
			score = ParamUtil.getLong(renderRequest, "score", 0);
			scoreTry = score;
			tryResultData = ParamUtil.getString(renderRequest, "tryResultData", "");
		}
		boolean userPassed=false;
		boolean showFeedback = testActivityType.getShowFeedback();
		
		boolean comesFromCorrection = ParamUtil.get(renderRequest, "correction", false);
		CalificationTypeFactory calificationTypeFactory = CalificationTypeFactoryRegistryUtil.getCalificationTypeFactoryByType(course.getCalificationType());
		CalificationType calificationType = calificationTypeFactory.getCalificationType(course);
		
		if(!comesFromCorrection) {
			userPassed=LearningActivityResultLocalServiceUtil.hasUserPassed(activity.getActId(),themeDisplay.getUserId());
		}else if(!hasPermissionAccessCourseFinished){
			userPassed=activity.getPassPuntuation()<=scoreTry;
			if(showFeedback){
				renderRequest.setAttribute("showPopupResult", true);
				renderRequest.setAttribute("hasFreeQuestion", testActivityType.hasFreeQuestions());
			}
		}
		
		renderRequest.setAttribute("userPassed", userPassed);
		
		if(showFeedback) {
			if(testActivityType.getRandom() > 0 && !hasPermissionAccessCourseFinished) {
				questions= new ArrayList<Question>();
				try {
					Iterator<Element> nodeItr = SAXReaderUtil.read(tryResultData).getRootElement().elementIterator();
					Question question=null;
					while(nodeItr.hasNext()) {
						Element element = nodeItr.next();				
						 if("question".equals(element.getName())) {
							 question=QuestionLocalServiceUtil.fetchQuestion(Long.valueOf(element.attributeValue("id")));
							 if(question != null){
								 questions.add(question); 
							 }		        	 
						 }
					}
				} catch (DocumentException e) {
					e.printStackTrace();
				}
				
				boolean canUserDoNewTry = testActivityType.hasTries(themeDisplay.getUserId()) && !isLocked;
				renderRequest.setAttribute("canUserDoNewTry",canUserDoNewTry);
					
			}
			try {
				Document documentTryResultData = SAXReaderUtil.read(tryResultData);
				renderRequest.setAttribute("documentTryResultData", documentTryResultData);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(tries==0 || userTries < tries || LearningActivityPermission.contains(themeDisplay.getPermissionChecker(), activity, LMSActionKeys.UPDATE)) {
			if(!LearningActivityResultLocalServiceUtil.hasUserPassed(activity.getActId(),themeDisplay.getUserId())){
				PortletURL tryTestURL = renderResponse.createRenderURL();
				tryTestURL.setParameter("actId", String.valueOf(activity.getActId()));
				renderRequest.setAttribute("tryTestURL", tryTestURL);
			}else if(score < 100 && testActivityType.getImprove()){
				PortletURL improveURL = renderResponse.createRenderURL();
				improveURL.setParameter("actId", String.valueOf(activity.getActId()));
				improveURL.setParameter("improve", StringPool.TRUE);
				renderRequest.setAttribute("improveURL", improveURL);
			}
		}
		
		renderRequest.setAttribute("hasFreeQuestions", hasFreeQuestions);
		renderRequest.setAttribute("calificationType", calificationType); 
		renderRequest.setAttribute("score", score);
		renderRequest.setAttribute("scoreTry", scoreTry);
		renderRequest.setAttribute("questions", questions);
		renderRequest.setAttribute("tries", tries);
		renderRequest.setAttribute("showFeedback", showFeedback);
		
		return "/results.jsp";
	}
}
