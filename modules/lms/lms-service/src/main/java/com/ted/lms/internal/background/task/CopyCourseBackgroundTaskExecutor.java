package com.ted.lms.internal.background.task;

import com.liferay.portal.kernel.backgroundtask.BackgroundTask;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskConstants;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskExecutor;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskResult;
import com.liferay.portal.kernel.backgroundtask.BaseBackgroundTaskExecutor;
import com.liferay.portal.kernel.backgroundtask.display.BackgroundTaskDisplay;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserNotificationEventLocalServiceUtil;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.model.Course;
import com.ted.lms.service.CourseLocalServiceUtil;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true,
	property = "background.task.executor.class.name=com.ted.lms.internal.background.task.CopyCourseBackgroundTaskExecutor",
	service = BackgroundTaskExecutor.class
)
public class CopyCourseBackgroundTaskExecutor extends BaseBackgroundTaskExecutor {
	
	private static final Log log = LogFactoryUtil.getLog(CopyCourseBackgroundTaskExecutor.class);
	
	@Override
	public BackgroundTaskResult execute(BackgroundTask backgroundTask) throws Exception {
		
		Map<String, Serializable> taskContextMap = backgroundTask.getTaskContextMap();
		
		long userId = (long)taskContextMap.get("userId");
		ServiceContext serviceContext = (ServiceContext)taskContextMap.get("serviceContext");
		long courseId = (long)taskContextMap.get("courseId");
		long courseParentId = (long)taskContextMap.get("courseParentId");
		String title = (String)taskContextMap.get("title");
		long layoutSetPrototypeId = (long)taskContextMap.get("layoutSetPrototypeId");
		Date registrationStartDate = (Date)taskContextMap.get("registrationStartDate");
		Date registrationEndDate = (Date)taskContextMap.get("registrationEndDate");
		Date executionStartDate = (Date)taskContextMap.get("executionStartDate");
		Date executionEndDate = (Date)taskContextMap.get("executionEndDate");
		boolean copyForum = (boolean)taskContextMap.get("copyForum");
		boolean copyDocuments = (boolean)taskContextMap.get("copyDocuments");
		
		if(log.isDebugEnabled()) {
			log.debug("userId: " + userId);
			log.debug("courseId: " + courseId);
			log.debug("courseParentId: " + courseParentId);
			log.debug("title: " + title);
			log.debug("layoutSetPrototypeId: " + layoutSetPrototypeId);
			log.debug("registrationStartDate: " + registrationStartDate);
			log.debug("registrationEndDate: " + registrationEndDate);
			log.debug("executionStartDate: " + executionStartDate);
			log.debug("executionEndDate: " + executionEndDate);
			log.debug("copyForum: " + copyForum);
			log.debug("copyDocuments: " + copyDocuments);
		}
		
		try {
		
			Course course = CourseLocalServiceUtil.copyCourse(userId, courseId, courseParentId, title, layoutSetPrototypeId, registrationStartDate, registrationEndDate, 
					executionStartDate, executionEndDate, copyForum, copyDocuments, serviceContext);
		//	sendNotification(backgroundTask, course, BackgroundTaskConstants.STATUS_SUCCESSFUL);
		} catch (Exception e) {
		//	sendNotification(backgroundTask, null, BackgroundTaskConstants.STATUS_FAILED);
			throw e;
		}

		return BackgroundTaskResult.SUCCESS;
	}
	
	protected void sendNotification(BackgroundTask backgroundTask, Course course, int status) throws PortalException {

		UserNotificationEventLocalServiceUtil.sendUserNotificationEvents(
			backgroundTask.getUserId(), LMSPortletKeys.COURSE,
			UserNotificationDeliveryConstants.TYPE_WEBSITE,
			getPayload(backgroundTask, course, status));
	}
	
	protected JSONObject getPayload(BackgroundTask backgroundTask, Course course, int status) {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("backgroundTaskId", backgroundTask.getBackgroundTaskId());
		jsonObject.put("courseURL", course != null ? course.getFriendlyURL() : "");
		jsonObject.put("status", status);

		return jsonObject;
	}

	@Override
	public BackgroundTaskDisplay getBackgroundTaskDisplay(BackgroundTask backgroundTask) {
		return null;
	}

	@Override
	public BackgroundTaskExecutor clone() {
		return this;
	}
	

}
