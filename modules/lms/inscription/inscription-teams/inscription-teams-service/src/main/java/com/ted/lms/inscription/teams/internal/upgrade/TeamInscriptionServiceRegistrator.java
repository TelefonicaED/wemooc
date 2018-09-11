package com.ted.lms.inscription.teams.internal.upgrade;

import com.liferay.portal.kernel.service.ReleaseLocalService;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;
import com.ted.lms.inscription.teams.internal.upgrade.v1_0_0.UpgradeSchema;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true, 
	service = UpgradeStepRegistrator.class
)
public class TeamInscriptionServiceRegistrator implements UpgradeStepRegistrator {
	@Override
	public void register(Registry registry) {
		registry.register("0.0.0", "1.0.0", new UpgradeSchema(releaseLocalService));
	}
	
	@Reference(unbind = "-")
	protected void setReleaseLocalService(ReleaseLocalService releaseLocalService) {
		this.releaseLocalService = releaseLocalService;
	}
	
	private ReleaseLocalService releaseLocalService;
}
