package com.ted.lms.internal.upgrade;

import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;
import com.ted.lms.internal.upgrade.v1_0_0.UpgradeClassNames;
import com.ted.lms.internal.upgrade.v1_0_0.UpgradeSchema;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true, 
	service = UpgradeStepRegistrator.class
)
public class LMSServiceRegistrator implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register("0.0.0", "1.0.0", new UpgradeSchema());
		registry.register("0.0.0", "1.0.0", new UpgradeClassNames(classNameLocalService));
	}
	
	@Reference(unbind = "-")
	protected void setClassNameLocalService(ClassNameLocalService classNameLocalService) {
		this.classNameLocalService = classNameLocalService;
	}

	private ClassNameLocalService classNameLocalService;
}
