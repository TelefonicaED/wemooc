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

import aQute.bnd.annotation.ProviderType;

import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.bean.BeanReference;
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
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.kernel.service.persistence.ClassNamePersistence;
import com.liferay.portal.kernel.service.persistence.CompanyPersistence;
import com.liferay.portal.kernel.service.persistence.GroupPersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import com.ted.lms.model.LearningActivityTry;
import com.ted.lms.service.LearningActivityTryLocalService;
import com.ted.lms.service.persistence.CoursePersistence;
import com.ted.lms.service.persistence.CourseResultPersistence;
import com.ted.lms.service.persistence.LearningActivityPersistence;
import com.ted.lms.service.persistence.LearningActivityResultPersistence;
import com.ted.lms.service.persistence.LearningActivityTryPersistence;
import com.ted.lms.service.persistence.ModulePersistence;
import com.ted.lms.service.persistence.ModuleResultPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the learning activity try local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.ted.lms.service.impl.LearningActivityTryLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ted.lms.service.impl.LearningActivityTryLocalServiceImpl
 * @see com.ted.lms.service.LearningActivityTryLocalServiceUtil
 * @generated
 */
@ProviderType
public abstract class LearningActivityTryLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements LearningActivityTryLocalService,
		IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.ted.lms.service.LearningActivityTryLocalServiceUtil} to access the learning activity try local service.
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

		return DynamicQueryFactoryUtil.forClass(LearningActivityTry.class,
			clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return learningActivityTryPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ted.lms.model.impl.LearningActivityTryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) {
		return learningActivityTryPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ted.lms.model.impl.LearningActivityTryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator) {
		return learningActivityTryPersistence.findWithDynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return learningActivityTryPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection) {
		return learningActivityTryPersistence.countWithDynamicQuery(dynamicQuery,
			projection);
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
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(learningActivityTryLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(LearningActivityTry.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("latId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		IndexableActionableDynamicQuery indexableActionableDynamicQuery = new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(learningActivityTryLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(LearningActivityTry.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName("latId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(learningActivityTryLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(LearningActivityTry.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("latId");
	}

	@Override
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		final PortletDataContext portletDataContext) {
		final ExportActionableDynamicQuery exportActionableDynamicQuery = new ExportActionableDynamicQuery() {
				@Override
				public long performCount() throws PortalException {
					ManifestSummary manifestSummary = portletDataContext.getManifestSummary();

					StagedModelType stagedModelType = getStagedModelType();

					long modelAdditionCount = super.performCount();

					manifestSummary.addModelAdditionCount(stagedModelType,
						modelAdditionCount);

					long modelDeletionCount = ExportImportHelperUtil.getModelDeletionCount(portletDataContext,
							stagedModelType);

					manifestSummary.addModelDeletionCount(stagedModelType,
						modelDeletionCount);

					return modelAdditionCount;
				}
			};

		initActionableDynamicQuery(exportActionableDynamicQuery);

		exportActionableDynamicQuery.setAddCriteriaMethod(new ActionableDynamicQuery.AddCriteriaMethod() {
				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
					portletDataContext.addDateRangeCriteria(dynamicQuery,
						"modifiedDate");
				}
			});

		exportActionableDynamicQuery.setCompanyId(portletDataContext.getCompanyId());

		exportActionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<LearningActivityTry>() {
				@Override
				public void performAction(
					LearningActivityTry learningActivityTry)
					throws PortalException {
					StagedModelDataHandlerUtil.exportStagedModel(portletDataContext,
						learningActivityTry);
				}
			});
		exportActionableDynamicQuery.setStagedModelType(new StagedModelType(
				PortalUtil.getClassNameId(LearningActivityTry.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return learningActivityTryLocalService.deleteLearningActivityTry((LearningActivityTry)persistedModel);
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
		return learningActivityTryPersistence.findByUuid_C(uuid, companyId,
			start, end, orderByComparator);
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
		String uuid, long groupId) throws PortalException {
		return learningActivityTryPersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the learning activity tries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ted.lms.model.impl.LearningActivityTryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of learning activity tries
	 * @param end the upper bound of the range of learning activity tries (not inclusive)
	 * @return the range of learning activity tries
	 */
	@Override
	public List<LearningActivityTry> getLearningActivityTries(int start, int end) {
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

	/**
	 * Returns the course local service.
	 *
	 * @return the course local service
	 */
	public com.ted.lms.service.CourseLocalService getCourseLocalService() {
		return courseLocalService;
	}

	/**
	 * Sets the course local service.
	 *
	 * @param courseLocalService the course local service
	 */
	public void setCourseLocalService(
		com.ted.lms.service.CourseLocalService courseLocalService) {
		this.courseLocalService = courseLocalService;
	}

	/**
	 * Returns the course persistence.
	 *
	 * @return the course persistence
	 */
	public CoursePersistence getCoursePersistence() {
		return coursePersistence;
	}

	/**
	 * Sets the course persistence.
	 *
	 * @param coursePersistence the course persistence
	 */
	public void setCoursePersistence(CoursePersistence coursePersistence) {
		this.coursePersistence = coursePersistence;
	}

	/**
	 * Returns the course result local service.
	 *
	 * @return the course result local service
	 */
	public com.ted.lms.service.CourseResultLocalService getCourseResultLocalService() {
		return courseResultLocalService;
	}

	/**
	 * Sets the course result local service.
	 *
	 * @param courseResultLocalService the course result local service
	 */
	public void setCourseResultLocalService(
		com.ted.lms.service.CourseResultLocalService courseResultLocalService) {
		this.courseResultLocalService = courseResultLocalService;
	}

	/**
	 * Returns the course result persistence.
	 *
	 * @return the course result persistence
	 */
	public CourseResultPersistence getCourseResultPersistence() {
		return courseResultPersistence;
	}

	/**
	 * Sets the course result persistence.
	 *
	 * @param courseResultPersistence the course result persistence
	 */
	public void setCourseResultPersistence(
		CourseResultPersistence courseResultPersistence) {
		this.courseResultPersistence = courseResultPersistence;
	}

	/**
	 * Returns the learning activity local service.
	 *
	 * @return the learning activity local service
	 */
	public com.ted.lms.service.LearningActivityLocalService getLearningActivityLocalService() {
		return learningActivityLocalService;
	}

	/**
	 * Sets the learning activity local service.
	 *
	 * @param learningActivityLocalService the learning activity local service
	 */
	public void setLearningActivityLocalService(
		com.ted.lms.service.LearningActivityLocalService learningActivityLocalService) {
		this.learningActivityLocalService = learningActivityLocalService;
	}

	/**
	 * Returns the learning activity persistence.
	 *
	 * @return the learning activity persistence
	 */
	public LearningActivityPersistence getLearningActivityPersistence() {
		return learningActivityPersistence;
	}

	/**
	 * Sets the learning activity persistence.
	 *
	 * @param learningActivityPersistence the learning activity persistence
	 */
	public void setLearningActivityPersistence(
		LearningActivityPersistence learningActivityPersistence) {
		this.learningActivityPersistence = learningActivityPersistence;
	}

	/**
	 * Returns the learning activity result local service.
	 *
	 * @return the learning activity result local service
	 */
	public com.ted.lms.service.LearningActivityResultLocalService getLearningActivityResultLocalService() {
		return learningActivityResultLocalService;
	}

	/**
	 * Sets the learning activity result local service.
	 *
	 * @param learningActivityResultLocalService the learning activity result local service
	 */
	public void setLearningActivityResultLocalService(
		com.ted.lms.service.LearningActivityResultLocalService learningActivityResultLocalService) {
		this.learningActivityResultLocalService = learningActivityResultLocalService;
	}

	/**
	 * Returns the learning activity result persistence.
	 *
	 * @return the learning activity result persistence
	 */
	public LearningActivityResultPersistence getLearningActivityResultPersistence() {
		return learningActivityResultPersistence;
	}

	/**
	 * Sets the learning activity result persistence.
	 *
	 * @param learningActivityResultPersistence the learning activity result persistence
	 */
	public void setLearningActivityResultPersistence(
		LearningActivityResultPersistence learningActivityResultPersistence) {
		this.learningActivityResultPersistence = learningActivityResultPersistence;
	}

	/**
	 * Returns the learning activity try local service.
	 *
	 * @return the learning activity try local service
	 */
	public LearningActivityTryLocalService getLearningActivityTryLocalService() {
		return learningActivityTryLocalService;
	}

	/**
	 * Sets the learning activity try local service.
	 *
	 * @param learningActivityTryLocalService the learning activity try local service
	 */
	public void setLearningActivityTryLocalService(
		LearningActivityTryLocalService learningActivityTryLocalService) {
		this.learningActivityTryLocalService = learningActivityTryLocalService;
	}

	/**
	 * Returns the learning activity try persistence.
	 *
	 * @return the learning activity try persistence
	 */
	public LearningActivityTryPersistence getLearningActivityTryPersistence() {
		return learningActivityTryPersistence;
	}

	/**
	 * Sets the learning activity try persistence.
	 *
	 * @param learningActivityTryPersistence the learning activity try persistence
	 */
	public void setLearningActivityTryPersistence(
		LearningActivityTryPersistence learningActivityTryPersistence) {
		this.learningActivityTryPersistence = learningActivityTryPersistence;
	}

	/**
	 * Returns the module local service.
	 *
	 * @return the module local service
	 */
	public com.ted.lms.service.ModuleLocalService getModuleLocalService() {
		return moduleLocalService;
	}

	/**
	 * Sets the module local service.
	 *
	 * @param moduleLocalService the module local service
	 */
	public void setModuleLocalService(
		com.ted.lms.service.ModuleLocalService moduleLocalService) {
		this.moduleLocalService = moduleLocalService;
	}

	/**
	 * Returns the module persistence.
	 *
	 * @return the module persistence
	 */
	public ModulePersistence getModulePersistence() {
		return modulePersistence;
	}

	/**
	 * Sets the module persistence.
	 *
	 * @param modulePersistence the module persistence
	 */
	public void setModulePersistence(ModulePersistence modulePersistence) {
		this.modulePersistence = modulePersistence;
	}

	/**
	 * Returns the module result local service.
	 *
	 * @return the module result local service
	 */
	public com.ted.lms.service.ModuleResultLocalService getModuleResultLocalService() {
		return moduleResultLocalService;
	}

	/**
	 * Sets the module result local service.
	 *
	 * @param moduleResultLocalService the module result local service
	 */
	public void setModuleResultLocalService(
		com.ted.lms.service.ModuleResultLocalService moduleResultLocalService) {
		this.moduleResultLocalService = moduleResultLocalService;
	}

	/**
	 * Returns the module result persistence.
	 *
	 * @return the module result persistence
	 */
	public ModuleResultPersistence getModuleResultPersistence() {
		return moduleResultPersistence;
	}

	/**
	 * Sets the module result persistence.
	 *
	 * @param moduleResultPersistence the module result persistence
	 */
	public void setModuleResultPersistence(
		ModuleResultPersistence moduleResultPersistence) {
		this.moduleResultPersistence = moduleResultPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the class name local service.
	 *
	 * @return the class name local service
	 */
	public com.liferay.portal.kernel.service.ClassNameLocalService getClassNameLocalService() {
		return classNameLocalService;
	}

	/**
	 * Sets the class name local service.
	 *
	 * @param classNameLocalService the class name local service
	 */
	public void setClassNameLocalService(
		com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService) {
		this.classNameLocalService = classNameLocalService;
	}

	/**
	 * Returns the class name persistence.
	 *
	 * @return the class name persistence
	 */
	public ClassNamePersistence getClassNamePersistence() {
		return classNamePersistence;
	}

	/**
	 * Sets the class name persistence.
	 *
	 * @param classNamePersistence the class name persistence
	 */
	public void setClassNamePersistence(
		ClassNamePersistence classNamePersistence) {
		this.classNamePersistence = classNamePersistence;
	}

	/**
	 * Returns the company local service.
	 *
	 * @return the company local service
	 */
	public com.liferay.portal.kernel.service.CompanyLocalService getCompanyLocalService() {
		return companyLocalService;
	}

	/**
	 * Sets the company local service.
	 *
	 * @param companyLocalService the company local service
	 */
	public void setCompanyLocalService(
		com.liferay.portal.kernel.service.CompanyLocalService companyLocalService) {
		this.companyLocalService = companyLocalService;
	}

	/**
	 * Returns the company persistence.
	 *
	 * @return the company persistence
	 */
	public CompanyPersistence getCompanyPersistence() {
		return companyPersistence;
	}

	/**
	 * Sets the company persistence.
	 *
	 * @param companyPersistence the company persistence
	 */
	public void setCompanyPersistence(CompanyPersistence companyPersistence) {
		this.companyPersistence = companyPersistence;
	}

	/**
	 * Returns the group local service.
	 *
	 * @return the group local service
	 */
	public com.liferay.portal.kernel.service.GroupLocalService getGroupLocalService() {
		return groupLocalService;
	}

	/**
	 * Sets the group local service.
	 *
	 * @param groupLocalService the group local service
	 */
	public void setGroupLocalService(
		com.liferay.portal.kernel.service.GroupLocalService groupLocalService) {
		this.groupLocalService = groupLocalService;
	}

	/**
	 * Returns the group persistence.
	 *
	 * @return the group persistence
	 */
	public GroupPersistence getGroupPersistence() {
		return groupPersistence;
	}

	/**
	 * Sets the group persistence.
	 *
	 * @param groupPersistence the group persistence
	 */
	public void setGroupPersistence(GroupPersistence groupPersistence) {
		this.groupPersistence = groupPersistence;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.kernel.service.ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.kernel.service.UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.kernel.service.UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register("com.ted.lms.model.LearningActivityTry",
			learningActivityTryLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.ted.lms.model.LearningActivityTry");
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
			DataSource dataSource = learningActivityTryPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = com.ted.lms.service.CourseLocalService.class)
	protected com.ted.lms.service.CourseLocalService courseLocalService;
	@BeanReference(type = CoursePersistence.class)
	protected CoursePersistence coursePersistence;
	@BeanReference(type = com.ted.lms.service.CourseResultLocalService.class)
	protected com.ted.lms.service.CourseResultLocalService courseResultLocalService;
	@BeanReference(type = CourseResultPersistence.class)
	protected CourseResultPersistence courseResultPersistence;
	@BeanReference(type = com.ted.lms.service.LearningActivityLocalService.class)
	protected com.ted.lms.service.LearningActivityLocalService learningActivityLocalService;
	@BeanReference(type = LearningActivityPersistence.class)
	protected LearningActivityPersistence learningActivityPersistence;
	@BeanReference(type = com.ted.lms.service.LearningActivityResultLocalService.class)
	protected com.ted.lms.service.LearningActivityResultLocalService learningActivityResultLocalService;
	@BeanReference(type = LearningActivityResultPersistence.class)
	protected LearningActivityResultPersistence learningActivityResultPersistence;
	@BeanReference(type = LearningActivityTryLocalService.class)
	protected LearningActivityTryLocalService learningActivityTryLocalService;
	@BeanReference(type = LearningActivityTryPersistence.class)
	protected LearningActivityTryPersistence learningActivityTryPersistence;
	@BeanReference(type = com.ted.lms.service.ModuleLocalService.class)
	protected com.ted.lms.service.ModuleLocalService moduleLocalService;
	@BeanReference(type = ModulePersistence.class)
	protected ModulePersistence modulePersistence;
	@BeanReference(type = com.ted.lms.service.ModuleResultLocalService.class)
	protected com.ted.lms.service.ModuleResultLocalService moduleResultLocalService;
	@BeanReference(type = ModuleResultPersistence.class)
	protected ModuleResultPersistence moduleResultPersistence;
	@ServiceReference(type = com.liferay.counter.kernel.service.CounterLocalService.class)
	protected com.liferay.counter.kernel.service.CounterLocalService counterLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.ClassNameLocalService.class)
	protected com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService;
	@ServiceReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@ServiceReference(type = com.liferay.portal.kernel.service.CompanyLocalService.class)
	protected com.liferay.portal.kernel.service.CompanyLocalService companyLocalService;
	@ServiceReference(type = CompanyPersistence.class)
	protected CompanyPersistence companyPersistence;
	@ServiceReference(type = com.liferay.portal.kernel.service.GroupLocalService.class)
	protected com.liferay.portal.kernel.service.GroupLocalService groupLocalService;
	@ServiceReference(type = GroupPersistence.class)
	protected GroupPersistence groupPersistence;
	@ServiceReference(type = com.liferay.portal.kernel.service.ResourceLocalService.class)
	protected com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.UserLocalService.class)
	protected com.liferay.portal.kernel.service.UserLocalService userLocalService;
	@ServiceReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@ServiceReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry persistedModelLocalServiceRegistry;
}