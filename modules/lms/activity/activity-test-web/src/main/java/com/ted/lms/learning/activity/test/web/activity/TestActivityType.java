package com.ted.lms.learning.activity.test.web.activity;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.ted.lms.learning.activity.QuestionsLearningActivityType;
import com.ted.lms.learning.activity.question.model.Question;
import com.ted.lms.learning.activity.question.model.QuestionType;
import com.ted.lms.learning.activity.question.model.QuestionTypeFactory;
import com.ted.lms.learning.activity.question.registry.QuestionTypeFactoryRegistryUtil;
import com.ted.lms.learning.activity.question.service.QuestionLocalServiceUtil;
import com.ted.lms.learning.activity.test.web.constants.TestConstants;
import com.ted.lms.learning.activity.test.web.util.TestPrefsPropsValues;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.LearningActivityTry;
import com.ted.lms.service.LearningActivityResultLocalServiceUtil;
import com.ted.lms.service.LearningActivityTryLocalServiceUtil;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.portlet.ActionRequest;

public class TestActivityType extends QuestionsLearningActivityType {
	
	private static final Log log = LogFactoryUtil.getLog(TestActivityType.class);
	private long random;
	private String password;
	private long timeStamp;
	private boolean improve;
	private boolean enableOrder;
	private long questionsPerPage;
	private boolean preview;

	public TestActivityType(LearningActivity activity) {
		super(activity);
		
		JSONObject extraContent = activity.getExtraContentJSON();
		
		if(extraContent != null) {
			JSONObject test = extraContent.getJSONObject(TestConstants.JSON_TEST);
			if(test != null) {
				random = test.getLong(TestConstants.JSON_RANDOM, TestConstants.DEFAULT_RANDOM);
				password = HtmlUtil.unescape(test.getString(TestConstants.JSON_PASSWORD, TestConstants.DEFAULT_PASSWORD));
				timeStamp = test.getLong(TestConstants.JSON_TIME_STAMP, TestConstants.DEFAULT_TIME_STAMP);
				improve = test.getBoolean(TestConstants.JSON_IMPROVE, TestConstants.DEFAULT_IMPROVE);
				enableOrder = test.getBoolean(TestConstants.JSON_ENABLE_ORDER, TestConstants.DEFAULT_ENABLE_ORDER);
				questionsPerPage = test.getLong(TestConstants.JSON_QUESTIONS_PER_PAGE, TestPrefsPropsValues.getQuestionPerPage(activity.getCompanyId()));
				preview = test.getBoolean(TestConstants.JSON_PREVIEW, TestConstants.DEFAULT_PREVIEW);
			}else {
				initializateActivity();
			}
		}else {
			initializateActivity();
		}
	}
	
	@Override
	public String getClassName() {
		return TestActivityType.class.getName();
	}
	
	@Override
	public void setExtraContent(ActionRequest actionRequest) throws PortalException {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		super.setExtraContent(actionRequest);
		
		JSONObject extraContent = activity.getExtraContentJSON();
		JSONObject testContent = extraContent.getJSONObject(TestConstants.JSON_TEST);
		
		if(Validator.isNull(testContent)) {
			testContent = JSONFactoryUtil.createJSONObject();
			extraContent.put(TestConstants.JSON_TEST, testContent);
		}
		
		random = ParamUtil.getLong(actionRequest, "random", TestConstants.DEFAULT_RANDOM);
		password = ParamUtil.get(actionRequest,"password",TestConstants.DEFAULT_PASSWORD);
		if(Validator.isNotNull(password))
			password = HtmlUtil.escape(password.trim());
		timeStamp = ParamUtil.getLong(actionRequest, "testHourDuration",0) * 3600 
                + ParamUtil.getLong(actionRequest, "testMinuteDuration",0) * 60 
                + ParamUtil.getLong(actionRequest, "testSecondDuration",0);
		improve = ParamUtil.getBoolean(actionRequest, "improve", TestConstants.DEFAULT_IMPROVE);
		enableOrder = ParamUtil.getBoolean(actionRequest, "enableOrder", TestConstants.DEFAULT_ENABLE_ORDER);
		questionsPerPage = ParamUtil.getLong(actionRequest, "questionsPerPage", TestPrefsPropsValues.getQuestionPerPage(themeDisplay.getCompanyId()));
		preview = ParamUtil.getBoolean(actionRequest, "preview", TestConstants.DEFAULT_PREVIEW);
		
		testContent.put(TestConstants.JSON_RANDOM, random);
		testContent.put(TestConstants.JSON_PASSWORD, password);
		testContent.put(TestConstants.JSON_TIME_STAMP, timeStamp);
		
		testContent.put(TestConstants.JSON_IMPROVE, improve);
		testContent.put(TestConstants.JSON_ENABLE_ORDER, enableOrder);
		testContent.put(TestConstants.JSON_QUESTIONS_PER_PAGE, questionsPerPage);
		testContent.put(TestConstants.JSON_PREVIEW, preview);
		
		activity.setExtraContent(extraContent.toJSONString());
		
	}

