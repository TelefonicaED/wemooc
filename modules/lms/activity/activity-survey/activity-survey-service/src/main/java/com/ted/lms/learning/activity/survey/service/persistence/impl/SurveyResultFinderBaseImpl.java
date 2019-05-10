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

package com.ted.lms.learning.activity.survey.service.persistence.impl;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;

import com.ted.lms.learning.activity.survey.model.SurveyResult;
import com.ted.lms.learning.activity.survey.service.persistence.SurveyResultPersistence;

import java.lang.reflect.Field;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class SurveyResultFinderBaseImpl extends BasePersistenceImpl<SurveyResult> {
	public SurveyResultFinderBaseImpl() {
		setModelClass(SurveyResult.class);

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
					"_dbColumnNames");

			field.setAccessible(true);

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("uuid", "uuid_");

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
		return getSurveyResultPersistence().getBadColumnNames();
	}

	/**
	 * Returns the survey result persistence.
	 *
	 * @return the survey result persistence
	 */
	public SurveyResultPersistence getSurveyResultPersistence() {
		return surveyResultPersistence;
	}

	/**
	 * Sets the survey result persistence.
	 *
	 * @param surveyResultPersistence the survey result persistence
	 */
	public void setSurveyResultPersistence(
		SurveyResultPersistence surveyResultPersistence) {
		this.surveyResultPersistence = surveyResultPersistence;
	}

	@BeanReference(type = SurveyResultPersistence.class)
	protected SurveyResultPersistence surveyResultPersistence;
	private static final Log _log = LogFactoryUtil.getLog(SurveyResultFinderBaseImpl.class);
}