package com.ted.lms.model;

import com.liferay.portal.kernel.upload.UploadRequest;

import javax.portlet.PortletResponse;

import aQute.bnd.annotation.ProviderType;

/**
 * Interfaz para los tipos de inscripción de los cursos
 * @author Virginia Martín Agudo
 *
 */
@ProviderType
public interface InscriptionType {

	public int getTypeId();

	public CourseResult enrollUser(long userId);
	
	public boolean unsubscribeUser(long userId);
	
	public String setExtraContent(UploadRequest uploadRequest, PortletResponse portletResponse);
	
	public InscriptionTypeFactory getInscriptionTypeFactory();
	
	public String getClassName();

}