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
 * The extended model interface for the P2PActivityCorrections service. Represents a row in the &quot;LMS_P2PActivityCorrections&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see P2PActivityCorrectionsModel
 * @see com.ted.lms.learning.activity.p2p.model.impl.P2PActivityCorrectionsImpl
 * @see com.ted.lms.learning.activity.p2p.model.impl.P2PActivityCorrectionsModelImpl
 * @generated
 */
@ImplementationClassName("com.ted.lms.learning.activity.p2p.model.impl.P2PActivityCorrectionsImpl")
@ProviderType
public interface P2PActivityCorrections extends P2PActivityCorrectionsModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.ted.lms.learning.activity.p2p.model.impl.P2PActivityCorrectionsImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<P2PActivityCorrections, Long> P2P_ACTIVITY_CORRECTIONS_ID_ACCESSOR =
		new Accessor<P2PActivityCorrections, Long>() {
			@Override
			public Long get(P2PActivityCorrections p2pActivityCorrections) {
				return p2pActivityCorrections.getP2pActivityCorrectionsId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<P2PActivityCorrections> getTypeClass() {
				return P2PActivityCorrections.class;
			}
		};
}