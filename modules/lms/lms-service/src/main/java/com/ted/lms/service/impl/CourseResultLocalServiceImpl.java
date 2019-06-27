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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;
import com.ted.lms.exception.NoSuchCourseResultException;
import com.ted.lms.model.Course;
import com.ted.lms.model.CourseEval;
import com.ted.lms.model.CourseEvalFactory;
import com.ted.lms.model.CourseResult;
import com.ted.lms.model.Module;
import com.ted.lms.model.ModuleResult;
import com.ted.lms.model.Postcondition;
import com.ted.lms.registry.CourseEvalFactoryRegistryUtil;
import com.ted.lms.registry.PostconditionRegistryUtil;
import com.ted.lms.service.base.CourseResultLocalServiceBaseImpl;
import java.util.Date;
import java.util.List;

/**
 * The implementation of the course result local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ted.lms.service.CourseResultLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CourseResultLocalServiceBaseImpl
 * @see com.ted.lms.service.CourseResultLocalServiceUtil
 */
public class CourseResultLocalServiceImpl
	extends CourseResultLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.ted.lms.service.CourseResultLocalServiceUtil} to access the course result local service.
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
			Course course = courseLocalService.getCourse(courseId);
			
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
			List<Postcondition> listPostcondition = PostconditionRegistryUtil.getPostconditions(courseResult.getCompanyId());
			for(Postcondition postcondition: listPostcondition) {
				if(courseResult.isPassed()) {
					postcondition.passedCourseResult(courseResult);
				}else {
					postcondition.failedCourseResult(courseResult);
				}
			}
		}
		return super.updateCourseResult(courseResult);
	}
	
	public CourseResult updateCourseResult(ModuleResult moduleResult, ServiceContext serviceContext) {
		
		CourseResult courseResult = null;
		
		Module module = modulePersistence.fetchByPrimaryKey(moduleResult.getModuleId());
		Course course=coursePersistence.fetchByGroupCreatedId(module.getGroupId());
		
		if(course!=null){
			
			courseResult=courseResultLocalService.fetchCourseResult(course.getCourseId(), moduleResult.getUserId());
			if(courseResult==null) {
				courseResult=courseResultLocalService.addCourseResult(moduleResult.getUserModifiedId(), course.getCourseId(), moduleResult.getUserId());
			}
			
			CourseEvalFactory courseEvalFactory = CourseEvalFactoryRegistryUtil.getCourseEvalFactoryByType(course.getCourseEvalId());
			try {
				CourseEval courseEval = courseEvalFactory.getCourseEval(course);
				courseResult = courseEval.updateCourseResult(courseResult);
			} catch (PortalException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			courseResult = courseResultLocalService.updateCourseResult(courseResult);
		}
		
		return courseResult;
	}
	
	public boolean hasUserTries(long courseId, long userId) {
		return courseResultFinder.hasUserTries(courseId, userId);
	}

}