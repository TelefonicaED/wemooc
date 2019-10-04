package com.ted.lms.learning.activity.question.web.portlet.action;

import com.liferay.exportimport.kernel.lar.ExportImportHelper;
import com.liferay.portal.kernel.model.Ticket;
import com.liferay.portal.kernel.model.TicketConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.TicketLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.util.PropsValues;
import com.ted.lms.learning.activity.question.constants.QuestionsWebPortletKeys;

import java.text.DecimalFormatSymbols;
import java.util.Date;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + QuestionsWebPortletKeys.EDIT_QUESTIONS,
		"mvc.command.name=/questions/import_questions"
	},
	service = MVCRenderCommand.class
)
public class ImportQuestionsMVCRenderCommand implements MVCRenderCommand {
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		long actId = ParamUtil.getLong(renderRequest, "actId", 0);
		String action = ParamUtil.getString(renderRequest, Constants.ACTION);
		String questionIdsAllowed = ParamUtil.getString(renderRequest, "questionIdsAllowed");
		
		System.out.println("action: " + action);
		System.out.println("questionIdsAllowed: " + questionIdsAllowed);
		
		renderRequest.setAttribute("actId", actId);
		
		String urlExample = "";	
		String importType = "";		
		String importQuestionURL = "";		
		String helpMessage = "";
		
		Date expirationDate = new Date(System.currentTimeMillis() + PropsValues.SESSION_TIMEOUT * Time.MINUTE);	
		Ticket ticket = TicketLocalServiceUtil.addTicket(themeDisplay.getCompanyId(), User.class.getName(), themeDisplay.getUserId(), TicketConstants.TYPE_IMPERSONATE, null, expirationDate, new ServiceContext());

		DecimalFormatSymbols decimalFormatSymbols = DecimalFormatSymbols.getInstance(themeDisplay.getLocale());	
		
		PortletURL deleteFileURL = renderResponse.createActionURL();
		deleteFileURL.setParameter("javax.portlet.action", "/questions/import_questions");
		deleteFileURL.setParameter("doAsUserId", String.valueOf(themeDisplay.getUserId()));
		deleteFileURL.setParameter(Constants.CMD, Constants.DELETE_TEMP);
		
		String folderName = ExportImportHelper.TEMP_FOLDER_NAME + QuestionsWebPortletKeys.EDIT_QUESTIONS;
		
		LiferayPortletURL importCSVURL = renderResponse.createActionURL();
		importCSVURL.setDoAsUserId(themeDisplay.getUserId());
		importCSVURL.setParameter("javax.portlet.action", "/questions/import_questions");
		importCSVURL.setParameter(Constants.CMD, Constants.ADD_TEMP);
		importCSVURL.setParameter("actId", String.valueOf(actId));
		
		LiferayPortletURL importQuestionsURL = renderResponse.createActionURL();
		importQuestionsURL.setParameter("javax.portlet.action", "/questions/import_questions");;
		importQuestionsURL.setParameter(Constants.CMD, Constants.IMPORT);
		importQuestionsURL.setParameter("actId", String.valueOf(actId));
		importQuestionsURL.setParameter("questionIdsAllowed", questionIdsAllowed);
		
		if(action.equals("XML")) {
			importType = "XML";
			importCSVURL.setParameter(Constants.ACTION, action);
			importQuestionsURL.setParameter(Constants.ACTION, action);
		}else if(action.equals("CSV")) {
			importType = "CSV";
			importCSVURL.setParameter(Constants.ACTION, action);
			importQuestionsURL.setParameter(Constants.ACTION, action);
		}
		
		renderRequest.setAttribute("urlExample", urlExample);
		renderRequest.setAttribute("importQuestionURL", importQuestionURL);
		renderRequest.setAttribute("helpMessage", helpMessage);
		renderRequest.setAttribute("importType", importType);
		renderRequest.setAttribute("ticket", ticket);
		renderRequest.setAttribute("decimalFormatSymbols", decimalFormatSymbols);
		renderRequest.setAttribute("deleteFileURL", deleteFileURL);
		renderRequest.setAttribute("folderName", HtmlUtil.escapeJS(folderName));
		renderRequest.setAttribute("importCSVURL", importCSVURL);
		renderRequest.setAttribute("importQuestionsURL", importQuestionsURL);
		renderRequest.setAttribute("result", ParamUtil.getString(renderRequest, "result"));
		
		return "/import_questions.jsp";
	}
}
