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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import com.ted.lms.model.LearningActivity;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the remote service interface for LearningActivity. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see LearningActivityServiceUtil
 * @generated
 */
@AccessControlled
@JSONWebService
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface LearningActivityService extends BaseService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LearningActivityServiceUtil} to access the learning activity remote service. Add custom service methods to <code>com.ted.lms.service.impl.LearningActivityServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public LearningActivity addLearningActivity(
			long groupId, long moduleId, long type,
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

	public void changeVisibility(long actId) throws PortalException;

	public LearningActivity deleteLearningActivity(long actId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LearningActivity> getActivities(long moduleId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LearningActivity> getActivitiesExcluded(
		long moduleId, long actId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LearningActivity> getActivitiesNotTypeId(
		long moduleId, long typeId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LearningActivity getFirstLearningActivity(
			long groupId, long moduleId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LearningActivity getLearningActivity(long actId)
		throws PortalException, PrincipalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LearningActivity getNextLearningActivity(LearningActivity activity)
		throws PortalException;

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LearningActivity getPreviousLearningActivity(
			LearningActivity activity)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public String[] getTempFileNames(long groupId) throws PortalException;

	public LearningActivity moveDownLearningActivity(long actId)
		throws PortalException;

	public LearningActivity moveLearningActivityToTrash(long actId)
		throws PortalException;

	public LearningActivity moveUpLearningActivity(long actId)
		throws PortalException;

	public LearningActivity updateLearningActivity(LearningActivity activity)
		throws PortalException;

	public LearningActivity updateLearningActivity(
			long actId, Map<Locale, String> titleMap,
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
		throws PortalException, PrincipalException;

}