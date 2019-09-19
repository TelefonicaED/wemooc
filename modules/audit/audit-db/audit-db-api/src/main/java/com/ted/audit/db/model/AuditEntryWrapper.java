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

package com.ted.audit.db.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * <p>
 * This class is a wrapper for {@link AuditEntry}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AuditEntry
 * @generated
 */
@ProviderType
public class AuditEntryWrapper
	extends BaseModelWrapper<AuditEntry>
	implements AuditEntry, ModelWrapper<AuditEntry> {

	public AuditEntryWrapper(AuditEntry auditEntry) {
		super(auditEntry);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("auditId", getAuditId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("associationClassPK", getAssociationClassPK());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("startDate", getStartDate());
		attributes.put("endDate", getEndDate());
		attributes.put("actionId", getActionId());
		attributes.put("extradata", getExtradata());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long auditId = (Long)attributes.get("auditId");

		if (auditId != null) {
			setAuditId(auditId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		Long associationClassPK = (Long)attributes.get("associationClassPK");

		if (associationClassPK != null) {
			setAssociationClassPK(associationClassPK);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Date endDate = (Date)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}

		Integer actionId = (Integer)attributes.get("actionId");

		if (actionId != null) {
			setActionId(actionId);
		}

		String extradata = (String)attributes.get("extradata");

		if (extradata != null) {
			setExtradata(extradata);
		}
	}

	/**
	 * Returns the action ID of this audit entry.
	 *
	 * @return the action ID of this audit entry
	 */
	@Override
	public int getActionId() {
		return model.getActionId();
	}

	/**
	 * Returns the association class pk of this audit entry.
	 *
	 * @return the association class pk of this audit entry
	 */
	@Override
	public long getAssociationClassPK() {
		return model.getAssociationClassPK();
	}

	/**
	 * Returns the audit ID of this audit entry.
	 *
	 * @return the audit ID of this audit entry
	 */
	@Override
	public long getAuditId() {
		return model.getAuditId();
	}

	/**
	 * Returns the fully qualified class name of this audit entry.
	 *
	 * @return the fully qualified class name of this audit entry
	 */
	@Override
	public String getClassName() {
		return model.getClassName();
	}

	/**
	 * Returns the class name ID of this audit entry.
	 *
	 * @return the class name ID of this audit entry
	 */
	@Override
	public long getClassNameId() {
		return model.getClassNameId();
	}

	/**
	 * Returns the class pk of this audit entry.
	 *
	 * @return the class pk of this audit entry
	 */
	@Override
	public long getClassPK() {
		return model.getClassPK();
	}

	/**
	 * Returns the company ID of this audit entry.
	 *
	 * @return the company ID of this audit entry
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the end date of this audit entry.
	 *
	 * @return the end date of this audit entry
	 */
	@Override
	public Date getEndDate() {
		return model.getEndDate();
	}

	/**
	 * Returns the extradata of this audit entry.
	 *
	 * @return the extradata of this audit entry
	 */
	@Override
	public String getExtradata() {
		return model.getExtradata();
	}

	/**
	 * Returns the group ID of this audit entry.
	 *
	 * @return the group ID of this audit entry
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the primary key of this audit entry.
	 *
	 * @return the primary key of this audit entry
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the start date of this audit entry.
	 *
	 * @return the start date of this audit entry
	 */
	@Override
	public Date getStartDate() {
		return model.getStartDate();
	}

	/**
	 * Returns the user ID of this audit entry.
	 *
	 * @return the user ID of this audit entry
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this audit entry.
	 *
	 * @return the user name of this audit entry
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this audit entry.
	 *
	 * @return the user uuid of this audit entry
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the action ID of this audit entry.
	 *
	 * @param actionId the action ID of this audit entry
	 */
	@Override
	public void setActionId(int actionId) {
		model.setActionId(actionId);
	}

	/**
	 * Sets the association class pk of this audit entry.
	 *
	 * @param associationClassPK the association class pk of this audit entry
	 */
	@Override
	public void setAssociationClassPK(long associationClassPK) {
		model.setAssociationClassPK(associationClassPK);
	}

	/**
	 * Sets the audit ID of this audit entry.
	 *
	 * @param auditId the audit ID of this audit entry
	 */
	@Override
	public void setAuditId(long auditId) {
		model.setAuditId(auditId);
	}

	@Override
	public void setClassName(String className) {
		model.setClassName(className);
	}

	/**
	 * Sets the class name ID of this audit entry.
	 *
	 * @param classNameId the class name ID of this audit entry
	 */
	@Override
	public void setClassNameId(long classNameId) {
		model.setClassNameId(classNameId);
	}

	/**
	 * Sets the class pk of this audit entry.
	 *
	 * @param classPK the class pk of this audit entry
	 */
	@Override
	public void setClassPK(long classPK) {
		model.setClassPK(classPK);
	}

	/**
	 * Sets the company ID of this audit entry.
	 *
	 * @param companyId the company ID of this audit entry
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the end date of this audit entry.
	 *
	 * @param endDate the end date of this audit entry
	 */
	@Override
	public void setEndDate(Date endDate) {
		model.setEndDate(endDate);
	}

	/**
	 * Sets the extradata of this audit entry.
	 *
	 * @param extradata the extradata of this audit entry
	 */
	@Override
	public void setExtradata(String extradata) {
		model.setExtradata(extradata);
	}

	/**
	 * Sets the group ID of this audit entry.
	 *
	 * @param groupId the group ID of this audit entry
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the primary key of this audit entry.
	 *
	 * @param primaryKey the primary key of this audit entry
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the start date of this audit entry.
	 *
	 * @param startDate the start date of this audit entry
	 */
	@Override
	public void setStartDate(Date startDate) {
		model.setStartDate(startDate);
	}

	/**
	 * Sets the user ID of this audit entry.
	 *
	 * @param userId the user ID of this audit entry
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this audit entry.
	 *
	 * @param userName the user name of this audit entry
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this audit entry.
	 *
	 * @param userUuid the user uuid of this audit entry
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	@Override
	protected AuditEntryWrapper wrap(AuditEntry auditEntry) {
		return new AuditEntryWrapper(auditEntry);
	}

}