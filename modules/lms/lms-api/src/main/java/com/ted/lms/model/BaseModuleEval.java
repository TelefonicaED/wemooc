package com.ted.lms.model;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.ted.lms.registry.ModuleEvalFactoryRegistryUtil;
import com.ted.lms.service.ModuleResultLocalService;
import com.ted.lms.service.ModuleResultLocalServiceUtil;

import java.util.List;

import javax.portlet.ActionRequest;

/**
 * Base para el método de evaluación de un módulo
 * @author Virginia Martín Agudo
 *
 */
public abstract class BaseModuleEval implements ModuleEval {
	
	protected Module module;
	protected ServiceContext serviceContext;

	public BaseModuleEval(Module module, ServiceContext serviceContext, ModuleResultLocalService moduleResultLocalService) {
		this.module = module;
		this.serviceContext = serviceContext;
		this.moduleResultLocalService = moduleResultLocalService;
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
	
	@Override
	public boolean updateModule() throws SystemException {
		List<ModuleResult> moduleResults = ModuleResultLocalServiceUtil.getModuleResults(module.getModuleId());
		for(ModuleResult moduleResult: moduleResults) {
			updateModule(moduleResult.getUserId());
		}
		
		return true;
	}
	
	@Override
	public boolean recalculateModule() throws SystemException {
		List<ModuleResult> moduleResults = ModuleResultLocalServiceUtil.getModuleResults(module.getModuleId());
		for(ModuleResult moduleResult: moduleResults) {
			recalculateModule(moduleResult.getUserId());
		}
		
		return true;
	}

	@Override
	public void setExtraContent(ActionRequest actionRequest) throws PortalException{
		
	}
	
	protected ModuleResultLocalService moduleResultLocalService;
}
