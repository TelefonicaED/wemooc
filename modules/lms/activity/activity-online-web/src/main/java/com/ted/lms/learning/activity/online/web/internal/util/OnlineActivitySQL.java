package com.ted.lms.learning.activity.online.web.internal.util;

public class OnlineActivitySQL {
	public static final String ACTIVITY_TRY_SQL = "WHERE (EXISTS (SELECT 1 FROM lms_learningactivitytry " +
			"WHERE User_.userId = lms_learningactivitytry.userId AND lms_learningactivitytry.actId = ? ))"; 

	public static final String ACTIVITY_RESULT_PASSED_SQL = "WHERE (EXISTS (SELECT 1 FROM lms_learningactivityresult " +
			"WHERE User_.userId = lms_learningactivityresult.userId " +
			" AND lms_learningactivityresult.passed > 0 AND lms_learningactivityresult.actId = ? ))"; 

	public static final String ACTIVITY_RESULT_FAIL_SQL = "WHERE (EXISTS (SELECT 1 FROM lms_learningactivityresult " +
			"WHERE User_.userId = lms_learningactivityresult.userId " +
			" AND lms_learningactivityresult.passed = 0 AND lms_learningactivityresult.actId = ? AND lms_learningactivityresult.endDate IS NOT NULL ))"; 

	public static final String ACTIVITY_RESULT_NO_CALIFICATION_SQL = "WHERE (NOT EXISTS (SELECT 1 FROM lms_learningactivityresult " +
			"WHERE User_.userId = lms_learningactivityresult.userId AND lms_learningactivityresult.actId = ? AND lms_learningactivityresult.endDate IS NOT NULL ))"; 

}
