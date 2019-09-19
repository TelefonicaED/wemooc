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

package com.ted.lms.service.impl;

import com.liferay.portal.aop.AopService;

import com.ted.lms.service.base.CourseTypeLocalServiceBaseImpl;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Repository;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector;
import com.liferay.portal.kernel.servlet.taglib.ui.ImageSelectorProcessor;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.ted.lms.constants.LMSConstants;
import com.ted.lms.exception.SmallImageScaleException;
import com.ted.lms.model.Course;
import com.ted.lms.model.CourseType;
import com.ted.lms.service.base.CourseTypeLocalServiceBaseImpl;
import com.ted.lms.service.util.SmallImageHelper;
import com.ted.lms.settings.CoursesGroupServiceSettings;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

/**
 * The implementation of the course type local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.ted.lms.service.CourseTypeLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CourseTypeLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=com.ted.lms.model.CourseType",
	service = AopService.class
)
public class CourseTypeLocalServiceImpl extends CourseTypeLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>com.ted.lms.service.CourseTypeLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.ted.lms.service.CourseTypeLocalServiceUtil</code>.
	 */
	
	public List<CourseType> getCourseTypes(long companyId){
		return courseTypePersistence.findByCompanyId(companyId);
	}
	
	public List<CourseType> getCourseTypes(long companyId, int start, int end){
		return courseTypePersistence.findByCompanyId(companyId, start, end);
	}
	
	public int countCourseTypes(long companyId) {
		return courseTypePersistence.countByCompanyId(companyId);
	}
	
	public CourseType addCourseType(Map<Locale, String> nameMap, Map<Locale, String> descriptionMap, ImageSelector iconSelector, 
			ServiceContext serviceContext) throws PortalException {
		
		User user = userLocalService.getUser(serviceContext.getUserId());
		Date now = new Date();
		
		long courseTypeId = counterLocalService.increment(CourseType.class.getName());
		CourseType courseType = courseTypePersistence.create(courseTypeId);
		
		courseType.setGroupId(serviceContext.getScopeGroupId());
		courseType.setCompanyId(serviceContext.getCompanyId());
		courseType.setUserId(serviceContext.getUserId());
		courseType.setUserName(user.getFullName());
		courseType.setCreateDate(now);
		courseType.setModifiedDate(now);
		
		courseType.setNameMap(nameMap);
		courseType.setDescriptionMap(descriptionMap);
		
		if(iconSelector != null) {
			courseType.setIconId(addIconCourseType(serviceContext.getUserId(), serviceContext.getScopeGroupId(), courseType.getCourseTypeId(), iconSelector));
		}
		
		courseType = courseTypePersistence.update(courseType, serviceContext);
		
		return courseType;
	}
	
	public CourseType updateCourseType(long courseTypeId, Map<Locale, String> nameMap, Map<Locale, String> descriptionMap, ImageSelector iconSelector, 
			ServiceContext serviceContext) throws PortalException {

		CourseType courseType = courseTypePersistence.fetchByPrimaryKey(courseTypeId);
		
		courseType.setNameMap(nameMap);
		courseType.setDescriptionMap(descriptionMap);

		if(iconSelector != null) {
			courseType.setIconId(addIconCourseType(serviceContext.getUserId(), serviceContext.getScopeGroupId(), courseType.getCourseTypeId(), 
					iconSelector));
		}
		
		courseType = courseTypePersistence.update(courseType, serviceContext);
		
		return courseType;
	}
	
	protected long addIconCourseType(long userId, long groupId, long courseId, ImageSelector iconSelector) throws PortalException {

		byte[] imageBytes = iconSelector.getImageBytes();

		if (imageBytes == null) {
			return 0;
		}

		try {
			CoursesGroupServiceSettings coursesGroupServiceSettings =
				CoursesGroupServiceSettings.getInstance(groupId);

			ImageSelectorProcessor imageSelectorProcessor =
				new ImageSelectorProcessor(iconSelector.getImageBytes());

			imageBytes = imageSelectorProcessor.scaleImage(
				coursesGroupServiceSettings.getSmallImageWidth());

			if (imageBytes == null) {
				throw new SmallImageScaleException();
			}

			Folder folder = addIconFolder(userId, groupId);

			return addProcessedIconFileEntry(
				userId, groupId, courseId, folder.getFolderId(),
				iconSelector.getImageTitle(), iconSelector.getImageMimeType(),
				imageBytes);
		}
		catch (IOException ioe) {
			throw new SmallImageScaleException(ioe);
		}
	}
	
	@Override
	public long addOriginalIconFileEntry(long userId, long groupId, long entryId, ImageSelector imageSelector) throws PortalException {

		byte[] imageBytes = imageSelector.getImageBytes();

		if (imageBytes == null) {
			return 0;
		}

		Folder folder = SmallImageHelper.addSmallImageFolder(userId, groupId, ICON_FOLDER_NAME);

		FileEntry originalFileEntry = PortletFileRepositoryUtil.addPortletFileEntry(
				groupId, userId, null, 0, LMSConstants.SERVICE_NAME, folder.getFolderId(), imageBytes,
				SmallImageHelper.getUniqueFileName(groupId, imageSelector.getImageTitle(), folder.getFolderId(), UNIQUE_FILE_NAME_TRIES),
				imageSelector.getImageMimeType(), true);

		return originalFileEntry.getFileEntryId();
	}
	
	protected Folder addIconFolder(long userId, long groupId) throws PortalException {

		return doAddFolder(userId, groupId, ICON_FOLDER_NAME);
	}
	
	protected Folder doAddFolder(long userId, long groupId, String folderName)
		throws PortalException {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);

		Repository repository = PortletFileRepositoryUtil.addPortletRepository(
			groupId, LMSConstants.SERVICE_NAME, serviceContext);

		return PortletFileRepositoryUtil.addPortletFolder(
			userId, repository.getRepositoryId(),
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, folderName,
			serviceContext);
	}
	
	protected long addProcessedIconFileEntry(
			long userId, long groupId, long entryId, long folderId,
			String title, String mimeType, byte[] bytes)
		throws PortalException {

		if (Validator.isNull(title)) {
			title = StringUtil.randomString() + "_processedIcon_" + entryId;
		}

		FileEntry processedImageFileEntry =
			PortletFileRepositoryUtil.addPortletFileEntry(
				groupId, userId, Course.class.getName(), entryId,
				LMSConstants.SERVICE_NAME, folderId, bytes,
				DLUtil.getUniqueFileName(groupId, folderId, title), mimeType, true);

		return processedImageFileEntry.getFileEntryId();
	}
	
	private static final String ICON_FOLDER_NAME = "Icon";
	private static final int UNIQUE_FILE_NAME_TRIES = 50;
}