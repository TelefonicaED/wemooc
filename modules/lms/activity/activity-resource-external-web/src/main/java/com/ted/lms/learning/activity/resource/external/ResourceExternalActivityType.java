package com.ted.lms.learning.activity.resource.external;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.ted.lms.learning.activity.QuestionsLearningActivityType;
import com.ted.lms.learning.activity.question.model.Question;
import com.ted.lms.learning.activity.question.model.QuestionType;
import com.ted.lms.learning.activity.question.model.QuestionTypeFactory;
import com.ted.lms.learning.activity.question.registry.QuestionTypeFactoryRegistryUtil;
import com.ted.lms.learning.activity.question.service.QuestionLocalServiceUtil;
import com.ted.lms.learning.activity.resource.external.web.constants.ResourceExternalConstants;
import com.ted.lms.learning.activity.resource.external.web.util.ResourceExternalPrefsPropsValues;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.LearningActivityTry;
import java.util.Iterator;
import java.util.List;

import javax.portlet.ActionRequest;

public class ResourceExternalActivityType extends QuestionsLearningActivityType {
	
	private static final Log log = LogFactoryUtil.getLog(ResourceExternalActivityType.class);
	private String video;
	private boolean showControls;
	private int correctMode;
	private boolean finalFeedback;
	private boolean questionFeedback;
	private JSONObject questionPositions;

	public ResourceExternalActivityType(LearningActivity activity) {
		super(activity);
		
		JSONObject extraContent = activity.getExtraContentJSON();
		
		if(extraContent != null) {
			JSONObject resourceExternal = extraContent.getJSONObject(ResourceExternalConstants.JSON_RESOURCE_EXTERNAL);
			if(resourceExternal != null) {
				video = resourceExternal.getString(ResourceExternalConstants.JSON_VIDEO, null);
				showControls = resourceExternal.getBoolean(ResourceExternalConstants.JSON_VIDEO_CONTROL, ResourceExternalConstants.DEFAULT_SHOW_CONTROLS);
				correctMode = resourceExternal.getInt(ResourceExternalConstants.JSON_CORRECT_MODE, ResourceExternalConstants.DEFAULT_CORRECT_MODE);
				finalFeedback = resourceExternal.getBoolean(ResourceExternalConstants.JSON_FINAL_FEEDBACK, ResourceExternalConstants.DEFAULT_FINAL_FEEDBACK);
				questionFeedback = resourceExternal.getBoolean(ResourceExternalConstants.JSON_QUESTION_FEEDBACK, ResourceExternalConstants.DEFAULT_QUESTION_FEEDBACK);
				questionPositions = resourceExternal.getJSONObject(ResourceExternalConstants.JSON_QUESTION_POSITIONS);
			}else {
				initializateActivity();
			}
		}else {
			initializateActivity();
		}
	}
	
	@Override
	public String getClassName() {
		return ResourceExternalActivityType.class.getName();
	}
	
	@Override
	public void setExtraContent(ActionRequest actionRequest) throws PortalException {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		super.setExtraContent(actionRequest);
		
		video = ParamUtil.getString(actionRequest,"video");
		
		int maxfile = ResourceExternalPrefsPropsValues.getMaxFiles(themeDisplay.getCompanyId());
		
		JSONObject extraContent = activity.getExtraContentJSON();
		
		JSONObject resourceExternalContent = extraContent.getJSONObject(ResourceExternalConstants.JSON_RESOURCE_EXTERNAL);
		
		if(Validator.isNull(resourceExternalContent)) {
			resourceExternalContent = JSONFactoryUtil.createJSONObject();
			extraContent.put(ResourceExternalConstants.JSON_RESOURCE_EXTERNAL, resourceExternalContent);
		}
		
		if(Validator.isNotNull(video)) {
			resourceExternalContent.put(ResourceExternalConstants.JSON_VIDEO, video);
		}else if(resourceExternalContent.has(ResourceExternalConstants.JSON_VIDEO)){
			resourceExternalContent.remove(ResourceExternalConstants.JSON_VIDEO);
		}

		resourceExternalContent.put(ResourceExternalConstants.JSON_VIDEO_CONTROL, ParamUtil.getBoolean(actionRequest,"videoControl", false));
		
		JSONArray additionalFiles = JSONFactoryUtil.createJSONArray();
		resourceExternalContent.put(ResourceExternalConstants.JSON_ADDITIONAL_FILES, additionalFiles);
		
		long additionalFileEntryId = 0;
		for(int i=0; i <= maxfile; i++){
			additionalFileEntryId = ParamUtil.getLong(actionRequest, "additionalFileEntryId"+i, 0);
			if(additionalFileEntryId > 0) {
				additionalFiles.put(additionalFileEntryId);
			}
		}
		
		//Guardamos los segundos de las preguntas
		List<Question> listQuestions = QuestionLocalServiceUtil.getQuestions(activity.getActId());
		
		JSONObject questionPositions = JSONFactoryUtil.createJSONObject();
		resourceExternalContent.put(ResourceExternalConstants.JSON_QUESTION_POSITIONS, questionPositions);
		
		if(Validator.isNotNull(listQuestions)){
			int second = 0;
			for(Question question: listQuestions){
				second = ParamUtil.getInteger(actionRequest, "second_" + question.getQuestionId(), 0);
				
				if(second > 0){
					questionPositions.put(ResourceExternalConstants.JSON_SECOND + question.getQuestionId(), second);
				}
			}
		}
		
		//CORRECT MODE
		resourceExternalContent.put(ResourceExternalConstants.JSON_CORRECT_MODE, ParamUtil.getInteger(actionRequest, "correctMode", ResourceExternalConstants.CORRECT_VIDEO));
		
		//FINAL FEEDBACK
		resourceExternalContent.put(ResourceExternalConstants.JSON_FINAL_FEEDBACK, ParamUtil.getBoolean(actionRequest, "finalFeedback", false));
					
		//QUESTION FEEDBACK
		resourceExternalContent.put(ResourceExternalConstants.JSON_QUESTION_FEEDBACK, ParamUtil.getBoolean(actionRequest, "questionFeedback", false));
		
		activity.setExtraContent(extraContent.toJSONString());
	}
	
