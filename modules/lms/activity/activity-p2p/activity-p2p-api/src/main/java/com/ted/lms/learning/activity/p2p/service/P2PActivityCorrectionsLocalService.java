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

import aQute.bnd.annotation.ProviderType;

import com.liferay.exportimport.kernel.lar.PortletDataContext;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.ted.lms.learning.activity.p2p.model.P2PActivityCorrections;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for P2PActivityCorrections. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see P2PActivityCorrectionsLocalServiceUtil
 * @see com.ted.lms.learning.activity.p2p.service.base.P2PActivityCorrectionsLocalServiceBaseImpl
 * @see com.ted.lms.learning.activity.p2p.service.impl.P2PActivityCorrectionsLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface P2PActivityCorrectionsLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link P2PActivityCorrectionsLocalServiceUtil} to access the p2p activity corrections local service. Add custom service methods to {@link com.ted.lms.learning.activity.p2p.service.impl.P2PActivityCorrectionsLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the p2p activity corrections to the database. Also notifies the appropriate model listeners.
	*
	* @param p2pActivityCorrections the p2p activity corrections
	* @return the p2p activity corrections that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public P2PActivityCorrections addP2PActivityCorrections(
		P2PActivityCorrections p2pActivityCorrections);

	/**
	* Creates a new p2p activity corrections with the primary key. Does not add the p2p activity corrections to the database.
	*
	* @param p2pActivityCorrectionsId the primary key for the new p2p activity corrections
	* @return the new p2p activity corrections
	*/
	@Transactional(enabled = false)
	public P2PActivityCorrections createP2PActivityCorrections(
		long p2pActivityCorrectionsId);

	/**
	* Deletes the p2p activity corrections with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param p2pActivityCorrectionsId the primary key of the p2p activity corrections
	* @return the p2p activity corrections that was removed
	* @throws PortalException if a p2p activity corrections with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public P2PActivityCorrections deleteP2PActivityCorrections(
		long p2pActivityCorrectionsId) throws PortalException;

	/**
	* Deletes the p2p activity corrections from the database. Also notifies the appropriate model listeners.
	*
	* @param p2pActivityCorrections the p2p activity corrections
	* @return the p2p activity corrections that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public P2PActivityCorrections deleteP2PActivityCorrections(
		P2PActivityCorrections p2pActivityCorrections);

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	public DynamicQuery dynamicQuery();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ted.lms.learning.activity.p2p.model.impl.P2PActivityCorrectionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ted.lms.learning.activity.p2p.model.impl.P2PActivityCorrectionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public P2PActivityCorrections fetchP2PActivityCorrections(
		long p2pActivityCorrectionsId);

	/**
	* Returns the p2p activity corrections matching the UUID and group.
	*
	* @param uuid the p2p activity corrections's UUID
	* @param groupId the primary key of the group
	* @return the matching p2p activity corrections, or <code>null</code> if a matching p2p activity corrections could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public P2PActivityCorrections fetchP2PActivityCorrectionsByUuidAndGroupId(
		String uuid, long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<P2PActivityCorrections> getCorrectionsDoneByP2PActivityId(
		long p2pActivityId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<P2PActivityCorrections> getCorrectionsDoneByUser(long actId,
		long userId);

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
	* Returns the p2p activity corrections with the primary key.
	*
	* @param p2pActivityCorrectionsId the primary key of the p2p activity corrections
	* @return the p2p activity corrections
	* @throws PortalException if a p2p activity corrections with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public P2PActivityCorrections getP2PActivityCorrections(
		long p2pActivityCorrectionsId) throws PortalException;

	/**
	* Returns the p2p activity corrections matching the UUID and group.
	*
	* @param uuid the p2p activity corrections's UUID
	* @param groupId the primary key of the group
	* @return the matching p2p activity corrections
	* @throws PortalException if a matching p2p activity corrections could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public P2PActivityCorrections getP2PActivityCorrectionsByUuidAndGroupId(
		String uuid, long groupId) throws PortalException;

	/**
	* Returns a range of all the p2p activity correctionses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ted.lms.learning.activity.p2p.model.impl.P2PActivityCorrectionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of p2p activity correctionses
	* @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	* @return the range of p2p activity correctionses
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<P2PActivityCorrections> getP2PActivityCorrectionses(int start,
		int end);

	/**
	* Returns all the p2p activity correctionses matching the UUID and company.
	*
	* @param uuid the UUID of the p2p activity correctionses
	* @param companyId the primary key of the company
	* @return the matching p2p activity correctionses, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<P2PActivityCorrections> getP2PActivityCorrectionsesByUuidAndCompanyId(
		String uuid, long companyId);

	/**
	* Returns a range of p2p activity correctionses matching the UUID and company.
	*
	* @param uuid the UUID of the p2p activity correctionses
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of p2p activity correctionses
	* @param end the upper bound of the range of p2p activity correctionses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching p2p activity correctionses, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<P2PActivityCorrections> getP2PActivityCorrectionsesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<P2PActivityCorrections> orderByComparator);

	/**
	* Returns the number of p2p activity correctionses.
	*
	* @return the number of p2p activity correctionses
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getP2PActivityCorrectionsesCount();

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	* Updates the p2p activity corrections in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param p2pActivityCorrections the p2p activity corrections
	* @return the p2p activity corrections that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public P2PActivityCorrections updateP2PActivityCorrections(
		P2PActivityCorrections p2pActivityCorrections);
}