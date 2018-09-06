package com.ted.lms.model;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.upload.UploadRequest;
import javax.portlet.PortletResponse;

import aQute.bnd.annotation.ProviderType;

/**
 * Interfaz para los métodos de evaluación de los módulos
 * @author Virginia Martín Agudo
 *
 */
@ProviderType
public interface ModuleEval {
	
	public boolean updateModule(long userId) throws SystemException;
	
	public boolean updateModule() throws SystemException;

	public void setExtraContent(UploadRequest uploadRequest, PortletResponse portletResponse);
	
	public ModuleEvalFactory getModuleEvalFactory();
	
	public String getClassName();

}