	@Override
	public double calculateResult(LearningActivityTry learningActivityTry) {
		double score = 0;
		
		try {
			
			log.debug("LAT DATA "+learningActivityTry.getTryResultData());
			Document documentTry = SAXReaderUtil.read(learningActivityTry.getTryResultData());
			Element rootTry = documentTry.getRootElement();
			
			score = learningActivityTry.getResult();
			log.debug("score del try: " + score);
			List<Question> listQuestions = QuestionLocalServiceUtil.getQuestions(activity.getActId());
					
			if(listQuestions != null && listQuestions.size() > 0){
				log.debug("tiene preguntas: " + listQuestions.size());
				Element element = null;
				int correctAnswers = 0;
				int penalizedAnswers = 0;
				int numQuestions = 0;
				Iterator<Element> questionIterator = null;
				long correct = 0;
				HashMap<Long, Question> questions = new HashMap<Long, Question>();
				
				questionIterator = rootTry != null ? rootTry.elementIterator("question") : null;
				
				if(questionIterator!=null){
					Question xmlQuestion =null;
					QuestionTypeFactory questionTypeFactory = null;
					QuestionType questionType = null;
					
					for(Question question :listQuestions){
						questions.put(question.getQuestionId(),question);
					}
					
					while(questionIterator.hasNext()){
						try{
							element = questionIterator.next();
							log.debug("element: " + element.getName() + " - " + element.attributeValue("id"));
							if(element != null){
								xmlQuestion = questions.get(Long.parseLong(element.attributeValue("id")));
								
								if(Validator.isNotNull(xmlQuestion)){
									log.debug("xmlQuestion: " + xmlQuestion.getQuestionId());
									numQuestions++;
									questionTypeFactory = QuestionTypeFactoryRegistryUtil.getQuestionTypeFactoryByType(xmlQuestion.getQuestionTypeId());
									questionType = questionTypeFactory.getQuestionType(xmlQuestion);
									correct = questionType.correct(element);
									log.debug("correct: " + correct);
									if(correct > 0) {
										correctAnswers += correct ;
									}else if(xmlQuestion.isPenalize()){
										penalizedAnswers+=correct;
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
						score = (correctAnswers + penalizedAnswers)/numQuestions;
						log.debug("scoreQuestions: " + score);
					}
						
					
				}
			}
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		log.debug("score: " + score);
		return score;
	}
	
	public boolean hasTries(long userId) {
		
		//Si ya ha pasado el test, no puede hacer más intentos.
		if(LearningActivityResultLocalServiceUtil.hasUserPassed(activity.getActId(), userId) && getImprove()) {
			return false;	
		} else if(activity.getTries() == 0) {
			return true;
		}
		
		//Mirar si los intentos que tiene son menores de los intentos posibles
		int userTries = LearningActivityTryLocalServiceUtil.getLearningActivityTriesCount(activity.getActId(), userId);
		int numTriesOpened = LearningActivityTryLocalServiceUtil.getNumTriesOpened(activity.getActId(), userId);
			
		//Si tiene menos intentos de los que se puede hacer
		return (userTries-numTriesOpened) < activity.getTries();
	}
	
	
	public long getRandom() {
		return random;
	}
	
	public String getPassword() {
		return password;
	}
	
	public long getTimeStamp() {
		return timeStamp;
	}
	
	public long getHourDuration() {
		return getTimeStamp() / 3600;
	}
	
	public long getMinuteDuration() {
		return (getTimeStamp()  % 3600) / 60;
	}
	
	public long getSecondDuration() {
		return getTimeStamp() % 60;
	}
	
	public boolean getImprove() {
		return improve;
	}

	public boolean getEnableOrder() {
		return enableOrder;
	}
	
	public long getQuestionsPerPage() {
		return questionsPerPage;
	}
	
	public boolean getPreview() {
		return preview;
	}

	private void initializateActivity() {
		random = TestConstants.DEFAULT_RANDOM;
		password = TestConstants.DEFAULT_PASSWORD;
		timeStamp = TestConstants.DEFAULT_TIME_STAMP;
		improve = TestConstants.DEFAULT_IMPROVE;
		enableOrder = TestConstants.DEFAULT_ENABLE_ORDER;
		questionsPerPage = TestPrefsPropsValues.getQuestionPerPage(activity.getCompanyId());
		preview = TestConstants.DEFAULT_PREVIEW;
	}	
}
