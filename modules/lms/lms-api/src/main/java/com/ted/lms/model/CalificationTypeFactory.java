package com.ted.lms.model;

import com.liferay.portal.kernel.upload.UploadRequest;
import java.util.Locale;

import javax.portlet.PortletResponse;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Interfaz para la factoría de los métodos de calificación, tiene los métodos que son genéricos y no dependen del método de calificación de un curso en concreto,
 * como el tipo, el nombre, etc.
 * @author Virginia Martín Agudo
 *
 */
@ProviderType
public interface CalificationTypeFactory {

	public CalificationType getCalificationType(Course course);

	public String getClassName();

	public long getClassNameId();

	public long getType();

	public String getTitle(Locale locale);
	
	public String getDescription(Locale locale);
	
	public String getIconCssClass();

	public String getURLSpecificContent();
	
	public String getURLEditResult();
	
	public String getPortletIdEditResult();
	
	public boolean specificValidations(UploadRequest uploadRequest,PortletResponse portletResponse);
	
	public String getPortletId();

}
