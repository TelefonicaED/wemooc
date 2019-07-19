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

package com.ted.prerequisite.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.ted.prerequisite.model.PrerequisiteRelation;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the prerequisite relation service. This utility wraps {@link com.ted.prerequisite.service.persistence.impl.PrerequisiteRelationPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PrerequisiteRelationPersistence
 * @see com.ted.prerequisite.service.persistence.impl.PrerequisiteRelationPersistenceImpl
 * @generated
 */
@ProviderType
public class PrerequisiteRelationUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(PrerequisiteRelation prerequisiteRelation) {
		getPersistence().clearCache(prerequisiteRelation);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<PrerequisiteRelation> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<PrerequisiteRelation> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<PrerequisiteRelation> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<PrerequisiteRelation> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static PrerequisiteRelation update(
		PrerequisiteRelation prerequisiteRelation) {
		return getPersistence().update(prerequisiteRelation);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static PrerequisiteRelation update(
		PrerequisiteRelation prerequisiteRelation, ServiceContext serviceContext) {
		return getPersistence().update(prerequisiteRelation, serviceContext);
	}

	/**
	* Returns all the prerequisite relations where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching prerequisite relations
	*/
	public static List<PrerequisiteRelation> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the prerequisite relations where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PrerequisiteRelationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of prerequisite relations
	* @param end the upper bound of the range of prerequisite relations (not inclusive)
	* @return the range of matching prerequisite relations
	*/
	public static List<PrerequisiteRelation> findByUuid(String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the prerequisite relations where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PrerequisiteRelationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of prerequisite relations
	* @param end the upper bound of the range of prerequisite relations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching prerequisite relations
	*/
	public static List<PrerequisiteRelation> findByUuid(String uuid, int start,
		int end, OrderByComparator<PrerequisiteRelation> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the prerequisite relations where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PrerequisiteRelationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of prerequisite relations
	* @param end the upper bound of the range of prerequisite relations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching prerequisite relations
	*/
	public static List<PrerequisiteRelation> findByUuid(String uuid, int start,
		int end, OrderByComparator<PrerequisiteRelation> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first prerequisite relation in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching prerequisite relation
	* @throws NoSuchPrerequisiteRelationException if a matching prerequisite relation could not be found
	*/
	public static PrerequisiteRelation findByUuid_First(String uuid,
		OrderByComparator<PrerequisiteRelation> orderByComparator)
		throws com.ted.prerequisite.exception.NoSuchPrerequisiteRelationException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first prerequisite relation in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching prerequisite relation, or <code>null</code> if a matching prerequisite relation could not be found
	*/
	public static PrerequisiteRelation fetchByUuid_First(String uuid,
		OrderByComparator<PrerequisiteRelation> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last prerequisite relation in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching prerequisite relation
	* @throws NoSuchPrerequisiteRelationException if a matching prerequisite relation could not be found
	*/
	public static PrerequisiteRelation findByUuid_Last(String uuid,
		OrderByComparator<PrerequisiteRelation> orderByComparator)
		throws com.ted.prerequisite.exception.NoSuchPrerequisiteRelationException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last prerequisite relation in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching prerequisite relation, or <code>null</code> if a matching prerequisite relation could not be found
	*/
	public static PrerequisiteRelation fetchByUuid_Last(String uuid,
		OrderByComparator<PrerequisiteRelation> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the prerequisite relations before and after the current prerequisite relation in the ordered set where uuid = &#63;.
	*
	* @param prerequisiteRelationId the primary key of the current prerequisite relation
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next prerequisite relation
	* @throws NoSuchPrerequisiteRelationException if a prerequisite relation with the primary key could not be found
	*/
	public static PrerequisiteRelation[] findByUuid_PrevAndNext(
		long prerequisiteRelationId, String uuid,
		OrderByComparator<PrerequisiteRelation> orderByComparator)
		throws com.ted.prerequisite.exception.NoSuchPrerequisiteRelationException {
		return getPersistence()
				   .findByUuid_PrevAndNext(prerequisiteRelationId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the prerequisite relations where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of prerequisite relations where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching prerequisite relations
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns all the prerequisite relations where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @return the matching prerequisite relations
	*/
	public static List<PrerequisiteRelation> findByClassNameIdClassPK(
		long classNameId, long classPK) {
		return getPersistence().findByClassNameIdClassPK(classNameId, classPK);
	}

	/**
	* Returns a range of all the prerequisite relations where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PrerequisiteRelationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param start the lower bound of the range of prerequisite relations
	* @param end the upper bound of the range of prerequisite relations (not inclusive)
	* @return the range of matching prerequisite relations
	*/
	public static List<PrerequisiteRelation> findByClassNameIdClassPK(
		long classNameId, long classPK, int start, int end) {
		return getPersistence()
				   .findByClassNameIdClassPK(classNameId, classPK, start, end);
	}

	/**
	* Returns an ordered range of all the prerequisite relations where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PrerequisiteRelationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param start the lower bound of the range of prerequisite relations
	* @param end the upper bound of the range of prerequisite relations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching prerequisite relations
	*/
	public static List<PrerequisiteRelation> findByClassNameIdClassPK(
		long classNameId, long classPK, int start, int end,
		OrderByComparator<PrerequisiteRelation> orderByComparator) {
		return getPersistence()
				   .findByClassNameIdClassPK(classNameId, classPK, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the prerequisite relations where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PrerequisiteRelationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param start the lower bound of the range of prerequisite relations
	* @param end the upper bound of the range of prerequisite relations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching prerequisite relations
	*/
	public static List<PrerequisiteRelation> findByClassNameIdClassPK(
		long classNameId, long classPK, int start, int end,
		OrderByComparator<PrerequisiteRelation> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByClassNameIdClassPK(classNameId, classPK, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first prerequisite relation in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching prerequisite relation
	* @throws NoSuchPrerequisiteRelationException if a matching prerequisite relation could not be found
	*/
	public static PrerequisiteRelation findByClassNameIdClassPK_First(
		long classNameId, long classPK,
		OrderByComparator<PrerequisiteRelation> orderByComparator)
		throws com.ted.prerequisite.exception.NoSuchPrerequisiteRelationException {
		return getPersistence()
				   .findByClassNameIdClassPK_First(classNameId, classPK,
			orderByComparator);
	}

	/**
	* Returns the first prerequisite relation in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching prerequisite relation, or <code>null</code> if a matching prerequisite relation could not be found
	*/
	public static PrerequisiteRelation fetchByClassNameIdClassPK_First(
		long classNameId, long classPK,
		OrderByComparator<PrerequisiteRelation> orderByComparator) {
		return getPersistence()
				   .fetchByClassNameIdClassPK_First(classNameId, classPK,
			orderByComparator);
	}

	/**
	* Returns the last prerequisite relation in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching prerequisite relation
	* @throws NoSuchPrerequisiteRelationException if a matching prerequisite relation could not be found
	*/
	public static PrerequisiteRelation findByClassNameIdClassPK_Last(
		long classNameId, long classPK,
		OrderByComparator<PrerequisiteRelation> orderByComparator)
		throws com.ted.prerequisite.exception.NoSuchPrerequisiteRelationException {
		return getPersistence()
				   .findByClassNameIdClassPK_Last(classNameId, classPK,
			orderByComparator);
	}

	/**
	* Returns the last prerequisite relation in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching prerequisite relation, or <code>null</code> if a matching prerequisite relation could not be found
	*/
	public static PrerequisiteRelation fetchByClassNameIdClassPK_Last(
		long classNameId, long classPK,
		OrderByComparator<PrerequisiteRelation> orderByComparator) {
		return getPersistence()
				   .fetchByClassNameIdClassPK_Last(classNameId, classPK,
			orderByComparator);
	}

	/**
	* Returns the prerequisite relations before and after the current prerequisite relation in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param prerequisiteRelationId the primary key of the current prerequisite relation
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next prerequisite relation
	* @throws NoSuchPrerequisiteRelationException if a prerequisite relation with the primary key could not be found
	*/
	public static PrerequisiteRelation[] findByClassNameIdClassPK_PrevAndNext(
		long prerequisiteRelationId, long classNameId, long classPK,
		OrderByComparator<PrerequisiteRelation> orderByComparator)
		throws com.ted.prerequisite.exception.NoSuchPrerequisiteRelationException {
		return getPersistence()
				   .findByClassNameIdClassPK_PrevAndNext(prerequisiteRelationId,
			classNameId, classPK, orderByComparator);
	}

	/**
	* Removes all the prerequisite relations where classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	*/
	public static void removeByClassNameIdClassPK(long classNameId, long classPK) {
		getPersistence().removeByClassNameIdClassPK(classNameId, classPK);
	}

	/**
	* Returns the number of prerequisite relations where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @return the number of matching prerequisite relations
	*/
	public static int countByClassNameIdClassPK(long classNameId, long classPK) {
		return getPersistence().countByClassNameIdClassPK(classNameId, classPK);
	}

	/**
	* Returns all the prerequisite relations where classNamePrerequisiteId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param classNamePrerequisiteId the class name prerequisite ID
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @return the matching prerequisite relations
	*/
	public static List<PrerequisiteRelation> findByClassNamePrerequisiteIdClassNameIdClassPK(
		long classNamePrerequisiteId, long classNameId, long classPK) {
		return getPersistence()
				   .findByClassNamePrerequisiteIdClassNameIdClassPK(classNamePrerequisiteId,
			classNameId, classPK);
	}

	/**
	* Returns a range of all the prerequisite relations where classNamePrerequisiteId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PrerequisiteRelationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNamePrerequisiteId the class name prerequisite ID
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param start the lower bound of the range of prerequisite relations
	* @param end the upper bound of the range of prerequisite relations (not inclusive)
	* @return the range of matching prerequisite relations
	*/
	public static List<PrerequisiteRelation> findByClassNamePrerequisiteIdClassNameIdClassPK(
		long classNamePrerequisiteId, long classNameId, long classPK,
		int start, int end) {
		return getPersistence()
				   .findByClassNamePrerequisiteIdClassNameIdClassPK(classNamePrerequisiteId,
			classNameId, classPK, start, end);
	}

	/**
	* Returns an ordered range of all the prerequisite relations where classNamePrerequisiteId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PrerequisiteRelationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNamePrerequisiteId the class name prerequisite ID
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param start the lower bound of the range of prerequisite relations
	* @param end the upper bound of the range of prerequisite relations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching prerequisite relations
	*/
	public static List<PrerequisiteRelation> findByClassNamePrerequisiteIdClassNameIdClassPK(
		long classNamePrerequisiteId, long classNameId, long classPK,
		int start, int end,
		OrderByComparator<PrerequisiteRelation> orderByComparator) {
		return getPersistence()
				   .findByClassNamePrerequisiteIdClassNameIdClassPK(classNamePrerequisiteId,
			classNameId, classPK, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the prerequisite relations where classNamePrerequisiteId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PrerequisiteRelationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNamePrerequisiteId the class name prerequisite ID
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param start the lower bound of the range of prerequisite relations
	* @param end the upper bound of the range of prerequisite relations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching prerequisite relations
	*/
	public static List<PrerequisiteRelation> findByClassNamePrerequisiteIdClassNameIdClassPK(
		long classNamePrerequisiteId, long classNameId, long classPK,
		int start, int end,
		OrderByComparator<PrerequisiteRelation> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByClassNamePrerequisiteIdClassNameIdClassPK(classNamePrerequisiteId,
			classNameId, classPK, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first prerequisite relation in the ordered set where classNamePrerequisiteId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param classNamePrerequisiteId the class name prerequisite ID
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching prerequisite relation
	* @throws NoSuchPrerequisiteRelationException if a matching prerequisite relation could not be found
	*/
	public static PrerequisiteRelation findByClassNamePrerequisiteIdClassNameIdClassPK_First(
		long classNamePrerequisiteId, long classNameId, long classPK,
		OrderByComparator<PrerequisiteRelation> orderByComparator)
		throws com.ted.prerequisite.exception.NoSuchPrerequisiteRelationException {
		return getPersistence()
				   .findByClassNamePrerequisiteIdClassNameIdClassPK_First(classNamePrerequisiteId,
			classNameId, classPK, orderByComparator);
	}

	/**
	* Returns the first prerequisite relation in the ordered set where classNamePrerequisiteId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param classNamePrerequisiteId the class name prerequisite ID
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching prerequisite relation, or <code>null</code> if a matching prerequisite relation could not be found
	*/
	public static PrerequisiteRelation fetchByClassNamePrerequisiteIdClassNameIdClassPK_First(
		long classNamePrerequisiteId, long classNameId, long classPK,
		OrderByComparator<PrerequisiteRelation> orderByComparator) {
		return getPersistence()
				   .fetchByClassNamePrerequisiteIdClassNameIdClassPK_First(classNamePrerequisiteId,
			classNameId, classPK, orderByComparator);
	}

	/**
	* Returns the last prerequisite relation in the ordered set where classNamePrerequisiteId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param classNamePrerequisiteId the class name prerequisite ID
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching prerequisite relation
	* @throws NoSuchPrerequisiteRelationException if a matching prerequisite relation could not be found
	*/
	public static PrerequisiteRelation findByClassNamePrerequisiteIdClassNameIdClassPK_Last(
		long classNamePrerequisiteId, long classNameId, long classPK,
		OrderByComparator<PrerequisiteRelation> orderByComparator)
		throws com.ted.prerequisite.exception.NoSuchPrerequisiteRelationException {
		return getPersistence()
				   .findByClassNamePrerequisiteIdClassNameIdClassPK_Last(classNamePrerequisiteId,
			classNameId, classPK, orderByComparator);
	}

	/**
	* Returns the last prerequisite relation in the ordered set where classNamePrerequisiteId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param classNamePrerequisiteId the class name prerequisite ID
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching prerequisite relation, or <code>null</code> if a matching prerequisite relation could not be found
	*/
	public static PrerequisiteRelation fetchByClassNamePrerequisiteIdClassNameIdClassPK_Last(
		long classNamePrerequisiteId, long classNameId, long classPK,
		OrderByComparator<PrerequisiteRelation> orderByComparator) {
		return getPersistence()
				   .fetchByClassNamePrerequisiteIdClassNameIdClassPK_Last(classNamePrerequisiteId,
			classNameId, classPK, orderByComparator);
	}

	/**
	* Returns the prerequisite relations before and after the current prerequisite relation in the ordered set where classNamePrerequisiteId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param prerequisiteRelationId the primary key of the current prerequisite relation
	* @param classNamePrerequisiteId the class name prerequisite ID
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next prerequisite relation
	* @throws NoSuchPrerequisiteRelationException if a prerequisite relation with the primary key could not be found
	*/
	public static PrerequisiteRelation[] findByClassNamePrerequisiteIdClassNameIdClassPK_PrevAndNext(
		long prerequisiteRelationId, long classNamePrerequisiteId,
		long classNameId, long classPK,
		OrderByComparator<PrerequisiteRelation> orderByComparator)
		throws com.ted.prerequisite.exception.NoSuchPrerequisiteRelationException {
		return getPersistence()
				   .findByClassNamePrerequisiteIdClassNameIdClassPK_PrevAndNext(prerequisiteRelationId,
			classNamePrerequisiteId, classNameId, classPK, orderByComparator);
	}

	/**
	* Removes all the prerequisite relations where classNamePrerequisiteId = &#63; and classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param classNamePrerequisiteId the class name prerequisite ID
	* @param classNameId the class name ID
	* @param classPK the class pk
	*/
	public static void removeByClassNamePrerequisiteIdClassNameIdClassPK(
		long classNamePrerequisiteId, long classNameId, long classPK) {
		getPersistence()
			.removeByClassNamePrerequisiteIdClassNameIdClassPK(classNamePrerequisiteId,
			classNameId, classPK);
	}

	/**
	* Returns the number of prerequisite relations where classNamePrerequisiteId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param classNamePrerequisiteId the class name prerequisite ID
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @return the number of matching prerequisite relations
	*/
	public static int countByClassNamePrerequisiteIdClassNameIdClassPK(
		long classNamePrerequisiteId, long classNameId, long classPK) {
		return getPersistence()
				   .countByClassNamePrerequisiteIdClassNameIdClassPK(classNamePrerequisiteId,
			classNameId, classPK);
	}

	/**
	* Caches the prerequisite relation in the entity cache if it is enabled.
	*
	* @param prerequisiteRelation the prerequisite relation
	*/
	public static void cacheResult(PrerequisiteRelation prerequisiteRelation) {
		getPersistence().cacheResult(prerequisiteRelation);
	}

	/**
	* Caches the prerequisite relations in the entity cache if it is enabled.
	*
	* @param prerequisiteRelations the prerequisite relations
	*/
	public static void cacheResult(
		List<PrerequisiteRelation> prerequisiteRelations) {
		getPersistence().cacheResult(prerequisiteRelations);
	}

	/**
	* Creates a new prerequisite relation with the primary key. Does not add the prerequisite relation to the database.
	*
	* @param prerequisiteRelationId the primary key for the new prerequisite relation
	* @return the new prerequisite relation
	*/
	public static PrerequisiteRelation create(long prerequisiteRelationId) {
		return getPersistence().create(prerequisiteRelationId);
	}

	/**
	* Removes the prerequisite relation with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param prerequisiteRelationId the primary key of the prerequisite relation
	* @return the prerequisite relation that was removed
	* @throws NoSuchPrerequisiteRelationException if a prerequisite relation with the primary key could not be found
	*/
	public static PrerequisiteRelation remove(long prerequisiteRelationId)
		throws com.ted.prerequisite.exception.NoSuchPrerequisiteRelationException {
		return getPersistence().remove(prerequisiteRelationId);
	}

	public static PrerequisiteRelation updateImpl(
		PrerequisiteRelation prerequisiteRelation) {
		return getPersistence().updateImpl(prerequisiteRelation);
	}

	/**
	* Returns the prerequisite relation with the primary key or throws a {@link NoSuchPrerequisiteRelationException} if it could not be found.
	*
	* @param prerequisiteRelationId the primary key of the prerequisite relation
	* @return the prerequisite relation
	* @throws NoSuchPrerequisiteRelationException if a prerequisite relation with the primary key could not be found
	*/
	public static PrerequisiteRelation findByPrimaryKey(
		long prerequisiteRelationId)
		throws com.ted.prerequisite.exception.NoSuchPrerequisiteRelationException {
		return getPersistence().findByPrimaryKey(prerequisiteRelationId);
	}

	/**
	* Returns the prerequisite relation with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param prerequisiteRelationId the primary key of the prerequisite relation
	* @return the prerequisite relation, or <code>null</code> if a prerequisite relation with the primary key could not be found
	*/
	public static PrerequisiteRelation fetchByPrimaryKey(
		long prerequisiteRelationId) {
		return getPersistence().fetchByPrimaryKey(prerequisiteRelationId);
	}

	public static java.util.Map<java.io.Serializable, PrerequisiteRelation> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the prerequisite relations.
	*
	* @return the prerequisite relations
	*/
	public static List<PrerequisiteRelation> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the prerequisite relations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PrerequisiteRelationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of prerequisite relations
	* @param end the upper bound of the range of prerequisite relations (not inclusive)
	* @return the range of prerequisite relations
	*/
	public static List<PrerequisiteRelation> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the prerequisite relations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PrerequisiteRelationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of prerequisite relations
	* @param end the upper bound of the range of prerequisite relations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of prerequisite relations
	*/
	public static List<PrerequisiteRelation> findAll(int start, int end,
		OrderByComparator<PrerequisiteRelation> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the prerequisite relations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PrerequisiteRelationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of prerequisite relations
	* @param end the upper bound of the range of prerequisite relations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of prerequisite relations
	*/
	public static List<PrerequisiteRelation> findAll(int start, int end,
		OrderByComparator<PrerequisiteRelation> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the prerequisite relations from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of prerequisite relations.
	*
	* @return the number of prerequisite relations
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static PrerequisiteRelationPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<PrerequisiteRelationPersistence, PrerequisiteRelationPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(PrerequisiteRelationPersistence.class);

		ServiceTracker<PrerequisiteRelationPersistence, PrerequisiteRelationPersistence> serviceTracker =
			new ServiceTracker<PrerequisiteRelationPersistence, PrerequisiteRelationPersistence>(bundle.getBundleContext(),
				PrerequisiteRelationPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}