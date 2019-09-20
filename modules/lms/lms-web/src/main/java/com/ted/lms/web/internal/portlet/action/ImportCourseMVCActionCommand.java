package com.ted.lms.web.internal.portlet.action;

import com.liferay.document.library.kernel.service.DLFileEntryLocalService;
import com.liferay.exportimport.kernel.configuration.ExportImportConfigurationConstants;
import com.liferay.exportimport.kernel.configuration.ExportImportConfigurationSettingsMapFactory;
import com.liferay.exportimport.kernel.exception.LARFileException;
import com.liferay.exportimport.kernel.exception.LARFileNameException;
import com.liferay.exportimport.kernel.exception.LARFileSizeException;
import com.liferay.exportimport.kernel.exception.LARTypeException;
import com.liferay.exportimport.kernel.lar.ExportImportHelper;
import com.liferay.exportimport.kernel.lar.MissingReference;
import com.liferay.exportimport.kernel.lar.MissingReferences;
import com.liferay.exportimport.kernel.lar.PortletDataHandlerKeys;
import com.liferay.exportimport.kernel.model.ExportImportConfiguration;
import com.liferay.exportimport.kernel.service.ExportImportConfigurationLocalService;
import com.liferay.exportimport.kernel.service.ExportImportService;
import com.liferay.exportimport.kernel.staging.Staging;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.exception.NoSuchLayoutException;
import com.liferay.portal.kernel.exception.PortletIdException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.portlet.JSONPortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.model.Course;
import com.ted.lms.service.CourseLocalService;
import java.io.InputStream;
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
		"mvc.command.name=/courses/import_course"
	},
	service = MVCActionCommand.class
)
public class ImportCourseMVCActionCommand extends BaseMVCActionCommand {
	
