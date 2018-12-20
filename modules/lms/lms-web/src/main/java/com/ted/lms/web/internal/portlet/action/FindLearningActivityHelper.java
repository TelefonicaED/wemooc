package com.ted.lms.web.internal.portlet.action;

import javax.portlet.PortletURL;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.portlet.PortletLayoutFinder;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.struts.BaseFindActionHelper;
import com.liferay.portal.struts.FindActionHelper;
import com.ted.lms.model.LearningActivity;
import com.ted.lms.model.Module;
import com.ted.lms.service.LearningActivityLocalService;
import com.ted.lms.service.ModuleLocalService;

@Component(
	immediate = true,
	property = "model.class.name=com.ted.lms.model.LearningActivity",
	service = FindActionHelper.class
)
public class FindLearningActivityHelper extends BaseFindActionHelper {
	@Override
	public long getGroupId(long primaryKey) throws Exception {
		LearningActivity activity = learningActivityLocalService.getLearningActivity(primaryKey);

		return activity.getGroupId();
	}
	
	@Override
	public String getPrimaryKeyParameterName() {
		return "actId";
	}
	
	@Override
	public PortletURL processPortletURL(HttpServletRequest request, PortletURL portletURL)
		throws Exception {

		return portletURL;
	}
	
	@Override
	public void setPrimaryKeyParameter(PortletURL portletURL, long primaryKey) throws Exception {

		LearningActivity activity = learningActivityLocalService.getLearningActivity(primaryKey);

		portletURL.setParameter("actId", String.valueOf(activity.getActId()));
	}
	
	@Override
	protected void addRequiredParameters(HttpServletRequest request, String portletId, PortletURL portletURL) {

		String mvcRenderCommandName = null;
		
		String action = ParamUtil.getString(request, Constants.CMD); 
		
		//Aquí deberíamos saber si venimos para editor o para ver

		if (action.equals(Constants.EDIT)) {
			mvcRenderCommandName = "/activities/edit_activity";
		} else if (action.equals(Constants.VIEW)) {
			mvcRenderCommandName = "/activities/view_activity";
		}

		portletURL.setParameter("mvcRenderCommandName", mvcRenderCommandName);
	}
	
	@Override
	protected PortletLayoutFinder getPortletLayoutFinder() {
		return portletLayoutFinder;
	}
	
	@Reference(unbind = "-")
	protected void setLearningActivityLocalService(LearningActivityLocalService learningActivityLocalService) {

		this.learningActivityLocalService = learningActivityLocalService;
	}
	
	@Reference(
		target = "(model.class.name=com.ted.lms.model.LearningActivity)",
		unbind = "-"
	)
	protected void setPortletLayoutFinder(PortletLayoutFinder portletPageFinder) {
		this.portletLayoutFinder = portletPageFinder;
	}
	
	private LearningActivityLocalService learningActivityLocalService;
	private PortletLayoutFinder portletLayoutFinder;
}
