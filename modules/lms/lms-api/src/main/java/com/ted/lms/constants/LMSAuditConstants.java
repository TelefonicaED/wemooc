package com.ted.lms.constants;

import com.ted.audit.api.contants.AuditConstants;

public class LMSAuditConstants implements AuditConstants{

	/**
	 * Las acciones sobre LMS van a ir del 10000 al 20000
	 */
	
	public static final int COURSE_VIEW = 10000;
	public static final int COURSE_ADD = 10001;
	public static final int COURSE_UPDATE = 10002;
	public static final int COURSE_REMOVE = 10003;
	public static final int COURSE_REGISTER = 10004;
	public static final int COURSE_UNREGISTER = 10005;
	public static final int COURSE_UPDATE_STATUS = 10006;
	public static final int COURSE_TO_TRASH = 10007;
	public static final int COURSE_RESTORE_FROM_TRASH = 10008;
	public static final int COURSE_IMPORT = 10009;
	
	public static final int COURSE_RESULT_ADD = 11000;
	public static final int COURSE_RESULT_UPDATE = 11001;
	public static final int COURSE_RESULT_REMOVE = 11002;
	public static final int COURSE_RESULT_START = 11003;
	public static final int COURSE_RESULT_PASSED = 11004;
	public static final int COURSE_RESULT_FAILED = 11005;
	
	public static final int MODULE_VIEW = 12000;
	public static final int MODULE_ADD = 12001;
	public static final int MODULE_UPDATE = 12002;
	public static final int MODULE_REMOVE = 12003;
	public static final int MODULE_UPDATE_STATUS = 12004;
	public static final int MODULE_TO_TRASH = 12005;
	public static final int MODULE_RESTORE_FROM_TRASH = 12006;
	
	public static final int MODULE_RESULT_ADD = 13000;
	public static final int MODULE_RESULT_UPDATE = 13001;
	public static final int MODULE_RESULT_REMOVE = 13002;
	
	public static final int LEARNING_ACTIVITY_VIEW = 14000;
	public static final int LEARNING_ACTIVITY_ADD = 14001;
	public static final int LEARNING_ACTIVITY_UPDATE = 14002;
	public static final int LEARNING_ACTIVITY_REMOVE = 14003;
	public static final int LEARNING_ACTIVITY_UPDATE_STATUS = 14004;
	public static final int LEARNING_ACTIVITY_TO_TRASH = 14005;
	public static final int LEARNING_ACTIVITY_RESTORE_FROM_TRASH = 14006;
	
	public static final int LEARNING_ACTIVITY_RESULT_ADD = 15000;
	public static final int LEARNING_ACTIVITY_RESULT_UPDATE = 15001;
	public static final int LEARNING_ACTIVITY_RESULT_REMOVE = 15002;
	
	public static final int LEARNING_ACTIVITY_TRY_ADD = 16000;
	public static final int LEARNING_ACTIVITY_TRY_UPDATE = 16001;
	public static final int LEARNING_ACTIVITY_TRY_REMOVE = 16002;
	public static final int LEARNING_ACTIVITY_TRY_FINISH = 16003;
}
