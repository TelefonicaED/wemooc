package com.ted.lms.learning.activity.resource.external.web.constants;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsUtil;

public class ResourceExternalPropsValues {
	public static final int MAX_FILES = GetterUtil.getInteger(PropsUtil.get(ResourceExternalPropsKeys.MAX_FILES), ResourceExternalConstants.DEFAULT_MAX_FILES);
	public static final String QUESTIONS_ALLOWED = GetterUtil.getString(PropsUtil.get(ResourceExternalPropsKeys.QUESTIONS_ALLOWED), ResourceExternalConstants.DEFAULT_QUESTIONS_ALLOWED);
}
