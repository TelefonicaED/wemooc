package com.ted.prerequisite.module.internal.upgrade.v1_0_0;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.PortalUtil;
import com.ted.lms.model.Module;
import com.ted.prerequisite.model.PrerequisiteRelation;
import com.ted.prerequisite.module.ModulePrerequisiteFactory;
import com.ted.prerequisite.module.internal.constants.ModulePrerequisiteConstants;
import com.ted.prerequisite.service.PrerequisiteRelationLocalService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class UpgradePrecedence extends UpgradeProcess{
	
	public UpgradePrecedence(CompanyLocalService companyLocalService, PrerequisiteRelationLocalService prerequisiteRelationLocalService) {
		this.companyLocalService = companyLocalService;
		this.prerequisiteRelationLocalService = prerequisiteRelationLocalService;
	}

	@Override
	protected void doUpgrade() throws Exception {
		updatePrecedenceModules();
	}
	
	protected void updatePrecedenceModules() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			List<Company> companies = companyLocalService.getCompanies();

			for (Company company : companies) {
				updatePrecedenceModules(company.getCompanyId());
			}
		}
	}

	protected void updatePrecedenceModules(long companyId)
		throws Exception {

		try (PreparedStatement ps = connection.prepareStatement(
				StringBundler.concat(
					"SELECT moduleId, precedence FROM lms_module ",
					"WHERE companyId = ", companyId, " AND precedence > 0"));
			ResultSet rs = ps.executeQuery()) {
			
			PrerequisiteRelation prerequisiteRelation = null;
			JSONObject extraData =  null;

			while (rs.next()) {
				long moduleId = rs.getLong("moduleId");
				long precedence = rs.getLong("precedence");

				prerequisiteRelation = prerequisiteRelationLocalService.addPrerequisiteRelation(PortalUtil.getClassNameId(ModulePrerequisiteFactory.class), PortalUtil.getClassNameId(Module.class), moduleId);
				
				extraData = prerequisiteRelation.getExtraDataJSON();
				extraData.put(ModulePrerequisiteConstants.JSON_MODULE_ID, precedence);
				prerequisiteRelation.setExtraData(extraData.toString());
				prerequisiteRelationLocalService.updatePrerequisiteRelation(prerequisiteRelation);
			}
		}
	}

	private final CompanyLocalService companyLocalService;
	private final PrerequisiteRelationLocalService prerequisiteRelationLocalService;
}
