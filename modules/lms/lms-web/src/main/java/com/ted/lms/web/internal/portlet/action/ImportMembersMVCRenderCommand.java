package com.ted.lms.web.internal.portlet.action;

import com.liferay.exportimport.kernel.background.task.BackgroundTaskExecutorNames;
import com.liferay.exportimport.kernel.lar.ExportImportHelper;
import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskManagerUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.model.Ticket;
import com.liferay.portal.kernel.model.TicketConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.LayoutServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.TicketLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.util.PropsValues;
import com.liferay.portlet.portletconfiguration.action.ActionUtil;
import com.ted.lms.constants.LMSPortletKeys;
import java.text.DecimalFormatSymbols;
import java.util.Date;
import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
		immediate = true,
		property = {
			"javax.portlet.name=" + LMSPortletKeys.COURSE,
			"mvc.command.name=/courses/import_members"
		},
		service = MVCRenderCommand.class
	)
public class ImportMembersMVCRenderCommand extends BaseImportCSVMVCRenderCommand {
	
	private static final Log log = LogFactoryUtil.getLog(ImportMembersMVCRenderCommand.class);
	
	@Override
	protected String getMessageInfo(RenderRequest renderRequest) {
		
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		String authType = themeDisplay.getCompany().getAuthType();
		
		String arguments = "";
		if (CompanyConstants.AUTH_TYPE_SN.equalsIgnoreCase(authType)) {
			arguments = LanguageUtil.get(themeDisplay.getLocale(), "screen-name");	
		}else if(CompanyConstants.AUTH_TYPE_EA.equalsIgnoreCase(authType)){
			arguments = LanguageUtil.get(themeDisplay.getLocale(), "email-address");
		}else{
			arguments = LanguageUtil.get(themeDisplay.getLocale(), "user-id");
		}

		return LanguageUtil.format(themeDisplay.getLocale(), "course-admin.assing-users.import.help", arguments);
	}
	
	@Override
	protected PortletURL getImportPortletURL(RenderRequest renderRequest, RenderResponse renderResponse) {
		LiferayPortletResponse liferayPortletResponse = PortalUtil.getLiferayPortletResponse(renderResponse);
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		LiferayPortletURL importPortletURL = liferayPortletResponse.createResourceURL(portal.getPortletId(renderRequest));
		importPortletURL.setResourceID("/courses/import_members");
		importPortletURL.setDoAsUserId(themeDisplay.getUserId());
		importPortletURL.setCopyCurrentRenderParameters(false);
		importPortletURL.setParameter("p_p_isolated", String.valueOf(true));
		importPortletURL.setParameter("redirect", ParamUtil.getString(renderRequest, "redirect"));
		importPortletURL.setParameter("courseId", String.valueOf(ParamUtil.getLong(renderRequest, "courseId")));
		importPortletURL.setParameter("roleId", String.valueOf(ParamUtil.getLong(renderRequest, "roleId")));
		
		return importPortletURL;
	}
	
	@Override
	protected PortletURL getImportCSVURL(RenderRequest renderRequest, RenderResponse renderResponse) {
	
		LiferayPortletResponse liferayPortletResponse = PortalUtil.getLiferayPortletResponse(renderResponse);
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
	
		LiferayPortletURL importCSVURL = liferayPortletResponse.createActionURL(portal.getPortletId(renderRequest));
		importCSVURL.setDoAsUserId(themeDisplay.getUserId());
		importCSVURL.setParameter("javax.portlet.action", "/courses/import_members");
		importCSVURL.setParameter("mvcRenderCommandName", "/courses/import_members");
		importCSVURL.setParameter(Constants.CMD, Constants.ADD_TEMP);
		importCSVURL.setParameter("redirect", ParamUtil.getString(renderRequest, "redirect"));
		importCSVURL.setParameter("courseId", String.valueOf(ParamUtil.getLong(renderRequest, "courseId")));
		importCSVURL.setParameter("roleId", String.valueOf(ParamUtil.getLong(renderRequest, "roleId")));
		
		return importCSVURL;
	}
	
	@Override
	protected String getPortletId(RenderRequest renderRequest) {
		return portal.getPortletId(renderRequest);
	}
	
	@Override
	protected String getActionName() {
		return "/courses/import_members";
	}

	@Reference
	private Portal portal;

}
