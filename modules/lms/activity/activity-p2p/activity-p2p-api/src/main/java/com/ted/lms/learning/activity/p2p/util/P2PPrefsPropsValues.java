package com.ted.lms.learning.activity.p2p.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.ted.lms.learning.activity.p2p.constants.P2PPropsKeys;
import com.ted.lms.learning.activity.p2p.constants.P2PPropsValues;

public class P2PPrefsPropsValues {
	
	public static long getP2PFileMaxSize(long companyId) {
		return PrefsPropsUtil.getLong(companyId, P2PPropsKeys.P2P_FILE_MAX_SIZE, P2PPropsValues.P2P_FILE_MAX_SIZE);
	}
	
	public static String[] getP2PFileExtensions(long companyId) {
		String[] defaultValues = P2PPropsValues.P2P_FILE_EXTENSIONS;
		if(Validator.isNull(defaultValues) || defaultValues.length == 0) {
			defaultValues = StringUtil.split(".bat,.com,.exe,.msi", StringPool.COMMA);
		}
		return PrefsPropsUtil.getStringArray(companyId, P2PPropsKeys.P2P_FILE_EXTENSIONS, StringPool.COMMA, defaultValues);
	}
	
	public static String getP2PEmailSignature(long companyId) {
		return PrefsPropsUtil.getString(companyId, P2PPropsKeys.P2P_EMAIL_SIGNATURE, P2PPropsValues.P2P_EMAIL_SIGNATURE);
	}
	
	public static String getP2PEmailFromName(long companyId) {
		return PrefsPropsUtil.getString(companyId, P2PPropsKeys.ADMIN_EMAIL_FROM_NAME, P2PPropsValues.ADMIN_EMAIL_FROM_NAME);
	}
	
	public static String getP2PEmailFromAddress(long companyId) {
		return PrefsPropsUtil.getString(companyId, P2PPropsKeys.ADMIN_EMAIL_FROM_ADDRESS, P2PPropsValues.ADMIN_EMAIL_FROM_ADDRESS);
	}
}
