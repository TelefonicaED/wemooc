package com.ted.lms.learning.activity.p2p.web.internal.upgrade;

import com.liferay.portal.kernel.service.ReleaseLocalService;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;
import com.ted.lms.learning.activity.p2p.web.internal.upgrade.v1_0_0.UpgradeLearningActivityExtraContent;
import com.ted.lms.service.LearningActivityLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true, 
	service = UpgradeStepRegistrator.class
)
public class P2PServiceRegistrator implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register("0.0.0", "1.0.0", new UpgradeLearningActivityExtraContent(learningActivityLocalService, releaseLocalService));
	}
	
	@Reference(unbind = "-")
	protected void setLearningActivityLocalService(LearningActivityLocalService learningActivityLocalService) {
		this.learningActivityLocalService = learningActivityLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setReleaseLocalService(ReleaseLocalService releaseLocalService) {
		this.releaseLocalService = releaseLocalService;
	}
	
	private LearningActivityLocalService learningActivityLocalService;
	private ReleaseLocalService releaseLocalService;
}
