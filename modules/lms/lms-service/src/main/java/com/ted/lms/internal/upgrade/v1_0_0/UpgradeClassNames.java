package com.ted.lms.internal.upgrade.v1_0_0;

import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.ted.lms.model.CalificationTypeFactory;
import com.ted.lms.model.CourseEvalFactory;
import com.ted.lms.model.InscriptionTypeFactory;
import com.ted.lms.model.LearningActivityTypeFactory;

public class UpgradeClassNames extends UpgradeProcess {

	public UpgradeClassNames(ClassNameLocalService classNameLocalService) {
		this.classNameLocalService = classNameLocalService;
	}

	@Override
	protected void doUpgrade() throws Exception {
		classNameLocalService.addClassName(CourseEvalFactory.class.getName());
		classNameLocalService.addClassName(LearningActivityTypeFactory.class.getName());
		classNameLocalService.addClassName(InscriptionTypeFactory.class.getName());
		classNameLocalService.addClassName(CalificationTypeFactory.class.getName());
	}
	
	private final ClassNameLocalService classNameLocalService;

}
