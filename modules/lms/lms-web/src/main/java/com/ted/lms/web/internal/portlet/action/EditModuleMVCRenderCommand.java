package com.ted.lms.web.internal.portlet.action;

import com.liferay.portal.kernel.bean.BeanParamUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.ModelHintsUtil;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.persistence.PortletUtil;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.taglib.ui.JournalArticleTag;
import com.liferay.trash.service.TrashEntryService;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.model.Course;
import com.ted.lms.model.Module;
import com.ted.lms.model.ModuleEvalFactory;
import com.ted.lms.registry.ModuleEvalFactoryRegistryUtil;
import com.ted.lms.service.CourseLocalService;
import com.ted.lms.service.ModuleLocalService;
import com.ted.lms.util.LMSPrefsPropsValues;
import com.ted.lms.web.internal.ModulesItemSelectorHelper;
import com.ted.lms.web.internal.servlet.taglib.ui.FormNavigatorConstants;
import com.ted.lms.web.internal.util.LMSWebKeys;
import com.ted.prerequisite.model.PrerequisiteFactory;
import com.ted.prerequisite.registry.PrerequisiteFactoryRegistryUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.portlet.PortletConfig;
import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + LMSPortletKeys.MODULE,
		"mvc.command.name=/modules/edit_module"
	},
	service = MVCRenderCommand.class
)
public class EditModuleMVCRenderCommand implements MVCRenderCommand {
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		String redirect = ParamUtil.getString(renderRequest, "redirect");

		Module module = (Module)renderRequest.getAttribute("MODULE");

		long moduleId = BeanParamUtil.getLong(module, renderRequest, "moduleId");
		
		if(module == null && moduleId > 0) {
			module = moduleLocalService.fetchModule(moduleId);
		}
		
		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		portletDisplay.setShowBackIcon(true);
		portletDisplay.setURLBack(redirect);
		
		PortletConfig portletConfig = (PortletConfig) renderRequest.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG);

		boolean portletTitleBasedNavigation = GetterUtil.getBoolean(portletConfig.getInitParameter("portlet-title-based-navigation"));
		
		ResourceBundle resourceBundle = resourceBundleLoader.loadResourceBundle(themeDisplay.getLocale());

		if (portletTitleBasedNavigation) {
			renderResponse.setTitle((module != null) ? module.getTitle(themeDisplay.getLocale()) : LanguageUtil.get(resourceBundle, "new-module"));
		}

		String cmd = moduleId > 0 ? Constants.ADD : Constants.UPDATE;
		
		Course course = courseLocalService.getCourseByGroupCreatedId(themeDisplay.getScopeGroupId());
		
		//Pedimos los métodos de evaluación
		List<ModuleEvalFactory> listModuleEvalFactories = ModuleEvalFactoryRegistryUtil.getModuleEvalFactories(themeDisplay.getCompanyId());
		
		//Enviamos el tiempo permitido
		if(module != null) {
			long timeAllowed = module.getAllowedTime();
			renderRequest.setAttribute("allowedHours", timeAllowed / 3600000);
			renderRequest.setAttribute("allowedMinutes", (timeAllowed % 3600000) / 60000);
		}
		
		String smallImageSelectedItemEventName = renderResponse.getNamespace() + "smallImageSelectedItem";
		RequestBackedPortletURLFactory requestBackedPortletURLFactory = RequestBackedPortletURLFactoryUtil.create(renderRequest);
		String itemSelectorURL = modulesItemSelectorHelper.getItemSelectorURL(requestBackedPortletURLFactory, themeDisplay, smallImageSelectedItemEventName);
		
		String[] classNamePrerequisites = LMSPrefsPropsValues.getPrerequisitesOfModule(themeDisplay.getCompanyId());
		
		PrerequisiteFactory prerequisiteFactory = null;
		
		List<PrerequisiteFactory> listPrerequisiteFactory = new ArrayList<PrerequisiteFactory>();
		
		for(String classNamePrerequisite: classNamePrerequisites){
			prerequisiteFactory = PrerequisiteFactoryRegistryUtil.getPrerequisiteFactoryByClassName(classNamePrerequisite);
			if(prerequisiteFactory != null) {
				listPrerequisiteFactory.add(prerequisiteFactory);
			}
		}
		
		renderRequest.setAttribute("module", module);
		renderRequest.setAttribute("cmd", cmd);
		renderRequest.setAttribute("moduleId", moduleId);
		renderRequest.setAttribute("defaultLanguageId", LocaleUtil.getDefault().getLanguage());
		renderRequest.setAttribute("formNavigatorId", FormNavigatorConstants.FORM_NAVIGATOR_ID_MODULE);	
		renderRequest.setAttribute("course", course);
		renderRequest.setAttribute("listModuleEvalFactories", listModuleEvalFactories);
		renderRequest.setAttribute("redirect", redirect);
		renderRequest.setAttribute("smallImageSelectedItemEventName", smallImageSelectedItemEventName);
		renderRequest.setAttribute("itemSelectorURL", itemSelectorURL);
		renderRequest.setAttribute("listPrerequisiteFactory", listPrerequisiteFactory);
		
		return "/modules/edit_module.jsp";
	}
	
	@Reference(unbind = "-")
	protected void setCourseLocalService(CourseLocalService courseLocalService) {
		this.courseLocalService = courseLocalService;
	}
	
	private CourseLocalService courseLocalService;
	
	@Reference(unbind = "-")
	protected void setModuleLocalService(ModuleLocalService moduleLocalService) {
		this.moduleLocalService = moduleLocalService;
	}
	
	private ModuleLocalService moduleLocalService;
	
	@Reference(
		unbind = "-"
	)
	public void setResourceBundleLoader(ResourceBundleLoader resourceBundleLoader) {

		this.resourceBundleLoader = resourceBundleLoader;
	}

	private ResourceBundleLoader resourceBundleLoader;
	
	@Reference(unbind = "-")
	public void setItemSelectorHelper(ModulesItemSelectorHelper modulesItemSelectorHelper) {
		this.modulesItemSelectorHelper = modulesItemSelectorHelper;
	}

	private ModulesItemSelectorHelper modulesItemSelectorHelper;

}
