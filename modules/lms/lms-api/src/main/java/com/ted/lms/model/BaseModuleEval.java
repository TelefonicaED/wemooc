package com.ted.lms.model;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.upload.UploadRequest;
import com.ted.lms.registry.ModuleEvalFactoryRegistryUtil;
import com.ted.lms.service.ModuleResultLocalServiceUtil;

import java.util.List;
import javax.portlet.PortletResponse;

/**
 * Base para el método de evaluación de un módulo
 * @author Virginia Martín Agudo
 *
 */
public abstract class BaseModuleEval<T> implements ModuleEval {
	
	private Module module;

	public BaseModuleEval(Module module) {
		this.module = module;
	}
	
	public void setModule(Module module) {
		this.module = module;
	}
	
	@Override
	public ModuleEvalFactory getModuleEvalFactory(){
		if (moduleEvalFactory != null) {
			return moduleEvalFactory;
		}

		moduleEvalFactory =
			(ModuleEvalFactory)
			ModuleEvalFactoryRegistryUtil.
					getModuleEvalFactoryByClassName(getClassName());

		return moduleEvalFactory;
	}
	
	private ModuleEvalFactory moduleEvalFactory;
	
	public boolean updateModule() throws SystemException {
		List<ModuleResult> moduleResults = ModuleResultLocalServiceUtil.getModuleResults(module.getModuleId());
		for(ModuleResult moduleResult: moduleResults) {
			updateModule(moduleResult.getUserId());
		}
		
		return true;
	}

	public void setExtraContent(UploadRequest uploadRequest, PortletResponse portletResponse) {
		
	}
	
	public void onOpenModule() throws SystemException{
		
	}
	
	public void onCloseModule() throws SystemException{
		
	}
}
