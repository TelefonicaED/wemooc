package com.ted.lms.web.internal.portlet.action;

import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskManagerUtil;
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
	immediate = true,
	property = {
		"javax.portlet.name=" + LMSPortletKeys.COURSE,
		"mvc.command.name=/courses/export_course"
	},
	service = MVCRenderCommand.class
)
public class ExportCourseMVCRenderCommand implements MVCRenderCommand {
	
	private static final Log log = LogFactoryUtil.getLog(ExportCourseMVCRenderCommand.class);
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		long courseId = ParamUtil.getLong(renderRequest, "courseId", 0);
		log.debug("courseId: " + courseId);
		log.debug("CopyCourseMVCRenderCommand redirect: " + ParamUtil.getString(renderRequest, "redirect"));
		
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		try {
			Course course = courseLocalService.getCourse(courseId);
			
			String fileName = LanguageUtil.get(themeDisplay.getLocale(), "course") + StringPool.UNDERLINE + Time.getShortTimestamp();
			
			renderRequest.setAttribute("course", course);
			renderRequest.setAttribute("fileName", fileName);
			renderRequest.setAttribute("redirect", ParamUtil.getString(renderRequest, "redirect"));
			
			return "/course_admin/export_course.jsp";
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
