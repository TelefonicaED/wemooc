package com.ted.lms.web.internal.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.kernel.repository.capabilities.TemporaryFileEntriesCapability;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector;
import com.liferay.portal.kernel.util.FileUtil;

import java.io.InputStream;

public class CourseImageSelectorHelper {
	
	public CourseImageSelectorHelper(long oldSmallImageId, long imageFileEntryId) {
			this.oldSmallImageId = oldSmallImageId;
			this.imageFileEntryId = imageFileEntryId;
		}

		public ImageSelector getImageSelector() throws Exception {
			if (this.oldSmallImageId != 0 || this.imageFileEntryId != this.oldSmallImageId) {

				if (this.imageFileEntryId != 0) {
					FileEntry fileEntry = PortletFileRepositoryUtil.getPortletFileEntry(this.imageFileEntryId);

					this.fileEntryTempFile = fileEntry.isRepositoryCapabilityProvided(TemporaryFileEntriesCapability.class);
					
					InputStream contentStream = fileEntry.getContentStream();
					byte[] imageBytes = FileUtil.getBytes(contentStream);

					return new ImageSelector(imageBytes, fileEntry.getFileName(), fileEntry.getMimeType(), StringPool.BLANK);
				} else {
					return new ImageSelector();
				}
			}

			return null;
		}

		public boolean isFileEntryTempFile() {
			if (this.fileEntryTempFile == null) {
				if ((this.imageFileEntryId == 0) || (this.imageFileEntryId == this.oldSmallImageId)) {
					this.fileEntryTempFile = false;
				} else {
					try {
						FileEntry fileEntry = PortletFileRepositoryUtil.getPortletFileEntry(this.imageFileEntryId);

						this.fileEntryTempFile = fileEntry.isRepositoryCapabilityProvided( TemporaryFileEntriesCapability.class);
					} catch (PortalException pe) {

						// LPS-52675

						if (log.isDebugEnabled()) {
							log.debug(pe, pe);
						}

						return false;
					}
				}
			}

			return this.fileEntryTempFile;
		}

		private static final Log log = LogFactoryUtil.getLog(CourseImageSelectorHelper.class);

		private Boolean fileEntryTempFile;
		private final long imageFileEntryId;
		private final long oldSmallImageId;
}
