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
 * The extended model interface for the CourseResult service. Represents a row in the &quot;LMS_CourseResult&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see CourseResultModel
 * @see com.ted.lms.model.impl.CourseResultImpl
 * @see com.ted.lms.model.impl.CourseResultModelImpl
 * @generated
 */
@ImplementationClassName("com.ted.lms.model.impl.CourseResultImpl")
@ProviderType
public interface CourseResult extends CourseResultModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.ted.lms.model.impl.CourseResultImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<CourseResult, Long> CR_ID_ACCESSOR = new Accessor<CourseResult, Long>() {
			@Override
			public Long get(CourseResult courseResult) {
				return courseResult.getCrId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<CourseResult> getTypeClass() {
				return CourseResult.class;
			}
		};
}