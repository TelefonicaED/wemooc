package com.ted.lms.learning.activity.question.web.portlet.action;

import com.liferay.document.library.kernel.exception.FileSizeException;
import com.liferay.document.library.kernel.service.DLFileEntryLocalService;
import com.liferay.exportimport.kernel.exception.LARFileSizeException;
import com.liferay.exportimport.kernel.lar.ExportImportHelper;
import com.liferay.exportimport.kernel.model.ExportImportConfiguration;
import com.liferay.exportimport.kernel.staging.Staging;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.JSONPortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.ServletResponseConstants;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadException;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.upload.UploadRequestSizeException;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.TempFileEntryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.ted.lms.constants.LMSConstants;
import com.ted.lms.exception.CSVFileException;
import com.ted.lms.learning.activity.question.constants.QuestionConstants;
import com.ted.lms.learning.activity.question.constants.QuestionsWebPortletKeys;
import com.ted.lms.learning.activity.question.model.Answer;
import com.ted.lms.learning.activity.question.model.Question;
import com.ted.lms.learning.activity.question.model.QuestionType;
import com.ted.lms.learning.activity.question.model.QuestionTypeFactory;
import com.ted.lms.learning.activity.question.registry.QuestionTypeFactoryRegistryUtil;
import com.ted.lms.learning.activity.question.service.AnswerLocalService;
import com.ted.lms.learning.activity.question.service.QuestionLocalService;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ServiceConfigurationError;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadBase;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + QuestionsWebPortletKeys.EDIT_QUESTIONS,
		"mvc.command.name=/questions/import_questions"
	},
	service = MVCActionCommand.class
)
public class ImportQuestionsMVCActionCommand extends BaseMVCActionCommand {
	
