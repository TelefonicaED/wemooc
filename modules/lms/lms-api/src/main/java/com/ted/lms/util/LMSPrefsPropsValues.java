package com.ted.lms.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.ted.lms.constants.LMSPropsKeys;
import com.ted.lms.constants.LMSPropsValues;

public class LMSPrefsPropsValues {
	
	public static boolean getCourseCommentsEnabled(long companyId) {
		return PrefsPropsUtil.getBoolean(companyId, LMSPropsKeys.COURSE_COMMENTS_ENABLED, LMSPropsValues.COURSE_COMMENTS_ENABLED);
	}
	
	public static boolean getModuleCommentsEnabled(long companyId) {
		return PrefsPropsUtil.getBoolean(companyId, LMSPropsKeys.MODULE_COMMENTS_ENABLED, LMSPropsValues.MODULE_COMMENTS_ENABLED);
	}
	
	public static boolean getCourseAddTeacherRoleToCreator(long companyId) {
		return PrefsPropsUtil.getBoolean(companyId, LMSPropsKeys.COURSE_ADD_TEACHER_ROLE_TO_CREATOR, LMSPropsValues.COURSE_ADD_TEACHER_ROLE_TO_CREATOR);
	}
	
	public static boolean getCourseAddEditorRoleToCreator(long companyId) {
		return PrefsPropsUtil.getBoolean(companyId, LMSPropsKeys.COURSE_ADD_EDITOR_ROLE_TO_CREATOR, LMSPropsValues.COURSE_ADD_EDITOR_ROLE_TO_CREATOR);
	}
	
	public static long getLMSPrefsTeacherRole(long companyId) {
		return PrefsPropsUtil.getLong(companyId, LMSPropsKeys.LMS_PREFS_TEACHER_ROLE, LMSPropsValues.LMS_PREFS_TEACHER_ROLE);
	}
	
	public static long getLMSPrefsEditorRole(long companyId) {
		return PrefsPropsUtil.getLong(companyId, LMSPropsKeys.LMS_PREFS_EDITOR_ROLE, LMSPropsValues.LMS_PREFS_EDITOR_ROLE);
	}
	
	public static long getCourseImageMaxSize(long companyId) {
		return PrefsPropsUtil.getLong(companyId, LMSPropsKeys.COURSE_IMAGE_MAX_SIZE, LMSPropsValues.COURSE_IMAGE_MAX_SIZE);
	}
	
	public static String[] getCourseImageExtensions(long companyId) {
		String[] defaultValues = LMSPropsValues.COURSE_IMAGE_EXTENSIONS;
		if(Validator.isNull(defaultValues)) {
			defaultValues = StringUtil.split(".gif,.jpeg,.jpg,.png", StringPool.COMMA);
		}
		return PrefsPropsUtil.getStringArray(companyId, LMSPropsKeys.COURSE_IMAGE_EXTENSIONS, StringPool.COMMA, defaultValues);
	}
	
	public static long getModuleImageMaxSize(long companyId) {
		return PrefsPropsUtil.getLong(companyId, LMSPropsKeys.MODULE_IMAGE_MAX_SIZE, LMSPropsValues.MODULE_IMAGE_MAX_SIZE);
	}
	
	public static String[] getModuleImageExtensions(long companyId) {
		String[] defaultValues = LMSPropsValues.MODULE_IMAGE_EXTENSIONS;
		if(Validator.isNull(defaultValues)) {
			defaultValues = StringUtil.split(".gif,.jpeg,.jpg,.png", StringPool.COMMA);
		}
		return PrefsPropsUtil.getStringArray(companyId, LMSPropsKeys.COURSE_IMAGE_EXTENSIONS, StringPool.COMMA, defaultValues);
	}
	
	public static boolean getLearningActivityType(long companyId, long type) {
		return PrefsPropsUtil.getBoolean(companyId, LMSPropsKeys.LEARNING_ACTIVITY_TYPE + StringPool.PERIOD + type, GetterUtil.getBoolean(PropsUtil.get(LMSPropsKeys.LEARNING_ACTIVITY_TYPE + "." + type)));
	}
	
	public static String[] getPrerequisitesOfModule(long companyId){
		return PrefsPropsUtil.getStringArray(companyId, LMSPropsKeys.PREREQUISITES_MODULE, StringPool.COMMA, LMSPropsValues.PREREQUISITES_MODULE);
	}
}