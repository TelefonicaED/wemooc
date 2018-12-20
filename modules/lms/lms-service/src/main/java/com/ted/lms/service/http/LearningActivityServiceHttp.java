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

package com.ted.lms.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

import com.ted.lms.service.LearningActivityServiceUtil;

/**
 * Provides the HTTP utility for the
 * {@link LearningActivityServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * {@link HttpPrincipal} parameter.
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LearningActivityServiceSoap
 * @see HttpPrincipal
 * @see LearningActivityServiceUtil
 * @generated
 */
@ProviderType
public class LearningActivityServiceHttp {
	public static com.ted.lms.model.LearningActivity updateLearningActivity(
		HttpPrincipal httpPrincipal, com.ted.lms.model.LearningActivity activity)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LearningActivityServiceUtil.class,
					"updateLearningActivity",
					_updateLearningActivityParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey, activity);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.ted.lms.model.LearningActivity)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.ted.lms.model.LearningActivity moveDownLearningActivity(
		HttpPrincipal httpPrincipal, long actId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LearningActivityServiceUtil.class,
					"moveDownLearningActivity",
					_moveDownLearningActivityParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey, actId,
					serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.ted.lms.model.LearningActivity)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.ted.lms.model.LearningActivity moveUpLearningActivity(
		HttpPrincipal httpPrincipal, long actId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LearningActivityServiceUtil.class,
					"moveUpLearningActivity",
					_moveUpLearningActivityParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey, actId,
					serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.ted.lms.model.LearningActivity)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.ted.lms.model.LearningActivity moveLearningActivityToTrash(
		HttpPrincipal httpPrincipal, long actId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LearningActivityServiceUtil.class,
					"moveLearningActivityToTrash",
					_moveLearningActivityToTrashParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey, actId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.ted.lms.model.LearningActivity)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.ted.lms.model.LearningActivity deleteLearningActivity(
		HttpPrincipal httpPrincipal, long actId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LearningActivityServiceUtil.class,
					"deleteLearningActivity",
					_deleteLearningActivityParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey, actId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.ted.lms.model.LearningActivity)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.ted.lms.model.LearningActivity addLearningActivity(
		HttpPrincipal httpPrincipal, long moduleId, long type,
		java.util.Map<java.util.Locale, String> titleMap,
		java.util.Map<java.util.Locale, String> descriptionMap,
		boolean useStartExecutionDateCourse, int startDateMonth,
		int startDateDay, int startDateYear, int startDateHour,
		int startDateMinute, boolean useEndExecutionDateCourse,
		int endDateMonth, int endDateDay, int endDateYear, int endDateHour,
		int endDateMinute, boolean required, int tries, double passPuntuation,
		java.util.Map<java.util.Locale, String> feedbackCorrectMap,
		java.util.Map<java.util.Locale, String> feedbackNoCorrectMap,
		boolean commentsActivated,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LearningActivityServiceUtil.class,
					"addLearningActivity", _addLearningActivityParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					moduleId, type, titleMap, descriptionMap,
					useStartExecutionDateCourse, startDateMonth, startDateDay,
					startDateYear, startDateHour, startDateMinute,
					useEndExecutionDateCourse, endDateMonth, endDateDay,
					endDateYear, endDateHour, endDateMinute, required, tries,
					passPuntuation, feedbackCorrectMap, feedbackNoCorrectMap,
					commentsActivated, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.ted.lms.model.LearningActivity)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.ted.lms.model.LearningActivity updateLearningActivity(
		HttpPrincipal httpPrincipal, long actId,
		java.util.Map<java.util.Locale, String> titleMap,
		java.util.Map<java.util.Locale, String> descriptionMap,
		boolean useStartExecutionDateCourse, int startDateMonth,
		int startDateDay, int startDateYear, int startDateHour,
		int startDateMinute, boolean useEndExecutionDateCourse,
		int endDateMonth, int endDateDay, int endDateYear, int endDateHour,
		int endDateMinute, boolean required, int tries, double passPuntuation,
		java.util.Map<java.util.Locale, String> feedbackCorrectMap,
		java.util.Map<java.util.Locale, String> feedbackNoCorrectMap,
		boolean commentsActivated,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.security.auth.PrincipalException,
			com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LearningActivityServiceUtil.class,
					"updateLearningActivity",
					_updateLearningActivityParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(methodKey, actId,
					titleMap, descriptionMap, useStartExecutionDateCourse,
					startDateMonth, startDateDay, startDateYear, startDateHour,
					startDateMinute, useEndExecutionDateCourse, endDateMonth,
					endDateDay, endDateYear, endDateHour, endDateMinute,
					required, tries, passPuntuation, feedbackCorrectMap,
					feedbackNoCorrectMap, commentsActivated, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.security.auth.PrincipalException) {
					throw (com.liferay.portal.kernel.security.auth.PrincipalException)e;
				}

				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.ted.lms.model.LearningActivity)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.ted.lms.model.LearningActivity> getActivities(
		HttpPrincipal httpPrincipal, long moduleId) {
		try {
			MethodKey methodKey = new MethodKey(LearningActivityServiceUtil.class,
					"getActivities", _getActivitiesParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(methodKey, moduleId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.ted.lms.model.LearningActivity>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(LearningActivityServiceHttp.class);
	private static final Class<?>[] _updateLearningActivityParameterTypes0 = new Class[] {
			com.ted.lms.model.LearningActivity.class
		};
	private static final Class<?>[] _moveDownLearningActivityParameterTypes1 = new Class[] {
			long.class, com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _moveUpLearningActivityParameterTypes2 = new Class[] {
			long.class, com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _moveLearningActivityToTrashParameterTypes3 = new Class[] {
			long.class
		};
	private static final Class<?>[] _deleteLearningActivityParameterTypes4 = new Class[] {
			long.class
		};
	private static final Class<?>[] _addLearningActivityParameterTypes5 = new Class[] {
			long.class, long.class, java.util.Map.class, java.util.Map.class,
			boolean.class, int.class, int.class, int.class, int.class, int.class,
			boolean.class, int.class, int.class, int.class, int.class, int.class,
			boolean.class, int.class, double.class, java.util.Map.class,
			java.util.Map.class, boolean.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _updateLearningActivityParameterTypes6 = new Class[] {
			long.class, java.util.Map.class, java.util.Map.class, boolean.class,
			int.class, int.class, int.class, int.class, int.class, boolean.class,
			int.class, int.class, int.class, int.class, int.class, boolean.class,
			int.class, double.class, java.util.Map.class, java.util.Map.class,
			boolean.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _getActivitiesParameterTypes7 = new Class[] {
			long.class
		};
}