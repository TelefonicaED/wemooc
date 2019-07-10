/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.ted.lms.web.internal.portlet.action;

import com.liferay.document.library.kernel.exception.FileSizeException;
import com.liferay.exportimport.kernel.exception.LARFileSizeException;
import com.liferay.exportimport.kernel.model.ExportImportConfiguration;
import com.liferay.exportimport.kernel.staging.Staging;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.JSONPortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.servlet.ServletResponseConstants;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadException;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.upload.UploadRequestSizeException;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.TempFileEntryUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.exception.CSVFileException;

import java.io.InputStream;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadBase;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Daniel Kocsis
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + LMSPortletKeys.COURSE,
		"mvc.command.name=importLayouts"
	},
	service = {ImportMVCActionCommand.class, MVCActionCommand.class}
)
public class ImportMVCActionCommand extends BaseMVCActionCommand {

	protected FileEntry addTempFileEntry(
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
	
	protected void handleUploadException(
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
					themeDisplay.getLocale(), "coure-admin.assign-users.import.error-headers");
			}
			exceptionMessagesJSONObject.put("message", errorMessage);
			exceptionMessagesJSONObject.put("status", ServletResponseConstants.SC_FILE_CUSTOM_EXCEPTION);
			
			jsonObject = exceptionMessagesJSONObject;
		}else {
			jsonObject = staging.getExceptionMessagesJSONObject(
				themeDisplay.getLocale(), e, (ExportImportConfiguration)null);
		}
			
		System.out.println("jsonObject: " + jsonObject);

		JSONPortletResponseUtil.writeJSON(
			actionRequest, actionResponse, jsonObject);
	}

	protected void checkExceededSizeLimit(HttpServletRequest request)
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

	protected void deleteTempFileEntry(
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

	protected void deleteTempFileEntry(long groupId, long userId, String folderName)
		throws PortalException {

		String[] tempFileNames = TempFileEntryUtil.getTempFileNames(
			groupId, userId, folderName);

		for (String tempFileEntryName : tempFileNames) {
			TempFileEntryUtil.deleteTempFileEntry(
				groupId, userId, folderName, tempFileEntryName);
		}
	}
	
	protected FileEntry getTempFileEntry(long groupId, long userId, String folderName) throws PortalException{
		String[] tempFileNames = TempFileEntryUtil.getTempFileNames(groupId, userId, folderName);

		if (tempFileNames.length == 0) {
			return null;
		}

		return TempFileEntryUtil.getTempFileEntry(
			groupId, userId,
			folderName,
			tempFileNames[0]);
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

	}

	private static final Log log = LogFactoryUtil.getLog(ImportMVCActionCommand.class);


	@Reference
	private Portal portal;
	
	@Reference
	private Staging staging;

}