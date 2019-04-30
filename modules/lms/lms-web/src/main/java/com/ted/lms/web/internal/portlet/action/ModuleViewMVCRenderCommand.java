package com.ted.lms.web.internal.portlet.action;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ted.lms.constants.LMSPortletKeys;
import com.ted.lms.model.Module;
import com.ted.lms.service.ModuleLocalService;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + LMSPortletKeys.COURSE_CONTENT_VIEWER,
		"mvc.command.name=/", "mvc.command.name=/modules/view_module"
	},
	service = MVCRenderCommand.class
)
public class ModuleViewMVCRenderCommand implements MVCRenderCommand {
	
	private static final Log log = LogFactoryUtil.getLog(ModuleViewMVCRenderCommand.class);

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		long moduleId = ParamUtil.getLong(renderRequest, "moduleId", 0);
		log.debug("render view moduleId: " + moduleId);
		
		Module module = null;
		
		if(moduleId > 0) {
			module = moduleLocalService.fetchModule(moduleId);
		}
		
		renderRequest.setAttribute("module", module);
		
		return "/modules/view_module.jsp";
	}
	
	@Reference(unbind = "-")
	protected void setModuleLocalService(ModuleLocalService moduleLocalService) {
		this.moduleLocalService = moduleLocalService;
	}
	
	private ModuleLocalService moduleLocalService;

}
