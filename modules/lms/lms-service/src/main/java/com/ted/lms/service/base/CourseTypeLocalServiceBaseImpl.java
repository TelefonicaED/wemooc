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
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;

import com.ted.lms.model.CourseType;
import com.ted.lms.service.CourseTypeLocalService;
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

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

import org.osgi.annotation.versioning.ProviderType;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the course type local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.ted.lms.service.impl.CourseTypeLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ted.lms.service.impl.CourseTypeLocalServiceImpl
 * @generated
 */
@ProviderType
public abstract class CourseTypeLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements CourseTypeLocalService, AopService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>CourseTypeLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.ted.lms.service.CourseTypeLocalServiceUtil</code>.
	 */

	/**
	 * Adds the course type to the database. Also notifies the appropriate model listeners.
	 *
	 * @param courseType the course type
	 * @return the course type that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CourseType addCourseType(CourseType courseType) {
		courseType.setNew(true);

		return courseTypePersistence.update(courseType);
	}

	/**
	 * Creates a new course type with the primary key. Does not add the course type to the database.
	 *
	 * @param courseTypeId the primary key for the new course type
	 * @return the new course type
	 */
	@Override
	@Transactional(enabled = false)
	public CourseType createCourseType(long courseTypeId) {
		return courseTypePersistence.create(courseTypeId);
	}

	/**
	 * Deletes the course type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param courseTypeId the primary key of the course type
	 * @return the course type that was removed
	 * @throws PortalException if a course type with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CourseType deleteCourseType(long courseTypeId)
		throws PortalException {

		return courseTypePersistence.remove(courseTypeId);
	}

	/**
	 * Deletes the course type from the database. Also notifies the appropriate model listeners.
	 *
	 * @param courseType the course type
	 * @return the course type that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CourseType deleteCourseType(CourseType courseType) {
		return courseTypePersistence.remove(courseType);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			CourseType.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return courseTypePersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.ted.lms.model.impl.CourseTypeModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return courseTypePersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.ted.lms.model.impl.CourseTypeModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return courseTypePersistence.findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return courseTypePersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection) {

		return courseTypePersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public CourseType fetchCourseType(long courseTypeId) {
		return courseTypePersistence.fetchByPrimaryKey(courseTypeId);
	}

	/**
	 * Returns the course type with the primary key.
	 *
	 * @param courseTypeId the primary key of the course type
	 * @return the course type
	 * @throws PortalException if a course type with the primary key could not be found
	 */
	@Override
	public CourseType getCourseType(long courseTypeId) throws PortalException {
		return courseTypePersistence.findByPrimaryKey(courseTypeId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(courseTypeLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CourseType.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("courseTypeId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			courseTypeLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(CourseType.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"courseTypeId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(courseTypeLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CourseType.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("courseTypeId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return courseTypeLocalService.deleteCourseType(
			(CourseType)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return courseTypePersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the course types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.ted.lms.model.impl.CourseTypeModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of course types
	 * @param end the upper bound of the range of course types (not inclusive)
	 * @return the range of course types
	 */
	@Override
	public List<CourseType> getCourseTypes(int start, int end) {
		return courseTypePersistence.findAll(start, end);
	}

	/**
	 * Returns the number of course types.
	 *
	 * @return the number of course types
	 */
	@Override
	public int getCourseTypesCount() {
		return courseTypePersistence.countAll();
	}

	/**
	 * Updates the course type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param courseType the course type
	 * @return the course type that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CourseType updateCourseType(CourseType courseType) {
		return courseTypePersistence.update(courseType);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			CourseTypeLocalService.class, IdentifiableOSGiService.class,
			PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		courseTypeLocalService = (CourseTypeLocalService)aopProxy;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return CourseTypeLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return CourseType.class;
	}

	protected String getModelClassName() {
		return CourseType.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = courseTypePersistence.getDataSource();

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

	protected CourseTypeLocalService courseTypeLocalService;

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

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ClassNameLocalService
		classNameLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.CompanyLocalService
		companyLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.GroupLocalService
		groupLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ImageLocalService
		imageLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ResourceLocalService
		resourceLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

}