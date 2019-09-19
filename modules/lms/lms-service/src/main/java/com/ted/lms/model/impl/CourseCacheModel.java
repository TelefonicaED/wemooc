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

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.ted.lms.model.Course;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The cache model class for representing Course in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class CourseCacheModel implements CacheModel<Course>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CourseCacheModel)) {
			return false;
		}

		CourseCacheModel courseCacheModel = (CourseCacheModel)obj;

		if (courseId == courseCacheModel.courseId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, courseId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(73);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", courseId=");
		sb.append(courseId);
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
		sb.append(", parentCourseId=");
		sb.append(parentCourseId);
		sb.append(", groupCreatedId=");
		sb.append(groupCreatedId);
		sb.append(", title=");
		sb.append(title);
		sb.append(", description=");
		sb.append(description);
		sb.append(", smallImageId=");
		sb.append(smallImageId);
		sb.append(", registrationStartDate=");
		sb.append(registrationStartDate);
		sb.append(", registrationEndDate=");
		sb.append(registrationEndDate);
		sb.append(", executionStartDate=");
		sb.append(executionStartDate);
		sb.append(", executionEndDate=");
		sb.append(executionEndDate);
		sb.append(", maxUsers=");
		sb.append(maxUsers);
		sb.append(", inscriptionType=");
		sb.append(inscriptionType);
		sb.append(", courseEvalId=");
		sb.append(courseEvalId);
		sb.append(", calificationType=");
		sb.append(calificationType);
		sb.append(", welcome=");
		sb.append(welcome);
		sb.append(", welcomeSubject=");
		sb.append(welcomeSubject);
		sb.append(", welcomeMsg=");
		sb.append(welcomeMsg);
		sb.append(", goodbye=");
		sb.append(goodbye);
		sb.append(", goodbyeSubject=");
		sb.append(goodbyeSubject);
		sb.append(", goodbyeMsg=");
		sb.append(goodbyeMsg);
		sb.append(", deniedInscription=");
		sb.append(deniedInscription);
		sb.append(", deniedInscriptionSubject=");
		sb.append(deniedInscriptionSubject);
		sb.append(", deniedInscriptionMsg=");
		sb.append(deniedInscriptionMsg);
		sb.append(", courseExtraData=");
		sb.append(courseExtraData);
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
	public Course toEntityModel() {
		CourseImpl courseImpl = new CourseImpl();

		if (uuid == null) {
			courseImpl.setUuid("");
		}
		else {
			courseImpl.setUuid(uuid);
		}

		courseImpl.setCourseId(courseId);
		courseImpl.setGroupId(groupId);
		courseImpl.setCompanyId(companyId);
		courseImpl.setUserId(userId);

		if (userName == null) {
			courseImpl.setUserName("");
		}
		else {
			courseImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			courseImpl.setCreateDate(null);
		}
		else {
			courseImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			courseImpl.setModifiedDate(null);
		}
		else {
			courseImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (lastPublishDate == Long.MIN_VALUE) {
			courseImpl.setLastPublishDate(null);
		}
		else {
			courseImpl.setLastPublishDate(new Date(lastPublishDate));
		}

		courseImpl.setParentCourseId(parentCourseId);
		courseImpl.setGroupCreatedId(groupCreatedId);

		if (title == null) {
			courseImpl.setTitle("");
		}
		else {
			courseImpl.setTitle(title);
		}

		if (description == null) {
			courseImpl.setDescription("");
		}
		else {
			courseImpl.setDescription(description);
		}

		courseImpl.setSmallImageId(smallImageId);

		if (registrationStartDate == Long.MIN_VALUE) {
			courseImpl.setRegistrationStartDate(null);
		}
		else {
			courseImpl.setRegistrationStartDate(
				new Date(registrationStartDate));
		}

		if (registrationEndDate == Long.MIN_VALUE) {
			courseImpl.setRegistrationEndDate(null);
		}
		else {
			courseImpl.setRegistrationEndDate(new Date(registrationEndDate));
		}

		if (executionStartDate == Long.MIN_VALUE) {
			courseImpl.setExecutionStartDate(null);
		}
		else {
			courseImpl.setExecutionStartDate(new Date(executionStartDate));
		}

		if (executionEndDate == Long.MIN_VALUE) {
			courseImpl.setExecutionEndDate(null);
		}
		else {
			courseImpl.setExecutionEndDate(new Date(executionEndDate));
		}

		courseImpl.setMaxUsers(maxUsers);
		courseImpl.setInscriptionType(inscriptionType);
		courseImpl.setCourseEvalId(courseEvalId);
		courseImpl.setCalificationType(calificationType);
		courseImpl.setWelcome(welcome);

		if (welcomeSubject == null) {
			courseImpl.setWelcomeSubject("");
		}
		else {
			courseImpl.setWelcomeSubject(welcomeSubject);
		}

		if (welcomeMsg == null) {
			courseImpl.setWelcomeMsg("");
		}
		else {
			courseImpl.setWelcomeMsg(welcomeMsg);
		}

		courseImpl.setGoodbye(goodbye);

		if (goodbyeSubject == null) {
			courseImpl.setGoodbyeSubject("");
		}
		else {
			courseImpl.setGoodbyeSubject(goodbyeSubject);
		}

		if (goodbyeMsg == null) {
			courseImpl.setGoodbyeMsg("");
		}
		else {
			courseImpl.setGoodbyeMsg(goodbyeMsg);
		}

		courseImpl.setDeniedInscription(deniedInscription);

		if (deniedInscriptionSubject == null) {
			courseImpl.setDeniedInscriptionSubject("");
		}
		else {
			courseImpl.setDeniedInscriptionSubject(deniedInscriptionSubject);
		}

		if (deniedInscriptionMsg == null) {
			courseImpl.setDeniedInscriptionMsg("");
		}
		else {
			courseImpl.setDeniedInscriptionMsg(deniedInscriptionMsg);
		}

		if (courseExtraData == null) {
			courseImpl.setCourseExtraData("");
		}
		else {
			courseImpl.setCourseExtraData(courseExtraData);
		}

		courseImpl.setStatus(status);
		courseImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			courseImpl.setStatusByUserName("");
		}
		else {
			courseImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			courseImpl.setStatusDate(null);
		}
		else {
			courseImpl.setStatusDate(new Date(statusDate));
		}

		courseImpl.resetOriginalValues();

		return courseImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		courseId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		lastPublishDate = objectInput.readLong();

		parentCourseId = objectInput.readLong();

		groupCreatedId = objectInput.readLong();
		title = objectInput.readUTF();
		description = objectInput.readUTF();

		smallImageId = objectInput.readLong();
		registrationStartDate = objectInput.readLong();
		registrationEndDate = objectInput.readLong();
		executionStartDate = objectInput.readLong();
		executionEndDate = objectInput.readLong();

		maxUsers = objectInput.readInt();

		inscriptionType = objectInput.readLong();

		courseEvalId = objectInput.readLong();

		calificationType = objectInput.readLong();

		welcome = objectInput.readBoolean();
		welcomeSubject = objectInput.readUTF();
		welcomeMsg = objectInput.readUTF();

		goodbye = objectInput.readBoolean();
		goodbyeSubject = objectInput.readUTF();
		goodbyeMsg = objectInput.readUTF();

		deniedInscription = objectInput.readBoolean();
		deniedInscriptionSubject = objectInput.readUTF();
		deniedInscriptionMsg = objectInput.readUTF();
		courseExtraData = objectInput.readUTF();

		status = objectInput.readInt();

		statusByUserId = objectInput.readLong();
		statusByUserName = objectInput.readUTF();
		statusDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(courseId);

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

		objectOutput.writeLong(parentCourseId);

		objectOutput.writeLong(groupCreatedId);

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

		objectOutput.writeLong(smallImageId);
		objectOutput.writeLong(registrationStartDate);
		objectOutput.writeLong(registrationEndDate);
		objectOutput.writeLong(executionStartDate);
		objectOutput.writeLong(executionEndDate);

		objectOutput.writeInt(maxUsers);

		objectOutput.writeLong(inscriptionType);

		objectOutput.writeLong(courseEvalId);

		objectOutput.writeLong(calificationType);

		objectOutput.writeBoolean(welcome);

		if (welcomeSubject == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(welcomeSubject);
		}

		if (welcomeMsg == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(welcomeMsg);
		}

		objectOutput.writeBoolean(goodbye);

		if (goodbyeSubject == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(goodbyeSubject);
		}

		if (goodbyeMsg == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(goodbyeMsg);
		}

		objectOutput.writeBoolean(deniedInscription);

		if (deniedInscriptionSubject == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(deniedInscriptionSubject);
		}

		if (deniedInscriptionMsg == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(deniedInscriptionMsg);
		}

		if (courseExtraData == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(courseExtraData);
		}

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
	public long courseId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long lastPublishDate;
	public long parentCourseId;
	public long groupCreatedId;
	public String title;
	public String description;
	public long smallImageId;
	public long registrationStartDate;
	public long registrationEndDate;
	public long executionStartDate;
	public long executionEndDate;
	public int maxUsers;
	public long inscriptionType;
	public long courseEvalId;
	public long calificationType;
	public boolean welcome;
	public String welcomeSubject;
	public String welcomeMsg;
	public boolean goodbye;
	public String goodbyeSubject;
	public String goodbyeMsg;
	public boolean deniedInscription;
	public String deniedInscriptionSubject;
	public String deniedInscriptionMsg;
	public String courseExtraData;
	public int status;
	public long statusByUserId;
	public String statusByUserName;
	public long statusDate;

}