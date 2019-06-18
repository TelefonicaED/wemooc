package com.ted.lms.web.internal.portlet.action;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.LayoutSetPrototype;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.LayoutSetPrototypeLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.trash.TrashHelper;
import com.ted.lms.configuration.CourseServiceConfiguration;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.model.CalificationTypeFactory;
import com.ted.lms.model.CourseEvalFactory;
import com.ted.lms.model.CourseType;
import com.ted.lms.model.InscriptionTypeFactory;
import com.ted.lms.model.LearningActivityTypeFactory;
import com.ted.lms.registry.CalificationTypeFactoryRegistryUtil;
import com.ted.lms.registry.CourseEvalFactoryRegistryUtil;
import com.ted.lms.registry.InscriptionTypeFactoryRegistryUtil;
import com.ted.lms.registry.LearningActivityTypeFactoryRegistryUtil;
import com.ted.lms.service.CourseLocalService;
import com.ted.lms.service.CourseTypeLocalService;
import com.ted.lms.service.CourseTypeRelationLocalService;
import com.ted.lms.web.internal.CourseTypeItemSelectorHelper;
import com.ted.lms.web.internal.util.CourseUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

@Component(
	configurationPid = "com.ted.lms.configuration.CourseServiceConfiguration",
	immediate = true,
	property = {
		"javax.portlet.name=" + LMSPortletKeys.COURSE_TYPE,
		"mvc.command.name=/course_type/edit_course_type"
	},
	service = MVCRenderCommand.class
)
public class EditCourseTypeMVCRenderCommand implements MVCRenderCommand {
	
	private static final Log log = LogFactoryUtil.getLog(EditCourseTypeMVCRenderCommand.class);
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		long courseTypeId = ParamUtil.getLong(renderRequest, "courseTypeId", 0);
		log.debug("courseId: " + courseTypeId);
		
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		PortletURL editCourseTypeURL = renderResponse.createActionURL();
		editCourseTypeURL.setParameter("javax.portlet.action", "/course_type/edit_course_type");
		editCourseTypeURL.setParameter("courseTypeId", String.valueOf(courseTypeId));
		renderRequest.setAttribute("editCourseTypeURL", editCourseTypeURL);
		
		PortletURL backURL = renderResponse.createRenderURL();
		backURL.setParameter("mvcRenderCommandName", "/course_type/view");
		renderRequest.setAttribute("backURL", backURL);
		
		
		List<LearningActivityTypeFactory> learningActivityTypeFactories = CourseUtil.getLearningActivityTypeFactories(configuration, themeDisplay.getCompanyId());
		List<CourseEvalFactory> courseEvalFactories = CourseUtil.getCourseEvalFactories(configuration, themeDisplay.getCompanyId());
		List<CalificationTypeFactory> calificationTypeFactories = CourseUtil.getCalificationTypeFactories(configuration, themeDisplay.getCompanyId());
		List<InscriptionTypeFactory> inscriptionTypeFactories = CourseUtil.getInscriptionTypeFactories(configuration, themeDisplay.getCompanyId());
		List<LayoutSetPrototype> listLayoutSetPrototype = CourseUtil.getTemplates(configuration, themeDisplay.getCompanyId());
		
		String iconSelectedItemEventName = renderResponse.getNamespace() + "iconSelectedItem";
		RequestBackedPortletURLFactory requestBackedPortletURLFactory = RequestBackedPortletURLFactoryUtil.create(renderRequest);
		String itemSelectorURL = courseTypeItemSelectorHelper.getItemSelectorURL(requestBackedPortletURLFactory, themeDisplay, iconSelectedItemEventName);

		try {
			
			if(courseTypeId > 0) {
				CourseType courseType = courseTypeLocalService.getCourseType(courseTypeId);
				renderRequest.setAttribute("courseType", courseType);
				
				//Recuperamos los seleccionados
				List<Long> courseTemplateIds = courseTypeRelationLocalService.getTemplateIds(courseTypeId);
				List<Long> courseEvalIds = courseTypeRelationLocalService.getCourseEvalIds(courseTypeId);
				List<Long> learningActivityTypeIds = courseTypeRelationLocalService.getLearningActivityTypeIds(courseTypeId);
				List<Long> inscriptionTypeIds = courseTypeRelationLocalService.getInscriptionTypeIds(courseTypeId);
				List<Long> calificationTypeIds = courseTypeRelationLocalService.getCalificationTypeIds(courseTypeId);
				
				renderRequest.setAttribute("courseTemplateIds", courseTemplateIds);
				renderRequest.setAttribute("courseEvalTypeIds", courseEvalIds);
				renderRequest.setAttribute("activityIds", learningActivityTypeIds);
				renderRequest.setAttribute("inscriptionTypeIds", inscriptionTypeIds);
				renderRequest.setAttribute("calificationTypeIds", calificationTypeIds);
				renderRequest.setAttribute(Constants.CMD, Constants.UPDATE);
			}else {
				renderRequest.setAttribute(Constants.CMD, Constants.ADD);
			}
			
			renderRequest.setAttribute("learningActivityTypeFactories", learningActivityTypeFactories);
			renderRequest.setAttribute("courseEvalFactories", courseEvalFactories);
			renderRequest.setAttribute("calificationTypeFactories", calificationTypeFactories);
			renderRequest.setAttribute("inscriptionTypeFactories", inscriptionTypeFactories);
			renderRequest.setAttribute("listLayoutSetPrototype", listLayoutSetPrototype);
			renderRequest.setAttribute("itemSelectorURL", itemSelectorURL);
			renderRequest.setAttribute("iconSelectedItemEventName", iconSelectedItemEventName);
			
			return "/course_type/edit_course_type.jsp";
		} catch(PortalException e) {
			e.printStackTrace();
			return "/error.jsp";
		}
	}


	@Reference(unbind = "-")
	protected void setCourseLocalService(CourseLocalService courseLocalService) {
		this.courseLocalService = courseLocalService;
	}
	
	private CourseLocalService courseLocalService;

	@Reference(unbind = "-")
	protected void setCourseTypeLocalService(CourseTypeLocalService courseTypeLocalService) {
		this.courseTypeLocalService = courseTypeLocalService;
	}
	
	private CourseTypeLocalService courseTypeLocalService;
	
	@Reference(unbind = "-")
	protected void setCourseTypeRelationLocalService(CourseTypeRelationLocalService courseTypeRelationLocalService) {
		this.courseTypeRelationLocalService = courseTypeRelationLocalService;
	}
	
	private CourseTypeRelationLocalService courseTypeRelationLocalService;
	
	@Reference(unbind = "-")
	protected void setLayoutSetPrototypeLocalService(LayoutSetPrototypeLocalService layoutSetPrototypeLocalService) {
		this.layoutSetPrototypeLocalService = layoutSetPrototypeLocalService;
	}
	
	private LayoutSetPrototypeLocalService layoutSetPrototypeLocalService;
	
	@Reference
	private TrashHelper trashHelper;
	
	@Reference(unbind = "-")
	public void setItemSelectorHelper(CourseTypeItemSelectorHelper courseTypeItemSelectorHelper) {
		this.courseTypeItemSelectorHelper = courseTypeItemSelectorHelper;
	}

	private CourseTypeItemSelectorHelper courseTypeItemSelectorHelper;
	
	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
	    configuration = ConfigurableUtil.createConfigurable(CourseServiceConfiguration.class, properties);
	}

	private volatile CourseServiceConfiguration configuration;

}
