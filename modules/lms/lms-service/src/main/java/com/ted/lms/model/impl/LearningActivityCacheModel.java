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

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import com.ted.lms.model.LearningActivity;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing LearningActivity in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see LearningActivity
 * @generated
 */
@ProviderType
public class LearningActivityCacheModel implements CacheModel<LearningActivity>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LearningActivityCacheModel)) {
			return false;
		}

		LearningActivityCacheModel learningActivityCacheModel = (LearningActivityCacheModel)obj;

		if (actId == learningActivityCacheModel.actId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, actId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(55);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", actId=");
		sb.append(actId);
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
		sb.append(", moduleId=");
		sb.append(moduleId);
		sb.append(", title=");
		sb.append(title);
		sb.append(", description=");
		sb.append(description);
		sb.append(", typeId=");
		sb.append(typeId);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", endDate=");
		sb.append(endDate);
		sb.append(", tries=");
		sb.append(tries);
		sb.append(", passPuntuation=");
		sb.append(passPuntuation);
		sb.append(", priority=");
		sb.append(priority);
		sb.append(", extraContent=");
		sb.append(extraContent);
		sb.append(", feedbackCorrect=");
		sb.append(feedbackCorrect);
		sb.append(", feedbackNoCorrect=");
		sb.append(feedbackNoCorrect);
		sb.append(", required=");
		sb.append(required);
		sb.append(", commentsActivated=");
		sb.append(commentsActivated);
		sb.append(", status=");
		sb.append(status);
		sb.append(", statusByUserId=");
		sb.append(statusByUserId);
		sb.append(", statusByUserName=");
		sb.append(statusByUserName);
		sb.append(", statusDate=");
		sb.append(statusDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public LearningActivity toEntityModel() {
		LearningActivityImpl learningActivityImpl = new LearningActivityImpl();

		if (uuid == null) {
			learningActivityImpl.setUuid("");
		}
		else {
			learningActivityImpl.setUuid(uuid);
		}

		learningActivityImpl.setActId(actId);
		learningActivityImpl.setGroupId(groupId);
		learningActivityImpl.setCompanyId(companyId);
		learningActivityImpl.setUserId(userId);

		if (userName == null) {
			learningActivityImpl.setUserName("");
		}
		else {
			learningActivityImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			learningActivityImpl.setCreateDate(null);
		}
		else {
			learningActivityImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			learningActivityImpl.setModifiedDate(null);
		}
		else {
			learningActivityImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (lastPublishDate == Long.MIN_VALUE) {
			learningActivityImpl.setLastPublishDate(null);
		}
		else {
			learningActivityImpl.setLastPublishDate(new Date(lastPublishDate));
		}

		learningActivityImpl.setModuleId(moduleId);

		if (title == null) {
			learningActivityImpl.setTitle("");
		}
		else {
			learningActivityImpl.setTitle(title);
		}

		if (description == null) {
			learningActivityImpl.setDescription("");
		}
		else {
			learningActivityImpl.setDescription(description);
		}

		learningActivityImpl.setTypeId(typeId);

		if (startDate == Long.MIN_VALUE) {
			learningActivityImpl.setStartDate(null);
		}
		else {
			learningActivityImpl.setStartDate(new Date(startDate));
		}

		if (endDate == Long.MIN_VALUE) {
			learningActivityImpl.setEndDate(null);
		}
		else {
			learningActivityImpl.setEndDate(new Date(endDate));
		}

		learningActivityImpl.setTries(tries);
		learningActivityImpl.setPassPuntuation(passPuntuation);
		learningActivityImpl.setPriority(priority);

		if (extraContent == null) {
			learningActivityImpl.setExtraContent("");
		}
		else {
			learningActivityImpl.setExtraContent(extraContent);
		}

		if (feedbackCorrect == null) {
			learningActivityImpl.setFeedbackCorrect("");
		}
		else {
			learningActivityImpl.setFeedbackCorrect(feedbackCorrect);
		}

		if (feedbackNoCorrect == null) {
			learningActivityImpl.setFeedbackNoCorrect("");
		}
		else {
			learningActivityImpl.setFeedbackNoCorrect(feedbackNoCorrect);
		}

		learningActivityImpl.setRequired(required);
		learningActivityImpl.setCommentsActivated(commentsActivated);
		learningActivityImpl.setStatus(status);
		learningActivityImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			learningActivityImpl.setStatusByUserName("");
		}
		else {
			learningActivityImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			learningActivityImpl.setStatusDate(null);
		}
		else {
			learningActivityImpl.setStatusDate(new Date(statusDate));
		}

		learningActivityImpl.resetOriginalValues();

		return learningActivityImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		actId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		lastPublishDate = objectInput.readLong();

		moduleId = objectInput.readLong();
		title = objectInput.readUTF();
		description = objectInput.readUTF();

		typeId = objectInput.readLong();
		startDate = objectInput.readLong();
		endDate = objectInput.readLong();

		tries = objectInput.readInt();

		passPuntuation = objectInput.readInt();

		priority = objectInput.readLong();
		extraContent = objectInput.readUTF();
		feedbackCorrect = objectInput.readUTF();
		feedbackNoCorrect = objectInput.readUTF();

		required = objectInput.readBoolean();

		commentsActivated = objectInput.readBoolean();

		status = objectInput.readInt();

		statusByUserId = objectInput.readLong();
		statusByUserName = objectInput.readUTF();
		statusDate = objectInput.readLong();
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

		objectOutput.writeLong(actId);

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

		objectOutput.writeLong(moduleId);

		if (title == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(title);
		}

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}

		objectOutput.writeLong(typeId);
		objectOutput.writeLong(startDate);
		objectOutput.writeLong(endDate);

		objectOutput.writeInt(tries);

		objectOutput.writeInt(passPuntuation);

		objectOutput.writeLong(priority);

		if (extraContent == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(extraContent);
		}

		if (feedbackCorrect == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(feedbackCorrect);
		}

		if (feedbackNoCorrect == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(feedbackNoCorrect);
		}

		objectOutput.writeBoolean(required);

		objectOutput.writeBoolean(commentsActivated);

		objectOutput.writeInt(status);

		objectOutput.writeLong(statusByUserId);

		if (statusByUserName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(statusByUserName);
		}

		objectOutput.writeLong(statusDate);
	}

	public String uuid;
	public long actId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long lastPublishDate;
	public long moduleId;
	public String title;
	public String description;
	public long typeId;
	public long startDate;
	public long endDate;
	public int tries;
	public int passPuntuation;
	public long priority;
	public String extraContent;
	public String feedbackCorrect;
	public String feedbackNoCorrect;
	public boolean required;
	public boolean commentsActivated;
	public int status;
	public long statusByUserId;
	public String statusByUserName;
	public long statusDate;
}