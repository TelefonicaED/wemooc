package com.ted.lms.web.internal.portlet.action;

import com.liferay.asset.kernel.exception.AssetCategoryException;
import com.liferay.asset.kernel.exception.AssetTagException;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.document.library.kernel.antivirus.AntivirusScannerException;
import com.liferay.document.library.kernel.exception.DuplicateFileEntryException;
import com.liferay.document.library.kernel.exception.DuplicateFolderNameException;
import com.liferay.document.library.kernel.exception.FileEntryLockException;
import com.liferay.document.library.kernel.exception.FileExtensionException;
import com.liferay.document.library.kernel.exception.FileMimeTypeException;
import com.liferay.document.library.kernel.exception.FileNameException;
import com.liferay.document.library.kernel.exception.FileSizeException;
import com.liferay.document.library.kernel.exception.InvalidFileEntryTypeException;
import com.liferay.document.library.kernel.exception.InvalidFileVersionException;
import com.liferay.document.library.kernel.exception.NoSuchFileEntryException;
import com.liferay.document.library.kernel.exception.NoSuchFolderException;
import com.liferay.document.library.kernel.exception.RequiredFileException;
import com.liferay.document.library.kernel.exception.SourceFileNameException;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLAppService;
import com.liferay.document.library.kernel.service.DLTrashService;
import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.document.library.kernel.util.DLValidator;
import com.liferay.dynamic.data.mapping.kernel.StorageFieldRequiredException;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.lock.DuplicateLockException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.TrashedModel;
import com.liferay.portal.kernel.portlet.JSONPortletResponseUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.repository.capabilities.TrashCapability;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.LiferayFileItemException;
import com.liferay.portal.kernel.upload.UploadException;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.upload.UploadRequestSizeException;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.KeyValuePair;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.TempFileEntryUtil;
import com.liferay.portal.kernel.util.TextFormatter;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.trash.service.TrashEntryService;
import com.liferay.upload.UploadResponseHandler;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.constants.LearningActivityConstants;
import com.ted.lms.util.LMSPrefsPropsValues;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletRequest;
import javax.portlet.WindowState;

import org.apache.commons.fileupload.FileUploadBase;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = {
			"javax.portlet.name=" + LMSPortletKeys.COURSE_CONTENT_VIEWER,
		"mvc.command.name=/activities/upload_multiple_documents"
	},
	service = MVCActionCommand.class
)
public class UploadMultipleDocumentsMVCActionCommand extends BaseMVCActionCommand {

	protected void addMultipleFileEntries(PortletConfig portletConfig, ActionRequest actionRequest, ActionResponse actionResponse)throws Exception {

		List<KeyValuePair> validFileNameKVPs = new ArrayList<>();
		List<KeyValuePair> invalidFileNameKVPs = new ArrayList<>();

		String[] selectedFileNames = ParamUtil.getParameterValues(actionRequest, "selectedFileName", new String[0], false);

		for (String selectedFileName : selectedFileNames) {
			addMultipleFileEntries(portletConfig, actionRequest, actionResponse, selectedFileName,validFileNameKVPs, invalidFileNameKVPs);
		}

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (KeyValuePair validFileNameKVP : validFileNameKVPs) {
			String fileName = validFileNameKVP.getKey();
			String originalFileName = validFileNameKVP.getValue();

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("added", Boolean.TRUE);
			jsonObject.put("fileName", fileName);
			jsonObject.put("originalFileName", originalFileName);

			jsonArray.put(jsonObject);
		}

		for (KeyValuePair invalidFileNameKVP : invalidFileNameKVPs) {
			String fileName = invalidFileNameKVP.getKey();
			String errorMessage = invalidFileNameKVP.getValue();

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("added", Boolean.FALSE);
			jsonObject.put("errorMessage", errorMessage);
			jsonObject.put("fileName", fileName);
			jsonObject.put("originalFileName", fileName);

			jsonArray.put(jsonObject);
		}

		JSONPortletResponseUtil.writeJSON(actionRequest, actionResponse, jsonArray);
	}

