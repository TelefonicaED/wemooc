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

package com.ted.lms.learning.activity.p2p.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the P2PActivity service. Represents a row in the &quot;LMS_P2PActivity&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see P2PActivityModel
 * @see com.ted.lms.learning.activity.p2p.model.impl.P2PActivityImpl
 * @see com.ted.lms.learning.activity.p2p.model.impl.P2PActivityModelImpl
 * @generated
 */
@ImplementationClassName("com.ted.lms.learning.activity.p2p.model.impl.P2PActivityImpl")
@ProviderType
public interface P2PActivity extends P2PActivityModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.ted.lms.learning.activity.p2p.model.impl.P2PActivityImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<P2PActivity, Long> P2P_ACTIVITY_ID_ACCESSOR = new Accessor<P2PActivity, Long>() {
			@Override
			public Long get(P2PActivity p2pActivity) {
				return p2pActivity.getP2pActivityId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<P2PActivity> getTypeClass() {
				return P2PActivity.class;
			}
		};
}