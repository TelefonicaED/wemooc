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

package com.ted.prerequisite.service;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
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

import com.ted.prerequisite.model.Prerequisite;
import com.ted.prerequisite.model.PrerequisiteRelation;

import java.io.Serializable;

import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the local service interface for PrerequisiteRelation. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see PrerequisiteRelationLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface PrerequisiteRelationLocalService
	extends BaseLocalService, PersistedModelLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PrerequisiteRelationLocalServiceUtil} to access the prerequisite relation local service. Add custom service methods to <code>com.ted.prerequisite.service.impl.PrerequisiteRelationLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public PrerequisiteRelation addPrerequisiteRelation(
		long classNamePrerequisiteId, long classNameId, long classPK,
		String extraData);

	/**
	 * Adds the prerequisite relation to the database. Also notifies the appropriate model listeners.
	 *
	 * @param prerequisiteRelation the prerequisite relation
	 * @return the prerequisite relation that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	public PrerequisiteRelation addPrerequisiteRelation(
		PrerequisiteRelation prerequisiteRelation);

	/**
	 * Creates a new prerequisite relation with the primary key. Does not add the prerequisite relation to the database.
	 *
	 * @param prerequisiteRelationId the primary key for the new prerequisite relation
	 * @return the new prerequisite relation
	 */
	@Transactional(enabled = false)
	public PrerequisiteRelation createPrerequisiteRelation(
		long prerequisiteRelationId);

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	/**
	 * Deletes the prerequisite relation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param prerequisiteRelationId the primary key of the prerequisite relation
	 * @return the prerequisite relation that was removed
	 * @throws PortalException if a prerequisite relation with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	public PrerequisiteRelation deletePrerequisiteRelation(
			long prerequisiteRelationId)
		throws PortalException;

	/**
	 * Deletes the prerequisite relation from the database. Also notifies the appropriate model listeners.
	 *
	 * @param prerequisiteRelation the prerequisite relation
	 * @return the prerequisite relation that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	public PrerequisiteRelation deletePrerequisiteRelation(
		PrerequisiteRelation prerequisiteRelation);

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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.ted.prerequisite.model.impl.PrerequisiteRelationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.ted.prerequisite.model.impl.PrerequisiteRelationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public PrerequisiteRelation fetchPrerequisiteRelation(
		long prerequisiteRelationId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	 * Returns the prerequisite relation with the primary key.
	 *
	 * @param prerequisiteRelationId the primary key of the prerequisite relation
	 * @return the prerequisite relation
	 * @throws PortalException if a prerequisite relation with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PrerequisiteRelation getPrerequisiteRelation(
			long prerequisiteRelationId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<PrerequisiteRelation> getPrerequisiteRelation(
		long classNamePrerequisiteId, long classNameId, long classPK);

	/**
	 * Returns a range of all the prerequisite relations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.ted.prerequisite.model.impl.PrerequisiteRelationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of prerequisite relations
	 * @param end the upper bound of the range of prerequisite relations (not inclusive)
	 * @return the range of prerequisite relations
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<PrerequisiteRelation> getPrerequisiteRelations(
		int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<PrerequisiteRelation> getPrerequisiteRelations(
		long classNameId, long classPK);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<PrerequisiteRelation> getPrerequisiteRelations(
		long classNameId, long classPK, int start, int end);

	/**
	 * Returns the number of prerequisite relations.
	 *
	 * @return the number of prerequisite relations
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getPrerequisiteRelationsCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getPrerequisiteRelationsCount(long classNameId, long classPK);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Prerequisite> getPrerequisites(long classNameId, long classPK);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Prerequisite> getPrerequisites(
		long classNameId, long classPK, int start, int end);

	/**
	 * Updates the prerequisite relation in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param prerequisiteRelation the prerequisite relation
	 * @return the prerequisite relation that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	public PrerequisiteRelation updatePrerequisiteRelation(
		PrerequisiteRelation prerequisiteRelation);

}