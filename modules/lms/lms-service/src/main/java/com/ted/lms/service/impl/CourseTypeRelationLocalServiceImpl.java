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

package com.ted.lms.service.impl;

import com.liferay.portal.aop.AopService;

import com.ted.lms.service.base.CourseTypeRelationLocalServiceBaseImpl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.LayoutSetPrototype;
import com.liferay.portal.kernel.util.PortalUtil;
import com.ted.lms.model.CalificationTypeFactory;
import com.ted.lms.model.CourseEvalFactory;
import com.ted.lms.model.CourseTypeRelation;
import com.ted.lms.model.InscriptionTypeFactory;
import com.ted.lms.model.LearningActivityTypeFactory;
import com.ted.lms.registry.CalificationTypeFactoryRegistryUtil;
import com.ted.lms.registry.CourseEvalFactoryRegistryUtil;
import com.ted.lms.registry.InscriptionTypeFactoryRegistryUtil;
import com.ted.lms.registry.LearningActivityTypeFactoryRegistryUtil;
import com.ted.lms.service.base.CourseTypeRelationLocalServiceBaseImpl;
import com.ted.lms.service.persistence.CourseTypeRelationPK;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * The implementation of the course type relation local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.ted.lms.service.CourseTypeRelationLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CourseTypeRelationLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=com.ted.lms.model.CourseTypeRelation",
	service = AopService.class
)
public class CourseTypeRelationLocalServiceImpl
	extends CourseTypeRelationLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>com.ted.lms.service.CourseTypeRelationLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.ted.lms.service.CourseTypeRelationLocalServiceUtil</code>.
	 */
	
	public void addCourseTypeRelation(long courseTypeId, long classNameId, long[] classPKs) {
		CourseTypeRelationPK courseTypeRelationPK = null;
		CourseTypeRelation courseTypeRelation = null;
		for(long classPK: classPKs) {
			courseTypeRelationPK = new CourseTypeRelationPK(courseTypeId, classNameId, classPK);
			if(courseTypeRelationPersistence.fetchByPrimaryKey(courseTypeRelationPK) == null) {
				courseTypeRelation = courseTypeRelationPersistence.create(courseTypeRelationPK);
				courseTypeRelationPersistence.update(courseTypeRelation);
			}
		}
	}
	
	public List<Long> getTemplateIds(long courseTypeId) {
		List<CourseTypeRelation> templates = courseTypeRelationPersistence.findByCourseTypeIdClassNameId(courseTypeId, PortalUtil.getClassNameId(LayoutSetPrototype.class));
		List<Long> templateIds = new ArrayList<>();
		templates.forEach(template -> templateIds.add(template.getClassPK()));
		return templateIds;
	}
	
	public List<Long> getCourseEvalIds(long courseTypeId) {
		List<CourseTypeRelation> courseEvals = courseTypeRelationPersistence.findByCourseTypeIdClassNameId(courseTypeId, PortalUtil.getClassNameId(CourseEvalFactory.class));
		List<Long> courseEvalIds = new ArrayList<>();
		courseEvals.forEach(courseEval -> courseEvalIds.add(courseEval.getClassPK()));
		return courseEvalIds;
	}
	
	public List<Long> getLearningActivityTypeIds(long courseTypeId) {
		List<CourseTypeRelation> activityTypes = courseTypeRelationPersistence.findByCourseTypeIdClassNameId(courseTypeId, PortalUtil.getClassNameId(LearningActivityTypeFactory.class));
		List<Long> activityTypeIds = new ArrayList<>();
		activityTypes.forEach(activityType -> activityTypeIds.add(activityType.getClassPK()));
		return activityTypeIds;
	}
	
	public List<Long> getInscriptionTypeIds(long courseTypeId) {
		List<CourseTypeRelation> inscriptionTypes = courseTypeRelationPersistence.findByCourseTypeIdClassNameId(courseTypeId, PortalUtil.getClassNameId(InscriptionTypeFactory.class));
		List<Long> inscriptionTypeIds = new ArrayList<>();
		inscriptionTypes.forEach(inscriptionType -> inscriptionTypeIds.add(inscriptionType.getClassPK()));
		return inscriptionTypeIds;
	}
	
	public List<Long> getCalificationTypeIds(long courseTypeId) {
		List<CourseTypeRelation> calificationTypes = courseTypeRelationPersistence.findByCourseTypeIdClassNameId(courseTypeId, PortalUtil.getClassNameId(CalificationTypeFactory.class));
		List<Long> calificationTypeIds = new ArrayList<>();
		calificationTypes.forEach(calificationType -> calificationTypeIds.add(calificationType.getClassPK()));
		return calificationTypeIds;
	}
	
	public List<LayoutSetPrototype> getTemplates(long courseTypeId) {
		List<CourseTypeRelation> templatesRelation = courseTypeRelationPersistence.findByCourseTypeIdClassNameId(courseTypeId, PortalUtil.getClassNameId(LayoutSetPrototype.class));
		List<LayoutSetPrototype> templates = new ArrayList<LayoutSetPrototype>();
		templatesRelation.forEach(template -> {
			try {
				templates.add(layoutSetPrototypeLocalService.getLayoutSetPrototype(template.getClassPK()));
			} catch (PortalException e) {
				e.printStackTrace();
			}
		});
		return templates;
	}
	
	public List<CourseEvalFactory> getCourseEvals(long courseTypeId) {
		List<CourseTypeRelation> courseEvals = courseTypeRelationPersistence.findByCourseTypeIdClassNameId(courseTypeId, PortalUtil.getClassNameId(CourseEvalFactory.class));
		List<CourseEvalFactory> courseEvalIds = new ArrayList<CourseEvalFactory>();
		courseEvals.forEach(courseEval -> courseEvalIds.add(CourseEvalFactoryRegistryUtil.getCourseEvalFactoryByType(courseEval.getClassPK())));
		return courseEvalIds;
	}
	
	public List<LearningActivityTypeFactory> getLearningActivityTypes(long courseTypeId) {
		List<CourseTypeRelation> activityTypes = courseTypeRelationPersistence.findByCourseTypeIdClassNameId(courseTypeId, PortalUtil.getClassNameId(LearningActivityTypeFactory.class));
		List<LearningActivityTypeFactory> activityTypeIds = new ArrayList<LearningActivityTypeFactory>();
		activityTypes.forEach(activityType -> activityTypeIds.add(LearningActivityTypeFactoryRegistryUtil.getLearningActivityTypeFactoryByType(activityType.getClassPK())));
		return activityTypeIds;
	}
	
	public List<InscriptionTypeFactory> getInscriptionTypes(long courseTypeId) {
		List<CourseTypeRelation> inscriptionTypes = courseTypeRelationPersistence.findByCourseTypeIdClassNameId(courseTypeId, PortalUtil.getClassNameId(InscriptionTypeFactory.class));
		List<InscriptionTypeFactory> inscriptionTypeIds = new ArrayList<InscriptionTypeFactory>();
		inscriptionTypes.forEach(inscriptionType -> inscriptionTypeIds.add(InscriptionTypeFactoryRegistryUtil.getInscriptionTypeFactoryByType(inscriptionType.getClassPK())));
		return inscriptionTypeIds;
	}
	
	public List<CalificationTypeFactory> getCalificationTypes(long courseTypeId) {
		List<CourseTypeRelation> calificationTypes = courseTypeRelationPersistence.findByCourseTypeIdClassNameId(courseTypeId, PortalUtil.getClassNameId(CalificationTypeFactory.class));
		List<CalificationTypeFactory> calificationTypeIds = new ArrayList<CalificationTypeFactory>();
		calificationTypes.forEach(calificationType -> calificationTypeIds.add(CalificationTypeFactoryRegistryUtil.getCalificationTypeFactoryByType(calificationType.getClassPK())));
		return calificationTypeIds;
	}
}