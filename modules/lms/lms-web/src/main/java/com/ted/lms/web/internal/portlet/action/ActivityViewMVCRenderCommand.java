package com.ted.lms.web.internal.portlet.action;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.ted.audit.api.AuditFactory;
import com.ted.lms.constants.LMSAuditConstants;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.model.Course;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.LearningActivityTypeFactory;
import com.ted.lms.registry.LearningActivityTypeFactoryRegistryUtil;
import com.ted.lms.service.CourseLocalServiceUtil;
import com.ted.lms.service.LearningActivityLocalService;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + LMSPortletKeys.COURSE_CONTENT_VIEWER,
		"mvc.command.name=/activities/view_activity"
	},
	service = MVCRenderCommand.class
)
public class ActivityViewMVCRenderCommand implements MVCRenderCommand {
	
	private static final Log log = LogFactoryUtil.getLog(ActivityViewMVCRenderCommand.class);

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		long actId = ParamUtil.getLong(renderRequest, "actId", 0);
		
		LearningActivity activity = null;
		
		if(actId > 0) {
			activity = learningActivityLocalService.fetchLearningActivity(actId);
			
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			
			AuditFactory.audit(themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId(), LMSAuditConstants.LEARNING_ACTIVITY_VIEW, 
					LearningActivity.class.getName(), actId, themeDisplay.getUserId(), themeDisplay.getUser().getFullName(), null);
			
			LearningActivityTypeFactory learningActivityTypeFactory = LearningActivityTypeFactoryRegistryUtil.getLearningActivityTypeFactoryByType(activity.getTypeId());
			Course course = CourseLocalServiceUtil.getCourseByGroupCreatedId(activity.getGroupId());
			
			PortalUtil.clearRequestParameters(renderRequest);
			
			try {
				if(activity.canAccess(learningActivityTypeFactory.canAccessFinished(), themeDisplay.getUser(), themeDisplay.getPermissionChecker(), course)){
					//Embebemos el portlet sin bordes
					StringBundler sb = new StringBundler();
					sb.append("<portlet-preferences >");
					sb.append("<preference>");
					sb.append("<name>");
					sb.append("portletSetupShowBorders");
					sb.append("</name>");
					sb.append("<value>");
					sb.append("false");
					sb.append("</value>");
					sb.append("</preference>");
					sb.append("</portlet-preferences>");
					
					StringBundler queryStringStringBundler = new StringBundler();
					//Pasamos el actId
					queryStringStringBundler.append("actId");
					queryStringStringBundler.append(StringPool.EQUAL);
					queryStringStringBundler.append(String.valueOf(actId));
					
					log.debug("queryStringBundler: " + queryStringStringBundler);
					renderRequest.setAttribute("activity", activity);
					renderRequest.setAttribute("activityPortletName", learningActivityTypeFactory.getPortletId());
					renderRequest.setAttribute("defaultPreferences", sb);
					renderRequest.setAttribute("queryString", queryStringStringBundler.toString());
					
					return "/activities/view_activity.jsp";
				}else {
					log.debug("no puedes acceder al curso");
					renderRequest.setAttribute(WebKeys.PORTLET_CONFIGURATOR_VISIBILITY, Boolean.FALSE);
					return null;
				}
			} catch (PortalException e) {
				e.printStackTrace();
				renderRequest.setAttribute(WebKeys.PORTLET_CONFIGURATOR_VISIBILITY, Boolean.FALSE);
				return null;
			}

		}else {
			//No hay actividad para mostrar as� que ocultamos el portlet
			renderRequest.setAttribute(WebKeys.PORTLET_CONFIGURATOR_VISIBILITY, Boolean.FALSE);
			return null;
		}
	}
	
	@Reference(unbind = "-")
	protected void setLearningActivityLocalService(LearningActivityLocalService learningActivityLocalService) {
		this.learningActivityLocalService = learningActivityLocalService;
	}
	
	private LearningActivityLocalService learningActivityLocalService;

}
