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

package com.ted.postcondition.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.Validator;

import com.ted.postcondition.model.PostconditionRelation;
import com.ted.postcondition.model.PostconditionRelationModel;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model implementation for the PostconditionRelation service. Represents a row in the &quot;post_PostconditionRelation&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>PostconditionRelationModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link PostconditionRelationImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PostconditionRelationImpl
 * @generated
 */
@ProviderType
public class PostconditionRelationModelImpl
	extends BaseModelImpl<PostconditionRelation>
	implements PostconditionRelationModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a postcondition relation model instance should use the <code>PostconditionRelation</code> interface instead.
	 */
	public static final String TABLE_NAME = "post_PostconditionRelation";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR}, {"postconditionRelationId", Types.BIGINT},
		{"classNamePostconditionId", Types.BIGINT},
		{"classNameId", Types.BIGINT}, {"classPK", Types.BIGINT},
		{"extraData", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("postconditionRelationId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classNamePostconditionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("extraData", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table post_PostconditionRelation (uuid_ VARCHAR(75) null,postconditionRelationId LONG not null primary key,classNamePostconditionId LONG,classNameId LONG,classPK LONG,extraData VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP =
		"drop table post_PostconditionRelation";

	public static final String ORDER_BY_JPQL =
		" ORDER BY postconditionRelation.postconditionRelationId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY post_PostconditionRelation.postconditionRelationId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final long CLASSNAMEID_COLUMN_BITMASK = 1L;

	public static final long CLASSNAMEPOSTCONDITIONID_COLUMN_BITMASK = 2L;

	public static final long CLASSPK_COLUMN_BITMASK = 4L;

	public static final long UUID_COLUMN_BITMASK = 8L;

	public static final long POSTCONDITIONRELATIONID_COLUMN_BITMASK = 16L;

	public static void setEntityCacheEnabled(boolean entityCacheEnabled) {
		_entityCacheEnabled = entityCacheEnabled;
	}

	public static void setFinderCacheEnabled(boolean finderCacheEnabled) {
		_finderCacheEnabled = finderCacheEnabled;
	}

	public PostconditionRelationModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _postconditionRelationId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setPostconditionRelationId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _postconditionRelationId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return PostconditionRelation.class;
	}

	@Override
	public String getModelClassName() {
		return PostconditionRelation.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<PostconditionRelation, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<PostconditionRelation, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<PostconditionRelation, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((PostconditionRelation)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<PostconditionRelation, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<PostconditionRelation, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(PostconditionRelation)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<PostconditionRelation, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<PostconditionRelation, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, PostconditionRelation>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			PostconditionRelation.class.getClassLoader(),
			PostconditionRelation.class, ModelWrapper.class);

		try {
			Constructor<PostconditionRelation> constructor =
				(Constructor<PostconditionRelation>)proxyClass.getConstructor(
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

	private static final Map<String, Function<PostconditionRelation, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<PostconditionRelation, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<PostconditionRelation, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<PostconditionRelation, Object>>();
		Map<String, BiConsumer<PostconditionRelation, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String, BiConsumer<PostconditionRelation, ?>>();

		attributeGetterFunctions.put("uuid", PostconditionRelation::getUuid);
		attributeSetterBiConsumers.put(
			"uuid",
			(BiConsumer<PostconditionRelation, String>)
				PostconditionRelation::setUuid);
		attributeGetterFunctions.put(
			"postconditionRelationId",
			PostconditionRelation::getPostconditionRelationId);
		attributeSetterBiConsumers.put(
			"postconditionRelationId",
			(BiConsumer<PostconditionRelation, Long>)
				PostconditionRelation::setPostconditionRelationId);
		attributeGetterFunctions.put(
			"classNamePostconditionId",
			PostconditionRelation::getClassNamePostconditionId);
		attributeSetterBiConsumers.put(
			"classNamePostconditionId",
			(BiConsumer<PostconditionRelation, Long>)
				PostconditionRelation::setClassNamePostconditionId);
		attributeGetterFunctions.put(
			"classNameId", PostconditionRelation::getClassNameId);
		attributeSetterBiConsumers.put(
			"classNameId",
			(BiConsumer<PostconditionRelation, Long>)
				PostconditionRelation::setClassNameId);
		attributeGetterFunctions.put(
			"classPK", PostconditionRelation::getClassPK);
		attributeSetterBiConsumers.put(
			"classPK",
			(BiConsumer<PostconditionRelation, Long>)
				PostconditionRelation::setClassPK);
		attributeGetterFunctions.put(
			"extraData", PostconditionRelation::getExtraData);
		attributeSetterBiConsumers.put(
			"extraData",
			(BiConsumer<PostconditionRelation, String>)
				PostconditionRelation::setExtraData);

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
	public long getPostconditionRelationId() {
		return _postconditionRelationId;
	}

	@Override
	public void setPostconditionRelationId(long postconditionRelationId) {
		_postconditionRelationId = postconditionRelationId;
	}

	@Override
	public long getClassNamePostconditionId() {
		return _classNamePostconditionId;
	}

	@Override
	public void setClassNamePostconditionId(long classNamePostconditionId) {
		_columnBitmask |= CLASSNAMEPOSTCONDITIONID_COLUMN_BITMASK;

		if (!_setOriginalClassNamePostconditionId) {
			_setOriginalClassNamePostconditionId = true;

			_originalClassNamePostconditionId = _classNamePostconditionId;
		}

		_classNamePostconditionId = classNamePostconditionId;
	}

	public long getOriginalClassNamePostconditionId() {
		return _originalClassNamePostconditionId;
	}

	@Override
	public String getClassName() {
		if (getClassNameId() <= 0) {
			return "";
		}

		return PortalUtil.getClassName(getClassNameId());
	}

	@Override
	public void setClassName(String className) {
		long classNameId = 0;

		if (Validator.isNotNull(className)) {
			classNameId = PortalUtil.getClassNameId(className);
		}

		setClassNameId(classNameId);
	}

	@Override
	public long getClassNameId() {
		return _classNameId;
	}

	@Override
	public void setClassNameId(long classNameId) {
		_columnBitmask |= CLASSNAMEID_COLUMN_BITMASK;

		if (!_setOriginalClassNameId) {
			_setOriginalClassNameId = true;

			_originalClassNameId = _classNameId;
		}

		_classNameId = classNameId;
	}

	public long getOriginalClassNameId() {
		return _originalClassNameId;
	}

	@Override
	public long getClassPK() {
		return _classPK;
	}

	@Override
	public void setClassPK(long classPK) {
		_columnBitmask |= CLASSPK_COLUMN_BITMASK;

		if (!_setOriginalClassPK) {
			_setOriginalClassPK = true;

			_originalClassPK = _classPK;
		}

		_classPK = classPK;
	}

	public long getOriginalClassPK() {
		return _originalClassPK;
	}

	@Override
	public String getExtraData() {
		if (_extraData == null) {
			return "";
		}
		else {
			return _extraData;
		}
	}

	@Override
	public void setExtraData(String extraData) {
		_extraData = extraData;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			0, PostconditionRelation.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public PostconditionRelation toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = _escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		PostconditionRelationImpl postconditionRelationImpl =
			new PostconditionRelationImpl();

		postconditionRelationImpl.setUuid(getUuid());
		postconditionRelationImpl.setPostconditionRelationId(
			getPostconditionRelationId());
		postconditionRelationImpl.setClassNamePostconditionId(
			getClassNamePostconditionId());
		postconditionRelationImpl.setClassNameId(getClassNameId());
		postconditionRelationImpl.setClassPK(getClassPK());
		postconditionRelationImpl.setExtraData(getExtraData());

		postconditionRelationImpl.resetOriginalValues();

		return postconditionRelationImpl;
	}

	@Override
	public int compareTo(PostconditionRelation postconditionRelation) {
		long primaryKey = postconditionRelation.getPrimaryKey();

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

		if (!(obj instanceof PostconditionRelation)) {
			return false;
		}

		PostconditionRelation postconditionRelation =
			(PostconditionRelation)obj;

		long primaryKey = postconditionRelation.getPrimaryKey();

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
		PostconditionRelationModelImpl postconditionRelationModelImpl = this;

		postconditionRelationModelImpl._originalUuid =
			postconditionRelationModelImpl._uuid;

		postconditionRelationModelImpl._originalClassNamePostconditionId =
			postconditionRelationModelImpl._classNamePostconditionId;

		postconditionRelationModelImpl._setOriginalClassNamePostconditionId =
			false;

		postconditionRelationModelImpl._originalClassNameId =
			postconditionRelationModelImpl._classNameId;

		postconditionRelationModelImpl._setOriginalClassNameId = false;

		postconditionRelationModelImpl._originalClassPK =
			postconditionRelationModelImpl._classPK;

		postconditionRelationModelImpl._setOriginalClassPK = false;

		postconditionRelationModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<PostconditionRelation> toCacheModel() {
		PostconditionRelationCacheModel postconditionRelationCacheModel =
			new PostconditionRelationCacheModel();

		postconditionRelationCacheModel.uuid = getUuid();

		String uuid = postconditionRelationCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			postconditionRelationCacheModel.uuid = null;
		}

		postconditionRelationCacheModel.postconditionRelationId =
			getPostconditionRelationId();

		postconditionRelationCacheModel.classNamePostconditionId =
			getClassNamePostconditionId();

		postconditionRelationCacheModel.classNameId = getClassNameId();

		postconditionRelationCacheModel.classPK = getClassPK();

		postconditionRelationCacheModel.extraData = getExtraData();

		String extraData = postconditionRelationCacheModel.extraData;

		if ((extraData != null) && (extraData.length() == 0)) {
			postconditionRelationCacheModel.extraData = null;
		}

		return postconditionRelationCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<PostconditionRelation, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<PostconditionRelation, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<PostconditionRelation, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(
				attributeGetterFunction.apply((PostconditionRelation)this));
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
		Map<String, Function<PostconditionRelation, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<PostconditionRelation, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<PostconditionRelation, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(
				attributeGetterFunction.apply((PostconditionRelation)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static final Function<InvocationHandler, PostconditionRelation>
		_escapedModelProxyProviderFunction = _getProxyProviderFunction();
	private static boolean _entityCacheEnabled;
	private static boolean _finderCacheEnabled;

	private String _uuid;
	private String _originalUuid;
	private long _postconditionRelationId;
	private long _classNamePostconditionId;
	private long _originalClassNamePostconditionId;
	private boolean _setOriginalClassNamePostconditionId;
	private long _classNameId;
	private long _originalClassNameId;
	private boolean _setOriginalClassNameId;
	private long _classPK;
	private long _originalClassPK;
	private boolean _setOriginalClassPK;
	private String _extraData;
	private long _columnBitmask;
	private PostconditionRelation _escapedModel;

}