package com.ted.lms.web.internal.portlet.action;

import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.ModelHintsUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.trash.TrashHelper;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.model.Course;
import com.ted.lms.service.CourseLocalService;
import com.ted.lms.util.LMSPrefsPropsValues;
import com.ted.lms.web.internal.CoursesItemSelectorHelper;

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
		
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		long courseId = ParamUtil.getLong(renderRequest, "courseId", 0);
		
		if(courseId != 0) {
			Course course = courseLocalService.fetchCourse(courseId);
			renderRequest.setAttribute("course", course);
		}
		
		PortletURL editCourseURL = renderResponse.createActionURL();
		editCourseURL.setParameter("javax.portlet.action", "/courses/edit_course");
		editCourseURL.setParameter("courseId", String.valueOf(courseId));
		renderRequest.setAttribute("editCourseURL", editCourseURL);
		
		String maxLengthTitle = GetterUtil.getString(ModelHintsUtil.getHints(Group.class.getName(), "name").get("max-length"),"");
		renderRequest.setAttribute("maxLengthTitle", maxLengthTitle);
		
		String navigation = ParamUtil.getString(renderRequest, "navigation", "description");
				
		renderRequest.setAttribute("navigation", navigation);

		PortletURL backURL = renderResponse.createRenderURL();
		renderRequest.setAttribute("backURL", backURL);
		
		LiferayPortletResponse liferayPortletResponse = PortalUtil.getLiferayPortletResponse(renderResponse);
		
		String courseImageSelectedItemEventName = liferayPortletResponse.getNamespace() + "courseImageSelectedItem";
		RequestBackedPortletURLFactory requestBackedPortletURLFactory = RequestBackedPortletURLFactoryUtil.create(renderRequest);
		String itemSelectorURL = coursesItemSelectorHelper.getItemSelectorURL(requestBackedPortletURLFactory, themeDisplay, courseImageSelectedItemEventName);

		
		renderRequest.setAttribute("itemSelectorURL", itemSelectorURL);
		renderRequest.setAttribute("courseImageSelectedItemEventName", courseImageSelectedItemEventName);
		renderRequest.setAttribute("maxFileSize", LMSPrefsPropsValues.getCourseImageMaxSize(themeDisplay.getCompanyId()));
		renderRequest.setAttribute("validExtensions", StringUtil.merge(LMSPrefsPropsValues.getCourseImageExtensions(themeDisplay.getCompanyId()), ", "));
		renderRequest.setAttribute("defaultLanguage", LocaleUtil.toLanguageId(themeDisplay.getSiteDefaultLocale()));
		renderRequest.setAttribute("friendlyURLMaxLength", String.valueOf(ModelHintsUtil.getMaxLength(Group.class.getName(), "friendlyURL")));
		
		return "/course_admin/edit_course.jsp";
	}
	
	@Reference(unbind = "-")
	protected void setCourseLocalService(CourseLocalService courseLocalService) {
		this.courseLocalService = courseLocalService;
	}
	
	private CourseLocalService courseLocalService;
	
	@Reference
	private TrashHelper trashHelper;
	
	@Reference(unbind = "-")
	public void setItemSelectorHelper(CoursesItemSelectorHelper coursesItemSelectorHelper) {
		this.coursesItemSelectorHelper = coursesItemSelectorHelper;
	}

	private CoursesItemSelectorHelper coursesItemSelectorHelper;

}
