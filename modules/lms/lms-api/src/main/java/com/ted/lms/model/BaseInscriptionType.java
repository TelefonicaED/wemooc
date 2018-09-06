package com.ted.lms.model;

import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.upload.UploadRequest;
import com.ted.lms.registry.InscriptionTypeFactoryRegistryUtil;
import com.ted.lms.service.CourseResultLocalService;

import javax.portlet.PortletResponse;

/**
 * Base para el tipo de inscripción a un curso
 * @author Virginia Martín Agudo
 *
 */
public abstract class BaseInscriptionType implements InscriptionType {
	
	protected Course course;
	protected ServiceContext serviceContext;

	public BaseInscriptionType(Course course, ServiceContext serviceContext, CourseResultLocalService courseResultLocalService) {
		this.course = course;
		this.serviceContext = serviceContext;
		this.courseResultLocalService = courseResultLocalService;
	}
	
	public CourseResult enrollUser(long userId) {
		return courseResultLocalService.enrollStudent(course, userId);
	}
	
	public boolean unsubscribeUser(long userId) {
		return courseResultLocalService.unsubscribeStudent(course, userId);
	}
	
	
	@Override
	public String setExtraContent(UploadRequest uploadRequest, PortletResponse portletResponse) {
		return null;
	}
	
	@Override
	public InscriptionTypeFactory getInscriptionTypeFactory(){
		if (calificationTypeFactory != null) {
			return calificationTypeFactory;
		}

		calificationTypeFactory =
			(InscriptionTypeFactory)
			InscriptionTypeFactoryRegistryUtil.
					getInscriptionTypeFactoryByClassName(getClassName());

		return calificationTypeFactory;
	}
	
	private InscriptionTypeFactory calificationTypeFactory;
	
	private CourseResultLocalService courseResultLocalService;
}
