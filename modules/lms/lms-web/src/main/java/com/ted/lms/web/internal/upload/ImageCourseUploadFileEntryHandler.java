package com.ted.lms.web.internal.upload;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.upload.UploadFileEntryHandler;
import com.ted.lms.constants.LMSConstants;
import com.ted.lms.exception.CourseImageNameException;
import com.ted.lms.exception.CourseImageSizeException;
import com.ted.lms.model.Course;
import com.ted.lms.service.CourseLocalService;
import com.ted.lms.util.LMSPrefsPropsValues;

import java.io.IOException;
import java.io.InputStream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = ImageCourseUploadFileEntryHandler.class)
public class ImageCourseUploadFileEntryHandler implements UploadFileEntryHandler {
	
	@Override
	public FileEntry upload(UploadPortletRequest uploadPortletRequest) throws IOException, PortalException {

		ThemeDisplay themeDisplay = (ThemeDisplay)uploadPortletRequest.getAttribute(WebKeys.THEME_DISPLAY);

		String fileName = uploadPortletRequest.getFileName(PARAMETER_NAME);
		long size = uploadPortletRequest.getSize(PARAMETER_NAME);

		validateFile(fileName, size, themeDisplay.getCompanyId());

		String contentType = uploadPortletRequest.getContentType(PARAMETER_NAME);

		try (InputStream inputStream = uploadPortletRequest.getFileAsStream(PARAMETER_NAME)) {
			return addFileEntry(fileName, contentType, inputStream, themeDisplay);
		}
	}

	protected FileEntry addFileEntry(String fileName, String contentType, InputStream inputStream,ThemeDisplay themeDisplay) throws PortalException {

		Folder folder = courseLocalService.addAttachmentsFolder(themeDisplay.getUserId(), themeDisplay.getScopeGroupId());

		String uniqueFileName = PortletFileRepositoryUtil.getUniqueFileName(themeDisplay.getScopeGroupId(), folder.getFolderId(), fileName);

		return PortletFileRepositoryUtil.addPortletFileEntry(themeDisplay.getScopeGroupId(), themeDisplay.getUserId(),
			Course.class.getName(), 0, LMSConstants.SERVICE_NAME, folder.getFolderId(), inputStream, uniqueFileName, contentType, true);
	}

	@Reference
	protected CourseLocalService courseLocalService;

	@Reference(target = "(resource.name=" + LMSConstants.RESOURCE_NAME + ")")
	protected PortletResourcePermission portletResourcePermission;

	private void validateFile(String fileName, long size, long companyId) throws PortalException {

		long courseImageMaxSize = LMSPrefsPropsValues.getCourseImageMaxSize(companyId);
		
		if (courseImageMaxSize > 0 && size > courseImageMaxSize) {

			throw new CourseImageSizeException();
		}

		String extension = FileUtil.getExtension(fileName);

		String[] imageExtensions = LMSPrefsPropsValues.getCourseImageExtensions(companyId);

		for (String imageExtension : imageExtensions) {
			if (StringPool.STAR.equals(imageExtension) ||
				imageExtension.equals(StringPool.PERIOD + extension)) {

				return;
			}
		}

		throw new CourseImageNameException("Invalid image for file name " + fileName);
	}

	private static final String PARAMETER_NAME = "imageSelectorFileName";
}
