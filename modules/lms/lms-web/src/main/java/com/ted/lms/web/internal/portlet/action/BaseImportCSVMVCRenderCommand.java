package com.ted.lms.web.internal.portlet.action;

import com.liferay.exportimport.kernel.background.task.BackgroundTaskExecutorNames;
import com.liferay.exportimport.kernel.lar.ExportImportHelper;
import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskManagerUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Ticket;
import com.liferay.portal.kernel.model.TicketConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.TicketLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.util.PropsValues;
import com.ted.lms.constants.LMSPortletKeys;
import java.text.DecimalFormatSymbols;
import java.util.Date;
import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import org.osgi.service.component.annotations.Reference;

public abstract class BaseImportCSVMVCRenderCommand implements MVCRenderCommand {
	
	private static final Log log = LogFactoryUtil.getLog(BaseImportCSVMVCRenderCommand.class);
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		long parentCourseId = ParamUtil.getLong(renderRequest, "parentCourseId");
		String redirect = ParamUtil.getString(renderRequest, "redirect");

		try {

			int incompleteBackgroundTaskCount = BackgroundTaskManagerUtil.getBackgroundTasksCount(themeDisplay.getScopeGroupId(), LMSPortletKeys.COURSE, BackgroundTaskExecutorNames.PORTLET_IMPORT_BACKGROUND_TASK_EXECUTOR, false);
			String folderName = ExportImportHelper.TEMP_FOLDER_NAME + getPortletId(renderRequest);
			try {
				FileEntry fileEntry = ExportImportHelperUtil.getTempFileEntry(themeDisplay.getScopeGroupId(), themeDisplay.getUserId(), folderName);
				renderRequest.setAttribute("fileEntry", fileEntry);
			} catch (PortalException e) {
				e.printStackTrace();
			}
			
			Date expirationDate = new Date(System.currentTimeMillis() + PropsValues.SESSION_TIMEOUT * Time.MINUTE);
			
			Ticket ticket = TicketLocalServiceUtil.addTicket(themeDisplay.getCompanyId(), User.class.getName(), themeDisplay.getUserId(), TicketConstants.TYPE_IMPERSONATE, null, expirationDate, new ServiceContext());

			DecimalFormatSymbols decimalFormatSymbols = DecimalFormatSymbols.getInstance(themeDisplay.getLocale());
			
			PortletURL deleteFileURL = renderResponse.createActionURL();
			deleteFileURL.setParameter("javax.portlet.action", getActionName());
			deleteFileURL.setParameter("doAsUserId", String.valueOf(themeDisplay.getUserId()));
			deleteFileURL.setParameter(Constants.CMD, Constants.DELETE_TEMP);
			deleteFileURL.setParameter("redirect", redirect);
			
			renderRequest.setAttribute("deleteFileURL", deleteFileURL);
			renderRequest.setAttribute("ticket", ticket);
			renderRequest.setAttribute("decimalFormatSymbols", decimalFormatSymbols);
			renderRequest.setAttribute("fileDescription", "csv");
			renderRequest.setAttribute("messageInfo", getMessageInfo(renderRequest));
			renderRequest.setAttribute("redirect", redirect);
			renderRequest.setAttribute("incompleteBackgroundTaskCount", incompleteBackgroundTaskCount);
			renderRequest.setAttribute("parentCourseId", parentCourseId);
			renderRequest.setAttribute("importPortletURL", getImportPortletURL(renderRequest, renderResponse));
			renderRequest.setAttribute("importCSVURL", getImportCSVURL(renderRequest, renderResponse));
			renderRequest.setAttribute("folderName", HtmlUtil.escapeJS(folderName));
			
			return "/import_csv_validation.jsp";
			
		} catch (Exception pe) {
			pe.printStackTrace();
			SessionErrors.add(renderRequest, pe.getClass());

			return "/error.jsp";
		}
	}
	
	protected abstract String getMessageInfo(RenderRequest renderRequest);
	protected abstract PortletURL getImportPortletURL(RenderRequest renderRequest, RenderResponse renderResponse);
	protected abstract PortletURL getImportCSVURL(RenderRequest renderRequest, RenderResponse renderResponse);
	protected abstract String getPortletId(RenderRequest renderRequest);
	protected abstract String getActionName();


}
