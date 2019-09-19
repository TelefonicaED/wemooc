package com.ted.lms.web.internal.portlet;

import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.service.LearningActivityService;
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
		"javax.portlet.init-param.view-template=/navigator/view.jsp",
		"javax.portlet.name=" + LMSPortletKeys.NAVIGATOR,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=user",
		"javax.portlet.supported-public-render-parameter=actId",
		"javax.portlet.supported-public-render-parameter=moduleId"
	},
	service = Portlet.class
)
public class NavigatorPortlet extends MVCPortlet {
	
	private static final Log log = LogFactoryUtil.getLog(NavigatorPortlet.class);
	
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
		
		long actId = ParamUtil.getLong(renderRequest, "actId", 0);
		long moduleId = ParamUtil.getLong(renderRequest, "moduleId", 0);
		
		log.debug("actId: " + actId);
		log.debug("moduleId: " + moduleId);
		
		ThemeDisplay themeDisplay  =(ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		//Comprobamos la primera opción: seleccionado un m�dulo
		//Debemos tener en cuenta tambi�n los bloqueos
		if(moduleId != 0 && actId == 0){
			
			//Primera actividad del módulo
			LearningActivity firstActivity = null;
			try {
				firstActivity = learningActivityService.getFirstLearningActivity(themeDisplay.getScopeGroupId(), moduleId);
			} catch (PortalException e) {
				e.printStackTrace();
			}
			
			if(firstActivity != null) {
				String urlFirstActivity = firstActivity.getURLView(themeDisplay);
				renderRequest.setAttribute("urlFirstActivity", urlFirstActivity);
			}
			
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
			
				LearningActivity lastActivity = null;
				try {
					lastActivity = learningActivityService.getPreviousLearningActivity(activity);
				} catch (PortalException e) {
					e.printStackTrace();
				}
				LearningActivity nextActivity = null;
				try {
					nextActivity = learningActivityService.getNextLearningActivity(activity);
				} catch (PortalException e) {
					e.printStackTrace();
				}
				
				if(lastActivity != null) {
					String urlLastActivity = lastActivity.getURLView(themeDisplay);
					renderRequest.setAttribute("urlLastActivity", urlLastActivity);
				}
				
				if(nextActivity != null) {
					String urlNextActivity = nextActivity.getURLView(themeDisplay);
					renderRequest.setAttribute("urlNextActivity", urlNextActivity);
				}
			}
		}
		
		super.render(renderRequest, renderResponse);
	}
	
	@Reference(unbind = "-")
	protected void setLearningActivityService(LearningActivityService learningActivityService) {
		this.learningActivityService = learningActivityService;
	}
	
	private LearningActivityService learningActivityService;

}