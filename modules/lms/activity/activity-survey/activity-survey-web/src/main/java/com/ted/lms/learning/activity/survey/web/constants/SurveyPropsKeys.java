package com.ted.lms.learning.activity.survey.web.constants;

import com.liferay.portal.kernel.util.PropsKeys;

/**
 * Esta clase define las propiedades que tendrá el LMS configuradas en el prefsprops
 * @author Virginia Martín Agudo
 *
 */
public class SurveyPropsKeys implements PropsKeys{
	public static final String QUESTIONS_ALLOWED = "lms.questions.allowed.for." + SurveyConstants.TYPE;
}
