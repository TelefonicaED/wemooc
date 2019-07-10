package com.ted.lms.web.internal.portlet.action;

import com.liferay.exportimport.kernel.lar.ExportImportHelper;
import com.liferay.portal.kernel.backgroundtask.BackgroundTask;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskConstants;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskManagerUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portlet.portletconfiguration.action.ActionUtil;
import java.io.PrintWriter;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

public abstract class BaseImportCSVMVCResourceCommand extends BaseMVCResourceCommand {
	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {
		
		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		long backgroundTaskId = ParamUtil.getLong(resourceRequest, "backgroundTaskId");

		if(Validator.isNotNull(backgroundTaskId)) {
			
			//Creamos el proceso de importación y mandamos los identificadores y el progreso	
					
			JSONObject oreturned = JSONFactoryUtil.createJSONObject();
			resourceResponse.setContentType("application/json");
					
			try {
				BackgroundTask backgroundTask = BackgroundTaskManagerUtil.getBackgroundTask(backgroundTaskId);
				boolean finished = backgroundTask.isCompleted();
				
				oreturned.put("finished", finished);
				if(finished){
					oreturned.put("SUCCESSFUL", backgroundTask.getStatus() == BackgroundTaskConstants.STATUS_SUCCESSFUL);
					oreturned.put("results",backgroundTask.getStatusMessage());		
				}
			} catch (PortalException e) {
				e.printStackTrace();
			}
			
			oreturned.put("backgroundTaskId", backgroundTaskId);
					
			PrintWriter out = resourceResponse.getWriter();
			out.print(oreturned.toString());
			out.flush();
			out.close();
		}else {
			
			long parentCourseId  = ParamUtil.getLong(resourceRequest, "parentCourseId");
			
			ImportMVCActionCommand importCSVMVCActionCommand = getImportCSVMVCActionCommand();
			String portletId = getPortletId(resourceRequest);
			
			//Fichero a importar
			FileEntry fileEntry = importCSVMVCActionCommand.getTempFileEntry(themeDisplay.getScopeGroupId(), themeDisplay.getUserId(), 
					ExportImportHelper.TEMP_FOLDER_NAME + portletId);
			
			backgroundTaskId = getBackgroundTaskId(resourceRequest, resourceResponse, ExportImportHelper.TEMP_FOLDER_NAME + portletId, fileEntry);
			
			resourceRequest.setAttribute("parentCourseId", parentCourseId);
			resourceRequest.setAttribute("backgroundTaskId", backgroundTaskId);
	
			PortletRequestDispatcher portletRequestDispatcher = getPortletRequestDispatcher(
					resourceRequest, "/import_csv_result.jsp");
	
			resourceRequest = ActionUtil.getWrappedResourceRequest(resourceRequest, null);
			portletRequestDispatcher.include(resourceRequest, resourceResponse);
		}
	}
	
	protected abstract long getBackgroundTaskId(ResourceRequest resourceRequest, ResourceResponse resourceResponse, String folderName, FileEntry fileEntry);
	protected abstract String getPortletId(ResourceRequest resourceRequest);
	protected abstract ImportMVCActionCommand getImportCSVMVCActionCommand();
}
