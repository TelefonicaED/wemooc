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

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the P2PActivity service. Represents a row in the &quot;LMS_P2PActivity&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.ted.lms.learning.activity.p2p.model.impl.P2PActivityModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.ted.lms.learning.activity.p2p.model.impl.P2PActivityImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see P2PActivity
 * @see com.ted.lms.learning.activity.p2p.model.impl.P2PActivityImpl
 * @see com.ted.lms.learning.activity.p2p.model.impl.P2PActivityModelImpl
 * @generated
 */
@ProviderType
public interface P2PActivityModel extends BaseModel<P2PActivity>, ShardedModel,
	StagedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a p2p activity model instance should use the {@link P2PActivity} interface instead.
	 */

	/**
	 * Returns the primary key of this p2p activity.
	 *
	 * @return the primary key of this p2p activity
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this p2p activity.
	 *
	 * @param primaryKey the primary key of this p2p activity
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this p2p activity.
	 *
	 * @return the uuid of this p2p activity
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this p2p activity.
	 *
	 * @param uuid the uuid of this p2p activity
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the p2p activity ID of this p2p activity.
	 *
	 * @return the p2p activity ID of this p2p activity
	 */
	public long getP2pActivityId();

	/**
	 * Sets the p2p activity ID of this p2p activity.
	 *
	 * @param p2pActivityId the p2p activity ID of this p2p activity
	 */
	public void setP2pActivityId(long p2pActivityId);

	/**
	 * Returns the group ID of this p2p activity.
	 *
	 * @return the group ID of this p2p activity
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this p2p activity.
	 *
	 * @param groupId the group ID of this p2p activity
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this p2p activity.
	 *
	 * @return the company ID of this p2p activity
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this p2p activity.
	 *
	 * @param companyId the company ID of this p2p activity
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user create ID of this p2p activity.
	 *
	 * @return the user create ID of this p2p activity
	 */
	public long getUserCreateId();

	/**
	 * Sets the user create ID of this p2p activity.
	 *
	 * @param userCreateId the user create ID of this p2p activity
	 */
	public void setUserCreateId(long userCreateId);

	/**
	 * Returns the user create name of this p2p activity.
	 *
	 * @return the user create name of this p2p activity
	 */
	@AutoEscape
	public String getUserCreateName();

	/**
	 * Sets the user create name of this p2p activity.
	 *
	 * @param userCreateName the user create name of this p2p activity
	 */
	public void setUserCreateName(String userCreateName);

	/**
	 * Returns the create date of this p2p activity.
	 *
	 * @return the create date of this p2p activity
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this p2p activity.
	 *
	 * @param createDate the create date of this p2p activity
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this p2p activity.
	 *
	 * @return the modified date of this p2p activity
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this p2p activity.
	 *
	 * @param modifiedDate the modified date of this p2p activity
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the act ID of this p2p activity.
	 *
	 * @return the act ID of this p2p activity
	 */
	public long getActId();

	/**
	 * Sets the act ID of this p2p activity.
	 *
	 * @param actId the act ID of this p2p activity
	 */
	public void setActId(long actId);

	/**
	 * Returns the user ID of this p2p activity.
	 *
	 * @return the user ID of this p2p activity
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this p2p activity.
	 *
	 * @param userId the user ID of this p2p activity
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this p2p activity.
	 *
	 * @return the user uuid of this p2p activity
	 */
	public String getUserUuid();

	/**
	 * Sets the user uuid of this p2p activity.
	 *
	 * @param userUuid the user uuid of this p2p activity
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the file entry ID of this p2p activity.
	 *
	 * @return the file entry ID of this p2p activity
	 */
	public long getFileEntryId();

	/**
	 * Sets the file entry ID of this p2p activity.
	 *
	 * @param fileEntryId the file entry ID of this p2p activity
	 */
	public void setFileEntryId(long fileEntryId);

	/**
	 * Returns the count corrections of this p2p activity.
	 *
	 * @return the count corrections of this p2p activity
	 */
	public long getCountCorrections();

	/**
	 * Sets the count corrections of this p2p activity.
	 *
	 * @param countCorrections the count corrections of this p2p activity
	 */
	public void setCountCorrections(long countCorrections);

	/**
	 * Returns the description of this p2p activity.
	 *
	 * @return the description of this p2p activity
	 */
	@AutoEscape
	public String getDescription();

	/**
	 * Sets the description of this p2p activity.
	 *
	 * @param description the description of this p2p activity
	 */
	public void setDescription(String description);

	/**
	 * Returns the date of this p2p activity.
	 *
	 * @return the date of this p2p activity
	 */
	public Date getDate();

	/**
	 * Sets the date of this p2p activity.
	 *
	 * @param date the date of this p2p activity
	 */
	public void setDate(Date date);

	/**
	 * Returns the asignations completed of this p2p activity.
	 *
	 * @return the asignations completed of this p2p activity
	 */
	public boolean getAsignationsCompleted();

	/**
	 * Returns <code>true</code> if this p2p activity is asignations completed.
	 *
	 * @return <code>true</code> if this p2p activity is asignations completed; <code>false</code> otherwise
	 */
	public boolean isAsignationsCompleted();

	/**
	 * Sets whether this p2p activity is asignations completed.
	 *
	 * @param asignationsCompleted the asignations completed of this p2p activity
	 */
	public void setAsignationsCompleted(boolean asignationsCompleted);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(P2PActivity p2pActivity);

	@Override
	public int hashCode();

	@Override
	public CacheModel<P2PActivity> toCacheModel();

	@Override
	public P2PActivity toEscapedModel();

	@Override
	public P2PActivity toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}