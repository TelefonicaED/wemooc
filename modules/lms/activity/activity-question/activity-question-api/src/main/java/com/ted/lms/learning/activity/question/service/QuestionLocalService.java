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
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.ted.lms.learning.activity.question.model.Question;

import java.io.Serializable;

import java.util.List;

import javax.portlet.ActionRequest;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the local service interface for Question. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see QuestionLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface QuestionLocalService
	extends BaseLocalService, PersistedModelLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link QuestionLocalServiceUtil} to access the question local service. Add custom service methods to <code>com.ted.lms.learning.activity.question.service.impl.QuestionLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public Question addQuestion(
			long userId, long groupId, long actId, String questionText,
			long questionType, boolean penalize, ServiceContext serviceContext)
		throws PortalException;

	/**
	 * Adds the question to the database. Also notifies the appropriate model listeners.
	 *
	 * @param question the question
	 * @return the question that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	public Question addQuestion(Question question);

	public Question copyQuestion(
			long userId, long groupId, long actId, Question oldQuestion,
			ServiceContext serviceContext)
		throws Exception;

	public void copyQuestionImages(Question oldQuestion, Question newQuestion)
		throws Exception;

	/**
	 * Creates a new question with the primary key. Does not add the question to the database.
	 *
	 * @param questionId the primary key for the new question
	 * @return the new question
	 */
	@Transactional(enabled = false)
	public Question createQuestion(long questionId);

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	/**
	 * Deletes the question with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param questionId the primary key of the question
	 * @return the question that was removed
	 * @throws PortalException if a question with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	public Question deleteQuestion(long questionId) throws PortalException;

	/**
	 * Deletes the question from the database. Also notifies the appropriate model listeners.
	 *
	 * @param question the question
	 * @return the question that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	public Question deleteQuestion(Question question);

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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.ted.lms.learning.activity.question.model.impl.QuestionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.ted.lms.learning.activity.question.model.impl.QuestionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public Question fetchQuestion(long questionId);

	/**
	 * Returns the question matching the UUID and group.
	 *
	 * @param uuid the question's UUID
	 * @param groupId the primary key of the group
	 * @return the matching question, or <code>null</code> if a matching question could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Question fetchQuestionByUuidAndGroupId(String uuid, long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext, long actId);

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
	 * Returns the question with the primary key.
	 *
	 * @param questionId the primary key of the question
	 * @return the question
	 * @throws PortalException if a question with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Question getQuestion(long questionId) throws PortalException;

	/**
	 * Returns the question matching the UUID and group.
	 *
	 * @param uuid the question's UUID
	 * @param groupId the primary key of the group
	 * @return the matching question
	 * @throws PortalException if a matching question could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Question getQuestionByUuidAndGroupId(String uuid, long groupId)
		throws PortalException;

	/**
	 * Returns a range of all the questions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.ted.lms.learning.activity.question.model.impl.QuestionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of questions
	 * @param end the upper bound of the range of questions (not inclusive)
	 * @return the range of questions
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Question> getQuestions(int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Question> getQuestions(long actId);

	/**
	 * Returns all the questions matching the UUID and company.
	 *
	 * @param uuid the UUID of the questions
	 * @param companyId the primary key of the company
	 * @return the matching questions, or an empty list if no matches were found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Question> getQuestionsByUuidAndCompanyId(
		String uuid, long companyId);

	/**
	 * Returns a range of questions matching the UUID and company.
	 *
	 * @param uuid the UUID of the questions
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of questions
	 * @param end the upper bound of the range of questions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching questions, or an empty list if no matches were found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Question> getQuestionsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Question> orderByComparator);

	/**
	 * Returns the number of questions.
	 *
	 * @return the number of questions
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getQuestionsCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getQuestionsCount(long actId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Question> getQuestionsOrder(long actId);

	public void saveQuestions(ActionRequest actionRequest, long actId)
		throws PortalException;

	public Question updateQuestion(
			long userId, long questionId, String questionText, boolean penalize)
		throws PortalException;

	/**
	 * Updates the question in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param question the question
	 * @return the question that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	public Question updateQuestion(Question question);

}