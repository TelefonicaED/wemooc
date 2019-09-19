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

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.petra.string.StringBundler;
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

import com.ted.lms.learning.activity.question.model.Question;
import com.ted.lms.learning.activity.question.model.QuestionModel;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model implementation for the Question service. Represents a row in the &quot;QU_Question&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>QuestionModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link QuestionImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see QuestionImpl
 * @generated
 */
@ProviderType
public class QuestionModelImpl
	extends BaseModelImpl<Question> implements QuestionModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a question model instance should use the <code>Question</code> interface instead.
	 */
	public static final String TABLE_NAME = "QU_Question";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR}, {"questionId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"lastPublishDate", Types.TIMESTAMP}, {"actId", Types.BIGINT},
		{"text_", Types.VARCHAR}, {"questionTypeId", Types.BIGINT},
		{"active_", Types.BOOLEAN}, {"weight", Types.BIGINT},
		{"penalize", Types.BOOLEAN}, {"extraContent", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

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

	public static final String TABLE_SQL_CREATE =
		"create table QU_Question (uuid_ VARCHAR(75) null,questionId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,lastPublishDate DATE null,actId LONG,text_ VARCHAR(75) null,questionTypeId LONG,active_ BOOLEAN,weight LONG,penalize BOOLEAN,extraContent VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP = "drop table QU_Question";

	public static final String ORDER_BY_JPQL =
		" ORDER BY question.questionId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY QU_Question.questionId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final long ACTID_COLUMN_BITMASK = 1L;

	public static final long COMPANYID_COLUMN_BITMASK = 2L;

	public static final long GROUPID_COLUMN_BITMASK = 4L;

	public static final long UUID_COLUMN_BITMASK = 8L;

	public static final long QUESTIONID_COLUMN_BITMASK = 16L;

	public static void setEntityCacheEnabled(boolean entityCacheEnabled) {
		_entityCacheEnabled = entityCacheEnabled;
	}

	public static void setFinderCacheEnabled(boolean finderCacheEnabled) {
		_finderCacheEnabled = finderCacheEnabled;
	}

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

		Map<String, Function<Question, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<Question, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Question, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((Question)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<Question, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<Question, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(Question)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<Question, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<Question, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, Question>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			Question.class.getClassLoader(), Question.class,
			ModelWrapper.class);

		try {
			Constructor<Question> constructor =
				(Constructor<Question>)proxyClass.getConstructor(
					InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException roe) {
					throw new InternalError(roe);
				}
			};
		}
		catch (NoSuchMethodException nsme) {
			throw new InternalError(nsme);
		}
	}

	private static final Map<String, Function<Question, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<Question, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<Question, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<Question, Object>>();
		Map<String, BiConsumer<Question, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<Question, ?>>();

		attributeGetterFunctions.put("uuid", Question::getUuid);
		attributeSetterBiConsumers.put(
			"uuid", (BiConsumer<Question, String>)Question::setUuid);
		attributeGetterFunctions.put("questionId", Question::getQuestionId);
		attributeSetterBiConsumers.put(
			"questionId", (BiConsumer<Question, Long>)Question::setQuestionId);
		attributeGetterFunctions.put("groupId", Question::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId", (BiConsumer<Question, Long>)Question::setGroupId);
		attributeGetterFunctions.put("companyId", Question::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId", (BiConsumer<Question, Long>)Question::setCompanyId);
		attributeGetterFunctions.put("userId", Question::getUserId);
		attributeSetterBiConsumers.put(
			"userId", (BiConsumer<Question, Long>)Question::setUserId);
		attributeGetterFunctions.put("userName", Question::getUserName);
		attributeSetterBiConsumers.put(
			"userName", (BiConsumer<Question, String>)Question::setUserName);
		attributeGetterFunctions.put("createDate", Question::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate", (BiConsumer<Question, Date>)Question::setCreateDate);
		attributeGetterFunctions.put("modifiedDate", Question::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<Question, Date>)Question::setModifiedDate);
		attributeGetterFunctions.put(
			"lastPublishDate", Question::getLastPublishDate);
		attributeSetterBiConsumers.put(
			"lastPublishDate",
			(BiConsumer<Question, Date>)Question::setLastPublishDate);
		attributeGetterFunctions.put("actId", Question::getActId);
		attributeSetterBiConsumers.put(
			"actId", (BiConsumer<Question, Long>)Question::setActId);
		attributeGetterFunctions.put("text", Question::getText);
		attributeSetterBiConsumers.put(
			"text", (BiConsumer<Question, String>)Question::setText);
		attributeGetterFunctions.put(
			"questionTypeId", Question::getQuestionTypeId);
		attributeSetterBiConsumers.put(
			"questionTypeId",
			(BiConsumer<Question, Long>)Question::setQuestionTypeId);
		attributeGetterFunctions.put("active", Question::getActive);
		attributeSetterBiConsumers.put(
			"active", (BiConsumer<Question, Boolean>)Question::setActive);
		attributeGetterFunctions.put("weight", Question::getWeight);
		attributeSetterBiConsumers.put(
			"weight", (BiConsumer<Question, Long>)Question::setWeight);
		attributeGetterFunctions.put("penalize", Question::getPenalize);
		attributeSetterBiConsumers.put(
			"penalize", (BiConsumer<Question, Boolean>)Question::setPenalize);
		attributeGetterFunctions.put("extraContent", Question::getExtraContent);
		attributeSetterBiConsumers.put(
			"extraContent",
			(BiConsumer<Question, String>)Question::setExtraContent);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
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
		_columnBitmask |= UUID_COLUMN_BITMASK;

		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	@Override
	public long getQuestionId() {
		return _questionId;
	}

	@Override
	public void setQuestionId(long questionId) {
		_columnBitmask = -1L;

		_questionId = questionId;
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
	public Date getLastPublishDate() {
		return _lastPublishDate;
	}

	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		_lastPublishDate = lastPublishDate;
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

	@Override
	public long getQuestionTypeId() {
		return _questionTypeId;
	}

	@Override
	public void setQuestionTypeId(long questionTypeId) {
		_questionTypeId = questionTypeId;
	}

	@Override
	public boolean getActive() {
		return _active;
	}

	@Override
	public boolean isActive() {
		return _active;
	}

	@Override
	public void setActive(boolean active) {
		_active = active;
	}

	@Override
	public long getWeight() {
		return _weight;
	}

	@Override
	public void setWeight(long weight) {
		_weight = weight;
	}

	@Override
	public boolean getPenalize() {
		return _penalize;
	}

	@Override
	public boolean isPenalize() {
		return _penalize;
	}

	@Override
	public void setPenalize(boolean penalize) {
		_penalize = penalize;
	}

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
		return new StagedModelType(
			PortalUtil.getClassNameId(Question.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), Question.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Question toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = _escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
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
		return _entityCacheEnabled;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _finderCacheEnabled;
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
		Map<String, Function<Question, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<Question, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Question, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((Question)this));
			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		Map<String, Function<Question, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<Question, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Question, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((Question)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static final Function<InvocationHandler, Question>
		_escapedModelProxyProviderFunction = _getProxyProviderFunction();
	private static boolean _entityCacheEnabled;
	private static boolean _finderCacheEnabled;

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