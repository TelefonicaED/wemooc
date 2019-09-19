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
 * This class is a wrapper for {@link P2PActivityCorrections}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see P2PActivityCorrections
 * @generated
 */
@ProviderType
public class P2PActivityCorrectionsWrapper
	extends BaseModelWrapper<P2PActivityCorrections>
	implements P2PActivityCorrections, ModelWrapper<P2PActivityCorrections> {

	public P2PActivityCorrectionsWrapper(
		P2PActivityCorrections p2pActivityCorrections) {

		super(p2pActivityCorrections);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put(
			"p2pActivityCorrectionsId", getP2pActivityCorrectionsId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userCreateId", getUserCreateId());
		attributes.put("userCreateName", getUserCreateName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("p2pActivityId", getP2pActivityId());
		attributes.put("userId", getUserId());
		attributes.put("actId", getActId());
		attributes.put("description", getDescription());
		attributes.put("date", getDate());
		attributes.put("fileEntryId", getFileEntryId());
		attributes.put("result", getResult());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long p2pActivityCorrectionsId = (Long)attributes.get(
			"p2pActivityCorrectionsId");

		if (p2pActivityCorrectionsId != null) {
			setP2pActivityCorrectionsId(p2pActivityCorrectionsId);
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

		Long p2pActivityId = (Long)attributes.get("p2pActivityId");

		if (p2pActivityId != null) {
			setP2pActivityId(p2pActivityId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Long actId = (Long)attributes.get("actId");

		if (actId != null) {
			setActId(actId);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Date date = (Date)attributes.get("date");

		if (date != null) {
			setDate(date);
		}

		Long fileEntryId = (Long)attributes.get("fileEntryId");

		if (fileEntryId != null) {
			setFileEntryId(fileEntryId);
		}

		Long result = (Long)attributes.get("result");

		if (result != null) {
			setResult(result);
		}
	}

	/**
	 * Returns the act ID of this p2p activity corrections.
	 *
	 * @return the act ID of this p2p activity corrections
	 */
	@Override
	public long getActId() {
		return model.getActId();
	}

	/**
	 * Returns the company ID of this p2p activity corrections.
	 *
	 * @return the company ID of this p2p activity corrections
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this p2p activity corrections.
	 *
	 * @return the create date of this p2p activity corrections
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the date of this p2p activity corrections.
	 *
	 * @return the date of this p2p activity corrections
	 */
	@Override
	public Date getDate() {
		return model.getDate();
	}

	/**
	 * Returns the description of this p2p activity corrections.
	 *
	 * @return the description of this p2p activity corrections
	 */
	@Override
	public String getDescription() {
		return model.getDescription();
	}

	/**
	 * Returns the file entry ID of this p2p activity corrections.
	 *
	 * @return the file entry ID of this p2p activity corrections
	 */
	@Override
	public long getFileEntryId() {
		return model.getFileEntryId();
	}

	/**
	 * Returns the group ID of this p2p activity corrections.
	 *
	 * @return the group ID of this p2p activity corrections
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the modified date of this p2p activity corrections.
	 *
	 * @return the modified date of this p2p activity corrections
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the p2p activity corrections ID of this p2p activity corrections.
	 *
	 * @return the p2p activity corrections ID of this p2p activity corrections
	 */
	@Override
	public long getP2pActivityCorrectionsId() {
		return model.getP2pActivityCorrectionsId();
	}

	/**
	 * Returns the p2p activity ID of this p2p activity corrections.
	 *
	 * @return the p2p activity ID of this p2p activity corrections
	 */
	@Override
	public long getP2pActivityId() {
		return model.getP2pActivityId();
	}

	/**
	 * Returns the primary key of this p2p activity corrections.
	 *
	 * @return the primary key of this p2p activity corrections
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the result of this p2p activity corrections.
	 *
	 * @return the result of this p2p activity corrections
	 */
	@Override
	public long getResult() {
		return model.getResult();
	}

	/**
	 * Returns the user create ID of this p2p activity corrections.
	 *
	 * @return the user create ID of this p2p activity corrections
	 */
	@Override
	public long getUserCreateId() {
		return model.getUserCreateId();
	}

	/**
	 * Returns the user create name of this p2p activity corrections.
	 *
	 * @return the user create name of this p2p activity corrections
	 */
	@Override
	public String getUserCreateName() {
		return model.getUserCreateName();
	}

	/**
	 * Returns the user ID of this p2p activity corrections.
	 *
	 * @return the user ID of this p2p activity corrections
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this p2p activity corrections.
	 *
	 * @return the user uuid of this p2p activity corrections
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the uuid of this p2p activity corrections.
	 *
	 * @return the uuid of this p2p activity corrections
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the act ID of this p2p activity corrections.
	 *
	 * @param actId the act ID of this p2p activity corrections
	 */
	@Override
	public void setActId(long actId) {
		model.setActId(actId);
	}

	/**
	 * Sets the company ID of this p2p activity corrections.
	 *
	 * @param companyId the company ID of this p2p activity corrections
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this p2p activity corrections.
	 *
	 * @param createDate the create date of this p2p activity corrections
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the date of this p2p activity corrections.
	 *
	 * @param date the date of this p2p activity corrections
	 */
	@Override
	public void setDate(Date date) {
		model.setDate(date);
	}

	/**
	 * Sets the description of this p2p activity corrections.
	 *
	 * @param description the description of this p2p activity corrections
	 */
	@Override
	public void setDescription(String description) {
		model.setDescription(description);
	}

	/**
	 * Sets the file entry ID of this p2p activity corrections.
	 *
	 * @param fileEntryId the file entry ID of this p2p activity corrections
	 */
	@Override
	public void setFileEntryId(long fileEntryId) {
		model.setFileEntryId(fileEntryId);
	}

	/**
	 * Sets the group ID of this p2p activity corrections.
	 *
	 * @param groupId the group ID of this p2p activity corrections
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the modified date of this p2p activity corrections.
	 *
	 * @param modifiedDate the modified date of this p2p activity corrections
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the p2p activity corrections ID of this p2p activity corrections.
	 *
	 * @param p2pActivityCorrectionsId the p2p activity corrections ID of this p2p activity corrections
	 */
	@Override
	public void setP2pActivityCorrectionsId(long p2pActivityCorrectionsId) {
		model.setP2pActivityCorrectionsId(p2pActivityCorrectionsId);
	}

	/**
	 * Sets the p2p activity ID of this p2p activity corrections.
	 *
	 * @param p2pActivityId the p2p activity ID of this p2p activity corrections
	 */
	@Override
	public void setP2pActivityId(long p2pActivityId) {
		model.setP2pActivityId(p2pActivityId);
	}

	/**
	 * Sets the primary key of this p2p activity corrections.
	 *
	 * @param primaryKey the primary key of this p2p activity corrections
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the result of this p2p activity corrections.
	 *
	 * @param result the result of this p2p activity corrections
	 */
	@Override
	public void setResult(long result) {
		model.setResult(result);
	}

	/**
	 * Sets the user create ID of this p2p activity corrections.
	 *
	 * @param userCreateId the user create ID of this p2p activity corrections
	 */
	@Override
	public void setUserCreateId(long userCreateId) {
		model.setUserCreateId(userCreateId);
	}

	/**
	 * Sets the user create name of this p2p activity corrections.
	 *
	 * @param userCreateName the user create name of this p2p activity corrections
	 */
	@Override
	public void setUserCreateName(String userCreateName) {
		model.setUserCreateName(userCreateName);
	}

	/**
	 * Sets the user ID of this p2p activity corrections.
	 *
	 * @param userId the user ID of this p2p activity corrections
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this p2p activity corrections.
	 *
	 * @param userUuid the user uuid of this p2p activity corrections
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this p2p activity corrections.
	 *
	 * @param uuid the uuid of this p2p activity corrections
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
	protected P2PActivityCorrectionsWrapper wrap(
		P2PActivityCorrections p2pActivityCorrections) {

		return new P2PActivityCorrectionsWrapper(p2pActivityCorrections);
	}

}