package com.ted.lms.web.internal.portlet;

import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.model.Course;
import com.ted.lms.service.CourseLocalService;
import com.ted.lms.service.TeacherLocalService;
import java.io.IOException;
import java.util.List;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Virginia Martín Agudo
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.add-default-resource=true",
		"com.liferay.portlet.display-category=category.wemooc",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/META-INF/resources/teachers/view.jsp",
		"javax.portlet.name=" + LMSPortletKeys.TEACHERS,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=guest,user",
	},
	service = Portlet.class
)
public class TeachersPortlet extends MVCPortlet {
	
	private static final Log log = LogFactoryUtil.getLog(InscriptionPortlet.class);
	
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
		ThemeDisplay themeDisplay  =(ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		Course course = courseLocalService.getCourseByGroupCreatedId(themeDisplay.getScopeGroupId());
		
		if(course != null) {
			
			try {
				List<User> teachers = teacherLocalService.getTeachersFromCourse(course.getCourseId(), course.getCompanyId(), null, WorkflowConstants.STATUS_APPROVED, 
						null, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
				
				renderRequest.setAttribute("teachers", teachers);
				
				if(teachers == null || teachers.size() == 0) {
					renderRequest.setAttribute(WebKeys.PORTLET_CONFIGURATOR_VISIBILITY, Boolean.FALSE);
				}
				
			} catch (PortalException e) {
				e.printStackTrace();
				renderRequest.setAttribute(WebKeys.PORTLET_CONFIGURATOR_VISIBILITY, Boolean.FALSE);
			}
			super.doView(renderRequest, renderResponse);
		}
	}
	
	@Reference
	private TeacherLocalService teacherLocalService;
	
	@Reference
	private CourseLocalService courseLocalService;
}