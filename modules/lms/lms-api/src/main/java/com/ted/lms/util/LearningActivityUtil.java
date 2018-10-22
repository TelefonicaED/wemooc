package com.ted.lms.util;

import com.liferay.document.library.kernel.exception.NoSuchFolderException;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.ServiceContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component
public class LearningActivityUtil {
	
	public Folder getFolderLearningActivity(long groupId, long actId) throws PortalException {
		try {
			return dlAppLocalService.getFolder(groupId, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, String.valueOf(actId));
		}catch (NoSuchFolderException e) {
			return null;
		}
	}
	
	public Folder createFolderLearningActivity(long userId, long groupId, long actId, String title, ServiceContext serviceContext) throws PortalException {
		return dlAppLocalService.addFolder(userId, groupId, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, String.valueOf(actId), title, serviceContext);
	}
	
	@Reference(unbind = "-")
	protected void setDLAppLocalService(DLAppLocalService dlAppLocalService) {

		this.dlAppLocalService = dlAppLocalService;
	}
	
	private DLAppLocalService dlAppLocalService;
}
