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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.ted.postcondition.model.Postcondition;
import com.ted.postcondition.model.PostconditionFactory;
import com.ted.postcondition.registry.PostconditionFactoryRegistryUtil;

import aQute.bnd.annotation.ProviderType;

/**
 * The extended model implementation for the PostconditionRelation service. Represents a row in the &quot;post_PostconditionRelation&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ted.postcondition.model.PostconditionRelation} interface.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class PostconditionRelationImpl extends PostconditionRelationBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a postcondition relation model instance should use the {@link com.ted.postcondition.model.PostconditionRelation} interface instead.
	 */
	public PostconditionRelationImpl() {
	}
	
	private JSONObject extraData = null;
	private PostconditionFactory postconditionFactory = null;
	private Postcondition postcondition = null;

	public PostconditionFactory getPostconditionFactory() {
		if(postconditionFactory == null) {
			postconditionFactory = PostconditionFactoryRegistryUtil.getPostconditionFactoryByClassNameId(getClassNamePostconditionId());
		}
		
		return postconditionFactory;
	}
	
	public Postcondition getPostcondition() {
		if(postcondition == null) {
			 PostconditionFactory postconditionFactory = getPostconditionFactory();
			 try {
				postcondition = postconditionFactory.getPostcondition(this);
			} catch (PortalException e) {
				e.printStackTrace();
			}
		}
		
		return postcondition;
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