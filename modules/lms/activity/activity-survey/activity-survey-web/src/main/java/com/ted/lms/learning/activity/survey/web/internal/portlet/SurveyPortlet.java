package com.ted.lms.learning.activity.survey.web.internal.portlet;

import com.ted.lms.learning.activity.survey.web.constants.SurveyPortletKeys;
import com.ted.lms.learning.activity.survey.web.internal.background.task.StatisticsBackgroundTaskExecutor;
import com.ted.lms.learning.activity.survey.web.internal.portlet.action.StatisticsViewMVCRenderCommand;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.backgroundtask.BackgroundTask;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskManager;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskManagerUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCCommandCache;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author JE10436
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.init-param.template-path=/META-INF/resources/",
		"javax.portlet.name=" + SurveyPortletKeys.SURVEY,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=user",
		"javax.portlet.supported-public-render-parameter=actId",
		"javax.portlet.init-param.always-send-redirect=true",
		"com.liferay.portlet.use-default-template=true"
	},
	service = Portlet.class
)
public class SurveyPortlet extends MVCPortlet {
	
	private static final Log log = LogFactoryUtil.getLog(SurveyPortlet.class);
	
	public void  serveResource(ResourceRequest request, ResourceResponse response) throws PortletException, IOException {

		String resourceId = request.getResourceID();
		
		if (resourceId != null && resourceId.equals("statisticsReports")){
			
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
			
			long backgroundTaskId = ParamUtil.getLong(request, "backgroundTaskId");
			log.debug("Entra en serve ResourceId: statisticsReports, backgroupId: " + backgroundTaskId);

			JSONObject oreturned = JSONFactoryUtil.createJSONObject();
			response.setContentType("application/json");

			if(Validator.isNotNull(backgroundTaskId)){
				
				try {
					BackgroundTask backgroundTask = BackgroundTaskManagerUtil.getBackgroundTask(backgroundTaskId);
					boolean finished = backgroundTask.isCompleted();
					
					oreturned.put("finished", finished);
					if(finished){
						List<FileEntry> attachmentsFileEntries = backgroundTask.getAttachmentsFileEntries();
						
						log.debug("FINISHED["+backgroundTaskId+"]");
						if (ListUtil.isNotEmpty(attachmentsFileEntries)) {
							String fileURL = PortletFileRepositoryUtil.getDownloadPortletFileEntryURL(themeDisplay, attachmentsFileEntries.get(0), StringPool.BLANK);
							oreturned.put("fileURL",fileURL);		
						}
						oreturned.put("contentType", "application/vnd.ms-excel");
					}
				} catch (PortalException e) {
					e.printStackTrace();
				}
				
				oreturned.put("backgroundTaskId", backgroundTaskId);
			}else{
				log.debug("backgroundTaskId: " + backgroundTaskId);				
				
				Map<String, Serializable> taskContextMap = new HashMap<>();
				
				taskContextMap.put("actId", ParamUtil.getLong(request, "actId"));
				taskContextMap.put("locale", themeDisplay.getLocale());
				taskContextMap.put("userId", themeDisplay.getUserId());
				
				try {
					BackgroundTask backgroundTask = BackgroundTaskManagerUtil.addBackgroundTask(themeDisplay.getUserId(), themeDisplay.getScopeGroupId(),
								SurveyPortletKeys.SURVEY, StatisticsBackgroundTaskExecutor.class.getName(),
								taskContextMap, new ServiceContext());
					backgroundTaskId = backgroundTask.getBackgroundTaskId();
				} catch (PortalException e) {
					e.printStackTrace();
				}
				
				log.debug("backgroundTaskId: " + backgroundTaskId);
				
				oreturned.put("backgroundTaskId", backgroundTaskId);
			}	
			PrintWriter out = response.getWriter();
			out.print(oreturned.toString());
			out.flush();
			out.close();
		}
	}
}