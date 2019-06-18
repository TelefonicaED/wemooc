package com.ted.lms.learning.activity.resource.external.web.internal.upgrade.v1_0_0;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalService;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Release;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ReleaseLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.TempFileEntryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.ted.lms.constants.LearningActivityConstants;
import com.ted.lms.learning.activity.question.model.Question;
import com.ted.lms.learning.activity.question.service.QuestionLocalService;
import com.ted.lms.learning.activity.resource.external.web.constants.ResourceExternalConstants;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.service.LearningActivityLocalService;

import java.io.InputStream;
import java.util.List;

public class UpgradeLearningActivityExtraContent extends UpgradeProcess {

	public UpgradeLearningActivityExtraContent(LearningActivityLocalService learningActivityLocalService, 
												ReleaseLocalService releaseLocalService, QuestionLocalService questionLocalService,
												AssetEntryLocalService assetEntryLocalService, DLFileEntryLocalService dlFileEntryLocalService,
												UserLocalService userLocalService) {
		this.learningActivityLocalService = learningActivityLocalService;
		this.releaseLocalService = releaseLocalService;
		this.questionLocalService = questionLocalService;
		this.assetEntryLocalService = assetEntryLocalService;
		this.dlFileEntryLocalService = dlFileEntryLocalService;
		this.userLocalService = userLocalService;
	}
	
	@Override
	protected void doUpgrade() throws Exception {
		//Comprobamos si se está migrando o no
		Release release = releaseLocalService.fetchRelease("liferaylms-portlet");
		if(release != null && release.getBuildNumber() > 0) {
			//Actualizamos del prefspropsutil al campo CourseExtraData
			List<LearningActivity> listLearningActivities = learningActivityLocalService.getLearningActivitiesByTypeId(ResourceExternalConstants.TYPE);
			JSONObject activityExtraContent = null;
			Document document = null;
			Element rootElement = null;
			JSONObject resourceExternalContent = null;
			Element video = null;
			Element videoControlEnabled = null;
			Element assetEntry = null;
			String nameDocument = null;
			Element correctModeElement = null;
			Element finalFeedbackElement = null;
			Element questionFeedbackElement = null;
			Element secondElement = null;
			Element teamElement = null;
			DLFileEntry fileEntry = null;
			AssetEntry entry = null;
			long defaultUserId = 0;
			
			for(LearningActivity learningActivity: listLearningActivities) {
				if(Validator.isNotNull(learningActivity.getExtraContent())) {
					try {
						document=SAXReaderUtil.read(learningActivity.getExtraContent());
						rootElement =document.getRootElement();
						activityExtraContent = JSONFactoryUtil.createJSONObject();
						
						resourceExternalContent = JSONFactoryUtil.createJSONObject();
						activityExtraContent.put(ResourceExternalConstants.JSON_RESOURCE_EXTERNAL, resourceExternalContent);
						
						video = rootElement.element("video");
						if(video != null) {
							resourceExternalContent.put(ResourceExternalConstants.JSON_VIDEO, video.getText());
						}
						
						videoControlEnabled = rootElement.element("video-control");
						if(videoControlEnabled != null) {
							resourceExternalContent.put(ResourceExternalConstants.JSON_VIDEO_CONTROL, Boolean.parseBoolean(videoControlEnabled.getText()));
						}
	
						correctModeElement = rootElement.element("correctMode");
						if(correctModeElement != null) {
							resourceExternalContent.put(ResourceExternalConstants.JSON_CORRECT_MODE, Integer.parseInt(correctModeElement.getText()));
						}
						
						finalFeedbackElement = rootElement.element("finalFeedback");
						if(finalFeedbackElement != null){
							resourceExternalContent.put(ResourceExternalConstants.JSON_FINAL_FEEDBACK, Boolean.parseBoolean(finalFeedbackElement.getText()));
						}
						
						questionFeedbackElement = rootElement.element("questionFeedback");
						if(questionFeedbackElement != null) {
							resourceExternalContent.put(ResourceExternalConstants.JSON_QUESTION_FEEDBACK, Boolean.parseBoolean(questionFeedbackElement.getText()));
						}
						
						//Pedimos las preguntas de esa actividad
						List<Question> listQuestions = questionLocalService.getQuestions(learningActivity.getActId());
						
						if(Validator.isNotNull(listQuestions)) {
							JSONObject questionPositions = JSONFactoryUtil.createJSONObject();
							resourceExternalContent.put(ResourceExternalConstants.JSON_QUESTION_POSITIONS, questionPositions);
							
							for(Question question: listQuestions){ 
								secondElement = rootElement.element("question_" + question.getQuestionId());
								if(secondElement != null) {
									questionPositions.put(String.valueOf(question.getQuestionId()), Integer.parseInt(secondElement.getText()));
								}
							}
						}
						
						//Migramos el equipo si existe
						teamElement = rootElement.element("team");
						if(teamElement != null){
							activityExtraContent.put(LearningActivityConstants.JSON_TEAM, Long.parseLong(teamElement.getText()));
						}
						
						assetEntry = null;
						int i = 0;
						
						//TODO Cambiar para que los documentos se guarden como attachments
						
						JSONArray additionalFiles = JSONFactoryUtil.createJSONArray();
						resourceExternalContent.put(ResourceExternalConstants.JSON_ADDITIONAL_FILES, additionalFiles);
						
						do {
							nameDocument = "document";
							if(i > 0) {
								nameDocument += (i-1);
							}
							assetEntry = rootElement.element(nameDocument);
							if(assetEntry != null) {
								entry = assetEntryLocalService.getAssetEntry(Long.parseLong(assetEntry.attributeValue("id")));
								fileEntry = dlFileEntryLocalService.getDLFileEntry(entry.getClassPK());

								InputStream inputStream = fileEntry.getContentStream();
								String mimeType = fileEntry.getMimeType();
								
								defaultUserId = userLocalService.getDefaultUserId(learningActivity.getCompanyId());

								learningActivityLocalService.addAttachment(defaultUserId, learningActivity, fileEntry.getFileName(), inputStream, mimeType);

								if (fileEntry != null) {
									dlFileEntryLocalService.deleteDLFileEntry(fileEntry);
								}
							}
						}while(assetEntry!= null);
						
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
	private final QuestionLocalService questionLocalService;
	private final ReleaseLocalService releaseLocalService;
	private final AssetEntryLocalService assetEntryLocalService;
	private final DLFileEntryLocalService dlFileEntryLocalService;
	private final UserLocalService userLocalService;
}
