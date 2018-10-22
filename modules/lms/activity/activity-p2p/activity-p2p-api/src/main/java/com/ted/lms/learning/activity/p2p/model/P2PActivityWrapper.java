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
 * This class is a wrapper for {@link P2PActivity}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see P2PActivity
 * @generated
 */
@ProviderType
public class P2PActivityWrapper implements P2PActivity,
	ModelWrapper<P2PActivity> {
	public P2PActivityWrapper(P2PActivity p2pActivity) {
		_p2pActivity = p2pActivity;
	}

	@Override
	public Class<?> getModelClass() {
		return P2PActivity.class;
	}

	@Override
	public String getModelClassName() {
		return P2PActivity.class.getName();
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

	@Override
	public Object clone() {
		return new P2PActivityWrapper((P2PActivity)_p2pActivity.clone());
	}

	@Override
	public int compareTo(P2PActivity p2pActivity) {
		return _p2pActivity.compareTo(p2pActivity);
	}

	/**
	* Returns the act ID of this p2p activity.
	*
	* @return the act ID of this p2p activity
	*/
	@Override
	public long getActId() {
		return _p2pActivity.getActId();
	}

	/**
	* Returns the asignations completed of this p2p activity.
	*
	* @return the asignations completed of this p2p activity
	*/
	@Override
	public boolean getAsignationsCompleted() {
		return _p2pActivity.getAsignationsCompleted();
	}

	/**
	* Returns the company ID of this p2p activity.
	*
	* @return the company ID of this p2p activity
	*/
	@Override
	public long getCompanyId() {
		return _p2pActivity.getCompanyId();
	}

	/**
	* Returns the count corrections of this p2p activity.
	*
	* @return the count corrections of this p2p activity
	*/
	@Override
	public long getCountCorrections() {
		return _p2pActivity.getCountCorrections();
	}

	/**
	* Returns the create date of this p2p activity.
	*
	* @return the create date of this p2p activity
	*/
	@Override
	public Date getCreateDate() {
		return _p2pActivity.getCreateDate();
	}

	/**
	* Returns the date of this p2p activity.
	*
	* @return the date of this p2p activity
	*/
	@Override
	public Date getDate() {
		return _p2pActivity.getDate();
	}

	/**
	* Returns the description of this p2p activity.
	*
	* @return the description of this p2p activity
	*/
	@Override
	public String getDescription() {
		return _p2pActivity.getDescription();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _p2pActivity.getExpandoBridge();
	}

	/**
	* Returns the file entry ID of this p2p activity.
	*
	* @return the file entry ID of this p2p activity
	*/
	@Override
	public long getFileEntryId() {
		return _p2pActivity.getFileEntryId();
	}

	/**
	* Returns the group ID of this p2p activity.
	*
	* @return the group ID of this p2p activity
	*/
	@Override
	public long getGroupId() {
		return _p2pActivity.getGroupId();
	}

	/**
	* Returns the modified date of this p2p activity.
	*
	* @return the modified date of this p2p activity
	*/
	@Override
	public Date getModifiedDate() {
		return _p2pActivity.getModifiedDate();
	}

	/**
	* Returns the p2p activity ID of this p2p activity.
	*
	* @return the p2p activity ID of this p2p activity
	*/
	@Override
	public long getP2pActivityId() {
		return _p2pActivity.getP2pActivityId();
	}

	/**
	* Returns the primary key of this p2p activity.
	*
	* @return the primary key of this p2p activity
	*/
	@Override
	public long getPrimaryKey() {
		return _p2pActivity.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _p2pActivity.getPrimaryKeyObj();
	}

	/**
	* Returns the user create ID of this p2p activity.
	*
	* @return the user create ID of this p2p activity
	*/
	@Override
	public long getUserCreateId() {
		return _p2pActivity.getUserCreateId();
	}

	/**
	* Returns the user create name of this p2p activity.
	*
	* @return the user create name of this p2p activity
	*/
	@Override
	public String getUserCreateName() {
		return _p2pActivity.getUserCreateName();
	}

	/**
	* Returns the user ID of this p2p activity.
	*
	* @return the user ID of this p2p activity
	*/
	@Override
	public long getUserId() {
		return _p2pActivity.getUserId();
	}

	/**
	* Returns the user uuid of this p2p activity.
	*
	* @return the user uuid of this p2p activity
	*/
	@Override
	public String getUserUuid() {
		return _p2pActivity.getUserUuid();
	}

	/**
	* Returns the uuid of this p2p activity.
	*
	* @return the uuid of this p2p activity
	*/
	@Override
	public String getUuid() {
		return _p2pActivity.getUuid();
	}

	@Override
	public int hashCode() {
		return _p2pActivity.hashCode();
	}

	/**
	* Returns <code>true</code> if this p2p activity is asignations completed.
	*
	* @return <code>true</code> if this p2p activity is asignations completed; <code>false</code> otherwise
	*/
	@Override
	public boolean isAsignationsCompleted() {
		return _p2pActivity.isAsignationsCompleted();
	}

	@Override
	public boolean isCachedModel() {
		return _p2pActivity.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _p2pActivity.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _p2pActivity.isNew();
	}

	@Override
	public void persist() {
		_p2pActivity.persist();
	}

	/**
	* Sets the act ID of this p2p activity.
	*
	* @param actId the act ID of this p2p activity
	*/
	@Override
	public void setActId(long actId) {
		_p2pActivity.setActId(actId);
	}

	/**
	* Sets whether this p2p activity is asignations completed.
	*
	* @param asignationsCompleted the asignations completed of this p2p activity
	*/
	@Override
	public void setAsignationsCompleted(boolean asignationsCompleted) {
		_p2pActivity.setAsignationsCompleted(asignationsCompleted);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_p2pActivity.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this p2p activity.
	*
	* @param companyId the company ID of this p2p activity
	*/
	@Override
	public void setCompanyId(long companyId) {
		_p2pActivity.setCompanyId(companyId);
	}

	/**
	* Sets the count corrections of this p2p activity.
	*
	* @param countCorrections the count corrections of this p2p activity
	*/
	@Override
	public void setCountCorrections(long countCorrections) {
		_p2pActivity.setCountCorrections(countCorrections);
	}

	/**
	* Sets the create date of this p2p activity.
	*
	* @param createDate the create date of this p2p activity
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_p2pActivity.setCreateDate(createDate);
	}

	/**
	* Sets the date of this p2p activity.
	*
	* @param date the date of this p2p activity
	*/
	@Override
	public void setDate(Date date) {
		_p2pActivity.setDate(date);
	}

	/**
	* Sets the description of this p2p activity.
	*
	* @param description the description of this p2p activity
	*/
	@Override
	public void setDescription(String description) {
		_p2pActivity.setDescription(description);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_p2pActivity.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_p2pActivity.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_p2pActivity.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the file entry ID of this p2p activity.
	*
	* @param fileEntryId the file entry ID of this p2p activity
	*/
	@Override
	public void setFileEntryId(long fileEntryId) {
		_p2pActivity.setFileEntryId(fileEntryId);
	}

	/**
	* Sets the group ID of this p2p activity.
	*
	* @param groupId the group ID of this p2p activity
	*/
	@Override
	public void setGroupId(long groupId) {
		_p2pActivity.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this p2p activity.
	*
	* @param modifiedDate the modified date of this p2p activity
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_p2pActivity.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_p2pActivity.setNew(n);
	}

	/**
	* Sets the p2p activity ID of this p2p activity.
	*
	* @param p2pActivityId the p2p activity ID of this p2p activity
	*/
	@Override
	public void setP2pActivityId(long p2pActivityId) {
		_p2pActivity.setP2pActivityId(p2pActivityId);
	}

	/**
	* Sets the primary key of this p2p activity.
	*
	* @param primaryKey the primary key of this p2p activity
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_p2pActivity.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_p2pActivity.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the user create ID of this p2p activity.
	*
	* @param userCreateId the user create ID of this p2p activity
	*/
	@Override
	public void setUserCreateId(long userCreateId) {
		_p2pActivity.setUserCreateId(userCreateId);
	}

	/**
	* Sets the user create name of this p2p activity.
	*
	* @param userCreateName the user create name of this p2p activity
	*/
	@Override
	public void setUserCreateName(String userCreateName) {
		_p2pActivity.setUserCreateName(userCreateName);
	}

	/**
	* Sets the user ID of this p2p activity.
	*
	* @param userId the user ID of this p2p activity
	*/
	@Override
	public void setUserId(long userId) {
		_p2pActivity.setUserId(userId);
	}

	/**
	* Sets the user uuid of this p2p activity.
	*
	* @param userUuid the user uuid of this p2p activity
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_p2pActivity.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this p2p activity.
	*
	* @param uuid the uuid of this p2p activity
	*/
	@Override
	public void setUuid(String uuid) {
		_p2pActivity.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<P2PActivity> toCacheModel() {
		return _p2pActivity.toCacheModel();
	}

	@Override
	public P2PActivity toEscapedModel() {
		return new P2PActivityWrapper(_p2pActivity.toEscapedModel());
	}

	@Override
	public String toString() {
		return _p2pActivity.toString();
	}

	@Override
	public P2PActivity toUnescapedModel() {
		return new P2PActivityWrapper(_p2pActivity.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _p2pActivity.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof P2PActivityWrapper)) {
			return false;
		}

		P2PActivityWrapper p2pActivityWrapper = (P2PActivityWrapper)obj;

		if (Objects.equals(_p2pActivity, p2pActivityWrapper._p2pActivity)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _p2pActivity.getStagedModelType();
	}

	@Override
	public P2PActivity getWrappedModel() {
		return _p2pActivity;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _p2pActivity.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _p2pActivity.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_p2pActivity.resetOriginalValues();
	}

	private final P2PActivity _p2pActivity;
}