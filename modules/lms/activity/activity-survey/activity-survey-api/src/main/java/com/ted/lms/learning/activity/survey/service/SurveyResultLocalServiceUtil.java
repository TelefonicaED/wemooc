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

import org.osgi.annotation.versioning.ProviderType;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for SurveyResult. This utility wraps
 * <code>com.ted.lms.learning.activity.survey.service.impl.SurveyResultLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see SurveyResultLocalService
 * @generated
 */
@ProviderType
public class SurveyResultLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.ted.lms.learning.activity.survey.service.impl.SurveyResultLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.ted.lms.learning.activity.survey.model.SurveyResult
		addSurveyResult(
			long actId, long latId, long userId, long questionId, long answerId,
			String textAnswer) {

		return getService().addSurveyResult(
			actId, latId, userId, questionId, answerId, textAnswer);
	}

	/**
	 * Adds the survey result to the database. Also notifies the appropriate model listeners.
	 *
	 * @param surveyResult the survey result
	 * @return the survey result that was added
	 */
	public static com.ted.lms.learning.activity.survey.model.SurveyResult
		addSurveyResult(
			com.ted.lms.learning.activity.survey.model.SurveyResult
				surveyResult) {

		return getService().addSurveyResult(surveyResult);
	}

	public static long countAnswerByQuestionId(
		long questionId, long companyId, long courseGroupCreatedId) {

		return getService().countAnswerByQuestionId(
			questionId, companyId, courseGroupCreatedId);
	}

	public static long countAnswerByQuestionIdAnswerId(
		long questionId, long answerId, long companyId,
		long courseGroupCreatedId) {

		return getService().countAnswerByQuestionIdAnswerId(
			questionId, answerId, companyId, courseGroupCreatedId);
	}

	/**
	 * Creates a new survey result with the primary key. Does not add the survey result to the database.
	 *
	 * @param surveyResultId the primary key for the new survey result
	 * @return the new survey result
	 */
	public static com.ted.lms.learning.activity.survey.model.SurveyResult
		createSurveyResult(long surveyResultId) {

		return getService().createSurveyResult(surveyResultId);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			deletePersistedModel(
				com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the survey result with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param surveyResultId the primary key of the survey result
	 * @return the survey result that was removed
	 * @throws PortalException if a survey result with the primary key could not be found
	 */
	public static com.ted.lms.learning.activity.survey.model.SurveyResult
			deleteSurveyResult(long surveyResultId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteSurveyResult(surveyResultId);
	}

	/**
	 * Deletes the survey result from the database. Also notifies the appropriate model listeners.
	 *
	 * @param surveyResult the survey result
	 * @return the survey result that was removed
	 */
	public static com.ted.lms.learning.activity.survey.model.SurveyResult
		deleteSurveyResult(
			com.ted.lms.learning.activity.survey.model.SurveyResult
				surveyResult) {

		return getService().deleteSurveyResult(surveyResult);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery
		dynamicQuery() {

		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.ted.lms.learning.activity.survey.model.impl.SurveyResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.ted.lms.learning.activity.survey.model.impl.SurveyResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static java.io.File exportStatisticsAsFile(
			String applicationKey, long actId, java.util.Locale locale)
		throws Exception {

		return getService().exportStatisticsAsFile(
			applicationKey, actId, locale);
	}

	public static com.ted.lms.learning.activity.survey.model.SurveyResult
		fetchSurveyResult(long surveyResultId) {

		return getService().fetchSurveyResult(surveyResultId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the survey result with the primary key.
	 *
	 * @param surveyResultId the primary key of the survey result
	 * @return the survey result
	 * @throws PortalException if a survey result with the primary key could not be found
	 */
	public static com.ted.lms.learning.activity.survey.model.SurveyResult
			getSurveyResult(long surveyResultId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getSurveyResult(surveyResultId);
	}

	/**
	 * Returns a range of all the survey results.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.ted.lms.learning.activity.survey.model.impl.SurveyResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of survey results
	 * @param end the upper bound of the range of survey results (not inclusive)
	 * @return the range of survey results
	 */
	public static java.util.List
		<com.ted.lms.learning.activity.survey.model.SurveyResult>
			getSurveyResults(int start, int end) {

		return getService().getSurveyResults(start, end);
	}

	/**
	 * Returns the number of survey results.
	 *
	 * @return the number of survey results
	 */
	public static int getSurveyResultsCount() {
		return getService().getSurveyResultsCount();
	}

	/**
	 * Updates the survey result in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param surveyResult the survey result
	 * @return the survey result that was updated
	 */
	public static com.ted.lms.learning.activity.survey.model.SurveyResult
		updateSurveyResult(
			com.ted.lms.learning.activity.survey.model.SurveyResult
				surveyResult) {

		return getService().updateSurveyResult(surveyResult);
	}

	public static SurveyResultLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<SurveyResultLocalService, SurveyResultLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(SurveyResultLocalService.class);

		ServiceTracker<SurveyResultLocalService, SurveyResultLocalService>
			serviceTracker =
				new ServiceTracker
					<SurveyResultLocalService, SurveyResultLocalService>(
						bundle.getBundleContext(),
						SurveyResultLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}