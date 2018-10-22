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

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
public class P2PActivityCorrectionsWrapper implements P2PActivityCorrections,
	ModelWrapper<P2PActivityCorrections> {
	public P2PActivityCorrectionsWrapper(
		P2PActivityCorrections p2pActivityCorrections) {
		_p2pActivityCorrections = p2pActivityCorrections;
	}

	@Override
	public Class<?> getModelClass() {
		return P2PActivityCorrections.class;
	}

	@Override
	public String getModelClassName() {
		return P2PActivityCorrections.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("p2pActivityCorrectionsId", getP2pActivityCorrectionsId());
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

	@Override
	public Object clone() {
		return new P2PActivityCorrectionsWrapper((P2PActivityCorrections)_p2pActivityCorrections.clone());
	}

	@Override
	public int compareTo(P2PActivityCorrections p2pActivityCorrections) {
		return _p2pActivityCorrections.compareTo(p2pActivityCorrections);
	}

	/**
	* Returns the act ID of this p2p activity corrections.
	*
	* @return the act ID of this p2p activity corrections
	*/
	@Override
	public long getActId() {
		return _p2pActivityCorrections.getActId();
	}

	/**
	* Returns the company ID of this p2p activity corrections.
	*
	* @return the company ID of this p2p activity corrections
	*/
	@Override
	public long getCompanyId() {
		return _p2pActivityCorrections.getCompanyId();
	}

	/**
	* Returns the create date of this p2p activity corrections.
	*
	* @return the create date of this p2p activity corrections
	*/
	@Override
	public Date getCreateDate() {
		return _p2pActivityCorrections.getCreateDate();
	}

	/**
	* Returns the date of this p2p activity corrections.
	*
	* @return the date of this p2p activity corrections
	*/
	@Override
	public Date getDate() {
		return _p2pActivityCorrections.getDate();
	}

	/**
	* Returns the description of this p2p activity corrections.
	*
	* @return the description of this p2p activity corrections
	*/
	@Override
	public String getDescription() {
		return _p2pActivityCorrections.getDescription();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _p2pActivityCorrections.getExpandoBridge();
	}

	/**
	* Returns the file entry ID of this p2p activity corrections.
	*
	* @return the file entry ID of this p2p activity corrections
	*/
	@Override
	public long getFileEntryId() {
		return _p2pActivityCorrections.getFileEntryId();
	}

	/**
	* Returns the group ID of this p2p activity corrections.
	*
	* @return the group ID of this p2p activity corrections
	*/
	@Override
	public long getGroupId() {
		return _p2pActivityCorrections.getGroupId();
	}

	/**
	* Returns the modified date of this p2p activity corrections.
	*
	* @return the modified date of this p2p activity corrections
	*/
	@Override
	public Date getModifiedDate() {
		return _p2pActivityCorrections.getModifiedDate();
	}

	/**
	* Returns the p2p activity corrections ID of this p2p activity corrections.
	*
	* @return the p2p activity corrections ID of this p2p activity corrections
	*/
	@Override
	public long getP2pActivityCorrectionsId() {
		return _p2pActivityCorrections.getP2pActivityCorrectionsId();
	}

	/**
	* Returns the p2p activity ID of this p2p activity corrections.
	*
	* @return the p2p activity ID of this p2p activity corrections
	*/
	@Override
	public long getP2pActivityId() {
		return _p2pActivityCorrections.getP2pActivityId();
	}

	/**
	* Returns the primary key of this p2p activity corrections.
	*
	* @return the primary key of this p2p activity corrections
	*/
	@Override
	public long getPrimaryKey() {
		return _p2pActivityCorrections.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _p2pActivityCorrections.getPrimaryKeyObj();
	}

	/**
	* Returns the result of this p2p activity corrections.
	*
	* @return the result of this p2p activity corrections
	*/
	@Override
	public long getResult() {
		return _p2pActivityCorrections.getResult();
	}

	/**
	* Returns the user create ID of this p2p activity corrections.
	*
	* @return the user create ID of this p2p activity corrections
	*/
	@Override
	public long getUserCreateId() {
		return _p2pActivityCorrections.getUserCreateId();
	}

	/**
	* Returns the user create name of this p2p activity corrections.
	*
	* @return the user create name of this p2p activity corrections
	*/
	@Override
	public String getUserCreateName() {
		return _p2pActivityCorrections.getUserCreateName();
	}

	/**
	* Returns the user ID of this p2p activity corrections.
	*
	* @return the user ID of this p2p activity corrections
	*/
	@Override
	public long getUserId() {
		return _p2pActivityCorrections.getUserId();
	}

	/**
	* Returns the user uuid of this p2p activity corrections.
	*
	* @return the user uuid of this p2p activity corrections
	*/
	@Override
	public String getUserUuid() {
		return _p2pActivityCorrections.getUserUuid();
	}

	/**
	* Returns the uuid of this p2p activity corrections.
	*
	* @return the uuid of this p2p activity corrections
	*/
	@Override
	public String getUuid() {
		return _p2pActivityCorrections.getUuid();
	}

	@Override
	public int hashCode() {
		return _p2pActivityCorrections.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _p2pActivityCorrections.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _p2pActivityCorrections.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _p2pActivityCorrections.isNew();
	}

	@Override
	public void persist() {
		_p2pActivityCorrections.persist();
	}

	/**
	* Sets the act ID of this p2p activity corrections.
	*
	* @param actId the act ID of this p2p activity corrections
	*/
	@Override
	public void setActId(long actId) {
		_p2pActivityCorrections.setActId(actId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_p2pActivityCorrections.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this p2p activity corrections.
	*
	* @param companyId the company ID of this p2p activity corrections
	*/
	@Override
	public void setCompanyId(long companyId) {
		_p2pActivityCorrections.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this p2p activity corrections.
	*
	* @param createDate the create date of this p2p activity corrections
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_p2pActivityCorrections.setCreateDate(createDate);
	}

	/**
	* Sets the date of this p2p activity corrections.
	*
	* @param date the date of this p2p activity corrections
	*/
	@Override
	public void setDate(Date date) {
		_p2pActivityCorrections.setDate(date);
	}

	/**
	* Sets the description of this p2p activity corrections.
	*
	* @param description the description of this p2p activity corrections
	*/
	@Override
	public void setDescription(String description) {
		_p2pActivityCorrections.setDescription(description);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_p2pActivityCorrections.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_p2pActivityCorrections.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_p2pActivityCorrections.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the file entry ID of this p2p activity corrections.
	*
	* @param fileEntryId the file entry ID of this p2p activity corrections
	*/
	@Override
	public void setFileEntryId(long fileEntryId) {
		_p2pActivityCorrections.setFileEntryId(fileEntryId);
	}

	/**
	* Sets the group ID of this p2p activity corrections.
	*
	* @param groupId the group ID of this p2p activity corrections
	*/
	@Override
	public void setGroupId(long groupId) {
		_p2pActivityCorrections.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this p2p activity corrections.
	*
	* @param modifiedDate the modified date of this p2p activity corrections
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_p2pActivityCorrections.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_p2pActivityCorrections.setNew(n);
	}

	/**
	* Sets the p2p activity corrections ID of this p2p activity corrections.
	*
	* @param p2pActivityCorrectionsId the p2p activity corrections ID of this p2p activity corrections
	*/
	@Override
	public void setP2pActivityCorrectionsId(long p2pActivityCorrectionsId) {
		_p2pActivityCorrections.setP2pActivityCorrectionsId(p2pActivityCorrectionsId);
	}

	/**
	* Sets the p2p activity ID of this p2p activity corrections.
	*
	* @param p2pActivityId the p2p activity ID of this p2p activity corrections
	*/
	@Override
	public void setP2pActivityId(long p2pActivityId) {
		_p2pActivityCorrections.setP2pActivityId(p2pActivityId);
	}

	/**
	* Sets the primary key of this p2p activity corrections.
	*
	* @param primaryKey the primary key of this p2p activity corrections
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_p2pActivityCorrections.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_p2pActivityCorrections.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the result of this p2p activity corrections.
	*
	* @param result the result of this p2p activity corrections
	*/
	@Override
	public void setResult(long result) {
		_p2pActivityCorrections.setResult(result);
	}

	/**
	* Sets the user create ID of this p2p activity corrections.
	*
	* @param userCreateId the user create ID of this p2p activity corrections
	*/
	@Override
	public void setUserCreateId(long userCreateId) {
		_p2pActivityCorrections.setUserCreateId(userCreateId);
	}

	/**
	* Sets the user create name of this p2p activity corrections.
	*
	* @param userCreateName the user create name of this p2p activity corrections
	*/
	@Override
	public void setUserCreateName(String userCreateName) {
		_p2pActivityCorrections.setUserCreateName(userCreateName);
	}

	/**
	* Sets the user ID of this p2p activity corrections.
	*
	* @param userId the user ID of this p2p activity corrections
	*/
	@Override
	public void setUserId(long userId) {
		_p2pActivityCorrections.setUserId(userId);
	}

	/**
	* Sets the user uuid of this p2p activity corrections.
	*
	* @param userUuid the user uuid of this p2p activity corrections
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_p2pActivityCorrections.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this p2p activity corrections.
	*
	* @param uuid the uuid of this p2p activity corrections
	*/
	@Override
	public void setUuid(String uuid) {
		_p2pActivityCorrections.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<P2PActivityCorrections> toCacheModel() {
		return _p2pActivityCorrections.toCacheModel();
	}

	@Override
	public P2PActivityCorrections toEscapedModel() {
		return new P2PActivityCorrectionsWrapper(_p2pActivityCorrections.toEscapedModel());
	}

	@Override
	public String toString() {
		return _p2pActivityCorrections.toString();
	}

	@Override
	public P2PActivityCorrections toUnescapedModel() {
		return new P2PActivityCorrectionsWrapper(_p2pActivityCorrections.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _p2pActivityCorrections.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof P2PActivityCorrectionsWrapper)) {
			return false;
		}

		P2PActivityCorrectionsWrapper p2pActivityCorrectionsWrapper = (P2PActivityCorrectionsWrapper)obj;

		if (Objects.equals(_p2pActivityCorrections,
					p2pActivityCorrectionsWrapper._p2pActivityCorrections)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _p2pActivityCorrections.getStagedModelType();
	}

	@Override
	public P2PActivityCorrections getWrappedModel() {
		return _p2pActivityCorrections;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _p2pActivityCorrections.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _p2pActivityCorrections.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_p2pActivityCorrections.resetOriginalValues();
	}

	private final P2PActivityCorrections _p2pActivityCorrections;
}