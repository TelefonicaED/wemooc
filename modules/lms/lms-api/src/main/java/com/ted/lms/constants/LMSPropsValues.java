package com.ted.lms.constants;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsUtil;

public class LMSPropsValues {
	public static final boolean COURSE_COMMENTS_ENABLED = GetterUtil.getBoolean(PropsUtil.get(LMSPropsKeys.COURSE_COMMENTS_ENABLED));
	public static final boolean COURSE_ADD_TEACHER_ROLE_TO_CREATOR = GetterUtil.getBoolean(PropsUtil.get(LMSPropsKeys.COURSE_ADD_TEACHER_ROLE_TO_CREATOR));
	public static final boolean COURSE_ADD_EDITOR_ROLE_TO_CREATOR = GetterUtil.getBoolean(PropsUtil.get(LMSPropsKeys.COURSE_ADD_EDITOR_ROLE_TO_CREATOR), true);
	public static final long COURSE_IMAGE_MAX_SIZE = GetterUtil.getLong(PropsUtil.get(LMSPropsKeys.COURSE_IMAGE_MAX_SIZE), 5242880);
	public static final String[] COURSE_IMAGE_EXTENSIONS = PropsUtil.getArray(LMSPropsKeys.COURSE_IMAGE_EXTENSIONS);
	
	public static final long LMS_PREFS_TEACHER_ROLE =  GetterUtil.getLong(PropsUtil.get(LMSPropsKeys.LMS_PREFS_TEACHER_ROLE));
	public static final long LMS_PREFS_EDITOR_ROLE =  GetterUtil.getLong(PropsUtil.get(LMSPropsKeys.LMS_PREFS_EDITOR_ROLE));
	
	public static final boolean MODULE_COMMENTS_ENABLED = GetterUtil.getBoolean(PropsUtil.get(LMSPropsKeys.MODULE_COMMENTS_ENABLED));
	public static final long MODULE_IMAGE_MAX_SIZE = GetterUtil.getLong(PropsUtil.get(LMSPropsKeys.MODULE_IMAGE_MAX_SIZE), 5242880);
	public static final String[] MODULE_IMAGE_EXTENSIONS = PropsUtil.getArray(LMSPropsKeys.MODULE_IMAGE_EXTENSIONS);
	
	public static final boolean LEARNING_ACTIVITY_COMMENTS_ENABLED = GetterUtil.getBoolean(PropsUtil.get(LMSPropsKeys.LEARNING_ACTIVITY_COMMENTS_ENABLED));
	public static final boolean LEARNING_ACTIVITY_CHANGE_VISIBILITY = GetterUtil.getBoolean(PropsUtil.get(LMSPropsKeys.LEARNING_ACTIVITY_CHANGE_VISIBILITY), true);
	
	public static final String[] PREREQUISITES_MODULE = PropsUtil.getArray(LMSPropsKeys.PREREQUISITES_MODULE);
	public static final String[] PREREQUISITES_LEARNING_ACTIVITY = PropsUtil.getArray(LMSPropsKeys.PREREQUISITES_LEARNING_ACTIVITY);
	
	public static final boolean USERS_FIRST_LAST_NAME = GetterUtil.getBoolean(PropsUtil.get(LMSPropsKeys.USERS_FIRST_LAST_NAME));
}
