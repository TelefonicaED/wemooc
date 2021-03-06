package com.ted.lms.constants;

/**
 * Esta clase define las constantes generales del LMS
 * @author Virginia Martín Agudo
 *
 */
public class LMSConstants {
	public static final String RESOURCE_NAME = "com.ted.lms";
	
	public static final String COURSE_MODEL_CLASS_NAME = "com.ted.lms.model.Course";
	public static final String MODULE_MODEL_CLASS_NAME = "com.ted.lms.model.Module";
	public static final String LEARNING_ACTIVITY_MODEL_CLASS_NAME = "com.ted.lms.model.LearningActivity";
	
	public static final String SERVICE_NAME = "com.ted.lms";
	
	public static final String CHANGE_VISIBILITY = "change_visibility";
	
	public static final String[] DEFAULT_LEARNING_ACTIVITY_DOCUMENTS_EXTENSIONS = {".gif",".jpeg",".jpg",".png",".txt",".doc",".docx"};
	
	public static final long DEFAULT_IMAGE_MAX_SIZE = 5242880;
	
	public static final String TEMP_FOLDER_NAME = "com.ted.lms";
	
	public static final String[] COLUMNS_IMPORT_EXPORT_ASSIGN_MEMBERS = {"full-name"};
	public static final String[] COLUMNS_IMPORT_EXPORT_ASSIGN_MEMBERS_STUDENTS = {"start-date", "end-date"};
	public static final String[] COLUMNS_IMPORT_EDITIONS = {"edition","friendly-url","registration-start-date", "registration-end-date", "execution-start-date", "execution-end-date"};
}
