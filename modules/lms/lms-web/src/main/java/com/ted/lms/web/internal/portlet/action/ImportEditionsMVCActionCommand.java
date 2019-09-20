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
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
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
import javax.portlet.RenderRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + LMSPortletKeys.COURSE,
		"mvc.command.name=/courses/import_editions"
	},
	service = MVCActionCommand.class
)
public class ImportEditionsMVCActionCommand extends BaseImportCSVMVCActionCommand {
	
	@Override
	protected long importData(ActionRequest actionRequest, ActionResponse actionResponse) throws PortalException {
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		long parentCourseId  = ParamUtil.getLong(actionRequest, "parentCourseId");
		long backgroundTaskId = 0;
		
		//Fichero a importar
		FileEntry fileEntry = importLayoutsMVCActionCommand.getTempFileEntry(themeDisplay.getScopeGroupId(), themeDisplay.getUserId(), 
				ExportImportHelper.TEMP_FOLDER_NAME + portal.getPortletId(actionRequest));
	
		Map<String, Serializable> taskContextMap = new HashMap<>();
		
		taskContextMap.put("parentCourseId", parentCourseId);
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
		
		log.debug("validamos el fichero");
		
		try (InputStream inputStream = dlFileEntryLocalService.getFileAsStream(
				fileEntry.getFileEntryId(), fileEntry.getVersion(), false);
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				CSVReader reader = new CSVReader(inputStreamReader, CharPool.SEMICOLON)) {
			

			//Comprobamos las cabeceras
			String[] headers = reader.readNext();
			
			log.debug("headers: " + headers.length);
			
			if((headers.length != LMSConstants.COLUMNS_IMPORT_EDITIONS.length) ) {
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
	protected ImportMVCActionCommand getImportCSVMVCActionCommand() {
		return importLayoutsMVCActionCommand;
	}
	
	@Reference(unbind = "-")
	protected void setDLFileEntryLocalService(DLFileEntryLocalService dlFileEntryLocalService) {

		this.dlFileEntryLocalService = dlFileEntryLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setImportLayoutsMVCActionCommand(
		ImportMVCActionCommand importLayoutsMVCActionCommand) {

		this.importLayoutsMVCActionCommand = importLayoutsMVCActionCommand;
	}

	private ImportMVCActionCommand importLayoutsMVCActionCommand;
	private DLFileEntryLocalService dlFileEntryLocalService;
	
	@Reference
	private Portal portal;
}
