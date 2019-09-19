package com.ted.lms.web.internal.portlet;

import com.ted.lms.constants.LMSPortletKeys;

import java.io.IOException;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Virginia Mart�n Agudo
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.wemooc",
		"com.liferay.portlet.instanceable=false",
		"com.liferay.portlet.use-default-template=true",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.mvc-command-names-default-views=/results/students",
		"javax.portlet.name=" + LMSPortletKeys.STUDENT_RESULTS,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=user"
	},
	service = Portlet.class
)
public class StudentResultsPortlet extends MVCPortlet {
	
	private static final Log log = LogFactoryUtil.getLog(MVCPortlet.class);
	
	@Override
    public void render(RenderRequest renderRequest, RenderResponse renderResponse)
        throws IOException, PortletException {

        String renderCmd = ParamUtil.getString(renderRequest, "mvcRenderCommandName", null);
        
        if (renderCmd == null) {
        	
        	MVCRenderCommand displayData = getRenderMVCCommandCache().getMVCCommand(getInitParameter("mvc-command-names-default-views"));
        	
            String mvcPath = displayData.render(renderRequest, renderResponse);
            renderRequest.setAttribute(getMVCPathAttributeName(renderResponse.getNamespace()), mvcPath);
        }
        
        super.render(renderRequest, renderResponse); 
    }
	
	
}