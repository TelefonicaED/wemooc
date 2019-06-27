/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.ted.lms.web.internal.portlet.action;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.CSVUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.ProgressTracker;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.ted.lms.constants.LMSConstants;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.model.Course;
import com.ted.lms.service.CourseLocalService;
import java.text.DateFormat;
import java.text.Format;
import java.util.List;
import java.util.Locale;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Brian Wing Shun Chan
 * @author Mika Koivisto
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + LMSPortletKeys.COURSE,
		"mvc.command.name=/courses/export_editions"
	},
	service = MVCResourceCommand.class
)
public class ExportEditionsMVCResourceCommand extends BaseMVCResourceCommand {

	@Override
	protected void doServeResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		try {
			SessionMessages.add(
				resourceRequest,
				_portal.getPortletId(resourceRequest) +
					SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);

			String csv = getEditionsCSV(resourceRequest, resourceResponse);

			PortletResponseUtil.sendFile(
				resourceRequest, resourceResponse, "editions.csv", csv.getBytes(),
				ContentTypes.TEXT_CSV_UTF8);
		}
		catch (Exception e) {
			SessionErrors.add(resourceRequest, e.getClass());

			_log.error(e, e);
		}
	}
	
	protected String getHeaderCSV(Locale locale) {
		StringBundler sb = new StringBundler(8);
		boolean firstColumn = true;
		for(String column: LMSConstants.COLUMNS_IMPORT_EDITIONS) {
			if(firstColumn) {
				firstColumn = false;
			}else {
				sb.append(StringPool.SEMICOLON);
			}
			sb.append(CSVUtil.encode(LanguageUtil.get(locale, column)));
		}
		
		sb.append(StringPool.NEW_LINE);
		
		return sb.toString();
	}

	protected String getEditionCSV(Course course, Locale locale, Format dateFormatDateTime) {
		StringBundler sb = new StringBundler(8);

		sb.append(CSVUtil.encode(course.getTitle(locale)));
		sb.append(StringPool.SEMICOLON);
		sb.append(CSVUtil.encode(course.getFriendlyURL()));
		sb.append(StringPool.SEMICOLON);
		sb.append(course.getRegistrationStartDate() != null ? dateFormatDateTime.format(course.getRegistrationStartDate()): StringPool.BLANK);
		sb.append(StringPool.SEMICOLON);
		sb.append(course.getRegistrationEndDate() != null ? dateFormatDateTime.format(course.getRegistrationEndDate()): StringPool.BLANK);
		sb.append(StringPool.SEMICOLON);
		sb.append(course.getExecutionStartDate() != null ? dateFormatDateTime.format(course.getExecutionStartDate()): StringPool.BLANK);
		sb.append(StringPool.SEMICOLON);
		sb.append(course.getExecutionEndDate() != null ? dateFormatDateTime.format(course.getExecutionEndDate()): StringPool.BLANK);
		
		sb.append(StringPool.NEW_LINE);

		return sb.toString();
	}

	protected String getEditionsCSV(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {

		long courseId = ParamUtil.getLong(resourceRequest, "courseId");
		
		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		String keywords = ParamUtil.getString(resourceRequest, "keywords", null);
		int status = ParamUtil.getInteger(resourceRequest, "status");
		
		int[] statusArray = null;
		if(status == WorkflowConstants.STATUS_APPROVED) {
			statusArray = new int[]{WorkflowConstants.STATUS_APPROVED, WorkflowConstants.STATUS_DRAFT};
		}else if(status != WorkflowConstants.STATUS_ANY){
			statusArray = new int[] {status};
		}
		
		long groupId = ParamUtil.getLong(resourceRequest, "groupId", themeDisplay.getScopeGroupId());
		
		List<Course> editions = courseLocalService.searchCourses(themeDisplay.getCompanyId(), keywords, themeDisplay.getLanguageId(), statusArray, 
				courseId, groupId, null, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		if (editions.isEmpty()) {
			return StringPool.BLANK;
		}

		String exportProgressId = ParamUtil.getString(resourceRequest, "exportProgressId");

		ProgressTracker progressTracker = new ProgressTracker(exportProgressId);

		progressTracker.start(resourceRequest);

		int percentage = 10;
		int total = editions.size();

		progressTracker.setPercent(percentage);

		StringBundler sb = new StringBundler(editions.size());

		DateFormat dateFormatDateTime = DateFormatFactoryUtil.getDateTime(themeDisplay.getLocale(), themeDisplay.getTimeZone());
		
		sb.append(getHeaderCSV(themeDisplay.getLocale()));
		
		for (int i = 0; i < editions.size(); i++) {
			Course edition = editions.get(i);

			sb.append(getEditionCSV(edition, themeDisplay.getLocale(), dateFormatDateTime));

			percentage = Math.min(10 + (i * 90) / total, 99);

			progressTracker.setPercent(percentage);
		}

		progressTracker.finish(resourceRequest);

		return sb.toString();
	}

	@Reference(unbind = "-")
	protected void setCourseLocalService(CourseLocalService courseLocalService) {
		this.courseLocalService = courseLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setCompanyLocalService(CompanyLocalService companyLocalService) {
		this.companyLocalService = companyLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setRoleLocalService(RoleLocalService roleLocalService) {
		this.roleLocalService = roleLocalService;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ExportEditionsMVCResourceCommand.class);

	@Reference
	private Portal _portal;

	private CourseLocalService courseLocalService;
	private CompanyLocalService companyLocalService;
	private RoleLocalService roleLocalService;

}