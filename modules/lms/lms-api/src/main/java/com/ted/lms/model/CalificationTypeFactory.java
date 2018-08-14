package com.ted.lms.model;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import java.util.Locale;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;

import aQute.bnd.annotation.ProviderType;

/**
 * Interfaz para la factoría de actividades del LMS, tiene los métodos que son genéricos y no dependen del método de calificación de un curso en concreto,
 * como el tipo, el nombre, etc.
 * @author Virginia Martín Agudo
 *
 */
@ProviderType
public interface CalificationTypeFactory<T> {

	public CalificationType<T> getCalificationType(Course course) throws PortalException;
	
	public CalificationType<T> getCalificationType(Module module) throws PortalException;
	
	public CalificationType<T> getCalificationType(LearningActivity learningActivity) throws PortalException;

	public String getClassName();

	public long getClassNameId();

	public int getType();

	public String getTitle(Locale locale);
	
	public String getDescription(Locale locale);
	
	public String getIconCssClass();

	public PortletURL getURLSpecificContent(
			LiferayPortletResponse liferayPortletResponse,
			WindowState windowState)
		throws PortalException;
}
