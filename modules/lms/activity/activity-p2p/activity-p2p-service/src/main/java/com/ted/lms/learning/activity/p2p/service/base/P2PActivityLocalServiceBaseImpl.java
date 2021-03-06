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

package com.ted.lms.learning.activity.p2p.service.base;

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

import com.ted.lms.learning.activity.p2p.model.P2PActivity;
import com.ted.lms.learning.activity.p2p.service.P2PActivityLocalService;
import com.ted.lms.learning.activity.p2p.service.persistence.P2PActivityCorrectionsFinder;
import com.ted.lms.learning.activity.p2p.service.persistence.P2PActivityCorrectionsPersistence;
import com.ted.lms.learning.activity.p2p.service.persistence.P2PActivityFinder;
import com.ted.lms.learning.activity.p2p.service.persistence.P2PActivityPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

import org.osgi.annotation.versioning.ProviderType;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the p2p activity local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.ted.lms.learning.activity.p2p.service.impl.P2PActivityLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ted.lms.learning.activity.p2p.service.impl.P2PActivityLocalServiceImpl
 * @generated
 */
@ProviderType
public abstract class P2PActivityLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements P2PActivityLocalService, AopService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>P2PActivityLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.ted.lms.learning.activity.p2p.service.P2PActivityLocalServiceUtil</code>.
	 */

	/**
	 * Adds the p2p activity to the database. Also notifies the appropriate model listeners.
	 *
	 * @param p2pActivity the p2p activity
	 * @return the p2p activity that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public P2PActivity addP2PActivity(P2PActivity p2pActivity) {
		p2pActivity.setNew(true);

		return p2pActivityPersistence.update(p2pActivity);
	}

	/**
	 * Creates a new p2p activity with the primary key. Does not add the p2p activity to the database.
	 *
	 * @param p2pActivityId the primary key for the new p2p activity
	 * @return the new p2p activity
	 */
	@Override
	@Transactional(enabled = false)
	public P2PActivity createP2PActivity(long p2pActivityId) {
		return p2pActivityPersistence.create(p2pActivityId);
	}

	/**
	 * Deletes the p2p activity with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param p2pActivityId the primary key of the p2p activity
	 * @return the p2p activity that was removed
	 * @throws PortalException if a p2p activity with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public P2PActivity deleteP2PActivity(long p2pActivityId)
		throws PortalException {

		return p2pActivityPersistence.remove(p2pActivityId);
	}

	/**
	 * Deletes the p2p activity from the database. Also notifies the appropriate model listeners.
	 *
	 * @param p2pActivity the p2p activity
	 * @return the p2p activity that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public P2PActivity deleteP2PActivity(P2PActivity p2pActivity) {
		return p2pActivityPersistence.remove(p2pActivity);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			P2PActivity.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return p2pActivityPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.ted.lms.learning.activity.p2p.model.impl.P2PActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return p2pActivityPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.ted.lms.learning.activity.p2p.model.impl.P2PActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return p2pActivityPersistence.findWithDynamicQuery(
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
		return p2pActivityPersistence.countWithDynamicQuery(dynamicQuery);
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

		return p2pActivityPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public P2PActivity fetchP2PActivity(long p2pActivityId) {
		return p2pActivityPersistence.fetchByPrimaryKey(p2pActivityId);
	}

	/**
	 * Returns the p2p activity matching the UUID and group.
	 *
	 * @param uuid the p2p activity's UUID
	 * @param groupId the primary key of the group
	 * @return the matching p2p activity, or <code>null</code> if a matching p2p activity could not be found
	 */
	@Override
	public P2PActivity fetchP2PActivityByUuidAndGroupId(
		String uuid, long groupId) {

		return p2pActivityPersistence.fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the p2p activity with the primary key.
	 *
	 * @param p2pActivityId the primary key of the p2p activity
	 * @return the p2p activity
	 * @throws PortalException if a p2p activity with the primary key could not be found
	 */
	@Override
	public P2PActivity getP2PActivity(long p2pActivityId)
		throws PortalException {

		return p2pActivityPersistence.findByPrimaryKey(p2pActivityId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(p2pActivityLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(P2PActivity.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("p2pActivityId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			p2pActivityLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(P2PActivity.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"p2pActivityId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(p2pActivityLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(P2PActivity.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("p2pActivityId");
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
			new ActionableDynamicQuery.PerformActionMethod<P2PActivity>() {

				@Override
				public void performAction(P2PActivity p2pActivity)
					throws PortalException {

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, p2pActivity);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(P2PActivity.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return p2pActivityLocalService.deleteP2PActivity(
			(P2PActivity)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return p2pActivityPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns all the p2p activities matching the UUID and company.
	 *
	 * @param uuid the UUID of the p2p activities
	 * @param companyId the primary key of the company
	 * @return the matching p2p activities, or an empty list if no matches were found
	 */
	@Override
	public List<P2PActivity> getP2PActivitiesByUuidAndCompanyId(
		String uuid, long companyId) {

		return p2pActivityPersistence.findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of p2p activities matching the UUID and company.
	 *
	 * @param uuid the UUID of the p2p activities
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of p2p activities
	 * @param end the upper bound of the range of p2p activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching p2p activities, or an empty list if no matches were found
	 */
	@Override
	public List<P2PActivity> getP2PActivitiesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<P2PActivity> orderByComparator) {

		return p2pActivityPersistence.findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the p2p activity matching the UUID and group.
	 *
	 * @param uuid the p2p activity's UUID
	 * @param groupId the primary key of the group
	 * @return the matching p2p activity
	 * @throws PortalException if a matching p2p activity could not be found
	 */
	@Override
	public P2PActivity getP2PActivityByUuidAndGroupId(String uuid, long groupId)
		throws PortalException {

		return p2pActivityPersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the p2p activities.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.ted.lms.learning.activity.p2p.model.impl.P2PActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of p2p activities
	 * @param end the upper bound of the range of p2p activities (not inclusive)
	 * @return the range of p2p activities
	 */
	@Override
	public List<P2PActivity> getP2PActivities(int start, int end) {
		return p2pActivityPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of p2p activities.
	 *
	 * @return the number of p2p activities
	 */
	@Override
	public int getP2PActivitiesCount() {
		return p2pActivityPersistence.countAll();
	}

	/**
	 * Updates the p2p activity in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param p2pActivity the p2p activity
	 * @return the p2p activity that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public P2PActivity updateP2PActivity(P2PActivity p2pActivity) {
		return p2pActivityPersistence.update(p2pActivity);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			P2PActivityLocalService.class, IdentifiableOSGiService.class,
			PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		p2pActivityLocalService = (P2PActivityLocalService)aopProxy;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return P2PActivityLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return P2PActivity.class;
	}

	protected String getModelClassName() {
		return P2PActivity.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = p2pActivityPersistence.getDataSource();

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

	protected P2PActivityLocalService p2pActivityLocalService;

	@Reference
	protected P2PActivityPersistence p2pActivityPersistence;

	@Reference
	protected P2PActivityFinder p2pActivityFinder;

	@Reference
	protected P2PActivityCorrectionsPersistence
		p2pActivityCorrectionsPersistence;

	@Reference
	protected P2PActivityCorrectionsFinder p2pActivityCorrectionsFinder;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ClassNameLocalService
		classNameLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ResourceLocalService
		resourceLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

	@Reference
	protected com.liferay.document.library.kernel.service.DLAppLocalService
		dlAppLocalService;

	@Reference
	protected
		com.liferay.document.library.kernel.service.DLFileEntryLocalService
			dlFileEntryLocalService;

}