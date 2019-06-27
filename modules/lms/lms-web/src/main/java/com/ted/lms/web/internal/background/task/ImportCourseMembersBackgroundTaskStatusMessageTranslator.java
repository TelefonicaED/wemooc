package com.ted.lms.web.internal.background.task;

import com.liferay.portal.kernel.backgroundtask.BackgroundTaskStatus;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskStatusMessageTranslator;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.util.LongWrapper;
import java.util.Map;

public class ImportCourseMembersBackgroundTaskStatusMessageTranslator implements BackgroundTaskStatusMessageTranslator {

	@Override
	public void translate(BackgroundTaskStatus backgroundTaskStatus, Message message) {

	}

	protected long getTotal(Map<String, LongWrapper> modelCounters) {
		if (modelCounters == null) {
			return 0;
		}

		long total = 0;

		for (Map.Entry<String, LongWrapper> entry : modelCounters.entrySet()) {
			LongWrapper longWrapper = entry.getValue();

			total += longWrapper.getValue();
		}

		return total;
	}

}
