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

package com.ted.prerequisite.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the PrerequisiteRelation service. Represents a row in the &quot;pre_PrerequisiteRelation&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see PrerequisiteRelationModel
 * @see com.ted.prerequisite.model.impl.PrerequisiteRelationImpl
 * @see com.ted.prerequisite.model.impl.PrerequisiteRelationModelImpl
 * @generated
 */
@ImplementationClassName("com.ted.prerequisite.model.impl.PrerequisiteRelationImpl")
@ProviderType
public interface PrerequisiteRelation extends PrerequisiteRelationModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.ted.prerequisite.model.impl.PrerequisiteRelationImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<PrerequisiteRelation, Long> PREREQUISITE_RELATION_ID_ACCESSOR =
		new Accessor<PrerequisiteRelation, Long>() {
			@Override
			public Long get(PrerequisiteRelation prerequisiteRelation) {
				return prerequisiteRelation.getPrerequisiteRelationId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<PrerequisiteRelation> getTypeClass() {
				return PrerequisiteRelation.class;
			}
		};
}