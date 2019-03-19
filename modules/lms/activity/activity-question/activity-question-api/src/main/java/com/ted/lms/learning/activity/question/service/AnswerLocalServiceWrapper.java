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

package com.ted.lms.learning.activity.question.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AnswerLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see AnswerLocalService
 * @generated
 */
@ProviderType
public class AnswerLocalServiceWrapper implements AnswerLocalService,
	ServiceWrapper<AnswerLocalService> {
	public AnswerLocalServiceWrapper(AnswerLocalService answerLocalService) {
		_answerLocalService = answerLocalService;
	}

	/**
	* Adds the answer to the database. Also notifies the appropriate model listeners.
	*
	* @param answer the answer
	* @return the answer that was added
	*/
	@Override
	public com.ted.lms.learning.activity.question.model.Answer addAnswer(
		com.ted.lms.learning.activity.question.model.Answer answer) {
		return _answerLocalService.addAnswer(answer);
	}

	@Override
	public com.ted.lms.learning.activity.question.model.Answer addAnswer(
		long questionId, long actId, String answerText,
		java.util.Map<java.util.Locale, String> feedbackCorrectMap,
		java.util.Map<java.util.Locale, String> feedbackIncorrectMap,
		boolean correct,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {
		return _answerLocalService.addAnswer(questionId, actId, answerText,
			feedbackCorrectMap, feedbackIncorrectMap, correct, serviceContext);
	}

	/**
	* Creates a new answer with the primary key. Does not add the answer to the database.
	*
	* @param answerId the primary key for the new answer
	* @return the new answer
	*/
	@Override
	public com.ted.lms.learning.activity.question.model.Answer createAnswer(
		long answerId) {
		return _answerLocalService.createAnswer(answerId);
	}

	/**
	* Deletes the answer from the database. Also notifies the appropriate model listeners.
	*
	* @param answer the answer
	* @return the answer that was removed
	*/
	@Override
	public com.ted.lms.learning.activity.question.model.Answer deleteAnswer(
		com.ted.lms.learning.activity.question.model.Answer answer) {
		return _answerLocalService.deleteAnswer(answer);
	}

	/**
	* Deletes the answer with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param answerId the primary key of the answer
	* @return the answer that was removed
	* @throws PortalException if a answer with the primary key could not be found
	*/
	@Override
	public com.ted.lms.learning.activity.question.model.Answer deleteAnswer(
		long answerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _answerLocalService.deleteAnswer(answerId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _answerLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _answerLocalService.dynamicQuery();
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
		return _answerLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ted.lms.learning.activity.question.model.impl.AnswerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _answerLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ted.lms.learning.activity.question.model.impl.AnswerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _answerLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _answerLocalService.dynamicQueryCount(dynamicQuery);
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
		return _answerLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.ted.lms.learning.activity.question.model.Answer fetchAnswer(
		long answerId) {
		return _answerLocalService.fetchAnswer(answerId);
	}

	/**
	* Returns the answer matching the UUID and group.
	*
	* @param uuid the answer's UUID
	* @param groupId the primary key of the group
	* @return the matching answer, or <code>null</code> if a matching answer could not be found
	*/
	@Override
	public com.ted.lms.learning.activity.question.model.Answer fetchAnswerByUuidAndGroupId(
		String uuid, long groupId) {
		return _answerLocalService.fetchAnswerByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _answerLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the answer with the primary key.
	*
	* @param answerId the primary key of the answer
	* @return the answer
	* @throws PortalException if a answer with the primary key could not be found
	*/
	@Override
	public com.ted.lms.learning.activity.question.model.Answer getAnswer(
		long answerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _answerLocalService.getAnswer(answerId);
	}

	/**
	* Returns the answer matching the UUID and group.
	*
	* @param uuid the answer's UUID
	* @param groupId the primary key of the group
	* @return the matching answer
	* @throws PortalException if a matching answer could not be found
	*/
	@Override
	public com.ted.lms.learning.activity.question.model.Answer getAnswerByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _answerLocalService.getAnswerByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the answers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ted.lms.learning.activity.question.model.impl.AnswerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of answers
	* @param end the upper bound of the range of answers (not inclusive)
	* @return the range of answers
	*/
	@Override
	public java.util.List<com.ted.lms.learning.activity.question.model.Answer> getAnswers(
		int start, int end) {
		return _answerLocalService.getAnswers(start, end);
	}

	@Override
	public java.util.List<com.ted.lms.learning.activity.question.model.Answer> getAnswersByQuestionId(
		long questionId) {
		return _answerLocalService.getAnswersByQuestionId(questionId);
	}

	/**
	* Returns all the answers matching the UUID and company.
	*
	* @param uuid the UUID of the answers
	* @param companyId the primary key of the company
	* @return the matching answers, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.ted.lms.learning.activity.question.model.Answer> getAnswersByUuidAndCompanyId(
		String uuid, long companyId) {
		return _answerLocalService.getAnswersByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of answers matching the UUID and company.
	*
	* @param uuid the UUID of the answers
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of answers
	* @param end the upper bound of the range of answers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching answers, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.ted.lms.learning.activity.question.model.Answer> getAnswersByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.ted.lms.learning.activity.question.model.Answer> orderByComparator) {
		return _answerLocalService.getAnswersByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of answers.
	*
	* @return the number of answers
	*/
	@Override
	public int getAnswersCount() {
		return _answerLocalService.getAnswersCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _answerLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _answerLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _answerLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _answerLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the answer in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param answer the answer
	* @return the answer that was updated
	*/
	@Override
	public com.ted.lms.learning.activity.question.model.Answer updateAnswer(
		com.ted.lms.learning.activity.question.model.Answer answer) {
		return _answerLocalService.updateAnswer(answer);
	}

	@Override
	public com.ted.lms.learning.activity.question.model.Answer updateAnswer(
		long answerId, String answerText,
		java.util.Map<java.util.Locale, String> feedbackCorrectMap,
		java.util.Map<java.util.Locale, String> feedbackIncorrectMap,
		boolean correct) {
		return _answerLocalService.updateAnswer(answerId, answerText,
			feedbackCorrectMap, feedbackIncorrectMap, correct);
	}

	@Override
	public AnswerLocalService getWrappedService() {
		return _answerLocalService;
	}

	@Override
	public void setWrappedService(AnswerLocalService answerLocalService) {
		_answerLocalService = answerLocalService;
	}

	private AnswerLocalService _answerLocalService;
}