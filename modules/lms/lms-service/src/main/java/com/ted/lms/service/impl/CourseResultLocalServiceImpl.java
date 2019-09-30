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
import com.ted.lms.service.base.CourseResultLocalServiceBaseImpl;
import com.ted.postcondition.model.Postcondition;
import com.ted.postcondition.service.PostconditionRelationLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.ted.audit.api.AuditFactory;
import com.ted.lms.constants.LMSAuditConstants;
import com.ted.lms.exception.NoSuchCourseResultException;
import com.ted.lms.model.Course;
import com.ted.lms.model.CourseEval;
import com.ted.lms.model.CourseEvalFactory;
import com.ted.lms.model.CourseResult;
import com.ted.lms.model.Module;
import com.ted.lms.model.ModuleResult;
import com.ted.lms.registry.CourseEvalFactoryRegistryUtil;
import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * The implementation of the course result local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.ted.lms.service.CourseResultLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CourseResultLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=com.ted.lms.model.CourseResult",
	service = AopService.class
)
public class CourseResultLocalServiceImpl
	extends CourseResultLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>com.ted.lms.service.CourseResultLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.ted.lms.service.CourseResultLocalServiceUtil</code>.
	 */
	
	public List<CourseResult> getCourseResults(long courseId){
		
		return courseResultPersistence.findByCourseId(courseId);
		
	}
	
	public CourseResult fetchCourseResult(long courseId, long userId) {
		return courseResultPersistence.fetchByCourseIdUserId(courseId, userId);
	}
	
	public CourseResult getCourseResult(long courseId, long userId) throws NoSuchCourseResultException {
		return courseResultPersistence.findByCourseIdUserId(courseId, userId);
	}
	
	/**
	 * Crear un courseresult para el alumno cuando se inscribe
	 * @param userId identificador del usuario que está inscribiendo
	 * @param courseId identificador del curso
	 * @param studentId usuario que se inscribe en el curso
	 * @param serviceContext
	 * @return
	 */
	public CourseResult addCourseResult(long userId, long courseId, long studentId) {
		CourseResult courseResult = null;
		
		try {
			User userModified = userLocalService.getUser(userId);
			Course course = coursePersistence.fetchByPrimaryKey(courseId);
			
			courseResult = courseResultPersistence.create(counterLocalService.increment(CourseResult.class.getName()));
			courseResult.setCourseId(courseId);
			courseResult.setUserId(studentId);
			courseResult.setResult(0);
			courseResult.setRegistrationDate(new Date());
			courseResult.setPassed(false);
			courseResult.setGroupId(course.getGroupCreatedId());
			courseResult.setCompanyId(course.getCompanyId());
			courseResult.setUserModifiedId(userId);
			courseResult.setUserModifiedName(userModified.getFullName());
			
			courseResultPersistence.update(courseResult);
			
			AuditFactory.audit(course.getCompanyId(), course.getGroupId(), LMSAuditConstants.COURSE_RESULT_ADD, CourseResult.class.getName(), courseResult.getCrId(), 
					userId, userModified.getFullName(), null);
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return courseResult;
	}
	
	public List<CourseResult> getCourseResults(long courseId, boolean passed){
		return courseResultPersistence.findByCourseIdPassed(courseId, passed);
	}
	
	public List<CourseResult> getCourseResultsNotFinished(long courseId){
		
		return courseResultFinder.getCourseResultNotFinished(courseId);
	}
	
	@Override
	public CourseResult updateCourseResult(CourseResult courseResult) {
		//Si ha finalizado el curso llamamos a las postcondiciones
		if(Validator.isNotNull(courseResult.getPassedDate())) {
			List<Postcondition> postconditions = postconditionRelationLocalService.getPostconditions(PortalUtil.getClassNameId(Course.class), courseResult.getCourseId());
			for(Postcondition postcondition: postconditions) {
				postcondition.completed(courseResult.getUserId());
				if(courseResult.isPassed()) {
					postcondition.passed(courseResult.getUserId());
				}else {
					postcondition.failed(courseResult.getUserId());
				}
			}
		}
		
		return super.updateCourseResult(courseResult);
	}
	
	public CourseResult updateCourseResult(ModuleResult moduleResult) throws PortalException {
		
		CourseResult courseResult = null;
		
		Module module = modulePersistence.fetchByPrimaryKey(moduleResult.getModuleId());
		Course course=coursePersistence.fetchByGroupCreatedId(module.getGroupId());
		
		if(course!=null){
			
			courseResult=courseResultLocalService.fetchCourseResult(course.getCourseId(), moduleResult.getUserId());
			
			if(courseResult==null) {
				courseResult=courseResultLocalService.addCourseResult(moduleResult.getUserModifiedId(), course.getCourseId(), moduleResult.getUserId());
			}
			
			CourseEvalFactory courseEvalFactory = CourseEvalFactoryRegistryUtil.getCourseEvalFactoryByType(course.getCourseEvalId());
			
			CourseEval courseEval = courseEvalFactory.getCourseEval(course);
			courseResult = courseEval.updateCourseResult(courseResult);
			
			courseResult = courseResultLocalService.updateCourseResult(courseResult);
			
			User user = userLocalService.getUser(moduleResult.getUserId());
			
			AuditFactory.audit(course.getCompanyId(), course.getGroupId(), LMSAuditConstants.COURSE_RESULT_UPDATE, CourseResult.class.getName(), courseResult.getCrId(), 
					user.getUserId(), user.getFullName(), null);
		}
		
		return courseResult;
	}
	
	public boolean hasUserTries(long courseId, long userId) {
		return courseResultFinder.hasUserTries(courseId, userId);
	}
	
	public List<CourseResult> getMyCourses(long userId, boolean inProgress, boolean completed, boolean expired, long groupId, int start, int end, 
			OrderByComparator obc){
		return courseResultFinder.filterByU_G(userId, inProgress, completed, expired, groupId, start, end, obc, true);
	}

	public int getMyCoursesCount(long userId, boolean inProgress, boolean completed, boolean expired, long groupId) {
		return courseResultFinder.doCountByU_G(userId, inProgress, completed, expired, groupId, true);
		
	}
	
	public CourseResult recalculate(long userId, CourseResult courseResult) throws PortalException {
		
		Course course = coursePersistence.findByPrimaryKey(courseResult.getCourseId());
		int requiredActivityResults = learningActivityResultFinder.countRequiredLearningActivityResults(course.getGroupCreatedId(), courseResult.getUserId());
		
		Date now = new Date();
		User user = userLocalService.getUser(userId);
		CourseResult courseResultUpdated = null;
		
		if(requiredActivityResults == 0) {
			courseResultUpdated = courseResult;
			courseResultUpdated.setStartDate(null);
			courseResultUpdated.setPassed(false);
			courseResultUpdated.setPassedDate(null);
			courseResultUpdated.setResult(0);
		} else {
			
			CourseEvalFactory courseEvalFactory = CourseEvalFactoryRegistryUtil.getCourseEvalFactoryByType(course.getCourseEvalId());
			CourseEval courseEval = courseEvalFactory.getCourseEval(course);
			courseResultUpdated = courseEval.updateCourseResult(courseResult);
		}
		
		if(courseResultUpdated.getStartDate() == null || courseResult.getResult() != courseResultUpdated.getResult() || courseResult.getPassed() != courseResultUpdated.getPassed()) {
			//Update en la bd

			courseResult.setUserModifiedId(user.getUserId());
			courseResult.setUserModifiedName(user.getFullName());
			courseResult.setModifiedDate(now);
			
			courseResult = courseResultPersistence.update(courseResultUpdated);
		}
			
		return courseResult;
	}
	
	@Reference
	protected PostconditionRelationLocalService postconditionRelationLocalService;
}