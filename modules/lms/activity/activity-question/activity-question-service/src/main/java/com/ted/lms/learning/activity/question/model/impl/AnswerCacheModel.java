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

import aQute.bnd.annotation.ProviderType;

import com.liferay.petra.string.StringBundler;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;

import com.ted.lms.learning.activity.question.model.Answer;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Answer in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Answer
 * @generated
 */
@ProviderType
public class AnswerCacheModel implements CacheModel<Answer>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AnswerCacheModel)) {
			return false;
		}

		AnswerCacheModel answerCacheModel = (AnswerCacheModel)obj;

		if (answerId == answerCacheModel.answerId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, answerId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", answerId=");
		sb.append(answerId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", questionId=");
		sb.append(questionId);
		sb.append(", actId=");
		sb.append(actId);
		sb.append(", answer=");
		sb.append(answer);
		sb.append(", correct=");
		sb.append(correct);
		sb.append(", feedbackCorrect=");
		sb.append(feedbackCorrect);
		sb.append(", feedbackIncorrect=");
		sb.append(feedbackIncorrect);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Answer toEntityModel() {
		AnswerImpl answerImpl = new AnswerImpl();

		if (uuid == null) {
			answerImpl.setUuid("");
		}
		else {
			answerImpl.setUuid(uuid);
		}

		answerImpl.setAnswerId(answerId);
		answerImpl.setGroupId(groupId);
		answerImpl.setCompanyId(companyId);
		answerImpl.setUserId(userId);

		if (userName == null) {
			answerImpl.setUserName("");
		}
		else {
			answerImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			answerImpl.setCreateDate(null);
		}
		else {
			answerImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			answerImpl.setModifiedDate(null);
		}
		else {
			answerImpl.setModifiedDate(new Date(modifiedDate));
		}

		answerImpl.setQuestionId(questionId);
		answerImpl.setActId(actId);

		if (answer == null) {
			answerImpl.setAnswer("");
		}
		else {
			answerImpl.setAnswer(answer);
		}

		answerImpl.setCorrect(correct);

		if (feedbackCorrect == null) {
			answerImpl.setFeedbackCorrect("");
		}
		else {
			answerImpl.setFeedbackCorrect(feedbackCorrect);
		}

		if (feedbackIncorrect == null) {
			answerImpl.setFeedbackIncorrect("");
		}
		else {
			answerImpl.setFeedbackIncorrect(feedbackIncorrect);
		}

		answerImpl.resetOriginalValues();

		return answerImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		answerId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		questionId = objectInput.readLong();

		actId = objectInput.readLong();
		answer = objectInput.readUTF();

		correct = objectInput.readBoolean();
		feedbackCorrect = objectInput.readUTF();
		feedbackIncorrect = objectInput.readUTF();
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

		objectOutput.writeLong(answerId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(questionId);

		objectOutput.writeLong(actId);

		if (answer == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(answer);
		}

		objectOutput.writeBoolean(correct);

		if (feedbackCorrect == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(feedbackCorrect);
		}

		if (feedbackIncorrect == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(feedbackIncorrect);
		}
	}

	public String uuid;
	public long answerId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long questionId;
	public long actId;
	public String answer;
	public boolean correct;
	public String feedbackCorrect;
	public String feedbackIncorrect;
}