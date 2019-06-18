package com.ted.lms.learning.activity.survey.web.internal.background.task;

import com.liferay.portal.kernel.backgroundtask.BackgroundTask;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskExecutor;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskManagerUtil;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskResult;
import com.liferay.portal.kernel.backgroundtask.BaseBackgroundTaskExecutor;
import com.liferay.portal.kernel.backgroundtask.display.BackgroundTaskDisplay;
import com.liferay.portal.kernel.util.MapUtil;
import com.ted.lms.learning.activity.survey.service.SurveyResultLocalServiceUtil;
import com.ted.lms.learning.activity.survey.web.constants.SurveyPortletKeys;
import com.ted.lms.learning.activity.survey.web.internal.background.task.display.StatisticsBackgroundTaskDisplay;

import java.io.File;
import java.io.Serializable;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true,
	property = "background.task.executor.class.name=com.ted.lms.learning.activity.survey.web.internal.background.task.StatisticsBackgroundTaskExecutor",
	service = BackgroundTaskExecutor.class
)
public class StatisticsBackgroundTaskExecutor extends BaseBackgroundTaskExecutor {
	
	public StatisticsBackgroundTaskExecutor() {
	}

	@Override
	public BackgroundTaskResult execute(BackgroundTask backgroundTask) throws Exception {
		
		System.out.println("entramos");
		
		Map<String, Serializable> taskContextMap = backgroundTask.getTaskContextMap();

		long actId = (long)taskContextMap.get("actId");
		Locale locale = (Locale)taskContextMap.get("locale");
		long userId = MapUtil.getLong(taskContextMap, "userId");
		
		File statisticsFile = SurveyResultLocalServiceUtil.exportStatisticsAsFile(SurveyPortletKeys.SURVEY, actId, locale);
		
		BackgroundTaskManagerUtil.addBackgroundTaskAttachment(
				userId, backgroundTask.getBackgroundTaskId(), statisticsFile.getName(), statisticsFile);

		return BackgroundTaskResult.SUCCESS;
	}

	@Override
	public BackgroundTaskDisplay getBackgroundTaskDisplay(BackgroundTask backgroundTask) {
		return new StatisticsBackgroundTaskDisplay(backgroundTask);
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
