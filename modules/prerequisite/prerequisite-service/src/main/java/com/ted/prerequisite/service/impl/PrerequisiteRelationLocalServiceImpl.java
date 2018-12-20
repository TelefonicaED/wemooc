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

package com.ted.prerequisite.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.ted.prerequisite.model.Prerequisite;
import com.ted.prerequisite.model.PrerequisiteFactory;
import com.ted.prerequisite.model.PrerequisiteRelation;
import com.ted.prerequisite.registry.PrerequisiteFactoryRegistryUtil;
import com.ted.prerequisite.service.base.PrerequisiteRelationLocalServiceBaseImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * The implementation of the prerequisite relation local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ted.prerequisite.service.PrerequisiteRelationLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PrerequisiteRelationLocalServiceBaseImpl
 * @see com.ted.prerequisite.service.PrerequisiteRelationLocalServiceUtil
 */
public class PrerequisiteRelationLocalServiceImpl
	extends PrerequisiteRelationLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.ted.prerequisite.service.PrerequisiteRelationLocalServiceUtil} to access the prerequisite relation local service.
	 */
	
	public PrerequisiteRelation addPrerequisiteRelation(long classNamePrerequisiteId, long classNameId, long classPK) {
		PrerequisiteRelation prerequisiteRelation = prerequisiteRelationPersistence.create(counterLocalService.increment(PrerequisiteRelation.class.getName()));
		
		prerequisiteRelation.setClassNamePrerequisiteId(classNamePrerequisiteId);
		prerequisiteRelation.setClassNameId(classNameId);
		prerequisiteRelation.setClassPK(classPK);
		prerequisiteRelation.setExtraData("");
		
		return prerequisiteRelationPersistence.update(prerequisiteRelation);
	}
	
	public List<Prerequisite> getPrerequisites(long classNameId, long classPK){
		List<Prerequisite> listPrerequisites = new ArrayList<Prerequisite>();
		
		List<PrerequisiteRelation> listPrerequisiteRelation = prerequisiteRelationPersistence.findByClassNameIdClassPK(classNameId, classPK);
		
		PrerequisiteFactory prerequisiteFactory = null;
		for(PrerequisiteRelation prerequisiteRelation: listPrerequisiteRelation) {
			prerequisiteFactory = PrerequisiteFactoryRegistryUtil.getPrerequisiteFactoryByClassNameId(prerequisiteRelation.getClassNamePrerequisiteId());
			if(prerequisiteFactory != null) {
				try {
					listPrerequisites.add(prerequisiteFactory.getPrerequisite(prerequisiteRelation));
				} catch (PortalException e) {
					e.printStackTrace();
				}
			}
		}
		
		return listPrerequisites;
	}
	
	public PrerequisiteRelation getPrerequisiteRelation(long classNamePrerequisiteId, long classNameId, long classPK) {
		return prerequisiteRelationPersistence.fetchByClassNamePrerequisiteIdClassNameIdClassPK(classNamePrerequisiteId, classNameId, classPK);
	}
}