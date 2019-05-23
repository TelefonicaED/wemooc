package com.ted.lms.constants;

import com.liferay.portal.kernel.util.PropsKeys;

/**
 * Esta clase define las propiedades que tendrá el LMS configuradas en el prefsprops
 * @author Virginia Martín Agudo
 *
 */
public class LMSPropsKeys implements PropsKeys{
	public static final String COURSE_COMMENTS_ENABLED = "course.comments.enabled";
	public static final String COURSE_ADD_TEACHER_ROLE_TO_CREATOR = "course.add.teacher-role";
	public static final String COURSE_ADD_EDITOR_ROLE_TO_CREATOR = "course.add.editor-role";
	
	public static final String LMS_PREFS_TEACHER_ROLE = "lms.prefs.role.teacher";
	public static final String LMS_PREFS_EDITOR_ROLE = "lms.prefs.role.editor";
	
	public static final String LEARNING_ACTIVITY_TYPE = "learning.activity.type";
	public static final String LEARNING_ACTIVITY_CHANGE_VISIBILITY = "learning.activity.change.visibility";
	public static final String LEARNING_ACTIVITY_DOCUMENTS_EXTENSIONS = "learning.activity.documetns.extensions";
	public static final String LEARNING_ACTIVITY_DOCUMENTS_MAX_SIZE = "learning.activity.documents.max.size";
	
	public static final String COURSE_IMAGE_EXTENSIONS = "course.image.extensions";
	public static final String COURSE_IMAGE_MAX_SIZE = "course.image.max-size";
	public static final String MODULE_IMAGE_EXTENSIONS = "module.image.extensions";
	public static final String MODULE_IMAGE_MAX_SIZE = "module.image.max-size";
	
	public static final String MODULE_COMMENTS_ENABLED = "module.comments.enabled";
	public static final String LEARNING_ACTIVITY_COMMENTS_ENABLED = "learning.activity.comments.enabled";
	
	public static final String PREREQUISITES_MODULE = "prerequisites.module";
	public static final String PREREQUISITES_LEARNING_ACTIVITY = "prerequisites.learning-activity";
	
	public static final String USERS_FIRST_LAST_NAME = "users.first.last.name";
}
