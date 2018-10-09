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

package com.ted.lms.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the Course service. Represents a row in the &quot;LMS_Course&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see CourseModel
 * @see com.ted.lms.model.impl.CourseImpl
 * @see com.ted.lms.model.impl.CourseModelImpl
 * @generated
 */
@ImplementationClassName("com.ted.lms.model.impl.CourseImpl")
@ProviderType
public interface Course extends CourseModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.ted.lms.model.impl.CourseImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Course, Long> COURSE_ID_ACCESSOR = new Accessor<Course, Long>() {
			@Override
			public Long get(Course course) {
				return course.getCourseId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Course> getTypeClass() {
				return Course.class;
			}
		};

	public String getFriendlyURL();

	public com.liferay.portal.kernel.json.JSONObject getCourseExtraDataJSON();

	public void addCourseExtraDataJSON(String key, Object value);

	public com.liferay.portal.kernel.json.JSONObject getCourseEvalJSON();

	public boolean isRegistredUser(long userId);

	public CourseResult getResultUser(long userId);

	public com.liferay.portal.kernel.model.Group getGroup();

	public boolean canUnsubscribe(long userId,
		com.liferay.portal.kernel.security.permission.PermissionChecker permissionChecker)
		throws com.liferay.portal.kernel.exception.PortalException;

	public boolean canEnroll(long userId, java.util.Locale locale,
		com.liferay.portal.kernel.security.permission.PermissionChecker permissionChecker)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.ted.lms.exception.InscriptionException;

	public java.util.Calendar getExecutionStartDateCalendar();

	public java.util.Calendar getExecutionEndDateCalendar();
}