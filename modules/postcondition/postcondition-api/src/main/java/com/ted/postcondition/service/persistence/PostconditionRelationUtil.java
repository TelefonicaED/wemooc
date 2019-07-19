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

package com.ted.postcondition.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.ted.postcondition.model.PostconditionRelation;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the postcondition relation service. This utility wraps {@link com.ted.postcondition.service.persistence.impl.PostconditionRelationPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PostconditionRelationPersistence
 * @see com.ted.postcondition.service.persistence.impl.PostconditionRelationPersistenceImpl
 * @generated
 */
@ProviderType
public class PostconditionRelationUtil {
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
	public static void clearCache(PostconditionRelation postconditionRelation) {
		getPersistence().clearCache(postconditionRelation);
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
	public static List<PostconditionRelation> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<PostconditionRelation> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<PostconditionRelation> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<PostconditionRelation> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static PostconditionRelation update(
		PostconditionRelation postconditionRelation) {
		return getPersistence().update(postconditionRelation);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static PostconditionRelation update(
		PostconditionRelation postconditionRelation,
		ServiceContext serviceContext) {
		return getPersistence().update(postconditionRelation, serviceContext);
	}

	/**
	* Returns all the postcondition relations where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching postcondition relations
	*/
	public static List<PostconditionRelation> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the postcondition relations where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostconditionRelationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of postcondition relations
	* @param end the upper bound of the range of postcondition relations (not inclusive)
	* @return the range of matching postcondition relations
	*/
	public static List<PostconditionRelation> findByUuid(String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the postcondition relations where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostconditionRelationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of postcondition relations
	* @param end the upper bound of the range of postcondition relations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching postcondition relations
	*/
	public static List<PostconditionRelation> findByUuid(String uuid,
		int start, int end,
		OrderByComparator<PostconditionRelation> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the postcondition relations where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostconditionRelationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of postcondition relations
	* @param end the upper bound of the range of postcondition relations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching postcondition relations
	*/
	public static List<PostconditionRelation> findByUuid(String uuid,
		int start, int end,
		OrderByComparator<PostconditionRelation> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first postcondition relation in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching postcondition relation
	* @throws NoSuchPostconditionRelationException if a matching postcondition relation could not be found
	*/
	public static PostconditionRelation findByUuid_First(String uuid,
		OrderByComparator<PostconditionRelation> orderByComparator)
		throws com.ted.postcondition.exception.NoSuchPostconditionRelationException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first postcondition relation in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching postcondition relation, or <code>null</code> if a matching postcondition relation could not be found
	*/
	public static PostconditionRelation fetchByUuid_First(String uuid,
		OrderByComparator<PostconditionRelation> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last postcondition relation in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching postcondition relation
	* @throws NoSuchPostconditionRelationException if a matching postcondition relation could not be found
	*/
	public static PostconditionRelation findByUuid_Last(String uuid,
		OrderByComparator<PostconditionRelation> orderByComparator)
		throws com.ted.postcondition.exception.NoSuchPostconditionRelationException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last postcondition relation in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching postcondition relation, or <code>null</code> if a matching postcondition relation could not be found
	*/
	public static PostconditionRelation fetchByUuid_Last(String uuid,
		OrderByComparator<PostconditionRelation> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the postcondition relations before and after the current postcondition relation in the ordered set where uuid = &#63;.
	*
	* @param postconditionRelationId the primary key of the current postcondition relation
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next postcondition relation
	* @throws NoSuchPostconditionRelationException if a postcondition relation with the primary key could not be found
	*/
	public static PostconditionRelation[] findByUuid_PrevAndNext(
		long postconditionRelationId, String uuid,
		OrderByComparator<PostconditionRelation> orderByComparator)
		throws com.ted.postcondition.exception.NoSuchPostconditionRelationException {
		return getPersistence()
				   .findByUuid_PrevAndNext(postconditionRelationId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the postcondition relations where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of postcondition relations where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching postcondition relations
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns all the postcondition relations where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @return the matching postcondition relations
	*/
	public static List<PostconditionRelation> findByClassNameIdClassPK(
		long classNameId, long classPK) {
		return getPersistence().findByClassNameIdClassPK(classNameId, classPK);
	}

	/**
	* Returns a range of all the postcondition relations where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostconditionRelationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param start the lower bound of the range of postcondition relations
	* @param end the upper bound of the range of postcondition relations (not inclusive)
	* @return the range of matching postcondition relations
	*/
	public static List<PostconditionRelation> findByClassNameIdClassPK(
		long classNameId, long classPK, int start, int end) {
		return getPersistence()
				   .findByClassNameIdClassPK(classNameId, classPK, start, end);
	}

	/**
	* Returns an ordered range of all the postcondition relations where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostconditionRelationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param start the lower bound of the range of postcondition relations
	* @param end the upper bound of the range of postcondition relations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching postcondition relations
	*/
	public static List<PostconditionRelation> findByClassNameIdClassPK(
		long classNameId, long classPK, int start, int end,
		OrderByComparator<PostconditionRelation> orderByComparator) {
		return getPersistence()
				   .findByClassNameIdClassPK(classNameId, classPK, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the postcondition relations where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostconditionRelationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param start the lower bound of the range of postcondition relations
	* @param end the upper bound of the range of postcondition relations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching postcondition relations
	*/
	public static List<PostconditionRelation> findByClassNameIdClassPK(
		long classNameId, long classPK, int start, int end,
		OrderByComparator<PostconditionRelation> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByClassNameIdClassPK(classNameId, classPK, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first postcondition relation in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching postcondition relation
	* @throws NoSuchPostconditionRelationException if a matching postcondition relation could not be found
	*/
	public static PostconditionRelation findByClassNameIdClassPK_First(
		long classNameId, long classPK,
		OrderByComparator<PostconditionRelation> orderByComparator)
		throws com.ted.postcondition.exception.NoSuchPostconditionRelationException {
		return getPersistence()
				   .findByClassNameIdClassPK_First(classNameId, classPK,
			orderByComparator);
	}

	/**
	* Returns the first postcondition relation in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching postcondition relation, or <code>null</code> if a matching postcondition relation could not be found
	*/
	public static PostconditionRelation fetchByClassNameIdClassPK_First(
		long classNameId, long classPK,
		OrderByComparator<PostconditionRelation> orderByComparator) {
		return getPersistence()
				   .fetchByClassNameIdClassPK_First(classNameId, classPK,
			orderByComparator);
	}

	/**
	* Returns the last postcondition relation in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching postcondition relation
	* @throws NoSuchPostconditionRelationException if a matching postcondition relation could not be found
	*/
	public static PostconditionRelation findByClassNameIdClassPK_Last(
		long classNameId, long classPK,
		OrderByComparator<PostconditionRelation> orderByComparator)
		throws com.ted.postcondition.exception.NoSuchPostconditionRelationException {
		return getPersistence()
				   .findByClassNameIdClassPK_Last(classNameId, classPK,
			orderByComparator);
	}

	/**
	* Returns the last postcondition relation in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching postcondition relation, or <code>null</code> if a matching postcondition relation could not be found
	*/
	public static PostconditionRelation fetchByClassNameIdClassPK_Last(
		long classNameId, long classPK,
		OrderByComparator<PostconditionRelation> orderByComparator) {
		return getPersistence()
				   .fetchByClassNameIdClassPK_Last(classNameId, classPK,
			orderByComparator);
	}

	/**
	* Returns the postcondition relations before and after the current postcondition relation in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param postconditionRelationId the primary key of the current postcondition relation
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next postcondition relation
	* @throws NoSuchPostconditionRelationException if a postcondition relation with the primary key could not be found
	*/
	public static PostconditionRelation[] findByClassNameIdClassPK_PrevAndNext(
		long postconditionRelationId, long classNameId, long classPK,
		OrderByComparator<PostconditionRelation> orderByComparator)
		throws com.ted.postcondition.exception.NoSuchPostconditionRelationException {
		return getPersistence()
				   .findByClassNameIdClassPK_PrevAndNext(postconditionRelationId,
			classNameId, classPK, orderByComparator);
	}

	/**
	* Removes all the postcondition relations where classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	*/
	public static void removeByClassNameIdClassPK(long classNameId, long classPK) {
		getPersistence().removeByClassNameIdClassPK(classNameId, classPK);
	}

	/**
	* Returns the number of postcondition relations where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @return the number of matching postcondition relations
	*/
	public static int countByClassNameIdClassPK(long classNameId, long classPK) {
		return getPersistence().countByClassNameIdClassPK(classNameId, classPK);
	}

	/**
	* Returns all the postcondition relations where classNamePostconditionId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param classNamePostconditionId the class name postcondition ID
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @return the matching postcondition relations
	*/
	public static List<PostconditionRelation> findByClassNamePostconditionIdClassNameIdClassPK(
		long classNamePostconditionId, long classNameId, long classPK) {
		return getPersistence()
				   .findByClassNamePostconditionIdClassNameIdClassPK(classNamePostconditionId,
			classNameId, classPK);
	}

	/**
	* Returns a range of all the postcondition relations where classNamePostconditionId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostconditionRelationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNamePostconditionId the class name postcondition ID
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param start the lower bound of the range of postcondition relations
	* @param end the upper bound of the range of postcondition relations (not inclusive)
	* @return the range of matching postcondition relations
	*/
	public static List<PostconditionRelation> findByClassNamePostconditionIdClassNameIdClassPK(
		long classNamePostconditionId, long classNameId, long classPK,
		int start, int end) {
		return getPersistence()
				   .findByClassNamePostconditionIdClassNameIdClassPK(classNamePostconditionId,
			classNameId, classPK, start, end);
	}

	/**
	* Returns an ordered range of all the postcondition relations where classNamePostconditionId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostconditionRelationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNamePostconditionId the class name postcondition ID
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param start the lower bound of the range of postcondition relations
	* @param end the upper bound of the range of postcondition relations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching postcondition relations
	*/
	public static List<PostconditionRelation> findByClassNamePostconditionIdClassNameIdClassPK(
		long classNamePostconditionId, long classNameId, long classPK,
		int start, int end,
		OrderByComparator<PostconditionRelation> orderByComparator) {
		return getPersistence()
				   .findByClassNamePostconditionIdClassNameIdClassPK(classNamePostconditionId,
			classNameId, classPK, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the postcondition relations where classNamePostconditionId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostconditionRelationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNamePostconditionId the class name postcondition ID
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param start the lower bound of the range of postcondition relations
	* @param end the upper bound of the range of postcondition relations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching postcondition relations
	*/
	public static List<PostconditionRelation> findByClassNamePostconditionIdClassNameIdClassPK(
		long classNamePostconditionId, long classNameId, long classPK,
		int start, int end,
		OrderByComparator<PostconditionRelation> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByClassNamePostconditionIdClassNameIdClassPK(classNamePostconditionId,
			classNameId, classPK, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first postcondition relation in the ordered set where classNamePostconditionId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param classNamePostconditionId the class name postcondition ID
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching postcondition relation
	* @throws NoSuchPostconditionRelationException if a matching postcondition relation could not be found
	*/
	public static PostconditionRelation findByClassNamePostconditionIdClassNameIdClassPK_First(
		long classNamePostconditionId, long classNameId, long classPK,
		OrderByComparator<PostconditionRelation> orderByComparator)
		throws com.ted.postcondition.exception.NoSuchPostconditionRelationException {
		return getPersistence()
				   .findByClassNamePostconditionIdClassNameIdClassPK_First(classNamePostconditionId,
			classNameId, classPK, orderByComparator);
	}

	/**
	* Returns the first postcondition relation in the ordered set where classNamePostconditionId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param classNamePostconditionId the class name postcondition ID
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching postcondition relation, or <code>null</code> if a matching postcondition relation could not be found
	*/
	public static PostconditionRelation fetchByClassNamePostconditionIdClassNameIdClassPK_First(
		long classNamePostconditionId, long classNameId, long classPK,
		OrderByComparator<PostconditionRelation> orderByComparator) {
		return getPersistence()
				   .fetchByClassNamePostconditionIdClassNameIdClassPK_First(classNamePostconditionId,
			classNameId, classPK, orderByComparator);
	}

	/**
	* Returns the last postcondition relation in the ordered set where classNamePostconditionId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param classNamePostconditionId the class name postcondition ID
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching postcondition relation
	* @throws NoSuchPostconditionRelationException if a matching postcondition relation could not be found
	*/
	public static PostconditionRelation findByClassNamePostconditionIdClassNameIdClassPK_Last(
		long classNamePostconditionId, long classNameId, long classPK,
		OrderByComparator<PostconditionRelation> orderByComparator)
		throws com.ted.postcondition.exception.NoSuchPostconditionRelationException {
		return getPersistence()
				   .findByClassNamePostconditionIdClassNameIdClassPK_Last(classNamePostconditionId,
			classNameId, classPK, orderByComparator);
	}

	/**
	* Returns the last postcondition relation in the ordered set where classNamePostconditionId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param classNamePostconditionId the class name postcondition ID
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching postcondition relation, or <code>null</code> if a matching postcondition relation could not be found
	*/
	public static PostconditionRelation fetchByClassNamePostconditionIdClassNameIdClassPK_Last(
		long classNamePostconditionId, long classNameId, long classPK,
		OrderByComparator<PostconditionRelation> orderByComparator) {
		return getPersistence()
				   .fetchByClassNamePostconditionIdClassNameIdClassPK_Last(classNamePostconditionId,
			classNameId, classPK, orderByComparator);
	}

	/**
	* Returns the postcondition relations before and after the current postcondition relation in the ordered set where classNamePostconditionId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param postconditionRelationId the primary key of the current postcondition relation
	* @param classNamePostconditionId the class name postcondition ID
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next postcondition relation
	* @throws NoSuchPostconditionRelationException if a postcondition relation with the primary key could not be found
	*/
	public static PostconditionRelation[] findByClassNamePostconditionIdClassNameIdClassPK_PrevAndNext(
		long postconditionRelationId, long classNamePostconditionId,
		long classNameId, long classPK,
		OrderByComparator<PostconditionRelation> orderByComparator)
		throws com.ted.postcondition.exception.NoSuchPostconditionRelationException {
		return getPersistence()
				   .findByClassNamePostconditionIdClassNameIdClassPK_PrevAndNext(postconditionRelationId,
			classNamePostconditionId, classNameId, classPK, orderByComparator);
	}

	/**
	* Removes all the postcondition relations where classNamePostconditionId = &#63; and classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param classNamePostconditionId the class name postcondition ID
	* @param classNameId the class name ID
	* @param classPK the class pk
	*/
	public static void removeByClassNamePostconditionIdClassNameIdClassPK(
		long classNamePostconditionId, long classNameId, long classPK) {
		getPersistence()
			.removeByClassNamePostconditionIdClassNameIdClassPK(classNamePostconditionId,
			classNameId, classPK);
	}

	/**
	* Returns the number of postcondition relations where classNamePostconditionId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param classNamePostconditionId the class name postcondition ID
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @return the number of matching postcondition relations
	*/
	public static int countByClassNamePostconditionIdClassNameIdClassPK(
		long classNamePostconditionId, long classNameId, long classPK) {
		return getPersistence()
				   .countByClassNamePostconditionIdClassNameIdClassPK(classNamePostconditionId,
			classNameId, classPK);
	}

	/**
	* Caches the postcondition relation in the entity cache if it is enabled.
	*
	* @param postconditionRelation the postcondition relation
	*/
	public static void cacheResult(PostconditionRelation postconditionRelation) {
		getPersistence().cacheResult(postconditionRelation);
	}

	/**
	* Caches the postcondition relations in the entity cache if it is enabled.
	*
	* @param postconditionRelations the postcondition relations
	*/
	public static void cacheResult(
		List<PostconditionRelation> postconditionRelations) {
		getPersistence().cacheResult(postconditionRelations);
	}

	/**
	* Creates a new postcondition relation with the primary key. Does not add the postcondition relation to the database.
	*
	* @param postconditionRelationId the primary key for the new postcondition relation
	* @return the new postcondition relation
	*/
	public static PostconditionRelation create(long postconditionRelationId) {
		return getPersistence().create(postconditionRelationId);
	}

	/**
	* Removes the postcondition relation with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param postconditionRelationId the primary key of the postcondition relation
	* @return the postcondition relation that was removed
	* @throws NoSuchPostconditionRelationException if a postcondition relation with the primary key could not be found
	*/
	public static PostconditionRelation remove(long postconditionRelationId)
		throws com.ted.postcondition.exception.NoSuchPostconditionRelationException {
		return getPersistence().remove(postconditionRelationId);
	}

	public static PostconditionRelation updateImpl(
		PostconditionRelation postconditionRelation) {
		return getPersistence().updateImpl(postconditionRelation);
	}

	/**
	* Returns the postcondition relation with the primary key or throws a {@link NoSuchPostconditionRelationException} if it could not be found.
	*
	* @param postconditionRelationId the primary key of the postcondition relation
	* @return the postcondition relation
	* @throws NoSuchPostconditionRelationException if a postcondition relation with the primary key could not be found
	*/
	public static PostconditionRelation findByPrimaryKey(
		long postconditionRelationId)
		throws com.ted.postcondition.exception.NoSuchPostconditionRelationException {
		return getPersistence().findByPrimaryKey(postconditionRelationId);
	}

	/**
	* Returns the postcondition relation with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param postconditionRelationId the primary key of the postcondition relation
	* @return the postcondition relation, or <code>null</code> if a postcondition relation with the primary key could not be found
	*/
	public static PostconditionRelation fetchByPrimaryKey(
		long postconditionRelationId) {
		return getPersistence().fetchByPrimaryKey(postconditionRelationId);
	}

	public static java.util.Map<java.io.Serializable, PostconditionRelation> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the postcondition relations.
	*
	* @return the postcondition relations
	*/
	public static List<PostconditionRelation> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the postcondition relations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostconditionRelationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of postcondition relations
	* @param end the upper bound of the range of postcondition relations (not inclusive)
	* @return the range of postcondition relations
	*/
	public static List<PostconditionRelation> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the postcondition relations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostconditionRelationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of postcondition relations
	* @param end the upper bound of the range of postcondition relations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of postcondition relations
	*/
	public static List<PostconditionRelation> findAll(int start, int end,
		OrderByComparator<PostconditionRelation> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the postcondition relations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostconditionRelationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of postcondition relations
	* @param end the upper bound of the range of postcondition relations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of postcondition relations
	*/
	public static List<PostconditionRelation> findAll(int start, int end,
		OrderByComparator<PostconditionRelation> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the postcondition relations from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of postcondition relations.
	*
	* @return the number of postcondition relations
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static PostconditionRelationPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<PostconditionRelationPersistence, PostconditionRelationPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(PostconditionRelationPersistence.class);

		ServiceTracker<PostconditionRelationPersistence, PostconditionRelationPersistence> serviceTracker =
			new ServiceTracker<PostconditionRelationPersistence, PostconditionRelationPersistence>(bundle.getBundleContext(),
				PostconditionRelationPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}