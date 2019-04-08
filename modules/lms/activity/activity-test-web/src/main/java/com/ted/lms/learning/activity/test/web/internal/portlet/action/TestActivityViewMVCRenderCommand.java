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
			"mvc.command.name=/activities/test/view_activity" }, 
	service = MVCRenderCommand.class
)
public class TestActivityViewMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		LearningActivity activity = (LearningActivity)renderRequest.getAttribute("activity");
		boolean canUserDoNewTry = ParamUtil.getBoolean(renderRequest, "canUserDoNewTry");
		
		int tries =  LearningActivityTryLocalServiceUtil.getLearningActivityTriesCount(activity.getActId(),themeDisplay.getUserId());
		
		Object[] argumentsTries =  new Object[]{tries,activity.getTries()};
		Object[] argumentsPassPuntuation =  new Object[]{activity.getPassPuntuation()};
		
		boolean hasQuestions = QuestionLocalServiceUtil.getQuestionsCount(activity.getActId()) > 0;
		
		PortletURL correctURL = renderResponse.createRenderURL();
		correctURL.setParameter("actId", String.valueOf(activity.getActId()));
		correctURL.setParameter("improve", StringPool.TRUE);
		
		renderRequest.setAttribute("argumentsTries", argumentsTries);
		renderRequest.setAttribute("argumentsPassPuntuation", argumentsPassPuntuation);
		renderRequest.setAttribute("hasQuestions", hasQuestions);
		renderRequest.setAttribute("hasTries", canUserDoNewTry);
		renderRequest.setAttribute("correctURL", correctURL);
		
		
		return "/view.jsp";
	}
}
