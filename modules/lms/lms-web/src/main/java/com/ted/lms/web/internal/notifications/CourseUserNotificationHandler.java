package com.ted.lms.web.internal.notifications;

import com.liferay.portal.kernel.notifications.BaseModelUserNotificationHandler;
import com.liferay.portal.kernel.notifications.UserNotificationHandler;
import com.ted.lms.constants.LMSPortletKeys;

import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true, property = "javax.portlet.name=" + LMSPortletKeys.COURSE,
	service = UserNotificationHandler.class
)
public class CourseUserNotificationHandler extends BaseModelUserNotificationHandler {
	public CourseUserNotificationHandler() {
		setPortletId(LMSPortletKeys.COURSE);
	}
}
