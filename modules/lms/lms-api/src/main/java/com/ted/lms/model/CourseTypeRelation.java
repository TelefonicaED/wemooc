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
 * The extended model interface for the CourseTypeRelation service. Represents a row in the &quot;LMS_CourseTypeRelation&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see CourseTypeRelationModel
 * @see com.ted.lms.model.impl.CourseTypeRelationImpl
 * @see com.ted.lms.model.impl.CourseTypeRelationModelImpl
 * @generated
 */
@ImplementationClassName("com.ted.lms.model.impl.CourseTypeRelationImpl")
@ProviderType
public interface CourseTypeRelation extends CourseTypeRelationModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.ted.lms.model.impl.CourseTypeRelationImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<CourseTypeRelation, Long> COURSE_TYPE_ID_ACCESSOR =
		new Accessor<CourseTypeRelation, Long>() {
			@Override
			public Long get(CourseTypeRelation courseTypeRelation) {
				return courseTypeRelation.getCourseTypeId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<CourseTypeRelation> getTypeClass() {
				return CourseTypeRelation.class;
			}
		};

	public static final Accessor<CourseTypeRelation, Long> CLASS_NAME_ID_ACCESSOR =
		new Accessor<CourseTypeRelation, Long>() {
			@Override
			public Long get(CourseTypeRelation courseTypeRelation) {
				return courseTypeRelation.getClassNameId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<CourseTypeRelation> getTypeClass() {
				return CourseTypeRelation.class;
			}
		};

	public static final Accessor<CourseTypeRelation, Long> CLASS_PK_ACCESSOR = new Accessor<CourseTypeRelation, Long>() {
			@Override
			public Long get(CourseTypeRelation courseTypeRelation) {
				return courseTypeRelation.getClassPK();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<CourseTypeRelation> getTypeClass() {
				return CourseTypeRelation.class;
			}
		};
}