package com.ted.prerequisite.module;

import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.xml.Element;
import com.ted.lms.model.ModuleResult;
import com.ted.lms.service.ModuleResultLocalService;
import com.ted.prerequisite.model.BasePrerequisite;
import com.ted.prerequisite.model.PrerequisiteRelation;
import com.ted.prerequisite.module.internal.constants.ModulePrerequisiteConstants;
import com.ted.prerequisite.service.PrerequisiteRelationLocalService;

import javax.portlet.PortletRequest;

public class ModulePrerequisite extends BasePrerequisite{

	public ModulePrerequisite(PrerequisiteRelation prerequisiteRelation, ModuleResultLocalService moduleResultLocalService, 
								PrerequisiteRelationLocalService prerequisiteRelationLocalService) {
		super(prerequisiteRelation, prerequisiteRelationLocalService);
		this.moduleResultLocalService = moduleResultLocalService;
	}
	
	public ModulePrerequisite(long classNameId, long classPK, ModuleResultLocalService moduleResultLocalService, 
				PrerequisiteRelationLocalService prerequisiteRelationLocalService) {
		super(classNameId, classPK, prerequisiteRelationLocalService);
		this.moduleResultLocalService = moduleResultLocalService;
		if(prerequisiteRelation == null) {
			prerequisiteRelation = prerequisiteRelationLocalService.getPrerequisiteRelation(PortalUtil.getClassNameId(ModulePrerequisiteFactory.class), classNameId, classPK);
		}
	}

	@Override
	public String getClassName() {
		return ModulePrerequisite.class.getName();
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
			System.out.println("prerequisiteRelationLocalService: " + prerequisiteRelationLocalService);
			prerequisiteRelation = prerequisiteRelationLocalService.addPrerequisiteRelation(PortalUtil.getClassNameId(ModulePrerequisiteFactory.class), classNameId, classPK);
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
	
	private ModuleResultLocalService moduleResultLocalService;

}
