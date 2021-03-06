package com.ted.lms.learning.activity.test.web.internal.upgrade.v1_0_0;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Release;
import com.liferay.portal.kernel.service.ReleaseLocalService;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.ted.lms.constants.LearningActivityConstants;
import com.ted.lms.learning.activity.question.constants.QuestionConstants;
import com.ted.lms.learning.activity.question.model.Question;
import com.ted.lms.learning.activity.question.service.QuestionLocalService;
import com.ted.lms.learning.activity.test.web.constants.TestConstants;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.service.LearningActivityLocalService;

import java.util.List;

public class UpgradeLearningActivityExtraContent extends UpgradeProcess {

	public UpgradeLearningActivityExtraContent(LearningActivityLocalService learningActivityLocalService, 
												ReleaseLocalService releaseLocalService,
												QuestionLocalService questionLocalService) {
		this.learningActivityLocalService = learningActivityLocalService;
		this.releaseLocalService = releaseLocalService;
		this.questionLocalService = questionLocalService;
	}
	
	@Override
	protected void doUpgrade() throws Exception {
		//Comprobamos si se está migrando o no
		Release release = releaseLocalService.fetchRelease("liferaylms-portlet");
		if(release != null && release.getBuildNumber() > 0) {
			List<LearningActivity> listLearningActivities = learningActivityLocalService.getLearningActivitiesByTypeId(TestConstants.TYPE);
			JSONObject activityExtraContent = null;
			Document document = null;
			Element rootElement = null;
			JSONObject testContent = null;
			JSONObject questionsContent = null;
			Element randomElement = null;
			Element passwordElement = null;
			Element timeStampElement = null;
			Element showCorrectAnswerElement = null;
			Element hideFeedbackElement = null;
			Element showCorrectAnswerOnlyOnFinalTryElement = null;
			Element improveElement = null;
			Element enableOrderElement = null;
			Element questionPerPageElement = null;
			Element previewElement = null;
			Element teamElement = null;
			List<Question> questions = null;
			
			for(LearningActivity learningActivity: listLearningActivities) {
				if(Validator.isNotNull(learningActivity.getExtraContent())) {
					try {
						document=SAXReaderUtil.read(learningActivity.getExtraContent());
						rootElement =document.getRootElement();
						activityExtraContent = JSONFactoryUtil.createJSONObject();
						
						questionsContent = JSONFactoryUtil.createJSONObject();
						activityExtraContent.put(QuestionConstants.JSON_QUESTIONS, questionsContent);
						
						showCorrectAnswerElement = rootElement.element("showCorrectAnswer");
						if(showCorrectAnswerElement != null) {
							questionsContent.put(QuestionConstants.JSON_SHOW_CORRECT_ANSWER, Boolean.parseBoolean(showCorrectAnswerElement.getText()));
						}
						
						hideFeedbackElement = rootElement.element("hideFeedback");
						if(hideFeedbackElement != null) {
							questionsContent.put(QuestionConstants.JSON_SHOW_FEEDBACK, !Boolean.parseBoolean(hideFeedbackElement.getText()));
						}
						
						showCorrectAnswerOnlyOnFinalTryElement = rootElement.element("showCorrectAnswerOnlyOnFinalTry");
						if(showCorrectAnswerOnlyOnFinalTryElement != null) {
							questionsContent.put(QuestionConstants.JSON_SHOW_CORRECT_ANSWER_ONLY_ON_FINAL_TRY, Boolean.parseBoolean(showCorrectAnswerOnlyOnFinalTryElement.getText()));
						}
						
						testContent = JSONFactoryUtil.createJSONObject();
						activityExtraContent.put(TestConstants.JSON_TEST, testContent);
						
						randomElement = rootElement.element("random");
						if(randomElement != null) {
							testContent.put(TestConstants.JSON_RANDOM, Long.parseLong(randomElement.getText()));
						}
						
						passwordElement = rootElement.element("password");
						if(passwordElement != null) {
							testContent.put(TestConstants.JSON_PASSWORD, passwordElement.getText());
						}
						
						timeStampElement = rootElement.element("timeStamp");
						if(timeStampElement != null) {
							testContent.put(TestConstants.JSON_TIME_STAMP, Long.parseLong(timeStampElement.getText()));
						}
						
						improveElement = rootElement.element("improve");
						if(improveElement != null) {
							testContent.put(TestConstants.JSON_IMPROVE, Boolean.parseBoolean(improveElement.getText()));
						}
						
						enableOrderElement = rootElement.element("enableorder");
						if(enableOrderElement != null) {
							testContent.put(TestConstants.JSON_ENABLE_ORDER, Boolean.parseBoolean(enableOrderElement.getText()));
						}
						
						questionPerPageElement = rootElement.element("questionsPerPage");
						if(questionPerPageElement != null) {
							testContent.put(TestConstants.JSON_QUESTIONS_PER_PAGE, Long.parseLong(questionPerPageElement.getText()));
						}
						
						previewElement = rootElement.element("showOnlyPreview");
						if(previewElement != null) {
							testContent.put(TestConstants.JSON_PREVIEW, Boolean.parseBoolean(previewElement.getText()));
						}
	
						//Migramos el equipo si existe
						teamElement = rootElement.element("team");
						if(teamElement != null){
							activityExtraContent.put(LearningActivityConstants.JSON_TEAM, Long.parseLong(teamElement.getText()));
						}
						
						learningActivity.setExtraContent(activityExtraContent.toJSONString());
						learningActivityLocalService.updateLearningActivity(learningActivity);
					} catch(DocumentException e) {
						e.printStackTrace();
					}
				}
				
				questions = questionLocalService.getQuestions(learningActivity.getActId());
				for(Question question : questions){
					if(question.getWeight()==0){
						question.setWeight(question.getQuestionId());
						questionLocalService.updateQuestion(question);
					}
				}
			}
		}	
	}
	
	private final LearningActivityLocalService learningActivityLocalService;
	private final QuestionLocalService questionLocalService;
	private final ReleaseLocalService releaseLocalService;
}
