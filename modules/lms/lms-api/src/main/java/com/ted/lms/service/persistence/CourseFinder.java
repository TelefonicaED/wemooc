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

package com.ted.lms.service.persistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public interface CourseFinder {

	public java.util.List<com.ted.lms.model.Course> findByKeywords(
		long companyId, String freeText, String languageId, int[] status,
		long parentCourseId, long groupId,
		java.util.LinkedHashMap<String, Object> params, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<com.ted.lms.model.Course> obc);

	public java.util.List<com.ted.lms.model.Course> filterByKeywords(
		long companyId, String freeText, String languageId, int[] status,
		long parentCourseId, long groupId,
		java.util.LinkedHashMap<String, Object> params, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<com.ted.lms.model.Course> obc);

	public java.util.List<com.ted.lms.model.Course> filterByC(
		long companyId, String title, String description, String languageId,
		int[] status, long parentCourseId, long groupId,
		java.util.LinkedHashMap<String, Object> params, boolean andOperator,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<com.ted.lms.model.Course> obc);

	public java.util.List<com.ted.lms.model.Course> findByC(
		long companyId, String title, String description, String languageId,
		int[] status, long parentCourseId, long groupId,
		java.util.LinkedHashMap<String, Object> params, boolean andOperator,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<com.ted.lms.model.Course> obc);

	public java.util.List<com.liferay.portal.kernel.model.Group>
		getDistinctCourseGroups(long companyId);

	public java.util.List<com.ted.lms.model.Course> doFindByC(
		long companyId, String title, String description, String languageId,
		int[] status, long parentCourseId, long groupId,
		java.util.LinkedHashMap<String, Object> params, boolean andOperator,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<com.ted.lms.model.Course> obc,
		boolean inlineSQLHelper);

	public int countByKeywords(
		long companyId, String freeText, String languageId, int[] status,
		long parentCourseId, long groupId,
		java.util.LinkedHashMap<String, Object> params,
		boolean inlineSQLHelper);

	public int filterCountByKeywords(
		long companyId, String freeText, String languageId, int[] status,
		long parentCourseId, long groupId,
		java.util.LinkedHashMap<String, Object> params,
		boolean inlineSQLHelper);

	public int countByC(
		long companyId, String title, String description, String languageId,
		int[] status, long parentCourseId, long groupId,
		java.util.LinkedHashMap<String, Object> params, boolean andOperator);

	public int filterCountByC(
		long companyId, String title, String description, String languageId,
		int[] status, long parentCourseId, long groupId,
		java.util.LinkedHashMap<String, Object> params, boolean andOperator);

	public int doCountByC(
		long companyId, String title, String description, String languageId,
		int[] status, long parentCourseId, long groupId,
		java.util.LinkedHashMap<String, Object> params, boolean andOperator,
		boolean inlineSQLHelper);

	public java.util.List<com.ted.lms.model.Course> findChildRegistredUser(
		long parentCourseId, long userId);

	public java.util.List<com.liferay.portal.kernel.model.User> findStudents(
			long courseId, long companyId, String screenName, String firstName,
			String lastName, String emailAddress, int status, long[] teamIds,
			java.util.LinkedHashMap<String, Object> params, boolean andOperator,
			int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException;

	public java.util.List<com.liferay.portal.kernel.model.User> findTeachers(
			long courseId, long companyId, String screenName, String firstName,
			String lastName, String emailAddress, int status, long[] teamIds,
			java.util.LinkedHashMap<String, Object> params, boolean andOperator,
			int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException;

	public int countStudents(
		long courseId, long companyId, String screenName, String firstName,
		String lastName, String emailAddress, int status, long[] teamIds,
		java.util.LinkedHashMap<String, Object> params, boolean andOperator);

	public void closeSessionUser(
		com.liferay.portal.kernel.dao.orm.Session session);

	public com.liferay.portal.kernel.dao.orm.Session openSessionUser()
		throws com.liferay.portal.kernel.dao.orm.ORMException;

}