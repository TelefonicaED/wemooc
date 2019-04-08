package com.ted.lms.learning.activity.test.web.internal.portlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.ted.lms.learning.activity.test.web.activity.TestActivityType;
import com.ted.lms.learning.activity.test.web.constants.TestPortletKeys;
import com.ted.lms.model.CalificationType;
import com.ted.lms.model.CalificationTypeFactory;
import com.ted.lms.model.Course;
import com.ted.lms.registry.CalificationTypeFactoryRegistryUtil;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true, 
	property = { "javax.portlet.name=" + TestPortletKeys.TEST,
			"mvc.command.name=/",
			"mvc.command.name=/activities/test/finish_without_tries" }, 
	service = MVCRenderCommand.class
)
public class TestActivityFinishWithoutTriesMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {

		TestActivityType testActivityType = (TestActivityType)renderRequest.getAttribute("testActivityType");
		Course course = (Course)renderRequest.getAttribute("course");
		
		boolean hasFreeQuestion = testActivityType.hasFreeQuestions();
		
		if(!hasFreeQuestion) {
			CalificationTypeFactory calificationTypeFactory = CalificationTypeFactoryRegistryUtil.getCalificationTypeFactoryByType(course.getCalificationType());
			CalificationType calificationType = calificationTypeFactory.getCalificationType(course);
			renderRequest.setAttribute("calificationType", calificationType);
		}
		
		renderRequest.setAttribute("hasFreeQuestion", hasFreeQuestion);
		
		return "/finish_without_tries.jsp";
	}
}
