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
 * The base model interface for the P2PActivityCorrections service. Represents a row in the &quot;LMS_P2PActivityCorrections&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.ted.lms.learning.activity.p2p.model.impl.P2PActivityCorrectionsModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.ted.lms.learning.activity.p2p.model.impl.P2PActivityCorrectionsImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see P2PActivityCorrections
 * @see com.ted.lms.learning.activity.p2p.model.impl.P2PActivityCorrectionsImpl
 * @see com.ted.lms.learning.activity.p2p.model.impl.P2PActivityCorrectionsModelImpl
 * @generated
 */
@ProviderType
public interface P2PActivityCorrectionsModel extends BaseModel<P2PActivityCorrections>,
	ShardedModel, StagedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a p2p activity corrections model instance should use the {@link P2PActivityCorrections} interface instead.
	 */

	/**
	 * Returns the primary key of this p2p activity corrections.
	 *
	 * @return the primary key of this p2p activity corrections
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this p2p activity corrections.
	 *
	 * @param primaryKey the primary key of this p2p activity corrections
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this p2p activity corrections.
	 *
	 * @return the uuid of this p2p activity corrections
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this p2p activity corrections.
	 *
	 * @param uuid the uuid of this p2p activity corrections
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the p2p activity corrections ID of this p2p activity corrections.
	 *
	 * @return the p2p activity corrections ID of this p2p activity corrections
	 */
	public long getP2pActivityCorrectionsId();

	/**
	 * Sets the p2p activity corrections ID of this p2p activity corrections.
	 *
	 * @param p2pActivityCorrectionsId the p2p activity corrections ID of this p2p activity corrections
	 */
	public void setP2pActivityCorrectionsId(long p2pActivityCorrectionsId);

	/**
	 * Returns the group ID of this p2p activity corrections.
	 *
	 * @return the group ID of this p2p activity corrections
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this p2p activity corrections.
	 *
	 * @param groupId the group ID of this p2p activity corrections
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this p2p activity corrections.
	 *
	 * @return the company ID of this p2p activity corrections
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this p2p activity corrections.
	 *
	 * @param companyId the company ID of this p2p activity corrections
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user create ID of this p2p activity corrections.
	 *
	 * @return the user create ID of this p2p activity corrections
	 */
	public long getUserCreateId();

	/**
	 * Sets the user create ID of this p2p activity corrections.
	 *
	 * @param userCreateId the user create ID of this p2p activity corrections
	 */
	public void setUserCreateId(long userCreateId);

	/**
	 * Returns the user create name of this p2p activity corrections.
	 *
	 * @return the user create name of this p2p activity corrections
	 */
	@AutoEscape
	public String getUserCreateName();

	/**
	 * Sets the user create name of this p2p activity corrections.
	 *
	 * @param userCreateName the user create name of this p2p activity corrections
	 */
	public void setUserCreateName(String userCreateName);

	/**
	 * Returns the create date of this p2p activity corrections.
	 *
	 * @return the create date of this p2p activity corrections
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this p2p activity corrections.
	 *
	 * @param createDate the create date of this p2p activity corrections
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this p2p activity corrections.
	 *
	 * @return the modified date of this p2p activity corrections
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this p2p activity corrections.
	 *
	 * @param modifiedDate the modified date of this p2p activity corrections
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the p2p activity ID of this p2p activity corrections.
	 *
	 * @return the p2p activity ID of this p2p activity corrections
	 */
	public long getP2pActivityId();

	/**
	 * Sets the p2p activity ID of this p2p activity corrections.
	 *
	 * @param p2pActivityId the p2p activity ID of this p2p activity corrections
	 */
	public void setP2pActivityId(long p2pActivityId);

	/**
	 * Returns the user ID of this p2p activity corrections.
	 *
	 * @return the user ID of this p2p activity corrections
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this p2p activity corrections.
	 *
	 * @param userId the user ID of this p2p activity corrections
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this p2p activity corrections.
	 *
	 * @return the user uuid of this p2p activity corrections
	 */
	public String getUserUuid();

	/**
	 * Sets the user uuid of this p2p activity corrections.
	 *
	 * @param userUuid the user uuid of this p2p activity corrections
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the act ID of this p2p activity corrections.
	 *
	 * @return the act ID of this p2p activity corrections
	 */
	public long getActId();

	/**
	 * Sets the act ID of this p2p activity corrections.
	 *
	 * @param actId the act ID of this p2p activity corrections
	 */
	public void setActId(long actId);

	/**
	 * Returns the description of this p2p activity corrections.
	 *
	 * @return the description of this p2p activity corrections
	 */
	@AutoEscape
	public String getDescription();

	/**
	 * Sets the description of this p2p activity corrections.
	 *
	 * @param description the description of this p2p activity corrections
	 */
	public void setDescription(String description);

	/**
	 * Returns the date of this p2p activity corrections.
	 *
	 * @return the date of this p2p activity corrections
	 */
	public Date getDate();

	/**
	 * Sets the date of this p2p activity corrections.
	 *
	 * @param date the date of this p2p activity corrections
	 */
	public void setDate(Date date);

	/**
	 * Returns the file entry ID of this p2p activity corrections.
	 *
	 * @return the file entry ID of this p2p activity corrections
	 */
	public long getFileEntryId();

	/**
	 * Sets the file entry ID of this p2p activity corrections.
	 *
	 * @param fileEntryId the file entry ID of this p2p activity corrections
	 */
	public void setFileEntryId(long fileEntryId);

	/**
	 * Returns the result of this p2p activity corrections.
	 *
	 * @return the result of this p2p activity corrections
	 */
	public long getResult();

	/**
	 * Sets the result of this p2p activity corrections.
	 *
	 * @param result the result of this p2p activity corrections
	 */
	public void setResult(long result);

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
	public int compareTo(P2PActivityCorrections p2pActivityCorrections);

	@Override
	public int hashCode();

	@Override
	public CacheModel<P2PActivityCorrections> toCacheModel();

	@Override
	public P2PActivityCorrections toEscapedModel();

	@Override
	public P2PActivityCorrections toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}