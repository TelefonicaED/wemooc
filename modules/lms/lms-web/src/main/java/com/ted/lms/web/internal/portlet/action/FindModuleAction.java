package com.ted.lms.web.internal.portlet.action;

import javax.portlet.PortletURL;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.portlet.PortletLayoutFinder;
import com.liferay.portal.kernel.struts.StrutsAction;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.struts.FindStrutsAction;
import com.ted.lms.model.Module;
import com.ted.lms.service.ModuleLocalService;

@Component(
	immediate = true, property = "path=/modules/find_module",
	service = StrutsAction.class
)
public class FindModuleAction extends FindStrutsAction {
	
	@Override
	public long getGroupId(long primaryKey) throws Exception {
		Module activity = moduleLocalService.getModule(primaryKey);

		return activity.getGroupId();
	}
	
	@Override
	public String getPrimaryKeyParameterName() {
		return "moduleId";
	}
	
	@Override
	protected PortletLayoutFinder getPortletLayoutFinder() {
		return portletLayoutFinder;
	}
	
	@Reference
	private ModuleLocalService moduleLocalService;
	
	@Reference(target = "(model.class.name=com.ted.lms.model.Module)")
	private PortletLayoutFinder portletLayoutFinder;

	
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
	}
	
	@Override
	public void setPrimaryKeyParameter(PortletURL portletURL, long primaryKey)
		throws Exception {
		portletURL.setParameter(
				"moduleId", String.valueOf(primaryKey));
		
	}
}
