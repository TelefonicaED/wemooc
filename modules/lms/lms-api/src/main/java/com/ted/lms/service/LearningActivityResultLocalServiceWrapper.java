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

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link LearningActivityResultLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see LearningActivityResultLocalService
 * @generated
 */
@ProviderType
public class LearningActivityResultLocalServiceWrapper
	implements LearningActivityResultLocalService,
		ServiceWrapper<LearningActivityResultLocalService> {
	public LearningActivityResultLocalServiceWrapper(
		LearningActivityResultLocalService learningActivityResultLocalService) {
		_learningActivityResultLocalService = learningActivityResultLocalService;
	}

	/**
	* Adds the learning activity result to the database. Also notifies the appropriate model listeners.
	*
	* @param learningActivityResult the learning activity result
	* @return the learning activity result that was added
	*/
	@Override
	public com.ted.lms.model.LearningActivityResult addLearningActivityResult(
		com.ted.lms.model.LearningActivityResult learningActivityResult) {
		return _learningActivityResultLocalService.addLearningActivityResult(learningActivityResult);
	}

	@Override
	public long countStudentFinished(long actId, long companyId,
		long courseGroupId) {
		return _learningActivityResultLocalService.countStudentFinished(actId,
			companyId, courseGroupId);
	}

	/**
	* Creates a new learning activity result with the primary key. Does not add the learning activity result to the database.
	*
	* @param larId the primary key for the new learning activity result
	* @return the new learning activity result
	*/
	@Override
	public com.ted.lms.model.LearningActivityResult createLearningActivityResult(
		long larId) {
		return _learningActivityResultLocalService.createLearningActivityResult(larId);
	}

	/**
	* Deletes the learning activity result from the database. Also notifies the appropriate model listeners.
	*
	* @param learningActivityResult the learning activity result
	* @return the learning activity result that was removed
	*/
	@Override
	public com.ted.lms.model.LearningActivityResult deleteLearningActivityResult(
		com.ted.lms.model.LearningActivityResult learningActivityResult) {
		return _learningActivityResultLocalService.deleteLearningActivityResult(learningActivityResult);
	}

	/**
	* Deletes the learning activity result with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param larId the primary key of the learning activity result
	* @return the learning activity result that was removed
	* @throws PortalException if a learning activity result with the primary key could not be found
	*/
	@Override
	public com.ted.lms.model.LearningActivityResult deleteLearningActivityResult(
		long larId) throws com.liferay.portal.kernel.exception.PortalException {
		return _learningActivityResultLocalService.deleteLearningActivityResult(larId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _learningActivityResultLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _learningActivityResultLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _learningActivityResultLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ted.lms.model.impl.LearningActivityResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _learningActivityResultLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ted.lms.model.impl.LearningActivityResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _learningActivityResultLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _learningActivityResultLocalService.dynamicQueryCount(dynamicQuery);
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
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _learningActivityResultLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.ted.lms.model.LearningActivityResult fetchLearningActivityResult(
		long larId) {
		return _learningActivityResultLocalService.fetchLearningActivityResult(larId);
	}

	/**
	* Returns the learning activity result matching the UUID and group.
	*
	* @param uuid the learning activity result's UUID
	* @param groupId the primary key of the group
	* @return the matching learning activity result, or <code>null</code> if a matching learning activity result could not be found
	*/
	@Override
	public com.ted.lms.model.LearningActivityResult fetchLearningActivityResultByUuidAndGroupId(
		String uuid, long groupId) {
		return _learningActivityResultLocalService.fetchLearningActivityResultByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _learningActivityResultLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _learningActivityResultLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _learningActivityResultLocalService.getIndexableActionableDynamicQuery();
	}

	@Override
	public int getLearningActivityCountByActId(long actId) {
		return _learningActivityResultLocalService.getLearningActivityCountByActId(actId);
	}

	/**
	* Returns the learning activity result with the primary key.
	*
	* @param larId the primary key of the learning activity result
	* @return the learning activity result
	* @throws PortalException if a learning activity result with the primary key could not be found
	*/
	@Override
	public com.ted.lms.model.LearningActivityResult getLearningActivityResult(
		long larId) throws com.liferay.portal.kernel.exception.PortalException {
		return _learningActivityResultLocalService.getLearningActivityResult(larId);
	}

	@Override
	public com.ted.lms.model.LearningActivityResult getLearningActivityResult(
		long actId, long userId) {
		return _learningActivityResultLocalService.getLearningActivityResult(actId,
			userId);
	}

	/**
	* Returns the learning activity result matching the UUID and group.
	*
	* @param uuid the learning activity result's UUID
	* @param groupId the primary key of the group
	* @return the matching learning activity result
	* @throws PortalException if a matching learning activity result could not be found
	*/
	@Override
	public com.ted.lms.model.LearningActivityResult getLearningActivityResultByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _learningActivityResultLocalService.getLearningActivityResultByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns a range of all the learning activity results.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ted.lms.model.impl.LearningActivityResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of learning activity results
	* @param end the upper bound of the range of learning activity results (not inclusive)
	* @return the range of learning activity results
	*/
	@Override
	public java.util.List<com.ted.lms.model.LearningActivityResult> getLearningActivityResults(
		int start, int end) {
		return _learningActivityResultLocalService.getLearningActivityResults(start,
			end);
	}

	/**
	* Returns all the learning activity results matching the UUID and company.
	*
	* @param uuid the UUID of the learning activity results
	* @param companyId the primary key of the company
	* @return the matching learning activity results, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.ted.lms.model.LearningActivityResult> getLearningActivityResultsByUuidAndCompanyId(
		String uuid, long companyId) {
		return _learningActivityResultLocalService.getLearningActivityResultsByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of learning activity results matching the UUID and company.
	*
	* @param uuid the UUID of the learning activity results
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of learning activity results
	* @param end the upper bound of the range of learning activity results (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching learning activity results, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.ted.lms.model.LearningActivityResult> getLearningActivityResultsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.ted.lms.model.LearningActivityResult> orderByComparator) {
		return _learningActivityResultLocalService.getLearningActivityResultsByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of learning activity results.
	*
	* @return the number of learning activity results
	*/
	@Override
	public int getLearningActivityResultsCount() {
		return _learningActivityResultLocalService.getLearningActivityResultsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _learningActivityResultLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _learningActivityResultLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public java.util.List<com.ted.lms.model.LearningActivityResult> getRequiredLearningActivityResults(
		long groupId, long userId) {
		return _learningActivityResultLocalService.getRequiredLearningActivityResults(groupId,
			userId);
	}

	@Override
	public int getRequiredLearningActivityResultsByModuleCount(long moduleId,
		long userId) {
		return _learningActivityResultLocalService.getRequiredLearningActivityResultsByModuleCount(moduleId,
			userId);
	}

	@Override
	public boolean hasUserPassed(long actId, long userId) {
		return _learningActivityResultLocalService.hasUserPassed(actId, userId);
	}

	/**
	* Updates the learning activity result in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param learningActivityResult the learning activity result
	* @return the learning activity result that was updated
	*/
	@Override
	public com.ted.lms.model.LearningActivityResult updateLearningActivityResult(
		com.ted.lms.model.LearningActivityResult learningActivityResult) {
		return _learningActivityResultLocalService.updateLearningActivityResult(learningActivityResult);
	}

	@Override
	public com.ted.lms.model.LearningActivityResult updateLearningActivityResult(
		com.ted.lms.model.LearningActivityTry learningActivityTry,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _learningActivityResultLocalService.updateLearningActivityResult(learningActivityTry,
			serviceContext);
	}

	@Override
	public LearningActivityResultLocalService getWrappedService() {
		return _learningActivityResultLocalService;
	}

	@Override
	public void setWrappedService(
		LearningActivityResultLocalService learningActivityResultLocalService) {
		_learningActivityResultLocalService = learningActivityResultLocalService;
	}

	private LearningActivityResultLocalService _learningActivityResultLocalService;
}