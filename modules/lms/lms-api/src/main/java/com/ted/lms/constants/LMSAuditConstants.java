package com.ted.lms.constants;

import com.ted.audit.api.contants.AuditConstants;

public class LMSAuditConstants implements AuditConstants{

	/**
	 * Las acciones sobre LMS van a ir del 10000 al 20000
	 */
	
	public static final int COURSE_VIEW = 10000;
	
	public static final int MODULE_VIEW = 11000;
	
	public static final int LEARNING_ACTIVITY_VIEW = 12000;
	
	public static final int LEARNING_ACTIVITY_RESULT_UPDATE = 13000;
	
	public static final int LEARNING_ACTIVITY_TRY_ADD = 14000;
	public static final int LEARNING_ACTIVITY_TRY_UPDATE = 14001;
	public static final int LEARNING_ACTIVITY_TRY_FINISH = 14002;
}
