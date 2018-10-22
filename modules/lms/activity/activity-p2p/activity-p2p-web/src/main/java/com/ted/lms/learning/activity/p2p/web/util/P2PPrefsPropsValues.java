package com.ted.lms.learning.activity.p2p.web.util;

import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.ted.lms.learning.activity.p2p.web.constants.P2PPropsKeys;
import com.ted.lms.learning.activity.p2p.web.constants.P2PPropsValues;

public class P2PPrefsPropsValues {
	public static int getNumEvaluationCriteria(long companyId) {
		return PrefsPropsUtil.getInteger(companyId, P2PPropsKeys.NUM_EVALUATION_CRITERIA, P2PPropsValues.NUM_EVALUATION_CRITERIA);
	}
}
