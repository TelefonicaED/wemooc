package com.ted.lms.model;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.upload.UploadRequest;

import java.util.Locale;

import javax.portlet.PortletResponse;
import aQute.bnd.annotation.ProviderType;

/**
 * Interfaz para la factoría de los métodos de evaluación de los cursos, tiene los métodos que son genéricos y no dependen del método de evaluación de un curso en concreto,
 * como el tipo, el nombre, etc.
 * @author Virginia Martín Agudo
 *
 */
@ProviderType
public interface CourseEvalFactory {

	public CourseEval getCourseEval(Course course) throws PortalException;

	public String getClassName();

	public long getClassNameId();

	public long getType();

	public String getTitle(Locale locale);
	
	public String getDescription(Locale locale);
	
	public String getIconCssClass();

	public String getURLSpecificContent();
	
	public String getPortletId();
	
	public boolean getNeedPassPuntuation();
	
	public boolean specificValidations(UploadRequest uploadRequest,PortletResponse portletResponse);
}
