package com.ted.postcondition.model;

import com.liferay.portal.kernel.exception.PortalException;
import java.util.Locale;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Interfaz para la factoría de los métodos de evaluación de los cursos, tiene los métodos que son genéricos y no dependen del método de evaluación de un curso en concreto,
 * como el tipo, el nombre, etc.
 * @author Virginia Martín Agudo
 *
 */
@ProviderType
public interface PostconditionFactory {

	public Postcondition getPostcondition(PostconditionRelation postconditionRelation) throws PortalException;
	
	public Postcondition getPostcondition(long classNameId, long classPK) throws PortalException;

	public String getClassName();

	public long getClassNameId();

	public String getTitle(Locale locale);
	
	public String getDescription(Locale locale);
	
	public String getIconCssClass();

	public String getURLSpecificContent();
	
	public String getPortletId();
	
	public void copyPostcondition(long classNameId, long oldClassPK, long newClassPK, Object...params);
}
