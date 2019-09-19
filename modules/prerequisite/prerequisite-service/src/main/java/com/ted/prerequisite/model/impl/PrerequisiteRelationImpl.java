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

package com.ted.prerequisite.model.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.ted.prerequisite.model.Prerequisite;
import com.ted.prerequisite.model.PrerequisiteFactory;
import com.ted.prerequisite.registry.PrerequisiteFactoryRegistryUtil;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model implementation for the PrerequisiteRelation service. Represents a row in the &quot;pre_PrerequisiteRelation&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.ted.prerequisite.model.PrerequisiteRelation<code> interface.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class PrerequisiteRelationImpl extends PrerequisiteRelationBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a prerequisite relation model instance should use the {@link com.ted.prerequisite.model.PrerequisiteRelation} interface instead.
	 */
	private JSONObject extraData = null;
	private PrerequisiteFactory prerequisiteFactory = null;
	private Prerequisite prerequisite = null;
	
	public PrerequisiteRelationImpl() {
	}
	
	public PrerequisiteFactory getPrerequisiteFactory() {
		if(prerequisiteFactory == null) {
			prerequisiteFactory = PrerequisiteFactoryRegistryUtil.getPrerequisiteFactoryByClassNameId(getClassNamePrerequisiteId());
		}
		
		return prerequisiteFactory;
	}
	
	public Prerequisite getPrerequisite() {
		if(prerequisite == null) {
			 PrerequisiteFactory prerequisiteFactory = getPrerequisiteFactory();
			 try {
				prerequisite = prerequisiteFactory.getPrerequisite(this);
			} catch (PortalException e) {
				e.printStackTrace();
			}
		}
		
		return prerequisite;
	}
	
	@Override
	public JSONObject getExtraDataJSON() {
		if(extraData == null) {
			try {
				extraData = JSONFactoryUtil.createJSONObject(getExtraData());
			} catch (JSONException e) {
				e.printStackTrace();
				extraData = JSONFactoryUtil.createJSONObject();
			}
		}
		return extraData;
	}
	
	@Override
	public void setExtraData(String prerequisiteExtraData) {
		try {
			extraData = JSONFactoryUtil.createJSONObject(prerequisiteExtraData);
		} catch (JSONException e) {
			extraData = JSONFactoryUtil.createJSONObject();
		}
		
		super.setExtraData(prerequisiteExtraData);
	}
}