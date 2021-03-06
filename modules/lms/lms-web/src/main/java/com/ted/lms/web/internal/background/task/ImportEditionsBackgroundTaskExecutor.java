package com.ted.lms.web.internal.background.task;

import com.liferay.portal.kernel.backgroundtask.BackgroundTask;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskConstants;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskExecutor;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskResult;
import com.liferay.portal.kernel.backgroundtask.BaseBackgroundTaskExecutor;
import com.liferay.portal.kernel.backgroundtask.display.BackgroundTaskDisplay;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.TempFileEntryUtil;
import com.ted.lms.service.CourseLocalServiceUtil;

import java.io.Serializable;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true,
	property = "background.task.executor.class.name=com.ted.lms.web.internal.background.task.ImportEditionsBackgroundTaskExecutor",
	service = BackgroundTaskExecutor.class
)
public class ImportEditionsBackgroundTaskExecutor extends BaseBackgroundTaskExecutor {
	
	private static final Log log = LogFactoryUtil.getLog(ImportEditionsBackgroundTaskExecutor.class);

	public ImportEditionsBackgroundTaskExecutor() {
		/*setBackgroundTaskStatusMessageTranslator(
				new ImportCourseUsersBackgroundTaskStatusMessageTranslator());*/
	}

	@Override
	public BackgroundTaskResult execute(BackgroundTask backgroundTask) throws Exception {
		
		log.debug("entramos");
		
		Map<String, Serializable> taskContextMap = backgroundTask.getTaskContextMap();

		long parentCourseId = MapUtil.getLong(taskContextMap, "parentCourseId");
		long userId = MapUtil.getLong(taskContextMap, "userId");
		long groupId = MapUtil.getLong(taskContextMap, "groupId");
		String folderName = MapUtil.getString(taskContextMap, "folderName");
		String fileName = MapUtil.getString(taskContextMap, "fileName");
		
		FileEntry fileEntry = TempFileEntryUtil.getTempFileEntry(groupId, userId, folderName, fileName);
		
		JSONObject importResult = CourseLocalServiceUtil.importEditions(userId, parentCourseId, fileEntry);
		
		TempFileEntryUtil.deleteTempFileEntry(fileEntry.getFileEntryId());
		
		BackgroundTaskResult backgroundTaskResult = null;
		
		if(importResult.getInt("error") > 0) {
			backgroundTaskResult = new BackgroundTaskResult(BackgroundTaskConstants.STATUS_FAILED, importResult.toJSONString());
		}else {
			backgroundTaskResult = new BackgroundTaskResult(BackgroundTaskConstants.STATUS_SUCCESSFUL, importResult.toJSONString());
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
