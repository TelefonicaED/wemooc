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

import com.liferay.portal.kernel.service.ServiceWrapper;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides a wrapper for {@link StudentLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see StudentLocalService
 * @generated
 */
@ProviderType
public class StudentLocalServiceWrapper
	implements StudentLocalService, ServiceWrapper<StudentLocalService> {

	public StudentLocalServiceWrapper(StudentLocalService studentLocalService) {
		_studentLocalService = studentLocalService;
	}

	@Override
	public boolean canEnroll(
			com.ted.lms.model.Course course, long userId,
			java.util.Locale locale,
			com.liferay.portal.kernel.security.permission.PermissionChecker
				permissionChecker)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.ted.lms.exception.InscriptionException {

		return _studentLocalService.canEnroll(
			course, userId, locale, permissionChecker);
	}

	@Override
	public boolean canUnsubscribe(
			com.ted.lms.model.Course course, long userId,
			com.liferay.portal.kernel.security.permission.PermissionChecker
				permissionChecker)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _studentLocalService.canUnsubscribe(
			course, userId, permissionChecker);
	}

	@Override
	public int countStudentsFinished(
		long courseId, long companyId, String keywords, int status,
		java.util.LinkedHashMap<String, Object> params, int start, int end) {

		return _studentLocalService.countStudentsFinished(
			courseId, companyId, keywords, status, params, start, end);
	}

	@Override
	public int countStudentsFromCourse(long courseId, long companyId) {
		return _studentLocalService.countStudentsFromCourse(
			courseId, companyId);
	}

	@Override
	public int countStudentsFromCourse(
		long courseId, long companyId, String keywords, int status,
		java.util.LinkedHashMap<String, Object> params) {

		return _studentLocalService.countStudentsFromCourse(
			courseId, companyId, keywords, status, params);
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
	@Override
	public int countStudentsFromCourse(
		long courseId, long companyId, String screenName, String firstName,
		String lastName, String emailAddress, int status,
		java.util.LinkedHashMap<String, Object> params, boolean andOperator) {

		return _studentLocalService.countStudentsFromCourse(
			courseId, companyId, screenName, firstName, lastName, emailAddress,
			status, params, andOperator);
	}

	/**
	 * Este método realiza las comprobaciones necesarias en base al método de inscripción seleccionado en el curso e inscribe al usuario,
	 * se debe llamar cuando es el mismo estudiante el que se está inscribiendo en el curso
	 */
	@Override
	public com.ted.lms.model.CourseResult enrollStudent(
			com.ted.lms.model.Course course, long studentId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext,
			com.liferay.portal.kernel.security.permission.PermissionChecker
				permissionChecker)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.ted.lms.exception.InscriptionException {

		return _studentLocalService.enrollStudent(
			course, studentId, serviceContext, permissionChecker);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _studentLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.User>
		getStudentsFromCourse(
			long courseId, long companyId, String keywords, int status,
			java.util.LinkedHashMap<String, Object> params, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator obc) {

		return _studentLocalService.getStudentsFromCourse(
			courseId, companyId, keywords, status, params, start, end, obc);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.User>
		getStudentsFromCourse(
			long courseId, long companyId, String screenName, String firstName,
			String lastName, String emailAddress, int status,
			java.util.LinkedHashMap<String, Object> params, boolean andOperator,
			int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator obc) {

		return _studentLocalService.getStudentsFromCourse(
			courseId, companyId, screenName, firstName, lastName, emailAddress,
			status, params, andOperator, start, end, obc);
	}

	/**
	 * Este método se usa para desinscribir a un usuario de un curso teniendo en cuenta el método de inscripción
	 */
	@Override
	public boolean unsubscribeStudent(
			com.ted.lms.model.Course course, long userId,
			com.liferay.portal.kernel.security.permission.PermissionChecker
				permissionChecker)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _studentLocalService.unsubscribeStudent(
			course, userId, permissionChecker);
	}

	@Override
	public StudentLocalService getWrappedService() {
		return _studentLocalService;
	}

	@Override
	public void setWrappedService(StudentLocalService studentLocalService) {
		_studentLocalService = studentLocalService;
	}

	private StudentLocalService _studentLocalService;

}