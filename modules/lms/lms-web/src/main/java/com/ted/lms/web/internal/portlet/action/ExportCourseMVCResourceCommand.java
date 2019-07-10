package com.ted.lms.web.internal.portlet.action;

import com.liferay.exportimport.kernel.lar.PortletDataHandlerKeys;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.backgroundtask.BackgroundTask;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskManagerUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.model.Course;
import com.ted.lms.service.CourseLocalService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + LMSPortletKeys.COURSE,
		"mvc.command.name=/courses/export_course"
	},
	service = MVCResourceCommand.class
)
public class ExportCourseMVCResourceCommand extends BaseMVCResourceCommand {
	
	private static final Log log = LogFactoryUtil.getLog(ExportCourseMVCResourceCommand.class);
	
	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws PortletException, PortalException, IOException {
		
		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		long courseId = ParamUtil.getLong(resourceRequest, "courseId");
		
		Course course = courseLocalService.getCourse(courseId);
		
		long backgroundTaskId = ParamUtil.getLong(resourceRequest, "backgroundTaskId");
		log.debug("Entra en serve ResourceId: statisticsReports, backgroupId: " + backgroundTaskId);

		JSONObject oreturned = JSONFactoryUtil.createJSONObject();
		resourceResponse.setContentType("application/json");

		if(Validator.isNotNull(backgroundTaskId)){
			
			try {
				BackgroundTask backgroundTask = BackgroundTaskManagerUtil.getBackgroundTask(backgroundTaskId);
				boolean finished = backgroundTask.isCompleted();
				
				oreturned.put("finished", finished);
				if(finished){
					List<FileEntry> attachmentsFileEntries = backgroundTask.getAttachmentsFileEntries();
					
					log.debug("FINISHED["+backgroundTaskId+"]");
					oreturned.put("status", backgroundTask.getStatus());
					if (ListUtil.isNotEmpty(attachmentsFileEntries)) {
						String fileURL = PortletFileRepositoryUtil.getDownloadPortletFileEntryURL(themeDisplay, attachmentsFileEntries.get(0), StringPool.BLANK);
						oreturned.put("fileURL",fileURL);		
						oreturned.put("contentType", "application/zip");
					}else {
						oreturned.put("message", backgroundTask.getStatusMessage());
					}
					
				}
			} catch (PortalException e) {
				e.printStackTrace();
			}
			
			oreturned.put("backgroundTaskId", backgroundTaskId);
		}else{
			log.debug("backgroundTaskId: " + backgroundTaskId);	
			
			String fileName = ParamUtil.getString(resourceRequest, "fileName")+".lar";
			
			Map<String, String[]> parameterMap = new HashMap<>();
			parameterMap.put(PortletDataHandlerKeys.PORTLET_DATA, new String[]{"true"});
			parameterMap.put("groupId", new String[]{String.valueOf(course.getGroupCreatedId())});
			parameterMap.put("_modules_modules", new String[]{"true"});
			parameterMap.put("_modules_activities", new String[]{"true"});
			parameterMap.put("_modules_referenced-content", new String[]{"true"});
			parameterMap.put("portletResource", new String[]{LMSPortletKeys.MODULES_ADMIN});
			parameterMap.put(PortletDataHandlerKeys.PORTLET_DATA + "_" + LMSPortletKeys.MODULES_ADMIN, new String[]{"true"});
			parameterMap.put(PortletDataHandlerKeys.PERMISSIONS, new String[]{String.valueOf(ParamUtil.getBoolean(resourceRequest, PortletDataHandlerKeys.PERMISSIONS))});
			
			ServiceContext serviceContext = ServiceContextFactory.getInstance(resourceRequest);
			serviceContext.setScopeGroupId(course.getGroupCreatedId());
			
			backgroundTaskId = courseLocalService.exportCourseContent(
					courseId, themeDisplay.getPlid(), fileName, parameterMap, serviceContext);
			
			log.debug("backgroundTaskId: " + backgroundTaskId);
			
			oreturned.put("backgroundTaskId", backgroundTaskId);
		}	
		PrintWriter out = resourceResponse.getWriter();
		out.print(oreturned.toString());
		out.flush();
		out.close();

	}
	
	@Reference
	private Portal _portal;
	
	@Reference
	private Http _http;
	
	@Reference(unbind = "-")
	protected void setCourseLocalService(CourseLocalService courseLocalService) {
		this.courseLocalService = courseLocalService;
	}
	
	private CourseLocalService courseLocalService;

}
