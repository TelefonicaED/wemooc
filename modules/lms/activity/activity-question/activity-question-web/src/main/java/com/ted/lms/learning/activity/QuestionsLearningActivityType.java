package com.ted.lms.learning.activity;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.ted.lms.learning.activity.question.constants.QuestionConstants;
import com.ted.lms.learning.activity.question.model.Question;
import com.ted.lms.learning.activity.question.model.QuestionTypeFactory;
import com.ted.lms.learning.activity.question.registry.QuestionTypeFactoryRegistryUtil;
import com.ted.lms.learning.activity.question.service.AnswerLocalService;
import com.ted.lms.learning.activity.question.service.QuestionLocalService;
import com.ted.lms.model.BaseLearningActivityType;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.service.LearningActivityResultLocalService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;

public abstract class QuestionsLearningActivityType extends BaseLearningActivityType {
	
	private boolean showCorrectAnswer;
	private boolean showFeedback;
	private boolean showCorrectAnswerOnlyOnFinalTry;
	private List<Question> questions;
	protected final QuestionLocalService questionLocalService;
	protected final AnswerLocalService answerLocalService;
	protected Map<Long, Long> questionsRelation;
	
	private static final Log log = LogFactoryUtil.getLog(QuestionsLearningActivityType.class);
	
	public QuestionsLearningActivityType(LearningActivity activity, LearningActivityResultLocalService learningActivityResultLocalService, 
			QuestionLocalService questionLocalService, AnswerLocalService answerLocalService) {
		super(activity, learningActivityResultLocalService);
		log.debug("questionstype questionLocalService: " + questionLocalService);
		this.questionLocalService = questionLocalService;
		this.answerLocalService = answerLocalService;
		
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
			activity.setExtraContent(extraContent.toJSONString());
		}else {
			initializateActivity();
		}
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
		
		//Ahora guardamos las preguntas
		questionLocalService.saveQuestions(actionRequest, activity.getActId());
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
	
	public List<Question> getQuestions(){
		if(questions == null) {
			questions = questionLocalService.getQuestions(activity.getActId());
		}
		return questions;
	}
	
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	
	public boolean hasFreeQuestions() {
		List<Question> questions = getQuestions();
		
		return hasFreeQuestions(questions);
	}
	
	@Override
	public void onDelete() throws PortalException {
		//Borramos las preguntas y respuestas
		questions = getQuestions();
		for(Question question: questions) {
			questionLocalService.deleteQuestion(question);
		}
	}
	
	public boolean hasFreeQuestions(List<Question> questions) {
		
		int i = 0;
		QuestionTypeFactory questionTypeFactory = null;
		HashMap<Long, QuestionTypeFactory> questionTypeFactories = new HashMap<>();

		boolean hasFreeQuestion = false;
		
		while(!hasFreeQuestion && i < questions.size()) {
			if(questionTypeFactories.containsKey(questions.get(i).getQuestionTypeId())) {
				questionTypeFactory = questionTypeFactories.get(questions.get(i).getQuestionTypeId());
			}else {
				questionTypeFactory = QuestionTypeFactoryRegistryUtil.getQuestionTypeFactoryByType(questions.get(i).getQuestionTypeId());
				questionTypeFactories.put(questions.get(i).getQuestionTypeId(), questionTypeFactory);
			}
			hasFreeQuestion = questionTypeFactory.isManualCorrection();
			i++;
		}
		
		return hasFreeQuestion;
	}
	
	private void initializateActivity() {
		showCorrectAnswer = QuestionConstants.DEFAULT_SHOW_CORRECT_ANSWER;
		showFeedback = QuestionConstants.DEFAULT_SHOW_FEEDBACK;
		showCorrectAnswerOnlyOnFinalTry = QuestionConstants.DEFAULT_SHOW_CORRECT_ANSWER_ONLY_ON_FINAL_TRY;
	}	
	

	
	@Override
	public void copyActivity(LearningActivity oldActivity, ServiceContext serviceContext) throws Exception {
		super.copyActivity(oldActivity, serviceContext);
		
		questionsRelation = new HashMap<Long, Long>();
		
		//Copiamos las preguntas y respuestas
		List<Question> oldQuestions = questionLocalService.getQuestions(oldActivity.getActId());
		Question newQuestion = null;
		for(Question oldQuestion: oldQuestions) {
			newQuestion = questionLocalService.copyQuestion(serviceContext.getUserId(), activity.getGroupId(), activity.getActId(), oldQuestion, serviceContext);
			questionsRelation.put(oldQuestion.getQuestionId(), newQuestion.getQuestionId());
		}
	}
}
