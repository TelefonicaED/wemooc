package com.ted.lms.learning.activity.question.web.portlet;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.ted.lms.learning.activity.question.constants.QuestionsWebPortletKeys;
import com.ted.lms.learning.activity.question.model.Question;
import com.ted.lms.learning.activity.question.model.QuestionTypeFactory;
import com.ted.lms.learning.activity.question.registry.QuestionTypeFactoryRegistryUtil;
import com.ted.lms.learning.activity.question.service.QuestionLocalService;

import java.io.IOException;
import java.io.PrintWriter;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author JE10436
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=" + QuestionsWebPortletKeys.EDIT_QUESTIONS_JSP,
		"javax.portlet.name=" + QuestionsWebPortletKeys.EDIT_QUESTIONS,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=user",
		"com.liferay.portlet.add-default-resource=true"
		
	},
	service = Portlet.class
)
public class EditQuestionsWebPortlet extends MVCPortlet {
	@Override    
    public void serveResource(ResourceRequest request, ResourceResponse response) throws PortletException, IOException {
		System.out.println("entramos en el render principal");
		
		String action = ParamUtil.getString(request, Constants.ACTION);
		System.out.println("action_ " + action);
		
		if(action.equalsIgnoreCase(Constants.ADD)) {
			response.setContentType("application/json");
			JSONObject oreturned = JSONFactoryUtil.createJSONObject();
			
			try {
				long questionId = ParamUtil.getLong(request, "questionId");
				Question question = questionLocalService.getQuestion(questionId);
				
				LiferayPortletResponse liferayPortletResponse = PortalUtil.getLiferayPortletResponse(response);
				
				QuestionTypeFactory questionTypeFactory = QuestionTypeFactoryRegistryUtil.getQuestionTypeFactoryByType(question.getQuestionTypeId());
				String urlQuestion = questionTypeFactory.getURLAddQuestion(liferayPortletResponse);
				
				System.out.println("urlQuestion: " + urlQuestion);
				
				oreturned.put("urlQuestion", urlQuestion);
				
				PrintWriter out = response.getWriter();
				out.print(oreturned.toString());
				out.flush();
				out.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else {
			String mvcPath = ParamUtil.getString(request, "mvcPath");
			
			PortletRequestDispatcher dispatcher = getPortletContext().getRequestDispatcher(mvcPath);
			dispatcher.include(request, response);
		}
	}
	
	@Reference
	private QuestionLocalService questionLocalService;
	
}