package com.ted.lms.web.internal.portlet.action;

import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.LayoutSetPrototype;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.LayoutSetPrototypeLocalService;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.WebKeys;
import com.ted.lms.configuration.CourseServiceConfiguration;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.model.Course;
import com.ted.lms.service.CourseLocalService;
import com.ted.lms.service.CourseTypeRelationLocalService;
import com.ted.lms.web.internal.configuration.CourseAdminPortletInstanceConfiguration;
import com.ted.lms.web.internal.util.CourseUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.portlet.PortletException;
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
		"mvc.command.name=/courses/copy_course"
	},
	service = MVCRenderCommand.class
)
public class CopyCourseMVCRenderCommand implements MVCRenderCommand {
	
	private static final Log log = LogFactoryUtil.getLog(CopyCourseMVCRenderCommand.class);
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		long courseId = ParamUtil.getLong(renderRequest, "courseId", 0);
		log.debug("courseId: " + courseId);
		log.debug("CopyCourseMVCRenderCommand redirect: " + ParamUtil.getString(renderRequest, "redirect"));
		
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		try {
			Course course = courseLocalService.getCourse(courseId);
			
			CourseAdminPortletInstanceConfiguration portletInstanceConfiguration = null;
			PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();
			
			try {
				portletInstanceConfiguration = portletDisplay.getPortletInstanceConfiguration(CourseAdminPortletInstanceConfiguration.class);
			} catch (ConfigurationException e) {
				e.printStackTrace();
			}
			
			List<LayoutSetPrototype> listLayoutSetPrototype = null;
			
			if(course.getCourseTypeId() > 0) {
				listLayoutSetPrototype = new ArrayList<LayoutSetPrototype>();
				List<Long> layoutSetPrototypeIds = courseTypeRelationLocalService.getTemplateIds(course.getCourseTypeId());
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
			
			Map<Locale, String> newTitleMap = course.getTitleMap();

			for (Map.Entry<Locale, String> entry : newTitleMap.entrySet()) {
				Locale locale = entry.getKey();

				StringBundler sb = new StringBundler(5);

				sb.append(entry.getValue());
				sb.append(StringPool.SPACE);
				sb.append(LanguageUtil.get(locale, "duplicate"));
				sb.append(StringPool.SPACE);
				sb.append(Time.getShortTimestamp());

				newTitleMap.put(locale, sb.toString());
			}
			
			String newTitle = LocalizationUtil.updateLocalization(newTitleMap, "", "Title", LocaleUtil.toLanguageId(LocaleUtil.getSiteDefault()));
			
			renderRequest.setAttribute("course", course);
			renderRequest.setAttribute("newTitle", newTitle);
			renderRequest.setAttribute("listLayoutSetPrototype", listLayoutSetPrototype);
			renderRequest.setAttribute("redirect", ParamUtil.getString(renderRequest, "redirect"));
			
			return "/course_admin/copy_course.jsp";
		} catch (PortalException e) {
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
	
	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
	    configuration = ConfigurableUtil.createConfigurable(CourseServiceConfiguration.class, properties);
	}

	private volatile CourseServiceConfiguration configuration;

}
