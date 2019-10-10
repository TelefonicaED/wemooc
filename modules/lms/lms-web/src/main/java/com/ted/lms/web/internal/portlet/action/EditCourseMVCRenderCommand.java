package com.ted.lms.web.internal.portlet.action;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.LayoutSetPrototype;
import com.liferay.portal.kernel.model.ModelHintsUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProviderUtil;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.LayoutSetPrototypeLocalService;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.trash.TrashHelper;
import com.ted.lms.configuration.CourseServiceConfiguration;
import com.ted.lms.constants.CourseConstants;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.model.CalificationTypeFactory;
import com.ted.lms.model.Course;
import com.ted.lms.model.CourseEvalFactory;
import com.ted.lms.model.InscriptionTypeFactory;
import com.ted.lms.registry.CalificationTypeFactoryRegistryUtil;
import com.ted.lms.registry.CourseEvalFactoryRegistryUtil;
import com.ted.lms.registry.InscriptionTypeFactoryRegistryUtil;
import com.ted.lms.service.CourseLocalService;
import com.ted.lms.service.CourseTypeRelationLocalService;
import com.ted.lms.web.constants.LMSPortletConstants;
import com.ted.lms.web.internal.CoursesItemSelectorHelper;
import com.ted.lms.web.internal.configuration.CourseAdminPortletInstanceConfiguration;
import com.ted.lms.web.internal.configuration.LMSWebConfiguration;
import com.ted.lms.web.internal.util.CourseUtil;

import java.util.ArrayList;
import java.util.Calendar;
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
		"javax.portlet.name=" + LMSPortletKeys.COURSE,
		"mvc.command.name=/courses/edit_course"
	},
	service = MVCRenderCommand.class
)
public class EditCourseMVCRenderCommand implements MVCRenderCommand {
	
	private static final Log log = LogFactoryUtil.getLog(EditCourseMVCRenderCommand.class);
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		long courseId = ParamUtil.getLong(renderRequest, "courseId", 0);
		log.debug("courseId: " + courseId);
		
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		String navigation = ParamUtil.getString(renderRequest, "navigation", LMSPortletConstants.EDIT_COURSE_DEFAULT_NAVIGATION);
		log.debug("navigation: " + navigation);
		log.debug("EditCourseMVCRenderCommand redirect: " + ParamUtil.getString(renderRequest, "redirect"));
		
		Course course = null;
		
		if(courseId != 0) {
			course = courseLocalService.fetchCourse(courseId);
			if(course != null && navigation.equals(LMSPortletConstants.EDIT_COURSE_CONFIGURATION)) {
				log.debug("cargamos fechas de registro y ejecuci�n");
				//Fecha de fin para registro y ejecución
				Calendar now = Calendar.getInstance();
				if(Validator.isNull(course.getRegistrationStartDate())) {
					course.setRegistrationStartDate(now.getTime());
				}
				if(Validator.isNull(course.getExecutionStartDate())) {
					course.setExecutionStartDate(now.getTime());
				}
				now.add(Calendar.YEAR, 1);
				if(Validator.isNull(course.getRegistrationEndDate())) {
					course.setRegistrationEndDate(now.getTime());
				}
				if(Validator.isNull(course.getExecutionEndDate())) {
					course.setExecutionEndDate(now.getTime());
				}
			}
			log.debug("mandamos el curso");
			renderRequest.setAttribute("course", course);
			try {
				AssetEntry assetEntry = assetEntryLocalService.getEntry(Course.class.getName(), courseId);
				renderRequest.setAttribute("assetEntry", assetEntry);
			} catch (PortalException e) {
				e.printStackTrace();
			}
		}
		
		PortletURL editCourseURL = renderResponse.createActionURL();
		editCourseURL.setParameter("javax.portlet.action", "/courses/edit_course");
		editCourseURL.setParameter("courseId", String.valueOf(courseId));
		editCourseURL.setParameter("redirect", ParamUtil.getString(renderRequest, "redirect"));
		renderRequest.setAttribute("editCourseURL", editCourseURL);
		
		log.debug("editCourseURL: " + editCourseURL.toString());
		
		CourseAdminPortletInstanceConfiguration configuration = null;
		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();
		
		try {
			configuration = portletDisplay.getPortletInstanceConfiguration(CourseAdminPortletInstanceConfiguration.class);
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		
		switch (navigation) {
			case LMSPortletConstants.EDIT_COURSE_DESCRIPTION: renderDescription(renderRequest, renderResponse, course, configuration); break;
			case LMSPortletConstants.EDIT_COURSE_CONFIGURATION: renderConfiguration(renderRequest, renderResponse, course); break;
			case LMSPortletConstants.EDIT_COURSE_MESSAGES: renderMessages(renderRequest, renderResponse);break;
		}
				
		renderRequest.setAttribute("navigation", navigation);
		renderRequest.setAttribute("redirect", ParamUtil.getString(renderRequest, "redirect"));
		renderRequest.setAttribute("cmd", courseId > 0 ? Constants.UPDATE :  Constants.ADD);
		renderRequest.setAttribute("configuration", configuration);
		renderRequest.setAttribute(CourseAdminPortletInstanceConfiguration.class.getName(), configuration);
		
		return "/course_admin/edit_course.jsp";
	}
	
