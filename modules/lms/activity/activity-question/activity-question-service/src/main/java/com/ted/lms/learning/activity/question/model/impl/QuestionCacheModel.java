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

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.ted.lms.learning.activity.question.model.Question;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The cache model class for representing Question in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class QuestionCacheModel
	implements CacheModel<Question>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof QuestionCacheModel)) {
			return false;
		}

		QuestionCacheModel questionCacheModel = (QuestionCacheModel)obj;

		if (questionId == questionCacheModel.questionId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, questionId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(33);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", questionId=");
		sb.append(questionId);
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
		sb.append(", lastPublishDate=");
		sb.append(lastPublishDate);
		sb.append(", actId=");
		sb.append(actId);
		sb.append(", text=");
		sb.append(text);
		sb.append(", questionTypeId=");
		sb.append(questionTypeId);
		sb.append(", active=");
		sb.append(active);
		sb.append(", weight=");
		sb.append(weight);
		sb.append(", penalize=");
		sb.append(penalize);
		sb.append(", extraContent=");
		sb.append(extraContent);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Question toEntityModel() {
		QuestionImpl questionImpl = new QuestionImpl();

		if (uuid == null) {
			questionImpl.setUuid("");
		}
		else {
			questionImpl.setUuid(uuid);
		}

		questionImpl.setQuestionId(questionId);
		questionImpl.setGroupId(groupId);
		questionImpl.setCompanyId(companyId);
		questionImpl.setUserId(userId);

		if (userName == null) {
			questionImpl.setUserName("");
		}
		else {
			questionImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			questionImpl.setCreateDate(null);
		}
		else {
			questionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			questionImpl.setModifiedDate(null);
		}
		else {
			questionImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (lastPublishDate == Long.MIN_VALUE) {
			questionImpl.setLastPublishDate(null);
		}
		else {
			questionImpl.setLastPublishDate(new Date(lastPublishDate));
		}

		questionImpl.setActId(actId);

		if (text == null) {
			questionImpl.setText("");
		}
		else {
			questionImpl.setText(text);
		}

		questionImpl.setQuestionTypeId(questionTypeId);
		questionImpl.setActive(active);
		questionImpl.setWeight(weight);
		questionImpl.setPenalize(penalize);

		if (extraContent == null) {
			questionImpl.setExtraContent("");
		}
		else {
			questionImpl.setExtraContent(extraContent);
		}

		questionImpl.resetOriginalValues();

		return questionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		questionId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		lastPublishDate = objectInput.readLong();

		actId = objectInput.readLong();
		text = objectInput.readUTF();

		questionTypeId = objectInput.readLong();

		active = objectInput.readBoolean();

		weight = objectInput.readLong();

		penalize = objectInput.readBoolean();
		extraContent = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(questionId);

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
		objectOutput.writeLong(lastPublishDate);

		objectOutput.writeLong(actId);

		if (text == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(text);
		}

		objectOutput.writeLong(questionTypeId);

		objectOutput.writeBoolean(active);

		objectOutput.writeLong(weight);

		objectOutput.writeBoolean(penalize);

		if (extraContent == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(extraContent);
		}
	}

	public String uuid;
	public long questionId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long lastPublishDate;
	public long actId;
	public String text;
	public long questionTypeId;
	public boolean active;
	public long weight;
	public boolean penalize;
	public String extraContent;

}