package com.ted.lms.internal.upgrade.v1_0_0;

import com.liferay.portal.kernel.model.Release;
import com.liferay.portal.kernel.service.ReleaseLocalServiceUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

public class UpgradeSchema extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		//Comprobamos si se está migrando o no
		Release release = ReleaseLocalServiceUtil.fetchRelease("liferaylms-portlet");
		if(release != null && release.getBuildNumber() > 0) {
			//Actualizamos la tabla de curso
			//Quitamos el campo de friendlyURL ya que es información redundante
			
			//Quitamos el campo closed y lo ponemos como status
			
			//Añadimos el campo inscriptionType
			

		}
		
	}

}
