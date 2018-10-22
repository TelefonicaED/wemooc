package com.ted.lms.learning.activity.p2p.web.constants;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsUtil;

public class P2PPropsValues {
	public static final int NUM_EVALUATION_CRITERIA = GetterUtil.getInteger(PropsUtil.get(P2PPropsKeys.NUM_EVALUATION_CRITERIA), P2PConstants.DEFAULT_NUM_EVALUATION_CRITERIA);
}
