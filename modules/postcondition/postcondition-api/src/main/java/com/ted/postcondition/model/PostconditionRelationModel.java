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

package com.ted.postcondition.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.AttachedModel;
import com.liferay.portal.kernel.model.BaseModel;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the PostconditionRelation service. Represents a row in the &quot;post_PostconditionRelation&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.ted.postcondition.model.impl.PostconditionRelationModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.ted.postcondition.model.impl.PostconditionRelationImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PostconditionRelation
 * @generated
 */
@ProviderType
public interface PostconditionRelationModel
	extends AttachedModel, BaseModel<PostconditionRelation> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a postcondition relation model instance should use the {@link PostconditionRelation} interface instead.
	 */

	/**
	 * Returns the primary key of this postcondition relation.
	 *
	 * @return the primary key of this postcondition relation
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this postcondition relation.
	 *
	 * @param primaryKey the primary key of this postcondition relation
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this postcondition relation.
	 *
	 * @return the uuid of this postcondition relation
	 */
	@AutoEscape
	public String getUuid();

	/**
	 * Sets the uuid of this postcondition relation.
	 *
	 * @param uuid the uuid of this postcondition relation
	 */
	public void setUuid(String uuid);

	/**
	 * Returns the postcondition relation ID of this postcondition relation.
	 *
	 * @return the postcondition relation ID of this postcondition relation
	 */
	public long getPostconditionRelationId();

	/**
	 * Sets the postcondition relation ID of this postcondition relation.
	 *
	 * @param postconditionRelationId the postcondition relation ID of this postcondition relation
	 */
	public void setPostconditionRelationId(long postconditionRelationId);

	/**
	 * Returns the class name postcondition ID of this postcondition relation.
	 *
	 * @return the class name postcondition ID of this postcondition relation
	 */
	public long getClassNamePostconditionId();

	/**
	 * Sets the class name postcondition ID of this postcondition relation.
	 *
	 * @param classNamePostconditionId the class name postcondition ID of this postcondition relation
	 */
	public void setClassNamePostconditionId(long classNamePostconditionId);

	/**
	 * Returns the fully qualified class name of this postcondition relation.
	 *
	 * @return the fully qualified class name of this postcondition relation
	 */
	@Override
	public String getClassName();

	public void setClassName(String className);

	/**
	 * Returns the class name ID of this postcondition relation.
	 *
	 * @return the class name ID of this postcondition relation
	 */
	@Override
	public long getClassNameId();

	/**
	 * Sets the class name ID of this postcondition relation.
	 *
	 * @param classNameId the class name ID of this postcondition relation
	 */
	@Override
	public void setClassNameId(long classNameId);

	/**
	 * Returns the class pk of this postcondition relation.
	 *
	 * @return the class pk of this postcondition relation
	 */
	@Override
	public long getClassPK();

	/**
	 * Sets the class pk of this postcondition relation.
	 *
	 * @param classPK the class pk of this postcondition relation
	 */
	@Override
	public void setClassPK(long classPK);

	/**
	 * Returns the extra data of this postcondition relation.
	 *
	 * @return the extra data of this postcondition relation
	 */
	@AutoEscape
	public String getExtraData();

	/**
	 * Sets the extra data of this postcondition relation.
	 *
	 * @param extraData the extra data of this postcondition relation
	 */
	public void setExtraData(String extraData);

}