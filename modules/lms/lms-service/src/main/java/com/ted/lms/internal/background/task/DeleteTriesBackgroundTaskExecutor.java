package com.ted.lms.internal.background.task;

import com.liferay.portal.kernel.backgroundtask.BackgroundTask;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskConstants;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskExecutor;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskResult;
import com.liferay.portal.kernel.backgroundtask.BaseBackgroundTaskExecutor;
import com.liferay.portal.kernel.backgroundtask.display.BackgroundTaskDisplay;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.ted.lms.service.LearningActivityLocalServiceUtil;

import java.io.Serializable;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true,
	property = "background.task.executor.class.name=com.ted.lms.internal.background.task.DeleteTriesBackgroundTaskExecutor",
	service = BackgroundTaskExecutor.class
)
public class DeleteTriesBackgroundTaskExecutor extends BaseBackgroundTaskExecutor {
	
	private static final Log log = LogFactoryUtil.getLog(DeleteTriesBackgroundTaskExecutor.class);

	public DeleteTriesBackgroundTaskExecutor() {
	}

	@Override
	public BackgroundTaskResult execute(BackgroundTask backgroundTask) throws Exception {
		
		log.debug("entramos");
		
		Map<String, Serializable> taskContextMap = backgroundTask.getTaskContextMap();

		long userId = MapUtil.getLong(taskContextMap, "userId");
		long actId = MapUtil.getLong(taskContextMap, "actId");
		long studentId = MapUtil.getLong(taskContextMap, "studentId");
		boolean deleteOnlyFailed = MapUtil.getBoolean(taskContextMap, "deleteOnlyFailed");
		
		boolean deleteTries = LearningActivityLocalServiceUtil.deleteTries(userId, actId, studentId, deleteOnlyFailed);
		
		BackgroundTaskResult backgroundTaskResult = null;
		
		if(!deleteTries) {
			backgroundTaskResult = new BackgroundTaskResult(BackgroundTaskConstants.STATUS_FAILED, "");
		}else {
			backgroundTaskResult = new BackgroundTaskResult(BackgroundTaskConstants.STATUS_SUCCESSFUL, "");
		}
		return backgroundTaskResult;
	}

	@Override
	public BackgroundTaskDisplay getBackgroundTaskDisplay(BackgroundTask backgroundTask) {
		return null;
	}

	@Override
	public BackgroundTaskExecutor clone() {
		return this;
	}
	
	// if it's not serial then multiple instances of this executor can run parallel, to run it in queue mode, we use isSerial true
	@Override
	public boolean isSerial() {
	    return true;
	}

}
