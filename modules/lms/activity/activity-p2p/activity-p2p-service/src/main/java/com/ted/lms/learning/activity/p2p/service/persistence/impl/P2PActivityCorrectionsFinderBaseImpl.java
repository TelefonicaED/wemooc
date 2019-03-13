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

package com.ted.lms.learning.activity.p2p.service.persistence.impl;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;

import com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections;
import com.ted.lms.learning.activity.p2p.service.persistence.P2PActivityCorrectionsPersistence;

import java.lang.reflect.Field;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class P2PActivityCorrectionsFinderBaseImpl extends BasePersistenceImpl<P2PActivityCorrections> {
	public P2PActivityCorrectionsFinderBaseImpl() {
		setModelClass(P2PActivityCorrections.class);

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
					"_dbColumnNames");

			field.setAccessible(true);

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("uuid", "uuid_");
			dbColumnNames.put("date", "date_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	@Override
	public Set<String> getBadColumnNames() {
		return getP2PActivityCorrectionsPersistence().getBadColumnNames();
	}

	/**
	 * Returns the p2p activity corrections persistence.
	 *
	 * @return the p2p activity corrections persistence
	 */
	public P2PActivityCorrectionsPersistence getP2PActivityCorrectionsPersistence() {
		return p2pActivityCorrectionsPersistence;
	}

	/**
	 * Sets the p2p activity corrections persistence.
	 *
	 * @param p2pActivityCorrectionsPersistence the p2p activity corrections persistence
	 */
	public void setP2PActivityCorrectionsPersistence(
		P2PActivityCorrectionsPersistence p2pActivityCorrectionsPersistence) {
		this.p2pActivityCorrectionsPersistence = p2pActivityCorrectionsPersistence;
	}

	@BeanReference(type = P2PActivityCorrectionsPersistence.class)
	protected P2PActivityCorrectionsPersistence p2pActivityCorrectionsPersistence;
	private static final Log _log = LogFactoryUtil.getLog(P2PActivityCorrectionsFinderBaseImpl.class);
}