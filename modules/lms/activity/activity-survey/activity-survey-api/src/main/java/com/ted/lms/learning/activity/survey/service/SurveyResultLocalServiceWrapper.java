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

package com.ted.lms.learning.activity.survey.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SurveyResultLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see SurveyResultLocalService
 * @generated
 */
@ProviderType
public class SurveyResultLocalServiceWrapper implements SurveyResultLocalService,
	ServiceWrapper<SurveyResultLocalService> {
	public SurveyResultLocalServiceWrapper(
		SurveyResultLocalService surveyResultLocalService) {
		_surveyResultLocalService = surveyResultLocalService;
	}

	@Override
	public com.ted.lms.learning.activity.survey.model.SurveyResult addSurveyResult(
		long actId, long latId, long userId, long questionId, long answerId,
		String textAnswer) {
		return _surveyResultLocalService.addSurveyResult(actId, latId, userId,
			questionId, answerId, textAnswer);
	}

	/**
	* Adds the survey result to the database. Also notifies the appropriate model listeners.
	*
	* @param surveyResult the survey result
	* @return the survey result that was added
	*/
	@Override
	public com.ted.lms.learning.activity.survey.model.SurveyResult addSurveyResult(
		com.ted.lms.learning.activity.survey.model.SurveyResult surveyResult) {
		return _surveyResultLocalService.addSurveyResult(surveyResult);
	}

	@Override
	public long countAnswerByQuestionId(long questionId, long companyId,
		long courseGroupCreatedId) {
		return _surveyResultLocalService.countAnswerByQuestionId(questionId,
			companyId, courseGroupCreatedId);
	}

	@Override
	public long countAnswerByQuestionIdAnswerId(long questionId, long answerId,
		long companyId, long courseGroupCreatedId) {
		return _surveyResultLocalService.countAnswerByQuestionIdAnswerId(questionId,
			answerId, companyId, courseGroupCreatedId);
	}

	/**
	* Creates a new survey result with the primary key. Does not add the survey result to the database.
	*
	* @param surveyResultId the primary key for the new survey result
	* @return the new survey result
	*/
	@Override
	public com.ted.lms.learning.activity.survey.model.SurveyResult createSurveyResult(
		long surveyResultId) {
		return _surveyResultLocalService.createSurveyResult(surveyResultId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _surveyResultLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the survey result with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param surveyResultId the primary key of the survey result
	* @return the survey result that was removed
	* @throws PortalException if a survey result with the primary key could not be found
	*/
	@Override
	public com.ted.lms.learning.activity.survey.model.SurveyResult deleteSurveyResult(
		long surveyResultId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _surveyResultLocalService.deleteSurveyResult(surveyResultId);
	}

	/**
	* Deletes the survey result from the database. Also notifies the appropriate model listeners.
	*
	* @param surveyResult the survey result
	* @return the survey result that was removed
	*/
	@Override
	public com.ted.lms.learning.activity.survey.model.SurveyResult deleteSurveyResult(
		com.ted.lms.learning.activity.survey.model.SurveyResult surveyResult) {
		return _surveyResultLocalService.deleteSurveyResult(surveyResult);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _surveyResultLocalService.dynamicQuery();
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
		return _surveyResultLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ted.lms.learning.activity.survey.model.impl.SurveyResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _surveyResultLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ted.lms.learning.activity.survey.model.impl.SurveyResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _surveyResultLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
		return _surveyResultLocalService.dynamicQueryCount(dynamicQuery);
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
		return _surveyResultLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public java.io.File exportStatisticsAsFile(String applicationKey,
		long actId, java.util.Locale locale) throws Exception {
		return _surveyResultLocalService.exportStatisticsAsFile(applicationKey,
			actId, locale);
	}

	@Override
	public com.ted.lms.learning.activity.survey.model.SurveyResult fetchSurveyResult(
		long surveyResultId) {
		return _surveyResultLocalService.fetchSurveyResult(surveyResultId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _surveyResultLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _surveyResultLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _surveyResultLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _surveyResultLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the survey result with the primary key.
	*
	* @param surveyResultId the primary key of the survey result
	* @return the survey result
	* @throws PortalException if a survey result with the primary key could not be found
	*/
	@Override
	public com.ted.lms.learning.activity.survey.model.SurveyResult getSurveyResult(
		long surveyResultId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _surveyResultLocalService.getSurveyResult(surveyResultId);
	}

	/**
	* Returns a range of all the survey results.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ted.lms.learning.activity.survey.model.impl.SurveyResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of survey results
	* @param end the upper bound of the range of survey results (not inclusive)
	* @return the range of survey results
	*/
	@Override
	public java.util.List<com.ted.lms.learning.activity.survey.model.SurveyResult> getSurveyResults(
		int start, int end) {
		return _surveyResultLocalService.getSurveyResults(start, end);
	}

	/**
	* Returns the number of survey results.
	*
	* @return the number of survey results
	*/
	@Override
	public int getSurveyResultsCount() {
		return _surveyResultLocalService.getSurveyResultsCount();
	}

	/**
	* Updates the survey result in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param surveyResult the survey result
	* @return the survey result that was updated
	*/
	@Override
	public com.ted.lms.learning.activity.survey.model.SurveyResult updateSurveyResult(
		com.ted.lms.learning.activity.survey.model.SurveyResult surveyResult) {
		return _surveyResultLocalService.updateSurveyResult(surveyResult);
	}

	@Override
	public SurveyResultLocalService getWrappedService() {
		return _surveyResultLocalService;
	}

	@Override
	public void setWrappedService(
		SurveyResultLocalService surveyResultLocalService) {
		_surveyResultLocalService = surveyResultLocalService;
	}

	private SurveyResultLocalService _surveyResultLocalService;
}