	private void renderDescription(RenderRequest renderRequest, RenderResponse renderResponse, Course course, CourseAdminPortletInstanceConfiguration portletInstanceConfiguration) {
		
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		long courseTypeId = ParamUtil.getLong(renderRequest, "courseTypeId", course != null ? course.getCourseTypeId() : 0);
		
		//Para cargar la imagen
		String smallImageSelectedItemEventName = renderResponse.getNamespace() + "smallImageSelectedItem";
		RequestBackedPortletURLFactory requestBackedPortletURLFactory = RequestBackedPortletURLFactoryUtil.create(renderRequest);
		String itemSelectorURL = coursesItemSelectorHelper.getItemSelectorURL(requestBackedPortletURLFactory, themeDisplay, smallImageSelectedItemEventName);
	
		List<LayoutSetPrototype> listLayoutSetPrototype = null;
		
		if(courseTypeId > 0) {
			listLayoutSetPrototype = new ArrayList<LayoutSetPrototype>();
			List<Long> layoutSetPrototypeIds = courseTypeRelationLocalService.getTemplateIds(courseTypeId);
			for(Long layoutSetPrototypeId: layoutSetPrototypeIds) {
				try {
					listLayoutSetPrototype.add(layoutSetPrototypeLocalService.getLayoutSetPrototype(layoutSetPrototypeId));
				} catch (PortalException e) {
					e.printStackTrace();
				}
			}
		}else {	
			listLayoutSetPrototype = CourseUtil.getTemplates(portletInstanceConfiguration, configuration, themeDisplay.getCompanyId());
		}
		
		LMSWebConfiguration lmsWebConfiguration = null;
		try {
			lmsWebConfiguration = ConfigurationProviderUtil.getCompanyConfiguration(LMSWebConfiguration.class, themeDisplay.getCompanyId());
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		
		renderRequest.setAttribute("listLayoutSetPrototype", listLayoutSetPrototype);
		renderRequest.setAttribute("smallImageSelectedItemEventName", smallImageSelectedItemEventName);
		renderRequest.setAttribute("itemSelectorURL", itemSelectorURL);
		renderRequest.setAttribute("friendlyURLMaxLength", String.valueOf(ModelHintsUtil.getMaxLength(Group.class.getName(), "friendlyURL")));
		renderRequest.setAttribute("courseTypeId", courseTypeId);
		renderRequest.setAttribute("lmsWebConfiguration", lmsWebConfiguration);
	
	}
	
	private void renderConfiguration(RenderRequest renderRequest, RenderResponse renderResponse, Course course) {
		
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		//Métodos de evaluación
		List<CourseEvalFactory> listCourseEvalFactory = null;
		if(course.getCourseTypeId() > 0) {
			listCourseEvalFactory = courseTypeRelationLocalService.getCourseEvals(course.getCourseTypeId());
		}else {
			listCourseEvalFactory = CourseUtil.getCourseEvalFactories(configuration, themeDisplay.getCompanyId());
		}
		renderRequest.setAttribute("listCourseEvalFactory", listCourseEvalFactory);
		
		List<CalificationTypeFactory> listCalificationTypeFactory = null;
		if(course.getCourseTypeId() > 0) {
			listCalificationTypeFactory = courseTypeRelationLocalService.getCalificationTypes(course.getCourseTypeId());
		}else {
			listCalificationTypeFactory = CourseUtil.getCalificationTypeFactories(configuration, themeDisplay.getCompanyId());
		}
		renderRequest.setAttribute("listCalificationTypeFactory", listCalificationTypeFactory);
		
		List<InscriptionTypeFactory> listInscriptionTypeFactory = null;
		if(course.getCourseTypeId() > 0) {
			listInscriptionTypeFactory = courseTypeRelationLocalService.getInscriptionTypes(course.getCourseTypeId()); 
		}else {
			listInscriptionTypeFactory = CourseUtil.getInscriptionTypeFactories(configuration, themeDisplay.getCompanyId());
		}
		renderRequest.setAttribute("listInscriptionTypeFactory", listInscriptionTypeFactory);
		
	}
	
	private void renderMessages(RenderRequest renderRequest, RenderResponse renderResponse) {
		
	}

	@Reference(unbind = "-")
	protected void setCourseLocalService(CourseLocalService courseLocalService) {
		this.courseLocalService = courseLocalService;
	}
	
	private CourseLocalService courseLocalService;
	
	@Reference(unbind = "-")
	protected void setAssetEntryLocalService(AssetEntryLocalService assetEntryLocalService) {
		this.assetEntryLocalService = assetEntryLocalService;
	}
	
	private AssetEntryLocalService assetEntryLocalService;
	
	@Reference(unbind = "-")
	protected void setLayoutSetPrototypeLocalService(LayoutSetPrototypeLocalService layoutSetPrototypeLocalService) {
		this.layoutSetPrototypeLocalService = layoutSetPrototypeLocalService;
	}
	
	private LayoutSetPrototypeLocalService layoutSetPrototypeLocalService;
	
	@Reference(unbind = "-")
	protected void setCourseTypeRelationLocalService(CourseTypeRelationLocalService courseTypeRelationLocalService) {
		this.courseTypeRelationLocalService = courseTypeRelationLocalService;
	}
	
	private CourseTypeRelationLocalService courseTypeRelationLocalService;
	
	@Reference
	private TrashHelper trashHelper;
	
	@Reference(unbind = "-")
	public void setItemSelectorHelper(CoursesItemSelectorHelper coursesItemSelectorHelper) {
		this.coursesItemSelectorHelper = coursesItemSelectorHelper;
	}

	private CoursesItemSelectorHelper coursesItemSelectorHelper;
	
	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
	    configuration = ConfigurableUtil.createConfigurable(CourseServiceConfiguration.class, properties);
	}

	private volatile CourseServiceConfiguration configuration;

}
