package com.ted.lms.web.internal.portlet.action;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.ted.lms.constants.LMSPortletKeys;

import java.text.DateFormat;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
		immediate = true,
		property = {
			"javax.portlet.name=" + LMSPortletKeys.COURSE,
			"mvc.command.name=/courses/import_editions"
		},
		service = MVCRenderCommand.class
	)
public class ImportEditionsMVCRenderCommand extends BaseImportCSVMVCRenderCommand {
	
	@Override
	protected String getMessageInfo(RenderRequest renderRequest) {
		
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		DateFormat dateFormatDateTime = DateFormatFactoryUtil.getDateTime(themeDisplay.getLocale(), themeDisplay.getTimeZone());
		
		return LanguageUtil.format(themeDisplay.getLocale(), "course-admin.editions.import.help", dateFormatDateTime.toString());
	}

	@Override
	protected PortletURL getImportPortletURL(RenderRequest renderRequest, RenderResponse renderResponse) {
		LiferayPortletResponse liferayPortletResponse = PortalUtil.getLiferayPortletResponse(renderResponse);
		
		LiferayPortletURL importPortletURL = liferayPortletResponse.createResourceURL(portal.getPortletId(renderRequest));
		importPortletURL.setResourceID("/courses/import_editions");
		importPortletURL.setCopyCurrentRenderParameters(false);
		importPortletURL.setParameter("p_p_isolated", String.valueOf(true));
		importPortletURL.setParameter("redirect", ParamUtil.getString(renderRequest, "redirect"));
		importPortletURL.setParameter("parentCourseId", String.valueOf(ParamUtil.getLong(renderRequest, "parentCourseId")));
		
		return importPortletURL;
	}

	@Override
	protected PortletURL getImportCSVURL(RenderRequest renderRequest, RenderResponse renderResponse) {
	
		LiferayPortletResponse liferayPortletResponse = PortalUtil.getLiferayPortletResponse(renderResponse);
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
	
		LiferayPortletURL importCSVURL = liferayPortletResponse.createActionURL(portal.getPortletId(renderRequest));
		importCSVURL.setDoAsUserId(themeDisplay.getUserId());
		importCSVURL.setParameter("javax.portlet.action", "/courses/import_editions");
		importCSVURL.setParameter("mvcRenderCommandName", "/courses/import_editions");
		importCSVURL.setParameter(Constants.CMD, Constants.ADD_TEMP);
		importCSVURL.setParameter("redirect", ParamUtil.getString(renderRequest, "redirect"));
		importCSVURL.setParameter("parentCourseId", String.valueOf(ParamUtil.getLong(renderRequest, "parentCourseId")));
		
		return importCSVURL;
	}
	
	@Override
	protected String getPortletId(RenderRequest renderRequest) {
		return portal.getPortletId(renderRequest);
	}
	
	@Override
	protected String getActionName() {
		return "/courses/import_editions";
	}
	
	@Reference
	private Portal portal;

}
