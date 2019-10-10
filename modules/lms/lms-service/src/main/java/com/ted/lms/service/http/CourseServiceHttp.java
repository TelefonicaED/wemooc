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

import com.ted.lms.service.CourseServiceUtil;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the HTTP utility for the
 * <code>CourseServiceUtil</code> service
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
 * @see CourseServiceSoap
 * @generated
 */
@ProviderType
public class CourseServiceHttp {

	public static com.ted.lms.model.Course addCourse(
			HttpPrincipal httpPrincipal, long groupId,
			java.util.Map<java.util.Locale, String> titleMap,
			java.util.Map<java.util.Locale, String> descriptionMap,
			java.util.Map<java.util.Locale, String> summaryMap, boolean indexer,
			String friendlyURL, long layoutSetPrototypeId, long parentCourseId,
			long courseTypeId,
			com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector
				smallImageSelector,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws Exception {

		try {
			MethodKey methodKey = new MethodKey(
				CourseServiceUtil.class, "addCourse",
				_addCourseParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, titleMap, descriptionMap, summaryMap,
				indexer, friendlyURL, layoutSetPrototypeId, parentCourseId,
				courseTypeId, smallImageSelector, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof Exception) {
					throw (Exception)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.ted.lms.model.Course)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.ted.lms.model.Course updateCourse(
			HttpPrincipal httpPrincipal, long courseId,
			java.util.Map<java.util.Locale, String> titleMap,
			java.util.Map<java.util.Locale, String> descriptionMap,
			java.util.Map<java.util.Locale, String> summaryMap, boolean indexer,
			String friendlyURL, long layoutSetPrototypeId,
			com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector
				smallImageImageSelector,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws Exception {

		try {
			MethodKey methodKey = new MethodKey(
				CourseServiceUtil.class, "updateCourse",
				_updateCourseParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, courseId, titleMap, descriptionMap, summaryMap,
				indexer, friendlyURL, layoutSetPrototypeId,
				smallImageImageSelector, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof Exception) {
					throw (Exception)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.ted.lms.model.Course)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.ted.lms.model.Course updateCourse(
			HttpPrincipal httpPrincipal, long courseId,
			int registrationStartMonth, int registrationStartDay,
			int registrationStartYear, int registrationStartHour,
			int registrationStartMinute, int registrationEndMonth,
			int registrationEndDay, int registrationEndYear,
			int registrationEndHour, int registrationEndMinute,
			int executionStartMonth, int executionStartDay,
			int executionStartYear, int executionStartHour,
			int executionStartMinute, int executionEndMonth,
			int executionEndDay, int executionEndYear, int executionEndHour,
			int executionEndMinute, int typeSite, long inscriptionType,
			long courseEvalId, long calificationType, int maxUsers, int status,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CourseServiceUtil.class, "updateCourse",
				_updateCourseParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, courseId, registrationStartMonth,
				registrationStartDay, registrationStartYear,
				registrationStartHour, registrationStartMinute,
				registrationEndMonth, registrationEndDay, registrationEndYear,
				registrationEndHour, registrationEndMinute, executionStartMonth,
				executionStartDay, executionStartYear, executionStartHour,
				executionStartMinute, executionEndMonth, executionEndDay,
				executionEndYear, executionEndHour, executionEndMinute,
				typeSite, inscriptionType, courseEvalId, calificationType,
				maxUsers, status, serviceContext);

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

			return (com.ted.lms.model.Course)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.ted.lms.model.Course updateCourse(
			HttpPrincipal httpPrincipal, long courseId, boolean welcome,
			java.util.Map<java.util.Locale, String> welcomeSubjectMap,
			java.util.Map<java.util.Locale, String> welcomeMsgMap,
			boolean goodbye,
			java.util.Map<java.util.Locale, String> goodbyeSubjectMap,
			java.util.Map<java.util.Locale, String> goodbyeMsgMap,
			boolean deniedInscription,
			java.util.Map<java.util.Locale, String> deniedInscriptionSubjectMap,
			java.util.Map<java.util.Locale, String> deniedInscriptionMsgMap,
			int status,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.security.auth.PrincipalException {

		try {
			MethodKey methodKey = new MethodKey(
				CourseServiceUtil.class, "updateCourse",
				_updateCourseParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, courseId, welcome, welcomeSubjectMap, welcomeMsgMap,
				goodbye, goodbyeSubjectMap, goodbyeMsgMap, deniedInscription,
				deniedInscriptionSubjectMap, deniedInscriptionMsgMap, status,
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

			return (com.ted.lms.model.Course)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.ted.lms.model.Course updateCourse(
			HttpPrincipal httpPrincipal, long courseId, int status,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.security.auth.PrincipalException {

		try {
			MethodKey methodKey = new MethodKey(
				CourseServiceUtil.class, "updateCourse",
				_updateCourseParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, courseId, status, serviceContext);

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

			return (com.ted.lms.model.Course)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void deleteCourse(HttpPrincipal httpPrincipal, long courseId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CourseServiceUtil.class, "deleteCourse",
				_deleteCourseParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, courseId);

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

	public static com.ted.lms.model.Course moveEntryToTrash(
			HttpPrincipal httpPrincipal, long courseId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CourseServiceUtil.class, "moveEntryToTrash",
				_moveEntryToTrashParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, courseId);

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

			return (com.ted.lms.model.Course)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void restoreEntryFromTrash(
			HttpPrincipal httpPrincipal, long courseId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CourseServiceUtil.class, "restoreEntryFromTrash",
				_restoreEntryFromTrashParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, courseId);

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

	public static void addUserCourse(
			HttpPrincipal httpPrincipal, long courseId, long[] addUserIds,
			long roleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CourseServiceUtil.class, "addUserCourse",
				_addUserCourseParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, courseId, addUserIds, roleId);

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

	public static void unsetUserCourse(
			HttpPrincipal httpPrincipal, long courseId, long[] removeUserIds,
			long roleId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CourseServiceUtil.class, "unsetUserCourse",
				_unsetUserCourseParameterTypes9);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, courseId, removeUserIds, roleId, serviceContext);

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

	public static void updateSmallImage(
			HttpPrincipal httpPrincipal, long courseId, String imageString,
			String imageTitle, String imageMimeType,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.security.auth.PrincipalException {

		try {
			MethodKey methodKey = new MethodKey(
				CourseServiceUtil.class, "updateSmallImage",
				_updateSmallImageParameterTypes10);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, courseId, imageString, imageTitle, imageMimeType,
				serviceContext);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
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
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.ted.lms.model.Course> searchCourses(
		HttpPrincipal httpPrincipal, long companyId, String title,
		String description, String language, int[] status, long parentCourseId,
		long groupId, java.util.LinkedHashMap<String, Object> params,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<com.ted.lms.model.Course> obc) {

		try {
			MethodKey methodKey = new MethodKey(
				CourseServiceUtil.class, "searchCourses",
				_searchCoursesParameterTypes11);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId, title, description, language, status,
				parentCourseId, groupId, params, andOperator, start, end, obc);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (java.util.List<com.ted.lms.model.Course>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int countCourses(
		HttpPrincipal httpPrincipal, long companyId, String title,
		String description, String language, int[] status, long parentCourseId,
		long groupId, java.util.LinkedHashMap<String, Object> params,
		boolean andOperator) {

		try {
			MethodKey methodKey = new MethodKey(
				CourseServiceUtil.class, "countCourses",
				_countCoursesParameterTypes12);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId, title, description, language, status,
				parentCourseId, groupId, params, andOperator);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static long executeCopyCourse(
			HttpPrincipal httpPrincipal, long courseId, long parentCourseId,
			java.util.Map<java.util.Locale, String> titleMap,
			java.util.Map<java.util.Locale, String> friendlyURLMap,
			long layoutSetPrototypeId, java.util.Date registrationStartDate,
			java.util.Date registrationEndDate,
			java.util.Date executionStartDate, java.util.Date executionEndDate,
			boolean copyForum, boolean copyDocuments,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CourseServiceUtil.class, "executeCopyCourse",
				_executeCopyCourseParameterTypes13);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, courseId, parentCourseId, titleMap, friendlyURLMap,
				layoutSetPrototypeId, registrationStartDate,
				registrationEndDate, executionStartDate, executionEndDate,
				copyForum, copyDocuments, serviceContext);

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

			return ((Long)returnObj).longValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(CourseServiceHttp.class);

	private static final Class<?>[] _addCourseParameterTypes0 = new Class[] {
		long.class, java.util.Map.class, java.util.Map.class,
		java.util.Map.class, boolean.class, String.class, long.class,
		long.class, long.class,
		com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector.class,
		com.liferay.portal.kernel.service.ServiceContext.class
	};
	private static final Class<?>[] _updateCourseParameterTypes1 = new Class[] {
		long.class, java.util.Map.class, java.util.Map.class,
		java.util.Map.class, boolean.class, String.class, long.class,
		com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector.class,
		com.liferay.portal.kernel.service.ServiceContext.class
	};
	private static final Class<?>[] _updateCourseParameterTypes2 = new Class[] {
		long.class, int.class, int.class, int.class, int.class, int.class,
		int.class, int.class, int.class, int.class, int.class, int.class,
		int.class, int.class, int.class, int.class, int.class, int.class,
		int.class, int.class, int.class, int.class, long.class, long.class,
		long.class, int.class, int.class,
		com.liferay.portal.kernel.service.ServiceContext.class
	};
	private static final Class<?>[] _updateCourseParameterTypes3 = new Class[] {
		long.class, boolean.class, java.util.Map.class, java.util.Map.class,
		boolean.class, java.util.Map.class, java.util.Map.class, boolean.class,
		java.util.Map.class, java.util.Map.class, int.class,
		com.liferay.portal.kernel.service.ServiceContext.class
	};
	private static final Class<?>[] _updateCourseParameterTypes4 = new Class[] {
		long.class, int.class,
		com.liferay.portal.kernel.service.ServiceContext.class
	};
	private static final Class<?>[] _deleteCourseParameterTypes5 = new Class[] {
		long.class
	};
	private static final Class<?>[] _moveEntryToTrashParameterTypes6 =
		new Class[] {long.class};
	private static final Class<?>[] _restoreEntryFromTrashParameterTypes7 =
		new Class[] {long.class};
	private static final Class<?>[] _addUserCourseParameterTypes8 =
		new Class[] {long.class, long[].class, long.class};
	private static final Class<?>[] _unsetUserCourseParameterTypes9 =
		new Class[] {
			long.class, long[].class, long.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _updateSmallImageParameterTypes10 =
		new Class[] {
			long.class, String.class, String.class, String.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _searchCoursesParameterTypes11 =
		new Class[] {
			long.class, String.class, String.class, String.class, int[].class,
			long.class, long.class, java.util.LinkedHashMap.class,
			boolean.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _countCoursesParameterTypes12 =
		new Class[] {
			long.class, String.class, String.class, String.class, int[].class,
			long.class, long.class, java.util.LinkedHashMap.class, boolean.class
		};
	private static final Class<?>[] _executeCopyCourseParameterTypes13 =
		new Class[] {
			long.class, long.class, java.util.Map.class, java.util.Map.class,
			long.class, java.util.Date.class, java.util.Date.class,
			java.util.Date.class, java.util.Date.class, boolean.class,
			boolean.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};

}