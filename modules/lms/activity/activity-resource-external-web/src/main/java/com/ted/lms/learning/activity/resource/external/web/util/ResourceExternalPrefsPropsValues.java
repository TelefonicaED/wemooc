package com.ted.lms.learning.activity.resource.external.web.util;

import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.ted.lms.learning.activity.resource.external.web.constants.ResourceExternalPropsKeys;
import com.ted.lms.learning.activity.resource.external.web.constants.ResourceExternalPropsValues;

public class ResourceExternalPrefsPropsValues {
	public static int getMaxFiles(long companyId) {
		return PrefsPropsUtil.getInteger(companyId, ResourceExternalPropsKeys.MAX_FILES, ResourceExternalPropsValues.MAX_FILES);
	}
	
	public static String getQuestionsAllowed(long companyId) {
		return PrefsPropsUtil.getString(companyId, ResourceExternalPropsKeys.QUESTIONS_ALLOWED, ResourceExternalPropsValues.QUESTIONS_ALLOWED);
	}
}
