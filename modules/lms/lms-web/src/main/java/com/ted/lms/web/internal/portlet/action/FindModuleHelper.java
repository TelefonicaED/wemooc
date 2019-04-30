package com.ted.lms.web.internal.portlet.action;

import javax.portlet.PortletURL;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletLayoutFinder;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.struts.BaseFindActionHelper;
import com.liferay.portal.struts.FindActionHelper;
import com.ted.lms.model.Module;
import com.ted.lms.service.ModuleLocalService;

@Component(
	immediate = true,
	property = "model.class.name=com.ted.lms.model.Module",
	service = FindActionHelper.class
)
public class FindModuleHelper extends BaseFindActionHelper {
	
	private static final Log log = LogFactoryUtil.getLog(FindModuleHelper.class);
	
	@Override
	public long getGroupId(long primaryKey) throws Exception {
		Module module = moduleLocalService.getModule(primaryKey);

		return module.getGroupId();
	}
	
	@Override
	public String getPrimaryKeyParameterName() {
		return "moduleId";
	}
	
	@Override
	public PortletURL processPortletURL(HttpServletRequest request, PortletURL portletURL)
		throws Exception {

		return portletURL;
	}
	
	@Override
	public void setPrimaryKeyParameter(PortletURL portletURL, long primaryKey) throws Exception {

		Module module = moduleLocalService.getModule(primaryKey);

		portletURL.setParameter("moduleId", String.valueOf(module.getModuleId()));
	}
	
	@Override
	protected void addRequiredParameters(HttpServletRequest request, String portletId, PortletURL portletURL) {

		String mvcRenderCommandName = null;
		
		String action = ParamUtil.getString(request, Constants.CMD); 
		
		//Aquí deberíamos saber si venimos para editor o para ver

		if (action.equals(Constants.EDIT)) {
			mvcRenderCommandName = "/modules/edit_module";
		} else if (action.equals(Constants.VIEW)) {
			mvcRenderCommandName = "/modules/view_module";
		}

		portletURL.setParameter("mvcRenderCommandName", mvcRenderCommandName);
		
		log.debug("portletURL: " + portletURL.toString());
	}
	
	@Override
	protected PortletLayoutFinder getPortletLayoutFinder() {
		return portletLayoutFinder;
	}
	
	@Reference(unbind = "-")
	protected void setModuleLocalService(ModuleLocalService moduleLocalService) {

		this.moduleLocalService = moduleLocalService;
	}
	
	@Reference(
		target = "(model.class.name=com.ted.lms.model.Module)",
		unbind = "-"
	)
	protected void setPortletLayoutFinder(PortletLayoutFinder portletPageFinder) {
		this.portletLayoutFinder = portletPageFinder;
	}
	
	private ModuleLocalService moduleLocalService;
	private PortletLayoutFinder portletLayoutFinder;
}
