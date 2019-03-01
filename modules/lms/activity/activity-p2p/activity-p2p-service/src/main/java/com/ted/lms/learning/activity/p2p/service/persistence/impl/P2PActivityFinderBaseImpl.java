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

import com.ted.lms.learning.activity.p2p.model.P2PActivity;
import com.ted.lms.learning.activity.p2p.service.persistence.P2PActivityPersistence;

import java.lang.reflect.Field;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class P2PActivityFinderBaseImpl extends BasePersistenceImpl<P2PActivity> {
	public P2PActivityFinderBaseImpl() {
		setModelClass(P2PActivity.class);

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
		return getP2PActivityPersistence().getBadColumnNames();
	}

	/**
	 * Returns the p2p activity persistence.
	 *
	 * @return the p2p activity persistence
	 */
	public P2PActivityPersistence getP2PActivityPersistence() {
		return p2pActivityPersistence;
	}

	/**
	 * Sets the p2p activity persistence.
	 *
	 * @param p2pActivityPersistence the p2p activity persistence
	 */
	public void setP2PActivityPersistence(
		P2PActivityPersistence p2pActivityPersistence) {
		this.p2pActivityPersistence = p2pActivityPersistence;
	}

	@BeanReference(type = P2PActivityPersistence.class)
	protected P2PActivityPersistence p2pActivityPersistence;
	private static final Log _log = LogFactoryUtil.getLog(P2PActivityFinderBaseImpl.class);
}