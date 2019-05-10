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

package com.ted.lms.learning.activity.survey.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import com.ted.lms.learning.activity.survey.model.SurveyResult;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing SurveyResult in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see SurveyResult
 * @generated
 */
@ProviderType
public class SurveyResultCacheModel implements CacheModel<SurveyResult>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SurveyResultCacheModel)) {
			return false;
		}

		SurveyResultCacheModel surveyResultCacheModel = (SurveyResultCacheModel)obj;

		if (surveyResultId == surveyResultCacheModel.surveyResultId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, surveyResultId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", surveyResultId=");
		sb.append(surveyResultId);
		sb.append(", actId=");
		sb.append(actId);
		sb.append(", latId=");
		sb.append(latId);
		sb.append(", questionId=");
		sb.append(questionId);
		sb.append(", answerId=");
		sb.append(answerId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", freeAnswer=");
		sb.append(freeAnswer);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SurveyResult toEntityModel() {
		SurveyResultImpl surveyResultImpl = new SurveyResultImpl();

		if (uuid == null) {
			surveyResultImpl.setUuid("");
		}
		else {
			surveyResultImpl.setUuid(uuid);
		}

		surveyResultImpl.setSurveyResultId(surveyResultId);
		surveyResultImpl.setActId(actId);
		surveyResultImpl.setLatId(latId);
		surveyResultImpl.setQuestionId(questionId);
		surveyResultImpl.setAnswerId(answerId);
		surveyResultImpl.setUserId(userId);

		if (freeAnswer == null) {
			surveyResultImpl.setFreeAnswer("");
		}
		else {
			surveyResultImpl.setFreeAnswer(freeAnswer);
		}

		surveyResultImpl.resetOriginalValues();

		return surveyResultImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		surveyResultId = objectInput.readLong();

		actId = objectInput.readLong();

		latId = objectInput.readLong();

		questionId = objectInput.readLong();

		answerId = objectInput.readLong();

		userId = objectInput.readLong();
		freeAnswer = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(surveyResultId);

		objectOutput.writeLong(actId);

		objectOutput.writeLong(latId);

		objectOutput.writeLong(questionId);

		objectOutput.writeLong(answerId);

		objectOutput.writeLong(userId);

		if (freeAnswer == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(freeAnswer);
		}
	}

	public String uuid;
	public long surveyResultId;
	public long actId;
	public long latId;
	public long questionId;
	public long answerId;
	public long userId;
	public String freeAnswer;
}