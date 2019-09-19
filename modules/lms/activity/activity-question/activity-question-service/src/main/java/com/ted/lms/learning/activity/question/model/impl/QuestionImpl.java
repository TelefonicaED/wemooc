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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.ted.lms.learning.activity.question.model.Answer;
import com.ted.lms.learning.activity.question.model.QuestionType;
import com.ted.lms.learning.activity.question.model.QuestionTypeFactory;
import com.ted.lms.learning.activity.question.registry.QuestionTypeFactoryRegistryUtil;
import com.ted.lms.learning.activity.question.service.AnswerLocalServiceUtil;

import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model implementation for the Question service. Represents a row in the &quot;QU_Question&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.ted.lms.learning.activity.question.model.Question<code> interface.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class QuestionImpl extends QuestionBaseImpl {

	private static final Log log = LogFactoryUtil.getLog(QuestionImpl.class);
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a question model instance should use the {@link com.ted.lms.learning.activity.question.model.Question} interface instead.
	 */
	private JSONObject extraContentJSON = null;
	
	private QuestionType questionType = null;
	
	public QuestionImpl() {
	}
	
	@Override
	public JSONObject getExtraContentJSON() {
		if(extraContentJSON == null) {
			try {
				extraContentJSON = JSONFactoryUtil.createJSONObject(getExtraContent());
			} catch (JSONException e) {
				e.printStackTrace();
				extraContentJSON = JSONFactoryUtil.createJSONObject();
			}
		}
		return extraContentJSON;
	}

	public void setExtraContentJSON(JSONObject extraContent) {
		this.extraContentJSON = extraContent;
		super.setExtraContent(extraContent.toJSONString());
	}
	
	@Override
	public QuestionType getQuestionType() throws PortalException {
		if(questionType == null) {
			QuestionTypeFactory questionTypeFactory = QuestionTypeFactoryRegistryUtil.getQuestionTypeFactoryByType(getQuestionTypeId());
			questionType = questionTypeFactory.getQuestionType(this);
		}
		return questionType;
	}
	
	@Override
	public List<Answer> getAnswers(){
		return AnswerLocalServiceUtil.getAnswersByQuestionId(getQuestionId());
	}

}