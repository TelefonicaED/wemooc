package com.ted.lms.web.internal.notifications;

import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;
import com.ted.lms.constants.LMSPortletKeys;

import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true, property = "javax.portlet.name=" + LMSPortletKeys.COURSE,
	service = UserNotificationDefinition.class
)
public class CopyCourseUserNotificationDefinition extends UserNotificationDefinition {
	public CopyCourseUserNotificationDefinition() {
		super(
				LMSPortletKeys.COURSE, 0,
			UserNotificationDefinition.NOTIFICATION_TYPE_ADD_ENTRY,
			"copy-course");

		addUserNotificationDeliveryType(
			new UserNotificationDeliveryType(
				"email", UserNotificationDeliveryConstants.TYPE_EMAIL, true,
				true));
		addUserNotificationDeliveryType(
			new UserNotificationDeliveryType(
				"website", UserNotificationDeliveryConstants.TYPE_WEBSITE, true,
				true));
	}
}
