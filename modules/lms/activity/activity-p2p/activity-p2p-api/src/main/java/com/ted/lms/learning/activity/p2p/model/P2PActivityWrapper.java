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

package com.ted.lms.learning.activity.p2p.model;

import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * <p>
 * This class is a wrapper for {@link P2PActivity}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see P2PActivity
 * @generated
 */
@ProviderType
public class P2PActivityWrapper
	extends BaseModelWrapper<P2PActivity>
	implements P2PActivity, ModelWrapper<P2PActivity> {

	public P2PActivityWrapper(P2PActivity p2pActivity) {
		super(p2pActivity);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("p2pActivityId", getP2pActivityId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userCreateId", getUserCreateId());
		attributes.put("userCreateName", getUserCreateName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("actId", getActId());
		attributes.put("userId", getUserId());
		attributes.put("fileEntryId", getFileEntryId());
		attributes.put("countCorrections", getCountCorrections());
		attributes.put("description", getDescription());
		attributes.put("date", getDate());
		attributes.put("asignationsCompleted", isAsignationsCompleted());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long p2pActivityId = (Long)attributes.get("p2pActivityId");

		if (p2pActivityId != null) {
			setP2pActivityId(p2pActivityId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userCreateId = (Long)attributes.get("userCreateId");

		if (userCreateId != null) {
			setUserCreateId(userCreateId);
		}

		String userCreateName = (String)attributes.get("userCreateName");

		if (userCreateName != null) {
			setUserCreateName(userCreateName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long actId = (Long)attributes.get("actId");

		if (actId != null) {
			setActId(actId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Long fileEntryId = (Long)attributes.get("fileEntryId");

		if (fileEntryId != null) {
			setFileEntryId(fileEntryId);
		}

		Long countCorrections = (Long)attributes.get("countCorrections");

		if (countCorrections != null) {
			setCountCorrections(countCorrections);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Date date = (Date)attributes.get("date");

		if (date != null) {
			setDate(date);
		}

		Boolean asignationsCompleted = (Boolean)attributes.get(
			"asignationsCompleted");

		if (asignationsCompleted != null) {
			setAsignationsCompleted(asignationsCompleted);
		}
	}

	/**
	 * Returns the act ID of this p2p activity.
	 *
	 * @return the act ID of this p2p activity
	 */
	@Override
	public long getActId() {
		return model.getActId();
	}

	/**
	 * Returns the asignations completed of this p2p activity.
	 *
	 * @return the asignations completed of this p2p activity
	 */
	@Override
	public boolean getAsignationsCompleted() {
		return model.getAsignationsCompleted();
	}

	/**
	 * Returns the company ID of this p2p activity.
	 *
	 * @return the company ID of this p2p activity
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the count corrections of this p2p activity.
	 *
	 * @return the count corrections of this p2p activity
	 */
	@Override
	public long getCountCorrections() {
		return model.getCountCorrections();
	}

	/**
	 * Returns the create date of this p2p activity.
	 *
	 * @return the create date of this p2p activity
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the date of this p2p activity.
	 *
	 * @return the date of this p2p activity
	 */
	@Override
	public Date getDate() {
		return model.getDate();
	}

	/**
	 * Returns the description of this p2p activity.
	 *
	 * @return the description of this p2p activity
	 */
	@Override
	public String getDescription() {
		return model.getDescription();
	}

	/**
	 * Returns the file entry ID of this p2p activity.
	 *
	 * @return the file entry ID of this p2p activity
	 */
	@Override
	public long getFileEntryId() {
		return model.getFileEntryId();
	}

	/**
	 * Returns the group ID of this p2p activity.
	 *
	 * @return the group ID of this p2p activity
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the modified date of this p2p activity.
	 *
	 * @return the modified date of this p2p activity
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the p2p activity ID of this p2p activity.
	 *
	 * @return the p2p activity ID of this p2p activity
	 */
	@Override
	public long getP2pActivityId() {
		return model.getP2pActivityId();
	}

	/**
	 * Returns the primary key of this p2p activity.
	 *
	 * @return the primary key of this p2p activity
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the user create ID of this p2p activity.
	 *
	 * @return the user create ID of this p2p activity
	 */
	@Override
	public long getUserCreateId() {
		return model.getUserCreateId();
	}

	/**
	 * Returns the user create name of this p2p activity.
	 *
	 * @return the user create name of this p2p activity
	 */
	@Override
	public String getUserCreateName() {
		return model.getUserCreateName();
	}

	/**
	 * Returns the user ID of this p2p activity.
	 *
	 * @return the user ID of this p2p activity
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this p2p activity.
	 *
	 * @return the user uuid of this p2p activity
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the uuid of this p2p activity.
	 *
	 * @return the uuid of this p2p activity
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	/**
	 * Returns <code>true</code> if this p2p activity is asignations completed.
	 *
	 * @return <code>true</code> if this p2p activity is asignations completed; <code>false</code> otherwise
	 */
	@Override
	public boolean isAsignationsCompleted() {
		return model.isAsignationsCompleted();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the act ID of this p2p activity.
	 *
	 * @param actId the act ID of this p2p activity
	 */
	@Override
	public void setActId(long actId) {
		model.setActId(actId);
	}

	/**
	 * Sets whether this p2p activity is asignations completed.
	 *
	 * @param asignationsCompleted the asignations completed of this p2p activity
	 */
	@Override
	public void setAsignationsCompleted(boolean asignationsCompleted) {
		model.setAsignationsCompleted(asignationsCompleted);
	}

	/**
	 * Sets the company ID of this p2p activity.
	 *
	 * @param companyId the company ID of this p2p activity
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the count corrections of this p2p activity.
	 *
	 * @param countCorrections the count corrections of this p2p activity
	 */
	@Override
	public void setCountCorrections(long countCorrections) {
		model.setCountCorrections(countCorrections);
	}

	/**
	 * Sets the create date of this p2p activity.
	 *
	 * @param createDate the create date of this p2p activity
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the date of this p2p activity.
	 *
	 * @param date the date of this p2p activity
	 */
	@Override
	public void setDate(Date date) {
		model.setDate(date);
	}

	/**
	 * Sets the description of this p2p activity.
	 *
	 * @param description the description of this p2p activity
	 */
	@Override
	public void setDescription(String description) {
		model.setDescription(description);
	}

	/**
	 * Sets the file entry ID of this p2p activity.
	 *
	 * @param fileEntryId the file entry ID of this p2p activity
	 */
	@Override
	public void setFileEntryId(long fileEntryId) {
		model.setFileEntryId(fileEntryId);
	}

	/**
	 * Sets the group ID of this p2p activity.
	 *
	 * @param groupId the group ID of this p2p activity
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the modified date of this p2p activity.
	 *
	 * @param modifiedDate the modified date of this p2p activity
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the p2p activity ID of this p2p activity.
	 *
	 * @param p2pActivityId the p2p activity ID of this p2p activity
	 */
	@Override
	public void setP2pActivityId(long p2pActivityId) {
		model.setP2pActivityId(p2pActivityId);
	}

	/**
	 * Sets the primary key of this p2p activity.
	 *
	 * @param primaryKey the primary key of this p2p activity
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the user create ID of this p2p activity.
	 *
	 * @param userCreateId the user create ID of this p2p activity
	 */
	@Override
	public void setUserCreateId(long userCreateId) {
		model.setUserCreateId(userCreateId);
	}

	/**
	 * Sets the user create name of this p2p activity.
	 *
	 * @param userCreateName the user create name of this p2p activity
	 */
	@Override
	public void setUserCreateName(String userCreateName) {
		model.setUserCreateName(userCreateName);
	}

	/**
	 * Sets the user ID of this p2p activity.
	 *
	 * @param userId the user ID of this p2p activity
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this p2p activity.
	 *
	 * @param userUuid the user uuid of this p2p activity
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this p2p activity.
	 *
	 * @param uuid the uuid of this p2p activity
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	@Override
	public StagedModelType getStagedModelType() {
		return model.getStagedModelType();
	}

	@Override
	protected P2PActivityWrapper wrap(P2PActivity p2pActivity) {
		return new P2PActivityWrapper(p2pActivity);
	}

}