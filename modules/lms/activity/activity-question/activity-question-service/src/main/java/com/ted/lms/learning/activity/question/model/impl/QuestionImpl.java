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

package com.ted.lms.learning.activity.question.model.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.Validator;

import aQute.bnd.annotation.ProviderType;

/**
 * The extended model implementation for the Question service. Represents a row in the &quot;qu_Question&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ted.lms.learning.activity.question.model.Question} interface.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class QuestionImpl extends QuestionBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a question model instance should use the {@link com.ted.lms.learning.activity.question.model.Question} interface instead.
	 */
	private JSONObject extraContent = null;
	
	public QuestionImpl() {
	}
	
	@Override
	public String getTextMapAsXML() {
		return LocalizationUtil.updateLocalization(getTextMap(), StringPool.BLANK, "Text",getDefaultLanguageId());
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
	public void setExtraContentJSON(JSONObject extraContent) {
		this.extraContent = extraContent;
		super.setExtraContent(extraContent.toJSONString());
	}
}