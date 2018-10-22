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
import com.ted.lms.learning.activity.test.web.constants.TestConstants;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.service.LearningActivityLocalService;

import java.util.List;

public class UpgradeLearningActivityExtraContent extends UpgradeProcess {

	public UpgradeLearningActivityExtraContent(LearningActivityLocalService learningActivityLocalService, 
												ReleaseLocalService releaseLocalService) {
		this.learningActivityLocalService = learningActivityLocalService;
		this.releaseLocalService = releaseLocalService;
	}
	
	@Override
	protected void doUpgrade() throws Exception {
		//Comprobamos si se está migrando o no
		Release release = releaseLocalService.fetchRelease("liferaylms-portlet");
		if(release != null && release.getBuildNumber() > 0) {
			//Actualizamos del prefspropsutil al campo CourseExtraData
			List<LearningActivity> listLearningActivities = learningActivityLocalService.getLearningActivitiesByTypeId(TestConstants.TYPE);
			JSONObject activityExtraContent = null;
			Document document = null;
			Element rootElement = null;
			JSONObject testContent = null;
			Element randomElement = null;
			Element passwordElement = null;
			Element timeStampElement = null;
			Element showCorrectAnswerElement = null;
			Element hideFeedbackElement = null;
			Element showCorrectAnswerOnlyOnFinalTryElement = null;
			Element improveElement = null;
			Element enableOrderElement = null;
			Element questionPerPageElement = null;
			Element teamElement = null;
			
			for(LearningActivity learningActivity: listLearningActivities) {
				if(Validator.isNotNull(learningActivity.getExtraContent())) {
					try {
						document=SAXReaderUtil.read(learningActivity.getExtraContent());
						rootElement =document.getRootElement();
						activityExtraContent = JSONFactoryUtil.createJSONObject();
						
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
						
						showCorrectAnswerElement = rootElement.element("showCorrectAnswer");
						if(showCorrectAnswerElement != null) {
							testContent.put(TestConstants.JSON_SHOW_CORRECT_ANSWER, Long.parseLong(showCorrectAnswerElement.getText()));
						}
						
						hideFeedbackElement = rootElement.element("hideFeedback");
						if(hideFeedbackElement != null) {
							testContent.put(TestConstants.JSON_HIDE_FEEDBACK, Long.parseLong(hideFeedbackElement.getText()));
						}
						
						showCorrectAnswerOnlyOnFinalTryElement = rootElement.element("showCorrectAnswerOnlyOnFinalTry");
						if(showCorrectAnswerOnlyOnFinalTryElement != null) {
							testContent.put(TestConstants.JSON_SHOW_CORRECT_ANSWER_ONLY_ON_FINAL_TRY, Long.parseLong(showCorrectAnswerOnlyOnFinalTryElement.getText()));
						}
						
						improveElement = rootElement.element("improve");
						if(improveElement != null) {
							testContent.put(TestConstants.JSON_IMPROVE, Long.parseLong(improveElement.getText()));
						}
						
						enableOrderElement = rootElement.element("enableorder");
						if(enableOrderElement != null) {
							testContent.put(TestConstants.JSON_ENABLE_ORDER, Long.parseLong(enableOrderElement.getText()));
						}
						
						questionPerPageElement = rootElement.element("questionsPerPage");
						if(questionPerPageElement != null) {
							testContent.put(TestConstants.JSON_QUESTIONS_PER_PAGE, Long.parseLong(questionPerPageElement.getText()));
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
			}
		}	
	}
	
	private final LearningActivityLocalService learningActivityLocalService;
	private final ReleaseLocalService releaseLocalService;
}
