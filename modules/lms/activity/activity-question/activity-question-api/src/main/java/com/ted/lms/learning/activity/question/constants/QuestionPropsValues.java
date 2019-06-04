package com.ted.lms.learning.activity.question.constants;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsUtil;

public class QuestionPropsValues {
	public static final boolean OPTIONS_EDIT_FORMAT = GetterUtil.getBoolean(PropsUtil.get(QuestionPropsKeys.OPTIONS_EDIT_FORMAT), true);
	public static final int MULTIOPTIONS_MAX_NUMBER_CHECKS = GetterUtil.getInteger(PropsUtil.get(QuestionPropsKeys.MULTIOPTIONS_MAX_NUMBER_CHECKS), QuestionConstants.DEFAULT_MULTIOPTIONS_MAX_NUMBER_CHECKS);
}
