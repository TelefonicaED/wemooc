package com.ted.lms.web.internal.portlet.action;

import com.liferay.exportimport.kernel.lar.ExportImportHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;


public abstract class BaseImportCSVMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		
		System.out.println("action import csv");
		
		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);
		String portletId = getPortletId(actionRequest);
		ImportMVCActionCommand importCSVMVCActionCommand = getImportCSVMVCActionCommand();
		try {
			
			if (cmd.equals(Constants.ADD_TEMP)) {
				
				System.out.println("cmd add temp");
				
				FileEntry fileEntry = importCSVMVCActionCommand.addTempFileEntry(
						actionRequest,
						ExportImportHelper.TEMP_FOLDER_NAME +
						portletId);
				
				validateFile(
						actionRequest, actionResponse,
						fileEntry);
	
				hideDefaultSuccessMessage(actionRequest);
			}else if (cmd.equals(Constants.DELETE_TEMP)) {
				importCSVMVCActionCommand.deleteTempFileEntry(
					actionRequest, actionResponse,
					ExportImportHelper.TEMP_FOLDER_NAME +
					portletId);
	
				hideDefaultSuccessMessage(actionRequest);
			}else if(cmd.equals(Constants.IMPORT)) {
				hideDefaultSuccessMessage(actionRequest);

				long backgroundTaskId = importData(
					actionRequest,
					actionResponse);
				
				String redirect = ParamUtil.getString(actionRequest, "redirect");
				redirect+= "&_" + portletId + "_backgroundTaskId=" + backgroundTaskId;
				
				System.out.println("redirect: " + redirect);
				
				sendRedirect(actionRequest, actionResponse, redirect);
				
				actionRequest.setAttribute("backgroundTaskId", String.valueOf(backgroundTaskId));
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (cmd.equals(Constants.ADD_TEMP) ||
				cmd.equals(Constants.DELETE_TEMP)) {

				hideDefaultSuccessMessage(actionRequest);

				importCSVMVCActionCommand.handleUploadException(
					actionRequest, actionResponse,
					ExportImportHelper.TEMP_FOLDER_NAME +
						portletId,
					e);
			}
		}
	}
	
	protected abstract long importData(ActionRequest actionRequest, ActionResponse actionResponse) throws PortalException;
	protected abstract void validateFile(ActionRequest actionRequest, ActionResponse actionResponse, FileEntry fileEntry) throws Exception;
	protected abstract String getPortletId(ActionRequest actionRequest);
	protected abstract ImportMVCActionCommand getImportCSVMVCActionCommand();
	
}
