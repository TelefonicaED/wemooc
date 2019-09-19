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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

import com.ted.lms.service.LearningActivityServiceUtil;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the HTTP utility for the
 * <code>LearningActivityServiceUtil</code> service
 * utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * <code>HttpPrincipal</code> parameter.
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
 * @generated
 */
@ProviderType
public class LearningActivityServiceHttp {

	public static com.ted.lms.model.LearningActivity getLearningActivity(
			HttpPrincipal httpPrincipal, long actId)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.security.auth.PrincipalException {

		try {
			MethodKey methodKey = new MethodKey(
				LearningActivityServiceUtil.class, "getLearningActivity",
				_getLearningActivityParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey, actId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.security.auth.
							PrincipalException) {

					throw (com.liferay.portal.kernel.security.auth.
						PrincipalException)e;
				}

				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.ted.lms.model.LearningActivity)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.ted.lms.model.LearningActivity updateLearningActivity(
			HttpPrincipal httpPrincipal,
			com.ted.lms.model.LearningActivity activity)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				LearningActivityServiceUtil.class, "updateLearningActivity",
				_updateLearningActivityParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, activity);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.ted.lms.model.LearningActivity)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.ted.lms.model.LearningActivity moveDownLearningActivity(
			HttpPrincipal httpPrincipal, long actId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				LearningActivityServiceUtil.class, "moveDownLearningActivity",
				_moveDownLearningActivityParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey, actId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.ted.lms.model.LearningActivity)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.ted.lms.model.LearningActivity moveUpLearningActivity(
			HttpPrincipal httpPrincipal, long actId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				LearningActivityServiceUtil.class, "moveUpLearningActivity",
				_moveUpLearningActivityParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey, actId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.ted.lms.model.LearningActivity)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.ted.lms.model.LearningActivity
			moveLearningActivityToTrash(HttpPrincipal httpPrincipal, long actId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				LearningActivityServiceUtil.class,
				"moveLearningActivityToTrash",
				_moveLearningActivityToTrashParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey, actId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.ted.lms.model.LearningActivity)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void changeVisibility(HttpPrincipal httpPrincipal, long actId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				LearningActivityServiceUtil.class, "changeVisibility",
				_changeVisibilityParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(methodKey, actId);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}
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
			MethodKey methodKey = new MethodKey(
				LearningActivityServiceUtil.class, "deleteLearningActivity",
				_deleteLearningActivityParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(methodKey, actId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.ted.lms.model.LearningActivity)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.ted.lms.model.LearningActivity addLearningActivity(
			HttpPrincipal httpPrincipal, long groupId, long moduleId, long type,
			java.util.Map<java.util.Locale, String> titleMap,
			java.util.Map<java.util.Locale, String> descriptionMap,
			boolean useStartExecutionDateCourse, int startDateMonth,
			int startDateDay, int startDateYear, int startDateHour,
			int startDateMinute, boolean useEndExecutionDateCourse,
			int endDateMonth, int endDateDay, int endDateYear, int endDateHour,
			int endDateMinute, boolean required, int tries,
			double passPuntuation,
			java.util.Map<java.util.Locale, String> feedbackCorrectMap,
			java.util.Map<java.util.Locale, String> feedbackNoCorrectMap,
			boolean commentsActivated, String[] selectedFileNames,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				LearningActivityServiceUtil.class, "addLearningActivity",
				_addLearningActivityParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, moduleId, type, titleMap, descriptionMap,
				useStartExecutionDateCourse, startDateMonth, startDateDay,
				startDateYear, startDateHour, startDateMinute,
				useEndExecutionDateCourse, endDateMonth, endDateDay,
				endDateYear, endDateHour, endDateMinute, required, tries,
				passPuntuation, feedbackCorrectMap, feedbackNoCorrectMap,
				commentsActivated, selectedFileNames, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
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
			int endDateMinute, boolean required, int tries,
			double passPuntuation,
			java.util.Map<java.util.Locale, String> feedbackCorrectMap,
			java.util.Map<java.util.Locale, String> feedbackNoCorrectMap,
			boolean commentsActivated, String[] selectedFileNames,
			long[] removeFileEntryIds,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.security.auth.PrincipalException {

		try {
			MethodKey methodKey = new MethodKey(
				LearningActivityServiceUtil.class, "updateLearningActivity",
				_updateLearningActivityParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, actId, titleMap, descriptionMap,
				useStartExecutionDateCourse, startDateMonth, startDateDay,
				startDateYear, startDateHour, startDateMinute,
				useEndExecutionDateCourse, endDateMonth, endDateDay,
				endDateYear, endDateHour, endDateMinute, required, tries,
				passPuntuation, feedbackCorrectMap, feedbackNoCorrectMap,
				commentsActivated, selectedFileNames, removeFileEntryIds,
				serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.security.auth.
							PrincipalException) {

					throw (com.liferay.portal.kernel.security.auth.
						PrincipalException)e;
				}

				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.ted.lms.model.LearningActivity)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.ted.lms.model.LearningActivity>
		getActivities(HttpPrincipal httpPrincipal, long moduleId) {

		try {
			MethodKey methodKey = new MethodKey(
				LearningActivityServiceUtil.class, "getActivities",
				_getActivitiesParameterTypes9);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, moduleId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (java.util.List<com.ted.lms.model.LearningActivity>)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.ted.lms.model.LearningActivity>
		getActivitiesExcluded(
			HttpPrincipal httpPrincipal, long moduleId, long actId) {

		try {
			MethodKey methodKey = new MethodKey(
				LearningActivityServiceUtil.class, "getActivitiesExcluded",
				_getActivitiesExcludedParameterTypes10);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, moduleId, actId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (java.util.List<com.ted.lms.model.LearningActivity>)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.ted.lms.model.LearningActivity>
		getActivitiesNotTypeId(
			HttpPrincipal httpPrincipal, long moduleId, long typeId) {

		try {
			MethodKey methodKey = new MethodKey(
				LearningActivityServiceUtil.class, "getActivitiesNotTypeId",
				_getActivitiesNotTypeIdParameterTypes11);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, moduleId, typeId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (java.util.List<com.ted.lms.model.LearningActivity>)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.ted.lms.model.LearningActivity getFirstLearningActivity(
			HttpPrincipal httpPrincipal, long groupId, long moduleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				LearningActivityServiceUtil.class, "getFirstLearningActivity",
				_getFirstLearningActivityParameterTypes12);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, moduleId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.ted.lms.model.LearningActivity)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.ted.lms.model.LearningActivity getNextLearningActivity(
			HttpPrincipal httpPrincipal,
			com.ted.lms.model.LearningActivity activity)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				LearningActivityServiceUtil.class, "getNextLearningActivity",
				_getNextLearningActivityParameterTypes13);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, activity);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.ted.lms.model.LearningActivity)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.ted.lms.model.LearningActivity
			getPreviousLearningActivity(
				HttpPrincipal httpPrincipal,
				com.ted.lms.model.LearningActivity activity)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				LearningActivityServiceUtil.class,
				"getPreviousLearningActivity",
				_getPreviousLearningActivityParameterTypes14);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, activity);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.ted.lms.model.LearningActivity)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static String[] getTempFileNames(
			HttpPrincipal httpPrincipal, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				LearningActivityServiceUtil.class, "getTempFileNames",
				_getTempFileNamesParameterTypes15);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (String[])returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		LearningActivityServiceHttp.class);

	private static final Class<?>[] _getLearningActivityParameterTypes0 =
		new Class[] {long.class};
	private static final Class<?>[] _updateLearningActivityParameterTypes1 =
		new Class[] {com.ted.lms.model.LearningActivity.class};
	private static final Class<?>[] _moveDownLearningActivityParameterTypes2 =
		new Class[] {long.class};
	private static final Class<?>[] _moveUpLearningActivityParameterTypes3 =
		new Class[] {long.class};
	private static final Class<?>[]
		_moveLearningActivityToTrashParameterTypes4 = new Class[] {long.class};
	private static final Class<?>[] _changeVisibilityParameterTypes5 =
		new Class[] {long.class};
	private static final Class<?>[] _deleteLearningActivityParameterTypes6 =
		new Class[] {long.class};
	private static final Class<?>[] _addLearningActivityParameterTypes7 =
		new Class[] {
			long.class, long.class, long.class, java.util.Map.class,
			java.util.Map.class, boolean.class, int.class, int.class, int.class,
			int.class, int.class, boolean.class, int.class, int.class,
			int.class, int.class, int.class, boolean.class, int.class,
			double.class, java.util.Map.class, java.util.Map.class,
			boolean.class, String[].class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _updateLearningActivityParameterTypes8 =
		new Class[] {
			long.class, java.util.Map.class, java.util.Map.class, boolean.class,
			int.class, int.class, int.class, int.class, int.class,
			boolean.class, int.class, int.class, int.class, int.class,
			int.class, boolean.class, int.class, double.class,
			java.util.Map.class, java.util.Map.class, boolean.class,
			String[].class, long[].class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _getActivitiesParameterTypes9 =
		new Class[] {long.class};
	private static final Class<?>[] _getActivitiesExcludedParameterTypes10 =
		new Class[] {long.class, long.class};
	private static final Class<?>[] _getActivitiesNotTypeIdParameterTypes11 =
		new Class[] {long.class, long.class};
	private static final Class<?>[] _getFirstLearningActivityParameterTypes12 =
		new Class[] {long.class, long.class};
	private static final Class<?>[] _getNextLearningActivityParameterTypes13 =
		new Class[] {com.ted.lms.model.LearningActivity.class};
	private static final Class<?>[]
		_getPreviousLearningActivityParameterTypes14 = new Class[] {
			com.ted.lms.model.LearningActivity.class
		};
	private static final Class<?>[] _getTempFileNamesParameterTypes15 =
		new Class[] {long.class};

}