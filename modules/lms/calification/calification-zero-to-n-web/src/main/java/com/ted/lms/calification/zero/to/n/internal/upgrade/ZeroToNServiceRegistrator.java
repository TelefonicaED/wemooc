package com.ted.lms.calification.zero.to.n.internal.upgrade;

import com.liferay.portal.kernel.service.ReleaseLocalService;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;
import com.ted.lms.calification.zero.to.n.internal.upgrade.v1_0_0.UpgradeCourseExtraData;
import com.ted.lms.service.CourseLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true, 
	service = UpgradeStepRegistrator.class
)
public class ZeroToNServiceRegistrator implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register("0.0.0", "1.0.0", new UpgradeCourseExtraData(courseLocalService, releaseLocalService));
	}
	
	@Reference(unbind = "-")
	protected void setCourseLocalService(CourseLocalService courseLocalService) {
		this.courseLocalService = courseLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setReleaseLocalService(ReleaseLocalService releaseLocalService) {
		this.releaseLocalService = releaseLocalService;
	}
	
	private CourseLocalService courseLocalService;
	private ReleaseLocalService releaseLocalService;

}
