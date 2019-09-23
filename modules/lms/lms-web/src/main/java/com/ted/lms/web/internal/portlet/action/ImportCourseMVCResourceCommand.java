package com.ted.lms.web.internal.portlet.action;

import com.liferay.portal.kernel.backgroundtask.BackgroundTask;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskConstants;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskManagerUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.ted.lms.constants.LMSPortletKeys;

import java.io.PrintWriter;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + LMSPortletKeys.COURSE,
		"mvc.command.name=/courses/import_course"
	},
	service = MVCResourceCommand.class
)
public class ImportCourseMVCResourceCommand extends BaseMVCResourceCommand {
	
	private static final Log log = LogFactoryUtil.getLog(ImportCourseMVCResourceCommand.class);
	
	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {
		
		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		long backgroundTaskId = ParamUtil.getLong(resourceRequest, "backgroundTaskId");
		
		log.debug("backgroundTaskId: " + backgroundTaskId);
		
		JSONObject oreturned = JSONFactoryUtil.createJSONObject();
		resourceResponse.setContentType("application/json");

		if(Validator.isNotNull(backgroundTaskId)) {
			
			//Creamos el proceso de importación y mandamos los identificadores y el progreso			
			try {
				BackgroundTask backgroundTask = BackgroundTaskManagerUtil.getBackgroundTask(backgroundTaskId);
				boolean finished = backgroundTask.isCompleted();
				
				oreturned.put("finished", finished);
				if(finished){
					oreturned.put("SUCCESSFUL", backgroundTask.getStatus() == BackgroundTaskConstants.STATUS_SUCCESSFUL);
					oreturned.put("statusMessage",backgroundTask.getStatusMessage());		
				}
			} catch (PortalException e) {
				e.printStackTrace();
			}
		}
		
		oreturned.put("backgroundTaskId", backgroundTaskId);
				
		PrintWriter out = resourceResponse.getWriter();
		out.print(oreturned.toString());
		out.flush();
		out.close();
	}
}
