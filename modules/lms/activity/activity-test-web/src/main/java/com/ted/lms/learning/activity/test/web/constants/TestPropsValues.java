package com.ted.lms.learning.activity.test.web.constants;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsUtil;

public class TestPropsValues {
	public static final int QUESTION_PER_PAGE = GetterUtil.getInteger(PropsUtil.get(TestPropsKeys.QUESTION_PER_PAGE), TestConstants.DEFAULT_QUESTION_PER_PAGE);
	public static final String QUESTIONS_ALLOWED = GetterUtil.getString(PropsUtil.get(TestPropsKeys.QUESTIONS_ALLOWED), TestConstants.DEFAULT_QUESTIONS_ALLOWED);
}
