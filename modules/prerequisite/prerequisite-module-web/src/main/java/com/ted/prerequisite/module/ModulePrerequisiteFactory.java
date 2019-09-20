package com.ted.prerequisite.module;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.ted.lms.service.ModuleLocalService;
import com.ted.lms.service.ModuleResultLocalService;
import com.ted.prerequisite.model.BasePrerequisiteFactory;
import com.ted.prerequisite.model.Prerequisite;
import com.ted.prerequisite.model.PrerequisiteFactory;
import com.ted.prerequisite.model.PrerequisiteRelation;
import com.ted.prerequisite.module.internal.constants.ModulePrerequisitePortletKeys;
import com.ted.prerequisite.service.PrerequisiteRelationLocalService;
import com.ted.prerequisite.service.PrerequisiteRelationLocalServiceUtil;

import java.util.List;
import java.util.Locale;

import javax.portlet.PortletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
    immediate = true,
    property = {},
    service = PrerequisiteFactory.class
)
public class ModulePrerequisiteFactory extends BasePrerequisiteFactory {

	@Override
	public String getClassName() {
		return ModulePrerequisiteFactory.class.getName();
	}
	
	@Override
	public ModulePrerequisite getPrerequisite(PrerequisiteRelation prerequisiteRelation) throws PortalException {	
		return new ModulePrerequisite(prerequisiteRelation, moduleResultLocalService, prerequisiteRelationLocalService, moduleLocalService);
	}
	
	@Override
	public void savePrerequisites(long classNameId, long classPK, PortletRequest request) throws PortalException{
		
		long moduleId = ParamUtil.getLong(request, "prerequisiteModuleId", 0);
		
		List<PrerequisiteRelation> prerequisiteRelations = prerequisiteRelationLocalService.getPrerequisiteRelation(getClassNameId(), classNameId, classPK);
		
		Prerequisite prerequisite = null;
		if(moduleId > 0 && (prerequisiteRelations == null || prerequisiteRelations.size() == 0)) {
			PrerequisiteRelation prerequisiteRelation = prerequisiteRelationLocalService.addPrerequisiteRelation(PortalUtil.getClassNameId(ModulePrerequisiteFactory.class), classNameId, classPK, "");
			prerequisite = getPrerequisite(prerequisiteRelation);
			prerequisite.setExtraContent(request);
		}else if(moduleId > 0){
			prerequisite = getPrerequisite(prerequisiteRelations.get(0));
			prerequisite.setExtraContent(request);
		}else if(prerequisiteRelations != null && prerequisiteRelations.size() > 0){
			prerequisiteRelationLocalService.deletePrerequisiteRelations(PortalUtil.getClassNameId(ModulePrerequisiteFactory.class), classNameId, classPK);
		}
	}
	
	@Override
	public String getIconCssClass() {
		return "module";
	}
	
	@Override
	public String getTitle(Locale locale) {
		return LanguageUtil.get(locale, "prerequisite.module");
	}
	
	@Override
	public String getDescription(Locale locale) {
		return LanguageUtil.get(locale, "prerequisite.module.description");
	}
	
	@Override
	public String getURLSpecificContent() {
		return "/module/prerequisite/edit.jsp";
	}
	
	@Override
	public String getPortletId(){
		return  ModulePrerequisitePortletKeys.MODULE;
	}
	
	@Reference(unbind = "-")
	protected void setModuleResultLocalService(ModuleResultLocalService moduleResultLocalService) {
		this.moduleResultLocalService = moduleResultLocalService;
	}
	
	protected ModuleResultLocalService moduleResultLocalService;
	
	@Reference(unbind = "-")
	protected void setModuleLocalService(ModuleLocalService moduleLocalService) {
		this.moduleLocalService = moduleLocalService;
	}
	
	protected ModuleLocalService moduleLocalService;
	
	@Reference(unbind = "-")
	protected void setPrerequisiteRelationLocalService(PrerequisiteRelationLocalService prerequisiteRelationLocalService) {
		this.prerequisiteRelationLocalService = prerequisiteRelationLocalService;
	}
	
	protected PrerequisiteRelationLocalService prerequisiteRelationLocalService;


}
