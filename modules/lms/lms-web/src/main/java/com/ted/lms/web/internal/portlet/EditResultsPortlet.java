package com.ted.lms.web.internal.portlet;

import com.ted.lms.constants.LMSActionKeys;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.model.CalificationType;
import com.ted.lms.model.CalificationTypeFactory;
import com.ted.lms.model.Course;
import com.ted.lms.model.Module;
import com.ted.lms.registry.CalificationTypeFactoryRegistryUtil;
import com.ted.lms.security.permission.resource.LMSPermission;
import com.ted.lms.service.CourseLocalService;
import com.ted.lms.service.LearningActivityResultLocalService;
import com.ted.lms.service.LearningActivityService;
import com.ted.lms.service.ModuleService;
import com.ted.lms.service.StudentLocalService;
import com.ted.lms.web.internal.display.context.EditResultsDisplayContext;

import java.io.IOException;
import java.util.List;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Virginia Mart�n Agudo
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.wemooc",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/META-INF/resources/results/edit_results.jsp",
		"javax.portlet.name=" + LMSPortletKeys.EDIT_COURSE_RESULTS,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=user"
	},
	service = Portlet.class
)
public class EditResultsPortlet extends MVCPortlet {
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
		
		HttpServletRequest request = portal.getHttpServletRequest(renderRequest);
		
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		try {
			EditResultsDisplayContext editResultsDisplayContext = new EditResultsDisplayContext(request, renderRequest, renderResponse, courseLocalService, 
					studentLocalService, moduleService);
			
			Course course = editResultsDisplayContext.getCourse();
			CalificationTypeFactory calificationTypeFactory = CalificationTypeFactoryRegistryUtil.getCalificationTypeFactoryByType(course.getCalificationType());
			CalificationType calificationType = calificationTypeFactory.getCalificationType(course);
			
			List<Module> modules = moduleService.getGroupModules(course.getGroupCreatedId());
			
			boolean viewResults = LMSPermission.contains(themeDisplay.getPermissionChecker(), course.getGroupCreatedId(), LMSActionKeys.VIEW_RESULTS);
			
			renderRequest.setAttribute("calificationType", calificationType);
			renderRequest.setAttribute("editResultsDisplayContext", editResultsDisplayContext);
			renderRequest.setAttribute("learningActivityResultLocalService", learningActivityResultLocalService);
			renderRequest.setAttribute("learningActivityService", learningActivityService);
			renderRequest.setAttribute("modules", modules);
			renderRequest.setAttribute("viewResults", viewResults);
			
			
		} catch (PortalException e) {
			e.printStackTrace();
		}
		
		super.doView(renderRequest, renderResponse);
	}
	
	@Reference
	private CourseLocalService courseLocalService;
	
	@Reference
	private StudentLocalService studentLocalService;
	
	@Reference
	private Portal portal;
	
	@Reference
	private LearningActivityResultLocalService learningActivityResultLocalService;
	
	@Reference
	private LearningActivityService learningActivityService;
	
	@Reference
	private ModuleService moduleService;

}