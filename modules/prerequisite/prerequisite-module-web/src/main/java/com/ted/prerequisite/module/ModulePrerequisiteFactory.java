package com.ted.prerequisite.module;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.ted.lms.service.ModuleResultLocalService;
import com.ted.prerequisite.model.BasePrerequisiteFactory;
import com.ted.prerequisite.model.Prerequisite;
import com.ted.prerequisite.model.PrerequisiteFactory;
import com.ted.prerequisite.model.PrerequisiteRelation;
import com.ted.prerequisite.module.internal.constants.ModulePrerequisitePortletKeys;
import com.ted.prerequisite.service.PrerequisiteRelationLocalService;

import java.util.Locale;
import java.util.ResourceBundle;

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
		return new ModulePrerequisite(prerequisiteRelation, moduleResultLocalService, prerequisiteRelationLocalService);
	}

	@Override
	public Prerequisite getPrerequisite(long classNameId, long classPK) throws PortalException {
		System.out.println("prerequisiteRelationLocalService: " + prerequisiteRelationLocalService);
		return new ModulePrerequisite(classNameId, classPK, moduleResultLocalService, prerequisiteRelationLocalService);
	}
	
	@Override
	public String getIconCssClass() {
		return "module";
	}
	
	@Override
	public String getTitle(Locale locale) {
		ResourceBundle resourceBundle = resourceBundleLoader.loadResourceBundle(locale);
		return LanguageUtil.get(resourceBundle, "prerequisite-module.module");
	}
	
	@Override
	public String getDescription(Locale locale) {
		ResourceBundle resourceBundle = resourceBundleLoader.loadResourceBundle(locale);
		return LanguageUtil.get(resourceBundle, "prerequisite-module.module.description");
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
	public void setResourceBundleLoader(ResourceBundleLoader resourceBundleLoader) {
		this.resourceBundleLoader = resourceBundleLoader;
	}

	protected ResourceBundleLoader resourceBundleLoader;
	
	@Reference(unbind = "-")
	protected void setModuleResultLocalService(ModuleResultLocalService moduleResultLocalService) {
		this.moduleResultLocalService = moduleResultLocalService;
	}
	
	protected ModuleResultLocalService moduleResultLocalService;
	
	@Reference(unbind = "-")
	protected void setPrerequisiteRelationLocalService(PrerequisiteRelationLocalService prerequisiteRelationLocalService) {
		this.prerequisiteRelationLocalService = prerequisiteRelationLocalService;
	}
	
	protected PrerequisiteRelationLocalService prerequisiteRelationLocalService;


}
