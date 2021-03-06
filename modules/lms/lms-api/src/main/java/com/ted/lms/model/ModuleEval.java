package com.ted.lms.model;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.Map;

import javax.portlet.ActionRequest;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Interfaz para los métodos de evaluación de los módulos
 * @author Virginia Martín Agudo
 *
 */
@ProviderType
public interface ModuleEval {
	
	public ModuleResult updateModuleResult(ModuleResult moduleResult) throws SystemException;
	
	public boolean updateModule() throws SystemException;
	
	public ModuleResult recalculateModule(ModuleResult moduleResult) throws SystemException;
	
	public boolean recalculateModule() throws SystemException;

	public void setExtraContent(ActionRequest actionRequest) throws PortalException;
	
	public ModuleEvalFactory getModuleEvalFactory();
	
	public String getClassName();
	
	public void copyModule(Module oldModule, Map<Long,Long> modulesRelation, Map<Long, Long> activitiesRelation);

}