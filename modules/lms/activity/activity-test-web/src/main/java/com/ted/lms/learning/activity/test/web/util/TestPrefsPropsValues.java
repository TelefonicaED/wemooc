package com.ted.lms.learning.activity.test.web.util;

import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.ted.lms.learning.activity.test.web.constants.TestPropsKeys;
import com.ted.lms.learning.activity.test.web.constants.TestPropsValues;

public class TestPrefsPropsValues {
	public static int getQuestionPerPage(long companyId) {
		return PrefsPropsUtil.getInteger(companyId, TestPropsKeys.QUESTION_PER_PAGE, TestPropsValues.QUESTION_PER_PAGE);
	}
}
