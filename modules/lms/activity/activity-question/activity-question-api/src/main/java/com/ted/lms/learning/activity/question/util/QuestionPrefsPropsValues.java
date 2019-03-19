package com.ted.lms.learning.activity.question.util;

import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.ted.lms.learning.activity.question.constants.QuestionPropsKeys;
import com.ted.lms.learning.activity.question.constants.QuestionPropsValues;

public class QuestionPrefsPropsValues {
	public static boolean getOptionsEditFormat(long companyId) {
		return PrefsPropsUtil.getBoolean(companyId, QuestionPropsKeys.OPTIONS_EDIT_FORMAT, QuestionPropsValues.OPTIONS_EDIT_FORMAT);
	}
	
	public static int getMultioptionsEditFormat(long companyId) {
		return PrefsPropsUtil.getInteger(companyId, QuestionPropsKeys.MULTIOPTIONS_MAX_NUMBER_CHECKS, QuestionPropsValues.MULTIOPTIONS_MAX_NUMBER_CHECKS);
	}
}
