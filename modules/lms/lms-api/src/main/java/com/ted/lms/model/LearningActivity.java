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
 * The extended model interface for the LearningActivity service. Represents a row in the &quot;LMS_LearningActivity&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see LearningActivityModel
 * @see com.ted.lms.model.impl.LearningActivityImpl
 * @see com.ted.lms.model.impl.LearningActivityModelImpl
 * @generated
 */
@ImplementationClassName("com.ted.lms.model.impl.LearningActivityImpl")
@ProviderType
public interface LearningActivity extends LearningActivityModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.ted.lms.model.impl.LearningActivityImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<LearningActivity, Long> ACT_ID_ACCESSOR = new Accessor<LearningActivity, Long>() {
			@Override
			public Long get(LearningActivity learningActivity) {
				return learningActivity.getActId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<LearningActivity> getTypeClass() {
				return LearningActivity.class;
			}
		};

	public com.liferay.portal.kernel.json.JSONObject getExtraContentJSON();

	public void addExtraContentJSON(String key, Object value);
}