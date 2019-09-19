package com.ted.lms.web.internal.portlet;

import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.Module;
import com.ted.lms.model.ModuleResult;
import com.ted.lms.service.LearningActivityService;
import com.ted.lms.service.ModuleLocalService;
import com.ted.lms.service.ModuleResultLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import java.io.IOException;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
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
		"javax.portlet.init-param.view-template=/module_timer/view.jsp",
		"javax.portlet.name=" + LMSPortletKeys.MODULE_TIMER,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=user",
		"javax.portlet.supported-public-render-parameter=actId",
		"javax.portlet.supported-public-render-parameter=moduleId"
	},
	service = Portlet.class
)
public class ModuleTimerPortlet extends MVCPortlet {
	
	private static final Log log = LogFactoryUtil.getLog(ModuleTimerPortlet.class);
	
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
		
		long actId = ParamUtil.getLong(renderRequest, "actId", 0);
		long moduleId = ParamUtil.getLong(renderRequest, "moduleId", 0);
		
		log.debug("actId: " + actId);
		log.debug("moduleId: " + moduleId);
		
		ThemeDisplay themeDisplay  =(ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		//Comprobamos la primera opción: seleccionado un m�dulo
		//Debemos tener en cuenta tambi�n los bloqueos
		
		Module module = null;
		
		if(moduleId != 0){
			
			module = moduleLocalService.fetchModule(moduleId);
			
		}else if(actId != 0){
			
			LearningActivity activity = null;
			try {
				activity = learningActivityService.getLearningActivity(actId);
			} catch (PrincipalException e1) {
				e1.printStackTrace();
			} catch (PortalException e1) {
				e1.printStackTrace();
			}
			
			if(activity != null) {
				module = moduleLocalService.fetchModule(activity.getModuleId());
			}
		}
		
		if(module != null) {
			
			ModuleResult moduleResult = moduleResultLocalService.getModuleResult(moduleId, themeDisplay.getUserId());
			
			if(moduleResult != null && !moduleResult.isPassed()) {
				long usedTime = System.currentTimeMillis() - moduleResult.getStartDate().getTime();
				long leftTime = module.getAllowedTime() - usedTime;
			}
			
			super.render(renderRequest, renderResponse);
		}else {
			renderRequest.setAttribute(WebKeys.PORTLET_CONFIGURATOR_VISIBILITY, Boolean.FALSE);
		}
	}
	
	@Reference(unbind = "-")
	protected void setLearningActivityService(LearningActivityService learningActivityService) {
		this.learningActivityService = learningActivityService;
	}
	
	private LearningActivityService learningActivityService;
	
	@Reference
	private ModuleLocalService moduleLocalService;
	
	@Reference
	private ModuleResultLocalService moduleResultLocalService;

}