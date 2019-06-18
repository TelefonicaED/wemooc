package com.ted.lms.course.eval.ponderated.internal.portlet;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.ted.lms.constants.CourseConstants;
import com.ted.lms.constants.LMSActionKeys;
import com.ted.lms.course.eval.ponderated.PonderatedCourseEval;
import com.ted.lms.course.eval.ponderated.PonderatedCourseEvalFactory;
import com.ted.lms.course.eval.ponderated.constants.PonderatedConstants;
import com.ted.lms.course.eval.ponderated.constants.PonderatedPortletKeys;
import com.ted.lms.model.Course;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.Module;
import com.ted.lms.security.permission.resource.CoursePermission;
import com.ted.lms.service.CourseLocalService;
import com.ted.lms.service.CourseResultLocalService;
import com.ted.lms.service.LearningActivityLocalService;
import com.ted.lms.service.LearningActivityResultLocalService;
import com.ted.lms.service.LearningActivityTryLocalService;
import com.ted.lms.service.ModuleLocalService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
		immediate = true,
		property = {
			"com.liferay.portlet.display-category=category.course-evaluation",
			"com.liferay.portlet.instanceable=false",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=/ponderated_admin/view.jsp",
			"javax.portlet.name=" + PonderatedPortletKeys.PONDERATED,
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=user"
		},
		service = Portlet.class
	)
public class PonderatedAdminPortlet extends MVCPortlet {

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
		
		ThemeDisplay themeDisplay  =(ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		Course course=courseLocalService.getCourseByGroupCreatedId(themeDisplay.getScopeGroupId());
		
		try {
			if (course==null || !CoursePermission.contains(themeDisplay.getPermissionChecker(), course.getGroupCreatedId(), LMSActionKeys.ADD_MODULE) 
					||course.getCourseEvalId() != PonderatedCourseEvalFactory.TYPE) {
				renderRequest.setAttribute(WebKeys.PORTLET_CONFIGURATOR_VISIBILITY, Boolean.FALSE);
			}else {
				
				PonderatedCourseEval ponderatedCourseEval = new PonderatedCourseEval(course, courseResultLocalService,
						learningActivityResultLocalService, learningActivityTryLocalService, learningActivityLocalService);
				
				List<Long> required = ponderatedCourseEval.getRequiredActivities();
				Map<Long,Long> weights=ponderatedCourseEval.getActivitiesWeight(); 
				
				double passPuntuation = ponderatedCourseEval.getPassPuntuation();
				
				List<Module> modules = moduleLocalService.findAllInGroup(themeDisplay.getScopeGroupId());
				
				renderRequest.setAttribute("required", required);
				renderRequest.setAttribute("weights", weights);
				renderRequest.setAttribute("passPuntuation", passPuntuation);
				renderRequest.setAttribute("modules", modules);
				
				super.doView(renderRequest, renderResponse);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void savePonderation(ActionRequest actionRequest, ActionResponse actionResponse) {
		ThemeDisplay themeDisplay  =(ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		Course course=courseLocalService.getCourseByGroupCreatedId(themeDisplay.getScopeGroupId());
		
		List<LearningActivity> listLearningActivities = learningActivityLocalService.getRequiredLearningActivitiesOfGroup(course.getGroupCreatedId());
		JSONObject courseEvalData = course.getCourseEvalJSON();
		if(courseEvalData == null) {
			courseEvalData = JSONFactoryUtil.createJSONObject();
			course.getCourseExtraDataJSON().put(CourseConstants.JSON_COURSE_EVAL, courseEvalData);
		}
		
		double passPuntuation = ParamUtil.getDouble(actionRequest, "passPuntuation", 0);
		courseEvalData.put(CourseConstants.JSON_COURSE_EVAL_PASS_PUNTUATION, passPuntuation);
		
		JSONArray learningActivityRequired = JSONFactoryUtil.createJSONArray();
		courseEvalData.put(PonderatedConstants.JSON_REQUIRED, learningActivityRequired);
		
		JSONArray learningActivityWeights = JSONFactoryUtil.createJSONArray();
		courseEvalData.put(PonderatedConstants.JSON_WEIGHTS, learningActivityWeights);
		
		JSONObject jsonObject = null;
		for(LearningActivity learningActivity: listLearningActivities){
			
			boolean required = ParamUtil.getBoolean(actionRequest, "required_" + learningActivity.getActId(),false);
			if(required){
				jsonObject = JSONFactoryUtil.createJSONObject();
				jsonObject.put(PonderatedConstants.JSON_ACT_ID, learningActivity.getActId());
				learningActivityRequired.put(jsonObject);
			}
			
			double score=ParamUtil.getDouble(actionRequest, "weight_" + learningActivity.getActId(), 0);
			if(score>0){
				jsonObject = JSONFactoryUtil.createJSONObject();
				jsonObject.put(PonderatedConstants.JSON_ACT_ID, learningActivity.getActId());
				jsonObject.put(PonderatedConstants.JSON_WEIGHT_PONDERATION, score);
				learningActivityWeights.put(jsonObject);
			}
		}
		course.setCourseExtraData(course.getCourseExtraDataJSON().toJSONString());
		courseLocalService.updateCourse(course);
	}
	
	@Reference(unbind = "-")
	protected void setCourseLocalService(CourseLocalService courseLocalService) {
		this.courseLocalService = courseLocalService;
	}
	
	private CourseLocalService courseLocalService;
	
	@Reference(unbind = "-")
	protected void setModuleLocalService(ModuleLocalService moduleLocalService) {
		this.moduleLocalService = moduleLocalService;
	}
	
	private ModuleLocalService moduleLocalService;
	
	@Reference(unbind = "-")
	protected void setLearningActivityLocalService(LearningActivityLocalService learningActivityLocalService) {
		this.learningActivityLocalService = learningActivityLocalService;
	}
	
	private LearningActivityLocalService learningActivityLocalService;
	
	@Reference(unbind = "-")
	protected void setCourseResultLocalService(CourseResultLocalService courseResultLocalService) {
		this.courseResultLocalService = courseResultLocalService;
	}
	
	private CourseResultLocalService courseResultLocalService;
	
	@Reference(unbind = "-")
	protected void setLearningActivityResultLocalService(LearningActivityResultLocalService learningActivityResultLocalService) {
		this.learningActivityResultLocalService = learningActivityResultLocalService;
	}
	
	private LearningActivityResultLocalService learningActivityResultLocalService;
	
	@Reference(unbind = "-")
	protected void setLearningActivityTryLocalService(LearningActivityTryLocalService learningActivityTryLocalService) {
		this.learningActivityTryLocalService = learningActivityTryLocalService;
	}
	
	private LearningActivityTryLocalService learningActivityTryLocalService;
}
