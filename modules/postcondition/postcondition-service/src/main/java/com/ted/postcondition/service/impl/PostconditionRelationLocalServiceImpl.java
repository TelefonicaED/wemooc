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

package com.ted.postcondition.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.ted.postcondition.model.Postcondition;
import com.ted.postcondition.model.PostconditionFactory;
import com.ted.postcondition.model.PostconditionRelation;
import com.ted.postcondition.registry.PostconditionFactoryRegistryUtil;
import com.ted.postcondition.service.base.PostconditionRelationLocalServiceBaseImpl;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * The implementation of the postcondition relation local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.ted.postcondition.service.PostconditionRelationLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PostconditionRelationLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=com.ted.postcondition.model.PostconditionRelation",
	service = AopService.class
)
public class PostconditionRelationLocalServiceImpl
	extends PostconditionRelationLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>com.ted.postcondition.service.PostconditionRelationLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.ted.postcondition.service.PostconditionRelationLocalServiceUtil</code>.
	 */
	
	public PostconditionRelation addPostconditionRelation(long classNamePostconditionId, long classNameId, long classPK, String extraData) {
		PostconditionRelation postconditionRelation = postconditionRelationPersistence.create(counterLocalService.increment(PostconditionRelation.class.getName()));
		
		postconditionRelation.setClassNamePostconditionId(classNamePostconditionId);
		postconditionRelation.setClassNameId(classNameId);
		postconditionRelation.setClassPK(classPK);
		postconditionRelation.setExtraData(extraData);
		
		return postconditionRelationPersistence.update(postconditionRelation);
	}
	
	public List<Postcondition> getPostconditions(long classNameId, long classPK){
		List<Postcondition> listPostconditions = new ArrayList<Postcondition>();
		
		List<PostconditionRelation> listPostconditionRelation = postconditionRelationPersistence.findByClassNameIdClassPK(classNameId, classPK);
		
		PostconditionFactory postconditionFactory = null;
		for(PostconditionRelation postconditionRelation: listPostconditionRelation) {
			postconditionFactory = PostconditionFactoryRegistryUtil.getPostconditionFactoryByClassNameId(postconditionRelation.getClassNamePostconditionId());
			if(postconditionFactory != null) {
				try {
					listPostconditions.add(postconditionFactory.getPostcondition(postconditionRelation));
				} catch (PortalException e) {
					e.printStackTrace();
				}
			}
		}
		
		return listPostconditions;
	}
	
	public List<Postcondition> getPostconditions(long classNameId, long classPK, int start, int end){
		List<Postcondition> listPostconditions = new ArrayList<Postcondition>();
		
		List<PostconditionRelation> listPostconditionRelation = postconditionRelationPersistence.findByClassNameIdClassPK(classNameId, classPK, start, end);
		
		PostconditionFactory postconditionFactory = null;
		for(PostconditionRelation postconditionRelation: listPostconditionRelation) {
			postconditionFactory = PostconditionFactoryRegistryUtil.getPostconditionFactoryByClassNameId(postconditionRelation.getClassNamePostconditionId());
			if(postconditionFactory != null) {
				try {
					listPostconditions.add(postconditionFactory.getPostcondition(postconditionRelation));
				} catch (PortalException e) {
					e.printStackTrace();
				}
			}
		}
		
		return listPostconditions;
	}
	
	public int getPostconditionRelationsCount(long classNameId, long classPK) {
		return postconditionRelationPersistence.countByClassNameIdClassPK(classNameId, classPK);
	}
	
	public List<PostconditionRelation> getPostconditionRelations(long classNameId, long classPK){
		return postconditionRelationPersistence.findByClassNameIdClassPK(classNameId, classPK);
	}
	
	public List<PostconditionRelation> getPostconditionRelations(long classNameId, long classPK, int start, int end){
		return postconditionRelationPersistence.findByClassNameIdClassPK(classNameId, classPK, start, end);
	}
	
	public List<PostconditionRelation> getPostconditionRelation(long classNamePostconditionId, long classNameId, long classPK) {
		return postconditionRelationPersistence.findByClassNamePostconditionIdClassNameIdClassPK(classNamePostconditionId, classNameId, classPK);
	}
}