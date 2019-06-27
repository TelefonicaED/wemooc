package com.ted.lms.web.internal.portlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.trash.service.TrashEntryService;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.model.Course;
import com.ted.lms.service.CourseLocalService;
import com.ted.lms.service.CourseService;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + LMSPortletKeys.COURSE,
		"mvc.command.name=/courses/add_edition"
	},
	service = MVCActionCommand.class
)
public class AddEditionMVCActionCommand extends BaseMVCActionCommand {
	
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		String redirect = ParamUtil.getString(actionRequest, "redirect");
		long parentCourseId = ParamUtil.getLong(actionRequest, "parentCourseId");
		
		Map<Locale, String> titleMap = LocalizationUtil.getLocalizationMap(actionRequest, "titleMapAsXML");
		Map<Locale, String> friendlyURLMap = LocalizationUtil.getLocalizationMap(actionRequest, "friendlyURLMapAsXML");
		long layoutSetPrototypeId = ParamUtil.getLong(actionRequest, "layoutSetPrototypeId", 0);
		
		int registrationStartDateMonth = ParamUtil.getInteger(actionRequest, "registrationStartDateMonth");
		int registrationStartDateDay = ParamUtil.getInteger(actionRequest, "registrationStartDateDay");
		int registrationStartDateYear = ParamUtil.getInteger(actionRequest, "registrationStartDateYear");
		int registrationStartDateHour = ParamUtil.getInteger(actionRequest, "registrationStartDateHour");
		int registrationStartDateMinute = ParamUtil.getInteger(actionRequest, "registrationStartDateMinute");
		int registrationStartDateAmPm = ParamUtil.getInteger(actionRequest, "registrationStartDateAmPm");
		if (registrationStartDateAmPm == Calendar.PM) {
			registrationStartDateHour += 12;
		}
		int registrationEndDateMonth = ParamUtil.getInteger(actionRequest, "registrationEndDateMonth");
		int registrationEndDateDay = ParamUtil.getInteger(actionRequest, "registrationEndDateDay");
		int registrationEndDateYear = ParamUtil.getInteger(actionRequest, "registrationEndDateYear");
		int registrationEndDateHour = ParamUtil.getInteger(actionRequest, "registrationEndDateHour");
		int registrationEndDateMinute = ParamUtil.getInteger(actionRequest, "registrationEndDateMinute");
		int registrationEndDateAmPm = ParamUtil.getInteger(actionRequest, "registrationEndDateAmPm");
		if (registrationEndDateAmPm == Calendar.PM) {
			registrationEndDateHour += 12;
		}
		int executionStartDateMonth = ParamUtil.getInteger(actionRequest, "executionStartDateMonth");
		int executionStartDateDay = ParamUtil.getInteger(actionRequest, "executionStartDateDay");
		int executionStartDateYear = ParamUtil.getInteger(actionRequest, "executionStartDateYear");
		int executionStartDateHour = ParamUtil.getInteger(actionRequest, "executionStartDateHour");
		int executionStartDateMinute = ParamUtil.getInteger(actionRequest, "executionStartDateMinute");
		int executionStartDateAmPm = ParamUtil.getInteger(actionRequest, "executionStartDateAmPm");
		if (executionStartDateAmPm == Calendar.PM) {
			executionStartDateHour += 12;
		}
		int executionEndDateMonth = ParamUtil.getInteger(actionRequest, "executionEndDateMonth");
		int executionEndDateDay = ParamUtil.getInteger(actionRequest, "executionEndDateDay");
		int executionEndDateYear = ParamUtil.getInteger(actionRequest, "executionEndDateYear");
		int executionEndDateHour = ParamUtil.getInteger(actionRequest, "executionEndDateHour");
		int executionEndDateMinute = ParamUtil.getInteger(actionRequest, "executionEndDateMinute");
		int executionEndDateAmPm = ParamUtil.getInteger(actionRequest, "executionEndDateAmPm");
		if (executionEndDateAmPm == Calendar.PM) {
			executionEndDateHour += 12;
		}
		
		Date registrationStartDate = PortalUtil.getDate(
				registrationStartDateMonth, registrationStartDateDay, registrationStartDateYear,
				registrationStartDateHour, registrationStartDateMinute, themeDisplay.getTimeZone(), null);
		
		Date registrationEndDate = PortalUtil.getDate(
				registrationEndDateMonth, registrationEndDateDay, registrationEndDateYear,
				registrationEndDateHour, registrationEndDateMinute, themeDisplay.getTimeZone(), null);
		
		Date executionStartDate = PortalUtil.getDate(
				executionStartDateMonth, executionStartDateDay, executionStartDateYear,
				executionStartDateHour, executionStartDateMinute, themeDisplay.getTimeZone(), null);
		
		Date executionEndDate = PortalUtil.getDate(
				executionEndDateMonth, executionEndDateDay, executionEndDateYear,
				executionEndDateHour, executionEndDateMinute, themeDisplay.getTimeZone(), null);
		
		ServiceContext serviceContext = ServiceContextFactory.getInstance(Course.class.getName(), actionRequest);
		
		courseService.executeCopyCourse(parentCourseId, parentCourseId, titleMap, friendlyURLMap, layoutSetPrototypeId, registrationStartDate, registrationEndDate, 
				executionStartDate, executionEndDate, false, false, serviceContext);
		
		sendRedirect(actionRequest, actionResponse, redirect);
	}
	
	
	@Reference
	private Portal _portal;
	
	@Reference
	private Http _http;
	
	@Reference(unbind = "-")
	protected void setCourseLocalService(CourseLocalService courseLocalService) {
		this.courseLocalService = courseLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}
	
	@Reference(unbind = "-")
	protected void setTrashEntryService(TrashEntryService trashEntryService) {
		this.trashEntryService = trashEntryService;
	}
	
	private CourseLocalService courseLocalService;
	private CourseService courseService;
	private TrashEntryService trashEntryService;

}