	private static final Log log = LogFactoryUtil.getLog(ImportCourseMVCActionCommand.class);
	
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		log.debug("entramos en la acción");
		
		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);
		
		log.debug("cmd: " + cmd);

		if (Validator.isNull(cmd)) {
			SessionMessages.add(
				actionRequest,
				portal.getPortletId(actionRequest) +
					SessionMessages.KEY_SUFFIX_FORCE_SEND_REDIRECT);

			hideDefaultSuccessMessage(actionRequest);

			return;
		}
		
		try {
			
			if (cmd.equals(Constants.ADD_TEMP)) {
				FileEntry fileEntry = importMVCActionCommand.addTempFileEntry(
						actionRequest,
						ExportImportHelper.TEMP_FOLDER_NAME +
						portal.getPortletId(actionRequest));
				
				validateFile(
						actionRequest, actionResponse,
						fileEntry);
	
				hideDefaultSuccessMessage(actionRequest);
			}
			else if (cmd.equals(Constants.DELETE_TEMP)) {
				importMVCActionCommand.deleteTempFileEntry(
					actionRequest, actionResponse,
					ExportImportHelper.TEMP_FOLDER_NAME +
						portal.getPortletId(actionRequest));

				hideDefaultSuccessMessage(actionRequest);
			}else if (cmd.equals(Constants.IMPORT)) {
				hideDefaultSuccessMessage(actionRequest);

				long backgroundTaskId = importData(
					actionRequest,
					ExportImportHelper.TEMP_FOLDER_NAME +
						portal.getPortletId(actionRequest));

				SessionMessages.add(
					actionRequest,
					portal.getPortletId(actionRequest) +
						SessionMessages.KEY_SUFFIX_CLOSE_REFRESH_PORTLET,
						portal.getPortletId(actionRequest));

				String redirect = ParamUtil.getString(actionRequest, "redirect");
				redirect+= "&_" + portal.getPortletId(actionRequest) + "_backgroundTaskId=" + backgroundTaskId;
				
				log.debug("redirect: " + redirect);
				
				sendRedirect(actionRequest, actionResponse, redirect);
				
				actionRequest.setAttribute("backgroundTaskId", String.valueOf(backgroundTaskId));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			if (cmd.equals(Constants.ADD_TEMP) ||
				cmd.equals(Constants.DELETE_TEMP)) {

				hideDefaultSuccessMessage(actionRequest);

				importMVCActionCommand.handleUploadException(
					actionRequest, actionResponse,
					ExportImportHelper.TEMP_FOLDER_NAME +
						portal.getPortletId(actionRequest),
					e);
			}
			else {
				if (e instanceof LARFileException ||
					e instanceof LARFileNameException ||
					e instanceof LARFileSizeException ||
					e instanceof LARTypeException ||
					e instanceof LocaleException ||
					e instanceof NoSuchLayoutException ||
					e instanceof PortletIdException ||
					e instanceof PrincipalException) {

					SessionErrors.add(actionRequest, e.getClass());
				}
				else {
					log.error(e, e);

					SessionErrors.add(
						actionRequest,
						ImportCourseMVCActionCommand.class.getName());
				}
			}
		}
	}
	
	protected long importData(
			ActionRequest actionRequest, InputStream inputStream)
		throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);
		
		long courseId = ParamUtil.getLong(actionRequest, "courseId");
		Course course = courseLocalService.getCourse(courseId);

		Map<String, String[]> parameterMap = new HashMap<>(actionRequest.getParameterMap());
		parameterMap.put(PortletDataHandlerKeys.PORTLET_DATA, new String[]{"true"});
		parameterMap.put("groupId", new String[]{String.valueOf(course.getGroupCreatedId())});
		parameterMap.put("_modules_modules", new String[]{"true"});
		parameterMap.put("_modules_activities", new String[]{"true"});
		parameterMap.put("_modules_referenced-content", new String[]{"true"});
		parameterMap.put("portletResource", new String[]{LMSPortletKeys.MODULES_ADMIN});
		parameterMap.put(PortletDataHandlerKeys.PORTLET_DATA + "_" + LMSPortletKeys.MODULES_ADMIN, new String[]{"true"});
		
		Map<String, Serializable> importPortletSettingsMap =
			exportImportConfigurationSettingsMapFactory.
				buildImportPortletSettingsMap(
					themeDisplay.getUserId(), themeDisplay.getPlid(), course.getGroupCreatedId(),
					LMSPortletKeys.MODULES_ADMIN, parameterMap,
					themeDisplay.getLocale(), themeDisplay.getTimeZone());

		ExportImportConfiguration exportImportConfiguration =
			exportImportConfigurationLocalService.
				addDraftExportImportConfiguration(
					themeDisplay.getUserId(),
					ExportImportConfigurationConstants.TYPE_IMPORT_PORTLET,
					importPortletSettingsMap);

		return exportImportService.importPortletInfoInBackground(
			exportImportConfiguration, inputStream);
	}
	
	protected void validateFile(
			ActionRequest actionRequest, ActionResponse actionResponse,
			FileEntry fileEntry)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		try (InputStream inputStream = dlFileEntryLocalService.getFileAsStream(
				fileEntry.getFileEntryId(), fileEntry.getVersion(), false)) {

			log.debug("validamos el archivo");
			
			MissingReferences missingReferences = validateFile(
				actionRequest, inputStream);
			
			log.debug("archivo validado");

			Map<String, MissingReference> weakMissingReferences =
				missingReferences.getWeakMissingReferences();

			if (weakMissingReferences.isEmpty()) {
				return;
			}
			
			if(log.isDebugEnabled())
				weakMissingReferences.forEach((key, reference)-> log.debug("key: " + key + " reference: " + reference));

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			if ((weakMissingReferences != null) &&
				!weakMissingReferences.isEmpty()) {

				jsonObject.put(
					"warningMessages",
					staging.getWarningMessagesJSONArray(
						themeDisplay.getLocale(), weakMissingReferences));
			}

			JSONPortletResponseUtil.writeJSON(
				actionRequest, actionResponse, jsonObject);
		}
	}
	
	protected long importData(ActionRequest actionRequest, String folderName)
			throws Exception {

			ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

			FileEntry fileEntry = importMVCActionCommand.getTempFileEntry(themeDisplay.getScopeGroupId(), themeDisplay.getUserId(), 
					ExportImportHelper.TEMP_FOLDER_NAME + portal.getPortletId(actionRequest));


			try (InputStream inputStream = dlFileEntryLocalService.getFileAsStream(
					fileEntry.getFileEntryId(), fileEntry.getVersion(), false)) {

				long backgroundTaskId = importData(actionRequest, inputStream);

				importMVCActionCommand.deleteTempFileEntry(
					themeDisplay.getScopeGroupId(), themeDisplay.getUserId(), folderName);
				
				return backgroundTaskId;
			}
		}
	
	protected MissingReferences validateFile(
			ActionRequest actionRequest, InputStream inputStream)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long courseId = ParamUtil.getLong(actionRequest, "courseId");
		Course course = courseLocalService.getCourse(courseId);
		
		Map<String, String[]> parameterMap = new HashMap<>(actionRequest.getParameterMap());
		parameterMap.put(PortletDataHandlerKeys.PORTLET_DATA, new String[]{"true"});
		parameterMap.put("groupId", new String[]{String.valueOf(course.getGroupCreatedId())});
		parameterMap.put("_modules_modules", new String[]{"true"});
		parameterMap.put("_modules_activities", new String[]{"true"});
		parameterMap.put("_modules_referenced-content", new String[]{"true"});
		parameterMap.put("portletResource", new String[]{LMSPortletKeys.MODULES_ADMIN});
		parameterMap.put(PortletDataHandlerKeys.PORTLET_DATA + "_" + LMSPortletKeys.MODULES_ADMIN, new String[]{"true"});
		
		Map<String, Serializable> importPortletSettingsMap =
			exportImportConfigurationSettingsMapFactory.
				buildImportPortletSettingsMap(
					themeDisplay.getUserId(), themeDisplay.getPlid(), course.getGroupCreatedId(),
					LMSPortletKeys.MODULES_ADMIN, parameterMap,
					themeDisplay.getLocale(), themeDisplay.getTimeZone());

		ExportImportConfiguration exportImportConfiguration =
			exportImportConfigurationLocalService.
				addDraftExportImportConfiguration(
					themeDisplay.getUserId(),
					ExportImportConfigurationConstants.TYPE_IMPORT_PORTLET,
					importPortletSettingsMap);

		return exportImportService.validateImportPortletInfo(
			exportImportConfiguration, inputStream);
	}
	
	@Reference
	private ExportImportConfigurationSettingsMapFactory
		exportImportConfigurationSettingsMapFactory;
	
	private ExportImportConfigurationLocalService
		exportImportConfigurationLocalService;
	
	@Reference(unbind = "-")
	protected void setCourseLocalService(CourseLocalService courseLocalService) {
		this.courseLocalService = courseLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setExportImportService(
		ExportImportService exportImportService) {

		this.exportImportService = exportImportService;
	}
	
	@Reference(unbind = "-")
	protected void setExportImportConfigurationLocalService(
		ExportImportConfigurationLocalService
			exportImportConfigurationLocalService) {

		this.exportImportConfigurationLocalService =
			exportImportConfigurationLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setDLFileEntryLocalService(DLFileEntryLocalService dlFileEntryLocalService) {

		this.dlFileEntryLocalService = dlFileEntryLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setImportLayoutsMVCActionCommand(
		ImportMVCActionCommand importMVCActionCommand) {

		this.importMVCActionCommand = importMVCActionCommand;
	}

	private ImportMVCActionCommand importMVCActionCommand;
	
	@Reference
	private ExportImportHelper exportImportHelper;
	
	@Reference
	private Portal portal;
	
	@Reference
	private Staging staging;

	private CourseLocalService courseLocalService;
	private ExportImportService exportImportService;
	private DLFileEntryLocalService dlFileEntryLocalService;
}
