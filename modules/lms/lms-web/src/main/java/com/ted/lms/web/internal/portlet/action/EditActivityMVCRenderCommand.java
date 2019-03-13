package com.ted.lms.web.internal.portlet.action;

import com.liferay.portal.kernel.bean.BeanParamUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.liferay.portal.kernel.util.WebKeys;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.model.Course;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.LearningActivityTypeFactory;
import com.ted.lms.model.Module;
import com.ted.lms.registry.LearningActivityTypeFactoryRegistryUtil;
import com.ted.lms.service.CourseLocalService;
import com.ted.lms.service.LearningActivityLocalService;
import com.ted.lms.service.ModuleLocalService;
import com.ted.lms.web.internal.ModulesItemSelectorHelper;
import com.ted.lms.web.internal.servlet.taglib.ui.FormNavigatorConstants;
import java.util.ResourceBundle;

import javax.portlet.PortletConfig;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + LMSPortletKeys.COURSE_CONTENT_VIEWER,
		"javax.portlet.name=" + LMSPortletKeys.MODULES_ACTIVITIES,
		"mvc.command.name=/activities/edit_activity"
	},
	service = MVCRenderCommand.class
)
public class EditActivityMVCRenderCommand implements MVCRenderCommand {
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		
		System.out.println("EditActivityMVCRenderCommand::render");
		
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		LearningActivity activity = (LearningActivity)renderRequest.getAttribute("LEARNING_ACTIVITY");

		long actId = BeanParamUtil.getLong(activity, renderRequest, "actId");
		
		System.out.println("EditActivityMVCRenderCommand::render::actId: " + actId);
		
		if(activity == null && actId > 0) {
			activity = learningActivityLocalService.fetchLearningActivity(actId);
		}
		
		long type = ParamUtil.getLong(renderRequest, "type", activity != null ? activity.getTypeId():-1);
		
		LearningActivityTypeFactory learningActivityTypeFactory = LearningActivityTypeFactoryRegistryUtil.getLearningActivityTypeFactoryByType(type);
		
		long moduleId = ParamUtil.getLong(renderRequest, "moduleId", activity != null ? activity.getModuleId() : 0);
		
		PortletConfig portletConfig = (PortletConfig) renderRequest.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG);

		boolean portletTitleBasedNavigation = GetterUtil.getBoolean(portletConfig.getInitParameter("portlet-title-based-navigation"));
		
		ResourceBundle resourceBundle = resourceBundleLoader.loadResourceBundle(themeDisplay.getLocale());

		if (portletTitleBasedNavigation) {
			renderResponse.setTitle((activity != null) ? activity.getTitle(themeDisplay.getLocale()) : LanguageUtil.get(resourceBundle, "new-activity"));
		}

		String cmd = actId > 0 ? Constants.ADD : Constants.UPDATE;
		
		Course course = courseLocalService.getCourseByGroupCreatedId(themeDisplay.getScopeGroupId());
		Module module = moduleLocalService.fetchModule(moduleId);
		
		renderRequest.setAttribute("module", module);
		renderRequest.setAttribute("cmd", cmd);
		renderRequest.setAttribute("moduleId", moduleId);
		renderRequest.setAttribute("defaultLanguageId", LocaleUtil.getDefault().getLanguage());
		renderRequest.setAttribute("formNavigatorId", FormNavigatorConstants.FORM_NAVIGATOR_ID_MODULE);	
		renderRequest.setAttribute("course", course);
		renderRequest.setAttribute("learningActivityTypeFactory", learningActivityTypeFactory);
		renderRequest.setAttribute("activity", activity);
		renderRequest.setAttribute("type", type);
		
		return "/activities/edit_activity.jsp";
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
	
	@Reference(unbind = "-")
	protected void setLearningActivityLocalService(LearningActivityLocalService learningActivityLocalService) {
		this.learningActivityLocalService = learningActivityLocalService;
	}
	
	private LearningActivityLocalService learningActivityLocalService;
	
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
