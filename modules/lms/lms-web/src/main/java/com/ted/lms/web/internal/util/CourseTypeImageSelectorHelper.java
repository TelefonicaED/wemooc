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

public class CourseTypeImageSelectorHelper {
	
	public CourseTypeImageSelectorHelper(long oldIconId, long iconFileEntryId) {
			this.oldIconId = oldIconId;
			this.iconFileEntryId = iconFileEntryId;
		}

		public ImageSelector getImageSelector() throws Exception {
			if (this.oldIconId != 0 || this.iconFileEntryId != this.oldIconId) {

				if (this.iconFileEntryId != 0) {
					FileEntry fileEntry = PortletFileRepositoryUtil.getPortletFileEntry(this.iconFileEntryId);

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
				if ((this.iconFileEntryId == 0) || (this.iconFileEntryId == this.oldIconId)) {
					this.fileEntryTempFile = false;
				} else {
					try {
						FileEntry fileEntry = PortletFileRepositoryUtil.getPortletFileEntry(this.iconFileEntryId);

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

		private static final Log log = LogFactoryUtil.getLog(CourseTypeImageSelectorHelper.class);

		private Boolean fileEntryTempFile;
		private final long iconFileEntryId;
		private final long oldIconId;
}
