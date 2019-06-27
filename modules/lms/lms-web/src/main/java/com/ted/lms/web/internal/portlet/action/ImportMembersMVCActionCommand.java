package com.ted.lms.web.internal.portlet.action;

import com.liferay.document.library.kernel.service.DLFileEntryLocalService;
import com.liferay.exportimport.kernel.lar.ExportImportHelper;
import com.liferay.petra.string.CharPool;
import com.liferay.portal.kernel.backgroundtask.BackgroundTask;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskManagerUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.portlet.JSONPortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;
import com.opencsv.CSVReader;
import com.ted.lms.constants.LMSConstants;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.constants.LMSRoleConstants;
import com.ted.lms.exception.CSVFileException;
import com.ted.lms.web.internal.background.task.ImportCourseUsersBackgroundTaskExecutor;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + LMSPortletKeys.COURSE,
		"mvc.command.name=/courses/import_members"
	},
	service = MVCActionCommand.class
)
public class ImportMembersMVCActionCommand extends BaseImportCSVMVCActionCommand {
	
	@Override
	protected long importData(ActionRequest actionRequest, ActionResponse actionResponse) throws PortalException {
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		long courseId  = ParamUtil.getLong(actionRequest, "courseId");
		long roleId  = ParamUtil.getLong(actionRequest, "roleId");
		long backgroundTaskId = 0;
		//Fichero a importar
		FileEntry fileEntry = importLayoutsMVCActionCommand.getTempFileEntry(themeDisplay.getScopeGroupId(), themeDisplay.getUserId(), 
				ExportImportHelper.TEMP_FOLDER_NAME + portal.getPortletId(actionRequest));
	
		Map<String, Serializable> taskContextMap = new HashMap<>();
		
		taskContextMap.put("courseId", courseId);
		taskContextMap.put("roleId", roleId);
		taskContextMap.put("userId", themeDisplay.getUserId());
		taskContextMap.put("groupId", themeDisplay.getScopeGroupId());
		taskContextMap.put("folderName", ExportImportHelper.TEMP_FOLDER_NAME + portal.getPortletId(actionRequest));
		taskContextMap.put("fileName", fileEntry.getFileName());
		
		try {
			BackgroundTask backgroundTask = BackgroundTaskManagerUtil.addBackgroundTask(themeDisplay.getUserId(), themeDisplay.getScopeGroupId(),
						portal.getPortletId(actionRequest), ImportCourseUsersBackgroundTaskExecutor.class.getName(),
						taskContextMap, new ServiceContext());
			backgroundTaskId = backgroundTask.getBackgroundTaskId();
		} catch (PortalException e) {
			e.printStackTrace();
		}

		return backgroundTaskId;
	}
	
	@Override
	protected void validateFile(ActionRequest actionRequest, ActionResponse actionResponse, FileEntry fileEntry) throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long roleId = ParamUtil.getLong(actionRequest, "roleId");
		
		Role roleStudent = roleLocalService.getRole(themeDisplay.getCompanyId(), LMSRoleConstants.STUDENT);
		boolean isStudent = roleStudent.getRoleId() == roleId;
		
		System.out.println("validamos el fichero");
		
		try (InputStream inputStream = dlFileEntryLocalService.getFileAsStream(
				fileEntry.getFileEntryId(), fileEntry.getVersion(), false);
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				CSVReader reader = new CSVReader(inputStreamReader, CharPool.SEMICOLON)) {
			

			//Comprobamos las cabeceras
			String[] headers = reader.readNext();
			
			System.out.println("headers: " + headers.length);
			
			if((isStudent && headers.length != (LMSConstants.COLUMNS_IMPORT_EXPORT_ASSIGN_MEMBERS.length + 
					LMSConstants.COLUMNS_IMPORT_EXPORT_ASSIGN_MEMBERS_STUDENTS.length + 1)) 
					|| (!isStudent && headers.length != (LMSConstants.COLUMNS_IMPORT_EXPORT_ASSIGN_MEMBERS.length + 1))) {
				throw new CSVFileException(
						CSVFileException.TYPE_INVALID_HEADERS);
			}
			
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
			
			jsonObject.put(
					"infoMessages",
					reader.getLinesRead());
			
			JSONPortletResponseUtil.writeJSON(
					actionRequest, actionResponse, jsonObject);
		}
	}
	
	@Override
	protected String getPortletId(ActionRequest actionRequest) {
		return portal.getPortletId(actionRequest);
	}
	
	@Override 
	protected ImportCSVMVCActionCommand getImportCSVMVCActionCommand() {
		return importLayoutsMVCActionCommand;
	}
	
	@Reference(unbind = "-")
	protected void setDLFileEntryLocalService(DLFileEntryLocalService dlFileEntryLocalService) {

		this.dlFileEntryLocalService = dlFileEntryLocalService;
	}
	@Reference(unbind = "-")
	protected void setRoleLocalService(RoleLocalService roleLocalService) {

		this.roleLocalService = roleLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setImportLayoutsMVCActionCommand(
		ImportCSVMVCActionCommand importLayoutsMVCActionCommand) {

		this.importLayoutsMVCActionCommand = importLayoutsMVCActionCommand;
	}

	private ImportCSVMVCActionCommand importLayoutsMVCActionCommand;
	private DLFileEntryLocalService dlFileEntryLocalService;
	private RoleLocalService roleLocalService;
	
	@Reference
	private Portal portal;
}
