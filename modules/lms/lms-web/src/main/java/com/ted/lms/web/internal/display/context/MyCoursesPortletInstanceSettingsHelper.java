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

package com.ted.lms.web.internal.display.context;

import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.ted.lms.web.internal.configuration.MyCoursesPortletInstanceConfiguration;

import javax.servlet.http.HttpServletRequest;

public class MyCoursesPortletInstanceSettingsHelper {

	public MyCoursesPortletInstanceSettingsHelper(
		HttpServletRequest request,
		MyCoursesPortletInstanceConfiguration myCoursesPortletInstanceConfiguration) {

		this.request = request;
		this.myCoursesPortletInstanceConfiguration = myCoursesPortletInstanceConfiguration;
	}

	public long getDisplayStyleGroupId() {
		if (displayStyleGroupId != 0) {
			return displayStyleGroupId;
		}

		displayStyleGroupId = myCoursesPortletInstanceConfiguration.displayStyleGroupId();

		if (displayStyleGroupId <= 0) {
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);

			displayStyleGroupId = themeDisplay.getScopeGroupId();
		}

		return displayStyleGroupId;
	}

	private final MyCoursesPortletInstanceConfiguration myCoursesPortletInstanceConfiguration;
	private long displayStyleGroupId;
	private final HttpServletRequest request;

}