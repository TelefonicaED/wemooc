package com.ted.lms.learning.activity.question.constants;

public class QuestionConstants {

	public static final String JSON_QUESTIONS = "questions";
	
	public static final String JSON_SHOW_CORRECT_ANSWER = "showCorrectAnswer";
	public static final String JSON_SHOW_FEEDBACK = "showFeedback";
	public static final String JSON_SHOW_CORRECT_ANSWER_ONLY_ON_FINAL_TRY = "showCorrectAnswerOnlyOnFinalTry";
	
	public static final boolean DEFAULT_SHOW_CORRECT_ANSWER = false;
	public static final boolean DEFAULT_SHOW_FEEDBACK = true;
	public static final boolean DEFAULT_SHOW_CORRECT_ANSWER_ONLY_ON_FINAL_TRY = false;
	public static final int DEFAULT_MULTIOPTIONS_MAX_NUMBER_CHECKS = 0;
	
	public static final String SERVICE_NAME = "com.ted.lms.learning.activity.question";
	
	public static final int IMPORT_EXPORT_XML = 1;
	public static final int IMPORT_EXPORT_EXCEL = 2;
	public static final String IMPORT_JSON_RESULT = "result";
	public static final String IMPORT_JSON_QUESTION_IDS = "questionIds";
	public static final int IMPORT_JSON_RESULT_SUCCESS = 1;
	public static final int IMPORT_JSON_RESULT_ERROR = -1;
	public static final String IMPORT_JSON_ERRORS = "errors";
	
	public static final String TIMES_NEW_ROMAN = "Times New Roman";
	public static final int COLUMN_INDEX_QUESTION_TITLE = 0;
	public static final int COLUMN_INDEX_QUESTION_TYPE = 1;
	public static final int COLUMN_INDEX_QUESTION_PENALIZE = 2;
	public static final int COLUMN_INDEX_ANSWER_TITLE = 3;
	public static final int COLUMN_INDEX_ANSWER_IS_CORRECT = 4;
	public static final int COLUMN_INDEX_ANSWER_FEEDBACK_CORRECT = 5;
	public static final int COLUMN_INDEX_ANSWER_FEEDBACK_INCORRECT = 6;
	
}
