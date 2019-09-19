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

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the ModuleResult service. Represents a row in the &quot;LMS_ModuleResult&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see ModuleResultModel
 * @generated
 */
@ImplementationClassName("com.ted.lms.model.impl.ModuleResultImpl")
@ProviderType
public interface ModuleResult extends ModuleResultModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.ted.lms.model.impl.ModuleResultImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<ModuleResult, Long> MR_ID_ACCESSOR =
		new Accessor<ModuleResult, Long>() {

			@Override
			public Long get(ModuleResult moduleResult) {
				return moduleResult.getMrId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<ModuleResult> getTypeClass() {
				return ModuleResult.class;
			}

		};

	public boolean isFinished();

	public String getModuleStatus(java.util.Locale locale);

}