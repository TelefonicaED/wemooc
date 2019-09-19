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

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.ted.prerequisite.model.Prerequisite;
import com.ted.prerequisite.model.PrerequisiteFactory;
import com.ted.prerequisite.model.PrerequisiteRelation;
import com.ted.prerequisite.registry.PrerequisiteFactoryRegistryUtil;
import com.ted.prerequisite.service.base.PrerequisiteRelationLocalServiceBaseImpl;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * The implementation of the prerequisite relation local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.ted.prerequisite.service.PrerequisiteRelationLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PrerequisiteRelationLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=com.ted.prerequisite.model.PrerequisiteRelation",
	service = AopService.class
)
public class PrerequisiteRelationLocalServiceImpl
	extends PrerequisiteRelationLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>com.ted.prerequisite.service.PrerequisiteRelationLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.ted.prerequisite.service.PrerequisiteRelationLocalServiceUtil</code>.
	 */
	public PrerequisiteRelation addPrerequisiteRelation(long classNamePrerequisiteId, long classNameId, long classPK, String extraData) {
		PrerequisiteRelation prerequisiteRelation = prerequisiteRelationPersistence.create(counterLocalService.increment(PrerequisiteRelation.class.getName()));
		
		prerequisiteRelation.setClassNamePrerequisiteId(classNamePrerequisiteId);
		prerequisiteRelation.setClassNameId(classNameId);
		prerequisiteRelation.setClassPK(classPK);
		prerequisiteRelation.setExtraData(extraData);
		
		return prerequisiteRelationPersistence.update(prerequisiteRelation);
	}
	
	public List<Prerequisite> getPrerequisites(long classNameId, long classPK){
		List<Prerequisite> listPrerequisites = new ArrayList<Prerequisite>();
			
		System.out.println("prerequisiteRelationPersistence: " + prerequisiteRelationPersistence);
		
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
	
	public List<Prerequisite> getPrerequisites(long classNameId, long classPK, int start, int end){
		List<Prerequisite> listPrerequisites = new ArrayList<Prerequisite>();
		
		List<PrerequisiteRelation> listPrerequisiteRelation = prerequisiteRelationPersistence.findByClassNameIdClassPK(classNameId, classPK, start, end);
		
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
	
	public int getPrerequisiteRelationsCount(long classNameId, long classPK) {
		return prerequisiteRelationPersistence.countByClassNameIdClassPK(classNameId, classPK);
	}
	
	public List<PrerequisiteRelation> getPrerequisiteRelations(long classNameId, long classPK){
		return prerequisiteRelationPersistence.findByClassNameIdClassPK(classNameId, classPK);
	}
	
	public List<PrerequisiteRelation> getPrerequisiteRelations(long classNameId, long classPK, int start, int end){
		return prerequisiteRelationPersistence.findByClassNameIdClassPK(classNameId, classPK, start, end);
	}
	
	public List<PrerequisiteRelation> getPrerequisiteRelation(long classNamePrerequisiteId, long classNameId, long classPK) {
		return prerequisiteRelationPersistence.findByClassNamePrerequisiteIdClassNameIdClassPK(classNamePrerequisiteId, classNameId, classPK);
	}
}