package com.ted.lms.inscription.simple;

import com.liferay.portal.kernel.service.ServiceContext;
import com.ted.lms.model.BaseInscriptionType;
import com.ted.lms.model.Course;
import com.ted.lms.service.CourseLocalService;

public class SimpleInscription  extends BaseInscriptionType{

	public SimpleInscription(Course course, ServiceContext serviceContext,
			CourseLocalService courseLocalService) {
		super(course, serviceContext, courseLocalService);
	}
	
	@Override
	public String getClassName() {
		return SimpleInscription.class.getName();
	}

}
