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

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;

import com.ted.lms.model.LearningActivityTry;
import com.ted.lms.model.LearningActivityTryModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the LearningActivityTry service. Represents a row in the &quot;LMS_LearningActivityTry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link LearningActivityTryModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link LearningActivityTryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LearningActivityTryImpl
 * @see LearningActivityTry
 * @see LearningActivityTryModel
 * @generated
 */
@ProviderType
public class LearningActivityTryModelImpl extends BaseModelImpl<LearningActivityTry>
	implements LearningActivityTryModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a learning activity try model instance should use the {@link LearningActivityTry} interface instead.
	 */
	public static final String TABLE_NAME = "LMS_LearningActivityTry";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "latId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userModifiedId", Types.BIGINT },
			{ "userModifiedName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "actId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "result", Types.DOUBLE },
			{ "comments", Types.CLOB },
			{ "startDate", Types.TIMESTAMP },
			{ "endDate", Types.TIMESTAMP },
			{ "endUserDate", Types.TIMESTAMP },
			{ "tryResultData", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("latId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userModifiedId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userModifiedName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("actId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("result", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("comments", Types.CLOB);
		TABLE_COLUMNS_MAP.put("startDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("endDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("endUserDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("tryResultData", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE = "create table LMS_LearningActivityTry (uuid_ VARCHAR(75) null,latId LONG not null primary key,groupId LONG,companyId LONG,userModifiedId LONG,userModifiedName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,actId LONG,userId LONG,result DOUBLE,comments TEXT null,startDate DATE null,endDate DATE null,endUserDate DATE null,tryResultData TEXT null)";
	public static final String TABLE_SQL_DROP = "drop table LMS_LearningActivityTry";
	public static final String ORDER_BY_JPQL = " ORDER BY learningActivityTry.latId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY LMS_LearningActivityTry.latId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.ted.lms.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.com.ted.lms.model.LearningActivityTry"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.ted.lms.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.com.ted.lms.model.LearningActivityTry"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.ted.lms.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.ted.lms.model.LearningActivityTry"),
			true);
	public static final long ACTID_COLUMN_BITMASK = 1L;
	public static final long COMPANYID_COLUMN_BITMASK = 2L;
	public static final long GROUPID_COLUMN_BITMASK = 4L;
	public static final long USERID_COLUMN_BITMASK = 8L;
	public static final long UUID_COLUMN_BITMASK = 16L;
	public static final long LATID_COLUMN_BITMASK = 32L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.ted.lms.service.util.ServiceProps.get(
				"lock.expiration.time.com.ted.lms.model.LearningActivityTry"));

	public LearningActivityTryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _latId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setLatId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _latId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return LearningActivityTry.class;
	}

	@Override
	public String getModelClassName() {
		return LearningActivityTry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("latId", getLatId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userModifiedId", getUserModifiedId());
		attributes.put("userModifiedName", getUserModifiedName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("actId", getActId());
		attributes.put("userId", getUserId());
		attributes.put("result", getResult());
		attributes.put("comments", getComments());
		attributes.put("startDate", getStartDate());
		attributes.put("endDate", getEndDate());
		attributes.put("endUserDate", getEndUserDate());
		attributes.put("tryResultData", getTryResultData());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long latId = (Long)attributes.get("latId");

		if (latId != null) {
			setLatId(latId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userModifiedId = (Long)attributes.get("userModifiedId");

		if (userModifiedId != null) {
			setUserModifiedId(userModifiedId);
		}

		String userModifiedName = (String)attributes.get("userModifiedName");

		if (userModifiedName != null) {
			setUserModifiedName(userModifiedName);
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

		Double result = (Double)attributes.get("result");

		if (result != null) {
			setResult(result);
		}

		String comments = (String)attributes.get("comments");

		if (comments != null) {
			setComments(comments);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Date endDate = (Date)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}

		Date endUserDate = (Date)attributes.get("endUserDate");

		if (endUserDate != null) {
			setEndUserDate(endUserDate);
		}

		String tryResultData = (String)attributes.get("tryResultData");

		if (tryResultData != null) {
			setTryResultData(tryResultData);
		}
	}

	@Override
	public String getUuid() {
		if (_uuid == null) {
			return "";
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	@Override
	public long getLatId() {
		return _latId;
	}

	@Override
	public void setLatId(long latId) {
		_latId = latId;
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_columnBitmask |= GROUPID_COLUMN_BITMASK;

		if (!_setOriginalGroupId) {
			_setOriginalGroupId = true;

			_originalGroupId = _groupId;
		}

		_groupId = groupId;
	}

	public long getOriginalGroupId() {
		return _originalGroupId;
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

	@Override
	public long getUserModifiedId() {
		return _userModifiedId;
	}

	@Override
	public void setUserModifiedId(long userModifiedId) {
		_userModifiedId = userModifiedId;
	}

	@Override
	public String getUserModifiedName() {
		if (_userModifiedName == null) {
			return "";
		}
		else {
			return _userModifiedName;
		}
	}

	@Override
	public void setUserModifiedName(String userModifiedName) {
		_userModifiedName = userModifiedName;
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

	@Override
	public long getActId() {
		return _actId;
	}

	@Override
	public void setActId(long actId) {
		_columnBitmask |= ACTID_COLUMN_BITMASK;

		if (!_setOriginalActId) {
			_setOriginalActId = true;

			_originalActId = _actId;
		}

		_actId = actId;
	}

	public long getOriginalActId() {
		return _originalActId;
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_columnBitmask |= USERID_COLUMN_BITMASK;

		if (!_setOriginalUserId) {
			_setOriginalUserId = true;

			_originalUserId = _userId;
		}

		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	public long getOriginalUserId() {
		return _originalUserId;
	}

	@Override
	public double getResult() {
		return _result;
	}

	@Override
	public void setResult(double result) {
		_result = result;
	}

	@Override
	public String getComments() {
		if (_comments == null) {
			return "";
		}
		else {
			return _comments;
		}
	}

	@Override
	public void setComments(String comments) {
		_comments = comments;
	}

	@Override
	public Date getStartDate() {
		return _startDate;
	}

	@Override
	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	@Override
	public Date getEndDate() {
		return _endDate;
	}

	@Override
	public void setEndDate(Date endDate) {
		_endDate = endDate;
	}

	@Override
	public Date getEndUserDate() {
		return _endUserDate;
	}

	@Override
	public void setEndUserDate(Date endUserDate) {
		_endUserDate = endUserDate;
	}

	@Override
	public String getTryResultData() {
		if (_tryResultData == null) {
			return "";
		}
		else {
			return _tryResultData;
		}
	}

	@Override
	public void setTryResultData(String tryResultData) {
		_tryResultData = tryResultData;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(PortalUtil.getClassNameId(
				LearningActivityTry.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			LearningActivityTry.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public LearningActivityTry toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (LearningActivityTry)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		LearningActivityTryImpl learningActivityTryImpl = new LearningActivityTryImpl();

		learningActivityTryImpl.setUuid(getUuid());
		learningActivityTryImpl.setLatId(getLatId());
		learningActivityTryImpl.setGroupId(getGroupId());
		learningActivityTryImpl.setCompanyId(getCompanyId());
		learningActivityTryImpl.setUserModifiedId(getUserModifiedId());
		learningActivityTryImpl.setUserModifiedName(getUserModifiedName());
		learningActivityTryImpl.setCreateDate(getCreateDate());
		learningActivityTryImpl.setModifiedDate(getModifiedDate());
		learningActivityTryImpl.setActId(getActId());
		learningActivityTryImpl.setUserId(getUserId());
		learningActivityTryImpl.setResult(getResult());
		learningActivityTryImpl.setComments(getComments());
		learningActivityTryImpl.setStartDate(getStartDate());
		learningActivityTryImpl.setEndDate(getEndDate());
		learningActivityTryImpl.setEndUserDate(getEndUserDate());
		learningActivityTryImpl.setTryResultData(getTryResultData());

		learningActivityTryImpl.resetOriginalValues();

		return learningActivityTryImpl;
	}

	@Override
	public int compareTo(LearningActivityTry learningActivityTry) {
		long primaryKey = learningActivityTry.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LearningActivityTry)) {
			return false;
		}

		LearningActivityTry learningActivityTry = (LearningActivityTry)obj;

		long primaryKey = learningActivityTry.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		LearningActivityTryModelImpl learningActivityTryModelImpl = this;

		learningActivityTryModelImpl._originalUuid = learningActivityTryModelImpl._uuid;

		learningActivityTryModelImpl._originalGroupId = learningActivityTryModelImpl._groupId;

		learningActivityTryModelImpl._setOriginalGroupId = false;

		learningActivityTryModelImpl._originalCompanyId = learningActivityTryModelImpl._companyId;

		learningActivityTryModelImpl._setOriginalCompanyId = false;

		learningActivityTryModelImpl._setModifiedDate = false;

		learningActivityTryModelImpl._originalActId = learningActivityTryModelImpl._actId;

		learningActivityTryModelImpl._setOriginalActId = false;

		learningActivityTryModelImpl._originalUserId = learningActivityTryModelImpl._userId;

		learningActivityTryModelImpl._setOriginalUserId = false;

		learningActivityTryModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<LearningActivityTry> toCacheModel() {
		LearningActivityTryCacheModel learningActivityTryCacheModel = new LearningActivityTryCacheModel();

		learningActivityTryCacheModel.uuid = getUuid();

		String uuid = learningActivityTryCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			learningActivityTryCacheModel.uuid = null;
		}

		learningActivityTryCacheModel.latId = getLatId();

		learningActivityTryCacheModel.groupId = getGroupId();

		learningActivityTryCacheModel.companyId = getCompanyId();

		learningActivityTryCacheModel.userModifiedId = getUserModifiedId();

		learningActivityTryCacheModel.userModifiedName = getUserModifiedName();

		String userModifiedName = learningActivityTryCacheModel.userModifiedName;

		if ((userModifiedName != null) && (userModifiedName.length() == 0)) {
			learningActivityTryCacheModel.userModifiedName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			learningActivityTryCacheModel.createDate = createDate.getTime();
		}
		else {
			learningActivityTryCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			learningActivityTryCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			learningActivityTryCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		learningActivityTryCacheModel.actId = getActId();

		learningActivityTryCacheModel.userId = getUserId();

		learningActivityTryCacheModel.result = getResult();

		learningActivityTryCacheModel.comments = getComments();

		String comments = learningActivityTryCacheModel.comments;

		if ((comments != null) && (comments.length() == 0)) {
			learningActivityTryCacheModel.comments = null;
		}

		Date startDate = getStartDate();

		if (startDate != null) {
			learningActivityTryCacheModel.startDate = startDate.getTime();
		}
		else {
			learningActivityTryCacheModel.startDate = Long.MIN_VALUE;
		}

		Date endDate = getEndDate();

		if (endDate != null) {
			learningActivityTryCacheModel.endDate = endDate.getTime();
		}
		else {
			learningActivityTryCacheModel.endDate = Long.MIN_VALUE;
		}

		Date endUserDate = getEndUserDate();

		if (endUserDate != null) {
			learningActivityTryCacheModel.endUserDate = endUserDate.getTime();
		}
		else {
			learningActivityTryCacheModel.endUserDate = Long.MIN_VALUE;
		}

		learningActivityTryCacheModel.tryResultData = getTryResultData();

		String tryResultData = learningActivityTryCacheModel.tryResultData;

		if ((tryResultData != null) && (tryResultData.length() == 0)) {
			learningActivityTryCacheModel.tryResultData = null;
		}

		return learningActivityTryCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(33);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", latId=");
		sb.append(getLatId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userModifiedId=");
		sb.append(getUserModifiedId());
		sb.append(", userModifiedName=");
		sb.append(getUserModifiedName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", actId=");
		sb.append(getActId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", result=");
		sb.append(getResult());
		sb.append(", comments=");
		sb.append(getComments());
		sb.append(", startDate=");
		sb.append(getStartDate());
		sb.append(", endDate=");
		sb.append(getEndDate());
		sb.append(", endUserDate=");
		sb.append(getEndUserDate());
		sb.append(", tryResultData=");
		sb.append(getTryResultData());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(52);

		sb.append("<model><model-name>");
		sb.append("com.ted.lms.model.LearningActivityTry");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>latId</column-name><column-value><![CDATA[");
		sb.append(getLatId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userModifiedId</column-name><column-value><![CDATA[");
		sb.append(getUserModifiedId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userModifiedName</column-name><column-value><![CDATA[");
		sb.append(getUserModifiedName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>actId</column-name><column-value><![CDATA[");
		sb.append(getActId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>result</column-name><column-value><![CDATA[");
		sb.append(getResult());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>comments</column-name><column-value><![CDATA[");
		sb.append(getComments());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>startDate</column-name><column-value><![CDATA[");
		sb.append(getStartDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>endDate</column-name><column-value><![CDATA[");
		sb.append(getEndDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>endUserDate</column-name><column-value><![CDATA[");
		sb.append(getEndUserDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>tryResultData</column-name><column-value><![CDATA[");
		sb.append(getTryResultData());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = LearningActivityTry.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			LearningActivityTry.class, ModelWrapper.class
		};
	private String _uuid;
	private String _originalUuid;
	private long _latId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userModifiedId;
	private String _userModifiedName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _actId;
	private long _originalActId;
	private boolean _setOriginalActId;
	private long _userId;
	private long _originalUserId;
	private boolean _setOriginalUserId;
	private double _result;
	private String _comments;
	private Date _startDate;
	private Date _endDate;
	private Date _endUserDate;
	private String _tryResultData;
	private long _columnBitmask;
	private LearningActivityTry _escapedModel;
}