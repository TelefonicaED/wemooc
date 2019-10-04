package com.ted.lms.learning.activity.question.web.portlet.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.learning.activity.question.constants.QuestionsWebPortletKeys;
import com.ted.lms.learning.activity.question.service.AnswerLocalService;
import com.ted.lms.learning.activity.question.service.QuestionLocalService;
import java.io.File;
import java.io.IOException;
import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + QuestionsWebPortletKeys.EDIT_QUESTIONS,
		"javax.portlet.name=" + LMSPortletKeys.COURSE_CONTENT_VIEWER,
		"mvc.command.name=/questions/export_questions"
	},
	service = MVCResourceCommand.class
)
public class ExportQuestionsMVCResourceCommand extends BaseMVCResourceCommand {
	
	private static final Log log = LogFactoryUtil.getLog(ExportQuestionsMVCResourceCommand.class);
	
	public static final int COLUMN_INDEX_QUESTION_TITLE = 0;
	public static final int COLUMN_INDEX_QUESTION_TYPE = 1;
	public static final int COLUMN_INDEX_QUESTION_PENALIZE = 2;
	public static final int COLUMN_INDEX_ANSWER_TITLE = 3;
	public static final int COLUMN_INDEX_ANSWER_IS_CORRECT = 4;
	public static final int COLUMN_INDEX_ANSWER_FEEDBACK_CORRECT = 5;
	public static final int COLUMN_INDEX_ANSWER_FEEDBACK_INCORRECT = 6;
	
	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws PortletException, PortalException, IOException {
		
		System.out.println("entramosssssssssssssssss");
		
		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		long actId = ParamUtil.getLong(resourceRequest, "actId");
		String action = ParamUtil.getString(resourceRequest, Constants.ACTION);
		
		if(action.equals("XML")) {
			resourceResponse.addProperty(HttpHeaders.CONTENT_DISPOSITION,"attachment; fileName=data.xml");
			resourceResponse.setContentType(ContentTypes.TEXT_XML_UTF8);
			
			questionLocalService.exportXMLQuestions(resourceResponse.getPortletOutputStream(), actId);
			
			resourceResponse.getPortletOutputStream().flush();
			resourceResponse.getPortletOutputStream().close();
		} else if(action.equals("CSV")) {
			String name = LanguageUtil.get(themeDisplay.getLocale(), "questions")+".xls";
			File file = questionLocalService.exportExcelQuestions(themeDisplay.getLocale(), actId);
			resourceResponse.setContentType( ContentTypes.APPLICATION_VND_MS_EXCEL);
			resourceResponse.setContentLength((int)file.length());
			resourceResponse.addProperty(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + name);
			ServletResponseUtil.sendFile(PortalUtil.getHttpServletRequest(resourceRequest),
					PortalUtil.getHttpServletResponse(resourceResponse),
					name,
					FileUtil.getBytes(file), 
					ContentTypes.APPLICATION_VND_MS_EXCEL);
		}
	}
	
	@Reference
	private Portal _portal;
	
	@Reference
	private Http _http;
	
	@Reference(unbind = "-")
	protected void setQuestionLocalService(QuestionLocalService questionLocalService) {
		this.questionLocalService = questionLocalService;
	}
	
	private QuestionLocalService questionLocalService;
	
	@Reference
	private AnswerLocalService answerLocalService;

}
