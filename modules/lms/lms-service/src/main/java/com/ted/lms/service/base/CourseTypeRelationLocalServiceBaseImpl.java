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

import com.ted.lms.model.CourseTypeRelation;
import com.ted.lms.service.CourseTypeRelationLocalService;
import com.ted.lms.service.persistence.CourseFinder;
import com.ted.lms.service.persistence.CoursePersistence;
import com.ted.lms.service.persistence.CourseResultFinder;
import com.ted.lms.service.persistence.CourseResultPersistence;
import com.ted.lms.service.persistence.CourseTypePersistence;
import com.ted.lms.service.persistence.CourseTypeRelationPK;
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
 * Provides the base implementation for the course type relation local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.ted.lms.service.impl.CourseTypeRelationLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ted.lms.service.impl.CourseTypeRelationLocalServiceImpl
 * @generated
 */
@ProviderType
public abstract class CourseTypeRelationLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements CourseTypeRelationLocalService, AopService,
			   IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>CourseTypeRelationLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.ted.lms.service.CourseTypeRelationLocalServiceUtil</code>.
	 */

	/**
	 * Adds the course type relation to the database. Also notifies the appropriate model listeners.
	 *
	 * @param courseTypeRelation the course type relation
	 * @return the course type relation that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CourseTypeRelation addCourseTypeRelation(
		CourseTypeRelation courseTypeRelation) {

		courseTypeRelation.setNew(true);

		return courseTypeRelationPersistence.update(courseTypeRelation);
	}

	/**
	 * Creates a new course type relation with the primary key. Does not add the course type relation to the database.
	 *
	 * @param courseTypeRelationPK the primary key for the new course type relation
	 * @return the new course type relation
	 */
	@Override
	@Transactional(enabled = false)
	public CourseTypeRelation createCourseTypeRelation(
		CourseTypeRelationPK courseTypeRelationPK) {

		return courseTypeRelationPersistence.create(courseTypeRelationPK);
	}

	/**
	 * Deletes the course type relation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param courseTypeRelationPK the primary key of the course type relation
	 * @return the course type relation that was removed
	 * @throws PortalException if a course type relation with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CourseTypeRelation deleteCourseTypeRelation(
			CourseTypeRelationPK courseTypeRelationPK)
		throws PortalException {

		return courseTypeRelationPersistence.remove(courseTypeRelationPK);
	}

	/**
	 * Deletes the course type relation from the database. Also notifies the appropriate model listeners.
	 *
	 * @param courseTypeRelation the course type relation
	 * @return the course type relation that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CourseTypeRelation deleteCourseTypeRelation(
		CourseTypeRelation courseTypeRelation) {

		return courseTypeRelationPersistence.remove(courseTypeRelation);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			CourseTypeRelation.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return courseTypeRelationPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.ted.lms.model.impl.CourseTypeRelationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return courseTypeRelationPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.ted.lms.model.impl.CourseTypeRelationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return courseTypeRelationPersistence.findWithDynamicQuery(
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
		return courseTypeRelationPersistence.countWithDynamicQuery(
			dynamicQuery);
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

		return courseTypeRelationPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public CourseTypeRelation fetchCourseTypeRelation(
		CourseTypeRelationPK courseTypeRelationPK) {

		return courseTypeRelationPersistence.fetchByPrimaryKey(
			courseTypeRelationPK);
	}

	/**
	 * Returns the course type relation with the primary key.
	 *
	 * @param courseTypeRelationPK the primary key of the course type relation
	 * @return the course type relation
	 * @throws PortalException if a course type relation with the primary key could not be found
	 */
	@Override
	public CourseTypeRelation getCourseTypeRelation(
			CourseTypeRelationPK courseTypeRelationPK)
		throws PortalException {

		return courseTypeRelationPersistence.findByPrimaryKey(
			courseTypeRelationPK);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			courseTypeRelationLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CourseTypeRelation.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"primaryKey.courseTypeId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			courseTypeRelationLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(CourseTypeRelation.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"primaryKey.courseTypeId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			courseTypeRelationLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CourseTypeRelation.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"primaryKey.courseTypeId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return courseTypeRelationLocalService.deleteCourseTypeRelation(
			(CourseTypeRelation)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return courseTypeRelationPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the course type relations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.ted.lms.model.impl.CourseTypeRelationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of course type relations
	 * @param end the upper bound of the range of course type relations (not inclusive)
	 * @return the range of course type relations
	 */
	@Override
	public List<CourseTypeRelation> getCourseTypeRelations(int start, int end) {
		return courseTypeRelationPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of course type relations.
	 *
	 * @return the number of course type relations
	 */
	@Override
	public int getCourseTypeRelationsCount() {
		return courseTypeRelationPersistence.countAll();
	}

	/**
	 * Updates the course type relation in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param courseTypeRelation the course type relation
	 * @return the course type relation that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CourseTypeRelation updateCourseTypeRelation(
		CourseTypeRelation courseTypeRelation) {

		return courseTypeRelationPersistence.update(courseTypeRelation);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			CourseTypeRelationLocalService.class, IdentifiableOSGiService.class,
			PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		courseTypeRelationLocalService =
			(CourseTypeRelationLocalService)aopProxy;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return CourseTypeRelationLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return CourseTypeRelation.class;
	}

	protected String getModelClassName() {
		return CourseTypeRelation.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				courseTypeRelationPersistence.getDataSource();

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

	protected CourseTypeRelationLocalService courseTypeRelationLocalService;

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
	protected com.liferay.portal.kernel.service.LayoutSetPrototypeLocalService
		layoutSetPrototypeLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ResourceLocalService
		resourceLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

}