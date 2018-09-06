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

package com.ted.lms.model.impl;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.model.impl.GroupImpl;
import com.ted.lms.constants.CourseConstants;

import aQute.bnd.annotation.ProviderType;

/**
 * The extended model implementation for the Course service. Represents a row in the &quot;LMS_Course&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ted.lms.model.Course} interface.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class CourseImpl extends CourseBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a course model instance should use the {@link com.ted.lms.model.Course} interface instead.
	 */
	private Group _group;
	private JSONObject extraData = null;
	
	public String getFriendlyURL() {
		if (_group == NULL_GROUP) {
			return null;
		}

		if (_group == null) {
			Group group = GroupLocalServiceUtil.fetchGroup(getGroupCreatedId());

			if (group == null) {
				_group = NULL_GROUP;
			}
			else {
				_group = group;
			}
		}

		return _group.getFriendlyURL();
	}
	
	private static final Group NULL_GROUP = new GroupImpl();

	@Override
	public JSONObject getCourseExtraDataJSON() {
		System.out.println("extraData: " + extraData);
		if(extraData == null) {
			System.out.println("extraData no cargado todavía");
			try {
				System.out.println("cargamos extraDatA: " + getCourseExtraData());
				extraData = JSONFactoryUtil.createJSONObject(getCourseExtraData());
			} catch (JSONException e) {
				e.printStackTrace();
				extraData = JSONFactoryUtil.createJSONObject();
			}
		}
		System.out.println("devolvemos: " + extraData.toJSONString());
		return extraData;
	}
	
	@Override
	public void setCourseExtraData(String courseExtraData) {
		System.out.println("setextraData: " + courseExtraData);
		try {
			extraData = JSONFactoryUtil.createJSONObject(courseExtraData);
		} catch (JSONException e) {
			extraData = JSONFactoryUtil.createJSONObject();
		}
		
		super.setCourseExtraData(courseExtraData);
	}
	
	@Override
	public void addCourseExtraDataJSON(String key, Object value) {
		JSONObject extraData = getCourseExtraDataJSON();
		extraData.put(key, value);
		this.extraData = extraData;
	}
	
	public JSONObject getCourseEvalJSON() {
		return getCourseExtraDataJSON().getJSONObject(CourseConstants.JSON_COURSE_EVAL);
	}
	
}