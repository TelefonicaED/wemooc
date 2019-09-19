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

package com.ted.postcondition.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the PostconditionRelation service. Represents a row in the &quot;post_PostconditionRelation&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see PostconditionRelationModel
 * @generated
 */
@ImplementationClassName(
	"com.ted.postcondition.model.impl.PostconditionRelationImpl"
)
@ProviderType
public interface PostconditionRelation
	extends PersistedModel, PostconditionRelationModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.ted.postcondition.model.impl.PostconditionRelationImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<PostconditionRelation, Long>
		POSTCONDITION_RELATION_ID_ACCESSOR =
			new Accessor<PostconditionRelation, Long>() {

				@Override
				public Long get(PostconditionRelation postconditionRelation) {
					return postconditionRelation.getPostconditionRelationId();
				}

				@Override
				public Class<Long> getAttributeClass() {
					return Long.class;
				}

				@Override
				public Class<PostconditionRelation> getTypeClass() {
					return PostconditionRelation.class;
				}

			};

	public PostconditionFactory getPostconditionFactory();

	public Postcondition getPostcondition();

	public com.liferay.portal.kernel.json.JSONObject getExtraDataJSON();

}