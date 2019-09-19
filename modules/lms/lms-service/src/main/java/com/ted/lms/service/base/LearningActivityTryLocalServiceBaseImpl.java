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

import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
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

import com.ted.lms.model.LearningActivityTry;
import com.ted.lms.service.LearningActivityTryLocalService;
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
 * Provides the base implementation for the learning activity try local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.ted.lms.service.impl.LearningActivityTryLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ted.lms.service.impl.LearningActivityTryLocalServiceImpl
 * @generated
 */
@ProviderType
public abstract class LearningActivityTryLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements LearningActivityTryLocalService, AopService,
			   IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>LearningActivityTryLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.ted.lms.service.LearningActivityTryLocalServiceUtil</code>.
	 */

	/**
	 * Adds the learning activity try to the database. Also notifies the appropriate model listeners.
	 *
	 * @param learningActivityTry the learning activity try
	 * @return the learning activity try that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public LearningActivityTry addLearningActivityTry(
		LearningActivityTry learningActivityTry) {

		learningActivityTry.setNew(true);

		return learningActivityTryPersistence.update(learningActivityTry);
	}

	/**
	 * Creates a new learning activity try with the primary key. Does not add the learning activity try to the database.
	 *
	 * @param latId the primary key for the new learning activity try
	 * @return the new learning activity try
	 */
	@Override
	@Transactional(enabled = false)
	public LearningActivityTry createLearningActivityTry(long latId) {
		return learningActivityTryPersistence.create(latId);
	}

	/**
	 * Deletes the learning activity try with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param latId the primary key of the learning activity try
	 * @return the learning activity try that was removed
	 * @throws PortalException if a learning activity try with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public LearningActivityTry deleteLearningActivityTry(long latId)
		throws PortalException {

		return learningActivityTryPersistence.remove(latId);
	}

	/**
	 * Deletes the learning activity try from the database. Also notifies the appropriate model listeners.
	 *
	 * @param learningActivityTry the learning activity try
	 * @return the learning activity try that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public LearningActivityTry deleteLearningActivityTry(
		LearningActivityTry learningActivityTry) {

		return learningActivityTryPersistence.remove(learningActivityTry);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			LearningActivityTry.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return learningActivityTryPersistence.findWithDynamicQuery(
			dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.ted.lms.model.impl.LearningActivityTryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return learningActivityTryPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.ted.lms.model.impl.LearningActivityTryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return learningActivityTryPersistence.findWithDynamicQuery(
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
		return learningActivityTryPersistence.countWithDynamicQuery(
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

		return learningActivityTryPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public LearningActivityTry fetchLearningActivityTry(long latId) {
		return learningActivityTryPersistence.fetchByPrimaryKey(latId);
	}

	/**
	 * Returns the learning activity try matching the UUID and group.
	 *
	 * @param uuid the learning activity try's UUID
	 * @param groupId the primary key of the group
	 * @return the matching learning activity try, or <code>null</code> if a matching learning activity try could not be found
	 */
	@Override
	public LearningActivityTry fetchLearningActivityTryByUuidAndGroupId(
		String uuid, long groupId) {

		return learningActivityTryPersistence.fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the learning activity try with the primary key.
	 *
	 * @param latId the primary key of the learning activity try
	 * @return the learning activity try
	 * @throws PortalException if a learning activity try with the primary key could not be found
	 */
	@Override
	public LearningActivityTry getLearningActivityTry(long latId)
		throws PortalException {

		return learningActivityTryPersistence.findByPrimaryKey(latId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			learningActivityTryLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(LearningActivityTry.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("latId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			learningActivityTryLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(
			LearningActivityTry.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName("latId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			learningActivityTryLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(LearningActivityTry.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("latId");
	}

	@Override
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		final PortletDataContext portletDataContext) {

		final ExportActionableDynamicQuery exportActionableDynamicQuery =
			new ExportActionableDynamicQuery() {

				@Override
				public long performCount() throws PortalException {
					ManifestSummary manifestSummary =
						portletDataContext.getManifestSummary();

					StagedModelType stagedModelType = getStagedModelType();

					long modelAdditionCount = super.performCount();

					manifestSummary.addModelAdditionCount(
						stagedModelType, modelAdditionCount);

					long modelDeletionCount =
						ExportImportHelperUtil.getModelDeletionCount(
							portletDataContext, stagedModelType);

					manifestSummary.addModelDeletionCount(
						stagedModelType, modelDeletionCount);

					return modelAdditionCount;
				}

			};

		initActionableDynamicQuery(exportActionableDynamicQuery);

		exportActionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {

				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
					portletDataContext.addDateRangeCriteria(
						dynamicQuery, "modifiedDate");
				}

			});

		exportActionableDynamicQuery.setCompanyId(
			portletDataContext.getCompanyId());

		exportActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod
				<LearningActivityTry>() {

				@Override
				public void performAction(
						LearningActivityTry learningActivityTry)
					throws PortalException {

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, learningActivityTry);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(
					LearningActivityTry.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return learningActivityTryLocalService.deleteLearningActivityTry(
			(LearningActivityTry)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return learningActivityTryPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns all the learning activity tries matching the UUID and company.
	 *
	 * @param uuid the UUID of the learning activity tries
	 * @param companyId the primary key of the company
	 * @return the matching learning activity tries, or an empty list if no matches were found
	 */
	@Override
	public List<LearningActivityTry> getLearningActivityTriesByUuidAndCompanyId(
		String uuid, long companyId) {

		return learningActivityTryPersistence.findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of learning activity tries matching the UUID and company.
	 *
	 * @param uuid the UUID of the learning activity tries
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of learning activity tries
	 * @param end the upper bound of the range of learning activity tries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching learning activity tries, or an empty list if no matches were found
	 */
	@Override
	public List<LearningActivityTry> getLearningActivityTriesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<LearningActivityTry> orderByComparator) {

		return learningActivityTryPersistence.findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the learning activity try matching the UUID and group.
	 *
	 * @param uuid the learning activity try's UUID
	 * @param groupId the primary key of the group
	 * @return the matching learning activity try
	 * @throws PortalException if a matching learning activity try could not be found
	 */
	@Override
	public LearningActivityTry getLearningActivityTryByUuidAndGroupId(
			String uuid, long groupId)
		throws PortalException {

		return learningActivityTryPersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the learning activity tries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.ted.lms.model.impl.LearningActivityTryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of learning activity tries
	 * @param end the upper bound of the range of learning activity tries (not inclusive)
	 * @return the range of learning activity tries
	 */
	@Override
	public List<LearningActivityTry> getLearningActivityTries(
		int start, int end) {

		return learningActivityTryPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of learning activity tries.
	 *
	 * @return the number of learning activity tries
	 */
	@Override
	public int getLearningActivityTriesCount() {
		return learningActivityTryPersistence.countAll();
	}

	/**
	 * Updates the learning activity try in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param learningActivityTry the learning activity try
	 * @return the learning activity try that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public LearningActivityTry updateLearningActivityTry(
		LearningActivityTry learningActivityTry) {

		return learningActivityTryPersistence.update(learningActivityTry);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			LearningActivityTryLocalService.class,
			IdentifiableOSGiService.class, PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		learningActivityTryLocalService =
			(LearningActivityTryLocalService)aopProxy;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return LearningActivityTryLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return LearningActivityTry.class;
	}

	protected String getModelClassName() {
		return LearningActivityTry.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				learningActivityTryPersistence.getDataSource();

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

	protected LearningActivityTryLocalService learningActivityTryLocalService;

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
	protected com.liferay.portal.kernel.service.ResourceLocalService
		resourceLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

}