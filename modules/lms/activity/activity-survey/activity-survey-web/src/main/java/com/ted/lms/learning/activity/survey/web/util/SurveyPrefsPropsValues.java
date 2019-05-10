package com.ted.lms.learning.activity.survey.web.util;

import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.ted.lms.learning.activity.survey.web.constants.SurveyPropsKeys;
import com.ted.lms.learning.activity.survey.web.constants.SurveyPropsValues;

public class SurveyPrefsPropsValues {
	public static String getQuestionsAllowed(long companyId) {
		return PrefsPropsUtil.getString(companyId, SurveyPropsKeys.QUESTIONS_ALLOWED, SurveyPropsValues.QUESTIONS_ALLOWED);
	}
}
