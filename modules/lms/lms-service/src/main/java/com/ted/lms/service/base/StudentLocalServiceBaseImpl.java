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

package com.ted.lms.service.base;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.util.InfrastructureUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import com.ted.lms.service.StudentLocalService;
import com.ted.lms.service.persistence.CourseFinder;
import com.ted.lms.service.persistence.CoursePersistence;
import com.ted.lms.service.persistence.CourseResultFinder;
import com.ted.lms.service.persistence.CourseResultPersistence;
import com.ted.lms.service.persistence.CourseTypePersistence;
import com.ted.lms.service.persistence.CourseTypeRelationPersistence;
import com.ted.lms.service.persistence.LearningActivityPersistence;
import com.ted.lms.service.persistence.LearningActivityResultFinder;
import com.ted.lms.service.persistence.LearningActivityResultPersistence;
import com.ted.lms.service.persistence.LearningActivityTryPersistence;
import com.ted.lms.service.persistence.ModulePersistence;
import com.ted.lms.service.persistence.ModuleResultFinder;
import com.ted.lms.service.persistence.ModuleResultPersistence;

import javax.sql.DataSource;

import org.osgi.annotation.versioning.ProviderType;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the student local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.ted.lms.service.impl.StudentLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ted.lms.service.impl.StudentLocalServiceImpl
 * @generated
 */
@ProviderType
public abstract class StudentLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements StudentLocalService, AopService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>StudentLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.ted.lms.service.StudentLocalServiceUtil</code>.
	 */
	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			StudentLocalService.class, IdentifiableOSGiService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		studentLocalService = (StudentLocalService)aopProxy;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return StudentLocalService.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = InfrastructureUtil.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(
				dataSource, sql);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@Reference
	protected CoursePersistence coursePersistence;

	@Reference
	protected CourseFinder courseFinder;

	@Reference
	protected CourseResultPersistence courseResultPersistence;

	@Reference
	protected CourseResultFinder courseResultFinder;

	@Reference
	protected CourseTypePersistence courseTypePersistence;

	@Reference
	protected CourseTypeRelationPersistence courseTypeRelationPersistence;

	@Reference
	protected LearningActivityPersistence learningActivityPersistence;

	@Reference
	protected LearningActivityResultPersistence
		learningActivityResultPersistence;

	@Reference
	protected LearningActivityResultFinder learningActivityResultFinder;

	@Reference
	protected LearningActivityTryPersistence learningActivityTryPersistence;

	@Reference
	protected ModulePersistence modulePersistence;

	@Reference
	protected ModuleResultPersistence moduleResultPersistence;

	@Reference
	protected ModuleResultFinder moduleResultFinder;

	protected StudentLocalService studentLocalService;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ClassNameLocalService
		classNameLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.GroupLocalService
		groupLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ResourceLocalService
		resourceLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

}