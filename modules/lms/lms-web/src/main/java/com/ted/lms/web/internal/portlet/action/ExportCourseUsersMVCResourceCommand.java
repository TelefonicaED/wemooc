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
import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.CSVUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.ProgressTracker;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.util.comparator.UserFirstNameComparator;
import com.liferay.portal.kernel.util.comparator.UserLastNameComparator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.util.PropsValues;
import com.ted.lms.constants.LMSConstants;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.constants.LMSRoleConstants;
import com.ted.lms.model.Course;
import com.ted.lms.model.CourseResult;
import com.ted.lms.service.CourseLocalServiceUtil;
import com.ted.lms.service.CourseResultLocalServiceUtil;
import com.ted.lms.util.LMSPrefsPropsValues;

import java.text.DateFormat;
import java.text.Format;
import java.util.LinkedHashMap;
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
		"mvc.command.name=/courses/export_users"
	},
	service = MVCResourceCommand.class
)
public class ExportCourseUsersMVCResourceCommand extends BaseMVCResourceCommand {

	@Override
	protected void doServeResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		try {
			SessionMessages.add(
				resourceRequest,
				_portal.getPortletId(resourceRequest) +
					SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);

			String csv = getCourseUsersCSV(resourceRequest, resourceResponse);

			PortletResponseUtil.sendFile(
				resourceRequest, resourceResponse, "users.csv", csv.getBytes(),
				ContentTypes.TEXT_CSV_UTF8);
		}
		catch (Exception e) {
			SessionErrors.add(resourceRequest, e.getClass());

			_log.error(e, e);
		}
	}
	
	protected String getHeaderCSV(String authType, boolean roleStudent, Locale locale) {
		StringBundler sb = new StringBundler(8);
		
		if (CompanyConstants.AUTH_TYPE_SN.equalsIgnoreCase(authType)) {
			sb.append(CSVUtil.encode(LanguageUtil.get(locale, "screen-name")));
		}else if(CompanyConstants.AUTH_TYPE_EA.equalsIgnoreCase(authType)){
			sb.append(CSVUtil.encode(LanguageUtil.get(locale, "email-address")));
		}else{
			sb.append(CSVUtil.encode(LanguageUtil.get(locale, "user-id")));
		}
		for(String column: LMSConstants.COLUMNS_IMPORT_EXPORT_ASSIGN_MEMBERS) {
			sb.append(StringPool.SEMICOLON);
			sb.append(CSVUtil.encode(LanguageUtil.get(locale, column)));
		}
		if(roleStudent) {
			for(String column: LMSConstants.COLUMNS_IMPORT_EXPORT_ASSIGN_MEMBERS_STUDENTS) {
				sb.append(StringPool.SEMICOLON);
				sb.append(CSVUtil.encode(LanguageUtil.get(locale, column)));
			}
		}
		
		sb.append(StringPool.NEW_LINE);
		
		return sb.toString();
	}

	protected String getUserCSV(User user, long courseId, boolean roleStudent, String authType, Format dateFormatDateTime) {
		StringBundler sb = new StringBundler(8);

		if (CompanyConstants.AUTH_TYPE_SN.equalsIgnoreCase(authType)) {
			sb.append(CSVUtil.encode(user.getScreenName()));
		}else if(CompanyConstants.AUTH_TYPE_EA.equalsIgnoreCase(authType)){
			sb.append(CSVUtil.encode(user.getEmailAddress()));
		}else{
			sb.append(CSVUtil.encode(user.getUserId()));
		}
		sb.append(StringPool.SEMICOLON);
		sb.append(CSVUtil.encode(user.getFullName()));

		if(roleStudent) {
			sb.append(StringPool.SEMICOLON);
			CourseResult courseResult = CourseResultLocalServiceUtil.fetchCourseResult(courseId, user.getUserId());
			if(courseResult != null) {
				sb.append(courseResult.getAllowStartDate() != null ? dateFormatDateTime.format(courseResult.getAllowStartDate()): StringPool.BLANK);
				sb.append(StringPool.SEMICOLON);
				sb.append(courseResult.getAllowEndDate() != null ? dateFormatDateTime.format(courseResult.getAllowEndDate()): StringPool.BLANK);
			}else {
				sb.append(StringPool.SEMICOLON);
			}
		}

		sb.append(StringPool.NEW_LINE);

		return sb.toString();
	}

	protected List<User> getUsers(ResourceRequest resourceRequest, long courseId, long roleId) throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String keywords = ParamUtil.getString(resourceRequest, DisplayTerms.KEYWORDS);

		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		Course course = CourseLocalServiceUtil.getCourse(courseId);

		params.put("usersGroups", Long.valueOf(course.getGroupCreatedId()));
		params.put("userGroupRole",new Long[] {Long.valueOf(course.getGroupCreatedId()), Long.valueOf(roleId)});

		Indexer<?> indexer = IndexerRegistryUtil.nullSafeGetIndexer(User.class);

		if (indexer.isIndexerEnabled() && PropsValues.USERS_SEARCH_WITH_INDEX) {
			params.put("expandoAttributes", keywords);
		}
		
		OrderByComparator<User> obc = null;
		if(LMSPrefsPropsValues.getUsersFirstLastName(themeDisplay.getCompanyId())) {
			obc = new UserLastNameComparator();
		}else {
			obc = new UserFirstNameComparator();
		}

		return userLocalService.search(themeDisplay.getCompanyId(), keywords, WorkflowConstants.STATUS_APPROVED, params, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, obc);
	}

	protected String getCourseUsersCSV(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {

		long courseId = ParamUtil.getLong(resourceRequest, "courseId");
		long roleId = ParamUtil.getLong(resourceRequest, "roleId");
		
		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		List<User> users = getUsers(resourceRequest, courseId, roleId);

		if (users.isEmpty()) {
			return StringPool.BLANK;
		}

		String exportProgressId = ParamUtil.getString(resourceRequest, "exportProgressId");
		
		String authType = PropsUtil.get(PropsKeys.COMPANY_SECURITY_AUTH_TYPE);
		Company company = companyLocalService.getCompany(themeDisplay.getCompanyId());

		authType = company.getAuthType();

		ProgressTracker progressTracker = new ProgressTracker(exportProgressId);

		progressTracker.start(resourceRequest);

		int percentage = 10;
		int total = users.size();

		progressTracker.setPercent(percentage);

		StringBundler sb = new StringBundler(users.size());
		
		Role roleStudent = roleLocalService.getRole(themeDisplay.getCompanyId(), LMSRoleConstants.STUDENT);
		
		boolean isStudent = roleStudent != null && roleStudent.getRoleId() == roleId;

		DateFormat dateFormatDateTime = DateFormatFactoryUtil.getDateTime(themeDisplay.getLocale(), themeDisplay.getTimeZone());
		
		sb.append(getHeaderCSV(authType, isStudent, themeDisplay.getLocale()));
		
		for (int i = 0; i < users.size(); i++) {
			User user = users.get(i);

			sb.append(getUserCSV(user, courseId, isStudent, authType, dateFormatDateTime));

			percentage = Math.min(10 + (i * 90) / total, 99);

			progressTracker.setPercent(percentage);
		}

		progressTracker.finish(resourceRequest);

		return sb.toString();
	}

	@Reference(unbind = "-")
	protected void setUserLocalService(UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
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
		ExportCourseUsersMVCResourceCommand.class);

	@Reference
	private Portal _portal;

	private UserLocalService userLocalService;
	private CompanyLocalService companyLocalService;
	private RoleLocalService roleLocalService;

}