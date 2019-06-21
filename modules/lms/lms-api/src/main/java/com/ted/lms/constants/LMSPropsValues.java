package com.ted.lms.constants;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsUtil;

public class LMSPropsValues {
	public static final boolean COURSE_COMMENTS_ENABLED = GetterUtil.getBoolean(PropsUtil.get(LMSPropsKeys.COURSE_COMMENTS_ENABLED));
	public static final long COURSE_IMAGE_MAX_SIZE = GetterUtil.getLong(PropsUtil.get(LMSPropsKeys.COURSE_IMAGE_MAX_SIZE), LMSConstants.DEFAULT_IMAGE_MAX_SIZE);
	public static final String[] COURSE_IMAGE_EXTENSIONS = PropsUtil.getArray(LMSPropsKeys.COURSE_IMAGE_EXTENSIONS);
	
	public static final boolean MODULE_COMMENTS_ENABLED = GetterUtil.getBoolean(PropsUtil.get(LMSPropsKeys.MODULE_COMMENTS_ENABLED));
	public static final long MODULE_IMAGE_MAX_SIZE = GetterUtil.getLong(PropsUtil.get(LMSPropsKeys.MODULE_IMAGE_MAX_SIZE), LMSConstants.DEFAULT_IMAGE_MAX_SIZE);
	public static final String[] MODULE_IMAGE_EXTENSIONS = PropsUtil.getArray(LMSPropsKeys.MODULE_IMAGE_EXTENSIONS);
	
	public static final boolean LEARNING_ACTIVITY_COMMENTS_ENABLED = GetterUtil.getBoolean(PropsUtil.get(LMSPropsKeys.LEARNING_ACTIVITY_COMMENTS_ENABLED));
	public static final boolean LEARNING_ACTIVITY_CHANGE_VISIBILITY = GetterUtil.getBoolean(PropsUtil.get(LMSPropsKeys.LEARNING_ACTIVITY_CHANGE_VISIBILITY), true);
	public static final String[] LEARNING_ACTIVITY_DOCUMENTS_EXTENSIONS = PropsUtil.getArray(LMSPropsKeys.LEARNING_ACTIVITY_DOCUMENTS_EXTENSIONS);
	public static final long LEARNING_ACTIVITY_DOCUMENTS_MAX_SIZE = GetterUtil.getLong(LMSPropsKeys.LEARNING_ACTIVITY_DOCUMENTS_MAX_SIZE, LMSConstants.DEFAULT_IMAGE_MAX_SIZE);
	
	public static final boolean USERS_FIRST_LAST_NAME = GetterUtil.getBoolean(PropsUtil.get(LMSPropsKeys.USERS_FIRST_LAST_NAME));
	
	public static final String[] COURSE_TYPE_ICON_EXTENSIONS = PropsUtil.getArray(LMSPropsKeys.COURSE_TYPE_ICON_EXTENSIONS);
	public static final long COURSE_TYPE_ICON_MAX_SIZE = GetterUtil.getLong(PropsUtil.get(LMSPropsKeys.COURSE_TYPE_ICON_MAX_SIZE), LMSConstants.DEFAULT_IMAGE_MAX_SIZE);
}
