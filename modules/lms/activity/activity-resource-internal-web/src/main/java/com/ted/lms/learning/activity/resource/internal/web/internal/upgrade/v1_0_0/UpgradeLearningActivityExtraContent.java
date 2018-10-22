package com.ted.lms.learning.activity.resource.internal.web.internal.upgrade.v1_0_0;

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
import com.ted.lms.learning.activity.resource.internal.web.constants.ResourceInternalConstants;
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
			List<LearningActivity> listLearningActivities = learningActivityLocalService.getLearningActivitiesByTypeId(ResourceInternalConstants.TYPE);
			JSONObject activityExtraContent = null;
			Document document = null;
			Element rootElement = null;
			JSONObject resourceInternalContent = null;
			Element assetEntryElement = null;
			Element teamElement = null;
			
			for(LearningActivity learningActivity: listLearningActivities) {
				if(Validator.isNotNull(learningActivity.getExtraContent())) {
					try {
						document=SAXReaderUtil.read(learningActivity.getExtraContent());
						rootElement =document.getRootElement();
						activityExtraContent = JSONFactoryUtil.createJSONObject();
						
						resourceInternalContent = JSONFactoryUtil.createJSONObject();
						activityExtraContent.put(ResourceInternalConstants.JSON_RESOURCE_INTERNAL, resourceInternalContent);
						
						assetEntryElement = rootElement.element("assetEntry");
						if(assetEntryElement != null) {
							resourceInternalContent.put(ResourceInternalConstants.JSON_RESOURCE_INTERNAL_ASSET_ENTRY, Long.parseLong(assetEntryElement.getText()));
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
