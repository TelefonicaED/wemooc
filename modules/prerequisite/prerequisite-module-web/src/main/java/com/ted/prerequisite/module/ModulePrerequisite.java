package com.ted.prerequisite.module;

import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.xml.Element;
import com.ted.lms.model.Module;
import com.ted.lms.model.ModuleResult;
import com.ted.lms.service.ModuleLocalService;
import com.ted.lms.service.ModuleResultLocalService;
import com.ted.prerequisite.model.BasePrerequisite;
import com.ted.prerequisite.model.PrerequisiteFactory;
import com.ted.prerequisite.model.PrerequisiteRelation;
import com.ted.prerequisite.module.internal.constants.ModulePrerequisiteConstants;
import com.ted.prerequisite.registry.PrerequisiteFactoryRegistryUtil;
import com.ted.prerequisite.service.PrerequisiteRelationLocalService;

import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletRequest;

public class ModulePrerequisite extends BasePrerequisite{
	
	private static final Log log = LogFactoryUtil.getLog(ModulePrerequisite.class);

	public ModulePrerequisite(PrerequisiteRelation prerequisiteRelation, ModuleResultLocalService moduleResultLocalService, 
								PrerequisiteRelationLocalService prerequisiteRelationLocalService, ModuleLocalService moduleLocalService) {
		super(prerequisiteRelation, prerequisiteRelationLocalService);
		this.moduleResultLocalService = moduleResultLocalService;
		this.moduleLocalService = moduleLocalService;
	}
	
	@Override
	public String getTitle(Locale locale) {
		JSONObject extraData = prerequisiteRelation.getExtraDataJSON();
		long moduleId = extraData.getLong(ModulePrerequisiteConstants.JSON_MODULE_ID);
		
		String title  = "";
		
		try {
			Module module = moduleLocalService.getModule(moduleId);
			title = module.getTitle(locale);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return title;
	}

	@Override
	public String getClassName() {
		return ModulePrerequisite.class.getName();
	}
	
	@Override
	public PrerequisiteFactory getPrerequisiteFactory(){
		if (prerequisiteFactory != null) {
			return prerequisiteFactory;
		}
		
		prerequisiteFactory = PrerequisiteFactoryRegistryUtil.getPrerequisiteFactoryByClassName(ModulePrerequisiteFactory.class.getName());

		return prerequisiteFactory;
	}

	@Override
	public boolean isPassed(long userId) {
		JSONObject extraData = prerequisiteRelation.getExtraDataJSON();
		long moduleId = extraData.getLong(ModulePrerequisiteConstants.JSON_MODULE_ID);
		
		ModuleResult moduleResult = moduleResultLocalService.getModuleResult(moduleId, userId);
		
		return moduleResult != null && moduleResult.isPassed();
	}

	@Override
	public void setExtraContent(PortletRequest request) throws PortalException{
		long moduleId = ParamUtil.getLong(request, "prerequisiteModuleId", 0);
		
		if(moduleId > 0 && prerequisiteRelation == null) {
			log.debug("prerequisiteRelationLocalService: " + prerequisiteRelationLocalService);
			prerequisiteRelation = prerequisiteRelationLocalService.addPrerequisiteRelation(PortalUtil.getClassNameId(ModulePrerequisiteFactory.class), classNameId, classPK, "");
		}
		
		if(moduleId > 0) {
			JSONObject extraData = prerequisiteRelation.getExtraDataJSON();
			extraData.put(ModulePrerequisiteConstants.JSON_MODULE_ID, moduleId);
			prerequisiteRelation.setExtraData(extraData.toString());
			prerequisiteRelationLocalService.updatePrerequisiteRelation(prerequisiteRelation);
		}else if(moduleId == 0 && prerequisiteRelation != null){
			prerequisiteRelationLocalService.deletePrerequisiteRelation(prerequisiteRelation);
			prerequisiteRelation = null;
		}
	}

	//Debemos cambiar la referencia por la nueva
	@Override
	public String doImportStagedModel(PortletDataContext portletDataContext, Element element) {
		return "";
	}
	
	@Override
	public void updatePrerequisiteCopied(long classNameId, Object ... params) {
		if(classNameId == PortalUtil.getClassNameId(Module.class) && params != null && params.length > 0 && params[0] instanceof Map<?,?>) {
			Map<Long,Long> modulesRelation = (Map<Long,Long>)params[0];
			
			JSONObject extraData = prerequisiteRelation.getExtraDataJSON();
			extraData.put(ModulePrerequisiteConstants.JSON_MODULE_ID, modulesRelation.get(extraData.getLong(ModulePrerequisiteConstants.JSON_MODULE_ID)));
			
			prerequisiteRelation.setExtraData(extraData.toJSONString());
			
			prerequisiteRelation = prerequisiteRelationLocalService.updatePrerequisiteRelation(prerequisiteRelation);
		}
	}
	
	private ModuleResultLocalService moduleResultLocalService;
	private ModuleLocalService moduleLocalService;

}
