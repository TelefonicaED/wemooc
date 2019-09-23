package com.ted.lms.question.options.internal.upgrade;

import com.liferay.portal.kernel.service.ReleaseLocalService;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;
import com.ted.lms.learning.activity.question.service.QuestionLocalService;
import com.ted.lms.question.options.internal.upgrade.v1_0_0.UpgradeExtraContent;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true, 
	service = UpgradeStepRegistrator.class
)
public class OptionsServiceRegistrator implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register("0.0.0", "1.0.0", new UpgradeExtraContent(questionLocalService, releaseLocalService));
	}
	
	
	@Reference(unbind = "-")
	protected void setQuestionLocalService(QuestionLocalService questionLocalService) {
		this.questionLocalService = questionLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setReleaseLocalService(ReleaseLocalService releaseLocalService) {
		this.releaseLocalService = releaseLocalService;
	}
	
	private QuestionLocalService questionLocalService;
	private ReleaseLocalService releaseLocalService;

}
