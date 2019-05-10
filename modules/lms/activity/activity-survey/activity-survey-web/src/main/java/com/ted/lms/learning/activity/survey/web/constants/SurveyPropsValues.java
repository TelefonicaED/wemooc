package com.ted.lms.learning.activity.survey.web.constants;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsUtil;

public class SurveyPropsValues {
	public static final String QUESTIONS_ALLOWED = GetterUtil.getString(PropsUtil.get(SurveyPropsKeys.QUESTIONS_ALLOWED), SurveyConstants.DEFAULT_QUESTIONS_ALLOWED);
}
