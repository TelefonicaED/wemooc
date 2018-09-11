package com.ted.lms.model;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.upload.UploadRequest;
import com.ted.lms.exception.InscriptionException;
import com.ted.lms.registry.InscriptionTypeFactoryRegistryUtil;
import com.ted.lms.service.CourseLocalService;

import javax.portlet.PortletResponse;

/**
 * Base para el tipo de inscripción a un curso
 * @author Virginia Martín Agudo
 *
 */
public abstract class BaseInscriptionType implements InscriptionType {
	
	protected Course course;
	protected ServiceContext serviceContext;

	public BaseInscriptionType(Course course, ServiceContext serviceContext, CourseLocalService courseLocalService) {
		this.course = course;
		this.serviceContext = serviceContext;
		this.courseLocalService = courseLocalService;
	}
	
	@Override
	public CourseResult enrollUser(long userId, PermissionChecker permissionChecker) throws PortalException, InscriptionException {
		return courseLocalService.enrollStudent(course, userId, serviceContext, permissionChecker);
	}
	
	@Override
	public boolean unsubscribeUser(long userId, PermissionChecker permissionChecker) throws PortalException {
		return courseLocalService.unsubscribeStudent(course, userId, permissionChecker);
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
	
	protected CourseLocalService courseLocalService;
}
