package com.ted.lms.web.internal.portlet.action;

import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.LayoutSetPrototype;
import com.liferay.portal.kernel.model.ModelHintsUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.LayoutSetPrototypeLocalService;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.trash.TrashHelper;
import com.ted.lms.configuration.CourseServiceConfiguration;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.model.Course;
import com.ted.lms.service.CourseLocalService;
import com.ted.lms.service.CourseTypeRelationLocalService;
import com.ted.lms.web.internal.CoursesItemSelectorHelper;
import com.ted.lms.web.internal.configuration.CourseAdminPortletInstanceConfiguration;
import com.ted.lms.web.internal.util.CourseUtil;

import java.util.ArrayList;
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
		"mvc.command.name=/courses/edit_edition"
	},
	service = MVCRenderCommand.class
)
public class AddEditionMVCRenderCommand implements MVCRenderCommand {
	
	private static final Log log = LogFactoryUtil.getLog(AddEditionMVCRenderCommand.class);
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		long parentCourseId = ParamUtil.getLong(renderRequest, "parentCourseId");
		long courseId = ParamUtil.getLong(renderRequest, "courseId", 0);
		log.debug("courseId: " + courseId);
		System.out.println("AddEditionMVCRenderCommand redirect: " + ParamUtil.getString(renderRequest, "redirect"));
		
		try {
		
			ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			
			Course edition = null;
			long courseTypeId = 0;
			
			if(courseId != 0) {
				edition = courseLocalService.getCourse(courseId);
			}else {
				Course parentCourse = courseLocalService.getCourse(parentCourseId);
				courseTypeId = parentCourse.getCourseTypeId();
				
				edition = courseLocalService.createCourse(0);
				edition.setRegistrationStartDate(parentCourse.getRegistrationStartDate());
				edition.setRegistrationEndDate(parentCourse.getRegistrationEndDate());
				edition.setExecutionStartDate(parentCourse.getExecutionStartDate());
				edition.setExecutionEndDate(parentCourse.getExecutionEndDate());
			}
				
			
			PortletURL addEditionURL = renderResponse.createActionURL();
			addEditionURL.setParameter("javax.portlet.action", "/courses/add_edition");
			addEditionURL.setParameter("parentCourseId", String.valueOf(parentCourseId));
			addEditionURL.setParameter("redirect", PortalUtil.getCurrentURL(renderRequest));
			renderRequest.setAttribute("addEditionURL", addEditionURL);
			
			CourseAdminPortletInstanceConfiguration configuration = null;
			PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();
			
			try {
				configuration = portletDisplay.getPortletInstanceConfiguration(CourseAdminPortletInstanceConfiguration.class);
			} catch (ConfigurationException e) {
				e.printStackTrace();
			}
			
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
				listLayoutSetPrototype = CourseUtil.getTemplates(configuration, this.configuration, themeDisplay.getCompanyId());
			}
			
			renderRequest.setAttribute("listLayoutSetPrototype", listLayoutSetPrototype);
	
			renderRequest.setAttribute("edition", edition);
			renderRequest.setAttribute("redirect", ParamUtil.getString(renderRequest, "redirect"));
			renderRequest.setAttribute("cmd", courseId > 0 ? Constants.UPDATE :  Constants.ADD);
			renderRequest.setAttribute("configuration", configuration);
			renderRequest.setAttribute(CourseAdminPortletInstanceConfiguration.class.getName(), configuration);
			renderRequest.setAttribute("friendlyURLMaxLength", String.valueOf(ModelHintsUtil.getMaxLength(Group.class.getName(), "friendlyURL")));
			
			return "/courses/add_edition.jsp";
		
		} catch(PortalException e) {
			e.printStackTrace();
			return "/courses/error.jsp";
		}
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
