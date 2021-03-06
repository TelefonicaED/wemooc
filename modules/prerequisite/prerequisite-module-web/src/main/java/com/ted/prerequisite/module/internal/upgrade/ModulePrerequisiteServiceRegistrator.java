package com.ted.prerequisite.module.internal.upgrade;

import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;
import com.ted.prerequisite.module.internal.upgrade.v1_0_0.UpgradeClassName;
import com.ted.prerequisite.module.internal.upgrade.v1_0_0.UpgradePrecedence;
import com.ted.prerequisite.service.PrerequisiteRelationLocalService;

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
		registry.register("0.0.0", "1.0.0", new UpgradePrecedence(companyLocalService, prerequisiteRelationLocalService));
	}

	@Reference(unbind = "-")
	protected void setClassNameLocalService(ClassNameLocalService classNameLocalService) {
		this.classNameLocalService = classNameLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setCompanyLocalService(CompanyLocalService companyLocalService) {
		this.companyLocalService = companyLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setPrerequisiteRelationLocalService(PrerequisiteRelationLocalService prerequisiteRelationLocalService) {
		this.prerequisiteRelationLocalService = prerequisiteRelationLocalService;
	}
	
	private ClassNameLocalService classNameLocalService;
	private CompanyLocalService companyLocalService;
	private PrerequisiteRelationLocalService prerequisiteRelationLocalService;
}
