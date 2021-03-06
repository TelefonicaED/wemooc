package com.ted.lms.model;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.ted.lms.registry.ModuleEvalFactoryRegistryUtil;
import com.ted.lms.service.ModuleResultLocalService;
import com.ted.lms.service.ModuleResultLocalServiceUtil;

import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;

/**
 * Base para el método de evaluación de un módulo
 * @author Virginia Martín Agudo
 *
 */
public abstract class BaseModuleEval implements ModuleEval {
	
	protected Module module;

	public BaseModuleEval(Module module, ModuleResultLocalService moduleResultLocalService) {
		this.module = module;
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
			updateModuleResult(moduleResult);
		}
		
		return true;
	}
	
	@Override
	public boolean recalculateModule() throws SystemException {
		List<ModuleResult> moduleResults = ModuleResultLocalServiceUtil.getModuleResults(module.getModuleId());
		for(ModuleResult moduleResult: moduleResults) {
			recalculateModule(moduleResult);
		}
		
		return true;
	}

	@Override
	public void setExtraContent(ActionRequest actionRequest) throws PortalException{
		
	}
	
	@Override
	public void copyModule(Module oldModule, Map<Long,Long> modulesRelation, Map<Long, Long> activitiesRelation) {
		
	}
	
	protected ModuleResultLocalService moduleResultLocalService;
}
