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

import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.Module;

import java.io.InputStream;
import java.io.Serializable;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the local service interface for LearningActivity. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see LearningActivityLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface LearningActivityLocalService
	extends BaseLocalService, PersistedModelLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LearningActivityLocalServiceUtil} to access the learning activity local service. Add custom service methods to <code>com.ted.lms.service.impl.LearningActivityLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public FileEntry addAttachment(
			long userId, LearningActivity activity, String fileName,
			InputStream inputStream, String mimeType)
		throws PortalException;

	/**
	 * Adds the learning activity to the database. Also notifies the appropriate model listeners.
	 *
	 * @param learningActivity the learning activity
	 * @return the learning activity that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	public LearningActivity addLearningActivity(
		LearningActivity learningActivity);

	public LearningActivity addLearningActivity(
			long userId, long groupId, long moduleId, long type,
			Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
			boolean useStartExecutionDateCourse, int startDateMonth,
			int startDateDay, int startDateYear, int startDateHour,
			int startDateMinute, boolean useEndExecutionDateCourse,
			int endDateMonth, int endDateDay, int endDateYear, int endDateHour,
			int endDateMinute, boolean required, int tries,
			double passPuntuation, Map<Locale, String> feedbackCorrectMap,
			Map<Locale, String> feedbackNoCorrectMap, boolean commentsActivated,
			String[] selectedFileNames, ServiceContext serviceContext)
		throws PortalException;

	public LearningActivity addLearningActivity(
			long userId, long groupId, long moduleId, long type,
			Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
			Date startDate, Date endDate, boolean required, int tries,
			double passPuntuation, Map<Locale, String> feedbackCorrectMap,
			Map<Locale, String> feedbackNoCorrectMap, boolean commentsActivated,
			String[] selectedFileNames, ServiceContext serviceContext)
		throws PortalException;

	@Indexable(type = IndexableType.REINDEX)
	public LearningActivity addLearningActivity(
			long userId, long groupId, long moduleId,
			Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
			long typeId, Date startDate, Date endDate, int tries,
			double passPuntuation, long priority, String extraContent,
			Map<Locale, String> feedbackCorrectMap,
			Map<Locale, String> feedbackNoCorrectMap, boolean required,
			boolean commentsActivated, String[] selectedFileNames,
			ServiceContext serviceContext)
		throws PortalException;

	public void changeVisibility(long userId, long actId)
		throws PortalException;

	public LearningActivity copyActivity(
			long userId, LearningActivity oldActivity, Module newModule,
			ServiceContext serviceContext)
		throws Exception;

	/**
	 * Creates a new learning activity with the primary key. Does not add the learning activity to the database.
	 *
	 * @param actId the primary key for the new learning activity
	 * @return the new learning activity
	 */
	@Transactional(enabled = false)
	public LearningActivity createLearningActivity(long actId);

	public void deleteLearningActivities(long moduleId);

	/**
	 * Deletes the learning activity from the database. Also notifies the appropriate model listeners.
	 *
	 * @param learningActivity the learning activity
	 * @return the learning activity that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	public LearningActivity deleteLearningActivity(
		LearningActivity learningActivity);

	/**
	 * Deletes the learning activity with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param actId the primary key of the learning activity
	 * @return the learning activity that was removed
	 * @throws PortalException if a learning activity with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	public LearningActivity deleteLearningActivity(long actId)
		throws PortalException;

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	public boolean deleteTries(
		long userId, long actId, long studentId, boolean deleteOnlyFailed);

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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.ted.lms.model.impl.LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.ted.lms.model.impl.LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public void executeDeleteTries(
			long userId, long groupId, long actId, long studentId,
			boolean deleteOnlyFailed, ServiceContext serviceContext)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LearningActivity fetchLearningActivity(long actId);

	/**
	 * Returns the learning activity matching the UUID and group.
	 *
	 * @param uuid the learning activity's UUID
	 * @param groupId the primary key of the group
	 * @return the matching learning activity, or <code>null</code> if a matching learning activity could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LearningActivity fetchLearningActivityByUuidAndGroupId(
		String uuid, long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	 * Returns a range of all the learning activities.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.ted.lms.model.impl.LearningActivityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @return the range of learning activities
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LearningActivity> getLearningActivities(int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LearningActivity> getLearningActivities(long moduleId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LearningActivity> getLearningActivitiesByTypeId(long typeId);

	/**
	 * Returns all the learning activities matching the UUID and company.
	 *
	 * @param uuid the UUID of the learning activities
	 * @param companyId the primary key of the company
	 * @return the matching learning activities, or an empty list if no matches were found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LearningActivity> getLearningActivitiesByUuidAndCompanyId(
		String uuid, long companyId);

	/**
	 * Returns a range of learning activities matching the UUID and company.
	 *
	 * @param uuid the UUID of the learning activities
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of learning activities
	 * @param end the upper bound of the range of learning activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching learning activities, or an empty list if no matches were found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LearningActivity> getLearningActivitiesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<LearningActivity> orderByComparator);

	/**
	 * Returns the number of learning activities.
	 *
	 * @return the number of learning activities
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getLearningActivitiesCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LearningActivity> getLearningActivitiesNotTypeId(
		long moduleId, long typeId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getLearningActivitiesOfModuleCount(long moduleId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LearningActivity> getLearningActivitiesWithoutModule(
		long groupId);

	/**
	 * Returns the learning activity with the primary key.
	 *
	 * @param actId the primary key of the learning activity
	 * @return the learning activity
	 * @throws PortalException if a learning activity with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LearningActivity getLearningActivity(long actId)
		throws PortalException;

	/**
	 * Returns the learning activity matching the UUID and group.
	 *
	 * @param uuid the learning activity's UUID
	 * @param groupId the primary key of the group
	 * @return the matching learning activity
	 * @throws PortalException if a matching learning activity could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LearningActivity getLearningActivityByUuidAndGroupId(
			String uuid, long groupId)
		throws PortalException;

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
	public String[] getPrerequisiteActivities(long companyId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LearningActivity> getRequiredLearningActivitiesOfGroup(
		long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LearningActivity> getRequiredLearningActivitiesOfModule(
		long moduleId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasDeleteTriesInProgress(long actId, long groupId);

	public LearningActivity moveDownLearningActivity(long userId, long actId)
		throws PortalException;

	@Indexable(type = IndexableType.REINDEX)
	public LearningActivity moveLearningActivityToTrash(
			long userId, LearningActivity activity)
		throws PortalException;

	public LearningActivity moveLearningActivityToTrash(long userId, long actId)
		throws PortalException;

	public LearningActivity moveUpLearningActivity(long userId, long actId)
		throws PortalException;

	public void updateAsset(
			long userId, LearningActivity activity, long[] assetCategoryIds,
			String[] assetTagNames, long[] assetLinkEntryIds, Double priority)
		throws PortalException;

	/**
	 * Updates the learning activity in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param learningActivity the learning activity
	 * @return the learning activity that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	public LearningActivity updateLearningActivity(
		LearningActivity learningActivity);

	public LearningActivity updateLearningActivity(
		long userId, LearningActivity activity);

	@Indexable(type = IndexableType.REINDEX)
	public LearningActivity updateLearningActivity(
			long userId, long actId, long moduleId,
			Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
			long typeId, Date startDate, Date endDate, int tries,
			double passPuntuation, long priority, String extraContent,
			Map<Locale, String> feedbackCorrectMap,
			Map<Locale, String> feedbackNoCorrectMap, boolean required,
			boolean commentsActivated, String[] selectedFileNames,
			long[] removeFileEntryIds, ServiceContext serviceContext)
		throws PortalException;

	public LearningActivity updateLearningActivity(
			long userId, long actId, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap,
			boolean useStartExecutionDateCourse, int startDateMonth,
			int startDateDay, int startDateYear, int startDateHour,
			int startDateMinute, boolean useEndExecutionDateCourse,
			int endDateMonth, int endDateDay, int endDateYear, int endDateHour,
			int endDateMinute, boolean required, int tries,
			double passPuntuation, Map<Locale, String> feedbackCorrectMap,
			Map<Locale, String> feedbackNoCorrectMap, boolean commentsActivated,
			String[] selectedFileNames, long[] removeFileEntryIds,
			ServiceContext serviceContext)
		throws PortalException;

	public LearningActivity updateLearningActivity(
			long userId, long actId, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap, Date startDate, Date endDate,
			int tries, double passPuntuation,
			Map<Locale, String> feedbackCorrectMap,
			Map<Locale, String> feedbackNoCorrectMap, boolean required,
			boolean commentsActivated, String[] selectedFileNames,
			long[] removeFileEntryIds, ServiceContext serviceContext)
		throws PortalException;

	@Indexable(type = IndexableType.REINDEX)
	public LearningActivity updateStatus(
			long userId, long actId, int status, ServiceContext serviceContext,
			Map<String, Serializable> workflowContext)
		throws PortalException;

}