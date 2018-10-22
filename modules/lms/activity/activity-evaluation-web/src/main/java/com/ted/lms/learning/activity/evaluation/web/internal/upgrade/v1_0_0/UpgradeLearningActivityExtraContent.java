package com.ted.lms.learning.activity.evaluation.web.internal.upgrade.v1_0_0;

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
import com.ted.lms.learning.activity.evaluation.web.constants.EvaluationConstants;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.service.LearningActivityLocalService;

import java.text.DateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

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
			List<LearningActivity> listLearningActivities = learningActivityLocalService.getLearningActivitiesByTypeId(EvaluationConstants.TYPE);
			JSONObject activityExtraContent = null;
			Document document = null;
			Element rootElement = null;
			JSONObject evaluationContent = null;
			Element activitiesElement = null;
			Element firedDateElement = null;
			Element publishDateElement = null;
			Element teamElement = null;
			Iterator<Element> activitiesElementItr = null;
			JSONArray activities = null;
			JSONObject activity = null;
			
			DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat("yyyy-MM-dd'T'HH:mm:sszzz",Locale.US);
			
			for(LearningActivity learningActivity: listLearningActivities) {
				if(Validator.isNotNull(learningActivity.getExtraContent())) {
					try {
						document=SAXReaderUtil.read(learningActivity.getExtraContent());
						rootElement =document.getRootElement();
						activityExtraContent = JSONFactoryUtil.createJSONObject();
						
						evaluationContent = JSONFactoryUtil.createJSONObject();
						activityExtraContent.put(EvaluationConstants.JSON_EVALUATION, evaluationContent);
						
						firedDateElement = rootElement.element("firedDate");
						if(firedDateElement != null) {
							evaluationContent.put(EvaluationConstants.JSON_FIRED_DATE, (Date)dateFormat.parseObject(firedDateElement.getTextTrim()));
						}
						
						publishDateElement = rootElement.element("publishDate");
						if(publishDateElement != null) {
							evaluationContent.put(EvaluationConstants.JSON_PUBLISH_DATE, (Date)dateFormat.parseObject(publishDateElement.getTextTrim()));
						}
						
						activitiesElement = rootElement.element("activities");
						if(activitiesElement!=null){
							activitiesElementItr = activitiesElement.elementIterator();
							
							activities = JSONFactoryUtil.createJSONArray();
							evaluationContent.put(EvaluationConstants.JSON_ACTIVITIES, activities);
							
							while(activitiesElementItr.hasNext()) {
								Element activityElement =activitiesElementItr.next();
								if(("activity".equals(activityElement.getName()))&&(activityElement.attribute("id")!=null)&&(activityElement.attribute("id").getValue().length()!=0)){
									activity = JSONFactoryUtil.createJSONObject();
									activity.put(EvaluationConstants.JSON_ACT_ID, Long.valueOf(activityElement.attribute("id").getValue()));
									activity.put(EvaluationConstants.JSON_WEIGHT, Long.valueOf(activityElement.getText()));
									activities.put(activity);
								}
							}				
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
