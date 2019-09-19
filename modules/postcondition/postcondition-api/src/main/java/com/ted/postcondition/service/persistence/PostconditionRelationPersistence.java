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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.ted.postcondition.exception.NoSuchPostconditionRelationException;
import com.ted.postcondition.model.PostconditionRelation;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the postcondition relation service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PostconditionRelationUtil
 * @generated
 */
@ProviderType
public interface PostconditionRelationPersistence
	extends BasePersistence<PostconditionRelation> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PostconditionRelationUtil} to access the postcondition relation persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the postcondition relations where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching postcondition relations
	 */
	public java.util.List<PostconditionRelation> findByUuid(String uuid);

	/**
	 * Returns a range of all the postcondition relations where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PostconditionRelationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of postcondition relations
	 * @param end the upper bound of the range of postcondition relations (not inclusive)
	 * @return the range of matching postcondition relations
	 */
	public java.util.List<PostconditionRelation> findByUuid(
		String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the postcondition relations where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PostconditionRelationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of postcondition relations
	 * @param end the upper bound of the range of postcondition relations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching postcondition relations
	 */
	public java.util.List<PostconditionRelation> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PostconditionRelation>
			orderByComparator);

	/**
	 * Returns an ordered range of all the postcondition relations where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PostconditionRelationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of postcondition relations
	 * @param end the upper bound of the range of postcondition relations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching postcondition relations
	 */
	public java.util.List<PostconditionRelation> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PostconditionRelation>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first postcondition relation in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching postcondition relation
	 * @throws NoSuchPostconditionRelationException if a matching postcondition relation could not be found
	 */
	public PostconditionRelation findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator
				<PostconditionRelation> orderByComparator)
		throws NoSuchPostconditionRelationException;

	/**
	 * Returns the first postcondition relation in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching postcondition relation, or <code>null</code> if a matching postcondition relation could not be found
	 */
	public PostconditionRelation fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PostconditionRelation>
			orderByComparator);

	/**
	 * Returns the last postcondition relation in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching postcondition relation
	 * @throws NoSuchPostconditionRelationException if a matching postcondition relation could not be found
	 */
	public PostconditionRelation findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator
				<PostconditionRelation> orderByComparator)
		throws NoSuchPostconditionRelationException;

	/**
	 * Returns the last postcondition relation in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching postcondition relation, or <code>null</code> if a matching postcondition relation could not be found
	 */
	public PostconditionRelation fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PostconditionRelation>
			orderByComparator);

	/**
	 * Returns the postcondition relations before and after the current postcondition relation in the ordered set where uuid = &#63;.
	 *
	 * @param postconditionRelationId the primary key of the current postcondition relation
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next postcondition relation
	 * @throws NoSuchPostconditionRelationException if a postcondition relation with the primary key could not be found
	 */
	public PostconditionRelation[] findByUuid_PrevAndNext(
			long postconditionRelationId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator
				<PostconditionRelation> orderByComparator)
		throws NoSuchPostconditionRelationException;

	/**
	 * Removes all the postcondition relations where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of postcondition relations where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching postcondition relations
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns all the postcondition relations where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching postcondition relations
	 */
	public java.util.List<PostconditionRelation> findByClassNameIdClassPK(
		long classNameId, long classPK);

	/**
	 * Returns a range of all the postcondition relations where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PostconditionRelationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of postcondition relations
	 * @param end the upper bound of the range of postcondition relations (not inclusive)
	 * @return the range of matching postcondition relations
	 */
	public java.util.List<PostconditionRelation> findByClassNameIdClassPK(
		long classNameId, long classPK, int start, int end);

	/**
	 * Returns an ordered range of all the postcondition relations where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PostconditionRelationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of postcondition relations
	 * @param end the upper bound of the range of postcondition relations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching postcondition relations
	 */
	public java.util.List<PostconditionRelation> findByClassNameIdClassPK(
		long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PostconditionRelation>
			orderByComparator);

	/**
	 * Returns an ordered range of all the postcondition relations where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PostconditionRelationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public java.util.List<PostconditionRelation> findByClassNameIdClassPK(
		long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PostconditionRelation>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first postcondition relation in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching postcondition relation
	 * @throws NoSuchPostconditionRelationException if a matching postcondition relation could not be found
	 */
	public PostconditionRelation findByClassNameIdClassPK_First(
			long classNameId, long classPK,
			com.liferay.portal.kernel.util.OrderByComparator
				<PostconditionRelation> orderByComparator)
		throws NoSuchPostconditionRelationException;

	/**
	 * Returns the first postcondition relation in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching postcondition relation, or <code>null</code> if a matching postcondition relation could not be found
	 */
	public PostconditionRelation fetchByClassNameIdClassPK_First(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<PostconditionRelation>
			orderByComparator);

	/**
	 * Returns the last postcondition relation in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching postcondition relation
	 * @throws NoSuchPostconditionRelationException if a matching postcondition relation could not be found
	 */
	public PostconditionRelation findByClassNameIdClassPK_Last(
			long classNameId, long classPK,
			com.liferay.portal.kernel.util.OrderByComparator
				<PostconditionRelation> orderByComparator)
		throws NoSuchPostconditionRelationException;

	/**
	 * Returns the last postcondition relation in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching postcondition relation, or <code>null</code> if a matching postcondition relation could not be found
	 */
	public PostconditionRelation fetchByClassNameIdClassPK_Last(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<PostconditionRelation>
			orderByComparator);

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
	public PostconditionRelation[] findByClassNameIdClassPK_PrevAndNext(
			long postconditionRelationId, long classNameId, long classPK,
			com.liferay.portal.kernel.util.OrderByComparator
				<PostconditionRelation> orderByComparator)
		throws NoSuchPostconditionRelationException;

	/**
	 * Removes all the postcondition relations where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 */
	public void removeByClassNameIdClassPK(long classNameId, long classPK);

	/**
	 * Returns the number of postcondition relations where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching postcondition relations
	 */
	public int countByClassNameIdClassPK(long classNameId, long classPK);

	/**
	 * Returns all the postcondition relations where classNamePostconditionId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNamePostconditionId the class name postcondition ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching postcondition relations
	 */
	public java.util.List<PostconditionRelation>
		findByClassNamePostconditionIdClassNameIdClassPK(
			long classNamePostconditionId, long classNameId, long classPK);

	/**
	 * Returns a range of all the postcondition relations where classNamePostconditionId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PostconditionRelationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNamePostconditionId the class name postcondition ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of postcondition relations
	 * @param end the upper bound of the range of postcondition relations (not inclusive)
	 * @return the range of matching postcondition relations
	 */
	public java.util.List<PostconditionRelation>
		findByClassNamePostconditionIdClassNameIdClassPK(
			long classNamePostconditionId, long classNameId, long classPK,
			int start, int end);

	/**
	 * Returns an ordered range of all the postcondition relations where classNamePostconditionId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PostconditionRelationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public java.util.List<PostconditionRelation>
		findByClassNamePostconditionIdClassNameIdClassPK(
			long classNamePostconditionId, long classNameId, long classPK,
			int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<PostconditionRelation> orderByComparator);

	/**
	 * Returns an ordered range of all the postcondition relations where classNamePostconditionId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PostconditionRelationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public java.util.List<PostconditionRelation>
		findByClassNamePostconditionIdClassNameIdClassPK(
			long classNamePostconditionId, long classNameId, long classPK,
			int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<PostconditionRelation> orderByComparator,
			boolean retrieveFromCache);

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
	public PostconditionRelation
			findByClassNamePostconditionIdClassNameIdClassPK_First(
				long classNamePostconditionId, long classNameId, long classPK,
				com.liferay.portal.kernel.util.OrderByComparator
					<PostconditionRelation> orderByComparator)
		throws NoSuchPostconditionRelationException;

	/**
	 * Returns the first postcondition relation in the ordered set where classNamePostconditionId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNamePostconditionId the class name postcondition ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching postcondition relation, or <code>null</code> if a matching postcondition relation could not be found
	 */
	public PostconditionRelation
		fetchByClassNamePostconditionIdClassNameIdClassPK_First(
			long classNamePostconditionId, long classNameId, long classPK,
			com.liferay.portal.kernel.util.OrderByComparator
				<PostconditionRelation> orderByComparator);

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
	public PostconditionRelation
			findByClassNamePostconditionIdClassNameIdClassPK_Last(
				long classNamePostconditionId, long classNameId, long classPK,
				com.liferay.portal.kernel.util.OrderByComparator
					<PostconditionRelation> orderByComparator)
		throws NoSuchPostconditionRelationException;

	/**
	 * Returns the last postcondition relation in the ordered set where classNamePostconditionId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNamePostconditionId the class name postcondition ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching postcondition relation, or <code>null</code> if a matching postcondition relation could not be found
	 */
	public PostconditionRelation
		fetchByClassNamePostconditionIdClassNameIdClassPK_Last(
			long classNamePostconditionId, long classNameId, long classPK,
			com.liferay.portal.kernel.util.OrderByComparator
				<PostconditionRelation> orderByComparator);

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
	public PostconditionRelation[]
			findByClassNamePostconditionIdClassNameIdClassPK_PrevAndNext(
				long postconditionRelationId, long classNamePostconditionId,
				long classNameId, long classPK,
				com.liferay.portal.kernel.util.OrderByComparator
					<PostconditionRelation> orderByComparator)
		throws NoSuchPostconditionRelationException;

	/**
	 * Removes all the postcondition relations where classNamePostconditionId = &#63; and classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNamePostconditionId the class name postcondition ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 */
	public void removeByClassNamePostconditionIdClassNameIdClassPK(
		long classNamePostconditionId, long classNameId, long classPK);

	/**
	 * Returns the number of postcondition relations where classNamePostconditionId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNamePostconditionId the class name postcondition ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching postcondition relations
	 */
	public int countByClassNamePostconditionIdClassNameIdClassPK(
		long classNamePostconditionId, long classNameId, long classPK);

	/**
	 * Caches the postcondition relation in the entity cache if it is enabled.
	 *
	 * @param postconditionRelation the postcondition relation
	 */
	public void cacheResult(PostconditionRelation postconditionRelation);

	/**
	 * Caches the postcondition relations in the entity cache if it is enabled.
	 *
	 * @param postconditionRelations the postcondition relations
	 */
	public void cacheResult(
		java.util.List<PostconditionRelation> postconditionRelations);

	/**
	 * Creates a new postcondition relation with the primary key. Does not add the postcondition relation to the database.
	 *
	 * @param postconditionRelationId the primary key for the new postcondition relation
	 * @return the new postcondition relation
	 */
	public PostconditionRelation create(long postconditionRelationId);

	/**
	 * Removes the postcondition relation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param postconditionRelationId the primary key of the postcondition relation
	 * @return the postcondition relation that was removed
	 * @throws NoSuchPostconditionRelationException if a postcondition relation with the primary key could not be found
	 */
	public PostconditionRelation remove(long postconditionRelationId)
		throws NoSuchPostconditionRelationException;

	public PostconditionRelation updateImpl(
		PostconditionRelation postconditionRelation);

	/**
	 * Returns the postcondition relation with the primary key or throws a <code>NoSuchPostconditionRelationException</code> if it could not be found.
	 *
	 * @param postconditionRelationId the primary key of the postcondition relation
	 * @return the postcondition relation
	 * @throws NoSuchPostconditionRelationException if a postcondition relation with the primary key could not be found
	 */
	public PostconditionRelation findByPrimaryKey(long postconditionRelationId)
		throws NoSuchPostconditionRelationException;

	/**
	 * Returns the postcondition relation with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param postconditionRelationId the primary key of the postcondition relation
	 * @return the postcondition relation, or <code>null</code> if a postcondition relation with the primary key could not be found
	 */
	public PostconditionRelation fetchByPrimaryKey(
		long postconditionRelationId);

	/**
	 * Returns all the postcondition relations.
	 *
	 * @return the postcondition relations
	 */
	public java.util.List<PostconditionRelation> findAll();

	/**
	 * Returns a range of all the postcondition relations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PostconditionRelationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of postcondition relations
	 * @param end the upper bound of the range of postcondition relations (not inclusive)
	 * @return the range of postcondition relations
	 */
	public java.util.List<PostconditionRelation> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the postcondition relations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PostconditionRelationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of postcondition relations
	 * @param end the upper bound of the range of postcondition relations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of postcondition relations
	 */
	public java.util.List<PostconditionRelation> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PostconditionRelation>
			orderByComparator);

	/**
	 * Returns an ordered range of all the postcondition relations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PostconditionRelationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of postcondition relations
	 * @param end the upper bound of the range of postcondition relations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of postcondition relations
	 */
	public java.util.List<PostconditionRelation> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PostconditionRelation>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Removes all the postcondition relations from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of postcondition relations.
	 *
	 * @return the number of postcondition relations
	 */
	public int countAll();

}