package com.ted.lms.model;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.ted.lms.exception.InscriptionException;

import java.util.Map;

import javax.portlet.ActionRequest;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Interfaz para los tipos de inscripción de los cursos
 * @author Virginia Martín Agudo
 *
 */
@ProviderType
public interface InscriptionType {

	public CourseResult enrollUser(long userId, PermissionChecker permissionChecker) throws PortalException, InscriptionException;
	
	public boolean unsubscribeUser(long userId, PermissionChecker permissionChecker) throws PortalException;
	
	public void setExtraContent(ActionRequest actionRequest) throws PortalException;
	
	public InscriptionTypeFactory getInscriptionTypeFactory();
	
	public String getClassName();
}