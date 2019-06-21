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

package com.ted.lms.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for Student. This utility wraps
 * {@link com.ted.lms.service.impl.StudentLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see StudentLocalService
 * @see com.ted.lms.service.base.StudentLocalServiceBaseImpl
 * @see com.ted.lms.service.impl.StudentLocalServiceImpl
 * @generated
 */
@ProviderType
public class StudentLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.ted.lms.service.impl.StudentLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static boolean canEnroll(com.ted.lms.model.Course course,
		long userId, java.util.Locale locale,
		com.liferay.portal.kernel.security.permission.PermissionChecker permissionChecker)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.ted.lms.exception.InscriptionException {
		return getService().canEnroll(course, userId, locale, permissionChecker);
	}

	public static boolean canUnsubscribe(com.ted.lms.model.Course course,
		long userId,
		com.liferay.portal.kernel.security.permission.PermissionChecker permissionChecker)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().canUnsubscribe(course, userId, permissionChecker);
	}

	public static int countStudentsFinished(long courseId, long companyId,
		String keywords, int status,
		java.util.LinkedHashMap<String, Object> params, int start, int end) {
		return getService()
				   .countStudentsFinished(courseId, companyId, keywords,
			status, params, start, end);
	}

	public static int countStudentsFromCourse(long courseId, long companyId) {
		return getService().countStudentsFromCourse(courseId, companyId);
	}

	public static int countStudentsFromCourse(long courseId, long companyId,
		String keywords, int status,
		java.util.LinkedHashMap<String, Object> params) {
		return getService()
				   .countStudentsFromCourse(courseId, companyId, keywords,
			status, params);
	}

	/**
	* Usar este método para contar los estudiantes de un curso
	*
	* @param courseId id del curso
	* @param companyId id de company
	* @param screenName nombre de usuario
	* @param firstName nombre
	* @param lastName apellido
	* @param emailAddress direccion de correo
	* @param status estado del usuario (WorkflowConstants)
	* @param andOperator true si queremos que coincidan screenname, firstname, lastname y emailaddress, false en caso contrario
	* @return
	*/
	public static int countStudentsFromCourse(long courseId, long companyId,
		String screenName, String firstName, String lastName,
		String emailAddress, int status,
		java.util.LinkedHashMap<String, Object> params, boolean andOperator) {
		return getService()
				   .countStudentsFromCourse(courseId, companyId, screenName,
			firstName, lastName, emailAddress, status, params, andOperator);
	}

	/**
	* Este método realiza las comprobaciones necesarias en base al método de inscripción seleccionado en el curso e inscribe al usuario,
	* se debe llamar cuando es el mismo estudiante el que se está inscribiendo en el curso
	*/
	public static com.ted.lms.model.CourseResult enrollStudent(
		com.ted.lms.model.Course course, long studentId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext,
		com.liferay.portal.kernel.security.permission.PermissionChecker permissionChecker)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.ted.lms.exception.InscriptionException {
		return getService()
				   .enrollStudent(course, studentId, serviceContext,
			permissionChecker);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static java.util.List<com.liferay.portal.kernel.model.User> getStudentsFromCourse(
		long courseId, long companyId, String keywords, int status,
		java.util.LinkedHashMap<String, Object> params, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc) {
		return getService()
				   .getStudentsFromCourse(courseId, companyId, keywords,
			status, params, start, end, obc);
	}

	public static java.util.List<com.liferay.portal.kernel.model.User> getStudentsFromCourse(
		long courseId, long companyId, String screenName, String firstName,
		String lastName, String emailAddress, int status,
		java.util.LinkedHashMap<String, Object> params, boolean andOperator,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc) {
		return getService()
				   .getStudentsFromCourse(courseId, companyId, screenName,
			firstName, lastName, emailAddress, status, params, andOperator,
			start, end, obc);
	}

	/**
	* Este método se usa para desinscribir a un usuario de un curso teniendo en cuenta el método de inscripción
	*/
	public static boolean unsubscribeStudent(com.ted.lms.model.Course course,
		long userId,
		com.liferay.portal.kernel.security.permission.PermissionChecker permissionChecker)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().unsubscribeStudent(course, userId, permissionChecker);
	}

	public static StudentLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<StudentLocalService, StudentLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(StudentLocalService.class);

		ServiceTracker<StudentLocalService, StudentLocalService> serviceTracker = new ServiceTracker<StudentLocalService, StudentLocalService>(bundle.getBundleContext(),
				StudentLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}