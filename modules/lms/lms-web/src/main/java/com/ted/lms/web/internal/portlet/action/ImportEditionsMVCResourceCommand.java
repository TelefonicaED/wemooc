package com.ted.lms.web.internal.portlet.action;

import com.liferay.portal.kernel.backgroundtask.BackgroundTask;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskManagerUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.service.CourseLocalService;
import com.ted.lms.web.internal.background.task.ImportCourseUsersBackgroundTaskExecutor;
import com.ted.lms.web.internal.background.task.ImportEditionsBackgroundTaskExecutor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + LMSPortletKeys.COURSE,
		"mvc.command.name=/courses/import_editions"
	},
	service = MVCResourceCommand.class
)
public class ImportEditionsMVCResourceCommand extends BaseImportCSVMVCResourceCommand {
	
	@Override
	protected long getBackgroundTaskId(ResourceRequest resourceRequest, ResourceResponse resourceResponse,
			String folderName, FileEntry fileEntry) {
		
		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		long backgroundTaskId = 0;
		long parentCourseId = ParamUtil.getLong(resourceRequest, "parentCourseId");
		
		Map<String, Serializable> taskContextMap = new HashMap<>();
		
		taskContextMap.put("parentCourseId", parentCourseId);
		taskContextMap.put("userId", themeDisplay.getUserId());
		taskContextMap.put("groupId", themeDisplay.getScopeGroupId());
		taskContextMap.put("folderName", folderName);
		taskContextMap.put("fileName", fileEntry.getFileName());
		
		try {
			BackgroundTask backgroundTask = BackgroundTaskManagerUtil.addBackgroundTask(themeDisplay.getUserId(), themeDisplay.getScopeGroupId(),
						portal.getPortletId(resourceRequest), ImportEditionsBackgroundTaskExecutor.class.getName(),
						taskContextMap, new ServiceContext());
			backgroundTaskId = backgroundTask.getBackgroundTaskId();
		} catch (PortalException e) {
			e.printStackTrace();
		}
		
		return backgroundTaskId;
	}

	@Override
	protected String getPortletId(ResourceRequest resourceRequest) {
		return portal.getPortletId(resourceRequest);
	}

	@Override
	protected ImportMVCActionCommand getImportCSVMVCActionCommand() {
		return importLayoutsMVCActionCommand;
	}
	
	@Reference(unbind = "-")
	protected void setCourseLocalService(CourseLocalService courseLocalService) {

		this.courseLocalService = courseLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setImportLayoutsMVCActionCommand(
		ImportMVCActionCommand importLayoutsMVCActionCommand) {

		this.importLayoutsMVCActionCommand = importLayoutsMVCActionCommand;
	}

	private ImportMVCActionCommand importLayoutsMVCActionCommand;
	private CourseLocalService courseLocalService;
	
	@Reference
	private Portal portal;
}
