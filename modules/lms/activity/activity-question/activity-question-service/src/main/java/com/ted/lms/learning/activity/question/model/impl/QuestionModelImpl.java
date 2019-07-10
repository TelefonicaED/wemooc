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

package com.ted.lms.learning.activity.question.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
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

import com.ted.lms.learning.activity.question.model.Question;
import com.ted.lms.learning.activity.question.model.QuestionModel;
import com.ted.lms.learning.activity.question.model.QuestionSoap;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base model implementation for the Question service. Represents a row in the &quot;qu_Question&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link QuestionModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link QuestionImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see QuestionImpl
 * @see Question
 * @see QuestionModel
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class QuestionModelImpl extends BaseModelImpl<Question>
	implements QuestionModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a question model instance should use the {@link Question} interface instead.
	 */
	public static final String TABLE_NAME = "qu_Question";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "questionId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "lastPublishDate", Types.TIMESTAMP },
			{ "actId", Types.BIGINT },
			{ "text_", Types.VARCHAR },
			{ "questionTypeId", Types.BIGINT },
			{ "active_", Types.BOOLEAN },
			{ "weight", Types.BIGINT },
			{ "penalize", Types.BOOLEAN },
			{ "extraContent", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("questionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("lastPublishDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("actId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("text_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("questionTypeId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("active_", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("weight", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("penalize", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("extraContent", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE = "create table qu_Question (uuid_ VARCHAR(75) null,questionId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,lastPublishDate DATE null,actId LONG,text_ TEXT null,questionTypeId LONG,active_ BOOLEAN,weight LONG,penalize BOOLEAN,extraContent STRING null)";
	public static final String TABLE_SQL_DROP = "drop table qu_Question";
	public static final String ORDER_BY_JPQL = " ORDER BY question.questionId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY qu_Question.questionId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.ted.lms.learning.activity.question.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.com.ted.lms.learning.activity.question.model.Question"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.ted.lms.learning.activity.question.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.com.ted.lms.learning.activity.question.model.Question"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.ted.lms.learning.activity.question.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.ted.lms.learning.activity.question.model.Question"),
			true);
	public static final long ACTID_COLUMN_BITMASK = 1L;
	public static final long COMPANYID_COLUMN_BITMASK = 2L;
	public static final long GROUPID_COLUMN_BITMASK = 4L;
	public static final long UUID_COLUMN_BITMASK = 8L;
	public static final long QUESTIONID_COLUMN_BITMASK = 16L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static Question toModel(QuestionSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		Question model = new QuestionImpl();

		model.setUuid(soapModel.getUuid());
		model.setQuestionId(soapModel.getQuestionId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setLastPublishDate(soapModel.getLastPublishDate());
		model.setActId(soapModel.getActId());
		model.setText(soapModel.getText());
		model.setQuestionTypeId(soapModel.getQuestionTypeId());
		model.setActive(soapModel.isActive());
		model.setWeight(soapModel.getWeight());
		model.setPenalize(soapModel.isPenalize());
		model.setExtraContent(soapModel.getExtraContent());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<Question> toModels(QuestionSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<Question> models = new ArrayList<Question>(soapModels.length);

		for (QuestionSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.ted.lms.learning.activity.question.service.util.ServiceProps.get(
				"lock.expiration.time.com.ted.lms.learning.activity.question.model.Question"));

	public QuestionModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _questionId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setQuestionId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _questionId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Question.class;
	}

	@Override
	public String getModelClassName() {
		return Question.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("questionId", getQuestionId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("lastPublishDate", getLastPublishDate());
		attributes.put("actId", getActId());
		attributes.put("text", getText());
		attributes.put("questionTypeId", getQuestionTypeId());
		attributes.put("active", isActive());
		attributes.put("weight", getWeight());
		attributes.put("penalize", isPenalize());
		attributes.put("extraContent", getExtraContent());

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

		Long questionId = (Long)attributes.get("questionId");

		if (questionId != null) {
			setQuestionId(questionId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Date lastPublishDate = (Date)attributes.get("lastPublishDate");

		if (lastPublishDate != null) {
			setLastPublishDate(lastPublishDate);
		}

		Long actId = (Long)attributes.get("actId");

		if (actId != null) {
			setActId(actId);
		}

		String text = (String)attributes.get("text");

		if (text != null) {
			setText(text);
		}

		Long questionTypeId = (Long)attributes.get("questionTypeId");

		if (questionTypeId != null) {
			setQuestionTypeId(questionTypeId);
		}

		Boolean active = (Boolean)attributes.get("active");

		if (active != null) {
			setActive(active);
		}

		Long weight = (Long)attributes.get("weight");

		if (weight != null) {
			setWeight(weight);
		}

		Boolean penalize = (Boolean)attributes.get("penalize");

		if (penalize != null) {
			setPenalize(penalize);
		}

		String extraContent = (String)attributes.get("extraContent");

		if (extraContent != null) {
			setExtraContent(extraContent);
		}
	}

	@JSON
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

	@JSON
	@Override
	public long getQuestionId() {
		return _questionId;
	}

	@Override
	public void setQuestionId(long questionId) {
		_columnBitmask = -1L;

		_questionId = questionId;
	}

	@JSON
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

	@JSON
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

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
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

	@JSON
	@Override
	public String getUserName() {
		if (_userName == null) {
			return "";
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@JSON
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

	@JSON
	@Override
	public Date getLastPublishDate() {
		return _lastPublishDate;
	}

	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		_lastPublishDate = lastPublishDate;
	}

	@JSON
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

	@JSON
	@Override
	public String getText() {
		if (_text == null) {
			return "";
		}
		else {
			return _text;
		}
	}

	@Override
	public void setText(String text) {
		_text = text;
	}

	@JSON
	@Override
	public long getQuestionTypeId() {
		return _questionTypeId;
	}

	@Override
	public void setQuestionTypeId(long questionTypeId) {
		_questionTypeId = questionTypeId;
	}

	@JSON
	@Override
	public boolean getActive() {
		return _active;
	}

	@JSON
	@Override
	public boolean isActive() {
		return _active;
	}

	@Override
	public void setActive(boolean active) {
		_active = active;
	}

	@JSON
	@Override
	public long getWeight() {
		return _weight;
	}

	@Override
	public void setWeight(long weight) {
		_weight = weight;
	}

	@JSON
	@Override
	public boolean getPenalize() {
		return _penalize;
	}

	@JSON
	@Override
	public boolean isPenalize() {
		return _penalize;
	}

	@Override
	public void setPenalize(boolean penalize) {
		_penalize = penalize;
	}

	@JSON
	@Override
	public String getExtraContent() {
		if (_extraContent == null) {
			return "";
		}
		else {
			return _extraContent;
		}
	}

	@Override
	public void setExtraContent(String extraContent) {
		_extraContent = extraContent;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(PortalUtil.getClassNameId(
				Question.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			Question.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Question toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (Question)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		QuestionImpl questionImpl = new QuestionImpl();

		questionImpl.setUuid(getUuid());
		questionImpl.setQuestionId(getQuestionId());
		questionImpl.setGroupId(getGroupId());
		questionImpl.setCompanyId(getCompanyId());
		questionImpl.setUserId(getUserId());
		questionImpl.setUserName(getUserName());
		questionImpl.setCreateDate(getCreateDate());
		questionImpl.setModifiedDate(getModifiedDate());
		questionImpl.setLastPublishDate(getLastPublishDate());
		questionImpl.setActId(getActId());
		questionImpl.setText(getText());
		questionImpl.setQuestionTypeId(getQuestionTypeId());
		questionImpl.setActive(isActive());
		questionImpl.setWeight(getWeight());
		questionImpl.setPenalize(isPenalize());
		questionImpl.setExtraContent(getExtraContent());

		questionImpl.resetOriginalValues();

		return questionImpl;
	}

	@Override
	public int compareTo(Question question) {
		int value = 0;

		if (getQuestionId() < question.getQuestionId()) {
			value = -1;
		}
		else if (getQuestionId() > question.getQuestionId()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Question)) {
			return false;
		}

		Question question = (Question)obj;

		long primaryKey = question.getPrimaryKey();

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
		QuestionModelImpl questionModelImpl = this;

		questionModelImpl._originalUuid = questionModelImpl._uuid;

		questionModelImpl._originalGroupId = questionModelImpl._groupId;

		questionModelImpl._setOriginalGroupId = false;

		questionModelImpl._originalCompanyId = questionModelImpl._companyId;

		questionModelImpl._setOriginalCompanyId = false;

		questionModelImpl._setModifiedDate = false;

		questionModelImpl._originalActId = questionModelImpl._actId;

		questionModelImpl._setOriginalActId = false;

		questionModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Question> toCacheModel() {
		QuestionCacheModel questionCacheModel = new QuestionCacheModel();

		questionCacheModel.uuid = getUuid();

		String uuid = questionCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			questionCacheModel.uuid = null;
		}

		questionCacheModel.questionId = getQuestionId();

		questionCacheModel.groupId = getGroupId();

		questionCacheModel.companyId = getCompanyId();

		questionCacheModel.userId = getUserId();

		questionCacheModel.userName = getUserName();

		String userName = questionCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			questionCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			questionCacheModel.createDate = createDate.getTime();
		}
		else {
			questionCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			questionCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			questionCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		Date lastPublishDate = getLastPublishDate();

		if (lastPublishDate != null) {
			questionCacheModel.lastPublishDate = lastPublishDate.getTime();
		}
		else {
			questionCacheModel.lastPublishDate = Long.MIN_VALUE;
		}

		questionCacheModel.actId = getActId();

		questionCacheModel.text = getText();

		String text = questionCacheModel.text;

		if ((text != null) && (text.length() == 0)) {
			questionCacheModel.text = null;
		}

		questionCacheModel.questionTypeId = getQuestionTypeId();

		questionCacheModel.active = isActive();

		questionCacheModel.weight = getWeight();

		questionCacheModel.penalize = isPenalize();

		questionCacheModel.extraContent = getExtraContent();

		String extraContent = questionCacheModel.extraContent;

		if ((extraContent != null) && (extraContent.length() == 0)) {
			questionCacheModel.extraContent = null;
		}

		return questionCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(33);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", questionId=");
		sb.append(getQuestionId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", lastPublishDate=");
		sb.append(getLastPublishDate());
		sb.append(", actId=");
		sb.append(getActId());
		sb.append(", text=");
		sb.append(getText());
		sb.append(", questionTypeId=");
		sb.append(getQuestionTypeId());
		sb.append(", active=");
		sb.append(isActive());
		sb.append(", weight=");
		sb.append(getWeight());
		sb.append(", penalize=");
		sb.append(isPenalize());
		sb.append(", extraContent=");
		sb.append(getExtraContent());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(52);

		sb.append("<model><model-name>");
		sb.append("com.ted.lms.learning.activity.question.model.Question");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>questionId</column-name><column-value><![CDATA[");
		sb.append(getQuestionId());
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
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
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
			"<column><column-name>lastPublishDate</column-name><column-value><![CDATA[");
		sb.append(getLastPublishDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>actId</column-name><column-value><![CDATA[");
		sb.append(getActId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>text</column-name><column-value><![CDATA[");
		sb.append(getText());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>questionTypeId</column-name><column-value><![CDATA[");
		sb.append(getQuestionTypeId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>active</column-name><column-value><![CDATA[");
		sb.append(isActive());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>weight</column-name><column-value><![CDATA[");
		sb.append(getWeight());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>penalize</column-name><column-value><![CDATA[");
		sb.append(isPenalize());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>extraContent</column-name><column-value><![CDATA[");
		sb.append(getExtraContent());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = Question.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			Question.class, ModelWrapper.class
		};
	private String _uuid;
	private String _originalUuid;
	private long _questionId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private Date _lastPublishDate;
	private long _actId;
	private long _originalActId;
	private boolean _setOriginalActId;
	private String _text;
	private long _questionTypeId;
	private boolean _active;
	private long _weight;
	private boolean _penalize;
	private String _extraContent;
	private long _columnBitmask;
	private Question _escapedModel;
}