package com.ted.prerequisite.module.internal.upgrade;

import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.ReleaseLocalService;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator.Registry;
import com.ted.lms.service.CourseLocalService;
import com.ted.prerequisite.module.internal.upgrade.v1_0_0.UpgradeClassName;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
		immediate = true, 
		service = UpgradeStepRegistrator.class
	)
public class ModulePrerequisiteServiceRegistrator implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register("0.0.0", "1.0.0", new UpgradeClassName(classNameLocalService));
	}

	@Reference(unbind = "-")
	protected void setClassNameLocalService(ClassNameLocalService classNameLocalService) {
		this.classNameLocalService = classNameLocalService;
	}
	
	private ClassNameLocalService classNameLocalService;
}
