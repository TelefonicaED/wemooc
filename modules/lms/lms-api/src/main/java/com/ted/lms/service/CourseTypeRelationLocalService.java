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

package com.ted.lms.service;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.LayoutSetPrototype;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.ted.lms.model.CalificationTypeFactory;
import com.ted.lms.model.CourseEvalFactory;
import com.ted.lms.model.CourseTypeRelation;
import com.ted.lms.model.InscriptionTypeFactory;
import com.ted.lms.model.LearningActivityTypeFactory;
import com.ted.lms.service.persistence.CourseTypeRelationPK;

import java.io.Serializable;

import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the local service interface for CourseTypeRelation. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see CourseTypeRelationLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface CourseTypeRelationLocalService
	extends BaseLocalService, PersistedModelLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CourseTypeRelationLocalServiceUtil} to access the course type relation local service. Add custom service methods to <code>com.ted.lms.service.impl.CourseTypeRelationLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	 * Adds the course type relation to the database. Also notifies the appropriate model listeners.
	 *
	 * @param courseTypeRelation the course type relation
	 * @return the course type relation that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	public CourseTypeRelation addCourseTypeRelation(
		CourseTypeRelation courseTypeRelation);

	public void addCourseTypeRelation(
		long courseTypeId, long classNameId, long[] classPKs);

	/**
	 * Creates a new course type relation with the primary key. Does not add the course type relation to the database.
	 *
	 * @param courseTypeRelationPK the primary key for the new course type relation
	 * @return the new course type relation
	 */
	@Transactional(enabled = false)
	public CourseTypeRelation createCourseTypeRelation(
		CourseTypeRelationPK courseTypeRelationPK);

	/**
	 * Deletes the course type relation from the database. Also notifies the appropriate model listeners.
	 *
	 * @param courseTypeRelation the course type relation
	 * @return the course type relation that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	public CourseTypeRelation deleteCourseTypeRelation(
		CourseTypeRelation courseTypeRelation);

	/**
	 * Deletes the course type relation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param courseTypeRelationPK the primary key of the course type relation
	 * @return the course type relation that was removed
	 * @throws PortalException if a course type relation with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	public CourseTypeRelation deleteCourseTypeRelation(
			CourseTypeRelationPK courseTypeRelationPK)
		throws PortalException;

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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.ted.lms.model.impl.CourseTypeRelationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.ted.lms.model.impl.CourseTypeRelationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public CourseTypeRelation fetchCourseTypeRelation(
		CourseTypeRelationPK courseTypeRelationPK);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Long> getCalificationTypeIds(long courseTypeId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CalificationTypeFactory> getCalificationTypes(
		long courseTypeId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Long> getCourseEvalIds(long courseTypeId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CourseEvalFactory> getCourseEvals(long courseTypeId);

	/**
	 * Returns the course type relation with the primary key.
	 *
	 * @param courseTypeRelationPK the primary key of the course type relation
	 * @return the course type relation
	 * @throws PortalException if a course type relation with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CourseTypeRelation getCourseTypeRelation(
			CourseTypeRelationPK courseTypeRelationPK)
		throws PortalException;

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CourseTypeRelation> getCourseTypeRelations(int start, int end);

	/**
	 * Returns the number of course type relations.
	 *
	 * @return the number of course type relations
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCourseTypeRelationsCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Long> getInscriptionTypeIds(long courseTypeId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<InscriptionTypeFactory> getInscriptionTypes(long courseTypeId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Long> getLearningActivityTypeIds(long courseTypeId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LearningActivityTypeFactory> getLearningActivityTypes(
		long courseTypeId);

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

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Long> getTemplateIds(long courseTypeId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LayoutSetPrototype> getTemplates(long courseTypeId);

	/**
	 * Updates the course type relation in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param courseTypeRelation the course type relation
	 * @return the course type relation that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	public CourseTypeRelation updateCourseTypeRelation(
		CourseTypeRelation courseTypeRelation);

}