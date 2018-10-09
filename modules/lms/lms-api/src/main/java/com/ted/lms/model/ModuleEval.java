package com.ted.lms.model;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import javax.portlet.ActionRequest;

import aQute.bnd.annotation.ProviderType;

/**
 * Interfaz para los métodos de evaluación de los módulos
 * @author Virginia Martín Agudo
 *
 */
@ProviderType
public interface ModuleEval {
	
	public ModuleResult updateModule(long userId) throws SystemException;
	
	public boolean updateModule() throws SystemException;
	
	public ModuleResult recalculateModule(long userId) throws SystemException;
	
	public boolean recalculateModule() throws SystemException;

	public void setExtraContent(ActionRequest actionRequest) throws PortalException;
	
	public ModuleEvalFactory getModuleEvalFactory();
	
	public String getClassName();

}