	private static final Log log = LogFactoryUtil.getLog(ImportQuestionsMVCActionCommand.class);

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		
		log.debug("action import csv");
		
		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);
		String type = ParamUtil.getString(actionRequest, Constants.ACTION);
		
		System.out.println("cmd: " + cmd);
		System.out.println("type: " + type);

		try {
			
			if (cmd.equals(Constants.ADD_TEMP)) {
				
				log.debug("cmd add temp");
				
				FileEntry fileEntry = addTempFileEntry(
						actionRequest,
						ExportImportHelper.TEMP_FOLDER_NAME +
						QuestionsWebPortletKeys.EDIT_QUESTIONS);
				
				if(type.equals("XML")) {
					JSONObject jsonObject = questionLocalService.validateFileXML(
							fileEntry);
					JSONPortletResponseUtil.writeJSON(
							actionRequest, actionResponse, jsonObject);
				}else if (type.equals("CSV")) {
					
					
				}
	
				hideDefaultSuccessMessage(actionRequest);
			}else if (cmd.equals(Constants.DELETE_TEMP)) {
				deleteTempFileEntry(
					actionRequest, actionResponse,
					ExportImportHelper.TEMP_FOLDER_NAME +
					QuestionsWebPortletKeys.EDIT_QUESTIONS);
	
				hideDefaultSuccessMessage(actionRequest);
			}else if(cmd.equals(Constants.IMPORT)) {
				ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
						WebKeys.THEME_DISPLAY);
				
				long actId = ParamUtil.getLong(actionRequest, "actId");
				
				FileEntry fileEntry = getTempFileEntry(themeDisplay.getScopeGroupId(), themeDisplay.getUserId(), 
						ExportImportHelper.TEMP_FOLDER_NAME + portal.getPortletId(actionRequest));
				
				String questionIdsAllowedString = ParamUtil.getString(actionRequest, "questionIdsAllowed", "");
				long[] questionIdsAllowed = Validator.isNotNull(questionIdsAllowedString) ? StringUtil.split(questionIdsAllowedString,",", 0L) : null;
				
				JSONObject result = null;
				
				if(type.equals("XML")) {
					result = questionLocalService.importQuestionsXML(themeDisplay.getUserId(), actId, fileEntry, questionIdsAllowed, themeDisplay.getLocale());
				}else if(type.equals("CSV")) {
					result = questionLocalService.importQuestionsExcel(themeDisplay.getUserId(), actId, fileEntry, questionIdsAllowed, themeDisplay.getLocale());
				}
				actionResponse.setRenderParameter("mvcRenderCommandName", "/questions/import_questions");
				actionResponse.setRenderParameter("actId", String.valueOf(actId));
				actionResponse.setRenderParameter("result", result.toJSONString());
				if(result.getInt(QuestionConstants.IMPORT_JSON_RESULT) == QuestionConstants.IMPORT_JSON_RESULT_SUCCESS) {
					SessionMessages.add(actionRequest, "importQuestions");
				}else {
					SessionErrors.add(actionRequest, "importQuestions");
					hideDefaultSuccessMessage(actionRequest);
				}
				System.out.println("result: " + result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (cmd.equals(Constants.ADD_TEMP) ||
				cmd.equals(Constants.DELETE_TEMP)) {

				hideDefaultSuccessMessage(actionRequest);

				handleUploadException(
					actionRequest, actionResponse,
					ExportImportHelper.TEMP_FOLDER_NAME +
						QuestionsWebPortletKeys.EDIT_QUESTIONS,
					e);
			}
		}
	}
	
	private FileEntry addTempFileEntry(
			ActionRequest actionRequest, String folderName)
		throws Exception {

		UploadPortletRequest uploadPortletRequest =
			portal.getUploadPortletRequest(actionRequest);

		checkExceededSizeLimit(uploadPortletRequest);

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		deleteTempFileEntry(themeDisplay.getScopeGroupId(), themeDisplay.getUserId(), folderName);

		try (InputStream inputStream = uploadPortletRequest.getFileAsStream(
				"file")) {

			String sourceFileName = uploadPortletRequest.getFileName("file");

			String contentType = uploadPortletRequest.getContentType("file");

			
			return TempFileEntryUtil.addTempFileEntry(themeDisplay.getScopeGroupId(), themeDisplay.getUserId(), 
					folderName, sourceFileName, inputStream, contentType);
		}
		catch (Exception e) {
			UploadException uploadException =
				(UploadException)actionRequest.getAttribute(
					WebKeys.UPLOAD_EXCEPTION);

			if (uploadException != null) {
				Throwable cause = uploadException.getCause();

				if (cause instanceof FileUploadBase.IOFileUploadException) {
					if (log.isInfoEnabled()) {
						log.info("Temporary upload was cancelled");
					}
				}

				if (uploadException.isExceededFileSizeLimit()) {
					throw new FileSizeException(cause);
				}

				if (uploadException.isExceededUploadRequestSizeLimit()) {
					throw new UploadRequestSizeException(cause);
				}
			}
			else {
				throw e;
			}
			return null;
		}
	}
	
	private void handleUploadException(
			ActionRequest actionRequest, ActionResponse actionResponse,
			String folderName, Exception e)
		throws Exception {

		HttpServletResponse response = portal.getHttpServletResponse(
			actionResponse);

		response.setContentType(ContentTypes.TEXT_HTML);
		response.setStatus(HttpServletResponse.SC_OK);

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		deleteTempFileEntry(themeDisplay.getScopeGroupId(), themeDisplay.getUserId(), folderName);

		JSONObject jsonObject = null;
		
		if(e instanceof CSVFileException) {
			JSONObject exceptionMessagesJSONObject = JSONFactoryUtil.createJSONObject();

			String errorMessage = StringPool.BLANK;

			if(((CSVFileException) e).getType() == CSVFileException.TYPE_INVALID_HEADERS) {
				errorMessage = LanguageUtil.get(
					themeDisplay.getLocale(), "questions.import.error-headers");
			}
			exceptionMessagesJSONObject.put("message", errorMessage);
			exceptionMessagesJSONObject.put("status", ServletResponseConstants.SC_FILE_CUSTOM_EXCEPTION);
			
			jsonObject = exceptionMessagesJSONObject;
		}else {
			jsonObject = staging.getExceptionMessagesJSONObject(
				themeDisplay.getLocale(), e, (ExportImportConfiguration)null);
		}
			
		log.debug("jsonObject: " + jsonObject);

		JSONPortletResponseUtil.writeJSON(
			actionRequest, actionResponse, jsonObject);
	}

	private void checkExceededSizeLimit(HttpServletRequest request)
		throws PortalException {

		UploadException uploadException = (UploadException)request.getAttribute(
			WebKeys.UPLOAD_EXCEPTION);

		if (uploadException != null) {
			Throwable cause = uploadException.getCause();

			if (uploadException.isExceededFileSizeLimit() ||
				uploadException.isExceededUploadRequestSizeLimit()) {

				throw new LARFileSizeException(cause);
			}

			throw new PortalException(cause);
		}
	}

	private void deleteTempFileEntry(
			ActionRequest actionRequest, ActionResponse actionResponse,
			String folderName)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		try {
			String fileName = ParamUtil.getString(actionRequest, "fileName");

			TempFileEntryUtil.deleteTempFileEntry(
				themeDisplay.getScopeGroupId(), themeDisplay.getUserId(), folderName, fileName);

			jsonObject.put("deleted", Boolean.TRUE);
		}
		catch (Exception e) {
			String errorMessage = themeDisplay.translate(
				"an-unexpected-error-occurred-while-deleting-the-file");

			jsonObject.put("deleted", Boolean.FALSE);
			jsonObject.put("errorMessage", errorMessage);
		}

		JSONPortletResponseUtil.writeJSON(
			actionRequest, actionResponse, jsonObject);
	}

	private void deleteTempFileEntry(long groupId, long userId, String folderName)
		throws PortalException {

		String[] tempFileNames = TempFileEntryUtil.getTempFileNames(
			groupId, userId, folderName);

		for (String tempFileEntryName : tempFileNames) {
			TempFileEntryUtil.deleteTempFileEntry(
				groupId, userId, folderName, tempFileEntryName);
		}
	}
	
	private FileEntry getTempFileEntry(long groupId, long userId, String folderName) throws PortalException{
		String[] tempFileNames = TempFileEntryUtil.getTempFileNames(groupId, userId, folderName);

		if (tempFileNames.length == 0) {
			return null;
		}

		return TempFileEntryUtil.getTempFileEntry(
			groupId, userId,
			folderName,
			tempFileNames[0]);
	}


	@Reference
	private Portal portal;
	
	@Reference
	private Staging staging;
	
	@Reference
	private DLFileEntryLocalService dlFileEntryLocalService;
	
	@Reference
	private QuestionLocalService questionLocalService;
	
	@Reference
	private AnswerLocalService answerLocalService;
}
