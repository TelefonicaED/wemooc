package com.ted.lms.inscription.teams.internal.upgrade.v1_0_0;

import com.liferay.portal.kernel.model.Release;
import com.liferay.portal.kernel.service.ReleaseLocalService;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringUtil;

public class UpgradeSchema extends UpgradeProcess {
	
	public UpgradeSchema(ReleaseLocalService releaseLocalService) {
		this.releaseLocalService = releaseLocalService;
	}
	
	@Override
	protected void doUpgrade() throws Exception {
		Release release = releaseLocalService.fetchRelease("liferaylms-portlet");
		if(release != null && release.getBuildNumber() > 0) {
			String template = StringUtil.read(
					UpgradeSchema.class.getResourceAsStream("dependencies/update.sql"));

				runSQLTemplateString(template, false, false);
		}
	}
	
	private final ReleaseLocalService releaseLocalService;
}
