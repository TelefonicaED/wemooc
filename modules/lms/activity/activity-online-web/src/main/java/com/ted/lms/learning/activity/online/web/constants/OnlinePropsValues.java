package com.ted.lms.learning.activity.online.web.constants;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsUtil;

public class OnlinePropsValues {
	public static final long ONLINE_FILE_MAX_SIZE = GetterUtil.getLong(PropsUtil.get(OnlinePropsKeys.ONLINE_FILE_MAX_SIZE), 314572800);
	public static final String[] ONLINE_FILE_EXTENSIONS = PropsUtil.getArray(OnlinePropsKeys.ONLINE_FILE_EXTENSIONS);
}
