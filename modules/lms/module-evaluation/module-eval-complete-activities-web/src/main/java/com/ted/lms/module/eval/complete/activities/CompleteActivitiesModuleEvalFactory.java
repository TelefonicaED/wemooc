package com.ted.lms.module.eval.complete.activities;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.ted.lms.model.BaseModuleEvalFactory;
import com.ted.lms.model.Module;
import com.ted.lms.model.ModuleEval;
import com.ted.lms.model.ModuleEvalFactory;
import java.util.Locale;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
    immediate = true,
    property = {},
    service = ModuleEvalFactory.class
)
public class CompleteActivitiesModuleEvalFactory extends BaseModuleEvalFactory{
	public static final long TYPE = 0;
	
	@Override
	public ModuleEval getModuleEval(Module module, ServiceContext serviceContext) throws PortalException {	
		return new CompleteActivitiesModuleEval(module, serviceContext, courseResultLocalService,
				learningActivityResultLocalService, learningActivityLocalService, 
				moduleResultLocalService);
	}

	@Override
	public String getIconCssClass() {
		return "module-eval-complete-activities";
	}

	@Override
	public String getTitle(Locale locale) {
		ResourceBundle resourceBundle = resourceBundleLoader.loadResourceBundle(locale);
		return LanguageUtil.get(resourceBundle, "module-eval.complete-activites.title");
	}

	@Override
	public String getClassName() {
		return CompleteActivitiesModuleEvalFactory.class.getName();
	}

	@Override
	public long getType() {
		return TYPE;
	}
	
	@Reference(
		unbind = "-"
	)
	public void setResourceBundleLoader(ResourceBundleLoader resourceBundleLoader) {

		this.resourceBundleLoader = resourceBundleLoader;
	}

	protected ResourceBundleLoader resourceBundleLoader;
}
