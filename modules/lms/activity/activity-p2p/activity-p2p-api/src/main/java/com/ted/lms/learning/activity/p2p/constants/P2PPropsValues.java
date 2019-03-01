package com.ted.lms.learning.activity.p2p.constants;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsUtil;

public class P2PPropsValues {
	public static final long P2P_FILE_MAX_SIZE = GetterUtil.getLong(PropsUtil.get(P2PPropsKeys.P2P_FILE_MAX_SIZE), 314572800);
	public static final String[] P2P_FILE_EXTENSIONS = PropsUtil.getArray(P2PPropsKeys.P2P_FILE_EXTENSIONS);
	public static final String P2P_EMAIL_SIGNATURE = GetterUtil.getString(PropsUtil.get(P2PPropsKeys.P2P_EMAIL_SIGNATURE), "");
	public static final String ADMIN_EMAIL_FROM_NAME = GetterUtil.getString(PropsUtil.get(P2PPropsKeys.ADMIN_EMAIL_FROM_NAME), "");
	public static final String ADMIN_EMAIL_FROM_ADDRESS = GetterUtil.getString(PropsUtil.get(P2PPropsKeys.ADMIN_EMAIL_FROM_ADDRESS), "");
}
