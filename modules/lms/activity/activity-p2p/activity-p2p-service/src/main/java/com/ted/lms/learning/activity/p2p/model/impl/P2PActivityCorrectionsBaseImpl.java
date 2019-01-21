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

package com.ted.lms.learning.activity.p2p.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections;
import com.ted.lms.learning.activity.p2p.service.P2PActivityCorrectionsLocalServiceUtil;

/**
 * The extended model base implementation for the P2PActivityCorrections service. Represents a row in the &quot;PTP_P2PActivityCorrections&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link P2PActivityCorrectionsImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see P2PActivityCorrectionsImpl
 * @see P2PActivityCorrections
 * @generated
 */
@ProviderType
public abstract class P2PActivityCorrectionsBaseImpl
	extends P2PActivityCorrectionsModelImpl implements P2PActivityCorrections {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a p2p activity corrections model instance should use the {@link P2PActivityCorrections} interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			P2PActivityCorrectionsLocalServiceUtil.addP2PActivityCorrections(this);
		}
		else {
			P2PActivityCorrectionsLocalServiceUtil.updateP2PActivityCorrections(this);
		}
	}
}