package com.ted.prerequisite.module;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.ted.lms.service.ModuleLocalService;
import com.ted.lms.service.ModuleResultLocalService;
import com.ted.prerequisite.model.BasePrerequisiteFactory;
import com.ted.prerequisite.model.Prerequisite;
import com.ted.prerequisite.model.PrerequisiteFactory;
import com.ted.prerequisite.model.PrerequisiteRelation;
import com.ted.prerequisite.module.internal.constants.ModulePrerequisitePortletKeys;
import com.ted.prerequisite.service.PrerequisiteRelationLocalService;

import java.util.Locale;
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

/*	@Override
	public Prerequisite getPrerequisite(long classNameId, long classPK) throws PortalException {
		System.out.println("prerequisiteRelationLocalService: " + prerequisiteRelationLocalService);
		return new ModulePrerequisite(classNameId, classPK, moduleResultLocalService, prerequisiteRelationLocalService, moduleLocalService);
	}*/
	
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
