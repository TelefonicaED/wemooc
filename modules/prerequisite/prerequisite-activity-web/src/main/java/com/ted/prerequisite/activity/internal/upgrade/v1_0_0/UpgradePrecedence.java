package com.ted.prerequisite.activity.internal.upgrade.v1_0_0;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Release;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.ReleaseLocalService;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.PortalUtil;
import com.ted.lms.model.LearningActivity;
import com.ted.prerequisite.activity.ActivityPrerequisiteFactory;
import com.ted.prerequisite.activity.internal.constants.ActivityPrerequisiteConstants;
import com.ted.prerequisite.model.PrerequisiteRelation;
import com.ted.prerequisite.service.PrerequisiteRelationLocalService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class UpgradePrecedence extends UpgradeProcess{
	
	public UpgradePrecedence(CompanyLocalService companyLocalService, PrerequisiteRelationLocalService prerequisiteRelationLocalService,
			ReleaseLocalService releaseLocalService) {
		this.companyLocalService = companyLocalService;
		this.prerequisiteRelationLocalService = prerequisiteRelationLocalService;
		this.releaseLocalService = releaseLocalService;
	}

	@Override
	protected void doUpgrade() throws Exception {
		Release release = releaseLocalService.fetchRelease("liferaylms-portlet");
		if(release != null && release.getBuildNumber() > 0) {
			updatePrecedenceActivities();
		}
	}
	
	protected void updatePrecedenceActivities() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			List<Company> companies = companyLocalService.getCompanies();

			for (Company company : companies) {
				updatePrecedenceActivities(company.getCompanyId());
			}
		}
	}

	protected void updatePrecedenceActivities(long companyId)
		throws Exception {

		try (PreparedStatement ps = connection.prepareStatement(
				StringBundler.concat(
					"SELECT actId, precedence FROM lms_learningactivity ",
					"WHERE companyId = ", companyId, " AND precedence > 0"));
			ResultSet rs = ps.executeQuery()) {
			
			PrerequisiteRelation prerequisiteRelation = null;
			JSONObject extraData =  null;

			while (rs.next()) {
				long actId = rs.getLong("actId");
				long precedence = rs.getLong("precedence");

				prerequisiteRelation = prerequisiteRelationLocalService.addPrerequisiteRelation(PortalUtil.getClassNameId(ActivityPrerequisiteFactory.class), PortalUtil.getClassNameId(LearningActivity.class), actId, "");
				
				extraData = prerequisiteRelation.getExtraDataJSON();
				extraData.put(ActivityPrerequisiteConstants.JSON_ACT_ID, precedence);
				prerequisiteRelation.setExtraData(extraData.toString());
				prerequisiteRelationLocalService.updatePrerequisiteRelation(prerequisiteRelation);
			}
		}
	}

	private final CompanyLocalService companyLocalService;
	private final PrerequisiteRelationLocalService prerequisiteRelationLocalService;
	private final ReleaseLocalService releaseLocalService;
}
