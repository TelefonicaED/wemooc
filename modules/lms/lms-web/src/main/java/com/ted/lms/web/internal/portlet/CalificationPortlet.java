package com.ted.lms.web.internal.portlet;

import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.model.CalificationType;
import com.ted.lms.model.CalificationTypeFactory;
import com.ted.lms.model.Course;
import com.ted.lms.model.LearningActivityResult;
import com.ted.lms.model.LearningActivityTry;
import com.ted.lms.registry.CalificationTypeFactoryRegistryUtil;
import com.ted.lms.service.CourseLocalService;
import com.ted.lms.service.LearningActivityTryLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Virginia Martín Agudo
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.name=" + LMSPortletKeys.CALIFICATION,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=guest,user",
		"com.liferay.portlet.use-default-template=true",
		"com.liferay.portlet.add-default-resource=true"
	},
	service = Portlet.class
)
public class CalificationPortlet extends MVCPortlet {
	
	public void updateResult(ActionRequest actionRequest, ActionResponse actionResponse) throws PortalException{
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		long actId = ParamUtil.getLong(actionRequest, "actId");
		
		long studentId = ParamUtil.getLong(actionRequest,"studentId");	
		String comments = ParamUtil.getString(actionRequest,"comments");
		long courseId = ParamUtil.getLong(actionRequest, "courseId");
		
		//Obtenemos el método de calificación para obtener el valor
		Course course = courseLocalService.getCourseByGroupCreatedId(themeDisplay.getScopeGroupId());
		CalificationTypeFactory calificationTypeFactory = CalificationTypeFactoryRegistryUtil.getCalificationTypeFactoryByType(course.getCalificationType());
		CalificationType calificationType = calificationTypeFactory.getCalificationType(course);
		
		try {
		
			double result = calificationType.getResultBase100(actionRequest);
			
			LearningActivityTry  learningActivityTry =  learningActivityTryLocalService.getLastLearningActivityTry(actId, studentId);
			ServiceContext serviceContext = ServiceContextFactory.getInstance(LearningActivityTry.class.getName(), actionRequest);
			if(learningActivityTry==null){
				learningActivityTry = learningActivityTryLocalService.addLearningActivityTry(actId, studentId, serviceContext);
			}
			
			learningActivityTry.setComments(comments);
			learningActivityTryLocalService.finishLearningActivityTry(learningActivityTry, result, serviceContext);
			
			SessionMessages.add(actionRequest, "resultEdited");
		}catch(PortalException e) {
			e.printStackTrace();
			SessionErrors.add(actionRequest, "grades.bad-updating");
		}
		
		actionResponse.setRenderParameter("mvcPath", "/calification/edit_result_form.jsp");
		actionResponse.setRenderParameter("actId", String.valueOf(actId));
		actionResponse.setRenderParameter("studentId", String.valueOf(studentId));
		actionResponse.setRenderParameter("courseId", String.valueOf(courseId));
		actionResponse.setRenderParameter("saved", "true");
	}
	
	@Reference
	CourseLocalService courseLocalService;
	
	@Reference
	LearningActivityTryLocalService learningActivityTryLocalService;

}