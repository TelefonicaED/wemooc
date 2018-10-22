package com.ted.lms.learning.activity.p2p.web.internal.upgrade.v1_0_0;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Release;
import com.liferay.portal.kernel.service.ReleaseLocalService;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.ted.lms.constants.LearningActivityConstants;
import com.ted.lms.learning.activity.p2p.web.constants.P2PConstants;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.service.LearningActivityLocalService;

import java.text.DateFormat;
import java.util.Date;
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
			List<LearningActivity> listLearningActivities = learningActivityLocalService.getLearningActivitiesByTypeId(P2PConstants.TYPE);
			JSONObject activityExtraContent = null;
			Document document = null;
			Element rootElement = null;
			JSONObject p2pContent = null;
			Element assignationTypeElement = null;
			Element anonimousElement = null;
			Element emailAnomimousElement = null;
			Element askForP2PActivitiesElement = null;
			Element numValidationsElement = null;
			Element resultElement = null;
			Element fileOptionalElement = null;
			Element dateUploadElement = null;
			Element evaluationCriteriaElement = null;
			Element teamElement = null;
			JSONArray evaluationCriterias = null;
			
			DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			for(LearningActivity learningActivity: listLearningActivities) {
				if(Validator.isNotNull(learningActivity.getExtraContent())) {
					try {
						document=SAXReaderUtil.read(learningActivity.getExtraContent());
						rootElement =document.getRootElement();
						activityExtraContent = JSONFactoryUtil.createJSONObject();
						
						p2pContent = JSONFactoryUtil.createJSONObject();
						activityExtraContent.put(P2PConstants.JSON_P2P, p2pContent);
						
						assignationTypeElement = rootElement.element("assignationType");
						if(assignationTypeElement != null) {
							p2pContent.put(P2PConstants.JSON_ASSIGNATION_TYPE, assignationTypeElement.getText());
						}
						
						anonimousElement = rootElement.element("anonimous");
						if(anonimousElement != null) {
							p2pContent.put(P2PConstants.JSON_ANONIMOUS, Boolean.parseBoolean(anonimousElement.getText()));
						}
						
						emailAnomimousElement = rootElement.element("email_anonimous");
						if(emailAnomimousElement != null) {
							p2pContent.put(P2PConstants.JSON_EMAIL_ANONIMOUS, Boolean.parseBoolean(emailAnomimousElement.getText()));
						}
						
						askForP2PActivitiesElement = rootElement.element("askforp2pactivities");
						if(askForP2PActivitiesElement != null) {
							p2pContent.put(P2PConstants.JSON_ASK_FOR_P2P_ACTIVITIES, Boolean.parseBoolean(askForP2PActivitiesElement.getText()));
						}
						
						numValidationsElement = rootElement.element("validaciones");
						if(numValidationsElement != null) {
							p2pContent.put(P2PConstants.JSON_NUM_VALIDATIONS, Integer.parseInt(numValidationsElement.getText()));
						}
						
						resultElement = rootElement.element("result");
						if(resultElement != null) {
							p2pContent.put(P2PConstants.JSON_RESULT, Boolean.parseBoolean(resultElement.getText()));
						}
						
						fileOptionalElement = rootElement.element("fileoptional");
						if(fileOptionalElement != null) {
							p2pContent.put(P2PConstants.JSON_FILE_OPTIONAL, Boolean.parseBoolean(fileOptionalElement.getText()));
						}
						
						dateUploadElement = rootElement.element("dateupload");
						if(dateUploadElement != null) {
							p2pContent.put(P2PConstants.JSON_DATE_UPLOAD, (Date)dateFormat.parseObject(dateUploadElement.getTextTrim()));
						}
						
						evaluationCriterias = JSONFactoryUtil.createJSONArray();
						
						int i = 0;
						do {
							evaluationCriteriaElement = rootElement.element("text" + i);
							if(evaluationCriteriaElement != null) {
								evaluationCriterias.put(Long.parseLong(evaluationCriteriaElement.getText()));
							}
						} while(evaluationCriteriaElement != null); 
						
						if(evaluationCriterias.length() > 0) {
							p2pContent.put(P2PConstants.JSON_EVALUATION_CRITERIA, evaluationCriterias);
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
