package com.ted.lms.learning.activity;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.ted.lms.learning.activity.question.constants.QuestionConstants;
import com.ted.lms.learning.activity.question.service.QuestionLocalService;
import com.ted.lms.model.BaseLearningActivityType;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.service.LearningActivityResultLocalService;
import javax.portlet.ActionRequest;

public abstract class QuestionsLearningActivityType extends BaseLearningActivityType {
	protected QuestionLocalService questionLocalService;
	private boolean showCorrectAnswer;
	private boolean showFeedback;
	private boolean showCorrectAnswerOnlyOnFinalTry;
	
	public QuestionsLearningActivityType(LearningActivity activity, LearningActivityResultLocalService learningActivityResultLocalService, 
			QuestionLocalService questionLocalService) {
		super(activity, learningActivityResultLocalService);
		this.questionLocalService = questionLocalService;
		
		JSONObject extraContent = activity.getExtraContentJSON();
		
		if(extraContent != null) {
			JSONObject test = extraContent.getJSONObject(QuestionConstants.JSON_QUESTIONS);
			if(test != null) {
				showCorrectAnswer = test.getBoolean(QuestionConstants.JSON_SHOW_CORRECT_ANSWER, QuestionConstants.DEFAULT_SHOW_CORRECT_ANSWER);
				showFeedback = test.getBoolean(QuestionConstants.JSON_SHOW_FEEDBACK, QuestionConstants.DEFAULT_SHOW_FEEDBACK);
				showCorrectAnswerOnlyOnFinalTry = test.getBoolean(QuestionConstants.JSON_SHOW_CORRECT_ANSWER_ONLY_ON_FINAL_TRY, QuestionConstants.DEFAULT_SHOW_CORRECT_ANSWER_ONLY_ON_FINAL_TRY);
			}else {
				initializateActivity();
			}
		}else {
			initializateActivity();
		}
		
		activity.setExtraContent(extraContent.toJSONString());
	}
	
	@Override
	public void setExtraContent(ActionRequest actionRequest) throws PortalException {
		
		JSONObject extraContent = activity.getExtraContentJSON();
		JSONObject questionsContent = extraContent.getJSONObject(QuestionConstants.JSON_QUESTIONS);
		
		if(Validator.isNull(questionsContent)) {
			questionsContent = JSONFactoryUtil.createJSONObject();
			extraContent.put(QuestionConstants.JSON_QUESTIONS, questionsContent);
		}
		
		showCorrectAnswer = ParamUtil.getBoolean(actionRequest, "showCorrectAnswer", QuestionConstants.DEFAULT_SHOW_CORRECT_ANSWER);
		showFeedback = ParamUtil.getBoolean(actionRequest, "showFeedback", QuestionConstants.DEFAULT_SHOW_FEEDBACK);
		showCorrectAnswerOnlyOnFinalTry = ParamUtil.getBoolean(actionRequest, "showCorrectAnswerOnlyOnFinalTry", QuestionConstants.DEFAULT_SHOW_CORRECT_ANSWER_ONLY_ON_FINAL_TRY);

		
		questionsContent.put(QuestionConstants.JSON_SHOW_CORRECT_ANSWER, showCorrectAnswer);
		questionsContent.put(QuestionConstants.JSON_SHOW_FEEDBACK, showFeedback);
		questionsContent.put(QuestionConstants.JSON_SHOW_CORRECT_ANSWER_ONLY_ON_FINAL_TRY, showCorrectAnswerOnlyOnFinalTry);
		
		activity.setExtraContent(extraContent.toJSONString());
	}
	
	
	public boolean getShowCorrectAnswer() {
		return showCorrectAnswer;
	}
	
	public boolean getShowFeedback() {
		return showFeedback;
	}
	
	public boolean getShowCorrectAnswerOnlyOnFinalTry() {
		return showCorrectAnswerOnlyOnFinalTry;
	}
	
	private void initializateActivity() {
		showCorrectAnswer = QuestionConstants.DEFAULT_SHOW_CORRECT_ANSWER;
		showFeedback = QuestionConstants.DEFAULT_SHOW_FEEDBACK;
		showCorrectAnswerOnlyOnFinalTry = QuestionConstants.DEFAULT_SHOW_CORRECT_ANSWER_ONLY_ON_FINAL_TRY;
	}	
}
