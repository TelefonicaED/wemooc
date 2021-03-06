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

package com.ted.lms.learning.activity.p2p.service;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.ted.lms.learning.activity.p2p.model.P2PActivity;
import com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the local service interface for P2PActivity. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see P2PActivityLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface P2PActivityLocalService
	extends BaseLocalService, PersistedModelLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link P2PActivityLocalServiceUtil} to access the p2p activity local service. Add custom service methods to <code>com.ted.lms.learning.activity.p2p.service.impl.P2PActivityLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public P2PActivity addP2PActivity(
			long userId, long actId, String description,
			ServiceContext serviceContext, ThemeDisplay themeDisplay)
		throws PortalException;

	public P2PActivity addP2PActivity(
			long userId, long actId, String description, String fileName,
			File file, String mimeType, ServiceContext serviceContext,
			ThemeDisplay themeDisplay)
		throws IOException, PortalException;

	/**
	 * Adds the p2p activity to the database. Also notifies the appropriate model listeners.
	 *
	 * @param p2pActivity the p2p activity
	 * @return the p2p activity that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	public P2PActivity addP2PActivity(P2PActivity p2pActivity);

	public DLFileEntry addP2PFileEntry(
			String fileName, File file, String mimeType, long folderId,
			long groupId, long companyId, long userCreatedId)
		throws IOException, PortalException;

	public Folder addP2PFolder(long userId, long groupId)
		throws PortalException;

	public void asignCorrectionP2PActivity(
		P2PActivity p2pActivity, int numValidations, String assignationType);

	/**
	 * Creates a new p2p activity with the primary key. Does not add the p2p activity to the database.
	 *
	 * @param p2pActivityId the primary key for the new p2p activity
	 * @return the new p2p activity
	 */
	@Transactional(enabled = false)
	public P2PActivity createP2PActivity(long p2pActivityId);

	/**
	 * Deletes the p2p activity with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param p2pActivityId the primary key of the p2p activity
	 * @return the p2p activity that was removed
	 * @throws PortalException if a p2p activity with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	public P2PActivity deleteP2PActivity(long p2pActivityId)
		throws PortalException;

	/**
	 * Deletes the p2p activity from the database. Also notifies the appropriate model listeners.
	 *
	 * @param p2pActivity the p2p activity
	 * @return the p2p activity that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	public P2PActivity deleteP2PActivity(P2PActivity p2pActivity);

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DynamicQuery dynamicQuery();

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public P2PActivity fetchP2PActivity(long p2pActivityId);

	/**
	 * Returns the p2p activity matching the UUID and group.
	 *
	 * @param uuid the p2p activity's UUID
	 * @param groupId the primary key of the group
	 * @return the matching p2p activity, or <code>null</code> if a matching p2p activity could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public P2PActivity fetchP2PActivityByUuidAndGroupId(
		String uuid, long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<P2PActivity> getP2PActivities(int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<P2PActivity> getP2PActivitiesByAssignationsCompleted(
		boolean assignationsCompleted);

	/**
	 * Returns all the p2p activities matching the UUID and company.
	 *
	 * @param uuid the UUID of the p2p activities
	 * @param companyId the primary key of the company
	 * @return the matching p2p activities, or an empty list if no matches were found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<P2PActivity> getP2PActivitiesByUuidAndCompanyId(
		String uuid, long companyId);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<P2PActivity> getP2PActivitiesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<P2PActivity> orderByComparator);

	/**
	 * Returns the number of p2p activities.
	 *
	 * @return the number of p2p activities
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getP2PActivitiesCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<P2PActivity> getP2PActivitiesToCorrect(
			long actId, P2PActivity p2pActivity, int numValidaciones,
			String assignationType)
		throws PortalException;

	/**
	 * Returns the p2p activity with the primary key.
	 *
	 * @param p2pActivityId the primary key of the p2p activity
	 * @return the p2p activity
	 * @throws PortalException if a p2p activity with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public P2PActivity getP2PActivity(long p2pActivityId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public P2PActivity getP2PActivity(long actId, long userId);

	/**
	 * Returns the p2p activity matching the UUID and group.
	 *
	 * @param uuid the p2p activity's UUID
	 * @param groupId the primary key of the group
	 * @return the matching p2p activity
	 * @throws PortalException if a matching p2p activity could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public P2PActivity getP2PActivityByUuidAndGroupId(String uuid, long groupId)
		throws PortalException;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasP2PActivity(long actId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasP2PActivity(long actId, long userId);

	/**
	 * Updates the p2p activity in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param p2pActivity the p2p activity
	 * @return the p2p activity that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	public P2PActivity updateP2PActivity(P2PActivity p2pActivity);

	/**
	 * Se actualizan los tries y los results de los usuarios implicados
	 *
	 * @param p2pActivityId
	 * @param userId
	 * @throws PortalException
	 * @throws UnsupportedEncodingException
	 * @throws Exception
	 */
	public void updateResultP2PActivity(
			P2PActivity p2pActivityCorrected,
			P2PActivityCorrections p2pActivityCorrection, long userId,
			int numValidations, boolean result, boolean anonimous,
			boolean emailAnonimous, JSONArray evaluationCriteria,
			ThemeDisplay themeDisplay, ServiceContext serviceContext)
		throws PortalException, UnsupportedEncodingException;

}