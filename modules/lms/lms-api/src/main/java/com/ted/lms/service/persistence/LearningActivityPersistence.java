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

package com.ted.lms.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.ted.lms.exception.NoSuchLearningActivityException;
import com.ted.lms.model.LearningActivity;

/**
 * The persistence interface for the learning activity service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ted.lms.service.persistence.impl.LearningActivityPersistenceImpl
 * @see LearningActivityUtil
 * @generated
 */
@ProviderType
public interface LearningActivityPersistence extends BasePersistence<LearningActivity> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LearningActivityUtil} to access the learning activity persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the learning activities where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching learning activities
	*/
	public java.util.List<LearningActivity> findByUuid(String uuid);

	/**
	* Returns a range of all the learning activities where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @return the range of matching learning activities
	*/
	public java.util.List<LearningActivity> findByUuid(String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the learning activities where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching learning activities
	*/
	public java.util.List<LearningActivity> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator);

	/**
	* Returns an ordered range of all the learning activities where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching learning activities
	*/
	public java.util.List<LearningActivity> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first learning activity in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching learning activity
	* @throws NoSuchLearningActivityException if a matching learning activity could not be found
	*/
	public LearningActivity findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException;

	/**
	* Returns the first learning activity in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching learning activity, or <code>null</code> if a matching learning activity could not be found
	*/
	public LearningActivity fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator);

	/**
	* Returns the last learning activity in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching learning activity
	* @throws NoSuchLearningActivityException if a matching learning activity could not be found
	*/
	public LearningActivity findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException;

	/**
	* Returns the last learning activity in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching learning activity, or <code>null</code> if a matching learning activity could not be found
	*/
	public LearningActivity fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator);

	/**
	* Returns the learning activities before and after the current learning activity in the ordered set where uuid = &#63;.
	*
	* @param actId the primary key of the current learning activity
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next learning activity
	* @throws NoSuchLearningActivityException if a learning activity with the primary key could not be found
	*/
	public LearningActivity[] findByUuid_PrevAndNext(long actId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException;

	/**
	* Removes all the learning activities where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of learning activities where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching learning activities
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the learning activity where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchLearningActivityException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching learning activity
	* @throws NoSuchLearningActivityException if a matching learning activity could not be found
	*/
	public LearningActivity findByUUID_G(String uuid, long groupId)
		throws NoSuchLearningActivityException;

	/**
	* Returns the learning activity where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching learning activity, or <code>null</code> if a matching learning activity could not be found
	*/
	public LearningActivity fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the learning activity where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching learning activity, or <code>null</code> if a matching learning activity could not be found
	*/
	public LearningActivity fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the learning activity where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the learning activity that was removed
	*/
	public LearningActivity removeByUUID_G(String uuid, long groupId)
		throws NoSuchLearningActivityException;

	/**
	* Returns the number of learning activities where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching learning activities
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the learning activities where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching learning activities
	*/
	public java.util.List<LearningActivity> findByUuid_C(String uuid,
		long companyId);

	/**
	* Returns a range of all the learning activities where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @return the range of matching learning activities
	*/
	public java.util.List<LearningActivity> findByUuid_C(String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the learning activities where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching learning activities
	*/
	public java.util.List<LearningActivity> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator);

	/**
	* Returns an ordered range of all the learning activities where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching learning activities
	*/
	public java.util.List<LearningActivity> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first learning activity in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching learning activity
	* @throws NoSuchLearningActivityException if a matching learning activity could not be found
	*/
	public LearningActivity findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException;

	/**
	* Returns the first learning activity in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching learning activity, or <code>null</code> if a matching learning activity could not be found
	*/
	public LearningActivity fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator);

	/**
	* Returns the last learning activity in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching learning activity
	* @throws NoSuchLearningActivityException if a matching learning activity could not be found
	*/
	public LearningActivity findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException;

	/**
	* Returns the last learning activity in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching learning activity, or <code>null</code> if a matching learning activity could not be found
	*/
	public LearningActivity fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator);

	/**
	* Returns the learning activities before and after the current learning activity in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param actId the primary key of the current learning activity
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next learning activity
	* @throws NoSuchLearningActivityException if a learning activity with the primary key could not be found
	*/
	public LearningActivity[] findByUuid_C_PrevAndNext(long actId, String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException;

	/**
	* Removes all the learning activities where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of learning activities where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching learning activities
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns all the learning activities where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching learning activities
	*/
	public java.util.List<LearningActivity> findByCompanyId(long companyId);

	/**
	* Returns a range of all the learning activities where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @return the range of matching learning activities
	*/
	public java.util.List<LearningActivity> findByCompanyId(long companyId,
		int start, int end);

	/**
	* Returns an ordered range of all the learning activities where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching learning activities
	*/
	public java.util.List<LearningActivity> findByCompanyId(long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator);

	/**
	* Returns an ordered range of all the learning activities where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching learning activities
	*/
	public java.util.List<LearningActivity> findByCompanyId(long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first learning activity in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching learning activity
	* @throws NoSuchLearningActivityException if a matching learning activity could not be found
	*/
	public LearningActivity findByCompanyId_First(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException;

	/**
	* Returns the first learning activity in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching learning activity, or <code>null</code> if a matching learning activity could not be found
	*/
	public LearningActivity fetchByCompanyId_First(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator);

	/**
	* Returns the last learning activity in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching learning activity
	* @throws NoSuchLearningActivityException if a matching learning activity could not be found
	*/
	public LearningActivity findByCompanyId_Last(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException;

	/**
	* Returns the last learning activity in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching learning activity, or <code>null</code> if a matching learning activity could not be found
	*/
	public LearningActivity fetchByCompanyId_Last(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator);

	/**
	* Returns the learning activities before and after the current learning activity in the ordered set where companyId = &#63;.
	*
	* @param actId the primary key of the current learning activity
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next learning activity
	* @throws NoSuchLearningActivityException if a learning activity with the primary key could not be found
	*/
	public LearningActivity[] findByCompanyId_PrevAndNext(long actId,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException;

	/**
	* Removes all the learning activities where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	*/
	public void removeByCompanyId(long companyId);

	/**
	* Returns the number of learning activities where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching learning activities
	*/
	public int countByCompanyId(long companyId);

	/**
	* Returns all the learning activities where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching learning activities
	*/
	public java.util.List<LearningActivity> findByGroupId(long groupId);

	/**
	* Returns a range of all the learning activities where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @return the range of matching learning activities
	*/
	public java.util.List<LearningActivity> findByGroupId(long groupId,
		int start, int end);

	/**
	* Returns an ordered range of all the learning activities where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching learning activities
	*/
	public java.util.List<LearningActivity> findByGroupId(long groupId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator);

	/**
	* Returns an ordered range of all the learning activities where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching learning activities
	*/
	public java.util.List<LearningActivity> findByGroupId(long groupId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first learning activity in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching learning activity
	* @throws NoSuchLearningActivityException if a matching learning activity could not be found
	*/
	public LearningActivity findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException;

	/**
	* Returns the first learning activity in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching learning activity, or <code>null</code> if a matching learning activity could not be found
	*/
	public LearningActivity fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator);

	/**
	* Returns the last learning activity in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching learning activity
	* @throws NoSuchLearningActivityException if a matching learning activity could not be found
	*/
	public LearningActivity findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException;

	/**
	* Returns the last learning activity in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching learning activity, or <code>null</code> if a matching learning activity could not be found
	*/
	public LearningActivity fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator);

	/**
	* Returns the learning activities before and after the current learning activity in the ordered set where groupId = &#63;.
	*
	* @param actId the primary key of the current learning activity
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next learning activity
	* @throws NoSuchLearningActivityException if a learning activity with the primary key could not be found
	*/
	public LearningActivity[] findByGroupId_PrevAndNext(long actId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException;

	/**
	* Returns all the learning activities that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching learning activities that the user has permission to view
	*/
	public java.util.List<LearningActivity> filterFindByGroupId(long groupId);

	/**
	* Returns a range of all the learning activities that the user has permission to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @return the range of matching learning activities that the user has permission to view
	*/
	public java.util.List<LearningActivity> filterFindByGroupId(long groupId,
		int start, int end);

	/**
	* Returns an ordered range of all the learning activities that the user has permissions to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching learning activities that the user has permission to view
	*/
	public java.util.List<LearningActivity> filterFindByGroupId(long groupId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator);

	/**
	* Returns the learning activities before and after the current learning activity in the ordered set of learning activities that the user has permission to view where groupId = &#63;.
	*
	* @param actId the primary key of the current learning activity
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next learning activity
	* @throws NoSuchLearningActivityException if a learning activity with the primary key could not be found
	*/
	public LearningActivity[] filterFindByGroupId_PrevAndNext(long actId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException;

	/**
	* Removes all the learning activities where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of learning activities where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching learning activities
	*/
	public int countByGroupId(long groupId);

	/**
	* Returns the number of learning activities that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching learning activities that the user has permission to view
	*/
	public int filterCountByGroupId(long groupId);

	/**
	* Returns all the learning activities where moduleId = &#63;.
	*
	* @param moduleId the module ID
	* @return the matching learning activities
	*/
	public java.util.List<LearningActivity> findByModuleId(long moduleId);

	/**
	* Returns a range of all the learning activities where moduleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param moduleId the module ID
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @return the range of matching learning activities
	*/
	public java.util.List<LearningActivity> findByModuleId(long moduleId,
		int start, int end);

	/**
	* Returns an ordered range of all the learning activities where moduleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param moduleId the module ID
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching learning activities
	*/
	public java.util.List<LearningActivity> findByModuleId(long moduleId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator);

	/**
	* Returns an ordered range of all the learning activities where moduleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param moduleId the module ID
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching learning activities
	*/
	public java.util.List<LearningActivity> findByModuleId(long moduleId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first learning activity in the ordered set where moduleId = &#63;.
	*
	* @param moduleId the module ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching learning activity
	* @throws NoSuchLearningActivityException if a matching learning activity could not be found
	*/
	public LearningActivity findByModuleId_First(long moduleId,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException;

	/**
	* Returns the first learning activity in the ordered set where moduleId = &#63;.
	*
	* @param moduleId the module ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching learning activity, or <code>null</code> if a matching learning activity could not be found
	*/
	public LearningActivity fetchByModuleId_First(long moduleId,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator);

	/**
	* Returns the last learning activity in the ordered set where moduleId = &#63;.
	*
	* @param moduleId the module ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching learning activity
	* @throws NoSuchLearningActivityException if a matching learning activity could not be found
	*/
	public LearningActivity findByModuleId_Last(long moduleId,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException;

	/**
	* Returns the last learning activity in the ordered set where moduleId = &#63;.
	*
	* @param moduleId the module ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching learning activity, or <code>null</code> if a matching learning activity could not be found
	*/
	public LearningActivity fetchByModuleId_Last(long moduleId,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator);

	/**
	* Returns the learning activities before and after the current learning activity in the ordered set where moduleId = &#63;.
	*
	* @param actId the primary key of the current learning activity
	* @param moduleId the module ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next learning activity
	* @throws NoSuchLearningActivityException if a learning activity with the primary key could not be found
	*/
	public LearningActivity[] findByModuleId_PrevAndNext(long actId,
		long moduleId,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException;

	/**
	* Removes all the learning activities where moduleId = &#63; from the database.
	*
	* @param moduleId the module ID
	*/
	public void removeByModuleId(long moduleId);

	/**
	* Returns the number of learning activities where moduleId = &#63;.
	*
	* @param moduleId the module ID
	* @return the number of matching learning activities
	*/
	public int countByModuleId(long moduleId);

	/**
	* Returns all the learning activities where moduleId = &#63; and required = &#63;.
	*
	* @param moduleId the module ID
	* @param required the required
	* @return the matching learning activities
	*/
	public java.util.List<LearningActivity> findByModuleIdRequired(
		long moduleId, boolean required);

	/**
	* Returns a range of all the learning activities where moduleId = &#63; and required = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param moduleId the module ID
	* @param required the required
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @return the range of matching learning activities
	*/
	public java.util.List<LearningActivity> findByModuleIdRequired(
		long moduleId, boolean required, int start, int end);

	/**
	* Returns an ordered range of all the learning activities where moduleId = &#63; and required = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param moduleId the module ID
	* @param required the required
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching learning activities
	*/
	public java.util.List<LearningActivity> findByModuleIdRequired(
		long moduleId, boolean required, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator);

	/**
	* Returns an ordered range of all the learning activities where moduleId = &#63; and required = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param moduleId the module ID
	* @param required the required
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching learning activities
	*/
	public java.util.List<LearningActivity> findByModuleIdRequired(
		long moduleId, boolean required, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first learning activity in the ordered set where moduleId = &#63; and required = &#63;.
	*
	* @param moduleId the module ID
	* @param required the required
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching learning activity
	* @throws NoSuchLearningActivityException if a matching learning activity could not be found
	*/
	public LearningActivity findByModuleIdRequired_First(long moduleId,
		boolean required,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException;

	/**
	* Returns the first learning activity in the ordered set where moduleId = &#63; and required = &#63;.
	*
	* @param moduleId the module ID
	* @param required the required
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching learning activity, or <code>null</code> if a matching learning activity could not be found
	*/
	public LearningActivity fetchByModuleIdRequired_First(long moduleId,
		boolean required,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator);

	/**
	* Returns the last learning activity in the ordered set where moduleId = &#63; and required = &#63;.
	*
	* @param moduleId the module ID
	* @param required the required
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching learning activity
	* @throws NoSuchLearningActivityException if a matching learning activity could not be found
	*/
	public LearningActivity findByModuleIdRequired_Last(long moduleId,
		boolean required,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException;

	/**
	* Returns the last learning activity in the ordered set where moduleId = &#63; and required = &#63;.
	*
	* @param moduleId the module ID
	* @param required the required
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching learning activity, or <code>null</code> if a matching learning activity could not be found
	*/
	public LearningActivity fetchByModuleIdRequired_Last(long moduleId,
		boolean required,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator);

	/**
	* Returns the learning activities before and after the current learning activity in the ordered set where moduleId = &#63; and required = &#63;.
	*
	* @param actId the primary key of the current learning activity
	* @param moduleId the module ID
	* @param required the required
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next learning activity
	* @throws NoSuchLearningActivityException if a learning activity with the primary key could not be found
	*/
	public LearningActivity[] findByModuleIdRequired_PrevAndNext(long actId,
		long moduleId, boolean required,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException;

	/**
	* Removes all the learning activities where moduleId = &#63; and required = &#63; from the database.
	*
	* @param moduleId the module ID
	* @param required the required
	*/
	public void removeByModuleIdRequired(long moduleId, boolean required);

	/**
	* Returns the number of learning activities where moduleId = &#63; and required = &#63;.
	*
	* @param moduleId the module ID
	* @param required the required
	* @return the number of matching learning activities
	*/
	public int countByModuleIdRequired(long moduleId, boolean required);

	/**
	* Returns all the learning activities where groupId = &#63; and required = &#63;.
	*
	* @param groupId the group ID
	* @param required the required
	* @return the matching learning activities
	*/
	public java.util.List<LearningActivity> findByGroupIdRequired(
		long groupId, boolean required);

	/**
	* Returns a range of all the learning activities where groupId = &#63; and required = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param required the required
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @return the range of matching learning activities
	*/
	public java.util.List<LearningActivity> findByGroupIdRequired(
		long groupId, boolean required, int start, int end);

	/**
	* Returns an ordered range of all the learning activities where groupId = &#63; and required = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param required the required
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching learning activities
	*/
	public java.util.List<LearningActivity> findByGroupIdRequired(
		long groupId, boolean required, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator);

	/**
	* Returns an ordered range of all the learning activities where groupId = &#63; and required = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param required the required
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching learning activities
	*/
	public java.util.List<LearningActivity> findByGroupIdRequired(
		long groupId, boolean required, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first learning activity in the ordered set where groupId = &#63; and required = &#63;.
	*
	* @param groupId the group ID
	* @param required the required
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching learning activity
	* @throws NoSuchLearningActivityException if a matching learning activity could not be found
	*/
	public LearningActivity findByGroupIdRequired_First(long groupId,
		boolean required,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException;

	/**
	* Returns the first learning activity in the ordered set where groupId = &#63; and required = &#63;.
	*
	* @param groupId the group ID
	* @param required the required
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching learning activity, or <code>null</code> if a matching learning activity could not be found
	*/
	public LearningActivity fetchByGroupIdRequired_First(long groupId,
		boolean required,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator);

	/**
	* Returns the last learning activity in the ordered set where groupId = &#63; and required = &#63;.
	*
	* @param groupId the group ID
	* @param required the required
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching learning activity
	* @throws NoSuchLearningActivityException if a matching learning activity could not be found
	*/
	public LearningActivity findByGroupIdRequired_Last(long groupId,
		boolean required,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException;

	/**
	* Returns the last learning activity in the ordered set where groupId = &#63; and required = &#63;.
	*
	* @param groupId the group ID
	* @param required the required
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching learning activity, or <code>null</code> if a matching learning activity could not be found
	*/
	public LearningActivity fetchByGroupIdRequired_Last(long groupId,
		boolean required,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator);

	/**
	* Returns the learning activities before and after the current learning activity in the ordered set where groupId = &#63; and required = &#63;.
	*
	* @param actId the primary key of the current learning activity
	* @param groupId the group ID
	* @param required the required
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next learning activity
	* @throws NoSuchLearningActivityException if a learning activity with the primary key could not be found
	*/
	public LearningActivity[] findByGroupIdRequired_PrevAndNext(long actId,
		long groupId, boolean required,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException;

	/**
	* Returns all the learning activities that the user has permission to view where groupId = &#63; and required = &#63;.
	*
	* @param groupId the group ID
	* @param required the required
	* @return the matching learning activities that the user has permission to view
	*/
	public java.util.List<LearningActivity> filterFindByGroupIdRequired(
		long groupId, boolean required);

	/**
	* Returns a range of all the learning activities that the user has permission to view where groupId = &#63; and required = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param required the required
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @return the range of matching learning activities that the user has permission to view
	*/
	public java.util.List<LearningActivity> filterFindByGroupIdRequired(
		long groupId, boolean required, int start, int end);

	/**
	* Returns an ordered range of all the learning activities that the user has permissions to view where groupId = &#63; and required = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param required the required
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching learning activities that the user has permission to view
	*/
	public java.util.List<LearningActivity> filterFindByGroupIdRequired(
		long groupId, boolean required, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator);

	/**
	* Returns the learning activities before and after the current learning activity in the ordered set of learning activities that the user has permission to view where groupId = &#63; and required = &#63;.
	*
	* @param actId the primary key of the current learning activity
	* @param groupId the group ID
	* @param required the required
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next learning activity
	* @throws NoSuchLearningActivityException if a learning activity with the primary key could not be found
	*/
	public LearningActivity[] filterFindByGroupIdRequired_PrevAndNext(
		long actId, long groupId, boolean required,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException;

	/**
	* Removes all the learning activities where groupId = &#63; and required = &#63; from the database.
	*
	* @param groupId the group ID
	* @param required the required
	*/
	public void removeByGroupIdRequired(long groupId, boolean required);

	/**
	* Returns the number of learning activities where groupId = &#63; and required = &#63;.
	*
	* @param groupId the group ID
	* @param required the required
	* @return the number of matching learning activities
	*/
	public int countByGroupIdRequired(long groupId, boolean required);

	/**
	* Returns the number of learning activities that the user has permission to view where groupId = &#63; and required = &#63;.
	*
	* @param groupId the group ID
	* @param required the required
	* @return the number of matching learning activities that the user has permission to view
	*/
	public int filterCountByGroupIdRequired(long groupId, boolean required);

	/**
	* Returns all the learning activities where typeId = &#63;.
	*
	* @param typeId the type ID
	* @return the matching learning activities
	*/
	public java.util.List<LearningActivity> findByTypeId(long typeId);

	/**
	* Returns a range of all the learning activities where typeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param typeId the type ID
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @return the range of matching learning activities
	*/
	public java.util.List<LearningActivity> findByTypeId(long typeId,
		int start, int end);

	/**
	* Returns an ordered range of all the learning activities where typeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param typeId the type ID
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching learning activities
	*/
	public java.util.List<LearningActivity> findByTypeId(long typeId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator);

	/**
	* Returns an ordered range of all the learning activities where typeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param typeId the type ID
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching learning activities
	*/
	public java.util.List<LearningActivity> findByTypeId(long typeId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first learning activity in the ordered set where typeId = &#63;.
	*
	* @param typeId the type ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching learning activity
	* @throws NoSuchLearningActivityException if a matching learning activity could not be found
	*/
	public LearningActivity findByTypeId_First(long typeId,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException;

	/**
	* Returns the first learning activity in the ordered set where typeId = &#63;.
	*
	* @param typeId the type ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching learning activity, or <code>null</code> if a matching learning activity could not be found
	*/
	public LearningActivity fetchByTypeId_First(long typeId,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator);

	/**
	* Returns the last learning activity in the ordered set where typeId = &#63;.
	*
	* @param typeId the type ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching learning activity
	* @throws NoSuchLearningActivityException if a matching learning activity could not be found
	*/
	public LearningActivity findByTypeId_Last(long typeId,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException;

	/**
	* Returns the last learning activity in the ordered set where typeId = &#63;.
	*
	* @param typeId the type ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching learning activity, or <code>null</code> if a matching learning activity could not be found
	*/
	public LearningActivity fetchByTypeId_Last(long typeId,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator);

	/**
	* Returns the learning activities before and after the current learning activity in the ordered set where typeId = &#63;.
	*
	* @param actId the primary key of the current learning activity
	* @param typeId the type ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next learning activity
	* @throws NoSuchLearningActivityException if a learning activity with the primary key could not be found
	*/
	public LearningActivity[] findByTypeId_PrevAndNext(long actId, long typeId,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException;

	/**
	* Removes all the learning activities where typeId = &#63; from the database.
	*
	* @param typeId the type ID
	*/
	public void removeByTypeId(long typeId);

	/**
	* Returns the number of learning activities where typeId = &#63;.
	*
	* @param typeId the type ID
	* @return the number of matching learning activities
	*/
	public int countByTypeId(long typeId);

	/**
	* Returns all the learning activities where moduleId = &#63; and priority &gt; &#63;.
	*
	* @param moduleId the module ID
	* @param priority the priority
	* @return the matching learning activities
	*/
	public java.util.List<LearningActivity> findByModuleIdNextLearningActivities(
		long moduleId, long priority);

	/**
	* Returns a range of all the learning activities where moduleId = &#63; and priority &gt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param moduleId the module ID
	* @param priority the priority
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @return the range of matching learning activities
	*/
	public java.util.List<LearningActivity> findByModuleIdNextLearningActivities(
		long moduleId, long priority, int start, int end);

	/**
	* Returns an ordered range of all the learning activities where moduleId = &#63; and priority &gt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param moduleId the module ID
	* @param priority the priority
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching learning activities
	*/
	public java.util.List<LearningActivity> findByModuleIdNextLearningActivities(
		long moduleId, long priority, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator);

	/**
	* Returns an ordered range of all the learning activities where moduleId = &#63; and priority &gt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param moduleId the module ID
	* @param priority the priority
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching learning activities
	*/
	public java.util.List<LearningActivity> findByModuleIdNextLearningActivities(
		long moduleId, long priority, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first learning activity in the ordered set where moduleId = &#63; and priority &gt; &#63;.
	*
	* @param moduleId the module ID
	* @param priority the priority
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching learning activity
	* @throws NoSuchLearningActivityException if a matching learning activity could not be found
	*/
	public LearningActivity findByModuleIdNextLearningActivities_First(
		long moduleId, long priority,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException;

	/**
	* Returns the first learning activity in the ordered set where moduleId = &#63; and priority &gt; &#63;.
	*
	* @param moduleId the module ID
	* @param priority the priority
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching learning activity, or <code>null</code> if a matching learning activity could not be found
	*/
	public LearningActivity fetchByModuleIdNextLearningActivities_First(
		long moduleId, long priority,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator);

	/**
	* Returns the last learning activity in the ordered set where moduleId = &#63; and priority &gt; &#63;.
	*
	* @param moduleId the module ID
	* @param priority the priority
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching learning activity
	* @throws NoSuchLearningActivityException if a matching learning activity could not be found
	*/
	public LearningActivity findByModuleIdNextLearningActivities_Last(
		long moduleId, long priority,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException;

	/**
	* Returns the last learning activity in the ordered set where moduleId = &#63; and priority &gt; &#63;.
	*
	* @param moduleId the module ID
	* @param priority the priority
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching learning activity, or <code>null</code> if a matching learning activity could not be found
	*/
	public LearningActivity fetchByModuleIdNextLearningActivities_Last(
		long moduleId, long priority,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator);

	/**
	* Returns the learning activities before and after the current learning activity in the ordered set where moduleId = &#63; and priority &gt; &#63;.
	*
	* @param actId the primary key of the current learning activity
	* @param moduleId the module ID
	* @param priority the priority
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next learning activity
	* @throws NoSuchLearningActivityException if a learning activity with the primary key could not be found
	*/
	public LearningActivity[] findByModuleIdNextLearningActivities_PrevAndNext(
		long actId, long moduleId, long priority,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException;

	/**
	* Removes all the learning activities where moduleId = &#63; and priority &gt; &#63; from the database.
	*
	* @param moduleId the module ID
	* @param priority the priority
	*/
	public void removeByModuleIdNextLearningActivities(long moduleId,
		long priority);

	/**
	* Returns the number of learning activities where moduleId = &#63; and priority &gt; &#63;.
	*
	* @param moduleId the module ID
	* @param priority the priority
	* @return the number of matching learning activities
	*/
	public int countByModuleIdNextLearningActivities(long moduleId,
		long priority);

	/**
	* Returns all the learning activities where moduleId = &#63; and priority &lt; &#63;.
	*
	* @param moduleId the module ID
	* @param priority the priority
	* @return the matching learning activities
	*/
	public java.util.List<LearningActivity> findByModuleIdPreviousLearningActivities(
		long moduleId, long priority);

	/**
	* Returns a range of all the learning activities where moduleId = &#63; and priority &lt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param moduleId the module ID
	* @param priority the priority
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @return the range of matching learning activities
	*/
	public java.util.List<LearningActivity> findByModuleIdPreviousLearningActivities(
		long moduleId, long priority, int start, int end);

	/**
	* Returns an ordered range of all the learning activities where moduleId = &#63; and priority &lt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param moduleId the module ID
	* @param priority the priority
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching learning activities
	*/
	public java.util.List<LearningActivity> findByModuleIdPreviousLearningActivities(
		long moduleId, long priority, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator);

	/**
	* Returns an ordered range of all the learning activities where moduleId = &#63; and priority &lt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param moduleId the module ID
	* @param priority the priority
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching learning activities
	*/
	public java.util.List<LearningActivity> findByModuleIdPreviousLearningActivities(
		long moduleId, long priority, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first learning activity in the ordered set where moduleId = &#63; and priority &lt; &#63;.
	*
	* @param moduleId the module ID
	* @param priority the priority
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching learning activity
	* @throws NoSuchLearningActivityException if a matching learning activity could not be found
	*/
	public LearningActivity findByModuleIdPreviousLearningActivities_First(
		long moduleId, long priority,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException;

	/**
	* Returns the first learning activity in the ordered set where moduleId = &#63; and priority &lt; &#63;.
	*
	* @param moduleId the module ID
	* @param priority the priority
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching learning activity, or <code>null</code> if a matching learning activity could not be found
	*/
	public LearningActivity fetchByModuleIdPreviousLearningActivities_First(
		long moduleId, long priority,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator);

	/**
	* Returns the last learning activity in the ordered set where moduleId = &#63; and priority &lt; &#63;.
	*
	* @param moduleId the module ID
	* @param priority the priority
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching learning activity
	* @throws NoSuchLearningActivityException if a matching learning activity could not be found
	*/
	public LearningActivity findByModuleIdPreviousLearningActivities_Last(
		long moduleId, long priority,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException;

	/**
	* Returns the last learning activity in the ordered set where moduleId = &#63; and priority &lt; &#63;.
	*
	* @param moduleId the module ID
	* @param priority the priority
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching learning activity, or <code>null</code> if a matching learning activity could not be found
	*/
	public LearningActivity fetchByModuleIdPreviousLearningActivities_Last(
		long moduleId, long priority,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator);

	/**
	* Returns the learning activities before and after the current learning activity in the ordered set where moduleId = &#63; and priority &lt; &#63;.
	*
	* @param actId the primary key of the current learning activity
	* @param moduleId the module ID
	* @param priority the priority
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next learning activity
	* @throws NoSuchLearningActivityException if a learning activity with the primary key could not be found
	*/
	public LearningActivity[] findByModuleIdPreviousLearningActivities_PrevAndNext(
		long actId, long moduleId, long priority,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator)
		throws NoSuchLearningActivityException;

	/**
	* Removes all the learning activities where moduleId = &#63; and priority &lt; &#63; from the database.
	*
	* @param moduleId the module ID
	* @param priority the priority
	*/
	public void removeByModuleIdPreviousLearningActivities(long moduleId,
		long priority);

	/**
	* Returns the number of learning activities where moduleId = &#63; and priority &lt; &#63;.
	*
	* @param moduleId the module ID
	* @param priority the priority
	* @return the number of matching learning activities
	*/
	public int countByModuleIdPreviousLearningActivities(long moduleId,
		long priority);

	/**
	* Caches the learning activity in the entity cache if it is enabled.
	*
	* @param learningActivity the learning activity
	*/
	public void cacheResult(LearningActivity learningActivity);

	/**
	* Caches the learning activities in the entity cache if it is enabled.
	*
	* @param learningActivities the learning activities
	*/
	public void cacheResult(java.util.List<LearningActivity> learningActivities);

	/**
	* Creates a new learning activity with the primary key. Does not add the learning activity to the database.
	*
	* @param actId the primary key for the new learning activity
	* @return the new learning activity
	*/
	public LearningActivity create(long actId);

	/**
	* Removes the learning activity with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param actId the primary key of the learning activity
	* @return the learning activity that was removed
	* @throws NoSuchLearningActivityException if a learning activity with the primary key could not be found
	*/
	public LearningActivity remove(long actId)
		throws NoSuchLearningActivityException;

	public LearningActivity updateImpl(LearningActivity learningActivity);

	/**
	* Returns the learning activity with the primary key or throws a {@link NoSuchLearningActivityException} if it could not be found.
	*
	* @param actId the primary key of the learning activity
	* @return the learning activity
	* @throws NoSuchLearningActivityException if a learning activity with the primary key could not be found
	*/
	public LearningActivity findByPrimaryKey(long actId)
		throws NoSuchLearningActivityException;

	/**
	* Returns the learning activity with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param actId the primary key of the learning activity
	* @return the learning activity, or <code>null</code> if a learning activity with the primary key could not be found
	*/
	public LearningActivity fetchByPrimaryKey(long actId);

	@Override
	public java.util.Map<java.io.Serializable, LearningActivity> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the learning activities.
	*
	* @return the learning activities
	*/
	public java.util.List<LearningActivity> findAll();

	/**
	* Returns a range of all the learning activities.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @return the range of learning activities
	*/
	public java.util.List<LearningActivity> findAll(int start, int end);

	/**
	* Returns an ordered range of all the learning activities.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of learning activities
	*/
	public java.util.List<LearningActivity> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator);

	/**
	* Returns an ordered range of all the learning activities.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LearningActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of learning activities
	* @param end the upper bound of the range of learning activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of learning activities
	*/
	public java.util.List<LearningActivity> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LearningActivity> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the learning activities from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of learning activities.
	*
	* @return the number of learning activities
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}