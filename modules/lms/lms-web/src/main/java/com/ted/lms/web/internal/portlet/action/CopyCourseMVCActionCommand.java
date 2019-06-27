package com.ted.lms.web.internal.portlet.action;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.ted.lms.constants.CourseConstants;
import com.ted.lms.constants.LMSPortletKeys;
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
		"mvc.command.name=/courses/copy_course"
	},
	service = MVCActionCommand.class
)
public class CopyCourseMVCActionCommand extends BaseMVCActionCommand {
	
	private static final Log log = LogFactoryUtil.getLog(CopyCourseMVCActionCommand.class);
	
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		long courseId = ParamUtil.getLong(actionRequest, "courseId", 0);
		
		Map<Locale, String> titleMap = LocalizationUtil.getLocalizationMap(actionRequest, "titleMapAsXML");
		
		long layoutSetPrototypeId = ParamUtil.getLong(actionRequest, "layoutSetPrototypeId");
		
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
		
		ServiceContext serviceContext = ServiceContextFactory.getInstance(actionRequest);
		
		Date registrationStartDate = PortalUtil.getDate(
				registrationStartDateMonth, registrationStartDateDay, registrationStartDateYear,
				registrationStartDateHour, registrationStartDateMinute, serviceContext.getTimeZone(), null);
		
		Date registrationEndDate = PortalUtil.getDate(
				registrationEndDateMonth, registrationEndDateDay, registrationEndDateYear,
				registrationEndDateHour, registrationEndDateMinute, serviceContext.getTimeZone(), null);
		
		Date executionStartDate = PortalUtil.getDate(
				executionStartDateMonth, executionStartDateDay, executionStartDateYear,
				executionStartDateHour, executionStartDateMinute, serviceContext.getTimeZone(), null);
		
		Date executionEndDate = PortalUtil.getDate(
				executionEndDateMonth, executionEndDateDay, executionEndDateYear,
				executionEndDateHour, executionEndDateMinute, serviceContext.getTimeZone(), null);
		
		if(registrationStartDate.after(registrationEndDate)) {
			SessionErrors.add(actionRequest, "registration-dates");
			actionResponse.setRenderParameter("courseId", String.valueOf(courseId));
			actionResponse.setRenderParameter("mvcRenderCommandName", "/courses/copy_course");
		} else if(executionStartDate.after(executionEndDate)) {
			SessionErrors.add(actionRequest, "execution-dates");
			actionResponse.setRenderParameter("courseId", String.valueOf(courseId));
			actionResponse.setRenderParameter("mvcRenderCommandName", "/courses/copy_course");
		} else {
			
			boolean copyForum = ParamUtil.getBoolean(actionRequest, "copyForum");
			boolean copyDocuments = ParamUtil.getBoolean(actionRequest, "copyDocuments");
	
			
			log.debug("courseId: " + courseId);
			log.debug("layoutSetPrototypeId: " + layoutSetPrototypeId);
			
			courseService.executeCopyCourse(courseId, CourseConstants.DEFAULT_PARENT_COURSE_ID, titleMap, null, layoutSetPrototypeId, registrationStartDate, 
					registrationEndDate, executionStartDate, executionEndDate, copyForum, copyDocuments, serviceContext);
		}
	}
	
	@Reference(unbind = "-")
	protected void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}

	private CourseService courseService;

}
