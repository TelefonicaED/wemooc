package com.ted.lms.model;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.ServiceContext;
import com.ted.lms.exception.InscriptionException;
import com.ted.lms.registry.InscriptionTypeFactoryRegistryUtil;
import com.ted.lms.service.StudentLocalService;

import javax.portlet.ActionRequest;

/**
 * Base para el tipo de inscripción a un curso
 * @author Virginia Martín Agudo
 *
 */
public abstract class BaseInscriptionType implements InscriptionType {
	
	protected Course course;
	protected ServiceContext serviceContext;

	public BaseInscriptionType(Course course, ServiceContext serviceContext, StudentLocalService studentLocalService) {
		this.course = course;
		this.serviceContext = serviceContext;
		this.studentLocalService = studentLocalService;
	}
	
	@Override
	public CourseResult enrollUser(long userId, PermissionChecker permissionChecker) throws PortalException, InscriptionException {
		return studentLocalService.enrollStudent(course, userId, serviceContext, permissionChecker);
	}
	
	@Override
	public boolean unsubscribeUser(long userId, PermissionChecker permissionChecker) throws PortalException {
		return studentLocalService.unsubscribeStudent(course, userId, permissionChecker);
	}
	
	@Override
	public void setExtraContent(ActionRequest actionRequest) throws PortalException {
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
	
	protected StudentLocalService studentLocalService;
}
