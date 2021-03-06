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

package com.ted.lms.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the LearningActivityResult service. Represents a row in the &quot;LMS_LearningActivityResult&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.ted.lms.model.impl.LearningActivityResultModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.ted.lms.model.impl.LearningActivityResultImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LearningActivityResult
 * @generated
 */
@ProviderType
public interface LearningActivityResultModel
	extends BaseModel<LearningActivityResult>, ShardedModel, StagedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a learning activity result model instance should use the {@link LearningActivityResult} interface instead.
	 */

	/**
	 * Returns the primary key of this learning activity result.
	 *
	 * @return the primary key of this learning activity result
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this learning activity result.
	 *
	 * @param primaryKey the primary key of this learning activity result
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this learning activity result.
	 *
	 * @return the uuid of this learning activity result
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this learning activity result.
	 *
	 * @param uuid the uuid of this learning activity result
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the lar ID of this learning activity result.
	 *
	 * @return the lar ID of this learning activity result
	 */
	public long getLarId();

	/**
	 * Sets the lar ID of this learning activity result.
	 *
	 * @param larId the lar ID of this learning activity result
	 */
	public void setLarId(long larId);

	/**
	 * Returns the group ID of this learning activity result.
	 *
	 * @return the group ID of this learning activity result
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this learning activity result.
	 *
	 * @param groupId the group ID of this learning activity result
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this learning activity result.
	 *
	 * @return the company ID of this learning activity result
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this learning activity result.
	 *
	 * @param companyId the company ID of this learning activity result
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user modified ID of this learning activity result.
	 *
	 * @return the user modified ID of this learning activity result
	 */
	public long getUserModifiedId();

	/**
	 * Sets the user modified ID of this learning activity result.
	 *
	 * @param userModifiedId the user modified ID of this learning activity result
	 */
	public void setUserModifiedId(long userModifiedId);

	/**
	 * Returns the user modified name of this learning activity result.
	 *
	 * @return the user modified name of this learning activity result
	 */
	@AutoEscape
	public String getUserModifiedName();

	/**
	 * Sets the user modified name of this learning activity result.
	 *
	 * @param userModifiedName the user modified name of this learning activity result
	 */
	public void setUserModifiedName(String userModifiedName);

	/**
	 * Returns the create date of this learning activity result.
	 *
	 * @return the create date of this learning activity result
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this learning activity result.
	 *
	 * @param createDate the create date of this learning activity result
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this learning activity result.
	 *
	 * @return the modified date of this learning activity result
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this learning activity result.
	 *
	 * @param modifiedDate the modified date of this learning activity result
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the act ID of this learning activity result.
	 *
	 * @return the act ID of this learning activity result
	 */
	public long getActId();

	/**
	 * Sets the act ID of this learning activity result.
	 *
	 * @param actId the act ID of this learning activity result
	 */
	public void setActId(long actId);

	/**
	 * Returns the user ID of this learning activity result.
	 *
	 * @return the user ID of this learning activity result
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this learning activity result.
	 *
	 * @param userId the user ID of this learning activity result
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this learning activity result.
	 *
	 * @return the user uuid of this learning activity result
	 */
	public String getUserUuid();

	/**
	 * Sets the user uuid of this learning activity result.
	 *
	 * @param userUuid the user uuid of this learning activity result
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the result of this learning activity result.
	 *
	 * @return the result of this learning activity result
	 */
	public double getResult();

	/**
	 * Sets the result of this learning activity result.
	 *
	 * @param result the result of this learning activity result
	 */
	public void setResult(double result);

	/**
	 * Returns the comments of this learning activity result.
	 *
	 * @return the comments of this learning activity result
	 */
	@AutoEscape
	public String getComments();

	/**
	 * Sets the comments of this learning activity result.
	 *
	 * @param comments the comments of this learning activity result
	 */
	public void setComments(String comments);

	/**
	 * Returns the passed of this learning activity result.
	 *
	 * @return the passed of this learning activity result
	 */
	public boolean getPassed();

	/**
	 * Returns <code>true</code> if this learning activity result is passed.
	 *
	 * @return <code>true</code> if this learning activity result is passed; <code>false</code> otherwise
	 */
	public boolean isPassed();

	/**
	 * Sets whether this learning activity result is passed.
	 *
	 * @param passed the passed of this learning activity result
	 */
	public void setPassed(boolean passed);

	/**
	 * Returns the start date of this learning activity result.
	 *
	 * @return the start date of this learning activity result
	 */
	public Date getStartDate();

	/**
	 * Sets the start date of this learning activity result.
	 *
	 * @param startDate the start date of this learning activity result
	 */
	public void setStartDate(Date startDate);

	/**
	 * Returns the end date of this learning activity result.
	 *
	 * @return the end date of this learning activity result
	 */
	public Date getEndDate();

	/**
	 * Sets the end date of this learning activity result.
	 *
	 * @param endDate the end date of this learning activity result
	 */
	public void setEndDate(Date endDate);

	/**
	 * Returns the extra data of this learning activity result.
	 *
	 * @return the extra data of this learning activity result
	 */
	@AutoEscape
	public String getExtraData();

	/**
	 * Sets the extra data of this learning activity result.
	 *
	 * @param extraData the extra data of this learning activity result
	 */
	public void setExtraData(String extraData);

}