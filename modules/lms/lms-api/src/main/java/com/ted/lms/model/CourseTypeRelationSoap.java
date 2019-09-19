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

import com.ted.lms.service.persistence.CourseTypeRelationPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class CourseTypeRelationSoap implements Serializable {

	public static CourseTypeRelationSoap toSoapModel(CourseTypeRelation model) {
		CourseTypeRelationSoap soapModel = new CourseTypeRelationSoap();

		soapModel.setCourseTypeId(model.getCourseTypeId());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());

		return soapModel;
	}

	public static CourseTypeRelationSoap[] toSoapModels(
		CourseTypeRelation[] models) {

		CourseTypeRelationSoap[] soapModels =
			new CourseTypeRelationSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CourseTypeRelationSoap[][] toSoapModels(
		CourseTypeRelation[][] models) {

		CourseTypeRelationSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new CourseTypeRelationSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CourseTypeRelationSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CourseTypeRelationSoap[] toSoapModels(
		List<CourseTypeRelation> models) {

		List<CourseTypeRelationSoap> soapModels =
			new ArrayList<CourseTypeRelationSoap>(models.size());

		for (CourseTypeRelation model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new CourseTypeRelationSoap[soapModels.size()]);
	}

	public CourseTypeRelationSoap() {
	}

	public CourseTypeRelationPK getPrimaryKey() {
		return new CourseTypeRelationPK(_courseTypeId, _classNameId, _classPK);
	}

	public void setPrimaryKey(CourseTypeRelationPK pk) {
		setCourseTypeId(pk.courseTypeId);
		setClassNameId(pk.classNameId);
		setClassPK(pk.classPK);
	}

	public long getCourseTypeId() {
		return _courseTypeId;
	}

	public void setCourseTypeId(long courseTypeId) {
		_courseTypeId = courseTypeId;
	}

	public long getClassNameId() {
		return _classNameId;
	}

	public void setClassNameId(long classNameId) {
		_classNameId = classNameId;
	}

	public long getClassPK() {
		return _classPK;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;
	}

	private long _courseTypeId;
	private long _classNameId;
	private long _classPK;

}