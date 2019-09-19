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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.ted.lms.exception.InscriptionException;
import com.ted.lms.model.Course;
import com.ted.lms.model.CourseResult;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the local service interface for Student. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see StudentLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface StudentLocalService extends BaseLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link StudentLocalServiceUtil} to access the student local service. Add custom service methods to <code>com.ted.lms.service.impl.StudentLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public boolean canEnroll(
			Course course, long userId, Locale locale,
			PermissionChecker permissionChecker)
		throws InscriptionException, PortalException;

	public boolean canUnsubscribe(
			Course course, long userId, PermissionChecker permissionChecker)
		throws PortalException;

	public int countStudentsFinished(
		long courseId, long companyId, String keywords, int status,
		LinkedHashMap<String, Object> params, int start, int end);

	public int countStudentsFromCourse(long courseId, long companyId);

	public int countStudentsFromCourse(
		long courseId, long companyId, String keywords, int status,
		LinkedHashMap<String, Object> params);

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
	public int countStudentsFromCourse(
		long courseId, long companyId, String screenName, String firstName,
		String lastName, String emailAddress, int status,
		LinkedHashMap<String, Object> params, boolean andOperator);

	/**
	 * Este método realiza las comprobaciones necesarias en base al método de inscripción seleccionado en el curso e inscribe al usuario,
	 * se debe llamar cuando es el mismo estudiante el que se está inscribiendo en el curso
	 */
	public CourseResult enrollStudent(
			Course course, long studentId, ServiceContext serviceContext,
			PermissionChecker permissionChecker)
		throws InscriptionException, PortalException;

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<User> getStudentsFromCourse(
		long courseId, long companyId, String keywords, int status,
		LinkedHashMap<String, Object> params, int start, int end,
		OrderByComparator obc);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<User> getStudentsFromCourse(
		long courseId, long companyId, String screenName, String firstName,
		String lastName, String emailAddress, int status,
		LinkedHashMap<String, Object> params, boolean andOperator, int start,
		int end, OrderByComparator obc);

	/**
	 * Este método se usa para desinscribir a un usuario de un curso teniendo en cuenta el método de inscripción
	 */
	public boolean unsubscribeStudent(
			Course course, long userId, PermissionChecker permissionChecker)
		throws PortalException;

}