	protected void addMultipleFileEntries(PortletConfig portletConfig, ActionRequest actionRequest,ActionResponse actionResponse, String selectedFileName,
			List<KeyValuePair> validFileNameKVPs,List<KeyValuePair> invalidFileNameKVPs) throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		long repositoryId = ParamUtil.getLong(actionRequest, "repositoryId");
		long folderId = ParamUtil.getLong(actionRequest, "folderId");
		String description = ParamUtil.getString(actionRequest, "description");
		String changeLog = ParamUtil.getString(actionRequest, "changeLog");

		FileEntry tempFileEntry = null;

		try {
			tempFileEntry = TempFileEntryUtil.getTempFileEntry(themeDisplay.getScopeGroupId(), themeDisplay.getUserId(),LearningActivityConstants.TEMP_FOLDER_NAME, selectedFileName);

			String originalSelectedFileName = TempFileEntryUtil.getOriginalTempFileName(tempFileEntry.getFileName());

			String uniqueFileName = DLUtil.getUniqueFileName(tempFileEntry.getGroupId(), folderId, originalSelectedFileName);

			String mimeType = tempFileEntry.getMimeType();
			InputStream inputStream = tempFileEntry.getContentStream();
			long size = tempFileEntry.getSize();

			ServiceContext serviceContext = ServiceContextFactory.getInstance(DLFileEntry.class.getName(), actionRequest);

			_dlAppService.addFileEntry(repositoryId, folderId, uniqueFileName, mimeType,uniqueFileName, description, changeLog, inputStream, size, serviceContext);

			validFileNameKVPs.add(new KeyValuePair(uniqueFileName, selectedFileName));

			return;
		} catch (Exception e) {
			String errorMessage = getAddMultipleFileEntriesErrorMessage(portletConfig, actionRequest, actionResponse, e);

			invalidFileNameKVPs.add(new KeyValuePair(selectedFileName, errorMessage));
		} finally {
			if (tempFileEntry != null) {
				TempFileEntryUtil.deleteTempFileEntry(tempFileEntry.getFileEntryId());
			}
		}
	}

	protected void addTempFileEntry(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		UploadPortletRequest uploadPortletRequest = _portal.getUploadPortletRequest(actionRequest);

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		long folderId = ParamUtil.getLong(uploadPortletRequest, "folderId");
		String sourceFileName = uploadPortletRequest.getFileName("file");

		try (InputStream inputStream = uploadPortletRequest.getFileAsStream("file")) {

			String tempFileName = TempFileEntryUtil.getTempFileName(sourceFileName);

			String mimeType = uploadPortletRequest.getContentType("file");

			FileEntry fileEntry = _dlAppService.addTempFileEntry(themeDisplay.getScopeGroupId(), folderId, LearningActivityConstants.TEMP_FOLDER_NAME,tempFileName, inputStream, mimeType);

			JSONObject jsonObject = _multipleUploadResponseHandler.onSuccess(uploadPortletRequest, fileEntry);

			JSONPortletResponseUtil.writeJSON(actionRequest, actionResponse, jsonObject);
		}
	}

	protected void cancelFileEntriesCheckOut(ActionRequest actionRequest) throws Exception {

		long fileEntryId = ParamUtil.getLong(actionRequest, "fileEntryId");

		if (fileEntryId > 0) {
			_dlAppService.cancelCheckOut(fileEntryId);
		} else {
			long[] fileEntryIds = ParamUtil.getLongValues(actionRequest, "rowIdsFileEntry");

			for (long curFileEntryId : fileEntryIds) {
				_dlAppService.cancelCheckOut(curFileEntryId);
			}
		}
	}

	protected void checkInFileEntries(ActionRequest actionRequest) throws Exception {

		long fileEntryId = ParamUtil.getLong(actionRequest, "fileEntryId");

		boolean majorVersion = ParamUtil.getBoolean(actionRequest, "majorVersion");
		String changeLog = ParamUtil.getString(actionRequest, "changeLog");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(actionRequest);

		if (fileEntryId > 0) {
			_dlAppService.checkInFileEntry(fileEntryId, majorVersion, changeLog, serviceContext);
		} else {
			long[] fileEntryIds = ParamUtil.getLongValues(actionRequest, "rowIdsFileEntry");

			for (long curFileEntryId : fileEntryIds) {
				_dlAppService.checkInFileEntry(curFileEntryId, majorVersion, changeLog, serviceContext);
			}
		}
	}

	protected void checkOutFileEntries(ActionRequest actionRequest) throws Exception {

		long fileEntryId = ParamUtil.getLong(actionRequest, "fileEntryId");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(actionRequest);

		if (fileEntryId > 0) {
			_dlAppService.checkOutFileEntry(fileEntryId, serviceContext);
		} else {
			long[] fileEntryIds = ParamUtil.getLongValues(actionRequest, "rowIdsFileEntry");

			for (long curFileEntryId : fileEntryIds) {
				_dlAppService.checkOutFileEntry(curFileEntryId, serviceContext);
			}
		}
	}

	protected void deleteFileEntry(ActionRequest actionRequest, boolean moveToTrash) throws Exception {

		long fileEntryId = ParamUtil.getLong(actionRequest, "fileEntryId");

		if (fileEntryId == 0) {
			return;
		}

		String version = ParamUtil.getString(actionRequest, "version");

		if (Validator.isNotNull(version)) {
			_dlAppService.deleteFileVersion(fileEntryId, version);

			return;
		}

		if (!moveToTrash) {
			_dlAppService.deleteFileEntry(fileEntryId);

			return;
		}

		FileEntry fileEntry = _dlAppService.getFileEntry(fileEntryId);

		if (!fileEntry.isRepositoryCapabilityProvided(TrashCapability.class)) {
			hideDefaultSuccessMessage(actionRequest);

			return;
		}

		fileEntry = _dlTrashService.moveFileEntryToTrash(fileEntryId);

		List<TrashedModel> trashedModels = new ArrayList<>();

		trashedModels.add((TrashedModel)fileEntry.getModel());

		Map<String, Object> data = new HashMap<>();

		data.put("trashedModels", trashedModels);

		addDeleteSuccessData(actionRequest, data);
	}

	protected void deleteTempFileEntry(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		long folderId = ParamUtil.getLong(actionRequest, "folderId");
		String fileName = ParamUtil.getString(actionRequest, "fileName");

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		try {
			_dlAppService.deleteTempFileEntry(themeDisplay.getScopeGroupId(), folderId, LearningActivityConstants.TEMP_FOLDER_NAME, fileName);

			jsonObject.put("deleted", Boolean.TRUE);
		} catch (Exception e) {
			String errorMessage = themeDisplay.translate("an-unexpected-error-occurred-while-deleting-the-file");

			jsonObject.put("deleted", Boolean.FALSE);
			jsonObject.put("errorMessage", errorMessage);
		}

		JSONPortletResponseUtil.writeJSON(actionRequest, actionResponse, jsonObject);
	}

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		FileEntry fileEntry = null;

		PortletConfig portletConfig = getPortletConfig(actionRequest);

		try {
			UploadException uploadException = (UploadException)actionRequest.getAttribute(WebKeys.UPLOAD_EXCEPTION);

			if (uploadException != null) {
				Throwable cause = uploadException.getCause();

				if (cmd.equals(Constants.ADD_TEMP)) {
					if (cause instanceof FileUploadBase.IOFileUploadException) {
						if (_log.isInfoEnabled()) {
							_log.info("Temporary upload was cancelled");
						}
					}
				} else {
					if (uploadException.isExceededFileSizeLimit()) {
						throw new FileSizeException(cause);
					}

					if (uploadException.isExceededLiferayFileItemSizeLimit()) {
						throw new LiferayFileItemException(cause);
					}

					if (uploadException.isExceededUploadRequestSizeLimit()) {
						throw new UploadRequestSizeException(cause);
					}

					throw new PortalException(cause);
				}
			} else if (cmd.equals(Constants.ADD) ||
					 cmd.equals(Constants.ADD_DYNAMIC) ||
					 cmd.equals(Constants.UPDATE) ||
					 cmd.equals(Constants.UPDATE_AND_CHECKIN)) {

				UploadPortletRequest uploadPortletRequest = _portal.getUploadPortletRequest(actionRequest);

				String sourceFileName = uploadPortletRequest.getFileName("file");

				try {
					fileEntry = updateFileEntry(portletConfig, actionRequest, actionResponse,uploadPortletRequest);
				}catch (Exception e) {
					if (!cmd.equals(Constants.ADD_DYNAMIC) &&
						Validator.isNotNull(sourceFileName)) {

						SessionErrors.add(actionRequest, RequiredFileException.class);
					}

					throw e;
				}
			} else if (cmd.equals(Constants.ADD_MULTIPLE)) {
				addMultipleFileEntries(portletConfig, actionRequest, actionResponse);
			}else if (cmd.equals(Constants.ADD_TEMP)) {
				addTempFileEntry(actionRequest, actionResponse);
			}else if (cmd.equals(Constants.DELETE)) {
				deleteFileEntry(actionRequest, false);
			}else if (cmd.equals(Constants.DELETE_TEMP)) {
				deleteTempFileEntry(actionRequest, actionResponse);
			}else if (cmd.equals(Constants.CANCEL_CHECKOUT)) {
				cancelFileEntriesCheckOut(actionRequest);
			}else if (cmd.equals(Constants.CHECKIN)) {
				checkInFileEntries(actionRequest);
			}else if (cmd.equals(Constants.CHECKOUT)) {
				checkOutFileEntries(actionRequest);
			}else if (cmd.equals(Constants.MOVE_TO_TRASH)) {
				deleteFileEntry(actionRequest, true);
			}else if (cmd.equals(Constants.RESTORE)) {
				restoreTrashEntries(actionRequest);
			}else if (cmd.equals(Constants.REVERT)) {
				revertFileEntry(actionRequest);
			}

			WindowState windowState = actionRequest.getWindowState();

			if (cmd.equals(Constants.ADD_TEMP) || cmd.equals(Constants.DELETE_TEMP)) {

				actionResponse.setRenderParameter("mvcPath", "/null.jsp");
			}else {
				String redirect = ParamUtil.getString(actionRequest, "redirect");
				int workflowAction = ParamUtil.getInteger(actionRequest, "workflowAction",WorkflowConstants.ACTION_SAVE_DRAFT);

				if ((fileEntry != null) && (workflowAction == WorkflowConstants.ACTION_SAVE_DRAFT)) {

					redirect = getSaveAndContinueRedirect(portletConfig, actionRequest, fileEntry, redirect);

					sendRedirect(actionRequest, actionResponse, redirect);
				} else {
					if (windowState.equals(LiferayWindowState.POP_UP)) {
						redirect = _portal.escapeRedirect(ParamUtil.getString(actionRequest, "redirect"));

						if (Validator.isNotNull(redirect)) {
							if (cmd.equals(Constants.ADD) &&(fileEntry != null)) {

								String portletId = _http.getParameter(redirect, "p_p_id", false);

								String namespace = _portal.getPortletNamespace(portletId);

								redirect = _http.addParameter(redirect, namespace + "className", DLFileEntry.class.getName());
								redirect = _http.addParameter(redirect, namespace + "classPK",fileEntry.getFileEntryId());
							}

							actionRequest.setAttribute(WebKeys.REDIRECT, redirect);
						}
					}
				}
			}
		}catch (Exception e) {
			handleUploadException(portletConfig, actionRequest, actionResponse, cmd, e);
		}
	}

	protected String getAddMultipleFileEntriesErrorMessage(PortletConfig portletConfig, ActionRequest actionRequest, ActionResponse actionResponse, 
			Exception e) throws Exception {

		String errorMessage = null;

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		if (e instanceof AntivirusScannerException) {
			AntivirusScannerException ase = (AntivirusScannerException)e;

			errorMessage = themeDisplay.translate(ase.getMessageKey());
		} else if (e instanceof AssetCategoryException) {
			AssetCategoryException ace = (AssetCategoryException)e;

			AssetVocabulary assetVocabulary = ace.getVocabulary();

			String vocabularyTitle = StringPool.BLANK;

			if (assetVocabulary != null) {
				vocabularyTitle = assetVocabulary.getTitle(themeDisplay.getLocale());
			}

			if (ace.getType() == AssetCategoryException.AT_LEAST_ONE_CATEGORY) {
				errorMessage = themeDisplay.translate("please-select-at-least-one-category-for-x",vocabularyTitle);
			} else if (ace.getType() == AssetCategoryException.TOO_MANY_CATEGORIES) {

				errorMessage = themeDisplay.translate("you-cannot-select-more-than-one-category-for-x", vocabularyTitle);
			}
		} else if (e instanceof DuplicateFileEntryException) {
			errorMessage = themeDisplay.translate("the-folder-you-selected-already-has-an-entry-with-this-" + "name.-please-select-a-different-folder");
		} else if (e instanceof FileExtensionException) {
			errorMessage = themeDisplay.translate("please-enter-a-file-with-a-valid-extension-x", 
					StringUtil.merge(getAllowedFileExtensions(actionRequest)));
		} else if (e instanceof FileNameException) {
			errorMessage = themeDisplay.translate("please-enter-a-file-with-a-valid-file-name");
		} else if (e instanceof FileSizeException) {
			errorMessage = themeDisplay.translate("please-enter-a-file-with-a-valid-file-size-no-larger-than-x",
				TextFormatter.formatStorageSize(_dlValidator.getMaxAllowableSize(), themeDisplay.getLocale()));
		} else if (e instanceof InvalidFileEntryTypeException) {
			errorMessage = themeDisplay.translate("the-document-type-you-selected-is-not-valid-for-this-folder");
		} else {
			errorMessage = themeDisplay.translate("an-unexpected-error-occurred-while-saving-your-document");
		}

		return errorMessage;
	}

	protected String[] getAllowedFileExtensions(PortletRequest portletRequest) throws PortalException {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(WebKeys.THEME_DISPLAY);

		return LMSPrefsPropsValues.getActivityDocumentsExtensions(themeDisplay.getCompanyId());
	}

	protected String getSaveAndContinueRedirect(PortletConfig portletConfig, ActionRequest actionRequest, FileEntry fileEntry, String redirect) throws Exception {

		LiferayPortletURL portletURL = PortletURLFactoryUtil.create(actionRequest, portletConfig.getPortletName(),PortletRequest.RENDER_PHASE);

		portletURL.setParameter("mvcRenderCommandName", "/document_library/edit_file_entry");
		portletURL.setParameter(Constants.CMD, Constants.UPDATE, false);
		portletURL.setParameter("redirect", redirect, false);
		portletURL.setParameter("groupId", String.valueOf(fileEntry.getGroupId()), false);
		portletURL.setParameter("fileEntryId", String.valueOf(fileEntry.getFileEntryId()), false);
		portletURL.setParameter("version", String.valueOf(fileEntry.getVersion()), false);
		portletURL.setWindowState(actionRequest.getWindowState());

		return portletURL.toString();
	}

	protected void handleUploadException(PortletConfig portletConfig, ActionRequest actionRequest, ActionResponse actionResponse, String cmd, Exception e) throws Exception {

		if (e instanceof AssetCategoryException || e instanceof AssetTagException) {

			SessionErrors.add(actionRequest, e.getClass(), e);
		} else if (e instanceof AntivirusScannerException ||
				 e instanceof DuplicateFileEntryException ||
				 e instanceof DuplicateFolderNameException ||
				 e instanceof FileExtensionException ||
				 e instanceof FileMimeTypeException ||
				 e instanceof FileNameException ||
				 e instanceof FileSizeException ||
				 e instanceof LiferayFileItemException ||
				 e instanceof NoSuchFolderException ||
				 e instanceof SourceFileNameException ||
				 e instanceof StorageFieldRequiredException ||
				 e instanceof UploadRequestSizeException) {

			if (!cmd.equals(Constants.ADD_DYNAMIC) &&
				!cmd.equals(Constants.ADD_MULTIPLE) &&
				!cmd.equals(Constants.ADD_TEMP)) {

				if (e instanceof AntivirusScannerException) {
					SessionErrors.add(actionRequest, e.getClass(), e);
				} else {
					SessionErrors.add(actionRequest, e.getClass());
				}

				return;
			} else if (cmd.equals(Constants.ADD_TEMP)) {
				hideDefaultErrorMessage(actionRequest);
			}

			if (e instanceof AntivirusScannerException ||
				e instanceof DuplicateFileEntryException ||
				e instanceof FileExtensionException ||
				e instanceof FileNameException ||
				e instanceof FileSizeException ||
				e instanceof UploadRequestSizeException) {

				JSONObject jsonObject =
					_multipleUploadResponseHandler.onFailure(
						actionRequest, (PortalException)e);

				JSONPortletResponseUtil.writeJSON(
					actionRequest, actionResponse, jsonObject);
			}

			if (e instanceof AntivirusScannerException) {
				SessionErrors.add(actionRequest, e.getClass(), e);
			} else {
				SessionErrors.add(actionRequest, e.getClass());
			}
		} else if (e instanceof DuplicateLockException ||
				 e instanceof FileEntryLockException.MustOwnLock ||
				 e instanceof InvalidFileVersionException ||
				 e instanceof NoSuchFileEntryException ||
				 e instanceof PrincipalException) {

			if (e instanceof DuplicateLockException) {
				DuplicateLockException dle = (DuplicateLockException)e;

				SessionErrors.add(actionRequest, dle.getClass(), dle.getLock());
			} else {
				SessionErrors.add(actionRequest, e.getClass());
			}

			actionResponse.setRenderParameter("mvcPath", "/document_library/error.jsp");
		} else {
			Throwable cause = e.getCause();

			if (cause instanceof DuplicateFileEntryException) {
				SessionErrors.add(actionRequest, DuplicateFileEntryException.class);
			} else {
				throw e;
			}
		}
	}

	protected void restoreTrashEntries(ActionRequest actionRequest)throws Exception {

		long[] restoreTrashEntryIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "restoreTrashEntryIds"), 0L);

		for (long restoreTrashEntryId : restoreTrashEntryIds) {
			_trashEntryService.restoreEntry(restoreTrashEntryId);
		}
	}

	protected void revertFileEntry(ActionRequest actionRequest)
		throws Exception {

		long fileEntryId = ParamUtil.getLong(actionRequest, "fileEntryId");
		String version = ParamUtil.getString(actionRequest, "version");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			DLFileEntry.class.getName(), actionRequest);

		_dlAppService.revertFileEntry(fileEntryId, version, serviceContext);
	}

	@Reference(unbind = "-")
	protected void setDLAppService(DLAppService dlAppService) {
		_dlAppService = dlAppService;
	}

	@Reference(unbind = "-")
	protected void setDLTrashService(DLTrashService dlTrashService) {
		_dlTrashService = dlTrashService;
	}

	@Reference(unbind = "-")
	protected void setTrashEntryService(TrashEntryService trashEntryService) {
		_trashEntryService = trashEntryService;
	}

	protected FileEntry updateFileEntry(
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse,
			UploadPortletRequest uploadPortletRequest)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String cmd = ParamUtil.getString(uploadPortletRequest, Constants.CMD);

		long fileEntryId = ParamUtil.getLong(
			uploadPortletRequest, "fileEntryId");

		long repositoryId = ParamUtil.getLong(
			uploadPortletRequest, "repositoryId");
		long folderId = ParamUtil.getLong(uploadPortletRequest, "folderId");
		String sourceFileName = uploadPortletRequest.getFileName("file");
		String title = ParamUtil.getString(uploadPortletRequest, "title");
		String description = ParamUtil.getString(
			uploadPortletRequest, "description");
		String changeLog = ParamUtil.getString(
			uploadPortletRequest, "changeLog");

		boolean majorVersion = ParamUtil.getBoolean(
			uploadPortletRequest, "majorVersion");

		boolean updateVersionDetails = ParamUtil.getBoolean(
			uploadPortletRequest, "updateVersionDetails");

		if (!updateVersionDetails) {
			majorVersion = Boolean.FALSE;
		}

		if (cmd.equals(Constants.ADD_DYNAMIC)) {
			title = uploadPortletRequest.getFileName("file");
		}

		try (InputStream inputStream = uploadPortletRequest.getFileAsStream(
				"file")) {

			String contentType = uploadPortletRequest.getContentType("file");
			long size = uploadPortletRequest.getSize("file");

			if ((cmd.equals(Constants.ADD) ||
				 cmd.equals(Constants.ADD_DYNAMIC)) &&
				(size == 0)) {

				contentType = MimeTypesUtil.getContentType(title);
			}

			if (cmd.equals(Constants.ADD) ||
				cmd.equals(Constants.ADD_DYNAMIC) || (size > 0)) {

				String portletName = portletConfig.getPortletName();

			/*	if (portletName.equals(DLPortletKeys.MEDIA_GALLERY_DISPLAY)) {
					PortletDisplay portletDisplay =
						themeDisplay.getPortletDisplay();

					DLPortletInstanceSettings dlPortletInstanceSettings =
						DLPortletInstanceSettings.getInstance(
							themeDisplay.getLayout(), portletDisplay.getId());

					String[] mimeTypes =
						dlPortletInstanceSettings.getMimeTypes();

					if (Arrays.binarySearch(mimeTypes, contentType) < 0) {
						throw new FileMimeTypeException(contentType);
					}
				}*/
			}

			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				DLFileEntry.class.getName(), uploadPortletRequest);

			FileEntry fileEntry = null;

			if (cmd.equals(Constants.ADD) ||
				cmd.equals(Constants.ADD_DYNAMIC)) {

				// Add file entry

				fileEntry = _dlAppService.addFileEntry(
					repositoryId, folderId, sourceFileName, contentType, title,
					description, changeLog, inputStream, size, serviceContext);

				if (cmd.equals(Constants.ADD_DYNAMIC)) {
					JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

					jsonObject.put("fileEntryId", fileEntry.getFileEntryId());

					JSONPortletResponseUtil.writeJSON(
						actionRequest, actionResponse, jsonObject);
				}
			}
			else if (cmd.equals(Constants.UPDATE_AND_CHECKIN)) {

				// Update file entry and checkin

				fileEntry = _dlAppService.updateFileEntryAndCheckIn(
					fileEntryId, sourceFileName, contentType, title,
					description, changeLog, majorVersion, inputStream, size,
					serviceContext);
			}
			else {

				// Update file entry

				fileEntry = _dlAppService.updateFileEntry(
					fileEntryId, sourceFileName, contentType, title,
					description, changeLog, majorVersion, inputStream, size,
					serviceContext);
			}

			return fileEntry;
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(UploadMultipleDocumentsMVCActionCommand.class);

	private DLAppService _dlAppService;
	private DLTrashService _dlTrashService;

	@Reference
	private DLValidator _dlValidator;

	@Reference
	private Http _http;

	@Reference(target = "(upload.response.handler=multiple)")
	private UploadResponseHandler _multipleUploadResponseHandler;

	@Reference
	private Portal _portal;

	private TrashEntryService _trashEntryService;
}
