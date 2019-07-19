package com.ted.lms.web.internal.portlet.action;

import com.liferay.frontend.taglib.clay.servlet.taglib.util.NavigationItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.NavigationItemList;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.trash.TrashHelper;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.model.Course;
import com.ted.lms.service.CourseLocalService;
import com.ted.lms.web.internal.display.context.PostconditionsDisplayContext;
import com.ted.lms.web.internal.display.context.PrerequisitesDisplayContext;
import com.ted.postcondition.model.PostconditionFactory;
import com.ted.postcondition.registry.PostconditionFactoryRegistryUtil;
import com.ted.prerequisite.model.PrerequisiteFactory;
import com.ted.prerequisite.registry.PrerequisiteFactoryRegistryUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	configurationPid = "com.ted.lms.configuration.CourseServiceConfiguration",
	immediate = true,
	property = {
		"javax.portlet.name=" + LMSPortletKeys.COURSE,
		"mvc.command.name=/courses/prerequisite_postcondition_course"
	},
	service = MVCRenderCommand.class
)
public class ViewPrerequisitePostconditionMVCRenderCommand implements MVCRenderCommand {
	
	private static final Log log = LogFactoryUtil.getLog(ViewPrerequisitePostconditionMVCRenderCommand.class);
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		long courseId = ParamUtil.getLong(renderRequest, "courseId", 0);
		
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		log.debug("courseId: " + courseId);
		System.out.println("AssignMembersMVCRenderCommand redirect: " + ParamUtil.getString(renderRequest, "redirect"));
		
		try {
			Course course = courseLocalService.getCourse(courseId);
			renderRequest.setAttribute("course", course);
			
			String redirect = ParamUtil.getString(renderRequest, "redirect");
			renderRequest.setAttribute("redirect", redirect);
			
			//Mandamos los prerequisitos y las postcondiciones si las hay para mostrarlas en el menú de acciones
			String[] classNamePrerequisites = courseLocalService.getPrerequisiteCourses(themeDisplay.getCompanyId());
			
			PrerequisiteFactory prerequisiteFactory = null;
			
			List<PrerequisiteFactory> prerequisiteFactories = new ArrayList<PrerequisiteFactory>();
			
			for(String classNamePrerequisite: classNamePrerequisites){
				prerequisiteFactory = PrerequisiteFactoryRegistryUtil.getPrerequisiteFactoryByClassName(classNamePrerequisite);
				if(prerequisiteFactory != null) {
					prerequisiteFactories.add(prerequisiteFactory);
				}
			}
			
			String[] classNamePostconditions = courseLocalService.getPostconditionCourses(themeDisplay.getCompanyId());
			
			PostconditionFactory postconditionFactory = null;
			
			List<PostconditionFactory> postconditionFactories = new ArrayList<PostconditionFactory>();
			
			for(String classNamePostcondition: classNamePostconditions){
				postconditionFactory = PostconditionFactoryRegistryUtil.getPostconditionFactoryByClassName(classNamePostcondition);
				if(postconditionFactory != null) {
					postconditionFactories.add(postconditionFactory);
				}
			}
			
			String tabs = ParamUtil.getString(renderRequest, "tabs", "prerequisites");
			
			PortletURL portletURL = renderResponse.createRenderURL();
			portletURL.setParameter("mvcRenderCommandName", "/courses/prerequisite_postcondition_course");
			portletURL.setParameter("courseId", String.valueOf(courseId));
			portletURL.setParameter("redirect", ParamUtil.getString(renderRequest, "redirect"));
			
			
			List<NavigationItem> navigationItem =  new NavigationItemList() {
				{
					if(prerequisiteFactories.size() > 0) {
						add(
							navigationItem -> {
								navigationItem.setActive(Objects.equals(tabs, "prerequisites"));
								navigationItem.setHref(portletURL, "tabs", "prerequisites");
								navigationItem.setLabel(LanguageUtil.get(themeDisplay.getLocale(),"course-admin.prerequisites"));
							});
					}
					if(postconditionFactories.size() > 0) {
						add(
							navigationItem -> {
								navigationItem.setActive(Objects.equals(tabs, "postconditions"));
								navigationItem.setHref(portletURL, "tabs", "postconditions");
								navigationItem.setLabel(LanguageUtil.get(themeDisplay.getLocale(),"course-admin.postconditions"));
							});
					}
				}
			};themeDisplay.get
			
			HttpServletRequest request = portal.getHttpServletRequest(renderRequest);
			
			if("prerequisites".equals(tabs)) {
				PrerequisitesDisplayContext prerequisteDisplayContext = new PrerequisitesDisplayContext(request, renderRequest, renderResponse, prerequisiteFactories);			
				renderRequest.setAttribute("prerequisteDisplayContext", prerequisteDisplayContext);
			}else {
				PostconditionsDisplayContext postconditionDisplayContext = new PostconditionsDisplayContext(request, renderRequest, renderResponse, postconditionFactories);			
				renderRequest.setAttribute("postconditionDisplayContext", postconditionDisplayContext);
			}
			
			renderRequest.setAttribute("prerequisiteFactories", prerequisiteFactories);
			renderRequest.setAttribute("postconditionFactories", postconditionFactories);
			renderRequest.setAttribute("courseId", courseId);
			renderRequest.setAttribute("navigationItem", navigationItem);
			renderRequest.setAttribute("tabs", tabs);
			
			return "/courses/view_prerequisite_postcondition.jsp";
		} catch (PortalException e) {
			e.printStackTrace();
			return "/courses/error.jsp";
		}

	}
	

	@Reference(unbind = "-")
	protected void setCourseLocalService(CourseLocalService courseLocalService) {
		this.courseLocalService = courseLocalService;
	}
	
	private CourseLocalService courseLocalService;
	
	@Reference
	private TrashHelper trashHelper;

	@Reference
	private Portal portal;
}
