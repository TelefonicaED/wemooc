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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.ted.prerequisite.exception.NoSuchPrerequisiteRelationException;
import com.ted.prerequisite.model.PrerequisiteRelation;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the prerequisite relation service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PrerequisiteRelationUtil
 * @generated
 */
@ProviderType
public interface PrerequisiteRelationPersistence
	extends BasePersistence<PrerequisiteRelation> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PrerequisiteRelationUtil} to access the prerequisite relation persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the prerequisite relations where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching prerequisite relations
	 */
	public java.util.List<PrerequisiteRelation> findByUuid(String uuid);

	/**
	 * Returns a range of all the prerequisite relations where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PrerequisiteRelationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of prerequisite relations
	 * @param end the upper bound of the range of prerequisite relations (not inclusive)
	 * @return the range of matching prerequisite relations
	 */
	public java.util.List<PrerequisiteRelation> findByUuid(
		String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the prerequisite relations where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PrerequisiteRelationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of prerequisite relations
	 * @param end the upper bound of the range of prerequisite relations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching prerequisite relations
	 */
	public java.util.List<PrerequisiteRelation> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PrerequisiteRelation>
			orderByComparator);

	/**
	 * Returns an ordered range of all the prerequisite relations where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PrerequisiteRelationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of prerequisite relations
	 * @param end the upper bound of the range of prerequisite relations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching prerequisite relations
	 */
	public java.util.List<PrerequisiteRelation> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PrerequisiteRelation>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first prerequisite relation in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching prerequisite relation
	 * @throws NoSuchPrerequisiteRelationException if a matching prerequisite relation could not be found
	 */
	public PrerequisiteRelation findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator
				<PrerequisiteRelation> orderByComparator)
		throws NoSuchPrerequisiteRelationException;

	/**
	 * Returns the first prerequisite relation in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching prerequisite relation, or <code>null</code> if a matching prerequisite relation could not be found
	 */
	public PrerequisiteRelation fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PrerequisiteRelation>
			orderByComparator);

	/**
	 * Returns the last prerequisite relation in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching prerequisite relation
	 * @throws NoSuchPrerequisiteRelationException if a matching prerequisite relation could not be found
	 */
	public PrerequisiteRelation findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator
				<PrerequisiteRelation> orderByComparator)
		throws NoSuchPrerequisiteRelationException;

	/**
	 * Returns the last prerequisite relation in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching prerequisite relation, or <code>null</code> if a matching prerequisite relation could not be found
	 */
	public PrerequisiteRelation fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PrerequisiteRelation>
			orderByComparator);

	/**
	 * Returns the prerequisite relations before and after the current prerequisite relation in the ordered set where uuid = &#63;.
	 *
	 * @param prerequisiteRelationId the primary key of the current prerequisite relation
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next prerequisite relation
	 * @throws NoSuchPrerequisiteRelationException if a prerequisite relation with the primary key could not be found
	 */
	public PrerequisiteRelation[] findByUuid_PrevAndNext(
			long prerequisiteRelationId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator
				<PrerequisiteRelation> orderByComparator)
		throws NoSuchPrerequisiteRelationException;

	/**
	 * Removes all the prerequisite relations where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of prerequisite relations where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching prerequisite relations
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns all the prerequisite relations where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching prerequisite relations
	 */
	public java.util.List<PrerequisiteRelation> findByClassNameIdClassPK(
		long classNameId, long classPK);

	/**
	 * Returns a range of all the prerequisite relations where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PrerequisiteRelationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of prerequisite relations
	 * @param end the upper bound of the range of prerequisite relations (not inclusive)
	 * @return the range of matching prerequisite relations
	 */
	public java.util.List<PrerequisiteRelation> findByClassNameIdClassPK(
		long classNameId, long classPK, int start, int end);

	/**
	 * Returns an ordered range of all the prerequisite relations where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PrerequisiteRelationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of prerequisite relations
	 * @param end the upper bound of the range of prerequisite relations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching prerequisite relations
	 */
	public java.util.List<PrerequisiteRelation> findByClassNameIdClassPK(
		long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PrerequisiteRelation>
			orderByComparator);

	/**
	 * Returns an ordered range of all the prerequisite relations where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PrerequisiteRelationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public java.util.List<PrerequisiteRelation> findByClassNameIdClassPK(
		long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PrerequisiteRelation>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first prerequisite relation in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching prerequisite relation
	 * @throws NoSuchPrerequisiteRelationException if a matching prerequisite relation could not be found
	 */
	public PrerequisiteRelation findByClassNameIdClassPK_First(
			long classNameId, long classPK,
			com.liferay.portal.kernel.util.OrderByComparator
				<PrerequisiteRelation> orderByComparator)
		throws NoSuchPrerequisiteRelationException;

	/**
	 * Returns the first prerequisite relation in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching prerequisite relation, or <code>null</code> if a matching prerequisite relation could not be found
	 */
	public PrerequisiteRelation fetchByClassNameIdClassPK_First(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<PrerequisiteRelation>
			orderByComparator);

	/**
	 * Returns the last prerequisite relation in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching prerequisite relation
	 * @throws NoSuchPrerequisiteRelationException if a matching prerequisite relation could not be found
	 */
	public PrerequisiteRelation findByClassNameIdClassPK_Last(
			long classNameId, long classPK,
			com.liferay.portal.kernel.util.OrderByComparator
				<PrerequisiteRelation> orderByComparator)
		throws NoSuchPrerequisiteRelationException;

	/**
	 * Returns the last prerequisite relation in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching prerequisite relation, or <code>null</code> if a matching prerequisite relation could not be found
	 */
	public PrerequisiteRelation fetchByClassNameIdClassPK_Last(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<PrerequisiteRelation>
			orderByComparator);

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
	public PrerequisiteRelation[] findByClassNameIdClassPK_PrevAndNext(
			long prerequisiteRelationId, long classNameId, long classPK,
			com.liferay.portal.kernel.util.OrderByComparator
				<PrerequisiteRelation> orderByComparator)
		throws NoSuchPrerequisiteRelationException;

	/**
	 * Removes all the prerequisite relations where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 */
	public void removeByClassNameIdClassPK(long classNameId, long classPK);

	/**
	 * Returns the number of prerequisite relations where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching prerequisite relations
	 */
	public int countByClassNameIdClassPK(long classNameId, long classPK);

	/**
	 * Returns all the prerequisite relations where classNamePrerequisiteId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNamePrerequisiteId the class name prerequisite ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching prerequisite relations
	 */
	public java.util.List<PrerequisiteRelation>
		findByClassNamePrerequisiteIdClassNameIdClassPK(
			long classNamePrerequisiteId, long classNameId, long classPK);

	/**
	 * Returns a range of all the prerequisite relations where classNamePrerequisiteId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PrerequisiteRelationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNamePrerequisiteId the class name prerequisite ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of prerequisite relations
	 * @param end the upper bound of the range of prerequisite relations (not inclusive)
	 * @return the range of matching prerequisite relations
	 */
	public java.util.List<PrerequisiteRelation>
		findByClassNamePrerequisiteIdClassNameIdClassPK(
			long classNamePrerequisiteId, long classNameId, long classPK,
			int start, int end);

	/**
	 * Returns an ordered range of all the prerequisite relations where classNamePrerequisiteId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PrerequisiteRelationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public java.util.List<PrerequisiteRelation>
		findByClassNamePrerequisiteIdClassNameIdClassPK(
			long classNamePrerequisiteId, long classNameId, long classPK,
			int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<PrerequisiteRelation> orderByComparator);

	/**
	 * Returns an ordered range of all the prerequisite relations where classNamePrerequisiteId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PrerequisiteRelationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public java.util.List<PrerequisiteRelation>
		findByClassNamePrerequisiteIdClassNameIdClassPK(
			long classNamePrerequisiteId, long classNameId, long classPK,
			int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<PrerequisiteRelation> orderByComparator,
			boolean retrieveFromCache);

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
	public PrerequisiteRelation
			findByClassNamePrerequisiteIdClassNameIdClassPK_First(
				long classNamePrerequisiteId, long classNameId, long classPK,
				com.liferay.portal.kernel.util.OrderByComparator
					<PrerequisiteRelation> orderByComparator)
		throws NoSuchPrerequisiteRelationException;

	/**
	 * Returns the first prerequisite relation in the ordered set where classNamePrerequisiteId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNamePrerequisiteId the class name prerequisite ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching prerequisite relation, or <code>null</code> if a matching prerequisite relation could not be found
	 */
	public PrerequisiteRelation
		fetchByClassNamePrerequisiteIdClassNameIdClassPK_First(
			long classNamePrerequisiteId, long classNameId, long classPK,
			com.liferay.portal.kernel.util.OrderByComparator
				<PrerequisiteRelation> orderByComparator);

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
	public PrerequisiteRelation
			findByClassNamePrerequisiteIdClassNameIdClassPK_Last(
				long classNamePrerequisiteId, long classNameId, long classPK,
				com.liferay.portal.kernel.util.OrderByComparator
					<PrerequisiteRelation> orderByComparator)
		throws NoSuchPrerequisiteRelationException;

	/**
	 * Returns the last prerequisite relation in the ordered set where classNamePrerequisiteId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNamePrerequisiteId the class name prerequisite ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching prerequisite relation, or <code>null</code> if a matching prerequisite relation could not be found
	 */
	public PrerequisiteRelation
		fetchByClassNamePrerequisiteIdClassNameIdClassPK_Last(
			long classNamePrerequisiteId, long classNameId, long classPK,
			com.liferay.portal.kernel.util.OrderByComparator
				<PrerequisiteRelation> orderByComparator);

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
	public PrerequisiteRelation[]
			findByClassNamePrerequisiteIdClassNameIdClassPK_PrevAndNext(
				long prerequisiteRelationId, long classNamePrerequisiteId,
				long classNameId, long classPK,
				com.liferay.portal.kernel.util.OrderByComparator
					<PrerequisiteRelation> orderByComparator)
		throws NoSuchPrerequisiteRelationException;

	/**
	 * Removes all the prerequisite relations where classNamePrerequisiteId = &#63; and classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNamePrerequisiteId the class name prerequisite ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 */
	public void removeByClassNamePrerequisiteIdClassNameIdClassPK(
		long classNamePrerequisiteId, long classNameId, long classPK);

	/**
	 * Returns the number of prerequisite relations where classNamePrerequisiteId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNamePrerequisiteId the class name prerequisite ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching prerequisite relations
	 */
	public int countByClassNamePrerequisiteIdClassNameIdClassPK(
		long classNamePrerequisiteId, long classNameId, long classPK);

	/**
	 * Caches the prerequisite relation in the entity cache if it is enabled.
	 *
	 * @param prerequisiteRelation the prerequisite relation
	 */
	public void cacheResult(PrerequisiteRelation prerequisiteRelation);

	/**
	 * Caches the prerequisite relations in the entity cache if it is enabled.
	 *
	 * @param prerequisiteRelations the prerequisite relations
	 */
	public void cacheResult(
		java.util.List<PrerequisiteRelation> prerequisiteRelations);

	/**
	 * Creates a new prerequisite relation with the primary key. Does not add the prerequisite relation to the database.
	 *
	 * @param prerequisiteRelationId the primary key for the new prerequisite relation
	 * @return the new prerequisite relation
	 */
	public PrerequisiteRelation create(long prerequisiteRelationId);

	/**
	 * Removes the prerequisite relation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param prerequisiteRelationId the primary key of the prerequisite relation
	 * @return the prerequisite relation that was removed
	 * @throws NoSuchPrerequisiteRelationException if a prerequisite relation with the primary key could not be found
	 */
	public PrerequisiteRelation remove(long prerequisiteRelationId)
		throws NoSuchPrerequisiteRelationException;

	public PrerequisiteRelation updateImpl(
		PrerequisiteRelation prerequisiteRelation);

	/**
	 * Returns the prerequisite relation with the primary key or throws a <code>NoSuchPrerequisiteRelationException</code> if it could not be found.
	 *
	 * @param prerequisiteRelationId the primary key of the prerequisite relation
	 * @return the prerequisite relation
	 * @throws NoSuchPrerequisiteRelationException if a prerequisite relation with the primary key could not be found
	 */
	public PrerequisiteRelation findByPrimaryKey(long prerequisiteRelationId)
		throws NoSuchPrerequisiteRelationException;

	/**
	 * Returns the prerequisite relation with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param prerequisiteRelationId the primary key of the prerequisite relation
	 * @return the prerequisite relation, or <code>null</code> if a prerequisite relation with the primary key could not be found
	 */
	public PrerequisiteRelation fetchByPrimaryKey(long prerequisiteRelationId);

	/**
	 * Returns all the prerequisite relations.
	 *
	 * @return the prerequisite relations
	 */
	public java.util.List<PrerequisiteRelation> findAll();

	/**
	 * Returns a range of all the prerequisite relations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PrerequisiteRelationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of prerequisite relations
	 * @param end the upper bound of the range of prerequisite relations (not inclusive)
	 * @return the range of prerequisite relations
	 */
	public java.util.List<PrerequisiteRelation> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the prerequisite relations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PrerequisiteRelationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of prerequisite relations
	 * @param end the upper bound of the range of prerequisite relations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of prerequisite relations
	 */
	public java.util.List<PrerequisiteRelation> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PrerequisiteRelation>
			orderByComparator);

	/**
	 * Returns an ordered range of all the prerequisite relations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PrerequisiteRelationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of prerequisite relations
	 * @param end the upper bound of the range of prerequisite relations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of prerequisite relations
	 */
	public java.util.List<PrerequisiteRelation> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PrerequisiteRelation>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Removes all the prerequisite relations from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of prerequisite relations.
	 *
	 * @return the number of prerequisite relations
	 */
	public int countAll();

}