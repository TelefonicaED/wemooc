package com.ted.lms.model;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.upload.UploadRequest;

import java.util.Locale;

import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;
import aQute.bnd.annotation.ProviderType;

/**
 * Interfaz para la factoría de los métodos de evaluación de los módulos, tiene los métodos que son genéricos y no dependen del método de evaluación 
 * de un módulo en concreto, como el tipo, el nombre, etc.
 * @author Virginia Martín Agudo
 *
 */
@ProviderType
public interface ModuleEvalFactory {

	public ModuleEval getModuleEval(Module module, ServiceContext serviceContext) throws PortalException;

	public String getClassName();

	public long getClassNameId();

	public long getType();

	public String getTitle(Locale locale);
	
	public String getDescription(Locale locale);
	
	public String getIconCssClass();

	public PortletURL getURLSpecificContent();
	
	public String getPortletId();
	
	public boolean specificValidations(UploadRequest uploadRequest,PortletResponse portletResponse);
}
