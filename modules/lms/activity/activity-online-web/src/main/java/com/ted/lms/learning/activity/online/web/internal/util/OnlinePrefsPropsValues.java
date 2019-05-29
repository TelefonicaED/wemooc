package com.ted.lms.learning.activity.online.web.internal.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.ted.lms.learning.activity.online.web.constants.OnlinePropsKeys;
import com.ted.lms.learning.activity.online.web.constants.OnlinePropsValues;

public class OnlinePrefsPropsValues {
	
	public static long getOnlineFileMaxSize(long companyId) {
		return PrefsPropsUtil.getLong(companyId, OnlinePropsKeys.ONLINE_FILE_MAX_SIZE, OnlinePropsValues.ONLINE_FILE_MAX_SIZE);
	}
	
	public static String[] getOnlineFileExtensions(long companyId) {
		String[] defaultValues = OnlinePropsValues.ONLINE_FILE_EXTENSIONS;
		if(Validator.isNull(defaultValues) || defaultValues.length == 0) {
			defaultValues = StringUtil.split(".bat,.com,.exe,.msi", StringPool.COMMA);
		}
		return PrefsPropsUtil.getStringArray(companyId, OnlinePropsKeys.ONLINE_FILE_EXTENSIONS, StringPool.COMMA, defaultValues);
	}
}
