package com.ted.lms.web.internal.portlet.action;

import com.liferay.exportimport.kernel.lar.ExportImportHelper;
import com.liferay.portal.kernel.backgroundtask.BackgroundTask;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskManagerUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portlet.portletconfiguration.action.ActionUtil;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.model.Course;
import com.ted.lms.service.CourseLocalService;
import com.ted.lms.web.internal.background.task.ImportCourseUsersBackgroundTaskExecutor;
import com.ted.lms.web.internal.background.task.ImportEditionsBackgroundTaskExecutor;

import java.io.PrintWriter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.PortletRequestDispatcher;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + LMSPortletKeys.COURSE,
		"mvc.command.name=/courses/import_members"
	},
	service = MVCResourceCommand.class
)
public class ImportMembersMVCResourceCommand extends BaseImportCSVMVCResourceCommand {
	
	@Override
	protected long getBackgroundTaskId(ResourceRequest resourceRequest, ResourceResponse resourceResponse,
			String folderName, FileEntry fileEntry) {
		
		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		long backgroundTaskId = 0;
		long courseId = ParamUtil.getLong(resourceRequest, "courseId");
		long roleId = ParamUtil.getLong(resourceRequest, "roleId");
		
		Map<String, Serializable> taskContextMap = new HashMap<>();
		
		taskContextMap.put("courseId", courseId);
		taskContextMap.put("roleId", roleId);
		taskContextMap.put("userId", themeDisplay.getUserId());
		taskContextMap.put("groupId", themeDisplay.getScopeGroupId());
		taskContextMap.put("folderName", ExportImportHelper.TEMP_FOLDER_NAME + portal.getPortletId(resourceRequest));
		taskContextMap.put("fileName", fileEntry.getFileName());
		
		try {
			BackgroundTask backgroundTask = BackgroundTaskManagerUtil.addBackgroundTask(themeDisplay.getUserId(), themeDisplay.getScopeGroupId(),
						portal.getPortletId(resourceRequest), ImportCourseUsersBackgroundTaskExecutor.class.getName(),
						taskContextMap, new ServiceContext());
			backgroundTaskId = backgroundTask.getBackgroundTaskId();
		} catch (PortalException e) {
			e.printStackTrace();
		}
		
		resourceRequest.setAttribute("roleId", roleId);
		
		return backgroundTaskId;
	}
	
	@Override
	protected String getPortletId(ResourceRequest resourceRequest) {
		return portal.getPortletId(resourceRequest);
	}

	@Override
	protected ImportCSVMVCActionCommand getImportCSVMVCActionCommand() {
		return importLayoutsMVCActionCommand;
	}
	
	@Reference(unbind = "-")
	protected void setCourseLocalService(CourseLocalService courseLocalService) {

		this.courseLocalService = courseLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setImportLayoutsMVCActionCommand(
		ImportCSVMVCActionCommand importLayoutsMVCActionCommand) {

		this.importLayoutsMVCActionCommand = importLayoutsMVCActionCommand;
	}

	private ImportCSVMVCActionCommand importLayoutsMVCActionCommand;
	private CourseLocalService courseLocalService;
	
	@Reference
	private Portal portal;
}
