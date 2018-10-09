package com.ted.lms.service.util;

import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Repository;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector;
import com.liferay.portal.kernel.servlet.taglib.ui.ImageSelectorProcessor;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PrefsPropsUtil;
import com.ted.lms.constants.LMSConstants;
import com.ted.lms.exception.SmallImageNameException;
import com.ted.lms.exception.SmallImageScaleException;
import com.ted.lms.util.LMSPrefsPropsValues;

import java.io.IOException;

public class SmallImageHelper {
	
	private static final Log log = LogFactoryUtil.getLog(SmallImageHelper.class);
	
	public static long addSmallImageFileEntry(long userId, long groupId, String className, long classPK, 
				ImageSelector imageSelector, int smallImageWidth, int uniqueFileNameTries, String smallImageFolderName) throws PortalException {

		byte[] imageBytes = imageSelector.getImageBytes();

		if (imageBytes == null) {
			return 0;
		}

		try {

			ImageSelectorProcessor imageSelectorProcessor = new ImageSelectorProcessor(imageSelector.getImageBytes());

			imageBytes = imageSelectorProcessor.scaleImage(smallImageWidth);

			if (imageBytes == null) {
				throw new SmallImageScaleException();
			}

			Folder folder = addSmallImageFolder(userId, groupId, smallImageFolderName);

			return addProcessedImageFileEntry( userId, groupId, className, classPK, folder.getFolderId(),
				imageSelector.getImageTitle(), imageSelector.getImageMimeType(), imageBytes, uniqueFileNameTries);
		}
		catch (IOException ioe) {
			throw new SmallImageScaleException(ioe);
		}
	}
	
	public static void validate(long smallImageFileEntryId, long companyId) throws PortalException {
		String[] imageExtensions = LMSPrefsPropsValues.getModuleImageExtensions(companyId);

		if (smallImageFileEntryId != 0) {
			FileEntry fileEntry = PortletFileRepositoryUtil.getPortletFileEntry(smallImageFileEntryId);

			boolean validSmallImageExtension = false;

			for (String imageExtension : imageExtensions) {
				if (StringPool.STAR.equals(imageExtension) || imageExtension.equals(StringPool.PERIOD + fileEntry.getExtension())) {
					validSmallImageExtension = true;
					break;
				}
			}

			if (!validSmallImageExtension) {
				throw new SmallImageNameException("Invalid small image for file entry " + smallImageFileEntryId);
			}
		}
	}
	
	private static long addProcessedImageFileEntry(
			long userId, long groupId, String className, long classPK, long folderId,
			String title, String mimeType, byte[] bytes, int uniqueFileNameTries)
		throws PortalException {

		if (Validator.isNull(title)) {
			title = StringUtil.randomString() + "_processedImage_" + classPK;
		}

		FileEntry processedImageFileEntry = PortletFileRepositoryUtil.addPortletFileEntry(
				groupId, userId, className, classPK,
				LMSConstants.SERVICE_NAME, folderId, bytes,
				getUniqueFileName(groupId, title, folderId, uniqueFileNameTries), mimeType, true);

		return processedImageFileEntry.getFileEntryId();
	}

	private static FileEntry fetchPortletFileEntry(long groupId, String fileName, long folderId) {

		try {
			return PortletFileRepositoryUtil.getPortletFileEntry(groupId, folderId, fileName);
		}
		catch (PortalException pe) {
			if (log.isDebugEnabled()) {
				log.debug(pe, pe);
			}

			return null;
		}
	}
	
	public static String getUniqueFileName(long groupId, String fileName, long folderId, int uniqueFileNameTries) throws PortalException {

		fileName = FileUtil.stripParentheticalSuffix(fileName);

		FileEntry fileEntry = fetchPortletFileEntry(groupId, fileName, folderId);

		if (fileEntry == null) {
			return fileName;
		}

		int suffix = 1;

		for (int i = 0; i < uniqueFileNameTries; i++) {
			String curFileName = FileUtil.appendParentheticalSuffix(fileName, String.valueOf(suffix));

			fileEntry = fetchPortletFileEntry(groupId, curFileName, folderId);

			if (fileEntry == null) {
				return curFileName;
			}

			suffix++;
		}

		throw new PortalException( StringBundler.concat(
				"Unable to get a unique file name for ", fileName,
				" in folder ", String.valueOf(folderId)));
	}
	
	public static Folder addSmallImageFolder(long userId, long groupId, String smallImageFolderName)
		throws PortalException {

		return doAddFolder(userId, groupId, smallImageFolderName);
	}
	
	private static Folder doAddFolder(long userId, long groupId, String folderName) throws PortalException {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);

		Repository repository = PortletFileRepositoryUtil.addPortletRepository(groupId, LMSConstants.SERVICE_NAME, serviceContext);

		return PortletFileRepositoryUtil.addPortletFolder(
			userId, repository.getRepositoryId(),
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, folderName,
			serviceContext);
	}
}
