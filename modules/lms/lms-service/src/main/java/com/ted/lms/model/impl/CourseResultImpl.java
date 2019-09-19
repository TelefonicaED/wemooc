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

package com.ted.lms.model.impl;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.ted.lms.model.Course;
import com.ted.lms.service.CourseLocalServiceUtil;

import java.util.Locale;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model implementation for the CourseResult service. Represents a row in the &quot;LMS_CourseResult&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.ted.lms.model.CourseResult<code> interface.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class CourseResultImpl extends CourseResultBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a course result model instance should use the {@link com.ted.lms.model.CourseResult} interface instead.
	 */
private Course course;
	
	public CourseResultImpl() {
	}
	
	public Course getCourse() {
		if (course == null) {
			course = CourseLocalServiceUtil.fetchCourse(getCourseId());
		}

		return course;
	}
	
	public double getProgress() {
		return getResult();
	}
	
	public boolean isFinished() {
		return getPassedDate() != null;
	}
	
	public String getCourseStatus(Locale locale) {
		String status = "";
		if(getStartDate() == null) {
			status = LanguageUtil.get(locale, "results.status.not-started");
		}else if(!isFinished()) {
			status = LanguageUtil.get(locale, "results.status.started");
		}else if(getPassed()) {
			status = LanguageUtil.get(locale, "results.status.passed");
		}else {
			status = LanguageUtil.get(locale, "results.status.failed");
		}
		
		return status;
	}

}