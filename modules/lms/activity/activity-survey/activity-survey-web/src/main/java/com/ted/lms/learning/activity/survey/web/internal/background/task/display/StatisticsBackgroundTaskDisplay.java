package com.ted.lms.learning.activity.survey.web.internal.background.task.display;

import com.liferay.portal.kernel.backgroundtask.BackgroundTask;
import com.liferay.portal.kernel.backgroundtask.display.BaseBackgroundTaskDisplay;
import com.liferay.portal.kernel.template.TemplateResource;
import com.liferay.portal.kernel.template.URLTemplateResource;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.Map;

public class StatisticsBackgroundTaskDisplay extends BaseBackgroundTaskDisplay {

	public StatisticsBackgroundTaskDisplay(BackgroundTask backgroundTask) {
		super(backgroundTask);
	}

	@Override
	public int getPercentage() {
		return GetterUtil.getInteger(
				getBackgroundTaskStatusAttributeLong("percentage"),
				PERCENTAGE_NONE);
	}

	@Override
	protected TemplateResource getTemplateResource() {
		Class<?> clazz = getClass();

		ClassLoader classLoader = clazz.getClassLoader();

		return new URLTemplateResource(
			_PROGRESS_TEMPLATE, classLoader.getResource(_PROGRESS_TEMPLATE));
	}

	@Override
	protected Map<String, Object> getTemplateVars() {
		return null;
	}

	private static final String _PROGRESS_TEMPLATE =
		"com/ted/lms/learning/activity/survey/web/internal/background/task/display/dependencies/statistics_background_task_progress.ftl";

	

}
