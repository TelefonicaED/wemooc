package com.ted.lms.learning.activity.resource.external.web.internal.upgrade;

import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.document.library.kernel.service.DLFileEntryLocalService;
import com.liferay.portal.kernel.service.ReleaseLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;
import com.ted.lms.learning.activity.question.service.QuestionLocalService;
import com.ted.lms.learning.activity.resource.external.web.internal.upgrade.v1_0_0.UpgradeLearningActivityExtraContent;
import com.ted.lms.service.LearningActivityLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true, 
	service = UpgradeStepRegistrator.class
)
public class ResourceExternalServiceRegistrator implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register("0.0.0", "1.0.0", new UpgradeLearningActivityExtraContent(learningActivityLocalService, releaseLocalService,
				questionLocalService, assetEntryLocalService, dlFileEntryLocalService, userLocalService));
	}
	
	@Reference(unbind = "-")
	protected void setLearningActivityLocalService(LearningActivityLocalService learningActivityLocalService) {
		this.learningActivityLocalService = learningActivityLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setReleaseLocalService(ReleaseLocalService releaseLocalService) {
		this.releaseLocalService = releaseLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setQuestionLocalService(QuestionLocalService questionLocalService) {
		this.questionLocalService = questionLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setAssetEntryLocalService(AssetEntryLocalService assetEntryLocalService) {
		this.assetEntryLocalService = assetEntryLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setDLFileEntryLocalService(DLFileEntryLocalService dlFileEntryLocalService) {
		this.dlFileEntryLocalService = dlFileEntryLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setUserLocalService(UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}
	
	private LearningActivityLocalService learningActivityLocalService;
	private ReleaseLocalService releaseLocalService;
	private QuestionLocalService questionLocalService;
	private AssetEntryLocalService assetEntryLocalService;
	private DLFileEntryLocalService dlFileEntryLocalService;
	private UserLocalService userLocalService;
}
