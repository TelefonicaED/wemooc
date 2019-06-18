package com.ted.lms.learning.activity.resource.external.web.constants;

import com.ted.lms.model.BaseLearningActivityTypeFactory;

public class ResourceExternalConstants {
	public static final String JSON_RESOURCE_EXTERNAL = "multimediaentry";
	public static final String JSON_VIDEO = "video";
	public static final String JSON_VIDEO_CONTROL = "videoControl";
	public static final String JSON_ADDITIONAL_FILES = "additionalFiles";
	public static final String JSON_QUESTION_POSITIONS = "questionPositions";
	public static final String JSON_CORRECT_MODE = "correctMode";
	public static final String JSON_FINAL_FEEDBACK = "finalFeedback";
	public static final String JSON_QUESTION_FEEDBACK = "questionFeedback";
	
	public static final int DEFAULT_MAX_FILES = 5;
	public final static int CORRECT_VIDEO = 1;
	public final static int CORRECT_QUESTIONS = 2;
	
	public static final long TYPE = 2;
	public static final double DEFAULT_SCORE = BaseLearningActivityTypeFactory.DEFAULT_SCORE;
	public static final boolean DEFAULT_SHOW_CONTROLS = true;
	public static final int DEFAULT_CORRECT_MODE = CORRECT_VIDEO;
	public static final boolean DEFAULT_FINAL_FEEDBACK = false;
	public static final boolean DEFAULT_QUESTION_FEEDBACK = false;
	
	public static final String DEFAULT_QUESTIONS_ALLOWED = "0,1,2,3,4,5";
	
}
