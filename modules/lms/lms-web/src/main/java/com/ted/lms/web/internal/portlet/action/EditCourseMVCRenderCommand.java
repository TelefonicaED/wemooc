package com.ted.lms.web.internal.portlet.action;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.LayoutSetPrototype;
import com.liferay.portal.kernel.model.ModelHintsUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
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
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.model.CalificationTypeFactory;
import com.ted.lms.model.Course;
import com.ted.lms.model.CourseEvalFactory;
import com.ted.lms.model.InscriptionTypeFactory;
import com.ted.lms.registry.CalificationTypeFactoryRegistryUtil;
import com.ted.lms.registry.CourseEvalFactoryRegistryUtil;
import com.ted.lms.registry.InscriptionTypeFactoryRegistryUtil;
import com.ted.lms.service.CourseLocalService;
import com.ted.lms.web.constants.LMSPortletConstants;
import com.ted.lms.web.internal.CoursesItemSelectorHelper;
import com.ted.lms.web.internal.configuration.CourseAdminPortletInstanceConfiguration;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
		immediate = true,
		property = {
			"javax.portlet.name=" + LMSPortletKeys.COURSE,
			"mvc.command.name=/courses/edit_course"
		},
		service = MVCRenderCommand.class
	)
public class EditCourseMVCRenderCommand implements MVCRenderCommand {
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		long courseId = ParamUtil.getLong(renderRequest, "courseId", 0);
		
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		if(courseId != 0) {
			Course course = courseLocalService.fetchCourse(courseId);
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
		renderRequest.setAttribute("editCourseURL", editCourseURL);
		
		String navigation = ParamUtil.getString(renderRequest, "navigation", LMSPortletConstants.EDIT_COURSE_DEFAULT_NAVIGATION);
		System.out.println("navigation render: " + navigation);
		
		CourseAdminPortletInstanceConfiguration configuration = null;
		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();
		
		try {
			configuration = portletDisplay.getPortletInstanceConfiguration(CourseAdminPortletInstanceConfiguration.class);
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		
		switch (navigation) {
			case LMSPortletConstants.EDIT_COURSE_DESCRIPTION: renderDescription(renderRequest, renderResponse, configuration);
			case LMSPortletConstants.EDIT_COURSE_CONFIGURATION: renderConfiguration(renderRequest, renderResponse);
			case LMSPortletConstants.EDIT_COURSE_MESSAGES: renderMessages(renderRequest, renderResponse);
		}
				
		renderRequest.setAttribute("navigation", navigation);
		renderRequest.setAttribute("redirect", ParamUtil.getString(renderRequest, "redirect"));
		renderRequest.setAttribute("cmd", courseId > 0 ? Constants.UPDATE :  Constants.ADD);
		renderRequest.setAttribute("configuration", configuration);
		renderRequest.setAttribute(CourseAdminPortletInstanceConfiguration.class.getName(), configuration);
		
		return "/course_admin/edit_course.jsp";
	}
	
	private void renderDescription(RenderRequest renderRequest, RenderResponse renderResponse, CourseAdminPortletInstanceConfiguration configuration) {
		
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		//Para cargar la imagen
		String smallImageSelectedItemEventName = renderResponse.getNamespace() + "smallImageSelectedItem";
		RequestBackedPortletURLFactory requestBackedPortletURLFactory = RequestBackedPortletURLFactoryUtil.create(renderRequest);
		String itemSelectorURL = coursesItemSelectorHelper.getItemSelectorURL(requestBackedPortletURLFactory, themeDisplay, smallImageSelectedItemEventName);
	
		List<LayoutSetPrototype> listLayoutSetPrototype = new ArrayList<LayoutSetPrototype>();
		String[] courseTemplates = configuration != null && Validator.isNotNull(configuration.courseTemplateValues()) ? configuration.courseTemplateValues().split(",") : null;
		for(String courseTemplate: courseTemplates) {
			try {
				listLayoutSetPrototype.add(layoutSetPrototypeLocalService.getLayoutSetPrototype(Long.parseLong(courseTemplate)));
			} catch (NumberFormatException | PortalException e) {
				e.printStackTrace();
			}
		}
		
		renderRequest.setAttribute("listLayoutSetPrototype", listLayoutSetPrototype);
		renderRequest.setAttribute("smallImageSelectedItemEventName", smallImageSelectedItemEventName);
		renderRequest.setAttribute("itemSelectorURL", itemSelectorURL);
		renderRequest.setAttribute("friendlyURLMaxLength", String.valueOf(ModelHintsUtil.getMaxLength(Group.class.getName(), "friendlyURL")));
	
	}
	
	private void renderConfiguration(RenderRequest renderRequest, RenderResponse renderResponse) {
		
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		//Métodos de evaluación
		List<CourseEvalFactory> listCourseEvalFactory = CourseEvalFactoryRegistryUtil.getCourseEvalFactories(themeDisplay.getCompanyId());
		renderRequest.setAttribute("listCourseEvalFactory", listCourseEvalFactory);
		
		List<CalificationTypeFactory> listCalificationTypeFactory = CalificationTypeFactoryRegistryUtil.getCalificationFactories(themeDisplay.getCompanyId());
		renderRequest.setAttribute("listCalificationTypeFactory", listCalificationTypeFactory);
		
		List<InscriptionTypeFactory> listInscriptionTypeFactory = InscriptionTypeFactoryRegistryUtil.getInscriptionFactories(themeDisplay.getCompanyId());
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
	
	@Reference
	private TrashHelper trashHelper;
	
	@Reference(unbind = "-")
	public void setItemSelectorHelper(CoursesItemSelectorHelper coursesItemSelectorHelper) {
		this.coursesItemSelectorHelper = coursesItemSelectorHelper;
	}

	private CoursesItemSelectorHelper coursesItemSelectorHelper;

}
