package com.ted.prerequisite.module.internal.upgrade.v1_0_0;

import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.ted.prerequisite.module.ModulePrerequisiteFactory;

public class UpgradeClassName extends UpgradeProcess {

	public UpgradeClassName(ClassNameLocalService classNameLocalService) {
		this.classNameLocalService = classNameLocalService;
	}

	@Override
	protected void doUpgrade() throws Exception {
		classNameLocalService.addClassName(ModulePrerequisiteFactory.class.getName());
	}
	
	private final ClassNameLocalService classNameLocalService;

}
