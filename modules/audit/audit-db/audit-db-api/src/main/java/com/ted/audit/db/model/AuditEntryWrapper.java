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

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
public class AuditEntryWrapper implements AuditEntry, ModelWrapper<AuditEntry> {
	public AuditEntryWrapper(AuditEntry auditEntry) {
		_auditEntry = auditEntry;
	}

	@Override
	public Class<?> getModelClass() {
		return AuditEntry.class;
	}

	@Override
	public String getModelClassName() {
		return AuditEntry.class.getName();
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

	@Override
	public Object clone() {
		return new AuditEntryWrapper((AuditEntry)_auditEntry.clone());
	}

	@Override
	public int compareTo(AuditEntry auditEntry) {
		return _auditEntry.compareTo(auditEntry);
	}

	/**
	* Returns the action ID of this audit entry.
	*
	* @return the action ID of this audit entry
	*/
	@Override
	public int getActionId() {
		return _auditEntry.getActionId();
	}

	/**
	* Returns the association class pk of this audit entry.
	*
	* @return the association class pk of this audit entry
	*/
	@Override
	public long getAssociationClassPK() {
		return _auditEntry.getAssociationClassPK();
	}

	/**
	* Returns the audit ID of this audit entry.
	*
	* @return the audit ID of this audit entry
	*/
	@Override
	public long getAuditId() {
		return _auditEntry.getAuditId();
	}

	/**
	* Returns the fully qualified class name of this audit entry.
	*
	* @return the fully qualified class name of this audit entry
	*/
	@Override
	public String getClassName() {
		return _auditEntry.getClassName();
	}

	/**
	* Returns the class name ID of this audit entry.
	*
	* @return the class name ID of this audit entry
	*/
	@Override
	public long getClassNameId() {
		return _auditEntry.getClassNameId();
	}

	/**
	* Returns the class pk of this audit entry.
	*
	* @return the class pk of this audit entry
	*/
	@Override
	public long getClassPK() {
		return _auditEntry.getClassPK();
	}

	/**
	* Returns the company ID of this audit entry.
	*
	* @return the company ID of this audit entry
	*/
	@Override
	public long getCompanyId() {
		return _auditEntry.getCompanyId();
	}

	/**
	* Returns the end date of this audit entry.
	*
	* @return the end date of this audit entry
	*/
	@Override
	public Date getEndDate() {
		return _auditEntry.getEndDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _auditEntry.getExpandoBridge();
	}

	/**
	* Returns the extradata of this audit entry.
	*
	* @return the extradata of this audit entry
	*/
	@Override
	public String getExtradata() {
		return _auditEntry.getExtradata();
	}

	/**
	* Returns the group ID of this audit entry.
	*
	* @return the group ID of this audit entry
	*/
	@Override
	public long getGroupId() {
		return _auditEntry.getGroupId();
	}

	/**
	* Returns the primary key of this audit entry.
	*
	* @return the primary key of this audit entry
	*/
	@Override
	public long getPrimaryKey() {
		return _auditEntry.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _auditEntry.getPrimaryKeyObj();
	}

	/**
	* Returns the start date of this audit entry.
	*
	* @return the start date of this audit entry
	*/
	@Override
	public Date getStartDate() {
		return _auditEntry.getStartDate();
	}

	/**
	* Returns the user ID of this audit entry.
	*
	* @return the user ID of this audit entry
	*/
	@Override
	public long getUserId() {
		return _auditEntry.getUserId();
	}

	/**
	* Returns the user name of this audit entry.
	*
	* @return the user name of this audit entry
	*/
	@Override
	public String getUserName() {
		return _auditEntry.getUserName();
	}

	/**
	* Returns the user uuid of this audit entry.
	*
	* @return the user uuid of this audit entry
	*/
	@Override
	public String getUserUuid() {
		return _auditEntry.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _auditEntry.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _auditEntry.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _auditEntry.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _auditEntry.isNew();
	}

	@Override
	public void persist() {
		_auditEntry.persist();
	}

	/**
	* Sets the action ID of this audit entry.
	*
	* @param actionId the action ID of this audit entry
	*/
	@Override
	public void setActionId(int actionId) {
		_auditEntry.setActionId(actionId);
	}

	/**
	* Sets the association class pk of this audit entry.
	*
	* @param associationClassPK the association class pk of this audit entry
	*/
	@Override
	public void setAssociationClassPK(long associationClassPK) {
		_auditEntry.setAssociationClassPK(associationClassPK);
	}

	/**
	* Sets the audit ID of this audit entry.
	*
	* @param auditId the audit ID of this audit entry
	*/
	@Override
	public void setAuditId(long auditId) {
		_auditEntry.setAuditId(auditId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_auditEntry.setCachedModel(cachedModel);
	}

	@Override
	public void setClassName(String className) {
		_auditEntry.setClassName(className);
	}

	/**
	* Sets the class name ID of this audit entry.
	*
	* @param classNameId the class name ID of this audit entry
	*/
	@Override
	public void setClassNameId(long classNameId) {
		_auditEntry.setClassNameId(classNameId);
	}

	/**
	* Sets the class pk of this audit entry.
	*
	* @param classPK the class pk of this audit entry
	*/
	@Override
	public void setClassPK(long classPK) {
		_auditEntry.setClassPK(classPK);
	}

	/**
	* Sets the company ID of this audit entry.
	*
	* @param companyId the company ID of this audit entry
	*/
	@Override
	public void setCompanyId(long companyId) {
		_auditEntry.setCompanyId(companyId);
	}

	/**
	* Sets the end date of this audit entry.
	*
	* @param endDate the end date of this audit entry
	*/
	@Override
	public void setEndDate(Date endDate) {
		_auditEntry.setEndDate(endDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_auditEntry.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_auditEntry.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_auditEntry.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the extradata of this audit entry.
	*
	* @param extradata the extradata of this audit entry
	*/
	@Override
	public void setExtradata(String extradata) {
		_auditEntry.setExtradata(extradata);
	}

	/**
	* Sets the group ID of this audit entry.
	*
	* @param groupId the group ID of this audit entry
	*/
	@Override
	public void setGroupId(long groupId) {
		_auditEntry.setGroupId(groupId);
	}

	@Override
	public void setNew(boolean n) {
		_auditEntry.setNew(n);
	}

	/**
	* Sets the primary key of this audit entry.
	*
	* @param primaryKey the primary key of this audit entry
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_auditEntry.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_auditEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the start date of this audit entry.
	*
	* @param startDate the start date of this audit entry
	*/
	@Override
	public void setStartDate(Date startDate) {
		_auditEntry.setStartDate(startDate);
	}

	/**
	* Sets the user ID of this audit entry.
	*
	* @param userId the user ID of this audit entry
	*/
	@Override
	public void setUserId(long userId) {
		_auditEntry.setUserId(userId);
	}

	/**
	* Sets the user name of this audit entry.
	*
	* @param userName the user name of this audit entry
	*/
	@Override
	public void setUserName(String userName) {
		_auditEntry.setUserName(userName);
	}

	/**
	* Sets the user uuid of this audit entry.
	*
	* @param userUuid the user uuid of this audit entry
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_auditEntry.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<AuditEntry> toCacheModel() {
		return _auditEntry.toCacheModel();
	}

	@Override
	public AuditEntry toEscapedModel() {
		return new AuditEntryWrapper(_auditEntry.toEscapedModel());
	}

	@Override
	public String toString() {
		return _auditEntry.toString();
	}

	@Override
	public AuditEntry toUnescapedModel() {
		return new AuditEntryWrapper(_auditEntry.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _auditEntry.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AuditEntryWrapper)) {
			return false;
		}

		AuditEntryWrapper auditEntryWrapper = (AuditEntryWrapper)obj;

		if (Objects.equals(_auditEntry, auditEntryWrapper._auditEntry)) {
			return true;
		}

		return false;
	}

	@Override
	public AuditEntry getWrappedModel() {
		return _auditEntry;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _auditEntry.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _auditEntry.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_auditEntry.resetOriginalValues();
	}

	private final AuditEntry _auditEntry;
}