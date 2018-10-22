package com.ted.lms.learning.activity.test;

import com.liferay.petra.string.StringPool;
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
import com.ted.lms.learning.activity.question.model.Question;
import com.ted.lms.learning.activity.question.model.QuestionType;
import com.ted.lms.learning.activity.question.model.QuestionTypeFactory;
import com.ted.lms.learning.activity.question.registry.QuestionTypeFactoryRegistryUtil;
import com.ted.lms.learning.activity.question.service.QuestionLocalService;
import com.ted.lms.learning.activity.test.web.constants.TestConstants;
import com.ted.lms.learning.activity.test.web.util.TestPrefsPropsValues;
import com.ted.lms.model.BaseLearningActivityType;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.LearningActivityTry;
import com.ted.lms.service.LearningActivityResultLocalService;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.portlet.ActionRequest;

public class TestActivityType extends BaseLearningActivityType {
	
	private QuestionLocalService questionLocalService;
	private static final Log log = LogFactoryUtil.getLog(TestActivityType.class);

	public TestActivityType(LearningActivity activity, LearningActivityResultLocalService learningActivityResultLocalService,
			QuestionLocalService questionLocalService) {
		super(activity, learningActivityResultLocalService);
		this.questionLocalService = questionLocalService;
	}
	
	@Override
	public String getClassName() {
		return TestActivityType.class.getName();
	}
	
	@Override
	public void setExtraContent(ActionRequest actionRequest) throws PortalException {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		JSONObject extraContent = activity.getExtraContentJSON();
		JSONObject testContent = extraContent.getJSONObject(TestConstants.JSON_TEST);
		
		if(Validator.isNotNull(testContent)) {
			testContent = JSONFactoryUtil.createJSONObject();
			extraContent.put(TestConstants.JSON_TEST, testContent);
		}
		
		testContent.put(TestConstants.JSON_RANDOM, ParamUtil.getLong(actionRequest, "random", 0));
		testContent.put(TestConstants.JSON_PASSWORD, HtmlUtil.escape(ParamUtil.get(actionRequest,"password",StringPool.BLANK).trim()));
		
		long timeStamp = ParamUtil.getLong(actionRequest, "hourDuration",0) * 3600 
                + ParamUtil.getLong(actionRequest, "minuteDuration",0) * 60 
                + ParamUtil.getLong(actionRequest, "secondDuration",0);
		
		testContent.put(TestConstants.JSON_TIME_STAMP, timeStamp);
		
		testContent.put(TestConstants.JSON_SHOW_CORRECT_ANSWER, ParamUtil.getBoolean(actionRequest, "showCorrectAnswer", false));
		testContent.put(TestConstants.JSON_HIDE_FEEDBACK, ParamUtil.getBoolean(actionRequest, "hideFeedback", false));
		testContent.put(TestConstants.JSON_SHOW_CORRECT_ANSWER_ONLY_ON_FINAL_TRY, ParamUtil.getBoolean(actionRequest, "showCorrectAnswerOnlyOnFinalTry", false));
		testContent.put(TestConstants.JSON_IMPROVE, ParamUtil.getBoolean(actionRequest, "improve", false));
		testContent.put(TestConstants.JSON_ENABLE_ORDER, ParamUtil.getBoolean(actionRequest, "enableorder", false));
		testContent.put(TestConstants.JSON_QUESTIONS_PER_PAGE, ParamUtil.getLong(actionRequest, "questionsPerPage", TestPrefsPropsValues.getQuestionPerPage(themeDisplay.getCompanyId())));
		
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
			List<Question> listQuestions = questionLocalService.getQuestions(activity.getActId());
					
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
							if(element != null){
								xmlQuestion = questions.get(Long.parseLong(element.attributeValue("id")));
								if(Validator.isNotNull(xmlQuestion)){
									numQuestions++;
									questionTypeFactory = QuestionTypeFactoryRegistryUtil.getQuestionTypeFactoryByType(xmlQuestion.getQuestionType());
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.debug("score: " + score);
		return score;
	}
}