	@Override
	public double calculateResult(LearningActivityTry learningActivityTry) {
		double score = 0;
		
		JSONObject extraContent = activity.getExtraContentJSON();
		JSONObject resourceExternalContent = extraContent.getJSONObject(ResourceExternalConstants.JSON_RESOURCE_EXTERNAL);
		
		if(Validator.isNotNull(resourceExternalContent)) {
			//Comprobamos el tipo de corrección
			int correctMode = resourceExternalContent.getInt(ResourceExternalConstants.JSON_CORRECT_MODE, ResourceExternalConstants.CORRECT_VIDEO);
			
			//Comprobamos si es necesario ver un porcetanje del video para aprobar
			boolean isDefaultScore = (activity.getPassPuntuation() == ResourceExternalConstants.DEFAULT_SCORE);
			log.debug("isDefaultScore: " + isDefaultScore);
			
			if(correctMode == ResourceExternalConstants.CORRECT_QUESTIONS){
				if(Validator.isNotNull(resourceExternalContent.getString(ResourceExternalConstants.JSON_VIDEO))){
					
					List<Question> listQuestions = QuestionLocalServiceUtil.getQuestions(activity.getActId());
					JSONObject questionPositions = resourceExternalContent.getJSONObject(ResourceExternalConstants.JSON_QUESTION_POSITIONS);
					
					if(Validator.isNotNull(listQuestions) && Validator.isNotNull(questionPositions)){
						log.debug("tiene preguntas: " + listQuestions.size());
						Element element = null;
						int correctAnswers = 0;
						int penalizedAnswers = 0;
						int numQuestions = 0;
						
						Iterator<Element> questionIterator = null;
						boolean finded = false;
						
						long correct = 0;
						int questionSecond = 0;
						QuestionTypeFactory questionTypeFactory = null;
						QuestionType questionType = null;
						
						try {
							Document documentTry = SAXReaderUtil.read(learningActivityTry.getTryResultData());
						
							Element rootTry = documentTry.getRootElement();
							
							score = rootTry.element("score") != null ? Long.parseLong(rootTry.element("score").getText()) :  learningActivityTry.getResult();
							log.debug("score del try: " + score);
							
							for(Question question: listQuestions){
								try{
									questionSecond = questionPositions.getInt(ResourceExternalConstants.JSON_SECOND + question.getQuestionId(), 0);
		
									if(questionSecond > 0){
										log.debug("esta pregunta tiene segundo: " + questionSecond);
										numQuestions++;
										
										element = null;
										questionIterator = rootTry != null ? rootTry.elementIterator("question") : null;
										
										if(questionIterator != null){
											
											finded = false;
											while(!finded && questionIterator.hasNext()){
												element = questionIterator.next();
												if(Long.parseLong(element.attributeValue("id")) == question.getQuestionId()){
													finded = true;
												}else{
													element = null;
												}
											}
											
											if(element != null){
												questionTypeFactory = QuestionTypeFactoryRegistryUtil.getQuestionTypeFactoryByType(question.getQuestionTypeId());
												questionType = questionTypeFactory.getQuestionType(question);
												
												correct = questionType.correct(element);
												log.debug("correct: " + correct);
												
												if(correct > 0) {
													correctAnswers += correct ;
												}else if(question.isPenalize()){
													penalizedAnswers++;
												}
											}
										}
									}
								}catch(Exception e){
									e.printStackTrace();
								}
							}
							
							log.debug("numQuestions: " + numQuestions);
							log.debug("correctAnswers: " + correctAnswers);
							log.debug("penalizedAnswers: " + penalizedAnswers);
							
							if(numQuestions > 0){
								score = (correctAnswers - penalizedAnswers)/numQuestions;
								log.debug("scoreQuestions: " + score);
							}
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}else if(correctMode == ResourceExternalConstants.CORRECT_VIDEO && isDefaultScore){
				score = 100;
			}
		}
		log.debug("score: " + score);
		return score;
	}
	
	public String getVideo() {
		return video;
	}
	
	public boolean getShowControls() {
		return showControls;
	}
	
	public int getCorrectMode() {
		return correctMode;
	}
	
	public boolean getFinalFeedback() {
		return finalFeedback;
	}
	
	public boolean getQuestionFeedback() {
		return questionFeedback;
	}
	
	public JSONObject getQuestionPositions() {
		return questionPositions;
	}
	
	private void initializateActivity() {
		video = null;
		showControls = ResourceExternalConstants.DEFAULT_SHOW_CONTROLS;
		correctMode = ResourceExternalConstants.DEFAULT_CORRECT_MODE;
		finalFeedback = ResourceExternalConstants.DEFAULT_FINAL_FEEDBACK;
		questionFeedback = ResourceExternalConstants.DEFAULT_QUESTION_FEEDBACK;
		questionPositions = null;
	}
}
