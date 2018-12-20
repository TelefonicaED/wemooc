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

import java.util.Calendar;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import aQute.bnd.annotation.ProviderType;

/**
 * The extended model implementation for the LearningActivity service. Represents a row in the &quot;LMS_LearningActivity&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ted.lms.model.LearningActivity} interface.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class LearningActivityImpl extends LearningActivityBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a learning activity model instance should use the {@link com.ted.lms.model.LearningActivity} interface instead.
	 */
	private JSONObject extraContent = null;
	
	public LearningActivityImpl() {
	}
	
	@Override
	public JSONObject getExtraContentJSON() {
		if(extraContent == null) {
			try {
				extraContent = JSONFactoryUtil.createJSONObject(getExtraContent());
			} catch (JSONException e) {
				e.printStackTrace();
				extraContent = JSONFactoryUtil.createJSONObject();
			}
		}
		return extraContent;
	}
	
	@Override
	public void setExtraContent(String activityExtraContent) {
		if(Validator.isNotNull(activityExtraContent)) {
			try {
				extraContent = JSONFactoryUtil.createJSONObject(activityExtraContent);
			} catch (JSONException e) {
				extraContent = JSONFactoryUtil.createJSONObject();
			}
		}else {
			extraContent = JSONFactoryUtil.createJSONObject();
		}
		
		super.setExtraContent(activityExtraContent);
	}
	
	@Override
	public void addExtraContentJSON(String key, Object value) {
		JSONObject extraContent = getExtraContentJSON();
		extraContent.put(key, value);
		this.extraContent = extraContent;
	}
	
	public Calendar getStartDateCalendar() {
		Calendar startDate = Calendar.getInstance();
		startDate.setTime(getStartDate());
		return startDate;
	}
	
	public Calendar getEndDateCalendar() {
		Calendar endDate = Calendar.getInstance();
		endDate.setTime(getEndDate());
		return endDate;
	}
	
	@Override
	public String getURLView(LiferayPortletRequest liferayPortletRequest) {
		
		ThemeDisplay themeDisplay = (ThemeDisplay)liferayPortletRequest.getAttribute(WebKeys.THEME_DISPLAY);

		StringBundler sb = new StringBundler(11);

		sb.append(themeDisplay.getPortalURL());
		sb.append(themeDisplay.getPathMain());
		sb.append("/activities/find_activity");
		sb.append("?p_l_id=");
		sb.append(themeDisplay.getPlid());
		sb.append(StringPool.AMPERSAND);
		sb.append("actId");
		sb.append(StringPool.EQUAL);
		sb.append(String.valueOf(getActId()));
		sb.append(StringPool.AMPERSAND);
		sb.append(Constants.CMD);
		sb.append(StringPool.EQUAL);
		sb.append(Constants.VIEW);

		return PortalUtil.addPreservedParameters(themeDisplay, sb.toString());
	}
	
	@Override
	public String getURLEdit(LiferayPortletRequest liferayPortletRequest) {
		
		ThemeDisplay themeDisplay = (ThemeDisplay)liferayPortletRequest.getAttribute(WebKeys.THEME_DISPLAY);

		StringBundler sb = new StringBundler(11);

		sb.append(themeDisplay.getPortalURL());
		sb.append(themeDisplay.getPathMain());
		sb.append("/activities/find_activity");
		sb.append("?p_l_id=");
		sb.append(themeDisplay.getPlid());
		sb.append(StringPool.AMPERSAND);
		sb.append("actId");
		sb.append(StringPool.EQUAL);
		sb.append(String.valueOf(getActId()));
		sb.append(StringPool.AMPERSAND);
		sb.append(Constants.CMD);
		sb.append(StringPool.EQUAL);
		sb.append(Constants.EDIT);

		return PortalUtil.addPreservedParameters(themeDisplay, sb.toString());
	}
	
	@Override
	public String getDescriptionMapAsXML() {
		return LocalizationUtil.updateLocalization(
			getDescriptionMap(), StringPool.BLANK, "Description",
			getDefaultLanguageId());
	}
	
}